<%try{ %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>
<c:set var="agencyList" value="${resValue.agencyList}"></c:set>
<c:set var="newsPaperList" value="${resValue.newsPaperList}"></c:set>
<c:set var="magazineList" value="${resValue.magazineList}"></c:set>
<!--<c:set var="admissible_amt" value="${resValue.admissible_amt}" ></c:set>-->
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<html>
<body>
<script type="text/javascript">
function setAgencyValue(){

	var agencyName 	= document.getElementById('agency_name').value;
	var agencyId	= document.getElementById('agency_id').value;

	document.getElementById('agencyName').options[document.getElementById('agencyName').options.length] = new Option(agencyName,agencyId); 

}
</script>


<tr bgcolor="#386CB7">
		<td class="fieldLabel" align="center" colspan="9">
		<font color="#ffffff"><strong><u>					
		<fmt:message bundle="${enLables}" key="RM.newsPaper"/><fmt:message bundle="${enLables}" key="RM.Details"/>
		</u></strong></font></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Fromdate" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="frmDate"  captionid="RM.Fromdate" bundle="${enLables}"/></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Todate" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:dateTime name="toDate"  captionid="RM.Todate" bundle="${enLables}"/></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.agencyName" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%">
		<hdiits:select name="agencyName" id="agencyName" captionid="RM.agencyName" bundle="${enLables}" mandatory="true" validation="sel.isrequired">
			<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
			<c:forEach items="${agencyList}" var="agencyNames">
				<hdiits:option value="${agencyNames.agencyId}">${agencyNames.agencyName}</hdiits:option>
			</c:forEach>
		</hdiits:select><hdiits:button name="addAgencyName" type="button" bundle="${enLables}" captionid="RM.Agency" onclick="agencynames()"/>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.scrapItem" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%">
		<hdiits:radio name="scrapItem" id="scrapYes" value="Yes" captionid="RM.Yes" bundle="${enLables}"/>
		<hdiits:radio name="scrapItem" id="scrapNo" value="No" captionid="RM.No" bundle="${enLables}"/>
	</td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.agencyAddress" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:textarea maxlength="240" cols="30" id="agencyAddress" name="agencyAddress" captionid="RM.agencyAddress" bundle="${enLables}" rows="3" readonly="true" /></td>
	
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.admAmt" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%"><hdiits:text name="admAmt" captionid="RM.admAmt" bundle="${enLables}" readonly="true"/></td>
</tr>

<tr>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.selectNewsPaper" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%">
	<c:forEach items="${newsPaperList}" var="newsPaperNms">
		<hdiits:checkbox id="newsPaperNms" name="newsPaperNms" value="${newsPaperNms.lookupDesc}"/><c:out value="${newsPaperNms.lookupDesc}"/>
		<br>
	</c:forEach>
	</td>
	<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.selectMagazine" bundle="${enLables}"/></td>
	<td class="fieldLabel" width="25%">
	<c:forEach items="${magazineList}" var="magazineNms">
		<hdiits:checkbox id="magazineName" name="magazineName" value="${magazineNms.lookupDesc}"/><c:out value="${magazineNms.lookupDesc}"/>
		<br>
	</c:forEach>
	</td>

</tr>

	<hdiits:hidden name="agency_name" id="agency_name"/>
	<hdiits:hidden name="agency_id" id="agency_id"/>
	<script type="text/javascript">
			document.getElementById('scrapNo').checked = 'true';
	</script>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</body>
</html>
