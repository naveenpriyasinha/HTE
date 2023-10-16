<%
try {
%>

<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="RtaCaption" scope="request"/>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<script type="text/javascript" 	src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="userId" value="${resValue.userId}"></c:set>

<script>

function showInitiateBtn(optSel)
{
	if(optSel.value == 1)
	{
		document.getElementById('rtaAdvance').style.display='';
		document.getElementById('rtaReimbursement').style.display='none';
	}
	else
	{
		document.getElementById('rtaAdvance').style.display='none';
		document.getElementById('rtaReimbursement').style.display='';
	}
}

function initiateRtaReq(optVal)
{
	if(optVal == 1)
	{
		rtaRequestInitiate.action="./hdiits.htm?actionFlag=showRtaApply&elementId=300802";
		rtaRequestInitiate.submit();
	}
	else
	{
		rtaRequestInitiate.action="./hdiits.htm?actionFlag=sendRtaReimbReqNotification";
		rtaRequestInitiate.submit();
	}
}


</script>



<hdiits:form name="rtaRequestInitiate" validate="true" method="post" encType="multipart/form-data">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:hidden name="userId" id="userId" default="${userId}" />

<table class="tabtable">



	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong><u><b><fmt:message bundle="${RtaCaption}" key="HRMS.rtaOptions"/></b></u></strong>
			</font>
		</td>
	</tr>
 	<tr>
 			
		<td align="right" width="50%">
			<hdiits:radio name="rtaOptSel" id="rtaOptSel" validation="sel.isradio" tabindex="1" value="1" onclick="showInitiateBtn(this);" captionid="HRMS.rtaAdvance" bundle="${RtaCaption}" />
		</td>
		<td align="left" width="50%">
			<hdiits:radio name="rtaOptSel" id="rtaOptSel" validation="sel.isradio" tabindex="2" value="2" onclick="showInitiateBtn(this);" captionid="HRMS.rtaReimbursement" bundle="${RtaCaption}" />
			
		</td>
		
	</tr>
	
	<tr>
		
		<td colspan ="4" align= "center">	
			<hdiits:button name="rtaAdvance" id="rtaAdvance" type="button" tabindex="3" captionid="HRMS.rtaAdvanceInit" bundle="${RtaCaption}"
	         onclick="initiateRtaReq(1);" style="display:none" />
	        <hdiits:button name="rtaReimbursement" id="rtaReimbursement" type="button" tabindex="3" captionid="HRMS.rtaReimbursementInit" bundle="${RtaCaption}"
	         onclick="initiateRtaReq(2);" style="display:none" />
			<hdiits:button type="button"  captionid="HRMS.close" tabindex="4"
	        bundle="${RtaCaption}"  name="Closey" onclick="goBack();" />
		</td>

	 </tr>
		 		  
 </table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
 </hdiits:form>

 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 