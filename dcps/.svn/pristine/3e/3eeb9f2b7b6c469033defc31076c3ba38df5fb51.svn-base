<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="allFileCorrLst" value="${resValue.allFileCorrLst}"></c:set>
<c:set var="allCreatedCorrId" value="${resValue.allCreatedCorrId}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<table width="100%" border="1" borderColor="BLACK" style="border-collapse: collapse;" align="center">
	<tr bgcolor="#C9DFFF">
		<td><b><fmt:message key="HR.ACR.Report16" bundle="${commonLables}" /></b></td>
		<td><b><fmt:message key="HR.ACR.CorrStatus" bundle="${commonLables}" /></b></td>
	</tr>
	<c:forEach items="${allCreatedCorrId}" var="corrId">
	<c:set var="corrFound" value="1"></c:set>
		<tr>
			<td width="50%"><c:out value="${corrId}"></c:out></td>
			<c:forEach var="allFileCorr" items="${allFileCorrLst}">
				<c:if test="${allFileCorr.refFileCorrId eq corrId}">					
					<c:set var="corrFound" value="2"></c:set>
				</c:if>			
			</c:forEach>
			<c:choose>
			<c:when test="${corrFound eq 1}">
				<td width="50%"><font color="red"><fmt:message key="HR.ACR.CorrNoAdd" bundle="${commonLables}" /></font></td>
			</c:when>
			<c:otherwise>
				<td width="50%"><font color="green"><fmt:message key="HR.ACR.CorrAdd" bundle="${commonLables}" /></font></td>
			</c:otherwise>
			</c:choose>
		</tr>
	</c:forEach>
</table>
<%
}
catch (Exception ex)
{
	ex.printStackTrace();
}
%>