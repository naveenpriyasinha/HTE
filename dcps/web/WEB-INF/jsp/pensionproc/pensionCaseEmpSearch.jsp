<%try{ %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseConstants" var="pensionConstants" scope="request" />
		<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript" src="script/common/common.js"></script>

<script>
function validateCriteria()
{
	var sevaarthId = document.getElementById("txtSevaarthId").value;	
	var reqType=document.getElementById("cmbClassOfPnsn").value;

	 if(!document.getElementById("PensionCaseTypeRegular").checked &&  !document.getElementById("PensionCaseTypeDeputation").checked)
	 {
		 alert("Please select pension case type");
		 return false ;
	}
	 else if(sevaarthId.trim() == "")
	{
		alert('Please enter Sevaarth Id');
		document.getElementById("txtSevaarthId").focus();
		return false ;

	}else if(reqType == "-1"){
		alert('Please select Type of Pension');
		document.getElementById("cmbClassOfPnsn").focus();
		return false;
	}
	else if(document.getElementById("judCasePendingyes").checked) {
	//	alert("Since Judicial / Departmental enquiry is pending, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return true;
	}
	else if(document.getElementById("deptInqPendingyes").checked) {
	//	alert("Since Judicial / Departmental enquiry is pending, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return true;
	}
/*	else if(document.getElementById("contriReceivedno").checked) {
		alert("Since Pension contribution is not received From AG, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return false;
	}
	else if(document.getElementById("contriSanctionedno").checked) {
		alert("Since Leave contribution is not sanctioned by AG, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return false;
	}*/
	return true ;
}

function submitSearchDetails()
{ 
  var pensionCaseType1=document.getElementById("pensionCaseType1").value;
  var url = "";
//Added by shraddha for Deputation module changes
	if(pensionCaseType1=="Regular"){
		//alert("Inside Regular");
	
	
	var flagPenn = confirm('याच कार्यालयातील अधिकाऱ्याचा सही ने Pension case AG ला पाठवणार आहे का ?? \n जर याचे उत्तर होय असेल तर OK वर क्लिक करा, अन्यथा CANCEL वर क्लिक करा');
     //alert(flagPen);
	if(flagPenn){
		//alert("inside true");
		var flagPen='Y';
		 url = "ifms.htm?actionFlag=loadPenProcInwardForm&elementId=376394&pensionCaseType="+pensionCaseType1+"&flagPen="+flagPen;
		 showProgressbar("Please wait...");
			 if(!validateCriteria())
			{
			return false ;
		}
		 else {

				document.pensionCaseEmpSearchForm.action = url ;
				document.pensionCaseEmpSearchForm.submit();

				}
	}

	else{
		//alert("inside false");
		flagPen='N';
		url = "ifms.htm?actionFlag=loadPenProcInwardForm&elementId=376394&pensionCaseType="+pensionCaseType1+"&flagPen="+flagPen;
		showProgressbar("Please wait...");
		if(!validateCriteria())
		{
		return false ;
	}
		else {

			document.pensionCaseEmpSearchForm.action = url ;
			document.pensionCaseEmpSearchForm.submit();

			}
		}
	
	 

	}

	else{
	 url = "ifms.htm?actionFlag=loadPenProcInwardForm&elementId=376394&pensionCaseType="+pensionCaseType1;
	//alert("url"+url);
	if(!validateCriteria())
	{
		return false ;
	}
	}
	 var sevaarthId=document.getElementById("txtSevaarthId").value;

		if(pensionCaseType1=="Deputation"){
	 	if(!checkIfCommonPool(sevaarthId))
		 {
			 return false;
		}
	 	else{

			document.pensionCaseEmpSearchForm.action = url ;
			document.pensionCaseEmpSearchForm.submit();

			}
		}

		
		else if(pensionCaseType1=="Provisional"){

			var judReaason="",deptReason="";
			if(document.getElementById('judCasePendingyes').checked==true)
			{
				if(document.getElementById('judCasePendingReason').value=='')
					{alert("Please enter Reason.");
					return false;
					}
				else
				{
					judReaason=document.getElementById('judCasePendingReason').value;
				
				}
			}
			 if(document.getElementById('deptInqPendingyes').checked==true){
				if(document.getElementById('deptInqPendingReason').value=='')
					{alert("Please enter Reason.");
					return false;
					}
				else
				{
					deptReason=document.getElementById('deptInqPendingReason').value;
				}
				}
			url=url+"&judCasePendingReason="+judReaason+"&deptInqPendingReason="+deptReason;
			//alert("url2"+url);
			document.pensionCaseEmpSearchForm.action = url ;
			document.pensionCaseEmpSearchForm.submit();
			}

		else{
		//	alert("ur3"+url);
	//var url = "ifms.htm?actionFlag=loadPensionCaseInwardForm&elementId=376394";	
	document.pensionCaseEmpSearchForm.action = url ;
	document.pensionCaseEmpSearchForm.submit();
		}
}
//added by ankita
function checkIfCommonPool(sevaarthId)
{
	//alert(objVal);
	var status=false;
	
	uri = "ifms.htm?actionFlag=checkIfCommonPool&sevaarthId="+sevaarthId;
	myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {if(checkIfCommonPoolAjax(myAjax))status=true;},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	return status;
}
function checkIfCommonPoolAjax(myAjax)
{ 
	var XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].nodeValue;
		if(lSaveStatus == "Fail")
		{
			alert("Please enter employee details belonging to common pool. ");
			return false;
		}
	
		else {
					return true;}
}
	
}
//---------------------------------
function init(){
	
    
       
	var msg = document.getElementById("txtAlert").value;
	var ddoCode = document.getElementById("txtlLngReqPendingDdoCode").value;
	if(msg != "" && msg!=null){
		if(msg == "InvalidEmployee")
			alert("Employee with this sevaarth id " + document.getElementById("hidSevaarthId").value +" does not exist.");
		if(msg == "InvalidEmp")
			alert("Employee with this sevaarth id " + document.getElementById("hidSevaarthId").value +" does not belong to logged in DDO.");
		if(msg == "ReqPending")
			alert("Pension case is already in progress for this employee in DDO "+document.getElementById("txtlLngReqPendingDdoCode").value);
		if(msg == "InvalidType")
			alert("Employee is not applicable for Pension Processing in selected pension type");
		
		document.getElementById("txtSevaarthId").value = '';
		document.getElementById("cmbClassOfPnsn").value = '';
		
	}
}
function expandJC()
{
	//if(document.getElementById("cmbClassOfPnsn").value!= -1){
		var pensionCaseType=null;
		if(document.getElementById('PensionCaseTypeRegular').checked==true){
			pensionCaseType1 ="Regular";
			document.getElementById("pensionCaseType1").value="Regular";
			document.getElementById("judiciary").style.display='';
			document.getElementById("judCasePendingno").checked=true;
			document.getElementById("judCasePendingReason").disabled=true;
			document.getElementById("judCasePendingReason").value="";
			document.getElementById("judCasePendingReason").style.backgroundColor='lightgrey';

			document.getElementById("deptInquiry").style.display='';
			document.getElementById("deptInqPendingno").checked=true;
			document.getElementById("deptInqPendingReason").disabled=true;
			document.getElementById("deptInqPendingReason").value="";
			document.getElementById("deptInqPendingReason").style.backgroundColor='lightgrey';

			//document.getElementById("contriReceived").style.display='none';
			//document.getElementById("contriSanctioned").style.display='none';
		}
		
		else if(document.getElementById('PensionCaseTypeDeputation').checked==true){
			 pensionCaseType1 ="Deputation";
			 document.getElementById("pensionCaseType1").value="Deputation";
		document.getElementById("judiciary").style.display='';
		document.getElementById("judCasePendingno").checked=true;
		document.getElementById("judCasePendingReason").disabled=true;
		document.getElementById("judCasePendingReason").value="";
		document.getElementById("judCasePendingReason").style.backgroundColor='lightgrey';

		document.getElementById("deptInquiry").style.display='';
		document.getElementById("deptInqPendingno").checked=true;
		document.getElementById("deptInqPendingReason").disabled=true;
		document.getElementById("deptInqPendingReason").value="";
		document.getElementById("deptInqPendingReason").style.backgroundColor='lightgrey';

	
	/*	document.getElementById("contriReceived").style.display='';
		document.getElementById("contriReceivedyes").checked=true;
		document.getElementById("contriReceivedReason").disabled=true;
		document.getElementById("contriReceivedReason").value="";
		document.getElementById("contriReceivedReason").style.backgroundColor='lightgrey';
	
		document.getElementById("contriSanctioned").style.display='';
		document.getElementById("contriSanctionedyes").checked=true;
		document.getElementById("contriSanctionedReason").disabled=true;
		document.getElementById("contriSanctionedReason").value="";
		document.getElementById("contriSanctionedReason").style.backgroundColor='lightgrey';*/
	}

	
	//}
	/*else {
		document.getElementById("judiciary").style.display='none';

		}*/
	}
function expandDI()
{

	if(document.getElementById("judCasePendingno").checked){
	
		document.getElementById("deptInquiry").style.display='block';
		document.getElementById("judCasePendingReason").disabled=true;
		document.getElementById("judCasePendingReason").value="";
		document.getElementById("judCasePendingReason").style.backgroundColor='lightgrey';
		document.getElementById("btnSubmitData").disabled=false;
		// document.getElementById("pensionCaseType1").value="Regular";
		
		document.getElementById('ifYes').style.visibility = 'hidden';
	if(document.getElementById('deptInqPendingyes').checked==true )
	{
		 document.getElementById("pensionCaseType1").value="Provisional";
		document.getElementById('ifYes').style.visibility = 'visible';
		document.getElementById("btnSubmitData").disabled=true;

	}
		
		/*if(document.getElementById('deptInqPendingyes').checked==true || document.getElementById('judCasePendingyes').checked==true ){
			
			 document.getElementById("pensionCaseType1").value="Provisional";
	}*/
		else{
			if(document.getElementById('PensionCaseTypeDeputation').checked==true){
				 pensionCaseType1 ="Deputation";
				 document.getElementById("pensionCaseType1").value="Deputation";
				}
				else
				{ document.getElementById("pensionCaseType1").value="Regular";
					}
			}
		
		
		}
	if(document.getElementById("judCasePendingyes").checked) {
		
			document.getElementById("judCasePendingReason").disabled=false;
			document.getElementById("judCasePendingReason").style.backgroundColor='white';
			document.getElementById("deptInquiry").style.display='block';
			document.getElementById("deptInqPendingno").checked=true;
			document.getElementById("deptInqPendingReason").disabled=true;
			document.getElementById("deptInqPendingReason").value="";
			document.getElementById("deptInqPendingReason").style.backgroundColor='lightgrey';

			//alert("inq"+document.getElementById('deptInqPendingyes').value);
			//alert("jud"+document.getElementById('judCasePendingyes').value);
			
			if(document.getElementById('deptInqPendingyes').checked==true || document.getElementById('judCasePendingyes').checked==true ){
				
				 document.getElementById("pensionCaseType1").value="Provisional";
			}
			else{
				if(document.getElementById('PensionCaseTypeDeputation').checked==true){
					 pensionCaseType1 ="Deputation";
					 document.getElementById("pensionCaseType1").value="Deputation";
					}
					else
					{ document.getElementById("pensionCaseType1").value="Regular";
						}

				}
	    document.getElementById('ifYes').style.visibility = 'visible';
		document.getElementById("btnSubmitData").disabled=true;		 
			}
	
	
}

function validDI()
{
	if(document.getElementById("deptInqPendingno").checked){
		
		
		document.getElementById("deptInqPendingReason").disabled=true;
		document.getElementById("deptInqPendingReason").value="";
		document.getElementById("deptInqPendingReason").style.backgroundColor='lightgrey';
		
		document.getElementById('ifYes').style.visibility = 'hidden';
		document.getElementById("btnSubmitData").disabled=false;
		if(document.getElementById('judCasePendingyes').checked==true )
		{
			 document.getElementById("pensionCaseType1").value="Provisional";
			document.getElementById('ifYes').style.visibility = 'visible';
			document.getElementById("btnSubmitData").disabled=true;
		}
		
	/*	if(document.getElementById('deptInqPendingyes').checked==true || document.getElementById('judCasePendingyes').checked==true ){
			
			 document.getElementById("pensionCaseType1").value="Provisional";
	}*/
		else
		{
			if(document.getElementById('PensionCaseTypeDeputation').checked==true){
				 pensionCaseType1 ="Deputation";
				 document.getElementById("pensionCaseType1").value="Deputation";
				}
				else
				{ document.getElementById("pensionCaseType1").value="Regular";
					}		
	}
		
		}
	if(document.getElementById("deptInqPendingyes").checked) {
			
			document.getElementById("deptInqPendingReason").disabled=false;
			document.getElementById("deptInqPendingReason").style.backgroundColor='white';



	//if( document.getElementById("pensionCaseType1").value="Deputation")
	
	//deptInqPendingyes
	//alert("inq"+document.getElementById('deptInqPendingyes').value);
	//alert("jud"+document.getElementById('judCasePendingyes').value);
	if(document.getElementById('deptInqPendingyes').checked==true || document.getElementById('judCasePendingyes').checked==true ){
			
			 document.getElementById("pensionCaseType1").value="Provisional";
	}
	else
	{
	
			if(document.getElementById('PensionCaseTypeDeputation').checked==true){
				 pensionCaseType1 ="Deputation";
				 document.getElementById("pensionCaseType1").value="Deputation";
				}
				else
				{ document.getElementById("pensionCaseType1").value="Regular";
					}

			
	}


	 document.getElementById('ifYes').style.visibility = 'visible';
		document.getElementById("btnSubmitData").disabled=true;		
			}
	

}
/*function contriDI(){

if(document.getElementById("contriReceivedyes").checked){
		
		
		document.getElementById("contriReceivedReason").disabled=true;
		document.getElementById("contriReceivedReason").value="";
		document.getElementById("contriReceivedReason").style.backgroundColor='lightgrey';
		}
	if(document.getElementById("contriReceivedno").checked) {
			
			document.getElementById("contriReceivedReason").disabled=false;
			document.getElementById("contriReceivedReason").style.backgroundColor='white';
		
			}
}


function sancDI(){

	if(document.getElementById("contriSanctionedyes").checked){
			
			
			document.getElementById("contriSanctionedReason").disabled=true;
			document.getElementById("contriSanctionedReason").value="";
			document.getElementById("contriSanctionedReason").style.backgroundColor='lightgrey';
			}
		if(document.getElementById("contriSanctionedno").checked) {
				
				document.getElementById("contriSanctionedReason").disabled=false;
				document.getElementById("contriSanctionedReason").style.backgroundColor='white';
			
				}
}*/
function disableType(){
	if(document.getElementById("txtSevaarthId").value!="" )
	{
		
		document.getElementById("PensionCaseTypeRegular").disabled=true;
		document.getElementById("PensionCaseTypeDeputation").disabled=true;
		
	}
}

//Added by shraddha
function showPensionCasePopup(){
	alert("iNSIDE POPUP");
	var newWindow;
	var height = 400;
	var width =600;
    var posY = screen.height/2 - height/2;
   var posX = screen.width/2 - width/2;
    var urlstring = "ifms.htm?viewName=pensionCasePreparationPopup";
   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top="+posY+",left="+posX;
   	newWindow = window.open(urlstring, "PensionEmpSearch", urlstyle);


}
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<input type="hidden"  name='txtAlert' id="txtAlert" value="${resValue.alertMessage}" />
<input type="hidden"  name='txtlLngReqPendingDdoCode' id="txtlLngReqPendingDdoCode" value="${resValue.lLngReqPendingDdoCode}" />
<input type="hidden"  name='hidSevaarthId' id="hidSevaarthId" value="${resValue.SevaarthId}" />
<input type="hidden"  name='hidPenType' id="hidPenType" value="${resValue.pensionType}" />

<hdiits:form name="pensionCaseEmpSearchForm" validate="" method="post">

<div style="width: 100%">
<fieldset style="width: 100%" class="tabstyle">

		<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
		
			<tr align="center">
				<td width="25%" align="left" />
			<td width="55%" align="left">
		<input type="radio" id="PensionCaseTypeRegular" name="PensionCaseType" value="Regular" onclick="enableID();" >Regular pension case&nbsp;
		<input type="radio" id="PensionCaseTypeDeputation" name="PensionCaseType" value="Deputation" onclick="enableID();" >Pension case on Deputation
				<script	type="text/javascript" language="JavaScript">
		

		function enableID()
		{
			document.getElementById('txtSevaarthId').readOnly  =  false;	
			document.getElementById('cmbClassOfPnsn').disabled  =  false;


			if(document.getElementById("cmbClassOfPnsn").value!= -1)
				expandJC();
			}
</script>
				
				
				</td>
				
			</tr>
		
			<tr align="center">
				<td width="25%" align="left" ><fmt:message key="PPROC.SEVAARTHID" bundle="${pensionLabels}" /></td>
				<td width="55%" align="left"><input type="text"	id="txtSevaarthId" style="text-transform: uppercase" size="25" maxlength="13"	name="txtSevaarthId"   readOnly  =  "readonly" onblur="disableType()" />
				  <label id="mandtryFinal" class="mandatoryindicator">*</label>
				<input type="hidden" name ="pensionCaseType1" id="pensionCaseType1" value="" />
				<script	type="text/javascript" language="JavaScript" >
						document.forms[0].txtSevaarthId.focus();
				</script></td>
			</tr>
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="PPROC.TYPEOFPNSN"	bundle="${pensionLabels}" /></td>
			<td width="50%" align="left">
			<select name="cmbClassOfPnsn" id="cmbClassOfPnsn" onfocus="onFocus(this)" onblur="onBlur(this);" style="width: 80%"  onchange="expandJC()" disabled = "disabled"  >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="<fmt:message key="PPROC.EXTRAORDINARY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.SUPERANNU" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY64" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY65" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING104" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING105" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/></option>					
					<option value="<fmt:message key="PPROC.ABSORPTION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INVALID" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INJURY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPULSORY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPASSIONATE" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/></option>					
					<option value="<fmt:message key="PPROC.COMPENSATION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/></option>										
					<option value="<fmt:message key="PPROC.FAMILYPNSN" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/></option>										
					<option value="<fmt:message key="PPROC.GALLANTRY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/></option>
			</select>
			  <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
		</tr>
	</table>
	<div id="judiciary" style="display: none">
	<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
		<tr>
		<td width="25%" align="left">Any Judicial Case Pending</td>
			<td width="55%" align="left"><input type="radio" id="judCasePendingyes" name="JudiciaryCase"
				value="Yes" onclick="expandDI();">Yes&nbsp; <input type="radio"
				id="judCasePendingno" name="JudiciaryCase" value="No"
				 onclick="expandDI();">No
				</td>
		</tr>
		<tr>
		
	<td width="25%" align="left">Reason</td>
			<td width="55%" align="left"><input type="text" id="judCasePendingReason"></td>
		</tr>
	</table>
	</div>
<div id="deptInquiry" style="display: none">
	<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
		<tr>
			<td width="25%" align="left">Any Departmental Inquiry Pending</td>
			<td width="55%" align="left">
			<input type="radio" id="deptInqPendingyes"	name="deptInqPending" value="Yes" onclick="validDI();">Yes&nbsp; 
				<input type="radio" id="deptInqPendingno" name="deptInqPending" value="No" onclick="validDI();">No
			</td>
		</tr>
		<tr>
			<td width="25%" align="left">Reason</td>
			<td width="55%" align="left"><input type="text"  id="deptInqPendingReason"></td>
		</tr>
	</table>
	</div>
	
	<!--  <div id="contriReceived" style="display: none">
	<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
		<tr>
			<td width="25%" align="left">Pension contribution received From AG</td>
			<td width="55%" align="left"><input type="radio" id="contriReceivedyes"
				name="contriReceived" value="Yes" onclick="contriDI();">Yes&nbsp; <input
				type="radio" id="contriReceivedno" name="contriReceived" value="No" onclick="contriDI();">No
			</td>
		</tr>
		<tr>
			<td width="25%" align="left">Reason</td>
			<td width="55%" align="left"><input type="text"  id="contriReceivedReason"></td>
		</tr>
	</table>
	</div>
	
	<div id="contriSanctioned" style="display: none">
	<table border="0" width="80%" align="center" cellpadding="4" cellspacing="4">
		<tr>
			<td width="25%" align="left">Leave contribution sanctioned by AG</td>
			<td width="55%" align="left"><input type="radio" id="contriSanctionedyes"
				name="contriSanctioned" value="Yes" onclick="sancDI();">Yes&nbsp; <input
				type="radio" id="contriSanctionedno" name="contriSanctioned" value="No" onclick="sancDI();">No
			</td>
		</tr>
		<tr>
			<td width="25%" align="left">Reason</td>
			<td width="55%" align="left"><input type="text"  id="contriSanctionedReason"></td>
		</tr>
	</table>
	</div>
	
	-->

		<table border="0" width="50%" align="center">
			<tr>
			
				
			<td align="left">
			<div  id="ifYes" style="visibility:hidden"><hdiits:button name="btnPocceed"  style="width:200px;" id="btnPocceed" type="button" captionid="BTN.PROV" bundle="${pensionLabels}" onclick="submitSearchDetails();" />
			</div>
			</td>					
			
				<td align="left"><hdiits:button name="btnSubmitData" id="btnSubmitData" type="button" captionid="BTN.SUBMIT" bundle="${pensionLabels}" onclick="submitSearchDetails();" /></td>					
				<td align="left"><hdiits:button name="btnClose" id="btnClose" type="button" captionid="PPROC.CLOSE"	bundle="${pensionLabels}" onclick="winCls();" /></td>
			</tr>
	</table>	
</fieldset>
</div>
</hdiits:form>

<script>
    init();
 </script>
 
 <%}catch(Exception e){
e.printStackTrace();
}%>