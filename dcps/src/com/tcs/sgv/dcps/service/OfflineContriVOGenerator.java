/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 21, 2011		Vihan Khatri								
 *******************************************************************************
 */

/**
 * Class Description - 
 *
 *
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0
 * Mar 21, 2011
 */
/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Mar 17, 2011		Vihan Khatri								
 *******************************************************************************
 */
/**
 * Class Description - 
 *
 *
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0
 * Mar 17, 2011
 */
package com.tcs.sgv.dcps.service;

//com.tcs.sgv.dcps.service.DCPSNomineeDtlsVOGenerator
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import au.id.jericho.lib.html.Logger;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.OfflineContriDAO;
import com.tcs.sgv.dcps.dao.OfflineContriDAOImpl;
import com.tcs.sgv.dcps.valueobject.TrnDcpsContribution;

public class OfflineContriVOGenerator extends ServiceImpl implements
		VOGeneratorService {

	/*
	 * @Description : Method to generate VO For DcpsEmpNmnMst.
	 * 
	 * @Input : Map : inputMap
	 * 
	 * @Output : ResultObject : ResultObject
	 */

	@Override
	public ResultObject generateMap(Map inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		inputMap.get("requestObj");

		TrnDcpsContribution[] lArrTrnDcpsContributions = null;

		try {
			lArrTrnDcpsContributions = generateContributionsVOList(inputMap);
			inputMap.put("lArrTrnDcpsContributions", lArrTrnDcpsContributions);
			objRes.setResultValue(inputMap);
		} catch (Exception ex) {
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			ex.printStackTrace();
		}
		return objRes;
	}

	/*
	 * @Description : Method to generate VO For DCPSNomineeDtlsVOGenerator.
	 * 
	 * @Input : Map : inputMap
	 * 
	 * @Output : ResultObject : DcpsEmpNmnMst[]
	 */

	public TrnDcpsContribution[] generateContributionsVOList(Map inputMap)
			throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
	
		ServiceLocator servLoc = (ServiceLocator) inputMap
		.get("serviceLocator");

		Long gLngPostId = SessionHelper.getPostId(inputMap);
		Long gLngUserId = SessionHelper.getUserId(inputMap);
		Long lLngDbId = SessionHelper.getDbId(inputMap);
		Long lLngLocId = SessionHelper.getLocationId(inputMap);
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Date gDtCurrDt = DBUtility.getCurrentDateFromDB();
		
		String lStrUserType = StringUtility.getParameter("User", request).trim();

		String lStrUseType = StringUtility.getParameter("Use", request).trim();

		String lStrTotalRecords = StringUtility.getParameter("hdnCounter",
				request).trim();
		Integer lIntTotalRecords = Integer.parseInt(lStrTotalRecords);

		TrnDcpsContribution[] lArrTrnDcpsContribution = new TrnDcpsContribution[lIntTotalRecords];

		Long lLongContributionId = null;
		String lStrDcpsEmpId = null;
		String lStrTreasuryCode = null;
		String schemeCode = null;
		String lStrDDOCode = null;
		String lStrMonthId = null;
		String lStrYearId = null;
		String lStrDelayedMonthId = null;
		String lStrDelayedYearId = null;
		String lStrBillGroupId = null;
		String lStrTypeOfPayment = null;
		String lStrPayCommission = null;
		String lStrtxtStartDate = null;
		String lStrtxtEndDate = null;
		String lStrBasic = null;
		String lStrDP = null;
		String lStrDA = null;
		String contributionNps=null;
		String lStrContribution = null;
		StringBuffer dcpsContributionIds = new StringBuffer();
		String lStrDeletedContributionIndexes = StringUtility.getParameter(
				"deletedContributionIndexes", request).trim();
		String[] lStrArrDeletedContributionIndexes = null;

		if (!"".equals(lStrDeletedContributionIndexes)) {
			lStrArrDeletedContributionIndexes = lStrDeletedContributionIndexes
					.split("~");
		}
		
		String lStrElementId = StringUtility.getParameter("hidElementId",
				request).trim();
		inputMap.put("elementId", lStrElementId);
		
		String lStrType = StringUtility.getParameter("Type", request).trim();
		inputMap.put("RLType", lStrType);
		
		lStrDelayedMonthId = StringUtility.getParameter("cmbDelayedMonth", request).trim();
		lStrDelayedYearId = StringUtility.getParameter("cmbDelayedYear", request).trim();
		
		String lStrSchemeName = StringUtility.getParameter("txtSchemeName",
				request).trim();
		inputMap.put("schemeName", lStrSchemeName);
		
		lStrTreasuryCode = StringUtility.getParameter("cmbTreasuryCode",
				request).trim();
		inputMap.put("TreasuryCode", lStrTreasuryCode);

		lStrBillGroupId = StringUtility.getParameter("cmbBillGroup",
				request).trim();
		inputMap.put("cmbBillGroup", lStrBillGroupId);

		schemeCode = StringUtility.getParameter("schemeCode", request)
				.trim();
		inputMap.put("schemeCode", schemeCode);
		lStrDDOCode = StringUtility.getParameter("cmbDDOCode", request)
				.trim();
		inputMap.put("cmbDDOCode", lStrDDOCode);

		lStrMonthId = StringUtility.getParameter("cmbMonth", request)
				.trim();
		inputMap.put("cmbMonth", lStrMonthId);
		lStrYearId = StringUtility.getParameter("cmbYear", request).trim();
		inputMap.put("cmbYear", lStrYearId);

		Integer lIntContinueFlag = 0;
		
		TrnDcpsContribution lObjTrnDcpsContribution = null;
		
		OfflineContriDAO lObjOfflineContriDAO = new OfflineContriDAOImpl(TrnDcpsContribution.class, servLoc.getSessionFactory());

		for (Integer lInt = 1; lInt <= lIntTotalRecords; lInt++) {

			// Code added not to consider the deleted contributions.

			lIntContinueFlag = 0;

			if (lStrArrDeletedContributionIndexes != null) {
				for (Integer lIntDelete = 0; lIntDelete < lStrArrDeletedContributionIndexes.length; lIntDelete++) {
					if (Integer
							.parseInt(lStrArrDeletedContributionIndexes[lIntDelete]) == lInt) {
						lIntContinueFlag = 1;
					}
				}

				if (lIntContinueFlag == 1) {
					continue;
				}
			}

			
			lLongContributionId = Long.parseLong(StringUtility.getParameter("checkbox" + lInt, request).trim());
			
			if (lLongContributionId == 0l)
			{
				lObjTrnDcpsContribution = new TrnDcpsContribution();
			}
			else
			{
				lObjTrnDcpsContribution = (TrnDcpsContribution) lObjOfflineContriDAO.read(lLongContributionId);
			}
	
			dcpsContributionIds.append(StringUtility.getParameter(
					"checkbox" + lInt, request).trim());
			dcpsContributionIds.append("~");
			inputMap.put("dcpsContributionIds", dcpsContributionIds);

			lStrDcpsEmpId = StringUtility.getParameter("dcpsempId" + lInt,
					request).trim();

			lStrTypeOfPayment = StringUtility.getParameter(
					"cmbTypeOfPayment" + lInt, request).trim();

			lStrPayCommission = StringUtility.getParameter(
					"cmbPayCommission" + lInt, request).trim();

			if(lStrUserType.equals("DDOAsst") && lStrUseType.equals("ViewAll"))
			{
				if(!"".equals(lStrDelayedMonthId) && !"-1".equals(lStrDelayedMonthId))
				{
					lObjTrnDcpsContribution.setDelayedMonthId(Long.valueOf(lStrDelayedMonthId));
				}
				if(!"".equals(lStrDelayedYearId) && !"-1".equals(lStrDelayedYearId))
				{
					lObjTrnDcpsContribution.setDelayedFinYearId(Long.valueOf(lStrDelayedYearId));
				}
			}
			
			lStrtxtStartDate = StringUtility.getParameter(
					"txtStartDate" + lInt, request);
			lStrtxtEndDate = StringUtility.getParameter("txtEndDate" + lInt,
					request);
			lStrBasic = StringUtility.getParameter("basic" + lInt, request)
					.trim();
			lStrDP = StringUtility.getParameter("DP" + lInt, request).trim();
			lStrDA = StringUtility.getParameter("DA" + lInt, request).trim();
			

			Double lDoubleDP = null;
			if (lStrDP.equals("")) {
				lDoubleDP = 0d;
			} else {
				lDoubleDP = Double.parseDouble(lStrDP);
			}

			Double lDoubleDA = null;
			if (lStrDA.equals("")) {
				lDoubleDA = 0d;
			} else {
				lDoubleDA = Double.parseDouble(lStrDA);
			}

			lStrContribution = StringUtility.getParameter("contribution" + lInt, request).trim();
			Double lDoubleContribution = null;
			if (lStrContribution.equals("")) {
				lDoubleContribution = 0d;
			} else {
				lDoubleContribution = Double.parseDouble(lStrContribution);
			}

			// /*NPS DCPS employee Contribution By naveen Priya Sinha */
			contributionNps = StringUtility.getParameter("contributionNps" + lInt, request).trim();
			Double lDoubleContributionNps = null;
			if (contributionNps.equals("")) {
				lDoubleContributionNps = 0d;
			} else {
				lDoubleContributionNps = Double.parseDouble(contributionNps);
			}			
			/*NPS DCPS employee Contribution By naveen Priya Sinha */
			
			lObjTrnDcpsContribution.setDcpsEmpId(Long.parseLong(lStrDcpsEmpId));
			lObjTrnDcpsContribution.setTreasuryCode(Long
					.parseLong(lStrTreasuryCode));
			lObjTrnDcpsContribution.setDdoCode(lStrDDOCode);
			lObjTrnDcpsContribution.setDcpsDdoBillGroupId(Long
					.parseLong(lStrBillGroupId));
			lObjTrnDcpsContribution.setSchemeCode(schemeCode);
			lObjTrnDcpsContribution.setTypeOfPayment(lStrTypeOfPayment);
			lObjTrnDcpsContribution.setPayCommission(lStrPayCommission);
			lObjTrnDcpsContribution.setFinYearId(Long.parseLong(lStrYearId));
			lObjTrnDcpsContribution.setMonthId(Long.parseLong(lStrMonthId));
			// /

			if (lStrTypeOfPayment.equals("700046")
					|| lStrTypeOfPayment.equals("700047")) {
				lObjTrnDcpsContribution.setBasicPay(Double.parseDouble(lStrBasic));
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			if (lStrTypeOfPayment.equals("700048")) {
				//lObjTrnDcpsContribution.setBasicPay(0d);
				if(!"".equals(lStrBasic))
				{
					lObjTrnDcpsContribution.setBasicPay(Double.parseDouble(lStrBasic));
				}
				else
				{
					lObjTrnDcpsContribution.setBasicPay(0d);
				}
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			if (lStrTypeOfPayment.equals("700049")) {
				//lObjTrnDcpsContribution.setBasicPay(0d);
				if(!"".equals(lStrBasic))
				{
					lObjTrnDcpsContribution.setBasicPay(Double.parseDouble(lStrBasic));
				}
				else
				{
					lObjTrnDcpsContribution.setBasicPay(0d);
				}
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			lObjTrnDcpsContribution.setContribution(lDoubleContribution);
			lObjTrnDcpsContribution.setContributionNps(lDoubleContributionNps);
			lObjTrnDcpsContribution.setStartDate(IFMSCommonServiceImpl
					.getDateFromString(lStrtxtStartDate));
			lObjTrnDcpsContribution.setEndDate(IFMSCommonServiceImpl
					.getDateFromString(lStrtxtEndDate));
			lObjTrnDcpsContribution.setEmployerContriFlag('N');
			lObjTrnDcpsContribution.setLangId(lLngLangId);
			lObjTrnDcpsContribution.setLocId(lLngLocId);
			lObjTrnDcpsContribution.setDbId(lLngDbId);
			lObjTrnDcpsContribution.setCreatedPostId(gLngPostId);
			lObjTrnDcpsContribution.setCreatedUserId(gLngUserId);
			lObjTrnDcpsContribution.setCreatedDate(gDtCurrDt);
			
			lObjTrnDcpsContribution.setRegStatus(0l);

			lArrTrnDcpsContribution[lInt - 1] = lObjTrnDcpsContribution;

		}

		return lArrTrnDcpsContribution;
	}
	

	public ResultObject generateContriVOListSchdlr(Map inputMap) throws Exception
	{
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
		
		
		Map loginMap =(HashMap) inputMap.get("baseLoginMap");
		Long gLngPostId = Long.valueOf((loginMap.get("postId").toString()));
		Long gLngUserId = Long.valueOf((loginMap.get("userId").toString()));
		Long lLngDbId = 99L;
		Long lLngLocId = Long.valueOf((loginMap.get("locationCode").toString()));
		Long lLngLangId = 1L;
		Date gDtCurrDt = new Date();

		String lStrUserType = inputMap.get("User") != null ? inputMap.get("User").toString():"";
		String lStrUseType = inputMap.get("Use") != null ? inputMap.get("Use").toString():"";

		String lStrTotalRecords = inputMap.get("hdnCounter").toString().trim();
		Integer lIntTotalRecords = Integer.parseInt(lStrTotalRecords);
		System.out.println("Total Records"+lIntTotalRecords);

		TrnDcpsContribution[] lArrTrnDcpsContribution = new TrnDcpsContribution[lIntTotalRecords];

		Long lLongContributionId = null;
		String lStrDcpsEmpId = null;
		String lStrTreasuryCode = null;
		String schemeCode = null;
		String lStrDDOCode = null;
		String lStrMonthId = null;
		String lStrYearId = null;
		String lStrDelayedMonthId = null;
		String lStrDelayedYearId = null;
		String lStrBillGroupId = null;
		String lStrTypeOfPayment = null;
		String lStrPayCommission = null;
		String lStrtxtStartDate = null;
		String lStrtxtEndDate = null;
		String lStrBasic = null;
		String lStrDP = null;
		String lStrDA = null;
		String lStrContribution = null;
		StringBuffer dcpsContributionIds = new StringBuffer();
		//String lStrDeletedContributionIndexes = StringUtility.getParameter("deletedContributionIndexes", request).trim();
		String[] lStrArrDeletedContributionIndexes = null;

		/*if (!"".equals(lStrDeletedContributionIndexes))
		{
			lStrArrDeletedContributionIndexes = lStrDeletedContributionIndexes.split("~");
		}*/

		/*String lStrElementId = StringUtility.getParameter("hidElementId", request).trim();
		inputMap.put("elementId", lStrElementId);

		String lStrType = StringUtility.getParameter("Type", request).trim();
		inputMap.put("RLType", lStrType);

		lStrDelayedMonthId = StringUtility.getParameter("cmbDelayedMonth", request).trim();
		lStrDelayedYearId = StringUtility.getParameter("cmbDelayedYear", request).trim();

		String lStrSchemeName = StringUtility.getParameter("txtSchemeName", request).trim();
		inputMap.put("schemeName", lStrSchemeName);

		lStrTreasuryCode = StringUtility.getParameter("cmbTreasuryCode", request).trim();
		inputMap.put("TreasuryCode", lStrTreasuryCode);

		lStrBillGroupId = StringUtility.getParameter("cmbBillGroup", request).trim();
		inputMap.put("cmbBillGroup", lStrBillGroupId);

		schemeCode = StringUtility.getParameter("schemeCode", request).trim();
		inputMap.put("schemeCode", schemeCode);
		lStrDDOCode = StringUtility.getParameter("cmbDDOCode", request).trim();
		inputMap.put("cmbDDOCode", lStrDDOCode);

		lStrMonthId = StringUtility.getParameter("cmbMonth", request).trim();
		inputMap.put("cmbMonth", lStrMonthId);
		lStrYearId = StringUtility.getParameter("cmbYear", request).trim();
		inputMap.put("cmbYear", lStrYearId);
*/
		inputMap.get("Type").toString();
		String lStrType = inputMap.get("Type") != null ? inputMap.get("Type").toString():"";
		inputMap.put("RLType", lStrType);

//		lStrTreasuryCode = StringUtility.getParameter("cmbTreasuryCode", request).trim(); // add in map
		
		DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, servLoc.getSessionFactory());
		
		lStrDDOCode = inputMap.get("cmbDDOCode") != null ? inputMap.get("cmbDDOCode").toString():"";
		inputMap.put("cmbDDOCode", lStrDDOCode);
		
		lStrTreasuryCode = lObjDcpsCommonDAO.getTreasuryCodeForDDO(lStrDDOCode);
		inputMap.put("TreasuryCode", lStrTreasuryCode);

		lStrBillGroupId = inputMap.get("cmbBillGroup") != null ? inputMap.get("cmbBillGroup").toString():"";
		inputMap.put("cmbBillGroup", lStrBillGroupId);

		schemeCode = inputMap.get("schemeCodeForBG") != null ? inputMap.get("schemeCodeForBG").toString():"";
		inputMap.put("schemeCode", schemeCode);

		lStrMonthId = inputMap.get("cmbMonth") != null ? inputMap.get("cmbMonth").toString():"";
		inputMap.put("cmbMonth", lStrMonthId);
		
		lStrYearId = inputMap.get("cmbYear") != null ? inputMap.get("cmbYear").toString():"";
		inputMap.put("cmbYear", lStrYearId);
		
		List empList = (List) (inputMap.get("empList") != null ? inputMap.get("empList"):null);
		
		//System.out.println("Size of EmpList is"+empList.size());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Integer lIntContinueFlag = 0;

		TrnDcpsContribution lObjTrnDcpsContribution = null;

		OfflineContriDAO lObjOfflineContriDAO = new OfflineContriDAOImpl(TrnDcpsContribution.class, servLoc.getSessionFactory());
		

		for (Integer lInt = 1; lInt <= lIntTotalRecords; lInt++)
		{

			// Code added not to consider the deleted contributions.

			lIntContinueFlag = 0;

			if (lStrArrDeletedContributionIndexes != null)
			{
				for (Integer lIntDelete = 0; lIntDelete < lStrArrDeletedContributionIndexes.length; lIntDelete++)
				{
					if (Integer.parseInt(lStrArrDeletedContributionIndexes[lIntDelete]) == lInt)
					{
						lIntContinueFlag = 1;
					}
				}

				if (lIntContinueFlag == 1)
				{
					continue;
				}
			}

			//lLongContributionId = Long.parseLong(StringUtility.getParameter("checkbox" + lInt, request).trim());
			lLongContributionId = 0l;

			if (lLongContributionId == 0l)
			{
				lObjTrnDcpsContribution = new TrnDcpsContribution();
			}
			else
			{
				lObjTrnDcpsContribution = (TrnDcpsContribution) lObjOfflineContriDAO.read(lLongContributionId);
			}

			//dcpsContributionIds.append(StringUtility.getParameter("checkbox" + lInt, request).trim());
			dcpsContributionIds.append("0");
			dcpsContributionIds.append("~");
			inputMap.put("dcpsContributionIds", dcpsContributionIds);

			//lStrDcpsEmpId = StringUtility.getParameter("dcpsempId" + lInt, request).trim();
			Object[] lArrObjectEmpList = (Object[]) empList.get(lInt-1);
			lStrDcpsEmpId =  lArrObjectEmpList[0].toString();

			lStrTypeOfPayment = StringUtility.getParameter("cmbTypeOfPayment" + lInt, request).trim();
			//lStrTypeOfPayment = "700046";

			//lStrPayCommission = StringUtility.getParameter("cmbPayCommission" + lInt, request).trim();
			lStrPayCommission = lArrObjectEmpList[3].toString();

			/*if (lStrUserType.equals("DDOAsst") && lStrUseType.equals("ViewAll"))
			{
				if (!"".equals(lStrDelayedMonthId) && !"-1".equals(lStrDelayedMonthId))
				{
					lObjTrnDcpsContribution.setDelayedMonthId(Long.valueOf(lStrDelayedMonthId));
				}
				if (!"".equals(lStrDelayedYearId) && !"-1".equals(lStrDelayedYearId))
				{
					lObjTrnDcpsContribution.setDelayedFinYearId(Long.valueOf(lStrDelayedYearId));
				}
			}*/

			lStrtxtStartDate = inputMap.get("schdlStartDate") != null ? inputMap.get("schdlStartDate").toString():null;
			lStrtxtEndDate =inputMap.get("schdlEndDate") != null ? inputMap.get("schdlEndDate").toString():null; 
				
			lStrBasic = lArrObjectEmpList[4].toString();
			lStrDP = lArrObjectEmpList[17].toString();
			lStrDA = lArrObjectEmpList[18].toString();
			Double lDoubleDP = null;
			if (lStrDP.equals(""))
			{
				lDoubleDP = 0d;
			}
			else
			{
				lDoubleDP = Double.parseDouble(lStrDP);
			}

			Double lDoubleDA = null;
			if (lStrDA.equals(""))
			{
				lDoubleDA = 0d;
			}
			else
			{
				lDoubleDA = Double.parseDouble(lStrDA);
			}

			lStrContribution = lArrObjectEmpList[19].toString();
			Double lDoubleContribution = null;
			if (lStrContribution.equals(""))
			{
				lDoubleContribution = 0d;
			}
			else
			{
				lDoubleContribution = Double.parseDouble(lStrContribution);
			}

			lObjTrnDcpsContribution.setDcpsEmpId(Long.parseLong(lStrDcpsEmpId));
			lObjTrnDcpsContribution.setTreasuryCode(Long.parseLong(lStrTreasuryCode));
			lObjTrnDcpsContribution.setDdoCode(lStrDDOCode);
			lObjTrnDcpsContribution.setDcpsDdoBillGroupId(Long.parseLong(lStrBillGroupId));
			lObjTrnDcpsContribution.setSchemeCode(schemeCode);
			lObjTrnDcpsContribution.setTypeOfPayment(lStrTypeOfPayment);
			lObjTrnDcpsContribution.setPayCommission(lStrPayCommission);
			lObjTrnDcpsContribution.setFinYearId(Long.parseLong(lStrYearId));
			lObjTrnDcpsContribution.setMonthId(Long.parseLong(lStrMonthId));
			// /

			if (lStrTypeOfPayment.equals("700046") || lStrTypeOfPayment.equals("700047"))
			{
				lObjTrnDcpsContribution.setBasicPay(Double.parseDouble(lStrBasic));
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			if (lStrTypeOfPayment.equals("700048"))
			{
				lObjTrnDcpsContribution.setBasicPay(0d);
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			if (lStrTypeOfPayment.equals("700049"))
			{
				lObjTrnDcpsContribution.setBasicPay(0d);
				lObjTrnDcpsContribution.setDP(lDoubleDP);
				lObjTrnDcpsContribution.setDA(lDoubleDA);
			}
			lObjTrnDcpsContribution.setContribution(lDoubleContribution);
			lObjTrnDcpsContribution.setStartDate(sdf.parse(lStrtxtStartDate));
			lObjTrnDcpsContribution.setEndDate(sdf.parse(lStrtxtEndDate));
			lObjTrnDcpsContribution.setEmployerContriFlag('N');
			lObjTrnDcpsContribution.setLangId(lLngLangId);
			lObjTrnDcpsContribution.setLocId(lLngLocId);
			lObjTrnDcpsContribution.setDbId(lLngDbId);
			lObjTrnDcpsContribution.setCreatedPostId(gLngPostId);
			lObjTrnDcpsContribution.setCreatedUserId(gLngUserId);
			lObjTrnDcpsContribution.setCreatedDate(gDtCurrDt);

			lArrTrnDcpsContribution[lInt - 1] = lObjTrnDcpsContribution;

		}

		
		inputMap.put("lArrTrnDcpsContributions", lArrTrnDcpsContribution);
		objRes.setResultValue(inputMap);
		return objRes;
		//return 
	}
	
}
