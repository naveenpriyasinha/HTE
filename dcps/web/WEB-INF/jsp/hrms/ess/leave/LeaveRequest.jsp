<%
try {
%>
<%@ include file="./alertMessage.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="LeaveTList" value="${resultValue.leaveTList}">
</c:set>
<c:set var="LeaveBal" value="${resultValue.leaveBal}">
</c:set>
<c:set var="holidayList" value="${resultValue.holidayList}">
</c:set>
<c:set var="latestAppLeave" value="${resultValue.latestApprLeave}">
</c:set>
<c:set var="appliedLeaveData" value="${resultValue.appliedLeaveData}">
</c:set>
<c:set var="ajaxKey" value="${resultValue.leaveDet}">
</c:set>
<c:set var="drafReq" value="${resultValue.draftReq}">
</c:set>
<c:set var="gender" value="${resultValue.gender}">
</c:set>
<c:set var="ruleKey" value="${resultValue.Key}">
</c:set>
<c:set var="alreadyApplied" value="${resultValue.appliedBefore}">
</c:set>
<c:set var="leaveTypesBalMaintain" value="${resultValue.leaveTypesBalMaintain}">
</c:set>
<c:set var="onBeHalfUserId" value="${resultValue.onBeHalfUserId}" />
<c:set var="attachments" value="${resultValue.LeaveAttachmentVO}" />
<c:set var="attachmentXML" value="${resultValue.attachmentXML}"/>
<fmt:setBundle basename="resources.ess.leave.AlertMessages"	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"	var="LeaveCaption" scope="request" />
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/leaveRequest.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>	
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>
<script language="javascript">
var leavetypeSelected="";
var leavetype_bal=new Array();
var counter =1;
var leaveCounter=1;
var leaveArr=new Array();
var toDateArr= new Array();
var fromDateArr= new Array();
var combFromArr = new Array();
var combToArr=new Array();
var leaveDataArrParent= new Array();
document.onclick=checkFirstHalfDay;
document.onkeydown=checkFirstHalfDay;
var editedObject;
var toDate;
var fromDate;
var nod=0; 
var duplicateLeave=false;

var holidayList='${holidayList}';
var leaveTypesBalMaintain='${leaveTypesBalMaintain}';
</script>
<hdiits:form name="frmleaveapply" validate="true" method="post"
	action="hrms.htm&actionFlag=insertLeave" encType="multipart/form-data">
	<!--  <form name="SPCP" method="POST"> -->
	<div id="leave" name="leave">
	<hdiits:hidden name="onBeHalfUserId" id="onBeHalfUserId" default="${onBeHalfUserId}" />
	
	<table class="tabtable" border=0>
		<tr id="messages" style="display:none">
			<td colspan=5>
			<div id="alertMsg"
				style="margin: 5px 0px; font-family: arial; color: #333333;border: solid 1px #6B2700; width: 100%; clear: left;">
			<br />
			<c:if test="${alreadyApplied==false}">
				<font color="red"><fmt:message key="HRMS.alreadyApplied" bundle="${alertLables}"/></font>
				<script>
					document.getElementById("messages").style.display="";
				</script>
			</c:if></div>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
		<tr>
			<td colspan=5>
<hdiits:fieldGroup bundle="${LeaveCaption}"  expandable="true" titleCaptionId="HRMS.Balanceavailable" id="LeaveBalanceGrp">
			<%@ include file="LeaveBalance.jsp"%>
</hdiits:fieldGroup>
			</td>
		</tr>
		<tr>
			<td colspan="5">
	<hdiits:fieldGroup bundle="${LeaveCaption}"  expandable="true" titleCaptionId="HRMS.LeaveEntryForm" id="LeaveEntryGrp">
			<table width="100%">
<c:set var="combTable" value="display:none;"/>
<c:set var="isCombination" value="0"/>
<c:if test="${appliedLeaveData.combFlag==1}">
<c:set var="combTable" value="display:visiblity;"/>
<c:set var="isCombination" value="${appliedLeaveData.combFlag}"/>
<c:set var="normalLeaveTbl" value="display:none;"/>
</c:if>
<c:if test="${appliedLeaveData.combFlag==0}">
<c:set var="normalLeaveTbl" value="display:visiblity;"/>
<c:set var="combTable" value="display:none;"/>
</c:if>
		<tr>
			<td><hdiits:caption captionid="HRMS.comb_leave"  bundle="${LeaveCaption}"/></td>
			<td>
				<hdiits:radio captionid="HRMS.Yes" bundle="${LeaveCaption}"  name="combinationleave" 
				value="1" mandatory="false" onclick="showbutton(this,1);" default="${isCombination}"
				tabindex="1" />
				 <hdiits:radio	captionid="HRMS.No"  bundle="${LeaveCaption}" name="combinationleave" value="0"
				mandatory="false" default="${isCombination}" onclick="showbutton(this,1);"
				tabindex="2" />
			<span id="leavedetail" style="${combTable}">
				<hdiits:button 
				type="button" name="leaveDetail" captionid="HRMS.EnterLeaveDetail1"
				bundle="${LeaveCaption}" onclick="return applyLeaveDetail();"
				tabindex="3" />
			</span>	
			</td>
		</tr>
			<c:set var="leavetypeSelected"  value=""/>
			<c:set var="halfday" value="0"/>
			<c:set var="before2" value="0"/>
			<c:set var="startday_endday" value=""/>
<c:set var="nod" value=""/>
	<c:forEach var="leaveOtherDtl" items="${appliedLeaveData.hrEssLeaveOtherDtls}">
			<c:set var="halfday" value="${leaveOtherDtl.halfday}"/>
			<c:set var="before2" value="${leaveOtherDtl.before2}"/>
			<c:set var="startday_endday" value="${leaveOtherDtl.startdayEndday}"/>
			<c:set var="leavetypeSelected"  value="${leaveOtherDtl.hrEssLeaveMst.leaveTypeid}_${leaveOtherDtl.hrEssLeaveMst.srno}_${leaveOtherDtl.hrEssLeaveMst.leavecode}"/>			
			<c:set var="nod" value="${leaveOtherDtl.noofdays}"/>
		</c:forEach>	
		<tr id="natureofleave" style="${normalLeaveTbl}">
			<td><hdiits:caption captionid="HRMS.NatureOfLeave"  bundle="${LeaveCaption}"/></td>
			<td><c:set var="counter" value="0" /> 
			<hdiits:select
				sort="false" name="natureofleave" captionid="HRMS.NatureOfLeave"
				size="1" mandatory="true" onchange="showOrd(this);" tabindex="4"
				bundle="${LeaveCaption}">
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
			<td id="tag" style="${normalLeaveTbl}"><hdiits:caption captionid="HRMS.NoD1"  bundle="${LeaveCaption}"/></td>
			<td id="nod" style="${normalLeaveTbl}">${nod}</td>
		</tr>
		<tr id="fromdate" style="${normalLeaveTbl}">
			<td width="20%"><hdiits:caption captionid="HRMS.FromDate" bundle="${LeaveCaption}"/> (DD/MM/YYYY)</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="yyyy-MM-dd" var="fromDate"/>
 <c:if test="${ not empty fromDate}">
 <c:set var="fromDate" value="${fromDate} 00:00:00" />
  </c:if>
			<td width="42%"><hdiits:dateTime name="fromdate" captionid="HRMS.fromdate"
				bundle="${LeaveCaption}" mandatory="true"
				afterDateSelect="checkHalfDay();" tabindex="5" default="${fromDate}" onblur="checkHalfDay();" maxvalue="31/12/9999"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.ToDate"  bundle="${LeaveCaption}"/> (DD/MM/YYYY)</td>
<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="yyyy-MM-dd" var="toDate"/>
 <c:if test="${not empty toDate }">
 <c:set var="toDate" value="${toDate} 00:00:00" />
  </c:if>
			<td><hdiits:dateTime name="todate" captionid="HRMS.todate"
				bundle="${LeaveCaption}" mandatory="true"
				afterDateSelect="checkHalfDay();" tabindex="6"
				onblur="checkHalfDay();" maxvalue="31/12/9999" default="${toDate}"></hdiits:dateTime></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.ContactAddress" bundle="${LeaveCaption}" /></td>
			<td>
			<span>
			<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_contactaddress_cnt">2000</label>]</nobr>
			</font>
			</span>
			<hdiits:textarea mandatory="true" rows="2" cols="50"
				name="contactaddress" tabindex="7" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.contactaddress"
				bundle="${LeaveCaption}" maxlength="2000" default="${appliedLeaveData.empAddress}" onkeypress="textAreaLimit(this,document.getElementById('sp_contactaddress_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_contactaddress_cnt'));" /></td>
			<c:set var="teleList"  value='${fn:split(appliedLeaveData.phoneno, "-")}'/>
			<c:set var="telecode" value=""/>
			<c:if test="${teleList[0] ne 'xxxx'}">
				<c:set var="telecode" value="${teleList[0]}"/>
			</c:if>
			<td><hdiits:caption captionid="HRMS.Telephone"  bundle="${LeaveCaption}"/>
			</td>
			<td align="left"><hdiits:number mandatory="false" name="telecode"
				tabindex="8" id="c_strNames" captionid="HRMS.Tele_code" 
				bundle="${LeaveCaption}" default="${telecode}"  maxlength="4"  size="2" /> - <hdiits:number
				mandatory="false" name="telephone" tabindex="9" id="c_strNames"  
				captionid="HRMS.telephone" bundle="${LeaveCaption}" default="${teleList[1]}" maxlength="10" size="10"/>
				<br>
 					<table  border=0 align="left">
 				<tr>	
				<td align="left">
				<nobr>
				 <hdiits:caption captionid="HRMS.STD"  bundle="${LeaveCaption}"/>
					-
				 <hdiits:caption captionid="HRMS.phonemobile"  bundle="${LeaveCaption}"/>
				</nobr>
				</td>
 				</tr>				
 					</table>			
				</td>
		</tr>
		<tr id="ordinarycir" style="display:none">
			<td><hdiits:caption captionid="HRMS.ordcir"  bundle="${LeaveCaption}"/></td>
			<td align="left">
			<hdiits:radio name="ordcir" value="1" captionid="HRMS.Yes"  bundle="${LeaveCaption}"
				mandatory="false" tabindex="10" />&nbsp;&nbsp;&nbsp;
			 <hdiits:radio value="0" captionid="HRMS.No"  bundle="${LeaveCaption}"
				name="ordcir" mandatory="false" tabindex="11" default="0" />&nbsp;&nbsp;</td>
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
			
		<tr id="halfday" style="${halfdayTR}">
			<td><hdiits:caption captionid="HRMS.halfday"  bundle="${LeaveCaption}"/></td>
			<td>
			<hdiits:radio name="halfday" id="halfdayYes" value="1" captionid="HRMS.Yes"
			  bundle="${LeaveCaption}"	mandatory="false" tabindex="12" default="${halfday}" onclick="checkHalfDay();" />
		  <hdiits:radio name="halfday" id="halfdayNo"
				value="0"  captionid="HRMS.No"  bundle="${LeaveCaption}" default="${halfday}" mandatory="false" 
				tabindex="13" onclick="checkHalfDay();"	/></td>
			<td id="before2_lbl" style="${before2TR}"><hdiits:caption captionid="HRMS.before2"  bundle="${LeaveCaption}"/></td>
			<td id="before2_radio" style="${before2TR}">
			<hdiits:radio name="before2" id="before2Yes" default="${before2}" captionid="HRMS.Yes" bundle="${LeaveCaption}" value="1" mandatory="false"
				tabindex="14" onclick="checkHalfDay();" />  
			 <hdiits:radio id="before2No" default="${before2}" captionid="HRMS.No"  
			 bundle="${LeaveCaption}" name="before2" value="0" mandatory="false"
				 tabindex="15" onclick="checkHalfDay();" />
			 </td>
		</tr>
		<tr id="First_Day" style="${startday_enddayTR}">
			<td id="firstday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}" pattern="dd/MM/yyy" />
			</td>
			<td>
			<hdiits:radio  name="firstday"	id="firstdayhalfdayYes"
			 captionid="HRMS.Yes"  bundle="${LeaveCaption}"
			 value="1"  default="${halfdayOnfirstday}" mandatory="false" tabindex="16"
				onclick="checkHalfDay();" />
			<hdiits:radio  name="firstday" 
				id="firstdayhalfdayNo" value="0" mandatory="false" default="${halfdayOnfirstday}"
				tabindex="17" onclick="checkHalfDay();" captionid="HRMS.No"  bundle="${LeaveCaption}" /></td>
			<td id="before2_radio_firstday" ><hdiits:caption captionid="HRMS.after2pm"  bundle="${LeaveCaption}"/></td>
		</tr>
		<tr id="Last_Day" style="${before2PMOnLastdayTD}">
			<td id="lastday_halfday">
			<fmt:message key="HRMS.halfdayon"/>&nbsp;<fmt:formatDate value="${appliedLeaveData.appLeaveTo}" pattern="dd/MM/yyy" /> 			</td>
			<td><hdiits:radio name="lastday"
				id="lastdayhalfdayYes" value="1" mandatory="false" tabindex="18" default="${halfdayOnLastday}"
				onclick="checkHalfDay();" captionid="HRMS.Yes"  bundle="${LeaveCaption}" />
			<hdiits:radio  name="lastday"
				id="lastdayhalfdayNo" value="0" mandatory="false" default="${halfdayOnLastday}"
				tabindex="19" onclick="checkHalfDay();" captionid="HRMS.No"  bundle="${LeaveCaption}" /></td>
			<td id="before2_radio_lastday"><hdiits:caption captionid="HRMS.before2pm"  bundle="${LeaveCaption}"/></td>
		</tr>
		<tr>
			<c:set var="prefixSelected"	value="0"/>
			<c:set var="prefixFromDatesTR"	value="display:none"/>
			<c:if test="${not empty appliedLeaveData.prefixFromdate}">
			<c:set var="prefixSelected"	value="1"/>
			<c:set var="prefixFromDatesTR"	value="display:visible"/>
			</c:if>
			<td><hdiits:caption captionid="HRMS.Prefix"  bundle="${LeaveCaption}"/></td>
			<td align="left">
			<hdiits:radio name="prefix"	value="1" onclick="return showPrefixDate(this);"
			 tabindex="20" default="${prefixSelected}"	condition="return checkPrefixDate(document.frmleaveapply);" 
			  captionid="HRMS.Yes"  bundle="${LeaveCaption}" />
				<hdiits:radio 
				name="prefix" value="0" tabindex="21" default="${prefixSelected}"
				onclick="return showPrefixDate(this);" captionid="HRMS.No" bundle="${LeaveCaption}"/>
				&nbsp;&nbsp;</td>
		</tr>
		<tr name="yes1" id="yes1" width="100%" border="0" style="${prefixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.PrefixFromDate"  bundle="${LeaveCaption}"/>
			(DD/MM/YYYY)</td>
		<fmt:formatDate value="${appliedLeaveData.prefixFromdate}" pattern="yyyy-MM-dd" var="prefixFromdate"/>
 <c:if test="${ not empty prefixFromdate}">
 <c:set var="prefixFromdate" value="${prefixFromdate} 00:00:00" />
  </c:if>
			<td><hdiits:dateTime name="prefixfromdate" mandatory="true"
				captionid="HRMS.prefixfromdate" tabindex="22"
				bundle="${LeaveCaption}" maxvalue="31/12/9999" default="${prefixFromdate}"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.PrefixToDate"  bundle="${LeaveCaption}"/> (DD/MM/YYYY)</td>
			<td>
			<fmt:formatDate value="${appliedLeaveData.prefixTodate}" pattern="yyyy-MM-dd" var="prefixTodate"/>
 <c:if test="${ not empty prefixTodate}">
 <c:set var="prefixTodate" value="${prefixTodate} 00:00:00" />
  </c:if>
			<hdiits:dateTime name="prefixtodate" mandatory="true"
				captionid="HRMS.prefixtodate" tabindex="23" bundle="${LeaveCaption}" default="${prefixTodate}"
				maxvalue="31/12/9999"></hdiits:dateTime></td>
		</tr>
		<c:set var="suffixSelected"	value="0"/>
			<c:set var="suffixFromDatesTR"	value="display:none"/>
			<c:if test="${not empty appliedLeaveData.suffixFromdate}">
			<c:set var="suffixSelected"	value="1"/>
			<c:set var="suffixFromDatesTR"	value="display:visible"/>
			</c:if>
		<tr>
			<td><hdiits:caption captionid="HRMS.Suffix"  bundle="${LeaveCaption}"/></td>
			<td align="left">
			<hdiits:radio name="suffix" default="${suffixSelected}"
				value="1" tabindex="24" onclick="return showSuffixDate(this);" 
				captionid="HRMS.Yes"  bundle="${LeaveCaption}"/> 
				<hdiits:radio 
				name="suffix" value="0" tabindex="25"
				onclick="return showSuffixDate(this)" default="${suffixSelected}" 
				captionid="HRMS.No"  bundle="${LeaveCaption}" /> </td>
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
			<td><hdiits:caption captionid="HRMS.SuffixFromDate"  bundle="${LeaveCaption}"/>
			(DD/MM/YYYY)</td>
			<td><hdiits:dateTime name="suffixfromdate" mandatory="true"
				captionid="HRMS.suffixfromdate" tabindex="26"
				bundle="${LeaveCaption}" default="${suffixFromdate}" maxvalue="31/12/9999"></hdiits:dateTime></td>
			<td><hdiits:caption captionid="HRMS.SuffixToDate"  bundle="${LeaveCaption}"/> (DD/MM/YYYY)</td>
			<td><hdiits:dateTime name="suffixtodate" mandatory="true"
				captionid="HRMS.suffixtodate" tabindex="27" bundle="${LeaveCaption}" default="${suffixTodate}"
				maxvalue="31/12/9999"></hdiits:dateTime></td>
		</tr>
		<tr>			
			<td><hdiits:caption captionid="HRMS.LeaveReason"  bundle="${LeaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leavereason_cnt">2000</label>]</nobr>
	</font>
			</span>
			<hdiits:textarea mandatory="true" rows="5"
				cols="40" name="leavereason" tabindex="28" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.leavereason"
				bundle="${LeaveCaption}" maxlength="2000" default="${appliedLeaveData.reason}" onkeypress="textAreaLimit(this,document.getElementById('sp_leavereason_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_leavereason_cnt'));"/></td>
				
			<td><hdiits:caption captionid="HRMS.Remarks"  bundle="${LeaveCaption}"/></td>
			<td>
			<span>
<font size="2"><fmt:message key="HRMS.CHARACTERLIMIT" bundle="${alertLables}"/>&nbsp;[<fmt:message key="HRMS.MAXIMUM" bundle="${alertLables}"/> : 2000 &nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED" bundle="${alertLables}"/>&nbsp;: <nobr><label id="sp_leaveremarks_cnt">2000</label>]</nobr>
			</font>
			</span>
			<hdiits:textarea rows="5" cols="50" name="remarks"
				tabindex="29" id="c_strNames" 
				captionid="HRMS.remarks" bundle="${LeaveCaption}" mandatory="false"
				maxlength="2000" default="${appliedLeaveData.remarks}" onkeypress="textAreaLimit(this,document.getElementById('sp_leaveremarks_cnt'));" onblur="textAreaLimit(this,document.getElementById('sp_leaveremarks_cnt'));" /></td>	
		</tr>
<tr>
<td colspan="5">
	<table id="lvdtl"  width="100%"  style="border-collapse: collapse;${combTable}" borderColor="BLACK"  border=1>
		<tbody>
			<c:set var="showCheckBoxInCombinationLeave" value="true"/>
			<%@ include file="CmnCombinationLeave.jsp"%>		
		</tbody>
		<tbody>
			<tr style="display:none">
				<td colspan="5" align="right" style="display:none"><font
					color="Red"><hdiits:caption captionid="HRMS.TotalDays"  bundle="${LeaveCaption}"/></font>
				</td>
				<td id="totalDays" style="display:none">0</td>
			</tr>
			<tr>
				<td align="center" colspan="6"><hdiits:button type="button"
					name="edit" captionid="HRMS.Edit" bundle="${LeaveCaption}"
					onclick="deleteRow(1,'',alertMsg);" tabindex="30" /> <hdiits:button
					type="button" name="Delete" captionid="HRMS.Delete"
					bundle="${LeaveCaption}"  onclick="deleteLeave();"
					tabindex="31" /></td>
			</tr>
		</tbody>
	</table>
	</td>
	</tr>
	</table>			
	</hdiits:fieldGroup>	
	</td>
	</tr>
	</table>	
	<table border="0" width="100%">
		<tbody>
			<tr style="display:none">
				<td>For officers of level SP informed the concerned DIG/IG <hdiits:radio
					captionid="HRMS.Yes"   bundle="${LeaveCaption}" name="informsuperior" value="1" default="1"
					mandatory="false" tabindex="32" />&nbsp;&nbsp;<fmt:message
					key="HRMS.Yes" /> <hdiits:radio captionid="HRMS.Yes" bundle="${LeaveCaption}" name="informsuperior"
					value="0" mandatory="false" tabindex="33" />&nbsp;&nbsp;<fmt:message
					key="HRMS.No" /> <hdiits:hidden name="noofdays" caption="nod"
					default="0"></hdiits:hidden> <hdiits:hidden name="tempsave"
					caption="tempsave" default="0"></hdiits:hidden> <hdiits:text
					name="halfday_comb" caption="halfday" /> <hdiits:text
					name="before2_comb" caption="before2" maxlength="1" /> <hdiits:text
					name="firstDay_comb" caption="firstDay" maxlength="" /> <hdiits:text
					name="ordi_comb" caption="ordinary_comb" maxlength="" /> <hdiits:hidden
					name="parentid" caption="tempsave" default="0"></hdiits:hidden> <hdiits:hidden
					name="status" caption="status" default="0"></hdiits:hidden> <hdiits:hidden
					name="leaveid" caption="leaveid"></hdiits:hidden> <hdiits:hidden
					name="srno" caption="srno"></hdiits:hidden>
					<hdiits:hidden name="counter" caption="counter" default="0" />
					</td>

				<hdiits:hidden name="leavedtls" caption="a" />
				<td colspan="2">&nbsp;</td>
			</tr>
			</tbody>
			</table>
			
			<hdiits:fieldGroup bundle="${LeaveCaption}" expandable="false" style="display:none" collapseOnLoad="false" titleCaptionId="HRMS.Agreement" id="agreement">
				<table border="0" width="100%">
				<tr><td><hdiits:checkbox id="chkAgree1" name="chkAgree1" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement1" bundle="${LeaveCaption}"></fmt:message></td></tr>
				<tr><td><hdiits:checkbox id="chkAgree2" name="chkAgree2" value="1" default="0" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement2" bundle="${LeaveCaption}"></fmt:message></td></tr>
				</table>	
			</hdiits:fieldGroup>
			
			<table border="0" width="100%">
			<tbody>
			<tr>
				<td colspan="4">				
				<table border='0' width="100%">
					<tr>
						<td><!-- (Example-Scan and Attach Medical Certificate In Case Any) -->
						<!-- For attachment : Start--> <jsp:include
							page="/WEB-INF/jsp/common/attachmentPage.jsp">
							<jsp:param name="attachmentName" value="LeaveAttachment" />
							<jsp:param name="formName" value="frmleaveapply" />
							<jsp:param name="attachmentType" value="Document" />
							<jsp:param name="multiple" value="N" />
							<jsp:param name="removeAttachmentFromDB" value="Y" />
						</jsp:include> <!-- For attachment : End--></td>						
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td align="center"><hdiits:button type="button" tabindex="34"
					name="Submit" value="Submit" captionid="HRMS.Submit"
					bundle="${LeaveCaption}"
					onclick="document.frmleaveapply.tempsave.value='0';submitData();" />
				<hdiits:button type="button" tabindex="35" name="Close"
					value="Close" captionid="HRMS.Close" bundle="${LeaveCaption}"
					onclick="goToMainPage(document.frmleaveapply);" /> <!--<hdiits:button type="button" name="save" value="Save" onclick="document.frmleaveapply.tempsave.value='0';submitData();"/>
	<hdiits:submitbutton  name="DrafSave" value="Save As Draft" onclick="document.frmleaveapply.tempsave.value='1';saveAsDraft();"/>
	<hdiits:submitbutton  name="save-send" value="Save-Send" />--></td>
			</tr>
		</tbody>
	</table>	
	</div>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");
</script>
<c:if test="${fn:trim(ruleKey) ne ''}">
	<script>
	alertMessages('${ruleKey}');	
	</script>
</c:if>
<script>
if(leavetypeSelected=='3_3_5')
{
	document.getElementById("agreement").style.display="";
}
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>