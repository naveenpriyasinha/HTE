<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/JavaScript" src="script/gpf/refundableAdvance.js"></script>
<script language="javascript">
function init(){
	var basicPay = document.getElementById("hidBasicPay").value;
	var accBalance = document.getElementById("hidNetBalance").value;

	var totalBasicPay = Number(basicPay)*3;
	var halfBalance = Number(accBalance)/2; 
	
	if(Number(totalBasicPay)<Number(halfBalance)){
		document.getElementById("txtEligibleAmt").value = totalBasicPay;
	}
	else{
		document.getElementById("txtEligibleAmt").value = halfBalance;
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empVO" value="${resValue.GPFEmpVO}"/>
<c:set var="lstPurposeCat" value="${resValue.lstPurposeCat}"></c:set>
<c:set var="RAdvanceVO" value="${resValue.savedAdvanceRA}"></c:set>
<c:set var="AdvanceNotRecovered" value="${resValue.AdvanceNotRecovered}"></c:set>
<c:set var="TillDateCredit" value="${resValue.TillDateCredit}"></c:set>
<c:set var="NetBalance" value="${resValue.OpeningBalance +TillDateCredit[0]+TillDateCredit[1]-resValue.AdvanceSanctioned-resValue.WithdrawalSanctioned}"></c:set>
<c:set var="FinYearCode" value="${resValue.FinYearCode}"></c:set>
<c:set var="OrderNo" value="${resValue.OrderNo}"></c:set>
<c:if test="${resValue.userType == 'DEOAPP' || resValue.userType == 'HO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
</c:if>
<c:if test="${resValue.userType == 'DEO' || resValue.userType == 'DEOAPP'}">
	<c:set var="varDisabledDEO" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.savedAdvanceRA == null || RAdvanceVO.purposeCategory != '800009'}">
	<c:set var="disableOther" value="readonly='readonly'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'HO'}">
	<c:set var="varRemarksDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'HO' || resValue.userType == 'DEOAPP' || RAdvanceVO.clubbedReqTrnId != null}">
	<c:set var="varChecked" value="checked='checked'"></c:set>
</c:if>
<c:choose>
	<c:when test="${resValue.AdvanceNotRecovered == null || AdvanceNotRecovered[0] == null || AdvanceNotRecovered[1] == null}">
		<c:set var="disableClubbing" value="display:none"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="disableClubbing" value=""></c:set>
		<c:set var="clubbingChecked" value="checked='checked'"></c:set>
	</c:otherwise>
</c:choose>
<c:if test="${RAdvanceVO.specialCase == 'Y'}">
	<c:set var="specialCaseChecked" value="checked='checked'"></c:set>
</c:if>
<input type="hidden" name="hidProofUrl" id="hidProofUrl" >
<input type="hidden" name="attachmentName1" id="attachmentName1" >
<input type="hidden" name='hidPKValueRA' id="hidPKValueRA"	value="${RAdvanceVO.mstGpfAdvanceId}" />
<c:choose>
	<c:when test="${RAdvanceVO != null}">
		<input type="hidden" size="20%" name='hidTIDRA' id="hidTIDRA"	value="${RAdvanceVO.transactionId}" />
	</c:when>
	<c:otherwise>
		<input type="hidden" size="20%" name='hidTIDRA' id="hidTIDRA"	value="${resValue.TransactionID}" />
	</c:otherwise>
</c:choose>

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
		<hdiits:button type="button" captionid="BTN.WITHDRAWALHIS" bundle="${gpfLables}" id="btnWithdraw" name="btnWithdraw" onclick="window_new_update('ifms.htm?actionFlag=loadWithdrawalDetailList&gpfAccNo=${empVO[6]}&advanceType=NRA');" style="width:180px"></hdiits:button>
		</td>
		<td align="left" >
		<hdiits:button type="button" captionid="BTN.ADVANCEHIS" bundle="${gpfLables}" id="btnAdvance" name="btnAdvance" onclick="window_new_update('ifms.htm?actionFlag=loadAdvanceDetailList&gpfAccNo=${empVO[6]}&advanceType=RA');" style="width:180px"></hdiits:button>
		</td>
</tr></table></td>
</tr>
<tr><td>&nbsp;</td></tr>

<tr><td>
<fieldset ><legend><fmt:message key="CMN.ADVANCEDETAIL" bundle="${gpfLabels}"></fmt:message></legend></br>
<table align="left" cellpadding="4">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SYSENTRYDATE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="25%" align="left">
			<input type="text"  name='txtSysDate' id="txtSysDate" maxlength="10" readonly="readonly" value="${resValue.lDtCurrDate}" />
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.ELIGIBLEAMT" bundle="${gpfLabels}"></fmt:message></td>
			<td align="left" width="20%">
			<input type="text"  name='txtEligibleAmt' id="txtEligibleAmt" maxlength="15" readonly="readonly" style="text-align: right"/>
			</td>
			
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.PHYAPPDATE" bundle="${gpfLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${gpfLabels}" ></fmt:message></font></td>
			<td width="25%">
			<fmt:formatDate value="${RAdvanceVO.applicationDate}" pattern="dd/MM/yyyy" var="appDate"/>
			<input type="text" name="txtAppDateRA" id="txtAppDateRA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);previousDateValidation(this);" value = "${appDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtAppDateRA",375,570)' style="cursor: pointer;"  ${varImageDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label></td>
		<td width="15%" align="left" ><fmt:message key="CMN.REQAMOUNT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
		<fmt:parseNumber var="varAdvanceAmount"   value="${RAdvanceVO.advanceAmt}"  type="number"/>
			<input type="text"  size="20%" name='txtAdvanceAmount' id="txtAdvanceAmount" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" onblur="popupSancDetails();enableClubbing();" value="${varAdvanceAmount}"  ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.SPECIALCASE" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="25%">
		<input type="checkbox" id="cbSpecialCase" name="cbSpecialCase" onclick="validateNoOfInstallment();" ${specialCaseChecked} ${varDisabled}/> 
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.NUMINSTALLMENT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtNoOfInstallment' id="txtNoOfInstallment" onkeypress="digitFormat(this);" onblur="validateNoOfInstallment()" maxlength="10" style="text-align: right" value="${RAdvanceVO.noOfInstallments }"  ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>

	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}"></fmt:message></td>
			<td align="left" width="25%">
			<select name="cmblstPurposeCategory" id="cmblstPurposeCategory" style="width:230px" ${varDisabled} onchange="actionOnOtherReason(this);">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="Purpose" items="${resValue.lstPurposeCat}">
					<c:choose>
						<c:when test="${RAdvanceVO.purposeCategory == Purpose.lookupId}">
							<option value="${Purpose.lookupId}" selected="selected"><c:out
								value="${Purpose.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${Purpose.lookupId}"><c:out
								value="${Purpose.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label></td>
			<td width="15%" align="left" rowspan="2" ><fmt:message key="CMN.REMARKS" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="15%" rowspan="2">
			<textarea NAME="textareaRemarks" id="textareaRemarks" ROWS="2" cols="25"  ${varDisabled}>${RAdvanceVO.userRemarks }</textarea>			
		</td>
	</tr>
	<tr id="trOtherPurpose"  >
		<td width="15%" align="left" ><fmt:message key="CMN.OTHERS" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="25%">
		<input type="text"  size="30%" name='textareaOther' id="textareaOther" maxlength="25" style="text-align: left" value="${RAdvanceVO.otherPurpose }" ${disableOther} ${varDisabled}/>
		<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</br>
		</td>
		
	</tr>
	
	<tr>
		<td colspan="4">
			<jsp:include
				page="/WEB-INF/jsp/gpf/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="Proof" />
				<jsp:param name="formName" value="GPFRequestProcessForm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="N" />
				<jsp:param name="readOnly" value="${varAttachmentDisabled}" />
			</jsp:include>
			</td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr style="${disableClubbing}">
	<td colspan="4">
	<b><fmt:message key="CMN.CLUBINSTALLMENT" bundle="${gpfLabels}"></fmt:message></b>
	<input type="checkbox" name="cbClubInstallment" id="cbClubInstallment" onclick="enableClubbing();"  ${varDisabled} />
	</td></tr>
	<tr style="${disableClubbing}"><td></td>
	<td colspan="2">
	<display:table list="${AdvanceNotRecovered}" pagesize="5"  id="voClubbing" requestURI=""cellpadding="4"  >

	<display:setProperty name="paging.banner.placement" value="none" />
	<display:setProperty name="basic.empty.showtable" value="true" />
	<display:setProperty name="basic.msg.empty_list_row" value="No Advance Requests Present to Club" />
				
		<display:column style="text-align: left;" class="tablecelltext"
		title="Transaction ID" headerClass="datatableheader"> 
		 <input type="text" name="txtClubbedTrnId" id="txtClubbedTrnId" value="${voClubbing[0]}" readOnly="readOnly" />
		</display:column>
		
		<display:column style="text-align: left;" class="tablecelltext"
		title="Outstanding Recovery" headerClass="datatableheader">
		<input type="text" id="txtOutstanding${voClubbing_rowNum}" value="${voClubbing[1]}" readOnly="readOnly" />
		</display:column>

	</display:table>
	
	</td>
	<td></td></tr>

</table>
</fieldset>
</td></tr>

<tr><td></td></tr>

<tr><td>
<fieldset ><legend><fmt:message key="CMN.SANCDTLS" bundle="${gpfLabels}"></fmt:message></legend></br>
<table align="left" >
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varSancAmount"   value="${RAdvanceVO.amountSanctioned}" type="number"/>
			<input type="text"  name='txtSancAmount' id="txtSancAmount" style="text-align: right" value="${varSancAmount}" onkeypress="amountFormat(this);" onblur="validateSancAmountRA();" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCINSTALLMENTS" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancInstallments' id="txtSancInstallments"  style="text-align: right" value="${RAdvanceVO.noOfInstallments}" onblur="digitFormat(this);populateSancDetailsHO();" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.INSTALLMENTAMT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varInstAmount"   value="${RAdvanceVO.installmentAmount}" type="number"/>
			<input type="text"  name='txtInstallmentAmount' id="txtInstallmentAmount" style="text-align: right" value="${varInstAmount}" onblur="digitFormat(this);" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.ODDINSTALLMENT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varOddInstAmount"   value="${RAdvanceVO.oddInstallment}" type="number"/>
			<input type="text"  name='txtOddInstallmentAmt' id="txtOddInstallmentAmt" style="text-align: right" value="${varOddInstAmount}" onblur="digitFormat(this);" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
			
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.PAYABLEAMT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varPayAmount"   value="${RAdvanceVO.payableAmount}" type="number"/>
			<input type="text"  name='txtPayableAmount' id="txtPayableAmount" style="text-align: right" value="${varPayAmount}" onblur="digitFormat(this);" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.ACCBALANCE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varOutstandingBal"   value="${RAdvanceVO.balance}" type="number"/>
			<input type="text"  name='txtOutstandingBalance' id="txtOutstandingBalance" style="text-align: right" value="${varOutstandingBal}" onblur="digitFormat(this);" ${varDisabledDEO}/>
			<label class="mandatoryindicator" ${varImageDisabledDEO}>*</label>
			</td>
		</tr>
		
</table>
</fieldset>
</td></tr>
<tr><td></br>
<c:if test="${resValue.userType != 'DEO' && resValue.requestType == 'RA'}">
<table><tr><td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${gpfLabels}"></fmt:message></td>
<td width="20%" ><textarea NAME="txtApproverRemarks" id="txtApproverRemarks" ROWS="2" cols="50" ${varRemarksDisabled}>${RAdvanceVO.verifierRemarks}</textarea>
</td>

</tr></table>
</c:if>
</td></tr>
<tr><td>
<c:choose>
	<c:when test="${resValue.userType == 'HO' && resValue.requestType == 'RA'}">
		<fieldset><legend><fmt:message key="CMN.ORDERDTLS" bundle="${gpfLabels}"></fmt:message></legend></br>
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
				<td colspan="3">	<textarea NAME="txtHORemarks" id="txtHORemarks" ROWS="2" cols="85"></textarea>
					</td>
			</tr>
		</table>
		</br>
		</fieldset>
	</c:when>	
	</c:choose>
	</td></tr>
</table>
<script>
    init();
</script>