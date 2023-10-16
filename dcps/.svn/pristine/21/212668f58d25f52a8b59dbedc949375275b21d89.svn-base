<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
<c:set var="resultVal" scope="request" value="${result.resultValue.length}"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display ChqueList Details</title>
</head>


<table border="1" align="center">
<tr>
<td bgcolor="lightblue">
Distribute Voucher For Detail Posting
</td>
</tr>
<tr>
<td>
Total No Of Voucher To Be Distributes:${resultVal}
</td>
</tr>
</table>
<table border="1" align="center">
<tr bgcolor="lightblue">
	<td><input type="checkbox" name="chqn" /></td>
	<td>PayeeName</td>
	<td>Cheque No</td>
	<td>Cheque Issue Date</td>
	<!-- <td>Major Head</td> -->
	<td>Amount</td>
	<!--<td>Foward To</td> -->
	
</tr>

	<c:forEach var='empRec2' items="${result.resultValue.ResultMap}" varStatus="No"> 
		<tr>
			<td><input type="checkbox" name="chq" /></td>
		
			<td><c:out value="${empRec2[0]}"/></td>
			<td><c:out value="${empRec2[1]}"/></td>
			<td><c:out value="${empRec2[2]}"/></td>
			<td><c:out value="${empRec2[3]}"/></td>
		
			<!--  <td><select type = "text" name = "txtFwdTo"  > 
			<option value="-1"  selected>--select--</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option></select></td> -->
						
		</tr>
	</c:forEach>

</table>




