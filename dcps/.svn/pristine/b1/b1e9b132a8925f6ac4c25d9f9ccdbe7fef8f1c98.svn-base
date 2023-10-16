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
<c:set var="action" value="${resValue.action}"></c:set>
<script>

function closewindow()
{
	 parent.window.opener.change_parent_url();
	 window.close()
}
</script>
<hdiits:form name="closecommfrm" method="post" validate="true">

<BR><BR><BR>


			<c:if test="${action eq 'closecomm'}">
			<center><h3><fmt:message key="WF.CommCloseSuccMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			<c:if test="${action ne 'closecomm'}">
			<center><h3><fmt:message key="WF.CommDeleteMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="closewindow()"/></center>

</hdiits:form>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>