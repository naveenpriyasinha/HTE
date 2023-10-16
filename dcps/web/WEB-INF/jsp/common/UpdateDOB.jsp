<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script>
function getEmpInfo()
{
	//alert("getEmpInfo");
	var SevaarthId = document.getElementById("txtSevaarthId").value;
	//alert("SevaarthId"+SevaarthId);
	if(SevaarthId.trim() == "")
	{
		alert("Sevaarth Id cannot be Empty.");
		document.getElementById("txtSevaarthId").focus();		
		return false;
	}		
	else
	{
		var url = "ifms.htm?actionFlag=checkEmpSevaarthId&SevaarthId="+SevaarthId+"&elementId=700218";
		document.frmEmpInfo.action = url ;
		document.frmEmpInfo.submit();
	}
		
}
function populateDOR() {
	//alert("populateDOR");
	var txtCadre = document.getElementById('txtCadre').value;
	//alert(txtCadre);
	var dobEmp = document.getElementById('txtNewDOB').value;
	//alert(dobEmp);
	if(txtCadre != "" && dobEmp !="")
		{		
			var uri = "ifms.htm?actionFlag=popGroup";
			var url = "cmbCadre=" + txtCadre + "&dobEmp=" + dobEmp ;
			
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
						getDataStateChangedForPopGroup(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
		}
}

function getDataStateChangedForPopGroup(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');	
	var retiringDate =  XMLEntries[0].childNodes[2].firstChild.nodeValue;
	if(retiringDate != 'null'){
		document.getElementById("txtNewDOR").value = retiringDate;
	}
}
function updateDOBAndDOR(){
	//alert("updateDOBAndDOR");
	var SevaarthId = document.getElementById("txtSevaarthId").value;
	//alert(SevaarthId);
	var txtNewDOB = document.getElementById('txtNewDOB').value;
	//alert(txtNewDOB);
	var txtNewDOR = document.getElementById('txtNewDOR').value;
	//alert(txtNewDOR);
	var txtEmpId = document.getElementById('txtEmpId').value;
	//alert(txtEmpId);
	if(txtNewDOB == ""){
		alert('New Date of Birth cannot be empty');
		document.getElementById('txtNewDOB').focus();
		return false;
	}
	if(txtNewDOR == ""){
		alert('New Date of Retirement cannot be empty');
		document.getElementById('txtNewDOR').focus();
		return false;
	}

	var uri = "ifms.htm?actionFlag=updateDOBandDOR";
	//alert(uri);
	var url = "SevaarthId=" +SevaarthId+"&txtEmpId="+txtEmpId+ "&txtNewDOB=" + txtNewDOB + "&txtNewDOR=" + txtNewDOR ;
	//alert(url);
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
						getUpdateSuccess(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
	
}
function getUpdateSuccess(myAjax){
	//alert("getUpdateSuccess");
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');	
	var flag =  XMLEntries[0].childNodes[0].firstChild.nodeValue;

	//alert(flag);
	if(flag == 'true'){
		alert('Date of Birth and Date of Retirement Updated successfully');
		self.location.href="ifms.htm?viewName=UpdateDOB&elementId=700218";
	}else{
		alert('Record not Updated');
	}

	
}
</script>
<hdiits:form name="frmEmpInfo" action="" id="frmEmpInfo" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
<table width="30%" align="Left" >
		<tr>
			<td width="10%">HTESevarth Id</td>
			<td width="20%"><input type="text" maxlength="13" id="txtSevaarthId" name="txtSevaarthId" value="${resValue.SevaarthId}"/>	</td>			
		</tr>	
		<tr>
			<td width="10%"><br></td>
			<td width="20%"><br></td>			
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="20%"><hdiits:button name="btnSubmit" id="btnSubmit" type="button" caption="Search" onclick="getEmpInfo()" /></td>			
		</tr>	
</table>
</fieldset>
<br><br>
<c:if test="${resValue.Cadre != null}">
<fieldset class="tabstyle" ><legend>Employee Information</legend>
	<table width="50%" align="left" >
		<tr>
			<td>DDO Code</td>
			<td>
			<input type="text" id="txtDdoCode" name="txtDdoCode" readonly="readonly" value="${resValue.Ddocode}"/>	
			<input type="hidden" id="txtCadre" name="txtCadre"  value="${resValue.Cadre}"/>
			<input type="hidden" id="txtEmpId" name="txtEmpId"  value="${resValue.EmpId}"/>
			</td>			
		</tr>
		<tr>
			<td>DDO Name</td>
			<td><input type="text" id="txtDdoName" name="txtDdoName" size="70" readonly="readonly" value="${resValue.DdoName}"/>	</td>			
		</tr>
		<tr>
			<td>DDO Office</td>
			<td><input type="text" id="txtDdoOffice" name="txtDdoOffice" size="70" readonly="readonly" value="${resValue.DdoOffice}"/>	</td>			
		</tr>
		<tr>
			<td>Date of Birth</td>
			<td>
				<fmt:formatDate value="${resValue.DOB}" pattern="dd/MM/yyyy" var="DOB"/>
				<input type="text" id="txtDOB" name="txtDOB" readonly="readonly" value="${DOB}"/>
			</td>			
		</tr>
		<tr>
			<td>Date of Retirement</td>
			<td>
			<fmt:formatDate value="${resValue.DOR}" pattern="dd/MM/yyyy" var="DOR"/>
			<input type="text" id="txtDOR" name="txtDOR" readonly="readonly" value="${DOR}"/>	
			</td>			
		</tr>
		<tr>
			<td>New Date of Birth</td>
			<td>			
			<input type="text" name="txtNewDOB" id="txtNewDOB" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);populateDOR();" value = "" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtNewDOB",375,570)'/>	
			</td>			
		</tr>
		<tr>
			<td>New Date of Retirement</td>
			<td>			
			<input type="text" name="txtNewDOR" id="txtNewDOR" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" value = "" />
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtNewDOR",375,570)'/>	
			</td>			
		</tr>
		<tr>
			<td></td>
			<td><hdiits:button name="btnUpdate" id="btnUpdate" type="button" caption="Update" onclick="updateDOBAndDOR()" /></td>			
		</tr>
</table>
</fieldset>
</c:if>
<c:if test="${resValue.invalid == 'invalid'}">
<fieldset class="tabstyle" >
<br><br>
 <center> <font size="2"> <b>Invalid Sevaarth Id </b> </font></center>
</fieldset>
</c:if>


</hdiits:form> 