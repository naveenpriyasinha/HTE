<%
try
{ %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<fmt:setBundle basename="resources.trng.TrainerMstLables" var="TrainerLabels"
	scope="request" />
 <script type="text/javascript" src="script/hrms/training/trainerMst.js"></script>


<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
	
	<c:set var="resultObj" value="${result}" > </c:set>
	<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
	
	<c:set var="firstTimeFlag" value="${resValue.firstTimeFlag}" scope="session"></c:set>
	<c:set var="readOnly" value="${resValue.readOnly}"></c:set>
	<c:set var="hrTrainerMst" value="${resValue.hrTrainerMst}"></c:set>
	<c:set var="editFlag" value="${resValue.editFlag}"></c:set>
	
	<c:set var="empMst" value="${resValue.empMst}"></c:set>
	<c:set var="orgUserpostRlt" value="${resValue.orgUserpostRlt}"></c:set>
	<c:set var="InHouseSelection" value="${resValue.InHouseSelection}"></c:set>
	<c:set var="orgPostMst" value="${resValue.orgPostMst}"></c:set>
	<c:set var="isInHouseTrainerPresent" value="${resValue.isInHouseTrainerPresent}"></c:set>
	<c:set var="SelectedTrainer" value="${resValue.SelectedTrainer}"></c:set>
	<c:set var="trainerFlag" value="${resValue.trainerFlag}"></c:set>
	<c:set var="checkEmpEntered" value="${resValue.checkEmpEntered}"></c:set>
	<c:set var="inHouseEditFlag" value="${resValue.inHouseEditFlag}"></c:set>
	<c:set var="eisEmpMst" value="${resValue.eisEmpMst}"></c:set>
	
	<fmt:message key="TR.FROMDATEVAL" bundle="${TrainerLabels}" var="dateValidation" />
	<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT" var="Submit"></fmt:message>
	<fmt:message bundle="${TrainerLabels}" key="TR.NEXT" var="Next"></fmt:message>
	<fmt:message key='TR.VALIDATEAGE' bundle="${TrainerLabels}" var="ageValidate"/>
	
	<fmt:message key="TR.TrainerType" bundle="${TrainerLabels}" var="trnrType"></fmt:message>
	<fmt:message key="TR.Prefix" bundle="${TrainerLabels}" var="trnrPrefix"></fmt:message>
	<fmt:message key="TR.TrainerNationality" bundle="${TrainerLabels}" var="trnrNation"></fmt:message>
	<fmt:message key="TR.TrainerMaritalStatus" bundle="${TrainerLabels}" var="trnrMarStatus"></fmt:message>
	<fmt:message key="TR.TrainerGender" bundle="${TrainerLabels}" var="trnrGender"></fmt:message>
	<fmt:message key="TR.TrainerSpecializeArea" bundle="${TrainerLabels}" var="trnrSpArea"></fmt:message>
	
	<c:choose>
		<c:when test="${firstTimeFlag eq 'false'}">
			<c:set var="nonMultiAreaOfSpecialization" value="${resValue.nonMultiAreaOfSpecialization }"></c:set>
			<c:set var="nonMultiGender" value="${resValue.nonMultiGender }"></c:set>
			<c:set var="nonMultiMaritalStatus" value="${resValue.nonMultiMaritalStatus }"></c:set>
			<c:set var="nonMultiTrainerType" value="${resValue.nonMultiTrainerType }"></c:set>
			<c:set var="nonMultiCountry" value="${resValue.nonMultiCountry }"></c:set>
			<c:set var="nonMultiOccupation" value="${resValue.nonMultiOccupation }"></c:set>
			<c:set var="cmnNationalityMst" value="${resValue.cmnNationalityMst }"></c:set>
			<c:set var="dateOfBirth" value="${resValue.dateOfBirth }"></c:set>
			<c:set var="nonMultiPrefix" value="${resValue.nonMultiPrefix }"></c:set>
			<c:set var="cmnPersonMst" value="${resValue.cmnPersonMst }" scope="session"></c:set>
			<c:set var="cmnContactMst" value="${resValue.cmnContactMst }" scope="session"></c:set>
			<c:set var="hrTrTrainerMst" value="${resValue.hrTrTrainerMst }" scope="session"></c:set>
			<c:set var="cmnAddressMst" value="${resValue.cmnAddressMst }" scope="session"></c:set>
			<c:set var="PhotoAttachmentVO" value="${resValue.PhotoAttachmentVO }" scope="session"></c:set>
			<c:set var="attachmentNames" value="${resValue.attachmentNames }" scope="session"></c:set>
		</c:when>
		
		<c:otherwise>
			<c:set var="languageList" value="${resValue.languageList }"></c:set>
			<c:set var="cmnGenderLookup" value="${resValue.cmnGenderLookup }"></c:set>
			<c:set var="cmnMaritalLookup" value="${resValue.cmnMaritalLookup }"></c:set>
			<c:set var="cmnOccupationLookup" value="${resValue.cmnOccupationLookup }"></c:set>
			<c:set var="nationList" value="${resValue.nationList }"></c:set>
			<c:set var="cmnTrainerTypeLookup" value="${resValue.cmnTrainerTypeLookup }"></c:set>
			<c:set var="cmnSpecalizedAreaLookup" value="${resValue.cmnSpecalizedAreaLookup }"></c:set>
			<c:set var="cmnPrefixLookUp" value="${resValue.cmnPrefixLookUp}"></c:set>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
	 <c:when test="${firstTimeFlag eq 'false'}">
	<c:set var="hrTrainerMstId" value="${resValue.hrTrainerMstId}"  scope="session"></c:set>
	</c:when>
	</c:choose>
	
<script type="text/javascript">

function checkforInhouseTrainer()
{
	if( ${empty trainerFlag})
	{
		return false;
		
	}
	else
	{
		if(${trainerFlag}==true)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
		
}


</script>


<hdiits:form name="trainersDetail" validate="true"  method="POST"
	action="hrms.htm?"  encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption  captionid="TR.TRAINERMASTER" bundle="${TrainerLabels}" captionLang="single"/></b></a></li>
	</ul>
</div>

<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0" >    
	
	<table class="tabtable">
		<tr>
		<hdiits:hidden name="actionFlag" default="TrainersDetail"/>
		<hdiits:hidden name="trainerFlag" default="${trainerFlag}"/>
		<hdiits:hidden name="inHouseEditFlag" default="${inHouseEditFlag}"/>
		<hdiits:hidden name="buttonSet"  default="garbage value"></hdiits:hidden>
		<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT" var="Submit"></fmt:message>
		<fmt:message bundle="${TrainerLabels}" key="TR.NEXT" var="Next"></fmt:message>
		
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerType" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			<hdiits:select sort="false" onchange="checkChange('${trainerFlag}','${Next}','${Submit}')"  captionid="TR.TrainerType" readonly="${editFlag or firstTimeFlag eq 'false' or inHouseEditFlag eq 'true'}" bundle="${TrainerLabels}" name="trainerType"  validation="sel.isrequired"  mandatory="true" >
				<c:choose>
					<c:when test="${firstTimeFlag eq 'false'}">
				    	<hdiits:option  value = "${nonMultiTrainerType.lookupName}"> <c:out value="${nonMultiTrainerType.lookupDesc }" > </c:out>  </hdiits:option>
				    </c:when>
				    <c:otherwise>
			     		<hdiits:option value="-1">--<c:out value='${trnrType}'/>--</hdiits:option>
				  	 	<c:forEach var="trainerTypeLst" items="${cmnTrainerTypeLookup}">
				  	 		<c:choose>
					  	 		<c:when test="${editFlag eq 'true' and trainerTypeLst.lookupName eq hrTrainerMst.cmnLookupMstTrainertypeLookupId.lookupName }">
					  	 			<hdiits:option value="${hrTrainerMst.cmnLookupMstTrainertypeLookupId.lookupName }" selected="true"><c:out value="${hrTrainerMst.cmnLookupMstTrainertypeLookupId.lookupDesc}" ></c:out> </hdiits:option>
					  	 		</c:when>
					  	 		<c:when test="${trainerFlag eq 'true' and SelectedTrainer.lookupName eq trainerTypeLst.lookupName }">
					  	 			<hdiits:option value="${SelectedTrainer.lookupName }" selected="true"><c:out value="${SelectedTrainer.lookupDesc}" ></c:out> </hdiits:option>
					  	 		</c:when>
					  	 		<c:when test="${inHouseEditFlag eq 'true' and hrTrainerMst.cmnLookupMstTrainertypeLookupId.lookupName eq trainerTypeLst.lookupName }">
					  	 			<hdiits:option value="${trainerTypeLst.lookupName }" selected="true"><c:out value="${trainerTypeLst.lookupDesc}" ></c:out> </hdiits:option>
					  	 		</c:when>
					  	 		<c:otherwise>
					  	 			 <hdiits:option value="${trainerTypeLst.lookupName}"><c:out value="${trainerTypeLst.lookupDesc}"> </c:out> </hdiits:option>
					  	 		</c:otherwise>
				  	 		</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</hdiits:select> 
			</td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>
	</table>
	<div id="inhouseTrainer" style="display:none">
	<table class="tabtable">
		<fmt:message key="TR.Trainer" bundle="${TrainerLabels}" var="title"></fmt:message>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.INHOUSE" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" colspan="6">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
					<jsp:param name="SearchEmployee" value="InhouseTrainer"/>
					<jsp:param name="searchEmployeeTitle" value="${title}"/> 
					<jsp:param name="mandatory" value="Yes"/> 
					<jsp:param name="condition" value="selReqForInHouse()"/>
					<jsp:param name="functionName" value="checkEmpId"/>
					</jsp:include>
			</td>
		</tr>
	</table>
	</div>
	
	<fmt:message bundle="${TrainerLabels}" key="TR.EMPPRESENT" var="ispresent"></fmt:message>
	<div id="inHouseTrainerInfo" style="display:none">
		
		<table class="tabtable">
		<hdiits:hidden name="Employee_ID" default="${empMst.empId}"/>  
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.Prefix" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:select sort="false" name="trainerTypeforInHouse" captionid="TR.Prefix"  bundle="${TrainerLabels}" readonly="true" >
				<hdiits:option value="${empMst.empPrefix}"> <c:out value="${empMst.empPrefix}"></c:out> </hdiits:option>
			 </hdiits:select></td>
			 <td class="fieldLabel" width="25%"> <hdiits:caption captionid="TR.TrainerFirstName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			 <td class="fieldLabel" width="25%"><hdiits:text onkeypress="return validateText(event, false)" maxlength="20" id="TrainerTypeInHouse" name="inHousetrainerFirstNameforInHouseTrainer" default="${empMst.empFname}" captionid="TR.TrainerFirstName" bundle="${TrainerLabels}" readonly="true"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"> <hdiits:caption captionid="TR.TrainerMiddleName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			<td class="fieldLabel" width="25%"><hdiits:text onkeypress="return validateText(event, false)" maxlength="20" name="trainerMiddleNameforInHouseTrainer" default="${empMst.empMname}" captionid="TR.TrainerMiddleName" bundle="${TrainerLabels}" readonly="true" /></td>
		
			<td class="fieldLabel" width="25%"> <hdiits:caption captionid="TR.TrainerLastName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			<td class="fieldLabel" width="25%"><hdiits:text onkeypress="return validateText(event, false)" maxlength="20" name="trainerLastNameforInHouseTrainer" default="${empMst.empLname}" captionid="TR.TrainerLastName" bundle="${TrainerLabels}" readonly="true"/></td>
		</tr>
		</table>
		<div id="inHouseTrainercurrentAddrInfo" style="display:none">
			<jsp:include page="/WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="inHouseAddress"/>
		        <jsp:param name="addressTitle" value="Your Address"/>
		        <jsp:param name="addrLookupName" value="Training Address"/>
		        <jsp:param name="isReadOnly" value="Yes"/>
		        <jsp:param name="mandatory" value="No"/>
		        <jsp:param name="condition" value="selReqForInHouse()"/>
	        </jsp:include>
		</div>	
		<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerNationality" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			<hdiits:select name="NationalityInHouse"  captionid="TR.TrainerNationality" bundle="${TrainerLabels}" readonly="true" >
				<hdiits:option value="${eisEmpMst.empNationality}" selected="true"><c:out value="${eisEmpMst.empNationality}"></c:out>  </hdiits:option>
			</hdiits:select></td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>
		
		<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerMaritalStatus" bundle="${TrainerLabels}" captionLang="single"/></td>
		<td class="fieldLabel" width="25%">
			<hdiits:select name="MaritalStatusInHouse" caption="Marital Status" readonly="true"> 
			<hdiits:option value="${eisEmpMst.cmnLookupMstByEmpMaritalStatusId.lookupName}" selected="true"><c:out value="${eisEmpMst.cmnLookupMstByEmpMaritalStatusId.lookupDesc}"></c:out>  </hdiits:option>
			</hdiits:select></td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerGender" bundle="${TrainerLabels}" captionLang="single"/></td>
			
			
		<td class="fieldLabel" width="25%">
			<hdiits:select name="genderInHouse" caption="Gender" readonly="true"> 
			<hdiits:option value="gender" selected="true"><c:out value="${eisEmpMst.empGender}"></c:out>  </hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerDOB" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			<fmt:formatDate value="${empMst.empDob}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="birthDateinHouse"/>
			<hdiits:dateTime  name="dateOfBirthInHouse"  captionid="TR.TrainerDOB"  default="${birthDateinHouse}"  bundle="${TrainerLabels}" condition="selReq()" disabled="true"/></td>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid= "TR.TrainerAge"  bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:number name="ageInHouse" captionid= "TR.TrainerAge" readonly="true"   bundle="${TrainerLabels}"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.CONTACTNO" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:number name="contactNumber" readonly="true" default="${eisEmpMst.contactNo}"/></td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption  captionid="TR.TrainerEmailAddress" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="emailInHouse" caption="Email Addr " readonly="true" default="${eisEmpMst.email}"/></td>
		</tr>
		</table>
	</div>
	
	<div id="visitorTrainerInfo" style="display:none">
	<table class="tabtable" >
		<tr>
		<hdiits:hidden name="editFlag" default="${editFlag}"/>
		<hdiits:hidden name="editTrainerMstId" default="${hrTrainerMst.trainerId}"/>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.Prefix" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="30%">
			<c:choose>
			    <c:when test="${firstTimeFlag eq 'false'}">
			    	<c:set var="name" value="trainerTypeForHiddenPrefix" />
				    <hdiits:hidden name="trainerprefix" default="${nonMultiPrefix.lookupId}" />
				</c:when>  	
				<c:otherwise>
			    	<c:set var="name" value="trainerprefix" />
			    </c:otherwise>
			 </c:choose>
			<hdiits:select id="prefixvalue"  sort="false" captionid="TR.Prefix" readonly="${readOnly}" bundle="${TrainerLabels}" name="${name}" condition="selReq(this)"  validation="sel.isrequired" mandatory="true" >
			  <c:choose>
				 <c:when test="${firstTimeFlag eq 'false'}">
			    	<hdiits:option  value = "${nonMultiPrefix.lookupId}"> <c:out value="${nonMultiPrefix.lookupDesc }" > </c:out>  </hdiits:option>
			     </c:when>
			     <c:otherwise>
			     	<hdiits:option value="-1">--<c:out value='${trnrPrefix}'/>--</hdiits:option>
			  	 	<c:forEach var="trainerPrefixLst" items="${cmnPrefixLookUp}">
			  	 		<c:choose>
			  	 			<c:when test="${editFlag eq 'true' and hrTrainerMst.prefix eq trainerPrefixLst.lookupDesc}">
			  	 				<hdiits:option value="${trainerPrefixLst.lookupId }" selected="true"><c:out value="${trainerPrefixLst.lookupDesc }" ></c:out> </hdiits:option>
			  	 			</c:when>
			  	 			<c:otherwise>
			  	 				 <hdiits:option value="${trainerPrefixLst.lookupId}"><c:out value="${trainerPrefixLst.lookupDesc}"> </c:out> </hdiits:option>
			  	 			</c:otherwise>
		 	      		 </c:choose>
					</c:forEach>
				 </c:otherwise>
				</c:choose>
			 </hdiits:select> </td>
			<td class="fieldLabel" width="15%"> <hdiits:caption captionid="TR.TrainerFirstName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			
			<td class="fieldLabel" width="30%" align="left"><hdiits:text maxlength="20" id="VisitorTrainerFirstName" name="trainerFirstName" default="${hrTrainerMst.firstName}" captionid="TR.TrainerFirstName" bundle="${TrainerLabels}" validation="txt.isrequired,txt.isname" condition="selReq(this)" mandatory="true"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"> <hdiits:caption captionid="TR.TrainerMiddleName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			<td class="fieldLabel" width="25%"><hdiits:text maxlength="20" name="trainerMiddleName" default="${hrTrainerMst.middleName}"  validation="txt.isname" captionid="TR.TrainerMiddleName" condition="selReq(this)" bundle="${TrainerLabels}" /></td>
		
			<td class="fieldLabel" width="25%"> <hdiits:caption captionid="TR.TrainerLastName" bundle="${TrainerLabels}" captionLang="single"/> </td>
			<td class="fieldLabel" width="25%"><hdiits:text maxlength="20" name="trainerLastName" default="${hrTrainerMst.lastName}"  validation="txt.isname" captionid="TR.TrainerLastName" bundle="${TrainerLabels}" /></td>
		</tr>
		
		
		</table>
		
				<c:set var="readonlyforAddress" value="No"></c:set>
			<c:if test="${readOnly eq 'true'}">
				<c:set var="readonlyforAddress" value="Yes"></c:set>
			</c:if> 
			
		
		<jsp:include page="/WEB-INF/jsp/common/address.jsp">
			<jsp:param name="addrName" value="trainerAddress"/>
	        <jsp:param name="addressTitle" value="Your Address"/>
	        <jsp:param name="addrLookupName" value="Training Address"/>
	        <jsp:param name="isReadOnly" value="${readonlyforAddress}"/>
	        <jsp:param name="mandatory" value="Yes"/>
	        <jsp:param name="condition" value="selReq()"/>
        </jsp:include>
        
    	
		
		
      	
		<table class="tabtable" name="trainersInformation">
		
	    <tr>
	    	<td class="fieldLabel" width="28%"><hdiits:caption captionid="TR.TrainerNationality" bundle="${TrainerLabels}" captionLang="single"/></td>
	    	<td class="fieldLabel" width="25%" align="left">
	    	
	    	<c:choose>
		    <c:when test="${firstTimeFlag eq 'false'}">
		    	<c:set var="name" value="trainerTypeForHiddenNationaliy" />
		    	<hdiits:hidden name="nationality" default="${cmnNationalityMst.nationalityId}"/>
		    </c:when>
			<c:otherwise>
		    	<c:set var="name" value="nationality" />
		 	</c:otherwise>
	        </c:choose>
	        
	    	<hdiits:select sort="false" readonly="${readOnly}" captionid="TR.TrainerNationality" bundle="${TrainerLabels}" mandatory="true" name="${name}" condition="selReq()" validation="sel.isrequired"
				caption="Nationality">
				<c:choose>
				    <c:when test="${firstTimeFlag eq 'false'}">
						<hdiits:option value = "${cmnNationalityMst.nationalityId}"> <c:out value="${cmnNationalityMst.nationalityName }" > </c:out>  </hdiits:option>
					</c:when>
					<c:otherwise>
			     		<hdiits:option value="-1">----------------------<c:out value='${trnrNation}'/>--------------------</hdiits:option>
						<c:forEach var="trainersnationlst" items="${nationList}">
							<c:choose>
								<c:when test="${editFlag eq 'true' and trainersnationlst.nationalityId eq hrTrainerMst.cmnPersonMst.cmnNationalityMst.nationalityId}">
									<hdiits:option value="${hrTrainerMst.cmnPersonMst.cmnNationalityMst.nationalityId}" selected="true"><c:out value="${hrTrainerMst.cmnPersonMst.cmnNationalityMst.nationalityName}" ></c:out> </hdiits:option>
								</c:when>
								<c:otherwise>
									<hdiits:option value="${trainersnationlst.nationalityId}"><c:out value="${trainersnationlst.nationalityName}"></c:out></hdiits:option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					 </c:otherwise>
				 </c:choose>
			</hdiits:select>
	    	</td>
	    	<td class="fieldLabel" width="25%"></td>
	    	<td class="fieldLabel" width="22%"></td>
	    </tr>
	    </table>
	    
	    <table class="tabtable">	
		<tr>
			<td class="fieldLabel" width="26%"><hdiits:caption captionid="TR.TrainerMaritalStatus" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="20%">
			 
			<c:choose>
		    <c:when test="${firstTimeFlag eq 'false'}">
		    	<c:set var="name" value="trainerTypeForHiddenmaritalStatus" />
		    	<hdiits:hidden name="maritalStatus" default="${nonMultiMaritalStatus.lookupId}"/>
		    </c:when>
			<c:otherwise>
		    	<c:set var="name" value="maritalStatus" />
		 	</c:otherwise>
	        </c:choose>
	        
			 <hdiits:select sort="false" name="${name}"  readonly="${readOnly}" captionid="TR.TrainerMaritalStatus" bundle="${TrainerLabels}" condition="selReq()" validation="sel.isrequired" mandatory="true" >
			 	<c:choose>
				 	<c:when test="${firstTimeFlag eq 'false'}">
				 		<hdiits:option value = "${nonMultiMaritalStatus.lookupId}"> <c:out value="${nonMultiMaritalStatus.lookupDesc}" > </c:out>  </hdiits:option>
				 	</c:when>
				 	<c:otherwise>
			     		<hdiits:option value="-1">---------------<c:out value='${trnrMarStatus}'/>-------------</hdiits:option>
				 		<c:forEach items="${cmnMaritalLookup}" var="maritalStatus">
				 			<c:choose>
				 				<c:when test="${editFlag eq 'true' and maritalStatus.lookupId eq hrTrainerMst.cmnPersonMst.cmnLookupMstByMaritalLookupid.lookupId}">
				 					<hdiits:option value="${hrTrainerMst.cmnPersonMst.cmnLookupMstByMaritalLookupid.lookupId}" selected="true"><c:out value="${hrTrainerMst.cmnPersonMst.cmnLookupMstByMaritalLookupid.lookupDesc}"></c:out> </hdiits:option>
				 				</c:when>
				 				<c:otherwise>
				 					<hdiits:option value="${maritalStatus.lookupId}"> <c:out value="${maritalStatus.lookupDesc}"> </c:out> </hdiits:option>
				 				</c:otherwise>
				 			</c:choose>
				 		</c:forEach>
				 	</c:otherwise>
			 	</c:choose>
			 </hdiits:select>
			 </td>
			 
			<td class="fieldLabel" width="26%" align="left"><hdiits:caption captionid="TR.TrainerGender" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="28%">
			<c:choose>
		    <c:when test="${firstTimeFlag eq 'false'}">
		    	<c:set var="name" value="trainerTypeForHiddengender" />
		    	<hdiits:hidden name="gender" default="${nonMultiGender.lookupId}"/>
		    </c:when>
			<c:otherwise>
		    	<c:set var="name" value="gender" />
		 	</c:otherwise>
	        </c:choose>
	        
	        
				<hdiits:select  sort="false" readonly="${readOnly}" name="${name}" captionid="TR.TrainerGender" bundle="${TrainerLabels}" condition="selReq()" validation="sel.isrequired" mandatory="true" >
				<c:choose>
					<c:when test="${firstTimeFlag eq 'false'}">
						<hdiits:option value = "${nonMultiGender.lookupId}"> <c:out value="${nonMultiGender.lookupDesc}" > </c:out>  </hdiits:option>
					</c:when>
					<c:otherwise>
			     		<hdiits:option value="-1">---<c:out value='${trnrGender}'/>--</hdiits:option>
						<c:forEach var="trainersGender" items="${cmnGenderLookup }">
						<c:choose>
							<c:when test="${editFlag eq 'true' and trainersGender.lookupId eq hrTrainerMst.cmnPersonMst.cmnLookupMstByGenderLookupid.lookupId}">
								<hdiits:option selected="true" value="${trainersGender.lookupId}"><c:out value="${trainersGender.lookupDesc}"></c:out></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${trainersGender.lookupId}"><c:out value="${trainersGender.lookupDesc}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
							
					</c:forEach>
					</c:otherwise>
				</c:choose>
			</hdiits:select>
			</td>
		</tr>
		
		<tr>
			
			<td class="fieldLabel" width="27%"><hdiits:caption captionid="TR.TrainerDOB" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="15%">
			<c:choose>
			     		<c:when test="${editFlag eq 'true' }">
			     		<c:choose>
			     				<c:when test="${firstTimeFlag eq 'false'}">
			     					<fmt:formatDate value="${cmnPersonMst.dob}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="birthDate"/>
			     				</c:when>
			     				<c:otherwise>
			     					<fmt:formatDate value="${hrTrainerMst.cmnPersonMst.dob}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="birthDate"/>
			     				</c:otherwise>
			     			</c:choose>
			     			
			     		</c:when>
			     		<c:otherwise>
			     			<fmt:formatDate value="${cmnPersonMst.dob}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="birthDate"/>
			     		</c:otherwise>
			</c:choose>
			
			<hdiits:dateTime  name="dateOfBirth" onblur="ageFromDateOfBirth('${ageValidate}')"  captionid="TR.TrainerDOB" mandatory="true" default="${birthDate}"  bundle="${TrainerLabels}" condition="selReq()" disabled="${readOnly}" validation="txt.isrequired"/></td>
			
			<td class="fieldLabel" width="18%" align="left"><hdiits:caption captionid= "TR.TrainerAge"  bundle="${TrainerLabels}" captionLang="single"/></td>
			
			<c:choose>
			     		<c:when test="${editFlag eq 'true'}">
			     			<c:choose>
			     				<c:when test="${firstTimeFlag eq 'false'}">
			     					<c:set var="age" value="${cmnPersonMst.age}"></c:set>
			     				</c:when>
			     				<c:otherwise>
			     					<c:set var="age" value="${hrTrainerMst.cmnPersonMst.age}"></c:set>
			     				</c:otherwise>
			     			</c:choose>
			     			
			     		</c:when>
			     		<c:otherwise>
			     			<c:set var="age" value="${cmnPersonMst.age}"></c:set>
			     		</c:otherwise>
			</c:choose>
			<td class="fieldLabel" width="41%" align="left"><hdiits:number maxlength="3" readonly="true" name="trngage" captionid="TR.TrainerAge" bundle="${TrainerLabels}" default="${age}" condition="selReq()" validation="txt.isrequired" mandatory="true"/></td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="26%"><hdiits:caption captionid="TR.TrainerResidentNo" bundle="${TrainerLabels}" captionLang="single"/></td>
				<c:choose>
			     		<c:when test="${editFlag eq 'true'}">
			     			<c:set var="resPhone" value="${hrTrainerMst.cmnContactMst.residencePhone}"></c:set>
			     		</c:when>
			     		<c:otherwise>
			     			<c:set var="resPhone" value="${cmnContactMst.residencePhone}"></c:set>
			     		</c:otherwise>
				</c:choose>
			<td class="fieldLabel" width="20%"><hdiits:phoneNumber readonly="${readOnly}" name="residentPhoneNumber"  default="${resPhone}"  captionid="TR.TrainerResidentNo" bundle="${TrainerLabels}" /></td>
			<td class="fieldLabel" width="23%" align="left"><hdiits:caption captionid="TR.TrainerMobileNo"  bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="31%" align="left"><hdiits:phoneNumber readonly="${readOnly}"   name="mobileNumber" default="${cmnContactMst.mobile}" captionid="TR.TrainerMobileNo" bundle="${TrainerLabels}" /></td>
			
		</tr>
	
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerFaxNo" bundle="${TrainerLabels}" captionLang="single"/></td>
				<c:choose>
			     		<c:when test="${editFlag eq 'true'}">
			     			<c:set var="fxAddress" value="${hrTrainerMst.cmnContactMst.fax}"></c:set>
			     		</c:when>
			     		<c:otherwise>
			     			<c:set var="fxAddress" value="${cmnContactMst.fax}"></c:set>
			     		</c:otherwise>
				</c:choose>
			<td class="fieldLabel" width="25%"><hdiits:number readonly="${readOnly}" name="fax" default="${fxAddress}" captionid="TR.TrainerFaxNo" bundle="${TrainerLabels}" maxlength="20"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption  captionid="TR.TrainerEmailAddress"  bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:Email readonly="${readOnly}" name="email"   captionid="TR.TrainerEmailAddress" bundle="${TrainerLabels}" condition="selReq()" /></td>
		</tr>
		</table>
		<fmt:message key="TR.PERSONIDENTIFICATION" bundle="${TrainerLabels}" var="person_identification"></fmt:message>
		<c:if test="${firstTimeFlag ne 'false'}">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" >
				<jsp:param name="attachmentName" value="PhotoAttachment" />
				<jsp:param name="formName" value="trainersDetail" />
				<jsp:param name="attachmentType" value="Biometric" />  
				<jsp:param name="attachmentTitle" value="${person_identification}" />    
				<jsp:param name="multiple" value="N" />   
				<jsp:param name="documentType" value="IMAGE" />
				<jsp:param name="isScannerEnable" value="Y" />
				<jsp:param name="attachmentPrefix" value="AccPhoto" />       
				<jsp:param name="bioDeviceType" value="IRIS,FACE,MULTI_FINGER,SIDE_FACE" />
				<jsp:param name="readOnly" value="N" />
			</jsp:include>
		</c:if>
		
		</div>
		<table class="tabtable" width="100%" >
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerSpecializeArea" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			<c:choose>
		    <c:when test="${firstTimeFlag eq 'false'}">
		    	<c:set var="name" value="trainerTypeForHiddenspecializedArea" />
		    	<hdiits:hidden name="specializedArea" default="${nonMultiAreaOfSpecialization.lookupId}"/>
		    </c:when>
			<c:otherwise>
		    	<c:set var="name" value="specializedArea" />
		 	</c:otherwise>
	        </c:choose>
			<hdiits:select sort="false" readonly="${readOnly}" id="specializeArea" style="width:68%" name="specializedArea" condition="selForSpecializeArea()" validation="sel.isrequired" mandatory="true" default="${nonMultiAreaOfSpecialization.lookupDesc}" captionid="TR.TrainerSpecializeArea" bundle="${TrainerLabels}">
				<c:choose>
					<c:when test="${firstTimeFlag eq 'false'}">
						<hdiits:option value="${nonMultiAreaOfSpecialization.lookupId}"><c:out value="${nonMultiAreaOfSpecialization.lookupDesc}"></c:out></hdiits:option>
					</c:when>
					<c:otherwise>
			     		<hdiits:option value="-1">----<c:out value='${trnrSpArea}'/>------</hdiits:option>
						<c:forEach var="trainersSpecalisedArea" items="${cmnSpecalizedAreaLookup }">
						<c:choose>
							<c:when test="${trainersSpecalisedArea.lookupId eq hrTrainerMst.cmnLookupMstAreaOfSpecialization.lookupId}">
								<hdiits:option value="${hrTrainerMst.cmnLookupMstAreaOfSpecialization.lookupId}" selected="true"><c:out value="${hrTrainerMst.cmnLookupMstAreaOfSpecialization.lookupDesc}"></c:out> </hdiits:option>
							</c:when>
							<c:when test="${inHouseEditFlag eq 'true' and hrTrainerMst.cmnLookupMstAreaOfSpecialization.lookupName eq trainerTypeLst.lookupName }">
					  	 			<hdiits:option value="${trainerTypeLst.lookupId }" selected="true"><c:out value="${trainerTypeLst.lookupDesc}" ></c:out> </hdiits:option>
					  	 		</c:when>
							<c:otherwise>
								<hdiits:option value="${trainersSpecalisedArea.lookupId}"><c:out value="${trainersSpecalisedArea.lookupDesc}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</hdiits:select>
			 </td>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerRemark" bundle="${TrainerLabels}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%" align="left"><hdiits:textarea cols="23"  rows="3" default="${hrTrainerMst.trngRemarks}" name="remark" captionid="TR.TrainerRemark" bundle="${TrainerLabels}" maxlength="500"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerAvailable" bundle="${TrainerLabels}" captionLang="single"/></td>
				<c:choose>
			     		<c:when test="${firstTimeFlag eq 'false'}">
			     			<c:set var="available" value="${hrTrTrainerMst.availability}"></c:set>
			     		</c:when>
			     		<c:otherwise>
			     			<c:set var="available" value="${hrTrainerMst.availability}"></c:set>
			     		</c:otherwise>
				</c:choose>
			<td class="fieldLabel" width="25%"><hdiits:checkbox  id="availabilityFlag" readonly="${readOnly}" default="${available}" onclick="AvailabilityCheck()" name="available" value="Y"/></td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>
		</table>
		
		
		<div id="trainerAvailability" style="display:none">
		<table class="tabtable">
		 <tr>
			<td class="fieldLabel" width="25%"><hdiits:caption  captionid="TR.TrainerAvailableFromDate" bundle="${TrainerLabels}" captionLang="single"/></td>
			
			<c:choose>
					 <c:when test="${firstTimeFlag eq 'false'}">
						<fmt:formatDate value="${hrTrTrainerMst.dateFrom}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="availableFromDate"/>
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${hrTrainerMst.dateFrom}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="availableFromDate"/>
					</c:otherwise>
			</c:choose>
				
			<td class="fieldLabel" width="25%"><hdiits:dateTime  condition="confirmAvailableDates()" disabled="${readOnly}" default="${availableFromDate}" validation="txt.isrequired" name="notAvailableFromDate" captionid="TR.TrainerAvailableFromDate" bundle="${TrainerLabels}" mandatory="true" onblur="checkFromDate('${dateValidation}')" maxvalue="01/01/9999"/></td>
			<td class="fieldLabel" width="25%"><hdiits:caption  captionid="TR.TrainerAvailableToDate" bundle="${TrainerLabels}" captionLang="single"/></td>
				<c:choose>
					 <c:when test="${firstTimeFlag eq 'false'}">
						<fmt:formatDate value="${hrTrTrainerMst.dateTo}" pattern="yyyy-MM-dd HH:mm:ss"  dateStyle="medium" var="availableToDate"/>
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${hrTrainerMst.dateTo}" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="medium" var="availableToDate"/>
					</c:otherwise>
				</c:choose>
			
			<td class="fieldLabel" width="25%"><hdiits:dateTime captionid="TR.TrainerAvailableToDate" bundle="${TrainerLabels}"  condition="confirmAvailableDates()" disabled="${readOnly}" default="${availableToDate}" validation="txt.isrequired" name="notAvaliableToDate"  mandatory="true" onblur="checkToDate('${dateValidation}')" maxvalue="01/01/9999"/></td>
		</tr>
		</table>
		
		 
		
		</div>
<div  id="TabNavigationButton" style="display:none" >
	
	<jsp:include page="../../core/tabnavigation.jsp" > 
	<jsp:param name="submitText" value="Submit"/>
	</jsp:include>
</div>

	</div>	
	
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>'/>
		
<c:if test="${hrTrTrainerMst.availability == 'Y'}">
<script>
document.getElementById("trainerAvailability").style.display="";
</script>
</c:if>


<script>
if(!${empty hrTrainerMst.cmnContactMst.email})
{
	document.getElementById("email").value='${hrTrainerMst.cmnContactMst.email}';
}
if(!${empty cmnContactMst.email})
{
document.getElementById("email").value='${cmnContactMst.email}';
}


</script>

<c:if test="${hrTrainerMst.availability == 'Y'}">
<script>
document.getElementById("trainerAvailability").style.display="";
</script>


</c:if>
<c:choose>
<c:when test="${firstTimeFlag eq 'false'}">
<%
if (request.getSession().getAttribute("locale").equals("en_US"))
{
    request.getSession().setAttribute("locale", "gu");
}
else if (request.getSession().getAttribute("locale").equals("gu"))
{
    request.getSession().setAttribute("locale", "en_US");
}
%>
</c:when>
</c:choose>

<c:choose>
	<c:when test="${trainerFlag==true}">
	<script>document.getElementById("inHouseTrainerInfo").style.display="";</script>
		<c:choose>
			<c:when test="${SelectedTrainer.lookupDesc eq 'Visitor'}">
		<script>
			document.getElementById("visitorTrainerInfo").style.display="";
			document.getElementById("inhouseTrainer").style.display="none";
			document.getElementById("inHouseTrainerInfo").style.display="";
			navDisplay=true;
			document.getElementById("TabNavigationButton").style.display="";
			document.getElementById("Prev").style.display="";
			document.getElementById("Next").style.display="none";
		</script>
		<c:set var="trainerFlag" value="false"></c:set>
			</c:when>
			<c:otherwise>
			<script>
				document.getElementById("inhouseTrainer").style.display="";
				document.getElementById("visitorTrainerInfo").style.display="none";
				navDisplay=true;
				document.getElementById("TabNavigationButton").style.display="";
				document.getElementById("Prev").style.display="none";
				document.getElementById("Next").style.display="none";
			</script>
			</c:otherwise>
		</c:choose>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${firstTimeFlag eq 'false'}">
	<script>
		document.getElementById("visitorTrainerInfo").style.display="";
		document.getElementById("inhouseTrainer").style.display="none";
		document.getElementById("formSubmitButton").value="<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT"></fmt:message>";
	</script>
	</c:when>
</c:choose>






<c:choose>
	<c:when test="${editFlag eq 'true'}">
	<script>
		var checkTrainerType='${hrTrainerMst.cmnLookupMstTrainertypeLookupId.lookupName}';
		if(checkTrainerType=='Visitor')
		{
			navDisplay=true;
			document.getElementById("visitorTrainerInfo").style.display="";
			document.getElementById("inhouseTrainer").style.display="none";
			document.getElementById("TabNavigationButton").style.display="";
			document.getElementById("Prev").style.display="";
			document.getElementById("Next").style.display="none";
			if(${firstTimeFlag eq 'false'})
			{
				document.getElementById("formSubmitButton").value="<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT"></fmt:message>";
			}else
			{
				document.getElementById("formSubmitButton").value="<fmt:message bundle="${TrainerLabels}" key="TR.NEXT"></fmt:message>";
			}
		}
		if(checkTrainerType=='In-House')
		{
			navDisplay=false;
			document.getElementById("TabNavigationButton").style.display="";
			document.getElementById("inhouseTrainer").style.display="";
			document.getElementById("visitorTrainerInfo").style.display="none";
			document.getElementById("formSubmitButton").value="<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT"></fmt:message>";
			document.getElementById("Prev").style.display="none";
			document.getElementById("Next").style.display="none";
		}
		
	</script>
	</c:when>
</c:choose>

		
<c:choose>
	<c:when test="${firstTimeFlag eq 'false'}">
	<script>
		navDisplay=true;
		document.getElementById("Prev").style.display="";
		document.getElementById("Next").style.display="none";
		document.getElementById("TabNavigationButton").style.display="";
		document.getElementById("formSubmitButton").value="<fmt:message bundle="${TrainerLabels}" key="TR.SUBMIT"></fmt:message>";
	</script>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${inHouseEditFlag eq 'true'}">
	<script>
	document.getElementById("actionFlag").value="setInHouseTrainerEmp";
	document.getElementById("inHouseTrainerInfo").style.display="";
	document.getElementById("inhouseTrainer").style.display="none";

	</script>
	
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${empty eisEmpMst.cmnAddressMstByEmpCurrentAddressId}">
		<script>document.getElementById("inHouseTrainercurrentAddrInfo").style.display="none";</script>
	</c:when>
	<c:otherwise>
		<script>
			document.getElementById("inHouseTrainercurrentAddrInfo").style.display="";
			
		</script>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${hrTrTrainerMst.availability eq 'N'}">
	<script>document.getElementById("trainerAvailability").style.display="none";</script>
	</c:when>
	
</c:choose>




<script>
	if(${checkEmpEntered == 'true'})
	{
		alert('${ispresent}');
	}

</script>
<script type="text/javascript">



</script>
<script type="text/javascript">

function selForSpecializeArea(th)
{
	if(${editFlag ne undefined} && ${editFlag eq 'true'})
		return false;
	else
		return true;
}



</script>
<script type="text/javascript">

function selReqForInHouse(th)
{	
	
	var trainerType=document.getElementById("trainerType").value;
	if(trainerType=='Visitor')
	{
		return false;
	}
	
	var inHouse=${inHouseEditFlag eq 'true'};
	var conditioninHouse=(trainerType=='Visitor' && inHouse);
	isEmpMstEmpty=${empty empMst};
	if(isEmpMstEmpty)
	{
		document.getElementById("actionFlag").value="setInHouseTrainerEmp";
	}
	if(trainerType=='Visitor' || inHouse || (!isEmpMstEmpty ))
	{
		return false;
	}else
	{
		
			return true;
	}
}

</script>
<script type="text/javascript">
var trainerType=document.getElementById("trainerType").value;
if(${empty empMst})
{
}else
{
	document.getElementById("actionFlag").value="setInHouseTrainerEmp";
}

toDaysDate=new Date;
toDaysDateArry = (''+toDaysDate).split(" ");
var dob=document.getElementById("dateOfBirthInHouse").value;
dobArry=dob.split("/")
var inHouseage=toDaysDateArry[5]-dobArry[2];
if(inHouseage > 0 )
{
	document.getElementById("ageInHouse").value=inHouseage;
}
	
</script>
<script type="text/javascript">

</script>
</div>
</hdiits:form>
<%
 }catch(Exception e)
{
	e.printStackTrace();
}%>

