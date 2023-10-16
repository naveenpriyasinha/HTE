
<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"  src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script> 
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/hr/compassion/compassion.js"></script>


<fmt:setBundle basename="resources.hr.compassion.Labels" var="CapLabels"
	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empDetailVo" value="${resultValue.empDetailVo}"></c:set>
<c:set var="Grades" value="${resultValue.Grades}"></c:set>
<c:set var="allPost" value="${resultValue.allPost}"></c:set>
<c:set var="compNomineeVoList" value="${resultValue.compNomineeVoList}"></c:set>

<hdiits:form name="compEmpDetail" validate="true" method="POST"  action="hdiits.htm?actionFlag=getCompassionPersonDetail"  encType="multipart/form-data"> 
	
<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="comp.compassion" bundle="${CapLabels}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
		<hdiits:fieldGroup titleCaptionId="comp.empDetails" bundle="${CapLabels}" id="empDtls" collapseOnLoad="false">
	<table id="empDetails" align="center" width="100%">
	<tr id="row1">
		
		<td width="25%"><b><hdiits:caption captionid="comp.Name" bundle="${CapLabels}"></hdiits:caption></b><input type="hidden" id="userId" name="userId" value="${empDetailVo.empUserId}"></td>
		<td width="25%"><c:out value="${empDetailVo.empName }"></c:out> </td>
		<td width="25%"><b><hdiits:caption captionid="comp.DoB" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><fmt:formatDate value="${empDetailVo.dob}" pattern="dd/MM/yyyy" /> <hdiits:hidden name="dob" id="dob"/> </td>
	</tr>
	<hdiits:hidden name="dod" id="dod"/>
	<tr id="row2">
		<td width="25%"><b><hdiits:caption captionid="comp.Designation" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.desgnName}"></c:out></td>
		<td width="25%"><hdiits:caption captionid="comp.ageOfDec" bundle="${CapLabels}"></hdiits:caption></td>
		<td width="25%">
			<script>	
				document.getElementById('dob').value='<fmt:formatDate value="${empDetailVo.dob}" pattern="dd/MM/yyyy" />';
				document.getElementById('dod').value='<fmt:formatDate value="${empDetailVo.dod}" pattern="dd/MM/yyyy" />';
				
				var answer=  getDateDiffInString(document.getElementById('dob').value,document.getElementById('dod').value, true);
				document.write(answer);
			</script>
		</td>
	</tr>
	
	<tr id="row3">
		<td width="25%"><b><hdiits:caption captionid="comp.DoJ" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><fmt:formatDate value="${empDetailVo.doj}" pattern="dd/MM/yyyy" />
		<hdiits:hidden name="doj" id="doj" />
		 </td>
		<td width="25%"><b><hdiits:caption captionid="comp.DoD" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><fmt:formatDate value="${empDetailVo.dod}" pattern="dd/MM/yyyy" /> 
		 
		</td>
	</tr>
	
	<tr id="row4">
		<td width="25%"><b><hdiits:caption captionid="comp.durationService" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%" id="durService">
		<script >
		document.getElementById('doj').value='<fmt:formatDate value="${empDetailVo.doj}" pattern="dd/MM/yyyy" />';
		document.getElementById('dod').value='<fmt:formatDate value="${empDetailVo.dod}" pattern="dd/MM/yyyy" />';
		var ans=  getDateDiffInString(document.getElementById('doj').value,document.getElementById('dod').value);
		document.write(ans);
		</script>
		</td>
		<td width="25%"><b><hdiits:caption captionid="comp.permanentOrTemp"  bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"> <c:out value="${empDetailVo.empTemporaryOrPerment}"></c:out></td>
	</tr>
	
	<tr>
	<td style="height: 15px"></td>
	</tr>
	<tr>
	 
		<td width="25%"><b><hdiits:caption captionid="comp.pension" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"> <fmt:formatNumber value="${empDetailVo.pensionRs}" maxFractionDigits="2"></fmt:formatNumber> </td>
		<td width="25%"><b><hdiits:caption captionid="comp.Gratuity" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.graturityRs}"></c:out></td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.empGinsure" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.empGroupInsurance}"></c:out></td>
		<td width="25%"><b><hdiits:caption captionid="comp.leaveEncash" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.leaveEnhancement}"></c:out></td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.others" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"></td>
		<td width="25%"><b><hdiits:caption captionid="comp.Total" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.empGroupInsurance+empDetailVo.graturityRs+empDetailVo.pensionRs+empDetailVo.leaveEnhancement}"></c:out></td>
	</tr>	
	
	</table>
</hdiits:fieldGroup>		
	<hdiits:fieldGroup titleCaptionId="comp.nomineeDtl" bundle="${CapLabels}" id="nomDtls" collapseOnLoad="false">
	<table id="nomineeDeyails" width="100%" align="center" border="1">
	<tr class="datatableheader">
	<td>
	<b><hdiits:caption captionid="comp.SrNo" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	<td><b><hdiits:caption captionid="comp.Name" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	<td><b><hdiits:caption captionid="comp.DoB" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	<td><b><hdiits:caption captionid="comp.age" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	<td><b><hdiits:caption captionid="comp.gender" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	<td><b><hdiits:caption captionid="comp.relWithApplicant" bundle="${CapLabels}"></hdiits:caption></b> 
	</td>
	<td><b><hdiits:caption captionid="comp.maritalStatus" bundle="${CapLabels}"></hdiits:caption></b> 
	</td>
	<td><b><hdiits:caption captionid="comp.eduQualification" bundle="${CapLabels}"></hdiits:caption></b>
	</td>
	
	</tr>
	<c:set var="srno" value="1"></c:set>
	<c:forEach var="nomList" items="${compNomineeVoList}">
	<tr>
	<td>
	
	<c:out value="${srno}"></c:out>
	<c:set var="srno" value="${srno+1}"></c:set>
	</td>	
	<td> <c:out value=" ${nomList.nomineeName}" />
	</td>
	<td>  <fmt:formatDate value="${nomList.nomineeDoB}" pattern="dd/MM/yyyy" />  
	</td>
	<td> <c:out value="${nomList.nomineeAge}" />
	</td>
	<td> <c:out value="${nomList.nomineeGender}" />
	</td>
	<td> <c:out value="${nomList.nomineeRelation}" />
	</td>
	<td> <c:out value="${nomList.nomineeMaritalStatus}" />
	</td>
	<td> <c:out value="${nomList.nomineeEduQualification}" />
	</td>
	</tr>
	
	</c:forEach> 
	</table>
</hdiits:fieldGroup>	
	<hdiits:fieldGroup titleCaptionId="comp.appliDetails" bundle="${CapLabels}" id="appDtls" collapseOnLoad="true">	
	<table id="appDetails" width="100%" align="center">
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.Name" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:select sort="false" name="nomineeName" id="nomineeName" size="1" caption="drop_down" mandatory="true"  onchange="return dropDownNomineeName();" >
               
				<hdiits:option value="0"><fmt:message key="comp.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
				<c:forEach var="nomList" items="${compNomineeVoList}">
					<hdiits:option value="${nomList.memberId}">${nomList.nomineeName}
				</hdiits:option>
				</c:forEach></hdiits:select> </td>
		<td width="25%"><hdiits:caption captionid="comp.dateTurn" bundle="${CapLabels}"></hdiits:caption></td>
		<td width="25%"><hdiits:text name="dtTurn18" id = "dtTurn18" readonly="true"/></td>
	</tr>
	</table>
	
	<table id="nomineeAddress" width="100%" align="center" style="display: none;" readonly="true">
	<tr>
	<td class="fieldLabel" width="100%" colspan="4" >
	
	<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="PersonAddress" />
						<jsp:param name="addressTitle" value="" />
						<jsp:param name="addrLookupName" value="Present Address" />
						<jsp:param name="mandatory" value="Y" />						
					</jsp:include>
	</td>
	</tr>
					
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.otherDependentGotCompassionGround" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="otherDependentGotCompassionGround" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="otherDependentGotCompassionGround" default="No" captionid="comp.no" bundle="${CapLabels}"/>
		 </td>
		<td width="25%"><b><hdiits:caption captionid="comp.anyPrevOrPresentEmpDtl" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="anyPrevOrPresentEmpDtl" onclick="anyPrevOrPresentEmpDtlScript(this)" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="anyPrevOrPresentEmpDtl" default="No" onclick="anyPrevOrPresentEmpDtlScript(this)" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
	</tr>		
	
	<tr id="nomOrgnInfo" style="display: none;">
		<td width="25%"><b><hdiits:caption captionid="comp.orgName" bundle="${CapLabels}"></hdiits:caption></b></td> 
		<td width="25%"><hdiits:text name="orgName" id="orgName" captionid="comp.orgName" bundle="${CapLabels}"/> </td>
		<td width="25%"><b><hdiits:caption captionid="comp.typeOfOrganization" bundle="${CapLabels}"></hdiits:caption></b></td> 
		<td width="25%"><hdiits:text name="typeOfOrganization" id="typeOfOrganization" captionid="comp.typeOfOrganization" bundle="${CapLabels}" /> </td>
	</tr>
	</table>
	
	<table id="OrganizationAddress1" width="100%" align="center" style="display: none;">
	<tr>
	<td class="fieldLabel" width="100%" colspan="4">
	
	<jsp:include page="/WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="OrganizationAddress" />
						<jsp:param name="addressTitle" value="Address" />
						<jsp:param name="addrLookupName" value="Organization" />
						<jsp:param name="mandatory" value="Y" />						
					</jsp:include>
	</td>
	</tr>			
	
	</table>				
	
	<table id="appDetails1" width="100%" align="center">
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.nameOfClassToBeRecruited" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:select sort="false" name="gradeNames" id="gradeNames" size="1" caption="drop_down" mandatory="true" >
               
				<hdiits:option value="0"><fmt:message key="comp.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
				<c:forEach var="grade" items="${Grades}">
					<hdiits:option value="${grade.gradeId}">${grade.gradeName}
				</hdiits:option>
				</c:forEach></hdiits:select></td>
		<td width="25%"><b><hdiits:caption captionid="comp.nameOfPostToBeRecruited" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:select sort="false" name="postNames" id="postNames" size="1" caption="drop_down" mandatory="true" >
               
				<hdiits:option value="0"><fmt:message key="comp.select" bundle="${CapLabels}"></fmt:message></hdiits:option>
				<c:forEach var="allPost" items="${allPost}">
					<hdiits:option value="${allPost.orgPostMst.postId}">${allPost.postName}
				</hdiits:option>
				</c:forEach></hdiits:select>
		</td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.postFilledUnderGujGaunSevaBoard" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="postFilledUnderGujGaunSevaBoard" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="postFilledUnderGujGaunSevaBoard" default="No" captionid="comp.no" bundle="${CapLabels}"/>
		 </td>
		<td width="25%"><b><hdiits:caption captionid="comp.appliFullfillQualification" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="appliFullfillQualification" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="appliFullfillQualification" default="No" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.provisionDirectRecruitment" bundle="${CapLabels}" id="provisionDirectRecruitment"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" id="provisionDirectRecruitment" name="provisionDirectRecruitment" onclick="provisionDirectRecruitmentScript(this)" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" id="provisionDirectRecruitment" name="provisionDirectRecruitment" default="No" onclick="provisionDirectRecruitmentScript(this)" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
		<td width="25%" id="provisionTdId" style="display: none;"><b><hdiits:caption captionid="comp.provision" bundle="${CapLabels}" ></hdiits:caption></b></td>
		<td width="25%"><hdiits:textarea captionid="comp.provision" bundle="${CapLabels}" name="provision"  style="display: none;" id="provision"/></td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.anyRelaxtionOrSpecialToGive" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="anyRelaxtionOrSpecialToGive" default="Yes" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="anyRelaxtionOrSpecialToGive" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
		<td width="25%"><b><hdiits:caption captionid="comp.appliMeetAllEligibleCriteria" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="appliMeetAllEligibleCriteria" default="Yes" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="appliMeetAllEligibleCriteria" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.detailsScrutinizedByDeptSectionOffice" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="detailsScrutinizedByDeptSectionOffice" default="Yes" onclick="detailsScrutinizedByDeptSectionOfficeScript(this)" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="detailsScrutinizedByDeptSectionOffice" onclick="detailsScrutinizedByDeptSectionOfficeScript(this)" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
		<td width="25%" id="lstDocumentTdId" style="display: none;"><b><hdiits:caption captionid="comp.listOfDoc" bundle="${CapLabels}" id="lstDocument" accessKey="lstDocument"></hdiits:caption></b></td>
		<td width="25%"><hdiits:textarea captionid="comp.listOfDoc" bundle="${CapLabels}" id="lstDocument" name="lstDocument" style="display: none;" /></td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.candidateRecommForJob" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:radio value="Yes" name="candidateRecommForJob" default="Yes" onclick="candidateRecommForJobScript(this)" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No"  name="candidateRecommForJob" onclick="candidateRecommForJobScript(this)" captionid="comp.no" bundle="${CapLabels}"/>
		</td>
		<td width="25%" id="reasonTdId" style="display: none;"><b><hdiits:caption captionid="comp.reason" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><hdiits:textarea captionid="comp.reason" bundle="${CapLabels}" name="reason" style="display: none;" /></td>
	</tr>
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.wifeOfDeceasedEmpRemarried" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%">
		<hdiits:radio value="Yes" name="wifeOfDeceasedEmpRemarried" default="Yes" captionid="comp.yes" bundle="${CapLabels}"/>
		 <hdiits:radio value="No" name="wifeOfDeceasedEmpRemarried" captionid="comp.no" bundle="${CapLabels}"/>
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
	</table>
	
	<table id="applicantAttachment" width="100%" align="center">
	<tr>
	<td><b> <hdiits:caption captionid="comp.attach" bundle="${CapLabels}"></hdiits:caption></b>
	<br>
	 <hdiits:caption captionid="comp.filesAttachments" bundle="${CapLabels}"></hdiits:caption>
	</td>
	</tr>
</table>
</hdiits:fieldGroup>	
<table width="100%">
	<tr>	
	<td>	
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
                <jsp:param name="attachmentName" value="compEmpDetailAttachment" />
                <jsp:param name="formName" value="compEmpDetail" />
                <jsp:param name="attachmentType" value="Document" />
    			<jsp:param name="multiple" value="N" />    
       			<jsp:param name="mandatory" value="Y" />             
	</jsp:include>
	</td>
	</tr>
	
	<tr height="15 px">
	</tr>
	
	<tr>
	<td align="center"><hdiits:button name="frmSubmit" type="button"  captionid="comp.submit" bundle="${CapLabels}"  onclick="insertDataInDB()"/>
	</td>
	</tr>
	</table>

	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
<script type="text/javascript">
	
function insertDataInDB(){
	
		if(document.getElementById('nomineeName').selectedIndex==0){
				alert('<fmt:message bundle="${CapLabels}" key="compalert.nomineeName"/>');
				document.forms[0].nomineeName.focus();
				return false;
		}
		
		
		
		if(document.compEmpDetail.anyPrevOrPresentEmpDtl[0].checked){
		
				if(document.getElementById('orgName').value==''){
					alert('<fmt:message bundle="${CapLabels}" key="compalert.orgName"/>');
					document.forms[0].orgName.focus();
					return false;
				}
		
				if(document.getElementById('typeOfOrganization').value==''){
					alert('<fmt:message bundle="${CapLabels}" key="compalert.typeOfOrganization"/>');
					document.forms[0].typeOfOrganization.focus();
					return false;
				}
				
						
		}
		
		if(document.getElementById('gradeNames').selectedIndex==0){
				alert('<fmt:message bundle="${CapLabels}" key="compalert.gradeNames"/>');
				document.forms[0].gradeNames.focus();
				return false;
		}
		
		if(document.getElementById('postNames').selectedIndex==0){
				alert('<fmt:message bundle="${CapLabels}" key="compalert.postNames"/>');
				document.forms[0].postNames.focus();
				return false;
		}
		
		
		if(document.compEmpDetail.provisionDirectRecruitment[0].checked){
		
				if(document.getElementById('provision').value==''){
					alert('<fmt:message bundle="${CapLabels}" key="compalert.provision"/>');
					document.forms[0].provision.focus();
					return false;
				}
					
		}
		
		if(document.compEmpDetail.detailsScrutinizedByDeptSectionOffice[1].checked){
		
				if(document.getElementById('lstDocument').value==''){
					alert('<fmt:message bundle="${CapLabels}" key="compalert.lstDocument"/>');
					document.forms[0].lstDocument.focus();
					return false;
				}
					
		}
		
		if(document.compEmpDetail.candidateRecommForJob[1].checked){
		
				if(document.getElementById('reason').value==''){
					alert('<fmt:message bundle="${CapLabels}" key="compalert.reason"/>');
					document.forms[0].reason.focus();
					return false;
				}
					
		}
		
		
		document.compEmpDetail.action="hrms.htm?actionFlag=sendNomineeData";
		document.compEmpDetail.submit();
	
	
	}
	makeReadOnly("PersonAddress");


	
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />	
	
</hdiits:form>


</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

