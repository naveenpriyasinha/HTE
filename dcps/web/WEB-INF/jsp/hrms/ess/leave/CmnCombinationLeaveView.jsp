
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
				
				<c:forEach var="hod" items="${appliedLeaveData.hrEssLeaveOtherDtls}">

				<c:set var="combLeaveTypes" value="${hod.hrEssLeaveMst.leaveTypeid}_${hod.hrEssLeaveMst.srno}_${hod.hrEssLeaveMst.leavecode}"/>
					<tr id="rowchk${combLeaveTypes}${counter-1}"  cnt="${combLeaveTypes}" counter="${counter-1}">
						<td  align="center" id="sr${combLeaveTypes}${counter-1}">${counter}</td>
						<td align="center" id="leavetype_${combLeaveTypes}${counter-1}">
						${hod.hrEssLeaveMst.leaveTypeName}</td>


						<td  align="center" id="fromdate_${combLeaveTypes}${counter-1}"><fmt:formatDate
							value="${hod.leaveFromdate}" pattern="dd-MM-yyyy" /></td>



						<td align="center" id="todate_${combLeaveTypes}${counter-1}"><fmt:formatDate
							value="${hod.leaveTodate}" pattern="dd-MM-yyyy" /></td>
						<td align="center" id="nod_${combLeaveTypes}${counter-1}">${hod.noofdays}
						<fmt:formatDate	value="${hod.leaveFromdate}" pattern="dd/MM/yyyy"  var="leaveFromDate"/>
						<fmt:formatDate	value="${hod.leaveTodate}" pattern="dd/MM/yyyy" var="leaveToDate"/>
						<c:set var="value" value="${combLeaveTypes}~${leaveFromDate}~${leaveToDate}~${hod.noofdays }~${hod.leaveOrdCircum}" />
						</td>
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
