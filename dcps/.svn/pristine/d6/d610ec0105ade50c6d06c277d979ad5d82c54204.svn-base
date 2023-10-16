<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="folderflag" value="${resValue.folderflag}"></c:set>


<hdiits:form name="newrefdoccrtfrm" method="post" validate="true">

<BR><BR><BR>
	
			<c:if test="${folderflag eq 'yes'}">
			<center><h3><fmt:message key="WF.RefDocCrtFolderMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			<c:if test="${folderflag ne 'yes'}">
			<center><h3><fmt:message key="WF.RefDocCrtDocMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="onlick_okbtn()"/></center>

</hdiits:form>

<script>
function onlick_okbtn()
{
	window.parent.location.href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RefDocs&moduleName=RefDocs&menuName=forRefDocs&fromHomePageOfRefDocs=true";
	//window.opener.parent.location.href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RefDocs&moduleName=RefDocs&menuName=forRefDocs&fromHomePageOfRefDocs=true";
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>