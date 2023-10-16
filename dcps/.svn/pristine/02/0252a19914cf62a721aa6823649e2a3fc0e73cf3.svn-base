<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script language="JavaScript" src="script/paybill/paybillvalidation.js"></script>

<fmt:setBundle basename="resources.paybill.PayBillLabels" var="paybillLables" scope="request"/>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data" validate="true" method="post"  >
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="10%" align="left"><fmt:message key="CMN.DCPSID" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtDCPSid" style="text-transform: uppercase" size="30"  name="txtDCPSid" value="" />
		</td>
	</tr>
</table>	
<fieldset class="tabstyle">

<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.NAME" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtName1"  size=""  name="txtName1" value="" />
			<input type="text" id="txtName2"  size=""  name="txtName2" value="" />
			<input type="text" id="txtName3"  size=""  name="txtName3" value="" />
		</td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CURRENTOFFICE" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtCurrentOffice"  size="30"  name="txtCurrentOffice" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtDesignation"  size="30"  name="txtDesignation" value="" />
		</td>
	</tr>		
	
</table>
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.INVESTMENTDETIALS" bundle="${dcpsLables}"></fmt:message></b>
</legend>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
		<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFPENSIONFUNDMNGR" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfPensionFundMngr" id="cmbChoiceOfPensionFundMngr" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFASSETALLOCATION" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfAssetAllocation" id="cmbChoiceOfAssetAllocation" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"></td>
		<td width="30%" align="left"><fmt:message key="MSG.EQUITYLIMIT" bundle="${dcpsLables}"></fmt:message></td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.EQUITY" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtEquity"  size="30"  name="txtEquity" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.CORPORATEDEBT" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtCorporateDebt"  size="30"  name="txtCorporateDebt" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.GOVTDEBT" bundle="${dcpsLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtGovtDebt"  size="30"  name="txtGovtDebt" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>
</fieldset>
</fieldset>
<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
			<hdiits:button name="btnSaveData" id="btnSaveData" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" onclick=""/>
			<hdiits:button name="btnSubmit" id="btnSubmit" type="button" captionid="BTN.SUBMIT" bundle="${dcpsLables}"  onclick="" />
			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick=""/>
		</td>
	</tr>	
</table>
</hdiits:form>