<%
try {
	

%>
<script type="text/javascript">
//window.focus();
//window.moveTo(250,250);
//window.resizeTo(500,300);
</script>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileNotings" value="${resValue.fileNotings}"></c:set>
<c:set var="fName" value="${resValue.fName}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="TappalSide" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >


<table>
	<tr><td><h1>Emp Name</h1></td><td>
		<h1><c:out value="${fName}"></c:out></h1>
	</td></tr>
	<tr><td><h1>Noting</h1></td><td>
	<h1><c:out value="${fileNotings.htmlNoteContent}"></c:out></h1>
	</td></tr>
</table>
<center><hdiits:button name="Close" caption="CLOSE"  type="button" onclick="javascript:self.close();parent.focus();" />

</hdiits:form>


<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>