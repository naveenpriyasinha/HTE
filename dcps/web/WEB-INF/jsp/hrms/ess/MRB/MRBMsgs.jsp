<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>

<script type="text/javascript">

function showEmpName(){
document.frmVac.action="hdiits.htm?actionFlag=getHomePage";
document.frmVac.submit();

}

</script>
<br>
<br>
<br>
<br>
<br>
<hdiits:form name="MRBFRM" validate="true" method="POST" action="" 	encType="multipart/form-data" >
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

    <tr><th align="center"><c:out value="${resultValue.msg}"></c:out></th></tr>
  <tr></tr>
  

</table>
  <hr align="center" width="50%">

<table align="center">
	<tr>
		<td>
			<input align="center" type="button" value="OK"  onclick="showEmpName();"/>
		</td>
	</tr>
</table>

<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
</hdiits:form>