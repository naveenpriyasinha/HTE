<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.trng.TrainingSearchLables" var="searchLabels" scope="request"/>

<c:set var="mandatory" 	value="${param.mandatory}" />
<c:set var="title" 	value="${param.searchTrainingTitle}" />
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>

<c:set var="searchVO"  value="${resultMap.searchVO}"/>

<script language="javascript">
function GoToNewPageTraining() 
{

	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?viewName=findTrainingMst","Show",urlstyle); 
}
</script>

<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatory}" >
<table width="100%">
         
		    	<tr>
					<hdiits:hidden name="hdntrngId"/>	    	
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingType" bundle="${searchLabels}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingName" bundle="${searchLabels}"></hdiits:text></td>
				</tr>
				<tr>
					<td class="fieldLabel" width="25%">
						<hdiits:button type="button" captionid="TR.SEARCH_TRAINING" bundle="${searchLabels}" name="btnSearchTraining" onclick="GoToNewPageTraining()" readonly="${readOnly}"/>
					</td>
					<td class="fieldLabel" width="25%"></td>
				</tr>

</table>   
</hdiits:fieldGroup>
