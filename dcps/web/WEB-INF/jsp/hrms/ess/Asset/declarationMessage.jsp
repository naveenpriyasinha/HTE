<%try { %>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
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
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="showreqId" value="${resValue.Pkvalue}"></c:set>
<c:set  var="flag" value="${resValue.flag}"></c:set>

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
		var flag='${flag}';
		if(flag == 'assetDeclaration')
		{
			var urlstyle="hrms.htm?actionFlag=getComboDetail&flag=assetDeclaration";
			document.approveAsset.action=urlstyle;
			document.approveAsset.submit();
		}
		else if(flag == 'assetBachmark')
		{
			var urlstyle="hrms.htm?actionFlag=getComboDetail&flag=assetBachmark";
			document.approveAsset.action=urlstyle;
			document.approveAsset.submit();
		}
	}
		
</script>
</head>

<body>
<hdiits:form name="approveAsset" validate="true" method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:choose>
	<c:when test="${flag == 'DeclarationOnJoining'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_ON_JOINING" bundle="${a2}"/></b></a></li>
	</c:when>
	<c:when test="${flag == 'assetBachmark'}">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_BANCHMARK" bundle="${a2}"/></b></a></li>
	</c:when>
	<c:otherwise>
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL" bundle="${a2}"/></b></a></li>
	</c:otherwise>
	</c:choose>
		
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
    <c:choose>
    <c:when test="${flag == 'DeclarationOnJoining'}">
    <TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_DECL_SAVED" bundle="${a2}"/></b></TD>
	</c:when>
	<c:when test="${flag == 'assetBachmark'}">
    <TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_BANCHMARK_SAVED" bundle="${a2}"/></b></TD>
	</c:when>
    <c:otherwise>
    <TD class=fieldLabel  align="center"><b><fmt:message key="ASSET_DECL_REQ_NO" bundle="${a2}"/><fmt:message key="HAS_SAVED" bundle="${a2}"/></b></TD>
    </c:otherwise>
    </c:choose>
    </tr>
	<tr></tr>
    <tr></tr>
    
    <tr>
	<td align="center">
	<hdiits:button type="button" name="Close"  captionid="ASSET_OK" bundle="${a2}" onclick="closewindow()"/>
	<hdiits:button type="button" name="Back"  captionid="BACK" bundle="${a2}" onclick="javascript:goBack();"/>
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