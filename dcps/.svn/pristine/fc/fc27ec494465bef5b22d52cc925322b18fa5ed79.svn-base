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

<fmt:setBundle basename="resources.trng.TimeTableofSchedule" var="reportLabels"
	scope="request" />
<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="chkifTrainingCenter" value="${resultMap.chkifTrainingCenter}" />
<c:set var="trainingcenterLst" value="${resultMap.trainingcenterLst}" />
<c:set var="isRecordFound" value="${resultMap.isRecordFound}" />
<c:set var="scheduleLst" value="${resultMap.scheduleLst}" />
<c:set var="trainingTypes" value="${resultMap.trainingTypes}" />

<c:set var="trainingCenterForReportSel" value="${resultMap.trainingCenterForReport}" />
<c:set var="trainingCourseSel" value="${resultMap.trainingCourse}" />
<c:set var="strtDateSel" value="${resultMap.strtDate}" />
<c:set var="endDateSel" value="${resultMap.endDate}" />
<c:set var="trainingclassSel" value="${resultMap.trainingclass}" />
<c:set var="trngTypeLookupid" value="${resultMap.trngTypeLookupid}" />
<c:set var="classLst" value="${resultMap.classLst}" />
<c:set var="trainingList" value="${resultMap.trainingList}" />

<script type="text/javascript" src="script/hrms/training/ScheduleTimeTable.js"></script>
 
<script>

function getContextPath()
{
	return '${contextPath}'
}
</script>

<hdiits:form name="scheduleSelect"   validate="true"  method="post"  >
<fmt:message bundle="${reportLabels}" key="TR.STARTDATEVAL" var="strtDateVal"></fmt:message>
<fmt:message bundle="${reportLabels}" key="TR.ENDDATEVAL" var="EndDateVal"></fmt:message>
<div id="tabmenu">
   <ul id="maintab" class="shadetabs" ><li  class="selected" ><a href="#"  rel="tcontent1"  >
   <b><hdiits:caption  captionid="TR.SCHEDULE" bundle="${reportLabels}"/></b></a></li>
   </ul>
</div>
<div class="tabcontentstyle"> 
	<div id="tcontent1" class="tabcontent" tabno="0" >
	 <!-- table for group and point -->
	 <hdiits:hidden name="chkifTrainingCenter" default="${chkifTrainingCenter}" />
	 <fmt:message var="confirmMsg" key="TR.ALERT_MSG" bundle="${reportLabels}" />
	 <fmt:message var="select" key="TR.SELECT" bundle="${reportLabels}" />
		 <div id="showTrainingcenter" style="display:none">
			<table class="tabtable">
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGCENTER"  bundle="${reportLabels}" /></td>
					<td class="fieldLabel" width="30%">
					<hdiits:select name="trainingCenterForReport" sort="false" validation="sel.isrequired" condition="selReq()" id="trngCntr" caption="Training Center" mandatory="true" >
						<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/> </hdiits:option>
						<c:forEach var="trngcenterLoop" items="${trainingcenterLst}">
						<c:choose>
							<c:when test="${trngcenterLoop.locId eq trainingCenterForReportSel}">
								<hdiits:option value="${trngcenterLoop.locId}" selected="true"> <c:out value="${trngcenterLoop.locName}"></c:out></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${trngcenterLoop.locId}"> <c:out value="${trngcenterLoop.locName}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
						</c:forEach>
					</hdiits:select>
					</td>
					<td class="fieldLabel" width="20%"></td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
			</table>
		</div>
			<table  class="tabtable">
				<tr>
					
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGTYPE"  bundle="${reportLabels}"/></td>
					<td class="fieldLabel" width="25%">
					<hdiits:select name="trainingTypeLookupid" sort="false" id="trainingTypeLookupid" captionid="TR.TRAININGTYPE"  bundle="${reportLabels}"  onchange="getTrainingNames(this)">
						<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/> </hdiits:option>
						<c:forEach var="trngType" items="${trainingTypes}">
						<c:choose>
							<c:when test="${trngType.lookupId eq trngTypeLookupid}">
								<hdiits:option value="${trngType.lookupId}" selected="true"> <c:out value="${trngType.lookupDesc}"></c:out></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${trngType.lookupId}"> <c:out value="${trngType.lookupDesc}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
						
							
						</c:forEach>
					</hdiits:select>
					</td>
					
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGCOURSE"  bundle="${reportLabels}" /></td>
					<td class="fieldLabel" width="25%">
					<hdiits:select name="trainingCourse" sort="false" id="trngcourse" caption="Training Course" onchange="getTrainingclass(this)">
						<hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></hdiits:option>
						<c:forEach var="trngCourseLoop" items="${trainingList}">
						<c:choose>
							<c:when test="${trngCourseLoop.trainingMstId eq trainingCourseSel}">
								<hdiits:option value="${trngCourseLoop.trainingMstId}" selected="true"> <c:out value="${trngCourseLoop.trainingName}"></c:out></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${trngCourseLoop.trainingMstId}"> <c:out value="${trngCourseLoop.trainingName}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
						
						</c:forEach>
					</hdiits:select>
					</td>
					
				</tr>
				<tr>
					
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.CLASSNO"  bundle="${reportLabels}"/></td>
					<td class="fieldLabel" width="25%">
					<hdiits:select name="trainingclass" sort="false" id="trainingclass" caption="Training class" >
						<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/>  </hdiits:option>
						<c:forEach var="trngClass" items="${classLst}">
						<c:choose>
							<c:when test="${trngClass eq trainingclassSel}">
								<hdiits:option value="${trngClass}" selected="true"> <c:out value="${trngClass}"></c:out></hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${trngClass}"> <c:out value="${trngClass}"></c:out></hdiits:option>
							</c:otherwise>
						</c:choose>
						
							
						</c:forEach>
					</hdiits:select>
					</td>
				</tr>
				
		</table>
		<table class="tabtable">
			<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.STARTDATE"  bundle="${reportLabels}"/></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime  name="strtDate"  captionid="TR.STARTDATE"  bundle="${reportLabels}" onblur="checkstrtDate('${strtDateVal}')" maxvalue="01/01/9999"/></td>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.ENDDATE"  bundle="${reportLabels}" /></td>
					<td class="fieldLabel" width="25%"><hdiits:dateTime  name="endDate" captionid="TR.ENDDATE"  bundle="${reportLabels}"   onblur="checkEndDate('${EndDateVal}')" maxvalue="01/01/9999"/></td>
			</tr>
		</table>
		
	
		<CENTER> 
		<hdiits:button type="button" name="smtButton" captionid="TR.SEARCH" bundle="${reportLabels}" onclick="submitForm('${chkifTrainingCenter}')"  /></CENTER>
	
	<br>
	<br>
	<br>
	<c:if test="${scheduleLst eq null and isRecordFound eq 'none'}">
		<table class="tabtable" >
			<tr>
				<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${reportLabels}" key="TR.NOTFOUND" /></b></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${scheduleLst ne null}">
	<display:table name="${scheduleLst}" id="row" requestURI="">
		<fmt:formatDate value="${row.startDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="startDt" />
		<fmt:formatDate value="${row.endDt}" pattern="dd/MM/yyyy" dateStyle="medium" var="endDt" />
		<display:column class="tablecelltext" titleKey="TR.SELECT" headerClass="datatableheader" >
			<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.trngScheduleId}"></hdiits:radio>
		</display:column>
		<display:column class="tablecelltext" value="${scheduleLst}" property="batchNo" sortable="true" titleKey="TR.BATCHNO" headerClass="datatableheader"></display:column>
		<display:column class="tablecelltext" value="${scheduleLst}" property="batchSize" sortable="true" titleKey="TR.BATCHSIZE" headerClass="datatableheader" ></display:column>
		<display:column class="tablecelltext" value="${row.hrTrTrainingMst.eligibilityCriteria}" sortable="true" titleKey="TR.ELICRI" headerClass="datatableheader" ></display:column>
		<display:column class="tablecelltext" value="${scheduleLst}" property="director" sortable="true" titleKey="TR.DIRECTOR" headerClass="datatableheader" ></display:column>	
		<display:column class="tablecelltext" sortable="true" titleKey="TR.StartDt" headerClass="datatableheader" >${startDt}</display:column>
		<display:column class="tablecelltext" sortable="true" titleKey="TR.EndDt" headerClass="datatableheader" >${endDt}</display:column>
	</display:table>
	<table align="center"  border="0">
		<tr>
			<td>
				<hdiits:button type="button" name="submitButton" captionid="TR.SUBMIT_BTN" bundle="${reportLabels}" onclick="submitMainForm('${confirmMsg}')"  />
			</td>
		</tr>
	</table>
</c:if>
	
</div>
<c:if test="${isRecordFound eq 'none' or scheduleLst ne null }">
<script>
hideProgressbar();
</script>
</c:if>
<script>
function mandatoryCheck()
{
	return '${chkifTrainingCenter}';
}
var chk=${chkifTrainingCenter};
if(!chk)
{
	document.getElementById("showTrainingcenter").style.display="";
	
}
if(chk)
{
	document.getElementById("trngCntr").value="notAllowed";
}

</script>

<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>