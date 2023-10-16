<%
try {
	
	
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="errorMsg" value="${resValue.errorMsg}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>

<script type="text/javascript">

var langId='${resValue.langId}';
var errorMsg='${resValue.error}'

alert(errorMsg);
window.close();
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>