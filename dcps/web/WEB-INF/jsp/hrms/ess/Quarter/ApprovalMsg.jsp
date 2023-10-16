
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables"	var="QtrLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="Flag" value="${resValue.StatusFlag}" />
<br>
<br>
<br>
<br>
<br>
<script type="text/javascript" language="JavaScript">
function ClosePage(frm){
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
}
</script>
<hdiits:form name="form1" validate="" action="" method="post">
<table height="100">
		<tr>
			<td rowspan="100" colspan="40"></td>
		</tr>
		<tr rowspan="30" colspan="40">
			<th rowspan="30" colspan="40"></th>
		</tr>
				<tr>
			<th rowspan="30" colspan="40"></th>
		</tr>
	</table>

      <hr align="center" width=" 50%">
<table width="100%" align="center">
   
  <c:if test="${Flag == 0 }" >
  <tr ></tr>

    <tr><th align="center"><hdiits:caption captionid="HRMS.approv" bundle="${QtrLables}" /> </th> </tr>
  <tr></tr>
  </c:if>
  
  <c:if test="${Flag == 1 }" >
  <tr  ></tr>

    <tr><th align="center"><hdiits:caption captionid="HRMS.reject" bundle="${QtrLables}" /> </th> </tr>
  <tr></tr>
  </c:if>
</table>

  <hr align="center" width="50%">
  
  <table align="center">
	<tr>
		<td>
			<hdiits:button name="Close" type="button" value="Close"  captionid="HRMS.Ok" bundle="${QtrLables}" onclick="ClosePage(document.form1);"/>
		</td>
	</tr>
</table>
</hdiits:form>