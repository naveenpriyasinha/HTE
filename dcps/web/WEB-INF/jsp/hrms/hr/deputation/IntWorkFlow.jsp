
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<link rel="stylesheet"
	href="<c:url value="/common/css/tabcontent.css" />" />

<fmt:setBundle basename="resources.hr.deputation.Deputation" var="comLable"
	scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="hrDeputreqmtDtl" value="${resultValue.hrDeputreqmtDtl}">
</c:set>
<c:set var="fileOwnerPostId" value="${resultValue.fileStatus}">
</c:set>

<script>

</script>
<br>
<br>


<table width="100%">
	<tr>


		<td><c:if test="${hrDeputreqmtDtl.statusFlage eq null }">



			<jsp:include page="requirmentScreen.jsp" />
		</c:if></td>
	</tr>

	<tr>
		<td><c:if test="${hrDeputreqmtDtl.statusFlage eq \"INIT_SAVE\"}"
			var="testResult">

			<script>
                 var testResultis = '${testResult}';
               
                  </script>

			
			<jsp:include page="reqdetail.jsp" />

		</c:if></td>

	</tr>

	<tr>
		<td><c:if test="${hrDeputreqmtDtl.statusFlage eq \"INIT_APP\"}">
		

			<jsp:include page="requestFileNoEdit.jsp" />

		</c:if></td>

	</tr>
	<tr>
		<td><c:if test="${hrDeputreqmtDtl.statusFlage eq \"DG_PEN\"}">
			

			<jsp:include page="broadcastListNoEdit.jsp" />
		</c:if></td>

	</tr>

</table>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
