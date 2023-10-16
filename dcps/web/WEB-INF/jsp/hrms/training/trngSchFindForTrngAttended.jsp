<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="<c:url value="script/hrms/training/trngSchFindForTrngAttended.js"/>"></script>

<fmt:setBundle basename="resources.trng.TrainingMstLables"	var="trangLables" scope="request" />
<fmt:setBundle basename="resources.trng.ScheduleSearchLables" var="searchLabels" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="scheduleTypeLst" value="${resultMap.selectLst}" />
<c:set var="trngRecord" value="${resultMap.TrngRecord}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />

<fmt:message var="msg" key="TR.ALERT_MSG" bundle="${searchLabels}"/>
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${searchLabels}" />

<hdiits:form name="findSchedule" validate="true" method="post" action="./hrms.htm?actionFlag=findScheduleForTrngAttended">
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCHSCHEDULE" bundle="${searchLabels}"/></a></li>
		</ul>
	</div>

<div class="tabcontentstyleForPopup">

<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${searchLabels}" key="TR.SEARCHSCHEDULE"/>'>
		<table class="datatable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
			<tr>
				<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="TR.SEARCHCRITERIA" bundle="${searchLabels}"/></b></td>
			</tr>
			<tr>	
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ORDERNO" bundle="${searchLabels}" /></td>
				<td class="fieldLabel" width="25%"><hdiits:text name="txtOrderNo" id="orderNo" default="%" maxlength="50" onkeypress="return validateText(event, false)"/></td>	
			    <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.BATCHNO" bundle="${searchLabels}" /></td>
				<td class="fieldLabel" width="25%"><hdiits:number name="txtBatchNo" id="batchNo" maxlength="3"/></td>	
			</tr>
			<tr>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType"   bundle="${trangLables}" /></td>
				<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" id="trainingType" maxlength="20" default="" onkeypress="return validateText(event, false)"/></td>		   
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${trangLables}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName"  validation="txt.isrequired" captionid="TR.TrainingName" bundle="${searchLabels}" onkeypress="return validateText(event, false)"></hdiits:text></td>			 
			</tr>
			
			<tr>
				<td class="fieldLabel" width="12%"><hdiits:caption captionid="TR.StartDt" bundle="${searchLabels}" /></td>
			    <td class="fieldLabel" width="25%"><hdiits:dateTime name="dtStartDate" captionid="TR.StartDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>	 
				<td class="fieldLabel" width="22%"><hdiits:caption captionid="TR.EndDt" bundle="${searchLabels}" /></td>	
				<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtEndDate" captionid="TR.EndDt" bundle="${searchLabels}"  maxvalue="01/01/9999"/></td>
			</tr>
			<tr>
				<td class="fieldLabel" colspan="4" align="center"><hdiits:submitbutton type="button" captionid="TR.SEARCH" bundle="${searchLabels}" name="searchButton" onclick="return checkForm()" style="width :5em" /></td>
			</tr>	
		</table>

<c:if test="${scheduleTypeLst eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${searchLabels}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${scheduleTypeLst ne null}">
<display:table pagesize="10" name="${scheduleTypeLst}" id="row" requestURI="" style="width:100%"  >
	<fmt:formatDate value="${row.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="fromDate" />
	<fmt:formatDate value="${row.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="toDate" />
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
		<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.orderNo}*${row.batchNo}*${fromDate}*${toDate}*${row.organizer}*${row.trngScheduleId}*${row.hrTrTrainingMst.trainingName}*${row.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupName}*${row.hrTrTrainingMst.trainingMstId}*${row.cmnAddressMst.addressId}"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext" value="${scheduleTypeLst}" property="orderNo" sortable="true" titleKey="TR.ORDERNO" headerClass="datatableheader"></display:column>
	<display:column class="tablecelltext" value="${scheduleTypeLst}" property="batchNo" sortable="true" titleKey="TR.BATCHNO"    headerClass="datatableheader" ></display:column>
	<display:column class="tablecelltext" value="${scheduleTypeLst}" property="organizer" sortable="true" titleKey="TR.ORGANISER" headerClass="datatableheader" ></display:column>	
	<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" >${fromDate}</display:column>
	<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" >${toDate}</display:column>
</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="SUBMIT_BTN" bundle="${searchLabels}" onclick="submitForm('${msg}')" style="width :5em" />
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
</hdiits:form>


