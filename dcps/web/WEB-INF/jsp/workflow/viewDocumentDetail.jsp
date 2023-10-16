<%
try
{
%>
<%@ include file="../core/include.jsp"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.fms.referencedocs.service.ComplaintStatusDecorator" %>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="fmsRefdocsDocFolderMpglst" value="${resultMap.fmsRefdocsDocFolderMpglst}"></c:set>
<c:set var="finallst" value="${resultMap.finallst}"></c:set>


<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script>


var folderId='${resultMap.folderId}';

		


function createNewFolder()
{
	window.document.forms[0].action = "hdiits.htm?viewName=wf-crtNewFolder&parent_folder_id="+folderId;
	window.document.forms[0].submit();
}	
function createNewDocument()
{
	window.document.forms[0].action = "hdiits.htm?actionFlag=FMS_crateNewRefDocsDocument&folderId="+folderId;
	window.document.forms[0].submit();
}	
function viewDocumentDtl(docId)
{
		
}
</script>

<hdiits:form name="viewDocDtlfrm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:button name="btnnewfolder" type="button" onclick="createNewFolder()"  captionid="WF.CrtNewFolder" bundle="${fmsLables}"/>
<hdiits:button name="btnnewdocument" type="button" onclick="createNewDocument()" captionid="WF.CrtNewDoc" bundle="${fmsLables}"/>


<display:table list="${finallst}" id="row" style="width:100%" pagesize="10" requestURI=""  >
		
		
		<display:column class="tablecelltext" titleKey="rad"  headerClass="datatableheader" >
		<hdiits:checkbox name="SelectCheck"  value="test" />
		</display:column>

		
	 	<display:column class="tablecelltext" titleKey="WF.RefDocsDocNo"  headerClass="datatableheader"  >${row[0]}</a></display:column>
		<display:column class="tablecelltext" titleKey="WF.RefDocsDocTitle"  headerClass="datatableheader"  >${row[1]}</display:column>
		
		
		<display:column class="tablecelltext" titleKey="WF.Attachment"  headerClass="datatableheader"  >${row[2]}</display:column>
		
		<display:column class="tablecelltext" titleKey="WF.Keywords"   headerClass="datatableheader"  >${row[3]}</display:column>
		<display:column class="tablecelltext" titleKey="WF.RefDocsDocType"  headerClass="datatableheader"  >${row[4]}</display:column>
		
		
		
		
	</display:table>
</hdiits:form>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>