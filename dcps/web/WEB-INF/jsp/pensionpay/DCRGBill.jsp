<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/commonfunctions.js"/>"></script>
<script type="text/javascript" src="script/pensionpay/Common.js"></script>
<head>
	<script type="text/javascript" src="common/script/prototype-1.3.1.js"></script>
	<title>DCRG Bill</title>
</head>

<c:if test="${resValue.EditBill != null && resValue.EditBill == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
</c:if>

<!-- Hidden Values Start... -->
<hdiits:hidden name="hidDPPONO" default="${resValue.PPONo}" />
<hdiits:hidden name="PensionType" default="DCRG"/>
<hdiits:hidden name="hidDIsNewSaving" default="${resValue.isNewSavingBill}"/>
<hdiits:hidden name="hidDCaseAuditFlag" default="${resValue.CaseAudit}"/>
<hdiits:hidden name="hidDPnsnrCode" default="${resValue.PnsnrCode}" />
<hdiits:hidden name="hidHeadcode" default="${resValue.Headcode}" />
<hdiits:hidden name="hidScheme" default="${resValue.Scheme}" />
<hdiits:hidden name="hidTrnRqsHdrID" default="${resValue.TrnRqsHdrID}" />

<hdiits:hidden name="hidOrderNo" default="${resValue.OrderNo}" />
<hdiits:hidden name="hidVoucherNo" default="${resValue.VoucherNo}" />
<hdiits:hidden name="hidPayingAuth" default="${resValue.PayingAuth}" />
<fmt:formatDate var="OrderDate" dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.OrderDate}" />
<hdiits:hidden id="hidOrderDate" name="hidOrderDate" default="${OrderDate}"  />	
<fmt:formatDate var="VoucherDate" dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.VoucherDate}" />
<hdiits:hidden id="hidVoucherDate" name="hidVoucherDate" default="${VoucherDate}" />	

<hdiits:hidden name="hidPrintDCRGBillString" id="hidPrintDCRGBillString" default="${resValue.PrintDCRGBillString}" /> 
<!-- Hidden Values End... -->
<script>
function printDcrgBill()
{
	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=750");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    var printString = document.getElementById("hidPrintDCRGBillString").value;
    cw.document.write(printString);	
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>'); 
    cw.location.reload(false);   
}
</script>
<div align="left">
	<center><b><fmt:message key="PEN.DCRGDTLS" bundle="${pensionLabels}"></fmt:message></b></center>
	<br/><br/>
<fieldset  style="width:100%" class="tabstyle">
	<legend id="headingMsg">	
		<b><fmt:message key="PEN.DCRGDTLS" bundle="${pensionLabels}"></fmt:message></b>
		</legend>
	<!-- <div align="right" style="width: 90%">
			<fmt:message key="PEN.PRINTBILL" bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;
			<a href="javascript:printDcrgBill()"> 
			<fmt:message key="PEN.PRINTBILL" bundle="${pensionLabels}"></fmt:message></a>
		</div>
	 -->
	<table align="center" width="98%" border="1" class="Label" bordercolor="#eed0aa" >		
		<tr class="datatableheader" height="25" >
			<td colspan="4" align="center" height="15" class="HLabel" style="border: 2px solid;" >
				 <b><fmt:message key="CMN.PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message></b> 
			</td>
		</tr>
		<hdiits:tr>
			<hdiits:td>
				<table align="left" width="100%">
					<hdiits:tr>
						<hdiits:td width="25%">
							<fmt:message key="PEN.PPONO" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td width="25%">
							<hdiits:text id="txtPPONo" size="24" name="txtPPONo" readonly="true" default='${resValue.PPONo}' /> 
						</hdiits:td>
						<hdiits:td align="left">
							<fmt:message key="PEN.DATE" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td >
							<fmt:formatDate var="DCRGCrntDate" dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDate}" />
							<hdiits:text id="txtDate" name="txtDate" size="24" default='${DCRGCrntDate}' readonly="true" />							
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td>
							<fmt:message key="PEN.NAMEOFPENSIONER" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td >
							<hdiits:text id="txtName" size="50" name="txtName" readonly="true" default="${resValue.Name}" /> 
						</hdiits:td>
						
					</hdiits:tr>
					
				</table>
			</hdiits:td>		
		</hdiits:tr>
		<tr height="20" class="datatableheader">
			<td colspan="4" style="border: 2px solid;">
				<fmt:message key="PEN.DCRGDTLS" bundle="${pensionLabels}"></fmt:message>
			</td>
		</tr>
		<hdiits:tr>
			<hdiits:td>
				<table align="left" width="100%">				
					<hdiits:tr>
						<hdiits:td width="25%">
							<fmt:message key="PEN.DCRGAMOUNT" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td width="25%">
							<input type="hidden" value="${resValue.DCRGAmount}" id="txtExpenditure" name="txtExpenditure"/>
							<fmt:formatNumber var="fmtDCRGAmount" pattern="0.00" value="${resValue.DCRGAmount}"></fmt:formatNumber>
							<hdiits:text size="24" id="txtDCRGAmt" name="txtDCRGAmt" style="text-align:right" readonly="true" default="${fmtDCRGAmount}" /> 
						</hdiits:td>
						<!--  NEW FIELD -->
						<hdiits:td width="25%">
							<fmt:message key="PEN.WITHHELDAMNT" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td width="25%">
							<input type="text" size="24" id="txtWithheldAmnt" name="txtWithheldAmnt" style="text-align:right" readonly="readonly"  value="${resValue.WithHeldAmount}" />
						</hdiits:td>
						<!--  NEW FIELD -->
					</hdiits:tr>
					
					
					<hdiits:tr>
					<!--  NEW FIELD -->
						<hdiits:td width="25%">
							<fmt:message key="PEN.AMNTAFTERWITHHELD" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td width="25%">
							<input type="text" size="24" id="txtAmntAfterWithHeld" name="txtAmntAfterWithHeld" style="text-align:right" readonly="readonly"  value="${resValue.AmountAfterWithHeld}"/> 
						</hdiits:td>	
						<!--  NEW FIELD -->
						<hdiits:td width="25%">
							<fmt:message key="PEN.RECOVERYAMOUNT" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td width="25%">
							<fmt:formatNumber var="fmtRecoveryAmount" pattern="0.00" value="${resValue.RecoveryAmount}"></fmt:formatNumber>
							<input type="hidden" value="${resValue.RecoveryAmount}" id="txtRecovery" name="txtRecovery"/>
							<hdiits:text size="24" id="txtRecAmt" name="txtRecAmt" style="text-align:right" readonly="true" default="${fmtRecoveryAmount}" /> 
						</hdiits:td>
					</hdiits:tr>
					
					<hdiits:tr>
						<hdiits:td>
							<fmt:message key="PEN.NETAMOUNT" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td colspan="3">
							<fmt:formatNumber var="fmtNetAmount" pattern="0.00" value="${resValue.NetAmount}"></fmt:formatNumber>
							<hdiits:text size="24" id="txtNetAmt" name="txtNetAmt" style="text-align:right" readonly="true" default="${fmtNetAmount}" /> 
						</hdiits:td>
					</hdiits:tr>					
					<tr height="20">
						<hdiits:td>
							<fmt:message key="PEN.NETAMTINWORDS" bundle="${pensionLabels}"></fmt:message>
						</hdiits:td>
						<hdiits:td colspan="3">
							<label id="txtNetAmtInword" name="txtNetAmtInword" readonly="true" value=""></label>
							<b> <script> document.write(getAmountInWords(${resValue.NetAmount})); </script> </b>
						</hdiits:td>
					</tr>
				</table>		
			</hdiits:td>
		</hdiits:tr>
</table>
</fieldset>
</div>






