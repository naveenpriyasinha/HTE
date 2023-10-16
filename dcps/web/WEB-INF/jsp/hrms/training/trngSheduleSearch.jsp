<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.trng.trschLables" var="searchLabels" scope="request"/>

<c:set var="mandatory" 	value="${param.mandatory}" />
<c:set var="title" 	value="${param.searchTrngTitle}" />
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>

<c:set var="searchVO"  value="${resultMap.searchVO}"/>

<script language="javascript">
function GoToNewPage() 
{
	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?viewName=findTrngSchedule","Show",urlstyle); 
}
</script>

<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatory}" >
<table width="100%">
<c:choose>    
	<c:when test="${mandatory eq 'Yes'}">               
		    	<tr>
					<hdiits:hidden name="hdntrngId"/>
					<hdiits:hidden name="hdnschId"/>	    	
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingName" bundle="${searchLabels}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.STARTDATE" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtStartDt" disabled="true" captionid="TR.STARTDATE" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ENDDATE" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtEndDt" disabled="true" captionid="TR.ENDDATE" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
				</tr>
				<tr>
					
					<td class="fieldLabel" width="25%">
						<hdiits:button type="button" captionid="TR.SEARCH_TRAINING_SCHEDULE" bundle="${searchLabels}" name="btnSearchTraining_${trainingName}" onclick="GoToNewPage${trainingName}()" readonly="${readOnly}"/>
					</td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
	</c:when>  		   		
</c:choose> 	
</table>   
</hdiits:fieldGroup>
