<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request"/>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>

<script type="text/javascript">

function validateDdoCode()
{
	showProgressbar();
	var ddoCode = document.getElementById("txtDdoCode").value;

	if(ddoCode.length == 0){
		hideProgressbar();
		return;		
	}	

	var url = "ifms.htm?actionFlag=validateDdoCodeForOutsideSevaarth&ddoCode="+ddoCode;
	document.FrmDdoOutSide.action = url;
	document.FrmDdoOutSide.submit();
	hideProgressbar();
}

function MinUserNameLength(obj){
	if(obj.value.length == 0){
		document.getElementById("btnUpdate").disabled = true;
		return;
	}
	
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
	var uName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(uName == "Exist"){		
		alert('User Name Already exist');
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
		document.getElementById("btnUpdate").disabled = true;
	}else if(uName == "Y"){
		alert('User Name Already exist');
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
		document.getElementById("btnUpdate").disabled = true;
	}else if(uName == "E"){
		alert('User Name Already Exist');
		document.getElementById("txtNewUserName").value="";
		document.getElementById("txtNewUserName").focus();
		document.getElementById("btnUpdate").disabled = true;
	}else{
		document.getElementById("btnUpdate").disabled = false;
	}

	hideProgressbar();
}

function validateData()
{
	if(document.getElementById("txtNewUserName").value.trim() == ""){
		alert('Please Enter New User Name');
		document.getElementById("txtNewUserName").focus();
	}else if(document.getElementById("txtEmpName").value.trim() == ""){
		alert('Please Enter Employee Name');
		document.getElementById("txtEmpName").focus();
	}else if(document.getElementById("txtEmpDOB").value.trim() == ""){
		alert('Please Select Employee Date Of Birth');
		document.getElementById("txtEmpDOB").focus();
	}else if(document.getElementById("txtEmpDOJ").value.trim() == ""){
		alert('Please Select Employee Date Of Joining');
		document.getElementById("txtEmpDOJ").focus();
	}else if(document.getElementById("txtEmpServiceExpr").value.trim() == ""){
		alert('Please Select employee service expire date');
		document.getElementById("txtEmpServiceExpr").focus();
	}else{		
		updateUname();		
	}
}

function updateUname()
{
	showProgressbar();
	var oldUsrName = document.getElementById("txtOldUsrName").value.trim();
	var newUname = document.getElementById("txtNewUserName").value.trim();
	var ddoCode = document.getElementById("txtDdoCode").value.trim();
	var emp_name = document.getElementById("txtEmpName").value.trim();
	var empDOB = document.getElementById("txtEmpDOB").value.trim();
	var empDOJ = document.getElementById("txtEmpDOJ").value.trim();
	var serviceExpr = document.getElementById("txtEmpServiceExpr").value.trim();

	if(newUname.length > 0 && oldUsrName.length > 0){
		var uri = 'ifms.htm?actionFlag=updateForDdoOutsideSevaarth';
		var url = '&oldUname='+oldUsrName+'&newUname='+newUname+'&ddoCode='+ddoCode+'&empName='+emp_name
				  +'&DOB='+empDOB+'&DOJ='+empDOJ+'&serviceExpr='+serviceExpr;

		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
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

function getResponseUpdate(myAjax){

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var ResData = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	hideProgressbar();
	
	if(ResData == "Success"){
		alert('DDO Details Saved Successfully');
		self.location.reload(true);
	}
}
</script>


<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />

<hdiits:form name="FrmDdoOutSide" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend><fmt:message key="CMN.DDODTLS" bundle="${dcpsLables}"></fmt:message></legend>

<input type="hidden" id="hidPostId" name="hidPostId" value="${resValue.postId}" /> 
	
	<table width="100%">
		<tr>		
			<td width="15%">
				<fmt:message key="CMN.DDOCODE" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtDdoCode"  name="txtDdoCode" onBlur="validateDdoCode();" value="${resValue.ddoCode}"/>
				<label class="mandatoryindicator" >*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		
		<tr>
			<td width="15%">
				<fmt:message key="CMN.DDONAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtDdoName"  name="txtDdoName" readonly="readonly" style="width: 100%" value="${resValue.ddoName}"/>				
			</td>
			<td width="50%">						
			</td>
		</tr>
		
		<tr>
			<td width="15%">
				<fmt:message key="CMN.DDOPNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtDdoPersnlName"  name="txtDdoPersnlName" readonly="readonly" style="width: 80%" value="${resValue.ddoPName}"/>				
			</td>
			<td width="50%">						
			</td>
		</tr>
		
		<tr>
			<td width="15%">
				<fmt:message key="CMN.OLDUSRNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtOldUsrName"  name="txtOldUsrName" readonly="readonly" value="${resValue.uName}"/>				
			</td>
			<td width="50%">						
			</td>
		</tr>
		
		<tr>
			<td width="15%">
				<fmt:message key="CMN.NEWUSRNAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtNewUserName"  name="txtNewUserName" onblur="MinUserNameLength(this);"/>
				<label class="mandatoryindicator" >*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="CMN.EMPLOYEENAME" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" id="txtEmpName"  name="txtEmpName" style="width: 60%"/>
				<label class="mandatoryindicator" >*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="CMN.DOB" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" name="txtEmpDOB" id="txtEmpDOB" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEmpDOB",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="CMN.DOJ" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" name="txtEmpDOJ" id="txtEmpDOJ" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEmpDOJ",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
			<td width="15%">
				<fmt:message key="CMN.SERVICEEXPIRE" bundle="${dcpsLables}" />
			</td>
			<td width="35%">
				<input type="text" name="txtEmpServiceExpr" id="txtEmpServiceExpr" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" />
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEmpServiceExpr",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>
			</td>
			<td width="50%">						
			</td>
		</tr>
		<tr>
		</tr>
		
		<tr>
			<td width="15%">
			</td>
			<td width="35%">
				<hdiits:button name="btnUpdate" id="btnUpdate" type="button" captionid="BTN.UPDATE" bundle="${dcpsLables}" onclick="validateData();"/>
				<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';"/>
			</td>
		</tr>
	</table>

</fieldset>
</hdiits:form>

<script>
	document.getElementById("btnUpdate").disabled = true;
</script>