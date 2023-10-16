<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<script type="text/javascript">

function chkUserName()
{
	var userName = document.getElementById("txtUserName").value;
	var uri = 'ifms.htm?actionFlag=validateDdoCodeForResetPwd';
	var url = '&userName='+userName;

	if(userName.length > 0){
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function (myAjax) {
						getResponseChkUname(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
		}
}

function getResponseChkUname(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var Emp_name = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(Emp_name == "Invalid"){
		alert('Invalid User Name');
		document.getElementById("txtUserName").value = "";
		document.getElementById("txtEmpName").value = "";
		document.getElementById("btnUpdate").disabled = true;		
	}else if(Emp_name == "DCPS"){
		alert("User Name does not belong to any DDO or DDO Assistant");
		document.getElementById("txtUserName").value = "";
		document.getElementById("txtEmpName").value = "";
		document.getElementById("btnUpdate").disabled = true;
	}else{
		var userId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		document.getElementById("txtEmpName").value = Emp_name;
		document.getElementById("hidUserId").value = userId;
		document.getElementById("btnUpdate").disabled = false;
	}	
}

function reSetPassword()
{
	var userId = document.getElementById("hidUserId").value;
	var uri = 'ifms.htm?actionFlag=setPassword';
	var url = '&userId='+userId;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function (myAjax) {
					getResponsePWD(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getResponsePWD(myAjax)
{
	var userName = document.getElementById("txtUserName").value;
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var PWD = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(PWD == "Bday"){
		//var Bdate = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		//alert("Your Password has been reset to: "+Bdate);
	}else if(PWD == "default"){
		alert("Username is : "+userName+" AND Password is : ifms123");
	}

	document.getElementById("txtUserName").value = "";
	document.getElementById("txtEmpName").value = "";
	document.getElementById("btnUpdate").disabled = true;	
}
</script>

<hdiits:form name="FrmResetPassword" encType="multipart/form-data" validate="true" method="post">

<input type="hidden" id="hidUserId" name="hidUserId"/>

<fieldset class="tabstyle"><legend><fmt:message key="PPMT.EMPDTLS" bundle="${pensionLabels}"></fmt:message></legend>
	<table width="100%">
		<tr>		
			<td width="15%">
				<fmt:message key="PPMT.USERNAME" bundle="${pensionLabels}" />
			</td>
			<td width="35%">
				<input type="text" id="txtUserName"  name="txtUserName" onblur="chkUserName();"/>
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
				
	</table>
	
<center>
		<hdiits:button name="btnUpdate" id="btnUpdate" type="button" captionid="PPMT.UPDATE" bundle="${pensionLabels}" onclick="reSetPassword();"/>
</center>
</fieldset>
</hdiits:form>

<script>
	document.getElementById("btnUpdate").disabled = true;
</script>