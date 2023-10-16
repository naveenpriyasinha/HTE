<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:form name="insertAltFlowMstfrm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<table width="100%">
<tr>
</tr>

<tr>
<td align="justify">

<center>
<b>
<fmt:message key="WF.ActionName" bundle="${fmsLables}"> </fmt:message>
</b>

<hdiits:text name="actionName"  size="50"/>
</center>
</td>
</table>
<br>
<center>
<hdiits:button name="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>
<hdiits:button name="okbtn" captionid="WF.Ok" bundle="${fmsLables}" type="button" onclick="insertDetail()"/>				
<center> 
</hdiits:form>
<script>
function insertDetail()
{
	
	window.document.forms[0].method="post";
	window.document.forms[0].action="hdiits.htm?actionFlag=fms_insertalternateflowMasterDetails";
	window.document.forms[0].submit();
	
}
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%> 