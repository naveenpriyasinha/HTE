
<c:if test="${not empty latestAppLeave }">
<fieldset>
	<legend>
	<c:if test="${latestAppLeave.combFlag=='1'}">
		<hdiits:caption captionid= "HRMS.combinationleave" bundle="${leaveCaption}"/>
	</c:if>
	<c:if test="${latestAppLeave.combFlag=='0'}">
		<hdiits:caption captionid= "HRMS.normalleave" bundle="${leaveCaption}"/>
	</c:if>
</legend>

<table width="100%" style="border-collapse: collapse;" borderColor="BLACK"  border=1
		 bgcolor="white">

	

		<tr bgcolor='#C9DFFF'>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.SrNo"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.LeaveType"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.StartDate"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.EndDate"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.NoD1"  bundle="${leaveCaption}"/></b></th>
		</tr>


		<c:set var="counter" value="1" />

		<c:forEach var="othrDtl" items="${latestAppLeave.hrEssLeaveOtherDtls}">
			<tr>
				<td align="center">${counter}</td>
				<td align="center"><c:out
					value="${othrDtl.hrEssLeaveMst.leaveTypeName}" /></td>

				<td id="startdate_app_${counter}" align="center"><script>
FormatDDMMYYYY_WO_COLON('${othrDtl.sanctionFromdate}',document.getElementById("startdate_app_${counter}"));</script></td>
				<td id="enddate_app_${counter}" align="center"><script>FormatDDMMYYYY_WO_COLON('${othrDtl.sanctionTodate}',document.getElementById("enddate_app_${counter}"));</script></td>
				<td align="center"><c:out value="${othrDtl.noofsancdays}" /></td>
				<c:set var="counter" value="${counter+1}" />
			</tr>
		</c:forEach>
</table>
	<br/>	
	</fieldset>
</c:if>
<c:if test="${empty latestAppLeave}">

<table width="100%" style="border-collapse: collapse;" borderColor="BLACK"  border=1
		 bgcolor="white">

	

		<tr bgcolor='#C9DFFF'>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.SrNo"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.LeaveType"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.StartDate"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.EndDate"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.NoD1"  bundle="${leaveCaption}"/></b></th>
		</tr>
<tr>
<td colspan="5" align="center"><b><fmt:message key="HRMS.Norecordsfound" /></b> </td>
</tr>
</table>
</c:if>
