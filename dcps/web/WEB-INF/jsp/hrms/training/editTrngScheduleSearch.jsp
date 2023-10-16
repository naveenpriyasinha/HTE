<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.trng.trschLables" var="searchLabels" scope="request"/>

<c:set var="mandatory" 	value="${param.mandatory}" />
<c:set var="title" 	value="${param.searchAttendanceTitle}"/>

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>

<c:set var="searchVO"  value="${resultMap.searchVO}"/>

<script language="javascript">
function GoToNewPage() 
{
	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?viewName=findeditTrngSchedule","Show",urlstyle); 
}
</script>

<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatory}">
<table width="100%">
<c:choose>    
	<c:when test="${mandatory eq 'Yes'}">               
		    	<tr>
					<hdiits:hidden name="hdntrngId"/>	 
					<hdiits:hidden name="hdnschId"/>	    	
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingName" bundle="${searchLabels}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ATTENDANCEDATE" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime name="attndDt" disabled="true"  mandatory="true" maxvalue="01/01/9999" validation="txt.isrequired" captionid="TR.ATTENDANCEDATE" bundle="${searchLabels}" /></td>
					
				</tr>
				<tr>
					
					<td class="fieldLabel" width="25%">
						<hdiits:button type="button" captionid="TR.SEARCH_ATTENDANCE" bundle="${searchLabels}" name="btnSearchTraining_${trainingName}" onclick="GoToNewPage${trainingName}()" readonly="${readOnly}"/>
					</td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
	</c:when>  		   		
</c:choose> 	
</table>   
</hdiits:fieldGroup>
