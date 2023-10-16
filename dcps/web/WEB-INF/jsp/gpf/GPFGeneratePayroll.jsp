<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels"
	scope="request" />

<script language="javascript">
function generatePayroll(){

	var txtEmpId=document.getElementById("txtEmpId").value;
	
	var uri = "ifms.htm?actionFlag=GPFPayrollProcessing";
	var url = "&txtEmpId="+txtEmpId;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSuccessMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getSuccessMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblSaveFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblSaveFlag) {
		alert('GPF Data updated successfully');		
		self.location.reload();
	}

}
</script>

<hdiits:form name="GPFGeneratePayrollForm" id="GPFGeneratePayrollForm"
	encType="multipart/form-data" validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

	<table border="0" width="70%" align="center" cellpadding="4"
		cellspacing="4">
		<tr align="center">
			<td width="25%" align="left"><fmt:message key="CMN.EMPID"
				bundle="${gpfLabels}" /></td>
			<td width="50%" align="left"><input type="text"
				id="txtEmpId" size="30"	name="txtEmpId" /></td>
		</tr>

		<tr>
			<td align="center" colspan="2">
			<table border="0" width="50%" align="center">
				<tr>
					<td><hdiits:button name="btnGeneratePayroll" id="btnGeneratePayroll"
						type="button" captionid="BTN.GENERATEPAYROLL" bundle="${gpfLabels}"
						onclick="generatePayroll();" /></td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>

</hdiits:form>