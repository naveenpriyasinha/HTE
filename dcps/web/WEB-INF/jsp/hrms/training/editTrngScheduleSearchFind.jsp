<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>

<fmt:setBundle basename="resources.trng.ScheduleSearchLables" var="searchLabels" scope="request"/>

<script type="text/javascript" src="script/hrms/training/editTrngScheduleSearchFind.js"></script>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="hrTrAttendanceDtlList" value="${resultMap.hrTrAttendanceDtlList}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />

<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${searchLabels}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${searchLabels}" />
<fmt:message var="attDtAlert" key="TR.ATTDT_ALERT" bundle="${searchLabels}" />

<hdiits:form name="findeditTrngSchedule" validate="true" method="post" action="./hrms.htm?actionFlag=findEditTrngAttnd">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCH_ATTENDANCE" bundle="${searchLabels}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyleForPopup">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${searchLabels}" key="TR.SEARCHSCHEDULE"/>'>
<table class="datatable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
	<tr>
		<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="TR.SEARCHCRITERIA" bundle="${searchLabels}"/></b></td>
	</tr>
	<tr>
		 <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" id="trainingName" default="%" maxlength="100" onkeypress="return validateText(event, false)"/></td>	
	   	<td class="fieldLabel" width="12%"><hdiits:caption captionid="TR.AttndDt" bundle="${searchLabels}" /></td>
	    <td class="fieldLabel" width="25%"><hdiits:dateTime name="attndDate" captionid="TR.AttndDt" mandatory="true" maxvalue="01/01/9999"  bundle="${searchLabels}"  /></td>
	</tr>
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><hdiits:button type="button" captionid="TR.SEARCH" bundle="${searchLabels}" name="searchButton" onclick="ValidateDate('${attDtAlert}')" style="width :5em" /></td>
	</tr>	
</table>
<c:if test="${hrTrAttendanceDtlList eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${searchLabels}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${hrTrAttendanceDtlList ne null}">
<display:table pagesize="10" name="${hrTrAttendanceDtlList}" id="row" requestURI="" style="width:100%"  >
	<fmt:formatDate value="${row.attendanceDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
	
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
		<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.hrTrTrainingMst.trainingName}*${startDt}*${row.hrTrTrainingMst.trainingMstId}*${row.trngScheduleID.trngScheduleId}"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext" value="${hrTrAttendanceDtlList}" property="hrTrTrainingMst.trainingName" sortable="true" titleKey="TR.TrainingName" headerClass="datatableheader"></display:column>
	
	<display:column class="tablecelltext" sortable="true" titleKey="TR.AttndDt" headerClass="datatableheader" >${startDt}</display:column>
	
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

<hdiits:hidden name="searchSchedule" default="${param.searchSchedule}" />
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>