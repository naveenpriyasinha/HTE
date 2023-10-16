<%@page import="java.io.File"%>
<html>
<script>
	function imageNotFound()
	{
		document.getElementById("ddoImage").style.display = 'none';
		alert('Image not Found');
		document.getElementById("ShowImage").style.display = 'inline';
	}
</script>
<body>
<form name='frmDigiSign'>
<div id="ShowImage" align="center" style="display:none">Nothing to display</div>
<table width='100%' >
<tr width='100%' align='center'>
<td >
<img width="80%" id="ddoImage" onError="imageNotFound()" src="images/digiSigs/<%=request.getParameter("ddoCode")%>.gif"  title="DDO Number :<%=request.getParameter("ddoCode")%>">
</td>
</tr>
</table>
</form>
</body>
</html>
 
 