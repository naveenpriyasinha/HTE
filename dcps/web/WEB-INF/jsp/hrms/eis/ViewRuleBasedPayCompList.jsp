
<!--
/**
 * FILENAME      : ViewRuleBasedPayCompList.jsp
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
<%@ include file="../../core/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Pay Components</title>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ruleBasedpayCompList" value="${resValue.ruleBasedpayCompList}"></c:set>




<fmt:setBundle basename="resources.payrollAdminLabels" var="payrollAdminLabels" scope="request"/>

<fmt:message bundle="${payrollAdminLabels}" var="payCompName" key="admin.name"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="detailHead" key="admin.pay_component_head"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="type" key="admin.pay_component_type"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="allowance" key="admin.allowance"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="deduction" key="admin.deduction"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="loan" key="admin.loan"></fmt:message>
<fmt:message bundle="${payrollAdminLabels}" var="savingScheme" key="admin.saving_scheme"></fmt:message>

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
<br>
<legend id=""><fmt:message key="admin.rulebased_pay_component" bundle="${payrollAdminLabels}"></fmt:message></legend>	
<br>
<br>

<%int i = 1; %>
<display:table list="${ruleBasedpayCompList}" id="ruleBasedpayCompList" style="width:100%" pagesize="30" requestURI="" >

		<display:column class="oddcentre" headerClass="datatableheader" title="Sr No"  style="text-align:left"><%=i++ %></display:column>
		  	<display:column class="oddcentre" headerClass="datatableheader" title="${payCompName}" style="text-left">
		  	
		  	<a href="./hrms.htm?actionFlag=viewPayComponentRuleList&elementId=100012512&reqPayComponentCode=${ruleBasedpayCompList.allwDedCode}">
			${ruleBasedpayCompList.allowDedName}</a> 
			</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${detailHead}"  style="text-align:left">
			${ruleBasedpayCompList.componentHead}</display:column>
			<display:column class="oddcentre" headerClass="datatableheader" title="${type}"  style="text-align:left">
			<c:if test="${ruleBasedpayCompList.type eq 2}">
			${allowance}
			</c:if>
			<c:if test="${ruleBasedpayCompList.type eq 3}">
			${deduction}
			</c:if>
			<c:if test="${ruleBasedpayCompList.type eq 4}">
			${loan}
			</c:if>
			<c:if test="${ruleBasedpayCompList.type eq 5}">
			${savingScheme}
			</c:if>
			</display:column>
		
		</display:table>
</fieldset>

</hdiits:form >

<script type="text/javascript">

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
