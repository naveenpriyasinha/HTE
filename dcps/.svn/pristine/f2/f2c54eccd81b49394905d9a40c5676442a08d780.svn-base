<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.trng.TrainingSearchLables" var="searchLabels" scope="request"/>

<c:set var="mandatory" 	value="${param.mandatory}" />
<c:set var="title" 	value="${param.searchTrainingTitle}" />
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<c:set var="funcName" value='<%=request.getParameter("functionName")%>'></c:set>
<c:set var="searchVO"  value="${resultMap.searchVO}"/>

<script language="javascript">
function GoToNewPageTraining() 
{
	var functionName='<%=request.getParameter("functionName")%>';
	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?actionFlag=findTraining&functionName="+functionName,"Show",urlstyle); 
	
}
</script>
<hdiits:hidden name="func" default=""/>
<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatory}" >
<table width="100%">

		    	<tr>
					<hdiits:hidden name="hdntrngId" id="hdntrngId"/>	
			    	<hdiits:hidden name="hdnschId" id="hdnschId"/>	
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingType" bundle="${searchLabels}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainingName" bundle="${searchLabels}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.NO_OF_PERSON" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtNoOfPerson" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.NO_OF_PERSON" bundle="${searchLabels}"></hdiits:text></td>					
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.StartDt" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtStartDt" disabled="true" captionid="TR.StartDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.EndDt" bundle="${searchLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtEndDt" disabled="true" captionid="TR.EndDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
					<td class="fieldLabel" width="25%">
						<hdiits:button type="button" captionid="TR.SEARCH_TRAINING" bundle="${searchLabels}" name="btnSearchTraining" onclick="GoToNewPageTraining()" readonly="${readOnly}"/>
					</td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
</table>   
</hdiits:fieldGroup>
