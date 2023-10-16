<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<fmt:setBundle basename="resources.hr.gradationList.gradationListLables" var="gradationLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dsgn" value="${resValue.designation}"></c:set>
<c:set var="loc" value="${resValue.location}"></c:set>


<script type="text/javascript">

	function closewindow()
	{		
		var urlstyle="hdiits.htm?actionFlag=getGLCombo"
		document.frm1.action=urlstyle;
		document.frm1.submit();
	}
	
</script>

<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			
	<table id="Message" width="40%" align="center" border="1">	
		<tr align="center">
			<td width="100%"><b><hdiits:caption captionid="HRMS.SUBMIT" bundle="${gradationLables}"/></b></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.DESIGNATION" bundle="${gradationLables}"/>: &nbsp; <c:out value="${dsgn}"></c:out> </td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.LOCATION" bundle="${gradationLables}"/>: &nbsp; <c:out value="${loc}"></c:out></td>
		</tr>
		
	</table>
	
	<table align="center">				
		<tr align="center">
			<td align="center"><hdiits:button type="button" name="closeButton" captionid="HRMS.BACK" bundle="${gradationLables}" value="Back" id="closeButton" onclick="closewindow()"/></td>
		</tr>
	</table>
</hdiits:form>

 <%
}catch(Exception e)
{
  		  
   e.printStackTrace();
}
%>