
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables"	var="QtrLables" scope="request" />

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
   
  
  <tr  ></tr>

    <tr><th align="center"><hdiits:caption captionid="HRMS.Update" bundle="${QtrLables}" /> </th> </tr>
  <tr></tr>
  

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