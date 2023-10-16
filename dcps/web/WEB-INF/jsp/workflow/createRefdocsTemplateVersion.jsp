<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="crtTemplateVersionfrm" method="post" validate="true" encType="multipart/form-data">

<table border="1" width="100%" bordercolor="green">
	<tr >
		<td width="100%" align="center" style="border: none;" colspan="4">	 
		<font size="3"><b><fmt:message key="WF.DocVer" bundle="${fmsLables}"></fmt:message></b></font>
		</td>
		
	</tr>
	<tr>
	</tr>
	
	<tr>
		<td width="30%" align="left" style="border: none;">	
		<hdiits:caption captionid="" caption="Version Type"></hdiits:caption>
		
		</td>
		<td width="30%" align="left" style="border: none;" >
		<hdiits:radio name="vertyperad" id="verrad1" mandatory="true"  validation="sel.isradio" value="major"  default="major"  captionid="WF.DocVerTypeMajor" bundle="${fmsLables}" ></hdiits:radio>
		<hdiits:radio name="vertyperad"  id="verrad2"  validation="sel.isradio" value="minor"  captionid="WF.DocVerTypeMinor" bundle="${fmsLables}"  ></hdiits:radio>
			
		</td>

		<td width="40%">
		</td>		
		
		
	</tr>
	<tr>
	</tr>			
	<tr >
		
		<td style="border: none" colspan="4">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="RefdocsTemplateVersionAttachment" />
						<jsp:param name="formName" value="crtTemplateVersionfrm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Document Attachment" />
			</jsp:include>
		</td>			
	</tr>
	
</table>
<center>

<hdiits:button name="crtfolderbtn"  captionid="WF.Create" bundle="${fmsLables}"  type="button"  onclick="submitform()" />
<hdiits:button name="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>				
</center>
</hdiits:form>

<script>

function submitform()
{
window.document.forms[0].action="hdiits.htm?actionFlag=FMS_insertTemplateVersionDetail&templateId=10001000548";
window.document.forms[0].submit()
}

</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>


