<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />

<script type="text/javascript">
function savePensionBillPref()
{
	var pnsnBillGenFor = "currMonth";
	if(document.getElementById("radPrevMnth").checked)
	{
		pnsnBillGenFor = document.getElementById("radPrevMnth").value;
	}
	else
	{
		pnsnBillGenFor = document.getElementById("radCurrMnth").value;
	}
	var ppoNo = window.opener.document.getElementById("selectedPpoNo").value;
	window.opener.document.getElementById("pnsnBillGenFor").value = pnsnBillGenFor;
	window.opener.openBillDtls('PENSION',ppoNo);
	self.close();
}



</script>
<hdiits:form name="frmPensionBillPopup" method="post" validate="" >
<fieldset style="width:100%"  class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.PNSNBILLPOPUP" bundle="${pensionLabels}"></fmt:message></b>
</legend>
	Generate Pension Bill For : <br/><br/>
	<input type="hidden" id="hidPpoNo" name="hidPpoNo" />
	<input type="radio" name="pnsnBillForMonth" value="prevMonth" id="radPrevMnth">Previous Month</input> &nbsp&nbsp
	<input type="radio" name="pnsnBillForMonth" value="currMonth" id="radCurrMnth" checked='checked'>Current Month</input><br/><br/>
	<center>
	<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="PPMT.BUTTONOK" bundle="${pensionLabels}" onclick="savePensionBillPref();" />
	<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="self.close();"/>
	</center>
</fieldset>
</hdiits:form>