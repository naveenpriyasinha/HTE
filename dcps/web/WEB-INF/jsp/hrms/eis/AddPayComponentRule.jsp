
<!--
/**
 * FILENAME      : AddPayComponentRule.jsp
 * @VERSION      : 1.0
 * @AUTHOR       : Ravysh Tiwari
 * DATE          : 25th september 2011
 *
 * REV. HISTORY :
 *-----------------------------------------------------------------------------
 *     DATE              AUTHOR               DESCRIPTION
 *
 *         
 *-----------------------------------------------------------------------------
 */

-->

<%try
{
%>

<%@ include file="../../core/include.jsp" %>


<html>
<head>

<title>Add Pay Components</title>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="ifmis" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ page import="com.tcs.sgv.payroll.util.PayrollConstants"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="MESSAGE" value="${resValue.MESSAGE}" ></c:set>


<c:set var="editFlag" value="${resValue.editFlag}" ></c:set>
<c:set var="empCtgryList" value="${resValue.empCtgryList}" ></c:set>
<c:set var="reqPayComponentCode" value="${resValue.reqPayComponentCode}" ></c:set>
<c:set var="gradeMstList" value="${resValue.gradeMstList}" ></c:set>
<c:set var="deptMstList" value="${resValue.deptMstList}" ></c:set>
<c:set var="cityList" value="${resValue.cityList}" ></c:set>
<c:set var="commissionMgLst" value="${resValue.commissionMgLst}" ></c:set>
<c:set var="payComp" value="${resValue.payComp}"></c:set>
<c:set var="quarterTypeLst" value="${resValue.quarterTypeLst}"></c:set>
<c:set var="yearList" value="${resValue.yearList}"></c:set>
<c:set var="savedRuleListSize" value="${resValue.savedRuleListSize}"></c:set>

<fmt:setBundle basename="resources.payrollAdminLabels" var="payrollAdminLabels" scope="request"/>

<fmt:message bundle="${payrollAdminLabels}" var="duplicateRecMsg" key="admin.duplicate_rec_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="phyChallengedYes" key="admin.yes"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="phyChallengedNo" key="admin.no"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="duplicatePayCompMsg" key="admin.duplicate_paycomp_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="fixedAmountAlertMsg" key="admin.fixed_amount_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="percentageAlertMsg" key="admin.percentage_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="formulaAlertMsg" key="admin.formula_msg"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="payScaleAlertMsg" key="admin.payscale_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="basicPayAlertMsg" key="admin.basicpay_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="grossPayAlertMsg" key="admin.grosspay_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="gradePayAlertMsg" key="admin.gradepay_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="payCommissionAlertMsg" key="admin.paycomsn_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="otherParameterAlertMsg" key="admin.otherparameter_msg"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="basicPayText" key="admin.basic_pay_text"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="gradePayText" key="admin.grade_pay_text"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="saveButton" key="admin.save_button"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="closeButton" key="admin.close_button"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="addButton" key="admin.add_button"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="resetButton" key="admin.reset_button"></fmt:message>

<c:set var="male" value="<%=PayrollConstants.PAYROLL_GENDER_MALE%>" ></c:set>
<c:set var="female" value="<%=PayrollConstants.PAYROLL_GENDER_FEMALE%>" ></c:set>
<c:set var="permanent" value="<%=PayrollConstants.PAYROLL_PERMANENT%>" ></c:set>
<c:set var="temporary" value="<%=PayrollConstants.PAYROLL_TEMPORARY%>" ></c:set>
<c:set var="fifthPayComm" value="<%=PayrollConstants.PAYROLL_FIFTH_PAYCOMMISSION%>" ></c:set>


<c:set var="sixthPayComm" value="<%=PayrollConstants.PAYROLL_SIXTH_PAYCOMMISSION%>" ></c:set>

<c:set var="Yes" value="<%=PayrollConstants.PAYROLL_YES%>" ></c:set>
<c:set var="No" value="<%=PayrollConstants.PAYROLL_NO%>" ></c:set>
<c:set var="active" value="<%=PayrollConstants.PAYROLL_STATUS_ACTIVE%>" ></c:set>
<c:set var="inactive" value="<%=PayrollConstants.PAYROLL_STATUS_INACTIVE%>" ></c:set>

<c:set var="returnTypeFixed" value="<%=PayrollConstants.PAYROLL_RETURNTYPE_FIXED%>" ></c:set>
<c:set var="returnTypeFormula" value="<%=PayrollConstants.PAYROLL_RETURNTYPE_FORMULA%>" ></c:set>
<c:set var="basicPayCode" value="<%=PayrollConstants.PAYROLL_BASIC_PAY_CODE%>" ></c:set>
<c:set var="gradePayCode" value="<%=PayrollConstants.PAYROLL_GRADE_PAY_CODE%>" ></c:set>

<c:set var="payCompAllowanceType" value="<%=PayrollConstants.PAYROLL_ALLOWANCE_TYPE%>" ></c:set>

<c:set var="phyChallengedYesValue" value="<%=PayrollConstants.PAYROLL_PHYCHALLENGED_YES%>" ></c:set>
<c:set var="phyChallengedNoValue" value="<%=PayrollConstants.PAYROLL_PHYCHALLENGED_NO%>" ></c:set>

<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/prototype.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>

<!--<c:url value="/script/common/addRecord.js"/>"></script>

--></head>
<body>

<ifmis:form method="post"  name="AddPayComponentRule" action="" validate="true"  encType="multipart/form-data">
<fieldset style="width:100%" class="tabstyle">
<legend id="">${payComp.allowDedShrtName} <fmt:message key="admin.add_pay_component_rules" bundle="${payrollAdminLabels}"></fmt:message></legend>	
<br>
<input type="hidden" id="editFlag" name="editFlag" value="${editFlag}"/>
<input type="hidden" id="reqPayComponentCode" name="reqPayComponentCode" value="${reqPayComponentCode}"/>
<input type="hidden" id="ruleFormulaStore" name="ruleFormulaStore" value=""/>
<input type="hidden" id="ruleFormulaDisplaySub" name="ruleFormulaDisplaySub" value=""/>
<input type="hidden" id="rdoPhyChallengedValue" name="rdoPhyChallengedValue" value=""/>

<input type="hidden" id="scaleCmprValue" name="scaleCmprValue" value=""/>
<input type="hidden" id="basicPayCmprValue" name="basicPayCmprValue" value=""/>
<input type="hidden" id="gradePayCmprValue" name="gradePayCmprValue" value=""/>
<input type="hidden" id="otherParamCmprValue" name="otherParamCmprValue" value=""/>
<input type="hidden" id="grossPayCmprValue" name="grossPayCmprValue" value=""/>

 <table width="100%" border="0" cellspacing="3">

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.department" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><select name="ruleDepartmentCmb" id="ruleDepartmentCmb" style="width:168px" onchange="fnGetOffice(this.value)">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
<c:forEach items="${deptMstList}" var="deptMstList">
						<option value="${deptMstList.ddoCode}" title="${deptMstList.ddoName}">${deptMstList.ddoName}</option>
					</c:forEach>
</select></td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.office" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:select multiple="false" captionid="admin.office" bundle="${payrollAdminLabels}" name="ruleOfficeCmb" id="ruleOfficeCmb" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>

</ifmis:select></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.class" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><select name="ruleClassCmb" id="ruleClassCmb" style="width:168px" onchange="fnGetDesgn(this.value)">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
<c:forEach items="${gradeMstList}" var="gradeMstList">
						<option value="${gradeMstList.gradeCode}" title="${gradeMstList.gradeName}">${gradeMstList.gradeName}</option>
					</c:forEach>
</select></td>
<%-- 
<td class="fieldLabel"><b><ifmis:caption captionid="admin.emp_category" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><select name="ruleEmpCategoryCmb" id="ruleEmpCategoryCmb" style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
<c:forEach items="${empCtgryList}" var="empCtgryList">
			<option value="${empCtgryList.ctgryCode}" title="${empCtgryList.ctgryDispName}">${empCtgryList.ctgryDispName}</option>
					</c:forEach>
</select></td>
--%>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gender" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:select captionid="admin.gender" bundle="${payrollAdminLabels}" name="ruleGenderCmb" id="ruleGenderCmb" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<ifmis:option value="${male}" ><ifmis:caption captionid="admin.male" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<ifmis:option value="${female}"><ifmis:caption captionid="admin.female" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
</ifmis:select>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.desgn" bundle="${payrollAdminLabels}"/></b></td>
 <td class="fieldLabel"><select name="ruleDesgnCmb" id="ruleDesgnCmb" style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
</select></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.post_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:select captionid="admin.post_type" bundle="${payrollAdminLabels}" name="rulePostTypeCmb" id="rulePostTypeCmb" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<ifmis:option value="${permanent}" ><ifmis:caption captionid="admin.permanent" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<ifmis:option value="${temporary}"><ifmis:caption captionid="admin.temporary" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
</ifmis:select>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.phy_challenged" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:radio value="${phyChallengedYes}" name="rdoPhyChallenged" id="rdoPhyChallengedYes" captionid="admin.yes" bundle="${payrollAdminLabels}" onclick="phyChallenged('${phyChallengedYesValue}')"/>
<ifmis:radio value="${phyChallengedNo}" name="rdoPhyChallenged" id="rdoPhyChallengedNo" captionid="admin.no" bundle="${payrollAdminLabels}" onclick="phyChallenged('${phyChallengedNoValue}')"/>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.city_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><select name="ruleCityTypeCmb" id="ruleCityTypeCmb" style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
<c:forEach items="${cityList}" var="cityList">
						<option value="${cityList.lookupId}" title="${cityList.lookupName}">${cityList.lookupName}</option>
					</c:forEach>
</select></td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.quarter_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><select name="ruleQuarterTypeCmb" id="ruleQuarterTypeCmb" style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>
<c:forEach items="${quarterTypeLst}" var="quarterTypeLst">
						<option value="${quarterTypeLst.lookupId}" title="${quarterTypeLst.lookupName}">${quarterTypeLst.lookupName}</option>
					</c:forEach>
</select></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.pay_component_commission" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:select captionid="admin.pay_component_commission" bundle="${payrollAdminLabels}" name="rulePayCommissionCmb" id="rulePayCommissionCmb" mandatory="true" onchange="payCommissionFn(this.value)" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<c:forEach items="${commissionMgLst}" var="commissionMgLst">
						<option value="${commissionMgLst.commissionId}">${commissionMgLst.commissionId}th</option>
					</c:forEach>
</ifmis:select>
</td>
<td>DoJ YEAR</td>

<td class="fieldLabel">
<ifmis:select name="dojYear" id="dojYear" mandatory="true" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>
<c:forEach items="${yearList}" var="year">
						<option value="${year.lookupName}">${year.lookupName}</option>
</c:forEach>
</ifmis:select>
</td>

</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.scale_from" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<select name="ruleScaleFromCmb" id="ruleScaleFromCmb"  style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>

</select>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.scale_to" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<select name="ruleScaleToCmb" id="ruleScaleToCmb"  style="width:168px">
<option value=""><ifmis:caption captionid="admin.select_all" bundle="${payrollAdminLabels}"></ifmis:caption></option>

</select>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.basic_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.basic_low_limit" bundle="${payrollAdminLabels}" name="ruleBasicPayLowerLimit" id="ruleBasicPayLowerLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '6', '2');"></ifmis:text></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.basic_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.basic_upr_limit" bundle="${payrollAdminLabels}"  name="ruleBasicPayUpperLimit" id="ruleBasicPayUpperLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '6', '2');"></ifmis:text></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gp_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.gp_low_limit" bundle="${payrollAdminLabels}" name="ruleGradePayLowerLimit" id="ruleGradePayLowerLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '5', '2');"></ifmis:text></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gp_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.gp_upr_limit" bundle="${payrollAdminLabels}"  name="ruleGradePayUpperLimit" id="ruleGradePayUpperLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '5', '2');"></ifmis:text></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.grosspay_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.grosspay_low_limit" bundle="${payrollAdminLabels}" name="ruleGrossPayLowerLimit" id="ruleGrossPayLowerLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '7', '2');"></ifmis:text></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.grosspay_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.grosspay_upr_limit" bundle="${payrollAdminLabels}"  name="ruleGrossPayUpperLimit" id="ruleGrossPayUpperLimit" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '7', '2');"></ifmis:text></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.rule_fixed_amount" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:radio value="${returnTypeFixed}" name="rdoRuleFixedAmount" id="rdoRuleFixedAmountYes" captionid="admin.yes" bundle="${payrollAdminLabels}" onclick="fixedAmount(this.value)"/>
<ifmis:radio value="${returnTypeFormula}" name="rdoRuleFixedAmount" id="rdoRuleFixedAmountNo" captionid="admin.no" bundle="${payrollAdminLabels}" onclick="fixedAmount(this.value)"/>
</td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.amount_paid" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.amount_paid" bundle="${payrollAdminLabels}" name="ruleAmount" id="ruleAmount" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '5', '2');"></ifmis:text></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.percentage" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.percentage" bundle="${payrollAdminLabels}" name="rulePercentage" id="rulePercentage" maxlength="5" style="width:160px" onkeyup="checkDecimalNumber(this, '2', '2');"></ifmis:text></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.pay_comp" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:select captionid="admin.pay_comp" bundle="${payrollAdminLabels}" name="rulePayComponentCmb" id="rulePayComponentCmb" onchange="displayFormula(this.value)" style="width:168px">
<ifmis:option value=""><ifmis:caption captionid="admin.select" bundle="${payrollAdminLabels}"></ifmis:caption></ifmis:option>

</ifmis:select>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.formula" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:textarea captionid="admin.formula" bundle="${payrollAdminLabels}" name="ruleFormulaDisplay" id="ruleFormulaDisplay" readonly="true" style="width:160px;height:80px" ></ifmis:textarea>
<a href="javascript:resetFormula()" title="clear" id="clearFormula">
<c:out value="Clear"></c:out>
</a>
</td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.pay_component_status" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:radio value="${active}" name="rdoRuleStatus" id="rdoRuleStatusActive" captionid="admin.yes" bundle="${payrollAdminLabels}"/>
<ifmis:radio value="${inactive}" name="rdoRuleStatus" id="rdoRuleStatusInactive" captionid="admin.no" bundle="${payrollAdminLabels}"/>
</td>
</tr>

<tr><td colspan="4">&nbsp;</td></tr>
				
					<tr id="saveData" align="center" >
						<td align="center" colspan="4">
							<ifmis:button type="button" name="add" value="${addButton}" onclick="addRule()"></ifmis:button>
							
							<ifmis:button type="button" name="reset" value="${resetButton}" onclick="resetRuleData()"></ifmis:button>
						</td>
					</tr>
	

<center>
<table id="Rule_Tbl" border="1" bgcolor="#ffffff" bordercolor="#aaaaaa" align="center" width="90%">
 			<tr>
			<td width="4%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.pay_component_commission" bundle="${payrollAdminLabels}"/></td>
			<td width="4%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.department" bundle="${payrollAdminLabels}"/></td>
			<td width="3%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.office" bundle="${payrollAdminLabels}"/></td>
			<td width="3%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.class" bundle="${payrollAdminLabels}"/></td>
			<td width="3%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.gender" bundle="${payrollAdminLabels}"/></td>
			<td width="4%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.desgn" bundle="${payrollAdminLabels}"/></td>
			<td width="4%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.post_type" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.phy_challenged" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.city_type" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.quarter_type" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader">dojYear</td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.scale_from" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.scale_to" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.basic_low_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.basic_upr_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.gp_low_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.gp_upr_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.grosspay_low_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="5%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.grosspay_upr_limit" bundle="${payrollAdminLabels}"/></td>
			<td width="3%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.amount_paid" bundle="${payrollAdminLabels}"/></td>
			<td width="7%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.formula" bundle="${payrollAdminLabels}"/></td>
			<td width="10%" bgcolor="#888888" class="tableHeader"><ifmis:caption captionid="admin.delete" bundle="${payrollAdminLabels}"/></td>
		</tr>
 		</table>    
 	</center>	 
 	
 	<center>
 	<tr><td colspan="4">&nbsp;</td></tr>
				
					<tr id="saveData" align="center" >
						<td align="center" colspan="4">
							<ifmis:button type="button" name="save" value="${saveButton}" onclick="submitRule()"></ifmis:button>
							
							<ifmis:button type="button" name="close" value="${closeButton}" onclick="cancelData()"></ifmis:button>
						</td>
					</tr>
	</center>
					
</table>
</fieldset>

</ifmis:form>
<ifmis:validate locale='<%=(String)session.getAttribute("locale")%>' />
</body>
<script type="text/javascript">
if("${MESSAGE}"!=null&&"${MESSAGE}"!='')
{
	alert("${MESSAGE}");			
	document.forms[0].action="hrms.htm?actionFlag=viewPayComponentRuleList&elementId=100012512&reqPayComponentCode=${reqPayComponentCode}";
	document.forms[0].submit();
}
document.getElementById('rdoRuleStatusActive').checked=true;
document.getElementById('rdoPhyChallengedNo').checked=true;
document.getElementById('rdoPhyChallengedValue').value='${phyChallengedNoValue}';
document.getElementById('rdoRuleFixedAmountYes').checked=true;
document.getElementById('rulePercentage').disabled=true;
document.getElementById('rulePayComponentCmb').disabled=true;

if('${payComp.type}'=='${payCompAllowanceType}')
{
document.getElementById('ruleGrossPayLowerLimit').readOnly=true;
document.getElementById('ruleGrossPayUpperLimit').readOnly=true;
}

var scaleAddedCount=0;
var basicAddedCount=0;
var gpAddedCount=0;
var otherParamAddedCount=0;
var grossAddedCount=0;

var cmprReturnFlag;
var cmprReturnMsg;
var cmprReturnRuleGrpId;
var cmprReqPayComponentCode;
initializetabcontent("maintab");

function saveData()
{

if (validateForm_AddPayComponentRule()){

		disableAllButtons();
    	showProgressbar();
    	alert('Jay Javan ');
		document.forms[0].action="hrms.htm?actionFlag=submitPayComponent";
		document.forms[0].submit();
		
	}

}

function addRule()
{
	//alert('inside add rule');
	var size = parseInt('${savedRuleListSize}',10);
	
	if(document.getElementById('rulePayCommissionCmb').value=='')
	{
	alert('${payCommissionAlertMsg}');
	document.getElementById('rulePayCommissionCmb').focus();
	return false;
	}
	else if(document.getElementById('rdoRuleFixedAmountYes').checked==true && document.getElementById('ruleAmount').value=='')
	{
	alert('${fixedAmountAlertMsg}');
	document.getElementById('ruleAmount').focus();
	return false;
	}
	else if(document.getElementById('rdoRuleFixedAmountNo').checked==true && document.getElementById('rulePercentage').value=='')
	{
		alert('${percentageAlertMsg}');
		document.getElementById('rulePercentage').focus();
		return false;
	}
	else if(document.getElementById('rdoRuleFixedAmountNo').checked==true && document.getElementById('ruleFormulaStore').value=='')
	{
		alert('${formulaAlertMsg}');
		document.getElementById('rulePayComponentCmb').focus();
		return false;
	}

	
			var ruleData = new Array('rulePayCommissionCmb','ruleDepartmentCmb','ruleOfficeCmb','ruleClassCmb','ruleGenderCmb',
			'ruleDesgnCmb','rulePostTypeCmb','rdoPhyChallenged','ruleBasicPayLowerLimit','ruleBasicPayUpperLimit','ruleGradePayLowerLimit',
			'ruleGradePayUpperLimit','ruleGrossPayLowerLimit','ruleGrossPayUpperLimit','ruleAmount','rdoRuleStatus','rdoRuleFixedAmount','ruleCityTypeCmb','ruleQuarterTypeCmb','dojYear','ruleScaleFromCmb',
			'ruleScaleToCmb','rulePercentage','ruleFormulaStore','ruleFormulaDisplay','rdoPhyChallengedValue');

			
				if(verifyDuplicate( 'Rule_Tbl' , ruleData, 'encXML', '${duplicateRecMsg}'))
				{
					
					if(otherParamValuesCheck() && ScaleRangeValuesCheck() && BasicPayRangeValuesCheck() && GradePayRangeValuesCheck() && GrossPayRangeValuesCheck())
					{
						//alert('size is '+size);
						
						if(size!=0)
						{
							compareToSavedRules();
						//	alert('vishram '+cmprReturnFlag);
							if(cmprReturnFlag=='0')
							{
								var msgFlag = confirm(cmprReturnMsg);
								alert('savdhannnnnnnnnnn '+msgFlag);
								if(msgFlag)
								{
									window.location.href='hrms.htm?actionFlag=updatePayComponentRule&elementId=100012912&reqPayComponentCode='+cmprReqPayComponentCode+'&reqPayCompRuleGrpId='+cmprReturnRuleGrpId;
									return false;
								}
								else if(!msgFlag)
								{
									resetRuleData();
									return false;
								}
								
							}
						}
					//	alert('going to add hummmmmmmmmmmmmmm');
						addOrUpdateRecord('addRecordToTable', 'addMultipleRuleData', ruleData);
					}
				}
	
}

function addRecordToTable()
{
	//alert("addRecordToTable");
	
	if (xmlHttp.readyState == 4)
	{ 	
		//alert("addRecordToTable  in ready state ");
		var ruleDataForTableDisplay = new Array('rulePayCommissionCmb','ruleDepartmentCmb','ruleOfficeCmb','ruleClassCmb',
				'ruleGenderCmb','ruleDesgnCmb','rulePostTypeCmb','rdoPhyChallenged','ruleCityTypeCmb','ruleQuarterTypeCmb','dojYear','ruleScaleFromCmb',
				'ruleScaleToCmb','ruleBasicPayLowerLimit','ruleBasicPayUpperLimit','ruleGradePayLowerLimit','ruleGradePayUpperLimit',
				'ruleGrossPayLowerLimit','ruleGrossPayUpperLimit','ruleAmount','ruleFormulaDisplay');

		//alert(ruleDataForTableDisplay);
		var rowId = addDataInTable('Rule_Tbl','encXML',ruleDataForTableDisplay,'','deleteRecordTempFn');
		//var rowId = addDataInTable('Rule_Tbl','encXML',ruleDataForTableDisplay,'','');
		//alert("after rowId :: "+rowId);
		
		resetRuleData();
		clearPayScaleCmb();;
		clearDesgnCmb();
		clearOfficeCmb();
		clearPayCompCmb();
	return true;
	}

}

function compareToSavedRules()
{

	var cmprDept      =  document.getElementById('ruleDepartmentCmb').value;
	var cmprOffice    =  document.getElementById('ruleOfficeCmb').value;
	var cmprGrade     =  document.getElementById('ruleClassCmb').value;
	//var cmprEmpCatgry =  document.getElementById('ruleEmpCategoryCmb').value;
	var cmprGender    =  document.getElementById('ruleGenderCmb').value;
	var cmprDesgn     =  document.getElementById('ruleDesgnCmb').value;
	var cmprPostType  =  document.getElementById('rulePostTypeCmb').value;
	var cmprBasicLower    =  document.getElementById('ruleBasicPayLowerLimit').value;
	var cmprBasicUpr     =  document.getElementById('ruleBasicPayUpperLimit').value;
	var cmprGPLower  =  document.getElementById('ruleGradePayLowerLimit').value;
	var cmprGPUpr    =  document.getElementById('ruleGradePayUpperLimit').value;
	var cmprScaleLower     =  document.getElementById('ruleScaleFromCmb').value;
	var cmprScaleUpr  =  document.getElementById('ruleScaleToCmb').value;

	var cmprCity    =  document.getElementById('ruleCityTypeCmb').value;
	var cmprQuarter     =  document.getElementById('ruleQuarterTypeCmb').value;
	var dojYear     =  document.getElementById('dojYear').value;
	alert(dojYear);
	var cmprPayCommission  =  document.getElementById('rulePayCommissionCmb').value;
	var payCompCode = '${reqPayComponentCode}'; 
	
	var cmprPhyChallenged=document.getElementById('rdoPhyChallengedValue').value;
	var cmprGrossLower    =  document.getElementById('ruleGrossPayLowerLimit').value;
	var cmprGrossUpr     =  document.getElementById('ruleGrossPayUpperLimit').value;

	xmlHttp=GetXmlHttpObject();
	 if (xmlHttp==null)
	 {
		  alert ("Your browser does not support AJAX!");
		  return;
	 } 
	
	var url; 
	var uri='';
	url= uri+'&rulePayCommissionCmb='+cmprPayCommission+'&ruleDepartmentCmb='+cmprDept+'&ruleOfficeCmb='+cmprOffice+'&ruleClassCmb='+cmprGrade;
	url=url+'&ruleGenderCmb='+cmprGender+'&ruleDesgnCmb='+cmprDesgn+'&rulePostTypeCmb='+cmprPostType;
	url=url+'&ruleBasicPayLowerLimit='+cmprBasicLower+'&ruleBasicPayUpperLimit='+cmprBasicUpr+'&ruleGradePayLowerLimit='+cmprGPLower+'&ruleGradePayUpperLimit='+cmprGPUpr;
	url=url+'&ruleScaleFromCmb='+cmprScaleLower+'&ruleScaleToCmb='+cmprScaleUpr+'&ruleCityTypeCmb='+cmprCity+'&ruleQuarterTypeCmb='+cmprQuarter+'&dojYear='+dojYear;
	url=url+'&rulePayCommissionCmb='+cmprPayCommission+'&rdoPhyChallengedValue='+cmprPhyChallenged+'&reqPayComponentCode='+payCompCode+'&ruleGrossPayLowerLimit='+cmprGrossLower+'&ruleGrossPayUpperLimit='+cmprGrossUpr;
	
	var actionf="compareTosavedRules";
	uri='./hrms.htm?actionFlag='+actionf;
	url=uri+url; 
	alert("==> url :: "+url);
	xmlHttp.onreadystatechange=compareResult;
	
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);
}

function compareResult()
{
	
	if (xmlHttp.readyState==complete_state)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var namesEntriesReturnVal = XMLDoc.getElementsByTagName('RuleCmprVal');

		cmprReturnMsg=namesEntriesReturnVal[0].childNodes[0].text;
		cmprReturnFlag=namesEntriesReturnVal[0].childNodes[1].text;
		cmprReturnRuleGrpId=namesEntriesReturnVal[0].childNodes[2].text;
		cmprReqPayComponentCode=namesEntriesReturnVal[0].childNodes[3].text;
	//	alert(cmprReturnMsg);
		//alert(cmprReturnFlag);
		//alert(cmprReturnRuleGrpId);
		//alert(cmprReqPayComponentCode);
	}
	
}


function resetRuleData()
{
	
	document.getElementById('ruleDepartmentCmb').value="";
	document.getElementById('ruleOfficeCmb').value="";
	document.getElementById('ruleClassCmb').value="";
	//document.getElementById('ruleEmpCategoryCmb').value="";
	document.getElementById('ruleGenderCmb').value="";
	document.getElementById('ruleDesgnCmb').value="";
	document.getElementById('rulePostTypeCmb').value="";
	document.getElementById('ruleCityTypeCmb').value="";
	document.getElementById('ruleQuarterTypeCmb').value="";

	document.getElementById('dojYear').value="";
	
	document.getElementById('ruleScaleFromCmb').value="";
	document.getElementById('ruleScaleToCmb').value="";
	document.getElementById('ruleBasicPayLowerLimit').value="";
	document.getElementById('ruleBasicPayUpperLimit').value="";
	document.getElementById('ruleGradePayLowerLimit').value="";
	document.getElementById('ruleGradePayUpperLimit').value="";
	document.getElementById('ruleAmount').value="";
	document.getElementById('rulePercentage').value="";
	document.getElementById('rulePayComponentCmb').value="";
	document.getElementById('ruleFormulaStore').value="";
	document.getElementById('ruleFormulaDisplay').value="";
	document.getElementById('ruleFormulaDisplaySub').value="";
	document.getElementById('rdoRuleStatusActive').checked=true;
	document.getElementById('rdoPhyChallengedNo').checked=true;
	document.getElementById('rdoRuleFixedAmountYes').checked=true;
	document.getElementById('rulePayCommissionCmb').value="";

	document.getElementById('ruleGrossPayLowerLimit').value="";
	document.getElementById('ruleGrossPayUpperLimit').value="";
	
	phyChallenged('${phyChallengedNoValue}');
	fixedAmount('${returnTypeFixed}');
}

function submitRule()
{
	alert('i am going t submit the rule');
	document.getElementById('scaleCmprValue').disabled=true;
	document.getElementById('basicPayCmprValue').disabled=true;
	document.getElementById('gradePayCmprValue').disabled=true;
	alert(document.getElementById('otherParamCmprValue').value);
	document.getElementById('otherParamCmprValue').disabled=true;
	document.getElementById('grossPayCmprValue').disabled=true;
	
	//disableAllButtons();
	showProgressbar();
	alert('i am going t submit the rule just ine step away');
	document.forms[0].action="hrms.htm?actionFlag=insertPayComponentRuleDetails";
	
	document.forms[0].submit();
	alert('Complete thay gayu ');
}

function cancelData()
{
	window.location.href='hrms.htm?actionFlag=viewPayComponentRuleList&elementId=100012512&reqPayComponentCode=${payComp.allwDedCode}';
}

function resetFormula()
{
	document.getElementById('ruleFormulaStore').value="";
	document.getElementById('ruleFormulaDisplay').value="";
	document.getElementById('ruleFormulaDisplaySub').value="";
	document.getElementById('rulePercentage').value="";
	document.getElementById('rulePayComponentCmb').value="";
}

function fixedAmount(flag)
{
	if(flag=='${returnTypeFixed}')
	{
	document.getElementById('ruleAmount').disabled=false;
	document.getElementById('ruleFormulaStore').value="";
	document.getElementById('ruleFormulaDisplay').value="";
	document.getElementById('ruleFormulaDisplaySub').value="";
	document.getElementById('rulePercentage').value="";
	document.getElementById('rulePayComponentCmb').value="";
	document.getElementById('rulePercentage').disabled=true;
	document.getElementById('rulePayComponentCmb').disabled=true;
	document.getElementById('clearFormula').disabled=true;
	}
	else if(flag=='${returnTypeFormula}')
	{
		document.getElementById('rulePercentage').disabled=false;
		document.getElementById('rulePayComponentCmb').disabled=false;
		document.getElementById('ruleAmount').value="";
		document.getElementById('ruleAmount').disabled=true;
		document.getElementById('clearFormula').disabled=false;
	}
}

function displayFormula(payCompVal)
{
	if(payCompVal!='')
	{
	var rulePercent =  document.getElementById('rulePercentage').value;
	var payComp = document.forms[0].rulePayComponentCmb;
	var selectedComp = payComp.selectedIndex;
	var payCompText = payComp.options[selectedComp].text;

	var formulaDisplay = document.getElementById('ruleFormulaDisplaySub').value;
	var formulaStore = document.getElementById('ruleFormulaStore').value;

	var addedPayComps =  formulaStore.split('P');
	var newCompFlag = true;
	for(var k=0;k<addedPayComps.length;k++)
	{
		if(addedPayComps[k]==payCompVal)
			newCompFlag=false;
	}
	if(newCompFlag)
	{
		if(formulaDisplay!='' && formulaDisplay!=null)
		{
			//alert("===> in if ....... ");
		document.getElementById('ruleFormulaDisplaySub').value = formulaDisplay+'+'+payCompText;
		document.getElementById('ruleFormulaDisplay').value = rulePercent+'*'+'('+document.getElementById('ruleFormulaDisplaySub').value+')/100';
		document.getElementById('ruleFormulaStore').value = formulaStore+'P'+payCompVal;

		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaDisplaySub').value);
		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaDisplay').value);
		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaStore').value);
		
		}
		else{
			//alert("===> in else ....... ");
		document.getElementById('ruleFormulaDisplaySub').value = payCompText;
		document.getElementById('ruleFormulaDisplay').value = rulePercent+'*'+payCompText+'/100';
		document.getElementById('ruleFormulaStore').value = payCompVal;

		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaDisplaySub').value);
		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaDisplay').value);
		//alert("===> ruleFormulaDisplaySub ::"+document.getElementById('ruleFormulaStore').value);
		
		}
	}
	else{
	alert('${duplicatePayCompMsg}');
	}
}
}

function phyChallenged(phyChallengedFlag)
{
	if(phyChallengedFlag=='${phyChallengedYesValue}')
	{
		document.getElementById('rdoPhyChallengedValue').value='${phyChallengedYesValue}';
	}
	else if(phyChallengedFlag=='${phyChallengedNoValue}')
	{
		document.getElementById('rdoPhyChallengedValue').value='${phyChallengedNoValue}';
	}
}

var dept;
var office;
var grade;
var empCatgry;
var gender;
var desgn;
var postType;
var phyChallengedCmprVal;
var cityCmprVal;
var quarterCmprVal;
var payComsnCmprVal;
var dojYearVal;
function otherParamValuesCheck()
{
	 dept      =  document.getElementById('ruleDepartmentCmb').value!=''?document.getElementById('ruleDepartmentCmb').value:'All';
	 office    =  document.getElementById('ruleOfficeCmb').value!=''?document.getElementById('ruleOfficeCmb').value:'All';
	 grade     =  document.getElementById('ruleClassCmb').value!=''?document.getElementById('ruleClassCmb').value:'All';
	 //empCatgry =  document.getElementById('ruleEmpCategoryCmb').value!=''?document.getElementById('ruleEmpCategoryCmb').value:'All';
	 gender    =  document.getElementById('ruleGenderCmb').value!=''?document.getElementById('ruleGenderCmb').value:'All';
	 desgn     =  document.getElementById('ruleDesgnCmb').value!=''?document.getElementById('ruleDesgnCmb').value:'All';
	 postType  =  document.getElementById('rulePostTypeCmb').value!=''?document.getElementById('rulePostTypeCmb').value:'All';

	 phyChallengedCmprVal=document.getElementById('rdoPhyChallengedValue').value!=''?document.getElementById('rdoPhyChallengedValue').value:'All';
	 cityCmprVal=document.getElementById('ruleCityTypeCmb').value!=''?document.getElementById('ruleCityTypeCmb').value:'All';
	 quarterCmprVal=document.getElementById('ruleQuarterTypeCmb').value!=''?document.getElementById('ruleQuarterTypeCmb').value:'All';

	 
	 dojYearVal = document.getElementById('dojYear').value!=''?document.getElementById('dojYear').value:'All';
	 payComsnCmprVal=document.getElementById('rulePayCommissionCmb').value!=''?document.getElementById('rulePayCommissionCmb').value:'All';

	 var ruleScaleFromCmb = document.getElementById('ruleScaleFromCmb').value;
	 var ruleScaleToCmb = document.getElementById('ruleScaleToCmb').value;
	 var ruleBasicPayLowerLimit = document.getElementById('ruleBasicPayLowerLimit').value;
	 var ruleBasicPayUpperLimit = document.getElementById('ruleBasicPayUpperLimit').value;
	 var ruleGradePayLowerLimit = document.getElementById('ruleGradePayLowerLimit').value;
	 var ruleGradePayUpperLimit = document.getElementById('ruleGradePayUpperLimit').value;

	 var ruleGrossPayLowerLimit = document.getElementById('ruleGrossPayLowerLimit').value;
	 var ruleGrossPayUpperLimit = document.getElementById('ruleGrossPayUpperLimit').value;
	 var otherParamArry;
	 var newOtherParamSetFlag= true;
	 
	 if((ruleScaleFromCmb=='') && (ruleScaleToCmb=='') && (ruleBasicPayLowerLimit=='') && (ruleBasicPayUpperLimit=='') && (ruleGradePayLowerLimit=='') && (ruleGradePayUpperLimit=='') && (ruleGrossPayLowerLimit=='') && (ruleGrossPayUpperLimit==''))
	 {
		 alert('Badhu deselect hoy tevu lage chhe');
		 var otherParamValueSet = document.getElementById('otherParamCmprValue').value;
		 alert(otherParamValueSet);
		 if(otherParamValueSet=='')
		 {
			 alert('1');
			 otherParamAddedCount++;
				document.getElementById('otherParamCmprValue').value = dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+otherParamAddedCount;
				document.getElementById('otherParamCmprValue').value=document.getElementById('otherParamCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal+'#'+dojYearVal;
				return true;
		 }
		 else{
			 var otherParamValueSetArry = otherParamValueSet.split('-');
		      
				for(var z=0;z<otherParamValueSetArry.length;z++)
				{
					alert('2');
					 otherParamArry = otherParamValueSetArry[z].split('#');

					 if(dept==otherParamArry[0] && office==otherParamArry[1] && grade==otherParamArry[2] && gender==otherParamArry[3] && desgn==otherParamArry[4] && postType==otherParamArry[5] && phyChallengedCmprVal==otherParamArry[7] && cityCmprVal==otherParamArry[8] && quarterCmprVal==otherParamArry[9] && payComsnCmprVal==otherParamArry[10]&& dojYearVal==otherParamArry[11])
						{
						 newOtherParamSetFlag=false;
						 alert('${otherParameterAlertMsg}');
							return false;
						}

				}
				if(newOtherParamSetFlag)
				{
					alert('3');
					otherParamAddedCount++;
					document.getElementById('otherParamCmprValue').value =otherParamValueSet+'-'+dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+otherParamAddedCount;
					document.getElementById('otherParamCmprValue').value=document.getElementById('otherParamCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal+'#'+dojYearVal;
					return true;
				}

		 }
	 }
	 else
		 return true;
}

function ScaleRangeValuesCheck()
{
	 dept      =  document.getElementById('ruleDepartmentCmb').value!=''?document.getElementById('ruleDepartmentCmb').value:'All';
	 office    =  document.getElementById('ruleOfficeCmb').value!=''?document.getElementById('ruleOfficeCmb').value:'All';
	 grade     =  document.getElementById('ruleClassCmb').value!=''?document.getElementById('ruleClassCmb').value:'All';
	 //empCatgry =  document.getElementById('ruleEmpCategoryCmb').value!=''?document.getElementById('ruleEmpCategoryCmb').value:'All';
	 gender    =  document.getElementById('ruleGenderCmb').value!=''?document.getElementById('ruleGenderCmb').value:'All';
	 desgn     =  document.getElementById('ruleDesgnCmb').value!=''?document.getElementById('ruleDesgnCmb').value:'All';
	 postType  =  document.getElementById('rulePostTypeCmb').value!=''?document.getElementById('rulePostTypeCmb').value:'All';

	 phyChallengedCmprVal=document.getElementById('rdoPhyChallengedValue').value!=''?document.getElementById('rdoPhyChallengedValue').value:'All';
	 cityCmprVal=document.getElementById('ruleCityTypeCmb').value!=''?document.getElementById('ruleCityTypeCmb').value:'All';
	 quarterCmprVal=document.getElementById('ruleQuarterTypeCmb').value!=''?document.getElementById('ruleQuarterTypeCmb').value:'All';
	 payComsnCmprVal=document.getElementById('rulePayCommissionCmb').value!=''?document.getElementById('rulePayCommissionCmb').value:'All';
	 
	var lowerScale = 0;
	var upperScale = 1000000000;
	var lowerScaleSecondParam = 0;
	var upperScaleSecondParam = 1000000000;

	var lowerScaleText='';
	var upperScaleText='';

//to get lower/upper limit and increment amount/grade pay
	var lowerSelIndx = document.forms[0].ruleScaleFromCmb.selectedIndex;
	var upperSelIndx = document.forms[0].ruleScaleToCmb.selectedIndex;

	
//ends

	var scaleValueSet = document.getElementById('scaleCmprValue').value;
	var newScaleRangeFlag = true;
	var scaleParamArry;


	if((document.getElementById('ruleScaleFromCmb').value!='') || (document.getElementById('ruleScaleFromCmb').value!=''))
	{
		if(document.getElementById('ruleScaleFromCmb').value!='')
		{
			
		lowerScaleText=document.forms[0].ruleScaleFromCmb.options[lowerSelIndx].text;
		
			var lowerScaleTextArry = lowerScaleText.split('-');
			
			lowerScale=lowerScaleTextArry[0];
			lowerScaleSecondParam=lowerScaleTextArry[2];
		}
		if(document.getElementById('ruleScaleToCmb').value!='')
		{
			upperScaleText=document.forms[0].ruleScaleToCmb.options[upperSelIndx].text;
			var upperScaleTextArry = upperScaleText.split('-');
			upperScale=upperScaleTextArry[0];
			upperScaleSecondParam=upperScaleTextArry[2];

		}
		
		lowerScale=parseInt(lowerScale,10);
		upperScale=parseInt(upperScale,10);

		lowerScaleSecondParam=parseInt(lowerScaleSecondParam,10);
		upperScaleSecondParam=parseInt(upperScaleSecondParam,10);
		
		if(lowerScale>upperScale)
		{
			alert('${payScaleAlertMsg}');
			document.getElementById('ruleScaleFromCmb').value="";
			document.getElementById('ruleScaleToCmb').value="";
			return false;
		}
		else if((lowerScale==upperScale)&&(lowerScaleSecondParam>=upperScaleSecondParam))
		{
			alert('${payScaleAlertMsg}');
			document.getElementById('ruleScaleFromCmb').value="";
			document.getElementById('ruleScaleToCmb').value="";
			return false;
		}
		else{
		if(scaleValueSet=='')
		{
			scaleAddedCount++;
		document.getElementById('scaleCmprValue').value = dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerScale+'#'+upperScale+'#'+scaleAddedCount;
		document.getElementById('scaleCmprValue').value=document.getElementById('scaleCmprValue').value+'#'+lowerScaleSecondParam+'#'+upperScaleSecondParam+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
		return true;
		}
		else
		{
		var scaleValueSetArry = scaleValueSet.split('-');
		for(var i=0;i<scaleValueSetArry.length;i++)
		{
			 scaleParamArry = scaleValueSetArry[i].split('#');
			if(dept==scaleParamArry[0] && office==scaleParamArry[1] && grade==scaleParamArry[2] && gender==scaleParamArry[3] && desgn==scaleParamArry[4] && postType==scaleParamArry[5] && phyChallengedCmprVal==scaleParamArry[13] && cityCmprVal==scaleParamArry[14] && quarterCmprVal==scaleParamArry[15] && payComsnCmprVal==scaleParamArry[16])
			{
				
				scaleParamArry[6]=parseInt(scaleParamArry[6],10);
				scaleParamArry[7]=parseInt(scaleParamArry[7],10);
				scaleParamArry[9]=parseInt(scaleParamArry[9],10);
				scaleParamArry[10]=parseInt(scaleParamArry[10],10);
				
				if(((lowerScale>scaleParamArry[6]) && (lowerScale<scaleParamArry[7]))||((upperScale>scaleParamArry[6]) && (upperScale<scaleParamArry[7])))
				{
					newScaleRangeFlag=false;
					alert('${payScaleAlertMsg}');
					document.getElementById('ruleScaleFromCmb').value="";
					document.getElementById('ruleScaleToCmb').value="";
					return false;
				}
				else if((lowerScale==scaleParamArry[6])&&(lowerScaleSecondParam>=scaleParamArry[9])&&(lowerScaleSecondParam<=scaleParamArry[10]))
				{
					newScaleRangeFlag=false;
					alert('${payScaleAlertMsg}');
					document.getElementById('ruleScaleFromCmb').value="";
					document.getElementById('ruleScaleToCmb').value="";
					return false;
				}
				else if((upperScale==scaleParamArry[7])&&(upperScaleSecondParam>=scaleParamArry[9])&&(upperScaleSecondParam<=scaleParamArry[10]))
				{
					newScaleRangeFlag=false;
					alert('${payScaleAlertMsg}');
					document.getElementById('ruleScaleFromCmb').value="";
					document.getElementById('ruleScaleToCmb').value="";
					return false;
				}

			}
		}
		if(newScaleRangeFlag){
			scaleAddedCount++;
			document.getElementById('scaleCmprValue').value =scaleValueSet+'-'+dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerScale+'#'+upperScale+'#'+scaleAddedCount;
			document.getElementById('scaleCmprValue').value=document.getElementById('scaleCmprValue').value+'#'+lowerScaleSecondParam+'#'+upperScaleSecondParam+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
			return true;
			}
		}
	}
	}
	else if((document.getElementById('ruleScaleFromCmb').value=='') && (document.getElementById('ruleScaleFromCmb').value==''))
	{
		if(scaleValueSet=='')
		{
			return true;
		}
		else
		{
			alert('${payScaleAlertMsg}');
		return false;
		}
	}

}



function BasicPayRangeValuesCheck()
{
	 dept      =  document.getElementById('ruleDepartmentCmb').value!=''?document.getElementById('ruleDepartmentCmb').value:'All';
	 office    =  document.getElementById('ruleOfficeCmb').value!=''?document.getElementById('ruleOfficeCmb').value:'All';
	 grade     =  document.getElementById('ruleClassCmb').value!=''?document.getElementById('ruleClassCmb').value:'All';
	 //empCatgry =  document.getElementById('ruleEmpCategoryCmb').value!=''?document.getElementById('ruleEmpCategoryCmb').value:'All';
	 gender    =  document.getElementById('ruleGenderCmb').value!=''?document.getElementById('ruleGenderCmb').value:'All';
	 desgn     =  document.getElementById('ruleDesgnCmb').value!=''?document.getElementById('ruleDesgnCmb').value:'All';
	 postType  =  document.getElementById('rulePostTypeCmb').value!=''?document.getElementById('rulePostTypeCmb').value:'All';

	 phyChallengedCmprVal=document.getElementById('rdoPhyChallengedValue').value!=''?document.getElementById('rdoPhyChallengedValue').value:'All';
	 cityCmprVal=document.getElementById('ruleCityTypeCmb').value!=''?document.getElementById('ruleCityTypeCmb').value:'All';
	 quarterCmprVal=document.getElementById('ruleQuarterTypeCmb').value!=''?document.getElementById('ruleQuarterTypeCmb').value:'All';
	 payComsnCmprVal=document.getElementById('rulePayCommissionCmb').value!=''?document.getElementById('rulePayCommissionCmb').value:'All';
	 
	var lowerBasic =  document.getElementById('ruleBasicPayLowerLimit').value;
	var upperBasic =  document.getElementById('ruleBasicPayUpperLimit').value;

	var basicPayValueSet = document.getElementById('basicPayCmprValue').value;
	var newBasicRangeFlag = true;
	var basicPayParamArry;

	
	if(lowerBasic!='' || upperBasic!='')
	{
		
		if(lowerBasic=='')
			lowerBasic=0;
		if(upperBasic=='')
			upperBasic=1000000000;

		lowerBasic=parseInt(lowerBasic,10);
		upperBasic=parseInt(upperBasic,10);
		if(lowerBasic>=upperBasic)
		{
			alert('${basicPayAlertMsg}');
			document.getElementById('ruleBasicPayLowerLimit').value="";
			document.getElementById('ruleBasicPayUpperLimit').value="";
			return false;
		}
	else{
		
		if(basicPayValueSet=='')
		{
			basicAddedCount++;
		document.getElementById('basicPayCmprValue').value = dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerBasic+'#'+upperBasic+'#'+basicAddedCount;
		document.getElementById('basicPayCmprValue').value=document.getElementById('basicPayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
		return true;
		}
		else
		{
		var basicPayValueSetArry = basicPayValueSet.split('-');
      
		for(var j=0;j<basicPayValueSetArry.length;j++)
		{
			 basicPayParamArry = basicPayValueSetArry[j].split('#');
 
			
			if(dept==basicPayParamArry[0] && office==basicPayParamArry[1] && grade==basicPayParamArry[2] && gender==basicPayParamArry[3] && desgn==basicPayParamArry[4] && postType==basicPayParamArry[5] && phyChallengedCmprVal==basicPayParamArry[9] && cityCmprVal==basicPayParamArry[10] && quarterCmprVal==basicPayParamArry[11] && payComsnCmprVal==basicPayParamArry[12])
			{
				
				basicPayParamArry[6]=parseInt(basicPayParamArry[6],10);
				basicPayParamArry[7]=parseInt(basicPayParamArry[7],10);
				if(((lowerBasic>=basicPayParamArry[6]) && (lowerBasic<=basicPayParamArry[7]))||((upperBasic>=basicPayParamArry[6]) && (upperBasic<=basicPayParamArry[7])))
				{
					newBasicRangeFlag=false;
					alert('${basicPayAlertMsg}');
					document.getElementById('ruleBasicPayLowerLimit').value="";
					document.getElementById('ruleBasicPayUpperLimit').value="";
					return false;
				}

			}
		}
			if(newBasicRangeFlag){
				basicAddedCount++;
			document.getElementById('basicPayCmprValue').value =basicPayValueSet+'-'+dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerBasic+'#'+upperBasic+'#'+basicAddedCount;
			document.getElementById('basicPayCmprValue').value=document.getElementById('basicPayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
			return true;
			}
		}
	}
	}
	else if((document.getElementById('ruleBasicPayLowerLimit').value=='') && (document.getElementById('ruleBasicPayUpperLimit').value==''))
	{
		if(basicPayValueSet=='')
		{
			return true;
		}
		else
		{
			alert('${basicPayAlertMsg}');
		return false;
		}
	}
	
}

function GrossPayRangeValuesCheck()
{
	 dept      =  document.getElementById('ruleDepartmentCmb').value!=''?document.getElementById('ruleDepartmentCmb').value:'All';
	 office    =  document.getElementById('ruleOfficeCmb').value!=''?document.getElementById('ruleOfficeCmb').value:'All';
	 grade     =  document.getElementById('ruleClassCmb').value!=''?document.getElementById('ruleClassCmb').value:'All';
	 //empCatgry =  document.getElementById('ruleEmpCategoryCmb').value!=''?document.getElementById('ruleEmpCategoryCmb').value:'All';
	 gender    =  document.getElementById('ruleGenderCmb').value!=''?document.getElementById('ruleGenderCmb').value:'All';
	 desgn     =  document.getElementById('ruleDesgnCmb').value!=''?document.getElementById('ruleDesgnCmb').value:'All';
	 postType  =  document.getElementById('rulePostTypeCmb').value!=''?document.getElementById('rulePostTypeCmb').value:'All';

	 phyChallengedCmprVal=document.getElementById('rdoPhyChallengedValue').value!=''?document.getElementById('rdoPhyChallengedValue').value:'All';
	 cityCmprVal=document.getElementById('ruleCityTypeCmb').value!=''?document.getElementById('ruleCityTypeCmb').value:'All';
	 quarterCmprVal=document.getElementById('ruleQuarterTypeCmb').value!=''?document.getElementById('ruleQuarterTypeCmb').value:'All';
	 payComsnCmprVal=document.getElementById('rulePayCommissionCmb').value!=''?document.getElementById('rulePayCommissionCmb').value:'All';
	 
	var lowerGross =  document.getElementById('ruleGrossPayLowerLimit').value;
	var upperGross =  document.getElementById('ruleGrossPayUpperLimit').value;

	var grossPayValueSet = document.getElementById('grossPayCmprValue').value;
	var newGrossRangeFlag = true;
	var grossPayParamArry;

	
	if(lowerGross!='' || upperGross!='')
	{
		
		if(lowerGross=='')
			lowerGross=0;
		if(upperGross=='')
			upperGross=1000000000;

		lowerGross=parseInt(lowerGross,10);
		upperGross=parseInt(upperGross,10);
		if(lowerGross>=upperGross)
		{
			alert('${grossPayAlertMsg}');
			document.getElementById('ruleGrossPayLowerLimit').value="";
			document.getElementById('ruleGrossPayUpperLimit').value="";
			return false;
		}
	else{
		
		if(grossPayValueSet=='')
		{
			grossAddedCount++;
		document.getElementById('grossPayCmprValue').value = dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerGross+'#'+upperGross+'#'+grossAddedCount;
		document.getElementById('grossPayCmprValue').value=document.getElementById('grossPayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
		return true;
		}
		else
		{
		var grossPayValueSetArry = grossPayValueSet.split('-');
      
		for(var j=0;j<grossPayValueSetArry.length;j++)
		{
			 grossPayParamArry = grossPayValueSetArry[j].split('#');
 
			
			if(dept==grossPayParamArry[0] && office==grossPayParamArry[1] && grade==grossPayParamArry[2] && gender==grossPayParamArry[3] && desgn==grossPayParamArry[4] && postType==grossPayParamArry[5] && phyChallengedCmprVal==grossPayParamArry[10] && cityCmprVal==grossPayParamArry[10] && quarterCmprVal==grossPayParamArry[11] && payComsnCmprVal==grossPayParamArry[12])
			{
				
				grossPayParamArry[6]=parseInt(grossPayParamArry[6],10);
				grossPayParamArry[7]=parseInt(grossPayParamArry[7],10);
				if(((lowerGross>=grossPayParamArry[6]) && (lowerGross<=grossPayParamArry[7]))||((upperGross>=grossPayParamArry[6]) && (upperGross<=grossPayParamArry[7])))
				{
					newGrossRangeFlag=false;
					alert('${grossPayAlertMsg}');
					document.getElementById('ruleGrossPayLowerLimit').value="";
					document.getElementById('ruleGrossPayUpperLimit').value="";
					return false;
				}

			}
		}
			if(newGrossRangeFlag){
				grossAddedCount++;
			document.getElementById('grossPayCmprValue').value =grossPayValueSet+'-'+dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerGross+'#'+upperGross+'#'+grossAddedCount;
			document.getElementById('grossPayCmprValue').value=document.getElementById('grossPayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
			return true;
			}
		}
	}
	}
	else if((document.getElementById('ruleGrossPayLowerLimit').value=='') && (document.getElementById('ruleGrossPayUpperLimit').value==''))
	{
		if(grossPayValueSet=='')
		{
			return true;
		}
		else
		{
			alert('${grossPayAlertMsg}');
		return false;
		}
	}
	
}

function GradePayRangeValuesCheck()
{
	dept      =  document.getElementById('ruleDepartmentCmb').value!=''?document.getElementById('ruleDepartmentCmb').value:'All';
	 office    =  document.getElementById('ruleOfficeCmb').value!=''?document.getElementById('ruleOfficeCmb').value:'All';
	 grade     =  document.getElementById('ruleClassCmb').value!=''?document.getElementById('ruleClassCmb').value:'All';
	 //empCatgry =  document.getElementById('ruleEmpCategoryCmb').value!=''?document.getElementById('ruleEmpCategoryCmb').value:'All';
	 gender    =  document.getElementById('ruleGenderCmb').value!=''?document.getElementById('ruleGenderCmb').value:'All';
	 desgn     =  document.getElementById('ruleDesgnCmb').value!=''?document.getElementById('ruleDesgnCmb').value:'All';
	 postType  =  document.getElementById('rulePostTypeCmb').value!=''?document.getElementById('rulePostTypeCmb').value:'All';

	 phyChallengedCmprVal=document.getElementById('rdoPhyChallengedValue').value!=''?document.getElementById('rdoPhyChallengedValue').value:'All';
	 cityCmprVal=document.getElementById('ruleCityTypeCmb').value!=''?document.getElementById('ruleCityTypeCmb').value:'All';
	 quarterCmprVal=document.getElementById('ruleQuarterTypeCmb').value!=''?document.getElementById('ruleQuarterTypeCmb').value:'All';
	 payComsnCmprVal=document.getElementById('rulePayCommissionCmb').value!=''?document.getElementById('rulePayCommissionCmb').value:'All';
		
	var lowerGP    =  document.getElementById('ruleGradePayLowerLimit').value;
	var upperGP    =  document.getElementById('ruleGradePayUpperLimit').value;

	var gradePayValueSet = document.getElementById('gradePayCmprValue').value;
	var newGPRangeFlag = true;
	var gradePayParamArry;

	if(lowerGP!='' || upperGP!='')
	{

		if(lowerGP=='')
			lowerGP=0;
		if(upperGP=='')
			upperGP=1000000000;

		lowerGP=parseInt(lowerGP,10);
		upperGP=parseInt(upperGP,10);
		if(lowerGP>=upperGP)
		{
			alert('${gradePayAlertMsg}');
			document.getElementById('ruleGradePayLowerLimit').value="";
			document.getElementById('ruleGradePayUpperLimit').value="";
			return false;
		}
		else{
		if(gradePayValueSet=='')
		{
			gpAddedCount++;
		document.getElementById('gradePayCmprValue').value = dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerGP+'#'+upperGP+'#'+gpAddedCount;
		document.getElementById('gradePayCmprValue').value=document.getElementById('gradePayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
		return true;
		}
		else
		{
		var gradePayValueSetArry = gradePayValueSet.split('-');
      
		for(var x=0;x<gradePayValueSetArry.length;x++)
		{
			 gradePayParamArry = gradePayValueSetArry[x].split('#');
 
			
			if(dept==gradePayParamArry[0] && office==gradePayParamArry[1] && grade==gradePayParamArry[2] && gender==gradePayParamArry[3] && desgn==gradePayParamArry[4] && postType==gradePayParamArry[5] && phyChallengedCmprVal==gradePayParamArry[9] && cityCmprVal==gradePayParamArry[10] && quarterCmprVal==gradePayParamArry[11] && payComsnCmprVal==gradePayParamArry[12])
			{
				
				gradePayParamArry[6]=parseInt(gradePayParamArry[6],10);
				gradePayParamArry[7]=parseInt(gradePayParamArry[7],10);
				if(((lowerGP>=gradePayParamArry[6]) && (lowerGP<=gradePayParamArry[7]))||((upperGP>=gradePayParamArry[6]) && (upperGP<=gradePayParamArry[7])))
				{
					newGPRangeFlag=false;
					alert('${gradePayAlertMsg}');
					document.getElementById('ruleGradePayLowerLimit').value="";
					document.getElementById('ruleGradePayUpperLimit').value="";
					return false;
				}

			}
		}
			if(newGPRangeFlag){
				gpAddedCount++;
			document.getElementById('gradePayCmprValue').value =gradePayValueSet+'-'+dept+'#'+office+'#'+grade+'#'+gender+'#'+desgn+'#'+postType+'#'+lowerGP+'#'+upperGP+'#'+gpAddedCount;
			document.getElementById('gradePayCmprValue').value=document.getElementById('gradePayCmprValue').value+'#'+phyChallengedCmprVal+'#'+cityCmprVal+'#'+quarterCmprVal+'#'+payComsnCmprVal;
			return true;
			}
		}
	}
	}
	else if((document.getElementById('ruleGradePayLowerLimit').value=='') && (document.getElementById('ruleGradePayUpperLimit').value==''))
	{
		if(gradePayValueSet=='')
		{
			return true;
		}
		else
		{
			alert('${gradePayAlertMsg}');
		return false;
		}
	}
}


function deleteRecordTempFn(rowId)
{
	//alert("==> alert in delete :: "+rowId);
	
	var rowIdVal = rowId.split('L');
	var rowIdCount =  rowIdVal[1];
	rowIdCount= parseInt(rowIdCount,10);
		
	var scaleAddedValueSet = document.getElementById('scaleCmprValue').value;
	var basicPayAddedValueSet = document.getElementById('basicPayCmprValue').value;
	var gradePayAddedValueSet = document.getElementById('gradePayCmprValue').value;
	var otherParamAddedValueSet = document.getElementById('otherParamCmprValue').value;

	var scaleNewVal;
	var basicPayNewVal;
	var gradePayNewVal;
	var otherParamNewVal;
	
	
	
	if(scaleAddedValueSet!='');
	{
		var tempScaleCount=0;
		scaleAddedValueSet=scaleAddedValueSet.split('-');

		if(scaleAddedValueSet.length>1)
		{
		for(var scaleCnt=0;scaleCnt<scaleAddedValueSet.length;scaleCnt++)
		{
			if(parseInt(scaleAddedValueSet[scaleCnt].split('#')[8],10)!=rowIdCount)
			{
				tempScaleCount++;
				if(tempScaleCount==1)
				{
					scaleNewVal=scaleAddedValueSet[scaleCnt];
				}
				else
					scaleNewVal=scaleNewVal+'-'+scaleAddedValueSet[scaleCnt];
			}
		}
		}
		else if(scaleAddedValueSet.length==1)
			scaleNewVal='';
		document.getElementById('scaleCmprValue').value=scaleNewVal;
	}

	//alert("==> 1111111111111111111");
	
	if(basicPayAddedValueSet!='')
	{
		var tempBasicCount=0;
		basicPayAddedValueSet=basicPayAddedValueSet.split('-');

		if(basicPayAddedValueSet.length>1)
		{
		for(var basicCnt=0;basicCnt<basicPayAddedValueSet.length;basicCnt++)
		{
			if(parseInt(basicPayAddedValueSet[basicCnt].split('#')[8],10)!=rowIdCount)
			{
				tempBasicCount++;
				if(tempBasicCount==1)
				{
					basicPayNewVal=basicPayAddedValueSet[basicCnt];
				}
				else
					basicPayNewVal=basicPayNewVal+'-'+basicPayAddedValueSet[basicCnt];
			}
		}
		}
		else if(basicPayAddedValueSet.length==1)
			basicPayNewVal='';
		document.getElementById('basicPayCmprValue').value=basicPayNewVal;
		
	}

	//alert("==> 22222222222222222222");
	
	if(gradePayAddedValueSet!='')
	{
		var tempGPCount=0;
		gradePayAddedValueSet=gradePayAddedValueSet.split('-');

		if(gradePayAddedValueSet.length>1)
		{
		for(var gpCnt=0;gpCnt<gradePayAddedValueSet.length;gpCnt++)
		{
			if(parseInt(gradePayAddedValueSet[gpCnt].split('#')[8],10)!=rowIdCount)
			{
				tempGPCount++;
				if(tempGPCount==1)
				{
					gradePayNewVal=gradePayAddedValueSet[gpCnt];
				}
				else
					gradePayNewVal=gradePayNewVal+'-'+gradePayAddedValueSet[gpCnt];
			}
		}
		}
		else if(gradePayAddedValueSet.length==1)
			gradePayNewVal='';
		document.getElementById('gradePayCmprValue').value=gradePayNewVal;
	}
	//alert("==> 33333333333333333333");
	//**************
	if(otherParamAddedValueSet!='')
	{
		var tempOtherParamCount=0;
		otherParamAddedValueSet=otherParamAddedValueSet.split('-');

		if(otherParamAddedValueSet.length>1)
		{
		for(var otherParamCnt=0;otherParamCnt<gradePayAddedValueSet.length;otherParamCnt++)
		{
			if(parseInt(otherParamAddedValueSet[otherParamCnt].split('#')[8],10)!=rowIdCount)
			{
				tempOtherParamCount++;
				if(tempOtherParamCount==1)
				{
					otherParamNewVal=otherParamAddedValueSet[otherParamCnt];
				}
				else
					otherParamNewVal=otherParamNewVal+'-'+otherParamAddedValueSet[otherParamCnt];
			}
		}
		}
		else if(otherParamAddedValueSet.length==1)
			otherParamNewVal='';
		document.getElementById('otherParamCmprValue').value=otherParamNewVal;
	}

	//alert("==> before deleteRecord() :: "+rowId);
	
	deleteRecord(rowId);
}

function payCommissionFn(payComsn)
{
	clearPayScaleCmb();
	clearPayCompCmb();
	document.getElementById('ruleScaleFromCmb').value="";
	document.getElementById('ruleScaleToCmb').value="";
	if(payComsn=='${fifthPayComm}')
	{
		document.getElementById('ruleGradePayLowerLimit').value="";
		document.getElementById('ruleGradePayUpperLimit').value="";
		document.getElementById('ruleGradePayLowerLimit').disabled=true;
		document.getElementById('ruleGradePayUpperLimit').disabled=true;	

		//to set pay components
		var payCompObj = document.getElementById('rulePayComponentCmb');
		var payComp1 = document.createElement('option');
		payComp1.value='${basicPayCode}';
		payComp1.text='${basicPayText}';
		try
    	{      				    					
			payCompObj.add(payComp1,null);
    	}
   		catch(ex)
    	{
   			payCompObj.add(payComp1); 
        	}
	}
	else if(payComsn=='${sixthPayComm}')
	{
		document.getElementById('ruleGradePayLowerLimit').disabled=false;
		document.getElementById('ruleGradePayUpperLimit').disabled=false;

		//to set pay components
		var payCompObj2 = document.getElementById('rulePayComponentCmb');
		var payComp2 = document.createElement('option');
		payComp2.value='${basicPayCode}';
		payComp2.text='${basicPayText}';

		var payComp3 = document.createElement('option');
		payComp3.value='${gradePayCode}';
		payComp3.text='${gradePayText}';
		try
    	{      				    					
			payCompObj2.add(payComp2,null);
			payCompObj2.add(payComp3,null);
    	}
   		catch(ex)
    	{
   			payCompObj2.add(payComp2); 
   			payCompObj2.add(payComp3); 
        	}
		
	}

	if(payComsn!='')
	{
		xmlHttp=GetXmlHttpObject();
		 if (xmlHttp==null)
		 {
			  alert ("Your browser does not support AJAX!");
			  return;
		 } 
		
		var url; 
		var uri='';
		url= uri+'&rulePayCommissionCmb='+payComsn;
		
		var actionf="getScaleFromCommission";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+url; 
		xmlHttp.onreadystatechange=mappedPayScale;
		
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}
}

function mappedPayScale()
{
	if (xmlHttp.readyState==complete_state)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var namesEntriesScale = XMLDoc.getElementsByTagName('ScaleFrmPayComsn');
		var lowerScaleObj = document.getElementById('ruleScaleFromCmb');
		var upperScaleObj = document.getElementById('ruleScaleToCmb');

		var payComsn = document.getElementById('rulePayCommissionCmb').value;
		
		for(var scaleCnt=0;scaleCnt<namesEntriesScale.length;scaleCnt++)
		{
			var y1 = document.createElement('option');  
			var y2 = document.createElement('option');  
   			val=namesEntriesScale[scaleCnt].childNodes[0].text;  

   			y1.value=val;
   			y2.value=val;
   		 	if(payComsn=='${fifthPayComm}')
   		 	{
   		 	text= namesEntriesScale[scaleCnt].childNodes[1].text+'-'+namesEntriesScale[scaleCnt].childNodes[2].text+'-'+namesEntriesScale[scaleCnt].childNodes[4].text;
   		 	}
   		 	else if(payComsn=='${sixthPayComm}')
   		 	{
   	   		// alert('inside sizth pay commission');
   	   		 //alert(namesEntriesScale[scaleCnt].childNodes[1].text);
   	   		 //alert(namesEntriesScale[scaleCnt].childNodes[2].text);
   	   		 //alert(namesEntriesScale[scaleCnt].childNodes[3].text);
   		 	text= namesEntriesScale[scaleCnt].childNodes[1].text+'-'+namesEntriesScale[scaleCnt].childNodes[2].text+'('+namesEntriesScale[scaleCnt].childNodes[3].text+')';
   		 	}
        	y1.text=text;
        	y1.title=text;
        	y2.text=text;
        	y2.title=text;
        	
        	try
        	{      				    					
        		lowerScaleObj.add(y1,null);
        		upperScaleObj.add(y2,null);
	    	}
       		catch(ex)
        	{
       			lowerScaleObj.add(y1); 
       			upperScaleObj.add(y2); 
	        	}	
		}
	}
}

function fnGetOffice(deptCode)
{
	
	clearOfficeCmb();

	if(deptCode!='')
	{
		xmlHttp=GetXmlHttpObject();
		 if (xmlHttp==null)
		 {
			  alert ("Your browser does not support AJAX!");
			  return;
		 } 
		
		var url; 
		var uri='';
		url= uri+'&ruleDepartmentCmb='+deptCode;
		
		var actionf="getOfficeFromDept";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+url; 
		xmlHttp.onreadystatechange=mappedOffice;
		
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}
}

function mappedOffice()
{
	if (xmlHttp.readyState==complete_state)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var namesEntriesOffice = XMLDoc.getElementsByTagName('OfficeFrmDept');
		var officeObj = document.getElementById('ruleOfficeCmb');

		for(var locCnt=0;locCnt<namesEntriesOffice.length;locCnt++)
		{
			var y1 = document.createElement('option');  
   			val=namesEntriesOffice[locCnt].childNodes[0].text;  
   			text= namesEntriesOffice[locCnt].childNodes[1].text;
   	
   		 	y1.value=val;
        	y1.text=text;
        	y1.title=text;
        	try
        	{      				    					
        		officeObj.add(y1,null);
	    	}
       		catch(ex)
        	{
       			officeObj.add(y1); 
	        	}	
		}
	}

}

function fnGetDesgn(gradeCode)
{
	clearDesgnCmb();
	if(gradeCode!='')
	{
		xmlHttp=GetXmlHttpObject();
		 if (xmlHttp==null)
		 {
			  alert ("Your browser does not support AJAX!");
			  return;
		 } 
		
		var url; 
		var uri='';
		url= uri+'&ruleClassCmb='+gradeCode;
		
		var actionf="getDesgnFromGrade";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+url; 
		xmlHttp.onreadystatechange=mappedDesignation;
		
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}
}

function mappedDesignation()
{
	if (xmlHttp.readyState==complete_state)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var namesEntriesDesgn = XMLDoc.getElementsByTagName('DesgnFrmGrade');
		var desgnObj = document.getElementById('ruleDesgnCmb');

		for(var desgnCnt=0;desgnCnt<namesEntriesDesgn.length;desgnCnt++)
		{
			var y1 = document.createElement('option');  
   			val=namesEntriesDesgn[desgnCnt].childNodes[0].text;  
   			text= namesEntriesDesgn[desgnCnt].childNodes[1].text;
   	
   		 	y1.value=val;
        	y1.text=text;
        	y1.title=text;
        	try
        	{      				    					
        		desgnObj.add(y1,null);
	    	}
       		catch(ex)
        	{
       			desgnObj.add(y1); 
	        	}	
		}
	}
}

function clearPayScaleCmb()
{
	var scaleFromLength=document.getElementById("ruleScaleFromCmb").length;
	for(i=1;i<scaleFromLength;i++)
	{
		lgth = document.getElementById("ruleScaleFromCmb").options.length -1;
		document.getElementById("ruleScaleFromCmb").options[lgth] = null;
	}
	var scaleToLength=document.getElementById("ruleScaleToCmb").length;
	for(i=1;i<scaleToLength;i++)
	{
		lgth = document.getElementById("ruleScaleToCmb").options.length -1;
		document.getElementById("ruleScaleToCmb").options[lgth] = null;
	}	
}

function clearOfficeCmb()
{
	var officeLength=document.getElementById("ruleOfficeCmb").length;
	for(i=1;i<officeLength;i++)
	{
		lgth = document.getElementById("ruleOfficeCmb").options.length -1;
		document.getElementById("ruleOfficeCmb").options[lgth] = null;
	}
}

function clearDesgnCmb()
{
	var desgnLength=document.getElementById("ruleDesgnCmb").length;
	for(i=1;i<desgnLength;i++)
	{
		lgth = document.getElementById("ruleDesgnCmb").options.length -1;
		document.getElementById("ruleDesgnCmb").options[lgth] = null;
	}
}

function clearPayCompCmb()
{
	var payCompLength=document.getElementById("rulePayComponentCmb").length;
	for(i=1;i<payCompLength;i++)
	{
		lgth = document.getElementById("rulePayComponentCmb").options.length -1;
		document.getElementById("rulePayComponentCmb").options[lgth] = null;
	}
}
</script>
</html>


<!-- body part of any page end-->
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>