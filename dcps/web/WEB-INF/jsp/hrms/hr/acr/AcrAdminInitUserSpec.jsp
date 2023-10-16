<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
var empArray = new Array();
var empName='';
function showCursorAsHand(elem)
{
	elem.style.cursor='hand';
}
function searchForEmp()
{
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
function empSearch(from)
{
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 		
	}	
	if(from.length>0)
	{
		var single = empArray[0];
		document.getElementById('selectUserId').value=single[2];
		document.getElementById('userName').value=single[1];
		empName=single[1];
		document.getElementById('dsgntxt').value=single[7];	
		var fieldArr = new Array("yearCmb","dsgnCmb","branchCmb","selectUserId");	
		addOrUpdateRecord('showHierarchy','viewSetHierachyInAcr&flag=search',fieldArr,true);				
	}
}
function openHierachy()
{
	win=window.open("hrms.htm?actionFlag=viewSetHierachyInAcr","AcrHierachyDetails","width=800,height=600,toolbar=no,status=yes,menubar=no,resizable=yes,top=100,left=100,dependent=yes,scrollbars=yes");
	if (win.opener == null) {win.opener = self;	}	
	win.focus();
}
function createAcr()
{
	if(document.getElementById('selectUserId').value==0)
	{
		alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.selUserFirst"/>');
		var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	}
	else
	{
		xmlHttp=GetXmlHttpObject();
		showProgressbar("Please Wait... ");			
		if (xmlHttp==null) 
		{
		  hideProgressbar();
		  alert ("Your browser does not support AJAX!");
		  return;
		}		
		var url='hdiits.htm?actionFlag=ACRInitByAdminSubmitted&flag=ajax&year='+document.getElementById('year').value+'&userId=' + document.getElementById('selectUserId').value;
		xmlHttp.onreadystatechange=processResponse;
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);																	
	}
}
function processResponse()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{
			var dscXML=xmlHttp.responseText;			
			var xmlDOM = getDOMFromXML(dscXML);
			var self=xmlDOM.getElementsByTagName('SELF');		
			var selfValue=self[0].childNodes[0].text;
			
			if(selfValue=='Y')
			{
				alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserHasSelfApp"/>');
			}
			else if(selfValue=='A')
			{
				alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserHasSelfAppCreated"/>");
			}
			else if(selfValue=='N')
			{
				alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.NoHieSetMsg"/>");
			}
			else if(selfValue=='P')
			{
				try
				{
					var miss=xmlDOM.getElementsByTagName('MISSLST');
					var missValue=miss[0].childNodes[0].text;
					alert("<fmt:message key="HR.ACR.PointNotFound" bundle="${commonLables}" />"+" "+missValue);	
				}catch(e){}
			}
			else if(selfValue=='H')
			{
				try
				{
					var miss=xmlDOM.getElementsByTagName('HIE');
					var missValue=miss[0].childNodes[0].text;
					alert("<fmt:message key="HR.ACR.HieNotFound" bundle="${commonLables1}" />"+" : "+missValue);	
				}catch(e){}
			}
			else
			{
				alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelfAppDone"/> : "+empName+" - Id : "+selfValue);
				//if(document.getElementById('msgBox').value!=''){document.getElementById('msgBox').value=document.getElementById('msgBox').value+'\n';}
				//document.getElementById('msgBox').value=document.getElementById('msgBox').value+"<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelfAppDone"/> : "+empName +" - Id : "+selfValue;
				//document.getElementById('msgBoxTable').style.display='';
			}
			try 
			{			
				for(var i = document.getElementById("tableMst").rows.length; i > 2;i--)
				{
					document.getElementById("tableMst").deleteRow(i -1);
				}							
			}catch(ex){}
			hideProgressbar();	
			document.getElementById('selectUserId').value=0;
			document.getElementById('userName').value='';
			document.getElementById('dsgntxt').value='';
		}
	}
}
function showHierarchy()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{		
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
							y1.border="0";
							y1.cellpadding="0";
							y1.cellspacing="0";
							
							var trow=document.createElement("tr");							
							trow.id="row";
							var tdR=document.createElement("td");
							tdR.width="20%";
							var tdR1=document.createElement("td");tdR1.width="20%";
							var tdR2=document.createElement("td");tdR2.width="20%";
							var tdR3=document.createElement("td");tdR3.width="20%";
							var tdR4=document.createElement("td");tdR4.width="20%";
							
							tdR.innerHTML =i+1;
							tdR1.innerHTML =branchValue;
							tdR2.innerHTML =newDesgnValue;
							tdR3.innerHTML =empNameValue;
							tdR4.innerHTML =selfAppValue;
							
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
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="year" value="${resValue.year}"></c:set>
<body>
<hdiits:form name="AcrIninatUserSpec" method="POST" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"> <b> <fmt:message key="HR.ACR.ACR" bundle="${commonLables}" /><c:out value=" :${year}-${year+1}"></c:out> </b> </a></li>
		</ul>
	</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="HR.ACR.ACRInitiateByAdmin" bundle="${commonLables}">		
		<table width="100%" class="tabtable">
		<tr>		
			<td width="25%" align="left"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" id="userNameCap"/></td>
			<td width="25%" align="left"><hdiits:text style="background-color:lightblue" name="userName" id="userName" default="" readonly="true"></hdiits:text>
							<hdiits:hidden id="selectUserId" name="selectUserId" default="0"></hdiits:hidden>
							<hdiits:image id="imgUser" tooltip="Search Officers" onmouseover="showCursorAsHand(this)"
								source="./images/search_icon.gif" onclick="searchForEmp();" >
							</hdiits:image>
			</td>			
			<td width="25%" align="left"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" /></td>
			<td width="25%" align="left"><hdiits:text style="background-color:lightblue" name="dsgntxt" id="dsgntxt" default="" readonly="true"></hdiits:text></td>
		</tr>
		<tr height="1"><td></td></tr>
		<tr align="center">
			<td class="fieldLabel" colspan="10"  align="center">
				<hdiits:button type="button" name="viewSetHierachy" onclick="openHierachy()" captionid="HR.ACR.BtnViewSetHierachy" bundle="${commonLables}" id="viewSetHierachy"/>
				&nbsp;&nbsp;
				<hdiits:button captionid="HR.ACR.CreateAcr" bundle="${commonLables}" name="btnAcr" type="button" onclick="createAcr();"/>
			</td>
		</tr>
		</table>	
		</hdiits:fieldGroup>
	<BR><BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="HR.ACR.ViewSetHierachy" bundle="${commonLables}">
		<table border="1" width="100%" bordercolor="" style="border-collapse: collapse;" cellpadding="0" cellspacing="0" id="tableMst">
		<tr><td width="50%" valign="top">
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
		<td width="50%" valign="top">
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
		
	<hdiits:hidden name="year" id="year" default="${year}"/>
	<hdiits:hidden name="yearCmb" id="yearCmb" default="${year}"/>	
	<hdiits:hidden name="branchCmb" id="branchCmb" default=""/>	
	<hdiits:hidden name="dsgnCmb" id="dsgnCmb" default=""/>	
	</div>
</div>
</hdiits:form>	
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale="${locale}" controlNames="" />
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>	