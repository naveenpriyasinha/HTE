package com.tcs.sgv.dcps.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.dao.SancBudgetDAO;
import com.tcs.sgv.dcps.dao.SancBudgetDAOImpl;
import com.tcs.sgv.dcps.dao.TickerMessageDAO;
import com.tcs.sgv.dcps.dao.TickerMessageDAOImpl;
import com.tcs.sgv.dcps.valueobject.NoticeBoard;
import com.tcs.sgv.dcps.valueobject.SanctionedBudget;
import com.tcs.sgv.dcps.valueobject.TickerMessage;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;

public class TickerMessageServiceImpl extends ServiceImpl implements
TickerMessageService{
	

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gStrPostId = SessionHelper.getPostId(inputMap).toString();
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}
	}
	public ResultObject saveTickerMessage(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBFlag = false;
		
		try{
			/* Sets the Session Information */
			setSessionInfo(inputMap);
	
			TickerMessage objTickerMessage = (TickerMessage)inputMap.get("TickerMessage");

			TickerMessageDAO dcpsTickerMessage = new TickerMessageDAOImpl(
					TickerMessageDAO.class, serv.getSessionFactory());
			
			dcpsTickerMessage.create(objTickerMessage);
			lBFlag = true;
		} catch (Exception e) {
		
			e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			gLogger.error(" Error in getDigiSig " + e, e);
		}
		String lSBStatus = getResponseXMLDoc(lBFlag).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();
		
		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}
	private StringBuilder getResponseXMLDoc(boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("  <Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("  </Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	public ResultObject showTickerMessage(Map inputMap){
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			setSessionInfo(inputMap);
			
			TickerMessageDAO lObjTickerMessageDAO = new TickerMessageDAOImpl(null, serv
					.getSessionFactory());
			String strTickerMessage = lObjTickerMessageDAO.getTickerMessage();

			inputMap.put("TickerMessage", strTickerMessage);
		
		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			e.printStackTrace();
		}
		
		resObj.setResultValue(inputMap);
		resObj.setViewName("DCPSTickerMessage");
		return resObj;
	}
}
