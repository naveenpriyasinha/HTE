package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.valueobject.EmpPaybillLoanVO;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.LoanRecoverVO;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

/**
 * This class is used to Calculate Loans and Advances.
 * @author Ankit Bhatt
 *
 */
public class GenerateBillLoanAndAdvanceHelper {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	int deactivate = Integer.valueOf(resourceBundle.getString("deactive").toString());
	private static final String INSERT_LOAN_FLAG = "N";

	/**
	 * Method will calculate Advances data Employee ID wise and
	 * return EMI value in Generate Bill service. 
	 * @param objectArgs
	 * @return EMI amount
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map calculateAdances(Map objectArgs) throws Exception {

		long advEMI = 0;
		long prinLoanAmt = 0;
		long intLoanAmt = 0;
		long recoveredPrinLoanAmt = 0;
		long recoveredIntLoanAmt = 0;
		long emp_loan_id = 0;
		long prin_install_no = 0;
		long int_install_no = 0;
		long tot_prin_install = 0;
		long tot_int_install = 0;
		Map returnMap = objectArgs;

		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
			int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
			EmpLoanDAOImpl LoanEmpDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			
			EmpPaybillLoanVO empPaybillLoanVO = (EmpPaybillLoanVO) (objectArgs.get("EmpPaybillLoanVO")!=null ?
					objectArgs.get("EmpPaybillLoanVO") : null);
			if(empPaybillLoanVO!=null)
			  emp_loan_id = empPaybillLoanVO.getEmpLoanId();
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());

			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			
			Map<Long,LoanRecoverVO> intRecoverVOMap = (Map)(objectArgs.get("loanIntRecoverMap")!=null ? objectArgs.get("loanIntRecoverMap"):new HashMap()); 
	        Map<Long,LoanRecoverVO> princRecoverVOMap = (Map)(objectArgs.get("loanPrincRecoverMap")!=null ? objectArgs.get("loanPrincRecoverMap"):new HashMap());
			
			LoanRecoverVO empLoanPrinList = null;
			LoanRecoverVO loanIntList = new LoanRecoverVO();
			
			List<HrPayPaybillLoanDtls> lstHrPayLoanEmpDtls = new ArrayList<HrPayPaybillLoanDtls>();												

			long prinRecvType = Long.parseLong(resourceBundle.getString("principal"));
			long intRecvType = Long.parseLong(resourceBundle.getString("interest"));
			CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			CmnLookupMst prinLookupType = cmnLookupDao.read(prinRecvType);
			CmnLookupMst intLookupType = cmnLookupDao.read(intRecvType);
				        
			IdGenerator idGenerator = new IdGenerator();
			long paybillLoanDtlsId = 0;

			if(princRecoverVOMap.containsKey(emp_loan_id))
				  empLoanPrinList = princRecoverVOMap.get(emp_loan_id);
			
			princRecoverVOMap = null;
			
			if(intRecoverVOMap.containsKey(emp_loan_id))
				loanIntList = intRecoverVOMap.get(emp_loan_id);
			
			intRecoverVOMap = null;
			
			Map deactivateAdvanceRecords = new HashMap();

			Date sysDate = new Date();			

					emp_loan_id = empPaybillLoanVO.getEmpLoanId();
					tot_prin_install = empPaybillLoanVO.getLoanPrinInstNo();
					tot_int_install = empPaybillLoanVO.getLoanIntInstNo();
					//empLoanPrinList = hrLoanPrinRecoverDao.getAllData(emp_loan_id);
					Date loanDate = empPaybillLoanVO.getLoanDate();
					Calendar c = Calendar.getInstance();
					c.set(Calendar.YEAR, loanDate.getYear() + 1900);
					c.set(Calendar.MONTH, loanDate.getMonth());
					c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());

					logger.info("tot_prin_install " + tot_prin_install + " tot_int_install " + tot_int_install);
					// 1 is added to get the last month 
					c.add(Calendar.MONTH, (int) (tot_prin_install + tot_int_install) + 1);
					Date endDate = c.getTime();

					c.set(Calendar.YEAR, yearGiven);
					c.set(Calendar.MONTH, monthGiven - 1);

					int maxDayInMonth = c.getActualMaximum(5);
					c.set(Calendar.DAY_OF_MONTH, maxDayInMonth);

					logger.info("The Loan Date is :-" + loanDate);
					logger.info("The Loan End Date is :-" + endDate);
					Date currentDate = c.getTime(); // 1st of given month
					if ((currentDate.compareTo(loanDate) == 1 && currentDate.compareTo(endDate) <= 0 )||( empLoanPrinList != null && empLoanPrinList.getRecoveredInst() != empPaybillLoanVO.getLoanPrinInstNo())) {
						// deduction will be in the current month and 1 less
						// than the current month
						if (empLoanPrinList != null) {
							
							prinLoanAmt = empPaybillLoanVO.getLoanPrinAmt();
							// Recovered Principal Amount.
							recoveredPrinLoanAmt = empLoanPrinList.getRecoveredAmount(); 
							prin_install_no = empLoanPrinList.getRecoveredInst();
							logger.info("The Recovered Principal Amount is:-" + recoveredPrinLoanAmt + "And empId is:"
									+ empPaybillLoanVO.getEmpId());
							boolean isMultiRecovery = empPaybillLoanVO.getMulLoanRecoveryMode() == 1 ? true : false;
							if( isMultiRecovery )
							{
								prin_install_no  = prin_install_no + empPaybillLoanVO.getMulLoanInstRecvd();
								recoveredPrinLoanAmt = recoveredPrinLoanAmt + empPaybillLoanVO.getMulLoanAmtRecvd();
							}
							
							// loan for principal Recovery amt as well as Installment Number.
							if(
								(isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt)
								||
								(!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt)
							)
							{
								// advEMI=hrLoanEmp.getLoanEmiAmt();
								// the Principal emi value
								advEMI = Math.round(isMultiRecovery ? empPaybillLoanVO.getMulLoanAmtRecvd() : empPaybillLoanVO.getLoanPrinEmiAmt());

								// Modified By Mrugesh Start
								// If last installment then set EMI amount as per remaining recovered amount
								//05 jan 2011
								//if ((empLoanList.get(0).getLoanPrinInstNo() - empLoanPrinList.get(0).getTotalRecoveredInst()) == 1)
								//{
								/*if ((advEMI + empLoanPrinList.get(0).getTotalRecoveredAmt()) < empLoanPrinList.get(0).getHrLoanEmpDtls()
											.getLoanPrinAmt()) {
										advEMI = empLoanPrinList.get(0).getHrLoanEmpDtls().getLoanPrinAmt()
												- empLoanPrinList.get(0).getTotalRecoveredAmt();
										//change the activate status of loan
										//last installmenet of principle amount and no interest installment there, so
										//deactivating of loan

											logger.info("Last installement of loan - Principle and no interest installement " +
													" so, deactivating the loan for Id " + hrLoanEmp.getEmpLoanId());
											int activateStatus = 0;
											int rowsUpdated = changeActivateStatus(hrLoanEmp.getEmpLoanId(),activateStatus,serv);
											if(!deactivateAdvanceRecords.containsKey(hrLoanEmp.getEmpLoanId()))
											  deactivateAdvanceRecords.put(hrLoanEmp.getEmpLoanId(), hrLoanEmp );
											logger.info("Rows updated for loan emp id " + hrLoanEmp.getEmpLoanId() + " is " + rowsUpdated);

										//ended
									}
								 */
								//added by manish as per Government of Maharashtra
								if(!isMultiRecovery){
									if (advEMI > (prinLoanAmt - recoveredPrinLoanAmt)) {
										advEMI = prinLoanAmt - recoveredPrinLoanAmt;
									}
									logger.info("The adv EMI before last installment is------------>>>>>>"+advEMI);
									if((empLoanPrinList.getRecoveredInst()+1L == empPaybillLoanVO.getLoanOddinstno()))
									{
										advEMI=empLoanPrinList.getOddInstAmount();
									}
									else
									{
										//advEMI=empLoanPrinList.get(0).getHrLoanEmpDtls().getLoanPrinEmiAmt();
										advEMI=empPaybillLoanVO.getLoanPrinEmiAmt();
									}
								}
								logger.info("The adv EMI after last installment is------------>>>>>>"+advEMI);
								//change the activate status of loan
								//last installmenet of principle amount and no interest installment there, so
								//deactivating of loan

								logger.info("Last installement of loan - Principle and no interest installement " +
										" so, deactivating the loan for Id " + empPaybillLoanVO.getEmpLoanId());
								//int activateStatus = 0;
								/*int rowsUpdated = changeActivateStatus(hrLoanEmp.getEmpLoanId(),activateStatus,serv);
										if(!deactivateAdvanceRecords.containsKey(hrLoanEmp.getEmpLoanId()))
										  deactivateAdvanceRecords.put(hrLoanEmp.getEmpLoanId(), hrLoanEmp );
										logger.info("Rows updated for loan emp id " + hrLoanEmp.getEmpLoanId() + " is " + rowsUpdated);*/

								//ended

								//ended by manish
								//}
								// Modified By Mrugesh End

								// long
								// amt=princiRecorerVo.getTotalRecoveredAmt()+advEMI;
								long totRecoveredAmt = isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt + advEMI;
								// princiRecorerVo.setTotalRecoveredAmt(amt);
								long totRecoveredInst = isMultiRecovery ? prin_install_no  : prin_install_no + 1;
								logger.info("The Recovered Principal Amount after :-" + totRecoveredAmt);
								logger.info("The Recovered Principal Installment No is :-" + totRecoveredInst);								
							

								List oldRecords = null;
									//LoanEmpDAO.getPaybillLoanDtls(empPaybillLoanVO.getEmpId(), empPaybillLoanVO.getLoanAdvId(),										 prinLookupType.getLookupId(), monthGiven, yearGiven);
								if (oldRecords != null && oldRecords.size() > 0) {
									logger.info("Brfore fetching the object for advance principal");
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls) oldRecords.get(0);
									logger.info("Obeject is fetched for advace principal:-" + hrPayPaybillLoanDtls.getId());
									// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
									hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanPrinAmt());
									// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
									hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanPrinInstNo());
									hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
									hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
									
									
									HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
									hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
									hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
									
									hrPayPaybillLoanDtls.setUpdatedDate(sysDate);
									hrPayPaybillLoanDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
									hrPayPaybillLoanDtls.setOrgUserMstByUpdatedBy(orgUserMst);
									logger.info("The Paybill is already generated for the same employee so the record is updated");

									// modified by Ankit Bhatt - will be updated
									// in Calling Method
									lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);
									// paybillLoanDtlsDao.update(hrPayPaybillLoanDtls);
									// ended

								} else {
									paybillLoanDtlsId = idGenerator.PKGenerator("HR_PAY_PAYBILL_LOAN_DTLS", objectArgs);
									logger.info("The generated bill Loan Id is:-" + paybillLoanDtlsId);
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = new HrPayPaybillLoanDtls();
									hrPayPaybillLoanDtls.setId(paybillLoanDtlsId);
									// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
									hrPayPaybillLoanDtls.setCmnDatabaseMst(cmnDatabaseMst);
									hrPayPaybillLoanDtls.setCmnLocationMst(cmnLocationMst);
									hrPayPaybillLoanDtls.setCreatedDate(sysDate);
									
									HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
									hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
									hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
									
									hrPayPaybillLoanDtls.setRecoveryType(prinLookupType);
									HrLoanAdvMst loanAdvMst = new HrLoanAdvMst();
									loanAdvMst.setLoanAdvId(empPaybillLoanVO.getLoanAdvId());
									hrPayPaybillLoanDtls.setHrLoanAdvMst(loanAdvMst);
									hrPayPaybillLoanDtls.setOrgPostMstByCreatedByPost(orgPostMst);
									hrPayPaybillLoanDtls.setOrgUserMstByCreatedBy(orgUserMst);
									hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanPrinAmt());
									hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanPrinInstNo());
									hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
									hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
									logger.info("The Loan Details are going to insert");
									// modified by Ankit Bhatt - will be updated
									// in Calling Method
									lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);
									// paybillLoanDtlsDao.create(hrPayPaybillLoanDtls);
									// ended
									logger.info("The Loan Details inserted successfully");
									logger.info("The Loan Details inserted successfully");
								}
								oldRecords = null;
								// }
								// End Urvin Shah.
							} else// for interest Recovery amt
							{
								
								//empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
								int_install_no = loanIntList.getRecoveredInst();
								recoveredIntLoanAmt = loanIntList.getRecoveredAmount();
								intLoanAmt = empPaybillLoanVO.getLoanInterestAmt();
								
								if( isMultiRecovery )
								{
									int_install_no  = int_install_no + empPaybillLoanVO.getMulLoanInstRecvd();
									recoveredIntLoanAmt = recoveredIntLoanAmt + empPaybillLoanVO.getMulLoanAmtRecvd();
								}
								if(
									(isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt)
									||
									(!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt)
									)
								{
									// advEMI=hrLoanEmp.getLoanEmiAmt();//the
									// emi value
									advEMI = Math.round(isMultiRecovery ? empPaybillLoanVO.getMulLoanAmtRecvd() :  empPaybillLoanVO.getLoanIntEmiAmt());// the
									// Interest
									// emi
									// value

									
									//05 jan 2011
									if(!isMultiRecovery){
										if (advEMI > (intLoanAmt - recoveredIntLoanAmt)) {
											advEMI = intLoanAmt - recoveredIntLoanAmt;
										}
										if((loanIntList.getRecoveredInst()+1L)==empPaybillLoanVO.getLoanOddinstno())
										{
											advEMI=loanIntList.getOddInstAmount();
											logger.info(" Encountered odd installment in ADVANCE_INTREST "+advEMI);											
	
										}
									}
									// Modified By Mrugesh Start
									// If last installment then set EMI amount as per remaining recovered amount
									/*if ((empLoanList.get(0).getLoanIntInstNo() - empLoanIntList.get(0).getTotalRecoveredIntInst()) == 1)
									{
										if ((advEMI + empLoanIntList.get(0).getTotalRecoveredInt()) < empLoanIntList.get(0).getHrLoanEmpDtls()
												.getLoanInterestAmt()) {
											advEMI = empLoanIntList.get(0).getHrLoanEmpDtls().getLoanInterestAmt()
													- empLoanIntList.get(0).getTotalRecoveredInt();

											logger.info("Last installement of loan - Interest so, deactivating the loan for Id " + hrLoanEmp.getEmpLoanId());											
											int rowsUpdated = changeActivateStatus(hrLoanEmp.getEmpLoanId(),deactivate,serv);
											if(!deactivateAdvanceRecords.containsKey(hrLoanEmp.getEmpLoanId()))
											 deactivateAdvanceRecords.put(hrLoanEmp.getEmpLoanId(), hrLoanEmp );
											logger.info("Rows updated for loan emp id " + hrLoanEmp.getEmpLoanId() + " is " + rowsUpdated);

										}
									}*/
									// //Modified By Mrugesh End

									long totRecoveredAmt = isMultiRecovery ?recoveredIntLoanAmt  : recoveredIntLoanAmt + advEMI;									
									long totRecoveredInst = isMultiRecovery  ? int_install_no  : int_install_no + 1;									
									List oldRecords = null;
										//LoanEmpDAO.getPaybillLoanDtls(empPaybillLoanVO.getEmpId(), empPaybillLoanVO											.getLoanAdvId(), intLookupType.getLookupId(), monthGiven, yearGiven);
									if (oldRecords != null && oldRecords.size() > 0) {
										HrPayPaybillLoanDtls hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls) oldRecords.get(0);
										logger.info("Obeject is fetched in advace interest" + hrPayPaybillLoanDtls.getId());
										// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanPrinAmt());
										// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanPrinInstNo());
										hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
										hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
										
										HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
										hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
										hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
										
										hrPayPaybillLoanDtls.setUpdatedDate(sysDate);
										hrPayPaybillLoanDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
										hrPayPaybillLoanDtls.setOrgUserMstByUpdatedBy(orgUserMst);
										logger.info("The Paybill is already generated for the same employee so the record is updated");

										// modified by Ankit Bhatt
										lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);
										// paybillLoanDtlsDao.update(hrPayPaybillLoanDtls);
										// ended
									} else {
										paybillLoanDtlsId = idGenerator.PKGenerator("HR_PAY_PAYBILL_LOAN_DTLS", objectArgs);
										logger.info("The generated bill Loan Id is:-" + paybillLoanDtlsId);
										HrPayPaybillLoanDtls hrPayPaybillLoanDtls = new HrPayPaybillLoanDtls();
										hrPayPaybillLoanDtls.setId(paybillLoanDtlsId);
										// hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setCmnDatabaseMst(cmnDatabaseMst);
										hrPayPaybillLoanDtls.setCmnLocationMst(cmnLocationMst);
										hrPayPaybillLoanDtls.setCreatedDate(sysDate);
										
										HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
										hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
										hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
										
										hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanInterestAmt());
										hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanIntInstNo());
										hrPayPaybillLoanDtls.setRecoveryType(intLookupType);
										HrLoanAdvMst loanAdvMst = new HrLoanAdvMst();
										loanAdvMst.setLoanAdvId(empPaybillLoanVO.getLoanAdvId());
										hrPayPaybillLoanDtls.setHrLoanAdvMst(loanAdvMst);
										hrPayPaybillLoanDtls.setOrgPostMstByCreatedByPost(orgPostMst);
										hrPayPaybillLoanDtls.setOrgUserMstByCreatedBy(orgUserMst);
										hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
										hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
										logger.info("The Loan Details are going to insert");

										// modified by Ankit Bhatt
										lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);
										// paybillLoanDtlsDao.create(hrPayPaybillLoanDtls);
										// ended

										logger.info("The Loan Details inserted successfully");
										logger.info("The Loan Details inserted successfully");
									}
									oldRecords = null;
									// }
									// End Urvin Shah.
								}
							}
						}
					}
								
			
			returnMap.put("deactivateAdvanceRecords",deactivateAdvanceRecords);
			returnMap.put("lstHrPayLoanEmpDtls",lstHrPayLoanEmpDtls);
			returnMap.put("advEMI",advEMI);
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			throw e;
		}
		return returnMap;
	}

	/**
	 * Method will calculate Loans for given employee ID and
	 * returns the Loan EMI amount, Interest EMI amount
	 * and Employee Loan list to be inserted, to GenerateBillService.
	 * @param objectArgs
	 * @return Map
	 * @throws Exception
	 */
	public Map calculateLoans(Map objectArgs) throws Exception {
		Map resultMap = objectArgs;
		try {			
			logger.info("calculateLoans in - GenerateBillLoanAndAdvances");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");			
			int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
			int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
			HrPayPaybill payBillVO = (HrPayPaybill)(objectArgs.get("paybillVO") != null ? objectArgs.get("paybillVO"): null);
			/*List<HrLoanEmpDtls> empLoanList = (List<HrLoanEmpDtls>)(objectArgs.get("empLoanList") != null ?
					objectArgs.get("empLoanList"): new ArrayList<HrLoanEmpDtls>());*/
			EmpPaybillLoanVO empPaybillLoanVO = (EmpPaybillLoanVO) (objectArgs.get("EmpPaybillLoanVO")!=null ?
					objectArgs.get("EmpPaybillLoanVO") : null);
			Map deactivateLoanRecords = new HashMap();
			
			EmpLoanDAOImpl LoanEmpDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());

			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());

			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			
			Map<Long,LoanRecoverVO> intRecoverVOMap = (Map)(objectArgs.get("loanIntRecoverMap")!=null ? objectArgs.get("loanIntRecoverMap"):new HashMap()); 
	        Map<Long,LoanRecoverVO> princRecoverVOMap = (Map)(objectArgs.get("loanPrincRecoverMap")!=null ? objectArgs.get("loanPrincRecoverMap"):new HashMap());

			GenericDaoHibernateImpl paybillLoanDtlsDao = new GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
			paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());

			List<HrPayPaybillLoanDtls> lstHrPayLoanEmpDtls = new ArrayList<HrPayPaybillLoanDtls>();

			// Added By Urvin Shah.
			long prinRecvType = Long.parseLong(resourceBundle.getString("principal"));
			long intRecvType = Long.parseLong(resourceBundle.getString("interest"));


			CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			CmnLookupMst prinLookupType = cmnLookupDao.read(prinRecvType);
			CmnLookupMst intLookupType = cmnLookupDao.read(intRecvType);
			//ended

			IdGenerator idGenerator = new IdGenerator();
			long paybillLoanDtlsId = 0;

			
			LoanRecoverVO empLoanPrinList = new LoanRecoverVO();

			long loanEMI=0;
			long loanIntEMI=0;
			long emp_loan_id=0;
			long prin_install_no=0;
			long int_install_no=0;
			long tot_prin_install=0;
			long tot_int_install=0;
			long loanEmi=0;
			long loanIntEmi=0;
			long prinLoanAmt = 0;
			long recoveredPrinLoanAmt = 0;
			long recoveredIntLoanAmt = 0 ;			

			//hrPayPaybill.setId(paybillId);
			HrPayPaybillLoanDtls hrPayPaybillLoanDtls = null;
			

													
					
					emp_loan_id=empPaybillLoanVO.getEmpLoanId();
					tot_prin_install=empPaybillLoanVO.getLoanPrinInstNo();
					tot_int_install=empPaybillLoanVO.getLoanIntInstNo();
					if(princRecoverVOMap.containsKey(emp_loan_id))
					  empLoanPrinList = princRecoverVOMap.get(emp_loan_id);
					
					princRecoverVOMap = null;
					logger.info("Employee Loan Id is:-"+emp_loan_id);
					Date loanDate=empPaybillLoanVO.getLoanDate();

					Calendar c = Calendar.getInstance();

					c.set(Calendar.YEAR, loanDate.getYear()+1900);
					c.set(Calendar.MONTH, loanDate.getMonth());
					c.set(Calendar.DAY_OF_MONTH, loanDate.getDate());

					c.add(Calendar.MONTH,(int)(tot_prin_install+tot_int_install)+1);//1 is added to get the last month

					Date endDate = c.getTime();

					c.set(Calendar.YEAR, yearGiven);
					c.set(Calendar.MONTH,monthGiven-1);

					int maxDayInMonth = c.getActualMaximum(5);



					c.set(Calendar.DAY_OF_MONTH,maxDayInMonth);

					Date currentDate = c.getTime();
					logger.info("testing loan Date Is:-"+loanDate);
					logger.info("currentDate Is:-"+currentDate);
					logger.info("End Date Is:-"+endDate);

					//added by manish
					LoanRecoverVO loanIntList = new LoanRecoverVO();
					if(intRecoverVOMap.containsKey(emp_loan_id))
						loanIntList = intRecoverVOMap.get(emp_loan_id);
					
					intRecoverVOMap = null;
					//List<HrLoanEmpIntRecoverDtls>  loanIntList =hrLoanIntRecoverDao.getAllData(emp_loan_id);
					long recoveredInstall=0;
					long totalInstallmentnts=0; 
					if(tot_prin_install >0 )
					{
						recoveredInstall=empLoanPrinList.getRecoveredInst();
						totalInstallmentnts=tot_prin_install;
					}
					else 
					{
						recoveredInstall=loanIntList.getRecoveredInst();
						totalInstallmentnts = tot_int_install;
					}


					//ended by manish 

					//&&empLoanPrinList != null  && empLoanPrinList.get(0).getTotalRecoveredInst()!=hrLoanEmp.getLoanPrinInstNo()
					if((currentDate.compareTo(loanDate)==1 && currentDate.compareTo(endDate)<=0 )||recoveredInstall != totalInstallmentnts )
					{
						//deduction will be in the current month and 1 less than the current month
						if(empLoanPrinList != null)
						{														

							prin_install_no = empLoanPrinList.getRecoveredInst();
							recoveredPrinLoanAmt = empLoanPrinList.getRecoveredAmount();
							prinLoanAmt = empPaybillLoanVO.getLoanPrinAmt();

							//Added By Varshil for Multiple Loan Recovery
							boolean isMultiRecovery = empPaybillLoanVO.getMulLoanRecoveryMode() == 1 ? true : false;
							if( isMultiRecovery )
							{
								prin_install_no  = prin_install_no + empPaybillLoanVO.getMulLoanInstRecvd();
								recoveredPrinLoanAmt = recoveredPrinLoanAmt + empPaybillLoanVO.getMulLoanAmtRecvd();
							}
							
							if(
									(isMultiRecovery && prin_install_no <= tot_prin_install && recoveredPrinLoanAmt <= prinLoanAmt)
									||
									(!isMultiRecovery && prin_install_no < tot_prin_install && recoveredPrinLoanAmt < prinLoanAmt)
									
							)//for principal Recovery amt
							{
								//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
								loanEMI=Math.round(isMultiRecovery ? empPaybillLoanVO.getMulLoanAmtRecvd() :  empPaybillLoanVO.getLoanPrinEmiAmt());//the Principal emi value
								logger.info("The loan EMI is------------>>>>>>"+loanEMI);
								
								//Modified By Mrugesh. Start
								//05 jan 2011
								//if((hrLoanEmp.getLoanPrinInstNo() - princiRecorerVo.getTotalRecoveredInst())==1)//If last installment then set EMI amount as per remaining recovered amount
								//{
								/*logger.info("The loan EMI before last installment is------------>>>>>>"+loanEMI);
									if((loanEMI + empLoanPrinList.get(0).getTotalRecoveredAmt()) < empLoanPrinList.get(0).getHrLoanEmpDtls().getLoanPrinAmt())
									{
										loanEMI = empLoanPrinList.get(0).getHrLoanEmpDtls().getLoanPrinAmt() - empLoanPrinList.get(0).getTotalRecoveredAmt();
										logger.info("The loan EMI After last installment is------------>>>>>>"+loanEMI);
									}*/
								//added by manish as per Government of Maharashtra
								logger.info("The loan EMI before last installment is------------>>>>>>"+loanEMI);
								if(!isMultiRecovery){
									if(loanEMI>(prinLoanAmt-recoveredPrinLoanAmt))
									{
										loanEMI=prinLoanAmt-recoveredPrinLoanAmt;
									}
									logger.info("The if condition value is------>"+(empPaybillLoanVO.getLoanPrinInstNo() - empLoanPrinList.getRecoveredInst()));
									
									if( empLoanPrinList.getRecoveredInst() + 1L  == empPaybillLoanVO.getLoanOddinstno() )
									{ // if odd inst is in between 
										loanEMI=empLoanPrinList.getOddInstAmount();
										logger.info("The loan EMI After last installment is------------>>>>>>"+loanEMI);
	
									}
									else
									{
										loanEMI=empPaybillLoanVO.getLoanPrinEmiAmt();
										logger.info("The loan EMI After last installment is------------>>>>>>"+loanEMI);
										//}
	
										//ended by manish
	
										logger.info("Last installement of loan - Principle so, deactivating the loan for Id " + empPaybillLoanVO.getEmpLoanId());									
										/*int rowsUpdated = changeActivateStatus(hrLoanEmp.getEmpLoanId(),deactivate,serv);
										if(!deactivateLoanRecords.containsKey(payBillVO.getOrgPostMst().getPostId()))
										  deactivateLoanRecords.put(payBillVO.getOrgPostMst().getPostId(), payBillVO );
										logger.info("Rows updated for loan  id " + hrLoanEmp.getEmpLoanId() + " is " + rowsUpdated);*/
									}
								}
								//Modified By Mrugesh. End
								//long amt=princiRecorerVo.getTotalRecoveredAmt()+loanEMI;
								long totRecoveredAmt=isMultiRecovery ? recoveredPrinLoanAmt : recoveredPrinLoanAmt+loanEMI;
								//princiRecorerVo.setTotalRecoveredAmt(amt);
								long totRecoveredInst=isMultiRecovery ? prin_install_no : prin_install_no+1;
								//princiRecorerVo.setTotalRecoveredInst(new_no);
								//princiRecorerVo.setUpdatedDate(sysdate);
								//princiRecorerVo.setOrgPostMstByUpdatedByPost(orgPostMst);
								//princiRecorerVo.setOrgUserMstByUpdatedBy(orgUserMst);
								//hrLoanPrinRecoverDao.update(princiRecorerVo);


								loanEmi=loanEmi+loanEMI;

								//added by Ankit Bhatt
								//passing Loan object to GenerateBillService and we will insert into that method only
								//as this method should not contain any insert/update												

								paybillLoanDtlsId  = idGenerator.PKGenerator("HR_PAY_PAYBILL_LOAN_DTLS",objectArgs);
								logger.info("The generated bill Loan Id is:-"+paybillLoanDtlsId);
								hrPayPaybillLoanDtls = new HrPayPaybillLoanDtls();
								hrPayPaybillLoanDtls.setId(paybillLoanDtlsId);
								hrPayPaybillLoanDtls.setPaybillId(payBillVO);
								hrPayPaybillLoanDtls.setCmnDatabaseMst(cmnDatabaseMst);
								hrPayPaybillLoanDtls.setCmnLocationMst(cmnLocationMst);
								hrPayPaybillLoanDtls.setCreatedDate(new java.util.Date());
								
								HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
								hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
								hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
								
								hrPayPaybillLoanDtls.setRecoveryType(prinLookupType);
								
								HrLoanAdvMst loanAdvMst = new HrLoanAdvMst();
								loanAdvMst.setLoanAdvId(empPaybillLoanVO.getLoanAdvId());
								hrPayPaybillLoanDtls.setHrLoanAdvMst(loanAdvMst);
								
								hrPayPaybillLoanDtls.setOrgPostMstByCreatedByPost(orgPostMst);
								hrPayPaybillLoanDtls.setOrgUserMstByCreatedBy(orgUserMst);
								hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanPrinAmt());
								hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanPrinInstNo());
								hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
								hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
								if(INSERT_LOAN_FLAG.equalsIgnoreCase("Y"))
									paybillLoanDtlsDao.create(hrPayPaybillLoanDtls);
								else
									lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);

								//ended

							}
							else 
								//for interest Recovery amt
							{
								//intRecoverVO = new HrLoanEmpIntRecoverDtls();
								//empLoanIntList = hrLoanIntRecoverDao.getAllData(emp_loan_id);
								
								
								long intLoanAmt = empPaybillLoanVO.getLoanInterestAmt();
								recoveredIntLoanAmt = loanIntList.getRecoveredAmount();
								int_install_no=loanIntList.getRecoveredInst();
								if( isMultiRecovery )
								{
									int_install_no  = int_install_no + empPaybillLoanVO.getMulLoanInstRecvd();
									recoveredIntLoanAmt = recoveredIntLoanAmt + empPaybillLoanVO.getMulLoanAmtRecvd();
								}
								if(
									(isMultiRecovery && int_install_no <= tot_int_install && recoveredIntLoanAmt <= intLoanAmt)
									||
									(!isMultiRecovery && int_install_no < tot_int_install && recoveredIntLoanAmt < intLoanAmt)
									)
								{
									//loanEMI=hrLoanEmp.getLoanEmiAmt();//the emi value
									loanIntEMI=Math.round(isMultiRecovery ? empPaybillLoanVO.getMulLoanAmtRecvd() :  empPaybillLoanVO.getLoanIntEmiAmt());//the Interest emi value

								
									//Modified By Mrugesh. Start
									//if((empLoanList.get(0).getLoanIntInstNo() - empLoanIntList.get(0).getTotalRecoveredIntInst())==1)//If last installment then set EMI amount as per remaining recovered amount
									//{
									/*	if((loanIntEMI + empLoanIntList.get(0).getTotalRecoveredInt()) < empLoanIntList.get(0).getHrLoanEmpDtls().getLoanInterestAmt())
										{
											loanIntEMI = empLoanIntList.get(0).getHrLoanEmpDtls().getLoanInterestAmt() - empLoanIntList.get(0).getTotalRecoveredInt();
										}*/

									//added by manish as per Government of Maharashtra
									if(!isMultiRecovery){
										if(loanIntEMI>(intLoanAmt-recoveredIntLoanAmt))
										{
											loanIntEMI=intLoanAmt-recoveredIntLoanAmt;
										}
										logger.info("The loan EMI before last installment is------------>>>>>>"+loanEMI);
										if((loanIntList.getRecoveredInst()+1L)==empPaybillLoanVO.getLoanOddinstno())
										{
											loanEMI=loanIntList.getOddInstAmount();
											logger.info("The loan EMI After last installment is------------>>>>>>"+loanEMI);
	
										}
										else
										{
											loanEMI=empPaybillLoanVO.getLoanIntEmiAmt();
											logger.info("The loan EMI After last installment is------------>>>>>>"+loanEMI);
										}
									}else{
										loanEMI = empPaybillLoanVO.getMulLoanAmtRecvd();
									}
									//ended by manish
									logger.info("Last installement of loan - Interest so, deactivating the loan for Id " + empPaybillLoanVO.getEmpLoanId());									
									/*int rowsUpdated = changeActivateStatus(empLoanList.get(0).getEmpLoanId(),deactivate,serv);
										if(!deactivateLoanRecords.containsKey(payBillVO.getOrgPostMst().getPostId()))
										 deactivateLoanRecords.put(payBillVO.getOrgPostMst().getPostId(), payBillVO );
										logger.info("Rows updated for loan id " + empLoanList.get(0).getEmpLoanId() + " is " + rowsUpdated);*/
									//}
									//Modified By Mrugesh. End
									long totRecoveredAmt=isMultiRecovery ? recoveredIntLoanAmt : recoveredIntLoanAmt+loanEMI;
									//intRecoverVO.setTotalRecoveredInt(amt);
									long totRecoveredInst=isMultiRecovery ? int_install_no : int_install_no+1;
									//intRecoverVO.setTotalRecoveredIntInst(new_no);
									//intRecoverVO.setUpdatedDate(sysdate);
									//intRecoverVO.setOrgPostMstByUpdatedByPost(orgPostMst);
									//intRecoverVO.setOrgUserMstByUpdatedBy(orgUserMst);
									//hrLoanIntRecoverDao.update(intRecoverVO);
									//loanIntEmi=loanIntEmi+loanEMI;	
									loanIntEmi=loanEMI;
									/*if(fromView!=1)
									{*/

									// Added By Urvin Shah for HR_PAY_PAYBILL_LOAN_DTLS for Interest
									//List oldRecords = new ArrayList();
									List oldRecords = null;//LoanEmpDAO.getPaybillLoanDtls(empPaybillLoanVO.getEmpId(),empPaybillLoanVO.getLoanAdvId(),intLookupType.getLookupId() ,monthGiven,yearGiven);
									if(oldRecords!=null && !oldRecords.isEmpty()) {
										logger.info("Before fetching the object for Loan Interest:-");
										hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls)oldRecords.get(0);
										logger.info("After fetching the object for Loan interest:-"+hrPayPaybillLoanDtls.getId());
										//hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanInterestAmt());
										hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanIntInstNo());
										hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
										hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
										
										HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
										hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
										hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
										
										hrPayPaybillLoanDtls.setUpdatedDate(new java.util.Date());
										hrPayPaybillLoanDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
										hrPayPaybillLoanDtls.setOrgUserMstByUpdatedBy(orgUserMst);
										logger.info("The Paybill is already generated for the same employee so the record is updated");
										//modified by Ankit Bhatt
										if(INSERT_LOAN_FLAG.equalsIgnoreCase("Y"))
											paybillLoanDtlsDao.update(hrPayPaybillLoanDtls);
										else
											lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);

										//ended
									}
									else {
										paybillLoanDtlsId  = idGenerator.PKGenerator("HR_PAY_PAYBILL_LOAN_DTLS",objectArgs);
										logger.info("The generated bill Loan Id is:-"+paybillLoanDtlsId);
										hrPayPaybillLoanDtls = new HrPayPaybillLoanDtls();
										hrPayPaybillLoanDtls.setId(paybillLoanDtlsId);
										hrPayPaybillLoanDtls.setPaybillId(payBillVO);
										hrPayPaybillLoanDtls.setCmnDatabaseMst(cmnDatabaseMst);
										hrPayPaybillLoanDtls.setCmnLocationMst(cmnLocationMst);
										hrPayPaybillLoanDtls.setTotalAmt(empPaybillLoanVO.getLoanInterestAmt());
										hrPayPaybillLoanDtls.setTotalInst(empPaybillLoanVO.getLoanIntInstNo());
										hrPayPaybillLoanDtls.setCreatedDate(new java.util.Date());
										
										HrLoanEmpDtls hrLoanEmp = new HrLoanEmpDtls();
										hrLoanEmp.setEmpLoanId(empPaybillLoanVO.getEmpLoanId());
										hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
										
										hrPayPaybillLoanDtls.setHrLoanEmpDtls(hrLoanEmp);
										hrPayPaybillLoanDtls.setRecoveryType(intLookupType);
										
										HrLoanAdvMst loanAdvMst = new HrLoanAdvMst();
										loanAdvMst.setLoanAdvId(empPaybillLoanVO.getLoanAdvId());
										hrPayPaybillLoanDtls.setHrLoanAdvMst(loanAdvMst);
										
										hrPayPaybillLoanDtls.setOrgPostMstByCreatedByPost(orgPostMst);
										hrPayPaybillLoanDtls.setOrgUserMstByCreatedBy(orgUserMst);
										hrPayPaybillLoanDtls.setRecoveredAmt(totRecoveredAmt);
										hrPayPaybillLoanDtls.setRecoveredInst(totRecoveredInst);
										logger.info("The Loan Details are going to insert");
										//modified by Ankit Bhatt
										if(INSERT_LOAN_FLAG.equalsIgnoreCase("Y"))
											paybillLoanDtlsDao.create(hrPayPaybillLoanDtls);
										else
											lstHrPayLoanEmpDtls.add(hrPayPaybillLoanDtls);										
										//ended

										logger.info("The Loan Details inserted successfully");
										logger.info("The Loan Details inserted successfully");
										// End Urvin Shah.
									}
									//}
								}
							}

						}
					}	
				
			
			resultMap.put("lstHrPayLoanEmpDtls",lstHrPayLoanEmpDtls);
			resultMap.put("loanEmi", loanEmi);
			resultMap.put("loanIntEmi", loanIntEmi);
			resultMap.put("deactivateLoanRecords", deactivateLoanRecords);
		}
		catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			throw e;
		}
		return resultMap;
	}

	public int changeActivateStatus(long loanEmpId, int status,ServiceLocator serv) {
		logger.info("changing activate flag status of " + loanEmpId + " to " + status);		
		EmpLoanDAOImpl loanDAO = new EmpLoanDAOImpl(HrLoanEmpDtls.class,serv.getSessionFactory());
		return loanDAO.changeLoanStatus(loanEmpId, status);
	}
}
