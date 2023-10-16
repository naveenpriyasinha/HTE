package com.tcs.sgv.gpf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.gpf.dao.GPFApprovedRequestDAO;
import com.tcs.sgv.gpf.dao.GPFApprovedRequestDAOImpl;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAO;
import com.tcs.sgv.gpf.dao.GPFRequestProcessDAOImpl;
import com.tcs.sgv.gpf.valueobject.MstGpfAdvance;

/**
 * @author 397138
 * 
 */
public class GPFApprovedRequestServiceImpl extends ServiceImpl implements GPFApprovedRequestService {
	Log glogger = LogFactory.getLog(getClass());

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	private void setSessionInfo(Map inputMap) {

		try {			
			gLngPostId = SessionHelper.getPostId(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
		} catch (Exception e) {
			glogger.error("Error is :" + e, e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tcs.sgv.gpf.service.GPFApprovedRequestService#gpfApprovedList(java
	 * .util.Map)
	 */
	public ResultObject gpfApprovedList(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		try {
			setSessionInfo(inputMap);
			List lGpfApprovedList = new ArrayList();
			GPFRequestProcessDAO lObjGPFReqProcess = new GPFRequestProcessDAOImpl(null, serv.getSessionFactory());
			// String lStrDdoCode =
			// lObjGPFReqProcess.getDdoCodeForDDO(gLngPostId);
			String lStrLocationCode = lObjGPFReqProcess.getLocationCodeOfUser(gLngPostId);
			GPFApprovedRequestDAO lObjGPFApprovedRequestDAO = new GPFApprovedRequestDAOImpl(MstGpfAdvance.class, serv
					.getSessionFactory());
			lGpfApprovedList = lObjGPFApprovedRequestDAO.getGPFApprovedRequestList(lStrLocationCode);
			inputMap.put("gpfApprovedList", lGpfApprovedList);
			inputMap.put("totalRecords", lGpfApprovedList.size());
		} catch (Exception e) {
			IFMSCommonServiceImpl.setErrorProperties(glogger, resObj, e, "Error is: ");
		}

		resObj.setResultValue(inputMap);
		resObj.setViewName("GPFApprovedRequest");

		return resObj;
	}
}
