<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<link rel="stylesheet" href="<c:url value="/themes/${themename}/calendar.css"/>" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"  src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/statusbar.js"/>"></script>	

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="applicationName" value="${resultValue.applicationName}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels" var="AddPay" scope="request"/>
<fmt:setBundle basename="resources.hr.addPay.AdditionalPayAlertMessage" var="alertLables" scope="request" />

<hdiits:form name="addtionalPayDirectPay"  id="addtionalPayDirectPay"   validate="true" method="POST"   encType="text/form-data" > 

<script type="text/javascript">
var rowCount = 1;
var rowNumber;
var userIdList = new Array();
var index = 0;

function SearchEmp(){
		
		var href = "hrms.htm?actionFlag=getEmpSearchSelData&multiple=false&searchFieldName=empInfo";
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}

function empSearch(from){

var empInfo = from[0].split('~');
document.getElementById('userId').value = empInfo[4];
document.getElementById('empInfo').value = empInfo[1];
document.getElementById('searchButton').disabled = false;

}


function getInchargePersonDetail(){
		
		showProgressbar();
		var inchargePersonUserId = document.getElementById('userId').value;
		var counter = 0;
		while(counter < index){
			
			if(inchargePersonUserId == userIdList[counter]){
	
				hideProgressbar();
				return false;
				
			}
		counter++;
		}
		
		userIdList[index] = document.getElementById('userId').value;
		index++;
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		 	
		 	alert('<fmt:message  bundle="${alertLables}" key="AddPay.notSupportAJAX"/>');
		 	return;
		} 
		
		
		var url='hdiits.htm?actionFlag=getInchargePersonDetail&userId='+inchargePersonUserId;
		xmlHttp.onreadystatechange = getpopulateInchargeXmlFile;	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
		

}

function getpopulateInchargeXmlFile(){

	
	if (xmlHttp.readyState == 4) 
	{
		var encXML = xmlHttp.responseText;
		
		if(encXML == 'Error'){
			
			hideProgressbar();
			return false;
		}
		var xmlpath = encXML.split(',');
		hideProgressbar();
		
		
		var i=0;
		while(i<xmlpath.length){
		
			xmlHttp=GetXmlHttpObject();
		
			if (xmlHttp==null){
				
			  	alert('<fmt:message  bundle="${alertLables}" key="AddPay.notSupportAJAX"/>');		
			  	return;
			} 
		
			var xmlDom = getDOMFromXML(xmlpath[i]);
			
			if(xmlDom == null ){
			
			
				var row = document.getElementById('searchEmpResult');
				if(row.rows[1].cells[0].innerHTML== 'No Record Found'){
				
					document.getElementById('searchEmpResult').deleteRow(1);
				}
			
				var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName='+xmlpath[i];
				xmlHttp.onreadystatechange = getpopulateInchargeDetail;
				xmlHttp.open("POST",encodeURI(url),false);
				xmlHttp.send(null);
				
			}
			
			
		i++;
	
		}
		
		
	}
}

function getpopulateInchargeDetail(){
	
	if(xmlHttp.readyState==4){
	
		var decXML = xmlHttp.responseText;
		var xmlDom = getDOMFromXML(decXML);
		
		var row = document.getElementById('searchEmpResult').insertRow();
		var tableStyle = document.getElementById('searchEmpResult');
		
		var fromDateList = getDateAndTimeFromDateObj (getXPathValueFromDOM(xmlDom,'fromDate'));
		var fromDate = fromDateList[0].split(',');
		
		var toDateList = getDateAndTimeFromDateObj (getXPathValueFromDOM(xmlDom,'toDate'));
		var toDate = toDateList[0].split(',');
		
		
		
		row.insertCell(0).innerHTML = "<input type='checkBox' name='checkBox"+rowCount+"' id='checkBox"+rowCount+"' disabled='true' >"+"<input type='hidden' name='inchargeReqId"+rowCount+"' id='inchargeReqId"+rowCount+"' value='"+getXPathValueFromDOM(xmlDom,'hrInchargeDtlRltReqId')+"'>"+"<input type='hidden' name='inchargeId"+rowCount+"' id='inchargeId"+rowCount+"' value='"+getXPathValueFromDOM(xmlDom,'inchargeUserId')+"'>";
		row.insertCell(1).innerHTML = getXPathValueFromDOM(xmlDom,'inchargeForEmpName');
		row.insertCell(2).innerHTML = getXPathValueFromDOM(xmlDom,'postHeld');
		row.insertCell(3).innerHTML = getXPathValueFromDOM(xmlDom,'inchargeEmpName');
		row.insertCell(4).innerHTML = getXPathValueFromDOM(xmlDom,'departmentName');
		row.insertCell(5).innerHTML = getXPathValueFromDOM(xmlDom,'locationName');
		row.insertCell(6).innerHTML = fromDate[0];
		row.insertCell(7).innerHTML = toDate[0];
		row.insertCell(8).innerHTML = getXPathValueFromDOM(xmlDom,'inchargePeriod');
		row.insertCell(9).innerHTML = "<input type='hidden' name='flag"+rowCount+"' id='flag"+rowCount+"' default='S'>"+"<input type='hidden' name='addPayValue"+rowCount+"' id='addPayValue"+rowCount+"' value='"+getXPathValueFromDOM(xmlDom,'sysComputedAddPayValue')+"'>"+"<input type='text' name='finalAddPayValue"+rowCount+"' id='finalAddPayValue"+rowCount+"' value='Not Set' readonly='readonly' style='border:0; font-weight:bold; background-color:"+tableStyle.style.backgroundColor+"'>";
		row.insertCell(10).innerHTML= "<a href ='javascript:{showAddPayDetail(\""+rowCount+"\")}'>Set Pay</A>";
		rowCount++;
	
	
	}

	
}

function setAddPayType(id){

	if(id.value == 'S'){
	
		document.getElementById('userComputedValue').readOnly = true;
		document.getElementById('userComputedValue').style.backgroundColor = document.getElementById('sysComputedValue').style.backgroundColor
		
	}
	
	if(id.value == 'U'){
		
		document.getElementById('userComputedValue').readOnly = false;
		document.getElementById('userComputedValue').style.backgroundColor = 'white';
		document.getElementById('userComputedValue').focus();
		
	}
	
}


function showAddPayDetail(rowNo){

	if(document.getElementById('functionFlag').value != 'Active'){
	
		document.getElementById('assignAddPay').style.display = '';
		document.getElementById('userComputedValue').value = '';
		document.getElementById('sysComputedValue').value = document.getElementById('addPayValue'+rowNo).value;
		
		if(document.getElementById('flag'+rowNo).value == 'U'){
		
			document.getElementById('userComputedValue').value = document.getElementById('finalAddPayValue'+rowNo).value;
			document.getElementById('userComputRadio').checked = true;
		}
		else{
	
			document.getElementById('sysComputRadio').checked = true;
			document.getElementById('userComputedValue').readOnly = true;
	
		}
		
		rowNumber = rowNo;
	
		var row = document.getElementById('searchEmpResult');
    	row.rows[rowNo].cells[10].innerHTML='<font color="Green" >Active</font>';
		document.getElementById('functionFlag').value = 'Active';
	}
	else{
	
		alert('<fmt:message  bundle="${alertLables}" key="checkActive"/>');
	
	}
}

function setAddPay(){

	if(document.getElementById('sysComputRadio').checked){
	
		document.getElementById('assignAddPay').style.display = 'none';
		var addPayValue = new Number(document.getElementById('sysComputedValue').value);
		document.getElementById('finalAddPayValue'+rowNumber).value = addPayValue.toFixed(2);
		document.getElementById('functionFlag').value = 'Edit';
		document.getElementById('flag'+rowNumber).value = 'S';
	}
	
	if(document.getElementById('userComputRadio').checked){
	
		
		if(document.getElementById('userComputedValue').value == ''){
		
			alert('<fmt:message  bundle="${alertLables}" key="addPayAmt"/>');
			return false;
		}
		
		document.getElementById('assignAddPay').style.display = 'none';
		var addPayValue = new Number(document.getElementById('userComputedValue').value);
		document.getElementById('finalAddPayValue'+rowNumber).value = addPayValue.toFixed(2);
		document.getElementById('functionFlag').value = 'Edit';
		document.getElementById('flag'+rowNumber).value = 'U';
		
	}
	
	var row = document.getElementById('searchEmpResult');
    row.rows[rowNumber].cells[10].innerHTML="<a href ='javascript:{showAddPayDetail(\""+rowNumber+"\")}'>Edit</A>";
	document.getElementById('checkBox'+rowNumber).disabled = false;
	
}


function restoreRecord(){

	var row = document.getElementById('searchEmpResult');
	
	if(document.getElementById('finalAddPayValue'+rowNumber).value== 'Not Set'){
	
		document.getElementById('assignAddPay').style.display = 'none';
		document.getElementById('functionFlag').value = 'NotActive';
		row.rows[rowNumber].cells[10].innerHTML="<a href ='javascript:{showAddPayDetail(\""+rowNumber+"\")}'>Set Pay</A>";
		
	}
	else{
		
		document.getElementById('assignAddPay').style.display = 'none';
		document.getElementById('functionFlag').value = 'NotActive';
		row.rows[rowNumber].cells[10].innerHTML="<a href ='javascript:{showAddPayDetail(\""+rowNumber+"\")}'>Edit</A>";
	}	


}

function setSelectAll(id){
	
	
	
	if(id.checked){
	
		var counter = 1;
		while(counter < rowCount){
		
			if(document.getElementById('checkBox'+counter).disabled==false){
				
				document.getElementById('checkBox'+counter).checked = true;
				
			}
			counter++;
				
		}
		
	}
	else{
	
		var counter = 1;
		while(counter < rowCount){
		
			document.getElementById('checkBox'+counter).checked = false;
			counter++;
				
		}
	
	}
	
	

}

function validateData(){

	var counter = 1;
	var selectedRecordNo='';
	while(counter < rowCount){

		if(document.getElementById('checkBox'+counter).checked == true){
		
			selectedRecordNo+=counter;
			
			if(rowCount-counter > 1){
				
				selectedRecordNo+=',';
			}
		}
		
		counter++;	
	}
	
	if(selectedRecordNo==''){
	
		alert('<fmt:message  bundle="${alertLables}" key="selectRecord"/>');
		return false;
	}
	document.getElementById('finalRecordList').value = selectedRecordNo;
	
	document.forms(0).action = 'hdiits.htm?actionFlag=setAddPayDirectDetail';
	document.forms(0).submit();
	
}

</script>

<style type="text/css">

  .modifyColor {
	background-color: #FFFFCC;
	border-color: black;
    
}
</style>

<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="AddPay.AddPay" bundle="${AddPay}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>
	
<table id="empSearchTable" align="center">
<tr>
<td>
<hdiits:caption captionid="AddPay.searchInchargePerson" bundle="${AddPay}"></hdiits:caption></td>
<td>
<hdiits:text name="empInfo" id="empInfo" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; "/>
<img src="images/search_icon.gif" onclick="SearchEmp()">
</td>
</tr>
<tr></tr>
<tr></tr>
<tr>
<td colspan="2" align="center"><hdiits:button type="button" name="searchButton" id="searchButton" captionid="AddPay.search" bundle="${AddPay}"  onclick="getInchargePersonDetail()" readonly="true"/>
<hdiits:hidden name="userId" id="userId" default="0"/>
<hdiits:button name="closeBtn" type="button" id="closeBtn" bundle="${AddPay}" captionid="AddPay.close"/>
</td>
</tr>
</table>	
<br>
<br>
<br>
<hdiits:fieldGroup titleCaptionId="AddPay.inchgDtl" bundle="${AddPay}">

<table id="searchEmpResult" width="100%" align="center" border="1" style="border-collapse: collapse; background-color:${tableBGColor};" border=1 borderColor="black" >
<tr class="datatableheader" style="background-color:${tdBGColor}; font-weight: bold;">
		
		<td><b><hdiits:checkbox name="masterCheckBox" id="masterCheckBox" value="Check All" captionid="AddPay.checkAll" bundle="${AddPay}" onclick="setSelectAll(this)" /></b></td>
		<td><b><hdiits:caption captionid="AddPay.lieuOf" bundle="${AddPay}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="AddPay.post" bundle="${AddPay}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="AddPay.inchrgName" bundle="${AddPay}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="AddPay.DeptName" bundle="${AddPay}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="AddPay.location" bundle="${AddPay}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="AddPay.frmDate" bundle="${AddPay}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="AddPay.toDate" bundle="${AddPay}"></hdiits:caption></b> </td>
		<td><b><hdiits:caption captionid="AddPay.ChrPeriod" bundle="${AddPay}"></hdiits:caption></b></td>
		<td><b><hdiits:caption captionid="AddPay.amtSanctioned" bundle="${AddPay}"></hdiits:caption></b></td>
		<td width="5%"><b><hdiits:caption captionid="AddPay.action" bundle="${AddPay}"></hdiits:caption> </b></td>
		
</tr>
<tr>
<td colspan="11" align="center" id="noRecordRow">No Record Found</td>
</tr>	
</table>
</hdiits:fieldGroup>	
<br>
<br>
<br>
<hdiits:fieldGroup titleCaptionId="AddPay.AddPayDetail" bundle="${AddPay}" id="assignAddPay" expandable="false" style="display:none">
<table id="addPayTable" align="center">
<tr>
<td><hdiits:radio  name="ComputRadio" id="sysComputRadio" captionid="AddPay.SysCal" bundle="${AddPay}" value="S" onclick="setAddPayType(this)" /></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right"><hdiits:caption captionid="AddPay.SanctionAmt" bundle="${AddPay}" /></td>
<td><hdiits:number name="sysComputedValue" id="sysComputedValue" readonly="true"/></td>
</tr>
<tr>
<td><hdiits:radio name="ComputRadio" id="userComputRadio"  captionid="AddPay.UserSanctionAmt" bundle="${AddPay}" value="U" onclick="setAddPayType(this)" /></td>
<td></td>
</tr>
<tr></tr>
<tr>
<td colspan="2" align="right"><hdiits:caption captionid="AddPay.SanctionAmt" bundle="${AddPay}" /></td>
<td><hdiits:number name="userComputedValue" id="userComputedValue" readonly="true" /></td>
</tr>
<tr>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td colspan="3" align="right">
<hdiits:button name="assignAddPayBtn" type="button" bundle="${AddPay}" captionid="AddPay.assignAddPay" onclick="setAddPay()"/>
<hdiits:button name="resetBtn" type="button" bundle="${AddPay}" captionid="AddPay.reset" onclick="restoreRecord()"/>
</td>
</tr>
</table>

</hdiits:fieldGroup>
<br>
<br>	
<center><hdiits:button type="button" name="submitButton" bundle="${AddPay}" captionid="AddPay.Submit" onclick="validateData()"/></center>
	
	<hdiits:hidden name="functionFlag" id="functionFlag" default="SetPay" />
	<hdiits:hidden name="finalRecordList" id="finalRecordList" />
	
	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>


</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

