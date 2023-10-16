<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="DrftAsCorrMsg"  method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="fileId" default="${param.fileId}"/>


<h3 align="center">
	Drafts are sent successfully as Correspondence to selected users
</h3>
<br><br>
<center><hdiits:button name="bt_save" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="window.close()"/></center>

<script type="text/javascript">
	function showOutgoingCorr(fileId)
	{
			window.close();
	}
</script>

</hdiits:form>