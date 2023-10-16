
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.eis.empNomineeLables" var="nomineeLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hrms/eis/Familyaddress.js"></script>
<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/NomineeDtls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<script type="text/javascript">
	var nomineeDtlsAlertMsgArr = new Array();
	nomineeDtlsAlertMsgArr[0]='<fmt:message  bundle="${nomineeLables}" key="eis.ValidShareAlert"/>';
	nomineeDtlsAlertMsgArr[1]='<fmt:message  bundle="${nomineeLables}" key="eis.Nomin_Total_Share"/>';
	nomineeDtlsAlertMsgArr[2]='<fmt:message  bundle="${nomineeLables}" key="eis.alt_draft"/>';
	nomineeDtlsAlertMsgArr[3]='<fmt:message  bundle="${nomineeLables}" key="eis.date_proper"/>';
	nomineeDtlsAlertMsgArr[4]='<fmt:message  bundle="${nomineeLables}" key="eis.ajax_error"/>';
	nomineeDtlsAlertMsgArr[5]='<fmt:message  bundle="${nomineeLables}" key="eis.PersionAlert"/>';
	nomineeDtlsAlertMsgArr[6]='<fmt:message  bundle="${nomineeLables}" key="eis.Nominee_Type"/>';
	nomineeDtlsAlertMsgArr[7]='<fmt:message  bundle="${nomineeLables}" key="eis.Nominee_Added"/>';
	nomineeDtlsAlertMsgArr[8]='<fmt:message  bundle="${nomineeLables}" key="eis.edit_mode"/>';
	nomineeDtlsAlertMsgArr[9]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeValidNameAlert"/>';
	nomineeDtlsAlertMsgArr[10]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeNameAlert"/>';
	nomineeDtlsAlertMsgArr[11]='<fmt:message  bundle="${nomineeLables}" key="eis.share_msg"/>';
	nomineeDtlsAlertMsgArr[12]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeOtherRelation"/>';
	nomineeDtlsAlertMsgArr[13]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeDOB"/>';
	nomineeDtlsAlertMsgArr[14]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeValidGuardianFName"/>';
	nomineeDtlsAlertMsgArr[15]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeGuardianFName"/>';
	nomineeDtlsAlertMsgArr[16]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeValidGuardianLName"/>';
	nomineeDtlsAlertMsgArr[17]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeGuardianLName"/>';
	nomineeDtlsAlertMsgArr[18]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeValidGuardianMName"/>';
	nomineeDtlsAlertMsgArr[19]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeGuardianOtherRelation"/>';
	nomineeDtlsAlertMsgArr[20]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeGuardianRelation"/>';
	nomineeDtlsAlertMsgArr[21]='<fmt:message  bundle="${nomineeLables}" key="eis.FamilyAddressAlert"/>';
	nomineeDtlsAlertMsgArr[22]='<fmt:message  bundle="${nomineeLables}" key="eis.GuardianAddressAlert"/>';
	nomineeDtlsAlertMsgArr[23]='<fmt:message  bundle="${nomineeLables}" key="eis.nominee_name"/>';
	nomineeDtlsAlertMsgArr[24]='<fmt:message  bundle="${nomineeLables}" key="eis.DATE_OF_BIRTH"/>';
	nomineeDtlsAlertMsgArr[25]='<fmt:message  bundle="${nomineeLables}" key="eis.GuardianFName"/>';
	nomineeDtlsAlertMsgArr[26]='<fmt:message  bundle="${nomineeLables}" key="eis.GuardianLName"/>';
	nomineeDtlsAlertMsgArr[27]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeGuardianRel"/>';
	nomineeDtlsAlertMsgArr[28]='<fmt:message  bundle="${nomineeLables}" key="eis.GuardianAddress"/>';
	nomineeDtlsAlertMsgArr[29]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeAddress"/>';
	nomineeDtlsAlertMsgArr[30]='<fmt:message  bundle="${nomineeLables}" key="eis.NomineeRelation"/>';
	nomineeDtlsAlertMsgArr[31]='<fmt:message  bundle="${nomineeLables}" key="eis.isrequired"/>';
	nomineeDtlsAlertMsgArr[32]='<fmt:message  bundle="${nomineeLables}" key="eis.alt_submit"/>';
	nomineeDtlsAlertMsgArr[33]="<fmt:message  bundle="${nomineeLables}" key="eis.Yes"/>";
	nomineeDtlsAlertMsgArr[34]="<fmt:message  bundle="${nomineeLables}" key="eis.No"/>";
	nomineeDtlsAlertMsgArr[35]='<fmt:message  bundle="${nomineeLables}" key="eis.updation"/>';
	nomineeDtlsAlertMsgArr[36]='<fmt:message  bundle="${nomineeLables}" key="eis.Deletion"/>';
	nomineeDtlsAlertMsgArr[37]='<fmt:message  bundle="${nomineeLables}" key="eis.new_add"/>';
	nomineeDtlsAlertMsgArr[38]='<fmt:message  bundle="${nomineeLables}" key="eis.Hide"/>';
	nomineeDtlsAlertMsgArr[39]='<fmt:message  bundle="${nomineeLables}" key="eis.select"/>';
	nomineeDtlsAlertMsgArr[40]='<fmt:message  bundle="${nomineeLables}" key="eis.crntRqst"/>';
	nomineeDtlsAlertMsgArr[41]='<fmt:message  bundle="${nomineeLables}" key="eis.draftRqst"/>';
	nomineeDtlsAlertMsgArr[42]='<fmt:message  bundle="${nomineeLables}" key="eis.pndngRqst"/>';	
</script>


<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xmlFile" value="${resValue.xmlFile}"></c:set>
<c:set var="RelationlookUpList" value="${resValue.Relation}"></c:set>
<c:set var="familyNamelstObj" value="${resValue.FamilyName}"></c:set>
<c:set var="benefitType" value="${resValue.benefitType}"></c:set>
<c:set var="benefitName" value="${resValue.benefitName}"></c:set>
<c:set var="approvedData" value="${resValue.approvedData}"></c:set>
<c:set var="pendingData" value="${resValue.pendingData}"></c:set>
<c:set var="draftData" value="${resValue.draftData}"></c:set>
<c:set var="showLinks" value="${resValue.requesetLstObj}"></c:set>
<c:set var="share" value="${resValue.share}"></c:set>
<c:set var="familyMemeberlst" value="${resValue.familyMemeber}"></c:set>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="workFlowEnabled" value="${resValue.blnWorkFlowEnabled}"></c:set>
<c:set var="hrEisEmpNomineeVOList" value="${resValue.hrEisEmpNomineeVOList}"></c:set>
<c:set var="xmlFilePathNameForMulAddRec" value="${resValue.xmlFilePathNameForMulAddRec}" />

<hdiits:form name="frmNominee" validate="true" method="POST" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<c:if test="${workFlowEnabled eq 'true'}">
			<!-- IFMS -->
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.nominee_dtls" bundle="${nomineeLables}" /></b></a></li>
		</c:if>
		<c:if test="${workFlowEnabled eq 'false'}">
			<!-- IFMS -->
			
			<li class="selected"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="EIS.PersonalDtls" bundle="${nomineeLables}"/> </b> </a></li>
			
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.quali_dtls"
				bundle="${nomineeLables}"></fmt:message> </b> </a></li>

			<li class="selected" style="width: 152px;"><a href="#" style="width: 142px;" 
				onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.extraCoCurricularDtls"
				bundle="${nomineeLables}"></fmt:message> </b> </a></li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.FamilyDtls"
				bundle="${nomineeLables}" /> </b> </a></li>

			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.nominee_dtls" bundle="${nomineeLables}" /></b></a></li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"> <b>
			<fmt:message key="EIS.EmpMislDtl" bundle="${nomineeLables}" />
			</b> </a></li>
		</c:if>
	</ul>
	</div>
	<div id="nominee" name="nominee">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<%@ include file="empInfo/EmployeeInfo.jsp"%>
	
	<c:if test="${benefitType eq 'Gratuity'}">
		<hdiits:fmtMessage key="eis.Nominee_Gratuity" bundle="${nomineeLables}" var="NomineeTypeTitle" />
	</c:if>
	<c:if test="${benefitType eq 'GPF'}">
		<hdiits:fmtMessage key="eis.Nominee_GPF" bundle="${nomineeLables}" var="NomineeTypeTitle" />
	</c:if>

<hdiits:fieldGroup id="nomineeFieldGroupId" titleCaption="${NomineeTypeTitle}">
	<table class="tabtable" align="center">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center">
				<font color="#ffffff"> <strong><u><fmt:message bundle="${nomineeLables}" key="eis.nominee_type"/>&nbsp;-&nbsp;<b>
				<c:if test="${benefitType eq 'Gratuity'}"><fmt:message bundle="${nomineeLables}" key="eis.Gratuity"/></c:if>
				<c:if test="${benefitType eq 'GPF'}"><fmt:message bundle="${nomineeLables}" key="eis.GPF"/></c:if>
				</b></u></strong> </font>
			</td>
		</tr>-->
		<tr style="display:none" id="errorMessage">
			<td class="fieldLabel" width="100%" rowspan="2" colspan="4"><textarea
				name="errorBox" id="errorBox" readonly="readonly"
				style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #eeeeee;"></textarea>
			</td>
		</tr>
	</table>
	<table class="tabtable">	
		<tr>
			<td class="fieldLabel" width="25%">
				<b><hdiits:caption captionid="eis.nominee_type" bundle="${nomineeLables}" /></b>
			</td>
			<td class="fieldLabel" width="25%">
				<hdiits:radio name="nomiType" id="nomiType" value="Other" onclick="ShowMe(1)" captionid="eis.other" bundle="${nomineeLables}"/>
				<hdiits:radio name="nomiType" id="nomiType" value="Family" onclick="ShowMe(2)" captionid="eis.family" bundle="${nomineeLables}"/>
			</td>
			<td class="fieldLabel" width="25%" id="fmnameTab" style="display:none">
				<b><hdiits:caption captionid="eis.NAME" bundle="${nomineeLables}" /></b>
			</td>
			<td class="fieldLabel" id="fmNameTabCmb" style="display:none" width="25%">
				<hdiits:select captionid="eis.NAME" bundle="${nomineeLables}" id="firstNameCmb"	name="firstNameCmb" onchange="showDtlsForRelation(this);">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${nomineeLables}" />--</hdiits:option>
						<c:forEach var="name" items="${familyNamelstObj}">
							<option value="<c:out value="${name}"/>"><c:out
								value="${name}" /></option>
						</c:forEach>
				</hdiits:select>
			</td>
		</tr>
	
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="nomn_name"
				id="nomn_name" captionid="eis.nominee_name" bundle="${nomineeLables}" size="25" maxlength="100"
				mandatory="true" /></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.share" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text captionid="eis.share" bundle="${nomineeLables}"
				mandatory="true" maxlength="6" id="nomi_share" name="nomi_share" onkeypress="return checkNumberForOnlyOneDot(this.value)" style="text-align: right"></hdiits:text></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Relation" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Relation" bundle="${nomineeLables}" readonly="true" id="nomi_Relation"
				name="nomi_Relation" onchange="showOtherText(this,1)">
				<hdiits:option value="Select">
					<fmt:message key="eis.select" bundle="${nomineeLables}" />
				</hdiits:option>
				<c:forEach var="relation" items="${RelationlookUpList}">
					<option value="<c:out value="${relation.lookupName}"/>"><c:out
						value="${relation.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td colspan="2">
			<table width="100%" id="other_nomi_relation"
				name="other_nomi_relation" style="display:none">
				<tr>
					<td class="fieldLabel" width="50%"><b><hdiits:caption
						captionid="eis.otherRelation" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" width="50%"><hdiits:text
						name="nomi_otherRelation" id="nomi_otherRelation" mandatory="true"
						captionid="eis.otherRelation" bundle="${nomineeLables}" maxlength="25" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /> </b>(DD/MM/YYYY)</td>
			<td class="fieldLabel" width="25%">
				<hdiits:dateTime name="nomi_DOB" captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}"  validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="minorOrNot()"/></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.age" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text
				name="nomi_personAge" id="nomi_personAge"
				style="color: black; font-family: Verdana; background-color: lightblue;text-align: right"
				readonly="true" maxlength="3" captionid="eis.age" bundle="${nomineeLables}"></hdiits:text></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<table id="nomineeAddressTable" name="nomineeAddressTable" class="tabtable" style="display:none" width="100%">
		<tr>
			<hdiits:fmtMessage key="eis.NomineeAddress" bundle="${nomineeLables}" var="nomineeAddressTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel"><jsp:include
				page="../../common/address.jsp">
				<jsp:param name="addrName" value="NomineeAddress" />
				<jsp:param name="addressTitle" value="${nomineeAddressTitle}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Yes" />
				<jsp:param name="collapseAddress" value="true"/>
			</jsp:include></td>
		</tr>
	</table>
	<br>
	<div class=tabtable id="guardianTable" name="guardianTable" style="display:none">
	<hdiits:fieldGroup id="nomineGuardianFieldGroupId" titleCaptionId="eis.guardianDtls" bundle="${nomineeLables}" collapseOnLoad="true">
	<table id="nomineeGuardianDtlsTable" width="100%" >
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${nomineeLables}" key="eis.guardianDtls"/></u></strong> </font></td>
		</tr>-->
		<tr>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.LAST_NAME" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.FIRST_NAME" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.MIDDLE_NAME" bundle="${nomineeLables}" /></b></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.NAME" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text
				name="gur_last_name" id="gur_last_name" captionid="eis.NAME" bundle="${nomineeLables}"
				mandatory="true" maxlength="25" /></td>
			<td class="fieldLabel" width="25%"><hdiits:text
				name="gur_first_name" id="gur_first_name" captionid="eis.NAME" bundle="${nomineeLables}"
				mandatory="true" maxlength="25" /></td>
			<td class="fieldLabel" width="25%"><hdiits:text
				name="gur_middle_name" id="gur_middle_name"
				captionid="eis.NAME" bundle="${nomineeLables}" maxlength="25" /></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Relation" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Relation" bundle="${nomineeLables}" id="gur_Relation" name="gur_Relation"
				mandatory="true" onchange="showOtherText(this,2)" sort="false">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${nomineeLables}" />--</hdiits:option>
				<c:forEach var="relation" items="${RelationlookUpList}">
					<option value="<c:out value="${relation.lookupName}"/>"><c:out
						value="${relation.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td colspan="2">
			<table class="tabtable" width="100%" id="other_gur_relation"
				name="other_gur_relation" style="display:none">
				<tr>
					<td class="fieldLabel" width="50%"><b><hdiits:caption
						captionid="eis.otherRelation" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" width="50%"><hdiits:text
						name="gur_otherRelation" id="gur_otherRelation"
						captionid="eis.otherRelation" bundle="${nomineeLables}" maxlength="25" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table id="nomineeGuardianAddressTable" width="100%">
		<tr>
		<hdiits:fmtMessage key="eis.GuardianAddress" bundle="${nomineeLables}" var="guardianAddressTitle" ></hdiits:fmtMessage>
			<td>
			<jsp:include page="../../common/address.jsp">
				<jsp:param name="addrName" value="GuardianAddress" />
			<jsp:param name="addressTitle" value="${guardianAddressTitle}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Yes" />
				<jsp:param name="collapseAddress" value="true"/>
			</jsp:include></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</div>
	<BR>
	<table align="center">
		<tr>
			<td align="center"><c:set var="addToolTip" value="To Add a New Record in Current Request Table"></c:set> 
			<c:set	var="saveToolTip" value="To Update a Record in Current Request Table"></c:set> 
			<hdiits:button	name="Add_nominee" type="button" captionid="EIS.Add" bundle="${nomineeLables}" onclick="AddNominee()"	title="${addToolTip}"></hdiits:button>
			&nbsp;&nbsp; <hdiits:button	name="Save_nominee" type="button" captionid="EIS.Update" bundle="${nomineeLables}" readonly="true"	onclick="SaveNominee()" title="${saveToolTip}"></hdiits:button>
			&nbsp;&nbsp;<hdiits:button type="button" name="Reset" id="Reset" captionid="EIS.Reset" bundle="${nomineeLables}" onclick="resetAllFields()"></hdiits:button> 
		</tr>
	</table>

<BR>

<hdiits:fieldGroup id="currentRequestFieldGroupId" titleCaptionId="eis.cur_req" bundle="${nomineeLables}" collapseOnLoad="false">
	<c:if test="${workFlowEnabled eq 'true'}">
		<!--<table class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4"  align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${nomineeLables}" key="eis.cur_req"/></u></strong> </font></td>
			</tr>
		</table><br>-->
	</c:if>
	
	<table width="100%" name="nomineeAddDataTable" id="nomineeAddDataTable"
		align="center" border="1" borderColor="black" cellpadding="1" cellspacing="1" style="border-collapse: collapse; display:none"
		BGCOLOR="WHITE" class="TableBorderLTRBN">

		<tr>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b>(DD/MM/YYYY)</td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.age" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.share" bundle="${nomineeLables}" /></b></td>
			<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
				captionid="eis.Links" bundle="${nomineeLables}" /></b></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	<BR>
	
	<table align="center">
		<tr>
			<c:if test="${workFlowEnabled eq 'true'}">	
				<td align="center"><hdiits:button name="btnSubmit" type="button" captionid="EIS.Submit" bundle="${nomineeLables}" readonly="true" onclick="SubmitNominee()"></hdiits:button>
				&nbsp;&nbsp;<hdiits:button name="btnSave_draft" type="button" captionid="EIS.SaveAsDraft" bundle="${nomineeLables}" readonly="true" onclick="Save_Draft()"></hdiits:button>
				&nbsp;&nbsp;<hdiits:button type="button" name="BACK" captionid="EIS.Back" bundle="${nomineeLables}" onclick="goToPurposePage(true);"  />
				&nbsp;&nbsp;<hdiits:button type="button" name="Close" captionid="EIS.Cancel" bundle="${nomineeLables}" onclick="goToHomePage()" />
				</td>
			</c:if>
			<c:if test="${workFlowEnabled eq 'false'}">
 				 <td align="center"><hdiits:button type="button" name="btnSubmit" captionid="EIS.Submit" bundle="${nomineeLables}" onclick="checkValidSharePercentage()"></hdiits:button> 
   				 &nbsp;&nbsp;<hdiits:button type="button" name="BACK" captionid="EIS.Back" bundle="${nomineeLables}" onclick="goToPurposePage(false);"  />
   				&nbsp;&nbsp;<hdiits:button type="button" name="Close" captionid="EIS.Cancel" bundle="${nomineeLables}" onclick="window.close();" /></td>
			</c:if> 
		</tr>
	</table>
	<BR>
	<c:if test="${workFlowEnabled eq 'false'}">
		<c:forEach items="${hrEisEmpNomineeVOList}" var="NomineeTuples"
			varStatus="x">
			<c:set var="curXMLFileName"
				value="${xmlFilePathNameForMulAddRec[x.index]}"></c:set>
			<c:set var="nomnName" value="${NomineeTuples.nomnName}" />
			<c:set var="nomnRelation"
				value="${NomineeTuples.cmnLookupMstByNomnRelation.lookupDesc}"></c:set>
			<c:set var="fmDOB" value="${NomineeTuples.nomnDob}" />
			<fmt:formatDate value="${fmDOB}" pattern="dd/MM/yyyy" var="dob" />

			<script>v_age=countAge('${dob}'); </script>
			<c:set var="nomnMinor" value="${nomineeTuples.nomnMinor}" />
			<c:set var="nomnShare" value="${NomineeTuples.nomnSharePercent}" />
			<c:set var="srNo" value="${NomineeTuples.memberId}" />
			<script type="text/javascript">
				var nomn_yes_no='${nomnMinor}';
				var dispMinor;
				if(nomn_yes_no=='Y')
				{
					dispMinor='<fmt:message bundle="${nomineeLables}" key="eis.Yes"/>';
				}
				else
					dispMinor='<fmt:message bundle="${nomineeLables}" key="eis.No"/>';
					
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA = new Array('${nomnName}','${nomnRelation}','${dob}',v_age,dispMinor,'${nomnShare}');
				showLinksId= addDBDataInTable('nomineeAddDataTable','addedPunch',displayFieldA,xmlFileName,'editRecord', 'deleteNomineeDBRecord');
				</script>

			<c:set var="memeberNo" value="0"></c:set>
			<c:set var="member" value="${familyMemeberlst[x.index]}"></c:set>
			<c:if test="${memeberNo != member}">
				<script>
					var member = "${member}";
					if (member != "")
					{
						AddMemeber('${member}/'+showLinksId);
					}
				</script>
			</c:if>
		</c:forEach>
	</c:if>
	
	
	<c:if test="${workFlowEnabled eq 'true'}">
		<hdiits:fieldGroup id="approvedRequestFieldGroupId" titleCaptionId="eis.app_req" bundle="${nomineeLables}" collapseOnLoad="true">
			<!--<table class="tabtable">
				<tr bgcolor="#386CB7">
					<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
					<strong><u><fmt:message bundle="${nomineeLables}" key="eis.app_req"/></u></strong></font></td>
				</tr>
			</table><br>-->
			
			<c:if test="${empty approvedData}">
				<center><b><hdiits:caption captionid="eis.no_draft_req" bundle="${nomineeLables}" /></b></center>
			</c:if>
			
			<c:if test="${not empty approvedData}">
				<table name="nomineeApproveDataTable" border="1" align="center"
					cellpadding="0" cellspacing="0" BGCOLOR="WHITE" borderColor="black" style="border-collapse: collapse;"
					class="TableBorderLTRBN" id="nomineeApproveDataTable" width="100%">
					<tr>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b>(DD/MM/YYYY)</td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.age" bundle="${nomineeLables}" /></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.share" bundle="${nomineeLables}" /></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
							captionid="eis.Links" bundle="${nomineeLables}" /></b></td>
					</tr>
				</table>
	
				<c:if test="${not empty approvedData}">
					<c:set var="yes" value="Yes"></c:set>
					<c:set var="no" value="No"></c:set>
					<c:set var="delete" value="delete"></c:set>
					<c:set var="draft" value="draft"></c:set>
					<c:set var="pending" value="pending"></c:set>
					
					<c:forEach items="${approvedData}" var="nomineeTuples" varStatus="x">
	
						<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>
						<c:set var="nomnName" value="${nomineeTuples.nomnName}" />
						<c:set var="nomnRelation" value="${nomineeTuples.cmnLookupMstByNomnRelation.lookupDesc}"></c:set>
						<c:set var="fmDOB" value="${nomineeTuples.nomnDob}" />
						<fmt:formatDate value="${fmDOB}" pattern="dd/MM/yyyy" var="dob" />
						<script>v_age=countAge('${dob}'); </script>
						
						<c:set var="nomnMinor" value="${nomineeTuples.nomnMinor}" />
						<c:set var="nomnShare" value="${nomineeTuples.nomnSharePercent}" />
						<c:set var="srNo" value="${nomineeTuples.memberId}" />
						<c:set var="xml" value="${showLinks[x.index]}"></c:set>
						
						<script type="text/javascript">		
							var nomn_yes_no='${nomnMinor}';
							var dispMinor;
							if(nomn_yes_no=='Y')
							{
								dispMinor='<fmt:message bundle="${nomineeLables}" key="eis.Yes"/>';
							}
							else
								dispMinor='<fmt:message bundle="${nomineeLables}" key="eis.No"/>';
							
							var xmlFileName = '${curXMLFileName}';													
							var displayFieldA  = new Array('${nomnName}','${nomnRelation}','${dob}',v_age,dispMinor,'${nomnShare}');
							showLinksId=addDBDataInTable('nomineeApproveDataTable','encXML',displayFieldA,xmlFileName,'editRecord', 'deleteNomineeDBRecord','');
							dbRowsId[dbRowsIdCount]=showLinksId;
							dbRowsIdCount=dbRowsIdCount+1;
							dbRowsSrNo[dbRowsSrNoCount] = '${srNo}/'+showLinksId;
							dbRowsSrNoCount=dbRowsSrNoCount+1;
						</script>
	
						<c:set var="memeberNo" value="0"></c:set>
						<c:set var="member" value="${familyMemeberlst[x.index]}"></c:set>
						<c:if test="${memeberNo != member}">
							<script>AddMemeber('${member}/'+showLinksId);</script>
						</c:if>
						
	
						<c:if test="${draft == xml}">
							<script>
								trow = document.getElementById(showLinksId);														
								//trow.childNodes[7].style.display='none';
								trow.childNodes[7].innerHTML=nomineeDtlsAlertMsgArr[41];
							</script>
						</c:if>
						<c:if test="${pending == xml}">
							<script>
								trow = document.getElementById(showLinksId);														
								trow.childNodes[7].innerHTML=nomineeDtlsAlertMsgArr[42];
							</script>
						</c:if>
					</c:forEach>
				</c:if>
			</c:if>
			<c:set var="memeberNo" value="0"></c:set>
			<c:forEach var="member" items="${familyMemeberlst}">
				<c:if test="${memeberNo != member}">
					<script>AddMemeber('${member}');</script>
				</c:if>
			</c:forEach>
		</hdiits:fieldGroup>
		<BR>
		<hdiits:fieldGroup id="pendingRequestFieldGroupId" titleCaptionId="eis.pending_req" bundle="${nomineeLables}" collapseOnLoad="true">		
			<!-- <table class="tabtable">
				<tr bgcolor="#386CB7">
					<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
					<strong><u><fmt:message bundle="${nomineeLables}" key="eis.pending_req"/></u></strong></font></td>
				</tr>
			</table><br>-->
			<c:if test="${empty pendingData}">
				<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${nomineeLables}" /></b></center>
			</c:if>
			
			<c:if test="${not empty pendingData}">
				<table id="PendingTable" name="PendingTable"
					border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" BGCOLOR="WHITE"
					class="TableBorderLTRBN" align="center" width="50%">
					<tr>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.srno" bundle="${nomineeLables}"></hdiits:caption></b></td>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.date_submit" bundle="${nomineeLables}"></hdiits:caption></b></td>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.person_with" bundle="${nomineeLables}"></hdiits:caption></b></td>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.Links" bundle="${nomineeLables}"></hdiits:caption></b></td>
					</tr>
					<c:forEach var="pendungArrObj" items="${pendingData}">
						<tr>
							<td><script> 
							var v1=increment();
							document.write(v1);
						</script></td>
							<td><c:set var="creatDate" value="${pendungArrObj[1]}"></c:set>
							<fmt:formatDate type="date" pattern="dd/MM/yyyy"
								value="${creatDate}"></fmt:formatDate></td>
							<td><c:out value="${pendungArrObj[2]}"></c:out></td>
							<td><a href=javascript:void('Request') onclick=javascript:viewPendingRequestDtls('${pendungArrObj[0]}')><fmt:message  bundle="${nomineeLables}" key="eis.View"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<BR>
			<table name="pendingDataTable" id="pendingDataTable" borderColor="black" cellpadding="1" cellspacing="1" style="border-collapse: collapse; display:none" BGCOLOR="WHITE" class="TableBorderLTRBN"
				 border="1" align="center" width="100%">
	
				<tr>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b>(DD/MM/YYYY)</td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.age" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.share" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.actions" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.Links" bundle="${nomineeLables}"></hdiits:caption></b></td>
				</tr>
			</table>
		</hdiits:fieldGroup>	
		<BR>
		<hdiits:fieldGroup id="draftRequestFieldGroupId" titleCaptionId="eis.draft_req" bundle="${nomineeLables}" collapseOnLoad="true">		
			<!--<table class="tabtable">
				<tr bgcolor="#386CB7">
					<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
					<strong><u><fmt:message bundle="${nomineeLables}" key="eis.draft_req"/></u></strong></font></td>
				</tr>
			</table><br>-->
			
			
			<c:if test="${empty draftData}">
				<center><b><hdiits:caption captionid="eis.no_draft_req" bundle="${nomineeLables}" /></b></center>
			</c:if>
			<c:if test="${not empty draftData}">
				<table id="RequestTable" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse"
					BGCOLOR="WHITE" class="TableBorderLTRBN" name="RequestTable"
					align="center" border="1" width="50%">
					<tr>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.srno" bundle="${nomineeLables}"></hdiits:caption></b></td>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.date_submit" bundle="${nomineeLables}"></hdiits:caption></b></td>
						<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
							captionid="eis.Links" bundle="${nomineeLables}"></hdiits:caption></b></td>
					</tr>
					<c:forEach var="draftArrObj" items="${draftData}">
						<tr id="${draftArrObj[1]}">
							<td><script> 
							var v1=incrementDraft();
							document.write(v1);
						</script></td>
							<td><c:set var="creatDate" value="${draftArrObj[0]}"></c:set>
							<fmt:formatDate type="date" pattern="dd/MM/yyyy"
								value="${creatDate}"></fmt:formatDate></td>
							<td><a href=javascript:void('Request') onclick=javascript:viewDraftRequestDtls('${draftArrObj[1]}')><fmt:message  bundle="${nomineeLables}" key="eis.View"/></a>
							/ <a href=javascript:void('Request') onclick=javascript:openDraftRequestDtls('${draftArrObj[1]}')><fmt:message  bundle="${nomineeLables}" key="eis.Open"/></a>
							/ <a href=javascript:void('Request') onclick=javascript:deleteDraftRequestDtls('${draftArrObj[1]}')><fmt:message  bundle="${nomineeLables}" key="eis.delete"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<BR>
			<table name="draftDataTable" id="draftDataTable" border="1"
				borderColor="black" cellpadding="1" cellspacing="1" style="border-collapse: collapse; display:none" BGCOLOR="WHITE"
				class="TableBorderLTRBN" align="center"
				width="100%">
				<tr>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.nominee_name" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.relation_nominee" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.DATE_OF_BIRTH" bundle="${nomineeLables}" /></b>(DD/MM/YYYY)</td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.age" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.minor" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.share" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption
						captionid="eis.actions" bundle="${nomineeLables}" /></b></td>
					<td class="fieldLabel" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.Links" bundle="${nomineeLables}"></hdiits:caption></b></td>
				</tr>
			</table>
		</hdiits:fieldGroup>
	</c:if> 
	<hdiits:hidden name="srNo" id="srNo" /> 
	<hdiits:hidden name="benefitType" id="benefitType" default="${benefitType}" /> 
	<hdiits:hidden name="minorHidden" id="minorHidden" default="N" /> 
	<hdiits:hidden name="nomi_Relation_id" id="nomi_Relation_id" /> 
	<hdiits:hidden name="Share" id="Share" default="${share}" /> 
	<hdiits:hidden name="nomn_nameCmb1" id="nomn_nameCmb1" default="-" /> 
	<hdiits:hidden name="nomi_RelationCmb1" id="nomi_RelationCmb1" default="-" /> 
	<hdiits:hidden name="nomi_DOBCmb1" id="nomi_DOBCmb1" default="-" /> 
	<hdiits:hidden name="nomi_personAgeCmb1" id="nomi_personAgeCmb1" default="-" /> 
	<hdiits:hidden name="nomi_shareCmb1" id="nomi_shareCmb1" default="-" /> <!--  IFMS -->
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"/>

	<c:if test="${workFlowEnabled eq 'false'}">
		<c:set var="memeberNo" value="0"></c:set>

		<c:forEach var="member" items="${familyMemeberlst}">
			<c:if test="${memeberNo != member}">
				<script>AddMemeber('${member}');</script>
			</c:if>
		</c:forEach>
	</c:if></div>
	</div></div>
	<c:if test="${workFlowEnabled eq 'false'}">
	
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="gur_first_name,gur_first_name" />
	</c:if>
	
	<script type="text/javascript">
		checkMeForGPF('${benefitType}');
		var checkFamilyRec=document.frmNominee.nomiType;
		checkFamilyRec[1].checked=true;
		ShowMe(2);
		var workFlowEnabled = ${workFlowEnabled};
		initializetabcontent("maintab")
	</script>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
