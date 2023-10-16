<%try { %>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.asset.AssetLables" var="a3" scope="request"/>
<html>
<head>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>	
<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/hrms/ess/asset/assetAddress.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">		


</script>
</head>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="declaredYear" value="${resValue.declaredYear}"></c:set>	
<c:set var="InboxIncomeData" value="${resValue.InboxIncomeData}"></c:set>
<c:set var="AnnualIncomeOfEmployee" value="${resValue.AnnualIncomeOfEmployee}"></c:set>	
<c:set var="cancleFlag" value="${resValue.cancleFlag}"></c:set>	

<body>
<hdiits:form name="PurchaseSaleDetails" validate="true" method="POST" encType="multipart/form-data" action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="INCOME_DECL" bundle="${a3}"/>${declaredYear}</b></a></li>
	</ul>
</div>
<div id="asset" name="asset">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<BR>
		
<!--<table id="EmployeeDtls"  width="100%" align="center">
<tr>
<td width="100%" bgcolor="#386CB7" align="center"><b><u><FONT color="WHITE"><fmt:message key="DETAILS_APPLICANT" bundle="${a3}"/></FONT></u></b></td>
</tr>
<tr>
<td width="25%">
-->

<c:if test="${cancleFlag == 'Y'}">
<TD class=fieldLabel  align="center"><b><FONT color="RED"><fmt:message key="CANCELLED_REQ" bundle="${a3}"/></FONT></b></TD>
</c:if>
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
		
<hdiits:fieldGroup bundle="${a3}" expandable="true" id="incmDclrtnFldGrp" titleCaptionId="INCOME_DECL_SYSTEMYEAR">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="INCOME_DECL_SYSTEMYEAR" bundle="${a3}"/> ${declaredYear}<fmt:message key="SPACE" bundle="${a3}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>		
	--><br>	

<table class=tabtable>
<tr>
<td class=fieldLabel width="25%"><hdiits:caption captionid="ANNUAL_INCOME_OF_EMPLOYEE" bundle="${a3}"/></td>
<td  width="25%" id="annualIncomeofEmployee"><c:out value="${AnnualIncomeOfEmployee}"/></td>
<td></td>
<td></td>
</tr>
</table>

<table id="incomeDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="5%"><b><hdiits:caption captionid="SRNO" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="ASSETID" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="SELL_DATE" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="APP_MARKET_PRICE" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="ANNUAL_INCOME" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="25%"><b><hdiits:caption captionid="REMARKS" bundle="${a3}" /></b></td>
</tr>	

<c:forEach items="${InboxIncomeData}" var="InboxIncomeData" varStatus="x">
<tr>
<td><c:out value="${InboxIncomeData.rowId}"/></td>
<td><c:out value="${InboxIncomeData.assetId}"/></td>
<td>
<c:out value="${InboxIncomeData.assetName}"/></td>
<td><c:out value="${InboxIncomeData.transactionDate}"/></td>
<td><c:out value="${InboxIncomeData.transactionPrice}"/></td>
<td><c:out value="${InboxIncomeData.marketPrice}"/></td>
<td><c:out value="${InboxIncomeData.annualIncome}"/></td>
<td><c:out value="${InboxIncomeData.remarks}"/></td>
</tr>
</c:forEach>


</table>
</hdiits:fieldGroup>
		
		</div>
	</div>
	
	<script type="text/javascript">
	initializetabcontent("maintab")
	</script>	
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />	
	</hdiits:form>
</body>
</html>		

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
		