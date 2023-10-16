
<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src='<c:url value="/script/acl/newRoleElement.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/acl/cmnElementMapping.js"/>'></script>
<script type="text/javascript" src='<c:url value="/script/common/addRecord.js"/>'></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels"
	scope="request" />
<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables"
	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="saveType" value="${resValue.saveType}"></c:set>
<c:set var="searchTypeVar" value="${resValue.searchType}"></c:set>
<c:set var="roleIdVar" value="${resValue.roleId}"></c:set>
<c:set var="roleNameVar" value="${resValue.roleName}"></c:set>
<c:set var="parentCodeOfScreen" value="${resValue.parentCode}"></c:set>
<c:set var="parent_permOfScreen" value="${resValue.parent_perm}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>

<script type="text/javascript">
var aclElementMpgLables=new Array();

aclElementMpgLables[0]='<fmt:message bundle="${AccessControlLabels}" key="EDITABLE"/>';
aclElementMpgLables[1]='<fmt:message bundle="${AccessControlLabels}" key="HIDDEN"/>';
aclElementMpgLables[2]='<fmt:message bundle="${AccessControlLabels}" key="READONLY"/>';
aclElementMpgLables[3]='<fmt:message bundle="${AccessControlLabels}" key="EDIT"/>';
aclElementMpgLables[4]='<fmt:message bundle="${AccessControlLabels}" key="DELETE"/>';
aclElementMpgLables[5]='<fmt:message bundle="${AccessControlLabels}" key="RESET"/>';
aclElementMpgLables[6]='<fmt:message bundle="${AccessControlLabels}" key="SEARCH_MSG"/>';
var aclElementMpgAlerts=new Array();
aclElementMpgAlerts[0]='<fmt:message bundle="${AccessControlLabels}" key="DELETE_CONFIRM"/>';
aclElementMpgAlerts[1]='<fmt:message bundle="${AccessControlLabels}" key="SAVE_MSG"/>';
aclElementMpgAlerts[2]='<fmt:message bundle="${AccessControlLabels}" key="RISRTICT_NAV_SCR"/>';

//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.

	function CheckSelectedValues(ScreenName, eleCode, parentPerm) {

		//document.form1.roleName_Hidden.value=document.form1.roleName.value;
		if (ScreenName == 'MODULE') {
			document.getElementById('moduleSearchTbl').style.display = '';
			if (document.form1.roleId.value == 0) {
				alert('<fmt:message bundle="${AccessControlLabels}" key="RselectMsg"/>');
				return;
			}
		} else if (ScreenName == 'SCREEN') {
			document.getElementById('screenSearchTbl').style.display = '';
			if (document.form1.roleId.value == 0) {
				alert('<fmt:message bundle="${AccessControlLabels}" key="RselectMsg"/>');
				return;
			}
		}else if(ScreenName == 'FIELD')
		{
			document.getElementById('FieldTable').style.display='';
			if(document.form1.roleId.value == 0)
			{
				alert('<fmt:message bundle="${AccessControlLabels}" key="RselectMsg"/>');
				return false;
			}
		}
		ajaxfunction(ScreenName, eleCode, parentPerm);

	}

	function ajaxfunction(screenName, eleCode, parentPerm)
	{
		//obj1 is action type
		// obj2 is action code
		/*try
		{
			// Firefox, Opera 8.0+, Safari    

			xmlHttp = new XMLHttpRequest();
		}
		catch(e)
		{ // Internet Explorer    
			try
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch(e)
			{
				try
				{
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch(e)
				{
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}*/
		if(screenName == 'MODULE')
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=getElementsMappedToRole&roleId="
					+ document.form1.roleId.value
					+ "&roleName="
					+ document.form1.roleName.value
					+ "&elementName="
					+ screenName;
		}
		else if(screenName == 'SCREEN')
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=getElementsMappedToRole&roleId="
					+ document.form1.roleId.value
					+ "&roleName="
					+ document.form1.roleName.value
					+ "&moduleCode="
					+ document.form1.parentCode.value
					+ "&elementName="
					+ screenName;
		}
		else if (screenName == 'FIELD')
		{
			var url = "${contextPath}/hdiits.htm?actionFlag=getElementsMappedToRole&roleId="
					+ document.form1.roleId.value
					+ "&roleName="
					+ document.form1.roleName.value
					+ "&moduleCode="
					+ document.form1.parentCodeForField.value
					+ "&elementName="
					+ screenName;
		}

		/*xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4)
			{
				if(xmlHttp.status == 200)
				{
					if(screenName == 'MODULE')
					{
						var XMLDoc = xmlHttp.responseXML.documentElement;

						var tableentries = XMLDoc
								.getElementsByTagName('existelement');
						document.form1.OldPermission_Hid.value = '';
						
						insertElementEntry(tableentries, "ModuleTable",
								screenName, 'SCREEN',1);
						document.form1.Module_Save.disabled=true;
					}

					if(screenName == 'SCREEN') // for screen
					{
						var XMLDoc = xmlHttp.responseXML.documentElement;
						var tableentries = XMLDoc.getElementsByTagName('existelement');
						var Hiddenentries = XMLDoc.getElementsByTagName('hiddenPerm');
						var parentElementId = XMLDoc.getElementsByTagName('parentElementId');

						var comboentries = XMLDoc.getElementsByTagName('remainingelement');
						document.form1.parentCode.value = parentElementId[0].childNodes[0].text;
						parentPerm=document.getElementById('parent_perm').value;
						insertElementEntry(tableentries, "ScreenTable",screenName, 'FIELD',parentPerm);

						var comboEle=document.getElementById('ScreenCombo');
						comboEle.length=0;
						for ( var i = 0; i < comboentries.length; i++)
						{
							var item=document.createElement('option');
							item.text=comboentries[i].childNodes[1].text;
							item.value=comboentries[i].childNodes[0].text;
							
							var screenCombo=document.getElementById('ScreenCombo');
							try
							{
								screenCombo.add(item,null);
							}
							catch(e)
							{
								screenCombo.add(item);
							}
						}
						document.form1.Screen_Save.disabled=true;
					}

					if (screenName == 'FIELD')
					{
						var XMLDoc = xmlHttp.responseXML.documentElement;
						var tableentries = XMLDoc.getElementsByTagName('existelement');
						var Hiddenentries = XMLDoc.getElementsByTagName('hiddenPerm');
						var parentElementId = XMLDoc.getElementsByTagName('parentElementId');
						document.getElementById('parent_permForField').value=Hiddenentries[0].childNodes[0].text;
						insertElementEntry(tableentries, "FieldTable",screenName, '',document.getElementById('parent_permForField').value);
						var comboEle=document.getElementById('FieldCombo');
						var comboentries = XMLDoc.getElementsByTagName('remainingelement');
           				comboEle.length=0;
           				for ( var i = 0; i < comboentries.length; i++)
						{
							var item=document.createElement('option');
							item.text=comboentries[i].childNodes[1].text;
							item.value=comboentries[i].childNodes[0].text;

							var fieldCombo=document.getElementById('FieldCombo');
							try
							{
								fieldCombo.add(item,null);
							}
							catch(e)
							{
								fieldCombo.add(item);
							}
						}
					

						document.form1.Field_Save.disabled=true;
					}

				}

			}
		}
		xmlHttp.open("POST", encodeURI(url), false);
		xmlHttp.setRequestHeader("Content-Type","text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));*/

		new Ajax.Request(encodeURI(url),
		{
			method: 'POST',
			onSuccess: function(resXmlHttp)
			{
				if(screenName == 'MODULE')
				{
					var XMLDoc = resXmlHttp.responseXML.documentElement;
	
					var tableentries = XMLDoc
							.getElementsByTagName('existelement');
					document.form1.OldPermission_Hid.value = '';
					
					insertElementEntry(tableentries, "ModuleTable",
							screenName, 'SCREEN',1);
					document.form1.Module_Save.disabled=true;
				}
	
				if(screenName == 'SCREEN') // for screen
				{
					var XMLDoc = resXmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('existelement');
					var Hiddenentries = XMLDoc.getElementsByTagName('hiddenPerm');
					var parentElementId = XMLDoc.getElementsByTagName('parentElementId');
	
					var comboentries = XMLDoc.getElementsByTagName('remainingelement');
					document.form1.parentCode.value = parentElementId[0].firstChild.nodeValue;
					parentPerm=document.getElementById('parent_perm').value;
					insertElementEntry(tableentries, "ScreenTable",screenName, 'FIELD',parentPerm);
	
					var comboEle=document.getElementById('ScreenCombo');
					comboEle.length=0;
					for ( var i = 0; i < comboentries.length; i++)
					{
						var item=document.createElement('option');
						item.text=comboentries[i].childNodes[1].firstChild.nodeValue;
						item.value=comboentries[i].childNodes[0].firstChild.nodeValue;
						
						var screenCombo=document.getElementById('ScreenCombo');
						try
						{
							screenCombo.add(item,null);
						}
						catch(e)
						{
							screenCombo.add(item);
						}
					}
					document.form1.Screen_Save.disabled=true;
				}
	
				if (screenName == 'FIELD')
				{
					var XMLDoc = resXmlHttp.responseXML.documentElement;
					var tableentries = XMLDoc.getElementsByTagName('existelement');
					var Hiddenentries = XMLDoc.getElementsByTagName('hiddenPerm');
					var parentElementId = XMLDoc.getElementsByTagName('parentElementId');
					document.getElementById('parent_permForField').value=Hiddenentries[0].firstChild.nodeValue;
					insertElementEntry(tableentries, "FieldTable",screenName, '',document.getElementById('parent_permForField').value);
					var comboEle=document.getElementById('FieldCombo');
					var comboentries = XMLDoc.getElementsByTagName('remainingelement');
	   				comboEle.length=0;
	   				for ( var i = 0; i < comboentries.length; i++)
					{
						var item=document.createElement('option');
						item.text=comboentries[i].childNodes[1].firstChild.nodeValue;
						item.value=comboentries[i].childNodes[0].firstChild.nodeValue;
	
						var fieldCombo=document.getElementById('FieldCombo');
						try
						{
							fieldCombo.add(item,null);
						}
						catch(e)
						{
							fieldCombo.add(item);
						}
					}
					document.form1.Field_Save.disabled=true;
				}
			},
		    onFailure: alertOnFailureForMstScr,
		    asynchronous: false
		} );
		
		if (screenName == 'MODULE' || screenName == 23) {
			document.getElementById("tabModule").style.display = '';
			document.getElementById("ModuleSave").style.display = '';
		} else if (screenName == 'SCREEN' || screenName == 21) {
			document.getElementById("tabScreen").style.display = '';
		} else if (screenName == 'FIELD') {
			document.getElementById("tabField").style.display = '';
		}
	}

	function insertElementEntry(tableentries, tableName, screenName, nextScreen,parentPerm) {
		deleteTableValues(tableName);
		document.getElementById(tableName).style.display="";
		for ( var i = 0; i < tableentries.length; i++) {
			document.getElementById(tableName).style.display="";
			elementStatus = tableentries[i].childNodes[4].text;
			elecode = tableentries[i].childNodes[0].text;
			elename = tableentries[i].childNodes[1].text;
			roleid = tableentries[i].childNodes[2].text;
			rolename = document.getElementById('roleName').value;
			permission = tableentries[i].childNodes[3].text;
			elementParentId = tableentries[i].childNodes[5].text;
			spaceLevel = tableentries[i].childNodes[6].text;
			
			var displayFieldArray = new Array();
			displayFieldArray[0] = elecode;
			displayFieldArray[1] = elename;
			displayFieldArray[2] = permission;
			displayFieldArray[3] = document.getElementById('roleId').value;
			displayFieldArray[4] = nextScreen;
			displayFieldArray[5] = parentPerm;
			displayFieldArray[6] = elementParentId;
			displayFieldArray[7] = spaceLevel;
			addDataInTableElement(tableName, displayFieldArray,
					'deleteDBRecordForElement', 'editDBRecord', 'M', true);
		}
	}
	function saveData()
	{
		var varRoleId = document.getElementById("roleId").value;
		var varModule_Save = document.getElementById("Module_Save").disabled;
		var varScreen_Save = document.getElementById("Screen_Save").disabled;
		var tcontent1Style = document.getElementById("tcontent1").style.display;
		var tcontent2Style = document.getElementById("tcontent2").style.display;

		if(varRoleId != "" && varRoleId != "0" && tcontent1Style == 'block' && varModule_Save == false)
		{
			saveElements('MODULE','ModuleTable');
		}
		else if(varRoleId != "" && varRoleId != "0" && tcontent2Style == 'block' && varScreen_Save == false)
		{
			saveElements('SCREEN','ScreenTable');
		}
	}
</script>

<hdiits:form name="form1" validate="true" method="post"
	action="./hdiits.htm">
	
	 
	<body onkeypress="return checkSpecialCharacter(event)">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="AccControlModule1" bundle="${AccessControlLabels}"></hdiits:caption>
		</a></li>
		<li><a href="#" rel="tcontent2"
			onmousedown="javascript:alert('<fmt:message bundle="${AccessControlLabels}" key="RISRTICT_NAV_SCR"/>')">
		<hdiits:caption captionid="AccControlModule2"
			bundle="${AccessControlLabels}"></hdiits:caption> </a></li>
		<li style="display: none;"><a href="#" rel="tcontent3"
			onmousedown="javascript:alert('<fmt:message bundle="${AccessControlLabels}" key="RISRTICT_NAV_FLD"/>')">
		<hdiits:caption captionid="AccControlModule3"
			bundle="${AccessControlLabels}"></hdiits:caption> </a></li>
	</ul>
	</div>

	<div id="tcontent1" class="tabcontent">
	<table width="50%" align="center">
		<tr>
			<td class="fieldLabel" align="center" width="20%"><hdiits:caption
				captionid="Role_Name" bundle="${AccessControlLabels}" /></td>
			
			<td width="30%"><hdiits:text id="roleName" name="roleName"
				captionid="SEARCH_TYPE" bundle="${userPostLables}"
				style="background-image: url('./images/search_autoCompelete.gif'); background-repeat: no-repeat; background-position: right" onblur="displayText('roleName');setOriginalWindowName();" onfocus="removeText('roleName');setAjaxWindowName();"/>
				<span
				id="roleIndicatorRegion" style="display: none"> <img
				src="./images/busy-indicator.gif" /> </span></td>
		</tr>
	</table>
	<br>
	<table width="50%" align="center" id="moduleSearchTbl"
		style="display: none">
		<tr>
			<td class="fieldLabel" align="center" width="20%"><hdiits:caption
				captionid="Module_Not_Assigned" bundle="${AccessControlLabels}" /></td>
			<td width="30%">
			<hdiits:text id="txtModuleName"	name="txtModuleName" captionid="Module_Not_Assigned"
				bundle="${AccessControlLabels}"
				style="background-image: url('./images/search_autoCompelete.gif'); background-repeat: no-repeat; background-position: right" onblur="displayText('txtModuleName');setOriginalWindowName();" onfocus="removeText('txtModuleName');setAjaxWindowName();"/>
				<span id="moduleIndicatorRegion" style="display: none"><img src="./images/busy-indicator.gif" /></span>
			</td>
		</tr>
		<hdiits:hidden name="moduleElementId" />
	</table>
	 <br>
	<table id="tabModule" align="center" style="display: none" width="100%">
		<tr>
			<td>

			<table id="ModuleTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="display: none;border-collapse: collapse;background-color: #F0F4FB"  >
				<tr>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						bundle="${AccessControlLabels}" captionid="Mcode" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="Mname" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="AcType_Permissions" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="ACTION" bundle="${AccessControlLabels}" /></b></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<table align="center" id="ModuleSave" style="display: none" width="100%">
		<tr>
			<td align="center">
				<hdiits:button type="button" id="Module_Save" name="Module_Save" captionid="SaveData" bundle="${AccessControlLabels}" onclick="saveElements('MODULE','ModuleTable')" style="disabled:true" />
				<hdiits:button name="btnCloseWindow" type="button" captionid="CLOSE" bundle="${AccessControlLabels}" onclick="closeWindow()"/>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
        	 var navDisplay = false;
         </script> <input type="hidden" name="actionFlag" value="insertRoleElementMapping">
	</div>

	<div id="tcontent2" class="tabcontent"><br>
	<br>
	<table id="tabScreen" align="center" style="display: none"
		width="100%">
		<tr>
			<td>

			<table width="80%" align="center" id="screenSearchTbl">
				<tr>
					<td class="fieldLabel" align="center"><hdiits:caption
						captionid="Screen_Not_Assigned" bundle="${AccessControlLabels}" />
					</td>
					<td class="fieldLabel">
					<hdiits:select id="ScreenCombo"  name="ScreenCombo" multiple="true"></hdiits:select>
					</td>
					<td><hdiits:button id="btnScreenAssignPer"
						name="btnScreenAssignPer" type="button" captionid="BTN_ASSIGN_PER"
						bundle="${AccessControlLabels}"
						onclick="addElementMapping('SCREEN')" /></td>
				</tr>
			</table>
			</td>
		</tr>
		 <tr><td>&nbsp;</td></tr>
		<tr>
			<td>

			<table id="ScreenTable" border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="display: none;border-collapse: collapse;background-color: #F0F4FB" >
				<tr>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						bundle="${AccessControlLabels}" captionid="Scode" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="Sname" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="AcType_Permissions" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
						captionid="ACTION" bundle="${AccessControlLabels}" /></b></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<br>
	<br>
	<table align="center" width="100%">
		<tr>
			<td align="center"><hdiits:button type="button" name="Screen_Save"
				id="Screen_Save" captionid="SaveData"
				onclick="saveElements('SCREEN','ScreenTable')" bundle="${AccessControlLabels}"
				style="disabled:true" /> <hdiits:button type="button"
				name="Prev_screen" id="Prev_screen" captionid="PREV_SCREEN"
				onclick="goToPrevTab()" bundle="${AccessControlLabels}" />
				<hdiits:button name="btnClose" type="button" captionid="CLOSE" bundle="${AccessControlLabels}" onclick="closeWindow()"/>
			</td>
		</tr>
	</table>

	</div>
	<div id="tcontent3" class="tabcontent"><br>
	<br>
	<br>
	 
	<table id="tabField" align="center" style="display: none"
		width="100%">
		<tr>
			<td>
				<table width="80%" align="center" id="searchFieldTbl">
	         
	      		 <tr>
	       			<td class="fieldLabel" align="center">
	         			<hdiits:caption bundle="${AccessControlLabels}" captionid="Field_Not_Assigned"/>
	        		 </td>
	       	 		<td align="center">  
	       	 			<hdiits:select id="FieldCombo"  name="FieldCombo" multiple="true"></hdiits:select>
	       	 		</td>
	        		 <td>
			   			<hdiits:button id="btnFieldAssignPer" name="btnFieldAssignPer" type="button" captionid="BTN_ASSIGN_PER" bundle="${AccessControlLabels}" onclick="addElementMapping('FIELD')" />
			 		</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table id="FieldTable" border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="display: none;border-collapse: collapse;background-color: #F0F4FB" >
					<tr>
						<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
							bundle="${AccessControlLabels}" captionid="Fcode" /></b></td>
						<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
							captionid="Fname" bundle="${AccessControlLabels}" /></b></td>
						<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
							captionid="AcType_Permissions" bundle="${AccessControlLabels}" /></b></td>
						<td align="center" bgcolor="#C9DFFF" class="fieldLable"><b><hdiits:caption
							captionid="ACTION" bundle="${AccessControlLabels}" /></b></td>
					</tr>

				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>

	<table align="center" width="100%">
		<tr>
			<td align="center"><hdiits:button type="button" name="Field_Save"
				id="Field_Save" captionid="SaveData"
				onclick="saveElements('FIELD','FieldTable')" bundle="${AccessControlLabels}"
				style="disabled:true" /> <hdiits:button type="button"
				name="Prev_TAB" id="Prev_TAB" captionid="PREV_SCREEN"
				onclick="goToPrevTab()" bundle="${AccessControlLabels}" /></td>
			<td>
				<hdiits:button name="close" type="button" captionid="CLOSE" bundle="${AccessControlLabels}" onclick="closeWindow()"/>
			</td>
		</tr>
	</table>
	</div>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<div id="progressImage"></div>
	
	</body>
	<hdiits:hidden name="searchField" default="role" />
	<hdiits:hidden name="parentCodeForField" default="0"/>
    <hdiits:hidden name="parent_permForField" default="1"/>
	
	<hdiits:hidden name="addedList" />
	<hdiits:hidden name="screenElementId" />
	<hdiits:hidden name="fieldElementId" />
	<hdiits:hidden name="hiddentext" />
	<hdiits:hidden name="hiddentext1" />
	<hdiits:hidden name="ActionType" />
	<hdiits:hidden name="roleId" default="0"/>
	<hdiits:hidden name="parentCode" default="0" />
	<hdiits:hidden name="OldPermission_Hid" />
	<hdiits:hidden name="exist_module_perm" />
	<hdiits:hidden name="parent_perm" default="1"/>
	<hdiits:hidden name="listCounter" />
	<hdiits:hidden name="listCounter1" />
	<hdiits:hidden name="ModuleLevel" default="MODULE" />
	<hdiits:hidden name="ScreenLevel" default="SCREEN" />
	<hdiits:hidden name="FieldLevel" default="FIELD" />
	<hdiits:hidden name="FileLevel" default="File Menu" />
</hdiits:form>
<script>
initializetabcontent("maintab")
//displayText('roleName');
    displayText('txtModuleName');	

    function onLoadDetails()
	{	 
		document.getElementById("roleId").value = '${roleIdVar}';
		document.getElementById("roleName").value = '${roleNameVar}';
		getModuleDetails();
	}
	if ("${saveType}" == "MODULE")
	{
		onLoadDetails();
	}else if("${saveType}" == "SCREEN")
	{
		onLoadDetails();
		document.getElementById('parentCode').value='${parentCodeOfScreen}';
		document.getElementById('parent_perm').value='${parent_permOfScreen}';
		moveToNextTab("SCREEN", document.getElementById('parentCode').value, document.getElementById('parent_perm').value, "");
	}
	else
	{
		displayText('roleName');
	}
	 document.getElementById('btnScreenAssignPer').style.width=130;
	 document.getElementById('Prev_screen').style.width=130;
	 //document.getElementById("tcontent1").focus();
</script>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<ajax:autocomplete source="roleName" target="roleId"
	baseUrl="hrms.htm?actionFlag=searchRoleByRoleName"
	parameters="searchKey={roleName}" className="autocomplete"
	minimumCharacters="3" postFunction="getModuleDetails"
	appendSeparator="false" preFunction="setBlankInRoleId"
	indicator="roleIndicatorRegion" />
	
<ajax:autocomplete source="txtModuleName" target="moduleElementId"
	baseUrl="hrms.htm?actionFlag=getElementDetails"
	parameters="moduleName={txtModuleName},searchPK={roleId},searchField={searchField},addedList={addedList},elementType={ModuleLevel}"
	className="autocomplete" minimumCharacters="3" postFunction="sendAjexRequestForSelectedModule" 
	appendSeparator="false" preFunction="setBlankInModuleId" indicator="moduleIndicatorRegion" />


	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>