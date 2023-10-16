/**
 * Created by Ankit Bhatt for Generating Payslip.
 */
package com.tcs.sgv.eis.service;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.OfflineContriServiceImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAO;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.LoanIntRecoverDAOImpl;
import com.tcs.sgv.eis.dao.LoanPrinRecoverDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.EdpDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQuaterTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpIntRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpPrinRecoverDtls;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.PayslipBasicDetailsVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

@SuppressWarnings( { "unchecked", "deprecation" })
public class GeneratePaySlipService extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	public ResultObject approvePayBill(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String viewname = "";
		String approveFlag = "";
		Date approvalStartTime = DBUtility.getCurrentDateFromDB();
		if (objectArgs.get("approveFlag") != null && !objectArgs.get("approveFlag").equals(""))
		{
			approveFlag = objectArgs.get("approveFlag").toString();
		}
		if (approveFlag.equals("FromTokenNumber"))
			viewname = "ViewTokenNumber";
		else
			viewname = "ApproveBillWindow";
		try
		{
			int monthGiven = -1; //-1 is default for current month 
			int yearGiven = -1; //-1 is default for current year

			ResourceBundle rs = ResourceBundle.getBundle("resources.Payroll");
			long appFlag = Long.parseLong(rs.getString("approved"));

			//ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");

			long advEMI = 0;
			long emp_loan_id = 0;
			long prin_install_no = 0;
			long int_install_no = 0;
			long tot_prin_install = 0;
			long tot_int_install = 0;
			long prinLoanAmt = 0;
			long recoveredPrinLoanAmt = 0;
			double totLoan = 0;
			long loanEMI = 0;
			long intLoanAmt = 0;
			long recoveredIntLoanAmt = 0;
			//			int advId; // Current Loan's Id.
			int loanStatusFlag = Integer.parseInt(rs.getString("activate"));

			int maxDayInMonth = 0;

			HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
			EmpGpfDtlsDAO empGpfDtlsDAO = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class, serv.getSessionFactory());

			//PaybillRegMpgDAOImpl paybillRegMpgDAOImpl = new PaybillRegMpgDAOImpl(PaybillBillregMpg.class, serv.getSessionFactory());

			int incrementAmt = 0; // Next Increment Amount.
			// End By Urvin shah.

			//Added By Paurav
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
			//Ended By Paurav

			LoanIntRecoverDAOImpl hrLoanIntRecoverDao = new LoanIntRecoverDAOImpl(HrLoanEmpIntRecoverDtls.class, serv.getSessionFactory());
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			List<HrLoanEmpIntRecoverDtls> empLoanIntList = new ArrayList();
			HrLoanEmpPrinRecoverDtls princiRecorerVo = new HrLoanEmpPrinRecoverDtls();

			LoanPrinRecoverDAOImpl hrLoanPrinRecoverDao = new LoanPrinRecoverDAOImpl(HrLoanEmpPrinRecoverDtls.class, serv.getSessionFactory());

			EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
			//List<HrPayEdpCompoMpg> edpList = (ArrayList) hrEdp.getAllData();
			List<HrPayEdpCompoMpg> edpList = (ArrayList) hrEdp.getAllDataLoanAndAdvcances();
			HrPayEdpCompoMpg edpComp = new HrPayEdpCompoMpg();
			Date sysdate = DBUtility.getCurrentDateFromDB();
			HrLoanEmpIntRecoverDtls intRecoverVO = new HrLoanEmpIntRecoverDtls();

			GenericDaoHibernateImpl hrEisProofDtlsDAO = new GenericDaoHibernateImpl(HrEisProofDtl.class);
			String colsLoanEmpDtls[] = { "orgPostMstByUserId.userId" };
			hrEisProofDtlsDAO.setSessionFactory(serv.getSessionFactory());
			List<HrEisProofDtl> hrEisProofDtlList;

			//by manoj for non govt change in payslip
			//			NonGovDeducDAO nonGovDeducDAO = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());

			//			List nonGovDeducList = new ArrayList();
			//			HrPayNonGovDeduction hrPayNonGovDeduction = new HrPayNonGovDeduction();
			//			HrPayPayslipNonGovt nonGovtPayslip = new HrPayPayslipNonGovt();

			List<HrLoanEmpPrinRecoverDtls> princiRecorerVoList = new ArrayList<HrLoanEmpPrinRecoverDtls>();
			List<HrLoanEmpDtls> hrLoanEmpList = new ArrayList<HrLoanEmpDtls>();
			List<HrLoanEmpIntRecoverDtls> intRecoverVOList = new ArrayList<HrLoanEmpIntRecoverDtls>();
			List<HrPayPayslip> paySlipVOList = new ArrayList<HrPayPayslip>();
			List<HrPayPaybill> hrPayPaybillList = new ArrayList<HrPayPaybill>();
			List<PaybillHeadMpg> paybillHeadMappingList = new ArrayList<PaybillHeadMpg>();
			//List hrPayPaybillList= new ArrayList();
			//List paybillHeadMappingList= new ArrayList();

			GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
			//end by manoj for non govt change in payslip

			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
			List intList = payBillDAOImpl.getLoanIntList();
			String givenBillNo = "";

			if (request.getParameter("month") != null && !request.getParameter("month").equals(""))
			{
				monthGiven = Integer.parseInt(request.getParameter("month").toString());
				yearGiven = Integer.parseInt(request.getParameter("year").toString());
				////logger.error("Given month and year for approve payslip is " + monthGiven + "--" + yearGiven);
			}
			if (request.getParameter("approveFlag") != null && !request.getParameter("approveFlag").equals(""))
			{
				approveFlag = request.getParameter("approveFlag").toString();
			}

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, yearGiven);
			cal.set(Calendar.MONTH, monthGiven - 1);

			java.util.Date approvalDate = cal.getTime();

			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			if (request.getParameter("billNo") != null && !request.getParameter("billNo").equals(""))
			{
				givenBillNo = request.getParameter("billNo").toString();
				////logger.error("::::::::::::::::::::::::::::::  givenBillNo from request.getParameter :::::::::::::::::::::::::   " + givenBillNo);
			}
			if (objectArgs.get("billNo") != null && !objectArgs.get("billNo").equals("") && objectArgs.get("approveFlag") != null && objectArgs.get("approveFlag").equals("FromTokenNumber"))
			{
				givenBillNo = objectArgs.get("billNo").toString();
				////logger.error("::::::::::::::::::::::::::::::  givenBillNo  from objectArgs.get:::::::::::::::::::::::::   " + givenBillNo);
			}

			PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class, serv.getSessionFactory());

			int currMonth = 0;
			int currYear = 0;

			if (monthGiven != -1 && yearGiven != -1)
			{
				currMonth = monthGiven;
				currYear = yearGiven;
			}
			else
			{
				Date DBDate = DBUtility.getCurrentDateFromDB();
				currMonth = DBDate.getMonth() + 1;
				currYear = DBDate.getYear() + 1900;
				//logger.error("DB Month is " + currMonth + "\nCurrent year is " + currYear);
			}
			//			check whether there are already records for the given month and year
			List paySlipRecs = new ArrayList();
			paySlipRecs = paySlipDAO.checkPayslipForInner(givenBillNo, locId, currMonth, currYear);
			//Added by Paurav. Condition changed by Paurav for checking if any employees there for whom payslip not generated
			if (paySlipRecs == null || paySlipRecs.size() > 0) //if(paySlipRecs!=null && paySlipRecs.size()>0)
			{
				throw new DuplicateRecordException();
			}
			else
			{
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

				long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

				paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class, serv.getSessionFactory());

				long paybillId = 0;

				paybillId = paySlipDAO.getPayBillGrpId(givenBillNo, locId, currMonth, currYear);

				HrPayPaybill objPayBillVO = new HrPayPaybill();
				List<HrPayPaybill> lstPayPayBill = payBillDAOImpl.getListByColumnAndValue("paybillGrpId", paybillId);

				//Added by manish khunt for performance improvement
				String empIdStr = "";
				int totalEmployees=0;
				if (lstPayPayBill != null && !lstPayPayBill.isEmpty())
				{
					int size = lstPayPayBill.size();
					for (int k = 0; k < size; k++)
					{
						HrEisEmpMst eisEmpMst = lstPayPayBill.get(k).getHrEisEmpMst();
						if (eisEmpMst != null)
						{
							empIdStr += "," + String.valueOf(eisEmpMst.getEmpId());
							totalEmployees++;
						}

					}
					empIdStr = empIdStr.replaceFirst(",", "");
				}
				logger.info("empId String is" + empIdStr);

				String empLoanIdStr = "";
				Map<String, HrLoanEmpDtls> empLoanMap = hrLoanEmpDao.getEmpLoanDetailAll(empIdStr);
				if (empLoanMap != null && !empLoanMap.isEmpty())
				{
					Set keys = empLoanMap.keySet();
					//int sizeMap = keys.size();

					//String[] str =(String[])keys. toArray();
					Iterator itr = keys.iterator();
					while (itr.hasNext())
					{
						empLoanIdStr += "," + String.valueOf(empLoanMap.get(itr.next()).getEmpLoanId());
					}
					empLoanIdStr = empLoanIdStr.replaceFirst(",", "");
				}
				logger.info("empLoanId String is" + empLoanIdStr);

				Map<Long, HrLoanEmpPrinRecoverDtls> prinMap = hrLoanPrinRecoverDao.getAllDataPrinApprove(empLoanIdStr);
				logger.info("Prin Map is " + prinMap);

				Map<Long, HrLoanEmpIntRecoverDtls> intMap = hrLoanIntRecoverDao.getAllDataIntApprove(empLoanIdStr);
				logger.info("int Map is " + intMap);

				//ended by manish  for performance Management

				Class pay = objPayBillVO.getClass();
				double methodValue = 0;
				double methodIntValue = 0;

				//added by manish
				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

				//dumping records for next month
				//checking if we found record of next month with approve flag=1
				//then no need to dump records for approve flag=4
				int nextMonth = monthGiven;
				int nextYear = yearGiven;
				if (monthGiven == 12)
				{
					nextMonth = 1;
					nextYear++;
				}
				else
				{
					nextMonth++;
				}

				List<PaybillHeadMpg> paybillHeadMpgList = paybillHeadMpgDAOImpl.getPaybillHeadFromPaybillId(paybillId);
				PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
				paybillHeadMpg = (PaybillHeadMpg) paybillHeadMpgList.get(0);
				objectArgs.put("counter", totalEmployees);
				IdGenerator idGen = new IdGenerator();
				long pkSeqPayslipId = idGen.PKGenerator("HR_PAY_PAYSLIP", objectArgs);
				logger.info("pkSeqPayslipId *****************************"+pkSeqPayslipId);
				objectArgs.remove("counter");
				long paySlipId=0;
				for (Iterator it = lstPayPayBill.iterator(); it.hasNext();)
				{
					logger.info("  353 ****************IN Iterator*************");
					long quaterId = 0;
					paySlipId=0;
					HrPayPayslip paySlipVO = new HrPayPayslip();

					HrPayPaybill payBillVO = (HrPayPaybill) it.next();
					HrEisEmpMst hrEis = payBillVO.getHrEisEmpMst();
					if (hrEis == null)
						continue;

					for (int i = 0; i < edpList.size(); i++)
					{
						methodValue = 0;
						methodIntValue = 0;

						edpComp = new HrPayEdpCompoMpg();
						edpComp = edpList.get(i);

						String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
						long compoType = edpComp.getCmnLookupId();
						String compoCode = edpComp.getTypeId();

						hrEisEmpMst = payBillVO.getHrEisEmpMst();
						logger.info("  hrEisEmpMst *****************************"+hrEisEmpMst.getEmpId());
						//long empIdForLoan = hrEisEmpMst.getOrgEmpMst().getEmpId();
						long empIdForLoan = hrEisEmpMst.getEmpId();

						if (compoType == 2500136)//for advances
						{
							String mthdName = "getAdv" + edpCode;
							Method payMthd = pay.getMethod(mthdName, null);
							methodValue = (Double) payMthd.invoke(payBillVO, null);

							if (methodValue != 0)
							{

								logger.info("  hrEisEmpMst ********2500136 insieede*********************"+hrEisEmpMst.getEmpId());
								Method method = null;
								String setterString = "";
								setterString = "set" + resourceBundle.getString("adv" + compoCode);
								logger.info("  setterString ********setterString nAME*********************"+setterString);
								method = paySlipVO.getClass().getMethod(setterString, long.class);
								logger.info("  method ********setterString nAME*********************"+method.getName());
								long longAmt = Long.parseLong(String.valueOf(Math.round(methodValue)));
								logger.info("  method ********longAmt nAME*********************"+longAmt);
								method.invoke(paySlipVO, longAmt);
								//manish

								//HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();

								//empLoanList = hrLoanEmpDao.getEmpLoanDetail(empIdForLoan, compoCode);//compocode is loan type id
								HrLoanEmpDtls hrLoanEmp = empLoanMap.get(empIdForLoan + "," + compoCode);

								/*	for (int listCnt = 0; listCnt < empLoanList.size(); listCnt++)
									{

										//if (empLoanList.size() > 0)
								*/if (hrLoanEmp != null)
								{

									logger.info("  hrLoanEmp ********IS NOT NULL******************"+emp_loan_id);
									
									//hrLoanEmp = empLoanList.get(listCnt);
									emp_loan_id = hrLoanEmp.getEmpLoanId();
									tot_prin_install = hrLoanEmp.getLoanPrinInstNo();
									tot_int_install = hrLoanEmp.getLoanIntInstNo();
									//empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);
									princiRecorerVo = prinMap.get(emp_loan_id);

									Date loanDate = hrLoanEmp.getLoanDate();
									Calendar c = Calendar.getInstance();
									c.set(Calendar.YEAR, loanDate.getYear() + 1900);
									c.set(Calendar.MONTH, loanDate.getMonth());
									c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());
									c.add(Calendar.MONTH, (int) (tot_prin_install + tot_int_install) + 1);//1 is added to get the last month

									Date endDate = c.getTime();

									c.set(Calendar.YEAR, yearGiven);
									c.set(Calendar.MONTH, monthGiven - 1);

									maxDayInMonth = c.getActualMaximum(5);
									c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);

									Date currentDate = c.getTime(); //1st of given month
									if (princiRecorerVo != null)
									{
										if ((currentDate.compareTo(loanDate) == 1 && currentDate.compareTo(endDate) <= 0) || princiRecorerVo.getTotalRecoveredInst() != tot_prin_install)
										{
											logger.info("  princiRecorerVo ****************IN Iterator*************"+princiRecorerVo.toString());

											/*if (!empLoanPrinList.isEmpty())
											{
												princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
												princiRecorerVo = empLoanPrinList.get(0);*/
											prinLoanAmt = hrLoanEmp.getLoanPrinAmt();
											//											advId = (int) hrLoanEmp.getHrLoanAdvMst().getLoanAdvId();
											recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt(); // Recovered Principal Amount.
											prin_install_no = princiRecorerVo.getTotalRecoveredInst();
											//logger.error("The Principal Advance Amount before :-" + recoveredPrinLoanAmt);
											//loan
											boolean isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
											if (isMultiRecovery)
											{
												prin_install_no = prin_install_no + hrLoanEmp.getMulLoanInstRecvd();
												recoveredPrinLoanAmt = recoveredPrinLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
											}
											if ((isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt) || (!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt))//for principal Recovery amt as well as Installment Number.
											{
												//advEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
												advEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanPrinEmiAmt();
												//the Principal emi value

												if (!isMultiRecovery)
												{
													if (advEMI > (prinLoanAmt - recoveredPrinLoanAmt))
													{
														advEMI = prinLoanAmt - recoveredPrinLoanAmt;
													}
													if (hrLoanEmp.getLoanOddinstno() == (princiRecorerVo.getTotalRecoveredInst() + 1L))
														advEMI = hrLoanEmp.getLoanOddinstAmt();
												}
												long amt = isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt + advEMI;
												princiRecorerVo.setTotalRecoveredAmt(amt);
												long new_no = isMultiRecovery ? prin_install_no : (prin_install_no + 1);
												princiRecorerVo.setTotalRecoveredInst(new_no);
												princiRecorerVo.setUpdatedDate(approvalDate);
												princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
												princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
												princiRecorerVoList.add(princiRecorerVo);

												if (amt >= prinLoanAmt)
												{
													loanStatusFlag = 0;
													hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
												}
												hrLoanEmp.setMulLoanRecRemarks("");
												hrLoanEmp.setMulLoanRecoveryMode(0);
												hrLoanEmp.setMulLoanInstRecvd(1);
												hrLoanEmp.setMulLoanAmtRecvd(0L);
												hrLoanEmpList.add(hrLoanEmp);
											}
										}
									}
								}
								//
							}
						}
						//advance part ends here 
						else if (compoType == 2500137)//for loans
						{

							String mthdName = "getLoan" + edpCode;
							logger.info("  mthdName ****************IN mthdName*************"+mthdName.toString());
							String mthdNameInt = "getLoanInt" + edpCode;
							logger.info("  mthdNameInt ****************IN Iterator*************"+mthdNameInt.toString());
							Method payMthd = pay.getMethod(mthdName, null);
							Method payIntMthd = pay.getMethod(mthdNameInt, null);
							methodValue = (Double) payMthd.invoke(payBillVO, null);
							methodIntValue = (Double) payIntMthd.invoke(payBillVO, null);

							if (methodValue != 0 || methodIntValue != 0)
							{
								if (!intList.contains(compoCode))
								{
									Method method = null;
									String setterString = "";
									setterString = "set" + resourceBundle.getString("loan" + compoCode);
									logger.info("  setterString ****************IN Iterator*************"+setterString.toString());
									method = paySlipVO.getClass().getMethod(setterString, long.class);
									logger.info("  setterString ****************METHOD CALOING*******"+setterString.toString());
									long longAmt = Math.round(methodValue);
									//logger.error("value is " + longAmt);
									logger.info("  longAmt **************** ***********"+longAmt);
									method.invoke(paySlipVO, longAmt);
								}
								if (intList.contains(compoCode))
								{
									Method method = null;
									String setterString = "";
									setterString = "set" + resourceBundle.getString("loan" + compoCode);
									logger.info("  setterString *******intList******** *******"+setterString.toString());
									method = paySlipVO.getClass().getMethod(setterString, long.class);
									logger.info("  setterString ****************METHOD CALOING*******"+setterString.toString());
									long longAmt = Math.round(methodIntValue);
									logger.info("  setterString ***************longAmt******"+longAmt);
									//logger.error("value is " + longAmt);
									method.invoke(paySlipVO, longAmt);
								}

								//empLoanList = hrLoanEmpDao.getEmpLoanDetail(empIdForLoan, compoCode);//compocode is loan type id

								HrLoanEmpDtls hrLoanEmp = empLoanMap.get(empIdForLoan + "," + compoCode);

								long loanEmi = 0;
								prinLoanAmt = 0;
								recoveredPrinLoanAmt = 0;
								recoveredIntLoanAmt = 0;
								/*for (int listCnt = 0; listCnt < empLoanList.size(); listCnt++)
								{*/

								if (hrLoanEmp != null)
								{
									//HrLoanEmpDtls hrLoanEmp = empLoanList.get(listCnt);
									emp_loan_id = hrLoanEmp.getEmpLoanId();
									tot_prin_install = hrLoanEmp.getLoanPrinInstNo();
									tot_int_install = hrLoanEmp.getLoanIntInstNo();

									//empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);
									//empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
									princiRecorerVo = prinMap.get(emp_loan_id);
									intRecoverVO = intMap.get(emp_loan_id);

									Date loanDate = hrLoanEmp.getLoanDate();

									Calendar c = Calendar.getInstance();

									c.set(Calendar.YEAR, loanDate.getYear() + 1900);
									c.set(Calendar.MONTH, loanDate.getMonth());
									c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());

									c.add(Calendar.MONTH, (int) (tot_prin_install + tot_int_install) + 1);//1 is added to get the last month

									Date endDate = c.getTime();

									c.set(Calendar.YEAR, yearGiven);
									c.set(Calendar.MONTH, monthGiven - 1);
									maxDayInMonth = c.getActualMaximum(5);
									c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
									Date currentDate = c.getTime();
									//logger.error("in approval flag currentdate " + currentDate + " loan date " + loanDate + " end Date " + endDate);

									//manish

									long recoveredInstallments = 0;
									long totalInstallments = 0;

									if (hrLoanEmp != null && hrLoanEmp.getLoanPrinInstNo() != 0)
									{
										totalInstallments = hrLoanEmp.getLoanPrinInstNo();
										recoveredInstallments = princiRecorerVo.getTotalRecoveredInst();
									}
									else
									{

										totalInstallments = hrLoanEmp.getLoanIntInstNo();
										recoveredInstallments = intRecoverVO.getTotalRecoveredIntInst();
									}

									//manish

									if ((currentDate.compareTo(loanDate) == 1 && currentDate.compareTo(endDate) <= 0) || recoveredInstallments != totalInstallments)
									{
										//deduction will be in the current month and 1 less than the current month
										//logger.error("the current date is between loan date and end date");
										if (hrLoanEmp.getLoanPrinInstNo() != 0)
										{
											//logger.error("got the principal list");

											//princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
											//princiRecorerVo = empLoanPrinList.get(0);

											prin_install_no = princiRecorerVo.getTotalRecoveredInst();
											recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt();
											prinLoanAmt = hrLoanEmp.getLoanPrinAmt();

											boolean isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
											if (isMultiRecovery)
											{
												prin_install_no = prin_install_no + hrLoanEmp.getMulLoanInstRecvd();
												recoveredPrinLoanAmt = recoveredPrinLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
											}

											if ((isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt) || (!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt))//for principal Recovery amt
											{
												//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
												loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanPrinEmiAmt();//the Principal emi value

												//long amt=princiRecorerVo.getTotalRecoveredAmt()+loanEMI;
												if (!isMultiRecovery)
												{
													if (loanEMI > (prinLoanAmt - recoveredPrinLoanAmt))
													{
														loanEMI = prinLoanAmt - recoveredPrinLoanAmt;
													}
													if (hrLoanEmp.getLoanOddinstno() == (princiRecorerVo.getTotalRecoveredInst() + 1L))
														loanEMI = hrLoanEmp.getLoanOddinstAmt();
												}
												long amt = isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt + loanEMI;
												princiRecorerVo.setTotalRecoveredAmt(amt);
												long new_no = isMultiRecovery ? prin_install_no : prin_install_no + 1;
												princiRecorerVo.setTotalRecoveredInst(new_no);
												princiRecorerVo.setUpdatedDate(approvalDate);
												princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
												princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
												princiRecorerVoList.add(princiRecorerVo);
												//hrLoanPrinRecoverDao.update(princiRecorerVo);

												loanEmi = loanEmi + loanEMI;

											}
											else
											//for interest Recovery amt
											{
												intRecoverVO = new HrLoanEmpIntRecoverDtls();
												empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);

												if (!empLoanIntList.isEmpty())
													intRecoverVO = empLoanIntList.get(0);
												intLoanAmt = hrLoanEmp.getLoanInterestAmt();
												recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();

												int_install_no = intRecoverVO.getTotalRecoveredIntInst();
												if (isMultiRecovery)
												{
													int_install_no = int_install_no + hrLoanEmp.getMulLoanInstRecvd();
													recoveredIntLoanAmt = recoveredIntLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
												}

												if ((isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt) || (!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt))
												{
													//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
													loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value
													if (!isMultiRecovery)
													{
														if (loanEMI > (intLoanAmt - recoveredIntLoanAmt))
														{
															loanEMI = intLoanAmt - recoveredIntLoanAmt;
														}
														if (hrLoanEmp.getLoanOddinstno() == (intRecoverVO.getTotalRecoveredIntInst() + 1L))
															loanEMI = hrLoanEmp.getLoanOddinstAmt();
													}
													long amt = isMultiRecovery ? recoveredIntLoanAmt : recoveredIntLoanAmt + loanEMI;
													intRecoverVO.setTotalRecoveredInt(amt);
													long new_no = isMultiRecovery ? int_install_no : int_install_no + 1;
													intRecoverVO.setTotalRecoveredIntInst(new_no);
													intRecoverVO.setUpdatedDate(approvalDate);
													intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
													intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
													intRecoverVOList.add(intRecoverVO);
													//hrLoanIntRecoverDao.update(intRecoverVO);

													loanEmi = loanEmi + loanEMI;
												}
											}
											if (princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt())
											{
												loanStatusFlag = 0;
												hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
											}
											hrLoanEmp.setMulLoanRecRemarks("");
											hrLoanEmp.setMulLoanRecoveryMode(0);
											hrLoanEmp.setMulLoanInstRecvd(1);
											hrLoanEmp.setMulLoanAmtRecvd(0L);
											hrLoanEmpList.add(hrLoanEmp);
											//hrLoanEmpDao.update(hrLoanEmp);

										}
										else
										{
											//logger.error("got the principal list null now going to calculate interest");
											//intRecoverVO = new HrLoanEmpIntRecoverDtls();
											//empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
											//logger.error("the interest list is " + empLoanIntList);
											//if (empLoanIntList.size() > 0)
											//intRecoverVO = empLoanIntList.get(0);

											intLoanAmt = hrLoanEmp.getLoanInterestAmt();
											recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();
											int_install_no = intRecoverVO.getTotalRecoveredIntInst();
											boolean isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
											if (isMultiRecovery)
											{
												int_install_no = int_install_no + hrLoanEmp.getMulLoanInstRecvd();
												recoveredIntLoanAmt = recoveredIntLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
											}

											if ((isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt) || (!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt))
											{
												//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
												loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value

												if (!isMultiRecovery)
												{
													if (loanEMI > (intLoanAmt - recoveredIntLoanAmt))
													{
														loanEMI = intLoanAmt - recoveredIntLoanAmt;
													}
													if (hrLoanEmp.getLoanOddinstno() == (intRecoverVO.getTotalRecoveredIntInst() + 1L))
														loanEMI = hrLoanEmp.getLoanOddinstAmt();
												}
												long amt = isMultiRecovery ? recoveredIntLoanAmt : recoveredIntLoanAmt + loanEMI;
												intRecoverVO.setTotalRecoveredInt(amt);
												long new_no = isMultiRecovery ? int_install_no : int_install_no + 1;
												intRecoverVO.setTotalRecoveredIntInst(new_no);
												intRecoverVO.setUpdatedDate(approvalDate);
												intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
												intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
												intRecoverVOList.add(intRecoverVO);
												//hrLoanIntRecoverDao.update(intRecoverVO);

												loanEmi = loanEmi + loanEMI;
											}

											if (princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt())
											{
												loanStatusFlag = 0;
												hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
											}
											hrLoanEmp.setMulLoanRecRemarks("");
											hrLoanEmp.setMulLoanRecoveryMode(0);
											hrLoanEmp.setMulLoanInstRecvd(1);
											hrLoanEmp.setMulLoanAmtRecvd(0L);
											hrLoanEmpList.add(hrLoanEmp);
											//hrLoanEmpDao.update(hrLoanEmp);

										}
									}

								}

								totLoan = totLoan + loanEmi;

								//								Class thisClass = this.getClass();

							}

						}
						// loan part end here 

					}
					//	loan calculation after approval end			  			 
					logger.info("Hii here *************");
					HrPayPaybill payBillObj = new HrPayPaybill(); //object to set in PaySlip
					payBillObj.setId(payBillVO.getId());
					logger.info("payBillObj****payBillObj value*********"+payBillObj.getPaybillGrpId());
					payBillObj.setNetTotal(payBillVO.getNetTotal());
					if (hrEisEmpMst != null)
					{
						OtherDetailDAOImpl otherDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
						List otherDtlsList = otherDAO.getEmpAvailable(hrEisEmpMst.getEmpId());
						incrementAmt = 0;
						if (otherDtlsList.size() > 0)
						{
							HrEisOtherDtls otherVO = (HrEisOtherDtls) otherDtlsList.get(0);
							if (otherVO.getHrEisSgdMpg() != null)
							{
								incrementAmt = getIncrementAmount(otherVO.getHrEisSgdMpg().getHrEisScaleMst(), otherVO.getotherCurrentBasic());
							}
						}
					}
					else
					{
						throw new Exception();
					}

					HrEisQtrMst hrEssQuater = new HrEisQtrMst();
					QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class, serv.getSessionFactory());
					List quaterList = new ArrayList();
					logger.info("After quarter liost********");
					long userID = 0;
					if (payBillVO.getHrEisEmpMst() != null)
						userID = payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();

					if (userID != 0)
						quaterList = hrEssQuaterDao.getQuarterDtls(userID);

					if (quaterList != null && quaterList.size() > 0)
					{
						logger.info("AFter quarter liost****quaterList inside****");
						
						for (int count = 0; count < quaterList.size(); count++)
						{
							hrEssQuater = (HrEisQtrMst) quaterList.get(count);
							logger.info("AFter quarter liost****HrEisQtrMst inside****isndie ");
							quaterId = hrEssQuater.getHrEisQuaterTypeMst().getQuaId();
						}
					}
					else
					{
						quaterId = 0;
					}
					
					logger.info("After quarter list**user id **payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId()****"+payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId());
					logger.info("After quarter list****12345****"+userID);
					HrEisQuaterTypeMst hrQuaterTypeMst = new HrEisQuaterTypeMst();
					logger.info("After quarter list****12345**solving**"+quaterId);
					if (quaterId != 0)
					{
						logger.info("After quaterId list****12345****"+quaterId);
						hrQuaterTypeMst.setQuaId(quaterId);
					//	paySlipVO.setHrQuaterTypeMst(hrQuaterTypeMst);
					}
					else {
						logger.info("***** In else condition for quarter idto making it nulll **");
						//paySlipVO.setHrQuaterTypeMst(null);
						logger.info("***** In else condition for quarter id * null ho gya*");
					}
					if (userID != 0)
					{
						logger.info("***** In   condition for userID  *"+userID);
						hrGpfBalanceDtls = empGpfDtlsDAO.read(userID);
						logger.info("***** hrGpfBalanceDtls for userID  *"+hrGpfBalanceDtls.getUserId());
						logger.info("***** hrGpfBalanceDtls for userID  *"+hrGpfBalanceDtls.getGpfAccNo());
					}
					logger.info("AFter quarter liost**hrGpfBalanceDtls******"+hrGpfBalanceDtls.getUserId());
					paySlipVO.setGpfAccNo(hrGpfBalanceDtls != null ? hrGpfBalanceDtls.getGpfAccNo() : "");

					if (paybillHeadMpg != null)
					{
						logger.info("***Allfine Till Here       (7283124836432842343fwfuweirt45745*");
						paySlipVO.setGrossAmt(paybillHeadMpg.getBillGrossAmt());
						paySlipVO.setNetTotal(paybillHeadMpg.getBillNetAmt());
						paySlipVO.setMstDcpsBillGroup(paybillHeadMpg.getBillNo());
					}
					else
					{
						logger.info("***Allfine%%%%%%%%%%%");
						paySlipVO.setGrossAmt(0);
						paySlipVO.setNetTotal(0);
					}
					logger.info("***Allfine Till Here *");
					paySlipVO.setIncrementAmt(incrementAmt);

	                paySlipVO.setBasicPay((long) payBillVO.getBasic0101() + (long) payBillVO.getBasic0102()); //Basic Pay.		

	                paySlipVO.setSplPay((long) payBillVO.getAllow0102()); //Spl Pay.
	                paySlipVO.setPerPay((long) payBillVO.getAllow0101()); //Per Pay.
	                paySlipVO.setDa((long) payBillVO.getAllow0103()); //DA.
	                paySlipVO.setDp((long) (payBillVO.getAllow0119() + payBillVO.getAllow0120())); //DP.
	                paySlipVO.setCla((long) payBillVO.getAllow0111()); //CLA.
	                paySlipVO.setHra((long) payBillVO.getAllow0110());//HRA		  		 
	                paySlipVO.setMa((long) payBillVO.getAllow0107()); //MA
	                paySlipVO.setTa((long) payBillVO.getAllow0113());//TA

	                paySlipVO.setArma((long) payBillVO.getAllow1006());
	                paySlipVO.setArmouera((long) payBillVO.getAllow1007());
	                paySlipVO.setBmia((long) payBillVO.getAllow1008());
	                paySlipVO.setCasha((long) payBillVO.getAllow1009());
	                paySlipVO.setCida((long) payBillVO.getAllow1010());
	                paySlipVO.setConva((long) payBillVO.getAllow1011());
	                paySlipVO.setEa((long) payBillVO.getAllow1012());
	                paySlipVO.setEsisa((long) payBillVO.getAllow1013());
	                paySlipVO.setEla((long) payBillVO.getAllow1014());
	                paySlipVO.setFita((long) payBillVO.getAllow1015());
	                paySlipVO.setGaa((long) payBillVO.getAllow1016());
	                paySlipVO.setKma((long) payBillVO.getAllow1017());
	                paySlipVO.setLfa((long) payBillVO.getAllow1018());
	                paySlipVO.setMecha((long) payBillVO.getAllow1019());
	                paySlipVO.setMea((long) payBillVO.getAllow1020());
	                paySlipVO.setMesha((long) payBillVO.getAllow1021());
	                paySlipVO.setNaa((long) payBillVO.getAllow1022());
	                paySlipVO.setNpa((long) payBillVO.getAllow1023());
	                paySlipVO.setSuma((long) payBillVO.getAllow1024());
	                paySlipVO.setPa((long) payBillVO.getAllow1025());
	                paySlipVO.setSda((long) payBillVO.getAllow1026());
	                paySlipVO.setOa((long) payBillVO.getAllow0104());
	                paySlipVO.setAp((long) payBillVO.getAllow1027());
	                paySlipVO.setUa((long) payBillVO.getAllow1028());
	                paySlipVO.setTecha((long) payBillVO.getAllow1003());
	                paySlipVO.setTaa((long) payBillVO.getAllow1032());
	                paySlipVO.setHa((long) payBillVO.getAllow1031());
	                paySlipVO.setPg((long) payBillVO.getAllow1029());
	                paySlipVO.setAts30((long) payBillVO.getAllow1033());
	                paySlipVO.setAts50((long) payBillVO.getAllow1034());
	                paySlipVO.setForce25((long) payBillVO.getAllow1035());
	                paySlipVO.setForce100((long) payBillVO.getAllow1036());
	                paySlipVO.setFpa((long) payBillVO.getAllow1030());
	                paySlipVO.setTaforsixth((long) payBillVO.getAllow1160());

	                paySlipVO.setAddDa((long) payBillVO.getAllow1147());
	                paySlipVO.setAddHra((long) payBillVO.getAllow1148());
	                paySlipVO.setDaArr((long) payBillVO.getAllow1149());
	                paySlipVO.setTempCla((long) payBillVO.getAllow1150());
	                paySlipVO.setFrana((long) payBillVO.getAllow1151());
	                paySlipVO.setTempHra((long) payBillVO.getAllow1152());
	                paySlipVO.setLta((long) payBillVO.getAllow1153());
	                paySlipVO.setMedstu((long) payBillVO.getAllow1154());
	                paySlipVO.setOas((long) payBillVO.getAllow1155());
	                paySlipVO.setPerTra((long) payBillVO.getAllow1156());
	                paySlipVO.setTempTa((long) payBillVO.getAllow1157());
	                paySlipVO.setWa((long) payBillVO.getAllow1158());
	                paySlipVO.setWriPay((long) payBillVO.getAllow1159());
	                //logger.error("Ref Allow in Approve Paybill" + payBillVO.getAllow1161());
	                paySlipVO.setRefAllow((long) payBillVO.getAllow1161()); // refreshment Allowance
	                paySlipVO.setCentralDA((long) payBillVO.getAllow9913());//cda
	                paySlipVO.setCta((long) payBillVO.getAllow9912());//cta
	                paySlipVO.setPeonAllow((long) payBillVO.getAllow1163());//Peon Allowance
	                paySlipVO.setIncentiveBdds((long) payBillVO.getAllow1165());// 
	                paySlipVO.setRltPilot((long) payBillVO.getAllow1166());// 
	                paySlipVO.setChplPilot((long) payBillVO.getAllow1167());// 
	                paySlipVO.setKitPilot((long) payBillVO.getAllow1168());// 
	                paySlipVO.setFlyingPayPilot((long) payBillVO.getAllow1169());// 
	                paySlipVO.setInstructionalPilot((long) payBillVO.getAllow1170());// 
	                paySlipVO.setQualificationPilot((long) payBillVO.getAllow1171());// 
	                paySlipVO.setInspectionPilot((long) payBillVO.getAllow1172());// 
	                paySlipVO.setFlyingAllowPilot((long) payBillVO.getAllow1173());// 
	                paySlipVO.setOutfirPilot((long) payBillVO.getAllow1174());// 
	                paySlipVO.setMiliteryPilot((long) payBillVO.getAllow1175());// 
	                paySlipVO.setSpecialPayPilot((long) payBillVO.getAllow1176());// 
	                paySlipVO.setCpf((long) payBillVO.getAllow1177());//
	                paySlipVO.setBasicArr((long) payBillVO.getAllow1178());//
	                paySlipVO.setDaOnTa((long) payBillVO.getAllow1179());//
	                // Allowance
	                // paySlipVO.setGisArrear((long)payBillVO.getDeduc9914());//GIS
	                // Arrear deduc9914
	                //55 allowances;

	                paySlipVO.setGpfiasos((long) payBillVO.getDeduc1037());
	                paySlipVO.setGpfias((long) payBillVO.getDeduc1038());
	                paySlipVO.setGpfips((long) payBillVO.getDeduc1039());
	                paySlipVO.setGpfifs((long) payBillVO.getDeduc1040());
	                paySlipVO.setServc((long) payBillVO.getDeduc1041());
	                paySlipVO.setOtherd((long) payBillVO.getDeduc1042());

	                paySlipVO.setITax((long) payBillVO.getDeduc9510());
	                paySlipVO.setHrr((long) payBillVO.getDeduc9550());
	                paySlipVO.setPTax((long) payBillVO.getDeduc9570());
                   // paySlipVO.setRevenueStamp((long) payBillVO.getDeduc9135()); // addded by lekhchand  REVENUE_STAMP
	                paySlipVO.setGpfgrpd((long) payBillVO.getDeduc9583());
	                paySlipVO.setGpfgrpbac((long) payBillVO.getDeduc9780());
	                paySlipVO.setGpfgrpbac((long) payBillVO.getDeduc9780());
	                paySlipVO.setDcps((long) payBillVO.getDeduc9534());
	                paySlipVO.setDcpsDelay((long) payBillVO.getDeduc9535());
	                paySlipVO.setDcpsDA((long) payBillVO.getDeduc9537());
	                paySlipVO.setDcpsPay((long) payBillVO.getDeduc9536());

	                paySlipVO.setGisips((long) payBillVO.getDeduc1000());
	                paySlipVO.setGisias((long) payBillVO.getDeduc1001());
	                paySlipVO.setGisifs((long) payBillVO.getDeduc1002());

	                paySlipVO.setCgegis((long) payBillVO.getDeduc1004());
	                paySlipVO.setGis((long) payBillVO.getDeduc1005());
	                paySlipVO.setPli((long) payBillVO.getDeduc9711());
	                paySlipVO.setGiszp((long) payBillVO.getDeduc9701());
	                paySlipVO.setGpfabcmr((long) payBillVO.getDeduc9702());
	                paySlipVO.setGpfdmr((long) payBillVO.getDeduc9703());
	                paySlipVO.setGpfiasmr((long) payBillVO.getDeduc9704());
	                paySlipVO.setGpfifsmr((long) payBillVO.getDeduc9705());
	                paySlipVO.setGpfipsmr((long) payBillVO.getDeduc9706());
	                paySlipVO.setHrrarr((long) payBillVO.getDeduc9707());
	                paySlipVO.setJanjulgisa((long) payBillVO.getDeduc9708());
	                paySlipVO.setJanjulgis((long) payBillVO.getDeduc9911());
	                /*Added By Tejashree 09122019 (allow9207)Test*/
					paySlipVO.setDA_7PC((long) payBillVO.getAllow9207()); //7pc DA.
					/*Ended By Tejashree*/
					/*Added By lekhchand 09122022(allow9212)Test*/
					paySlipVO.setSVNPC_TA((long) payBillVO.getAllow9212()); //7pc TA 
					paySlipVO.setSVNPC_GPF_ARR((long) payBillVO.getAllow9213()); //7pc TA 
					paySlipVO.setSVNPC_DCPS_ARR((long) payBillVO.getAllow9214()); //7pc TA 
					paySlipVO.setSVNPC_TA_ARR((long) payBillVO.getAllow9215()); //7pc TA 
					paySlipVO.setSVNPC_GPF_ARR_DEDU((long) payBillVO.getDeduc9216()); //7pc TA 
					paySlipVO.setSVNPC_GPF_RECO((long) payBillVO.getDeduc9217()); //7pc TA
					paySlipVO.setSVNPC_DCPS_RECO((long) payBillVO.getDeduc9218()); //7pc TA
					/*Ended By lekhchand*/
	                

	                paySlipVO.setPtarr((long) payBillVO.getDeduc9712());
	                paySlipVO.setOdtr((long) payBillVO.getDeduc9713());
	                paySlipVO.setCpfEmployee((long) payBillVO.getDeduc9141());
					paySlipVO.setCpfEmployer((long) payBillVO.getDeduc9140());
					paySlipVO.setCpfContribution((long) payBillVO.getAllow9186());//
	                paySlipVO.setOdtr((long) payBillVO.getDeduc9713());//30 deductions
	              //Added by Kinjal for GAP
					paySlipVO.setGroupAcciPolicy((long) payBillVO.getDeduc9134());
					//NPS Dedcution 14% BY Naveen
					paySlipVO.setNpsDeduction((long) payBillVO.getDeduc9142());
					paySlipVO.setNpsNpsAllowance((long) payBillVO.getAllow9208());


	                paySlipVO.setNetTotal((long) payBillVO.getNetTotal());//net total
	                

	                /**************************************************************************************/
	                paySlipVO.setMonth((long) paybillHeadMpg.getMonth());
	                paySlipVO.setPaySlipDate(approvalDate);
	                paySlipVO.setUpdatedDate(new Date());
	                paySlipVO.setYear((long) paybillHeadMpg.getYear());
	                paySlipVO.setHrEisEmpMst(hrEisEmpMst);
	                paySlipVO.setHrPayPaybill(payBillObj);
	                paySlipVO.setTrnCounter(Integer.valueOf(1));

	                // Added By Urvin shah.

	                String itAccNo = "";

	                Object valsLoanEmpDtls[] = { userID };
	               
	                
	                hrEisProofDtlList = hrEisProofDtlsDAO.getListByColumnAndValue(colsLoanEmpDtls, valsLoanEmpDtls);
	                logger.info("***hrEisProofDtlList ***donme****");
	                if (hrEisProofDtlList != null && !hrEisProofDtlList.isEmpty())
	                    itAccNo = hrEisProofDtlList.get(0).getProofNum();

	                paySlipVO.setItAccNo(itAccNo);
	                // End By Urvin Shah.

	                paySlipVO.setCreatedDate(sysdate);
	                paySlipVO.setCmnLanguageMst(cmnLanguageMst);
	                paySlipVO.setCmnDatabaseMst(cmnDatabaseMst);
	                paySlipVO.setOrgPostMstByUpdatedByPost(orgPostMst);
	                paySlipVO.setOrgPostMstByCreatedByPost(orgPostMst);
	                paySlipVO.setCmnLocationMst(cmnLocationMst);
	                paySlipVO.setOrgUserMstByUpdatedBy(orgUserMst);
	                paySlipVO.setOrgUserMstByCreatedBy(orgUserMst);

	                //long paySlipId = idGen.PKGenerator("HR_PAY_PAYSLIP", objectArgs);
	                paySlipId = ++pkSeqPayslipId;
	                paySlipVO.setPaySlipId(paySlipId);

	                paySlipVOList.add(paySlipVO);
	                
	                

				}
				for (HrPayPaybill hrPayPaybill : lstPayPayBill)
				{
					hrPayPaybill.setApproveRejectDate(approvalDate);
					hrPayPaybillList.add(hrPayPaybill);

				}

				//PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
				//	List<PaybillHeadMpg> paybillHeadMpgList = paybillHeadMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", paybillId);
				long voucherNo = 0;
				String voucherdate;

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if (request.getParameter("voucherNo") != null && !request.getParameter("voucherNo").equals(""))
					voucherNo = Long.parseLong(request.getParameter("voucherNo"));
				voucherdate = (request.getParameter("voucherDate") != null && !(request.getParameter("voucherDate").equals("")) ? (request.getParameter("voucherDate")) : "").toString();
				Date voucherDate = null;
				if (voucherdate != null && (!voucherdate.equals("")))
					voucherDate = sdf.parse(voucherdate);
				if (!voucherdate.equals(""))

					for (PaybillHeadMpg paybillHeadMapping : paybillHeadMpgList)
					{
						paybillHeadMapping.setApproveFlag(appFlag);
						paybillHeadMapping.setUpdatedDate(sysdate);
						paybillHeadMapping.setVoucherNumber(voucherNo);
						paybillHeadMapping.setVoucherDate(voucherDate);
						paybillHeadMappingList.add(paybillHeadMapping);
						//paybillHeadMpgDAOImpl.update(paybillHeadMapping);

					}
				//Ended By Mrugesh

				//}
				//else
				//{
				//throw new MonthException();
				//}

				//To pass Voucher Details on Approval
				ResultObject resultObjectByDcps = null;
				if (givenBillNo != null && givenBillNo.length() > 0 && appFlag == 1 && voucherNo != 0 && voucherdate != null && voucherdate.length() > 0)
				{
					//logger.error("Calling DCPS Method to Update Voucher Details----saveVoucherDtlsForContri");

					logger.info("hii here **************for saveVoucherDtlsForContri");
					Map inputMap = objectArgs;
					inputMap.put("cmbMonth", monthGiven);
					inputMap.put("cmbYear", yearGiven);
					//	  inputMap.put("cmbDDOCode", value);
					inputMap.put("cmbBillGroup", givenBillNo);
					inputMap.put("txtVoucherNo", voucherNo);
					inputMap.put("txtVoucherDt", voucherdate);
					inputMap.put("FreezeFlag", appFlag);
					//Commeted For HTE testing
					resultObjectByDcps = (ResultObject) serv.executeService("saveVoucherDtlsForContri", inputMap);

					//logger.error("Successfully Returned From saveVoucherDtlsForContri");
					 
				}

				objectArgs.put("msg", "Records Have Been Approved.");
				logger.info("Abhi don here " + objectArgs.get("msg"));
				if (resultObjectByDcps != null && resultObjectByDcps.getResultCode() == 1)
				{
					logger.info("hii here **************for  resultObjectByDcps.getResultCode()***"+resultObjectByDcps.getResultCode());
					if (princiRecorerVoList != null && !princiRecorerVoList.isEmpty())
					{
						for (HrLoanEmpPrinRecoverDtls princiRecorer : princiRecorerVoList)
						{
							logger.info("hii here **************for 1");
							hrLoanPrinRecoverDao.update(princiRecorer);
						}
					}
					if (paybillHeadMappingList != null && !paybillHeadMappingList.isEmpty())
					{
						for (PaybillHeadMpg princiRecorer : paybillHeadMappingList)
						{
							logger.info("hii here **************for 2");
							paybillHeadMpgDAOImpl.update(princiRecorer);
							princiRecorer=null;
						}
					}
					if (hrLoanEmpList != null && !hrLoanEmpList.isEmpty())
					{
						for (HrLoanEmpDtls princiRecorer : hrLoanEmpList)
						{
							logger.info("hii here **************for 3");
							hrLoanEmpDao.update(princiRecorer);
							princiRecorer=null;
						}
					}
					if (paySlipVOList != null && !paySlipVOList.isEmpty())
					{
						for (HrPayPayslip princiRecorer : paySlipVOList)
						{
							logger.info("hii here **************for 4");
							
							paySlipDAO.create(princiRecorer);
							princiRecorer=null;
						}
					}
					if (intRecoverVOList != null && !intRecoverVOList.isEmpty())
					{
						for (HrLoanEmpIntRecoverDtls princiRecorer : intRecoverVOList)
						{
							logger.info("hii here **************for 5");
							hrLoanIntRecoverDao.update(princiRecorer);
							princiRecorer=null;
						}
					}
				}
				else
					throw new Exception("DCPS Service Throws Exception");

				logger.info("in service before executing query");
				paySlipDAO.updateHrPaybillTrnLog(approvalStartTime, new Date(), paybillId);
				logger.info("in service after executing query***viewname*******"+viewname);
			

				resultObject.setResultValue(objectArgs);
				resultObject.setViewName(viewname);
				princiRecorerVoList = null;
				paybillHeadMappingList = null;
				hrLoanEmpList = null;
				paySlipVOList = null;
				intRecoverVOList = null;
			}
			princiRecorerVoList = null;
			paybillHeadMappingList = null;
			hrLoanEmpList = null;
			cmnLanguageMstDaoImpl = null;
			paySlipVOList = null;
			intRecoverVOList = null;
			hrEisProofDtlList = null;
			hrEisProofDtlsDAO = null;
			hrLoanIntRecoverDao = null;
			hrLoanPrinRecoverDao = null;
			edpList = null;
			empGpfDtlsDAO = null;
			empLoanIntList = null;
		}
		catch (DuplicateRecordException e)
		{
			objectArgs.put("msg", "Records For this month already exist.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error("Error is: " + e.getMessage());
			logger.error(e);

		}
		catch (MonthException e)
		{
			objectArgs.put("msg", "There is No Record For This Month in Paybill.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error("Error is: " + e.getMessage());
			logger.error(e);

		}
		catch (NullPointerException ne)
		{
			//logger.error("Null Pointer Exception Ocuures...approvePayBill");
			logger.error("Error is:********IN Payslip  ******* " + ne.getMessage());
			ne.printStackTrace();
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(ne);
		}
		catch (PropertyValueException pe)
		{
			//logger.error("PropertyValueException Ocuures...approvePayBill");
			pe.printStackTrace();
			logger.error("Error is: " + pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(pe);
		}
		catch (Exception e)
		{
			//logger.error("Exception Ocuures...approvePayBill");
			e.printStackTrace();
			if (e.getMessage().equalsIgnoreCase("DCPS Service Throws Exception"))
				objectArgs.put("msg", "Voucher details not saved");
			else
				objectArgs.put("msg", "There is Some Problem while inserting into database.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(e);
		}
		return resultObject;
	}

	public ResultObject findEmpNamePayslip(Map objectArgs)
	{
		logger.info("IN findEmpNamePayslip Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		//LoginDetails objLoginDetails =(LoginDetails)session.getAttribute("loginDetails");
		//long langId = objLoginDetails.getLangId();

		String empName = "";

		StringBuffer propertyList = new StringBuffer();
		try
		{
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			List empNames = new ArrayList();
			List ddoNames = new ArrayList();
			String isPayBillGenChk = "";
			if (voToService.get("paybillGenChk") != null && !voToService.get("paybillGenChk").toString().equals(""))
			{
				isPayBillGenChk = voToService.get("paybillGenChk").toString().toLowerCase();
			}
			if (voToService.get("emp_first_name") != null && !voToService.get("emp_first_name").toString().equals(""))
			{

				empName = voToService.get("emp_first_name").toString().toLowerCase();
				/*PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				empNames = payBillDAO.findEmpNamePayslip(empName, langId);*/
			}

			/*
			 * 
			 * 
			 */

			String strGrade = "";
			long gradeId = 0;

			if (voToService.get("gradeId") != null && !voToService.get("gradeId").toString().equals("Select"))
			{
				strGrade = voToService.get("gradeId").toString();
				if (strGrade != null && !"Select".equals(strGrade))
				{
					gradeId = Long.parseLong(strGrade);
				}
			}

			/*
			 * 
			 * 
			 */

			String deptId = "";
			long departmentId = 0;
			if (voToService.get("deptId") != null && !voToService.get("deptId").toString().equals(""))
			{

				deptId = voToService.get("deptId").toString();

				if (!deptId.equals(""))
				{
					departmentId = Long.parseLong(deptId);
				}

				//empNames

			}

			String givenMonth = "";

			String givenYear = "";

			if (voToService.get("givenMonth") != null && !voToService.get("givenMonth").toString().equals(""))
				givenMonth = voToService.get("givenMonth").toString();
			if (voToService.get("givenYear") != null && !voToService.get("givenYear").toString().equals(""))
				givenYear = voToService.get("givenYear").toString();

			/*Calendar c = Calendar.getInstance();
			if(givenMonth.equals("")||givenMonth.equals("-1"))
			givenMonth=String.valueOf(c.get(Calendar.MONTH));
			if(givenYear.equals("")||givenYear.equals("-1"))
			givenYear=String.valueOf(c.get(Calendar.YEAR));*/

			EmpInfoDAOImpl hrEisEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());

			empNames = hrEisEmpDao.getHrEmpFromDeptId(departmentId, empName, givenMonth, givenYear, gradeId, isPayBillGenChk);

			for (Iterator iterDist = empNames.iterator(); iterDist.hasNext();)
			{
				HrEisEmpMst empMstObj = (HrEisEmpMst) iterDist.next();
				long empId = empMstObj.getEmpId();
				String empFirstNameFound = empMstObj.getOrgEmpMst().getEmpFname();
				String empMiddleNameFound = empMstObj.getOrgEmpMst().getEmpMname();
				String empLastNameFound = empMstObj.getOrgEmpMst().getEmpLname();
				logger.info("Emp Name in service iterator part... " + empFirstNameFound);
				propertyList.append("<emp-mapping>");
				propertyList.append("<emp-Id>").append(empId).append("</emp-Id>");
				propertyList.append("<emp-first_name>").append("<![CDATA[").append(empFirstNameFound).append("]]>").append("</emp-first_name>");
				propertyList.append("<emp-middle_name>").append("<![CDATA[").append(empMiddleNameFound).append("]]>").append("</emp-middle_name>");
				propertyList.append("<emp-last_name>").append("<![CDATA[").append(empLastNameFound).append("]]>").append("</emp-last_name>");
				propertyList.append("</emp-mapping>");
			}

			ddoNames = hrEisEmpDao.getDDOListFromDeptId(departmentId, langId);

			for (Iterator iterDdo = ddoNames.iterator(); iterDdo.hasNext();)
			{
				Object[] ddoMstObj = (Object[]) iterDdo.next();
				long ddoId = Long.parseLong((ddoMstObj[0] != null ? ddoMstObj[0].toString() : "0"));
				String ddoPrefixFound = ddoMstObj[1] != null ? ddoMstObj[1].toString() : "";
				String ddoFirstNameFound = ddoMstObj[2] != null ? ddoMstObj[2].toString() : "";
				String ddoMiddleNameFound = ddoMstObj[3] != null ? ddoMstObj[3].toString() : "";
				String ddoLastNameFound = ddoMstObj[4] != null ? ddoMstObj[4].toString() : "";

				propertyList.append("<ddo-mapping>");
				propertyList.append("<ddo-Id>").append(ddoId).append("</ddo-Id>");
				propertyList.append("<ddo-prefix>").append("<![CDATA[").append(ddoPrefixFound).append("]]>").append("</ddo-prefix>");
				propertyList.append("<ddo-first_name>").append("<![CDATA[").append(ddoFirstNameFound).append("]]>").append("</ddo-first_name>");
				propertyList.append("<ddo-middle_name>").append("<![CDATA[").append(ddoMiddleNameFound).append("]]>").append("</ddo-middle_name>");
				propertyList.append("<ddo-last_name>").append("<![CDATA[").append(ddoLastNameFound).append("]]>").append("</ddo-last_name>");
				propertyList.append("</ddo-mapping>");
			}

			//}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			result.put("ajaxKey", stateNameIdStr);
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.info("Exception occures...");
			logger.error("Error is: " + e.getMessage());
			logger.info("Exception Occures.");
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}

	public ResultObject getPayslipParameterPage(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{

			List<CmnLocationMst> newCmnList = new ArrayList();
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, 9);
			c.set(Calendar.YEAR, 2007);
			logger.info("Maximum days in the" + c.getActualMaximum(Calendar.DATE));
			//Added by Mrugesh
			//Map loginMap = (Map) objectArgs.get("baseLoginMap");	
			//long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			//Ended	

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());

			//for getting department list

			//added by Ankit Bhatt.
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			//ended by Ankit Bhatt

			/*List cmnLocVoList = cmnLocDao.getLocationList(langId);

			if (cmnLocVoList != null && cmnLocVoList.size() != 0)
			{
				logger.info("the loc list size is " + cmnLocVoList.size());

				//Collections.sort(cmnLocVoList);
				newCmnList = cmnLocVoList;

				for(Iterator it1=newCmnList.iterator();it1.hasNext();)
				 {
					 CmnLocationMst cmnLocVo = (CmnLocationMst)it1.next();	            
				  logger.info("To Avoid LazyInitialization in getPayslipParameterPage" + cmnLocVo.getLocName());                    
				 }
			}*/

			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());

			/*PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List empList = payBillDAO.getAllEmpData();*/

			//List empList = paySlipDAO.getAllEmpData();
			/*HrEisEmpMst hrEisEmpMst = null;

			if (empList != null && empList.size() != 0)
			{
				for (Iterator it = empList.iterator(); it.hasNext();)
				{
					hrEisEmpMst = (HrEisEmpMst) it.next();
					logger.info("To Avoid LazyInitialization in getPayslipParameterPage" + hrEisEmpMst.getOrgEmpMst());
				}
			}*/

			/*EmpInfoDAOImpl empInfoDAO = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());		
			List empList = empInfoDAO.getEmpNamesFromOtherDtls();*/

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
			//added by manish

			String ddo_code = null;
			//List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInPost(postId);
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
			if (ddoCodeList != null)
				logger.info("After query execution for DDO Code " + ddoCodeList.size());

			OrgDdoMst ddoMst = null;
			if (ddoCodeList != null && ddoCodeList.size() > 0)
			{
				ddoMst = ddoCodeList.get(0);
			}

			if (ddoMst != null)
			{
				ddo_code = ddoMst.getDdoCode();
			}
			logger.info("DDO Code is " + ddo_code);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long finYearId = Long.parseLong(resourceBundle.getString("finYearId"));
			ArrayList billList = new ArrayList();
			List billNoList = new ArrayList();
			if (ddo_code != null)
			{
				billNoList = payBillDAO.getBillNo(finYearId, locId, ddo_code);
			}
			for (Iterator itr = billNoList.iterator(); itr.hasNext();)
			{
				HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
				Object[] row = (Object[]) itr.next();
				String billNo = row[1].toString(); //dcpsDdoBillDescription = Bill Number of GAD
				String billHeadId = row[0].toString(); ////dcpsDdoBillGroupId = Bill Value (PK)
				billNoCustomVO.setBillId(billNo);
				billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
				billList.add(billNoCustomVO);
			}
			//ended

			objectArgs.put("loginId", userId);
			objectArgs.put("locId", locId);
			objectArgs.put("billList", billList);
			objectArgs.put("monthList", monthList);
			objectArgs.put("cmnLocVoList", newCmnList);
			objectArgs.put("yearList", yearList);
			/*objectArgs.put("empList", empList);
			logger.info("Emp List is " + empList.size());*/
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("paySlipPara");
		}
		catch (Exception e)
		{
			logger.error("Error is: " + e.getMessage());
		}
		return resultObject;

	}

	public ResultObject generatePayslip(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("start time " + System.currentTimeMillis());
		logger.info("inside generatePayslip method of GeneratePayslipService class ");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			int month = Integer.parseInt(objectArgs.get("month").toString());
			int year = Integer.parseInt(objectArgs.get("year").toString());
			long selectedBill = Long.parseLong(objectArgs.get("billNo").toString());
			long dsgnIdFromBillNo = 0;
			if (objectArgs.get("dsgnId") != null && !objectArgs.get("dsgnId").equals("") && !objectArgs.get("dsgnId").equals("-1"))
			{
				dsgnIdFromBillNo = Long.parseLong(objectArgs.get("dsgnId").toString());
				logger.info("In generatePayslip dsgnIdFromBillNo is ***********" + dsgnIdFromBillNo);
			}

			long employeeId = 0;
			if (objectArgs.get("employeeid") != null && !objectArgs.get("employeeid").equals("") && !objectArgs.get("employeeid").equals("-1"))
			{
				employeeId = Long.parseLong(objectArgs.get("employeeid").toString());
				logger.info("In generatePayslip employeeId is ***********" + employeeId);
			}

			logger.info("monht: year : bill No in generatePayslip service Is" + month + ": " + year + ": " + selectedBill);

			PaySlipDAOImpl payslipDAO = new PaySlipDAOImpl(HrPayPayslip.class, serv.getSessionFactory());
			EmpCompMpgDAOImpl empCompMpgDAOImpl = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class, serv.getSessionFactory());
			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

			Map payslipCompoMap = new HashMap();
			List mappedList = new ArrayList();
			EdpDtlsVO edpDtlsVO = new EdpDtlsVO();
			long empID = 0;
			String sevarthCode = "";
			String edpCode = "";
			int size = 0;
			double allowTotal = 0;
			double deducTotal = 0;
			double ngTotal = 0;
			double amount = 0;
			String installment = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			List<HrPayPaybill> billDataList = null;
			Map<Long, HrPayPayslip> payslipDataMap = null;
			Map<Long, List> mappedComponentsMap = null;
			PayslipBasicDetailsVO payslipBasicDetailsVO = null;

			String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

			//For voucher no and Voucherdate
			List vouchernoList = payslipDAO.getVoucheNoandVoucherDate(locId, selectedBill, month, year);
			if (vouchernoList != null && !vouchernoList.isEmpty())
			{
				Object[] data = (Object[]) vouchernoList.get(0);
				long voucherNumber = Long.valueOf(data[0].toString());
				Date voucherDate = (Date) data[1];
				objectArgs.put("voucherNumber", voucherNumber);
				objectArgs.put("voucherDate", sdf.format(voucherDate));
				//data = null;
				data = (Object[])null;
			}
			//Ended

			if (dsgnIdFromBillNo > 0 || employeeId > 0)
			{
				billDataList = payslipDAO.getBillData(locId, selectedBill, month, year, dsgnIdFromBillNo, employeeId);
			}
			else
			{
				billDataList = payBillDAOImpl.getBillData(locId, selectedBill, month, year);
			}

			Map allEdpCodes = payBillDAOImpl.getAllEdpCode(locId);
			Map allowEdpMap = (Map) allEdpCodes.get("allowEdpMap");
			Map deducEdpMap = (Map) allEdpCodes.get("deducEdpMap");
			Map loanEdpMap = (Map) allEdpCodes.get("loanEdpMap");
			Map advanceEdpMap = (Map) allEdpCodes.get("advanceEdpMap");
			logger.info("allowEdpMap " + allowEdpMap + " deducEdpMap " + deducEdpMap + "loan Edp Map " + loanEdpMap + "advance Edp Map " + advanceEdpMap);
			Map paylsipColEdpMpg = (Map) allEdpCodes.get("paylsipColEdpMpg");
			Map paylsipColEdpDeductionMpg = (Map) allEdpCodes.get("paylsipColEdpDeductionMpg");
			Map edpShortNameEdpAllowancesMpg = (Map) allEdpCodes.get("edpShortNameEdpAllowancesMpg");
			Map edpShortNameEdpDeductionsMpg = (Map) allEdpCodes.get("edpShortNameEdpDeductionsMpg");
			Map typeIdPayslipCol = (Map) allEdpCodes.get("typeIdPayslipCol");
			Map paylsipColEdpLoan = (Map) allEdpCodes.get("paylsipColEdpLoan");
			Map edpShortNameEdpLoanMpg = (Map) allEdpCodes.get("edpShortNameEdpLoanMpg");


			Map empMap = null;
			List allowanceList = null;
			List deductionList = null;
			List nonGovernmetList = null;
			HrPayPayslip hrPayPayslip = null;

			String empId = "";
			String paybillIdStr = "";
			int billSize = 0;
			if (billDataList != null && !billDataList.isEmpty())
			{
				billSize = billDataList.size();
				logger.info("size of bill list is" + billDataList.size());
			}

			for (int j = 0; j < billSize; j++)
			{
				empId = empId + billDataList.get(j).getHrEisEmpMst().getEmpId() + ",";
				paybillIdStr = paybillIdStr + billDataList.get(j).getId() + ",";
			}

			if (!empId.equals(""))
				empId = empId.substring(0, empId.lastIndexOf(','));

			if (!paybillIdStr.equals(""))
				paybillIdStr = paybillIdStr.substring(0, paybillIdStr.lastIndexOf(','));

			mappedComponentsMap = empCompMpgDAOImpl.getDataChcked(selectedBill, month, year);
			logger.info("After the execution of getDataChcked");
			payslipDataMap = payBillDAOImpl.getPayslipData(selectedBill, month, year);
			logger.info("After the execution of getPayslipData");
			String office = payBillDAOImpl.getOffice(locId);
			logger.info("After the execution of getOffice");
			String salaryMonth = String.valueOf(monthName[month - 1]) + "-" + String.valueOf(year);

			Map<Long, Map<String, Object>> allowanceMap = payBillDAOImpl.getPayslipData(selectedBill, month, year, paylsipColEdpMpg);/// find by pandey
			Map<Long, Map<String, Object>> deductMap = payBillDAOImpl.getPayslipData(selectedBill, month, year, paylsipColEdpDeductionMpg);
			Map<Long, Map<String, Long>> nonGovListForEmp = payslipDAO.getNonGovDataFromPaybillId(paybillIdStr, locId);
			Map<Long, Object> basicDtlMap = payslipDAO.getBasicDetail(empId, month, year);



			Map<Long, Map<Long, Map<String, Object>>> loanDataMap = payBillDAOImpl.getPayslipDataForLoan(selectedBill, month, year, paylsipColEdpLoan);

			for (int k = 0; k < billSize; k++)
			{
				empMap = new HashMap();
				allowanceList = new ArrayList();
				deductionList = new ArrayList();
				nonGovernmetList = new ArrayList();
				allowTotal = 0;
				deducTotal = 0;
				ngTotal = 0;
				amount = 0;
				installment = "";

				if (billDataList.get(k).getHrEisEmpMst() != null)
				{
					empID = billDataList.get(k).getHrEisEmpMst().getEmpId();
					sevarthCode = billDataList.get(k).getHrEisEmpMst().getSevarthEmpCode();
					logger.info("emp id is " + empID);
					logger.info("sevarthCode id is " + sevarthCode);

					Class paySlipClass = null;
					if (payslipDataMap != null && payslipDataMap.containsKey(empID))
					{
						hrPayPayslip = payslipDataMap.get(empID);
						paySlipClass = hrPayPayslip.getClass();
					}
					logger.info("Payslip class is " + paySlipClass);

					if (paySlipClass != null)
					{
						//for basic
						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setAddDedFlag("A");
						amount = hrPayPayslip.getBasicPay();

						edpDtlsVO.setAmount(amount);
						edpDtlsVO.setDisplayName("BASIC");
						edpDtlsVO.setEdpCode("0101");
						edpDtlsVO.setExpRcpRec("EXP");
						allowanceList.add(edpDtlsVO);
						edpDtlsVO = null;
						allowTotal += amount;
						//ended

						List mappedComponents = mappedComponentsMap.get(empID);
						int mappedCompoSize = 0;

						if (mappedComponents != null && !mappedComponents.isEmpty())
							mappedCompoSize = mappedComponents.size();

						for (int i = 0; i < mappedCompoSize; i++)
						{
							Object[] dataObj = (Object[]) mappedComponents.get(i);
							long compoType = Long.valueOf(dataObj[0].toString());
							
							logger.info("Compo type id are as follows1: "+compoType);
							String[] compoIds = dataObj[1].toString().split(",");
							int compoIdSize = compoIds.length;
							logger.info("CompoIds are as follows 2: "+compoIds);				
							
							if (compoType == 2500134)
							{
								for (int l = 0; l < compoIdSize; l++)
								{
									long compoId = Long.valueOf(compoIds[l]);
									edpDtlsVO = new EdpDtlsVO();
									//modified by roshan
									logger.info("compoId*****roshan"+compoId);
									if (compoId!=0){
										logger.info("Inside the if of compoId"+compoId);
									logger.info("value after converting into string"+String.valueOf(compoId).toString());
										edpCode = allowEdpMap.get(String.valueOf(compoId)).toString();
										logger.info("edp code is   roshan ******"+edpCode);
										edpDtlsVO.setEdpCode(edpCode);
									
									}
									
									//ended:
									if (edpCode.equals("0101"))
									{
										amount = hrPayPayslip.getPerPay();
									}
									else
									{
										String colName = paylsipColEdpMpg.get(edpCode).toString();
										Map<String, Object> amountMap = allowanceMap.get(empID);
										amount = ((BigInteger) amountMap.get(colName)).doubleValue();
										amountMap = null;
									}
									edpDtlsVO.setAmount(amount);
									edpDtlsVO.setAddDedFlag("A");
									edpDtlsVO.setExpRcpRec("EXP");
									edpDtlsVO.setDisplayName(edpShortNameEdpAllowancesMpg.get(edpCode).toString());
									allowTotal += amount;
									allowanceList.add(edpDtlsVO);
									edpDtlsVO = null;
								}
							}							
							else if (compoType == 2500135)
							{
								for (int l = 0; l < compoIdSize; l++)
								{
									long compoId = Long.valueOf(compoIds[l]);
									logger.info("compoId*****roshan for deduction = "+compoId);
									edpDtlsVO = new EdpDtlsVO();
									edpCode = deducEdpMap.get(String.valueOf(compoId)).toString();
									edpDtlsVO.setEdpCode(edpCode);

									String colName = paylsipColEdpDeductionMpg.get(edpCode).toString();
									Map<String, Object> amountMap = deductMap.get(empID);
									amount = ((BigInteger) amountMap.get(colName)).doubleValue();

									edpDtlsVO.setAmount(amount);
									edpDtlsVO.setAddDedFlag("D");
									edpDtlsVO.setExpRcpRec("RCP");
									edpDtlsVO.setDisplayName(edpShortNameEdpDeductionsMpg.get(compoId+"").toString());
									edpDtlsVO.setInstallMent("");
									deducTotal += amount;
									deductionList.add(edpDtlsVO);
									edpDtlsVO = null;
									amountMap = null;
								}
							}
							else if (compoType == 2500137)
							{
								for (int l = 0; l < compoIdSize; l++)
								{
									long compoId = Long.valueOf(compoIds[l]);
									edpDtlsVO = new EdpDtlsVO();
									if (advanceEdpMap.get(String.valueOf(compoId)) != null)
										edpCode = advanceEdpMap.get(String.valueOf(compoId)).toString();
									else
										edpCode = loanEdpMap.get(String.valueOf(compoId)).toString();

									edpDtlsVO.setEdpCode(edpCode);
									String colName = typeIdPayslipCol.get(String.valueOf(compoId)).toString();
									Map<Long, Map<String, Object>> dapaFromMap = (Map) loanDataMap.get(empID);

									if (dapaFromMap != null && dapaFromMap.containsKey(compoId))
									{
										Map<String, Object> loanMap = (Map) dapaFromMap.get(compoId);
										amount = loanMap != null ? ((BigInteger) loanMap.get(colName)).doubleValue() : 0;
										installment = loanMap != null ? loanMap.get("INSTALLMENT").toString() : "";
										logger.info("installment "+installment);
										edpDtlsVO.setAmount(amount);
										edpDtlsVO.setInstallMent(installment);
										edpDtlsVO.setDisplayName(edpShortNameEdpLoanMpg.get(compoId+"").toString());
										edpDtlsVO.setAddDedFlag("D");
										edpDtlsVO.setExpRcpRec("RCP");
										deducTotal += amount;
										deductionList.add(edpDtlsVO);
										logger.info("third success ");
										mappedList.add(edpDtlsVO);
										loanMap = null;
									}
									else
									{
										edpDtlsVO.setAmount(0);
										edpDtlsVO.setInstallMent("");
										edpDtlsVO.setDisplayName(edpShortNameEdpLoanMpg.get(compoId+"").toString());
										deducTotal += 0;
										edpDtlsVO.setAddDedFlag("D");
										edpDtlsVO.setExpRcpRec("RCP");
										deductionList.add(edpDtlsVO);
										logger.info("third success ");
										mappedList.add(edpDtlsVO);
									}
									edpDtlsVO = null;
									dapaFromMap = null;
								}
							}
						}

						logger.info("Size of the List fetched for NG is" + nonGovListForEmp.size());
						if (nonGovListForEmp != null && nonGovListForEmp.size() > 0)
						{
							Map<String, Long> amountMap = (Map) nonGovListForEmp.get(empID);
							if (amountMap != null)
							{
								Iterator it = amountMap.entrySet().iterator();
								while (it.hasNext())
								{
									Map.Entry pairs = (Map.Entry) it.next();

									edpDtlsVO = new EdpDtlsVO();
									edpDtlsVO.setAddDedFlag("NG");
									edpDtlsVO.setExpRcpRec("REC");
									String displayName = pairs.getKey().toString();
									edpDtlsVO.setDisplayName(displayName);
									long ngAmount = Long.valueOf(pairs.getValue().toString());
									edpDtlsVO.setAmount(ngAmount);
									ngTotal += ngAmount;
									nonGovernmetList.add(edpDtlsVO);
									pairs = null;
									edpDtlsVO = null;
								}
							}
							amountMap = null;
						}
						logger.info("Total Non Gov For Employee is" + ngTotal);
						paySlipClass = null;
						mappedComponents = null;
					}

					//Basic Details
					Object[] data = (Object[]) basicDtlMap.get(empID);
					payslipBasicDetailsVO = new PayslipBasicDetailsVO();
					String bankAccNo = data[0] != null ? data[0].toString() : "";
					String employeeName = data[1] != null ? data[1].toString() : "";
					String designation = data[2] !=null ? data[2].toString() : "";
					String pfSeries = data[3] != null ? data[3].toString() : "";
					String gpfAcNo = data[4] != null ? data[4].toString() : "";
					String payBand = data[5] != null ? data[5].toString() : "";
					String otherCurrBasic = data[6] != null ? data[6].toString() : "0";
					String gradPa = data[7] != null ? data[7].toString() : "0";
					
					payslipBasicDetailsVO.setBankAccNo(bankAccNo);
					payslipBasicDetailsVO.setEmployeeName(employeeName);
					payslipBasicDetailsVO.setSalaryMonth(salaryMonth);
					payslipBasicDetailsVO.setDesignation(designation);

					if ("DCPS".equals(pfSeries))
						payslipBasicDetailsVO.setGpfAccNo(gpfAcNo);
					else
						payslipBasicDetailsVO.setGpfAccNo(pfSeries + "/" + gpfAcNo);

					payslipBasicDetailsVO.setOffice(office);
					
					String payInPaybandPlusGp = "";
					long otherCurrentBasic = Long.valueOf(new Double((Double.parseDouble(otherCurrBasic))).longValue());
					long gradePay = Long.valueOf(gradPa);
					long tempBasicWithoutGP = otherCurrentBasic - gradePay;
					payInPaybandPlusGp = "( " + tempBasicWithoutGP + "+" + gradePay + ")";
					payslipBasicDetailsVO.setPayBand(payBand);
					payslipBasicDetailsVO.setPayInPaybandPlusGp(payInPaybandPlusGp);
					//data = null;
					data = (Object[])null;

					empMap.put("allowanceList", allowanceList);
					empMap.put("deductionList", deductionList);
					empMap.put("nonGovernmetList", nonGovernmetList);
					empMap.put("deducTotal", Math.round(deducTotal));
					empMap.put("allowTotal", Math.round(allowTotal));
					empMap.put("ngTotal", Math.round(ngTotal));
					empMap.put("payslipBasicVO", payslipBasicDetailsVO);
					double netpay_amount = allowTotal - deducTotal - ngTotal;
					empMap.put("netPay", Math.round(netpay_amount));
					String inWords = "( " + ConvertNumbersToWord.convert(Math.round(netpay_amount)) + " Only )";
					empMap.put("netPayInWords", inWords);
					payslipCompoMap.put(empID, empMap);
					payslipBasicDetailsVO = null;

				}
				empMap = null;
				allowanceList = null;
				deductionList = null;
				nonGovernmetList = null;

			}

			if (payslipCompoMap != null)
				size = payslipCompoMap.size();

			objectArgs.put("payslipCompoMap", payslipCompoMap);
			objectArgs.put("size", size);
			resultObject.setResultValue(objectArgs);
			serv = null;
			loginDetailsMap = null;
			payslipDAO = null;
			empCompMpgDAOImpl = null;
			payBillDAOImpl = null;
			payslipCompoMap = null;
			mappedList = null;
			edpDtlsVO = null;
			sdf = null;
			payslipDataMap = null;
			mappedComponentsMap = null;
			vouchernoList = null;
			allEdpCodes = null;
			allowEdpMap = null;
			deducEdpMap = null;
			loanEdpMap = null;
			advanceEdpMap = null;
			paylsipColEdpMpg = null;
			paylsipColEdpDeductionMpg = null;
			edpShortNameEdpAllowancesMpg = null;
			edpShortNameEdpDeductionsMpg = null;
			typeIdPayslipCol = null;
			hrPayPayslip = null;
			allowanceMap = null;
			deductMap = null;
			nonGovListForEmp = null;
			basicDtlMap = null;
			edpShortNameEdpLoanMpg = null;
			logger.info("end time " + System.currentTimeMillis());

			resultObject.setViewName("MahaPaySlip");
		}
		catch (MonthException e)
		{
			objectArgs.put("msg", "No Record Found in Payslip.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");
			logger.error("Error is: " + e);
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(errorMap);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");
			logger.error("Error is: " + e);
			e.printStackTrace();
		}
		catch (Exception e)
		{
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(errorMap);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");
			logger.error("Error is: " + e);
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * Author :- Urvin Shah. Function:- This method will return Next Increment
	 * Amount.
	 * 
	 * @param hrEisScaleMst
	 * @param basicAmt
	 * @return
	 */

	public int getIncrementAmount(HrEisScaleMst hrEisScaleMst, long basicAmt)
	{
		int incrementAmount = 0;
		if (basicAmt >= hrEisScaleMst.getScaleStartAmt() && basicAmt < hrEisScaleMst.getScaleEndAmt())
			incrementAmount = (int) hrEisScaleMst.getScaleIncrAmt();
		else
			incrementAmount = (int) hrEisScaleMst.getScaleHigherIncrAmt();
		return incrementAmount;
	}

	/**
	 * @Author : Urvin Shah
	 * @Date : 07/04/2008 Function : This method will make the Last Date String
	 *       of given Date's Month.
	 * @param currDate
	 * @return
	 */

	public String getMaxDateOfMonth(Date currDate)
	{
		String strMaxDateOfCurrMonth = "";
		Calendar calCurrDate = Calendar.getInstance();
		calCurrDate.set(Calendar.YEAR, currDate.getYear() + 1900);
		calCurrDate.set(Calendar.MONTH, currDate.getMonth());
		calCurrDate.set(Calendar.DAY_OF_MONTH, currDate.getDate());
		strMaxDateOfCurrMonth = String.valueOf(calCurrDate.getActualMaximum(Calendar.DAY_OF_MONTH)) + "/" + (calCurrDate.get(Calendar.MONTH) + 1) + "/" + (calCurrDate.get(Calendar.YEAR));
		return strMaxDateOfCurrMonth;
	}

	/**
	 * @Author : Urvin Shah
	 * @Date : 07/04/2008 Function : This method will make the First Date String
	 *       of given Date's Month.
	 * @param currDate
	 * @return
	 */

	public String getMinDateOfMonth(Date currDate)
	{
		String strMaxDateOfCurrMonth = "";
		Calendar calCurrDate = Calendar.getInstance();
		calCurrDate.set(Calendar.YEAR, currDate.getYear() + 1900);
		calCurrDate.set(Calendar.MONTH, currDate.getMonth());
		calCurrDate.set(Calendar.DAY_OF_MONTH, currDate.getDate());
		strMaxDateOfCurrMonth = String.valueOf(calCurrDate.getActualMinimum(Calendar.DAY_OF_MONTH)) + "/" + (calCurrDate.get(Calendar.MONTH) + 1) + "/" + (calCurrDate.get(Calendar.YEAR));
		return strMaxDateOfCurrMonth;
	}

	/**
	 * Author:- Urvin shah. Date:- 09/04/2008. Function :- This Function
	 * calculate the current Financial Year LIC Amount.
	 * 
	 * @param currFinLicDtl
	 * @return currFinYearLicAmt
	 */

	public int getCurrFinYearLicAmt(List currFinLicDtl, Date currDate)
	{
		int currFinYearLicAmt = 0;
		HrPayNonGovDeduction hrPayNonGovDeduction = new HrPayNonGovDeduction();
		Date dtLicStartDate;
		Date dtLicEndDate;
		int fromMonth = 0;
		int toMonth = 0;
		int totalMonth = 0;
		Date currFinStartDate = new Date();
		logger.info("Date :-" + currDate);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, currDate.getMonth());
		currFinStartDate.setDate(1);
		logger.info("Fincancial Date:-" + currFinStartDate);
		currFinStartDate.setMonth(3);
		if ((currDate.getMonth() + 1) <= 3)
		{
			currFinStartDate.setYear(currDate.getYear() - 1);
		}
		else
		{
			currFinStartDate.setYear(currDate.getYear());
		}
		logger.info("Financial Year Date is:-" + currFinStartDate);
		for (int i = 0; i < currFinLicDtl.size(); i++)
		{
			hrPayNonGovDeduction = (HrPayNonGovDeduction) currFinLicDtl.get(i);
			dtLicStartDate = hrPayNonGovDeduction.getNonGovDeducEfftStartDt();
			dtLicEndDate = hrPayNonGovDeduction.getNonGovDeducEfftEndDt();
			fromMonth = 0;
			toMonth = 0;
			logger.info("LIC Start Date is:-" + dtLicStartDate);
			logger.info("LIC End Date is:-" + dtLicStartDate);
			logger.info("Current Date is:-" + currDate);
			logger.info("Financial Year Date is:-" + currFinStartDate);

			if (dtLicEndDate.before(currFinStartDate) || dtLicStartDate.after(currDate))
			{
				totalMonth = 0;
			}
			else
			{
				if (dtLicStartDate.before(currFinStartDate)) //Deduction start date is b4 Financial Year 
					fromMonth = 4;

				else
					fromMonth = dtLicStartDate.getMonth() + 1;

				if (dtLicEndDate.after(currDate)) //Deduction end date is b4 Current Date			
					toMonth = currDate.getMonth() + 1;
				else
					toMonth = dtLicEndDate.getMonth() + 1;
				logger.info("The TOMonth:-" + toMonth);
				logger.info("The FromMonth:-" + fromMonth);
				logger.info("The CurrentMonth:-" + currDate.getMonth() + 1);
				if (toMonth < 4 && fromMonth > 3)
				{
					toMonth += 12;
				}
				totalMonth = toMonth - fromMonth + 1;
			}
			currFinYearLicAmt = (int) (totalMonth * hrPayNonGovDeduction.getNonGovDeducAmount());
			logger.info("Month diff for LIC is " + totalMonth + " Amount is " + currFinYearLicAmt); //Month calculation for LIC History record.
		}
		return currFinYearLicAmt;
	}

	//Added by Abhilash

	public ResultObject getDsgnFromBill(Map objectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) session.getAttribute("loginDetailsMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			int month = 0;
			int year = 0;
			if (request.getParameter("payslipMonth") != null)
				month = Integer.parseInt(request.getParameter("payslipMonth").toString());
			logger.info("In getDsgnFromBill the selected month  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + month);

			if (request.getParameter("payslipYear") != null)
				year = Integer.parseInt(request.getParameter("payslipYear").toString());
			logger.info("In getDsgnFromBill the selected year  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + year);

			String billNo = "";
			if (request.getParameter("billNo") != null && !request.getParameter("billNo").toString().equals(""))
				billNo = request.getParameter("billNo").toString();
			logger.info("In getDsgnFromBill the bill no is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + billNo);

			PaySlipDAOImpl payslipDAO = new PaySlipDAOImpl(HrPayPayslip.class, serv.getSessionFactory());
			//commonPayslipDAOImpl billclassmpgdao = new commonPayslipDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
			//List classList = (List)billclassmpgdao.getClassFromBillNo(month,year,billNo);
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			logger.info("In getDsgnFromBill the locId  is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + locId);

			List dsgnList = (List) payslipDAO.getDsgnsFromBillNo(month, year, billNo, locId);
			StringBuffer propertyList = new StringBuffer();
			Object[] row;
			String dsgnName = "";
			String empName = "";
			long dsgnId = 0;
			long empId = 0;
			logger.info("In getDsgnFromBill dsgnList.size is " + dsgnList.size());

			if (dsgnList != null && dsgnList.size() > 0)
			{
				Map desigMap = new HashMap();
				for (Iterator itr = dsgnList.iterator(); itr.hasNext();)
				{
					row = (Object[]) itr.next();

					if (row[0] != null)
					{
						logger.info("row[0].toString() " + row[0].toString());
						dsgnId = Long.parseLong(row[0].toString());
						logger.info("In getDsgnFromBill dsgnId is************* " + dsgnId);
					}
					if (row[1] != null || !row[1].toString().equals(""))
					{
						dsgnName = row[1].toString();
					}
					if (row[2] != null)
					{
						logger.info("row[2].toString() " + row[2].toString());
						empId = Long.parseLong(row[2].toString());
						logger.info("In getDsgnFromBill dsgnId is************* " + dsgnId);
					}
					if (row[3] != null || !row[3].toString().equals(""))
					{
						empName = row[3].toString();
					}
					logger.info("In getDsgnFromBill dsgnName is************" + dsgnName);
					propertyList.append("<class-mapping>");
					if(!desigMap.containsKey(dsgnId)){
						desigMap.put(dsgnId, dsgnName);
						propertyList.append("<class-Id>").append(dsgnId).append("</class-Id>");
						propertyList.append("<class-name>").append("<![CDATA[").append(dsgnName).append("]]>").append("</class-name>");
					}else{
						desigMap.put(dsgnId, dsgnName);
						propertyList.append("<class-Id>").append("").append("</class-Id>");
						propertyList.append("<class-name>").append("<![CDATA[").append("").append("]]>").append("</class-name>");
					}
					propertyList.append("<emp-Id>").append(empId).append("</emp-Id>");
					propertyList.append("<emp-name>").append("<![CDATA[").append(empName).append("]]>").append("</emp-name>");
					propertyList.append("</class-mapping>");
				}
				desigMap=null;
				
			}
			Map result = new HashMap();
			String classData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("The Ajax Key is:-" + classData);
			result.put("ajaxKey", classData);
			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.info("Exception occurs...");
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;

	}

	//Ended by Abhilash

	//By Amish to Approve Bill Offline
	public ResultObject approvePayBillThroughScheduler(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		LoginDetails loginDetails = (LoginDetails) objectArgs.get("baseLoginVO");
		String viewname = "";
		Session currSession = (Session) objectArgs.get("currentSession");
		try
		{
			int monthGiven = -1; //-1 is default for current month 
			int yearGiven = -1; //-1 is default for current year
			ResourceBundle rs = ResourceBundle.getBundle("resources.Payroll");
			long appFlag = Long.parseLong(rs.getString("approved"));
			long advEMI = 0;
			long emp_loan_id = 0;
			long prin_install_no = 0;
			long int_install_no = 0;
			long tot_prin_install = 0;
			long tot_int_install = 0;
			long prinLoanAmt = 0;
			long recoveredPrinLoanAmt = 0;
			double totLoan = 0;
			long loanEMI = 0;
			long intLoanAmt = 0;
			long recoveredIntLoanAmt = 0;
			int loanStatusFlag = Integer.parseInt(rs.getString("activate"));
			int maxDayInMonth = 0;
			HrPayGpfBalanceDtls hrGpfBalanceDtls = new HrPayGpfBalanceDtls();
			EmpGpfDtlsDAO empGpfDtlsDAO = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class, serv.getSessionFactory());
			int incrementAmt = 0; // Next Increment Amount.

			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			LoanIntRecoverDAOImpl hrLoanIntRecoverDao = new LoanIntRecoverDAOImpl(HrLoanEmpIntRecoverDtls.class, serv.getSessionFactory());
			LoanPrinRecoverDAOImpl hrLoanPrinRecoverDao = new LoanPrinRecoverDAOImpl(HrLoanEmpPrinRecoverDtls.class, serv.getSessionFactory());
			HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
			EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			GenericDaoHibernateImpl hrEisProofDtlsDAO = new GenericDaoHibernateImpl(HrEisProofDtl.class);
			hrEisProofDtlsDAO.setSessionFactory(serv.getSessionFactory());
			GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
			PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class, serv.getSessionFactory());

			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			List<HrLoanEmpIntRecoverDtls> empLoanIntList = new ArrayList();
			HrLoanEmpPrinRecoverDtls princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
			List<HrLoanEmpPrinRecoverDtls> empLoanPrinList = new ArrayList();
			List<HrLoanEmpDtls> empLoanList = new ArrayList();
			List<HrPayEdpCompoMpg> edpList = (ArrayList) hrEdp.getAllData();
			HrPayEdpCompoMpg edpComp = new HrPayEdpCompoMpg();
			Date sysdate = new Date();
			HrLoanEmpIntRecoverDtls intRecoverVO = new HrLoanEmpIntRecoverDtls();
			List<HrLoanEmpPrinRecoverDtls> princiRecorerVoList = new ArrayList<HrLoanEmpPrinRecoverDtls>();
			//			List<HrLoanEmpDtls> hrLoanEmpList = new ArrayList<HrLoanEmpDtls>();
			List<HrLoanEmpIntRecoverDtls> intRecoverVOList = new ArrayList<HrLoanEmpIntRecoverDtls>();
			List<HrPayPayslip> paySlipVOList = new ArrayList<HrPayPayslip>();
			List<HrPayPaybill> hrPayPaybillList = new ArrayList<HrPayPaybill>();
			List<PaybillHeadMpg> paybillHeadMappingList = new ArrayList<PaybillHeadMpg>();

			HrPayPayslip paySlipVO = null;
			long quaterId;
			HrPayPaybill payBillVO = null;
			HrEisEmpMst hrEis = null;
			PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			List intList = payBillDAOImpl.getLoanIntList();
			PaybillHeadMpg paybillHeadMpg = null;
			HrEisQuaterTypeMst hrQuaterTypeMst = null;
			long compoType;
			String compoCode;
			long empIdForLoan;
			boolean isMultiRecovery;
			OtherDetailDAOImpl otherDAO;
			List otherDtlsList = new ArrayList();
			HrEisOtherDtls otherVO;
			HrEisQtrMst hrEssQuater;
			QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class, serv.getSessionFactory());
			List quaterList = new ArrayList();
			long userID = 0;
			MstDcpsBillGroup billGroup;
			String itAccNo = "";

			monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
			yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
			String givenBillNo = objectArgs.get("billNo") != null ? String.valueOf(objectArgs.get("billNo").toString()) : "";
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, yearGiven);
			cal.set(Calendar.MONTH, monthGiven - 1);
			java.util.Date approvalDate = cal.getTime();

			CmnLocationMst cmnLocationMst = loginDetails.getLocation();
			OrgUserMst orgUserMst = loginDetails.getUser();
			OrgPostMst orgPostMst = loginDetails.getLoggedInPost();

			int currMonth = 0;
			int currYear = 0;

			if (monthGiven != -1 && yearGiven != -1)
			{
				currMonth = monthGiven;
				currYear = yearGiven;
			}
			else
			{
				Date DBDate = DBUtility.getCurrentDateFromDB();
				currMonth = DBDate.getMonth() + 1;
				currYear = DBDate.getYear() + 1900;
			}
			List paySlipRecs = new ArrayList();
			paySlipRecs = paySlipDAO.checkPayslipForInner(givenBillNo, locId, currMonth, currYear);
			if (paySlipRecs == null || paySlipRecs.size() > 0) //if(paySlipRecs!=null && paySlipRecs.size()>0)
			{
				throw new DuplicateRecordException();
			}
			else
			{
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMst cmnDatabaseMst = new CmnDatabaseMst();
				cmnDatabaseMst.setDbId(dbId);
				cmnDatabaseMst.setActivateFlag(1);

				long paybillId = paySlipDAO.getPayBillGrpId(givenBillNo, locId, currMonth, currYear);

				HrPayPaybill objPayBillVO = new HrPayPaybill();
				Class pay = objPayBillVO.getClass();
				List<HrPayPaybill> lstPayPayBill = paySlipDAO.getHrPayPaybillFromHeadMpg(paybillId);
				double methodValue = 0;
				double methodIntValue = 0;
				String mthdName = "";
				Method payMthd;
				Method method;
				String setterString;
				long longAmt;
				long loanEmi;
				long amt;
				long new_no;
				HrLoanEmpDtls hrLoanEmp;
				long recoveredInstallments;
				long totalInstallments;
				HrPayPaybill payBillObj;
				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
				List<PaybillHeadMpg> paybillHeadMpgList = paybillHeadMpgDAOImpl.getPaybillHeadFromPaybillId(paybillId);
				paybillHeadMpg = new PaybillHeadMpg();
				paybillHeadMpg = (PaybillHeadMpg) paybillHeadMpgList.get(0);
				if (lstPayPayBill != null && lstPayPayBill.size() > 0)
				{
					for (Iterator it = lstPayPayBill.iterator(); it.hasNext();)//for(Iterator it = payBillData.iterator();it.hasNext();)
					{
						quaterId = 0;
						paySlipVO = new HrPayPayslip();
						payBillVO = (HrPayPaybill) it.next();
						hrEis = payBillVO.getHrEisEmpMst();
						if (hrEis == null)
							continue;
						if (paybillHeadMpg.getMonth() == currMonth)
						{
							for (int i = 0; i < edpList.size(); i++)
							{
								methodValue = 0;
								methodIntValue = 0;

								edpComp = new HrPayEdpCompoMpg();
								edpComp = edpList.get(i);

								String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
								compoType = edpComp.getCmnLookupId();
								compoCode = edpComp.getTypeId();
								hrEisEmpMst = payBillVO.getHrEisEmpMst();
								empIdForLoan = hrEisEmpMst.getOrgEmpMst().getEmpId();
								if (compoType == 2500136)//for advances
								{
									mthdName = "getAdv" + edpCode;
									payMthd = pay.getMethod(mthdName, null);
									methodValue = (Double) payMthd.invoke(payBillVO, null);
									if (methodValue != 0)
									{
										method = null;
										setterString = "";
										setterString = "set" + resourceBundle.getString("adv" + compoCode);
										method = paySlipVO.getClass().getMethod(setterString, long.class);
										longAmt = Long.parseLong(String.valueOf(Math.round(methodValue)));
										method.invoke(paySlipVO, longAmt);
										hrLoanEmp = new HrLoanEmpDtls();
										empLoanList = hrLoanEmpDao.getEmpLoanDetail(empIdForLoan, compoCode);//compocode is loan type id
										advEMI = 0;
										emp_loan_id = 0;
										prin_install_no = 0;
										int_install_no = 0;
										tot_prin_install = 0;
										tot_int_install = 0;
										for (int listCnt = 0; listCnt < empLoanList.size(); listCnt++)
										{
											if (empLoanList.size() > 0)
											{
												hrLoanEmp = empLoanList.get(listCnt);
												emp_loan_id = hrLoanEmp.getEmpLoanId();
												tot_prin_install = hrLoanEmp.getLoanPrinInstNo();
												tot_int_install = hrLoanEmp.getLoanIntInstNo();
												empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);

												Date loanDate = hrLoanEmp.getLoanDate();
												Calendar c = Calendar.getInstance();
												c.set(Calendar.YEAR, loanDate.getYear() + 1900);
												c.set(Calendar.MONTH, loanDate.getMonth());
												c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());
												c.add(Calendar.MONTH, (int) (tot_prin_install + tot_int_install) + 1);//1 is added to get the last month

												Date endDate = c.getTime();

												c.set(Calendar.YEAR, yearGiven);
												c.set(Calendar.MONTH, monthGiven - 1);

												maxDayInMonth = c.getActualMaximum(5);
												c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);

												Date currentDate = c.getTime(); //1st of given month
												if ((currentDate.compareTo(loanDate) == 1 && currentDate.compareTo(endDate) <= 0) || empLoanPrinList.get(0).getTotalRecoveredInst() != tot_prin_install)
												{
													if (empLoanPrinList.size() > 0)
													{
														princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
														princiRecorerVo = empLoanPrinList.get(0);
														prinLoanAmt = hrLoanEmp.getLoanPrinAmt();
														logger.info(hrLoanEmp.getHrLoanAdvMst().getLoanAdvId());
														recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt(); // Recovered Principal Amount.
														prin_install_no = princiRecorerVo.getTotalRecoveredInst();
														isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
														if (isMultiRecovery)
														{
															prin_install_no = prin_install_no + hrLoanEmp.getMulLoanInstRecvd();
															recoveredPrinLoanAmt = recoveredPrinLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
														}
														if ((isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt) || (!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt))//for principal Recovery amt as well as Installment Number.
														{
															advEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanPrinEmiAmt();

															if (!isMultiRecovery)
															{
																if (advEMI > (prinLoanAmt - recoveredPrinLoanAmt))
																{
																	advEMI = prinLoanAmt - recoveredPrinLoanAmt;
																}
																if (hrLoanEmp.getLoanOddinstno() == (princiRecorerVo.getTotalRecoveredInst() + 1L))
																	advEMI = hrLoanEmp.getLoanOddinstAmt();
															}
															amt = isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt + advEMI;
															princiRecorerVo.setTotalRecoveredAmt(amt);
															new_no = isMultiRecovery ? prin_install_no : (prin_install_no + 1);
															princiRecorerVo.setTotalRecoveredInst(new_no);
															princiRecorerVo.setUpdatedDate(approvalDate);
															princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
															princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
															princiRecorerVoList.add(princiRecorerVo);
															if (amt >= prinLoanAmt)
															{
																loanStatusFlag = 0;
																hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
															}
															hrLoanEmp.setMulLoanRecRemarks("");
															hrLoanEmp.setMulLoanRecoveryMode(0);
															hrLoanEmp.setMulLoanInstRecvd(1);
															hrLoanEmp.setMulLoanAmtRecvd(0L);
															hrLoanEmp.setUpdatedDate(sysdate);
															//currSession.saveOrUpdate(hrLoanEmp);
															//hrLoanEmpList.add(hrLoanEmp);
															paySlipDAO.updateHrLoanEmpDtls(hrLoanEmp);
														}
													}
												}
											}
										}

									}//end of iff for checking paybill value
								}
								else if (compoType == 2500137)//for loans
								{
									mthdName = "getLoan" + edpCode;
									String mthdNameInt = "getLoanInt" + edpCode;
									payMthd = pay.getMethod(mthdName, null);
									Method payIntMthd = pay.getMethod(mthdNameInt, null);
									methodValue = (Double) payMthd.invoke(payBillVO, null);
									methodIntValue = (Double) payIntMthd.invoke(payBillVO, null);
									if (methodValue != 0 || methodIntValue != 0)
									{
										if (!intList.contains(compoCode))
										{
											method = null;
											setterString = "";
											setterString = "set" + resourceBundle.getString("loan" + compoCode);
											method = paySlipVO.getClass().getMethod(setterString, long.class);
											longAmt = Math.round(methodValue);
											method.invoke(paySlipVO, longAmt);
										}
										if (intList.contains(compoCode))
										{
											method = null;
											setterString = "";
											setterString = "set" + resourceBundle.getString("loan" + compoCode);
											method = paySlipVO.getClass().getMethod(setterString, long.class);
											longAmt = Math.round(methodIntValue);
											method.invoke(paySlipVO, longAmt);
										}
										hrLoanEmp = new HrLoanEmpDtls();
										empLoanList = hrLoanEmpDao.getEmpLoanDetail(empIdForLoan, compoCode);//compocode is loan type id
										loanEMI = 0;
										emp_loan_id = 0;
										prin_install_no = 0;
										int_install_no = 0;
										tot_prin_install = 0;
										tot_int_install = 0;
										loanEmi = 0;
										prinLoanAmt = 0;
										recoveredPrinLoanAmt = 0;
										recoveredIntLoanAmt = 0;
										for (int listCnt = 0; listCnt < empLoanList.size(); listCnt++)
										{
											if (!empLoanList.isEmpty())
											{
												hrLoanEmp = empLoanList.get(listCnt);
												emp_loan_id = hrLoanEmp.getEmpLoanId();
												tot_prin_install = hrLoanEmp.getLoanPrinInstNo();
												tot_int_install = hrLoanEmp.getLoanIntInstNo();
												empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);
												empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
												Date loanDate = hrLoanEmp.getLoanDate();

												Calendar c = Calendar.getInstance();
												c.set(Calendar.YEAR, loanDate.getYear() + 1900);
												c.set(Calendar.MONTH, loanDate.getMonth());
												c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());
												c.add(Calendar.MONTH, (int) (tot_prin_install + tot_int_install) + 1);//1 is added to get the last month

												Date endDate = c.getTime();
												c.set(Calendar.YEAR, yearGiven);
												c.set(Calendar.MONTH, monthGiven - 1);
												maxDayInMonth = c.getActualMaximum(5);
												c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);
												Date currentDate = c.getTime();
												//manish

												recoveredInstallments = 0;
												totalInstallments = 0;

												if (hrLoanEmp != null && hrLoanEmp.getLoanPrinInstNo() != 0)
												{
													totalInstallments = hrLoanEmp.getLoanPrinInstNo();
													recoveredInstallments = empLoanPrinList.get(0).getTotalRecoveredInst();
												}
												else
												{

													totalInstallments = hrLoanEmp.getLoanIntInstNo();
													recoveredInstallments = empLoanIntList.get(0).getTotalRecoveredIntInst();
												}
												if ((currentDate.compareTo(loanDate) == 1 && currentDate.compareTo(endDate) <= 0) || recoveredInstallments != totalInstallments)
												{
													if (empLoanPrinList != null && empLoanPrinList.size() > 0)
													{
														princiRecorerVo = new HrLoanEmpPrinRecoverDtls();
														princiRecorerVo = empLoanPrinList.get(0);

														prin_install_no = princiRecorerVo.getTotalRecoveredInst();
														recoveredPrinLoanAmt = princiRecorerVo.getTotalRecoveredAmt();
														prinLoanAmt = hrLoanEmp.getLoanPrinAmt();

														isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
														if (isMultiRecovery)
														{
															prin_install_no = prin_install_no + hrLoanEmp.getMulLoanInstRecvd();
															recoveredPrinLoanAmt = recoveredPrinLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
														}

														if ((isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt) || (!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt))//for principal Recovery amt
														{
															loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanPrinEmiAmt();//the Principal emi value
															if (!isMultiRecovery)
															{
																if (loanEMI > (prinLoanAmt - recoveredPrinLoanAmt))
																{
																	loanEMI = prinLoanAmt - recoveredPrinLoanAmt;
																}
																if (hrLoanEmp.getLoanOddinstno() == (princiRecorerVo.getTotalRecoveredInst() + 1L))
																	loanEMI = hrLoanEmp.getLoanOddinstAmt();
															}
															amt = isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt + loanEMI;
															princiRecorerVo.setTotalRecoveredAmt(amt);
															new_no = isMultiRecovery ? prin_install_no : prin_install_no + 1;
															princiRecorerVo.setTotalRecoveredInst(new_no);
															princiRecorerVo.setUpdatedDate(approvalDate);
															princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
															princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
															princiRecorerVoList.add(princiRecorerVo);
															loanEmi = loanEmi + loanEMI;

														}
														else
														//for interest Recovery amt
														{
															intRecoverVO = new HrLoanEmpIntRecoverDtls();
															empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);

															if (empLoanIntList.size() > 0)
																intRecoverVO = empLoanIntList.get(0);
															intLoanAmt = hrLoanEmp.getLoanInterestAmt();
															recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();

															int_install_no = intRecoverVO.getTotalRecoveredIntInst();
															if (isMultiRecovery)
															{
																int_install_no = int_install_no + hrLoanEmp.getMulLoanInstRecvd();
																recoveredIntLoanAmt = recoveredIntLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
															}
															if ((isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt) || (!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt))
															{
																loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value
																if (!isMultiRecovery)
																{
																	if (loanEMI > (intLoanAmt - recoveredIntLoanAmt))
																	{
																		loanEMI = intLoanAmt - recoveredIntLoanAmt;
																	}
																	if (hrLoanEmp.getLoanOddinstno() == (intRecoverVO.getTotalRecoveredIntInst() + 1L))
																		loanEMI = hrLoanEmp.getLoanOddinstAmt();
																}
																amt = isMultiRecovery ? recoveredIntLoanAmt : recoveredIntLoanAmt + loanEMI;
																intRecoverVO.setTotalRecoveredInt(amt);
																new_no = isMultiRecovery ? int_install_no : int_install_no + 1;
																intRecoverVO.setTotalRecoveredIntInst(new_no);
																intRecoverVO.setUpdatedDate(approvalDate);
																intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
																intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
																intRecoverVOList.add(intRecoverVO);
																loanEmi = loanEmi + loanEMI;
															}
														}
														if (princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt())
														{
															loanStatusFlag = 0;
															hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
														}
														hrLoanEmp.setMulLoanRecRemarks("");
														hrLoanEmp.setMulLoanRecoveryMode(0);
														hrLoanEmp.setMulLoanInstRecvd(1);
														hrLoanEmp.setMulLoanAmtRecvd(0L);
														hrLoanEmp.setUpdatedDate(sysdate);
														//currSession.saveOrUpdate(hrLoanEmp);
														//hrLoanEmpList.add(hrLoanEmp);
														paySlipDAO.updateHrLoanEmpDtls(hrLoanEmp);
													}
													else
													{
														intRecoverVO = new HrLoanEmpIntRecoverDtls();
														empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
														//logger.error("the interest list is " + empLoanIntList);
														if (empLoanIntList.size() > 0)
															intRecoverVO = empLoanIntList.get(0);
														intLoanAmt = hrLoanEmp.getLoanInterestAmt();
														recoveredIntLoanAmt = intRecoverVO.getTotalRecoveredInt();
														int_install_no = intRecoverVO.getTotalRecoveredIntInst();
														isMultiRecovery = hrLoanEmp.getMulLoanRecoveryMode() == 1 ? true : false;
														if (isMultiRecovery)
														{
															int_install_no = int_install_no + hrLoanEmp.getMulLoanInstRecvd();
															recoveredIntLoanAmt = recoveredIntLoanAmt + hrLoanEmp.getMulLoanAmtRecvd();
														}

														if ((isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt) || (!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt))
														{
															loanEMI = isMultiRecovery ? hrLoanEmp.getMulLoanAmtRecvd() : hrLoanEmp.getLoanIntEmiAmt();//the Interest emi value
															if (!isMultiRecovery)
															{
																if (loanEMI > (intLoanAmt - recoveredIntLoanAmt))
																{
																	loanEMI = intLoanAmt - recoveredIntLoanAmt;
																}
																if (hrLoanEmp.getLoanOddinstno() == (intRecoverVO.getTotalRecoveredIntInst() + 1L))
																	loanEMI = hrLoanEmp.getLoanOddinstAmt();
															}
															amt = isMultiRecovery ? recoveredIntLoanAmt : recoveredIntLoanAmt + loanEMI;
															intRecoverVO.setTotalRecoveredInt(amt);
															new_no = isMultiRecovery ? int_install_no : int_install_no + 1;
															intRecoverVO.setTotalRecoveredIntInst(new_no);
															intRecoverVO.setUpdatedDate(approvalDate);
															intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
															intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
															intRecoverVOList.add(intRecoverVO);
															loanEmi = loanEmi + loanEMI;
														}

														if (princiRecorerVo.getTotalRecoveredAmt() >= hrLoanEmp.getLoanPrinAmt() && intRecoverVO.getTotalRecoveredInt() >= hrLoanEmp.getLoanInterestAmt())
														{
															loanStatusFlag = 0;
															hrLoanEmp.setLoanActivateFlag(loanStatusFlag);
														}
														hrLoanEmp.setMulLoanRecRemarks("");
														hrLoanEmp.setMulLoanRecoveryMode(0);
														hrLoanEmp.setMulLoanInstRecvd(1);
														hrLoanEmp.setMulLoanAmtRecvd(0L);
														hrLoanEmp.setUpdatedDate(sysdate);
														//currSession.saveOrUpdate(hrLoanEmp);
														//hrLoanEmpList.add(hrLoanEmp);
														paySlipDAO.updateHrLoanEmpDtls(hrLoanEmp);
													}
												}
												else
												{}
											}
										}
										totLoan = totLoan + loanEmi;
									}//end of if for chking paybill value
									else
									{}
								}
							}
							//	loan calculation after approval end			  			 
							payBillObj = new HrPayPaybill(); //object to set in PaySlip
							payBillObj.setId(payBillVO.getId());
							payBillObj.setNetTotal(payBillVO.getNetTotal());
							if (hrEisEmpMst != null)
							{
								otherDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
								otherDtlsList = otherDAO.getEmpAvailable(hrEisEmpMst.getEmpId());
								incrementAmt = 0;
								if (otherDtlsList.size() > 0)
								{
									otherVO = (HrEisOtherDtls) otherDtlsList.get(0);
									if (otherVO.getHrEisSgdMpg() != null)
										incrementAmt = getIncrementAmount(otherVO.getHrEisSgdMpg().getHrEisScaleMst(), otherVO.getotherCurrentBasic());
								}
							}
							else
							{
								throw new NullPointerException();
							}

							quaterList = new ArrayList();
							hrEssQuater = new HrEisQtrMst();
							userID = 0;
							if (payBillVO.getHrEisEmpMst() != null)
								userID = payBillVO.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();

							if (userID != 0)
								quaterList = hrEssQuaterDao.getQuarterDtls(userID);

							if (quaterList != null && quaterList.size() > 0)
							{
								for (int count = 0; count < quaterList.size(); count++)
								{
									hrEssQuater = (HrEisQtrMst) quaterList.get(count);

									quaterId = hrEssQuater.getHrEisQuaterTypeMst().getQuaId();
								}
							}
							else
							{
								quaterId = 0;
							}
							hrQuaterTypeMst = new HrEisQuaterTypeMst();
							if (quaterId != 0)
							{
								hrQuaterTypeMst.setQuaId(quaterId);
								//paySlipVO.setHrQuaterTypeMst(hrQuaterTypeMst);
							}
							else
								//paySlipVO.setHrQuaterTypeMst(null);
							if (userID != 0)
								hrGpfBalanceDtls = empGpfDtlsDAO.read(userID);
							paySlipVO.setGpfAccNo(hrGpfBalanceDtls != null ? hrGpfBalanceDtls.getGpfAccNo() : "");

							billGroup = new MstDcpsBillGroup();
							billGroup = paybillHeadMpg.getBillNo();
							paySlipVO.setMstDcpsBillGroup(billGroup);
							if (paybillHeadMpg != null)
							{
								paySlipVO.setGrossAmt(paybillHeadMpg.getBillGrossAmt());
								paySlipVO.setNetTotal(paybillHeadMpg.getBillNetAmt());
							}
							else
							{
								paySlipVO.setGrossAmt(0);
								paySlipVO.setNetTotal(0);
							}
							paySlipVO.setIncrementAmt(incrementAmt);
							paySlipVO.setBasicPay((long) payBillVO.getBasic0101() + (long) payBillVO.getBasic0102()); //Basic Pay.		
							paySlipVO.setSplPay((long) payBillVO.getAllow0102()); //Spl Pay.
							paySlipVO.setPerPay((long) payBillVO.getAllow0101()); //Per Pay.
							paySlipVO.setDa((long) payBillVO.getAllow0103()); //DA.
							paySlipVO.setDp((long) (payBillVO.getAllow0119() + payBillVO.getAllow0120())); //DP.
							paySlipVO.setCla((long) payBillVO.getAllow0111()); //CLA.
							paySlipVO.setHra((long) payBillVO.getAllow0110());//HRA		  		 
							paySlipVO.setMa((long) payBillVO.getAllow0107()); //MA
							paySlipVO.setTa((long) payBillVO.getAllow0113());//TA

							paySlipVO.setArma((long) payBillVO.getAllow1006());
							paySlipVO.setArmouera((long) payBillVO.getAllow1007());
							paySlipVO.setBmia((long) payBillVO.getAllow1008());
							paySlipVO.setCasha((long) payBillVO.getAllow1009());
							paySlipVO.setCida((long) payBillVO.getAllow1010());
							paySlipVO.setConva((long) payBillVO.getAllow1011());
							paySlipVO.setEa((long) payBillVO.getAllow1012());
							paySlipVO.setEsisa((long) payBillVO.getAllow1013());
							paySlipVO.setEla((long) payBillVO.getAllow1014());
							paySlipVO.setFita((long) payBillVO.getAllow1015());
							paySlipVO.setGaa((long) payBillVO.getAllow1016());
							paySlipVO.setKma((long) payBillVO.getAllow1017());
							paySlipVO.setLfa((long) payBillVO.getAllow1018());
							paySlipVO.setMecha((long) payBillVO.getAllow1019());
							paySlipVO.setMea((long) payBillVO.getAllow1020());
							paySlipVO.setMesha((long) payBillVO.getAllow1021());
							paySlipVO.setNaa((long) payBillVO.getAllow1022());
							paySlipVO.setNpa((long) payBillVO.getAllow1023());
							paySlipVO.setSuma((long) payBillVO.getAllow1024());
							paySlipVO.setPa((long) payBillVO.getAllow1025());
							paySlipVO.setSda((long) payBillVO.getAllow1026());
							paySlipVO.setOa((long) payBillVO.getAllow0104());
							paySlipVO.setAp((long) payBillVO.getAllow1027());
							paySlipVO.setUa((long) payBillVO.getAllow1028());
							paySlipVO.setTecha((long) payBillVO.getAllow1003());
							paySlipVO.setTaa((long) payBillVO.getAllow1032());
							paySlipVO.setHa((long) payBillVO.getAllow1031());
							paySlipVO.setPg((long) payBillVO.getAllow1029());
							paySlipVO.setAts30((long) payBillVO.getAllow1033());
							paySlipVO.setAts50((long) payBillVO.getAllow1034());
							paySlipVO.setForce25((long) payBillVO.getAllow1035());
							paySlipVO.setForce100((long) payBillVO.getAllow1036());
							paySlipVO.setFpa((long) payBillVO.getAllow1030());
							paySlipVO.setTaforsixth((long) payBillVO.getAllow1160());

							paySlipVO.setAddDa((long) payBillVO.getAllow1147());
							paySlipVO.setAddHra((long) payBillVO.getAllow1148());
							paySlipVO.setDaArr((long) payBillVO.getAllow1149());
							paySlipVO.setTempCla((long) payBillVO.getAllow1150());
							paySlipVO.setFrana((long) payBillVO.getAllow1151());
							paySlipVO.setTempHra((long) payBillVO.getAllow1152());
							paySlipVO.setLta((long) payBillVO.getAllow1153());
							paySlipVO.setMedstu((long) payBillVO.getAllow1154());
							paySlipVO.setOas((long) payBillVO.getAllow1155());
							paySlipVO.setPerTra((long) payBillVO.getAllow1156());
							paySlipVO.setTempTa((long) payBillVO.getAllow1157());
							paySlipVO.setWa((long) payBillVO.getAllow1158());
							paySlipVO.setWriPay((long) payBillVO.getAllow1159());
							//logger.error("Ref Allow in Approve Paybill" + payBillVO.getAllow1161());
							paySlipVO.setRefAllow((long) payBillVO.getAllow1161()); // refreshment Allowance
							paySlipVO.setCentralDA((long) payBillVO.getAllow9913());//cda
							paySlipVO.setCta((long) payBillVO.getAllow9912());//cta
							paySlipVO.setPeonAllow((long) payBillVO.getAllow1163());//Peon Allowance
							paySlipVO.setIncentiveBdds((long) payBillVO.getAllow1165());// 
							paySlipVO.setRltPilot((long) payBillVO.getAllow1166());// 
							paySlipVO.setChplPilot((long) payBillVO.getAllow1167());// 
							paySlipVO.setKitPilot((long) payBillVO.getAllow1168());// 
							paySlipVO.setFlyingPayPilot((long) payBillVO.getAllow1169());// 
							paySlipVO.setInstructionalPilot((long) payBillVO.getAllow1170());// 
							paySlipVO.setQualificationPilot((long) payBillVO.getAllow1171());// 
							paySlipVO.setInspectionPilot((long) payBillVO.getAllow1172());// 
							paySlipVO.setFlyingAllowPilot((long) payBillVO.getAllow1173());// 
							paySlipVO.setOutfirPilot((long) payBillVO.getAllow1174());// 
							paySlipVO.setMiliteryPilot((long) payBillVO.getAllow1175());// 
							paySlipVO.setSpecialPayPilot((long) payBillVO.getAllow1176());// 
							paySlipVO.setCpf((long) payBillVO.getAllow1177());//
							paySlipVO.setBasicArr((long) payBillVO.getAllow1178());//
							paySlipVO.setGpfiasos((long) payBillVO.getDeduc1037());
							paySlipVO.setGpfias((long) payBillVO.getDeduc1038());
							paySlipVO.setGpfips((long) payBillVO.getDeduc1039());
							paySlipVO.setGpfifs((long) payBillVO.getDeduc1040());
							paySlipVO.setServc((long) payBillVO.getDeduc1041());
							paySlipVO.setOtherd((long) payBillVO.getDeduc1042());

							paySlipVO.setITax((long) payBillVO.getDeduc9510());
							paySlipVO.setHrr((long) payBillVO.getDeduc9550());
							paySlipVO.setPTax((long) payBillVO.getDeduc9570());
					//		paySlipVO.setRevenueStamp((long) payBillVO.getDeduc9135()); // comment by lekhchand REVENUE_STAMP
							paySlipVO.setGpfgrpd((long) payBillVO.getDeduc9583());
							paySlipVO.setGpfgrpbac((long) payBillVO.getDeduc9780());
							paySlipVO.setGpfgrpbac((long) payBillVO.getDeduc9780());
							paySlipVO.setDcps((long) payBillVO.getDeduc9534());
							paySlipVO.setDcpsDelay((long) payBillVO.getDeduc9535());
							paySlipVO.setDcpsDA((long) payBillVO.getDeduc9537());
							paySlipVO.setDcpsPay((long) payBillVO.getDeduc9536());

							paySlipVO.setGisips((long) payBillVO.getDeduc1000());
							paySlipVO.setGisias((long) payBillVO.getDeduc1001());
							paySlipVO.setGisifs((long) payBillVO.getDeduc1002());

							paySlipVO.setCgegis((long) payBillVO.getDeduc1004());
							paySlipVO.setGis((long) payBillVO.getDeduc1005());
							paySlipVO.setPli((long) payBillVO.getDeduc9711());
							paySlipVO.setGiszp((long) payBillVO.getDeduc9701());
							paySlipVO.setGpfabcmr((long) payBillVO.getDeduc9702());
							paySlipVO.setGpfdmr((long) payBillVO.getDeduc9703());
							paySlipVO.setGpfiasmr((long) payBillVO.getDeduc9704());
							paySlipVO.setGpfifsmr((long) payBillVO.getDeduc9705());
							paySlipVO.setGpfipsmr((long) payBillVO.getDeduc9706());
							paySlipVO.setHrrarr((long) payBillVO.getDeduc9707());
							paySlipVO.setJanjulgisa((long) payBillVO.getDeduc9708());
							paySlipVO.setJanjulgis((long) payBillVO.getDeduc9911());
							/*Added By Tejashree 09122019 (allow9207)Test*/
							paySlipVO.setDA_7PC((long) payBillVO.getAllow9207()); //7pc DA.
							/*Ended By Tejashree*/
							/*Added By lekhchand 09122019 (allow9209)Test*/
							paySlipVO.setSVNPC_TA((long) payBillVO.getAllow9212()); //7pc TA 
							paySlipVO.setSVNPC_GPF_ARR((long) payBillVO.getAllow9213()); //7pc GPF ARR
							paySlipVO.setSVNPC_DCPS_ARR((long) payBillVO.getAllow9214()); //7pc DCPS ARR
							paySlipVO.setSVNPC_TA_ARR((long) payBillVO.getAllow9215()); //7pc TA Arr
							paySlipVO.setSVNPC_GPF_ARR_DEDU((long) payBillVO.getDeduc9216()); //7pcGPF ARR DED
							paySlipVO.setSVNPC_GPF_RECO((long) payBillVO.getDeduc9217()); //7pc GPF REC
							paySlipVO.setSVNPC_DCPS_RECO((long) payBillVO.getDeduc9218()); //7pc DCPS RECO
							/*Ended By lekhchand*/
			                
							paySlipVO.setPtarr((long) payBillVO.getDeduc9712());
							paySlipVO.setOdtr((long) payBillVO.getDeduc9713());

							paySlipVO.setOdtr((long) payBillVO.getDeduc9713());//30 deductions
							paySlipVO.setCpfContribution((long) payBillVO.getAllow9186());//
							paySlipVO.setCpfEmployee((long) payBillVO.getDeduc9141());
							paySlipVO.setCpfEmployer((long) payBillVO.getDeduc9140());
							//Added by Kinjal for GAP
							paySlipVO.setGroupAcciPolicy((long) payBillVO.getDeduc9134());
							
							//NPS Allowance deduction
							paySlipVO.setNpsDeduction((long) payBillVO.getDeduc9142());
							paySlipVO.setNpsNpsAllowance((long) payBillVO.getAllow9208());
						//	paySlipVO.setNpsAllowance();
							
							paySlipVO.setNetTotal((long) payBillVO.getNetTotal());//net total

							/**************************************************************************************/
							paySlipVO.setMonth((long) paybillHeadMpg.getMonth());
							paySlipVO.setPaySlipDate(approvalDate);
							paySlipVO.setUpdatedDate(new Date());
							paySlipVO.setYear((long) paybillHeadMpg.getYear());
							paySlipVO.setHrEisEmpMst(hrEisEmpMst);
							paySlipVO.setHrPayPaybill(payBillObj);
							paySlipVO.setTrnCounter(new Integer(1));

							itAccNo = paySlipDAO.getHrEisProofDtls(userID);
							if (itAccNo == null)
								itAccNo = "";

							paySlipVO.setItAccNo(itAccNo);
							paySlipVO.setCreatedDate(sysdate);
							paySlipVO.setCmnLanguageMst(cmnLanguageMst);
							paySlipVO.setCmnDatabaseMst(cmnDatabaseMst);
							paySlipVO.setOrgPostMstByUpdatedByPost(orgPostMst);
							paySlipVO.setOrgPostMstByCreatedByPost(orgPostMst);
							paySlipVO.setCmnLocationMst(cmnLocationMst);
							paySlipVO.setOrgUserMstByUpdatedBy(orgUserMst);
							paySlipVO.setOrgUserMstByCreatedBy(orgUserMst);

							IdGenerator idGen = new IdGenerator();
							long paySlipId = idGen.PKGenerator("HR_PAY_PAYSLIP", objectArgs);

							paySlipVO.setPaySlipId(paySlipId);

							paySlipVOList.add(paySlipVO);
						}
						else
						{
							throw new MonthException();
						}
					}
				}
				for (HrPayPaybill hrPayPaybill : lstPayPayBill)
				{
					hrPayPaybill.setApproveRejectDate(approvalDate);
					hrPayPaybillList.add(hrPayPaybill);
				}

				long voucherNo = objectArgs.get("billNo") != null ? Long.valueOf(objectArgs.get("billNo").toString()) : 0;
				String voucherdate;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
				logger.info("Date Generated" + cal.getTime());
				voucherdate = sdf.format(cal.getTime());
				Date voucherDate = null;
				voucherDate = sdf.parse(sdf.format(cal.getTime()));
				for (PaybillHeadMpg paybillHeadMapping : paybillHeadMpgList)
				{
					paybillHeadMapping.setApproveFlag(appFlag);
					paybillHeadMapping.setUpdatedDate(sysdate);
					paybillHeadMapping.setVoucherNumber(voucherNo);
					paybillHeadMapping.setVoucherDate(voucherDate);
					paybillHeadMappingList.add(paybillHeadMapping);
				}
				//To pass Voucher Details on Approval
				ResultObject resultObjectByDcps = new ResultObject();
				;
				resultObjectByDcps.setResultCode(1);
				if (givenBillNo != null && givenBillNo.length() > 0 && appFlag == 1 && voucherNo != 0 && voucherdate != null && voucherdate.length() > 0)
				{
					Map inputMap = objectArgs;
					inputMap.put("currentSessionForDcps", currSession);
					inputMap.put("cmbMonth", monthGiven);
					inputMap.put("cmbYear", yearGiven);
					inputMap.put("cmbBillGroup", givenBillNo);
					inputMap.put("txtVoucherNo", voucherNo);
					inputMap.put("txtVoucherDt", voucherdate);
					inputMap.put("FreezeFlag", appFlag);
					OfflineContriServiceImpl offlineContriServiceImpl = new OfflineContriServiceImpl();
					//resultObjectByDcps = offlineContriServiceImpl.saveVoucherDtlsForContriSchdlr(inputMap);
				}
				objectArgs.put("msg", "Records Have Been Approved By Scheduler.");
				logger.info("Abhi don here " + objectArgs.get("msg"));

				if (resultObjectByDcps != null && resultObjectByDcps.getResultCode() == 1)
				{
					logger.info("Inside If");
					if (princiRecorerVoList != null && !princiRecorerVoList.isEmpty())
					{
						for (HrLoanEmpPrinRecoverDtls princiRecorer : princiRecorerVoList)
						{
							currSession.saveOrUpdate(princiRecorer);
						}
					}
					if (paybillHeadMappingList != null && !paybillHeadMappingList.isEmpty())
					{
						for (PaybillHeadMpg princiRecorer : paybillHeadMappingList)
						{
							currSession.saveOrUpdate(princiRecorer);
						}
					}
					if (paySlipVOList != null && !paySlipVOList.isEmpty())
					{
						for (HrPayPayslip princiRecorer : paySlipVOList)
						{
							currSession.saveOrUpdate(princiRecorer);
						}
					}
					if (intRecoverVOList != null && !intRecoverVOList.isEmpty())
					{
						for (HrLoanEmpIntRecoverDtls princiRecorer : intRecoverVOList)
						{
							currSession.saveOrUpdate(princiRecorer);
						}
					}
				}
				else
					throw new Exception("DCPS Service Throws Exception");
				if (resultObjectByDcps.getResultCode() == -1)
					objectArgs.put("messageForPaybillSch", "Error is Thrown From DCPS Approval Service.");
				else
					objectArgs.put("messageForPaybillSch", "Paybill Successfully Approved.");

				resultObject.setResultValue(objectArgs);
				resultObject.setViewName(viewname);
			}
			princiRecorerVoList = null;
			paybillHeadMappingList = null;
			//			hrLoanEmpList = null;
			cmnLanguageMstDaoImpl = null;
			paySlipVOList = null;
			intRecoverVOList = null;
			hrEisProofDtlsDAO = null;
			hrLoanIntRecoverDao = null;
			hrLoanPrinRecoverDao = null;
			edpList = null;
			empGpfDtlsDAO = null;
			empLoanIntList = null;
			empLoanList = null;
			empLoanPrinList = null;
			logger.info("Successfully Leaving Scheduler to Approve Bill");
		}
		catch (DuplicateRecordException e)
		{
			objectArgs.put("msg", "Payslip Record Already Exist.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error("Error is: " + e.getMessage());
			logger.error(e);

		}
		catch (MonthException e)
		{
			objectArgs.put("msg", "There is No Record For This Month in Paybill.");
			objectArgs.put("messageForPaybillSch", "There is No Record For This Month in Paybill.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error("Error is: " + e.getMessage());
			logger.error(e);

		}
		catch (NullPointerException ne)
		{
			//logger.error("Null Pointer Exception Ocuures...approvePayBill");
			logger.error("Error is: " + ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objectArgs.put("messageForPaybillSch", ne);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(ne);
		}
		catch (PropertyValueException pe)
		{
			//logger.error("PropertyValueException Ocuures...approvePayBill");
			logger.error("Error is: " + pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objectArgs.put("messageForPaybillSch", pe);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(pe);
		}
		catch (Exception e)
		{
			//logger.error("Exception Ocuures...approvePayBill");
			e.printStackTrace();
			if (e.getMessage().equalsIgnoreCase("DCPS Service Throws Exception"))
				objectArgs.put("msg", "Voucher details not saved");
			else
				objectArgs.put("msg", "There is Some Problem while inserting into database.");
			objectArgs.put("messageForPaybillSch", e);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName(viewname);
			logger.error(e);
		}
		return resultObject;
	}
}

@SuppressWarnings("serial")
class MonthException extends Exception
{
	MonthException()
	{
		super();
	}

}

@SuppressWarnings("serial")
class DuplicateRecordException extends Exception
{
	DuplicateRecordException()
	{
		super();
	}

}
