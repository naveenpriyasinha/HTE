package com.tcs.sgv.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CHBCalculationDAOImpl;
import com.tcs.sgv.common.dao.UpdateInstitutionNameDAO;
import com.tcs.sgv.common.dao.UpdateInstitutionNameDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;

public class UpdateInstitutionNameServiceImpl extends ServiceImpl{

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

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
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap)
	{

		try
		{
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		}
		catch (Exception e)
		{

		}

	}
	
	public ResultObject updateInstitutionName(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

			UpdateInstitutionNameDAO updateInstitutionNameDAO = null;
			List lLstReasons = null;
			
			String orgDdoCode = null;
			String orgInstName = null;
			

			try {
				setSessionInfo(inputMap);

				updateInstitutionNameDAO = new UpdateInstitutionNameDAOImpl(UpdateInstitutionNameServiceImpl.class,
						serv.getSessionFactory());
				DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
						.getSessionFactory());

				lLstReasons = IFMSCommonServiceImpl.getLookupValues(
						"Reason For Deslection", gLngLangId, inputMap);
				
				orgDdoCode = StringUtility.getParameter("orgDdoCode",request);
				orgInstName = StringUtility.getParameter("orgInstName",request);
				
				updateInstitutionNameDAO.updateOrgInstName(orgDdoCode,orgInstName);
				
				objRes.setResultValue(inputMap);
				objRes.setViewName("updateInstitutionName");

			}

			catch (Exception e) {
				//e.printStackTrace();
				gLogger.error("Exception is " + e, e);
				objRes.setResultValue(null);
				objRes.setThrowable(e);
				objRes.setResultCode(ErrorConstants.ERROR);
				objRes.setViewName("errorPage");
			}

			return objRes;
		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	public ResultObject getInstitutionName(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		
		setSessionInfo(inputMap);
		UpdateInstitutionNameDAO updateInstitutionNameDAO = null;
		updateInstitutionNameDAO = new UpdateInstitutionNameDAOImpl(UpdateInstitutionNameServiceImpl.class,
				serv.getSessionFactory());

		try
		{
			
		 
			List lStrInstitutionName = null;
			String DDOCodeForname = StringUtility.getParameter("DDOCodeForname", request).trim();
			gLogger.info("name **********"+DDOCodeForname);
			
			lStrInstitutionName = updateInstitutionNameDAO.getAllData(DDOCodeForname);
			gLogger.info("lStrInstitutionName **********"+lStrInstitutionName);
			
			inputMap.put("lStrInstitutionName", lStrInstitutionName);
			resObj.setResultValue(inputMap);
			resObj.setViewName("updateInstitutionName");
			 
		}
		catch (Exception e)
		{
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	
}
