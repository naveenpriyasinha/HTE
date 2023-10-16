<%@ include file="../core/include.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="rcptObjHds" value="${resValue.rcptObjHds}" scope="request"> </c:set>
<c:set var="expObjHds" value="${resValue.expObjHds}" scope="request"></c:set>
<c:set var="recObjHds" value="${resValue.recObjHds}" scope="request"></c:set>

<script type="text/javascript" src="script/onlinebillprep/Common.js"> </script>

<hdiits:form name="medBillPrintableForm" validate="true" method="post">
	<input type="hidden" name="strHTML" />
	<hdiits:table width="100%">
			<hdiits:tr>
				<td align="right" width="100%">
					<input type='button' id="printbtn" value="Print Bill" onclick="printBill()"></input>
				</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="Center" width="100%"><font size=4><b>FORM G.T.R. NO - 29</b></font></td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="Center" width="100%">(See Rule 172 and 189)</td>
			</hdiits:tr>
			<hdiits:tr>
				<td>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="Center" width="100%">Medical charges reimbursement bill gazetted</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="Center" width="100%">Goverment servent/non-gazetted establishment</td>
			</hdiits:tr>
	</hdiits:table>
 	<hr>
	<table cols="3" class="TableBorderLTRBN" width="100%" >
			<hdiits:tr>
				<td align="left" class="Label3"><b>Bill Register No.</b> 
						<font class="Label3"></font><c:out value='${resValue.TrnBillRegister.billCntrlNo}'></c:out></td>
				<td align="left" class="Label3"><b>Token No.</b> 
						<c:out value='${resValue.TrnBillRegister.tokenNum}'></c:out></td>
				<td align="left" class="Label3"><b>Date:-</b>&nbsp;<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.TrnBillRegister.billDate}"/>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=3>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td>&nbsp;</td>
				<td class="Label3" align="left"><b>Token No.</b> 
						<c:out	value='${resValue.TrnBillRegister.tokenNum}'></c:out></td>
				<td class="Label3" align="left"><b>Date:-</b>
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.TrnBillRegister.billDate}"/></td>
			</hdiits:tr>
	</table>

	<br /><hr>

	<table cols=4 border="0" cellspacing="0" cellpadding="0" bgcolor='white' class="TableBorderLTRBN" width="100%">
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td class="Label3" align="left">&nbsp;<b>Detailed medical bill of Shri</b></td>
				<td colspan=3 align="left"><c:out value='${resValue.BillDtls.TrnMedblHdr.empName}'></c:out></td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td class="Label3" align="left">&nbsp;<b>Designation</b></td>
				<td align=left id="dsgn"><c:out value='${resValue.BillDtls.TrnMedblHdr.empDesgn}'></c:out></td>
				<td class="Label3">&nbsp;<b>for the establishment of the</b></td>
				<td align=left id="dsgn"><c:out value='${resValue.BillDtls.TrnMedblHdr.deptName}'></c:out></td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td class="Label3" align=left>&nbsp;<b>for the Month of</b></td>
				<td class="Label3" align="left"><c:out value='${resValue.MonthName}'></c:out>,&nbsp;<c:out value='${resValue.Selected_Year}'></c:out> </td>
				<td colspan=2>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
	</table>

	<br><hr>

	<table width="100%" cols=4>
			<hdiits:tr>
				<td align="left" width="25%" class="Label3"><b>COMPUTER	INPUT DATA</b></td>
				<td>&nbsp;</td>
				<td align="left" width="25%" class="Label3">(To be filled in by	Treasury)</td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td align="left" width="25%" class="Label3"><b>1. District </b></td>
				<td>&nbsp;</td>
				<td width="25%" class="Label3" align="left"><b>2. Month & Year</b></td>
				<td>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=2 width="25%">&nbsp;</td>
				<td align="left" width="25%" class="Label3"><b>3. Voucher No.</b></td>
				<td>&nbsp;</td>
			</hdiits:tr>
			<hdiits:tr>
				<td colspan=4>&nbsp;</td>
			</hdiits:tr>
	</table>
<hr>
	<table cellspacing="0" cellpadding="0" class="TableBorderLTRBN" width="100%">
		<hdiits:tr>
			<td width="50%">

				<table width="100%">
						<hdiits:tr>
							<td class="Label3" align=left width="50%"><b>4.&nbsp;Class of Expenditure </b></td>
							<td align=left width="50%"><b>:</b>&nbsp;<c:out value='${resValue.ClassOfExp}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td align=left width="50%" class="Label3"><b>5.&nbsp;Fund</b></td>
							<td align=left width="50%"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.fund}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td align=left width="50%" class="Label3"><b>6.&nbsp;Drawing Officer </b></td>
							<td align=left width="50%"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.drawing}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td width="50%" align=left class="Label3"><b>7.&nbsp;Demand	No. </b></td>
							<td width="50%" align=left><b>:</b>&nbsp;<c:out	value='${resValue.TrnBillBudheadDtls.dmndNo}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td width="50%" align=left class="Label3"><b>8:&nbsp;Type of Budget </b></td>
							<td align=left width="50%" class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TypeOfBudget}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td width="50%" align=left class="Label3"><b>9.&nbsp;Schme No. </b></td>
							<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.schemeNo}'></c:out></td>
						</hdiits:tr>
						<hdiits:tr>
							<td width="50%" align=left class="Label3"><b>10.&nbsp;Head Chargeble </b></td>
							<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.headChrg}'></c:out></td>
						</hdiits:tr>
				</table>
			</td>
			<td width="50%">

				<table border="1" bgcolor='white' cellspacing="0" cellpadding="0" width="100%">
						<hdiiits:tr >
							<td align=center width=15% class="Label3"><b>Budget Code</b></td>
							<td align=center width=50% class="Label3"><b>Particulars</b></td>
							<td align=center width=15% class="Label3"><b>EDP Code</b></td>
							<td align=center width=15% class="Label3"><b>Amount</b></td>
						</hdiits:tr>
						<c:set var="expAmt" value="0"/>
						<c:forEach var="billEdpVO" items="${expObjHds}"  varStatus="No" > 
						<hdiits:tr>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.objHdCode}'></c:out></td>
							<td align=left width=50% class="Label3"><c:out value="${billEdpVO.edpDesc}"/></td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpCode}'></c:out>(+)</td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpAmt}'></c:out></td>
						<c:set var="expAmt" value="${billEdpVO.edpAmt+expAmt}"></c:set>
						</hdiits:tr>
						</c:forEach>
						<c:set var="recAmt" value="0"/>
						<c:forEach var="billEdpVO" items="${recObjHds}"  varStatus="No" > 
						<hdiits:tr>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.objHdCode}'></c:out></td>
							<td align=left width=50% class="Label3"><c:out value="${billEdpVO.edpDesc}"/></td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpCode}'></c:out>(-)</td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpAmt}'></c:out></td>
						<c:set var="recAmt" value="${billEdpVO.edpAmt+recAmt}"></c:set>
						</hdiits:tr>
						</c:forEach>
						<c:set var="recptAmt" value="0"/>
						<c:forEach var="billEdpVO" items="${rcptObjHds}"  varStatus="No" > 
						<hdiits:tr>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.objHdCode}'></c:out></td>
							<td align=left width=50% class="Label3"><c:out value="${billEdpVO.edpDesc}"/></td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpCode}'></c:out>(-)</td>
							<td align=left width=15% class="Label3"><c:out value='${billEdpVO.edpAmt}'></c:out></td>
						<c:set var="recptAmt" value="${billEdpVO.edpAmt+recptAmt}"></c:set>
						</hdiits:tr>
						</c:forEach>
						<c:set var="recptAmt" value='${expAmt-recAmt-recptAmt}' />
						<hdiits:tr>
							<td align=left width=15%>&nbsp;</td>
							<td align=left width=50% class="Label3"><b>NET TOTAL</b></td>
							<td align=left width=15%>&nbsp;</td>
							<td align=left width=15%><b><c:out value='${recptAmt}'></c:out></b></td>
						</hdiits:tr>
				</table>
			</td>
	</table>
	<br /><br />

	<table border="1" cols=2 bgcolor='white' cellspacing="0" cellpadding="0" width="100%">
		<tr>
			<td width="50%">
			<table width="100%">
				<hdiits:tr>
					<td align=left width="50%" class="Label3">&nbsp;&nbsp;&nbsp;<b>Scheme No </b></td>
					<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.schemeNo}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Demand No. </b></td>
					<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.dmndNo}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Major Head 2. </b></td>
					<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budMjrHd}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Sub-Major Head </b></td>
					<td width="50%" align=left class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budSubmjrHd}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Minor Head </b></td>
					<td width="50%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budMinHd}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Sub Head </b></td>
					<td width="50%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budSubHd}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td width="50%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Detail Head </b></td>
					<td width="50%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budDtlHd}'></c:out></td>
				</hdiits:tr>
			</table>
			</td>

			<td width="50%">
			<table width="100%" >
				<hdiits:tr>
					<td align="left" class="Label3" width="50%"><b>Amount in Rs.</b></td>
					<td width="50%" align="left"><b>:</b>&nbsp;<c:out value='${recptAmt}'></c:out></td>
				</hdiits:tr>
				<hdiits:tr>
					<td align=left class="Label3"><b>In Words</b></td>
					<td align=left> <b>:</b>&nbsp;<script type="text/javascript"> document.write(getAmountInWords('${recptAmt}')); </script> </td>
				</hdiits:tr>
				<hdiits:tr>
					<td align=left class="Label3"><b>Appropriation for <c:out value='${resValue.Appropriation}'/></b></td>
					<td align=left class="Label3"><b>:</b> <c:out value='${resValue.TrnBillRegister.grantAmount}'/> </td>
				</hdiits:tr>
				<hdiits:tr>
					<td align=left class="Label3"><b>Expenditure including this Bill</b></td>
					<td align="left"><b>:</b>&nbsp;<c:out value='${resValue.TotalExpenditure}'/></td>
				</hdiits:tr>
				<hdiits:tr>
					<td align=left class="Label3"><b>Balance</b></td>
					<td width="10%" align=left><b>:</b>&nbsp;<c:out value='${resValue.AvailableBalance}'/></td>
				</hdiits:tr>
			</table>
			</td>
		</tr>
	</table>

	<br />
	
	<table border="1" id="T2" bgcolor='white' cellspacing="0"
		cellpadding="0" width="100%">
		<tr class="TableHeaderBG">
			<td align=center width=5% class="Label3">Sr.No.</td>
			<td align=center width=20% class="Label3">Name of patient and relation-ship with Govt. Servant</td>
			<td align=center width=10% class="Label3">Name of Incumbent</td>
			<td align=center width=25% class="Label3">Period of Treatment from to</td>
			<td align=center width=20% class="Label3">Amount of Claim(in Rs.)</td>
			<td align=center width=20% class="Label3">Remarks</td>
		</tr>
			<c:forEach var="TrnMedblDtls" items="${resValue.BillDtls.TrnMedblDtls}" varStatus="No" > 
				<hdiits:tr>
					<td align="center"> <c:out value="${No.count}" /> </td>
					<td align="center"><c:out value='${TrnMedblDtls.ptntName}'></c:out>,&nbsp;<c:out value='${TrnMedblDtls.rltnshp}'></c:out></td>
					<td align=center ><c:out value='${resValue.BillDtls.TrnMedblHdr.empName}'></c:out></td>
					<td align=center><c:out value='${TrnMedblDtls.trtmtTime}'></c:out></td>
					<td align=center><c:out value='${TrnMedblDtls.claimAmt}'></c:out></td>
					<td align=center><c:out value='${TrnMedblDtls.remarks}'></c:out></td>
				</hdiits:tr>
			</c:forEach>
		</table>
	<br>

	<table border="0" bgcolor='white' cellspacing="0" cellpadding="0"
		width="100%">
		<hdiits:tr>
			<td class="Label3">
				Certified that I have satisfied myself that the amount included in bill drawn 1/2 months previous
				to this date with the exception of those detailed below(of which total amount has been refunded by
				deductions from this bill) have been disbursed to the Govt. servants therein named and their receipts
				taken in the office copy of the bill or in a separate acquittance roll.</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="right" class="Label3">Signature of Drawing officer&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</hdiits:tr>
	</table>

	<br></br><br></br>
				Details of Medical charges refunded(This intimation is not required to be filled in case of claim 
				of Central Officer)
	<br></br>

	<table border="1" id="T4" bgcolor='white' cellspacing="0" cellpadding="0" width="100%">
		<tr class="TableHeaderBG">
			<td align=center width=5% class="Label3">Sr.No.</td>
			<td align=center width=15% class="Label3">Section of Establishment</td>
			<td align=center width=20% class="Label3">Name of Incumbent</td>
			<td align=center width=30% class="Label3">Period of Treatment from to</td>
			<td align=center width=30% class="Label3">Amount of Claim</td>
		</tr>
		<c:forEach var="TrnMedblAprvdDtls" items="${resValue.BillDtls.TrnMedblAprvdDtls}"  varStatus="No"> 
			<hdiits:tr>
				<td align="center"> <c:out value="${No.count}" /> </td>
				<td align=center><c:out value='${TrnMedblAprvdDtls.sctnEstblshmnt}'></c:out></td>
				<td align=center ><c:out value='${resValue.BillDtls.TrnMedblHdr.empName}'></c:out></td>
				<td align=center><c:out value='${TrnMedblAprvdDtls.trtmtTime}'></c:out></td>
				<td align=center><c:out value='${TrnMedblAprvdDtls.claimAmt}'></c:out></td>
			</hdiits:tr>
		</c:forEach>
	</table>

	<br /><br />
	<table border="0" cols=2 bgcolor='white' cellspacing="0" cellpadding="0" width="100%">
		<hdiits:tr>
			<td align="left" width="50%" class="Label3"><b>Date</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDtls.TrnMedblHdr.billPassedDate}"/></td>

		</hdiits:tr>
		<hdiits:tr>
			<td width="50%">&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" width="50%" class="Label3"><b>Passed for Rs </b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:out value='${resValue.BillDtls.TrnMedblHdr.billPassedAmt}'></c:out></td>
			<td width="50%" align="left" class="Label3">Signature of Drawing Officer</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" width="50%" class="Label3"><b>Date</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDtls.TrnMedblHdr.billPassedDate}"/></td>
			<td width="50%" align="left" class="Label3">Signature of controling officer</td>
		</hdiits:tr>
	</table>

	<hr>
	<br /><br />

	<table cols=2 border=0 cellspacing="0" width="100%">
		<hdiits:tr>
			<td width="50%">&nbsp;</td>
			<td width="50%" class="Label3">Received Contents</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td width="50%">&nbsp;</td>
			<td width="50%" class="Label3">Signature of Controling Officer</td>
		</hdiits:tr>
	</table>
	<br />
	<hr>
	<br />
	<table border="0" cellspacing="0" cellpadding="0" bgcolor='white' width="100%">
		<tr>
			<td align="left" class="Label3">For Use In Treasury Office</td>
		</tr>
	</table>

	<table border="0" cols=3 cellspacing="0" cellpadding="0" bgcolor='white' width="100%">
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align=left class="Label3">Pay Rs.</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td align="left" class="Label3">In words</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td width="30%" class="Label3">Date</td>
			<td width="30%" class="Label3">Accounts&nbsp;</td>
			<td width="40%" class="Label3">Treasury/sub treasury officer Pay and Accounts Offficer</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
		</hdiits:tr>
	</table>
	<hr>
	<table border="0" cols=2 cellspacing="0" cellpadding="0" bgcolor='white' width="100%">
		<hdiits:tr>
			<td align=left class="Label3">Examined and entered in</td>
			<td align=left class="Label3">For Use in Audit Office</td>
		</hdiits:tr>
		<hdiits:tr>
			<td colspan="2">&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td class="Label3">Treasury Accounts on..................</td>
			<td align=left class="Label3">Admitted Rs</td>
		</hdiits:tr>
		<hdiits:tr>
			<td colspan=2>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
			<td align=left class="Label3">Objected to Rs:</td>
		</hdiits:tr>
		<hdiits:tr>
			<td colspan=2>&nbsp;</td>
		</hdiits:tr>
		<hdiits:tr>
			<td>&nbsp;</td>
			<td align=left class="Label3">Reason for objection:</td>
		</hdiits:tr>
		<hdiits:tr>
			<td colspan=2>&nbsp;</td>
		</hdiits:tr>
	</table>

	<br /><br />

	<table border="0" cellspacing="0" cellpadding="0" bgcolor='white' class="TableBorderLTRBN" width="100%">
		<hdiits:tr>
			<td align=left class="Label3">Accountant</td>
			<td align=left class="Label3">Treasury Officer.</td>
			<td align=left class="Label3">Auditor</td>
			<td align=left class="Label3">Superintendent</td>
			<td align=left class="Label3">Gazetted Officer
		</hdiits:tr>
	</table>
	<hr>
	<br> Direction for Note:<br />
	<br />Note:The bill should be supported by essential certificates,receipts and bills etc.
	 
</hdiits:form>
