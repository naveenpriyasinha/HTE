<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.acl.acl" var="AccessControlLabels" scope="request"/>
<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables" scope="request" />
<fmt:setBundle basename="resources.MstScrConstants" var="mstScrConstants" scope="request" />
<html>
<head>
	<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
	<script type="text/javascript" src="<c:url value="/script/acl/commonRoleMapping.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/acl/newUserRoleMapping.js"/>"></script>
	<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
	
	<script type="text/javascript">
		var cmnRoleMappingLabel = new Array();
		cmnRoleMappingLabel[0]="<fmt:message key='ACTIVE' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[1]="<fmt:message key='DEACTIVE' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[2]="<fmt:message key='EDIT' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[3]="<fmt:message key='RESET' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[4]="<fmt:message key='DELETE' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[5]="<fmt:message key='Acl_Insert_Msg' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[6]="<fmt:message key='USER_ROLE_ALERT' bundle='${AccessControlLabels}'/>";
		cmnRoleMappingLabel[7]="<fmt:message key='SEARCH_MSG' bundle='${AccessControlLabels}'/>";
		function saveData()
		{
			var varUserId = document.getElementById("userId").value;
			if(varUserId != "" && varUserId != "0" && varUserId != null && varUserId != 'null')
			{
				submitFormData();
			}
		}
		function searchUser()
		{
			
			var href='hdiits.htm?actionFlag=showUsersList&radio=true';
			window.open(href,'chield', 'width=860,height=600,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=60');
					
		}
	</script>
</head>

<c:set var="resultObj" value="${result}" > </c:set>		
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>			
<c:set var="userList" value="${resValue.userList}"> </c:set>
<body onkeypress="return checkSpecialCharacter(event)">
<hdiits:form name="frmUserRoleMpg" validate="true" method="POST" action="./hdiits.htm">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	 	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption id="temp1" captionid="UserToRoleMapping"  bundle="${AccessControlLabels}"/></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent">

			<table width="50%" border=0 align="center">
				<tr>
			    	<td class="fieldLabel" align="center">
			    		<b><hdiits:caption captionid="SEARCH_BY" bundle="${userPostLables}" /></b>
			    	</td>
			    	
			    	 
			    	<td align="center">
						<hdiits:select id="searchType" name="searchType" captionid="SEARCH_TYPE" bundle="${userPostLables}" default="${searchType}" onchange="checkForEmpSearch(this);" sort="false">
								<hdiits:option value="name"><fmt:message key="USER_NAME" bundle="${userPostLables}"></fmt:message></hdiits:option>
						</hdiits:select>
					</td> 
					<%-- <td id="txtSearchTypeTdID" style="display:none">
						<hdiits:text id="txtSearchType" name="txtSearchType" captionid="SEARCH_TYPE" bundle="${userPostLables}" onblur="displayText();setOriginalWindowName()" onfocus="removeText();setAjaxWindowName()" style="background-image: url('./images/search_autoCompelete.gif'); background-repeat: no-repeat; background-position: right"/>
					</td>
					  <td id="indicatorRegion" style="display:none;">
						<img src="./images/busy-indicator.gif"/>
					</td>--%>  
					<td id="txtSearchTypeTdID" >
						<hdiits:text id="UserName" name="UserName" captionid="SEARCH_TYPE" bundle="${userPostLables}"  disable="true"/>
					<img style='cursor: pointer' src='images/search_autoCompelete.gif' onclick='searchUser()'/>
		
					</td>
					<%-- <td id="txtEmployeeNameTdID" ><hdiits:search name="txtEmployeeName" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true" size="20" /></td>--%>
					
					
				</tr>
			</table>
			<br></br>
			
			<table id='userTableId' border=1 borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1"
				style="display: none; border-collapse: collapse; background-color: #F0F4FB">
				<tr>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="EMP_NAME" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="USER_NAME" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="LOC_NAME" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="DSGN_NAME" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="BRNCH_NAME" bundle="${AccessControlLabels}" /></b></td>
					<td align="center" bgcolor="#C9DFFF"><b><hdiits:caption	captionid="SELECT_POST_NM" bundle="${AccessControlLabels}" /></b></td>
				</tr>
				<tr>
					<td align="center" id="empName"><label></label></td>
					<td align="center" id="userNm"><label></label></td>
					<td align="center" id="location"><label></label></td>
					<td align="center" id="designation"><label></label></td>
					<td align="center" id="branch"><label></label></td>
					<td align="center" id="postName"><label></label></td>
				</tr>
			</table>
			
			<br>
			
			<table align="center">
				<tr>
					<td align="center">
						<hdiits:button name="getRoles" type="button" captionid="GT_ROLES" bundle="${AccessControlLabels}" onclick="openAppWindow('hdiits.htm?actionFlag=showAdminRoleDtl&checkbox=true')"/>
			    	</td>
				</tr>
			</table>
			
			<br></br>
			<table id="userActiveRoleDtls" border="1" cellpadding="1" cellspacing="1" borderColor="black" style="border-collapse: collapse;display:none;" BGCOLOR="WHITE" align="center" width="100%">
			   	<tr>
					<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="Role_Name" bundle="${AccessControlLabels}"/></td>
					<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="Role_Desc" bundle="${AccessControlLabels}"/></td>
					<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="STATUS" bundle="${AccessControlLabels}"/></td>
					<td align="center" class="fieldLabel" bgcolor="#C9DFFF"><hdiits:caption captionid="ACTION" bundle="${AccessControlLabels}"/></td>
				</tr>
			 </table>
			 <br></br><br></br>
			 
			 <table width="100%" align="center" id="savebtnTableId" style="display: none;">
				<tr><td align="center">
					<hdiits:button name="submitData" type="button" captionid="SUBMIT" bundle="${AccessControlLabels}" onclick="submitFormData()"/>
					<hdiits:button name="close" type="button" captionid="CLOSE" bundle="${AccessControlLabels}" onclick="closeWindow()"/>
				</td></tr>
			</table>
   	       <hdiits:hidden name="userId" />
			<script language="javascript">
			
				var empSearchReqFlag="<fmt:message key='IS_EMP_SEARCH_REQ' bundle='${mstScrConstants}'/>";
				//removeComboValueOnEmpReq(empSearchReqFlag);
				
				initializetabcontent("maintab");
				document.getElementById("getRoles").disabled=true;
				//displayText();
				document.getElementById("tcontent1").focus();
			</script>
			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="" />
		</div>
	</div>
</hdiits:form>
</body>
</html>
<%--- -<ajax:autocomplete
    source="txtSearchType"
    target="userId"
    baseUrl="hrms.htm?actionFlag=getUserList_master"
    parameters="searchKey={txtSearchType},searchType={searchType}"
    className="autocomplete"
    minimumCharacters="3"
    postFunction="sendAjexRequestForSelectedUserDtls"
    appendSeparator="false"
    preFunction="setBlankValue"
    indicator="indicatorRegion"
    /> --%>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>