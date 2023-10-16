
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.empEducationLabels" var="educationLables" scope="request" />
<html>
<head>

<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EducationDtls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<script type="text/javascript">
	var navDisplay = true,xmlHttp;	
	var xmlHttp,setdiscipline=-1; 	
	var setCombo=-1,showLinksId;
	var globalFlagDeleteDisable = false;
	var dbRecordUpdateOrDelete = false;
	var count=0,dbRowsIdCount=0,dbRowsSrNoCount=0;	    
	var dbRecord=0,draftFlag=0,mappingArrCount=0;
	var dbRowId,editRowId,reqIdCounter=0,deleteRowId,delArrCount=0,draftArrCount=-1,parentId=0;	
	var dbRowsSrNo = new Array();
	var dbRowsId = new Array();
	var deleteArr = new Array();
	var draftArr = new Array();
	var reqIdArr = new Array();	
	var mappingArr = new Array();	
	var displayFieldArrayInTable = new Array('QualificationCmb1','SubQualificationCmb1','disciplineCmb1','courseCategoryCmb1','UniBoardCmb1','yrpassingCmb1','unitMarks');
	
	var EducationDtlsAlertMsgArr=new Array();
	EducationDtlsAlertMsgArr[0] ='<fmt:message  bundle="${educationLables}" key="eis.alt_submit" />';
	EducationDtlsAlertMsgArr[1] ='<fmt:message  bundle="${educationLables}" key="eis.alt_draft" />';
	EducationDtlsAlertMsgArr[2] ='<fmt:message  bundle="${educationLables}" key="eis.select"  />';	
	EducationDtlsAlertMsgArr[3] ='<fmt:message  bundle="${educationLables}" key="eis.ajax_error"/>';
	EducationDtlsAlertMsgArr[4] ='<fmt:message  bundle="${educationLables}" key="eis.error"/>';
	EducationDtlsAlertMsgArr[5] ='<fmt:message  bundle="${educationLables}" key="eis.MarkErrorAlert"/>';
	EducationDtlsAlertMsgArr[6] ='<fmt:message  bundle="${educationLables}" key="eis.edit_mode"/>';
	EducationDtlsAlertMsgArr[7] ='<fmt:message  bundle="${educationLables}" key="eis.updation"/>';
	EducationDtlsAlertMsgArr[8] ='<fmt:message  bundle="${educationLables}" key="eis.new_add"/>';
	EducationDtlsAlertMsgArr[9] ='<fmt:message  bundle="${educationLables}" key="eis.alt_delete"/>';
	EducationDtlsAlertMsgArr[10]='<fmt:message  bundle="${educationLables}" key="eis.req_sel"/>';
	EducationDtlsAlertMsgArr[11]='<fmt:message  bundle="${educationLables}" key="eis.valid_UniName"/>';
	EducationDtlsAlertMsgArr[12]='<fmt:message  bundle="${educationLables}" key="eis.valid_mark"/>';
	EducationDtlsAlertMsgArr[13]='<fmt:message  bundle="${educationLables}" key="eis.uni_ins_board"/>';
	EducationDtlsAlertMsgArr[14]='<fmt:message  bundle="${educationLables}" key="eis.uni_ins_board"/>';
	EducationDtlsAlertMsgArr[15]='<fmt:message  bundle="${educationLables}" key="eis.isrequired"/>';
	EducationDtlsAlertMsgArr[16]='<fmt:message  bundle="${educationLables}" key="eis.qualification"/>';
	EducationDtlsAlertMsgArr[17]='<fmt:message  bundle="${educationLables}" key="eis.subqualification"/>';
	EducationDtlsAlertMsgArr[18]='<fmt:message  bundle="${educationLables}" key="eis.courseCategory"/>';
	EducationDtlsAlertMsgArr[19]='<fmt:message  bundle="${educationLables}" key="eis.unit"/>';
	EducationDtlsAlertMsgArr[20]='<fmt:message  bundle="${educationLables}" key="eis.year_of_pass"/>';
	EducationDtlsAlertMsgArr[21]='<fmt:message  bundle="${educationLables}" key="eis.marks"/>';
	EducationDtlsAlertMsgArr[22]='<fmt:message  bundle="${educationLables}" key="eis.Hide"/>';
	EducationDtlsAlertMsgArr[23]='<fmt:message  bundle="${educationLables}" key="eis.eis.Deletion"/>';
	EducationDtlsAlertMsgArr[24]='<fmt:message  bundle="${educationLables}" key="eis.updation"/>';
	EducationDtlsAlertMsgArr[25]='<fmt:message  bundle="${educationLables}" key="eis.new_add"/>';
	EducationDtlsAlertMsgArr[26]='<fmt:message bundle="${educationLables}" key="eis.year_of_pass"/>'+' '+'<fmt:message bundle="${educationLables}" key="eis.dobAlert"/>';
	var strSelect='<fmt:message  bundle="${educationLables}" key="eis.select"/>';
</script>
</head>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListlObj" value="${resValue.Qualifications}"></c:set>
<c:set var="lListUnit" value="${resValue.Unit}"></c:set>
<c:set var="lArrObj" value="${resValue.QualificationDtls}"></c:set>
<c:set var="xmlFile" value="${resValue.XmlFile}"></c:set>
<c:set var="request" value="${resValue.RequestDtls}"></c:set>
<c:set var="showLinks" value="${resValue.requesetLstObj}"></c:set>
<c:set var="draftArrObj" value="${resValue.draftRequest}"></c:set>
<c:set var="lListcourseCategoryObj" value="${resValue.courseCategory}"></c:set>
<c:set var="arYear"	value="${resValue.arYear}" />
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="workFlowEnabled" value="${resValue.blnWorkFlowEnabled}"></c:set>
<c:set var="hrEisEmpQualificationVOList" value="${resValue.hrEisEmpQualificationVOList}"></c:set>
<c:set var="xmlFilePathNameForMulAddRec" value="${resValue.xmlFilePathNameForMulAddRec}" />

<script>
	var workFlowEnabled = ${workFlowEnabled}; //IFMS
</script>

<body>
<hdiits:form name="frmEdu" validate="true" method="post"
	encType="multipart/form-data" action="hdiits.htm">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">

		<c:if test="${workFlowEnabled eq 'true'}">
			<!-- IFMS -->
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.quali_dtls" bundle="${educationLables}"></fmt:message></b></a></li>
		</c:if>

		<c:if test="${workFlowEnabled eq 'false'}">
			<!-- IFMS -->
			
			<li class="selected"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="EIS.PersonalDtls" bundle="${educationLables}"/> </b> </a></li>
			
			
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.quali_dtls" bundle="${educationLables}"></fmt:message></b></a></li>

			<li class="selected" style="width: 152px;"><a href="#" style="width: 142px;"
				onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.extraCoCurricularDtls"
				bundle="${educationLables}"></fmt:message> </b> </a></li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.FamilyDtls"
				bundle="${educationLables}" /> </b> </a></li>
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.nominee_dtls"
				bundle="${educationLables}" /> </b> </a></li>
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"> <b>
			<fmt:message key="EIS.EmpMislDtl"
				bundle="${educationLables}" /> </b> </a></li>


		</c:if>
	</ul>
	</div>

	<div id="education" name="education">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0"><%@ include file="empInfo/EmployeeInfo.jsp"%>
	
<!--Starts Added by Sunil -->
	<fmt:formatDate var="dob" value="${EmpDetVO.dob}" pattern="dd/MM/yyyy" />
	<script type="text/javascript"> var empDOB="${dob}";</script>
<!-- Ends Added by Sunil -->
	
	<hdiits:fieldGroup id="educationFieldGroupId" titleCaptionId="eis.quali_dtls" bundle="${educationLables}">
	
	<table class="tabtable">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${educationLables}" key="eis.quali_dtls"/></u></strong> </font></td>
		</tr>-->
		<tr></tr>
		<tr style="display:none" id="errorMessage">
			<td class="fieldLabel" width="100%" rowspan="2" colspan="4"><textarea
				name="errorBox" id="errorBox" readonly="readonly"
				style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #eeeeee;"></textarea>
			</td>
		</tr>
		<tr></tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.qualification" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.qualification" bundle="${educationLables}" name="Qualification" id="Qualification" validation="sel.isrequired" onchange="getSubqualificationsLst(this);" mandatory="true" sort="false">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${educationLables}"></fmt:message>--</hdiits:option>
						<c:forEach var="unit" items="${lListlObj}">
							<option value=<c:out value="${unit.lookupName}"/>><c:out value="${unit.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption	captionid="eis.subqualification" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select id="SubQualification" name="SubQualification" sort="false" captionid="eis.subqualification" bundle="${educationLables}" onchange="getDisciplineLst(this);" mandatory="true" validation="sel.isrequired">
					<option value="Select">--<fmt:message key="eis.select" bundle="${educationLables}"></fmt:message>--</option>
				</hdiits:select>
			</td>
		</tr>
		
		<tr id="diciplineId" style="display:none">
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.discipline" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.discipline" bundle="${educationLables}" name="discipline" mandatory="" validation="sel.isrequired" sort="false" id="discipline">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${educationLables}"></fmt:message>--</hdiits:option>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.courseCategory" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select id="courseCategory" name="courseCategory" captionid="eis.courseCategory" bundle="${educationLables}"	validation="sel.isrequired" mandatory="true">
					<hdiits:option value="Select">--<fmt:message	key="eis.select" bundle="${educationLables}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lListcourseCategoryObj}">
						<option value=<c:out value="${unit.lookupName}"/>><c:out value="${unit.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.uni_ins_board" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="UniBoard" id="UniBoard" captionid="eis.uni_ins_board" bundle="${educationLables}" maxlength="40" validation="txt.isrequired" mandatory="true" /></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.year_of_pass" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.year_of_pass" sort="false" bundle="${educationLables}" name="yrpassing" id="yrpassing" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${educationLables}"></fmt:message>--</hdiits:option>
						<c:forEach var="yearValue" items="${arYear}">
							<option value="<c:out value="${yearValue}"/>"><c:out value="${yearValue}"/></option>	
						</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.unit" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:select captionid="eis.unit" bundle="${educationLables}" id="unit" name="unit" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${educationLables}"></fmt:message>--</hdiits:option>
					<c:forEach var="unit" items="${lListUnit}">
						<option value=<c:out value="${unit.lookupName}"/>><c:out value="${unit.lookupDesc}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.marks" bundle="${educationLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="Marks" id="Marks" captionid="eis.marks_req" bundle="${educationLables}" style="text-align: right" maxlength="5" validation="txt.isrequired" mandatory="true" onkeypress="return checkNumberForOnlyOneDot(this.value)"/></td>
		</tr>
	</table>

	<table class="tabtable" align="center">
		<tr>
			<hdiits:fmtMessage key="eis.attachment_Details" bundle="${educationLables}" var="eduAttachmentTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="attachmentBiometricEducation" />
				<jsp:param name="formName" value="frmEdu" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="${eduAttachmentTitle}" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
	
	<BR>
	
	<table align="center">
		<c:set var="addToolTip"	value="To Add a New Record in Current Request Table"></c:set>
		<c:set var="saveToolTip" value="To Update a Record in Current Request Table"></c:set>
		<c:set var="resetToolTip" value="Reset The Form"></c:set>
		<tr>
			<td>
				<hdiits:button type="button" name="Add" id="Add" title="${addToolTip}" captionid="EIS.Add" bundle="${educationLables}" onclick="startupAjaxFormValidation('Add')"></hdiits:button>
				&nbsp;&nbsp;<hdiits:button type="button" name="Save" captionid="EIS.Update" bundle="${educationLables}"	readonly="true" title="${saveToolTip}" onclick="startupAjaxFormValidation('Save')"></hdiits:button> 
				&nbsp;&nbsp;<hdiits:button type="button" name="Reset" title="${resetToolTip}" captionid="EIS.Reset" bundle="${educationLables}" onclick="resetData(0)"></hdiits:button>
			</td>
		</tr>
	</table>
	
	<br>
	<!--  This Table For the Current Data Only --> <!--  IFMS --> 
	<hdiits:fieldGroup  id="currentRequestFieldGroupId" titleCaptionId="eis.cur_req" bundle="${educationLables}" collapseOnLoad="false">
	
	<c:if test="${workFlowEnabled eq 'true'}">
		<!-- <table id="DtlsTab1" name="DtlsTab1" class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${educationLables}" key="eis.cur_req"/></u></strong> </font></td>
			</tr>
		</table>
		<br>-->
	</c:if>

	<table class="TableBorderLTRBN" id="QuaDtlsTab" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE"
		name="QuaDtlsTab" align="center" width="100%">
		<tr>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.qualification" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.subqualification" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.discipline" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.courseCategory" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.uni_ins_board" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.year_of_pass" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.unitMarks" bundle="${educationLables}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.actions" bundle="${educationLables}"></hdiits:caption></b></td>
		</tr>
	</table>
	
	<!--  IFMS --> <c:if test="${workFlowEnabled eq 'false'}">
		<c:forEach items="${hrEisEmpQualificationVOList}" var="EmployeeQualificationTuples" varStatus="x">
			<c:set var="curXMLFileName"	value="${xmlFilePathNameForMulAddRec[x.index]}"></c:set>
			<c:set var="qualification" value="${EmployeeQualificationTuples.cmnLookupMstByQualificationId.lookupDesc}" />
			<c:set var="subqualification" value="${EmployeeQualificationTuples.cmnLookupMstBySubQualificationId.lookupDesc}" />
			<c:set var="discipline"	value="${EmployeeQualificationTuples.cmnLookupMstByDicipline.lookupDesc}" />
			<c:set var="courseCate"	value="${EmployeeQualificationTuples.cmnLookupMstByCourseCategory.lookupDesc}" />
			<c:set var="unitOfMarks" value="${EmployeeQualificationTuples.cmnLookupMstByUnitsOfMarks.lookupDesc}" />
			<c:set var="yearOfPassing" value="${EmployeeQualificationTuples.yearOfPassing}" />
			<c:set var="unitInstBoard" value="${EmployeeQualificationTuples.uniInstituteBoard}" />
			<c:set var="markScored"	value="${EmployeeQualificationTuples.marksScored}" />
			<c:set var="srNo" value="${EmployeeQualificationTuples.srNo}" />
			<c:set var="attachmentId" value="${EmployeeQualificationTuples.cmnAttachmentMst.attachmentId}"></c:set>

			<script type="text/javascript">
				var xmlFileName = '${curXMLFileName}';
				var unitMarks='${markScored}'+' '+'${unitOfMarks}';
				var discipline ='${discipline}';
				var courseCate ='${courseCate}';
				if(discipline.search(/<fmt:message  bundle="${educationLables}" key="eis.select"/>/i)!=-1 || discipline==''){discipline='-';}
				if(courseCate.search(/<fmt:message  bundle="${educationLables}" key="eis.select"/>/i)!=-1){courseCate='-';}
				
				var displayFieldA  = new Array('${qualification}','${subqualification}',discipline,courseCate,'${unitInstBoard}','${yearOfPassing}',unitMarks);
				addDBDataInTableAttachment('QuaDtlsTab','addedPunch',displayFieldA,xmlFileName,'${attachmentId}','editDBRecord', 'deleteDBRecord');
				</script>
		</c:forEach>
	</c:if>
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	<br>
	<table name="submitTab" id="submitTab" align="center">
		<tr>
			<td align="center"><hdiits:button type="button" name="Submit" captionid="EIS.Submit" bundle="${educationLables}" onclick="SubmitAction()"></hdiits:button> 
				<c:if test="${workFlowEnabled eq 'false'}">
					<hdiits:button type="button" name="Close" captionid="EIS.Cancel" bundle="${educationLables}" onclick="window.close();"></hdiits:button>
				</c:if> 
				<c:if test="${workFlowEnabled eq 'true'}">
            		&nbsp;&nbsp;<hdiits:button type="button" name="btnSave_Draft" id="btnSave_Draft" readonly="true" captionid="EIS.SaveAsDraft" bundle="${educationLables}" value="Save Draft" onclick="SubmitSaveAsDraft()"></hdiits:button>
		            &nbsp;&nbsp;<hdiits:button type="button" name="btnClose" captionid="EIS.Cancel" bundle="${educationLables}" onclick="startupAjaxFormValidation('Close')" ></hdiits:button>
			</c:if></td>
		</tr>
	</table>
	
	<!--  End Of Current Data Table --> 
	
	<!--  This Table For the Approved Data Only -->
	<br>
	
	<c:if test="${workFlowEnabled eq 'true'}">
		<!-- IFMS -->
		<hdiits:fieldGroup  id="approvedRequestFieldGroupId" titleCaptionId="eis.app_req" bundle="${educationLables}" collapseOnLoad="true">
		<!--<table id="ApproveDtlsTab1" name="ApproveDtlsTab1" class="tabtable">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${educationLables}" key="eis.app_req"/></u></strong> </font></td>
			</tr>
		</table>-->
		
		<table class="TableBorderLTRBN" id="QuaApproveDtlsTab" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" BGCOLOR="WHITE"
			name="QuaApproveDtlsTab" align="center" width="100%">
			<tr>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.qualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.subqualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.discipline" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.courseCategory" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.uni_ins_board" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.year_of_pass" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.unitMarks" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.actions" bundle="${educationLables}"></hdiits:caption></b></td>
			</tr>
		</table>
		<c:if test="${not empty lArrObj}">
			<c:set var="yes" value="Yes"></c:set>
			<c:set var="no" value="No"></c:set>
			<c:forEach items="${lArrObj}" var="qualificationTuples" varStatus="x">
				<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>
				<c:set var="qualification" value="${qualificationTuples.cmnLookupMstByQualificationId.lookupDesc}" />
				<c:set var="subqualification" value="${qualificationTuples.cmnLookupMstBySubQualificationId.lookupDesc}" />
				<c:set var="discipline"	value="${qualificationTuples.cmnLookupMstByDicipline.lookupDesc}" />
				<c:set var="courseCate"	value="${qualificationTuples.cmnLookupMstByCourseCategory.lookupDesc}" />
				<c:set var="unitOfMarks" value="${qualificationTuples.cmnLookupMstByUnitsOfMarks.lookupDesc}" />
				<c:set var="yearOfPassing" value="${qualificationTuples.yearOfPassing}" />
				<c:set var="unitInstBoard" value="${qualificationTuples.uniInstituteBoard}" />
				<c:set var="markScored" value="${qualificationTuples.marksScored}" />
				<c:set var="srNo" value="${qualificationTuples.srNo}" />
				<c:set var="attachmentId" value="${qualificationTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<script type="text/javascript">					
					var xmlFileName = '${curXMLFileName}';
					var discipline ='${discipline}';
					var courseCate ='${courseCate}';
					
					var unitMarks='${markScored}'+' '+'${unitOfMarks}';
					
					if(discipline.search(/<fmt:message  bundle="${educationLables}" key="eis.select"/>/i)!=-1){discipline='-';}
					if(courseCate.search(/<fmt:message  bundle="${educationLables}" key="eis.select"/>/i)!=-1){courseCate='-';}
					var displayFieldA  = new Array('${qualification}','${subqualification}',discipline,courseCate,'${unitInstBoard}','${yearOfPassing}',unitMarks);
					displayFieldA = eprofileCheckForNull(displayFieldA);																		
					addDBDataInTableAttachment('QuaApproveDtlsTab','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecord', 'qualificationDeleteDBRecord','');
					showLinksId='rowencXML'+parseInt(counter-1);
					dbRowsId[dbRowsIdCount]=showLinksId;
					dbRowsIdCount=dbRowsIdCount+1;
					dbRowsSrNo[dbRowsSrNoCount] = '${srNo}/'+showLinksId;
					dbRowsSrNoCount=dbRowsSrNoCount+1;
				</script>

				<c:set var="xml" value="${showLinks[x.index]}">
				</c:set>
				<c:if test="${no == xml}">
					<script>								
							trow= document.getElementById(showLinksId);														
							trow.childNodes[8].style.display='none';	
					</script>
				</c:if>
			</c:forEach>
		</c:if>

		<!--  End Of Approved Data Table -->

		<c:if test="${empty lArrObj}">
			<script>showme(1);</script>
			<center><b><hdiits:caption captionid="eis.no_app_req" bundle="${educationLables}" /></b></center>
		</c:if>
	</hdiits:fieldGroup>
		
		<!--  This Table is Use for The Pending Request Table -->
		<br>
		<hdiits:fieldGroup  id="pendingRequestFieldGroupId" titleCaptionId="eis.pending_req" bundle="${educationLables}" collapseOnLoad="true">
		<!--<table class="tabtable" name="header_draft" id="header_draft">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${educationLables}" key="eis.pending_req"/></u></strong> </font></td>
			</tr>
		</table>
		<br>-->
		<table border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" BGCOLOR="WHITE"
			class="TableBorderLTRBN" id="RequestTable" name="RequestTable" align="center" width="50%">
			<tr>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.date_submit" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.person_with" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Links" bundle="${educationLables}"></hdiits:caption></b></td>
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
						<td>
							<c:set var="creatDate" value="${i2[1]}"></c:set> 
							<fmt:formatDate	type="date" pattern="dd/MM/yyyy" value="${creatDate}"></fmt:formatDate>
						</td>
						<td><c:out value="${i2[2]}"></c:out></td>
						<td><a href=javascript:void('Request') onclick=javascript:viewPendingRequestDtls('${i2[0]}')><fmt:message key="eis.view" bundle="${educationLables}"/></a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<br>
		<c:if test="${empty request}">
			<center><b><hdiits:caption captionid="eis.no_pen_req" bundle="${educationLables}" /></b></center>
			<script>
				showme(2);
			</script>
		</c:if>
		
		<table class="TableBorderLTRBN" id="QuaPendingDtlsTab" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE"
			name="QuaPendingDtlsTab" align="center" width="100%">
			<tr>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.qualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.subqualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.discipline" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.courseCategory" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.uni_ins_board" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.year_of_pass" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.unitMarks" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.type_req" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.Links" bundle="${educationLables}"></hdiits:caption></b></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
		<br>		
		<!--  End Code For the Pending Request Data -->
		
		<hdiits:fieldGroup  id="draftRequestFieldGroupId" titleCaptionId="eis.draft_req" bundle="${educationLables}" collapseOnLoad="true">
		<!-- <table class="tabtable" name="header_draft" id="header_draft">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
				<strong><u><fmt:message bundle="${educationLables}" key="eis.draft_req"/></u></strong> </font></td>
			</tr>
		</table>
		<BR>-->
		<c:if test="${empty draftArrObj}">
			<center><b><hdiits:caption captionid="eis.no_draft_req" bundle="${educationLables}"/></b></center>
		</c:if>
		
		<c:if test="${not empty draftArrObj}">
			<table border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;" BGCOLOR="WHITE"
				class="TableBorderLTRBN" id="QuaDraftDtlsTab" name="QuaDraftDtlsTab"
				align="center" border="1" width="50%">

				<tr>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.srno" bundle="${educationLables}"></hdiits:caption></b></td>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption
						captionid="eis.date" bundle="${educationLables}"></hdiits:caption></b></td>
					<td align="center" class="fieldLabel" bgcolor="${tdBGColor}"><b>
					<hdiits:caption	captionid="eis.Links" bundle="${educationLables}"></hdiits:caption></b></td>
				</tr>
				<c:forEach var="i1" items="${draftArrObj}">
					<tr id='${i1[0]}'>
						<td><script> 					
					var v1=increment_draft();
					document.write(v1);
				</script></td>
						<td><c:set var="creatDate" value="${i1[1]}"></c:set> <fmt:formatDate
							type="date" pattern="dd/MM/yyyy" value="${creatDate}"></fmt:formatDate>
						</td>
						<td>
						<a href=javascript:void('Request') onclick=javascript:viewDraftRequestDtls('${i1[0]}')><fmt:message key="eis.view" bundle="${educationLables}"/></a>
						/ <a href=javascript:void('Request') onclick=javascript:openDraftRequestDtls('${i1[0]}')><fmt:message key="eis.open" bundle="${educationLables}"/></a>
						/ <a href=javascript:void('Request') onclick=javascript:deleteDraftRequestDtls('${i1[0]}')><fmt:message key="eis.delete" bundle="${educationLables}"/></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<BR>
		<table  class="TableBorderLTRBN" id="QuaDraftTable" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE"
			name="QuaDraftTable" width="100%">
			<tr>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.srno" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.qualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.subqualification" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.discipline" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.courseCategory" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.uni_ins_board" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.year_of_pass" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.unitMarks" bundle="${educationLables}"></hdiits:caption></b></td>
				<td align="center"  bgcolor="${tdBGColor}"><b><hdiits:caption
					captionid="eis.status" bundle="${educationLables}"></hdiits:caption></b></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
	</c:if> 
	<!-- End of Code fot Draft Request --> 
	<hdiits:hidden name="requestFlag" id="requestFlag"></hdiits:hidden> 
	<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden> 
	<hdiits:hidden name="sendAttachmentId" id="sendAttachmentId"></hdiits:hidden> 
	<hdiits:text style="display:none" name="unitCmb1" id="unitCmb1"></hdiits:text> 
	<hdiits:text style="display:none" name="yrpassingCmb1" id="yrpassingCmb1"></hdiits:text>
	<hdiits:text style="display:none" name="courseCategoryCmb1" id="courseCategoryCmb1"></hdiits:text> 
	<hdiits:text style="display:none" name="disciplineCmb1" id="disciplineCmb1"></hdiits:text>
	<hdiits:text style="display:none" name="SubQualificationCmb1" id="SubQualificationCmb1"></hdiits:text>
	<hdiits:text style="display:none" name="QualificationCmb1" id="QualificationCmb1"></hdiits:text>
	<hdiits:text style="display:none" name="MarksCmb1" id="MarksCmb1"></hdiits:text>
	<hdiits:text style="display:none" name="UniBoardCmb1" id="UniBoardCmb1"></hdiits:text>

	<hdiits:hidden name="hdnUserId" id="hdnUserId" /><!-- IFMS --></div>
	</div></div>
	<hdiits:text style="display:none" name="unitMarks" id="unitMarks"></hdiits:text>
	<!--  IFMS -->
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>

	<c:if test="${workFlowEnabled eq 'false'}">
		<!-- IFMS -->
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
			controlNames="Qualification,SubQualification,discipline,courseCategory,UniBoard,unit,Marks" />
	</c:if>

	<c:if test="${workFlowEnabled eq 'true'}">
		<!-- IFMS -->
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
			controlNames="UniBoardCmb1,unitCmb1,courseCategoryCmb1,yrpassingCmb1,SubQualificationCmb1,disciplineCmb1,MarksCmb1,QualificationCmb1,Marks,UniBoard,Subquaqualification,unit,Qualification,yrpassing" />
	</c:if>
</hdiits:form>
<script type="text/javascript">	
		initializetabcontent("maintab")
</script>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
