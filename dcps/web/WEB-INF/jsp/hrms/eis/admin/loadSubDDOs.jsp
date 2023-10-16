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
<c:set var="DDOlist" value="${resValue.DDOlist}"></c:set>
<html>
<head>
</head>
<body>
<c:set value="0" var="i"></c:set>
<hdiits:form name="subDDOlist" validate="true" method="post" encType="multipart/form-data" action="addSubDDOPostDtl">
<table align="center" width="85%">
<tr>
			<td>
			<hdiits:select name="ddoCode"	id="ddoCode" captionid="Reorting DDOS"
				bundle="${adminCreatePostLabel}" mandatory="false" validation="sel.isrequired" >
				<hdiits:option value="-1">
					<hdiits:caption captionid="admin.select"
						bundle="${adminCreatePostLabel}"></hdiits:caption>
				</hdiits:option>
				<c:forEach items="${DDOlist}" var="desi">
					<option value="<c:out value="${desi[0]}"/>"><c:out
						value="${desi[1]}" /></option>
				</c:forEach>
			</hdiits:select></td>
			</tr>
			<tr>
			<td><input type="submit" value="Generate Post For Selected DDO"></td>
			</tr>

</hdiits:form>
</body>
</html>
<%
}catch(Exception e) {e.printStackTrace();}
%>