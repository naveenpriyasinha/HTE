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
<c:set var="selectedValue" value="${sessionScope.corrId}"/>
<c:set var ="appliedLeaveData" value="${sessionScope.appliedLeaveData}"/>
<c:set var="attachmentXMLLst" value="${sessionScope.leaveAttachmentXMLLst}"/>
<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"
	var="leaveCaption" scope="request" />

	<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>	
	<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cancelleaveRequest.js"/>"></script>
<script>
</script>
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
	<c:set var="viewLeaveAttachmentComp"  value="display:none"/>
		<c:if test="${fn:length(attachmentXMLLst) gt 0}">
		<c:set var="viewLeaveAttachmentComp"  value="display:visibility"/>
		</c:if>


<%
try {
%>
<hdiits:form name="frmleaveapply" validate="true"
	action="hdiits.htm?actionFlag=insertCancelLeaveData" method="post"
	encType="multipart/form-data">


	
	<table class="hrms_tabtable" border=0>
		<tr>
			<td  colspan="4">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
		</table>
		<table width="100%">
			<tr>
			<td colspan=5>
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.Balanceavailable" bundle="${leaveCaption}"  id="LeaveBalanceGrp">	
			<%@ include file="LeaveBalance.jsp"%>
			</hdiits:fieldGroup>
			</td>
		</tr>

</table>
		
<hdiits:fieldGroup bundle="${leaveCaption}"  expandable="true" titleCaptionId="HRMS.LeaveDetail" id="appliedBetweenGrp">	
			<table border=0 width="100%">
				<tr>
					<td width="20%" ><hdiits:caption captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"/>
					</td>
					<td><hdiits:select sort="false" name="appliedbetween"
						size="1" captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"
						validation="sel.isrequired" mandatory="true" tabindex="1"
						onchange="getLeaveDataCancel(this,document.forms[0]);">
						<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="hrleavemain" items="${app_penLst}">
							<hdiits:option value="${hrleavemain.corrid}">
								<fmt:formatDate value="${hrleavemain.appLeaveFrom}"
									pattern="dd-MM-yyyy" />&nbsp;<fmt:message key="HRMS.Dash" bundle="${leaveCaption}"/>&nbsp;<fmt:formatDate value="${hrleavemain.appLeaveTo}"
									pattern="dd-MM-yyyy" />
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
<script>
if("${selectedValue}"!==""){
document.forms[0].appliedbetween.value="${selectedValue}";
}
					
</script>					
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
	</hdiits:fieldGroup>					
			<table id="lvdtl" width="100%"  style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border=1>
				<tbody>
				<%@include file="CmnCombinationLeaveView.jsp"%>
				</tbody>
			</table>
	
<table width="100%" border=0 id="leaveAttachmentTable" style="${viewLeaveAttachmentComp}">
				<tr>
					<td colspan=5><!-- For attachment : Start--> 
					<jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="LeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />
						</jsp:include>
					
					
					
					 <!-- For attachment : End--></td>
				</tr>
			</table>
			
<hdiits:fieldGroup bundle="${leaveCaption}"  expandable="true" titleCaptionId="HRMS.CancellationDetail" id="cancelDtlGrp">			
			<table width="100%" border=0>

				<tr>
					<td width="20%"><hdiits:caption captionid="HRMS.CancellationReason" bundle="${leaveCaption}"/></td>
					<td >
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_cancelreason_cnt">2000</label>]</nobr>
				</font>
			</span><br/>
					<hdiits:textarea mandatory="true" rows="3" cols="50"
						name="cancelremarks" tabindex="2" id="c_strNames" 
						validation="txt.isrequired" captionid="HRMS.CancellationReason" bundle="${leaveCaption}" maxlength="2000" onkeypress="textAreaLimit(this,document.getElementById('sp_cancelreason_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_cancelreason_cnt'));"/></td>
				</tr>
			</table>
</hdiits:fieldGroup>			
			<table width="100%" border=0>
				<tr>
					<td colspan=5><!-- For attachment : Start--> <jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="cancelLeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />
					</jsp:include> <!-- For attachment : End--></td>
				</tr>
			</table>
	<table border="0" width="100%" >
		<tr>
			<td align="center" colspan="2"><hdiits:formSubmitButton
				type="button" name="formSubmit" value="Submit"
				captionid="HRMS.Submit" tabindex="3" bundle="${leaveCaption}"/>
			<hdiits:button type="button" name="close" value="Close"
				captionid="HRMS.Close" tabindex="4" bundle="${leaveCaption}"
				onclick="goToMainPage(document.forms[0]);" /><hdiits:hidden
				name="leavedtls" caption="a" />
				<hdiits:hidden name="status" caption="status"  default="0"/>
				
				
				</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
	</table>
	<table border=0 style="display:none">
		<tr style="display:none">
			<td><hdiits:select sort="false" name="natureofleave" size="1"
				caption="drop_down" tabindex="5" mandatory="false">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
					<hdiits:option value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}">
						${leavetypes.leaveTypeName}
				</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
	</table>



	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");
document.getElementById('formTable1LeaveAttachment').firstChild.firstChild.style.display='none';
</script>
</c:forEach>
<c:if test="${empty LeaveBal}">
	<script>
	document.forms[0].formSubmit.disabled=true;
	</script>		
		
		</c:if>
<c:remove var="corrId" scope="session"/>
<c:remove var="appliedLeaveData" scope="session"/>
<c:remove var="leaveAttachmentXMLLst" scope="session"/>



		
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
