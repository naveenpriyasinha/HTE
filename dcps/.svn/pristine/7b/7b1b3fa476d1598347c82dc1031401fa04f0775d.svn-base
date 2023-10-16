<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionConstant" var="pensionConst" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionBillLabels" scope="request" />

<head>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/commonfunctions.js"/>"></script>
<script type="text/javascript" src="script/pensionpay/Common.js"></script>
<script>
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "ECSReport", urlstyle);
}
</script>
</head>
<c:set var="lMonthlyPensionBillVO" value="${resValue.lMonthlyPensionBillVO}"></c:set>
<c:set var="MstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}"></c:set>
<c:set var="TrnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>
<c:set var="billNo" value="${resValue.TrnBillRegister.billNo}"></c:set>
<c:set var="viewFlag" value="${resValue.ViewFlag}"></c:set>

<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
</c:if>

<center><b><fmt:message key="PNSNBILL.PNSNBILLDTL" bundle="${pensionBillLabels}"></fmt:message></b></center>
	<br/>
<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg">	
	<b><fmt:message key="PNSNBILL.PNSNBILLDTL" bundle="${pensionBillLabels}"></fmt:message></b>
	</legend>
<!-- Hidden Values Start... -->
<hdiits:hidden name="PensionType" default="PENSION"/>
<hdiits:hidden name="hidDPPONO" default='${lMonthlyPensionBillVO.ppoNo}'/>
<hdiits:hidden name="hidTrnPensionRqstID" default="${TrnPensionRqstHdrVO.pensionRequestId}" />
<hdiits:hidden name="hidPensionerCode" default="${lMonthlyPensionBillVO.pensionerCode}" />
<hdiits:hidden name="hidBillForMnth" default="${resValue.CrntMonth}" />
<hdiits:hidden name="hidHeadcode" default="${lMonthlyPensionBillVO.headCode}" />
<hdiits:hidden name="hidScheme" default="${TrnPensionRqstHdrVO.schemeType}" />
<hdiits:hidden name="hidAliveFlg" default="${MstPensionerHdrVO.aliveFlag}" />
<hdiits:hidden name="hidPayROP" default="${TrnPensionRqstHdrVO.ropType}" />
<hdiits:hidden name="hidNewROPBasic" default="${lMonthlyPensionBillVO.basicPensionAmount}" />
<hdiits:hidden name="hidDIsNewSaving" default="${resValue.isNewSavingBill}"/>
<hdiits:hidden name="hidUptoBillCrtDate" default="${resValue.UptoBillCrtDate}"/>
<hdiits:hidden name="hidFromPayDate" default="${resValue.FromPayDate}"/>
<hdiits:hidden name="hidAllnBf11136" default="${lMonthlyPensionBillVO.allnBf11136}"/>
<hdiits:hidden name="hidAllnBf11156" default="${lMonthlyPensionBillVO.allnBf11156}"/>
<hdiits:hidden name="hidAllnAf11156" default="${lMonthlyPensionBillVO.allnAf11156}"/>
<hdiits:hidden name="hidAllnAf10560" default="${lMonthlyPensionBillVO.allnAf10560}"/>
<hdiits:hidden name="hidAllnAfZp" default="${lMonthlyPensionBillVO.allnAfZp}"/>

<hdiits:hidden name="hidAccountNo" default="${lMonthlyPensionBillVO.accountNo}"/>
<hdiits:hidden name="hidArrearLC" default="${lMonthlyPensionBillVO.arrearLC}"/>
<hdiits:hidden name="hidDaRate" default="${lMonthlyPensionBillVO.tiPercent}"/>
<hdiits:hidden name="hidLedgerNo" default="${lMonthlyPensionBillVO.ledgerNo}"/>
<hdiits:hidden name="hidPageNo" default="${lMonthlyPensionBillVO.pageNo}"/>
<hdiits:hidden name="hidPunishmentCut" default="${lMonthlyPensionBillVO.punishmentCutAmt}"/>
<hdiits:hidden name="hidROPType" default="${lMonthlyPensionBillVO.ropType}"/>
<hdiits:hidden name="hidTotalArrearAmt" default="${lMonthlyPensionBillVO.totalArrearAmt}"/>
<hdiits:hidden name="hidBankCode" default="${resValue.bankCode}"/>
<hdiits:hidden name="hidBranchCode" default="${resValue.branchCode}"/>


<table align="center" width="98%" class="Label" border="1"  bordercolor="#eed0aa" >
	
	<tr class="datatableheader">
		<td colspan="4" align="center" height="20" class="HLabel" style="border: 2px solid;"> 
			<b><fmt:message key="CMN.PENSIONERDTLS" bundle="${pensionBillLabels}"></fmt:message></b> 
		</td>
	</tr>
	
	<hdiits:tr>
		<hdiits:td align="left">
			<table align="left" width="100%">
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.PPONO" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<hdiits:text size="25" name="txtPPONO" default='${lMonthlyPensionBillVO.ppoNo}' readonly="true" /> 
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.NAMEOFPENSIONER" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<hdiits:text size="25" name="txtPnsnerName" default="${lMonthlyPensionBillVO.pensionerName}" readonly="true" /> 
					</hdiits:td>	
				</tr>
				<!--<tr>
					<!--<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.PENSIONBILLFORMONTH" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<c:set var="EffectedPervMonth" value="${(resValue.CrntMonth) % 100}"></c:set>
						<c:set var="EffectedPervYear" value="${((resValue.CrntMonth) - EffectedPervMonth) / 100}"></c:set>
							<fmt:formatNumber var="fmtEffectedPervYear" pattern="0" value="${EffectedPervYear}"></fmt:formatNumber>
						<hdiits:text size="25" name="txtMonth" default="${resValue.MonthList[EffectedPervMonth-1].commonDesc}-${fmtEffectedPervYear}" readonly="true" />
					</hdiits:td>
				</tr>-->
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.EFFECTFROMDATE" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<hdiits:text size="25" name="txtPnsnEffFrmDate" default="${resValue.FromPayDate}" readonly="true" /> 
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.EFFECTTODATE" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<hdiits:text size="25" name="txtPnsnEffToDate" default="${resValue.UptoBillCrtDate}" readonly="true" /> 
					</hdiits:td>
				</tr>
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.DATE" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%" >
						<fmt:formatDate var="PnsrCrntDate" dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.PnsrCrntDate}" />
						<hdiits:text name="txtPnsrBillDate" default="${PnsrCrntDate}" size="25" readonly="true" />
					</hdiits:td>
				</tr>
			</table>
		</hdiits:td>
	</hdiits:tr>
	
	<tr class="datatableheader">
		<td colspan="4" align="center" height="20" class="HLabel" style="border: 2px solid;">  
			<b><fmt:message key="PNSNBILL.PENSIONDTL" bundle="${pensionBillLabels}"></fmt:message></b>
		</td>
	</tr>
					
	<hdiits:tr>
		<hdiits:td align="left" width="100%">
			
			<table align="left" width="100%" >
				<tr>
					<hdiits:td width="25%">
						<c:out value="${resValue.BasicPnsnFlage}"></c:out> <fmt:message key="PNSNBILL.PENSION" bundle="${pensionBillLabels}"></fmt:message>		
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtBasicPensionAmt" pattern="0.00" value="${lMonthlyPensionBillVO.basicPensionAmount}"></fmt:formatNumber>
						<hdiits:text name="txtBasicPension" size="25" default="${fmtBasicPensionAmt}" readonly="true" style="text-align: right;" />
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.PEONALLOWANCE" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtPeonAllowanceAmt" pattern="0.00" value="${lMonthlyPensionBillVO.peonAllowanceAmt}"></fmt:formatNumber>
						<hdiits:text name="txtPeonAllowanceAmt" size="25" default="${fmtPeonAllowanceAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
				</tr>
				
				<tr> 
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.DP" bundle="${pensionBillLabels}"></fmt:message><fmt:message key="PNSNBILL.AMOUNT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtDPPercentAmt" pattern="0.00" value="${lMonthlyPensionBillVO.dpPercentAmount}"></fmt:formatNumber>
						<hdiits:text name="txtDPPercentAmt" size="25" default="${fmtDPPercentAmt}" readonly="true" style="text-align: right;"/>						
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.MAALLOWANCE" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtMedicalAllowenceAmt" pattern="0.00" value="${lMonthlyPensionBillVO.medicalAllowenceAmount}"></fmt:formatNumber>
						<hdiits:text name="txtMedicalAllowenceAmt" size="25" default="${fmtMedicalAllowenceAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
				</tr>
				
				<tr> 
					<hdiits:td width="25%">
						<fmt:message key="MNTH.ADPAMOUNT" bundle="${pensionBillLabels}"></fmt:message>	
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtADPAmt" pattern="0.00" value="${lMonthlyPensionBillVO.adpAmount}"></fmt:formatNumber>
						<hdiits:text name="txtADPAmt" size="25" default="${fmtADPAmt}" readonly="true" style="text-align: right;"/>						
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.GALLANTRYAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtGallantryAmt" pattern="0.00" value="${lMonthlyPensionBillVO.gallantryAmt}"></fmt:formatNumber>
						<hdiits:text name="txtGallantryAmt" size="25" default="${fmtGallantryAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
				</tr>
				  
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="MNTH.DAAMOUNT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtTIPercentAmt" pattern="0.00" value="${lMonthlyPensionBillVO.tiPercentAmount}"></fmt:formatNumber>
						<hdiits:text name="txtTIPercentAmt" size="25" default="${fmtTIPercentAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.OTHRBENEFIT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtOtherBenefitAmt" pattern="0.00" value="${lMonthlyPensionBillVO.otherBenefit}"></fmt:formatNumber>
						<hdiits:text name="txtOtherBenefitAmt" size="25" default="${fmtOtherBenefitAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
				</tr>
				
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="MNTH.IR1AMOUNT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtIR1Amt" pattern="0.00" value="${lMonthlyPensionBillVO.ir1Amt}"></fmt:formatNumber>
						<hdiits:text name="txtIR1Amt" size="25" default="${fmtIR1Amt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.DAARREARAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtlDaArrearAmt" pattern="0.00" value="${lMonthlyPensionBillVO.TIArrearsAmount}"></fmt:formatNumber>
						<hdiits:text name="txtTIArrearAmt" id="txtTIArrearAmt" size="25" default="${fmtlDaArrearAmt}" readonly="true"  style="text-align: right;"/>
					</hdiits:td>					
				</tr>
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="MNTH.IR2AMOUNT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtIR2Amt" pattern="0.00" value="${lMonthlyPensionBillVO.ir2Amt}"></fmt:formatNumber>
						<hdiits:text name="txtIR2Amt" size="25" default="${fmtIR2Amt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.OTHRARREARAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtlOthrArrearAmt" pattern="0.00" value="${lMonthlyPensionBillVO.otherArrearsAmount}"></fmt:formatNumber>
						<hdiits:text name="txtOthArrearAmt" id="txtOthArrearAmt" size="25" default="${fmtlOthrArrearAmt}" readonly="true"  style="text-align: right;"/>
						<c:choose>
							<c:when test="${billNo =='' or billNo==null and !(viewFlag=='Y')}">
								<hdiits:button type="button" name="btnArrear" id="btnArrear"  captionid="BTN.ARREAR" bundle="${pensionBillLabels}" classcss="bigbutton"onclick="window_new_update('ifms.htm?actionFlag=openRevisedArrear&BillFlag=P');"/>
							</c:when>
						</c:choose>
						<input type="hidden" value="0" id="hdnCalcOthrArrearAmt" />
					</hdiits:td>			
				</tr>
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="MNTH.IR3AMOUNT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtIR3Amt" pattern="0.00" value="${lMonthlyPensionBillVO.ir3Amt}"></fmt:formatNumber>
						<hdiits:text name="txtIR3Amt" size="25" default="${fmtIR3Amt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.6PCARREARAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmt6PCArrearAmt" pattern="0.00" value="${lMonthlyPensionBillVO.arrear6PC}"></fmt:formatNumber>
						<hdiits:text name="txt6PCArrearAmt" id="txt6PCArrearAmt" size="25" default="${fmt6PCArrearAmt}" readonly="true"  style="text-align: right;"/>
					</hdiits:td>				
				</tr>
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.PENSIONCUTAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtPensionCutAmount" pattern="0.00" value="${lMonthlyPensionBillVO.pensionCutAmount}"></fmt:formatNumber>
						<hdiits:text name="txtPensionCutAmount" size="25" default="${fmtPensionCutAmount}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.COMMUTARREARAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtCommArrearAmt" pattern="0.00" value="${lMonthlyPensionBillVO.arrearCommutation}"></fmt:formatNumber>
						<hdiits:text name="txtCommArrearAmt" id="txtCommArrearAmt" size="25" default="${fmtCommArrearAmt}" readonly="true"  style="text-align: right;"/>
					</hdiits:td>							
				</tr>
				<tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSN.CVPMONTHLY" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtCvpMonthlyAmt" pattern="0.00" value="${lMonthlyPensionBillVO.cvpMonthlyAmount}"></fmt:formatNumber>
						<hdiits:text name="txtCvpMonthlyAmt" size="25" default="${fmtCvpMonthlyAmt}" readonly="true" style="text-align: right;"/>
					</hdiits:td>
					<hdiits:td width="10%">
						<fmt:message key="PNSNBILL.OTHERDIFFARREARAMT" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="40%">
						<fmt:formatNumber var="fmtOthrDiffArrearAmt" pattern="0.00" value="${lMonthlyPensionBillVO.arrearOthrDiff}"></fmt:formatNumber>
						<hdiits:text name="txtOthrDiffArrearAmt" id="txtOthrDiffArrearAmt" size="25" default="${fmtOthrDiffArrearAmt}" readonly="true"  style="text-align: right;"/>
					</hdiits:td>						
				</tr>
				<tr>
					<hdiits:td width="25%">
						<b><fmt:message key="MNTH.GROSSAMOUNT" bundle="${pensionBillLabels}"></fmt:message></b>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtPensionBillAmt" pattern="0.00" value="${lMonthlyPensionBillVO.grossAmount}"></fmt:formatNumber>
						<hdiits:text name="txtPensionBillAmt" size="25" default="${fmtPensionBillAmt}" readonly="true" style="text-align: right;"/>
						<input type="hidden" value="${lMonthlyPensionBillVO.grossAmount}" name="txtExpenditure" id="txtExpenditure" />
					</hdiits:td>
					<hdiits:td width="10%">
					</hdiits:td>
					<hdiits:td width="40%" align="right">
					</hdiits:td>					
				</tr>

			</table>			
		</hdiits:td>
	</hdiits:tr>
	
	<tr>
		<hdiits:td align="left" width="100%">
			<table align="left" width="100%" >
				<hdiits:tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.RECODEDCTN" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtRecoveryAmt" pattern="0.00" value="${lMonthlyPensionBillVO.recoveryAmount}"></fmt:formatNumber>
						<hdiits:text name="txtPnsnRecovery" size="25" default="${fmtRecoveryAmt}" readonly="true" style="text-align: right;"/>
						<input type="hidden" id="txtRecovery" name="txtRecovery" value="${lMonthlyPensionBillVO.recoveryAmount}" />
					</hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td width="25%">
						<b><fmt:message key="PNSNBILL.NETPNSNAMNT" bundle="${pensionBillLabels}"></fmt:message></b>
					</hdiits:td>
					<hdiits:td width="25%">
						<fmt:formatNumber var="fmtlNetPensionAmt" pattern="0.00" value="${lMonthlyPensionBillVO.netPensionAmount}"></fmt:formatNumber>
						<hdiits:text name="txtNetPensionAmt" id="txtNetPensionAmt" size="25" default="${fmtlNetPensionAmt}" readonly="true"  style="text-align: right;"/>
						<input type="hidden" name="txtNetAmt" id="txtNetAmt" value="${lMonthlyPensionBillVO.netPensionAmount}" />
					</hdiits:td>
					<hdiits:td width="50%" colspan="2">
					</hdiits:td>
				</hdiits:tr>
				<hdiits:tr>
					<hdiits:td width="25%">
						<fmt:message key="PNSNBILL.NETAMNTINWORDSINRS" bundle="${pensionBillLabels}"></fmt:message>
					</hdiits:td>
					<hdiits:td width="75%" colspan="3">
						<label id="txtNetAmtInword" name="txtNetAmtInword"><b> 
						<script> document.write(getAmountInWords(${lMonthlyPensionBillVO.netPensionAmount})); </script>
						</b></label>
					</hdiits:td>
				</hdiits:tr>
			</table>			
		</hdiits:td>
	</tr>		
</table>
</fieldset>
