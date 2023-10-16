<%
try{
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../../core/include.jsp" %>

<fmt:setBundle basename="resources.hr.award.award" var="awdlbl" scope="request"/>
<html>
   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="awardList" value="${resValue.awardList}" > </c:set>	
<c:set var="punishment" value="${resValue.punishment}" > </c:set>
<c:set var="awardMedalList" value="${resValue.awardMedalList}" > </c:set>
<c:set var="DeptInqrDtls" value="${resValue.DeptInqrDtls}" > </c:set>
<body>

<hdiits:form name="frm1" validate="true" method="POST"	encType="multipart/form-data">

<div id="tabmenu" >
	<ul id="maintab" class="shadetabs">
		<li class="selected" ><a href="#" rel="tcontent1"><b><hdiits:caption captionid="ess.awards" bundle="${awdlbl}" captionLang="single"></hdiits:caption></b></a></li>
	</ul>
</div>
<div class="tabcontentstyle" style="height: 100%">
<div id="tcontent1" class="tabcontent" tabno="0">
<br>

<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	

	
<div id="medalYrTab" >
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.medalYr" id="medalDtls" collapseOnLoad="true">	
	
		<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
			
		<tr>
		<c:set var="d" value="0" /> <% int c=0; %>	
		
		<display:table list="${resValue.awardMedalList}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext" titleKey="ess.srno" headerClass="datatableheader" value="<%=c=c+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.awdCat"	headerClass="datatableheader" style="text-align: center"
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
		
				
		<c:set var="d" value="${d+1}" />
		</display:table>
		</tr>	
		</table>	
	
</hdiits:fieldGroup>
</div>		

<div id="penInqrTab">
<hdiits:fieldGroup bundle="${awdlbl}" titleCaptionId="ess.penInqr" id="pendInquiryDtls" collapseOnLoad="true">	
	
		<table class="TableBorderLTRBN" border="1" cellpadding="0" cellspacing="0" BGCOLOR="lightblue" align="center" width="85%">
			
		<tr>
		<c:set var="e" value="0" /> <% int d=0; %>
	
		
		<display:table list="${resValue.DeptInqrDtls}" id="row" requestURI=""  export="false" style="width:100%" offset="1" >
						
		<display:column class="tablecelltext" titleKey="ess.srno" headerClass="datatableheader" value="<%=d=d+1 %>"
			style="text-align: center" sortable="true">
		</display:column>
		<display:column class="tablecelltext" titleKey="ess.causeInquiry" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			${row.cause} 
			
		</display:column>
		
		<display:column class="tablecelltext" titleKey="ess.awdStatus" headerClass="datatableheader" style="text-align: center" sortable="true" >
			<c:if test="${row.prosecFlag ne 'I' && row.prosecFlag ne 'NI' && row.prosecFlag ne 'C'}">
						<fmt:message key="ess.prosecEnq" bundle="${awdlbl}"/>
			</c:if>			
			<c:if test="${row.suspFlag ne 'NI' && row.suspFlag ne 'I' && row.suspFlag ne 'C'}">
				<c:if test="${row.prosecFlag ne 'P'}">				
					<fmt:message key="ess.suspEnq" bundle="${awdlbl}"/>
				</c:if>
			</c:if>
			<c:if test="${row.deptFlag ne 'NI' && row.deptFlag ne 'I' && row.deptFlag ne 'C'}">
				<c:if test="${row.suspFlag ne 'P'}">
					<fmt:message key="ess.deptEnq" bundle="${awdlbl}"/>
				</c:if>
			</c:if>	
			<c:if test="${row.prelimFlag ne 'NI' && row.prelimFlag ne 'I' && row.prelimFlag ne 'C'}">
				<c:if test="${row.prePrelimFlag ne 'P'}">
					<fmt:message key="ess.prelim" bundle="${awdlbl}"/>
				</c:if>
			</c:if>	
			<c:if test="${row.prePrelimFlag ne 'NI' && row.prePrelimFlag ne 'I' && row.prePrelimFlag ne 'C' && row.prelimFlag ne 'P' && row.prelimFlag ne 'C'}">
					<fmt:message key="ess.preprelim" bundle="${awdlbl}"/>
			</c:if>			
			 
		</display:column>
		
		<display:column class="tablecelltext" titleKey="ess.awdPunish" headerClass="datatableheader" style="text-align: center"
			sortable="true" >
			 ${row.punishDtlsType} 
		</display:column>
		
		<display:column class="tablecelltext" titleKey="ess.deptStartDt" headerClass="datatableheader" style="text-align: center"
			sortable="true">
		
			<fmt:formatDate value="${row.prePrelimStartDate}"  pattern="dd/MM/yyyy" type="date"/>
			
			
		</display:column>		
		
		<c:set var="d" value="${d+1}" />
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
		name="close" id="back" captionid="ess.Close" bundle="${awdlbl}"
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
	
