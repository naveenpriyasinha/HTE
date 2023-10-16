package com.tcs.sgv.nps.service;

 

import java.io.BufferedOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.util.StackableException;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/*import com.tcs.sgv.dcps.dao.NewRegTreasuryDAO;
import com.tcs.sgv.dcps.dao.NewRegTreasuryDAOImpl;*/
import com.tcs.sgv.dcps.dao.SearchEmployeeDAO;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAOImpl;
import com.tcs.sgv.nps.dao.UpdatePranNoDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;


public class UpdatePranNoServiceImpl extends ServiceImpl { 
	/* Global Variable for Logger Class */
	private final Log logger = LogFactory.getLog(getClass());

	
	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private HttpServletResponse response = null;
	private HttpServletResponse response1 = null;/* RESPONSE OBJECT */

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
	List lstemployee = null;
	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

	private void setSessionInfo(Map inputMap) {
		try {
			response = (HttpServletResponse) inputMap.get("responseObj");
			response1 = (HttpServletResponse) inputMap.get("responseObj");
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
		} catch (Exception e) {

		}

	}

	public ResultObject testPranNO(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		List pranExist = null;

		try {

			setSessionInfo(inputMap);
			UpdatePranNoDAOImpl lObjupdatePr = new UpdatePranNoDAOImpl(null,
					serv.getSessionFactory());

			String Pran_no = StringUtility.getParameter("pranno", request)
					.trim();

			if (!"".equals(Pran_no)) {
				pranExist = lObjupdatePr.testPranNO(Pran_no);
			}
			String empName = "NA";
			String dcpsId = "NA";
			if (pranExist.size() != 0) {
				Object[] tuple = (Object[]) pranExist.get(0);
				lBlFlag = true;
				empName = tuple[0].toString();
				dcpsId = tuple[1].toString();
			}

			StringBuffer strbuflag = new StringBuffer();
			strbuflag.append("<XMLDOC>");
			strbuflag.append("<Flag>");
			strbuflag.append(lBlFlag);
			strbuflag.append("</Flag>");
			strbuflag.append("<EmpName>");
			strbuflag.append(empName);
			strbuflag.append("</EmpName>");
			strbuflag.append("<DcpsId>");
			strbuflag.append(dcpsId);
			strbuflag.append("</DcpsId>");
			strbuflag.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					strbuflag.toString()).toString();
			System.out.println("Return value"+lStrResult);
			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			// e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in testpranno " + e, e);
		}

		return resObj;

	}

	public ResultObject loadEmpList(Map inputMap) throws Exception {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrEmpName = null;
		String lStrSevaarthId = null;
		String ddoCode = null;
		String lStrDcpsId = null;
		String lStrPpanNo = null;
		List lLstEmpDeselect = null;

		try {
			setSessionInfo(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			UpdatePranNoDAOImpl objUpdateDao = new UpdatePranNoDAOImpl(null,
					serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					serv.getSessionFactory());
			ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			logger.info("ddoCode*******" + ddoCode);
			String locId = gStrLocationCode;
			logger.info("locId*******" + locId);

			if (StringUtility.getParameter("fromSearch", request).trim()
					.equals("Yes")) {
				lStrEmpName = StringUtility
						.getParameter("txtEmployeeName", request).trim()
						.toUpperCase();
				lStrSevaarthId = StringUtility
						.getParameter("txtSevaarthId", request).trim()
						.toUpperCase();
				lStrDcpsId = StringUtility.getParameter("txtDcpsId", request)
						.trim().toUpperCase();
				lStrPpanNo = StringUtility.getParameter("txtPpanNo", request)
						.trim().toUpperCase();
				// lLstEmpDeselect =
				// objUpdateDao.getAllEmp(lStrSevaarthId,lStrEmpName,lStrDcpsId,lStrPpanNo,ddoCode
				// );
				lLstEmpDeselect = objUpdateDao.getAllEmp(lStrSevaarthId,
						lStrEmpName, lStrDcpsId, lStrPpanNo,ddoCode);
				if (lLstEmpDeselect == null || lLstEmpDeselect.size() == 0) {
					logger.info("in if **************");

					lLstEmpDeselect = objUpdateDao
							.getAllEmpActive(lStrSevaarthId, lStrEmpName,
									lStrDcpsId, lStrPpanNo);
				}

			}

			String postExpFlag = "";
			String postEndDate = "";

			if (lLstEmpDeselect != null && lLstEmpDeselect.size() > 0) {
				Object[] empLst = (Object[]) lLstEmpDeselect.get(0);
				postExpFlag = empLst[9].toString().trim();
				postEndDate = empLst[10].toString().trim();
			}

			logger.info("postExpFlag  :  " + postExpFlag);
			logger.info("postEndDate  :  " + postEndDate);
			inputMap.put("postExpFlag", postExpFlag);
			inputMap.put("postEndDate", postEndDate);
			inputMap.put("DESELECTEMPLIST", lLstEmpDeselect);
			objRes.setResultValue(inputMap);
			objRes.setViewName("updatePranNoForNPS");

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject checkSevaarthId(Map inputMap) throws Exception {
		logger.info("Inside checkSevaarthId ");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Object[] exist = null;
		String lStrSevaarthId = null;
		String lStrDDOCode = null;
		String dcpsOrGpf = null;
		Date servEndDate = null;
		String serviceEndDate = null;
		String accMain = null;
		String NotExist = "";
		Date dateOfJoin = null;
		String pranNo = null;
		String ddoCode = null;
		String ppanNo = null;

		String lStrEmpname = null;
		String lStrDcpsId = null;
		// String lStrPpanNo = null;

		try {
			setSessionInfo(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			UpdatePranNoDAOImpl lObjcheck = new UpdatePranNoDAOImpl(null,
					serv.getSessionFactory());
			lStrSevaarthId = StringUtility
					.getParameter("txtSevaarthId", request).trim()
					.toUpperCase();
			lStrEmpname = StringUtility
					.getParameter("txtEmployeeName", request).trim()
					.toUpperCase();
			lStrDcpsId = StringUtility.getParameter("txtDcpsId", request)
					.trim().toUpperCase();
			// lStrPpanNo = StringUtility.getParameter("txtPpanNo",
			// request).trim().toUpperCase();

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					serv.getSessionFactory());
			lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date current = new Date();
			logger.info(dateFormat.format(current));

			Date currentdt = dateFormat.parse(dateFormat.format(current));
			Date doj = dateFormat.parse("01-11-2005");
			logger.info("doj*******" + doj);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// sdf.format(date1).equals(sdf.format(date2));

			logger.info("Inside checkSevaarthId ");
			logger.info("currentdt:::" + currentdt);
			List existList = lObjcheck.checkSevaarthIdExist(lStrSevaarthId,
					lStrDDOCode, lStrEmpname, lStrDcpsId);
			logger.info("existList  size::::::::::" + existList.size());

			label: if (existList != null && existList.size() > 0) {
				Object[] tuple = (Object[]) existList.get(0);

				dcpsOrGpf = tuple[0].toString();
				servEndDate = sdf.parse(tuple[1].toString());
				serviceEndDate = tuple[1].toString();
				logger.info("########servEndDate" + servEndDate);
				accMain = tuple[2].toString();
				dateOfJoin = sdf.parse(tuple[3].toString());
				logger.info("########dateOfJoin" + dateOfJoin);
				pranNo = tuple[4].toString();
				ddoCode = tuple[5].toString();
				ppanNo = tuple[6].toString();

				logger.info("days:::" + daysBetween(servEndDate, currentdt));
				Integer days = servEndDate.compareTo(currentdt);
				Integer days1 = dateOfJoin.compareTo(doj); // daysBetween(dateOfJoin,doj);

				logger.info("days1****" + days1);
				logger.info("ddocode1**********" + ddoCode);
				logger.info("ddocode2**********" + lStrDDOCode);

				String dCode = ddoCode.substring(0, 4);
				logger.info("dcpsOrGpf**" + dcpsOrGpf);

				// logger.info("dCode##"+dCode+"###loc_id##"+gStrLocationCode);

				// if(!dCode.equals(gStrLocationCode))
				// { logger.info("Hello!!");
				// NotExist="NA6";
				// break label;
				// }
				if (ppanNo.equals("0") && dcpsOrGpf.equals("Y")) {
					logger.info("ppanNo********" + ppanNo);
					NotExist = "NA7";
					break label;
				}
				if (!dcpsOrGpf.equals("Y")) {
					logger.info("dcpsOrGpf**" + dcpsOrGpf);
					NotExist = "NA1";
					break label;
				}
				if (serviceEndDate.equals("01-01-1900")) {
					logger.info("serviceEndDate******" + serviceEndDate);
					NotExist = "NA2";
					break label;
				}
				/*
				 * if (days>1 ) { NotExist="NA2"; break label; }
				 */
				// if (!accMain.equals("700174") && !accMain.equals("700240") &&
				// !accMain.equals("700241") && !accMain.equals("700242"))
				// {
				// NotExist="NA3";
				// break label;
				// }
				if (dateOfJoin.equals("NA") ||  dcpsOrGpf.equals("N") ) {
					NotExist = "NA4";
					break label;
				}
				if (days1 < 0) {
					NotExist = "NA4";
					break label;
				}
				if (!pranNo.equals("#")) {
					NotExist = "NA5";
					break label;
				}

			} else {
				NotExist = "NA";
				break label;

			}

			StringBuffer strbuflag = new StringBuffer();
			strbuflag.append("<XMLDOC>");
			strbuflag.append("<Flag>");
			strbuflag.append(NotExist);
			strbuflag.append("</Flag>");
			strbuflag.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					strbuflag.toString()).toString();
System.out.println("HI Herer"+strbuflag.toString());
			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public static Integer daysBetween(Date sPassedDate, Date ePassedDate) {

		Calendar sDate = Calendar.getInstance();
		sDate.setTime(sPassedDate);
		Calendar eDate = Calendar.getInstance();
		eDate.setTime(ePassedDate);

		Calendar d = (Calendar) sDate.clone();
		Integer dBetween = 0;
		while (d.before(eDate)) {
			d.add(Calendar.DAY_OF_MONTH, 1);
			dBetween++;
		}
		if (dBetween == 0 && d.equals(eDate)) {
			dBetween = 1;
		}

		else if (dBetween == 0 && d.after(eDate)) {
			dBetween = 0;
		}
		return dBetween;
	}

	public ResultObject getEmpNameAutoCompleteForNPS(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;
		String lStrEmpName = null;
		String lStrSearchBy = null;
		String lStrDDOCode = null;
		String lStrSearchType = null;

		try {
			setSessionInfo(inputMap);
			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					serv.getSessionFactory());

			lStrEmpName = StringUtility.getParameter("searchKey", request)
					.trim();
			logger.info("lStrEmpName*******" + lStrEmpName);

			lStrSearchBy = StringUtility.getParameter("searchBy", request)
					.trim();

			logger.info("lStrSearchBy*******" + lStrSearchBy);
			lStrSearchType = StringUtility.getParameter("searchType", request);

			if (lStrSearchBy.equals("searchByDDOAsst")) {

				lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			}
			if (lStrSearchBy.equals("searchFromDDODeSelection")
					|| lStrSearchBy.equals("searchByDDO")) {
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
				logger.info("lStrDDOCode*******" + lStrDDOCode);
			}

			logger.info("gLngPostId*****" + gLngPostId);
			if (lStrSearchBy.equals("searchBySRKA")) {
				// commented by vaibhav tyagi
				// lStrDDOCode = null;
				// added by vaibhav tyagi : start
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
				// added by vaibhav tyagi : end
			}
			logger.info("lStrDDOCode*******" + lStrDDOCode);

			finalList = lObjSearchEmployeeDAO.getEmpNameForAutoComplete(
					lStrEmpName.toUpperCase(), lStrSearchType, lStrDDOCode,
					lStrSearchBy);

			String lStrTempResult = null;
			if (finalList != null) {
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList,
						"desc", "id", true).toString();

			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			// ex.printStackTrace();
			return objRes;
		}

		return objRes;

	}

	public ResultObject updatePranNo(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String sevarthId = null;
		String pranNo = null;
		String flag = null;

		try {
			setSessionInfo(inputMap);
			logger.info("inside update pran no");
			sevarthId = StringUtility.getParameter("sevarthId", request).trim();
			pranNo = StringUtility.getParameter("pranNo", request).trim();

			UpdatePranNoDAOImpl objUpdatePran = new UpdatePranNoDAOImpl(null,
					serv.getSessionFactory());
			flag = objUpdatePran.updatePranNo(sevarthId, pranNo);
			logger.info("FLAG:" + flag);
			logger.info("sevarthId" + sevarthId);
			logger.info("pranNo" + pranNo);

			StringBuffer strbuflag = new StringBuffer();
			strbuflag.append("<XMLDOC>");
			strbuflag.append("<Flag>");
			strbuflag.append(flag);
			strbuflag.append("</Flag>");
			strbuflag.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					strbuflag.toString()).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);
			logger.info("Flag is  ********" + flag);

			/*
			 * objRes.setResultValue(inputMap);
			 * objRes.setViewName("updatePranNo");
			 */

		}

		catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			// e.printStackTrace();
			// return objRes;
			// TODO: handle exception
		}
		return objRes;
	}

	/* Added By Shivram Below 4 methods 02092020 */

	public ResultObject loadPranNoSearchPage(Map inputMap) throws Exception {
		ResultObject objRes = new ResultObject(0, "FAIL");
		String ddoCode = null;
		String lStrPpanNo = null;
		List lLstEmpDeselect = null;
		try {
			setSessionInfo(inputMap);
			this.gLngPostId = SessionHelper.getPostId(inputMap);
			UpdatePranNoDAOImpl objUpdateDao = new UpdatePranNoDAOImpl(null,
					this.serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					this.serv.getSessionFactory());
			ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);
			this.logger.info("ddoCode*******" + ddoCode);
			String locId = this.gStrLocationCode;
			this.logger.info("locId*******" + locId);
			if (StringUtility.getParameter("fromSearch", this.request).trim()
					.equals("Yes")) {
				lStrPpanNo = StringUtility
						.getParameter("txtPpanNo", this.request).trim()
						.toUpperCase();

				lLstEmpDeselect = objUpdateDao
						.getAllEmpForPranActDeact(lStrPpanNo);
				if ((lLstEmpDeselect == null) || (lLstEmpDeselect.size() == 0)) {
					this.logger.info("in if **************");

					lLstEmpDeselect = objUpdateDao
							.getAllEmpForActive(lStrPpanNo);
				}
			}
			String postExpFlag = "";
			String postEndDate = "";
			if ((lLstEmpDeselect != null) && (lLstEmpDeselect.size() > 0)) {
				Object[] empLst = (Object[]) lLstEmpDeselect.get(0);
				postExpFlag = empLst[9].toString().trim();
				postEndDate = empLst[10].toString().trim();
			}
			this.logger.info("postExpFlag  :  " + postExpFlag);
			this.logger.info("postEndDate  :  " + postEndDate);
			inputMap.put("postExpFlag", postExpFlag);
			inputMap.put("postEndDate", postEndDate);
			inputMap.put("DESELECTEMPLIST", lLstEmpDeselect);
			objRes.setResultValue(inputMap);
			this.logger.info("viru");
			objRes.setViewName("updatePranNoForActDeactivate");
		} catch (Exception e) {
			this.logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject checkPranNo(Map inputMap) throws Exception {
		this.logger.info("Inside checkPranNo ");
		ResultObject objRes = new ResultObject(0, "FAIL");
		Object[] exist = null;
		String txtPranNo = null;
		String lStrDDOCode = null;
		String dcpsOrGpf = null;
		Date servEndDate = null;
		String serviceEndDate = null;
		String accMain = null;
		String NotExist = "";
		Date dateOfJoin = null;
		String pranNo = null;
		String ddoCode = null;
		String ppanNo = null;

		String lStrEmpname = null;
		String lStrDcpsId = null;
		try {
			setSessionInfo(inputMap);
			this.gLngPostId = SessionHelper.getPostId(inputMap);
			UpdatePranNoDAOImpl lObjcheck = new UpdatePranNoDAOImpl(null,
					this.serv.getSessionFactory());
			txtPranNo = StringUtility.getParameter("txtPranNo", this.request)
					.trim().toUpperCase();

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					this.serv.getSessionFactory());
			lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date current = new Date();
			this.logger.info(dateFormat.format(current));

			Date currentdt = dateFormat.parse(dateFormat.format(current));
			Date doj = dateFormat.parse("01-11-2005");
			this.logger.info("doj*******" + doj);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			this.logger.info("Inside txtPranNo ");
			this.logger.info("currentdt:::" + currentdt);
			List existList = lObjcheck.checkPranNoExist(txtPranNo, lStrDDOCode);
			this.logger.info("existList  size:::::: ::::" + existList.size());
			if ((existList != null) && (existList.size() > 0)) {
				Object[] tuple = (Object[]) existList.get(0);

				dcpsOrGpf = tuple[0].toString();
				servEndDate = sdf.parse(tuple[1].toString());
				serviceEndDate = tuple[1].toString();
				this.logger.info("########servEndDate" + servEndDate);
				accMain = tuple[2].toString();
				dateOfJoin = sdf.parse(tuple[3].toString());
				this.logger.info("########dateOfJoin" + dateOfJoin);
				pranNo = tuple[4].toString();
				ddoCode = tuple[5].toString();
				ppanNo = tuple[6].toString();

				this.logger.info("days:::"
						+ daysBetween(servEndDate, currentdt));
				Integer days = Integer
						.valueOf(servEndDate.compareTo(currentdt));
				Integer days1 = Integer.valueOf(dateOfJoin.compareTo(doj));

				this.logger.info("days1****" + days1);
				this.logger.info("ddocode1**********" + ddoCode);
				this.logger.info("ddocode2**********" + lStrDDOCode);

				String dCode = ddoCode.substring(0, 4);
				this.logger.info("dcpsOrGpf**" + dcpsOrGpf);
				if (ppanNo.equals("0")) {
					this.logger.info("ppanNo********" + ppanNo);
					NotExist = "NA7";
				} else if (!dcpsOrGpf.equals("Y")) {
					this.logger.info("dcpsOrGpf**" + dcpsOrGpf);
					NotExist = "NA1";
				} else if (serviceEndDate.equals("01-01-1900")) {
					this.logger.info("serviceEndDate******" + serviceEndDate);
					NotExist = "NA2";
				} else if (dateOfJoin.equals("NA")) {
					NotExist = "NA4";
				} else if (days1.intValue() < 0) {
					NotExist = "NA4";
				} else if (!pranNo.equals("#")) {
					NotExist = "NA5";
				}
			} else {
				NotExist = "NA";
			}
			StringBuffer strbuflag = new StringBuffer();
			strbuflag.append("<XMLDOC>");
			strbuflag.append("<Flag>");
			strbuflag.append(NotExist);
			strbuflag.append("</Flag>");
			strbuflag.append("</XMLDOC>");
System.out.println("PPS"+strbuflag.toString());
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					strbuflag.toString()).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			this.logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject empListForPranNo(Map inputMap) throws Exception {
		ResultObject objRes = new ResultObject(0, "FAIL");
		String ddoCode = null;
		String txtPranNo = null;
		List lLstEmpDeselect = null;
		try {
			setSessionInfo(inputMap);
			this.gLngPostId = SessionHelper.getPostId(inputMap);
			UpdatePranNoDAOImpl objUpdateDao = new UpdatePranNoDAOImpl(null,
					this.serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null,
					this.serv.getSessionFactory());
			ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(this.gLngPostId);
			this.logger.info("ddoCode*******" + ddoCode);
			String locId = this.gStrLocationCode;
			this.logger.info("locId*******" + locId);
			if (StringUtility.getParameter("fromSearch", this.request).trim()
					.equals("Yes")) {
				txtPranNo = StringUtility
						.getParameter("txtPranNo", this.request).trim()
						.toUpperCase();

				lLstEmpDeselect = objUpdateDao
						.getAllEmpForPranActDeact(txtPranNo);
				if ((lLstEmpDeselect == null) || (lLstEmpDeselect.size() == 0)) {
					this.logger.info("blank list  **************");

					lLstEmpDeselect = objUpdateDao
							.getAllEmpForActive(txtPranNo);
				}
			}
			String postExpFlag = "";
			String postEndDate = "";
			String pranActive = "";
			if ((lLstEmpDeselect != null) && (lLstEmpDeselect.size() > 0)) {
				Object[] empLst = (Object[]) lLstEmpDeselect.get(0);
				this.logger.info("size check by viru" + empLst.length);
				postExpFlag = empLst[9].toString().trim();
				postEndDate = empLst[10].toString().trim();
				pranActive = empLst[13].toString().trim();
			}
			this.logger.info("postExpFlag  :  " + postExpFlag);
			this.logger.info("postEndDate  :  " + postEndDate);
			inputMap.put("postExpFlag", postExpFlag);
			inputMap.put("postEndDate", postEndDate);
			inputMap.put("pranActive", pranActive);
			inputMap.put("DESELECTEMPLIST", lLstEmpDeselect);
			objRes.setResultValue(inputMap);
			objRes.setViewName("updatePranNoForActDeactivate");
		} catch (Exception e) {
			this.logger.info("Exception is " + e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject updateFlagForActDeact(Map inputMap) {
		ResultObject objRes = new ResultObject(0, "FAIL");
		String remark = null;
		String flag = null;
		String pranNo = null;
		try {
			setSessionInfo(inputMap);
			this.logger.info("inside update pran no");
			remark = StringUtility.getParameter("remark", this.request).trim();
			flag = StringUtility.getParameter("active", this.request).trim();
			this.logger.info("flag for update" + flag);
			pranNo = StringUtility.getParameter("pranNo", this.request).trim();

			Date updateDate = new Date();
			UpdatePranNoDAOImpl objUpdatePran = new UpdatePranNoDAOImpl(null,
					this.serv.getSessionFactory());
			flag = objUpdatePran.updateFlag(remark, flag, pranNo, updateDate);
			this.logger.info("FLAG:" + flag);
			this.logger.info("remark" + remark);
			this.logger.info("pranNo" + pranNo);

			StringBuffer strbuflag = new StringBuffer();
			strbuflag.append("<XMLDOC>");
			strbuflag.append("<Flag>");
			strbuflag.append(flag);
			strbuflag.append("</Flag>");
			strbuflag.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					strbuflag.toString()).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);
			this.logger.info("Flag is  ********" + flag);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

}