<%
try
{
%>
<%@ include file="../core/include.jsp"%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="doctypelst" value="${resultMap.doctypelst}"></c:set>
<script>
function insertNewDocDetail()
{
	var folderId='${resultMap.folderId}';

	var urlStyle = 'toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,width=800,height=500,top=40,left=40';
	window.open("hdiits.htm?actionFlag=FMS_CreateNewRefDocCatTemplate&folderId="+folderId, '', urlStyle);
	//window.document.forms[0].action = "hdiits.htm?actionFlag=FMS_insertNewDocumentDetail&folderId="+folderId;
	//window.document.forms[0].submit();
}
</script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<hdiits:form name="viewDocDtlfrm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<center><h3><fmt:message key="WF.CrtNewDoc" bundle="${fmsLables}" ></fmt:message></h3></center>
<hdiits:hidden name="folderId" default='${resultMap.folderId}'/>
<table width="100%" border="1" bordercolor="green">
	
	<tr>
		<td style="border: none" width="10%">
			<hdiits:caption captionid="WF.RefDocsDocNo"  bundle="${fmsLables}" />
		</td>
		<td style="border: none" width="10%">
			<hdiits:text name="txtdocno" />
		</td>
		<td style="border: none"  width="10%">
			<hdiits:caption captionid="WF.Keywords"  bundle="${fmsLables}" />
		</td>
		<td style="border: none" width="10%">
			<textarea rows="2" cols="15" name="txtkeyword"></textarea>
		</td>
	</tr>
	<tr>
		<td style="border: none">
			<hdiits:caption captionid="WF.RefDocsDocTitle"  bundle="${fmsLables}" />
		</td>
		<td style="border: none">
			<hdiits:text name="txtdoctitle" />
		</td style="border: none">
		
	</tr>
	<tr>
		<td style="border: none">
			<hdiits:caption captionid="WF.RefDocsDocType"  bundle="${fmsLables}" />
		</td>
		<td style="border: none">
			
			<hdiits:select name="seldoctype" mandatory="true">
				<c:forEach items="${doctypelst}" var="doctypeLookup">
						<option value='<c:out value="${doctypeLookup.lookupId}"/>' >
						<c:out value="${doctypeLookup.lookupDesc}" /></option>
					
				</c:forEach>
			</hdiits:select>
		</td>
		
	</tr>
	<tr>
		
		<td style="border: none" colspan="4">
			<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp">
						<jsp:param name="attachmentName" value="RefdocsDocumentAttachment" />
						<jsp:param name="formName" value="viewDocDtlfrm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Document Attachment" />
			</jsp:include>
		</td>			
	</tr>
</table>
<center>
	<hdiits:button name="crtfolderbtn"  captionid="WF.Create" bundle="${fmsLables}"  type="button"  onclick="insertNewDocDetail()" />
	<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}"  type="button"  onclick="window.close()"/>
</center>
</hdiits:form>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>