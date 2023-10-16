<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.trng.TrainerMstLables" var="trnrSearchLable" scope="request"/>

<c:set var="mandatory" 	value="${param.mandatory}" />
<c:set var="title" 	value="${param.searchTrainerTitle}" />
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}"/>

<c:set var="searchVO"  value="${resultMap.searchVO}"/>


<script language="javascript">
function GoToNewPage() 
{

	var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top=50,left=200';
	var requeswtURL= '${rootUrl}';
	window.open(requeswtURL+"?actionFlag=getTrainerType","Show",urlstyle); 
}
</script>

<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatory}" >
<table width="100%">
		    	<tr>
					<hdiits:hidden name="hdntrnrId"/>	    	
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerFirstName" bundle="${trnrSearchLable}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrnrFirstName" readonly="true" mandatory="true" validation="txt.isrequired" captionid="TR.TrainerFirstName" bundle="${trnrSearchLable}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerMiddleName" bundle="${trnrSearchLable}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrnrMiddleName" readonly="true" captionid="TR.TrainerMiddleName" bundle="${trnrSearchLable}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerLastName" bundle="${trnrSearchLable}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrnrLastName" readonly="true" captionid="TR.TrainerLastName" bundle="${trnrSearchLable}"></hdiits:text></td>					
				</tr>
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TrainerType" bundle="${trnrSearchLable}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:text name="txtTrnrType" readonly="true" captionid="TR.TrainerType" bundle="${trnrSearchLable}"></hdiits:text></td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%">
						<hdiits:button type="button" captionid="TR.SEARCH_TRAINER" bundle="${trnrSearchLable}" name="btnSearchTrainer" onclick="GoToNewPage()" readonly="${readOnly}"/>
					</td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
</table>   
</hdiits:fieldGroup>
