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
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script> 

<fmt:setBundle basename="resources.ess.resignation.ResignationLabels" var="commomLables" scope="request" />
<link rel="stylesheet" href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 

<c:set var="msg" value="${resValue.msg}"> </c:set>
<html>
<script type="text/javascript">

function showEmpName(){
document.frmVac.action="hdiits.htm?actionFlag=validateLogin&theme=hdiits";
document.frmVac.submit();

}
function searchagain(){
document.frmVac.action="hrms.htm?actionFlag=month&elementId=200031";
document.frmVac.submit();

}


function closeWindow()
			  {
			  	
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.frmVac.action=urlstyle;
		document.frmVac.submit();
			  				  	
			  }

</script>





<hdiits:form name="frmVac" validate="true" method="POST" action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b>
<hdiits:caption captionid="HRMS.Request" bundle="${commomLables}"></hdiits:caption></b></a></li>

</ul>
</div>

<div class="tabcontentstyle">


<div id="tcontent1" class="tabcontent" tabno="0"> 
<table bgcolor="#386CB7" align="center" width="100%">

<tr align="center">


</tr> 
</table>
<hdiits:table align="center">


</hdiits:table>
<hdiits:table align="center">
<tr>
<tr><tr align="centre"><tr>
<tr>
<td width="23%">

</tr>

</hdiits:table>
<br>
<br>
<br>

<table height="200">
<tr>
<td rowspan="100" colspan="40"></td>
</tr>
<tr rowspan="30" colspan="40">
<th rowspan="30" colspan="40"></th>
</tr>
<tr rowspan="30" colspan="40">
<th rowspan="30" colspan="40"></th>
</tr>
<tr>
<th rowspan="30" colspan="40"></th>
</tr>
</table>

<hr align="center" width=" 50%">
<table width="100%" align="center">


<tr ></tr>

<tr><th align="center"><c:out value="${msg}"></c:out></th></tr>
<tr></tr>


</table>
<hr align="center" width="50%">



<br><br>
			<table align="center" width="100%">
			<tr>
			<td align="center">
			
			<b><hdiits:button name="Close" type="button" onclick="closeWindow()" value="Close" 

/></b></td></tr></table>
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
