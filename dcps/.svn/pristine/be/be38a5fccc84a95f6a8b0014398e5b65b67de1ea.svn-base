<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="HrRtaReqDtl" value="${resValue.hrRtaReqDtl}"></c:set>
<c:set var="reportView" value="${resValue.reportView}" />

<hdiits:form name="viewRtaAdvDtl" validate="true" method="post" encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup bundle="${commonLables}"  id="viewAdvRtaDetails" expandable="false" titleCaptionId="HRMS.rtaAdvReqDtl">
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
      		<hdiits:number floatAllowed="true" tabindex="2" captionid ="sanctionAmt" readonly="true" name="sanctionAmt"  maxlength="10" id="sanctionAmt" default="${HrRtaReqDtl.sanctionedAmt}"  />
   		</td>  
   	</tr>
   	<tr>
   		
		<td>
 			<b><hdiits:caption bundle="${commonLables}" captionid="HRMS.remarks" /></b>
 		</td>
		<td>
			<hdiits:hidden name="status" id="status" default="${HrRtaReqDtl.status}"/>
			<hdiits:textarea readonly="true" rows="2" cols="17" default="${HrRtaReqDtl.remarks}" name="remarks" id="remarks" tabindex="3"  caption="remarks" maxlength="2000" />
		</td>
		
	</tr>
	
 		<tr>
 		<td colspan ="4" align= "center">
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
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 </hdiits:form>
<script>

</script>
 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
