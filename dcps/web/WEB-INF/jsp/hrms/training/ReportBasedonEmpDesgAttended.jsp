<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
<script type="text/javascript" src="script/hrms/training/ReportBasedonEmpDesgAttended.js"></script>
<fmt:setBundle basename="resources.trng.ReportOnDesgAttended" var="trngAttendedLabel"
	scope="request" />

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="chkifTrainingCenter" value="${resultMap.chkifTrainingCenter}" />
<c:set var="trainingcenterLst" value="${resultMap.trainingcenterLst}" />


<hdiits:form name="personDetail"   validate="true"  method="post" action="hdiits.htm?">
<fmt:message bundle="${trngAttendedLabel}" key="TR.STARTDATEVAL" var="strtDateVal"></fmt:message>
<fmt:message bundle="${trngAttendedLabel}" key="TR.ENDDATEVAL" var="EndDateVal"></fmt:message>
<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1">
   <hdiits:caption captionid="TR.REPORT" bundle="${trngAttendedLabel}"/>  </a></li>				
   </ul>
</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0" >
	 <!-- table for group and point -->
	 <hdiits:hidden name="trainingCenter" default="${chkifTrainingCenter}" />
	 <fmt:message bundle="${trngAttendedLabel}" key="TR.EXTERNAL" var="extr"> </fmt:message>
	 <fmt:message bundle="${trngAttendedLabel}" key="TR.SELECT" var="Select"></fmt:message>
	 
			<table class="tabtable">
			<c:if test="${chkifTrainingCenter eq 'false'}">
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGCENTER" bundle="${trngAttendedLabel}"/></td>
					<td class="fieldLabel" width="25%" align="left">
						<hdiits:select name="trainingCenterForReport" id="trngCntr" captionid="TR.TRAININGCENTER" bundle="${trngAttendedLabel}" mandatory="true" condition="selReq()" validation="sel.isrequired">
						<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/> </hdiits:option>
						<c:forEach var="trngcenterLoop" items="${trainingcenterLst}">
						<hdiits:option value="${trngcenterLoop.locId}"> <c:out value="${trngcenterLoop.locName}"></c:out></hdiits:option>
						</c:forEach>
						<hdiits:option value="Other"><c:out value="${extr}"></c:out></hdiits:option>
						</hdiits:select>
					</td>
					<td class="fieldLabel" width="25%"></td>
					<td class="fieldLabel" width="25%"></td>
				</tr>
			</c:if>
			
			<tr>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.STRTDATE" bundle="${trngAttendedLabel}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:dateTime onblur="checkstrtDate('${strtDateVal}')"  captionid="TR.STRTDATE" bundle="${trngAttendedLabel}" maxvalue="01/01/9999" name="strtDate" /></td>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.NDDATE" bundle="${trngAttendedLabel}"/></td>
				<td class="fieldLabel" width="25%"><hdiits:dateTime onblur="checkEndDate('${EndDateVal}')"  captionid="TR.NDDATE" bundle="${trngAttendedLabel}" maxvalue="01/01/9999" name="endDate"  /></td>
			</tr>
		</table>
		<br>
		
		<CENTER><hdiits:button  type="button"  name="smtButton"  onclick="submitForm('${chkifTrainingCenter}')" captionid="TR.SUBMITQUERY" bundle="${trngAttendedLabel}" /></CENTER>
</div>


</div>



<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<script>
function checkMandatory()
{
	return '${chkifTrainingCenter}';
}
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>