<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="DisposeCorr"  method="POST" action="./hdiits.htm" validate="true">
<hdiits:hidden name="fileId" id="hid_fileId" default="${resValue.fileId}"/>
	<h3 align="center">
		<fmt:message key="WF.DSPMSGDP" bundle="${fmsLables}"></fmt:message>
	</h3>

	<br><br>
	<center><hdiits:button  name="bt_Ok" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="showTappalSide()"/></center>
	
</hdiits:form>
 <script>
  function showTappalSide()
	{
			//alert(currenttabid);
			var fileId = document.getElementById('hid_fileId').value;
			window.opener.parent.frames["Target_frame"].document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=showaddedCorrespondence&corrCriteria=Incoming&fileId="+fileId;
			window.opener.parent.frames["Target_frame"].document.forms[0].method = "post";
			window.opener.parent.frames["Target_frame"].document.forms[0].submit();
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