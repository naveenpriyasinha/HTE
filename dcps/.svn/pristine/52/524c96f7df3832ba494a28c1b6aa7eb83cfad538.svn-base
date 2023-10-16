<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 




<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="approvelist" value="${resValue.approvelist}"> </c:set>
<c:set var="fileId" value="${resValue.fileId}"/>
<c:set var="rejlist" value="${resValue.rejlist}"> </c:set>



<html>

<script type="text/javascript">


function clearf(form)
{


			var idf=form.id; 
			var href='./hrms.htm?actionFlag=getrecruitmentdetails&allocationid='+idf+"&appflag=1";
		
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
			
				
}


</script>
</html>

<hdiits:form name="Allocation" validate="true" method="POST"  action="" encType="text/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	
				<hdiits:hidden name="fileId" id="fileId" default="${fileId}"/>
			<hdiits:fieldGroup titleCaptionId="Allocation.applist" bundle="${AllocLab}" expandable="true"  > 
	
	
	
	<c:if test="${not empty approvelist}">
	<table id='allocate' border="1" cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE" >
			<tr class="datatableheader">
			
				<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
				
				<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
				<td align="center"> <b><hdiits:caption captionid="Allocation.view" bundle="${AllocLab}"/></b></td>
				
				</tr>
				
				<c:forEach var="newlist" items="${approvelist}">
				<tr>
				
					<td align="center">${newlist.empName}</td>
					<td align="center">${newlist.post}</td>
					<td align="center"><fmt:formatDate value="${newlist.dob}" pattern="dd/MM/yyyy" /></td>
					<td align="center">${newlist.empFirstName}</td>
					<td align="center">${newlist.yearrecruit}</td>
					
					<td align="center">${newlist.jurisdiction}</td>
					<td align="center"><a href="#"  name="${newlist.allocid}" id="${newlist.allocid}" onclick="clearf(this)"><fmt:message key="Allocation.view" bundle="${AllocLab}"/> </a> </td>
				</tr>
				
			</c:forEach>
			</table>
			
	</c:if>
	</hdiits:fieldGroup>
  		
  	
  		<hdiits:fieldGroup titleCaptionId="Allocation.rejlist" bundle="${AllocLab}" expandable="true"  > 
	
	
	
	<c:if test="${not empty rejlist}">
	<table id='allocate' border="1" cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE" >
			<tr class="datatableheader">
			
				<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
				<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
				
				<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
				<td align="center"> <b><hdiits:caption captionid="Allocation.view" bundle="${AllocLab}"/></b></td>
				
				</tr>
				
				<c:forEach var="rejlist" items="${rejlist}">
				<tr>
				
					<td align="center">${rejlist.empName}</td>
					<td align="center">${rejlist.post}</td>
					<td align="center"><fmt:formatDate value="${rejlist.dob}" pattern="dd/MM/yyyy" /></td>
					<td align="center">${rejlist.empFirstName}</td>
					<td align="center">${rejlist.yearrecruit}</td>
					
					<td align="center">${rejlist.jurisdiction}</td>
					<td align="center"><a href="#"  name="${rejlist.allocid}" id="${rejlist.allocid}" onclick="clearf(this)"><fmt:message key="Allocation.view" bundle="${AllocLab}"/> </a> </td>
				</tr>
				
			</c:forEach>
			</table>
			
	</c:if>
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
