<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>

<script type="text/javascript">

function validateDdoCode()
{
	var ddoCode = document.getElementById("txtDdoCode").value;
	//alert("In validateDdoCode");
	if(ddoCode.length == 0){
		return;
	}	

	showProgressbar();
	var url = "ifms.htm?actionFlag=validateDdoCode&ddoCode="+ddoCode;
	document.FrmChngUname.action = url ;
	document.FrmChngUname.submit();
	hideProgressbar();
}

function MinUserNameLength(obj){
	if(obj.value.length == 0){
		return;
	}

	//alert("In MinUserNameLength");
	//alert("In MinUserNameLength"+obj.value.length);
	if (obj.value.length < 10 || obj.value.length > 15){ 
		alert('Invalid length. Length should be between 10 and 15'); 
		obj.value ="";
		obj.focus();
	}else{
		var postId = document.getElementById("hidPostId").value;
		var reqType = document.getElementsByName("rdoType");
		
		showProgressbar();
		
		var uri = 'ifms.htm?actionFlag=checkNewUname';
		var url = '&newUname='+obj.value+'&postId='+postId;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
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
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var uName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(uName == "Exist"){
		var empName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		alert('User Name exist for '+empName);
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
	}else if(uName == "Y"){
		var ddoCode = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		if(ddoCode == "Child"){
			alert("New DDO does not have parent post");
			document.getElementById("txtNewUserName").value="";
			document.getElementById("txtNewUserName").focus();
		}else{
			var ddoName = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
			var msg = 'This User Name(Sevaarth Id) exist for DDO Code:- '+ddoCode+', DDO Name:- '+ddoName+'.Do you want to add multiple charge to this Sevaarth Id to avail switch post Functionality?';
			if(confirm(msg)){
				var primaryDdo = document.getElementById("txtDdoCode").value;
				setPrimaryPost(primaryDdo,ddoCode);
			}else{
				document.getElementById("txtNewUserName").value="";
				document.getElementById("txtNewUserName").focus();
				hideProgressbar();
			}
		}
	}else if(uName == "E"){
		alert('User Name Already Exist');
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
	}else{
		document.getElementById("btnUpdate").disabled = false;
	}
	hideProgressbar();
}

function setPrimaryPost(primDdo,ScndDdo)
{
	var uri = 'ifms.htm?actionFlag=setPrimaryPost';
	var url = '&primaryDdo='+primDdo+'&secondaryDdo='+ScndDdo;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function (myAjax) {
					getResponseSetPrimary(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getResponseSetPrimary(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var ResData = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(ResData == "Y"){
		alert('Secondary post Added to the user');		
		self.location.href='ifms.htm?viewName=changeUserName&elementId=700207';
	}
	hideProgressbar();
}

function updateUname()
{
	alert('In function');
	var oldUsrName = document.getElementById("txtOldUsrName").value.trim();
	var newUname = document.getElementById("txtNewUserName").value.trim();
	var ddoCode = document.getElementById("txtDdoCode").value.trim();

	alert('checking value'+oldUsrName+''+newUname+''+ddoCode);
	if(newUname.length > 0 && oldUsrName.length > 0){

		showProgressbar();
		
		var uri = 'ifms.htm?actionFlag=updateUname';
		var url = '&oldUname='+oldUsrName+'&newUname='+newUname+'&ddoCode='+ddoCode;

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
		if(newUname.length == 0){
			alert('New User Name can not be empty');
			document.getElementById("txtNewUserName").focus();
		}else if(oldUsrName.length == 0){
			alert('Old User Name can not be empty');
			document.getElementById("txtOldUsrName").focus();
		}
	}
}

function getResponseUpdate(myAjax)
{
	hideProgressbar();
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var ResData = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if(ResData == "Success"){
		var updtUserName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		alert('User Name Updated Successfully. \nNew Username is :'+updtUserName);		
		self.location.href = "ifms.htm?viewName=changeUserName&elementId=700207";
	}else if(ResData == "inactive"){
		alert('New User Name is not an active user');
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
		document.getElementById("btnUpdate").disabled = true;
	}
}
</script>


<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<hdiits:form name="FrmChngUname" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend><fmt:message key="CMN.DDODTLS" bundle="${dcpsLables}"></fmt:message></legend>

<input type="hidden" id="hidPostId" name="hidPostId" value="${resValue.postId}" /> 
	
	<table width="100%" border='0'>
		<tr>		
			<td >
				<fmt:message key="CMN.DDOCODE" bundle="${dcpsLables}" />
			</td>
			<td width="85%" style="padding-left: -50px;">
				<input type="text" id="txtDdoCode"  name="txtDdoCode" onBlur="validateDdoCode();" value="${resValue.ddoCode}"/>
				<label class="mandatoryindicator" ${varImageDisabled}>*</label>
			</td>
								
			
		</tr>
		
		<tr>
			<td>
				<fmt:message key="CMN.DDONAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%" style="padding-right: 5px;">
				<input type="text" id="txtDdoName"  name="txtDdoName" readonly="readonly" style="width: 100%" value="${resValue.ddoName}"/>				
			</td>
			
		</tr>
		
		<tr>
			<td>
				<fmt:message key="CMN.NEWUSRNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%" >
				<input type="text" id="txtNewUserName"  name="txtNewUserName" onblur="MinUserNameLength(this);" value="${resValue.ddoCode}"/>
			</td>
			
		</tr>
	
		
		<tr>
			<td style="display: none;">
				<fmt:message key="CMN.DDOPNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%" style="display: none;">
				<input type="text" id="txtDdoPersnlName"  name="txtDdoPersnlName" readonly="readonly" style="width: 80%" value="${resValue.ddoPName}"/>				
			</td>
			
		</tr>
		
		<tr>
			<td  style="display: none;">
				<fmt:message key="CMN.OLDUSRNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%" style="display: none;">
				<input type="text" id="txtOldUsrName"  name="txtOldUsrName" readonly="readonly" value="${resValue.uName}"/>				
			</td>
			
		</tr>
		
		
		<tr>
		<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td >
			</td>
			<td width="35%" colspan="1" style="align:center;">
				<hdiits:button name="btnUpdate" id="btnUpdate" type="button" captionid="BTN.UPDATE" bundle="${dcpsLables}" onclick="updateUname();"/>
				<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>				
			</td>
		</tr>
	</table>

</fieldset>
</hdiits:form>

<script>
	document.getElementById("btnUpdate").disabled = true;
</script>