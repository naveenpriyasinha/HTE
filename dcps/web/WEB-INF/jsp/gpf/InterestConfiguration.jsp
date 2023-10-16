<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script language="javascript">
function submitInterestRate(){
	if(!validateInterestConfig())
	{
		return;
	}
	
	var uri="ifms.htm?actionFlag=saveInterestConfig";
	var url = runForm(0);

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getSaveRequestMsg(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function validateInterestConfig(){
	if (!chkEmpty(document.getElementById("cmbGroup"), "Group")
			|| !chkEmpty(document.getElementById("txtEffeFromDate"),"Effective From Date")				
			|| !chkEmpty(document.getElementById("txtApplicableToDate"), "Applicable To Date")
			|| !chkEmpty(document.getElementById("txtInterestRate"), "Interest Rate")) 
	{
		return false;
	}

	return true;
}
function getSaveRequestMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (lblFlag=="true") {
		alert('Request has been Saved');		
		self.location.href = "ifms.htm?actionFlag=loadInterestConfig";
	}
}
</script>
<hdiits:form name="GPFInterestConfiguration" encType="multipart/form-data" validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<table width="80%" align="center"><tr><td>
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.INTERESTCONFIG" bundle="${gpfLabels}" ></fmt:message></legend>
</br>
<table width="80%" align="center" cellpadding="4" cellspacing="4">

	<tr>
		<td width="20%"><fmt:message key="CMN.GROUP" bundle="${gpfLabels}"></fmt:message></td>
		<td colspan="3">
		<select name="cmbGroup" id="cmbGroup" style="width:160px">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="groupList" items="${resValue.groupList}">
						<option value="${groupList.lookupId}">
							<c:out	value="${groupList.lookupDesc}"/>
						</option>
				</c:forEach>
		</select><label class="mandatoryindicator">*</label>
		</td>

	</tr>
	
	<tr>
		<td width="20%"><fmt:message key="CMN.EFD" bundle="${gpfLabels}"></fmt:message></td>
		<td width="25%">
		<input type="text" name="txtEffeFromDate" id="txtEffeFromDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);previousDateValidation(this);" value = "${appDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEffeFromDate",375,570)' style="cursor: pointer;"  ${varImageDisabled}/>
			<label class="mandatoryindicator">*</label>
		</td>
		<td width="15%"><fmt:message key="CMN.ATD" bundle="${gpfLabels}"></fmt:message></td>
		<td width="25%">
		<input type="text" name="txtApplicableToDate" id="txtApplicableToDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);previousDateValidation(this);" value = "${appDate}" ${varDisabled}/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtApplicableToDate",375,570)' style="cursor: pointer;"  ${varImageDisabled}/>
			<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	<tr>
		<td width="20%"><fmt:message key="CMN.INTERESTRATE" bundle="${gpfLabels}"></fmt:message></td>
		<td width="25%" colspan="3"><input type="text"  name='txtInterestRate' id="txtInterestRate" onkeypress="digitFormat(this);"/><label class="mandatoryindicator">*</label></td>

	</tr>
</table>
<br>
</fieldset>
</td></tr></table>
<table width="50%" align="center"><tr><td align="center"></br>
<hdiits:button type="button" captionid="BTN.SAVE" bundle="${gpfLabels}" id="btnSubmit" name="btnSubmit" onclick="submitInterestRate();"></hdiits:button>

</td></tr></table>
</hdiits:form>