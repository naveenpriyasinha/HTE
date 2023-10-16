<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setBundle basename="resources.eis.eisLables_en_US"	var="adminCreatePostLabel" scope="request" />
<%@page import="java.util.List"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="resultlist" value="${resValue.resultlist}"></c:set>

<%--
List dataList = (List) pageContext.getAttribute("dataList");
int size = dataList.size();
pageContext.setAttribute("listSize",size);

--%>
<html>
<head>


<script type="text/javascript" src="common/script/tabcontent.js"></script>


<script type="text/javascript">
	function getConfirm(){
		var condrm = confirm('sure')
		if(condrm == true)
		return true;
		else return false;
	}
</script>
</head>
<body onload="getConfirm();">

<form name="frmAdminCrtPost" method="post"  action="./hrms.htm?actionFlag=showStastics&flag=run">
		
		
<input type="text" name="query">

<c:if test="${resultlist != null}">

<input type="text" readonly="readonly" value="${resultlist[0]}">

</c:if>
<input type="submit" value="Execute"> 


 

</form>
</body>
</html>
<%
}catch(Exception e) {e.printStackTrace();}
%>