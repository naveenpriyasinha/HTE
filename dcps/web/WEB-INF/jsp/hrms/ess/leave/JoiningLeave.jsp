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
<c:set var="holidayList" value="${resultValue.holidayList}">
</c:set>
<c:set var="currentDate" value="${resultValue.currentDate}">
</c:set>
<c:set var="ruleKey" value="${sessionScope.Key}">
</c:set>
<c:set var="alreadyApplied" value="${sessionScope.appliedBefore}">
</c:set>
<c:set var="selectedLeave" value="${sessionScope.selectedIndex}"/>
<c:set var="leaveTypesBalMaintain" value="${resultValue.leaveTypesBalMaintain}"/>
<c:set var="hrleavemain" value="${sessionScope.Modified_HrEssLeaveMainTxn}"/>
<c:set var="selectedValue" value="${sessionScope.corrId}"/>
<c:set var ="appliedLeaveData" value="${sessionScope.appliedLeaveData}"/>
<c:set var="attachmentXMLLst" value="${sessionScope.leaveAttachmentXMLLst}"/>
<c:set var="joiningVO" value="${sessionScope.joiningVO}"/>
	<c:set var="leaveTypesBalMaintain" value="${resultValue.leaveTypesBalMaintain}"/>
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
<c:set var="after2JoiningTR" value="display:none"/>
<c:set var="breakInService" value="display:none;"/>
<c:set var="updatedBISToDate" value=""/> 
<c:set var="breakinserviceRadio" value="1" />

<c:set var="joiningHalfday" value="0"/>
<c:if test="${joiningVO.halfday eq 1}">
<c:set var="after2JoiningTR" value="display:visibility"/>
<c:set var="joiningHalfday" value="${joiningVO.halfday}"/>
</c:if>
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
<c:if test="${leaveOtherDtl.hrEssLeaveMst.leaveTypeid eq 13 and not empty leaveOtherDtl.breakinservice}">
						<c:set var="breakInService" value="display:visibility;"/>
					<c:if test="${not empty leaveOtherDtl.breakinserviceTodate}">
						<fmt:formatDate value="${leaveOtherDtl.breakinserviceTodate}" var="updatedBISToDate" pattern="dd/MM/yyyy"/>
						<fmt:formatDate value="${leaveOtherDtl.breakinserviceTodate}" var="updatedBISToDateCopy" pattern="dd/MM/yyyy"/>
					</c:if>
					<c:set var="breakinserviceRadio" value="${leaveOtherDtl.breakinservice}" />
				</c:if>		
		</c:forEach>	
		
		<c:set var="halfdayTR" value="display:none"/>
			<c:if test="${not empty halfday and halfday ne 0}" >
			<c:set var="halfdayTR" value="display:visibility"/>
			</c:if>
			
			<c:set var="before2TR" value="display:none"/>	
			<c:if test="${not empty before2 and halfday eq 1}" >
			<c:set var="before2TR" value="display:visibility"/>
			</c:if>
			
			<c:if test="${empty before2}" >
			<c:set var="before2" value="0"/>
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
var leavetypeSelected="";
var noofdaysApplied=0.0;
var leaveTypesBalMaintain='${leaveTypesBalMaintain}';
function checkHalfDay_joining()
{

 var nod=0;
 var  natureofleave=document.frmleaveapply.natureofleave.value.split('_');
	if(document.forms[0].fromdate.value!="" &&  document.forms[0].todate.value!=""){
			var valueOfFromDate = document.frmleaveapply.fromdate.value;
			if(eval(natureofleave[0])==1)
			{
				document.getElementById("joining_halfday").style.display="";
				nod=Differ(document.forms[0].fromdate,document.forms[0].todate,'${holidayList}',document.forms[0],alertMsgDates);
				if(document.getElementById('halfdayYes').status==true){
						document.getElementById("tag_joining").style.display="";
						document.getElementById("nod_joining").style.display="";
				}
				else{
				document.getElementById("tag_joining").style.display="";
				document.getElementById("nod_joining").style.display="";
				document.getElementById("nod_joining").innerHTML="";

				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod_joining").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);				
				}

				//document.getElementById("joining_halfday").style.display="none";
				}
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
					if(eval(nod)>0.5 && (document.getElementById("firstdayhalfdayYes").status==true
						|| document.getElementById("lastdayhalfdayYes").status==true))
				{
					document.getElementById("nod_joining").innerHTML=eval(nod);
					document.frmleaveapply.noofdays.value=eval(nod);
				}
				else if(eval(nod)>1 && (document.getElementById("firstdayhalfdayNo").status==true
						|| document.getElementById("lastdayhalfdayNo").status==true) && document.getElementById('halfdayYes').status==true )
				{						
					nod=Differ(document.forms[0].fromdate,document.forms[0].todate,'${holidayList}',document.forms[0],alertMsgDates);
					if(!isNaN(eval(nod))){
						document.getElementById("nod_joining").innerHTML=eval(nod+0.5);
						document.frmleaveapply.noofdays.value=eval(nod+0.5);
					}
				}
				else if (eval(nod)<=0.5)
				{
					document.getElementById("nod_joining").innerHTML=eval(nod);
					document.frmleaveapply.noofdays.value=eval(nod);
				}

				
				}

			
			}		
		else if(natureofleave[0]=='2')
			{							
				nod=Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate,'${holidayList}',document.frmleaveapply,alertMsgDates);
				document.getElementById("nod_joining").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0))
				{
					document.getElementById("tag_joining").style.display="";
					document.getElementById("nod_joining").innerHTML="";
					document.getElementById("halfday").style.display="none";
					document.getElementById("nod_joining").innerHTML=eval(nod);
					document.frmleaveapply.noofdays.value=eval(nod);
				}
				document.getElementById("joining_halfday").style.display="none";				
			}
			else{
				var Date1=document.frmleaveapply.fromdate.value.split("/");
				var Date2=document.frmleaveapply.todate.value.split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
				nod=dateDifference(new Date(Date1),new Date(Date2),alertMsgDates);
				document.getElementById("halfday").style.display="none";
				document.getElementById("tag_joining").style.display="";
				document.getElementById("nod_joining").style.display="";
				document.getElementById("nod_joining").innerHTML="";
				document.getElementById("joining_halfday").style.display="none";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod_joining").innerHTML=eval(nod);
				document.frmleaveapply.noofdays.value=eval(nod);				
				}

			}
			if(document.frmleaveapply.fromdate.value=="")
			{
				document.frmleaveapply.fromdate.value=valueOfFromDate;
			}

	if(eval(nod) <=eval(0)){
	 document.forms[0].Submit.disabled=false;
	return false;
	}
	else{
	return true;
	}
}

}


</script>

<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavejoining.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>
<script>
var sysDate='${currentDate}';
</script>
<%
try {
%>



<hdiits:form name="frmleaveapply" validate="true"
	action="hdiits.htm?actionFlag=insertJoiningDetail" method="post"
	encType="multipart/form-data">

<hdiits:hidden name="counter" caption="counter"/>

	<table class="tabtable" border=0>
			<tr id="messages" style="display:none">
			<td colspan=5>
			<div id="alertMsg"
				style="margin: 5px 0px; font-family: arial; color: #333333;border: solid 1px #6B2700; width: 100%; clear: left;">
			<c:if test="${alreadyApplied==false}">
				<font color="red"><b><fmt:message bundle="${alertLables}"
					key="HRMS.alreadyApplied" /></b></font>
				<script>
document.getElementById("messages").style.display="";
</script>
			</c:if></div>

			</td>
		</tr>
		</table>
<table width="100%"> 
		<tr>
			<td  colspan="4"><%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>
		<table width="100%" border="0">
		<tr>
			<td colspan="5">
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.Balanceavailable" bundle="${leaveCaption}"  id="LeaveBalanceGrp">	
				<%@ include file="LeaveBalance.jsp"%>
			</hdiits:fieldGroup>	
			</td></tr>
			</table>
<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.LeaveDetail" bundle="${leaveCaption}"  id="LeaveDetailsGrp">	
			<table border="0" width="100%">
				<tr>
					<td width="20%">
								<hdiits:caption captionid="HRMS.appliedbetween1"  bundle="${leaveCaption}" /></td>
								
								<td >
								<hdiits:select sort="false" name="appliedbetween"
						size="1" captionid="HRMS.appliedbetween1"
						validation="sel.isrequired" mandatory="true"
						onchange="getLeaveDataJoining(this,document.frmleaveapply);"
						tabindex="1" bundle="${leaveCaption}">
						<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="hrleavemain" items="${app_penLst}">
							<hdiits:option value="${hrleavemain.corrid}">
								<fmt:formatDate value="${hrleavemain.appLeaveFrom}"
									pattern="dd-MM-yyyy" />&nbsp;
			<fmt:message key="HRMS.Dash" bundle="${leaveCaption}"/>&nbsp;
				 <fmt:formatDate value="${hrleavemain.appLeaveTo}"
									pattern="dd-MM-yyyy" />
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					
					<script>
if("${selectedValue}"!=""){
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

<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd-MM-yyyy" var="sancStartDate"/>
${sancStartDate}
<script>
sanc_fromDate="${sancStartDate}";
</script>


<c:if test="${empty sancFromDate}">
-
</c:if>
  </td>
    <td>
	<hdiits:caption captionid="HRMS.sanc_todate1" bundle="${leaveCaption}"/>

  </td>
<td id="sanc_todate" align="left">

<fmt:formatDate value="${appliedLeaveData.appLeaveTo}"  pattern="dd-MM-yyyy" var="sancEndDate"/>
${sancEndDate}
<script>
sanc_toDate="${sancEndDate}";
</script>
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
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd-MM-yyy" />
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
			<fmt:message key="HRMS.halfdayon"/>&nbsp;<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="dd-MM-yyyy" /> 			</td>
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
						<table id="lvdtl_display" width="100%"  style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border=1>
				<tbody>
				<%@include file="CmnCombinationLeaveView.jsp"%>
				</tbody>
			</table>
			<table>
			<tr id="breakinservice" style="${breakInService}">
				<td colspan="5">
				<fieldset>
				<legend>
				<hdiits:caption captionid="HRMS.LWPBIS" bundle="${leaveCaption}"/>
				</legend>
				<table  border="0">
				<tr>
				<td>
				<c:if test="${empty breakinserviceRadio}">
				<c:set var="breakinserviceRadio" value="1"/>
				</c:if>
				<hdiits:radio name="rad_breakinservice" captionid="HRMS.Breakinservice" bundle="${leaveCaption}"  value="0" default="${breakinserviceRadio}" readonly="true" mandatory="false" />
				</td>
				<td>
				<hdiits:radio name="rad_breakinservice" captionid="HRMS.continuationinservice" bundle="${leaveCaption}" value="1"  mandatory="false" readonly="true" default="${breakinserviceRadio}" />
				</td>
				</tr>
				<tr>
					<td><hdiits:caption captionid="HRMS.fromdate" bundle="${leaveCaption}" /></td>
					<td>
					<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyyy" var="bisFromDate"/>
					<hdiits:text  name="BIS_fromdate" caption="BISFromDate" readonly="true" default="${bisFromDate}"/></td>
					<td><hdiits:caption captionid="HRMS.todate" bundle="${leaveCaption}" /></td>
					<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="yyyy-MM-dd" var="lwpEndDate"/>
				 <c:if test="${not empty lwpEndDate }">
					 <c:set var="lwpEndDate" value="${lwpEndDate}" />
				 </c:if>
					<td>
					<c:if test="${not empty updatedBISToDateCopy}" > 
						<hdiits:text name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${updatedBISToDateCopy}" readonly="true"/>
					</c:if>
					<c:if test="${empty updatedBISToDate}" > 
					<hdiits:text name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${lwpEndDate}" />
					</c:if>

					
					</td>
				</tr>
				</table>
				</fieldset>
				</td>
				</tr>	
			</table>
			</hdiits:fieldGroup>
				
<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.JoiningDetail" bundle="${leaveCaption}"  id="JoiningDetailsGrp">		
			<table width="100%">
				<tr>
					<td width="10%"><hdiits:caption captionid="HRMS.DutyResumptionDate"  bundle="${leaveCaption}"/></td>
					<td>
		<hdiits:dateTime name="DutyResumptionDate" mandatory="true"	captionid="HRMS.DutyResumptionDate"
		 tabindex="2" bundle="${leaveCaption}" showCurrentDate="true" afterDateSelect="document.forms[0].joinedDays.value='';"/>
         </td>
	
			</tr>

				<tr id="halfday_jng">
					<td><hdiits:caption captionid="HRMS.halfday" bundle="${leaveCaption}"/></td>
					<td><hdiits:radio name="halfday_joining" id="halfdayjoiningYes"
						value="1" mandatory="false" onclick="checkHalfDayJoining();" default="${joiningHalfday}" 
						tabindex="3" captionid="HRMS.Yes"  bundle="${leaveCaption}" /> 
						<hdiits:radio name="halfday_joining" id="halfdayjoiningNo" value="0" mandatory="false"
						onclick="checkHalfDayJoining();" default="${joiningHalfday}" tabindex="4" captionid="HRMS.No"  bundle="${leaveCaption}"/>
						<span id="before2_lbl_jng" style="${after2JoiningTR}">
						<c:if test="${joiningVO.halfday eq 1}">
						<hdiits:caption captionid="HRMS.after2pm"  bundle="${leaveCaption}"/>
						</c:if>
						
						</span></td>
				</tr>


				<tr>
					<td width="20%"><hdiits:caption captionid="HRMS.LeaveReason"  bundle="${leaveCaption}" /></td>
					<td>
				<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_joiningremarks_cnt">2000</label>]</nobr>
				</font>
				</span><br/>
					
					
					<hdiits:textarea mandatory="true" rows="5" cols="60"
						name="joiningRemarks" tabindex="5" id="c_strNames"
						validation="txt.isrequired" captionid="HRMS.leavereason" bundle="${leaveCaption}" default="${joiningVO.remarks}"  maxlength="2000" onkeypress="textAreaLimit(this,document.getElementById('sp_joiningremarks_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_joiningremarks_cnt'));"/></td>
				</tr>


			</table>
</hdiits:fieldGroup>




			<!-- ******************** style="display:none"-->

			<div id="modifyLeave" name="leave" style="display:none">
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.leave_entry_detail1" bundle="${LeaveCaption}"  id="modifyLeaveEntryGrp">	
		<table width="100%">		
			<tr>
			<td><hdiits:caption captionid="HRMS.comb_leave"  bundle="${leaveCaption}"/></td>
			<td>
				<hdiits:radio captionid="HRMS.Yes" name="combinationleave"
				value="1" mandatory="false" onclick="showbutton(this,1);" 
default="${isCombination}"  bundle="${leaveCaption}"	tabindex="1" />
				 <hdiits:radio	captionid="HRMS.No" bundle="${leaveCaption}" name="combinationleave" value="0"
				mandatory="false" default="${isCombination}" onclick="showbutton(this,1);"
				tabindex="2" />
			<span id="leavedetail" style="${combTable}">
				<hdiits:button 
				type="button" name="leaveDetail" captionid="HRMS.EnterLeaveDetail1"
				bundle="${leaveCaption}" onclick="return applyLeaveDetail();"
				tabindex="3" />
			</span>	
			</td>
		</tr>

			<tr id="natureofleave_joining_mod" style="${normalLeaveTbl}">
			<td><hdiits:caption captionid="HRMS.NatureOfLeave"  bundle="${leaveCaption}"/></td>
			<td><c:set var="counter" value="0" /> 
			<hdiits:select
				sort="false" name="natureofleave" captionid="HRMS.NatureOfLeave"
				size="1" mandatory="true" onchange="showOrd(this);" tabindex="4"
				bundle="${leaveCaption}">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
					<hdiits:option value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}" >
										${leavetypes.leaveTypeName}
										</hdiits:option>
					
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
			</hdiits:select>
			<c:if test="${appliedLeaveData.combFlag==0}">
			<script>
			 document.forms[0].natureofleave.value="${leavetypeSelected}";
			</script>
			</c:if>
			</td>

			<td id="tag_joining" style="${normalLeaveTbl}"><hdiits:caption captionid="HRMS.NoD1"  

bundle="${leaveCaption}"/></td>
			<td id="nod_joining" style="${normalLeaveTbl}">${nod}</td>
<script>
noofdaysApplied="${nod}";
</script>
		</tr>

		
		<tr id="fromdate_joining" style="${normalLeaveTbl}">
			<td width="20%"><hdiits:caption captionid="HRMS.FromDate" bundle="${leaveCaption}"/> 

(DD/MM/YYYY)</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyyy" var="fromDate"/>
 <c:if test="${ not empty fromDate}">
 <c:set var="fromDate" value="${fromDate}" />
  </c:if>
			<td width="42%"><hdiits:text name="fromdate" id="fromdate" captionid="HRMS.fromdate"
				bundle="${leaveCaption}" mandatory="true" readonly="true" tabindex="5" default="${fromDate}" 
				></hdiits:text></td>
			<td><hdiits:caption captionid="HRMS.ToDate"  bundle="${leaveCaption}"/> (DD/MM/YYYY)

</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="yyyy-MM-dd" var="toDate"/>
 <c:if test="${not empty toDate }">
 <c:set var="toDate" value="${toDate} 00:00:00" />
  </c:if>
		
<td>
<hdiits:dateTime name="todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="true"	afterDateSelect="checkHalfDay_joining();" tabindex="6" default="${toDate}"></hdiits:dateTime></td>

		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.ContactAddress" bundle="${leaveCaption}" /></td>
			<td>
			<span>
			<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;

[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message 

key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label 

id="sp_contactaddress_cnt">2000</label>]</nobr>
			</font>
			</span>

			<hdiits:textarea mandatory="true" rows="2" cols="50"
				name="contactaddress" tabindex="7" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.contactaddress"
				bundle="${leaveCaption}" maxlength="2000" 

default="${appliedLeaveData.empAddress}" onkeypress="textAreaLimit(this,document.getElementById

('sp_contactaddress_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_contactaddress_cnt'));" 

/></td>

			<c:set var="teleList"  value='${fn:split(appliedLeaveData.phoneno, "-")}'/>
			<c:set var="telecode" value=""/>
			<c:if test="${teleList[0] ne 'xxxx'}">
				<c:set var="telecode" value="${teleList[0]}"/>
			</c:if>
			<td><hdiits:caption captionid="HRMS.Telephone"  bundle="${leaveCaption}"/>
			</td>
			<td align="left"><hdiits:number mandatory="false" name="telecode"
				tabindex="8" id="c_strNames" captionid="HRMS.Tele_code" 
				bundle="${leaveCaption}" default="${telecode}"  maxlength="4"  size="2" /> - 

<hdiits:number
				mandatory="false" name="telephone" tabindex="9" id="c_strNames"  
				captionid="HRMS.telephone" bundle="${leaveCaption}" default="${teleList[1]}" 

maxlength="10" size="10"/>
				<br>
				
 					<table  border=0 align="left">
 				<tr>	
				<td align="left">
				<nobr>
				 <hdiits:caption captionid="HRMS.STD"  bundle="${leaveCaption}"/>
					-
				 <hdiits:caption captionid="HRMS.phonemobile"  bundle="${leaveCaption}"/>
				</nobr>
				</td>
 				</tr>				
 					</table>			
				</td>

		</tr>
		<tr id="ordinarycir" style="display:none">
			<td><hdiits:caption captionid="HRMS.ordcir"  bundle="${leaveCaption}"/></td>
			<td align="left"><hdiits:radio name="ordcir" value="1" captionid="HRMS.Yes"  bundle="${leaveCaption}"
				mandatory="false" tabindex="10"  />&nbsp;&nbsp;&nbsp;
				 <hdiits:radio value="0"
				name="ordcir" mandatory="false" tabindex="11" captionid="HRMS.No"  bundle="${leaveCaption}" default="0"/>
&nbsp;&nbsp;</td>
			<td></td>
		</tr>
			
		
		<tr id="joining_halfday" style="${halfdayTR}">
			<td><hdiits:caption captionid="HRMS.halfday"  bundle="${leaveCaption}"/></td>
			<td><hdiits:radio name="halfday" id="halfdayYes" value="1" captionid="HRMS.Yes"  bundle="${leaveCaption}"
				mandatory="false" tabindex="12" default="${halfday}" onclick="checkHalfDay_joining();" />
				 <hdiits:radio name="halfday" id="halfdayNo" captionid="HRMS.No"  bundle="${leaveCaption}"
				value="0" default="${halfday}" mandatory="false" tabindex="13" onclick="checkHalfDay_joining();"
				/></td>
			<td id="before2_lbl_joining" style="${before2TR}"><hdiits:caption captionid="HRMS.before2"  

bundle="${leaveCaption}"/></td>
			<td id="before2_radio_joining" style="${before2TR}">
			<hdiits:radio name="before2" id="before2Yes" default="${before2}" captionid="HRMS.Yes"
bundle="${leaveCaption}" value="1" mandatory="false"
				tabindex="14" onclick="checkHalfDay_joining();" />
			 <hdiits:radio id="before2No" default="${before2}" captionid="HRMS.No" bundle="${leaveCaption}" 
name="before2" value="0" mandatory="false"
				 tabindex="15" onclick="checkHalfDay_joining();" />
				</td>

		</tr>
			
		<tr id="First_Day_joining" style="${startday_enddayTR}">
			<td id="firstday_halfday_joining">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyy" />
			</td>
			<td>
			<hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}" name="firstday"	id="firstdayhalfdayYes" 

value="1"  default="${halfdayOnfirstday}" mandatory="false" tabindex="16"
				onclick="checkHalfDay_joining();" />
				
			<hdiits:radio captionid="HRMS.No"  bundle="${leaveCaption}" name="firstday" 
				id="firstdayhalfdayNo" value="0" mandatory="false" 

default="${halfdayOnfirstday}"
				tabindex="17" onclick="checkHalfDay_joining();" /></td>
			<td id="before2_radio_firstday_joining" ><hdiits:caption captionid="HRMS.after2pm"  

bundle="${leaveCaption}"/></td>
		</tr>


		<tr id="Last_Day_joining" style="${before2PMOnLastdayTD}">
			<td id="lastday_halfday_joining">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;<fmt:formatDate 

value="${appliedLeaveData.appLeaveTo}" pattern="dd/MM/yyy" /> 			</td>
			<td><hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}" name="lastday"
				id="lastdayhalfdayYes" value="1" mandatory="false" tabindex="18" 
default="${halfdayOnLastday}"
				onclick="checkHalfDay_joining();" />
			<hdiits:radio captionid="HRMS.No"  bundle="${leaveCaption}" name="lastday"
				id="lastdayhalfdayNo" value="0" mandatory="false" default="${halfdayOnLastday}"
				tabindex="19" onclick="checkHalfDay_joining();" /></td>
			<td id="before2_radio_lastday_joining"><hdiits:caption captionid="HRMS.before2pm"  
bundle="${leaveCaption}"/></td>
		</tr>
		<tr>
			<c:set var="prefixSelected"	value="0"/>
			<c:set var="prefixFromDatesTR"	value="display:none"/>
			<c:if test="${not empty appliedLeaveData.prefixFromdate}">
			<c:set var="prefixSelected"	value="1"/>
			<c:set var="prefixFromDatesTR"	value="display:visible"/>
			</c:if>

			<td><hdiits:caption captionid="HRMS.Prefix"  bundle="${leaveCaption}"/></td>
			<td align="left"><hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}" name="prefix"
				value="1" onclick="return showPrefixDate(this);" tabindex="20" 

default="${prefixSelected}"
				condition="return checkPrefixDate(document.frmleaveapply);" />
				<hdiits:radio captionid="HRMS.No"  bundle="${leaveCaption}"
				name="prefix" value="0" tabindex="21" default="${prefixSelected}"
				onclick="return showPrefixDate(this);" />&nbsp;&nbsp;</td>
		</tr>
		
		
		
		<tr name="yes1" id="yes1" width="100%" border="0" style="${prefixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.PrefixFromDate"  bundle="${leaveCaption}"/>
			(DD/MM/YYYY)</td>
		
		<fmt:formatDate value="${appliedLeaveData.prefixFromdate}" pattern="yyyy-MM-dd" 

var="prefixFromdate"/>
 <c:if test="${ not empty prefixFromdate}">
 <c:set var="prefixFromdate" value="${prefixFromdate} 00:00:00" />
  </c:if>
		
			<td><hdiits:dateTime name="prefixfromdate" mandatory="true"
				captionid="HRMS.prefixfromdate" tabindex="22"
				bundle="${leaveCaption}" maxvalue="31/12/9999" 

default="${prefixFromdate}"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.PrefixToDate"  bundle="${leaveCaption}"/> 

(DD/MM/YYYY)</td>
			<td>
			
			<fmt:formatDate value="${appliedLeaveData.prefixTodate}" pattern="yyyy-MM-dd" 

var="prefixTodate"/>
 <c:if test="${ not empty prefixTodate}">
 <c:set var="prefixTodate" value="${prefixTodate} 00:00:00" />
  </c:if>
		
			
			<hdiits:dateTime name="prefixtodate" mandatory="true"
				captionid="HRMS.prefixtodate" tabindex="23" bundle="${leaveCaption}" 

default="${prefixTodate}"
				maxvalue="31/12/9999"></hdiits:dateTime></td>

		</tr>
		
		<c:set var="suffixSelected"	value="0"/>
			<c:set var="suffixFromDatesTR"	value="display:none"/>
			<c:if test="${not empty appliedLeaveData.suffixFromdate}">
			<c:set var="suffixSelected"	value="1"/>
			<c:set var="suffixFromDatesTR"	value="display:visible"/>
			</c:if>
		
		<tr>
			<td><hdiits:caption captionid="HRMS.Suffix"  bundle="${leaveCaption}"/></td>
			<td align="left"><hdiits:radio captionid="HRMS.Yes"  bundle="${leaveCaption}" name="suffix" default="${suffixSelected}"
				value="1" tabindex="24" onclick="return showSuffixDate(this);" 

/><hdiits:radio  captionid="HRMS.No"  bundle="${leaveCaption}"
				name="suffix" value="0" tabindex="25"
				onclick="return showSuffixDate(this)" default="${suffixSelected}"/></td>
			<td></td>
		</tr>

<fmt:formatDate value="${appliedLeaveData.suffixFromdate}" pattern="yyyy-MM-dd" var="suffixFromdate"/>
 <c:if test="${ not empty suffixFromdate}">
 <c:set var="suffixFromdate" value="${suffixFromdate} 00:00:00" />
  </c:if>

<fmt:formatDate value="${appliedLeaveData.suffixTodate}" pattern="yyyy-MM-dd" var="suffixTodate"/>
 <c:if test="${ not empty suffixTodate}">
 <c:set var="suffixTodate" value="${suffixTodate} 00:00:00" />
  </c:if>
				


		<tr name="yes2" id="yes2" style="${suffixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.SuffixFromDate"  bundle="${leaveCaption}"/>
			(DD/MM/YYYY)</td>
			<td><hdiits:dateTime name="suffixfromdate" mandatory="true"
				captionid="HRMS.suffixfromdate" tabindex="26"
				bundle="${leaveCaption}" default="${suffixFromdate}" 

maxvalue="31/12/9999"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.SuffixToDate"  bundle="${leaveCaption}"/> 

(DD/MM/YYYY)</td>
			<td><hdiits:dateTime name="suffixtodate" mandatory="true"
				captionid="HRMS.suffixtodate" tabindex="27" bundle="${leaveCaption}" 

default="${suffixTodate}"
				maxvalue="31/12/9999"></hdiits:dateTime></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.LeaveReason"  bundle="${leaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message 

key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" 

bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leavereason_cnt">2000</label>]</nobr>
	</font>
			</span>
			<hdiits:textarea mandatory="true" rows="5"
				cols="40" name="leavereason" tabindex="28" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.leavereason"
				bundle="${leaveCaption}" maxlength="2000" default="${appliedLeaveData.reason}" 

onkeypress="textAreaLimit(this,document.getElementById('sp_leavereason_cnt'));" onblur="textAreaLimit

(this,document.getElementById('sp_leavereason_cnt'));"/></td>

			<td><hdiits:caption captionid="HRMS.Remarks"  bundle="${leaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message 

key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" 

bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leaveremarks_cnt">2000</label>]</nobr>
			</font>
			</span>
			<hdiits:textarea rows="5" cols="50" name="remarks"
				tabindex="29" id="c_strNames" 
				captionid="HRMS.remarks" bundle="${leaveCaption}" mandatory="false"
				maxlength="2000" default="${appliedLeaveData.remarks}" 

onkeypress="textAreaLimit(this,document.getElementById('sp_leaveremarks_cnt'));" onblur="textAreaLimit

(this,document.getElementById('sp_leaveremarks_cnt'));" /></td>

			
		</tr>


		<tr>
			<td>
			<table class="tabtable" name="no2" id="no2" style="display:none">
				<tr>
					<td></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	<table id="lvdtl"  width="100%"  style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border="1">

		<tbody>
			<c:set var="showCheckBoxInCombinationLeave" value="true"/>
			<%@ include file="CmnCombinationLeave.jsp"%>
		</tbody>
		<tbody>
			<tr style="display:none">
				<td colspan="5" align="right" style="display:none"><font
					color="Red"><hdiits:caption captionid="HRMS.TotalDays"  

bundle="${leaveCaption}"/></font>
				</td>
				<td id="totalDays" style="display:none">0</td>
			</tr>
			<tr>
				<td align="center" colspan="6" id="combiButtons"><hdiits:button type="button"
					name="edit" captionid="HRMS.Edit" bundle="${leaveCaption}"
					onclick="deleteRow(1,'',alertMsg);" tabindex="30" /> <hdiits:button
					type="button" name="Delete" captionid="HRMS.Delete"
					bundle="${leaveCaption}"  onclick="deleteLeave();"
					tabindex="31" /></td>
			</tr>
</tbody>

		</table>
		</hdiits:fieldGroup>
		<BR>
		<hdiits:fieldGroup bundle="${leaveCaption}" style="display:none" expandable="false" collapseOnLoad="false" titleCaptionId="HRMS.Agreement" id="agreement">
				<table border="0" width="100%">
				<tr><td><hdiits:checkbox id="chkAgree1" name="chkAgree1" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement1" bundle="${leaveCaption}"></fmt:message></td></tr>
				<tr><td><hdiits:checkbox id="chkAgree2" name="chkAgree2" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement2" bundle="${leaveCaption}"></fmt:message></td></tr>
				</table>	
		</hdiits:fieldGroup>
		<BR>
					<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="LeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
						</jsp:include>
					<!-- For attachment : End-->
		
<!-- End  -->
		
			</div>

	<table border="0" width="100%">
<tbody>

<tr style="display:none">
	<td>For officers of level SP informed the concerned DIG/IG 

	<hdiits:radio caption="" name="informsuperior" value="1"  default="1"  mandatory="false"  tabindex="33"/>&nbsp;&nbsp;<fmt:message key="HRMS.Yes"/>
	<hdiits:radio caption="" name="informsuperior" value="0" mandatory="false" tabindex="34"/>&nbsp;&nbsp;<fmt:message key="HRMS.No"/>
	<hdiits:hidden name="noofdays" caption="nod" default="0"></hdiits:hidden> 
	<hdiits:hidden name="tempsave" caption="tempsave" default="0"></hdiits:hidden> 
   <hdiits:hidden name="firstDay_comb" caption="firstDay" default="0"/>
    <hdiits:hidden name="halfday_comb" caption="halfday" default="0"></hdiits:hidden> 
   <hdiits:hidden name="before2_comb" caption="before2" default="0" ></hdiits:hidden> 
    <hdiits:hidden name="ordi_comb" caption="ordinary_comb"></hdiits:hidden>
	<hdiits:hidden name="parentid" caption="tempsave" default="${appliedLeaveData.leaveid}"/>
   <hdiits:hidden name="status" caption="status" default="0"></hdiits:hidden> 
    <hdiits:hidden name="leaveid" caption="leaveid" default="0"/> 
    <hdiits:hidden name="ismodified" caption="modified" default="1"></hdiits:hidden> 
   <hdiits:hidden name="srno" caption="srno"></hdiits:hidden> 
    <hdiits:hidden name="selectedIndex" caption="selectedIndex"/>
    
    <hdiits:hidden name="leavedtls" caption="a"/>
	</td>
    
			
			

			<td colspan="2">
			&nbsp;
			</td>
</tr>
</tbody>
</table>

							

			<!-- **************** --> <!-- 
**** cancel Leave starts
 -->
			<div id="cancelLeave" style="display:none">
<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.CancellationDetail" bundle="${leaveCaption}"  id="cancelDtlGrp">	
			<table width="100%">
				<tr>
					<td colspan="6">
					<table width="100%" border="0">

						<tr>
							<td width="20%"><hdiits:caption captionid="HRMS.CancellationReason"  bundle="${leaveCaption}"/></td>

							<td>
							<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_cancelreason_cnt">2000</label>]</nobr>
				</font>
				</span><br/>
							<hdiits:textarea mandatory="false" rows="3" cols="50"
								name="cancelremarks" tabindex="40" id="c_strNames"
								validation="txt.isrequired" captionid="HRMS.CancellationReason"
								bundle="${leaveCaption}" maxlength="2000" onkeypress="textAreaLimit(this,document.getElementById('sp_cancelreason_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_cancelreason_cnt'));"/></td>

						</tr>
					</table>
					<table width="100%" border=0>
						<tr>
							<td colspan="6" id="cancelAttachment"><!-- For attachment : Start-->
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="cancelLeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />
					</jsp:include> 
				 <!-- For attachment : End--></td>
						</tr>
					</table>


					</td>
				</tr>
			</table>
</hdiits:fieldGroup>
			<!-- 
 cancel Leave Ends
  --></div>

			<table width="100%">
				<tr>
					<td align="center" colspan="3"><!-- temprorySave(); --> <hdiits:button
						type="button" name="Submit" value="Submit" captionid="HRMS.Submit"
						tabindex="41" bundle="${leaveCaption}"
						onclick="document.frmleaveapply.tempsave.value='0';submitData();" />
					<hdiits:button type="button" name="close" value="close"
						captionid="HRMS.Close" tabindex="42" bundle="${leaveCaption}"
						onclick="goToMainPage(document.frmleaveapply);" /> <hdiits:hidden
						name="leavedtls" caption="a" />
						 </td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>




<c:if test="${empty LeaveBal}">
	<script>
//	document.forms[0].Submit.disabled=true;
	</script>		
		
		</c:if>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="appliedbetween,DutyResumptionDate,joiningRemarks,natureofleave,fromdate,todate,contactaddress,remarks,leavereason,cancelremarks"/>
	<!-- 
	<div align="center" >
	<a href="hrms.htm?actionFlag=modifyLeave" target="_blank"><fmt:message key="HRMS.Leavemodification"/></a> &nbsp;
	<a href="hrms.htm?actionFlag=cancelLeave" target="_blank"><fmt:message key="HRMS.Leavecancellation"/></a>
	
	</div>
 -->
<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");
</script>
</c:forEach>

<hdiits:hidden name="joinedDays" caption="jnddays" default=""></hdiits:hidden> 
</hdiits:form>

<script>
	document.forms[0].DutyResumptionDate.setAttribute("onchange",function(){changeJoinedDays()});
	document.forms[0].fromdate.setAttribute("onchange",function(){checkHalfDay_joining()});
	document.forms[0].todate.setAttribute("onchange",function(){checkHalfDay_joining()});

function changeJoinedDays(){
document.forms[0].joinedDays.value='';

}
			function showbutton(component,flag){					
					var joinedDays=document.forms[0].joinedDays.value;
					var remarks_joining=document.forms[0].joiningRemarks.value;
					var  selectedIndexLeave=document.forms[0].appliedbetween.selectedIndex;
					var parentid=document.forms[0].parentid.value;
				if(flag==1){
					if(component.value=='1'){
						if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.LEAVEDETAIL_REMOVED"/>')){
							document.getElementById("leavedetail").style.display="";
							document.getElementById("natureofleave_joining_mod").style.display="none";
							document.getElementById("fromdate_joining").style.display="none";
							document.getElementById("ordinarycir").style.display="none";
							document.getElementById("agreement").style.display="none";
							var erase="";
							if(leaveArr.length==0)
							{
								leaveCounter=0;
								document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase;								
							}							
							document.getElementById("yes1").style.display="none";
							document.getElementById("yes2").style.display="none";
							var dutyResumptionDate = document.forms[0].DutyResumptionDate.value;
							document.forms[0].reset();
							document.forms[0].DutyResumptionDate.value=dutyResumptionDate;
							document.forms[0].joinedDays.value=joinedDays;
							document.forms[0].joiningRemarks.value=remarks_joining;
							document.forms[0].appliedbetween.options[selectedIndexLeave].selected=true;
							document.forms[0].parentid.value=parentid;
							component.status=true;												
						}
						else{
							if(document.getElementById("leavedetail").style.display!="")
								{document.forms[0].combinationleave[1].status=true;}
						}
					}
				 	else{
						if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.LEAVEDETAIL_REMOVED"/>')){
							document.getElementById("natureofleave_joining_mod").style.display="";
							document.getElementById("fromdate_joining").style.display="";
							document.getElementById("leavedetail").style.display="none";
							document.getElementById("lvdtl").style.display="none";
							var erase="";
							document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
							leaveArr=null;
							leaveArr=new Array();
							document.getElementById("yes1").style.display="none";
							document.getElementById("yes2").style.display="none";
							var dutyResumptionDate = document.forms[0].DutyResumptionDate.value;
							document.forms[0].reset();
							document.forms[0].DutyResumptionDate.value=dutyResumptionDate;
							document.forms[0].joinedDays.value=joinedDays;
							document.forms[0].joiningRemarks.value=remarks_joining;
							document.forms[0].appliedbetween.options[selectedIndexLeave].selected=true;
							document.forms[0].parentid.value=parentid;
							component.status=true;
						}
						else{
							if(document.getElementById("leavedetail").style.display=="")
								{document.forms[0].combinationleave[0].status=true;}
						}
					}
			}
		else{
					if(component.value=='1'){
						document.getElementById("leavedetail").style.display="";
						document.getElementById("natureofleave_joining_mod").style.display="none";
						document.getElementById("fromdate_joining").style.display="none";
						document.getElementById("ordinarycir").style.display="none";
						document.getElementById("agreement").style.display="none";
					}
				else{
						document.getElementById("natureofleave_joining_mod").style.display="";
						document.getElementById("fromdate_joining").style.display="";
						document.getElementById("leavedetail").style.display="none";
						document.getElementById("lvdtl").style.display="none";
						var erase="";
						document.getElementById("lvdtl").getElementsByTagName("tbody")[0].innerText=erase; 
						leaveArr=null;
						leaveArr=new Array();
				}
				
				
				}
	}
</script>
<c:if test="${fn:trim(ruleKey) ne ''}">
	<script>
alertMessages('${ruleKey}');
//populateJoiningModifyForm(document.forms[0],lineBreak('${currentLeaveDet}'),lineBreak('${previousLeaveXML}'),'${selectedIndex}');
document.getElementById('modifyLeave').style.display="";
</script>


</c:if>
<script>
if(leavetypeSelected=='3_3_5')
{
	document.getElementById("agreement").style.display="";
}
</script>
<c:remove var="Key" scope="session"/>
<c:remove var="appliedBefore" scope="session"/>
<c:remove var="selectedIndex" scope="session"/>
<c:remove var="Modified_HrEssLeaveMainTxn" scope="session"/>
<c:remove var="corrId" scope="session"/>
<c:remove var="appliedLeaveData" scope="session"/>
<c:remove var="leaveAttachmentXMLLst" scope="session"/>
<c:remove var="joiningVO" scope="session"/>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

