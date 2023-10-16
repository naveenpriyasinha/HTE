<%@ include file="./alertMessage.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
	<c:set var="resultObj" value="${result}" > </c:set>	
	<c:set var="resultValue" value="${resultObj.resultValue}"> </c:set>
	<c:set var="LeaveTList" value="${resultValue.leaveTypeList}"> </c:set>
	<c:set var="app_penLst" value="${resultValue.approve_pendingList}"> </c:set>
	<c:set var="holidayList" value="${resultValue.holidayList}"> </c:set>
	<c:set var="latestAppLeave" value="${resultValue.latestApprLeave}">
</c:set>
	<c:set var="LeaveBal" value="${resultValue.leaveBal}"> </c:set>
	<c:set var="gender" value="${resultValue.gender}"> </c:set>
	<c:set var="ruleKey" value="${resultValue.Key}"> </c:set>
	<c:set var="alreadyApplied" value="${resultValue.appliedBefore}"> </c:set>
	<c:set var="selectedIndex" value="${resultValue.selectedIndex}"> </c:set>	
	<c:set var="appliedLeaveData" value="${resultValue.appliedLeaveData}" />
	<c:set var="selectedValue" value="${resultValue.corrId}"/>
	<c:set var="attachmentXMLLst" value="${resultValue.leaveAttachmentXMLLst}"/>
	<c:set var="hrleavemain" value="${sessionScope.Modified_HrEssLeaveMainTxn}"/>
	<c:set var="leaveTypesBalMaintain" value="${resultValue.leaveTypesBalMaintain}"/>

	
	<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/modifyleaveRequest.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>

	<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>

 <fmt:setBundle basename="resources.ess.leave.AlertMessages" var="alertLables" scope="request"/> 		
	<fmt:setBundle basename="resources.ess.leave.LeaveCaption" var="leaveCaption" scope="request"/> 

<script>
var holidayList='${holidayList}';
var leaveTypesBalMaintain='${leaveTypesBalMaintain}';
function checkHalfDay()
{
 var nod=0;
 var  natureofleave=document.forms[0].natureofleave.value.split('_');
	if(document.forms[0].fromdate.value!="" &&  document.forms[0].todate.value!=""){
			if(natureofleave[0]=='1'){
				nod=Differ(document.forms[0].fromdate,document.forms[0].todate,holidayList,document.forms[0],alertMsgDates);
				if(document.getElementById('halfdayYes').status==true){
						document.getElementById("halfday").style.display="";
						document.getElementById("tag").style.display="";
						document.getElementById("nod").style.display="";
				}
				else{
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";
				document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("nod").innerHTML=": "+eval(nod);
				document.forms[0].noofdays.value=eval(nod);				
				}
				//document.getElementById("halfday").style.display="none";
				}
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){								
					if(eval(nod)>0.5 && (document.getElementById("firstdayhalfdayYes").status==true
							|| document.getElementById("lastdayhalfdayYes").status==true))
					{
						document.getElementById("nod").innerHTML=eval(nod);
						document.forms[0].noofdays.value=eval(nod);
					}
					else if(eval(nod)>1 && (document.getElementById("firstdayhalfdayNo").status==true
							|| document.getElementById("lastdayhalfdayNo").status==true) && document.getElementById('halfdayYes').status==true )
					{						
						nod=Differ(document.forms[0].fromdate,document.forms[0].todate,holidayList,document.forms[0],alertMsgDates);
						if(!isNaN(eval(nod))){
						document.getElementById("nod").innerHTML=eval(nod+0.5);
						document.forms[0].noofdays.value=eval(nod+0.5);
						}
					}
					else if (eval(nod)<=0.5)
					{
						document.getElementById("nod").innerHTML=eval(nod);
						document.forms[0].noofdays.value=eval(nod);
					}
				}
			}		
		else if(natureofleave[0]=='2')
			{
			document.getElementById("halfday").style.display="none";
			nod=Differ(document.forms[0].fromdate,document.forms[0].todate,holidayList,document.forms[0],alertMsgDates);
							document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> eval(0)){
				document.getElementById("tag").style.display="";
				document.getElementById("nod").innerHTML="";
				
				document.getElementById("nod").innerHTML=": "+ eval(nod);
				document.forms[0].noofdays.value=eval(nod);
				}
			
			}
			else{				
				var Date1=document.forms[0].fromdate.value.split("/");
				var Date2=document.forms[0].todate.value.split("/");
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
				Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
				nod=dateDifference(new Date(Date1),new Date(Date2),alertMsgDates);
				document.getElementById("halfday").style.display="none";
				document.getElementById("tag").style.display="";
				document.getElementById("nod").style.display="";
				document.getElementById("nod").innerHTML="";
				if(!isNaN(eval(nod)) && eval(nod)> 0){
				document.getElementById("nod").innerHTML=": " +eval(nod);
				document.forms[0].noofdays.value=eval(nod);				
				}

			}
	if(eval(nod) <=eval(0)){document.forms[0].Submit.disabled=false;return false;}
	else{return true;}
}
}
var leavetypeSelected="";

</script>



<%try{ 
%>
<hdiits:form name="frmleaveapply" validate="true" method="post" action="hdiits.htm?actionFlag=insertLeave" encType="multipart/form-data"> 

	<table class="tabtable" border="0" width="100%">

		<tr id="messages" style="display:none">
			<td colspan="5">
<div id="alertMsg"
				style="margin: 5px 0px; font-family: arial; color: #333333;border: solid 1px #6B2700; width: 100%; clear: left;">			
			<c:if test="${alreadyApplied==false}">
			
			<br/>
				<font color="red"><fmt:message key="HRMS.alreadyApplied" bundle="${alertLables}"/></font>
				<script>
document.getElementById("messages").style.display="";
</script>
			</c:if>
</div>
			</td>
		</tr>
</table>
<table width="100%" border="0">
		<tr>
			<td class="fieldLabel" colspan="5"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>
		
<table width="100%" border="0">
		<tr>
			<td colspan="5">
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.Balanceavailable" bundle="${leaveCaption}"  id="LeaveBalanceGrp">	
				<%@ include file="LeaveBalance.jsp"%>
			</hdiits:fieldGroup>	
	

<c:set var="combTable" value="display:none;"/>
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
</td>
</tr>
</table>
	
	<hdiits:fieldGroup bundle="${leaveCaption}"  expandable="true" titleCaptionId="HRMS.LeaveDetail" id="appliedBetweenGrp">	
	<table border="0" width="100%"> 
	<tr>
	<td width="*">
 <hdiits:caption captionid="HRMS.appliedbetween1" bundle="${leaveCaption}"/> :
	</td>
<td>
 <hdiits:select sort="false" name="appliedbetween" size="1" captionid="HRMS.appliedbetween1"  bundle="${leaveCaption}"
mandatory="true" tabindex="1" validation="sel.isrequired" onchange="getLeaveData(this,document.frmleaveapply);document.forms[0].Submit.disable='false';">
               <hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="hrleavemain" items="${app_penLst}">
				<hdiits:option value="${hrleavemain.corrid}">
				<fmt:formatDate value="${hrleavemain.appLeaveFrom}"  pattern="dd-MM-yyyy"/>&nbsp;
	<fmt:message key="HRMS.Dash" bundle="${leaveCaption}"/>&nbsp;<fmt:formatDate value="${hrleavemain.appLeaveTo}"  pattern="dd-MM-yyyy"/>
				
				</hdiits:option>
						</c:forEach>	
</hdiits:select>
<script>
if("${selectedValue}"!==""){
document.forms[0].appliedbetween.value="${selectedValue}";
}
</script>
<!-- include file="./ApprovePendingList.jsp-->
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
	</table>
	</hdiits:fieldGroup>
		<%@ include file="./previousLeaveDetail.jsp"%>
	
<!--  start    -->	

	<hdiits:fieldGroup bundle="${leaveCaption}" expandable="true" titleCaptionId="HRMS.LeaveEntryForm" id="LeaveEntryGrp">
			<table width="100%" border="0">
		<tr>
			<td><hdiits:caption captionid="HRMS.comb_leave"  bundle="${leaveCaption}"/></td>
			<td>
				<hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}" name="combinationleave"
				value="1" mandatory="false" onclick="showbutton(this,1);" default="${isCombination}"
				tabindex="1" />
				 <hdiits:radio	captionid="HRMS.No"   bundle="${leaveCaption}" name="combinationleave" value="0"
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
			<tr id="natureofleave" style="${normalLeaveTbl}">
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

			<td id="tag" style="${normalLeaveTbl}"><hdiits:caption captionid="HRMS.NoD1"  bundle="${leaveCaption}"/></td>
			<td id="nod" style="${normalLeaveTbl}">${nod}</td>

		</tr>
		<tr id="fromdate" style="${normalLeaveTbl}">
			<td width="20%"><hdiits:caption captionid="HRMS.FromDate" bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="yyyy-MM-dd" var="fromDate"/>
 <c:if test="${ not empty fromDate}">
 <c:set var="fromDate" value="${fromDate} 00:00:00" />
  </c:if>
			<td width="42%"><hdiits:dateTime name="fromdate" captionid="HRMS.fromdate"
				bundle="${leaveCaption}" mandatory="true"
				afterDateSelect="checkHalfDay();" tabindex="5" default="${fromDate}" onblur="checkHalfDay();" maxvalue="31/12/9999"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.ToDate"  bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="yyyy-MM-dd" var="toDate"/>
 <c:if test="${not empty toDate }">
 <c:set var="toDate" value="${toDate} 00:00:00" />
  </c:if>
		
			<td><hdiits:dateTime name="todate" captionid="HRMS.todate"
				bundle="${leaveCaption}" mandatory="true"
				afterDateSelect="checkHalfDay();" tabindex="6"
				onblur="checkHalfDay();" maxvalue="31/12/9999" default="${toDate}"></hdiits:dateTime></td>

		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.ContactAddress" bundle="${leaveCaption}" /></td>
			<td>
			<span>
			<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_contactaddress_cnt">2000</label>]</nobr>
			</font>
			</span>

			<hdiits:textarea mandatory="true" rows="2" cols="50"
				name="contactaddress" tabindex="7" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.contactaddress"
				bundle="${leaveCaption}" maxlength="2000" default="${appliedLeaveData.empAddress}" onkeypress="textAreaLimit(this,document.getElementById('sp_contactaddress_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_contactaddress_cnt'));" /></td>

			<c:set var="teleList"  value='${fn:split(appliedLeaveData.phoneno, "-")}'/>
			<c:set var="telecode" value=""/>
			<c:if test="${teleList[0] ne 'xxxx'}">
				<c:set var="telecode" value="${teleList[0]}"/>
			</c:if>
			<td><hdiits:caption captionid="HRMS.Telephone"  bundle="${leaveCaption}"/>
			</td>
			<td align="left"><hdiits:number mandatory="false" name="telecode"
				tabindex="8" id="c_strNames" captionid="HRMS.Tele_code" 
				bundle="${leaveCaption}" default="${telecode}"  maxlength="4"  size="2" /> - <hdiits:number
				mandatory="false" name="telephone" tabindex="9" id="c_strNames"  
				captionid="HRMS.telephone" bundle="${leaveCaption}" default="${teleList[1]}" maxlength="10" size="10"/>
				<br>
				
 					<table  border="0" align="left" width="100%">
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
			<td align="left"><hdiits:radio name="ordcir" value="1"  captionid="HRMS.Yes"  bundle="${leaveCaption}"
				mandatory="false" tabindex="10"  />&nbsp;&nbsp;&nbsp;
				 <hdiits:radio value="0"
				name="ordcir" mandatory="false" tabindex="11" default="0" captionid="HRMS.No"  bundle="${leaveCaption}" />&nbsp;&nbsp;</td>
			<td></td>
		</tr>
			<c:set var="halfdayTR" value="display:none"/>
			<c:if test="${not empty halfday and halfday ne '0'}" >
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
			
			<c:if test="${not empty startday_endday}" >
			<c:set var="startday_enddayTR" value="display:visibility"/>
			<c:set var="splittedStartday" value='${fn:split(startday_endday,"_")}'/>	
			<c:set var="halfdayOnfirstday" value="${splittedStartday[0]}"/>
			<c:set var="halfdayOnLastday" value="${splittedStartday[1]}"/>
			<c:if test="${startday_endday ne '0_0'}">
			<c:set var="before2TR" value="display:none"/>
			</c:if>

			<c:choose>
			<c:when test="${halfdayOnfirstday eq 1}" >				
			<c:set var="after2PMOnFirstdayTD" value="display:visibility"/>
			</c:when>
			<c:when test="${halfdayOnfirstday eq 0}" >				
			<c:set var="after2PMOnFirstdayTD" value="display:none"/>
			</c:when>
		
			</c:choose>

			<c:if test="${halfdayOnLastday eq 1}" >
				<c:set var="before2PMOnLastdayTD" value="display:visibility"/>				
	
			</c:if>
			
			<c:if test="${halfdayOnLastday eq 0}" >			
				<c:set var="before2PMOnLastdayTD" value="display:none"/>

			</c:if>

			</c:if>
		
		<tr id="halfday" style="${halfdayTR}">
			<td><hdiits:caption captionid="HRMS.halfday"  bundle="${leaveCaption}"/></td>
			<td><hdiits:radio name="halfday" id="halfdayYes" value="1" 	captionid="HRMS.Yes"  bundle="${leaveCaption}"
				mandatory="false" tabindex="12" default="${halfday}" onclick="checkHalfDay();" />
				 <hdiits:radio name="halfday" id="halfdayNo" captionid="HRMS.No"  bundle="${leaveCaption}"
				value="0" default="${halfday}" mandatory="false" tabindex="13" onclick="checkHalfDay();"
				/></td>
			<td id="before2_lbl" style="${before2TR}"><hdiits:caption captionid="HRMS.before2"  bundle="${leaveCaption}"/></td>
			<td id="before2_radio" style="${before2TR}">
			<hdiits:radio name="before2" id="before2Yes" default="${before2}" captionid="HRMS.Yes" 
			bundle="${leaveCaption}" value="1" mandatory="false" tabindex="14" onclick="checkHalfDay();" />
			 <hdiits:radio id="before2No" default="${before2}" captionid="HRMS.No" bundle="${leaveCaption}" name="before2" value="0" mandatory="false"
				 tabindex="15" onclick="checkHalfDay();" /></td>

		</tr>
			
		<tr id="First_Day" style="${startday_enddayTR}">
			<td id="firstday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyy" />
			</td>
			<td>
			<hdiits:radio captionid="HRMS.Yes" bundle="${leaveCaption}" name="firstday"	id="firstdayhalfdayYes" value="1"  default="${halfdayOnfirstday}" mandatory="false" tabindex="16"
				onclick="checkHalfDay();" />
				
			<hdiits:radio captionid="HRMS.No" bundle="${leaveCaption}" name="firstday" 
				id="firstdayhalfdayNo" value="0" mandatory="false" default="${halfdayOnfirstday}"
				tabindex="17" onclick="checkHalfDay();" /></td>
			<td id="before2_radio_firstday" ><hdiits:caption captionid="HRMS.after2pm"  bundle="${leaveCaption}"/></td>
		</tr>


		<tr id="Last_Day" style="${before2PMOnLastdayTD}">
			<td id="lastday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="dd/MM/yyy" /> 			</td>
			<td><hdiits:radio  name="lastday" captionid="HRMS.Yes"  bundle="${leaveCaption}"
				id="lastdayhalfdayYes" value="1" mandatory="false" tabindex="18" default="${halfdayOnLastday}"
				onclick="checkHalfDay();" />
			<hdiits:radio  name="lastday"  captionid="HRMS.No"  bundle="${leaveCaption}"
				id="lastdayhalfdayNo" value="0" mandatory="false" default="${halfdayOnLastday}"
				tabindex="19" onclick="checkHalfDay();" /></td>
			<td id="before2_radio_lastday"><hdiits:caption captionid="HRMS.before2pm"  bundle="${leaveCaption}"/></td>
		</tr>
		<tr>
			<c:set var="prefixSelected"	value="0"/>
			<c:set var="prefixFromDatesTR"	value="display:none"/>
			<c:if test="${not empty appliedLeaveData.prefixFromdate}">
			<c:set var="prefixSelected"	value="1"/>
			<c:set var="prefixFromDatesTR"	value="display:visible"/>
			</c:if>

			<td><hdiits:caption captionid="HRMS.Prefix"  bundle="${leaveCaption}"/></td>
			<td align="left"><hdiits:radio captionid="HRMS.Yes"  bundle="${leaveCaption}" name="prefix"
				value="1" onclick="return showPrefixDate(this);" tabindex="20" default="${prefixSelected}"
				condition="return checkPrefixDate(document.frmleaveapply);" />
				
				<hdiits:radio captionid="HRMS.No"  bundle="${leaveCaption}"
				name="prefix" value="0" tabindex="21" default="${prefixSelected}"
				onclick="return showPrefixDate(this);" />&nbsp;&nbsp;</td>
		</tr>
		
		
		
		<tr name="yes1" id="yes1" width="100%" border="0" style="${prefixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.PrefixFromDate"  bundle="${leaveCaption}"/>
			(DD/MM/YYYY)</td>
		
		<fmt:formatDate value="${appliedLeaveData.prefixFromdate}" pattern="yyyy-MM-dd" var="prefixFromdate"/>
 <c:if test="${ not empty prefixFromdate}">
 <c:set var="prefixFromdate" value="${prefixFromdate} 00:00:00" />
  </c:if>
		
			<td><hdiits:dateTime name="prefixfromdate" mandatory="true"
				captionid="HRMS.prefixfromdate" tabindex="22"
				bundle="${leaveCaption}" maxvalue="31/12/9999" default="${prefixFromdate}"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.PrefixToDate"  bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
			<td>
			
			<fmt:formatDate value="${appliedLeaveData.prefixTodate}" pattern="yyyy-MM-dd" var="prefixTodate"/>
 <c:if test="${ not empty prefixTodate}">
 <c:set var="prefixTodate" value="${prefixTodate} 00:00:00" />
  </c:if>
		
			
			<hdiits:dateTime name="prefixtodate" mandatory="true"
				captionid="HRMS.prefixtodate" tabindex="23" bundle="${leaveCaption}" default="${prefixTodate}"
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
			<td align="left"><hdiits:radio  name="suffix" default="${suffixSelected}"
				value="1" tabindex="24"  captionid="HRMS.Yes"  bundle="${leaveCaption}" onclick="return showSuffixDate(this);" />
				<hdiits:radio captionid="HRMS.No"  bundle="${leaveCaption}" name="suffix" value="0" tabindex="25"
				onclick="return showSuffixDate(this)" default="${suffixSelected}" />
				</td>
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
				bundle="${leaveCaption}" default="${suffixFromdate}" maxvalue="31/12/9999"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.SuffixToDate"  bundle="${leaveCaption}"/> (DD/MM/YYYY)</td>
			<td><hdiits:dateTime name="suffixtodate" mandatory="true"
				captionid="HRMS.suffixtodate" tabindex="27" bundle="${leaveCaption}" default="${suffixTodate}"
				maxvalue="31/12/9999"></hdiits:dateTime></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.LeaveReason"  bundle="${leaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leavereason_cnt">2000</label>]</nobr>
	</font>
			</span>
			<hdiits:textarea mandatory="true" rows="5"
				cols="40" name="leavereason" tabindex="28" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.leavereason"
				bundle="${leaveCaption}" maxlength="2000" default="${appliedLeaveData.reason}" onkeypress="textAreaLimit(this,document.getElementById('sp_leavereason_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_leavereason_cnt'));"/></td>
				
			<td><hdiits:caption captionid="HRMS.Remarks"  bundle="${leaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leaveremarks_cnt">2000</label>]</nobr>
			</font>
			</span>
			<hdiits:textarea rows="5" cols="50" name="remarks"
				tabindex="29" id="c_strNames"
				captionid="HRMS.remarks" bundle="${leaveCaption}" mandatory="false"
				maxlength="2000" default="${appliedLeaveData.remarks}" onkeypress="textAreaLimit(this,document.getElementById('sp_leaveremarks_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_leaveremarks_cnt'));" /></td>			
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
	

	<table id="lvdtl" width="100%"  style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border=1>

		<tbody>
			<c:set var="showCheckBoxInCombinationLeave" value="true"/>
			<%@ include file="CmnCombinationLeave.jsp"%>
		</tbody>
		<tbody>
			<tr style="display:none">
				<td colspan="5" align="right" style="display:none"><font
					color="Red"><hdiits:caption captionid="HRMS.TotalDays"  bundle="${leaveCaption}"/></font>
				</td>
				<td id="totalDays" style="display:none">0</td>
			</tr>
			<tr>
				<td align="center" colspan="6"><hdiits:button type="button"
					name="edit" captionid="HRMS.Edit" bundle="${leaveCaption}"
					onclick="deleteRow(1,'',alertMsg);" tabindex="30" /> <hdiits:button
					type="button" name="Delete" captionid="HRMS.Delete"
					bundle="${leaveCaption}"  onclick="deleteLeave();"
					tabindex="31" /></td>
			</tr>

		</tbody>
		 </table>
<!-- End  -->
</table>
</hdiits:fieldGroup>

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
    <hdiits:hidden name="counter" caption="counter"/>
    <hdiits:hidden name="leavedtls" caption="a"/>
	</td>
 
			<td colspan="2">
			&nbsp;
			</td>
</tr></tbody>
</table>
			
			<hdiits:fieldGroup bundle="${leaveCaption}" expandable="false" style="display:none" collapseOnLoad="false" titleCaptionId="HRMS.Agreement" id="agreement">
				<table border="0" width="100%">
				<tr><td><hdiits:checkbox id="chkAgree1" name="chkAgree1" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement1" bundle="${leaveCaption}"></fmt:message></td></tr>
				<tr><td><hdiits:checkbox id="chkAgree2" name="chkAgree2" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement2" bundle="${leaveCaption}"></fmt:message></td></tr>
				</table>	
			</hdiits:fieldGroup>
			
<table border="0" width="100%">
<tr>
	<td colspan="4">
		<table border='0' width="100%">
			<tr>
				<td>
					<!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
					<!-- For attachment : Start-->	
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="LeaveAttachment" />
						<jsp:param name="formName" value="frmleaveapply" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="multiple" value="N" />                
						</jsp:include>
					<!-- For attachment : End-->
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td align="center">
	<hdiits:button type="button" name="Submit" value="Submit" captionid="HRMS.Submit" tabindex="35"  bundle="${leaveCaption}"  readonly="false" onclick="document.frmleaveapply.tempsave.value='0';submitData();"/>
	<hdiits:button type="button" name="close" value="Close"  captionid="HRMS.Close" tabindex="36" bundle="${leaveCaption}" onclick="goToMainPage(document.frmleaveapply);" />
	</td></tr>
</tbody>
</table>
	<c:if test="${empty LeaveBal}">
	<script>
	//document.forms[0].Submit.disabled=true;
	</script>		
		
		</c:if>
<table id="applLeaveDtl" border="0" style="display:none">
<tr>
	<td><hdiits:caption captionid="HRMS.NatureOfLeave" bundle="${leaveCaption}"/></td>
	<td>


<hdiits:select sort="false" name="nol" size="1" caption="drop_down"  tabindex="37"   onchange="checkHalfDay(this);">
               <hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
				<hdiits:option value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}">
				${leavetypes.leaveTypeName}
				</hdiits:option>
				</c:forEach>
	        </hdiits:select>
	</td>
</tr>
</table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");
</script>
</c:forEach>
<c:if test="${fn:trim(ruleKey) ne ''}"> 
<script>
alertMessages('${ruleKey}');
</script>
</c:if>
<script type="text/javascript">
if(leavetypeSelected=='3_3_5')
{
	document.getElementById("agreement").style.display="";
}
</script>
<c:remove var="Modified_HrEssLeaveMainTxn" scope="session"/>

<%
}catch(Exception e){
	e.printStackTrace();
}

%>
