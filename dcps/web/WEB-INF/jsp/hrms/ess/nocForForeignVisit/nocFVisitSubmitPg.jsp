<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="tdBGColor" value="#76A9E2"></c:set>	
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="reqId" value="${resultValue.reqId}"></c:set>
<c:set var="lngRequestId" value="${resultValue.lngRequestId}"></c:set>
<c:set var="statusApproveReject" value="${resultValue.statusApproveReject}"></c:set>
<c:set var="fwdTo" value="${resultValue.fwdTo}"></c:set>

<fmt:setBundle basename="resources.ess.noc.NOC" var="NOCLables" scope="request" />

<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>

</head>
<body>

<hdiits:form name="frmNocPassportSubmit" validate="true" method="POST" action="./hrms.htm?actionFlag=submitData"> 
<br>
<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<fmt:message key="NOC.NocHeader" bundle="${NOCLables}"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
<br>	
<br>
<br>

<table width="100%" align="center">
	<tr width="100%" align="center">
		<th width="100%" align="center">
			<center>				
			<c:if test="${empty lngRequestId=='false'}" >
				<c:if test="${statusApproveReject=='Approved'}" >
					<fmt:message bundle="${NOCLables}" key="NOC.FVisitApprovalMsg1"/> <c:out value="${lngRequestId}"/> <fmt:message bundle="${NOCLables}" key="NOC.ApprovalMsg2"/> <fmt:message bundle="${NOCLables}" key="NOC.Approve"/>
				</c:if>
				<c:if test="${statusApproveReject=='Rejected'}" >
					<fmt:message bundle="${NOCLables}" key="NOC.FVisitApprovalMsg1"/> <c:out value="${lngRequestId}"/> <fmt:message bundle="${NOCLables}" key="NOC.ApprovalMsg2"/> <fmt:message bundle="${NOCLables}" key="NOC.Reject"/>
				</c:if>	
				<c:if test="${statusApproveReject=='Forwarded'}" >
					<fmt:message bundle="${NOCLables}" key="NOC.FVisitApprovalMsg1"/> <c:out value="${lngRequestId}"/> <fmt:message bundle="${NOCLables}" key="NOC.ApprovalMsg2"/> <fmt:message bundle="${NOCLables}" key="NOC.Forward"/>
				</c:if>				
			</c:if>
			<c:if test="${empty lngRequestId=='true'}" >
				<fmt:message bundle="${NOCLables}" key="NOC.SubmitNocFvisitMsg"/><c:out value="${reqId}"/> to <c:out value="${fwdTo}"/>
			</c:if>
			</center>
		</th>
	</tr>
</table>
	
</div>

	</div>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
</hdiits:form>
 
<script language="javascript">
</script>
<script>
initializetabcontent("maintab")
</script>
<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
		