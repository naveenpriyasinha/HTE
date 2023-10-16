<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.pdpla.PdPlaLabels" var="paybillLables" scope="request"/>

<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.INVESTMENTDETIALS" bundle="${paybillLables}"></fmt:message></b>
</legend>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFPENSIONFUNDMNGR" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfPensionFundMngr" id="cmbChoiceOfPensionFundMngr" style="width:180px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFASSETALLOCATION" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfAssetAllocation" id="cmbChoiceOfAssetAllocation" style="width:180px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"></td>
		<td width="30%" align="left"><fmt:message key="MSG.EQUITYLIMIT" bundle="${paybillLables}"></fmt:message></td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.EQUITY" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtEquity"  size="20"  name="txtEquity" value="" onkeypress="digitFormat(this);"/>
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.CORPORATEDEBT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtCorporateDebt"  size="20"  name="txtCorporateDebt" value="" onkeypress="digitFormat(this);"/>
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.GOVTDEBT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtGovtDebt"  size="20"  name="txtGovtDebt" value="" onkeypress="digitFormat(this);"/>
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>	
</fieldset>
