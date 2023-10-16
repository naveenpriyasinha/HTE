<% try
{
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<br><br><br><br><br><br><br><br><br>


<hdiits:form name="SuccessCorrAttachForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<hdiits:hidden name="corrId" default="${resValue.corrId}"/>
<hdiits:hidden name="buttonId" default="${resValue.buttonId}" />
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
	<center><h3><fmt:message key="WF.ATTADDMSG" bundle="${fmsLables}"/></h3>
	<hdiits:button name="button" type="button" onclick="displayAttachmentsInCorr()" value="OK"/></center>
</hdiits:form>


<script>
function displayAttachmentsInCorr()
{
		
	var corrId = document.getElementById('corrId').value;
	//window.opener.parent.frames['Target_frame'].src = "hdiits.htm?actionFlag=addAttachinCorr";
	var buttonId = document.getElementById('buttonId').value;
	var fileId = document.getElementById('fileId').value;
	//alert(window.opener.parent.frames['Target_frame']);
	
	//BasicInfoFrameID
	//window.opener.parent.frames['Target_frame'].src = "http://www.google.co.in"
	
	//alert(' parent iframe   docu form= '+window.opener.parent.frames["Target_frame"].document.forms[0]);
	 
	 
	//window.opener.parent.frames["Target_frame"].document.forms[0].action = "hdiits.htm?actionFlag=addAttachinCorr";
	window.opener.parent.frames["Target_frame"].document.forms[0].action = "hdiits.htm?actionFlag=showCorrAttach&corrId="+corrId+"&file="+fileId;
	window.opener.parent.frames["Target_frame"].document.forms[0].method = "post";
	window.opener.parent.frames["Target_frame"].document.forms[0].submit();
	window.close();
}
</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>