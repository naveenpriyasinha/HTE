
<%@ page contentType="text/html;charset=UTF-8"%>
<%try{ %>
<html>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<body>
<form name="f1">

<table align="center">
	<tr>
		<td>	
			Name for search : 
		</td>
		<td>
			<input type="text" name="searchTxt" /> 
		</td> 
	</tr>

	<tr>
		<td>	 
			<input type="button" name="sbmt1" value="SearchSubmit" onclick="validate()"/> 
		</td> 
	</tr>
</table>

<script language="javascript">
	function validate(){
		alert(document.f1.searchTxt.value);
		if(document.f1.searchTxt.value == '')
		{
			alert("Please Enter search string...");
			return false;
		}
		document.f1.action="hdiits.htm?actionFlag=luceneSearchTest";
		document.f1.method='post';
		document.f1.submit();
	}
</script>
</form>
</body>
</html>
<% }
catch(Exception e)
{e.printStackTrace();}
%>