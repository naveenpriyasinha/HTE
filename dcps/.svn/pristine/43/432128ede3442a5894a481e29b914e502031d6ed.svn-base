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
	if(radioValue=="Stop3rdLC"){
		window.returnValue = "Stop3rdLC";
	}
	else if(radioValue=="Club3rd4thLC"){
		window.returnValue = "Club3rd4thLC";
	}
	else if(radioValue=="Want3rdLC"){
		window.returnValue = "Want3rdLC";
	}
	if(radioValue=="WantSp"){
		window.returnValue = "WantSp";
	}	
	self.close();
}	
</script>
<hdiits:form name="frmSubtypeSelectionForHBA" encType="multipart/form-data"  validate="true" method="post">
<table width="100%">
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoWant3rdDisburse" value="Want3rdLC" checked="checked"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION2FORPPLC" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoStop3rdDisburse" value="Stop3rdLC"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION6FORPPLC" bundle="${lnaLabels}"></fmt:message></td>
	</tr>
	<tr>
		<td width="1%"><input type="radio" name="rdoDisburse" id="rdoClub3rd4thDisburse" value="Club3rd4thLC"/></td>
		<td width="99%"><fmt:message key="CMN.OPTION4FORPPLC" bundle="${lnaLabels}"></fmt:message></td>
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