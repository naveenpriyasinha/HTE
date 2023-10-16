<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<br><br><br>
<fmt:message key="GENERATEVOUCHER.MESSAGE1" bundle="${billprocLabels}"></fmt:message>

<br>
<fmt:message key="GENERATEVOUCHER.MESSAGE2" bundle="${billprocLabels}"></fmt:message>
</body>
</html>