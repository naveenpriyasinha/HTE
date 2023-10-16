<%
//By Keyur Patel - 202428 
%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" >
	var navDisplay = true;
</script>

<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>
<fmt:setBundle basename="resources.CommonLables" var="adminLables" scope="request"/>
<fmt:setLocale value="en_US"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="resultMessage" value="${resValue.resultMessage}"></c:set>
<c:set var="defaultLangId" value="${resValue.defaultLangId}"></c:set>

	<hdiits:form name="tableWizard" validate = "true" method ="post" action="hdiits.htm?viewName=fieldWizard">

	<div>

		<br><br>
		<center><h2><fmt:message key="RESULT_WIZARD_${defaultLangId}"  bundle="${appgenLables}"/></h2></center>
		
		
		<br><br><br>
		<table cellspacing="10" cellpadding="0" height="30%" width="100%" >
		
		<tr>
			<td><b>
				<c:choose>
					<c:when test="${resultMessage eq 'ADD'}">
						<fmt:message key="RESULT_MESSAGE_ADD_${defaultLangId}"  bundle="${appgenLables}"/>
					</c:when>
					<c:when test="${resultMessage eq 'EDIT'}">
						<fmt:message key="RESULT_MESSAGE_EDIT_${defaultLangId}"  bundle="${appgenLables}"/>
					</c:when>
					<c:otherwise>
						<fmt:message key="RESULT_MESSAGE_ERROR_${defaultLangId}"  bundle="${appgenLables}"/>
					</c:otherwise>
				</c:choose>
			</b></td>
		</tr>		
		
		</table>
				
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>	
					
	</div>            
        
</hdiits:form>
	


