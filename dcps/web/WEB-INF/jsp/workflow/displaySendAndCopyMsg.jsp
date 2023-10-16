<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="corrNo" value="${resValue.corrNo}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>


<hdiits:form name="sendandcopymsgfrm" method="post" validate="true">

<BR><BR><BR>
			<center>
			
			<h3>
			<c:if test="${langId eq '1'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo}
			</c:if>
			<c:if test="${langId eq '2'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo} <fmt:message key="WF.CorrCrtMsg2"  bundle="${fmsLables}"/>
			</c:if>
			</h3>
			<br>
			<h3>
			<c:if test="${langId eq '1'}">
					<fmt:message key="WF.ForwardMsg"  bundle="${fmsLables}"/> <c:out value="${resValue.empName}"/>
				</c:if>
				<c:if test="${langId eq '2'}">
					<fmt:message key="WF.CorrFwdMsg1"  bundle="${fmsLables}"/> <c:out value="${resValue.empName}"/> <fmt:message key="WF.CorrFwdMsg2"  bundle="${fmsLables}"/>
				</c:if>
			</h3>	
			</center>
			
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/></center>

</hdiits:form>

<script>
function WorkflowAction()
{

	window.document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=viewCorrespondence";
	window.document.forms[0].method = "post";
	window.document.forms[0].submit();
}
	

</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>