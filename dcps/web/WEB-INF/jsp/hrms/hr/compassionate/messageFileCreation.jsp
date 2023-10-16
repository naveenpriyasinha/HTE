<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>

<hdiits:form name="form1" validate="true" method="post">

<table align="center">
  <br>
  <br>
  <br>
  <br>
  <br>
  
  <tr>
   <td align="center"> <font color="red" size="h3"> <b>The Compansion Request File for the Nominee is Created and Forwarded for Approval</b></font>
  </td>
  </tr>
  
  
</table>




</hdiits:form>


<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
	