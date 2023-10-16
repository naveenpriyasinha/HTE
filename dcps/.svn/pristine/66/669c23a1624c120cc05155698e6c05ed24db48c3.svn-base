<%@ include file="../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.*"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="docNameList" value="${resultMap.docNameList}"></c:set>
<c:set var="wfDocTemplist" value="${resultMap.wfDocTemplist}"></c:set>
<c:set var="attachmentList" value="${resultMap.AttachmentList}"></c:set>
<c:set var="mode" value="${resultMap.mode}"></c:set>
<c:out value="${resultMap.msg}"/> 
<c:set var="docid1" value="${resultMap.docid1}"></c:set>
<%int counter=0;%>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<%
	ResultObject resObj;
	resObj = (ResultObject) request.getAttribute("result");		
	Map resValueMap = (HashMap) resObj.getResultValue();
	List objUrlList=(List)resValueMap.get("lurlList");
	List attachments=(List)resValueMap.get("AttachmentList");	
	
%>
<script>
function callsearch()
{
		var mode="${mode}";

		if(mode=="search")
		{
		
			goToNextTab();
			document.getElementById('docAttachInfoTable').style.display='';
			document.getElementById('viewdocnameid').options.selectedIndex="${docid1}";
		}
}
function showDocAttachment()
{
	document.getElementById('docnameid').value=document.getElementById('viewdocnameid').options.selectedIndex;
		
	//document.getElementById("templateFormId").method="post";
	//document.getElementById("templateFormId").action="hdiits.htm?actionFlag=searchDocumentAttachmentDetail";
	document.getElementById('actionFlag1').value="searchDocumentAttachmentDetail";
	document.getElementById("templateFormId").submit();
	
	
	
	
	

}
</script>
<hdiits:form name="templateForm" id="templateFormId" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true" >
<hdiits:hidden name="actionFlag" id="actionFlag1" default="insertWorkflowTemplateDetails" />
<hdiits:hidden name="docname" id="docnameid" />

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption caption="Insert" captionid="insertTab"/></a></li>
			<li><a href="#" rel="tcontent2"><hdiits:caption caption="View" captionid="viewTab"/></a></li>
	</ul>
</div>

<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0">

	<b><hdiits:caption captionid="first" caption="Subjects : "/></b>
	<hdiits:select captionid="frist1" name="docnameinsert">
		<c:forEach  items="${docNameList}" var="docLookup">
			<option value='<c:out value="${docLookup.docId}"/>'><c:out value="${docLookup.docName}" />
		    </option>								
		</c:forEach>
	</hdiits:select>

	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp" >
                <jsp:param name="attachmentName" value="DocumentAttachment" />
                <jsp:param name="formName" value="templateForm" />
                <jsp:param name="attachmentType" value="Document" />   
                <jsp:param name="attachmentTitle" value="Document" />   
                <jsp:param name="rowNumber" value="1" />                
                           
    </jsp:include>       

</div>

<div id="tcontent2" class="tabcontent" tabno="1" >

	<b><hdiits:caption captionid="second" caption="Subjects : "/></b>
	<hdiits:select captionid="second2" name="viewdocname" id="viewdocnameid">
		<c:forEach  items="${docNameList}" var="docLookup">
			<option value='<c:out value="${docLookup.docId}"/>'><c:out value="${docLookup.docName}" />
		    </option>								
		</c:forEach>
	</hdiits:select>
	
	<hdiits:button name="btnview" caption="View" type="button" onclick="showDocAttachment()"/>
	
	<table id="docAttachInfoTable"  class="datatable" border="0" >
		<tr>
			<td><b>SR No</b></td>
			<td><b>Attachment</b> </td>
						
		</tr>
		
				
		<c:forEach var="attachmentObj" items="${attachmentList}">
			<tr>	
					<td><%=counter +1%></td>
					<td>
					<hdiits:a href="<%=(String)objUrlList.get(counter)%>" caption="<%=(String)attachments.get(counter++) %>"></hdiits:a> 
					</td>
			</tr>		
		</c:forEach>
	</table>
	
</div>



<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>		
<script>
callsearch();
</script>