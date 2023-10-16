package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
//import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.OuterVO;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;
//import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;

public class DisplayOuterServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog(getClass());

	public ResultObject getData(Map objectArgs)
	{
		logger.info("in getData service");

		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long locId=0;
		locId=Long.valueOf(loginMap.get("locationId").toString());

		try{
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long billNo = StringUtility.getParameter("billNo", request)!=null && !StringUtility.getParameter("billNo", request).equals("")?Long.parseLong(StringUtility.getParameter("billNo", request)):0;
			int month = StringUtility.getParameter("month", request)!=null && !StringUtility.getParameter("month", request).equals("")? Integer.parseInt(StringUtility.getParameter("month", request)):0;
			int year = StringUtility.getParameter("year", request)!=null && !StringUtility.getParameter("year", request).equals("") ?Integer.parseInt(StringUtility.getParameter("year", request)):0;

			long parentId = 340000L;
			CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List<CmnLookupMst> list = cmnDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(month),parentId} );
			String monthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(month);

			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
			
			
			
			String hbaLandPrinId= resourceBundle.getString("hbaForLandPrincipal");
			String hbaHousePrinId= resourceBundle.getString("hbaForHousePrincipal");
			String hbaLandIntId=resourceBundle.getString("hbaForLandInterest");
			String hbaHouseIntId=resourceBundle.getString("hbaForHouseInterest");
			
			
			String gpfGrpAbcId=resourceBundle.getString("GpfGrpAbcId");
			String gpfGrpDId=resourceBundle.getString("gpfGrpDId");
			String gpfAdvAbcId=resourceBundle.getString("gpfAdvAbcId");
			String gpfAdvDId=resourceBundle.getString("gpfAdvDId");
			
			

			String BillNumber = StringUtility.getParameter("BillNumber", request).trim();
			BillHeadMpgDAOImpl paybillHeadMpgDAOImpl=new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
			PayBillDAOImpl pbDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			if(BillNumber!=null && !BillNumber.equals(""))
			{
				long bill = Long.parseLong(BillNumber);
				
				List<MstDcpsBillGroup> billNoList =pbDao.getBillNumber(bill, finYearId, locId);
				billNo = billNoList.get(0).getDcpsDdoBillGroupId();
			}
			
    		//added by roshan
			logger.info("hhii the bill number"+billNo);
			locId=pbDao.getLocationCode(billNo);
			logger.info("hhi the location cxod eis "+locId);
			//ended by roshan

			///find paybillid from paybill and save as paybillNo
			logger.info("Bill number is "+billNo+" Generating outer for month "+ month +" year "+year);
			GenericDaoHibernateImpl<MstDcpsBillGroup,Long> genDao = new GenericDaoHibernateImpl<MstDcpsBillGroup,Long>(MstDcpsBillGroup.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			MstDcpsBillGroup hrPaybillSubHeadMpg =(MstDcpsBillGroup)genDao.read(billNo);



			MstScheme schemeMst = new MstScheme();
			if(hrPaybillSubHeadMpg!=null) {
				
				
				//Added by saurabh for subschemme
				String subSchemeCode=hrPaybillSubHeadMpg.getDcpsDdoSubSchemeCode();
				objectArgs.put("subSchemeCode",subSchemeCode);
				
				
				
				String schemeCode = hrPaybillSubHeadMpg.getDcpsDdoSchemeCode();
				List<MstScheme> schemeMstList=paybillHeadMpgDAOImpl.getMstScheme(schemeCode,locId);
				if(schemeMstList!=null && schemeMstList.size()>0)
					schemeMst = schemeMstList.get(0);
			}



			objectArgs.put("paybillNo",billNo);
			objectArgs.put("month", month);
			objectArgs.put("year",year);

			HrEdpComponentMpgDAOImpl edpDao = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
			DeptCompMPGDAOImpl compDao = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,serv.getSessionFactory());
			DeducTypeMasterDAOImpl deduDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			LoanAdvMstDAOImpl loanAdvDao = new LoanAdvMstDAOImpl(HrLoanAdvMst.class, serv.getSessionFactory());

			List<HrPayEdpCompoMpg> allowEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getAllowCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducAgEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getAGDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducTyEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getTRDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> advGrossEdpList = new ArrayList<HrPayEdpCompoMpg>();


			List<HrPayEdpCompoMpg> allEdpList = edpDao.getAllDataForOuter(); // for outer
			List<HrPayLocComMpg> locAllow = compDao.getDataAllowChckedForMonthYear(locId,month,year);
			List<HrPayLocComMpg> locDeduc = compDao.getDataDeductChckedForMonthYear(locId,month,year);
			List<HrPayLocComMpg> locLoan = compDao.getDataLoanChckedForMonthYear(locId,month,year);


			Map outerMap = new GenerateBillServiceHelper().getOuterValues(objectArgs);
			outerMap.put(" ", 0);
			long totalSalary=Long.parseLong(outerMap.get("0101~BSC").toString());
			long totalA=0,totalB=0,totalLoan = 0,totalLoanTry =0;

			long gpf_d=0;
			long gpf_abc_val=0; 
			logger.info("outerMap-->"+outerMap);
			//started by manish
			boolean hbaLandPrin=false;
			boolean hbaHousePrin=false;
			int rempveObjectPrin=0;
			String edpCodeForHbaLandPri="5051";
			
			for(int i=0;i<locLoan.size();i++)
			{
				if(new Long(locLoan.get(i).getCompId()).toString().equals(hbaLandPrinId))
				{
					hbaLandPrin=true;
					rempveObjectPrin=i;
					logger.info("going to remove object with index value from loanList"+i);
				}
				if(new Long(locLoan.get(i).getCompId()).toString().equals(hbaHousePrinId))
					hbaHousePrin=true;
				
			}
			if(hbaHousePrin==true && hbaLandPrin == true)
			{
				locLoan.remove(rempveObjectPrin);
			}
			logger.info("size of loanList after removing hbaLandObject in outer is   "+locLoan.size());
			boolean hbaLandInt=false;
			boolean hbaHouseInt=false;
			int rempveObjectInt=0;
			String edpCodeForHbaLandInt="5051";
			
			for(int i=0;i<locLoan.size();i++)
			{
				if(new Long(locLoan.get(i).getCompId()).toString().equals(hbaLandIntId))
				{
					hbaLandInt=true;
					rempveObjectInt=i;
					logger.info("going to remove object with index value in outer is "+i);
				}
				if(new Long(locLoan.get(i).getCompId()).toString().equals(hbaHouseIntId))
					hbaHouseInt=true;
				
			}
			if(hbaLandInt==true && hbaHouseInt == true)
			{
				locLoan.remove(rempveObjectInt);
			}
			logger.info("size of loanListNew after removing hbaLandObject in outer is "+locLoan.size());
			
			boolean gpfGrpAbc=false;
			boolean gpfGrpD=false;
			int gpfAbcRemoveObject=0;
			int gpfDRemoveObject=0;
			boolean gpfAdvAbc=false;
			boolean gpfAdvD=false;
			String gpfGrpAbcEdpCode="9780";
			String gpfGrpDEdpCode="9583";
			String tempCoHsgKeyName = "";
			long tempCoHsgValue= 0;
			for(int i=0;i<locDeduc.size();i++)
			{
				if(new Long(locDeduc.get(i).getCompId()).toString().equals(gpfGrpAbcId))
				{
					gpfGrpAbc=true;
					gpfAbcRemoveObject=i;
					logger.info("going to remove object with index value in outer for gpf abc  is "+i);
				}
				
			}
			/*if(new Long(locDeduc.get(i).getCompId()).toString().equals(gpfGrpDId))
			{
				gpfGrpD=true;
				gpfDRemoveObject=i;
				logger.info("going to remove object with index value in outer for gpf d is "+i);
			}*/
			for(int i=0;i<locLoan.size();i++)
			{
				if(new Long(locLoan.get(i).getCompId()).toString().equals(gpfAdvAbcId))
					gpfAdvAbc=true;
				if(new Long(locLoan.get(i).getCompId()).toString().equals(gpfAdvDId))
					gpfAdvD=true;
				
			}
			if(gpfAdvAbc==true && gpfGrpAbc == true)
			{
				locDeduc.remove(gpfAbcRemoveObject);
			}
			logger.info("size of locDeduc after removing gpfAdvAbc in outer is "+locDeduc.size());
			for(int i=0;i<locDeduc.size();i++)
			{
				if(new Long(locDeduc.get(i).getCompId()).toString().equals(gpfGrpDId))
				{
					gpfGrpD=true;
					gpfDRemoveObject=i;
					logger.info("going to remove object with index value in outer for gpf d is "+i);
				}
				
			}
			if(gpfAdvD==true && gpfGrpD == true)
			{
				
				locDeduc.remove(gpfDRemoveObject);
				
			}
			logger.info("size of locDeduc after removing gpfAdvD in outer is "+locDeduc.size());
			
			//ended by manish 

			
			for(int i=0;i<allEdpList.size();i++)
			{
				if(allEdpList.get(i).getTypeId()!=null)
				{
					if(allEdpList.get(i).getCmnLookupId()==2500134)
					{
						for(int j=0;j<locAllow.size();j++)
						{
							if(allEdpList.get(i).getTypeId().equals(""+locAllow.get(j).getCompId()))
							{
								logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+" ---> allow name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								allowEdpList.add(allEdpList.get(i));
								totalSalary+=outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~EXP")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~EXP").toString()):0;
								break;
							}
						}
					}
					else if(allEdpList.get(i).getCmnLookupId()==2500135)
					{
						for(int j=0;j<locDeduc.size();j++)
						{
						//	logger.info("locDeduc.get(j) class " + locDeduc.get(j).getClass().getName());
							if(allEdpList.get(i).getTypeId().equals(""+locDeduc.get(j).getCompId()))
							{
								HrPayDeducTypeMst ded =deduDao.read(locDeduc.get(j).getCompId());
								if(ded.getDeductionBy().getLookupId()==2901424)
								{
									logger.info("deduc by AG "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+" ---> deduc name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									
									
									HrPayEdpCompoMpg h=allEdpList.get(i);
									String key = "";
									if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~RCP"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCP";
									else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~REC"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC";
									/*else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~RCPNA"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCPNA";
									else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~RCPGA"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCPGA";
									else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~RCPND"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCPND";
									else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+"~RCPGD"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCPGD";*/
									else
										key=" ";
									logger.info("key by kinjal "+key);
									/*if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals(""+9583))
									{
											gpf_d = outerMap.get(key)!=null?Long.parseLong(outerMap.get(key).toString().trim()):0;
											logger.info("gpf _d "+gpf_d);
											
									}
									else if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals(""+9780) )
									{
											gpf_abc_val = outerMap.get(key)!=null?Long.parseLong(outerMap.get(key).toString().trim()):0;
											logger.info("gpf_abc "+gpf_abc_val);
											logger.info("Key name for GPF_ABC is " + key);
									}
									else
									{*/
									logger.info("allEdpList.get(i)-------- "+allEdpList.get(i));
										deducAgEdpList.add(allEdpList.get(i));
										logger.info("deducAgEdpList-------- "+deducAgEdpList);
										totalA+=outerMap.get(key)!=null?Long.parseLong(outerMap.get(key).toString().trim()):0;
										logger.info("totalA-------- "+totalA);
									//}
								}
								else if (ded.getDeductionBy().getLookupId()==2901425)
								{
									logger.info("deduc by TRA "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									
									deducTyEdpList.add(allEdpList.get(i));
									String key = "";
									if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCP"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~RCP";
									else if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC"))
										key=allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC";
									else
										key=" ";

									totalB+=outerMap.get(key)!=null?Long.parseLong(outerMap.get(key).toString()):0;
								}
								else
								{

								}
								break;
							}
						}
					}
					else if(allEdpList.get(i).getCmnLookupId()==2500136||allEdpList.get(i).getCmnLookupId()==2500137)
					{
						for(int j=0;j<locLoan.size();j++)
						{
							if(allEdpList.get(i).getTypeId().equals(""+locLoan.get(j).getCompId()))
							{
								HrLoanAdvMst hrLoanAdvMst = loanAdvDao.read(locLoan.get(j).getCompId());
								if(hrLoanAdvMst.getDisplayGroup().getLookupId() == 2500383)
								{
									logger.info("in gross loop for "+allEdpList.get(i).getRltBillTypeEdp().getEdpShortName() );
									logger.info("Loan "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+" ---> Loan name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									advGrossEdpList.add(allEdpList.get(i));
									totalLoan+=outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0;
									break;
								}
								else if(hrLoanAdvMst.getDisplayGroup().getLookupId() == 2500381)
								{
									logger.info("Loan "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+" ---> Loan name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									deducTyEdpList.add(allEdpList.get(i));
									if(outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT") && locLoan.get(j).getCompId() != 61)
									{
										logger.info("Inside my if ");
										logger.info(" value to be addes is "+outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT").toString()):0);
										
										if( new Long(locLoan.get(j).getCompId()).toString().equals(hbaHouseIntId) && hbaHouseInt==true && hbaLandInt==true)
										{
											long hbaInterest=0;
											hbaInterest=(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT").toString()):0);
											logger.info("hbaInterest before cubbing is "+hbaInterest);
											hbaInterest+=(outerMap.get(edpCodeForHbaLandInt+"~INT")!=null?Long.parseLong(outerMap.get(edpCodeForHbaLandInt+"~INT").toString()):0);
											logger.info("hbaInterest after  cubbing is "+hbaInterest);
											outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT",hbaInterest);
										}
										totalB+=outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT").toString()):0;
										logger.info("TOTAL B After Adding INT Loan is"+totalB);
										//Removing Co-Hsg-Soc Int  So that Principal Loan Under Treasury Can be calculated
											if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals("5061") && outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC"))
											{
													tempCoHsgKeyName = ""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim() + "~INT";
													tempCoHsgValue = outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT").toString()):0;
													outerMap.remove(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~INT");
												
											}
																		
									}
									else if (outerMap.containsKey(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC"))
										{
										logger.info("Inside my esle if ");
										totalB+=outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0;
										logger.info("value t obe added is "+ outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										logger.info("TOTAL B After Adding REC Loan is"+totalB);
										//Putting Co-Hsg-Soc Int back into Map
											if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals("5061") && tempCoHsgKeyName != "")
											{
												outerMap.put(tempCoHsgKeyName,tempCoHsgValue);
												
											}
										
										}
									else
										logger.info("Done");
									break;
								}
								else if(hrLoanAdvMst.getDisplayGroup().getLookupId() == 2500382)
								{
									
									logger.info("Loan "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+" ---> Loan name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									deducAgEdpList.add(allEdpList.get(i));
									logger.info(" value to be added in totalA is "+outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
									
									/*if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals(""+5055))
									{
										logger.info("gpf_adv_d "+outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										long tempGpfD = (outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0)+gpf_d;
										logger.info(" tempGpfD "+ tempGpfD);
										outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC",tempGpfD);
									}
									if(allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim().equals(""+5054))
									{
										logger.info("gpf_adv_abc 1234 "+outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										
										String key=9780+"~RCP";
										long tempGpfrtu=(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										long tempGpfAbc= outerMap.get(key)!=null?Long.parseLong(outerMap.get(key).toString().trim()):0;
										tempGpfAbc+=tempGpfrtu;
										
										logger.info(" tempGpfAbc 123456 "+ gpf_abc_val);
										logger.info(" tempGpfAbc 123456 "+ tempGpfAbc);
										outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC",tempGpfAbc);
									}*/
									if( new Long(locLoan.get(j).getCompId()).toString().equals(gpfAdvAbcId) && gpfAdvAbc==true && gpfGrpAbc == true)
									{
										long gpfAdvAbcMerged=0;
										gpfAdvAbcMerged=(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										logger.info("gpfAdvAbc before cubbing is "+gpfAdvAbcMerged);
										if(outerMap.containsKey(gpfGrpAbcEdpCode+"~REC"))
											gpfAdvAbcMerged+=(outerMap.get(gpfGrpAbcEdpCode+"~REC")!=null?Long.parseLong(outerMap.get(gpfGrpAbcEdpCode+"~REC").toString()):0);
										if(outerMap.containsKey(gpfGrpAbcEdpCode+"~RCP"))
											gpfAdvAbcMerged+=(outerMap.get(gpfGrpAbcEdpCode+"~RCP")!=null?Long.parseLong(outerMap.get(gpfGrpAbcEdpCode+"~RCP").toString()):0);
										
										logger.info("gpfAdvAbc after  cubbing is "+gpfAdvAbcMerged);
										outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC",gpfAdvAbcMerged);
									}
									if( new Long(locLoan.get(j).getCompId()).toString().equals(gpfAdvDId) && gpfAdvD==true && gpfGrpD == true)
									{
										long gpfAdvDMerged=0;
										gpfAdvDMerged=(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										logger.info("gpfAdvD before cubbing is "+gpfAdvDMerged);
										if(outerMap.containsKey(gpfGrpDEdpCode+"~REC"))
											gpfAdvDMerged+=(outerMap.get(gpfGrpDEdpCode+"~REC")!=null?Long.parseLong(outerMap.get(gpfGrpDEdpCode+"~REC").toString()):0);
										if(outerMap.containsKey(gpfGrpDEdpCode+"~RCP"))
											gpfAdvDMerged+=(outerMap.get(gpfGrpDEdpCode+"~RCP")!=null?Long.parseLong(outerMap.get(gpfGrpDEdpCode+"~RCP").toString()):0);
										logger.info("gpfAdvD after  cubbing is "+gpfAdvDMerged);
										outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC",gpfAdvDMerged);
									}
									
									
									if( new Long(locLoan.get(j).getCompId()).toString().equals(hbaHousePrinId) && hbaLandPrin==true && hbaHousePrin == true)
									{
										long hbaPrincipal=0;
										hbaPrincipal=(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0);
										logger.info("HbaPrincipal before cubbing is "+hbaPrincipal);
										if(outerMap.containsKey(edpCodeForHbaLandPri+"~REC"))
										hbaPrincipal+=(outerMap.get(edpCodeForHbaLandPri+"~REC")!=null?Long.parseLong(outerMap.get(edpCodeForHbaLandPri+"~REC").toString()):0);
										logger.info("HbaPrincipal after  cubbing is "+hbaPrincipal);
										outerMap.put(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC",hbaPrincipal);
									}
									
								
									totalA+=outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC")!=null?Long.parseLong(outerMap.get(""+allEdpList.get(i).getRltBillTypeEdp().getEdpCode().trim()+"~REC").toString()):0;
									break;
								}

							}
						}
					}

				}

			}
			
			if(!outerMap.containsKey(tempCoHsgKeyName)){
				outerMap.put(tempCoHsgKeyName, tempCoHsgValue);
			}
			//logger.info("size is ++++"+allowEdpList.size()+"  "+deducAgEdpList.size()+"  "+deducTyEdpList.size());
			//logger.info("size of gross list is"+advGrossEdpList.get(1));
			logger.info("size of gross list is"+advGrossEdpList.size());
			//logger.info("size of gross list is"+advGrossEdpList.size());
			logger.info(" total of list size is "+totalLoan);
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String officeDetails=payBillDAOImpl.getOffice(locId);


			//for below table

			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<HrPayPaybill>  paybillVoList = payDao.getBillData( locId,  billNo,  month,  year);

			int[] grpCount = new int[5];
			long[] empNetCount = new long[5];


			int[]	vacantCount=new int[5];

			logger.info("size of paybillVoList is "+paybillVoList.size());
						
			for(HrPayPaybill payBillVo: paybillVoList)
			{
				int index =0;
				if(payBillVo.getHrEisOtherDtls()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg()!=null &&payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst()!=null)
					{index = Integer.parseInt(payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeCode());

				grpCount[index-1]++;
				empNetCount[index-1]+=payBillVo.getNetTotal();
					}
				if(payBillVo.getHrEisEmpMst()==null )
				{
					//index = Integer.parseInt(payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeCode());
					
					OrgPostDetailsRlt detailsRlt = payBillDAOImpl.getOrgPostDtlsObj(payBillVo.getOrgPostMst().getPostId());
					long  lookUpId = payBillDAOImpl.getOrgGradeMst(detailsRlt.getOrgDesignationMst().getDsgnId(), locId);
					
					/*CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
					CmnLookupMst cmnLookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpIDAndLang(lookUpId, 1);
					*/
					if(lookUpId == 700057)
						index=1;
					if(lookUpId == 700058)
						index=2;
					if(lookUpId == 700059)
						index=3;
					if(lookUpId == 700060)
						index=4;

					if(lookUpId == 700061)
						index=5;
					vacantCount[index-1]+=1;
					logger.info("payBillVo.getHrEisEmpMst() is null for post id "+payBillVo.getOrgPostMst().getPostId());
				}
			}

			for(int p=0;p<5;p++)
			{
				vacantCount[p]=vacantCount[p]+grpCount[p];
			}

			
			OuterVO outerVo = new OuterVO();
			outerVo.setAllowAdvEdpCompoList(advGrossEdpList);
			outerVo.setAllowEdpCompoList(allowEdpList);
			outerVo.setDedycAgEdpCompoList(deducAgEdpList);
			outerVo.setDedycTryEdpCompoList(deducTyEdpList);
			
		    List<HrPayEdpCompoMpg> deducTryEdpCompoList=outerVo.getDedycTryEdpCompoList();
		    List<String> headAccNoList=new ArrayList<String>(deducTryEdpCompoList.size());
		    for (int i=0;i<=deducTryEdpCompoList.size()-1;i++)
		    {
		    	
		    	if(deducTryEdpCompoList.get(i).getHeadOfAcc()!=0)
		    	{
		    		//String.format("%010ld", deducTryEdpCompoList.get(i).getHeadOfAcc());
		    		String tempAccNo=String.valueOf(deducTryEdpCompoList.get(i).getHeadOfAcc());
		    		tempAccNo=String.format("%10s", tempAccNo).replace(' ', '0'); 
		    		headAccNoList.add(tempAccNo);
		    		logger.info("Account No is"+tempAccNo); 
		    	}
		    	else
		    		headAccNoList.add(" ");		    	
		    				
			}
		    List<String> headAccNoListNew=new ArrayList<String>(deducTryEdpCompoList.size());
		    for (int i=0;i<=deducTryEdpCompoList.size()-1;i++)
		    {
		    	
		    	if(deducTryEdpCompoList.get(i).getHeadOfAcc()!=0)
		    	{
		    		//String.format("%010ld", deducTryEdpCompoList.get(i).getHeadOfAcc());
		    		String tempAccNo=String.valueOf(deducTryEdpCompoList.get(i).getHeadOfAcc());
		    		tempAccNo=String.format("%10s", tempAccNo).replace(' ', '0'); 
		    		String newtempAccNo = tempAccNo.substring(0,4);
		    		headAccNoListNew.add(newtempAccNo);
		    		logger.info("Account newtempAccNo is"+newtempAccNo); 
		    	}
		    	else
		    		headAccNoListNew.add(" ");		    	
		    				
			}
		    
		    
		    
		    
			Date date = new Date();
			outerVo.setDate(date);
			outerVo.setMstScheme(schemeMst);

			outerVo.setBillId(billNo);
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst locationMst=cmnLocationMstDaoImpl.read(hrPaybillSubHeadMpg.getLocId());
			outerVo.setCmnlocationMst(locationMst);
			outerVo.setMonth(monthName);
			outerVo.setYear(year);
			outerVo.setTotalSalary(totalSalary);
			outerVo.setGross(totalSalary-totalLoan);
			outerVo.setTotalA(totalA);
			outerVo.setTotalB(totalB);
			outerVo.setTotalDed((totalA+totalB));
			outerVo.setNetPay(totalSalary-(totalA+totalB)-totalLoan);
			//logger.info("outerMap in service"+outerMap);
			outerVo.setOuterMap(outerMap);
			outerVo.setGradeCount(grpCount);
			outerVo.setVacantCount(vacantCount);
			outerVo.setTotalCount(empNetCount);

			long loggedInpostId = Long.parseLong(loginMap.get("loggedInPost").toString());
			
			/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
			String ddocode ="";
			if(ddoList.size()>0)
				ddocode = ddoList.get(0).getDdoCode();*/

			String TresuryName ="";
			String TresuryCode ="";
			
			
			String ddocode ="";
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
    		if(ddoCodeList!=null)
    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
    		
    		OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddocode=ddoMst.getDdoCode();
    		}
    		logger.info("DDO Code is " + ddocode);
    		
    		
			List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);

			if(TreasuryList!=null && TreasuryList.size()!=0)
			{
				logger.info("hii i m inside....******");
				for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						logger.info("hii i m inside....******rowList[0]"+rowList[0]);
						TresuryName = rowList[0].toString().trim();
						logger.info("hii i m inside....******TresuryName"+TresuryName);
					}
					if(rowList[1] != null)
					{
						logger.info("hii i m inside....******rowList[1]"+rowList[1]);
						TresuryCode = rowList[1].toString().trim();
						logger.info("hii i m inside....******TresuryCode"+TresuryCode);
					}

				}
			}
			Calendar cal = new GregorianCalendar();
			int calMonth = cal.get(Calendar.MONTH);
			int calYear = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			int millysecond = cal.get(Calendar.MILLISECOND);

			//String dated=day + "/" + (month + 1) + "/" + year;
			String verificationDate=year + "-" + (month) + "-" + day + " " + hour +":" +minute+":"+second+"."+millysecond;
			
			  Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			  logger.info("10. "+format.format(now));
			  
			objectArgs.put("HeadAccNoList", headAccNoList);
			objectArgs.put("HeadAccNoListNew", headAccNoListNew);
			objectArgs.put("TresuryCode", TresuryCode);
			objectArgs.put("TresuryName", TresuryName);
			objectArgs.put("netPayInWordsPlusOne", ConvertNumbersToWord.convert(outerVo.getNetPay()+1));
			objectArgs.put("netPayInWords", ConvertNumbersToWord.convert(outerVo.getNetPay()));
			objectArgs.put("deducAgInWords", ConvertNumbersToWord.convert(outerVo.getTotalA()));
			objectArgs.put("deducTryInWords", ConvertNumbersToWord.convert(outerVo.getTotalB()));
			objectArgs.put("deducTotalInWords", ConvertNumbersToWord.convert(outerVo.getTotalDed()));
			objectArgs.put("outerVo", outerVo);
			objectArgs.put("officeDetails", officeDetails);
			objectArgs.put("ddocode", ddocode);
			
			String GeneratedDate = payBillDAOImpl.getBillGeneratedDate(billNo, month, year, locId);
			//SimpleDateFormat sd = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
			//String formatedDate = sd.format(GeneratedDate);
			logger.info("generatedDate*******" + GeneratedDate);

			objectArgs.put("generatedDate", GeneratedDate);
			objectArgs.put("VerificationDate", format.format(now));

			String DeptName=payBillDAOImpl.getOffice(locId);
			String dsgnNameOfDDO =payBillDAOImpl.getDsgnNameForFormB(locId,loggedInpostId);
			objectArgs.put("DeptName", DeptName);
			objectArgs.put("dsgnNameOfDDO",dsgnNameOfDDO);
			
			//Added by Abhilash for dsgn and order names display purpose
			long paybillGroupId = payBillDAOImpl.getPaybillgrpIdForOuter(billNo, month, year, locId);
			
			logger.info("for outer paybillGroupId is..... " + paybillGroupId);
			GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
					CmnLocationMst.class);
			gen.setSessionFactory(serv.getSessionFactory());
			CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locId);

			long parentLocId = cmnLocMst.getParentLocId();
			List outerList = payBillDAOImpl.getDsgnAndOrderNamesForOuter(billNo, month, year, locId,paybillGroupId, parentLocId);
			long designationId =0;
			String dsgnName ="";
			String orderName ="";
			String orderDate="";
			String postendDate="";
			String sanctionedupto="sanctioned upto";
			String fullDsgnNameAndOrderNo="";
			List outerCustomVOList = new ArrayList();
			
			//if(outerList.size()>0)
				
			for(Iterator iterator = outerList.iterator();iterator.hasNext();)
			{
				Object[] row = (Object[])iterator.next();
				OuterVO outerVoForOuter = new OuterVO();
				designationId = Long.parseLong(row[0].toString());
				dsgnName = row[1].toString();
				orderName = row[2].toString();
				orderDate = row[3].toString();
				if(row[4] != null)
				postendDate = row[4].toString();
				logger.info("before changing orderDate**********"+orderDate);
				String b[] = orderDate.split("-");
				long Year = Long.parseLong(b[0]);
				String Month = b[1];
				
				String Date= b[2];
				logger.info("before spilitting date************"+Date);
				String date1[] =Date.split(" ");
				logger.info("after spilitting date************"+date1[0]);
				Date = date1[0];
				orderDate=Date+"/"+Month+"/"+Year;
				
                logger.info("After changing postendDate**********"+orderDate);
				
				if(row[4] != null && !row[4].equals(""))
				{
					logger.info("before changing orderDate**********"+postendDate);
					String c[] = postendDate.split("-");
					long Year1 = Long.parseLong(c[0]);
					String Month1 = c[1];
					
					String Date1= c[2];
					logger.info("before spilitting date************"+Date1);
					String date2[] =Date1.split(" ");
					logger.info("after spilitting date************"+date2[0]);
					Date1 = date2[0];
					postendDate=Date1+"/"+Month1+"/"+Year1;
					logger.info("After changing postendDate**********"+postendDate);
				}
				
				logger.info("After changing orderDate**********"+orderDate);
				fullDsgnNameAndOrderNo = dsgnName + "-" + sanctionedupto + "-" + orderName;
				outerVoForOuter.setDsgnName(dsgnName);
				outerVoForOuter.setOrderName(orderName);
				outerVoForOuter.setOrderDate(orderDate);
				outerVoForOuter.setFullDsgnNameAndOrderNo(fullDsgnNameAndOrderNo);
				outerVoForOuter.setPostendDate(postendDate);
				outerCustomVOList.add(outerVoForOuter);
			}
			
			//Added by Kinjal for Dynamic outer
			List gpfBifurcatedList = payBillDAOImpl.getGPFBifurcatedlist(paybillGroupId, year, month);
			objectArgs.put("gpfBifurcatedList", gpfBifurcatedList);
			
			objectArgs.put("outerCustomVOList", outerCustomVOList);
			objectArgs.put("outerCustomVOListSize", outerCustomVOList.size());
			

			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("mtr19Outer");
			//changed for ZP
			resultObject.setViewName("zpOuter");
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.info("Exception Occurrs in getData Method of DisplayOuterServiceImpl..Exception is "+e);
		}

		return resultObject;
	}
	public ResultObject getBillMonthYearData(Map ObjectArgs)
	{
		//logger.info("in get bill month year data ");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
		try
		{
			Map loginDetailsMap = (Map) ObjectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			HttpServletRequest request = (HttpServletRequest) ObjectArgs.get("requestObj");
			CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
			List lMonthList = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("Month", langId);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long gisTypes = Long.parseLong(resourceBundle.getString("gisTypes"));
			long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());

			String nextPage = StringUtility.getParameter("nextPage", request);
			String actionName = resourceBundle.getString(nextPage);

			//EmpCompMpgDAOImpl empCompMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,serv.getSessionFactory());
			/*List billNoList = empCompMpg.getBillNo(finYearId,locId);*/
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List billNoList = payBillDAO.getBillNoOuter(finYearId,locId);

			List<CmnLookupMst> GisList = (List) cmnLookupMstObj.getAllChildren("GIS Type");
			logger.info("GIS List size is"+GisList.size());
			GisList = (List) cmnLookupMstObj.getListByColumnAndValue("parentLookupId", gisTypes);
			logger.info("GIS List size after is"+GisList.size());

			//----------to get year for dropdown---------------------		
			ArrayList lArrYrLst=new ArrayList();
			Calendar currentCal = Calendar.getInstance();
			int year = currentCal.get(Calendar.YEAR);

			for(int yr=2008;yr<=year;yr++)
			{
				lArrYrLst.add(yr);
			}

			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
			String yr = fmt1.format(today);
			fmt1 =new SimpleDateFormat("MM");

			String month = fmt1.format(today);

			if(month.charAt(0)=='0')
			{
				month=month.substring(1,2);
			}

			//logger.info("===> curmonth ::"+month);
			//logger.info("===> yr ::"+yr);

			ObjectArgs.put("curMonth", month);
			ObjectArgs.put("curyear",yr);
			/*logger.info("=====> in service getBillMonthYearData billNoList :: "+billNoList.size());
			logger.info("=====> in service getBillMonthYearData lMonthList :: "+lMonthList.size());
			logger.info("=====> in service getBillMonthYearData lArrYrLst :: "+lArrYrLst.size());*/

			ObjectArgs.put("billNoList",billNoList);
			ObjectArgs.put("lMonthList",lMonthList);
			ObjectArgs.put("lArrYrLst",lArrYrLst);

			ObjectArgs.put("actionName", actionName);
			ObjectArgs.put("gisList", GisList);

			resultObject.setViewName("ViewOuterFirst");
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(ObjectArgs);

			//logger.info("View ViewOuterFirst SUCCESSFULLY");

		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.info("Exception Occurrs in getBillMonthYearData Method of DisplayOuterServiceImpl..Exception is "+e);
		}
		return resultObject;

	}



}
