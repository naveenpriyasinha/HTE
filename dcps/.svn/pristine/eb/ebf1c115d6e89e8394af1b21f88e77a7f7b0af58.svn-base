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

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;

public class MajorHeadUpdation extends ServiceImpl  {

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


	public ResultObject updateMajorHead(Map inputMap) throws Exception
	{

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			gLogger.info("inside Major Head Updation : updateMajorHead");
			setSessionInfo(inputMap);
			
			String sevarthID="";
			String majorHeadCode="";
            String flag="N";
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			
			majorHeadCode = StringUtility.getParameter("majorHeadCode", request);
			sevarthID = StringUtility.getParameter("sevarthID", request);
            flag = StringUtility.getParameter("flag", request);
            gLogger.info("Major Head Of Account Code :"+majorHeadCode);
            gLogger.info("DDO Code : "+sevarthID);
            gLogger.info("update flag : "+flag);
            
            String arrHeadOfAcctCode[]=majorHeadCode.split("~");
            String arrDDOCode[]=sevarthID.split("~");
            if(flag.equals("Y"))
            {
            	for(int i=0;i<arrDDOCode.length;i++){
                	lObjDcpsCommonDAO.updateMajorHeadOfAccountCode(arrHeadOfAcctCode[i],arrDDOCode[i]);	
                }
            }
            
			
			String ddoCode=lObjDcpsCommonDAO.getDdoCode(Long.parseLong(gStrPostId));
			//Added by Akshay on 22/05/2017
			List lstMajorHeadCodes = IFMSCommonServiceImpl.getLookupValues("Major Head Codes", SessionHelper
					.getLangId(inputMap), inputMap);
			gLogger.info("lstMajorHeadCodes size: "+lstMajorHeadCodes.size());
			inputMap.put("lstMajorHeadCodes", lstMajorHeadCodes);
			//Ended by Akshay on 22/05/2017
		
			gLogger.info("DDOCODE: "+ddoCode);
			List empList = lObjDcpsCommonDAO.getEmpListForMajorHeadUpdation(ddoCode);
			inputMap.put("empList", empList);
			gLogger.info("empListSize: "+empList.size());
			inputMap.put("empListSize", empList.size());
			resObj.setResultValue(inputMap);
			resObj.setViewName("updateMajorHead");

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
