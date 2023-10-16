<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="script/common/calendar.js"></script>

<fmt:setBundle basename="resources.trng.TrainerMstLables" var="trnrSearchLable" scope="request"/>



<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="trainerTypeLst" value="${resultMap.trainerTypeLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="lstTrainerType" value="${resultMap.lstTrainerType}" />


<fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${trnrSearchLable}" />
<fmt:message var="alertMsg" key="TR.SEARCH_CRITERIA_ALERT" bundle="${trnrSearchLable}" />

<script language="javascript">
function submitForm()
	{
		var selectedVal;
		var statusFlag = false;
		var radioLength = document.findTrainer.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.findTrainer.rdoLoc.checked)
			{
				var content = document.findTrainer.rdoLoc.value;
				fillInParentWindow(content);
			}
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.findTrainer.rdoLoc.length; rdoIndex++)
			{ 
				if (document.findTrainer.rdoLoc[rdoIndex].checked)
				{
					selectedVal = document.findTrainer.rdoLoc[rdoIndex].value
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
	var trainerArr  = content.split("*");
	var firstName 	 = trainerArr[0];
	var middleName = trainerArr[1];
	var lastName = trainerArr[2];
	var trainerId = trainerArr[3];
	var trnrType = trainerArr[4];
	
	window.opener.parent.document.forms[0].txtTrnrFirstName.value = firstName;
	window.opener.parent.document.forms[0].txtTrnrMiddleName.value = middleName;
	window.opener.parent.document.forms[0].txtTrnrLastName.value = lastName;
	window.opener.parent.document.forms[0].hdntrnrId.value = trainerId;
	window.opener.parent.document.forms[0].txtTrnrType.value = trnrType;
	window.close();
	
}	          

function checkForm()
{
		return true;
}

function init()
{
	document.findTrainer.txtTrainerFirstName.focus();
}
</script>
<!--  aaa -->
<hdiits:form name="findTrainer" validate="true" method="post" action="./hrms.htm?actionFlag=findTrainer">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SEARCH_TRAINER" bundle="${trnrSearchLable}"/></a></li>
	</ul>
</div>

<div class="tabcontentstyleForPopup">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${trnrSearchLable}" key="TR.SEARCH_TRAINER"/>'>
<table class="datatable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
	<tr>
		<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="TR.SEARCHCRITERIA" bundle="${trnrSearchLable}"/></b></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerFirstName" bundle="${trnrSearchLable}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainerFirstName" id="trainerFirstName" default="%"/></td>	
	    <td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerMiddleName" bundle="${trnrSearchLable}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainerMiddleName" id="trainerMiddleName"/></td>	
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerLastName" bundle="${trnrSearchLable}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainerLastName" id="trainerLastName"/></td>	
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerType" bundle="${trnrSearchLable}"/></td>
		<td class="fieldLabel" width="25%">
		<hdiits:select name="selTrnrType" captionid="TR.TrainerType" bundle="${trnrSearchLable}">
		<option value="-1">----select----</option>
		<c:forEach  var="trnrType" items="${lstTrainerType}">> 
		<option value='<c:out value="${trnrType.lookupId}"/>'><c:out value="${trnrType.lookupDesc}" /></option>
		</c:forEach>
		</hdiits:select>
		
		</td>
	</tr>
	
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><hdiits:submitbutton type="button" captionid="TR.SEARCH_TRAINER" bundle="${trnrSearchLable}" name="searchButton" onclick="return checkForm()" style="width :5em" /></td>
	</tr>	
</table>
<c:if test="${trainingTypeLst eq null and isRecordFound eq 'none'}">
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${trnrSearchLable}" key="TR.TRNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${trainerTypeLst ne null}">
<display:table pagesize="10" name="${trainerTypeLst}" id="row" requestURI="" style="width:100%" >
	
	<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'In-House'}">
	
	<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.orgEmpId.empFname}*${row.orgEmpId.empMname}*${row.orgEmpId.empLname}*${row.trainerId}*${row.cmnLookupMstTrainertypeLookupId.lookupDesc}"></hdiits:radio>
	</c:if>
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'Visitor'}">
	<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.firstName}*${row.middleName}*${row.lastName}*${row.trainerId}*${row.cmnLookupMstTrainertypeLookupId.lookupDesc}"></hdiits:radio>
	
	</c:if>
	
		
	</display:column>
	
	<%/* %>
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'Visitor'}">
	
	<display:column class="tablecelltext" value="${trainerTypeLst}" property="firstName" sortable="true" titleKey="TR.TrainerFirstName" headerClass="datatableheader"></display:column>
	<display:column class="tablecelltext" value="${trainerTypeLst}" property="middleName" sortable="true" titleKey="TR.TrainerMiddleName" headerClass="datatableheader" ></display:column>
	<display:column class="tablecelltext" value="${trainerTypeLst}" property="lastName" sortable="true" titleKey="TR.TrainerLastName" headerClass="datatableheader" ></display:column>	
	
	</c:if>
	
	<%*/ %>
	
	
	
	<display:column class="tablecelltext" sortable="true" titleKey="TR.TrainerFirstName" headerClass="datatableheader">
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'In-House'}">
	${row.orgEmpId.empFname}
	</c:if>
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'Visitor'}">
	${row.firstName}
	</c:if>
	</display:column>
	
	
	 <display:column class="tablecelltext" sortable="true" titleKey="TR.TrainerMiddleName" headerClass="datatableheader" >
	 <c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'In-House'}">
	${row.orgEmpId.empMname}
	</c:if>
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'Visitor'}">
	${row.middleName}
	</c:if>
	 
	 
	 </display:column>
	
	
	<display:column class="tablecelltext" sortable="true" titleKey="TR.TrainerLastName" headerClass="datatableheader" >
	 <c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'In-House'}">
	${row.orgEmpId.empLname}
	</c:if>
	<c:if test="${row.cmnLookupMstTrainertypeLookupId.lookupName eq 'Visitor'}">
	${row.lastName}
	</c:if>
	
	</display:column>
	
	
	
	<display:column class="tablecelltext" value="${trainerTypeLst}" property="cmnLookupMstTrainertypeLookupId.lookupDesc" sortable="true" titleKey="TR.TrainerType" headerClass="datatableheader" ></display:column>
</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="SUBMIT_BTN" bundle="${trnrSearchLable}" onclick="submitForm()" style="width :5em" />
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
</hdiits:form>