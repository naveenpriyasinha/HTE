<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="hrleavemain"	value="${resultValue.LatestModified_HrEssLeaveMainTxn}">

</c:set>
<c:if test="${hrleavemain.modifiedFlag eq 1}">
<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.leave_modified_detail" bundle="${leaveCaption}"  id="LeaveModDeGrp">	
<table width="100%">
<c:if test="${hrleavemain.combFlag eq 0}">
	<tr id="leaveDetail_mod">

		<td colspan="10">
		<table border=0 width="100%">
			<tr>
				<td width="20%"><hdiits:caption captionid="HRMS.appliedbetween1"  bundle="${leaveCaption}"/>
				</td>
				<td width="38%">: &nbsp; <fmt:formatDate
					value="${hrleavemain.appLeaveFrom}" pattern="dd-MM-yyyy" />&nbsp;<fmt:message key="HRMS.Dash"  bundle="${leaveCaption}"/>&nbsp;<fmt:formatDate
					value="${hrleavemain.appLeaveTo}" pattern="dd-MM-yyyy" /></td>

				<c:forEach var="hod" items="${hrleavemain.hrEssLeaveOtherDtls}">
					<td id="sanction_days1" width="27%"><hdiits:caption captionid="HRMS.sanc_days1"  bundle="${leaveCaption}"/></td>
					<td id="sanction_days">: &nbsp;<c:if
					test="${func:trim(hod.noofsancdays) eq ''}">
												      -
												      </c:if>${hod.noofsancdays}</td>
			</tr>
			<tr id="leavetype">
				<td><hdiits:caption captionid="HRMS.NatureOfLeave"  bundle="${leaveCaption}"/></td>
				<td id="leavetype_lbl">: &nbsp;${hod.hrEssLeaveMst.leaveTypeName}
				</td>
				<td><hdiits:caption captionid="HRMS.NoD1"  bundle="${leaveCaption}"/></td>
				<td id="noofdays_lbl">: &nbsp;
				<c:if
					test="${func:trim(hod.noofdays) eq ''}">
												      -
												      </c:if>
				${hod.noofdays}</td>
			</tr>
			<tr id="sanction_TR">
				<td id="sanctionFromDate"><hdiits:caption captionid="HRMS.sanc_fromdate1" bundle="${leaveCaption}"/></td>
				<td id="sanc_fromdate">: &nbsp; <c:if
					test="${func:trim(hod.sanctionFromdate) eq ''}">
												      -
												      
												      </c:if></td>
				<td><hdiits:caption captionid="HRMS.sanc_todate1"  bundle="${leaveCaption}"/></td>
				<td id="sanc_todate" align="left">: &nbsp;
				${hod.sanctionTodate} 
				<c:if	test="${func:trim(hod.sanctionTodate) eq ''}">
												      -
												      
												      </c:if></td>
				</c:forEach>


			</tr>
		</table>
		</td>
	</tr>
</c:if>



<tr id="displayTable_mod">

	<td colspan="4"><c:if test="${hrleavemain.combFlag=='1'}">


		<table id="displayTable" style="border-collapse: collapse; display:none" borderColor="BLACK"  border=1 width="100%" name="lvdtl">

			<tbody>
				<tr>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.SrNo"  bundle="${leaveCaption}"/></b></TH>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.LeaveType" bundle="${leaveCaption}"/></b></TH>
					<TH class="datatableheader"><b><fmt:message
						key="HRMS.fromdate" /></b></TH>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.todate"  bundle="${leaveCaption}"/></b></TH>


					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.NoD1"  bundle="${leaveCaption}"/></b></TH>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.sanc_fromdate1"  bundle="${leaveCaption}"/></b></TH>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.sanc_todate1"  bundle="${leaveCaption}"/></b></TH>
					<TH class="datatableheader"><b><hdiits:caption captionid="HRMS.sanc_days1"  bundle="${leaveCaption}"/></b></TH>
				</tr>
				<c:set var="counter" value="1" />
				<c:forEach var="hod" items="${hrleavemain.hrEssLeaveOtherDtls}">

					<tr>
						<td class="tablecelltext" align="center">${counter}</td>

						<td class="tablecelltext" align="center">
						${hod.hrEssLeaveMst.leaveTypeName}</td>


						<td class="tablecelltext" align="center"><fmt:formatDate
							value="${hod.leaveFromdate}" pattern="dd-MM-yyyy" /></td>



						<td class="tablecelltext" align="center"><fmt:formatDate
							value="${hod.leaveTodate}" pattern="dd-MM-yyyy" /></td>
						<td class="tablecelltext" align="center">${hod.noofdays}</td>
						<td class="tablecelltext" align="center"><fmt:formatDate
							value="${hod.sanctionFromdate}" pattern="dd-MM-yyyy" /></td>
						<td class="tablecelltext" align="center"><fmt:formatDate
							value="${hod.sanctionTodate}" pattern="dd-MM-yyyy" /></td>
						<td class="tablecelltext" align="center">${hod.noofsancdays}
						</td>
					</tr>
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
		</table>
	</c:if></td>
</tr>	
	<tr>
		<td colspan=6><!-- For attachment : Start--> <jsp:include
			page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="LeaveAttachment" />
			<jsp:param name="formName" value="frmleaveapply" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />
		</jsp:include> <!-- For attachment : End--></td>
	</tr>
</table>
</hdiits:fieldGroup>
<script>
document.getElementById('formTable1LeaveAttachment').firstChild.firstChild.style.display='none';
</script>
</c:if>
<c:remove var="Modified_HrEssLeaveMainTxn" scope="session"/>
