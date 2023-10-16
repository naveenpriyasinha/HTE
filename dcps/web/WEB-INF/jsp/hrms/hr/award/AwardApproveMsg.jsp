<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<br>
<br>
<br>
<br>
<br>

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

