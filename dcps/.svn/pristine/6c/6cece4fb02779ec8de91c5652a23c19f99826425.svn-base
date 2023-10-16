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


<hdiits:form name="TappalAttForm" method="POST" validate="true" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="corrId" id="hid_corr_id"/>
<hdiits:hidden name="buttonId" id="hid_butn_id"/>
<hdiits:hidden name="fileId" id="hid_file_id"/>
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		           		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.CorrAttach" bundle="${fmsLables}"/></a></li>
		</ul>
</div>
<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
			Add Attachments from below if needed
			<fmt:message key="WF.CorrAttach" bundle="${fmsLables}" var="attachmentTitle"></fmt:message>
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" >
                <jsp:param name="attachmentName" value="TappalAttachment" />
                <jsp:param name="formName" value="TappalAttForm" />
                <jsp:param name="attachmentType" value="Document" />   
                <jsp:param name="attachmentTitle" value="${attachmentTitle}" />              
		    </jsp:include>
		    <br> 
		   <center><hdiits:button type="button" name="button" captionid="WF.ADDATT" bundle="${fmsLables}" onclick="submitform()"/></center>
		</div>
</div>
<script type="text/javascript"> 
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script> 
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
<script type="text/javascript">
	var corrId = window.opener.document.forms[0].corr_id.value;
	document.getElementById('hid_corr_id').value = corrId;
	var butFlag = window.opener.document.forms[0].butFlag.value;
	document.getElementById('hid_butn_id').value = butFlag
	var fileId = window.opener.document.forms[0].fileId.value;
	document.getElementById('hid_file_id').value = fileId
	
	
	function submitform()
	{
		document.forms[0].method='post';
		document.forms[0].action='hdiits.htm?actionFlag=addAttachinCorr'
		document.forms[0].submit();
	}
	//alert(fileId);
</script>
</hdiits:form>
<%}
catch(Exception e)
{
	e.printStackTrace();
}
%>