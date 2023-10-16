<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.hr.posting.postingLabels" var="EmpJoiningLables" scope="request" />

<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>

<script>
// start to display office name as per selected office Type
var LocationCombo = -1;
var PostCombo = -1;
var RoleCombo = -1;

	function showOfficeName(flag, selDepartment, setLocation, selDesignation, selPost, selRole)
	{
		var url = new String("");
		if (flag == 'location')
		{
			departmentCode = eval("document.frmEmpJoiningDtls."+ selDepartment +".value");
			url = "hrms.htm?actionFlag=getOffNmByOffType&departmentCode="+ departmentCode +"&flag="+flag+"&locationFieldName="+ setLocation+"&recType="+recType;  
		}
		else if(flag == 'post')
		{
			locationCode = eval("document.frmEmpJoiningDtls."+ setLocation +".value") ;
			designationCode = eval("document.frmEmpJoiningDtls."+ selDesignation +".value");

			if(recType=='H')
			{
				StartDate='';
				url = "hrms.htm?actionFlag=getOffNmByOffType&locationCode="+ locationCode +"&flag="+flag+"&designationCode="+ designationCode +"&postFieldName="+ selPost+"&recType="+recType+"&StartDate="+StartDate;  
			}
			else if(recType=='C')
			{
				if(StartDate=='')
				{
					alert('<fmt:message  bundle="${EmpJoiningLables}" key="StartDateNullAlert"/>');
					document.frmEmpJoiningDtls.selOfficeName.value = 0;
					return null;
				}
				else
				{
					url = "hrms.htm?actionFlag=getOffNmByOffType&locationCode="+ locationCode +"&flag="+flag+"&designationCode="+ designationCode +"&postFieldName="+ selPost+"&recType="+recType+"&StartDate="+StartDate;  
				}
			}
		}
		else if(flag == 'role')
		{
			PostCode = eval("document.frmEmpJoiningDtls."+ selPost +".value") ;
			url = "hrms.htm?actionFlag=getOffNmByOffType&PostCode="+ PostCode +"&flag="+flag+"&roleFieldName="+selRole+"&recType="+recType; 
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
	    else if(flag == 'role')
	        xmlHttp.onreadystatechange = fillRoleDropdown; 
	        
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
						objOfficeNameCombo = eval("document.frmEmpJoiningDtls."+ locationFieldName) ;
						
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
						objPostCombo = eval("document.frmEmpJoiningDtls."+ postFieldName) ;
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
							if (recType == 'C')
								createOptionForPost(objPostCombo,PostCombo);
							else
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
	
	function fillRoleDropdown()
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
						var roleFieldName = "";
						if (XMLDoc.getElementsByTagName('roleFieldName')[0] != null)
						{
							roleFieldName = XMLDoc.getElementsByTagName('roleFieldName')[0].childNodes[0].text; 
							objRoleCombo = eval("document.frmEmpJoiningDtls."+ roleFieldName) ;
							objRoleCombo.length = 1; 
							for ( var iter = 0 ; iter < officeNm.length ; iter++ )
							{
								var objoption = document.createElement('option');
								
								if (officeNm[iter].childNodes[0] != null)
								 	objoption.text = officeNm[iter].childNodes[0].text;
		
								objoption.value=officeNm[iter].getAttribute('value');
								
								try
								{
									objRoleCombo.add(objoption,null); 
								}
								catch(ex)
								{ 
									objRoleCombo.add(objoption); 
								}
							}
							
							if (RoleCombo != -1)
							{
								objRoleCombo.value = RoleCombo;
								RoleCombo = -1;
							}	
							else
								objRoleCombo.value = '0';	
						}
					} 	
			    }
			}
		}
	}
	
	var StartDate='';
	function checkStartDate()
	{
		StartDate=document.frmEmpJoiningDtls.dtJoinDate.value;	
		//alert("StartDate====InSide checkStartDate==="+StartDate);
	}
	
	function checkForDesg(flag,selDepartment,selLocation)
	{
		departmentCode = eval("document.frmEmpJoiningDtls."+ selDepartment +".value") ;
		locationCode = eval("document.frmEmpJoiningDtls."+ selLocation +".value") ;
		
		if(flag=='Salect')
		{ 	
			if(departmentCode=='0' ||locationCode=='0')
			{
				//document.getElementById('selDesignation').options[0].selected= true ;
				document.frmEmpJoiningDtls.selDesignation.disabled  = true; 
				
				document.getElementById('selPost').options[0].selected= true ;
					document.getElementById('selPost').length= 1 ;
						
					
					document.getElementById('selRole').options[0].selected= true ;
					document.getElementById('selRole').length= 1 ;
			}
			else 
			{
				//document.frmEmpJoiningDtls.selDesignation.disabled  = false; 
			}
		}
	}	
	
	var recType='';
	function checkRecType()
	{

		DesgnValue=document.getElementById('selDesignation').value ;
		postValue=document.getElementById('selPost').value ;
		if(checkHistoryRec[0].checked) 
		{
			recType=checkHistoryRec[0].value;
			
		}
		else 
		{
			recType=checkHistoryRec[1].value;
		}
		
		document.getElementById('hdnRecType').value=recType;    //add by sandip
		
		if(DesgnValue!='0')
		{
			checkStartDate();
			showOfficeName('post','','selOfficeName','selDesignation','selPost');
		}
		if(postValue!='0')
		{
			showOfficeName('role','','','','selPost','selRole');
		}
	}
	
	
	function resetDtls()
	{
		checkHistoryRec[1].checked='true';
		recType=checkHistoryRec[1].value;
		document.getElementById('hdnRecType').value=recType;    //add by sandip
		
		document.getElementById('txtOrderNo').value='';	
		document.getElementById('dtOrderDate').value='';
		
		document.getElementById('selOfficeType').options[0].selected= true ;
		
		document.getElementById('selOfficeName').options[0].selected= true ;
		document.getElementById('selOfficeName').length= 1 ;
		
		document.getElementById('selDesignation').options[0].selected= true ;
		document.frmEmpJoiningDtls.selDesignation.disabled  = true;
				
		document.getElementById('selPost').options[0].selected= true ;
		document.getElementById('selPost').length= 1 ;
		
		document.getElementById('selRole').options[0].selected= true ;
		document.getElementById('selRole').length= 1 ;
		
		document.getElementById('dtJoinDate').value='';	
		
	}

	function saveEmpJoinDtls()
	{
		var fieldArray = new Array('txtOrderNo','dtOrderDate','dtJoinDate','selOfficeType','selOfficeName','selDesignation','selPost');
		var statusValidation = validateSpecificFormFields(fieldArray);
		if(statusValidation)
		{
			document.frmEmpJoiningDtls.action="hrms.htm?actionFlag=saveEmpJoinDtls";
			document.frmEmpJoiningDtls.submit();
		}
	}
	
	function closeWindow()
	{
		window.close();
	}	
	
	//start Create Dynamic Post Combo for Vaccant post on Edit record for Current
	function createOptionForPost(objPostCombo,PostCombo)
	{		
		var z=objPostCombo;
 		var y=document.createElement('option');
		y.text=postName;
		y.value=PostCombo;
			
		try
		{
			z.add(y,null); 	    						
		}
		catch(ex)
		{	   			 
				z.add(y);	   			 				 
		}    
		postName = '';	
 		objPostCombo.value = PostCombo;
	}
	// Create Dynamic Post Combo for Vaccant post on Edit record for Current
</script>



<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#76A9E2"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="OfficeTypeInfo" value="${resultValue.arOfficeTypeInfo}"></c:set>
<c:set var="arDesignationVO" value="${resultValue.arDesignationVO}"></c:set>
<c:set var="joiningDtls" value="${resultValue.joiningDtls}"></c:set>
<c:set var="postingDtls" value="${resultValue.postingDtls}"></c:set>
<c:set var="joinDate" value="${resultValue.joinDate}"></c:set>
<c:set var="locCode" value="${resultValue.locCode}"></c:set>
<c:set var="postId" value="${resultValue.postId}"></c:set>
<c:set var="postName" value="${resultValue.postName}"></c:set>
<c:set var="roleId" value="${resultValue.roleId}"></c:set>
<c:set var="depCode" value="${resultValue.depCode}"></c:set>
<c:set var="recType" value="${resultValue.recType}"></c:set>
<c:set var="dsgnCode" value="${resultValue.dsgnCode}"></c:set>
<c:set var="selectedUserId" value="${resultValue.userId}"></c:set>
<c:set var="flagRec" value="${resultValue.flagRec}"></c:set>

<fmt:formatDate value="${joinDate}" var="jDate" pattern="dd/MM/yyyy"/>



<hdiits:form name="frmEmpJoiningDtls" validate="true" method="POST">
	
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="EMP_JOIN_DTLS" bundle="${EmpJoiningLables}"/></b></a></li>
	</ul>
	</div>
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<br>
	
	<c:if test="${empty joiningDtls}">
		<center><font color="red"><fmt:message key="recruitementAlert" bundle="${EmpJoiningLables}" /></font></center>
	</c:if>
	
	<table class="tabtable">
		<tr>
			<td width="25%"></td>
			<td><br></br><b><hdiits:caption captionid="REC_TYPE" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><br></br>
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="H" onclick="checkRecType()" captionid="HISTORY"  bundle="${EmpJoiningLables}"   />
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="C"  onclick="checkRecType()" captionid="CURRENT" bundle="${EmpJoiningLables}"  /></td>
			<td></td>
		</tr>
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="ORDER_NO" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:text captionid="ORDER_NO" bundle="${EmpJoiningLables}" name="txtOrderNo" caption="Order_No" default="${joiningDtls.postingOrderNo}" validation="txt.isrequired" mandatory="true"/></td>
			<td></td>
		</tr>
		
		<tr>	
			<td width="25%"></td>	
			<td><b><hdiits:caption captionid="DATE_ORDER" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:dateTime captionid="DATE_ORDER" bundle="${EmpJoiningLables}" name="dtOrderDate" caption="Order_Date" default="${joiningDtls.postingOrderDate}" validation="txt.isdt,txt.isrequired" mandatory="true"/></td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="eis.DATE_OF_JOINING" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:dateTime captionid="eis.DATE_OF_JOINING" bundle="${EmpJoiningLables}" name="dtJoinDate" caption="Join_Date" afterDateSelect="checkStartDate()" validation="txt.isdt,txt.isrequired" mandatory="true"/></td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="OFFICE_TYPE" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="OFFICE_TYPE" bundle="${EmpJoiningLables}" name="selOfficeType" sort="false"
						caption="Office_Type" validation="sel.isrequired" onchange="showOfficeName('location','selOfficeType','selOfficeName'),checkForDesg('Salect','selOfficeType','selOfficeName')" default="${depCode}" mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${EmpJoiningLables}" />
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
			<td><b><hdiits:caption captionid="OFFICE_NAME" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="OFFICE_NAME" bundle="${EmpJoiningLables}" name="selOfficeName" id="selOfficeName" sort="false"
						caption="Office_Nm" validation="sel.isrequired" onchange="checkForDesg('Salect','selOfficeType','selOfficeName');showOfficeName('post','','selOfficeName','selDesignation','selPost')" mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${EmpJoiningLables}" />
					</hdiits:option>
					
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="DSGN" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="DSGN" bundle="${EmpJoiningLables}" name="selDesignation" id="selDesignation" sort="false"
						caption="Designation" validation="sel.isrequired" onchange="checkStartDate();showOfficeName('post','','selOfficeName','selDesignation','selPost')"  mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${EmpJoiningLables}" />
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
			<td><b><hdiits:caption captionid="POST" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="POST" bundle="${EmpJoiningLables}" name="selPost" id="selPost" sort="false"
						caption="Post" validation="sel.isrequired" onchange="showOfficeName('role','','','','selPost','selRole')" mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${EmpJoiningLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><hdiits:caption captionid="ROLE" bundle="${EmpJoiningLables}"></hdiits:caption></b></td>
			<td><hdiits:select captionid="ROLE" bundle="${EmpJoiningLables}" name="selRole" sort="false"
						caption="Role">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${EmpJoiningLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
	</table>
	
	<c:if test="${not empty joiningDtls}">
		<table class="tabtable">
			<tr>
				<td align="center">
					<c:if test="${empty postingDtls}">	
						<br></br><hdiits:button name="btnEmpJoiningDtlSubmit" type="button" captionid="SUBMIT" bundle="${EmpJoiningLables}" onclick="saveEmpJoinDtls()"/>
					</c:if>
					<c:if test="${not empty postingDtls}">	
						<br></br><hdiits:button name="btnEmpJoiningDtlUpdate" type="button" captionid="UPDATE" bundle="${EmpJoiningLables}" onclick="saveEmpJoinDtls()"/>
					</c:if>
				
				<hdiits:button name="btnEmpJoiningDtlReset" type="button" captionid="RESET" bundle="${EmpJoiningLables}" onclick="resetDtls()"/>
				<hdiits:button name="btnEmpJoiningDtlClose" type="button" captionid="CLOSE" bundle="${EmpJoiningLables}" onclick="closeWindow()"/>
				
			</tr>
		</table>
	</c:if>
	
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	<hdiits:hidden name="hdnRecType" id="hdnRecType"></hdiits:hidden>
	
	<script type="text/javascript">
	
		var checkHistoryRec=document.frmEmpJoiningDtls.rdoTypeOfRec;
		checkHistoryRec[1].checked='true';
		recType=checkHistoryRec[1].value;
		
		var strJoinDate='${jDate}';
		if(strJoinDate!='')
		{
			StartDate=strJoinDate;
		}
		
		
		var strRecType='${recType}';
		if(strRecType!='')
		{
			recType=strRecType;
		}
		if(strRecType=="C")
		{
			checkHistoryRec[1].checked='true';
		}
		else if(strRecType=="H")
		{
			checkHistoryRec[0].checked='true';
		}
		
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
		
		PostCombo = postId;
		postName =  '${postName}';
		
		if(postId!="")
		{
			showOfficeName('post','','selOfficeName','selDesignation','selPost');
			document.getElementById('selPost').value =postId;
		}
		var roleId = '${roleId}';
		if(roleId!="")
		{
			showOfficeName('role','','','','selPost','selRole');
			document.getElementById('selRole').value =roleId;
		}
		
		document.frmEmpJoiningDtls.dtOrderDate.readOnly=true;
		document.frmEmpJoiningDtls.dtJoinDate.readOnly=true;
		
		
		var flagRec =  '${flagRec}';
		
		if(flagRec == 'true')
		{	
			checkHistoryRec[1].disabled=true;
			checkHistoryRec[0].disabled=true;
		}
		
		document.frmEmpJoiningDtls.selDesignation.disabled  = true; 
		
		document.frmEmpJoiningDtls.dtJoinDate.value='${jDate}';
		document.getElementById('hdnRecType').value=recType;  //add by sandip
		initializetabcontent("maintab");
		
	</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
			controlNames="txtOrderNo,dtOrderDate,dtJoinDate,selOfficeType,selOfficeName,selDesignation,selPost" />
		
	</div>

</hdiits:form>
<%
	} 
    catch (Exception e)
    {
	 e.printStackTrace();
	}
%>		
