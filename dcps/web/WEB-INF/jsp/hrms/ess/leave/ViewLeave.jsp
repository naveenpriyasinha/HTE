<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="./alertMessage.jsp"%>


<c:set var="resultObj" value="${result}"/>

<c:set var="resultValue" value="${resultObj.resultValue}"/>

<c:set var="LeaveTList" value="${resultValue.leaveTList}"/>

<c:set var="app_penLst" value="${resultValue.approve_pendingList}"/>

<c:set var="latestAppLeave" value="${resultValue.latestApprLeave}"/>
<c:set var="LeaveBal" value="${resultValue.leaveBal}"/>

<c:set var="ajaxKey" value="${resultValue.ajaxKey}"/>

<c:set var="parentPost" value="${resultValue.parentPost}"/>
<c:set var="appliedLeaveData" value="${resultValue.appliedLeaveData}"/>
<c:set var="attachmentXMLLst" value="${resultValue.leaveAttachmentXMLLst}"/>
<c:set var="hrleavemain" value="${sessionScope.Modified_HrEssLeaveMainTxn}"/>
<c:set var="isModified" value="${resultValue.isModified}"/>
<fmt:setBundle basename="resources.ess.leave.AlertMessages" 	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"	var="leaveCaption" scope="request" />
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/sanctionLeave.js"/>"></script>
<script><!--
//document.onclick=validateLWP;
var leavetypeSelected=""
var yes='<fmt:message key="HRMS.Yes"/>';
var no='<fmt:message key="HRMS.No"/>';
var counter =1;
var leaveArr=new Array();
var toDateArr= new Array();
var fromDateArr= new Array();
var leavetype_bal=new Array();
//document.onclick=checkHalfDay;

var editedObject;
var toDate;
var fromDate;
var nod=0; 
var duplicateLeave=false;

var inchrg_status=0;
function showhide_inchrg_dtl(inchrg_tbl){
if(inchrg_status==0){
inchrg_tbl.style.display="";
inchrg_status=1;
}
else{
inchrg_tbl.style.display="none";
inchrg_status=0;
}


}

				
function temprorySave()
{
				document.frmleaveapply.action="hdiits.htm?actionFlag=callOperation";
				if('${parentPost}'=='-1' && document.frmleaveapply.status.value=='0'){
						alert("You cant forward this request as you are the parent");
				}else{

		    if(validateBalance(leavetype_bal,document.frmleaveapply,'<fmt:message  bundle="${alertLables}" key="HRMS.AVAILABLE_BALANCE"/>',0,'${leaveTypesBalMaintain}')){
			document.frmleaveapply.submit();
			}
}
}

function validateLWP(){

if(createIncharge())
{
	if(document.forms[0].natureofleave.value=="13_12_25" || document.forms[0].natureofleave.value=="13_12_26"){
		if(document.forms[0].BIS_todate.value==""){
			alert('<fmt:message key="HRMS.BIS_ENDDATE_LEFT_BLANK" bundle="${alertLables}"/>');
		}
		else if(document.forms[0].BIS_todate.value!=""){
			if(document.forms[0].rad_breakinservice[0].status){
				if(eval(dateDifferenceWOAlert(convertToDateDDMMYYYY(document.forms[0].todate.value),convertToDateDDMMYYYY(document.forms[0].BIS_todate.value)))==1){
				alert('<fmt:message key="HRMS.BIS_ENDDATE_GREATERTHAN_LWP_ENDDATE" bundle="${alertLables}"/>');
				return false;
				}
			}
			
			var bisToDate=convertToDateDDMMYYYY(document.forms[0].BIS_todate.value);
			
			
			if(convertToDateDDMMYYYY(document.forms[0].todate.value) < bisToDate){
			alert('<fmt:message key="HRMS.BIS_ENDDATE_GREATERTHAN_LWP_ENDDATE" bundle="${alertLables}"/>');
			document.forms[0].BIS_todate.value=document.forms[0].todate.value;
			return false;
			}
			else if(convertToDateDDMMYYYYParam(document.forms[0].fromdate.value) > bisToDate){
			alert('<fmt:message key="HRMS.BIS_ENDDATE_LESSTHAN_LWP_FROMDATE" bundle="${alertLables}"/>');
			document.forms[0].BIS_todate.value=document.forms[0].todate.value;
			return false;
			}
			document.forms[0].nod.value=eval(dateDifferenceWOAlert(convertToDateDDMMYYYY(document.forms[0].fromdate.value),convertToDateDDMMYYYY(document.forms[0].BIS_todate.value)));
			document.forms[0].approveActionTaken.value=parent.document.getElementById('wfAction').value;
			document.forms[0].rejectActionTaken.value=parent.document.getElementById('reject').value;
			return true;
		
		
		
		}
	}else {
	document.forms[0].approveActionTaken.value=parent.document.getElementById('wfAction').value;
	document.forms[0].rejectActionTaken.value=parent.document.getElementById('reject').value;
	return true;
	
	}
	document.forms[0].approveActionTaken.value=parent.document.getElementById('wfAction').value;
	document.forms[0].rejectActionTaken.value=parent.document.getElementById('reject').value;
	return true;
}
}

function checkDateLimit(dateComp)
{

if(dateComp.value!=""){
var bisToDate=convertToDateDDMMYYYY(dateComp.value);
if(convertToDateDDMMYYYY(document.forms[0].todate.value) < bisToDate){
alert('<fmt:message key="HRMS.BIS_ENDDATE_GREATERTHAN_LWP_ENDDATE" bundle="${alertLables}"/>');
dateComp.value=document.forms[0].todate.value;
return;
}
else if(convertToDateDDMMYYYYParam(document.forms[0].fromdate.value) > bisToDate){
alert('<fmt:message key="HRMS.BIS_ENDDATE_LESSTHAN_LWP_FROMDATE" bundle="${alertLables}"/>');
dateComp.value=document.forms[0].todate.value;
return;
}

}
}


							

function checkBIS(BISradio){
if(BISradio.value==0){
}
else if(BISradio.value==1){
document.forms[0].BIS_todate.value=document.forms[0].todate.value;
}

}							

--></script>






<%
try {
%>

<hdiits:form name="frmleaveapply" action="hdiits.htm?actionFlag=updateLeaveDetails"
 validate="true" method="post" encType="multipart/form-data">

	<table class="tabtable" border="0">
<tr>
<td align="center" colspan="5">
<c:if test="${isModified eq true }">
<b><hdiits:caption captionid="HRMS.ALREADY_MODIFIED" bundle="${alertLables}"/></b>
</c:if>
</td>
</tr>
</table>
<table width="100%">
		<tr>
			<td colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
</tr></table>

<table width="100%" border="0">
		<tr>
			<td colspan="5">
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.Balanceavailable" bundle="${leaveCaption}"  id="LeaveBalanceGrp">	
				<%@ include file="LeaveBalance.jsp"%>
			</hdiits:fieldGroup>	
			</td></tr></table>

		<%@ include file="./previousLeaveDetail.jsp"%>
		<hdiits:fieldGroup bundle="${leaveCaption}" expandable="true" titleCaptionId="HRMS.LeaveEntryForm" id="LeaveEntryGrp">		<c:set var="combTable" value="display:none;" />
		<table width="100%">
		<c:set var="isCombination" value="0" />
		<c:if test="${appliedLeaveData.combFlag==1}">
			<c:set var="combTable" value="display:visiblity;" />
			<c:set var="isCombination" value="${appliedLeaveData.combFlag}" />
			<c:set var="normalLeaveTbl" value="display:none;" />
		</c:if>
		<c:if test="${appliedLeaveData.combFlag==0}">
			<c:set var="normalLeaveTbl" value="display:visiblity;" />
			<c:set var="combTable" value="display:none;" />
		</c:if>



				<tr>
					<td><hdiits:caption captionid="HRMS.comb_leave"
						bundle="${leaveCaption}" /></td>
					<td>
					<c:if test="${appliedLeaveData.combFlag eq 1}">
						<hdiits:caption captionid="HRMS.Yes" bundle= "${leaveCaption}"/>
					</c:if>
					<c:if test="${appliedLeaveData.combFlag eq 0}">
						<hdiits:caption captionid="HRMS.No" bundle= "${leaveCaption}"/>
					</c:if>
					</td>
				</tr>
				<c:set var="leavetypeSelected" value="" />
				<c:set var="halfday" value="0" />
				<c:set var="before2" value="0" />
				<c:set var="startday_endday" value="" />
				<c:set var="breakInService" value="display:none;"/>
				<c:set var="nod" value="" />
				<c:set var="updatedBISToDate" value=""/> 
				<c:set var="breakinserviceRadio" value="1" />
			<c:forEach var="leaveOtherDtl"	items="${appliedLeaveData.hrEssLeaveOtherDtls}">
					<c:set var="halfday" value="${leaveOtherDtl.halfday}" />
					<c:set var="before2" value="${leaveOtherDtl.before2}" />
					<c:set var="startday_endday" value="${leaveOtherDtl.startdayEndday}" />
					<c:set var="leavetypeSelected"
				value="${leaveOtherDtl.hrEssLeaveMst.leaveTypeid}_${leaveOtherDtl.hrEssLeaveMst.srno}_${leaveOtherDtl.hrEssLeaveMst.leavecode}" />
					<c:set var="nod" value="${leaveOtherDtl.noofdays}" />
				<c:if test="${leaveOtherDtl.hrEssLeaveMst.leaveTypeid eq 13}">
						<c:set var="breakInService" value="display:visibility;"/>
					<c:if test="${not empty leaveOtherDtl.breakinserviceTodate}">
						<fmt:formatDate value="${leaveOtherDtl.breakinserviceTodate}" var="updatedBISToDate" pattern="dd/MM/yyyy"/>
						<fmt:formatDate value="${leaveOtherDtl.breakinserviceTodate}" var="updatedBISToDateCopy" pattern="yyyy-MM-dd"/>
					</c:if>
					<c:set var="breakinserviceRadio" value="${leaveOtherDtl.breakinservice}" />
				</c:if>		
			</c:forEach>
		<tr id="natureofleave" style="${normalLeaveTbl}">
			<td><hdiits:caption captionid="HRMS.NatureOfLeave"	bundle="${leaveCaption}" /></td>
			<td id="natureofLeaveTD">
			
			</td>
			<c:set var="counter" value="0" />

			 <hdiits:select style="display:none" 
				sort="false" name="ntl" captionid="HRMS.NatureOfLeave"
				size="1" mandatory="false" onchange="showOrd(this);" tabindex="4"
				bundle="${leaveCaption}" >
				<hdiits:option value="0">
					<fmt:message key="HRMS.select" />
				</hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
					<hdiits:option
						value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}">
										${leavetypes.leaveTypeName}
										</hdiits:option>
					<c:if test="${leavetypes.leaveTypeid ==1 || leavetypes.leaveTypeid==2  || leavetypes.leaveTypeid==3 || leavetypes.leaveTypeid==4}">
						<script>
			leavetype_bal["${counter}"]=leavetype_bal["${counter}"]+"~"+"${leavetypes.leaveTypeid}";			
			</script>
					</c:if>
					<c:set var="counter" value="${counter+1}" />
				</c:forEach>
			</hdiits:select>
			<hdiits:hidden name="natureofleave" caption="natureofleave" default=""/>
			 <c:if test="${appliedLeaveData.combFlag==0}">
				<script>
			document.forms[0].natureofleave.value="${leavetypeSelected}";
			//alert("${leavetypeSelected}");
			//selectComboOption(document.forms[0].ntl,"${leavetypeSelected}");
			document.forms[0].ntl.value="${leavetypeSelected}";
			leavetypeSelected="${leavetypeSelected}";
			document.getElementById("natureofLeaveTD").innerHTML=document.forms[0].ntl.options[document.forms[0].ntl.selectedIndex].innerText;
			</script>
			</c:if>


			<td  id="tag" style="${normalLeaveTbl}"><hdiits:caption
				captionid="HRMS.NoD1" bundle="${leaveCaption}" /></td>
			<td id="nod" style="${normalLeaveTbl}">${nod}</td>

		</tr>
		<tr id="fromdate">
			<td width="18%"><hdiits:caption captionid="HRMS.sanc_fromdate1"
				bundle="${leaveCaption}" /> (DD/MM/YYYY)</td>
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}"
				pattern="dd/MM/yyyy" var="fromDate" />
			<c:if test="${ not empty fromDate}">
				<c:set var="fromDate" value="${fromDate}" />
			</c:if>
			<td ><hdiits:text name="fromdate"	captionid="HRMS.fromdate" bundle="${leaveCaption}" 
			 tabindex="5" default="${fromDate}" readonly="true"/></td>
			<td width="14%"> <hdiits:caption captionid="HRMS.sanc_todate1"
				bundle="${leaveCaption}" /> (DD/MM/YYYY)</td>
			<fmt:formatDate value="${appliedLeaveData.appLeaveTo}"
				pattern="dd/MM/yyyy" var="toDate" />
			<c:if test="${not empty toDate }">
				<c:set var="toDate" value="${toDate}" />
			</c:if>

			<td><hdiits:text name="todate" captionid="HRMS.todate"	bundle="${leaveCaption}" 
				 tabindex="6" default="${toDate}" readonly="true"/></td>

		</tr>
		
				<tr id="breakinservice" style="${breakInService}">
				<td colspan="5">
				<br/>
			<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.LWPBIS" bundle="${leaveCaption}"  id="BreakInSrvcGrp">	
				<table  border="0">
				<tr>
				<td>
				<c:if test="${empty breakinserviceRadio}">
				<c:set var="breakinserviceRadio" value="1"/>
				</c:if>
				<hdiits:radio name="rad_breakinservice" captionid="HRMS.Breakinservice" bundle="${leaveCaption}"  value="0" default="${breakinserviceRadio}" mandatory="false" onclick="checkBIS(this);"/>
				</td>
				<td>
				<hdiits:radio name="rad_breakinservice" captionid="HRMS.continuationinservice" bundle="${leaveCaption}" value="1"  mandatory="false" default="${breakinserviceRadio}" onclick="checkBIS(this);"/>
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
					 <c:set var="lwpEndDate" value="${lwpEndDate} 00:00:00" />
				 </c:if>
					<td>
					<c:if test="${not empty updatedBISToDateCopy}" > 
						<hdiits:text name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${updatedBISToDateCopy}"  readonly="true" />
					</c:if>
					<c:if test="${empty updatedBISToDate}" > 
						<hdiits:text name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${lwpEndDate}"  readonly="true" />
					</c:if>

					
					</td>
				</tr>
				</table>
			</hdiits:fieldGroup>
				</td>
				</tr>
		
		
		
		<tr>
			<td><hdiits:caption captionid="HRMS.ContactAddress"
				bundle="${leaveCaption}" /></td>
			<td><span> <font size="2"><fmt:message
				key="HRMS.CHARACTERLIMIT" bundle="${alertLables}" />&nbsp;[<fmt:message
				key="HRMS.MAXIMUM" bundle="${alertLables}" /> : 2000
			&nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED"
				bundle="${alertLables}" />&nbsp;: <nobr><label
				id="sp_contactaddress_cnt">2000</label>]</nobr> </font> </span> <hdiits:textarea
				mandatory="true" rows="2" cols="50" name="contactaddress"
				tabindex="7" id="c_strNames" validation="txt.isrequired"
				captionid="HRMS.contactaddress" bundle="${leaveCaption}"
				maxlength="2000" default="${appliedLeaveData.empAddress}" readonly="true"/></td>
					 <td id="telephone"><hdiits:caption captionid="HRMS.Telephone"  bundle="${leaveCaption}"/>
					</td>
				<td id="telephone_txt" align="left">
					<c:set var="teleList"  value='${fn:split(appliedLeaveData.phoneno, "-")}'/>
					<c:if test="${not empty appliedLeaveData.phoneno}">
						<c:set var="telephoneTR" value="display:visibility"/>
					</c:if>
						<c:set var="telecode" value=""/>
					<c:if test="${teleList[0] ne 'xxxx'}">
						<c:set var="telecode" value="${teleList[0]}"/>
					</c:if>
					<c:if test="${teleList[0] ne 'xxxx'}">
									${telecode}-
					</c:if>
							${teleList[1]}
					
					</td>
				</tr>
		<tr id="ordinarycir" style="display:none">
			<td><hdiits:caption captionid="HRMS.ordcir"
				bundle="${leaveCaption}" /></td>
			<td align="left"><hdiits:radio name="ordcir" value="1"
				mandatory="false" tabindex="10"  /><hdiits:caption
				captionid="HRMS.Yes" bundle="${leaveCaption}" />&nbsp;&nbsp;&nbsp; <hdiits:radio
				value="0" name="ordcir" mandatory="false" tabindex="11" default="0" /><hdiits:caption
				captionid="HRMS.No" bundle="${leaveCaption}" />&nbsp;&nbsp;</td>
			<td></td>
		</tr>
		<c:set var="halfdayTR" value="display:none" />
		<c:if test="${not empty halfday}">
			<c:set var="halfdayTR" value="display:visibility" />
		</c:if>

		<c:set var="before2TR" value="display:none" />
		<c:if test="${not empty before2 and halfday eq 1}">
			<c:set var="before2TR" value="display:visibility" />
		</c:if>

		<c:set var="halfdayOnfirstday" value="" />
		<c:set var="halfdayOnLastday" value="" />
		<c:set var="startday_enddayTR" value="display:none" />
		<c:set var="after2PMOnFirstdayTD" value="display:none" />
		<c:set var="before2PMOnLastdayTD" value="display:none" />

		<c:if
			test="${not empty startday_endday and halfday eq 1 and startday_endday ne '0_0'}">
			<c:set var="startday_enddayTR" value="display:visibility" />
			<c:set var="splittedStartday"
				value='${fn:split(startday_endday,"_")}' />
			<c:set var="halfdayOnfirstday" value="${splittedStartday[0]}" />
			<c:set var="halfdayOnLastday" value="${splittedStartday[1]}" />
			<c:if test="${startday_endday ne '0_0'}">
				<c:set var="before2TR" value="display:none" />
			</c:if>

			<c:choose>
				<c:when
					test="${halfdayOnfirstday eq 1 and halfday eq 1 and startday_endday ne '0_0'}">
					<c:set var="after2PMOnFirstdayTD" value="display:visibility" />
				</c:when>
				<c:when
					test="${halfdayOnfirstday eq 0 and startday_endday ne '0_0'}">
					<c:set var="after2PMOnFirstdayTD" value="display:none" />
				</c:when>

			</c:choose>

			<c:if
				test="${halfdayOnLastday eq 1 and halfday eq 1 and startday_endday ne '0_0'}">
				<c:set var="before2PMOnLastdayTD" value="display:visibility" />

			</c:if>

			<c:if test="${halfdayOnLastday eq 0 and startday_endday ne  '0_0'}">
				<c:set var="before2PMOnLastdayTD" value="display:none" />

			</c:if>

		</c:if>

		<tr id="halfday" style="${halfdayTR}">
			<td><hdiits:caption captionid="HRMS.halfday"
				bundle="${leaveCaption}" /></td>
			<td>
			<c:if test="${halfday eq 1}" >
				<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
			</c:if>

			<c:if  test="${halfday eq 0}" >
				<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
			</c:if>
			</td>
			<td id="before2_lbl" style="${before2TR}">
			<hdiits:caption	captionid="HRMS.before2" bundle="${leaveCaption}" /></td>
			<td id="before2_radio" style="${before2TR}">
			<c:if  test="${before2 eq 1}" >
				<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
			</c:if>
			<c:if  test="${before2 eq 0}" >
				<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
			</c:if>
</td>
		</tr>
		<tr id="First_Day" style="${startday_enddayTR}">
			<td id="firstday_halfday"><fmt:message key="HRMS.halfdayon" />&nbsp;
			<fmt:formatDate value="${appliedLeaveData.appLeaveFrom}"
				pattern="dd/MM/yyy" /></td>
			<td>
						
				<c:if  test="${halfdayOnfirstday eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				</c:if>
				<c:if  test="${halfdayOnfirstday eq 0}" >
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</c:if>
		 	</td>
			<td id="before2_radio_firstday"><hdiits:caption
				captionid="HRMS.after2pm" bundle="${leaveCaption}" /></td>
			</tr>


		<tr id="Last_Day" style="${before2PMOnLastdayTD}">
			<td id="lastday_halfday"><fmt:message key="HRMS.halfdayon" />&nbsp;<fmt:formatDate
				value="${appliedLeaveData.appLeaveTo}" pattern="dd/MM/yyy" /></td>
			<td>
			
			<c:if  test="${halfdayOnLastday eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				</c:if>
				<c:if  test="${halfdayOnLastday eq 0}" >
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</c:if>
		</td>
			<td id="before2_radio_lastday"><hdiits:caption
				captionid="HRMS.before2pm" bundle="${leaveCaption}" /></td>
		</tr>
		<tr>
			<c:set var="prefixSelected" value="0" />
			<c:set var="prefixFromDatesTR" value="display:none" />
			<c:if test="${not empty appliedLeaveData.prefixFromdate}">
				<c:set var="prefixSelected" value="1" />
				<c:set var="prefixFromDatesTR" value="display:visible" />
			</c:if>

			<td><hdiits:caption captionid="HRMS.Prefix"
				bundle="${leaveCaption}" /></td>
			<td align="left">
			<c:if  test="${prefixSelected eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				</c:if>
				<c:if  test="${prefixSelected eq 0}" >
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</c:if>&nbsp;&nbsp;</td>
		</tr>



		<tr name="yes1" id="yes1" width="100%" border="0"
			style="${prefixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.PrefixFromDate"
				bundle="${leaveCaption}" /> (DD-MM-YYYY)</td>

			<fmt:formatDate value="${appliedLeaveData.prefixFromdate}"
				pattern="yyyy-MM-dd" var="prefixFromdate" />
			<c:if test="${ not empty prefixFromdate}">
				<c:set var="prefixFromdate" value="${prefixFromdate} 00:00:00" />
			</c:if>

			<td><hdiits:text name="prefixfromdate" mandatory="false"
				captionid="HRMS.prefixfromdate" tabindex="22"
				bundle="${leaveCaption}" 
				default="${prefixFromdate}" readonly="true"/></td>
			<td><hdiits:caption captionid="HRMS.PrefixToDate"
				bundle="${leaveCaption}" /> (DD-MM-YYYY)</td>
			<td><fmt:formatDate value="${appliedLeaveData.prefixTodate}"
				pattern="yyyy-MM-dd" var="prefixTodate" /> <c:if
				test="${ not empty prefixTodate}">
				<c:set var="prefixTodate" value="${prefixTodate} 00:00:00" />
			</c:if> <hdiits:text name="prefixtodate" mandatory="false"
				captionid="HRMS.prefixtodate" tabindex="23" bundle="${leaveCaption}"
				default="${prefixTodate}" readonly="true" /></td>

		</tr>

		<c:set var="suffixSelected" value="0" />
		<c:set var="suffixFromDatesTR" value="display:none" />
		<c:if test="${not empty appliedLeaveData.suffixFromdate}">
			<c:set var="suffixSelected" value="1" />
			<c:set var="suffixFromDatesTR" value="display:visible" />
		</c:if>

		<tr>
			<td><hdiits:caption captionid="HRMS.Suffix"
				bundle="${leaveCaption}" /></td>
			<td align="left"><c:if  test="${suffixSelected eq 1}" >
					<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
				</c:if>
				<c:if  test="${suffixSelected eq 0}" >
					<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
				</c:if></td>
			<td></td>
		</tr>

		<fmt:formatDate value="${appliedLeaveData.suffixFromdate}"
			pattern="yyyy-MM-dd" var="suffixFromdate" />
		<c:if test="${ not empty suffixFromdate}">
			<c:set var="suffixFromdate" value="${suffixFromdate} 00:00:00" />
		</c:if>

		<fmt:formatDate value="${appliedLeaveData.suffixTodate}"
			pattern="yyyy-MM-dd" var="suffixTodate" />
		<c:if test="${ not empty suffixTodate}">
			<c:set var="suffixTodate" value="${suffixTodate} 00:00:00" />
		</c:if>



		<tr name="yes2" id="yes2" style="${suffixFromDatesTR}">
			<td><hdiits:caption captionid="HRMS.SuffixFromDate"
				bundle="${leaveCaption}" /> (DD-MM-YYYY)</td>
			<td><hdiits:text name="suffixfromdate" mandatory="false"
				captionid="HRMS.suffixfromdate" tabindex="26"
				bundle="${leaveCaption}" default="${suffixFromdate}" readonly="true"
				/></td>
			<td><hdiits:caption captionid="HRMS.SuffixToDate"
				bundle="${leaveCaption}" /> (DD-MM-YYYY)</td>
			<td><hdiits:text name="suffixtodate" mandatory="false"
				captionid="HRMS.suffixtodate" tabindex="27" bundle="${leaveCaption}"
				default="${suffixTodate}" readonly="true"></hdiits:text></td>
		</tr>
		<tr>
			<td><hdiits:caption captionid="HRMS.Remarks"
				bundle="${leaveCaption}" /></td>
			<td><span> <font size="2"><fmt:message
				key="HRMS.CHARACTERLIMIT" bundle="${alertLables}" />&nbsp;[<fmt:message
				key="HRMS.MAXIMUM" bundle="${alertLables}" /> : 2000
			&nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED"
				bundle="${alertLables}" />&nbsp;: <nobr><label
				id="sp_leaveremarks_cnt">2000</label>]</nobr> </font> </span> <hdiits:textarea rows="5"
				cols="50" name="remarks" tabindex="28" id="c_strNames"
				validation="txt.isrequired" captionid="HRMS.remarks"
				bundle="${leaveCaption}" mandatory="true" maxlength="2000"
				default="${appliedLeaveData.remarks}" readonly="true"/></td>

			<td><hdiits:caption captionid="HRMS.LeaveReason"
				bundle="${leaveCaption}" /></td>
			<td><span> <font size="2"><fmt:message
				key="HRMS.CHARACTERLIMIT" bundle="${alertLables}" />&nbsp;[<fmt:message
				key="HRMS.MAXIMUM" bundle="${alertLables}" /> : 2000
			&nbsp;,&nbsp;&nbsp;<fmt:message key="HRMS.CHARACTERENTERED"
				bundle="${alertLables}" />&nbsp;: <nobr><label
				id="sp_leavereason_cnt">2000</label>]</nobr> </font> </span> <hdiits:textarea
				mandatory="true" rows="5" cols="40" name="leavereason" tabindex="29"
				id="c_strNames" validation="txt.isrequired"
				captionid="HRMS.leavereason" bundle="${leaveCaption}"
				maxlength="2000" default="${appliedLeaveData.reason}" readonly="true" /></td>
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

	<table id="lvdtl" width="100%"
		style="border-collapse: collapse;${combTable}" borderColor="BLACK"
		border=1>

		<tbody>
			<c:set var="showCheckBoxInCombinationLeave" value="false"/>
			<%@ include file="CmnCombinationLeave.jsp"%>
		</tbody>
	
	</table>	
</hdiits:fieldGroup>
<BR>
		<hdiits:fieldGroup bundle="${leaveCaption}" style="display:none" expandable="false" collapseOnLoad="false" titleCaptionId="HRMS.Agreement" id="agreement">
				<table border="0" width="100%">
				<tr><td><hdiits:checkbox id="chkAgree1" name="chkAgree1"  value="1" default="1" readonly="true"/></td>
					<td><fmt:message key="HRMS.Agreement1" bundle="${leaveCaption}"></fmt:message></td></tr>
				<tr><td><hdiits:checkbox id="chkAgree2" name="chkAgree2" value="1" default="1" readonly="true"/></td>
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



	<hdiits:hidden caption="leaveid" name="leaveid"  default="${appliedLeaveData.leaveid}"/>
	<hdiits:hidden caption="noofdays" name="nod"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

<br/>	<center>
	<hdiits:button type="button" name="close"  captionid="HRMS.Close" bundle="${leaveCaption}" onclick="window.close();"/>
	</center>
</hdiits:form>
<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");

</script>
</c:forEach>
<script>document.getElementById('formTable1LeaveAttachment').firstChild.firstChild.style.display='none';
document.getElementById('target_uploadLeaveAttachment').style.display='none';

</script>
<c:remove var="Modified_HrEssLeaveMainTxn" scope="session"/>
<script type="text/javascript">
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