<%@ include file="../../core/include.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="rcptObjHds" value="${resValue.rcptObjHds}" scope="request"> </c:set>
<c:set var="expObjHds" value="${resValue.expObjHds}" scope="request"></c:set>
<c:set var="recObjHds" value="${resValue.recObjHds}" scope="request"></c:set>

<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"> </script>

<hdiits:form name="tABillPrintableForm" validate="true" method="post" action="./ifms.htm?actionFlag=getRqstData">
	<hdiits:table width="100%">
	
		<hdiits:tr>
			<td align="Right" width="100%" colspan="3"><input type='button' id="printbtn" value="Print Bill" onclick="printBill()"></td>
		</hdiits:tr>
				
		<hdiits:tr>
			<td align="Center" width="100%" colspan="3"><font size=4><b>FORM G.T.R. NO - 35</b></font></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td align="Center" width="100%" colspan="3">(See Rule 169 and 186)</td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td align="right" width="100%" colspan="2"><b>Bill Register No.</b>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${resValue.BillDtls.TrnTablHdr.billNo}"></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td align="left" width="100%" colspan="3">Travelling Allowance Bill of the Establishment/Gazetted Goverment Servents.Office of the</td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td colspan=3><hr></td>
		</hdiits:tr>
	
	</hdiits:table>

	
	<table border=0 bgcolor='white' width=100%>
		<tr align="left">
			<td class="Label3"><b>Name of Treasury :</b></td>
			<td class="Label3">____________</td>
			<td colspan="8"></td>
		</tr>
		
		<tr align="left">
			<td class="Label3"><b>Bill Transit Reg. Sr. No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
			<td class="Label3"><b>Token No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
		</tr>
		
		<tr align="left">
			<td class="Label3"><b>Bill Transit Reg. Sr. No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
			<td class="Label3"><b>Token No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
		</tr>
		
		<tr align="left">
			<td class="Label3"><b>Bill Transit Reg. Sr. No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
			<td class="Label3"><b>Token No.</b></td>
			<td class="Label3">____________</td>
			<td class="Label3"><b>Date</b></td>
			<td class="Label3">____________</td>
			<td></td>
		</tr>
		
		<tr>
			<td colspan=10><hr></td>
		</tr>
		
	</table>

	
	<table width="100%" cols=4>
		<hdiits:tr>
			<td align="right" width="50%" class="Label3" colspan=2><b><u>COMPUTER INPUT DATA</u></b></td>
			<td>&nbsp;</td>
			<td align="right" width="25%" class="Label3" colspan=2>(To be filled in by Treasury)</td>
		</hdiits:tr>

		<hdiits:tr>
			<td colspan=4>&nbsp;</td>
		</hdiits:tr>

		<hdiits:tr>
			<td align="left" width="25%" class="Label3"><b>1. District</b></td>
			<td>&nbsp;</td>
			<td align="left" width="25%" class="Label3"><b>2. Month & Year</b></td>
			<td>&nbsp;</td>
		</hdiits:tr>

		<hdiits:tr>
			<td colspan=4>&nbsp;</td>
		</hdiits:tr>

		<hdiits:tr>
			<td width="25%">&nbsp;</td>
			<td>&nbsp;</td>
			<td align="left" width="25%" class="Label3"><b>3. Voucher No.</b></td>
			<td>&nbsp;</td>
		</hdiits:tr>

		<hdiits:tr>
			<td colspan=4>&nbsp;</td>
		</hdiits:tr>

		<hdiits:tr>
			<td colspan=4><hr></td>
		</hdiits:tr>
	
	</table>

	
	<table cellspacing="0" cellpadding="0" bgcolor='white'	class="TableBorderLTRBN" width="100%"  cols=2>
		<hdiits:tr>	
			<td width="50%">
			
				<table width="100%">
			
					<hdiits:tr>
						<td class="Label3" align=left width="50%"><b>4.&nbsp;Class of Expenditure :</b></td>
						<td align=left width="50%"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.ClassOfExp}'></c:out></td>
					</hdiits:tr>
				
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>

					<hdiits:tr>
						<td align=left width="50%" class="Label3"><b>5.&nbsp;Fund :</b></td>
						<td align=left width="50%"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.fund}'></c:out></td>
					</hdiits:tr>
			
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>
	
					<hdiits:tr>
						<td align=left width="50%" class="Label3"><b>6.&nbsp;Drawing Officer:</b></td>
						<td align=left width="50%"><b>:</b>&nbsp;&nbsp;<c:out	value='${resValue.TrnBillBudheadDtls.drawing}'></c:out></td>
					</hdiits:tr>
		
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>
		
					<hdiits:tr>
						<td width="50%" align=left class="Label3"><b>7.&nbsp;Demand No. :</b></td>
						<td width="50%" align=left><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.dmndNo}'></c:out></td>
					</hdiits:tr>
			
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>

					<hdiits:tr>
						<td width="50%" align=left class="Label3"><b>8:&nbsp;Type of Budget :</b></td>
						<td align=left width="50%" class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TypeOfBudget}'></c:out></td>
					</hdiits:tr>
		
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>

					<hdiits:tr>
						<td width="50%" align=left class="Label3"><b>9.&nbsp;Schme No. :</b></td>
						<td width="50%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.schemeNo}'></c:out></td>
					</hdiits:tr>

					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
					</hdiits:tr>
	
					<hdiits:tr>
						<td width="50%" align=left class="Label3"><b>10.&nbsp;Head Chargeble :</b></td>
						<td width="50%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.headChrg}'></c:out></td>
					</hdiits:tr>
		
					<hdiits:tr>
						<td colspan=2>&nbsp;</td>
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
	
	<br/>
	<br/>
	
	<table width="100%">
		
		<hdiits:tr>
			<td align=left width="20%" class="Label3">&nbsp;&nbsp;&nbsp;<b>Sector</b></td>
			<td width="80%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;</td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Demand No. </b></td>
			<td width="80%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.dmndNo}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Major Head </b></td>
			<td width="80%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budMjrHd}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Sub-Major Head </b></td>
			<td width="80%" align=left class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budSubmjrHd}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Minor Head </b></td>
			<td width="80%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budMinHd}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Sub Head </b></td>
			<td width="80%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;&nbsp;<c:out	value='${resValue.TrnBillBudheadDtls.budSubHd}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td width="20%" align=left class="Label3">&nbsp;&nbsp;&nbsp;<b>Detail Head </b></td>
			<td width="80%" align=left colspan=5 class="Label3"><b>:</b>&nbsp;&nbsp;<c:out value='${resValue.TrnBillBudheadDtls.budDtlHd}'></c:out></td>
		</hdiits:tr>
		
		<hdiits:tr>
			<td  colspan=5 ><hr></td>
		</hdiits:tr>
		
	</table>
	
	<center><u>For Use in A.G.'s OFFICE</u></center>
	
	<table bgcolor=white width=100%>

		<tr align="left">
			<td class="Label3" width=20%><b>Admitted for Rs.</b></td>
			<td class="Label3" width=80%><b>:</b>&nbsp;&nbsp;</td>
		</tr>

		<tr align="left">
			<td class="Label3" width=20%><b>Objection for Rs.</b></td>
			<td class="Label3" width=80%><b>:</b>&nbsp;&nbsp;</td>
		</tr>

		<tr align="left">
			<td class="Label3" width=20%><b>Reason for Objection</b></td>
			<td class="Label3" width=80%><b>:</b>&nbsp;&nbsp;</td>
		</tr>

	</table>


	<table id="TrnAllowanceDtl" border="1" cellpadding="1" cellspacing="0" align="center" class="TableBorderLTRBN" width="100%" bgcolor="white">
		<tr>
			<td colspan="20">
				<table border="1" cellpadding="2" cellspacing="0" align="center" class="TableBorderLTRBN" width="100%" bgcolor="white">
					<tr>
						<td colspan=20 align="center">
							<table width=100% border=0>
								<tr class="TableHeaderBG">
									<td colspan="20" align="center"><p align="center">Traveling Allowance Bill of the Establishment/Gazetted</p></td>
								</tr>
							</table>
						</td>
					</tr>
				
					<tr class="TableHeaderBG">
						<td colspan=20>
							<table width=100% border=0>
								<tr class="TableHeaderBG">
									<td colspan="6" class="Label3"><b>Name</b>&nbsp;:&nbsp;<c:out value="${resValue.BillDtls.TrnTablHdr.empName}"></c:out></td>
									<td colspan="10" class="Label3"><b>Designation</b>&nbsp;:&nbsp;<c:out value="${resValue.BillDtls.TrnTablHdr.empDesgn}"></c:out></td>
									<td colspan="4" class="Label3"><b>Basic Pay</b>&nbsp;:&nbsp;<c:out value="${resValue.BillDtls.TrnTablHdr.empPay}"></c:out></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr class="TableHeaderBG">
						<td align=left valign="top" class="Label3" colspan="6"><center>Particulars of journeys and halts</center></td>
						<td align=left valign="top" class="Label3" rowspan="3"><center>Kind of Journey<br>7</center></td>
						<td align=left valign="top" class="Label3" rowspan="2" colspan="3"><center>Fare</center></td>
						<td align=left valign="top" class="Label3" colspan="3">Distance	travelled by road by trolly</td>
						<td align=left valign="top" class="Label3" rowspan="3"><center>No.of days for which daily allowance is claimed<br>14</center></td>
						<td align=left valign="top" class="Label3" rowspan="3"><center>Purpose of journey<br>15</center></td>
						<td align=center valign="center" class="Label3" colspan="2"	rowspan="3"><center>Total<br>16</center></td>
						<td align=center valign="center" class="Label3" colspan="1"	rowspan="3"><center>Remarks<br>17</center></td>
					</tr>

					<tr class="TableHeaderBG">
						<td align=left valign="top" class="Label3" colspan="3"><center>Departure</center></td>
						<td align=left valign="top" class="Label3" colspan="3"><center>Arrival</center></td>
						<td align=left valign="top" class="Label3" colspan="2">For which mileage is admissible</td>
						<td align=left valign="top" class="Label3" rowspan="2"><center>For Which daily allowance is admissible<br>13</center></td>
					</tr>

					<tr class="TableHeaderBG">
						<td align=left valign="top" class="Label3"><center>Station<br>1</center></td>
						<td align=left valign="top" class="Label3"><center>Date<br>2</center></td>
						<td align=left valign="top" class="Label3"><center>Hour<br>3</center></td>
						<td align=left valign="top" class="Label3"><center>Station<br>4</center></td>
						<td align=left valign="top" class="Label3"><center>Date<br>5</center></td>
						<td align=left valign="top" class="Label3"><center>Hour<br>6</center></td>
						<td align=left valign="top" class="Label3"><center>Class <br></center>8</td>
						<td align=left valign="top" class="Label3"><center>No.of fares<br>9</center></td>
						<td align=left valign="top" class="Label3"><center>Amount<br></center>10</td>
						<td align=left valign="top" class="Label3"><center>At ordinary rates<br></center>11</td>
						<td align=left valign="top" class="Label3"><center>At other rates<br>12</center></td>
					</tr>
								
					<c:forEach var="TrnTablTrvlDtls" items="${resultObj.resultValue.BillDtls.TrnTablTrvlDtlsLst}">
	
						<hdiits:tr>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.depStation}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${TrnTablTrvlDtls.depDate}"/></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.depHour}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.arrStation}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${TrnTablTrvlDtls.arrDate}"/></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.arrHour}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.trvlMode}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.fareClass}"></c:out></td>
							<td align=left valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.fareNumber}"></c:out></td>
							<td align="right" valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.fareAmt}"></c:out></td>
							<td align="right" valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.admsblOrdRate}"></c:out></td>
							<td align="right" valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.admsblOthRate}"></c:out></td>
							<td align="right" valign="top" width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.admsblDa}"></c:out></td>
							<td align=left valign="top" width="4%" class="Label3"><c:out value="${TrnTablTrvlDtls.numOfDaysClmed}"></c:out></td>
							<td align=left valign="top" width="4%" class="Label3"><c:out value="${TrnTablTrvlDtls.purpose}"></c:out></td>
							<td align="right"  valign="top" colspan=2 width="8%" class="Label3"><c:out value="${TrnTablTrvlDtls.totalAmt}"></c:out></td>
							<td align=left valign="top" width="10%" class="Label3" class=Label3><c:out value="${TrnTablTrvlDtls.remarks}"></c:out></td>
						</hdiits:tr>
						<c:set var="lTA" value="${TrnTablTrvlDtls.fareAmt + lTA}"></c:set>
						<c:set var="lDA" value="${(TrnTablTrvlDtls.admsblDa * TrnTablTrvlDtls.numOfDaysClmed) + lDA}"></c:set>
						<c:set var="lGrandAmt" value="${TrnTablTrvlDtls.totalAmt + GrandAmt}"></c:set>
					</c:forEach>

					<tr>
						<td Colspan=17 height="10"></td>					
					</tr>
					
					<tr>
						<td colspan="13"></td>
						<td colspan="4" align="left"><b>TA : &nbsp;&nbsp; </b> <c:out value="${lTA}"></c:out> </td>
					</tr>
					
					<tr>
						<td colspan="13"></td>
						<td colspan="4" align="left"><b> DA : &nbsp;&nbsp; </b> <c:out value="${lDA}"></c:out> </td>
					</tr>
				
					<tr>
						<td colspan="13"></td>
						<td colspan="4" align="left"><b>Grand total : &nbsp;&nbsp; </b> <c:out value="${lGrandAmt}"></c:out> </td>
					</tr>
				</table>
			</td>
		</tr>
	</table>


	<table border="1" width="100%" height="585" bgcolor="white">
		<tr align="left">
			<td width="50%" class="label3">
				<table width="100%">
					<tr>
						<td>
	
							Certified that the facts mentioned in the T.A Bill are true and	that the claims made in this 
							T.A Bill are correct , on the basis of the assumptions referred to in G.R F.D No. 
							TJN-1071/2511/GH, dated 15 June,1971 as amended from time to time
	
							<p align=right>(Government Servant)</p>
	
							<p><b>Passed for Rs. (In Words) </b></p>
	
							<input type="hidden" id="amt" value="${resValue.BillDtls.TrnTablAmtDtls.billPassedAmt}" >
							<font class=Label3><label id="amtInWord_1" ></label></font>
						
							<script type="text/javascript">
								var amt = Number(document.getElementById('amt').value);
								var amtInWord = document.getElementById('amtInWord_1');
								var wordData;
								
								if(amt != null && amt!= 0){
									wordData = getAmountInWords(amt);
									amtInWord.innerHTML = wordData;
								}								
							</script>
							<p><b>Dated</b><font class=Label3><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDtls.TrnTablAmtDtls.billPassedDate}"/></font></p>
							<p align=right>Head of Office</p>
							<p>&nbsp;</p>
							<p align=right>(Controlling Officer).</p>
							<p>&nbsp;</p>
							<br /><br /><br />
							<p><b>Pay Rupees. (In words and Figures)</b> </p>
							<font class=Label3><script type="text/javascript"> document.write(getAmountInWords('${recptAmt}')); </script> </font>
							<p></p>
							<font class=Label3><c:out value='${recptAmt}'></c:out></font>
							<p></p>							
							<p><b>Dated</b> <font class=Label3><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDtls.TrnTablAmtDtls.billPassedDate}"/></font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Treasury officer/P.A.O/Sub-Treasury Officer.</p><p>From&nbsp;&nbsp;&nbsp;<font class=Label3></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Sub-Treasury</p>
							<p>Examined and entered &nbsp;</p>
							<p>Accountant</p>
							<p>Dated :<font class=Label3><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.BillDtls.TrnTablAmtDtls.billPassedDate}"/></font></p>
						</td>
					</tr>
					<tr>
						<hr>
					</tr>

					<tr>
						<td>*To be filled up when payable from sub-Treasury</td>
					</tr>
				</table>
			</td>
	
			<td width="50%" align="top">
				<table border=0 width="100%" bgcolor=white>

					<tr>
						<td colspan="1"></td>
						<td align=left><b>Rs.</b></td>
					</tr>

					<tr>
						<td class="label3"><b>Railway and Steamer fare (Col 10)</b> </td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.rlwStmFare}" ></c:out></td>
					</tr>

					<tr>
						<td class="label3"><b>Road Mileage Kmts, by road at <c:out value="${resValue.BillDtls.TrnTablAmtDtls.rdChrgsPsperkm}" ></c:out> paise per 1 Kms. (Cols ,.11 and 12 )</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.rdTrlFare}" ></c:out></td>
					</tr>

					<tr>
						<td class="label3"><b>Day for which daily Allowance is claimed	(Col.14)</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.ttlDaClmed}" ></c:out></td>
					</tr>

					<tr>
						<td class="label3"><b>Incidental Expenses</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.incdntlExp}" ></c:out></td>
					</tr>

					<tr>
						<td></td>
						<td><hr></td>
					</tr>

					<tr>
						<td class="label3"><b>Total</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.grossTotal}" ></c:out></td>
					</tr>

					<tr>
						<td></td>
						<td><hr></td>
					</tr>

					<tr>
						<td colspan=2 class="label3"><b><u>Deduct</u></b></td>
					</tr>

					<tr></tr>

					<tr>
						<td class="label3"><b>1. Amount of T.A. advance on transfer/tour</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.dedTaAdv}" ></c:out></td>
					</tr>

					<tr>
						<td class="label3"><b>2. Single/Double P.T.A. for(<c:out value="${resValue.BillDtls.TrnTablAmtDtls.dedPtaDays}" ></c:out>)days G.C.S.R.20, 21, 22, 24</b></td>
						<td class="Label3" align="right"><c:out value="${resValue.BillDtls.TrnTablAmtDtls.dedPta}" ></c:out></td>
					</tr>

					<tr>
						<td></td>
						<td><hr></td>
					</tr>

					<tr>
						<td class="label3"><b>Net Claimed </b></td>
						<td class="Label3" align="right" ><b><c:out value="${resValue.BillDtls.TrnTablAmtDtls.netClaimed}" ></c:out></b></td>
					</tr>

					<tr>
						<td></td>
						<td><hr></td>
					</tr>

					<tr>
						<td colspan=2><hr></td>
					</tr>

					<tr>
						<td colspan=2 class="label3">(For the use of Gazetted Government Servants only)</td>
					</tr>

					<tr>
						<td colspan=2><hr></td>
					</tr>

					<tr>
						<td></td>
						<td align=right class="label3">Received Payment</td>
					</tr>

					<tr>
						<td class="label3"><b>Signature of officer who traveled</b></td>
						<td></td>
					</tr>

					<tr></tr>

					<tr></tr>

					<tr>
						<td class="label3">Office Memo</td>
						<td class="label3"><b>Cheque to be made payable to the Order of</b> <br>(<c:out value="${resValue.BillDtls.TrnTablAmtDtls.chqOrder}" ></c:out>)</td>
					</tr>

					<tr>
						<td colspan=2><hr></td>
					</tr>

					<tr>
						<td align="left" class="Label3"><b>Amount in Rs.</b></td>
						<td align="left"><b>:</b>&nbsp;<c:out value='${recptAmt}'></c:out></td>
					</tr>

					<tr>
						<td align=left class="Label3"><b>In Words</b></td>
						<td align=left> <b>:</b>&nbsp;<script type="text/javascript"> document.write(getAmountInWords('${recptAmt}')); </script> </td>
					</tr>

					<tr>
						<td align=left class="Label3"><b>Allotment for <c:out value='${resValue.Appropriation}'/></b></td>
						<td align=left class="Label3"><b>:</b> <c:out value='${resValue.TrnBillRegister.grantAmount}'/> </td>
					</tr>

					<tr>
						<td align=left class="Label3"><b>Expenditure including this Bill</b></td>
						<td align="left"><b>:</b>&nbsp;<c:out value='${resValue.TotalExpenditure}'/></td>
					</tr>

					<tr>
						<td align=left class="Label3"><b>Balance</b></td>
						<td align=left><b>:</b>&nbsp;<c:out value='${resValue.AvailableBalance}'/></td>
					</tr>

					<tr></tr>

					<tr></tr>

					<tr>
						<td class="label3">Passed for Ruppes.</td>
						<td class="label3">Controlling Officer</td>
					</tr>

				</table>
			</td>
		</tr>
	</table>

	<br />

</hdiits:form>
