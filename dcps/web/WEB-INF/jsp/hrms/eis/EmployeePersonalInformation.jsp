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
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EmployeePersonalInformation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>

<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request" />

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="xmlFile" value="${resValue.xmlFile}" ></c:set>
<c:set var="lArrObj" value="${resValue.lArrObj}" ></c:set>
<c:set var="contactxmlFile" value="${resValue.contactxmlFile}" ></c:set>
<c:set var="contactArrObj" value="${resValue.contactArrObj}" ></c:set>
<c:set var="maritalStatus" value="${resValue.maritalStatus}" ></c:set>
<c:set var="orgEmpMst" value="${resValue.hrEisEmpMst1}" ></c:set>
<c:set var="editLst" value="${resValue.editList}" ></c:set>
<c:set var="empBGList" value="${resValue.empBGList}"></c:set>
<c:set var="proficiencyList" value="${resValue.proficiencyList}"></c:set>
<c:set var="readProficiencyList" value="${resValue.readProficiencyList}"></c:set>
<c:set var="writeProficiencyList" value="${resValue.writeProficiencyList}"></c:set>
<c:set var="ContactTypeList" value="${resValue.ContactTypeList}"></c:set>
<c:set var="disabilityList" value="${resValue.disabilityList}"></c:set>
<c:set var="langList" value="${resValue.langList}"></c:set>
<c:set var="langProfList" value="${resValue.langList}"></c:set><!-- Change By Sunil For language -->
<c:set var="empTypeList" value="${resValue.empTypeList}"></c:set>
<c:set var="strLookupName" value="${resValue.strLookupName}"></c:set>
<c:set var="ReligionList" value="${resValue.religionList}"></c:set>
<c:set var="categoryList" value="${resValue.categoryList}" > </c:set>
<c:set var="GenderTypeList" value="${resValue.GenderTypeList}" > </c:set>
<c:set var="saluList" value="${resValue.saluList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="checkPhotoAttachment" value="${resValue.checkPhotoAttachment}" ></c:set>
<c:set var="checkThumbAttachment" value="${resValue.checkThumbAttachment}" ></c:set>
<c:set var="radNativeAddress" value="${resValue.radNativeAddress}" ></c:set>
<c:set var="radPermanentAddress" value="${resValue.radPermanentAddress}" ></c:set>
<c:set var="radCurrentAddress" value="${resValue.radCurrentAddress}" ></c:set>
<c:set var="selectedUserId" value="${resValue.userId}" ></c:set>
<c:set var="cmnCountryMstList" value="${resValue.cmnCountryMstList}" />
<c:set var="strDepartmentName" value="${resValue.strDepartmentName}" />

<script type="text/javascript">
	
	var empPersonalInfoAlertMsgArr = new Array();
	empPersonalInfoAlertMsgArr[0]='<fmt:message bundle="${empEditListCommonLables}" key="eis.ProperDate_alert"/>';
	empPersonalInfoAlertMsgArr[1]='<fmt:message bundle="${empEditListCommonLables}" key="eis.ValidEmail_alert"/>';
	empPersonalInfoAlertMsgArr[2]='<fmt:message bundle="${empEditListCommonLables}" key="eis.RightFormateEmail_alert"/>';
	empPersonalInfoAlertMsgArr[3]='<fmt:message bundle="${empEditListCommonLables}" key="eis.Position@_alert"/>';
	empPersonalInfoAlertMsgArr[4]='<fmt:message bundle="${empEditListCommonLables}" key="eis.lastEmail_alert"/>';
	empPersonalInfoAlertMsgArr[5]='<fmt:message bundle="${empEditListCommonLables}" key="eis.dataWeightAlert"/>';
	empPersonalInfoAlertMsgArr[6]='<fmt:message bundle="${empEditListCommonLables}" key="eis.dataHeightAlert"/>';
	empPersonalInfoAlertMsgArr[7]='<fmt:message bundle="${empEditListCommonLables}" key="eis.dataChestAlert"/>';
	empPersonalInfoAlertMsgArr[8]='<fmt:message bundle="${empEditListCommonLables}" key="eis.contactType_alert"/>';
	empPersonalInfoAlertMsgArr[9]='<fmt:message bundle="${empEditListCommonLables}" key="eis.contactNo_alert"/>';
	empPersonalInfoAlertMsgArr[10]='<fmt:message bundle="${empEditListCommonLables}" key="eis.Mobile_alert"/>';
	empPersonalInfoAlertMsgArr[11]='<fmt:message bundle="${empEditListCommonLables}" key="eis.Mobile_Validlen_alert"/>';
	empPersonalInfoAlertMsgArr[12]='<fmt:message bundle="${empEditListCommonLables}" key="eis.PhoneNo_alert"/>';
	empPersonalInfoAlertMsgArr[13]='<fmt:message bundle="${empEditListCommonLables}" key="eis.LanguageType_alert"/>';
	empPersonalInfoAlertMsgArr[14]='<fmt:message bundle="${empEditListCommonLables}" key="eis.speakLanguage_alert"/>';
	empPersonalInfoAlertMsgArr[15]='<fmt:message bundle="${empEditListCommonLables}" key="eis.readLanguage_alert"/>';
	empPersonalInfoAlertMsgArr[16]='<fmt:message bundle="${empEditListCommonLables}" key="eis.writeLanguage_alert"/>';
	empPersonalInfoAlertMsgArr[17]='<fmt:message bundle="${empEditListCommonLables}" key="eis.Language_alert"/>';
	empPersonalInfoAlertMsgArr[18]='<fmt:message bundle="${empEditListCommonLables}" key="EIS.Select"/>';
	
</script>

<hdiits:form name="frmBF" validate="true" method="POST" action="hrms.htm?actionFlag=addEditEmpInfo" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<c:choose>
				<c:when test="${strDepartmentName eq 'HOMEGUARD'}">
					<li class="selected"><a href="#" rel="tcontent1">
					<b> <fmt:message key="EIS.PersonalDtls" bundle="${empEditListCommonLables}"/> </b> </a></li>
				</c:when>	
				<c:otherwise>	
					<li class="selected"><a href="#" rel="tcontent1">
					<b> <fmt:message key="EIS.PersonalDtls" bundle="${empEditListCommonLables}"/> </b> </a></li>
					
					<li class="selected"><a href="#"
						onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
					<b> <fmt:message key="eis.quali_dtls"
						bundle="${empEditListCommonLables}"></fmt:message> </b> </a></li>
		
					<li class="selected" style="width: 152px;">
						<a href="#" onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')" style="width: 142px;">
						<b> <fmt:message key="eis.extraCoCurricularDtls" bundle="${empEditListCommonLables}"></fmt:message> </b> </a></li>
		
					<li class="selected"><a href="#"
						onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
					<b> <fmt:message key="eis.FamilyDtls"
						bundle="${empEditListCommonLables}" /> </b> </a></li>
					
					<li class="selected"><a href="#"
						onclick="javascript:openAppWindow('NomineeDetails&flag=getComboDetails&workFlowEnabled=false')">
					<b> <fmt:message key="eis.nominee_dtls"
						bundle="${empEditListCommonLables}" /> </b> </a></li>
					
					<li class="selected"><a href="#"
						onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"> <b>
					<fmt:message key="EIS.EmpMislDtl"
						bundle="${empEditListCommonLables}" /> </b> </a></li>
				</c:otherwise>	
			</c:choose>		
		</ul>
	</div>
	<div class="tabcontentstyle">
     	<div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup id="personalFieldGroupId" titleCaptionId="eis.EMP_PERSONAL_DETAILS" bundle="${empEditListCommonLables}">
	<table class="tabtable" width="100%">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_PERSONAL_DETAILS"/></u></strong></font></td>
		</tr>-->

<!-- Personal Details-->

		<tr>
			<td class="fieldLabel" align="left"><b>
					<hdiits:caption
				captionid="eis.EMP_SALUTATION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td align="left"><hdiits:text name="Salutation" 
				maxlength="6" captionid="Salutation" size="3" readonly="true"
				bundle="${empEditListCommonLables}" validation="txt.isrequired"
				default="${orgEmpMst.empPrefix}" /></td>
		</tr>
		
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:text name="emp_last_name" captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}"
				maxlength="20" validation="txt.isrequired" readonly="true"
				default="${orgEmpMst.empLname}" /></td>
			<td class="fieldLabel"><b>
				<hdiits:caption	captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td>
			   <hdiits:text name="emp_first_name" captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}"
				maxlength="20" validation="txt.isrequired" readonly="true"
				default="${orgEmpMst.empFname}" />
			</td>
			
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:text name="emp_middle_name" readonly="true"
				captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}" maxlength="20" 
				default="${orgEmpMst.empMname}" /></td>
		</tr>

		<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.EMP_GENDER"
				bundle="${empEditListCommonLables}"></hdiits:caption></b></td>
               <td>
	               <hdiits:select name="gender" captionid="eis.EMP_GENDER" bundle="${empEditListCommonLables}"> 
              		 <hdiits:option value="M"><fmt:message  key="eis.EMP_GENDER_MALE" bundle="${empEditListCommonLables}"  /></hdiits:option>
			  	 	<hdiits:option value="F"><fmt:message  key="eis.EMP_GENDER_FEMALE" bundle="${empEditListCommonLables}"  /></hdiits:option>               
                    </hdiits:select>   
               </td>
			
			
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>

			<td><fmt:formatDate var="empDob"
				value="${orgEmpMst.empDob}" pattern="dd/MM/yyyy" /> <hdiits:dateTime
				captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}" 
				name="emp_dob" validation="txt.isdt,txt.isrequired" disabled="true" /></td>
			
			<td><b><hdiits:caption captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}"></hdiits:caption></b> </td>
			<td class="fieldLabel"><hdiits:text name="emp_age" id="emp_age" captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}" size="1"
				maxlength="3" readonly="true" style="text-align: right"/></td>
			
			</tr>

		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_RELIGION" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select captionid="eis.EMP_RELIGION" 
				bundle="${empEditListCommonLables}" id="Religion" name="Religion"
				validation="sel.isrequired" mandatory="true" sort="false" 
				onchange="getNextCmbValue(this,1);">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"></fmt:message>
				</hdiits:option>
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
			<td><hdiits:select name="emp_caste_id" id="emp_caste_id" captionid="eis.EMP_CASTE" bundle="${empEditListCommonLables}" sort="false" onchange="getNextCmbValue(this,2);">
				<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
			</hdiits:select></td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select name="emp_sub_caste" id="emp_sub_caste"	captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}" sort="false">
				<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
			</hdiits:select></td>
		</tr>
		
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_CATEGORY" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			<td><hdiits:select captionid="eis.EMP_CATEGORY"
				bundle="${empEditListCommonLables}" name="Category" sort="false" 
				validation="sel.isrequired" mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
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
			<!-- Change By Sunil For language -->
			 <td><hdiits:select name="emp_mother_tongue" captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}" mandatory="true" validation="sel.isrequired" sort="false">
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
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
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
			<td class="fieldLabel"><b><hdiits:caption
				captionid="eis.EMP_TYPE" bundle="${empEditListCommonLables}"></hdiits:caption></b>
			</td>
			
			<td>
				<hdiits:select captionid="eis.EMP_TYPE"	bundle="${empEditListCommonLables}" name="EmpType" sort="false" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="Select">
						<fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
						
						<c:forEach var="empType" items="${empTypeList}">
							<option value="<c:out value="${empType.lookupName}"/>">
							<c:out value="${empType.lookupDesc}" /></option>
						</c:forEach>
				</hdiits:select>
			</td>
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
<br>
<!-- Language Proficiyancy Details -->
		
	<!--<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="10" align="center">	
	 	<font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.Language_Details"/></u></strong>
		</font></td>
	</tr>-->
	<!-- Change By Sunil For language -->
<hdiits:fieldGroup id="langProFieldGroupId" titleCaptionId="eis.Language_Details" bundle="${empEditListCommonLables}" collapseOnLoad="true">
<table class="tabtable" width="100%">
	<tr>
	<td align="center" colspan="10">
		<table border="0" class="fieldLabel" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse"  width="100%">
		<tr>
			<td class="fieldLabel">
			<hdiits:select name="emp_lang_known" id="emp_lang_known" captionid="eis.Language" bundle="${empEditListCommonLables}" validation ="sel.isrequired" mandatory="true" sort="false">
			<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
				<c:forEach var="langProfList" items="${langProfList}">	    						    					
   					<option value=<c:out value="${langProfList.lookupName}"/>>
						<c:out value="${langProfList.lookupDesc}"/></option>				
				</c:forEach>	
			</hdiits:select>
		</td>
			<td><b><hdiits:caption captionid="eis.EMP_SPEAK_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
		
		
			<td>	
			<hdiits:select name="emp_speak_proficiency" captionid="eis.EMP_SPEAK_PROFICIENCY" bundle="${empEditListCommonLables}"  sort="true" mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
				<c:forEach var="proficiencyList" items="${proficiencyList}">	    						    					
   					<option value=<c:out value="${proficiencyList.lookupName}"/>>
						<c:out value="${proficiencyList.lookupDesc}"/></option>				
				</c:forEach>	
				
				</hdiits:select>
			</td>
			<td><b><hdiits:caption captionid="eis.EMP_READ_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
		
		
			<td>	<hdiits:select name="emp_read_proficiency" captionid="eis.EMP_READ_PROFICIENCY" bundle="${empEditListCommonLables}"  sort="true" mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
				<c:forEach var="readProficiencyList" items="${readProficiencyList}">	    						    					
   						<option value=<c:out value="${readProficiencyList.lookupName}"/>>
						<c:out value="${readProficiencyList.lookupDesc}"/></option>				
				</c:forEach>	
				</hdiits:select>
			</td>
			<td><b><hdiits:caption captionid="eis.EMP_WRITE_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
	
			<td>	<hdiits:select name="emp_write_proficiency" captionid="eis.EMP_WRITE_PROFICIENCY" bundle="${empEditListCommonLables}"  sort="true" mandatory="true">
				<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></hdiits:option>
				<c:forEach var="writeProficiencyList" items="${writeProficiencyList}">	    						    					
   					<option value=<c:out value="${writeProficiencyList.lookupName}"/>>
						<c:out value="${writeProficiencyList.lookupDesc}"/></option>				
				</c:forEach>	
				</hdiits:select>
			</td>	
		</tr>
	</table></td>
</tr>

	<tr><td></td></tr>
			
	<tr>
		<td  colspan="10" align="center" class="fieldLabel">
			<hdiits:button type="button" name="addLanguage" captionid="EIS.Add" bundle="${empEditListCommonLables}" onclick="addLangauge('Add')"/>
			<hdiits:button type="button" name="saveLanguage" captionid="EIS.Update" bundle="${empEditListCommonLables}" readonly="true" onclick="addLangauge('Update')"/>
			<hdiits:button type="button" name="resetLang" captionid="EIS.Reset" bundle="${empEditListCommonLables}"  onclick="javascript:resetLanguage();"/>
		</td>
	</tr>
	
<!-- Add Language Details -->

<tr><td></td></tr>
<tr>
		<td align="center" colspan="10">
			<table border="1" class="fieldLabel" name="multipleAddLangTable" id="multipleAddLangTable" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" width="60%">
				<tr>
						
					<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Language" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.EMP_SPEAK_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.EMP_READ_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>	
					<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.EMP_WRITE_PROFICIENCY" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
					<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.TABLE_ACTIONS" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
				</tr>			
			</table>
			<c:if test="${not empty lArrObj}"><!-- Change By Sunil For language -->
			<c:forEach items="${lArrObj}" var="empLanguageTuples" varStatus="x">
				<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>
				<c:set var="langName" value="${empLanguageTuples.cmnLanguageMstByLanguageId.lookupDesc}" />
				<c:set var="proficiency" value="${empLanguageTuples.cmnLookupMst.lookupDesc}" />
				<c:set var="readProficiency" value="${empLanguageTuples.cmnLookupMstReadProf.lookupDesc}" />
				<c:set var="writeProficiency" value="${empLanguageTuples.cmnLookupMstWriteProf.lookupDesc}" />
				<script type="text/javascript">
					var xmlFileName = '${curXMLFileName}';
					var displayFieldA  = new Array('${langName}','${proficiency}','${readProficiency}','${writeProficiency}');
					addDBDataInTable('multipleAddLangTable','addedlangXML',displayFieldA,xmlFileName,'editDBLanguage', 'deleteDBLanguage');					
				</script>
			</c:forEach>
			</c:if>
		</td>
</tr>

</table>
</hdiits:fieldGroup>
<br>
<!--AttachMent  Details -->

<!-- Photograph Attachment-->
<!--<table class="tabtable" width="100%">
	<tr bgcolor="#386CB7" align="left">
		<td  class="fieldLabel" colspan="10" align="left"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.attachment_Details"/></u></strong></font>
		<a href="javascript:void(0);" onclick="expandCollapse(attachmentDivId,imgAttachmentExpandId);"><img id="AttachmentExpandAgent" src="images/expand.gif" /></a></td>
	</tr>
</table>-->

<hdiits:fieldGroup id="attachmentFieldGroupId" titleCaptionId="eis.attachment_Details" bundle="${empEditListCommonLables}" collapseOnLoad="true">
<table id="attachmentDiv" width="100%">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_PHOTO_ATTACHMENT"/></u>
			</strong></font></td>
		</tr>-->
		<tr>
			<td class="fieldLabel" colspan="10">
			<table class="tabtable" align="center">
				<tr>
					<hdiits:fmtMessage key="eis.EMP_PHOTO_ATTACHMENT" bundle="${empEditListCommonLables}" var="photoAttachmentTitle" />
					<td class="fieldLabel">
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="photoAttachment" />
							<jsp:param name="formName" value="frmBF" />
							<jsp:param name="attachmentType" value="Biometric" />
							<jsp:param name="attachmentTitle" value="${photoAttachmentTitle}" />
							<jsp:param name="multiple" value="N" />
							<jsp:param name="removeAttachmentFromDB" value="Y" />
							<jsp:param name="documentType" value="IMAGE" />
							<jsp:param name="bioDeviceType" value="FACE,MULTI_FINGER" />
						</jsp:include>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</table>
</hdiits:fieldGroup>
<br>

<hdiits:fieldGroup id="addressDtlsFieldGroupId" titleCaptionId="Address_Details" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<div id="addressDiv" width="100%">
		<table width="100%" align="center" >	
			<tr align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
					<hdiits:fmtMessage key="eis.BIRTH_PLACE" bundle="${empEditListCommonLables}"  var="eisBirthPlaceTitle" />
					<jsp:include page="../../common/address.jsp">
						<jsp:param name="addrName" value="birthPlaceAddress" />
						<jsp:param name="addressTitle" value="${eisBirthPlaceTitle}" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="readOnly" value="Yes" />	
						<jsp:param name="mandatory" value="Yes" />
						<jsp:param name="collapseAddress" value="false"/>
					</jsp:include>
				</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
		<br>
		<table width="100%" align="center" >	
			<tr align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
					<hdiits:fieldGroup id="nativeAddressDtlsFieldGroupId" titleCaptionId="eis.NATIVE_PLACE" bundle="${empEditListCommonLables}">
					<table width="80%" align="center">
						<tr align="center">			
							<td class="fieldLabel" > 
									<hdiits:radio name="radNativeAddress" id="radNativeAddress" value="Y"  onclick="showHideAddress(1,'Y')" captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
									<hdiits:radio name="radNativeAddress" id="radNativeAddress" value="N"  onclick="showHideAddress(1,'N')" captionid="eis.No" bundle="${empEditListCommonLables}"/>
									<b><fmt:message key="eis.SAME_AS_BIRTH_Address" bundle="${empEditListCommonLables}"></fmt:message></b>
							</td>
						</tr>	
					</table>
					
					<table width="100%" align="center" >	
						<tr id="row_personNativeAddress" align="left" width="100%">			
							<td class="fieldLabel"  width="1%"></td>
							<td class="fieldLabel" colspan="2">
								<hdiits:fmtMessage key="Address_Details" bundle="${empEditListCommonLables}" var="eisNativePlaceTitle" />	
								<jsp:include page="../../common/address.jsp">
									<jsp:param name="addrName" value="nativePlaceAddress" />
									<jsp:param name="addressTitle" value="${eisNativePlaceTitle}"/>
									<jsp:param name="addrLookupName" value="Permanent Address" />
									<jsp:param name="readOnly" value="Y" />	
									<jsp:param name="mandatory" value="Yes" />
									<jsp:param name="collapseAddress" value="false"/>						
								</jsp:include>
							</td>
							<td class="fieldLabel"  width="1%"></td>
						</tr>
					</table>
					</hdiits:fieldGroup>
				</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
		<br>
		<table width="100%" align="center" >	
			<tr align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
				
		<hdiits:fieldGroup id="permanentAddressDtlsFieldGroupId" titleCaptionId="PERMANENT_PLACE" bundle="${empEditListCommonLables}">
		<table width="100%" align="center">
			<tr align="center">			
				<td class="fieldLabel" > 
					<hdiits:radio name="radPermanentAddress" id="radPermanentAddress" value="Y" onclick="showHideAddress(2,'Y')" captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
					<hdiits:radio name="radPermanentAddress" id="radPermanentAddress" value="N" onclick="showHideAddress(2,'N')" captionid="eis.No" bundle="${empEditListCommonLables}"/>
					<b><fmt:message key="eis.SAME_AS_NATIVE_Address" bundle="${empEditListCommonLables}"></fmt:message></b>
				</td>
			</tr>
		</table>	

		<table width="100%" align="center" >	
			<tr id="row_personPermanentAddress" align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
					<hdiits:fmtMessage key="Address_Details" bundle="${empEditListCommonLables}" var="eisPermanentPlaceTitle" />	
					<jsp:include page="../../common/address.jsp">
						<jsp:param name="addrName" value="permanentPlaceAddress" />
						<jsp:param name="addressTitle" value="${eisPermanentPlaceTitle}" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Yes" />
						<jsp:param name="readOnly" value="Y" />	
						<jsp:param name="collapseAddress" value="false"/>
					</jsp:include>
				</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
		</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
		<br>
		<table width="100%" align="center" >	
			<tr align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
		<hdiits:fieldGroup id="currentAddressDtlsFieldGroupId" titleCaptionId="CURRENT_PLACE" bundle="${empEditListCommonLables}">
		<table width="100%" align="center">	
			<tr align="center">			
				<td class="fieldLabel"> 
					<hdiits:radio name="radCurrentAddress" id="radCurrentAddress" value="Y" onclick="showHideAddress(3,'Y')" captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
					<hdiits:radio name="radCurrentAddress" id="radCurrentAddress" value="N" onclick="showHideAddress(3,'N')" captionid="eis.No" bundle="${empEditListCommonLables}"/>
					<b><fmt:message key="eis.SAME_AS_PERMANENT_Address" bundle="${empEditListCommonLables}"></fmt:message></b>
				</td>
			</tr>
		</table>

		<table width="100%" align="center" >	
			<tr id="row_personCurrentAddress" align="left" width="100%">			
				<td class="fieldLabel"  width="1%"></td>
				<td class="fieldLabel" colspan="2">
					<hdiits:fmtMessage key="Address_Details" bundle="${empEditListCommonLables}" var="eisCurrentPlaceTitle" />		
					<jsp:include page="../../common/address.jsp">
							<jsp:param name="addrName" value="currentPlaceAddress" />
							<jsp:param name="addressTitle" value="${eisCurrentPlaceTitle}" />
							<jsp:param name="addrLookupName" value="Present Address" />
							<jsp:param name="mandatory" value="Yes" />
							<jsp:param name="readOnly" value="Y" />
							<jsp:param name="collapseAddress" value="false"/>
					</jsp:include>
				</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
		</hdiits:fieldGroup>
		</td>
				<td class="fieldLabel"  width="1%"></td>
			</tr>
		</table>
</div>
</hdiits:fieldGroup>
<br>

<hdiits:fieldGroup id="contactDtlsFieldGroupId" titleCaptionId="eis.EMP_CONTACT_DETAILS" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table width="100%">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.EMP_CONTACT_DETAILS"/></u></strong></font></td>
		</tr>-->
		<tr>
			<td align="center" colspan="10">
				<table border="0" class="fieldLabel" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse"  width="60%">
					<tr>
						<td class="fieldLabel" align="right"><b><hdiits:caption captionid="eis.EMP_CONTACT_TYPE" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
						<td class="fieldLabel" align="left">
							<hdiits:select name="emp_contact_type" id="emp_contact_type" captionid="eis.EMP_CONTACT_TYPE" bundle="${empEditListCommonLables}" mandatory="true">
								<hdiits:option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
									<c:forEach var="ContactTypeList" items="${ContactTypeList}">	    						    					
					   					<option value=<c:out value="${ContactTypeList.lookupName}"/>>
											<c:out value="${ContactTypeList.lookupDesc}"/></option>				
									</c:forEach>	
								</hdiits:select>
						 </td>
						<td class="fieldLabel" align="right" ><b><hdiits:caption captionid="eis.EMP_CONTACT" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
						<td class="fieldLabel" align="left">
							<hdiits:text name="emp_contact" id="emp_contact" captionid="eis.EMP_CONTACT" bundle="${empEditListCommonLables}" maxlength="45" mandatory="true"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td  colspan="10" align="center" class="fieldLabel">
				<hdiits:button type="button" name="addContact" captionid="EIS.Add" bundle="${empEditListCommonLables}" onclick="javascript:addContactNumber('AddContact')"/>
				<hdiits:button type="button" name="saveContact" captionid="EIS.Update" bundle="${empEditListCommonLables}" readonly="true" onclick="addContactNumber('saveContact')"/>
				<hdiits:button type="button" name="resetCont" captionid="EIS.Reset" bundle="${empEditListCommonLables}"  onclick="javascript:resetContact();"/>
			</td>
		</tr>

		<tr><td></td></tr>

		<tr>
				<td align="center" colspan="10">
				<table border="1" class="fieldLabel" name="multipleAddContactTable" id="multipleAddContactTable" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse" width="60%">
		
					<tr>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.EMP_CONTACT_TYPE" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.EMP_CONTACT" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.TABLE_ACTIONS" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
					</tr>			
				</table>
				<c:if test="${not empty contactArrObj}">
					<c:forEach items="${contactArrObj}" var="empContactTuples" varStatus="x">
						<c:set var="curXMLFileName1" value="${contactxmlFile[x.index]}"></c:set>
						<c:set var="contactType" value="${empContactTuples.cmnLookupMst.lookupDesc}" />
						<c:set var="contact" value="${empContactTuples.contactNumber}" />
						
						<script type="text/javascript">
							var contactXmlFileName = '${curXMLFileName1}';
							var displayContactFieldA  = new Array('${contactType}','${contact}');
							addDBDataInTable('multipleAddContactTable','addedcontactXML',displayContactFieldA,contactXmlFileName,'editDBContact', 'deleteDBContact');					
						</script>
					</c:forEach>
					</c:if>
				</td>	
		</tr>
	</table>
</hdiits:fieldGroup>
<br>
<!-- For Physical Details -->

<hdiits:fieldGroup id="PhysicalDtlsFieldGroupId" titleCaptionId="eis.Physical_Details" bundle="${empEditListCommonLables}" collapseOnLoad="true">
	<table width="100%">
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff" ><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.Physical_Details"/></u></strong></font>
			</td>
		</tr>-->
		<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Physically_Challenged" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>	
			<td class="fieldLabel">
				<hdiits:radio name="radPhyChallenged" id="radPhyChallenged" value="Y" onclick="phyChallenged('Y')" default="${editLst.hrPersonBiometricDtls.empPhyChallenged}" captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
				<hdiits:radio name="radPhyChallenged" id="radPhyChallenged" value="N" onclick="phyChallenged('N')" default="${editLst.hrPersonBiometricDtls.empPhyChallenged}" captionid="eis.No" bundle="${empEditListCommonLables}"/>
			</td>
		</tr>
	
		<tr id="emp_phyChallenged"  style="display:none">
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Type_Of_Disability" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td class="fieldLabel">
				<hdiits:select captionid="eis.Type_Of_Disability" bundle="${empEditListCommonLables}"	id="type_Of_Disability" name="type_Of_Disability" >
					<option value=""><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></option>
					<c:forEach var="disabilityList" items="${disabilityList}">
			    		<option value="<c:out value="${disabilityList.lookupName}"/>"><c:out value="${disabilityList.lookupDesc}"/></option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Details" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td class="fieldLabel" colspan="3">
				<hdiits:textarea
					id="empDisabilityDtls" cols="30" name="empDisabilityDtls" captionid="eis.Details" bundle="${empEditListCommonLables}"
					 maxlength="490" default="${editLst.hrPersonBiometricDtls.empDisabilityDetails}" >
				</hdiits:textarea>
			</td>
		</tr>		
			
		<tr>
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.BLOOD_GROUP" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td class="fieldLabel">
				<hdiits:select captionid="eis.BLOOD_GROUP" bundle="${empEditListCommonLables}" id="empBg" name="empBg" >
					<option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}" /></option>
					<c:forEach var="empBGList" items="${empBGList}">	    						    					
   					<option value=<c:out value="${empBGList.lookupName}"/>>
						<c:out value="${empBGList.lookupDesc}"/></option>				
				</c:forEach>	
				</hdiits:select>
			</td>						
			
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Identification_Mark" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			<td class="fieldLabel" colspan="1">
				<hdiits:textarea cols="30" id="empIdentificationMark" name="empIdentificationMark" validation="txt.isrequired" captionid="eis.Identification_Mark" bundle="${empEditListCommonLables}"
					 maxlength="490"  default="${editLst.hrPersonBiometricDtls.empIdentificationMark}">
				</hdiits:textarea>
			</td>			
		</tr>	
		<tr>
		
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.Weight" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			
			<td class="fieldLabel" >
				<hdiits:text name="empWeight" id="empWeight" onkeypress="return checkNumberForOnlyOneDot(this.value)"  captionid="eis.Weight" bundle="${empEditListCommonLables}" size="2" maxlength="6" default="${editLst.hrPersonBiometricDtls.empWeight}" style="text-align: right"></hdiits:text>
			</td>
			
			<td class="fieldLabel" ><b><hdiits:caption captionid="eis.Height" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			
			<td class="fieldLabel" >
				<hdiits:text name="empHeight" id="empHeight" onkeypress="return checkNumberForOnlyOneDot(this.value)" maxlength="6" captionid="eis.Height" bundle="${empEditListCommonLables}" size="2"  default="${editLst.hrPersonBiometricDtls.empHeight}" style="text-align: right"></hdiits:text>
			</td>
			
			<td class="fieldLabel"><b><hdiits:caption captionid="eis.Chest" bundle="${empEditListCommonLables}" ></hdiits:caption></b></td>
			
			<td class="fieldLabel" >
				<hdiits:text name="empChest" id="empChest"  onkeypress="return checkNumberForOnlyOneDot(this.value)"  maxlength="6" captionid="eis.Chest" bundle="${empEditListCommonLables}" size="2"   default="${editLst.hrPersonBiometricDtls.empChest}" style="text-align: right"></hdiits:text>
			</td>
		</tr>			
</table>
</hdiits:fieldGroup>
<br>
<table width="100%" align="center">
	<tr>
		<td colspan="10" align="center">
		<hdiits:button name="btnSubmit" type="button" captionid="EIS.Submit" bundle="${empEditListCommonLables}" onclick="saveEditDataForm()"></hdiits:button>
		<hdiits:button name="Close" type="button"  captionid="EIS.CloseButton" bundle="${empEditListCommonLables}" title="${closeToolTip}" onclick="window.close();" ></hdiits:button>  </td>  	     	
	</tr>
</table>

</div>
</div>
<hdiits:hidden name="srNo" id="hdnEmpContactId"></hdiits:hidden>
<hdiits:hidden name="srNoId" id="srNo"></hdiits:hidden>
<hdiits:hidden name="hdNativeAddress" id="hdNativeAddress"></hdiits:hidden>
<hdiits:hidden name="hdPermanentAddress" id="hdPermanentAddress"></hdiits:hidden>
<hdiits:hidden name="hdCurrentAddress" id="hdCurrentAddress"></hdiits:hidden>
<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
<hdiits:hidden name="hdnstrDepartmentName" id="hdnstrDepartmentName" default="${strDepartmentName}"></hdiits:hidden>


<hdiits:validate locale="${locale}" controlNames="emp_last_name,emp_first_name,Religion,Category,emp_mother_tongue,Marital_Status,EmpType,Nationality" /> 

</hdiits:form>
<script type="text/javascript">

	initializetabcontent("maintab");

/********Starts Address Show Script as same at time of papulating***********/	
	var checkNativeAsBirth=document.getElementsByName('radNativeAddress');

	if('${radNativeAddress}'=="Y")
	{
		checkNativeAsBirth[0].checked="true";
		document.getElementById('row_personNativeAddress').style.display='none';
	}
	else if ('${radNativeAddress}'=="N")
	{
		checkNativeAsBirth[1].checked="true";
		document.getElementById('row_personNativeAddress').style.display='';
	}
	else
	{
		checkNativeAsBirth[0].checked="true";
		document.getElementById('row_personNativeAddress').style.display='none';
	}
	
	var checkPermanentAsNative=document.getElementsByName('radPermanentAddress');
	if('${radPermanentAddress}'=="Y")
	{
		checkPermanentAsNative[0].checked="true";
		document.getElementById('row_personPermanentAddress').style.display='none';
	}
	else if('${radPermanentAddress}'=="N")
	{
		checkPermanentAsNative[1].checked="true";
		document.getElementById('row_personPermanentAddress').style.display='';
	}
	else
	{
		checkPermanentAsNative[0].checked="true";
		document.getElementById('row_personPermanentAddress').style.display='none';
	}
	
	
	var checkCurrentAsPermanent=document.getElementsByName('radCurrentAddress');
	if('${radCurrentAddress}'=="Y")
	{
		checkCurrentAsPermanent[0].checked="true";
		document.getElementById('row_personCurrentAddress').style.display='none';
	}
	else if ('${radCurrentAddress}'=="N")
	{
		checkCurrentAsPermanent[1].checked="true";
		document.getElementById('row_personCurrentAddress').style.display='';
	}
	
	else
	{
		checkCurrentAsPermanent[0].checked="true";
		document.getElementById('row_personCurrentAddress').style.display='none';
	}
/********Ends  of Address Show Script as same at time of papulating***********/

	phyChallenged('${editLst.hrPersonBiometricDtls.empPhyChallenged}');	
	document.frmBF.emp_dob.value='${empDob}';
	var motherTounge = "${editLst.cmnLanguageMst.lookupName}";//Change By Sunil For language
	if(motherTounge  !='' && motherTounge !=null && motherTounge !='null')
	document.frmBF.emp_mother_tongue.value = motherTounge;
	
	tempCmbCastName='${editLst.cmnLookupMstByEmpCasteId.lookupName}';
	tempCmbSubCastName='${editLst.cmnLookupMstByEmpSubCasteId.lookupName}';
	if(tempCmbCastName!='' || tempCmbCastName!=null || tempCmbCastName!='null')
	{
		getNextCmbValueOnTextForCast('${editLst.cmnLookupMstByEmpReligionId.lookupName}',1);

	}	
	
	var gender = "${editLst.empGender}";

	if(gender !='' && gender !=null && gender !='null')
	document.getElementById('gender').value = "${editLst.empGender}";
	
     var empType='${strLookupName}';
	if(empType!=null && empType!='null' && empType!='')
	{
			document.getElementById('EmpType').value = empType;
	}
	else document.getElementById('EmpType').value = 'Select';
   	
   	
	var typeOfDisability= "${editLst.hrPersonBiometricDtls.cmnLookupMstByEmpTypeOfDisability.lookupName}";

	if(typeOfDisability !='' && typeOfDisability !=null && typeOfDisability !='null')
	document.getElementById('type_Of_Disability').value=typeOfDisability;	

	var bloodGroup= "${editLst.hrPersonBiometricDtls.cmnLookupMstByEmpBloodGroup.lookupName}";

	if(bloodGroup!='' && bloodGroup!=null && bloodGroup!='null' && bloodGroup!='SELECT')		
	{
		document.getElementById('empBg').value=bloodGroup;
	}
	else
	{
		document.getElementById('empBg').value='Select';
	}	
	
	document.getElementById('emp_age').value='';
	
	countAge('${empDob}',"emp_age","value"); 
	
	var country='${editLst.cmnCountryMstByEmpNationality.countryCode}';
	if(country!='' && country!=null && country!='null')
	{
		document.frmBF.Nationality.value = country;
	}
	else
		document.frmBF.Nationality.value = "1";
		
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
