<%@page import="java.util.Hashtable"%>
<%@page import="java.util.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
session.setAttribute("resultMap",resultMap);

%>
<script>
function closewindow()
{
	window.close();
}
</script>		

<iframe src="graphicalpendency_main.jsp" width="100%" height="670" scrolling="auto"></iframe>
