<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request" />
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request" />
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
var cmbValue='';
function showSearchParameter(cmb)
{
	cmbValue=cmb.value;
	if(cmb.value=='BRANCH')
	{	
		document.getElementById("branch_row").style.display="";
		document.getElementById("dsgn_row").style.display="none";
		document.getElementById("emp_row").style.display="none";
	}
	else if(cmb.value=='DSGN')
	{
		document.getElementById("branch_row").style.display="none";
		document.getElementById("dsgn_row").style.display="";
		document.getElementById("emp_row").style.display="none";		
	}
	else if(cmb.value=='EMP')
	{		
		document.getElementById("branch_row").style.display="none";
		document.getElementById("dsgn_row").style.display="none";
		document.getElementById("emp_row").style.display="";
	}
	else
	{
		document.getElementById("branch_row").style.display="none";
		document.getElementById("dsgn_row").style.display="none";
		document.getElementById("emp_row").style.display="none";
	}
}
function searchForDtl()
{		
	var cmb = document.getElementById("groupCmb");
	if(cmb.value=='BRANCH')
	{	
		var  validateArrName = new Array("branchCmb");
		var ans = validateSpecificFormFields(validateArrName);
		if(ans!=true)
		{			
			return;
		}		
	}
	else if(cmb.value=='DSGN')
	{
		var  validateArrName = new Array("dsgnCmb");
		var ans = validateSpecificFormFields(validateArrName);	
		if(ans!=true)
		{			
			return;
		}
	}
	else if(cmb.value=='EMP')
	{
		if(document.getElementById('selectUserId').value==0)
		{	
			alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.SetUserHie"/>");			
			return;
		}
	}
	else
	{
		return;	
	}	
	var fieldArr = new Array("yearCmb","dsgnCmb","branchCmb","selectUserId");
	str =document.getElementById("yearCmb").value;
	if(str=='Select'){		
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectYear"/>');
		return;
	}
	document.getElementById("msgTag").style.display='none';
	addOrUpdateRecord('processResponse','viewSetHierachyInAcr&flag=search',fieldArr,true);
}
function processResponse()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{
			resetForm();
			try 
			{			
				for(var i = document.getElementById("tableMst").rows.length; i > 2;i--)
				{
					document.getElementById("tableMst").deleteRow(i -1);
				}							
			}catch(ex){}
			xmlStr = xmlHttp.responseText;
			
			var XMLDoc=getDOMFromXML(xmlStr);			
			var xml = XMLDoc.getElementsByTagName('XML');			
			if(cmbValue=='EMP')
			{
				try 
				{
					var typeVal =XMLDoc.getElementsByTagName('TYPE');					
					type=typeVal[0].childNodes[0].text;					
					if(type=='1')
					{
						document.getElementById("msgTag").style.display='';
						document.getElementById("msgTagBox").value="<fmt:message  bundle="${commonLables}" key="HR.ACR.UserSpecHie"/>";
					}
					else if(type=='2')
					{
						document.getElementById("msgTag").style.display='';
						document.getElementById("msgTagBox").value="<fmt:message  bundle="${commonLables}" key="HR.ACR.DsgnSpecHie"/>";
					}
					else if(type=='3')
					{	
						document.getElementById("msgTag").style.display='';
						document.getElementById("msgTagBox").value="<fmt:message  bundle="${commonLables}" key="HR.ACR.BranchSpecHie"/>";
					}
					else
					{
						document.getElementById("msgTag").style.display='none';
						document.getElementById("msgTagBox").value="";
					}
				}catch(ex){document.getElementById("msgTag").style.display='none';document.getElementById("msgTagBox").value="";}
			}
			if("X"==xml[0].childNodes[0].text)
			{				
				document.getElementById("noRecTr").style.display="";
			}
			else 
			{
				showProgressbar("Please Wait... ");	
				document.getElementById("noRecTr").style.display="none";
				for(var i = 0 ; i < xml.length ; i++)
				{
					xmlFileName=xml[i].childNodes[0].text;				
	  				xmlHttp=GetXmlHttpObject();
					if (xmlHttp==null) 
					{
					  hideProgressbar();
					  alert ("Your browser does not support AJAX!");
					  return;
					}		
					
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
					xmlHttp.open("POST",encodeURI(url),false);
					
					xmlHttp.onreadystatechange = function() 
					{
						if(xmlHttp.readyState == 4) 
						{
							dscXML=xmlHttp.responseText;													
							var xmlDOM = getDOMFromXML(dscXML,'HierachyGrpEntryVO');
									
							var branch=xmlDOM.getElementsByTagName('branch');
							branchValue=branch[0].childNodes[0].text;		
															
							var newDesgn=xmlDOM.getElementsByTagName('designation');
							newDesgnValue=newDesgn[0].childNodes[0].text;
							
							var empName=xmlDOM.getElementsByTagName('empName');
							empNameValue=empName[0].childNodes[0].text;
							
							var selfApp = xmlDOM.getElementsByTagName('selfApp');
							selfAppValue=selfApp[0].childNodes[0].text;
							
							/*var userId=xmlDOM.getElementsByTagName('userId');
							userIdValue=userId[0].childNodes[0].text;
							
							var GpfNo=xmlDOM.getElementsByTagName('gpfNo');
							GpfNoValue=GpfNo[0].childNodes[0].text;														
							
							var year = xmlDOM.getElementsByTagName('year');
							yearValue=year[0].childNodes[0].text;*/
							
							var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hierachyGepDtlVO');													
														
							var trowMst=document.getElementById("tableMst").insertRow();
							var mainTD1=document.createElement("td");
							var mainTD2=document.createElement("td");
							
							var trow=document.getElementById("dataTable").insertRow();
						
							var y1=document.createElement("table");
							y1.width="100%";
							y1.height="100%";
							y1.border="1";
							y1.cellpadding="2";
							y1.cellspacing="0";
							
							var trow=document.createElement("tr");							
							trow.id="row";
							trow.height="100%";
							var tdR=document.createElement("td");
							tdR.width="20%";
							var tdR1=document.createElement("td");tdR1.width="20%";
							var tdR2=document.createElement("td");tdR2.width="20%";
							var tdR3=document.createElement("td");tdR3.width="20%";
							var tdR4=document.createElement("td");tdR4.width="20%";
							
							tdR.innerHTML =i+1;tdR.align="center";
							tdR1.innerHTML =branchValue;tdR1.align="center";
							tdR2.innerHTML =newDesgnValue;tdR2.align="center";
							tdR3.innerHTML =empNameValue;tdR3.align="center";
							tdR4.innerHTML =selfAppValue;tdR4.align="center";
							
							trow.appendChild(tdR);
							trow.appendChild(tdR1);
							trow.appendChild(tdR2);
							trow.appendChild(tdR3);
							trow.appendChild(tdR4);
							y1.appendChild(trow);
							
							var y=document.createElement("table");
							y.width="100%";
							y.border="1";
							y.cellpadding="0";
							y.cellspacing="0";
							mainTD1.appendChild(y1);
							trowMst.insertCell(0).innerHTML =mainTD1.innerHTML;
							trowMst.insertCell(1).innerHTML =""; 
							for(var j=0;j<len;j++)
							{															
								var parentNode='hierachyGepDtlVO['+j+']';
								var disValue=getXPathValueFromDOM(xmlDOM, parentNode+'/designation');
								var nameValue=getXPathValueFromDOM(xmlDOM, parentNode+'/empName');
								var startDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/startDate');
								var endDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/endDate');
								roleTypeValue =getXPathValueFromDOM(xmlDOM, parentNode+'/roleType');																																					
								
								var trow1=document.createElement("tr");								
								var tdR=document.createElement("td");tdR.width="20%";tdR.align="center";
								var tdR1=document.createElement("td");tdR1.width="20%";
								var tdR2=document.createElement("td");tdR2.width="20%";
								var tdR3=document.createElement("td");tdR3.width="20%";
								var tdR4=document.createElement("td");tdR4.width="20%";
								
								tdR.innerHTML =roleTypeValue;
								tdR1.innerHTML =nameValue;
								tdR2.innerHTML =disValue;
								tdR3.innerHTML =startDValue;
								tdR4.innerHTML =endDValue;
								
								trow1.appendChild(tdR);
								trow1.appendChild(tdR1);
								trow1.appendChild(tdR2);
								trow1.appendChild(tdR3);
								trow1.appendChild(tdR4);
								
								y.appendChild(trow1);
								mainTD2.appendChild(y);
														
							}
							trowMst.cells[1].innerHTML=mainTD2.innerHTML;														
						}
					}
					xmlHttp.send(null);	
				}									
			}	           		   
			hideProgressbar();																		
		}
	}
}
function resetForm()
{
	document.getElementById("branch_row").style.display="none";
	document.getElementById("dsgn_row").style.display="none";
	document.getElementById("emp_row").style.display="none";
	document.viewACRHieScreen.reset();
}
function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}
function searchForEmp()
{
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
var empArray = new Array();
function empSearch(from)
{
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 		
	}	
	if(from.length>0)
	{
		var single = empArray[0];
		userId=single[2];
		document.getElementById('selectUserId').value=single[2];
		document.getElementById('userName').value=single[1];
		document.getElementById('dsgnName').value=single[7];
	}
}
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dsgn" value="${resValue.dsgn}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="branch" value="${resValue.branch}"></c:set>

<hdiits:form name="viewACRHieScreen" method="POST" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"> <b> <fmt:message key="HR.ACR.ACR" bundle="${commonLables}" /> </b> </a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" >
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="HR.ACR.ACR" bundle="${commonLables}">
		<table width="100%">
		<tr>
			<td width="25%"><hdiits:caption captionid="HR.ACR.Location" bundle="${commonLables}"/></td>
			<td width="25%">
				<hdiits:text readonly="true" name="Location" id="Location" default="${resValue.Location}"></hdiits:text>
			</td>				
			<td width="25%"><hdiits:caption
					captionid="HR.ACR.Year" bundle="${commonLables}" /></td>
			<td width="25%">
				<hdiits:select name="yearCmb" id="yearCmb" sort="false">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>					
					<option value="<c:out value="${year-1}" />" selected="selected"><c:out value="${year-1} - ${year}" /></option>					
					<option value="<c:out value="${year-2}" />" ><c:out value="${year-2} - ${year-1}" /></option>					
					<option value="<c:out value="${year-3}" />" ><c:out value="${year-3} - ${year-2}" /></option>										
				</hdiits:select>		
			</td>
		</tr>
		<tr>
			<td width="25%">			
				<hdiits:caption captionid="HR.ACR.HieType" bundle="${commonLables}"></hdiits:caption>
			</td>
			<td width="25%">			
				<hdiits:select name="groupCmb" id="groupCmb" mandatory="true" onchange="showSearchParameter(this);" validation="sel.isrequired" captionid="HR.ACR.HieType" bundle="${commonLables}">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<option value="BRANCH"><fmt:message key="HR.ACR.BranchType" bundle="${commonLables}" /></option>
					<option value="DSGN"><fmt:message key="HR.ACR.DsgnType" bundle="${commonLables}" /></option>
					<option value="EMP"><fmt:message key="HR.ACR.EmpType" bundle="${commonLables}" /></option>
				</hdiits:select>
			</td>
		</tr>		
		<tr id="branch_row" style="display:none">
			<td width="25%"><hdiits:caption	captionid="HR.ACR.Branch" bundle="${commonLables}" /></td>
			<td width="25%">
				<hdiits:select name="branchCmb" id="branchCmb" validation="sel.isrequired" captionid="HR.ACR.Branch" bundle="${commonLables}" mandatory="true">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<c:forEach var="branch" items="${branch}">
						<option value="<c:out value="${branch.branchCode}" />"><c:out
							value="${branch.branchName}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>	
		</tr>
		<tr id="dsgn_row" style="display:none">			
			<td width="25%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" /></td>
			<td width="25%">
				<hdiits:select name="dsgnCmb" id="dsgnCmb" validation="sel.isrequired" captionid="HR.ACR.Designation" bundle="${commonLables}" mandatory="true">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<c:forEach var="dsgn1" items="${dsgn}">
						<option value="<c:out value="${dsgn1.dsgnCode}" />"><c:out
							value="${dsgn1.dsgnName}" /></option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		<tr id="emp_row" style="display:none">									
			<td width="25%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" id="userNameCap"/></td>
			<td width="25%"><hdiits:text name="userName" id="userName" default="" readonly="true" style="background-color:lightblue"></hdiits:text>
					<hdiits:hidden id="selectUserId" name="selectUserId" default="0"></hdiits:hidden>
					<hdiits:image id="imgUser" tooltip="Search Officers" onmouseover="showCursorAsHand(this)"
						source="./images/search_icon.gif" onclick="searchForEmp();" >
					</hdiits:image>
			</td>
			<td width="25%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" /></td>			
			<td width="25%">
				<hdiits:text name="dsgnName" id="dsgnName" default="" readonly="true" style="background-color:lightblue"></hdiits:text>
			</td>	
		</tr>
		</table>
		<BR>		
		<center>
			<hdiits:button type="button" id="btnSearch" value="Search" name="btnSearch" onclick="searchForDtl();"/>
			<hdiits:button type="button" id="btnReset" captionid="HR.ACR.Reset" bundle="${commonLables}" name="btnReset" onclick="javascript:resetForm();"/>			
		</center>
		</hdiits:fieldGroup>
		<BR><BR>
		<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="HR.ACR.ViewSetHierachy" bundle="${commonLables}">
			<table border="1" width="100%" bordercolor="" style="border-collapse: collapse;" cellpadding="0" cellspacing="0" id="tableMst">
			<tr><td width="43%" valign="top">
				<table id="dataTable" width="100%" border="1" borderColor="BLACK" style="border-collapse: collapse;">
				<tr bgcolor="#C9DFFF" height="100%">		
					<td width="20%"><hdiits:caption captionid="HR.ACR.index" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.Branch" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>	
					<td width="20%"><hdiits:caption captionid="HR.ACR.SelfAppraisal" bundle="${commonLables}"/></td>	
				</tr>
				</table>
			</td>
			<td width="57%" valign="top">
				<table id="role" border="1" borderColor="BLACK" style="border-collapse: collapse;" width="100%">
				<tr bgcolor="#C9DFFF" height="100%">
					<td width="20%"><hdiits:caption captionid="HR.ACR.RoleType" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.OffName" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"/></td>
					<td width="20%"><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>			
				</tr></table>
			</td></tr>
			<tr style="display:none" id="noRecTr">
				<td colspan="10" align="center"><b><hdiits:caption captionid="HR.ACR.NORECORD" bundle="${commonLables}"/></b></td>
			</tr>
			</table>	
		</hdiits:fieldGroup>
		<BR><BR>	
		<center id="msgTag" style="display:none">
			<hdiits:textarea name="msgTagBox" cols="40" id="msgTagBox" readonly="true" mandatory="true"></hdiits:textarea>
		</center>
	</div>	
</div>
</hdiits:form>
<hdiits:validate locale="${locale}" controlNames="" />
<script>
	initializetabcontent("maintab")
</script>
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>