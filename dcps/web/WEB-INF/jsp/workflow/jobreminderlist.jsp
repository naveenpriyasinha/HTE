
<% try {  %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<script>

function editReminder(srNo)
{
	var urlStyle ='toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,type=fullWindow';	
	window.open('hdiits.htm?actionFlag=FMS_editReminderDetails&srNo='+srNo,'test',urlStyle);
}

function createNew()
{
	var fileId = document.getElementById('fileId').value;
	var urlStyle ='toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,type=fullWindow';	
	window.open('hdiits.htm?actionFlag=FMS-CreateReminder&fileId='+fileId,'test',urlStyle);
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FmsJobReminderMstList" value="${resValue.FmsJobReminderMstList}"></c:set>

<hdiits:form name="reminderlist" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="jobSeqId" default="${resValue.JobSeqId}" />
<hdiits:hidden name="fileId" default="${resValue.fileId}" />

		<display:table list="${FmsJobReminderMstList}" pagesize="12" requestURI="" id="row"  style="width:100%">		
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio" name="radio" id="${row.srNo}" value="${row.srNo}"> </display:column>
			<display:column  titleKey="subject" headerClass="datatableheader">${row.remSubject}</display:column>			
			<display:column titleKey="to" headerClass="datatableheader">${row.toPostList}</display:column>
			<display:column titleKey="cc" headerClass="datatableheader">${row.ccPostList}</display:column>
			<display:column titleKey="start date" headerClass="datatableheader">${row.startDate}</display:column>
			<display:column titleKey="end date" headerClass="datatableheader">${row.endDate}</display:column>
			<display:column titleKey="fire time" headerClass="datatableheader">${row.fireTime}</display:column>
			<display:column titleKey="edit" headerClass="datatableheader"><a href="javascript:editReminder(${row.srNo})" >EDIT</display:column>
		</display:table>
</hdiits:form>
<hdiits:button name="sendbtn" captionid=""  caption="Creste New Reminder" bundle="${fmsLables}" type="button" onclick="createNew()"/>
<% }
catch(Exception ex)  {
	ex.printStackTrace();	
}	
%>