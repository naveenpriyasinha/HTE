<% 
try { 
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.acl.AclRole" var="aclRoleLables" scope="request" />
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="activeFlag" value="${resValue.activeFlag}"></c:set>
<c:set var="arStatusFlag" value="${resValue.arStatusFlag}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>

<c:set var="aclRoleMstObj" value="${resValue.aclRoleMstObj}"></c:set>
<c:set var="orgRoleDetailsRlt_en" value="${resValue.orgRoleDetailsRlt_en}"></c:set>
<c:set var="orgRoleDetailsRlt_gu" value="${resValue.orgRoleDetailsRlt_gu}"></c:set>

<html>
<head>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" >
var roleArray;
var temp1="<fmt:message bundle='${aclRoleLables}' key='admin.RoleName'/>";
var temp2="<fmt:message bundle='${mstScrLables}' key='MstScr.AlreadyExist'/>";


	function removeDisabled(langId)
	{
		if(langId==1)
		{
			document.frmAdminCrtRole.rdoActiveFlag_gu[0].disabled = false;
			document.frmAdminCrtRole.rdoActiveFlag_gu[1].disabled = false;
			document.frmAdminCrtRole.rdoActiveFlag_gu[2].disabled = false;		
		}
		else if(langId==2)
		{
			document.frmAdminCrtRole.rdoActiveFlag[0].disabled = false;
			document.frmAdminCrtRole.rdoActiveFlag[1].disabled = false;
			document.frmAdminCrtRole.rdoActiveFlag[2].disabled = false;	
		}	
	}
	function copyEngToGuj()
	{
		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
		if(stausFlagVal == 1) // active
			document.frmAdminCrtRole.rdoActiveFlag_gu[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmAdminCrtRole.rdoActiveFlag_gu[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmAdminCrtRole.rdoActiveFlag_gu[2].checked = true;
			
		document.frmAdminCrtRole.rdoActiveFlag_gu[0].disabled = true;
		document.frmAdminCrtRole.rdoActiveFlag_gu[1].disabled = true;
		document.frmAdminCrtRole.rdoActiveFlag_gu[2].disabled = true;			
	}
	function copyGujToEng()
	{	
		var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
		if(stausFlagVal == 1) // active
			document.frmAdminCrtRole.rdoActiveFlag[0].checked = true;
		if(stausFlagVal == 2) // deactive
			document.frmAdminCrtRole.rdoActiveFlag[1].checked = true;
		if(stausFlagVal == 3) // delete
			document.frmAdminCrtRole.rdoActiveFlag[2].checked = true;	
					
		document.frmAdminCrtRole.rdoActiveFlag[0].disabled = true;
		document.frmAdminCrtRole.rdoActiveFlag[1].disabled = true;
		document.frmAdminCrtRole.rdoActiveFlag[2].disabled = true;	
	}
	function getCheckedRadioValue(radioName)
	{
		var radioValue = "";
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
		return 	radioValue;
	}
	function resetData()
	{
		document.frmAdminCrtRole.roleNameTxt.value = '';
		document.frmAdminCrtRole.roleShrtNametxt.value = '';
		document.frmAdminCrtRole.roleDescription.value = '';
		
		document.frmAdminCrtRole.roleNameTxt_gu.value = '';
		document.frmAdminCrtRole.roleShrtNametxt_gu.value = '';
		document.frmAdminCrtRole.roleDescription_gu.value = '';

		document.frmAdminCrtRole.rdoActiveFlag[0].checked = true;
		document.frmAdminCrtRole.rdoActiveFlag_gu[0].checked = true;
	}
	function submit_frmAdminCrtRole()
	{
		if('${langId}' == '1' || '${langId}'==1)
		{
			copyEngToGuj();
			var fieldArrayEng = new Array('roleNameTxt', 'roleShrtNametxt', 'roleDescription'); 
			var statusValidationEng = validateSpecificFormFields(fieldArrayEng);
			if(statusValidationEng)
			{				
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
				if(stausFlagVal == "")
				{
					alert("<fmt:message bundle='${aclRoleLables}' key='selActivate'/>");
					return false;
				}
				else if('${multiLang}'=='false' || '${multiLang}'==false)
				{
					removeDisabled('${langId}');
					//document.frmAdminCrtRole.action="hrms.htm?actionFlag=submitAdminRoleData";
					//document.frmAdminCrtRole.submit();
					processSubmit();
				}
				else
				{
					var fieldArrayGuj = new Array('roleNameTxt_gu', 'roleShrtNametxt_gu','roleDescription_gu'); 
					var statusValidationGuj = validateSpecificFormFields(fieldArrayGuj);
					if(statusValidationGuj)
					{
						var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
						if(stausFlagVal == "")
						{
							selectRequiredTab("rdoActiveFlag_gu");
							alert("<fmt:message bundle='${aclRoleLables}' key='selActivate'/>");
							return false;
						}
						else
						{
							removeDisabled('${langId}');
							//document.frmAdminCrtRole.action="hrms.htm?actionFlag=submitAdminRoleData";
							//document.frmAdminCrtRole.submit();
							processSubmit();
						}
					}						
				}					
			}
		}
		else if('${langId}' == '2' || '${langId}'==2)
		{
			copyGujToEng();	
			var fieldArrayGuj1 = new Array('roleNameTxt_gu', 'roleShrtNametxt_gu','roleDescription_gu'); 
			var statusValidationGuj1 = validateSpecificFormFields(fieldArrayGuj1);
			if(statusValidationGuj1)
			{
				var stausFlagVal = getCheckedRadioValue("rdoActiveFlag_gu");
				if(stausFlagVal == "")
				{
					alert("<fmt:message bundle='${aclRoleLables}' key='selActivate'/>");
					return false;
				}
				else
				{
					var fieldArrayEng1 = new Array('roleNameTxt', 'roleShrtNametxt', 'roleDescription');
					var statusValidationEng1 = validateSpecificFormFields(fieldArrayEng1);
					if(statusValidationEng1)
					{	
						var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");							
						if(stausFlagVal == "")
						{
							selectRequiredTab("rdoActiveFlag");
							alert("<fmt:message bundle='${aclRoleLables}' key='selActivate'/>");
							return false;
						}
						else					
						{
							removeDisabled('${langId}');
							//document.frmAdminCrtRole.action="hrms.htm?actionFlag=submitAdminRoleData";
							//document.frmAdminCrtRole.submit();
							processSubmit();
						}
					}						
				}
			}				
		}					
	}
	function closeCurrWindow()
	{
		showProgressbar();
		var multiLang = document.getElementById("multiLangHdn").value;
		document.frmAdminCrtRole.action = "hrms.htm?actionFlag=showAdminRoleDtl&multiLang="+multiLang;
	   	document.frmAdminCrtRole.submit();
	}
	function resetAllData()
	{
		if('${flag}'=='edit')
		{	
			globalFlagType = "E";	
			document.getElementById('editRole').style.display = '';	
			document.getElementById('editRole_gu').style.display = '';
			
			document.getElementById('newRole').style.display = 'none';	
			document.getElementById('newRole_gu').style.display = 'none';
				
			document.getElementById('roleId').value='${aclRoleMstObj.roleId}';
			
			document.getElementById('roleNameTxt').value='${orgRoleDetailsRlt_en.roleName}';
			generateInitCapName('roleNameTxt');
			document.getElementById('roleShrtNametxt').value='${orgRoleDetailsRlt_en.roleShortName}';
			generateInitCapName('roleShrtNametxt');
			document.getElementById('roleDescription').value='${orgRoleDetailsRlt_en.roleDesc}';	
			document.getElementById('roleNameTxt_gu').value='${orgRoleDetailsRlt_gu.roleName}';
			document.getElementById('roleShrtNametxt_gu').value='${orgRoleDetailsRlt_gu.roleShortName}';
			document.getElementById('roleDescription_gu').value='${orgRoleDetailsRlt_gu.roleDesc}';	
			
			var stausFlagVal = '${aclRoleMstObj.cmnLookupMstByStatus.lookupId}'; 	
			if(stausFlagVal == 1) // active
			{
				document.frmAdminCrtRole.rdoActiveFlag[0].checked = true;
				document.frmAdminCrtRole.rdoActiveFlag_gu[0].checked = true;
			}
			if(stausFlagVal == 2) // deactive
			{
				document.frmAdminCrtRole.rdoActiveFlag[1].checked = true;
				document.frmAdminCrtRole.rdoActiveFlag_gu[1].checked = true;
			}
			if(stausFlagVal == 3) // delete
			{
				document.frmAdminCrtRole.rdoActiveFlag[2].checked = true;
				document.frmAdminCrtRole.rdoActiveFlag_gu[2].checked = true;	
			}
		}
		else
		{
			resetData();
		}
	}
	//added by uzma
	function compareRoleNames()
{
	if('${multiLang}'=='true')
	{
		var roleName=document.getElementById('roleNameTxt').value;
		var roleName_gu=document.getElementById('roleNameTxt_gu').value;

		if(roleName.toLowerCase() == roleName_gu.toLowerCase())
		{
			alert("<fmt:message bundle='${aclRoleLables}' key='admin.roleEngGujNm'/>");
			return false;
		}
	}
	return true;
}
function createArrayToPassValue()
{
	 
	var flag=document.getElementById('flag').value;
	var langID = document.getElementById('langId').value;
	var multiLangVal = document.getElementById('multiLangHdn').value;

	if(flag=="add" && multiLangVal == 'false')
	{
		roleArray =new Array('flag','roleNameTxt','multiLangHdn');
	}
	else if(flag=="add" && multiLangVal == 'true')
	{
		roleArray = new Array('flag','roleNameTxt','roleNameTxt_gu','multiLangHdn');
	}
	else if(flag=="edit" && multiLangVal == 'false')
	{
		roleArray = new Array('flag','roleId','roleNameTxt','multiLangHdn');
	}
	else if(flag=="edit" && multiLangVal == 'true')	
	{
		roleArray = new Array('flag','roleId','roleNameTxt','roleNameTxt_gu','multiLangHdn');
	}
}
function processSubmit()
{
	var uniqueRoleFlag=compareRoleNames();
	if(uniqueRoleFlag)
	{
		showProgressbar();
		createArrayToPassValue();
		addOrUpdateRecord("processResponseForVaildateRole","validateRoleName",roleArray,false);
	}
}
function processResponseForVaildateRole()
{

	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);  
			var validateValue = XMLDoc.getElementsByTagName('roleNameFlag');
			var validateLang= XMLDoc.getElementsByTagName('langFlag');
			var validateFlag=validateValue[0].childNodes[0].text;
	
			if(validateFlag =='true')
			{
				document.frmAdminCrtRole.action="hrms.htm?actionFlag=submitAdminRoleData";
				document.frmAdminCrtRole.submit();
			}
			else
			{
				var langFlag=validateLang[0].childNodes[0].text;
				var temp;
				var temp3;
				hideProgressbar();
				if(langFlag =='gu')
				{
					temp="<fmt:message bundle='${mstScrLables}' key='MstScr.inGuj'/>";
					temp3="<fmt:message bundle='${mstScrLables}' key='MstScr.Guj'/>"
				}
				else if(langFlag =='en')
				{
					temp="<fmt:message bundle='${mstScrLables}' key='MstScr.inEng'/>";
					temp3="<fmt:message bundle='${mstScrLables}' key='MstScr.Eng'/>"
				}
				else if(langFlag == 'both')
				{
					temp="<fmt:message bundle='${mstScrLables}' key='MstScr.inEngGuj'/>";
					temp3="<fmt:message bundle='${mstScrLables}' key='MstScr.EngGuj'/>"
				}
				var nameExistAlert=temp1+' '+temp3+' '+temp2+' '+temp;
				if('${multiLang}'=='false')
				{
					nameExistAlert=temp1+' '+temp2;
				}
				alert(nameExistAlert);
				return false;
			}
		}
	}
}
function saveData()
{
	submit_frmAdminCrtRole();
}
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="frmAdminCrtRole" action="hrms.htm?actionFlag=submitAdminRoleData" method="post" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
		<c:if test="${langId == 1}">
			<li class="selected" id="eng"><a href="#" rel="tcontent1" ><b>
				<fmt:message key="admin.Eng" bundle="${aclRoleLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent2" onfocus="copyEngToGuj();"><b>
				<fmt:message key="admin.Gujarati" bundle="${aclRoleLables}"></fmt:message>
			</b></a></li>
		</c:if>
		<c:if test="${langId == 2}">
			<li class="selected" id="guj" style="display: block"><a href="#" rel="tcontent1"><b>
				<fmt:message key="admin.Gujarati" bundle="${aclRoleLables}"></fmt:message>
			</b></a></li>
			<li class="selected" id="eng"><a href="#" rel="tcontent2" onfocus="copyGujToEng();"><b>
				<fmt:message key="admin.Eng" bundle="${aclRoleLables}"></fmt:message>
			</b></a></li>
		</c:if>
	</ul>
	</div>		
	<div id="createPost" name="createPost" class="tabcontentstyle" >
	<c:if test="${langId == 1}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
		<table width="100%" id="newRole_gu">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${aclRoleLables}' key='NewRole'/></b></td></tr>
		</table>
		<table width="100%" id="editRole_gu" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${aclRoleLables}' key='EditRole'/></b></td></tr>
		</table>
		<table class="tabtable">				
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleName" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="roleNameTxt" id="roleNameTxt" mandatory="true" captionid="admin.RoleName" bundle="${aclRoleLables}" validation="txt.isrequired" maxlength="25" onblur="generateShortName(this,'roleShrtNametxt'); generateInitCapName('roleNameTxt')"></hdiits:text></td>
				
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleShortName" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="roleShrtNametxt" id="roleShrtNametxt" captionid="admin.RoleShortName" bundle="${aclRoleLables}" mandatory="true" maxlength="10" validation="txt.isrequired" onblur="generateInitCapName('roleShrtNametxt')"></hdiits:text></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleDescription" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="roleDescription" id="roleDescription" mandatory="true" captionid="admin.RoleDescription" bundle="${aclRoleLables}" validation="txt.isrequired" maxlength="95" cols="28"></hdiits:textarea></td>
				
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.ActiveFlag" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel" >
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" mandatory="true" />${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
				<td class="fieldLabel"></td>				
				<td class="fieldLabel"></td>
			</tr>
		</table>		
	</div>
	
	<c:if test="${langId == 1}">
		<div id="tcontent2" class="tabcontent" tabno="1">
	</c:if>
	<c:if test="${langId == 2}">
		<div id="tcontent1" class="tabcontent" tabno="0">
	</c:if>
		<table width="100%" id="newRole">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${aclRoleLables}' key='NewRole'/></b></td></tr>
		</table>
		<table width="100%" id="editRole" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${aclRoleLables}' key='EditRole'/></b></td></tr>
		</table>	
		<table class="tabtable">
			
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleName" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="roleNameTxt_gu" id="roleNameTxt_gu" mandatory="true" captionid="admin.RoleName" bundle="${aclRoleLables}" validation="txt.isrequired" maxlength="25"></hdiits:text></td>
				
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleShortName" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:text name="roleShrtNametxt_gu" id="roleShrtNametxt_gu" captionid="admin.RoleShortName" bundle="${aclRoleLables}" mandatory="true" maxlength="10" validation="txt.isrequired"></hdiits:text></td>
			</tr>
			<tr>
				<td class="fieldLabel"><b><hdiits:caption captionid="admin.RoleDescription" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel"><hdiits:textarea name="roleDescription_gu" id="roleDescription_gu" mandatory="true" captionid="admin.RoleDescription" bundle="${aclRoleLables}" validation="txt.isrequired" maxlength="95" cols="28"></hdiits:textarea></td>
				
				<td class="fieldLabel">
				<b><hdiits:caption captionid="admin.ActiveFlag" bundle="${aclRoleLables}"/></b></td>
				<td class="fieldLabel" >
					<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
						<hdiits:radio name="rdoActiveFlag_gu" value="${StatusFlagValue.lookupId}" mandatory="true" />${StatusFlagValue.lookupName}<br>										
					</c:forEach>
				</td>
				<td class="fieldLabel"></td>				
				<td class="fieldLabel"></td>
			</tr>						
		</table>
	</div>
	</div>
	<table align="center">	
		<tr>
			<td align="center">
				<c:if test="${flag ne'edit'}">
					<br><hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${aclRoleLables}" onclick="submit_frmAdminCrtRole()"></hdiits:button>
				</c:if>
				<c:if test="${flag eq 'edit'}">
					<br><hdiits:button name="Update" type="button" captionid="UPDATE" bundle="${aclRoleLables}" onclick="submit_frmAdminCrtRole()"></hdiits:button>
				</c:if>
			</td>
			<td align="center">
				<br><hdiits:button name="reset" type="button" captionid="RESET" bundle="${aclRoleLables}" onclick="resetAllData()"></hdiits:button>
			</td>
			<td align="center">
				<br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${aclRoleLables}" onclick="closeCurrWindow()"></hdiits:button>
			</td>
		</tr>
	</table>
<hdiits:hidden name="langId" id="langId" default="${langId}"></hdiits:hidden>
<hdiits:hidden name="multiLangHdn" id="multiLangHdn" default="${multiLang}"></hdiits:hidden>	
<hdiits:hidden name="flag" id="flag" default="${flag}"/>
<hdiits:hidden name="roleId" id="roleId" default=""/>		
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript">
	initializetabcontent("maintab")	;
	
</script>
</hdiits:form>
</body>
<script type="text/javascript">
	if('${multiLang}'=='false' || '${multiLang}'==false)
	{
		document.getElementById("guj").style.display='none';
		document.getElementById("eng").childNodes[0].childNodes[0].innerHTML = "Role Creation";
	}
	document.getElementById("flag").value='${flag}';	
	document.getElementById("langId").value='${langId}';
	resetAllData();
	if('${langId}' == '1')
	{
		document.getElementById('roleNameTxt').focus();
	}
	else if('${langId}' == '2')
	{
		document.getElementById('roleNameTxt_gu').focus();
	}
</script>
</html>
<%
}catch(Exception ex){ex.printStackTrace();}
%>			