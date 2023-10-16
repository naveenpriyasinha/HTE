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
	if(radioValue=="Stop2ndCF"){
		window.returnValue = "Stop2ndCF";
	}
	else if(radioValue=="Club2nd3rdCF"){
		window.returnValue = "Club2nd3rdCF";
	}
	else if(radioValue=="Want2ndCF"){
		window.returnValue = "Want2ndCF";
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
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoWant2ndDisburse" value="Want2ndCF" checked="checked"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION1FORCF" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoStop2ndDisburse" value="Stop2ndCF"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION4FORCF" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoClub2nd3rdDisburse" value="Club2nd3rdCF"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION3FORCF" bundle="${lnaLabels}"></fmt:message></td>
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
</hdiits:form>