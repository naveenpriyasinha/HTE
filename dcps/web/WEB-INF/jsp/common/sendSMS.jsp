<%@ include file="../core/include.jsp" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
<script language="javaScript" >
function validate()
{
	return true;	
} 
</script>
</head>
<body>
<form name="SMSSender" action="hdiits.htm?actionFlag=sendSMS" method="post" onSubmit="return validate()">
<table>
<caption>
  	<font class="Labelerrormsg" >
		<c:set var="resultObj" value="${result}" > </c:set>		
		<c:set var="resultMap" value="${resultObj.resultValue}"> </c:set>		
  	    <c:out value='${resultMap.msg}'/>
	  </font>
</caption>
<tr>
	<td>Enter Phone Number :</td>
	<td><input type="text" name="phoneNo"></td>
	</tr>
<tr>
	<td>Message:</td>
	<td><Textarea rows="3" cols="20" name="message"></Textarea>
	</tr>
<tr>
<Td colspan="2" align="center">
<input type="submit" value="Send SMS">
<input type="Reset" value="Reset">
</Td>
</tr>
</table>
</form>

</body>
</html>