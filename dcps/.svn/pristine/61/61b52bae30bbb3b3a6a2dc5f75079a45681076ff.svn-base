<%try{ %>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<script type="text/javascript" src="script/pensionpay/pensionBills.js"> </script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript" src="script/pensionpay/RecoveryTab.js"></script>
<script type="text/javascript" src="script/pensionpay/pensionBills.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>


<script type="text/javascript">
LISTADVANCE='';
LISTBANK='';
function calAdvanceAmtTotal(field,fieldName)
{
	try
	{
		if(field.value!='')
		{
			var rowCount=Number(document.getElementById("hidBankGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
				{
					total=total+Number(document.getElementById(fieldName+cnt).value);
					document.getElementById("txtTotalAmt").value=total;
					setValidAmountFormat(document.getElementById("txtTotalAmt"));
				}
			}
			
		}
	}catch(ex)
	{
		
	}
	return false;
}
//
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="PnsnDtls"value="${resValue.PnsnDtls}"/>
<c:set var="PensionerName" value="${resValue.PensionerName}"/>
<c:set var="PPONo" value="${resValue.PPONo}"/>
<c:set var="SuppBillType" value="${resValue.SuppBillType}"/>
<c:set var="PayMode" value="${resValue.PayMode}"/>
<c:set var="showReadOnly" value="${resValue.showReadOnly}"/>  
<c:set var="currRole" value="${resValue.currRole}"></c:set>
<c:set var="trnPensionBillDtlsVO" value="${resValue.TrnPensionBillDtls}"></c:set>
<c:set var="trnPensionSupplyBillDtls" value="${resValue.TrnPensionSupplyBillDtls}"></c:set>



<c:forEach var="RecoveryType" items="${resValue.RecoveryType}" >
	<script> LISTADVANCE += '<option value="${RecoveryType.lookupName}"> ${RecoveryType.lookupName}</option>';</script>
</c:forEach>  
<c:forEach var="BankList" items="${resValue.BankList}" >
	<script> LISTBANK += '<option value="${BankList.id}"> ${BankList.desc}</option>';</script>
</c:forEach>
<script type="text/javascript"	src="script/pensionpay/SupplementaryBills.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<hdiits:form method="post" name="frmSuppBill"  validate="true" encType="multipart/form-data" >

<input type="hidden" name="hidDPnsnrCode" id="hidDPnsnrCode" value="${resValue.PensionerCode}"/>
<input type="hidden" name="hidBillTypeId" id="hidBillTypeId" value="${resValue.BillTypeId}"/>
<input type="hidden" name="hidBillNo" id="hidBillNo" value="${resValue.TrnBillRegister.billNo}" />
<input name="hidBillCntrlNo" type="hidden" value="${resValue.TrnBillRegister.billCntrlNo}" />
<input type="hidden" name=hdCurrRole" id="hdCurrRole" value="${resValue.currRole}"/>
<input type="hidden" name="userAction" id="userAction" value="save"/>	
<input type="hidden" name="showReadOnly" value="${resValue.showReadOnly}"/>
<input type="hidden" name="hidBillMvmntId" value="${resValue.lObjTrnBillMvmnt.billMvmtId }"/>
<input type="hidden" name="hidDPPONO" value="${resValue.PPONo}"/>
<input type="hidden" name="hidPensionBillHdrId" value="${resValue.TrnPensionBillHdr.trnPensionBillHdrId }"/>
<input type="hidden" name="hidPensionBillSupplyId" value="${resValue.TrnPensionSupplyBillDtls.pensionSupplyBillId}"/>
<input type="hidden" name="validNomineeFlag" id="validNomineeFlag" value="${resValue.ValidNomineeFlag}"/>
<input type="hidden" name="arrearDtls" id = "arrearDtls" value="${trnPensionBillDtlsVO.arrearDtls}"/>
<input type="hidden" name="hdnArrearAmt" id ="hdnArrearAmt" value="${trnPensionBillDtlsVO.arrearAmount}"/>
<input type="hidden" name="hdnInstallmentAmt" id ="hdnInstallmentAmt" />
<input type="hidden" name="hdnInstallment5Amt" id ="hdnInstallment5Amt"/>
<input type="hidden" name="hdnCashAmt" id ="hdnCashAmt" />
<input type="hidden" name="hdnNoOfInstallment" id ="hdnNoOfInstallment" />
<input type="hidden" name="hdnSixPayCalcFlag" id ="hdnSixPayCalcFlag" />
<input type="hidden" name="txtSchemeNo" id = "txtSchemeNo" value="${resValue.SchemeCode}"/>

<hdiits:hidden name="hidPrintBillString" id="hidPrintBillString" default="${resValue.PrintBillString}" /> 

<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.SUPPLBILLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>	
<table width="70%">
  <tr>
     <td width="15%">
	       <fmt:message key="PPMT.SUPBILLTYPE" bundle="${pensionLabels}"></fmt:message>
	 </td>
	 
	  <td width="35%">
	 	<c:choose>
	    	<c:when test="${SuppBillType == 'CVP'}">
	        <fmt:message key="PPMT.PENSION" bundle="${pensionLabels}" ></fmt:message>
	        <input type="radio" id="radioSupBillTypePnsn" name="radioSupBillType" value="PENSION" tabindex="14" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeCvp" name="radioSupBillType" value="CVP" checked="checked" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.DCRG" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeDcrg" name="radioSupBillType" value="DCRG" onclick="onChangeSupBillType();"/>
			</c:when>
			<c:when test="${SuppBillType == 'DCRG'}">
	        <fmt:message key="PPMT.PENSION" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypePnsn" name="radioSupBillType" value="PENSION" tabindex="14" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeCvp" name="radioSupBillType" value="CVP" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.DCRG" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeDcrg" name="radioSupBillType" value="DCRG" checked="checked" onclick="onChangeSupBillType();"/>
			</c:when>
			<c:otherwise>
	         <fmt:message key="PPMT.PENSION" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypePnsn" name="radioSupBillType" value="PENSION" tabindex="14" checked="checked" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeCvp" name="radioSupBillType" value="CVP" onclick="onChangeSupBillType();"/>
			<fmt:message key="PPMT.DCRG" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioSupBillTypeDcrg" name="radioSupBillType" value="DCRG" onclick="onChangeSupBillType();"/>
			</c:otherwise>
			
			</c:choose>
	 
	 	 </td>
  </tr>	 	
</table>	

<table width="100%" >
    
    <tr>
		<td width="15%">
	       <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="20%">
        	 <input type="text" id="txtPPONo" name="txtPPONo" value="${resValue.PPONo}"onblur="getPensionerDtls()" style="display: ;text-transform: uppercase;">
	    
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtName" name="txtName" value="${resValue.PensionerName}" readonly="readonly"/>
		 </td>	
		  <td align="center">
					</td>
	</tr>
	</table>
	<div id="divPensionDtls">
	<table width="100%"  >
	<tr >
		<td width="15%">
	       <fmt:message key="PPMT.PENSION" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtPensionAmt" name="txtPensionAmt" value="${trnPensionBillDtlsVO.pensionAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.PEONALLOWANCE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtPeonAllowAmt" name="txtPeonAllowAmt" value="${trnPensionBillDtlsVO.peonAllowance}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		  <td rowspan="12" align="center"><div id="arrearDtlContainer">
					</div></td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.ADP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtADPAmt" name="txtADPAmt" value="${trnPensionBillDtlsVO.adpAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.MEDALLOWANCE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtMedicalAllowAmt" name="txtMedicalAllowAmt" value="${trnPensionBillDtlsVO.medicalAllowenceAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.DP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtDPAmt" name="txtDPAmt" value="${trnPensionBillDtlsVO.dpAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.GALANTORYAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtGallantryAmt" name="txtGallantryAmt" value="${trnPensionBillDtlsVO.gallantryAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.IR1" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtIR1Amt" name="txtIR1Amt" value="${trnPensionBillDtlsVO.ir1Amount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.OTHERBEN" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtOtherBenefit" name="txtOtherBenefit" value="${trnPensionBillDtlsVO.otherBenefits}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.IR2" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtIR2Amt" name="txtIR2Amt" value="${trnPensionBillDtlsVO.ir2Amount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.OTHER1" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%" colspan="2">
	        <input type="text" id="txtOthArrearAmt" name="txtOthArrearAmt" value="${trnPensionBillDtlsVO.other1}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
	        
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.IR3" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtIR3Amt" name="txtIR3Amt" value="${trnPensionBillDtlsVO.ir3Amount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.OTHER2" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtOthArrearAmt2" name="txtOthArrearAmt2" value="${trnPensionBillDtlsVO.other2}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.DA" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtDAAmt" name="txtDAAmt" value="${trnPensionBillDtlsVO.tiAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.OTHER3" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtOthArrearAmt3" name="txtOthArrearAmt3" value="${trnPensionBillDtlsVO.other3}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtCvpAmt" name="txtCvpAmt" value="${trnPensionBillDtlsVO.cvpMonthAmount}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
		 <td>
		 </td>
		 <td>
		 <c:choose>
	        	<c:when test="${currRole== '365450'}">
	        	<hdiits:button type="button" name="btnArrear" id="btnArrear"  captionid="BTN.ARREAR" bundle="${pensionLabels}" classcss="bigbutton" onclick="openWindowForArrear();"/>
	        	</c:when>
	        </c:choose>
		 </td>
	</tr>
	
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.GROSSAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    
		 <td width="20%">
		      <input type="text" id="txtGrossAmt" name="txtGrossAmt" value="${resValue.SuppAmnt}" readonly="readonly" onblur="calculateNetAmount();onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		      <input type="hidden" id="txtExpenditure" name="txtExpenditure" value="" />		      
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.ARREARPAYOF6PC" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtArrearOf6PC" name="txtArrearOf6PC" value="${trnPensionBillDtlsVO.arrear6PC}" onblur="onBlur(this);setValidAmountFormat(this);calculateNetAmount();" readonly="readonly"  style="text-align: right;"/>
	        <c:if test="${resValue.TrnBillRegister.billCntrlNo == null}">
	        	<hdiits:button type="button" name="btnArrearOf6PC" id="btnArrearOf6PC"  captionid="BTN.CALC6PCARREAR" bundle="${pensionLabels}" classcss="bigbutton" onclick="validatePnsnrBeforSixPayArrearCalc();"/>
	        </c:if>
		 </td>
	</tr>
	<tr>	 
		<td width="15%">
	       <fmt:message key="PPMT.RECOVERYAMT" bundle="${pensionLabels}" ></fmt:message>
	    </td>
		 <td width="20%">
		      <input type="text" id="txtRecAmt" name="txtRecAmt" value="${trnPensionBillDtlsVO.recoveryAmount}" readonly="readonly" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;" default="0.0"/>
		      <input type="hidden" id="txtRecovery" name="txtRecovery" value="" />
		      		      
		 </td>
	    		 		      
	</tr>
	
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.NETAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtNetAmt" name="txtNetAmt" value="${trnPensionBillDtlsVO.reducedPension}" readonly="readonly" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>	 
	
</table>
</div>
<div id="divCvpDtls" style="display:none">
<table width="71%">
	<tr>
		<td width="15%">
			<fmt:message key="PPMT.CVPORDERNO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" name="txtCPONumber" id="txtCPONumber" value="${trnPensionSupplyBillDtls.cvpOrderNo}" maxlength="35" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		</td>
		<td width="15%">
			<fmt:message key="PPMT.CVPPAIDDATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" id="txtCPODate" name="txtCPODate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionSupplyBillDtls.cvpOrderDate}" />"
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	        <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtCPODate",375,570)'style="cursor: pointer;" ${disabled}/>
		</td>
		
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="PPMT.AMOUNTSANCT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" name="txtCvpSanctionedAmt" id="txtCvpSanctionedAmt" value="${trnPensionBillDtlsVO.reducedPension}"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" />
		</td>
		<td width="15%">
			<fmt:message key="PPMT.CVPAMOUNT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" name="txtCommutationAmt" id="txtCommutationAmt" value="${trnPensionSupplyBillDtls.totalCvpAmount}"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" />
		</td>
		
	</tr>

</table>
</div>
<div id="divDcrgDtls" style="display:none">
<table width="71%">
	<tr>
		<td width="15%">
			<fmt:message key="PPMT.GPONO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" name="txtGPONumber" id="txtGPONumber" value="${trnPensionSupplyBillDtls.gpoNo}" maxlength="35" onfocus="onFocus(this)" onblur="onBlur(this);"/>
		</td>
		<td width="15%">
			<fmt:message key="PPMT.GPODATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%">
			<input type="text" id="txtGPODate" name="txtGPODate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionSupplyBillDtls.gpoDate}" />"
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	        <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtGPODate",375,570)'style="cursor: pointer;" ${disabled}/>
		</td>
	</tr>
	<tr>
		<td width="15%">
			<fmt:message key="PPMT.AMOUNTSANCT" bundle="${pensionLabels}"></fmt:message>
		</td>
		 <td width="20%">
		      <input type="text" id="txtDcrgSanctionedAmt" name="txtDcrgSanctionedAmt" value="${resValue.SuppAmnt}" onblur="calculateNetAmount();onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		            
		 </td>
	</tr>
	<tr>	 
		<td width="15%">
	       <fmt:message key="PPMT.RECOVERYAMT" bundle="${pensionLabels}" ></fmt:message>
	    </td>
		 <td width="20%">
		      <input type="text" id="txtDcrgRecAmt" name="txtDcrgRecAmt" value="${trnPensionBillDtlsVO.recoveryAmount}" readonly="readonly" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;" default="0.0"/>
		 </td>
	    		 		      
	</tr>
	
	<tr>
		<td width="15%">
	       <fmt:message key="PPMT.NETAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="20%">
	        <input type="text" id="txtDcrgNetAmt" name="txtDcrgNetAmt" value="${trnPensionBillDtlsVO.reducedPension}" readonly="readonly" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right;"/>
		 </td>
	</tr>

</table>
</div>
<input type="hidden" id="hidFlag" name="hidFlag"/>
<fieldset class="tabstyle" style="width:100%" id="fsRecoveryDtls"><legend> <b>
	<fmt:message key="PPMT.DEDRECOVERY" bundle="${pensionLabels}"></fmt:message></b> </legend>

<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidRecoveryGridSize" name="hidRecoveryGridSize" value="0"/>
<hdiits:button id="btnRecDtlsAddRow" type="button" name="btnRecDtlsAddRow" caption="Add Row" onclick="recoveryDtlsTableAddRow();" />
<table width="95%" id="tblRecoveryDtls" border="1">
	    <tr style="width: 90%" class="datatableheader" >
			 				  <td width="15%" class="datatableheader"><fmt:message key="PPMT.TYPESOFADVANCE" bundle="${pensionLabels}"></fmt:message></td>
			  				  <td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}" ></fmt:message></td>	
			  				  <td width="15%" class="datatableheader"><fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>
			 				  <td width="5%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
						
		</tr>
		<c:choose>
			<c:when test="${resValue.RecoveryDtls != null}">
			<c:forEach var="RecoveryDtls" items="${resValue.RecoveryDtls}" varStatus="Counter">
					
				<tr>
				
					
					<td class="tds" align="center">
					<c:choose>
					<c:when test="${SuppBillType == 'DCRG'}">
						<input type="text" name="txtNature" id="txtNature${Counter.index}" maxlength="60" value="${RecoveryDtls.nature}"/>
					</c:when>
					<c:otherwise>
					<select name="cmbRecoveryType"
							id="cmbRecoveryType${Counter.index}" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							 <c:forEach var="recovery" items="${resValue.RecoveryType}">
					         <c:choose>
					            <c:when test="${recovery.lookupShortName == RecoveryDtls.recoveryType}">
					         <option selected="selected" value="${recovery.lookupShortName}"><c:out value="${recovery.lookupShortName}"></c:out></option>
					         </c:when>
					         <c:otherwise>
					         <option value="${recovery.lookupShortName}">
									<c:out value="${recovery.lookupShortName}"></c:out>									
								</option>
					         </c:otherwise>
					         </c:choose>
		            		</c:forEach>	
						</select>
						</c:otherwise>
					</c:choose>
					</td>	
					
					<td class="tds" align="center"><input type="text"
						name="txtAmount" id="txtAmount${Counter.index}" size="15"
						value="${RecoveryDtls.amount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calRecoveryAmtTotal(this,'txtAmount');calculateNetAmount()" onkeypress="amountFormat(this);"  style="text-align: right;"/></td>
					<td class="tds" align="center">
						<input type="text" value="${RecoveryDtls.schemeCode}" 
						name="txtSchemeCode" id="txtSchemeCode${Counter.index}" onblur="validateSchemeCode(this);"  size="15" />
						<a href="#" id="txtSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif"/></a>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblRecoveryDtls');"/>
					</td>
						
				</tr>
				
			<script>
				document.getElementById("hidRecoveryGridSize").value = Number('${Counter.index}') + 1;
			</script>
			</c:forEach>
			</c:when>
		</c:choose>	
			
</table> 
</div>
	
</fieldset>	
<table>
				
				<tr>
					<td colspan="5">
					Mode Of Payment :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
						<select id="paymentMode" name="paymentMode" onclick="validatePaymentMode();">
						<c:choose>
						<c:when test="${PayMode == 'ECS'}">
							<option value="ECS" selected="selected">ECS/N.EFT</option>
							<option value="Cheque">Cheque</option>
							<option value="Cash">Cash</option>
						</c:when>
						<c:when test="${PayMode == 'Cheque'}">
							<option value="ECS">ECS/N.EFT</option>
							<option value="Cheque"  selected="selected">Cheque</option>
							<option value="Cash">Cash</option>
						</c:when>
						<c:when test="${PayMode == 'Cash'}">
							<option value="ECS">ECS/N.EFT</option>
							<option value="Cheque">Cheque</option>
							<option value="Cash" selected="selected">Cash</option>
						</c:when>
						<c:otherwise>
							<option value="ECS">ECS/N.EFT</option>
							<option value="Cheque">Cheque</option>
							<option value="Cash">Cash</option>
						</c:otherwise>
						</c:choose>	
						</select>
					</td>
				</tr>
</table>	
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidBankGridSize" name="hidBankGridSize" value="0"/>
<hdiits:button id="btnBankDtlsAddRow" type="button" name="btnBankDtlsAddRow" caption="Add Row" onclick="bankDtlsTableAddRow();" />
<table width="98%" id="tblBankDtls">
	     <tr class="datatableheader"> 
			    		<td width="20%" class="datatableheader"><fmt:message key="PPMT.BENNAME" bundle="${pensionLabels}" ></fmt:message></td>	
			    		<td width="15%" class="datatableheader"><fmt:message key="PPMT.BANKCODE" bundle="${pensionLabels}"></fmt:message></td>
						<td width="25%" class="datatableheader"><fmt:message key="PPMT.BANK" bundle="${pensionLabels}"></fmt:message></td>
						<td width="20%" class="datatableheader"><fmt:message key="PPMT.BRANCH" bundle="${pensionLabels}"></fmt:message></td>
						<td width="7%" class="datatableheader"><fmt:message key="PPMT.ACNO" bundle="${pensionLabels}"></fmt:message></td>
						<td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
						<td width="10%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
		 </tr>					    
		<c:choose>
			<c:when test="${resValue.PnsnDtls != null}">
			<c:forEach var="PnsnDtls" items="${resValue.PnsnDtls}" varStatus="Counter">
					
				<tr>
				<td class="tds" align="center">
						<input type="text" name="txtPartyName" id="txtPartyName${Counter.index}" size="15" value="${PnsnDtls.beneifiicaryName}"  onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)">
					</td>
					<td class="tds" align="center"><input type="text"
						name="txtBranchCode" id="txtBranchCode${Counter.index}"  onblur="getBnkBrnchNameFrmBnkCode(this);" onKeyPress="numberFormat(this)"
						size="15" value="${PnsnDtls.branchCode}" /></td>
					<td class="tds" align="center"><select name="cmbBankCode"
							id="cmbBankCode${Counter.index}" style="width: 100%" onchange="getBranchNameFromBankCode(this);">
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							 <c:forEach var="bank" items="${resValue.BankList}">
					         <c:choose>
					            <c:when test="${bank.id == PnsnDtls.bankCode}">
					         <option selected="selected" value="${bank.id}"><c:out value="${bank.desc}"></c:out></option>
					         </c:when>
					         <c:otherwise>
					         <option value="${bank.id}">
									<c:out value="${bank.desc}"></c:out>									
								</option>
					         </c:otherwise>
					         </c:choose>
		            		</c:forEach>
							
						</select>
					</td>	
					<td class="tds" align="center"><select name="cmbBankBranchName"
						id="cmbBankBranchName${Counter.index}" onchange="getIfscCodeFromBrachCode('${Counter.index}');onChangeBranch(this);" style="width: 100%">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<option value="${PnsnDtls.branchCode}" selected="selected"><c:out
							value="${PnsnDtls.branchName}" /></option>
					</select></td>
					<td class="tds" align="center"><input type="text"
						name="txtAccountNo" id="txtAccountNo${Counter.index}" size="25"
						value="${PnsnDtls.accountNo}" /></td>
						<td>
						<input type="text" value="${PnsnDtls.amount}" name="txtChkAmt" id="txtChkAmt${Counter.index}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calAdvanceAmtTotal(this,'txtChkAmt')" onkeypress="amountFormat(this);"  style="text-align: right" />
						</td>
					<td class="tds" align="center">
						<img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblBankDtls');"/>
						<input type="hidden" name="txtAddress" value=" " />
						<input type="hidden" name="txtMicrCode" id="txtMicrCode${Counter.index}"value="${PnsnDtls.micrCode}" />
						</td>
						
				</tr>
				
			<script>
				document.getElementById("hidBankGridSize").value = Number('${Counter.index}') + 1;
				</script>
			</c:forEach>
			</c:when>
		</c:choose>	
</table>
<table width="95%">		   
		   <tr>		   		
		   		<td width="43%">
		   		</td>
		   		<td width="20%">
		   		<fmt:message key="PPMT.TOTALAMT" bundle="${pensionLabels}"></fmt:message>		   		
		      	<input type="text" id="txtTotalAmt" name="txtTotalAmt"  readonly="readonly" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>		      
		 		</td>
		   </tr>
		  
		   
</table> 
 
</div>
</fieldset>
<script>
		   	calAdvanceAmtTotal(this,'txtChkAmt');
</script>


<table width="100%">
<tr>
<td width="40%">
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REMARKS" bundle="${pensionLabels}"></fmt:message></b> </legend>
<table width="100%">
	<tr>
		<td width="10%" valign="top">
	       <fmt:message key="PPMT.REMARKS" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%" valign="top">
		      <textarea id="id_txtareaRemarks" name="id_txtareaRemarks" style="width: 70%" >${resValue.currRemarks}</textarea>		      
		 </td>				 		
	</tr>	 
</table>
</fieldset>
</td>
<c:if test="${!(resValue.TrnBillRegister.billNo =='' or resValue.TrnBillRegister.billNo == null)}">
<td valign="top" width="20%">
<hdiits:button name="btnViewRemark" id="btnViewRemark" type="button" caption="View Remarks" bundle="${pensionLabels}" onclick="viewRemarks();"  />
</td>
</c:if>
</tr>

						
</table>
</hdiits:form>


<table  align="center">
<tr>
	<td>
		<c:choose>	
			<c:when test="${showReadOnly == 'N'}">
				<hdiits:button name="btnUpdate" type="button" captionid="PPMT.UPDATE" bundle="${pensionLabels}" onclick="saveDataOfBill();"  />
				
			</c:when>
			<c:when test="${currRole== '365451'}">
				<hdiits:button type="button" name="btnApprove" id="btnApprove"  captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick="saveDataOfBill()"/>
				<hdiits:button type="button" name="btnReject" id="btnReject"  captionid="PPMT.REJECT" bundle="${pensionLabels}" onclick="rejectBill()"/>
			</c:when>
			<c:otherwise>
				<hdiits:button name="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveDataOfBill();"  />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${resValue.TrnBillRegister.billCntrlNo != null and SuppBillType == 'PENSION'}">
				<hdiits:button name="btnViewOuterBill" id ="btnViewOuterBill" type="button" captionid="BTN.VIEWOUTERBILL" bundle="${pensionLabels}" onclick="viewOuterSupplPensionBill();"  style="display: inline;"/>
				<hdiits:button name="btnViewInnerBill" id ="btnViewInnerBill" type="button" captionid="BTN.VIEWINNERBILL" bundle="${pensionLabels}" onclick="viewInnerSupplPensionBill();"  style="display: inline;"/>
			</c:when>
			<c:when test="${resValue.TrnBillRegister.billCntrlNo != null}">
				<hdiits:button name="btnViewBill" type="button" captionid="PPMT.VIEWBILL" bundle="${pensionLabels}" onclick="printSupplBill();"  />
			</c:when>
		</c:choose>
		
		
	<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"  />
	</td>
</tr>
</table>
<c:choose>
<c:when test="${SuppBillType == 'CVP'}">
<script>
		document.getElementById("divPensionDtls").style.display="none";
		document.getElementById("divCvpDtls").style.display="block";
		document.getElementById("divDcrgDtls").style.display="none";
		document.getElementById("fsRecoveryDtls").style.display="none";
		document.getElementById("btnBankDtlsAddRow").style.display="none";
</script>
</c:when>
<c:when test="${SuppBillType == 'DCRG'}">
<script>
		document.getElementById("divPensionDtls").style.display="none";
		document.getElementById("divCvpDtls").style.display="none";
		document.getElementById("divDcrgDtls").style.display="block";
		document.getElementById("fsRecoveryDtls").style.display="block";
		document.getElementById("btnBankDtlsAddRow").style.display="inline";
</script>
</c:when>
<c:otherwise>
<script>
		document.getElementById("divPensionDtls").style.display="block";
		document.getElementById("divCvpDtls").style.display="none";
		document.getElementById("divDcrgDtls").style.display="none";
		document.getElementById("fsRecoveryDtls").style.display="block";
		document.getElementById("btnBankDtlsAddRow").style.display="none";
</script>
</c:otherwise>
</c:choose>

<c:if test="${resValue.TrnBillRegister.billCntrlNo != null}">
<script>
document.getElementById("radioSupBillTypePnsn").disabled=true;
document.getElementById("radioSupBillTypeCvp").disabled=true;
document.getElementById("radioSupBillTypeDcrg").disabled=true;
document.getElementById("txtPPONo").disabled = true;
</script>
</c:if> 
<script>
var lStrShowReadOnly = "${showReadOnly}";
var tempFlag=0;
if(lStrShowReadOnly == "Y")
{
	objElems = document.frmSuppBill.elements;
	for(var i=0;i<objElems.length;i++)
	{
		if(objElems[i].tagName == "INPUT")
		{
			if(objElems[i].type == "button" || objElems[i].type == "radio" || objElems[i].type == "checkbox")
			{
				objElems[i].disabled = true;
			}
			else
			{
				objElems[i].readOnly = true;
			}
		}
		else if(objElems[i].tagName == "SELECT" || objElems[i].tagName == "TEXTAREA" )
		{
			objElems[i].disabled = true;
		}
	 }
	
	
	 document.getElementById("id_txtareaRemarks").disabled='';
	 document.getElementById("btnViewRemark").disabled='';
}							


</script>
	<script>
	var arrearDtlsVal = document.getElementById("arrearDtls").value;
	if(arrearDtlsVal.length > 0)
	{
		arrearDtlsVal = arrearDtlsVal.replace(new RegExp("~","g"),">");
	}
	document.getElementById("arrearDtlContainer").innerHTML = arrearDtlsVal;
	</script>	
<%}catch(Exception ex){ex.printStackTrace();}%>