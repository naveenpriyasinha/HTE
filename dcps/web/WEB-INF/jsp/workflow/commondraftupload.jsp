<%
try{

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page autoFlush="true" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>

<c:set var="docTemplateList" value="${resultMap.listFmsDocTemplateMst}"></c:set>

<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<hdiits:form name="commondraftuploadForm" validate="true" action="./hdiits.htm?insertAction" encType="multipart/form-data">
<hdiits:hidden name="docId"  default="${resultMap.docId}"/>
<hdiits:hidden name="xslEnabled" default="1"/>
<table align="center" >

	<tr>					
			<td>
				<hdiits:radio name="radio" id="xslenabled" value="xslenabled" captionid="WF.XSLENABLE" bundle="${wfLables}" default="xslenabled" onclick="enabled(this)" />
			</td>					
			<td style="border:none" width="15%">	
				<hdiits:radio name="radio" id="xsldisabled" value="xsldisabled" captionid="WF.SIMPLEUPLOAD" bundle="${wfLables}" onclick="enabled123(this)" />
			</td>					
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
			
		<tr align="center" id="serName">
		
			<td>
				<hdiits:caption  captionid="WF.SERVICENAME" bundle="${wfLables}"/>
			</td>
			<td>
				<hdiits:text name="serviceName" mandatory="true"/>
				
			</td>
		
			
		</tr>	
	
		<tr align="center">
			<td>
				<hdiits:caption  captionid="WF.TEMPLATETYPE" bundle="${wfLables}"/>
			</td>
			<td>			
				<hdiits:text name="TemplateTye" mandatory="true"/>
			</td>
			
		</tr>
		<tr align="center">
			<td>
				<hdiits:caption  captionid="WF.ORDERNO" bundle="${wfLables}"/>
			</td>
			<td>			
				<hdiits:text name="orderno" mandatory="true" onchange="checkNumber()"/>
			</td>
			
		</tr>
	
	</table>
	
	<tr>
	</tr>
	<tr>
	</tr>
	
	<table align="center">
	
		<tr style="width: 50%" align="center">
		<td align="center">		
				<jsp:include page="/WEB-INF/jsp/workflow/attachmentPageWF.jsp" >
				 <jsp:param name="attachmentName" value="DocumentAttachmentDraft" />
		         <jsp:param name="formName" value="commondraftuploadForm" />
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

</table>
	
</hdiits:form>	

<script>

function checkNumber()
{
	var flag =IsNumeric(document.getElementById('orderno').value)
	
	if(flag==false)
	{
		alert("Please write numeric only");
		return false;
	}
}

function enabled(src)
{
	
	if(src.checked == true)
	{
		document.getElementById('xsldisabled').checked=false;		
		document.getElementById('serName').style.display='';
		document.getElementById('xslEnabled').value=1;
	}
}


function enabled123(src)
{
	
	if(src.checked == true)
	{
		document.getElementById('xslenabled').checked=false;			
		document.getElementById('serName').style.display="none";
		document.getElementById('xslEnabled').value=0;
	}
}

function getDocList()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no'; 
	window.open('${contextPath}/hdiits.htm?actionFlag=draftUpload','',urlStyle);
		
}


function closewindow()
{
	var action="${contextPath}/hdiits.htm?actionFlag=getHomePage";
	document.getElementById("commondraftuploadForm").method="post";
	document.getElementById("commondraftuploadForm").action=action	
	document.getElementById("commondraftuploadForm").submit();

}

function createtemplate()
{
	if(document.getElementById('cntDocumentAttachmentDraft').value==0)
	{
		alert("Please specify atleast one draft to create");
		return false;
	}	
	
	if(document.getElementById('xslEnabled').value==1 && document.getElementById('serviceName').value=='')
	{
		alert("Please specify Service Name");
		return false;
	}
	
	if(document.getElementById('orderno').value=='')
	{
		alert("Please specify Service Name");
		return false;
	}
	
	
	if(document.getElementById('TemplateTye').value=='')
	{
		alert("Please specify TemplateTye");
		return false;
	}
	
	var action='${contextPath}/hdiits.htm?actionFlag=insertcommonDraftTemplate';
	document.getElementById("commondraftuploadForm").method="post";
	document.getElementById("commondraftuploadForm").action=action	
	
	document.getElementById("commondraftuploadForm").submit();
}
function getTemplateList()
{
	if(document.getElementById('docName').value=='')
	{
		alert("Please select  atleast one doc to create  draft");
		return false;
	}
	
	var action='${contextPath}/hdiits.htm?actionFlag=fetchcommonTemplateList';
	document.getElementById("commondraftuploadForm").method="post";
	document.getElementById("commondraftuploadForm").action=action	
	
	document.getElementById("commondraftuploadForm").submit();

}



function IsNumeric(sText)
{
   var ValidChars = "0123456789";
   var IsNumber=true;
   var Char;
 
   for (i = 0; i < sText.length && IsNumber == true; i++)
      {
      Char = sText.charAt(i);
      if (ValidChars.indexOf(Char) == -1)
         {
         IsNumber = false;
         }
      }
   return IsNumber;
 
}

</script>

<%
}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>