<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Paymnent Data</title>
</head>


<table border="1" align="center">
<tr>
<td>
Date Of Retrieval:<input type="text" name="dateofret" value=""/>
</td>
</tr>
</table>

<table border="1" align="center">
<tr>

	<td>Date</td>
	<td>Pd Pla NO/td>
	<td>Head</td>
	<td>Minor</td>
	<td>Amount</td>
	<td>Chque No</td>
	<td>Party Name</td>
	<td>Type</td>
	<td>Internal Tc</td>
	<td>DetailHead</td>

</tr>
<c:forEach var='PayRec1' items="${result.resultValue.ResultMap}" varStatus="No"> 
		<tr>
		
			<td><input type="text" name="dt" id="dt" value="<c:out value="${PayRec1[0]}"/>" /></td>
			<td><input type="text" name="pdplano" id="pdplano" value="<c:out value="${PayRec1[1]}"/>" /></td>
			<td><input type="text" name="head" id="head" value="<c:out value="${PayRec1[2]}"/>" /></td>
			<td><input type="text" name="minor" id="minor" value="<c:out value="${PayRec1[3]}"/>" /></td>
			<td><input type="text" name="amount" id="amount" value="<c:out value="${PayRec1[4]}"/>" /></td>
			<td><input type="text" name="chqno" id="chqno" value="<c:out value="${PayRec1[5]}"/>" /></td>
			<td><input type="text" name="partynm" id="partynm" value="<c:out value="${PayRec1[6]}"/>" /></td>
			<td><input type="text" name="type" id="type" value="<c:out value="${PayRec1[7]}"/>" /></td>
			<td><input type="text" name="inttc" id="inttc" value="<c:out value="${PayRec1[8]}"/>" /></td>
			<td><input type="text" name="detailhd" id="detailhd" value="<c:out value="${PayRec1[9]}"/>" /></td>
					
		</tr>
	</c:forEach>
</table>
