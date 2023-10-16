<html>
<head>
<%@ page language="java" session="true"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.tcs.sgv.acl.login.valueobject.LoginDetails"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue"  value="${resultObj.resultValue}"></c:set>   
<c:set var="imagePath" value="${resValue.imagePath}"></c:set>
<c:set var="imageFlag" value="${resValue.imageFlag}"></c:set>
<body>
<form id="form" method="POST">
<div class=imgAttr >
<c:if test="${imageFlag eq 1}">
<center><img src="${imagePath}"/></center>
</c:if>
</div>
</body>

</head>
</html>