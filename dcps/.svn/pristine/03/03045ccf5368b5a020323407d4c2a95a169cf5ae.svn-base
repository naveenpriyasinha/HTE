<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empVO" value="${resValue.LNAEmpVO}"/>
<c:set var="MotorAdvance" value="${resValue.MotorAdvance}"/>
<c:set var="EmpBankDtls" value="${resValue.EmpBankDtls}"/>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>
<fmt:message key="CMN.HOD" bundle="${lnaLabels}" var="HODMA"></fmt:message>
<fmt:message key="CMN.DEO" bundle="${lnaLabels}" var="DEOMA"></fmt:message>
<fmt:message key="CMN.HODASST2" bundle="${lnaLabels}" var="HODASST2MA"></fmt:message>
<fmt:message key="CMN.HODASST" bundle="${lnaLabels}" var="HODASSTMA"></fmt:message>
<fmt:message key="CMN.MOTORADVC" bundle="${lnaLabels}" var="MotorAdvanceMA"></fmt:message>
<fmt:message key="CMN.DEOAPP" bundle="${lnaLabels}" var="DEOAPPMA"></fmt:message>
<c:if test="${MotorAdvance.motorAdvanceId!=null}">
<c:if test="${resValue.userType == DEOAPPMA||resValue.userType == HODASSTMA}">
	<c:set var="varReadonly" value="true"></c:set>
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
</c:if>
</c:if>
	<c:if test="${resValue.userType == HODASSTMA || resValue.userType == HODASST2MA}">
	<c:set var="varReadonlyHODASST" value="readOnly='readOnly'"></c:set>
	<c:set var="varDisabledHODASST" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabledHODASST" value="style='display:none'"></c:set>
	<c:set var="removeAttachmentAccess" value="Y"/>
</c:if>
<c:if test="${resValue.userType == HODMA}">
	<c:set var="varDisabledHOD" value="disabled='disabled'"></c:set>
</c:if>
<c:if test="${resValue.userType == DEOMA}">
	<c:set var="varDisabledDEO" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledDEO" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.userType == HO || resValue.userType == HODMA}">
	<c:set var="varDisabledHO" value="disabled='disabled'"></c:set>
	<c:set var="varReadonly" value="true"></c:set>
	<c:set var="varReadonlyHOD" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabledHO" value="style='display:none'"></c:set>
	<c:set var="varAttachmentDisabled" value="Y"></c:set>
	<c:set var="removeAttachmentAccess" value="N"/>
</c:if>
<c:choose>
<c:when test="${MotorAdvance.oddInstallmentNumber == null}">
	<c:set var="varDisabledOddInstCombo" value="disabled='disabled'"></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisabledOddInstCombo" value=""></c:set>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${MotorAdvance.oddInterestInstallmentNo == null}">
	<c:set var="varDisabledOddInterInstCombo" value="disabled='disabled'"></c:set>
</c:when>
<c:otherwise>
	<c:set var="varDisabledOddInterInstCombo" value=""></c:set>
</c:otherwise>
</c:choose>
<input type="hidden"  name='hidMotorAdvanceId' id="hidMotorAdvanceId"  value="${MotorAdvance.motorAdvanceId}" />
<input type="hidden" name="hidCheckListsMCA" id="hidCheckListsMCA" value="0" />

<table width="70%">
<tr><td>
<fieldset>
<table align="left">
	<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.LOANTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%" align="left" ><b><fmt:message key="CMN.VA" bundle="${lnaLabels}"></fmt:message></b></td>
			<td width="15%"><fmt:message key="CMN.SUBTYPE" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">				
				<select name="cmbVehicleSubType" id="cmbVehicleSubType" style="width:200px" ${varDisabled} ${varDisabledHO} onchange="validateNewOrOldVehicle();validateReqAmountMCA(1);changeSancDtlsMCA();popupSancDtlsMCA(1);">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="LoanSubType" items="${resValue.lstVehicleSubType}">
					<c:choose>
						<c:when test="${MotorAdvance.advanceSubType == LoanSubType.lookupId}">
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
	<tr>
	<td width="15%" align="left" ><fmt:message key="CMN.SUBCATEGORY" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%" align="left" >		
			<c:choose>
				<c:when test="${MotorAdvance.motorAdvanceId!=null}">
					<c:choose>
						<c:when test="${MotorAdvance.subCategory=='N'}">
							<input type="radio" id="rdoNew" name="rdoVehicleType" value="N" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);" checked="checked" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.NEW" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;			
							<input type="radio" id="rdoOld" name="rdoVehicleType" value="O" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.OLD" bundle="${lnaLabels}"></fmt:message>						
						</c:when>
						<c:otherwise>
							<input type="radio" id="rdoNew" name="rdoVehicleType" value="N" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);"  ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.NEW" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;			
							<input type="radio" id="rdoOld" name="rdoVehicleType" value="O" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);" checked="checked" ${varDisabled}${varDisabledHO}/>
							<fmt:message key="CMN.OLD" bundle="${lnaLabels}"></fmt:message>
						</c:otherwise>
					</c:choose>			
				</c:when>
			<c:otherwise>
				<input type="radio" id="rdoNew" name="rdoVehicleType" value="N" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);" checked="checked" />
				<fmt:message key="CMN.NEW" bundle="${lnaLabels}"></fmt:message>&nbsp;	&nbsp;			
				<input type="radio" id="rdoOld" name="rdoVehicleType" value="O" onclick="validateNewOrOldVehicle();validateReqAmountMCA(1);" />
				<fmt:message key="CMN.OLD" bundle="${lnaLabels}"></fmt:message>	
			</c:otherwise>
			</c:choose>
			</td>
			<td width="15%"><fmt:message key="CMN.PAYCOMMGR" bundle="${lnaLabels}"></fmt:message></td>
			<td width="20%">				
			<select name="cmbPayCommissionMCA" id="cmbPayCommissionMCA" style="width:200px" ${varDisabled}${varDisabledHO} onchange="validateReqAmountMCA(1);validateNewOrOldVehicle();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="PayCommission" items="${resValue.PayCommissionGR}">
					<c:choose>
						<c:when test="${MotorAdvance.payCommissionGR == PayCommission.lookupId}">
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
			<fmt:formatDate value="${MotorAdvance.applicationDate}" pattern="dd/MM/yyyy" var="appDate"/>
			<input type="text" name="txtAppDateMCA" id="txtAppDateMCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);greaterThanCurrDateValidation(this);popupSancDtlsMCA(1);" value = "${appDate}" ${varDisabled}${varDisabledHO}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtAppDateMCA",375,570)'   ${varImageDisabled}${varImageDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label></td>
		</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.REQAMOUNT" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtReqAmountMCA' id="txtReqAmountMCA" maxlength="10" onkeypress="digitFormat(this);" style="text-align: right" onblur="validateReqAmountMCA(1);popupSancDtlsMCA(1)" value="${MotorAdvance.amountRequested}"  ${varDisabled}${varDisabledHO}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
		</td>
		<td width="15%" align="left" ><fmt:message key="CMN.EXSHOWPRICE" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtExShowPriceMCA' id="txtExShowPriceMCA" onkeypress="digitFormat(this);" onblur="popupSancDtlsMCA(1);" maxlength="10" style="text-align: right" value="${MotorAdvance.exshowroomPrice}"  ${varDisabled}${varDisabledHO}/>
		</td>

	</tr>
	<tr>
		<td width="15%" align="left"></td>
		<td align="left" width="20%"></td>
		<td width="15%" align="left" ><fmt:message key="CMN.INTERESTRATE" bundle="${lnaLabels}"></fmt:message></td>
		<td align="left" width="20%">
			<input type="text"  size="20%" name='txtInterestRateMCA' id="txtInterestRateMCA" onkeypress="digitFormat(this);" style="text-align: right" value="${MotorAdvance.interestRate}"  readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
		<fieldset>
			<jsp:include page="/WEB-INF/jsp/lna/ProofAttachment.jsp">
				<jsp:param name="attachmentName" value="ProofMCA" />
				<jsp:param name="formName" value="LNARequestProcessForm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="attachmentSize" value="1" /> 
				<jsp:param name="multiple" value="N" />				
				<jsp:param name="removeAttachmentFromDB" value="${removeAttachmentAccess}"/>
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
<fieldset><legend><fmt:message key="CMN.CHECKLISTS" bundle="${lnaLabels}"></fmt:message></legend><br>

<input type="hidden" name="hidRowCountMCA" id="hidRowCountMCA" value="3" />
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
<div id="ChecklistMCA" ${hideDivForOldChecklist}>
<table align="left" width="100%" id="tblChecklistMCA">
<tbody>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Application for Vehicle Advance"/>
		<fmt:message key="CMN.MOTORCARADVNAPP" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Permanent License"/>
		<fmt:message key="CMN.PERMANENTLICENCE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Quotation Document"/>
		<fmt:message key="CMN.QUTATIONDOC" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="RC Book"/>
		<fmt:message key="CMN.RCBOOK" bundle="${lnaLabels}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Efficiency Certificate"/>
		<fmt:message key="CMN.EFFICIENCYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">		
		<input type="checkbox" name="cbDocCheckListMCA" id="cbDocCheckListMCA" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
</tbody>	
</table>
</div>
<div id="ChecklistMCAHandicap" ${hideDivForOldChecklist} style="display: none;">
<table align="left" width="100%" id="tblChecklistMCAHandicap">
<tbody>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Application for Vehicle Advance"/>
		<fmt:message key="CMN.MOTORCARADVNAPP" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Salary Certificate"/>
		<fmt:message key="CMN.SALARYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Gratuity Recovery Certificate"/>
		<fmt:message key="CMN.GRATUITYRECOVERCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Prescribed Format Form"/>
		<fmt:message key="CMN.ENTRYFORM" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Quotation Document"/>
		<fmt:message key="CMN.QUTATIONDOC" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="RC Book"/>
		<fmt:message key="CMN.RCBOOK" bundle="${lnaLabels}"></fmt:message>
		</td>
	</tr>
	<tr>
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Efficiency Certificate"/>
		<fmt:message key="CMN.EFFICIENCYCERT" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>			
		<td width="33%">
		<input type="checkbox" name="cbDocCheckListMCAHC" id="cbDocCheckListMCAHC" value="Family Certificate"/>
		<fmt:message key="CMN.FAMILYCERTIFICATE" bundle="${lnaLabels}"></fmt:message>
		<label class="mandatoryindicator" ${varImageDisabledHO}>*</label>
		</td>
		<td width="33%">		
		</td>
	</tr>
</tbody>	
</table>
</div>
<table align="left" width="100%" id="tblChecklistMCANew" ${hideDivForNewChecklist}>
<tbody>
<c:set value="1" var="count" />
<c:set value="1" var="rowCount" />
<tr> 
<c:forEach items="${resValue.Checklist}" var="CheckBoxList" varStatus="Counter">
<c:if test="${MotorAdvance.advanceSubType!=800035}">
<c:choose>
<c:when test="${count<10}">
			<td>
				<c:if test="${CheckBoxList[1]=='Y'}">
					<c:set var="varChecked" value="checked='checked'"></c:set>
				</c:if>
				<c:if test="${CheckBoxList[1]=='N'}">
					<c:set var="varChecked" value=""></c:set>
				</c:if>
				<input type="checkbox" name="cbDocCheckListMCANew" id="cbDocCheckListMCANew" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>	
					<c:if test="${CheckBoxList[0]!='RC Book'}">	
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
		<input type="checkbox" name="cbDocCheckListMCANew" id="cbDocCheckListMCANew" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistMCANew");' ${varImageDisabledHO}/>
	</td>
</tr>
<c:set value="${count+1}" var="count"/>
</c:otherwise>
</c:choose>
</c:if>
<c:if test="${MotorAdvance.advanceSubType==800035}">
<c:choose>
<c:when test="${count<9}">
			<td>
				<c:if test="${CheckBoxList[1]=='Y'}">
					<c:set var="varChecked" value="checked='checked'"></c:set>
				</c:if>
				<c:if test="${CheckBoxList[1]=='N'}">
					<c:set var="varChecked" value=""></c:set>
				</c:if>
				<input type="checkbox" name="cbDocCheckListMCANew" id="cbDocCheckListMCANew" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled}${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>	
					<c:if test="${CheckBoxList[0]!='RC Book'}">	
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
		<input type="checkbox" name="cbDocCheckListMCANew" id="cbDocCheckListMCANew" value="${CheckBoxList[0]}" ${varChecked}  ${varDisabled} ${varDisabledHO}/>
					<c:out value="${CheckBoxList[0]}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img name="Image" id="Image" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblChecklistMCANew");' ${varImageDisabledHO}/>
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
<input type="hidden" name="hidRowCountMCA" id="hidRowCountMCA" value="${rowCount}" />
</fieldset>
<center>		<hdiits:button type="button" captionid="BTN.ADDNEW" bundle="${lnaLabels}" id="btnAddNewCheckListMCA" name="btnAddNewCheckListMCA" onclick="addNewCheckListMCA();" readonly="${varReadonly}"/></center>
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
			<td width="15%" align="left" ><fmt:message key="CMN.SANCDATE" bundle="${lnaLabels}">
			<font size="1"><fmt:message key="CMN.DATEFORMAT" bundle="${lnaLabels}" ></fmt:message></font></fmt:message>	
			</td>
			<td width="20%" align="left">
			<fmt:formatDate value="${MotorAdvance.sanctionedDate}" pattern="dd/MM/yyyy" var="sanctionedDate"/>
			<input type="text" name="txtSanctionedDateMCA" id="txtSanctionedDateMCA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);lessThanCurrDateValidation(this);" value = "${sanctionedDate}" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtSanctionedDateMCA",375,570)' ${varImageDisabled}${varImageDisabledDEO}${varImageDisabledHODASST}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledDEO}${varImageDisabledHODASST}>*</label>			
			</td>
			<td width="15%" align="left"></td>
			<td width="20%" align="left"></td>
	</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCAMT" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancAmountMCA' id="txtSancAmountMCA" style="text-align: right" value="${MotorAdvance.amountSanctioned}" onkeypress="digitFormat(this);" onblur="validateReqAmountMCA(2);validateNoOfInstallmentsMCA();popupSancDtlsMCA(2);" ${varDisabledDEO} ${varDisabled}${varReadonlyHODASST}/>
			</td>		
			<td width="15%" align="left" ><fmt:message key="CMN.INTERESTAMOUNT" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInterestAmountMCA' id="txtInterestAmountMCA" style="text-align: right" value="${MotorAdvance.interestAmount}" onkeypress="digitFormat(this);" onblur="validateReqAmountMCA(2);validateNoOfInstallmentsMCA();popupSancDtlsMCA(2);" ${varDisabledDEO} ${varDisabled}${varReadonlyHODASST}/>
			</td>
		
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCPRININSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancPrincipalInstallMCA' id="txtSancPrincipalInstallMCA" style="text-align: right" onkeypress="digitFormat(this);" value="${MotorAdvance.sancCapitalInst}" onblur="validateNoOfInstallmentsMCA();" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.SANCINTERINSTALLMENTS" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtSancInterInstallMCA' id="txtSancInterInstallMCA" style="text-align: right" value="${MotorAdvance.sancInterestInst}" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsMCA();" ${varDisabled}/>
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
		</tr>
		<tr>
			<td width="15%" align="left" ><fmt:message key="CMN.PRININSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtPrinInstallmentAmountMCA' id="txtPrinInstallmentAmountMCA" style="text-align: right" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsMCA();" value="${MotorAdvance.cappitalInstAmtMonth}" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			</td>
			<td width="15%" align="left" ><fmt:message key="CMN.INTINSTALLMENTSMONTH" bundle="${lnaLabels}"></fmt:message>	
			</td>
			<td width="20%" align="left">
			<input type="text"  name='txtInterInstallmentAmountMCA' id="txtInterInstallmentAmountMCA" style="text-align: right" onkeypress="digitFormat(this);" onblur="validateNoOfInstallmentsMCA();" value="${MotorAdvance.interestInstAmtMonth}" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
			</td>
		</tr>
		<tr>
				<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLMENT" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
					<input type="text"  name='txtOddPrincipalInstallmentAmtMCA' id="txtOddPrincipalInstallmentAmtMCA" style="text-align: right" value="${MotorAdvance.oddInstallment}" onblur="validateNoOfInstallmentsMCA();" onkeypress="digitFormat(this);" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
					<td width="15%" ><fmt:message key="CMN.ODDINTERESTINSTALLMENT" bundle="${lnaLabels}"></fmt:message>	
					</td>
					<td width="20%">
					<input type="text"  name='txtOddInterestInstallmentAmtMCA' id="txtOddInterestInstallmentAmtMCA" style="text-align: right"  value="${MotorAdvance.oddInterestInstallment}" onblur="validateNoOfInstallmentsMCA();" onkeypress="digitFormat(this);" ${varDisabled}${varDisabledDEO}${varReadonlyHODASST}/>
					</td>
				</tr>
				<tr>
				<td width="15%" ><fmt:message key="CMN.ODDPRINCIPALINSTALLNO" bundle="${lnaLabels}"></fmt:message></td>
					<td width="20%">				
						<select name="cmbOddPrincipalInstallNoMCA" id="cmbOddPrincipalInstallNoMCA" style="width:160px" ${varDisabled}${varDisabledOddInstCombo}>
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${MotorAdvance.oddInstallmentNumber == OddInstallNo.lookupId}">
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
					<td width="20%"><select name="cmbOddInterestInstallNoMCA" id="cmbOddInterestInstallNoMCA" style="width:160px" ${varDisabled}${varDisabledOddInterInstCombo}>
						<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="OddInstallNo" items="${resValue.OddInstallNo}">
							<c:choose>
								<c:when test="${MotorAdvance.oddInterestInstallmentNo == OddInstallNo.lookupId}">
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
</fieldset>
<br>
<c:if test="${resValue.userType == HODASST2MA || resValue.userType == HODMA }">
<fieldset>
		<table>
			<tr>
				<td width="5%"><fmt:message key="CMN.HODASSTREMARKS" bundle="${lnaLabels}"></fmt:message></td>
				<td width="20%" ><input type="text" id="txtUserRemarksMCA" name="txtUserRemarksMCA" size="85" value="${MotorAdvance.userRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled}${varDisabledHO}${varDisabledHOD} ${varReadonlyHOD}/></td>
			</tr>
		</table>
<c:if test="${resValue.userType == DEOMA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.VERIFIERREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtApproverRemarksMCA" name="txtApproverRemarksMCA" size="85" value="${MotorAdvance.approverRemarks}" ${varRemarksDisabled}${varDisabledHO}${varDisabledHODASST}/></td>
	</tr>
</table>
</c:if>
<br>
<c:if test="${resValue.userType == HODMA && resValue.requestType == MotorAdvanceMA}">
<table>
	<tr>
		<td width="5%"><fmt:message key="CMN.HODREMARKS" bundle="${lnaLabels}"></fmt:message></td>
		<td width="20%" ><input type="text" id="txtHORemarks" name="txtHORemarks" size="85" value="${MotorAdvance.hoRemarks}" onblur="validateRemarks(this,'Special characters are not allowed');" ${varDisabled}${varDisabledHODASST}/></td>
	</tr>
</table>
<br>
</c:if>
</fieldset>
</c:if>
</td></tr>
</table>
