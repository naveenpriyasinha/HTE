<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empVO" value="${resValue.LNAEmpVO}"/>
<c:set var="comAdvanceVO" value="${resValue.savedCompAdvance}"/>
<c:set var="Checklist" value="${resValue.Checklist}"/>
<c:set var="EmpBankDtls" value="${resValue.EmpBankDtls}"/>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>
<fmt:message key="CMN.HOD" bundle="${lnaLabels}" var="HODCA"></fmt:message>
<fmt:message key="CMN.DEO" bundle="${lnaLabels}" var="DEOCA"></fmt:message>
<fmt:message key="CMN.HODASST2" bundle="${lnaLabels}" var="HODASST2CA"></fmt:message>
<fmt:message key="CMN.HODASST" bundle="${lnaLabels}" var="HODASSTCA"></fmt:message>
<fmt:message key="CMN.COMPADVC" bundle="${lnaLabels}" var="ComputerAdvanceCA"></fmt:message>
<fmt:message key="CMN.DEOAPP" bundle="${lnaLabels}" var="DEOAPPCA"></fmt:message>

<c:if test="${comAdvanceVO.computerAdvanceId!=null}">
<c:if test="${resValue.userType == DEOAPPCA || resValue.userType == HODASSTCA}">
	<c:set var="varReadonly" value="true"></c:set>
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
</c:if>
</c:if>
<c:if test="${resValue.userType == HODASSTCA || resValue.userType == HODASST2CA}">
	<c:set var="varReadonlyHODASST" value="readOnly='readOnly'"></c:set>
	<c:set var="varDisabledHODASST" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHODASST" value="style='display:none'"></c:set>
	<c:set var="removeAttachmentAccess" value="Y"/>	
</c:if>
<c:if test="${resValue.userType == HODCA}">
<c:set var="varDisabledHOD" value="disabled='disabled'"></c:set>
</c:if>
<c:if test="${resValue.userType == DEOCA}">
	<c:set var="varDisabledDEO" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == HO || resValue.userType == HODCA}">
	<c:set var="varDisabledHO" value="disabled='disabled'"></c:set>
	<c:set var="varReadonly" value="true"></c:set>
	<c:set var="varReadonlyHOD" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
	<c:set var="removeAttachmentAccess" value="N"/>
</c:if>
<c:choose>
<c:when test="${comAdvanceVO.oddInstallmentNumber == null}">
	<c:set var="varDisabledOddInstCombo" value="disabled='disabled'"></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisabledOddInstCombo" value=""></c:set>
</c:otherwise>
</c:choose>
<input type="hidden"  name='hidComAdvanceId' id="hidComAdvanceId"  value="${comAdvanceVO.computerAdvanceId}" />
<input type="hidden" name="hidCheckListsCA" id="hidCheckListsCA" value="0" />
<table width="70%">
<tr><td >
<fieldset>
<table width="100%">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.LOANTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%" align="left" ><b><fmt:message key="CMN.CA" bundle="${lnaLabels}"></fmt:message></b></td>
			<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">				
				<select name="cmbComputerSubType" id="cmbComputerSubType" style="width:170px" ${varDisabled} ${varDisabledHO}>
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstComputerSubType}">
					<c:choose>
						<c:when test="${comAdvanceVO.advanceSubType == LoanSubType.lookupId}">
							<option value="${LoanSubType.lookupId}" selected="selected"><c:out
								value="${LoanSubType.lookupDesc}"></c:out></option>
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
			<td width="15%" align="left" ><fmt:message key="CMN.PHYAPPDATE" bundle="${lnaLabels}"></fmt:message>
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
			<td width="20%">
			<fmt:formatDate value="${comAdvanceVO.applicationDate}" pattern="dd/MM/yyyy" var="appDate"/>
			<input type="text" name="txtAppDateCA" id="txtAppDateCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);greaterThanCurrDateValidation(this);popupSancDetailsCA();" value = "${appDate}" ${varDisabled}${varDisabledHO}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtAppDateCA",375,570)' ${varImageDisabled}${varImageDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label></td>
		</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.REQAMOUNT" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtReqAmountCA' id="txtReqAmountCA" maxlength="10" onkeypress="digitFormat(this);" style="text-align: right" onblur="validateReqAmountCA(1);popupSancDetailsCA();" value="${comAdvanceVO.amountRequested}"  ${varDisabled}${varDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.ACTUALCOST" bundle="${lnaLabels}"></fmt:message></td>		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtActualCostCA' id="txtActualCostCA" onkeypress="digitFormat(this);" maxlength="10" style="text-align: right" onblur="popupSancDetailsCA();validateNoOfInstallmentsCA();" value="${comAdvanceVO.actualCost}"  ${varDisabled}${varDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>

	</tr>
	<tr>
		<td width="15%" align="left"></td>
		<td align="left" width="20%"></td>
		<td width="15%" align="left" ><fmt:message key="CMN.INTERESTRATE" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtInterestRateCA' id="txtInterestRateCA" maxlength="10" style="text-align: right" value="0" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<fieldset>
			<jsp:include page="/WEB-INF/jsp/lna/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="ProofCA" />
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
<input type="hidden" name="hidRowCountCA" id="hidRowCountCA" value="4" />
<table align="left" width="100%" id="tblChecklistCA">
<tbody>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Computer Advance Application"/>
		<fmt:message key="CMN.COMADVNAPP" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Efficiency Certificate"/>
		<fmt:message key="CMN.EFFICIENCYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="MSCIT/CT certificate"/>
		<fmt:message key="CMN.MSCITCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Advance not taken earlier from GPF"/>
		<fmt:message key="CMN.ADVNNOTTAKEN" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Quotation Document"/>
		<fmt:message key="CMN.QUTATIONDOC" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
</tbody>
</table>
</c:when>
<c:otherwise>
<c:set value="1" var="count" />
<c:set value="1" var="rowCount" />
<table align="left" width="100%" id="tblChecklistCA" >
<tbody>
<tr> 
<c:forEach items="${resValue.Checklist}" var="CheckBoxList">
<c:choose>
<c:when test="${count<10}">
			<td>
				<c:if test="${CheckBoxList[1]=='Y'}">
					<c:set var="varChecked" value="checked='checked'"></c:set>
				</c:if>
				<c:if test="${CheckBoxList[1]=='N'}">
					<c:set var="varChecked" value=""></c:set>
				</c:if>
				<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>
					<c:if test="${CheckBoxList[0]!='Gratuity Recovery Certificate'}">	
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
	<td>
		<c:if test="${CheckBoxList[1]=='Y'}">
			<c:set var="varChecked" value="checked='checked'"></c:set>
		</c:if>
		<c:if test="${CheckBoxList[1]=='N'}">
			<c:set var="varChecked" value=""></c:set>
		</c:if>
		<input type="checkbox" name="cbDocCheckListCA" id="cbDocCheckListCA" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistCA");' ${varImageDisabledHO}/>
	</td>
</tr>
<c:set value="${count+1}" var="count"/>
</c:otherwise>
</c:choose>
</c:forEach>
</tr>
</tbody>
</table>
<input type="hidden" name="hidRowCountCA" id="hidRowCountCA" value="${rowCount}" />
</c:otherwise>
</c:choose>
</fieldset>

<center><hdiits:button type="button" captionid="BTN.ADDNEW" bundle="${lnaLabels}" id="btnAddNewCheckListCA" name="btnAddNewCheckListCA" onclick="addNewCheckListCA();" readonly="${varReadonly}"/></center>

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
<table align="left" cellpadding="4">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCAMT" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancAmountCA' id="txtSancAmountCA" style="text-align: right" value="${comAdvanceVO.amountSanctioned}" onkeypress="digitFormat(this);" onblur="validateReqAmountCA(2);validateNoOfInstallmentsCA();" ${varDisabledDEO} ${varDisabled}${varReadonlyHODASST}/>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCINSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancInstallmentsCA' id="txtSancInstallmentsCA"  style="text-align: right" value="${comAdvanceVO.sancInstallments}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsCA();" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled} ${varImageDisabledHO}>*</label>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}"></fmt:message>	
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></td>
			<td width="20%" align="left">
			<fmt:formatDate value="${comAdvanceVO.sanctionedDate}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
			<input type="text" name="txtSanctionedDateCA" id="txtSanctionedDateCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);" value = "${sanctionedDate}" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateCA",375,570)' ${varImageDisabled}${varImageDisabledDEO}${varImageDisabledHODASST}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledDEO}${varImageDisabledHODASST}>*</label>			
			</td>		
			<td width="15%" align="left" ><fmt:message key="CMN.INSTALLMENTAMT" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInstallmentAmountCA' id="txtInstallmentAmountCA" style="text-align: right" value="${comAdvanceVO.installmentAmount}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsCA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.ODDINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">				
				<select name="cmbOddInstallNoCA" id="cmbOddInstallNoCA" style="width:160px" ${varDisabled} ${varDisabledOddInstCombo}>
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
					<c:choose>
						<c:when test="${comAdvanceVO.oddInstallmentNumber == OddInstallNo.lookupId}">
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
			<td width="15%" align="left" ><fmt:message key="CMN.ODDINSTALLMENT" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtOddInstallmentAmtCA' id="txtOddInstallmentAmtCA" style="text-align: right" value="${comAdvanceVO.oddInstallment}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsCA();" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			</td>
		
		</tr>
</table>
</fieldset>
<br>
<c:if test="${resValue.userType == HODASST2CA || resValue.userType == HODCA }">
<fieldset>
		<table>
			<tr>
				<td width="5%"><fmt:message key="CMN.HODASSTREMARKS" bundle="${lnaLabels}"></fmt:message></td>
				<td width="20%" ><input type="text" id="txtUserRemarksCA" name="txtUserRemarksCA" size="85" value="${comAdvanceVO.userRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled} ${varDisabledHO}${varDisabledHOD} ${varReadonlyHOD}/></td>
			</tr>
		</table>
<c:if test="${resValue.userType == DEOCA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtApproverRemarksCA" name="txtApproverRemarksCA" size="85" value="${comAdvanceVO.approverRemarks}" ${varDisabledHO}${varDisabledHODASST}/></td>
	</tr>
</table>
</c:if>
<br>

<c:if test="${resValue.userType == HODCA && resValue.requestType == ComputerAdvanceCA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtHORemarks" name="txtHORemarks" size="85" value="${comAdvanceVO.hoRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled}${varDisabledHODASST}/></td>
	</tr>
</table>
<br>
</c:if>
</fieldset>
</c:if>
</td></tr>
</table>