
<!-- 
**** cancel Leave starts
 -->
<c:if test="${not empty cancelLeaveVO}">

<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.CancellationDetail" bundle="${leaveCaption}"  id="LeaveCancelGrp">		
			<table width="100%" border=0>

				<tr>
					<td width="20%"><hdiits:caption captionid="HRMS.CancellationReason"  bundle="${LeaveCaption}"/></td>

					<td><hdiits:textarea mandatory="false" rows="3" cols="50"
						name="cancelremarks" tabindex="40" id="c_strNames" 
						validation="txt.isrequired" captionid="HRMS.leavereason"
						default="${cancelLeaveVO.remarks}" bundle="${LeaveCaption}"
						readonly="true" >

					</hdiits:textarea></td>

				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td colspan=6><!-- For attachment : Start--> <jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="cancelAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />
					</jsp:include> <!-- For attachment : End--></td>
				</tr>

			</table>
</hdiits:fieldGroup>


</c:if>
