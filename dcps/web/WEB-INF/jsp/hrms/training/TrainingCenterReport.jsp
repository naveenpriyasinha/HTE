
<%try{ %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ include file="../../core/include.jsp"%>
<script type="text/javascript"   
   src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
         src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
<fmt:setBundle basename="resources.trng.TrainingCenter" var="trngCenterLabel"
	scope="request" />
 <script type="text/javascript" src="script/hrms/training/TrainingCenterReport.js"></script>
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="chkifTrainingCenter" value="${resultMap.chkifTrainingCenter}" />
<c:set var="trainingcenterLst" value="${resultMap.trainingcenterLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="scheduleLst" value="${resultMap.scheduleLst}" />
<c:set var="locCode" value="${resultMap.locCode}" />

<script type="text/javascript">
var chk=${chkifTrainingCenter};
</script>



<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="scheduleLst" value="${resultMap.scheduleLst}" />


<fmt:message bundle="${trngCenterLabel}" key="TR.SELECT" var="Select"></fmt:message>
<fmt:message bundle="${trngCenterLabel}" key="TR.STARTDATEVAL" var="strtDateVal"></fmt:message>
<fmt:message bundle="${trngCenterLabel}" key="TR.ENDDATEVAL" var="EndDateVal"></fmt:message>
<hdiits:form name="trainingCenterDetail"  validate="true"  method="post" >

<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1">
  <hdiits:caption captionid="TR.SCHEDULE" bundle="${trngCenterLabel}"/>  </a></li>				
   </ul>
</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0" >
	 <!-- table for group and point -->
	 <hdiits:hidden name="ckhIfTrainingCenter" default="${chkifTrainingCenter}" />
			<table class="tabtable">
			<c:if test="${chkifTrainingCenter eq false}">
				<tr>
					<td class="fieldLabel" width="26%"><hdiits:caption captionid="TR.TRAININGCENTER"  bundle="${trngCenterLabel}"/></td>
					<td class="fieldLabel" width="25%" >
						<hdiits:select sort="false" name="trainingCenterForReport" condition="selReq()" id="trngCntr"  captionid="TR.TRAININGCENTER"  bundle="${trngCenterLabel}" mandatory="true"  validation="sel.isrequired">
							<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/></hdiits:option>
							<c:forEach var="trngcenterLoop" items="${trainingcenterLst}">
								<hdiits:option value="${trngcenterLoop.locId}"> <c:out value="${trngcenterLoop.locName}"></c:out></hdiits:option>
							</c:forEach>
								<hdiits:option value="others"> <hdiits:caption captionid="TR.EXTERNAL" bundle="${trngCenterLabel}" captionLang="single"/></hdiits:option>
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="24%"></td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.STARTDATE" bundle="${trngCenterLabel}" /></td>
				<td class="fieldLabel" width="25%"><hdiits:dateTime    name="strtDate" onblur="checkstrtDate('${strtDateVal}')" captionid="TR.STARTDATE" bundle="${trngCenterLabel}" maxvalue="01/01/9999"/></td>
		
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ENDDATE" bundle="${trngCenterLabel}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:dateTime   name="endDate" onblur="checkEndDate('${EndDateVal}')" captionid="TR.ENDDATE" bundle="${trngCenterLabel}" maxvalue="01/01/9999"/></td>
			</tr>
		</table>
		
		<CENTER><hdiits:button name="submitReportForm" bundle="${trngCenterLabel}" captionid="TR.SEARCH" type="button" onclick="submitForm123()"  /></CENTER> 
		
		
		<br>
		<br>
		
			<c:if test="${scheduleLst eq null and isRecordFound eq 'none'}">
		<table class="tabtable"  >
			<tr>
				 <td class="fieldLabel" colspan="4" align="center" ><center><b><fmt:message bundle="${trngCenterLabel}" key="TR.NOTFOUND" /></b></center></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${scheduleLst ne null}">
	<display:table name="${scheduleLst}" id="row" requestURI="">
		<fmt:formatDate value="${row.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
		<fmt:formatDate value="${row.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDt" />
		<c:set var="trngName" value="${row.hrTrTrainingMst.trainingName}"> </c:set>
		<c:choose>
			<c:when test="${row.batchSize ne 0}"><c:set value="${row.batchSize}" var="btchsize"></c:set>   </c:when>
			<c:otherwise><c:set value=" " var="btchsize"></c:set>  </c:otherwise>
		</c:choose>
		<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
			<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.hrTrTrainingMst.trainingMstId}*${row.trngScheduleId}*${row.startDt}*${row.endDt}*${locCode}"></hdiits:radio>
		</display:column>
		<display:column class="tablecelltext" sortable="true" titleKey="TR.TRAININGNAME" headerClass="datatableheader">${trngName}</display:column>
		<display:column class="tablecelltext" sortable="true" titleKey="TR.BATCHSIZE" headerClass="datatableheader" >${btchsize}</display:column>
		<display:column class="tablecelltext" value="${row.hrTrTrainingMst.eligibilityCriteria}" sortable="true" titleKey="TR.ELICRI" headerClass="datatableheader" ></display:column>
		<display:column class="tablecelltext" value="${scheduleLst}" property="director" sortable="true" titleKey="TR.DIRECTOR"  headerClass="datatableheader" ></display:column>	
		<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" >${startDt}</display:column>
		<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" >${endDt}</display:column>
	</display:table>
	
	
	
	</c:if>
	<div id="reportGenerateButton" style="display:none">
		<table class="tabtable"  >
			<tr>
				<td>
				 <center><hdiits:button type="button" name="submitButton" captionid="TR.GENERATE" bundle="${trngCenterLabel}" onclick="submitMainForm()"  /></center>
				 </td>
			</tr>
		</table>
	</div>	
</div>

</div>

<c:if test="${isRecordFound eq 'yes'}">
	<script>
	document.getElementById("reportGenerateButton").style.display="";
	</script>
</c:if>

<script>
function mandatoryCheck()
{
	return '${chkifTrainingCenter}';
}

</script>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

<% }catch(Exception e){
	e.printStackTrace();
}

%>