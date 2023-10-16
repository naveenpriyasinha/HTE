package com.tcs.sgv.common.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.cheque.dao.RltBillChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.dao.TrnChequeDAOImpl;
import com.tcs.sgv.billproc.cheque.valueobject.RltBillCheque;
import com.tcs.sgv.billproc.cheque.valueobject.TrnChequeDtls;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.dao.VoucherDAOImpl;
import com.tcs.sgv.exprcpt.service.ReceiptServiceImpl;
import com.tcs.sgv.exprcpt.valueobject.TrnVoucherDetails;

/**
 * com.tcs.sgv.common.service.AdviceServiceImpl
 * 
 * Date of Creation : 10th July 2007 Author : Vidhya Mashru
 * 
 * Revision History ===================== Vidhya M 23-Oct-2007 For making
 * changes for code formating
 */
public class AdviceServiceImpl extends ServiceImpl implements AdviceService {
	Log logger = LogFactory.getLog(getClass());
	/**
	 * 	This method is used to generate advice and voucher
	 * @param Map: inputMap
	 * @return  ResultObject
	 * 
	 */
	public ResultObject getAdivceVoucher(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			VoucherDAOImpl voucherDAO = new VoucherDAOImpl(
					TrnVoucherDetails.class, serv.getSessionFactory());
			TrnChequeDAOImpl chequeDAO = new TrnChequeDAOImpl(
					TrnChequeDtls.class, serv.getSessionFactory());
			RltBillChequeDAOImpl billCheqDAO = new RltBillChequeDAOImpl(
					RltBillCheque.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());

			String voucher = "";
			String advice = "";
			Long lStrPostId = SessionHelper.getPostId(inputMap);
			Long lStrUserId = SessionHelper.getUserId(request);
			Long lStrDbId = SessionHelper.getDbId(inputMap);
			Long lStrLocId = SessionHelper.getLocationId(inputMap);
			Long lStrEmpId = SessionHelper.getEmpId(inputMap);
			Long langId = SessionHelper.getLangId(inputMap);
			CmnLocationMstDaoImpl locDAO = new CmnLocationMstDaoImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			long longAdviceNum = chequeDAO.getMaxAdviceNo(SessionHelper
					.getLocationCode(inputMap));
			String billList = (String) request.getParameter("chkbox");
			// String[] bills = (String[])request.getParameterValues("chkbox");
			ArrayList adviceList = new ArrayList();
			ArrayList voucherList = new ArrayList();
			logger.info(" Bill List is  :::: " + billList);
			String bills[] = billList.split(",");
			ArrayList chequeArr1 = new ArrayList();
			if (bills != null) {
				logger.info(" Bils No  is :::: " + bills);
				for (int cnt = 0; cnt < bills.length; cnt++) {
					String[] result = bills[cnt].split("~");

					TrnVoucherDetails trnVoucherDtls = null;
					CmnLocationMst locVO = locDAO.read(lStrLocId);
					if (locVO.getLocName().equals("PAO")) {
						trnVoucherDtls = voucherDAO.getNextVoucherMonthWise(
								result[0], SessionHelper
										.getLocationCode(inputMap));
					} else {
						trnVoucherDtls = voucherDAO.getNextVoucherMjrHdWise(
								result[0], SessionHelper
										.getLocationCode(inputMap));
					}

					trnVoucherDtls.setBillNo(Long.parseLong(result[0]));
					trnVoucherDtls.setCreatedDate(new java.util.Date(System
							.currentTimeMillis()));
					trnVoucherDtls.setCreatedPostId(lStrPostId);
					trnVoucherDtls.setCreatedUserId(lStrUserId);
					trnVoucherDtls.setDbId(lStrDbId);
					trnVoucherDtls.setLocationCode(SessionHelper
							.getLocationCode(inputMap));
					trnVoucherDtls.setDistributed((short) 0);
					trnVoucherDtls.setUpdatedDate(new java.util.Date(System
							.currentTimeMillis()));
					long voucherId = BptmCommonServiceImpl.getNextSeqNum(
							"trn_voucher_details", inputMap);
					trnVoucherDtls.setVoucherDetailId(voucherId);

					voucher = voucher + trnVoucherDtls.getVoucherNo() + ",";
					PhyBillDAOImpl phyDAO = new PhyBillDAOImpl(
							TrnBillRegister.class, serv.getSessionFactory());
					String billCntrl = lObjCmnSrvcDAOImpl
							.getBillCntrlNoFromBillNo(result[0]);

					CommonVO comVO = new CommonVO();
					comVO.setCommonId(billCntrl);
					comVO.setCommonDesc(String.valueOf(trnVoucherDtls
							.getVoucherNo()));
					/* added for TC Bill case for Expenditure accountin -- Start */
					if (voucherDAO.isValidVoucher(result[0])) {
						TrnBillRegister trnBilReg = phyDAO.read(Long
								.parseLong(result[0]));
						// String billCategory =
						// lObjCmnSrvcDAOImpl.getLookUpText(trnBilReg.getTcBill().intValue(),
						// SessionHelper.getLangId(inputMap));
						String billCategory = trnBilReg.getTcBill();
						if (billCategory != null && billCategory.equals("TC")) {
							TrnReceiptDetails lObjTrnRcptDtls = new TrnReceiptDetails();
							List lListTrnRcptDtls = lObjCmnSrvcDAOImpl
									.getTrnRcptFromBill(trnBilReg.getBillNo(),
											inputMap);
							logger
									.info("List for getting trnReceiptDetailsVO in AdviceSErviceImpl : "
											+ lListTrnRcptDtls);
							for (int i = 0; i < lListTrnRcptDtls.size(); i++) {
								lObjTrnRcptDtls = (TrnReceiptDetails) lListTrnRcptDtls
										.get(i);
								logger
										.info("Value of TrnReceiptDetailId in AdviceServiceIml "
												+ i
												+ " : "
												+ lObjTrnRcptDtls
														.getReceiptDetailId());
								inputMap.put("receiptDetailId", lObjTrnRcptDtls
										.getReceiptDetailId());
								new ReceiptServiceImpl()
										.createTCChallanDoc(inputMap);
							}

						}
						/*
						 * added for TC Bill case for Expenditure accountin -
						 * End
						 */
						voucherDAO.insertIntoVoucher(trnVoucherDtls);
					} else {
						logger.info(" Voucher already created");
						comVO.setCommonDesc("Voucher already generated");
					}
					voucherList.add(comVO);
					trnVoucherDtls = null;
					TrnBillRegister billVo = phyDAO.read(Long
							.parseLong(result[0]));
					// String lookUpText =
					// lObjCmnSrvcDAOImpl.getLookUpText(billVo.getTcBill().intValue(),langId);
					String lookUpText = billVo.getTcBill();

					if (lookUpText != null && lookUpText.equals("Regular")) {

						int totalSize = result.length;
						logger.info(" Total size is  " + totalSize);
						// String[] chequeArr1 = new String[totalSize-3];

						int j = 0;
						for (int i = 3; i < totalSize; i++) {
							logger.info(" result[i]  " + result[i]);
							chequeArr1.add(result[i]);
							j++;
						}
					}
				}
				if (chequeArr1 != null && chequeArr1.size() > 0) {
					ArrayList lArrCheque = new ArrayList();
					lArrCheque.add(chequeArr1.get(0));

					for (int i = 0; i < chequeArr1.size(); i++) {
						int k = 0;
						for (int j = 0; j < lArrCheque.size(); j++) {
							if (lArrCheque.get(j).equals(chequeArr1.get(i))) {
								k = 1;
								break;
							}
						}
						if (k == 0) {
							lArrCheque.add(chequeArr1.get(i));
						}
					}
					Object[] chequeArr = (Object[]) lArrCheque.toArray();
					for (int i = 0; i < chequeArr.length; i++) {
						logger
								.info("................. chequeq "
										+ chequeArr[i]);

						TrnChequeDtls trnCheque = chequeDAO.read(Long
								.parseLong(chequeArr[i].toString()));

						trnCheque.setAdviceDt(new java.util.Date(System
								.currentTimeMillis()));
						CommonVO comVO = new CommonVO();
						comVO.setCommonId(String.valueOf(trnCheque
								.getChequeNo()));
						comVO.setCommonDesc(String.valueOf(longAdviceNum));
						SimpleDateFormat sdf1 = new SimpleDateFormat(
								"yyyy-MM-dd");
						sdf1.applyPattern("dd/MM/yyyy");
						String txtChqDate = sdf1.format(trnCheque.getFromDt());
						comVO.setFrmDate(txtChqDate);
						if (chequeDAO.isValidateAdvice(chequeArr[i].toString())) {
							trnCheque.setAdviceNo(longAdviceNum);
							chequeDAO.update(trnCheque);
							longAdviceNum++;
						} else {
							comVO.setCommonDesc("Advice already generated");
						}
						adviceList.add(comVO);
					}
				}
			}

			inputMap.put("AdviceList", adviceList);
			inputMap.put("VoucherList", voucherList);
			// inputMap.put("MESSAGECODE", (long)1040);
			objRes.setResultValue(inputMap);
			objRes.setViewName("adviceVoucher");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in generating advice and voucher " +e,e);
		}

		return objRes;
	}
}
