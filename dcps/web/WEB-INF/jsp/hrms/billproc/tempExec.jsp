<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script language="javascript">
			function showName(ctrl)
			{
				alert("Control Name : " +document.frm1.ctrl.name);
			}
		</script>
	</head>
	
	<body>
	Hello World
		<hdiits:form name="frm1" validate="true">
			<% 
			int i;
				for(i =0;i<5;i++) {
			%>
				Name <%=i %> : 
				<hdiits:text name="t"+'<%=i %>'  >
				</hdiits:text>
			<% }  
			%>
		</hdiits:form>
	</body>
</html>