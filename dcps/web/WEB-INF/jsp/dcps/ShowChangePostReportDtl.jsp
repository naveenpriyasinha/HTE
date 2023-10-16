<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page import="java.util.List"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="permList" value="${resValue.PermanentConvert}"></c:set>
<c:set var="tempList" value="${resValue.TemporaryConvert}"></c:set>


<c:out value="${permList}">The number of employees converted to permanent post</c:out>
<c:out value="${tempList}">The number of employees converted to temporary post</c:out>

<%
}catch(Exception e) {e.printStackTrace();}
%>