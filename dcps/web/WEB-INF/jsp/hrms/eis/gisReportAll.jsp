
<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import="java.util.Map"%>
<fmt:setLocale value="${sessionScope.locale}" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="compoCount" value="${resValue.compoCount}">
</c:set>
<c:set var="premCount" value="${resValue.premCount}">
</c:set>
<c:set var="compoAmt" value="${resValue.compoAmt}">
</c:set>
<c:set var="premAmt" value="${resValue.premAmt}">
</c:set>
<c:set var="gisArr" value="${resValue.gisArr}">
</c:set>
<c:set var="month" value="${resValue.month}">
</c:set>
<c:set var="year" value="${resValue.year}">
</c:set>
<head>
<link rel="stylesheet" type="text/css" href="http://cdn.webrupee.com/font">
</head>
<c:set var="totArr" value="${resValue.totArr}">
</c:set>
<c:set var="compoCountAll" value="${resValue.compoCountAll}">
</c:set>
<c:set var="premCountAll" value="${resValue.premCountAll}">
</c:set>
<c:set var="totCompo" value="${resValue.totCompo}">
</c:set>
<c:set var="totPrem" value="${resValue.totPrem}">
</c:set>
<c:set var="totCompoAll" value="${resValue.totCompoAll}">
</c:set>
<c:set var="totPremAll" value="${resValue.totPremAll}">
</c:set>
<c:set var="netPayInWords" value="${resValue.netPayInWords}">
</c:set>
<c:set var="officenamewithddocode"
	value="${resValue.officenamewithddocode}">
</c:set>
<c:set var="officename" value="${resValue.officename}">
</c:set>

<c:set var="dsgnName" value="${resValue.dsgnName}">
</c:set>

<c:set var="treasury" value="${resValue.treasury}">
</c:set>
<c:set var="VerificationDate" value="${resValue.VerificationDate}"></c:set>
<c:set var="Dated" value="${resValue.Dated}"></c:set>

<c:set var="groupACompositeamount" value="${resValue.groupACompositeamount}"></c:set>
<c:set var="groupBCompositeamount" value="${resValue.groupBCompositeamount}"></c:set>
<c:set var="groupCCompositeamount" value="${resValue.groupCCompositeamount}"></c:set>
<c:set var="groupDCompositeamount" value="${resValue.groupDCompositeamount}"></c:set>
<c:set var="groupBnGzCompositeamount" value="${resValue.groupBnGzCompositeamount}"></c:set>
<c:set var="totalCompositeAmount" value="${resValue.totalCompositeAmount}"></c:set>


<c:set var="groupAPremiumamount" value="${resValue.groupAPremiumamount}"></c:set>
<c:set var="groupBPremiumamount" value="${resValue.groupBPremiumamount}"></c:set>
<c:set var="groupCPremiumamount" value="${resValue.groupCPremiumamount}"></c:set>
<c:set var="groupDPremiumamount" value="${resValue.groupDPremiumamount}"></c:set>
<c:set var="groupBnGzPremiumamount" value="${resValue.groupBnGzPremiumamount}"></c:set>
<c:set var="totalPremiumAmount" value="${resValue.totalPremiumAmount}"></c:set> 
<c:set var="majorCode" value="${resValue.majorCode}"></c:set>

<c:set var="gisName" value="${resValue.gisName}"></c:set>
<script>
	function printReport() 
	{
		
		    /* document.getElementById('Print').style.visibility='hidden'; // hide
			document.getElementById('Back').style.visibility='hidden'; // hide   
			document.getElementById('Save').style.visibility='hidden'; // hide   
			window.print();
			document.getElementById('Print').style.visibility='visible'; // show 
			document.getElementById('Back').style.visibility='visible'; // show 
			document.getElementById('Save').style.visibility='visible'; // show  */
			
		window.print();
		 document.getElementById('Print').style.visibility = 'visible';

	
	}

	function GoToPage() 
	{
		document.ViewOuterFirst.action = "ifms.htm?actionFlag=getBillMonthYearData&nextPage=gis";
		document.ViewOuterFirst.submit();

	}

	 function doSaveAs()
	{
		   /* document.getElementById('Print').style.visibility='hidden'; // hide
			document.getElementById('Back').style.visibility='hidden'; // hide   
			document.getElementById('Save').style.visibility='hidden'; // hide
			
		if (document.execCommand)
	 	{
			     
				document.execCommand("SaveAs");
				document.getElementById('Print').style.visibility='visible'; // show 
				document.getElementById('Back').style.visibility='visible'; // show 
				document.getElementById('Save').style.visibility='visible'; // show 
		  
			   		   
		}
		else
		{
		alert("Save-feature available only in Internet Exlorer 5.x.")
		} */
		
		{
			 var oPrntWin = window.open("","_blank","width=1,height=1,left=1,top=1,menubar=yes,toolbar=yes,resizable=yes,location=no,scrollbars=yes");
			   oPrntWin.document.open();
			   var printcontent = document.getElementById("ViewOuterFirst").innerHTML;
			   oPrntWin.document.write(printcontent);
			   //oPrntWin .document.execCommand("SaveAs",true,"C:\\abc.html");
			   oPrntWin .document.execCommand("SaveAs");
			  oPrntWin.document.close();
		}
	} 
			
</script>
<style>

table.maintb2 td, table.maintb2 tr {
    border: 1px solid #000;
    border-collapse: collapse !important;
}

table.maintb2 table * {
    border: none !important;
}

 @media print {
     .scrollablediv {
         width: 100%;
         height: 100% !important;
    }
     div#content * {
         font-size: 8px;
         padding: 0.5px;
    }
     div#pageContent {
         overflow-x: auto;
    }
     div#footer, input#btnprintReport, input#btnExporttoExcel, div#header, div#nav, div#currentApplication,span.pagelinks,span.pagebanner,input#btnsaveReport,.hidetb {
         display: none !important;
    }
     div#content * {
         word-break: break-all;
    }
   .scrollablediv {
    overflow: visible !important;
}
div#content {
    overflow: hidden;
    width: 98% !IMPORTANT;
}
}

</style>



<table width="100%" align="center" cellspacing="0">
	<tr align="center">
		<td>
		<table cellspacing="0">
			<tr align="center">
				<td align="center"><b>Schedule For Recovery of <c:out value="${gisName}"></c:out></b></td>
			</tr>
			<tr align="center">
				<td align="center"><b>From Major Head <c:out value="${majorCode}"></c:out></b></td>
			</tr>

		</table>
		</td>
	</tr>


	<tr>
		<td align="left">For the Month of <c:out value="${month}"></c:out>
		&nbsp;&nbsp;<c:out value="${year}"></c:out></td>
	</tr>

	<tr>
		<td>
		<table width="100%" cellspacing="0">
			<tr>
				<td align="left">Name of the Office : <c:out
					value="${officenamewithddocode}" /></td>
				<td align="right">Treasury : <c:out value="${treasury}" /></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" cellspacing="0" cellpadding="0" class="maintb2">
			<tr>
				<td align="center" width="5%"
					style="border-bottom: solid; border-left: solid; border-top: solid; border-right: solid; border-color: black; border-width: 1px">SrNo.</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-top: solid; border-right: solid; border-color: black; border-width: 1px">Particular
				Of Group</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">

					<tr>
						<td width=" 100%" colspan="3"
							style="border-bottom: solid; border-top: solid; border-right: solid; border-color: black; border-width: 1px"
							align="center">Recovery made at Composite Rate</td>
					</tr>
					<tr align="center">
						<td width="30%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">No.
						of Emp</td>
						<td width="30%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">Rate (<Font face='Rupee Foradian'>`</Font>) </td>
						<td width="40%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">Amount (<Font face='Rupee Foradian'>`</Font>) </td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">
					<tr>
						<td width="100%" colspan="3"
							style="border-bottom: solid; border-right: solid; border-top: solid; border-color: black; border-width: 1px"
							align="center">Recovery made at Premium Rate</td>
					</tr>
					<tr align="center">
						<td width="30%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">No.
						of Emp</td>
						<td width="30%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">Rate (<Font face='Rupee Foradian'>`</Font>)</td>
						<td width="40%"
							style="border-bottom: solid; border-right: solid; border-color: black; border-width: 1px">Amount (<Font face='Rupee Foradian'>`</Font>)</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-right: solid; border-top: solid; border-color: black; border-width: 1px">Arrears (<Font face='Rupee Foradian'>`</Font>)</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-right: solid; border-top: solid; border-color: black; border-width: 1px">Total
				Recovery (<Font face='Rupee Foradian'>`</Font>)</td>
			</tr>


			<tr align="center">
				<td align="center" width="5%">1</td>
				<td align="center" width="15%">Group A</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">


					<tr>
						<td width="30%" align="center">${compoCount[0]}</td>
						<td width="30%" align="right">${compoAmt[0]}</td>
						<td width="40%" align="center">${groupACompositeamount}</td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">

					<tr align="center">
						<td width="30%">${premCount[0]}</td>
						<td width="30%">${premAmt[0]}</td>
						<td width="40%">${groupAPremiumamount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%">${gisArr[0]}</td>
				<td align="center" width="15%">${groupACompositeamount +groupAPremiumamount +
				gisArr[0]}</td>
			</tr>

			<tr align="center">
				<td align="center" width="5%">2</td>
				<td align="center" width="15%">Group B</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">


					<tr>
						<td width="30%" align="center">${compoCount[1]}</td>
						<td width="30%" align="right">${compoAmt[1]}</td>
						<td width="40%" align="center">${groupBCompositeamount}</td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">

					<tr align="center">
						<td width="30%">${premCount[1]}</td>
						<td width="30%">${premAmt[1]}</td>
						<td width="40%">${groupBPremiumamount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%">${gisArr[1]}</td>
				<td align="center" width="15%">${groupBCompositeamount + groupBPremiumamount +
				gisArr[1]}</td>
			</tr>

			<tr align="center">
				<td align="center" width="5%">3</td>
				<td align="center" width="15%">Group B nGz</td>
				<td align="right" width="25%">
				<table width="100%" cellspacing="0">


					<tr>
						<td width="30%" align="center">${compoCount[2]}</td>
						<td width="30%" align="right">${compoAmt[2]}</td>
						<td width="40%" align="center">${groupBnGzCompositeamount}</td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">

					<tr align="center">
						<td width="30%">${premCount[2]}</td>
						<td width="30%">${premAmt[2]}</td>
						<td width="40%">${groupBnGzPremiumamount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%">${gisArr[2]}</td>
				<td align="center" width="15%">${groupBnGzCompositeamount + groupBnGzPremiumamount +
				gisArr[2]}</td>
			</tr>

			<tr align="center">
				<td align="center" width="5%">4</td>
				<td align="center" width="15%">Group C</td>
				<td align="right" width="25%">
				<table width="100%" cellspacing="0">


					<tr>
						<td width="30%" align="center">${compoCount[3]}</td>
						<td width="30%" align="right">${compoAmt[3]}</td>
						<td width="40%" align="center">${groupCCompositeamount}</td>
					</tr>
				</table>
				</td>
				<td align="right" width="25%">
				<table width="100%" cellspacing="0">

					<tr align="right">
						<td align="center" width="30%">${premCount[3]}</td>
						<td align="center" width="30%">${premAmt[3]}</td>
						<td align="center" width="40%">${groupCPremiumamount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%">${gisArr[3]}</td>
				<td align="center" width="15%">${groupCCompositeamount + groupCPremiumamount +
				gisArr[3]}</td>
			</tr>

			<tr align="center">
				<td align="center" width="5%">5</td>
				<td align="center" width="15%">Group D</td>
				<td align="right" width="25%">
				<table width="100%" cellspacing="0">


					<tr>
						<td width="30%" align="center">${compoCount[4]}</td>
						<td width="30%" align="right">${compoAmt[4]}</td>
						<td width="40%" align="center">${groupDCompositeamount}</td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%">
				<table width="100%" cellspacing="0">

					<tr align="center">
						<td width="30%">${premCount[4]}</td>
						<td width="30%">${premAmt[4]}</td>
						<td width="40%">${groupDPremiumamount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%">${gisArr[4]}</td>
				<td align="center" width="15%">${groupDCompositeamount + groupDPremiumamount +
				gisArr[4]}</td>
			</tr>

			<tr>

				<td align="left" width="5%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">Total (<Font face='Rupee Foradian'>`</Font>)</td>
				<td align="right" width="25%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">
				<table width="100%" style>


					<tr align="center">
						<td width="30%">${compoCountAll }</td>
						<td width="30%"></td>
						<td width="40%">${totalCompositeAmount}</td>
					</tr>
				</table>
				</td>
				<td align="left" width="25%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">
				<table width="100%">

					<tr align="center">
						<td width="30%">${premCountAll}</td>
						<td width="30%"></td>
						<td width="40%">${totalPremiumAmount}</td>
					</tr>
				</table>
				</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">${totArr}</td>
				<td align="center" width="15%"
					style="border-bottom: solid; border-top: solid; border-color: black; border-width: 1px">${totalCompositeAmount+totalPremiumAmount+totArr}
				</td>
			</tr>


		</table>
	</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>Total Deduction In Words (<Font face='Rupee Foradian'>`</Font>): <c:out value="${netPayInWords}" /> Only.</td>
	</tr>


	<tr>
		<td>

		<table width="100%">
			<tr>
				<td align="center">CERTIFICATE</td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">1.Certified that total Recoveries shown in
				column (10) above agree with the amount actually recovered and shown
				in the body of the bill.</td>
			</tr>
			<tr>
				<td align="left">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">Dated: <c:out value="${resValue.Dated}"></c:out>
				</td>
			</tr>
			<tr>
				<td align="left">&nbsp;</td>
			</tr>
			<tr>
				<td align="right"><c:out value="${dsgnName}" /></td>
			</tr>
			<tr>
				<td align="right"><c:out value="${officename}" /></td>
			</tr>

			<tr>
				<td align="left">Portion for Treasury Office<br>
				Treasury Voucher No. and Date<br>
				Challan no. and Date</td>
			</tr>
			<tr>
				<td align="right">Treasury Officer / Pay and Accounts Officer</td>
			</tr>
			<tr>
				<td align="left">VERIFICATION TIME:<c:out
					value="${resValue.VerificationDate}"></c:out></td>
			</tr>
		</table>


		</td>
	</tr>




	<tr align="center">
		<hdiits:form name="ViewOuterFirst" id="ViewOuterFirst" validate="true" method="POST"
			action="" encType="multipart/form-data">



			<td align="center"><br></br>
			<input name="Back" type="button" Value="Back" onclick=GoToPage();></input>&nbsp;
			<input name="Print" value="Print" type="button" id="printButton" onclick=printReport();></input>
			&nbsp;
		    <input name="Save" value="Save" type="button" id="saveButton" onclick=doSaveAs();></input> 
			
			</td>
			
		</hdiits:form>

	</tr>

</table>

<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>