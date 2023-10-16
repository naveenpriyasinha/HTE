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
	
	<table id="tab_new" border="1" align="center" class="tabtable">
		<thead>
			<tr>
				<th class="datatableheader">Select Group</th>
				<th class="datatableheader">Group Name</th>
				<th class="datatableheader">Group Members</th>
			</tr>
		</thead>
	</table>
	
	<br>
	<table align="center">
		<tr>
			<td><hdiits:button name="sendBtn" type="button" onclick="javascript:window.close();" value="Close" /></td>
		</tr>
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
		for(var i=0; i<window.opener.postIdArray.length; i++)
		{
			if(window.opener.postIdArray[i] == postId)
				return true;
		}
		return false;
	}
	
	var temp1;
	function getPostId(check, cnt)
	{
		var postGroupId = check.id.split(';');
		if(check.checked==true)
		{
			if(checkForDuplicatePost(postGroupId[1]) == false)
			{
				var element=window.opener.document.frames['displayEmpFrame'].document.createElement('option');
				var str1 = document.getElementById(check.id).value;
				var str2 = str1.replace(' (', ', ');
				var str3 = str2.replace(')', '');
				element.text=str3;
				element.value=postGroupId[1];
				window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id').add(element);
				postIdList.push(postGroupId[1]);
				window.opener.postIdArray.push(postGroupId[1]);
				postGroupIdList.push(check.id);
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
				alert('This Employee is already selected...');
				document.getElementById(check.id).checked = false;
			}
		}
		else
		{
			var del = 0;
			var combo1 = window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id');
			var combo2 = window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice2_id');
			for(var k=0; k<combo1.options.length; k++)
			{
				if(combo1.options[k].value == postGroupId[1])
				{
					del = 1;
					if(document.getElementById(postGroupId[0]).checked == true)
					{
						document.getElementById(postGroupId[0]).checked = false;
					}
					combo1.remove(k);
					for(var i=0; i<window.opener.postIdArray.length; i++)
					{
						if(window.opener.postIdArray[i] == postGroupId[1])
						{
							window.opener.postIdArray.splice(i, 1);
							break;
						}
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
					break;
				}
			}
			if(del == 0)
			{
				for(var k=0; k<combo2.options.length; k++)
				{
					var temp1 = combo2.options[k].value.split(',');
					if(temp1[0] == postGroupId[1])
					{
						del = 0;
						break;
					}
					del = 1;
				}
				if(del == 0)
					check.checked=true;
				else
				{
					if(document.getElementById(postGroupId[0]).checked == true)
					{
						document.getElementById(postGroupId[0]).checked = false;
					}
					for(var i=0; i<window.opener.postIdArray.length; i++)
					{
						if(window.opener.postIdArray[i] == postGroupId[1])
						{
							window.opener.postIdArray.splice(i, 1);
							break;
						}
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

					var userNamePostId = userInfoList[i].split(', ');
					cell1.innerHTML='<input type=checkbox id=' + groupIdList[i] +' onclick=getGroupId(this,' + i + ') />';
					cell2.innerHTML=groupNameList[i];
					for(var j=0; j<userNamePostId.length; j=j+2)
					{
						cell3.innerHTML+='<input type=checkbox value="' + userNamePostId[j] + '" id=' + groupIdList[i] + ';' + userNamePostId[j+1] + ' onclick=getPostId(this,' + i + ') />' + userNamePostId[j] + '<br />';
					}
				}
			}
		}
	}
	
	function getGroupId(check, cnt)
	{
		if(check.checked==true)
		{
			var userNamePostId = userInfoList[cnt].split(', ');
			var j = 0;
			for(var i=0; i<userNamePostId.length; i=i+2)
			{
				if(checkForDuplicatePost(userNamePostId[i+1]) == false)
				{
					var element=window.opener.document.frames['displayEmpFrame'].document.createElement('option');
					var str1 = document.getElementById(check.id + ';' + userNamePostId[i+1]).value;
					var str2 = str1.replace(' (', ', ');
					var str3 = str2.replace(')', '');
					element.text=str3;
					element.value=userNamePostId[i+1];
					window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id').add(element);
					document.getElementById(check.id + ';' + userNamePostId[i+1]).checked = true;
					window.opener.postIdArray.push(userNamePostId[i+1]);
					postIdList.push(userNamePostId[i+1]);
					postGroupIdList.push(check.id + ';' + userNamePostId[i+1]);
					j++;
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
			if(j!=userNamePostId.length/2)
				check.checked=false;
		}
		else
		{
			var del = 0;
			var get = 0;
			var combo1 = window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id');
			var combo2 = window.opener.document.frames['displayEmpFrame'].document.getElementById('dd_choice2_id');
			for(var i=0; i<postGroupIdList.length; i++)
			{
				var gpId = postGroupIdList[i].split(';');
				if(gpId[0] == check.id)
				{
					for(var k=0; k<combo1.options.length; k++)
					{
						if(combo1.options[k].value == gpId[1])
						{
							del++;
							combo1.remove(k);
							document.getElementById(postGroupIdList[i]).checked = false;
							postIdList.splice(i, 1);
							postGroupIdList.splice(i, 1);
							i--;
							for(var j=0; j<window.opener.postIdArray.length; j++)
							{
								if(window.opener.postIdArray[j] == gpId[1])
								{
									window.opener.postIdArray.splice(j, 1);
									break;
								}
							}
							break;
						}
					}
				}
			}
			for(var i=0; i<postGroupIdList.length; i++)
			{
				var gpId = postGroupIdList[i].split(';');
				if(gpId[0] == check.id)
				{
					for(var k=0; k<combo2.options.length; k++)
					{
						var temp1 = combo2.options[k].value.split(',');
						if(temp1[0] == gpId[1])
						{
							get = 1;
							break;
						}
						get = 0;
					}
					if(get == 0)
					{
						document.getElementById(postGroupIdList[i]).checked = false;
						postIdList.splice(i, 1);
						postGroupIdList.splice(i, 1);
						i--;
						for(var j=0; j<window.opener.postIdArray.length; j++)
						{
							if(window.opener.postIdArray[j] == gpId[1])
							{
								window.opener.postIdArray.splice(j, 1);
								break;
							}
						}
						del++;
					}
				}
			}
			if(del==0)
				check.checked=true;
		}
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