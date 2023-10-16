
<hdiits:fieldGroup bundle="${leaveCaption}"  expandable="true" collapseOnLoad="true" titleCaptionId="HRMS.PreviousAppPendReq" id="prevPendAppReq">


	

	<table width="100%" style="border-collapse: collapse;"  borderColor="BLACK"  border=1 
		 bgcolor="white">
		 
		 	<tr>
		<td colspan="5" align="center">
		<c:if test="${latestAppLeave.combFlag=='1'}">
		<hdiits:caption captionid="HRMS.combinationleave" bundle="${leaveCaption}"/>
	</c:if>
	<c:if test="${latestAppLeave.combFlag=='0'}">
		<hdiits:caption captionid="HRMS.normalleave" bundle="${leaveCaption}"/>
	</c:if>
		
		</td>
		</tr>
		<tr  bgcolor="#C9DFFF">
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.SrNo"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.LeaveType"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.StartDate"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.EndDate" bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.NoD1"  bundle="${leaveCaption}"/></b></th>
			<th class="fieldLabel" align="center"><b><hdiits:caption captionid=
				"HRMS.Status_lbl"  bundle="${leaveCaption}"/></b></th>

		</tr>
	
		<c:set var="counter" value="1" />
			<c:forEach var="othrDtl" items="${latestAppLeave.hrEssLeaveOtherDtls}">
				<tr>
					<td align="center">${counter}</td>
					<td align="center"><c:out
						value="${othrDtl.hrEssLeaveMst.leaveTypeName}" /></td>
					<c:if test="${latestAppLeave.status=='0'}">
						<td id="startdate_app_${counter}" align="center"><script>
							FormatDDMMYYYY_WO_COLON('${othrDtl.leaveFromdate}',document.getElementById("startdate_app_${counter}"));
							</script></td>

						<td id="enddate_app_${counter}" align="center"><script>
								FormatDDMMYYYY_WO_COLON('${othrDtl.leaveTodate}',document.getElementById("enddate_app_${counter}"));
							</script></td>
					</c:if>

					<c:if test="${latestAppLeave.status=='1'}">
						<td id="startdate_app_${counter}" align="center"><script>
								FormatDDMMYYYY_WO_COLON('${othrDtl.sanctionFromdate}',document.getElementById("startdate_app_${counter}"));
							</script></td>

						<td id="enddate_app_${counter}" align="center"><script>
								FormatDDMMYYYY_WO_COLON('${othrDtl.sanctionTodate}',document.getElementById("enddate_app_${counter}"));
							</script></td>
					</c:if>
					<td align="center"><c:if test="${latestAppLeave.status=='1'}">
						<c:out value="${othrDtl.noofsancdays}" />
					</c:if> <c:if test="${latestAppLeave.status=='0'}">
						<c:out value="${othrDtl.noofdays}" />
					</c:if></td>
					<td align="center"><c:if test="${latestAppLeave.status=='1'}">
						<hdiits:caption captionid= "HRMS.Approved_lbl"  bundle="${leaveCaption}"/>
					</c:if> <c:if test="${latestAppLeave.status=='0'}">
						<hdiits:caption captionid= "HRMS.Pending_lbl" bundle="${leaveCaption}"/>
					</c:if></td>
					<c:set var="counter" value="${counter+1}" />

				</tr>
			</c:forEach>

<c:if test="${empty latestAppLeave }">
	<tr>
<td colspan="6" align="center">
<hdiits:caption captionid="HRMS.Norecordsfound" bundle="${leaveCaption}"/>
</td>
</tr>
</c:if>
	</table>
</hdiits:fieldGroup>