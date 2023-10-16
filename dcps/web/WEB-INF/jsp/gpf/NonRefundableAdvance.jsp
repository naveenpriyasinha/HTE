<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/JavaScript" src="script/gpf/nonRefundableAdvance.js"></script>
<script language="javascript">
function init(){
	var basicPay = document.getElementById("hidBasicPay").value;
	var accBalance = document.getElementById("hidNetBalance").value;
	var totalAmount = Number(basicPay)*6;
	if(document.getElementById("hidFinalEligibility").value == "true"){
		document.getElementById("txtEligibleAmtNRA").value=accBalance*(0.9);
	}else if(Number(totalAmount)<Number(accBalance)){
		document.getElementById("txtEligibleAmtNRA").value = totalAmount;
	}
	else{
		document.getElementById("txtEligibleAmtNRA").value = Number(accBalance)/2;
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lstPurposeCat" value="${resValue.lstPurposeCat}"></c:set>
<c:set var="empVO" value="${resValue.GPFEmpVO}"/>
<c:set var="NRAdvanceVO" value="${resValue.savedAdvanceRA}"></c:set>
<c:set var="TillDateCredit" value="${resValue.TillDateCredit}"></c:set>
<c:set var="NetBalance" value="${resValue.OpeningBalance +TillDateCredit[0]+TillDateCredit[1]-resValue.AdvanceSanctioned-resValue.WithdrawalSanctioned}"></c:set>
<c:set var="FinYearCode" value="${resValue.FinYearCode}"></c:set>
<c:set var="OrderNo" value="${resValue.OrderNo}"></c:set>
<c:if test="${resValue.userType == 'DEOAPP' || resValue.userType == 'HO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
</c:if>
<c:if test="${resValue.userType != 'HO'}">
	<c:set var="varDisabledInst" value="readOnly='readOnly'"></c:set>
	<c:set var="varImagevarDisabledInst" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'DEOAPP'}">
	<c:set var="varDisabledDEOApp" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEOApp" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == 'HO'}">
	<c:set var="varRemarksDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.savedAdvanceRA == null || NRAdvanceVO.purposeCategory != '800026'}">
	<c:set var="disableOther" value="readonly='readonly'"></c:set>
</c:if>
<c:if test="${resValue.savedAdvanceRA == null || (NRAdvanceVO.purposeCategory != '800024' && NRAdvanceVO.purposeCategory != '800025')}">
	<c:set var="disableSubType" value="disabled='disabled'"></c:set>
</c:if>
<c:if test="${(resValue.savedAdvanceRA != null && NRAdvanceVO.purposeCategory != '800024')}">
		<c:set var="varDisabledReleaseInst" value="style='display:none'"></c:set>
</c:if>
<c:choose>
	<c:when test="${NRAdvanceVO != null}">
		<input type="hidden"  name='hidTIDNRA' id="hidTIDNRA" maxlength="10" readonly="readonly" value="${NRAdvanceVO.transactionId}" ${varDisabled}/>
	</c:when>
	<c:otherwise>
		<input type="hidden"  name='hidTIDNRA' id="hidTIDNRA" maxlength="10" readonly="readonly" value="${resValue.TransactionID}" ${varDisabled}/>
	</c:otherwise>
</c:choose>
<c:if test="${NRAdvanceVO.specialCase == 'Y'}">
	<c:set var="specialCaseChecked" value="checked='checked'"></c:set>
</c:if>
<input type="hidden"  size="20%" name='hidFinalEligibility' id="hidFinalEligibility" value="${resValue.FinalEligibility}" />
<input type="hidden" size="20%" name='hidPKValueNRA' id="hidPKValueNRA"	value="${NRAdvanceVO.mstGpfAdvanceId}" />
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
		<hdiits:button type="button" captionid="BTN.WITHDRAWALHIS" bundle="${gpfLables}" id="btnWithdrawNRA" name="btnWithdrawNRA" onclick="window_new_update('ifms.htm?actionFlag=loadWithdrawalDetailList&gpfAccNo=${empVO[6]}&advanceType=NRA');" style="width:180px"></hdiits:button>
		</td>
		<td align="left" >
		<hdiits:button type="button" captionid="BTN.ADVANCEHIS" bundle="${gpfLables}" id="btnAdvanceNRA" name="btnAdvanceNRA" onclick="window_new_update('ifms.htm?actionFlag=loadAdvanceDetailList&gpfAccNo=${empVO[6]}&advanceType=RA');" style="width:180px"></hdiits:button>
		</td>
</tr></table></td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>
<fieldset ><legend><fmt:message key="CMN.ADVANCEDETAIL" bundle="${gpfLabels}"></fmt:message></legend></br>
<table align="left" cellpadding="5">
	<tr >
		<td width="15%" align="left" ><fmt:message key="CMN.SPECIALCASE" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
		<input type="checkbox" id="cbSpecialCaseNRA" name="cbSpecialCaseNRA" onclick="checkEligibleAmountNRA();" ${varDisabled}/> 
		</td>
		<td width="15%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SYSENTRYDATE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSysDate2' id="txtSysDate2" maxlength="10" readonly="readonly" value="${resValue.lDtCurrDate}" />
			</td>
			<td width="15%" align="left"><fmt:message key="CMN.ELIGIBLEAMT" bundle="${gpfLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"  name='txtEligibleAmtNRA' id="txtEligibleAmtNRA" maxlength="15" readonly="readonly" style="text-align: right"/></td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.PHYAPPDATE" bundle="${gpfLabels}"></fmt:message>
		<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${gpfLabels}" ></fmt:message></font></td>
		<td width="20%">
		<fmt:formatDate value="${NRAdvanceVO.applicationDate}" pattern="dd/MM/yyyy" var="applDate"/>
		<input type="text" name="txtAppDate2" id="txtAppDate2" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);previousDateValidation(this);validateAppDateNRA();" value="${applDate}" ${varDisabled}>
		<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtAppDate2",375,570)' style="cursor: pointer;"  ${varImageDisabled}/>
		<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.REQAMOUNT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<fmt:parseNumber var="varAdvanceAmount2"   value="${NRAdvanceVO.advanceAmt}"  type="number"/>
			<input type="text"  size="20%" name="txtAdvanceAmount2" id="txtAdvanceAmount2" maxlength="10" style="text-align: right" value="${varAdvanceAmount2}" onblur="checkEligibleAmountNRA();" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
		<select name="cmblstPurposeCategory2" id="cmblstPurposeCategory2" style="width:200px" ${varDisabled} >
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="Purpose" items="${resValue.lstPurposeCat}">
					<c:choose>
						<c:when test="${NRAdvanceVO.purposeCategory == Purpose.lookupId}">
							<option value="${Purpose.lookupId}" selected="selected"><c:out
								value="${Purpose.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${Purpose.lookupId}"><c:out
								value="${Purpose.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.SUBTYPE" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
		<select name="cmblstSubType" id="cmblstSubType" style="width:300px" onchange="checkEligibleAmountNRA();validateAppDateNRA();" ${varDisabled} ${disableSubType}>
				<c:forEach var="SubType" items="${resValue.lLstSubtype}">
					<c:choose>
						<c:when test="${NRAdvanceVO.subType == SubType.id}">
							<option value="${SubType.id}" selected="selected"><c:out
								value="${SubType.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${SubType.id}"><c:out
								value="${SubType.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label></td>
	</tr>
		
	<tr id="trOtherPurpose2"  >
		
		<td width="15%" align="left" ><fmt:message key="CMN.OTHERS" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='textareaOther2' id="textareaOther2" maxlength="25" style="text-align: left" value="${NRAdvanceVO.otherPurpose }" ${disableOther} ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.REMARKS" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="20%" >
			<textarea NAME="textareaRemarks2" id="textareaRemarks2" ROWS="2" cols="25" ${varDisabled}>${NRAdvanceVO.userRemarks }</textarea>			
		</td>
	</tr>
	
		<tr>
		<td colspan="4">
			<jsp:include
				page="/WEB-INF/jsp/gpf/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="Proof1" />
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

<tr><td>
<fieldset ><legend><fmt:message key="CMN.SANCDTLS" bundle="${gpfLabels}"></fmt:message></legend></br>
<table align="left" cellpadding="5">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varSancAmountNRA"   value="${NRAdvanceVO.amountSanctioned}"   type="number"/>
			<input type="text"  name='txtSancAmountNRA' id="txtSancAmountNRA" style="text-align: right" value="${varSancAmountNRA}" onblur="validateSancAmountNRA();" ${varDisabledInst}/>
			<label class="mandatoryindicator" ${varImageDisabledInst}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.ACCBALANCE" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:parseNumber var="varOutstandingBalNRA"   value="${NRAdvanceVO.balance}"   type="number"/>
			<input type="text"  name='txtOutstandingBalanceNRA' id="txtOutstandingBalanceNRA" style="text-align: right" value="${varOutstandingBalNRA}" ${varDisabledInst}/>
			<label class="mandatoryindicator" ${varImageDisabledInst}>*</label>
			</td>
		</tr>
		<tr ${varDisabledReleaseInst} id="Installment">
			<td width="15%" align="left" ><fmt:message key="CMN.SANCINSTALLMENTS" bundle="${gpfLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancPayInstNo' id="txtSancPayInstNo" value="${NRAdvanceVO.noOfInstallments}" style="text-align: right" onkeypress="digitFormat(this);" onblur="validateInstallmentNoNRA();" maxlength="2" ${varDisabledDEOApp}/>
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			</td>
			<td width="15%" align="left"></td>
			<td width="20%" align="left"></td>
		</tr>
		<tr ${varDisabledReleaseInst} id="Installment1">
			<td width="15%" align="left" ><fmt:message key="CMN.INST1" bundle="${gpfLabels}"></fmt:message>
			</br>	<fmt:message key="CMN.RELEASEDATE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInstallment1' id="txtInstallment1" value="${NRAdvanceVO.installment1}" style="text-align: right" ${varDisabledDEOApp}/>
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			<fmt:formatDate value="${NRAdvanceVO.releaseDate1}" pattern="dd/MM/yyyy" var="releaseDate1"/>
			<br><input type="text"  name='txtReleaseDate1' id="txtReleaseDate1" value="${releaseDate1}" pattern="dd/MM/yyyy" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);validateReleaseDates();" ${varDisabledDEOApp}>			
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtReleaseDate1",375,570)' style="cursor: pointer;"  />
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.INST2" bundle="${gpfLabels}"></fmt:message>	
			</br>	<fmt:message key="CMN.RELEASEDATE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInstallment2' id="txtInstallment2" value="${NRAdvanceVO.installment2}" style="text-align: right" ${varDisabledDEOApp}/>
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			<fmt:formatDate value="${NRAdvanceVO.releaseDate2}" pattern="dd/MM/yyyy" var="releaseDate2"/>
			<br><input type="text"  name='txtReleaseDate2' id="txtReleaseDate2" value="${releaseDate2}" pattern="dd/MM/yyyy" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);validateReleaseDates();" ${varDisabledDEOApp}>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtReleaseDate2",375,570)' style="cursor: pointer;"  />
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			</td>
		</tr>
		<tr ${varDisabledReleaseInst} id="Installment2">
			<td width="15%" align="left" ><fmt:message key="CMN.INST3" bundle="${gpfLabels}"></fmt:message>	
			</br>	<fmt:message key="CMN.RELEASEDATE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInstallment3' id="txtInstallment3" value="${NRAdvanceVO.installment3}" style="text-align: right" ${varDisabledDEOApp}/>
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			<fmt:formatDate value="${NRAdvanceVO.releaseDate3}" pattern="dd/MM/yyyy" var="releaseDate3"/>
			<br><input type="text"  name='txtReleaseDate3' id="txtReleaseDate3" value="${releaseDate3}" pattern="dd/MM/yyyy" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);validateReleaseDates();" ${varDisabledDEOApp}>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtReleaseDate3",375,570)' style="cursor: pointer;"  />
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.INST4" bundle="${gpfLabels}"></fmt:message>	
			</br>	<fmt:message key="CMN.RELEASEDATE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInstallment4' id="txtInstallment4" value="${NRAdvanceVO.installment4}" style="text-align: right" ${varDisabledDEOApp}/>
			<label class="mandatoryindicator" ${varImageDisabledDEOApp}>*</label>
			<fmt:formatDate value="${NRAdvanceVO.releaseDate4}" pattern="dd/MM/yyyy" var="releaseDate4"/>
			<br><input type="text"  name='txtReleaseDate4' id="txtReleaseDate4" value="${releaseDate4}" pattern="dd/MM/yyyy" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);validateReleaseDates();" ${varDisabledDEOApp}>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtReleaseDate4",375,570)' style="cursor: pointer;"  />
			<label class="mandatoryindicator" ${varImageDisabledDEOApp} >*</label>
			</td>
		</tr>
		
</table>
</fieldset>
</td></tr>
<tr><td></br>
<c:if test="${resValue.userType != 'DEO' && resValue.requestType == 'NRA'}">
<table><tr><td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${gpfLabels}"></fmt:message></td>
<td width="20%" ><textarea NAME="txtApproverRemarks" id="txtApproverRemarks" ROWS="2" cols="50" ${varRemarksDisabled}>${NRAdvanceVO.verifierRemarks}</textarea>
</td>
</tr></table>
</c:if>
</td></tr>
<tr><td>
<c:choose>
	<c:when test="${resValue.userType == 'HO' && resValue.requestType == 'NRA'}">
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
				<td colspan="3"><input type="text" id="txtHORemarks" size="85" name="txtHORemarks" /></td>					
			</tr>
		</table>
		</br>
		</fieldset>
	</c:when>	
	</c:choose>
	</td></tr>
</table>

<c:if test="${resValue.savedAdvanceRA == null}">
<script>
document.getElementById("Installment").style.display = 'none';
document.getElementById("Installment1").style.display = 'none';
document.getElementById("Installment2").style.display = 'none';
</script>
</c:if>
<c:if test="${resValue.userType == 'DEOAPP'}">
<script>
document.getElementById("Installment").disabled = true;
document.getElementById("Installment1").disabled = true;
document.getElementById("Installment2").disabled = true;
</script>
</c:if>

<ajax:select 
		source="cmblstPurposeCategory2" 
		target="cmblstSubType" 
		eventType="change"
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popNonRefundType" 
		parameters="cmbNonRefundType={cmblstPurposeCategory2}"
		postFunction="actionOnOtherReasonNRA">
</ajax:select>
<script>
    init();
</script>