package com.tcs.sgv.common.report.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
/**
 * @author 217977
 *
 */
public class HRMSReportServiceImpl extends ServiceImpl implements HRMSReportService{

	public final static String SERVICE_LOCATOR="serviceLocator";
	public final static String BASELOGINMAP="baseLoginMap";
	public final static String SHOW_EMP_DTLS="SHOW_EMP_DETAIL";
	
	
	public ResultObject getFileIdFromVOGEN(Map objectArgs){
		Log logger = LogFactory.getLog(getClass());
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			
			ServiceLocator serv = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);			
			
			String fileId=objectArgs.get("fileId").toString();
			String moduleId=objectArgs.get("moduleId").toString();
		//	System.out.println("Module ID>>>> "+moduleId);
			
			Map redirectMap = new HashMap();
			if(moduleId.equals("330023"))
			{
				redirectMap.put("actionFlag", "gpfAdvInbox");	
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("330022"))
			{
				redirectMap.put("actionFlag", "gpfSubInbox");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("330024"))
			{
				redirectMap.put("actionFlag", "gpfWithInbox");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300025"))
			{
			//	System.out.println("For Resignation Action flag service");
				redirectMap.put("actionFlag", "populateReqPage");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300013"))
			{
			//	System.out.println("For Quarter Allotment Action flag service");
				redirectMap.put("actionFlag", "getQtrDetailsBranch");						
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300010"))
			{
			//	System.out.println("For NOC Passport Action flag service");
				redirectMap.put("actionFlag", "getInboxData");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300011"))
			{
			//	System.out.println("For NOC Foreign Action flag service");
				redirectMap.put("actionFlag", "getNocFVisitInboxData");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300012"))
			{
			//	System.out.println("For Foreign Visit Action flag service");
				redirectMap.put("actionFlag", "getFVisitReportInboxData");
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300005"))
			{
			//	System.out.println("For Qualification Action flag service");	
				redirectMap.put("actionFlag", "ShowEducationDtls");
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300006"))
			{
			//	System.out.println("For Nominee Action flag service");
				redirectMap.put("actionFlag", "ShowNomineeDtlsForApprove");								
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300007"))
			{
			//	System.out.println("For Family Action flag service");
				redirectMap.put("actionFlag", "ShowFamilyDtlsForApprove");							
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300020"))
			{
			//	System.out.println("For Change Employee Profile Action flag service");
				redirectMap.put("actionFlag", "showApproveEmpDtls");						
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300021"))
			{
			//	System.out.println("For Change Employee Address Action flag service");
				redirectMap.put("actionFlag", "showAndApproveAddress");					
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("300015"))
			{
			//	System.out.println("For LTC Action flag service");
				redirectMap.put("actionFlag", "viewLTCRqst");					
				redirectMap.put("fileId", fileId);
				redirectMap.put("StatusFlag","0");
			}
			if(moduleId.equals("320401"))
			{
				redirectMap.put("actionFlag", "viewUpdatedAssetPermissionReq");					
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("320400"))
			{
				redirectMap.put("actionFlag", "viewAssetPermissionReq");					
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("330027"))
			{
				redirectMap.put("actionFlag", "getDraftData");					
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("330028"))
			{
				redirectMap.put("actionFlag", "getDraftData");					
				redirectMap.put("corrId", fileId);
				redirectMap.put("flag","cancle");
			}
			if(moduleId.equals("330029"))
			{
				redirectMap.put("actionFlag", "getAdvanceReqPage");					
				redirectMap.put("corrId", fileId);
			}
			if(moduleId.equals("330030"))
			{
				redirectMap.put("actionFlag", "getRmbrsReqInboxPage");					
				redirectMap.put("corrId", fileId);
			}
			
			
			if(moduleId.equals("330026"))
			{
			//	System.out.println("For GPF NEW ACC Action flag service");
				redirectMap.put("actionFlag", "viewNewAccReqPage");					//actionflag
				redirectMap.put("fileId", fileId);
			}
			if(moduleId.equals("300001"))
			{
				redirectMap.put("actionFlag", "getParticularLeaveData");					//actionflag
				redirectMap.put("corrId", fileId);
				redirectMap.put("appId", fileId);
				redirectMap.put("viewFromReport", "1");
			}
			if(moduleId.equals("300002"))
			{
				redirectMap.put("actionFlag", "getParticularLeaveData");					//actionflag
				redirectMap.put("corrId", fileId);
				redirectMap.put("appId", fileId);
				redirectMap.put("viewCancelReport","1");
			}
			if(moduleId.equals("300003"))
			{
				redirectMap.put("actionFlag", "App_Joining");					//actionflag
				redirectMap.put("corrId", fileId);
				redirectMap.put("joid",fileId);
			}
			
			
			
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objectArgs.put("redirectMap", redirectMap);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("redirect view");
						
			
		}catch (Exception e) {
			logger.error("Error while populating file details from hrms report in service",e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			
		}
	//	  System.out.println("getFileIdFromVOGEN of VOGEN executed successfully::");
		return objRes;
	}
	public ResultObject getDtlsforHrmsreport(Map objectArgs){
		
		//ReadProperty readProperty= ReadProperty.getInstance();
		//Object[] map= readProperty.getKeyValue();
		
		Map logInMap=(Map)objectArgs.get("baseLoginMap");
		long langId=Long.parseLong(logInMap.get("langId").toString());
		String operator="";
		String noOfDays="";
		String actionval="";
		String fromdate=null;
		String todate=null;
		String appliedDt=null;
		String appliedTo=null;
		String apptype=null;	
		
		Log logger = LogFactory.getLog(getClass());
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		SimpleDateFormat  dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:MM:SS");
		
		try
		{ 
			
			ServiceLocator serv = (ServiceLocator) objectArgs.get(SERVICE_LOCATOR);			
			
			if(objectArgs.get("appliedDt")!=null && !objectArgs.get("appliedDt").equals("")){
				appliedDt=(String)objectArgs.get("appliedDt");
		//		System.out.println("The appliedDt in the Service is   ::"+appliedDt);
			}
			
			if(objectArgs.get("NoOfDays")!=null && !objectArgs.get("NoOfDays").equals("")){
				noOfDays=(String)objectArgs.get("NoOfDays");
		//		System.out.println("The NoOf Daysin the Service is   ::"+noOfDays);
			}
			
			if(objectArgs.get("operator") != null && !objectArgs.get("operator").equals("-1")){
				operator=(String)objectArgs.get("operator");
		//		System.out.println("The operator in the Service is   ::"+operator);
			}
			
			if(operator.equals("0")){
				//here  
				SimpleDateFormat simpledt = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date =  StringUtility.convertStringToDate(appliedDt);
		//		System.out.println("Applend DATe :::::: >>>>>    "+simpledt.format(date));
				
				String[] str = simpledt.format(date).split("-");
				int year = Integer.parseInt(str[0]);
		//		System.out.println("Year >> "+year);
				int month = Integer.parseInt(str[1]);
		//		System.out.println("Month >> "+month);
				int day = Integer.parseInt(str[2]);
		//		System.out.println("Day >> "+day);
				
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(date);
				calendar.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(noOfDays)+1);
				
				
				appliedTo= simpledt.format(calendar.getTime());
		//		System.out.println("Applied TO >>>>>>>>>>>>>>>> IN SERVICE:::: "+simpledt.format(calendar.getTime()));

			}else if(operator.equals("1")){

				//here  
				SimpleDateFormat simpledt = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date =  StringUtility.convertStringToDate(appliedDt);
	//			System.out.println("Applend DATe :::::: >>>>>    "+simpledt.format(date));
				
				String[] str = simpledt.format(date).split("-");
				int year = Integer.parseInt(str[0]);
	//			System.out.println("Year >> "+year);
				int month = Integer.parseInt(str[1]);
	//			System.out.println("Month >> "+month);
				int day = Integer.parseInt(str[2]);
	//			System.out.println("Day >> "+day);
				
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(date);
				calendar.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(noOfDays));
				
				
				appliedTo= simpledt.format(calendar.getTime());
	//			System.out.println("Applied TO >>>>>>>>>>>>>>>> IN SERVICE:::: "+simpledt.format(calendar.getTime()));

			}else if(operator.equals("2")){
				SimpleDateFormat simpledt = new SimpleDateFormat("yyyy-MM-dd");
				
				Date date =  StringUtility.convertStringToDate(appliedDt);
	//			System.out.println("Applend DATe :::::: >>>>>    "+simpledt.format(date));
				
				String[] str = simpledt.format(date).split("-");
				int year = Integer.parseInt(str[0]);
				int month = Integer.parseInt(str[1]);
				int day = Integer.parseInt(str[2]);
				
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(date);
				calendar.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(noOfDays));
				
				
				appliedTo= simpledt.format(calendar.getTime());
				
			}
			
			
			if(objectArgs.get("fromdate") != null &&  !objectArgs.get("fromdate").equals("")){
				fromdate=objectArgs.get("fromdate").toString();
			}
			
			if(objectArgs.get("todate") != null && !objectArgs.get("todate").equals("") ){
				todate=objectArgs.get("todate").toString();
			}
			
			if(objectArgs.get("apptype") != null && !objectArgs.get("apptype").equals("") ){
				apptype=objectArgs.get("apptype").toString();
			}
			
			String appname=objectArgs.get("appname").toString();
			String appstatus=objectArgs.get("appstatus").toString();
			Map redirectMap = new HashMap();
			
			int apps=Integer.parseInt(appstatus);
			
			
			if(apps==1)
			{
				redirectMap.put("actionFlag", "reportService");
				if(langId==1){
					redirectMap.put("reportCode", "300050");
				}else{
					redirectMap.put("reportCode", "300053");
				}
				
				redirectMap.put("action", "generateReport");
				redirectMap.put("FromParaPage", "TRUE");
				redirectMap.put("appname", appname);
				redirectMap.put("NoOfDays", noOfDays);
				redirectMap.put("operator", operator);
				redirectMap.put("fromdate", fromdate);
				redirectMap.put("todate", todate);
				redirectMap.put("appliedDt", appliedDt);
				redirectMap.put("appliedTo", appliedTo);
				redirectMap.put("apptype", apptype);
			}
			if(apps==2)
			{
				/*redirectMap.put("actionFlag", "reportService");
				redirectMap.put("reportCode", "300051");
				redirectMap.put("action", "generateReport");
				redirectMap.put("actionVal",appname+"&"+fromdate+"&"+todate);
				redirectMap.put("FromParaPage", "TRUE");
				if(objectArgs.get("status").equals("true")){
					redirectMap.put("actionVal",appname+"&"+fromdate+"&"+todate);
				}else{
					redirectMap.put("actionVal",appname);
				}*/
				
				redirectMap.put("actionFlag", "reportService");
				if(langId==1){
					redirectMap.put("reportCode", "300051");
				}else{
					redirectMap.put("reportCode", "300054");
				}
				
				redirectMap.put("action", "generateReport");
				redirectMap.put("FromParaPage", "TRUE");
				redirectMap.put("appname", appname);
				redirectMap.put("NoOfDays", noOfDays);
				redirectMap.put("operator", operator);
				redirectMap.put("fromdate", fromdate);
				redirectMap.put("todate", todate);
				redirectMap.put("appliedDt", appliedDt);
				redirectMap.put("appliedTo", appliedTo);
				redirectMap.put("apptype", apptype);
			}
			if(apps==0)
			{
				/*redirectMap.put("actionFlag", "reportService");
				redirectMap.put("reportCode", "300052");
				redirectMap.put("action", "generateReport");
				redirectMap.put("actionVal",appname+"&"+fromdate+"&"+todate);
				redirectMap.put("FromParaPage", "TRUE");
				if(objectArgs.get("status").equals("true")){
					redirectMap.put("actionVal",appname+"&"+fromdate+"&"+todate);
				}else{
					redirectMap.put("actionVal",appname);
				}*/
				
				redirectMap.put("actionFlag", "reportService");
				if(langId==1){
					redirectMap.put("reportCode", "300052");
				}else{
					redirectMap.put("reportCode", "300055");
				}
				
				redirectMap.put("action", "generateReport");
				redirectMap.put("FromParaPage", "TRUE");
				redirectMap.put("appname", appname);
				redirectMap.put("NoOfDays", noOfDays);
				redirectMap.put("operator", operator);
				redirectMap.put("fromdate", fromdate);
				redirectMap.put("todate", todate);
				redirectMap.put("appliedDt", appliedDt);
				redirectMap.put("appliedTo", appliedTo);
				redirectMap.put("apptype", apptype);
			}
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objectArgs.put("redirectMap",redirectMap);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("redirect view");
						
			
		}catch (Exception e) {
			logger.error("Error while populating file details from hrms report in service",e);
			objRes.setResultCode(ErrorConstants.ERROR);
			e.printStackTrace();
			objRes.setResultCode(-1);
			
		}
		return objRes;
	}
	
	/*public ResultObject getBundleValues(Map objectArgs){
		System.out.println("Inside getBundleValues method:: ");
		Log logger = LogFactory.getLog(getClass());
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		
		ReadProperty readProperty= ReadProperty.getInstance();
		Object[] map= readProperty.getKeyValue();
		System.out.println("the Size of the Array is ::>>"+map.length);
		for(int i=0;i<map.length;i++){
			System.out.println("The Values are ::>>"+map[i]);
		}
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objectArgs.put("map",map);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("reportMain");
		
		return objRes;
		//return map;
	}*/
	
}