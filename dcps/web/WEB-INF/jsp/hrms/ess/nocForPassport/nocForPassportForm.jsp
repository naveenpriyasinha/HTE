<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="todaysDate" value="${resultValue.todaysDate}"></c:set>
<c:set var="purpose" value="${resultValue.nocPurpose}"></c:set>
<c:set var="country" value="${resultValue.visitingCountry}"></c:set>
<c:set var="prevNOCList" value="${resultValue.prevNOCList}"></c:set>
<c:set var="pastFvisit" value="${resultValue.pastFvisitList}"></c:set>
<fmt:formatDate var="todayDate"  pattern="dd/MM/yyyy" value="${todaysDate}" type="date"/>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request"/>
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/nocForPassportForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>
<script type="text/javascript">
	var nocPassportFormArray = new Array();
	
	nocPassportFormArray[0]="<fmt:message bundle="${NOCAlerts}" key="NOC.GreaterThanTodaysDate"/>";
	nocPassportFormArray[1]="<fmt:message bundle="${NOCAlerts}" key="NOC.VDate"/>";	
	nocPassportFormArray[2]="<fmt:message bundle="${NOCLables}" key="NOC.Years"/>";
	nocPassportFormArray[3]="<fmt:message bundle="${NOCLables}" key="NOC.Months"/>";
	nocPassportFormArray[4]="<fmt:message bundle="${NOCLables}" key="NOC.Days"/>";
	nocPassportFormArray[5]="<fmt:message bundle="${NOCAlerts}" key="NOC.LessThanTodaysDate"/>";
	nocPassportFormArray[6]="<fmt:message bundle="${NOCAlerts}" key="NOC.VnocDate"/>";
	nocPassportFormArray[7]="<fmt:message bundle="${NOCAlerts}" key="NOC.AddFvisitFirst"/>";
	nocPassportFormArray[8]="<fmt:message bundle="${NOCAlerts}" key="NOC.AddPrevNocFirst"/>";
	nocPassportFormArray[9]="<fmt:message bundle="${NOCAlerts}" key="NOC.PlzSaveExp"/>";
	nocPassportFormArray[10]="<fmt:message bundle="${NOCAlerts}" key="CTRY.AtleastOne"/>";
	
	var todayDt='${todayDate}';
	var pastFvisitDtl ='${pastFvisit}';
	var prevNOCLst='${prevNOCList}';
	
</script>
</head>
<body>

<hdiits:form name="frmNocPassport" validate="true" method="POST" action="./hrms.htm?actionFlag=nocSubmitData"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
			<fmt:message key="NOC.NocHeader" bundle="${NOCLables}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle" style="height: 100%">
		
	<div id="tcontent1" class="tabcontent" tabno="0" >

		<%@ include file="../../eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup titleCaptionId="NOC.SecApplnDetails" bundle="${NOCLables}" id="ApplnDetails" mandatory="true">	

		<!--  <table bgcolor="#386CB7" align="center"  width="100%">
			<tr align="center">
		      <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecApplnDetails"></fmt:message></u></strong></font></td>
		    </tr> 
		</table>-->
		
		<table width="100%">
			<tr>
				<td width="20%"><hdiits:caption captionid="NOC.Purpose" bundle="${NOCLables}"/></td>
				<td width="33%">
					<hdiits:select name="selPurpose" id="selPurpose" captionid="NOC.Purpose" bundle="${NOCLables}" sort="false" onchange="showPurposeDetails();" validation="sel.isrequired" mandatory="true">
						<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
							<c:forEach var="purposeValue" items="${purpose}">
								<hdiits:option value="${purposeValue.lookupName}">${purposeValue.lookupDesc}</hdiits:option>
							</c:forEach>
					</hdiits:select></td>
				<td></td>
				<td></td>	
			</tr>
		</table>
		
	<div id="displayVisitDetails" style="display:none">	
	<hdiits:fieldGroup titleCaptionId="NOC.SecJourneyDetails" bundle="${NOCLables}" mandatory="true">	
	
	<!--  <table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
	      <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecJourneyDetails"></fmt:message></u></strong></font></td>
	    </tr> 
	</table>-->
		
	<table width="100%" >
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.NocVisitCountry" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:search name="selCountry" id="selCountry" url="hrms.htm?actionFlag=searchCountry" captionid="NOC.NocVisitCountry" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true" /></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.StartDate" bundle="${NOCLables}"/></td>
			<td width="33%"><hdiits:dateTime name="txtFromDateVisit" captionid="NOC.StartDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="showDuration();" maxvalue="31/12/2099"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.EndDate" bundle="${NOCLables}"/></td>
			<td><hdiits:dateTime name="toDateVisit" captionid="NOC.EndDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="showDuration();" maxvalue="31/12/2099"/></td>
		</tr>
		<tr id="displayDuration" style="display:none">
			<td><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></td>
			<td><hdiits:text name="txtDuration" id="txtDuration" readonly="true" /></td>
			<td></td>
			<td></td>
		</tr>
		<tr>		
			<td><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></td>
			<td><hdiits:textarea name="txtaPurposeOfVisit" captionid="NOC.NocPurposeVisit" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>
			<td><hdiits:caption captionid="NOC.OtherDetails" bundle="${NOCLables}"/></td>
			<td><hdiits:textarea name="txtaOtherDetails" caption="txtaOtherDetails" rows="3" cols="30" maxlength="200"/></td>
		</tr>
		<tr>		
			<td><hdiits:caption captionid="NOC.Expense" bundle="${NOCLables}"/></td>
			<td><hdiits:text name="numExpense" id="numExpense" captionid="NOC.Expense" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true" maxlength="10" onkeypress="return checkNumberForOnlyOneDot(this.value)" onblur="fixTwoDecimalPt();" style="text-align: right"/></td>
			<td></td>
			<td></td>
		</tr>
	</table>

<%@ include file="../nocForForeignVisit/Expenditure.jsp"%>
<br>
	</hdiits:fieldGroup>
	</div>
	
	
<table id="displayRemark" style="display:none" width="100%">
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.Remark" bundle="${NOCLables}"/></td>
		<td>
		 <hdiits:textarea name="txtaRemark" caption="txtaRemark" captionid="NOC.Remark" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/>
		</td>
		<td></td>
		<td></td>	
	</tr>
</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecPastForeign" bundle="${NOCLables}" id="PastForeignDtls" collapseOnLoad="true">	

<!--  <table bgcolor="#386CB7" align="center"  width="100%" id="displayBearerDetailsHeader" style="display:none">
	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecBearerDetails"></fmt:message></u></strong></font></td>
    </tr> 
</table>


<table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPastForeign"></fmt:message></u></strong></font></td>
    </tr> 
</table>-->

<table width="100%" >
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.NocAbroad" bundle="${NOCLables}"/></td>
		<td width="15%"><hdiits:radio name="rdoNocVisitedCountry" id="rdoNocVisitedCountry" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showVisitedCountryDetails();"/>
			<hdiits:radio name="rdoNocVisitedCountry" value="No" id="rdoNocVisitedCountry" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideVisitedCountryDetails();" /></td>
		<td class="fieldLabel"><hdiits:caption captionid="NOC.OneTime" bundle="${NOCLables}"/></td>
		<td></td>	
	</tr>
</table>

<script>
	
</script>

<c:if test="${empty pastFvisit=='false'}" >
	<script>
		document.frmNocPassport.rdoNocVisitedCountry[0].checked = true;
		document.frmNocPassport.rdoNocVisitedCountry[0].disabled = true;
		document.frmNocPassport.rdoNocVisitedCountry[1].disabled = true;
	</script>
	
	<table id='pastFvisitTable' name="pastFvisitTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
	<tr>		
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.FromDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.ToDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocVisitedCountry" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></label></b></td>
	</tr>
	
	<c:forEach var="pastFvisitValue" items="${pastFvisit}">
		<tr>		
			<td align="left" class="fieldLabel"><fmt:formatDate var="fromDates"  pattern="dd/MM/yyyy" value="${pastFvisitValue.fromDate}" type="date"/>${fromDates}</td>
			<td align="left" class="fieldLabel"><fmt:formatDate var="toDates"  pattern="dd/MM/yyyy" value="${pastFvisitValue.toDate}" type="date"/>${toDates}</td>
			<td align="left" class="fieldLabel">
			<script>									
				var dateDiff = getDateDiffInString("${fromDates}","${toDates}");
				var dateDiffDisplay;			
				var duration = dateDiff.split("~");
				if(duration[0] != 0 && duration[1] != 0)
					dateDiffDisplay = duration[0]+" " +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> "+" "+ duration[1] +" " +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> "+" " + duration[2]+" "+ " <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
				else if(duration[0] == 0 && duration[1] != 0)
						dateDiffDisplay = duration[1]+" " +"<fmt:message bundle='${NOCLables}' key='NOC.Months'/>" +" "+ duration[2]+" "+"<fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
					 else if(duration[0] != 0 && duration[1] == 0)
							dateDiffDisplay = duration[0]+" " +"<fmt:message bundle='${NOCLables}' key='NOC.Years'/>" +" "+ duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
						else if(duration[0] == 0 && duration[1] == 0)
								dateDiffDisplay = duration[2]+" "+"<fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
							else
								dateDiffDisplay = duration[0]+" " +"<fmt:message bundle='${NOCLables}' key='NOC.Years'/>"+" " + duration[1]+" " +"<fmt:message bundle='${NOCLables}' key='NOC.Months'/>" +" " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
				
				document.write(dateDiffDisplay);
			</script>
			</td>
			<td align="left" class="fieldLabel">
				<c:set var="strPlaces" value=""></c:set>				
				<c:forEach var="foreignJournyDtl" items="${pastFvisitValue.hrEssForeignJournyDtls}">								
					<c:set var="strPlaces" value="${strPlaces},${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"></c:set>	
				</c:forEach>
								
				<c:if test="${fn:length(strPlaces) gt 0}">
					<c:out 	value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:out> 
				</c:if>				
			</td>	
			<td align="left" class="fieldLabel">${pastFvisitValue.purposeOfVisit}</td>					
		</tr>
	</c:forEach>
	
</table>

</c:if>

<c:if test="${empty pastFvisit=='true'}" >
	<script>
		document.frmNocPassport.rdoNocVisitedCountry[1].checked = true;
		document.frmNocPassport.rdoNocVisitedCountry[0].disabled = false;
		document.frmNocPassport.rdoNocVisitedCountry[1].disabled = false;
	</script>
</c:if>	
 
<table width="100%" id="displayVisitedDetails" style="display:none">
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.FromDate" bundle="${NOCLables}"/></td>
		<td width="33%"><hdiits:dateTime name="txtFromDate" captionid="NOC.FromDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="checkDatesValue();"/></td>
		<td width="20%"><hdiits:caption captionid="NOC.ToDate" bundle="${NOCLables}"/></td>
		<td><hdiits:dateTime name="txtToDate" captionid="NOC.ToDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="checkDatesValue();"/></td>
	</tr>
	<tr id="displayFvisitDuration" style="display:none">
		<td width="20%"><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></td>
		<td width="33%" ><hdiits:text name="txtFvisitDuration" id="txtFvisitDuration" readonly="true"/></td>
	</tr>
	<tr>
		<td><hdiits:caption captionid="NOC.NocVisitedCountry" bundle="${NOCLables}"/></td>
		<td><hdiits:search name="selVisitedCountry" id="selVisitedCountry" url="hrms.htm?actionFlag=searchCountry" captionid="NOC.NocVisitedCountry" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true"/></td>			
		<td><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></td>
		<td><hdiits:textarea id="txtaPurpOfVisit" name="txtaPurpOfVisit" captionid="NOC.NocPurposeVisit" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>	
	</tr>
	<tr><td><br></td></tr>
	<tr>		
		<td width="100%"  colspan="4">
			<center><hdiits:button type="button" name="btnAddFvisit" id="btnAddFvisit" captionid="NOC.Add" bundle="${NOCLables}" title="Add Record" onclick="javascript:addOrUpdateForeignVisit('addFvisitRecord', 'addFvisit');"/>
		    	&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateFvisit" id="btnUpdateFvisit" title="Update Record" captionid="NOC.Update" bundle="${NOCLables}" style="display:none" onclick="javascript:addOrUpdateForeignVisit('updateFvisitRecord', 'addFvisit')"/>
        		&nbsp;&nbsp;<hdiits:button type="button" name="btnResetFvisit" id="Reset" captionid="NOC.Reset" bundle="${NOCLables}" onclick="resetFvisitData()" title="Reset Record"></hdiits:button>
        	</center>
        </td>        
	</tr>
</table>

<br>

<table id='fvisitTable' name="fvisitTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" style="display:none">
	<tr>		
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.FromDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.ToDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocVisitedCountry" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}"/></label></b></td>
	</tr>	
</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecPrevNonAppln" bundle="${NOCLables}" id="PrevNonAppln" collapseOnLoad="true">	

<!-- <table bgcolor="#386CB7" align="center"  width="100%">
	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPrevNonAppln"></fmt:message></u></strong></font></td>
    </tr> 
</table> -->
<table width="100%">
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.NocPast" bundle="${NOCLables}"/></td>
		<td width="15%"><hdiits:radio name="rdoNocIssued" id="rdoNocIssued" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showTextBoxes();"/>
			<hdiits:radio name="rdoNocIssued"  value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hide();" /></td>
		<td class="fieldLabel"><hdiits:caption captionid="NOC.OneTime" bundle="${NOCLables}"/></td>
		<td></td>	
	</tr>
</table>

<c:if test="${empty prevNOCList=='false'}" >
	<script>
		document.frmNocPassport.rdoNocIssued[0].checked = true;
		document.frmNocPassport.rdoNocIssued[0].disabled = true;
		document.frmNocPassport.rdoNocIssued[1].disabled = true;
	</script>
	
	<table id='prevNocDBTable' name="prevNocDBTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
		<tr>		
			<td style="display:none">&nbsp;</td>			
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}"/></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}"/></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}"/></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}"/></label></b></td>
		</tr>
		<c:forEach var="prevNocValue" items="${prevNOCList}">
		<tr>
			<td align="left" class="fieldLabel"><fmt:formatDate var="nocIssueDate" pattern="dd/MM/yyyy" value="${prevNocValue.nocIssueDate}" type="date"/>${nocIssueDate}</td>
			<td align="left" class="fieldLabel">${prevNocValue.appoverName}</td>
			<td align="left" class="fieldLabel">${prevNocValue.approverDesignationId.dsgnName}</td>
			<td align="left" class="fieldLabel">${prevNocValue.orderNo}</td>
		</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${empty prevNOCList=='true'}" >
	<script>
		document.frmNocPassport.rdoNocIssued[1].checked = true;
		document.frmNocPassport.rdoNocIssued[0].disabled = false;
		document.frmNocPassport.rdoNocIssued[1].disabled = false;
	</script>
</c:if>

<table width="100%" id="displayIfYes" style="display:none">
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}"/></td>
		<td width="33%"><hdiits:dateTime  name="date" captionid="NOC.NocDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="checkWithTodaysDate();"/></td>	
		<td width="20%"><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}"/></td>
		<td><hdiits:search name="txtApprover" id="txtApprover" captionid="NOC.Approved" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true"/></td>					
	</tr>	
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}"/></td>
		<td width="33%"><hdiits:text name="txtDesignation" id="txtDesignation" captionid="NOC.Designation" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true"/><hdiits:hidden name="hdnDesignationId" id="hdnDesignationId"/></td>
		<td width="20%"><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}"/></td>
		<td><hdiits:text name="txtOrderNo" id="txtOrderNo" captionid="NOC.OrderNo" bundle="${NOCLables}" maxlength="20" validation="txt.isrequired" mandatory="true"/></td>				
	</tr>
	<tr><td><br></td></tr>
	<tr>	
		<td colspan="4" align="center">
			<hdiits:button type="button" name="btnAddPrevNOC" id="btnAddPrevNOC" captionid="NOC.Add" bundle="${NOCLables}" title="Add Record" onclick="javascript:addOrUpdatePrevNOC('addPrevNOCRecord', 'addPrevNOC');"/>
		   	 	&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdatePrevNOC" id="btnUpdatePrevNOC" title="Update Record" captionid="NOC.Update" bundle="${NOCLables}" style="display:none" onclick="javascript:addOrUpdatePrevNOC('updatePrevNOCRecord', 'addPrevNOC');"/>
        		&nbsp;&nbsp;<hdiits:button type="button" name="btnResetPrevNOC" id="Reset" captionid="NOC.Reset" bundle="${NOCLables}" onclick="resetPrevNOCData()" title="Reset Record"></hdiits:button>        	
        </td>
	</tr>
</table>

<br>

<table id='prevNOCTable' name="prevNOCTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" style="display:none">
	<tr>		
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}"/></label></b></td>
	</tr>	
</table>
 </hdiits:fieldGroup>
<br>
 
<br>
	   	 	
<table align="center"  width="100%"  colspan="4">
	<tr align="center">
      <td><center><hdiits:button type="button" name="btnSubmit" captionid="NOC.Submit" bundle="${NOCLables}" title="Submit Form" onclick="submitNocDtls();"/>
		   	 	&nbsp;&nbsp;<hdiits:button type="button" name="btnClose" id="btnClose" title="Close" captionid="NOC.Close" bundle="${NOCLables}" onclick="closePage();"/>
	  </center>
	  </td>
    </tr> 
</table>

</div>

	</div>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:validate locale="${locale}" controlNames="selPurpose,txtaRemark,name_selCountry,txtaPurposeOfVisit,txtFromDateVisit,toDateVisit,numExpense,selBearer,selBearerName,txtFromDate,txtToDate,name_selVisitedCountry,txtaPurpOfVisit,date,name_txtApprover,txtDesignation,txtOrderNo"/> 
</hdiits:form> 

<script type="text/javascript">		
initializetabcontent("maintab");
document.getElementById("displayAmtLabel").style.display='none';
document.getElementById("displayAmt").style.display='none';
document.frmNocPassport.rdoOfficialDealing[1].checked="true";
document.frmNocPassport.txtFromDateVisit.readOnly=true;	
document.frmNocPassport.toDateVisit.readOnly=true;
document.frmNocPassport.txtFromDate.readOnly=true;
document.frmNocPassport.txtToDate.readOnly=true;
document.frmNocPassport.date.readOnly=true;
document.frmNocPassport.dtKnownSince.readOnly=true;
</script>

<%
	}

	catch (Exception e) 
	{
		e.printStackTrace();
	}
%>
		