<%
try 
{
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="wfLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>">
</script>	

<hdiits:form name="createCommGroup" id="createCommGroup" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">

<input type="hidden" name="postIdArray" />
<input type="hidden" name="empArray" />
<input type="hidden" name="desgnArray" />
<input type="hidden" name="locArray" />
	<br />
	<br />
	<table align="center">
		<tr>
			<td>
				<b><fmt:message key="WF.GROUP" bundle="${wfLables}"/></b>
			</td>
		</tr>
	</table>
	
	<br />
	<br />

	<table width="100%" id="table1" align="center">
		<tr align="center">
			<td class="fieldLabel" width="20%" align="left">
			</td>
			<td class="fieldLabel" width="10%" align="left">
				<hdiits:caption captionid="WF.GROUPNAME" bundle="${wfLables}" />
			</td>
			<td>
				:
			</td>
			<td class="fieldLabel" width="66%" align="left" colspan="2">
				<hdiits:text id="groupName" name="groupName" mandatory="true" size="50" />
			</td>
		</tr>
		
		<tr align="center">
			<td class="fieldLabel" width="20%" align="left">
			</td>
			<td class="fieldLabel" width="10%" align="left">
				<hdiits:caption captionid="WF.EMPGROUP" bundle="${wfLables}" />
			</td>
			<td>
				:
			</td>
			<td class="fieldLabel" width="33%" align="left">
				<textarea id="groupMember" name="groupMember" cols="50" rows="4" readonly="true">
				</textarea>
			</td>
			<td class="fieldLabel" width="33%" align="left">
				<a href="javascript:openDepartmentSearch()"> <hdiits:image source="images/workflowImages/select-employee.gif" /> </a>
			</td>
		</tr>
	
		<tr align="center">
			<td colspan="5" align="center">
				<hdiits:button type="button" id="okBtn" name="okBtn" captionid="WF.OKAY" bundle="${wfLables}" onclick="okay()" />
				<hdiits:button type="button" id="closeBtn" name="closeBtn" captionid="WF.CANCEL" bundle="${wfLables}" onclick="closeMe()" />
			</td>
		</tr>
	</table>

<script type="text/javascript">

document.getElementById('groupMember').value = '';

function useSelectedEmployees()
{
	if(document.getElementById('postIdArray').value != '')
	{
		document.getElementById('groupMember').value = '';
		var empNameArrTemp = document.getElementById('empArray').value;
		var empNameArr = empNameArrTemp.split(',');
		var desgnArrTemp = document.getElementById('desgnArray').value;
		var desgnArr = desgnArrTemp.split(',');
		for(var i=0; i<empNameArr.length; i++)
		{
			document.getElementById('groupMember').value += empNameArr[i] + ' (' + desgnArr[i] + ')';
			if(i != empNameArr.length - 1)
				document.getElementById('groupMember').value += ', ';
		}
	}
}

function openDepartmentSearch()
{
	var urlstyle = 'toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes';
	window.open("${contextPath}/hdiits.htm?actionFlag=DeptSearchData&otherSender=yes","test",urlstyle);
}

function okay()
{
	if(document.getElementById('groupName').value == '')
	{
		alert('<fmt:message key="WF.ENTERGROUPNAME" bundle="${wfLables}"/>');
		return false;
	}
	
	if(document.getElementById('groupMember').value == '')
	{
		alert('<fmt:message key="WF.SELECTEMPLOYEE" bundle="${wfLables}"/>');
		return false;
	}

	var url = "${contextPath}/hdiits.htm?actionFlag=addCommuniqueGroup";
	document.forms[0].action=url;
	document.forms[0].submit();	
}

function closeMe()
{
	window.location = "${contextPath}/hdiits.htm?actionFlag=showCommuniqueGroup";
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