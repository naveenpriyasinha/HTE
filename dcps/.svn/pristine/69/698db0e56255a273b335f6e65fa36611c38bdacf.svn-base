
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="todaysDate" value="${resultValue.todaysDate}"></c:set>
<c:set var="country" value="${resultValue.visitingCountry}"></c:set>
<c:set var="prevNOCList" value="${resultValue.prevNOCList}"></c:set>

<c:set var="objProofDtl" value="${resultValue.objProofDtl}"></c:set> <!-- added for NOC CR --> 
<c:set var="pastFvisit" value="${resultValue.pastFvisitList}"></c:set> <!-- added for NOC CR -->
<c:set var="pastFvisitNOCList" value="${resultValue.pastFvisitNOCList}"></c:set> <!-- added for NOC CR -->
<c:set var="arHrEssForeignTripDtl" value="${resultValue.arHrEssForeignTripDtl}"></c:set> <!-- added for NOC CR -->
<c:set var="arForeignTripDebarDtl" value="${resultValue.arForeignTripDebarDtl}"></c:set> <!-- added for NOC CR -->
<c:set var="voToXmllstObj" value="${resultValue.voToXmllstObj}"></c:set> <!-- added for NOC CR -->

<fmt:formatDate var="todayDate"  pattern="dd/MM/yyyy" value="${todaysDate}" type="date"/>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables"	scope="request" />
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request" />

<html>
<head>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/nocForForeignVisitForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>

<script type="text/javascript">		
	
	var addClickedFlag = false;
	var debaredValue = "";
	var debaredOption = "";
	var globalflagForDBRecord;
	var jrnyRowId = "";
	
	var nocForeignVisitAlert = new Array();
	nocForeignVisitAlert[1]="<fmt:message bundle='${NOCAlerts}' key='NOC.PlzSaveExp'/>";
	nocForeignVisitAlert[2]="<fmt:message bundle='${NOCAlerts}' key='NOC.GreaterThanTodaysDate'/>";
	nocForeignVisitAlert[3]="<fmt:message bundle='${NOCAlerts}' key='NOC.VDate'/>";
	nocForeignVisitAlert[4]="<fmt:message bundle='${NOCLables}' key='NOC.Years'/>";
	nocForeignVisitAlert[5]="<fmt:message bundle='${NOCLables}' key='NOC.Months'/>";
	nocForeignVisitAlert[6]="<fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
	nocForeignVisitAlert[7]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.StartDtFirst'/>";
	nocForeignVisitAlert[8]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.EndDtFirst'/>";
	nocForeignVisitAlert[9]="<fmt:message bundle='${NOCAlerts}' key='NOC.AddPrevNocFirst'/>";
	nocForeignVisitAlert[10]="<fmt:message bundle='${NOCAlerts}' key='NOC.AddOneJourney'/>";
	nocForeignVisitAlert[11]="<fmt:message bundle='${NOCAlerts}' key='NOC.RestrictSamePlaces'/>";
	nocForeignVisitAlert[12]="<fmt:message bundle='${NOCAlerts}' key='NOC.LessThanTodaysDate'/>";
	nocForeignVisitAlert[13]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.JournyStrtWithinTrip'/>";
	nocForeignVisitAlert[14]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.JournyEndWithinTrip'/>";
	nocForeignVisitAlert[15]="<fmt:message bundle='${NOCAlerts}' key='NOC.WithinTrip'/>";
	nocForeignVisitAlert[16]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.StartDateOverlap'/>";
	nocForeignVisitAlert[17]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.EndDateOverlap'/>";
	nocForeignVisitAlert[18]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.OverLap'/>";
	nocForeignVisitAlert[19]="<fmt:message bundle='${NOCAlerts}' key='NOC.VnocDate'/>";
	 
	
	var todayDt ='${todayDate}';
	var prevNOCLst ='${prevNOCList}';
	
	</script>

</head>
<body>

<hdiits:form name="frmNocForeignVisit" validate="true" method="POST" action="./hrms.htm?actionFlag=getNocForeign">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="NOC.NocForeignHeader" bundle="${NOCLables}" /></a></li>
			<li><a href="#" rel="tcontent2"><fmt:message key="NOC.PrevNocDtls" bundle="${NOCLables}" /></a></li>
			<li><a href="#" rel="tcontent3"><fmt:message key="NOC.DebarDtl" bundle="${NOCLables}" /></a></li>
		</ul>
	</div>
	

<div id="NOCDtls" name="NOCDtls">

	<div id="tcontent1" class="tabcontent" tabno="0" >
	<%@ include	file="../../eis/empInfo/EmployeeInfo.jsp"%>



<hdiits:fieldGroup titleCaptionId="NOC.SecVisitDetails" bundle="${NOCLables}" id="ForeignVisitTripInfo" mandatory="true">	
	<!-- <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecVisitDetails"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.TripStart" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:dateTime name="dtTFromDate" captionid="NOC.TripStart" bundle="${NOCLables}" afterDateSelect="showDuration();" validation="txt.isrequired" mandatory="true" maxvalue="31/12/2099"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.TripEnd"
				bundle="${NOCLables}" /></td>
			<td><hdiits:dateTime name="dtTToDate" captionid="NOC.TripEnd" bundle="${NOCLables}" afterDateSelect="showDuration();" validation="txt.isrequired" mandatory="true"  maxvalue="31/12/2099"/></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.Duration"
				bundle="${NOCLables}" /></td>
			<td><hdiits:text name="txtDuration" id="txtDuration"
				readonly="true" /></td>
			<td><hdiits:caption captionid="NOC.NocPurposeVisit"
				bundle="${NOCLables}" /></td>
			<td><hdiits:textarea name="txtaPurposeOfVisit"
				captionid="NOC.NocPurposeVisit" bundle="${NOCLables}" rows="3" cols="30"
				validation="txt.isrequired" mandatory="true" maxlength="200"/></td>
		</tr>
	</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecTripDetails" bundle="${NOCLables}" id="ForeignVisitJourneyInfo" mandatory="true">	

	<!--<table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecTripDetails"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%" border="0">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.StartDate" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:dateTime name="dtJStartDate" captionid="NOC.JourneyStart" bundle="${NOCLables}" onblur="checkStartEndDates(this);" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="31/12/2099" /></td><!-- changed by divya for NOC CR -->
			<td width="20%"><hdiits:caption captionid="NOC.EndDate" bundle="${NOCLables}" /></td>
			<td><hdiits:dateTime name="dtJEndDate" captionid="NOC.JourneyEnd" bundle="${NOCLables}" onblur="checkStartEndDates(this);" validation="txt.isdt,txt.isrequired" mandatory="true" maxvalue="31/12/2099"/></td><!-- changed by divya for NOC CR -->
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.FromPlace" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:search name="selFromPlace" id="selFromPlace" url="hrms.htm?actionFlag=searchCountry" captionid="NOC.FromPlace" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.ToPlace" bundle="${NOCLables}" /></td>
			<td><hdiits:search name="selToPlace" id="selToPlace" url="hrms.htm?actionFlag=searchCountry" captionid="NOC.ToPlace" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true"/></td>
		</tr>
	</table>

	<table width="100%">
		<tr>
			<th class="fieldLabel"><b><u><fmt:message key="NOC.SecContact" bundle="${NOCLables}" /></u></b></th>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.PhoneRes" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:text name="numPhoneResCtry" id="numPhoneResCtry" captionid="NOC.PhoneRes" bundle="${NOCLables}" validation="txt.isnumber" size="2" onkeypress="return checkDecimalNumber()" maxlength="5" style="text-align: right"  /> - <hdiits:text	name="numPhoneResArea" captionid="NOC.PhoneRes" bundle="${NOCLables}" validation="txt.isnumber" id="numPhoneResArea" size="2" onkeypress="return checkDecimalNumber()" maxlength="5" style="text-align: right"/> - <hdiits:text name="numPhoneRes" id="numPhoneRes" captionid="NOC.PhoneRes" bundle="${NOCLables}" validation="txt.isnumber" size="2" onkeypress="return checkDecimalNumber()" maxlength="9" style="text-align: right"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.PhoneOff" bundle="${NOCLables}" /></td>
			<td><hdiits:text name="numPhoneOffCtry" id="numPhoneOffCtry" size="2" captionid="NOC.PhoneOff" bundle="${NOCLables}" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="5" style="text-align: right" /> - <hdiits:text name="numPhoneOffArea" id="numPhoneOffArea" size="2" captionid="NOC.PhoneOff" bundle="${NOCLables}" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="5" style="text-align: right"/> - <hdiits:text name="numPhoneOff" id="numPhoneOff" size="2" captionid="NOC.PhoneOff" bundle="${NOCLables}" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="9" style="text-align: right"/></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.Mobile" bundle="${NOCLables}" /></td>
			<td><hdiits:text name="numMobile" id="numMobile" captionid="NOC.Mobile" bundle="${NOCLables}" validation="txt.isnumber" onkeypress="return checkDecimalNumber()" maxlength="10" style="text-align: right"/></td>
			<td><hdiits:caption captionid="NOC.Fax" bundle="${NOCLables}" /></td>
			<td><hdiits:text name="numFax" id="numFax" captionid="NOC.Fax"
				bundle="${NOCLables}" onkeypress="return checkDecimalNumber()" maxlength="15" validation="txt.isnumber" style="text-align: right"/></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.Email" bundle="${NOCLables}" /></td>
			<td><hdiits:text caption="email" name="txtEmail" id="txtEmail"
				captionid="NOC.Email" bundle="${NOCLables}"
				validation="txt.isrequired,txt.email" mandatory="true" maxlength="50"/></td>
		</tr>
	</table>

	<table width="100%">
		<tr>
			<td><jsp:include page="../../../common/address.jsp">
				<jsp:param name="addrName" value="contactAddress" />
				<jsp:param name="addressTitle" value="" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
	<br>
	<table width="100%">
		<tr>
			<th class="fieldLabel"><b><u><fmt:message key="NOC.SecExpenditureDetails" bundle="${NOCLables}" /></u></b></th>
		</tr>
	</table>

	<%@ include file="Expenditure.jsp"%> <br>
	<table align="center" id="journeyButtons" width="100%">
		<tr>
			<td>
			<center><hdiits:button type="button" name="btnAddJourney"
				captionid="NOC.Add" bundle="${NOCLables}" title="Add Journey Record"
				onclick="javascript:addOrUpdateNOCJourney('addNOCJourneyRecord', 'addNOCJourney');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateJourney"
				title="Update Journey Record" captionid="NOC.Update"
				bundle="${NOCLables}"
				onclick="javascript:addOrUpdateNOCJourney('updateNOCJourneyRecord', 'addNOCJourney');"
				style="display:none" /> &nbsp;&nbsp;<hdiits:button type="button"
				name="btnResetJourney" id="Reset1" captionid="NOC.Reset"
				bundle="${NOCLables}" onclick="resetJourneyData()"
				title="Reset Journey Record"></hdiits:button></center>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
			<table id='JourneyTable' name="JourneyTable" border="1"
				borderColor="black" align="center" width="100%" cellpadding="1"
				cellspacing="1"
				style="border-collapse: collapse;background-color: ${tableBGColor}"
				style="display:none">
				<tr>
					<td style="display:none">&nbsp;</td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.StartDate" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.EndDate" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.FromPlace" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.ToPlace" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
</div>
<div id="tcontent2" class="tabcontent" tabno="1">	
<!-- added for NOC CR(starts) -->

<hdiits:fieldGroup titleCaptionId="NOC.PPDetails" bundle="${NOCLables}" id="passPortDtlsFieldGroup">	
	<!-- <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.PPDetails" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table> -->
	
	<c:if test="${objProofDtl.srNo ne '' && objProofDtl.srNo ne 0}" >
		<table width="100%">
			<tr>
				<td width="20%"><hdiits:caption  captionid="NOC.PPNo" bundle="${NOCLables}"/></td>
				<td width="33%">${objProofDtl.proofNum}</td>
				<td width="25%"></td>
			</tr>
			<tr>
				<td width="20%"><hdiits:caption captionid="NOC.PPIssueDate" bundle="${NOCLables}"/></td>
				<td width="33%"><fmt:formatDate var="ppIssueDate" pattern="dd/MM/yyyy" value="${objProofDtl.issueDate}" type="date" />${ppIssueDate}</td>
				<td width="25%"><hdiits:caption captionid="NOC.PPExpiryDate" bundle="${NOCLables}"/></td>
				<td><fmt:formatDate var="expiryDate" pattern="dd/MM/yyyy" value="${objProofDtl.expiryDate}" type="date" />${expiryDate}</td>
			</tr>
			<tr>
				<td width="20%"><hdiits:caption captionid="NOC.IssuePlace" bundle="${NOCLables}"/></td>
				<td width="33%">${objProofDtl.issuePlace}</td>
				<td width="25%"><hdiits:caption captionid="NOC.IssueAuth" bundle="${NOCLables}"/></td>
				<td>${objProofDtl.issueAuthority}</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${objProofDtl.srNo eq '' || objProofDtl.srNo eq 0}" >
	<br>
		<table width="100%">
			<tr width="100%"> 
				<td><center><b><fmt:message key="NOC.NtAvailable" bundle="${NOCLables}"></fmt:message></b></center></td>
			</tr>
		</table>
	<br>
	</c:if>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecPastForeign" bundle="${NOCLables}" id="PastForeignVisitInfo">	
<!-- 
<table bgcolor="#386CB7" align="center"  width="100%">
	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPastForeign"></fmt:message></u></strong></font></td>
    </tr> 
</table>
 -->
<table width="100%" >
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.NocAbroad" bundle="${NOCLables}"/></td>
		<td width="15%"><hdiits:radio name="rdoNocVisitedCountry" id="rdoNocVisitedCountry" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showVisitedCountryDetails();"/>
			<hdiits:radio name="rdoNocVisitedCountry" value="No" id="rdoNocVisitedCountry" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideVisitedCountryDetails();" /></td>
		<td class="fieldLabel"><hdiits:caption captionid="NOC.OneTime" bundle="${NOCLables}"/></td>
		<td></td>	
	</tr>
</table>

<c:if test="${not empty pastFvisit}" >	
	<script>
		document.frmNocForeignVisit.rdoNocVisitedCountry[0].checked = true;
		document.frmNocForeignVisit.rdoNocVisitedCountry[0].disabled = true;
		document.frmNocForeignVisit.rdoNocVisitedCountry[1].disabled = true;
	</script>
	<table id='pastFvisitTable' name="pastFvisitTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
	<tr>		
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.FromDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.ToDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocVisitedCountry" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.ViewFullDetail" bundle="${NOCLables}"/></label></b></td>
		
	</tr>
	
	<c:forEach var="pastFvisitValue" items="${pastFvisit}">
		<tr>
			<td style="display:none">${pastFvisitValue.fileId}</td>		<!-- 7th july -->
			<td align="left"><fmt:formatDate var="fromDates"  pattern="dd/MM/yyyy" value="${pastFvisitValue.fromDate}" type="date"/>${fromDates}</td>
			<td align="left"><fmt:formatDate var="toDates"  pattern="dd/MM/yyyy" value="${pastFvisitValue.toDate}" type="date"/>${toDates}</td>
			<td align="left">
			<script>									
				var dateDiff = getDateDiffInString("${fromDates}","${toDates}");
				var dateDiffDisplay;			
				var duration = dateDiff.split("~");
				if(duration[0] != 0 && duration[1] != 0)
					dateDiffDisplay = duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " +duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
				else if(duration[0] == 0 && duration[1] != 0)
						dateDiffDisplay = duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
					 else if(duration[0] != 0 && duration[1] == 0)
							dateDiffDisplay = duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
						else if(duration[0] == 0 && duration[1] == 0)
								dateDiffDisplay = duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
							else
								dateDiffDisplay = duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " +duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
				
				document.write(dateDiffDisplay);
			</script>
			</td>
			<td align="left">
				<c:set var="strPlaces" value=""></c:set>				
				<c:forEach var="foreignJournyDtl" items="${pastFvisitValue.hrEssForeignJournyDtls}">								
					<c:set var="strPlaces" value="${strPlaces},${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"></c:set>	
				</c:forEach>
								
				<c:if test="${fn:length(strPlaces) gt 0}">
					<c:out value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:out> 
				</c:if>				
			</td>	
			<td align="left">${pastFvisitValue.purposeOfVisit}</td>	
			<td align="left">
				<c:set var="check" value="yesDetail"></c:set>
				<c:forEach var="foreignJournyDtl" items="${pastFvisitValue.hrEssForeignJournyDtls}">								
					<c:if test="${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryId eq foreignJournyDtl.cmnCountryMstByCmnCtryMstToCtryIdFk.countryId}">	
						<c:set var="check" value="noDetail"></c:set>
					</c:if>
				</c:forEach>
				<c:if test="${check eq 'yesDetail'}">
					<a href="javascript:void(0)" onclick="visitDetails(this)"><fmt:message key="NOC.DtlOfVisit" bundle="${NOCLables}"></fmt:message></a>
				</c:if>
				<c:if test="${check eq 'noDetail'}">
					<fmt:message key="NOC.NtAvailable" bundle="${NOCLables}"></fmt:message>
				</c:if>
			</td>				
		</tr>
	</c:forEach>
	
</table>
</c:if>

<c:if test="${empty pastFvisit}" >
	<script>
		document.frmNocForeignVisit.rdoNocVisitedCountry[1].checked = true;
		document.frmNocForeignVisit.rdoNocVisitedCountry[0].disabled = false;
		document.frmNocForeignVisit.rdoNocVisitedCountry[1].disabled = false;		
	</script>
	
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
<script>
	document.frmNocForeignVisit.txtFromDate.readOnly=true;
	document.frmNocForeignVisit.txtToDate.readOnly=true;
</script>

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
	
	
</c:if>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.HeaderPrevNocFvisit" bundle="${NOCLables}" id="PrevNocFvist" collapseOnLoad="true">	

<!-- added for NOC CR starts -->
	<!-- <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.HeaderPrevNocFvisit" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>
 -->
	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.IfIssuedNocFv" bundle="${NOCLables}"/></td>
			<td width="15%"><hdiits:radio name="rdoNocFvisitIssued" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showNocFvisitTextBoxes();" /> 
							<hdiits:radio name="rdoNocFvisitIssued" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideNocFvisit();" /></td>
			<td class="fieldLabel"><hdiits:caption captionid="NOC.OneTime" bundle="${NOCLables}"/></td>
			<td></td>
		</tr>
	</table>

	<c:if test="${empty pastFvisitNOCList=='false'}">
		<script>
		document.frmNocForeignVisit.rdoNocFvisitIssued[0].checked = true;
		document.frmNocForeignVisit.rdoNocFvisitIssued[0].disabled = true;
		document.frmNocForeignVisit.rdoNocFvisitIssued[1].disabled = true;
	</script>

		<table id='prevNocFvisitDBTable' name="prevNocFvisitDBTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
			<tr>
				<td style="display:none">&nbsp;</td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.IsDebarred" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></label></b></td>
			</tr>
			<c:forEach var="pastFvisitNOCValue" items="${pastFvisitNOCList}">			
				<tr>
					<td align="left"><fmt:formatDate var="nocIssueDate" pattern="dd/MM/yyyy" value="${pastFvisitNOCValue.nocIssueDate}" type="date" />${nocIssueDate}</td>
					<td align="left">${pastFvisitNOCValue.appoverName}</td>
					<td align="left">${pastFvisitNOCValue.approverDsgnId.dsgnName}</td>					
					<td align="left">${pastFvisitNOCValue.orderNo}</td> 
					<c:if test="${pastFvisitNOCValue.whetherDebarred ne 89}">							
						<c:if test="${pastFvisitNOCValue.whetherDebarred ne 78}">						
							<td align="left">--</td>
							<td align="left">--</td>
							<td align="left">--</td>
						</c:if>
					</c:if> 
					<td align="left">					
						<c:if test="${pastFvisitNOCValue.whetherDebarred eq 89}">						
							<fmt:message key="NOC.NocYes" bundle="${NOCLables}"/>
						</c:if> 
						<c:if test="${pastFvisitNOCValue.whetherDebarred eq 78}">						
							<fmt:message key="NOC.NocNo" bundle="${NOCLables}"/>
						</c:if>
					</td>
					<c:if test="${pastFvisitNOCValue.whetherDebarred eq 89}">						
						<td align="left">${pastFvisitNOCValue.debarredBy}</td>
						<td align="left"><fmt:formatDate var="debarDate" pattern="dd/MM/yyyy" value="${pastFvisitNOCValue.debarDate}" type="date" />${debarDate}</td>
					</c:if>
					<c:if test="${pastFvisitNOCValue.whetherDebarred eq 78}">						
						<td align="left">--</td>
						<td align="left">--</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>

	</c:if> 
	<c:if test="${empty pastFvisitNOCList=='true'}">
		<script>
			document.frmNocForeignVisit.rdoNocFvisitIssued[1].checked = true;
			document.frmNocForeignVisit.rdoNocFvisitIssued[0].disabled = false;
			document.frmNocForeignVisit.rdoNocFvisitIssued[1].disabled = false;
		</script>
	</c:if>

	<table width="100%" id="dispNocFvisitIfYes" style="display:none">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.ApprovalDate" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:dateTime name="dateFvisit" captionid="NOC.ApprovalDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="checkWithTodaysDate();"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}" /></td>
			<td ><hdiits:search name="txtFvisitApprover" id="txtFvisitApprover" captionid="NOC.Approved" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true" /></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:text name="txtFvisitDesig" captionid="NOC.Designation" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true" />
			<hdiits:hidden name="hdnFvisitDesigId" id="hdnFvisitDesigId" /></td>
			<td><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}" /></td>
			<td><hdiits:text name="txtFvisitOrderNo" captionid="NOC.OrderNo" bundle="${NOCLables}" maxlength="20" validation="txt.isrequired" mandatory="true" /></td>
		</tr>
		<tr>
			<td><br>
			</td>
		</tr>
		
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.PastDebar" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:radio name="rdoDebarHst" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showDebarrDtlsHst();" /> 
						<hdiits:radio name="rdoDebarHst" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideDebarrDtlsHst();" /></td>
		<td></td>
		<td></td>
	</tr>
	<tr id="debarrRow1Hst" style="display:none">		
		<td width="20%"><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:dateTime name="dtDebarredOnHst" captionid="NOC.NocPurposeVisit" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true"/></td>
		<td width="20%"></td>
		<td></td>
	<tr id="debarrRow2Hst" style="display:none">		
		<td width="20%"><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:textarea name="txtaDebarredByHst" captionid="NOC.DebarBy" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>
		<td width="20%"><hdiits:caption captionid="NOC.Reason" bundle="${NOCLables}" /></td>
		<td><hdiits:textarea name="txtaReasonHst" captionid="NOC.Reason" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>
	</tr>	
</table>
<table width="100%" id="PrevFvisitNOCButtons" style="display:none">
	<tr align="center">
		<td colspan="4" align="center">
			<center><hdiits:button type="button" name="btnAddPrevFvisitNOC" captionid="NOC.Add" bundle="${NOCLables}" onclick="javascript:addOrUpdatePrevFvisitNOC('addPrevFvisitRecord', 'addPrevFvisit');" />
				&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdatePrevFvisitNOC" captionid="NOC.Update" bundle="${NOCLables}" style="display:none" onclick="javascript:addOrUpdatePrevFvisitNOC('updatePrevFvisitRecord', 'addPrevFvisit');" />
				&nbsp;&nbsp;<hdiits:button type="button" name="btnResetPrevFvisitNOC" captionid="NOC.Reset" bundle="${NOCLables}" onclick="resetPrevFvisitNOCData()" title="Reset Record"></hdiits:button>
			</center>
		</td>
	</tr>
</table>
<table id='prevFvisitNOCTable' name="prevFvisitNOCTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" style="display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}" /></label></b></td>			
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.IsDebarred" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
		</tr>
	</table>
<!-- added for NOC CR ends -->
</hdiits:fieldGroup>
<br>	
<hdiits:fieldGroup titleCaptionId="NOC.SecDetailsOfNOC" bundle="${NOCLables}" id="PrevNocPassport" collapseOnLoad="true">	

<!-- added for NOC CR(ends)
	<table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecDetailsOfNOC"></fmt:message></u></strong></font></td>
		</tr>
	</table>
 -->
	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.NocPast" bundle="${NOCLables}" /></td>
			<td width="15%"><hdiits:radio name="rdoNocIssued" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}"
				onclick="showTextBoxes();" /> <hdiits:radio name="rdoNocIssued" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hide();" /></td>
			<td class="fieldLabel"><hdiits:caption captionid="NOC.OneTime" bundle="${NOCLables}"/></td>
			<td></td>
		</tr>
	</table>

	<c:if test="${empty prevNOCList=='false'}">
		<script>
			document.frmNocForeignVisit.rdoNocIssued[0].checked = true;
			document.frmNocForeignVisit.rdoNocIssued[0].disabled = true;
			document.frmNocForeignVisit.rdoNocIssued[1].disabled = true;
		</script>

		<table id='prevNocDBTable' name="prevNocDBTable" border=1
			borderColor="black" align="center" width="100%" cellpadding="1"
			cellspacing="1"
			style="border-collapse: collapse;background-color: ${tableBGColor}">
			<tr>
				<td style="display:none">&nbsp;</td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
					captionid="NOC.NocDate" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
					captionid="NOC.Approved" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
					captionid="NOC.Designation" bundle="${NOCLables}" /></label></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
					captionid="NOC.OrderNo" bundle="${NOCLables}" /></label></b></td>
			</tr>
			<c:forEach var="prevNocValue" items="${prevNOCList}">
				<tr>
					<td align="left"><fmt:formatDate var="nocIssueDate"
						pattern="dd/MM/yyyy" value="${prevNocValue.nocIssueDate}"
						type="date" />${nocIssueDate}</td>
					<td align="left">${prevNocValue.appoverName}</td>
					<td align="left">${prevNocValue.approverDesignationId.dsgnName}</td>
					<td align="left">${prevNocValue.orderNo}</td>
				</tr>
			</c:forEach>
		</table>

	</c:if> <c:if test="${empty prevNOCList=='true'}">
		<script>
			document.frmNocForeignVisit.rdoNocIssued[1].checked = true;
			document.frmNocForeignVisit.rdoNocIssued[0].disabled = false;
			document.frmNocForeignVisit.rdoNocIssued[1].disabled = false;
		</script>
	</c:if>

	<table width="100%" id="displayIfYes" style="display:none">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.ApprovalDate" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:dateTime name="date" captionid="NOC.ApprovalDate" bundle="${NOCLables}" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="checkWithTodaysDate();"/></td>
			<td width="20%"><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}" /></td>
			<td><hdiits:search name="txtApprover" id="txtApprover" captionid="NOC.Approved" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true" /></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:text name="txtDesignation" captionid="NOC.Designation" bundle="${NOCLables}" readonly="true" validation="txt.isrequired" mandatory="true" />
			<hdiits:hidden name="hdnDesignationId" id="hdnDesignationId" /></td>
			<td width="20%"><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}" /></td>
			<td><hdiits:text name="txtOrderNo" captionid="NOC.OrderNo" bundle="${NOCLables}" maxlength="20" validation="txt.isrequired" mandatory="true" /></td>
		</tr>
		<tr>
			<td><br>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
			<center><hdiits:button type="button" name="btnAddPrevNOC"
				captionid="NOC.Add" bundle="${NOCLables}"
				onclick="javascript:addOrUpdatePrevNOC('addPrevNOCRecord', 'addPrevNOC');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdatePrevNOC"
				captionid="NOC.Update" bundle="${NOCLables}" style="display:none"
				onclick="javascript:addOrUpdatePrevNOC('updatePrevNOCRecord', 'addPrevNOC');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnResetPrevNOC"
				id="Reset" captionid="NOC.Reset" bundle="${NOCLables}"
				onclick="resetPrevNOCData()" title="Reset Record"></hdiits:button></center>
			</td>
		</tr>
	</table>

	<table id='prevNOCTable' name="prevNOCTable" border=1
		borderColor="black" align="center" width="100%" cellpadding="1"
		cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor}"
		style="display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.NocDate" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Approved" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Designation" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.OrderNo" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
<br>
</div>
<div id="tcontent3" class="tabcontent" tabno="2">	
<!-- added for NOC CR starts -->
 <div id="displayDebarHstTitle">
 <hdiits:fieldGroup titleCaptionId="NOC.DebarDtl" bundle="${NOCLables}">	 
<!--  	    
<table bgcolor="#386CB7" align="center" width="100%" id="displayDebarHstTitle">
	<tr align="center">
		<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.DebarDtl" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
	</tr>
</table>
 -->
<br>
<table id="displayDebarHst" >
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.SelTripDebar" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:select name="selDebarTrip" id="selDebarTrip" captionid="NOC.SelTripDebar" bundle="${NOCLables}" size="1" sort="false" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0"><fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/></hdiits:option>
						<c:forEach var="ForeignTripValue" items="${arHrEssForeignTripDtl}">
							<fmt:formatDate var="frmDate" pattern="dd/MM/yyyy" value="${ForeignTripValue.fromDate}" type="date" />
							<fmt:formatDate var="toDate" pattern="dd/MM/yyyy" value="${ForeignTripValue.toDate}" type="date" />
							<c:set var="strPlaces" value=""></c:set>
							<c:forEach var="foreignJournyDtl" items="${ForeignTripValue.hrEssForeignJournyDtls}">
								<c:set var="strPlaces" value="${strPlaces},${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"></c:set>
							</c:forEach>
							<c:if test="${fn:length(strPlaces) gt 0}">
								<c:set var="places" value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:set>
							</c:if>							
					<hdiits:option value="${ForeignTripValue.hrEssForeignTripPk}">${frmDate} to ${toDate} ${places}</hdiits:option>
					
					</c:forEach>				
			</hdiits:select></td>
	</tr>
	<tr>
		<td width="20%"><hdiits:caption captionid="NOC.PastDebar" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:radio name="rdoDebar" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showDebarrDtls();" /> 
						<hdiits:radio name="rdoDebar" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideDebarrDtls();" /></td>
		<td></td>
		<td></td>
	</tr>
	<tr id="debarrRow1" style="display:none">		
		<td width="20%"><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:dateTime name="dtDebarredOn" captionid="NOC.DebarDate" bundle="${NOCLables}" validation="txt.isrequired" mandatory="true"/></td>
		<td width="20%"></td>
		<td></td>
	</tr>
	<tr id="debarrRow2" style="display:none">		
		<td width="20%"><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></td>
		<td width="33%"><hdiits:textarea name="txtaDebarredBy" captionid="NOC.DebarBy" bundle="${NOCLables}" rows="5" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>		
		<td width="20%"><hdiits:caption captionid="NOC.Reason" bundle="${NOCLables}" /></td>
		<td><hdiits:textarea name="txtaReason" captionid="NOC.Reason" bundle="${NOCLables}" rows="3" cols="30" validation="txt.isrequired" mandatory="true" maxlength="200"/></td>
	</tr>
	<tr align="center">
		<td colspan="4" align="center">
		<center><hdiits:button type="button" name="btnAddDebarDtl" captionid="NOC.Add" bundle="${NOCLables}" onclick="javascript:addOrUpdateDebarDtl('addPrevDebarDtl', 'addDebarDtl');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateDebarDtl" captionid="NOC.Update" bundle="${NOCLables}" style="display:none" onclick="javascript:addOrUpdateDebarDtl('updatePrevDebarDtl', 'addDebarDtl');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnResetDebarDtl" id="Reset" captionid="NOC.Reset" bundle="${NOCLables}" onclick="resetPrevDebarDtl()" title="Reset Record"></hdiits:button></center>
		</td>
	</tr>
</table>
<br>
<c:if test="${empty arHrEssForeignTripDtl}">
	<script>
		document.getElementById("displayDebarHst").style.display = 'none';
		document.getElementById("displayDebarHstTitle").style.display = 'none';
	</script>
</c:if>

<table width="100%" id="prevDebarTable" border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" style="display:none">
	<tr>
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Trip" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.IsDebarred" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Reason" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
	</tr>
</table>
<table id="debarNorecord" style="display:none" align="center" width="85%">
	<tr><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
</table>
<table>
	<tr>
		<td>
			<c:if test="${not empty arForeignTripDebarDtl}">
				<c:forEach items="${arForeignTripDebarDtl}" var="foreignTripDebarDtl" varStatus="x">
					<c:set var="curXMLFileName" value="${voToXmllstObj[x.index]}"></c:set>		
					<c:set var="trip" value="${foreignTripDebarDtl.tripDesc}" />
						<c:if test="${foreignTripDebarDtl.whetherDebarred eq 89}">						
							<c:set var="isDebarred" value='Yes' ></c:set>
							<c:set var="debarredBy" value="${foreignTripDebarDtl.debarredBy}" />
							<fmt:formatDate var="debarDate" pattern="dd/MM/yyyy" value="${foreignTripDebarDtl.debarDate}" type="date" />
							<c:set var="debarredOn" value="${debarDate}" />
							<c:set var="debarReason" value="${foreignTripDebarDtl.debarReason}" />
						</c:if> 
						<c:if test="${foreignTripDebarDtl.whetherDebarred eq 78}">						
							<c:set var="isDebarred" value='No' ></c:set>
							<c:set var="debarredBy" value="--" />							
							<c:set var="debarredOn" value="--" />
							<c:set var="debarReason" value="--" />
						</c:if>
					
					<script type="text/javascript">			
						var xmlFileName = '${curXMLFileName}';					
						var displayFieldA  = new Array('${trip}', '${isDebarred}', '${debarredBy}','${debarredOn}','${debarReason}');
						addDBDataInTable('prevDebarTable','encDBdebarXML',displayFieldA,xmlFileName,'editDBdebar');
						document.getElementById("displayDebarHstTitle").style.display = '';						
					</script>
				</c:forEach>
			</c:if>
			<c:if test="${empty arForeignTripDebarDtl}">
				<script type="text/javascript">	
					document.getElementById("displayDebarHstTitle").style.display = '';	
					document.getElementById("prevDebarTable").style.display = '';	
					document.getElementById("debarNorecord").style.display = '';	
				</script>
			</c:if>
		</td>
</tr>
</table>
</hdiits:fieldGroup>
</div>
  
</div>
</div>
<!-- added for NOC CR ends -->
	<table align="center" width="100%">
		<tr align="center">
			<td>
			<center><hdiits:button type="button" name="btnSubmit" captionid="NOC.Submit" bundle="${NOCLables}" onclick="submitNocForeignDtls();" /> 
					&nbsp;&nbsp;<hdiits:button	type="button" name="btnClose" id="btnClose" title="Close" captionid="NOC.Close" bundle="${NOCLables}" onclick="closePage();" />
			</center>
			</td>
		</tr>
	</table>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:validate locale="${locale}"
		controlNames="dtTFromDate,dtTToDate,txtaPurposeOfVisit,dtJStartDate,dtJEndDate,name_selFromPlace,name_selToPlace,txtEmail,selBearer,selBearerName,numCategorisedAmount,date,name_txtApprover,txtDesignation,txtOrderNo" />
</hdiits:form>

<script>
initializetabcontent("maintab");
document.getElementById("displayAmtLabel").innerText = '<fmt:message bundle="${NOCLables}" key="NOC.Expense"/>';
document.frmNocForeignVisit.rdoOfficialDealing[1].checked = true;
document.frmNocForeignVisit.rdoDebar[1].checked=true; // noc cr
document.frmNocForeignVisit.rdoDebarHst[1].checked = true; // noc cr 
document.frmNocForeignVisit.dtDebarredOnHst.readOnly=true; // noc cr 
document.frmNocForeignVisit.dtDebarredOn.readOnly=true; // noc cr 
// document.frmNocForeignVisit.rdoNocFvisitIssuedHst[1].checked = true; // noc cr 
document.frmNocForeignVisit.dtTFromDate.readOnly=true;
document.frmNocForeignVisit.dtTToDate.readOnly=true;
document.frmNocForeignVisit.dtJStartDate.readOnly=true;
document.frmNocForeignVisit.dtJEndDate.readOnly=true;
document.frmNocForeignVisit.date.readOnly=true;
document.frmNocForeignVisit.dtKnownSince.readOnly=true;
document.frmNocForeignVisit.dateFvisit.readOnly=true;
</script>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
