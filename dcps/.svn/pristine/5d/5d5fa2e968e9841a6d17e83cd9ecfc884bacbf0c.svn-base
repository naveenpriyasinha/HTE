<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@page import="java.util.Date"%>
<fmt:setBundle basename="resources.trng.TrainingMstLables"	var="trangLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="editFlag" value="${resValue.editFlag}" />
<c:set var="lstTrainingType" value="${resValue.lstTrainingType}" />
<c:set var="lstQualification" value="${resValue.lstQualification}" />
<c:set var="changeLocaleFlag" value="${resValue.changeLocaleFlag}" />
<c:set var="langId" value="${resValue.langId}" />
<c:set var="defaults" value="${resValue.defaults}" />
<c:set var="today" value="${resValue.today}" />
<c:set var="defaultTrainingType" value="${defaults.cmnLookupMstTrainingTypeLookupId}" />
<c:set var="trainingId" value="${resValue.trainingId}" />
<c:set var="subKey" value="${resValue.SubmitKey}" />
<c:set var="trainingName" value="${defaults.trainingName}"/>
<c:set var="trainingDetail" value="${defaults.trainingDetail}"/>
<c:set var="eligibilityCriteria" value="${defaults.eligibilityCriteria}"/>
<c:set var="locationCode" value="${resValue.locationCode}"/>
<c:set var="lstQualificationflag" value="${resValue.lstQualificationflag}"/>

<fmt:message key="TR.AgeAltMax" bundle="${trangLables}" var="mag" /> 
<fmt:message key="TR.AgeAltMin" bundle="${trangLables}" var="mag1" />
<fmt:message key="TR.AgeBlMX" bundle="${trangLables}" var="mag2" />
<fmt:message key="TR.AgeBlMN" bundle="${trangLables}" var="mag3" />

<c:if test="${changeLocaleFlag == 'Y'}" >
<c:set var="readOnly" value="true" />
<c:set var="addrReadOnly" value="Yes" />

<c:if test="${editFlag != 'Y'}">

<c:set var="trainingName" value=""/>
<c:set var="trainingDetail" value=""/>
<c:set var="eligibilityCriteria" value=""/>

</c:if>

</c:if>


<c:if test="${changeLocaleFlag != 'Y'}" >
<c:set var="readOnly" value="false" />
<c:set var="addrReadOnly" value="No" />
</c:if>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"> </script>
<script type="text/javascript"
	src="<c:url value="/script/eis/Address.js"/>">
</script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/training/TrainingMst.js"></script>
<c:out value="${resValue.msg}" />


<hdiits:form name="frmTraining" validate="true" method="POST"
	encType="multipart/form-data" action="./hdiits.htm?actionFlag=insertTraining">
	
	
	<fmt:formatDate value="${today}" pattern="dd/MM/yyyy" dateStyle="medium" var="todayDate"/>
	<hdiits:hidden id="todayDate" name="todayDate" default="${todayDate}"/>
	<hdiits:hidden name="editFlag" default="${editFlag}"/>
	<hdiits:hidden name="langId" default="${langId}"/>
	<hdiits:hidden name="changeLocaleFlag" default="${changeLocaleFlag}"/>
	<hdiits:hidden name="trainingId" default="${trainingId}"/>
	<hdiits:hidden name="trainingCode" default="${defaults.trngCode}"/>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="TR.TrainingMst" bundle="${trangLables}" captionLang="single"/> </a></li>
	</ul>
	</div>

	<div class="tabcontentstyle">

	<div id="tcontent1" class="tabcontent" tabno="0">

	<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName"	bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:text name="trainingName" caption="trainingName"	validation="txt.isrequired" mandatory="true" default="${trainingName}" maxlength="100" onkeypress="return validateText(event, false)"/></td>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType"	bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			
			<c:if test="${changeLocaleFlag == 'N'}" >
			<hdiits:select name="trainingType" validation="sel.isrequired" mandatory="true" caption="trainingType" sort="false">
				<option value="-1">----select----</option>
				<c:forEach var="trngType" items="${lstTrainingType}">
					<c:choose>
					<c:when test="${trngType.lookupId == defaultTrainingType.lookupId}">
					<option selected="true" value='<c:out value="${trngType.lookupId}"/>'><c:out value="${trngType.lookupDesc}" /></option>										
					</c:when>
					<c:otherwise>
					<option value='<c:out value="${trngType.lookupId}"/>'><c:out value="${trngType.lookupDesc}" /></option>
					</c:otherwise>
					</c:choose>	
				</c:forEach>
				</hdiits:select>
			</c:if>	
				
			<c:if test="${changeLocaleFlag == 'Y'}" >
			<hdiits:select name="trainingType" readonly="true" validation="sel.isrequired" mandatory="true" caption="trainingType" sort="false">
			<option value="${lstTrainingType.lookupId}" selected="selected">${lstTrainingType.lookupDesc}</option>
			<hdiits:hidden name="trainingType1" default="${lstTrainingType.lookupId}"/>
			</hdiits:select>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingDetail" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:textarea name="trainingDetail" caption="trainingDetail" validation="txt.isrequired"	mandatory="true" default="${trainingDetail}" maxlength="500" onkeypress="return validateText(event, false)"/></td>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ElegiCriteria" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:textarea name="elegiCriteria" caption="elegiCriteria" validation="txt.isrequired" mandatory="true" default="${eligibilityCriteria}" maxlength="200" onkeypress="return validateText(event, false)"/></td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.AGEMIN" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:age name="age" validation="txt.isrequired,txt.isnumber"	caption="age" mandatory="true" default="${defaults.minage}" readonly="${readOnly}" /></td>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.Age" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%"><hdiits:age name="agemax" validation="txt.isrequired,txt.isnumber"	caption="age" mandatory="true" default="${defaults.maxage}" readonly="${readOnly}" onblur="checkMaxAge('${mag}','${mag2}')"/></td>
		</tr>
		
		<tr id="qualfic">
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.Qualification" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			
			<c:if test="${changeLocaleFlag == 'N'}" >
			<hdiits:select name="qualification"  caption="qualification" sort="false"> 
				<option value="-1">----select----</option>
				
				<c:forEach var="qual" items="${lstQualification}">
					
					<c:choose>
	
					<c:when test="${qual.lookupId == defaults.cmnLookupMstQualificationLookupId.lookupId}">
							
					<option selected="true" value='<c:out value="${qual.lookupId}"/>'><c:out value="${qual.lookupDesc}" /></option>										
					</c:when>
					<c:otherwise>
					<option value='<c:out value="${qual.lookupId}"/>'><c:out value="${qual.lookupDesc}" /></option>
					</c:otherwise>
					</c:choose>	
				</c:forEach>
				</hdiits:select>
			</c:if>
			
			<c:if test="${changeLocaleFlag == 'Y'}" >
			<hdiits:select name="qualification" readonly="true"  caption="qualification">
			<option value="${lstQualification.lookupId}" selected="selected">${lstQualification.lookupDesc}</option>
			<hdiits:hidden name="qualification1" default="${lstQualification.lookupId}"/>
			</hdiits:select>
			</c:if>
			</td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>
		
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ExamReq"	bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			
			<c:choose>
			<c:when test="${changeLocaleFlag eq 'N'}">
					<hdiits:radio name="examReq" caption="Yes" value="Y" readonly="${readOnly}"/>
					<hdiits:radio name="examReq" caption="No" value="N" readonly="${readOnly}" default="N"/>
			</c:when>
			<c:otherwise>
					<hdiits:radio name="examReq" caption="Yes" value="Y" readonly="${readOnly}" default="${defaults.isExamRequired}"/>
					<hdiits:radio name="examReq" caption="No" value="N" readonly="${readOnly}" default="${defaults.isExamRequired}"/>
			</c:otherwise>
			</c:choose>
			</td>
			
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.CertiReq"	bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
			<c:choose>
			<c:when test="${changeLocaleFlag eq 'N'}">
					<hdiits:radio name="certiReq" caption="Yes" value="Y" readonly="${readOnly}"/>
					<hdiits:radio name="certiReq" caption="No" value="N" readonly="${readOnly}" default="N"/>
			</c:when>
			<c:otherwise>
					<hdiits:radio name="certiReq" caption="Yes" value="Y" readonly="${readOnly}" default="${defaults.isCertiRequired}"/>
					<hdiits:radio name="certiReq" caption="No" value="N" readonly="${readOnly}" default="${defaults.isCertiRequired}"/>
			</c:otherwise>
			</c:choose>
			
			</td>
		</tr>

		<tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ExternalFlag" bundle="${trangLables}" captionLang="single"/></td>
			<td class="fieldLabel" width="25%">
				
			<c:choose>
				<c:when test="${locationCode eq 'Y'}">
							<hdiits:hidden name="externalFlag" default="Y" id="externalFlag"/>
							<hdiits:radio name="externalFlag" caption="Yes" value="Y" readonly="true" default="Y" />
							<hdiits:radio name="externalFlag" caption="No" value="N" readonly="true" />
				</c:when>
				<c:otherwise>
						<hdiits:hidden name="externalFlag" default="N" id="externalFlag"/>
						<hdiits:radio name="externalFlag1" caption="Yes" value="Y" readonly="true"  />
						<hdiits:radio name="externalFlag1" caption="No" value="N" readonly="true"  default="N"/>
				</c:otherwise>
			</c:choose>		
							
							
				
					
			</td>
		</tr>
	</table>
	<div id="externalInfo" style="display:none">
	<table class="tabtable">
		<tr>
		</tr>
		<tr>

		</tr>

	</table>
	<jsp:include page="/WEB-INF/jsp/common/address.jsp">
		<jsp:param name="addrName" value="trainingAddress" />
		<jsp:param name="addrLookupName" value="Training Address" />
		<jsp:param name="addressTitle" value="Training Address" />
		<jsp:param name="isReadOnly" value="${addrReadOnly}"/>
	</jsp:include>
	</div>




	</div><!--   ....Tab 1 END.... -->

	<jsp:include page="../../core/tabnavigation.jsp" > 
		<jsp:param name="submitText" value="${subKey}" />
	</jsp:include>

	</div>
	<hdiits:jsField jsFunction="checkMinAge('${mag1}','${mag3}')" name="checkagemintomax"/>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script> <hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
		
</hdiits:form>
<script type="text/javascript">
if('${lstQualificationflag}' == 'N')
{
	document.getElementById('qualfic').style.display='none';
}
</script>

<c:if test="${changeLocaleFlag == 'Y'}" >
<%
if( request.getSession().getAttribute("locale").equals("en_US"))
{
	request.getSession().setAttribute("locale","gu");
	
}
else
{
	request.getSession().setAttribute("locale","en_US");
	
}
%>
</c:if>

<c:if test="${defaults.isExternal == 'Y'}">
<script>

//document.getElementById("externalInfo").style.display="";

</script>
</c:if>


