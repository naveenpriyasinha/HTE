

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="script/gpf/finalWithdrawal.js"></script>

<script language="javascript">
function init(){
	var accBalance = document.getElementById("hidNetBalance").value;
	var finalAmount = accBalance;
	document.getElementById("txtFinalAmount").value = finalAmount;
	document.getElementById("txtSancAmountFW").value = finalAmount;
	document.getElementById("txtOutstandingBalanceFW").value = accBalance-finalAmount;
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TransactionID" value="${resValue.TransactionID}"/>
<c:set var="finalWithDrawal" value="${resValue.finalWithDrawal}"/>
<c:set var="empVO" value="${resValue.GPFEmpVO}" />
<c:set var="TillDateCredit" value="${resValue.TillDateCredit}"></c:set>
<c:set var="NetBalance" value="${resValue.OpeningBalance +TillDateCredit[0]+TillDateCredit[1]-resValue.AdvanceSanctioned-resValue.WithdrawalSanctioned}"></c:set>
<c:set var="FinYearCode" value="${resValue.FinYearCode}"></c:set>
<c:set var="OrderNo" value="${resValue.OrderNo}"></c:set>
<c:if test="${resValue.userType == 'DEO' || resValue.userType == 'DEOAPP'}">
	<c:set var="varDisabledDEO" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'DEOAPP' || resValue.userType == 'HO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
</c:if>
<c:if test="${resValue.userType == 'HO'}">
	<c:set var="varRemarksDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
</c:if>
<c:choose>
	<c:when test="${finalWithDrawal.transactionId!=null}">
		<input type="hidden"  size="20%" name='txtTIDFW' id="txtTIDFW" value="${finalWithDrawal.transactionId}" />
	</c:when>
	<c:otherwise>
		<input type="hidden"  size="20%" name='txtTIDFW' id="txtTIDFW" value="${TransactionID}" />
	</c:otherwise>
</c:choose>
<input type="hidden"  size="20%" name='hidFinalEligibility' id="hidFinalEligibility" value="${resValue.FinalEligibility}" />
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<table width="100%">
<tr ><td >
<table width="70%" border="1" cellspacing="0" align="center" >
<tr>
	<td colspan="2" class="datatableheader" ><fmt:message key="MSG.BALANCE" bundle="${gpfLabels}"></fmt:message></td>
</tr>
<tr>
		<td width="30%" align="left" ></td>
		<td width="8%" align="right"><fmt:message key="CMN.RS" bundle="${gpfLabels}"></fmt:message></td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.OPENBALANCE" bundle="${gpfLabels}"></fmt:message>${FinYearCode}</td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${resValue.OpeningBalance}" />&nbsp;</td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.REGULARSUB" bundle="${gpfLabels}"></fmt:message></td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${TillDateCredit[0]}" />&nbsp;</td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.SANCADVANCE" bundle="${gpfLabels}"></fmt:message></td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${resValue.AdvanceSanctioned}" />&nbsp;</td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.ADRECOVERY" bundle="${gpfLabels}"></fmt:message></td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${TillDateCredit[1]}" />&nbsp;</td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.SANCWITHDRAWAL" bundle="${gpfLabels}"></fmt:message></td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${resValue.WithdrawalSanctioned}" />&nbsp;</td>
</tr>
<tr>
		<td width="30%" align="left" ><fmt:message key="CMN.NETBALANCE" bundle="${gpfLabels}"></fmt:message></td>
		<td width="8%" align="right"><fmt:formatNumber type="number" 
            pattern="##,##,###.##" value="${NetBalance}" />&nbsp;</td>
</tr>

</table>
</td></tr>
<tr><td><table width="70%" align="center"><tr>
		<td align="center" >
		<hdiits:button type="button" captionid="BTN.WITHDRAWALHIS" bundle="${gpfLables}" id="btnWithdrawFW" name="btnWithdrawFW" onclick="window_new_update('ifms.htm?actionFlag=loadWithdrawalDetailList&gpfAccNo=${empVO[6]}&advanceType=NRA');" style="width:180px"></hdiits:button>
		</td>
		<td align="left" >
		<hdiits:button type="button" captionid="BTN.ADVANCEHIS" bundle="${gpfLables}" id="btnAdvanceFW" name="btnAdvanceFW" onclick="window_new_update('ifms.htm?actionFlag=loadAdvanceDetailList&gpfAccNo=${empVO[6]}&advanceType=RA');" style="width:180px"></hdiits:button>
		</td>
</tr></table></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>
<br/>
	<input type="hidden" name="hidFinalWithdrawalID" id="hidFinalWithdrawalID" value="${finalWithDrawal.trnGpfFinalWithdrawalId}" />
<br/>
<fieldset ><legend><fmt:message key="CMN.ADVANCEDETAIL" bundle="${gpfLabels}"></fmt:message></legend></br>
<table cellspacing = "4" cellpadding = "4" >
<tr>

		<td width="15%" align="left" ><fmt:message key="CMN.SYSENTRYDATE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSysDateFW' id="txtSysDateFW" maxlength="10" readonly="readonly" value="${resValue.lDtCurrDate}" />
			</td>
		<td width="15%" > <fmt:message key="CMN.FINALAMOUNT" bundle="${gpfLabels}"></fmt:message> </td>
		<td width="20%" align="left">
		<fmt:parseNumber var="varFinalAmount"   value="${finalWithDrawal.finalAmount}" type="number"/>
			<input type="text"  size="20%" name='txtFinalAmount' id="txtFinalAmount" value="${varFinalAmount}" style="text-align: right;" readOnly="readOnly" ${varDisabled} />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
</tr>
<tr>

		<td width="15%" align="left" > <fmt:message key="CMN.PHYAPPDATE" bundle="${gpfLabels}"></fmt:message> 
		<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${gpfLabels}" ></fmt:message></font></td>
		<td width="20%" align="left">		
			<fmt:formatDate value="${finalWithDrawal.applicationDate}" pattern="dd/MM/yyyy" var="appDate" />
			<input type="text" name="txtAppDateFW" id="txtAppDateFW" maxlength="10" value="${appDate}" onBlur="validateDate(this);previousDateValidation(this);" onkeypress="digitFormat(this);dateFormat(this);" ${varDisabled} /> 
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtAppDateFW",375,570)' style="cursor: pointer;" ${varImageDisabled} />
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%" rowspan="2"> <fmt:message key="CMN.REMARKS" bundle="${gpfLabels}"></fmt:message> </td>
		<td width="20%" align="left" rowspan="2">
			<textarea NAME="textareaUserRemarks" id="textareaUserRemarks" ROWS="3" cols="25" ${varDisabled} ><c:out value="${finalWithDrawal.userRemarks}"/></textarea>			
		</td>
</tr>
<tr>

		<td width="15%" align="left" > <fmt:message key="CMN.FINALREASON" bundle="${gpfLabels}"></fmt:message> </td>
		<td width="20%" align="left">		
			<select name="cmblstReason" id="cmblstReason" style="width:200px" ${varDisabled} >
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="Reason" items="${resValue.lstFinalReasons}">
					<c:choose>
						<c:when test="${finalWithDrawal.reason == Reason.lookupId}">
							<option value="${Reason.lookupId}" selected="selected"><c:out
								value="${Reason.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${Reason.lookupId}"><c:out
								value="${Reason.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		
</tr>
<tr>
		<td colspan="4">
			<jsp:include page="/WEB-INF/jsp/gpf/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="Proof2" />
				<jsp:param name="formName" value="GPFRequestProcessForm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="N" />				
				<jsp:param name="readOnly" value="${varAttachmentDisabled}" />
			</jsp:include>
			</td>
		</tr>
</table>
</fieldset>
</td></tr>
<tr><td>&nbsp;</td></tr>

<tr style="display:none"><td>
<fieldset ><legend><fmt:message key="CMN.ELIGIBILITYDTLS" bundle="${gpfLabels}"></fmt:message></legend><br>
<table align="left">

<tr>
	<td align="left" width="10%"><fmt:message key="CMN.ELIGIBLEAMT" bundle="${gpfLabels}"></fmt:message>	
	</td><td width="20%" align="left">
	<input type="text"  name='txtEligibleAmtFW' id="txtEligibleAmtFW" maxlength="15" readonly="readonly" value="" onchange=""/>
	</td>
	<td align="left">
	<hdiits:button type="button" captionid="BTN.CHKELIGIBILITY" bundle="${gpfLables}" id="btnChkEligibilityFW" name="btnChkEligibilityFW" onclick="checkEligibleAmountFW();" style="width:150px"></hdiits:button>
	</td>
</tr>
</table>
</fieldset>
</td></tr>
<tr><td>
<fieldset ><legend><fmt:message key="CMN.SANCDTLS" bundle="${gpfLabels}"></fmt:message></legend></br>
<table align="left" cellspacing = "4" cellpadding = "4">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varSancAmountFW"   value="${finalWithDrawal.amountSanctioned}" type="number"/>
			<input type="text"  name='txtSancAmountFW' id="txtSancAmountFW" style="text-align: right" value="${varSancAmountFW}" onblur="validateSancAmountFW();" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.ACCBALANCE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varOutstandingBalFW"   value="${finalWithDrawal.balanceOutstanding}" type="number"/>
			<input type="text"  name='txtOutstandingBalanceFW' id="txtOutstandingBalanceFW" style="text-align: right" value="${varOutstandingBalFW}" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
		</tr>
</table>
</fieldset>
</td></tr><tr><td>
<c:if test="${resValue.userType != 'DEO' && resValue.requestType == 'FW'}">
<table>
<tr>
<td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${gpfLabels}"></fmt:message></td>
<td width="20%"><textarea NAME="txtApproverRemarks" id="txtApproverRemarks" ROWS="2" cols="50" ${varRemarksDisabled}>${finalWithDrawal.approverRemarks}</textarea>
</td>
</tr></table>
</c:if>
</td></tr>
<tr><td>
<c:choose>
	<c:when test="${resValue.userType == 'HO' && resValue.requestType == 'FW'}">
		<fieldset ><legend><fmt:message key="CMN.ORDERDTLS" bundle="${gpfLabels}"></fmt:message></legend></br>
		<table>
			<tr>
			<td width="15%"><fmt:message key="CMN.ORDERNO" bundle="${gpfLabels}"></fmt:message></td>
			<td width="20%"><input type="text" id="txtOrderNo"	name="txtOrderNo" value="${OrderNo}" disabled='disabled'/>
				<label class="mandatoryindicator" >*</label>
			</td>
			<td width="15%"><fmt:message key="CMN.ORDERDATE" bundle="${gpfLabels}"></fmt:message></td>
			<td width="20%"><input type="text" id="txtOrderDate" name="txtOrderDate"maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"onBlur="validateDate(this);"/>
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtOrderDate",375,570)' style="cursor: pointer;" />
				<label class="mandatoryindicator">*</label></td>
			</tr>
			<tr>
				<td width="15%"><fmt:message key="CMN.APPREMARKS" bundle="${gpfLabels}"></fmt:message></td>
				<td colspan="3"><input type="text" id="txtHORemarks" size="85" name="txtHORemarks" />
					</td>					
			</tr>
			</table>
		</br>
		</fieldset>
	</c:when>	
	</c:choose>
	</td></tr>
</table>
<br/>
<script>
   init();
</script>