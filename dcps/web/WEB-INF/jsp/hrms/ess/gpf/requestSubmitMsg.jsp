<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>


<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 
<c:set var="eisPayincTran" value="${resValue.eisPayincTran}"> </c:set>
<c:set var="msg" value="${resValue.msg}"> </c:set>
<html>
<hdiits:form name="frmVac" validate="true" method="POST" action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b>
<fmt:message key="GPF"/></b></a></li>

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
