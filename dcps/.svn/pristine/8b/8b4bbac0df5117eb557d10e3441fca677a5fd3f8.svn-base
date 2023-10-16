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
<c:set var="pkval" value="${resValue.pkval}"></c:set>


<script>
		
window.opener.document.forms[0].pkval.value='${resValue.pkval}';	
window.opener.document.forms[0].draftRefDocFlag.value=1;
window.opener.approveDraftAction();
window.close();
</script>


<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>