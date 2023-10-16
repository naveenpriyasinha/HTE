<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<body>
<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels"
	var="CapLabels" scope="request" />
	
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DesgnName" value="${resultValue.DesgnName}"></c:set>
<c:set var="parentPostName" value="${resultValue.parentPostName}"></c:set>
<c:set var="inchargePersonName" value="${resultValue.inchargePersonName}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<hdiits:form name="inchargePersonDtl" validate="true" encType="">

<table id="inchPsnDtl" border="1" width="90%" cellpadding="1"
		cellspacing="1" style="border-collapse: collapse; background-color:${tableBGColor}" border=1 borderColor="black" align="center">
		<tr bgcolor="#386CB7">
			<td align="center" colspan="3"><font color="#ffffff"> <strong><u>
			<fmt:message key="IC.personDtl" bundle="${CapLabels}" /> </u></strong> </font></td>
		</tr>
		
		<th align="center" style="font-weight: bold" class="datatableheader" style="background-color:${tdBGColor}">
			<hdiits:caption captionid="IC.inchrgName" bundle="${CapLabels}"></hdiits:caption></th>
		<th align="center" style="font-weight: bold" class="datatableheader" style="background-color:${tdBGColor}">
			<hdiits:caption captionid="IC.Designation" bundle="${CapLabels}" ></hdiits:caption></th>
		<th align="center" style="font-weight: bold"  class="datatableheader" style="background-color:${tdBGColor}">
			<hdiits:caption captionid="IC.postHeld" bundle="${CapLabels}"></hdiits:caption></th>
		<tr>
		<td align="center"><c:out value="${inchargePersonName}"></c:out></td><td align="center"><c:out value="${DesgnName}"></c:out></td><td align="center"><c:out value="${parentPostName}"></c:out></td>
		</tr>
	</table>

<br>
<br>
<center><hdiits:button name="CloseButton" type="button" onclick="javascript:window.close();" captionid="IC.close" bundle="${CapLabels}"/></center>

</hdiits:form>
</body>
</html>
<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>