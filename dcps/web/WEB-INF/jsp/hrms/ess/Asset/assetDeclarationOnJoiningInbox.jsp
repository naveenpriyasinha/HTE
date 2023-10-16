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
<script type="text/javascript"  src="script/hrms/ess/asset/assetJoining.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="assetDataForJoiningDeclaration" value="${resValue.assetDataForJoiningDeclaration}"></c:set>
<c:set var="inheritedPersonDtlsList" value="${resValue.inheritedPersonDtlsList}"></c:set>
<c:set var="empDoj" value="${resValue.doj}"></c:set>
<c:set var="cancleFlag" value="${resValue.cancleFlag}"></c:set>

<script type="text/javascript">		
function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		dd1 = (dd1 > 0 && dd1 < 10)? ("0"+dd1) : dd1;
   		var mm1=dt.getMonth()+1;
   		mm1 = (mm1 > 0 && mm1 < 10)? ("0"+mm1) : mm1;
   		var yy1=dt.getYear();
   		var sysDate=dd1+'/'+mm1+'/'+yy1;
   		//alert(sysDate);
   		var doj = "${empDoj}";
   		//alert(doj);
   		//sysDate = "12/07/2005";
   		var dateDiff = getDateDiffInString(doj,sysDate);
   		
   		trow= document.getElementById('totalServiceTable');
   		trow.cells[1].innerText=dateDiff;
   		
   		
}

</script>
</head>


<body>
<hdiits:form name="PurchaseSaleDetails" validate="true" method="POST" encType="multipart/form-data" action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ASSET_DECL_ON_JOINING" bundle="${a3}"/></b></a></li>
	</ul>
</div>
<div id="asset" name="asset">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
	
<c:if test="${cancleFlag == 'Y'}">
<TD class=fieldLabel  align="center"><b><FONT color="RED"><fmt:message key="CANCELLED_REQ" bundle="${a3}"/></FONT></b></TD>
</c:if>
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup bundle="${a3}" expandable="true" id="totleYrFldGrp" titleCaptionId="TOTAL_YEARS_OF_SERVICE">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="TOTAL_YEARS_OF_SERVICE" bundle="${a3}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>


--><TABLE class=tabtable width="25%">  
	<tr>  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="GAZETTED"  bundle="${a3}"/></td>
		<td width="25%">Not Applicable</td>    
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
	<tr>  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="NON_GAZETTED" bundle="${a3}"/></td>
		<td width="25%">Not Applicable</td>  
		<td width="25%"></td>
		<td width="25%"></td>  
	</tr>
	<tr id="totalServiceTable">  
		<TD class=fieldLabel width="25%"><hdiits:caption captionid="TOTAL_SERVICE" bundle="${a3}"/></td>
		<td width="25%">
			<script>getDate1();</script>
		</td> 
		<td width="25%"></td>
		<td width="25%"></td>
	</tr>
</TABLE>	
</hdiits:fieldGroup>
		
<hdiits:fieldGroup bundle="${a3}" expandable="true" titleCaptionId="ASSET_DTLS">
<!--<TABLE class=tabtable>
	<TBODY>
	<TR bgColor=#386cb7>
	<TD class=fieldLabel align=middle colSpan=5 ><FONT color=#ffffff><STRONG><U><fmt:message key="ASSET_DTLS" bundle="${a3}"/><fmt:message key="SPACE" bundle="${a3}"/></U></STRONG> 
	</FONT></TD></TR>
	</TBODY>
</TABLE>		
	--><br>	
	
	
<table id="joiningDataTable" style="" align="center" style="border-collapse: collapse;" borderColor="BLACK"  border=1 width="98%"> 

<tr bgcolor="#C9DFFF">
<td class="fieldLabel"  align="center" width="02%"><b><hdiits:caption captionid="SRNO" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="09%"><b><hdiits:caption captionid="ASSETID" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="NAME_OF_PRPTY" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="REGI_NO" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="18%"><b><hdiits:caption captionid="ADD_OF_ASSET" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="DATE_OF_TRANS" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="APP_PRICE" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="ASSET_OBTAIN_TYPE" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="10%"><b><hdiits:caption captionid="ASSET_OBTAIN_FROM" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="12%"><b><hdiits:caption captionid="NAME_OF_OWNER" bundle="${a3}" /></b></td>
<td class="fieldLabel"  align="center" width="07%"><b><hdiits:caption captionid="RELATION_TYPE" bundle="${a3}" /></b></td>
</tr>	

<c:forEach items="${assetDataForJoiningDeclaration}" var="assetDataForJoiningDeclaration" varStatus="x">
<tr>
<td><c:out value="${assetDataForJoiningDeclaration.rowId}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.assetId}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.assetName}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.registrationNo}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.assetAddress}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.transactionDate}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.transactionPrice}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.obtainType}"/></td>
<td>
<c:forEach items="${assetDataForJoiningDeclaration.hrEssAssetJoiningInheritedDtl}" var="hrEssAssetJoiningInheritedDtl" varStatus="x">
<c:out value="(${x.index+1}) "/><c:out value="${hrEssAssetJoiningInheritedDtl.firstName}"/> 
</c:forEach>
</td>
<td><c:out value="${assetDataForJoiningDeclaration.firstName}"/></td>
<td><c:out value="${assetDataForJoiningDeclaration.relationType}"/></td>
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
		