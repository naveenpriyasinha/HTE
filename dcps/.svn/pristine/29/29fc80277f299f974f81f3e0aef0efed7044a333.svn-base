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
	document.frmResetPassword.method="POST";
	document.frmResetPassword.action="./hdiits.htm?actionFlag=searchUserByUserName";
	document.frmResetPassword.submit();
}
function resetConfirm()
{
	var answer = confirm("Are you sure, you want to reset password?");
	var userId = null;
	var radioLength =null;
	if(answer)
	{
		radioLength = document.frmResetPassword.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.frmResetPassword.rdoLoc.checked)
			{
				userId = document.frmResetPassword.rdoLoc.value;
				document.frmResetPassword.method="POST";
				document.frmResetPassword.action="./hdiits.htm?actionFlag=resetPswdByAdmin&userId="+userId;
				document.frmResetPassword.submit();
			}	
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.frmResetPassword.rdoLoc.length; rdoIndex++)
			{ 
				if (document.frmResetPassword.rdoLoc[rdoIndex].checked)
				{
					userId = document.frmResetPassword.rdoLoc[rdoIndex].value;
					document.frmResetPassword.method="POST";
					document.frmResetPassword.action="./hdiits.htm?actionFlag=resetPswdByAdmin&userId="+userId;
					document.frmResetPassword.submit();
				}
			}
		}
	}
	else
	{
		radioLength = document.frmResetPassword.rdoLoc.length;
		if(radioLength == undefined)
		{
			document.frmResetPassword.rdoLoc.checked = false;
		}
		else
		{
			for (var radoIndex = 0; radoIndex < document.frmResetPassword.rdoLoc.length; radoIndex++)
			{ 
				if (document.frmResetPassword.rdoLoc[radoIndex].checked)
				{
					document.frmResetPassword.rdoLoc[radoIndex].checked = false;
				}
			}
		}
	}
}
</script>
<hdiits:form name="frmResetPassword" method="POST"  validate="true">  
<div id="tabmenu"> <ul id="maintab" class="shadetabs"> 
						<li class="selected" >  <a href="#" rel="tcontent1"> <hdiits:caption captionid="res.resetpwdtitle" bundle="${aclLabels}"/> </a> </li>
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
