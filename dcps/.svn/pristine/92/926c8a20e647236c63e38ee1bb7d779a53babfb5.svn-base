<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script language="JavaScript" src="script/paybill/paybillvalidation.js"></script>

<fmt:setBundle basename="resources.paybill.PayBillLabels" var="paybillLables" scope="request"/>

<script>
var rowCountForNomineeDetails=0;
function addRowToTableForNomineeDtls(arrayOfCols,arrayOfHeaders,tableId)
{
	var tbody = document.getElementById(tableId).getElementsByTagName('tbody')[0];	

	 
	if(rowCountForNomineeDetails==0)
	{
		var rowHead = document.createElement('TR');
		for(var i=0;i<arrayOfHeaders.length;i++)
		{
			var cellHeader=document.createElement('TD');
			cellHeader.innerHTML="<label><b>"+arrayOfHeaders[i]+"</b></label>";
			rowHead.appendChild(cellHeader);
		}
		tbody.appendChild(rowHead);
		flag=false;
	}

	var row = document.createElement('TR'); 
	rowCountForNomineeDetails++;

	for(var j=0;j<arrayOfCols.length;j++)
	{
		var cell=document.createElement('TD');
		cell.innerHTML=document.getElementById(arrayOfCols[j]).value;
		row.appendChild(cell);	
	}
	
	var cellForEditButton=document.createElement('TD');
	cellForEditButton.innerHTML="<input type=\"button\" value=\"EDIT\" onclick=\"populateEditForNomineeDetails(true,createArrOfColsForNomineeDtls());\"/>";
	
	var cellForDeleteButton=document.createElement('TD');
	cellForDeleteButton.innerHTML="<img src=\"images/CalendarImages/DeleteIcon.gif\" onclick=\"deleteRow()\" />";
	
  	var cellHiddenRowCount=document.createElement('TD');
  	cellHiddenRowCount.innerHTML="<input type=\"hidden\" value='"+ rowCountForNomineeDetails +"'/>";

  	row.appendChild(cellForEditButton);	
  	row.appendChild(cellForDeleteButton);	
  	row.appendChild(cellHiddenRowCount);	

  	tbody.appendChild(row);

  	//To Reset the Fields After Adding a Row To Table
  	for(var k=0;k<arrayOfCols.length;k++)
	{
		document.getElementById(arrayOfCols[k]).value="";
	} 
}
var currObjForNomineeDetails;
function populateEditForNomineeDetails(bool,arrayOfCols)
{
	if(bool)
	{
		currObjForNomineeDetails = window.event.srcElement;
		flag=1;
		
		while( (currObjForNomineeDetails.tagName != "TR") && (currObjForNomineeDetails.tagName != null) ){
			currObjForNomineeDetails = currObjForNomineeDetails.parentElement;
		}

		var rowObj = currObjForNomineeDetails;
		var cellCount = rowObj.cells.length;
		var tdObj = rowObj.firstChild;
		var colValues = new Array( cellCount );

		for( i = 0; i < cellCount; i++ ) 
		{
			currObjForNomineeDetails = rowObj.cells[i];
			while( (currObjForNomineeDetails != null) && (currObjForNomineeDetails.innerText == null) ) 
			{
				currObjForNomineeDetails = currObjForNomineeDetails.firstChild;
			}
			if( currObjForNomineeDetails != null )
			colValues[i] = currObjForNomineeDetails.innerText;
		}

		for(var i=0;i<arrayOfCols.length;i++)
		{
			document.getElementById(arrayOfCols[i]).value=colValues[i];
		}
			
	}
	else if(bool==false && flag==1)
	{
				
		while( (currObjForNomineeDetails.tagName != "TR") && (currObjForNomineeDetails.tagName != null) ){
			currObjForNomineeDetails = currObjForNomineeDetails.parentElement;
			}
		
		var rowObj2 = currObjForNomineeDetails;
		
		var cellCount2 = rowObj2.cells.length;

		for(var j=0;j<arrayOfCols.length;j++)
		{
			currObjForNomineeDetails=rowObj2.cells[j];
			while( (currObjForNomineeDetails != null) && (currObjForNomineeDetails.innerText == null) ) {
				currObjForNomineeDetails = currObjForNomineeDetails.firstChild;
				}	
			currObjForNomineeDetails.innerText=document.getElementById(arrayOfCols[j]).value;
		}	
		//To Reset the Fields After Updating a Row To Table
	  	for(var k=0;k<arrayOfCols.length;k++)
		{
			document.getElementById(arrayOfCols[k]).value="";
		} 
	}
	else
	{
		alert("Invalid Call to a Function");
	}
			
}
function deleteRow() {  

	var current = window.event.srcElement;
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);


}  
function createArrOfColsForNomineeDtls()
{
	var arrayOfCols=new Array();
	arrayOfCols[0]="txtNomineeSerialNo";
	arrayOfCols[1]="txtName1";
	arrayOfCols[2]="txtBirthDateOfNominee";
	arrayOfCols[3]="txtPercentShare";
	arrayOfCols[4]="cmbRelationship";
	return arrayOfCols;	
}
function createArrOfHeadersForNomineeDtls()
{
	var arrayOfHeaders=new Array();
	arrayOfHeaders[0]="Sr. No";
	arrayOfHeaders[1]="Nominee Name";
	arrayOfHeaders[2]="Date Of Birth";
	arrayOfHeaders[3]="% share";
	arrayOfHeaders[4]="Relationship";
	return arrayOfHeaders;
}
</script>



<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data" validate="true" method="post"  >
<fieldset class="tabstyle">

<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.FIRSTNAME" bundle="${paybillLables}"></fmt:message></td>
		<td width="20%" align="left">
			<input type="text" id="txtFirstName" style="text-transform: uppercase" size="30"  name="txtFirstName" value="" />
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.MIDDLENAME" bundle="${paybillLables}"></fmt:message></td>
		<td width="20%" align="left">
			<input type="text" id="txtMiddleName" style="text-transform: uppercase" size="30"  name="txtMiddleName" value="" />
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.LASTNAME" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtLastName" style="text-transform: uppercase" size="30"  name="txtLastName" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.MALE" bundle="${paybillLables}"></fmt:message>
			<input type="radio" id="radioGender" name="radioGender" value="Male" checked="checked"/>
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.FEMALE" bundle="${paybillLables}"></fmt:message>
			<input type="radio" id="radioGender" name="radioGender" value="Female"/>
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.DOB" bundle="${paybillLables}"></fmt:message></td>
		<td width="" align="left">
			<input type="hidden" name="currDate" id="currDate" value="${resValue.lDtCurDate}"/>
			<input type="text" name="txtBirthDate" id="txtBirthDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="compareDates(this,currDate,'Error','>');" value="" />
			<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtBirthDate",375,570)' style="cursor: pointer;" }/>		
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.DOJ" bundle="${paybillLables}"></fmt:message></td>
		<td width="20%" align="left">
			<input type="text" name="txtJoiningDate" id="txtJoiningDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="compareDates(this,currDate,'Error','>');" value="" />
			<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtJoiningDate",375,570)' style="cursor: pointer;" }/>		
		</td>
	</tr>
</table>

<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.CADREINPARENTDEPT" bundle="${paybillLables}"></fmt:message></b>
</legend>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.PARENTFIELDDEPT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtParentFieldDept"  size="30"  name="txtParentFieldDept" value="" />
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.CHANGEPARENTDEPT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="checkbox" name="cbChangeParentDept" value="ChangeParentDept" checked>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CADRE" bundle="${paybillLables}"></fmt:message></td>
		<td>		
			<select name="cmbCadre" id="cmbCadre" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.GROUP" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtGroup"  size="30"  name="txtGroup" value="" />
		</td>
	</tr>
</table>	
</fieldset>
<fieldset class="tabstyle">
	<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CURRENTOFFICE" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbCurrentOffice" id="cmbCurrentOffice" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.DESIGNATION" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbDesignation" id="cmbDesignation" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.PAYSCALE" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtPayScale"  size="30"  name="txtPayScale" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.RESIDENTIALADDRESS" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtResidentialAddress"  size="30"  name="txtResidentialAddress" value="" />
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.OFFICECONTACTNO" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtOfficeContactNo"  size="30"  name="txtOfficeContactNo" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.ADDRESS2" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtAddress2"  size="30"  name="txtAddress2" value="" />
		</td>
		<td width="20%" align="left"><fmt:message key="CMN.CELLNO" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtCellNo"  size="30"  name="txtCellNo" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CONTACTTELNO" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtContactTelNo"  size="30"  name="txtContactTelNo" value="" />
		</td>
	</tr>
	</table>
</fieldset>
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.INVESTMENTDETIALS" bundle="${paybillLables}"></fmt:message></b>
</legend>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFPENSIONFUNDMNGR" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfPensionFundMngr" id="cmbChoiceOfPensionFundMngr" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.CHOICEOFASSETALLOCATION" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbChoiceOfAssetAllocation" id="cmbChoiceOfAssetAllocation" style="width:240px"  onChange="" >
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
			<input type="text" id="txtEquity"  size="30"  name="txtEquity" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.CORPORATEDEBT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtCorporateDebt"  size="30"  name="txtCorporateDebt" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="20%" align="left">&nbsp;&nbsp;&nbsp;</td>
		<td width="10%" align="left"><fmt:message key="CMN.GOVTDEBT" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" id="txtGovtDebt"  size="30"  name="txtGovtDebt" value="" />
		</td>
		<td width="40%" align="left">&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>	
</fieldset>
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.NOMINEEDETAILS" bundle="${paybillLables}"></fmt:message></b>
</legend>
<table width="100%" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="10%" align="left"><fmt:message key="CMN.NOMINEESRNO" bundle="${paybillLables}"></fmt:message></td>
		<td width="20%" align="left">
			<input type="text" id="txtNomineeSerialNo"  size="30"  name="txtNomineeSerialNo" value="" />
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.NAME" bundle="${paybillLables}"></fmt:message></td>
		<td width="" align="left">
			<input type="text" id="txtName1"  size=""  name="txtName1" value="" />
			<input type="text" id="txtName2"  size=""  name="txtName2" value="" />
			<input type="text" id="txtName3"  size=""  name="txtName3" value="" />
		</td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.ADDRESS1" bundle="${paybillLables}"></fmt:message></td>
		<td width="20%" align="left">
			<input type="text" id="txtAddress1"  size="30"  name="txtAddress1" value="" />
		</td>
	</tr>	
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.ADDRESS2" bundle="${paybillLables}"></fmt:message></td>
		<td width="" align="left">
			<input type="text" id="txtAddress"  size="30"  name="txtAddress" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.DOB" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="hidden" name="currDate" id="currDate" value="${resValue.lDtCurDate}"/>
			<input type="text" name="txtBirthDateOfNominee" id="txtBirthDateOfNominee" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="compareDates(this,currDate,'Error','>');" value="" />
			<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtBirthDateOfNominee",375,570)' style="cursor: pointer;" }/>		
		</td>
		<td width="10%" align="left"><fmt:message key="CMN.PERCENTSHARE" bundle="${paybillLables}"></fmt:message></td>
		<td width="40%" align="left">
			<input type="text" id="txtPercentShare"  size="30"  name="txtPercentShare" value="" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="CMN.RELATIONSHIP" bundle="${paybillLables}"></fmt:message></td>
		<td width="30%" align="left">
			<select name="cmbRelationship" id="cmbRelationship" style="width:240px"  onChange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			</select>
		</td>
	</tr>
</table>	
<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
			<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="BTN.SAVE" bundle="${paybillLables}" onclick=""/>
			<hdiits:button name="btnAdd" id="btnAdd" type="button" captionid="BTN.ADD" bundle="${paybillLables}"  onclick="addRowToTableForNomineeDtls(createArrOfColsForNomineeDtls(),createArrOfHeadersForNomineeDtls(),'displayTableForNomineeDtls')" />
			<hdiits:button name="btnView" id="btnView" type="button"  captionid="BTN.VIEW" bundle="${paybillLables}" onclick=""/>
		</td>
	</tr>	
</table>
<table id="displayTableForNomineeDtls" align="center" width="50%" border="1" bordercolor="red">

			
</table>
</fieldset>

<table width="100%" align="center" height="10%" cellpadding="0" cellspacing="0">
	<tr></tr>
	<tr></tr>
	<tr>
		<td width="100%" align="center">
			<hdiits:button name="btnSaveData" id="btnSaveData" type="button"  captionid="BTN.SAVE" bundle="${paybillLables}" onclick=""/>
			<hdiits:button name="btnSubmit" id="btnSubmit" type="button" captionid="BTN.SUBMIT" bundle="${paybillLables}"  onclick="" />
			<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${paybillLables}" onclick=""/>
		</td>
	</tr>	
</table>
</fieldset>
</hdiits:form>
		