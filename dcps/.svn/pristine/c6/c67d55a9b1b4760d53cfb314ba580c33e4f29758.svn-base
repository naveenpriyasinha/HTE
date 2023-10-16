<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty hrleavemain}">
<c:if test="${hrleavemain.combFlag=='0'}">
	
	<tr id="leaveDetail_mod">

		<td colspan="5">
		
		<hdiits:fieldGroup bundle="${LeaveCaption}"  expandable="true" titleCaptionId="HRMS.leave_entry_detail1" id="previousLeaveEntryGrp">	
		<table border=0 width="100%">
			<tr>
				<td width="20%"><hdiits:caption captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"/>
				</td>
				<td>:&nbsp; <fmt:formatDate
					value="${hrleavemain.appLeaveFrom}" pattern="dd-MM-yyyy" /> <hdiits:caption captionid=
					"HRMS.Dash"  bundle="${leaveCaption}"/> <fmt:formatDate
					value="${hrleavemain.appLeaveTo}" pattern="dd-MM-yyyy" /></td>

				<c:forEach var="hod" items="${hrleavemain.hrEssLeaveOtherDtls}">
					<td id="sanction_days1"><hdiits:caption captionid=
						"HRMS.sanc_days1"  bundle="${leaveCaption}"/></td>
					<td id="sanction_days">:&nbsp;${hod.noofsancdays}</td>
			</tr>
			<tr id="leavetype">
				<td><hdiits:caption captionid= "HRMS.NatureOfLeave"  bundle="${leaveCaption}"/></td>
				<td id="leavetype_lbl">:&nbsp;${hod.hrEssLeaveMst.leaveTypeName}
				</td>
				<td><hdiits:caption captionid= "HRMS.NoD1"  bundle="${leaveCaption}"/></td>
				<td id="noofdays_lbl">:&nbsp;${hod.noofdays}</td>
			</tr>
			<tr id="sanction_TR">
				<td id="sanctionFromDate"><hdiits:caption captionid=
					"HRMS.sanc_fromdate1"  bundle="${leaveCaption}"/></td>
				<td id="sanc_fromdate">:&nbsp;
				<fmt:formatDate
					value="${hod.sanctionFromdate}" pattern="dd-MM-yyyy" />
				 <c:if	test="${func:trim(hod.sanctionFromdate) eq '-'}"> -
			      </c:if></td>
				<td><hdiits:caption captionid= "HRMS.sanc_todate1"  bundle="${leaveCaption}"/></td>
				<td id="sanc_todate" align="left">:&nbsp;
				<fmt:formatDate
					value="${hod.sanctionTodate}" pattern="dd-MM-yyyy" /> <c:if
					test="${func:trim(hod.sanctionTodate) eq '-'}">
												      -
												      
												      </c:if></td>
				</c:forEach>


			</tr>
		</table>
	</hdiits:fieldGroup>	
		</td>
	</tr>
</c:if>
<tr id="displayTable_mod">
	<td colspan="4"><c:if test="${hrleavemain.combFlag=='1'}">
	
	<hdiits:fieldGroup bundle="${LeaveCaption}"  expandable="true" titleCaptionId="HRMS.leave_entry_detail1" id="previousLeaveEntryGrp">	
		<table id="displayTable"  width="100%" name="lvdtl"  style="border-collapse: collapse;" borderColor="BLACK"  border=1>
			<tbody>
				<tr bgcolor="#C9DFFF">
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.SrNo" bundle="${leaveCaption}"/></b></TH>
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.LeaveType"  bundle="${leaveCaption}"/></b></TH>
					<TH align="center" ><b><hdiits:caption captionid=
						"HRMS.fromdate"  bundle="${leaveCaption}"/></b></TH>
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.todate" bundle="${leaveCaption}"/></b></TH>


					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.NoD1"  bundle="${leaveCaption}"/></b></TH>
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.sanc_fromdate1"  bundle="${leaveCaption}"/></b></TH>
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.sanc_todate1"  bundle="${leaveCaption}"/></b></TH>
					<TH align="center"><b><hdiits:caption captionid=
						"HRMS.sanc_days1"  bundle="${leaveCaption}"/></b></TH>
				</tr>
				<c:set var="counter" value="1" />
				<c:forEach var="hod" items="${hrleavemain.hrEssLeaveOtherDtls}">

					<tr>
						<td  align="center">${counter}</td>

						<td align="center">
						${hod.hrEssLeaveMst.leaveTypeName}</td>


						<td  align="center"><fmt:formatDate
							value="${hod.leaveFromdate}" pattern="dd-MM-yyyy" /></td>



						<td align="center"><fmt:formatDate
							value="${hod.leaveTodate}" pattern="dd-MM-yyyy" /></td>
						<td align="center">${hod.noofdays}</td>
						<td align="center">
						<c:if test="${empty hod.sanctionFromdate}">
						-
						</c:if>
						<fmt:formatDate
							value="${hod.sanctionFromdate}" pattern="dd-MM-yyyy" /></td>
						<td align="center">
						<c:if test="${empty  hod.sanctionTodate}">
							-
						</c:if>
						
						<fmt:formatDate
							value="${hod.sanctionTodate}" pattern="dd-MM-yyyy" /></td>
						<td align="center">
						<c:if test="${empty  hod.noofsancdays}">
							-
						</c:if>
						
						${hod.noofsancdays}
						</td>
						
					</tr>
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
		</table>
		</hdiits:fieldGroup>
	</c:if></td>
</tr>
</c:if>
