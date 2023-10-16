<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script>
function showExpDate()
{
	document.getElementById('expdateid').style.display=''
}
function hideExpDate()
{
	document.getElementById('expdateid').style.display="none"
}
function NewCommunique()
{
	
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?viewName=wf-newcommunique','',urlStyle);
}
</script>
<hdiits:form name="communiquedetailfrm" method="post" validate="true" encType="multipart/form-data">

<br>

<hdiits:button name="sendbtn" type="button" value="New" onclick="NewCommunique()"/>

</hdiits:form>



<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>