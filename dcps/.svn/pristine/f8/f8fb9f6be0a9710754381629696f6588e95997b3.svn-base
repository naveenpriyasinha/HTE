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
<hdiits:hidden name="fromPreferredList" default="true" />
<hdiits:hidden name="postIdArray" />
<hdiits:hidden name="empArray" />
<hdiits:hidden name="locArray" />
<hdiits:hidden name="desgnArray" />
	<table align="center">
		<tr>
			<td>
				<b><fmt:message key="WF.PRFRDGRP" bundle="${wfLables}"/></b>
			</td>
		</tr>
	</table>
	
	<table id="tab_new" border="1" align="center" class="tabtable">
		<thead>
			<tr>
				<th class="datatableheader">Select Group</th>
				<th class="datatableheader">Group Name</th>
				<th class="datatableheader">Group Members</th>
				<th class="datatableheader">Select</th>
			</tr>
		</thead>
	</table>
	
	<table id="tab_other" border="1" align="center" class="tabtable" style="display: none;">
		<thead>
			<tr>
				<th class="datatableheader">Employee Name</th>
			</tr>
		</thead>
	</table>
	
		<br>
	<table align="center">
		<tr>
			<td><hdiits:button name="addBtn" type="button" onclick="addGroup()" value="Add Group" /></td>
			<td><hdiits:button name="sendBtn" type="button" onclick="sendToPost()" value="Send Job" /></td>
<!--		<td><hdiits:resetbutton name="resetBtn" type="button" value="Reset" onclick="confirmReset()"/></td> -->
			<td><hdiits:button name="sendToOtherBtn" type="button" onclick="sendToOtherEmp()" value="Send To Other Employee" /></td>
		</tr>
	</table>
	<br>
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
	var userInfoList = new Array();
	var groupIdList = new Array();
	var postGroupIdList = new Array();
	
	function checkForDuplicatePost(postId)
	{
		for(var i=0; i<postIdList.length; i++)
		{
			if(postIdList[i] == postId)
				return true;
		}
		return false;
	}
	
	function getPostId(check, cnt)
	{
		var postGroupId = check.id.split(';');
		if(check.checked==true)
		{
			if(checkForDuplicatePost(postGroupId[1]) == false)
			{
				postIdList.push(postGroupId[1]);
				postGroupIdList.push(check.id);
			}
			else
			{
				alert('This Employee is already selected...');
				document.getElementById(check.id).checked = false;
			}
			
			var len = userInfoList[cnt].split(', ').length / 2;
			var j = 0;
			for(var i=0; i<postGroupIdList.length; i++)
			{
				var pgId = postGroupIdList[i].split(';');
				if(pgId[0] == postGroupId[0])
					j++;
			}
			if(j == len)
				document.getElementById(postGroupId[0]).checked = true;
		}
		else
		{
			if(document.getElementById(postGroupId[0]).checked == true)
			{
				document.getElementById(postGroupId[0]).checked = false;
			}
				
			for(var i=0; i<postIdList.length; i++)
			{
				if(postIdList[i] == postGroupId[1])
				{
					postIdList.splice(i, 1);
					postGroupIdList.splice(i, 1);
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
		    groupIdList = new2[0].split(', ');
			
			var groupNameList1 = '${groupNameList}';
			new1 = groupNameList1.split('[');
		    new2 = new1[1].split(']');
		    groupNameList = new2[0].split(', ');
		    
			var userInfoList1 = '${userInfoList}';
			new1 = userInfoList1.split('[');
		    new2 = new1[1].split(', ;]');
		    userInfoList = new2[0].split(', ;, ');
		    
		    if(groupIdList != '')
		    {
				for(var i=0;i<groupIdList.length;i++)
				{
					var row = document.getElementById("tab_new").insertRow();
					row.setAttribute("id",i);
					var cell1=row.insertCell(0);
					var cell2=row.insertCell(1);
					var cell3=row.insertCell(2);
					var cell4=row.insertCell(3);

					var userNamePostId = userInfoList[i].split(', ');
					cell1.innerHTML='<input type=checkbox id=' + groupIdList[i] +' onclick=getGroupId(this,' + i + ') />';
					cell2.innerHTML=groupNameList[i];
					for(var j=0; j<userNamePostId.length; j=j+2)
					{
						cell3.innerHTML+='<input type=checkbox id=' + groupIdList[i] + ';' + userNamePostId[j+1] + ' onclick=getPostId(this,' + i + ') />' + userNamePostId[j] + '<br />';
					}
					cell4.innerHTML='<a href=javascript:updateGrp(' + groupIdList[i] + ')>Update</a>';
					cell4.innerHTML+='<br />';
					cell4.innerHTML+='<a href=javascript:deleteGrp(' + groupIdList[i] + ')>Delete</a>';
				}
			}
		}
	}
	
	function getGroupId(check, cnt)
	{
		if(check.checked==true)
		{
			var userNamePostId = userInfoList[cnt].split(', ');
			for(var i=0; i<userNamePostId.length; i=i+2)
			{
				if(checkForDuplicatePost(userNamePostId[i+1]) == false)
				{
					document.getElementById(check.id + ';' + userNamePostId[i+1]).checked = true;
					postIdList.push(userNamePostId[i+1]);
					postGroupIdList.push(check.id + ';' + userNamePostId[i+1]);
				}
				else
				{
					for(var j=0; j<postGroupIdList.length; j++)
					{
						var pgId = postGroupIdList[j].split(';');
						if(pgId[1] == userNamePostId[i+1])
						{
							if(pgId[0] != check.id)
							{
								document.getElementById(check.id).checked = false;
								break;
							}
						}
					}
				}
			}
		}
		else
		{
			for(var i=0; i<postGroupIdList.length; i++)
			{
				var gpId = postGroupIdList[i].split(';');
				if(gpId[0] == check.id)
				{
					document.getElementById(postGroupIdList[i]).checked = false;
					postIdList.splice(i, 1);
					postGroupIdList.splice(i, 1);
					i--;
				}
			}
		}
	}
	
	function sendToPost()
	{
		if(postIdList.length != 0)
		{
			alert(postIdList);
			alert(postGroupIdList);
		}
		else
			alert('Please select at least one Employee...');
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
				if(groupNameList[i].toLowerCase() == document.getElementById('grpName').value.toLowerCase())
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
		else
		{
			alert('Please enter Group Name...');
			document.getElementById('grpName').focus();
		}
	}
	
	function updateGrp(grpId)
	{
		var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
		var url='hdiits.htm?actionFlag=DeptSearchData&upd=true&groupId='+grpId;
		var docWindow = window.open (url,"Document",urlstyle); 
		docWindow.resizeTo(screen.availWidth,screen.availHeight);
		docWindow.moveTo(0,0);  
	}
	
	function deleteGrp(grpId)
	{
		if(confirm("Are you sure want to delete this Group?"))
		{
			var url='hdiits.htm?actionFlag=deletePreferredGroup&groupId='+grpId;
			document.forms[0].action=url;
			document.forms[0].submit();
		}
	}

	function confirmReset()
	{
		if(confirm("Are You sure want to Reset all the Selection...") == true)
			return true;
		else
			return false;
	}
	
	function sendToOtherEmp()
	{
		var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight"
		var url='hdiits.htm?actionFlag=DeptSearchData';
		var docWindow = window.open (url,"Document",urlstyle); 
		docWindow.resizeTo(screen.availWidth,screen.availHeight);
		docWindow.moveTo(0,0); 
	}
	
	function useSelectedEmployees()
	{
		document.getElementById('tab_other').style.display = '';
		var postIds = document.getElementById('postIdArray').value.split(',');
		var empName = document.getElementById('empArray').value.split(',');
		var desgnName = document.getElementById('desgnArray').value.split(',');

		for(var i=0; i<postIds.length; i++)
		{
			if(checkForDuplicatePost(postIds[i]) == false)
			{
				var row = document.getElementById("tab_other").insertRow();
				row.setAttribute("id", 'other' + i);
				var cell1=row.insertCell(0);
				cell1.innerHTML = empName[i] + ' (' + desgnName[i] + ')';
				postIdList.push(postIds[i]);
			}
		}
		alert(postIdList);
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