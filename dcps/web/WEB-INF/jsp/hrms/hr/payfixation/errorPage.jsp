<%@ page isErrorPage="true" %>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<script language="javaScript" type="text/javascript">
function viewDetail(divObj)
{
	 var obj = document.getElementsByTagName("DIV").item(divObj);
        obj.style.visibility="visible";
}
function hideDetail(divObj)
{
	 var obj = document.getElementsByTagName("DIV").item(divObj);
        obj.style.visibility="hidden";
}
</script>
<table cellspacing="0" cellpadding="0" border="0" height="100%" style="z-index: 1;">
   <tr height="98%">
    <td colspan="8">
     <table border="0" width="100%" cellspacing="0" cellpadding="0" height="100%"
            style="height:100.0%;">           
       <tr>
       <TD WIDTH="5%">
       <a href="javaScript:window.history.go(-1)">Back</a>
       </TD>
       <td valign="top">&nbsp;&nbsp;&nbsp;
       <BR><BR>
	<%
	StackTraceElement errors []=null;
       Throwable error = null;
       boolean hasError = false;
	try{
		Object obj = request.getAttribute("result");
	if (obj!=null){		
		ResultObject resultObj =(ResultObject) request.getAttribute("result");
		error = resultObj.getThrowable();
		if (error==null)
			hasError = true;
	}else
	{
		hasError = true;
	}
	if (hasError)
	{
		try{
			error= exception;
		}catch(Exception e){}
	}
		
	%>
	<%
		if (error!=null)
			out.println(error.getMessage() + "<br>");
	%>
	<a href="#" onClick="viewDetail('errorDetail')"> view Error detail</a>

	<div id="errorDetail" style="visibility:hidden">
		<a href="#" onClick="hideDetail('errorDetail')">hide Detail</a>
		<br>
		<pre style="spacing=0.5">
		<% 			
			errors = error.getStackTrace();
			for(int i=0;i<errors.length;i++)
				out.println(errors[i].toString());
	}catch(Exception e){
		out.println("<font color='RED'>System Error Occurs...</font>");
		e.printStackTrace();
	}			
		%>
		</pre>
	</div>
	</td>
	</tr>
	</table>
	</td>
	</tr>	
	</table>