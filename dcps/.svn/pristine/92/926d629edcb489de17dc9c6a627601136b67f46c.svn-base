<% try { %>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables" var="approveEduLabels" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Human Resource Management System</title>
<script type="text/javascript">		
		var navDisplay = true,v=0;
		function View(reqId,empId)
		{			
			//win=window.open("hdiits.htm?actionFlag=ShowEducationDtls&flag=ShowPendingEducationDtls&reqId="+reqId+"&empId="+empId,"RequestData","width=700,height=350,status=yes,menubar=yes,resizable=yes,top=100,left=100");
		}		
		function increment()
		{
			v=v+1;
			return v;
		}
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="showreqId" value="${resValue.reqId}"></c:set>
<c:set  var="msg" value="${resValue.msg}"></c:set>
<c:set  var="flag" value="${resValue.flag}"></c:set>

<body>
<hdiits:form name="frmApproveEdu" validate="true" method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.quali_dtls" bundle="${approveEduLabels}"></hdiits:caption></b></a></li>
	</ul>
</div>
<div id="education" name="education">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<!-- <table class="tabtable">
	<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4"><font color="#ffffff">
		<strong><u>Pending Request</u></strong> </font></td>		
	</tr>		
	</table> 	
	<c:if test="${not empty lArrObj}">						
	<table class="tabtable" border="1">			
		<tr>
		<td class="fieldLabel" width="25%" bgcolor="lightblue">Sr No </td>
		<td class="fieldLabel" width="25%" bgcolor="lightblue">Request Id</td>
		<td class="fieldLabel" width="25%" bgcolor="lightblue">Created Date</td>
		<td class="fieldLabel" width="25%" bgcolor="lightblue">Link	</td>
		</tr>
		<c:forEach var="i1" items="${lArrObj}">	
		<tr>
			<td class="fieldLabel" width="25%">		
				<script type="text/javascript">v=increment();document.write(v); </script>
			</td>
			<td class="fieldLabel" width="25%">		
				<c:set var="reqId" value="${i1[0]}" />
				<c:out value="${reqId}" />
			</td>
			<td class="fieldLabel" width="25%">
				<c:out value="${i1[1]}" />	
			</td>
			<td>
					<a href=javascript:void('view') onclick=javascript:View('${reqId}','${i1[2]}')>View</a>
			</td>
		</tr>
		</c:forEach>		
	</table>
	</c:if>
	<c:if test="${empty lArrObj}">							
		No Pending Request Found
	</c:if>-->
	<BR>
	<BR>
	<BR>
	<BR>
	<hr align="center" width=" 70%">		
		<center><b><c:out value="${msg}"></c:out></b></center>
	<hr align="center" width=" 70%">	
		<!--	<c:set var="f" value="forward"></c:set>
	<c:choose>
		<c:when test="${flag == f }">
		<center><b><c:out value="${msg}"></c:out></b></center>
		</c:when>
		<c:otherwise>
			<center><b><c:out value="${msg}"></c:out></b></center>
		</c:otherwise>
	</c:choose>  -->
	</div>
	</div>
	</hdiits:form>
	<script type="text/javascript">
	initializetabcontent("maintab")
	</script>		
</body>
</html>
	<%
	} catch(Exception e)
	{
		e.printStackTrace();
	}
	%>