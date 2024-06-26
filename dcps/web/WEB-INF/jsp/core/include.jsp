
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setHeader("Expires", "0"); //prevents caching at the proxy server
%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page errorPage="webErrorPage.jsp" isErrorPage="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:setLocale value='<%=session.getAttribute("locale")%>' />
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>
	';
	var navDisplay = navDisplay;
	if (navDisplay == undefined)
		navDisplay = true;
</script>
