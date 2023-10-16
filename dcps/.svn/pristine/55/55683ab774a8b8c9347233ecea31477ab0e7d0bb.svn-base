<%@ include file="../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<head>
	<script type="text/javascript">
		var PARTY_PARTYNAME = "<fmt:message key="PARTY.PARTYNAME" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYADD = "<fmt:message key="PARTY.PARTYADD" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYACCNO = "<fmt:message key="PARTY.PARTYACCNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
	   	var PARTY_PARTYCHQNO = "<fmt:message key="PARTY.PARTYCHQNO" bundle="${onlinebillprepAlerts}"></fmt:message>";	
	   	var PARTY_CHQAMTVAL = "<fmt:message key="PARTY.CHQAMTVAL" bundle="${onlinebillprepAlerts}"></fmt:message>";	
	   	RowNum=0;
	</script>
</head>

<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	
	<input type="hidden" name="hidPartiCounter" value="-1"/>
	
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> <fmt:message key="PARTY.INFO" bundle="${onlinebillprepLabels}"></fmt:message> </b> </legend>
		<table id="TableParty" align="center" width="100%" border="1" bordercolor="#eed0aa" cellspacing="0">
			<tr>
				<hdiits:td colspan="5">
				</hdiits:td>
			</tr>
			<c:if test="${resValue.subjectId == 9 || resValue.subjectId == 10 || resValue.subjectId == 11 || resValue.subjectId == 44}">
				<tr>
					<td colspan="5">
					Mode Of Payment :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select id="paymentMode" name="paymentMode" onchange="validatePaymentMode();" disabled="disabled">
							<option value="ECS">ECS/N.EFT</option>
							<option value="Cheque">Cheque</option>
							<option value="Cash">Cash</option>
						</select>
					</td>
				</tr>
				<c:if test="${resValue.TrnBillRegister.billNo == null}">
				<script>
				 document.getElementById("paymentMode").disabled=false;
				</script>
				</c:if>
			</c:if>
			<tr class="datatableheader" >				
				<td align="center" width="30%">  
					<fmt:message key="PARTY.NAME" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
				<td align="center" width="25%">
					<fmt:message key="PARTY.ADDRESS" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<td align="center" width="15%">
					<fmt:message key="PARTY.ACCOUNT.NO" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<td align="center" class="HLabel" width="15%">
					<fmt:message key="PARTY.MICR_CODE" bundle="${onlinebillprepLabels}"></fmt:message>
				</td>
				<td align="center" width="15%">
					<fmt:message key="PARTY.CHEQUE.AMOUNT" bundle="${onlinebillprepLabels}"></fmt:message>&nbsp;&nbsp;<label class="mandatoryindicator">*</label>
				</td>
			</tr>	
	
			<c:forEach var="rltBillParty" items="${resValue.RltBillParty}" varStatus="No">
				<script> ++RowNum; </script>
				<tr class="odd">
					<td>
						<input type="text" size="50" name="txtPartyName" value="${rltBillParty.partyName}" tabindex="43"  ${varDisabled}/>
					</td>
					<td>
						<input type="text" size="40" name="txtAddress" value="${rltBillParty.partyAddr}" tabindex="44" ${varDisabled}/>
					</td>
					<td>
						<input type="text" name="txtAccountNo" onkeypress="NumberFormet()" value="${rltBillParty.accntNo}" tabindex="45" ${varDisabled}/>
					</td>
					<td>
						<input type="text" name="txtMicrCode" onkeypress="NumberFormet()" value="${rltBillParty.micrCode}" tabindex="45" ${varDisabled}/>
					</td>
					<td>
						<fmt:formatNumber pattern="0.00" var="PartyAmt" value="${rltBillParty.partyAmt}" ></fmt:formatNumber>
						<input type="text" name="txtChkAmt" id="txtChkAmt" value="${PartyAmt}" tabindex="46"  ${varDisabled} style="text-align: right;" />
					</td>
					<td>
						<input type="hidden" name="cmbBankCode" value="${rltBillParty.bankCode}" />
						<input type="hidden" name="txtBranchCode" value="${rltBillParty.branchCode}" />
					</td>
				</tr>
				<c:if test="${rltBillParty.paymentMode != null &&  rltBillParty.paymentMode != '' && (resValue.subjectId == 9 || resValue.subjectId == 10 || resValue.subjectId == 11 || resValue.subjectId == 44) }">
					<script>
					
						if(document.getElementById('paymentMode') && RowNum == 1)
						{
							if('${rltBillParty.paymentMode}'=='ECS')
								document.getElementById('paymentMode').options[0].selected = true;
							else if('${rltBillParty.paymentMode}'=='Cheque')
								document.getElementById('paymentMode').options[1].selected = true;
							else
								document.getElementById('paymentMode').options[2].selected = true;
						}
					</script>
			   </c:if>
			
			</c:forEach>
		</table>
	</fieldset>
	