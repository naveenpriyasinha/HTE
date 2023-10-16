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

<script>
var alertRtaAdvInbox=new Array();
alertRtaAdvInbox[0]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>';
alertRtaAdvInbox[1]='<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>';
alertRtaAdvInbox[2]='<fmt:message  bundle="${commonLables}" key="HRMS.remarks"/>';
</script>

<hdiits:form name="rtaApplyBranch" validate="true" action="./hrms.htm?actionFlag=updateRtaStatus" method="post" encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" id="ratAdvFile" titleCaptionId="HRMS.rtaoptions" collapseOnLoad="false">
<table class="tabtable">
 	<tr>
 		<td>
 		
 			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.totalpayablerta" />
 			:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" tabindex="1" style="background-color: lightblue;" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${HrRtaReqDtl.totalPayableAmt}" />
   		</td> 
   		<td>
 			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.sanctionAmt" />:</b>
 		</td>
 		
    	<td>
      		<hdiits:number floatAllowed="true" tabindex="2" captionid ="sanctionAmt" readonly="false" name="sanctionAmt"  maxlength="10" id="sanctionAmt" default="${HrRtaReqDtl.sanctionedAmt}"  />
   		</td>  
   	</tr>
   	<tr>
   		<td>
   			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b>
   		</td>
		
		<td>
			<hdiits:hidden name="isAdvReimb" id="isAdvReimb" default="${HrRtaReqDtl.isAdvReimb}" />
			<hdiits:hidden name="status" id="status" default="${HrRtaReqDtl.status}"/>
			<hdiits:textarea readonly="false" mandatory="true" rows="2" cols="17" default="${HrRtaReqDtl.remarks}" name="remarks" id="remarks" tabindex="3"  caption="remarks" maxlength="2000" />
		</td>
		
	</tr>
	
	
 
 
 		<tr>
 		<td colspan ="4" align= "center">
			
			<hdiits:hidden  name="rtaApplyStatus" caption="status" default="0" />
		
			<hdiits:hidden  name="fileId" caption="rtaId" default="${HrRtaReqDtl.rtaId}" />
		</td>
	 </tr>	
 
 </table>
 </hdiits:fieldGroup>
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
