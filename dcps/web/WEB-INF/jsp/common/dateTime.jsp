<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.common.CommonLables" var="dateTimeLabels" scope="request"/>
<c:set var="dateName" value="${param.dateName}%>" />
<c:set var="timeName" value="${param.timeName}" />
<c:set var="resultObj"	value="${result}"/>
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<c:set var="currentDBDateTime" value="${resultMap.currentDBDate}"/>
<fmt:formatDate value="${currentDBDateTime}" pattern="dd/MM/yyyy" dateStyle="medium" var="currentDate"/>
<fmt:formatDate value="${currentDBDateTime}" pattern="HH:mm" dateStyle="medium" var="currentTime"/>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
		    <td class="fieldLabel" width="25%" ><hdiits:caption captionid="COMMON.DATE" bundle="${dateTimeLabels}"/></td>
		    <td class="fieldLabel1" width="25%"><hdiits:text   name="${dateName}" captionid="COMMON.DATE" readonly="true" bundle="${dateTimeLabels}" default="${currentDate}"/></td>
		    <td class="fieldLabel" width="25%"><hdiits:caption captionid="COMMON.TIME" bundle="${dateTimeLabels}"/></td>
	        <td class="fieldLabel" width="25%"><hdiits:text   name="${timeName}" captionid="COMMON.TIME" readonly="true" bundle="${dateTimeLabels}" default="${currentTime}"/></td>
	 	</tr>
</table>