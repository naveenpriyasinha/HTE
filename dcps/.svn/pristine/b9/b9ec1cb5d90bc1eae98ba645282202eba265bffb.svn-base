<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/PensionConfig.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="CurrMappingDtls" value="${resValue.lLstCurrMappingDtls}"></c:set>
<c:set var="counter" value="1"></c:set>


<hdiits:form name="ViewauditorMappingInfo" encType="multipart/form-data" id="ViewauditorMappingInfo"
	validate="true" method="post">
	<div
	style="float: inherit; width: 90%;">
<table align="left" width="90%" cellspacing="5" border="1">
	<tr class="datatableheader">
		<td width="10%" class="HLabel"><fmt:message key="PPMT.AUDITORNAME" bundle="${pensionLabels}"></fmt:message></th>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message></th>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.BANKBRNNAME" bundle="${pensionLabels}"></fmt:message></th>
	</tr>
	<c:forEach var="CurrMapping" items="${CurrMappingDtls}">
	<tr>
		<td class="tds" align="center"><label>${CurrMapping[0]}</label></td>
		<td class="tds" align="center"><label>${CurrMapping[1]}</label></td>
		<td class="tds" align="center"><label>${CurrMapping[2]}</label></td>
	</tr>
	<c:set var="counter" value="${counter+1}"></c:set>
	</c:forEach>
	
</table>
</div>
<div>&nbsp;</div>
<table align="center">
<tr>
<td width="30%" align="center" >
<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
</hdiits:form>



  