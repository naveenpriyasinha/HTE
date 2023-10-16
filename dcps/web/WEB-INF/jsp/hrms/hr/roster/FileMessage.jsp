<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<fmt:setBundle basename="resources.hr.roster.RosterAlertMsges" var="alertMsges" scope="request" />
<script type="text/javascript" src="script/hrms/hr/rosterScript/fileMessage.js">
</script>
<script type="text/javascript">
	
</script>
<html>
	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
   <c:set var="ReqId" value="${resValue.Pkvalue}"></c:set>
  
    <c:set var="fwdToUser" value="${resValue.fwdTo}"></c:set>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">

<table id="Message" width="40%" align="center" >
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr align="center">

<td>  
<b> <fmt:message key="HRMS.FileMsgStart" bundle="${alertMsges}" /> ${ReqId} <fmt:message key="HRMS.FileMsgMid" bundle="${alertMsges}" /> ${fwdToUser} <fmt:message key="HRMS.FileMsgEnd" bundle="${alertMsges}" /></b>

<br>
<br>
<hdiits:button type="button"
		name="closeButton" value="OK" id="closeButton" captionid="Close"
		onclick="closewindow()" style="width:120px" />
</td>
</tr>
</table>
</hdiits:form>
</body>
</html>
 <%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
  	  %>