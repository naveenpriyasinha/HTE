	<table border=0 width="79%">
				<c:set var="counter" value="0"/>
				<c:set var="counter" value="0"/>
  			<tr id="leaveType_hdr">
			<td width="1%">
			&nbsp;
			</td>
			
		<c:if test="${empty LeaveBal}">
		<br/>
			<td colspan="5" align="center">
				<hdiits:caption captionid="HRMS.BALANCE_NOT_AVAILABLE" bundle="${alertLables}" />
		</td>
		</c:if>

				<c:forEach var="leaveBal" items="${LeaveBal}">
				
			<td width="8%">
				<div >
					<div>
					${leaveBal.leaveshortName}
					</div>
					<div>
						${leaveBal.availableBal}
					</div>
				</div>
					</td>
				<script>
					leavetype_bal["${counter}"]=${leaveBal.availableBal}+"~"+${leaveBal.leaveTypeId};
				</script>

				<c:set var="counter" value="${counter+1}"/>
				</c:forEach>
			</tr>
</table>

