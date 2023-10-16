
<% try {  %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript">

var remMsg='${resValue.reminderSuccess}';


if(remMsg == "yes")
{
	var startDt='${resValue.startDate}';
	var endDt='${resValue.endDate}';
	var tm='${resValue.fireTime}';
	var msg="reminder is set on file from "+startDt+" to "+endDt+" on "+tm+" time";
	alert(msg);
	window.close();
}
else
{
	var startDt='${resValue.startDate}';
	var endDt='${resValue.endDate}';
	var msg="reminder is alredy exist for date "+startDt+" to"+endDt; 
	alert(msg);
	window.close();
}


</script>

<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>