<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>

<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="script/common/commonCalculations.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/eis/ITOtherDetails.js"></script>
<script type="text/javascript" src="script/hrms/eis/PayTabNavigation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/cmbBoxFillUp.js"/>"></script>
	

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="financialYearList" value="${resValue.financialYearList}" ></c:set>
<c:set var="bankList" value="${resValue.bankList}" />
<c:set var="isUpdate" value="${resValue.isUpdate}" />
<c:set var="dataFound" value="${resValue.dataFound}" />

<c:set var="Form16Dtls" value="${resValue.Form16Dtls}" ></c:set>
<c:set var="cmnLookupMstForYear" value="${resValue.cmnLookupMstForYear}" ></c:set>
<c:set var="isFound" value="${resValue.isFound}" ></c:set>
<c:set var="empLastName" value="${resValue.empLastName}" ></c:set>
<c:set var="GPF" value="${resValue.GPF}" ></c:set>
<c:set var="GSalary" value="${resValue.GSalary}" ></c:set>

<c:set var="loginLocId" value = "${resValue.baseLoginMap.locationId}" />  

<c:set var="Form16TaxDeducDtls1" value="${resValue.Form16TaxDeducDtls1}" />
<c:set var="Form16TaxDeducDtls2" value="${resValue.Form16TaxDeducDtls2}" />
<c:set var="Form16TaxDeducDtls3" value="${resValue.Form16TaxDeducDtls3}" />
<c:set var="Form16TaxDeducDtls4" value="${resValue.Form16TaxDeducDtls4}" />
<c:set var="Form16TaxDeducDtls5" value="${resValue.Form16TaxDeducDtls5}" />
<c:set var="Form16TaxDeducDtls6" value="${resValue.Form16TaxDeducDtls6}" />
<c:set var="Form16TaxDeducDtls7" value="${resValue.Form16TaxDeducDtls7}" />
<c:set var="Form16TaxDeducDtls8" value="${resValue.Form16TaxDeducDtls8}" />
<c:set var="Form16TaxDeducDtls9" value="${resValue.Form16TaxDeducDtls9}" />
<c:set var="Form16TaxDeducDtls10" value="${resValue.Form16TaxDeducDtls10}" />
<c:set var="Form16TaxDeducDtls11" value="${resValue.Form16TaxDeducDtls11}" />
<c:set var="Form16TaxDeducDtls12" value="${resValue.Form16TaxDeducDtls12}" />
<c:set var="Form16TaxDeducDtls13" value="${resValue.Form16TaxDeducDtls13}" />
<c:set var="Form16TaxDeducDtls14" value="${resValue.Form16TaxDeducDtls14}" />


<fmt:formatDate value="${Form16TaxDeducDtls1.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate1"/>
<fmt:formatDate value="${Form16TaxDeducDtls2.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate2"/>
<fmt:formatDate value="${Form16TaxDeducDtls3.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate3"/>
<fmt:formatDate value="${Form16TaxDeducDtls4.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate4"/>
<fmt:formatDate value="${Form16TaxDeducDtls5.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate5"/>
<fmt:formatDate value="${Form16TaxDeducDtls6.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate6"/>
<fmt:formatDate value="${Form16TaxDeducDtls7.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate7"/>
<fmt:formatDate value="${Form16TaxDeducDtls8.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate8"/>
<fmt:formatDate value="${Form16TaxDeducDtls9.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate9"/>
<fmt:formatDate value="${Form16TaxDeducDtls10.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate10"/>
<fmt:formatDate value="${Form16TaxDeducDtls11.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate11"/>
<fmt:formatDate value="${Form16TaxDeducDtls12.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate12"/>
<fmt:formatDate value="${Form16TaxDeducDtls13.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate13"/>
<fmt:formatDate value="${Form16TaxDeducDtls14.deducDtlsDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="deducDtlsDate14"/>


<c:set var="isUpdate" value="${resValue.isUpdate}" />

<script type="text/javascript">

function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}

function checkAvailability()
{
	empId = document.getElementById("Employee_ID_ITDetails").value;
	if(empId!='') {
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		}
		var url = "hrms.htm?actionFlag=checkEmpAvailability&empId="+empId;  	
   var available=0;	
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var empIdMapping = XMLDocForAjax.getElementsByTagName('empIdMapping');	
				var flag="true";
							
				if(empIdMapping.length != 0) {		
						if(empIdMapping[0].childNodes[0].text==flag){
							available=1;				
							//return true;
						}	
						else {
						    alert("Record no found");
						    
							//return false;	
						}
				}				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	if(available==1)
		return true;
	else
		return false;	
	}




function openForBankDetails(){

	var div2 = document.getElementById('div1');
	
	
	if(div2.style.visibility=="visible")
	{
		var table = document.getElementById('divtable');
		if(table){
			table.style.display = "none";
		}
		div2.style.visibility = "hidden";		
	}
	else
	{
		var endRight1 = window.event.clientX;
		var startTop1 = window.event.clientY;			
		
		var offWidth1=0;
	
		div2.style.left = (endRight1 - 100) + 'px';
		div2.style.top = (startTop1) + 'px';
		div2.style.width = "600px";
		div2.style.visibility = "visible";
		var table = document.getElementById('divtable');
		if(table){
			table.style.display = "";
		}
	}
}

function keyPressHandler(e) {
    var kC  = (window.event) ?    // MSIE or Firefox?
               event.keyCode : e.keyCode;
    var Esc = (window.event) ?   
              27 : e.DOM_VK_ESCAPE // MSIE : Firefox
    if(kC==Esc){
		
    	closeDiv();
    	
    }
 }

function closeDiv(){
	
	var div2 = document.getElementById('div1');
	var table = document.getElementById('divtable');
	if(table){
		table.style.display = "none";
		
	}
	if(div2.style.visibility=="visible")
	{	
		
		div2.style.visibility = "hidden";		
	}
}
function ChangeAll(){
	var strValue = document.ITOtherDetails.cmbBankName1.value;
	if(strValue != 'Select'){
		for(var i=2 ; i<15 ; i++){
			document.getElementById("cmbBankName"+i).value = strValue;
		}
	}
}
function ChangeAllDates(){
	var value = document.ITOtherDetails.M1Date.value;

	var dateValues = value.split('/');

	var date = dateValues[0];
	var month = dateValues[1];
	var year = dateValues[2];

	
	
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M2Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;
		if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M2Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M3Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M3Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M4Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M4Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M5Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M5Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M6Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M6Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M7Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M7Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M8Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M8Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M9Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M9Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M10Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M10Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M11Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M11Date.value = date + "/" + month + "/" +year;
	}
	if(month < 12){
		month++;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M12Date.value = date + "/" + month + "/" +year;
	}else{
		year++;
		month = 1;if(month < 10){	month = "0"+month;}
		document.ITOtherDetails.M12Date.value = date + "/" + month + "/" +year;
	}
}

function updateOtherDetails(){
	
	var id = '${Form16Dtls.form16DtlId}';
	
	document.ITOtherDetails.action = "./hrms.htm?actionFlag=updateForm16Details&update=Y&dtlsId="+id;
	document.ITOtherDetails.submit();
}

function chkEmpId(){
	var isUpdate = '${isUpdate}';

	if(isUpdate != 'Yes'){
		var id = document.getElementById("Employee_ID_ITDetails").value;
		var yearValue = document.ITOtherDetails.FinYear.value; 
		
	
		if(id != '' && yearValue != '' && yearValue != 'Select'){
			
			
			var url = "hrms.htm?actionFlag=insertITOtherDetails&chk=true&empId="+id+"&year="+yearValue;    
			new Ajax.Request(encodeURI(url),
			{
				method: 'post',
				onSuccess: getResponse,
		        onFailure: alertOnFailure
		    });
		}
		
	}
	
		
	return true;
}
function getResponse(transport)
{
	
	var xmlStr = transport.responseText;	
	var XMLDoc= getDOMFromXML(xmlStr);

	
	if(XMLDoc!=null)
    {
		
    	var entries = XMLDoc.getElementsByTagName('formDtls');					    					    	
		var isPresent = entries[0].childNodes[0].text;
		
		if(isPresent == 'present'){
				var id = document.getElementById("Employee_ID_ITDetails").value;
				var yearValue = document.ITOtherDetails.FinYear.value; 
				alert("Form 16 Data Already Exists for this Employee .");
				document.ITOtherDetails.action = "./hrms.htm?actionFlag=updateForm16Details&directUpdate=N&year="+yearValue+"&empId="+id;
		 		document.ITOtherDetails.submit();
				//clearEmployee('ITDetails');
				//document.ITOtherDetails.Employee_Name_ITDetails.focus();
		}else{
		var id = document.getElementById("Employee_ID_ITDetails").value;
		var yearValue = document.ITOtherDetails.FinYear.value; 
		
	
		if(id != '' && yearValue != '' && yearValue != 'Select'){
			
			
			
			var url = "hrms.htm?actionFlag=GetGrossSalary&empId="+id+"&year="+yearValue+"&isAjax=true";    
			new Ajax.Request(encodeURI(url),
			{
				method: 'post',
				onSuccess: gotGrossSalary,
		        onFailure: alertOnFailure
		    });
		}
		}
    }
}
var GlobalSalary = '${GSalary}';
var GlobalGPF = '${GPF}';

function gotGrossSalary(transport){
	var xmlStr = transport.responseText;	
	var XMLDoc= getDOMFromXML(xmlStr);

	
	if(XMLDoc!=null)
    {	
    	var entries = XMLDoc.getElementsByTagName('EmpData');					    					    	
		var salary = entries[0].childNodes[0].text;
		var gpf = entries[0].childNodes[1].text;
		
		document.ITOtherDetails.GrossSalary.value = salary;
		document.ITOtherDetails.GPF.value = gpf;
		
		GlobalSalary =  salary;
		GlobalGPF = gpf;
    }
}
function alertOnFailure()
{
	alert('Error');
}

function addtoSalary(){
	if(document.getElementById("OtherAllowance").value != ''){
		document.ITOtherDetails.GrossSalary.value = parseInt(GlobalSalary) + parseInt(document.getElementById("OtherAllowance").value) ;
		}
	
}
function addtoGPF(){
if(document.getElementById("gpfCpf").value != ''){
	document.ITOtherDetails.GPF.value = parseInt(GlobalGPF) + parseInt(document.getElementById("gpfCpf").value);	
	}
}

function populateChkBox(){
	var hbaClaimed = '${Form16Dtls.hbaInterestClaimed}';
	var hbaRepayClaimed = '${Form16Dtls.hbaRepayClaimed}';
	
	
	if(hbaClaimed != '' && parseInt(hbaClaimed) == 1)
	{
		document.ITOtherDetails.hbaIntrestClaimed.checked = true;
	}else{
		document.ITOtherDetails.hbaIntrestClaimed.checked = false;
	}
	
	if(hbaRepayClaimed != '' && parseInt(hbaRepayClaimed) == 1)
	{
		document.ITOtherDetails.hbaRepayClaimed.checked = true;
	}else{
		document.ITOtherDetails.hbaRepayClaimed.checked = false;
	}
}


</script>
<body onkeyup="keyPressHandler(event)">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


 
<hdiits:form name="ITOtherDetails" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertITOtherDetails" encType="text/form-data">
	
	<div id="div1" style="background-color: #FFFFFF; border: 3px solid black;visibility:hidden;position:absolute; z-index:50;top:20px;left:90px;" > 	
		<table id="divtable" align="center" style="display: none">
			<tr>
				<td width="10%"></td>	
				<td width="20%"><b>BSR Code of Bank Branch/PAO</b></td>
				<td width="10%"><b>Date on which Tax was deposited</b></td>
				<td width="10%"><b>Tax deposited</b></td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.April" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName1"  id="cmbBankName1" size="1"   onchange="ChangeAll()" mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls1.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls1.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" name="M1Date" captionid="HR.EIS.Date" onblur="ChangeAllDates()" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls1.incomeTax}" name="it1" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it1" maxlength="10" /> 
				</td>
				
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.May" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName2"   id="cmbBankName2" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls2.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls2.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M2Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls2.incomeTax}" name="it2" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it2" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.June" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName3"   id="cmbBankName3" size="1"   mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls3.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls3.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M3Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls3.incomeTax}" name="it3" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it3" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.July" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName4"   id="cmbBankName4" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls4.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls4.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M4Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls4.incomeTax}" name="it4" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it4" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.August" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName5"   id="cmbBankName5" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls5.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls5.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M5Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls5.incomeTax}" name="it5" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it5" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.Sept" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName6"   id="cmbBankName6" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls6.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls6.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M6Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls6.incomeTax}" name="it6" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it6" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.Oct" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName7"   id="cmbBankName7" size="1" mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls7.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls7.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M7Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls7.incomeTax}" name="it7" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it7" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.Nov" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName8"   id="cmbBankName8" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls8.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls8.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise> 
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M8Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls8.incomeTax}" name="it8" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it8" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.Dec" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName9"   id="cmbBankName9" size="1" mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls9.deducDtlsBankId.bankId}"> 
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls9.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M9Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls9.incomeTax}" name="it9" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it9" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}"  key="IT.Jan"/></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName10"   id="cmbBankName10" size="1" mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls10.deducDtlsBankId.bankId}"> 
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls10.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M10Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls10.incomeTax}" name="it10" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it10" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.Feb"/></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName11"   id="cmbBankName11" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls11.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls11.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M11Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls11.incomeTax}" name="it11" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it11" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}"  key="IT.Mar" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName12"    id="cmbBankName12" size="1" mandatory="true"  validation="sel.isrequired" default="${Form16TaxDeducDtls12.deducDtlsBankId.bankId}">
		       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls12.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise></c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M12Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					<hdiits:number default="${Form16TaxDeducDtls12.incomeTax}" name="it12" bundle="${enLables}" captionid="IT.ITax" floatAllowed="true" id="it12" maxlength="10" /> 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}"   key="IT.ArrearTax"/></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName13"   id="cmbBankName13" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls13.deducDtlsBankId.bankId}">
			       			<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
								<c:choose><c:when test="${Form16TaxDeducDtls13.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise>
								</c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M13Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					 
				</td>
			</tr>
			
			<tr>
				<td width="10%"><fmt:message bundle="${enLables}" key="IT.ChallanTax" /></td>	
				<td width="20%">
					<hdiits:select captionid="BM.bankName" bundle="${enLables}"   name="cmbBankName14"   id="cmbBankName14" size="1"  mandatory="true" validation="sel.isrequired" default="${Form16TaxDeducDtls14.deducDtlsBankId.bankId}">
							<hdiits:option value="Select">------Select------</hdiits:option>
		     				<c:forEach items ="${resValue.bankList}" var="list">
		     				
		     					<c:choose><c:when test="${Form16TaxDeducDtls14.deducDtlsBankId.bankId eq list.bankId}">
									<hdiits:option value="${list.bankId}" selected="true"> ${list.bankName} </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${list.bankId}" > ${list.bankName} </hdiits:option>
								</c:otherwise>
							</c:choose>
							</c:forEach>  
					</hdiits:select>	
				</td>
				<td width="10%"><hdiits:dateTime  mandatory="true" validation="txt.isrequired" bundle="${enLables}" captionid="HR.EIS.Date" name="M14Date" ></hdiits:dateTime>
						
				</td>
				<td width="10%">
					 
				</td>
			</tr>
			<tr>
				<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="close" id="close" captionid="eis.close" bundle="${enLables}" onclick="closeDiv()" /></TD>
			</tr>
			
		</table>
	</div>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="IT.AddOtherDetails" bundle="${enLables}"></hdiits:caption></b></font></a></li>
		</ul>
	</div>
	<div class="exhalftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	    <br>
	    
	    
	    <c:choose>
		<c:when test="${isUpdate eq 'Yes'}">
			<table id="btnAddOtherDet" class="tabtable" border="0" >
					<tr>
						<TD class="fieldLabel" align="center" colspan="4"><center><b>
							Update Form 16 details for 	${Form16Dtls.hrEisEmpMst.orgEmpMst.empFname} ${Form16Dtls.hrEisEmpMst.orgEmpMst.empMname} ${Form16Dtls.hrEisEmpMst.orgEmpMst.empLname} 
						</b></center></TD>
					</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table align="center">
		    <tr>
			<TD class="fieldLabel" colspan="2">
						<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
							<jsp:param name="SearchEmployee" value="ITDetails"/>
							<jsp:param name="formName" value="ITOtherDetails"/>
							<jsp:param name="mandatory" value="Yes"/>
							<jsp:param name="functionName" value="chkEmpId"/>
						</jsp:include>
			 </td>
			 <td>
	        <hdiits:hidden id="empId" name = "empId"  />
		    </td>
		    </tr>
		    <tr>   </tr>
		    <tr>   </tr>
		    </table>
		</c:otherwise>
		</c:choose>
	    
	    
	    
	    <table align="center">
		
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.OtherAllow" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="OtherAllowance" default="${Form16Dtls.otherAllow}" style="text-align:right" size="25" caption="IT.OtherAllow" maxlength="10"  onblur="addtoSalary()" /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.GPF" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="gpfCpf" default="${Form16Dtls.gpfCpf}" style="text-align:right" size="25" caption="IT.GPF" maxlength="10"  onblur="addtoGPF()"  /></td>
			<td width="5%"></td>
		</tr>
		<tr id="defaultValues">
			<td width="5%"></td>	
			<td width="20%">
			<td width="20%"><hdiits:number  floatAllowed="true" name="GrossSalary" default="${GSalary}" style="text-align:right" size="15" caption="IT.OtherAllow" maxlength="10"  readonly="true" /> </td>
			<td width="10%">&nbsp;</td>
			<td width="20%"></td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="GPF" default="${GPF}" style="text-align:right" size="15" caption="IT.GPF" maxlength="10"  readonly="true" /> </td>
		</tr>
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.TaxPaidChallan" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number floatAllowed="true" name="TaxPaidChallan" default="${Form16Dtls.challanTax}" style="text-align:right" size="25" caption="IT.TaxPaidChallan" maxlength="10"   /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.TaxDedInArrear" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="TaxDedArrear" default="${Form16Dtls.arrearTax}" style="text-align:right" size="25" caption="IT.TaxDedInArrear" maxlength="10"   /></td>
			<td width="5%"></td>
		</tr>
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.ChallanNumber" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:text name="challanNumber" default="${Form16Dtls.challanNumber}" style="text-align:left" size="25" caption="IT.ChallanNumber" maxlength="20"   /></td>
			<td width="10%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.TravelAllow" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="travelAllow" default="${Form16Dtls.travelAllow}" style="text-align:right" size="25" caption="IT.TravelAllow" maxlength="10"   /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.ForeignAllow" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="foreignAllow" default="${Form16Dtls.foreignAllow}" style="text-align:right" size="25" caption="IT.ForeignAllow" maxlength="10"   /></td>
			<td width="5%"></td>
		</tr>
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.HBA" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="hbaIntrest" default="${Form16Dtls.hbaIntrest}" style="text-align:right" size="25" caption="IT.HBA" maxlength="10"   /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.RepayHBA" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="repayHba" default="${Form16Dtls.repayHba}" style="text-align:right" size="25" caption="IT.RepayHBA" maxlength="10"   /></td>
			<td width="5%"></td>
			
		</tr>
		
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.HBAClaimedCheck" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:checkbox  name="hbaIntrestClaimed" id="hbaIntrestClaimed"  value="Y" bundle="${enLables}" /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.HBARepayCheck" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:checkbox  name="hbaRepayClaimed" id="hbaRepayClaimed"  value="Y" bundle="${enLables}" /></td>
			<td width="5%"></td>
			
		</tr>
		
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.GovIns" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number floatAllowed="true" name="govtInsurance" default="${Form16Dtls.govtInsurance}" style="text-align:right" size="25" caption="IT.GovIns" maxlength="10"   /></td>
			<td width="10%">&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.ProfTax" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%"><hdiits:number  floatAllowed="true" name="profTax" default="${Form16Dtls.profTax}" style="text-align:right" size="25" caption="IT.ProfTax" maxlength="10"   /></td>
			<td width="5%"></td>
		</tr>
		
		<tr>
			<td width="5%"></td>	
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.Quarter" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td width="20%"><hdiits:image source="images/graph/caseOpenNode.gif" onclick="openForBankDetails()" ></hdiits:image></td>
			<td width="10%" >&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.FinYr" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%">
				
				
				<hdiits:select captionid="IT.FinYr" bundle="${enLables}" mandatory="true" validation="sel.isrequired"  name="FinYear"   id="FinYear" size="1"  lookupName="FinancialYear"  default="${cmnLookupMstForYear.lookupName}" onchange="chkEmpId()" >
					<hdiits:option value="Select"> --Select-- </hdiits:option>
				</hdiits:select>
				
			</td>
			<td width="5%"></td>
		</tr>
		
		<tr> </tr> <tr> </tr>
		</table>
 	</div>  
 	<br>
	
	
	<c:choose>
		<c:when test="${isUpdate eq 'Yes'}">
			<table id="btnAddOtherDet" class="tabtable" border="0" >
					<tr>
						<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="UpdateOtherDetail" id="UpdateOtherDetail" captionid="eis.update" bundle="${enLables}" onclick="updateOtherDetails()" /></TD>
					</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table id="btnAddOtherDet" class="tabtable" border="0" >
					<tr>
						<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="addOtherDetail" id="multiOtherDetail" captionid="IT.AddOtherDetails" bundle="${enLables}" onclick="addOtherDetails('addRecord')" /></TD>
					</tr>
			</table>
	
	
	
			<br><font size="2">
			<table id="tblEmpITFormDtls" style="display: none" width="100%" align="center" border="1"  class="datatable" bgcolor="ffffff" bordercolor="aaaaaa" >
	
 			<tr>
 				
 				<td width="7%" align="center"><center><b>Employee Name</b></center></td>
 				
 				<td width="8%" align="center"><center><b>Other Allowance</b></center></td>
 				<td width="8%" align="center"><center><b>Foreign Allowance</b></center></td>
 				<td width="8%" align="center"><center><b>Tax Paid By Challan</b></center></td>
 				<td width="8%" align="center"><center><b>Tax Deducted In Arrears</b></center></td> 		
 					
 				
 				<td width="8%" align="center"><center><b>Travel Allowance</b></center></td>
 				<td width="8%" align="center"><center><b>professional tax</b></center></td>
 				<td width="8%" align="center"><center><b>H.B.A Interest</b></center></td>
 				<td width="8%" align="center"><center><b>G.P.F/C.P.F</b></center></td>
 				<td width="8%" align="center"><center><b>Government Insurance</b></center></td>
 				<td width="8%" align="center"><center><b>Repayment of HBA</b></center></td> 	
 				
 				<td width="8%" align="center"><center><b>Financial Year</b></center></td> 		
 					
 				<td width="5%" align="center"></td> 				
 			</tr>
 			</table>         
	
 			</font>
 				<br>
				 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>	
			 	<jsp:include page="../../core/PayTabnavigation.jsp" />
		</c:otherwise>
	</c:choose>
	
	
 
 	</div>

<hdiits:jsField name="checkForm16Details" jsFunction="checkForm16Details()"/>
<hdiits:hidden name="jsfieldCheckForm16Label" captionid="IT.jsfieldCheckForm16Label" bundle="${enLables}"/>
		
	<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		window.FILL_COMBO_BOX_TAB_WISE = false;

		document.ITOtherDetails.M1Date.value='${deducDtlsDate1}';
		document.ITOtherDetails.M2Date.value='${deducDtlsDate2}';
		document.ITOtherDetails.M3Date.value='${deducDtlsDate3}';
		document.ITOtherDetails.M4Date.value='${deducDtlsDate4}';
		document.ITOtherDetails.M5Date.value='${deducDtlsDate5}';
		document.ITOtherDetails.M6Date.value='${deducDtlsDate6}';
		document.ITOtherDetails.M7Date.value='${deducDtlsDate7}';
		document.ITOtherDetails.M8Date.value='${deducDtlsDate8}';
		document.ITOtherDetails.M9Date.value='${deducDtlsDate9}';
		document.ITOtherDetails.M10Date.value='${deducDtlsDate10}';
		document.ITOtherDetails.M11Date.value='${deducDtlsDate11}';
		document.ITOtherDetails.M12Date.value='${deducDtlsDate12}';
		document.ITOtherDetails.M13Date.value='${deducDtlsDate13}';
		document.ITOtherDetails.M14Date.value='${deducDtlsDate14}';
		
		addtoSalary();
		addtoGPF();
		populateChkBox();

	if(${loginLocId}==300024)
		{
		
			//document.ITOtherDetails.GrossSalary.style.visibility='hidden';
			

			//document.ITOtherDetails.GPF.style.visibility='hidden';
			
			document.getElementById("defaultValues").style.display='none';
		}
		</script>
	<c:choose>
		<c:when test="${isUpdate eq 'Yes'}">	
		</c:when>
		<c:otherwise>
			<hdiits:validate controlNames="OtherAllowance,foreignAllow,TaxPaidChallan,TaxDedArrear,cmbBankName1,cmbBankName2,cmbBankName3,cmbBankName4,cmbBankName5,cmbBankName6,cmbBankName7,cmbBankName8,cmbBankName9,cmbBankName10,cmbBankName11,cmbBankName12,cmbBankName13,cmbBankName14,M1Date,M2Date,M3Date,M4Date,M5Date,M6Date,M7Date,M8Date,M9Date,M10Date,M11Date,M12Date,M13Date,M14Date,FinYear,Employee_Name_ITDetails" locale='<%=(String)session.getAttribute("locale")%>' />
		</c:otherwise>
	</c:choose>
	<c:out value="${isfound}"></c:out> 
	<c:if test="${isfound eq 'No'}">
		<script type="text/javascript"> 
			document.ITOtherDetails.Employee_srchNameText_ITDetails.value = '{empLastName}';
			GoToNewPageITDetails();
		</script>
	</c:if>
	
</hdiits:form>
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>