<%
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
<c:set var="CommuniqueGroupLst" value="${resultMap.CommuniqueGroupLst}"></c:set>

<hdiits:form name="commGroup" id="commGroup" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">

<input type="hidden" name="selectedGroupId" />
	<br />
	<br />
	<table align="center">
		<tr>
			<td>
				<b><fmt:message key="WF.USERGROUP" bundle="${wfLables}"/></b>
			</td>
		</tr>
	</table>
	
	<br />
	<br />

		<display:table list="${CommuniqueGroupLst}" id="CommuniqueGroupLst" style="width:100%" pagesize="10" requestURI="">	
			<display:column style="text-align:center" class="tablecelltext"  media="HTML">
				<hdiits:checkbox id="${CommuniqueGroupLst.groupId}" value="${CommuniqueGroupLst.groupId}" name="viewGroupId" onclick="addGroupDetails('${CommuniqueGroupLst.groupId}','${CommuniqueGroupLst.postIds}','${CommuniqueGroupLst.groupMember}')" />
			</display:column>
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.GROUPNAME" sortable="false" headerClass="datatableheader">${CommuniqueGroupLst.groupName}</display:column>  							
			<display:column style="text-align:center" class="tablecelltext" titleKey="WF.GROUPMEMBER" sortable="false" headerClass="datatableheader">${CommuniqueGroupLst.groupMember}</display:column>
		</display:table> 			

	<br />
	<br />

	<table width="100%" id="table1" align="center">
		<tr align="center">
			<td colspan="5" align="center">
				<hdiits:button type="button" id="okBtn" name="okBtn" captionid="WF.OKAY"  bundle="${wfLables}" onclick="okay()" />
				<hdiits:button type="button" id="createBtn" name="createBtn" captionid="WF.CREATE"  bundle="${wfLables}" onclick="createGroup()" />
				<hdiits:button type="button" id="updateBtn" name="updateBtn" captionid="WF.UPDATE"  bundle="${wfLables}" onclick="updateGroup()" />
				<hdiits:button type="button" id="deleteBtn" name="deleteBtn" captionid="WF.DELETE"  bundle="${wfLables}" onclick="deleteGroup()" />
				<hdiits:button type="button" id="closeBtn" name="closeBtn" captionid="WF.CLOSE"  bundle="${wfLables}" onclick="closeMe()" />
			</td>
		</tr>
	</table>

<script type="text/javascript">

var groupIdArr = new Array();
var postIdArr = new Array();
var empNameArr = new Array();

function okay()
{
	if(groupIdArr.length == 0)
	{
		alert('<fmt:message key="WF.SELECTGROUP" bundle="${wfLables}"/>');
		return false;
	}
	else
	{
		var action=window.opener.document.getElementById('groupflag').value;
		
		if(action=='to')
		{
			window.opener.document.getElementById('ToList').value = postIdArr;
			window.opener.document.getElementById('ToGrpFlag').value = "yes";
			window.opener.document.getElementById('ToListtxt').value = empNameArr;
		}
		else
		{
			window.opener.document.getElementById('CCList').value = postIdArr;
			window.opener.document.getElementById('CCGrpFlag').value = "yes";
			window.opener.document.getElementById('CCListtxt').value = empNameArr;
		}
		window.close();
	}
}

function createGroup()
{
	window.location = '${contextPath}/hdiits.htm?viewName=wf-createCommuniqueGroup';
}

function deleteGroup()
{
	if(groupIdArr.length == 0)
	{
		alert('<fmt:message key="WF.SELECTGROUP" bundle="${wfLables}"/>');
		return false;
	}
	else
	{
		if(confirm('<fmt:message key="WF.DELETEGROUP" bundle="${wfLables}"/>'))
		{
			document.getElementById('selectedGroupId').value = groupIdArr;
			var url = "${contextPath}/hdiits.htm?actionFlag=deleteCommuniqueGroup";
			document.forms[0].action=url;
			document.forms[0].submit();	
		}
		else
			return false;
	}
}

function updateGroup()
{
	if(groupIdArr.length == 0)
	{
		alert('<fmt:message key="WF.SELECTGROUP" bundle="${wfLables}"/>');
		return false;
	}
	else if(groupIdArr.length > 1)
	{
		alert('<fmt:message key="WF.SELECTONEGROUP" bundle="${wfLables}"/>');
		return false;
	}
	else
	{
		document.getElementById('selectedGroupId').value = groupIdArr;
		var url = "${contextPath}/hdiits.htm?actionFlag=updateCommuniqueGroup";
		document.forms[0].action=url;
		document.forms[0].submit();	
	}
}

function closeMe()
{
	window.close();
}

function addGroupDetails(groupId, postIds, empNames)
{
	if(document.getElementById(groupId).checked == true)
	{
		groupIdArr.push(groupId);
		postIdArr.push(postIds);
		empNameArr.push(empNames);
	}
	else
	{
		for(var i=0;i<groupIdArr.length;i++)
		{
			if(groupIdArr[i] == groupId)
			{
				groupIdArr.splice(i,1);
				postIdArr.splice(i,1);
				empNameArr.splice(i,1);
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