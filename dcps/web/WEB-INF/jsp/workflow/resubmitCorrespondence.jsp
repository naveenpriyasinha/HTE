<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="wfWindowName" value="${resValue.wfWindowName}"></c:set>
<c:set var="corrId" value="${resValue.corrId}"></c:set>
<script>
		
		var wfwinname='${wfWindowName}'
		if('${corrId}'!='')
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RecordRoom&Show=ShowApproveCorr",wfwinname);				
		else
			win=window.open("${contextPath}/hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RecordRoom",wfwinname);
		
		win.focus();
		self.close();

</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>