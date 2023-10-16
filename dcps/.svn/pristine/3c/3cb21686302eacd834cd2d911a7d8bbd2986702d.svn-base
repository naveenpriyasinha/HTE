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

function reSetPassword()
{
	if(document.getElementById("cmbUserName").value != 'UserName'){
		var userId = document.getElementById("cmbUserName").value;
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
	}else{
		alert('Please Select user Name');
		document.getElementById("cmbUserName").focus();
	}
}

function getResponsePWD(myAjax)
{	
	var userName = document.getElementById("cmbUserName").options[document.getElementById("cmbUserName").options.selectedIndex].text;
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var PWD = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(PWD == "Bday"){
		//var Bdate = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		//alert("Your Password has been reset to: "+Bdate);
	}else if(PWD == "default"){
		alert("Username is : "+userName+" AND Password is : ifms123");
	}

	document.getElementById("cmbUserName").value = "UserName";
}
</script>

<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/> 

<hdiits:form name="FrmResetPassword" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend><fmt:message key="PPMT.EMPDTLS" bundle="${pensionLabels}"></fmt:message></legend>
	<table width="100%">
		<tr>		
			<td width="15%">
				<fmt:message key="PPMT.USERNAME" bundle="${pensionLabels}" />
			</td>
			<td width="35%">
				<select id="cmbUserName" name="cmbUserName" tabindex="10">
					<option value="UserName" selected="selected">-----Select User Name-----</option>
						<c:forEach items="${resValue.ToAnsAsstList}" var="UName">
							<option value="${UName.id}" >${UName.desc}</option>
						</c:forEach>
				</select>
				<label class="mandatoryindicator">*</label>
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