<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RtaAdvanceReq.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="HrRtaReqDtl" value="${resValue.hrRtaReqDtl}"></c:set>
<c:set var="reportView" value="${resValue.reportView}" />

<script>
var alertRtaAdvInbox=new Array();
alertRtaAdvInbox[0]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>';
alertRtaAdvInbox[1]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>';
alertRtaAdvInbox[2]='<fmt:message  bundle="${commonLables}" key="HRMS.remarks"/>';
</script>

<hdiits:form name="rtaApplyBranch" validate="true" action="./hrms.htm?actionFlag=updateRtaStatus" method="post" encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="reimbCorr" titleCaptionId="HRMS.rtaoptions" collapseOnLoad="false">
<table class="tabtable">
	<tr>
   		<td width="20%">
   			<hdiits:caption bundle="${commonLables}" captionid="HRMS.Notification" />
   		</td>
   		 <td align="left" width="80%">
   			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.YES" /></b>
   		</td>
   	</tr>
   	<tr>
   		<td>
   			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b>
   		</td>
		
		<td>
		<hdiits:hidden name="isAdvReimb" id="isAdvReimb" default="${HrRtaReqDtl.isAdvReimb}" />
		<c:if test="${reportView}">
			<hdiits:textarea readonly="true" mandatory="false" rows="2" cols="17" default="${HrRtaReqDtl.remarks}" name="remarks" id="remarks" tabindex="3"  caption="remarks" maxlength="2000" />
		</c:if>
		<c:if test="${reportView eq false}">
			<hdiits:textarea mandatory="true" rows="2" cols="17" default="${HrRtaReqDtl.remarks}" name="remarks" id="remarks" tabindex="3"  caption="remarks" maxlength="2000" />
		</c:if>
		</td>
		
	</tr>
   </table>
    <table>
 		<tr>
 		<td colspan ="4" align= "center">
			
		<hdiits:hidden name="rtaApplyStatus" caption="status" default="0" />
		<hdiits:hidden  caption="sanctionAmt" name="sanctionAmt" default="${HrRtaReqDtl.sanctionedAmt}"  />
		<hdiits:hidden  name="fileId" caption="status" default="${HrRtaReqDtl.rtaId}" />
		</td>
	 </tr>	
 </table>
   
</hdiits:fieldGroup>
<table class="tabtable" width="100%">
	<c:if test="${reportView}">
			<tr align="center">
			<td>
				<hdiits:button type="button" id="Close"
					name="Close" captionid="HRMS.close" bundle="${commonLables}"
					onclick="window.close()" />
			</td>
			</tr>
			
		</c:if>
</table>


<hdiits:jsField name="testSantAmt" jsFunction="validateSanctionedAmt()"/>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 </hdiits:form>
<script>

</script>
 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
