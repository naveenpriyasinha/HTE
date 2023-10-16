<% 
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.acl.AclRole" var="aclRoleLables" scope="request" />
<fmt:setBundle basename="resources.common.MstScrLables" var="mstScrLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="aclRoleDtlsList" value="${resValue.aclRoleDtlsList}"></c:set>
<c:set var="multiLang" value="${resValue.multiLang}"></c:set>
<c:set var="roleName" value="${resValue.roleName}"></c:set>
<c:set var="roleDesc" value="${resValue.roleDesc}"></c:set>
<c:set var="isCheckbox" value="${param.checkbox}"></c:set>
<c:set var="searchTypeValue" value="${resValue.searchType}"></c:set>
<html>
<head> 
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript">
function addNewEntry()
{
	showProgressbar();
	document.forms[0].action='hdiits.htm?actionFlag=addAdminRoleDtl';
	document.forms[0].submit();
}
function editRoleDtls(reqId)
{
	showProgressbar();
	document.forms[0].action="hdiits.htm?actionFlag=editAdminAclRoleData&reqId="+reqId;
	document.forms[0].submit();		
}
function deleteData()
{	
	var isChecked = false;
	if(document.forms[0].deletedata != null)//added by uzma
	{
		for (var i = 0; i < document.forms[0].deletedata.length; i++) 
		{
	   			if (document.forms[0].deletedata[i].checked) 
	   			{
	     			isChecked = true;
	  			}
		}
		if(document.frmAdminShowRole.deletedata.checked)
		{	
			isChecked = true;
		}
		if(isChecked )
		{
			var answer=confirm("<fmt:message key='MstScr.confirmDelete' bundle='${mstScrLables}' />");
			if(answer)
			{
				showProgressbar();
				document.forms[0].action='hdiits.htm?actionFlag=deleteAdminAclRoleData';
				document.forms[0].submit();
			}
		}
		else
		{
			alert("Please select the checkbox");
		}
	}
}
function searchPost()
{
	showProgressbar();
	if("${isCheckbox}"==true || "${isCheckbox}"=='true' )
	{
		document.frmAdminShowRole.action='hdiits.htm?actionFlag=showAdminRoleDtl&checkbox=true';
	}
	else
	{
		document.frmAdminShowRole.action='hdiits.htm?actionFlag=showAdminRoleDtl';
	}
	document.frmAdminShowRole.submit();
}


function selectRoles()
{
	var formElementsLength = document.forms[0].elements.length;
	var noOfRowSelected = 0;
	var selectedRowIndex;
	var selectedRowIndexArray=new Array();
	var selectedRoleIdArray=new Array();	  
	var selectedRoleNameArray=new Array();
	var selectedRoleDescArray=new Array();
	for(var iElement=0; iElement<formElementsLength; iElement++)
	{
		if(document.forms[0].elements[iElement].type == "checkbox")
		{
			if(document.forms[0].elements[iElement].checked == true)
			{
				noOfRowSelected= 1;
				selectedRowIndex = iElement;
				selectedRowIndexArray.push(selectedRowIndex);
			}
		}
	}
  	if (noOfRowSelected == 0)
    {
    	alert("Please select any Role");
    	return false;
    }
	for(var i=0; i<selectedRowIndexArray.length; i++)
	{
	  	selectedRoleIdArray.push(document.forms[0].elements[selectedRowIndexArray[i]].value);
		selectedRoleNameArray.push(document.forms[0].elements[selectedRowIndexArray[i]].parentNode.parentNode.childNodes[2].innerHTML);
		selectedRoleDescArray.push(document.forms[0].elements[selectedRowIndexArray[i]].parentNode.parentNode.childNodes[3].innerHTML);
	}
	window.opener.parent.addRequestForSelectedRoleDtls(selectedRoleIdArray,selectedRoleNameArray,selectedRoleDescArray);//Calling for selecting Role for selected user for userRoleMapping
	window.close();
	return true;
}
function getHomePage()
{
	showProgressbar();
	document.forms[0].action="hdiits.htm?actionFlag=getHdiitsHomePage";
	document.forms[0].submit();
}
</script>
</head>
<body onkeypress="return checkSpecialCharacter(event)">
<c:set value="1" var="i"></c:set>
<hdiits:form name="frmAdminShowRole" action="" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="admin.roleMst" bundle="${aclRoleLables}"></fmt:message>
		</b></a></li>		
	</ul>
	</div>
	<div id="createPost" name="createPost" class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table width="70%" align="center">
		<tr>
			<td align="right"><hdiits:caption captionid="MstScr.searchBy" bundle="${mstScrLables}"/>
			</td>
			<td align="center">
				<hdiits:select id="searchType" name="searchType" captionid="MstScr.searchBy" bundle="${mstScrLables}" sort="false" default="${searchTypeValue}">
					<hdiits:option value="RoleName"><fmt:message key="admin.RoleName" bundle="${aclRoleLables}"></fmt:message></hdiits:option>
					<hdiits:option value="RoleShortName"><fmt:message key="admin.RoleShortName" bundle="${aclRoleLables}"></fmt:message></hdiits:option>
					<hdiits:option value="RoleDesc"><fmt:message key="admin.RoleDesc" bundle="${aclRoleLables}"></fmt:message></hdiits:option>
				</hdiits:select>
			</td>
			<td align="center">
				<hdiits:text id="txtRoleName" name="txtRoleName" captionid="admin.RoleName" bundle="${aclRoleLables}" default="${roleName}"/>
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td align="center">
				<hdiits:button name="btnSearch" id="btnSearch" type="button" captionid="admin.Search" bundle="${aclRoleLables}" onclick="searchPost()"/>
			</td>
		</tr>
	</table>	
	<br>
	<c:set var="checkAll">
  			<hdiits:checkbox name="allbox" captionid="MstScr.selectClear" value="" bundle="${mstScrLables}" onclick="selectAllClearAll(this)" />
	</c:set>
	
	<display:table pagesize="15" name="${aclRoleDtlsList}" id="row"  export="false" offset="1" requestURI="hrms.htm?actionFlag=showAdminRoleDtl" style="width:100%" decorator="com.tcs.sgv.acl.decorator.AdminAclRoleDtlDecorator">
	
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
			
		<c:if test="${isCheckbox eq true}">
			<display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center" >
				<hdiits:checkbox id="selcheckBox" name="selcheckBox" value="${row.column[0]}"/>
			</display:column>
			<display:column style="text-align: center;" class="tablecelltext" titleKey="srNumber" headerClass="datatableheader">
			<c:out value="${i}"></c:out>
			</display:column>
			<display:column style="text-align: center;" class="tablecelltext" sortable="true" titleKey= "admin.RoleName" headerClass="datatableheader" value="${row.column[1]}"/>
		</c:if>
		
		<c:if test="${isCheckbox ne true}">
			<display:column style="text-align: center;width:15%" class="tablecelltext" title="${checkAll}" headerClass="datatableheader" property="checkBox">	
			</display:column>
			<display:column style="text-align: center;" class="tablecelltext" property="link2"  titleKey= "admin.RoleName" headerClass="datatableheader" value="${row.column[1]}"/>
		</c:if>
		<display:column style="text-align: center;" class="tablecelltext"  titleKey= "admin.RoleShortName" headerClass="datatableheader" value="${row.column[2]}"/>
	 	<display:column style="text-align: center;" class="tablecelltext"  titleKey= "admin.RoleDesc" headerClass="datatableheader" value="${row.column[3]}"/>
	   	<c:choose>
			<c:when test="${row.column[4] == '1'}">
				<display:column style="text-align: center;" class="tablecelltext"  titleKey= "MstScr.Status" headerClass="datatableheader">
					<fmt:message key="MstScr.Active" bundle="${mstScrLables}"></fmt:message>
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column style="text-align: center;" class="tablecelltext" titleKey= "MstScr.Status" headerClass="datatableheader">
					<fmt:message key="MstScr.Deactive" bundle="${mstScrLables}"></fmt:message>
				</display:column>
			</c:otherwise>
		</c:choose>
		<display:setProperty name="export.pdf" value="true" />
		<c:set var="i" value="${i+1}"></c:set>
	</display:table>
		
	<table width="100%">
		<tr align="center" width="100%">
			<td align="center" width="100%">
				<c:if test="${isCheckbox ne true}">
					<hdiits:button name="addNewEntry_button" captionid="admin.AddEntry" bundle="${aclRoleLables}" onclick="addNewEntry()" type="button"/>
					<hdiits:button captionid="admin.Delete" bundle="${aclRoleLables}" onclick="deleteData()" name="cmdDel2" type="button"/>
					<hdiits:button captionid="CLOSE" bundle="${aclRoleLables}" onclick="closeWindow()" name="closeBtn" type="button"/>
				</c:if>
				<c:if test="${isCheckbox eq true}">
					<hdiits:button name="btnOk" type="button" captionid="SUBMIT" bundle="${aclRoleLables}" onclick="selectRoles();" />
					<hdiits:button captionid="CLOSE" bundle="${aclRoleLables}" onclick="window.close();" name="closeBtn" type="button"/>
				</c:if>
				
			</td>
		</tr>
	</table>
</div>
</div>
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">	
	initializetabcontent("maintab");
	document.getElementById("tcontent1").focus();
</script>
<hdiits:hidden id="multiLang" name="multiLang" default="${multiLang}"/>
<hdiits:hidden id="multiLangHdn" name="multiLangHdn" default="${multiLang}"/>	

</hdiits:form>
</body>
</html>
<%
}catch(Exception e) {e.printStackTrace();}
%>
