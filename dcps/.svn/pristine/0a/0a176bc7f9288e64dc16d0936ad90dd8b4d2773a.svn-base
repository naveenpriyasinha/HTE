<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,com.tcs.sgv.ess.valueobject.OrgUserMst"%>
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

 
<%
try {
	

%>


<%
ResultObject result=(ResultObject)request.getAttribute("result");
//out.println("******************************result  "+result);
Map resultMap=(Map)result.getResultValue();
//out.println("******************************resultMap  "+resultMap);
Map myMap=(Map)resultMap.get("testMap");
OrgUserMst obj = (OrgUserMst) resultMap.get("userVO");
out.println("uservo value is "+obj.getUserId());
out.println("uservo value is "+obj.getActivateFlag());
out.println("uservo value is "+obj.getUserName());
System.out.println(resultMap);
out.println("******************************"+myMap);
%>

<%
	out.println("msg-----"+request.getParameter("msg"));
	out.println("testing-----"+request.getParameter("testing"));
%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:out value="${resValue.msg}"></c:out>
<br><br><br>

<c:out value="${resValue.testMap}"></c:out>
<hdiits:form name="form1" method="POST" action="./hdiits.htm" validate="true">
<br><br><br>


</hdiits:form>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>