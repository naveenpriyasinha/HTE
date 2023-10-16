<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script>
function SaveEmployerContriUsingAjax()
{
	var billAmount=document.getElementById("txtEmplrContriPending").value;
	var dcpsId=document.getElementById("hidDcpsId").value;
	var terminationDate=document.getElementById("txtTerminationDate").value;
	var terminalId=document.getElementById("hidTerminalId").value;
	
	var uri = "ifms.htm?actionFlag=insertEmployerContributionOnTermination";
	var url = "&billAmount="+billAmount+"&dcpsId="+dcpsId+"&terminationDate="+terminationDate+"&terminalId="+terminalId;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForSaveData(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function dataStateChangedForSaveData(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("Employer Contribution Performed");
		document.getElementById("btnEmployerContribution").disabled="disabled";
	}
}
function validateTerminalData(){
	if(!chkEmpty(document.getElementById("txtOrderNo"),"Order No")
			|| !chkEmpty(document.getElementById("txtOrderDate"),"Order Date")
			|| !chkEmpty(document.getElementById("txtLetterNo"), "Authority Letter No")
			|| !chkEmpty(document.getElementById("textareaRemarks"), "Remarks"))
	{
		return false;
	}
	return true;
}
function SaveTerminalRequestUsingAjax()
{
	if(!validateTerminalData())
	{
		return ;
	}
	var uri="ifms.htm?actionFlag=saveOrForwardTerminalRequest";
	var url = runForm(0);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
		dataStateChangedForSaveTerminalRequest(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function dataStateChangedForSaveTerminalRequest(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
		alert('Terminal Request Saved Successfully.');
		self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=SRKA&Use=FromDDO&elementId=700192";
	}
}
</script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lDblCurrYearContri" value="${resValue.lDblCurrYearContriPaid + resValue.lDblCurrYearContriPending}"></c:set>

<fmt:formatNumber type="number" pattern="##,##,###.##" value="${resValue.lDblInterestOpenBal + resValue.lDblInterestForContribution + resValue.lDblInterestForMissingCredits}" var="lDblInterest"/>
<hdiits:form name="TerminalRequestForm" id="TerminalRequestForm" encType="multipart/form-data" validate="true" method="post">
<input type="hidden" id="hidDcpsId" name="hidDcpsId" value="${resValue.lStrDcpsId}" />
	<input type="hidden" name="hidUser" id="hidUser" value="${resValue.hidUser}" />
	<input type="hidden" name="hidUse" id="hidUse" value="${resValue.hidUse}" />
	<input type="hidden" name="hidDcpsEmpId" id="hidDcpsEmpId" value="${resValue.lStrEmpId}" />
	<input type="hidden" name="hidDcpsId" id="hidDcpsId" value="${resValue.lStrDcpsId}" />
	<input type="hidden" name="hidTerminalId" id="hidTerminalId" value="${resValue.hidTerminalId}" />
	<input type="hidden" name="hidOpenInt" id="hidOpenInt" value="${resValue.lDblInterestOpenBal}" />
	<input type="hidden" name="hidContriEmpInt" id="hidContriEmpInt" value="${resValue.lDblInterest - lDblInterestOpenBal}" />
	<input type="hidden" name="hidTier2Int" id="hidTier2Int" value="${resValue.lDblTier2ContriInterest}" />


<fieldset class="tabstyle" ><legend><fmt:message key="CMN.TERMINATIONDETAILS" bundle="${dcpsLabels}" ></fmt:message></legend>
</br>
<table width="80%" cellpadding="4" cellspacing="4">
       <tr>
           <td width=20%">
           <fmt:message key="CMN.EMPNAME" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td colspan="3">
           <input type="text" id="txtEmployeeName" name="txtEmployeeName" value="${resValue.lStrName}" size="40" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="20%">
           <fmt:message key="CMN.REASONFORTERMINATION" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td colspan="3">
            <select name="cmbReasonForTermination"
			id="cmbReasonForTermination" onChange=""${varDisableAboveInputDtls} disabled="disabled" style="width:180px">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="reasonForTermination"
				items="${resValue.listReasonsForTermination}">
				<c:choose>
					<c:when
						test="${resValue.lStrReasonForTermination == reasonForTermination.lookupId}">
						<option value="${reasonForTermination.lookupId}"
							selected="selected"><c:out
							value="${reasonForTermination.lookupDesc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${reasonForTermination.lookupId}"><c:out
							value="${reasonForTermination.lookupDesc}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
           </td>

       </tr>
       <tr>
           <td width="20%">
           <fmt:message key="CMN.ORDERNO" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="20%">
           <input type="text" id="txtOrderNo" size="20" name="txtOrderNo" />
           <label class="mandatoryindicator" >*</label>
           </td>
           <td width="10%">
           <fmt:message key="CMN.ORDERDATE" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="50%">
           <input type="text" name="txtOrderDate" id="txtOrderDate" maxlength="10" value="" onkeypress="digitFormat(this);dateFormat(this);" 
						onBlur=""  ${varReadOnlyAboveInputDtls}/> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtOrderDate",375,570)' style="cursor: pointer;" ${varImageDisabledAboveInputDtls} />
				<label class="mandatoryindicator" >*</label>
           </td>

       </tr>
       <tr>
           <td width="20%">
           <fmt:message key="CMN.DATEOFTERMINATION" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td colspan="3">
           		<input type="hidden" name="hidJoiningDate" id="hidJoiningDate" value="${resValue.lStrDOJ }"/>
				<input type="text" name="txtTerminationDate" id="txtTerminationDate" maxlength="10" value="${resValue.lStrTerminationDate}" onkeypress="digitFormat(this);dateFormat(this);" readOnly="readOnly" 
						onBlur="compareDates(txtTerminationDate,hidJoiningDate,'Date of Termination should be greater than date Of Joining.','>');"  ${varReadOnlyAboveInputDtls}/> 
				
           </td>

       </tr>
       <tr>
           <td width="20%">
           <fmt:message key="CMN.AUTHLETTERNO" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td colspan="3">
           <input type="text" id="txtLetterNo" size="20" name="txtLetterNo" />
           <label class="mandatoryindicator" >*</label>
           </td>

       </tr>
       <tr>
           <td width="20%">
           <fmt:message key="CMN.REMARKS" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td colspan="3">
           <textarea NAME="textareaRemarks" id="textareaRemarks" ROWS="2" cols="35"  ></textarea>
           <label class="mandatoryindicator" >*</label>
           </td>

       </tr> 
</table>
</br>
</fieldset>
<fieldset class="tabstyle" >
</br>
<table width="100%" cellpadding="4" cellspacing="4">
       <tr>
           <td width="45%">
           <fmt:message key="CMN.OPENINGBAL" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtOpeningBal" size="20" name="txtOpeningBal" value="${resValue.lDblOpeningBal}" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="45%">
           <fmt:message key="CMN.EMPTERMINALCONTRI" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtCurrYearAndMissingContri" size="20" name="txtCurrYearAndMissingContri" value="${lDblCurrYearContri}" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="45%">
           <fmt:message key="CMN.EMPLRTERMINALCONTRI" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtEmplrContri" size="20" name="txtEmplrContri" value="${resValue.lDblCurrYearContriPaid}" style="text-align: right" readOnly="readOnly"/>&nbsp;&nbsp;&nbsp;
           </td>

       </tr>
       
       <tr>
           <td width="45%">
           <fmt:message key="CMN.EMPLRTERMINALCONTRIPENDING" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtEmplrContriPending" size="20" name="txtEmplrContriPending" value="${resValue.lDblCurrYearContriPending}" style="text-align: right" readOnly="readOnly"/>&nbsp;&nbsp;&nbsp;
           <c:if test="${resValue.lDblCurrYearContriPending != 0}">
           <hdiits:button type="button" captionid="BTN.EMPLOYERCONTRI" bundle="${dcpsLabels}" id="btnEmployerContribution" name="btnEmployerContribution" onclick="SaveEmployerContriUsingAjax();" style="width:170px" ></hdiits:button>
           </c:if>
           </td>

       </tr>
       
       <tr>
           <td width="45%">
           <fmt:message key="CMN.TERMINALINTREST" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           
           <input type="text" id="txtInterest" size="20" value="${lDblInterest}" name="txtInterest" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="45%">
           <fmt:message key="CMN.TIER2CONTRITERMINAL" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtTier2Contri" size="20" value="${resValue.lDblTier2Contri}" name="txtTier2Contri" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="45%">
           <fmt:message key="CMN.TIER2CONTRIINTTERMINAL" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width=55%>
           <input type="text" id="txtTier2Interest" size="20" value="${resValue.lDblTier2ContriInterest}" name="txtTier2Interest" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
       <tr>
           <td width="45%">
           <fmt:message key="CMN.TOTALAMTTERMINAL" bundle="${dcpsLabels}"></fmt:message>
           </td>
           <td width="55%">
           <input type="text" id="txtTotalAmt" size="20" value="${resValue.lDblOpeningBal+(resValue.lDblCurrYearContri)*2+resValue.lDblInterest+resValue.lDblTier2Contri+resValue.lDblTier2ContriInterest}" name="txtTotalAmt" style="text-align: right" readOnly="readOnly"/>
           </td>

       </tr>
</table>
</br>
</fieldset>
<table width="50%" align="center">
<br>
	<tr>
    	<td width="15%">
        	<hdiits:button type="button" captionid="BTN.SAVE" bundle="${dcpsLabels}" id="btnSave" name="btnSave" onclick="SaveTerminalRequestUsingAjax();"></hdiits:button>
        </td>
        <td width="15%">
            <hdiits:button type="button" captionid="BTN.VIEWATTACHMENTS" bundle="${dcpsLabels}" id="btnViewAttachments" name="btnViewAttachments" onclick="" style="width:150px"></hdiits:button>
        </td>
        <td width="15%">
            <hdiits:button type="button" captionid="BTN.CANCEL" bundle="${dcpsLabels}" id="btnCancel" name="btnCancel" onclick=""></hdiits:button>
        </td>
    </tr>
</table>
</hdiits:form>
