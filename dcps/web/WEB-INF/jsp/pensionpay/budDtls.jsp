<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resValue" scope="request" value="${result.resultValue}"></c:set>

<head>
	<script type="text/javascript">
		var lookupArray = new Array();
		var glDDOGrantAmt = 0;
		var glDDOExpAmt = 0;
		var NetTotal = 0;
		
		var BUD_CMBBUDTYPE = "<fmt:message key="BUD.CMBBUDTYPE" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTDMNDNO = "<fmt:message key="BUD.TXTDMNDNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTMAJHD = "<fmt:message key="BUD.TXTMAJHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSUBMAJHD = "<fmt:message key="BUD.TXTSUBMAJHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTMINHD = "<fmt:message key="BUD.TXTMINHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSUBHD = "<fmt:message key="BUD.TXTSUBHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTDTLHD = "<fmt:message key="BUD.TXTDTLHD" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBCLSEXP = "<fmt:message key="BUD.CMBCLSEXP" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBFUND = "<fmt:message key="BUD.CMBFUND" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTSCHNO = "<fmt:message key="BUD.TXTSCHNO" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_TXTHDCHRG = "<fmt:message key="BUD.TXTHDCHRG" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBCATEGORY = "<fmt:message key="BUD.CMBCATEGORY" bundle="${onlinebillprepAlerts}"></fmt:message>";
		var BUD_CMBBILLCODE = "<fmt:message key="BUD.CMBBILLCODE" bundle="${onlinebillprepAlerts}"></fmt:message>";
	</script>
</head>
	
	<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
		<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	</c:if>
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varReadonly" scope="page" value="readonly='readonly'"></c:set>
	<c:if test="${resValue.EditBill != null}">
		<c:set var="billCatDisabled" scope="page" value="disabled='disabled'"></c:set>
	</c:if>
			
	<c:if test="${resValue.Selected_ExpCls.Value != null}">
		<c:set var="varClassofExp" scope="page" value="${resValue.Selected_ExpCls.Value}"></c:set>
	</c:if>	
	<c:if test="${resValue.TrnBillRegister.clsExp != null}">
		<c:set var="varClassofExp" scope="page" value="${resValue.TrnBillRegister.clsExp}"></c:set>
	</c:if>		
		
	<c:if test="${resValue.Selected_Fund.Value != null}">
		<c:set var="varFund" scope="page" value="${resValue.Selected_Fund.Value}"></c:set>
	</c:if>			
	<c:if test="${resValue.TrnBillRegister.fund != null}">
		<c:set var="varFund" scope="page" value="${resValue.TrnBillRegister.fund}"></c:set>
	</c:if>			
	<c:if test="${resValue.TrnBillRegister.tcBill != null}">
		<c:set var="varBillCtgry" scope="page" value="${resValue.TrnBillRegister.tcBill}"></c:set>
	</c:if>	
	<c:if test="${resValue.Selected_BillType.Value != null}">
		<c:set var="varBillCtgry" scope="page" value="${resValue.Selected_BillType.Value}"></c:set>
	</c:if>	
	<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> <fmt:message key="BUD.DTLS" bundle="${onlinebillprepLabels}"></fmt:message> </b> </legend>
		<table align="left" width="80%">
			<hdiits:tr>	
				<td align="left" class="Label">
					<fmt:message key="BUD.SCHNO" bundle="${onlinebillprepLabels}"></fmt:message> 
				</td>
				<hdiits:td align="left">
				<c:choose>
				<c:when test="${resValue.isMonthlyBill != null}">
					<input type="text" name="txtSchemeNo" id="txtSchemeNo" maxlength="6" size="10" ${varDisabled} value="207112023"  tabindex="13"/>
				</c:when>
				<c:otherwise>
					<input type="text" name="txtSchemeNo" id="txtSchemeNo" maxlength="6" size="10" ${varDisabled} value="${resValue.schemeNo}"  tabindex="13"/>
				</c:otherwise>
				</c:choose>		 	
				</hdiits:td>
			</hdiits:tr>
			<hdiits:tr>	
				<hdiits:td align="left">
					
				</hdiits:td>
				<td align="left" class="Label">
					<input type="hidden" id="Demand" name="txtDmd" maxlength="3" size="10"  ${varDisabled} value="${resValue.TrnBillRegister.demandCode}"  tabindex="14"/>
				</td>
				<hdiits:td align="left">
					<input type="hidden" id="MjHd" name="txtMjrHd" maxlength="4" size="10" ${varDisabled} value="${resValue.TrnBillRegister.budmjrHd}" tabindex="15"/>
					<c:set var="tmpBudmjrHd" value="${resValue.TrnBillRegister.budmjrHd}"></c:set>
				</hdiits:td>
				<hdiits:td align="left">
					<input type="hidden" id="SubMjHd" name="txtSbMjrHd" maxlength="2" size="10"  ${varDisabled} value="${resValue.TrnBillRegister.budSubmjrHd}" tabindex="16"/>
					<c:set var="tmpBudSubmjrHd" value="${resValue.TrnBillRegister.budSubmjrHd}"></c:set>
				</hdiits:td>
				<hdiits:td align="left">
					<input type="hidden" id="MnrHd" name="txtMnrHd" maxlength="3" size="10"  ${varDisabled} value="${resValue.TrnBillRegister.budMinHd}" tabindex="17"/>
					<c:set var="tmpBudMinHd" value="${resValue.TrnBillRegister.budMinHd}"></c:set>
				</hdiits:td>
				<hdiits:td align="left">
					<input type="hidden" id="SubHd" name="txtSbHd" maxlength="2" size="10"  ${varDisabled} value="${resValue.TrnBillRegister.budSubHd}" tabindex="18"/>
					<c:set var="tmpBudSubHd" value="${resValue.TrnBillRegister.budSubHd}"></c:set>
				</hdiits:td>
				<hdiits:td align="left">
					<input type="hidden" id="DtlHd" name="txtDtldHd" maxlength="2" size="10"  ${varDisabled} value="${resValue.TrnBillRegister.budDtlHd}" tabindex="19"/>
				</hdiits:td>
			</hdiits:tr>
		</table>
	</fieldset>
	
