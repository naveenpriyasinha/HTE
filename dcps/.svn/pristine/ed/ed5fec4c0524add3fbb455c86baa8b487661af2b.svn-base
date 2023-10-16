<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>

<fmt:setBundle basename="resources.trng.TrainingSearchLables" var="searchLabels" scope="request"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="trainingTypeLst" value="${resultMap.trainingTypeLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="functionName" value='<%=request.getParameter("functionName")%>'/>
<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${searchLabels}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${searchLabels}" />

<script language="javascript">
function submitForm()
	{

		var selectedVal;
		var statusFlag = false;
		var radioLength = document.findTraining.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.findTraining.rdoLoc.checked)
			{
				var content = document.findTraining.rdoLoc.value;
				fillInParentWindow(content);
			}
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.findTraining.rdoLoc.length; rdoIndex++)
			{ 
				if (document.findTraining.rdoLoc[rdoIndex].checked)
				{
					selectedVal = document.findTraining.rdoLoc[rdoIndex].value
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
	var trainingNm 	 = trainingArr[0];
	var trainingType = trainingArr[1];
	var strtDt = trainingArr[2];
	var endDt = trainingArr[3];
	var noofPerson = trainingArr[4];
	var schId = trainingArr[5];
	var trngId = trainingArr[6];
	
	window.opener.parent.document.forms[0].txtTrainingType.value = trainingType;
	window.opener.parent.document.forms[0].txtTrainingName.value = trainingNm;
	window.opener.parent.document.forms[0].dtStartDt.value = strtDt;
	window.opener.parent.document.forms[0].dtEndDt.value = endDt;
	window.opener.parent.document.forms[0].txtNoOfPerson.value = noofPerson;
	window.opener.parent.document.forms[0].hdnschId.value = schId;
	window.opener.parent.document.forms[0].hdntrngId.value = trngId;
	
	var functionName='<%=request.getParameter("functionName")%>'
		if(functionName != null)
		{
			window.close(); 
			window.opener.${functionName}();
			window.focus();
		}
		else
		{
				window.close(); 
		}
		
}	          

function checkForm()
{
		return true;
}

function init()
{
	document.findTraining.trainingType.focus();
}
</script>

<hdiits:form name="findTraining" validate="true" method="post" action="./hrms.htm?actionFlag=findTraining">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCH_TRAINING" bundle="${searchLabels}"/></a></li>
	</ul>
</div>
<hdiits:hidden name="funct" default="${functionName}"/>

<div class="tabcontentstyleForPopup">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${searchLabels}" key="TR.SEARCH_TRAINING"/>'>
<table class="datatable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
	<tr>
		<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="TR.SEARCHCRITERIA" bundle="${searchLabels}"/></b></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType" bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" id="trainingType" default="%" maxlength="100" onkeypress="return validateText(event, false)"/></td>	
	    <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" id="trainingName" maxlength="100" onkeypress="return validateText(event, false)"/></td>	
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.NO_OF_PERSON" bundle="${searchLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:number name="txtNoOfPerson" id="noOfPerson" maxlength="3"/></td>	
		<td class="fieldLabel" width="12%"><hdiits:caption captionid="TR.StartDt" bundle="${searchLabels}" />
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
		<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.trainingName}*${row.trainingType}*${startDt}*${endDt}*${row.noOfPerson}*${row.scheduleId}*${row.trainingId}"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext" value="${trainingTypeLst}" property="trainingType" sortable="true" titleKey="TR.TrainingType" headerClass="datatableheader"></display:column>
	<display:column class="tablecelltext" value="${trainingTypeLst}" property="trainingName" sortable="true" titleKey="TR.TrainingName" headerClass="datatableheader" ></display:column>
	<display:column class="tablecelltext" value="${trainingTypeLst}" property="noOfPerson" sortable="true" titleKey="TR.NO_OF_PERSON" headerClass="datatableheader" ></display:column>	
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
<hdiits:hidden name="searchTraining" default="${param.searchTraining}" />
</hdiits:form>