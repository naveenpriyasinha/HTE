package com.tcs.sgv.pensionproc.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.tcs.sgv.pensionproc.dao.Form5DAOImpl;

import edu.emory.mathcs.backport.java.util.Collections;

public class Form5ServiceImpl extends ServiceImpl{

	
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for LangId */
	Long gLngLangId = null;

	/* Global Variable for EmpId */
	Long gLngEmpId = null;

	/* Global Variable for Location Id */
	String gStrLocId = null;

	Long gLngLocId = null;
	/* Global Variable for DB Id */
	Long gLngDBId = null;

	/* Global Variable for Current Date */
	Date gCurDate = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	private Locale gLclLocale = null; /* LOCALE */
	private String gStrPostId = null; /* STRING POST ID */
	private String gStrUserId = null; /* STRING USER ID */
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private HttpSession session = null; /* SESSION */
	private String gStrLocale = null; /* STRING LOCALE */
	private Date gDtCurDate = null; /* CURRENT DATE */

	private static final Log logger = LogFactory.getLog(PensionCaseServiceImpl.class); /* LOGGER */

	private void setSessionInfo(Map inputMap) throws Exception {

		try {
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
			gStrLocId = SessionHelper.getLocationId(inputMap).toString();
			gLngLocId = SessionHelper.getLocationId(inputMap);
		} catch (Exception e) {
			logger.error("Error in setSessionInfo of PensionCaseServiceImpl ", e);
			throw e;
		}
	}

	public ResultObject loadForms(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {

			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String status="no";
			inputMap.put("status", status);
			resObj.setViewName("loadRequestForm");
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
	
	
	public ResultObject getPensionerDtls(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			String status="no";
			String flag="false";
			Object[] pnsrDtls=null;
			String sevaarthId="";
			String name="";
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			String EmpName=StringUtility.getParameter("EmpName", request);
			pnsrDtls=lObjForm5DAO.getPensionerDtls(sevaarthID,EmpName);
			if(pnsrDtls!=null){
				status="yes";
				sevaarthId=pnsrDtls[1].toString();
				name=pnsrDtls[0].toString();
			}
			inputMap.put("sevaarthId", sevaarthId);
			inputMap.put("name", name);
			inputMap.put("status", status);
			inputMap.put("flag", flag);
			inputMap.put("pnsrDtls", pnsrDtls);
			resObj.setViewName("loadRequestForm");
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
	
	public ResultObject checkCaseStatus(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			Object[] pnsrDtls=null;
			String flag="NA";
			String status="yes";
			String sevaarthId="";
			String name="";
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			String EmpName=StringUtility.getParameter("EmpName", request);
			String caseStatus=lObjForm5DAO.checkCaseStatus(sevaarthID);
			if(caseStatus.equals("APRVDBYDDO") || caseStatus.equals("APRVDBYAG") || caseStatus.equals("APRVDBYHO"))
			{
				flag="true";
			}
			pnsrDtls=lObjForm5DAO.getPensionerDtls(sevaarthID,EmpName);
			if(pnsrDtls!=null){
				status="yes";
				sevaarthId=pnsrDtls[1].toString();
				name=pnsrDtls[0].toString();
			}
			inputMap.put("sevID",sevaarthID);
			inputMap.put("name", name);
			inputMap.put("status",status);
			inputMap.put("flag",flag);
			inputMap.put("pnsrDtls", pnsrDtls);
			resObj.setViewName("loadRequestForm");
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
	
	public ResultObject viewForm5Enclosure1(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm5ENCL1");
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
	
	
	public ResultObject viewForm5Enclosure2(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm5ENCL2");
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
	
	public ResultObject viewForm5Enclosure3(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm5ENCL3");
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
	
	public ResultObject viewForm5Enclosure4(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm5ENCL4");
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
	public ResultObject viewForm3(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			List FamilyDtls=lObjForm5DAO.getFamilyDtls(sevaarthID);
			
			inputMap.put("Form5Dtls", Form5Dtls);
			inputMap.put("FamilyDtls", FamilyDtls);
			resObj.setViewName("loadForm3");
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
	
	public ResultObject viewForm5(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			/*String dob=Form5Dtls[1].toString();
			String dateOfBirth[]=dob.split(" ");

			String dor=Form5Dtls[1].toString();
			String dateOfRetirement[]=dor.split(" ");
			*/
			
			/*inputMap.put("dateOfRetirement", dateOfRetirement[0]);
			inputMap.put("dateOfBirth", dateOfBirth[0]);*/
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm5");
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
	
	
	public ResultObject viewForm7(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			
			/*String dob=Form5Dtls[1].toString();
			String dateOfBirth[]=dob.split(" ");

			String dor=Form5Dtls[1].toString();
			String dateOfRetirement[]=dor.split(" ");
			*/
			
			/*inputMap.put("dateOfRetirement", dateOfRetirement[0]);
			inputMap.put("dateOfBirth", dateOfBirth[0]);*/
			inputMap.put("Form5Dtls", Form5Dtls);
			resObj.setViewName("loadForm7");
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
	
	public ResultObject viewForm1(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			Form5DAOImpl lObjForm5DAO = new Form5DAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request);
			Object[] Form5Dtls=lObjForm5DAO.getForm5Dtls(sevaarthID);
			List NomineeDtls=lObjForm5DAO.getNomineeDtls(sevaarthID);
			
			inputMap.put("Form5Dtls", Form5Dtls);
			inputMap.put("NomineeDtls", NomineeDtls);
			resObj.setViewName("loadForm1");
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
	
	
}
