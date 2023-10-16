
<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="ReportingDDO" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="details" value="${resValue.details}"></c:set>
<c:set var="sum" value="${resValue.sum}"></c:set>
<c:set var="billid" value="${resValue.billid}"></c:set>

<c:set var="totalRd" value="${resValue.totalRd}"></c:set>
<c:set var="totalLic" value="${resValue.totalLic}"></c:set>
<c:set var="totalRevenue_Stamp" value="${resValue.totalRevenue_Stamp}"></c:set>
<!--<c:set var="totalMisc" value="${resValue.totalMisc}"></c:set>
<c:set var="totalGslis" value="${resValue.totalGslis}"></c:set>

<c:set var="totaloSGPF" value="${resValue.totaloSGPF}"></c:set>
<c:set var="totalosGIS" value="${resValue.totalosGIS}"></c:set>-->
<c:set var="totalQRent" value="${resValue.totalQRent}"></c:set>
<c:set var="totalBankLoan" value="${resValue.totalBankLoan}"></c:set>

<c:set var="netAmt" value="${resValue.netAmt}"></c:set>
<c:set var="totalSLoan" value="${resValue.totalSLoan}"></c:set>
<c:set var="totalToatlDed" value="${resValue.totalToatlDed}"></c:set>
<c:set var="totalSalaryPayabale" value="${resValue.totalSalaryPayabale}"></c:set>

<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>

</head>

<form name="AbstractConsBillDtls" method="post">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li ><a href="#" rel="tcontent1" class="selected"><b>Abstract Reports.
		</b></a></li>
	</ul>
	</div>

<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<input type="hidden" id="hdConsBillId" value="${resValue.billid}"/>
	<div class="scrollablediv" >
<table width="100%" border="1">

<tr >
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Sr. No.</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Institute Name</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Bill Name</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Gross Salary</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>FA</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Exc. Pay Recovery</b></td>

<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>GPF</b></td>
<%-- 
<table border="0" align ="center" width="100%"><tr align ="center"><td colspan="2" align="center">
<b>GPF</b></td></tr><tr><td align="center"><b>UEPF</b></td><td align="center"><b>STATE GPF</b></td></tr></table>
</td>
--%>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>DCPS(Regular)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>DCPS(Delayed)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>DCPS(Pay)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>DCPS(DA)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NPS EMPLR DED</b></td>
<%--
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold">
<table border="0" align ="center" width="100%"><tr align ="center"><td colspan="2" align="center"><b>DCPS</b></td></tr><tr><td align="center"><b>DCPS DELAYED</b></td><td align="center"><b>DCPS REGULAR</b></td></tr></table>
</td>
--%>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>IT</b></td>
<!-- <td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Revenue Stamp</b></td> -->
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>PT</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Comp. Adv</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Other Deduction</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>PLI</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>GIS</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>ACC Policy</b></td>

<%--added by samadhan to display GIS ZP--%>
<!-- <td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>GIS(ZP)</b></td> -->

<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Total Deduction</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Net Pay</b></td>

<%--
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold">
<table border="0" align ="center" width="100%"><tr align ="center"><td colspan="13" align="center"><b>COLLEGE DEDUCTION AS PER AQUITTANCE ROLL</b></td></tr><tr>
<td align="center"><b>RD</b></td>
<td align="center"><b>LIC</b></td>
<td align="center"><b>MISC</b></td>
<td align="center"><b>GSLIS</b></td>
<td align="center"><b>OTHER STATE GPF</b></td>
<td align="center"><b>OTHER STATE GIS</b></td>
<td align="center"><b>QUATER RENT</b></td>
<td align="center"><b>BANK LOAN</b></td>
<td align="center"><b>SOCIETY LOAN</b></td>
<td align="center"><b>TOTAL DEDUCTION</b></td>
</tr></table>
</td>
--%> 

<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(RD)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(LIC)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(MISC)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(Bank Loan)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(Society Loan)</b></td>
<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>NGR(Total Deductions)</b></td>


<td align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"><b>Salary Payable</b></td>
</tr>

<c:forEach var="details" items="${details}">
<tr>
	<td align="center"><c:out value='${details.srNo}'/></td>
	<td align="center"><c:out value='${details.instituteName}'/></td>
	<td align="center"><c:out value='${details.billname}'/></td>
	<td align="center"><c:out value='${details.grossSalary}'/></td>
	<td align="center"><c:out value='${details.fa}'/></td>
	<td align="center"><c:out value='${details.excPay}'/></td>
	
	<td align="center"><c:out value='${details.gpf}'/></td>
	<%---
	<td>
	<table border="0" align ="center" width="100%"><tr><td align="center"><c:out value='${details.gpf}'/></td></tr></table></td>
	
	<td>
	<table border="0" align ="center" width="100%"><tr><td align="center"><c:out value='${details.dcpsDelayed}'/></td><td align="center"><c:out value='${details.dcpsregular}'/></td></tr></table></td>
 ----%>
 	<td align="center"><c:out value='${details.dcpsregular}'/></td>
 	<td align="center"><c:out value='${details.dcpsDelayed}'/></td> 
 	<td align="center"><c:out value='${details.dcpsPay}'/></td> 	
    <td align="center"><c:out value='${details.dcpsDa}'/></td>
    <td align="center"><c:out value='${details.npsEmployr}'/></td>
	<td align="center"><c:out value='${details.it}'/></td>
	<!-- <td align="center"><c:out value='${details.revenueStamp}'/></td> -->
	
	<td align="center"><c:out value='${details.pt}'/></td>
	<td align="center"><c:out value='${details.compAdv}'/></td>
	<td align="center"><c:out value='${details.otherDed}'/></td>
	<td align="center"><c:out value='${details.pli}'/></td>
	<td align="center"><c:out value='${details.gis}'/></td>
	<td align="center"><c:out value='${details.accPolicy}'/></td>
	
	<%--added by samadhan to display GIS zp--%>
	<%-- <td align="center"><c:out value='${details.gisZp}'/></td> --%>
	
	
	<td align="center"><c:out value='${details.totalDed}'/></td>
	<td align="center"><c:out value='${details.netPay}'/></td>
	<%---<td align="center">
	<table border="0" align ="center" width="100%"><tr> ----%>.
	<td align="center"><c:out value='${details.rd}'/></td>
	<td align="center"><c:out value='${details.lic}'/></td>
	<td align="center"><c:out value='${details.misc}'/></td><!--
	<td align="center"><c:out value='${details.gslis}'/></td>
	<td align="center"><c:out value='${details.otherStatGpf}'/></td>
	<td align="center"><c:out value='${details.otherStatGis}'/></td>
	<td align="center"><c:out value='${details.qtrRent}'/></td>
	--><td align="center"><c:out value='${details.bankLoan}'/></td>
	<td align="center"><c:out value='${details.socLoan}'/></td>
	<td align="center"><c:out value='${details.totalNonDed}'/></td>
	<%---</tr></table></td>----%>
	<td align="center"><c:out value='${details.salaryPayable}'/></td>
	
	


</tr>
</c:forEach>



<c:forEach var="sum" items="${sum}">
<tr>
<td align="center"></td>
<td align="center"></td>
<td align="center">Total</td>
<td align="center"><c:out value='${sum[3]}'/></td>
<td align="center"><c:out value='${sum[1]}'/></td>
<td align="center"><c:out value='${sum[2]}'/></td>
<td align="center"><c:out value='${sum[4]}'/></td>
<td align="center"><c:out value='${sum[6]}'/></td>
<td align="center"><c:out value='${sum[5]}'/></td>
<td align="center"><c:out value='${sum[17]}'/></td>
<td align="center"><c:out value='${sum[8]}'/></td>
<td align="center"><c:out value='${sum[16]}'/></td>
<td align="center"><c:out value='${sum[7]}'/></td>
<!--  <td align="center"><c:out value='${totalRevenue_Stamp}'/></td> -->
<td align="center"><c:out value='${sum[9]}'/></td>

<td align="center"><c:out value='${sum[10]}'/></td>
 
<td align="center"><c:out value='${sum[11]}'/></td>
<td align="center"><c:out value='${sum[12]}'/></td>
<td align="center"><c:out value='${sum[13]}'/></td>
<td align="center"><c:out value='${sum[18]}'/></td>
<%--added by samadhan for gis zp--%>
<%-- <td align="center"><c:out value='${sum[16]}'/></td> --%>

<td align="center"><c:out value='${sum[14]}'/></td>
<td align="center"><c:out value='${netAmt}'/></td>
<td align="center"><c:out value='${totalRd}'/></td>
<td align="center"><c:out value='${totalLic}'/></td>
<td align="center"><c:out value='${totalMisc}'/></td>
<!--
<td align="center"><c:out value='${totalGslis}'/></td>
<td align="center"><c:out value='${totaloSGPF}'/></td>
<td align="center"><c:out value='${totalosGIS}'/></td>
<td align="center"><c:out value='${totalQRent}'/></td>
-->
<td align="center"><c:out value='${totalBankLoan}'/></td>
<td align="center"><c:out value='${totalSLoan}'/></td>
<td align="center"><c:out value='${totalToatlDed}'/></td>
<td align="center"><c:out value='${totalSalaryPayabale}'/></td>
</tr>
</c:forEach>
</table></div></div>
<br>
	
	<table align="center">
		<tr>
			<td align="right" style="width:50%">
						
		<hdiits:button name="btnExcel" style="width:100%"
				id="btnExcel" type="button" value="Export To Excel"
				onclick="return generateConsExcelReport();" />
			</td>
			
			<td align="left" style="width:50%">
						
		<hdiits:button name="btnBack"
				id="btnBack" type="button" value="Back" style="width:100%"
				onclick="return returnToViewConsDetails();" />
			</td>
			
		</tr>
	</table>		
</div>
	
<form>



<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

function returnToViewConsDetails(){
	var url="ifms.htm?actionFlag=viewConsolidatedBill";
	document.AbstractConsBillDtls.action=url;
	document.AbstractConsBillDtls.submit();
}
	
	
</script>

<script type="text/javascript">
function generateConsExcelReport(){
	
	var consBillId = '${billid}';

	var url="ifms.htm?actionFlag=generateExcelForAbstractRep&billid="+consBillId;
	document.AbstractConsBillDtls.action=url;
	document.AbstractConsBillDtls.submit();
}
</script>