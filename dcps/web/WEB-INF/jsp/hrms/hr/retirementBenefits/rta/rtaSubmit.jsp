<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RtaSubmitReq.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="reimbReqList" value="${resValue.rtaReimbReqList}" />
<c:set var="corrNoList" value="${resValue.corrNoList}" />
<script>
var alertRtaReimb=new Array();
alertRtaReimb[0]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>';
alertRtaReimb[1]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>';
alertRtaReimb[2]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>';
alertRtaReimb[3]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>';
alertRtaReimb[4]='<fmt:message  bundle="${commonLables}" key="HRMS.reimbReqId"/>';
alertRtaReimb[5]='<fmt:message  bundle="${commonLables}" key="HRMS.confirmSub"/>';
</script>

<hdiits:form name="rtaSubmit" validate="true" action="./hrms.htm?actionFlag=submitRta" method="post" encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<br>
<hdiits:hidden name="reimbReqId" id="reimbReqId" default="0" />
<%@ include file="rtaAdvDtl.jsp"%>
<br>
<%@ include file="rtaReimbursement.jsp"%>
<br>
<%@ include file="rtaTransportDetails.jsp"%>
		<hdiits:hidden name="userId" id="userId" default="${userId}"/>
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" titleCaptionId="HRMS.reimbAmtDtl" id="reimb">
<table width="100%">
			<tr align="center">
						<td align="right" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.grandTotal" /></b></td>
						<td width="10%" align="left"><hdiits:number tabindex="26" name="grandTotal" style="background-color: lightblue;" id="grandTotal" mandatory="true" readonly="true"
						default="0.0" floatAllowed="true" /></td>
						
			</tr>
			
			<tr align="center" id="dueAmtHeading" style="display:none" >
					
			</tr>
			<tr align="center" id="dueAmtTr" style="display:none" >
						<td align="right" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.amtDue" /></b></td>
						<td width="10%" align="left"><hdiits:number name="dueAmt" tabindex="27" style="background-color: lightblue;" id="dueAmt" mandatory="true" 
						 readonly="true" default="0.0" floatAllowed="true" /></td>
						
			</tr>
			<tr align="center" >
			</tr>

			
			<c:if test="${empty advDtlObj}">
				<script>
					document.getElementById('dueAmtHeading').style.display="none";
					document.getElementById('dueAmtTr').style.display='none';
				</script>
			</c:if>
			<c:if test="${not empty advDtlObj.sanctionedAmt ||   advDtlObj.status=='1'}">
				<script>
					document.getElementById('dueAmtTr').style.display='';
    				document.getElementById('dueAmtHeading').style.display='';
				</script>
			</c:if>
			<tr align="center">
						<td align="right" width="10%"><b><hdiits:caption bundle="${commonLables}" captionid="HRMS.sanctionAmt" /></b></td>
						<td width="10%" align="left"><hdiits:number name="sanctionedAmt" tabindex="28" id="sanctionedAmt" mandatory="true" 
						default="0.0" floatAllowed="true" onblur="calDueAmt();" /></td>
						
			</tr>
		
		
			
		</table>
</hdiits:fieldGroup>
<table class="tabtable" width="100%">
<tr>	
					<td align="center" id="btn" colspan="8">
						<hdiits:hidden name="isAdvReimb" id="isAdvReimb" default="${advDtlObj.isAdvReimb}" />
						<hdiits:button id="subButton" type="button" tabindex="29" name="Submit" captionid="HRMS.submit" 
			        bundle="${commonLables}" onclick="document.rtaSubmit.isAdvReimb.value=2;SubmitRequest();" readonly="true"/>
						<hdiits:button type="button" name="back"  value="Back" captionid="HRMS.back" tabindex="30" bundle="${commonLables}" onclick="goBackToRtaReimb();"/>
						<hdiits:button type="button" id="Close" tabindex="31" name="Close" captionid="HRMS.close" bundle="${commonLables}" onclick="goToHomePage();" />
					<c:if test="${not empty advDtlObj}">
						<hdiits:hidden name="advTaken" id="advTaken" default="${advDtlObj.totalPayableAmt}" />
					</c:if>
					<c:if test="${empty advDtlObj.totalPayableAmt}">
					<hdiits:hidden name="advTaken" id="advTaken" default="0.0" />
					</c:if>
					</td>
			</tr>
</table>		
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<script>
document.getElementById("subButton").disabled=true;
document.getElementById("UpdateT").disabled=true;
document.getElementById("Update").disabled=true;
</script>
<%
}
   	catch(Exception e)
   	{
		e.printStackTrace();
	}

%>