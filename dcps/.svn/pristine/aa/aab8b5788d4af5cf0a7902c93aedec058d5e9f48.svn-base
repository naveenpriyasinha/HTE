<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.eis.empPreEmplLables" var="ProfDtlsLables" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EmpProfessionalDtls.js"/>"></script>

<script>

	var empPreEmploymentAlertMsgArr = new Array();
	empPreEmploymentAlertMsgArr[0]='<fmt:message bundle="${ProfDtlsLables}" key="EIS.JoinDtAlert"/>';
	empPreEmploymentAlertMsgArr[1]='<fmt:message bundle="${ProfDtlsLables}" key="EIS.JoiningDateAlert"/>';
	empPreEmploymentAlertMsgArr[2]='<fmt:message bundle="${ProfDtlsLables}" key="EIS.RelievingDateAlert"/>';
	empPreEmploymentAlertMsgArr[3]="<fmt:message bundle="${ProfDtlsLables}" key="EIS.DurationAlert"/>";
	empPreEmploymentAlertMsgArr[4]=" <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurYrs"/> ";
	empPreEmploymentAlertMsgArr[5]=" <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurMonth"/> ";
	empPreEmploymentAlertMsgArr[6]=" <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurDay"/>";

</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="arTypeOfOrgInfo" value="${resultValue.arTypeOfOrgInfo}" />
<c:set var="xmlFileNmPreEmpl" value="${resultValue.xmlFileNmPreEmpl}" />
<c:set var="PreEmplVOList" value="${resultValue.PreEmplVOList}" />
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="selectedUserId" value="${resultValue.selectedUserId}"></c:set>
<c:set var="workFlowEnabled" value="${resultValue.blnWorkFlowEnabled}"></c:set>
<c:set var="jobProfileList" value="${resultValue.jobProfileList}"></c:set>
<c:set var="strAppType" value="PROF-PRE-EMPLOYEMENT"></c:set>
<c:set var="arIsContinue" value="${resultValue.arIsContinue}"></c:set>

<script type="text/javascript">
	var workFlowEnabled = '${workFlowEnabled}'; //IFMS
</script>

<hdiits:form name="frmEmpProfDtls" validate="true" method="POST">
	<!-- start of employee Service examination Details -->
	<div id="tabmenu">
	
	<c:if test="${workFlowEnabled eq 'true'}">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="EIS.EmplProfDtls" bundle="${ProfDtlsLables}"></fmt:message></b></a></li>
		</ul>
	</c:if>
	
	<c:if test="${workFlowEnabled eq 'false'}">
		<%@ include file="ProfessionalDetailsTab.jsp"%>
	</c:if>	
	
	</div>
	<div id="profDtlsDiv" name="profDtlsDiv">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<%@ include file="empInfo/EmployeeInfo.jsp"%>

	<hdiits:fieldGroup id="profFieldGroupId" titleCaptionId="EIS.EmplProfDtls" bundle="${ProfDtlsLables}">
	<table class="tabtable">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="EIS.EmplProfDtls" bundle="${ProfDtlsLables}" /></u></strong> </font></td>
		</tr>-->
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.OrgNm"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.OrgNm"
				bundle="${ProfDtlsLables}" name="txtOrgNm" caption="Org_Nm"
				validation="txt.isrequired" mandatory="true" /></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.TypOfOrg"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select captionid="EIS.TypOfOrg"
				bundle="${ProfDtlsLables}" name="drodnTypOfOrg" sort="false"
				caption="TypOf_Org" validation="sel.isrequired" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${ProfDtlsLables}" />
				</hdiits:option>
				<c:forEach var="arTypeOfOrgInfoVar" items="${arTypeOfOrgInfo}">
					<hdiits:option value="${arTypeOfOrgInfoVar.lookupName}">${arTypeOfOrgInfoVar.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.DtOfJoin"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.DtOfJoin" bundle="${ProfDtlsLables}" name="dtDtOfJoin" caption="DtOf_Join"  /></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.DtOfRelevng"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.DtOfRelevng" bundle="${ProfDtlsLables}" name="dtDtOfRelevng" caption="DtOf_Relevng" onblur="dateComparisonForPreEmpl()" /></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.DsgAtTimeOfRelvng"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:text captionid="EIS.DsgAtTimeOfRelvng"
				bundle="${ProfDtlsLables}" name="txtDsgAtTimeOfRelvng"
				caption="DsgAtTime_OfRelvng" /></td>

			<td width="25%"><b><hdiits:caption captionid="EIS.JobProfCarrerSkls"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:textarea captionid="EIS.JobProfCarrerSkls" cols="23"
				bundle="${ProfDtlsLables}" name="txtareaJobProfCarrerSkls"
				caption="JobProf_CarrerSkls" /></td>
		</tr>
		
		<tr>
			<td width="25%"><b>
				<hdiits:caption captionid="Eis.IsContinue" bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select name="IsContinue" id="IsContinue"  captionid="Eis.IsContinue" bundle="${ProfDtlsLables}" sort="false" onchange="showDurationTd();" >
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${ProfDtlsLables}" />
				</hdiits:option>
				<c:forEach var="arIsContinue" items="${arIsContinue}">
					<hdiits:option value="${arIsContinue.lookupName}">${arIsContinue.lookupDesc}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			
			<td width="25%" id="durationTdLabelId" style="display:none" rowspan="2"><b><hdiits:caption captionid="EIS.Duration" bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%" id="durationTdId" style="display:none" colspan="3" rowspan="2">
				<table>
					<tr>			
						<td><b><hdiits:caption captionid="EIS.DurYrs" bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
						<td><b><hdiits:caption captionid="EIS.DurMonth" bundle="${ProfDtlsLables}" ></hdiits:caption></b></td>
						<td><b><hdiits:caption captionid="EIS.DurDay" bundle="${ProfDtlsLables}" ></hdiits:caption></b></td>
					</tr>
					<tr>
						<td><hdiits:text name="txtDurationYrs" id="txtDurationYrs" captionid="EIS.DurYrs" bundle="${ProfDtlsLables}" size="1" onkeypress="return checkDecimalNumber()" validation="txt.isnumber" maxlength="2" style="text-align: right"/></td>
						<td><hdiits:text name="txtDurationMonths" id="txtDurationMonths" captionid="EIS.DurMonth" bundle="${ProfDtlsLables}" size="1" onkeypress="return checkForMaxMonths()" validation="txt.isnumber" maxlength="2" style="text-align: right"/></td>
						<td><hdiits:text name="txtDurationDays" id="txtDurationDays" captionid="EIS.DurDay" bundle="${ProfDtlsLables}" size="1"  onkeypress="return checkForMaxDays()" validation="txt.isnumber" maxlength="2" style="text-align: right"/></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.Remarks"
				bundle="${ProfDtlsLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:textarea captionid="EIS.Remarks" cols="23"
				bundle="${ProfDtlsLables}" name="txtareaRemarks" caption="Rema_rks" />
			</td>
		</tr>
	</table>

	<table class="tabtable">
		<tr>
			<hdiits:fmtMessage key="EIS.Address" bundle="${ProfDtlsLables}" var="ProfDtlsAddressTitle" ></hdiits:fmtMessage>
			<td><jsp:include page="../../common/address.jsp">
				<jsp:param name="addrName" value="preEmployerAddress" />
				<jsp:param name="addressTitle" value="${ProfDtlsAddressTitle}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Yes" />
			</jsp:include></td>
		</tr>
		<tr>
			<td align="center" colspan="4">
			<hdiits:button	name="btnEmpProfDtlsAdd" type="button" captionid="EIS.Add"	bundle="${ProfDtlsLables}" onclick="javascript:addOrUpdateProfessionalDtls('Add')" />
			<hdiits:button	name="btnEmpProfDtlsUpdate" type="button" captionid="EIS.Update" bundle="${ProfDtlsLables}"	onclick="javascript:addOrUpdateProfessionalDtls('Update')" style="display:NONE" /> 
			<hdiits:button name="btnEmpProfDtlsReset" type="button" captionid="EIS.Reset" bundle="${ProfDtlsLables}"onclick="javascript:addOrUpdateProfessionalDtls('Reset')" /> 
			</td>
		</tr>
	</table>
	
	<br>
	<hdiits:fieldGroup  id="empProfDtlsFieldGroupId" titleCaptionId="EIS.EmplProfDtls" bundle="${ProfDtlsLables}" collapseOnLoad="false">
	<table id='txnAddEmpProfessionalDtl' name="EmpProfessionalDtl"
		border="1" borderColor="black" align="center" width="100%"
		cellpadding="1" cellspacing="1"
		style="border-collapse: collapse">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.OrgNm" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.TypOfOrg" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.DtOfJoin" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.DtOfRelevng" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.DsgAtTimeOfRelvng" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.JobProfCarrerSkls" bundle="${ProfDtlsLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="Eis.IsContinue" bundle="${ProfDtlsLables}" /></label></b></td>	
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.Duration" bundle="${ProfDtlsLables}" /></label></b></td>	
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption
				captionid="EIS.edtdel" bundle="${ProfDtlsLables}" /></label></b></td>
		</tr>
	</table>
	
	<table id="profNoRecords" align="center">
		<c:if test="${empty PreEmplVOList}">
			<tr></tr>
			<tr></tr>
			<tr>
				<td align="center"><b><hdiits:caption captionid="eis.no_app_req" bundle="${ProfDtlsLables}" /></b></td>
			</tr>
		</c:if>
	</table>
	
	<c:forEach items="${PreEmplVOList}" var="PreEmplVO" varStatus="x">
		<c:set var="currentXMLFilePreEmpl" value="${xmlFileNmPreEmpl[x.index]}"></c:set>
		<c:set var="OrgName" value="${PreEmplVO.cmnOrganizationMst.organizationName}" />
		<c:set var="TypeOfOrg" value="${PreEmplVO.cmnOrganizationMst.cmnLookupMst.lookupDesc}" />
		<fmt:formatDate var="DtOgJoin" pattern="dd/MM/yyyy"	value="${PreEmplVO.dateOfJoining}" type="date" />
		<c:set var="DtOgJoin" value="${DtOgJoin}" />
		<fmt:formatDate var="DtOfReleve" pattern="dd/MM/yyyy" value="${PreEmplVO.dateOfReleving}" type="date" />
		<c:set var="DtOfReleve" value="${DtOfReleve}" />
		<c:set var="Designation" value="${PreEmplVO.designation}" />
		<c:set var="JobCarrerProfile" value="${PreEmplVO.jobProfile}" />
		<c:set var="isContinue" value="${PreEmplVO.cmnLookupMstByIsContinue.lookupDesc}" />
		<c:set var="isContinueLookupName" value="${PreEmplVO.cmnLookupMstByIsContinue.lookupName}" />
		<c:set var="YearsDuratoin" value="${PreEmplVO.durationYears}" />
		<c:set var="MonthsDuration" value="${PreEmplVO.durationMonths}" />
		<c:set var="DaysDuration" value="${PreEmplVO.durationDays}" />
		
		<script type="text/javascript">
			var isContinue='${isContinueLookupName}';
			var dispContinue='${isContinue}';
			var duration='-';
			var durYrs='${YearsDuratoin}';
			var durMon='${MonthsDuration}';
			var durDay='${DaysDuration}';
			var dsgn='${Designation}';
			var jobCarrerProfile='${JobCarrerProfile}';
			var doj='${DtOgJoin}';
			var dor='${DtOfReleve}';
			if(dispContinue=='' || dispContinue==null){dispContinue='-';}
			if(dsgn=='' || dsgn==null){dsgn='-';}
			if(jobCarrerProfile=='' || jobCarrerProfile==null){jobCarrerProfile='-';}
			if(doj=='' || doj==null){doj='-';}
			if(dor=='' || dor==null){dor='-';}
			
			if(isContinue=="Yes_srvc")
			{
				if(durYrs==''){durYrs=0;}if(durMon==''){durMon=0;}if(durDay==''){durDay=0;}
				duration=durYrs+" <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurYrs"/> "+durMon+ " <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurMonth"/> "+durDay+ " <fmt:message bundle="${ProfDtlsLables}" key="EIS.DurDay"/>";
			}
					
			var xmlFileNamePreEmpl = '${currentXMLFilePreEmpl}';
			var JobCarrerProfile = '${jobProfileList[x.index]}';
			var displayFieldArray1  = new Array('${OrgName}','${TypeOfOrg}',doj,dor,dsgn,jobCarrerProfile,dispContinue,duration);
			addDBDataInTable('txnAddEmpProfessionalDtl','encXMLPreviousEmpl',displayFieldArray1,xmlFileNamePreEmpl, 'editEmpProfessionalRecord','deleteDBEmpProfessionalRecord');
		</script>
	</c:forEach> 
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	<br>
	<table align="center">
		<hdiits:button name="btnEmpProfDtlsSubmitInDB" type="button" captionid="EIS.Submit" bundle="${ProfDtlsLables}" onclick="submitInDb()"></hdiits:button>
		<hdiits:button name="btnEmpProfDtlsAddCancel" type="button" captionid="EIS.Cancel" bundle="${ProfDtlsLables}" onclick="closeWindow()"></hdiits:button>
	</table>


	<!-- end of employee service examination Details --></div>
	</div></div>
	<hdiits:validate locale="${locale}"	controlNames="txtOrgNm,drodnTypOfOrg,txtDurationYrs,txtDurationMonths,txtDurationDays" />

	<script type="text/javascript">
		initializetabcontent("maintab");
		document.frmEmpProfDtls.dtDtOfJoin.readOnly = true;
		document.frmEmpProfDtls.dtDtOfRelevng.readOnly = true;
	</script>

	<hdiits:hidden name="hdnProfessionId" id="hdnProfessionId" />
	<hdiits:hidden name="hdnOrganizationId" id="hdnOrganizationId" />
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}" />
	<hdiits:hidden name="hdnDuration" id="hdnDuration" />
	<hdiits:hidden name="hdnIsContinue" id="hdnIsContinue" />
	
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
