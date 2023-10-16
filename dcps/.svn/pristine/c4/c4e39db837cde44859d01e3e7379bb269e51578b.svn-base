<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script>

function goHome()
{
	
}
</script>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="message" value="${resultValue.msg}">
</c:set>
<c:set var="id" value="${resultValue.appReqId}">
</c:set>
<c:set var="noteKey" value="${resultValue.noteKey}">
</c:set>

<c:set var="LeaveType" value="${resultValue.leaveType}">
</c:set>
<fmt:setBundle basename="resources.hr.gratuity.gratuity" var="commonLables" scope="request"/>	<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	
		<c:if test="${not empty id}">
		<tr >
		<td align="right">
		<b><font color="red">*</font></b>
		<b><fmt:message key="HRMS.TYPE" bundle="${commonLables}"/> :</b>
		</td>
		<td align="left"><fmt:message key="HRMS.${message}" bundle="${commonLables}"/>(${id})
		</td>
		</tr>
		<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.APPROVER" bundle="${commonLables}"/> :</b>	
			</td>
		<td  align="left" >
		 ${resultValue.fwdTo}
		</td>
		</tr>
		</c:if>
		
		<c:if test="${empty id}">
		<tr>
		<td align="center">
			<b><fmt:message key="HRMS.NOT_ENTITLED" bundle="${commonLables}"/></b>
		</td>
		</tr>
		</c:if>
	

</table>
