<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>

<script type="text/javascript" src="script/gpf/dataEntryForm.js"></script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<c:if test="${resValue.userType == 'DDO'}">
	<c:set var="varDisabled" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" value="style='display:none'"></c:set>
</c:if>

<table width="100%" id="tblRefundableHis">
	<tr>
		<td width="15%">
			<fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}"></fmt:message>
		</td>
		<td width="20%">
			<select name="cmbPurposeCategoryHistory" id="cmbPurposeCategoryHistory" style="width:230px">
				<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT"/></option>
				<c:forEach var="Purpose" items="${resValue.lstPurposeCatRA}">					
						<option value="${Purpose.lookupId}">
						<c:out value="${Purpose.lookupDesc}"></c:out></option>
				</c:forEach>
			</select><label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.SANCTIONAMONUT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtSancAmountHistory" id="txtSancAmountHistory" maxlength="10" onkeypress="amountFormat(this);" style="text-align: right" />
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.SANCDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" name="txtSancDateHistory" id="txtSancDateHistory" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);chkSanctionDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtSancDateHistory",375,570)' style="cursor: pointer;"/>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERNO" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text"  size="20%" name="txtVchrNoHisRA" id="txtVchrNoHisRA"  />
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.VOUCHERDATE" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" name="txtVchrDateHisRA" id="txtVchrDateHisRA" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVchrDateHisRA",375,570)' style="cursor: pointer;"/>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.TOTALNOINSTALL" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtNoOfInstlmntHis" name="txtNoOfInstlmntHis" onblur="validateTotalNoOfInstallmentHis();">
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.INSTALLAMTPN" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtInstlmntAmtPerMonHis" name="txtInstlmntAmtPerMonHis" readonly>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="CMN.ODDINSTALLMENT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtFirstOddInstlmntHis" name="txtFirstOddInstlmntHis" readonly/>
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%">
			<fmt:message key="CMN.NOOFRECINSTLMNT" bundle="${gpfLabels}" />
		</td>
		<td width="20%">
			<input type="text" id="txtRecoveredInstlmntHis" name="txtRecoveredInstlmntHis">
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	
</table>
<br><br>
<center>
<c:if test="${resValue.userType == 'DEO'}">
<hdiits:button name="btnAddRowRAHis" id="btnAddRowRAHis" type="button" captionid="BTN.ADDROW" bundle="${gpfLabels}" onclick="addRowRAHistory();" />
</c:if>
</center>
<br><br>
<div align="center">
	<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:95%; height:120px; overflow: scroll; overflow-x:scroll; overflow-y:scroll; ">
	<table id="tblRAHistoryDtls" align="center" width="98%" border="1">
	
	<tr>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SRNO" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.PURPOSECATEGORY" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SANCAMT" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.SANCDATE" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.VOUCHERNO" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.VOUCHERDATE" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.TOTALSANCINSTALL" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.INSTALLAMTPN" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.ODDINSTALLMENT" bundle="${gpfLabels}" /></b></td>
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.RECEIVEDINSTALLMENTS" bundle="${gpfLabels}" /></b></td>		
		<td  align="center" class="datatableheader"><b><fmt:message key="CMN.DELETE" bundle="${gpfLabels}" /></b></td>
	</tr>
	
	<c:choose>

			<c:when test="${resValue.RAHistory != null}">
	
				<c:forEach var="RAHistory" items="${resValue.RAHistory}" varStatus="Counter">					
					<tr>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="5" style="text-align: center" name="txtRAHistorySrnoRA" value="${Counter.index + 1}" readonly="readonly" />
							<input type="hidden" id="hidRADtlsPk${Counter.index}" name="hidRADtlsPk" value="${RAHistory[9]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtPurposeOfAdvanceRA" value="${RAHistory[0]}" readonly="readonly" />
							<input type="hidden" name="hidPurposeOfAdvanceRA" value="${RAHistory[10]}" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="20" style="text-align: center" name="txtSancAmountRA" value="${RAHistory[1]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<fmt:formatDate value="${RAHistory[2]}" pattern="dd/MM/yyyy" var="RAHisSancDate"/>
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtSancDateRA" value="${RAHisSancDate}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtVoucherNoRA" value="${RAHistory[3]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<fmt:formatDate value="${RAHistory[4]}" pattern="dd/MM/yyyy" var="RAHisVchrDate"/>
							<input type="text" style="border: none;" size="10" style="text-align: center" name="txtVoucherDateRA" value="${RAHisVchrDate}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="7" style="text-align: center" name="txtNoOfInstlmntRA" value="${RAHistory[5]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="7" style="text-align: center" name="txtInstlmntAmtPmRA" value="${RAHistory[6]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="7" style="text-align: center" name="txtFirstOddInstlmntRA" value="${RAHistory[7]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<input type="text" style="border: none;" size="7" style="text-align: center" name="txtRecoveredInstlmntRA" value="${RAHistory[5] - RAHistory[8]}" readonly="readonly" />
						</td>
						<td class="tds" align="center">
							<img src="images/CalendarImages/DeleteIcon.gif"  onclick="deleteRow_RA_His();" ${varImageDisabled}/>
						</td>
						
					</tr>
				</c:forEach>
			</c:when>
	</c:choose>
						
	</table>
	</div>
</div>