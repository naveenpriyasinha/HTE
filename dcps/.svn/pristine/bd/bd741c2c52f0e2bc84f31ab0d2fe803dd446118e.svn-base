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
	var dcpsId = document.getElementById("txtEmployeeId").value;
	var empName=document.getElementById("txtEmployeeName").value;
	var birthDate=document.getElementById("txtBirthDate").value;
	
	if(dcpsId=="" && empName=="" && birthDate=="")
	{
		alert('Please Enter the Search Criteria');
		return false;
	}
	
	if(dcpsId != "")
	{
		if(empName != "" || birthDate != "" )
		{
			alert('Either Enter DCPS ID only or Employee Name and Date Of Birth only.');
			return false ;
		}
	}

	if(empName != "" && birthDate != "")
	{
		if(dcpsId != "")
		{
			alert('Either Enter DCPS ID only or Employee Name and Date Of Birth only.');
			return false ;
		}
	}

	if(empName != "" &&  birthDate=="")
	{
			alert('Please Enter Date of birth also.');
			return false ;
	}

	if(empName == "" &&  birthDate!="")
	{
			alert('Please Enter Employee name also.');
			return false ;
	}
	
	return true ;
}
function submitSearchDetails()
{
	if(!validateCriteria())
	{
			return false ;
	}
	var txtEmployeeId=document.getElementById("txtEmployeeId").value;
	var txtEmployeeName=document.getElementById("txtEmployeeName").value;
	var txtBirthDate=document.getElementById("txtBirthDate").value;
	var url = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="+txtEmployeeId+"&txtEmployeeName="+txtEmployeeName+"&txtBirthDate="+txtBirthDate;
	document.EmployeeSearchForm.action = url ;
	document.EmployeeSearchForm.submit();
}
function clearAllfields()
{
	document.getElementById("txtEmployeeId").value= "";
	document.getElementById("txtEmployeeName").value= "";
	document.getElementById("txtBirthDate").value= "";
}
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />
<hdiits:form name="EmployeeSearchForm" id="EmployeeSearchForm"
	encType="multipart/form-data" validate="true" method="post">

	<fieldset class="tabstyle"><legend>Search Employees</legend>

		<table border="0" width="40%" align="left" cellpadding="4"
			cellspacing="4">
			<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message
					key="MSG.SEARCH" bundle="${DCPSLables}" /></label></td>
			</tr>
			
			<tr>
				<td width="21%" align="left" ><fmt:message
					key="CMN.DCPSID" bundle="${dcpsLables}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtEmployeeId" style="text-transform: uppercase" size="30"
					name="txtEmployeeId" /></td>
			</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.EmployeeName"
				bundle="${dcpsLables}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmployeeName" style="text-transform: uppercase" size="30"
				name="txtEmployeeName" /></td>
		</tr>

		<tr>
			<td width="10%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}" /></td>
				
			<td width="50%" align="left"><input type="hidden"
				name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> <input
				type="text" name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(this);compareDates(this,document.getElementById('currDate1'),'Date of Birth should be less than current date.','<');" />
			<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick="window_open('txtBirthDate', 375, 570);" style="cursor: pointer;"/></td>
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