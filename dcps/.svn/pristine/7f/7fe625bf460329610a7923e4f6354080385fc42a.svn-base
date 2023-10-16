<%
try 
{
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:setBundle basename="resources.WFLables" var="wfLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>"></script>	

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set> 
<c:set var="groupNameList" value="${resultMap.groupNameList}"></c:set>
<c:set var="userInfoList" value="${resultMap.userInfoList}"></c:set>
<c:set var="groupIdList" value="${resultMap.groupIdList}"></c:set>

<hdiits:form name="prfrdGroup" id="prfrdGroup" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="upd" default="true"/>

	<table align="center">
		<tr>
			<td>
				<b><fmt:message key="WF.PRFRDGRP" bundle="${wfLables}"/></b>
			</td>
		</tr>
	</table>
	
	<br />
	<br />

	<table id="tab_new" border="1" align="center" class="tabtable">
		<thead>
			<tr>
				<th class="datatableheader">Select Group</th>
				<th class="datatableheader">Group Name</th>
				<th class="datatableheader">Group Members</th>
			</tr>
		</thead>
	</table>
	<br> <br>
	<hdiits:button name="addBtn" type="button" onclick="addGroup()" value="Add Group" />
	<hdiits:button name="updateBtn" type="button" onclick="update()" value="Update Group" />
	<hdiits:button name="deleteBtn" type="button" onclick="deleteGrp()" value="Delete Group" />
	<hdiits:button name="sendBtn" type="button" onclick="sendToPost()" value="Send Job" />
	<br> <br>
	<table id="tab_add" border="1" align="center" class="tabtable" style="visibility: hidden">
		<thead>
		<tr><th class="datatableheader" colspan="3"> Add New Group </th></tr>
			<tr>
				<td >Enter Group Name:</td>
				<td ><hdiits:text name="grpName" id="grpName"/></td>
				<td >
				<hdiits:button name="addMember" type="button" onclick="addGrpMember()" value="Add Group Members" />
				</td>
			</tr>
		</thead>
	</table>
	
</hdiits:form> 

<script type="text/javascript">
	var grpId = 0;
	var groupNameList;
	var postIdList = new Array();
	
	function getPostId(check)
	{
		if(check.checked==true)
			postIdList.push(check.id);
		else
		{
			for(var i=0; i<postIdList.length; i++)
			{
				if(postIdList[i] == check.id)
				{
					postIdList.splice(i, 1);
					break;
				}
			}
		}
	}
	
	function addPrfrdEmpinTable()
	{
		if('${groupIdList}'!='')
		{
			var groupIdLst1 = '${groupIdList}';
			new1 = groupIdLst1.split('[');
		    new2 = new1[1].split(']');
		    var groupIdList = new2[0].split(', ');
			
			var groupNameList1 = '${groupNameList}';
			new1 = groupNameList1.split('[');
		    new2 = new1[1].split(']');
		    groupNameList = new2[0].split(', ');
		    
			var userInfoList1 = '${userInfoList}';
			new1 = userInfoList1.split('[');
		    new2 = new1[1].split(', ;]');
		    var userInfoList = new2[0].split(', ;, ');
		    
		    if(groupIdList != '')
		    {
				for(var i=0;i<groupIdList.length;i++)
				{
					var row = document.getElementById("tab_new").insertRow();
					row.setAttribute("id",i);
					var cell1=row.insertCell(0);
					var cell2=row.insertCell(1);
					var cell3=row.insertCell(2);
		
					cell1.innerHTML='<input type="radio" name="groupSelect" id="' + groupIdList[i] +'" onclick="getGroupId(this)" />';
					cell2.innerHTML=groupNameList[i];
					var userNamePostId = userInfoList[i].split(', ');
					for(var j=0; j<userNamePostId.length; j=j+2)
					{
						cell3.innerHTML+='<input type="checkbox" id="' + userNamePostId[j+1] + '" onclick="getPostId(this)" />' + userNamePostId[j] + '<br />';
					}
				}
			}
		}
	}
	
	function update()
	{
		if(grpId != 0)
		{
			var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
			var url='hdiits.htm?actionFlag=DeptSearchData&upd=true&groupId='+grpId;
			var docWindow = window.open (url,"Document",urlstyle); 
			docWindow.resizeTo(screen.availWidth,screen.availHeight);
			docWindow.moveTo(0,0);  
		}
		else
			alert('Please select a group first...');
	}
	
	function deleteGrp()
	{
		if(grpId!=0)
		{
			var url='hdiits.htm?actionFlag=deletePreferredGroup&groupId='+grpId;
			document.forms[0].action=url;
			document.forms[0].submit();
		}	
		else
			alert('Please select a group first...');
	}
	
	function getGroupId(obj)
	{
		grpId = obj.id;
	}
	
	function addGroup()
	{
		document.getElementById('tab_add').style.visibility = 'visible';
		document.getElementById('grpName').focus();
	}
	
	function addGrpMember()
	{
		if(document.getElementById('grpName').value != '')
		{
			for(var i=0; i<groupNameList.length; i++)
			{
				if(groupNameList[i] == document.getElementById('grpName').value)
				{
					alert('A group with same name already exist... Please enter other name...');
					document.getElementById('grpName').focus();
					return false;
				}
			}
			var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
			var url='hdiits.htm?actionFlag=addPreferredGroup&upd=true&groupName='+document.getElementById('grpName').value;
			var docWindow = window.open (url,"Document",urlstyle); 
			docWindow.resizeTo(screen.availWidth,screen.availHeight);
			docWindow.moveTo(0,0);
		}
	}
	
	function sendToPost()
	{
		if(postIdList.length != 0)
			alert(postIdList);
		else
			alert('Please select at least one Employee...');
	}
	
	addPrfrdEmpinTable();
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>