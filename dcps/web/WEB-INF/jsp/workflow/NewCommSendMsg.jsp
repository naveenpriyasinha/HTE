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
<c:set var="draftflag" value="${resValue.draftflag}"></c:set>


<hdiits:form name="newcommsendfrm" method="post" validate="true">

<BR><BR><BR>
	
			<c:if test="${draftflag eq 'yes'}">
			<center><h3><fmt:message key="WF.DftSaveMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			<c:if test="${draftflag ne 'yes'}">
			<center><h3><fmt:message key="WF.CommSendMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/></center>

</hdiits:form>

<script>
function WorkflowAction()
{
	var fileId='${resValue.fileId}'
	if(fileId!='')
	{
		self.close();
	}
	else
	{
	parent.window.opener.change_parent_url();
	self.close();
	}
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>