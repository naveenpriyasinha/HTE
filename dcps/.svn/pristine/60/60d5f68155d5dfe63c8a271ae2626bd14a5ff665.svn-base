<%@ include file="../../../core/include.jsp"%>
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
<c:set var="status" value="${resultValue.noteKey}">
</c:set>

<c:set var="LeaveType" value="${resultValue.leaveType}">
</c:set>

<fmt:setBundle basename="resources.ess.leave.AlertMessages"
	var="alertLables" scope="request" />
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
<br/>
<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr >
		<td align="right">
		<b><font color="red">*</font></b>
		<b><fmt:message key="HRMS.TYPE" bundle="${alertLables}"/> :</b>
		</td>
		<td align="left"><fmt:message key="HRMS.${message}" bundle="${alertLables}"/>(${id})
		</td>
		</tr>
		<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.APPROVER" bundle="${alertLables}"/> :</b>	
			</td>
		<td  align="left" >
		 ${resultValue.fwdTo}
		</td>
		
				<tr align="center">
		<td align="right">
		<b><font color="red">*</font><fmt:message key="HRMS.STATUS" bundle="${alertLables}"/> :</b>	
			</td>
		<td  align="left" >
		 <fmt:message key="HRMS.${status}" bundle="${alertLables}"/>
		</td>
		
	</tr>

</table>






