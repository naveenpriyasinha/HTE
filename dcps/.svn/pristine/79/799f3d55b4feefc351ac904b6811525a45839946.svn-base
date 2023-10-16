<%
try 
{
%>
<%@ include file="../core/include.jsp"%>
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
				<th class="datatableheader">Sr No</th>
				<th class="datatableheader">Group Name</th>
				<th class="datatableheader">Group Members</th>
				<th class="datatableheader">Select</th>
			</tr>
		</thead>
	</table>
	
	<br>
	<table align="center">
		<tr>
			<td><hdiits:button name="addBtn" type="button" onclick="addGroup()" value="Add Group" /></td>
			<td><hdiits:button name="closeBtn" type="button" onclick="javascript:window.close()" value="Close" /></td>
		</tr>
	</table>
	<br>
	<table id="tab_add" border="1" align="center" class="tabtable" style="visibility: hidden">
		<thead>
		<tr><th class="datatableheader" colspan="3"> Add New Group </th></tr>
			<tr>
				<td>Enter Group Name:</td>
				<td><hdiits:text name="grpName" id="grpName"/></td>
				<td><hdiits:button name="addMember" type="button" onclick="addGrpMember()" value="Add Group Members" /></td>
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
					cell1.innerHTML=i+1;
					cell2.innerHTML=groupNameList[i];
					for(var j=0; j<userNamePostId.length; j=j+2)
					{
						if(j!=0)
							cell3.innerHTML+=', ';
						cell3.innerHTML+= userNamePostId[j];
					}
					cell4.innerHTML ='<a href=javascript:updateGrp(' + groupIdList[i] + ')>Update</a>';
					cell4.innerHTML+='<br />';
					cell4.innerHTML+='<a href=javascript:deleteGrp(' + groupIdList[i] + ')>Delete</a>';
				}
			}
		}
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

	addPrfrdEmpinTable();
</script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>