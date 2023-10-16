
<%

try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>




<script type="text/javascript"
	src="<c:url value="/script/hrms/gnl/AuditPara/AuditaddRecord.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>

<fmt:setBundle basename="resources.gnl.AuditPara.AuditParaLables" var="auditLables"
	scope="request" />
<fmt:setBundle basename="resources.gnl.AuditPara.AuditAlertMessages" var="alertLables"
	scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ParaList" value="${resValue.ListPara }"></c:set>
<c:set var="ParaId" value="${resValue.ParaId }"></c:set>
<c:set var="BothParaList" value="${resValue.BothParaList }"></c:set>
<c:set var="TypePara" value="${resValue.TypePara }"></c:set>
<c:set var="Halfmargin" value="${resValue.Halfmargin }"></c:set>
<c:set var="AuditPara" value="${resValue.AuditPara }"></c:set>
<c:set var="HalfParaList" value="${resValue.HalfParaList }"></c:set>
<c:set var="NameinBothLang" value="${resValue.NameinBothLang }"></c:set>
<script type="text/javascript">


function ClosePage(frm)
{
var fileNo="${ParaList.fileId}";
frm.action="hdiits.htm?actionFlag=getDesignationList&fileId="+fileNo;
frm.method="POST";
frm.submit();
}


var ParentParaId=0;
function SetParentPara1()
{

document.getElementById('ShowPreviousDtls').style.display='';
}
function SetParentPara2()
{
document.getElementById('h2').value="";
document.getElementById('ShowPreviousDtls').style.display='none';
}
function SaveVal(l)
{
	
	ParentParaId=l.value;
	if(ParentParaId==0||ParentParaId==undefined||ParentParaId=="")
	{
		document.getElementById('h2').value="";
		
	}	
	else
	{	
		document.getElementById('h2').value=ParentParaId;
		
	}
}
function ReplyData(PopupForm)
{
	
	window.document.forms[PopupForm].submit();
}

function validateReply(){
	
 	var officeTypeArray = new Array('ReplyBtn','yes','reply');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
		ReplyData("PopupForm");
	}
	}

function chkSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message bundle="${alertLables}" key="HRMS.SpclChars"/>");
  							control.focus();
  							return false;
  						}
  					}
			}
		


</script>
	
<hdiits:form name="PopupForm" validate="true" method="POST" encType="multipart/form-data" action="hrms.htm?actionFlag=ReplyData">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"
			bgColor="#386CB7"><b><fmt:message key="HRMS.AuditPara"
			bundle="${auditLables}" /></b></a></li>

	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0" ><br>

<hdiits:fieldGroup bundle="${auditLables}" expandable="false" id="EditReportDtlGrp"  titleCaptionId="HRMS.EditReprtDtls">
	<table width="50%" >
	
	<tr>
		<td width="25%">
			<b><hdiits:caption captionid="HRMS.AuditParasCncrnd" bundle="${auditLables}"/></b>
		</td>
		<td width="25%"> 
			<hdiits:text name="AuditParasCncernd" id="AuditParasCncernd" default="${ParaList.paraNo }" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td width="25%">
			<b><hdiits:caption captionid="HRMS.Description"  bundle="${auditLables}"/>
		</b></td>
		<td width="25%">
			<hdiits:textarea name="description" id="description" cols="35" rows="3" default="${ParaList.paraDesc }" readonly="true"/>
		</td>
	</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${auditLables}" expandable="false" id="ParasReplyGrp"  titleCaptionId="HRMS.ParasReply">
<table class="tabtable">
<c:set var="Replied" value="Replied"/>
<c:set var="Dropped" value="Dropped"></c:set>
<c:if test="${Replied == ParaList.status}" >
	<table width="50%" id="Replied" >
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.ParaRep" bundle="${auditLables}"/></b></td>
			<td width="25%"><hdiits:textarea name="Reply" id="Reply" cols="35" rows="3" readonly="true"/></td>
		</tr>
	</table>
	<table width="100%">
	<tr>
		<td>
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="ReplyAttachmentView" />
						<jsp:param name="formName" value="PopupForm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="N" />
						
            </jsp:include>
         </td>
	</tr>
</table>
<script>
		document.getElementById('target_uploadReplyAttachmentView').style.display='none';
		document.getElementById('formTable1ReplyAttachmentView').firstChild.firstChild.style.display='';	
		document.getElementById('Reply').value="${ParaList.replyByBranch}";
</script>	
</c:if>

<c:if test="${Dropped == ParaList.status}" >
	<table width="50%" id="Dropped" >
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.ParaRep" bundle="${auditLables}"/></b></td>
			<td width="25%"><hdiits:textarea name="Reply" id="Reply" cols="35" rows="3" readonly="true"/></td>
		</tr>
	</table>
	<table width="100%">
	<tr>
		<td>
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="ReplyAttachmentView" />
						<jsp:param name="formName" value="PopupForm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="N" />
						
            </jsp:include>
         </td>
	</tr>
</table>
<script>
		document.getElementById('target_uploadReplyAttachmentView').style.display='none';
		document.getElementById('formTable1ReplyAttachmentView').firstChild.firstChild.style.display='';	
		document.getElementById('Reply').value="${ParaList.replyByBranch}";
</script>	
</c:if>


<c:if test="${Replied != ParaList.status}">
<c:if test="${Dropped != ParaList.status}">
	<table width="52%" id="Unreplied" >
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.ParaRep" bundle="${auditLables}"/></b></td>
			<td width="25%"><hdiits:textarea name="reply" id="reply" cols="35" rows="3" mandatory="true" validation="txt.isrequired"  captionid="HRMS.ParaRep" bundle="${auditLables}" onblur="chkSpChars(this)"></hdiits:textarea></td>
		</tr>
	</table>	
	<table width="100%">
	<tr>
		<td>
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="ReplyAttachment" />
						<jsp:param name="formName" value="PopupForm" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="N" />
						
            </jsp:include>
         </td>
	</tr>
</table>
<script type="text/javascript">

</script>

</c:if>
</c:if>
<c:set var="halfMargin" value="Half Margin  Para"/>
<c:if test="${halfMargin!=TypePara }">
<table id="ShowRadio">
	<tr>
		<td><b><hdiits:caption captionid="HRMS.Check" bundle="${alertLables}"/></b></td>
	</tr>
	<tr>
		<td><hdiits:radio id="yes" name="yes"  value="${Halfmargin}" onclick="SetParentPara1()" mandatory="true" validation="sel.isradio" captionid="HRMS.Yes" bundle="${auditLables}" errCaption=""/></td>
		<td><hdiits:radio id="yes" name="yes"  value="${AuditPara}" onclick="SetParentPara2()" mandatory="true" validation="sel.isradio" captionid="HRMS.No" bundle="${auditLables}"/></td>
	</tr>
</table>

<table id="ShowPreviousDtls" width="100%" border="1"  style="border-collapse: collapse;display:none" borderColor="BLACK">
<tr>
<td></td>
<td class=fieldLabel align="center" bgcolor="#C9DFFF"><b><fmt:message key="HRMS.AuditParasCncrnd" bundle="${auditLables}"/></b></td>
<td class=fieldLabel align="center" bgcolor="#C9DFFF"><b><fmt:message key="HRMS.Desc"  bundle="${auditLables}"/></b></td>
<td class=fieldLabel align="center" bgcolor="#C9DFFF"><b><fmt:message key="HRMS.ParaType" bundle="${auditLables}"/></b></td>
</tr>
<c:set var="DraftPara" value="Draft Para"/>
<c:if test="${ DraftPara==TypePara}">
<c:forEach var="NameinBothLang" items="${NameinBothLang }">
<tr>
	<td><hdiits:radio name="ParaId" id="ParaId" value="${NameinBothLang.paraId}" onclick="SaveVal(this)"/></td>
 		<td><c:out value="${NameinBothLang.paraNo}"></c:out> </td>
 		<td><c:out value="${NameinBothLang.paraDesc}"></c:out></td>
 		<td><c:out value="${NameinBothLang.cmnLookupMst.lookupDesc}"></c:out></td>
</tr>	
</c:forEach>
</c:if>
<c:forEach var="separteItems" items="${NameinBothLang}">
	<tr>
		<td><hdiits:radio name="ParaId" id="ParaId" value="${separteItems.paraId}" onclick="SaveVal(this)"/></td>
 		<td><c:out value="${separteItems.paraNo}"></c:out> </td>
 		<td><c:out value="${separteItems.paraDesc}"></c:out></td>
 		<td><c:out value="${separteItems.cmnLookupMst.lookupDesc}"></c:out></td>
	</tr>
</c:forEach>
</table>
</c:if>
</table>
</hdiits:fieldGroup>
<center>
<hdiits:button captionid="HRMS.Reply" bundle="${auditLables}" name="ReplyBtn" id="ReplyBtn" type="button" onclick="validateReply()" />
<hdiits:button name="Close" type="button" value="Close" captionid="HRMS.Close" bundle="${auditLables}" onclick="window.close()"/> 

</center>
<script type="text/javascript">
var StatusIs="${ParaList.status}";
if("${TypePara}"!="Half Margin  Para")
{
	if(StatusIs=="Replied" || StatusIs=="Dropped")
	{
		document.getElementById('ShowRadio').style.display='none';
		document.getElementById('ReplyBtn').style.display='none';
	}
}
else
{
	if(StatusIs=="Replied" || StatusIs=="Dropped")
	{
		document.getElementById('ReplyBtn').style.display='none';
	}
}
</script>

</div>
</div>
<input type="text" name="h1" id="h1" value="${ParaId }" style="display:none"/>
<input type="text" name="h2" id="h2"  style="display:none"/>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />	
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="ReplyBtn,yes,reply" />
		<script type="text/javascript">
	initializetabcontent("maintab")
	</script>
	
</hdiits:form>
		<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>