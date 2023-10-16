<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request" />
<script type="text/javascript" src="<c:url value="/script/hrms/hr/acr/acrHierarchyGrp.js"/>"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
var totalUser = new Array();
var countArr=0,totalRow=0,userIdValue=0,empAddFlag=false;
var selectedRadioValue='',selectedRadioRow='',hiddenObj='';
var empArray = new Array();
var empDtl = '';
var dateOverLappingArr = new Array();
function submitMe()
{
	var sCheckDate = "01/"+"04/"+window.opener.currentYear;
	var eCheckDate = "31/"+"03/"+parseInt(window.opener.currentYear+1);
	var len = document.getElementsByName('rad').length;
	var validateArrName = new Array();	
	for(var i=0;i<len;i++)
	{
		var str = document.getElementsByName('rad')[i].value;
		str =str.substring(3, str.length);
		sDate = document.forms[0].elements('startDate'+str).value;
		eDate = document.forms[0].elements('endDate'+str).value;
		if(sDate==''){alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');document.forms[0].elements('startDate'+str).focus();return ;}
		else if(eDate==''){alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');document.forms[0].elements('endDate'+str).focus();return ;}
	}
	dateOverLappingArr = new Array();		
	for(var i=0;i<len;i++)		
	{
		var str = document.getElementsByName('rad')[i].value;
		str =str.substring(3, str.length);
		sDate = document.forms[0].elements('startDate'+str).value;
		eDate = document.forms[0].elements('endDate'+str).value;
		roleType = document.forms[0].elements('roleType'+str).value
		dateOverLappingArr.push(roleType+"^"+sDate+"^"+eDate);
		ans = acrDateDiff(sDate,eDate);
		if(ans==false)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');	
			document.forms[0].elements('startDate'+str).value='';
			document.forms[0].elements('endDate'+str).value='';		
			return;
		}
		else
		{
			ans = checkDateInBetween(sDate,eDate,sCheckDate,eCheckDate);
			if(ans==false)
			{				
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.betweenDate"/> '+sCheckDate + " - " + eCheckDate);				
				document.forms[0].elements('startDate'+str).value='';
				document.forms[0].elements('endDate'+str).value='';
				return;
			}
			var totaldays = getTotalDays(sDate,eDate);
			if(totaldays>=90 || totaldays>='90'){}
			else 
			{
				ans=false;
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.totalDays"/>');				
				return;
			}
		}
	}
	ans = checkForDateOverLapping(dateOverLappingArr);	
	if(ans==false)
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.overlappingDate"/>');
		return;
	}
	if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SubmitForm"/>')==true)
	{
		var rdo = document.getElementsByName("R1");
		for(var j = 0; j < rdo.length; j++) 
		{
			if(rdo[j].checked) 
			{
				document.getElementById('lStrSelfFlag').value=rdo[j].value;
			}
		}			
		if(countArr==0){document.getElementById("selectedUser").value="";}
		else{document.getElementById("selectedUser").value=totalUser;}
		var fieldArray = new Array();
		for (var lCntr = 0; lCntr < document.editACRHieScreen.elements.length; lCntr++)
		{
			if(document.editACRHieScreen.elements[lCntr].id=='' || document.editACRHieScreen.elements[lCntr].id==null) 
			{
				fieldArray[lCntr]=document.editACRHieScreen.elements[lCntr].name;
			}
			else
			{
				fieldArray[lCntr]=document.editACRHieScreen.elements[lCntr].id;
			}
		}
			
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		
		var reqBody = getRequestBodyValue(fieldArray);
		methodName="processResponse";
		var url='actionFlag=editSetHierachyInAcr&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName);}
		xmlHttp.open('POST', 'hdiits.htm', true);
      	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      	xmlHttp.setRequestHeader("Content-length", url.length);
      	xmlHttp.setRequestHeader("Connection", "close");
      	xmlHttp.send(url);
	}
}
function getRequestBodyValue(fieldArray)
{
		var aParams = new Array();
		for(var i = 0; i < fieldArray.length; i++)
		{
			try
			{
				if(fieldArray[i]!=undefined)
				{
					var sParam = document.getElementById(fieldArray[i]).name;
					sParam += "=";
		
					if(document.getElementById(fieldArray[i]).type == 'radio') 
					{
						var rdo = document.getElementsByName(fieldArray[i]);
						for(var j = 0; j < rdo.length; j++) 
						{
							sParam += rdo[j].value;	
							aParams.push(sParam);							
						}			
					}
					else if(document.getElementById(fieldArray[i]).type == 'select-multiple')
					{
						var lstbox = document.getElementById(fieldArray[i]);
						for(var j=0; j<lstbox.length; j++)
						{
							if(lstbox[j].selected)
							{
								var a = sParam;
								a += lstbox[j].value;
								aParams.push(a);		
							}
						}
					}
					else 
					{				
						var elementArr = document.getElementsByName(fieldArray[i]);				
						for(var j = 0; j < elementArr.length; j++) 
						{					
							var temp = elementArr[j].name+"=";
							temp += elementArr[j].value;
							aParams.push(temp);
						}			
		
						//sParam += document.getElementById(fieldArray[i]).value;
						//aParams.push(sParam);		
					}
				}
			}catch(ex){}
		}
		return aParams.join("&");
}
function processResponse()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{ 
			window.opener.xmlResponseData=xmlHttp.responseText;
			window.opener.processResponse12();
			window.close();
		}
	}
}
function empSearch(from)
{
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 		
	}	
	if(from.length>0 && empAddFlag==false)
	{
		var single = empArray[0];
		for(var i=0;i<totalUser.length;i++)
		{
			if(totalUser[i]==selectedRadioValue)
			{			
				totalUser.splice(i,1);
				countArr--;
			}
		}
		totalUser[countArr]=single[2];
		countArr++;
		var trow = document.getElementById(selectedRadioRow);
		hiddenObj.value=single[2];
		trow.cells[1].innerHTML = single[1];
		trow.cells[2].innerHTML = single[7];		
	}
	else if (from.length>0)
	{
//validateACRUser		
		var single = empArray[0];
		var validateThisUser =false;
		for(var i=0;i<totalUser.length;i++)
		{
			if(totalUser[i]==single[2])
			{			
				validateThisUser=true;
			}
		}
		if(validateThisUser == false)
		{
			empDtl = empArray[0];		
			document.getElementById("selectedUser").value=single[2];
			var validateNewUser = new Array("selectedUser","userId","year");
			addOrUpdateRecord('addRow','validateACRUser',validateNewUser,true);		
	    }
	    else
	    {
	    	alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserAlreadySelected"/>');	    	
	    }
	}	
}
function addRow()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) 
	{		
		var dscXML=xmlHttp.responseText;			
		var xmlDOM = getDOMFromXML(dscXML);
		
		var self=xmlDOM.getElementsByTagName('VALID');		
		var selfValue=self[0].childNodes[0].text;
		if(selfValue=='D')
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.DsgnLevel"/>');
		}
		if(selfValue=='S')
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SameUser"/>');
		}
		if(selfValue=='A' && empDtl!='')   
		{
				document.getElementById("tr1").style.display='none';
				document.getElementById("btnEdit").style.display='';
				document.getElementById("btnDelte").style.display='';
				document.getElementById("btnSubmit").style.display='';
				
				var single = empDtl;
				empDtl='';		
				pkVal=0;
				totalUser[countArr]=single[2];
				countArr++;		
				roleUserId=single[2];
				nameValue=single[1];		
				disValue=single[7];
				field=document.getElementById("roleCmb");
				roleTypeValue=field.value;
				roleDescValue=field.options[field.selectedIndex].text;
				totalRow++;
				trow=document.getElementById("dataTable").insertRow();
				trow.id="row"+totalRow;
				trow.insertCell(0).innerHTML = "<INPUT type=radio  name='rad' id='rad"+totalRow+"_"+userIdValue+"' value='"+trow.id+"' />"
												+"<INPUT type=hidden id='roleType"+totalRow+"' name='roleType"+totalRow+"' value='"+roleTypeValue+"' />"
												+"<INPUT type=hidden id='rad"+totalRow+"_"+userIdValue+"hidden' name='rad"+totalRow+"_"+userIdValue+"hidden' value='"+roleUserId+"' />"
												+"<INPUT type=hidden id='pk"+totalRow+"hidden' name='pk"+totalRow+"hidden' value='"+pkVal+"' />";
				trow.cells[0].align="center";
				trow.insertCell(1).innerHTML = nameValue;
			    trow.insertCell(2).innerHTML = disValue;
			    trow.insertCell(3).innerHTML = roleDescValue;
			    trow.insertCell(4).innerHTML="<input type='text' name='startDate"+totalRow+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur=return checkDate('startDate"+totalRow+"','Please enter valid $CPTN','Start Date','0','01/01/2999','Please enter $CPTN less than 01/01/2999') value=''  maxlength='10' size='10'/>"
			    							+"<img style='cursor:hand' id='img_startDate' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('startDate"+totalRow+"',375,570,'','startDate"+totalRow+",Please~enter~valid~$CPTN,Start~Date,0,01/01/2999,Please~enter~$CPTN~less~than~01/01/2999') >"
			    							+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";	
			    
			    trow.insertCell(5).innerHTML="<input type='text' name='endDate"+totalRow+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur=return checkDate('endDate"+totalRow+"','Please enter valid $CPTN','Start Date','0','01/01/2999','Please enter $CPTN less than 01/01/2999') value=''  maxlength='10' size='10'/>"
			    							+"<img style='cursor:hand' id='img_endDate' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('endDate"+totalRow+"',375,570,'','endDate"+totalRow+",Please~enter~valid~$CPTN,Start~Date,0,01/01/2999,Please~enter~$CPTN~less~than~01/01/2999') >"
			    							+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";	
	   }
	   document.getElementById("roleCmb").value="Select";	   
	}
} 
function chkForRadio()
{
	var len = document.getElementsByName('rad').length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName('rad')[i].status==true)
		{	
			hiddenObj = document.getElementById(document.getElementsByName('rad')[i].id+"hidden");		
			selectedRadioValue = document.getElementById(document.getElementsByName('rad')[i].id+"hidden").value;
			selectedRadioRow = document.getElementsByName('rad')[i].value;
			return true;
		}
	}
	return false;
}
function openUser(flag)
{
	empAddFlag=false;
	if(flag==1)	
	{
		if(document.getElementById("roleCmb").value!="Select")
		{
			empAddFlag=true;
			var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
			win_E = window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');		
		}
	}
	else
	{
		if(chkForRadio()==true)
		{
			var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
			win_E = window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');	
		}
		else{alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserName"/>');}
	}
}
function deleteMe()
{
	if(chkForRadio()==true)
	{
		for(var i=0;i<document.getElementsByName('rad').length;i++)
		{
			if(document.getElementsByName('rad')[i].status==true)
			{
				delRow = document.getElementById(document.getElementsByName('rad')[i].value);
				delRow.parentNode.deleteRow(delRow.rowIndex);				
				for(var i=0;i<totalUser.length;i++)
				{
					if(totalUser[i]==selectedRadioValue)
					{			
						totalUser.splice(i,1);
						countArr--;
					}
				}
			}
		}
		try{if(document.getElementsByName('rad').length==0){document.getElementById("tr1").style.display='';}}catch(ex){document.getElementById("tr1").style.display='';}
	}
	else{alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserName"/>');}
}
</script>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="xml" value="${resValue.xml}"></c:set>
<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="lStrSelfFlag" value="${resValue.lStrSelfFlag}"></c:set>
<c:set var="xmlData" value="${resValue.xmlData}"></c:set>
<c:set var="acrRole" value="${resValue.acrRole}"></c:set>
<hdiits:form name="editACRHieScreen" method="POST" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b> 
			<fmt:message key="HR.ACR.ACR" bundle="${commonLables}" /></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" >
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="HR.ACR.ACR" bundle="${commonLables}">
		<table width="100%">
		<tr>
			<td width="25%"><hdiits:caption captionid="HR.ACR.Branch" bundle="${commonLables}"/></td>
			<td width="25%">
				<hdiits:text name="branch1" id="branch1" default="" readonly="true"></hdiits:text>
				<hdiits:hidden name="branch" id="branch" default=""/>
			</td>				
			<td width="25%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
			<td width="25%"><hdiits:text name="desig" id="desig" default="" readonly="true"></hdiits:text></td>				
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>
			<td width="25%"><hdiits:text name="username" id="username" default="" readonly="true"></hdiits:text></td>				
			<td width="25%"><hdiits:caption captionid="HR.ACR.GPF" bundle="${commonLables}"/></td>
			<td width="25%"><hdiits:text name="gpf" id="gpf" default="" readonly="true"></hdiits:text></td>				
		</tr>
		<tr>
			<td width="25%"><hdiits:caption	captionid="HR.ACR.CheckForSelfAppraisal" bundle="${commonLables}"/></td>
			<td width="25%">
				<hdiits:radio name="R1" id="Rno"
					value="N" captionid="HR.ACR.ButtonNo" bundle="${commonLables}" default="${lStrSelfFlag}" />&nbsp;&nbsp;
				<hdiits:radio name="R1" id="Ryes"
					value="Y" captionid="HR.ACR.ButtonYes" bundle="${commonLables}" default="${lStrSelfFlag}"/>
				<hdiits:hidden name="lStrSelfFlag" id="lStrSelfFlag" default="${lStrSelfFlag}"/>
			</td>
			<td width="25%"><hdiits:caption	captionid="HR.ACR.AddOfficer" bundle="${commonLables}"/></td>
			<td width="25%">
				<hdiits:select name="roleCmb" id="roleCmb" mandatory="true" captionid="HR.ACR.RoleSelect" bundle="${commonLables}">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<c:forEach items="${acrRole}" var="role">
						<c:if test="${role.lookupName ne 'acrRoleSelf'}">
							<option value="<c:out value="${role.lookupName}" />"><c:out
								value="${role.lookupDesc}" /></option>
						</c:if>	
					</c:forEach>
				</hdiits:select>&nbsp;
				<hdiits:image id="imgUser1" tooltip="Search Officers" onmouseover="showCursorAsHand(this)"
					source="./images/search_icon.gif" onclick="openUser(1);" ></hdiits:image>
			</td>
		</tr>
		</table>
	</hdiits:fieldGroup>
		<BR><BR>
		<table width="100%" id="dataTable" border="1" borderColor="BLACK" style="border-collapse: collapse;">
			<tr bgcolor="#C9DFFF"><td align="center"><hdiits:caption captionid="HR.ACR.Action" bundle="${commonLables}"/></td>
			<td align="center" ><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>
			<td align="center" ><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
			<td align="center" ><hdiits:caption captionid="HR.ACR.RoleType" bundle="${commonLables}"/></td>			
			<td align="center" ><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"></hdiits:caption></td>
			<td align="center" ><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>
		</tr>
		<tr id="tr1" style="display:none">
			<td align="center" colspan="10"><hdiits:caption captionid="HR.ACR.NORECORD" bundle="${commonLables}"/></td>
		</tr>
		</table>
		<BR><BR>
		<center>
			<hdiits:button type="button" name="btnEdit" id="btnEdit" value="Edit" onclick="openUser();"/>&nbsp;&nbsp;&nbsp;
			<hdiits:button type="button" name="btnDelte" id="btnDelte" value="Delete" onclick="deleteMe();"/>&nbsp;&nbsp;&nbsp;
			<hdiits:button type="button" name="btnSubmit" id="btnSubmit" value="Submit" onclick="submitMe();"/>
		</center>
		
		<hdiits:hidden name="xmlData" id="xmlData" default="${xmlData}"/>
		<hdiits:hidden name="userId" id="userId" default="${userId}"/>
		<hdiits:hidden name="year" id="year" default="${resValue.year}"/>
		<hdiits:hidden name="selectedUser" id="selectedUser" default=""/>
		<hdiits:hidden name="flag" id="flag" default="submit"/>
		<hdiits:hidden name="mstPK" id="mstPK" default="0"/>		
		<script type="text/javascript">	
			if(window.opener.xmlUserData1!='')
			{			
					mstPk = window.opener.mstPk;
					var xmlDOM = getDOMFromXML(window.opener.xmlUserData1,'HierachyGrpEntryVO');
					var branch=xmlDOM.getElementsByTagName('branch');
					branchValue=branch[0].childNodes[0].text;		
					document.getElementById("branch").value=branchValue;
					
					var branchName=xmlDOM.getElementsByTagName('branchName');
					branchNameValue=branchName[0].childNodes[0].text;
					document.getElementById("branch1").value=branchNameValue;
						
					var newDesgn=xmlDOM.getElementsByTagName('designation');
					newDesgnValue=newDesgn[0].childNodes[0].text;
					document.getElementById("desig").value=newDesgnValue;
					
					var userId=xmlDOM.getElementsByTagName('userId');
					userIdValue=userId[0].childNodes[0].text;					
					
					var empName=xmlDOM.getElementsByTagName('empName');
					empNameValue=empName[0].childNodes[0].text;
					document.getElementById("username").value=empNameValue;
										
					var GpfNo=xmlDOM.getElementsByTagName('gpfNo');
					GpfNoValue=GpfNo[0].childNodes[0].text;
					document.getElementById("gpf").value=GpfNoValue;
					
					var selfApp = xmlDOM.getElementsByTagName('selfApp');
					selfAppValue=selfApp[0].childNodes[0].text;
										
					document.getElementById("mstPK").value=mstPk;
					var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hierachyGepDtlVO');
				   	var roleTypeValue='';				   		
					for(var j=0;j<len;j++)
					{		
						var parentNode='hierachyGepDtlVO['+j+']';
						var disValue=getXPathValueFromDOM(xmlDOM, parentNode+'/designation');
						var nameValue=getXPathValueFromDOM(xmlDOM, parentNode+'/empName');
						var startDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/startDate');
						var endDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/endDate');
						var roleUserId=getXPathValueFromDOM(xmlDOM, parentNode+'/userId'); 
						var roleTypeValue =getXPathValueFromDOM(xmlDOM, parentNode+'/roleType');
						var roleDescValue =getXPathValueFromDOM(xmlDOM, parentNode+'/roleDesc');
						var pkVal=getXPathValueFromDOM(xmlDOM, parentNode+'/pk'); 

						totalUser[countArr]=roleUserId;
						countArr++;
						
						trow=document.getElementById("dataTable").insertRow();
						trow.id="row"+j;
						trow.insertCell(0).innerHTML = "<INPUT type=radio  name='rad' id='rad"+j+"_"+userIdValue+"' value='"+trow.id+"' />"
														+"<INPUT type=hidden id='roleType"+j+"' name='roleType"+j+"' value='"+roleTypeValue+"' />"
														+"<INPUT type=hidden id='rad"+j+"_"+userIdValue+"hidden' name='rad"+j+"_"+userIdValue+"hidden' value='"+roleUserId+"' />"
														+"<INPUT type=hidden id='pk"+j+"hidden' name='pk"+j+"hidden' value='"+pkVal+"' />";
						trow.cells[0].align="center";
						trow.insertCell(1).innerHTML = nameValue;
					    trow.insertCell(2).innerHTML = disValue;
					    trow.insertCell(3).innerHTML = roleDescValue;
					    trow.insertCell(4).innerHTML="<input type='text' name='startDate"+j+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur=return checkDate('startDate"+j+"','Please enter valid $CPTN','Start Date','0','01/01/2999','Please enter $CPTN less than 01/01/2999') value='"+startDValue+"'  maxlength='10' size='10'/>"
					    							+"<img style='cursor:hand' id='img_startDate' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('startDate"+j+"',375,570,'','startDate"+j+",Please~enter~valid~$CPTN,Start~Date,0,01/01/2999,Please~enter~$CPTN~less~than~01/01/2999') >"
					    							+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";	
					    
					    trow.insertCell(5).innerHTML="<input type='text' name='endDate"+j+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur=return checkDate('endDate"+j+"','Please enter valid $CPTN','Start Date','0','01/01/2999','Please enter $CPTN less than 01/01/2999') value='"+endDValue+"'  maxlength='10' size='10'/>"
					    							+"<img style='cursor:hand' id='img_endDate' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('endDate"+j+"',375,570,'','endDate"+j+",Please~enter~valid~$CPTN,Start~Date,0,01/01/2999,Please~enter~$CPTN~less~than~01/01/2999') >"
					    							+"&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";						    												   
					    							
						totalRow++;					    							
					}
					if(len==0)
					{
						document.getElementById("tr1").style.display='';
						document.getElementById("btnEdit").style.display='none';
						document.getElementById("btnDelte").style.display='none';
						document.getElementById("btnSubmit").style.display='none';
					}
			}
		</script>
	</div>	
</div>
</hdiits:form>
<script type="text/javascript">
		initializetabcontent("maintab")		
</script>
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>