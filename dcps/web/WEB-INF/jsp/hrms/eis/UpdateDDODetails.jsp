<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ddoList" value="${resValue.ddoHistoryList}"></c:set>
<c:set var="count" value="${resValue.count}"></c:set>
<c:set var="ExistingDDODetails" value="${resValue.ExistingDDODetails}"></c:set>
<c:set var="lFlag" value="${resValue.lFlag}"></c:set>
<c:set var="ddoCode" value="${resValue.ddoCode}"></c:set>
<c:set var="ddoCode" value="${resValue.ddoCode}"></c:set>
<script type="text/javascript" src="script/common/common.js"></script>
<script>
function generateExcel()
{
	//alert("generateExcel Calling");
	var url = "ifms.htm?actionFlag=generateExcel&empStat=Y";
	document.frmEmpStatistics.action = url ;
	document.frmEmpStatistics.submit();
}

function getEmpStatistics()
{	
	var Ddocode = document.getElementById("txtDdocode").value;
	if(Ddocode.trim() == "")
	{
		alert("Ddocode Cannot be Empty.");
		document.getElementById("txtDdocode").focus();		
		return false;
	}		
	else
	{
		var url = "ifms.htm?actionFlag=getEmployeeList&Ddocode="+Ddocode;
		document.frmEmpStatistics.action = url ;
		document.frmEmpStatistics.submit();
	}
		
}
function saveReport() 
{
	document.execCommand("SaveAs");
}
function printReport() 
{

	//document.getElementById('btnExporttoExcel').style.visibility = 'hidden'; // hide
	//document.getElementById('Back').style.visibility = 'hidden'; // hide   
	//document.getElementById('Save').style.visibility = 'hidden'; // hide   
	window.print();
	document.getElementById('Print').style.visibility = 'visible'; // show 
	//document.getElementById('Back').style.visibility = 'visible'; // show 
	//document.getElementById('Save').style.visibility = 'visible'; // show 
}

function popUpDetails(emp_id,emp_name,shalarthID){
	//alert("hello bhaiya jee smile");
//	var empID = document.getElementById("emp_id").value;
	//alert(emp_id);
	//alert(emp_name);
	//alert(shalarthID);
	document.getElementById("txtName").value=' '+emp_name;
	document.getElementById("shalrthID").value=' '+shalarthID;
	document.getElementById("empID").value=' '+emp_id;
	document.getElementById("updatedetails").style.display = '';
	alert('Please Enter service end date and update the Details.');
	//alert("hii i m at last");
}
function updateDDODetailsDetails(){
	//alert("hello bhaiya jee fir se smile");
	var ddoName = document.getElementById("updatedtxtName").value;
	//alert(ddoName);
	var ddoFromDate  = document.getElementById("updatedtxtFromDate").value;
	//alert(ddoFromDate);
	var ddoToDate = document.getElementById("updatedtxtTodate").value;
	//alert(ddoToDate);
	if(ddoName.trim() == "")
	{
		alert("Ddo Name Cannot be Empty.");
		document.getElementById("updatedtxtName").focus();		
		return false;
	}
	
	var answer = confirm("Are you sure you want to update the Details of Existing DDO..?");
	if(answer)
	{
	document.getElementById("actionFlag").value="insertDdoDetails";
	document.getElementById("ddoName").value=ddoName;
	document.getElementById("ddoFromDate").value=ddoFromDate;
	document.getElementById("ddoToDate").value=ddoToDate;
	var url = "ifms.htm";
	document.frmEmpStatistics.action = url ;
	document.frmEmpStatistics.submit();
	}
	//alert("hghghghghghghhhh");
}


</script>

<script>


</script>
<hdiits:form name="frmEmpStatistics" action="" id="frmEmpStatistics" encType="multipart/form-data" validate="true" method="post">


<br/><br/>
<fieldset class="tabstyle" ><legend>Existing DDO Details.</legend>
<br/>
<table>
<tr>

<td width="15%" align="left"><fmt:message
			key="CMN.DDONAME" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtName" size="30" maxlength="99" disabled="disabled"
					name="txtName" value="${ExistingDDODetails}"/> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
					</tr>

<tr>					
<td width="15%" align="left"><fmt:message
			key="CMN.FROMDATE" bundle="${commonLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
			name="txtFromDate" id="txtFromDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);" disabled="disabled" value="${ExistingDDoFromDate}"
			/><label class="mandatoryindicator" ${varLabelDisabled}>  *</label></td>
			
			<td width="15%" align="left"><fmt:message
			key="CMN.TODATE" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
			name="txtTodate" id="txtTodate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);" disabled="disabled"
			/></td>
			</tr>

</table>
<br/>
</fieldset>
<br/><br/>
<fieldset class="tabstyle" ><legend>Update DDO Details.</legend>
<br/><table>
<tr>

<td width="15%" align="left"><fmt:message
			key="CMN.DDONAME" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="updatedtxUName" size="30" maxlength="99" onblur="isName(this,'This field should not contain any special characters or digits.');"
					name="updatedtxtName" /> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
					</tr>

<tr>					
<td width="15%" align="left"><fmt:message
			key="CMN.FROMDATE" bundle="${commonLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
			name="updatedtxtFromDate" id="updatedtxtFromDate" maxlength="10" onBlur="chkValidDate(this);"
			onkeypress="digitFormat(this);dateFormat(this);"
			/><img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtBirthDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} />
			
			<label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
			
			<td width="15%" align="left"><fmt:message
			key="CMN.TODATE" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
			name="updatedtxtTodate" id="updatedtxtTodate" maxlength="10" onBlur="chkValidDate(this);"
			onkeypress="digitFormat(this);dateFormat(this);"
			/><img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtBirthDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /></td>
			</tr>

</table>
<br/>
<div align="center">
		<hdiits:button	name="BTN.UPDATE" id="btnUpdate" type="button" 
											captionid="BTN.UPDATE" bundle="${commonLables}"
											onclick="updateDDODetailsDetails();" style="width = 150%"/>
	</div>
</fieldset>

<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "ddoName"  id = "ddoName">
<input type = "hidden" name = "ddoFromDate"  id = "ddoFromDate">
<input type = "hidden" name = "ddoToDate"  id = "ddoToDate">

</hdiits:form>
<ajax:autocomplete source="txtDdocode" target="txtDdocode" baseUrl="ifms.htm?actionFlag=getDdoCodeForAutoComplete"
	parameters="searchKey={txtDdocode}" className="autocomplete" minimumCharacters="4" indicator="roleIndicatorRegion"/>	
