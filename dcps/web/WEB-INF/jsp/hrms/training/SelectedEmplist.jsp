<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%@page import="com.tcs.sgv.common.utils.DateUtility"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hrms/training/SelectedEmplist.js"></script>

<fmt:setBundle basename="resources.trng.TrainingSearchLables" var="searchLabels" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="trainingTypeLst" value="${resultMap.selectLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="tdate" value="${resultMap.today}" />
<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${searchLabels}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${searchLabels}" />

<hdiits:form name="findSelectedEmp" validate="true" method="post" action="./hdiits.htm?actionFlag=Selectedfind">
 
<fmt:formatDate value="${tdate}" pattern="dd/MM/yyyy" var="sysDate"/>
<hdiits:hidden name="systemDate" default="${sysDate}" /> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				<hdiits:caption captionid="TR.SEARCH_TRAINING" bundle="${searchLabels}"/>
			</a>
		</li>
	</ul>
</div>

<div class="tabcontentstyleForPopup">
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${searchLabels}" key="TR.SEARCH_TRAINING"/>'>
 <table class="datatable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
	<tr>
		<td class="datatableheader" colspan="4" align="center">
			<b><hdiits:caption captionid="TR.SEARCHCRITERIA" bundle="${searchLabels}"/></b>
		</td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType"   bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" id="trainingType" maxlength="20" default="%" onkeypress="return validateText(event, false)"/></td>	
	    <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName"  bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" maxlength="100" id="trainingName" onkeypress="return validateText(event, false)"/></td>	
	</tr>
	 
	<tr>
		<td class="fieldLabel" colspan="4" align="center">
			<hdiits:submitbutton type="button" captionid="TR.SEARCH" bundle="${searchLabels}" name="searchButton" onclick="return checkForm()" style="width :5em" /></td>
	</tr>	
</table>
<c:if test="${trainingTypeLst eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${searchLabels}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${trainingTypeLst ne null}">
<display:table pagesize="10" name="${trainingTypeLst}" id="row" requestURI="" style="width:100%" >
	<fmt:formatDate value="${row.fromDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
	<fmt:formatDate value="${row.toDate}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDt" />
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
		<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.trainingName}*${row.trainingType}*${startDt}*${endDt}*${row.scheduleId}*${row.trainingId}"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext" value="${trainingTypeLst}" property="trainingType" sortable="true" titleKey="TR.TrainingType" headerClass="datatableheader"></display:column>
	<display:column class="tablecelltext" value="${trainingTypeLst}" property="trainingName" sortable="true" titleKey="TR.TrainingName" headerClass="datatableheader" ></display:column>
 	<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" >${startDt}</display:column>
	<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" >${endDt}</display:column>
</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="SUBMIT_BTN" bundle="${searchLabels}" onclick="submitForm('${confirmMsg}')" style="width :5em" />
		</td>
	</tr>
</table>
</c:if>
</div>
</div>

<script type="text/javascript">
	initializetabcontent("maintab")
	init()
</script> 
<hdiits:hidden name="searchTraining" default="${param.searchTraining}" />
</hdiits:form>
