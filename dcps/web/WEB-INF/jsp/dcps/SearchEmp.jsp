<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script>
function validateCriteria()
{
	var sevarthId = document.getElementById("txtSevarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	
	if(sevarthId == "" && txtEmployeeName == "")
	{
		alert('Please Enter Search Criteria');
		return false;
	}
	
	return true ;
}
function submitSearchDetails()
{
	if(!validateCriteria())
	{
			return false ;
	}
	var sevarthId = document.getElementById("txtSevarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	var url = "ifms.htm?actionFlag=srchEmp&elementId=700162&sevarthId="+sevarthId+"&employeeName="+txtEmployeeName;
	document.EmpSearchForm.action = url ;
	document.EmpSearchForm.submit();
}
function clearAllfields()
{
	document.getElementById("txtSevarthId").value= "";
}
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />
<hdiits:form name="EmpSearchForm" id="EmpSearchForm"
	encType="multipart/form-data" validate="true" method="post">

	<fieldset class="tabstyle"><legend>Search Employees</legend>

		<table border="0" width="40%" align="left" cellpadding="4"
			cellspacing="4">
		
			
		<tr>
			<td width="21%" align="left" ><fmt:message
				key="CMN.SEVARTHID" bundle="${dcpsLables}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtSevarthId" style="text-transform: uppercase" size="30"
				name="txtSevarthId" /></td>
		</tr>
		
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
										bundle="${dcpsLables}" /></td>
			<td width="50%" align="left"><input type="text"
										id="txtEmployeeName" size="30" style="text-transform: uppercase"
										name="txtEmployeeName" />
			<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
		</tr>
	
		<tr>
			<td style="width: 50%" align="center" colspan="2">
			<table border="0" width="70%" align="center">
				<tr>
					<td ><hdiits:button name="btnSubmitData"
						id="btnSubmitData" type="button" captionid="BTN.SEARCH"
						bundle="${dcpsLables}" onclick="submitSearchDetails();" /></td>
					<td ><hdiits:button name="btnClearAllFields"
						id="btnClearData" type="button" captionid="BTN.CLEAR"
						bundle="${dcpsLables}" onclick="clearAllfields();" /></td>
					<td ><hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" 
						bundle="${dcpsLables}" onclick="ReturnLoginPage();"/></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	</fieldset>
</hdiits:form>

<input type="hidden" name="hidSearchFromDDOAsst" id="hidSearchFromDDOAsst" value="searchByDDOAsst" />

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDOAsst}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />