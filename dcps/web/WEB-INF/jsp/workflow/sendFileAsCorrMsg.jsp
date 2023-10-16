<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript">
window.moveTo(250,250);
window.resizeTo(500,300);
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="username" value="${resValue.empName}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>

<BR><BR><BR>


<hdiits:form name="fileSendAsCorrfrm" method="post" validate="true">
		<c:if test="${langId eq '1'}">
		
			<center><h3><fmt:message key="WF.CorrForwardMsg" bundle="${fmsLables}"></fmt:message><c:out value="${username}"></c:out> </h3></center>
			
		</c:if>	
		<c:if test="${langId eq '2'}">
			
			<center><h3><c:out value="${username}" ></c:out><fmt:message key="WF.CorrForwardMsg" bundle="${fmsLables}" ></fmt:message> </h3></center>
			
		</c:if>
		
	
		

	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok" captionid="WF.Ok" bundle="${fmsLables}" onclick="closewindow()" /></center>

</hdiits:form>

<script>

function closewindow()
{

	window.close();	
		
}

</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>