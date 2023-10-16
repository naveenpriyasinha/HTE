<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<hdiits:form name="frmInsertChqNo" validate="true" >
		<hdiits:table align="center" width="90%">
		<hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
		<hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
			<hdiits:tr>
				<hdiits:td>
					<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
				</hdiits:td>
				<hdiits:td>
					<hdiits:text name="txtChqNo" />
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr><hdiits:td>&nbsp;</hdiits:td></hdiits:tr>
			<hdiits:tr>
				<hdiits:td colspan="2">
					<hdiits:button name="btnOK" value="OK" type="button" onclick="window.close();" />
					<hdiits:button name="btnClose" value="Close" type="button" onclick="window.close();" />
				</hdiits:td>
			</hdiits:tr>
		</hdiits:table>
	</hdiits:form>
</body>
</html>