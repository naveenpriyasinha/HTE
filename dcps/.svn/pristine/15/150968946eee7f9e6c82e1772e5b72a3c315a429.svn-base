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
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="country" value="${resultValue.visitingCountry}"></c:set>
<c:set var="prevNOCList" value="${resultValue.prevNOCList}"></c:set>
<c:set var="nocFVisitInboxObj" value="${resultValue.nocFVisitInboxObj}"></c:set>
<c:set var="foreignJourneyDtls" value="${nocFVisitInboxObj.hrEssForeignJournyDtls}"></c:set>
<c:set var="applicantUserId" value="${nocFVisitInboxObj.orgUserMstByOrgUserMstUserIdFk.userId}"></c:set>
<c:set var="statusFlag" value="${nocFVisitInboxObj.statusFlag}"></c:set>
<c:set var="reqId" value="${nocFVisitInboxObj.hrEssForeignTripPk}"></c:set>
<c:set var="pastFvisit" value="${resultValue.pastFvisitList}"></c:set> <!-- added for NOC CR -->
<c:set var="pastFvisitNOCList" value="${resultValue.pastFvisitNOCList}"></c:set> <!-- added for NOC CR -->
<c:set var="arForeignTripDebarDtl" value="${resultValue.arForeignTripDebarDtl}"></c:set> <!-- added for NOC CR -->
<c:set var="voToXmllstObj" value="${resultValue.voToXmllstObj}"></c:set> <!-- added for NOC CR -->

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request" />
<fmt:setBundle basename="resources.ess.noc.AlertMessages" var="NOCAlerts" scope="request"/>

<html>
<head>
<script type="text/javascript" src="/script/common/address.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/nocForeignVisitInbox.js"/>"></script>


<script type="text/javascript">
	
var applicantId='${applicantUserId}';	
var requestId='${reqId}';
	</script>

</head>
<body>

<hdiits:form name="frmNocForeignVisit" validate="true" method="POST"> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
			<fmt:message key="NOC.NocForeignHeader" bundle="${NOCLables}"/></a></li>
	</ul>
</div>
	<div class="tabcontentstyle" style="height: 100%">		
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
		
<%@ include file="../../eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup titleCaptionId="NOC.SecVisitDetails" bundle="${NOCLables}" id="FvisitDtl">
<!-- <table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecVisitDetails" bundle="${NOCLables}"/></u></strong></font></td>
    </tr> 
</table>
 -->
<table width="100%">
	<tr>
		<td width="25%"><hdiits:caption captionid="NOC.StartDate" bundle="${NOCLables}"/></td>
		<td width="25%"><fmt:formatDate var="fromDate"  pattern="dd/MM/yyyy" value="${nocFVisitInboxObj.fromDate}" type="date"/>
  			<hdiits:text name="dtTFromDate" id="dtTFromDate" default="${fromDate}" readonly="true"/></td>
		<td width="25%"><hdiits:caption captionid="NOC.EndDate" bundle="${NOCLables}"/></td>
		<td width="25%"><fmt:formatDate var="toDate"  pattern="dd/MM/yyyy" value="${nocFVisitInboxObj.toDate}" type="date"/>
  			<hdiits:text name="dtTToDate" id="dtTToDate" default="${toDate}" readonly="true"/></td>
	</tr>
	<tr>
		<td><hdiits:caption captionid="NOC.Duration" bundle="${NOCLables}"/></td>			
		<td><hdiits:text name="txtDuration" id="txtDuration" readonly="true"/></td>
		<td><hdiits:caption captionid="NOC.NocPurposeVisit" bundle="${NOCLables}"/></td>
		<td><hdiits:textarea name="txtaPurposeOfVisit" caption="txtaPurposeOfVisit" rows="3" cols="30" default="${nocFVisitInboxObj.purposeOfVisit}" readonly="true"/></td>
	</tr>
</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecTripDetails" bundle="${NOCLables}" id="FvisitTripDtl">
<!--  <table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecTripDetails" bundle="${NOCLables}"/></u></strong></font></td>
    </tr> 
</table>-->
<br>

<table id='JourneyTable' name="JourneyTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
	<tr>									
		<td align="center" style="display:none" bgcolor="${tdBGColor}"><b><label>Request Id</label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.StartDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.EndDate" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.FromPlace" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.ToPlace" bundle="${NOCLables}"/></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Action" bundle="${NOCLables}"/></label></b></td>
	</tr>
		<c:forEach var="foreignJourney" items="${foreignJourneyDtls}">
			<tr>
				<td align="left" style="display:none">${foreignJourney.hrEssForeignJournyPk}</td>
				<td align="left"><fmt:formatDate var="fromDate" pattern="dd/MM/yyyy" value="${foreignJourney.fromDate}" type="date"/>${fromDate}</td>
				<td align="left"><fmt:formatDate var="toDate" pattern="dd/MM/yyyy" value="${foreignJourney.toDate}" type="date"/>${toDate}</td>
				<td align="left">${foreignJourney.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}</td>
				<td align="left">${foreignJourney.cmnCountryMstByCmnCtryMstToCtryIdFk.countryName}</td>
				<td align="left"><hdiits:a href="javascript:void(0)" onclick="openJourneyDetails(this)" captionid="NOC.View" bundle="${NOCLables}"/></td>
			</tr>
		</c:forEach>
</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecPastForeign" bundle="${NOCLables}" id="PastDetails" collapseOnLoad="true">
 <!-- added for NOC CR(starts) 
<table bgcolor="#386CB7" align="center"  width="100%">
	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPastForeign"></fmt:message></u></strong></font></td>
    </tr> 
</table>     -->  	
<br>
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
	<c:if test="${not empty pastFvisit}">
	<c:forEach var="pastFvisitValue" items="${pastFvisit}">
		<tr>
			<td style="display:none">${pastFvisitValue.fileId}</td>		<!-- 7th july -->
			<td align="left" bgcolor="${tableBGColor}"><fmt:formatDate var="fromDates" pattern="dd/MM/yyyy" value="${pastFvisitValue.fromDate}" type="date"/>${fromDates}</td>
			<td align="left" bgcolor="${tableBGColor}"><fmt:formatDate var="toDates" pattern="dd/MM/yyyy" value="${pastFvisitValue.toDate}" type="date"/>${toDates}</td>		
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
					<c:out 	value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:out> 
				</c:if>	
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
	</c:if>
</table>
 
<c:if test="${empty pastFvisit}">
<table width="100%">
		<tr width="100%"><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
</table>
</c:if>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.HeaderPrevNocFvisit" bundle="${NOCLables}" id="PrevNOCDtl" collapseOnLoad="true">
<!-- added for NOC CR starts -->
	<!--  <table bgcolor="#386CB7" align="center" width="100%">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.HeaderPrevNocFvisit" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="25%"><hdiits:caption captionid="NOC.IfIssuedNocFv" bundle="${NOCLables}"/></td>
			<td width="25%"><hdiits:radio name="rdoNocFvisitIssued" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" onclick="showNocFvisitTextBoxes();" /> 
							<hdiits:radio name="rdoNocFvisitIssued" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" onclick="hideNocFvisit();" /></td>
			<td></td>
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
							<fmt:message key="NOC.NocYes" bundle="${NOCLables}"></fmt:message>
						</c:if> 
						<c:if test="${pastFvisitNOCValue.whetherDebarred eq 78}">						
							<fmt:message key="NOC.NocNo" bundle="${NOCLables}"></fmt:message>
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
			document.frmNocForeignVisit.rdoNocFvisitIssued[0].disabled = true;
			document.frmNocForeignVisit.rdoNocFvisitIssued[1].disabled = true;
		</script>
	</c:if>

	</hdiits:fieldGroup>
<br>
<!-- added for NOC CR ends -->
<hdiits:fieldGroup titleCaptionId="NOC.SecDetailsOfNOC" bundle="${NOCLables}" id="DetailsOfNOC" collapseOnLoad="true">
<!--  <table bgcolor="#386CB7" align="center"  width="100%">
	<tr align="center">
      <td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.SecDetailsOfNOC" bundle="${NOCLables}"/></u></strong></font></td>
    </tr> 
</table>-->

<table width="100%">
	<tr>
		<td width="25%"><hdiits:caption captionid="NOC.NocPast" bundle="${NOCLables}"/></td>
		<td width="25%"><hdiits:radio name="rdoNocIssued" value="Yes" captionid="NOC.NocYes" bundle="${NOCLables}" />
			<hdiits:radio name="rdoNocIssued" value="No" captionid="NOC.NocNo" bundle="${NOCLables}" /></td>
		<td></td>
		<td></td>	
	</tr>
</table>

<c:if test="${empty prevNOCList=='false'}" >
	<script>
		document.frmNocForeignVisit.rdoNocIssued[0].checked = true;
		document.frmNocForeignVisit.rdoNocIssued[0].disabled = true;
		document.frmNocForeignVisit.rdoNocIssued[1].disabled = true;
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
			<td align="left"><fmt:formatDate var="nocIssueDate" pattern="dd/MM/yyyy" value="${prevNocValue.nocIssueDate}" type="date"/>${nocIssueDate}</td>
			<td align="left">${prevNocValue.appoverName}</td>
			<td align="left">${prevNocValue.approverDesignationId.dsgnName}</td>
			<td align="left">${prevNocValue.orderNo}</td>
		</tr>
	</c:forEach>
</table>
	
</c:if>

<c:if test="${empty prevNOCList=='true'}" >
	<script>
		document.frmNocForeignVisit.rdoNocIssued[1].checked = true;
		document.frmNocForeignVisit.rdoNocIssued[0].disabled = true;
		document.frmNocForeignVisit.rdoNocIssued[1].disabled = true;
	</script>
</c:if>
</hdiits:fieldGroup>
<br>
<!-- added for NOC CR starts -->
<hdiits:fieldGroup titleCaptionId="NOC.DebarDtl" bundle="${NOCLables}" id="displayDebarHstTitle" collapseOnLoad="true">
<!--  <table bgcolor="#386CB7" align="center" width="100%" id="displayDebarHstTitle" style="display:none">
	<tr align="center">
		<td class="fieldLabel" colspan="10" align="center">
			<font color="#ffffff"><strong><u><fmt:message key="NOC.DebarDtl" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
	</tr>
</table>-->
<br>

<table width="100%" id="prevDebarTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}"  style="display:none">
	<tr>
		<td style="display:none">&nbsp;</td>			
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Trip" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.IsDebarred" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarBy" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.DebarDate" bundle="${NOCLables}" /></label></b></td>
		<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption captionid="NOC.Reason" bundle="${NOCLables}" /></label></b></td>	
	</tr>
</table>
<table id="debarNorecord" style="display:none" align="center" width="85%">
	<tr ><td><center><b><fmt:message key="NOC.NoRecordFound" bundle='${NOCLables}'></fmt:message></b></center></td></tr>
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
						</c:if> 
						<c:if test="${foreignTripDebarDtl.whetherDebarred eq 78}">						
							<c:set var="isDebarred" value='No' ></c:set>
						</c:if>
					<c:set var="debarredBy" value="${foreignTripDebarDtl.debarredBy}" />
					<fmt:formatDate var="debarDate" pattern="dd/MM/yyyy" value="${foreignTripDebarDtl.debarDate}" type="date" />
					<c:set var="debarredOn" value="${debarDate}" />
					<c:set var="debarReason" value="${foreignTripDebarDtl.debarReason}" />
					
					<script type="text/javascript">			
						var xmlFileName = '${curXMLFileName}';					
						var displayFieldA  = new Array('${trip}', '${isDebarred}', '${debarredBy}','${debarredOn}','${debarReason}');
						addDBDataInTable('prevDebarTable','encDBdebarXML',displayFieldA,xmlFileName);
						document.getElementById("displayDebarHstTitle").style.display = '';	
						document.getElementById("prevDebarTable").style.display = '';						
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
<!-- added for NOC CR ends -->
<hr>

<table id="hideBtns" align="center"  width="100%" style="display:none">
	<tr align="center">
       <td><center><!--<hdiits:button type="button" name="btnFwd" captionid="NOC.Fwd" bundle="${NOCLables}" onclick="fwdNocFVisit();"/>
		   	 	&nbsp;&nbsp;<hdiits:button type="button" name="btnApprove" id="btnApprove" captionid="NOC.App" bundle="${NOCLables}" onclick="approveNocFVisit();"/>
		   	 	&nbsp;&nbsp;<hdiits:button type="button" name="btnReject" id="btnReject" captionid="NOC.Rej" bundle="${NOCLables}" onclick="rejectNocFVisit();"/>
	  -->
	  
	  </center>
	  </td>
    </tr> 
</table>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}" /> 
<table id="hideRejectMsg" width="100%" colspan="4" style="display:none">
    <tr>
    	<td><font class="fieldLabel" color="red"><strong><fmt:message key="NOC.NocRejectMessage" bundle="${NOCLables}" /></strong></font></td>
    </tr>
</table>

<table id="hideApproveMsg" width="100%" colspan="4" style="display:none">
    <tr>
    	<td><font class="fieldLabel" color="red"><strong><fmt:message key="NOC.NocApproveMessage" bundle="${NOCLables}" /></strong></font></td>
    </tr>
</table>

</div>

	</div>
		
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:validate locale="${locale}" controlNames="selBearer,selBearerName,date,txtApprover,txtDesignation,txtOrderNo"/> 
</hdiits:form>

<script>
initializetabcontent("maintab");
// Display  duration --starts
	
	var fromDate=document.getElementById("dtTFromDate").value;	
	var toDate=document.getElementById("dtTToDate").value;	
	var dateDiff = getDateDiffInString(fromDate,toDate);
				
	var duration = dateDiff.split("~");
	if(duration[0] != 0 && duration[1] != 0)
		document.getElementById("txtDuration").value=duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " +duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
	else if(duration[0] == 0 && duration[1] != 0)
			document.getElementById("txtDuration").value=duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
		 else if(duration[0] != 0 && duration[1] == 0)
				document.getElementById("txtDuration").value=duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
			else if(duration[0] == 0 && duration[1] == 0)
					document.getElementById("txtDuration").value=duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
				else
					document.getElementById("txtDuration").value=duration[0] +" <fmt:message bundle='${NOCLables}' key='NOC.Years'/> " +duration[1] +" <fmt:message bundle='${NOCLables}' key='NOC.Months'/> " + duration[2]+" <fmt:message bundle='${NOCLables}' key='NOC.Days'/>";
	
	
// Display  duration --Ends

if("${statusFlag}"=='P')
{
	document.getElementById("hideBtns").style.display='block';
}
else 
{
	if("${statusFlag}"=='R')	
		document.getElementById("hideRejectMsg").style.display='block';	
	 else if("${statusFlag}"=='A')
			document.getElementById("hideApproveMsg").style.display='block';
}
</script>
 
 <%
	}

	catch (Exception e) 
	{
		e.printStackTrace();
	}
%>
		