<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<fmt:setBundle basename="resources.pensionpay.PensionLabels"
	var="PensionLabels" scope="request" />
	<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>






<script>

function reset(){
	document.getElementById("sevarthId").value="";
	document.getElementById("fname").value="";
	document.getElementById("dor").value="";
	document.getElementById("currDdo").value="";
	document.getElementById("currDdoName").value="";
	document.getElementById("newDdo").value="";
	document.getElementById("newDdoName").value="";
	document.getElementById("remark").value="";
	
}

function updateDdoCode(){

	//alert("inside update");
	
	var sevarthId= document.getElementById("sevarthId").value;	
	var newDdo= document.getElementById("newDdo").value;
	var remark= document.getElementById("remark").value;
	var currDdo= document.getElementById("currDdo").value;
	if(document.getElementById("newDdo").value==""){
		alert("Please enter new DDO Code");
	}
	if (document.getElementById("remark").value==""){
		alert("Please enter Reamark");
	}
	else{
	uri = "ifms.htm?actionFlag=changeDdoCode&sevarthId="+sevarthId+"&newDdo="+newDdo+"&remark="+remark+"&currDdo="+currDdo;
	myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {status=updateDdoCodeAjax(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});

 
	}
return status;


	}


function updateDdoCodeAjax(myAjax){

	//alert("inside update ajax");
var XMLDoc = myAjax.responseXML.documentElement;
	
	if(XMLDoc != null)
	{

		var XmlHiddenValues = XMLDoc.getElementsByTagName('STATUS');
		var lStrMessage = XmlHiddenValues[0].childNodes[0].nodeValue;
		
		//alert("status++++"+lStrMessage);
	if(lStrMessage=='true'){
		alert("DDO Code is updated successfully");
		reset();
}
	}
}


function populateDetails(){

	//alert("inside populate");


		var sevarthId=document.getElementById("sevarthId").value;	
		uri = "ifms.htm?actionFlag=populateDetails&sevarthId="+sevarthId;
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {status=populateDetailsAjax(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});

	 
	
 return status;
}


function populateDetailsAjax(myAjax)
		{	
		
	var XMLDoc = myAjax.responseXML.documentElement;
	
	if(XMLDoc != null)
	{

		var XmlHiddenValues5 = XMLDoc.getElementsByTagName('FLAG');
		var flag = XmlHiddenValues5[0].childNodes[0].nodeValue;
		 if(flag=='details'){
				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('STATUS');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].nodeValue;
		
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('FNAME');
		var fname = XmlHiddenValues1[0].childNodes[0].nodeValue;

		var XmlHiddenValues2 = XMLDoc.getElementsByTagName('DOR');
		var dor = XmlHiddenValues2[0].childNodes[0].nodeValue;
		
		var XmlHiddenValues3 = XMLDoc.getElementsByTagName('CURRDDO');
		var currDdo = XmlHiddenValues3[0].childNodes[0].nodeValue;

		var XmlHiddenValues4 = XMLDoc.getElementsByTagName('CURRDDONAME');
		var currDdoName = XmlHiddenValues4[0].childNodes[0].nodeValue;

		
		if(lSaveStatus=="true")
		{

			document.getElementById("fname").value=fname;
			document.getElementById("dor").value=dor;
			document.getElementById("currDdo").value=currDdo;
			document.getElementById("currDdoName").value=currDdoName;


		}
		//alert("flag*****"+flag);
		 }

	
		 else
		 {
			 alert("Pension case has to be in 'Draft' or 'Forward to DDO' status");
			 }
	}
		}

function populateNewDdoOfc(){

	//alert("inside populate ddo offc");
	
	var newDdo = document.getElementById("newDdo").value;	
	
	uri = "ifms.htm?actionFlag=populateDdoOfc&newDdo="+newDdo;
	myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {status=populateNewDdoOfcAjax(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
	return status;
	}





function populateNewDdoOfcAjax(myAjax)
		{ 
//alert("inside ajax");
	var XMLDoc = myAjax.responseXML.documentElement;
	
	if(XMLDoc != null)
	{
		var XmlHiddenValues = XMLDoc.getElementsByTagName('DDOOFFC');
		var ddoOffc = XmlHiddenValues[0].childNodes[0].nodeValue;

		document.getElementById("newDdoName").value=ddoOffc;
	}


		}

</script>



<fieldset class="tabstyle"><legend> <b>Change DDO Code</b> </legend>
	<hdiits:form name="ChangeDdoCode" validate="true" method="post" action=" ">
	
	<table width="50%" border="0" cellspacing="1"
		cellpadding="1">
		
		<tr align="left">
		<td ><fmt:message key="PPMT.SEVARTHID" 
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="sevarthId" id="sevarthId" size="40" 
				style="text-transform: uppercase" onblur="populateDetails();"  />
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
			</tr>
			
		<tr align="left">
		<td><fmt:message key="PPMT.NAME"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="fname" id="fname"
				style="text-transform: uppercase"  readonly="readonly"  size="40"/>	
				</td>
				</tr>
				
				
				<tr align="left">
				<td ><fmt:message key="PPMT.DOR"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="dor" id="dor"
				style="text-transform: uppercase"  readonly="readonly"  size="40"/>	
				</td>
				</tr>
				
				<tr align="left">
					<td><fmt:message key="PPMT.CURRDDO"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="currDdo" id="currDdo" maxlength="10"
				style="text-transform: uppercase"  readonly="readonly"  size="40"/>	
				</td>
				</tr>
				
				<tr align="left">
						<td ><fmt:message key="PPMT.CURRDDONAME"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="currDdoName" id="currDdoName"
				style="text-transform: uppercase"  readonly="readonly"  size="40"/>	
				</td>
				</tr>
				
				<tr align="left">
				<td  ><fmt:message key="PPMT.NEWDDO"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="newDdo" id="newDdo" size="40" maxlength="10"
				style="text-transform: uppercase" onblur="populateNewDdoOfc();" />
			<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
			</td>
			</tr>
			
			<tr align="left">
			<td ><fmt:message key="PPMT.NEWDDONAME"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="newDdoName" id="newDdoName"
				style="text-transform: uppercase"  readonly="readonly"  size="40"/>	
				</td>
				</tr>
				
				<tr align="left">
					<td ><fmt:message key="PPMT.REMARK"
				bundle="${PensionLabels}"></fmt:message></td>
		<td align="left"><input type="text" name="remark" id="remark"
				style="text-transform: uppercase"  size="40" maxlength="500"/>	
				<label class="mandatoryindicator" ${varImageDisabled}${varImageDisabledHO}>*</label>
				</td>
				</tr>
				<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			
				<td align="left">
				<hdiits:button name="btnUpdate" id="btnUpdate" type="button"  captionid="PPMT.UPDATE" bundle="${PensionLabels}" onclick="updateDdoCode();"  />
				<hdiits:button name="btnReset" id="btnReset" type="button"  captionid="PPMT.RESET" bundle="${PensionLabels}"  onclick="reset();" />
					</td>
					</tr>
		</table>
		</hdiits:form>
		</fieldset>
		
		