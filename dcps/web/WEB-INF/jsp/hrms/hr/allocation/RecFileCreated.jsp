<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<html>
<script type="text/javascript">




</script>





<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
  
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">

	
</tr> 
	</table>
	<hdiits:table align="center">
		
	
	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	
	
	<tr align="centre">
<hdiits:td align="centre"><hr align="center"  ><font size="4" color="red" ><hdiits:caption captionid="Recruitment.success" bundle="${AllocLab}"  /></font> </hdiits:td>

</tr>

<hdiits:td>	


</hdiits:td>
	
	
	
		    	
		    	</hdiits:table>    
		    	  	
		    	
	</div>	
	

	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>"'/>

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
	 