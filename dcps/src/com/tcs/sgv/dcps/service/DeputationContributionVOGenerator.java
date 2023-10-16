/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 19, 2011		Bhargav Trivedi								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.TrnDcpsDeputationContribution;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 May 20, 2011
 */
public class DeputationContributionVOGenerator extends ServiceImpl {
	
	Log gLogger = LogFactory.getLog(getClass());

	public ResultObject generateMap(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		TrnDcpsDeputationContribution[] lArrTrnDcpsDeputationContribution = null;

		try {

			lArrTrnDcpsDeputationContribution = generateVOList(inputMap);
			inputMap.put("lArrTrnDcpsDeputationContribution",
					lArrTrnDcpsDeputationContribution);

			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			gLogger.info("Error is  " + e);
		}

		return objRes;
	}

	public TrnDcpsDeputationContribution[] generateVOList(Map inputMap) {

		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		Long gLngPostId = SessionHelper.getPostId(inputMap);
		Long gLngUserId = SessionHelper.getUserId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLocId = SessionHelper.getLocationId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Date gDtCurrDt = DBUtility.getCurrentDateFromDB();
		
		TrnDcpsDeputationContribution[] lArrTrnDcpsDeputationContribution = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TrnDcpsDeputationContribution lObjTrnDcpsDeputationContribution = null;

		try {
			
			String lStrDummyOfficeId = StringUtility.getParameter(
					"dummyOfficeId", request);
			Long lLongMonthId = Long.valueOf(StringUtility.getParameter(
					"monthId", request));
			Long lLongYearId = Long.valueOf(StringUtility.getParameter(
					"yearId", request));

			String lStrdcpsEmpIds = StringUtility.getParameter("dcpsEmpIds",
					request);
			String[] lStrArrdcpsEmpIds = lStrdcpsEmpIds.split("~");
			Long[] lLongArrdcpsEmpIds = new Long[lStrArrdcpsEmpIds.length];
			for (Integer lInt = 0; lInt < lStrArrdcpsEmpIds.length; lInt++) {
				lLongArrdcpsEmpIds[lInt] = Long
						.valueOf(lStrArrdcpsEmpIds[lInt]);
			}

			String lStrContributions = StringUtility.getParameter(
					"contributions", request);
			String[] lStrArrContributions = lStrContributions.split("~");
			Double[] lDoubleArrContributions = new Double[lStrArrContributions.length];
			for (Integer lInt = 0; lInt < lStrArrContributions.length; lInt++) {
				lDoubleArrContributions[lInt] = Double
						.valueOf(lStrArrContributions[lInt]);
			}

			String lStrChallanNos = StringUtility.getParameter("challanNos",
					request);
			String[] lStrArrChallanNos = lStrChallanNos.split("~");

			String lStrChallanDates = StringUtility.getParameter(
					"challanDates", request);
			String[] lStrArrChallanDates = lStrChallanDates.split("~");
			Date[] lDateArrChallanDates = new Date[lStrArrChallanDates.length];
			for (Integer lInt = 0; lInt < lStrArrChallanDates.length; lInt++) {
				if (lStrArrChallanDates[lInt] != null
						&& !"".equals(lStrArrChallanDates[lInt].trim())) {
					lDateArrChallanDates[lInt] = sdf
							.parse(lStrArrChallanDates[lInt]);
				}
			}
			
			String lStrContributionsEmplr = StringUtility.getParameter(
					"contributionsEmplr", request);
			String[] lStrArrContributionsEmplr = lStrContributionsEmplr.split("~");
			Double[] lDoubleArrContributionsEmplr = new Double[lStrArrContributionsEmplr.length];
			for (Integer lInt = 0; lInt < lStrArrContributionsEmplr.length; lInt++) {
				lDoubleArrContributionsEmplr[lInt] = Double
						.valueOf(lStrArrContributionsEmplr[lInt]);
			}
			
			String lStrChallanNosEmplr = StringUtility.getParameter("challanNosEmplr",
					request);
			String[] lStrArrChallanNosEmplr = lStrChallanNosEmplr.split("~");

			String lStrChallanDatesEmplr = StringUtility.getParameter(
					"challanDatesEmplr", request);
			String[] lStrArrChallanDatesEmplr = lStrChallanDatesEmplr.split("~");
			Date[] lDateArrChallanDatesEmplr = new Date[lStrArrChallanDatesEmplr.length];
			for (Integer lInt = 0; lInt < lStrArrChallanDatesEmplr.length; lInt++) {
				if (lStrArrChallanDatesEmplr[lInt] != null
						&& !"".equals(lStrArrChallanDatesEmplr[lInt].trim())) {
					lDateArrChallanDatesEmplr[lInt] = sdf
							.parse(lStrArrChallanDatesEmplr[lInt]);
				}
			}

			lArrTrnDcpsDeputationContribution = new TrnDcpsDeputationContribution[lLongArrdcpsEmpIds.length];

			for (Integer lInt = 0; lInt < lLongArrdcpsEmpIds.length; lInt++) {

				lObjTrnDcpsDeputationContribution = new TrnDcpsDeputationContribution();

				lObjTrnDcpsDeputationContribution
						.setDummyOfficeId(lStrDummyOfficeId);
				lObjTrnDcpsDeputationContribution.setMonthId(lLongMonthId);
				lObjTrnDcpsDeputationContribution.setFinYearId(lLongYearId);

				lObjTrnDcpsDeputationContribution.setDcpsEmpId(lLongArrdcpsEmpIds[lInt]);
				lObjTrnDcpsDeputationContribution.setContribution(lDoubleArrContributions[lInt]);
				lObjTrnDcpsDeputationContribution.setChallanNo(lStrArrChallanNos[lInt]);
				lObjTrnDcpsDeputationContribution.setChallanDate(lDateArrChallanDates[lInt]);
				
				lObjTrnDcpsDeputationContribution.setcontributionEmplr(lDoubleArrContributionsEmplr[lInt]);
				lObjTrnDcpsDeputationContribution.setChallanNoEmplr(lStrArrChallanNosEmplr[lInt]);
				lObjTrnDcpsDeputationContribution.setChallanDateEmplr(lDateArrChallanDatesEmplr[lInt]);
				
				lObjTrnDcpsDeputationContribution.setLangId(lLngLangId);
				lObjTrnDcpsDeputationContribution.setLocId(lLngLocId);
				lObjTrnDcpsDeputationContribution.setDbId(lLngDbId);
				lObjTrnDcpsDeputationContribution.setCreatedPostId(gLngPostId);
				lObjTrnDcpsDeputationContribution.setCreatedUserId(gLngUserId);
				lObjTrnDcpsDeputationContribution.setCreatedDate(gDtCurrDt);

				lArrTrnDcpsDeputationContribution[lInt] = lObjTrnDcpsDeputationContribution;

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return lArrTrnDcpsDeputationContribution;
	}
}
