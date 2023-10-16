<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="errorMsg" value="${resultValue.errorMsg}">
</c:set>

<fmt:setBundle basename="resources.hr.gratuityLeaveencashment.gratuityLeaveencashment" var="commonLables" scope="request"/>
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
	
	
	<c:forEach var="errorMsg" items="${errorMsg}">
		<tr>
		<td align="left">
		<b><font color="red" >*</font></b>
		<font size="1"> <b><fmt:message key="HRMS.${errorMsg}" bundle="${commonLables}"/></b></font>
		</td>
		
		</tr>
		
	
		
</c:forEach>
</table>
<p align="center">
		<hdiits:button	type="button" id="Close" name="Close" captionid="HRMS.close"
				bundle="${commonLables}" onclick="history.go(-1);" /></p>
			
		






