<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request" />
<script language="JavaScript">
function onKeyDownEvent(event){
	if(event.keyCode == 78)
	{
		CloseBox();
	}
	else if (event.keyCode == 89)
	{
		openBox();
	}
}

function CloseBox()
{		
	window.returnValue = "No";
	self.close();
	
}
function openBox()
{	
	var radio = document.frmSubtypeSelectionForHBA.rdoDisburse;

	var radioValue;
	for (var i=0; i<radio.length; i++){
		  if (radio[i].checked == true){
			  radioValue=radio[i].value;
		  }
	}
	if(radioValue=="Stop4thLC"){
		window.returnValue = "Stop4thLC";
	}
	else if(radioValue=="Want4thLC"){
		window.returnValue = "Want4thLC";
	}	
	else if(radioValue=="WantSp"){
		window.returnValue = "WantSp";
	}	
	self.close();
}	
</script>
<hdiits:form name="frmSubtypeSelectionForHBA" encType="multipart/form-data"  validate="true" method="post">
<table width="100%">
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoWant4thDisburse" value="Want4thLC" checked="checked"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION3FORPPLC" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>	
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoStop4thDisburse" value="Stop4thLC"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION7FORPPLC" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>		
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoWantSp" value="WantSp"/></td>
		<td width="99%"><fmt:message key="CMN.OPTIONFORSP" bundle="${lnaLabels}"></fmt:message></td>				
	</tr>	
</table>
<table align="center">
<tr>
		<td width="15%">
			<hdiits:button name="btnOK" id="btnOK" type="button" captionid="BTN.OK" bundle="${lnaLabels}" onclick="openBox();" />
		</td>
		
		<td width="15%">
			<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${lnaLabels}" onclick="CloseBox();" />
		</td>
		
</tr>
</table>
</hdiits:form>