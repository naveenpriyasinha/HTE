<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<script type="text/javascript" src="script/hrms/training/CenterSelectionForScheduleReport.js"></script>

<fmt:setBundle basename="resources.trng.yearlyScheduleReport" var="yearlySchLabel"
	scope="request" />

<c:set var="resultObj" value="${result}" />
<c:set var="resultMap" value="${resultObj.resultValue}" />						 
<c:set var="chkifTrainingCenter" value="${resultMap.chkifTrainingCenter}" />
<c:set var="trainingcenterLst" value="${resultMap.trainingcenterLst}" />
<c:set var="finalYearLst" value="${resultMap.finalYearLst}" />



<hdiits:form name="personDetail"   validate="true"  method="post"  action="hdiits.htm?">
<fmt:message bundle="${yearlySchLabel}" key="TR.SELECT" var="Select"></fmt:message>

<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1">
   <B><hdiits:caption  captionid="TR.REPORT" bundle="${yearlySchLabel}"/></B>  </a></li>				
   </ul>
</div>
<div class="tabcontentstyle"> 
	<div id="tcontent1" class="tabcontent" tabno="0" >
	 <!-- table for group and point -->
	 <hdiits:hidden name="trainingCenter" default="${chkifTrainingCenter}" />
		 
			<table class="tabtable">
			<c:if test="${chkifTrainingCenter eq 'false'}">
				<tr>
					<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGCENTER" bundle="${yearlySchLabel}"/></td>
					<td class="fieldLabel" width="25%">
					<hdiits:select name="trainingCenterForReport" validation="sel.isrequired" condition="selReq()" id="trngCntr" caption="Training Center" mandatory="true">
					<hdiits:option value="-1"> <fmt:message key="COMMON.DROPDOWN.SELECT"/></hdiits:option>
					<c:forEach var="trngcenterLoop" items="${trainingcenterLst}">
					<hdiits:option value="${trngcenterLoop.locId}"> <c:out value="${trngcenterLoop.locName}"></c:out></hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
				</tr>
			</c:if>
			
			<tr>
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="TR.YEAR" bundle="${yearlySchLabel}"/></td>
				<td class="fieldLabel" width="25%">
					<hdiits:select id="yearOfSchedule" name="scheduleyear" captionid="TR.YEAR" bundle="${yearlySchLabel}">
					<hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></hdiits:option>
					<c:forEach var="yearLoop" items="${finalYearLst}">
					<hdiits:option value="${yearLoop}"><c:out value="${yearLoop}"></c:out></hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
				<td width="25%"></td>
				<td width="25%"></td>
			</tr>
		</table>
	
	<center><hdiits:button  type="button"  name="smtButton"  onclick="submitForm(${chkifTrainingCenter})" captionid="TR.SUBMITQUERY" bundle="${yearlySchLabel}" /></center>
	
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