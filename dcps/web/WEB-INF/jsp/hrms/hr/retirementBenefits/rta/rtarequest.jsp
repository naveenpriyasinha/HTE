<%
try {
%>

<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="RtaCaption" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RetirementBenefitsCommon.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/retirementbenefits/RtaAdvanceReq.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="totalPayableAmt" value="${resValue.totalPayableAmt}"></c:set>

<script>
var subDiffFlag;
var alertRtaReq=new Array();
alertRtaReq[0]='<fmt:message  bundle="${RtaCaption}" key="HRMS.remarks"/>';
alertRtaReq[1]='<fmt:message   key="HRMS.SubmitConfirmation"/>';

function validateForm()
{
	if(document.getElementById('rtaOptSel').checked==true)
	{
		if(document.getElementById('remarks').value==''){
			alert('<fmt:message bundle="${RtaCaption}" key="HRMS.remarks"/>');
			}
		else{
				if(confirm('<fmt:message key="HRMS.SubmitConfirmation"/>'))
					document.rtaApplySubmit.submit();
			}
	}
	else
	{
				if(confirm('<fmt:message key="HRMS.SubmitConfirmation"/>'))
					document.rtaApplySubmit.submit();
	}
}

function showInitiateBtn(optSel)
{
	if(optSel.value == 1)
	{
		subDiffFlag=1;
		document.getElementById('rtaAdvance').style.display='';
	}
	else
	{
		subDiffFlag=2;
		document.getElementById('rtaAdvance').style.display='none';
	}
	document.getElementById('Submit').disabled=false;
}

</script>

<hdiits:form name="rtaApplySubmit" validate="true" action="./hrms.htm?actionFlag=submitRtaApply" method="post" encType="multipart/form-data">

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup bundle="${RtaCaption}"  expandable="true" titleCaptionId="HRMS.rtaOptions" id="rtaAdvanceGrp"> 
 <table class="tabtable">
	<tr>
		<td align="right" width="50%">
			<hdiits:radio name="rtaOptSel" id="rtaOptSel" validation="sel.isradio" tabindex="1" default="1" value="1" onclick="showInitiateBtn(this);" captionid="HRMS.rtaAdvance" bundle="${RtaCaption}" />
		</td>
		<td align="left" width="50%">
			<hdiits:radio name="rtaOptSel" id="rtaOptSel" validation="sel.isradio" tabindex="2"   value="2" onclick="showInitiateBtn(this);" captionid="HRMS.rtaReimbursement" bundle="${RtaCaption}" />
		</td>
	</tr>
 </table>
 
 <table class="tabtable" id="rtaAdvance" >
	 <tr>
 		<td>
 			<b><hdiits:caption captionid="HRMS.totalpayablerta" bundle="${RtaCaption}" id="totalpayablerta"  />:</b>
 		</td>
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" tabindex="1"  style="background-color: lightblue;" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${totalPayableAmt}"  />
   		</td>  
   		<td>
   			<b><hdiits:caption bundle="${RtaCaption}" captionid="HRMS.remarks" /></b>
   		</td>
		<td>
			<hdiits:hidden name="status" id="status" default="0"/>
			<hdiits:textarea mandatory="true" rows="2" cols="17" name="remarks" id="remarks" validation="txt.isrequired" tabindex="2"  captionid="HRMS.remarks" bundle="${RtaCaption}" maxlength="2000" />
		</td>
	</tr>
  </table>

</hdiits:fieldGroup>
  <table class="tabtable">	
	<tr>
		<td colspan ="4" align= "center">	
			<hdiits:button name="Submit" id="Submit" type="button" tabindex="3" captionid="HRMS.submit" 
		        bundle="${RtaCaption}" onclick="validateForm();"/>
	       	<hdiits:button type="button"   captionid="HRMS.close" tabindex="4" bundle="${RtaCaption}"  name="CloseRta" onclick="goToHomePage();" />
		</td>
	 </tr>
 </table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 </hdiits:form>

 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 