				<c:if test="${appliedLeaveData.combFlag eq 1 }">
				<tr bgcolor="#C9DFFF">
					<c:if test="${showCheckBoxInCombinationLeave eq true}">
					<TH>
					<INPUT type="checkbox" id="checkAll" name="chk"  onclick="checkAllchild();"/>
					</TH>
					</c:if>
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
					
				</tr>
				<c:set var="counter" value="1" />
				
				<c:forEach var="hod" items="${appliedLeaveData.hrEssLeaveOtherDtls}">

				<c:set var="combLeaveTypes" value="${hod.hrEssLeaveMst.leaveTypeid}_${hod.hrEssLeaveMst.srno}_${hod.hrEssLeaveMst.leavecode}"/>
					<tr id="rowchk${combLeaveTypes}${counter-1}"  cnt="${combLeaveTypes}" counter="${counter-1}">
					<c:if test="${showCheckBoxInCombinationLeave eq true}">
					<td>
									<!-- "chk"+lvdtlArr[0]+z -->
						<INPUT type="checkbox" id="chk${combLeaveTypes}${counter-1}" name="cmb_leave" value="0"  counter="${counter-1}" onclick="changeStatusOfParentChkbox(this);"/>
					</td>
			</c:if>
						<td  align="center" id="sr${combLeaveTypes}${counter-1}">${counter}</td>
						<td align="center" id="leavetype_${combLeaveTypes}${counter-1}">
						${hod.hrEssLeaveMst.leaveTypeName}</td>
						<script type="text/javascript">						
							if('${hod.hrEssLeaveMst.leaveTypeid}'=='3')
							{						
								leavetypeSelected='3_3_5';
							}
						</script>

						<td  align="center" id="fromdate_${combLeaveTypes}${counter-1}"><fmt:formatDate
							value="${hod.leaveFromdate}" pattern="dd-MM-yyyy" /></td>



						<td align="center" id="todate_${combLeaveTypes}${counter-1}"><fmt:formatDate
							value="${hod.leaveTodate}" pattern="dd-MM-yyyy" /></td>
						<td align="center" id="nod_${combLeaveTypes}${counter-1}">${hod.noofdays}
						<fmt:formatDate	value="${hod.leaveFromdate}" pattern="dd/MM/yyyy"  var="leaveFromDate"/>
						<fmt:formatDate	value="${hod.leaveTodate}" pattern="dd/MM/yyyy" var="leaveToDate"/>
						<c:set var="value" value="${combLeaveTypes}~${leaveFromDate}~${leaveToDate}~${hod.noofdays }~${hod.leaveOrdCircum}" />
						<input type="hidden" name="combLeave" id="txt${combLeaveTypes}${counter-1}" value="${value}">
						</td>
					</tr>
					<script>
					leaveDataArrParent["${counter-1}"]="${value}";
					leaveArr["${counter-1}"]="${combLeaveTypes}";
					</script>
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
			<script>
			counter="${counter}";			
			leaveCounter="${counter}";			
			</script>	
			</c:if>	
<script>
function checkAllchild() {
var checks=document.getElementsByTagName("INPUT");

	var flag=false;
		for(j=0;j<checks.length;j++){
			if(checks[j].type=="checkbox"){
				if(checks[j].name=="chk")
				{
				 	if(checks[j].checked){
				 	flag=true;
				 	}
				 	else{
				 	flag=false;
				 	}
				 }
				if(flag && checks[j].name!="chkAgree1" && checks[j].name!="chkAgree2"){
				checks[j].checked=true;

				}
				else if (checks[j].name!="chkAgree2" && checks[j].name!="chkAgree1"){
				checks[j].checked=false;
				
				}
				}
			}


}


</script>