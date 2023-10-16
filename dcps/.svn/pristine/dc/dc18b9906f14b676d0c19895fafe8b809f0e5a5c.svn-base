
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="nocPassportInboxObj" value="${resultValue.nocPassportInboxObj}"></c:set>
<c:set var="purpose" value="${resultValue.nocPurpose}"></c:set>
<c:set var="country" value="${resultValue.visitingCountry}"></c:set>
<c:set var="prevNOCList" value="${resultValue.prevNOCList}"></c:set>
<c:set var="pastFvisit" value="${resultValue.pastFvisitList}"></c:set>
<c:set var="selectedPurpose" value="${nocPassportInboxObj.cmnLookupMst.lookupName}"></c:set>
<c:set var="appicantUserId" value="${nocPassportInboxObj.orgUserMstByOrgUserMstUserIdFk.userId}"></c:set>
<c:set var="reqId" value="${nocPassportInboxObj.hrEssNocPassportDtlPk}"></c:set>
<c:set var="expBearerType" value="${nocPassportInboxObj.hrEssExpbearerDtl.cmnLookupMst.lookupName}"></c:set>
<c:set var="selectedOrg" value="${nocPassportInboxObj.hrEssExpbearerDtl.cmnOrganizationMst.organizationId}"></c:set>
<c:set var="selectedPerson" value="${nocPassportInboxObj.hrEssExpbearerDtl.cmnPersonMst.personId}"></c:set>
<c:set var="selectedCountry" value="${nocPassportInboxObj.cmnCountryMst.countryName}"></c:set>
<c:set var="statusFlag" value="${nocPassportInboxObj.statusFlag}"></c:set>
<c:set var="cmnLookupMst" value="${resultValue.cmnLookupMst}"></c:set> <!-- added for NOC CR --> 
<c:if test="${empty selectedPerson=='true'}">
	<c:set var="selectedPerson" value="0"></c:set>
</c:if>
<c:if test="${empty selectedOrg=='true'}">
	<c:set var="selectedOrg" value="0"></c:set>
</c:if>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request" />

<html>
<head>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/nocPassportInbox.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/NOC/Expenditure.js"/>"></script>

<script language="javascript">

</script>
</head>
<body>

<hdiits:form name="frmNocPassport" validate="true" method="POST">
	   
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="NOC.NocHeader" bundle="${NOCLables}" /></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">

	<div id="tcontent1" class="tabcontent" tabno="0"><%@ include
		file="../../eis/empInfo/EmployeeInfo.jsp"%>

	<hdiits:fieldGroup titleCaptionId="NOC.SecApplnDetails" bundle="${NOCLables}" id="ApplicationDtl" >
	<!--  <table bgcolor="#386CB7" align="center" width="100%">

		<tr align="center">
			 <td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecApplnDetails" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.Purpose"
				bundle="${NOCLables}" /></td>
			<td width="25%"><hdiits:select name="selPurpose" id="selPurpose"
				captionid="NOC.Purpose" bundle="${NOCLables}" sort="false"
				readonly="true">
				<hdiits:option value="0">
					<fmt:message key="NOC.Dropdown.Select" bundle="${NOCLables}"/>
				</hdiits:option>
				<c:forEach var="purposeValue" items="${purpose}">
					<c:choose>
						<c:when test="${purposeValue.lookupName == selectedPurpose}">

							<option value="<c:out value="${purposeValue.lookupName}"/>"
								selected="selected"><c:out
								value="${purposeValue.lookupDesc}" /></option>
						</c:when>

						<c:otherwise>
							<option value="<c:out value="${purposeValue.lookupName}"/>">
							<c:out value="${purposeValue.lookupDesc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</hdiits:select></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<table id="displayRemark" style="display:none" width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.Remark"
				bundle="${NOCLables}" /></td>
			<td><hdiits:textarea name="txtaRemark" caption="txtaRemark"
				captionid="NOC.Remark" bundle="${NOCLables}" rows="3" cols="30"
				default="${nocPassportInboxObj.remarks}" readonly="true" /></td>
			<td></td>
			<td></td>
		</tr>
	</table>


	<div id="displayVisitDetails" style="display:none">
 <hdiits:fieldGroup titleCaptionId="NOC.SecJourneyDetails" bundle="${NOCLables}" id="TentativeJrnyDtl" >
	<!--  <table bgcolor="#386CB7" align="center" width="100%">

		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecJourneyDetails" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.NocVisitCountry"
				bundle="${NOCLables}" /></td>

			<td width="33%"><hdiits:text name="selCountry" id="selCountry" captionid="NOC.NocVisitCountry" bundle="${NOCLables}" readonly="true" default="${selectedCountry}"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.StartDate"
				bundle="${NOCLables}" /></td>
			<td width="33%"><fmt:formatDate var="fromDate" pattern="dd/MM/yyyy"
				value="${nocPassportInboxObj.fromDate}" type="date" /> <hdiits:text
				name="txtStartDate" id="txtStartDate" default="${fromDate}"
				readonly="true" /></td>
			<td width="18%"><hdiits:caption captionid="NOC.EndDate"
				bundle="${NOCLables}" /></td>
			<td><fmt:formatDate var="toDate" pattern="dd/MM/yyyy"
				value="${nocPassportInboxObj.toDate}" type="date" /> <hdiits:text
				name="txtEndDate" id="txtEndDate" default="${toDate}"
				readonly="true" /></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.Duration"
				bundle="${NOCLables}" /></td>
			<td><hdiits:text name="txtDuration" id="txtDuration"
				readonly="true" /></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.NocPurposeVisit"
				bundle="${NOCLables}" /></td>
			<td><hdiits:textarea name="txtaPurposeOfVisit" rows="3" cols="30"
				default="${nocPassportInboxObj.purposeOfVisit}"
				readonly="true" /></td>
			<td><hdiits:caption captionid="NOC.OtherDetails"
				bundle="${NOCLables}" /></td>
			<td><hdiits:textarea name="txtaOtherDetails"
				caption="txtaOtherDetails" rows="3" cols="30"
				default="${nocPassportInboxObj.otherDetails}" readonly="true" /></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="NOC.Expense"
				bundle="${NOCLables}" /></td>
			<td><fmt:formatNumber var="exp" type="number" minFractionDigits="2" groupingUsed="false" value="${nocPassportInboxObj.approxExpenditure}"></fmt:formatNumber>
				<hdiits:text name="numExpense" id="numExpense" captionid="NOC.Expense" bundle="${NOCLables}" default="${exp}" readonly="true" style="text-align: right"/></td>
		</tr>
	</table>

	<%@ include file="../nocForForeignVisit/Expenditure.jsp"%>
	</hdiits:fieldGroup>
	</div>
</hdiits:fieldGroup>
	<c:if
		test="${nocPassportInboxObj.cmnLookupMst.lookupName=='nocVisitCountry'}">
		<script>
			document.getElementById("displayVisitDetails").style.display='block';		
			var fromDate=document.getElementById("txtStartDate").value;	
			var toDate=document.getElementById("txtEndDate").value;	
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
		
			
		
	</script>
	</c:if>

	<c:if test="${nocPassportInboxObj.cmnLookupMst.lookupName=='nocOther'}">
		<script>
	document.getElementById("displayRemark").style.display='block';
	</script>
	</c:if>
	

	<br>
	 <hdiits:fieldGroup titleCaptionId="NOC.SecPastForeign" bundle="${NOCLables}" id="displayBearerDetailsHeader" collapseOnLoad="true">
	<!--  <table bgcolor="#386CB7" align="center" width="100%"
		id="displayBearerDetailsHeader" style="display:none">
		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecBearerDetails" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>


	<table bgcolor="#386CB7" align="center" width="100%">

		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPastForeign" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->

	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.NocAbroad"
				bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:radio name="rdoNocVisitedCountry"
				id="rdoNocVisitedCountry" value="Yes" captionid="NOC.NocYes"
				bundle="${NOCLables}"/> <hdiits:radio
				name="rdoNocVisitedCountry" value="No" captionid="NOC.NocNo"
				bundle="${NOCLables}"/></td>
			<td></td>
			<td></td>
		</tr>
	</table>


	<c:if test="${empty pastFvisit=='false'}">
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
					<td align="left"><c:set var="strPlaces" value=""></c:set> <c:forEach
						var="foreignJournyDtl"
						items="${pastFvisitValue.hrEssForeignJournyDtls}">
						<c:set var="strPlaces"
							value="${strPlaces},${foreignJournyDtl.cmnCountryMstByCmnCtryMstFrmCtryIdFk.countryName}"></c:set>
					</c:forEach> <c:if test="${fn:length(strPlaces) gt 0}">
						<c:out value="${fn:substring(strPlaces,1,fn:length(strPlaces))}"></c:out>
					</c:if></td>
					<td align="left">${pastFvisitValue.purposeOfVisit}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if> <c:if test="${empty pastFvisit=='true'}">
		<script>
		document.frmNocPassport.rdoNocVisitedCountry[1].checked = true;
		document.frmNocPassport.rdoNocVisitedCountry[0].disabled = true;
		document.frmNocPassport.rdoNocVisitedCountry[1].disabled = true;
	</script>

	</c:if>


	<table id='fvisitTable' name="fvisitTable" border=1 borderColor="black"
		align="center" width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor}"
		style="display:none">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.FromDate" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.ToDate" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.NocVisitedCountry" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.NocPurposeVisit" bundle="${NOCLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="NOC.Action" bundle="${NOCLables}" /></label></b></td>
		</tr>
	</table>
	

 </hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="NOC.SecPrevNonAppln" bundle="${NOCLables}" id="prevAppl" collapseOnLoad="true" >
	<!--  <table bgcolor="#386CB7" align="center" width="100%">

		<tr align="center">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message key="NOC.SecPrevNonAppln" bundle="${NOCLables}"></fmt:message></u></strong></font></td>
		</tr>
	</table>-->
	<table width="100%">
		<tr>
			<td width="20%"><hdiits:caption captionid="NOC.NocPast"
				bundle="${NOCLables}" /></td>
			<td width="33%"><hdiits:radio name="rdoNocIssued"
				id="rdoNocIssued" value="Yes" captionid="NOC.NocYes"
				bundle="${NOCLables}" /> <hdiits:radio
				name="rdoNocIssued" value="No" captionid="NOC.NocNo"
				bundle="${NOCLables}" /></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<c:if test="${empty prevNOCList=='false'}">
		<script>
		document.frmNocPassport.rdoNocIssued[0].checked = true;
		document.frmNocPassport.rdoNocIssued[0].disabled = true;
		document.frmNocPassport.rdoNocIssued[1].disabled = true;
	</script>
		<table id='prevNocDBTable' name="prevNocDBTable" border=1 align="center" width="100%" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;background-color: ${tableBGColor}">
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
		document.frmNocPassport.rdoNocIssued[1].checked = true;
		document.frmNocPassport.rdoNocIssued[0].disabled = true;
		document.frmNocPassport.rdoNocIssued[1].disabled = true;
	</script>
	</c:if>

</hdiits:fieldGroup>

	

	<table id='hideBtns' align="center" width="100%" colspan="4"
		style="display:none">
		<tr align="center">
			<td>
			<center><!--<hdiits:button type="button" name="btnFwd"
				captionid="NOC.Fwd" bundle="${NOCLables}" onclick="fwdNocReq();" />
			&nbsp;&nbsp;<hdiits:button type="button" name="btnApprove"
				captionid="NOC.App" bundle="${NOCLables}"
				onclick="approveNocDtls();" /> &nbsp;&nbsp;<hdiits:button
				type="button" name="btnReject" id="btnReject" captionid="NOC.Rej"
				bundle="${NOCLables}" onclick="rejectNocDtls();" />-->
				
				
				</center>
			</td>
		</tr>
	</table>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}" /> 
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
	<script>
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
</div>

	</div>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>


<script type="text/javascript">
	if(document.getElementById("selPurpose").value=='nocVisitCountry')
	{
		document.getElementById("selBearer").value="${expBearerType}";
		
		if (document.getElementById("selBearer").value == 'nocExpOrg') 
		{
			showExpenditureName(document.getElementById("selBearer"),true,"${appicantUserId}","${selectedOrg}");
		}	
		else if(document.getElementById("selBearer").value == 'nocExpOther')
		{
			showExpenditureName(document.getElementById("selBearer"),true,"${appicantUserId}","${selectedPerson}");
		}
		else if(document.getElementById("selBearer").value == 'nocExpSelf')
		{
			/* added for NOC CR--starts */
			document.getElementById("displaySelBearerBank").style.display=''; // added for NOC CR
			document.getElementById("displayBankDtls").style.display = '';			
			document.frmNocPassport.selBearerBank.readOnly=true;				
			// document.getElementById("selBearerBank").value='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.hrEisBankMst.bankName}';
			// document.getElementById("txtBranch").value='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.hrEisBranchMst.branchName}';
			// document.getElementById("txtAcctType").value='${cmnLookupMst.lookupDesc}';	
			// document.getElementById("txtAcctNo").value='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.bankAcctNo}';		
			
			str='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.hrEisBankMst.bankName}';
			if(str==null || str=='' || str=='null')
			{
				document.getElementById("selBearerBank").value= "--";					
			}
			else
			{
				document.getElementById("selBearerBank").value=str;
			}
				
			
			str='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.hrEisBranchMst.branchName}';
			
			if(str==null || str=='' || str=='null')
			{
				document.getElementById("txtBranch").value= "--";
			}
			else
			{
				document.getElementById("txtBranch").value=str;						
			}
			
			var bankAcctType = '${cmnLookupMst.lookupDesc}';	
			if(bankAcctType==null || bankAcctType=='' || bankAcctType=='null')
			{
				document.getElementById('txtAcctType').value="--";
			}	
			else
			{
				document.getElementById("txtAcctType").value=bankAcctType;
			}
			// document.getElementById("txtAcctType").value='to do';	
			str='${nocPassportInboxObj.hrEssExpbearerDtl.bearerBankDtlId.bankAcctNo}';		
			if(str==null || str=='' || str=='null')
			{
				document.getElementById("txtAcctNo").value="--";
			}
			else
			{
				document.getElementById("txtAcctNo").value= str;
			}
					
			document.getElementById("displayBankDtls").style.display = '';
			/* added for NOC CR--ends */
		}
		if (document.getElementById("selBearer").value == 'nocExpOrg' ||  document.getElementById("selBearer").value == 'nocExpOther' )  
		{
		var  radioValue='${nocPassportInboxObj.hasOfficialDealing}'
		var   strofficeDtls  ='${nocPassportInboxObj.officialDealDetails}';
		var  checkVal = new Array();
		checkVal= document.frmNocPassport.rdoOfficialDealing
		if(checkVal[0].value==radioValue)
		{
			document.getElementById("DealDetail1").style.display='';
			document.getElementById("DealDetail2").style.display='';
			checkVal[0].checked=true;
			checkVal[0].disabled=true;
			checkVal[1].disabled=true;
			
			var txtArea=document.getElementById('txtaDealDetail')
			txtArea.value=strofficeDtls;
			txtArea.disabled=true;
		}
		else
		{
			checkVal[1].checked=true;
			checkVal[1].disabled=true;
			checkVal[0].disabled=true;
			document.frmNocPassport.txtaDealDetail.disable=true;
			//document.frmNocPassport.rdoOfficialDealing.disabled=true;
	           
		}
	}	
		
		document.getElementById("selBearer").mandatory=false
		document.getElementById("selBearer").mandatory=false;
		document.frmNocPassport.selBearer.disabled=true;
		document.getElementById("selBearerName").mandatory=false;
		document.frmNocPassport.selBearerName.disabled=true;
		
	}
	initializetabcontent("maintab");
	document.getElementById("displayAmtLabel").style.display='none';
	document.getElementById("displayAmt").style.display='none';
</script>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
			