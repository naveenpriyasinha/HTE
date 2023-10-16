<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.hr.transfer.transferLabels" var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<hdiits:form name="form1" validate="true" method="post">
<script type="text/javascript">
function Closebt()
{	
	method="POST";
	document.form1.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.form1.submit();
}
</script>
<table align="center">
  <br>
  <br>
  <br>
  <br>
  <br>
  
  <tr>
   <td align="center"> <font color="red" size="h4"> <b><c:out value="${resValue.msg}"></c:out></b></font>
  </td>
  </tr>
  <tr></tr><tr></tr><tr></tr>
  <tr>
  <td  align="center"><hdiits:button name="onReqClose"
				id="onReqClose" type="button" captionid="trn.Close"
				bundle="${transferLables}" onclick="Closebt()" /></td>	
  </tr>
  
  
</table>




</hdiits:form>


<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
	