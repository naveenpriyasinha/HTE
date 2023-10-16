<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>


<script type="text/javascript">

function dataForUserName()
{
	var userName = document.getElementById("txtUserName").value.trim();
	document.getElementById("btnUpdate").disabled = true;
	
	if(userName.length > 0)
	{
		var url = 'ifms.htm?actionFlag=getDataFromUserName';
		var uri = '&userName='+userName;
		
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function (myAjax) {
						getResponseUname(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function getResponseUname(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var empName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(empName == "Invalid"){
		alert('Username Does not Exist');
		document.getElementById("txtUserName").value = "";
		document.getElementById("txtUserName").focus();
	}else{
		var location = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		document.getElementById("txtEmpName").value = empName;
		document.getElementById("txtLocation").value = location;
	}
}

function chkNewUserName()
{
	var userName = document.getElementById("txtNewUserName").value.trim();

	if(userName.length > 0)
	{
		var url = 'ifms.htm?actionFlag=chkNewUserName';
		var uri = '&userName='+userName;
		
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function (myAjax) {
						getResponseNewUname(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function getResponseNewUname(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var response = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(response == "Y"){
		alert('Username Already Exist');
		document.getElementById("txtNewUserName").value = "";
		document.getElementById("txtNewUserName").focus();
		document.getElementById("btnUpdate").disabled = true;
	}else if(response == "N"){
		document.getElementById("btnUpdate").disabled = false;
	}else{
	}
}

function updateUserName()
{
	var oldUserName = document.getElementById("txtUserName").value.trim();
	var newUserName = document.getElementById("txtNewUserName").value.trim();

	if(oldUserName.length > 0 && newUserName.length > 0)
	{
		var url = 'ifms.htm?actionFlag=updateUserName';
		var uri = '&oldUserName='+oldUserName+'&newUserName='+newUserName;
		
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function (myAjax) {
						getResponseUpdateUname(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function getResponseUpdateUname(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var resData = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(resData == "Y"){
		alert('Username Updated Successfully');
		self.location.reload(true);
	}
}
</script>

<hdiits:form id="FrmUpdateUserName" name="FrmUpdateUserName" encType="multipart/form-data" validate="true" method="post">
	<fieldset class="tabstyle"><legend><fmt:message key="PPMT.USERDTLS" bundle="${pensionLabels}"></fmt:message></legend>
	<table width="100%">
		<tr>		
			<td width="15%">
				<fmt:message key="PPMT.USERNAME" bundle="${pensionLabels}" />
			</td>
			<td width="35%">
				<input type="text" id="txtUserName"  name="txtUserName" onblur="dataForUserName();"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="PPMT.NAME" bundle="${pensionLabels}" />
			</td>
			<td width="35%">
				<input type="text" id="txtEmpName"  name="txtEmpName" readonly="readonly" style="width: 70%" />				
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="PPMT.LOCATION" bundle="${pensionLabels}" />
			</td>
			<td width="35%">
				<input type="text" id="txtLocation"  name="txtLocation" readonly="readonly" style="width: 85%" />				
			</td>
			<td width="50%">
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="CMN.NEWUSRNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtNewUserName"  name="txtNewUserName" onblur="chkNewUserName();"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>		
	</table>
	<center>
		<hdiits:button name="btnUpdate" id="btnUpdate" type="button" captionid="PPMT.UPDATE" bundle="${pensionLabels}" onclick="updateUserName();"/>
	</center>
	</fieldset>
</hdiits:form>


<script>
	document.getElementById("btnUpdate").disabled = true;
</script>