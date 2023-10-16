<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@page import="com.tcs.sgv.common.valueobject.CmnLocationMst" %>
<%@ page language="java" session="true"%>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ddoList" value="${resValue.ddoHistoryList}"></c:set>
<c:set var="count" value="${resValue.count}"></c:set>
<c:set var="lFlag" value="${resValue.lFlag}"></c:set>
<script src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>

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

function popUpDetails(sr_no,ddo_code,ddo_name,startDate,endDate){
	//alert("hello bhaiya jee smile");
//	var empID = document.getElementById("emp_id").value;
	//alert(sr_no);
	document.getElementById("newEntry").style.display = 'none';	
	document.getElementById("updatedtxtNameNew").value='';
	var dateFormat=new Array();
	dateFormat=startDate.split(" ");
	var date=new Array();
	date=dateFormat[0].split("-"); 
	var formatedStartDate=date[2]+'/'+date[1]+'/'+date[0];

	var dateFormat1=new Array();
	dateFormat1=endDate.split(" ");
	var date1=new Array();
	date1=dateFormat1[0].split("-"); 
	var formatedEndDate=date1[2]+'/'+date1[1]+'/'+date1[0];

	
	document.getElementById("srNo").value=+sr_no;
	
	document.getElementById("updateDdoName").value=ddo_name;
	document.getElementById("updatedtxtFromDate").value="";
	document.getElementById("updatedtxtTodate").value="";
	
	document.getElementById("updatedetails").style.display = '';
	
	//alert('Please Enter the details and update.');
	//alert("hii i m at last");
}
function updateDDODetails(){
	//alert("hello bhaiya jee fir se smile");
	var srNo = document.getElementById("srNo").value;
	//alert(srNo);
	var updateDdoName  = document.getElementById("updateDdoName").value;
	//alert(updateDdoName);
	var updatedtxtFromDate  = document.getElementById("updatedtxtFromDate").value;
	//alert(updatedtxtFromDate);
	var updatedtxtTodate  = document.getElementById("updatedtxtTodate").value;
	//alert(updatedtxtTodate);
	if(updateDdoName.trim() == "")
	{
		alert("Ddo Name Cannot be Empty.");
		document.getElementById("updateDdoName").focus();		
		return false;
	}else if(updatedtxtFromDate.trim()== ""){
		alert("From Date Cannot be Empty.");
		document.getElementById("updatedtxtFromDate").focus();		
		return false;
	}
	var answer = confirm("Are you sure you want to update details.?");
	if(answer)
	{
	document.getElementById("actionFlag").value = "updateDdoDetails";
	document.getElementById("srNo").value = srNo;
	document.getElementById("updateDdoName").value = updateDdoName;
	document.getElementById("updatedtxtFromDate").value = updatedtxtFromDate;
	document.getElementById("updatedtxtTodate").value = updatedtxtTodate;
	var url = "ifms.htm";
	document.frmEmpStatistics.action = url ;
	document.frmEmpStatistics.submit();
	}
}

function enterNewEntry(){

	var genUserId='${userId}';
	var genAction='newE';
	var genRandomNo=Math.floor(Math.random()*10001);
	var genTokenNo=genUserId+genAction+genRandomNo;
	document.getElementById("genUser").value=genUserId;
	document.getElementById("genTokenNo").value=genTokenNo;
	document.getElementById("genRandomNo").value=genRandomNo;
	//ended by roshan for Token Number
	//alert(genRandomNo);
	
	var updatedtxtNameNew  = document.getElementById("updatedtxtNameNew").value;
	//alert(updatedtxtNameNew);
	var updatedtxtFromDateNew  = document.getElementById("updatedtxtFromDateNew").value;
	//alert(updatedtxtFromDateNew);
	var updatedtxtTodateNew  = document.getElementById("updatedtxtTodateNew").value;
	//alert(updatedtxtTodateNew);
	if(updatedtxtNameNew.trim() == "")
	{
		alert("Ddo Name Cannot be Empty.");
		document.getElementById("updatedtxtNameNew").focus();		
		return false;
	}
	if(updatedtxtFromDateNew.trim() == "")
	{
		alert("From Date Cannot be Empty.");
		document.getElementById("updatedtxtFromDateNew").focus();		
		return false;
	}
	var answer = confirm("Are you sure you want to make new Entry ?");
	if(answer)
	{
	document.getElementById("actionFlag").value = "newEntryForDDODetails";
	document.getElementById("updatedtxtNameNew").value = updatedtxtNameNew;
	document.getElementById("updatedtxtFromDateNew").value = updatedtxtFromDateNew;
	document.getElementById("updatedtxtTodateNew").value = updatedtxtTodateNew;
	var url = "ifms.htm";
	
	document.frmEmpStatistics.action = url ;	
	document.frmEmpStatistics.submit();
	}

	
}


function createNewEntry(){
	//alert("haaaa haahahha");
	document.getElementById("updatedetails").style.display = 'none';
	document.getElementById("updateDdoName").value='';
	document.getElementById("newEntry").style.display = '';	
}

</script>

<body>               

	
<form  name="frmEmpStatistics" action="" id="frmEmpStatistics" method="post">

<%-- ADDED BY ROSHAN FOR IMPLEMENTING TOKEN MUBER --%>
<input type="hidden" name="genUser" id="genUser"/>
<input type="hidden" name="genTokenNo" id="genTokenNo"/>
<input type="hidden" name="genRandomNo" id="genRandomNo"/>
<%-- ENDED BY ROSHAN FOR IMPLEMENTING TOKEN MUBER --%>

<c:if test="${ddoList != null && ddoList[0] != null}">

<fieldset class="tabstyle" ><legend>DDO History</legend>
	<br/>
	<a><font color="green">Please click on ddo name to update details.
	</font></a>
	<br/>
	<div>	
    <display:table list="${ddoList}"  id="vo" style="width:100%"  pagesize="500" requestURIcontext="false" requestURI="" >	

		<display:setProperty name="paging.banner.placement" value="top" />	
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="DDO Name" >					
				<a href=#
				onclick="popUpDetails('${vo[0]}','${vo[1]}','${vo[2]}','${vo[3]}','${vo[4]}');"><c:out value="${vo[2]}"></c:out>
				
		</display:column>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${emp[3]}" var="DOB"/>
				<c:if test="${emp[3] != null}">	
				<c:out value="${DOB}"></c:out>
				</c:if>
				<c:if test="${emp[3] == null}">		
				<c:out value="-"></c:out>
		</c:if>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="Start date" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="stDate"/>					
				<c:out value="${stDate}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" sortable="true" title="End Date" >	
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="edDate"/>
				<c:out value="${edDate}"></c:out>
		</display:column>
			<display:column headerClass="datatableheader" style="text-align:left" class="oddcentre" title="DDO Code" >		
				<c:out value="${vo[1]}"></c:out>
		</display:column>
		</display:table>	
	</div>
	<div align="center">
		<hdiits:button	name="BTN.CREATE" id="btnCreate" type="button" style="width: 20%"
											captionid="BTN.CREATE" bundle="${commonLables}"
											onclick="createNewEntry();" />
	</div>
</fieldset>
</c:if>

<fieldset class="tabstyle" id="newEntry" style="display:none"><legend>Add New Entry</legend>
<table>
<tr>
<td width="15%" align="left" style="display:none" ><fmt:message
			key="CMN.SRNO" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left" style="display:none"><input type="text"
					id="srNo" size="30" maxlength="99"
					name="srNo"/> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
<td width="15%" align="left"><fmt:message
			key="CMN.DDONAME" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="updatedtxtNameNew" size="30" maxlength="99" onblur="isName(this,'This field should not contain any special characters or digits.');"
					name="updatedtxtNameNew" /> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
					</tr>

<tr>					
<td width="15%" align="left"><fmt:message
			key="CMN.FROMDATE" bundle="${commonLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
			name="updatedtxtFromDateNew" id="updatedtxtFromDateNew" maxlength="10" onBlur="chkValidDate(this);"
			onkeypress="digitFormat(this);dateFormat(this);"
			/><img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtBirthDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /><label class="mandatoryindicator" ${varLabelDisabled}>  *</label></td>
			
			<td width="15%" align="left"><fmt:message
			key="CMN.TODATE" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
			name="updatedtxtTodateNew" id="updatedtxtTodateNew" maxlength="10" onBlur="chkValidDate(this);"
			onkeypress="digitFormat(this);dateFormat(this);"
			/><img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtBirthDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /></td>
			</tr>

</table>
<div align="center">
		<hdiits:button	name="BTN.CREATENEWENTRTY" id="btnCreateNewEntry" type="button" style="width:15%"
											captionid="BTN.CREATENEWENTRTY" bundle="${commonLables}"
											onclick="enterNewEntry();" />
	</div>
</fieldset>


<fieldset class="tabstyle" id="updatedetails" style="display:none"><legend>Update Details.</legend>
<table>
<tr>
<td width="15%" align="left" style="display:none"><fmt:message
			key="CMN.SRNO" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left" style="display:none"><input type="text"
					id="srNo" size="30" maxlength="99"
					name="srNo" /> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
<td width="15%" align="left"><fmt:message
			key="CMN.DDONAME" bundle="${commonLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="updateDdoName" size="30" maxlength="99" onblur="isName(this,'This field should not contain any special characters or digits.');"
					name="updateDdoName" /> <label class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
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
			style="cursor: pointer;" ${varImageDisabled} /><label class="mandatoryindicator" ${varLabelDisabled}>  *</label></td>
			
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
<div align="center">
		<hdiits:button	name="BTN.UPDATE" id="btnUpdate" type="button" style="width:15%"
											captionid="BTN.UPDATE" bundle="${commonLables}"
											onclick="updateDDODetails();" />
	</div>
</fieldset>



<input type = "hidden" name = "actionFlag"  id = "actionFlag">
<input type = "hidden" name = "srNo"  id = "srNo">
<input type = "hidden" name = "updateDdoName"  id = "updateDdoName">
<input type = "hidden" name = "updatedtxtFromDate"  id = "updatedtxtFromDate">
<input type = "hidden" name = "updatedtxtTodate"  id = "updatedtxtTodate">
<input type = "hidden" name = "updatedtxtNameNew"  id = "updatedtxtNameNew">
<input type = "hidden" name = "updatedtxtFromDateNew"  id = "updatedtxtFromDateNew">
<input type = "hidden" name = "updatedtxtTodateNew"  id = "updatedtxtTodateNew">

</form>

<ajax:autocomplete source="txtDdocode" target="txtDdocode" baseUrl="ifms.htm?actionFlag=getDdoCodeForAutoComplete"
	parameters="searchKey={txtDdocode}" className="autocomplete" minimumCharacters="4" indicator="roleIndicatorRegion"/>	
