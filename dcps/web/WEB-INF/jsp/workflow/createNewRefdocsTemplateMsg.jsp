<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="locId" value="${resValue.locId}"></c:set>
<c:set var="branchId" value="${resValue.branchId}"></c:set>
<c:set var="deptId" value="${resValue.deptId}"></c:set>
<c:set var="ctgryId" value="${resValue.ctgryId}"></c:set>
<c:set var="createNewDoc" value="${resValue.createNewDoc}"></c:set>


<hdiits:form name="newcommsendasdraftfrm" method="post" validate="true">

<BR><BR><BR>
	
			<c:if test="${createNewDoc eq 'Yes'}">
					
			<center><h3><fmt:message key="WF.RefDocCrtDocMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
			<c:if test="${createNewDoc ne 'Yes'}">
			<center><h3><fmt:message key="WF.RefDocCrtVerMsg" bundle="${fmsLables}"></fmt:message></h3></center>
			</c:if>
	
<br><center><hdiits:button name="ActionButton" type="button" value="Ok"captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/></center>

</hdiits:form>

<script>
function WorkflowAction()
{

	var deptId='${resValue.deptId}';
	var locId='${resValue.locId}';
	var branchId='${resValue.branchId}';
	var url="hdiits.htm?actionFlag=fms_displayRefDocs&fromdraftFlag=N";


	
	if(deptId!='')
	{
		url=url+"&departmentId="+deptId;
	}
	if(locId!='')
	{
		url=url+"&locCode="+locId;
	}
	if(branchId!='')
	{
		url=url+"&branch="+branchId;
	}

	url=url+'&CategoryTemplateMpgCode='+'${ctgryId}';

	alert(url);
	window.document.forms[0].action =url
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