<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="caseList" value="${resValue.CaseList}" />

<script>
function ReturnToSearchForSRKA()
{
	var url = "ifms.htm?actionFlag=loadSearchEmpForSRKA&elementId=700174";
	self.location.href = url;	
}

function printForm1()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
						window_new_update(url);
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

function printForm1Acmnt()
{
	var noOfEmployeesSelected = 0;
	var flag = 0;
	var totalSelectedEmployees=document.getElementById("totalCount").value;

	for(var i=0;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("chkbxFormVeri"+i).checked == true)
			{
				flag = 1;	
				noOfEmployeesSelected++ ; 
			}
	}
	
	if(flag == 1)
	{
		if(noOfEmployeesSelected == 1)
		{
			for(i=0;i<totalSelectedEmployees;i++)
			{
				if(document.getElementById("chkbxFormVeri"+i).checked == true)
					{
						var EmpId = document.getElementById("chkbxFormVeri"+i).value;
						url = "ifms.htm?actionFlag=reportService&reportCode=700007&action=generateReport&empid="+EmpId+"&asPopup=TRUE";
						window_new_update(url);
					}
			}
		}
		else
		{
			alert('Please select only one form');
		}
	}
	else
	{
		alert("Please select a form");
	}
}

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
	var url = "ifms.htm?actionFlag=searchEmpForSRKA&txtEmployeeId="+txtEmployeeId+"&txtEmployeeName="+txtEmployeeName+"&txtBirthDate="+txtBirthDate;
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

	<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.SEARCHEMPLOYEE" bundle="${dcpsLables}"></fmt:message></b></legend>

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
						bundle="${dcpsLables}" onclick="ReturnToSearchForSRKA();"/></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	</fieldset>
	<c:if test="${resValue.totalRecords != 0}">
	<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.SEARCHRESULT" bundle="${dcpsLables}"></fmt:message></b></legend>
			<c:set var="counterForSearch" value="0" ></c:set>
	<br>
    <display:table list="${caseList}"  id="vo"   requestURI="" export="" style="width:100%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri${counterForSearch}" id="chkbxFormVeri${counterForSearch}" value="${vo[0]}"/>
		</display:column>
	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<c:out value="${vo[1]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
				<c:out value="${vo[2]}" />
		</display:column>
				
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.GENDER" >    			
    			<c:out value="${vo[3]}"></c:out> 
		</display:column>
      	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="birthDate"/>
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre"  sortable="true"  titleKey="CMN.DOB"  >		
				<c:out value="${birthDate}"></c:out> 
		</display:column>	
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPOFFICE" >		
				<c:out value="${vo[5]}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:center" class="oddcentre" sortable="true"  titleKey="CMN.EMPDESIG" >		
				<c:out value="${vo[6]}"></c:out>
				
				<input type="hidden" name="hidEmpDcpsId${counterForSearch}" id="hidEmpDcpsId${counterForSearch}" value="${resValue.lStrDcpsId}"/>
				<input type="hidden" name="hidEmpName${counterForSearch}" id="hidEmpName${counterForSearch}" value="${resValue.lStrEmpName}"/>
				<input type="hidden" name="hidBirthDate${counterForSearch}" id="hidBirthDate${counterForSearch}" value="${resValue.lStrEmpDOB}"/>
				<c:set var="counterForSearch" value="${counterForSearch+1}"></c:set> 
		</display:column>

	</display:table>
	
		<input type="hidden" id="totalCount" name="totalCount" value="${counterForSearch}" /> 
		<br>
	<div align="center">
		<hdiits:button name="btnBackAgain" id="btnBackAgain" type="button"  captionid="BTN.BACK" 	bundle="${dcpsLables}" onclick="ReturnToSearchForSRKA();"/>	
		<hdiits:button name="btnPrintForm1" id="btnPrintForm1" type="button"  captionid="BTN.PRINTFORM1" bundle="${dcpsLables}" onclick="printForm1();"/>
		<hdiits:button name="btnPrintForm1Ackngmnt" id="btnPrintForm1Ackngmnt" type="button" style="width: 250px" captionid="BTN.PRINTFORM1ACKNMNT" bundle="${dcpsLables}" onclick="printForm1Acmnt();"/>
	</div>
	</fieldset>
	
	
	</c:if>
</hdiits:form>