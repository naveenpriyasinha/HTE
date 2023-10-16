<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="callDate" value="${resValue.callDate}" />
<c:set var="callTime" value="${resValue.callTime}" />
<c:set var="slotId" value="${resValue.slotId}" />

<script type="text/javascript">
function insertScheduledAppointment()
{
	document.getElementById("PensionerDtls").action = "ifms.htm?actionFlag=insertScheduledAppointment";
	document.getElementById("PensionerDtls").submit();
}


</script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US"
	var="pensionLabels" scope="request" />
<hdiits:form name="PensionerDtls" id="PensionerDtls"
	encType="multipart/form-data" validate="true" method="post">

	<table width="90%" align="center">
		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.PPOID"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtPPOId'
				id="txtPPOId" value="123456" style="text-align: left" size="30" /></td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message
				key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtPensionerName' id="txtPensionerName" value="abc"
				style="text-align: left" size="30" /></td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.CALLDATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtCallDate' id="txtCallDate" value="${callDate}"
				style="text-align: left" size="30" /></td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.CALLTIME"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name='txtCallTime' id="txtCallTime" value="${callTime}"
				style="text-align: left" size="30" /></td>

		</tr>
		<tr>
			<td><input type="hidden" name='txtSlotId' id="txtSlotId"
				value="${slotId}" /></td>
		</tr>
		<tr>
			<td width="100%" align="center"><hdiits:button
				name="btnSaveData" id="btnSaveData" type="button"
				captionid="PPMTBTN.CONFIRM" bundle="${pensionLabels}"
				onclick="insertScheduledAppointment();" /></td>
		</tr>
	</table>
</hdiits:form>