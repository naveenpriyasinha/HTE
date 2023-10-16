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
<c:set var="totalPayableAmt" value="${resValue.totalPayableAmt}"></c:set>

<script>

function SubmitRequest()
{
	if(document.getElementById('remarks').value=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.remarks"/>');
		document.forms[0].remarks.focus();
		return;
		
	}
	else
	{
		document.forms[0].Submit.disabled=true;
		document.rtaApplySubmit.submit();
	}
}


function goBack()
	{
		document.rtaApplySubmit.action="./hdiits.htm?actionFlag=getHomePage";
		document.rtaApplySubmit.submit();
	}
</script>



<hdiits:form name="rtaApplySubmit" validate="true" action="./hrms.htm?actionFlag=submitRtaApply" method="post" encType="multipart/form-data">


<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>


<table class="tabtable">



	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong><u><fmt:message key="HRMS.rtaDtl"/></u></strong>
			</font>
		</td>
	</tr>
 	<tr>
 		<td>
 			<b><hdiits:caption captionid="HRMS.totalpayablerta" bundle="${commonLables}" id="totalpayablerta"  />:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" tabindex="1"  style="background-color: lightblue;" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${totalPayableAmt}"  />
   		</td>  
   
   		<td>
   			<b><fmt:message key="HRMS.remarks"></fmt:message></b>
   		</td>
		
		<td>
			<hdiits:hidden name="status" id="status" default="0"/>
			<hdiits:textarea mandatory="true" rows="2" cols="17" name="remarks" id="remarks" tabindex="2"  caption="remarks" maxlength="2000" />
		</td>
	
	</tr>
	
	
 
 
 		<tr>
 			<td colspan ="4" align= "center">	
 					<hdiits:button name="Submit" type="button" tabindex="3" captionid="HRMS.submit" 
			        bundle="${commonLables}" onclick="SubmitRequest();" />
					<hdiits:button type="button"   captionid="HRMS.close" tabindex="4"
			        bundle="${commonLables}"  name="Closey" onclick="goBack();" />
			</td>

		 </tr>
 
 </table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
 </hdiits:form>

 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 