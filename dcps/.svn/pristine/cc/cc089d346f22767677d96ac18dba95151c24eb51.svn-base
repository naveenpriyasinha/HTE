<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>

<script type="text/javascript">

function getDataFromSevaarth()
{
	var sevaarthId = document.getElementById("txtSevaarthId").value.trim();
	var uri = 'ifms.htm?actionFlag=getDojFromSevaarth';
	var url = '&sevaarthId='+sevaarthId;

	showProgressbar();
	if(sevaarthId.length > 0){
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:url,
			        onSuccess: function (myAjax) {
						getResponseSevaarth(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}else{
		hideProgressbar();
	}
}

function getResponseSevaarth(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var DOJ = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(DOJ == "Invalid"){
		alert('Invalid Sevarth Id');
		document.getElementById("txtSevaarthId").value = "";
		document.getElementById("radioDCPS").checked = false;
		document.getElementById("radioGPF").checked = false;
		document.getElementById("txtDOJ").value = "";
	}else{
		document.getElementById("txtDOJ").value = DOJ.substring(0,10);
		checkDOJ_OLD();
	}
	hideProgressbar();
}

function checkDOJ_OLD()
{
	var DOJ = document.getElementById("txtDOJ").value;
	var year = DOJ.substring(6,10);
	var mon = DOJ.substring(3,5);
	var day = DOJ.substring(0,2);

	mon = mon -1;
	var dtDOJ = new Date();
	dtDOJ.setFullYear(year,mon,day);
	var dtComp = new Date();
	dtComp.setFullYear(2005,10,01);

	if(dtDOJ >= dtComp){
		document.getElementById("radioDCPS").checked = true;
	}else{
		document.getElementById("radioGPF").checked = true;
	}
}

function checkDOJ()
{
	var DOJ = document.getElementById("txtDOJNew").value;
	var dpValue ="";
	
	if(DOJ.length > 0){
		var year = DOJ.substring(6,10);
		var mon = DOJ.substring(3,5);
		var day = DOJ.substring(0,2);
	
		mon = mon -1;
		var dtDOJ = new Date();
		dtDOJ.setFullYear(year,mon,day);
		var dtComp = new Date();
		dtComp.setFullYear(2005,10,01);
	
		if(dtDOJ >= dtComp){
			dpValue = 'DCPS';
		}else{
			dpValue = 'GPF';
		}
	}

	return dpValue;
}

function validateData()
{
	if(document.getElementById("txtDOJ").value.trim() == ""){
		alert('Date Of Joining Can not be blank');
		document.getElementById("txtDOJ").focus();
	}else if(document.getElementById("txtDOJNew").value.trim() == ""){
		alert('Please Enter Date Of Joining');
		document.getElementById("txtDOJNew").focus();
	}else{
		updateData();
	}
}

function updateData()
{
	/* var dpValue = checkDOJ(); */
	/*Added By Naveen dated 3- Aug-2022 as per client requirement*/
	var dpValue='';
	if(document.getElementById("radioDCPS").checked){
		 dpValue = document.getElementById("radioDCPS").value.trim();
		
		} else 
			{
			dpValue = document.getElementById("radioGPF").value.trim();
			//alert("hi"+dpValue);
			}
	 
	/* Ended Here  chnages 3-Aug-2022 as per client Requiremment DCPS to GPF*/
	var sevaarthId = document.getElementById("txtSevaarthId").value.trim();
	var dojNew = document.getElementById("txtDOJNew").value.trim();
	var uri = 'ifms.htm?actionFlag=updateGPForDCPS';
	var url = '&sevaarthId='+sevaarthId+'&dpValue='+dpValue+'&dojNew='+dojNew;

	showProgressbar();
	if(dpValue.length > 0){
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:url,
			        onSuccess: function (myAjax) {
						getResponseUpdate(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}else{
		hideProgressbar();
	}
}

function getResponseUpdate(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var resData = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(resData == "true"){
		alert('Updated Successfully');
		self.location.reload(true);
	}	
	hideProgressbar();
}

</script>

<hdiits:form name="FrmSetDCPSorGPF" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend><fmt:message key="PPMT.EMPDTLS" bundle="${pensionLabels}"></fmt:message></legend>
	<table width="100%">
		<tr>		
			<td width="25%">
				<fmt:message key="CMN.SEVARTHID" bundle="${dcpsLables}" />
			</td>
			<td width="25%">
				<input type="text" id="txtSevaarthId"  name="txtSevaarthId" onblur="getDataFromSevaarth();"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">
			</td>
		</tr>
	
		<tr>		
			<td width="25%">
				<fmt:message key="PPMT.DOJ" bundle="${pensionLabels}" />
			</td>
			<td width="25%">
				<input type="text" id="txtDOJ"  name="txtDOJ" readonly="readonly"/>
				<label class="mandatoryindicator">*</label><font color="red">(DD/MM/YYYY)</font>
			</td>
			<td width="50%">
			</td>
		</tr>
		
		<tr>		
			<td width="25%">
				<fmt:message key="CMN.DOJNEW" bundle="${dcpsLables}" />
			</td>
			<td width="25%">				
				<input type="text" name="txtDOJNew" id="txtDOJNew" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtDOJNew",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">
			</td>
		</tr>
		
		<tr>		
			<td width="25%">
				<fmt:message key="CMN.DCPSORGPF" bundle="${dcpsLables}" />
			</td>
			<td width="25%">
				<input type="radio" id="radioDCPS" name="radioDCPSGPF"  value="D"/> :DCPS
				<input type="radio" id="radioGPF" name="radioDCPSGPF" value="G"/> :GPF			
			</td>
			<td width="50%">
			</td>
		</tr>
		
		<tr>
			<td><br></td>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%">
			</td>
			<td width="25%">
				<hdiits:button name="btnUpdate" id="btnUpdate" type="button" captionid="BTN.UPDATE" bundle="${dcpsLables}" onclick="validateData();"/>
				<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>				
			</td>
			<td width="50%">
			</td>
		</tr>
	</table>
</fieldset>

</hdiits:form>	