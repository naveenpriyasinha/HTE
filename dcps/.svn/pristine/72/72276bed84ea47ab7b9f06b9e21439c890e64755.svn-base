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
<c:set var="HrRtaReqDtl" value="${resValue.hrRtaReqDtl}"></c:set>

<script>





function SubmitForm()
{
	document.rtaApplySubmit.submit();
			
}
function goBack()
	{
		history.go(-1);
	}
</script>



<hdiits:form name="rtaApplyBranch" validate="true" action="./hrms.htm?actionFlag=updateRtaStatus" method="post" encType="multipart/form-data">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>


<table class="tabtable">



	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong><u><fmt:message key="HRMS.rtaoptions"/></u></strong>
			</font>
		</td>
	</tr>
 	<tr>
 		<td>
 		
 			<b><fmt:message key="HRMS.totalpayablerta"/>
 			:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" tabindex="1" style="background-color: lightblue;" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${HrRtaReqDtl.totalPayableAmt}" />
   		</td> 
   		<td>
 			<b><fmt:message key="HRMS.sanctionAmt"/>:</b>
 		</td>
 		
    	<td>
      		<hdiits:number floatAllowed="true" tabindex="2" captionid ="sanctionAmt" readonly="false" name="sanctionAmt"  maxlength="10" id="sanctionAmt" default="${HrRtaReqDtl.sanctionedAmt}"  />
   		</td>  
   	</tr>
   	<tr>
   		<td>
   			<b><fmt:message key="HRMS.remarks"></fmt:message></b>
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
		<!-- 
			<hdiits:button type="button" name="approve" captionid="HRMS.approve" bundle="${commonLables}" onclick="document.rtaApplyBranch.rtaApplyStatus.value=1;SubmitForm();"/>
			<hdiits:button type="button" name="reject" captionid="HRMS.reject" bundle="${commonLables}" onclick="document.rtaApplyBranch.rtaApplyStatus.value=2;SubmitForm();"/>
			<hdiits:button type="button" name="forward" captionid="HRMS.forward" bundle="${commonLables}" onclick="SubmitForm();" />
			<hdiits:button type="button" name="closepage" captionid="HRMS.close" bundle="${commonLables}" onclick="goBack();"/>
 		-->
			<!--<hdiits:hidden  name="rta_Id" caption="rta_Id" default="${HrRtaReqDtl.rtaId}" />-->
			<hdiits:hidden  name="fileId" caption="status" default="${HrRtaReqDtl.rtaId}" />
		</td>
	 </tr>	
 
 </table>
<jsp:include page="../../../core/tabnavigation.jsp"/>   
<hdiits:jsField name="testSantAmt" jsFunction="validateSanctionedAmt()"/>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
 </hdiits:form>
<script>
function validateSanctionedAmt()
{	
	if((document.forms[0].sanctionAmt.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>');
		document.forms[0].sanctionAmt.focus();
		return;
	}
	
	else if(isNaN(document.getElementById('sanctionAmt').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>');
		document.forms[0].sanctionAmt.value='';
		document.forms[0].sanctionAmt.focus();
		return;
	}
	else if(document.getElementById('remarks').value=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.remarks"/>');
		document.forms[0].remarks.focus();
		return;
		
	}
	else
	{
		return true;
	}
}
</script>
 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
