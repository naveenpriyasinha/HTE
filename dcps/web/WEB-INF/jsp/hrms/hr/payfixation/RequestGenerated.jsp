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


<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="Name" value="${resValue.Name}"> </c:set>
<c:set var="empid" value="${resValue.empid}"> </c:set>
<html>
<script type="text/javascript">

function showEmpName(){
document.frmVac.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
document.frmVac.submit();

}


</script>





<hdiits:form name="frmVac" validate="true" method="POST"  action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle">
	
  
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<table bgcolor="#386CB7" align="center"  width="100%">

	<tr align="center">

	
</tr> 
	</table>
	<table>


<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>

	</table>
	
	 	
<table align="center" >

	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
	<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>

	<tr align="centre">
<td align="centre">
	<hr align="center" >
<hdiits:caption captionid="Pf.Requestgenerated" bundle="${PayLab}"  captionLang="single"/>
<c:out value="${Name}"></c:out>  (<hdiits:caption captionid="Pf.Gpfaccno" bundle="${PayLab}" captionLang="single" />
<c:out value="${empid}"></c:out>)</td>

<td>	

</td>
	
	
	<tr align="centre">
	

	 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<hdiits:button name="ok" type="button" captionid="Pay.Close"  onclick="showEmpName()" bundle="${PayLab}"/>
	    <hr align="center" >
	 </td>
	</tr>
		 	
		    	</table>    
		  	  	
		    	
	</div>	
	

	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
	 