<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>

<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables"
		scope="request" />

<%@page session="true"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>

<fmt:setBundle basename="resources.ess.MRB.MRBLables" var="cmnLables" scope="request" />
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="reqNo" value="${resValue.ReqNo}">
</c:set>
<script type="text/javascript">

function showEmpName(){
document.frmVac.action="hdiits.htm?actionFlag=getHomePage";
document.frmVac.method="POST";
document.frmVac.submit();

}
<html>

</script>

<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >


<head>

</head>
<body>
<br><br><br><br><br><br><br><br>
<table bgcolor="WHITE" align="center" >
<tr align="center">
<td>
<FONT color="blue" >

<hdiits:caption captionid="MRB.RequestSub" bundle="${cmnLables}"/>&nbsp; &nbsp;<u><c:out value="${reqNo}"></c:out></u>&nbsp; &nbsp;<hdiits:caption captionid="MRB.HasApprove" bundle="${cmnLables}"/>       

</td>
</tr>

</table>
<table align="center" height="67%">

<tr>
<td><input align="center" type="button" value="OK"  onclick="showEmpName();"/></td>
</tr>

<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
</table>

</body>
</hdiits:form>

<%
}
catch(Exception ex) 
{
	//System.out.println("Error in JSPs"+ex);
}
%>




