<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/ChangeEmpProfile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request" />

<c:set var="resultObj" value="${result}" />
<c:set var="resultVal" value="${resultObj.resultValue }" />
<c:set var="Obj_gu" value="${resultVal.hrEmpObject}" />
<c:set var="Obj" value="${resultVal.hrEmpObject_en}" />
<c:set var="engLookupDesc" value="${resultVal.engLookupDesc}" />
<c:set var="engLookupDescApr" value="${resultVal.engLookupDescApr}" />
<c:set var="orgEmpMstForEng" value="${resultVal.orgEmpMstForEng}" />
<c:set var="orgEmpMstForGuj" value="${resultVal.orgEmpMstForGuj}" />
<c:set var="empcontactMstForMobile" value="${resultVal.empcontactMstForMobile}" />
<c:set var="empcontactMstForPhone" value="${resultVal.empcontactMstForPhone}" />
<c:set var="empcontactMstForEmail" value="${resultVal.empcontactMstForEmail}" />
<c:set var="empContactDtls" value="${resultVal.empContactDtls}" />

<hdiits:form name="frmEditDispForm" validate="true" method="POST"  encType="multipart/form-data">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
		<a href="#" rel="tcontent1">
		<b><fmt:message	key="eis.UPDATE_EMP_DATA" bundle="${empEditListCommonLables}"></fmt:message></b>
		</a>
		</li>
	</ul>
	</div>


	<div class="tabcontentstyle" id="tabDivId" style="display:none">
     	<div id="tcontent1" class="tabcontent" tabno="0">


<hdiits:fieldGroup id="empPersonalDtls" titleCaptionId="eis.EMP_PERSONAL_DETAILS" bundle="${empEditListCommonLables}">
	<table width="100%">	
<!-- Personal Details Approved-->

	<tr>
	<td>
	<hdiits:fieldGroup id="arempPersonalDtls" titleCaptionId="eis.APR_PERSONAL_DETAILS" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table width="100%" id="aprEmpPersonalDtlsTbl" style="display: block">
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EN_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td>${orgEmpMstForEng.empPrefix}  ${orgEmpMstForEng.empFname} ${orgEmpMstForEng.empLname} ${orgEmpMstForEng.empMname}</td>
			<td></td>
			
		</tr>
	
<!--  START For Gujrati -->
	
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.GU_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td>${orgEmpMstForGuj.empPrefix}  ${orgEmpMstForGuj.empFname} ${orgEmpMstForGuj.empLname} ${orgEmpMstForGuj.empMname}</td>
			<td></td>
		</tr>
	
	
<!-- End For Gujrati -->
	
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_GENDER" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
            <td width="25%">
            	<c:if test="${engLookupDescApr.empGender eq 77}"><fmt:message key="eis.EMP_GENDER_MALE" bundle="${empEditListCommonLables}"></fmt:message></c:if>
				<c:if test="${engLookupDescApr.empGender eq 70}"><fmt:message key="eis.EMP_GENDER_FEMALE" bundle="${empEditListCommonLables}"></fmt:message></c:if>
            </td>
              
            <td width="25%"><b><hdiits:caption captionid="eis.EMP_MARITAL_STATUS" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td width="25%">${engLookupDescApr.cmnLookupMstByEmpMaritalStatusId.lookupDesc}</td>
		</tr>
		
		<tr>	 
        	<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
       		<td width="25%">
       		<fmt:formatDate pattern="dd/MM/yyyy" value="${orgEmpMstForEng.empDob}" var="dobApr"/>
       		${dobApr}
       		</td>
       			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td width="25%"><label id="emp_age_apr"></label></td>			
		</tr>

		<tr>
			<td width="25%">
			<b><hdiits:caption captionid="eis.EMP_CATEGORY" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td width="25%">
				<c:if test="${not empty engLookupDescApr.cmnLookupMstByEmpCategoryId and engLookupDescApr.cmnLookupMstByEmpCategoryId.lookupName != 'SELECT'}">
					<c:out value="${engLookupDescApr.cmnLookupMstByEmpCategoryId.lookupDesc}" />
				</c:if>
			</td>	
			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_RELIGION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">${engLookupDescApr.cmnLookupMstByEmpReligionId.lookupDesc}</td>
		</tr>
		
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_CASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<c:if test="${not empty engLookupDescApr.cmnLookupMstByEmpCasteId and engLookupDescApr.cmnLookupMstByEmpCasteId.lookupName != 'SELECT'}">
					<td width="25%">${engLookupDescApr.cmnLookupMstByEmpCasteId.lookupDesc}</td>
			</c:if>
			
			<c:if test="${empty engLookupDescApr.cmnLookupMstByEmpCasteId or engLookupDescApr.cmnLookupMstByEmpCasteId.lookupName == 'SELECT'}">
					<td width="25%">-</td>
			</c:if>
		
		
			<td width="25%">
			<b><hdiits:caption captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<c:if test="${not empty engLookupDescApr.cmnLookupMstByEmpSubCasteId and engLookupDescApr.cmnLookupMstByEmpSubCasteId.lookupName != 'SELECT'}">
					<td width="25%">${engLookupDescApr.cmnLookupMstByEmpSubCasteId.lookupDesc}</td>
			</c:if>
			
			<c:if test="${empty engLookupDescApr.cmnLookupMstByEmpSubCasteId or engLookupDescApr.cmnLookupMstByEmpSubCasteId.lookupName == 'SELECT'}">
					<td width="25%">-</td>
			</c:if>
		</tr>
		
		<tr>
			<td width="25%">
			<b><hdiits:caption captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">
				${engLookupDescApr.cmnLanguageMst.lookupDesc}
			</td>
			<td width="25%">
				<b><hdiits:caption captionid="eis.Nationality" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">${engLookupDescApr.cmnCountryMstByEmpNationality.countryName}</td>
		</tr>
		
	</table>
	<table align="center" id="notAvlblMsgId" style="display:none">
		<tr><td align="center">
			<b><hdiits:caption captionid="eis.NotAvailable" bundle="${empEditListCommonLables}" /></b>
		</td></tr>
	</table>
	</hdiits:fieldGroup>
	</td></tr><tr><td>
	<hdiits:fieldGroup id="reqempPersonalDtls" titleCaptionId="eis.REQ_PERSONAL_DETAILS" bundle="${empEditListCommonLables}">
	<table width="100%">
	
<!-- Personal Details-->
	
	
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EN_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td>${Obj.empPrefix}  ${Obj.empFname} ${Obj.empMname} ${Obj.empLname}</td>
			<td></td>
		</tr>

<!--  START For Gujrati -->
	
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.GU_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td>${Obj_gu.empPrefix}  ${Obj_gu.empFname} ${Obj_gu.empMname} ${Obj_gu.empLname}</td>
			<td></td>
		</tr>
	
<!-- End For Gujrati -->
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_GENDER" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
            <td width="25%">
            	<c:if test="${engLookupDesc.empGender eq 77}"><fmt:message key="eis.EMP_GENDER_MALE" bundle="${empEditListCommonLables}"></fmt:message></c:if>
				<c:if test="${engLookupDesc.empGender eq 70}"><fmt:message key="eis.EMP_GENDER_FEMALE" bundle="${empEditListCommonLables}"></fmt:message></c:if>
            </td>
             
            <td width="25%"><b><hdiits:caption captionid="eis.EMP_MARITAL_STATUS" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td width="25%">${engLookupDesc.cmnLookupMstByEmpMaritalStatusId.lookupDesc}</td>
        </tr>
        
        <tr>       
        	<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
       		<td width="25%">
       		<fmt:formatDate pattern="dd/MM/yyyy" value="${Obj.empDob}" var="dob"/>
       		${dob}
       		</td>
       			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td width="25%"><label id="emp_age"></label></td>			
		</tr>

		<tr>
			
			<td width="25%">
			<b><hdiits:caption captionid="eis.EMP_CATEGORY" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">${engLookupDesc.cmnLookupMstByEmpCategoryId.lookupDesc}</td>
			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_RELIGION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">${engLookupDesc.cmnLookupMstByEmpReligionId.lookupDesc}</td>
		</tr>
		
		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_CASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<c:if test="${not empty engLookupDesc.cmnLookupMstByEmpCasteId and engLookupDesc.cmnLookupMstByEmpCasteId.lookupName != 'SELECT'}">
					<td width="25%">${engLookupDesc.cmnLookupMstByEmpCasteId.lookupDesc}</td>
			</c:if>
			
			<c:if test="${empty engLookupDesc.cmnLookupMstByEmpCasteId or engLookupDesc.cmnLookupMstByEmpCasteId.lookupName == 'SELECT'}">
					<td width="25%">-</td>
			</c:if>
						
			<td width="25%">
				<b><hdiits:caption captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<c:if test="${not empty engLookupDesc.cmnLookupMstByEmpSubCasteId and engLookupDesc.cmnLookupMstByEmpSubCasteId.lookupName != 'SELECT'}">
					<td width="25%">${engLookupDesc.cmnLookupMstByEmpSubCasteId.lookupDesc}</td>
			</c:if>
			
			<c:if test="${empty engLookupDesc.cmnLookupMstByEmpSubCasteId or engLookupDesc.cmnLookupMstByEmpSubCasteId.lookupName == 'SELECT'}">
					<td width="25%">-</td>
			</c:if>
		</tr>
		
		<tr>
			<td width="25%">
			<b><hdiits:caption captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">
				${engLookupDesc.cmnLanguageMstByEmpMotherTongueId.lookupDesc}
			</td>
			<td width="25%">
				<b><hdiits:caption captionid="eis.Nationality" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td width="25%">${engLookupDesc.cmnCountryMstByEmpNationality.countryName}</td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</td></tr>
	</table>
</hdiits:fieldGroup>
<br>

<hdiits:fieldGroup id="empContactDtlsId" titleCaptionId="eis.EMP_CONTACT_DETAILS" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table width="100%">
	<tr><td>
	<hdiits:fieldGroup titleCaptionId="eis.APR_CONTACT_DETAILS" bundle="${empEditListCommonLables}">
	<table width="100%" id="aprEmpContactDtlsTblId" style="display: ">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_PHONE_NO" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td width="25%">${empcontactMstForPhone.contactNumber}</td>
			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			
			<c:if test="${not empty empcontactMstForMobile and empcontactMstForMobile.contactNumber != 0 and empcontactMstForMobile.contactNumber!=''}">
					<td width="25%">${empcontactMstForMobile.contactNumber}</td>
			</c:if>
			<c:if test="${empty empcontactMstForMobile or empcontactMstForMobile.contactNumber == '' or empcontactMstForMobile.contactNumber == 0}">
					<td width="25%">-</td>
			</c:if>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			
			<c:if test="${not empty empcontactMstForEmail and empcontactMstForEmail.contactNumber != ''}">
					<td width="25%">${empcontactMstForEmail.contactNumber}</td>
			</c:if>
			<c:if test="${empty empcontactMstForEmail or empcontactMstForEmail.contactNumber == ''}">
					<td width="25%">-</td>
			</c:if>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	<table align="center" id="aprCntcNotAvlblMsgId" style="display:none">
		<tr><td align="center">
			<b><hdiits:caption captionid="eis.NotAvailable" bundle="${empEditListCommonLables}" /></b>
		</td></tr>
	</table>
	</hdiits:fieldGroup>
	</td></tr>
	<tr><td>
<!--Contact Details-->

	<hdiits:fieldGroup titleCaptionId="eis.REQ_CONTACT_DETAILS" bundle="${empEditListCommonLables}">
	<table width="100%">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_PHONE_NO" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td width="25%">${Obj.empStdCode}-${Obj.empPhoneNumber}</td>
			
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<c:if test="${not empty Obj and Obj.empMobileNumber ne null and Obj.empMobileNumber!=''}">
					<td width="25%">${Obj.empMobileNumber}</td>
			</c:if>
			<c:if test="${empty Obj or Obj.empMobileNumber eq null or Obj.empMobileNumber eq '' }">
					<td width="25%">-</td>
			</c:if>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<c:if test="${not empty Obj and Obj.empEmail != ''}">
					<td width="25%">${Obj.empEmail}</td>
			</c:if>
			<c:if test="${empty Obj or Obj.empEmail == ''}">
					<td width="25%">-</td>
			</c:if>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	</td></tr>
</table>	
</hdiits:fieldGroup>

<br>

<hdiits:fieldGroup id="empPhotoAttachmentDtlsId" titleCaptionId="eis.EMP_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table width="100%">
	  <tr id="aprPhotoAtchmntTr" style="display:block ">
			<td class="fieldLabel" colspan="10">
			<table class="tabtable" align="center" id="photoAttchmntTabId" style="display:block ">
				<tr>
				<hdiits:fmtMessage key="eis.APR_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}" var="aprPhotoAttachment" />
					<td class="fieldLabel"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="AprPhotoAtchmnt" />
						<jsp:param name="formName" value="frmEditDispForm" />
						<jsp:param name="attachmentType" value="Biometric" />
						<jsp:param name="attachmentTitle" value="${aprPhotoAttachment}" />
						<jsp:param name="multiple" value="N" />
					</jsp:include></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr id="aprPhotoAtchmntMsgTr" style="display: none;">
			<td align="center"><hdiits:fieldGroup titleCaptionId="eis.APR_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}">
				<b><hdiits:caption captionid="eis.NotAvailable" bundle="${empEditListCommonLables}" /></b>
			</hdiits:fieldGroup></td>
		</tr>
	  	<tr>
			<td class="fieldLabel" colspan="10">
			<table class="tabtable" align="center">
				<tr>
					<hdiits:fmtMessage key="eis.REQ_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}" var="reqPhotoAttachment" />
					<td class="fieldLabel"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="photoAttachment" />
						<jsp:param name="formName" value="frmEditDispForm" />
						<jsp:param name="attachmentType" value="Biometric" />
						<jsp:param name="attachmentTitle" value="${reqPhotoAttachment}" />
						<jsp:param name="multiple" value="N" />
					</jsp:include></td>
				</tr>
			</table>
			</td>
		</tr>
</table>
</hdiits:fieldGroup>
<br>
	<table align="center">
	<tr><td><hdiits:hidden name="reqId" default="${Obj.id.reqId}" /></td></tr>
		</table>

	<table id="tabNavigation" style="display:none" >
		<tr><td>
		<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
			<jsp:param name="disableReset" value="true"/> 
		</jsp:include>
		</td></tr>
	</table>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
	</div>
</div>

<div  id="msg" style="display:none">
<br><br><br><br><br>
 <hr align="center" width=" 50%">
			<center><b><hdiits:caption captionid="eis.RequestApprove" bundle="${empEditListCommonLables}"/></b></center>
 <hr align="center" width="50%">
</div>
	
</hdiits:form>

<script type="text/javascript">
		countAge('${dob}','emp_age','innerHTML'); 
		countAge('${dobApr}','emp_age_apr','innerHTML');  
		var engLookupDescApr = '${engLookupDescApr}';
		var empContactDtls = '${empContactDtls}';
		
		var  ObjGu = '${Obj_gu.actionFlag}';
		var  ObjEn = '${Obj.actionFlag}';
		onLoadDataForApproveChangeEmpProfile();
		initializetabcontent("maintab");
</script>



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
