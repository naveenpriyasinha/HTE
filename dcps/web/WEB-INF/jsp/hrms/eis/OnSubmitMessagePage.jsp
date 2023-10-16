<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables" var="submitLabels" scope="request"/>
<html>
<head>

	<script type="text/javascript">	
	function closeWindow()
	{
		document.frmOnSubmitPage.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	   	document.frmOnSubmitPage.submit();
	}
	</script>

<title>Human Resource Management System</title>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="draft" value="${resValue.draft}"></c:set>
<c:set  var="msg" value="${resValue.msg}"></c:set>
<body>
<hdiits:form name="frmOnSubmitPage" validate="true" method="POST" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.MessagePage" bundle="${submitLabels}"></fmt:message></b></a></li>
		</ul>
	</div>
	<div id="OnSubmitPage" name="OnSubmitPage">
		<div id="tcontent1" class="tabcontent" tabno="0">
			<br>
			<br>
			<hr align="center" width=" 70%">		
			<c:if test="${draft == 'N'}">
				<center><b><c:out value="${msg}"></c:out></b></center>	
			</c:if>
			<c:if test="${draft == 'Y'}">
				<center><b><hdiits:caption captionid="eis.onSaveDraft" bundle="${submitLabels}"/></b></center>
			</c:if>
			<c:if test="${draft == 'A'}">
				<center><b><hdiits:caption captionid="eis.OnDataSubmit" bundle="${submitLabels}"/></b></center>
			</c:if>		
			<hr align="center" width=" 70%">	
			
			<br><br>
			
			<center>
				<hdiits:button name="cancel" type="button" captionid="EIS.CloseButton" bundle="${submitLabels}" onclick="closeWindow()"></hdiits:button>
			</center>
		</div>
	</div>		
</hdiits:form>
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
</body>
</html>	