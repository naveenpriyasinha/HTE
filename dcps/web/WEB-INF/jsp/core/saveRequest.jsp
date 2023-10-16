<%@ page import="java.util.Enumeration"%><html>
<form name="saveRequestFORM" action="" method="post" style="display: none;">
<span id="saveRequestSpan"></span>
<script type="text/javascript">
var oldReqEleArray = new Array();
var oldReqValueArray = new Array();
</script>
<%
	// Setting Request Parameters from request.
	Enumeration eParmas = request.getParameterNames();
	while(eParmas.hasMoreElements())
	{
		String strnewLine = System.getProperty("line.separator");
		String pname = (String) eParmas.nextElement();
		String pvalue = request.getParameter(pname);
		pvalue = pvalue.replaceAll(strnewLine,"~");
			%>
			<script type="text/javascript">
			if(document.getElementById('<%=pname%>') == null || 
					document.getElementsByName('<%=pname%>') == null || 
					document.getElementsByName('<%=pname%>').length == 0)
			{
				var span = document.getElementById('saveRequestSpan');
				var var_name = '<%=pname%>';
				var var_val = '<%=pvalue%>';
				var_val = var_val.replace(/~/g,'\n');
				var str="<input type='hidden' name='"+var_name+"' value='"+var_val+"'/>";
				span.innerHTML+=str;
			}
			else
			{
				oldReqEleArray.push('<%=pname%>');
				oldReqValueArray.push('<%=pvalue%>');
			}
			</script>
			<%
	}
	// Setting Request Attributes from request.
	Enumeration eAttrs = request.getAttributeNames();
	while(eAttrs.hasMoreElements())
	{
		String attname = (String) eAttrs.nextElement();
		Object attvalue = request.getParameter(attname);
		request.setAttribute(attname,attvalue);
	}
%>
<input type="hidden" name="changeLang" value="<%=(String) session.getAttribute("locale")%>" />
<input type="hidden" name="theme" id="theme" value="<%=(String) session.getAttribute("themename")%>" />
</form>
