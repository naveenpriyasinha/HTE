<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="rimages">
	<spring:theme code="reportsimages" />
</c:set>
<c:set var="rimg"><c:url value="${rimages}"/></c:set>
<c:set var="labelcss">captionTag</c:set>
<c:set var="textcss">texttag</c:set>
<c:set var="datecss">texttag</c:set>
<c:set var="timecss">texttag</c:set>
<c:set var="selectcss">selecttag</c:set>
<c:set var="buttoncss">buttontag</c:set>
<c:set var="rcss">
	<spring:theme code="reportscss" />
</c:set>
<link rel="stylesheet" href='<spring:theme code="reportscss"/>' type="text/css"/>
<link rel="stylesheet" href='<c:url value="${rcss}"/>' type="text/css" />
<%
String viewmode="";
if( request.getParameter("asPopup") != null && request.getParameter("asPopup").equalsIgnoreCase("TRUE") )
    viewmode="&asPopup=TRUE";
else
    viewmode="";
%>
<% 
    if(response!=null){
    response.setHeader( "Cache-Control", "no-cache" );//HTTP 1.1 
    response.setHeader( "Pragma", "no-cache");//HTTP 1.0 
    response.setHeader( "Expires", "0");//prevents caching at the proxy server
    }
%>
