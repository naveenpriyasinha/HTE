package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrPayCompRuleCustomVO;
import com.tcs.sgv.payroll.util.PayrollConstants;

/**
 * This class consists of methods to add, modify or delete payroll components
 * 
 * @class name : PayComponentMasterVOGeneratorImpl
 * @author : Ravysh Tiwari
 * @version : 1
 */

public class PayComponentMasterVOGeneratorImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	public PayComponentMasterVOGeneratorImpl() {

	}

	
	
	public ResultObject viewPayComponent(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->viewPayComponent method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen view Pay Component Error in PayComponentMasterVOGeneratorImpl--->viewPayComponent", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	
	public ResultObject addPayComponent(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->addPayComponent method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			String editFlag = request.getParameter("editFlag") != null ? StringUtility.getParameter("editFlag",request): "";
			objectArgs.put("editFlag",editFlag);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen add or update Pay Component Error in PayComponentMasterVOGeneratorImpl--->addPayComponent", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject submitPayComponent(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->submitPayComponent method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String payComponentShortName = request.getParameter("payComponentShortName") != null ? StringUtility.getParameter("payComponentShortName",request): "";
			objectArgs.put("payComponentShortName",payComponentShortName.trim());
			
			String payComponentShortNameSecLang = request.getParameter("payComponentShortName_sec_lang") != null ? StringUtility.getParameter("payComponentShortName_sec_lang",request): "";
			objectArgs.put("payComponentShortNameSecLang",payComponentShortNameSecLang.trim());
			
			String payComponentName = request.getParameter("payComponentName") != null ? StringUtility.getParameter("payComponentName",request): "";
			objectArgs.put("payComponentName",payComponentName.trim());
			
			String payComponentNameSecLang = request.getParameter("payComponentName_sec_lang") != null ? StringUtility.getParameter("payComponentName_sec_lang",request): "";
			objectArgs.put("payComponentNameSecLang",payComponentNameSecLang.trim());
			
			int componentType = Integer.parseInt(request.getParameter("rdoComponentType") != null ? StringUtility.getParameter("rdoComponentType",request): "0");
			objectArgs.put("componentType",componentType);
			
			String[] payComponentCommissionList =  StringUtility.getParameterValues("payComponentPayCommissionCmb",request);
			
			objectArgs.put("payComponentCommissionList",payComponentCommissionList);
			
			int otherAllowRecovPartFlag = Integer.parseInt(request.getParameter("rdoOtherAllowRecovPart") != null ? StringUtility.getParameter("rdoOtherAllowRecovPart",request): "0");
			objectArgs.put("otherAllowRecovPartFlag",otherAllowRecovPartFlag);
			
			String componentHead = request.getParameter("payComponentDetailHead") != null ? StringUtility.getParameter("payComponentDetailHead",request): "";
			objectArgs.put("componentHead",componentHead);
			
			int displayOrder = Integer.parseInt(request.getParameter("payComponentDisplayOrder") != null ? StringUtility.getParameter("payComponentDisplayOrder",request): "0");
			objectArgs.put("displayOrder",displayOrder);
			
			int displaySubOrder = Integer.parseInt(request.getParameter("payComponentDisplaySubOrder") != null ? StringUtility.getParameter("payComponentDisplaySubOrder",request): "0");
			objectArgs.put("displaySubOrder",displaySubOrder);
			
			int changeReportOrder = Integer.parseInt(request.getParameter("rdoChangeColumnOrder") != null ? StringUtility.getParameter("rdoChangeColumnOrder",request): "0");
			objectArgs.put("changeReportOrder",changeReportOrder);
			
			int mergeColumnFlag = Integer.parseInt(request.getParameter("rdoMergeColumn") != null ? StringUtility.getParameter("rdoMergeColumn",request): "0");
			objectArgs.put("mergeColumnFlag",mergeColumnFlag);
			
			int taxExemptedFlag = Integer.parseInt(request.getParameter("rdoComponentTaxExempted") != null ? StringUtility.getParameter("rdoComponentTaxExempted",request): "0");
			objectArgs.put("taxExemptedFlag",taxExemptedFlag);
			
			int taxExemptionSection = Integer.parseInt(((request.getParameter("payComponentTaxExemptedSection") != null)&&(!StringUtility.getParameter("payComponentTaxExemptedSection",request).equals("")) ) ? StringUtility.getParameter("payComponentTaxExemptedSection",request): "-1");
			objectArgs.put("taxExemptionSection",taxExemptionSection);
			
			int ruleFlag = Integer.parseInt(request.getParameter("rdoComponentRuleBased") != null ? StringUtility.getParameter("rdoComponentRuleBased",request): "1");
			objectArgs.put("ruleFlag",ruleFlag);
			
			int deductionType = Integer.parseInt(request.getParameter("rdoDeductionType") != null ? StringUtility.getParameter("rdoDeductionType",request): "-1");
			objectArgs.put("deductionType",deductionType);
			
			String startDate = request.getParameter("startDate") != null ? StringUtility.getParameter("startDate",request): "";
			objectArgs.put("startDate",startDate);
			
			int componentStatus = Integer.parseInt(request.getParameter("rdoComponentStatus") != null ? StringUtility.getParameter("rdoComponentStatus",request): "1");
			objectArgs.put("componentStatus",componentStatus);
			
			String editFlag = request.getParameter("editFlag") != null ? StringUtility.getParameter("editFlag",request): "";
			objectArgs.put("editFlag",editFlag);
			
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);

			int rdoComponentUsedInFormula = Integer.parseInt(request.getParameter("rdoComponentUsedInFormula") != null ? StringUtility.getParameter("rdoComponentUsedInFormula",request): "0");
			objectArgs.put("rdoComponentUsedInFormula",rdoComponentUsedInFormula);
			
			
			logger.info("PayComponentMasterVOGeneratorImpl--->submitPayComponent method completed successfully");
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Submitting Pay Component Error in PayComponentMasterVOGeneratorImpl--->submitPayComponent", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject retrievePayCompRuleList(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->retrievePayCompRuleList method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->retrievePayCompRuleList", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject addPayComponentRule(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->addPayComponent method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			String editFlag = request.getParameter("editFlag") != null ? StringUtility.getParameter("editFlag",request): "";
			objectArgs.put("editFlag",editFlag);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->addPayComponent", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject getOfficeFromDept(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->getOfficeFromDept method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String deptCode = request.getParameter("ruleDepartmentCmb") != null ? StringUtility.getParameter("ruleDepartmentCmb",request): "0";
			objectArgs.put("deptCode",deptCode);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->getOfficeFromDept", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject getDesgnFromGrade(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->getDesgnFromGrade method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String gradeCode = request.getParameter("ruleClassCmb") != null ? StringUtility.getParameter("ruleClassCmb",request): "0";
			objectArgs.put("gradeCode",gradeCode);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->getDesgnFromGrade", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
 }
	public ResultObject getScaleFromCommission(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->getScaleFromCommission method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			int comsnId = Integer.parseInt(request.getParameter("rulePayCommissionCmb") != null ? StringUtility.getParameter("rulePayCommissionCmb",request): "0");
			int payrollComsnId = comsnId;
			
			if(PayrollConstants.PAYROLL_INTEGRATION_FLAG==1){
				if(comsnId==5)
					comsnId=PayrollConstants.PAYROLL_MP_FIFTHCOMMMISSION_LOOKUPID;
				else if(comsnId==6)
					comsnId=PayrollConstants.PAYROLL_MP_SIXTHCOMMMISSION_LOOKUPID;
			}
			int reqPayComponentType = Integer.parseInt(request.getParameter("reqPayComponentType") != null ? StringUtility.getParameter("reqPayComponentType",request): "0");
			int payComponentUsedInFormula = Integer.parseInt(request.getParameter("payComponentUsedInFormula") != null ? StringUtility.getParameter("payComponentUsedInFormula",request): "1");
			
		   	objectArgs.put("reqPayComponentType",reqPayComponentType);
			objectArgs.put("payrollComsnId",payrollComsnId);
			objectArgs.put("payComponentUsedInFormula",payComponentUsedInFormula);
			
			objectArgs.put("payCommissionId",comsnId);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->getScaleFromCommission", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject compareTosavedRules(Map<String, Object> objectArgs) {
		
		logger.info("PayComponentMasterVOGeneratorImpl--->compareTosavedRules method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

	try{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		HrPayCompRuleCustomVO payCompCustomVO =  new HrPayCompRuleCustomVO();
		
		payCompCustomVO.setDeptCode(StringUtility.getParameter("ruleDepartmentCmb", request)!=null && !(StringUtility.getParameter("ruleDepartmentCmb", request).equals("")) ? StringUtility.getParameter("ruleDepartmentCmb", request):"-1");
		payCompCustomVO.setLocationCode(StringUtility.getParameter("ruleOfficeCmb", request)!=null && !(StringUtility.getParameter("ruleOfficeCmb", request).equals("")) ? StringUtility.getParameter("ruleOfficeCmb", request):"-1");
		payCompCustomVO.setClassCode(StringUtility.getParameter("ruleClassCmb", request)!=null && !(StringUtility.getParameter("ruleClassCmb", request).equals("")) ? StringUtility.getParameter("ruleClassCmb", request):"-1");
		//payCompCustomVO.setEmpCatgryCode(StringUtility.getParameter("ruleEmpCategoryCmb", request)!=null && !(StringUtility.getParameter("ruleEmpCategoryCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("ruleEmpCategoryCmb", request)):-1);
		payCompCustomVO.setGender(StringUtility.getParameter("ruleGenderCmb", request)!=null && !(StringUtility.getParameter("ruleGenderCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("ruleGenderCmb", request)):-1);
		payCompCustomVO.setDesgnCode(StringUtility.getParameter("ruleDesgnCmb", request)!=null && !(StringUtility.getParameter("ruleDesgnCmb", request).equals("")) ? StringUtility.getParameter("ruleDesgnCmb", request):"-1");
		payCompCustomVO.setPostType(StringUtility.getParameter("rulePostTypeCmb", request)!=null && !(StringUtility.getParameter("rulePostTypeCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rulePostTypeCmb", request)):-1);
		
		
		payCompCustomVO.setPhyChallengedFlag(StringUtility.getParameter("rdoPhyChallengedValue", request)!=null && !(StringUtility.getParameter("rdoPhyChallengedValue", request).equals("0")) ? Integer.parseInt(StringUtility.getParameter("rdoPhyChallengedValue", request)):-1);

		payCompCustomVO.setScaleLowLmt(StringUtility.getParameter("ruleScaleFromCmb", request)!=null && !(StringUtility.getParameter("ruleScaleFromCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleScaleFromCmb", request)):-1);
		payCompCustomVO.setScaleUprLmt(StringUtility.getParameter("ruleScaleToCmb", request)!=null && !(StringUtility.getParameter("ruleScaleToCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleScaleToCmb", request)):-1);
		
		payCompCustomVO.setBasicLowLmt(StringUtility.getParameter("ruleBasicPayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleBasicPayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleBasicPayLowerLimit", request)):"0");
		payCompCustomVO.setBasicUprLmt(StringUtility.getParameter("ruleBasicPayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleBasicPayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleBasicPayUpperLimit", request)):"-1");
		
		payCompCustomVO.setGrossLowLmt(StringUtility.getParameter("ruleGrossPayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleGrossPayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleGrossPayLowerLimit", request)):"0");
		payCompCustomVO.setGrossUprLmt(StringUtility.getParameter("ruleGrossPayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleGrossPayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleGrossPayUpperLimit", request)):"-1");
			
		
		payCompCustomVO.setGpLowLmt(StringUtility.getParameter("ruleGradePayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleGradePayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleGradePayLowerLimit", request)):"0");
		payCompCustomVO.setGpUprLmt(StringUtility.getParameter("ruleGradePayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleGradePayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleGradePayUpperLimit", request)):"-1");
		
		payCompCustomVO.setCityType(StringUtility.getParameter("ruleCityTypeCmb", request)!=null && !(StringUtility.getParameter("ruleCityTypeCmb", request).equals("")) ? (StringUtility.getParameter("ruleCityTypeCmb", request)):"-1");
		payCompCustomVO.setQuarterType(StringUtility.getParameter("ruleQuarterTypeCmb", request)!=null && !(StringUtility.getParameter("ruleQuarterTypeCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleQuarterTypeCmb", request)):-1);
		
		payCompCustomVO.setDojYear(StringUtility.getParameter("dojYear", request)!=null && !(StringUtility.getParameter("dojYear", request).equals("")) ? StringUtility.getParameter("dojYear", request):"-1");
		//System.out.println("value i am getting is "+StringUtility.getParameter("dojYear", request)!=null && !(StringUtility.getParameter("dojYear", request).equals("")) ? StringUtility.getParameter("dojYear", request):"-1");
		payCompCustomVO.setPayCommission(StringUtility.getParameter("rulePayCommissionCmb", request)!=null && !(StringUtility.getParameter("rulePayCommissionCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rulePayCommissionCmb", request)):-1);

		
		long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
		
	   	objectArgs.put("payCompCustomVO", payCompCustomVO);
	   	//objectArgs.put("totalParamCount", paramCount);
		objectArgs.put("reqPayComponentCode",reqPayComponentCode);

	   
	   	resObj.setResultValue(objectArgs);
	   	resObj.setResultCode(ErrorConstants.SUCCESS);
		
	}
	catch(Exception ex){
		logger.error("Error is: "+ ex.getMessage());
		resObj.setThrowable(ex);
		logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->compareTosavedRules", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}

	return resObj;
	}
	public ResultObject addMultipleRuleData(Map<String, Object> objectArgs) {
		
		logger.info("PayComponentMasterVOGeneratorImpl--->addMultipleRuleData method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	
	try{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		HrPayCompRuleCustomVO payCompCustomVO =  new HrPayCompRuleCustomVO();
		
		payCompCustomVO.setDeptCode(StringUtility.getParameter("ruleDepartmentCmb", request)!=null && !(StringUtility.getParameter("ruleDepartmentCmb", request).equals("")) ? StringUtility.getParameter("ruleDepartmentCmb", request):"-1");
		payCompCustomVO.setLocationCode(StringUtility.getParameter("ruleOfficeCmb", request)!=null && !(StringUtility.getParameter("ruleOfficeCmb", request).equals("")) ? StringUtility.getParameter("ruleOfficeCmb", request):"-1");
		payCompCustomVO.setClassCode(StringUtility.getParameter("ruleClassCmb", request)!=null && !(StringUtility.getParameter("ruleClassCmb", request).equals("")) ? StringUtility.getParameter("ruleClassCmb", request):"-1");
		//payCompCustomVO.setEmpCatgryCode(StringUtility.getParameter("ruleEmpCategoryCmb", request)!=null && !(StringUtility.getParameter("ruleEmpCategoryCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("ruleEmpCategoryCmb", request)):-1);
		payCompCustomVO.setGender(StringUtility.getParameter("ruleGenderCmb", request)!=null && !(StringUtility.getParameter("ruleGenderCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("ruleGenderCmb", request)):-1);
		payCompCustomVO.setDesgnCode(StringUtility.getParameter("ruleDesgnCmb", request)!=null && !(StringUtility.getParameter("ruleDesgnCmb", request).equals("")) ? StringUtility.getParameter("ruleDesgnCmb", request):"-1");
		payCompCustomVO.setPostType(StringUtility.getParameter("rulePostTypeCmb", request)!=null && !(StringUtility.getParameter("rulePostTypeCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rulePostTypeCmb", request)):-1);
		
		
		payCompCustomVO.setPhyChallengedFlag(StringUtility.getParameter("rdoPhyChallengedValue", request)!=null && !(StringUtility.getParameter("rdoPhyChallengedValue", request).equals("0")) ? Integer.parseInt(StringUtility.getParameter("rdoPhyChallengedValue", request)):-1);

		payCompCustomVO.setScaleLowLmt(StringUtility.getParameter("ruleScaleFromCmb", request)!=null && !(StringUtility.getParameter("ruleScaleFromCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleScaleFromCmb", request)):-1);
		payCompCustomVO.setScaleUprLmt(StringUtility.getParameter("ruleScaleToCmb", request)!=null && !(StringUtility.getParameter("ruleScaleToCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleScaleToCmb", request)):-1);
		
		payCompCustomVO.setBasicLowLmt(StringUtility.getParameter("ruleBasicPayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleBasicPayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleBasicPayLowerLimit", request)):"-1");
		payCompCustomVO.setBasicUprLmt(StringUtility.getParameter("ruleBasicPayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleBasicPayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleBasicPayUpperLimit", request)):"-1");
		
		payCompCustomVO.setGrossLowLmt(StringUtility.getParameter("ruleGrossPayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleGrossPayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleGrossPayLowerLimit", request)):"-1");
		payCompCustomVO.setGrossUprLmt(StringUtility.getParameter("ruleGrossPayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleGrossPayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleGrossPayUpperLimit", request)):"-1");
		
		
		payCompCustomVO.setGpLowLmt(StringUtility.getParameter("ruleGradePayLowerLimit", request)!=null && !(StringUtility.getParameter("ruleGradePayLowerLimit", request).equals("")) ? (StringUtility.getParameter("ruleGradePayLowerLimit", request)):"-1");
		payCompCustomVO.setGpUprLmt(StringUtility.getParameter("ruleGradePayUpperLimit", request)!=null && !(StringUtility.getParameter("ruleGradePayUpperLimit", request).equals("")) ? (StringUtility.getParameter("ruleGradePayUpperLimit", request)):"-1");
		
		payCompCustomVO.setCityType(StringUtility.getParameter("ruleCityTypeCmb", request)!=null && !(StringUtility.getParameter("ruleCityTypeCmb", request).equals("")) ? (StringUtility.getParameter("ruleCityTypeCmb", request)):"-1");
		payCompCustomVO.setQuarterType(StringUtility.getParameter("ruleQuarterTypeCmb", request)!=null && !(StringUtility.getParameter("ruleQuarterTypeCmb", request).equals("")) ? Long.parseLong(StringUtility.getParameter("ruleQuarterTypeCmb", request)):-1);
		
		payCompCustomVO.setStatus(StringUtility.getParameter("rdoRuleStatus", request)!=null && !(StringUtility.getParameter("rdoRuleStatus", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rdoRuleStatus", request)):-1);
		payCompCustomVO.setPayCommission(StringUtility.getParameter("rulePayCommissionCmb", request)!=null && !(StringUtility.getParameter("rulePayCommissionCmb", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rulePayCommissionCmb", request)):-1);
		
		payCompCustomVO.setRuleFormula(StringUtility.getParameter("ruleFormulaStore", request)!=null && !(StringUtility.getParameter("ruleFormulaStore", request).equals("")) ? StringUtility.getParameter("ruleFormulaStore", request):"-1");
		
		payCompCustomVO.setRuleAmount(StringUtility.getParameter("ruleAmount", request)!=null && !(StringUtility.getParameter("ruleAmount", request).equals("")) ? (StringUtility.getParameter("ruleAmount", request)):"-1");
		payCompCustomVO.setRulePercentage(StringUtility.getParameter("rulePercentage", request)!=null && !(StringUtility.getParameter("rulePercentage", request).equals("")) ? (StringUtility.getParameter("rulePercentage", request)):"-1");
		
		payCompCustomVO.setReturnType(StringUtility.getParameter("rdoRuleFixedAmount", request)!=null && !(StringUtility.getParameter("rdoRuleFixedAmount", request).equals("")) ? Integer.parseInt(StringUtility.getParameter("rdoRuleFixedAmount", request)):-1);
		
		payCompCustomVO.setRuleFormulaDisplay(StringUtility.getParameter("ruleFormulaDisplay", request)!=null && !(StringUtility.getParameter("ruleFormulaDisplay", request).equals("")) ? StringUtility.getParameter("ruleFormulaDisplay", request):"-1");
		payCompCustomVO.setDojYear(StringUtility.getParameter("dojYear", request)!=null && !(StringUtility.getParameter("dojYear", request).equals("")) ? StringUtility.getParameter("dojYear", request):"-1");
		
		//create XML file 
		logger.info("PayComponentMasterVOGeneratorImpl---->addMultipleRuleData voToXml");
		
		String xmlFileId = FileUtility.voToXmlFileByXStream(payCompCustomVO);
       	objectArgs.put("ajaxKey", xmlFileId);
       	logger.info("VO successfully created into XML..");
       	logger.info("XML file PATH:::"+xmlFileId);

       	resObj.setViewName("ajaxData");
       	resObj.setResultValue(objectArgs);
       	resObj.setResultCode(ErrorConstants.SUCCESS);
		
	}
	catch(Exception ex){
		logger.error("Error is: "+ ex.getMessage());
		resObj.setThrowable(ex);
		logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->addMultipleRuleData", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	
	return resObj;
	}
	public ResultObject insertPayComponentRuleDetails(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->insertPayComponentRuleDetails method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String encXML[] = StringUtility.getParameterValues("encXML",request);
			long reqPayComponentCode= StringUtility.getParameter("reqPayComponentCode", request)!=null 
			&& !(StringUtility.getParameter("reqPayComponentCode", request).equals("")) ?
					Integer.parseInt(StringUtility.getParameter("reqPayComponentCode", request)):-1;
			
			objectArgs.put("encXML",encXML);
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->insertPayComponentRuleDetails", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject updatePayComponentRule(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->updatePayComponentRule method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqPayCompRuleGrpId = request.getParameter("reqPayCompRuleGrpId") != null ? StringUtility.getParameter("reqPayCompRuleGrpId",request): "0";
			
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			
			objectArgs.put("reqPayCompRuleGrpId",reqPayCompRuleGrpId);
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->updatePayComponentRule", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject updatePayComponentRuleValue(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->updatePayComponentRuleValue method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqPayCompRuleGrpId = request.getParameter("reqPayCompRuleGrpId") != null ? StringUtility.getParameter("reqPayCompRuleGrpId",request): "0";
			String reqPayCompReturnType = request.getParameter("reqPayCompReturnType") != null ? StringUtility.getParameter("reqPayCompReturnType",request): "0";
			String rulePercentage = request.getParameter("rulePercentage") != null ? StringUtility.getParameter("rulePercentage",request): "0";
			String ruleAmount = request.getParameter("ruleAmount") != null ? StringUtility.getParameter("ruleAmount",request): "0";
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			String ruleStatus = StringUtility.getParameter("rdoRuleStatus", request)!=null && !(StringUtility.getParameter("rdoRuleStatus", request).equals("")) ? (StringUtility.getParameter("rdoRuleStatus", request)):"-1";
			
			objectArgs.put("reqPayCompRuleGrpId",reqPayCompRuleGrpId);
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			objectArgs.put("reqPayCompReturnType",reqPayCompReturnType);
			objectArgs.put("rulePercentage",rulePercentage);
			objectArgs.put("ruleAmount",ruleAmount);
			objectArgs.put("ruleStatus",ruleStatus);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->updatePayComponentRuleValue", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	public ResultObject updatePayComponentRuleStatus(Map<String, Object> objectArgs) {

		logger.info("PayComponentMasterVOGeneratorImpl--->updatePayComponentRuleStatus method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			String reqPayCompRuleGrpId = request.getParameter("reqPayCompRuleGrpId") != null ? StringUtility.getParameter("reqPayCompRuleGrpId",request): "0";
			
			String reqdStatus= request.getParameter("reqdStatus") != null ? StringUtility.getParameter("reqdStatus",request): "-1";
			long reqPayComponentCode = Long.parseLong(request.getParameter("reqPayComponentCode") != null ? StringUtility.getParameter("reqPayComponentCode",request): "0");
			
			objectArgs.put("reqPayCompRuleGrpId",reqPayCompRuleGrpId);
			objectArgs.put("reqPayComponentCode",reqPayComponentCode);
			objectArgs.put("reqdStatus",reqdStatus);
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			logger.error("Error is: "+ ex.getMessage());
			resObj.setThrowable(ex);
			logger.error("Admin Screen Error in PayComponentMasterVOGeneratorImpl--->updatePayComponentRuleStatus", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
}