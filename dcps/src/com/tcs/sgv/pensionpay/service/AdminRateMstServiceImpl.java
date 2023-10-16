package com.tcs.sgv.pensionpay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pensionpay.dao.AdminRateMstDAO;
import com.tcs.sgv.pensionpay.dao.AdminRateMstDAOImpl;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAO;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pensionpay.dao.PhysicalCaseInwardDAO;
import com.tcs.sgv.pensionpay.dao.PhysicalCaseInwardDAOImpl;
import com.tcs.sgv.pensionpay.dao.RltPensionHeadcodeRateDAO;
import com.tcs.sgv.pensionpay.dao.RltPensionHeadcodeRateDAOImpl;
import com.tcs.sgv.pensionpay.dao.RltPensionRevisedPaymentDAO;
import com.tcs.sgv.pensionpay.dao.RltPensionRevisedPaymentDAOImpl;
import com.tcs.sgv.pensionpay.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pensionpay.valueobject.MstPensionStateRate;
import com.tcs.sgv.pensionpay.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pensionpay.valueobject.RltPensionHeadcodeRate;
import com.tcs.sgv.pensionpay.valueobject.RltPensionRevisedPayment;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pensionpay.valueobject.ValidPcodeView;


public class AdminRateMstServiceImpl extends ServiceImpl {

	/* Global Variable for UserId */
	private Long gLngUserId = null;

	/* Global Variable for LangId */
	private Long gLngLangId = null;

	/* Global Variable for Logger Class */
	private Log gLogger = LogFactory.getLog(getClass());

	/* Global Variable for Current Date */
	private Date gDate = null;

	/* Global Variable for Location Code */
	private String gStrLocCode = null;

	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");

	public ResultObject loadAdminRateMst(Map<String, Object> inputMap) {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String lStrLangId = SessionHelper.getLangId(inputMap).toString();

		setSessionInfo(inputMap);
		try {
			// To populate month combo....
			List<SgvaMonthMst> lObjSgvaMonthMst = new ArrayList<SgvaMonthMst>();

			CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());

			Integer lIntCurrentMonth = DBUtility.getCurrentDateFromDB().getMonth() + 1;
			inputMap.put("CurrentMonth", lIntCurrentMonth);
			inputMap.put("CurrentYear", new SimpleDateFormat("yyyy").format(gDate));

			// Returning VO array...
			lObjSgvaMonthMst = lObjCommonPensionDAO.getSgvaMonthMstVO(lStrLangId);

			if (lObjSgvaMonthMst != null) {
				inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
			}

			// To populate year combo....
			List<SgvcFinYearMst> lObjSgvcFinYearMst = new ArrayList<SgvcFinYearMst>();

			// Returning VO array...
			lObjSgvcFinYearMst = lObjCommonPensionDAO.getSgvcFinYearMstVO(lStrLangId);

			if (lObjSgvcFinYearMst != null) {
				inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
			}

			// To populate HeadCode combo...
			AdminRateMstDAOImpl lObjAdminRateMst = new AdminRateMstDAOImpl(serv.getSessionFactory());

			List listPnsnHeadCode = lObjCommonPensionDAO.getAllHeadCode();

			if (listPnsnHeadCode != null) {
				inputMap.put("listHeadCode", listPnsnHeadCode);
			}

			// To populate TI rate type combo...
			List listTIRate = lObjAdminRateMst.getTIRateType(gLngLangId);

			List lLstStateDept = lObjAdminRateMst.getAllStateDept(gLngLangId);
			if (lLstStateDept != null) {
				inputMap.put("lLstStateDept", lLstStateDept);
			}

			if (listTIRate != null) {
				inputMap.put("listTIRate", listTIRate);
			}

			// To populate For Pension combo...
			List listForPension = lObjAdminRateMst.getForPension(gLngLangId);

			if (listForPension != null) {
				inputMap.put("listForPension", listForPension);
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		resObj.setResultValue(inputMap);
		resObj.setViewName("AdminRateMst");
		return resObj;
	}

	public ResultObject getDataFromHeadcode(Map<String, Object> inputMap) {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		String lStrHeadCode = null;
		String lStrConfigType = null;
		String lStrTIRateType = null;
		String lStrForPension = null;
		Double lRate = 0D;
		// Double lAmt = 0D;
		String EffFrmDate = null;
		String tablePK = null;
		Calendar cal = Calendar.getInstance();
		List lLstHdRate = new ArrayList();

		try {
			setSessionInfo(inputMap);
			
			lStrHeadCode = StringUtility.getParameter("HeadCode", request);
			lStrConfigType = StringUtility.getParameter("ConfigType", request);
			lStrTIRateType = StringUtility.getParameter("TIRateType", request);
			lStrForPension = StringUtility.getParameter("ForPension", request);
			Long lngNewPK = IFMSCommonServiceImpl.getNextSeqNum("rlt_pension_headcode_rate", inputMap);

			String lStrCode = StringUtility.getParameter("code", request);
			inputMap.put("lngNewPK", lngNewPK);

			CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
			String strPnsnHeadCodeDesc = lObjCommonPensionDAO.getAllHeadCodeDesc(lStrHeadCode, SessionHelper.getLangId(inputMap));
			if (strPnsnHeadCodeDesc != null) {
				strPnsnHeadCodeDesc = strPnsnHeadCodeDesc.replace('&', '~');
				inputMap.put("HeadCodeDesc", strPnsnHeadCodeDesc);
			}

			/*StringBuilder lStrData = new StringBuilder();
			lStrData.append("<XMLDOC>");
			lStrData.append("<HEADDESC>");
			lStrData.append(strPnsnHeadCodeDesc);
			lStrData.append("</HEADDESC>");*/

			AdminRateMstDAOImpl lObjAdminRateDAO = new AdminRateMstDAOImpl(serv.getSessionFactory());
			if ("TI".equals(lStrConfigType)) {

				// normal case
				// get vo of rlt_pension_headcode_rate
				lLstHdRate = new ArrayList();
				lLstHdRate = lObjAdminRateDAO.getRltPensionHeadcodeRate(lStrHeadCode, lStrConfigType, lStrTIRateType, lStrForPension);

				if (lLstHdRate != null && !lLstHdRate.isEmpty()) {
					if (lStrForPension.equals("UPTO 1750") && lLstHdRate.get(1) != null) {
						lRate = Double.parseDouble(lLstHdRate.get(1).toString());
					} else {
						if (lLstHdRate.get(1) != null) {
							lRate = Double.parseDouble(lLstHdRate.get(1).toString());
						}
						// if (lLstHdRate.get(2) != null) {
						// lAmt =
						// Double.parseDouble(lLstHdRate.get(2).toString());
						// }
					}
					tablePK = lLstHdRate.get(0).toString();
					cal.setTime((Date) lLstHdRate.get(2));

				}

			} else {
				// get vo of rlt_pension_headcode_rate
				lLstHdRate = new ArrayList();
				lLstHdRate = lObjAdminRateDAO.getRltPensionHeadcodeRate(lStrHeadCode, lStrConfigType, lStrTIRateType, lStrForPension);

				if (lLstHdRate != null && !lLstHdRate.isEmpty()) {
					if ("DP".equals(lStrConfigType) && lLstHdRate.get(1) != null) {
						lRate = Double.parseDouble(lLstHdRate.get(1).toString());
					} else if ("IR".equals(lStrConfigType)) {
						if (lLstHdRate.get(1) != null) {
							lRate = Double.parseDouble(lLstHdRate.get(1).toString());
						}
						// if (lLstHdRate.get(2) != null) {
						// lAmt =
						// Double.parseDouble(lLstHdRate.get(2).toString());
						// }
					} else if ("MA".equals(lStrConfigType) && lLstHdRate.get(2) != null) {
						lRate = Double.parseDouble(lLstHdRate.get(2).toString());						
					}
					tablePK = lLstHdRate.get(0).toString();
					cal.setTime((Date) lLstHdRate.get(2));
				}
			}

			inputMap.put("rate", lRate);
			inputMap.put("tablePK", tablePK);
			
			List lLstDARateDetails = lObjAdminRateDAO.getDARateDetails(lStrTIRateType, lStrHeadCode, lStrForPension);
			inputMap.put("DARateDetails", lLstDARateDetails);						

			if (tablePK == null) {
				/*lStrData.append("<Flag>");
				lStrData.append("N");
				lStrData.append("</Flag>");*/
				inputMap.put("flag", "N");
			} else {
				/*lStrData.append("<Flag>");
				lStrData.append("Y");
				lStrData.append("</Flag>");*/
				inputMap.put("flag", "Y");
				EffFrmDate = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
				inputMap.put("EffFrmDate", EffFrmDate);
			}

			/*lStrData.append("<Rate>");
			lStrData.append(lRate);
			lStrData.append("</Rate>");
			lStrData.append("<tablePK>");
			lStrData.append(tablePK);
			lStrData.append("</tablePK>");
			lStrData.append("<tableNewPK>");
			lStrData.append(lngNewPK);
			lStrData.append("</tableNewPK>");
			lStrData.append("<EffectDate>");
			lStrData.append(EffFrmDate);
			lStrData.append("</EffectDate>");

			lStrData.append("</XMLDOC>");

			String lStrAjaxResult = new AjaxXmlBuilder().addItem("ajax_key", lStrData.toString()).toString();*/
			
			List listTIRate = lObjAdminRateDAO.getTIRateType(gLngLangId);
			if (listTIRate != null) {
				inputMap.put("listTIRate", listTIRate);
			}
						
			List lLstStateDept = lObjAdminRateDAO.getAllStateDept(gLngLangId);
			if (lLstStateDept != null) {
				inputMap.put("lLstStateDept", lLstStateDept);
			}

			inputMap.put("HeadCode", lStrHeadCode.trim());
			inputMap.put("TIRateType", lStrTIRateType.trim());
			
			List<SgvaMonthMst> lObjSgvaMonthMst = new ArrayList<SgvaMonthMst>();						
			lObjSgvaMonthMst = lObjCommonPensionDAO.getSgvaMonthMstVO(gLngLangId.toString());

			if (lObjSgvaMonthMst != null) {
				inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
			}
			
			List<SgvcFinYearMst> lObjSgvcFinYearMst = new ArrayList<SgvcFinYearMst>();
			lObjSgvcFinYearMst = lObjCommonPensionDAO.getSgvcFinYearMstVO(gLngLangId.toString());

			if (lObjSgvcFinYearMst != null) {
				inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
			}
			
			resObj.setResultValue(inputMap);
			if(lStrCode.equals("HIS")){
				resObj.setViewName("AdminRateHistory");
			}else if(lStrCode.equals("MST")){
				resObj.setViewName("AdminRateMst");
			}
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}

	public ResultObject saveAdmin(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		RltPensionRevisedPayment lObjPensionRevisedPayment = null;
		RltPensionRevisedPaymentDAO lObjRevisedPaymentDAO = new RltPensionRevisedPaymentDAOImpl(RltPensionRevisedPayment.class, serv.getSessionFactory());
		String lStrXMLMsg = "Records saved successfully";

		try {
			setSessionInfo(inputMap);

			// now we need to compute what amount has to be paid for all those
			// months
			// for all pensioners and make entries of those in arrears table
			List<TrnPensionArrearDtls> lLstArrear = getArrearForPayment(inputMap);

			int i = 1;
			HibernateTemplate hmt = new HibernateTemplate(serv.getSessionFactory());
			if (lLstArrear != null && !lLstArrear.isEmpty()) {
				Long ledgerID = IFMSCommonServiceImpl.getCurrentSeqNumAndUpdateCount("trn_pension_arrear_dtls", inputMap, lLstArrear.size());
				for (TrnPensionArrearDtls lObjArrearDtl1 : lLstArrear) {
					if (i % 500 == 0) {
						i = 1;
						serv.getSessionFactory().getCurrentSession().flush();
					}
					lObjArrearDtl1.setPensionArrearDtlsId(IFMSCommonServiceImpl.getFormattedPrimaryKey(++ledgerID, inputMap));
					hmt.save(lObjArrearDtl1);
					i++;
				}
			}

			// Code Commented by vrajesh: Start
			// if ("16".equals(lHeadCode) || "17".equals(lHeadCode) ||
			// "18".equals(lHeadCode)
			// || "19".equals(lHeadCode)) {
			// // special headcode
			// RltPensionHeadcodeSpecialDAO lObjHeadcodeSpecialDAO = new
			// RltPensionHeadcodeSpecialDAOImpl(
			// RltPensionHeadcodeSpecial.class, serv.getSessionFactory());
			// RltPensionHeadcodeSpecial lObjHeadcodeSpecial = new
			// RltPensionHeadcodeSpecial();
			// lObjHeadcodeSpecial = (RltPensionHeadcodeSpecial) inputMap
			// .get("RltPensionHeadcodeSpecialOld");
			// lObjHeadcodeSpecialDAO.update(lObjHeadcodeSpecial);
			//
			// lObjHeadcodeSpecial = new RltPensionHeadcodeSpecial();
			// lObjHeadcodeSpecial = (RltPensionHeadcodeSpecial) inputMap
			// .get("RltPensionHeadcodeSpecialNew");
			// //
			// lObjHeadcodeSpecial.setPensionHeadcodeSpecialId(IFMSCommonServiceImpl.getNextSeqNum("rlt_pension_headcode_special",
			// // inputMap));
			// lObjHeadcodeSpecialDAO.create(lObjHeadcodeSpecial);
			// } else {
			// normal headcode
			RltPensionHeadcodeRateDAO lObjHeadcodeRateDAO = new RltPensionHeadcodeRateDAOImpl(RltPensionHeadcodeRate.class, serv.getSessionFactory());
			RltPensionHeadcodeRate lObjHeadcodeRate = new RltPensionHeadcodeRate();
			lObjHeadcodeRate = (RltPensionHeadcodeRate) inputMap.get("RltPensionHeadcodeRateOld");
			lObjHeadcodeRateDAO.update(lObjHeadcodeRate);

			lObjHeadcodeRate = new RltPensionHeadcodeRate();
			lObjHeadcodeRate = (RltPensionHeadcodeRate) inputMap.get("RltPensionHeadcodeRateNew");
			// lObjHeadcodeRate.setPensionHeadcodeRateId(IFMSCommonServiceImpl.getNextSeqNum("rlt_pension_headcode_rate",
			// inputMap));
			lObjHeadcodeRateDAO.create(lObjHeadcodeRate);
			// }
			// Code Commented by vrajesh: End

			// now we have to do entries in rlt_pension_revised_payment
			List<RltPensionRevisedPayment> lLstRevisedPayment = (List<RltPensionRevisedPayment>) inputMap.get("LstRevisedPayment");
			if (lLstRevisedPayment != null && !lLstRevisedPayment.isEmpty()) {
				Long ledgerID = IFMSCommonServiceImpl.getCurrentSeqNumAndUpdateCount("rlt_pension_revised_payment", inputMap, lLstRevisedPayment.size());
				for (int z = 0; z < lLstRevisedPayment.size(); z++) {
					lObjPensionRevisedPayment = new RltPensionRevisedPayment();
					lObjPensionRevisedPayment = lLstRevisedPayment.get(z);
					lObjPensionRevisedPayment.setPensionRevisedPaymentId(IFMSCommonServiceImpl.getFormattedPrimaryKey(++ledgerID, inputMap));
					lObjRevisedPaymentDAO.create(lObjPensionRevisedPayment);
				}
			}

			StringBuffer lStrBldXML = new StringBuffer();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<MESSAGE>");
			lStrBldXML.append(lStrXMLMsg);
			lStrBldXML.append("</MESSAGE>");
			lStrBldXML.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();

			inputMap.put("ajaxKey", lStrResult);

			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	List<TrnPensionArrearDtls> getArrearForPayment(Map<String, Object> inputMap) throws Exception {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		List<TrnPensionArrearDtls> lLstArrearList = new ArrayList<TrnPensionArrearDtls>();
		TrnPensionArrearDtls lObjArrearVO = null;
		AdminRateMstDAOImpl lObjAdminDAO = new AdminRateMstDAOImpl(serv.getSessionFactory());
		RltPensionRevisedPayment lObjRevPay = null;
		List lLstReqdData = new ArrayList();
		Date lCalcToDt = null;
		Date lDateOfDeath = null;
		Date lEndDate = null;
		// Date lCommDate = null;
		// Integer lDaysOfComm = 0;
		Integer lcuryyyyMM = 0;
		// Integer lCommyyyyMM = 0;
		// Integer lDaysOfMonth = 0;
		Integer lEndYYYYMM = 0;
		Integer lDODYYYYMM = 0;

		ValidPcodeView lObjValidPcode = null;
		Map lMapFamily = new HashMap<String, MstPensionerFamilyDtls>();
		// Map lMapPenCut = new HashMap();
		MstPensionerFamilyDtls lLstFPmembers = null;
		List fpMemberList = new ArrayList();
		Date lFPDODdate = null;
		PensionBillProcessServiceImpl lObjPensionBill = new PensionBillProcessServiceImpl();
		MonthlyPensionBillVO lPensionBillVO = null;

		String lStrPensionFlag = bundleConst.getString("RECOVERY.PENSION");

		try {
			setSessionInfo(inputMap);

			String lStateCode = inputMap.get("HeadCode").toString();
			String lFieldType = inputMap.get("Fieldtype").toString();
			Double lOldRate = Double.parseDouble(inputMap.get("OldRate").toString());
			Double lNewRate = Double.parseDouble(inputMap.get("NewRate").toString());
			List<RltPensionRevisedPayment> lLstRevisedPayment = (List<RltPensionRevisedPayment>) inputMap.get("LstRevisedPayment");
			String lStrTIStyle = null;
			// fetching family details and putting conditions for end date.
			Map<String, Object> lNewMap = new HashMap<String, Object>();
			Double lArrears = 0D;
			Double lPrevTIAmnt = 0D;
			Double lNewTIAmnt = 0D;
			String lStrLstPdDt = null;
			String lStrForPension = null;
			inputMap.put("BillType", lStrPensionFlag);
			String lStrCalcArrear = (String) inputMap.get("CalcArrear");
			if (inputMap.containsKey("TIStyle")) {
				lStrTIStyle = inputMap.get("TIStyle").toString();
			}

			if ("Y".equals(lStrCalcArrear)) {
				if (lStrTIStyle.equals("DA_2006")) {
					lLstReqdData = lObjAdminDAO.getRecordsFor6ThPay(gStrLocCode, lStateCode);
					lMapFamily = lObjAdminDAO.getMstFamilyDtlsMap(lStateCode);
				} else if (lStrTIStyle.equals("DA_1996_DP")) {
					lLstReqdData = lObjAdminDAO.getRecordsFor5ThPay(gStrLocCode, lStateCode);
					lMapFamily = lObjAdminDAO.getMstFamilyDtlsMapFor5thPay(lStateCode);
				} else if (lStrTIStyle.equals("DA_1986")) {
					lStrForPension = inputMap.get("ForPension").toString();
					lLstReqdData = lObjAdminDAO.getRecordsFor4thPay(lStrForPension, lStateCode);
					lMapFamily = lObjAdminDAO.getMstFamilyDtlsMapFor4thPay(lStateCode, lStrForPension);
					// System.out.println("Length = "+ lLstReqdData.size());
				}
			}
			for (int x = 0; x < lLstRevisedPayment.size(); x++) {
				lObjRevPay = lLstRevisedPayment.get(x);
				Integer lFromMonth = lObjRevPay.getForPayMonth();
				Integer lToMonth = lObjRevPay.getToPayMonth();
				Integer lInMonth = lObjRevPay.getPayInMonth();
				// inputMap.put("lMapPenCut", lMapPenCut);

				if (lStrTIStyle.equals("DA_2006") || lStrTIStyle.equals("DA_1996_DP") || lStrTIStyle.equals("DA_1986")) {

					if (lLstReqdData != null && !lLstReqdData.isEmpty()) {

						// if (lStrTIStyle.equals("DA_1996_DP")) {
						// lMapPenCut =
						// lObjAdminDAO.getPenCutMapFor5thPay(lHeadCode,
						// lFromMonth, lToMonth);
						// } else if (lStrTIStyle.equals("DA_2006")) {
						// lMapPenCut = lObjAdminDAO.getPenCutMap(lHeadCode,
						// lFromMonth, lToMonth);
						// } else if (lStrTIStyle.equals("DA_1986")) {
						// lMapPenCut =
						// lObjAdminDAO.getPenCutMapFor4thPay(lHeadCode,
						// lFromMonth, lToMonth, lStrForPension);
						// }

						for (int y = 0; y < lLstReqdData.size(); y++) {

							lObjValidPcode = new ValidPcodeView();
							lObjValidPcode = (ValidPcodeView) lLstReqdData.get(y);

							lCalcToDt = lObjValidPcode.getLastPaidDate();

							if (lObjValidPcode.getCommensionDate() != null) {
								if ((Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lObjValidPcode.getCommensionDate()))) > lFromMonth) {
									lFromMonth = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lObjValidPcode.getCommensionDate()));
								}
							}

							if (lObjValidPcode.getLastPaidDate() != null) {
								lStrLstPdDt = new SimpleDateFormat("yyyyMM").format(lCalcToDt);

								if (lToMonth >= Integer.valueOf(lStrLstPdDt)) {
									lToMonth = Integer.valueOf(lStrLstPdDt);
								}
							}
							// System.out.println(lObjValidPcode.getPpoNo()+
							// " "+lFromMonth+ " "+ lToMonth);
							inputMap.put("Date", new SimpleDateFormat("yyyyMM").format(SessionHelper.getCurDate()));

							lToMonth = (lToMonth % 100 == 12) ? (lToMonth + 89) : (lToMonth + 1);

							for (Integer i = lFromMonth; i < lToMonth;) {
								// System.out.println("i + pCode -- >"+i+" "+lObjValidPcode.getPensionerCode());
								inputMap.put("Date", i);

								if (lObjValidPcode.getEndDate() != null && lObjValidPcode.getLastPaidDate() != null && lObjValidPcode.getEndDate().before(lObjValidPcode.getLastPaidDate())) {
									lObjValidPcode.setEndDate(null);
								}

								if (lObjValidPcode.getEndDate() != null) {
									lEndDate = lObjValidPcode.getEndDate();
									lEndYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lEndDate));
									lcuryyyyMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(gDate));
									if (lEndYYYYMM != 0 && lcuryyyyMM >= lEndYYYYMM) // checking
									// EndDate
									// before
									// Current
									// month
									// or
									// not.
									{
										lcuryyyyMM = lEndYYYYMM;
									}
								}

								lDateOfDeath = lObjValidPcode.getDateOfDeath();

								if (lDateOfDeath != null) {
									lDODYYYYMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lDateOfDeath));

									if (lMapFamily != null && !lMapFamily.isEmpty() && lDateOfDeath != null && lMapFamily.containsKey("Family" + lObjValidPcode.getPensionerCode())) {
										lLstFPmembers = (MstPensionerFamilyDtls) (lMapFamily.get("Family" + lObjValidPcode.getPensionerCode()));
									}
									if (lLstFPmembers != null) {
										fpMemberList = lObjPensionBill.getFpMemberList(lLstFPmembers);
										lFPDODdate = lLstFPmembers.getDateOfDeath();
									}
									if (!fpMemberList.isEmpty() && lFPDODdate != null) {
										if (lFPDODdate.after(lDateOfDeath)) {
											if (lEndDate == null) {
												lEndDate = lFPDODdate;
											} else if (lEndDate != null && (lFPDODdate.before(lEndDate) || lFPDODdate.equals(lEndDate))) {
												lEndDate = lFPDODdate;
											}
										} else {
											if (lEndDate == null) {
												lEndDate = lDateOfDeath;
											} else if (lEndDate != null && (lDateOfDeath.before(lEndDate) || lDateOfDeath.equals(lEndDate))) {
												lEndDate = lDateOfDeath;
											}
										}
										lEndYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lEndDate));
									} else if (fpMemberList.isEmpty()) {
										if (lEndDate == null) {
											lEndDate = lDateOfDeath;
										} else if (lEndDate != null && (lDateOfDeath.before(lEndDate) || lDateOfDeath.equals(lEndDate))) {
											lEndDate = lDateOfDeath;
										}
										lEndYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lEndDate));
									}
									inputMap.put("EndDate", lEndDate);
									inputMap.put("FPmembersVo", lLstFPmembers);
									inputMap.put("fpMemberList", fpMemberList);

								}

								if (lEndYYYYMM != 0 && lcuryyyyMM >= lEndYYYYMM) // checking
								// EndDate
								// before
								// Current
								// month
								// or
								// not.
								{
									lcuryyyyMM = lEndYYYYMM;
								}
								inputMap.put("BillCrtMonth", lcuryyyyMM);
								inputMap.put("DateOfDeath", lObjValidPcode.getDateOfDeath());
								inputMap.put("DateOfRetirement", lObjValidPcode.getDateOfRetirement());
								inputMap.put("ArrComputeFlag", "N");
								inputMap.put("lPendingArrear", 0);
								inputMap.put("lMapFamilyDtls", lMapFamily);
								inputMap.put("EndDate", lEndDate);
								inputMap.put("lObjValidPcode", lObjValidPcode);

								inputMap.put("NewPensionBasic", Double.valueOf(lObjValidPcode.getBasicPensionAmount().toString()));
								inputMap.put("NewFP1Basic", Double.valueOf(lObjValidPcode.getFp1Amount().toString()));
								inputMap.put("NewFP2Basic", Double.valueOf(lObjValidPcode.getFp2Amount().toString()));

								inputMap.put("ROP_1986", "P");
								if (lStrTIStyle.equals("DA_1986")) {
									inputMap.put("ROP_1996", "N");
								} else {
									inputMap.put("ROP_1996", "P");
								}

								if (lStrTIStyle.equals("DA_1996_DP") || lStrTIStyle.equals("DA_1986")) {
									inputMap.put("ROP_2006", "N");
								} else {
									inputMap.put("ROP_2006", "P");
								}

								inputMap.put("PayStartDate", lObjValidPcode.getCommensionDate());

								if (lDateOfDeath != null && i >= lDODYYYYMM) {
									inputMap.put("FPFlag", "Y");
								}
								inputMap.put("fromAdmin", "Y");

								if (lEndYYYYMM == 0 || lEndYYYYMM >= i) {
									// Previous TI Amount
									inputMap.put("lDiffRate", lOldRate);
									lNewMap = lObjPensionBill.getCurrMonthData(inputMap);
									if ((List) lNewMap.get("lLstMonthlyPensionBillVO") != null && !((List) lNewMap.get("lLstMonthlyPensionBillVO")).isEmpty()) {
										lPensionBillVO = (MonthlyPensionBillVO) ((List) lNewMap.get("lLstMonthlyPensionBillVO")).get(0);
									}
									if (lPensionBillVO != null) {
										lPrevTIAmnt = Double.parseDouble(lPensionBillVO.getTiPercentAmount().toString());
										if (lStrTIStyle.equals("DA_1996_DP") || lStrTIStyle.equals("DA_1986")) {
											lPrevTIAmnt = (double) Math.round(lPrevTIAmnt);
										} else {
											lPrevTIAmnt = Math.ceil(lPrevTIAmnt);
										}
									}

									// New TI Amount
									inputMap.put("lDiffRate", lNewRate);
									lNewMap = lObjPensionBill.getCurrMonthData(inputMap);
									if ((List) lNewMap.get("lLstMonthlyPensionBillVO") != null && !((List) lNewMap.get("lLstMonthlyPensionBillVO")).isEmpty()) {
										lPensionBillVO = (MonthlyPensionBillVO) ((List) lNewMap.get("lLstMonthlyPensionBillVO")).get(0);
									}
									if (lPensionBillVO != null) {
										lNewTIAmnt = Double.parseDouble(lPensionBillVO.getTiPercentAmount().toString());
										if (lStrTIStyle.equals("DA_1996_DP") || lStrTIStyle.equals("DA_1986")) {
											lNewTIAmnt = (double) Math.round(lNewTIAmnt);
										} else {
											lNewTIAmnt = Math.ceil(lNewTIAmnt);
										}
									}

									lArrears += lNewTIAmnt - lPrevTIAmnt;

								}
								i += ((Integer.parseInt((i.toString().substring(4, 6)))) == 12) ? 89 : 1;

							} // month wise loop end
								// System.out.print(" arrear -- >"+lArrears);

							if (lArrears != 0D) {
								lObjArrearVO = new TrnPensionArrearDtls();

								lObjArrearVO.setPensionerCode(lObjValidPcode.getPensionerCode());
								lObjArrearVO.setPensionRequestId(new Long(lObjValidPcode.getPensionRequestId()));
								if ("DP".equals(lFieldType) || "IR".equals(lFieldType)) {
									lObjArrearVO.setArrearFieldType("Pension");
								} else {
									lObjArrearVO.setArrearFieldType(lFieldType);
								}
								lObjArrearVO.setEffectedFromYyyymm(lFromMonth);
								lObjArrearVO.setEffectedToYyyymm(lObjRevPay.getToPayMonth());
								lObjArrearVO.setOldAmountPercentage(new BigDecimal(lOldRate));
								lObjArrearVO.setNewAmountPercentage(new BigDecimal(lNewRate));

								lObjArrearVO.setPaymentFromYyyymm(lInMonth);
								lObjArrearVO.setPaymentToYyyymm(lInMonth);
								lObjArrearVO.setCreatedDate(gDate);
								if (lStrTIStyle.equals("DA_1996_DP") || lStrTIStyle.equals("DA_1986")) {
									lObjArrearVO.setDifferenceAmount(new BigDecimal(Math.round(lArrears)));
									lObjArrearVO.setTotalDifferenceAmt(new BigDecimal(Math.round(lArrears)));
									lObjArrearVO.setCreatedPostId(BigDecimal.ONE);
								} else {
									lObjArrearVO.setDifferenceAmount(new BigDecimal(Math.ceil(lArrears)));
									lObjArrearVO.setTotalDifferenceAmt(new BigDecimal(Math.ceil(lArrears)));
									lObjArrearVO.setCreatedPostId(BigDecimal.ZERO);
								}
								lObjArrearVO.setCreatedUserId(new BigDecimal(gLngUserId));

								lLstArrearList.add(lObjArrearVO);
							}
							lArrears = 0D;
							lObjValidPcode = null;
							lDateOfDeath = null;
							lEndYYYYMM = 0;
							lEndDate = null;
							lToMonth = lObjRevPay.getToPayMonth();
							lFromMonth = lObjRevPay.getForPayMonth();
						}
					}
				}

			}

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			throw (e);
		}
		return lLstArrearList;
	}

	public ResultObject checkQueue(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		StringBuilder lStrBldXML = new StringBuilder();
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

			String lStrActv = "N";
			String lHeadCode = StringUtility.getParameter("HeadCode", request).trim();

			AdminRateMstDAOImpl lObjAdminDAO = new AdminRateMstDAOImpl(serv.getSessionFactory());
			lStrActv = lObjAdminDAO.chkStatusForHeadCode(lHeadCode);

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<MESSAGE>");
			lStrBldXML.append(lStrActv);
			lStrBldXML.append("</MESSAGE>");
			lStrBldXML.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}
		return objRes;
	}

	public ResultObject loadDARateHistoryConfig(Map<String, Object> inputMap) {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		// String lStrLangId = SessionHelper.getLangId(inputMap).toString();
		String lStrHistory = null;
		String lStrDARateType = null;
		String lStrHeadCodeType = null;
		String lStrHeadCodeTypeText = null;
		String lStrForPension = null;
		List lLstDARateDetails = null;
		setSessionInfo(inputMap);

		try {
			lStrHistory = StringUtility.getParameter("History", request);
			lStrDARateType = StringUtility.getParameter("TIRateTypeText", request);
			lStrHeadCodeType = StringUtility.getParameter("headCodeType", request);
			lStrHeadCodeTypeText = StringUtility.getParameter("headCodeTypeText", request);
			lStrForPension = StringUtility.getParameter("forPension", request);
			CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
			// To populate HeadCode combo...
			AdminRateMstDAOImpl lObjAdminRateMst = new AdminRateMstDAOImpl(serv.getSessionFactory());

			// To populate TI rate type combo...
			List listTIRate = lObjAdminRateMst.getTIRateType(gLngLangId);

			List listPnsnHeadCode = lObjCommonPensionDAO.getAllHeadCode();

			// To populate For Pension combo...
			List listForPension = lObjAdminRateMst.getForPension(gLngLangId);

			if (lStrDARateType.length() > 0) {
				inputMap.put("TIRateTypeText", lStrDARateType.trim());
			}
			if (lStrHeadCodeTypeText.length() > 0) {
				inputMap.put("HeadCodeTypeText", lStrHeadCodeTypeText.trim());
			}
			if (lStrForPension.length() > 0) {
				inputMap.put("ForPension", lStrForPension.trim());
			}

			if (listForPension != null) {
				inputMap.put("listForPension", listForPension);
			}

			if (listPnsnHeadCode != null) {
				inputMap.put("listHeadCode", listPnsnHeadCode);
			}

			if (listTIRate != null) {
				inputMap.put("listTIRate", listTIRate);
			}

			List lLstStateDept = lObjAdminRateMst.getAllStateDept(gLngLangId);
			if (lLstStateDept != null) {
				inputMap.put("lLstStateDept", lLstStateDept);
			}

			if (lStrHistory.length() > 0) {
				if (!lStrDARateType.equals("DA_1986") && !lStrDARateType.equals("")) {
					lStrForPension = "";
				}
				if (lStrDARateType.length() > 0) {
					lLstDARateDetails = lObjAdminRateMst.getDARateDetails(lStrDARateType, lStrHeadCodeType, lStrForPension);
				}

				Iterator lObjiterator = lLstDARateDetails.iterator();
				Object[] lArrObj = null;
				int lIntCnt = 0;
				List<Date> lLstFromDate = new ArrayList<Date>();
				List<Date> lLstToDate = new ArrayList<Date>();
				List<BigDecimal> lLstRate = new ArrayList<BigDecimal>();
				List<BigDecimal> lLstMinAmnt = new ArrayList<BigDecimal>();
				Date lDtFromDate = null;
				Date lDtToDate = null;
				BigDecimal lBDRate = BigDecimal.ZERO;
				BigDecimal lBDMinAmnt = BigDecimal.ZERO;
				while (lObjiterator.hasNext()) {
					lArrObj = (Object[]) lObjiterator.next();

					if (lArrObj[0] == null) {
						lDtFromDate = null;
					} else {
						lDtFromDate = (Date) lArrObj[0];
					}
					if (lArrObj[1] == null) {
						lDtToDate = null;
					} else {
						lDtToDate = (Date) lArrObj[1];
					}

					if (lArrObj[2] != null) {
						lBDRate = (BigDecimal) lArrObj[2];
					}
					if (lArrObj[3] != null) {
						lBDMinAmnt = (BigDecimal) lArrObj[3];
					}

					lLstFromDate.add(lDtFromDate);
					lLstToDate.add(lDtToDate);
					lLstRate.add(lBDRate);
					lLstMinAmnt.add(lBDMinAmnt);

					lIntCnt++;
				}

				inputMap.put("lLstFromDate", lLstFromDate);
				inputMap.put("lLstToDate", lLstToDate);
				inputMap.put("lLstRate", lLstRate);
				inputMap.put("lLstMinAmnt", lLstMinAmnt);
				inputMap.put("LoopIndex", lLstDARateDetails.size());

				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setResultValue(inputMap);
				objRes.setViewName("AdminRateHistoryPopUp");
			} else {				
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setResultValue(inputMap);
				objRes.setViewName("AdminRateHistory");
			}

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}

		return objRes;

	}

	public ResultObject chkDateIsOverLapOrNot(Map<String, Object> inputMap) {

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		AdminRateMstDAO lObjAdminRateMstDAO = null;
		String lStrDARateType = null;
		String lStrHeadCodeType = null;
		String lStrForPension = null;
		String lStrResVal = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		List lLstDARateDetails = null;
		Boolean lBlResFlag = false;
		StringBuilder lStrBldXML = null;
		String lStrResult = null;
		try {
			setSessionInfo(inputMap);
			lStrDARateType = StringUtility.getParameter("TiRateTypeText", request);
			lStrForPension = StringUtility.getParameter("ForPensionText", request);
			lStrHeadCodeType = StringUtility.getParameter("HeadCode", request);
			lStrFromDate = StringUtility.getParameter("fromDate", request);
			lStrToDate = StringUtility.getParameter("toDate", request);
			lObjAdminRateMstDAO = new AdminRateMstDAOImpl(serv.getSessionFactory());

			lLstDARateDetails = lObjAdminRateMstDAO.getDARateDetails(lStrDARateType, lStrHeadCodeType, lStrForPension);

			Iterator lObjiterator = lLstDARateDetails.iterator();
			Object[] lArrObj = null;
			int lIntCnt = 0;
			Date lDtEffctvFromDate = null;
			Date lDtEffctvToDate = null;
			List<String> lLstResVal = new ArrayList<String>();
			while (lObjiterator.hasNext()) {
				lArrObj = (Object[]) lObjiterator.next();

				if (lArrObj[0] == null) {
					lDtEffctvFromDate = null;
				} else {
					lDtEffctvFromDate = (Date) lArrObj[0];
				}
				if (lArrObj[1] == null) {
					lDtEffctvToDate = DBUtility.getCurrentDateFromDB();
				} else {
					lDtEffctvToDate = (Date) lArrObj[1];
				}
				lStrResVal = lObjAdminRateMstDAO.chkDateIsOverLapOrNot(lStrDARateType, lStrForPension, lStrHeadCodeType, StringUtility.convertStringToDate(lStrFromDate),
						StringUtility.convertStringToDate(lStrToDate), lDtEffctvFromDate, lDtEffctvToDate);
				lLstResVal.add(lStrResVal);
				lIntCnt++;
			}
			if (lLstResVal.contains("Y")) {
				lBlResFlag = true;
			}

			lStrBldXML = getResponseXMLDoc(lBlResFlag);
			gLogger.info(" lStrBldXML :: " + lStrBldXML);
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);

			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	private StringBuilder getResponseXMLDoc(Boolean lBlResFlag) {

		StringBuilder lStrHidPKs = new StringBuilder();

		lStrHidPKs.append("<XMLDOCCHECKDATE>");
		lStrHidPKs.append("<RESFLAG>");
		lStrHidPKs.append(lBlResFlag);
		lStrHidPKs.append("</RESFLAG>");
		lStrHidPKs.append("</XMLDOCCHECKDATE>");

		gLogger.info("lStrHidPKs : " + lStrHidPKs);
		return lStrHidPKs;

	}

	public ResultObject saveAdminHistory(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Long lLngPensionHeadcodeRateId = 0l;
		RltPensionHeadcodeRate lObjRltPensionHeadcodeRate = new RltPensionHeadcodeRate();
		List<RltPensionHeadcodeRate> lLstRltPensionHeadcodeRate = new ArrayList<RltPensionHeadcodeRate>();
		String lStrFinalData = "";
		
		try {
			RltPensionHeadcodeRateDAO lObjRltPensionHeadcodeRateDAO = new RltPensionHeadcodeRateDAOImpl(RltPensionHeadcodeRate.class, serv.getSessionFactory());
			String lStrDateChk = (String) inputMap.get("maxDate");
			if(!lStrDateChk.equals("Y")){
				lLstRltPensionHeadcodeRate = (List<RltPensionHeadcodeRate>) inputMap.get("lLstRltPensionHeadcodeRate");
				if (lLstRltPensionHeadcodeRate != null) {
					for (int lIntCount = 0; lIntCount < lLstRltPensionHeadcodeRate.size(); lIntCount++) {
						lObjRltPensionHeadcodeRate = lLstRltPensionHeadcodeRate.get(lIntCount);
						lLngPensionHeadcodeRateId = IFMSCommonServiceImpl.getNextSeqNum("rlt_pension_headcode_rate", inputMap);
						lObjRltPensionHeadcodeRate.setPensionHeadcodeRateId(lLngPensionHeadcodeRateId);
						lObjRltPensionHeadcodeRateDAO.create(lObjRltPensionHeadcodeRate);
						gLogger.info("Record Inserted in table rlt_pension_headcode_rate successfully.");
					}
				}
				lStrFinalData = "Add";
			}else{
				lStrFinalData = "MAX";
			}
			StringBuffer lStrBldXML = new StringBuffer();

			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<MESSAGE>");
			lStrBldXML.append(lStrFinalData);
			lStrBldXML.append("</MESSAGE>");
			lStrBldXML.append("</XMLDOC>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject loadDARateConfigForState(Map<String, Object> inputMap) {

		gLogger.info("In loadDARateConfigForState method.......");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		AdminRateMstDAO lObjAdminRateMstDAO = null;
		List lLstDARateConfigForStateDtls = null;
		Integer lIntTotalRecords = null;
		// List lLstDARateForStateId = null;
		try {
			setSessionInfo(inputMap);
			Map displayTag = IFMSCommonServiceImpl.getDisplayPara(request);
			lObjAdminRateMstDAO = new AdminRateMstDAOImpl(serv.getSessionFactory());
			lIntTotalRecords = lObjAdminRateMstDAO.getDARateConfigForStateCount(gLngLangId, gStrLocCode, displayTag);
			lLstDARateConfigForStateDtls = lObjAdminRateMstDAO.getDARateConfigForStateDtls(gLngLangId, gStrLocCode, displayTag);
			// lLstDARateForStateId =
			// lObjAdminRateMstDAO.getAllDARateStateId(gLngLangId);

			inputMap.put("totalRecords", lIntTotalRecords);
			inputMap.put("lLstDARateConfigForStateDtls", lLstDARateConfigForStateDtls);
			// inputMap.put("lLstMainCategoryId", lLstMainCategory);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(inputMap);
			resObj.setViewName("adminRateMstForState");
		} catch (Exception e) {
			gLogger.error("Error is:" + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			//e.printStackTrace();

		}
		return resObj;

	}

	public ResultObject saveDARateConfigForState(Map<String, Object> inputMap) {

		gLogger.info("In saveDARateConfigForState method.......");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		PhysicalCaseInwardDAO lObjPhysicalCaseInwardDAO = null;
		StringBuilder lStrBldXML = null;
		String lStrResult = null;
		String lStrTransMode = null;
		MstPensionStateRate lObjMstPensionStateRate = null;
		Long lLngMstPensionStateRateId = null;
		try {
			setSessionInfo(inputMap);
			lObjMstPensionStateRate = new MstPensionStateRate();
			lStrTransMode = (String) inputMap.get("Mode");
			lObjMstPensionStateRate = (MstPensionStateRate) inputMap.get("lObjMstPensionStateRate");
			lObjPhysicalCaseInwardDAO = new PhysicalCaseInwardDAOImpl(MstPensionStateRate.class, serv.getSessionFactory());
			if (lStrTransMode.equalsIgnoreCase("Add")) {
				if (lObjMstPensionStateRate != null) {
					lLngMstPensionStateRateId = IFMSCommonServiceImpl.getNextSeqNum("MST_PENSION_STATE_RATE", inputMap);
					lObjMstPensionStateRate.setPensionStateRateId(lLngMstPensionStateRateId);
					lObjPhysicalCaseInwardDAO.create(lObjMstPensionStateRate);
					gLogger.info("Record Inserted in table mst_pension_state_rate successfully.");
				}
			} else if (lStrTransMode.equalsIgnoreCase("Update")) {
				String lStrStateDesc = StringUtility.getParameter("txtStateDesc", request);
				if (!"".equals(lStrStateDesc) && lStrStateDesc.length() > 0) {
					lObjMstPensionStateRate.setStateDesc(lStrStateDesc.trim());
				}

				lObjPhysicalCaseInwardDAO.update(lObjMstPensionStateRate);

			} else // delete
			{
				String lStrMstPensionStateRateId = StringUtility.getParameter("MstPensionStateRateId", request);
				String[] lStrMstPensionStateRateIdArr = lStrMstPensionStateRateId.split("~");
				for (int lIntCnt = 0; lIntCnt < lStrMstPensionStateRateIdArr.length; lIntCnt++) {
					lObjMstPensionStateRate = (MstPensionStateRate) lObjPhysicalCaseInwardDAO.read(Long.valueOf(lStrMstPensionStateRateIdArr[lIntCnt].trim()));
					lObjPhysicalCaseInwardDAO.delete(lObjMstPensionStateRate);
				}
			}
			lStrBldXML = getResponseXMLDoc(inputMap, lStrTransMode);
			gLogger.info(" lStrBldXML :: " + lStrBldXML);
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);

			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			gLogger.error("Error is:" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//e.printStackTrace();

		}
		return objRes;

	}

	private StringBuilder getResponseXMLDoc(Map inputMap, String strMode) {

		StringBuilder lStrHidPKs = new StringBuilder();
		if (strMode.equalsIgnoreCase("Add")) {
			lStrHidPKs.append("<XMLDOC>");
			lStrHidPKs.append("<MESSAGECODE>");
			lStrHidPKs.append("Add");
			lStrHidPKs.append("</MESSAGECODE>");
			lStrHidPKs.append("</XMLDOC>");

		}
		if (strMode.equals("Update")) {
			lStrHidPKs.append("<XMLDOC>");
			lStrHidPKs.append("<MESSAGECODE>");
			lStrHidPKs.append("Update");
			lStrHidPKs.append("</MESSAGECODE>");
			lStrHidPKs.append("</XMLDOC>");
		}
		if (strMode.equals("Delete")) {
			lStrHidPKs.append("<XMLDOC>");
			lStrHidPKs.append("<MESSAGECODE>");
			lStrHidPKs.append("Delete");
			lStrHidPKs.append("</MESSAGECODE>");
			lStrHidPKs.append("</XMLDOC>");
		}
		gLogger.info("lStrHidPKs : " + lStrHidPKs);
		return lStrHidPKs;

	}

	/**
	 * Method to set Session variables
	 * 
	 * @param inputMap
	 */
	private void setSessionInfo(Map inputMap) {

		gLngLangId = SessionHelper.getLangId(inputMap);
		gLngUserId = SessionHelper.getUserId(inputMap);
		gStrLocCode = SessionHelper.getLocationCode(inputMap);
		gDate = DBUtility.getCurrentDateFromDB();
	}

}
