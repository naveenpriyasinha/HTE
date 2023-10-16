 
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.trng.ScheduleSearchLables" var="searchLabels" scope="request"/>
<fmt:setBundle basename="resources.trng.TrainingMstLables"	var="trangLables" scope="request" />

<script language="javascript">
function GoToNewPage() 
{
//alert(${trainingName});
	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?viewName=trngSchFindForTrngAttended","Show",urlstyle); 
}
</script>

<c:set var="title" 	value="${param.searchScheduleTitle}" />
 
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<c:set var="searchVO"  value="${resultMap.searchVO}"/>

<hdiits:fieldGroup titleCaption="${title}" mandatory="true" >
<table width="100%">
		<hdiits:hidden name="trngType"/>	
		<hdiits:hidden name="hdntrngId"/>	
	    <hdiits:hidden name="hdnSchId"/>	
	    <hdiits:hidden name="addressId"/>	
	    <hdiits:hidden name="startDate"/>	
	    <hdiits:hidden name="endDate"/>	
	    <hdiits:hidden name="trngnamE"/>	
		
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingType" bundle="${trangLables}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingType" readonly="true" mandatory="true"   captionid="TR.TrainingType" bundle="${trangLables}"></hdiits:text></td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainingName" bundle="${searchLabels}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtTrainingName" readonly="true" mandatory="true"   captionid="TR.TrainingName" bundle="${searchLabels}"></hdiits:text></td>
	</tr>
	
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ORDERNO" bundle="${searchLabels}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:text name="txtOrderNo" readonly="true" mandatory="true"   captionid="TR.ORDERNO" bundle="${searchLabels}"></hdiits:text></td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.BATCHNO" bundle="${searchLabels}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:number name="txtBatchNo" readonly="true" mandatory="true"   captionid="TR.BATCHNO" bundle="${searchLabels}"></hdiits:number></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.StartDt" bundle="${searchLabels}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtStartDt" disabled="true" captionid="TR.StartDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.EndDt" bundle="${searchLabels}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:dateTime name="dtEndDt" disabled="true" captionid="TR.EndDt" bundle="${searchLabels}" maxvalue="01/01/9999"/></td>
		<td class="fieldLabel" width="25%">
			<hdiits:button type="button" captionid="TR.SEARCH_schedule" bundle="${searchLabels}" name="btnSearchSchedule" onclick="GoToNewPage()" readonly="${readOnly}"/>
		</td>
		 
	</tr>		    	
</table>   
</hdiits:fieldGroup>
