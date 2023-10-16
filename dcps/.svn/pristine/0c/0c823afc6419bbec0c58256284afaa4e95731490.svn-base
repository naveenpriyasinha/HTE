<script>
function calcAdvBalanceOut()
{
var advBalOut=0;
var advCurrent="${hrGpfBalanceDtls.advCurrYr}";
var refundCurrent="${hrGpfBalanceDtls.refundCurrYr}";
var advOut="${hrGpfBalanceDtls.advBalanceOutstanding}";
advBalOut=(advCurrent*1)-(refundCurrent*1)+(advOut*1);
return advBalOut;
}

function decimalPoint(val)
{
var no=val;
return (no.toFixed(2)); 
}

</script>


<hdiits:fieldGroup titleCaptionId="GPF.BalDtls" bundle="${gpfLables}"  expandable="true" collapseOnLoad="true">


<table  width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse">
	<tr> 
		<th align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;"> 
		<hdiits:caption captionid="GPF.SrNo" bundle="${gpfLables}"/>
		</th>
		<th align="left"   style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;">   
		<hdiits:caption captionid="GPF.balCredit" bundle="${gpfLables}"/>
	
		<fmt:formatDate var="finYrStartPrint"  pattern="dd/MM/yyyy" value="${finYrStart}" type="date"/>
		<fmt:formatDate var="datePrint"  pattern="dd/MM/yyyy" value="${date}" type="date"/>
		${finYrStartPrint}-${datePrint}
		</th>
		<th align="right" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;"> 
		<hdiits:caption captionid="GPF.Rs" bundle="${gpfLables}"/>
		</th>
	</tr>

	<tr>
		<td width="10%" align="center"> A </td>
		<td width="70%"> 
		<hdiits:caption captionid="GPF.openingBal" bundle="${gpfLables}"/>
		</td>
		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.closingBalance}));</script></td>
	</tr>
	
	<tr>
		<td width="10%" align="center"> B </td>
		<td width="70%" > 
		<hdiits:caption captionid="GPF.sub" bundle="${gpfLables}"/>
		</td>
		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.subRate}));</script></td>
	</tr>
	
	<tr>
	<td width="10%" align="center"> C </td>
		<td>
		<hdiits:caption captionid="GPF.withd" bundle="${gpfLables}"/>
		</td>
		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.withCurrYr}));</script></td>
	</tr>
	
	<tr>
	<td width="10%" align="center"> D </td>
		<td>
		<hdiits:caption captionid="GPF.adv" bundle="${gpfLables}"/>
		</td>
		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.advCurrYr}));</script></td>
	</tr>
	<tr>
	<td width="10%" align="center"> E </td>
		<td> 
		<hdiits:caption captionid="GPF.refunds" bundle="${gpfLables}"/>
		</td>
		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.refundCurrYr}));</script></td>
	</tr>
	<tr> 
	<td width="10%" align="center"> F </td>
		<td><b> 
		<hdiits:caption captionid="GPF.netBal" bundle="${gpfLables}"/>
			<c:out value="${datePrint}" /> (A)+(B)-(C)-(D)+(E) </b> </td>

  		<td  align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.netBalance}));</script> </td>
	</tr>
	<tr>
	<td width="10%" align="center"> G </td>
			<td>
			<hdiits:caption captionid="GPF.advBalOut" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(calcAdvBalanceOut()));</script></td>
	</tr>
	
	
		
</table>

</hdiits:fieldGroup>

 <hdiits:fieldGroup titleCaptionId="GPF.viewPrevFinStmt"  id="advHst" bundle="${gpfLables}" expandable="true" collapseOnLoad="true">
		

 
<table  width="100%" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse" id="PrevFinDtls" >

	<tr> 
		<th align="center" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;"> 
		<hdiits:caption captionid="GPF.SrNo" bundle="${gpfLables}"/>
		</th>
		<th align="left"   style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;"> 
		<hdiits:caption captionid="GPF.balDtlYr" bundle="${gpfLables}"/>
		  ${hrGpfBalanceDtls.financialYear} </th>
		<th align="right" style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C9DFFF;"> 
		<hdiits:caption captionid="GPF.Rs" bundle="${gpfLables}"/>
		</th>
	</tr>
		
	<tr>
			<td width="10%" align="center"> A </td>
			<td>
			<hdiits:caption captionid="GPF.totCreditAmt" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.creditAmt}));</script></td>
	</tr>
	
	<tr>
			<td width="10%" align="center"> B </td>
			<td>
			<hdiits:caption captionid="GPF.totWithdrawals" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.withdrawal}));</script></td>
	</tr>
	
	<tr>
			<td width="10%" align="center"> C </td>
			<td>
			<hdiits:caption captionid="GPF.advBalOut" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.advBalanceOutstanding}));</script></td>
	</tr>
	
	<tr>
			<td width="10%" align="center"> D </td>
			<td>
			<hdiits:caption captionid="GPF.totRef" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.refundAmt}));</script></td>
	</tr>
	
	<tr>
			<td width="10%" align="center"> E </td>
			<td>
			<hdiits:caption captionid="GPF.higherPayAmt" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.higherPayAmt}));</script></td>
	</tr>
	
	<tr>
			<td width="10%" align="center"> F </td>
			<td>
			<hdiits:caption captionid="GPF.inter" bundle="${gpfLables}"/>
			</td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.interest}));</script></td>
	</tr>
	
	<tr>  
			<td width="10%" align="center"> G </td>
			<td width="70%"><b>
			<hdiits:caption captionid="GPF.closingBal" bundle="${gpfLables}"/>
			</b></td>
     		<td align="right"><script>document.write(decimalPoint(${hrGpfBalanceDtls.closingBalance}));</script></td>
	</tr>
</table>

	</hdiits:fieldGroup>
<br>
<table width="50%">
	<tr>
	    <td width="25%">
			<b>
			<hdiits:caption captionid="GPF.accNo" bundle="${gpfLables}"/>
			</b>
			<br>
		</td>
		<td width="25%">
			 ${hrGpfBalanceDtls.gpfAccNo}<br>
		</td>
	</tr>
</table>


<script>
function showFinStmt()
{
	document.getElementById("showFinBalDtls").style.display='none';
	document.getElementById("hideFinBalDtls").style.display='block';
	document.getElementById("PrevFinDtls").style.display='block';	
}

function hideFinStmt()
{
	document.getElementById("showFinBalDtls").style.display='block';
	document.getElementById("hideFinBalDtls").style.display='none';
	document.getElementById("PrevFinDtls").style.display='none';	
}
</script>



 