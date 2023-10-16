<%
try{

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page autoFlush="true" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="WfDocList" value="${resultMap.WfDocList}"></c:set>
<c:set var="xslEnable" value="${resultMap.xslEnable}"></c:set>
<c:set var="docTemplateList" value="${resultMap.listFmsDocTemplateMst}"></c:set>
<c:set var="enabledField" value="${resultMap.displayFields}"></c:set>
<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<hdiits:form name="draftuploadForm" validate="true" action="./hdiits.htm?insertAction" encType="multipart/form-data">
<hdiits:hidden name="docId"  default="${resultMap.docId}"/>
<hdiits:hidden name="displayFileds"  default="${resultMap.displayFields}"/>
<hdiits:hidden name="xslen"  default="${resultMap.xslEnable}"/>
<table align="center" >
	<tr>
			<td class="fieldLabel" width="25%">
				Document Name
			</td>
			<td>				
				<hdiits:text name="docName" mandatory="true" default="${resultMap.selectedDocName}" disable="true"/>
				<img src="themes/defaulttheme/images/workflow/hyperlink.gif" onclick="getDocList();">
			</td>
		
			
			
			<td>
				 <td style="border:none"><hdiits:button type="button"  name="addnew" id="addnew" captionid="WF.ADDNEW" bundle="${wfLables}" onclick="getTemplateList();" /></td>
			</td>
			
	</tr>
	<tr>
		<c:if test="${xslEnable eq 1 }">
		<h1> <b>This Doc Is XSL Enabled </b></h1>
		</c:if>
		<c:if test="${xslEnable eq 0 }">
		<h1><b> This Doc Is XSL Disabled</b></h1>
		</c:if>
	</tr>
	<c:if test="${ not empty docTemplateList}">
	<table align="center">
		<tr>
			<td align="center">
			
				<display:table list="${docTemplateList}" pagesize="12" requestURI="" id="row"  style="width:50%" >
					<display:column titleKey="Select" headerClass="datatableheader" media="HTML">
						<input type="radio" name="radio" id="${row.srNo}" value="${row.srNo}">
					</display:column>
					
					
			
					<display:column titleKey="WF.TEMPLATETYPE" headerClass="datatableheader"><c:out value="${row.templateType}"></c:out></display:column>
					
					<display:column titleKey="WF.SERVICENAME" headerClass="datatableheader"><c:out value="${row.serviceName}"></c:out></display:column>
					
					
					
				</display:table>
				
			</td>
		</tr>
	</table>
	</c:if>
	
	<table align="center">
		<tr align="left">
		<c:if test="${xslEnable eq 1 && enabledField eq 'yes'}">
			<td>
				Service Name
			</td>
			<td>
				<hdiits:text name="serviceName" mandatory="true"/>
				
			</td>
		</c:if>
			
		</tr>	
	<c:if test="${enabledField eq 'yes'}">	
		<tr align="center">
			<td>
				Template Type
			</td>
			<td>			
				<hdiits:text name="TemplateTye" mandatory="true"/>
			</td>
			
		</tr>
	</c:if>
	</table>
	
	<tr>
	</tr>
	<tr>
	</tr>
	<c:if test="${enabledField eq 'yes'}">	
	<table align="center">
	
		<tr style="width: 50%" align="center">
		<td align="center">		
				<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" >
				 <jsp:param name="attachmentName" value="DocumentAttachmentDraft" />
		         <jsp:param name="formName" value="draftuploadForm" />
		         <jsp:param name="attachmentType" value="Document" />
		         <jsp:param name="attachmentTitle" value="AttachmentTitle" />  
		        
		    </jsp:include>
		</td>
		</tr>
	</table>
	
	<table align="center">
		<tr>
			
				 <td style="border:none"><hdiits:button type="button"  name="submitButton" id="submitButton" captionid="WF.CREATE" bundle="${wfLables}" onclick="createtemplate();" /></td>
							 
				 <td style="border:none"><hdiits:button type="button"  name="close" id="close" captionid="WF.CLOSE" bundle="${wfLables}" onclick="closewindow();" /></td>
			
		</tr>
	</table>
	</c:if>
</table>
	
</hdiits:form>	
<script type="text/javascript">
function getDocList()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes'; 
	window.open('${contextPath}/hdiits.htm?actionFlag=draftUpload','',urlStyle);
		
}


function closewindow()
{
	var action="${contextPath}/hdiits.htm?actionFlag=getHomePage";
	document.getElementById("draftuploadForm").method="post";
	document.getElementById("draftuploadForm").action=action	
	document.getElementById("draftuploadForm").submit();

}

function createtemplate()
{
	if(document.getElementById('cntDocumentAttachmentDraft').value==0)
	{
		alert("Please specify atleast one draft to create");
		return false;
	}
	
	alert(document.getElementById('xslen').value);
	
	if(document.getElementById('xslen').value==1 && document.getElementById('serviceName').value=='')
	{
		alert("Please specify Service Name");
		return false;
	}
	if(document.getElementById('TemplateTye').value=='')
	{
		alert("Please specify TemplateTye");
		return false;
	}
	if(document.getElementById('docName').value=='')
	{
		alert("Please select  atleast one doc to create  draft");
		return false;
	}
	var action='${contextPath}/hdiits.htm?actionFlag=insertDraftTemplate';
	document.getElementById("draftuploadForm").method="post";
	document.getElementById("draftuploadForm").action=action	
	alert(action);
	document.getElementById("draftuploadForm").submit();
}
function getTemplateList()
{
	if(document.getElementById('docName').value=='')
	{
		alert("Please select  atleast one doc");
		return false;
	}
	
	var action='${contextPath}/hdiits.htm?actionFlag=fetchTemplateList';
	document.getElementById("draftuploadForm").method="post";
	document.getElementById("draftuploadForm").action=action	
	alert(action);
	document.getElementById("draftuploadForm").submit();

}
</script>

<%
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>