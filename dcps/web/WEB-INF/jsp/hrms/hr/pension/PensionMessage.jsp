<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension" var="pensionMsg" scope="request" />
<html>
<head>
<title>Human Resource Management System</title>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<body>
<hdiits:form name="frmMsg" action="hdiits.htm" validate="true" method="post" encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Pension.Msg" bundle="${pensionMsg}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
	<div id="PensionMessage" name="PensionMessage">
	<div id="tcontent1" class="tabcontent" tabno="0">	
	<BR>
	<BR>
	<BR>
	<center>
		<hr width="70%">
			<b><c:out value="${msg}"></c:out></b>
		<hr width="70%">
	</center>
	</div>
	</div>
	<hdiits:validate locale="${locale}" controlNames="" />
	<script type="text/javascript">	
		initializetabcontent("maintab")
	</script>
</hdiits:form>
</body>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>