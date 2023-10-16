
<!--
/**
 * FILENAME      : UpdatePayComponentRule.jsp
 * @VERSION      : 1.0
 * @AUTHOR       : Ravysh Tiwari
 * DATE          : 15th November 2011
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
<%@page import="com.tcs.sgv.payroll.util.PayrollConstants"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="payComp" value="${resValue.payComp}"></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="MESSAGE" value="${resValue.MESSAGE}" ></c:set>
<c:set var="reqPayComponentCode" value="${resValue.reqPayComponentCode}" ></c:set>
<c:set var="payCompViewRuleCustomVO" value="${resValue.payCompViewRuleCustomVO}" ></c:set>


<fmt:setBundle basename="resources.payrollAdminLabels" var="payrollAdminLabels" scope="request"/>

<fmt:message bundle="${payrollAdminLabels}" var="percentageAlertMsg" key="admin.percentage_msg"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="fixedAmountAlertMsg" key="admin.fixed_amount_msg"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="saveButton" key="admin.save_button"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="closeButton" key="admin.close_button"></fmt:message>

<c:set var="returnTypeFixed" value="<%=PayrollConstants.PAYROLL_RETURNTYPE_FIXED%>" ></c:set>
<c:set var="returnTypeFormula" value="<%=PayrollConstants.PAYROLL_RETURNTYPE_FORMULA%>" ></c:set>
<c:set var="active" value="<%=PayrollConstants.PAYROLL_STATUS_ACTIVE%>" ></c:set>
<c:set var="inactive" value="<%=PayrollConstants.PAYROLL_STATUS_INACTIVE%>" ></c:set>

<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/prototype.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

</head>
<body>

<ifmis:form method="post"  name="UpdatePayComponentRule" action="" validate="true"  encType="multipart/form-data">
<fieldset style="width:100%" class="tabstyle">
<legend id="">${payComp.allowDedShrtName} <fmt:message key="admin.update_paycomponent_rules" bundle="${payrollAdminLabels}"></fmt:message></legend>	
<br>

<input type="hidden" id="reqPayComponentCode" name="reqPayComponentCode" value="${reqPayComponentCode}"/>
<input type="hidden" id="reqPayCompRuleGrpId" name="reqPayCompRuleGrpId" value="${payCompViewRuleCustomVO.ruleGrpId}"/>
<input type="hidden" id="reqPayCompReturnType" name="reqPayCompReturnType" value="${payCompViewRuleCustomVO.returnType}"/>

 <table width="100%" border="0" cellspacing="3">

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.department" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.deptName}</b></td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.office" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.locationName}</b></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.class" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.className}</b></td>

<%-- 
<td class="fieldLabel"><b><ifmis:caption captionid="admin.emp_category" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.empCatgryName}</b></td>
--%>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gender" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<b>${payCompViewRuleCustomVO.genderName}</b>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.desgn" bundle="${payrollAdminLabels}"/></b></td>
 <td class="fieldLabel"><b>${payCompViewRuleCustomVO.desgnName}</b></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.post_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<b>${payCompViewRuleCustomVO.postTypeName}</b>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.phy_challenged" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.phyChallenged}</b>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.city_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.cityName}</b></td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.quarter_type" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.quarterTypeName}</b></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.pay_component_commission" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<b>${payCompViewRuleCustomVO.payCommissionName}</b>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.scale_from" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<b>${payCompViewRuleCustomVO.scaleLowLmtVal}</b>
</td>

<td class="fieldLabel"><b><ifmis:caption captionid="admin.scale_to" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<b>${payCompViewRuleCustomVO.scaleUprLmtVal}</b>
</td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.basic_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.basicLowLmtVal}</b></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.basic_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.basicUprLmtVal}</b></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gp_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.gpLowLmtVal}</b></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.gp_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.gpUprLmtVal}</b></td>
</tr>

<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.grosspay_low_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.grossLowLmtVal}</b></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.grosspay_upr_limit" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><b>${payCompViewRuleCustomVO.grossUprLmtVal}</b></td>
</tr>

<c:if test="${payCompViewRuleCustomVO.returnType eq returnTypeFixed}">
<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.amount_paid" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.amount_paid" bundle="${payrollAdminLabels}" name="ruleAmount" id="ruleAmount" maxlength="8" style="width:160px" onkeyup="checkDecimalNumber(this, '5', '2');"></ifmis:text></td>
</tr>
</c:if>

<c:if test="${payCompViewRuleCustomVO.returnType eq returnTypeFormula}">
<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.percentage" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:text captionid="admin.percentage" bundle="${payrollAdminLabels}" name="rulePercentage" id="rulePercentage" maxlength="5" style="width:160px" onkeyup="checkDecimalNumber(this, '2', '2');formatFormula(this.value);"></ifmis:text></td>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.formula" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel"><ifmis:textarea captionid="admin.formula" bundle="${payrollAdminLabels}" name="ruleFormulaDisplay" id="ruleFormulaDisplay" readonly="true" style="width:160px;height:80px" ></ifmis:textarea>
</tr>
</c:if>
<tr>
<td class="fieldLabel"><b><ifmis:caption captionid="admin.pay_component_status" bundle="${payrollAdminLabels}"/></b></td>
<td class="fieldLabel">
<ifmis:radio value="${active}" name="rdoRuleStatus" id="rdoRuleStatusActive" captionid="admin.yes" bundle="${payrollAdminLabels}"/>
<ifmis:radio value="${inactive}" name="rdoRuleStatus" id="rdoRuleStatusInactive" captionid="admin.no" bundle="${payrollAdminLabels}"/>
</td>
</tr>

<tr><td colspan="4">&nbsp;</td>
</tr>

<center>
 	<tr><td colspan="4">&nbsp;</td></tr>
				
					<tr id="saveData" align="center" >
						<td align="center" colspan="4">
							<ifmis:button type="button" name="save" value="Save" onclick="submitRule()"></ifmis:button>
							
							<ifmis:button type="button" name="close" value="Close" onclick="cancelData()"></ifmis:button>
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

if('${payCompViewRuleCustomVO.returnType}'=='${returnTypeFixed}')
document.getElementById('ruleAmount').value='${payCompViewRuleCustomVO.ruleAmount}';

if('${payCompViewRuleCustomVO.returnType}'=='${returnTypeFormula}')
{
	document.getElementById('rulePercentage').value='${payCompViewRuleCustomVO.rulePercentage}';
	document.getElementById('ruleFormulaDisplay').value='${payCompViewRuleCustomVO.ruleFormulaDisplay}';
}

if('${payCompViewRuleCustomVO.status}'=='${active}')
	document.getElementById('rdoRuleStatusActive').checked=true;
else if('${payCompViewRuleCustomVO.status}'=='${inactive}')
	document.getElementById('rdoRuleStatusInactive').checked=true;

initializetabcontent("maintab");

function submitRule()
{

	//alert('Hiiiii i am going to update rule');
	if('${payCompViewRuleCustomVO.returnType}'=='${returnTypeFormula}')
	{
		if((document.getElementById('rulePercentage').value=='')||(document.getElementById('rulePercentage').value=='0'))
		{
			alert('${percentageAlertMsg}');
			document.getElementById('rulePercentage').focus();
			return false;
		}

	}
	else if('${payCompViewRuleCustomVO.returnType}'=='${returnTypeFixed}')
		{
		
			if((document.getElementById('ruleAmount').value==''))
			{
			alert('${fixedAmountAlertMsg}');
			document.getElementById('ruleAmount').focus();
			return false;
			}
		}
	//alert('Hellow update rule');
	//disableAllButtons();
	//alert('Hellow  1 update rule');
	//showProgressbar();
//	alert('Hellow  2 update rule');
	document.forms[0].action="hrms.htm?actionFlag=updatePayComponentRuleValue";
	document.forms[0].submit();
	
}

function cancelData()
{
	window.location.href='hrms.htm?actionFlag=viewPayComponentRuleList&elementId=100012512&reqPayComponentCode=${payComp.allwDedCode}';
}

function formatFormula(rulePercentage)
{
	if(rulePercentage!='' && rulePercentage!='0')
	var formula = document.getElementById('ruleFormulaDisplay').value;
	var formulaSub = formula.split('*')[1];
	document.getElementById('ruleFormulaDisplay').value=rulePercentage+'*'+formulaSub;
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