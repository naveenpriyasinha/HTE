<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>


<fmt:setBundle basename="resources.common.cancellation.CommonCancellation"
	var="msgBundle" scope="request" />
	<script type="text/javascript">
	function Closebt()
{	
	method="POST";
	document.CancelView.action="./hrms.htm?viewName=CancelPage";
	
	document.CancelView.submit();
}
</script>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<hdiits:form name="CancelView" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >
<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">

	
	<tr>
		<td colspan="2">
		<b><font color="red">*</font></b>
		<b>${resultValue.reqtype}<fmt:message key="CAN.DENIED" bundle="${msgBundle}"/> </b>
	<b> (<fmt:message key="CAN.REQUESTNO" bundle="${msgBundle}"/> : ${resultValue.jobId})</b>
	
		
		
		</td>
		
	</tr>
		<tr>
		<td >
		<b><font color="red">*</font></b>
		<b><fmt:message key="CAN.REASON" bundle="${msgBundle}"/> : ${resultValue.reasonCancellation}</b>
		
		
		
		</tr>
		

</table>

<center>
<hdiits:button name="close" type="button" captionid="Can.Close" bundle="${msgBundle}" onclick="Closebt()"/>
</center>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>




