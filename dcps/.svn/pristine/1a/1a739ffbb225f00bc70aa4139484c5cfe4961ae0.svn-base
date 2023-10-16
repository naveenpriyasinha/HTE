<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<script type="text/javascript" src="script/hrms/hr/promotion/promotion.js"></script>
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fitEmpList" value="${resValue.fitList}"></c:set>
<c:set var="unFitEmpList" value="${resValue.unFitList}"></c:set>
<c:set var="fitremarks" value="${resValue.fitremarks}"></c:set>
<c:set var="unfitremarks" value="${resValue.unfitremarks}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="myActionFlag" value="${resValue.myFlag}"></c:set>
<c:set var="desi" value="${resValue.toDesig}"></c:set>
<c:set var="FromDesi" value="${resValue.FromDesi}"></c:set>
<c:set var="reqType" value="${resValue.reqType}"></c:set>
<script>
var win,GlobaltorwId='';
function callTransferPage()
{	
	document.promotionPersonData.action="hrms.htm?actionFlag=promotionTransferDls";
	document.promotionPersonData.submit();	
}
function writeRemarks(trowId)
{
	if(document.getElementById('openChild').value=='false')
	{	
		GlobaltorwId=trowId;
		document.getElementById('textData').value=document.getElementById('text_'+trowId).value;
		document.getElementById('openChild').value='true';	
		win=window.open("hrms.htm?viewName=promotion6","ReadRemarks","width=650,height=250,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=no,top=40,left=100,dependent=yes");
		if(win.opener == null) {win.opener = self;}	
		if(window.complete_state){win.focus();}
	}
	else{
		try {
			win.focus();
		}catch(ex){}
	}
}	
function viewDetails(userId,fileId,gId)
{		
	if(document.getElementById('openEmpChild').value=='false')
	{
		document.getElementById('openEmpChild').value='true';			
		win1=window.open("hrms.htm?actionFlag=viewEmpDataPromotion&userIdEmp="+userId+"&fileId="+fileId+"&gId="+gId,"EmployeeDetails","width=650,height=650,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=no,top=40,left=100,dependent=yes");
	}	
}
function showChild()
{
	if(document.getElementById('openEmpChild').value=='true')
	{
		try {		
			win1.focus();
		}catch(e){}
	}
	else if(document.getElementById('openChild').value=='true')
	{
		try {
			win.focus();
		}catch(e){}
	}
}
function closeChild()
{function showChild()
{
	if(document.getElementById('openEmpChild').value=='true')
	{
		try {		
			win1.focus();
		}catch(e){}
	}
	else if(document.getElementById('openChild').value=='true')
	{
		try {
			win.focus();
		}catch(e){}
	}
}
function closeChild()
{
	try{
		win.close();
	}catch(ex){}
	try{
		win1.close();
	}catch(ex){}	
}
	try{
		win.close();
	}catch(ex){}
	try{
		win1.close();
	}catch(ex){}	
}
</script>
<body onclick="showChild();" onunload="closeChild();">
<hdiits:form name="promotionPersonData" validate="true" method="POST" action="" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1"><fmt:message key="Promotion.selectedEmp"/></a>
		</li>
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.listOfEmployees" bundle="${promotionLabels}">		</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.promotedPerList" bundle="${promotionLabels}">				
	<hdiits:hidden name="lenEmp" id="lenEmp" default="0"/>		
	<c:if test="${not empty fitEmpList}">		
	<table id="fitEmpTable" border="1" width="100%">
		<tr>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.srNo" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.name" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.doj" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.religion" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.category" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.remarks" bundle="${promotionLabels}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.viewDetails" bundle="${promotionLabels}" /></b></td>			
		</tr>		
	</table>	
	<c:set var="i" value="1"></c:set>
		<c:forEach var="x" items="${fitEmpList}" varStatus="j">	
			<c:set var="remarksData" value="${fitremarks[j.index]}"></c:set>
			<c:set var="name1" value="${x.name}"></c:set>		
			<c:set var="caste" value="${x.caste}"></c:set>					
			<c:set var="designation" value="${desi}"></c:set>					
			<c:set var="doj" value="${x.doj}"></c:set>											
			<fmt:formatDate value="${doj}" pattern="dd/MM/yyyy" var="doj"/>
			<c:set var="religion" value="${x.religion}"></c:set>											
			<c:set var="dor" value="${x.dor}"></c:set>
			<script type="text/javascript">
				document.getElementById('fitEmpTable').style.display='';
				var trow=document.getElementById('fitEmpTable').insertRow();	
				trow.id = 'row${i}';
				trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='encXML' id='encXML' value='"+${x.gid}+"'/>";				
				trow.cells[0].style.display = 'none';
				var dispFieldA = new Array('${i}','${name1}','${designation}','${doj}','${religion}','${caste}');
				var len = dispFieldA.length;
				for(var i = 0; i < len; i++) 
				{
					trow.insertCell(i+1).innerHTML = dispFieldA[i];	
				}
				trow.insertCell(len + 1).innerHTML = 
					"<a href=javascript:void('remarks') onclick=javascript:writeRemarks('" + trow.id + "')>Read Remarks</a>";			
				trow.insertCell(len + 2).innerHTML = 
					"<a href=javascript:void('view') onclick=javascript:viewDetails('${x.userId}','${resValue.fileId}','${x.gid}')>View Details</a>";				
				trow.insertCell(len + 3).innerHTML = "<INPUT type='textarea' name='remarks' id='text_" + trow.id +"' value='${remarksData}' />";
				trow.insertCell(len + 4).innerHTML = "<INPUT type='hidden' name='FitUserId'  value='${x.userId}' />";
				trow.cells[len + 3].style.display = 'none';
				trow.cells[len + 4].style.display = 'none';
				document.getElementById("lenEmp").value='${i}';			
			</script>	
			<c:set var="i" value="${i+1}"></c:set>	
		</c:forEach>	
	</c:if>
	<c:if test="${empty fitEmpList}">
		<table width="100%" align="center">
		<tr><td colspan="10" align="center">
			<hdiits:caption captionid="Promotion.no_rec" bundle="${promotionLabels}"/>
		</td></tr>
		</table>
	</c:if>	
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.unpromotedPersonList" bundle="${promotionLabels}">					
		<c:if test="${not empty unFitEmpList}">		
		<table id="unFitEmpTable" border="1" width="100%">
			<tr>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.srNo" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.name" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.doj" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.religion" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.category" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.remarks" bundle="${promotionLabels}" /></b></td>
				<td class="fieldLabel" align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.viewDetails" bundle="${promotionLabels}" /></b></td>
			</tr>		
		</table>						
		<c:set var="i" value="1"></c:set>
		<c:forEach var="x" items="${unFitEmpList}" varStatus="j">	
			<c:set var="remarksData" value="${unfitremarks[j.index]}"></c:set>
			<c:set var="name1" value="${x.name}"></c:set>		
			<c:set var="caste" value="${x.caste}"></c:set>					
			<c:set var="designation" value="${FromDesi}"></c:set>					
			<c:set var="doj" value="${x.doj}"></c:set>	
			<fmt:formatDate value="${doj}" pattern="dd/MM/yyyy" var="doj"/>										
			<c:set var="religion" value="${x.religion}"></c:set>											
			<c:set var="dor" value="${x.dor}"></c:set>
			<fmt:formatDate value="${dor}" pattern="dd/MM/yyyy" var="dor"/>
			<script type="text/javascript">
				document.getElementById('unFitEmpTable').style.display='';
				var trow=document.getElementById('unFitEmpTable').insertRow();	
				trow.id = 'row${i}';
				trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='encXML' id='encXML' value='"+${x.gid}+"'/>";				
				trow.cells[0].style.display = 'none';
				var dispFieldA = new Array('${i}','${name1}','${designation}','${doj}','${religion}','${caste}');
				var len = dispFieldA.length;
				for(var i = 0; i < len; i++) 
				{
					trow.insertCell(i+1).innerHTML = dispFieldA[i];	
				}
				trow.insertCell(len + 1).innerHTML = 
					"<a href=javascript:void('remarks') onclick=javascript:writeRemarks('" + trow.id + "')>Read Remarks</a>";			
				trow.insertCell(len + 2).innerHTML = 
					"<a href=javascript:void('view') onclick=javascript:viewDetails('${x.userId}','${resValue.fileId}','${x.gid}')>View Details</a>";				
				trow.insertCell(len + 3).innerHTML = "<INPUT type='textarea' name='remarks' id='text_" + trow.id +"' value='${remarksData}' />";
				trow.insertCell(len + 4).innerHTML = "<INPUT type='hidden' name='selUserId_${i}' id='selUserId_${i}' value='${x.userId}' />";
				trow.cells[len + 3].style.display = 'none';
				trow.cells[len + 4].style.display = 'none';
			</script>	
			<c:set var="i" value="${i+1}"></c:set>	
		</c:forEach>	
		</c:if>	
		<c:if test="${empty unFitEmpList}">		
			<CENTER>
				<CENTER>
					<hdiits:caption captionid="Promotion.no_rec" bundle="${promotionLabels}"/>
				</CENTER>
			</CENTER>
		</c:if>
	</hdiits:fieldGroup>
	<hdiits:hidden name="displayButton" id="displayButton" default="A"/>
	<hdiits:hidden name="openChild" id="openChild" default="false"/>	
	<hdiits:hidden name="textData" id="textData" default=""/>			
	<hdiits:hidden name="openEmpChild" id="openEmpChild" default="false"/>		
	<hdiits:hidden name="fileId" id="fileId" default="${fileId}"/>				
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.fileId}"/>
	<center>	
		<BR>	
		<c:if test="${not empty fitEmpList}">			
			<hdiits:button name="btnTransfer" type="button" captionid="Promotion.ButtonTransfer" bundle="${promotionLabels}" onclick="callTransferPage();"/>
		<script type="text/javascript">
			if('${myActionFlag}'=='A')
			{
				document.getElementsByName('btnTransfer').style.display='none';
				document.getElementById('displayButton').value='A';
			}	
			if('${reqType}'=='srvcReq')
			{
				document.getElementsByName('btnTransfer').style.display='none';
			}		
		</script>
		</c:if>		
	</center>
	</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>
<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />	
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>