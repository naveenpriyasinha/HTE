
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.empCoCurricularLables" var="coCurricularLabels" scope="request" />
<html>
<head>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/CocurricularDtls.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<title>Human Resource Management System</title>

<script>
	var setCombo=-1,submiteFlag=false;       // To set the Value in the Sub Type combo Box while cliking on Edit button
	var navDisplay = true;				
	var xmlHttp,dbRecordForEdit=false; 
	var count=0,updateSrNo=1;
	var dbRows=0;
	var editRowId;		
	var globalFlagDeleteDisable = false;
	var CoCurricularAlertMsgArr=new Array();
	CoCurricularAlertMsgArr[0]='<fmt:message  bundle="${coCurricularLabels}" key="eis.ajax_error"/>';
	CoCurricularAlertMsgArr[1]='<fmt:message  bundle="${coCurricularLabels}" key="eis.select"/>';
	CoCurricularAlertMsgArr[2]='<fmt:message  bundle="${coCurricularLabels}" key="eis.submite_alt"/>';	
	CoCurricularAlertMsgArr[3]='<fmt:message  bundle="${coCurricularLabels}" key="eis.alt_submit"/>';
	CoCurricularAlertMsgArr[4]='<fmt:message  bundle="${coCurricularLabels}" key="eis.edit_mode"/>';
	CoCurricularAlertMsgArr[5]='<fmt:message  bundle="${coCurricularLabels}" key="eis.YearOfParticipation"/>'+' '+'<fmt:message bundle="${coCurricularLabels}" key="eis.dobAlert"/>';
	</script>
</head>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListCurrObj" value="${resValue.Curricular}"></c:set>
<c:set var="lListCompeObj" value="${resValue.Competed_at}"></c:set>
<c:set var="lListSubCurrObj" value="${resValue.SubCurricular}"></c:set>
<c:set var="lArrObj" value="${resValue.CoCurricularDtls}"></c:set>
<c:set var="xmlFile" value="${resValue.XmlFile}"></c:set>
<c:set var="arYear" value="${resValue.arYear}"></c:set>
<c:set var="workFlowEnabled" value="${resValue.blnWorkFlowEnabled}"></c:set>
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>

<script>
	var workFlowEnabled = ${workFlowEnabled}; //IFMS
</script>



<body>
<hdiits:form name="frmCoCurricular" action="hdiits.htm" validate="true"
	method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">

		<c:if test="${workFlowEnabled eq 'true'}">
			<!-- IFMS -->
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.extraCoCurricularDtls" bundle="${coCurricularLabels}"></fmt:message></b></a></li>
		</c:if>

		<c:if test="${workFlowEnabled eq 'false'}">
			<!-- IFMS -->

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="EIS.PersonalDtls"
				bundle="${coCurricularLabels}" /> </b> </a></li>


			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.quali_dtls"
				bundle="${coCurricularLabels}"></fmt:message> </b> </a></li>

			<li class="selected" style="width: 152px;"><a href="#" rel="tcontent1" style="width: 142px;"><b><fmt:message
				key="eis.extraCoCurricularDtls" bundle="${coCurricularLabels}"></fmt:message></b></a>
			</li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.FamilyDtls"
				bundle="${coCurricularLabels}" /> </b> </a></li>
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.nominee_dtls"
				bundle="${coCurricularLabels}" /> </b> </a></li>
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"> <b>
			<fmt:message key="EIS.EmpMislDtl"
				bundle="${coCurricularLabels}" /> </b> </a></li>


		</c:if>
	</ul>
	</div>
	<div id="CoCurricularDtls" name="CoCurricularDtls">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<%@ include	file="empInfo/EmployeeInfo.jsp"%>

<!--Starts Added by Sunil -->
	<fmt:formatDate var="dob" value="${EmpDetVO.dob}" pattern="dd/MM/yyyy" />
	<script type="text/javascript"> var empDOB="${dob}";</script>
<!-- Ends Added by Sunil -->
	
	<hdiits:fieldGroup id="CoCurricularFieldGroupId" titleCaptionId="eis.extraCoCurricularDtls" bundle="${coCurricularLabels}">
	<table class="tabtable">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${coCurricularLabels}"
				key="eis.extraCoCurricularDtls" /></u></strong></font></td>
		</tr>-->
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Co-Curricular" bundle="${coCurricularLabels}"></hdiits:caption></b>
			</td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Co-Curricular" bundle="${coCurricularLabels}"
				id="CoCurricular" name="CoCurricular" mandatory="true"
				validation="sel.isrequired" onchange="com(this);">
				<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${coCurricularLabels}"></fmt:message>--</hdiits:option>
				<c:forEach var="curricular" items="${lListCurrObj}">
					<option value="<c:out value="${curricular.lookupName}"/>">
					<c:out value="${curricular.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.SubTypes" bundle="${coCurricularLabels}"></hdiits:caption></b>
			</td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.SubTypes" bundle="${coCurricularLabels}"
				id="TypeOfCoCurricular" name="TypeOfCoCurricular" mandatory="true"
				validation="sel.isrequired">
				<option value="Select">--<fmt:message
					key="eis.select" bundle="${coCurricularLabels}"></fmt:message>--</option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.YearOfParticipation" bundle="${coCurricularLabels}"></hdiits:caption></b>
			</td>
			<td class="fieldLabel" width="25%"><hdiits:select sort="false"
				captionid="eis.YearOfParticipation" bundle="${coCurricularLabels}"
				name="year_combo" mandatory="true" validation="sel.isrequired">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${coCurricularLabels}"></fmt:message>--</hdiits:option>
					<c:forEach var="yearValue" items="${arYear}">
						<option value="<c:out value="${yearValue}"/>"> 
		    			 <c:out value="${yearValue}"/></option>	
					</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.Competedat" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%"><hdiits:select
				captionid="eis.Competedat" bundle="${coCurricularLabels}"
				name="Competed_at"  sort="false">
				<hdiits:option value="Select">--<fmt:message
						key="eis.select" bundle="${coCurricularLabels}"></fmt:message>--</hdiits:option>
				<c:forEach var="competed" items="${lListCompeObj}">
					<option value=<c:out value="${competed.lookupName}"/>><c:out
						value="${competed.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption
				captionid="eis.SpecialAchievement" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%" colspan="3"><hdiits:textarea
				id="Specialachievement" name="Specialachievement"
				validation="txt.isrequired" captionid="eis.SpecialAchievement"
				cols="40" bundle="${coCurricularLabels}" maxlength="490"></hdiits:textarea>
			</td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>
	</table>
	<table class="tabtable" align="center">
		<tr>
		<hdiits:fmtMessage key="eis.attachment_Details" bundle="${coCurricularLabels}" var="cocurricularAttachmentTitle" ></hdiits:fmtMessage>
			<td class="fieldLabel">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometricCocurricular" />
					<jsp:param name="formName" value="frmCoCurricular" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="${cocurricularAttachmentTitle}" />
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
		<c:set var="closeToolTip" value="Go To Home Page"></c:set>
		<tr>
			<!-- updated by sandip start -->
			<td>
			<hdiits:button type="button" name="Add" id="Add" captionid="EIS.Add" bundle="${coCurricularLabels}"	title="${addToolTip}" onclick="startupAjaxFormValidation('Add')" />
			&nbsp;&nbsp;<hdiits:button type="button" name="Save" captionid="EIS.Update" bundle="${coCurricularLabels}" title="${saveToolTip}" readonly="true" onclick="startupAjaxFormValidation('Save')" />
			&nbsp;&nbsp;<hdiits:button type="button" name="Reset" id="Reset" captionid="EIS.Reset" bundle="${coCurricularLabels}" onclick="resetData()"></hdiits:button></td>
			<!-- updated by sandip end -->
		</tr>
	</table>
	
	<BR>
	<hdiits:fieldGroup id="CoCurricularDtlsFieldGroupId" titleCaptionId="eis.extraCoCurricularDtls" bundle="${coCurricularLabels}" collapseOnLoad="false">
	<!--<table class="tabtable">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${coCurricularLabels}"
				key="eis.extraCoCurricularDtls" /></u></strong></font></td>
		</tr>
	</table><BR>-->
	
	<c:set var="lObjCoCurr" value="${HrEisEmpCocurricular}"></c:set>
	<table id="coCurricularTable" name="coCurricularTable" align="center" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" BGCOLOR="WHITE" class="TableBorderLTRBN" width="100%">
		<tr>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.Co-Curricular" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b>
			<hdiits:caption	captionid="eis.SubTypes" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.Competedat" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.YearOfParticipation" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.SpecialAchievement" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption
				captionid="eis.actions" bundle="${coCurricularLabels}"></hdiits:caption></b></td>
		</tr>
	</table>
	
	<table id="coCurrNoRecords" align="center">
		<c:if test="${empty lArrObj}">
				<tr></tr><tr></tr>
				<tr>
					<td align="center"><b><hdiits:caption captionid="eis.no_app_req" bundle="${coCurricularLabels}" /></b></td>
				</tr>
		</c:if>
	</table>
	
	<c:if test="${not empty lArrObj}">
		<c:forEach items="${lArrObj}" var="coCurricularTuples" varStatus="x">
			<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>
			<c:set var="year" value="${coCurricularTuples.yearId}" />
			<c:set var="competeted"	value="${coCurricularTuples.cmnLookupMstByCompetedAtId.lookupDesc}" />
			<c:set var="cocurricular" value="${coCurricularTuples.cmnLookupMstByCocurricularId.lookupDesc}" />
			<c:set var="subcocurricular" value="${coCurricularTuples.cmnLookupMstBySubCocurricularId.lookupDesc}" />
			<c:set var="specialAchievement"	value="${coCurricularTuples.specialAchievement}" />
			<c:set var="attachmentId" value="${coCurricularTuples.cmnAttachmentMst.attachmentId}"></c:set>
			<script type="text/javascript">					
				var xmlFileName = '${curXMLFileName}';					
				var specialAchievement= '${specialAchievement}';	
				var competeted='${competeted}';
				
				//Added by Sunil
				if(competeted=='' || competeted==null || competeted=='<fmt:message  bundle="${coCurricularLabels}" key="eis.select"/>')
				{competeted='-';}
				
				if(specialAchievement!='')
				{			
					for(var i=0;i<specialAchievement.length;i++)
					{
						specialAchievement = specialAchievement.replace("&amp;#x0D;"," ");
						specialAchievement = specialAchievement.replace("&#x0D;"," ");
					}
				}
				else
				{
					specialAchievement='-';
				}
				var displayFieldA  = new Array('${cocurricular}','${subcocurricular}',competeted,'${year}',specialAchievement);//Added by Sunil
				addDBDataInTableAttachment('coCurricularTable','addedencXML',displayFieldA,xmlFileName,'${attachmentId}','editRecordForDB', 'coCurrDeleteDBRecord','');
			</script>
		</c:forEach>
	</c:if>
	</hdiits:fieldGroup> 
	</hdiits:fieldGroup>
	<c:if test="${empty lArrObj}">
		<script>showme();</script>
	</c:if>
	<BR>
	<table name="submitTab" id="submitTab" align="center">
		<tr>
			<td align="center">
				<hdiits:button type="button" name="Submit" captionid="EIS.Submit" bundle="${coCurricularLabels}"	onclick="SubmitAction()"></hdiits:button>
				&nbsp;&nbsp;<hdiits:button	type="button" name="Close" captionid="EIS.Cancel" bundle="${coCurricularLabels}" title="${closeToolTip}" onclick="startupAjaxFormValidation('Close')"></hdiits:button>
			 </td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>

	<!--  IFMS -->
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	<hdiits:text style="display:none" name="hdnCompeteted" id="hdnCompeteted"></hdiits:text><!-- //Added by Sunil -->
	<hdiits:validate locale="${locale}"
		controlNames="CoCurricular,TypeOfCoCurricular,year_combo" />
	<script type="text/javascript">	
		initializetabcontent("maintab")
	</script>
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
