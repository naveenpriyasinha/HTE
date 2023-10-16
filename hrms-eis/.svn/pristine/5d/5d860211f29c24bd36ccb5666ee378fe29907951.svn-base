package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
//import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAOImpl;
import com.tcs.sgv.eis.dao.ScaleMasterDAOImpl;
import com.tcs.sgv.eis.util.ScaleMasterServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
//import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import java.text.NumberFormat;

/**
 * This service class is used to view, add, update the Scale master data
 * 
 *
 */
public class ScaleMasterService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle constantBundle = ResourceBundle.getBundle("../resources/Payroll");

	/**
	 * For fillin up scale components like list of pay commission and pay band
	 */
	public ResultObject fillScaleDefaultComponents(Map objectArgs) {
		ResultObject resultObject = new ResultObject();

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ScaleMasterDAOImpl scaleMasterDAOImpl = new ScaleMasterDAOImpl(HrEisScaleMst.class, serv.getSessionFactory());

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		List<CmnLookupMst> payBandList = null;
		List<HrPayCommissionMst> payCommissionList = null;
		List<CmnLookupMst> payScaleTypeList = null; // Added by Muneendra for list of all pay scale type
		// List scaleList = null;

		try {

			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());

			payBandList = scaleMasterDAOImpl.getPayBands(); // list of all pay bands
			payCommissionList = scaleMasterDAOImpl.getPayCommisions(langId, locId); // list of all pay commission
			payScaleTypeList = scaleMasterDAOImpl.getPayscaleType(); // Added by Muneendra for list of all pay scale
																		// type
			logger.info("payBandList size : " + payBandList.size());
			logger.info("payCommissionList size : " + payCommissionList.size());
			logger.info("payScaleTypeList size : " + payScaleTypeList.size());

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date sysdate = new Date();

			long currYear = sysdate.getYear() + 1900;
			// logger.info("currYear::::::::::::::::::: " + currYear);
			long currMonth = sysdate.getMonth() + 1;
			// logger.info("currMonth::::::::::::::::::: " + currMonth);

			if (currMonth > 7)
				currYear = currYear + 1;
			else
				currYear = currYear;

			String incrementDate = "01/07/" + currYear;
			// logger.info("incrementDate:::::::::::::: " + incrementDate);

			Date inctDate = df.parse(incrementDate.toString());

			// logger.info("inctDate:::::::::::::: " + inctDate);

			objectArgs.put("inctDate", inctDate);
			objectArgs.put("payBandList", payBandList);
			objectArgs.put("payCommissionList", payCommissionList);
			objectArgs.put("payScaleTypeList", payScaleTypeList);

		} catch (Exception e) {

		}
		resultObject.setResultValue(objectArgs);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setViewName("newScaleMaster");

		return resultObject;
	}// end method

	/**
	 * This method is to insert the new scale and return back the list of scale
	 * 
	 * @param objServiceArgs
	 *            (Map)
	 * @return ResultObject (Map)
	 */
	public ResultObject insertScaleData(Map objServiceArgs) {

		logger.info("inside insertScaleData---------->");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
		HttpSession session = request.getSession();
		Map loginDetailsMap = (Map) objServiceArgs.get("baseLoginMap");

		int msg = 0;

		ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");
		ScaleMasterDAOImpl scalemstDao = new ScaleMasterDAOImpl(HrEisScaleMst.class, serv.getSessionFactory());

		String editFromVO = objServiceArgs.get("edit").toString();

		HrEisScaleMst scaleMstVO = (HrEisScaleMst) objServiceArgs.get("scaleMstVO");

		try {
			logger.info("Scale Start Amount is---->" + scaleMstVO.getScaleStartAmt());

			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());

			long scaleStartAmt = scaleMstVO.getScaleStartAmt();
			long scaleEndAmt = scaleMstVO.getScaleEndAmt();

			long scaleIncAmt = scaleMstVO.getScaleIncrAmt();
			long payCommission = Long.parseLong(objServiceArgs.get("payCommission").toString());
			long scaleincrementamt = scaleMstVO.getScaleIncrAmt();
			long scaleGradePay = scaleMstVO.getScaleGradePay();
			long payScaleType = Long.parseLong(objServiceArgs.get("payScaleType").toString());
			logger.info("payScaleType********* " + payScaleType);

			long scaleHigherIncrAmt = 0;// = Long.parseLong(voToService.get("higherIncrAmt").toString());
			long scaleHigherEndAmt = 0; // = Long.parseLong(voToService.get("higherEndAmt").toString());
			// long payCommission=0;
			long payBand = 0;
			long gradePay = 0;

			logger.info("scalestartamt********* " + scaleStartAmt);
			logger.info("sclaeendamt********* " + scaleEndAmt);
			logger.info("commissioncode********* " + payCommission);
			logger.info("scaleincrementamt********* " + scaleincrementamt);
			logger.info("gradepay********* " + scaleGradePay);
			logger.info("scaleIncAmt********* " + scaleIncAmt);

			ScaleMasterDAOImpl scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class, serv.getSessionFactory());
			List DuplicateactionList = scaleMasterDAO.checkDuplicateEntry(scaleStartAmt, scaleIncAmt, scaleEndAmt,
					scaleHigherIncrAmt, scaleHigherEndAmt, langId, payBand, payCommission, scaleGradePay, payScaleType);

			logger.info("DuplicateactionList********* " + DuplicateactionList.size());

			logger.info("editFromVO " + editFromVO);

			long elementCode = 1;
			Date sysdate = new Date();
			Map loginMap = (Map) objServiceArgs.get("baseLoginMap");
			ScaleMasterServiceHelper helper = new ScaleMasterServiceHelper(serv);

			if (DuplicateactionList.size() > 0) {
				objServiceArgs.put("msg", "Duplicate Scale. Please Enter Another.");

			}

			else if (editFromVO != null && editFromVO.equalsIgnoreCase("Y")) {
				logger.info("Inside Update Mode");
				// long scaleId= scaleMstVO.getScaleId();
				// long scaleId= this.getScaleId(scaleMstVO);

				/*
				 * String
				 * PreviewBill=objServiceArgs.get("PreviewBill")!=null?objServiceArgs.get(
				 * "PreviewBill").toString():""; String
				 * MergedScreen=objServiceArgs.get("MergedScreen")!=null?objServiceArgs.get(
				 * "MergedScreen").toString():""; String
				 * allowId=objServiceArgs.get("allowId")!=null &&
				 * !objServiceArgs.get("allowId").equals("")?(objServiceArgs.get("allowId").
				 * toString()):""; String deducId=objServiceArgs.get("deducId")!=null &&
				 * !objServiceArgs.get("deducId").equals("")?(objServiceArgs.get("deducId").
				 * toString()):""; //double scale_start_amt =
				 * Double.valueOf(objServiceArgs.get("scale_start_amt").toString()); double
				 * scale_start_amt = Double.valueOf(scaleStartAmt); double GradePayExcell =
				 * Double.valueOf(objServiceArgs.get("GradePayExcell").toString());
				 * HrEisOtherDtls otherDtlsVO= (HrEisOtherDtls)objServiceArgs.get("empOtherVO");
				 * String editMode = objServiceArgs.get("editMode").toString(); String
				 * fromScaleRevision = objServiceArgs.get("fromScaleRevision").toString();
				 * 
				 * Map passMap = new HashMap(); passMap.put("PreviewBill",PreviewBill);
				 * passMap.put("MergedScreen",MergedScreen); passMap.put("allowId",allowId);
				 * passMap.put("deducId",deducId);
				 * passMap.put("scale_start_amt",scale_start_amt);
				 * passMap.put("GradePayExcell",GradePayExcell);
				 * passMap.put("empOtherVO",otherDtlsVO); passMap.put("editMode",editMode);
				 */

				helper.updateScaleMasterDtls(scaleMstVO, userId, postId);
				// logger.info("The scale id is---->"+scaleId);

				objServiceArgs.put("msg", "Record Updated Successfully");
				logger.info("scale mst service complete");
				msg = 1;
			} else if (editFromVO != null && editFromVO.equalsIgnoreCase("N")) {

				/** HrEisScaleMst Details */
				logger.info("INSIDE CREATE");
				helper.insertScaleMasterDtls(scaleMstVO, langId, dbId, postId, locId, userId);
				objServiceArgs.put("msg", "Record Inserted Successfully");
			}

			else {
				logger.info("Exception occurs");
				throw new NullPointerException();
			}

			/*
			 * List <HrEisScaleMst> actionList = scalemstDao.getAllScaleData(); for(int
			 * i=0;i<actionList.size();i++) {
			 * logger.info("insideactionList List : "+actionList.size());
			 * 
			 * actionList.get(i).getHrPayCommissionMst().getCommissionName(); }
			 * logger.info("List : "+actionList);
			 * 
			 * 
			 * 
			 * 
			 * objServiceArgs.put("actionList", actionList);
			 */

			/*
			 * Map redirectMap = new HashMap(); redirectMap.put("actionFlag",
			 * "getScaleData"); resultObject.setResultCode(ErrorConstants.SUCCESS);
			 * resultObject.setResultValue(objServiceArgs);
			 */
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objServiceArgs);
			resultObject.setViewName("scaleEditList");

		} catch (ConstraintViolationException exception) {
			exception.printStackTrace();
			int flag = 1;
			objServiceArgs.put("flag", flag);
			objServiceArgs.put("scaleMstVO", scaleMstVO);
			logger.info("Exception");
			if (editFromVO.equalsIgnoreCase("Y")) {
				logger.info("Update Exception");
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("scaleEditList");
			} else {
				logger.info("Insert Exception");
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("newScaleMaster");
			}
		}

		catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
			int flag = 1;
			objServiceArgs.put("flag", flag);
			logger.error("Exception in insertScaleData");
			// resultObject.setResultCode(-1);
			// resultObject.setThrowable(e);
			resultObject.setResultValue(objServiceArgs);
			List<HrEisScaleMst> actionList = scalemstDao.getAllScaleData();
			logger.info("List : " + actionList);
			for (int i = 0; i < actionList.size(); i++) {
				actionList.get(i).getHrPayCommissionMst().getCommissionName();
			}
			objServiceArgs.put("actionList", actionList);
			resultObject.setViewName("ScaleViewList");
		}

		return resultObject;
	}
	// Added by Abhilash

	public ResultObject checkScaleAvailability(Map objectArgs) {

		logger.info("inside checkScaleAvailability");

		logger.info("inside insertScaleData---------->");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

		Map voToService = (Map) objectArgs.get("voToServiceMap");

		StringBuffer scaleNameBf = new StringBuffer();
		String check;
		long langId = Long.parseLong(loginDetailsMap.get("langId").toString());

		try {

			ScaleMasterDAOImpl scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class, serv.getSessionFactory());
			// String newScaleName = request.getParameter("newScaleName");

			long scaleStartAmt = Long.parseLong(voToService.get("startAmt").toString());
			long scaleIncAmt = Long.parseLong(voToService.get("incAmt").toString());
			long scaleEndAmt = Long.parseLong(voToService.get("endAmt").toString());
			// Adde by Mrugesh for Bigger Scale Value
			long scaleHigherIncramt = 0;// = Long.parseLong(voToService.get("higherIncrAmt").toString());
			long scaleHigherEndamt = 0; // = Long.parseLong(voToService.get("higherEndAmt").toString());
			long payCommission = 0;
			long payBand = 0;
			long gradePay = 0;
			// if(voToService.containsKey("gradePay"))
			if (voToService.get("gradePay") != null && voToService.get("gradePay") != "")
				gradePay = Long.parseLong(voToService.get("gradePay").toString());

			if (voToService.get("higherIncrAmt") != null && voToService.get("higherIncrAmt") != "")
				scaleHigherIncramt = Long.parseLong(voToService.get("higherIncrAmt").toString());

			if (voToService.get("higherEndAmt") != null && voToService.get("higherEndAmt") != "")
				scaleHigherEndamt = Long.parseLong(voToService.get("higherEndAmt").toString());
			// Ended by Mrugesj

			// fetch pay commission
			if (voToService.get("payCommission") != null && voToService.get("payCommission") != "")
				payCommission = Long.parseLong(voToService.get("payCommission").toString());

			// fetch pay band
			if (voToService.get("payBand") != null && voToService.get("payBand") != "")
				payBand = Long.parseLong(voToService.get("payBand").toString());

			logger.info("1**********" + scaleStartAmt);
			logger.info("2**********" + scaleIncAmt);
			logger.info("3**********" + scaleEndAmt);
			logger.info("4**********" + gradePay);
			logger.info("5**********" + scaleHigherIncramt);
			logger.info("6**********" + scaleHigherEndamt);
			logger.info("7**********" + payCommission);
			logger.info("8**********" + payBand);

			List actionList = scaleMasterDAO.checkScaleNameAvailability(scaleStartAmt, scaleIncAmt, scaleEndAmt,
					scaleHigherIncramt, scaleHigherEndamt, langId, payBand, payCommission, gradePay);

			logger.info("actionList actionList" + actionList.size());

			if (actionList == null || actionList.size() == 0) {
				check = "false";
				scaleNameBf.append("<scaleNameMapping>");
				scaleNameBf.append("<availability>").append(check).append("</availability>");
				scaleNameBf.append("</scaleNameMapping>");
			} else {
				check = "true";
				scaleNameBf.append("<scaleNameMapping>");
				scaleNameBf.append("<availability>").append(check).append("</availability>");
				scaleNameBf.append("</scaleNameMapping>");
			}
			logger.info("check.check()" + check);

			Map map = new HashMap();
			logger.info("scaleNameBf.toString()" + scaleNameBf.toString());

			String scaleNameMapping = new AjaxXmlBuilder().addItem("ajax_key", scaleNameBf.toString()).toString();

			map.put("ajaxKey", scaleNameMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(map);
			resultObject.setViewName("ajaxData");

			// resultObject.setViewName("allowTypeMasterView");
		} catch (Exception e) {
			// ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.error("Error is: " + e.getMessage());
			logger.info("Exception comes in duplicate check");

		}
		return resultObject;
	}

	public ResultObject getScaleData(Map objServiceArgs) {

		logger.info("inside getScaleData ");
		logger.info("---------------inside getScaleData-------------------");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try {
			ServiceLocator serv = (ServiceLocator) objServiceArgs.get("serviceLocator");
			// ScaleMstServiceTest scaleMstServiceTest = new ScaleMstServiceTest();

			ScaleMasterDAOImpl scaleMstDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class, serv.getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");

			HttpSession session = request.getSession();
			// LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
			Map loginDetails = (Map) objServiceArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetails.get("langId").toString());

			Map voToService = (Map) objServiceArgs.get("voToServiceMap");
			String editFlag = voToService.get("edit") != null ? (String) voToService.get("edit") : "";

			// if(request.getParameter("edit")!= null &&
			// request.getParameter("edit").equals("Y"))
			if (editFlag != null && editFlag.equals("Y")) {

				// String sid=request.getParameter("scaleid");
				String sid = (String) voToService.get("scaleid");
				logger.info("update scale id is***********" + sid);

				List duplicateList = scaleMstDAO.getDuplicateDataFromScaleId(sid);
				if (duplicateList.size() > 0) {
					objServiceArgs.put("msg", "The scale is assigned to employee's, So can not be updated.");

				}

				else {
					HrEisScaleMst actionList = scaleMstDAO.getScaleData(sid, langId);

					logger.info("----Sid is----" + sid);
					// logger.info("Scale Name is----->"+actionList.getScaleName());

					objServiceArgs.put("actionList", actionList);

					// added by abhilash
					long commissioncode = actionList.getHrPayCommissionMst().getId();
					objServiceArgs.put("commissioncode", commissioncode);
					long locId = StringUtility.convertToLong(loginDetails.get("locationId").toString());
					List<HrPayCommissionMst> payCommissionList = null;
					payCommissionList = scaleMstDAO.getPayCommisions(langId, locId); // list of all pay commission

					logger.info("update payCommissionList size : " + payCommissionList.size());

					objServiceArgs.put("payCommissionList", payCommissionList);

					List<CmnLookupMst> payScaleTypeList = null;

					logger.info("checking in object **************" + scaleMstDAO);
					logger.info("payscaletype form vogen is **************" + scaleMstDAO.getPayscaleType());
					payScaleTypeList = scaleMstDAO.getPayscaleType();

					// logger.info("payScaleType from action list is
					// ************"+actionList.getPayScaleType().toString());

					CmnLookupMst cmnLookupMst = actionList.getPayScaleType();
					long payScaleType = 0;
					if (cmnLookupMst != null)
						payScaleType = cmnLookupMst.getLookupId();
					objServiceArgs.put("payScaleType", payScaleType);

					logger.info("payScaleType is ************" + payScaleType);

					logger.info("update payScaleTypeList size : " + payScaleTypeList.size());
					objServiceArgs.put("payScaleTypeList", payScaleTypeList);
					// ended by abhilash

				}

				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("scaleEditList");

			} else {
				List<HrEisScaleMst> actionList = scaleMstDAO.getAllScaleData();
				logger.info("list size in getScaleData is------------>" + actionList.size());
				// Map map = new HashMap();
				// List <HrEisScaleMst> actionListnew =
				for (int i = 0; i < actionList.size(); i++) {
					HrEisScaleMst hrEisScaleMst = actionList.get(i);
					logger.info("the value of the actionList.get(i) is ::" + actionList.get(i));
					// for lazy initialization
					logger.info(hrEisScaleMst.getHrPayCommissionMst().getCommissionName()
							+ "the value of the start amount is ::" + hrEisScaleMst.getScaleStartAmt());
					String tempBuffer = "";
					if (hrEisScaleMst.getScaleStartAmt() != 0) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						logger.info(
								"Formatted as currency: " + currencyFormatter.format(hrEisScaleMst.getScaleStartAmt()));
						tempBuffer = currencyFormatter.format(hrEisScaleMst.getScaleStartAmt()).replace("$", "");
						// varun gettin error since i took
						// hrEisScaleMst.setCurrencyStartAmount(tempBuffer.replace(".00", ""));
					}

					if (hrEisScaleMst.getScaleEndAmt() != 0) {
						// hrEisScaleMst.setCurrencyEndAmount(currencyEndAmount) ;
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						currencyFormatter.setParseIntegerOnly(true);
						logger.info(
								"Formatted as currency: " + currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()));
						tempBuffer = currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()).replace("$", "");
						// varun gettin error since i took
						// hrEisScaleMst.setCurrencyEndAmount(tempBuffer.replace(".00", ""));
						// not by me
						// hrEisScaleMst.setCurrencyEndAmount(currencyFormatter.format(hrEisScaleMst.getScaleEndAmt()).replace("$",
						// ""));
					}

					if (hrEisScaleMst.getScaleHigherEndAmt() != 0) {
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
						currencyFormatter.setParseIntegerOnly(true);
						logger.info("Formatted as currency: "
								+ currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()));
						tempBuffer = currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()).replace("$", "");
						// varun gettin error since i took
						// hrEisScaleMst.setCurrencyHigherEndAmount(tempBuffer.replace(".00", ""));
						// not by
						// mehrEisScaleMst.setCurrencyHigherEndAmount(currencyFormatter.format(hrEisScaleMst.getScaleHigherEndAmt()).replace("$",
						// ""));
					}

				}

				objServiceArgs.put("actionList", actionList);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objServiceArgs);
				resultObject.setViewName("ScaleViewList");

			}
		} catch (ClassCastException c) {
			logger.info("Exception in getScaleData 1 ");
			logger.error("Exception in getScaleData 2 ");
			c.printStackTrace();
			resultObject.setResultValue(objServiceArgs);
			resultObject.setViewName("ScaleViewList");
		} catch (Exception e) {
			logger.info("Exception in getScaleData 3 ");
			logger.info("Error is this " + e);
			logger.error("Error is this " + e);
			logger.error("Exception in getScaleData 4");
			resultObject.setResultValue(objServiceArgs);

			resultObject.setViewName("ScaleViewList");
		}

		return resultObject;

	}

}
