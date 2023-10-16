
<!--
/**
 * FILENAME      : ViewPayCompRuleList.jsp
 * @VERSION      : 1.0
 * @AUTHOR       : Ravysh Tiwari
 * DATE          : 28th September 2011
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Pay Components</title>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="payCompRuleList" value="${resValue.payCompRuleList}"></c:set>
<c:set var="payComp" value="${resValue.payComp}"></c:set>
<c:set var="reqPayComponentCode" value="${resValue.reqPayComponentCode}"></c:set>
<c:set var="MESSAGE" value="${resValue.MESSAGE}" ></c:set>


<fmt:setBundle basename="resources.payrollAdminLabels" var="payrollAdminLabels" scope="request"/>

<fmt:message bundle="${payrollAdminLabels}" var="ruleComsn" key="admin.pay_component_commission"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleDept" key="admin.department"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleOffice" key="admin.office"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleClass" key="admin.class"></fmt:message>
<%--   <fmt:message bundle="${payrollAdminLabels}" var="ruleEmpCtgry" key="admin.emp_category"></fmt:message>--%>
<fmt:message bundle="${payrollAdminLabels}" var="ruleGender" key="admin.gender"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleDesgn" key="admin.desgn"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="rulePostType" key="admin.post_type"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="rulePhyChallenged" key="admin.phy_challenged"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleCity" key="admin.city_type"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleQuarterType" key="admin.quarter_type"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleScaleFrom" key="admin.scale_from"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleScaleTo" key="admin.scale_to"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleBasicFrom" key="admin.basic_low_limit"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleBasicTo" key="admin.basic_upr_limit"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="ruleGrossFrom" key="admin.grosspay_low_limit"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleGrossTo" key="admin.grosspay_upr_limit"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="ruleGPFrom" key="admin.gp_low_limit"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleGPTo" key="admin.gp_upr_limit"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleFixedAmount" key="admin.amount_paid"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleFormula" key="admin.formula"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="ruleEnable" key="admin.enable"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleDisable" key="admin.disable"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleEnableDisable" key="admin.enable_disable"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="ruleUpdate" key="admin.update"></fmt:message>

<fmt:message bundle="${payrollAdminLabels}" var="backButton" key="admin.back_button"></fmt:message>

<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/prototype.js"></script>
<script type="text/javascript" src='<c:url value="script/common/IFMSCommonFunctions.js"/>'></script>
<script type="text/javascript" src="script/dmo/common.js"></script>

</head>
<body>

<hdiits:form method="post"  name="ViewPayComponents" validate="true" >
<fieldset style="width:100%" class="tabstyle">
<a href="./hrms.htm?actionFlag=addPayComponentRule&reqPayComponentCode=${payComp.allwDedCode}&elementId=100012514"><b><fmt:message key="admin.add_new" bundle="${payrollAdminLabels}"></fmt:message></b></a>
<br>
<br>
<legend id="">${payComp.allowDedShrtName} <fmt:message key="admin.pay_component_rules" bundle="${payrollAdminLabels}"></fmt:message></legend>	
<br>
<br>
<input type="hidden" id="reqPayComponentCode" name="reqPayComponentCode" value="${reqPayComponentCode}"/>
<%int i = 1; %>
<display:table list="${payCompRuleList}" id="payCompRuleList" style="width:100%" pagesize="30" requestURI="" >

		<display:column class="oddcentre" headerClass="datatableheader" title="Sr No"  style="text-align:left"><%=i++ %></display:column>
		  	<display:column class="oddcentre" headerClass="datatableheader" title="${ruleComsn}" style="text-align:center">
		  	${payCompRuleList.payCommissionName} 
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleDept}"  style="text-align:center">
			${payCompRuleList.deptName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleOffice}"  style="text-align:center">
			${payCompRuleList.locationName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleClass}"  style="text-align:center">
			${payCompRuleList.className}
			</display:column>
		<%-- 	<display:column class="oddcentre" headerClass="datatableheader" title="${ruleEmpCtgry}"  style="text-align:center">
			${payCompRuleList.empCatgryName}
			</display:column>
		--%>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleGender}"  style="text-align:center">
			${payCompRuleList.genderName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleDesgn}"  style="text-align:center">
			${payCompRuleList.desgnName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${rulePostType}"  style="text-align:center">
			${payCompRuleList.postTypeName}
			</display:column>
			
			<display:column class="oddcentre" headerClass="datatableheader" title="${rulePhyChallenged}" style="text-align:center">
		  	${payCompRuleList.phyChallenged} 
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleCity}"  style="text-align:center">
			${payCompRuleList.cityName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleQuarterType}"  style="text-align:center">
			${payCompRuleList.quarterTypeName}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="DoJ Year"  style="text-align:center">
			${payCompRuleList.dojYear}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleScaleFrom}"  style="text-align:center">
			${payCompRuleList.scaleLowLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleScaleTo}"  style="text-align:center">
			${payCompRuleList.scaleUprLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleBasicFrom}"  style="text-align:right">
			${payCompRuleList.basicLowLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleBasicTo}"  style="text-align:right">
			${payCompRuleList.basicUprLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleGPFrom}"  style="text-align:right">
			${payCompRuleList.gpLowLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleGPTo}"  style="text-align:right">
			${payCompRuleList.gpUprLmtVal}
			</display:column>
			
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleGrossFrom}"  style="text-align:right">
			${payCompRuleList.grossLowLmtVal}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleGrossTo}"  style="text-align:right">
			${payCompRuleList.grossUprLmtVal}
			</display:column>
		
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleFixedAmount}"  style="text-align:right">
			${payCompRuleList.ruleAmount}
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleFormula}"  style="text-align:center">
			${payCompRuleList.ruleFormulaDisplay}
			</display:column>
			
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleDisable}"  style="text-align:center">
			<!--<c:if test="${payCompRuleList.status eq 0}">
			<a href="./hrms.htm?actionFlag=updatePayComponentRuleStatus&elementId=100012512&reqPayComponentCode=${reqPayComponentCode}&reqdStatus=1&reqPayCompRuleGrpId=${payCompRuleList.ruleGrpId}">
			${ruleEnable}</a>
			</c:if>-->
			<c:if test="${payCompRuleList.status eq 1}">
			<a href="./hrms.htm?actionFlag=updatePayComponentRuleStatus&elementId=100012512&reqPayComponentCode=${reqPayComponentCode}&reqdStatus=0&reqPayCompRuleGrpId=${payCompRuleList.ruleGrpId}">
			${ruleDisable}</a>
			</c:if>
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${ruleUpdate}"  style="text-align:center">
			<a href="./hrms.htm?actionFlag=updatePayComponentRule&elementId=100012912&reqPayComponentCode=${reqPayComponentCode}&reqPayCompRuleGrpId=${payCompRuleList.ruleGrpId}">
			${ruleUpdate}</a>
			</display:column>
		</display:table>
</fieldset>

<center>
 	<tr><td colspan="4">&nbsp;</td></tr>
				
					<tr id="saveData" align="center" >
						<td align="center" colspan="4">
							<hdiits:button type="button" name="back" value="${backButton}" onclick="cancelData()"></hdiits:button>
						</td>
					</tr>
	</center>

 
</hdiits:form >

<script type="text/javascript">
if("${MESSAGE}"!=null&&"${MESSAGE}"!='')
{
	alert("${MESSAGE}");	
		
	document.forms[0].action="hrms.htm?actionFlag=viewPayComponentRuleList&elementId=100012512&reqPayComponentCode=${reqPayComponentCode}";
	document.forms[0].submit();
}
function cancelData()
{
	window.location.href='hrms.htm?actionFlag=viewRuleBasedPayCompList&elementId=100012492';
}

</script>

</body>
</html>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>