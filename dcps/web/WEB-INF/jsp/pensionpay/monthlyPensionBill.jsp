<%try{ %>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionToolTip" var="pensionToolTip" scope="request"/>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="script/pensionpay/pensionBills.js"> </script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:hidden name="Flag" id="Flag" default="${resValue.Flag}"/>
<hdiits:hidden name="hidforMonth" default="${resValue.Date}"/>
<hdiits:hidden name="PensionType" default="Monthly"/>
<hdiits:hidden name="hidHeadCode" default="${resValue.HeadCode}"/>
<hdiits:hidden name="hidBank" default="${resValue.Bank}"/>
<hdiits:hidden name="hidBranch" default="${resValue.Branch}"/>
<hdiits:hidden name="hidScheme" default="${resValue.Scheme}"/>
<hdiits:hidden name="txtNetAmt" id="txtNetAmt" default="${resValue.TotalNetAmt + resValue.TotalOtherPartyAmount}" />
<hdiits:hidden name="hidPayROP1986" default="${resValue.PayROP1986}" />
<hdiits:hidden name="hidPayROP1996" default="${resValue.PayROP1996}" />
<hdiits:hidden name="hidBillNo" default="${resValue.billNo}"/>
<hdiits:hidden name="hidLessAmntFlag" id="hidLessAmntFlag" default="${resValue.LessAmntFlag}" />
<hdiits:hidden name="hidLessAmntPPONos" id="hidLessAmntPPONos" default="${resValue.LessAmntPPONos}" />
<hdiits:hidden name="hidNoAccntPPONos" id="hidNoAccntPPONos" default="${resValue.NoAccntPPONos}" />
<hdiits:hidden name="hidNoPensionerFlag" id="hidNoPensionerFlag" default="${resValue.NoPensionerFlag}" />
<hdiits:hidden name="billStatus" id="billStatus" default="${resValue.billStatus}" /> 
<hdiits:hidden name="hidPrintString" id="hidPrintString" default="${resValue.PrintString}" /> 


<script>
function printMonthlyBill()
{

	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes,width=670");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 12in 12in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    var printString = document.getElementById("hidPrintString").value;
    cw.document.write(printString);	
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>');
    cw.location.reload(false); 
}

</script>
<c:set var="sno" value="0" />
<c:set var="sno1" value="0" />
<c:set var="sno2" value="0" />

<b><fmt:message key="MNTH.BILLTITLE" bundle="${pensionLabels}" ></fmt:message></b>
<fieldset class="tabstyle">
	<legend id="headingMsg"> <b> HeadCode Wise Summary</b> </legend>
	<br />
	<hdiits:table align="center" width="100%">
		<hdiits:tr>
			<hdiits:td align="left"><b><fmt:message key="MNTH.FORMONTH" bundle="${pensionLabels}"></fmt:message></b></hdiits:td>
				<c:set var="ForMonth" value="${resValue.ForMonth}"></c:set>
			<hdiits:td align="left" colspan="2"><c:out value="${resValue.MonthName} ${resValue.ForBillYear}"></c:out></hdiits:td>
		<!-- 	<c:if test="${!(resValue.billNo == null or resValue.billNo == '')}">
			
			<hdiits:td align="right">Print Bill:</hdiits:td>
			<hdiits:td align="right"><hdiits:a href="javascript:printMonthlyBill()" caption="Print Bill"> </hdiits:a></hdiits:td> 
			</c:if>
		 -->
		</hdiits:tr>
		
		<hdiits:tr>
			<hdiits:td align="left"><b><fmt:message key="MNTH.BANK" bundle="${pensionLabels}"></fmt:message></b></hdiits:td>
			<hdiits:td align="left">${resValue.BankName}</hdiits:td>
			<hdiits:td align="left"><b><fmt:message key="MNTH.BRANCH" bundle="${pensionLabels}"></fmt:message></b></hdiits:td>
			<hdiits:td align="left">${resValue.BranchName}</hdiits:td>
		</hdiits:tr>
	</hdiits:table>
	<br>


	<c:set var="SubTotalGross" value="0"></c:set>
	<c:set var="SubTotalDedu" value="0"></c:set>
	<c:set var="SubTotalNet" value="0"></c:set>
	<c:set var="TiArrearAmount" value="${MonthlyPensionList.TIArrearsAmount}"></c:set>
	
<table width="100%" align="center" id="ExpDetPostTbl" border="1" bordercolor="#C9DFFF" cellspacing="0">	

	<tbody>
		<tr class="datatableheader" height="20">
			<td align="center" class="HLabel">Category</td>
			<td align="center" class="HLabel">Description</td>
			<td align="center" class="HLabel">Gross Amount</td>
			<td align="center" class="HLabel">Deduction A</td>
			<td align="center" class="HLabel">Deduction B</td>
			<td align="center" class="HLabel">Net Amount</td>
		</tr>
		<tr class="datatableheader" height="20">
			<td colspan="6" align="center" class="HLabel">Pension Bill Details</td>
		</tr>
		<c:forEach var="lHeadCodeDtls" items="${resValue.HeadCodeWiseDtls}">
		<tr class="odd">
			<td> ${lHeadCodeDtls[0]} </td>
			<td align="left"> ${lHeadCodeDtls[1]} </td>
			<td align="right"> ${lHeadCodeDtls[2]} <c:set var="SubTotalGross" value="${SubTotalGross + lHeadCodeDtls[2]}"></c:set> </td>
			<td align="right"> ${lHeadCodeDtls[3]} <c:set var="SubTotalDeduA" value="${SubTotalDeduA + lHeadCodeDtls[3]}"></c:set> </td>
			<td align="right"> ${lHeadCodeDtls[4]} <c:set var="SubTotalDeduB" value="${SubTotalDeduB + lHeadCodeDtls[4]}"></c:set> </td>
			<td align="right"> ${lHeadCodeDtls[5]} <c:set var="SubTotalNet" value="${SubTotalNet + lHeadCodeDtls[5]}"></c:set> </td>
		</tr>
		</c:forEach>
		<tr class="odd">
			<td> &nbsp; </td>
			<td align="right"><b> Sub Total </b>&nbsp;&nbsp;</td>
			<td align="right"> <b>${SubTotalGross}</b> </td>
			<td align="right"> <b>${SubTotalDeduA}</b> </td>
			<td align="right"> <b>${SubTotalDeduB}</b> </td>
			<td align="right"> <b>${SubTotalNet}</b> </td>
		</tr>
		</tbody>
</table>
<table width="100%" align="center" id="ExpDetPostTbl" border="1" bordercolor="#C9DFFF" cellspacing="0">	
	<tbody>
		<tr class="odd">
			<td align="left" width="20%">&nbsp;&nbsp;&nbsp;&nbsp;<b>Total Net Amount </b></td>
			<td align="left" >&nbsp;&nbsp;<b>Rs. <fmt:formatNumber pattern="0" value="${resValue.TotalNetAmt}" /></b></td>		
		</tr>
		<tr class="odd">
			<td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;<b>Total Net Amount in words : </b>
			<td align="left" >&nbsp;&nbsp;Rupees ${resValue.TotalNetAmtInWords} only</td>		
		</tr>
	
	</tbody>
	
	</table>
	<fieldset class="tabstyle">
		<legend  id="headingMsg">Monthly Pension List</legend>
		
		<div style="float: inherit; border:1px solid #000000; background-color: transparent;width:950px; height:350px; overflow: scroll; overflow-x:scroll; overflow-y:scroll;  " class="scrollablediv">
			<display:table list="${resValue.MonthlyPensionList}" id="MonthlyPensionList" requestURI="" style="width:100%"  excludedParams="contentUsingAjax">		
				<display:setProperty name="paging.banner.placement" value="bottom"/>
				<c:set var="sno" value="${sno+1}"></c:set>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.SRNO" headerClass="datatableheader" >${sno}</display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSNR.CATEGORY" headerClass="datatableheader">${MonthlyPensionList.series}</display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PEN.PPONO" headerClass="datatableheader" >${MonthlyPensionList.ppoNo}</display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PEN.NAMEOFPENSIONER" headerClass="datatableheader" >${MonthlyPensionList.pensionerName}</display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PROVPNSN.ACNO" headerClass="datatableheader" >${MonthlyPensionList.accountNo}</display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLBF56" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.allnBf11156}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLAF56" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.allnAf11156}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLAF60" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.allnAf10560}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLBF36" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.allnBf11136}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.ALLAFZP" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.allnAfZp}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.DPAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.dpPercentAmount}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PNSN.TIAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.tiPercentAmount}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.IRA1" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.ir1Amt}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.IRA2" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.ir2Amt}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.IRA3" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.ir3Amt}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PSB.DIFFERENCEAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.totalArrearAmt}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="MNTH.GROSSAMNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.grossAmount}"></fmt:formatNumber></display:column>
				<display:column style="text-align: left;" class="tablecelltext" titleKey="PEN.NETAMOUNT" headerClass="datatableheader" ><fmt:formatNumber pattern="0" value="${MonthlyPensionList.netPensionAmount}"></fmt:formatNumber></display:column>
				<display:footer media="html"></display:footer>
			</display:table>
		</div>
		</fieldset>

	
	
		<!--<c:forEach var="MonthlyPensionVo"  items="${resValue.MonthlyPensionList}" > 
			<input type="hidden" name="hidPnsnerCode" value="${MonthlyPensionVo.pensionerCode}" />
		</c:forEach>-->
	
	
	
<script type="text/javascript" src="script/pensionpay/Monthly.js"> </script>
<%}catch(Exception ex){ex.printStackTrace(); }%>	