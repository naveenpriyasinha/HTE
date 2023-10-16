<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empVO" value="${resValue.LNAEmpVO}"/>
<c:set var="HouseAdvance" value="${resValue.HouseAdvance}"/>
<c:set var="EmpBankDtls" value="${resValue.EmpBankDtls}"/>
<c:set var="Disburse" value="${resValue.Disburse}"/>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>

<fmt:message key="CMN.HOD" bundle="${lnaLabels}" var="HODHA"></fmt:message>
<fmt:message key="CMN.DEO" bundle="${lnaLabels}" var="DEOHA"></fmt:message>
<fmt:message key="CMN.HODASST2" bundle="${lnaLabels}" var="HODASST2HA"></fmt:message>
<fmt:message key="CMN.HODASST" bundle="${lnaLabels}" var="HODASSTHA"></fmt:message>
<fmt:message key="CMN.HOUSEADVC" bundle="${lnaLabels}" var="HouseAdvanceHA"></fmt:message>
<fmt:message key="CMN.DEOAPP" bundle="${lnaLabels}" var="DEOAPPHA"></fmt:message>
<c:if test="${HouseAdvance.houseAdvanceId!=null}">
	<c:if test="${resValue.userType == DEOAPPHA||resValue.userType == HODASSTHA}">
		<c:set var="varReadonly" value="true"></c:set>
		<c:set var="varDisabled" value="disabled='disabled'"></c:set>
		<c:set var="varImageDisabled" value="style='display:none'"></c:set>
		<c:set var="varAttachmentDisabled" value="Y"></c:set>
	</c:if>
</c:if>
<c:if test="${resValue.userType == HODASSTHA || resValue.userType == HODASST2HA}">
	<c:set var="varReadonlyHODASST" value="readOnly='readOnly'"></c:set>
	<c:set var="varDisabledHODASST" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHODASST" value="style='display:none'"></c:set>
	<c:set var="removeAttachmentAccess" value="Y"/>
</c:if>
<c:if test="${resValue.userType == HODHA}">
	<c:set var="varDisabledHOD" value="disabled='disabled'"></c:set>
</c:if>
<c:if test="${resValue.userType == DEOHA}">
	<c:set var="varDisabledDEO" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == HO || resValue.userType == HODHA}">
	<c:set var="varDisabledHO" value="disabled='disabled'"></c:set>
	<c:set var="varReadonly" value="true"></c:set>
	<c:set var="varReadonlyHOD" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
	<c:set var="removeAttachmentAccess" value="N"/>
</c:if>
<c:choose>
<c:when test="${HouseAdvance.oddInstallmentNumber == null}">
	<c:set var="varDisabledOddInstCombo" value="disabled='disabled'"></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisabledOddInstCombo" value=""></c:set>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${HouseAdvance.oddInterestInstallmentNo == null}">
	<c:set var="varDisabledOddInterInstCombo" value="disabled='disabled'"></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisabledOddInterInstCombo" value=""></c:set>
</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${(HouseAdvance.firstGuarantor == null && HouseAdvance.secondGuarantor == null) || HouseAdvance.empStatus == 'P'}">
		<c:set var="varDisplayNoneGuarantorDtls" value="style='display: none;'"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="varDisplayNoneGuarantorDtls" value=""></c:set>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when	test="${((HouseAdvance.disbursementThree != null && HouseAdvance.advanceSubType == 800038) || (HouseAdvance.disbursementFour != null && HouseAdvance.advanceSubType == 800058)) && resValue.userType == HODHA}">
		<c:set var="varReadOnlyInterest" value=""></c:set>
		<c:set var="varImageDisabledInterest" value=""></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="varReadOnlyInterest" value="readOnly='readOnly'"></c:set>
		<c:set var="varImageDisabledInterest" value="style='display:none'"></c:set>
	</c:otherwise>
</c:choose>

<c:if test="${resValue.userType == HODASST2HA}">
	<c:choose>
		<c:when test="${Disburse == 'Want2ndCF' || Disburse == 'Want2ndLC' || Disburse == 'Club2nd3rdCF'}">
			<c:set var="varDisplayNoneDis2" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplayNoneDis2" value="style='display:none'"></c:set>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${Disburse == 'Want3rdCF' || Disburse == 'Want3rdLC' || Disburse == 'Club3rd4thLC'}">
			<c:set var="varDisplayNoneDis2" value=""></c:set>
			<c:set var="varDisplayNoneDis3" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplayNoneDis3" value="style='display:none'"></c:set>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${Disburse == 'Want4thLC'}">
			<c:set var="varDisplayNoneDis2" value=""></c:set>
			<c:set var="varDisplayNoneDis3" value=""></c:set>
			<c:set var="varDisplayNoneDis4" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplayNoneDis4" value="style='display:none'"></c:set>
		</c:otherwise>
	</c:choose>
</c:if>

<c:choose>
	<c:when test="${Disburse == '800059'}">
		<c:set var="varDisabledSubtype" value="disabled='disabled'"></c:set>
		<c:set var="varDisplayChecklistPP" value="style='display:none'"></c:set>
		<c:set var="varDisplayChecklistSR" value=""></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="varDisplayChecklistPP" value=""></c:set>
		<c:set var="varDisplayChecklistSR" value="style='display:none'"></c:set>
	</c:otherwise>
</c:choose>

<c:if test="${resValue.userType == HODHA}">
	<c:if test="${HouseAdvance.disbursementTwo == null}">
		<c:set var="varDisplayNoneDis2" value="style='display:none'"></c:set>
	</c:if>
	<c:if test="${HouseAdvance.disbursementThree == null}">
		<c:set var="varDisplayNoneDis3" value="style='display:none'"></c:set>
	</c:if>
	<c:if test="${HouseAdvance.disbursementFour == null}">
		<c:set var="varDisplayNoneDis4" value="style='display:none'"></c:set>
	</c:if>
</c:if>
<c:if test="${resValue.userType == HODASST2HA}">
	<c:if test="${HouseAdvance.disbursementTwo != null}">
		<c:set var="varDisplayNoneDis2" value=""></c:set>
	</c:if>
	<c:if test="${HouseAdvance.disbursementThree != null}">
		<c:set var="varDisplayNoneDis3" value=""></c:set>
	</c:if>
	<c:if test="${HouseAdvance.disbursementFour != null}">
		<c:set var="varDisplayNoneDis4" value=""></c:set>
	</c:if>
</c:if>

<c:if test="${HouseAdvance.releaseDateOne != null}">
	<c:set var="varReadOnlyReleaseDate1" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledReleaseDate1" value="style='display:none'"></c:set>	
</c:if>
<c:if test="${HouseAdvance.releaseDateTwo != null}">
	<c:set var="varReadOnlyReleaseDate2" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledReleaseDate2" value="style='display:none'"></c:set>	
</c:if>
<c:if test="${HouseAdvance.releaseDateThree != null}">
	<c:set var="varReadOnlyReleaseDate3" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledReleaseDate3" value="style='display:none'"></c:set>	
</c:if>

<input type="hidden" name='hidHouseAdvanceId' id="hidHouseAdvanceId"  value="${HouseAdvance.houseAdvanceId}" />
<input type="hidden" name="hidColCountPP" id="hidColCountPP" value="0" />
<input type="hidden" name="hidColCountCF" id="hidColCountCF" value="0" />
<input type="hidden" name="hidColCountRB" id="hidColCountRB" value="0" />
<input type="hidden" name="hidColCountBL" id="hidColCountBL" value="0" />
<input type="hidden" name="hidColCountSR" id="hidColCountSR" value="0" />
<input type="hidden" name="hidColCountOF" id="hidColCountOF" value="0" />
<input type="hidden" name="hidColCountER" id="hidColCountER" value="0" />
<input type="hidden" name="hidColCountLC" id="hidColCountLC" value="0" />

<input type="hidden" name="hidRowCountPP" id="hidRowCountPP" value="4" />
<input type="hidden" name="hidRowCountCF" id="hidRowCountCF" value="4" />
<input type="hidden" name="hidRowCountRB" id="hidRowCountRB" value="5" />
<input type="hidden" name="hidRowCountBL" id="hidRowCountBL" value="5" />
<input type="hidden" name="hidRowCountSR" id="hidRowCountSR" value="4" />
<input type="hidden" name="hidRowCountOF" id="hidRowCountOF" value="5" />
<input type="hidden" name="hidRowCountER" id="hidRowCountER" value="4" />
<input type="hidden" name="hidRowCountLC" id="hidRowCountLC" value="5" />

<input type="hidden" name="hidReqExists" id="hidReqExists" value="${resValue.alertMessage}" />
<input type="hidden" name="hidDisburseMsg" id="hidDisburseMsg" value="${Disburse}" />
<table width="82%">
<tr><td>
<fieldset>
<table align="left">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.LOANTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%" align="left" ><b><fmt:message key="CMN.HBA" bundle="${lnaLabels}"></fmt:message></b></td>
			<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">
				<select name="cmbHBAType" id="cmbHBAType" style="width:200px"  ${varDisabled} ${varDisabledSubtype} ${varDisabledHO} onchange="validateSubType();changeSancDtls();validateReqAmountHBA(1);popupSancDtlsHBA(1);">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstHouseSubType}">
					<c:choose>
						<c:when test="${HouseAdvance.advanceSubType == LoanSubType.lookupId || Disburse == LoanSubType.lookupId}">
							<option value="${LoanSubType.lookupId}" selected="selected">
							<c:out value="${LoanSubType.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${LoanSubType.lookupId}"><c:out
								value="${LoanSubType.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select><label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
	</tr>
	<tr>
			<td width="15%" align="left" ></td>
			<td width="20%" align="left" >			
			<c:choose>
				<c:when test="${HouseAdvance.empStatus!=null}">
					<c:choose>
						<c:when test="${HouseAdvance.empStatus=='P'}">
							<input type="radio" id="rdoPermanent" name="rdoEmpStatus" value="P" onclick="displayGuarantorDtls()" checked="checked" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.PERMANENT" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;
							<input type="radio" id="rdoSuspended" name="rdoEmpStatus" value="S" onclick="displayGuarantorDtls()" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.SUSPENDED" bundle="${lnaLabels}"></fmt:message>						
						</c:when>
						<c:otherwise>
							<input type="radio" id="rdoPermanent" name="rdoEmpStatus" value="P" onclick="displayGuarantorDtls()" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.PERMANENT" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;
							<input type="radio" id="rdoSuspended" name="rdoEmpStatus" value="S" onclick="displayGuarantorDtls()"  checked="checked" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.SUSPENDED" bundle="${lnaLabels}"></fmt:message>
						</c:otherwise>
					</c:choose>			
				</c:when>
			<c:otherwise>
				<input type="radio" id="rdoPermanent" name="rdoEmpStatus" value="P" onclick="displayGuarantorDtls()" checked="checked" ${varDisabled}${varDisabledHO}/>
				<fmt:message key="CMN.PERMANENT" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;
				<input type="radio" id="rdoSuspended" name="rdoEmpStatus" value="S" onclick="displayGuarantorDtls()" ${varDisabled}${varDisabledHO}/>
				<fmt:message key="CMN.SUSPENDED" bundle="${lnaLabels}"></fmt:message>	
			</c:otherwise>
			</c:choose>
			</td>			
			<td width="15%"><fmt:message key="CMN.PAYCOMMGR" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">				
			<select name="cmbPayCommissionHBA" id="cmbPayCommissionHBA" style="width:200px" ${varDisabled}${varDisabledHO} onchange="validatePayComGR();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="PayCommission" items="${resValue.PayCommissionGR}">
					<c:choose>
						<c:when test="${HouseAdvance.payCommissionGR == PayCommission.lookupId}">
							<option value="${PayCommission.lookupId}" selected="selected"><c:out
								value="${PayCommission.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${PayCommission.lookupId}"><c:out
								value="${PayCommission.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select><label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
	</tr>
</table>
</fieldset>
<br/>
<fieldset id="fieldsetGuarantor" ${varDisplayNoneGuarantorDtls}><legend><fmt:message key="CMN.GUARANTORDTLS" bundle="${lnaLabels}"></fmt:message></legend>
<table align="left" cellpadding="4" width="100%">
	<tr>
	<td width="15%" align="left" ><b>1.</b>&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SEVAARTHID" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtGuarantor1" name="txtGuarantor1" ${varDisabled}${varDisabledHO} value="${HouseAdvance.firstGuarantor}" onblur="popUpGuarantorDtls(1)"/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.EMPNAME" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtEmpName1" name="txtEmpName1" value="${resValue.EmpName1}" size="45" readonly="readonly"/>
		</td>		
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.BASICPAY" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtBasicPay1" name="txtBasicPay1" value="${resValue.BasicPay1}" readonly="readonly"/>
		</td>
		<td width="10%" align="left" ></td>
		<td width="20%" align="left" >			
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.DDOCODE" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtDdoCode1" name="txtDdoCode1" value="${resValue.Ddocode1}" readonly="readonly"/>
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.DDOOFFICENAME" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtDdoName1" name="txtDdoName1" value="${resValue.DdoName1}" size="60" readonly="readonly"/>
		</td>		
	</tr>
	<tr>
	
		<td width="15%" align="left" ><b>2.</b>&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.SEVAARTHID" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtGuarantor2" name="txtGuarantor2" ${varDisabled}${varDisabledHO} value="${HouseAdvance.secondGuarantor}" onblur="popUpGuarantorDtls(2)"/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>		
		<td width="10%" align="left" ><fmt:message key="CMN.EMPNAME" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtEmpName2" name="txtEmpName2" value="${resValue.EmpName2}" size="45" readonly="readonly"/>
		</td>		
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.BASICPAY" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtBasicPay2" name="txtBasicPay2" value="${resValue.BasicPay2}" readonly="readonly"/>
		</td>
		<td width="10%" align="left" ></td>
		<td width="20%" align="left" >
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.DDOCODE" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtDdoCode2" name="txtDdoCode2" value="${resValue.Ddocode2}" readonly="readonly"/>
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.DDOOFFICENAME" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" align="left" >			
			<input type="text" id="txtDdoName2" name="txtDdoName2" size="60" value="${resValue.DdoName2}" readonly="readonly"/>
		</td>		
	</tr>
</table>
</fieldset>
<fieldset><legend><fmt:message key="CMN.ADVANCEDETAIL" bundle="${lnaLabels}"></fmt:message></legend></br>
<table align="left" cellpadding="4">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SYSENTRYDATE" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSysDate' id="txtSysDate" maxlength="10" readonly="readonly" value="${resValue.lDtCurrDate}" />
			</td>
			<td width="20%" align="left" ><fmt:message key="CMN.PHYAPPDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
			<td width="20%">
			<fmt:formatDate value="${HouseAdvance.applicationDate}" pattern="dd/MM/yyyy" var="appDate"/>
			<input type="text" name="txtAppDateHBA" id="txtAppDateHBA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);greaterThanCurrDateValidation(this);popupLoanRepayCapacity();popupSancDtlsHBA(1);" value = "${appDate}" ${varDisabled}${varDisabledHO}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtAppDateHBA",375,570)' ${varImageDisabled}${varImageDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label></td>
		</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.REQAMOUNT" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtReqAmountHBA' id="txtReqAmountHBA" maxlength="10" onkeypress="digitFormat(this);" style="text-align: right" onblur="validateReqAmountHBA(1);popupLoanRepayCapacity();popupSancDtlsHBA(1);" value="${HouseAdvance.amountRequested}"  ${varDisabled}${varDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>
		<td width="20%" align="left" ><fmt:message key="CMN.LOANREPAYMENT" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtLoanRepaymentCapacity' id="txtLoanRepaymentCapacity" style="text-align: right" value="${HouseAdvance.loanRepayCapacity}" readonly="readonly"/>
		</td>

	</tr>
	<tr>
		<td width="15%" align="left"></td>
		<td align="left" width="20%"></td>
		<td width="20%" align="left" ><fmt:message key="CMN.INTERESTRATE" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtInterestRateHBA' id="txtInterestRateHBA" maxlength="10" value="${HouseAdvance.interestRate}" style="text-align: right" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<fieldset>
			<jsp:include page="/WEB-INF/jsp/lna/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="ProofHBA" />
				<jsp:param name="formName" value="LNARequestProcessForm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="attachmentSize" value="1" /> 
				<jsp:param name="multiple" value="N" />
				<jsp:param name="removeAttachmentFromDB" value="${removeAttachmentAccess}" />
				<jsp:param name="readOnly" value="${varAttachmentDisabled}" />				
			</jsp:include>
			</fieldset>
			</td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	</table>
</fieldset>
</td></tr>
<tr><td>
<fieldset ><legend><fmt:message key="CMN.CHECKLISTS" bundle="${lnaLabels}"></fmt:message></legend><br>
<c:choose>
<c:when test="${resValue.Checklist[0]==null}">
<c:set var="hideDivForOldChecklist" value=""></c:set>
<c:set var="hideDivForNewChecklist" value="style='display: none;'"></c:set>
</c:when>
<c:otherwise>
<c:set var="hideDivForOldChecklist" value="style='display: none;'"></c:set>
<c:set var="hideDivForNewChecklist" value=""></c:set>
</c:otherwise>
</c:choose>
<div id="ChecklistHBAForPP" ${hideDivForOldChecklist} ${varDisplayChecklistPP}>
<table align="left" width="100%" id="tblChecklistHBAPP">
<tbody>
	<tr>
		<td width="33%">
			<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Application for HBA"/>
			<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
			<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
			<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="No of Children Certificate"/>
			<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
			<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Plot Owner's certificate"/>
		<fmt:message key="CMN.PLOTOWERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">		
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">		
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Registration Paper of Plot Owner"/>
		<fmt:message key="CMN.REGPAPEROFPLOTOWNER" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAPP" id="cbDocCheckListHBAPP" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>		
		</td>
	</tr>	
</tbody>
</table>
</div>
<div id="ChecklistHBAForCF" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBACF">
<tbody>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="No of Children Certificate"/>
		<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Mortgage Form"/>
		<fmt:message key="CMN.MORTGAGEFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value=" Estimate of work"/>
		<fmt:message key="CMN.ESTIMATEOFWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Permission certificate for Construction"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">		
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBACF" id="cbDocCheckListHBACF" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
</tbody>	
</table>
</div>
<div id="ChecklistHBAForRB" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBARB">
<tbody>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="No of Children Certificate"/>
		<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Mortgage Form"/>
		<fmt:message key="CMN.MORTGAGEFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Pre Permission Certificate"/>
		<fmt:message key="CMN.PREPERMISSIONCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Permission certificate for Construction"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">		
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Registration Paper of Flat"/>
		<fmt:message key="CMN.REGPAPEROFFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBARB" id="cbDocCheckListHBARB" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		</td>
		<td width="25%">
		</td>
		<td width="25%">
		</td>
	</tr>
</tbody>
</table>
</div>
<div id="ChecklistHBAForOF" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBAOF">
<tbody>
	<tr>
		<td width="33%">
			<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Application for HBA"/>
			<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
			<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
			<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="No of Children Certificate"/>
			<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
			<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Plot Owner's certificate"/>
		<fmt:message key="CMN.PLOTOWERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">		
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Valuation Certificate by Government Valuer"/>
		<fmt:message key="CMN.VALUCERTBYGOV" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Registration Paper of Previous Owner"/>
		<fmt:message key="CMN.REGPAPEROFPREVIOUSOWNER" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>		
		</td>
	</tr>	
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBAOF" id="cbDocCheckListHBAOF" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		</td>
		<td width="33%">
		</td>
	</tr>	
</tbody>
</table>
</div>
<div id="ChecklistHBAForBL" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBABL">
<tbody>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="No of Children Certificate"/>
		<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Mortgage Certificate"/>
		<fmt:message key="CMN.MORTGAGECERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Pre Permission Certificate"/>
		<fmt:message key="CMN.PREPERMISSIONCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Permission certificate for Construction"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">		
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="First Latter of Loan Sanctioning"/>
		<fmt:message key="CMN.FIRSTLATTEROFLOANSANC" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Loan Agreement"/>
		<fmt:message key="CMN.LOANAGREEMENT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBABL" id="cbDocCheckListHBABL" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		</td>
		<td width="25%">
		</td>
	</tr>
</tbody>
</table>
</div>
<div id="ChecklistHBAForLC" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBALC">
<tbody>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="No of Children Certificate"/>
		<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Mortgage Form"/>
		<fmt:message key="CMN.MORTGAGEFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value=" Estimate of work"/>
		<fmt:message key="CMN.ESTIMATEOFWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Permission certificate for Construction"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">		
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Registration Paper of Plot Owner"/>
		<fmt:message key="CMN.REGPAPEROFPLOTOWNER" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBALC" id="cbDocCheckListHBALC" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		</td>
		<td width="25%">
		</td>
		<td width="25%">
		</td>
	</tr>
</tbody>	
</table>
</div>
<div id="ChecklistHBAForSR" ${varDisplayChecklistSR}>
<table align="left" width="100%" id="tblChecklistHBASR">
<tbody>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Permission certificate for Construction"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Natural Calamity Certificate From Government"/>
		<fmt:message key="CMN.NATURALCALAMITYCERTGOV" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Estimate Certificate"/>
		<fmt:message key="CMN.ESTIMATECERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Mortgage Certificate"/>
		<fmt:message key="CMN.MORTGAGECERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListHBASR" id="cbDocCheckListHBASR" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
</tbody>
</table>
</div>
<div id="ChecklistHBAForER" style="display: none;" >
<table align="left" width="100%" id="tblChecklistHBAER">
<tbody>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Application for HBA"/>
		<fmt:message key="CMN.APPFORHBA" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="No of Children Certificate"/>
		<fmt:message key="CMN.NOOFCHILDCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Mortgage Form"/>
		<fmt:message key="CMN.MORTGAGEFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value=" Estimate of work"/>
		<fmt:message key="CMN.ESTIMATEOFWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Form No. 7/12"/>
		<fmt:message key="CMN.FORM712" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Remaining Amount Certificate"/>
		<fmt:message key="CMN.REMAINAMOUNTCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="NA Permission"/>
		<fmt:message key="CMN.NAPERMISSION" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Approved plan for Flat"/>
		<fmt:message key="CMN.APPROVEDPLANFLAT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">		
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="NOC of recovery"/>
		<fmt:message key="CMN.NOCOFRECOVERY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Agreement Doc. of Spouse"/>
		<fmt:message key="CMN.AGREEMENTDOCOFSPOUSE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Permission certificate for Construction from Government Authority"/>
		<fmt:message key="CMN.PERMISSIONOFCONSTRUCTIONGOV" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="25%">
		<input type="checkbox" name="cbDocCheckListHBAER" id="cbDocCheckListHBAER" value="Certificate of Spouse not working in Govt./Semi Govt./Govt. aided org/Private org"/>
		<fmt:message key="CMN.CERTOFSPOUSENOTWORK" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
</tbody>	
</table>
</div>
<table align="left" width="100%" id="tblChecklistHBA" ${hideDivForNewChecklist}>
<tbody>
<c:set value="1" var="count" />
<c:set value="1" var="rowCount" />
<tr> 
<c:forEach items="${resValue.Checklist}" var="CheckBoxList" varStatus="Counter">
<c:if test="${HouseAdvance.advanceSubType=='800037'}">
<c:choose>
	<c:when test="${count<13}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>			
				</td>
				<c:if test="${count%3==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800041'}">
<c:choose>
	<c:when test="${count<14}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%3==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800059'}">
<c:choose>
	<c:when test="${count<13}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%3==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800038'}">
<c:choose>
	<c:when test="${count<17}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%4==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
						<c:if test="${CheckBoxList[1]=='Y'}">
							<c:set var="varChecked" value="checked='checked'"></c:set>
						</c:if>
						<c:if test="${CheckBoxList[1]=='N'}">
							<c:set var="varChecked" value=""></c:set>
						</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800039'}">
<c:choose>
	<c:when test="${count<18}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%4==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
						<c:if test="${CheckBoxList[1]=='Y'}">
							<c:set var="varChecked" value="checked='checked'"></c:set>
						</c:if>
						<c:if test="${CheckBoxList[1]=='N'}">
							<c:set var="varChecked" value=""></c:set>
						</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800042'}">
<c:choose>
	<c:when test="${count<19}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%4==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
						<c:if test="${CheckBoxList[1]=='Y'}">
							<c:set var="varChecked" value="checked='checked'"></c:set>
						</c:if>
						<c:if test="${CheckBoxList[1]=='N'}">
							<c:set var="varChecked" value=""></c:set>
						</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800058'}">
<c:choose>
	<c:when test="${count<18}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%4==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
						<c:if test="${CheckBoxList[1]=='Y'}">
							<c:set var="varChecked" value="checked='checked'"></c:set>
						</c:if>
						<c:if test="${CheckBoxList[1]=='N'}">
							<c:set var="varChecked" value=""></c:set>
						</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>
<c:if test="${HouseAdvance.advanceSubType=='800060'}">
<c:choose>
	<c:when test="${count<17}">
				<td width="25%">
					<c:if test="${CheckBoxList[1]=='Y'}">
						<c:set var="varChecked" value="checked='checked'"></c:set>
					</c:if>
					<c:if test="${CheckBoxList[1]=='N'}">
						<c:set var="varChecked" value=""></c:set>
					</c:if>
					<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
						<c:out value="${CheckBoxList[0]}"></c:out>	
						<c:if test="${CheckBoxList[0]!='NOC of recovery'}">	
							<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
						</c:if>				
				</td>
				<c:if test="${count%4==0}">
						</tr>
						<tr>
						<c:set value="${rowCount+1}" var="rowCount"/> 
					</c:if>
				<c:set value="${count+1}" var="count"/>
	</c:when>
	<c:otherwise>
				<tr>
					<td width="25%">
						<c:if test="${CheckBoxList[1]=='Y'}">
							<c:set var="varChecked" value="checked='checked'"></c:set>
						</c:if>
						<c:if test="${CheckBoxList[1]=='N'}">
							<c:set var="varChecked" value=""></c:set>
						</c:if>
						<input type="checkbox" name="cbDocCheckListHBA" id="cbDocCheckListHBA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
									<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistHBA");' ${varImageDisabledHO}/>
					</td>
				</tr>
				<c:set value="${count+1}" var="count"/>
			</c:otherwise>
</c:choose>
</c:if>

</c:forEach>
</tr>
</tbody>
</table>
<input type="hidden" name="hidRowCount" id="hidRowCount" value="${rowCount}" />
</fieldset>
<center>		<hdiits:button type="button" captionid="BTN.ADDNEW" bundle="${lnaLabels}" id="btnAddNewCheckListHBA" name="btnAddNewCheckListHBA" onclick="addNewCheckListHBA();" readonly="${varReadonly}"/></center>
</td></tr>
<tr><td>
<fieldset ><legend><fmt:message key="CMN.BANKDTLS" bundle="${lnaLabels}"></fmt:message></legend></br>
<table cellpadding="4" width="100%">
	<tr>
			<td width="15%"><fmt:message key="CMN.BANKNAME" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%"><input type="text"  name='txtBankNameCA' id="txtBankNameCA" size="40" value="${EmpBankDtls[0]}" readonly="readonly"/></td>
			<td width="10%"><fmt:message key="CMN.BANKACCNO" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%"><input type="text"  name='txtBankAccNoCA' id="txtBankAccNoCA" value="${EmpBankDtls[2]}" readonly="readonly"/></td>			
	</tr>
	<tr>
			<td width="15%"><fmt:message key="CMN.BRANCHNAME" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%"><input type="text"  name='txtBranchNameCA' id="txtBranchNameCA" size="40" value="${EmpBankDtls[1]}" readonly="readonly"/></td>
			<td width="10%"><fmt:message key="CMN.IFSCODE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%"><input type="text"  name='txtIfsCodeCA' id="txtIfsCodeCA" value="${EmpBankDtls[3]}" readonly="readonly"/></td>
	</tr>
</table>
</fieldset>	
	
</td></tr>
<tr><td>
<fieldset ><legend><fmt:message key="CMN.SANCDTLS" bundle="${lnaLabels}"></fmt:message></legend></br>
<c:choose>
<c:when test="${HouseAdvance.houseAdvanceId!=null}">
	<c:choose>
		<c:when test="${HouseAdvance.advanceSubType == 800038 || HouseAdvance.advanceSubType == 800058}">
			<c:set var ="displaySancDtlsTable1" value="style='display: none;'"/>
			<c:set var ="displaySancDtlsTable2" value=""/>
		</c:when>
		<c:otherwise>
			<c:set var ="displaySancDtlsTable1" value=""/>
			<c:set var ="displaySancDtlsTable2" value="style='display: none;'"/>
		</c:otherwise>
	</c:choose>
</c:when>
<c:otherwise>
	<c:set var ="displaySancDtlsTable1" value=""/>
	<c:set var ="displaySancDtlsTable2" value="style='display: none;'"/>
</c:otherwise>
</c:choose>
<table id="tblSancDtls1" cellpadding="3" cellspacing="3" ${displaySancDtlsTable1} width="100%">
			<tr>
					<td width="15%" align="left" ><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>	
					<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font>
					</td>
					<td width="20%" align="left">
					<fmt:formatDate value="${HouseAdvance.sanctionedDate}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
					<input type="text" name="txtSanctionedDateHBA" id="txtSanctionedDateHBA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);" value = "${sanctionedDate}" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateHBA",375,570)'  ${varImageDisabled}${varImageDisabledDEO}${varImageDisabledHODASST}/>
					<label class="mandatoryindicator" ${varImageDisabled} ${varImageDisabledDEO}${varImageDisabledHODASST}>*</label>			
					</td>
			</tr>
			<tr>	
				<td width="15%" align="left" ><fmt:message key="CMN.SANCTIONAMONUT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text" name="txtSanctionedAmountHBA" id="txtSanctionedAmountHBA" style="text-align: right" onkeypress="digitFormat(this);" value = "${HouseAdvance.amountSanctioned}" onblur="validateReqAmountHBA(2);popupSancDtlsHBA(3);validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
				</td>
				<td width="15%" align="left" ><fmt:message key="CMN.INTERESTAMOUNT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text"  name='txtInterestAmountHBA' id="txtInterestAmountHBA" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.interestAmount}" onblur="validateReqAmountHBA(2);validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
				</td>
			</tr>
					<tr>
					<td width="15%" align="left" ><fmt:message key="CMN.SANCPRININSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text"  name='txtSancPrinInstallHBA' id="txtSancPrinInstallHBA" style="text-align: right" value="${HouseAdvance.sancPrinInst}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}/>
					<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
					</td>
					<td width="15%" align="left" ><fmt:message key="CMN.SANCINTERINSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text"  name='txtSancInterInstallHBA' id="txtSancInterInstallHBA" style="text-align: right" value="${HouseAdvance.sancInterestInst}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}/>
					<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
					</td>
				</tr>
				<tr>
					<td width="15%" align="left" ><fmt:message key="CMN.PRININSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text"  name='txtPrincipalInstallmentAmtHBA' id="txtPrincipalInstallmentAmtHBA" style="text-align: right" value="${HouseAdvance.principalInstAmtMonth}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%" align="left" ><fmt:message key="CMN.INTINSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text"  name='txtInterInstallmentAmountHBA' id="txtInterInstallmentAmountHBA" style="text-align: right" value="${HouseAdvance.interestInstAmtMonth}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
				</tr>
				<tr>
				<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLMENT" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
					<input type="text"  name='txtOddPrincipalInstallmentAmtHBA' id="txtOddPrincipalInstallmentAmtHBA" style="text-align: right" value="${HouseAdvance.oddInstallment}" onblur="validateNoOfInstallmentsHBA();" onkeypress="digitFormat(this);" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%" ><fmt:message key="CMN.ODDINTERESTINSTALLMENT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtOddInterestInstallmentAmtHBA' id="txtOddInterestInstallmentAmtHBA" style="text-align: right"  value="${HouseAdvance.oddInterestInstallment}" onblur="validateNoOfInstallmentsHBA();" onkeypress="digitFormat(this);" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
				</tr>
				<tr>
				<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
						<select name="cmbOddPrincipalInstallNoHBA" id="cmbOddPrincipalInstallNoHBA" style="width:160px" ${varDisabled} ${varDisabledOddInstCombo}>
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${HouseAdvance.oddInstallmentNumber == OddInstallNo.lookupId}">
									<option value="${OddInstallNo.lookupId}" selected="selected"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${OddInstallNo.lookupId}"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
					<td width="15%"><fmt:message key="CMN.ODDINTERESTINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%"><select name="cmbOddInterestInstallNoHBA" id="cmbOddInterestInstallNoHBA" style="width:160px" ${varDisabled} ${varDisabledOddInterInstCombo}>
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${HouseAdvance.oddInterestInstallmentNo == OddInstallNo.lookupId}">
									<option value="${OddInstallNo.lookupId}" selected="selected"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${OddInstallNo.lookupId}"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
				</tr>
		</table>
<table width="100%" id="tblSancDtls2" cellpadding="2" cellspacing="3" ${displaySancDtlsTable2}>		
			<tr>
					<td width="10%" ><fmt:message key="CMN.SANCTIONAMONUT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%" align="left">
					<input type="text" name="txtSanctionedAmountHBA2" id="txtSanctionedAmountHBA2" style="text-align: right" value = "${HouseAdvance.amountSanctioned}" onkeypress="digitFormat(this);" onblur="validateReqAmountHBA(3);popupSancDtlsHBA(2);validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%"><fmt:message key="CMN.PRINCIPALAMOUNT" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%"><input type="text" name="txtPrincipalAmountHBA" id="txtPrincipalAmountHBA" style="text-align: right" onkeypress="digitFormat(this);" value = "${HouseAdvance.amountSanctioned}" readonly="readonly"/></td>
					<td width="15%" ><fmt:message key="CMN.INTERESTAMOUNT" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">
					<input type="text"  name='txtInterestAmountHBA2' id="txtInterestAmountHBA2" style="text-align: right" value="${HouseAdvance.interestAmount}" onkeypress="digitFormat(this);" onblur="validateReqAmountHBA(3);validateNoOfInstallmentsHBA();" />
				</td>
			</tr>
			<tr>
					<td width="10%"><fmt:message key="CMN.DISBURSEMENT1" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%" align="left">
					<input type="text"  name='txtDisbursement1' id="txtDisbursement1" style="text-align: right" value="${HouseAdvance.disbursementOne}" onkeypress="digitFormat(this);" readonly="readonly"/>
					</td>
					<td width="15%" ><fmt:message key="CMN.SANCPRININSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtSancPrinInstallHBA2' id="txtSancPrinInstallHBA2" style="text-align: right" value="${HouseAdvance.sancPrinInst}"  onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}/>
					<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
					</td>
					<td width="10%" ><fmt:message key="CMN.SANCINTERINSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtSancInterInstallHBA2' id="txtSancInterInstallHBA2" style="text-align: right" value="${HouseAdvance.sancInterestInst}"onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();"/>
					<label class="mandatoryindicator" ${varImageDisabledInterest}>*</label>
					</td>
			</tr>
			<tr>
				<td width="10%"><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
				<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
				<td width="20%">
					<fmt:formatDate value="${HouseAdvance.releaseDateOne}" pattern="dd/MM/yyyy" var="ReleaseDate1"/>
					<input type="text" name="txtReleaseDate1" id="txtReleaseDate1" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);validateNoOfInstallmentsHBA();" value = "${ReleaseDate1}" ${varReadonlyHODASST}${varReadOnlyReleaseDate1}/>
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate1",375,570)' ${varImageDisabledHODASST} ${varImageDisabledReleaseDate1}/>
					<label class="mandatoryindicator" ${varImageDisabledHODASST} ${varImageDisabledReleaseDate1}>*</label>
				</td>
				<td width="15%" ><fmt:message key="CMN.PRININSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtPrinInstallmentAmountHBA' id="txtPrinInstallmentAmountHBA" style="text-align: right" value="${HouseAdvance.principalInstAmtMonth}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%" ><fmt:message key="CMN.INTINSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtInterInstallmentAmountHBA2' id="txtInterInstallmentAmountHBA2" style="text-align: right" value="${HouseAdvance.interestInstAmtMonth}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsHBA();" />
					</td>
			</tr>
					<tr>
					
					<td width="10%">
						<div  id="tdHideColMsgDis2" ${varDisplayNoneDis2}>
								<fmt:message key="CMN.DISBURSEMENT2" bundle="${lnaLabels}"></fmt:message>
						</div>
					</td>
					<td width="20%">
						<div id="tdHideColDis2" ${varDisplayNoneDis2}>
							<c:if test="${resValue.userType == HODASST2HA}">
								<c:choose>
									<c:when test="${HouseAdvance.advanceSubType == 800038 && (Disburse == 'Want2ndCF' || Disburse =='Want2ndLC' || Disburse == 'Want3rdCF' || Disburse =='Want3rdLC' || Disburse =='Want4thLC')}">
										<input type="text"  name='txtDisbursement2' id="txtDisbursement2" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.40}" readonly="readonly"/>
									</c:when>
									<c:when test="${HouseAdvance.advanceSubType == 800058 && (Disburse == 'Want2ndCF' || Disburse =='Want2ndLC' || Disburse == 'Want3rdCF' || Disburse =='Want3rdLC' || Disburse =='Want4thLC' || Disburse =='Club3rd4thLC')}">
										<input type="text"  name='txtDisbursement2' id="txtDisbursement2" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.25}" readonly="readonly"/>
									</c:when>
									<c:when test="${HouseAdvance.advanceSubType == 800038 && Disburse == 'Club2nd3rdCF'}">
										<input type="text"  name='txtDisbursement2' id="txtDisbursement2" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.70}" readonly="readonly"/>
									</c:when>
									<c:otherwise>
										<c:if  test="${HouseAdvance.disbursementTwo != null && Disburse == null}" >
											<input type="text"  name='txtDisbursement2' id="txtDisbursement2" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementTwo}" readonly="readonly"/>
										</c:if>									
									</c:otherwise>	
								</c:choose>
							</c:if>
							<c:if test="${resValue.userType == HODHA}">
								<input type="text"  name='txtDisbursement2' id="txtDisbursement2" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementTwo}" readonly="readonly"/>
							</c:if>
						</div>						
					</td>					
					<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLMENT" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
					<input type="text"  name='txtOddPrincipalInstallmentAmtHBA2' id="txtOddPrincipalInstallmentAmtHBA2" style="text-align: right" value="${HouseAdvance.oddInstallment}" onblur="validateNoOfInstallmentsHBA();" onkeypress="digitFormat(this);" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%" ><fmt:message key="CMN.ODDINTERESTINSTALLMENT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtOddInterestInstallmentAmtHBA2' id="txtOddInterestInstallmentAmtHBA2" style="text-align: right"  value="${HouseAdvance.oddInterestInstallment}" onblur="validateNoOfInstallmentsHBA();" onkeypress="digitFormat(this);" />
					</td>
				</tr>
			<tr>
			<td width="10%">			
				<div  id="tdHideColMsg2" ${varDisplayNoneDis2}>
				<fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
				<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font>
				</div>
			</td>
				<td width="20%">
					<div  id="tdHideColMsgRel2" ${varDisplayNoneDis2}>
					<fmt:formatDate value="${HouseAdvance.releaseDateTwo}" pattern="dd/MM/yyyy" var="ReleaseDate2"/>
					<input type="text" name="txtReleaseDate2" id="txtReleaseDate2" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);validateNoOfInstallmentsHBA();" value = "${ReleaseDate2}" ${varReadonlyHODASST} ${varReadOnlyReleaseDate2}/>
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate2",375,570)' ${varImageDisabledHODASST} ${varImageDisabledReleaseDate2}/>
					<label class="mandatoryindicator" ${varImageDisabledHODASST} ${varImageDisabledReleaseDate2}>*</label>
					</div>
				</td>				
				<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
						<select name="cmbOddPrincipalInstallNoHBA2" id="cmbOddPrincipalInstallNoHBA2" style="width:160px" ${varDisabled} ${varDisabledOddInstCombo}>
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${HouseAdvance.oddInstallmentNumber == OddInstallNo.lookupId}">
									<option value="${OddInstallNo.lookupId}" selected="selected"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${OddInstallNo.lookupId}"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
					<td width="15%"><fmt:message key="CMN.ODDINTERESTINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%"><select name="cmbOddInterestInstallNoHBA2" id="cmbOddInterestInstallNoHBA2" style="width:160px" >
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${HouseAdvance.oddInterestInstallmentNo == OddInstallNo.lookupId}">
									<option value="${OddInstallNo.lookupId}" selected="selected"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${OddInstallNo.lookupId}"><c:out
										value="${OddInstallNo.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</select>
					</td>
			</tr>
			
				<tr id="trHideColMsgDis3" ${varDisplayNoneDis3}>
					<td width="10%" ><fmt:message key="CMN.DISBURSEMENT3" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%" id="tdHideColDis3">
					<c:if test="${resValue.userType == HODASST2HA}">
						<c:choose>
								<c:when test="${HouseAdvance.advanceSubType == 800038 && (Disburse == 'Want3rdCF' || Disburse =='Want3rdLC' || Disburse =='Want4thLC')}">
									<input type="text"  name='txtDisbursement3' id="txtDisbursement3" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.30}" readonly="readonly"/>
								</c:when>
								<c:when test="${HouseAdvance.advanceSubType == 800058 && (Disburse == 'Want3rdCF' || Disburse =='Want3rdLC' || Disburse =='Want4thLC')}">
									<input type="text"  name='txtDisbursement3' id="txtDisbursement3" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.40}" readonly="readonly"/>
								</c:when>		
								<c:when test="${HouseAdvance.advanceSubType == 800058 && Disburse == 'Club3rd4thLC'}">
									<input type="text"  name='txtDisbursement3' id="txtDisbursement3" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned *0.50}" readonly="readonly"/>
								</c:when>	
								<c:otherwise>										
								<c:if test="${HouseAdvance.disbursementThree != null && Disburse == null}">
									<input type="text"  name='txtDisbursement3' id="txtDisbursement3" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementThree}" readonly="readonly"/>
								</c:if>																
								</c:otherwise>	
						</c:choose>
					</c:if>
					<c:if test="${resValue.userType == HODHA}">
							<input type="text"  name='txtDisbursement3' id="txtDisbursement3" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementThree}" readonly="readonly"/>
					</c:if>				
					</td>		
					<td width="15%" align="left" >	
					</td>
					<td width="20%" align="left">
								
					</td>	
				</tr>			
				<tr id="trHideColMsgRel3" ${varDisplayNoneDis3}>
				<td width="10%"><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
				<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
				<td width="20%">
					<fmt:formatDate value="${HouseAdvance.releaseDateThree}" pattern="dd/MM/yyyy" var="ReleaseDate3"/>
					<input type="text" name="txtReleaseDate3" id="txtReleaseDate3" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);validateNoOfInstallmentsHBA();" value = "${ReleaseDate3}" ${varReadonlyHODASST} ${varReadOnlyReleaseDate3}/>
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate3",375,570)' ${varImageDisabledHODASST} ${varImageDisabledReleaseDate3}/>
					<label class="mandatoryindicator" ${varImageDisabledHODASST} ${varImageDisabledReleaseDate3}>*</label>
				</td>					
				</tr>
				<c:if test="${HouseAdvance.advanceSubType != 800058}">
					<c:set var="trDisbursement" value="style='display: none;'"></c:set>
				</c:if>
				<tr id="trHideColMsgDis4" ${trDisbursement} ${varDisplayNoneDis4}>
					<td width="10%" ><fmt:message key="CMN.DISBURSEMENT4" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%" id="tdHideColDis4">
					<c:if test="${resValue.userType == HODASST2HA}">
						<c:choose>
							<c:when test="${HouseAdvance.advanceSubType == 800058  && Disburse =='Want4thLC'}">
								<input type="text"  name='txtDisbursement4' id="txtDisbursement4" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.amountSanctioned * 0.10}" readonly="readonly"/>
							</c:when>
							<c:otherwise>										
								<c:if test="${HouseAdvance.disbursementFour != null && Disburse == null}">
									<input type="text"  name='txtDisbursement4' id="txtDisbursement4" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementFour}" readonly="readonly"/>
								</c:if>															
							</c:otherwise>	
						</c:choose>
					</c:if>
					<c:if test="${resValue.userType == HODHA}">
						<input type="text"  name='txtDisbursement4' id="txtDisbursement4" style="text-align: right" onkeypress="digitFormat(this);" value="${HouseAdvance.disbursementFour}" readonly="readonly"/>
					</c:if>
					</td>					
				</tr>
				<tr id="trHideColMsgRel4" ${trDisbursement}${varDisplayNoneDis4}>
					<td width="10%"><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>
					<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
					<td width="20%">
						<fmt:formatDate value="${HouseAdvance.releaseDateFour}" pattern="dd/MM/yyyy" var="ReleaseDate4"/>
						<input type="text" name="txtReleaseDate4" id="txtReleaseDate4" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);validateNoOfInstallmentsHBA();" value = "${ReleaseDate4}" ${varReadonlyHODASST} />
						<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtReleaseDate4",375,570)' ${varImageDisabledHODASST} />
						<label class="mandatoryindicator" ${varImageDisabledHODASST}>*</label>
					</td>					
				</tr>				
</table>		
</fieldset>
<br>
<c:if test="${resValue.userType == HODASST2HA || resValue.userType == HODHA }">
<fieldset>
		<table>
			<tr>
				<td width="5%"><fmt:message key="CMN.HODASSTREMARKS" bundle="${lnaLabels}"></fmt:message></td>
				<td width="20%" ><input type="text" id="txtUserRemarksHBA" name="txtUserRemarksHBA" size="85" value="${HouseAdvance.userRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled}${varDisabledHO}${varDisabledHOD} ${varReadonlyHOD}/></td>
			</tr>
		</table>
<br>
<c:if test="${resValue.userType == DEOHA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtApproverRemarksHBA" name="txtApproverRemarksHBA" size="85" value="${HouseAdvance.approverRemarks}" ${varDisabledHO}${varDisabledHODASST}/></td>
	</tr>
</table>
<br>
</c:if>
<c:if test="${resValue.userType == HODHA && resValue.requestType == HouseAdvanceHA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtHORemarks" name="txtHORemarks" size="85" value="${HouseAdvance.hoRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled}${varDisabledHODASST}/></td>
	</tr>
</table>
<br>
</c:if>
</fieldset>
</c:if>
</td></tr>
</table>
