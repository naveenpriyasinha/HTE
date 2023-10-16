package com.tcs.sgv.billproc.counter.service;

import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;

/** BdgtHeadDtlsVOGeneratorImpl
 *  This class generates VO for TrnBillBudheadDtls based on the values provided by end-user.
 *  This VO is then used to insert the data in 'trn_bill_budhead_dtls' table
 *   
 * 	Date of Creation : 6th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *   Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class BdgtHeadDtlsVOGeneratorImpl extends ServiceImpl implements
		VOGeneratorService {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle lObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to generate VO for 'trn_budhead_dtls' table.
	 * 
	 * @param :
	 *            Map - p_objServiceArgs
	 * @return : ResultObject
	 * @Author : 203818
	 */
	public ResultObject generateMap(Map p_objServiceArgs) {
		ResultObject retObj = new ResultObject();

		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs
				.get("requestObj");
		TrnBillBudheadDtls budHeadVo = new TrnBillBudheadDtls();
		ServiceLocator serv = (ServiceLocator) p_objServiceArgs
				.get("serviceLocator");
		FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
				SgvcFinYearMst.class, serv.getSessionFactory());

		Long lLngPostId = Long.parseLong(SessionHelper.getPostId(
				p_objServiceArgs).toString());
		Long lLngUserId = Long.parseLong(SessionHelper.getUserId(request)
				.toString());
		String lStrLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
		Long lLngDbId = Long.parseLong(SessionHelper.getDbId(p_objServiceArgs)
				.toString());
		Integer finYearId = lObjFinYearDAOImpl.getFinYearIdByCurDate();
		try {
			System.out
					.println("Value of Detail Head in Budget Head VO Generator : "
							+ StringUtility.getParameter("cmbDetailHead",
									request));

			String lStrDemandNo = (StringUtility.getParameter("cmbDemand",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbDemand", request) : null;
			String lStrMjrHead = (StringUtility.getParameter("cmbMajorHead",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbMajorHead", request) : null;
			String lStrSubMjrHead = (StringUtility.getParameter(
					"cmbSubMajorHead", request).length() > 0) ? StringUtility
					.getParameter("cmbSubMajorHead", request) : null;
			String lStrMinorHead = (StringUtility.getParameter("cmbMinorHead",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbMinorHead", request) : null;
			String lStrSubHead = (StringUtility.getParameter("cmbSubHead",
					request).length() > 0) ? StringUtility.getParameter(
					"cmbSubHead", request) : null;
			String lStrDetailHead = (StringUtility.getParameter(
					"cmbDetailHead", request).length() > 0) ? StringUtility
					.getParameter("cmbDetailHead", request) : null;
			String lStrBudType = (StringUtility
					.getParameter("cmbPlan", request).length() > 0) ? StringUtility
					.getParameter("cmbPlan", request)
					: null;
			String lStrSchemeNo = (StringUtility.getParameter("txtSchemeCode",
					request).length() > 0) ? StringUtility.getParameter(
					"txtSchemeCode", request) : null;

			//System.out.println("Value of lStrDetailHead : " + lStrDetailHead);

			budHeadVo.setBudMjrHd(lStrMjrHead);
			budHeadVo.setDmndNo(lStrDemandNo);
			budHeadVo.setBudSubmjrHd(lStrSubMjrHead);
			budHeadVo.setBudMinHd(lStrMinorHead);
			budHeadVo.setBudSubHd(lStrSubHead);
			budHeadVo.setBudDtlHd(lStrDetailHead);
			budHeadVo.setBudType(lStrBudType);
			budHeadVo.setSchemeNo(lStrSchemeNo);

			budHeadVo.setVersionId(Long.parseLong(lObjRsrcBndle
					.getString("CMN.One")));
			budHeadVo.setCreatedUserId(lLngUserId);
			budHeadVo.setCreatedPostId(lLngPostId);
			budHeadVo.setCreatedDate(new Date(System.currentTimeMillis()));
			budHeadVo.setUpdatedUserId(lLngUserId);
			budHeadVo.setUpdatedPostId(lLngPostId);
			budHeadVo.setUpdatedDate(new Date(System.currentTimeMillis()));
			budHeadVo.setLocationCode(lStrLocCode);
			budHeadVo.setDbId(lLngDbId);
			budHeadVo.setFinYearId(finYearId.toString());
			budHeadVo.setTrnCounter(new Integer(1));

			p_objServiceArgs.put("BudgetHeadVO", budHeadVo);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			retObj.setResultValue(p_objServiceArgs);
		} catch (Exception e) {
			retObj.setResultValue(null);
			retObj.setThrowable(e);
			retObj.setResultCode(ErrorConstants.ERROR);
			retObj.setViewName("errorPage");
			logger.error(" Error in generateMap " + e, e);
		}
		return retObj;
	}
}
