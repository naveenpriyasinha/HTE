<%try { %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="a2" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
 

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Human Resource Management System</title>
<script type="text/javascript">		
		var navDisplay = true,v=0;
		function closewindow()
	{
		
		var urlstyle="hdiits.htm?actionFlag=getHomePage"
		document.approveAsset.action=urlstyle;
		document.approveAsset.submit();
	}
	function goBack()
	{
		
		var urlstyle="./hrms.htm?actionFlag=getAnnualAssetDataForIncomeDeclaration"
		document.approveAsset.action=urlstyle;
		document.approveAsset.submit();
	}
	
		
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="Year" value="${resValue.Year}"></c:set>
<c:set  var="flag" value="${resValue.flag}"></c:set>


<body>
<hdiits:form name="approveAsset" validate="true" method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:if test="${flag == 'AssetIncomeDeclaration'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="INCOME_DECL" bundle="${a2}"/>${Year}</b></a></li>
	</c:if>	
	<c:if test="${flag == 'AssetDeclarationOfYear'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_YEAR" bundle="${a2}"/></b></a></li>
	</c:if>	
	</ul>
</div>
<div id="asset" name="asset">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	<BR>
	<BR>	
	<BR>
	<BR>
	<BR>
	<BR>
	<BR>
	<BR>
	<table align="center">
	<TBODY>
	 <TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5 ><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    <tr></tr>
    <tr></tr>
    <tr>
    <c:if test="${flag == 'AssetIncomeDeclaration'}">
	<TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_INCOME_REQ" bundle="${a2}"/> ${Year} <fmt:message key="HAS_SUBMITTED" bundle="${a2}"/></b></TD>
	</c:if>
	<c:if test="${flag == 'AssetDeclarationOfYear'}">
	<TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_DECL_REQ_YEAR" bundle="${a2}"/> ${Year} <fmt:message key="HAS_SUBMITTED" bundle="${a2}"/></b></TD>
	</c:if>
	</tr>
	<tr></tr>
    <tr></tr>
    
    <tr>
	<td align="center">
	<hdiits:button type="button" name="Close"  captionid="ASSET_OK" bundle="${a2}" onclick="closewindow()"/>
	<hdiits:button type="button" name="Back"  captionid="BACK" bundle="${a2}" onclick="goBack()"/>
	
	</td></tr> 
	<tr></tr>
    <tr></tr>
		<TR bgColor=#386cb7>
    <TD class=fieldLabel align="center" colSpan=5><FONT color=#ffffff><STRONG><U></U></STRONG></FONT>
    </TD></TR>
    </TBODY></table>
	</div>
	</div>
	</hdiits:form>
	<script type="text/javascript">
	initializetabcontent("maintab")
	</script>	
</body>
</html>		
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>