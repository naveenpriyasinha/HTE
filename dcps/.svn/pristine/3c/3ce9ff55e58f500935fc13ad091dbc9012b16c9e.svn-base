
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="mode" value="${resValue.nocPaymentMode}"></c:set>
<c:set var="expCategory" value="${resValue.Category}"></c:set>
<c:set var="arAgencyList" value="${resValue.AgencyList}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="arHrEssForeignTripDtl" value="${resValue.arHrEssForeignTripDtl}"></c:set>
<c:set var="hrEssForeignTripDtl" value="${resValue.hrEssForeignTripDtl}"></c:set>
<c:set var="isInboxJsp" value="${resValue.isInboxJsp}"></c:set>
<c:set var="voToXmllstObj" value="${resValue.voToXmllstObj}"></c:set>
<c:set var="hrEssForeignJournyDtlVOList" value="${resValue.hrEssForeignJournyDtlVOList}"></c:set>
<c:set var="reqId" value="${resValue.lngRequestId}"></c:set>
<c:set var="statusFlag" value="${hrEssForeignTripDtl.statusFlag}"></c:set>
<c:set var="todaysDate" value="${resValue.todaysDate}"></c:set>
<c:set var="selectedTripPk" value="${resValue.tripPk}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="isViewDetails" value="${resValue.isViewDetails}"></c:set>

<fmt:formatDate var="todayDate" pattern="dd/MM/yyyy" value="${todaysDate}" type="date" />

<fmt:setBundle basename="resources.ess.noc.FVISIT" var="FVISITLables" scope="request" />
<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request" />
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request" />

<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/visitReport.js"/>"></script>
<script type="text/javascript">
	var clickedOnAddBtn = false;
	var updateJournyRowId = "";
	var updateExpRowId = "";
	var updateAgentRowId = "";
	var totalExpenditure = 0;
	var currentRowId = "";
	var isJounryRecordInUpdate = false;
	// var isOtherRecordInUpdate = false;
	
	var visitReportArray = new Array();
	visitReportArray[0]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.StartDtFirst'/>";
	visitReportArray[1]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.EndDtFirst'/>";
	visitReportArray[2]="<fmt:message bundle='${NOCAlerts}' key='NOC.LessThanTodaysDate'/>";
	visitReportArray[3]="<fmt:message bundle='${NOCAlerts}' key='NOC.VDate'/>";
	visitReportArray[4]="<fmt:message bundle='${NOCLables}' key='NOC.Years'/>";
	visitReportArray[5]="<fmt:message bundle='${NOCLables}' key='NOC.Months'/>";
	visitReportArray[6]="<fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
	visitReportArray[7]="<fmt:message bundle='${NOCAlerts}'key='FVISIT.SaveAgency'/>";
	visitReportArray[8]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.Confirm'/>";
	visitReportArray[9]="<fmt:message bundle='${NOCAlerts}' key='NOC.RestrictSamePlaces'/>";
	visitReportArray[10]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.CantDelete'/>";
	visitReportArray[11]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.CantEdit'/>";
	visitReportArray[12]="<fmt:message bundle='${NOCAlerts}' key='NOC.AddOneJourney'/>";
	visitReportArray[13]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.JournyStrtWithinTrip'/>";
	visitReportArray[14]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.JournyEndWithinTrip'/>";
	visitReportArray[15]="<fmt:message bundle='${NOCAlerts}' key='NOC.WithinTrip'/>";
	visitReportArray[16]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.StartDateOverlap'/>";
	visitReportArray[17]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.EndDateOverlap'/>";
	visitReportArray[18]="<fmt:message bundle='${NOCAlerts}' key='FVISIT.OverLap'/>";
	visitReportArray[19]="<fmt:message bundle='${NOCAlerts}' key='NOC.AjaxAlert'/>";
	visitReportArray[20]="<fmt:message bundle='${NOCAlerts}' key='NOC.PlzSaveExp'/>";
	visitReportArray[21]="<fmt:message  bundle='${NOCLables}' key='NOC.AddNew'/>";
    
	var inboxJsp ='${isInboxJsp}';
	var todayDt ='${todayDate}';
	var applicantUserId ='${hrEssForeignTripDtl.orgUserMstByOrgUserMstUserIdFk.userId}';
	

</script>

</head>
<body>

<hdiits:form name="frmVisitReport" validate="true" method="POST" encType="multipart/form-data"	action="hrms.htm?actionFlag=appRejFVisitReport">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="FVISIT.VisitReportHeader" bundle="${FVISITLables}" /></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">

	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include	file="../../eis/empInfo/EmployeeInfo.jsp"%>
	<div id="dropdown" style="display:none">
	<hdiits:fieldGroup titleCaptionId="FVISIT.SelForeignTrip" bundle="${FVISITLables}"  mandatory="true">	
	<!--  <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="FVISIT.SelForeignTrip" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
		</tr>
	</table>-->
	<br>
	<table align="center">
		<tr align="center">
			<td colspan="4" align="center">
			<center><hdiits:hidden name="totalExpenditure"
				id="totalExpenditure" /> <hdiits:select name="selTrip" id="selTrip"
				size="1" sort="false" onchange="showPage();">
				<hdiits:option value="0">
					<fmt:message key="FVISIT.Dropdown.Select"
						bundle="${FVISITLables}" />
				</hdiits:option>
				<c:forEach var="ForeignTripValue" items="${arHrEssForeignTripDtl}">
					<fmt:formatDate var="frmDate" pattern="dd/MM/yyyy"
						value="${ForeignTripValue.fromDate}" type="date" />
					<fmt:formatDate var="toDate" pattern="dd/MM/yyyy"
						value="${ForeignTripValue.toDate}" type="date" />
					<c:set var="strPlaces" value=""></c:set>
					<c:forEach var="foreignJournyDtl"
						items="${ForeignTripValue.hrEssForeignJournyDtls}">
						<c:set var="strPlaces"
							value="${strPlaces},${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"></c:set>
					</c:forEach>
					<c:if test="${fn:length(strPlaces) gt 0}">
						<c:set var="places"
							value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:set>
					</c:if>
					<hdiits:option value="${ForeignTripValue.hrEssForeignTripPk}">${frmDate} to ${toDate} ${places}</hdiits:option>
				</c:forEach>
				<hdiits:option value="addNew">
					<fmt:message key="FVISIT.AddNew" bundle="${FVISITLables}" />
				</hdiits:option>
			</hdiits:select></center>
			</td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</div>

	<div id="wholePage">
	<div id="leaveHeader" style="display:none" >
	<hdiits:fieldGroup titleCaptionId="FVISIT.LeaveDetail" bundle="${FVISITLables}"   id="leaveDeatilsHeader">	
	<!--  <table bgcolor="#386CB7" id="leaveHeader" align="center" width="100%" style="display:none" >
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="FVISIT.LeaveDetail" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
		</tr>
		
	</table>-->
	<table id="newLine" style="display:none"><tr><td><br></td></tr></table>
	<table id="leaveTable" border=1 borderColor="black"	align="center" width="85%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="FVISIT.LeaveType" bundle="${FVISITLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="NOC.FromDate" bundle="${NOCLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="NOC.ToDate" bundle="${NOCLables}" /></b></td>			
		</tr>
	</table>
	<table id="leaveNoRecord" style="display:none" align="center" width="85%">
		<tr><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
	</table>
	</hdiits:fieldGroup>
	</div>
	<br>
	<hdiits:fieldGroup titleCaptionId="FVISIT.SecVisitDetails" bundle="${FVISITLables}"  mandatory="true" id="secVisitdetails">	
	<!--  <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="FVISIT.SecVisitDetails" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.TripStart" bundle="${FVISITLables}" /></td>
			<td width="33%"><fmt:formatDate var="tripFrmDate" pattern="dd/MM/yyyy" value="${hrEssForeignTripDtl.fromDate}" /> 
			<hdiits:dateTime name="txtFromDate" captionid="FVISIT.TripStart" bundle="${FVISITLables}" afterDateSelect="showDuration();getLeaveDetail()"
				validation="txt.isdt,txt.isrequired" mandatory="true" disabled="${isInboxJsp}" /></td>
			<td width="25%"><hdiits:caption captionid="FVISIT.TripEnd" bundle="${FVISITLables}" /></td>
			<td><fmt:formatDate var="tripToDate" pattern="dd/MM/yyyy" value="${hrEssForeignTripDtl.toDate}" /> 
			<hdiits:dateTime name="txtToDate" captionid="FVISIT.TripEnd" bundle="${FVISITLables}" afterDateSelect="showDuration();getLeaveDetail()"
				validation="txt.isdt,txt.isrequired" mandatory="true" disabled="${isInboxJsp}" /></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.Duration" bundle="${FVISITLables}" /></td>
			<td><hdiits:text name="txtDuration" id="txtDuration" readonly="true" /></td>
			<td><hdiits:caption captionid="FVISIT.NocPurposeVisit" bundle="${FVISITLables}" /></td>
			<td><hdiits:textarea name="txtaPurposeOfVisit" id="txtaPurposeOfVisit" caption="txtaPurposeOfVisit" rows="3" cols="30" maxlength="200"/></td>
		</tr>
	</table></hdiits:fieldGroup><br>
<hdiits:fieldGroup titleCaptionId="FVISIT.SecTripDetails" bundle="${FVISITLables}"  mandatory="true" id="secTripdetails">	
	<!-- <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="FVISIT.SecTripDetails" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
		</tr>
	</table> -->

	<table width="100%" border="0">
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.StartDate"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:dateTime name="dtStartDate"
				captionid="FVISIT.JourneyStart" bundle="${FVISITLables}"
				onblur="checkJourneyStartEndDates(this);"
				validation="txt.isdt,txt.isrequired" mandatory="true"
				disabled="${isInboxJsp}" /></td><!-- changed for NOC CR -->
			<td width="25%"><hdiits:caption captionid="FVISIT.EndDate"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:dateTime name="dtEndDate"
				captionid="FVISIT.JourneyEnd" bundle="${FVISITLables}"
				onblur="checkJourneyStartEndDates(this);"
				validation="txt.isdt,txt.isrequired" mandatory="true"
				disabled="${isInboxJsp}" /></td><!-- changed for NOC CR -->
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.FromPlace"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:search name="selFromPlace"
				id="selFromPlace" url="hrms.htm?actionFlag=searchCountry"
				captionid="FVISIT.FromPlace" bundle="${FVISITLables}"
				readonly="true" validation="txt.isrequired" mandatory="true"
				disable="${isInboxJsp}" /></td>
			<td width="25%"><hdiits:caption captionid="FVISIT.ToPlace"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:search name="selToPlace" id="selToPlace"
				url="hrms.htm?actionFlag=searchCountry" captionid="FVISIT.ToPlace"
				bundle="${FVISITLables}" readonly="true" validation="txt.isrequired"
				mandatory="true" disable="${isInboxJsp}" /></td>
		</tr>
	</table>
<hdiits:fieldGroup titleCaptionId="FVISIT.AgentDetails" bundle="${FVISITLables}"   id="agentDetailsId" collapseOnLoad="true">		
	<!-- <table bgcolor="#76A9E2" align="center" width="100%">
		<tr align="left">
			<td class="fieldLabel" width="20%" valign="middle"><font color="#ffffff"><strong><u><fmt:message key="FVISIT.AgentDetails" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
			<td valign="middle"><a href="javascript:void(0);" onclick="expandCollapse();"><img id="expandAgent" src="images/expand.gif" /></a></td>
		</tr>
	</table>
	 -->
	<div id="inboxHideAgent">
	<table width="100%" id="displayAgentDetail" style="display:none">
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.Agency"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:select name="selAgencyName"
				id="selAgencyName" captionid="FVISIT.Agency"
				bundle="${FVISITLables}" size="1" sort="false"
				onchange="addAgent();" validation="sel.isrequired" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="FVISIT.Dropdown.Select"
						bundle="${FVISITLables}" />
				</hdiits:option>
				<c:forEach var="AgencyListValue" items="${arAgencyList}">
					<hdiits:option value="${AgencyListValue.organizationId}">${AgencyListValue.organizationName}</hdiits:option>
				</c:forEach>
				<hdiits:option value="addNew">
					<fmt:message key="FVISIT.AddNew" bundle="${FVISITLables}" />
				</hdiits:option>
			</hdiits:select></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<!-- New Agent -->

	<fieldset style="background: #E3E3E3;" id="displayNewAgentDetail"
		style="display:none"><legend><u><hdiits:caption
		captionid="FVISIT.NewAgent" bundle="${FVISITLables}" /></u></legend>
	<table width="100%" id="displayNewAgentAddr" style="display:none">
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.AgencyName"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:text name="txtNewAgencyName"
				id="txtNewAgencyName" captionid="FVISIT.AgencyName"
				bundle="${FVISITLables}" validation="txt.isrequired"
				mandatory="true" maxlength="50"/></td>
			<td width="25%"><hdiits:caption captionid="FVISIT.AgentName"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:text name="txtAgentName" id="txtAgentName"
				maxlength="30" /></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td><jsp:include page="../../../common/address.jsp">
				<jsp:param name="addrName" value="agencyAddress" />
				<jsp:param name="addressTitle" value="" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include></td>
		</tr>
		<tr align="center">
			<td align="center"><hdiits:button type="button"
				captionid="FVISIT.SaveNewAgent" bundle="${FVISITLables}"
				name="btnSaveNewAgent" onclick="saveNewAgent();" /></td>
		</tr>
	</table>
	<br>
	</fieldset>

	<table width="100%" id="displayPaymentDetail" style="display:none">
		<tr>
			<th class="fieldLabel"><u><b><fmt:message key="FVISIT.Payment"
				bundle="${FVISITLables}" /></b></u></th>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.Amount"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:text name="numAmount" id="numAmount" captionid="FVISIT.Amount" bundle="${FVISITLables}"
				validation="txt.isrequired" mandatory="true" maxlength="10" onkeypress="return checkNumberForOnlyOneDot(this.value)" onblur="fixTwoDecimalPt();" style="text-align: right"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.Mode"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:select name="selMode" id="selMode"
				captionid="FVISIT.Mode" bundle="${FVISITLables}" size="1"
				sort="false" onchange="showPaymentDetails();"
				validation="sel.isrequired" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="FVISIT.Dropdown.Select"
						bundle="${FVISITLables}" />
				</hdiits:option>
				<c:forEach var="modeValue" items="${mode}">
					<hdiits:option value="${modeValue.lookupName}">${modeValue.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr id="displayChequeDetails1" style="display:none">
			<td width="20%"><hdiits:caption captionid="FVISIT.Bank"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:text name="txtBank" maxlength="40"
				id="txtBank" captionid="FVISIT.Bank" bundle="${FVISITLables}"
				validation="txt.isrequired" mandatory="true" /></td>
			<td width="25%"><hdiits:caption captionid="FVISIT.Acct"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:text name="numAcct" id="numAcct"
				captionid="FVISIT.Acct" bundle="${FVISITLables}"
				validation="txt.isrequired" mandatory="true"
				onkeypress="return checkDecimalNumber();" maxlength="16" style="text-align: right"/></td>
		</tr>
		<tr id="displayChequeDetails2" style="display:none">
			<td><hdiits:caption captionid="FVISIT.ChequeNo"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:text name="numChequeNo" id="numChequeNo"
				captionid="FVISIT.ChequeNo" bundle="${FVISITLables}"
				validation="txt.isrequired" mandatory="true"
				onkeypress="return checkDecimalNumber();" maxlength="6" style="text-align: right"/></td>
		</tr>
		<tr id="displayOtherDetails" style="display:none">
			<td><hdiits:caption captionid="FVISIT.Detail"
				bundle="${FVISITLables}" /></td>
			<td><hdiits:textarea name="txtPaymentOtherDetails"
				id="txtPaymentOtherDetails" captionid="FVISIT.Detail"
				bundle="${FVISITLables}" rows="3" cols="30"
				validation="txt.isrequired" mandatory="true" maxlength="100"/></td>
		</tr>

	</table>
	
	<table align="center" id="agentButtons" style="display:none"
		width="100%">
		<tr id="agentBtns">
			<td>
			<center><hdiits:button type="button" name="btnAddAgent"
				captionid="FVISIT.Add" bundle="${FVISITLables}"
				onclick="javascript:addOrUpdateAgent('addAgentRecord', 'addAgent');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateAgent"
				captionid="FVISIT.Update" bundle="${FVISITLables}"
				onclick="javascript:addOrUpdateAgent('updateAgentRecord', 'addAgent');"
				style="display:none" /> &nbsp;&nbsp;<hdiits:button type="button"
				name="btnResetAgent" captionid="FVISIT.Reset"
				bundle="${FVISITLables}" onclick="resetAgent();" /></center>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
			<table id='agentTable' name="agentTable" border=1 borderColor="black"
				align="center" width="85%" cellpadding="1" cellspacing="1"
				style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
				<tr>
					<td style="display:none">&nbsp;</td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="FVISIT.AgencyName" bundle="${FVISITLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}" style="display:none"><b></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="FVISIT.Mode" bundle="${FVISITLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.Amount" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
						captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
	</div>
	<div id="inboxShowAgent"><br>
	<table id='inboxAgentTable' name="inboxAgentTable" border=1
		borderColor="black" align="center" width="85%" cellpadding="1"
		cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.AgencyName" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.Mode" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.Amount" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.Bank" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.Acct" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.ChequeNo" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.Detail" bundle="${FVISITLables}" /></label></b></td>
		</tr>		
	</table>
	<table id="agentNoRecord" style="display:none" align="center" width="85%">
		<tr><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
	</table>
	</div>
	</hdiits:fieldGroup>
	<hdiits:fieldGroup titleCaptionId="FVISIT.SecExpenditureDetails" bundle="${FVISITLables}"   id="expenditureDetailsId" collapseOnLoad="true">
	<!-- <table bgcolor="#76A9E2" align="center" width="100%">
		<tr align="left">
			<td class="fieldLabel" width="20%" valign="middle"><font color="#ffffff"><strong><u><fmt:message key="FVISIT.SecExpenditureDetails" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
			<td valign="middle"><a href="javascript:void(0);" onclick="expandCollapseExp();"><img id="expandExp" src="images/expand.gif" /></a></td>
		</tr>
	</table> -->
	<div id="inboxHideExp">

	<table width="100%" id="displayExpDetail" style="display:none">
		<tr>
			<td width="20%"><hdiits:caption captionid="FVISIT.Category"
				bundle="${FVISITLables}" /></td>
			<td width="33%"><hdiits:select name="selCategory"
				id="selCategory" captionid="FVISIT.Category"
				bundle="${FVISITLables}" size="1" sort="false"
				onchange="showBearerDetails();" validation="sel.isrequired"
				mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="FVISIT.Dropdown.Select" bundle="${FVISITLables}" />
				</hdiits:option>
				<c:forEach var="expCategoryValue" items="${expCategory}">
					<hdiits:option value="${expCategoryValue.lookupName}">${expCategoryValue.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td width="25%"></td>
			<td></td>
		</tr>
	</table>

	<%@ include file="../nocForForeignVisit/Expenditure.jsp"%>

	<br>

	<div id="displayBearerDetailsHeader" style="display:none">
		<hdiits:fieldGroup titleCaptionId="FVISIT.SecBearerDetails" bundle="${FVISITLables}"   id="bearerDetailsHeader" collapseOnLoad="true">
		<!--  <tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="FVISIT.SecBearerDetails" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
		</tr>
		
	</table>-->
	</hdiits:fieldGroup>
	</div>

	<table align="center" id="expenditureButtons" style="display:none"
		width="100%">
		<tr align="center" id="expBtns">
			<td>
			<center><hdiits:button type="button" name="btnAddExp"
				captionid="FVISIT.Add" bundle="${FVISITLables}"
				onclick="javascript:addOrUpdateExp('addExpRecord', 'addAgentExp');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateExp"
				captionid="FVISIT.Update" bundle="${FVISITLables}"
				onclick="javascript:addOrUpdateExp('updateExpRecord', 'addAgentExp');"
				style="display:none" /> &nbsp;&nbsp;<hdiits:button type="button"
				name="btnResetExp" id="Reset" captionid="FVISIT.Reset"
				bundle="${FVISITLables}" onclick="resetExpData()"
				></hdiits:button></center>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
			<table id='expTable' name="expTable" border=1 borderColor="black"
				align="center" width="85%" cellpadding="1" cellspacing="1"
				style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
				<tr>
					<td style="display:none">&nbsp;</td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="FVISIT.Category" bundle="${FVISITLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="FVISIT.Type" bundle="${FVISITLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.HowBearExpense" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.SelBank" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.AcctNo" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.Amount" bundle="${NOCLables}" /></label></b></td>
					<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>

	<br>
	<table id='inboxExpTable' name="inboxExpTable" border=1
		borderColor="black" align="center" width="85%" cellpadding="1"
		cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="FVISIT.Category" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="FVISIT.Type" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.HowBearExpense" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.SelBank" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="NOC.AcctNo" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Amount" bundle="${NOCLables}" /></label></b></td>
		</tr>		
	</table>
	<table id="expNorecord" style="display:none" align="center" width="85%">
		<tr ><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
	</table>
	</hdiits:fieldGroup>
	  	<!-- <table bgcolor="#76A9E2" align="center" width="100%">
		<tr align="left">
			<td class="fieldLabel" width="20%" valign="middle"><font color="#ffffff"><strong><u><fmt:message key="FVISIT.TicketAttachment" bundle='${FVISITLables}'></fmt:message></u></strong></font></td>
			<td valign="middle"><a href="javascript:void(0);" onclick="expandCollapseTicket();"><img id="expandTicket" src="images/expand.gif" /></a></td>
		</tr>
	</table>
	 -->
 

	<table width="100%" id="ticketAttachment" align="center">
		<tr>
			<td colspan="8"><!-- For attachment : Start--> 
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="ticketAttachment" />
				<jsp:param name="formName" value="frmVisitReport" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
			</jsp:include> <!-- For attachment : End--><br>
			</td>
		</tr>
	</table>
	
	<br>
	
	<hr>
	
	<table align="center" id="journeyButtons">
		<tr>
			<td><hdiits:button type="button" name="btnAddJourney" captionid="FVISIT.AddJourney" bundle="${FVISITLables}" onclick="javascript:addOrUpdateJourney('addJourneyRecord', 'addJourney');" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnUpdateJourney"
				captionid="FVISIT.UpdateJourney" bundle="${FVISITLables}"
				onclick="javascript:addOrUpdateJourney('updateJourneyRecord', 'addJourney');"
				style="display:none" /> &nbsp;&nbsp;<hdiits:button type="button"
				name="btnResetJourney" id="ResetJourney"
				captionid="FVISIT.ResetJourney" bundle="${FVISITLables}"
				onclick="resetJourneyData()"></hdiits:button></td>
		</tr>
	</table>

	<br>

	<table id='journeyTable' name="journeyTable" border=1 borderColor="black" align="center" width="100%" 
		style="border-collapse: collapse;background-color: ${tableBGColor}; display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.JStartDate" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.JEndDate" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.FromPlace" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.ToPlace" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="FVISIT.ExpOccured" bundle="${FVISITLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
		</tr>
	</table>

	<c:if test="${not empty hrEssForeignJournyDtlVOList}">
		<c:forEach var="hrEssForeignJournyDtl" items="${hrEssForeignJournyDtlVOList}" varStatus="x">
			<c:set var="curXMLFileName" value="${voToXmllstObj[x.index]}"></c:set>
			<fmt:formatDate var="journeyStartDate" pattern="dd/MM/yyyy" value="${hrEssForeignJournyDtl.fromDate}" type="date" />
			<fmt:formatDate var="journeyEndDate" pattern="dd/MM/yyyy" value="${hrEssForeignJournyDtl.toDate}" type="date" />
			<c:set var="fromPlace" value="${hrEssForeignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}" />
			<c:set var="toPlace" value="${hrEssForeignJournyDtl.cmnCountryMstByCmnCtryMstToCtryIdFk.countryName}" />
			<fmt:formatNumber var="exp" type="number" minFractionDigits="2" groupingUsed="false" value="${hrEssForeignJournyDtl.approxExpenditureNoc}"></fmt:formatNumber>
			<c:set var="expOccured"	value="${exp}" />
			<c:set var="attachmentId" value="${hrEssForeignJournyDtl.cmnAttachmentMst.attachmentId}" />

			<script type="text/javascript">					
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array('${journeyStartDate}','${journeyEndDate}','${fromPlace}','${toPlace}','${expOccured}');
				
				if ('${isInboxJsp}' == 'false')
				{
					 // addDBDataInTable('journeyTable','encXMLJ',displayFieldA,xmlFileName,'editJourneyRecord', 'deleteJournyRecord','');					
					addDBDataInTableAttachment('journeyTable','encXMLJ',displayFieldA,xmlFileName,'','editJourneyRecord', 'deleteJournyRecord','');
				}
				else
				{
					var attachmentId = "";
					
					if ('${attachmentId}' != 'null' && '${attachmentId}' != 'NULL' && '${attachmentId}' != '0')
						attachmentId = '${attachmentId}';
						
					// addDBDataInTable('journeyTable','encXMLJ',displayFieldA,xmlFileName,'', '','viewJourneyRecord');
					addDBDataInTableAttachment('journeyTable','encXMLJ',displayFieldA,xmlFileName,attachmentId,'', '','viewJourneyRecord');
				}
					
			</script>
		</c:forEach>
	</c:if>

	</hdiits:fieldGroup>

	<table align="center" width="100%" id="submitBtns">
		<tr align="center">
			<td>
			<center><hdiits:button type="button" name="btnSubmit"
				captionid="FVISIT.Submit" bundle="${FVISITLables}"
				onclick="submitFVisitReport();" /> &nbsp;&nbsp;<hdiits:button
				type="button" name="btnClose" id="btnClose" captionid="FVISIT.Close"
				bundle="${FVISITLables}" onclick="closePage();" /></center>
			</td>
		</tr>
	</table>	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden"
		default="${fileId}" />
	<table id='hideRejectMsg' width="100%" colspan="4" style="display:none">
		<tr>
			<td><font class="fieldLabel" color="red"><strong><fmt:message key="NOC.NocRejectMessage" bundle="${NOCLables}" /></strong></font></td>
		</tr>
	</table>
	<table id='hideApproveMsg' width="100%" colspan="4"
		style="display:none">
		<tr>
			<td><font class="fieldLabel" color="red"><strong><fmt:message key="NOC.NocApproveMessage" bundle="${NOCLables}" /></strong></font></td>
		</tr>
	</table>
	<table align="center" id="displayCloseBtn" width="100%" style="display:none" >
	<tr align="center">
       <td><center><hdiits:button type="button" name="btnViewClose" captionid="NOC.Close" bundle="${NOCLables}" onclick="self.close();"/>
		    </center>
	  </td>
    </tr> 
</table>
	</div>
	</div>
	</div>

	<hdiits:hidden id="selectedTripPk" name="selectedTripPk" />

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:validate locale="${locale}"
		controlNames="txtFromDate,txtToDate,dtStartDate,dtEndDate,name_selFromPlace,name_selToPlace,selAgencyName,txtNewAgencyName,selMode,numAmount,txtBank,numAcct,txtPaymentOtherDetails,numChequeNo,selCategory,selBearer,selBearerName,numCategorisedAmount" />
</hdiits:form>


<script language="javascript">
	initializetabcontent("maintab");
	hideExpenditureType();
	document.frmVisitReport.txtFromDate.readOnly=true;
	document.frmVisitReport.txtToDate.readOnly=true;
	document.frmVisitReport.dtStartDate.readOnly=true;
	document.frmVisitReport.dtEndDate.readOnly=true;
	document.frmVisitReport.dtKnownSince.readOnly=true;
	document.frmVisitReport.rdoOfficialDealing[1].checked="true";
	if("${flag}"=="1")	
	{
		document.getElementById("dropdown").style.display='block';	
		document.getElementById("wholePage").style.display='none';
	}	
	else if("${flag}"=="2")
	{
		document.frmVisitReport.selTrip.readOnly=true;
		document.frmVisitReport.txtFromDate.value='${tripFrmDate}';
		document.frmVisitReport.txtToDate.value='${tripToDate}';
		document.frmVisitReport.selectedTripPk.value='${selectedTripPk}';
		showDuration();
		if('${tripToDate}' != null && '${tripToDate}' != "" && '${tripFrmDate}' != null && '${tripFrmDate}' != "")		
			getLeaveDetail();
		if('${tripToDate}' != null)
		{
			document.frmVisitReport.txtaPurposeOfVisit.value='${hrEssForeignTripDtl.purposeOfVisit}';
		}
	}
		
	 if ("${isInboxJsp}" == "true")
	 {
	 	document.getElementById("img_txtFromDate").style.display='none';
	 	document.getElementById("img_txtToDate").style.display='none';
	 	document.getElementById("img_dtStartDate").style.display='none';
	 	document.getElementById("img_dtEndDate").style.display='none';
	 	document.getElementById("img_selFromPlace").style.display='none';
	 	document.getElementById("img_selToPlace").style.display='none';
	 	
	 	var obj = document.getElementById("txtFromDate");
		obj.parentNode.childNodes[3].innerHTML="";

		obj = document.getElementById("txtToDate");
		obj.parentNode.childNodes[3].innerHTML="";
		
		obj = document.getElementById("dtStartDate");
		obj.parentNode.childNodes[3].innerHTML="";
		
		obj = document.getElementById("dtEndDate");
		obj.parentNode.childNodes[3].innerHTML="";
		
		obj = document.getElementById("selFromPlace");
		obj.parentNode.childNodes[4].innerHTML="";
		
		obj = document.getElementById("selToPlace");
		obj.parentNode.childNodes[4].innerHTML="";	 	
	 	
	 	document.getElementById("submitBtns").style.display='none';
	 	document.frmVisitReport.txtaPurposeOfVisit.readOnly=true;
	 	document.getElementById("journeyButtons").style.display='none';
	 	document.getElementById("agentBtns").style.display='none';
	 	document.getElementById("expBtns").style.display='none';
	 	
	 	document.frmVisitReport.txtFromDate.value='${tripFrmDate}';
		document.frmVisitReport.txtToDate.value='${tripToDate}';
	 	showDuration();
	 	
	 	if('${tripToDate}' != null && '${tripToDate}' != "" && '${tripFrmDate}' != null && '${tripFrmDate}' != "")		
			getLeaveDetail();
	 	document.frmVisitReport.txtaPurposeOfVisit.value='${hrEssForeignTripDtl.purposeOfVisit}';
	 	document.getElementById("inboxHideAgent").style.display='none';
	 	document.getElementById("inboxShowAgent").style.display='block';
	 	 	
	 	document.getElementById("inboxHideExp").style.display='none';	 	
	 	
	 	document.getElementById('target_uploadticketAttachment').style.display='none';
		document.getElementById('formTable1ticketAttachment').firstChild.firstChild.style.display='none';	 	
	
	 	if("${statusFlag}"=='P')
		{
			
		}
		else 
		{
			if("${statusFlag}"=='R')
			{				
				document.getElementById("hideRejectMsg").style.display='block';	
			}
			else if("${statusFlag}"=='A')
			{
				if("${isViewDetails}"=="yes")
				{
					document.getElementById("hideApproveMsg").style.display='none';					
					document.getElementById("displayCloseBtn").style.display='';
				}
				else if("${isViewDetails}"=="no")
				{					
					document.getElementById("hideApproveMsg").style.display='block';
				}
			}
		}
	 }
	  else //Added by krunal
{
	viewAgentDetail();
	viewExpDetail();
}
	 	
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
