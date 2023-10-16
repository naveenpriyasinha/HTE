<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/acr/acrHierarchyGrp.js"/>"></script>
<script>
var displayFieldArray = new Array("userName","ShowDsgn","roleCmb","startDate","endDate");
var fieldArray = new Array("year","startDate","endDate","roleCmb","selectUserId","empBranch","empDesgn","userId","userName","ShowDsgn");
var hostno=new Number();
var dateOverLappingArr = new Array();
var currentYear = '0000';
var adminChkFlag=false,selfFlagValue=0;
function SearchEmp(host){
	if(host==4){adminChkFlag=false;}
	else{adminChkFlag=true;}
	var href='${rootUrl}'+'?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
var empArray = new Array();
function empSearch(from)
{
	for(var i=0; i<from.length; i++){		
		empArray[i] = from[i].split("~"); 		
	}
	
	if(from.length>0)
	{
		var single = empArray[0];
		
		if(adminChkFlag==false)
		{
			document.getElementById("adminName").value=single[1];
			document.getElementById("adminDesi").value=single[7];
			document.getElementById("adminId").value=single[2];
		}
		else
		{
			document.getElementById("userName").value=single[1];
			document.getElementById("ShowDsgn").value=single[7];
			document.getElementById("selectUserId").value=single[2];
		}
	}
}
function Add()
{
	var validateArr = new Array("startDate","endDate","roleCmb");	
	var validData = validateSpecificFormFields(validateArr);
	if(validData==true)
	{
		sDate = document.IniateRequest.startDate.value;
		eDate = document.IniateRequest.endDate.value;
		var ans = acrDateDiff(sDate,eDate);
		if(ans==false)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');
			document.IniateRequest.startDate.value='';
			document.IniateRequest.endDate.value='';
			return;
		}
		else
		{
			var sCheckDate = "01/"+"04/"+currentYear;
			var eCheckDate = "31/"+"03/"+parseInt(currentYear+1);
			ans = checkDateInBetween(sDate,eDate,sCheckDate,eCheckDate);
			if(ans==false)
			{
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.betweenDate"/> '+sCheckDate + " - " + eCheckDate);
				document.IniateRequest.startDate.value='';
				document.IniateRequest.endDate.value='';
				return;
			}
			else
			{
				roleType = document.getElementById("roleCmb").value;				
				dateOverLappingArr.push(roleType+"^"+sDate+"^"+eDate);				
				ans = checkForDateOverLapping(dateOverLappingArr);	
				if(ans==false)
				{
					alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.overlappingDate"/> ');
					dateOverLappingArr.pop();					
					return;
				}
			}
		}
		selectUserId=document.getElementById('selectUserId').value;
		if(selectUserId==0)
		{
			dateOverLappingArr.pop();
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserName"/>');
		}
		else
		{
			addOrUpdateRecord("processResponse", "acrUserSetHierarchy", fieldArray, true);
		}
	}
}
function processResponse()
{	
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;				    									
			addDataInTable('currTable', 'encXML', displayFieldArray, 'editRecord', 'deleteMyRecord', '') 
			resetData();
		}
	}
}
function Update()
{
	var validateArr = new Array("startDate","endDate","roleCmb");
	var validData = validateSpecificFormFields(validateArr);
	if(validData==true)
	{	
		sDate = document.IniateRequest.startDate.value;
		eDate = document.IniateRequest.endDate.value;
		var ans = acrDateDiff(sDate,eDate);
		if(ans==false)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');
			document.IniateRequest.startDate.value='';
			document.IniateRequest.endDate.value='';
			return;
		}
		else
		{
			var sCheckDate = "01/"+"04/"+currentYear;
			var eCheckDate = "31/"+"03/"+parseInt(currentYear+1);
			ans = checkDateInBetween(sDate,eDate,sCheckDate,eCheckDate);
			if(ans==false)
			{
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.betweenDate"/> '+sCheckDate + " - " + eCheckDate);
				document.IniateRequest.startDate.value='';
				document.IniateRequest.endDate.value='';
				return;
			}
			else
			{
				var totaldays = getTotalDays(sDate,eDate);
				if(totaldays>=90 || totaldays>='90'){}
				else {
					ans=false;
					alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.totalNoDays"/>' + " : " + totaldays);
					document.IniateRequest.startDate.focus();
				}				
				if(ans==true)
				{
					roleType = document.getElementById("roleCmb").value;				
					dateOverLappingArr.push(roleType+"^"+sDate+"^"+eDate);				
					ans = checkForDateOverLapping(dateOverLappingArr);	
					if(ans==false)
					{
						dateOverLappingArr.pop();
						alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.overlappingDate"/> ');
						return;
					}
				}
			}
		}
		selectUserId=document.getElementById('selectUserId').value;
		if(selectUserId==0)
		{
			dateOverLappingArr.pop();
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserName"/>');
		}
		else
		{							
			addOrUpdateRecord("updateRecord", "acrUserSetHierarchy", fieldArray, true);
		}
	}
}
function updateRecord()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{	
			updateDataInTable('encXML',displayFieldArray);			
			document.getElementById('brnAdd').style.display="";
			document.getElementById('brnUp').style.display="none";
			resetData();
		}
	}
}
function editRecord(rowId)
{
	document.getElementById('brnUp').style.display="";
	document.getElementById('brnAdd').style.display="none";
	sendAjaxRequestForEdit(rowId, 'populateForm', true) 
}
function getDateInProperFormat(v)
{
	if(v.indexOf("-")!=-1)
	{
		v=v.substring(0,10);				
		var splitDate=v.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;	
	}
	return v;
}
function populateForm()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;
			var xmlDOM=getDOMFromXML(xmlStr);						
			document.IniateRequest.startDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'startDate'));
			document.IniateRequest.endDate.value=getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'endDate'));
			document.getElementById("roleCmb").value=getXPathValueFromDOM (xmlDOM, 'roleCmb');
			document.getElementById("userName").value=getXPathValueFromDOM (xmlDOM, 'userName');
			document.getElementById("selectUserId").value=getXPathValueFromDOM (xmlDOM, 'roleUserId');
			document.getElementById("ShowDsgn").value=getXPathValueFromDOM (xmlDOM, 'roleDesgn');	
			
			var index = dateOverLappingArr.indexOf(document.getElementById("roleCmb").value
												+"^"+document.IniateRequest.startDate.value
												+"^"+document.IniateRequest.endDate.value);			
			if(index!=-1)
			{
				dateOverLappingArr.splice(index,1);
			}
		}
	}
}
function deleteMyRecord(rowId)
{
	deleteRecord(rowId);
}
function resetData()
{
	var resetArr =new Array('selectUserId','startDate','endDate','userName');	
	for (var x=0;x<resetArr.length;x++)
	{
		document.getElementById(resetArr[x]).value="";
	}
	document.getElementById('roleCmb').value="Select";
	
}
function showTable(flag)
{	
	var hideShowArr =new Array('currTable','adminTable1','adminTable2','adminTable','btnTable','acr_init_field1','acr_init_field2','acr_init_field3');
	if(flag==1)
	{
		for (var x=0;x<hideShowArr.length;x++)
		{
			document.getElementById(hideShowArr[x]).style.display="";
		}
	}
	else if(flag==0)
	{
		for (var x=0;x<hideShowArr.length;x++)
		{
			document.getElementById(hideShowArr[x]).style.display="none";
		}		
	}
}
function submit1()
{
	document.IniateRequest.Submit.disabled=true;
	showProgressbar("Please Wait...");		
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hrms.htm?actionFlag=ACRInitiateAccepted";	
	document.IniateRequest.submit();	
}

function submit2()
{
	document.IniateRequest.Submit.disabled=true;
	showProgressbar("Please Wait...");		
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hrms.htm?actionFlag=ACRHierarchyChangeRequestWF";	
	document.IniateRequest.submit();	
}

function validation()
{	
	var radioValue=0;
	var radio=document.getElementsByName("R1");
	for(var j = 0; j < radio.length; j++)
	{
		if(radio[j].checked)
		{
			radioValue = radio[j].value;
		}
	}
	if(radioValue=='yes')
	{	
		var validateArr = new Array("reason");
		var validData = validateSpecificFormFields(validateArr);
		var userId =  document.getElementById("userId").value;
		var adminId = document.getElementById("adminId").value;
		if(adminId=='' || adminId==0 || adminId==null)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectAdmin"/>');
			validData=false;
		}
		if(validData==true && userId!=adminId)
		{
			if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SubmitForm"/>')==true)		
			{
				submit2();		
			}
		}
		else if(userId==adminId)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.ValidAdminUser"/>');
			document.getElementById("adminId").value=0;
			document.getElementById("adminName").value="";
			document.getElementById("adminDesi").value="";
			SearchEmp(4);
		}		
	}
	else if(selfFlagValue==1)
	{
		if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SubmitForm"/>')==true)		
		{			
			submit1();
		}
	}
	else
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.NoSelfAppRight"/>');	
	}	
}
function isValidData(txtarea)
{	
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
		
	str1="`~!#$%^&*+|"
	for(i=0;i<len;i++)
	{		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			txtarea.focus();
			return;
		}		
	}
	return;
}
function closeButtonHandler()
{
	document.IniateRequest.method="POST";
	document.IniateRequest.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";	
	document.IniateRequest.submit();	
}
function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}
</script> 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="authorityPointLst" value="${resValue.authorityMissPoint}"></c:set>
<c:set var="parentDataMap" value="${resValue.parentDataMap}"></c:set>
<c:set var="parentDataList" value="${resValue.parentDataList}"></c:set>
<c:set var="count" value="${resValue.count}"></c:set>
<c:set var="groupFlag" value="${resValue.groupFlag}"></c:set>
<c:set var="allRoleList" value="${resValue.allRoleList}"></c:set>
<c:set var="trnList" value="${resValue.trnList}"></c:set>
<c:set var="year" value="${resValue.minYear}"></c:set>
<c:set var="selfFlag" value="${resValue.selfFlag}"></c:set>
<c:set var="authoMiss" value="${resValue.authoMiss}"></c:set>
<script>
	currentYear = ${year};
</script>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<hdiits:form name="IniateRequest" validate="true"  encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}"/>
		</b></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
		<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"></jsp:include>

<BR>
<c:if test="${not empty trnList}">
		<table id="recordInserted" align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
		<tr>
			<td colspan="10" align="center" >
			<font color="red"><b>
				<fmt:message key="HR.ACR.RecordAlreadyInserted" bundle="${commonLables}" />
				<c:out value=" ${year} - ${year+1}"></c:out>
			</b></font>							
			</td>
		</tr>
		</table>
</c:if>		
<c:if test="${resValue.hieNotApp eq '1' and empty trnList }">
		<table align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
		<tr>
			<td colspan="10" align="center" >
			<font color="red"><b>
				<fmt:message key="HR.ACR.HieReqNotApp" bundle="${commonLables}" />				
			</b></font>							
			</td>
		</tr>
		</table>
</c:if>	
<c:if test="${resValue.hieNotApp ne '1' and empty trnList and not empty authorityPointLst}">
		<table align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
		<tr>
		<td colspan="10" align="center" >
			<font color="red"><b>					
			<fmt:message key="HR.ACR.PointNotFound" bundle="${commonLables}" />	
			<c:forEach items="${authorityPointLst}" var="autho">								
				<c:out value="${autho}, " />																				
			</c:forEach>	
			</b></font>	
		</td>
		</tr>
		</table>
</c:if>	
<c:if test="${resValue.hieNotApp ne '1' and empty trnList and authoMiss ne '' and authoMiss ne null}">
	<table align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
		<tr>
		<td colspan="10" align="center" >
			<font color="red"><b>
			<fmt:message key="HR.ACR.HieNotFound" bundle="${commonLables1}" />											
				<c:out value=" : ${authoMiss}"/>																							
			</b></font>	
		</td>
		</tr>
	</table>
</c:if>
<BR>
<BR>
		<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="HR.ACR.Initiate" id="acr_init_field">
			<c:forEach items="${allRoleList}" var="parent" varStatus="x">
				<c:set value="${parent.lookupName}" var="flag"></c:set>
				<table  border="1" borderColor="BLACK" style="border-collapse: collapse;" id="default" width="100%">															
				<c:if test="${parent.lookupName ne 'acrRoleSelf'}">					
					<tr bgcolor="#C9DFFF">
						<td colspan="10" align="center">						
								<b><c:out value="${parent.lookupDesc}"></c:out></b>
						</td>																				
					</tr>
					<tr>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" /></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.District" bundle="${commonLables}" /></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" /></b>
						</td>
						<td width="5%" align="center">
							<b><hdiits:caption captionid="HR.ACR.Time" bundle="${commonLables}"/></b>
						</td>						
					</tr>	
				</c:if>			
					<c:set value="1" var="recFounfFlag"></c:set>									
					<c:forEach items="${parentDataList}" var="parentObj" varStatus="x">					
						<c:set value="0" var="recFounfFlag"></c:set>
						<c:if test="${flag eq parentObj.cmnLookupObj.lookupName}">
							<c:choose>
								<c:when test="${parentObj.name ne 'NO^ACR'}">
								<tr>
									<td width="10%" align="left">
										<c:out value="${parentObj.name}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.district}"></c:out>
									</td>
									<td width="10%" align="left">
										<c:out value="${parentObj.desgn}"></c:out>      
									</td>							
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>								
								</c:when>
								<c:otherwise>
								<tr>
									<td width="10%" align="left"><b><fmt:message key="HR.ACR.Noacr" bundle="${commonLables}" /></b></td>
									<td width="10%" align="left"><b><fmt:message key="HR.ACR.Noacr" bundle="${commonLables}" /></b></td>
									<td width="10%" align="left"><b><fmt:message key="HR.ACR.Noacr" bundle="${commonLables}" /></b></td>
									<td width="10%" align="left">
										<c:out value="${parentObj.time}"></c:out>      
									</td>
								</tr>
								</c:otherwise>
							</c:choose>	
						</c:if>											
					</c:forEach>					
					<c:if test="${empty parentDataList or recFounfFlag eq 1 }">					
						<c:if test="${parent.lookupName ne 'acrRoleSelf'}">
							<tr><td colspan="10" align="center">
							<b><fmt:message key="HR.ACR.NORECORD" bundle="${commonLables}"/> </b></td></tr>
						</c:if>
					</c:if>
				</table>
			</c:forEach>				
		</hdiits:fieldGroup>	
			<br>
			<table>
			<tr>
			<td>
				<c:if test="${resValue.hieNotApp ne '1' and empty authorityPointLst and authoMiss eq ''}">
					<c:if test="${empty parentDataList}">				
						<b><hdiits:caption captionid="HR.ACR.NoDefaultMsg" bundle="${commonLables}" /></b>					
					</c:if>
				</c:if>
			</td>
			</tr>
			</table>	
			<br><br>
			<table align="center" id="defaultButtonTable">
				<tr>
				<td align="center">
					<c:if test="${not empty parentDataList}">
						<c:if test="${empty trnList and selfFlag eq 'Y'}">						
							<script>
								selfFlagValue=1;
							</script>
							<hdiits:button captionid="HR.ACR.Accept" style="display:none" bundle="${commonLables}" name="Accept" type="button" onclick="submit1();"/>	
						</c:if>
					</c:if>
				</td>				
				</tr>
			</table>				
			<br>
			<c:if test="${resValue.hieNotApp ne '1' and empty authorityPointLst and authoMiss eq ''}">
				<c:if test="${empty trnList and not empty parentDataList}">
					<table width="100%">				
					<tr colspan="4">
							<td width="40%" align="left">
								<b>
									<hdiits:caption captionid="HR.ACR.CheckForHierarchy" bundle="${commonLables}" id="NoDefaultMsg"/>
								</b>
							</td>
							<td width="20%" align="left">
								<INPUT type="radio" name="R1" value="no" onclick="showTable(0)" checked="checked"><fmt:message key="HR.ACR.ButtonNo" bundle="${commonLables}" />
								<INPUT type="radio" name="R1" value="yes" onclick="showTable(1)"><fmt:message key="HR.ACR.ButtonYes" bundle="${commonLables}" />
							</td>
							<td width="20%" align="left"/>
							<td width="20%" align="left"/>							
					</tr>
					</table>
				</c:if>			
			</c:if>
			<br><br>
			<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="HR.ACR.GroupDetails" id="acr_init_field1">
				<table id="adminTable" style="display: none" width="100%">				
				<tr>
					<td width="25%"><hdiits:caption captionid="HR.ACR.Location" bundle="${commonLables}"/></td>
					<td width="25%"><c:out value="${resValue.Location}"></c:out></td>
					<td width="25%"><hdiits:caption captionid="HR.ACR.Branch" bundle="${commonLables}"/></td>
					<td width="25%"><c:out value="${resValue.Branch}"></c:out></td>
				</tr>
				<tr>
					<td width="25%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
					<td width="25%"><c:out value="${resValue.dsgn}"></c:out></td>
					<td width="25%"><hdiits:caption captionid="HR.ACR.Year" bundle="${commonLables}"/></td>
					<td width="25%"><c:out value="${year} - ${year+1}"></c:out></td>
				</tr>
				<tr>
					<td width="25%"><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"></hdiits:caption></td>
					<td width="25%"><hdiits:dateTime name="startDate"  validation="txt.isdt,txt.isrequired" captionid="HR.ACR.StartDate" mandatory="true" bundle="${commonLables}" afterDateSelect="" maxvalue="01/01/2999"></hdiits:dateTime></td>
					<td width="25%"><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>
					<td width="25%"><hdiits:dateTime name="endDate" validation="txt.isdt,txt.isrequired" captionid="HR.ACR.EndDate" mandatory="true" bundle="${commonLables}" maxvalue="01/01/2999"></hdiits:dateTime></td>
				</tr>					
				<tr><td width="25%">
					<hdiits:caption captionid="HR.ACR.RoleSelect" bundle="${commonLables}"/>
					</td>
					<td width="25%">
						<hdiits:select name="roleCmb" id="roleCmb" mandatory="true" validation="sel.isrequired" captionid="HR.ACR.RoleSelect" bundle="${commonLables}">
						<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
						<c:forEach items="${allRoleList}" var="role">
							<c:if test="${role.lookupName ne 'acrRoleSelf'}">
								<option value="<c:out value="${role.lookupName}" />"><c:out
									value="${role.lookupDesc}" /></option>
							</c:if>	
						</c:forEach>
						</hdiits:select>
					</td>
					<td width="25%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>
					<td width="25%"><hdiits:text name="userName" id="userName" readonly="true" style="background-color:lightblue"></hdiits:text>
							<hdiits:image id="img" tooltip="Search User" onmouseover="showCursorAsHand(this)" source="./images/search_icon.gif" onclick="SearchEmp(0);"> </hdiits:image> 
							<hdiits:hidden id="selectUserId" name="selectUserId" default="0"></hdiits:hidden>
					</td>
				</tr>			
			</table>
			</hdiits:fieldGroup>
			<br><br>
			<hdiits:fieldGroup bundle="${commonLables}" titleCaptionId="HR.ACR.CurrTable" id="acr_init_field2">
				<table id="currTable" border="1" borderColor="BLACK" style="border-collapse: collapse; display: none;" width="100%">
					<tr bgcolor="#C9DFFF">
						<td align="center"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>
						<td align="center"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
						<td align="center"><hdiits:caption captionid="HR.ACR.RoleType" bundle="${commonLables}"/></td>			
						<td align="center"><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"></hdiits:caption></td>
						<td align="center"><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>
						<td align="center"><hdiits:caption captionid="HR.ACR.Action" bundle="${commonLables}"/></td>
					</tr>
				</table>
			</hdiits:fieldGroup>
			<br><br>
			<table id="btnTable" style="display: none" align="center">
				<tr>
					<td colspan="10" align="center">
						<hdiits:button id="brnAdd" captionid="HR.ACR.BtnAdd" bundle="${commonLables}" name="brnAdd" type="button"  onclick="Add();"/>
						<hdiits:button captionid="HR.ACR.BtnUpdate" bundle="${commonLables}" name="brnUp"  id="brnUp" type="button"  onclick="Update();" style="display:none"/>
					</td>
				</tr>
			</table>
			<br><br>			
				<hdiits:fieldGroup id="acr_init_field3" bundle="${commonLables}" titleCaptionId="HR.ACR.AdminOfficer">
					<table id="adminTable1" style="display:none" width="100%">
					<tr>									
							<td width="25%" align="left">
								<b><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/>:</b>
							</td>
							<td width="25%" align="left">
								<hdiits:text default="${resValue.adminName}" size="15" id="adminName"  name="adminName"   caption="${labels}"  readonly="true" mandatory="true"  style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
								<hdiits:image id="img4" tooltip="Search Administrator" onmouseover="showCursorAsHand(this)" source="./images/search_icon.gif" onclick="SearchEmp(4);"> </hdiits:image> 
								<hdiits:hidden name="adminId" id="adminId" default="${resValue.adminId}"/>
							</td>
							<td width="25%" align="left">
								<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/>:</b>
							</td>
							<td width="25%" align="left">
								<hdiits:text maxlength="30" size="15" id="adminDesi" default="${resValue.adminDesi}" name="adminDesi"  caption="${labels}"  readonly="true" mandatory="true"  style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
							</td>
					</tr>
					</table>					
				</hdiits:fieldGroup>
				<BR>
				<table id="adminTable2" style="display: none" width="100%">				
				<tr>
					<td>				
						<b><hdiits:caption captionid="HR.ACR.Reason" bundle="${commonLables}" id="Reason"/>:</b>						
					</td>				
					<td>
					<hdiits:textarea mandatory="true" rows="3" cols="100"  validation="txt.isrequired"
	                                    name="reason" tabindex="7" id="reason" onblur="isValidData(this)"
	                                   	captionid="HR.ACR.Reason" 
	                                    bundle="${commonLables}" maxlength="4000"/>
	                </td>
	                <td width="25%" style="color: red; font-family: Verdana; font-weight: bold; font-size: 8px;">
							<hdiits:caption captionid="HR.ACR.MaxLimit" bundle="${commonLables}" />
					</td>
					</tr>
					</table>				
				<br>
				<c:if test="${not empty parentDataList  and authoMiss eq '' and empty authorityPointLst}">
					<table width="100%">
					<tr align="center">
						<td width="25%"></td>						
						<td align="right" width="25%">
							<hdiits:button captionid="HR.ACR.Submit" bundle="${commonLables}" name="Submit" type="button"  onclick="validation();"/>						
						</td>
						<td align="left" width="25%">
							<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="SetDefault1" type="button" onclick="closeButtonHandler()"/>		
						</td>
						<td width="25%"></td>
					</tr>					
					</table>			
				</c:if>
<hdiits:hidden name="empBranch" id="empBranch" default="${resValue.BranchCode}"/>
<hdiits:hidden name="empDesgn" id="empDesgn" default="${resValue.dsgnCode}"/>
<hdiits:hidden name="userId" id="userId" default="${resValue.userId}"/>
<hdiits:hidden name="ShowDsgn" id="ShowDsgn" default=""/>
<hdiits:hidden name="selfFlag" id="selfFlag" default="${selfFlag}"/>
<hdiits:hidden name="year" id="year" default="${year}"/>
<hdiits:validate locale="${locale}" controlNames="" />
</div>
</div>
</hdiits:form>	
<script type="text/javascript">
	initializetabcontent("maintab")	
	showTable(0)
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	