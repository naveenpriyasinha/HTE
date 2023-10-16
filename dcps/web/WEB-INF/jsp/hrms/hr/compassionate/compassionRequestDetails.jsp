
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
<c:set var="isInboxJsp" value="${resultValue.isInboxJsp}"></c:set>

<hdiits:form name="compNomReqDetail" validate="true" method="POST" encType="multipart/form-data"> 
	
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
		<td width="25%"><fmt:formatDate value="${empDetailVo.dob}" pattern="dd/MM/yyyy" />  </td>
	</tr>
	
	<tr id="row2">
		<td width="25%"><b><hdiits:caption captionid="comp.Designation" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><c:out value="${empDetailVo.desgnName}"></c:out></td>
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
	
	<tr id="row3">
		<td width="25%"><b><hdiits:caption captionid="comp.DoJ" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><fmt:formatDate value="${empDetailVo.doj}" pattern="dd/MM/yyyy" />
		<hdiits:hidden name="doj" id="doj" />
		 </td>
		<td width="25%"><b><hdiits:caption captionid="comp.DoD" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%"><fmt:formatDate value="${empDetailVo.dod}" pattern="dd/MM/yyyy" /> 
		 <hdiits:hidden name="dod" id="dod"/>
		</td>
	</tr>
	
	<tr id="row4">
		<td width="25%"><b><hdiits:caption captionid="comp.durationService" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="25%" id="durService"><hdiits:text name="dservice" id = "dservice" style="border:0; width:170px"  /> </td>
		<script >
		document.getElementById('doj').value='<fmt:formatDate value="${empDetailVo.doj}" pattern="dd/MM/yyyy" />';
		document.getElementById('dod').value='<fmt:formatDate value="${empDetailVo.dod}" pattern="dd/MM/yyyy" />';
		var ans=  getDateDiffInString(document.getElementById('doj').value,document.getElementById('dod').value);
		document.getElementById('dservice').value = ans;
		</script>
		
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
	
	
	<tr>
		<td width="25%"><b><hdiits:caption captionid="comp.status" bundle="${CapLabels}"/></b></td>
		<c:forEach var="nomList" items="${compNomineeVoList}">
		<c:if test="${nomList.lookUpName eq 'compassion-Pending'}">
		<td width="25%"><font color="Red"><b><c:out value="${nomList.lookUpDesc}"></c:out></b></font></td>
		</c:if>
		<c:if test="${nomList.lookUpName eq 'compassion-Approved'}">
		<td width="25%"><font color="Green"><b><c:out value="${nomList.lookUpDesc}"></c:out></b></font></td>
		</c:if>
		
		</c:forEach>
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
	</table>
</hdiits:fieldGroup>	
	
<hdiits:fieldGroup titleCaptionId="comp.nomineeDtl" bundle="${CapLabels}" id="nomDtls" collapseOnLoad="false">	
	<table id="nomineeDeyails" width="100%" align="center" border="1">
	<tr class="datatableheader">
	 
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
	 
	<c:forEach var="nomList" items="${compNomineeVoList}">
	<tr>
	 
	<td> 
	<hdiits:hidden name="fileId" id="${nomList.fileId}"/>
	<c:out value=" ${nomList.nomineeName}" />
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
	<table id="nomineeAddress" width="100%" align="center" border="1">

	<c:forEach var="nomList" items="${compNomineeVoList}">				
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.otherDependentGotCompassionGround" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.depHasCgFlag}"></c:out></td>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.anyPrevOrPresentEmpDtl" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.prvPstDtlsFlag}"></c:out></td>
	</tr>		
	
	<tr id="orgnNmType">
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.orgName" bundle="${CapLabels}"></hdiits:caption></b></td> 
		<td width="20%"><c:out value="${nomList.orgnNm}"></c:out></td>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.typeOfOrganization" bundle="${CapLabels}"></hdiits:caption></b></td> 
		<td width="20%"><c:out value="${nomList.orgnType}"></c:out></td>
	</tr>
	
	<script type="text/javascript">
			
			if('${nomList.prvPstDtlsFlag}'=='No'){
			
				document.getElementById('orgnNmType').style.display = 'none';
				
			}
	
	</script>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.nameOfClassToBeRecruited" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.gradeNm}"></c:out></td>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.nameOfPostToBeRecruited" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.postNm}"></c:out></td>
	</tr>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.postFilledUnderGujGaunSevaBoard" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.postUnderGgspbFlag}"></c:out></td>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.appliFullfillQualification" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.nomFulReqqualFlag}"></c:out></td>
	</tr>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.provisionDirectRecruitment" bundle="${CapLabels}" id="provisionDirectRecruitment"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.provRctFlag}"></c:out></td>
		<td width="30%" id="provisionTdId" class="datatableheader"><b><hdiits:caption captionid="comp.provision" bundle="${CapLabels}" ></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.provDirectRct}"></c:out></td>
	</tr>
	
	<script type="text/javascript">
			
			if('${nomList.provRctFlag}'=='No'){
			
				document.getElementById('provisionTdId').style.display = 'none';
				
			}
	
	</script>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.anyRelaxtionOrSpecialToGive" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.otherRelaxGivenFlag}"></c:out></td>
		
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.appliMeetAllEligibleCriteria" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.nomMeetCrtFlag}"></c:out></td>
	</tr>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.detailsScrutinizedByDeptSectionOffice" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.docScrutFlag}"></c:out></td>
		<td width="30%" id="lstDocumentTdId" class="datatableheader" ><b><hdiits:caption captionid="comp.listOfDoc" bundle="${CapLabels}" id="lstDocument" accessKey="lstDocument"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.lstofDocScrut}"></c:out></td>
	</tr>
	
	<script type="text/javascript">
			
			if('${nomList.docScrutFlag}'=='Yes'){
			
				document.getElementById('lstDocumentTdId').style.display = 'none';
				
			}
	
	</script>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.candidateRecommForJob" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.nomRecomFlag}"></c:out></td>
		<td width="30%" id="reasonTdId" class="datatableheader"><b><hdiits:caption captionid="comp.reason" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.nomRecomRsn}"></c:out></td>
	</tr>
	
	<script type="text/javascript">
			
			if('${nomList.nomRecomFlag}'=='Yes'){
			
				document.getElementById('reasonTdId').style.display = 'none';
				
			}
	
	</script>
	
	<tr>
		<td width="30%" class="datatableheader"><b><hdiits:caption captionid="comp.wifeOfDeceasedEmpRemarried" bundle="${CapLabels}"></hdiits:caption></b></td>
		<td width="20%"><c:out value="${nomList.empWifeRemarryFlag}"></c:out><td width="25%"></td>
		<td width="30%"></td>
		<td width="20%"></td>
	</tr>
	</c:forEach> 
	</table>
</hdiits:fieldGroup>
	<br>
	<br>
	<c:if test="${isInboxJsp eq 'false'}">
	<center><hdiits:button name="closeButton" type="button" bundle="${CapLabels}" captionid="comp.close" onclick="closeWindow()"></hdiits:button></center>
	</c:if>
		</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />	


<script type="text/javascript">

function closeWindow(){

document.forms[0].action='./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome';
document.forms[0].submit();

}
</script>
	
</hdiits:form>


</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

