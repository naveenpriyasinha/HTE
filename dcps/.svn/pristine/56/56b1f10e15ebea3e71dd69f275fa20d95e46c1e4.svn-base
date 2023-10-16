<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ page autoFlush="true" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileDetails" value="${resValue.listFmsPrecedenceVo}"></c:set>
<c:set var="fileDetails" value="${resValue.listFmsPrecedenceVo}"></c:set>
<c:set var="outBoxFlag" value="${resValue.outBoxFlag}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />

<hdiits:form name="HierarchyForm" method="POST" validate="true" action="./hdiits.htm">
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
	
	<display:table list="${fileDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
		
		<display:column  titleKey="WF.FileNo" headerClass="datatableheader"><a href="javascript:openDocument('${row.fileUrl}');">${row.fileNo}</a></display:column>
		
		<display:column titleKey="WF.ApproveStatus" headerClass="datatableheader">${row.approveStstus}</display:column>
	
		<display:column titleKey="WF.Description" headerClass="datatableheader">${row.description}</display:column>
	
	</display:table>
	
	
	<c:if test="${not outBoxFlag}">
	<center><td><hdiits:button type="button"  name="Addfile" id="Addfile" captionid="WF.Add" bundle="${wfLables}" onclick="Add()" /></td></center>
	</c:if>
</hdiits:form>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>

<script>
	function Add()
	{
		
					var action="${contextPath}/hdiits.htm?actionFlag=getFilesForprecendenceCases&fileId=${resValue.fileId}";
					var urlStyle ='width=600,height=650,toolbar=no,menubar=no,location=no,top=150,left=200,scrollbars=yes'; 
					window.open(action,'test',urlStyle);
			
	}
	
	function openDocument(url)	
	{			
			window.open(url);
	}
</script>
