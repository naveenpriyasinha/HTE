<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>



<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="fileId" value="${resValue.fileId}"/>
<c:set var="applist" value="${resValue.applist}"> </c:set>



<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?actionFlag=allocation" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle" style="height: 100%">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	

   
	<hdiits:fieldGroup titleCaptionId="Allocation.applist" bundle="${AllocLab}" expandable="true" mandatory="true" > 
	
	
	
	
	
	<table name="txnAdd" id="txnAdd"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  >
	<tr class="datatableheader">
	<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.district" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
	<td align="center" ></td>
	</tr> 
		<c:forEach var="applist" items="${applist}">
				<tr>
				
					<td align="center">${applist.salutationdesc} ${applist.empFirstName} ${applist.empMiddleName} ${applist.empLastName}</td>
					<td align="center">${applist.category}</td>
					<td align="center">${applist.designation}</td>
					
					<td align="center"><fmt:formatDate value="${applist.dob}" pattern="dd/MM/yyyy" /></td>
					<td align="center">${applist.subEducqualification}</td>
					<td align="center">${applist.yearrecruit}</td>
					
					<td align="center">${applist.district}</td>
					<td align="center">${applist.location}</td>
				</tr>
				
			</c:forEach>
	</table>
	</hdiits:fieldGroup>
	

	</div>	

		
	
	
 	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
