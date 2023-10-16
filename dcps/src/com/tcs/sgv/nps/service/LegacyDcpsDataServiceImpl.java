package com.tcs.sgv.nps.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.nps.dao.LegacyDataEntryDao;
import com.tcs.sgv.nps.dao.LegacyDataEntryDaoImpl;
import com.tcs.sgv.dcps.dao.MatchContriEntryDAO;
import com.tcs.sgv.dcps.dao.MatchContriEntryDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.nps.dao.NsdlSrkaNewFileGeneDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;

public class LegacyDcpsDataServiceImpl extends ServiceImpl {

/* Global Variable for Logger Class */
private final Log logger = LogFactory.getLog(getClass());

private String gStrPostId = null; /* STRING POST ID */

private String gStrLocationCode = null;

private Long gLngPostId = null;

private HttpServletRequest request = null; /* REQUEST OBJECT */

private ServiceLocator serv = null; /* SERVICE LOCATOR */

private HttpSession session = null; /* SESSION */

private Date gDtCurDate = null; /* CURRENT DATE */

private String gStrUserId = null; /* STRING USER ID */

/* Global Variable for UserId */
Long gLngUserId = null;

private Long gLngDBId = null; /* DB ID */


private ResourceBundle gObjRsrcBndle = ResourceBundle
	.getBundle("resources/dcps/DCPSConstants");

 void setSessionInfo(Map inputMap) {

try {
	request = (HttpServletRequest) inputMap.get("requestObj");
	serv = (ServiceLocator) inputMap.get("serviceLocator");
	session = request.getSession();
	gStrPostId = SessionHelper.getPostId(inputMap).toString();
	gLngPostId = SessionHelper.getPostId(inputMap);
	gStrLocationCode = SessionHelper.getLocationCode(inputMap);
	gLngUserId = SessionHelper.getUserId(inputMap);
	gStrUserId = gLngUserId.toString();
	gLngDBId = SessionHelper.getDbId(inputMap);
	gDtCurDate = SessionHelper.getCurDate();
} catch (Exception e) {
	logger.error(" Error is : " + e, e);
}
}

public ResultObject getDcpsLegacyData(Map objectArgs) throws Exception {
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	String lStrEmpName = null;
	String lStrSevaarthId = null;
	String ddoCode = null;
	String lStrDcpsId = null;
	String limit=null;
	String actionEmp=null;
	int count1=0;

	List lLstEmpDeselect = null;

	
	try {
		
		setSessionInfo(objectArgs);

		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		logger.info("ddoCode*******"+ddoCode);
		String locId=gStrLocationCode;
		logger.info("locId*******"+locId);
	
	     final List lLstLegValidatePeriod = IFMSCommonServiceImpl.getLookupValues("validatePeriod", SessionHelper.getLangId(objectArgs), objectArgs);
	     
	     System.out.print("lLstLegValidatePeriod"+lLstLegValidatePeriod);
	     
	     objectArgs.put("lLstLegValidatePeriod", lLstLegValidatePeriod);
	

		if (StringUtility.getParameter("fromSearch", request).trim().equals("Yes")) {
			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request).trim().toUpperCase();
			logger.info("txtEmployeeName*******"+lStrEmpName);
			lStrSevaarthId = StringUtility.getParameter("txtSevaarthId", request).trim().toUpperCase();
			logger.info("lStrSevaarthId*******"+lStrSevaarthId);
			lStrDcpsId = StringUtility.getParameter("txtDcpsId", request).trim().toUpperCase();
			limit = StringUtility.getParameter("limit", request).trim().toUpperCase();
			logger.info("limit*******"+limit);
	
			lLstEmpDeselect = lObjNewLegDdoDAO.getAllEmp(lStrSevaarthId,lStrEmpName,lStrDcpsId,ddoCode );
		
			count1=lLstEmpDeselect.size();
			if(count1>0)
			{
				actionEmp="successful";
			}
			
			else{
				actionEmp="failure";
				
			}
		}
		objectArgs.put("actionEmp", actionEmp);
		objectArgs.put("DESELECTEMPLIST", lLstEmpDeselect);
		
		objRes.setResultValue(objectArgs);
		objRes.setViewName("getDcpsLegacyData");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
}

public ResultObject saveDcpsLegacyData(Map objectArgs) throws Exception {
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	String lStrEmpName = "";
	String lStrSevaarthId = "";
	String ddoCode = "";
	String lStrDcpsId = "";
	String period="";
	String empContri="";
	String employerContri="";
	String empInterest="";
	String employerInterest="";
	String remark="";
	
	String strContriStartDt="";
	String strContriEndtDt="";

	int count1;
	int count2=0;
	String total=null;

	List lLstEmpDeselect = null;
	int insertCount=0;
	String successFlag="fail";
	String fromDay="";
	String fromMonth="";
	String fromYear="";
	String toDay="";
	String toMonth="";
	String toYear="";
	
	try {
		
		setSessionInfo(objectArgs);
	
		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());

		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		logger.info("ddoCode*******"+ddoCode);
		String locId=gStrLocationCode;
		logger.info("locId*******"+locId);
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("yyyy-MM-DD");
	
			lStrEmpName = StringUtility.getParameter("pqr", request).trim().toUpperCase();
			logger.info("txtEmployeeName*******"+lStrEmpName);
			lStrSevaarthId = StringUtility.getParameter("abc", request).trim().toUpperCase();
			logger.info("lStrSevaarthId*******"+lStrSevaarthId);
			lStrDcpsId = StringUtility.getParameter("xyz", request).trim().toUpperCase();
		
			period= StringUtility.getParameter("period", request);
			empContri= StringUtility.getParameter("empContri", request);
			logger.info("period"+period);
			
			employerContri=StringUtility.getParameter("employerContri", request);
			empInterest=StringUtility.getParameter("empInterest", request);
			employerInterest=StringUtility.getParameter("employerInterest", request);
			strContriEndtDt=StringUtility.getParameter("contriEndDate",request);
			strContriStartDt=StringUtility.getParameter("contriStartDt",request);
			remark=StringUtility.getParameter("remark", request);
			
			logger.info("strContriStartDt***"+strContriStartDt);
			logger.info("strContriEndtDt^^^"+strContriEndtDt);
			
		if(strContriStartDt !="" || strContriStartDt !=null){
			 fromDay=strContriStartDt.split("/")[0];
			 fromMonth=strContriStartDt.split("/")[1];
			 fromYear=strContriStartDt.split("/")[2];
		}
		String contriStartDt=fromYear+"-"+fromMonth+"-"+fromDay;
		logger.info("contriStartDt***"+contriStartDt);
		
		if(strContriEndtDt !="" || strContriEndtDt !=null){
			 toDay=strContriEndtDt.split("/")[0];
			 toMonth=strContriEndtDt.split("/")[1];
			 toYear=strContriEndtDt.split("/")[2];
			
		}
			
			
			String contriEndtDt=toYear+"-"+toMonth+"-"+toDay;
			
			
		
			logger.info("contriEndtDt^^^"+contriEndtDt);
		
				
			total=StringUtility.getParameter("total", request);
	
			int entrystatus=0;
			if((period!=null)&&(period!="")){
				
				 entrystatus= lObjNewLegDdoDAO.validationOfPeriodWithStatus(lStrSevaarthId,period.toString());

				if(entrystatus==0){
					count2= lObjNewLegDdoDAO.updateForRejected(lStrSevaarthId,Long.valueOf(period).longValue(), Double.parseDouble(empContri),  Double.parseDouble(employerContri),  Double.parseDouble(empInterest), Double.parseDouble(employerInterest), Double.parseDouble(total),remark);
					
				} else if(entrystatus==10){
						
					insertCount=	lObjNewLegDdoDAO.saveData(lStrSevaarthId,lStrDcpsId,Long.valueOf(period).longValue(), Double.parseDouble(empContri), Double.parseDouble(employerContri),  Double.parseDouble(empInterest), Double.parseDouble(employerInterest), Double.parseDouble(total),contriStartDt,contriEndtDt,objectArgs,remark);
	           }
			} 
		if(insertCount > 0 || count2>0){
			successFlag="success";
		} else if(entrystatus==2 || entrystatus==2 ) {
			successFlag="successFail";
		}
	
		objectArgs.put("successFlag", successFlag);
		objectArgs.put("DESELECTEMPLIST", lLstEmpDeselect);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("getDcpsLegacyData");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
}

public ResultObject validatePeriod(Map objectArgs) throws Exception {
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	String lStrEmpName = null;
	String lStrSevaarthId = null;
	String ddoCode = null;
	String lStrDcpsId = null;
	List lLstEmpView = null;
	String lStrPeriod=null;
	Boolean lBlFlag = false;
	String countperiod=null;
	String actionss=null;
	try {
		
		setSessionInfo(objectArgs);
		//setSessionInfo(inputMap);
		//gLngPostId = SessionHelper.getPostId(inputMap);
		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
		//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		logger.info("ddoCode*******"+ddoCode);
		String locId=gStrLocationCode;
		logger.info("locId*******"+locId);

			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request).trim().toUpperCase();
			logger.info("txtEmployeeName*******"+lStrEmpName);
			lStrSevaarthId = StringUtility.getParameter("abc", request).trim().toUpperCase();
			logger.info("lStrSevaarthId*******"+lStrSevaarthId);
			lStrDcpsId = StringUtility.getParameter("txtDcpsId", request).trim().toUpperCase();
			
			lStrPeriod=StringUtility.getParameter("period", request).trim();
			logger.info("Period*******"+lStrPeriod);
			
			countperiod=lObjNewLegDdoDAO.validationOfPeriod(lStrSevaarthId,lStrPeriod);
			logger.info("countperiod*******"+countperiod);
		

			
			
			String lSBStatus = getResponseXMLDoc(countperiod).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			objectArgs.put("ajaxKey", lStrResult);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");
			return objRes;

			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
}


private StringBuilder getResponseXMLDoc(String flag)
{

	StringBuilder lStrBldXML = new StringBuilder();

	lStrBldXML.append("<XMLDOC>");
	lStrBldXML.append("<Flag>");
	lStrBldXML.append(flag);
	logger.info("value of flag  is " + flag);
	lStrBldXML.append("</Flag>");
	lStrBldXML.append("</XMLDOC>");

	return lStrBldXML;
}

public ResultObject verifyDcpsLegacyData(Map objectArgs) throws Exception {
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	String lStrEmpName = null;
	String lStrSevaarthId = null;
	String ddoCode = null;
	String lStrDcpsId = null;
	List lLstEmpView = null;
	String lStrPeriod=null;
	int row=0;
	String actions=null;
	try {
		
		setSessionInfo(objectArgs);
		//setSessionInfo(inputMap);
		//gLngPostId = SessionHelper.getPostId(inputMap);
		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
//		NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(null,serv.getSessionFactory()); 
		//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		logger.info("ddoCode*******"+ddoCode);
		String locId=gStrLocationCode;
		logger.info("locId*******"+locId);

			lStrEmpName = StringUtility.getParameter("empName", request).trim().toUpperCase();
			logger.info("txtEmployeeName*******"+lStrEmpName);
			lStrSevaarthId = StringUtility.getParameter("txtSevaarthId", request).trim().toUpperCase();
			logger.info("lStrSevaarthId*******"+lStrSevaarthId);
			lStrDcpsId = StringUtility.getParameter("txtDcpsId", request).trim().toUpperCase();
			
			lStrPeriod=StringUtility.getParameter("Period11", request).trim();
			logger.info("Period*******"+lStrPeriod);
			
		//	lLstEmpDeselect = lObjNewLegDdoDAO.getAllEmp(lStrSevaarthId,lStrEmpName,lStrDcpsId,ddoCode );
			lLstEmpView=lObjNewLegDdoDAO.viewLegacyDataEntry(lStrSevaarthId,lStrEmpName,lStrDcpsId,ddoCode);
		
			
			if((StringUtility.getParameter("txtSevaarthId", request)!=null)&&(StringUtility.getParameter("txtSevaarthId", request)!="")){
				logger.info("Period123"+lStrPeriod);
			row=lObjNewLegDdoDAO.verifySavedData(lStrSevaarthId,lStrPeriod);
			logger.info("outside"+ row);
				if(row>=0)
				{
					logger.info("inside if condition"+ row);
					actions="successfull";
					
				}
			
			}
			
			List treasury=lObjNewLegDdoDAO.getAllTreasuries();
			objectArgs.put("treasury",treasury);	
		
		//objectArgs.put("DESELECTEMPLIST", lLstEmpDeselect);
			objectArgs.put("actions", actions);
		objectArgs.put("VIEWSELECTEMPLIST", lLstEmpView);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("verifyDcpsLegacyData");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
}


public ResultObject getLegacyEmpsContri(Map inputMap) throws Exception {
	logger.info("Inside Get getAISEmpsContri--------------------");
	ResultObject resultObject = new ResultObject(0);

	List lstAisType = null;
	String aisType = null;
	String finType = null;
	String billno = null;
	List lstYear = null;
	String treasuryyno = null;
	String createdfile = null;
	List lstAlIndiaSerEmp = null;
	List lstbillNo = null;
	String aisTypeSelected = null;
	String finTypeSelected = null;
	String ddoCode=null;
	String fromDate = null;
	String toDate = "";
	String locId = "";
	try {
		setSessionInfo(inputMap);
		NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(
				null, serv.getSessionFactory());
		DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null,
				serv.getSessionFactory());
		Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
		logger.info("gStrLocationCode-------------------"
				+ gStrLocationCode);

		lstYear = lObjAlIndSer.getFinyeardesc();
		List treasury = lObjAlIndSer.getAllTreasuries();
		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		inputMap.put("lstYear", lstYear);
		inputMap.put("treasury", treasury);
		Boolean check = Boolean.valueOf(false);
		System.out.println("check" + check);
		String treasuryno = null;
		if ((StringUtility.getParameter("treasno", request) != null)
				&& (StringUtility.getParameter("treasno", request) != "")) {
			treasuryno = StringUtility.getParameter("treasno", request);
			inputMap.put("treasuryno", treasuryno);
		}
		locId = lObjAlIndSer.getLocationId(gStrLocationCode);

		if ((StringUtility.getParameter("treasno", request) != null)
				&& (StringUtility.getParameter("treasno", request) != "")) {
			check = Boolean.valueOf(true);
			treasuryyno = StringUtility.getParameter("treasno", request)
					.trim();
			String extn = StringUtility.getParameter("flag", request)
					.trim();
			logger.info("treasuryyno-------------------" + treasuryyno);
			if (((locId != null) || (locId != ""))
					&& ((treasuryyno != null) || (treasuryyno != ""))) {
				lstAlIndiaSerEmp = lObjAlIndSer.getEmployeeList(locId,treasuryyno,ddoCode);
			}
			Boolean flagg = Boolean.valueOf(true);
			if ((lstAlIndiaSerEmp == null)
					|| (lstAlIndiaSerEmp.size() == 0)) {
				inputMap.put("totalRecordsMstContri", Integer.valueOf(0));
				logger.info("flagg*********" + flagg);
				inputMap.put("flagg", flagg);
			} else {
				inputMap.put("totalRecordsMstContri",
						Integer.valueOf(lstAlIndiaSerEmp.size()));
				flagg = Boolean.valueOf(false);
				logger.info("flagg" + flagg);
				inputMap.put("flagg", flagg);
			}
			logger.info("lstAlIndiaSerEmp+++++++++++" + lstAlIndiaSerEmp);

			if (lstAlIndiaSerEmp.size() != 0) {
				inputMap.put("VIEWSELECTEMPLIST", lstAlIndiaSerEmp);
				inputMap.put("Empsize",
						Integer.valueOf(lstAlIndiaSerEmp.size()));
			}
		}

		inputMap.put("check", check);

		resultObject.setViewName("verifyDcpsLegacyData");
		resultObject.setResultCode(0);
		resultObject.setResultValue(inputMap);
	} catch (Exception e) {
		resultObject = new ResultObject(-1);
		resultObject.setResultCode(-1);
		resultObject.setViewName("errorPage");
		logger.error("Error in load employee lists of SRKA on getSRKAEmpsContri () "
				+ e);
	}
	return resultObject;
}


public ResultObject rejectLegacyData(Map objectArgs) throws Exception {
	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	String lStrEmpName = null;
	String lStrSevaarthId = null;
	String ddoCode = null;
	String lStrDcpsId = null;
	List lLstEmpView = null;
	String lStrPeriod=null;
	int rowcount=0;
	String action=null;
	try {
		
		setSessionInfo(objectArgs);
		//setSessionInfo(inputMap);
		//gLngPostId = SessionHelper.getPostId(inputMap);
		LegacyDataEntryDao lObjNewLegDdoDAO = new LegacyDataEntryDaoImpl(MstEmp.class, serv.getSessionFactory());
		//DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
		ddoCode = lObjNewLegDdoDAO. getDdoCode(gLngPostId);
		logger.info("ddoCode*******"+ddoCode);
		String locId=gStrLocationCode;
		logger.info("locId*******"+locId);

			lStrEmpName = StringUtility.getParameter("txtEmployeeName", request).trim().toUpperCase();
			logger.info("txtEmployeeName*******"+lStrEmpName);
			lStrSevaarthId = StringUtility.getParameter("txtSevaarthId", request).trim().toUpperCase();
			logger.info("lStrSevaarthId*******"+lStrSevaarthId);
			lStrDcpsId = StringUtility.getParameter("txtDcpsId", request).trim().toUpperCase();
			
			lStrPeriod=StringUtility.getParameter("Period12", request).trim();
			logger.info("Period12*******"+lStrPeriod);
			
			String remarks=StringUtility.getParameter("remarks", request);
			logger.info("remarks*******"+remarks);
			
		//	lLstEmpDeselect = lObjNewLegDdoDAO.getAllEmp(lStrSevaarthId,lStrEmpName,lStrDcpsId,ddoCode );
			lLstEmpView=lObjNewLegDdoDAO.viewLegacyDataEntry(lStrSevaarthId,lStrEmpName,lStrDcpsId,ddoCode);
		
			
			if((StringUtility.getParameter("txtSevaarthId", request)!=null)&&(StringUtility.getParameter("txtSevaarthId", request)!="")){
			
				String[] lstrempIds = lStrSevaarthId.split("~");
			String[] lstrperiods = lStrPeriod.split("~");
			String[] lstrremarks = remarks.split("~");
				for (Integer lInt = 0; lInt < lstrempIds.length; lInt++)
				{
					rowcount=lObjNewLegDdoDAO.rejectSavedData(lstrempIds[lInt],lstrperiods[lInt],lstrremarks[lInt]);
	              
			
				}
			
				if(rowcount>0)
				{
					action="success";
					
				}
			}
		
		//objectArgs.put("DESELECTEMPLIST", lLstEmpDeselect);
		objectArgs.put("action", action);
		objectArgs.put("VIEWSELECTEMPLIST", lLstEmpView);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("verifyDcpsLegacyData");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
}



}
