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
<c:set var="finalcategorylist" value="${resValue.finalcategorylist}"></c:set>
<script>

function insertNewTamplateDetail()
{

	
	if(document.getElementById('txttemplatname').value=='')
	{
		alert("Please enter template name");
		
	}
	else if(document.getElementById('catnameid').style.display=='')
	{
		if(document.getElementById('RefDocCat').value=='0')
		{
			alert("Please enter Category Name");
		}
		
	}
	else if(document.getElementById('engattid').style.display=='')
	{
		if(document.getElementById('myTableRefdocsTemplateEngAttachment').rows.length==1)
		{
			alert("Please enter English Attachment");
		}
	}	
	else if(document.getElementById('gujattid').style.display=='')
	{
		if(document.getElementById('myTableRefdocsTemplateGujAttachment').rows.length==1)
		{
			alert("Please enter Gujarati Attachment");
		}
	}	
	else
	{
	window.document.forms[0].action = "hdiits.htm?actionFlag=FMS_inserNewRefdocTemplateDetail";
	window.document.forms[0].submit();
	}
}
function langrad_onclick(src)
{

	
	if(src.id=='langrad1')
	{
		document.getElementById('engattid').style.display='';
		document.getElementById('gujattid').style.display='none';
	}
	if(src.id=='langrad2')
	{
		document.getElementById('gujattid').style.display='';
		document.getElementById('engattid').style.display='none';
	}
	if(src.id=='langrad3')
	{	
		document.getElementById('engattid').style.display='';
		document.getElementById('gujattid').style.display='';
		
	}
	
}
</script>
<hdiits:form name="newrefdoctemfrm" method="post" validate="true" encType="multipart/form-data">
<hdiits:hidden name="deptId" default="${resValue.deptId}"/>
<hdiits:hidden name="locId" default="${resValue.locId}"/>
<hdiits:hidden name="branchId" default="${resValue.branchId}"/>
<hdiits:hidden name="folderId" default="${resValue.folderId}"/>
<hdiits:hidden name="categoryCode" default="${resValue.categoryCode}"/>
<c:out value="categoryCode${resValue.categoryCode}"></c:out>
<c:out value="folderId${resValue.folderId}"></c:out>
<table border="1" width="100%" bordercolor="green">
	<tr >
		<td width="100%" align="center" style="border: none;" colspan="4">	 
		<font size="3"><b><fmt:message key="WF.CrtNewDoc" bundle="${fmsLables}"></fmt:message></b></font>
		</td>
		
	</tr>
	<tr>
	</tr>
	<tr>
		<td width="25%" align="left" style="border: none;">	<hdiits:caption captionid="WF.TemplateName" bundle="${fmsLables}"></hdiits:caption>
		</td>
		<td width="25%" align="left" style="border: none;">	<hdiits:text  name="txttemplatname" mandatory="true" /> 
		</td>
		
		<td width="25%" align="left" style="border: none;" id="catnamecapid">	<hdiits:caption captionid="WF.CatName" bundle="${fmsLables}"/>
		</td>
		<td  width="25%" align="left" style="border: none;" id="catnameid">
			<hdiits:select name="RefDocCat"  id="RefDocCat"  mandatory="true" validation="sel.isrequired" captionid="WF.CatName" bundle="${fmsLables}"  sort="false">
		
				<hdiits:option value="0" selected="true">
				<fmt:message key="WF.Select" bundle="${fmsLables}"></fmt:message></hdiits:option>
							<c:forEach var="categorylst" items="${finalcategorylist}">
							<option value='<c:out value="${categorylst.fmsTemplateCategoryMst.categoryCode}"/>'>
							<c:out value="${categorylst.categoryName}"/></option>
				</c:forEach>
			</hdiits:select>
		</td>
		
		
	</tr>
	<tr>
		<td width="25%" align="left" style="border: none;">	<hdiits:caption captionid="WF.TemplateDesc" bundle="${fmsLables}"></hdiits:caption>
		</td>
		<td width="25%" align="left" style="border: none;">	<hdiits:text  name="txttemplatdesc" />
		</td>
		<td width="25%" align="left" style="border: none;">	
		</td>
		<td width="25%" align="left" style="border: none;">	
		</td>
		
	</tr>
	
	<tr>
		<td width="25%" align="left" style="border: none;">	
		<hdiits:caption captionid="WF.Lang" bundle="${fmsLables}"></hdiits:caption>
		
		</td>
		<td width="25%" align="left" style="border: none;" >
		<hdiits:radio name="langrad" id="langrad1" mandatory="true"  validation="sel.isradio" value="E"  default="E"  captionid="" caption="English" onclick="langrad_onclick(this)"></hdiits:radio>
		<hdiits:radio name="langrad"  id="langrad2"  validation="sel.isradio" value="G" captionid="" caption="Gujarati" onclick="langrad_onclick(this)" ></hdiits:radio>
			
		</td>
		<td width="25%" align="left" style="border: none;">	
		<hdiits:radio name="langrad"  id="langrad3"  validation="sel.isradio" value="BOTH" captionid="" caption="Both" onclick="langrad_onclick(this)"></hdiits:radio>
		</td>
		<td width="25%" align="left" style="border: none;">	
		</td>
		
	</tr>
	
				
	<tr id="engattid">
		
		<td style="border: none" colspan="4">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="RefdocsTemplateEngAttachment" />
						<jsp:param name="formName" value="newrefdoctemfrm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="English Attachment" />
						
			</jsp:include>
		</td>			
	</tr>
	<tr style="display: none" id="gujattid">
		
		<td style="border: none" colspan="4">
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="RefdocsTemplateGujAttachment" />
						<jsp:param name="formName" value="newrefdoctemfrm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Gujarati Attachment" />
			</jsp:include>
		</td>			
	</tr>
</table>
<center>

<hdiits:button name="crtfolderbtn"  captionid="WF.Create" bundle="${fmsLables}"  type="button"  onclick="insertNewTamplateDetail()" />
<hdiits:button name="cancelbtn" captionid="WF.Close" bundle="${fmsLables}" type="button" onclick="self.close()"/>				
</center>
</hdiits:form>

<script>
	var catcode='${resValue.categoryCode}';
	if(catcode!='')
	{
			document.getElementById('catnameid').style.display='none';
			document.getElementById('catnamecapid').style.display='none';
			
	}
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
