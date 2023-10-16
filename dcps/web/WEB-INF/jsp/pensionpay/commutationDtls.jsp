<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
<script>
function chckGenerateBill(object)
{
	var radioValue=object.value;
	if(radioValue=="Y")
	{


		document.getElementById("trGenBillCPONo").style.display="none";
		document.getElementById("trGenBillCPODate").style.display="none";
		document.getElementById("trGenBillCPOAmnt").style.display="none";
		document.getElementById("btnViewBill").style.display="block";
		

	}
	if(radioValue=="N")
	{
		document.getElementById("btnViewBill").style.display="none";
		document.getElementById("trGenBillCPONo").style.display="block";
		document.getElementById("trGenBillCPODate").style.display="block";
		document.getElementById("trGenBillCPOAmnt").style.display="block";
	
	}

}
function chckMedCertiRcvd(object)
{
	var radioValue=object.value;
	if(radioValue=="Y")
	{

		document.getElementById("trNoMedCertRcvdCPONo").style.display="block";
		document.getElementById("trNoMedCertRcvdCPODate").style.display="block";
		document.getElementById("trNoMedCertRcvdCPOAmnt").style.display="block";
		
	}
	if(radioValue=="N")
	{
		document.getElementById("trNoMedCertRcvdCPONo").style.display="none";
		document.getElementById("trNoMedCertRcvdCPODate").style.display="none";
		document.getElementById("trNoMedCertRcvdCPOAmnt").style.display="none";
		
	
	}
	
}
</script>

<hdiits:form name="CommutationDtls" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
	<legend>
		<fmt:message key="COMMUTATIONDTLS" bundle="${pensionLabels}"></fmt:message> 
	</legend>
	<table>
	<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.CPONO"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtCpoNo'
				id="txtCpoNo" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.DATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtDateCommu'
				id="txtDateCommu" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.AMOUNT"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtAmount'
				id="txtAmount" style="text-align: left" /></td>	
					
	</tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	
	<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.MEDCERTIRCVD"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="50%" align="left">
				<input type="radio" name="radioMediCertiRcvd" id="radioMediCertiRcvd" value="Y" onclick="chckMedCertiRcvd(this)"> Yes
				<input type="radio" name="radioMediCertiRcvd" id="radioMediCertiRcvd" value="N" onclick="chckMedCertiRcvd(this)" >No
			</td>	 
	</tr>
	<tr id="trNoMedCertRcvdCPONo" style="display:none">
			<td width="20%" align="left"><fmt:message key="PPMT.MEDCERTINO"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtMedCertiNo'
				id="txtMedCertiNo" style="text-align: left" /></td>
	</tr>
	<tr id="trNoMedCertRcvdCPODate" style="display:none">			
			<td width="20%" align="left"><fmt:message key="PPMT.MEDCERTIDATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtMediCertiDate'
				id="txtMediCertiDate" style="text-align: left" /></td>
	</tr>
	<tr id="trNoMedCertRcvdCPOAmnt" style="display:none">			
			<td width="20%" align="left"><fmt:message key="PPMT.MEDAUTHORITY"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtMedCertiAutho'
				id="txtMedCertiAutho" style="text-align: left" /></td>		
	</tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.GENERATEBILL"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="50%" align="left">
				<input type="radio" name="radioGenerateBill" id="radioGenerateBill" value="Y" onclick="chckGenerateBill(this)"> Yes
				<input type="radio" name="radioGenerateBill" id="radioGenerateBill" value="N" onclick="chckGenerateBill(this)" >No
			</td>	 
	</tr>
	<tr>
			<td>
				<hdiits:button name="btnViewBill" id="btnViewBill" type="button"
				captionid="PPMTBTN.VIEWBILL" bundle="${pensionLabels}" onclick="" style="display:none"/>
			</td>
	</tr>
	<tr id="trGenBillCPONo" style="display:none">
			<td width="20%" align="left"><fmt:message key="PPMT.CPONO"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtCpoNo'
				id="txtCpoNo" style="text-align: left" /></td>
	</tr>
	<tr id="trGenBillCPODate" style="display:none">			
			<td width="20%" align="left"><fmt:message key="PPMT.DATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtDateCommu'
				id="txtDateCommu" style="text-align: left" /></td>
	</tr>
	<tr id="trGenBillCPOAmnt" style="display:none">			
			<td width="20%" align="left"><fmt:message key="PPMT.AMOUNT"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtAmount'
				id="txtAmount" style="text-align: left" /></td>	
					
	</tr>
	<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.ORIMONTHLYPENAMNT"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtOrigMonthlyPension'
				id="txtOrigMonthlyPension" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.COMMVALUEPERMNTH"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtCommValPerMnth'
				id="txtCommValPerMnth" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.BALPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtBalPension'
				id="txtBalPension" style="text-align: left" /></td>	
					
	</tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
	<tr>
			<td width="20%" align="left"><fmt:message key="PPMT.COMPUTATIONDATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtComputationDate'
				id="txtComputationDate" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.RESTOFPENSION"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtRestOfPension'
				id="txtRestOfPension" style="text-align: left" /></td>
	</tr>
	<tr>			
			<td width="20%" align="left"><fmt:message key="PPMT.RESTOFPENSIONDATE"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name='txtRestOfPensionDate'
				id="txtRestOfPensionDate" style="text-align: left" /></td>	
					
	</tr>
	</table>
</fieldset>

</hdiits:form>