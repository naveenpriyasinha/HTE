package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.BrokenPeriodDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.dao.PayrollReportsDAO;
import com.tcs.sgv.eis.dao.SearchEmpDAOImplPayroll;

public class SearchEmpServiceImplPayroll extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	
	 ResourceBundle constantsBundle = ResourceBundle.getBundle("../resources/Payroll");

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");
	
	
	private void setSessionInfo(Map inputMap) {

		try {
			logger.info("Executing setSessionInfo");
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = Long.parseLong(SessionHelper.getUserId(inputMap).toString());
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			logger.info("gStrPostId"+gStrPostId+"gLngUserId"+gLngUserId+"gDtCurDate"+gDtCurDate);
			logger.info("Execution of setSessionInfo Completed******");
		} catch (Exception e) {

		}

	}
	
	
	public ResultObject getEmpNameForAutoCompletePayroll(Map<String, Object> inputMap) {
		
		logger.info("Inside getEmpNameForAutoCompletePayroll****** ");

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList  = new ArrayList<ComboValuesVO>();
		List finalListForSevarthId  = new ArrayList<ComboValuesVO>();
		List finalListFromEmpLname  = new ArrayList<ComboValuesVO>();
		String lStrEmpName = null;
		String lStrSearchBy = null;
		String lStrDDOCode = null;
		
		try {
			setSessionInfo(inputMap);
			SearchEmpDAOImplPayroll lObjSearchEmployeeDAO = new SearchEmpDAOImplPayroll(
					null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			lStrEmpName = StringUtility.getParameter("searchKey", request).toString().trim();
			
			lStrSearchBy = StringUtility.getParameter("searchBy", request).toString().trim();

			if(lStrSearchBy.equals("searchByDDOAsst"))  
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			}
			if(lStrSearchBy.equals("searchFromDDOSelection") || lStrSearchBy.equals("searchFromDDODeSelection"))
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			if(lStrSearchBy.equals("searchBySRKA"))
			{
				lStrDDOCode = null;
				logger.info("Inside Search By SRKA----->"+lStrEmpName.toString());
			}
					
			
			finalList = lObjSearchEmployeeDAO.getEmpNameForAutoComplete(lStrEmpName.toUpperCase(),lStrDDOCode);

			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			String locId=loginDetailsMap.get("locationId").toString();
			long langId=1;
			finalListForSevarthId = lObjSearchEmployeeDAO.getEmpNameForAutoCompleteFromSevarthId(lStrEmpName.toUpperCase(), locId,langId);
			finalListFromEmpLname = lObjSearchEmployeeDAO.getEmpNameForAutoCompleteForPayrollFromEmpLname(lStrEmpName.toUpperCase(), locId,langId);
			
			logger.info("In getEmpNameForAutoCompletePayroll finalList size is **********"+finalList.size());
			logger.info("In getEmpNameForAutoCompletePayroll finalListForSevarthId size is **********"+finalListForSevarthId.size());
			logger.info("In getEmpNameForAutoCompletePayroll finalListFromEmpLname size is **********"+finalListFromEmpLname.size());
			
			String lStrTempResult = null;
			if (finalList != null && finalList.size()>0 ) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList, "desc", "id", true).toString();
			}
			else if (finalListFromEmpLname != null && finalListFromEmpLname.size()>0 ) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalListFromEmpLname, "desc", "id", true).toString();
			}
			else
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(finalListForSevarthId, "desc", "id", true).toString();
			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			ex.printStackTrace();
			return objRes;
		}

		return objRes;

	}


}
