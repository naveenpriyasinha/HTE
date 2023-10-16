/**
 * 
 */
package com.tcs.sgv.billproc.counter.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;

/** TrnRcptDtlsVOGenerator
 *  This class generates VO for TrnReceiptDetails based on the values provided by end-user.
 *  This VO is then used to insert the data in 'trn_receipt_details' table
 *   
 * 	Date of Creation : 6th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *   Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class TrnRcptDtlsVOGenerator extends ServiceImpl implements
		VOGeneratorService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to generate VO for 'trn_receipt_details' table.
	 * 
	 * @param Map :
	 *            p_objServiceArgs
	 * 
	 * @return ResultObject
	 */
	public ResultObject generateMap(Map p_objServiceArgs) {
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);

		try {
			HttpServletRequest request = (HttpServletRequest) p_objServiceArgs
					.get("requestObj");
			Integer lIntRowCount = StringUtility.getParameter("rowCount",
					request) != null ? Integer.parseInt(StringUtility
					.getParameter("rowCount", request)) : null;

			ServiceLocator serv = (ServiceLocator) p_objServiceArgs
					.get("serviceLocator");
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());

			Long DbId = Long.parseLong(SessionHelper.getDbId(p_objServiceArgs)
					.toString());
			String lStrLocCode = SessionHelper
					.getLocationCode(p_objServiceArgs);
			Long userId = Long.parseLong(SessionHelper.getUserId(request)
					.toString());
			Long postId = Long.parseLong(SessionHelper.getPostId(
					p_objServiceArgs).toString());

			Integer finYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();
			String lStrRcptType = bundleConst.getString("CMN.Others");

			String tcName = (String) request.getParameter("cmbTCCtgry");
			List lListChallan = new ArrayList();
			if (tcName.equalsIgnoreCase("TC")) {
				for (int i = 0; i < lIntRowCount; i++) {
					TrnReceiptDetails trnRcptDtlsVO = new TrnReceiptDetails();
					String rcptNo = request.getParameter("txtChallanNo"
							+ (i + 1)) != null ? request
							.getParameter("txtChallanNo" + (i + 1)) : null;
					BigDecimal lBDAmount = (StringUtility.getParameter(
							"txtChallanAmt" + (i + 1), request).length() > 0) ? new java.math.BigDecimal(
							StringUtility.getParameter("txtChallanAmt"
									+ (i + 1), request))
							: null;
					String challanDate = (StringUtility.getParameter(
							"txtChallanDate" + (i + 1), request).length() > 0) ? StringUtility
							.getParameter("txtChallanDate" + (i + 1), request)
							: null;
					String majorHead = (StringUtility.getParameter(
							"txtChallanMjrHead" + (i + 1), request).length() > 0) ? StringUtility
							.getParameter("txtChallanMjrHead" + (i + 1),
									request)
							: null;

					logger.info("Challan Number " + i + " : " + rcptNo);
					logger.info("Challan Amount " + i + " : " + lBDAmount);
					logger.info("Challan Date " + i + " : " + challanDate);
					logger.info("Challan Major Head " + i + " : " + majorHead);

					Long createdUserHead = SessionHelper.getUserId(request);
					Long createdPostHead = SessionHelper
							.getPostId(p_objServiceArgs);
					java.util.Date lDtCurDate = new java.util.Date();
					trnRcptDtlsVO.setReceiptNo(rcptNo);

					trnRcptDtlsVO.setAmount(lBDAmount);
					if (challanDate != null) {
						trnRcptDtlsVO.setReceiptDate(new SimpleDateFormat(
								"dd/MM/yyyy").parse(challanDate));
					}

					if (majorHead != null) {
						trnRcptDtlsVO.setMajorHead(majorHead);
					}

					trnRcptDtlsVO.setReceiptType(lStrRcptType);
					trnRcptDtlsVO.setCreatedUserId(createdUserHead);
					trnRcptDtlsVO.setCreatedPostId(createdPostHead);
					trnRcptDtlsVO.setCreatedDate(lDtCurDate);
					trnRcptDtlsVO.setUpdatedUserId(userId);
					trnRcptDtlsVO.setUpdatedPostId(postId);
					trnRcptDtlsVO.setUpdatedDate(lDtCurDate);
					trnRcptDtlsVO.setDbId(DbId);
					trnRcptDtlsVO.setLocationCode(lStrLocCode);
					trnRcptDtlsVO.setFinYearId(Long.parseLong(finYearId
							.toString()));
					trnRcptDtlsVO.setTrnCounter(new Integer(1));
					trnRcptDtlsVO.setTc(new Integer(1));
					lListChallan.add(trnRcptDtlsVO);				
				}
			}
			p_objServiceArgs.put("ReceiptDetailsVO", lListChallan);
			retObj.setResultValue(p_objServiceArgs);
		} catch (Exception e) {
			retObj.setResultValue(null);
			retObj.setThrowable(e);
			retObj.setResultCode(ErrorConstants.ERROR);
			retObj.setViewName("errorPage");
			logger.error("Error in generateMap for TrnReceiptDetails : " +e,e);
		}
		return retObj;
	}
}
