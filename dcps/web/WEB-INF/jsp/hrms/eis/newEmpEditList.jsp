<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/ChangeEmpProfile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request" />

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="maritalStatus" value="${resValue.maritalStatus}" />
<c:set var="hrEisEmpMst" value="${resValue.hrEisEmpMst1}" />
<c:set var="editLst" value="${resValue.editList}" />
<c:set var="langList" value="${resValue.langList}" />
<c:set var="ReligionList" value="${resValue.religionList}" />
<c:set var="categoryList" value="${resValue.categoryList}" />
<c:set var="stdCode" value="${resValue.std}" />
<c:set var="phoneNo" value="${resValue.phone}" />
<c:set var="mobileNo" value="${resValue.mobile}" />
<c:set var="email" value="${resValue.email}" />
<c:set var="element_gu" value="${resValue.element_gu}" />
<c:set var="element_en" value="${resValue.element_en}" />
<c:set var="cmnCountryMstList" value="${resValue.cmnCountryMstList}" />

<script>
	var cmbObjFlag=''  
	var getNextComboData = false;  
	var tempCmbCastName='${editLst.cmnLookupMstByEmpCasteId.lookupName}';
	var tempCmbSubCastName='${editLst.cmnLookupMstByEmpSubCasteId.lookupName}';
	var tempCmbEmpReligionName='${editLst.cmnLookupMstByEmpReligionId.lookupName}';
	var countryCode='${editLst.cmnCountryMstByEmpNationality.countryCode}';
	var langName='${editLst.cmnLanguageMst.lookupName}';// Change by sunil for language
	
	var ChangeEmpProfileAlertMsgArr=new Array();
	ChangeEmpProfileAlertMsgArr[0]='<fmt:message bundle="${empEditListCommonLables}" key="eis.ProperDate_alert"/>';
	ChangeEmpProfileAlertMsgArr[1]='<fmt:message bundle="${empEditListCommonLables}" key="Eis.stdPhoneNo"/>';
	ChangeEmpProfileAlertMsgArr[2]='<fmt:message bundle="${empEditListCommonLables}" key="Eis.mobileNo"/>';	
</script>

<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=getNewEmpData" encType="multipart/form-data">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
		<a href="#" rel="tcontent1">
		<b><fmt:message key="eis.UPDATE_EMP_MASTER" bundle="${empEditListCommonLables}"></fmt:message></b>
		</a>
		</li>
	</ul>
	</div>


	<div class="tabcontentstyle">
     	<div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup titleCaptionId="eis.EMP_PERSONAL_DETAILS" bundle="${empEditListCommonLables}">
	<table class="tabtable">
		<!-- <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_PERSONAL_DETAILS"/></u></strong></font></td>
		</tr>
		 -->

<!-- Personal Details-->

		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_SALUTATION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:text name="Salutation"
				maxlength="6" captionid="eis.EMP_SALUTATION" size="3"
				bundle="${empEditListCommonLables}" validation="txt.isrequired"
				default="${element_en.empPrefix}" /></td>
		</tr>
		
		
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EN_LAST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:text name="emp_last_name" captionid="eis.EN_LAST_NAME" bundle="${empEditListCommonLables}"
				maxlength="20" validation="txt.isrequired" mandatory="true"
				default="${element_en.empLname}" /></td>
			<td class="fieldLabel"><b>
				<hdiits:caption	captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td>
			   <hdiits:text name="emp_first_name" captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}"
				maxlength="20" validation="txt.isrequired" mandatory="true"
				default="${element_en.empFname}" />
			</td>
			
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:text name="emp_middle_name"
				captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="20"
				default="${element_en.empMname}" /></td>
		</tr>


<!--************************** FOR GUJRATI ENTRY******************************** -->

	<tr>
			<td class="fieldLabel">
			<b><hdiits:caption	captionid="eis.GU_LAST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td>
			<hdiits:text name="gu_emp_last_name" captionid="eis.GU_LAST_NAME" bundle="${empEditListCommonLables}" maxlength="20" validation="txt.isrequired"	mandatory="true" default="${element_gu.empLname}" />
			</td>
			
			<td class="fieldLabel">
			<b><hdiits:caption	captionid="eis.GU_FIRST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td>
			 <hdiits:text name="gu_emp_first_name" captionid="eis.GU_FIRST_NAME" bundle="${empEditListCommonLables}"	maxlength="20" validation="txt.isrequired" mandatory="true" default="${element_gu.empFname}" />
			</td>
			
			<td class="fieldLabel">
			<b><hdiits:caption captionid="eis.GU_MIDDLE_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td>
			<hdiits:text name="gu_emp_middle_name"	captionid="eis.GU_MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="20" default="${element_gu.empMname}" />
			</td>
		</tr>

<!-- ********************END OF GUJRATI LANGUAGE************ -->


		<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.EMP_GENDER"
				bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
               <td><hdiits:select name="gender" default="${hrEisEmpMst.empGender}"> 
               <hdiits:option value="M"><fmt:message key="eis.EMP_GENDER_MALE" bundle="${empEditListCommonLables}"/></hdiits:option>
			   <hdiits:option value="F"><fmt:message key="eis.EMP_GENDER_FEMALE" bundle="${empEditListCommonLables}"/></hdiits:option>              
               
               </hdiits:select>   </td>



			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>

			<td><fmt:formatDate var="empDob"
				value="${element_en.empDob}" pattern="dd/MM/yyyy" /> <hdiits:dateTime
				captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}" 
				name="emp_dob" validation="txt.isdt,txt.isrequired" mandatory="true" afterDateSelect="countAge('','emp_age','value','','emp_dob','','frmBF')" /></td>
			
			<td><b><hdiits:caption captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}"></hdiits:caption></b> </td>
			<td class="fieldLabel"><hdiits:text name="emp_age" caption="Age" size="1" style="text-align: right"
				maxlength="3" readonly="true"/></td>
			
			</tr>

		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_RELIGION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select captionid="eis.EMP_RELIGION" 
				bundle="${empEditListCommonLables}" id="Religion" name="Religion"
				validation="sel.isrequired" mandatory="true" sort="false" 
				onchange="getNextCmbValue(this,1);">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="religion" items="${ReligionList}">
					<c:choose>
						<c:when
							test="${editLst.cmnLookupMstByEmpReligionId.lookupName==religion.lookupName}">
							<option value=<c:out value="${religion.lookupName}" />
								selected="true"><c:out value="${religion.lookupDesc}" /></option>
						</c:when>
						<c:otherwise>
							<option value=<c:out value="${religion.lookupName}"/>><c:out
								value="${religion.lookupDesc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</hdiits:select></td>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.EMP_CASTE"
				bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td><hdiits:select name="emp_caste_id" id="emp_caste_id"
				captionid="eis.EMP_CASTE" bundle="${empEditListCommonLables}" 
				onchange="getNextCmbValue(this,2);">
				<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
			</hdiits:select></td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select name="emp_sub_caste" id="emp_sub_caste"
				captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}" >
				<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
			</hdiits:select></td>
		</tr>
		
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_CATEGORY" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select captionid="eis.EMP_CATEGORY"
				bundle="${empEditListCommonLables}" name="Category" sort="false"
				validation="sel.isrequired" mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="category" items="${categoryList}">
					<c:choose>
						<c:when
							test="${editLst.cmnLookupMstByEmpCategoryId.lookupName==category.lookupName}">
							<option value=<c:out value="${category.lookupName}" />
								selected="true"><c:out value="${category.lookupDesc}" /></option>
						</c:when>
						<c:otherwise>
							<option value=<c:out value="${category.lookupName}"/>><c:out
								value="${category.lookupDesc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</hdiits:select></td>
			
			
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select name="emp_mother_tongue"
				captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}" mandatory="true" sort="false"
				validation="sel.isrequired">
			<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="langList" items="${langList}">	    						    					
   					<option value=<c:out value="${langList.lookupName}"/>>
						<c:out value="${langList.lookupDesc}"/></option>				
				</c:forEach>
			</hdiits:select></td>
		
			
			
			<td class="fieldLabel"><b><hdiits:caption	captionid="eis.EMP_MARITAL_STATUS" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td class="fieldLabel"><hdiits:select captionid="eis.EMP_MARITAL_STATUS" bundle="${empEditListCommonLables}"
				name="Marital_Status" sort="false" validation="sel.isrequired"
				mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="lStmaritalStatus" items="${maritalStatus}">
					<c:choose>
						<c:when
							test="${editLst.cmnLookupMstByEmpMaritalStatusId.lookupName==lStmaritalStatus.lookupName}">
							<option value=<c:out value="${lStmaritalStatus.lookupName}" />
								selected="true"><c:out
								value="${lStmaritalStatus.lookupDesc}" /></option>
						</c:when>
						<c:otherwise>
							<option value=<c:out value="${lStmaritalStatus.lookupName}"/>>
							<c:out value="${lStmaritalStatus.lookupDesc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</hdiits:select></td>
			
		</tr>
		
		<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Nationality" bundle="${empEditListCommonLables}" /></b></td>
			<td class="fieldLabel">
				<hdiits:select captionid="eis.Nationality" bundle="${empEditListCommonLables}" name="Nationality" id="Nationality" mandatory="true" validation="sel.isrequired" style="width:80%">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="nationalityList" items="${cmnCountryMstList}">
					<option value="<c:out value="${nationalityList.countryCode}"/>">
					<c:out value="${nationalityList.countryName}" /></option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
		
</table>
</hdiits:fieldGroup>
<!--Contact Details-->
<hdiits:fieldGroup titleCaptionId="eis.EMP_CONTACT_DETAILS" bundle="${empEditListCommonLables}">
<table class="tabtable" border="0">
		<!--  <tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_CONTACT_DETAILS"/></u></strong></font></td>
		</tr>
		-->
		<tr>
			<td class="fieldLabel" width="14%"></td>
			<td class="fieldLabel" width="7%" colspan="2"><hdiits:caption	captionid="Eis.StdCode" bundle="${empEditListCommonLables}"></hdiits:caption></td>
			<td class="fieldLabel" width="14%"></td>
		</tr>
		<tr>	
			<td class="fieldLabel" width="15%"><b><hdiits:caption	captionid="eis.EMP_PHONE_NO" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td width="2%" align="right">
				<hdiits:text name="emp_Phone_std" maxlength="3" id="emp_Phone_std" mandatory="true"
				size="1" style="text-align: right" validation="txt.isrequired,txt.isnumber"  captionid="Eis.StdCode" bundle="${empEditListCommonLables}" default="${stdCode}" onkeypress="return checkNumberOnly(false)"/>
			</td>
			<td width="15%">
			<hdiits:text style="text-align: right" name="emp_Phone_Num" id="emp_Phone_Num" mandatory="true" size="8" maxlength="10" 
				validation="txt.isrequired,txt.isnumber" captionid="eis.EMP_PHONE_NO" bundle="${empEditListCommonLables}" default="${phoneNo}" onkeypress="return checkNumberOnly(false)"></hdiits:text>
			</td>
			
			<td class="fieldLabel" width="20%"><b><hdiits:caption	captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="20%"><hdiits:text name="emp_Mobile_No" maxlength="10" size="10"
				id="emp_Mobile_No" style="text-align: right" captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}" onkeypress="return checkNumberOnly(false)" validation="txt.isnumber"></hdiits:text></td>
						
			<td class="fieldLabel" width="10%"><b><hdiits:caption  captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
			<td class="fieldLabel"><hdiits:text name="emp_email" id="emp_email" validation="txt.email" captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}" default="${email}" maxlength="45"></hdiits:text>
		  </td>
		</tr>
	</table>
	</hdiits:fieldGroup>
<!-- Photograph Attachment-->
<br>
<hdiits:fieldGroup id="attachment_Details_id" titleCaptionId="eis.attachment_Details" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table class="tabtable" border="0">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_PHOTO_ATTACHMENT"/></u></strong></font></td>
		</tr>
		-->
		<tr>
			<td class="fieldLabel" colspan="10">
			<table class="tabtable" align="center">
				<tr>
					<hdiits:fmtMessage key="eis.EMP_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}" var="photoAttachment" />
					<td class="fieldLabel"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="photoAttachment" />
						<jsp:param name="formName" value="frmBF" />
						<jsp:param name="attachmentType" value="Biometric" />
						<jsp:param name="attachmentTitle" value="${photoAttachment}" />
						<jsp:param name="multiple" value="N" />
						<jsp:param name="removeAttachmentFromDB" value="Y" />
						<jsp:param name="documentType" value="IMAGE" />
						<jsp:param name="bioDeviceType" value="FACE,MULTI_FINGER" />
					</jsp:include></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</hdiits:fieldGroup>
<br>
<table class="tabtable" border="0">
		<tr>
		<td colspan="10" align="center"><hdiits:button name="btnSubmit"
			type="button" captionid="EIS.Submit" bundle="${empEditListCommonLables}" onclick="saveEditDataForm()"></hdiits:button>
			<hdiits:button name="Close" type="button" captionid="EIS.CloseButton" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
		</td>
   </tr>
</table>

</div>
</div>
<hdiits:validate locale="${locale}" controlNames="emp_last_name,emp_first_name,gu_emp_last_name,gu_emp_first_name,emp_dob,Religion,Category,emp_mother_tongue,Marital_Status,Nationality,emp_Phone_std,emp_Phone_Num,emp_Mobile_No,emp_email" /> 

</hdiits:form>

<script type="text/javascript">
	birthDate = '${empDob}';
	empMobileNo ="${mobileNo}";
	document.frmBF.emp_dob.value=birthDate;
	onLoadDataForChangeEmpProfile();		
	initializetabcontent("maintab");
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
