<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript">
if('${fromAction}'=='yes')
{
	window.moveTo(250,250);
	window.resizeTo(500,300);
}	
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fromAction" value="${resValue.fromAction}"></c:set>
<c:set var="altrntActnId" value="${resValue.altrntActnId}"></c:set>
<c:set var="altrntActnName" value="${resValue.altrntActnName}"></c:set>




<hdiits:form name="disAltFlowMsgFrm" method="post" validate="true">

<BR><BR><BR>
	
			
			
			<c:if test="${fromAction eq 'newaction'}">
			<center><h3><fmt:message key="WF.CrtNewAltFlowActionMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			
			
			<c:if test="${fromAction eq 'insertaction'}">
			<center><h3><fmt:message key="WF.CrtNewAltFlowDetailMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
		
			
			<c:if test="${fromAction eq 'updateaction'}">
			<center><h3><fmt:message key="WF.UpdateAltFlowDetailMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			<c:if test="${fromAction eq 'deleteaction'}">
			<center><h3><fmt:message key="WF.DeleteAltFlowDetailMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			
			
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="onlclick_ok()"/></center>

</hdiits:form>

<script>



function onlclick_ok()
{
	 if('${fromAction}'=='newaction')
	 {
		
		 window.opener.add('${altrntActnName}','${altrntActnId}');
		 window.close();
		 
	 }
	 else if('${fromAction}'=='deleteaction')
	 {

			
		 window.opener.document.getElementById('actionFlag').value="fms_viewalternateflowDetails";
		 window.opener.document.forms[0].submit();
		 window.close();
		 
	 }
	 else
	 {
		 
		 window.document.forms[0].action="hdiits.htm?actionFlag=fms_viewalternateflowDetails";
		 window.document.forms[0].submit();
			
	 }
	
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>