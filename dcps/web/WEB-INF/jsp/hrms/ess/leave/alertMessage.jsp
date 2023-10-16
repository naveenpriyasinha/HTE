<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<style type="text/css">
<%@ include file="./theme/hrmsdefault.css" %>
</style>



<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"
	var="leaveCaption" scope="request" />

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateDifference.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/leavevalidation.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/ess/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script>

var remOf='<fmt:message key="HRMS.RemarksOf" bundle="${leaveCaption}"/>';
var alertMsg=new Array();
var alertMsgJoining =new  Array();
alertMsg[0]='<fmt:message  bundle="${alertLables}" key="HRMS.MULTIPLE_EDIT"/>';
alertMsg[1]='<fmt:message  bundle="${alertLables}" key="HRMS.SELECT_LEAVE"/>';
alertMsg[2]='<fmt:message  bundle="${alertLables}" key="HRMS.DELETIONCONFIRMATION"/>';
alertMsg[3]='You must select half day on either datess';
alertMsg[4]='<fmt:message  bundle="${alertLables}" key="HRMS.LEAVEDETAIL_REMOVED"/>';
alertMsg[5]='<fmt:message  bundle="${alertLables}" key="HRMS.AGREEMENT_SELECT"/>';



var alertMsgDates= new Array();
alertMsgDates[0]='<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_FALLS_IN_HOLIDAY"/>';
alertMsgDates[1]='<fmt:message  bundle="${alertLables}" key="HRMS.TODATE_FALLS_IN_HOLIDAY"/>';
alertMsgDates[2]='<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_FALLS_ON_SUNDAY"/>';
alertMsgDates[3]='<fmt:message  bundle="${alertLables}" key="HRMS.APPLIED_FOR_GENERALHOLIDAY"/>';
alertMsgDates[4]='<fmt:message  bundle="${alertLables}" key="HRMS.FROMDATE_LESSTHAN_ENDDATE"/>';
alertMsgDates[5]='<fmt:message  bundle="${alertLables}" key="HRMS.PREFIXTODATE_LESSTHAN_LEAVEFROMDATE"/>';
alertMsgDates[6]='<fmt:message  bundle="${alertLables}" key="HRMS.PREFIXDATE_IN_CONTINUATION"/>';
alertMsgDates[7]='<fmt:message  bundle="${alertLables}" key="HRMS.PREFIXFROMDATE_LESS_THAN_PREFIXTODATE"/>';
alertMsgDates[8]='<fmt:message  bundle="${alertLables}" key="HRMS.SUFFIXFROMDATE_GREATERTHAN_LEAVETODATE"/>';
alertMsgDates[9]='<fmt:message  bundle="${alertLables}" key="HRMS.SUFFIXDATE_IN_CONTINUATION"/>';
alertMsgDates[10]='<fmt:message bundle="${alertLables}" key="HRMS.SUFFIXFROMDATE_LESSTHAN_SUFFIXTODATE"/>';
alertMsgDates[11]='<fmt:message bundle="${alertLables}" key="HRMS.SHOULDFALLINHOLIDAY"/>';
alertMsgDates[12]='<fmt:message bundle="${alertLables}" key="HRMS.ATLEASTTWOLEAVES"/>';
alertMsgDates[13]='<fmt:message bundle="${alertLables}" key="HRMS.PREFIX_IN_HOLIDAY"/>';
alertMsgDates[14]='<fmt:message bundle="${alertLables}" key="HRMS.SUFFIX_IN_HOLIDAY"/>';
alertMsgDates[15]='<fmt:message  bundle="${alertLables}" key="HRMS.PREFIXB42PM"/>';
alertMsgDates[16]='<fmt:message  bundle="${alertLables}" key="HRMS.SUFFIXB42PM"/>'; 
alertMsgDates[17]='<fmt:message  bundle="${alertLables}" key="HRMS.SIMILAR_COMBINATIONAL_LEAVE"/>';
alertMsgDates[18]='<fmt:message  bundle="${alertLables}" key="HRMS.SELECT_COMBI"/>';
alertMsgDates[19]='<fmt:message  bundle="${alertLables}" key="HRMS.AVAILABLE_BALANCE"/>';
alertMsgDates[20]='<fmt:message  bundle="${alertLables}" key="HRMS.CONTINUATION_DATE"/>';
alertMsgDates[21]='<fmt:message  bundle="${alertLables}" key="HRMS.SUBMITCONFIRMATION"/>';
alertMsgDates[22]='<fmt:message  bundle="${alertLables}" key="HRMS.MODIFYTHISLEAVE"/>';
alertMsgDates[23]='<fmt:message  bundle="${alertLables}" key="HRMS.CANCELTHISLEAVE"/>';
alertMsgDates[24]='<fmt:message  bundle="${alertLables}" key="HRMS.BALANCE_NOT_AVAILABLE"/>';
alertMsgJoining[0]='<fmt:message  bundle="${alertLables}" key="HRMS.DUTY_RESUMP_DATE"/>';
alertMsgJoining[1]='<fmt:message  bundle="${alertLables}" key="HRMS.LESS_DUTY_RESUMP_DATE"/>';
alertMsgJoining[2]='<fmt:message  bundle="${alertLables}" key="HRMS.PROPER_FROM_DATE"/>';



var requiredMsg=new Array();

requiredMsg[0]='<fmt:message  bundle="${alertLables}" key="ERROR.INPUT_REQUIRED"/>';
requiredMsg[1]='<fmt:message  bundle="${alertLables}" key="ERROR.VALID_DATE"/>';
requiredMsg[2]='<fmt:message  bundle="${alertLables}" key="ERROR.SELECT_REQUIRED"/>';


var components=new Array();

components[0]='<fmt:message   key="HRMS.NatureOfLeave"/>';
components[1]='<fmt:message  key="HRMS.prefixfromdate"/>';
components[2]='<fmt:message  key="HRMS.prefixtodate"/>';
components[3]='<fmt:message   key="HRMS.suffixfromdate"/>';
components[4]='<fmt:message  key="HRMS.suffixtodate"/>';
components[5]='<fmt:message  bundle="${alertLables}" key="ERROR.INPUT_REQUIRED"/>';
components[6]='<fmt:message  key="HRMS.fromdate"/>';
components[7]='<fmt:message  key="HRMS.todate"/>';
components[8]='<fmt:message  key="HRMS.Telephone" bundle="${leaveCaption}"/>';
components[9]='<fmt:message  bundle="${alertLables}" key="ERROR.TEXTAREALIMIT"/>';



var labels =new Array();

labels[0]='<fmt:message key="HRMS.sanc_fromdate1"/>';
labels[1]='<fmt:message key="HRMS.sanc_todate1"/>';
labels[2]='<fmt:message key="HRMS.FromDate"/>';
labels[3]='<fmt:message key="HRMS.ToDate"/>';
labels[4]='<fmt:message key="HRMS.Yes"/>';
labels[5]='<fmt:message key="HRMS.No"/>';
labels[6]='<fmt:message key="HRMS.after2pm"/>';
labels[7]='<fmt:message key="HRMS.before2pm"/>';
labels[8]='<fmt:message key="HRMS.halfdayon"/>';
labels[9]='<fmt:message key="HRMS.AlreadyProcessed"/>';
labels[10]='<fmt:message key="HRMS.Dash"/>';
labels[11]='<fmt:message key="HRMS.NotingDetails"/>';
labels[12]='<fmt:message key="HRMS.SrNo"/>';
labels[13]='<fmt:message key="HRMS.LeaveType"/>';
labels[14]='<fmt:message key="HRMS.sanc_days1"/>';
labels[15]='<fmt:message key="HRMS.NoD1"/>';



function alertMessages(msgType)
{
	if(msgType=="CL0001"){
				
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CL0001"/>'+"</b></font>";

				
				
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.CL0001"/>');
					return false;
	}
	else if(msgType=="CL1001"){

					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CL1001"/>'+"</b></font>";
	//				alert('<fmt:message  bundle="${alertLables}" key="HRMS.CL1001"/>');
					return false;
	}
	else if(msgType=="CL1002"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	documen.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CL1002"/>'+"</b></font>";		
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.CL1002"/>');
					return false;
	}
	else if(msgType=="CL1003"){

					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	documen.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CL1003"/>'+"</b></font>";		
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.CL1003"/>');
					return false;
	}
	else if(msgType=="EL1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.EL1001"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.EL1001"/>');
					return false;
	}
	
	else if(msgType=="EL1002"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.EL1002"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.EL1001"/>');
					return false;
	}
	
	
	else if(msgType=="CM1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CM1001"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.CM1001"/>');
					return false;
	}
	else if(msgType=="CM1002"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CM1002"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.CM1001"/>');
					return false;
	}
	else if (msgType=="CL1004")
	{
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.CL1004"/>'+"</b></font>";
					return false;
	}
	else if (msgType=="RH1001")
	{
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.RH1001"/>'+"</b></font>";
					return false;
	}
	else if(msgType=="ML1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.ML1001"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.ML1001"/>');
					return false;
	}
	else if(msgType=="PL1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.PL1001"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.PL1001"/>');
					return false;
	}
	else if(msgType=="JL1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.JL1001"/>'+"</b></font>";
	
	//				alert('<fmt:message  bundle="${alertLables}" key="HRMS.JL1001"/>');
					return false;
	}
	else if(msgType=="ST1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.ST1001"/>'+"</b></font>";
//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.ST1001"/>');
					return false;
	}
	else if(msgType=="RL1001"){
					document.getElementById("messages").style.display="";
					document.getElementById("alertMsg").innerHTML=	document.getElementById("alertMsg").innerHTML+"<br><font color='red'>* <b>"+'<fmt:message  bundle="${alertLables}" key="HRMS.RL1001"/>'+"</b></font>";

//					alert('<fmt:message  bundle="${alertLables}" key="HRMS.RL1001"/>');
					return false;
	}
	
	
	
	else {
			return true;
	}
document.getElementById("alertMsg").focus();
}

</script>


			<c:set var="showCheckBoxInCombinationLeave" value=""/>


