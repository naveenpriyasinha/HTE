
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<html>
<head>
	<script type="text/javascript" src="script/hrms/eis/eprofile.js"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/eis/empEditList.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/eis/Familyaddress.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/eis/showImage.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/hrms/eis/EISCommonFunction.js"/>"></script>
	<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>
</head>

<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="fmId" value="${resValue.fmId}" > </c:set>	
<c:set var="familyName" value="${resValue.familyName}" ></c:set>
<c:set var="xmlFile" value="${resValue.xmlFile}" ></c:set>
<c:set var="lArrObj" value="${resValue.lArrObj}" ></c:set>
<c:set var="hrEisEmpEmergencyContact" value="${resValue.hrEisEmpEmergencyContact}" ></c:set>
<c:set var="hrEisEmpMstDtls" value="${resValue.orgEmpMstObj}" ></c:set>  
<c:set var="editLst" value="${resValue.editList}" ></c:set>
<c:set var="empBGList" value="${resValue.empBG}"></c:set>
<c:set var="proficiencyList" value="${resValue.proficiencyList}"></c:set>
<c:set var="disabilityList" value="${resValue.disabilityList}"></c:set>
<c:set var="langList" value="${resValue.langList}"></c:set>
<c:set var="mpgSrNo" value="${resValue.mpgSrNo}"></c:set>
<c:set var="attchId" value="${resValue.attchId}"></c:set>
<c:set var="mpgSrNo1" value="${resValue.mpgSrNo1}"></c:set>
<c:set var="attchId1" value="${resValue.attchId1}"></c:set>
<c:set var="OtherAddressXmlFile" value="${resValue.OtherAddressXmlFile}" ></c:set>
<c:set var="strOtherLookupDesc" value="${resValue.strOtherLookupDesc}"></c:set>
<c:set var="empFlag" value="${resValue.empFlag}"></c:set>

<script type="text/javascript">	
	var alrtLangProf = '<fmt:message bundle="${empEditListCommonLables}" key="eis.langProf_Type"/>';
	var alrtWeight = '<fmt:message bundle="${empEditListCommonLables}" key="eis.dataWeightAlert"/>';
	var alrtHeight = '<fmt:message bundle="${empEditListCommonLables}" key="eis.dataHeightAlert"/>';
	var alrtChest = '<fmt:message bundle="${empEditListCommonLables}" key="eis.dataChestAlert"/>';
	var alrtLanguageType = '<fmt:message bundle="${empEditListCommonLables}" key="eis.LanguageType_alert"/>';
	var alrtLanguage = '<fmt:message bundle="${empEditListCommonLables}" key="eis.Language_alert"/>';
	var alrtMobileNoLength = '<fmt:message bundle="${empEditListCommonLables}" key="Eis.mobileNo"/>';
	var emergencyAddressAlert = '<fmt:message bundle="${empEditListCommonLables}" key="eis.emergencyAddress"/>'; //added by sandip
	var OtherAddressDtlsXmlFile='${OtherAddressXmlFile}';
	var globalflagForDBRecord='';
	var strAjaxKey='${resValue.ajaxKey}'; 
	var familyMemId='${fmId}';
	var emerResidencePhone='${hrEisEmpEmergencyContact.cmnContactMst.residencePhone}';
	var emerOfficePhone='${hrEisEmpEmergencyContact.cmnContactMst.officePhone}';
	var emerMobileNo='${hrEisEmpEmergencyContact.cmnContactMst.mobile}';
	var emerEmail='${hrEisEmpEmergencyContact.cmnContactMst.email}';
	var emerFaxNo='${hrEisEmpEmergencyContact.cmnContactMst.fax}';
	var otherFName='${hrEisEmpEmergencyContact.contactFirstName}';
	var otherMName='${hrEisEmpEmergencyContact.contactMiddleName}';
	var otherLName='${hrEisEmpEmergencyContact.contactLastName}';
	var otherEmerRelation='${hrEisEmpEmergencyContact.otherRelation}';
</script>

<body>
<hdiits:form name="frmBF" validate="true" method="POST" action="javascript:validateForm()" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EIS.PersonalDtls" bundle="${empEditListCommonLables}"></fmt:message></b></a></li>
			<li><a href="#" rel="tcontent2"><b><fmt:message key="Address_Details" bundle="${empEditListCommonLables}"></fmt:message></b></a></li>
			<li><a href="#" rel="tcontent3"><b><fmt:message key="EIS.otherDetails" bundle="${empEditListCommonLables}"></fmt:message></b></a></li>
		</ul>
	</div>
	
<div id="empEdit" name="empEdit"> 
	<div id="tcontent1" class="tabcontent" tabno="0" >
		<table width="100%" class="tabtable">
			<tr>
				<td class="fieldLabel" width="25%">
					<b><hdiits:caption captionid="eis.NAME" bundle="${empEditListCommonLables}" ></hdiits:caption></b>
				</td>
				<td width="1%"><b>:</b></td>
				<td width="24%">${hrEisEmpMstDtls.empPrefix} ${hrEisEmpMstDtls.empFname} ${hrEisEmpMstDtls.empMname} ${hrEisEmpMstDtls.empLname}</td>
				<td class="fieldLabel" width="25%"></td>
				<td class="fieldLabel" width="1%"></td>
				<td class="fieldLabel" width="24%"></td>
			</tr>
		</table>
		
		<table class="tabtable" >
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_GENDER" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%"><label id="gender"></label></td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_MARITAL_STATUS" bundle="${empEditListCommonLables}"/></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%"> 
					<c:if test="${not empty editLst.cmnLookupMstByEmpMaritalStatusId}">
						<c:out value="${editLst.cmnLookupMstByEmpMaritalStatusId.lookupDesc}" />
					</c:if>
					<c:if test="${empty editLst.cmnLookupMstByEmpMaritalStatusId}">
						-
					</c:if>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_DATE_OF_BIRTH" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%">
				<fmt:formatDate var="empDob" value="${hrEisEmpMstDtls.empDob}" pattern="dd/MM/yyyy"/>
				<c:out value="${empDob}"></c:out>
			</td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_AGE" bundle="${empEditListCommonLables}"/></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%"><label id="emp_age"></label></td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_CATEGORY" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%">
				<c:if test="${not empty editLst.cmnLookupMstByEmpCategoryId and editLst.cmnLookupMstByEmpCategoryId.lookupName != 'SELECT'}">
					<c:out value="${editLst.cmnLookupMstByEmpCategoryId.lookupDesc}" />
				</c:if>
				<c:if test="${empty editLst.cmnLookupMstByEmpCategoryId or editLst.cmnLookupMstByEmpCategoryId.lookupName == 'SELECT'}">
					-
				</c:if>
			</td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_RELIGION" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%"> 
					<c:if test="${not empty editLst.cmnLookupMstByEmpReligionId}">
						<c:out value="${editLst.cmnLookupMstByEmpReligionId.lookupDesc}" />
					</c:if>
					<c:if test="${empty editLst.cmnLookupMstByEmpReligionId}">
						-
					</c:if>
			</td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_CASTE" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<c:if test="${not empty editLst.cmnLookupMstByEmpCasteId and editLst.cmnLookupMstByEmpCasteId.lookupName!='SELECT'}">
					<td class="fieldLabel" width="24%">${editLst.cmnLookupMstByEmpCasteId.lookupDesc}</td>
			</c:if>
			<c:if test="${empty editLst.cmnLookupMstByEmpCasteId or editLst.cmnLookupMstByEmpCasteId.lookupName=='SELECT' }">
				<td class="fieldLabel" width="24%">-</td>	
			</c:if>
			<!-- Added By Sunil for Language-->
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_SUBCASTE" bundle="${empEditListCommonLables}"/></td>
			<td width="1%"><b>:</b></td>
			<c:if test="${not empty editLst.cmnLookupMstByEmpSubCasteId and editLst.cmnLookupMstByEmpSubCasteId.lookupName!='SELECT'}">
				<td class="fieldLabel" width="24%">${editLst.cmnLookupMstByEmpSubCasteId.lookupDesc}</td>
			</c:if>
			<c:if test="${empty editLst.cmnLookupMstByEmpSubCasteId or editLst.cmnLookupMstByEmpSubCasteId.lookupName=='SELECT'}">
				<td class="fieldLabel" width="24%">-</td>
			</c:if>
			
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.EMP_MOTHER_TONGUE" bundle="${empEditListCommonLables}" /></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%">
				<c:if test="${not empty editLst.cmnLanguageMst and editLst.cmnLanguageMst.lookupName!='SELECT'}">
						<c:out value="${editLst.cmnLanguageMst.lookupDesc}" />
				</c:if>
				<c:if test="${empty editLst.cmnLanguageMst or editLst.cmnLanguageMst.lookupName=='SELECT'}">
						-
				</c:if>
				
			</td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="eis.Nationality" bundle="${empEditListCommonLables}"/></td>
			<td width="1%"><b>:</b></td>
			<td class="fieldLabel" width="24%"> 
					<c:if test="${not empty editLst.cmnCountryMstByEmpNationality}">
						<c:out value="${editLst.cmnCountryMstByEmpNationality.countryName}" />
					</c:if>
					<c:if test="${empty editLst.cmnCountryMstByEmpNationality}">
						-
					</c:if>
			</td>
		</tr>
		</table>

		<table class="tabtable">			
			<tr>	
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="EIS.PHOTO" bundle="${empEditListCommonLables}"/></b></td>
					<td class="fieldLabel" align="left" width="25%" id="photo"><img width="100px" id="myImage" name="myImage" onmouseover="showtrail('myImage_div','',200,150,'${attchId}','${mpgSrNo}');"  onmouseout="hidetrail();"></td>
					<td class="fieldLabel" width="25%">	<b><hdiits:caption captionid="EIS.THUMB" bundle="${empEditListCommonLables}"/></b></td>
					<td class="fieldLabel" align="left" width="25%" id="thumb"><img width="100px" id="myImage_thumb" name="myImage_thumb" onmouseover="showtrail('myImage_div','',200,150,'${attchId1}','${mpgSrNo1}');"  onmouseout="hidetrail();"></td>
					<td><div style="display: none; position: absolute; z-index:110; " id="preview_div"></div></td>
			</tr>
		</table>	
		
		<hdiits:fieldGroup titleCaptionId="eis.EMP_CONTACT_DETAILS" bundle="${empEditListCommonLables}">
			<table class="tabtable" >	
				<tr>
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.EMP_PHONE_NO" bundle="${empEditListCommonLables}"/></b></td>	
					<td width="1%"><b>:</b></td>
					<c:if test="${resValue.phone!=''}">
						<td class="fieldLabel" width="24%">${resValue.phone}</td>
					</c:if>
					<c:if test="${resValue.phone==''}">
						<td class="fieldLabel" width="24%">-</td>
					</c:if>
					
					<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}"/></b></td>
					<td width="1%"><b>:</b></td>
					<c:if test="${resValue.mobile!=''}">
						<td class="fieldLabel" width="24%">${resValue.mobile}</td>
					</c:if>
					<c:if test="${resValue.mobile==''}">
						<td class="fieldLabel" width="24%">-</td>
					</c:if>
				</tr>
				<tr>	
					<td class="fieldLabel"  width="25%"><b><hdiits:caption captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}"/></b></td>
					<td width="1%"><b>:</b></td>
					<c:if test="${resValue.email!=''}">
						<td class="fieldLabel" width="24%">${resValue.email}</td>
					</c:if>
					<c:if test="${resValue.email==''}">
						<td class="fieldLabel" width="24%">-</td>
					</c:if>
				</tr>
			</table>
		</hdiits:fieldGroup>
		
	</div>
	<div id="tcontent2" class="tabcontent" tabno="1">
		<!-- <table class="tabtable" >	
		<tr bgcolor="#386CB7" align="center">
				<td  class="fieldLabel" colspan="10"><font color="#ffffff">
				<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.BIRTH_PLACE"/></u></strong></font></td>	
		</tr>
		</table> -->
		<table class="tabtable">	
		<tr>			
			<td class="fieldLabel"  width="5%"></td>
			<td class="fieldLabel" colspan="2">
				<hdiits:fmtMessage key="eis.BIRTH_PLACE" bundle="${empEditListCommonLables}" var="eisBirthPlaceLabel" ></hdiits:fmtMessage>
				<jsp:include page="../../common/viewAddress.jsp">
					<jsp:param name="addrName" value="birthPlaceAddress" />
					<jsp:param name="addressTitle" value="${eisBirthPlaceLabel}" />
					<jsp:param name="collapseAddress" value="true"/>
				</jsp:include>
			</td>
			<td class="fieldLabel"  width="5%"></td>
		</tr>
		</table>
		<!--<table class="tabtable" >	
			 <tr bgcolor="#386CB7"> 
				<td  class="fieldLabel" colspan="10" align="center">
					<font color="#ffffff">
					<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.NATIVE_PLACE"/></u></strong></font>
				</td>			
			</tr>	
		</table>-->
		
		<table class="tabtable" >						
			<tr>	
					<td class="fieldLabel" width="5%"></td>	
					<td class="fieldLabel" colspan="2"> 
						<hdiits:fmtMessage key="eis.NATIVE_PLACE" bundle="${empEditListCommonLables}" var="eisNativePlaceLabel" ></hdiits:fmtMessage>
						<jsp:include page="../../common/viewAddress.jsp">
							<jsp:param name="addrName" value="nativePlaceAddress" />
							<jsp:param name="addressTitle" value="${eisNativePlaceLabel}" />
							<jsp:param name="collapseAddress" value="true"/>
						</jsp:include>
					</td>		
					<td class="fieldLabel" width="5%"></td>
		</tr>
		</table>
		
		<!--<table class="tabtable" >	
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center">
				<font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="PERMANENT_PLACE"/></u></strong></font>
			</td>
		</tr>
		</table>-->
		
		<table class="tabtable" >						
			<tr id="row_personPermanentAddress">		
					<td class="fieldLabel" width="5%"></td>
					<td class="fieldLabel" colspan="2"> 
						<hdiits:fmtMessage key="PERMANENT_PLACE" bundle="${empEditListCommonLables}" var="eisPermanentLabel" ></hdiits:fmtMessage>
						<jsp:include page="../../common/viewAddress.jsp">
							<jsp:param name="addrName" value="personPermanentAddress" />
							<jsp:param name="addressTitle" value="${eisPermanentLabel}" />
						</jsp:include>
					</td>		
					<td class="fieldLabel" width="5%"></td>
		</tr>
		</table>
		
		<!--<table class="tabtable" >	
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="10" align="center">
					<font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="CURRENT_PLACE"/></u></strong></font>
				</td>
			</tr>
		</table>-->
		
		<table class="tabtable" >						
			<tr id="row_personCurrentAddress">		
					<td class="fieldLabel" width="5%"></td>
					<td class="fieldLabel" colspan="2"> 
						<hdiits:fmtMessage key="CURRENT_PLACE" bundle="${empEditListCommonLables}" var="eisCurrentLabel" ></hdiits:fmtMessage>
						<jsp:include page="../../common/viewAddress.jsp">
							<jsp:param name="addrName" value="personCurrentAddress" />
							<jsp:param name="addressTitle" value="${eisCurrentLabel}" />
						</jsp:include>
					</td>		
					<td class="fieldLabel" width="5%"></td>
		</tr>
		</table>
		
	<!--</div>  -->
	</div>
	<div id="tcontent3" class="tabcontent" tabno="2">
	<table class="tabtable" width="100%" style="display:none" id="errorMessage">
		<tr>
			<td width="35%"></td>
			<td class="fieldLabel" width="40%" colspan="4">
				<hdiits:text name="errorBox" id="errorBox" readonly="readonly" style="color: #E41B17;width:100%;border:0; font-family: Verdana; font-weight: bold; font-size: 12px; "></hdiits:text>
			</td>
			<td width="25%"></td>
		</tr>
	</table>

<hdiits:fieldGroup id="emp_lang_dtl_id" titleCaptionId="eis.Language_Details" bundle="${empEditListCommonLables}">
	<table class="tabtable">
		<tr align="center">
			<td class="fieldLabel" align="right" width="25%">
				<b><hdiits:caption captionid="eis.Language" bundle="${empEditListCommonLables}"/></b>
			</td><!-- Added by Sunil For Language  -->
			<td class="fieldLabel" align="center" width="25%">
				<hdiits:select name="emp_lang_known" id="emp_lang_known" captionid="eis.Language" bundle="${empEditListCommonLables}" validation ="sel.isrequired" mandatory="true" sort="false">
				<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
					<c:forEach var="langProfList" items="${langList}">	    						    					
   					<option value=<c:out value="${langProfList.lookupName}"/>>
						<c:out value="${langProfList.lookupDesc}"/></option>				
				</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" align="center"  width="20%">
				<b><hdiits:caption captionid="EIS.PROFICIENCY" bundle="${empEditListCommonLables}"/></b>
			</td>
			
			<td class="fieldLabel" align="left" width="30%">
				<hdiits:select name="emp_proficiency" captionid="EIS.PROFICIENCY" bundle="${empEditListCommonLables}" validation ="sel.isrequired" mandatory="true" sort="true">
					<hdiits:option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></hdiits:option>
					<c:forEach var="proficiencyList" items="${proficiencyList}">	    						    					
	   					<option value=<c:out value="${proficiencyList.lookupName}"/>>
							<c:out value="${proficiencyList.lookupDesc}"/></option>				
					</c:forEach>	
				</hdiits:select>
			</td>	
		</tr>
		<tr></tr><tr></tr><tr></tr>
		<tr align="center" >
			<td class="fieldLabel" colspan="10" align="center">
				<hdiits:button type="button" captionid="EIS.Add" bundle="${empEditListCommonLables}" name="addLanguage" onclick="addLangauge('Add')"/>
				<hdiits:button type="button" captionid="EIS.Update" bundle="${empEditListCommonLables}" name="saveLanguage"  readonly="true" onclick="addLangauge('Save')"/>
				<hdiits:button type="button" captionid="EIS.Reset" bundle="${empEditListCommonLables}" name="reset" onclick="javascript:resetLanguage();"/>
			</td>
		</tr>
		<tr></tr><tr></tr><tr></tr>
		<tr>
			<td align="center" colspan="10">
				<table border="1" class="fieldLabel" name="multipleAddLangTable" border=1 borderColor="black" id="multipleAddLangTable" style="display:none" width="50%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;display:none;">
					<tr>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.Language" bundle="${empEditListCommonLables}"/></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="EIS.PROFICIENCY" bundle="${empEditListCommonLables}"/></b></td>
						<td class="fieldLabel" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="eis.TABLE_ACTIONS" bundle="${empEditListCommonLables}"/></b></td>	
					</tr>			
				</table>
				<c:if test="${not empty lArrObj}">
					<c:forEach items="${lArrObj}" var="empLanguageTuples" varStatus="x">
						<c:set var="curXMLFileName" value="${xmlFile[x.index]}"></c:set>
						<c:set var="langName" value="${empLanguageTuples.cmnLanguageMstByLanguageId.lookupDesc}" /><!-- Added by Sunil For Language  -->
						<c:set var="proficiency" value="${empLanguageTuples.cmnLookupMst.lookupDesc}" />
						<script type="text/javascript">
							var xmlFileName = '${curXMLFileName}';
							var displayFieldA  = new Array('${langName}','${proficiency}');/* Added by Sunil For Language  */
							addDBDataInTable('multipleAddLangTable','addedlangXML',displayFieldA,xmlFileName,'editDBLanguage', 'deleteDBLanguage','');					
						</script>
					</c:forEach>
				</c:if>
			</td>
		</tr>
	</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup id="emp_physical_dtl_id" titleCaptionId="eis.Physical_Details" bundle="${empEditListCommonLables}" mandatory="true">
	<table class="tabtable" border="0" bordercolor="black" width="100%">
			<tr>
				<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.Physically_Challenged" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="20%">
					<hdiits:radio name="radPhyChallenged" id="radPhyChallenged" value="Y" onclick="phyChallenged('Y')" default="${editLst.hrPersonBiometricDtls.empPhyChallenged}"  validation="sel.isradio" captionid="eis.Yes" bundle="${empEditListCommonLables}" />&nbsp;&nbsp;
					<hdiits:radio name="radPhyChallenged" id="radPhyChallenged" value="N" onclick="phyChallenged('N')" default="${editLst.hrPersonBiometricDtls.empPhyChallenged}"  validation="sel.isradio" captionid="eis.No" bundle="${empEditListCommonLables}"/>
				</td>
				<td class="fieldLabel"></td>
				<td class="fieldLabel"></td>
			</tr>
	
			 <tr id="emp_phyChallenged"  style="display:none">
				<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.Type_Of_Disability" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="20%">
					<hdiits:select captionid="eis.Type_Of_Disability" bundle="${empEditListCommonLables}"
						id="type_Of_Disability" name="type_Of_Disability" mandatory="true"  sort="false"
						validation="sel.isrequired">
					<option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></option>
					<c:forEach var="disability" items="${disabilityList}">
			    			<option value="<c:out value="${disability.lookupName}"/>">						
						<c:out value="${disability.lookupDesc}"/></option>
					</c:forEach>
					</hdiits:select>
				</td>
				<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="eis.Details" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="49%">
					<hdiits:textarea
						id="empDisabilityDtls" name="empDisabilityDtls" cols="50"
						validation="txt.isrequired" captionid="eis.Details" bundle="${empEditListCommonLables}"
						mandatory="true" maxlength="490"  default="${editLst.hrPersonBiometricDtls.empDisabilityDetails}" >
					</hdiits:textarea>
				</td>
			</tr>
					
			<tr>
				<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.BLOOD_GROUP" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="20%">
					<hdiits:select captionid="eis.BLOOD_GROUP" bundle="${empEditListCommonLables}" id="empBg" name="empBg" mandatory="true" validation="sel.isrequired">
						<option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></option>
						<c:forEach var="empBG" items="${empBGList}">
		    					<c:choose>
			    					<c:when test="${editLst.hrPersonBiometricDtls.cmnLookupMstByEmpBloodGroup.lookupName==empBG.lookupName}">
			    						<option value=<c:out value="${empBG.lookupName}" /> selected="true">
										<c:out value="${empBG.lookupDesc}"/></option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value=<c:out value="${empBG.lookupName}"/>>
										<c:out value="${empBG.lookupDesc}"/></option>
			    					</c:otherwise>
		    					</c:choose>		    					
						</c:forEach>
					</hdiits:select>
				</td>						
				<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="eis.Identification_Mark" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="49%">
					<hdiits:textarea cols="50" id="empIdentificationMark" name="empIdentificationMark" validation="txt.isrequired" captionid="eis.Identification_Mark" bundle="${empEditListCommonLables}"
						mandatory="true" maxlength="490"  default="${editLst.hrPersonBiometricDtls.empIdentificationMark}">
					</hdiits:textarea>
				</td>	
			</tr>	
	</table>
		
	<table class="tabtable" border="0" bordercolor="black" width="100%">
			<tr>
				<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.Weight" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="20%">
					<hdiits:text name="empWeight" style="text-align: right" id="empWeight" mandatory="true" maxlength="6" size="9" captionid="eis.Weight" bundle="${empEditListCommonLables}" validation="txt.isrequired,txt.isflt"  default="${editLst.hrPersonBiometricDtls.empWeight}" onkeypress="return checkNumberForOnlyOneDot(this.value);"/>
				</td>
				<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="eis.Height" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel" width="20%">
					<hdiits:text name="empHeight" style="text-align: right" id="empHeight" mandatory="true" maxlength="6" size="10" captionid="eis.Height" bundle="${empEditListCommonLables}" validation="txt.isrequired,txt.isflt"  default="${editLst.hrPersonBiometricDtls.empHeight}" onkeypress="return checkNumberForOnlyOneDot(this.value);"/>
				</td>
				<td class="fieldLabel" width="11%"><b><hdiits:caption captionid="eis.Chest" bundle="${empEditListCommonLables}"/></b></td>
				<td class="fieldLabel">
					<hdiits:text name="empChest" style="text-align: right" id="empChest" mandatory="true" maxlength="6" size="10" captionid="eis.Chest" bundle="${empEditListCommonLables}" validation="txt.isrequired,txt.isflt"  default="${editLst.hrPersonBiometricDtls.empChest}" onkeypress="return checkNumberForOnlyOneDot(this.value);"/>
				</td>
			</tr>	
	</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup id="emp_emrgncy_cntct_dtl_id" titleCaptionId="EIS.EMR_CONT_DTLS" bundle="${empEditListCommonLables}" collapseOnLoad="true" mandatory="true">
	<table class="tabtable" border="0" bordercolor="black">
	<tr>
		<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="EIS.EMR_CONT_TYPE" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="40%" align="left">
			<hdiits:radio name="radEmergencyDtls" id="radEmergencyDtls" value="O" onclick="showEmergencyDtls('O')"  default="${hrEisEmpEmergencyContact.contactType}"  validation="sel.isradio" captionid="eis.other" bundle="${empEditListCommonLables}"/>&nbsp;&nbsp;
			<hdiits:radio name="radEmergencyDtls" id="radEmergencyDtls" value="F" onclick="showEmergencyDtls('F')"  default="${hrEisEmpEmergencyContact.contactType}"  validation="sel.isradio" captionid="EIS.FAMILY_MEM" bundle="${empEditListCommonLables}"/>
		</td>
		<td></td>
	</tr>
	</table>
	
	<table class="tabtable" id="otherEmergency" style="display:none" border="0" bordercolor="black">
	<tr>
		<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherPersonLName" validation="txt.isrequired" id="otherPersonLName" mandatory="true" captionid="eis.LAST_NAME" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.contactLastName}" maxlength="45"></hdiits:text></td>
		<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherPersonFName" validation="txt.isrequired" id="otherPersonFName" mandatory="true" captionid="eis.EN_FIRST_NAME" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.contactFirstName}" maxlength="45"></hdiits:text></td>
		<td class="fieldLabel" width="11%"><b><hdiits:caption captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel"><hdiits:text name="otherPersonMName" id="otherPersonMName" captionid="eis.EN_MIDDLE_NAME" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.contactMiddleName}" maxlength="45"></hdiits:text></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.Relation" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%">
			<hdiits:text captionid="eis.Relation" bundle="${empEditListCommonLables}" id="otherEmergencyRelation" name="otherEmergencyRelation" readonly="true"/>
		</td>
		<td class="fieldLabel"  style="display:none" id="other_Relation" width="14%"><b><hdiits:caption captionid="eis.otherRelation" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" style="display:none" id="other_Relation_text" width="20%"><hdiits:text name="otherRelation" id="otherRelation" validation="txt.isrequired" mandatory="true" captionid="eis.otherRelation" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.otherRelation}" ></hdiits:text></td>	
		<td class="fieldLabel" width="11%"></td>
		<td class="fieldLabel"></td>
	</tr>
	</table>
	
	<table class="tabtable" id="familyEmergency" style="display:none" width="100%" border="0" bordercolor="black">
		<tr>
			<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.person_name" bundle="${empEditListCommonLables}"/></b></td>
			<td class="fieldLabel" width="20%">
				<hdiits:select captionid="eis.person_name" bundle="${empEditListCommonLables}" id="familyPersonName" name="familyPersonName" mandatory="true"
					validation="sel.isrequired" onchange="getFamilyInfo(this);">
				<option value="Select"><fmt:message key="EIS.Select" bundle="${empEditListCommonLables}"/></option>
	
				<c:forEach var="familyPersonName" items="${familyName}">					
		    			<c:choose>
			    			<c:when test="${familyPersonName.memberId==fmId}">						
			    				<option value=<c:out value="${familyPersonName.memberId}" /> selected="true">
								<c:out value="${familyPersonName.fmFirstName}"/>&nbsp;<c:out value="${familyPersonName.fmLastName}"/></option>
			    			</c:when>
			    			<c:otherwise>
			    			<option value=<c:out value="${familyPersonName.memberId}"/>>
							<c:out value="${familyPersonName.fmFirstName}"/>&nbsp;<c:out value="${familyPersonName.fmLastName}"/></option>
			    			</c:otherwise>
	  				</c:choose>	    				
				</c:forEach>
				</hdiits:select>
			</td>
			<td class="fieldLabel" width="14%" ><b><hdiits:caption captionid="eis.Relation" bundle="${empEditListCommonLables}"/></b></td>
			<td class="fieldLabel" width="18%">
			<hdiits:text captionid="eis.Relation" bundle="${empEditListCommonLables}" readonly="true" id="familyEmergencyRelation" name="familyEmergencyRelation" />
			</td>
			<td>
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td style="display:none" id="family_Relation" align="center" width="25%"><b><hdiits:caption captionid="eis.otherRelation" bundle="${empEditListCommonLables}"/></b> </td>
						<td style="display:none" id="family_Relation_text" align="center" width="40%"><hdiits:text readonly="true" name="familyRelation" id="familyRelation" mandatory="true" captionid="eis.otherRelation" bundle="${empEditListCommonLables}" validation="txt.isrequired"></hdiits:text></td>				
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table class="tabtable" width="100%" border="0" bordercolor="black">
	<tr>
		<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="EIS.RESI_PHONE" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherEmergencyResidencePhone" id="otherEmergencyResidencePhone" style="text-align: right" maxlength="10" onkeypress="return checkDecimalNumber()" captionid="EIS.RESI_PHONE" bundle="${empEditListCommonLables}" validation="txt.isnumber"  default="${hrEisEmpEmergencyContact.cmnContactMst.residencePhone}" /></td>
		
		<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="EIS.OFFICE_PHONE" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherEmergencyOfficePhone" id="otherEmergencyOfficePhone" style="text-align: right" maxlength="10" onkeypress="return checkDecimalNumber()" captionid="EIS.OFFICE_PHONE" bundle="${empEditListCommonLables}" validation="txt.isnumber" default="${hrEisEmpEmergencyContact.cmnContactMst.officePhone}"/></td>
		
		<td class="fieldLabel" width="11%"><b> <hdiits:caption captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel"><hdiits:text name="otherEmergencyMobile" id="otherEmergencyMobile" maxlength="10" mandatory="true" style="text-align: right" onkeypress="return checkDecimalNumber()" captionid="eis.EMP_MOBILE_NO" bundle="${empEditListCommonLables}" validation="txt.isrequired,txt.isnumber" default="${hrEisEmpEmergencyContact.cmnContactMst.mobile}" /></td>
	
	</tr>
	<tr>
		<td class="fieldLabel" width="17%"><b><hdiits:caption captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherEmergencyEmail" id="otherEmergencyEmail" captionid="eis.EMP_EMAIL" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.cmnContactMst.email}" maxlength="30" validation="txt.email"></hdiits:text></td>
		<td class="fieldLabel" width="14%"><b><hdiits:caption captionid="EIS.FAX" bundle="${empEditListCommonLables}"/></b></td>
		<td class="fieldLabel" width="20%"><hdiits:text name="otherEmergencyFax" style="text-align: right" validation="txt.isnumber" id="otherEmergencyFax" maxlength="10" onkeypress="return checkDecimalNumber()" captionid="EIS.FAX" bundle="${empEditListCommonLables}" default="${hrEisEmpEmergencyContact.cmnContactMst.fax}"/></td>
		<td class="fieldLabel" width="11%"><b></b></td>
		<td class="fieldLabel" ></td>	
	</tr>
	</table>
	
	<table class="tabtable" >						
		<tr>		
			<td class="fieldLabel" width="5%"></td>
				<td class="fieldLabel" colspan="2"> 
				<hdiits:fmtMessage key="Address_Details" bundle="${empEditListCommonLables}" var="eisEmergancyAddress" ></hdiits:fmtMessage>
				<jsp:include page="../../common/address.jsp">
					<jsp:param name="addrName" value="otherEmergencyAddress" />
					<jsp:param name="addressTitle" value="${eisEmergancyAddress}" />
					<jsp:param name="addrLookupName" value="Permanent Address" />
					<jsp:param name="mandatory" value="Yes" />	
				</jsp:include> 
			</td>		
			<td class="fieldLabel" width="5%"></td>
		</tr>
	</table>
</hdiits:fieldGroup>

<br/>

<center>
		<hdiits:button name="btnSubmit" type="button" captionid="EIS.Submit" bundle="${empEditListCommonLables}" onclick="validateForm()"></hdiits:button>	
		<hdiits:button name="btnEmpProfDtlsAddCancel" type="button"
			captionid="EIS.CloseButton" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
</center>
<hdiits:hidden name="srNo" id="srNo"></hdiits:hidden>

<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
</div>
</div>
</hdiits:form>
</BODY>

<script type="text/javascript">
		initializetabcontent("maintab");
</script>
<script>	
	if('${attchId}'!='')
	{
		document.getElementById('myImage').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${attchId}'+"&attachmentSerialNumber="+'${mpgSrNo}';
		document.getElementById('photo').style.display='';
	}
	else
		document.getElementById('photo').innerHTML = "<fmt:message key="eis.NotAvailable" bundle="${empEditListCommonLables}"/>";
		
	if('${attchId1}'!='')		
	{
		document.getElementById('myImage_thumb').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+'${attchId1}'+"&attachmentSerialNumber="+'${mpgSrNo1}';
		document.getElementById('thumb').style.display='';
	}
	else
		document.getElementById('thumb').innerHTML = "<fmt:message key="eis.NotAvailable" bundle="${empEditListCommonLables}"/>";

	var gender = "${editLst.empGender}";
	if (gender == 'M')
		document.getElementById("gender").innerHTML = "<fmt:message key="eis.EMP_GENDER_MALE" bundle="${empEditListCommonLables}"/>";
	else if (gender == 'F')
		document.getElementById("gender").innerHTML = "<fmt:message key="eis.EMP_GENDER_FEMALE" bundle="${empEditListCommonLables}"/>";
	else 	
		document.getElementById("gender").innerHTML = "-";
	
	
	if ('${editLst.hrPersonBiometricDtls.empPhyChallenged}' == '')
	{
		document.frmBF.radPhyChallenged[1].checked=true;
	}
	else
	{
		phyChallenged('${editLst.hrPersonBiometricDtls.empPhyChallenged}');	
	}
	
	countAge('${empDob}',"emp_age","innerHTML");
		
	tmep='${editLst.hrPersonBiometricDtls.cmnLookupMstByEmpTypeOfDisability.lookupName}';
	if(tmep!='' && tmep!=null && tmep!='null')
	{
		document.getElementById('type_Of_Disability').value=tmep;	
	}
	
	tmep='${editLst.hrPersonBiometricDtls.cmnLookupMstByEmpBloodGroup.lookupName}';	
	if(tmep!='' && tmep!=null && tmep!='null' && tmep!='SELECT')		
	{
		document.getElementById('empBg').value=tmep;
	}
	else
	{
		document.getElementById('empBg').value='Select';
	}	
	
	
	var DbContactType='${hrEisEmpEmergencyContact.contactType}';			
	showEmergencyDtls('${hrEisEmpEmergencyContact.contactType}');
	
	var tmep='${hrEisEmpEmergencyContact.cmnLookupMst.lookupDesc}';	
	if(tmep!='' && tmep!=null && tmep!='null')		
	{
		document.getElementById('otherEmergencyRelation').value=tmep;	
	}
	
	if('${hrEisEmpEmergencyContact.contactType}'=='O')
	{
		OtherRelation(document.getElementById('otherEmergencyRelation'),1);
	}
	else if('${hrEisEmpEmergencyContact.contactType}'=='F')
	{	
		OtherRelation(document.getElementById('familyEmergencyRelation'),2);
	}
	else
	{
		document.frmBF.radEmergencyDtls[1].checked=true;
		makeEnableDisable('otherEmergencyAddress',0);
		document.getElementById('familyEmergency').style.display='';
		document.getElementById('family_Relation_text').value='';
		document.getElementById('otherEmergency').style.display='none';
		document.getElementById('family_Relation').style.display='none';
		document.getElementById('family_Relation_text').style.display='none';	
		OtherRelation(document.getElementById('familyEmergencyRelation'),2);
	}
		
	if (document.frmBF.otherEmergencyResidencePhone.value=='0')document.frmBF.otherEmergencyResidencePhone.value='';
	if (document.frmBF.otherEmergencyOfficePhone.value=='0')document.frmBF.otherEmergencyOfficePhone.value='';
	if (document.frmBF.otherEmergencyFax.value=='0')document.frmBF.otherEmergencyFax.value='';	
	
	var otherLookupDesc='${strOtherLookupDesc}';
	
	var empFlag='${empFlag}';
	var str='<fmt:message bundle="${empEditListCommonLables}" key="EIS.empMstAlert"/>';
	if(!empFlag || empFlag=='' )
	{
		document.getElementById('errorMessage').style.display='';				
		document.getElementById('errorBox').value=str;
		document.frmBF.btnSubmit.disabled=true;	
	}
	
</script>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>