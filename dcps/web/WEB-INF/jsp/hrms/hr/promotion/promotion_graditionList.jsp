<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
  
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabels" scope="request" />
<html>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
 
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="graditionList" value="${resValue.graditionList}"></c:set>
<c:set var="date" value="${resValue.date}"></c:set>
<c:set var="desi" value="${resValue.desi}"></c:set>
<c:set var="toDesig" value="${resValue.toDesig}"></c:set>
<c:set var="number" value="${resValue.number}"></c:set>
<c:set var="location" value="${resValue.location}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="remarks" value="${resValue.remarks}"></c:set>
<c:set var="myFlag" value="${resValue.myFlag}"></c:set>
<script type="text/javascript">
<!--
var win;
function submitPage()
{
		if(document.getElementById('moreData').value==''){
			alert('<fmt:message  bundle="${promotionLabels}" key="ess.TentativeNumRequire" />');
			return;
		}
		var urlstyle="hdiits.htm?actionFlag=getPromotionGLCombo&flag=Y";
		window.open(urlstyle,'chield', 'width=940,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}
function displayGradList(){
	
	/*var annual_rule_num="0123456789";
	var shubmitFlag=true;
	var annual_income=document.getElementById('moreData').value;
	if(annual_income!="")
	{
		for(var i=0;i<annual_income.length;i++)
		{
       		var cchar=annual_income.charAt(i);
	        if(annual_rule_num.indexOf(cchar)==-1)
       		{
        	  	alert('<fmt:message  bundle="${promotionLabels}" key="Promotion.altNumTenEmp" />');
        	  	shubmitFlag=false;
       		}
    	} 
    }
	if(document.getElementById('moreData').value!='' && isNaN(document.getElementById('moreData').value)==false && shubmitFlag==true)
	{*/
		 
		document.promotionInit.action="hrms.htm?actionFlag=searchForMoreDataList";
		document.promotionInit.submit();
	//}
	
}
function setChildFocus()
{
	try {
	if(document.getElementById('openChild').value=='true')
	{
		win.focus();
	}}catch(ex){}
}
function closeChild()
{
	try {
		win.close();
	}catch(ex){}
}
// To Write remarks
var GlobaltorwId='';
function writeRemarks(trowId)
{
	if(document.getElementById('openChild').value=='false')
	{	
		GlobaltorwId=trowId;
		document.getElementById('textData').value=document.getElementById('text_'+trowId).value;
		document.getElementById('openChild').value='true';	
		win=window.open("hrms.htm?viewName=promotion3","WriteRemarks","width=650,height=250,scrollbars=yes,toolbar=no,status=yes,menubar=no,resizable=no,top=40,left=100,dependent=yes");
		if(win.opener == null) {win.opener = self;}	
		if(window.complete_state){win.focus();}
	}
	else{
		try {
			win.focus();
		}catch(ex){}
	}
}
function SetData()
{
	document.getElementById('text_'+GlobaltorwId).value=document.getElementById('textData').value;
}

//-->
</script>

<body onunload="closeChild();" onclick="setChildFocus();" >

<hdiits:form name="promotionInit" validate="true" method="POST" action="hrms.htm?actionFlag=submitPromotionList" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="Promotion.initiatePromotion"/></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.reqDetais" bundle="${promotionLabels}">		
	<table width=100%>
		 <tr>
			<td width=25%><b><hdiits:caption captionid="Promotion.reqDesignation" bundle="${promotionLabels}" /></b></td>
			<td width=25%> 
				<c:out value="${toDesig}"></c:out>	
			</td>						
			<td width=25%><b><hdiits:caption captionid="Promotion.tentEmployeeToBePromoted" bundle="${promotionLabels}" /></b></td>			
			<td width=25%>	
				<c:out value="${number}"></c:out>
			</td>
		</tr>
		<tr></tr>
	</table>
	</hdiits:fieldGroup>
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.gradList" bundle="${promotionLabels}">		
		<table width=100%>
			<tr>
				<td width=25%><b><hdiits:caption captionid="Promotion.offJurisdiction" bundle="${promotionLabels}" /></b></td>
				
				<td width=25%>	<c:out value="${location}"></c:out>	</td>
				
				<td width=25%><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></td>
				
				<td width=25%>	<c:out value="${desi}"></c:out>	</td>
			</tr>
			<tr>
				<td width=25%><b> <hdiits:caption captionid="Promotion.tentEmployeeInList" bundle="${promotionLabels}" /></b></td>			
				<td width=25%><hdiits:number name="moreData" id="moreData" caption="Number" validation="txt.isnumber" default="${resValue.moreData}" /></td>
			</tr>
		</table>	
	<table width=100%>
		<tr align="center">
			<td>
				<hdiits:button type="button" name="genList" value="Generate List" id="genList" onclick="submitPage()" />				
			</td>
		</tr>
	</table>
	</hdiits:fieldGroup>	
	<hdiits:fieldGroup collapseOnLoad="false" titleCaptionId="Promotion.gradListFor" bundle="${promotionLabels}">		
		<table width="100%" border="2" id="dataTable" style="display:none">
			<tr>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.srNo" bundle="${promotionLabels}" />	</b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.name" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.Designation" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.doj" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.religion" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.category" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.dor" bundle="${promotionLabels}" /></b></th>
				<th align="center" bgcolor="lightblue"><b><hdiits:caption captionid="Promotion.remarks" bundle="${promotionLabels}" /></b></th>
			</tr>
		</table>
		<c:if test="${not empty graditionList}">			
			<c:set var="i" value="1"></c:set>
			<c:forEach var="x" items="${graditionList}" varStatus="j">			
				<c:set var="remarksData" value="${remarks[j.index]}"></c:set>
				<c:set var="name1" value="${x.name}"></c:set>		
				<c:set var="caste" value="${x.caste}"></c:set>					
				<c:set var="designation" value="${desi}"></c:set>					
				<c:set var="doj" value="${x.doj}"></c:set>
				<fmt:formatDate value="${doj}" pattern="dd/MM/yyyy" var="doj"/>												
				<c:set var="religion" value="${x.religion}"></c:set>											
				<c:set var="dor" value="${x.dor}"></c:set>
				<fmt:formatDate value="${dor}" pattern="dd/MM/yyyy" var="dor"/>	
				<script type="text/javascript">
					document.getElementById('dataTable').style.display='';
					var trow=document.getElementById('dataTable').insertRow();	
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
						"<a href=javascript:void('remarks') onclick=javascript:writeRemarks('" + trow.id + "')>Write Remarks</a>";			
					trow.insertCell(len + 2).innerHTML = "<INPUT type='textarea' name='remarks' id='text_" + trow.id +"' value='${remarksData}' />";
					trow.cells[len + 2].style.display = 'none';			
				</script>	
			<c:set var="i" value="${i+1}"></c:set>	
			</c:forEach>	
		</c:if>
	</hdiits:fieldGroup>	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.fileId}"/>
	<hdiits:hidden name="openChild" id="openChild" default="false"/>		
	<hdiits:hidden name="textData" id="textData" default=""/>			
	<hdiits:hidden name="myFlag" id="myFlag" default="${myFlag}"/>					
	<c:choose>
	<c:when test="${flag != 'F'}">
		<center>
			<jsp:include page="../../../core/tabnavigation.jsp"/>
		</center>
	</c:when>
	<c:otherwise>
		<BR>
		<BR>
		<center>
			<b><hdiits:caption captionid="Promotion.forwReq" bundle="${promotionLabels}" /></b>
		</center>
	</c:otherwise>
	</c:choose>
	</div>
</div>
<c:if test="${flag eq 'F'}">
	<script>
		document.getElementById('genList').disabled=true;
	</script>	
</c:if>
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

