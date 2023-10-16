<%try { %>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="a4" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 
<c:set var="isAjax" value="<%=request.getParameter("isAjaxRequest")%>"> </c:set>  

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
		
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="showreqId" value="${resValue.Pkvalue}"></c:set>
<c:set  var="RequestedUser" value="${resValue.RequestedUser}"></c:set>
<c:set  var="RequestedDesignation" value="${resValue.RequestedDesignation}"></c:set>


<body>
<hdiits:form name="approveAsset" validate="true" method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="PER_PUR_SALE" bundle="${a4}"/></b></a></li>
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
    <TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_REQ_NO" bundle="${a4}"/> ${showreqId} <fmt:message key="REQ_REJECT_SEND" bundle="${a4}"/> ${RequestedUser}/${RequestedDesignation} </b></TD>
	</tr>
	<tr></tr>
    <tr></tr>
    
    <tr>
	<td align="center">
	<hdiits:button type="button" name="Close"  captionid="ASSET_OK" bundle="${a4}" onclick="closewindow()"/>
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