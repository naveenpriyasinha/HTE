<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>

<script type="text/javascript"  src="script/common/common.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstOldPpoNo" value="${resValue.lLstOldPpoNo}"></c:set>
<c:set var="lLstNewPpoNo" value="${resValue.lLstNewPpoNo}"></c:set>
<c:set var="lLstUpdatedDate" value="${resValue.lLstUpdatedDate}"></c:set>
<c:set var="LoopIndex" value="${resValue.LoopIndex}"></c:set>
<c:set var="counter" value="0"></c:set>

<hdiits:form name="ViewPPONoHistory" encType="multipart/form-data" id="ViewPPONoHistory" validate="true" method="post">
<div style="float: inherit; width: 90%;">

 <div align="center"><label><strong> PPO No. History :- </strong></label></div> 
<div>&nbsp;</div>
<table id="PpoNoHstryDtls" border="1" width="75%" align="center">
	<tr style="width: 100%" class="datatableheader">
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.SRNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" class="datatableheader"><fmt:message
			key="PPMT.OLDPPONO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" class="datatableheader"><fmt:message
			key="PPMT.NEWPPONO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" class="datatableheader"><fmt:message
			key="PPMT.UPDATEDDATE" bundle="${pensionLabels}"></fmt:message></td>
		
		
	</tr>
	<c:forEach  begin='1' end='${LoopIndex}' varStatus="item">
	<tr>
		<td class="HLabel" align="center">${item.index}</td>
		<td class="HLabel"  align="center">
			<label>${lLstOldPpoNo[counter]}</label>
		</td>
		<td class="HLabel"  align="center">
			<c:choose>
			<c:when test="${item.index == LoopIndex}"><strong><label>${lLstNewPpoNo[counter]}</label></strong></c:when>
			<c:otherwise><label>${lLstNewPpoNo[counter]}</label></c:otherwise>
			</c:choose>
			
		</td>
		<td class="HLabel"  align="center">
			<label>${lLstUpdatedDate[counter]}</label>
		</td>
	</tr>
	<c:set var="counter" value="${counter+1}"></c:set>
	</c:forEach>
</table>
<div>&nbsp;</div>
<div>&nbsp;</div>
<table align="center">
<tr>
<td width="30%" align="center" >
<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>
</hdiits:form>