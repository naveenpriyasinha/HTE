<%@ include file="./alertMessage.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="LeaveTList" value="${resultValue.leaveTList}">
</c:set>
<c:set var="app_penLst" value="${resultValue.approve_pendingList}">
</c:set>
<c:set var="LeaveBal" value="${resultValue.leaveBal}">
</c:set>
<c:set var="ajaxKey" value="${resultValue.ajaxKey}">
</c:set>
<c:set var="parentPost" value="${resultValue.parentPost}">
</c:set>
<c:set var="leaveCancellationVO"
	value="${resultValue.leaveCancellationVO}"></c:set>

<c:set var ="appliedLeaveData" value="${resultValue.appliedLeaveData}"/>
<c:set var="attachmentXMLLst" value="${resultValue.leaveAttachmentXMLLst}"/>
<c:set var ="attachmentXML" value="${resultValue.attachmentXML}"/>
<c:set var ="cancelAttachmentXML" value="${sessionScope.cancelLeaveAttachmentXMLLst}"/>

<c:set var="combTable" value="display:none;"/>
<c:set var="telephoneTR" value="display:none"/>
<c:set var="isCombination" value="0"/>
<c:set var="leavetypeSelected"  value=""/>
<c:set var="halfday" value="0"/>
<c:set var="before2" value="0"/>
<c:set var="startday_endday" value=""/>
<c:set var="nod" value=""/>
<c:set var="sancNoD" value=""/>
<c:set var="sancFromDate" value=""/>
<c:set var="sancToDate" value=""/>
<c:set var="leaveTypeName" value=""/>
<c:set var="normalLeaveTbl" value="display:none;"/>
	<c:if test="${appliedLeaveData.combFlag==1}">
		<c:set var="combTable" value="display:visiblity;"/>
		<c:set var="isCombination" value="${appliedLeaveData.combFlag}"/>
		<c:set var="normalLeaveTbl" value="display:none;"/>
	</c:if>
	<c:if test="${appliedLeaveData.combFlag==0}">
		<c:set var="normalLeaveTbl" value="display:visiblity;"/>
		<c:set var="combTable" value="display:none;"/>
	</c:if>

	<c:forEach var="leaveOtherDtl" items="${appliedLeaveData.hrEssLeaveOtherDtls}">
			<c:set var="halfday" value="${leaveOtherDtl.halfday}"/>
			<c:set var="before2" value="${leaveOtherDtl.before2}"/>
			<c:set var="startday_endday" value="${leaveOtherDtl.startdayEndday}"/>
			<c:set var="leavetypeSelected"  value="${leaveOtherDtl.hrEssLeaveMst.leaveTypeid}_${leaveOtherDtl.hrEssLeaveMst.srno}_${leaveOtherDtl.hrEssLeaveMst.leavecode}"/>			
			<c:set var="leaveTypeName" value="${leaveOtherDtl.hrEssLeaveMst.leaveTypeName}"/>
			<c:set var="nod" value="${leaveOtherDtl.noofdays}"/>
			<c:set var="sancNod" value="${leaveOtherDtl.noofsancdays}"/>
			<c:set var="sancFromDate" value="${leaveOtherDtl.sanctionFromdate}"/>
			<c:set var="sancToDate" value="${leaveOtherDtl.sanctionTodate}"/>


		</c:forEach>	
		
		<c:set var="halfdayTR" value="display:none"/>
			<c:if test="${not empty halfday and halfday ne 0}" >
			<c:set var="halfdayTR" value="display:visibility"/>
			</c:if>
			
			<c:set var="before2TR" value="display:none"/>	
			<c:if test="${not empty before2 and halfday eq 1}" >
			<c:set var="before2TR" value="display:visibility"/>
			</c:if>
			
			<c:set var="halfdayOnfirstday" value=""/>
			<c:set var="halfdayOnLastday" value=""/>
			<c:set var="startday_enddayTR" value="display:none"/>	
			<c:set var="after2PMOnFirstdayTD" value="display:none"/>
			<c:set var="before2PMOnLastdayTD" value="display:none"/>
			
			<c:if test="${not empty startday_endday and halfday eq 1 and startday_endday ne '0_0'}" >
			<c:set var="startday_enddayTR" value="display:visibility"/>
			<c:set var="splittedStartday" value='${fn:split(startday_endday,"_")}'/>	
			<c:set var="halfdayOnfirstday" value="${splittedStartday[0]}"/>
			<c:set var="halfdayOnLastday" value="${splittedStartday[1]}"/>
			<c:if test="${startday_endday ne '0_0'}">
			<c:set var="before2TR" value="display:none"/>
			</c:if>

			<c:choose>
			<c:when test="${halfdayOnfirstday eq 1 and halfday eq 1 and startday_endday ne '0_0'}" >				
			<c:set var="after2PMOnFirstdayTD" value="display:visibility"/>
			</c:when>
			<c:when test="${halfdayOnfirstday eq 0 and startday_endday ne '0_0'}" >				
			<c:set var="after2PMOnFirstdayTD" value="display:none"/>
			</c:when>
		
			</c:choose>

			<c:if test="${halfdayOnLastday eq 1 and halfday eq 1 and startday_endday ne '0_0'}" >
				<c:set var="before2PMOnLastdayTD" value="display:visibility"/>				
	
			</c:if>
			
			<c:if test="${halfdayOnLastday eq 0 and startday_endday ne  '0_0'}" >			
				<c:set var="before2PMOnLastdayTD" value="display:none"/>

			</c:if>

			</c:if>
		


<script>
	var leavetype_bal= new Array();				
function temprorySave()
{
				document.frmleaveapply.action="hdiits.htm?actionFlag=callOperation";
		    /*if(validateBalance(leavetype_bal,document.frmleaveapply)){*/

			if('${parentPost}'=='-1' && document.frmleaveapply.status.value=='0'){
						alert("You cant forward this request as you are the parent");
				}else{
				document.frmleaveapply.submit();
				}
			//}

	

}
--></script>

<%
try {
%>

<hdiits:form name="frmleaveapply" validate="true"  method="post">
	<div id="leave" name="leave">
	<table class="tabtable" border=0>
		<tr>
			<td colspan="4">
			<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
		<tr bgcolor="#386CB7" id="balance_hdr" >
			<td class="fieldLabel" colspan="4" align="center">
			<FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.Balanceavailable" /></U></STRONG></FONT>
			</td>
		</tr>

		<tr>
			<td colspan=5><%@ include file="LeaveBalance.jsp"%>
		</tr>
		
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center">
<FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.LeaveDetail" /></U></STRONG></FONT>
			</td>
		</tr>
		<tr>
			<td>	<table border=0 width="100%">
				<tr>
					<td width="20%" ><hdiits:caption captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"/>
					</td>
					<td><fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd-MM-yyyy" />&nbsp;
				 <hdiits:caption captionid="HRMS.Dash"  bundle="${leaveCaption}"/>&nbsp;
				 <fmt:formatDate value="${appliedLeaveData.appLeaveTo}"		pattern="dd-MM-yyyy" />
					</td>
    <td id="sanction_days1" style="${normalLeaveTbl}">
    <hdiits:caption captionid="HRMS.sanc_days1" bundle="${leaveCaption}"/>
    </td>
    <td  id="sanction_days" style="${normalLeaveTbl}">
	${sancNod}
	<c:if test="${empty sancNod}">
-
</c:if>
    </td>
	</tr>
	<tr id="leavetype" style="${normalLeaveTbl}">
	<td>
	<hdiits:caption captionid="HRMS.NatureOfLeave" bundle="${leaveCaption}"/>
	</td>
	<td id="leavetype_lbl">
	${leaveTypeName}
	</td>
	<td><hdiits:caption captionid="HRMS.NoD1" bundle="${leaveCaption}"/>
	</td>
	<td id="noofdays_lbl">
	${nod}
	</td>
	</tr>
	
	
	 <tr id="sanction_TR" style="${normalLeaveTbl}">
 <td id="sanctionFromDate">
 <hdiits:caption captionid="HRMS.sanc_fromdate1" bundle="${leaveCaption}"/>
  
  </td>
  <td id="sanc_fromdate">
<fmt:formatDate value="${sancFromDate}" pattern="dd/MM/yyyy"/>
<c:if test="${empty sancFromDate}">
-
</c:if>
  </td>
    <td>
	<hdiits:caption captionid="HRMS.sanc_todate1" bundle="${leaveCaption}"/>

  </td>
<td id="sanc_todate" align="left">

<fmt:formatDate value="${sancToDate}"  pattern="dd/MM/yyyy"/>
<c:if test="${empty sancToDate}">
-
</c:if>

</td>  
</tr>

					<tr id="halfday" style="${halfdayTR}">
					<td><hdiits:caption captionid="HRMS.halfday"  bundle="${leaveCaption}"/></td>
					<td id="halfday_radio">
					<c:if test="${halfday eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
					</c:if>
					<c:if test="${halfday eq 0 }">
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
					</c:if>
					</td>
					<td id="before2_lbl" style="${before2TR}"><hdiits:caption captionid="HRMS.before2" bundle="${leaveCaption}" />
					</td>
					<td align="left" id="before_radio" style="${before2TR}">
										<c:if test="${before2 eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
					</c:if>
					<c:if test="${before2 eq 0 }">
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
					</c:if>
					
					
					</td>

				</tr>

				<tr id="First_Day" style="${startday_enddayTR}">
			<td id="firstday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyy" />
			</td>
			<td>
			<c:if test="${halfdayOnfirstday eq 1}">
			<hdiits:caption captionid="HRMS.Yes"  bundle="${leaveCaption}"/>
			</c:if> 
			
			<c:if test="${halfdayOnfirstday eq 0}">
			<hdiits:caption captionid="HRMS.No"  bundle="${leaveCaption}"/>
			</c:if> 
			</td>
			<td id="before2_radio_firstday" ><hdiits:caption captionid="HRMS.after2pm"  bundle="${leaveCaption}"/></td>
		</tr>


		<tr id="Last_Day" style="${before2PMOnLastdayTD}">
			<td id="lastday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="dd/MM/yyy" /> 			</td>
			<td>
			<c:if test="${halfdayOnLastday eq 1}">
			<hdiits:caption captionid="HRMS.Yes"  bundle="${leaveCaption}"/>
			</c:if> 
			
			<c:if test="${halfdayOnLastday eq 0}">
			<hdiits:caption captionid="HRMS.No"  bundle="${leaveCaption}"/>
			</c:if> 
			</td>
			<td id="before2_radio_lastday"><hdiits:caption captionid="HRMS.before2pm"  bundle="${leaveCaption}"/></td>
		</tr>
				
			<c:set var="teleList"  value='${fn:split(appliedLeaveData.phoneno, "-")}'/>
			<c:if test="${not empty appliedLeaveData.phoneno}">
			<c:set var="telephoneTR" value="display:visibility"/>
			</c:if>

			<c:set var="telecode" value=""/>
			<c:if test="${teleList[0] ne 'xxxx'}">
				<c:set var="telecode" value="${teleList[0]}"/>
				
			</c:if>
				<tr id="telephone" style="${telephoneTR}">
					<td><hdiits:caption captionid="HRMS.Telephone" bundle="${leaveCaption}" /></td>
					<td id="telephone_txt">
			<c:if test="${teleList[0] ne 'xxxx'}">
									${telecode}-
			</c:if>
					${teleList[1]}
					</td>
				</tr>

				<tr id="first_second_Day" style="display:none">
					<td><hdiits:caption captionid="HRMS.FirstDay_LastDay" bundle="${leaveCaption}"/></td>
					<td id="first_last_Day"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table id="lvdtl"  width="100%" style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border=1>
				<tbody>
				<%@include file="CmnCombinationLeaveView.jsp"%>
				</tbody>
			</table>
			</td>
		</tr>
		<tr><td>
		<!-- For attachment : Start--> <jsp:include
				page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="LeaveAttachment" />
				<jsp:param name="formName" value="frmleaveapply" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
			</jsp:include> <!-- For attachment : End-->
		
		</td>
		</tr>
		
		
			<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center">
		<FONT color=#ffffff><STRONG><U>	<fmt:message key="HRMS.CancellationDetail" /></U></STRONG></FONT>
		</td>
		</tr>
		<tr>

			<td>
			<table>
				<tr>
					<td><hdiits:caption captionid="HRMS.CancellationReason"  bundle="${leaveCaption}"/></td>
					<td><hdiits:textarea mandatory="false" rows="3" cols="50"
						name="reason" tabindex="1" id="c_strNames"
						validation="txt.isrequired" caption="leavereason"
						default="${leaveCancellationVO.remarks}" readonly="true" maxlength="2000"/></td>
			</table>
			</td>
		</tr>


		<tr>
			<td>

								<jsp:include
								page="/WEB-INF/jsp/common/attachmentPage.jsp">
								<jsp:param name="attachmentName" value="cancelLeaveAttachment"/>
								<jsp:param name="formName" value="frmleaveapply" />
								<jsp:param name="attachmentType" value="Document" />
								<jsp:param name="multiple" value="N" />
							</jsp:include> 
			<hr>
			<table border="0" width="100%" style="display:none">

				<tr><td>
					 <hdiits:hidden
						id="noofdays" name="noofdays" caption="noofdays"></hdiits:hidden>
					<hdiits:hidden name="parentid" caption="ParentId" default="-1" /> <hdiits:hidden
						name="tempsave" caption="tempsave" default="-1"></hdiits:hidden> <hdiits:hidden
						name="halfday_comb" caption="halfday" default="0"></hdiits:hidden> <hdiits:hidden
						name="halfday" caption="halfday"></hdiits:hidden><hdiits:hidden
						name="before2_comb" caption="before2" default="0"></hdiits:hidden> <hdiits:hidden
						name="before2" caption="before2" default="0"></hdiits:hidden> <hdiits:hidden
						name="ordi_comb" caption="ordinary_comb" default="0"></hdiits:hidden>
				    	<hdiits:text	name="status" caption="status" default="1"></hdiits:text> <hdiits:hidden
						name="userId" caption="userId"></hdiits:hidden> <hdiits:hidden
						name="leaveid" caption="leaveid" /> <hdiits:hidden name="cnclId"
						caption="cnclId" default="${param.appliedBetween}" /> <hdiits:hidden
						name="comeFrom" caption="comeFrom" default="cancelAppPage"></hdiits:hidden>
					<hdiits:hidden name="sanction" caption="action" default="sanction"></hdiits:hidden>
					<hdiits:hidden name="combinationleave" caption="combinationleave"
						default="0"></hdiits:hidden></td>



					<td><hdiits:hidden name="leavedtls" caption="a" /></td>
					<td>&nbsp;</td>

				</tr>

			</table>
			</td>
		</tr>
	</table>


	</div>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");
document.getElementById('formTable1LeaveAttachment').firstChild.firstChild.style.display='none';
</script>
</c:forEach>
<c:forEach var="attachmentXML" items="${cancelAttachmentXML}">
<script>
populateAttachmentForEditing('${attachmentXML}',"cancelLeaveAttachment");
document.getElementById('formTable1cancelLeaveAttachment').firstChild.firstChild.style.display='none';
</script>
</c:forEach>

<center>
<hdiits:button type="button"  name="close" captionid="HRMS.Close" bundle="${leaveCaption}" onclick="window.close();"/>
</center>
</hdiits:form>


<script>
//populateCancelApproval(document.frmleaveapply,lineBreak('${ajaxKey}'),'${leaveCancellationVO.status}',labels);
document.getElementById('target_uploadLeaveAttachment').style.display='none';
document.getElementById('formTable1LeaveAttachment').firstChild.firstChild.style.display='none';
document.getElementById('formTable1cancelLeaveAttachment').firstChild.firstChild.style.display='none';
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
