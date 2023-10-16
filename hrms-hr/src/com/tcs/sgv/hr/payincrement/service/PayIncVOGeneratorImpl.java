package com.tcs.sgv.hr.payincrement.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.HrPayincTxn;

public class PayIncVOGeneratorImpl extends ServiceImpl implements VOGeneratorService, PayIncVOGenerator{



	Log logger = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncVOGenerator#generateMap(java.util.Map)
	 */
	public ResultObject generateMap(Map objectArgs) 
	{

		String Year=null;
		String Month=null;
		String Designation=null;
		String dname =null;
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);

			try
			{
				if (objRes == null || objectArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();

			}


			//ServiceLocator servLoc= (ServiceLocator) objectArgs.get("serviceLocator");

			Year = StringUtility.getParameter("Year",request);
			Month = StringUtility.getParameter("Month",request);
			Designation = StringUtility.getParameter("Designation",request);
			dname =StringUtility.getParameter("dname",request);
			String date = 01+"/"+Month+"/"+Year;
			String date2=31+"/"+Month+"/"+Year;
			
			//_____________________________________________________

			if(Designation!=null){
				OrgDesignationMst orgDesignationMst=new OrgDesignationMst();


				orgDesignationMst. setDsgnName(Designation);

				objectArgs.put("OrgDesignationMst",orgDesignationMst);
			}

			// String date = Year+"-"+Month+"-"+ "01";
			
			
			
			CmnLookupMst cmnLookupMst =new CmnLookupMst();
			cmnLookupMst.setLookupName(date);
			cmnLookupMst.setLookupDesc(date2);
			objectArgs.put("CmnLookupMst",cmnLookupMst);
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncVOGenerator#Display(java.util.Map)
	 */
	public ResultObject Display(Map objectArgs) {

		int empId=0;

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);


		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);

			try
			{
				if (objRes == null || objectArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();

			}

			empId= Integer.parseInt(  StringUtility.getParameter("empId",request));
			String	dname =  StringUtility.getParameter("dname",request);
			HrEisEmpMst   hreisempmst= new HrEisEmpMst();
			OrgDesignationMst orgDesignationMst = new OrgDesignationMst();
			orgDesignationMst.setDsgnName(dname);
			hreisempmst.setEmpId(empId);
			objectArgs.put("OrgDesignationMst",orgDesignationMst);
			objectArgs.put("HrEisEmpMst",hreisempmst);

			
			
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	


	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncVOGenerator#transaction(java.util.Map)
	 */
	public ResultObject transaction(Map objectArgs) {



		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);


		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);

			try
			{
				if (objRes == null || objectArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
			}

			catch (Exception e)
			{
				e.printStackTrace();

			}

			long empId= Integer.parseInt(  StringUtility.getParameter("check",request));
             
            


			HrEisPayInc eisPayinc= new HrEisPayInc();
			

		     eisPayinc.setUserId(empId);
			objectArgs.put("HrEisPayInc",eisPayinc);


			

			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;

	}

	   
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncVOGenerator#UpdateMstt(java.util.Map)
	 */
	public ResultObject UpdateMstt(Map objectArgs) {
		
		
		HrPayincTxn hrEisPayincTran =new HrPayincTxn();
		

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		Date sysDate = new Date ();
		 
       
		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);

			try
			{
				if (objRes == null || objectArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
				
			}

			catch (Exception e)
			{
				e.printStackTrace();

			}
			
			String YES=StringUtility.getParameter("YES",request);
			String SystemComp=StringUtility.getParameter("SystemComp",request);
			String userComp=StringUtility.getParameter("userComp",request);
			
			
			if(SystemComp.equalsIgnoreCase("1")){
          
            long SystemBasic =Integer.parseInt(  StringUtility.getParameter("SystemBasic",request));
            
		   
		    Date  Systemdate =StringUtility.convertStringToDate(StringUtility.getParameter("Systemdate",request));
		   
			HrEisPayInc hrEisPayInc =new HrEisPayInc();
			
			hrEisPayInc.setBasicSal(SystemBasic);
			hrEisPayInc.setNextIncDt(Systemdate);
			hrEisPayInc.setLastIncDt(sysDate);
			hrEisPayincTran.setLastIncDate(sysDate);
			hrEisPayInc.setDeffdFlag("S");
			long empid =Integer.parseInt(StringUtility.getParameter("empid",request));
			
			hrEisPayInc.setUserId(empid);
			long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
			
		    hrEisPayincTran.setReqTranId(reqid);
		    hrEisPayincTran.setCompFlag("SysComp");
		    hrEisPayincTran.setApprovalFlag("A");
		    hrEisPayincTran.setSysComSalary(SystemBasic);
		    hrEisPayincTran.setSysComNxtincDate(Systemdate);
			objectArgs.put("HrEisPayInc",hrEisPayInc);
			objectArgs.put("HrEisPayincTran",hrEisPayincTran);
			}
      
            
           if(YES.equalsIgnoreCase("5")){
    	  
    	   String dexplanation=StringUtility.getParameter("dexplanation",request);
		   Date dffdincdate=StringUtility.convertStringToDate(StringUtility.getParameter("dffdincdate",request));
		   long LWP =Integer.parseInt(  StringUtility.getParameter("LWP",request));
		 
		   HrEisPayInc hrEisPayInc =new HrEisPayInc();
		   hrEisPayInc.setLwp(LWP);
		   hrEisPayInc.setRemarks(dexplanation);
		   hrEisPayInc.setNextIncDt(dffdincdate);
		   hrEisPayInc.setDeffdFlag("D");
		   long empid =Integer.parseInt(  StringUtility.getParameter("empid",request));
		 
		   hrEisPayInc.setUserId(empid);
		   long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
		
		   hrEisPayincTran.setReqTranId(reqid);
		   hrEisPayincTran.setDefferedDate(dffdincdate);
		   hrEisPayincTran.setRemarks(dexplanation);
		   hrEisPayincTran.setApprovalFlag("D");
		   hrEisPayincTran.setCompFlag("Deferred");
		   hrEisPayincTran.setLwp(LWP);
		 
		 
		   objectArgs.put("HrEisPayInc",hrEisPayInc);
		   objectArgs.put("HrEisPayincTran",hrEisPayincTran);
            }
            
           if(userComp.equalsIgnoreCase("4")){
         
           String explanation=StringUtility.getParameter("explanation",request);
   		   Date fromdate=StringUtility.convertStringToDate(StringUtility.getParameter("fromdate",request));
   		   
   		   long payfixed =Integer.parseInt(  StringUtility.getParameter("payfixed",request));
   		  
   		   long LWP =Integer.parseInt(  StringUtility.getParameter("LWP",request));
   		 
   		   HrEisPayInc hrEisPayInc =new HrEisPayInc();
		   hrEisPayInc.setRemarks(explanation);
		   hrEisPayInc.setNextIncDt(fromdate);  
		   hrEisPayInc.setBasicSal(payfixed);
		   hrEisPayInc.setLwp(LWP);
		   hrEisPayInc.setDeffdFlag("N");/*look for this change*/
		   hrEisPayInc.setLastIncDt(sysDate);
			hrEisPayincTran.setLastIncDate(sysDate);
		   long empid =Integer.parseInt(  StringUtility.getParameter("empid",request));
		   hrEisPayInc.setUserId(empid);
		   long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
		  
		   hrEisPayincTran.setReqTranId(reqid);
		   hrEisPayincTran.setUserComNxtincDate(fromdate);
		   hrEisPayincTran.setApprovalFlag("A");
		   hrEisPayincTran.setCompFlag("UserComp");
		   hrEisPayincTran.setUserComSalary(payfixed);
		   hrEisPayincTran.setRemarks(explanation);
		   hrEisPayincTran.setLwp(LWP);
		   objectArgs.put("HrEisPayInc",hrEisPayInc);
		   objectArgs.put("HrEisPayincTran",hrEisPayincTran);
           }

			
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;

	}

	
	
	
/* (non-Javadoc)
 * @see com.tcs.sgv.hr.payincrement.service.PayIncVOGenerator#UpdateMstf(java.util.Map)
 */
public ResultObject UpdateMstf(Map objectArgs) {
		
		
	HrPayincTxn hrEisPayincTran =new HrPayincTxn();
		

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		Date sysDate = new Date ();
		 
       
		try{

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);

			try
			{
				if (objRes == null || objectArgs == null)
				{
					objRes.setResultCode(-1);
					return objRes;
				}
				
			}

			catch (Exception e)
			{
				e.printStackTrace();

			}
		
			String YES=StringUtility.getParameter("YES",request);
			String SystemComp=StringUtility.getParameter("SystemComp",request);
			String userComp=StringUtility.getParameter("userComp",request);
			
			
			if(SystemComp.equalsIgnoreCase("1")){
           
            long SystemBasic =Integer.parseInt(  StringUtility.getParameter("SystemBasic",request));
            
		   
		    Date  Systemdate =StringUtility.convertStringToDate(StringUtility.getParameter("Systemdate",request));
		   
			HrEisPayInc hrEisPayInc =new HrEisPayInc();
			
			hrEisPayInc.setBasicSal(SystemBasic);
			hrEisPayInc.setNextIncDt(Systemdate);
			hrEisPayInc.setLastIncDt(sysDate);
			hrEisPayincTran.setLastIncDate(sysDate);
			hrEisPayInc.setDeffdFlag("S");
			long empid =Integer.parseInt(StringUtility.getParameter("empid",request));
			
			 hrEisPayInc.setUserId(empid);
			long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
			
		    hrEisPayincTran.setReqTranId(reqid);
		    hrEisPayincTran.setCompFlag("SysComp");
		    hrEisPayincTran.setApprovalFlag("F");
		    hrEisPayincTran.setSysComSalary(SystemBasic);
		    hrEisPayincTran.setSysComNxtincDate(Systemdate);
			objectArgs.put("HrEisPayInc",hrEisPayInc);
			objectArgs.put("HrEisPayincTran",hrEisPayincTran);
			}
      
            
           if(YES.equalsIgnoreCase("5")){
    	 
    	   String dexplanation=StringUtility.getParameter("dexplanation",request);
		   Date dffdincdate=StringUtility.convertStringToDate(StringUtility.getParameter("dffdincdate",request));
		   long LWP =Integer.parseInt(  StringUtility.getParameter("LWP",request));
		  
		   HrEisPayInc hrEisPayInc =new HrEisPayInc();
		   hrEisPayInc.setLwp(LWP);
		   hrEisPayInc.setRemarks(dexplanation);
		   hrEisPayInc.setNextIncDt(dffdincdate);
		   hrEisPayInc.setDeffdFlag("D");
		   long empid =Integer.parseInt(  StringUtility.getParameter("empid",request));
		  
		   hrEisPayInc.setUserId(empid);
		   long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
		
		   hrEisPayincTran.setReqTranId(reqid);
		   hrEisPayincTran.setDefferedDate(dffdincdate);
		   hrEisPayincTran.setRemarks(dexplanation);
		   hrEisPayincTran.setApprovalFlag("DF");
		   hrEisPayincTran.setCompFlag("Deferred");
		   hrEisPayincTran.setLwp(LWP);
		
		
		   objectArgs.put("HrEisPayInc",hrEisPayInc);
		   objectArgs.put("HrEisPayincTran",hrEisPayincTran);
            }
            
           if(userComp.equalsIgnoreCase("4")){
          
           String explanation=StringUtility.getParameter("explanation",request);
   		   Date fromdate=StringUtility.convertStringToDate(StringUtility.getParameter("fromdate",request));
   		   
   		   long payfixed =Integer.parseInt(  StringUtility.getParameter("payfixed",request));
   		   
   		   long LWP =Integer.parseInt(  StringUtility.getParameter("LWP",request));
   		
   		   HrEisPayInc hrEisPayInc =new HrEisPayInc();
		   hrEisPayInc.setRemarks(explanation);
		   hrEisPayInc.setNextIncDt(fromdate);  
		   hrEisPayInc.setBasicSal(payfixed);
		   hrEisPayInc.setLwp(LWP);
		   hrEisPayInc.setDeffdFlag("N");/*look for this change*/
		   hrEisPayInc.setLastIncDt(sysDate);
			hrEisPayincTran.setLastIncDate(sysDate);
		   long empid =Integer.parseInt(  StringUtility.getParameter("empid",request));
		   hrEisPayInc.setUserId(empid);
		   long reqid = StringUtility.convertToLong((StringUtility.getParameter("reqid",request)));
		  
		   hrEisPayincTran.setReqTranId(reqid);
		   hrEisPayincTran.setUserComNxtincDate(fromdate);
		   hrEisPayincTran.setApprovalFlag("F");
		   hrEisPayincTran.setCompFlag("UserComp");
		   hrEisPayincTran.setUserComSalary(payfixed);
		   hrEisPayincTran.setRemarks(explanation);
		   hrEisPayincTran.setLwp(LWP);
		   objectArgs.put("HrEisPayInc",hrEisPayInc);
		   objectArgs.put("HrEisPayincTran",hrEisPayincTran);
           }

			
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;

	}
public ResultObject getFileId(Map objectArgs) {



	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);


	try{

		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		String locale = StringUtility.getParameter("locale",request);

		try
		{
			if (objRes == null || objectArgs == null)
			{
				objRes.setResultCode(-1);
				return objRes;
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();

		}

		String fileId=  StringUtility.getParameter("fileId",request);
         
        


		
		objectArgs.put("fileId",fileId);


		

		objRes.setResultValue(objectArgs);
	}
	catch(Exception e){
		logger.error("error while getting Fileid",e);			
		objRes = new ResultObject(ErrorConstants.ERROR);
		objRes.setThrowable(e);
		objRes.setViewName("errorPage");

	}	
	return objRes;

}

}


























