	
<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">
window.moveTo(250,250);
window.resizeTo(500,300);
</script>

<hdiits:form name="newcommsendasdraftfrm" method="post" validate="true">

<BR><BR><BR>
	
			
			
			
			<center><h3><fmt:message key="WF.CommSendMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/></center>

</hdiits:form>

<script>
function WorkflowAction()
{
	
	self.close();
	
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>