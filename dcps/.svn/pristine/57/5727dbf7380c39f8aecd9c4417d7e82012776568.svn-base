<%
try {
%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.ess.UserPost" var="userPostLables" scope="request" />

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script>

// start to display office name as per selected office Type 
var LocationCombo = -1;
var PostCombo = -1;
var recType='H';

	function showOfficeName(flag, selDepartment, setLocation, selDesignation, selPost, selRole)
	{
		var url = new String("");
		if (flag == 'location')
		{
			departmentCode = eval("document.frmUserPostMapping."+ selDepartment +".value");
			url = "hrms.htm?actionFlag=getDeptLocDropdownDetails&departmentCode="+ departmentCode +"&flag="+flag+"&locationFieldName="+ setLocation+"&recType="+recType;  
		}
		else if(flag == 'post')
		{
			locationCode = eval("document.frmUserPostMapping."+ setLocation +".value") ;
			designationCode = eval("document.frmUserPostMapping."+ selDesignation +".value");
			url = "hrms.htm?actionFlag=getDeptLocDropdownDetails&locationCode="+ locationCode +"&flag="+flag+"&designationCode="+ designationCode +"&postFieldName="+ selPost+"&recType="+recType;  
		}
		try
		{
		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (e)
			{							
				return false; 
			}
		}
        xmlHttp.open("POST", encodeURI(url) , false); 
        if (flag == 'location')
	        xmlHttp.onreadystatechange = processResponseforBlocks; 
	    else if(flag == 'post')
	        xmlHttp.onreadystatechange = fillPostDropdown; 
	        
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); 
	}

	function processResponseforBlocks()
	{
		if (xmlHttp.readyState == 4) 
		{ 				
			if (xmlHttp.status == 200) 
			{ 					
				var xmlStr = xmlHttp.responseText; 
				
				var XMLDoc=getDOMFromXML(xmlStr); 
				
				if(XMLDoc!=null)
				{
					if(XMLDoc.getElementsByTagName('option')!= null)
					{
						var officeNm=XMLDoc.getElementsByTagName('option'); 	
						var locationFieldName = XMLDoc.getElementsByTagName('locationFieldName')[0].childNodes[0].text; 
						objOfficeNameCombo = eval("document.frmUserPostMapping."+ locationFieldName) ;
						
						var locationName = new String("");
						objOfficeNameCombo.length = 1; 	
						for ( var iter = 0 ; iter < officeNm.length ; iter++ )
						{
							var objoption = document.createElement('option');
							if (officeNm[iter].childNodes[0] != null)
							{
							 	locationName = officeNm[iter].childNodes[0].text;
							 	objoption.text = locationName.replace("amp;","&");
							}
							objoption.value=officeNm[iter].getAttribute('value');
							try
							{
								objOfficeNameCombo.add(objoption,null); 
							}
							catch(ex)
							{ 
								objOfficeNameCombo.add(objoption); 
							}
						}
						if (LocationCombo != -1)
						{
							objOfficeNameCombo.value = LocationCombo;
							LocationCombo = -1;
						}	
						else
							objOfficeNameCombo.value = '0';	
					} 	
			   }
			}
		}
	}
	
	// end to display office name as per selected office Type
	
	function fillPostDropdown()
	{
		if (xmlHttp.readyState == 4) 
		{ 				
			if (xmlHttp.status == 200) 
			{ 					
				var xmlStr = xmlHttp.responseText; 
				var XMLDoc=getDOMFromXML(xmlStr); 
				if(XMLDoc!=null)
				{
					if(XMLDoc.getElementsByTagName('option')!= null)
					{
						var officeNm=XMLDoc.getElementsByTagName('option'); 	
						var postFieldName = XMLDoc.getElementsByTagName('postFieldName')[0].childNodes[0].text; 
						objPostCombo = eval("document.frmUserPostMapping."+ postFieldName) ;
						objPostCombo.length = 1; 	
						for ( var iter = 0 ; iter < officeNm.length ; iter++ )
						{
							var objoption = document.createElement('option');
							
							if (officeNm[iter].childNodes[0] != null)
							 	objoption.text = officeNm[iter].childNodes[0].text;
	
							objoption.value=officeNm[iter].getAttribute('value');
							
							try
							{
								objPostCombo.add(objoption,null); 
							}
							catch(ex)
							{ 
								objPostCombo.add(objoption); 
							}
						}
						if (PostCombo != -1)
						{
							objPostCombo.value = PostCombo;
							PostCombo = -1;
						}	
						else
							objPostCombo.value = '0';	
			  	    } 
			    }	
			}
		}
	}
	function checkForDesg(flag,selDepartment,selLocation)
	{
		departmentCode = eval("document.frmUserPostMapping."+ selDepartment +".value") ;
		locationCode = eval("document.frmUserPostMapping."+ selLocation +".value") ;
		if(flag=='Salect')
		{ 	
			if(departmentCode=='0' || locationCode=='0')
			{
				document.getElementById('selDesignation').options[0].selected= true ;
				document.frmUserPostMapping.selDesignation.disabled  = true; 
			}
			else 
			{
				document.frmUserPostMapping.selDesignation.disabled  = false; 
			}
		}
	}	
	function resetDtls()
	{
		document.getElementById('selUserName').options[0].selected= true ;
		document.getElementById('selOfficeType').options[0].selected= true ;
		document.getElementById('selOfficeName').options[0].selected= true ;
		document.getElementById('selOfficeName').length= 1 ;
		
		document.getElementById('selDesignation').options[0].selected= true ;
		document.frmUserPostMapping.selDesignation.disabled  = true;
				
		document.getElementById('selPost').options[0].selected= true ;
		document.getElementById('selPost').length= 1 ;
	
		document.getElementById('dtStartDate').value='';	
		document.getElementById('dtEndDate').value='';	
		
		document.getElementById('postTypeCmb').options[0].selected = true ; // added by divya
		var rdoLength = document.frmUserPostMapping.rdoActiveFlag.length;// added by divya
		
		for(var i = 0; i < rdoLength; i++)// added by divya
		{
			document.frmUserPostMapping.rdoActiveFlag[i].checked = false;
		}		
	}

	function saveUserPostMappingDtls()
	{		
		var fieldArray = new Array('selUserName','selPost','dtStartDate', 'postTypeCmb'); // changed by divya
		var statusValidation = validateSpecificFormFields(fieldArray);
		if(statusValidation)
		{
			var stausFlagVal = getCheckedRadioValue("rdoActiveFlag");
			if(stausFlagVal == "")
			{
				alert("<fmt:message bundle='${userPostLables}' key='selActivate'/>");
				return false;
			}
			else
			{	
				document.frmUserPostMapping.action="hrms.htm?actionFlag=submitUserPostMappingDtls";
				document.frmUserPostMapping.submit();
			}
		}
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
	
	function closeWindow()
	{
		document.frmUserPostMapping.action = "hrms.htm?actionFlag=userPostMapping";
	   	document.frmUserPostMapping.submit();
	}	
	
	function compareStartDateToEndDate()
	{		
		if(document.getElementById("dtStartDate").value!="" && document.getElementById("dtEndDate").value!="")
		{			
			var lFrmDate=document.getElementById("dtStartDate").value;							
			var lToDate=document.getElementById("dtEndDate").value;					
			if(compareDate(lFrmDate,lToDate) < 0 )
			{
				alert("<fmt:message bundle='${userPostLables}' key='ValidEndDate'/>");
				document.getElementById('dtEndDate').value='';
				document.frmUserPostMapping.dtEndDate.focus();
			} 
		}
	}
	
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<c:set var="OfficeTypeInfo" value="${resultValue.arOfficeTypeInfo}"></c:set>
<c:set var="arDesignationVO" value="${resultValue.arDesignationVO}"></c:set>
<c:set var="arOrgUserMst" value="${resultValue.arOrgUserMst}"></c:set>
<c:set var="lUserPostRltId" value="${resultValue.lUserPostRltId}"></c:set>
<!-- <c:set var="arActivateFlag" value="${resultValue.arActivateFlag}"></c:set> --><!-- added by divya -->
<c:set var="activateFlag" value="${resultValue.activateFlag}"></c:set><!-- added by divya -->
<c:set var="arStatusFlag" value="${resultValue.arStatusFlag}"></c:set><!-- added by divya -->
<c:set var="postType" value="${resultValue.arPostStatus}"></c:set><!-- added by divya -->
<c:set var="postStatus" value="${resultValue.postStatus}"></c:set><!-- added by divya -->
<c:set var="locCode" value="${resultValue.locCode}"></c:set>
<c:set var="postId" value="${resultValue.postId}"></c:set>
<c:set var="depCode" value="${resultValue.depCode}"></c:set>
<c:set var="dsgnCode" value="${resultValue.dsgnCode}"></c:set>
<c:set var="userID" value="${resultValue.userID}"></c:set>
<c:set var="startDate" value="${resultValue.startDate}"></c:set>
<c:set var="endDate" value="${resultValue.endDate}"></c:set>

<hdiits:form name="frmUserPostMapping" validate="true" method="POST">
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message bundle='${userPostLables}' key='UserPostMapping'/></b></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<table width="100%" id="newUserPost">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userPostLables}' key='NewUserPostMap'/></b></td></tr>
		</table>
		<table width="100%" id="editUserPost" style="display:none">
			<tr width="100%" align="center"><td><b><fmt:message bundle='${userPostLables}' key='EditUserPostMap'/></b></td></tr>
		</table>
	<table class="tabtable">		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="USER_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="USER_NAME" bundle="${userPostLables}" name="selUserName" sort="false" validation="sel.isrequired" default="${userID}" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="SELECT" bundle="${userPostLables}" />
					</hdiits:option>
					<c:forEach var="userNameInfoVar" items="${arOrgUserMst}">
						<hdiits:option value="${userNameInfoVar.userId}">${userNameInfoVar.userName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="OFFICE_TYPE" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="OFFICE_TYPE" bundle="${userPostLables}" name="selOfficeType" sort="false"
						caption="Office_Type" validation="sel.isrequired" onchange="showOfficeName('location','selOfficeType','selOfficeName'),checkForDesg('Salect','selOfficeType','selOfficeName')" default="${depCode}">
					<hdiits:option value="0">
						<fmt:message key="SELECT" bundle="${userPostLables}" />
					</hdiits:option>
					<c:forEach var="OfficeTypeInfoVar" items="${OfficeTypeInfo}">
						<hdiits:option value="${OfficeTypeInfoVar.depCode}">${OfficeTypeInfoVar.depName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="OFFICE_NAME" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="OFFICE_NAME" bundle="${userPostLables}" name="selOfficeName" id="selOfficeName" sort="false"
						caption="Office_Nm" validation="sel.isrequired" onchange="checkForDesg('Salect','selOfficeType','selOfficeName')" >
					<hdiits:option value="0">
						<fmt:message key="SELECT" bundle="${userPostLables}" />
					</hdiits:option>
					
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="DSGN" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="DSGN" bundle="${userPostLables}" name="selDesignation" id="selDesignation" sort="false"
						caption="Designation" validation="sel.isrequired" onchange="showOfficeName('post','','selOfficeName','selDesignation','selPost')">
					<hdiits:option value="0">
						<fmt:message key="SELECT" bundle="${userPostLables}" />
					</hdiits:option>
					<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
						<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>		
			<td width="25%"></td>			
			<td><b><hdiits:caption captionid="POST" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="POST" bundle="${userPostLables}" name="selPost" id="selPost" sort="false"
						caption="Post" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="SELECT" bundle="${userPostLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="StartDate" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:dateTime captionid="StartDate" bundle="${userPostLables}" name="dtStartDate"  default="${startDate}" afterDateSelect="compareStartDateToEndDate()" validation="txt.isdt,txt.isrequired" mandatory="true"/></td>
			<td></td>
		</tr>


		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="ToDate" bundle="${userPostLables}"></hdiits:caption></b></td>
			<td><hdiits:dateTime captionid="ToDate" bundle="${userPostLables}" name="dtEndDate"  default="${endDate}" afterDateSelect="compareStartDateToEndDate()" validation="txt.isdt,txt.isrequired"/></td>
			<td></td>
		</tr>
		<!-- added by divya --  starts -->
		<tr>
			<td width="25%"></td>
			<td><hdiits:caption captionid="admin.PostType" bundle="${userPostLables}"/></td>
				<td><hdiits:select name="postTypeCmb" id="postTypeCmb" captionid="admin.PostType" bundle="${userPostLables}" mandatory="true" sort="false" validation="sel.isrequired">
				<hdiits:option value=""><fmt:message key="admin.select" bundle="${userPostLables}"></fmt:message></hdiits:option>
					<c:forEach items="${postType}" var="postType">
						<option value="<c:out value="${postType.lookupName}"/>">
						<c:out value="${postType.lookupDesc}" /></option>
					</c:forEach>
					</hdiits:select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td width="25%"></td>
			<td><hdiits:caption captionid="ActivateFlag" bundle="${userPostLables}"></hdiits:caption></td>
			<td>					
				<c:forEach var="StatusFlagValue" items="${arStatusFlag}">
					<hdiits:radio name="rdoActiveFlag" value="${StatusFlagValue.lookupId}" />${StatusFlagValue.lookupName}<b><label class="mandatoryindicator">*</label></b><br>										
				</c:forEach>
							
			</td>
		</tr>
		<!-- added by divya --  ends -->
	</table>
	
	<table class="tabtable">
		<tr>
			<td align="center">
				<br></br><hdiits:button name="btnEmpJoiningDtlSubmit" type="button" captionid="SUBMIT" bundle="${userPostLables}" onclick="saveUserPostMappingDtls()"/>
			<hdiits:button name="btnReset" type="button" captionid="RESET" bundle="${userPostLables}" onclick="resetDtls()"/>
			<hdiits:button name="btnClose" type="button" captionid="close" bundle="${userPostLables}" onclick="closeWindow()"/>
		</tr>
	</table>

	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	
	<script type="text/javascript">
		document.frmUserPostMapping.dtStartDate.readOnly = true;
		document.frmUserPostMapping.dtEndDate.readOnly = true;
		var lUserPostRltId = "${lUserPostRltId}";
		
		if (lUserPostRltId != null && lUserPostRltId != 0)
		{
			showOfficeName('location','selOfficeType','selOfficeName');
			var locCode='${locCode}';
			if(locCode!="")
			{
				document.getElementById('selOfficeName').value = locCode;
			}
			var dsgnCode='${dsgnCode}'; 
			if(dsgnCode!="")
			{
				document.getElementById('selDesignation').value = dsgnCode;
			}
			
			var postId='${postId}'; 
			if(postId!="")
			{
				showOfficeName('post','','selOfficeName','selDesignation','selPost');
				document.getElementById('selPost').value =postId;
			}
			// added by divya -->
			
			document.getElementById('postTypeCmb').value = '${postStatus}';
			var activateFlag='${activateFlag}'; 
			
			if(activateFlag == 1) // active
				document.frmUserPostMapping.rdoActiveFlag[0].checked = true;
			if(activateFlag == 2) // deactive
				document.frmUserPostMapping.rdoActiveFlag[1].checked = true;
			if(activateFlag == 3) // delete
				document.frmUserPostMapping.rdoActiveFlag[2].checked = true;
			
			document.getElementById('newUserPost').style.display = 'none';	
			document.getElementById('editUserPost').style.display = '';	
			
		}
		
		initializetabcontent("maintab");
		
	</script>
		</div>
	</div>
	<hdiits:hidden name="hdnUserId" id="hdnUserId" default="${lUserPostRltId}"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="selUserName,selPost,dtStartDate" />
</hdiits:form>
<%
	} 
    catch (Exception e)
    {
	 e.printStackTrace();
	}
%>		
