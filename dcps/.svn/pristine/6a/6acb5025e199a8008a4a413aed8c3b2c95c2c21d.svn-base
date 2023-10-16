<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="primaryKey" value="${resValue.PrimaryKey}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.MessagePage" bundle="${empEditListCommonLables}"/></b></a></li>
	</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
		<BR>
		<BR>
		<BR>
<center>
<hr width="50%" align="center"> 
	<center><b><c:out value="${msg}"></c:out></b></center>
<hr width="50%" align="center">
</center>
</div>
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each  a comma.
		initializetabcontent("maintab")
</script>
<%}catch(Exception e){
	e.printStackTrace();
}

%>