<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.hr.promotion.Promotion" var="promotionLabel" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript">

	function Close()
	{
		opener.document.promotionPersonData.openEmpChild.value = 'false';
		window.close();
	}
	function Submit()
	{
		if(confirm('Are You Sure Do you want to Submit?')==true)
		{
			opener.document.promotionPersonData.openEmpChild.value = 'false';
			document.frm1.action="hrms.htm?actionFlag=submitPromotionRemarks";
			document.frm1.submit();
			window.close();		
		}
	}

	function Show(buttonName,Val)
	{
		
		//alert(buttonName);
		
		var id = Val.value;
		//alert('id========>'+id);
		if(buttonName==0 && id == '')
		{
			return;
		}
		if(buttonName==6 && id == "V")
		{
			document.getElementById('punishDtlsTab').style.display="";
			document.frm1.punishDtls.value = "^";
			
		}
		if(buttonName==6 && id == "^")
		{
			document.getElementById('punishDtlsTab').style.display="none";
			document.frm1.punishDtls.value = "V";
		}
				
		if(buttonName==5 && id == "V")
		{
			document.getElementById('rewardsDtlsTab').style.display="";
			document.frm1.rewardDtls.value = "^";
			
			
		}
		if(buttonName==5 && id == "^")
		{
			document.getElementById('rewardsDtlsTab').style.display="none";
			document.frm1.rewardDtls.value = "V";
		}
		if(buttonName==7 && id == "V")
		{
			document.getElementById('ACRTab').style.display="";
			document.frm1.ACR.value = "^";
			
			
		}
		if(buttonName==7 && id == "^")
		{
			document.getElementById('ACRTab').style.display="none";
			document.frm1.ACR.value = "V";
		}
		if(buttonName==1 && id == "V")
		{
			document.getElementById('courtcasesTab').style.display="";
			document.frm1.courtcases.value = "^";
			
		}
		if(buttonName==1 && id == "^")
		{
			document.getElementById('courtcasesTab').style.display="none";
			document.frm1.courtcases.value = "V";
		}
		if(buttonName==2 && id == "V")
		{
			document.getElementById('decepProceedingsTab').style.display="";
			document.frm1.decepProceedings.value = "^";
			
		}
		if(buttonName==2 && id == "^")
		{
			document.getElementById('decepProceedingsTab').style.display="none";
			document.frm1.decepProceedings.value = "V";
		}
		if(buttonName==3 && id == "V")
		{
			document.getElementById('penInqrTab').style.display="";
			document.frm1.penInqr.value = "^";
			
		}
		if(buttonName==3 && id == "^")
		{
			document.getElementById('penInqrTab').style.display="none";
			document.frm1.penInqr.value = "V";
		}
		if(buttonName==4 && id == "V")
		{
			document.getElementById('medalYrTab').style.display="";
			document.frm1.medalYr.value = "^";
				
		}
		if(buttonName==4 && id == "^")
		{
			document.getElementById('medalYrTab').style.display="none";
			document.frm1.medalYr.value = "V";
		}
		
	}
		
</script>



</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="awardList" value="${resValue.awardList}" > </c:set>	
<c:set var="punishment" value="${resValue.punishment}" > </c:set>
<c:set var="awardMedalList" value="${resValue.awardMedalList}" > </c:set>
<c:set var="DeptInqrDtls" value="${resValue.DeptInqrDtls}" > </c:set>
<c:set var="fileId" value="${resValue.fileId}" > </c:set>
<c:set var="gId" value="${resValue.gId}" > </c:set>
<c:set var="remarks" value="${resValue.remarks}" > </c:set>
<body onunload="Close();">
<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected" ><a href="#" rel="tcontent1"><b><fmt:message key="Promotion.empData"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<BR><BR>
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7">
        	<td class="fieldLabel" colspan="4"><font color="#ffffff">
				
				<hdiits:button type="button"  
					name="rewardDtls" value="V" id="rewardDtls" captionid="rewardDtls"
					onclick="Show(5,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.rewardDtls"/></u></strong></font>				
			</td>
			</tr>
	</table>	
	<BR>	

	<div id="rewardsDtlsTab" style="display:none">
		<table class="TableBorderLTRBN"   border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">					
			
		<tr>
		<c:set var="i" value="0" /> <% int a=0; %>
	
		
		<display:table list="${resValue.awardList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" title="Award Category"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.awardCategory} 
			
		</display:column>
		<display:column class="tablecelltext" title=" Award Type"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardType} 
			
		</display:column>

		<display:column class="tablecelltext" title="Award Name"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardName} 
			
		</display:column>
		<display:column class="tablecelltext" title="Awarded For"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardedFor} 
			
		</display:column>
		<display:column class="tablecelltext" title="Award Date"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.awardDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>
		
		<display:column class="tablecelltext" title="Award Rupees"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.awardRupees} 
			
		</display:column>
		
		<c:set var="i" value="${i+1}" />
		</display:table>
		
	</tr>
	</table>
	</div>
	<BR>
	<BR>
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="punishDtls" value="V" id="punishDtls" captionid="punishDtls"
					onclick="Show(6,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.punishDtls"/></u></strong></font>
			</td>
        	
			</tr> 
			
	</table>	
	
	<BR>
	
	<div id="punishDtlsTab" style="display:none">
	<table class="TableBorderLTRBN"  border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue"  align="center" width="85%" >					
			
			<tr>
			<c:set var="c" value="0" /> <% int b=0; %>
		
		<display:table list="${resValue.punishment}" id="row" requestURI=""   export="false" style="width:100%" offset="1" >
		
		
		
		
		
			<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=b=b+1 %>"
			style="text-align: center" sortable="true">
		</display:column>	
		
		<display:column class="tablecelltext" title=" Punishment Details"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.punshDtls} 
			
		</display:column>
		
		
		<display:column class="tablecelltext" title="Punishment Date"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
		<c:choose >
		<c:when test="${not empty row.outcmMeetDateTime}">
		<fmt:formatDate value="${row.outcmMeetDateTime}" pattern="dd/MM/yyyy" type="date" />
		</c:when>
		<c:otherwise>
		<c:out value="Pending"></c:out>
		
		</c:otherwise>
		</c:choose>
		
		</display:column>
		
		<c:set var="c" value="${c+1}" />	
		
	</display:table>
	</tr>	
							
	</table>
	</div>
	
	<BR>
	
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="medalYr" value="V" id="medalYr" captionid="medalYr"
					onclick="Show(4,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.medalYr"/></u></strong></font>
			</td>
			</tr>
	</table>	
	
	<BR>
	
	<div id="medalYrTab" style="display:none">
		<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
			
		<tr>
		<c:set var="d" value="0" /> <% int c=0; %>
	
		
		<display:table list="${resValue.awardMedalList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=c=c+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" title="Award Category"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.awardCategory} 
			
		</display:column>
		<display:column class="tablecelltext" title=" Award Type"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardType} 
			
		</display:column>

		<display:column class="tablecelltext" title="Award Name"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardName} 
			
		</display:column>
		<display:column class="tablecelltext" title="Awarded For"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardedFor} 
			
		</display:column>
		<display:column class="tablecelltext" title="Award Date"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.awardDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>
		
				
		<c:set var="d" value="${d+1}" />
		</display:table>
		</tr>	
		</table>
		</div>
	<BR>
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="penInqr" value="V" id="penInqr" captionid="penInqr"
					onclick="Show(3,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.penInqr"/></u></strong></font>
				
			</td>
        	
        	
			</tr>
	</table>	
	
	<BR>

	<div id="penInqrTab" style="display:none">
		<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
			
		<tr>
		<c:set var="e" value="0" /> <% int d=0; %>
	
		
		<display:table list="${resValue.DeptInqrDtls}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext"  	title=" Sr.No"	headerClass="datatableheader" value="<%=d=d+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" title="Cause of Inquiry "
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.cause} 
			
		</display:column>
		
		<display:column class="tablecelltext" title="Status"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			<c:if test="${row.deptFlag eq 'P'}">
				<c:if test="${row.suspFlag eq 'P'}">
					<c:if test="${row.prosecFlag eq 'P'}">
						<c:out value="Prosecution"></c:out>
					</c:if>
					<c:out value="Suspension"></c:out>
					
				</c:if>
			</c:if>
			<c:out value="Departmental"></c:out> 
			
		</display:column>
		
		<display:column class="tablecelltext" title="Punishment"
			headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.punishDtlsType} 
		</display:column>
		
		<display:column class="tablecelltext" title="Start Date"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.prePrelimStartDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>
		
		<display:column class="tablecelltext" title="End Date"
			headerClass="datatableheader" style="text-align: center"
			sortable="true">
			<c:if test="${row.deptFlag eq 'A'}">
			<fmt:formatDate value="${row.deptEndDate}"  pattern="dd/MM/yyyy" type="date"/>
			</c:if>
			<c:if test="${row.suspFlag eq 'A'}">
			<fmt:formatDate value="${row.suspEndDate}"  pattern="dd/MM/yyyy" type="date"/>
			</c:if>
			<c:if test="${row.prosecFlag eq 'A'}">
			<fmt:formatDate value="${row.prosecEndDate}"  pattern="dd/MM/yyyy" type="date"/>
			</c:if>
			<c:out value="Pending"></c:out>
			
		</display:column>
		
				
		<c:set var="d" value="${d+1}" />
		</display:table>
		</tr>	
		</table>
		</div>
	
	
	<BR>
	
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="decepProceedings" value="V" id="decepProceedings" captionid="decepProceedings"
					onclick="Show(2,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.decepProceedings"/></u></strong></font>
				
			</td>
        				
			</tr>
	</table>		
	<BR>

	<table class="TableBorderLTRBN" id="decepProceedingsTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" name= "decepProceedingsTab" align="center" width="85%" style="display:none">					
			
			<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.srno" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.year" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.nature" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.present" bundle="${promotionLabel}"></hdiits:caption></b></td>
			</tr>	
							
	</table>
	
	
	<BR>
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="courtcases" value="V" id="courtcases" captionid="courtcases"
					onclick="Show(1,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.courtcases"/></u></strong></font>
				
			</td>
        	
			</tr>
	</table>	
	
	<BR>
	
	<table class="TableBorderLTRBN" id="courtcasesTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" name= "courtcasesTab" align="center" width="85%" style="display:none">					
			
			<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.srno" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.year" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.charge" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.present" bundle="${promotionLabel}"></hdiits:caption></b></td>
			<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.grading" bundle="${promotionLabel}"></hdiits:caption></b></td>
			</tr>	
							
	</table>
	
	
	<BR>
		
	
	<table id="ListTab1" name= "ListTab1" class="tabtable">
        	<tr bgcolor="#386CB7" colspan="2">
        	<td><font size=2 color="#ffffff">
				<hdiits:button type="button"  
					name="ACR" value="V" id="ACR" captionid="ACR"
					onclick="Show(7,this);"  style="width:20px"/>
				<strong><u><fmt:message key="ess.ACR"/></u></strong></font>
				
			</td>
        	</tr>
        	
	</table>	
<BR>
	<table class="TableBorderLTRBN" id="ACRTab" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" name= "ACRTab" align="center" width="85%" style="display:none">					
			<tr>
				<td style="display:none">&nbsp;</td>
				<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.srno" bundle="${promotionLabel}"></hdiits:caption></b></td>
				<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.year" bundle="${promotionLabel}"></hdiits:caption></b></td>
				<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.charge" bundle="${promotionLabel}"></hdiits:caption></b></td>
				<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.present" bundle="${promotionLabel}"></hdiits:caption></b></td>
				<td align="center" bgcolor="lightblue"><b><hdiits:caption captionid="ess.grading" bundle="${promotionLabel}"></hdiits:caption></b></td>
			</tr>	
	</table>
	
	<BR>
	<BR>
	
	<center>
		<hdiits:textarea name="str" id="str" cols="30" rows="3" default="${remarks}">
		</hdiits:textarea>
		
		<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
		<hdiits:hidden name="gId" id="gId" default="${gId}"/>				
		<BR>
		<hdiits:button type="button" name="btnsave" value="SUBMIT" id="btnSubmit" captionid="Submit" onclick="Submit()" />	
		<hdiits:button type="button" name="close" value="CLOSE" id="back" captionid="Close"	onclick="Close()" />
	</center>
	<script type="text/javascript">		
		initializetabcontent("maintab")
		try {
			if(opener.document.promotionPersonData.displayButton.value=='A')
			{
				document.getElementById('btnSubmit').style.display='none';
				document.getElementById('back').style.display='none';
			}
		}catch(e){}
	</script>
</div>
</div>	
</hdiits:form>
</body>
</html>
<%
}
  catch(Exception e)
  {
	  
	  e.printStackTrace();
  }
%>
	
