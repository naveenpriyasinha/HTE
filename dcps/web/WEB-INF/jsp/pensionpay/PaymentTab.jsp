<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/PaymentTab.js"></script>
<script type="text/javascript">
var LISTMONTHS='';
var LISTYEARS='';
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="mstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}" />
<c:set var="mstPensionerDtlsVO" value="${resValue.MstPensionerDtlsVO}" />
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}" />
<c:set var="trnProvPensionDtlsVO" value="${resValue.TrnProvisionalPensionDtlsVO}"></c:set>
<c:set var="trnPnsnArrearDtlsForDA" value="${resValue.TrnPnsnArrearDtlsForDA}"></c:set>
<c:set var="trnPnsnArrearDtlsFor6PC" value="${resValue.TrnPnsnArrearDtlsFor6PC}"></c:set>
<c:set var="trnPnsnArrearDtlsForPnsn" value="${resValue.TrnPnsnArrearDtlsForPnsn}"></c:set>
<c:set var="trnPnsnArrearDtlsForComtPnsn" value="${resValue.TrnPnsnArrearDtlsForComtPnsn}"></c:set>
<c:set var="trnPnsnArrearDtlsForOther" value="${resValue.TrnPnsnArrearDtlsForOther}"></c:set>
<c:set var="currentDate" value="${resValue.CurrDate}"/>
<c:set var="read" value="disabled" />

<c:forEach var="MonthList" items="${resValue.lLstMonths}" >
	<script> LISTMONTHS += '<option value="${MonthList.id}"> ${MonthList.desc}</option>';</script>
</c:forEach>
<c:forEach var="YearList" items="${resValue.lLstYears}" >
	<script> LISTYEARS += '<option value="${YearList.desc}"> ${YearList.desc}</option>';</script>
</c:forEach>

<script type="text/javascript">
LISTBILLTYPE='<option value="<fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/></option>'
	+'<option value="<fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/></option>';

LISTCHRGVOTD='<option value="C"><fmt:message key="PPMT.CHARGED" bundle="${pensionConstants}"/></option>'
	+'<option value="V"><fmt:message key="PPMT.VOTED" bundle="${pensionConstants}"/></option>';	
</script>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.BANKDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>		
  <table width="100%">	
	
	
	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.PMNTSCHEME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbPaymentScheme" id="cmbPaymentScheme"  style="width: 50%;" onchange="onChangePaymentScheme();" tabindex="12">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:choose>
					<c:when test="${mstPensionerDtlsVO.paymentScheme == 'MO'}">
						<option value="<fmt:message key="PAYSCHEME.REGULAR" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.MONEYORDER" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.MONEYORDER" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.CASH" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.CASH" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:when test="${mstPensionerDtlsVO.paymentScheme == 'RP'}">
						<option value="<fmt:message key="PAYSCHEME.REGULAR" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.MONEYORDER" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.MONEYORDER" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.CASH" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.CASH" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:when test="${mstPensionerDtlsVO.paymentScheme == 'CS'}">
						<option value="<fmt:message key="PAYSCHEME.REGULAR" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.MONEYORDER" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.MONEYORDER" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.CASH" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.CASH" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:otherwise>
						<option value="<fmt:message key="PAYSCHEME.REGULAR" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.MONEYORDER" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.MONEYORDER" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYSCHEME.CASH" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.CASH" bundle="${pensionLabels}"/></option>
					</c:otherwise>
				</c:choose>
					
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label>		      
		</td>  
	    <td width="15%">
	       <fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	       <select name="cmbBankCode" id="cmbBankCode"   style="width: 80%"  onblur="resetIfscCode();" tabindex="13">
	      	    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			    <c:forEach var="bank" items="${resValue.lLstBanks}">
						<c:choose>
							<c:when test="${bank.id == mstPensionerDtlsVO.bankCode}">
								<option selected="selected" value='${bank.id}' title="${bank.desc}">
									<c:out value="${bank.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value='${bank.id}' title="${bank.desc}">
									<c:out value="${bank.desc}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    
		    </select>
		 </td>
	    
	</tr>
	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.BANKBRANCH" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <select name="cmbBankBranch" id="cmbBankBranch"   style="width: 70%" onchange="getAuditorNameFromBranchCode();getIfscCodeFromBrachCode();" tabindex="14">
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option> 
			    <c:choose>
							<c:when test="${mstPensionerDtlsVO.branchCode != null}">
     						<c:forEach var="branchList" items="${resValue.lLstPnsnrBankBranch}">
							       <c:choose>
									<c:when test="${branchList.id == mstPensionerDtlsVO.branchCode}">
											<option value="${branchList.id}" selected="selected" title="${branchList.desc}">
											<c:out value="${branchList.desc}"></c:out>									
											</option>
								   </c:when>
							    <c:otherwise>
										<option value="${branchList.id}" title="${branchList.desc}">
										<c:out value="${branchList.desc}"></c:out>									
										</option>
								</c:otherwise>
						       </c:choose>
							</c:forEach>		
					        </c:when>
					    <c:otherwise>
							<c:forEach var="branchList" items="${resValue.lLstBankBranch}">
							<option value="${branchList.id}" title="${branchList.desc}">
								<c:out value="${branchList.desc}"></c:out>
							</option>	
							</c:forEach>		
						</c:otherwise>
						</c:choose>
	    </select>
		
		 </td>
	   
		 <td width="15%">
	       <fmt:message key="PPMT.BANKBRANCHCODE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtBankBranchCode" name="txtBankBranchCode" value="${mstPensionerDtlsVO.branchCode}" onKeyPress="numberFormat(this)" onfocus="onFocus(this)"  onblur="onBlur(this);getBankBranchFrmBnkCode(this);" tabindex="15" />
	        
		 </td>
		   
	</tr>
	<tr>
		 <td width="20%">
	       <fmt:message key="PPMT.ACCNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtAccountNo" name="txtAccountNo" value="${mstPensionerDtlsVO.accountNo}"  onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="20" tabindex="16"/>
		      
		</td> 
		
	   <td width="15%">
	       <fmt:message key="PPMT.IFSCCODE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtIfscCode" name="txtIfscCode" readonly="readonly" value="${resValue.IfscCode}"/>		      
		 </td>      
	</tr>
</table>
</fieldset>

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PENSIONDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%">	
<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.PNSNRCATAGORY" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <select name="cmbHeadCode" id="cmbHeadCode"   style="width: 50%" onchange="getHeadCodeDesc();calculateDPAndDAAmounts();" tabindex="17">
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			    <c:forEach var="headCode" items="${resValue.lLstHeadCode}">
						<c:choose>
								<c:when test="${headCode.id == trnPensionRqstHdrVO.headCode}">
								<option selected="true" value='${headCode.id}' title="${headCode.desc}">
									<c:out value="${headCode.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value='${headCode.id}' title="${headCode.desc}">
									<c:out value="${headCode.desc}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    
		    </select>
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
		 </td>
		 
		 <td width="15%">
	       <fmt:message key="PPMT.HEADCODEDESC" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtHeadCodeDesc" name="txtHeadCodeDesc" size="35" readonly="readonly" value="${resValue.HeadCodeDesc}"/>
		      <label id="mandtryFinal" class="mandatoryindicator">*</label>
		 </td>       
	</tr>
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.STATEDEPT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	      <select name="cmbStateCode" id="cmbStateCode"   style="width: 50%" onchange="getHeadCodeDesc();calculateDPAndDAAmounts();" tabindex="18">
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			    <c:forEach var="ListOfStateCode" items="${resValue.lLstStateDept}">
						<c:choose>
							<c:when test="${ListOfStateCode.id == trnPensionRqstHdrVO.daRateForState}">
								<option selected="selected" value='${ListOfStateCode.id}'>
									<c:out value="${ListOfStateCode.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value='${ListOfStateCode.id}'>
									<c:out value="${ListOfStateCode.desc}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			    
		    </select>
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
		 </td>
		 
		 <td width="15%">
	    </td>
		 <td width="35%">
		 </td>       
	</tr>
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.ROP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <select name="cmbRopType" id="cmbRopType"   style="width: 37%" onfocus="onFocus(this)"  onblur="onBlur(this);" onchange="onChangeROPType();getHeadCodeDesc();calculateDPAndDAAmounts();calcReducedPension();" tabindex="19">
			    <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			    <c:choose>
			    	<c:when test="${trnPensionRqstHdrVO.ropType == '1986'}">
			    		<option value="<fmt:message key="PPMT.ROPTYPE1986" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.ROPTYPE1996" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.ROPTYPE2006" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${trnPensionRqstHdrVO.ropType == '1996'}">
			    		<option value="<fmt:message key="PPMT.ROPTYPE1986" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.ROPTYPE1996" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.ROPTYPE2006" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:when test="${trnPensionRqstHdrVO.ropType == '2006'}">
			    		<option value="<fmt:message key="PPMT.ROPTYPE1986" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.ROPTYPE1996" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.ROPTYPE2006" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:when>
			    	<c:otherwise>
			    		<option value="<fmt:message key="PPMT.ROPTYPE1986" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1986" bundle="${pensionLabels}"/></option>
			   			<option value="<fmt:message key="PPMT.ROPTYPE1996" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.1996" bundle="${pensionLabels}"/></option>
			    		<option value="<fmt:message key="PPMT.ROPTYPE2006" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.2006" bundle="${pensionLabels}"/></option>
			    	</c:otherwise>
			    </c:choose>
			   
		    </select>
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>
	
		<td width="15%">
	      	<!--  <fmt:message key="PPMT.DPMERGED" bundle="${pensionLabels}"></fmt:message> -->
	    </td>

	     <td width="35%">
	     <c:choose>
	     <c:when test="${trnPensionRqstHdrVO.dpFlag == 'Y'}" >
	    	<!-- <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeY" name="radioDpMerge" value="Y" checked="checked" disabled="disabled" onclick="getHeadCodeDesc();onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" tabindex="20" style="display: none"/>
			<!-- <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeN" name="radioDpMerge" value="N"  disabled="disabled" onclick="onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" style="display: none"/>
		</c:when>
		<c:when test="${trnPensionRqstHdrVO.dpFlag =='N'}" >
			<!-- <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeY" name="radioDpMerge" value="Y"  disabled="disabled" onclick="getHeadCodeDesc();onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" tabindex="20" style="display: none"/>
			<!-- <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeN" name="radioDpMerge" value="N" checked="checked"  disabled="disabled" onclick="onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" style="display: none"/>
		</c:when>
		<c:otherwise>
			<!-- <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeY" name="radioDpMerge" value="Y"  disabled="disabled" onclick="getHeadCodeDesc();onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" tabindex="20" style="display: none"/>
			<!-- <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message> -->
			<input type="radio" id="radioDpMergeN" name="radioDpMerge" value="N"  disabled="disabled" onclick="onClickDPMerged(this);calculateDPAndDAAmounts();calcReducedPension();" style="display: none"/>
		</c:otherwise>
		</c:choose>
		<!-- <label id="mandtryFinal" class="mandatoryindicator">*</label> -->
		</td>	
		
	</tr>
	
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.BASICPENSIONBFCVP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtBasicPensionAmt" name="txtBasicPensionAmt" value="${trnPensionRqstHdrVO.basicPensionAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);onChangeBasicPension();calculateDPAndDAAmounts();calcReducedPension();" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="21"/>
	         <input type="hidden" id="hdnBasicPensionAmt" name="hdnBasicPensionAmt" value="${trnPensionRqstHdrVO.basicPensionAmount}"  style="text-align: right"/>
		 	<label id="mandtryFinal" class="mandatoryindicator">*</label>
		 </td>
		 
		 <td width="15%">
	       <fmt:message key="PPMT.DPRATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtDpRate" name="txtDpRate"  value="${trnPensionRqstHdrVO.dpPercent}" readonly="readonly" maxlength="6" onfocus="onFocus(this)"  onblur="onBlur(this);"   style="text-align: right"/>
		      <input type="hidden" id="hidDpRate" name="hidDpRate"  value="${trnPensionRqstHdrVO.dpPercent}"/>		      
		 </td>       
	</tr>
	 
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.DPAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtDpAmount" name="txtDpAmount" onfocus="onFocus(this)" readonly="readonly"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>	        
		 </td>
		  <td width="15%">
	       <fmt:message key="PPMT.FPFORTENYEARS" bundle="${pensionLabels}"></fmt:message>	      
	    </td>
		 <td width="35%">
		 <c:choose>
		 <c:when test="${trnPensionRqstHdrVO.isFp1datechange=='Y'}">
		  <input type="checkbox" id="chkFpForTenYears" name="chkFpForTenYears"  checked="checked" value="Y" onclick="setFP1AndFp2Date()"/>		
		 </c:when>
		 <c:otherwise>
		  <input type="checkbox" id="chkFpForTenYears" name="chkFpForTenYears" value="Y" disabled="disabled" onclick="setFP1AndFp2Date()"/>		
		 </c:otherwise>
		 </c:choose>	      
		 </td>  
	</tr>
	<tr>	 
		<td width="20%">
	       <fmt:message key="PPMT.COMMUTEDPNSN" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtCvpMonthly" name="txtCvpMonthly"  value="${trnPensionRqstHdrVO.cvpMonthlyAmount}"  onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calcReducedPension();" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="22"/>	        
		 </td>
		<td width="15%">
	       <fmt:message key="PPMT.BASICPNSNAFCVP" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtReducedPension" name="txtReducedPension" value="${trnPensionRqstHdrVO.reducedPension}" readonly="readonly" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>
		 
		<!--
		 <td width="15%">
	       <fmt:message key="PPMT.ADPAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtAdpAmount" name="txtAdpAmount" readonly="readonly" value="${trnPensionRqstHdrVO.adpAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>		     
		 </td>  
		 -->     
	</tr>	
	 <!--	
	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.DARATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtDaRate" name="txtDaRate" readonly="readonly" value="${trnPensionRqstHdrVO.daPercent}" maxlength="5" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>	        
		 </td>
		 
		 <td width="15%">
	       <fmt:message key="PPMT.DAAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtDaAmount" name="txtDaAmount" onfocus="onFocus(this)" readonly="readonly" onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>		      
		 </td>       
	</tr>	
	 -->	
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.FPAVAILABLEFLAG" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	      <c:choose>
	     <c:when test="${trnPensionRqstHdrVO.fpAvailableFlag == 'N'}" >
	    	<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioFpAvailableY" name="radioFpAvailable" value="Y" onclick="onClickFpAvailableFlag();"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioFpAvailableN" name="radioFpAvailable" value="N" checked="checked" onclick="onClickFpAvailableFlag();"/>
		</c:when>
		<c:otherwise>
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioFpAvailableY" name="radioFpAvailable" value="Y"  checked="checked" onclick="onClickFpAvailableFlag();"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioFpAvailableN" name="radioFpAvailable" value="N" onclick="onClickFpAvailableFlag();"/>
		</c:otherwise>
		</c:choose>
		 </td>
		 
		 <td width="15%">
	       
	     </td>
		 <td width="35%">
		    	     
		 </td>       
	</tr>	
		
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.FP1DATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtFp1Date" name="txtFp1Date" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.fp1Date}" />"
	         onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="26"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,FP1DTGRTCOMDT,'<');compareDates(txtDateOfCommencement,this,FP1DTGRTCOMDT,'=');"/>
	        <img id='imgFp1Date' src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtFp1Date",375,570)'style="cursor: pointer;" ${disabled}/>	
			<label id="mandtryFinal" class="mandatoryindicator">*</label>     
			 <input type="hidden" name="hidFp1Date1" id="hidFp1Date1"/> 
			 <input type="hidden" name="hidFp1Date2" id="hidFp1Date2"/> 
		 </td>
		 
		 <td width="15%">
	       <fmt:message key="PPMT.FP1AMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtFp1amount" name="txtFp1amount" value="${trnPensionRqstHdrVO.fp1Amount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="25"/>
		      <label id="mandtryFinal" class="mandatoryindicator">*</label>		     
		 </td>       
	</tr>	
	
	<tr>
	    <td width="20%">
	       <fmt:message key="PPMT.FP2DATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtFp2Date" name="txtFp2Date"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.fp2Date}" />"
	        onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" tabindex="23"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtFp1Date,this,FP2DTGRTFP1DT,'<');compareDates(txtFp1Date,this,FP2DTGRTFP1DT,'=');setFp1FrmFp2Date();"/>
	        <img id="imgFp2Date"  src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtFp2Date",375,570)'style="cursor: pointer;" $"""""{disabled}/>
			<label id="mandtryFinal" class="mandatoryindicator">*</label>	
					        <input type="hidden" name="hidFp2Date" id="hidFp2Date"/>         
		 </td>
		 
		 <td width="15%">
	       <fmt:message key="PPMT.FP2AMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtFp2amount" name="txtFp2amount" value="${trnPensionRqstHdrVO.fp2Amount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="24"/>
		      <label id="mandtryFinal" class="mandatoryindicator">*</label>		      
		 </td>       
	</tr>
	
	<tr>
    	
		<td width="20%">
	       <fmt:message key="PPMT.LINKEDPPOID" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtLinkedPpoId" name="txtLinkedPpoId" value="${trnPensionRqstHdrVO.linkedPpoNo}" onfocus="onFocus(this)"  onblur="onBlur(this);" tabindex="28" maxlength="50"/>	        
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.PEONALLOWANCE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtPeonAllowance" name="txtPeonAllowance" value="${trnPensionRqstHdrVO.peonAllowanceAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="29"/>
		</td>  
		     
	</tr>
		<tr>
    	
		<td width="20%">
	       <fmt:message key="PPMT.MEDALLOWANCE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtMedicalAllowance" name="txtMedicalAllowance" value="${trnPensionRqstHdrVO.medicalAllowenceAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="30"/>	        
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.PENSIONCUT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtPensionCut" name="txtPensionCut" value="${trnPensionRqstHdrVO.pensionCut}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="31"/>
		</td>      
	</tr>
		
		<tr>
    	
		<td width="20%">
	      <fmt:message key="PPMT.GALANTORYAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	      <input type="text" id="txtOther1" name="txtOther1" value="${trnPensionRqstHdrVO.other1}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="32"/>     
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.OTHERBEN" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtOther2" name="txtOther2" value="${trnPensionRqstHdrVO.other2}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="33"/>
		</td>      
	</tr>
	<tr>
    	<td width="20%">
	       <fmt:message key="PPMT.ARREARPAYOFDA" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtArrearOfDA" name="txtArrearOfDA"  onfocus="onFocus(this)" value = "${trnPnsnArrearDtlsForDA.totalDifferenceAmt}"  onblur="onBlur(this);setValidAmountFormat(this);validateArrearDtls('DA');" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="27" disabled="disabled"/>
		      <input type="hidden" id="hdnArrearDtlsIdForDA" name="hdnArrearDtlsIdForDA"  value = "${trnPnsnArrearDtlsForDA.pensionArrearDtlsId}" />
		      <input type="hidden" id="hdnArrearDtlsForDABillNo" name="hdnArrearDtlsForDABillNo"  value = "${trnPnsnArrearDtlsForDA.billNo}" />
		      <input type="hidden" id="hdnArrearDtlsForDAPaidFlag" name="hdnArrearDtlsForDAPaidFlag"  value = "${trnPnsnArrearDtlsForDA.paidFlag}" />
		      <input type="hidden" id="hdnArrearDtlsForDAAmt" name="hdnArrearDtlsForDAAmt"  value = "${trnPnsnArrearDtlsForDA.totalDifferenceAmt}" />
		      <input type="hidden" id="hdnArrearDtlsForDAPayMonth" name="hdnArrearDtlsForDAPayMonth"  value = "${fn:substring(trnPnsnArrearDtlsForDA.paymentFromYyyymm,4,6)}" />
		      <input type="hidden" id="hdnArrearDtlsForDAPayYear" name="hdnArrearDtlsForDAPayYear"  value = "${fn:substring(trnPnsnArrearDtlsForDA.paymentFromYyyymm,0,4)}" />
		</td>  
		<td width="15%">
	       <fmt:message key="PPMT.PAYINMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbArrearOfDAPayMonth" id="cmbArrearOfDAPayMonth" onchange = "validateArrearDtls('DA');" disabled="disabled"> 
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForDA.paymentFromYyyymm,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForDA.paymentFromYyyymm,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
	            						
					 </select>
					
		            <select name="cmbArrearOfDAPayYear" id="cmbArrearOfDAPayYear" onchange = "validateArrearDtls('DA');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(trnPnsnArrearDtlsForDA.paymentFromYyyymm,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
						
					</select>
		</td>  
		<td width="15%" style="display: none">
	       <fmt:message key="PPMT.OTHER3" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%" style="display: none">
	      	  <input type="text" id="txtOther3" name="txtOther3" value="${trnPensionRqstHdrVO.other3}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="34"/>      
		 </td>
		     
	</tr>
	<tr>
    	<td width="20%">
	       <fmt:message key="PPMT.ARREARPAYOF6PC" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtArrearOf6PC" name="txtArrearOf6PC" value="${trnPnsnArrearDtlsFor6PC.totalDifferenceAmt}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);validateArrearDtls('6PC');" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="27" disabled="disabled"/>
		      <input type="hidden" id="hdnArrearDtlsIdFor6PC" name="hdnArrearDtlsIdFor6PC"  value = "${trnPnsnArrearDtlsFor6PC.pensionArrearDtlsId}" />
		      <input type="hidden" id="hdnArrearDtlsFor6PCBillNo" name="hdnArrearDtlsFor6PCBillNo"  value = "${trnPnsnArrearDtlsFor6PC.billNo}" />
		      <input type="hidden" id="hdnArrearDtlsFor6PCPaidFlag" name="hdnArrearDtlsFor6PCPaidFlag"  value = "${trnPnsnArrearDtlsFor6PC.paidFlag}" />
		      <input type="hidden" id="hdnArrearDtlsFor6PCAmt" name="hdnArrearDtlsFor6PCAmt"  value = "${trnPnsnArrearDtlsFor6PC.totalDifferenceAmt}" />
		      <input type="hidden" id="hdnArrearDtlsFor6PCPayMonth" name="hdnArrearDtlsFor6PCPayMonth"  value = "${fn:substring(trnPnsnArrearDtlsFor6PC.paymentFromYyyymm,4,6)}" />
		      <input type="hidden" id="hdnArrearDtlsFor6PCPayYear" name="hdnArrearDtlsFor6PCPayYear"  value = "${fn:substring(trnPnsnArrearDtlsFor6PC.paymentFromYyyymm,0,4)}" />
		</td>  
		<td width="15%">
	       <fmt:message key="PPMT.PAYINMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbArrearOf6PCMonth" id="cmbArrearOf6PCMonth" onchange = "validateArrearDtls('6PC');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsFor6PC.paymentFromYyyymm,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsFor6PC.paymentFromYyyymm,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					
					 </select>
					
		            <select name="cmbArrearOf6PCYear" id="cmbArrearOf6PCYear" onchange = "validateArrearDtls('6PC');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(trnPnsnArrearDtlsFor6PC.paymentFromYyyymm,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
						
					</select>
		</td>  
		
	</tr>
	<tr>
    	<td width="20%">
	       <fmt:message key="PPMT.ARREARPAYOFPNSN" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtArrearPayOfPnsn" name="txtArrearPayOfPnsn" value="${trnPnsnArrearDtlsForPnsn.totalDifferenceAmt}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);validateArrearDtls('Pension');" onkeypress="amountFormat(this);"  style="text-align: right" disabled="disabled" tabindex="27"/>
		      <input type="hidden" id="hdnArrearDtlsIdForPnsn" name="hdnArrearDtlsIdForPnsn"  value = "${trnPnsnArrearDtlsForPnsn.pensionArrearDtlsId}" />
		      <input type="hidden" id="hdnArrearDtlsForPnsnBillNo" name="hdnArrearDtlsForPnsnBillNo"  value = "${trnPnsnArrearDtlsForPnsn.billNo}" />
		      <input type="hidden" id="hdnArrearDtlsForPnsnPaidFlag" name="hdnArrearDtlsForPnsnPaidFlag"  value = "${trnPnsnArrearDtlsForPnsn.paidFlag}" />
		      <input type="hidden" id="hdnArrearDtlsForPnsnAmt" name="hdnArrearDtlsForPnsnAmt"  value = "${trnPnsnArrearDtlsForPnsn.totalDifferenceAmt}" />
		      <input type="hidden" id="hdnArrearDtlsForPnsnPayMonth" name="hdnArrearDtlsForPnsnPayMonth"  value = "${fn:substring(trnPnsnArrearDtlsForPnsn.paymentFromYyyymm,4,6)}" />
		      <input type="hidden" id="hdnArrearDtlsForPnsnPayYear" name="hdnArrearDtlsForPnsnPayYear"  value = "${fn:substring(trnPnsnArrearDtlsForPnsn.paymentFromYyyymm,0,4)}" />
		</td>  
		<td width="15%">
	       <fmt:message key="PPMT.PAYINMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbArrearOfPnsnMonth" id="cmbArrearOfPnsnMonth" onchange = "validateArrearDtls('Pension');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForPnsn.paymentFromYyyymm,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForPnsn.paymentFromYyyymm,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					
					 </select>
					
		            <select name="cmbArrearOfPnsnYear" id="cmbArrearOfPnsnYear" onchange = "validateArrearDtls('Pension');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(trnPnsnArrearDtlsForPnsn.paymentFromYyyymm,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					</select>
		</td>  
		
	</tr>
	<tr>
    	<td width="20%">
	       <fmt:message key="PPMT.DIFFOFCOMTPNSN" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtArrearDiffOfComtPnsn" name="txtArrearDiffOfComtPnsn" value="${trnPnsnArrearDtlsForComtPnsn.totalDifferenceAmt}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);validateArrearDtls('ComtPension');" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="27" disabled="disabled"/>
		      <input type="hidden" id="hdnArrearDtlsIdForComtPnsn" name="hdnArrearDtlsIdForComtPnsn"  value = "${trnPnsnArrearDtlsForComtPnsn.pensionArrearDtlsId}" />
		      <input type="hidden" id="hdnArrearDtlsForComtPnsnBillNo" name="hdnArrearDtlsForComtPnsnBillNo"  value = "${trnPnsnArrearDtlsForComtPnsn.billNo}" />
		      <input type="hidden" id="hdnArrearDtlsForComtPnsnPaidFlag" name="hdnArrearDtlsForComtPnsnPaidFlag"  value = "${trnPnsnArrearDtlsForComtPnsn.paidFlag}" />
		      <input type="hidden" id="hdnArrearDtlsForComtPnsnAmt" name="hdnArrearDtlsForComtPnsnAmt"  value = "${trnPnsnArrearDtlsForComtPnsn.totalDifferenceAmt}" />
		      <input type="hidden" id="hdnArrearDtlsForComtPnsnPayMonth" name="hdnArrearDtlsForComtPnsnPayMonth"  value = "${fn:substring(trnPnsnArrearDtlsForComtPnsn.paymentFromYyyymm,4,6)}" />
		      <input type="hidden" id="hdnArrearDtlsForComtPnsnPayYear" name="hdnArrearDtlsForComtPnsnPayYear"  value = "${fn:substring(trnPnsnArrearDtlsForComtPnsn.paymentFromYyyymm,0,4)}" />
		</td>  
		<td width="15%">
	       <fmt:message key="PPMT.PAYINMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbArrearDiffOfComtPnsnMonth" id="cmbArrearDiffOfComtPnsnMonth" onchange = "validateArrearDtls('ComtPension');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForComtPnsn.paymentFromYyyymm,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForComtPnsn.paymentFromYyyymm,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					
					 </select>
					
		            <select name="cmbArrearDiffOfComtPnsnYear" id="cmbArrearDiffOfComtPnsnYear" onchange = "validateArrearDtls('ComtPension');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(trnPnsnArrearDtlsForComtPnsn.paymentFromYyyymm,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					</select>
		</td>  
		
	</tr>
	<tr>
    	<td width="20%">
	       <fmt:message key="PPMT.ANYOTHERDIFF" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <input type="text" id="txtArrearAnyOtherDiff" name="txtArrearAnyOtherDiff" value="${trnPnsnArrearDtlsForOther.totalDifferenceAmt}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);validateArrearDtls('Other');" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="27" disabled="disabled"/>
		      <input type="hidden" id="hdnArrearDtlsIdForOther" name="hdnArrearDtlsIdForOther"  value = "${trnPnsnArrearDtlsForOther.pensionArrearDtlsId}" />
		      <input type="hidden" id="hdnArrearDtlsForOtherBillNo" name="hdnArrearDtlsForOtherBillNo"  value = "${trnPnsnArrearDtlsForOther.billNo}" />
		      <input type="hidden" id="hdnArrearDtlsForOtherPaidFlag" name="hdnArrearDtlsForOtherPaidFlag"  value = "${trnPnsnArrearDtlsForOther.paidFlag}" />
		      <input type="hidden" id="hdnArrearDtlsForOtherAmt" name="hdnArrearDtlsForOtherAmt"  value = "${trnPnsnArrearDtlsForOther.totalDifferenceAmt}" />
		      <input type="hidden" id="hdnArrearDtlsForOtherPayMonth" name="hdnArrearDtlsForOtherPayMonth"  value = "${fn:substring(trnPnsnArrearDtlsForOther.paymentFromYyyymm,4,6)}" />
		      <input type="hidden" id="hdnArrearDtlsForOtherPayYear" name="hdnArrearDtlsForOtherPayYear"  value = "${fn:substring(trnPnsnArrearDtlsForOther.paymentFromYyyymm,0,4)}" />
		</td>  
		<td width="15%">
	       <fmt:message key="PPMT.PAYINMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		<td width="35%">
		      <select name="cmbArrearAnyOtherDiffMonth" id="cmbArrearAnyOtherDiffMonth" onchange = "validateArrearDtls('Other');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForOther.paymentFromYyyymm,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(trnPnsnArrearDtlsForOther.paymentFromYyyymm,5,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${MonthList.id}">
								<c:out value="${MonthList.desc}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					
					 </select>
					
		            <select name="cmbArrearAnyOtherDiffYear" id="cmbArrearAnyOtherDiffYear" onchange = "validateArrearDtls('Other');" disabled="disabled">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(trnPnsnArrearDtlsForOther.paymentFromYyyymm,0,4)}">
				              <option selected="selected" value="${YearList.desc}"><c:out value="${YearList.desc}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${YearList.desc}">
								<c:out value="${YearList.desc}"></c:out>									
						 </option>
				         </c:otherwise>
				         </c:choose>
	            		</c:forEach>
					</select>
		</td>  
		
	</tr>
	<tr>
		<td width="20%">
 		</td>
		<td width="35%">
			<hdiits:button id="btnPayHist" name="btnPayHist" type="button" captionid="PPMT.PAYHIST" onclick='generatePaymentHistory();' bundle="${pensionLabels}" classcss="bigbutton"  tabindex="35"/>
		</td>   
	    <td width="15%">
 		</td>
		<td width="35%">
			<hdiits:button id="btnCurrentEntlmt" name="btnCurrentEntlmt" type="button" captionid="PPMT.CURENTITLMENT" onclick='openBillDtls();' bundle="${pensionLabels}" classcss="bigbutton"  tabindex="36"/>
		</td>   
	</tr>
	<!--

	<tr>
	    <td width="15%">
	       <fmt:message key="PPMT.GROSSAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="hidden" id="txtGrossAmount" name="txtGrossAmount" readonly="readonly" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>
		 
		 <td width="15%" >
	       <fmt:message key="PPMT.OTHRRECOVERY" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="hidden" id="txtOtherRecovery" name="txtOtherRecovery" onfocus="onFocus(this)" readonly="readonly" onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>       
	</tr>
	
	<tr>
	    <td width="15%" >
	       <fmt:message key="PPMT.NETAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="hidden" id="txtNetAmount" name="txtNetAmount" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>
		 <td width="15%">
		 </td>
		 <td width="35%">
		 </td>		 		      
	</tr>
	  -->
</table>
</fieldset>





	<!-- 
	<tr>
		<td width="15%">
			<fmt:message key="PPMT.CVPEFCTFROMDATE" bundle="${pensionLabels}"></fmt:message>
	     
	    </td>
		 <td width="35%">
		 	  <input type="text" id="txtCvpEffectFromDate" name="txtCvpEffectFromDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.cvpEffectiveDate}" />"
		       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
		      <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtCvpEffectFromDate",375,570)'style="cursor: pointer;" ${disabled}/>
		     
		 </td>
		 
		<td width="15%">
	         <fmt:message key="PPMT.CVPRESDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		       <input type="text" id="txtCvpRestorationDate" name="txtCvpRestorationDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.cvpRestorationDate}" />"
		      onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
		      <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtCvpRestorationDate",375,570)'style="cursor: pointer;" ${disabled}/>
		 </td>  		 		 
	</tr>
	
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.DCRGDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%">	
	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.DCRGPAID" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		 <c:choose>
		 <c:when test="${trnPensionRqstHdrVO.dcrgPaidFlag == 'Y'}">
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioDcrgPaid" name="radioDcrgPaid" value="Y" checked="checked" onclick="enableDCRGDtls(this);"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioDcrgPaid" name="radioDcrgPaid" value="N" onclick="enableDCRGDtls(this);"/>
		 </c:when>
		 <c:otherwise>
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioDcrgPaid" name="radioDcrgPaid" value="Y" onclick="enableDCRGDtls(this);"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioDcrgPaid" name="radioDcrgPaid" value="N" onclick="enableDCRGDtls(this);" checked="checked"/>
		 </c:otherwise>
		 </c:choose>
		
		 </td>
		
	   		       
	</tr>
	<tr>
	 	<td width="20%">
	       <fmt:message key="PPMT.DCRGORDER" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtDcrgOrder" name="txtDcrgOrder" value="${trnPensionRqstHdrVO.dcrgOrderNo}" onfocus="onFocus(this)" onblur="onBlur(this);" disabled="disabled"/>
		 </td>		 
		 <td width="15%">
	 	<fmt:message key="PPMT.DCRGPAIDDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	     <input type="text" id="txtDcrgPaidDate" name="txtDcrgPaidDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.paidDate}" />"
		      onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,DCRGPAIDDTCOMDT,'<')"/>
		      <img id="imgDcrgPaidDate" src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtDcrgPaidDate",375,570)'style="cursor: pointer;"/>
	    </td>
	    </tr>
	    <tr>
		<td width="20%">
	 	 <fmt:message key="PPMT.DCRGVOUCHERNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    <input type="text" id="txtDcrgVoucherNo" name="txtDcrgVoucherNo"  onfocus="onFocus(this)"  onblur="onBlur(this);" disabled="disabled"/>
	     
	    </td>
	    <td width="15%">
	       <fmt:message key="PPMT.DCRGVOUCHERDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtDcrgVoucherDate" name="txtDcrgVoucherDate" disabled="disabled"
		       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
		      <img id="imgCvpPaidDate" src='images/CalendarImages/ico-calendar.gif'  
					        onClick='window_open("txtDcrgVoucherDate",375,570)'style="cursor: pointer;" />	    
		 </td>
	</tr>
	<tr>
		
		<td width="20%">
	       <fmt:message key="PPMT.DCRGAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtTotalDcrgAmount" name="txtTotalDcrgAmount" value="${trnPensionRqstHdrVO.totalDcrgAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" disabled="disabled"/>
		 </td>
		 <td width="15%">
	       <fmt:message key="PPMT.DCRGPAYABLEAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtDcrgPayableAmount" name="txtDcrgPayableAmount" value="${trnPensionRqstHdrVO.dcrgAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" disabled="disabled"/>
		 </td>
	<!-- 
		<td width="15%">
	       <fmt:message key="PPMT.DCRGPAIDDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtDcrgPaidDate" name="txtDcrgPaidDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.paidDate}" />"
		      onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,DCRGPAIDDTCOMDT,'<')"/>
		      <img id="imgDcrgPaidDate" src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtDcrgPaidDate",375,570)'style="cursor: pointer;"/>
		 </td>  --> 
	</tr>	
	<!--  
	<script>
		document.getElementById("imgDcrgPaidDate").disabled=true;
	</script> 
	<c:if test="${trnPensionRqstHdrVO.dcrgPaidFlag=='Y'}">
	<script>
		document.getElementById("txtDcrgPaidDate").disabled=false;
		document.getElementById("imgDcrgPaidDate").disabled=false;
	</script>
	</c:if>
	<tr>
	 	<td width="20%">
	    </td>
		 <td width="35%">
		 </td>
	    <td width="15%">
	    </td>
	    <td width="35%">
	        <hdiits:button id="btnDcrgHst" name="btnDcrgHst" type="button" captionid="PPMT.DCRGHISTORY" onclick='showDcrgHistory();' bundle="${pensionLabels}" classcss="bigbutton"  />	    
		 </td>
	</tr>
  </table>
</fieldset>
<c:if test="${trnPensionRqstHdrVO.dcrgPaidFlag == 'Y'}">
<script>
document.getElementById("txtDcrgOrder").disabled=false;
document.getElementById("txtDcrgPaidDate").disabled=false;
document.getElementById("txtDcrgVoucherNo").disabled=false;
document.getElementById("txtDcrgVoucherDate").disabled=false;
document.getElementById("txtTotalDcrgAmount").disabled=false;
document.getElementById("txtDcrgPayableAmount").disabled=false;
</script>
</c:if>
--> 
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PROVPENDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%">

	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.PROVPEN" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioProvPnsnY" name="radioProvPnsn" value="Y" onclick="ProvPenEnableDisableAddRow(this);" tabindex="38"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioProvPnsnN" name="radioProvPnsn" value="N" checked="checked" onclick="ProvPenEnableDisableAddRow(this);"/>
		 </td>		 
		 </tr>
	</table>
	
	<hdiits:button name="btnProvPnsnDtlsAddRow" id="btnProvPnsnDtlsAddRow" type="button" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="provPnsnDtlsTableAddRow();" readonly="true" tabindex="39"/>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" name="hidProvPnsnGridSize" id="hidProvPnsnGridSize" value="0">
<table id="tblProvPnsnDtls" border="1" width="99%">
	   <tr class="datatableheader" >
			<td width="9%" class="datatableheader"><fmt:message key="PPMT.SANCAUTHNAME" bundle="${pensionLabels}" ></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>	
			<td width="9%" class="datatableheader"><fmt:message key="PPMT.SANCAUTHNO" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message key="PPMT.SANCDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message key="PPMT.APPLFROMDATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="10%" class="datatableheader"><fmt:message key="PPMT.APPLTODATE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="6%" class="datatableheader"><fmt:message key="PPMT.BILLTYPE" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="8%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="8%" class="datatableheader"><fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message></td>
			<td width="6%" class="datatableheader"><fmt:message key="PPMT.CHARGERVOTED" bundle="${pensionLabels}"></fmt:message><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
			<td width="1%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		</tr>
		
		<c:choose>

			<c:when test="${resValue.TrnProvisionalPensionDtlsVO != null}">
	
				<c:forEach var="trnProvisionalPensionDtlsVO" items="${resValue.TrnProvisionalPensionDtlsVO}" varStatus="Counter">					
					<tr>
						<td class="tds" align="center">
							<input type="text" name="txtSancAuthName" id="txtSancAuthName${Counter.index}" size="15" value="${trnProvisionalPensionDtlsVO.provPensionSanctionAuthority}"
									onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="60"/>
							<input type="hidden" name="hdnProvPnsnDtlsId" id="hdnProvPnsnDtlsId${Counter.index}" value="${trnProvisionalPensionDtlsVO.provisionalPensionDtlsId}"/>
						</td>						
						<td class="tds" align="center">
							<input type="text" name="txtSancAuthNo" id="txtSancAuthNo${Counter.index}" size="15" value="${trnProvisionalPensionDtlsVO.provPensionAuthorityNo}"
									onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="20" />					
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtSanctionedDate" id="txtSanctionedDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnProvisionalPensionDtlsVO.provPensionAuthorityDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtSanctionedDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtApplnFromDate" id="txtApplnFromDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnProvisionalPensionDtlsVO.commensionDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtApplnFromDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtApplnToDate" id="txtApplnToDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnProvisionalPensionDtlsVO.provPensionToDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtApplnToDate${Counter.index}",375,570)'style="cursor: pointer;"/>
						</td>
						
						<td class="tds" align="center">
							<select name="cmbBillType" id="cmbBillType${Counter.index}" >
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>															            
						        <c:choose>
			    				<c:when test="${trnProvisionalPensionDtlsVO.billType == 'Pension'}">
			    					<option value="<fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/></option>
			   						<option value="<fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/></option>			    					
			    				</c:when>
			    				<c:when test="${trnProvisionalPensionDtlsVO.billType == 'Gratuity'}">			    								   						
			   						<option value="<fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/></option>
			   						<option value="<fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/>"selected="selected"><fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/></option>			    					
			    				</c:when>	
			    				<c:otherwise>
			    					<option value="<fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.PENSION" bundle="${pensionConstants}"/></option>
			   						<option value="<fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.GRATUITY" bundle="${pensionConstants}"/></option>
			    				</c:otherwise>
			    				</c:choose>					        
							</select>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtPaidAmount" id="txtPaidAmount${Counter.index}" size="15" value="${trnProvisionalPensionDtlsVO.basicPensionAmount}"
							onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>					
						</td>
						
						<td class="tds" align="center">							
							<input type="text" name="txtProVoucherNo" id="txtProVoucherNo${Counter.index}" size="15" value="${trnProvisionalPensionDtlsVO.gratuityVoucherNo}" maxlength="20"/>					
						</td>
					
						<td class="tds" align="center">							
							<input type="text" name="txtProVoucherDate" id="txtProVoucherDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnProvisionalPensionDtlsVO.gratuityVoucherDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtProVoucherDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<td class="tds" align="center">						
							<select name="cmbChargedVoted" id="cmbChargedVoted${Counter.index}" >				
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>															            
						        <c:choose>
			    					<c:when test="${trnProvisionalPensionDtlsVO.voucherChargedVotedFlag == 'C '}">
			    						<option value="C" selected="selected"><fmt:message key="PPMT.CHARGED" bundle="${pensionConstants}"/></option>
			   							<option value="V"><fmt:message key="PPMT.VOTED" bundle="${pensionConstants}"/></option>			    					
			    					</c:when>
			    					<c:when test="${trnProvisionalPensionDtlsVO.voucherChargedVotedFlag == 'V '}">			    					
			   							<option value="C" ><fmt:message key="PPMT.CHARGED" bundle="${pensionConstants}"/></option>
			   							<option value="V" selected="selected"><fmt:message key="PPMT.VOTED" bundle="${pensionConstants}"/></option>			    					
			    					</c:when>
			    					<c:otherwise>
			    						<option value="C" ><fmt:message key="PPMT.CHARGED" bundle="${pensionConstants}"/></option>
			   							<option value="V" ><fmt:message key="PPMT.VOTED" bundle="${pensionConstants}"/></option>			    			
			    					</c:otherwise>	
			    				</c:choose>							        
							</select>
						</td>
						
						<td class="tds" align="center"><img name="Image" id="Image${Counter.index}"
							src="images/CalendarImages/DeleteIcon.gif"
							onclick="RemoveTableRow(this, 'tblProvPnsnDtls');"/>
						</td>						
					</tr>	
					<script>				    
							document.getElementById("hidProvPnsnGridSize").value = Number('${Counter.index}') + 1;					
					</script>
											
				</c:forEach>
				
			</c:when>
			
		</c:choose>	
				
</table>	
	
</div>
	
</fieldset>	
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PNSHMNTCUT" bundle="${pensionLabels}"></fmt:message></b> </legend>
	<hdiits:button id="btnAddRowPunishmntCut" type="button" name="btnAddRowPunishmntCut" captionid="PPMT.ADDROW" bundle="${pensionLabels}"
	onclick="punishmentCutAddRow();" tabindex="40"/>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 70%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">			
<input type="hidden" id="hidPnshmntCutSize" name="hidPnshmntCutSize" value="0" />
<table id="tblPnshmntCut" border="1" width="98%">
	<tr style="width: 100%" class="datatableheader">
		<td width="14%" class="datatableheader"><fmt:message
			key="PPMT.AMOUNT" bundle="${pensionLabels}"></fmt:message></td>
		<td width="14%" class="datatableheader"><fmt:message
			key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="14%" class="datatableheader"><fmt:message
			key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="2%" class="datatableheader"><fmt:message
			key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	
	<c:choose>

			<c:when test="${resValue.TrnPensionCutDtlsVO != null}">
	
				<c:forEach var="trnPensionCutDtlsVO" items="${resValue.TrnPensionCutDtlsVO}" varStatus="Counter">					
					<tr>
						<td class="tds" align="center">
							<input type="text" name="txtPnshmntAmount" id="txtPnshmntAmount${Counter.index}" size="15" value="${trnPensionCutDtlsVO.amount}"
							onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
							<input type="hidden" name="hdnPensionCutDtlsId" id="hdnPensionCutDtlsId${Counter.index}" value="${trnPensionCutDtlsVO.pensionCutDtlsId}"/>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtPnshmntFromDate" id="txtPnshmntFromDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionCutDtlsVO.fromDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtPnshmntFromDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
						</td>
						
						<td class="tds" align="center">
							<input type="text" name="txtPnshmntToDate" id="txtPnshmntToDate${Counter.index}" maxlength="10" size="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionCutDtlsVO.toDate}" />"
	       					onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src='images/CalendarImages/ico-calendar.gif' 
					        	onClick='window_open("txtPnshmntToDate${Counter.index}",375,570)'style="cursor: pointer;"/>
						</td>
						
						<td class="tds" align="center"><img name="Image" id="Image${Counter.index}"
							src="images/CalendarImages/DeleteIcon.gif"
							onclick="RemoveTableRow(this, 'tblPnshmntCut');"/>
						</td>
					</tr>
					<script>				    
							document.getElementById("hidPnshmntCutSize").value = Number('${Counter.index}') + 1;					
					</script>
				</c:forEach>
			</c:when>
	</c:choose>
</table>
</div>
</fieldset>
<jsp:include page="/WEB-INF/jsp/pensionpay/AllocationRevisionTab.jsp" />

<div id="divCommutationHst">
</div>
<div id="divDcrgHst">
</div>
<script>
calculateDPAndDAAmounts();
//calcGrossAmount();
onChangeROPType();
//calcReducedPension();
</script>
<c:if test="${mstPensionerDtlsVO.paymentScheme == 'M'}">
<script>
document.getElementById("cmbBankBranch").disabled=true;
document.getElementById("cmbBankCode").disabled=true;
</script>
</c:if>
<c:if test="${trnPensionRqstHdrVO.dpFlag == 'Y'}" >
 <script>
 document.getElementById("radioDpMergeY").checked=true;
 </script>
</c:if>
 <c:if test="${trnPensionRqstHdrVO.dpFlag == 'N'}" >
 <script>
 document.getElementById("radioDpMergeN").checked=true;
 </script>
 </c:if>
 <c:if test="${trnPensionRqstHdrVO.isFp1datechange=='Y'}">
 <script>
 document.getElementById("chkFpForTenYears").checked=true;
 </script>
 </c:if>
 
 <script>
 if(document.getElementById('tblProvPnsnDtls').rows.length > 1)
 {
 	document.getElementById("radioProvPnsnY").checked=true; 
 	document.getElementById("btnProvPnsnDtlsAddRow").disabled=false;
 }
 </script>
 
 <c:if test="${resValue.lStrShowCaseFor == '15' || resValue.lStrShowCaseFor == '10'}">
  <script>
  	document.getElementById("txtArrearOfDA").disabled = false;
	document.getElementById("cmbArrearOfDAPayMonth").disabled = false;
	document.getElementById("cmbArrearOfDAPayYear").disabled = false;
	
	document.getElementById("txtArrearOf6PC").disabled = false;
	document.getElementById("cmbArrearOf6PCMonth").disabled = false;
	document.getElementById("cmbArrearOf6PCYear").disabled = false;
	
	document.getElementById("txtArrearPayOfPnsn").disabled = false;
	document.getElementById("cmbArrearOfPnsnMonth").disabled = false;
	document.getElementById("cmbArrearOfPnsnYear").disabled = false;
	
	document.getElementById("txtArrearDiffOfComtPnsn").disabled = false;
	document.getElementById("cmbArrearDiffOfComtPnsnMonth").disabled = false;
	document.getElementById("cmbArrearDiffOfComtPnsnYear").disabled = false;
	
	document.getElementById("txtArrearAnyOtherDiff").disabled = false;
	document.getElementById("cmbArrearAnyOtherDiffMonth").disabled = false;
	document.getElementById("cmbArrearAnyOtherDiffYear").disabled = false;
 </script>
 </c:if>
 <c:if test="${trnPensionRqstHdrVO.fpAvailableFlag == 'N'}" >
 <script>
 document.getElementById("txtFp1Date").disabled = true;
 document.getElementById("txtFp1amount").disabled = true;
 document.getElementById("txtFp2Date").disabled = true;
 document.getElementById("txtFp2amount").disabled = true;
 document.getElementById("imgFp1Date").disabled = true;
 document.getElementById("imgFp2Date").disabled = true;
 </script>
 </c:if>
 
<ajax:select baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchListFromBankCode" source="cmbBankCode" target="cmbBankBranch" parameters="bankCode={cmbBankCode}" ></ajax:select>