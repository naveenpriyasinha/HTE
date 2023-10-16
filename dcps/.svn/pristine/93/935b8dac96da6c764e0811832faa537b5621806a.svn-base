<!-- Displays parameter page for arrear calculation after revision [author:Rupsa Mukherjee] [ver:1.0] -->

<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionAlerts" var="PensionAlerts" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionToolTip" var="pensionToolTip" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"	var="pensionLabels1" scope="request" />



<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/pensionpay/RevisedArrear.js"></script>

<script type="text/javascript">
LISTDAIN='<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>';
LISTALLOWANCE='<option value="<fmt:message key="ALLOWANCE.PEON" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.PEON" bundle="${pensionLabels1}"/></option>'
	+'<option value="<fmt:message key="ALLOWANCE.GALLANTRY" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.GALLANTRY" bundle="${pensionLabels1}"/></option>'
	+'<option value="<fmt:message key="ALLOWANCE.MEDICAL" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.MEDICAL" bundle="${pensionLabels1}"/></option>'
	+'<option value="<fmt:message key="ALLOWANCE.OTHER" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.OTHER" bundle="${pensionLabels1}"/></option>';
</script>
<head>


</head>	
<script type="text/javascript">
cmbListYesNo='';
cmbListRevisedType='';
</script>

<hdiits:form name="RevisedArrrearCalculation" validate="true"  method="post" >
	
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
<input type="hidden" name="hidBillFlag" id="hidBillFlag" value="${resValue.BillFlag}"/>
<input type="hidden" name="hidPpoNumberLC" id="hidPpoNumberLC" value="${resValue.PpoNo}"/>
<input type="hidden" name="hidRowCnt" id="hidRowCnt" value="${resValue.RowCnt}"/>



	<fieldset style="width:100%" class="tabstyle" >
		<legend ><fmt:message key="REVARREAR.HDG" bundle="${pensionLabels}"></fmt:message></legend>
		
		<table width="100%">
			<tr id="trBankDtls">
				<td align="left" ><fmt:message key="REVARREAR.BANKNAME" bundle="${pensionLabels}"></fmt:message></td>
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
				<td align="left" ><fmt:message key="REVARREAR.BRANCHNAME" bundle="${pensionLabels}"></fmt:message></td>
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
				<td align="left" ><fmt:message key="REVARREAR.ACCOUNTNO" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtAccountNo"  size="20"  name="txtAccountNo" onblur="getPnsnrDtlsFromBankBranch();" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="REVARREAR.PPONO" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPPONO"  size="20"  name="txtPPONO"  style="display: ;text-transform: uppercase;"  onblur="validatePPONumber();" /><label class="mandatoryindicator">*</label>
				</td>
				<td align="left" ><fmt:message key="REVARREAR.DOB" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOB"  size="20"  name="txtDOB" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="left" ><fmt:message key="REVARREAR.PENNAME" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtPensionerName"  size="30"  name="txtPensionerName" disabled="disabled"/>
					<input type="hidden" id="hdnPensionerName" name="hdnPensionerName"/>
				</td>
				<td align="left" ><fmt:message key="REVARREAR.DOR" bundle="${pensionLabels}"></fmt:message></td>
				<td>
					<input type="text" id="txtDOR"  size="20"  name="txtDOR" disabled="disabled"/>
					
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
			<table align="left" id="BasicPnsnDtl" name="BasicPnsnDtl" border="1" bordercolor="#C9DFFF" width="65%">
				<tr>
					<td align="left" colspan="7">
						<input type="button" value="Add Row" name="AddRowBasic" id="basicPnsnAddrow"  onclick="addRowPnsnBasicDtls()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">									    
					</td>
				</tr>
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="REVARREAR.REVTYPE" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="REVARREAR.OLDBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="REVARREAR.NEWBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="15%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="14%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td width="2%" style="background-color: white;"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
					

				</tr>
				<c:forEach var="listRevisedType" items="${resValue.RevisedType}">
					<script> 
						cmbListRevisedType += '<option value="${listRevisedType.lookupName}"> ${listRevisedType.lookupDesc} </option>'; 
					</script>
				</c:forEach>
				<tr>
					<td>
						<select name="cmbRevisionType"  id="cmbRevisionType" style="width:100%" >
							<option value="-1">--Select--</option>
							<c:forEach var="listRevisionType" items="${resValue.RevisedType}">
								<option value='${listRevisionType.lookupName}'>
									<c:out value="${listRevisionType.lookupDesc}"></c:out>									
								</option>
							</c:forEach>						
						</select>
						<label style="display: none;" class="mandatoryindicator">*</label>
					</td>
					<td align="center"><input type="text" name="txtOldBasic" id="txtOldBasic1" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right" /><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtNewBasic" id="txtNewBasic1" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtOldBasicEffFrom" id="txtOldBasicEffFrom1" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldBasicEffFrom1",375,570);' style="cursor: pointer;"  /></td>
					<td align="center"><input type="text" name="txtOldBasicEffTo" id="txtOldBasicEffTo1" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldBasicEffTo1",375,570)'	style="cursor: pointer;"/>
					<!-- <input type="text" name="txtNewBasicEffFrom" id="txtNewBasicEffFrom1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"      />
					<input type="text" name="txtNewBasicEffTo" id="txtNewBasicEffTo1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   /> --></td>
					<td></td>
				</tr>
			</table>
		</fieldset>
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
	<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PNSHMNTCUT" bundle="${pensionLabels1}"></fmt:message></b> </legend>
	<hdiits:button id="btnAddRowPunishmntCut" type="button" name="btnAddRowPunishmntCut" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="punishmentCutAddRow();" tabindex="40"/>
				
	<input type="hidden" id="hidPnshmntCutSize" name="hidPnshmntCutSize" value="1" />
	<table id="tblPnshmntCut" border="1" bordercolor="#C9DFFF" width="70%">
		<tr style="width: 100%" class="datatableheader">
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="PPMT.AMOUNT" bundle="${pensionLabels1}"></fmt:message></td>
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" align="center" class="HLabel"><fmt:message
				key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></td>
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
	
	<!-- 
	<c:forEach var="listDP" items="${resValue.listYesNo}">
		<script> cmbListYesNo += '<option value="${listDP.lookupDesc}"> ${listDP.lookupDesc}</option>';</script>
	</c:forEach>
			
	
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		
		
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		
		<fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">DP Detail</legend>
			<table align="left" id="tblDPDtls" name="tblDPDtls" border="1" bordercolor="#C9DFFF" width="65%">
				<tr>
					<td align="left" colspan="6">
					    <input type="hidden" name="hidDPGridSize" id="hidDPGridSize" value="2">
						<input type="button" value="Add Row" name="AddRowDP" id="DPAddrow" onclick="addRowDPDtls()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">									    
					</td>
				</tr>
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="16%" ><b>DP Old</b></td>
					<td align="center" class="HLabel" width="16%"><b>DP New</b></td>
					<td align="center" class="HLabel" width="17%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="17%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td width="2%" style="background-color: white;"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
				</tr>
				<tr>
					<td align="center">
						<select name="cmbOldDP" id="cmbOldDP1" style="width:90%" >
							<option value="-1">--Select--</option>
							<c:forEach var="listDP" items="${resValue.listYesNo}">
						    	<c:choose>
								<c:when test="${listDP.lookupDesc == 'No'}">
									<option value="${listDP.lookupDesc}" selected="selected"> <c:out value="${listDP.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${listDP.lookupDesc}"> <c:out value="${listDP.lookupDesc}"></c:out></option>
								</c:otherwise>
								</c:choose>
					        </c:forEach>					
						</select>
					</td>
					<td align="center">
						<select name="cmbNewDP"  id="cmbNewDP1" style="width:90%" >
							<option value="-1">--Select--</option>
							<c:forEach var="listDP" items="${resValue.listYesNo}">
							<c:choose>
								<c:when test="${listDP.lookupDesc == 'No'}">
									<option value="${listDP.lookupDesc}" selected="selected"> <c:out value="${listDP.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${listDP.lookupDesc}"> <c:out value="${listDP.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
								
					        </c:forEach>					
						</select>
						<label style="display: none;" class="mandatoryindicator">*</label>
					</td>
					<td align="center"><input type="text" name="txtOldDPEffFrom" id="txtOldDPEffFrom1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"     />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldDPEffFrom1",375,570)'	style="cursor: pointer;"/></td>
					<td align="center"><input type="text" name="txtOldDPEffTo" id="txtOldDPEffTo1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"    />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldDPEffTo1",375,570)'	style="cursor: pointer;"/>
					<!-- <input type="text" name="txtNewDPEffFrom" id="txtNewDPEffFrom1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
									onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"      />
					<input type="text" name="txtNewDPEffTo" id="txtNewDPEffTo1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
									onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"    /></td>
					<td width="2%" style="background-color: white;"></td>
					
				</tr>
			</table>
		</fieldset>
		 -->
			
		<table>
			<tr>
				<td height="15px"></td>
			</tr>
		</table>
		
			
		<fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">CVP Details</legend>
			<table align="left" id="tblCVPDtls" name="tblCVPDtls" border="1" bordercolor="#C9DFFF" width="65%">
				<tr>
					<td align="left" colspan="6">
					    <input type="hidden" name="hidCVPGridSize" id="hidCVPGridSize" value="2">
						<input type="button" value="Add Row" name="AddRowCVP" id="CVPAddrow"  onclick="addRowCVPDtls()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">									    
					</td>
				</tr>
				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="16%" ><b>CVP Monthly Old</b></td>
					<td align="center" class="HLabel" width="16%"><b>CVP Monthly New</b></td>
					<td align="center" class="HLabel" width="17%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="17%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					
					<td width="2%" style="background-color: white;"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
				</tr>
				<tr>
					<td align="center"><input type="text" name="txtOldCVP" id="txtOldCVP1" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/></td>
					<td align="center"><input type="text" name="txtNewCVP" id="txtNewCVP1" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/></td>
					<td align="center"><input type="text" name="txtOldCVPEffFrom" id="txtOldCVPEffFrom1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldCVPEffFrom1",375,570)'	style="cursor: pointer;"/></td>
					<td align="center"><input type="text" name="txtOldCVPEffTo" id="txtOldCVPEffTo1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldCVPEffTo1",375,570)'	style="cursor: pointer;"/>
					<!-- <input type="text" name="txtNewCVPEffFrom" id="txtNewCVPEffFrom1"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"    />
					<input type="text" name="txtNewCVPEffTo" id="txtNewCVPEffTo1" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   /> --></td>
				</tr>
			</table>
		</fieldset>
		
			
		<table>
			<tr>
				<td height="10px"></td>
			</tr>
		</table>
		
		
	   <table>
			<tr>
				<td height="10px"></td>
			</tr>
		</table>
		
			
		<fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">Re-Employed Detail</legend>
			<!-- Include DA in :
				    <input type="radio"id="radioPnsnSalary" name="radioPnsnSalary" value="P" />
				    Pension
			        <input type="radio"id="radioPnsnSalary" name="radioPnsnSalary" value="S" />
					Salary -->
			
			<table align="left" id="ReEmp" name="ReEmp" border="1" bordercolor="#C9DFFF" width="70%">
				<tr>
					<td align="left" colspan="6">
					    <input type="hidden" name="hidReEmpDtlsGridSize" id="hidReEmpDtlsGridSize" value="1">	
						<input type="button" value="Add Row" name="AddRowReEmp" id="ReEmpAddrow" onclick="addRowReEmp()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">
												    
					</td>
				</tr>
					
<!--				</tr>-->
<!--				<tr class="datatableheader">-->
<!--					<td align="center" class="HLabel" width="50%" colspan="2"><b>Re-Employment Old</b></td>-->
<!--					<td align="center" class="HLabel" width="50%" colspan="2"><b>Re-Employment New</b></td>-->
<!--				</tr>-->

				<tr class="datatableheader" >
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionLabels1}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="2%"><b><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></b></td>
<!--					<td align="center" class="HLabel" width="25%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>-->
<!--					<td align="center" class="HLabel" width="25%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>-->
				</tr>
				<tr>
			    	<td align="center"><input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom0"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldReEmpEffFrom0",375,570);' style="cursor: pointer;"  />
					</td>
					<td align="center"><input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo0"  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtOldReEmpEffTo0",375,570);' style="cursor: pointer;"  />
					</td>
					<td align="center" class="HLabel" width="35%"><select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal0" onfocus="onFocus(this)" onblur="onBlur(this);">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
						<option value="<fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINPNSN" bundle="${pensionConstants}"/></option>
						<option value="<fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/>"><fmt:message key="REEMP.DAINSALARY" bundle="${pensionConstants}"/></option>
					</select>
					</td>
					<td class="tds" align="center">
						 <img name="Image" id="Image0" src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'ReEmp');"/> </td>
				<!-- 	<input type="text" name="txtNewReEmpEffFrom" id="txtNewReEmpEffFrom1"  size="13" onKeyup="dateFormat(this);" />
					<input type="text" name="txtNewReEmpEffTo" id="txtNewReEmpEffTo1"  size="13" onKeyup="dateFormat(this);" /> --></td>
				</tr>
			</table>
		</fieldset>
		
		 
		</fieldset>
		<!-- Other Allowance  -->
	    <fieldset style="width:100%" class="tabstyle" >
			<legend  id="headingMsg">Other Allowance</legend>
			<!-- Include DA in :
				    <input type="radio"id="radioPnsnSalary" name="radioPnsnSalary" value="P" />
				    Pension
			        <input type="radio"id="radioPnsnSalary" name="radioPnsnSalary" value="S" />
					Salary -->
			
			<table align="left" id="tblAllowanceDtls" name="tblAllowanceDtls" border="1" bordercolor="#C9DFFF" width="70%">
				<tr>
					<td align="left" colspan="6">
					    <input type="hidden" name="hidOtherAllwGridSize" id="hidOtherAllwGridSize" value="1">	
						<input type="button" value="Add Row" name="AddRowOtherAllow" id="AddRowOtherAllow" onclick="addRowAllowanceDtls()" title='<fmt:message key="RECO.ADDROW" bundle="${pensionToolTip}"></fmt:message>' class="bigbutton">
												    
					</td>
				</tr>
					
    			<tr class="datatableheader" >
    				<td align="center" class="HLabel" width="25%"><b><fmt:message key="REVARREAR.ALLOWANCETYPE" bundle="${pensionLabels}"></fmt:message></b></td>
    				<td align="center" class="HLabel" width="13%"><b><fmt:message key="REVARREAR.OLDBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="13%"><b><fmt:message key="REVARREAR.NEWBASIC" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="25%"><b><fmt:message key="REVARREAR.EFFFROM" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="35%"><b><fmt:message key="REVARREAR.EFFTO" bundle="${pensionLabels}"></fmt:message></b></td>
					<td align="center" class="HLabel" width="2%"><b><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></b></td>
				</tr>
				<tr>
			    	<td align="center">
						<select name="cmbAllowanceType"  id="cmbAllowanceType0" style="width:90%" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
							<option value="<fmt:message key="ALLOWANCE.PEON" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.PEON" bundle="${pensionLabels1}"/></option>
							<option value="<fmt:message key="ALLOWANCE.GALLANTRY" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.GALLANTRY" bundle="${pensionLabels1}"/></option>
							<option value="<fmt:message key="ALLOWANCE.MEDICAL" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.MEDICAL" bundle="${pensionLabels1}"/></option>
							<option value="<fmt:message key="ALLOWANCE.OTHER" bundle="${pensionConstants}"/>"><fmt:message key="ALLOWANCE.OTHER" bundle="${pensionLabels1}"/></option>					
						</select>
						<label class="mandatoryindicator">*</label>
					</td>
					<td align="center"><input type="text" name="txtOldAllowanceAmt" id="txtOldAllowanceAmt0" size="16"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right" /><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtNewAllowanceAmt" id="txtNewAllowanceAmt0" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label></td>
					<td align="center"><input type="text" name="txtAllowanceEffFrom" id="txtAllowanceEffFrom0" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtAllowanceEffFrom0",375,570);' style="cursor: pointer;"  /></td>
					<td align="center"><input type="text" name="txtAllowanceEffTo" id="txtAllowanceEffTo0" size="13" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
										onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"   />
										<img src='images/CalendarImages/ico-calendar.gif' style="width: 16px" onClick='window_open("txtAllowanceEffTo0",375,570)'	style="cursor: pointer;"/>
					
					<td class="tds" align="center">
						 <img name="Image" id="Image0" src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, 'tblAllowanceDtls');"/> </td>

					
				</tr>
			</table>
		</fieldset>
		
		 
		</fieldset>
	<br>
	<center>
		<hdiits:button name="btnCalculateArrear" type="button" id="btnCalculateArrear" value='Calculate' onclick="calculateArrear()" />
		
		<hdiits:button name="btnClose" value="Close" type="button" onclick="winCls();"/>
	</center>	


</hdiits:form>
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode" source="cmbBankName" target="cmbBankBranch" parameters="bankCode={cmbBankName}" ></ajax:select>

<script>
if(document.getElementById("hidBillFlag").value=='P')
{
	document.getElementById("txtPPONO").value=window.opener.document.getElementById("txtPPONO").value;
	document.getElementById("txtPPONO").disabled=true;
	document.getElementById("trBankDtls").style.display="none";
	getPensionerDetailsFromPPONO();
	
}
if(document.getElementById("hidBillFlag").value=='S')
{
	document.getElementById("txtPPONO").value=window.opener.document.getElementById("txtPPONo").value;
	document.getElementById("txtPPONO").disabled=true;
	document.getElementById("trBankDtls").style.display="none";
	getPensionerDetailsFromPPONO();
	
}
if(document.getElementById("hidBillFlag").value=='L')
{
	document.getElementById("txtPPONO").value=document.getElementById("hidPpoNumberLC").value;
	document.getElementById("txtPPONO").disabled=true;
	document.getElementById("trBankDtls").style.display="none";
	getPensionerDetailsFromPPONO();
	
}
</script>

