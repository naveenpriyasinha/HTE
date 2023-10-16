<%@ include file="./alertMessage.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="LeaveTList" value="${resultValue.leaveTList}">
</c:set>
<c:set var="LeaveBal" value="${resultValue.leaveBal}">
</c:set>
<c:set var="holidayList" value="${resultValue.holidayList}">
</c:set>
<c:set var="pendingLst" value="${resultValue.pendingReqList}">
</c:set>
<c:set var="cancelLst" value="${resultValue.cancelReqList}">
</c:set>

<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.WFLables" var="commonLables"
	scope="request" />

<%
		try {
		int i = 1;
%>
<script ="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript"></script>






<display:table list="${pendingLst}" id="row" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom" />
	<display:column class="tablecelltext" titleKey="Sr_No"
		headerClass="datatableheader" defaultorder="ascending"
		sortName="external">
		<%=i%>
	</display:column>
	<display:column class="tablecelltext" titleKey="Document_Name"
		sortable="false" headerClass="datatableheader">
		<a
			href="./hrms.htm?viewName=DocumentPage&docId=${row.leaveid}&jobRefId=${row.leaveid}">
		${row.leaveid}</a>
	</display:column>
	<%
	i++;
	%>
</display:table>




<display:table list="${cancelLst}" id="row" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom" />
	<display:column class="tablecelltext" titleKey="Sr_No"
		headerClass="datatableheader" defaultorder="ascending"
		sortName="external">
		<%=i%>
	</display:column>
	<display:column class="tablecelltext" titleKey="Document_Name"
		sortable="false" headerClass="datatableheader">
		<a
			href="./hrms.htm?viewName=DocumentPage&docId=${row.leaveid}&jobRefId=${row.cancellationId}">
		${row.cancellationId}</a>
	</display:column>
	<%
	i++;
	%>
</display:table>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

<table border=1 width="100%">
	<tr>
		<td>Sr.No.</td>
		<td>Request No.</td>
		<td>Leave StartDate to EndDate</td>

	</tr>
	<c:set var="counter" value="1" />
	<c:forEach var="hrLeaveMain" items="${pendingLst}">
		<tr>
			<td>${counter}</td>
			<td><a
				href="hrms.htm?actionFlag=getParticularLeaveData&corrId=${hrLeaveMain.leaveid}&sanction=1" />${hrLeaveMain.leaveid}</a>
			</td>
			<td>${hrLeaveMain.appLeaveFrom} to ${hrLeaveMain.appLeaveTo}</td>
		</tr>
		<c:set var="counter" value="${counter+1}" />
	</c:forEach>


	<c:forEach var="hrLeaveMain" items="${cancelLst}">
		<tr>
			<td>${counter}</td>
			<td><c:set var="cancelRequest" value="0" /> <c:forEach
				var="CancelReq" items="${hrLeaveMain.hrLeaveCancellations}">
				<c:set var="cancelRequest" value="${CancelReq.cancellationId}" />
			</c:forEach> <a
				href="hrms.htm?actionFlag=getParticularLeaveData&appliedBetween=${hrLeaveMain.leaveid}&cancelReq=1" />${cancelRequest}</a>
			</td>
			<td>${hrLeaveMain.appLeaveFrom} to ${hrLeaveMain.appLeaveTo}</td>
		</tr>
		<c:set var="counter" value="${counter+1}" />

	</c:forEach>


</table>
