<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script>

function selectValueOfPTApplicable()
{	
	var radio = document.formNewRegPhyForm.radioPTApplicable;

	var radioValue;
	for (var i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(radioValue=="TRUE"){
		//window.returnValue = "T";
		window.opener.document.getElementById("hidPTApplicableForPhyHandi").value = "T";
	}
	else if(radioValue=="FALSE"){
		//window.returnValue = "F";
		window.opener.document.getElementById("hidPTApplicableForPhyHandi").value = "F";
	}
	
	//alert('After value-->'+window.opener.document.getElementById("hidPTApplicableForPhyHandi").value);
	//self.close();
	window.close();
}	

</script>

<hdiits:form name="formNewRegPhyForm" encType="multipart/form-data"  validate="true" method="post">

<table align="center">

<tr>
	<td>
		<table align="left">
			<tr>
				<td>
					<font size="3" style="color: red" ><fmt:message key="CMN.PTAPPLICABLE"	bundle="${dcpsLables}"></fmt:message></font>
				</td>
				<td></td>
				<td> 
					<fmt:message key="CMN.YES"	bundle="${dcpsLables}"></fmt:message> 
					<input type="radio"	id="radioPTApplicable" name="radioPTApplicable" value="TRUE"/>
					<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
					<input type="radio"	id="radioPTApplicableFalse" name="radioPTApplicable" value="FALSE" checked="checked" />
				</td>
			</tr>
		</table>
	</td>
</tr>

<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>
<tr></tr>

<tr>
	<td>
		<div align="center">
			<hdiits:button name="btnOK" id="btnOK" type="button" captionid="BTN.OK" bundle="${dcpsLables}" onclick="selectValueOfPTApplicable();" />
		</div>
		<c:out value = "${lStrhidPTApplicableForPhyHandi}"/>
	</td>
</tr>

</table>

</hdiits:form>

<script>
	var PTApplicableOrNot = window.opener.document.getElementById("hidPTApplicableForPhyHandi").value.trim();
	if(PTApplicableOrNot == 'T')
		{
			document.getElementById("radioPTApplicable").checked = true;		
		}
	else
		{
		document.getElementById("radioPTApplicable").checked = false;
		}
</script>