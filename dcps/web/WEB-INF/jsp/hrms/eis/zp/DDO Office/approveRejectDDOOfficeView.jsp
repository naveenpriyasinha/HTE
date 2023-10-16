<html>
<head>
<%
try{
%>

<%@ include file="../../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>



<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="ddoInfoList" value="${resultValue.ddoInfoList}" > </c:set>
<c:set var="hierarchy" value="${resultValue.hierarchy}" > </c:set>
<c:set var="officeName" value="${resultValue.officeName}" > </c:set>
<c:set var="headMasterName" value="${resultValue.headMasterName}" > </c:set>
 
<c:set var="ddocode" value="${resultValue.ddocode}" > </c:set>
<c:set var="ddoMobileNo" value="${resultValue.ddoMobileNo}" > </c:set>

<script type="text/javascript">
function approveDtls(field){
	var ddoCode=document.getElementById("selDdocode").value;
	var hierarchy=document.getElementById("hierarchy").value;
	var ddoMobileNo=document.getElementById("hdnDdoMobileNo").value;
	var headMstrName=document.getElementById("hdnHeadMstrName").value;
	var officeName=document.getElementById("hdnOfficeName").value;
	var divId=field.toString();
	
	var url="./hrms.htm?actionFlag=updateApproveStatus&ddocode="+ddoCode+"&hierarchy="+hierarchy+"&ddoMobileNo="+ddoMobileNo+"&headMstrName="+headMstrName+"&officeName="+officeName;
	document.frmApproveReject.action = url;
	document.frmApproveReject.submit();
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
	alert('System data is getting approved.');
}

function rejectDtls(ddocode){
	document.getElementById("reasonForRejectionRow").style.display='';
	
	
	 
	 var reasonForRejection=document.getElementById("reasonForRejection").value;
	 
	 if(!reasonForRejection.match(/\S/))
		{
			alert('Please fill reason for rejection before rejection.');
			return false;
		}
		var hierarchy=document.getElementById("hierarchy").value;
		var ddoCode=document.getElementById("selDdocode").value;
		var ddoMobileNo=document.getElementById("hdnDdoMobileNo").value;
		var headMstrName=document.getElementById("hdnHeadMstrName").value;
		var officeName=document.getElementById("hdnOfficeName").value;
		
	var url="./hrms.htm?actionFlag=updateRejectStatus&ddocode="+ddoCode+"&hierarchy="+hierarchy+"&reasonForRejection="+reasonForRejection+"&ddoMobileNo="+ddoMobileNo+"&headMstrName="+headMstrName+"&officeName="+officeName;
	document.frmApproveReject.action = url;
	document.frmApproveReject.submit();
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
	alert('System data is getting rejected.');

}

function backToSysApprove()
{
	self.location.href="ifms.htm?actionFlag=approveDdoOfficeDataList&elementId=90002605";
}
</script>


</head>
<body>
<form name="frmApproveReject" method="post">
<input type="hidden" id="selDdocode" value="${ddocode}">
<input type="hidden" id="hierarchy" value="${hierarchy}">
<input type="hidden" id="hdnDdoMobileNo" value="${ddoMobileNo}">
<input type="hidden" id="hdnHeadMstrName" value="${headMasterName}">
<input type="hidden" id="hdnOfficeName" value="${officeName}">
<display:table name="${ddoInfoList}"  id="row" export="false" style="display:none" >
</display:table>

<br><br><br>

<center>
<div align="center" style="float: none; width: 50%" >
<fieldset class="tabstyle"><legend>Configuration Detail</legend> 

<table width="100%"  border="1">
<tr>
<td width="25%">DDO Code</td>
<td width="25%">${row[0]}</td>
</tr>
<tr>
<td width="25%">Office Name</td>
<td width="25%">${officeName}</td>
</tr>
<tr>
<tr>
<td width="25%">DDO Name</td>
<td width="25%">${headMasterName}</td>
</tr>
<tr style="display: none;">
<td width="25%">Head Master Mobile Number</td>
<td width="25%">${ddoMobileNo}</td>
</tr>
<tr>
<td width="25%">Level 2 DDO Code</td>
<td width="25%">${row[1]}</td>
</tr>
<tr>
<td width="25%">Level 2 DDO Name</td>
<td width="25%">${row[5]}</td>
</tr>
 
 


 



<tr>
<tr style="Display:none" id="reasonForRejectionRow">
<td width="25%">Reason for rejection</td><td width="75%"><input type="text" id="reasonForRejection" name="reasonForRejection" size="60" value=""/> </td>
</tr>
</table>

</fieldset>
</div>


<br><br>

<div align="center" style="float: none; width: 50%" >
<table width="50%" >
<tr>
<td align="right"><hdiits:button name="Approve" type="button" captionid="eis.Approve" bundle="${commonLables}" onclick="approveDtls(${row[0]});"></hdiits:button></td>
<td align="center"><hdiits:button name="Reject" type="button" captionid="eis.Reject" bundle="${commonLables}" onclick="rejectDtls(${row[0]});"></hdiits:button></td>
<td align="left"><hdiits:button name="btnClose" type="button" id="btnClose" captionid="eis.back" bundle="${commonLables}" onclick="backToSysApprove();" /></td>
</tr>
<tr >

</tr>
</table>
</div>

</center>
</form>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>