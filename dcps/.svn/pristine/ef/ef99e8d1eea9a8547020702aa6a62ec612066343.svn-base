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
<c:set var="hlist" value="${resultValue.holidayList}">
</c:set>
<c:set var="LeaveBal" value="${resultValue.leaveBal}">
</c:set>
<c:set var="joiningVO" value="${resultValue.joiningDtlVO}">
</c:set>
<c:set var="leavemain" value="${joiningVO.hrEssLeaveMainTxn}" />
<c:set var="ajaxKey" value="${resultValue.ajaxKey}">
</c:set>
<c:set var="parentPost" value="${resultValue.parentPost}">
</c:set>
<c:set var="cancelLeaveVO" value="${resultValue.cancellationVO}">
</c:set>
<c:set var="attachmentXMLLst" value="${resultValue.leaveAttachmentXMLLst}"/>
<c:set var="cancelLeaveAttachment"	value="${resultValue.cancelLeaveAttachmentXMLLst}">
</c:set>
<c:set var="modifiedLeaveAttachment"
	value="${resultValue.modifiedLeaveAttachment}">
</c:set>

<c:set var ="appliedLeaveData" value="${joiningVO.hrEssLeaveMainTxn}"/>
<c:set var="attachmentXMLLst" value="${resultValue.leaveAttachmentXMLLst}"/>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavejoiningsanction.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>

<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/cmntableheader.js"/>"></script>


<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"
	var="leaveCaption" scope="request" />
<script>
var leavetypeSelected='';
var remOf='<fmt:message key="RemarksOf"/>';
var counter =1;
var leaveArr=new Array();
var toDateArr= new Array();
var fromDateArr= new Array();
var leavetype_bal=new Array();
var leaveDataArrParent= new Array();
var editedObject;
var toDate;
var fromDate;
var nod=0; 
var duplicateLeave=false;

var frm;











function deleteRow(actionFlag)
{
var delimiter="~";
var deletedObject="";	
var nondeletedObject="";
var checkCounter=0;
	var checks=document.getElementsByTagName("INPUT");
		for(j=0;j<checks.length;j++){
			if(checks[j].type=="checkbox"){
				if(checks[j].name=="cmb_leave"){
					if(checks[j].checked){
						editedObject=document.getElementById("row"+checks[j].id);
						
						deletedObject+="row"+checks[j].id+delimiter;
						checkCounter+=1;
							}
						else
						{
 						 nondeletedObject+="row"+checks[j].id+delimiter;
						}	
							
					}
				}
			}

if(actionFlag==2){
	delRow(deletedObject,nondeletedObject);
}
else{
if(checkCounter>1){
alert('<fmt:message  bundle="${alertLables}" key="HRMS.MULTIPLE_EDIT"/>');

}
else if(checkCounter<1){
alert('<fmt:message  bundle="${alertLables}" key="HRMS.SELECT_LEAVE"/>');
}
else{

value=document.getElementById("txt"+editedObject.cnt).value;
win=window.open("hdiits.htm?actionFlag=viewLeaveTypes&flag=0&editedValue="+value,"LeaveTypes","width=700,height=240,status=yes,resizable=no,top=100,left=100");
}
}
}
function delRow(deletedObject,nondeletedObject){

var delCounter=0;
var temp="";
var tbody = document.getElementById("lvdtl").getElementsByTagName("tbody")[0]; 
var rows=deletedObject.split("~");

	for(m=0;m<rows.length-1;m++){
			temp+=document.getElementById(rows[m]).cnt+"~";
						for(l=0;l<leaveArr.length;l++){
							if(document.getElementById(rows[m]).cnt==leaveArr[l]){
									
									
									leaveArr.splice(l,1);
									toDateArr.splice(l,1);
									fromDateArr.splice(l,1);
									
									
								}
							}
			counter-=1;
			delCounter+=1;	
			tbody.removeChild(document.getElementById(rows[m]));
			
		}
if(delCounter>0){
var serialUpdate=nondeletedObject.split("~");

for(k=0;k<serialUpdate.length-1;k++){
var cell=document.getElementById("sr_"+document.getElementById(serialUpdate[k]).cnt);
var srno=eval((cell.innerHTML)-delCounter);
if(srno>0){

cell.innerHTML=srno;
}


}
}
if(nondeletedObject==""){
tbody.innerText="";
document.getElementById("lvdtl").style.display="none";
counter=1;
}
}






function checkData(leavedetails)
{


var lvldtl=leavedetails.split("~");

for(k=0;k<leaveArr.length;k++)
{
	if(leaveArr[k]!=lvldtl[0]){
	duplicateLeave=true;
    }
	else if(leaveArr[k]==lvldtl[0]){
	duplicateLeave=false;
	break;
    }
}


if(duplicateLeave){
return true;
}else{
		
	for(k=0;k<leaveArr.length;k++){
			if(k!=(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1)){
			if(leaveArr[k]==lvldtl[0]){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
				return false;
			}
		}
	}
	}
	
	}



function continuationDate(todate,nextDate){

var diff=dateDifference(todate,nextDate);

	if(diff>0){
	if(diff ==2){
	 toDate=toDate; 
		return true;
		}
	else{
	toDate=todate; 
alert('<fmt:message  bundle="${alertLables}" key="HRMS.CONTINUATION_DATE"/>');
	return false;
	}	
}else{
toDate=todate; 
return false;
}

}


function editRow(oldcomponentid,newcomponentid,previousValue)
{
var lvldtl=previousValue;
var lvdtl=document.getElementById("txt"+oldcomponentid).value;

var lvldtl=lvldtl.split("~");
var newlvldtl=lvdtl.split("~");

for(k=0;k<leaveArr.length;k++)
{
	if(leaveArr[k]!=lvldtl[0]){
	duplicateLeave=true;
    }
	else if(leaveArr[k]==lvldtl[0]){
	duplicateLeave=false;
	break;
    }
}


if(duplicateLeave==false){
		var chk=eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-1;

	for(k=0;k<leaveArr.length;k++){

			if(k!=chk){

			if(leaveArr[k]==newlvldtl[0]){
				alert('<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>');
				document.getElementById("txt"+oldcomponentid).value=previousValue;
				return false;
			}
		}
	}

}



var continuationFlag=true;
if(eval(eval(document.getElementById("sr_"+lvldtl[0]).innerHTML))> 1){
var tempDate=newlvldtl[1].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
continuationFlag=continuationDate(toDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)-2],new Date(tempDate));	
}
else{
if(counter>2){
var tempDate=newlvldtl[2].split("/");
tempDate=tempDate[1]+"/"+tempDate[0]+"/"+tempDate[2];
continuationFlag=continuationDate(new Date(tempDate),fromDateArr[eval(document.getElementById("sr_"+lvldtl[0]).innerHTML)]);	
}
}

if(continuationFlag){
document.getElementById("txt"+oldcomponentid).setAttribute("id","txt"+newcomponentid);
document.getElementById("rowchk"+oldcomponentid).setAttribute("cnt",newcomponentid);
document.getElementById("rowchk"+oldcomponentid).setAttribute("id","rowchk"+newcomponentid);
document.getElementById("chk"+oldcomponentid).setAttribute("id","chk"+newcomponentid);
editedObject=document.getElementById("rowchk"+newcomponentid);

document.getElementById("leavetype_"+oldcomponentid).setAttribute("id","leavetype_"+newcomponentid);
document.getElementById("fromdate_"+oldcomponentid).setAttribute("id","fromdate_"+newcomponentid);
document.getElementById("todate_"+oldcomponentid).setAttribute("id","todate_"+newcomponentid);
document.getElementById("nod_"+oldcomponentid).setAttribute("id","nod_"+newcomponentid);
document.getElementById("sr_"+oldcomponentid).setAttribute("id","sr_"+newcomponentid);
var lvdtl=document.getElementById("txt"+newcomponentid).value;
var lvdtlArr=lvdtl.split("~");


document.getElementById("leavetype_"+newcomponentid).innerHTML=document.frmleaveapply.nol.options[lvdtlArr[0]].innerHTML; 

document.getElementById("fromdate_"+newcomponentid).innerHTML=lvdtlArr[1];
document.getElementById("todate_"+newcomponentid).innerHTML=lvdtlArr[2];

var inputtag=document.getElementById("nod_"+newcomponentid).innerHTML;

var splited=inputtag.split("<");


document.getElementById("nod_"+newcomponentid).innerHTML=lvdtlArr[3]+"<"+ splited[1];




for(k=0;k<leaveArr.length;k++){
if(leaveArr[k]==oldcomponentid){
leaveArr[k]=newcomponentid;
}
}
return true;
/* work here for updating the innerHTML of tds*/

}else{
document.getElementById("txt"+oldcomponentid).value=previousValue;
return false;
}


}

			
 
			function showOrd(combo){
						if(combo.value==3){


								document.getElementById("ordinarycir").style.display="";
				
						}else{
								document.getElementById("ordinarycir").style.display="none";
						}
				}



			function goin1(l)
			{	
				var id = l.value;
				if(id =='1')
				{
					document.getElementById('yes1').style.display='';
					document.getElementById('no1').style.display='none';
				}
				else if(id == '0')
				{
				
					document.getElementById('yes1').style.display='none';
					document.getElementById('no1').style.display='';
				}
			}
			function goin2(l)
			{	
				var id = l.value;
				if(id == '1')
				{
					document.getElementById('yes2').style.display='';
					document.getElementById('no2').style.display='none';
				}
				else if(id == '0')
				{
					document.getElementById('yes2').style.display='none';
					document.getElementById('no2').style.display='';
				}
			}
							
function submitRequest()
{
				document.frmleaveapply.action="hdiits.htm?actionFlag=SanctionJoining";

			if('${parentPost}'=='-1' && document.frmleaveapply.status.value=='0'){
						alert("You cant forward this request as you are the parent");
				}else{
				document.frmleaveapply.submit();
			}

}



							
		function validateLWP(){

	if(document.forms[0].ntl.value=="13_12_25" || document.forms[0].ntl.value=="13_12_26"){
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
							

</script>






<%
try {
%>



<hdiits:form name="frmleaveapply" validate="true" method="post" 	encType="multipart/form-data">
	<div id="leave" name="leave">
	<table class="tabtable" border="0">
		<tr>
			<td><c:choose>
				<c:when test="${joiningVO.joiningDays=='0'}">
					<b> <font color="red"> <fmt:message key="HRMS.JOINED" />
					<fmt:message key="HRMS.SAMEDAY" /> </font> </b>
					<br/>
				</c:when>
				<c:when test="${joiningVO.joiningDays < '0'}">
					<b> <font color="red"> <fmt:message key="HRMS.JOINED" />
					&nbsp;${joiningVO.joiningDays * -1}&nbsp; <fmt:message
						key="HRMS.EARLYDAYS" /> </font> </b>					<br/>
				</c:when>
				<c:when test="${joiningVO.joiningDays > '0'}">
					<b> <font color="red"> <fmt:message key="HRMS.JOINED" />
					&nbsp;${joiningVO.joiningDays}&nbsp; <fmt:message key="HRMS.LATE" />
					</font> </b>					<br/>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> <c:if test="${joiningVO.halfday=='1'}">
<b> <font color="red"> <fmt:message key="HRMS.halfday" />
			<fmt:message key="HRMS.after2pm" /> &nbsp; </font> </b>
			</c:if></td>
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
			</td>
		</tr>
</table>

<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.leave_entry_detail1" bundle="${leaveCaption}"  id="LeaveEntryDtlsGrp">	
<table width="100%" border="0">			
			
<!-- ************ -->

	<c:set var="combTable" value="display:none;" />
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

			 <hdiits:select
				sort="false" name="ntl" captionid="HRMS.NatureOfLeave"
				size="1" mandatory="false" onchange="showOrd(this);" tabindex="4"
				bundle="${leaveCaption}" style="display:none">
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
			document.forms[0].ntl.value="${leavetypeSelected}";
			document.forms[0].natureofleave.value="${leavetypeSelected}";
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
				
				<hdiits:fieldGroup bundle="${leaveCaption}" expandable="false"  titleCaptionId="HRMS.LWPBIS" id="bisGrp">
				<table  border="0">
				<tr>
				<td>
				<c:if test="${empty breakinserviceRadio}">
				<c:set var="breakinserviceRadio" value="1"/>
				</c:if>
				<hdiits:radio name="rad_breakinservice" caption="Break in service"  value="0" default="${breakinserviceRadio}" mandatory="false" onclick="checkBIS(this);"/>
				</td>
				<td>
				<hdiits:radio name="rad_breakinservice" caption="Continuation in service" value="1"  mandatory="false" default="${breakinserviceRadio}" onclick="checkBIS(this);"/>
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
						<hdiits:dateTime name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${updatedBISToDateCopy} 00:00:00"   maxvalue="31/12/9999"/>
					</c:if>
					<c:if test="${empty updatedBISToDate}" > 
					<hdiits:dateTime name="BIS_todate" captionid="HRMS.todate"	bundle="${leaveCaption}" mandatory="false" default="${lwpEndDate}"   maxvalue="31/12/9999" />
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
				value="0" name="ordcir" mandatory="false" tabindex="11" default="0"/><hdiits:caption
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
			<c:if  test="${halfday eq 1}" >
				<hdiits:caption captionid="HRMS.Yes" bundle="${leaveCaption}"/>
			</c:if>

			<c:if  test="${halfday eq 0}" >
				<hdiits:caption captionid="HRMS.No" bundle="${leaveCaption}"/>
			</c:if>
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
<tr>
<td colspan="5">
	<table id="lvdtl" width="100%"
		style="border-collapse: collapse;${combTable}" borderColor="BLACK"
		border=1>

		<tbody>
			<c:set var="showCheckBoxInCombinationLeave" value="false"/>
			<%@ include file="CmnCombinationLeave.jsp"%>
		</tbody>
	
</table>
</td>
</tr>
</table>
</hdiits:fieldGroup>
<!-- ************ -->
	
		<hdiits:fieldGroup bundle="${leaveCaption}" expandable="false" collapseOnLoad="false" titleCaptionId="HRMS.Agreement" id="agreement" style="display:none">
				<table border="0" width="100%">
				<tr><td><hdiits:checkbox id="chkAgree1" name="chkAgree1" value="1" default="1" readonly="true" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement1" bundle="${leaveCaption}"></fmt:message></td></tr>
				<tr><td><hdiits:checkbox id="chkAgree2" name="chkAgree2" value="1" default="1" readonly="true" mandatory="true" /></td>
					<td><fmt:message key="HRMS.Agreement2" bundle="${leaveCaption}"></fmt:message></td></tr>
				</table>	
		</hdiits:fieldGroup>
		

	<%@ include file="./ModifiedLeaveDetail.jsp"%>
	
	<%@ include file="./CancelLeaveJoiningDetail.jsp"%>
	
<hdiits:fieldGroup  expandable="true" titleCaptionId="HRMS.JoiningDetail" bundle="${leaveCaption}"  id="JoinDtlsGrp">	
<table class="tabtable" width="100%"> 
	
				
				<tr>
					<td width="10%"><hdiits:caption captionid="HRMS.DutyResumptionDate" bundle="${leaveCaption}"/></td>
					<td><hdiits:text name="DutyResumptionDate" mandatory="false"
						caption="DutyResumptionDate" tabindex="2" readonly="true" ></hdiits:text></td>
					<script>
if(leavetypeSelected=='3_3_5')
{
	document.getElementById('agreement').style.display="";
}
document.forms[0].DutyResumptionDate.value=DDMMYYYY("${joiningVO.dutyResumptionDate}");
</script>

				</tr>

				<tr>
					<td width="20%"><hdiits:caption captionid="HRMS.LeaveReason"  bundle="${leaveCaption}"/></td>
					<td><hdiits:textarea mandatory="true" rows="5" cols="60"
						name="joining_remarks" tabindex="3" id="c_strNames"
						validation="txt.isrequired" caption="Remarks"
						default="${joiningVO.remarks}" readonly="true" maxlength="2000">

					</hdiits:textarea></td>
				</tr>
			
	</table>
</hdiits:fieldGroup>
</div>
	

	<table id="applLeaveDtl" border=0 style="display:none">
		<tr>
			<td><hdiits:caption captionid="HRMS.NatureOfLeave"  bundle="${leaveCaption}"/></td>
			<td><hdiits:select sort="false" name="natureofleave" size="1"
				caption="drop_down" tabindex="8" mandatory="false">
				<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
				<c:forEach var="leavetypes" items="${LeaveTList}">
					<hdiits:option
						value="${leavetypes.leaveTypeid}_${leavetypes.srno}_${leavetypes.leavecode}">
						${leavetypes.leaveTypeName}
				</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
</table>
	<hdiits:hidden name="approveActionTaken" caption="approveActionTaken" />
	<hdiits:hidden name="rejectActionTaken" caption="rejectActionTaken" />		
	<hdiits:jsField jsFunction="validateLWP()" name="validateLWP1" />
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:hidden caption="noofdays" name="nod"/>
	<hdiits:hidden name="leavedtls" caption="leavedtls" />
</hdiits:form>


<c:forEach var="attachmentXML" items="${attachmentXMLLst}">
<script>
populateAttachmentForEditing('${attachmentXML}',"LeaveAttachment");

</script>
</c:forEach>
<c:forEach var="attachmentXML" items="${cancelLeaveAttachment}">
<script>
populateAttachmentForEditing('${attachmentXML}',"cancelAttachment");

</script>
</c:forEach>



<c:if test="${not empty cancelLeaveAttachment}">
<script>
document.getElementById('formTable1cancelAttachment').firstChild.firstChild.style.display='none';
</script>


</c:if>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
