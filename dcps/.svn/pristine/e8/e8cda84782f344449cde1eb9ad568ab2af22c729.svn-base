<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>

<fmt:setBundle basename="resources.trng.ScheduleSearchLables" var="searchLabels" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="trngScheduleTypeLst" value="${resultMap.trngScheduleTypeLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />

<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${searchLabels}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${searchLabels}" />

<script language="javascript">
function submitForm()
	{
		var selectedVal;
		var statusFlag = false;
		var radioLength = document.findTrngSchedule.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.findTrngSchedule.rdoLoc.checked)
			{
				var content = document.findTrngSchedule.rdoLoc.value;
				fillInParentWindow(content);
			}
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.findTrngSchedule.rdoLoc.length; rdoIndex++)
			{ 
				if (document.findTrngSchedule.rdoLoc[rdoIndex].checked)
				{
					selectedVal = document.findTrngSchedule.rdoLoc[rdoIndex].value
					statusFlag = true;
				} 
			} 
			if(statusFlag)
			{
				fillInParentWindow(selectedVal);
			}	
			else
			{
				alert('${confirmMsg}');
			}
		}
	}    
function fillInParentWindow(content)
{	
	var trainingArr  = content.split("*");
	var trainingName 	 = trainingArr[0];
	
	var strtDt = trainingArr[1];
	var endDt = trainingArr[2];
	var trngId = trainingArr[3];
	var trngSchId = trainingArr[4];
	
	
	
	
	
	window.opener.parent.document.forms[0].txtTrainingType.value = trainingName;
	
	window.opener.parent.document.forms[0].dtStartDt.value = strtDt;
	window.opener.parent.document.forms[0].dtEndDt.value = endDt;
	window.opener.parent.document.forms[0].hdntrngId.value = trngId;
	window.opener.parent.document.forms[0].hdnschId.value = trngSchId;
	window.close();
	
}	          

function checkForm()
{
		return true;
}

function init()
{
	document.findTrngSchedule.txtTrainingName.focus();
}
</script>

<hdiits:form name="findTrngSchedule" validate="true" method="post" action="./hrms.htm?actionFlag=findTrngSchedule">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCHSCHEDULE" bundle="${searchLabels}"/></a></li>
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
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" id="trainingName" default="%"/></td>	
	   	<td class="fieldLabel" width="12%"><hdiits:caption captionid="TR.StartDt" bundle="${searchLabels}" /></td>
	    <td class="fieldLabel" width="25%"><hdiits:dateTime name="dtStartDate" captionid="TR.StartDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="22%"><hdiits:caption captionid="TR.EndDt" bundle="${searchLabels}" /></td>	
		<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtEndDate" captionid="TR.EndDt" bundle="${searchLabels}"  maxvalue="01/01/9999"/></td>
	</tr>
	
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><hdiits:submitbutton type="button" captionid="TR.SEARCH" bundle="${searchLabels}" name="searchButton" onclick="return checkForm()" style="width :5em" /></td>
	</tr>	
</table>
<c:if test="${trngScheduleTypeLst eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${searchLabels}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${trngScheduleTypeLst ne null}">
<display:table pagesize="10" name="${trngScheduleTypeLst}" id="row" requestURI="" style="width:100%"  >
	<fmt:formatDate value="${row.trngScheduleID.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
	<fmt:formatDate value="${row.trngScheduleID.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDt" />
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
		<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.hrTrTrainingMst.trainingName}*${startDt}*${endDt}*${row.hrTrTrainingMst.trainingMstId}*${row.trngScheduleID.trngScheduleId}"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext" value="${scheduleTypeLst}" property="hrTrTrainingMst.trainingName" sortable="true" titleKey="TR.TrainingName" headerClass="datatableheader"></display:column>
	
	<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" >${startDt}</display:column>
	<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" >${endDt}</display:column>
</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="SUBMIT_BTN" bundle="${searchLabels}" onclick="submitForm()" style="width :5em" />
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