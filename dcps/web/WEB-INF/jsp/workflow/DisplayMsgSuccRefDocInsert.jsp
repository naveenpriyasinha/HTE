<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript">
window.moveTo(250,250);
window.resizeTo(500,300);
</script>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="categoryTemplateMpgCode" value="${resultMap.categoryTemplateMpgCode}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />




<hdiits:form name="DisplayMsgSuccRefDocs"  method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="fromDraftFlag" default="${resultMap.fromDraftsFlag}"/>

<table align="center">

<tr >
<td >
<h3 align="right">
	<fmt:message key="WF.RefDocSaveMsg" bundle="${fmsLables}"></fmt:message>
	
</h3>
</td>
</tr>



</table>
<br><br><br>
<center>
<hdiits:button name="bt_save" type="button" captionid="WF.Ok" bundle="${fmsLables}" onclick="viewRefDocAttrDtls('${categoryTemplateMpgCode}')"/>
<c:if test="${resultMap.fromDraftsFlag eq 'Y'}">
&nbsp;&nbsp;&nbsp;<hdiits:button name="bt_Approve" type="button" caption="Approve Draft" bundle="${fmsLables}" onclick="approveDraft()"/>
</c:if>
</center>



</hdiits:form>

<script type="text/javascript">
	function viewRefDocAttrDtls(categoryTemplateMpgCode)
	{
		var fromDraftFlag = document.getElementById('fromDraftFlag').value;


		
		if(fromDraftFlag!='Y'){
			/*document.forms[0].action='${contextPath}/hdiits.htm?actionFlag=fms_displayRefDocs&CategoryTemplateMpgCode='+categoryTemplateMpgCode;
			document.forms[0].method='post';
			document.forms[0].submit();*/
			//alert(window.opener);
			//alert(window.opener.parent);
			//alert(window.opener.parent.window.document);
			window.opener.parent.location.href="hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=RefDocs&moduleName=RefDocs&menuName=forRefDocs&fromHomePageOfRefDocs=true"

			//window.opener.parent.window.document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=showaddedCorrespondence&corrCriteria=Incoming&fileId="+fileId;
		//	window.opener.parent.window.document.forms[0].method = "post";
		//	window.opener.parent.window.document.forms[0].submit();
			window.close();
			}
			else{
				
				window.close();
			}
	}

	function approveDraft()
	{
		var fromDraftFlag = document.getElementById('fromDraftFlag').value;
		if(fromDraftFlag=='Y'){
			window.parent.opener.window.document.getElementById('draftRefDocFlag').value=1;
			window.parent.opener.approveDraftAction();
			window.close();
		}
	}
</script>