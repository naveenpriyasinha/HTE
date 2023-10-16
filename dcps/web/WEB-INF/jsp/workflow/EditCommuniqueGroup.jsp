<%
int i = 1;
try 
{
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>">
</script>	

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set> 
<c:set var="CommuniqueUser" value="${resultMap.CommuniqueUser}"></c:set>

<hdiits:form name="commGroup" id="commGroup" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">

<input type="hidden" name="selectedGroupId" />
	<br />
	<br />
	<table align="center">
		<tr>
			<td>
				<b><fmt:message key="WF.GROUPMEMBER" bundle="${wfLables}"/></b>
			</td>
		</tr>
	</table>
	
	<br />
	<br />

	<display:table list="${CommuniqueUser}" id="CommuniqueUser" style="width:100%" pagesize="10" requestURI="">	
		<display:column style="text-align:center" class="tablecelltext"  media="HTML">
			<hdiits:checkbox id="${CommuniqueUser.groupId}" value="${CommuniqueUser.groupId}" name="viewGroupId" onclick="addGroupId('${CommuniqueUser.groupId}')" />
		</display:column>
		<display:column style="text-align:center" class="tablecelltext" titleKey="WF.GROUPMEMBER" sortable="false" headerClass="datatableheader">${CommuniqueUser.groupMember}</display:column>
	<%=++i%>
	</display:table> 			

	<br />
	<br />

	<table width="100%" id="table1" align="center">
		<tr align="center">
			<td colspan="5" align="center">
				<hdiits:button type="button" id="okBtn" name="okBtn" captionid="WF.OKAY" bundle="${wfLables}" onclick="okay()" />
				<hdiits:button type="button" id="deleteBtn" name="deleteBtn" captionid="WF.DELETE" bundle="${wfLables}" onclick="deleteUser()" />
			</td>
		</tr>
	</table>

<script type="text/javascript">

var groupIdArr = new Array();

function okay()
{
	window.location = "${contextPath}/hdiits.htm?actionFlag=showCommuniqueGroup";
}

function deleteUser()
{
	if(groupIdArr.length == 0)
	{
		alert('<fmt:message key="WF.SELECTMEMBER" bundle="${wfLables}"/>');
		return false;
	}
	else if(groupIdArr.length == parseInt('<%=i%>') - 1)
	{
		alert('<fmt:message key="WF.REMOVEALLMEMBER" bundle="${wfLables}"/>');
		return false;
	}
	else
	{
		if(confirm('<fmt:message key="WF.DELETEMEMBER" bundle="${wfLables}"/>'))
		{
			document.getElementById('selectedGroupId').value = groupIdArr;
			var url = "${contextPath}/hdiits.htm?actionFlag=deleteCommuniqueUser";
			document.forms[0].action=url;
			document.forms[0].submit();	
		}
		else
			return false;
	}
}

function addGroupId(groupId)
{
	if(document.getElementById(groupId).checked == true)
	{
		groupIdArr.push(groupId);
	}
	else
	{
		for(var i=0;i<groupIdArr.length;i++)
		{
			if(groupIdArr[i] == groupId)
			{
				groupIdArr.splice(i,1);
			}
		}
	}
}

</script>

</hdiits:form> 
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>