<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/SixthPayArrearStatement.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />

<script type="text/javascript">
LISTDAIN='<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>';
</script>

<hdiits:form name="SixthPayArrearStmt" validate="true"  method="post" >
	
<hdiits:hidden name="hidPensnReqId" id="hidPensnReqId" />
<hdiits:hidden name="hidPnsnrCode" id="hidPnsnrCode"/>
<hdiits:hidden name="hidppono" id="hidppono"/>
<hdiits:hidden name="hidheadcode" id="hidheadcode"  />
<hdiits:hidden name="hidlastBillMonth" id="hidlastBillMonth"/>
<hdiits:hidden name="hidFamMemDOB" id="hidFamMemDOB"/>
<hdiits:hidden name="hidFamMemDOD" id="hidFamMemDOD" />
<hdiits:hidden name="hidPnsnrDOB" id="hidPnsnrDOB" />
<hdiits:hidden name="hidPnsnrDOR" id="hidPnsnrDOR" />
<hdiits:hidden name="hidPnsnrDOD" id="hidPnsnrDOD" />
<hdiits:hidden name="supplyFlg" id="supplyFlg" />

<hdiits:hidden name="hidFP1Date" id="hidFP1Date"/>
<hdiits:hidden name="hidFP2Date" id="hidFP2Date" />
<hdiits:hidden name="hidNewFP1Basic" id="hidNewFP1Basic" />
<hdiits:hidden name="hidNewFP2Basic" id="hidNewFP2Basic"/>
<hdiits:hidden name="hidCommensionDate" id="hidCommensionDate" />
<hdiits:hidden name="hidEndDate" id="hidEndDate"/>
<hdiits:hidden name="hidBankName" id="hidBankName"/>
<hdiits:hidden name="hidBranchName" id="hidBranchName"/>
<hdiits:hidden name="hidAccountNo" id="hidAccountNo"/>
<hdiits:hidden name="hidCurrentDt" id="hidCurrentDt"/>
<hdiits:hidden name="hidVolumeNo" id="hidVolumeNo"/>
<hdiits:hidden name="hidPageNo" id="hidPageNo"/>
<hdiits:hidden name="hidTreasuryName" id="hidTreasuryName"/>
<input type="hidden" name="hidBillFlag" id="hidBillFlag" value="${resValue.BillFlag}"/>

	<fieldset style="width:100%" class="tabstyle" >
		<legend ><fmt:message key="PPMT.SIXTHARRERSTMT" bundle="${pensionLabels}"></fmt:message></legend>
		
		<table width="100%">
			<tr id="trBankDtls">
				<td align="left" ><fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					 <select name="cmbBankName" id="cmbBankName"   style="width: 90%" >
	      	         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					    <c:forEach var="bank" items="${resValue.lLstBanks}">
									<option value='${bank.id}'>
											<c:out value="${bank.desc}"></c:out>
									</option>
						</c:forEach>
			    
		    		</select>
				</td>
				<td align="left" ><fmt:message key="PPMT.BANKBRNNAME" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<select name="cmbBankBranch" id="cmbBankBranch"   style="width: 100%" onchange="">
					    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
							<c:forEach var="branchList" items="${resValue.lLstBankBranch}">
									<option value="${branchList.id}">
										<c:out value="${branchList.desc}"></c:out>
									</option>	
							</c:forEach>		
	             </select>
				</td>
				<td align="left" ><fmt:message key="PPMT.ACCNO" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtAccountNo"  size="20"  name="txtAccountNo" onblur="getPnsnrDtlsFromBankBranch();" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="PPMT.PPONUMBER" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPPONO"  size="20"  name="txtPPONO"  style="display: ;text-transform: uppercase;"  onblur="validatePPONumber();" /><label class="mandatoryindicator">*</label>
				</td>
				<td align="left" ><fmt:message key="PPMT.DOB" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOB"  size="20"  name="txtDOB" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPensionerName"  size="30"  name="txtPensionerName" disabled="disabled"/>
					<input type="hidden" id="hdnPensionerName" name="hdnPensionerName"/>
				</td>
				<td align="left" ><fmt:message key="PPMT.DOR" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOR"  size="20"  name="txtDOR" disabled="disabled"/>
					
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="PPMT.ADVANCEAMT" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtAdvanceAmt"  size="20"  name="txtAdvanceAmt" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>
					
				</td>
				<td align="left" ><fmt:message key="PPMT.RECOVERYAMT" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtRecoveryAmt"  size="20"  name="txtRecoveryAmt" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/>
					
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		
		 
		<fieldset style="width:100%" class="tabstyle" >
			<legend id="headingMsg"><b>Pension Basic Details</b></legend>
			<input type="hidden" name="hidPnsnBasicGridSize" id="hidPnsnBasicGridSize" value="2">	
			<table align="left" id="BasicPnsnDtl" name="BasicPnsnDtl" border="1" bordercolor="#C9DFFF" width="80%">
				<tr>
					<td align="left" colspan="7">
						<input type="button" value="Add Row" name="AddRowBasic" id="basicPnsnAddrow"  onclick="addRowPnsnBasicDtls()"  class="bigbutton"> 									    
					</td>
				</tr>
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="PPMT.OLDBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="PPMT.NEWBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="PPMT.CVPOLDAMT" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="PPMT.CVPNEWAMT" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="20%"><b><fmt:message key="PPMT.EFFTFROMDATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="20%"><b><fmt:message key="PPMT.EFFTTODATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td width="2%" style="background-color: white;"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td> 
				</tr>
				
				<tr>
					<td align="center"><input type="text" name="txtOldBasic" id="txtOldBasic1" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right" /><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtNewBasic" id="txtNewBasic1" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtOldCvp" id="txtOldCvp1" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right" /><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtNewCvp" id="txtNewCvp1" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtEffectiveFromDt" id="txtEffectiveFromDt1" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtEffectiveFromDt1",375,570);' style="cursor: pointer;"  /></td>
					<td align="center"><input type="text" name="txtEffectiveToDt" id="txtEffectiveToDt1" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtEffectiveToDt1",375,570)'	style="cursor: pointer;"/>
					
					<td></td> 
				</tr>
			</table>
		</fieldset>
		<fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">Re-Employed Detail</legend>
					
			<table align="left" id="ReEmp" name="ReEmp" border="1" bordercolor="#C9DFFF" width="70%">
				<tr>
					<td align="left" colspan="6">
					    <input type="hidden" name="hidReEmpDtlsGridSize" id="hidReEmpDtlsGridSize" value="1">	
						<input type="button" value="Add Row" name="AddRowReEmp" id="ReEmpAddrow" onclick="addRowReEmp()"  class="bigbutton">							    
					</td>
				</tr>
					
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="PPMT.EFFTFROMDATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="PPMT.EFFTTODATE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="2%"><b><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></b></td>
				</tr>
				<tr>
			    	<td align="center"><input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom0"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldReEmpEffFrom0",375,570);' style="cursor: pointer;"  />
					</td>
					<td align="center"><input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo0"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldReEmpEffTo0",375,570);' style="cursor: pointer;"  />
					</td>
					<td align="center" class="HLabel" width="35%">
					<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal0" onfocus="onFocus(this)" onblur="onBlur(this);">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
						<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
						<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</select>
					</td>
					<td class="tds" align="center">
						 <img name="Image" id="Image0" src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'ReEmp');"/> </td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PNSHMNTCUT" bundle="${pensionLabels}"></fmt:message></b> </legend>
	<hdiits:button id="btnAddRowPunishmntCut" type="button" name="btnAddRowPunishmntCut" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="punishmentCutAddRow();" tabindex="40"/>
				
	<input type="hidden" id="hidPnshmntCutSize" name="hidPnshmntCutSize" value="1" />
	<table id="tblPnshmntCut" border="1" bordercolor="#C9DFFF" width="70%">
		<tr style="width: 100%" class="datatableheader">
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="2%" align="center" class="HLabel"><fmt:message
				key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		</tr>
	
		<tr>
			<td class="tds" align="center" class="HLabel">
				<input type="text" name="txtPnshmntAmount" id="txtPnshmntAmount0" size="15"	onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
							
			</td>
			<td class="tds" align="center" class="HLabel">
				<input type="text" name="txtPnshmntFromDate" id="txtPnshmntFromDate0" maxlength="10" size="10" 
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtPnshmntFromDate0",375,570)'style="cursor: pointer;" ${disabled}/>
			</td>
			<td class="tds" align="center" class="HLabel">
				<input type="text" name="txtPnshmntToDate" id="txtPnshmntToDate0" maxlength="10" size="10" 
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtPnshmntToDate0",375,570)'style="cursor: pointer;"/>
			</td>
			<td class="tds" align="center" class="HLabel"><img name="Image" id="Image0"
							src="images/CalendarImages/DeleteIcon.gif"
							onclick="RemoveTableRow(this, 'tblPnshmntCut');"/>
			</td>
		  </tr>
					
			
</table>

</fieldset>
			
</fieldset>
<br/><br/>
<center>
		<hdiits:button name="btnCalculateArrear" type="button" id="btnCalculateArrear" value='Calculate' onclick="generateSixthPayArrearRpt();" />
		
		<hdiits:button name="btnClose" value="Close" type="button" onclick="winCls();"/>
</center>
</hdiits:form>

<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode" source="cmbBankName" target="cmbBankBranch" parameters="bankCode={cmbBankName}" ></ajax:select>

<script>
if(document.getElementById("hidBillFlag").value=='S')
{
	document.getElementById("txtPPONO").value=window.opener.document.getElementById("txtPPONO").value;
	document.getElementById("txtPPONO").disabled=true;
	document.getElementById("trBankDtls").style.display="none";
	getPensionerDetailsFromPPONO();
	
}
</script>