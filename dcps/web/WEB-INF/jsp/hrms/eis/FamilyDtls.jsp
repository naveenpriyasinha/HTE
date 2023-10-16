<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.eis.empFamilyLables" var="familyLables" scope="request" />
<html>
<head>
<script type="text/javascript" src="script/hrms/eis/Familyaddress.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/FamilyDtls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>


<script language="javascript">

	var navDisplay = true,allowSubmit=true,chkValidateRow,depedenetMember=-1,setMeForLink=0;
	var globalFlagDeleteDisable = false,checkDraftOrPendingReq = false,dbRecordUpdateOrDelete=false;
	var showLinksId,count=0,draftFlag=0,openReqId,dbRowsIdCount=0,dbRowsSrNoCount=0;
	var dbRowId,dbRows=0,deleteArrCount=0,draftArrCount=-1,deleteAppArrCount=0,mappingArrCount=0,dependentNomineeArrCount=0;
	var editRowId,dbRecord=0;
	var setCombo=-1;	 
	var setOccuCombo=-1;
	var setdiscipline=-1; 
	var globalflagForDBRecord=false;
	var v1=0;
	var v1_pen=0;
	var v1_d=0;
	var xmlHttp = null;
	var dbRowsSrNo = new Array();
	var dbRowsId = new Array();
	var draftArr = new Array();
	var dependentNomineeArr = new Array();
	var deleteArr = new Array();
	var mappingArr = new Array();
	var deleteAppArr =  new Array();
	var deadOrAliveArr = new Array();
	var displayFieldArrayInTable = new Array('empFmFullName','GenderCmb1','RelationCmb1','statusCmb1','DOBCmb1','EmploymentCmb1','OccupationCmb1','MaritalStatusCmb1','QualificationCmb1','RemarksCmb1');
	
	var FamilyDtlsAlertMsgArr=new Array();
	FamilyDtlsAlertMsgArr[0] ='<fmt:message  bundle="${familyLables}" key="eis.submitDraftAlert"/>';
	FamilyDtlsAlertMsgArr[1] ='<fmt:message  bundle="${familyLables}" key="eis.alt_draft"/>';
	FamilyDtlsAlertMsgArr[2] ='<fmt:message  bundle="${familyLables}" key="eis.submitAlert"/>';	
	FamilyDtlsAlertMsgArr[3] ='<fmt:message  bundle="${familyLables}" key="eis.alt_submit"/>';
	FamilyDtlsAlertMsgArr[4] ='<fmt:message  bundle="${familyLables}" key="eis.EditModeAlert"/>';
	FamilyDtlsAlertMsgArr[5] ='<fmt:message  bundle="${familyLables}" key="eis.date_proper"/>';
	FamilyDtlsAlertMsgArr[6] ='<fmt:message  bundle="${familyLables}" key="eis.ajax_error"/>';
	FamilyDtlsAlertMsgArr[7] ='<fmt:message  bundle="${familyLables}" key="eis.select"/>';
	FamilyDtlsAlertMsgArr[8] ='<fmt:message  bundle="${familyLables}" key="eis.updation"/>';
	FamilyDtlsAlertMsgArr[9] ='<fmt:message  bundle="${familyLables}" key="eis.Deletion"/>';
	FamilyDtlsAlertMsgArr[10]='<fmt:message  bundle="${familyLables}" key="eis.new_add"/>';
	FamilyDtlsAlertMsgArr[11]='<fmt:message  bundle="${familyLables}" key="eis.Hide"/>';
	FamilyDtlsAlertMsgArr[12]='<fmt:message  bundle="${familyLables}" key="eis.CompanyValidNameAlert"/>';
	FamilyDtlsAlertMsgArr[13]='<fmt:message  bundle="${familyLables}" key="eis.AnnualIncomeAlert"/>';
	FamilyDtlsAlertMsgArr[14]='<fmt:message  bundle="${familyLables}" key="eis.DesignationAlert"/>';
	FamilyDtlsAlertMsgArr[15]='<fmt:message  bundle="${familyLables}" key="eis.ValidLNameAlert"/>';
	FamilyDtlsAlertMsgArr[16]='<fmt:message  bundle="${familyLables}" key="eis.ValidFNameAlert"/>';
	FamilyDtlsAlertMsgArr[17]='<fmt:message  bundle="${familyLables}" key="eis.ValidMNameAlert"/>';
	FamilyDtlsAlertMsgArr[18]='<fmt:message  bundle="${familyLables}" key="eis.DODAlert"/>';
	FamilyDtlsAlertMsgArr[19]='<fmt:message  bundle="${familyLables}" key="eis.DependentAlert"/>';
	FamilyDtlsAlertMsgArr[20]='<fmt:message  bundle="${familyLables}" key="eis.DeadDependentAlert"/>';
	FamilyDtlsAlertMsgArr[21]='<fmt:message  bundle="${familyLables}" key="eis.family_dead"/>';
	FamilyDtlsAlertMsgArr[22]='<fmt:message  bundle="${familyLables}" key="eis.family_DOBAlert"/>';
	FamilyDtlsAlertMsgArr[23]='<fmt:message  bundle="${familyLables}" key="eis.family_DODAlert"/>';
	FamilyDtlsAlertMsgArr[24]='<fmt:message  bundle="${familyLables}" key="eis.family_ValidDODAlert"/>';
	FamilyDtlsAlertMsgArr[25]='<fmt:message  bundle="${familyLables}" key="eis.LAST_NAME"/>';
	FamilyDtlsAlertMsgArr[26]='<fmt:message  bundle="${familyLables}" key="eis.FIRST_NAME"/>';
	FamilyDtlsAlertMsgArr[27]='<fmt:message  bundle="${familyLables}" key="eis.Relation"/>';
	FamilyDtlsAlertMsgArr[28]='<fmt:message  bundle="${familyLables}" key="eis.GENDER"/>';
	FamilyDtlsAlertMsgArr[29]='<fmt:message  bundle="${familyLables}" key="eis.DATE_OF_BIRTH"/>';
	FamilyDtlsAlertMsgArr[30]='<fmt:message  bundle="${familyLables}" key="eis.status"/>';
	FamilyDtlsAlertMsgArr[31]='<fmt:message  bundle="${familyLables}" key="eis.date_Demise"/>';
	FamilyDtlsAlertMsgArr[32]='<fmt:message  bundle="${familyLables}" key="eis.Marital_Status"/>';
	FamilyDtlsAlertMsgArr[33]='<fmt:message  bundle="${familyLables}" key="eis.fm_dependant_dtls"/>';
	FamilyDtlsAlertMsgArr[34]='<fmt:message  bundle="${familyLables}" key="eis.Employment_Status"/>';
	FamilyDtlsAlertMsgArr[35]='<fmt:message  bundle="${familyLables}" key="eis.Nationality"/>';
	FamilyDtlsAlertMsgArr[36]='<fmt:message  bundle="${familyLables}" key="eis.Employment_Type"/>';
	FamilyDtlsAlertMsgArr[37]='<fmt:message  bundle="${familyLables}" key="EIS.Dept"/>';										
	FamilyDtlsAlertMsgArr[38]='<fmt:message  bundle="${familyLables}" key="eis.FamilyMemAdd"/>';
	FamilyDtlsAlertMsgArr[39]='<fmt:message  bundle="${familyLables}" key="eis.isrequired"/>';
	FamilyDtlsAlertMsgArr[40]='<fmt:message  bundle="${familyLables}" key="eis.error"/>';
	FamilyDtlsAlertMsgArr[41]='<fmt:message  bundle="${familyLables}" key="eis.other"/>';
	FamilyDtlsAlertMsgArr[42]='<fmt:message  bundle="${familyLables}" key="eis.DuplicateFatherRec"/>';
	FamilyDtlsAlertMsgArr[43]='<fmt:message  bundle="${familyLables}" key="eis.DuplicateMotherRec"/>';
	FamilyDtlsAlertMsgArr[44]='<fmt:message  bundle="${familyLables}" key="eis.crntRqst"/>';
	FamilyDtlsAlertMsgArr[45]='<fmt:message  bundle="${familyLables}" key="eis.draftRqst"/>';
	FamilyDtlsAlertMsgArr[46]='<fmt:message  bundle="${familyLables}" key="eis.pndngRqst"/>';
	FamilyDtlsAlertMsgArr[47]='<fmt:message  bundle="${familyLables}" key="eis.Nominated"/>';
</script>
</head>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RelationlookUpList" value="${resValue.Relation}"></c:set>
<c:set var="EmpStalookUpList" value="${resValue.EmploymentStatus}"></c:set>
<c:set var="GenderlookUpList" value="${resValue.Gender}"></c:set>
<c:set var="MaritallookUpList" value="${resValue.Marital}"></c:set>
<c:set var="FmQualookUpList" value="${resValue.FmQualookUpList}"></c:set>
<c:set var="FamilyArrObj" value="${resValue.FamilyDtls}"></c:set>

<c:set var="FmDeadOrAlive" value="${resValue.DeadOrAlive}"></c:set>
<c:set var="xmlFile" value="${resValue.XmlFile}"></c:set>
<c:set var="request" value="${resValue.PendingDtls}"></c:set>
<c:set var="showLinks" value="${resValue.requesetLstObj}"></c:set>
<c:set var="draftArrObj" value="${resValue.draftRequest}"></c:set>
<c:set var="DepedentLookupLst" value="${resValue.DepedentLookupLst}"></c:set>
<c:set var="dependantNomnDtls" value="${resValue.dependantNomnDtls}"></c:set>
<c:set var="workFlowEnabled" value="${resValue.blnWorkFlowEnabled}"></c:set>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="hrEisEmpFamilyVOList" value="${resValue.hrEisEmpFamilyVOList}"></c:set>
<c:set var="xmlFilePathNameForMulAddRec" value="${resValue.xmlFilePathNameForMulAddRec}" />
<c:set var="checkDependRecOnNominee" value="${resValue.checkDependRecOnNomineeLstObj}" />
<c:set var="cmnCountryMstList" value="${resValue.cmnCountryMstList}" />
<c:set var="DeptList" value="${resValue.DeptList}" />
<script>
	var workFlowEnabled = ${workFlowEnabled};
</script>

<body>
<hdiits:form name="frmFamily" validate="true" method="POST"
	encType="multipart/form-data" action="hdiits.htm">
	<div id="tabmenu"> 
	<ul id="maintab" class="shadetabs">
		<c:if test="${workFlowEnabled eq 'true'}">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.FamilyDtls" bundle="${familyLables}"></fmt:message></b></a></li>
		</c:if>
	
		<c:if test="${workFlowEnabled eq 'false'}">
		
		<li class="selected"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="EIS.PersonalDtls" bundle="${familyLables}"/> </b> </a></li>
			
		<li class="selected">
			<a href="#" onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
				<b>
					<fmt:message key="eis.quali_dtls" bundle="${familyLables}"></fmt:message>
				</b>
			</a>
		</li>
		
		<li class="selected" style="width: 152px;">
				<a href="#" style="width: 142px;" onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')" >
				<b>
					<fmt:message key="eis.extraCoCurricularDtls" bundle="${familyLables}"></fmt:message>
				</b>
			</a>
		</li>
		
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.FamilyDtls" bundle="${familyLables}"></fmt:message></b></a></li>
		
		<li class="selected">
			<a href="#" onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')" >
				<b>
					<fmt:message key="eis.nominee_dtls" bundle="${familyLables}" />
				</b>
			</a>
		</li>
		<li class="selected">
			<a href="#"  onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')">
				<b>
					<fmt:message key="EIS.EmpMislDtl" bundle="${familyLables}" />
				</b>
			</a>
		</li>
	
	
	</c:if>
	</ul>
	</div>
	<div id="family" name="family" class="halfplustabcontentstyle">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<%@ include file="empInfo/EmployeeInfo.jsp"%>
	
	<hdiits:fieldGroup id="familyFieldGroupId" titleCaptionId="eis.FamilyDtls" bundle="${familyLables}">
	
	<table class="tabtable">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${familyLables}" key="eis.FamilyDtls"/></u></strong> </font></td>
		</tr> -->
		<tr style="display:none" id="errorMessage">
			<td class="fieldLabel" width="100%" rowspan="2" colspan="4"><textarea
				name="errorBox" id="errorBox" readonly="readonly"
				style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #eeeeee;"></textarea>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.LAST_NAME" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.FIRST_NAME" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.MIDDLE_NAME" bundle="${familyLables}"></hdiits:caption></b></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.NAME" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="lastName"	id="lastName" captionid="eis.LAST_NAME" bundle="${familyLables}" maxlength="25" mandatory="true" validation="txt.isrequired" /></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="firstName" id="firstName" captionid="eis.FIRST_NAME" bundle="${familyLables}"	maxlength="25" mandatory="true" validation="txt.isrequired" /></td>
			<td class="fieldLabel" width="25%"><hdiits:text	name="middleName" id="middleName" captionid="eis.MIDDLE_NAME" bundle="${familyLables}" maxlength="25" /></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Relation" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Relation" bundle="${familyLables}" id="Relation"
				name="Relation" mandatory="true" validation="sel.isrequired"
				onchange="return OtherRelation(this);" sort="false">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
				<c:forEach var="relation" items="${RelationlookUpList}">
					<option value="<c:out value="${relation.lookupName}"/>"><c:out
						value="${relation.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel" colspan="2">
			<table class="tabtable" id="other1" name="other1"
				style="display:none">
				<tr>
					<td class="fieldLabel" width="50%"><b><hdiits:caption
						captionid="eis.Relation" bundle="${familyLables}"></hdiits:caption></b></td>
					<td class="fieldLabel" width="50%"><hdiits:text
						name="otherRelation" id="otherRelation"
						captionid="eis.Relation" bundle="${familyLables}" mandatory="true" maxlength="20"
						validation="txt.isrequired" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.GENDER" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select name="Gender"
				id="Gender" captionid="eis.GENDER" bundle="${familyLables}"
				mandatory="true">
				<option value="Select">--<fmt:message
					key="eis.select" bundle="${familyLables}"></fmt:message>--</option>
				<c:forEach var="genderLookup" items="${GenderlookUpList}">
					<option value="<c:out value="${genderLookup.lookupName}"/>">
					<c:out value="${genderLookup.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}"></hdiits:caption> </b>(DD/MM/YYYY)</td>
			<td class="fieldLabel" width="25%">
			<hdiits:dateTime name="DOB"	afterDateSelect="countAge('','personAge','value','','DOB','','frmFamily','status');" mandatory="true" validation="txt.isdt,txt.isrequired" captionid="eis.DATE_OF_BIRTH"	bundle="${familyLables}"></hdiits:dateTime></td>
		</tr>
		<tr>
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.status" bundle="${familyLables}"></hdiits:caption></b></td>
				<td class="fieldLabel" width="25%">
					<hdiits:select name="status" id="status" captionid="eis.status" bundle="${familyLables}" mandatory="true" validation="sel.isrequired" onchange="checkForMember(this);hideInfoForDeadMember(this);">
						<option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</option>
						<c:forEach var="deadOrAlive" items="${FmDeadOrAlive}">
							<option value="<c:out value="${deadOrAlive.lookupName}"/>">
							<c:out value="${deadOrAlive.lookupDesc}" /></option>
						</c:forEach>
					</hdiits:select>
				</td>
				
				<td class="fieldLabel" colspan="2">
				<table id="member_Date_of_Demise" style="display:none" border="0">
					<tr>
						<td width="25%"><b><hdiits:caption captionid="eis.date_Demise" bundle="${familyLables}"></hdiits:caption> </b>(DD/MM/YYYY)</td>
						<td calss="fieldLabel" width="25%"><hdiits:dateTime	captionid="eis.date_Demise" bundle="${familyLables}" mandatory="true"  validation="txt.isdt,txt.isrequired" name="date_Demise" afterDateSelect="validDateDemis();" /></td>
					</tr>
				</table>
				</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.age" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="personAge"
				readonly="true"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;text-align: right;" 
				id="personAge" captionid="eis.age" bundle="${familyLables}" maxlength="3"></hdiits:text></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Marital_Status" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Marital_Status" bundle="${familyLables}"
				name="MaritalStatus" id="MaritalStatus" mandatory="true"
				validation="sel.isrequired" sort="false">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
				<c:forEach var="maritalStatus" items="${MaritallookUpList}">
					<option value="<c:out value="${maritalStatus.lookupName}"/>">
					<c:out value="${maritalStatus.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
	</table>
	
	<table class="tabtable" id="quliTable">
		<tr>
			<td class="fieldLabel" width="25%">
				<b><hdiits:caption captionid="eis.Qualification" bundle="${familyLables}" /></b>
			</td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.Qualification" bundle="${familyLables}" name="Qualification" id="Qualification" sort="false" onchange="getSubqualificationsLst(this);">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
						<c:forEach var="qualification" items="${FmQualookUpList}">
							<option value="<c:out value="${qualification.lookupName}"/>">
							<c:out value="${qualification.lookupDesc}" /></option>
						</c:forEach>
				</hdiits:select>
			</td>
			
			<td class="fieldLabel" width="25%">
				<b><hdiits:caption captionid="eis.subqualification" bundle="${familyLables}" /></b>
			</td>
			<td class="fieldLabel" width="25%">
				<hdiits:select id="SubQualification" name="SubQualification" sort="false" captionid="eis.subqualification" bundle="${familyLables}" onchange="getDisciplineLst(this);">
					<option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</option>
				</hdiits:select>
			</td>
		</tr>
	</table>
	
	<table class="tabtable" id="quliTable2" border="0" bordercolor="">
		<tr>
			<td width=50%>
			<table class="tabtable" border="0" bordercolor="" >
			<tr  id="diciplineId" style="display:none">
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.discipline" bundle="${familyLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%" align="left">
				<hdiits:select captionid="eis.discipline" bundle="${familyLables}" name="discipline"  sort="false" id="discipline">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
				</hdiits:select>
			</td>
			</tr>
			</table>
			</td>
			
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.fm_dependant_dtls" bundle="${familyLables}" /></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				name="depedentCmb" id="depedentCmb" captionid="eis.fm_dependant_dtls" bundle="${familyLables}"
				mandatory="true" validation="sel.isrequired" sort="false">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
				<c:forEach var="depedent" items="${DepedentLookupLst}">
					<option value="<c:out value="${depedent.lookupName}"/>"><c:out
						value="${depedent.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
	</table>
	
	<table class="tabtable" border="0" id="employmentTable">
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.Employment_Status" bundle="${familyLables}" name="Employment" id="Employment" mandatory="true" validation="sel.isrequired" onchange="employmentStatus(this);getEmploymentComboList(this);">
				<hdiits:option value="Select">------<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>------</hdiits:option>
				<c:forEach var="employment" items="${EmpStalookUpList}">
					<option value="<c:out value="${employment.lookupName}"/>">
					<c:out value="${employment.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.Nationality" bundle="${familyLables}"/></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.Nationality" bundle="${familyLables}" name="Nationality" id="Nationality" mandatory="true"  sort="false" validation="sel.isrequired" style="width:80%">
				<hdiits:option value="Select">-------<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>-------</hdiits:option>
				<c:forEach var="nationalityList" items="${cmnCountryMstList}">
					<option value="<c:out value="${nationalityList.countryCode}"/>">
					<c:out value="${nationalityList.countryName}" /></option>
				</c:forEach>
			</hdiits:select></td>
	  </tr>
	
	 <tr>
			<td class="fieldLabel" colspan="4">
				<table name='employedData' class="tabtable" id="employedData" style="display:none" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.Employment_Type" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%">
						<hdiits:select captionid="eis.Employment_Type" bundle="${familyLables}" id="Occupation" name="Occupation" mandatory="true" validation="sel.isrequired" onchange="displayOtherOccu()" sort="false">
							<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%" style="display:None" id="DeptTd"><b><hdiits:caption captionid="EIS.Dept" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%" style="display:None" id="DeptTdList">
						<hdiits:select captionid="EIS.Dept" bundle="${familyLables}" name="Dept" id="Dept" mandatory="true" validation="sel.isrequired" onchange="showForOtherDept();">
							<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${familyLables}"></fmt:message>--</hdiits:option>
								<c:forEach var="deptList" items="${DeptList}">
									<option value="<c:out value="${deptList.depCode}"/>">
									<c:out value="${deptList.depName}" /></option>
								</c:forEach> 
						</hdiits:select></td>
				</tr>
				
				<tr>
					<td class="fieldLabel" width="25%" style="display:None" id="companyDtls"><b><hdiits:caption captionid="eis.name_of_comp" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%" style="display:None" id="companyDtlsTd"><hdiits:text	name="Name_of_Company" id="Name_of_Company"	captionid="eis.name_of_comp" bundle="${familyLables}"  maxlength="25"></hdiits:text></td>
					<td class="fieldLabel" width="25%" style="display:None" id="occuDtls"><b><hdiits:caption captionid="eis.OrganizationDtls" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%" style="display:None" id="occuDtlsTd"><hdiits:textarea cols="30" name="other_occu" id="other_occu" captionid="eis.OrganizationDtls" maxlength="1000" bundle="${familyLables}" /></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.Designation" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="designation" id="designation" bundle="${familyLables}" captionid="eis.Designation" maxlength="25"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.annual_Income" bundle="${familyLables}" /></b></td>
					<td class="fieldLabel" width="25%"><hdiits:text	name="annual_income" id="annual_income" captionid="eis.annual_Income" bundle="${familyLables}" maxlength="10" onkeypress="return checkNumberForOnlyOneDot(this.value)" style="text-align: right" ></hdiits:text></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<table class="tabtable" id="remarkTable">
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Remarks" bundle="${familyLables}" /></b></td>
			<td class="fieldLabel" clospan="3"><hdiits:textarea cols="100"
				name="Remarks" id="Remarks" captionid="eis.Remarks" maxlength="1000"
				bundle="${familyLables}" /></td>
		</tr>
	</table>
	<table class="tabtable" width="100%" id="addressTable">
		<tr>
			<td class="fieldLabel">
			<hdiits:fmtMessage key="eis.FamilyMemAdd" bundle="${familyLables}" var="familyAddressTitle" ></hdiits:fmtMessage>
			<jsp:include page="../../common/address.jsp">
				<jsp:param name="addrName" value="familyPersonAddress" />
				<jsp:param name="addressTitle" value="${familyAddressTitle}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Yes" />
				<jsp:param name="collapseAddress" value="true"/>
			</jsp:include></td>
		</tr>
	</table>
	<table class="tabtable" align="center">
		<tr>
		<hdiits:fmtMessage key="eis.attachment_Details" bundle="${familyLables}" var="familyAttachmentTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel"><jsp:include
				page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="attachmentBiometricFamily" />
				<jsp:param name="formName" value="frmFamily" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="${familyAttachmentTitle}" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
	
	<table align="center">
		<c:set var="addToolTip"
			value="To Add a New Record in Current Request Table"></c:set>
		<c:set var="saveToolTip"
			value="To Update a Record in Current Request Table"></c:set>
		<tr>
			<td>
			<hdiits:button type="button" name="Add" captionid="EIS.Add" bundle="${familyLables}" title="${addToolTip}" onclick="startupAjaxFormValidation('Add')" />
			&nbsp;&nbsp;<hdiits:button type="button" name="Save" readonly="true" title="${saveToolTip}" captionid="EIS.Update" bundle="${familyLables}" onclick="startupAjaxFormValidation('Save')" />
    	    &nbsp;&nbsp;<hdiits:button type="button" name="Reset" id="Reset" captionid="EIS.Reset" bundle="${familyLables}" onclick="resetData()"></hdiits:button>
    	   	</td>
			
		</tr>
  	</table>
  	
	<BR>
	
	<!--  This Table For the Current/ Request Data Only --> <!-- IFMS --> 
	<hdiits:fieldGroup id="currentRequestFieldGroupId" titleCaptionId="eis.cur_req" bundle="${familyLables}"  collapseOnLoad="false" >
	<c:if test="${workFlowEnabled eq 'true'}">
		<!--<table class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${familyLables}" key="eis.cur_req"/></u></strong> </font></td>
			</tr>
		</table><BR>-->
	</c:if> 

	<table class="TableBorderLTRBN" id="FamilyDataTable" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE"
		name="FamilyDataTable" align="center" width="100%">
		<tr>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="15%"><b><hdiits:caption captionid="eis.NAME" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.GENDER" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Relation" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}" /></b>(DD/MM/YYYY)</td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.occupation" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Remarks" bundle="${familyLables}" /></b></td>
			<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.actions" bundle="${familyLables}"></hdiits:caption></b></td>
		</tr>
	</table>
	<table id="familyCurrentNoRecords" align="center" style="display:none">
		<tr>
			<td align="center"><b><hdiits:caption captionid="eis.no_app_req" bundle="${familyLables}" /></b></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	<BR>
	<table align="center">
		<tr>
			<td align="center">
			<hdiits:button type="button" name="Submit" captionid="EIS.Submit" bundle="${familyLables}" readonly="false" onclick="SubmitAction()"></hdiits:button>
			
			<c:if test="${workFlowEnabled eq 'false'}">
			<!-- IFMS -->
				<hdiits:button type="button" name="btnClose" captionid="EIS.Cancel" bundle="${familyLables}" onclick="window.close();"></hdiits:button>
			</c:if> 
			<c:if test="${workFlowEnabled eq 'true'}"> 
            	&nbsp;&nbsp;<hdiits:button type="button" name="Save_Draft" id="Save_Draft" readonly="false" captionid="EIS.SaveAsDraft" bundle="${familyLables}" onclick="SubmitSaveAsDraft()"></hdiits:button>
            	&nbsp;&nbsp;<hdiits:button type="button" name="Close" captionid="EIS.Cancel" bundle="${familyLables}"	onclick="startupAjaxFormValidation('Close')" />
			</c:if>

			</td>
		</tr>
	</table>
	
	<BR>
	<!--  End Of Current Data Table --> <!-- IFMS --> 
	
	<c:if test="${workFlowEnabled eq 'false'}">
		<c:forEach items="${hrEisEmpFamilyVOList}" var="EmployeeFamilyTuples" varStatus="x">
			<c:set var="curXMLFileName"	value="${xmlFilePathNameForMulAddRec[x.index]}"></c:set>
			<c:set var="checkNomineeFlag"	value="${checkDependRecOnNominee[x.index]}"></c:set>
			<c:set var="cmnGender" value="${EmployeeFamilyTuples.cmnLookupMstByFmGender.lookupDesc}" />
			<c:set var="cmnDeadOrAlive"	value="${EmployeeFamilyTuples.cmnLookupMstByFmDeadOrAlive.lookupDesc}" />
			<c:set var="cmnRelation" value="${EmployeeFamilyTuples.cmnLookupMstByFmRelation.lookupDesc}" />
			<c:set var="cmnMarital"	value="${EmployeeFamilyTuples.cmnLookupMstByFmMaritalStatus.lookupDesc}" />
			<c:set var="cmnEmployment" value="${EmployeeFamilyTuples.cmnLookupMstByFmEmploymentStatus.lookupDesc}" />
			<c:set var="cmnFmOccu" value="${EmployeeFamilyTuples.cmnLookupMstByFmOccupation.lookupDesc}" />
			<c:set var="cmnQuali" value="${EmployeeFamilyTuples.cmnLookupMstByFmQualification.lookupDesc}" />
			<c:set var="fmFirstName" value="${EmployeeFamilyTuples.fmFirstName}" />
			<c:set var="fmMiddleName" value="${EmployeeFamilyTuples.fmMiddleName}" />
			<c:set var="fmLastName" value="${EmployeeFamilyTuples.fmLastName}" />
			<c:set var="fmDOB" value="${EmployeeFamilyTuples.fmDateOfBirth}" />
			<fmt:formatDate value="${fmDOB}" pattern="dd/MM/yyyy" var="dob" />
			<c:set var="remarks" value="${EmployeeFamilyTuples.fmRemarks}" />
			<c:set var="srNo" value="${EmployeeFamilyTuples.memberId}" />
			<c:set var="attachmentId" value="${EmployeeFamilyTuples.cmnAttachmentMst.attachmentId}"></c:set>
			<script type="text/javascript">
				var gender = '${cmnGender}';
				var relation = '${cmnRelation}';
				var employment = '${cmnEmployment}';
				var occu = '${cmnFmOccu}';
				var quali = '${cmnQuali}';
				var marital = '${cmnMarital}';
				var Mname='${fmMiddleName}';
				var FName='${fmFirstName}';
				var LName='${fmLastName}';
				var ifmsFName=FName+" "+Mname+" "+LName;
				var fmRemarks='${remarks}';
				
				if(Mname==''){Mname='-';} 
				if(gender.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){gender='-';}
				if(relation.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){relation='-';}
				if(employment.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1 || employment==''){employment='-';}
				if(occu.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){occu='-';}
				if(quali.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1 || quali==''){quali='-';}
				if(marital.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){marital='-';}
				if(occu=='<fmt:message  bundle="${familyLables}" key="eis.select"/>' || occu==''){occu='-';}
				if(fmRemarks==''){fmRemarks='-';}
				
				var flag='${checkNomineeFlag}';
				var xmlFileName = '${curXMLFileName}';
				var displayFieldA  = new Array(ifmsFName,gender,relation,'${cmnDeadOrAlive}','${dob}',employment,occu,marital,quali,fmRemarks);					
				if(flag=='deleteNo')
				{
					addDBDataInTableAttachment('FamilyDataTable','addedPunch',displayFieldA,xmlFileName,'${attachmentId}','editDBRecord', 'deleteFamilyDBRecord');
				}
				else
				{
					addDBDataInTableAttachment('FamilyDataTable','addedPunch',displayFieldA,xmlFileName,'${attachmentId}','editDBRecord', '');
					var linkId = 'rowaddedPunch'+parseInt(counter-1);
					trow = document.getElementById(linkId);														
					trow.childNodes[11].innerHTML=trow.childNodes[11].innerHTML+' <font color="blue"> ('+FamilyDtlsAlertMsgArr[47]+')</font>';
				}
			</script>
		</c:forEach>
	</c:if> 
	<!--  This Table For the Approved Data Only -->
	 <c:if test="${workFlowEnabled eq 'true'}">
	 <hdiits:fieldGroup id="approvedRequestFieldGroupId" titleCaptionId="eis.app_req" bundle="${familyLables}">
		<!--<table class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${familyLables}" key="eis.app_req"/></u></strong> </font></td>
			</tr>
		</table><BR>--> 
		
		<table class="TableBorderLTRBN" id="FamilyApproveDataTable" name="FamilyApproveDataTable" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;" BGCOLOR="WHITE" align="center" width="100%">
			<tr>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" style="display:none">&nbsp;</td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="15%"><b><hdiits:caption	captionid="eis.NAME" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.GENDER" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.Relation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption	captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}" /></b>(DD/MM/YYYY)</td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.occupation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.Remarks" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption	captionid="eis.actions" bundle="${familyLables}"></hdiits:caption></b></td>
			</tr>
		</table>

		<c:if test="${not empty FamilyArrObj}">
			<c:set var="yes" value="Yes"></c:set>
			<c:set var="no" value="No"></c:set>
			<c:set var="delete" value="delete"></c:set>
			<c:set var="draft" value="Draft"></c:set>
			<c:set var="pending" value="Pending"></c:set>
			<c:forEach items="${FamilyArrObj}" var="familyTuples" varStatus="x">
				<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>

				<c:set var="cmnGender"	value="${familyTuples.cmnLookupMstByFmGender.lookupDesc}" />
				<c:set var="cmnDeadOrAlive"	value="${familyTuples.cmnLookupMstByFmDeadOrAlive.lookupDesc}" />
				<c:set var="cmnRelation" value="${familyTuples.cmnLookupMstByFmRelation.lookupDesc}" />
				<c:set var="cmnMarital"	value="${familyTuples.cmnLookupMstByFmMaritalStatus.lookupDesc}" />
				<c:set var="cmnEmployment" value="${familyTuples.cmnLookupMstByFmEmploymentStatus.lookupDesc}" />
				<c:set var="cmnFmOccu" value="${familyTuples.cmnLookupMstByFmOccupation.lookupDesc}" />
				<c:set var="cmnQuali" value="${familyTuples.cmnLookupMstByFmQualification.lookupDesc}" />

				<c:set var="fmFirstName" value="${familyTuples.fmFirstName}" />
				<c:set var="fmMiddleName" value="${familyTuples.fmMiddleName}" />
				<c:set var="fmLastName" value="${familyTuples.fmLastName}" />
				<c:set var="fmDOB" value="${familyTuples.fmDateOfBirth}" />
				<fmt:formatDate value="${fmDOB}" pattern="dd/MM/yyyy" var="dob" />
				<c:set var="remarks" value="${familyTuples.fmRemarks}" />
				<c:set var="srNo" value="${familyTuples.memberId}" />
				<c:set var="attachmentId" value="${familyTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<c:set var="xml" value="${showLinks[x.index]}">
				</c:set>
				<c:if test="${draft == xml or pending == xml}">
					<script>setMeForLink=0;</script>
				</c:if>
				<c:if test="${delete == xml}">
					<script>setMeForLink=1;</script>
				</c:if>
				<c:if test="${yes == xml}">
					<script>setMeForLink=0;</script>
				</c:if>
				
				<script type="text/javascript">					
					var xmlFileName = '${curXMLFileName}';			
															
					var gender = '${cmnGender}';
					var relation = '${cmnRelation}';
					var employment = '${cmnEmployment}';
					var occu = '${cmnFmOccu}';
					var quali = '${cmnQuali}';
					var marital = '${cmnMarital}';
					
					var fName ='${fmFirstName}';
					var mName='${fmMiddleName}';
					var lName='${fmLastName}';
					var fullFmName=fName+" "+mName+" "+lName;
					
					if(gender.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){gender='-';}
					if(relation.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){relation='-';}
					if(employment.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){employment='-';}
					if(occu.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){occu='-';}
					if(occu=='<fmt:message  bundle="${familyLables}" key="eis.select"/>'){occu='-';}
					if(quali.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){quali='-';}
					if(marital.search(/<fmt:message  bundle="${familyLables}" key="eis.select"/>/i)!=-1){marital='-';}
					var displayFieldA  = new Array(fullFmName,gender,relation,'${cmnDeadOrAlive}','${dob}',employment,occu,marital,quali,'${remarks}');					//change by Sunil(15/05/08) for FullName
					displayFieldA=eprofileCheckForNull(displayFieldA);
					if(setMeForLink==0)
					{									
						addDBDataInTableAttachment('FamilyApproveDataTable','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecord', 'deleteFamilyDBRecord','');
						showLinksId='rowencXML'+parseInt(counter-1);
					}
					else if(setMeForLink==1)
					{								
						addDBDataInTableAttachment('FamilyApproveDataTable','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecord', '','');
						showLinksId='rowencXML'+parseInt(counter-1);
					}		
					dbRowsId[dbRowsIdCount]=showLinksId;
					dbRowsIdCount=dbRowsIdCount+1;
					dbRowsSrNo[dbRowsSrNoCount] = '${srNo}/'+showLinksId;
					dbRowsSrNoCount=dbRowsSrNoCount+1;
				</script>
				<c:if test="${draft == xml}">
					<script>								
						trow = document.getElementById(showLinksId);														
						trow.childNodes[11].innerHTML=FamilyDtlsAlertMsgArr[45];
					</script>
				</c:if>
				<c:if test="${pending == xml}">
					<script>								
						trow = document.getElementById(showLinksId);														
						trow.childNodes[11].innerHTML=FamilyDtlsAlertMsgArr[46];
					</script>
				</c:if>
				<c:if test="${delete == xml}">
					<script>								
						trow = document.getElementById(showLinksId);														
						trow.childNodes[11].innerHTML=trow.childNodes[11].innerHTML+' ('+FamilyDtlsAlertMsgArr[47]+')';
					</script>
				</c:if>
			</c:forEach>
		</c:if>

		<c:if test="${empty FamilyArrObj}">
			<center><b><hdiits:caption captionid="eis.no_app_req" bundle="${familyLables}" /></b></center>
			<script>showme(1);</script>
		</c:if>
		<c:forEach var="dependantNomnDtls" items="${dependantNomnDtls}">
			<script>AddDependentNomineeArr('${dependantNomnDtls}');</script>
		</c:forEach>
		</hdiits:fieldGroup>
		
		<!--  End Of Approve Data Table -->
		
		<!--  This Table is Use for The Pending Request Table -->
		<BR>
		<hdiits:fieldGroup id="pendingRequestFieldGroupId" titleCaptionId="eis.pending_req" bundle="${familyLables}"  collapseOnLoad="true" >
		
		<!--<table class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${familyLables}" key="eis.pending_req"/></u></strong> </font></td>
			</tr>
		</table> <BR>-->
		
		<table id="RequestTable" name="RequestTable" align="center" border="1"
			cellpadding="1" cellspacing="1" BGCOLOR="WHITE" bordercolor="black" style="border-collapse: collapse;
			class="TableBorderLTRBN" width="50%">
			<tr>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${familyLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.date_submit" bundle="${familyLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.person_with" bundle="${familyLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>
			</tr>
			<c:if test="${not empty request}">
				<c:forEach var="i2" items="${request}">
					<tr>
						<td>
							<script> 
								var v1=increment();
								document.write(v1);
							</script>
						</td>
						<td><c:set var="creatDate" value="${i2[1]}"></c:set> <fmt:formatDate
							type="date" pattern="dd/MM/yyyy" value="${creatDate}"></fmt:formatDate>
						</td>
						<td><c:out value="${i2[2]}"></c:out></td>
						<td><a href=javascript:void('Request') onclick=javascript:viewPendingRequestDtls('${i2[0]}')><fmt:message  bundle="${familyLables}" key="eis.View"/></a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${empty request}">
			<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${familyLables}"/></b></center>
				<script>
					showme(2);
				</script>
		</c:if>
		<BR>
		<table id="FamilyPendingDataTable" name="FamilyPendingDataTable"
			border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse; display:none" BGCOLOR="WHITE"
			class="TableBorderLTRBN" align="center" width="100%">
			<tr>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"  width="15%"><b><hdiits:caption
					captionid="eis.NAME" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.GENDER" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Relation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption
					captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}"/></b>(DD/MM/YYYY)</td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.occupation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Remarks" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.type_req" bundle="${familyLables}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
		<BR>
		<!-- End of Pending Data code -->
		
		<!-- Draft Data Table -->
		<hdiits:fieldGroup id="draftRequestFieldGroupId" titleCaptionId="eis.draft_req" bundle="${familyLables}"  collapseOnLoad="true" >
		<!--<table class="tabtable" name="header_draft" id="header_draft">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${familyLables}" key="eis.draft_req"/></u></strong> </font></td>
			</tr>
		</table> <BR>-->
		
		<c:if test="${empty draftArrObj}">
			<center><b><hdiits:caption captionid="eis.no_draft_req" bundle="${familyLables}" /></b></center>
		</c:if>
		<c:if test="${not empty draftArrObj}">
			<table id="FamilyDraftDtlsTab" name="FamilyDraftDtlsTab" align="center" border="1" cellpadding="1" cellspacing="1" borderColor="black" COLOR="WHITE" class="TableBorderLTRBN" style="border-collapse: collapse;" width="50%" BGCOLOR="WHITE" >
				<tr>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.srno" bundle="${familyLables}"></hdiits:caption></b></td>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.date" bundle="${familyLables}"></hdiits:caption></b></td>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>
				</tr>
				<c:forEach var="i1" items="${draftArrObj}">
					<tr id='${i1[0]}'>
						<td><script> 
					var v1=increment_draft();
					document.write(v1);
				</script></td>
						<td><c:set var="creatDate" value="${i1[1]}"></c:set> 
						<fmt:formatDate	type="date" pattern="dd/MM/yyyy" value="${creatDate}"></fmt:formatDate>
						</td>
						<td><a href=javascript:void('Request') onclick=javascript:viewDraftRequestDtls('${i1[0]}')><fmt:message  bundle="${familyLables}" key="eis.View"/></a>
						/ <a href=javascript:void('Request') onclick=javascript:openDraftRequestDtls('${i1[0]}')><fmt:message  bundle="${familyLables}" key="eis.Open"/></a>
						/ <a href=javascript:void('Request') onclick=javascript:deleteDraftRequestDtls('${i1[0]}')><fmt:message  bundle="${familyLables}" key="eis.delete"/></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<BR>
		<table id="FamilyDraftDataTable" name="FamilyDraftDataTable"
			border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse; display:none" BGCOLOR="WHITE"
			class="TableBorderLTRBN" align="center" width="100%">
			<tr>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="15%"><b><hdiits:caption
					captionid="eis.NAME" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.GENDER" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Relation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}" width="10%"><b><hdiits:caption
					captionid="eis.DATE_OF_BIRTH" bundle="${familyLables}" /></b>(DD/MM/YYYY)</td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Employment_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.occupation" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Marital_Status" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Qualification" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Remarks" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.actions" bundle="${familyLables}" /></b></td>
				<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Links" bundle="${familyLables}"></hdiits:caption></b></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
	</c:if> 
	
	<!-- End Draft Data Table -->
	
	</div></div></div>
	<hdiits:hidden name="statusValue" id="statusValue" />
	<hdiits:hidden name="dependentValue" id="dependentValue" />
	<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>
	<hdiits:hidden name="GenderValue" id="GenderValue"></hdiits:hidden>
	<hdiits:hidden name="sendAttachmentId" id="sendAttachmentId"></hdiits:hidden>
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>

	<hdiits:text style="display:none" name="lastNameCmb1" id="lastNameCmb1"	default="-"></hdiits:text>
	<hdiits:text style="display:none" name="firstNameCmb1" id="firstNameCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="middleNameCmb1" id="middleNameCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="RelationCmb1" id="RelationCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="GenderCmb1" id="GenderCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="DOBCmb1" id="DOBCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="date_DemiseCmb1" id="date_DemiseCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="personAgeCmb1" id="personAgeCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="maritalStatusCmb1" id="maritalStatusCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="QualificationCmb1" id="QualificationCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="depedentCmbCmb1" id="depedentCmbCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="EmploymentCmb1" id="EmploymentCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="Name_of_CompanyCmb1" id="Name_of_CompanyCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="designationCmb1" id="designationCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="occupationCmb1" id="occupationCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="annual_incomeCmb1" id="annual_incomeCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="RemarksCmb1" id="RemarksCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="statusCmb1" id="statusCmb1" default="-"></hdiits:text>
	<hdiits:text style="display:none" name="empFmFullName" id="empFmFullName"></hdiits:text>
	<c:if test="${workFlowEnabled eq 'true'}">
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
			controlNames="lastName,firstName,middleName,Relation,status,DOB,Employment,Occupation,MaritalStatus,Qualification,Gender" />
	</c:if>


 <c:if test="${workFlowEnabled eq 'false'}">
	
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="lastName,firstName,Relation,otherRelation,Gender,DOB,status,MaritalStatus,depedentCmb,Occupation,Employment,Dept" /> 
			
	</c:if>
 
</hdiits:form>

<script type="text/javascript">
	onLoadFamilyDtls();
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
</script>

</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
