package com.tcs.sgv.pensionproc.service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.ibm.wsdl.ServiceImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

import com.tcs.sgv.pensionproc.dao.ChangeDdoCodeDaoImpl;
import com.tcs.sgv.core.service.ServiceImpl;

public class ChangeDdoCodeServiceImpl extends ServiceImpl{
	
	Long gLngPostId = null;
	private static final long serialVersionUID = 1L;
	static URI serverURI;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for LangId */
	Long gLngLangId = null;

	/* Global Variable for EmpId */
	Long gLngEmpId = null;

	/* Global Variable for Location Id */
	String gStrLocId = null;

	/* Global Variable for DB Id */
	Long gLngDBId = null;

	/* Global Variable for Current Date */
	Date gCurDate = null;
	Date gCurrDate = null;
	/* Global Variable for Location Code */
	String gStrLocCode = null;
	Long gDbId = null;
	String gStrLocShortName = null;
	private Log gLogger = LogFactory.getLog(getClass());
	Long gLngLocId = null;

	private Date gDate = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private Log logger = LogFactory.getLog(getClass()); /* LOGGER */

	private void setSessionInfo(Map<String, Object> inputMap) {
		request = (HttpServletRequest) inputMap.get("requestObj");
		request.getSession();
		gLngLangId = SessionHelper.getLangId(inputMap);
		gLngPostId = SessionHelper.getPostId(inputMap);
		gLngUserId = SessionHelper.getUserId(inputMap);
		gStrLocCode = SessionHelper.getLocationCode(inputMap);
		gDate = DBUtility.getCurrentDateFromDB();
		gDbId = SessionHelper.getDbId(inputMap);
		gStrLocShortName = SessionHelper.getLocationVO(inputMap).getLocShortName();
		serv = (ServiceLocator) inputMap.get("serviceLocator");
	}
	
	public ResultObject loadUpdateDdoCode(Map inputMap) {
		
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String sevarthId="";
		String ddoCode="";
		List result= null;
		try {
			setSessionInfo(inputMap);
			resObj.setViewName("loadUpdateDdoCode");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
		

	public ResultObject populateDetails(Map inputMap){
		
		String fname= "";
		String status= "";
		String dor= "";
		String currDdo= "";
		String currDdoName= "";
		String lStrResult = "Fail";
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Object[] details = null;
		
		try {
			setSessionInfo(inputMap);
			ChangeDdoCodeDaoImpl ddoCodeDao = new ChangeDdoCodeDaoImpl(null, serv.getSessionFactory());
			String sevarthId = StringUtility.getParameter("sevarthId", request);
			System.out.println("sevarthId+++++"+sevarthId);
			details=ddoCodeDao.getPensionerDtls(sevarthId);
			//System.out.println("size is+++++"+details.length);
			String flag="Empty Details";
			if (details != null){
				flag="details";
				
			}
			if (details != null) {
				fname = (details[0] != null) ? (details[0].toString()) : "-";
				dor = (details[1] != null) ? (details[1].toString()): "-";
				currDdo=(details[2] != null) ? (details[2].toString()): "-";
				currDdoName = (details[3] != null) ? (details[3].toString()) : "-";
				System.out.println("Inside if");
			}
			lStrResult = getResponseXMLDocForpopulateDetails(fname,"true",dor,currDdo,currDdoName,flag).toString();
			resObj.setResultCode(ErrorConstants.SUCCESS);
		
	}
		catch (Exception e) {
			gLogger.error("Error is:" + e, e);
			resObj.setResultValue(null);
			lStrResult = getResponseXMLDocForpopulateDetails(null,"false",null,null,null,null).toString();
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		 lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrResult).toString();

			inputMap.put("ajaxKey", lStrResult);
     		resObj.setResultValue(inputMap);
     		resObj.setViewName("ajaxData");
     		return resObj;

}
	private StringBuilder getResponseXMLDocForpopulateDetails(String fname,String status,String dor,String currDdo,String currDdoName,String flag) {
		StringBuilder lSB = new StringBuilder();
		lSB.append("<XMLDOC>");
		
		lSB.append("<FNAME>");
		lSB.append(fname);
		lSB.append("</FNAME>");

		lSB.append("<STATUS>");
		lSB.append(status);
		lSB.append("</STATUS>");
		
		lSB.append("<DOR>");
		lSB.append(dor);
		lSB.append("</DOR>");

		lSB.append("<CURRDDO>");
		lSB.append(currDdo);
		lSB.append("</CURRDDO>");
		
		lSB.append("<CURRDDONAME>");
		lSB.append(currDdoName);
		lSB.append("</CURRDDONAME>");
		
		lSB.append("<FLAG>");
		lSB.append(flag);
		lSB.append("</FLAG>");
		
		lSB.append("</XMLDOC>");
		// System.out.println(name);
		return lSB;
	}
	
	public ResultObject populateDdoOfc(Map inputMap){
		
		String ddo_offc="";
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Object[] details = null;
		String lStrResult="Fail";
		
		try {
			setSessionInfo(inputMap);
			ChangeDdoCodeDaoImpl ddoCodeDao = new ChangeDdoCodeDaoImpl(null, serv.getSessionFactory());
			String ddoCode = StringUtility.getParameter("newDdo", request);
			System.out.println("ddoCode+++++"+ddoCode);
			details=ddoCodeDao.getNewDdoOfc(ddoCode);
			
			if (details != null) {
				ddo_offc = (details[0] != null) ? (details[0].toString()) : "-";
				lStrResult = getResponseXMLDocForpopulateDdoOfc(ddo_offc).toString();
				resObj.setResultCode(ErrorConstants.SUCCESS);
				
			}
		}
				catch (Exception e) {
					gLogger.error("Error is:" + e, e);
					resObj.setResultValue(null);
					lStrResult = getResponseXMLDocForpopulateDdoOfc(null).toString();
					resObj.setThrowable(e);
					resObj.setResultCode(ErrorConstants.ERROR);
					resObj.setViewName("errorPage");
				}
				 lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrResult).toString();

					inputMap.put("ajaxKey", lStrResult);
		     		resObj.setResultValue(inputMap);
		     		resObj.setViewName("ajaxData");
		     		return resObj;

	}
	
	private StringBuilder getResponseXMLDocForpopulateDdoOfc(String ddo_offc) {
		
		StringBuilder lSB = new StringBuilder();
		
		lSB.append("<XMLDOC>");
		
		lSB.append("<DDOOFFC>");
		lSB.append(ddo_offc);
		lSB.append("</DDOOFFC>");

		lSB.append("</XMLDOC>");
		// System.out.println(name);
		return lSB;
	
	}
	
	
	public ResultObject changeDdoCode(Map inputMap) {
		
		//gLogger.error("updateBB is:" );
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		//String sevarthId = "";
		//String newDdo="";
		String lSBStatus = "false";
		
		try {
			setSessionInfo(inputMap);
			ChangeDdoCodeDaoImpl ddoCodeDao = new ChangeDdoCodeDaoImpl(null, serv.getSessionFactory());
			
			String sevarthid = StringUtility.getParameter("sevarthId", request);
			String ddoCode = StringUtility.getParameter("newDdo", request);
			String prevDdoCode=StringUtility.getParameter("currDdo", request);
			String remark=StringUtility.getParameter("remark", request); 
			System.out.println("sevarthId:"+sevarthid);
			System.out.println("ddoCode:"+ddoCode);
			System.out.println("prevDdoCode:"+prevDdoCode);
			System.out.println("remark:"+remark);
		int result=ddoCodeDao.updateDdoCode(sevarthid, ddoCode, prevDdoCode, remark);
		if(result > 0){
			
			lSBStatus = getResponseXMLchangeDdoCode("true").toString();	
		}
		else
			lSBStatus = getResponseXMLchangeDdoCode("false").toString();

		gLogger.error("getResponseXMLupdateBB lSBStatus is:" +lSBStatus);
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
				lSBStatus).toString();

		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");

	} catch (Exception e) {
		gLogger.error("Error is:" + e, e);
		resObj.setResultValue(null);
		lSBStatus = getResponseXMLchangeDdoCode("Fail").toString();
		resObj.setThrowable(e);
		resObj.setResultCode(ErrorConstants.ERROR);
		resObj.setViewName("errorPage");
	}

	return resObj;
		}
		
	private StringBuilder getResponseXMLchangeDdoCode(String lStrMessage) {
		StringBuilder lSB = new StringBuilder();
	
		lSB.append("<STATUS>");
		lSB.append(lStrMessage);
		lSB.append("</STATUS>");
	
		System.out.println(lStrMessage);
		return lSB;
	}
		
		
}

