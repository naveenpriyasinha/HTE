<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/SixPayFPArrear.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstPPOYearDesc" value="${resValue.lLstPPOYearDesc}"></c:set>
<c:set var="lLstPPOMonthId" value="${resValue.lLstPPOMonthId}"></c:set>
<c:set var="lLstPPOMonthDesc" value="${resValue.lLstPPOMonthDesc}"></c:set>
<c:set var="lLstPPOInstallments" value="${resValue.lLstPPOInstallments}"></c:set>
<c:set var="lLstRemarks" value="${resValue.lLstRemarks}"></c:set>
<c:set var="counter" value="0"></c:set>
<c:set var="LoopIndex" value="${resValue.Counter}"></c:set>

<hdiits:form name="ViewPPOCaseDtls" encType="multipart/form-data" id="ViewPPOCaseDtls" validate="true" method="post">
<div style="float: inherit; width: 90%;">
<c:forEach begin='1' end='${LoopIndex}' varStatus="Count">
<div align="center"><label><strong> PPO Case revised ${LoopIndex} time :- </strong></label></div>
<div>&nbsp;</div>
<table id="arrearDtls" border="1" width="75%" align="center">
	<tr style="width: 100%" class="datatableheader">
		<td width="20%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="50%" class="datatableheader"><fmt:message
			key="SIXPAYARR.PAYIN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="%" class="datatableheader"><fmt:message
			key="SIXPAYARR.INSTALLAMT" bundle="${pensionLabels}"></fmt:message></td>
		<td width="%" class="datatableheader"><fmt:message
			key="CMN.REMARKS" bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	<c:forEach  begin='1' end='5' varStatus="item">
	<tr>
		<td class="HLabel" align="center">${item.index}</td>
		<td class="HLabel"  align="center">
			<c:choose>
			<c:when test="${lLstPPOMonthDesc[counter] == null}">
				<label>No Month</label>
				<label>and No Year</label>
			</c:when>
			<c:otherwise>
				<label>${lLstPPOMonthDesc[counter]}</label>
				<label>${lLstPPOYearDesc[counter]}</label>
			</c:otherwise>
			</c:choose>
		</td>
		<td class="HLabel"  align="center">
			<label>${lLstPPOInstallments[counter]}</label>
		</td>
		<td class="HLabel"  align="center">
		<c:choose>
			<c:when test="${lLstRemarks[counter] == 'null'}">
				<label>No Remarks</label>
			</c:when>
			<c:otherwise>
				<label>${lLstRemarks[counter]}</label>
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<c:set var="counter" value="${counter+1}"></c:set>
	</c:forEach>
	
	<c:set var="SubLoopIndex" value="5"></c:set>
</table>
<div>&nbsp;</div>
<c:set var="LoopIndex" value="${LoopIndex-1}"></c:set>
</c:forEach>
</div>
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