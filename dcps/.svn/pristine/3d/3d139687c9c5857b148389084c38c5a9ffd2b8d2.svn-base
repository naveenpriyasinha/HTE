<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>		
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="orgDeptMstList" value="${resValue.orgDeptMstList}"></c:set>

<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/acl/commonRoleMapping.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/acl/newPostRoleMapping.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<script type="text/javascript">

var cmnRoleMappingLabel = new Array();
cmnRoleMappingLabel[0]="<fmt:message key='ACTIVE' bundle='${AccessControlLabels}'/>";
cmnRoleMappingLabel[1]="<fmt:message key='DEACTIVE' bundle='${AccessControlLabels}'/>";
cmnRoleMappingLabel[2]="<fmt:message key='EDIT' bundle='${AccessControlLabels}'/>";
cmnRoleMappingLabel[3]="<fmt:message key='RESET' bundle='${AccessControlLabels}'/>";
cmnRoleMappingLabel[4]="<fmt:message key='DELETE' bundle='${AccessControlLabels}'/>";
cmnRoleMappingLabel[5]="<fmt:message key='SEARCH_MSG' bundle='${AccessControlLabels}'/>";

var postRoleMappingLabel = new Array();
postRoleMappingLabel[0]="<fmt:message key='ROLEALERT' bundle='${AccessControlLabels}'/>";
function saveData()
{
	var varPostId = document.getElementById("postId").value;
	if(varPostId != "" && varPostId != "0" && varPostId != null && varPostId != 'null')
	{
		submitFormData();
	}
}
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="frmPostRole" validate="true" method="POST" action="hdiits.htm">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
         	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption id="temp1" captionid="PostTitle"  bundle="${AccessControlLabels}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent">

<table align="center">
	<tr>
		<td align="center"><b><hdiits:caption captionid="SELECT_POST_NM" bundle="${AccessControlLabels}"/></b></td>
		<td>
			<hdiits:text name="txtPostName" id="txtPostName" size="35"/>
			<img style='cursor:hand' id='img_txtPostName' src="themes/defaulttheme/images/taglib/search_icon.gif" onClick="searchSelectedPost()">
		</td>
	</tr>
</table>
<br><br>
<table id='empIno' name="empIno" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="display: none;border-collapse: collapse;background-color: #F0F4FB" >
	<tr>		
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="EMP_NAME" bundle="${AccessControlLabels}"/></b></td>
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="USER_NAME" bundle="${AccessControlLabels}"/></b></td>
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="DEPT_NAME" bundle="${AccessControlLabels}"/></b></td>
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="LOC_NAME" bundle="${AccessControlLabels}"/></b></td>
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="DSGN_NAME" bundle="${AccessControlLabels}"/></b></td>
		<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="BRNCH_NAME" bundle="${AccessControlLabels}"/></b></td>
	</tr>
	<tr>		
		<td align="center" id="empName"><label></label></td>
		<td align="center" id="userName"><label></label></td>
		<td align="center" id="department"><label></label></td>
		<td align="center" id="location"><label></label></td>
		<td align="center" id="designation"><label></label></td>
		<td align="center" id="branch"><label></label></td>
	</tr>	
</table>
<br>
<table align="center">
<tr><td align="center"><hdiits:button id="getRoles" name="getRoles" type="button" captionid="GT_ROLES" bundle="${AccessControlLabels}" onclick="searchRoles()" /></td></tr>
</table>
<br><br>

<table width="100%" id="postRoleTable" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE" align="center">
	<tr>
		<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="Role_Name" bundle="${AccessControlLabels}"/></td>
		<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="Role_Desc" bundle="${AccessControlLabels}"/></td>
		<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="STATUS" bundle="${AccessControlLabels}"/></td>
		<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="ACTION" bundle="${AccessControlLabels}"/></td>
	</tr>
</table>

<br><br>
<table width="100%" align="center" id="btnTable" style="display: none;">
	<tr><td align="center">
		<hdiits:button name="submitData" type="button" captionid="SUBMIT" bundle="${AccessControlLabels}" onclick="submitFormData()"/>
		<hdiits:button name="close" type="button" captionid="CLOSE" bundle="${AccessControlLabels}" onclick="closeWindow()"/>
	</td></tr>
</table>

<hdiits:hidden name="postId"/>

<script language="javascript">
	initializetabcontent("maintab");
	document.getElementById("getRoles").disabled=true;
	//document.getElementById("empIno").style.display='none';
	document.getElementById("tcontent1").focus();
</script>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="" />
</div>
</div>
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>