<%@ include file="../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
		.tabstyle 
		{
			border-width: 5px 1px 1px 1px; 
			border-color: #2065c0;
			border-style: solid;
		}
		legend 
		{
			padding-left:5px;
			padding-right:5px;
			font-weight:normal; 
			font-family:verdana;
				
			border-width: 0px 0px 1px 0px; 
			border-color: #2065c0;
			border-style: solid;
		}	
	</style>
	<script type="text/javascript">
		var PARTY_PARTYNAME = "<fmt:message key="PARTY.PARTYNAME" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYADD = "<fmt:message key="PARTY.PARTYADD" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYACCNO = "<fmt:message key="PARTY.PARTYACCNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYCHQNO = "<fmt:message key="PARTY.PARTYCHQNO" bundle="${onlinebillprepAlerts}"></fmt:message>";	
	</script>
</head>
	
	<input type="hidden" name="hidPartiCounter" value="-1"/>
	
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> <fmt:message key="PARTY.INFO" bundle="${onlinebillprepLabels}"></fmt:message> </b> </legend>
		<table id="TableParty" align="center" width="100%">
			<tr>
				<hdiits:td colspan="5">
				</hdiits:td>
			</tr>
			<hdiits:tr>
				<hdiits:td colspan="5">
					 <c:choose>
						<c:when test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">
							<hdiits:button name="butAddRow" type="button" value="Add Row" onclick="AddPartyRow()"/>
						</c:when>
						<c:when test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
						</c:when>
						<c:otherwise>
							<hdiits:button name="butAddRow" type="button" value="Add Row" onclick="AddPartyRow()"/>
						</c:otherwise>
					 </c:choose>
				</hdiits:td> 
			</hdiits:tr>
			<tr class="datatableheader">
				<td align="center" width="25%" class="HLabel">
					<fmt:message key="PARTY.NAME" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
				<td align="center" class="HLabel" width="25%">
					<fmt:message key="PARTY.ADDRESS" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
				<td align="center" class="HLabel" width="25%">
					<fmt:message key="PARTY.ACCOUNT.NO" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
				<td align="center" class="HLabel" width="25%">
					<fmt:message key="PARTY.CHEQUE.AMOUNT" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
			</tr>	
	
			<c:choose>
				<c:when test="${resValue.EditBill != null && resValue.EditBill == 'Y'}">
					<c:forEach var="rltBillParty" items="${resValue.RltBillParty}" varStatus="No">
						<script> ++RowNum; </script>
						<tr class="odd">
							<td>
								<input type="text" name="txtPartyName${No.index}" value="${rltBillParty.partyName}" />
								<input type="hidden" name="partyCode${No.index}" />
								<img src="images/party.gif" onclick="ShowAllParty(${No.index})" />
							</td>
							<td>
								<input type="text" name="txtAddress${No.index}" value="${rltBillParty.partyAddr}"/>
							</td>
							<td>
								<input type="text" name="txtAccountNo${No.index}" onkeypress="NumberFormet()" value="${rltBillParty.accntNo}"/>
							</td>
							<td>
								<input type="text" name="txtChkAmt${No.index}" onkeypress="amountFormat(this)" onblur="setValidAmountFormat(this)" value="${rltBillParty.partyAmt}"/>
								<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemovePartyRow(this, 'TableParty')" />
							</td>
						</tr>
					</c:forEach>	
				</c:when>
				<c:when test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
					<c:forEach var="rltBillParty" items="${resValue.RltBillParty}" varStatus="No">
						<script> ++RowNum; </script>
						<tr class="odd">
							<td>
								<input type="text" name="txtPartyName${No.index}" value="${rltBillParty.partyName}" disabled="true" />
								<input type="hidden" name="partyCode${No.index}" />
							</td>
							<td>
								<input type="text" name="txtAddress${No.index}" value="${rltBillParty.partyAddr}" disabled="true"/>
							</td>
							<td>
								<input type="text" name="txtAccountNo${No.index}" onkeypress="NumberFormet()" value="${rltBillParty.accntNo}" disabled="true"/>
							</td>
							<td>
								<input type="text" name="txtChkAmt${No.index}" onkeypress="amountFormat(this)" onblur="setValidAmountFormat(this)" value="${rltBillParty.partyAmt}" disabled="true"/>
								<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemovePartyRow(this, 'TableParty')" />
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
	</fieldset>
	