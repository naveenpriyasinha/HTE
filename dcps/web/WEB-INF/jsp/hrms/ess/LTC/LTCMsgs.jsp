<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">

</c:set>
<fmt:setBundle basename="resources.ess.ltc.AlertMessages"
	var="alertLables" scope="request" />

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<table width="100%" align="center"
	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="right"><b><font color="red">*</font></b> <b><fmt:message
			key="HR.REQTYPE" bundle="${alertLables}" /> :</b></td>
		<c:if test="${resultValue.reqType ne '0'}">
			<td align="left"><fmt:message key="HR.ADV"
				bundle="${alertLables}" />
		</c:if>
		<c:if test="${resultValue.reqType ne '1'}">
			<td align="left"><fmt:message key="HR.REIM"
				bundle="${alertLables}" /></td>
		</c:if>

	</tr>
	<tr align="center">
		<td align="right"><b><font color="red">*</font><fmt:message
			key="HR.REQNO" bundle="${alertLables}" /> :</b></td>
		<td align="left">${resultValue.msg}</td>
	<tr>
		<td align="right"><b><font color="red">*</font></b> <b><fmt:message
			key="HR.Status" bundle="${alertLables}" /> :</b></td>

		<td align="left"><fmt:message key="HR.Pending"
			bundle="${alertLables}" /></td>
	</tr>



</table>
