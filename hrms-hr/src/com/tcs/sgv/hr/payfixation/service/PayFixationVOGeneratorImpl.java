package com.tcs.sgv.hr.payfixation.service;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayFixTxn;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayfixReqDtl;

/**
 * @author 217977
 *
 */
public class PayFixationVOGeneratorImpl extends ServiceImpl implements VOGeneratorService, PayFixationVOGenerator
{
	Log logger = LogFactory.getLog(getClass());
	/* 
	 * To get gpf account no while intialization of pay fixation request.
	 * (non-Javadoc)
	 * @see com.tcs.sgv.core.service.VOGeneratorService#generateMap(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#generateMap(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#generateMap(java.util.Map)
	 */
	public ResultObject generateMap(Map objectArgs) 
	{
        
		long empId=0;
		
		
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
			
			
			
			
			empId = Long.parseLong(StringUtility.getParameter("hempId",request));
			
			
			
			HrEisEmpMst hrempmst=new HrEisEmpMst();
			
			
			hrempmst.setEmpId(empId);
			
			objectArgs.put("hrEmpMst",hrempmst);
			
			
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* 
	 * To get details regarding payfixation for given emp.
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#insertfixation(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#insertfixation(java.util.Map)
	 */
	public ResultObject insertfixation(Map objectArgs) 
	{
      	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map   loginMap = (Map) objectArgs.get("baseLoginMap");
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
			
			
			
			Date dt=new Date();
			int day=dt.getDate();
			int mont=dt.getMonth()+1;
			int yr=dt.getYear()+1900;
			String dt1=day+"/"+mont+"/"+yr;
			
			Date appdate=StringUtility.convertStringToDate(dt1);
			  long userId=Long.parseLong(StringUtility.getParameter("userid",request));
			 
			  long revscaleid=Long.parseLong(StringUtility.getParameter("scalemst",request));
			  long desigid=Long.parseLong(StringUtility.getParameter("desigid",request));
			  long basic=Long.parseLong(StringUtility.getParameter("basic",request));
			  long currscalid=Long.parseLong(StringUtility.getParameter("currscalid",request));
			  String date=(StringUtility.getParameter("fromdate",request));
			  Date fixdate=StringUtility.convertStringToDate(date);
			  long reasonpay=Long.parseLong(StringUtility.getParameter("payfixation",request));
			  String deci=StringUtility.getParameter("dec",request);
			
			
        	
			
			
			HrPayFixTxn trans=new HrPayFixTxn();
			
			OrgUserMst user=new OrgUserMst();
			user.setUserId(userId);
			trans.setUserId(user);
			trans.setApplDate(appdate);
			trans.setPayFixDate(fixdate);
			CmnLookupMst cmn=new CmnLookupMst();
			cmn.setLookupId(reasonpay);
			trans.setCmnLookupMst(cmn);
			HrEisScaleMst scale=new HrEisScaleMst();
			scale.setScaleId(revscaleid);
			trans.setRevScaleId(scale);
			
			
			objectArgs.put("Transaction",trans);
			
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* 
	 * To get empseldate .
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#empseldate(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#empseldate(java.util.Map)
	 */
	public ResultObject empseldate(Map objectArgs) 
	{
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
			
			
			
			
			String date= StringUtility.getParameter("accept",request);
			 long Payfixid=Long.parseLong(StringUtility.getParameter("payfixid",request));
			
			
				
			 HrPayFixTxn hrtrans=new HrPayFixTxn();
				
				hrtrans.setPayFixation(date);
				hrtrans.setPayFixId(Payfixid);
                objectArgs.put("hrTrans",hrtrans);
				objRes.setResultValue(objectArgs);	
		}
		catch(Exception e)
		{
						
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* 
	 * To get year,month entered during search .
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#searchpayfix(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#searchpayfix(java.util.Map)
	 */
	public ResultObject searchpayfix(Map objectArgs) 
	{
        
	
		
		
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
			
			
			
			String option=StringUtility.getParameter("option",request);
			int year = Integer.parseInt(StringUtility.getParameter("Year",request));
			int month = Integer.parseInt(StringUtility.getParameter("Month",request));
			String Designation=StringUtility.getParameter("Designation",request);
			
			
			
			
			
			String date="01/"+month+"/"+year;
			Date date1=StringUtility.convertStringToDate(date);
			
			
			HrPayFixTxn hrtran=new HrPayFixTxn();
			hrtran.setApplDate(date1);
			
			java.util.GregorianCalendar gc=new java.util.GregorianCalendar(year,month-1,1);
			
			
			int total=gc.getActualMaximum(gc.DAY_OF_MONTH);
			

			int dayOfMonth = 1; // or whatever day you want
			 
			
			
			String date2=total+"/"+month+"/"+year;
			 Date date3=StringUtility.convertStringToDate(date2);
			 
			 
			
			 hrtran.setPayFixDate(date3);
			
			 hrtran.setPayFixation(option);
		
			
			
			
			
			
			objectArgs.put("hrtranpayfix",hrtran);
			
			
			objRes.setResultValue(objectArgs);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("error while populating config VO",e);	
			objRes.setResultCode(-1);
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* 
	 * To get payfixid during generation of request.
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#createRequest(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#createRequest(java.util.Map)
	 */
	public ResultObject createRequest(Map objectArgs) 
	{
        
		long empId=0;
		
		
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
			
			
			long payid=Long.parseLong(StringUtility.getParameter("check",request));
			
			HrPayFixTxn tran=new HrPayFixTxn();
			tran.setPayFixId(payid);
			objectArgs.put("hrtranpayfix",tran);
			objRes.setResultValue(objectArgs);
						
		}
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* 
	 * To get deatils during approval.
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Approval(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Approval(java.util.Map)
	 */
	public ResultObject Approval(Map objectArgs) 
	{
        
		
		
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);
			 ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
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
			
			long reqid=Long.parseLong(StringUtility.getParameter("reqid", request));
			String value=StringUtility.getParameter("SystemComp", request);
			String value1=StringUtility.getParameter("UserComp", request);
			
			
			
			if(value.equalsIgnoreCase("1"))
			{
				
				HrPayfixReqDtl payfxdtls=new HrPayfixReqDtl();
		         payfxdtls.setReqId(reqid);
		         objectArgs.put("hrpayfixdtls",payfxdtls);
			}
			if(value1.equalsIgnoreCase("2"))
			{
				long payfixed=Long.parseLong(StringUtility.getParameter("payfixed", request));
				String date=StringUtility.getParameter("user_nxt_incr_date", request);
				Date nxtdate=StringUtility.convertStringToDate(date);
				String expl=StringUtility.getParameter("explanation", request);
				
				HrPayfixReqDtl payfxdtls=new HrPayfixReqDtl();
		         payfxdtls.setReqId(reqid);
		         payfxdtls.setUserCompNxtIncrDate(nxtdate);
		         payfxdtls.setUserRemarks(expl);
		         payfxdtls.setUserCompNewBasicSal(payfixed);
		         objectArgs.put("hrpayfixdtls",payfxdtls);
			}
			objectArgs.put("value",value);
			objectArgs.put("value1",value1);
			objRes.setResultValue(objectArgs);
						
		}
		
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Reject(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Reject(java.util.Map)
	 */
	public ResultObject Reject(Map objectArgs) 
	{
        
		long empId=0;
		
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);
			 ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
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
			long reqid=Long.parseLong(StringUtility.getParameter("reqid", request));
			
			objectArgs.put("reqid",reqid);
			objRes.setResultCode(ErrorConstants.SUCCESS);
         	
            logger.info("The objectArgs is : "+objectArgs);
            objRes.setResultValue(objectArgs);
            
			
		}
		
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Forward(java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#Forward(java.util.Map)
	 */
	public ResultObject SubmitPayfixation(Map objectArgs) 
	{
        
		
		
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);
			 ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
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
				objRes.setViewName("cntbeforwarded");
				
			}
			
			
			long reqId=Long.parseLong(StringUtility.getParameter("reqid", request));
			String value=StringUtility.getParameter("SystemComp", request);
			String value1=StringUtility.getParameter("UserComp", request);
			HrPayfixReqDtl payfxdtls=new HrPayfixReqDtl();
	         payfxdtls.setReqId(reqId);
			if(value1.equalsIgnoreCase("2"))
			{
				
				long payfixed=Long.parseLong(StringUtility.getParameter("payfixed", request));
				String date=StringUtility.getParameter("user_nxt_incr_date", request);
				Date nxtdate=StringUtility.convertStringToDate(date);
				String expl=StringUtility.getParameter("explanation", request);
				
		         payfxdtls.setUserCompNxtIncrDate(nxtdate);
		         payfxdtls.setUserRemarks(expl);
		         payfxdtls.setUserCompNewBasicSal(payfixed);
		         
				
				
			}
		
			objectArgs.put("hrpayfixdtls",payfxdtls);
			objectArgs.put("value1",value1);
       	
			objRes.setResultCode(ErrorConstants.SUCCESS);
         	
            logger.info("The objectArgs is : "+objectArgs);
            objRes.setResultValue(objectArgs);
            
			
		}
		
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("cntbeforwarded");

		}	
	
		return objRes;
	}	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationVOGenerator#RequestId(java.util.Map)
	 */
	public ResultObject RequestId(Map objectArgs) 
	{
        
		long empId=0;
		
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String locale = StringUtility.getParameter("locale",request);
			 ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			
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
			 long reqId=0;
			 if(StringUtility.getParameter("fileId", request)!=null && !StringUtility.getParameter("fileId", request).equals(""))
				{
	            	reqId=Long.parseLong(StringUtility.getParameter("fileId", request));
				}
			
			objectArgs.put("reqid",reqId);
			objRes.setResultCode(ErrorConstants.SUCCESS);
         	
            logger.info("The objectArgs is : "+objectArgs);
            objRes.setResultValue(objectArgs);
            
			
		}
		
		catch(Exception e){
			logger.error("error while populating config VO",e);			
			objRes = new ResultObject(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");

		}	
		return objRes;
	}	
}



