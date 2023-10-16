<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>

<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>



</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="awardList" value="${resValue.awardList}" > </c:set>	
<c:set var="punishment" value="${resValue.punishment}" > </c:set>
<c:set var="acrStartYr" value="${resValue.acrStartYr}" > </c:set>
<body>

<hdiits:form name="frm1" validate="true" method="POST" encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
</div>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<div id="rewardsDtlsTab">
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.rewardDtls" id="rewardDtlsHeader" collapseOnLoad="true">	
	
		<table class="TableBorderLTRBN"   border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">					
			
		<tr>
		<c:set var="i" value="0" /> <% int a=0; %>
	
		
		<display:table list="${resValue.awardList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext" titleKey="ess.srno"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.awdCat" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.awardCategory} 
			
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.awdType" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardType} 
			
		</display:column>

		<display:column class="tablecelltext" titleKey="ess.awdName" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardName} 
			
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.awdedFor" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.awardedFor} 
			
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.awdDt" headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.awardDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>
		
		<display:column class="tablecelltext" titleKey="ess.awdRs" headerClass="datatableheader" style="text-align: center"
			sortable="true">
			${row.awardRupees} 
			
		</display:column>
		
		<c:set var="i" value="${i+1}" />
		</display:table>
		</tr>	
		</table>
		</hdiits:fieldGroup>
		</div>

<div id="punishDtlsTab">
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.punishDtls" id="punishDtlsHeader" collapseOnLoad="true">		
	
	<table class="TableBorderLTRBN"  border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue"  align="center" width="85%" >					
			
			<tr>
			<c:set var="c" value="0" /> <% int b=0; %>
		
		<display:table list="${resValue.punishment}" id="row" requestURI=""   export="false" style="width:100%" offset="1" >
		
			<display:column class="tablecelltext" titleKey="ess.srno" headerClass="datatableheader" value="<%=b=b+1 %>"
			style="text-align: center" sortable="true">
		</display:column>	
		
		<display:column class="tablecelltext" titleKey="ess.PunishDtls" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.punshDtls} 
			
		</display:column>
		
		
		<display:column class="tablecelltext" titleKey="ess.PunishDate" headerClass="datatableheader" style="text-align: center"
			sortable="true">
		<c:choose >
		<c:when test="${not empty row.outcmMeetDateTime}">
		<fmt:formatDate value="${row.outcmMeetDateTime}" pattern="dd/MM/yyyy" type="date" />
		</c:when>
		<c:otherwise>
		<fmt:message key="ess.OutcomePending" bundle="${awdlbl}"/>
		
		</c:otherwise>
		</c:choose>
		
		</display:column>
		
		<c:set var="c" value="${c+1}" />	
		
	</display:table>
	</tr>	
							
	</table>
	
</hdiits:fieldGroup>
</div>	
<div id="AcrDtlsTab">
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.ACR" id="AcrDtlsHeader" collapseOnLoad="true">	
	
		<table class="TableBorderLTRBN"   border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">					
			
		<tr>
		<c:set var="i" value="0" /> <% int a=0; %>
		<c:set var="Year" value="${acrStartYr}" />
		
		<display:table list="${resValue.acrList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext" titleKey="ess.srno"	headerClass="datatableheader" value="<%=a=a+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.year" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${Year}
			
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.grading" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
		${row.cmnLookupMst.lookupDesc}	  
			
		</display:column>
		<c:set var="Year" value="${Year-1}" />
		<c:set var="i" value="${i+1}" />
		</display:table>
		</tr>	
		</table>
		</hdiits:fieldGroup>
		</div>

<table align="center">
<tr>
	<center>
	<td></td>
	
	<td>
	<hdiits:button type="button"  
		name="close"  id="back" captionid="ess.Close" bundle="${awdlbl}"
		onclick="window.close()"  style="width:80px"/>
	</td>
	
	<td></td>
	</tr>
</table>
</div>

</div>
<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

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
	
