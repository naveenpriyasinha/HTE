<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link rel="stylesheet" href="latest/themes/hdiits/hdiits.css" type="text/css" />
<script type="text/javascript" src='<c:url value="/script/common/tagLibValidation.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/commonfunctions.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/login/validation.js"/>'></script>
<fmt:setBundle basename="resources.acl.acl" var="aclLabels" scope="request"/>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>				
<script type="text/javascript">
function searchUser()
{
	document.frmUserUnlock.method="POST";
	document.frmUserUnlock.action="./hdiits.htm?actionFlag=searchUserByUserName&Flag=true";
	document.frmUserUnlock.submit();
}

function deleteAllUser()
{
	document.frmUserUnlock.method="POST";
	document.frmUserUnlock.action="./hdiits.htm?actionFlag=unlockUserByAdmin&allUser=Y&userId=0";
	document.frmUserUnlock.submit();
}

function resetConfirm()
{
	var answer = confirm("Are you sure, you want to delete previous login session(s) ?");
	var userId = null;
	var radioLength =null;
	if(answer)
	{
		radioLength = document.frmUserUnlock.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.frmUserUnlock.rdoLoc.checked)
			{
				userId = document.frmUserUnlock.rdoLoc.value;
				document.frmUserUnlock.method="POST";
				document.frmUserUnlock.action="./hdiits.htm?actionFlag=unlockUserByAdmin&userId="+userId;
				document.frmUserUnlock.submit();
			}	
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.frmUserUnlock.rdoLoc.length; rdoIndex++)
			{ 
				if (document.frmUserUnlock.rdoLoc[rdoIndex].checked)
				{
					userId = document.frmUserUnlock.rdoLoc[rdoIndex].value;
					document.frmUserUnlock.method="POST";
					document.frmUserUnlock.action="./hdiits.htm?actionFlag=unlockUserByAdmin&userId="+userId;
					document.frmUserUnlock.submit();
				}
			}
		}
	}
	else
	{
		radioLength = document.frmUserUnlock.rdoLoc.length;
		if(radioLength == undefined)
		{
			document.frmUserUnlock.rdoLoc.checked = false;
		}
		else
		{
			for (var radoIndex = 0; radoIndex < document.frmUserUnlock.rdoLoc.length; radoIndex++)
			{ 
				if (document.frmUserUnlock.rdoLoc[radoIndex].checked)
				{
					document.frmUserUnlock.rdoLoc[radoIndex].checked = false;
				}
			}
		}
	}
}
</script>
<hdiits:form name="frmUserUnlock" method="POST"  validate="true">  
<div id="tabmenu"> <ul id="maintab" class="shadetabs"> 
							<li class="selected" >  <a href="#" rel="tcontent1"> Single User, Single Login </a> </li>
                   </ul>
</div>  
<div class="tabcontentstyle" > 
<div id="tcontent1" class="tabcontent" > 
<table width="100%" align="center" >
 <tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="res.userName" bundle="${aclLabels}" /></td>
		<td class="fieldLabel" width="25%"><hdiits:text captionid="res.userName" bundle="${aclLabels}" name="txtuserName"/></td>
		<td class="fieldLabel" width="25%"></td>
		<td class="fieldLabel" width="25%"></td>
</tr>
<tr></tr>
<tr>
		<td colspan="4" align="center">
		<hdiits:button type="button" captionid="res.SEARCH" bundle="${aclLabels}" name="btnSearch" id="btnSearch" onclick="searchUser()"/>
		<hdiits:button type="button" captionid="res.SEARCHAll" bundle="${aclLabels}" name="btnSearch2" id="btnSearch2" onclick="deleteAllUser()"/>
		</td>
</tr>
</table>
<display:table  pagesize="10" name="${resultMap.ResetPswdCustomlist}" id="row" requestURI="" style="width:100%" >
	<display:column class="tablecelltext" titleKey="res.SELECT" headerClass="datatableheader" >
			<hdiits:radio name="rdoLoc" id="rdoLoc" value="${row.userId}" onclick="resetConfirm()"></hdiits:radio>
	</display:column>
	<display:column class="tablecelltext"  titleKey="res.empName" headerClass="datatableheader" >${row.name}</display:column>
	<display:column class="tablecelltext"  titleKey="res.location" headerClass="datatableheader"  >${row.location}</display:column>
	<display:column class="tablecelltext"  titleKey="res.post" headerClass="datatableheader"  >${row.post}</display:column>
	</display:table>
</div>
</div>
<script type="text/javascript">initializetabcontent("maintab");</script>
</hdiits:form>