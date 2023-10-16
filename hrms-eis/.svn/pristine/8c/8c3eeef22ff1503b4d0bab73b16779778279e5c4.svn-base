package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.ArrearBillDaoImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.PaybillRegMpgDAOImpl;
import com.tcs.sgv.eis.dao.SalRevMstDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class ArrearPaybillServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog( getClass() );
	
	public ResultObject approveArrearBill(Map objectArgs) {

		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		
		
		try
		{
			int monthGiven=-1; //-1 is default for current month 
			int yearGiven=-1; //-1 is default for current year
			// Added By Urvin Shah. For Setting up Approval Flag. 
			ResourceBundle rs = ResourceBundle.getBundle("resources.Payroll");
			long appFlag = Long.parseLong(rs.getString("approved"));
			long paid = Long.parseLong(rs.getString("paid"));
			// End By Urvin shah.
			
			//Added By Paurav
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
	        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			//Ended By Paurav
			
			Date sysdate = new Date();
			
			PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String category="";
			String Grade="";
			String subHeadId="";
			String dsgnId="";
			
			String givenBillNo="";
			
			if(voToService.get("billNo")!=null && !voToService.get("billNo").equals(""))
			{
				givenBillNo = voToService.get("billNo").toString();
			}
			
			if(voToService.get("category")!=null && voToService.get("category").toString()!="")
			{
				category = voToService.get("category").toString();
			}			
			if(voToService.get("Grade")!=null && voToService.get("Grade").toString()!="")
			{
				Grade = voToService.get("Grade").toString();
			}	
			if(voToService.get("dsgnId")!=null && voToService.get("dsgnId").toString()!="")
			{
				dsgnId = voToService.get("dsgnId").toString();
			}	
			if(voToService.get("subHeadId")!=null && voToService.get("subHeadId").toString()!="")
			{
				subHeadId = voToService.get("subHeadId").toString();
			}	
				
			if(voToService.get("month")!=null && !voToService.get("month").equals("") )
			{
			  monthGiven = Integer.parseInt(voToService.get("month").toString());
			  yearGiven = Integer.parseInt(voToService.get("year").toString());
			 logger.info("Given month and year for approve payslip is " + monthGiven + "--" + yearGiven);
			}
			logger.info(" givenBillNo" + givenBillNo + " -- category " + category+" Grade " + Grade + " -- dsgnId " + dsgnId+" subHeadId " + subHeadId );
			ArrearBillDaoImpl arrearBillDAO = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
			
			int currMonth=0;
			 int currYear=0;
			 

             if(monthGiven!=-1 && yearGiven!=-1)
			 {
				 currMonth=monthGiven;
				 currYear=yearGiven;
			 }
			 else
			 {
				 Date DBDate = DBUtility.getCurrentDateFromDB();
				 currMonth = DBDate.getMonth()+1;
				 currYear=DBDate.getYear()+1900;
				 logger.info("DB Month is " + currMonth + "\nCurrent year is " + currYear);				  				  	
			 }
			 
//			check whether there are already records for the given month and year
			 List arrearRecs= arrearBillDAO.getArrearBillData(currMonth,currYear,category,Grade,dsgnId,subHeadId,langId,givenBillNo);
			 
			 //Added by Paurav. Condition changed by Paurav for checking if any employees there for whom payslip not generated
			 if(arrearRecs==null || arrearRecs.size()<=0) //if(paySlipRecs!=null && paySlipRecs.size()>0)
			 {
				 	logger.info("No employees Found because there are no employees for the current month ");
				    throw new DuplicateRecordException();
		      }

		 else
		 {
			long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
	        logger.info("Loc Id is:-->" + dbId + " " + locId);
	       
	        
			 long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			 OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			 OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
						 						 			 
			  
			 
		  	long arrearbillId=0;
		  	logger.info("No of Employees for whom payslip is to be generated are " + arrearRecs.size());
		  	
		  	HrPayArrearPaybill arrearbill = new HrPayArrearPaybill();
		  	//Added By Mrugesh
		  	PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
		  	//Ended By Mrugesh
		  	long salRevId = 0;
		  	List salRevIdList = new ArrayList();
		  	
		  	for(int cnt =0;cnt<arrearRecs.size();cnt++)//for(Iterator it = payBillData.iterator();it.hasNext();)
		  	{
		  		
		  		arrearbillId=Long.parseLong(arrearRecs.get(cnt).toString());
		  		logger.info("+++++++++++++++++++++++the paybill id is "+arrearbillId);
		  		
		  		arrearbill = arrearBillDAO.read(arrearbillId);
		  		
		  		salRevId = arrearbill.getSalRevId().getSalRevId();
		  		logger.info("+++++++++++++++++++++++the salRevId id is "+salRevId);
		  		
		  		if(!salRevIdList.contains(salRevId))
		  		{
		  			logger.info("going to add in list "+salRevId);
		  			salRevIdList.add(salRevId);
		  		}
		  		//Modified By Mrugesh
		  		paybillHeadMpg.setApproveFlag(appFlag);
		  		//Ended By Mrugesh
		  		arrearbill.setApproveRejectDate(sysdate);
		  		
		  		logger.info("going to update ");
		  		arrearBillDAO.update(arrearbill);
		  		
			  	
			  	
		  	}	
		  	//	Approval of Paybill. By Urvin Shah.
		  	
		  	HrPaySalRevMst salRevMst = new HrPaySalRevMst();
		  	
		  	SalRevMstDAOImpl salRevDao = new SalRevMstDAOImpl(HrPaySalRevMst.class,serv.getSessionFactory());
		  	long newSalRevId =0;
		  	
		  	for(int i=0;i<salRevIdList.size();i++)
		  	{
			  	
		  		newSalRevId = (Long)salRevIdList.get(i);
		  		logger.info("+++++++++++++++++++++++the newSalRevId id is "+newSalRevId);
		  		salRevMst = salRevDao.read(newSalRevId);
			  	
			  	Date payoutDate = salRevMst.getRevPayOutDate();
			  	long freq = salRevMst.getRevFreqMthPaid();
			  	long installments = salRevMst.getRevInstallments();
			  	
			  	logger.info("the payoutDate is "+payoutDate+" and fre month "+freq+" and installments "+installments);
			  	
			  	GenerateBillService billService = new GenerateBillService();
			  	
			  	int payoutMonth = billService.monthofDate(payoutDate);
			  	int payoutYear = billService.yearofDate(payoutDate);
			  	
			  	logger.info("before adding frequ the end month is "+payoutMonth+" and year is "+payoutYear);
			  	payoutMonth +=installments-1;
			  	logger.info("after adding frequ the end month is "+payoutMonth+" and year is "+payoutYear);
			  	
			  	if(payoutMonth>=13)
			  	{
			  		payoutMonth = payoutMonth-12;
			  		payoutYear ++;
			  	}
			  	logger.info("the month is "+monthGiven+" and year is "+yearGiven);
			  	logger.info("after chking the end month is "+payoutMonth+" and year is "+payoutYear);
			  			  	
				 if(payoutMonth==monthGiven && payoutYear==yearGiven)
				 {
					 logger.info("the last month encountered. Going to update sal rev table ");
					 
					 salRevMst.setRevStatus(paid);
					 
					 salRevDao.update(salRevMst);
					 logger.info("sal rev mst update succesfully");
				 }
		  	}	 
		  	
		  	objectArgs.put("msg", "Records Have Been Approved.");
		  	
		  	objectArgs.put("status", "1");
		  	resultObject.setResultValue(objectArgs);
		  	resultObject.setViewName("payslipsucessMsg");
		 }
		}
		catch(DuplicateRecordException e)
		{
			objectArgs.put("msg", "Records For this month are already exist.");
			
			objectArgs.put("status", "11");
			
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");
			logger.error("Error is: "+ e.getMessage());
			
		}
		catch(MonthException e)
		{
			objectArgs.put("msg", "There is No Record For This Month in Paybill.");
			
			objectArgs.put("status", "12");
			
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");
			logger.error("Error is: "+ e.getMessage());
			
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...approvePayBill");
			objectArgs.put("status", "13");
			
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...approvePayBill");
			objectArgs.put("status", "13");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("payslipsucessMsg");				
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...approvePayBill");
			objectArgs.put("status", "13");
			
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("payslipsucessMsg");			
		}
		return resultObject;
	
	}
	public int getIncrementAmount(HrEisScaleMst hrEisScaleMst,long basicAmt){
		int incrementAmount=0;
		if(basicAmt>=hrEisScaleMst.getScaleStartAmt() && basicAmt < hrEisScaleMst.getScaleEndAmt())
			incrementAmount = (int)hrEisScaleMst.getScaleIncrAmt();
		else
			incrementAmount = (int) hrEisScaleMst.getScaleHigherIncrAmt();
		return incrementAmount;
	}
	public ResultObject getArrearBill(Map objectArgs)
	{

		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		try
		{
			ArrearBillDaoImpl arrearBillDAO = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
			
			 
//			check whether there are already records for the given month and year
			 List arrearRecs= arrearBillDAO.getArrearBillData();
			 
			 List arrearBillList = new ArrayList();
			 HrPayArrearPaybill arrearBill = new HrPayArrearPaybill();
			 //Added By Mrugesh
			 PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();
			 //Ended By Mrugesh
			 for(Iterator it = arrearRecs.iterator();it.hasNext();)
			 {
				 Object[] row = (Object[])it.next();
				 
				 arrearBill = new HrPayArrearPaybill();
				 
				 String billNo = (String)row[0];
				 long paybillGrpId = (Long)row[1];
				 long billMonth = (Long)row[2];
				 long billYear = (Long)row[3];
				 double grossAmt = (Double)row[4];
				 double netTotal = (Double)row[5];
				 HrPaySalRevMst salRevId = (HrPaySalRevMst)row[6];
				 
				 logger.info("ther bill no is "+billNo);
				 logger.info("ther groupId is "+paybillGrpId);
				 logger.info("ther billMonth is "+billMonth);
				 logger.info("ther billYear is "+billYear);
				 logger.info("ther grossAmt is "+grossAmt);
				 logger.info("ther netTotal is "+netTotal);
				 logger.info("ther salRevId is "+salRevId.getRevOrderNo()+ " and date is "+salRevId.getRevOrderDate());
				 
				 arrearBill.setBillNo(billNo);
				 arrearBill.setPaybillGrpId(paybillGrpId);
				 paybillHeadMpg.setMonth(billMonth);
				 paybillHeadMpg.setYear(billYear);
				 arrearBill.setGrossAmt(grossAmt);
				 arrearBill.setNetTotal(netTotal);
				 arrearBill.setSalRevId(salRevId);
				 
				 arrearBillList.add(arrearBill);
				 
			 }
			  	
			  	objectArgs.put("arrearBill", arrearBillList);
			  	resultObject.setResultValue(objectArgs);
			  	resultObject.setViewName("arrearBillView");
			 
			 //Added by Paurav. Condition changed by Paurav for checking if any employees there for whom payslip not generated
		} 
		catch(Exception e){
			
			logger.info("Exception Ocuures...approvePayBill");
			objectArgs.put("status", "13");
			
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setResultCode(-1);
			 resultObject.setViewName("payslipsucessMsg");			
		}
		return resultObject;
	
	}
	public ResultObject deleteArrearBill(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
	           logger.info("**************coming into deleteArrearBill****************" );
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
				long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);
				
				Integer outerBill  = (Integer)objectArgs.get("outer");
				String[] arrearGrpIds=null;
				
				
					Map voToService = (Map)objectArgs.get("voToServiceMap");
					arrearGrpIds = (String[]) voToService.get("chkArrearId");
				
				
				logger.info("the trnBillId "+arrearGrpIds+" the size is "+arrearGrpIds.length);
				
				Date approveRejectDate = new Date();
				
				ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");
				 
				 String logicalDelete = constantsBundle.getString("logicalDelete").trim();
				
				long delete = Long.parseLong(logicalDelete!=null?logicalDelete:"3");
				
				for(int i=0;i<arrearGrpIds.length;i++)
				{
					String arrearId = arrearGrpIds[i];
					logger.info("the arrearId "+arrearId);
					
					long arrearNo = (arrearId!=null?Long.parseLong(arrearId):0);
					
					ArrearBillDaoImpl arrearDao = new ArrearBillDaoImpl(HrPayArrearPaybill.class,serv.getSessionFactory());
					
					List arrearbillToBeDelete = arrearDao.getListByColumnAndValue("paybillGrpId",arrearNo);
					
					if(arrearbillToBeDelete!=null && arrearbillToBeDelete.size()>0)
					{
						for(int cnt=0;cnt<arrearbillToBeDelete.size();cnt++)
						{
							HrPayArrearPaybill arrearbill = (HrPayArrearPaybill) arrearbillToBeDelete.get(cnt);
							//Modified By Mrugesh
							//arrearbill.setApproveFlag(delete);//3 is for logical delete
							//Ended By Mrugesh
							arrearbill.setApproveRejectDate(approveRejectDate);
							logger.info("going to delete record");							
							arrearDao.update(arrearbill);
						}
					}
					
					//Added By Mrugesh
					PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class,serv.getSessionFactory());
					HrPayPaybill paybillVO = new HrPayPaybill();
					paybillVO.setId(arrearNo);
					List paybillHeadMpgList = paybillHeadMpgDAOImpl.getListByColumnAndValue("hrPayPaybill", paybillVO);
					logger.info("The list size is---->"+paybillHeadMpgList.size());
					 if(paybillHeadMpgList!=null && paybillHeadMpgList.size()>0)
						{
							for(int cnt=0;cnt<paybillHeadMpgList.size();cnt++)
							{
								PaybillHeadMpg paybillHeadMpg = (PaybillHeadMpg) paybillHeadMpgList.get(cnt);
								
								paybillHeadMpg.setApproveFlag(delete);//3 is for logical delete
								paybillHeadMpg.setUpdatedDate(approveRejectDate);
								logger.info("going to delete record from paybill head mapping bill");							
								paybillHeadMpgDAOImpl.update(paybillHeadMpg);
							}
						}
					//}
					//Ended By Mrugesh
					
					
				}
				
					resultObject.setResultCode(ErrorConstants.SUCCESS);
					resultObject=getArrearBill(objectArgs);
				
               
				
		}
		catch(Exception e)
		{
			resultObject.setResultCode(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
		}
			
		
	
		return resultObject;
	}
}
