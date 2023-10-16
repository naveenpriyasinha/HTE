
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 

<script type="text/javascript" src="script/login/validation.js"></script>
<script type="text/javascript" src="script/exprcpt/Common.js"></script>

<script> keyPreview =1 ;</script> 

<fmt:setBundle basename="resources.rcptacct.rcptacct" var="rcptacctLabels" scope="request"/>
<hdiits:form name="challan_search_posted_Rec" id="challan_search_posted_Rec" validate="true" method="post">
	<div onkeydown="processF12();">
	<table align="center" width="90%" id="id_searchTable" style="display: inline;">
		<tr>
			<td width="20%" align="left">
				<fmt:message key="CDP.BRANCHCODE" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtBranchCodeSearch" id="id_txtBranchCodeSearch" tabindex="6" maxlength="8" value="${resValue.brachCode}"/>
			</td>
			<td width="20%" align="left">
				<fmt:message key="CDP.SALES_TAX_NO" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtSalesTaxNoSearch" id="id_txtSalesTaxNoSearch" tabindex="7" maxlength="11" value="${resValue.salesTaxNo}"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="CDP.RCVD_BY_BANK" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtRcvdByBankDateSearch" id="id_txtRcvdByBankDateSearch" tabindex="9" maxlength="10" value="${resValue.rcvdByBank}"/>
			</td>
			<td width="20%" align="left">
				<fmt:message key="CDP.PARTY_NAME" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtPartyNameSearch" id="id_txtPartyNameSearch" tabindex="10" onkeypress="upperCase(event)" value="${resValue.partyName}"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="CMN.MAJORHEAD" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtMajorHeadSearch" id="id_txtMajorHeadSearch" tabindex="11" maxlength="4" value="${resValue.majorHead}"/>
			</td>
			<td width="20%" align="left">
				<fmt:message key="CDP.AMOUNT" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtAmountSearch" id="id_txtAmountSearch" tabindex="12" maxlength="20" value="${resValue.amount}"  onkeypress="javascript:amountFormatOnly2Dec(this)"/>
			</td>
		</tr>
	<!-- 
	 	<tr>
			<td width="20%" align="left">
				<fmt:message key="CMN.CHALLAN_NO" bundle="${rcptacctLabels}" ></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtReceiptNoSearch" id="id_txtReceiptNoSearch" tabindex="1" value="${resValue.rcptNo}"/>
			</td>
			<td width="20%" align="left">
				<fmt:message key="CMN.RCPT_DATE" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtReceiptDateSearch" id="id_txtReceiptDateSearch" tabindex="2" value="${resValue.rcptDate}"/>
			</td>
		</tr>
	 	<tr>
			<td width="20%" align="left">
				<fmt:message key="CDP.TIN_NO" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtTinNoSearch" id="id_txtTinNoSearch" tabindex="5" value="${resValue.tinNo}"/>
			</td>
			<td width="20%" align="left">
				<fmt:message key="CMN.LOC_CODE" bundle="${rcptacctLabels}"></fmt:message>
			</td>
			<td width="20%" align="left">
				<input type="text" name="txtLocationCodeSearch" id="id_txtLocationCodeSearch" tabindex="8" maxlength="5" value="${resValue.locationCd}"/>
			</td>
		</tr>
	 -->	
	</table>
	
	<p align="center">
		  <hdiits:button name="btnSearch" type="button" value="Search [F12]" onclick="searchPostedChallan();" tabindex="13"/>
		<!-- <input type="button" class="searchButton" name="btnSearch" onclick="searchPostedChallan();") tabindex="13"/> -->
	</p>
	<input type="hidden" name="viewPage" value="<%=request.getParameter("viewName")%>" >
	</div>
</hdiits:form>

<script type="text/javascript">
function processF12()
{
if(event.keyCode == 123)
	{
		try{
			searchPostedChallan();
		}catch(e){}
	}
}

function searchPostedChallan()
{
document.getElementById("id_searchTable").focus();

		if(validateSearchFeilds())
		{
			document.forms[0].action ='ifms.htm?actionFlag=getPostedChallanSearch';
			document.forms[0].submit();
		}
}
function validateSearchFeilds()
{
 // 	var rcptDate = document.getElementById('id_txtReceiptDateSearch').value;
	
	var rcvdByBankDate = document.getElementById('id_txtRcvdByBankDateSearch').value;
	/*if(rcptDate != "" && !dateValidate(rcptDate,'dd/MM/yyyy'))
	{
		alert('Invalid Date.');
		document.getElementById('id_txtReceiptDateSearch').focus();
		return false;
	}
	else if(rcvdByBankDate != "" && !dateValidate(rcvdByBankDate,'dd/MM/yyyy'))
	{
		alert('Invalid Date.');
		document.getElementById('id_txtRcvdByBankDateSearch').focus();
  		return false;
	}*/
	return true;
}
</script>