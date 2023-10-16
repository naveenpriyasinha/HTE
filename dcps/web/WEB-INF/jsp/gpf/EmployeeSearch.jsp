<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables"
	scope="request" />

<script language="javascript">
function init(){
	var msg = document.getElementById("txtAlert").value;
	var reqType = document.getElementById("hidReqType").value;
	var requestName;
	if(reqType == "CS"){
		requestName="Change Subscription";
	}else if(reqType == "RA" || reqType == "NRA"){
		requestName="Advance";
	}else if(reqType == "FW"){
		requestName="Final Withdrawal";
	}
	if(msg != "" && msg!=null){
		if(msg == "HierarchyUser")
			alert("Work flow approvers not configured for this location");
		else if(msg == "InvalidEmp")
			alert("Invalid Employee Code Or Employee Name");
		else if(msg == "WithdrawalExists")
			alert("Employee has already taken a non-refundable advance for this financial year");
		else if(msg == "OneReqExists")
			alert("One Request For " +requestName+ " Is Already In Progress For the Employee");
//		else if(msg == "NotEligibleFW")
//			alert("Employee is not eligible for Final Withdrawal before 1 year of superannuation.");
		else if(msg == "NotEligibleRA")
			alert("Employee is not eligible to take refundable advance before completion of 1 year of service and after 15 months of superannuation.");
		else if(msg == "NotEligibleCS")
			alert("Employee is not eligible to change subscription before completion of 1 year of service and after 3 months of superannuation.");
	}
	document.getElementById("txtSevaarthId").value = document.getElementById("hidSevaarthId").value
	document.getElementById("txtEmployeeName").value = document.getElementById("hidEmpName").value
}
function validateCriteria()
{
	var sevaarthId = document.getElementById("txtSevaarthId").value;
	var empName=document.getElementById("txtEmployeeName").value;

	if(sevaarthId.trim() == "" && empName.trim() == "")
	{
		alert('Please enter any one value to search');
		return false ;

	}

	return true ;
}

function checkForDataEntry()
{
	var EmpCode = document.getElementById("txtSevaarthId").value;
	var uri = 'ifms.htm?actionFlag=checkDataEntryForEmployee';
	var url = '&EmpCode='+EmpCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
					getResponseDataEntry(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getResponseDataEntry(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;	
	if(lBlFlag == "true"){
		submitSearchDetails();
	}else{
		alert("Your Request can not proceed as Data Entry for this Employee Code is pending");
	}
}

function submitSearchDetails()
{
	if(!validateCriteria())
	{
			return false ;
	}
	var criteria;
	var txtSevaarthId=document.getElementById("txtSevaarthId").value;
	var txtEmployeeName=document.getElementById("txtEmployeeName").value;
	var requestArr = document.EmployeeSearchForm.RadioButtonRequest ;
	var requestType;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestType = requestArr[i].value ;
		  }
	}
	if(txtEmployeeName == ""){
		criteria=1;
	}
	else if(txtSevaarthId == ""){
		criteria=2;
	}
	else{
		criteria=3;
	}
	var url = "ifms.htm?actionFlag=loadGPFRequestProcess&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="
	+txtEmployeeName+"&criteria="+criteria+"&requestType="+requestType+"&userType=DEO";
	document.EmployeeSearchForm.action = url ;
	document.EmployeeSearchForm.submit();
	
	return true;
}
function clearAllfields()
{
	document.getElementById("txtSevaarthId").value= "";
	document.getElementById("txtEmployeeName").value= "";
}
</script>
<script type="text/javascript" src="script/common/common.js"></script>
<hdiits:form name="EmployeeSearchForm" id="EmployeeSearchForm"
	encType="multipart/form-data" validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<input type="hidden"  name='txtAlert' id="txtAlert" value="${resValue.alertMessage}" />
<input type="hidden"  name='hidSevaarthId' id="hidSevaarthId" value="${resValue.SevaarthId}" />
<input type="hidden"  name='hidEmpName' id="hidEmpName" value="${resValue.EmpName}" />
<input type="hidden"  name='hidReqType' id="hidReqType" value="${resValue.requestType}" />

	<fieldset class="tabstyle"><legend><fmt:message key="CMN.SEARCHEMP" bundle="${gpfLables}"></fmt:message></legend>

		<table border="0" width="70%" align="center" cellpadding="4"
			cellspacing="4">
			<tr align="center">
				<td width="25%" align="left" ><fmt:message
					key="CMN.EMPCODE" bundle="${gpfLables}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtSevaarthId" style="text-transform: uppercase" size="30"
					name="txtSevaarthId" /></td>
			</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
				bundle="${gpfLables}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmployeeName" size="30" style="text-transform: uppercase"
				name="txtEmployeeName" />
				<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
		</tr>
		<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message
					key="MSG.SEARCH" bundle="${gpfLables}" /></label></td>
		</tr>
		<tr>
		<td colspan="2">

		<table >
			<tr>
				<td width="20%">
					<input type="radio" id="RadioButtonRequest"
						name="RadioButtonRequest" value="CS" checked/>
					<fmt:message key="CMN.CHANGESUBSCRIPTION" bundle="${gpfLables}"></fmt:message>
				</td>
				<td width="20%">
					<input type="radio" id="RadioButtonRequest"
						name="RadioButtonRequest" value="RA" />
					<fmt:message key="CMN.REFADVANCE" bundle="${gpfLables}"></fmt:message>
				</td>
				<td width="20%">
					<input type="radio" id="RadioButtonRequest"
						name="RadioButtonRequest" value="NRA" />
					<fmt:message key="CMN.NONREFADVANCE" bundle="${gpfLables}"></fmt:message>
				</td>
				<td width="20%">
					<input type="radio" id="RadioButtonRequest"
						name="RadioButtonRequest" value="FW" />
					<fmt:message key="CMN.FINALWITHDRAW" bundle="${gpfLables}"></fmt:message>
				</td>
			</tr>
		</table>
		</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
			<table border="0" width="50%" align="center">
				<tr>
					<td align="left"><hdiits:button name="btnSubmitData"
						id="btnSubmitData" type="button" captionid="BTN.SUBMIT"
						bundle="${gpfLables}" onclick="checkForDataEntry();" /></td>
					<td align="left"><hdiits:button name="btnClearAllFields"
						id="btnSubmitData" type="button" captionid="BTN.CLEAR"
						bundle="${gpfLables}" onclick="clearAllfields();" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	</fieldset>
<script>
    init();
</script>
</hdiits:form>
<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoComplete"
	parameters="searchKey={txtEmployeeName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />