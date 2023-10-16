<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script>

//varun from combo filling by designation post only unmapped post
function GetPostfromDesg()
{

		  var v=document.getElementById("selPost").length;
		
		  for(i=1;i<v;i++)
		  {
			  lgth = document.getElementById("selPost").options.length -1;
			  document.getElementById("selPost").options[lgth] = null;
		  }		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  
		  url= uri+'&dsgnId='+document.frmUserPostMapping.selDesignation.value;
		  

		  var actionf="GetPostfromDesignation";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  xmlHttp.onreadystatechange=GetPostsfromDesg;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		
}

function GetPostsfromDesg()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var post = document.getElementById("selPost");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('post-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    
     				    text =entries[i].childNodes[1].text; 
     				    
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            post.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       post.add(y); 
   			   	        }	
	                
	                 }
  }
}








//End by varun

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
			if(departmentCode=='0' ||locationCode=='0')
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
	}

	function saveUserPostMappingDtls()
	{
	
		var fieldArray = new Array('selUserName','selPost','dtStartDate');
		var statusValidation = validateSpecificFormFields(fieldArray);
		if(statusValidation)
		{
			document.frmUserPostMapping.btnEmpSubmit.disabled = true;
			document.frmUserPostMapping.action="hrms.htm?actionFlag=submitUserPostMappingDtls";
			document.frmUserPostMapping.submit();
		}
	}
	
	function onchangeDate()
	{
			var dateValue = document.frmUserPostMapping.dtEndDate.value;
			
			if (dateValue!= '')
			{
			
			document.frmUserPostMapping.flag.value = '0';
			document.frmUserPostMapping.flag[1].checked=true;
			
			}
			else{
			
			document.frmUserPostMapping.flag.value = '1';			
			document.frmUserPostMapping.flag[0].checked=true;
			}
	}
	
	function closeWindow()
	{
		document.frmUserPostMapping.action = "hrms.htm?actionFlag=UserPostMapping";
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
			alert("<fmt:message bundle='${userPostMapping}' key='ValidEndDate'/>");
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
<c:set var="userID" value="${resultValue.userID}"></c:set>
<c:set var="msg" value="${resultValue.msg}" ></c:set>
<c:set var="locCode" value="${resultValue.locCode}"></c:set>
<c:set var="postId" value="${resultValue.postId}"></c:set>

<c:set var="PsrNo" value="${resultValue.PsrNo}"></c:set>
<c:set var="BillNo" value="${resultValue.BillNo}"></c:set>

<c:set var="depCode" value="${resultValue.depCode}"></c:set>
<c:set var="dsgnCode" value="${resultValue.dsgnCode}"></c:set>
<c:set var="userID" value="${resultValue.userID}"></c:set>
<c:set var="startDate" value="${resultValue.startDate}"></c:set>
<c:set var="endDate" value="${resultValue.endDate}"></c:set>
<c:set var="empFname" value="${resultValue.empFname}"></c:set>
<c:set var="empLname" value="${resultValue.empLname}"></c:set>
<c:set var="activateFlagId" value="${resultValue.activateFlagId}"></c:set>
<hdiits:form name="frmUserPostMapping" validate="true" method="POST">
	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>User Post Mapping</b></a></li>
	</ul>
	</div>
	
    <div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br> <br> <br>	<br> 
	<table align="center" cellsapcing="2" cellpadding="2">
		
		
		<tr>
			<td width="25%"></td>
			<c:choose>
			<c:when test="${lUserPostRltId eq 0}">
			<td><b><fmt:message key="USER_NAME" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="USER_NAME" bundle="${userPostMapping}" name="selUserName" sort="false" validation="sel.isrequired"   mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
					<c:forEach var="userNameInfoVar" items="${arOrgUserMst}">
						<hdiits:option value="${userNameInfoVar.userId}">${userNameInfoVar.empFname} ${userNameInfoVar.empMname}  ${userNameInfoVar.empLname} (${userNameInfoVar.userName})</hdiits:option>
					</c:forEach>
					<!--<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option> -->
				</hdiits:select>
			</td>
			</c:when>
			<c:otherwise>
			<td><b><fmt:message key="USER_NAME" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="USER_NAME" bundle="${userPostMapping}" name="selUserName"  sort="false" validation="sel.isrequired" readonly="true" default="${userID}" mandatory="true">
					<c:forEach var="userNameInfoVar" items="${arOrgUserMst}">
					<c:forEach var="empFname" items="${empFname}">

					<c:forEach var="empLname" items=" ${empLname}">
						<hdiits:option value="${userNameInfoVar.userId}"> ${empFname} ${empLname} </hdiits:option>
					</c:forEach>
					</c:forEach>
					</c:forEach>
					
				</hdiits:select>
			</td>
			</c:otherwise>
			</c:choose>
			<td></td>
		</tr>
		
		<tr style="display:none">
			<td width="25%"></td>
			<td><b><fmt:message key="OFFICE_TYPE" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="OFFICE_TYPE" bundle="${userPostMapping}" name="selOfficeType" sort="false"
						caption="Office_Type" validation="sel.isrequired" onchange="showOfficeName('location','selOfficeType','selOfficeName'),checkForDesg('Salect','selOfficeType','selOfficeName')" default="${depCode}">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
					<c:forEach var="OfficeTypeInfoVar" items="${OfficeTypeInfo}">
						<hdiits:option value="${OfficeTypeInfoVar.depCode}">${OfficeTypeInfoVar.depName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td></td>
		</tr>

		<tr style="display:none">
			<td width="25%"></td>
			<td><b><fmt:message key="OFFICE_NAME" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="OFFICE_NAME" bundle="${userPostMapping}" name="selOfficeName" id="selOfficeName" sort="false"
						caption="Office_Nm" validation="sel.isrequired" onchange="checkForDesg('Salect','selOfficeType','selOfficeName')" >
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
					
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		
		<tr>
			<td width="25%"></td>
			<td><b><fmt:message key="DSGN" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="DSGN" bundle="${userPostMapping}" name="selDesignation" id="selDesignation" sort="false" mandatory="true"
						caption="Designation" validation="sel.isrequired" onchange="GetPostfromDesg()">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
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
			<td><b><fmt:message key="POST" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:select captionid="POST" bundle="${userPostMapping}" name="selPost" id="selPost" sort="false"
						caption="Post" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
				</hdiits:select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td width="25%"></td>
			<td><b><fmt:message key="StartDate" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:dateTime captionid="StartDate" bundle="${userPostMapping}" name="dtStartDate"  default="${startDate}" afterDateSelect="compareStartDateToEndDate()" validation="txt.isdt,txt.isrequired" mandatory="true"/></td>
			<td></td>
		</tr>

		<tr>
			<td width="25%"></td>
			<td><b><fmt:message key="ToDate" bundle="${userPostMapping}"/></b></td>
			<td><hdiits:dateTime captionid="ToDate" bundle="${userPostMapping}" name="dtEndDate"   default="${endDate}" afterDateSelect="compareStartDateToEndDate()" onblur="onchangeDate()" validation="txt.isdt,txt.isrequired"/></td>
			<td></td>
		</tr>
		<c:choose>
			<c:when test="${lUserPostRltId eq 0}">
		<tr>
		<td width="25%"></td>
		<td><b><fmt:message key="ActivateFlag" bundle="${userPostMapping}"/></b></td>
		<td><hdiits:radio caption='Yes'  bundle="${commonLables}" name="flagy" id="flagy" value="1" default="1" validation="sel.isradio" readonly="true" errCaption ="ActivateFlag"></hdiits:radio>
				<hdiits:radio caption='No' bundle="${commonLables}" name="flagn" id="flagn" readonly="true" value="0" validation="sel.isradio" ></hdiits:radio>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${activateFlagId eq 0}"> 
		<tr>
		<td width="25%"></td>
		<td><b><fmt:message key="ActivateFlag" bundle="${userPostMapping}"/></b></td>
		<td><hdiits:radio caption='Yes'  bundle="${commonLables}" name="flag"  id="flag" value="1"  validation="sel.isradio"  errCaption ="ActivateFlag"></hdiits:radio>
				<hdiits:radio caption='No' bundle="${commonLables}" name="flag" id="flag"  value="0" default="0" validation="sel.isradio" ></hdiits:radio>
			</td>
		</tr>
				</c:when>
			<c:otherwise>
		<tr>
		<td width="25%"></td>
		<td><b><fmt:message key="ActivateFlag" bundle="${userPostMapping}"/></b></td>
		<td><hdiits:radio caption='Yes'  bundle="${commonLables}" name="flag"  id="flag" value="1" default="1" validation="sel.isradio"  errCaption ="ActivateFlag"></hdiits:radio>
				<hdiits:radio caption='No' bundle="${commonLables}" name="flag" id="flag"  value="0"    validation="sel.isradio" ></hdiits:radio>
			</td>
		</tr>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose>
	</table>
	
	<table class="tabtable">
		<tr>
			<td align="center">
				<br></br><hdiits:button name="btnEmpSubmit" id = "bEJDS" type="button" captionid="SUBMIT" bundle="${userPostMapping}" onclick="saveUserPostMappingDtls()"/>
			<hdiits:button name="btnReset" id = "bR" type="button" captionid="RESET" style="display:none" bundle="${userPostMapping}" onclick="resetDtls()"/>
			<hdiits:button name="btnClose" type="button" captionid="close" bundle="${userPostMapping}" onclick="closeWindow()"/>
		</tr>
	</table>

	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	
	<script type="text/javascript">
	
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
		}
		
		initializetabcontent("maintab");

		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=UserPostMapping";
			document.frmUserPostMapping.action=url;
			document.frmUserPostMapping.submit();
		}
		
		
		
		
	</script>
		</div>
	<hdiits:hidden name="hdnUserId" id="hdnUserId" default="${lUserPostRltId}"/>
	
	<hdiits:hidden name="hiddenUserId" id="hiddenUserId" default="${userID}"/>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="selUserName,selPost,dtStartDate" />
</hdiits:form>
<%
	} 
    catch (Exception e)
    {
	 e.printStackTrace();
	}
%>		
