<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="DspMsgRefDocsRem"  method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="fileId" default="${param.fileId}"/>


<h3 align="center">
	Selected Reference Docs are removed
</h3>
<br><br>
<center><hdiits:button name="bt_save" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="showRefDocs()"/></center>

<script type="text/javascript">
	function showRefDocs()
	{
		
			document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=showCorrAttach&fileflag=fromFile";
			document.forms[0].method='post';
			document.forms[0].submit();
	}
</script>

</hdiits:form>