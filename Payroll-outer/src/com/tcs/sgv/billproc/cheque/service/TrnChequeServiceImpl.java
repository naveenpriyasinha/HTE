package com.tcs.sgv.billproc.cheque.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.billproc.cheque.dao.RltBillChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.valueobject.ChequeVO;
import com.tcs.sgv.billproc.cheque.valueobject.LogicChequeVO;
import com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque;
import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.billproc.common.dao.ShowBillDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.service.PhyBillServiceImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.DDOInfoDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.dao.PartyMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.util.ChequePrintUtil;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.MstParty;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataService;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.appwfinterface.WorkFlowInterfaceImpl;
import com.tcs.sgv.wf.util.IntimationRule;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO;
import com.tcs.sgv.wf.valueobject.WfNotificationVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;
/** 
 *  com.tcs.sgv.billproc.cheque.dao.TrnChequeServiceImpl
 *  
 *  This is Implementation service class for  getting cheque details 
 *  and for all cheque functions
 *  
 * 	Date of Creation : 10th July 2007
 * 
 *  Author : Vidhya Mashru 
 *  
 *  Revision History 
 *  =====================
 *  Vidhya M    23-Oct-2007  Made changes for code formatting
 *  Hiral Shah  24-Oct-2007  Changes for 'Written Cheques'. 
 */
public class TrnChequeServiceImpl extends ServiceImpl implements
		TrnChequeService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	String gStrChqType = bundleConst.getString("CMN.Bill");
	static HashMap lMapPDF = new HashMap();
	 /**
	 * Method to chque Range Update At Advice Genration
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author bhavik
	 */	
	public ResultObject chqRangeUpdateAtAdviceGen(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		try {
			String[] lStrChqNumbers = (String[]) request
					.getParameterValues("chequesData");
			if (lStrChqNumbers != null && lStrChqNumbers.length > 0) {
				logger.info("Hi !!!!!!!!!!! " + lStrChqNumbers.length);
				TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
						TrnChequeDtls.class, serv.getSessionFactory());
				;
				TrnChequeDtls trnChequeDtls = null;
				for (int i = 0; i < lStrChqNumbers.length; i++) {
					logger.info("lStrChqNumbers");
					String date = (String) request.getParameter("date_"
							+ lStrChqNumbers[i]);
					if (date != null && date.length() > 0) {
						trnChequeDtls = chequeDAO
								.getChqVoFrmChqNo(lStrChqNumbers[i]);
						SimpleDateFormat sdf = new java.text.SimpleDateFormat(
								"dd/MM/yyyy");
						Date lDate = sdf.parse(date);
						trnChequeDtls.setFromDt(lDate);
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTimeInMillis(trnChequeDtls.getFromDt().getTime());
						gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc
								.getMaximum(gc.DAY_OF_MONTH) - 1);
						gc.add(GregorianCalendar.MONTH, 3);
						Date toDt = gc.getTime();
						trnChequeDtls.setToDt(toDt);
						chequeDAO.update(trnChequeDtls);
					}

					logger.info("Date is :::: " + date);
				}
			}
			inputMap.put("MESSAGECODE", (long) 1038);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in chqRangeUpdateAtAdviceGen " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to get Cheque Detail From Chque No
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject getChequeDetailFromChqNo(Map inputMap) {
		String lStrNewChqNo = null;
		logger.info(" Inside getChequeDetailFromChqNo ");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			String lStrChkNo = (String) request.getParameter("txtChqNo");
			TrnChequeDAOImpl chequeDAO = null;
			TrnChequeDtls trnChequeDtls = null;

			try {
				Long.parseLong(lStrChkNo);
				chequeDAO = new TrnChequeDAOImpl(TrnChequeDtls.class, serv
						.getSessionFactory());
				trnChequeDtls = chequeDAO.getChqVoFrmChqNo(lStrChkNo);
			} catch (Exception e) {

			}

			// inputMap.put("chkDtls", trnChequeDtls);
			RltBillChequeDAOImpl billCheDAO = new RltBillChequeDAOImpl(
					RltBillCheque.class, serv.getSessionFactory());
			DDOInfoDAOImpl dDOInfoDAOImpl = new DDOInfoDAOImpl(serv
					.getSessionFactory());
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String lStrAjaxString = "";
			List lBillChq = null;
			List lDdoList = null;
			StringBuffer sb = new StringBuffer("");
			ArrayList lStrTempDdo = new ArrayList();
			if (trnChequeDtls != null) {
				lBillChq = billCheDAO.getBillFromCheque(trnChequeDtls
						.getChequeId());
				logger.info("lBillChq size is  :::::::" + lBillChq.size());
				for (int i = 0; i < lBillChq.size(); i++) {
					RltBillCheque rbc = new RltBillCheque();
					rbc = (RltBillCheque) lBillChq.get(i);
					Long lStrBillNumber = rbc.getBillNo();
					logger.info("lStrBillNumber is :::::::"
							+ lStrBillNumber.toString());
					TrnBillRegister trnBillReg = pbDaoImpl.read(lStrBillNumber);
					lDdoList = dDOInfoDAOImpl.getDDOInfo(trnBillReg
							.getDdoCode(), SessionHelper.getLangId(inputMap),
							SessionHelper.getDbId(inputMap));
					for (int j = 0; j < lDdoList.size(); j++) {
						OrgDdoMst orgDDOMst = (OrgDdoMst) lDdoList.get(j);
						sb.append(orgDDOMst.getDdoName());
						lStrTempDdo.add(orgDDOMst.getDdoName());
						if (i < lBillChq.size() - 1) {
							sb.append("~");

						}
					}
				}
				// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				// sdf = trnChequeDtls.getCreatedDate();
				Long lLongnextChqNum = chequeDAO
						.getLatestChequeNum(SessionHelper
								.getLocationCode(inputMap));

				GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis(trnChequeDtls.getToDt().getTime());
				gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc
						.getMaximum(gc.DAY_OF_MONTH) - 1);
				gc.add(GregorianCalendar.MONTH, 1);
				Date toDt = gc.getTime();

				SimpleDateFormat sdf = new SimpleDateFormat(
						"EEE MMM dd hh:mm:ss z yyyy");
				sdf.applyPattern("dd/MM/yyyy");
				String newDate = sdf.format(toDt);
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				sdf1.applyPattern("dd/MM/yyyy");
				String newDate1 = sdf1.format(trnChequeDtls.getFromDt());

				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				sdf1.applyPattern("dd/MM/yyyy");
				String txtChqDate = sdf1.format(trnChequeDtls.getCreatedDate());
				boolean noErrThr = true; // any cancle or already revalid err
											// is ther or not
				if (trnChequeDtls.getStatus().equals("CHQ_RVLD")) {
					if (request.getParameter("chqPage") != null
							&& (request.getParameter("chqPage"))
									.equals("chqRvld")) {
						lStrNewChqNo = "RENAMED";
						lStrAjaxString = new AjaxXmlBuilder().addItem(
								"txtPartyName",
								String.valueOf(trnChequeDtls.getPartyName()))
								.addItem(
										"txtChqAmt",
										String.valueOf(trnChequeDtls
												.getChequeAmt())).addItem(
										"txtChqDate", txtChqDate).addItem(
										"txtNewChqNo", lStrNewChqNo).addItem(
										"txtChqFromDt", newDate1).addItem(
										"txtChqToDt", newDate).addItem(
										"MessageStatus", sb.toString())
								.toString().toString();
						noErrThr = false;
					}

				} else if (trnChequeDtls.getStatus().equals(
						bundleConst.getString("STATUS.CheqCancel"))) {
					lStrNewChqNo = bundleConst.getString("STATUS.CheqCancel");
					lStrAjaxString = new AjaxXmlBuilder().addItem(
							"txtPartyName",
							String.valueOf(trnChequeDtls.getPartyName()))
							.addItem(
									"txtChqAmt",
									String
											.valueOf(trnChequeDtls
													.getChequeAmt())).addItem(
									"txtChqDate", txtChqDate).addItem(
									"txtNewChqNo", lStrNewChqNo).addItem(
									"txtChqFromDt", newDate1).addItem(
									"txtChqToDt", newDate).addItem(
									"MessageStatus", sb.toString()).toString()
							.toString();
					noErrThr = false;
				} else if (!(trnChequeDtls.getClearedDt() == null)) {
					lStrNewChqNo = "CLEARED";
					lStrAjaxString = new AjaxXmlBuilder().addItem(
							"txtPartyName",
							String.valueOf(trnChequeDtls.getPartyName()))
							.addItem(
									"txtChqAmt",
									String
											.valueOf(trnChequeDtls
													.getChequeAmt())).addItem(
									"txtChqDate", txtChqDate).addItem(
									"txtNewChqNo", lStrNewChqNo).addItem(
									"txtChqFromDt", newDate1).addItem(
									"txtChqToDt", newDate).addItem(
									"MessageStatus", sb.toString()).toString()
							.toString();
					noErrThr = false;
				}

				if (noErrThr) {
					lStrNewChqNo = lLongnextChqNum.toString();
					lStrAjaxString = new AjaxXmlBuilder().addItem(
							"txtPartyName",
							String.valueOf(trnChequeDtls.getPartyName()))
							.addItem(
									"txtChqAmt",
									String
											.valueOf(trnChequeDtls
													.getChequeAmt())).addItem(
									"txtChqDate", txtChqDate).addItem(
									"txtNewChqNo", lStrNewChqNo).addItem(
									"txtChqFromDt", newDate1).addItem(
									"txtChqToDt", newDate).addItem(
									"MessageStatus", sb.toString())
							.addItem("txtAccountNo",
									trnChequeDtls.getAccntNum()).addItem(
									"txtAddress", trnChequeDtls.getPartyAddr())
							.addItem("partyCode", trnChequeDtls.getPartyCode())
							.toString().toString();
				}

			} else {
				lStrAjaxString = new AjaxXmlBuilder().addItem("txtPartyName",
						"-").addItem("txtChqAmt", "-").addItem("txtChqDate",
						"-").addItem("txtNewChqNo", "-").addItem(
						"txtChqFromDt", "-").addItem("txtChqToDt", "-")
						.toString();
			}
			inputMap.put("ajaxKey", lStrAjaxString);
			logger.info("Ddo List is ::::: " + sb.toString());
			resObj.setSessionValues("ddoLists", lStrTempDdo);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getChequeDetailFromChequeNo " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to revalidate Cheque
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject revalidateCheque(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");

			String lStrChkNo = (String) request.getParameter("txtChqNo");
			String lStrChqToDt = (String) request.getParameter("txtChqToDt");

			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date toDate = null;

			try {
				toDate = sdf.parse(lStrChqToDt);
			} catch (Exception e) {
				e.printStackTrace();
			}

			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			TrnChequeDtls trnChequeDtls = chequeDAO.getChqVoFrmChqNo(lStrChkNo);

			trnChequeDtls.setToDt(toDate);
			trnChequeDtls.setUpdatedDate(new java.util.Date());
			trnChequeDtls.setUpdatedUserId(SessionHelper.getUserId(request));
			trnChequeDtls.setUpdatedPostId(SessionHelper.getPostId(inputMap));
			trnChequeDtls.setStatus(bundleConst.getString("STATUS.CheqRevalidate"));
			trnChequeDtls.setStatusDate(new java.util.Date());
			chequeDAO.update(trnChequeDtls);
			// Added By Hiral
			inputMap.put("TrnChequeVo", trnChequeDtls);
			updateRptPayDtls(inputMap, Long.parseLong(lStrChkNo));
			// End : Added By Hiral
			resObj.setResultValue(inputMap);
			resObj.setViewName("chqRevalidateChq");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in revalidateCheque " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to getting New TrnChequeDtls vo
	 * 
	 * @param  TrnChequeDtls : trnChequeDtls
	 * @return TrnChequeDtls vo
	 * @author vidhya
	 */

	public TrnChequeDtls getNewTrnChequeDtls(TrnChequeDtls trnChequeDtls) {
		TrnChequeDtls newTrnChequeDtls = new TrnChequeDtls();
		newTrnChequeDtls.setChequeAmt(trnChequeDtls.getChequeAmt());
		newTrnChequeDtls.setTrnCounter(new Integer(1));
		newTrnChequeDtls.setChequeType(trnChequeDtls.getChequeType());
		newTrnChequeDtls.setGroupId(trnChequeDtls.getGroupId());
		newTrnChequeDtls.setAccntNum(trnChequeDtls.getAccntNum());
		newTrnChequeDtls.setPartyAddr(trnChequeDtls.getPartyAddr());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = new java.util.Date();
		logger.info(" Current date is  ::: " + fromDate.toString());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(fromDate.getTime());
		gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc
				.getMaximum(gc.DAY_OF_MONTH) - 1);
		gc.add(GregorianCalendar.MONTH, 3);
		Date toDt = gc.getTime();
		newTrnChequeDtls.setFromDt(fromDate);
		newTrnChequeDtls.setToDt(toDt);
		return newTrnChequeDtls;
	}
	 /**
	 * Method to get New RltBillCheque vo
	 * 
	 * @param  RltBillCheque : rltBillCheque
	 * @return vo
	 * @author vidhya
	 */

	public RltBillCheque getNewRltBillCheque(RltBillCheque rltBillCheque) {
		RltBillCheque newRltBillCheque = new RltBillCheque();
		newRltBillCheque.setBillNo(rltBillCheque.getBillNo());
		newRltBillCheque.setPartyAmt(rltBillCheque.getPartyAmt());
		return newRltBillCheque;
	}
	 /**
	 * Method to cancle Cheque
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject 
	 * @author vidhya
	 */

	public ResultObject cancleCheque(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {

			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");

			String lStrChkNo = (String) request.getParameter("txtChqNo");
			String lStrChkReason = (String) request.getParameter("txtReason");

			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			TrnChequeDtls trnChequeDtls = chequeDAO.getChqVoFrmChqNo(lStrChkNo);

			trnChequeDtls.setStatus(bundleConst.getString("STATUS.CheqCancel"));
			trnChequeDtls.setStatusDate(new Date(System.currentTimeMillis()));
			trnChequeDtls.setRemarks(lStrChkReason);
			trnChequeDtls.setUpdatedDate(new java.util.Date());
			trnChequeDtls.setUpdatedUserId(SessionHelper.getUserId(request));
			trnChequeDtls.setUpdatedPostId(SessionHelper.getPostId(inputMap));
			chequeDAO.update(trnChequeDtls);
			// Added By Hiral
			inputMap.put("TrnChequeVo", trnChequeDtls);
			updateRptPayDtls(inputMap, Long.parseLong(lStrChkNo));
			// End : Added By Hiral
			resObj.setResultValue(inputMap);
			resObj.setViewName("chqCancellation");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in cancelCheque " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to Rrename Cheque
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject renameCheque(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			String lStrChkNo = (String) request.getParameter("txtChqNo");
			String lStrChkNoNew = (String) request.getParameter("txtNewChqNo");
			String lStrAccountNo = (String) request
					.getParameter("txtAccountNo");
			String lStrAddress = (String) request.getParameter("txtAddress");
			String lStrpartyCode = (String) request.getParameter("partyCode");
			String strMessage = "";
			String txtNewChqNo = "";
			boolean errFlag = false;

			try {
				Long.parseLong(lStrChkNoNew);
				if (Long.parseLong(lStrChkNoNew) <= 0) {
					errFlag = true;
					strMessage = "Not Valid Cheque Number";
					txtNewChqNo = "-";
					logger.info("not exception But err");
				}
			} catch (Exception e) {

				errFlag = true;
				strMessage = "Not Valid Cheque Number";
				txtNewChqNo = "-";				
				logger.error(" Error in renameCheque " + e,e);
			}

			FinancialYearDAOImpl finYrDAO = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			RltBillChequeDAOImpl billCheDAO = new RltBillChequeDAOImpl(
					RltBillCheque.class, serv.getSessionFactory());
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			TrnChequeDtls trnChequeDtls = chequeDAO.getChqVoFrmChqNo(lStrChkNo);

			Long chequeNo = null;
			String[] result = null;

			if (!errFlag) {
				chequeNo = Long.parseLong(lStrChkNoNew);
				result = isChequePresent(chequeNo, chequeNo, serv);
				if (result != null) {
					errFlag = true;
					strMessage = "Cheque No." + chequeNo + " Already present";
					txtNewChqNo = "-";
				}
			}

			String lStrChkAmt = (String) request.getParameter("txtChqAmt");
			String lStrChkType = null;
			String lStrChkFavourNew = null;

			lStrChkFavourNew = (String) request.getParameter("txtPartyNameNew");
			if (lStrChkFavourNew != null && !lStrChkFavourNew.equals("")
					&& lStrChkFavourNew.length() > 0) {
			} else {
				if (!errFlag) {
					errFlag = true;
					strMessage = "Favour of (New) is Mandatory";
					txtNewChqNo = lStrChkNoNew;
				}
			}

			if (errFlag) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				sdf1.applyPattern("dd/MM/yyyy");
				String txtChqDate = sdf1.format(trnChequeDtls.getCreatedDate());
				logger.info("\n\n date in rename chq is :::::::::::::::: "
						+ txtChqDate);
				String lStrAjaxString = new AjaxXmlBuilder().addItem(
						"txtPartyName", trnChequeDtls.getPartyName()).addItem(
						"txtChqAmt", trnChequeDtls.getChequeAmt().toString())
						.addItem("txtChqDate", txtChqDate).addItem(
								"txtNewChqNo", txtNewChqNo).addItem("chkbox",
								String.valueOf(trnChequeDtls.getChequeId()))
						.addItem("MessageStatus", strMessage).toString();
				inputMap.put("ajaxKey", lStrAjaxString);
				resObj.setResultValue(inputMap);
				resObj.setViewName("ajaxData");
				logger.info("I am returning with Message" + strMessage);
				return resObj;
			}
			if (request.getParameter("ddoParty") != null) {
				lStrChkType = (String) request.getParameter("ddoParty");
			}

			String lStrChkReason = (String) request.getParameter("txtReason");
			TrnChequeDtls trnChequeDtlsNew = new TrnChequeDtls();
			trnChequeDtlsNew = getNewTrnChequeDtls(trnChequeDtls);

			Long lCheqId = BptmCommonServiceImpl.getNextSeqNum(
					"trn_cheque_dtls", inputMap);
			// trnChequeDtlsNew.setChequeAmt(BigDecimal.valueOf(Long.parseLong(lStrChkAmt)));
			trnChequeDtlsNew.setChequeNo(Long.parseLong(lStrChkNoNew));
			trnChequeDtlsNew.setPrevChequeId(trnChequeDtls.getChequeId());
			trnChequeDtlsNew.setChequeId(lCheqId);
			logger.info(" new enter chq is  ::: " + lCheqId);
			trnChequeDtlsNew.setCreatedDate(new java.util.Date());
			trnChequeDtlsNew
					.setCreatedPostId(SessionHelper.getPostId(inputMap));
			trnChequeDtlsNew.setCreatedUserId(SessionHelper.getUserId(request));

			if (lStrChkType.equals("~")) {
				trnChequeDtlsNew.setPartyName(trnChequeDtls.getPartyName());
				trnChequeDtlsNew.setDdoParty(trnChequeDtls.getDdoParty());
				trnChequeDtlsNew.setStatus(bundleConst.getString("STATUS.CheqDuplicate"));
				trnChequeDtlsNew.setAccntNum(trnChequeDtls.getAccntNum());
				trnChequeDtlsNew.setPartyAddr(trnChequeDtls.getPartyAddr());
				trnChequeDtlsNew.setPartyCode(trnChequeDtls.getPartyCode());
			} else {
				trnChequeDtlsNew.setPartyName(lStrChkFavourNew);
				trnChequeDtlsNew.setDdoParty(lStrChkType);
				trnChequeDtlsNew.setStatus(bundleConst.getString("STATUS.CheqRename"));
				trnChequeDtlsNew.setAccntNum(lStrAccountNo);
				trnChequeDtlsNew.setPartyAddr(lStrAddress);
				trnChequeDtlsNew.setPartyCode(lStrpartyCode);
			}

			trnChequeDtlsNew.setRemarks(lStrChkReason);
			trnChequeDtlsNew.setDbId(SessionHelper.getDbId(inputMap));

			trnChequeDtlsNew.setLocationCode(SessionHelper
					.getLocationCode(inputMap));
			logger.info("Date is  ::: " + new java.util.Date());
			Integer lObjFinYr = finYrDAO.getFinYearIdByCurDate();
			logger.info(" Fin yr id is  ::::::::::" + lObjFinYr);
			trnChequeDtlsNew.setFinYearId(lObjFinYr.longValue());
			trnChequeDtlsNew
					.setStatusDate(new Date(System.currentTimeMillis()));
			chequeDAO.create(trnChequeDtlsNew);
			trnChequeDtls.setStatus(bundleConst.getString("STATUS.CheqCancel"));
			trnChequeDtls.setStatusDate(new Date(System.currentTimeMillis()));
			trnChequeDtls.setUpdatedDate(new java.util.Date());
			trnChequeDtls.setUpdatedUserId(SessionHelper.getUserId(request));
			trnChequeDtls.setUpdatedPostId(SessionHelper.getPostId(inputMap));
			chequeDAO.update(trnChequeDtls);

			// Added By Hiral

			inputMap.put("TrnChequeVo", trnChequeDtlsNew);
			RptPaymentDtls lObjRptPayDtls = generateRptPaymentVo(inputMap);
			Map lMapTest = lObjRptPayDtls.validateData();

			if (Boolean.parseBoolean(lMapTest.get("flag").toString())) {
				DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
				Map lMapPass = new HashMap();
				lMapPass.put("map", inputMap);
				lMapPass.put("RptPaymentVO", lObjRptPayDtls);
				lObjDssDataSrvc.insertPaymentData(lMapPass);
				lMapPass.remove("RptPaymentVO");
				logger
						.info("Payment Data for rename/duplicate inserted Succesfully for Iteration : "
								+ trnChequeDtls.getChequeNo());
			}

			inputMap.put("TrnChequeVo", trnChequeDtls);
			updateRptPayDtls(inputMap, Long.parseLong(lStrChkNo));
			// End : Added By Hiral

			List rltBillChequeList = billCheDAO.getBillFromCheque(trnChequeDtls
					.getChequeId());
			for (int i = 0; i < rltBillChequeList.size(); i++) {
				RltBillCheque rltBillCheque = (RltBillCheque) rltBillChequeList
						.get(i);
				RltBillCheque rltBillChequeNew = getNewRltBillCheque(rltBillCheque);
				Long lRltBillCheqId = BptmCommonServiceImpl.getNextSeqNum(
						"rlt_bill_cheque", inputMap);
				rltBillChequeNew.setBillChequeId(lRltBillCheqId);
				rltBillChequeNew.setChequeId(trnChequeDtlsNew.getChequeId());
				rltBillChequeNew.setCreatedDate(new java.util.Date());
				rltBillChequeNew.setCreatedPostId(SessionHelper
						.getPostId(inputMap));
				rltBillChequeNew.setCreatedUserId(SessionHelper
						.getUserId(request));
				rltBillChequeNew.setDbId(SessionHelper.getDbId(inputMap));
				rltBillChequeNew.setLocationCode(SessionHelper
						.getLocationCode(inputMap));
				rltBillChequeNew.setGroupId(trnChequeDtlsNew.getGroupId());
				billCheDAO.create(rltBillChequeNew);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			sdf1.applyPattern("dd/MM/yyyy");
			String txtChqDate = sdf1.format(trnChequeDtls.getCreatedDate());
			logger.info("date format is :::::::::" + txtChqDate);
			String lStrAjaxString = new AjaxXmlBuilder().addItem(
					"txtPartyName",
					String.valueOf(trnChequeDtls.getPartyName())).addItem(
					"txtChqAmt", String.valueOf(trnChequeDtls.getChequeAmt()))
					.addItem("txtChqDate", txtChqDate).addItem("txtNewChqNo",
							trnChequeDtlsNew.getChequeNo().toString()).addItem(
							"chkbox",
							String.valueOf(trnChequeDtlsNew.getChequeId()))
					.toString();
			inputMap.put("ajaxKey", lStrAjaxString);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in renameCheque " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to Rename Adivce Voucher
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject renameAdivceVoucher(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			String lStrChqNo = (String) request.getParameter("chkbox");
			TrnChequeDtls trnCheque = chequeDAO.read(Long.parseLong(lStrChqNo));
			ArrayList adviceList = new ArrayList();
			String advice = "";
			long longAdviceNum = chequeDAO.getMaxAdviceNo(SessionHelper
					.getLocationCode(inputMap));
			trnCheque.setAdviceNo(longAdviceNum);

			trnCheque
					.setAdviceDt(new java.util.Date(System.currentTimeMillis()));
			CommonVO comVO = new CommonVO();
			comVO.setCommonId(String.valueOf(trnCheque.getChequeNo()));
			comVO.setCommonDesc(String.valueOf(longAdviceNum));

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			sdf1.applyPattern("dd/MM/yyyy");
			String txtChqDate = sdf1.format(trnCheque.getFromDt());
			comVO.setFrmDate(txtChqDate);
			if (chequeDAO.isValidateAdvice(lStrChqNo)) {
				chequeDAO.update(trnCheque);
				// Added By Hiral
				inputMap.put("TrnChequeVo", trnCheque);
				updateRptPayDtls(inputMap, trnCheque.getChequeNo());
				// End : Added By Hiral
			} else {
				comVO.setCommonDesc("Advice already generated");
			}

			adviceList.add(comVO);

			// longAdviceNum++;
			inputMap.put("AdviceList", adviceList);
			objRes.setResultValue(inputMap);
			objRes.setViewName("adviceVoucher");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in renameAdviceVoucher " + e,e);
		}
		return objRes;

	}
	 /**
	 * Method to Check Bill Chque Combination
	 * 
	 * @param  Map : inputMap
	 * @return boolean
	 * @author vidhya
	 */

	public boolean isCheckBillChkCombi(Map inputMap) {
		logger.info("In checkBillChkCombi !!!!!!!!!!!!!!!!!!!!!!!");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

		boolean returnFlag = true;
		String lStrBillArr[] = (String[]) request.getParameterValues("BillArr");
		if (lStrBillArr != null && lStrBillArr.length > 0) {
			for (int i = 0; i < lStrBillArr.length; i++) {
				logger.info(" ~~~~~~~~~~~~~~~~~~  " + "index id  :: " + i + " "
						+ lStrBillArr[i]);
			}

			String lStrBillSplitArr[] = lStrBillArr[0].split(",");
			String[] bills = lStrBillSplitArr;
			logger.info("Bill length is  :::" + bills.length);
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			int m = 0;
			if (bills != null) {
				for (int i = 0; i < bills.length; i++) {
					String result[] = bills[i].split("~");
					for (int j = 3; j < result.length; j++) {
						m++;
					}
				}
			}

			String lObjBillNum[] = new String[m];
			String lObjChkNum[] = new String[m];
			if (bills != null) {
				m = 0;
				for (int i = 0; i < bills.length; i++) {
					String result[] = bills[i].split("~");
					for (int j = 3; j < result.length; j++) {
						lObjBillNum[m] = result[0];
						lObjChkNum[m] = result[j];
						m++;
					}
				}
			}
			logger.info("lObjBillNum  length is " + lObjBillNum.length);
			returnFlag = chequeDAO.isCheckBillChkCombi(lObjBillNum, lObjChkNum);
		}
		return returnFlag;
	}
	 /**
	 * Method to load Cheque Preparation Screen
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject loadChequePrepScreen(Map inputMap) {
		ResultObject rs = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			PartyMstDAOImpl partyDAO = new PartyMstDAOImpl(MstParty.class, serv
					.getSessionFactory());
			ShowBillDAOImpl showBillDAO = new ShowBillDAOImpl(
					TrnShowBill.class, serv.getSessionFactory());
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			HttpSession hs = request.getSession();
			Long langId = SessionHelper.getLangId(inputMap);

			String billsNo = null;

			if (request.getParameter("chkbox") != null) {
				billsNo = request.getParameter("chkbox");
			} else {
				logger.info("in Else Part Cant Get From request");
				billsNo = "-1~-1~-1~-1";
				return null;
			}

			logger.info("Getting check box ::: " + billsNo);

			int countBill = 0;
			StringTokenizer stCount = new StringTokenizer(billsNo, ",");
			{
				while (stCount.hasMoreTokens()) {
					countBill++;
					stCount.nextToken();
				}
			}

			// countBill++; // only one comma have two bills

			String[] lStrBillNo = new String[countBill];
			String[] lStrTokenNo = new String[countBill];
			String[] lStrBudMjrHd = new String[countBill];
			String[] lStrCardexNo = new String[countBill];

			int num = 0;
			StringTokenizer st = new StringTokenizer(billsNo, ",");
			{
				while (st.hasMoreTokens()) {
					logger.info("Getting Bills :::" + lStrBillNo[num]);
					StringTokenizer st1 = new StringTokenizer(st.nextToken(),
							"~");
					lStrBillNo[num] = st1.nextToken();
					logger.info("After getting bills");

					lStrTokenNo[num] = st1.nextToken();
					lStrBudMjrHd[num] = st1.nextToken();
					lStrCardexNo[num] = st1.nextToken();
					num++;
				}

			}
			List partyList = partyDAO.getAllParty();
			inputMap.put("PartyList", partyList);

			List billList = new ArrayList();
			List lArrpartyList = new ArrayList();
			TrnShowBill trnShowBill = null;
			for (int i = 0; i < lStrBillNo.length; i++) {
				inputMap.put("billNo", lStrBillNo[i]);
				inputMap.put("cardexNo", lStrCardexNo[i]);
				trnShowBill = showBillDAO.getBilldetails(inputMap);
				billList.add(trnShowBill);
				List partyBillList = chequeDAO.getPartyForBill(Long
						.parseLong(lStrBillNo[i]), langId);
				logger.info("========= " + partyBillList);
				if (partyBillList != null && partyBillList.size() > 0) {
					lArrpartyList.addAll(partyBillList);
				}
			}
			logger.info("--------- party list returned............. "
					+ partyList);

			request.getSession().removeAttribute("ChequeList");
			inputMap.put("BillPartyList", lArrpartyList);
			rs.setResultValue(inputMap);
			rs.setSessionValues("BillList", billList);
			rs.setViewName("chqWriteChq");
		} catch (Exception e) {
			rs.setResultValue(null);
			rs.setThrowable(e);
			rs.setResultCode(ErrorConstants.ERROR);
			rs.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in loadChequePrepScreen " + e,e);
		}
		return rs;
	}
	 /**
	 * Method to put Cheque In Session
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject putChequeInSession(Map inputMap) {
		logger.info(" IN session put for cheques");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			FinancialYearDAOImpl finYrDAO = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl commonDAO = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			String lStrFlag = StringUtility.getParameter("flag", request);
			String lStrRowCnt = StringUtility.getParameter("rowCount", request);
			String lStrNumOfCheqs = StringUtility.getParameter("rdoTransReg",
					request);
			// String groupId = String.valueOf(chequeDAO.getGroupId());
			int rowCnt = 0;
			if (lStrRowCnt != null && !lStrRowCnt.equals("")) {
				rowCnt = Integer.parseInt(lStrRowCnt);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			ArrayList chequeList = new ArrayList();

			ArrayList logicVoList = new ArrayList();

			if (request.getSession().getAttribute("ChequeList") != null) {
				chequeList = (ArrayList) request.getSession().getAttribute(
						"ChequeList");
			}
			String lStrTCCheques = DBConstants.CHEQ_TYPE_TC;

			String lStrLookupID = BptmCommonServiceImpl.getLookupIdFromName(
					lStrTCCheques, inputMap);

			if (lStrFlag.equals("ADD")) {
				for (int liCnt = 0; liCnt < rowCnt; liCnt++) {
					LogicChequeVO logicVO = new LogicChequeVO();
					String lStrChqAmt = StringUtility.getParameter("txtChqAmt"
							+ (liCnt + 1), request);
					logicVO.setChequeAmt(new java.math.BigDecimal(lStrChqAmt));
					logicVO.setChequeType(lStrTCCheques);
					logicVO.setFromDt(StringUtility.getParameter("txtDate"
							+ (liCnt + 1), request));
					String lStrPartyName = StringUtility.getParameter(
							"txtPartyName" + (liCnt + 1), request);
					String lStrCheqType = StringUtility.getParameter("cmbType"
							+ (liCnt + 1), request);
					// add by bee
					String lStrPartyAddr = StringUtility.getParameter(
							"txtAddress" + (liCnt + 1), request);
					String lStrPartyAcc = StringUtility.getParameter(
							"txtAccountNo" + (liCnt + 1), request);
					String lStrPartyCode = StringUtility.getParameter(
							"partyCode" + (liCnt + 1), request);
					logger.info("Address is ::" + lStrPartyAddr + " ac is "
							+ lStrPartyAcc);
					logicVO.setCheqType(lStrCheqType);
					// logicVO.setGroupId(groupId);
					logicVO.setPartyName(lStrPartyName);
					logicVO.setStatus(bundleConst
							.getString("STATUS.CheqWritten"));
					logicVO.setToDt(new java.util.Date(System
							.currentTimeMillis()));
					logicVO.setFinYrId(finYrDAO.getFinYearIdByCurDate());
					logicVO.setAccntNum(lStrPartyAcc);
					logicVO.setPartyAddr(lStrPartyAddr);
					logicVO.setPartyCode(lStrPartyCode);
					logicVoList.add(logicVO);
				}
				logger.info(" Logic VO list " + logicVoList);
				for (int liCnt = 0; liCnt < logicVoList.size(); liCnt++) {
					LogicChequeVO logicVO = (LogicChequeVO) logicVoList
							.get(liCnt);
					ChequeVO chequeVO = new ChequeVO();
					List rltBillCheqList = new ArrayList();
					List tokenList = new ArrayList();
					List cntrlList = new ArrayList();
					chequeVO.setTrnChequeDtls(makeChequeDtlsVO(logicVO,
							request, inputMap));
					rltBillCheqList = makeBillCheque(logicVO, request, inputMap);
					tokenList = getTokenList(request);
					cntrlList = getBillControlList(request);
					chequeVO.setBillChequeRlt(rltBillCheqList);
					chequeVO.setTokenNo(tokenList);
					chequeVO.setBillCntrlNo(cntrlList);
					chequeList.add(chequeVO);
					logger.info(" added in same bill number");
				}
				
				
				
			} else if (lStrFlag.equals("REMOVE")) {
				String[] lStrCheques = request.getParameterValues("chkSelect1");
				for (int i = lStrCheques.length - 1; i >= 0; i--) {
					chequeList.remove(Integer.parseInt(lStrCheques[i]) - 1);
				}
			}
			logger.info(" Cheque lIst is " + chequeList);

			resObj.setSessionValues("ChequeList", chequeList);
			resObj.setResultValue(inputMap);
			resObj.setViewName("chqWriteChq");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in putChequeInSession " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to getting Token For Bill
	 * 
	 * @param  long : billNo, 
	 * 		   HttpServletRequest : request
	 * @return long
	 * @author vidhya
	 */

	private long getTokenForBill(long billNo, HttpServletRequest request) {
		ArrayList billList = (ArrayList) request.getSession().getAttribute(
				"BillList");
		long tokenNo = 0;
		for (int i = 0; i < billList.size(); i++) {
			TrnShowBill showBillVo = (TrnShowBill) billList.get(i);
			if (showBillVo.getBillNo() == billNo) {
				tokenNo = showBillVo.getTokenNum();
				break;
			}
		}
		return tokenNo;
	}
	 /**
	 * Method to write Cheque
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject writeCheque(Map inputMap) {

		logger.info(" Came in side write cheques ");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			ArrayList chequeList = (ArrayList) request.getSession()
					.getAttribute("ChequeList");
			String chkSelect[] = request.getParameterValues("chkSelect1");

			String lStrNumOfCheqs = null;
			String lStrToPost = null;
			ChequeVO chequeVO = null;
			TrnChequeDtls cheqDtls = null;

			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			RltBillChequeDAOImpl billCheDAO = new RltBillChequeDAOImpl(
					RltBillCheque.class, serv.getSessionFactory());

			lStrNumOfCheqs = StringUtility.getParameter("rdoTransReg", request);
			lStrToPost = StringUtility.getParameter("toPost", request);
			logger.info("-------------- in write chequew----------- "
					+ chequeList);

			String groupId = String.valueOf(chequeDAO.getGroupId());

			for (int licnt = 0; licnt < chequeList.size(); licnt++) {
				logger.info("============= in for loop");
				chequeVO = (ChequeVO) chequeList.get(licnt);
				cheqDtls = chequeVO.getTrnChequeDtls();
				logger.info("============= in for loocheqDtlsp" + cheqDtls);
				Long lCheqId = BptmCommonServiceImpl.getNextSeqNum(
						"trn_cheque_dtls", inputMap);
				logger.info(" Cheque Id is" + lCheqId);
				// ResultObject resultObjholiday =
				// serv.executeService("GENERATE_ID_SRVC",inputMap);
				cheqDtls.setChequeId(lCheqId);
				cheqDtls.setGroupId(groupId);
				chequeDAO.create(cheqDtls);
				inputMap.put("ChequeId", lCheqId);

				logger.info(" Inserted Cheque");

				List billCheques = chequeVO.getBillChequeRlt();
				if (billCheques != null) {
					for (int i = 0; i < billCheques.size(); i++) {
						RltBillCheque rltBillCheq = (RltBillCheque) billCheques
								.get(i);
						long billCheqId = BptmCommonServiceImpl.getNextSeqNum(
								"rlt_bill_cheque", inputMap);
						rltBillCheq.setBillChequeId(billCheqId);
						rltBillCheq.setChequeId(lCheqId);
						logger.info(" Setting bill cheque relatin .... "
								+ rltBillCheq.toString());
						billCheDAO.create(rltBillCheq);
						logger.info(" Inserted  Bill Cheque");
						PhyBillDAOImpl phyBill = new PhyBillDAOImpl(
								TrnBillRegister.class, serv.getSessionFactory());
						TrnBillRegister trnBill = phyBill.read(rltBillCheq
								.getBillNo());
						trnBill.setCurrBillStatus(bundleConst
								.getString("STATUS.CheqWritten"));
						phyBill.update(trnBill);
						// Added By Hiral
						// inputMap.put("TrnChequeVo", chequeVO);
						phyBill.updateRptExpDtls(inputMap, rltBillCheq
								.getBillNo(), trnBill);
						// End : Added By Hiral
					}
				}
			}
			// forwarding the bills in createChequeFromWF
/*	Changes for Written Cheques : Hiral		*/			
//			createChequeFromWF(inputMap);
/*	Ends : Changes for Written Cheques : Hiral		*/			
			resObj.setViewName("chqWriteChq");
			inputMap.put("ChequeStatus", "Cheques Written Successfully");
			resObj.setResultValue(inputMap);

			request.getSession().removeAttribute("ChequeList");
			// ut.commit();
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in writeCheque " + e,e);

		}
		return resObj;
	}
	 /**
	 * Method to  make Cheque detail VO
	 * 
	 * @param  LogicChequeVO : logicVO,
	 *		   HttpServletRequest : request, 
	 *		   Map : inputMap
	 * @return TrnChequeDtls vo
	 * @author vidhya
	 */

	public TrnChequeDtls makeChequeDtlsVO(LogicChequeVO logicVO,
			HttpServletRequest request, Map inputMap) // change here
	{
		Long lStrPostId = SessionHelper.getPostId(inputMap);
		Long lStrUserId = SessionHelper.getUserId(request);
		Long lStrDbId = SessionHelper.getDbId(inputMap);

		TrnChequeDtls trnChequeDtls = new TrnChequeDtls();
		try {

			trnChequeDtls.setDdoParty(logicVO.getCheqType());
			trnChequeDtls.setChequeType(logicVO.getChequeType());
			trnChequeDtls.setChequeAmt(logicVO.getChequeAmt());
			trnChequeDtls.setPartyName(logicVO.getPartyName());
			trnChequeDtls.setGroupId(logicVO.getGroupId());
			trnChequeDtls.setCreatedDate(new java.util.Date(System
					.currentTimeMillis()));
			trnChequeDtls.setCreatedPostId(lStrPostId);
			trnChequeDtls.setCreatedUserId(lStrUserId);
			trnChequeDtls.setTrnCounter(new Integer(1));
			trnChequeDtls.setDbId(lStrDbId);
			trnChequeDtls.setFinYearId(new Long(logicVO.getFinYrId()));
			trnChequeDtls.setAccntNum(logicVO.getAccntNum());
			trnChequeDtls.setPartyAddr(logicVO.getPartyAddr());
			trnChequeDtls.setPartyCode(logicVO.getPartyCode());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date fromDate = sdf.parse(logicVO.getFromDt());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(fromDate.getTime());

			gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc
					.getMaximum(gc.DAY_OF_MONTH) - 1);
			gc.add(GregorianCalendar.MONTH, 3);
			Date toDt = gc.getTime();

			trnChequeDtls.setFromDt(fromDate);
			trnChequeDtls.setLocationCode(SessionHelper
					.getLocationCode(inputMap));
			trnChequeDtls
					.setStatus(bundleConst.getString("STATUS.CheqWritten"));
			trnChequeDtls.setStatusDate(new Date(System.currentTimeMillis()));
			trnChequeDtls.setToDt(toDt);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error in makeChequeDtlsVO " + e,e);
		}
		return trnChequeDtls;
	}
	 /**
	 * Method to getting the make Bill Cheque
	 * 
	 * @param  LogicChequeVO : logicVO,
			   HttpServletRequest : request, 
			   Map : inputMap
	 * @return List
	 * @author vidhya
	 */

	public List makeBillCheque(LogicChequeVO logicVO,
			HttpServletRequest request, Map inputMap) {
		Long lStrPostId = SessionHelper.getPostId(inputMap);
		Long lStrUserId = SessionHelper.getUserId(request);
		Long lStrDbId = SessionHelper.getDbId(inputMap);

		ArrayList rltBillCheqList = new ArrayList();
		ArrayList billList = (ArrayList) request.getSession().getAttribute(
				"BillList");
		if (billList != null && billList.size() > 0) {
			for (int i = 0; i < billList.size(); i++) {
				TrnShowBill billVO = (TrnShowBill) billList.get(i);
				RltBillCheque rltBillCheque = new RltBillCheque();
				rltBillCheque.setBillNo(billVO.getBillNo());
				rltBillCheque.setGroupId(logicVO.getGroupId());
				rltBillCheque.setCreatedDate(new java.util.Date(System
						.currentTimeMillis()));
				rltBillCheque.setCreatedPostId(lStrPostId);
				rltBillCheque.setCreatedUserId(lStrUserId);
				rltBillCheque.setDbId(lStrDbId);
				rltBillCheque.setLocationCode(SessionHelper
						.getLocationCode(inputMap));
				rltBillCheqList.add(rltBillCheque);
			}
		}
		return rltBillCheqList;
	}
	 /**
	 * Method to getting Token List
	 * 
	 * @param  HttpServletRequest : request
	 * @return List
	 * @author vidhya
	 */

	public List getTokenList(HttpServletRequest request) {
		ArrayList tokenList = new ArrayList();
		ArrayList billList = (ArrayList) request.getSession().getAttribute(
				"BillList");
		if (billList != null && billList.size() > 0) {
			for (int i = 0; i < billList.size(); i++) {
				TrnShowBill billVO = (TrnShowBill) billList.get(i);
				tokenList.add(billVO.getTokenNum());
			}
		}
		return tokenList;
	}
	 /**
	 * Method to getting Bill Control List
	 * 
	 * @param  HttpServletRequest : request
	 * @return List
	 * @author vidhya
	 */

	public List getBillControlList(HttpServletRequest request) {
		ArrayList billCntrlList = new ArrayList();
		ArrayList billList = (ArrayList) request.getSession().getAttribute(
				"BillList");
		if (billList != null && billList.size() > 0) {
			for (int i = 0; i < billList.size(); i++) {
				TrnShowBill billVO = (TrnShowBill) billList.get(i);
				billCntrlList.add(billVO.getBillCntrlNo());
			}
		}
		return billCntrlList;
	}
	 /**
	 * Method to create Cheque From WF
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject createChequeFromWF(Map inputMap) {
		logger
				.info(" \n************************ in Create Cheque workflow ******* \n ");
		/* Workflow Code starts here */
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			Long lStrPostId = SessionHelper.getPostId(inputMap);
			Long lStrUserId = SessionHelper.getUserId(request);
			Long lStrDbId = SessionHelper.getDbId(inputMap);
			Long lStrLocId = SessionHelper.getLocationId(inputMap);
			Long lStrEmpId = SessionHelper.getEmpId(inputMap);

			List billList = (List) request.getSession()
					.getAttribute("BillList");
			String lStrPost = (String) request.getParameter("toPost");
			logger.info("############ Post id is------------" + lStrPost);
			String[] posts = lStrPost.split("~");
			String lStrToPost = posts[0];
			String lStrToLevel = posts[1];

			for (int i = 0; i < billList.size(); i++) {
				TrnShowBill shwoBill = (TrnShowBill) billList.get(i);
				WorkFlowVO workFlowVO = new WorkFlowVO();
				workFlowVO.setAppMap(inputMap);
				workFlowVO.setCrtEmpId(String.valueOf(lStrEmpId));
				workFlowVO.setCrtPost(String.valueOf(lStrPostId));
				workFlowVO.setFromPost(String.valueOf(lStrPostId));
				workFlowVO.setCrtUsr(String.valueOf(lStrUserId));
				workFlowVO.setConnection(conn);
				OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
				WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(
						workFlowVO);
				// Map resultMap =
				// orgUtil.getHierarchyByPostIDAndDescription("8", "Cheque
				// Preparation");

				/* Workflow Code ends here */
				// logger.info(" \n\n System Map is ------------- " +
				// resultMap.toString());
				// List resultList = (List)resultMap.get("Result");
				// logger.info(" \n\n resultList is ------------- " +
				// resultList);
				// Long lStrHeirRefId = (Long)resultList.get(0);
				// workFlowVO.setHirRefId(lStrHeirRefId);
				WorkFlowUtility wfUtility = new WorkFlowUtility();
				workFlowVO.setActId(Long.valueOf(bundleConst
						.getString("CMN.ForwardActId")));
				BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
						TrnChequeDtls.class, serv.getSessionFactory());
				long lDocId = 0;
				if (lObjCmnSrvcDAOImpl.isPhyBill(shwoBill.getBillNo())) {
					lDocId = Long.parseLong(bundleConst
							.getString("CMN.PhyBillDocId"));
				} else {
					lDocId = Long.parseLong(bundleConst
							.getString("CMN.OnlineBillDocId"));
				}
				workFlowVO.setDocId(lDocId);
				workFlowVO.setJobRefId(String.valueOf(shwoBill.getBillNo()));
				logger.info(" Bill value to be forwarded .. "
						+ shwoBill.getBillNo());
				workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));

				workFlowVO.setDbId(lStrDbId);
				workFlowVO.setHierarchyFlag(1);
				workFlowVO.setToPost(lStrToPost);
				workFlowVO.setToLevelId(Integer.parseInt(lStrToLevel));
				logger.info(" Workflow VO set ====" + workFlowVO);
				TrnBillMvmnt trnBillMvmtn = makeMovementVO(inputMap, shwoBill
						.getBillNo());
				inputMap.put("BillMovementVO", trnBillMvmtn);
				workFlowVO.setAppMap(inputMap);
				wfUtility.invokeWorkFlow(workFlowVO);
			}
			logger.info(" After Forward flow ------- ");
			inputMap.put("MESSAGECODE", (long) 1038);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in createChequeFromWF " + e,e);
		}
		return objRes;
	}
	 /**
	 * Method to getting  User Cheques
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject getUserCheques(Map inputMap) {
		logger.info(" Came in getUserCheques");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			String statFlag = (String) request.getParameter("StatusFlag");
			if (statFlag == null) {
				statFlag = (String) inputMap.get("StatusFlag");
			}
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~````` ");

			String lObjStrSrc[] = new String[2];
			if (request.getParameter("txtSearch") != null
					&& request.getParameter("cmbSearch") != null) {
				logger.info("::: in service SearchTxt is  :::"
						+ request.getParameter("txtSearch") + " ::: "
						+ request.getParameter("cmbSearch"));
				lObjStrSrc[0] = (String) request.getParameter("txtSearch");
				lObjStrSrc[1] = (String) request.getParameter("cmbSearch");
			}

			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			WorkFlowVO workFlowVO = new WorkFlowVO();
			Long lStrPostId = SessionHelper.getPostId(inputMap);
			Long lStrUserId = SessionHelper.getUserId(request);
			Long lStrDbId = SessionHelper.getDbId(inputMap);
			// Long lStrLocId = SessionHelper.getLocationId(inputMap);
			String lStrLocCode = SessionHelper.getLocationCode(inputMap);
			Long lStrEmpId = SessionHelper.getEmpId(inputMap);
			String lStrViewName = "";

			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(String.valueOf(lStrEmpId));
			workFlowVO.setCrtPost(String.valueOf(lStrPostId));
			workFlowVO.setFromPost(String.valueOf(lStrPostId));
			workFlowVO.setCrtUsr(String.valueOf(lStrUserId));
			workFlowVO.setConnection(conn);
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			List postList = new ArrayList();
			postList.add(String.valueOf(lStrPostId));
			List resultLst = orgUtil.getDocList(postList);
			logger.info(" --Return -  " + resultLst);
			String lStrChequeList = "";
			// Following parameter is used for distinguishing factor for Written cheques and Printing chequees screen
			String lStrPage = "";
			for (int i = 0; i < resultLst.size(); i++) {
				WfDocMvmntMstVO mvmntVO = (WfDocMvmntMstVO) resultLst.get(i);
				logger.info(" ====================== " + mvmntVO.getJobRefId());
				if (i < resultLst.size() - 1) {

					lStrChequeList = lStrChequeList + mvmntVO.getJobRefId()
							+ ",";
				} else {
					lStrChequeList = lStrChequeList + mvmntVO.getJobRefId();
				}
			}
			if(request.getParameter("writeFlag") != null)
			{
				lStrPage = "Written";
			}
			else
			{
				lStrPage = "Printing";
			}
			
			logger.info(statFlag + "Cheques found with this user are --  "
					+ lStrChequeList);
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			List returnCheques = null;
			if (lStrChequeList != null && !lStrChequeList.equals("")) {
				returnCheques = chequeDAO.getAllCheques(statFlag,
						lStrChequeList, lObjStrSrc, SessionHelper
								.getLangId(inputMap),lStrPage,lStrUserId);
				logger.info(" \n\n return cheques are --- " + returnCheques);
				inputMap.put("ChequeList", returnCheques);
			}
			/* getting latest cheque number */
			long chequeNum = chequeDAO.getLatestChequeNum(lStrLocCode); // put
																		// Location
																		// Id
																		// here
			inputMap.put("LatestChequeNumber", chequeNum);

			resObj.setResultValue(inputMap);
			if (statFlag.equals(bundleConst.getString("STATUS.CheqWritten"))) {			
				lStrViewName = "chqPrinting";
			} else if (statFlag.equals(bundleConst
					.getString("STATUS.CheqPrinted"))) {
				lStrViewName = "chqPrintedChqs";
			} else if (statFlag.equals(bundleConst
					.getString("STATUS.CheqDispToCnt"))) {
				lStrViewName = "chqPrintedChqs";
			}
//	View set for Written Cheques : Hiral			
			if(request.getParameter("writeFlag")!=null)
			{
				if(request.getParameter("writeFlag").equals("1"))
				{
					logger.info("View set to Written cheques");
					lStrViewName = "chqPrintedChqs";
				}
			}
//   Ends : View Set for Written Cheques : Hiral			
			logger.info("********** view name in cheque ********8 *"
					+ lStrViewName);
			resObj.setViewName(lStrViewName);

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getUserCheque " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to generate Cheque PDF
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject generateChequePDF(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());

			/* For generating PDF */
			logger.info(" Cheques recieved " + request.getParameter("Cheques"));
			String cheque = (String) request.getParameter("Cheques");
			String[] cheques = cheque.split(",");
			ArrayList cheqList = new ArrayList();
			ArrayList tokenList = new ArrayList();
			String tokenStr = "";

			String lStrRenChq = null;
			if (request.getParameter("Chqrename") != null) {
				lStrRenChq = request.getParameter("Chqrename").toString();
			}
			for (int i = 0; i < cheques.length; i++) {
				String tokens[] = cheques[i].split("~");
				Long cheqId = Long.parseLong(tokens[0]);
				TrnChequeDtls trnCheque = chequeDAO.read(cheqId);
				if(lMapPDF != null)
				{
					if(lMapPDF.containsKey(String.valueOf(cheqId)))
					{
						inputMap.put("ChequePrinted", trnCheque);
						//System.out.println("-------- Cheque Id rejectd " + trnCheque);
						break;
					}
					else
					{
						lMapPDF.put(String.valueOf(cheqId), "Exists");
					}
				}
				
				
				if (lStrRenChq != null) {
					trnCheque.setPrintDate(new java.util.Date());
					trnCheque.setPrintUser(SessionHelper.getUserId(request));
					chequeDAO.update(trnCheque);
					// Added By Hiral
					inputMap.put("TrnChequeVo", trnCheque);
					//System.out.println("------------ trnCheque.getChequeNo() inPDF " + trnCheque.getChequeNo());
					updateRptPayDtls(inputMap, trnCheque.getChequeNo());
					// End : Added By Hiral
				}
				cheqList.add(trnCheque);
				List tempList = chequeDAO.getTokenNumberForCheq(cheqId);
				for (int k = 0; k < tempList.size() - 1; k++) {
					tokenStr = tokenStr
							+ String.valueOf((Long) tempList.get(k)) + ",";
				}
				tokenStr = tokenStr
						+ String.valueOf((Long) tempList
								.get(tempList.size() - 1));
				logger.info(" Token for Chque ..........." + tokenStr);
				tokenList.add(tokenStr);
				tokenStr = "";
			}

			ChequePrintUtil printUtil = new ChequePrintUtil();
			String s = printUtil.generatePDF(cheqList, tokenList);

			/* PDF Generated */
			/* Now opening PDF again */
			/*
			 * File file = new File("ChequeReport.pdf"); FileInputStream fis =
			 * null; DataInputStream dis = null; logger.info("---------- " +
			 * file );
			 */
			try {
				byte[] lbBytes = s.getBytes();
				logger.info("--------- Byte -------- " + lbBytes);
				inputMap.put("File", lbBytes);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(" Error in getUserCheque " + e,e);
			}
			resObj.setResultValue(inputMap);
			resObj.setViewName("BillsShowDocument");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in generateChequePDF " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to print Cheques
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject printCheques(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
/*	Changes for Written Cheques : Hiral		*/ 			
			logger.info("Value of statusFlag in request : " +request.getParameter("StatusFlag"));
			String lStrStatus = null;
			if(request.getParameter("StatusFlag")!=null)
			{
				lStrStatus = request.getParameter("StatusFlag");
				logger.info("Value of sTatusFlag in printCheques : " +lStrStatus);
			}
/*	Ends :	Changes for Written Cheques : Hiral  */			
			/* For generating PDF */
			String cheque = (String) request.getParameter("selectCheques");
			String nextCheque = (String) request.getParameter("nextCheque");
			String chqNumbers[] = (String[]) request
					.getParameterValues("chqNumbers");
			List chqList = new ArrayList();
			for (int k = 0; k < chqNumbers.length; k++) {
				if (!(chqNumbers[k].equals("")) && chqNumbers[k].length() > 0) {
					chqList.add(chqNumbers[k]);
				}
			}
			// String chqNumer [] =(String []) chqList.toArray();

			Long userId = (Long) request.getSession().getAttribute("userId");
			logger.info(" Cheques recieved " + userId);
			Long nextCheqNo = Long.parseLong(nextCheque);
			String[] cheques = cheque.split(",");
			ArrayList cheqList = new ArrayList();
			String chequeList[] = new String[cheques.length];
			String groupList[] = new String[cheques.length];
			for (int i = 0; i < cheques.length; i++) {
				String[] parts = cheques[i].split("~");
				chequeList[i] = parts[0];
				groupList[i] = parts[1];
			}
			boolean testFlag = isCheckBillChequeCombination(chequeList,
					groupList, serv.getSessionFactory());
			int totalSelectedCheque = chequeList.length;
			Long startNo = nextCheqNo;
			Long endNo = (startNo + totalSelectedCheque) - 1;
			// String[] result=isChequePresent(startNo,endNo,serv);
			String[] result = isChequePresent(Long.parseLong(chqList.get(0)
					.toString()), Long.parseLong(chqList
					.get(chqList.size() - 1).toString()), serv);
			boolean status = true;
			if (result != null) {
				String strMessage = "Cheque no. ";
				for (int i = 0; i < result.length; i++) {
					strMessage = strMessage + result[i].toString();
					if (i != (result.length - 1)) {
						strMessage = strMessage + ",";
					}
				}
				strMessage = strMessage + " already are present";
				inputMap.put("ChequeCombination", strMessage);
/*	Changes for Written Cheques : Hiral		*/				
				if(lStrStatus!=null && lStrStatus.equals(bundleConst.getString("STATUS.CheqWritten")))
				{
					logger.info("Inside setting writeFlag");
					inputMap.put("writeFlag","1");
				}
/*	Ends : Changes for Written Cheques : Hiral		*/				
				status = false;
			}
			if (testFlag && status) {
				List billList1 = new ArrayList();
				for (int i = 0; i < cheques.length; i++) {
					String[] parts = cheques[i].split("~");
					logger.info("\n\n************** while printing ********* "
							+ cheques[i] + "\n");
					Long cheqId = Long.parseLong(parts[0]);
					String groupId = parts[1];
					TrnChequeDtls trnCheque = chequeDAO.read(cheqId);
					trnCheque.setStatus(bundleConst
							.getString("STATUS.CheqPrinted"));
					trnCheque
							.setStatusDate(new Date(System.currentTimeMillis()));
					trnCheque.setChequeNo(Long.parseLong(chqList.get(i)
							.toString()));
					trnCheque.setPrintDate(new java.util.Date(System
							.currentTimeMillis()));
					trnCheque.setPrintUser(userId);
					chequeDAO.update(trnCheque);
					nextCheqNo = nextCheqNo + 1;

					RltBillChequeDAOImpl billCheqDAO = new RltBillChequeDAOImpl(
							RltBillCheque.class, serv.getSessionFactory());
					List billList = billCheqDAO.getBillFromCheque(cheqId);

					for (int k = 0; k < billList.size(); k++) {
						RltBillCheque rltBill = (RltBillCheque) billList.get(k);
						Long billNo = rltBill.getBillNo();
						boolean chkBill = true;
						for (int l = 0; l < billList1.size(); l++) {
							Long billLong = (Long) billList1.get(l);
							if ((billNo.toString()).equals(billLong.toString())) {
								chkBill = false;
							}
						}
						if (chkBill) {

							logger.info("\n ^^^^^^^^^^6 updating for bill "
									+ billNo + "\n");
							PhyBillDAOImpl phyBill = new PhyBillDAOImpl(
									TrnBillRegister.class, serv
											.getSessionFactory());
							TrnBillRegister trnBill = phyBill.read(billNo);
							trnBill.setCurrBillStatus(bundleConst
									.getString("STATUS.CheqPrinted"));
							phyBill.update(trnBill);

							// Added By Hiral
							// inputMap.put("TrnChequeVo", trnCheque);
							phyBill.updateRptExpDtls(inputMap, billNo, trnBill);
							// End : Added By Hiral

							// inserting into movement for cheque printing
							// option
							BillMovementDAOImpl billMvmntDAO = new BillMovementDAOImpl(
									TrnBillMvmnt.class, serv
											.getSessionFactory());
							TrnBillMvmnt trnBillMvmnt = makeMovementVO(
									inputMap, billNo);
							trnBillMvmnt.setMvmntStatus(bundleConst
									.getString("STATUS.CheqPrinted"));
							trnBillMvmnt.setBillMvmtId(BptmCommonServiceImpl
									.getNextSeqNum("trn_bill_mvmnt", inputMap));
							billMvmntDAO.create(trnBillMvmnt);
							billList1.add(billNo);
						}
					}
					// Added By Hiral
					inputMap.put("TrnChequeVo", trnCheque);
					RptPaymentDtls lObjRptPayDtls = generateRptPaymentVo(inputMap);
					Map lMapTest = lObjRptPayDtls.validateData();

					if (Boolean.parseBoolean(lMapTest.get("flag").toString())) {

						DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
						Map lMapPass = new HashMap();
						lMapPass.put("map", inputMap);
						lMapPass.put("RptPaymentVO", lObjRptPayDtls);
						lObjDssDataSrvc.insertPaymentData(lMapPass);
						lMapPass.remove("RptPaymentVO");
						logger
								.info("Payment Data inserted Succesfully for Iteration : "
										+ i
										+ " Cheque No is : "
										+ trnCheque.getChequeNo());
					}
					// End : Added By Hiral

				}
			} else {
				if (status) {
					inputMap
							.put("ChequeCombination",
									"Cheque and Bill Combination does not match. Please select appropriate values.");
				}
			}
			inputMap.put("StatusFlag", bundleConst
					.getString("STATUS.CheqWritten"));
			resObj = getUserCheques(inputMap);
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");			
			logger.error(" Error in printCheques " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to find Cheque Present 
	 * 
	 * @param  Long startNo,
	 * 		   Long endNo,
	 *		   ServiceLocator serv
	 *		   
	 * @return String[]
	 * @author vidhya
	 */

	public String[] isChequePresent(Long startNo, Long endNo,
			ServiceLocator serv) {
		String[] result = null;
		TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(TrnChequeDtls.class,
				serv.getSessionFactory());
		result = chequeDAO.getChequeStatus(startNo, endNo);
		return result;
	}
	 /**
	 * Method to make Movement VO
	 * 
	 * @param  Map  : inputMap,
	 * 		   Long : BillNo
	 * @return vo
	 * @author vidhya
	 */

	public TrnBillMvmnt makeMovementVO(Map inputMap, Long BillNo) {
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		PhyBillDAOImpl phyBill = new PhyBillDAOImpl(TrnBillRegister.class, serv
				.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		String lStrPost = (String) request.getParameter("toPost");
		TrnBillMvmnt trnBillMvmnt = new TrnBillMvmnt();
		Long lStrPostId = SessionHelper.getPostId(inputMap);
		Long lStrUserId = SessionHelper.getUserId(request);
		Long lStrDbId = SessionHelper.getDbId(inputMap);
		Long lStrLocId = SessionHelper.getLocationId(inputMap);
		String lStrLocCode = SessionHelper.getLocationCode(inputMap);
		Long langId = SessionHelper.getLangId(inputMap);

		trnBillMvmnt.setBillNo(BillNo);
		trnBillMvmnt.setCreatedDate(new java.util.Date(System
				.currentTimeMillis()));
		trnBillMvmnt.setCreatedPostId(lStrPostId);
		trnBillMvmnt.setCreatedUserId(lStrUserId);
		trnBillMvmnt.setDbId(lStrDbId);
		if (lStrPost != null && !lStrPost.equals("")) {
			trnBillMvmnt.setStatusUpdtPostid(Long
					.parseLong(lStrPost.split("~")[0]));
			String userId = lObjCmnSrvcDAOImpl.getUserIdFromPost(lStrPost
					.split("~")[0], langId);
			trnBillMvmnt.setStatusUpdtUserid(Long.parseLong(userId));
			trnBillMvmnt.setReceivingUserId(Long.parseLong(userId));
		} else {
			trnBillMvmnt.setStatusUpdtUserid(SessionHelper.getUserId(request));
			trnBillMvmnt.setStatusUpdtPostid(SessionHelper.getPostId(inputMap));
			trnBillMvmnt.setReceivingUserId(SessionHelper.getUserId(request));
		}
		trnBillMvmnt.setReceivedDate(new java.util.Date(System
				.currentTimeMillis()));
		trnBillMvmnt.setLocationCode(lStrLocCode);
		trnBillMvmnt.setStatusUpdtDate(new java.util.Date(System
				.currentTimeMillis()));
		trnBillMvmnt
				.setMvmntStatus(bundleConst.getString("STATUS.CheqWritten"));
		trnBillMvmnt.setReceivedFlag((short) 1);
		BillMovementDAOImpl movementDAO = new BillMovementDAOImpl(
				TrnBillMvmnt.class, serv.getSessionFactory());
		trnBillMvmnt.setMovemntId(movementDAO.getmaxMovementId(BillNo));
		return trnBillMvmnt;
	}
	 /**
	 * Method to send bills and chq To Counter
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject sendToCounter(Map inputMap) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			String StatusFlag = (String) request.getParameter("StatusFlag");
			String[] bills = null;
			String lStrBillStatus = "";
			String returnBill = request.getParameter("ReturnBill");
			short recFlag = 0;
			boolean combiFlag = false;
			if (StatusFlag != null
					&& (StatusFlag.equals("CUSTCON1") || StatusFlag
							.equals("CUSTCON2"))) {
				String[] bills1 = (String[]) request
						.getParameterValues("chkbox");
				for (int i = 0; i < bills1.length; i++) {
					String[] bills2 = bills1[i].split("~");
					bills1[i] = bills2[0];
				}
				bills = bills1;
/*	Changes for Written Cheques : Hiral		*/				
				if(!(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten"))))
				{
					lStrBillStatus = bundleConst.getString("STATUS.CheqDispToCnt");
				}
				else
				if(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten")))
				{
					lStrBillStatus = bundleConst.getString("STATUS.CheqWritten");
				}
/* Ends : Changes for Written Cheques : Hiral		*/				
				recFlag = 0;
				combiFlag = isCheckBillChkCombi(inputMap);
				logger.info("chq Flag is ::::" + combiFlag);
			} else {
				bills = (String[]) request.getParameterValues("chkbox");				
				String[] bills1 = new String[bills.length];
				ArrayList billArr = new ArrayList();
				String cheques[] = new String[bills.length];
				String groups[] = new String[bills.length];
				for (int k = 0; k < bills.length; k++) {
					String bills2[] = bills[k].split("~");
					cheques[k] = bills2[0];
					groups[k] = bills2[1];
					for (int j = 2; j < bills2.length; j++) {
						billArr.add(bills2[j]);
					}
				}
				combiFlag = isCheckBillChequeCombination(cheques, groups, serv
						.getSessionFactory());
				// removing duplicate entry for bills as in case of C1~G1~B1~B2
				// ,C2~G2~B1~B2
				ArrayList lStrTempBill = new ArrayList();
				lStrTempBill.add((String) billArr.get(0));
				for (int i = 1; i < billArr.size(); i++) {
					int k = 0;
					for (int j = 0; j < lStrTempBill.size(); j++) {
						if (lStrTempBill.get(j).equals(billArr.get(i))) {
							k = 1;
							break;
						}
					}
					if (k == 0) {
						lStrTempBill.add((String) billArr.get(i));
						logger
								.info("\n-------------- Adding in Send to counter ------ "
										+ billArr.get(i));
					}
				}
				logger.info("\n-------- bill list size =--------------"
						+ lStrTempBill.size());
				// converting
				bills = new String[lStrTempBill.size()];
				for (int i = 0; i < lStrTempBill.size(); i++) {
					bills[i] = (String) lStrTempBill.get(i);
				}
/*	Changes for Written Cheques : Hiral		*/				
				if(!(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten"))))
				{
					lStrBillStatus = bundleConst.getString("STATUS.CheqInCustody");
				}
				else
				if(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten")))					
				{
					lStrBillStatus = bundleConst.getString("STATUS.CheqWritten");
				}
/*	Ends : Changes for Written Cheques : Hiral		*/				
				recFlag = 0;
			}

			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			logger.info(" \n\n Bills length  " + bills.length);

			if (returnBill != null && returnBill.equals("Yes")) {
				lStrBillStatus = bundleConst
						.getString("STATUS.BillExceptional");
			}
			if (bills != null && combiFlag) {
				List listBillNumbers = new ArrayList();
				for (int i = 0; i < bills.length; i++) {
					logger
							.info("\n\n--------------in Sending to counter -------"
									+ bills[i] + "\n");
					String[] billArr = bills[i].split("~");
					Long billId;
					if (StatusFlag != null
							&& (StatusFlag.equals("CUSTCON1") || StatusFlag
									.equals("CUSTCON2"))) {
						billId = Long.parseLong(billArr[0]);
					} else {
						billId = Long.parseLong(bills[i]);
					}
					RltBillChequeDAOImpl billCheqDAO = new RltBillChequeDAOImpl(
							RltBillCheque.class, serv.getSessionFactory());
					List cheques = billCheqDAO.getChequesForBill(billId);
					PhyBillDAOImpl phyBill = new PhyBillDAOImpl(
							TrnBillRegister.class, serv.getSessionFactory());
					TrnBillRegister trnBill = phyBill.read(billId);
					trnBill.setCurrBillStatus(lStrBillStatus);
					trnBill.setCurrBillStatusDate(new Date(System
							.currentTimeMillis()));
					phyBill.update(trnBill);

					// Added By Hiral
					// inputMap.put("TrnChequeVo", trnCheque);
					phyBill.updateRptExpDtls(inputMap, billId, trnBill);
					// End : Added By Hiral

					TrnBillMvmnt bilMvmtn = makeMovementVO(inputMap, billId);
					bilMvmtn.setReceivedFlag(recFlag);
					bilMvmtn.setMvmntStatus(lStrBillStatus);
					inputMap.put("BillMovementVO", bilMvmtn);
					logger.info("+++++++++++++++++++ 0 " + cheques.size());
					for (int k = 0; k < cheques.size(); k++) {
						RltBillCheque billCheq = (RltBillCheque) cheques.get(k);
						TrnChequeDtls trnCheque = chequeDAO.read(billCheq
								.getChequeId());
						/*
						 * writing code for changing the state of cheques to
						 * cancelld after writing them or printing them
						 */
						if (returnBill != null && returnBill.equals("Yes")) {
							if (StatusFlag != null
									&& (StatusFlag.equals(bundleConst
											.getString("STATUS.CheqWritten")) || StatusFlag
											.equals(bundleConst
													.getString("STATUS.CheqPrinted")))) {
								trnCheque.setStatus(bundleConst
										.getString("STATUS.CheqCancel"));
							}
						}
						/*
						 * writing code for changing the state of cheques to
						 * cancelld after writing them or printing them
						 */
						else {
							trnCheque.setStatus(lStrBillStatus);
						}
						logger
								.info(":::::::::::::::::"
										+ trnCheque.getStatus());
						trnCheque.setStatusDate(new Date(System
								.currentTimeMillis()));
						chequeDAO.update(trnCheque);
						// Added By Hiral
						if(!(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten"))))
						{
							inputMap.put("TrnChequeVo", trnCheque);
							updateRptPayDtls(inputMap, trnCheque.getChequeNo());
						}
						// End : Added By Hiral

					}
					listBillNumbers.add(billId);
					if (returnBill != null && returnBill.equals("Yes")) {
						inputMap.put("jobId", String.valueOf(billId));
						new PhyBillServiceImpl().returnBillFromWF(inputMap);
					} else {
						forwardToCounter(inputMap, billId);
					}

				}

				if (StatusFlag != null
						&& (StatusFlag.equals("CUSTCON1") || StatusFlag
								.equals("CUSTCON2"))) {
					String lStrPost = (String) request.getParameter("toPost");
					logger.info(" \n############Post id is------------"
							+ lStrPost);
					String[] posts = lStrPost.split("~");
					String lStrToPost = posts[0];
					if (lStrToPost != null && !lStrToPost.equals("")) {
						inputMap.put("message", "Cheques Send To Counter");
						inputMap.put("desc", "Once Chq Fwd intimation is sent");
						inputMap.put("catagory", "cat1");
						inputMap.put("bills", listBillNumbers);
						inputMap.put("toPostId", lStrToPost);
						BptmCommonServicesDAO bptmDAO = new BptmCommonServicesDAOImpl(
								TrnBillRegister.class, serv.getSessionFactory());
						inputMap.put("toEmpId", bptmDAO.getUserIdFromPost(
								lStrToPost, SessionHelper.getLangId(inputMap)));
						sendIntimation(inputMap);
					}
				}

			} else {
/*	Changes for Written Cheques : Hiral		*/				
				if(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten")))
				{
					inputMap.put("writeFlag",1);
					logger.info("Inside cheque written status, invalid billCombination");
				}
/*	Ends : Changes for Written Cheques : Hiral		*/				
				inputMap
						.put("ChequeCombination",
								"Cheque and Bill Combination does not match. Please select appropriate values.");
			}
			inputMap.put("StatusFlag", bundleConst
					.getString("STATUS.CheqPrinted"));
			logger.info(" \n\n Mpa before going inside " + inputMap.toString());
			if (StatusFlag != null
					&& (StatusFlag.equals("CUSTCON1") || StatusFlag
							.equals("CUSTCON2"))) {
				inputMap.put("StatusFlag", StatusFlag);
				inputMap.put("FromCust", "Yes");
				resObj = getCounterBillCheques(inputMap);
			} else {
				resObj = getUserCheques(inputMap);
			}
/*	Changes for Written Cheques : Hiral		*/			
			if(StatusFlag.equals(bundleConst.getString("STATUS.CheqWritten")))
			{
				inputMap.put("writeFlag", "1");				
				resObj.setViewName("chqPrintedChqs");				
			}
			resObj.setResultValue(inputMap);
/* Ends : Changes for Written Cheques : Hiral		*/			
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in sendTOCounter " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to send Intimation
	 * 
	 * @param  Map : inputMap
	 * @return void
	 * @author vidhya
	 */

	public void sendIntimation(Map inputMap) {

		IntimationRule ntfcRule = new IntimationRule();
		logger
				.info(" %%%%%%%%%%%%%%%%%%%% Inside intimation %%%%%%%%%%%%%%%%%%%%");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		String lStrToPost = (String) inputMap.get("toPostId");
		String lStrToEmpId = (String) inputMap.get("toEmpId");

		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		List listBillNumbers = (List) inputMap.get("bills");
		Connection conn = serv.getSessionFactory().getCurrentSession()
				.connection();

		// String [] lObjBillNo = (String [])inputMap.get("bills");

		for (int i = 0; i < listBillNumbers.size(); i++) {
			WfNotificationVO ntfcVO = new WfNotificationVO();
			List ntfcList = new ArrayList();
			ntfcVO.setActivateFlag(true);
			ntfcVO.setNtfCntnt((String) inputMap.get("message"));
			ntfcVO.setNtfCtgry((String) inputMap.get("catagory"));
			ntfcVO.setNtfToPost(lStrToPost);
			ntfcVO.setNtfPrtyCode("normal");
			ntfcVO.setNtfToEmpId(lStrToEmpId);
			logger.info(" Billno is  for intimation ::::::::::::::::::: "
					+ listBillNumbers.get(i).toString());

			ntfcVO.setNtfDesc((String) inputMap.get("desc"));
			ntfcList.add(ntfcVO);

			WorkFlowVO wfvo = new WorkFlowVO();
			wfvo.setFromPost(SessionHelper.getPostId(inputMap).toString());
			wfvo.setCrtUsr(SessionHelper.getUserId(request).toString());
			wfvo.setLangID(SessionHelper.getLangId(inputMap).toString());
			wfvo.setLocID(SessionHelper.getLocationCode(inputMap));
			wfvo.setJobRefId(listBillNumbers.get(i).toString());
			wfvo.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
			wfvo.setAppMap(inputMap);
			wfvo.setConnection(conn);
			try {
				ntfcRule.sendNotification(ntfcList, wfvo);
				ntfcList = null;
			} catch (Exception e) {
				logger.error(" Error in sendIntimation " + e,e);
			}
		}
		return;
	}
	 /**
	 * Method to forward bill To Counter
	 * 
	 * @param    Map inputMap,
	 * 			 Long billId
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject forwardToCounter(Map inputMap, Long billId) {
		logger
				.info(" \n************************ Forwarding to Counter ******* \n ");
		/* Workflow Code starts here */
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			Long lStrPostId = SessionHelper.getPostId(inputMap);
			Long lStrUserId = SessionHelper.getUserId(request);
			Long lStrDbId = SessionHelper.getDbId(inputMap);
			Long lStrLocId = SessionHelper.getLocationId(inputMap);
			Long lStrEmpId = SessionHelper.getEmpId(inputMap);
			List billList = (List) request.getSession()
					.getAttribute("BillList");
			String lStrPost = (String) request.getParameter("toPost");
			logger.info(" \n############Post id is------------" + lStrPost);
			String[] posts = lStrPost.split("~");
			String lStrToPost = posts[0];
			String lStrToLevel = posts[1];

			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(String.valueOf(lStrEmpId));
			workFlowVO.setCrtPost(String.valueOf(lStrPostId));
			workFlowVO.setFromPost(String.valueOf(lStrPostId));
			workFlowVO.setCrtUsr(String.valueOf(lStrUserId));
			workFlowVO.setConnection(conn);
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);
			WorkFlowUtility wfUtility = new WorkFlowUtility();
			workFlowVO.setActId(Long.valueOf(bundleConst
					.getString("CMN.ForwardActId")));
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			long lDocId = 0;
			if (lObjCmnSrvcDAOImpl.isPhyBill(billId)) {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.PhyBillDocId"));
			} else {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.OnlineBillDocId"));
			}
			workFlowVO.setDocId(lDocId);

			// workFlowVO.setDocId(Long.valueOf(bundleConst.getString("CMN.PhyBillDocId")));
			workFlowVO.setJobRefId(String.valueOf(billId));
			logger.info(" Bill value to be forwarded .. " + billId);
			workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
			workFlowVO.setDbId(lStrDbId);
			workFlowVO.setHierarchyFlag(1);
			workFlowVO.setToPost(lStrToPost);
			workFlowVO.setToLevelId(Integer.parseInt(lStrToLevel));
			logger.info(" Workflow VO set ====" + workFlowVO);
			workFlowVO.setAppMap(inputMap);
			wfUtility.invokeWorkFlow(workFlowVO);

			logger.info(" After Forward flow ------- ");
			// inputMap.put("MESSAGECODE",(long)1038);
			objRes.setResultValue(inputMap);
			// objRes.setViewName("ajaxData");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error(" Error in forwardToCounter " + e,e);
		}
		return objRes;
	}
	 /**
	 * Method to getting Counter Bill and Cheques
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject getCounterBillCheques(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			RltBillChequeDAOImpl billCheqDAO = new RltBillChequeDAOImpl(
					RltBillCheque.class, serv.getSessionFactory());
			BillMovementDAOImpl billMovmntDAO = new BillMovementDAOImpl(
					TrnBillMvmnt.class, serv.getSessionFactory());
			Long lStrPostId = SessionHelper.getPostId(inputMap);
			Long lStrUserId = SessionHelper.getUserId(request);
			Long lStrDbId = SessionHelper.getDbId(inputMap);
			Long lStrLocId = SessionHelper.getLocationId(inputMap);
			Long lStrEmpId = SessionHelper.getEmpId(inputMap);
			Long langId = SessionHelper.getLangId(inputMap);
			String fromCust = (String) inputMap.get("FromCust");
			String lStrStatus = (String) request.getParameter("StatusFlag");
			String lStrReceiveFlag = (String) request
					.getParameter("ReceiveFlag");
			String lStrReceive = (String) request.getParameter("Receive");

			String lStrSrc[] = null;

			if (lStrStatus != null && lStrStatus.equals("CUSTCON2")) // SEARCH
																		// COMMING
																		// FROM
																		// CUSTODIAN
			{
				lStrSrc = new String[9];
				for (int i = 0; i < 9; i++) {
					lStrSrc[i] = (String) request.getParameter("srch" + i);
				}
			} else {
				String lStrCmbSrc = null;
				String lStrTxtSrc = null;
				lStrSrc = new String[2];
				lStrCmbSrc = (String) request.getParameter("cmbSearch");
				lStrTxtSrc = (String) request.getParameter("txtSearch");
				if (lStrTxtSrc != null && lStrCmbSrc != null
						&& !lStrTxtSrc.equals("") && !lStrCmbSrc.equals("")) {

					lStrSrc[0] = lStrCmbSrc;
					lStrSrc[1] = lStrTxtSrc;

				}

				logger.info(" Txt Searchis  ::: " + lStrCmbSrc + " :::: "
						+ lStrTxtSrc);
			}

			if (lStrReceive != null && lStrReceive.equals("Yes")) {
				String[] bills = (String[]) request
						.getParameterValues("chkbox");
				if (bills != null) {
					for (int i = 0; i < bills.length; i++) {
						logger.info(" Came of updating......... " + bills[i]);
						String[] result = bills[i].split("~");
						logger.info(" updating for ......... " + result[0]);
						TrnBillMvmnt billMvmnt = billMovmntDAO
								.getMvmntVOByStatus(lStrStatus, lStrUserId,
										result[0]);
						billMvmnt.setReceivedDate(new Date(System
								.currentTimeMillis()));
						billMvmnt.setReceivingUserId(lStrUserId);
						billMvmnt.setReceivedFlag((short) 1);

						// /////////////Send intimation////////////////////////
						List listBillNumbers = new ArrayList();
						listBillNumbers.add(result[0]);
						inputMap.put("message", "Bill Received By "
								+ SessionHelper.getUserName(request));
						inputMap.put("desc",
								"Once User Get Bill intimation is send");
						inputMap.put("catagory", "cat1");
						inputMap.put("bills", listBillNumbers);
						inputMap.put("toPostId", billMvmnt.getCreatedPostId()
								.toString());
						inputMap.put("toEmpId", billMvmnt.getCreatedUserId()
								.toString());
						sendIntimation(inputMap);

						// /////////////Send intimation
						// ends////////////////////////

						billMovmntDAO.update(billMvmnt);
					}
				}
			}

			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(String.valueOf(lStrEmpId));
			workFlowVO.setCrtPost(String.valueOf(lStrPostId));
			workFlowVO.setFromPost(String.valueOf(lStrPostId));
			workFlowVO.setCrtUsr(String.valueOf(lStrUserId));
			workFlowVO.setConnection(conn);
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			List postList = new ArrayList();
			postList.add(String.valueOf(lStrPostId));
			List resultLst = orgUtil.getDocList(postList);
			logger.info(" --Return -  " + resultLst);
			String lStrBillList = "";
			for (int i = 0; i < resultLst.size(); i++) {
				WfDocMvmntMstVO mvmntVO = (WfDocMvmntMstVO) resultLst.get(i);
				logger.info(" ====================== " + mvmntVO.getJobRefId());
				if (i < resultLst.size() - 1) {

					lStrBillList = lStrBillList + mvmntVO.getJobRefId() + ",";
				} else {
					lStrBillList = lStrBillList + mvmntVO.getJobRefId();
				}
			}

			List resultList = null;
			resultList = billCheqDAO.getCounterBillCheques(lStrStatus,
					lStrReceiveFlag, lStrBillList, lStrSrc, langId);
			logger.info(" Bill cheque return ILst is " + resultList.size());
			inputMap.put("BillChequeList", resultList);
			if (lStrReceiveFlag != null && lStrReceiveFlag.equals("1")) {
				inputMap.put("ResultPage", "AcceptCheques");
			}
			if (lStrStatus != null
					&& (lStrStatus.equals("CUSTCON1") || lStrStatus
							.equals("CUSTCON2"))) {
				inputMap.put("StatusFlag", lStrStatus);
				inputMap.put("FromCust", "Yes");
			}
			objRes.setResultValue(inputMap);
			objRes.setViewName("cntrAcptCheques");

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error(" Error in getCounterBillCheques " + e,e);
		}
		return objRes;
	}
	 /**
	 * Method to sending chques info To Book banch
	 * 
	 * @param  Map : inputMap
	 * @return ResultObject
	 * @author vidhya
	 */

	public ResultObject sendToBook(Map inputMap) {
		logger.info(" Send to Book bbracnh........... ");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			String[] bills = (String[]) request.getParameterValues("chkbox");
			String StatusFlag = (String) request.getParameter("StatusFlag");
			logger.info("Status Flag is ::::: " + StatusFlag);
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());

			PhyBillDAOImpl phy = new PhyBillDAOImpl(TrnBillRegister.class, serv
					.getSessionFactory());

			if (bills != null) {
				for (int i = 0; i < bills.length; i++) {
					String result[] = bills[i].split("~");
					Long billId = Long.parseLong(result[0]);
					logger.info(" bill number is " + billId);
					RltBillChequeDAOImpl billCheqDAO = new RltBillChequeDAOImpl(
							RltBillCheque.class, serv.getSessionFactory());
					List cheques = billCheqDAO.getChequesForBill(billId);
					PhyBillDAOImpl phyBill = new PhyBillDAOImpl(
							TrnBillRegister.class, serv.getSessionFactory());
					TrnBillRegister trnBill = phyBill.read(billId);
					if (StatusFlag != null
							&& (StatusFlag.equals("CUSTCON1") || StatusFlag
									.equals("CUSTCON2"))) {
						trnBill.setCurrBillStatus(bundleConst
								.getString("STATUS.CheqInCustody"));
					} else {
						trnBill.setCurrBillStatus(bundleConst
								.getString("STATUS.CheqDispToDDO"));
					}
					trnBill.setCurrBillStatusDate(new Date(System
							.currentTimeMillis()));
					phyBill.update(trnBill);

					// Added By Hiral
					// phyBill.updateRptExpDtls(inputMap, billId, trnBill);
					// End : Added By Hiral

					TrnBillMvmnt bilMvmtn = makeMovementVO(inputMap, billId);

					if (StatusFlag != null
							&& (StatusFlag.equals("CUSTCON1") || StatusFlag
									.equals("CUSTCON2"))) {
						bilMvmtn.setMvmntStatus(bundleConst
								.getString("STATUS.CheqInCustody"));
						bilMvmtn.setReceivedFlag((short) 1);
					} else {
						bilMvmtn.setMvmntStatus(bundleConst
								.getString("STATUS.CheqDispToDDO"));
						bilMvmtn.setReceivedFlag((short) 1);
					}
					inputMap.put("BillMovementVO", bilMvmtn);

					// Add By bhavik For token Delete
					Long BillNum = null;
					BillNum = trnBill.getBillNo();
					logger.info(" Cheque LIst is ::::::::::::::: "
							+ cheques.toString());

					for (int k = 0; k < cheques.size(); k++) {
						RltBillCheque billCheq = (RltBillCheque) cheques.get(k);
						TrnChequeDtls trnCheque = chequeDAO.read(billCheq
								.getChequeId());
						if (StatusFlag != null
								&& (StatusFlag.equals("CUSTCON1") || StatusFlag
										.equals("CUSTCON2"))) {
							trnCheque.setStatus(bundleConst
									.getString("STATUS.CheqInCustody"));
						} else {
							/*
							 * SimpleDateFormat sdf = new
							 * SimpleDateFormat("dd/MM/yyyy"); Date fromDate =
							 * new java.util.Date(); logger.info(" Current date
							 * is ::: "+fromDate.toString()); GregorianCalendar
							 * gc = new GregorianCalendar();
							 * gc.setTimeInMillis(fromDate.getTime());
							 * gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH),
							 * gc.getMaximum(gc.DAY_OF_MONTH)-1);
							 * gc.add(GregorianCalendar.MONTH,3); Date toDt =
							 * gc.getTime(); trnCheque.setFromDt(fromDate);
							 * trnCheque.setToDt(toDt);
							 */
							trnCheque.setStatus(bundleConst
									.getString("STATUS.CheqDispToDDO"));
						}
						logger
								.info(trnCheque.getChequeId()
										+ " -------------- updating for cheque.............. "
										+ trnCheque.getStatus());
						trnCheque.setStatusDate(new Date(System
								.currentTimeMillis()));
						chequeDAO.update(trnCheque);
						// Added By Hiral
						inputMap.put("TrnChequeVo", trnCheque);
						updateRptPayDtls(inputMap, trnCheque.getChequeNo());
						// End : Added By Hiral
					}
					forwardToCounter(inputMap, billId);

					TrnBillRegister billVo = phy.read(BillNum);
					billVo
							.setChequeDispDt(new Date(System
									.currentTimeMillis()));
					billVo.setCurrBillStatus(bundleConst
							.getString("STATUS.VoucherGen"));
					billVo.setCurrBillStatusDate(new Date(System
							.currentTimeMillis()));
					billVo.setChequeStatus("CHEQISSUE");
					phy.update(billVo);

					// Added By Hiral
					// inputMap.put("TrnChequeVo", trnCheque);
					phyBill.updateRptExpDtls(inputMap, BillNum, billVo);
					// End : Added By Hiral

					OrgTokenStatusDAOImpl tokenDAO = new OrgTokenStatusDAOImpl(
							OrgTokenStatus.class, serv.getSessionFactory());
					tokenDAO.updateTokenRedeem(billVo.getTokenNum(),
							SessionHelper.getLocationCode(inputMap),
							SessionHelper.getUserId(request), SessionHelper
									.getPostId(inputMap));

					// inserting into movement for Voucher Generation option
					BillMovementDAOImpl billMvmntDAO = new BillMovementDAOImpl(
							TrnBillMvmnt.class, serv.getSessionFactory());
					TrnBillMvmnt bilMvmnt1 = makeMovementVO(inputMap, billId);
					bilMvmnt1.setMvmntStatus(bundleConst
							.getString("STATUS.VoucherGen"));
					bilMvmnt1.setReceivedFlag((short) 0);
					bilMvmnt1.setBillMvmtId(BptmCommonServiceImpl
							.getNextSeqNum("trn_bill_mvmnt", inputMap));
					billMvmntDAO.create(bilMvmnt1);

				}
			}
			inputMap.put("StatusFlag", bundleConst
					.getString("STATUS.CheqDispToCnt"));
			inputMap.put("ReceiveFlag", "1");
			logger.info(" \n\n Mpa before going inside " + inputMap.toString());
			resObj = getCounterBillCheques(inputMap);
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in sendToBook " + e,e);
		}
		return resObj;
	}
	 /**
	 * Method to getting chq bill combination is true or not
	 * 
	 * @param   String[] : cheques,
			    String[] : groups, 
			    SessionFactory : sessionFactory
	 * @return boolean
	 * @author vidhya
	 */

	public boolean isCheckBillChequeCombination(String[] cheques,
			String[] groups, SessionFactory sessionFactory) {
		TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(TrnChequeDtls.class,
				sessionFactory);
		return chequeDAO.isCheckBillChequeCombination(cheques, groups);
	}

	/**
	 * Method to update "rpt_payment_dtls" table.
	 * 
	 * @param Map :
	 *            inputMap
	 * @param String :
	 *            lStrChqTypeCode
	 * @param Long :
	 *            lLngChqNo
	 * 
	 * @return void
	 * 
	 * @author 203818
	 */
	public void updateRptPayDtls(Map inputMap, Long lLngChqNo) {
		Map lMapPass = new HashMap();
		// String lStrExpTypeCode = BptmCommonServiceImpl.getExpType(inputMap,
		// bundleConst.getString("CMN.Bill"));
		String lStrExpTypeCode = bundleConst.getString("CMN.Bill");

		lMapPass.put("chqTypeCode", lStrExpTypeCode);
		lMapPass.put("chqNo", lLngChqNo);
		lMapPass.put("map", inputMap);

		DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
		RptPaymentDtls lObjRptPayDtlsVo = lObjDssDataSrvc
				.getPaymentData(lMapPass);
		if (lObjRptPayDtlsVo != null) {
			inputMap.put("RptPaymentVO", lObjRptPayDtlsVo);
			lMapPass.put("RptPaymentVO", lObjRptPayDtlsVo);
			lObjRptPayDtlsVo = generateRptPaymentVo(inputMap);

			inputMap.put("RptPaymentVO", lObjRptPayDtlsVo);
			lMapPass.put("RptPaymentVO", lObjRptPayDtlsVo);
			lObjDssDataSrvc.updatePaymentData(lMapPass);
		}
	}
	 /**
	 * Method to generate RptPayment Vo
	 * 
	 * @param  Map : inputMap
	 * @return vo
	 * @author vidhya
	 */

	public RptPaymentDtls generateRptPaymentVo(Map inputMap) {
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		BptmCommonServicesDAOImpl lObjBptmCmnSrvcDao = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());

		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
				SgvcFinYearMst.class, serv.getSessionFactory());
		RptPaymentDtls lObjRptPaymentDtls = new RptPaymentDtls();
		TrnChequeDtls lObjTrnChequeDtls = new TrnChequeDtls();
		String lStrDeptCode = "";
		Long lLngChqNo = null;

		if (inputMap.containsKey("TrnChequeVo")) {
			lObjTrnChequeDtls = (TrnChequeDtls) inputMap.get("TrnChequeVo");
			lLngChqNo = lObjTrnChequeDtls.getChequeNo();
			logger
					.info("Value of Cheque no in generateRptPaymentVo inside if inputMap.containsKey(TrnChequeVo)"
							+ lLngChqNo);
		}

		if (inputMap.containsKey("RptPaymentVO")) {
			lObjRptPaymentDtls = (RptPaymentDtls) inputMap.get("RptPaymentVO");
		}

		Long lLngLocId = SessionHelper.getLocationId(inputMap);

		if (inputMap.containsKey("chqNo")) {
			lLngChqNo = (Long) inputMap.get("chqNo");
			logger
					.info("Value of Cheque no in generateRptPaymentVo inside if inputMap.containsKey(chqNo)"
							+ lLngChqNo);
		}

		logger.info("Value of Cheqe no that is passed in getBillVoFromChqNo : "
				+ lLngChqNo);
		TrnBillRegister lObjTrnBillVo = lObjBptmCmnSrvcDao
				.getBillVoFromChqNo(lLngChqNo);

		if (lObjTrnBillVo != null) {
			logger
					.info("Value of DeptCode from TrnBillVo in generateRptPaymentVo : "
							+ lObjTrnBillVo.getDeptCode());
			logger
					.info("Value of BillNo from TrnBillVo in generateRptPaymentVo : "
							+ lObjTrnBillVo.getBillNo());
			logger
					.info("Value of DDO Code from TrnBillVo in generateRptPaymentVo : "
							+ lObjTrnBillVo.getDdoCode());
			lStrDeptCode = lObjTrnBillVo.getDeptCode();
			if (lStrDeptCode != null)
				lObjRptPaymentDtls.setDeptCode(lStrDeptCode);
			lObjRptPaymentDtls.setTsryCode(lLngLocId.toString());
			lObjRptPaymentDtls.setDdoCode(lObjTrnBillVo.getDdoCode());
		}
		if (lObjTrnChequeDtls != null) {
			logger.info("Value of Party Code : "
					+ lObjTrnChequeDtls.getPartyCode());
			if (lObjTrnChequeDtls.getPartyCode() != null)
				lObjRptPaymentDtls.setPartyCode(lObjTrnChequeDtls
						.getPartyCode());

			// String lStrExpTypeCode =
			// BptmCommonServiceImpl.getExpType(inputMap,
			// bundleConst.getString("CMN.Bill"));
			lObjRptPaymentDtls
					.setChqTypeCode(bundleConst.getString("CMN.Bill"));

			lObjRptPaymentDtls.setChqNo(new java.math.BigDecimal(
					lObjTrnChequeDtls.getChequeNo()));
			lObjRptPaymentDtls.setChqClrDt(lObjTrnChequeDtls.getClearedDt());
			lObjRptPaymentDtls.setFinYrId(new java.math.BigDecimal(
					lObjFinYearDAOImpl.getFinYearIdByCurDate()));
			lObjRptPaymentDtls.setAmount(lObjTrnChequeDtls.getChequeAmt());
			// lObjRptPaymentDtls.setBankCode(bankCode);
			lObjRptPaymentDtls.setChqStatusCode(lObjTrnChequeDtls.getStatus());
			lObjRptPaymentDtls
					.setChqStatusDt(lObjTrnChequeDtls.getStatusDate());
			lObjRptPaymentDtls.setPartyName(lObjTrnChequeDtls.getPartyName());
		}
		if (lLngChqNo != null) {
			lObjRptPaymentDtls.setChqNo(new java.math.BigDecimal(lLngChqNo));
		}

		logger.info("Value of Cheque No in generateRptPaymentVo : "
				+ lObjRptPaymentDtls.getChqNo());

		return lObjRptPaymentDtls;
	}

}
