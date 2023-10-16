<%try{ %>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>



<table width="100%" border="1">

<tr>
	<td>Year</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td>JAN</td>
	<td>FEB</td>
	<td>MAR</td>
	<td>APR</td>
	<td>MAY</td>
	<td>JUN</td>
	<td>JUL</td>
	<td>AUG</td>
	<td>SEP</td>
	<td>OCT</td>
	<td>NOV</td>
	<td>DEC</td>
</tr>

<c:forEach var="vo" items="${resValue.RowList}" >

<tr>

	<td>${vo[0]}</td>
	<td>${vo[1]}</td>
	<td>${vo[2]}</td>
	<td>${vo[3]}</td>
	<td>${vo[4]}</td>
	<td>${vo[5]}</td>
	<td>${vo[6]}</td>
	<td>${vo[7]}</td>
	<td>${vo[8]}</td>
	<td>${vo[9]}</td>
	<td>${vo[10]}</td>
	<td>${vo[11]}</td>
	<td>${vo[12]}</td>
	<td>${vo[13]}</td>
	<td>${vo[14]}</td>
	</tr>
</c:forEach>
</table>



<%}catch(Exception ex){ex.printStackTrace();}%>