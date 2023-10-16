/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Jun 2, 2011		Shivani Rana								
 *******************************************************************************
 */
package com.tcs.sgv.pensionpay.service;
//com.tcs.sgv.pensionpay.service.SupplementaryBillServiceImpl
import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAO;
import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAOImpl;
import com.tcs.sgv.common.dao.CommonDAO;
import com.tcs.sgv.common.dao.CommonDAOImpl;
import com.tcs.sgv.common.dao.RltBillPartyDAO;
import com.tcs.sgv.common.dao.RltBillPartyDAOImpl;
import com.tcs.sgv.common.helper.ColumnVo;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.util.EnglishDecimalFormat;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.RltBillParty;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pensionpay.dao.CVPRestorationLetterDAO;
import com.tcs.sgv.pensionpay.dao.CVPRestorationLetterDAOImpl;
import com.tcs.sgv.pensionpay.dao.NewPensionBillDAO;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAO;
import com.tcs.sgv.pensionpay.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pensionpay.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pensionpay.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pensionpay.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pensionpay.dao.OnlinePensionCaseDAO;
import com.tcs.sgv.pensionpay.dao.OnlinePensionCaseDAOImpl;
import com.tcs.sgv.pensionpay.dao.PensionBillDAO;
import com.tcs.sgv.pensionpay.dao.PensionBillDAOImpl;
import com.tcs.sgv.pensionpay.dao.PhysicalCaseInwardDAO;
import com.tcs.sgv.pensionpay.dao.PhysicalCaseInwardDAOImpl;
import com.tcs.sgv.pensionpay.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pensionpay.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pensionpay.dao.SupplementaryBillDAO;
import com.tcs.sgv.pensionpay.dao.SupplementaryBillDAOImpl;
import com.tcs.sgv.pensionpay.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pensionpay.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pensionpay.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pensionpay.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pensionpay.dao.TrnPensionRecoveryDtlsDAO;
import com.tcs.sgv.pensionpay.dao.TrnPensionRecoveryDtlsDAOImpl;
import com.tcs.sgv.pensionpay.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pensionpay.valueobject.HstCommutationDtls;
import com.tcs.sgv.pensionpay.valueobject.HstPnsnPmntDcrgDtls;
import com.tcs.sgv.pensionpay.valueobject.HstReEmploymentDtls;
import com.tcs.sgv.pensionpay.valueobject.MstPensionerFamilyDtls;
import com.tcs.sgv.pensionpay.valueobject.MstPensionerHdr;
import com.tcs.sgv.pensionpay.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pensionpay.valueobject.SupplementaryPartyDtlsVO;
import com.tcs.sgv.pensionpay.valueobject.TrnEcsDtl;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionSixpayfpArrear;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionSupplyBillDtls;
import com.tcs.sgv.web.jsp.tags.DateUtilities;


/**
 * Class Description -
 * 
 * 
 * @author Shivani Rana
 * @version 0.1
 * @since JDK 5.0 Jun 2, 2011
 */
public class SupplementaryBillServiceImpl extends ServiceImpl implements SupplementaryBillService {

	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;

	/* Glonal Variable for Location Code */
	private String gStrLocCode = null;

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	/* Global Variable for Current Date */
	private Date gDtCurrDt = null;

	/* Global Variable for Lang ID */
	private Long gLangID = null;

	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");

	/**
	 * View CVP Bill data after saving the bill
	 * 
	 * @param inputMap
	 * @return objRes ResultObject
	 */
	public ResultObject getSupplementaryBillData(Map<String, Object> inputMap) {

		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = null;
		String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
		List<SupplementaryPartyDtlsVO> lLstPensionerDtls = null;
		SupplementaryPartyDtlsVO lObjSupplementaryPartyDtlsVO = new SupplementaryPartyDtlsVO();
		List<ComboValuesVO> lLstBanks = null;
		MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class, srvcLoc.getSessionFactory());
		
		PensionBillDAO lObjPensionBillDAO = new PensionBillDAOImpl(srvcLoc.getSessionFactory());
		CommonDAO lObjCommonDAO = new CommonDAOImpl(srvcLoc.getSessionFactory());
		List<CmnLookupMst> lLstRecoveryType = new ArrayList();
		String lStrCurrRoleId = "";
		String lStrElementCode = null;
		String lStrSchemeCode = "";
		CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
		try {
			setSessionInfo(inputMap);
			lLstPensionerDtls = new ArrayList<SupplementaryPartyDtlsVO>();
			request = (HttpServletRequest) inputMap.get("requestObj");
			lStrElementCode = StringUtility.getParameter("elementId", request).trim();
			String lStrPPONo = StringUtility.getParameter("PPONo", request);
			lStrCurrRoleId = StringUtility.getParameter("CurrRole", request);
			String lStrSuplBillType = StringUtility.getParameter("SuplBillType", request);
			String lStrPnsnCode = "";
			if(!"".equals(lStrPPONo))
				lStrPnsnCode = lObjPensionBillDAO.getPnsnCodeFromPPONo(lStrPPONo.trim(), lStrStatus);
			
			lLstPensionerDtls = lObjPensionBillDAO.getSuppBillData(lStrPnsnCode, gStrLocCode,lStrSuplBillType);
			String lStrPensionerName = lObjMstPensionerHdrDAO.getPensionerName(lStrPnsnCode);
			if(!lLstPensionerDtls.isEmpty())
			{
				lObjSupplementaryPartyDtlsVO = lLstPensionerDtls.get(0);
				if(lObjSupplementaryPartyDtlsVO.getDateOfDeath() != null)
				{
					if("PENSION".equals(lStrSuplBillType) || "CVP".equals(lStrSuplBillType))
					{
						lLstPensionerDtls = new ArrayList<SupplementaryPartyDtlsVO>();
						lLstPensionerDtls = lObjPensionBillDAO.getFamilyDtlsSuppBill(lStrPnsnCode, gStrLocCode,lStrSuplBillType);
					}
					else if("DCRG".equals(lStrSuplBillType))
					{
						lLstPensionerDtls = new ArrayList<SupplementaryPartyDtlsVO>();
						List lLstResult = new ArrayList();
						lLstResult = lObjPensionBillDAO.getNomineeDtlsSuppBill(lStrPnsnCode, gStrLocCode);
						if(!lLstResult.isEmpty())
						{
							for(Integer lIntCnt=0;lIntCnt<lLstResult.size();lIntCnt++)
							{   
								lObjSupplementaryPartyDtlsVO = new SupplementaryPartyDtlsVO();
								Object[] obj=(Object[])lLstResult.get(lIntCnt);
								if(obj[0]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setBeneifiicaryName(obj[0].toString());
								}
								if(obj[1]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setBankCode(obj[1].toString());
								}
								if(obj[2]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setBranchName(obj[2].toString());
								}
								if(obj[3]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setAccountNo(obj[3].toString());
								}
								if(obj[4]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setBranchCode(obj[4].toString());
								}
								if(obj[5]!=null)
								{
									lObjSupplementaryPartyDtlsVO.setMicrCode(Long.parseLong(obj[5].toString()));
								}
								lLstPensionerDtls.add(lObjSupplementaryPartyDtlsVO);
							}
						}
					}
				}
			}
			lLstRecoveryType = IFMSCommonServiceImpl.getLookupValues("RECVTYPE", gLangID, inputMap);

			OnlinePensionCaseDAO lObjOnlinePensionCaseDAO = new OnlinePensionCaseDAOImpl(TrnEcsDtl.class, srvcLoc.getSessionFactory());
			// lStrCurrRoleId =
			// lObjOnlinePensionCaseDAO.getRoleByPost(gLngPostId);
			//lStrCurrRoleId = lObjCommonPensionDAO.getRoleByElement(lStrElementCode);
			inputMap.put("currRole", lStrCurrRoleId);
			// lObjIterator = lLstPensionerDtls.iterator();
			//
			// if (lLstPensionerDtls != null) {
			//
			// while (lObjIterator.hasNext()) {
			// Object[] lObj = (Object[]) lObjIterator.next();
			// if (lObj[0] != null) {
			// lStrPensionerName = lObj[0].toString();
			// }
			// if (lObj[1] != null) {
			// lStrBankCode = lObj[1].toString();
			// }
			// if (lObj[2] != null) {
			// lStrBranchName = lObj[2].toString();
			// }
			// if (lObj[3] != null) {
			// lStrAccountNo = lObj[3].toString();
			// }
			// if (lObj[4] != null) {
			// lStrBranchCode = lObj[4].toString();
			// }
			//
			// }
			// }
			if(!lLstPensionerDtls.isEmpty())
			{
				lObjSupplementaryPartyDtlsVO = new SupplementaryPartyDtlsVO();
				lObjSupplementaryPartyDtlsVO = lLstPensionerDtls.get(0);
				lStrSchemeCode = lObjSupplementaryPartyDtlsVO.getSchemeCode();
			}
			lLstBanks = lObjCommonDAO.getBankList(inputMap, gLangID);
			
			inputMap.put("BankList", lLstBanks);
			inputMap.put("PPONo", lStrPPONo);
			inputMap.put("PnsnDtls", lLstPensionerDtls);
			inputMap.put("PensionerName", lStrPensionerName);
			inputMap.put("PensionerCode", lStrPnsnCode);
			inputMap.put("BillTypeId", "45");
			inputMap.put("RecoveryType", lLstRecoveryType);
			inputMap.put("SuppBillType", lStrSuplBillType);
			inputMap.put("SchemeCode", lStrSchemeCode);
			// inputMap.put("BranchName", lStrBranchName);
			// inputMap.put("BankCode", lStrBankCode);
			// inputMap.put("BranchCode", lStrBranchCode);
			// inputMap.put("AccountNo", lStrAccountNo);

			// StringBuilder lStrBldXML = new StringBuilder();
			// lStrBldXML.append("Y");
			// String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
			// lStrBldXML.toString()).toString();
			// inputMap.put("ajaxKey", lStrResult);
			// resObj.setViewName("ajaxData");
			resObj.setViewName("SupplementaryBills");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		resObj.setResultValue(inputMap);
		return resObj;
	}

	public ResultObject validatePPONo(Map<String, Object> inputMap) {

		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		PensionBillDAO lObjPensionBillDAO = new PensionBillDAOImpl(srvcLoc.getSessionFactory());
		List lLstResult = new ArrayList();
		String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
		String lStrFlag = "N";
		try {
			setSessionInfo(inputMap);
			String lStrPPONo = StringUtility.getParameter("PPONo", request);
			lLstResult = lObjPensionBillDAO.isValidPPONo(lStrPPONo.toUpperCase(), gStrLocCode, lStrStatus, gLngPostId);

			if (!lLstResult.isEmpty()) {
				lStrFlag = "Y";
			}

			StringBuilder lStrBldXML = new StringBuilder();
			lStrBldXML.append("<FLAG>");
			lStrBldXML.append(lStrFlag);
			lStrBldXML.append("</FLAG>");

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		resObj.setResultValue(inputMap);
		return resObj;
	}

	/**
	 * Saves Supplementary Bill Data in
	 * 
	 * 
	 * @param Map
	 *            :lMapInput
	 * @return ResultObject
	 */
	public ResultObject saveSuppBill(Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class, serv.getSessionFactory());
		TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
		TrnPensionRecoveryDtlsDAO lObjTrnRecoveryDtls = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class, serv.getSessionFactory());
		SupplementaryBillDAO lObjSupplementaryBillDAO = new SupplementaryBillDAOImpl(TrnPensionSupplyBillDtls.class, serv.getSessionFactory());
		PhysicalCaseInwardDAO lObjPhysicalCaseInwardDAO = new PhysicalCaseInwardDAOImpl(HstPnsnPmntDcrgDtls.class, serv.getSessionFactory());
		TrnPensionBillHdr lObjTrnPensionBillHdr = null;
		TrnPensionBillDtls lObjTrnPensionBillDtls = null;

		Long lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
		String lStrBillType = bundleConst.getString("RECOVERY.SUPP");
		List<RltBillParty> lLstBillParty = new ArrayList();
		TrnPensionSupplyBillDtls lObjTrnPensionSupplyBillDtls = null;
		RltPensioncaseBillDAO lObjBillCasDao = null;
		RltPensioncaseBill lObjRltPensioncaseBill = null;
		HstPnsnPmntDcrgDtls lObjHstPnsnPmntDcrgDtls= null;
		HstCommutationDtls lObjHstCommutationDtls = null;
		Long lRltPensioncaseBillId = null;
		RltBillParty lObjBillParty = null;
		Long lLngBillPartyId = null;
		String lStrBillNo = null;
		Long lLngBillNo = null;
		String lStrShowReadOnly = null;
		String lStrSuppBillType = "";
		Long lLngDcrgDtlsId = null;
		String lStrSixPayCalcFlag = "";
		try {
			setSessionInfo(inputMap);
			lStrSixPayCalcFlag = StringUtility.getParameter("hdnSixPayCalcFlag", request);
			// Getting VO Object from inputMap with the key
			// "DCRGTrnPensionBillDtlsVO"
			if (inputMap.containsKey("showReadOnly")) {
				lStrShowReadOnly = inputMap.get("showReadOnly").toString();
			}
			if (inputMap.containsKey("billNo")) {
				lStrBillNo = inputMap.get("billNo").toString();
			}
			if (lStrBillNo != null && lStrBillNo.length() > 0) {
				lLngBillNo = Long.parseLong(lStrBillNo);
			}
			lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("SuppTrnPensionBillHdrVO");
			lObjTrnPensionBillDtls = (TrnPensionBillDtls) inputMap.get("SuppTrnPensionBillDtlsVO");
			lObjTrnPensionSupplyBillDtls = (TrnPensionSupplyBillDtls) inputMap.get("SuppTrnPensionSupplyBillDtlsVO");
			lStrSuppBillType = inputMap.get("SuppBillType").toString();
			if("DCRG".equals(lStrSuppBillType))
				lObjHstPnsnPmntDcrgDtls = (HstPnsnPmntDcrgDtls)inputMap.get("HstPnsnPmntDcrgDtls");
			if("CVP".equals(lStrSuppBillType))
				lObjHstCommutationDtls = (HstCommutationDtls)inputMap.get("HstCommutationDtls");
			Long lLngTrnPensionRecoveryDtlsId = null;
			RltBillPartyDAO lObjBillPartyDAO = new RltBillPartyDAOImpl(RltBillParty.class, serv.getSessionFactory());

			if (lLngBillNo != null && lLngBillNo > 0) {
				// insert Data into TrnPensionBillHdr.

				if (lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0)

				{
					lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
				}
				if ("N".equals(lStrShowReadOnly)) {

					lObjTrnPensionBillHdrDAO.update(lObjTrnPensionBillHdr);
					lObjTrnPensionBillDtlsDAO.update(lObjTrnPensionBillDtls);
					lObjSupplementaryBillDAO.update(lObjTrnPensionSupplyBillDtls);
					if("DCRG".equals(lStrSuppBillType))
						lObjPhysicalCaseInwardDAO.update(lObjHstPnsnPmntDcrgDtls);
//					if("DCRG".equals(lStrSuppBillType))
//					{
//						if(lObjHstPnsnPmntDcrgDtls != null)
//						{
//							lLngDcrgDtlsId = lObjHstPnsnPmntDcrgDtls.getDcrgDtlsId();
//						}
//						if(lLngDcrgDtlsId == null || "".equals(lLngDcrgDtlsId))
//						{
//							lLngDcrgDtlsId = IFMSCommonServiceImpl.getNextSeqNum("hst_pnsnpmnt_dcrg_dtls", inputMap);
//							lObjHstPnsnPmntDcrgDtls.setDcrgDtlsId(lLngDcrgDtlsId);
//							lObjHstPnsnPmntDcrgDtls.setBillNo(lLngBillNo);
//							lObjPhysicalCaseInwardDAO.create(lObjHstPnsnPmntDcrgDtls);
//						}
//						else
//						{
//							lObjPhysicalCaseInwardDAO.update(lObjHstPnsnPmntDcrgDtls);
//						}
//					}
					if("CVP".equals(lStrSuppBillType))
					{
						lObjPhysicalCaseInwardDAO =new PhysicalCaseInwardDAOImpl(HstCommutationDtls.class, serv.getSessionFactory());
						lObjPhysicalCaseInwardDAO.update(lObjHstCommutationDtls);
						
					}

					// for (int i = 0; i < lLstObjRecovery.size(); i++) {
					// TrnPensionRecoveryDtls lObjRecovery = new
					// TrnPensionRecoveryDtls();
					// lObjRecovery = lLstObjRecovery.get(i);
					// lObjTrnRecoveryDtls.delete(lObjRecovery);
					// }

					// Deleting previous party Info
					lLstBillParty = lObjBillPartyDAO.getPartyByBill(lLngBillNo);
					Iterator<RltBillParty> lItrBillParty = lLstBillParty.iterator();

					while (lItrBillParty.hasNext()) {
						lObjBillPartyDAO.delete(lItrBillParty.next());
					}
					if (inputMap.get("BillPartyDtls") != null) {
						lLstBillParty = (List<RltBillParty>) inputMap.get("BillPartyDtls");
						lItrBillParty = lLstBillParty.iterator();

						while (lItrBillParty.hasNext()) {
							lObjBillParty = lItrBillParty.next();
							lObjBillParty.setBillNo(lLngBillNo);
							lLngBillPartyId = IFMSCommonServiceImpl.getNextSeqNum("rlt_bill_party", inputMap);
							lObjBillParty.setBillPartyId(lLngBillPartyId);
							lObjBillPartyDAO.create(lObjBillParty);
						}
					}

				} else {
					// TrnPensionBillHdr
					lObjTrnPensionBillHdr.setBillType(lStrBillType);
					lObjTrnPensionBillHdr.setBillNo(lLngBillNo);
					lObjTrnPensionBillHdr.setTrnPensionBillHdrId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", inputMap));
					lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);

					// TrnPensionBillDtls
					lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap));
					lObjTrnPensionBillDtls.setTrnPensionBillHdrId(lObjTrnPensionBillHdr.getTrnPensionBillHdrId());
					lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);

					// TrnPensionSupplyBillDtls
					Long lLngPensionSupplyBillId = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_supply_bill_dtls", inputMap);
					lObjTrnPensionSupplyBillDtls.setPensionSupplyBillId(lLngPensionSupplyBillId);
					lObjTrnPensionSupplyBillDtls.setBillNo(lLngBillNo);
					lObjSupplementaryBillDAO.create(lObjTrnPensionSupplyBillDtls);

					// RltPensioncaseBill

					lObjBillCasDao = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class, serv.getSessionFactory());
					lObjRltPensioncaseBill = (RltPensioncaseBill) inputMap.get("SuppRltPensioncaseBillVO");
					lRltPensioncaseBillId = IFMSCommonServiceImpl.getNextSeqNum("rlt_pensioncase_bill", inputMap);
					lObjRltPensioncaseBill.setRltPensioncaseBillId(lRltPensioncaseBillId);
					lObjRltPensioncaseBill.setBillNo(lLngBillNo);
					lObjBillCasDao.create(lObjRltPensioncaseBill);
					
					// HstPnsnPmntDcrgDtls
					if("DCRG".equals(lStrSuppBillType))
					{
						lLngDcrgDtlsId = IFMSCommonServiceImpl.getNextSeqNum("hst_pnsnpmnt_dcrg_dtls", inputMap);
						lObjHstPnsnPmntDcrgDtls.setDcrgDtlsId(lLngDcrgDtlsId);
						lObjHstPnsnPmntDcrgDtls.setBillNo(lLngBillNo);
						lObjPhysicalCaseInwardDAO.create(lObjHstPnsnPmntDcrgDtls);
					}
					
					// HstCommutationDtls
					if("CVP".equals(lStrSuppBillType))
					{
						lObjPhysicalCaseInwardDAO =new PhysicalCaseInwardDAOImpl(HstCommutationDtls.class, serv.getSessionFactory());
						Long lLngCvpDtlsId = IFMSCommonServiceImpl.getNextSeqNum("hst_commutation_dtls", inputMap);
						lObjHstCommutationDtls.setCvpDtlsId(lLngCvpDtlsId);
						lObjHstCommutationDtls.setBillNo(lLngBillNo);
						lObjPhysicalCaseInwardDAO.create(lObjHstCommutationDtls);
					}

				}
				//Insert six pay arrear config
				if("Y".equalsIgnoreCase(lStrSixPayCalcFlag))
				{
					inputMap.put("BillNo", lLngBillNo);
					objRes = serv.executeService("SIXTH_PAY_ARREAR_VOGEN", inputMap);

					objRes = serv.executeService("SAVE_SIXTH_PAY_ARREAR", inputMap);
				}
			}

			inputMap.put("SuppTrnPensionBillDtlsVO", lObjTrnPensionBillDtls);

			inputMap.put("TrnPensionSupplyBillDtls", lObjTrnPensionSupplyBillDtls);

			// TrnPensionRecoveryDtls

			List<TrnPensionRecoveryDtls> lLstObjRecovery = (List<TrnPensionRecoveryDtls>) inputMap.get("SuppTrnPensionRecoveryDtls");
			if (!lLstObjRecovery.isEmpty()) {
				for (int i = 0; i < lLstObjRecovery.size(); i++) {

					TrnPensionRecoveryDtls lObjRecovery = new TrnPensionRecoveryDtls();
					lLngTrnPensionRecoveryDtlsId = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_recovery_dtls", inputMap);
					lObjRecovery = lLstObjRecovery.get(i);
					lObjRecovery.setTrnPensionRecoveryDtlsId(lLngTrnPensionRecoveryDtlsId);
					lObjRecovery.setBillNo(lLngBillNo);

					lObjTrnRecoveryDtls.create(lObjRecovery);
				}
			}
			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			objRes.setThrowable(e);
			objRes.setResultValue(null);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject viewSuppBillData(Map inputMap) {

		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TrnPensionBillHdr lObjTrnPensionBillHdr = null;
		List<CmnLookupMst> lLstRecoveryType = new ArrayList();
		String lStrRcvryFlag = "";
		Map lMapDCRGData = new HashMap();
		String lStrPPONo = null;
		String lStrPnsrCode = null;
		Double lSuppAmt = 0D;
		Double lReducedAmt = 0D;
		Double lRecAmt = 0D;
		Date lStrBillDate = null;
		Long lTrnPensionBillHdrPK = null;
		String lStrPnsnrName = null;
		List<RltBillParty> lLstRltBillPartyVO = null;
		RltBillParty lObjRltBillParty = null;
		String lStrBillType = "";
		List<SupplementaryPartyDtlsVO> lLstSupplementaryPartyDtlsVO = null;
		SupplementaryPartyDtlsVO lObjSupplementaryPartyDtlsVO = null;
		List lLstBillDtlsElem = new ArrayList();
		Iterator lObjIterator = null;
		PensionBillDAO lObjPensionBillDAO = new PensionBillDAOImpl(srvcLoc.getSessionFactory());
		TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class, srvcLoc.getSessionFactory());
		NewPensionBillDAO lObjNewPensionBillDAO = new NewPensionBillDAOImpl(srvcLoc.getSessionFactory());
		MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class, srvcLoc.getSessionFactory());
		CVPRestorationLetterDAO lObjCVPRestorationLetterDAO = new CVPRestorationLetterDAOImpl(null, srvcLoc.getSessionFactory());
		List<ComboValuesVO> lLstBanks = null;
		CommonDAO lObjCommonDAO = new CommonDAOImpl(srvcLoc.getSessionFactory());
		String lStrCurrRoleId = null;
		List<TrnPensionRecoveryDtls> lLstTrnPensionRecoveryDtls = null;
		String lStrPaymentMode = null;
		TrnPensionSupplyBillDtls lObjTrnPensionSupplyBillDtls = null;
		TrnPensionBillDtls lObjTrnPensionBillDtls = new TrnPensionBillDtls();
		StringBuilder lSbCvpBill =new StringBuilder();
		String lStrPensionerName = "";
		Double lArrearAmt = 0.0;
		Double lOther2 = 0.0;
		Double lOther3 = 0.0;
		CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(srvcLoc.getSessionFactory());
		String lStrArrearDtls = null;
		String lStrElementCode = "";
		String lStrSchemeCode = "";
		String lStrBankName = "";
		String lStrBranchName = "";
		String lStrAccountNo = "";
		try {
			setSessionInfo(inputMap);
			Long lStrBillNo = Long.parseLong(inputMap.get("billNo").toString());
			lLstBanks = lObjCommonDAO.getBankList(inputMap, gLangID);

			lStrElementCode = StringUtility.getParameter("elementId", request).trim();
			OnlinePensionCaseDAO lObjOnlinePensionCaseDAO = new OnlinePensionCaseDAOImpl(TrnEcsDtl.class, srvcLoc.getSessionFactory());
			lStrCurrRoleId = lObjCommonPensionDAO.getRoleByElement(lStrElementCode);
			// lStrCurrRoleId =
			// lObjOnlinePensionCaseDAO.getRoleByPost(gLngPostId);
			lStrRcvryFlag= bundleConst.getString("RECOVERY.SUPP");
			if (lStrBillNo != null) {
				// Getting the ObjectVo of TrnPensionBillDtlsVO
				lObjTrnPensionBillHdr = new TrnPensionBillHdr();
				lObjTrnPensionBillHdr = lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo, lStrRcvryFlag);
				lStrBillDate = lObjTrnPensionBillHdr.getBillDate();
				lTrnPensionBillHdrPK = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
				lObjTrnPensionBillDtls = (TrnPensionBillDtls) lObjNewPensionBillDAO.getTrnPensionBillDtls(lTrnPensionBillHdrPK);
				lStrPnsrCode = lObjTrnPensionBillDtls.getPensionerCode();
				lSuppAmt = lObjTrnPensionBillDtls.getReducedPension().doubleValue() + lObjTrnPensionBillDtls.getRecoveryAmount().doubleValue();
				inputMap.put("TrnPensionBillHdr", lObjTrnPensionBillHdr);
				
			}
			inputMap.put("TrnPensionBillDtls", lObjTrnPensionBillDtls);
			

			if (lStrPnsrCode != null && lStrPnsrCode.length() > 0) {
				lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);

				// Getting the ObjectVo of MstPensionerHdr... End...
			}
			if (inputMap.containsKey("RltBillParty")) {
				lLstRltBillPartyVO = (List<RltBillParty>) inputMap.get("RltBillParty");
			}
			lObjIterator = lLstRltBillPartyVO.iterator();
			lLstSupplementaryPartyDtlsVO = new ArrayList();

			while (lObjIterator.hasNext()) {
				lObjRltBillParty = (RltBillParty) lObjIterator.next();
				String lStrPartyName = lObjRltBillParty.getPartyName();
				String lStrBankCode = lObjRltBillParty.getBankCode();
				String lStrBranchCode = lObjRltBillParty.getBranchCode();
				Long lLngMicrCode = lObjRltBillParty.getMicrCode();
				BigDecimal lBdDcmlAmount = lObjRltBillParty.getPartyAmt();
				lStrPaymentMode = lObjRltBillParty.getPaymentMode();
				String lBranchName = lObjPensionBillDAO.getBranchName(lStrBankCode, lStrBranchCode, gStrLocCode);
				lStrBankName = lObjCommonDAO.getBankNameFromBankCode(lObjRltBillParty.getBankCode(), gLangID);
				lStrBranchName = lObjCommonDAO.getBranchNameFromBranchCode(lObjRltBillParty.getBankCode(), lObjRltBillParty.getBranchCode(), gLangID);
				lStrAccountNo = (lObjRltBillParty.getAccntNo() != null) ? lObjRltBillParty.getAccntNo() : "";
				lStrPensionerName = lStrPensionerName +" "+ lStrPartyName +" / "+lObjRltBillParty.getPartyAmt().longValue() +  " / " + lStrBankName + " / " + lStrBranchName + " / " + lStrAccountNo+"$"; 
				lObjSupplementaryPartyDtlsVO = new SupplementaryPartyDtlsVO(lStrPartyName, lStrBankCode, lBranchName, lStrAccountNo, lStrBranchCode, lLngMicrCode, lBdDcmlAmount);
				lLstSupplementaryPartyDtlsVO.add(lObjSupplementaryPartyDtlsVO);
			}
			lObjTrnPensionSupplyBillDtls = lObjPensionBillDAO.getSupplyDtlsVO(lStrPnsrCode, lStrBillNo, gStrLocCode);
			lStrBillType=lObjTrnPensionSupplyBillDtls.getBillType();
			if(!"".equals(lStrBillType) && lStrBillType != null)
			{
				if("PENSION".equals(lStrBillType))
				{
					lStrRcvryFlag= bundleConst.getString("RECOVERY.SUPPPNSN");
				}
				if("DCRG".equals(lStrBillType))
				{
					lStrRcvryFlag= bundleConst.getString("RECOVERY.SUPPDCRG");
					/************************Print DCRG bill start*************/
					ReportExportHelper objExport = new ReportExportHelper();
					StringBuilder lSbDcrgBill =new StringBuilder();
					StringBuilder lSbHeader =new StringBuilder();
					StringBuilder lSbFooter = new StringBuilder();
					List<List> arrOuter = new ArrayList<List>();
					ArrayList lineList = new ArrayList();
					String lStrTreasuryName = "";
					String lStrNameOfOffice = "";
					String lStrHeader="";
					lStrSchemeCode = (lObjTrnPensionBillHdr.getSchemeCode() != null) ? lObjTrnPensionBillHdr.getSchemeCode() : "";
					
					lSbHeader.append("Form M.T.R.45 A");
					lSbHeader.append("\r\n");
					lSbHeader.append("( See Rule 406 A )");
					lSbHeader.append("\r\n");
					lSbHeader.append("Simple Receipt");
					lSbHeader.append("\r\n");
					
					lStrTreasuryName = "Name of the Treasury:-"+space(25)+"$"+SessionHelper.getLocationName(inputMap)+space(48 - SessionHelper.getLocationName(inputMap).length())+"$";	
					
					lStrTreasuryName= lStrTreasuryName + "Token No."+space(39)+"$"
										+"Date :"+space(43)+"$"
										+"Voucher No. "+space(38);
					String lStrOffAddr = lObjCVPRestorationLetterDAO.getOffiCeAddr(gStrLocCode);
					lStrNameOfOffice = "Name of the Office:- "+space(1)+lStrOffAddr;
					
					ColumnVo[] columnHeading = new ColumnVo[2];
					columnHeading[0] =  new ColumnVo(lStrTreasuryName,1,50,0,true,false,false);
					columnHeading[1] =  new ColumnVo(lStrNameOfOffice,1,27,0,true,false,false);
					
					lSbFooter.append("Demand No         : G6");
					lSbFooter.append("\r\n");
					lSbFooter.append("Major Head        : 2071 Pension & Other Retirement Benefits.");
					lSbFooter.append("\r\n");
					lSbFooter.append("Minor Head        : 101 Superannuation and Retirement Allowances.");
					lSbFooter.append("\r\n");
					lSbFooter.append("Sub   Head        : 104 DCRG");
					lSbFooter.append("\r\n");
					lSbFooter.append("Detailed Head     : 04");
					lSbFooter.append("\r\n");
					lSbFooter.append("Bill Type         :");
					lSbFooter.append("\r\n");
					lSbFooter.append("Scheme Code       : "+lStrSchemeCode);
					lSbFooter.append("\r\n");
					lSbFooter.append("( Object Expenditure )");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					String lStrReducedAmt= "Received Sum of(Rs. "+lObjTrnPensionBillDtls.getNetAmount().longValue()+" /-) Rs. "+EnglishDecimalFormat.convertWithSpace(new BigDecimal(lObjTrnPensionBillDtls.getNetAmount().doubleValue()));
					lSbFooter.append(lStrReducedAmt);
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					
					lSbDcrgBill.append("<div><pre>");
					lStrHeader = objExport.getReportFileForCvpBill(arrOuter, columnHeading,lSbHeader.toString(),
							lSbFooter.toString(),null,79 ,true,1);
					lSbDcrgBill.append(lStrHeader);
					
					
					arrOuter = new ArrayList<List>();
					lineList = new ArrayList();
					columnHeading = new ColumnVo[2];
					columnHeading[0] =  new ColumnVo("",1,50,0,true,false,false);
					columnHeading[1] =  new ColumnVo("",1,27,0,true,false,false);
					
					lStrHeader="";
					
					lineList = new ArrayList();
					lineList.add(getChar(51,"-"));
					lineList.add(getChar(27,"-"));
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Name/Amount/Bank/Branch/AC.No : "+lStrPensionerName);
					lineList.add("Non Plan");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Authorised by A.G.Mah.Mumbai.	");
					lineList.add("B DCRG");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Authority No.	");
					lineList.add("B 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add(" ");
					lineList.add("A 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Date : "+DateUtilities.stringFromDate(lStrBillDate));
					lineList.add("A 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("P.P.O. No.  "+lObjTrnPensionBillDtls.getPpoNo());
					lineList.add("");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add(getChar(51,"-"));
					lineList.add(getChar(27,"-"));
					arrOuter.add(lineList);
					
					lSbFooter = new StringBuilder();
					
					lSbFooter.append("Copy - enclosed                      Received Payment");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%80s", "Signature & Designation"));
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%80s", "A.P.A.O/ATO"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%50s", "For use in Treasury"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					lSbFooter.append("Pay Rs.(  "+lObjTrnPensionBillDtls.getNetAmount().longValue()+" /-) Rupees : "+EnglishDecimalFormat.convertWithSpace(new BigDecimal(lObjTrnPensionBillDtls.getNetAmount().doubleValue())));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%70s","Asstt.Pay and Accounts Officer/Asstt. Treasury Officer."));
					lStrHeader = objExport.getReportFileForCvpBill(arrOuter, columnHeading,"",
							lSbFooter.toString(),null,79 ,true,26);
					lSbDcrgBill.append(lStrHeader);
					
					lSbDcrgBill.append("</pre></div>");
					inputMap.put("PrintBillString", lSbDcrgBill);

					/**********Print DCRG Bill End***********/
				}
				if("CVP".equals(lStrBillType))
				{
					//Print CVP bill data start
					
					ReportExportHelper objExport = new ReportExportHelper();
					
					StringBuilder lSbHeader =new StringBuilder();
					StringBuilder lSbFooter = new StringBuilder();
					List<List> arrOuter = new ArrayList<List>();
					ArrayList lineList = new ArrayList();
					String lStrTreasuryName = "";
					String lStrNameOfOffice = "";
					String lStrHeader="";
					
					lStrSchemeCode = (lObjTrnPensionBillHdr.getSchemeCode() != null) ? lObjTrnPensionBillHdr.getSchemeCode() : "";
					
					lSbHeader.append("Form M.T.R.45 A");
					lSbHeader.append("\r\n");
					lSbHeader.append("( See Rule 406 A )");
					lSbHeader.append("\r\n");
					lSbHeader.append("Simple Receipt");
					lSbHeader.append("\r\n");
					
					lStrTreasuryName = "Name of the Treasury:-"+space(25)+"$"+SessionHelper.getLocationName(inputMap)+space(48 - SessionHelper.getLocationName(inputMap).length())+"$";	
					
					lStrTreasuryName= lStrTreasuryName + "Token No."+space(39)+"$"
										+"Date :"+space(43)+"$"
										+"Voucher No. "+space(38);
					String lStrOffAddr = lObjCVPRestorationLetterDAO.getOffiCeAddr(gStrLocCode);
					
					lStrNameOfOffice = "Name of the Office:- "+space(1)+lStrOffAddr;
					
					ColumnVo[] columnHeading = new ColumnVo[2];
					columnHeading[0] =  new ColumnVo(lStrTreasuryName,1,50,0,true,false,false);
					columnHeading[1] =  new ColumnVo(lStrNameOfOffice,1,27,0,true,false,false);
					
					lSbFooter.append("Demand No         : G6");
					lSbFooter.append("\r\n");
					lSbFooter.append("Major Head        : 2071 Pension & Other Retirement Benefits.");
					lSbFooter.append("\r\n");
					lSbFooter.append("Minor Head        : 101 Superannuation and Retirement Allowances.");
					lSbFooter.append("\r\n");
					lSbFooter.append("Sub   Head        : 102 Commuted Value of Pension.");
					lSbFooter.append("\r\n");
					lSbFooter.append("Detailed Head     : 04");
					lSbFooter.append("\r\n");
					lSbFooter.append("Bill Type         :");
					lSbFooter.append("\r\n");
					lSbFooter.append("Scheme Code       : "+lStrSchemeCode);
					lSbFooter.append("\r\n");
					lSbFooter.append("( Object Expenditure )");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					String lStrReducedAmt= "Received Sum of(Rs. "+lObjTrnPensionBillDtls.getNetAmount().longValue()+" /-) Rs. "+EnglishDecimalFormat.convertWithSpace(new BigDecimal(lObjTrnPensionBillDtls.getNetAmount().doubleValue()));
					lSbFooter.append(lStrReducedAmt);
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					
					lSbCvpBill.append("<div><pre>");
					lStrHeader = objExport.getReportFileForCvpBill(arrOuter, columnHeading,lSbHeader.toString(),
							lSbFooter.toString(),null,79 ,true,1);
					lSbCvpBill.append(lStrHeader);
					
					
					arrOuter = new ArrayList<List>();
					lineList = new ArrayList();
					columnHeading = new ColumnVo[2];
					columnHeading[0] =  new ColumnVo("",1,50,0,true,false,false);
					columnHeading[1] =  new ColumnVo("",1,27,0,true,false,false);
					
					lStrHeader="";
					
					lineList = new ArrayList();
					lineList.add(getChar(51,"-"));
					lineList.add(getChar(27,"-"));
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Name/Amount/Bank/Branch/AC.No : "+lStrPensionerName);
					lineList.add("Non Plan");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Authorised by A.G.Mah.Mumbai.	");
					lineList.add("B Commuted Value of Pension");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Authority No.	");
					lineList.add("B 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add(" ");
					lineList.add("A 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("Date : "+DateUtilities.stringFromDate(lStrBillDate));
					lineList.add("A 1-11-1956");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add("P.P.O. No.  "+lObjTrnPensionBillDtls.getPpoNo());
					lineList.add("");
					arrOuter.add(lineList);
					
					lineList = new ArrayList();
					lineList.add(getChar(51,"-"));
					lineList.add(getChar(27,"-"));
					arrOuter.add(lineList);
					
					lSbFooter = new StringBuilder();
					
					lSbFooter.append("Copy - enclosed                      Received Payment");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%80s", "Signature & Designation"));
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%80s", "A.P.A.O/ATO"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%50s", "For use in Treasury"));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(getChar(80, "-"));
					lSbFooter.append("\r\n");
					lSbFooter.append("Pay Rs.(  "+lObjTrnPensionBillDtls.getNetAmount().longValue()+" /-) Rupees : "+EnglishDecimalFormat.convertWithSpace(new BigDecimal(lObjTrnPensionBillDtls.getNetAmount().doubleValue())));
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append("\r\n");
					lSbFooter.append(String.format("%70s","Asstt.Pay and Accounts Officer/Asstt. Treasury Officer."));
					lStrHeader = objExport.getReportFileForCvpBill(arrOuter, columnHeading,"",
							lSbFooter.toString(),null,79 ,true,26);
					lSbCvpBill.append(lStrHeader);
					
					lSbCvpBill.append("</pre></div>");
					inputMap.put("PrintBillString", lSbCvpBill);
					//Print CVP bill data end
				}
			}
			// Getting Recovery Dtls for the pensioner
			lLstRecoveryType = IFMSCommonServiceImpl.getLookupValues("RECVTYPE", gLangID, inputMap);
			
			lLstTrnPensionRecoveryDtls = lObjPensionBillDAO.getTrnPensionRecoveryDtlsForSupp(lStrPnsrCode, lStrRcvryFlag, lStrBillNo);
			// lStrSuppBillType =
			// lObjPensionBillDAO.getBillTypeForSupp(lStrPnsrCode, lStrBillNo,
			// gStrLocCode);
			
			inputMap.put("TrnPensionSupplyBillDtls", lObjTrnPensionSupplyBillDtls);
			inputMap.put("PnsnDtls", lLstSupplementaryPartyDtlsVO);
			inputMap.put("PPONo", lObjTrnPensionBillDtls.getPpoNo());
			inputMap.put("PensionerName", lObjTrnPensionBillDtls.getPensionerName());
			inputMap.put("SuppAmnt", lSuppAmt);
			//inputMap.put("RecoveryAmount", lRecAmt);
			//inputMap.put("NetAmount", lReducedAmt);
			inputMap.put("BillDate", lStrBillDate);
			inputMap.put("MapDCRGData", lMapDCRGData);
			inputMap.put("PensionerCode", lStrPnsrCode);
			inputMap.put("BillTypeId", "45");
			inputMap.put("currRole", lStrCurrRoleId);
			inputMap.put("BankList", lLstBanks);
			inputMap.put("RecoveryDtls", lLstTrnPensionRecoveryDtls);
			inputMap.put("RecoveryType", lLstRecoveryType);
			inputMap.put("SuppBillType", lObjTrnPensionSupplyBillDtls.getBillType());
			inputMap.put("PayMode", lStrPaymentMode);
			inputMap.put("arrearDtls", lStrArrearDtls);
			
			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			resObj.setThrowable(e);
			resObj.setResultValue(null);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}

	private void setSessionInfo(Map inputMap) {

		gLngPostId = SessionHelper.getPostId(inputMap);
		gLngUserId = SessionHelper.getUserId(inputMap);
		gStrLocCode = SessionHelper.getLocationCode(inputMap);
		gDtCurrDt = DBUtility.getCurrentDateFromDB();
		gLangID = SessionHelper.getLangId(inputMap);
	}
	
	public String space(int noOfSpace) {

		String blank = "";
		for (int i = 0; i < noOfSpace; i++) {
			blank += "\u00a0";
		}
		return blank;
	}
	
	private String getChar(int count, String ele) {

		StringBuffer lSBSpace = new StringBuffer();
		for (int i = 0; i < count-1; i++) {
			lSBSpace.append(ele);
		}
		return lSBSpace.toString();
	}
	
	public ResultObject validatePnsnrBeforeSixPayArrerCalc(Map inputMap) {

		ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrPensionerCode = "";
		StringBuilder lStrBldXML = new StringBuilder();
		try {
			setSessionInfo(inputMap);
			lStrPensionerCode = StringUtility.getParameter("pensionerCode", request);
			Integer lIntRevisionCnt = 0;
			SupplementaryBillDAO lObjSupplementaryBillDAO = new SupplementaryBillDAOImpl(TrnPensionSixpayfpArrear.class, serv.getSessionFactory());
			if(!"".equals(lStrPensionerCode))
			{
				lIntRevisionCnt = lObjSupplementaryBillDAO.getPnsnrSixPayArrearRevisionCnt(lStrPensionerCode);
			}
			
			lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<ISEXISTS>");
			if (lIntRevisionCnt == 0) {
				lStrBldXML.append("N");
			} else {
				lStrBldXML.append("Y");
			}
			lStrBldXML.append("</ISEXISTS>");
			lStrBldXML.append("</XMLDOC>");

			gLogger.info(" lStrBldXML :: " + lStrBldXML);
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");
			resObj.setResultValue(inputMap);
			
			
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
			resObj.setThrowable(e);
			resObj.setResultValue(null);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}
		return resObj;
	}
	
}
