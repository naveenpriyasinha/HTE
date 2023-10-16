<% try
{
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="CorrAttachments" value="${resValue.CorrAttach}"></c:set>
<c:set var="ServiceObj" value="${resValue.ServiceLocator}" scope="session"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="srNo" value="${1}" />
<c:set var="disableflag" value="${resValue.disableflag}"></c:set>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:form name="TappalAttForm" method="POST" validate="true" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="decFlag" id="hid_decFlag"/>
<hdiits:hidden name="corr_id" id="hid_corr_id"/>
<hdiits:hidden name="fileId" id="hid_file_id" />
<hdiits:hidden name="butFlag" default="${param.butFlag}"/>
<hdiits:hidden name="corrId" default="${resValue.corrId}"/>
<hdiits:hidden name="attachmentId"  default="${resValue.attachmentId}"/>
<hdiits:hidden name="attIdList" id="attIdList"/>	
			<body>
				<display:table list="${CorrAttachments}" pagesize="12" requestURI="" id="row" defaultorder="ascending" defaultsort="1"  style="width:100%">
					<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo}</display:column>
					<display:column  titleKey="WF.ATTNAME" headerClass="datatableheader" sortable="true" media="html"><font color="blue"><a id="${srNo}" style="cursor:hand;" dynLink="${row.cmnAttachmentMst.attachmentId},${row.srNo},${row.orgFileName}" onclick="displayFile(this)">${row.orgFileName}</a></font></display:column>
					<c:if test="${not empty resValue.fromRefDocs}">
						<display:column titleKey="WF.PUBTOREFDOC" headerClass="datatableheader" media="HTML"><input  type="checkbox"  id="rad_${row.cmnAttachmentMst.attachmentId}" onclick="addRefDoctoList('${row.cmnAttachmentMst.attachmentId}')"></display:column>
					</c:if>
					<c:set var="srNo" value="${srNo+1}" />
				</display:table>
			</body>
			<br>
			<c:if test="${not empty resValue.fromRefDocs and not empty CorrAttachments}">
				<center><hdiits:button name="button_Rem" type="button" captionid="WF.RMCORR" bundle="${fmsLables}" onclick="removeRefDocs()"/></center>
			</c:if>
			
			<c:if test="${not empty CorrAttachments}">
				<iframe src="./servlet/FileOpenServlet?attachmentId=<%=request.getParameter("attachmentId")%>&attachmentSerialNumber=<%=request.getParameter("attachmentSerialNumber")%>" name="frameNameDyn" id="frame_id"  width="100%" height="50%" marginwidth="0" marginheight="0" >
				</iframe>
			</c:if>
			
				<br>
				<br>
				
		         <BR>
		         <c:if test="${empty resValue.fromRefDocs}">
				<center><hdiits:button  readonly="${disableflag}"  type="button" id="addattachbtn" name="button" captionid="WF.ADDATT" bundle="${fmsLables}" onclick="openAttachmentTab()"/></center>
				</c:if>
			<br>
			<br>
			<c:if test="${not empty param.butFlag}">
				<a style="cursor:hand;" onclick="parent.backtoFrameSrc('${param.fileId}')"><center><font size="3" color="blue">Back</font></center></a>
			</c:if>

		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
</hdiits:form>

<script type="text/javascript">

	var refDocAttIds = new Array();
	function addRefDoctoList(attachObj)
	{
		refDocAttIds.push(attachObj);
	}

	function displayFile(attachObj)
		{
			var splitParameter = attachObj.dynLink;
			var newLinks = new Array();
			newLinks = splitParameter.split(',');
			var fileName = attachObj.innerHTML;
			document.getElementById('frame_id').src="./servlet/FileOpenServlet?attachmentId="+newLinks[0]+"&attachmentSerialNumber="+newLinks[1]+"&fileName="+fileName;
			document.getElementById("frame_id").height=100 ;
			document.getElementById('frame_id').height=document.getElementById("frame_id").document.body.scrollHeight;
			
		}
	
		function openAttachmentTab()
		{
			var urlStyle = 'width=1000,height=500,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no';
			var butFlag = document.getElementById('butFlag').value;
			var corrId = document.getElementById('corr_id').value;
			window.open("${contextPath}/hdiits.htm?actionFlag=showCorrAttach&flag=fromTappalAttachment&corrId="+corrId,"",urlStyle);
			
		}
		function removeRefDocs(){
			if(confirm("Do you want to remove selected Reference Docs form file?")==true){
			document.getElementById('attIdList').value=refDocAttIds;
			document.forms[0].method="post";
			document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=fms_remRefDocsFromFile";	
			document.forms[0].submit();
			}
			else{
				return false;
			}
		}
		
</script>
<script type="text/javascript">
	var corr_id;
	var decflag = parent.document.forms[0].DecisionFlag.value;
	if(decflag == 'file')
	{
		corr_id = document.forms[0].corrId.value;
		file_id = parent.document.forms[0].fileId.value
		document.getElementById('hid_file_id').value = file_id;
	}
	else if (decflag == 'corr')
		corr_id = parent.document.forms[0].corrId.value;
	document.getElementById('hid_decFlag').value = decflag;
	document.getElementById('hid_corr_id').value = corr_id;
	if(document.getElementById("frame_id") != null)
	{
		document.getElementById("frame_id").height=100 ;
		document.getElementById('frame_id').height=document.getElementById("frame_id").document.body.scrollHeight;
	}
</script>
<%}
catch(Exception e)
{
	e.printStackTrace();
}
%>


<script>
	if(${fn:length(CorrAttachments)} > 0 )
	{
		if(document.getElementById(""+${fn:length(CorrAttachments)}) != null)
			document.getElementById(""+${fn:length(CorrAttachments)}).onclick();
	}	

</script>
