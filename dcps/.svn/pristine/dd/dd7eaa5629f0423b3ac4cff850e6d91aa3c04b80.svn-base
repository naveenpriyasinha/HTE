<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

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

<fmt:setBundle basename="resources.hr.groupInsurance.groupInsurance" var="constants" scope="request" /><br/>
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
	<tr >
		<td align="right">
		<b><font color="red">*</font></b>
		<b><fmt:message key="HRMS.TYPE" bundle="${constants}"/> :</b>
		</td>
		<td align="left"><fmt:message key="HRMS.${message}" bundle="${constants}"/>(${id})
		</td>
		</tr>
		<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.APPROVER" bundle="${constants}"/> :</b>	
			</td>
		<td  align="left" >
		 ${resultValue.fwdTo}
		</td>
	</tr>

</table>






