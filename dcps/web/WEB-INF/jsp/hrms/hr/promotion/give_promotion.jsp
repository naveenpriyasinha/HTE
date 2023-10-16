<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<html>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="graditionList" value="${resValue.graditionList}"></c:set>
<c:set var="remarks" value="${resValue.remarks}"></c:set>
<c:set var="myFlag" value="${resValue.myFlag}"></c:set>
<head>
<script type="text/javascript">
var win,win1;
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
{
	try{
		win.close();
	}catch(ex){}
	try{
		win1.close();
	}catch(ex){}	
}
</script>
</head>
<body onclick="showChild();" onunload="closeChild();">
<hdiits:form name="promotionPersonData" validate="true" method="POST" 
	action="hrms.htm?actionFlag=promotionSelEmp" encType="multipart/form-data">
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="Promotion.promEmpList" bundle="${promotionLabels}" /></a></li>
	</ul>
	</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.reqDetais" bundle="${promotionLabels}">		
	<table width="100%">	
	<tr>
		<td class="fieldLabel"><b><hdiits:caption captionid="Promotion.reqDesignation" bundle="${promotionLabels}" /></b></td>
		<td class="fieldLabel"><c:out value="${resValue.desi}"></c:out></td>
		<td class="fieldLabel"><b><hdiits:caption captionid="Promotion.tentEmployeeToBePromoted" bundle="${promotionLabels}" /></b></td>
		<td class="fieldLabel"><c:out value="${resValue.tentavtive}"></c:out></td>				
	</tr>
	</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.gradListDetails" bundle="${promotionLabels}">		
	<table width="100%">	
	<tr>		
		<td class="fieldLabel"><b><hdiits:caption captionid="Promotion.offJurisdiction" bundle="${promotionLabels}" /></b></td>
		<td class="fieldLabel"><c:out value="${resValue.location}"></c:out></td>
		<td class="fieldLabel"><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></td>
		<td class="fieldLabel"><c:out value="${resValue.toDesig}"></c:out></td>				
	</tr>
	<tr>
		<td class="fieldLabel"><b><hdiits:caption captionid="Promotion.noOfEmpForList" bundle="${promotionLabels}" /></b></td>
		<td class="fieldLabel"><c:out value="${resValue.totalTentative}"></c:out></td>
		<td class="fieldLabel"></td>
		<td class="fieldLabel"></td>				
	</tr>
	</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.gradListFor" bundle="${promotionLabels}">	
		<table width="100%">
			<tr>
				<td class="fieldLabel" colspan="5"><font color="black">
				<strong><u><c:out value="${resValue.desi} as on ${resValue.date}"></c:out></u></strong></font></td>
			</tr>
		</table>
		<hdiits:hidden name="lenEmp" id="lenEmp" default="0"/>	
		<c:if test="${not empty graditionList}">	
		<table width="100%" id="graditionTable" border="1" borderColor="BLACK" style="border-collapse: collapse;">
		<tr>
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.srNo" bundle="${promotionLabels}" /></td>
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.name" bundle="${promotionLabels}" /></td>
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></td>				
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.doj" bundle="${promotionLabels}" /></td>		
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.religion" bundle="${promotionLabels}" /></td>				
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.category" bundle="${promotionLabels}" /></td>
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.dor" bundle="${promotionLabels}" /></td>				
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.remarks" bundle="${promotionLabels}" /></td>				
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.viewDetails" bundle="${promotionLabels}" /></td>	
			<td  align="center" bgcolor="lightblue"><hdiits:caption captionid="Promotion.fitUnfit" bundle="${promotionLabels}" /></td>
		</tr>
		</table>
		<c:set var="i" value="${i+1}"></c:set>
			
			<c:forEach var="x" items="${graditionList}" varStatus="j">	
				<c:set var="remarksData" value="${remarks[j.index]}"></c:set>
				<c:set var="name1" value="${x.name}"></c:set>		
				<c:set var="caste" value="${x.caste}"></c:set>					
				<c:set var="designation" value="${resValue.desi}"></c:set>					
				<c:set var="doj" value="${x.doj}"></c:set>	
				<fmt:formatDate value="${doj}" pattern="dd/MM/yyyy" var="doj"/>											
				<c:set var="religion" value="${x.religion}"></c:set>											
				<c:set var="dor" value="${x.dor}"></c:set>
				<fmt:formatDate value="${dor}" pattern="dd/MM/yyyy" var="dor"/>	
				<script type="text/javascript">
					document.getElementById('graditionTable').style.display='';
					var trow=document.getElementById('graditionTable').insertRow();	
					trow.id = 'row${i}';
					trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='encXML' id='encXML' value='"+${x.gid}+"'/>";				
					trow.cells[0].style.display = 'none';
					var dispFieldA = new Array('${i}','${name1}','${designation}','${doj}','${religion}','${caste}','${dor}');
					var len = dispFieldA.length;
					for(var i = 0; i < len; i++) 
					{
						trow.insertCell(i+1).innerHTML = dispFieldA[i];	
					}
					trow.insertCell(len + 1).innerHTML = 
						"<a href=javascript:void('remarks') onclick=javascript:writeRemarks('" + trow.id + "')>Read Remarks</a>";			
					trow.insertCell(len + 2).innerHTML = 
						"<a href=javascript:void('view') onclick=javascript:viewDetails('${x.userId}','${resValue.fileId}','${x.gid}')>View Details</a>";				
					trow.insertCell(len + 3).innerHTML = 
						"<INPUT type='radio' name='radio_${i}' id='radio_${i}' value='F' />Fit <INPUT type='radio' name='radio_${i}' id='radio_${i}' value='U' checked='checked'/>Unfit";				
					trow.insertCell(len + 4).innerHTML = "<INPUT type='textarea' name='remarks' id='text_" + trow.id +"' value='${remarksData}' />";
					trow.insertCell(len + 5).innerHTML = "<INPUT type='hidden' name='selUserId_${i}' id='selUserId_${i}' value='${x.userId}' />";
					trow.cells[len + 4].style.display = 'none';
					trow.cells[len + 5].style.display = 'none';
					document.getElementById("lenEmp").value='${i}';			
				</script>	
				<c:set var="i" value="${i+1}"></c:set>	
			</c:forEach>	
		</c:if>	
		<c:if test="${empty graditionList}">	
			<center>
				<b><hdiits:caption captionid="Promotion.no_rec" bundle="${promotionLabels}"></hdiits:caption></b>
			</center>
		</c:if>
	</hdiits:fieldGroup>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.fileId}"/>
	<hdiits:hidden name="openChild" id="openChild" default="false"/>		
	<hdiits:hidden name="openEmpChild" id="openEmpChild" default="false"/>
	<hdiits:hidden name="myFlag" id="myFlag" default="${myFlag}"/>		
	<hdiits:hidden name="textData" id="textData" default=""/>			
	<hdiits:hidden name="displayButton" id="displayButton" default="P"/>
	</div>
</div>

<br>
	<center>
			<jsp:include page="../../../core/tabnavigation.jsp"/>
	</center>
	
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.		
		initializetabcontent("maintab")
</script>
<hdiits:jsField jsFunction="" name="callLastJSFunction" />
<hdiits:validate locale="${locale}" controlNames="" />
</hdiits:form>
</body>
</html>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

