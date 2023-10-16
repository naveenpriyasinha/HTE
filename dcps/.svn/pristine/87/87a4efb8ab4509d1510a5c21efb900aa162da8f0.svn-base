<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="PensionLabels" scope="request" />

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/AdminRateMst.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TIRateTypeText" value="${resValue.TIRateTypeText}"></c:set>
<c:set var="HeadCodeTypeText" value="${resValue.HeadCodeTypeText}"></c:set>
<c:set var="ForPension" value="${resValue.ForPension}"></c:set>
<c:set var="lLstFromDate" value="${resValue.lLstFromDate}"></c:set>
<c:set var="lLstToDate" value="${resValue.lLstToDate}"></c:set>
<c:set var="lLstRate" value="${resValue.lLstRate}"></c:set>
<c:set var="lLstMinAmnt" value="${resValue.lLstMinAmnt}"></c:set>
<c:set var="count" value="0"></c:set>
<c:set var="LoopIndex" value="${resValue.LoopIndex}"></c:set>
<hdiits:form name="ViewDARateDtls" encType="multipart/form-data" id="ViewDARateDtls" validate="true" method="post">
<div style="float: inherit; width: 90%;">

<div align="center"><label ><strong> DA Rate History for ${TIRateTypeText} and State/Dept. ${HeadCodeTypeText} :- </strong></label></div>
<div>&nbsp;</div>
<table id="daRateDtls" border="1" width="75%" align="center">
	<tr style="width: 100%" class="datatableheader">
		<td width="5%" class="datatableheader"><fmt:message key="DARATEHST.SRNO" bundle="${PensionLabels}"></fmt:message></td>
		<td width="30%" class="datatableheader"><fmt:message
			key="DARATEHST.FROMDATE" bundle="${PensionLabels}"></fmt:message></td>
		<td width="30%" class="datatableheader"><fmt:message
			key="DARATEHST.TODATE" bundle="${PensionLabels}"></fmt:message></td>
		<td width="10%" class="datatableheader"><fmt:message
			key="DARATEHST.RATE" bundle="${PensionLabels}"></fmt:message></td>
		<c:if test="${TIRateTypeText == 'DA_1986'}">
			<c:if test="${ForPension != 'UPTO 1750'}">
				<td width="25%" class="datatableheader"><fmt:message key="DARATEHST.AMNT" bundle="${PensionLabels}"></fmt:message></td>
			</c:if>
		</c:if>
		
	</tr>
	<c:forEach begin='1' end='${LoopIndex}' varStatus="Count">
	<tr>
		<td class="HLabel" align="center">${Count.index}</td>
		<td class="HLabel"  align="center">
			<c:choose>
			<c:when test="${lLstFromDate[count] == null}">
				<label><strong> No Effective From Date</strong></label>
			</c:when>
			<c:otherwise>
				<label><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lLstFromDate[count]}" /></label>
			</c:otherwise>
			</c:choose>
		</td>
		<td class="HLabel"  align="center">
			<c:choose>
			<c:when test="${lLstToDate[count] == null}">
				<label><strong>Current rate in use</strong></label>
			</c:when>
			<c:otherwise>
				<label><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lLstToDate[count]}" /></label>
			</c:otherwise>
			</c:choose>
		</td>
		<td class="HLabel"  align="center">
			<label>${lLstRate[count]}</label>
		</td>
		<c:if test="${TIRateTypeText == 'DA_1986'}">
			<c:if test="${ForPension != 'UPTO 1750'}">
				<td class="HLabel"  align="center"><label>${lLstMinAmnt[count]}</label></td>
			</c:if>
		</c:if>
		
	</tr>
	<c:set var="count" value="${count+1}"></c:set>
	</c:forEach>
	
	
</table>
<div>&nbsp;</div>
<c:set var="LoopIndex" value="${LoopIndex-1}"></c:set>

</div>
<div>&nbsp;</div>
<table align="center">
<tr>
<td width="30%" align="center" >
<hdiits:button name="btnClose"	type="button" captionid="DARATEHST.CLOSE" bundle="${PensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
</hdiits:form>