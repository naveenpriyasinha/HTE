<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script>
function GenerateDetails()
{
	var authNo = document.getElementById("txtAuthNo").value;
	alert(authNo);
	if(authNo.length > 0 ){
	showProgressbar();
	
	var uri = 'ifms.htm?actionFlag=getBillReport';
	var url = '&authNo='+authNo;

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
	}
	else{
	if(authNo.length == 0){
		alert('Authorization number can not be empty');
		document.getElementById("txtAuthNo").focus();
		}
	}
			
}

function generateExcel()
{
	var authNo = document.getElementById("txtAuthNo").value;
	//alert(authNo);
	if(authNo.length > 0 ){
	//showProgressbar();
	
	var url = "./hrms.htm?actionFlag=generateExcelForBill&AuthNo="+authNo;
	document.AuthNoForm.action=url;
	document.AuthNoForm.submit();
	
	
}
	else
		alert("Please Enter Authorization Number");
}

function generateExcelBasedOnUID()
{
	var authNo = document.getElementById("txtAuthNo").value;

	if(authNo.length > 0 ){
	//showProgressbar();
	
	alert("Work In Progress");
	var url = "./hrms.htm?actionFlag=generateExcelBasedOnUID&AuthNo="+authNo;
	//document.AuthNoForm.action=url;
	//document.AuthNoForm.submit();
}
	else
		alert("Please Enter Authorization Number");
}
</script>
<hdiits:form name="AuthNoForm" id="AuthNoForm" validate="true" method="post">
		
		
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	
	<table width = 50%>
	<tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr>
	<tr>
	<td colspan="2"</td>
	</tr>
	<tr>
	<td colspan="2"</td>
	</tr>
	<tr>
	<td colspan="2"</td>
	</tr>
	<tr>
	<td align="RIGHT">
				<fmt:message key="CMN.AUTH_NO" bundle="${commonLables}" />
			</td>
			<td align="left"> 
				<input type="text" id="txtAuthNo"  name="txtAuthNo" />
			</td>
	</tr>
	<tr>
	<td align="right">
				<hdiits:button name="btnSubmit" id="btnSubmit" type="button"  captionid="EIS.Submit" bundle="${commonLables}" onclick="generateExcel();" style="text-wrap:normal; width:80%"/>
				</td>
				
	</tr>
	
	<tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr><tr>
	<td colspan="2"</td>
	</tr>
	</table>
	
	</div>
	</hdiits:form>