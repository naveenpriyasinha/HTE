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
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<hdiits:form name="draftuploadForm" validate="true" action="./hdiits.htm?insertAction" encType="multipart/form-data">

<table>
	<tr>
	<td>Subject Name</td>
	<td><hdiits:text name="subname" caption="subname"/></td>
	<td>docId</td>
	<td><hdiits:text name="docIdTxt" caption="docIdTxt"/></td>
	</tr>
	<tr>
		<td>
			<hdiits:submitbutton name="search" caption="search" onclick="getsearchedDocList();"/>
		</td>
	</tr>
	
	<tr>
						
	</tr>
	
	<tr>
		<display:table list="${WfDocList}" pagesize="12" requestURI="" id="row"  style="width:100%"  >
				<display:column titleKey="Select" headerClass="datatableheader" media="HTML">
					<input type="radio" name="radio" id="${row.docId}" value="${row.docName}">
				</display:column>
				
				<display:column  titleKey="WF.DOCUMENTNAME" headerClass="datatableheader">${row.docName}</display:column>
		
				<display:column titleKey="WF.Description" headerClass="datatableheader"><c:out value="${row.description}"></c:out></display:column>
				
		</display:table>	
	<tr>
	
							
	
</table>
<table align="center">

	<tr></tr><tr></tr>
	<tr align="center">	
		<td></td><td></td>
		<td align="center"><hdiits:button type="button"  name="ok" id="approveButton" captionid="WF.Ok" bundle="${wfLables}" onclick="docSelect()" /></td>
		
							
		<td  align="center"><hdiits:button type="button"  name="close" id="doNotIssueButton" captionid="WF.Close" bundle="${wfLables}" onclick="close();" /></td>					
		
		<td  align="center"><hdiits:button type="button"  name="back" id="back" caption="back" onclick="getDocList();" /></td>					
		
	</tr>
</table>
</hdiits:form>	

<script type="text/javascript">
function getsearchedDocList()
{
	var action="${contextPath}/hdiits.htm?actionFlag=getsortedDocList";
	document.getElementById("draftuploadForm").method="post";
	document.getElementById("draftuploadForm").action=action	
	document.getElementById("draftuploadForm").submit();
}
function getDocList()
{
	var action="${contextPath}/hdiits.htm?actionFlag=draftUpload";
	document.getElementById("draftuploadForm").method="post";
	document.getElementById("draftuploadForm").action=action	
	document.getElementById("draftuploadForm").submit();
}
function docSelect()
{
	
	var radioObj = document.getElementsByName('radio');
	var radLength = document.getElementsByName('radio').length;
	var val;
	var id;
	for(var cnt=0;cnt<radLength ;cnt++)
	{
		if(radioObj[cnt].checked == true)
		{
			 val=radioObj[cnt].value;
			 id=radioObj[cnt].id;
		}
	}
		
	window.opener.document.getElementById('docName').value=val;
	alert("selected docid is  "+id);
	
	window.opener.document.getElementById('docId').value=id;
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