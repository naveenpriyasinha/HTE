<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants"
	var="pensionConstants" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts_en_US"
	var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/pensionpay/DCRGTab.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>

<script type="text/javascript">
	ORDERNO = '<fmt:message key="PPMT.ORDERNO" bundle="${pensionAlerts}"></fmt:message>';
	ORDERDATE = '<fmt:message key="PPMT.ORDERDATE" bundle="${pensionAlerts}"></fmt:message>';
	ORDERAMOUNT = '<fmt:message key="PPMT.ORDERAMOUNT" bundle="${pensionAlerts}"></fmt:message>';
	AMOUNT = '<fmt:message key="PPMT.AMOUNT" bundle="${pensionAlerts}"></fmt:message>';
	VOUCHERNO = '<fmt:message key="PPMT.VOUCHERNO" bundle="${pensionAlerts}"></fmt:message>';
	VOUCHERDATE = '<fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionAlerts}"></fmt:message>';
	PAYMENTAUTH = '<fmt:message key="PPMT.PAYMENTAUTH" bundle="${pensionAlerts}"></fmt:message>';
</script>

<script>
	
</script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="LoopIndex" value="${resValue.Counter}"></c:set>
<c:set var="SubLoopIndex" value="${resValue.SubCounter}"></c:set>
<c:set var="HstDcrgDtlsPKCount" value="0"></c:set>
<c:set var="TempFlag" value="0"></c:set>

<hdiits:form name="DcrgHistoryForm" id="DcrgHistoryForm" method="POST"
	action="" encType="multipart/form-data" validate="true">
	<fieldset class="tabstyle" style="width: 100%"><legend>
	<b> <fmt:message key="PPMT.DCRGHISTORY" bundle="${pensionLabels}"></fmt:message></b>
	</legend> <!-- <div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 200px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;"> -->
	<input type="hidden" id="hidDcrgHistorySize" name="hidDcrgHistorySize"
		value="0" /> <!-- <hdiits:button id="btnAddRowDcrgHistory" type="button" name="btnAddRowDcrgHistory" captionid="PPMT.ADDROW" bundle="${pensionLabels}"
	onclick="dcrgHistoryTableAddRow();" /> -->

	<div align="center"><label><strong> HISTORY OF
	DCRG :- </strong></label></div>
	<div>&nbsp;</div>
	<div>
	<table id="tblDcrgHistory" border="1" width="100%">
		<tr style="width: 100%" class="datatableheader">
			<td width="5%" class="datatableheader"><fmt:message
				key="PPMT.SRNO" bundle="${pensionLabels}"></fmt:message></td>
			<td width="8%" class="datatableheader"><fmt:message
				key="PPMT.ORDERNO" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" class="datatableheader"><fmt:message
				key="PPMT.ORDERDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="8%" class="datatableheader"><fmt:message
				key="PPMT.ORDERAMOUNT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" class="datatableheader"><fmt:message
				key="PPMT.PAYPAYEBLEAMNT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message
				key="PPMT.VOUCHERNO" bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" class="datatableheader"><fmt:message
				key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message
				key="PPMT.PAYAUTH" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" class="datatableheader"><fmt:message
				key="PPMT.WITHHELDAMNT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="14%" class="datatableheader"><fmt:message
				key="PPMT.TOTRECVOERYAMNT" bundle="${pensionLabels}"></fmt:message></td>
			<td width="2%" class="datatableheader"><fmt:message
				key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		</tr>

		<c:choose>

			<c:when test="${resValue.lLstHstPnsnPmntDcrgDtlsVO !=null}">

				<c:forEach var="hstPnsnPmntDcrgDtls"
					items="${resValue.lLstHstPnsnPmntDcrgDtlsVO}" varStatus="Counter">
					<tr>
						<td class="tds" align="center">${Counter.index+1}</td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.orderNo}</td>

						<input type="hidden" name="hdnDcrgDtlsId"
							id="hdnDcrgDtlsId${Counter.index}"
							value="${hstPnsnPmntDcrgDtls.dcrgDtlsId}" />
						<input type="hidden" name="hdnBillNo"
							id="hdnBillNo${Counter.index}"
							value="${hstPnsnPmntDcrgDtls.billNo}" />

						<td class="tds" align="center"><fmt:formatDate
							dateStyle="full" pattern="dd/MM/yyyy"
							value="${hstPnsnPmntDcrgDtls.orderDate}" /></td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.totalOrderAmount}</td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.paidAmount}</td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.voucherNo}</td>

						<td class="tds" align="center"><fmt:formatDate
							dateStyle="full" pattern="dd/MM/yyyy"
							value="${hstPnsnPmntDcrgDtls.voucherDate}" /></td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.paymentAuthority}</td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.withHeldAmnt}</td>

						<td class="tds" align="center">${hstPnsnPmntDcrgDtls.totalRecoveryAmnt}</td>

						<td class="tds" align="center"><c:choose>
							<c:when test="${hstPnsnPmntDcrgDtls.billNo != null}">
								<img name="Image" id="Image${Counter.index}"
									src="images/CalendarImages/DeleteIcon.gif" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<img name="Image" id="Image${Counter.index}"
									src="images/CalendarImages/DeleteIcon.gif"
									onclick="RemoveTableRow(this, 'tblDcrgHistory');deleteHstDcrgDtlsPk('${hstPnsnPmntDcrgDtls.dcrgDtlsId}','${Counter.index+1}');" />
							</c:otherwise>
						</c:choose></td>
					</tr>
					<script>
	//document.getElementById("hidDcrgHistorySize").value = Number('${Counter.index}') + 1;
</script>
				</c:forEach>
			</c:when>
		</c:choose>

	</table>
	</div>
	<div>&nbsp;</div>
	<div>&nbsp;</div>
	<div align="center"><label><strong> RECOVERY
	DETAILS OF DCRG :- </strong></label></div>
	<div>&nbsp;</div>
	<div>
	<table id="RecoveyDtls" border="1" width="100%">
		<tr id="abc" style="width: 100%" class="datatableheader">
			<td width="5%" class="datatableheader"><fmt:message
				key="PPMT.SRNO" bundle="${pensionLabels}"></fmt:message></td>
			<td class="datatableheader"><fmt:message key="PPMT.RECFORDCRG"
				bundle="${pensionLabels}"></fmt:message></td>
		</tr>
		<c:forEach begin='1' end='${LoopIndex}' varStatus="Count">
			<tr id="tr${Count.index}">
				<td class="tds" align="center">${Count.index}</td>
				<td class="tds" align="center">
				<table id="tblDCRGRecoveyDtls" border="1" width="100%">
					<tr style="width: 100%" class="datatableheader">
						<td width="15%" class="datatableheader"><fmt:message
							key="PPMT.NATURE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message
							key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message
							key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>
					</tr>

					<c:if test="${resValue.RecoveryDtlsForDCRGHstryPopUp != null}">
						<c:forEach var="TrnPensionRecoveryDtlsVO"
							items="${resValue.RecoveryDtlsForDCRGHstryPopUp}"
							varStatus="SubCounter">
							<c:set var="dcrgDtlsId"
								value="${TrnPensionRecoveryDtlsVO.dcrgDtlsId}"></c:set>
							<c:choose>
								<c:when test="${dcrgDtlsId == null && TempFlag ==0}">
									<tr>
										<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.nature}</td>
										<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.amount}</td>
										<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.schemeCode}</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:if
										test="${resValue.lLstHstDcrgDtlsId[HstDcrgDtlsPKCount] == dcrgDtlsId}">
										<tr>
											<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.nature}</td>
											<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.amount}</td>
											<td class="tds" align="center">${TrnPensionRecoveryDtlsVO.schemeCode}</td>
											<c:set var="TempFlag" value="1"></c:set>
										</tr>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</table>
				</td>
			</tr>
			<c:set var="HstDcrgDtlsPKCount" value="${HstDcrgDtlsPKCount+1}"></c:set>
			<c:set var="TempFlag" value="0"></c:set>
		</c:forEach>

	</table>
	</div>
	
	</fieldset>
	<div>&nbsp;</div>
	<div>&nbsp;</div>
	<table width="100%">
		<tr>
			<td align="center"><hdiits:button id="btnSaveDcrg"
				name="btnSaveDcrg" type="button" captionid="PPMT.SAVE"
				onclick="saveDcrgHistoryDtls('H');" bundle="${pensionLabels}" /> <hdiits:button
				id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE"
				bundle="${pensionLabels}" onclick="winCls();" /></td>
		</tr>
	</table>
	<div>&nbsp;</div>
	<div>&nbsp;</div>
</hdiits:form>
