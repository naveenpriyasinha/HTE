<%
try {
%>

<%@ include file="/WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="userId" value="${resValue.userId}"></c:set>

<script>



function SubmitRequest(){
		
	
	if((document.forms[0].sanctionedAmt.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>');
		document.forms[0].sanctionedAmt.focus();
		return;
	}
	else if(isNaN(document.getElementById('sanctionedAmt').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>');
		document.forms[0].sanctionedAmt.value='';
		document.forms[0].sanctionedAmt.focus();
		return;
	}
	else
	{
	document.forms[0].Submit.disabled=true;
		document.rtaSubmit.submit();		
	}
}
function goBack()
	{
		history.go(-1);
	}
	
function calDueAmt()
{	
	if((document.forms[0].sanctionedAmt.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>');
		return;
	}
	else if(isNaN(document.getElementById('sanctionedAmt').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>');
		document.forms[0].sanctionedAmt.value='';
		document.forms[0].sanctionedAmt.focus();
		return;
	}
	else
	{
		var sancAmt=document.forms[0].sanctionedAmt.value;
		var dueAmt=document.forms[0].dueAmt.value;
		var grandTotal=document.forms[0].grandTotal.value;
		dueAmt=eval(eval(grandTotal)-eval(sancAmt));
		document.forms[0].dueAmt.value=dueAmt;
	}
}
</script>



<hdiits:form name="rtaSubmit" validate="true" action="./hrms.htm?actionFlag=submitRta" method="post" encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>
<%@ include file="/WEB-INF/jsp/hrms/hr/rta/rtaAdvDtl.jsp"%>
<br>
<%@ include file="/WEB-INF/jsp/hrms/hr/rta/rtaReimbursement.jsp"%>
<br>
<%@ include file="/WEB-INF/jsp/hrms/hr/rta/rtaTransportDetails.jsp"%>
		<hdiits:hidden name="userId" id="userId" default="${userId}"/>
		<table width="100%">
			<tr align="center" >
						<td align="center" colspan="4" bgcolor="#386CB7">
						<strong><u><font color="white"><fmt:message key="HRMS.grandTotal"/></font></u></strong>
						</td>
			</tr>
			<tr align="center">
						<td align="right" width="10%"><b><fmt:message
						key="HRMS.grandTotal" /></b></td>
						<td width="10%" align="left"><hdiits:number tabindex="26" name="grandTotal" style="background-color: lightblue;" id="grandTotal" mandatory="true" readonly="true"
						default="0.0" floatAllowed="true" /></td>
						
			</tr>
			
			<tr align="center" id="dueAmtHeading" style="display:none" >
						<td align="center" colspan="4" bgcolor="#386CB7">
						<strong><u><font color="white"><fmt:message key="HRMS.amtDue"/></font></u></strong>
						</td>
			</tr>
			<tr align="center" id="dueAmtTr" style="display:none" >
						<td align="right" width="10%"><b><fmt:message
						key="HRMS.amtDue" /></b></td>
						<td width="10%" align="left"><hdiits:number name="dueAmt" tabindex="27" style="background-color: lightblue;" id="dueAmt" mandatory="true" 
						 readonly="true" default="0.0" floatAllowed="true" /></td>
						
			</tr>
			<tr align="center" >
						<td align="center" colspan="4" bgcolor="#386CB7">
						<strong><u><font color="white"><fmt:message key="HRMS.sanctionAmt"/></font></u></strong>
						</td>
			</tr>

			
			<c:if test="${empty advDtlObj}">
			<c:set var="sanctAmt" value="0.0" ></c:set>
			<script>
					document.getElementById('dueAmtHeading').style.display="none";
					document.getElementById('dueAmtTr').style.display='none';
				</script>
			</c:if>
			<c:if test="${not empty advDtlObj.sanctionedAmt ||   advDtlObj.status=='1'}">
			<c:set var="sanctAmt" value="${advDtlObj.sanctionedAmt}" ></c:set>
				<script>
					document.getElementById('dueAmtTr').style.display='';
    				document.getElementById('dueAmtHeading').style.display='';
				</script>
			
			</c:if>
			<tr align="center">
						<td align="right" width="10%"><b><fmt:message
						key="HRMS.sanctionAmt" /></b></td>
						<td width="10%" align="left"><hdiits:number name="sanctionedAmt" tabindex="28" id="sanctionedAmt" mandatory="true" 
						default="${sanctAmt}" floatAllowed="true" onblur="calDueAmt();" /></td>
						
			</tr>
		
		
			<tr>	
					<td align="center" id="btn" colspan="8">
						<hdiits:hidden name="isAdvReimb" id="isAdvReimb" default="${advDtlObj.isAdvReimb}" />
						<hdiits:button id="subButton" type="button" tabindex="29" name="Submit" captionid="HRMS.submit" 
			        bundle="${commonLables}" onclick="document.rtaSubmit.isAdvReimb.value=2;SubmitRequest();" readonly="true"/>
						<hdiits:button type="button" id="Close" tabindex="30" name="Close" captionid="HRMS.close" bundle="${commonLables}" onclick="goBack();" />
					<c:if test="${not empty advDtlObj}">
						<hdiits:hidden name="advTaken" id="advTaken" default="${advDtlObj.totalPayableAmt}" />
					</c:if>
					<c:if test="${empty advDtlObj.totalPayableAmt}">
					<hdiits:hidden name="advTaken" id="advTaken" default="0.0" />
					</c:if>
					</td>
			</tr>
		</table>
 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>

<%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>
<script>
document.getElementById("subButton").disabled=true;
document.getElementById("UpdateT").disabled=true;
document.getElementById("Update").disabled=true;
</script>