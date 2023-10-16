<%
try {
%>


<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script> 

<fmt:setBundle basename="resources.hr.addPay.PayInc" var="pyinc" scope="request" />
<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels" var="AddPay" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set> 
<c:set var="eisPayincTran" value="${resValue.eisPayincTran}"> </c:set>
<c:set var="msg" value="${resValue.msg}"> </c:set>

<c:set var="msgSubmit" value="${resValue.msgSubmit}"> </c:set>
<c:set var="Person" value="${resValue.Person}"> </c:set>
<c:set var="msgApproved" value="${resValue.msgApproved}"> </c:set>
<c:set var="msgForward" value="${resValue.msgForward}"> </c:set>
<c:set var="allReadySubmitted" value="${resValue.allReadySubmitted}"> </c:set>
<c:set var="langId" value="${resValue.langId}"> </c:set>


<html>

<hdiits:form name="frmVac" validate="true" method="POST" action="./hrms.htm?" encType="text/form-data" >

<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b>
<hdiits:caption captionid="AddPay.message" bundle="${AddPay}"></hdiits:caption></b></a></li>

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

<tr><th align="center">

<c:if test="${not empty msgSubmit }">
<hdiits:caption captionid="AddPay.RequestNo" bundle="${AddPay}"></hdiits:caption>
<c:out value="${msgSubmit}"></c:out>
<hdiits:caption captionid="AddPay.isSummitted" bundle="${AddPay}"></hdiits:caption>
<c:out value="${Person}"></c:out>
	<c:if test="${langId == 2}">
	<hdiits:caption captionid="AddPay.isForwarded" bundle="${AddPay}"></hdiits:caption>
	<hdiits:caption captionid="chhe" bundle="${AddPay}"></hdiits:caption>
	</c:if>
</c:if>

<c:if test="${not empty msgApproved}">
<hdiits:caption captionid="AddPay.RequestNo" bundle="${AddPay}"></hdiits:caption>
<c:out value="${msgApproved}"></c:out>
<hdiits:caption captionid="AddPay.isApproved" bundle="${AddPay}"></hdiits:caption>
</c:if>


<c:if test="${not empty msgForward }">
	<c:if test="${langId == 1}">
		<hdiits:caption captionid="AddPay.RequestNo" bundle="${AddPay}"></hdiits:caption>
		<c:out value="${msgForward}"></c:out>
		<hdiits:caption captionid="AddPay.isForwarded" bundle="${AddPay}"></hdiits:caption>
		<c:out value="${Person}"></c:out>
		<hdiits:caption captionid="chhe" bundle="${AddPay}"></hdiits:caption>
	</c:if>
	
	<c:if test="${langId == 2}">
		<hdiits:caption captionid="AddPay.RequestNo" bundle="${AddPay}"></hdiits:caption>
		<c:out value="${msgForward}"></c:out>
		<hdiits:caption captionid="AddPay.ne" bundle="${AddPay}"></hdiits:caption>
		<c:out value="${Person}"></c:out>
		<hdiits:caption captionid="AddPay.isForwarded" bundle="${AddPay}"></hdiits:caption>		
		<hdiits:caption captionid="chhe" bundle="${AddPay}"></hdiits:caption>
	</c:if>


</c:if>


<c:if test="${not empty allReadySubmitted}">
<hdiits:caption captionid="AddPay.RequestNo" bundle="${AddPay}"></hdiits:caption>
<c:out value="${allReadySubmitted}"></c:out>
<hdiits:caption captionid="allReadySubmitted" bundle="${AddPay}"></hdiits:caption>
</c:if>

</th></tr>
<tr></tr>


</table>
<hr align="center" width="50%">
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
 