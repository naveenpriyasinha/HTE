<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<input type="hidden"  name='txtAlert' id="txtAlert" value="${resValue.alertMessage}" />
<script language="javascript">
function init(){
	var msg = document.getElementById("txtAlert").value;
	if(msg != "" && msg!=null){
		if(msg == "InvalidSevaarthId")
			alert("Invalid SevaarthId");
		else
			alert("All Installments are paid for this Employee");
	}
}
function showHideEmpGroup(){
	var radio = document.ArrearsEntryForm.rdoSelectEmp;
	var radioValue;
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(radioValue=="A"){
		document.getElementById("trChechbox").style.display = '';
		document.getElementById("trSevaarthId").style.display = 'none';
	}else{
		document.getElementById("trChechbox").style.display = 'none';
		document.getElementById("trSevaarthId").style.display = '';
	}
}
function submitData(){
	var radio = document.ArrearsEntryForm.rdoSelectEmp;
	var empGroup = document.ArrearsEntryForm.cbEmpGroup;
	var radioValue = "";
	var url = "";
	for (i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}if(radioValue==""){
		alert('Please Select All Employee Or Single Employee');
		return false;
	}
	else if(radioValue=="A"){
		var flag=0;
		var selectedGroup="";
		for(var i=0; i < empGroup.length; i++){
			if(empGroup[i].checked == true){
				selectedGroup= selectedGroup + empGroup[i].value + "~";
				flag=1;		
			}
		}
		if(flag==0){
			alert('Please Select Employee Group');
			return false;
		}else{
			selectedGroup = selectedGroup.substring(0, selectedGroup.length-1);
			url="ifms.htm?actionFlag=popUpEmpDtlsGroup&selectedGroup="+selectedGroup;
		}
	}else{
		var sevaarthId = document.getElementById("txtSevaarthId").value;
		if(sevaarthId==""){
			alert('Please Enter Sevaarth ID');
			return false;
		}else{
			url="ifms.htm?actionFlag=popUpEmpArrearsDtls&sevaarthId="+sevaarthId;
		}
	}
	document.ArrearsEntryForm.action = url ;
	document.ArrearsEntryForm.submit();
}
</script>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<fieldset style="width:100%" class="tabstyle"><legend><fmt:message key="CMN.ARREARSMANUALENTRY" bundle="${gpfLabels}"></fmt:message></legend>
<hdiits:form name="ArrearsEntryForm" id="ArrearsEntryForm" encType="multipart/form-data" validate="true" method="post">
<table width="50%">
	<tr>
		<td width="25%"><input type="radio" id="rdoForAllEmp" name="rdoSelectEmp" value="A" onclick="showHideEmpGroup()"/>
		<fmt:message key="CMN.ALLEMPLOYEE" bundle="${gpfLabels}"></fmt:message></td>		
		<td width="25%"><input type="radio" id="rdoForSingleEmp" name="rdoSelectEmp" value="S" onclick="showHideEmpGroup()"/>
		<fmt:message key="CMN.SINGLEEMP" bundle="${gpfLabels}"></fmt:message></td>
	</tr>
	<tr id="trChechbox" style="display: none;">
		<td width="25%">
		<br>
		<input type="checkbox" name="cbEmpGroup" id="cbGroupA" value="A" onclick="">
		<fmt:message key="CMN.A" bundle="${gpfLabels}"></fmt:message>&nbsp;	&nbsp;			
		<input type="checkbox" name="cbEmpGroup" id="cbGroupB" value="B" onclick="">
		<fmt:message key="CMN.B" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="checkbox" name="cbEmpGroup" id="cbGroupBnGz" value="BnGz" onclick="">	
		<fmt:message key="CMN.BNGZ" bundle="${gpfLabels}"></fmt:message>&nbsp;	&nbsp;	
		<input type="checkbox" name="cbEmpGroup" id="cbGroupC" value="C" onclick="">		
		<fmt:message key="CMN.C" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;
		<input type="checkbox" name="cbEmpGroup" id="cbGroupD" value="D" onclick="">		
		<fmt:message key="CMN.D" bundle="${gpfLabels}"></fmt:message>
		</td>
	</tr>
	<tr id="trSevaarthId" style="display: none;">
		<td width="25%"><br></td>
		<td width="25%"><br>	
		<fmt:message key="CMN.SEVAARTHID" bundle="${gpfLabels}"></fmt:message>
		<input type="text" id="txtSevaarthId" name="txtSevaarthId" onchange=""/>
		</td>
	</tr>
	
	<tr >
		<td width="25%"><br/><hdiits:button type="button" captionid="BTN.GO" bundle="${gpfLabels}" id="btnGo" name="btnGo" onclick="submitData();"></hdiits:button></td>
		<td width="25%"></td>
	</tr>
</table>
</hdiits:form>
</fieldset>
<script language="javascript">
    window.onload = init();
</script>