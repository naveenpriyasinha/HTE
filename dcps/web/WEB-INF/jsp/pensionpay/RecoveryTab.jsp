<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/RecoveryTab.js"></script>
<script type="text/javascript">
LISTADVANCE='';
</script>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>
<c:set var="RecoveryDtlsVO" value="${resValue.RecoveryDtls}"></c:set>
<c:set var="currentDate" value="${resValue.CurrDate}"/>

<c:forEach var="AdvanceType" items="${resValue.lLstTypeOfAdvance}" >
	<script> LISTADVANCE += '<option value="${AdvanceType.lookupName}"> ${AdvanceType.lookupName}</option>';</script>
</c:forEach>
<!--  <table width="100%">	
	<tr>	
		 <td width="15%">
	       <fmt:message key="PPMT.RECOVERY" bundle="${pensionLabels}"></fmt:message> 
	     </td>
		 <td width="35%">
		 <c:choose>
		 <c:when test="${trnPensionRqstHdrVO.recoveryFlag == 'Y'}">
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecovery" name="radioRecovery" value="Y" checked="checked"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecovery" name="radioRecovery" value="N" />
		</c:when>
		<c:otherwise>
		   <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecovery" name="radioRecovery" value="Y" />
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecovery" name="radioRecovery" value="N" checked="checked"/>
		</c:otherwise>
		</c:choose>
		 </td>
		 </tr>
 </table>
-->		
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.ADVANCEDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
		 <table>
		 <tr>
		 <td width="15%">
	       <fmt:message key="PPMT.BALANCEOFADVNC" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="35%">
		 <c:choose>
		 <c:when test="${resValue.AdvanceDtls !=null}">
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioBalanceOfAdvanceY" name="radioBalanceOfAdvance" value="Y" onclick="addAdvanceDtl(this)" checked="checked"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioBalanceOfAdvanceN" name="radioBalanceOfAdvance" value="N" onclick="addAdvanceDtl(this)"/>
			</c:when>
			<c:otherwise>
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioBalanceOfAdvanceY" name="radioBalanceOfAdvance" value="Y" onclick="addAdvanceDtl(this)"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio" id="radioBalanceOfAdvanceN" name="radioBalanceOfAdvance" value="N" onclick="addAdvanceDtl(this)" checked="checked"/>
			</c:otherwise>
		 </c:choose>
		 </td>
	</tr>
</table>	


<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidAdvanceGridSize" name="hidAdvanceGridSize" value="0"/>
<hdiits:button id="btnAdvDtlsAddRow" type="button" name="btnAdvDtlsAddRow" bundle="${pensionLabels}" captionid="PPMT.ADDROW" onclick="advanceDtlsTableAddRow();" readonly="true"/>

<table id="tblAdvanceDtls" border="1">
		<tr style="width: 100%" class="datatableheader" >
			   <td width="10%" class="datatableheader"><fmt:message key="PPMT.MAJORHEAD" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}" ></fmt:message></td>	
			   <td width="8%" class="datatableheader"><fmt:message key="PPMT.NOOFINSTLMNT" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="20%" class="datatableheader"><fmt:message key="PPMT.STARTMONTHYEAR" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="20%" class="datatableheader"><fmt:message key="PPMT.ENDMONTHYEAR" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="40%" class="datatableheader"><fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="3%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		</tr>
		<c:choose>

		<c:when test="${resValue.AdvanceDtls !=null}">

			<c:forEach var="AdvanceDtlsVO"
				items="${resValue.AdvanceDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnAdvRecoveryDtlsId" id="hdnAdvRecoveryDtlsId${Counter.index}" value="${AdvanceDtlsVO.trnPensionRecoveryDtlsId}"/>
					<input type="text" name="txtAdvMajorHead" id="txtAdvMajorHead${Counter.index}" value="${AdvanceDtlsVO.mjrhdCode}" onfocus="onFocus(this)"  onblur="onBlur(this);validateMajorHead(this);"/>
					<!-- <select name="cmbTypeOfAdvance" id="cmbTypeOfAdvance${Counter.index}"  style="width: 50%"> 
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="AdvanceType" items="${resValue.lLstTypeOfAdvance}">
				         <c:choose>
				            <c:when test="${AdvanceType.lookupName == AdvanceDtlsVO.recoveryType}">
				         <option selected="selected" value="${AdvanceType.lookupName}"><c:out value="${AdvanceType.lookupName}"></c:out></option>
				         </c:when>
				         <c:otherwise>
				         <option value="${AdvanceType.lookupName}">
								<c:out value="${AdvanceType.lookupName}"></c:out>									
							</option>
				         </c:otherwise>
				         </c:choose>
	            	</c:forEach>
					</select> -->
					<input type="hidden" name="hdnRecoveryId" id="hdnRecoveryId${Counter.index}" value="${AdvanceDtlsVO.trnPensionRecoveryDtlsId}">
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtAdvanceAmount" id="txtAdvanceAmount${Counter.index}" value="${AdvanceDtlsVO.amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calAdvanceAmtTotal(this,'txtAdvanceAmount')" onkeypress="amountFormat(this);"  style="text-align: right"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtNoOfInstallment" id="txtNoOfInstallment${Counter.index}" value="${AdvanceDtlsVO.noOfInstallments}" size="10" onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)"/>
					</td>
					<td class="tds" align="center">
					
					<select name="cmbAdvanceStartMonth" id="cmbAdvanceStartMonth${Counter.index}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(AdvanceDtlsVO.fromMonth,4,6)}">
				       	     <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(AdvanceDtlsVO.fromMonth,5,6)}">
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
					
		            <select name="cmbAdvanceStartYear" id="cmbAdvanceStartYear${Counter.index}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(AdvanceDtlsVO.fromMonth,0,4)}">
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
					<td class="tds" align="center">
					
					<select name="cmbAdvanceEndMonth" id="cmbAdvanceEndMonth${Counter.index}" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				          <c:when test="${MonthList.id == fn:substring(AdvanceDtlsVO.toMonth,4,6)}">
				            <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				          </c:when>
				          <c:when test="${MonthList.id == fn:substring(AdvanceDtlsVO.toMonth,5,6)}">
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
		            <select name="cmbAdvanceEndYear" id="cmbAdvanceEndYear${Counter.index}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				            <c:when test="${YearList.desc == fn:substring(AdvanceDtlsVO.toMonth,0,4)}">
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
					<td class="tds" align="center">
					<select name="cmbAdvSchemeCode" id="cmbAdvSchemeCode${Counter.index}" onchange="getSchemeNameFromSchemeCode(this);" style="width: 40%"> 
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="${AdvanceDtlsVO.schemeCode}" selected="selected">${AdvanceDtlsVO.schemeCode}</option>
					</select>
					<input type="text" name="txtAdvSchemeName" id="txtAdvSchemeName${Counter.index}" value = "${AdvanceDtlsVO.schemeName}" readonly="readonly"/>
					<!-- <input type="text" name="txtAdvSchemeCode" id="txtAdvSchemeCode${Counter.index}" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${AdvanceDtlsVO.schemeCode}" size="12"/>
					<a href="#" id="txtAdvSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a> -->
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblAdvanceDtls');"/></td>
				</tr>
				<script>
				    
					document.getElementById("hidAdvanceGridSize").value = Number('${Counter.index}') + 1;
					
				</script>
					
			</c:forEach>
		</c:when>
		</c:choose>
			
</table>

 <script>
 if(document.getElementById('tblAdvanceDtls').rows.length > 1)
 {
 	document.getElementById("btnAdvDtlsAddRow").disabled=false;
 	document.getElementById("radioBalanceOfAdvanceY").checked=true;
 }
 else
 {
	 document.getElementById("btnAdvDtlsAddRow").disabled=true;
	 document.getElementById("radioBalanceOfAdvanceN").checked=true;
 }
 </script>    	
</div>

<table width="80%">
	<tr>
		<td width="20%" align="right" class="datatableheader">
		<fmt:message key="PPMT.BALANCEOFADVNC" bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left">
		<input type="text" id="txtBalanceOfAdvance" name="txtBalanceOfAdvance"  readonly="readonly" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		</td>
		<td width="20%" align="right">
		
		</td>
		</tr>
	</table>
	</fieldset>
	 <script>
	 calAdvanceAmtTotal(this,'txtAdvanceAmount');
	 </script>
	 <br/>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.RECOVERY" bundle="${pensionLabels}"></fmt:message></b> </legend>		
<table width="100%">	
	<tr>	
		 <td width="40%">
	       <fmt:message key="PPMT.OVERPAYMENT" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="10%">
		    <input type="text" id="txtOverPaymentAmt" name="txtOverPaymentAmt" value="${resValue.OverPayAmt}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>
		 <td width="10%" align="center">
		 	<fmt:message key="PPMT.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
		 </td>
		 <td width="21%" align="left">
			 <select name="cmbOverPayFromMonth" id="cmbOverPayFromMonth" >
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 	<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(resValue.OverPayFromMonth,4,6)}">
				            <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(resValue.OverPayFromMonth,5,6)}">
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
			 <select name="cmbOverPayFromYear" id="cmbOverPayFromYear" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				            <c:when test="${YearList.desc == fn:substring(resValue.OverPayFromMonth,0,4)}">
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
	     <td width="7%"  align="left">
	       <fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
	     </td>
	     <td width="30%"  align="left">
	     <input type="text" name="txtOverPaySchemeCode" id="txtOverPaySchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${resValue.OverPaySchemeCode}" size="12"/>
	     <a href="#" onclick="showSchemeCodeList('txtOverPaySchemeCode');"><img src="images/search.gif" /></a>
	     </td>
	</tr>
	
	
	
	
	<tr>	 
		 <td width="40%">
	       <fmt:message key="PPMT.ARREAROFLICNCFEE" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="10%">
		    <input type="text" id="txtArrearOfLicenseFee" name="txtArrearOfLicenseFee" onfocus="onFocus(this)"  value="${resValue.ArrearOfFee}" onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>	
		<td width="10%" align="center">
		 	<fmt:message key="PPMT.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
		 </td>
		 <td width="21%" align="left">
		 	 <select name="cmbArrearFromMonth" id="cmbArrearFromMonth" >
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 	<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(resValue.ArrearOfFeeFromMonth,4,6)}">
				            <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(resValue.ArrearOfFeeFromMonth,5,6)}">
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
			 <select name="cmbArrearFromYear" id="cmbArrearFromYear" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				            <c:when test="${YearList.desc == fn:substring(resValue.ArrearOfFeeFromMonth,0,4)}">
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
		 <td width="7%"  align="left">
	       <fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
	     </td>
	     <td width="30%"  align="left">
	     <input type="text" name="txtArrearSchemeCode" id="txtArrearSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${resValue.ArrearSchemeCode}" size="12"/>
	     <a href="#" onclick="showSchemeCodeList('txtArrearSchemeCode');"><img src="images/search.gif" /></a>
	     </td> 
	</tr>
	
	
	<tr>	 
		 <td width="40%">
	       <fmt:message key="PPMT.AMTOFLICNCFEERETENTION" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="10%">
		    <input type="text" id="txtLicenseFeeForGovt" name="txtLicenseFeeForGovt"  value="${resValue.AmtOfLicenceFee}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
		 </td>
		 <td width="10%" align="center">
		 	<fmt:message key="PPMT.FORMONTHYEAR" bundle="${pensionLabels}"></fmt:message>
		 </td>
		 <td width="21%" align="left">
		  <select name="cmbLicenseFeeFromMonth" id="cmbLicenseFeeFromMonth" >
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 	<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(resValue.AmtOfLicenceFeeFromMonth,4,6)}">
				            <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(resValue.AmtOfLicenceFeeFromMonth,5,6)}">
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
			 <select name="cmbLicenseFeeFromYear" id="cmbLicenseFeeFromYear" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				            <c:when test="${YearList.desc == fn:substring(resValue.AmtOfLicenceFeeFromMonth,0,4)}">
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
		 <td width="7%" align="left">
	       <fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message>
	     </td>
	     <td width="30%"  align="left">
	     <input type="text" name="txtLicFeeSchemeCode" id="txtLicFeeSchemeCode" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${resValue.LicFeeShemeCode}" size="12"/>
	     <a href="#" onclick="showSchemeCodeList('txtLicFeeSchemeCode');"><img src="images/search.gif" /></a>
	     </td>	 
	</tr>
	</table>
	</fieldset>
	<br/>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.ASSESSEDDUESDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
	<table>
	
	<tr>	
		 <td width="30%">
	       <fmt:message key="PPMT.ANYOTHRASSMNT" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="10%">
		 <c:choose>
			<c:when test="${resValue.AssessedDuesDtls !=null}">
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioOthrAsmtY" name="radioOthrAsmt" value="Y" onclick="addAssessedDues(this)" checked="checked"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioOthrAsmtN" name="radioOthrAsmt" value="N" onclick="addAssessedDues(this)" />
			</c:when>
			<c:otherwise>
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioOthrAsmtY" name="radioOthrAsmt" value="Y" onclick="addAssessedDues(this)"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioOthrAsmtN" name="radioOthrAsmt" value="N" onclick="addAssessedDues(this)"  checked="checked" />
			</c:otherwise>
			</c:choose>
		 </td>
		 <td width="50%">
		 </td>
		 </tr>
</table>

<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
 <hdiits:button id="btnDuesDtlsAddRow" type="button" name="btnDuesDtlsAddRow" bundle="${pensionLabels}" captionid="PPMT.ADDROW" onclick="assdDuesDtlsTableAddRow();" readonly="true"/>

 <input type="hidden" id="hidInstlGridSize" name="hidInstlGridSize" value="0"/>
<table id="tblAssessedDuesDtls" border="1">
		<tr style="width: 100%" class="datatableheader" >
	       	   <td width="15%" class="datatableheader"><fmt:message key="PPMT.NATURE" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}" ></fmt:message></td>	
			   <td width="15%" class="datatableheader"><fmt:message key="PPMT.NOOFINSTLMNT" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="20%" class="datatableheader"><fmt:message key="PPMT.STARTMONTHYEAR" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="20%" class="datatableheader"><fmt:message key="PPMT.ENDMONTHYEAR" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="10%" class="datatableheader"><fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="3%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		</tr>
		<c:choose>

		<c:when test="${resValue.AssessedDuesDtls !=null}">

			<c:forEach var="AssessedDuesDtlsVO"
				items="${resValue.AssessedDuesDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnDueRecoveryDtlId" id="hdnDueRecoveryDtlId${Counter.index}" value="${AssessedDuesDtlsVO.trnPensionRecoveryDtlsId}"/>
					<input type="text" name="txtNature" id="txtNature${Counter.index}" size="25" onfocus="onFocus(this)"  onblur="onBlur(this);" value="${AssessedDuesDtlsVO.nature}" maxlength="60"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDuesAmount" id="txtDuesAmount${Counter.index}" value="${AssessedDuesDtlsVO.amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDuesNoOfInstlmt" id="txtDuesNoOfInstlmt${Counter.index}" value="${AssessedDuesDtlsVO.noOfInstallments}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)"/>
					</td>
					<td class="tds" align="center">
					
					<select name="cmbDuesStartMonth" id="cmbDuesStartMonth${Counter.index}" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(AssessedDuesDtlsVO.fromMonth,4,6)}">
				            <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(AssessedDuesDtlsVO.fromMonth,5,6)}">
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
		            <select name="cmbDuesStartYear" id="cmbDuesStartYear${Counter.index}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				            <c:when test="${YearList.desc == fn:substring(AssessedDuesDtlsVO.fromMonth,0,4)}">
				            
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
					<td class="tds" align="center">
					
					<select name="cmbDuesEndMonth" id="cmbDuesEndMonth${Counter.index}" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="MonthList" items="${resValue.lLstMonths}">
				         <c:choose>
				         <c:when test="${MonthList.id == fn:substring(AssessedDuesDtlsVO.toMonth,4,6)}">
				              <option selected="selected" value="${MonthList.id}"><c:out value="${MonthList.desc}"></c:out></option>
				         </c:when>
				         <c:when test="${MonthList.id == fn:substring(AssessedDuesDtlsVO.toMonth,5,6)}">
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
		            <select name="cmbDuesEndYear" id="cmbDuesEndYear${Counter.index}" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="YearList" items="${resValue.lLstYears}">
				         <c:choose>
				         <c:when test="${YearList.desc == fn:substring(AssessedDuesDtlsVO.toMonth,0,4)}">
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
					<td class="tds" align="center">
					<input type="text" name="txtAssdDueSchemeCode" id="txtAssdDueSchemeCode${Counter.index}" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" value="${AssessedDuesDtlsVO.schemeCode}" size="11"/>
					<a href="#" id="txtAssdDueSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif"/></a>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblAssessedDuesDtls');"/></td>
				</tr>
				<script>
					document.getElementById("hidInstlGridSize").value = Number('${Counter.index}') + 1;
				</script>
					
			</c:forEach>
		</c:when>
		</c:choose>	
</table>
<script>
 if(document.getElementById('tblAssessedDuesDtls').rows.length > 1)
 {
 	document.getElementById("btnDuesDtlsAddRow").disabled=false;
 	document.getElementById("radioOthrAsmtY").checked=true;
 }
 else
 {
	 document.getElementById("btnDuesDtlsAddRow").disabled=true;
	 document.getElementById("radioOthrAsmtN").checked=true;
 }
 </script>    	
</div>
</fieldset>
<!--  <fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.RECFORDCRG" bundle="${pensionLabels}"></fmt:message></b> </legend>	
	<table>
	
	<tr>	
		 <td width="30%">
	       <fmt:message key="PPMT.RECGOVDUES" bundle="${pensionLabels}"></fmt:message>
	     </td>
		 <td width="10%">
		 <c:choose>
			<c:when test="${resValue.DCRGRecoveryDtls !=null}">
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecGovtDuesY" name="radioRecGovtDues" value="Y" onclick="addDCRGRecoveryDtl(this)"  checked="checked"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecGovtDuesN" name="radioRecGovtDues" value="N" onclick="addDCRGRecoveryDtl(this)"/>
			</c:when>
			<c:otherwise>
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecGovtDuesY" name="radioRecGovtDues" value="Y" onclick="addDCRGRecoveryDtl(this)" />
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioRecGovtDuesN" name="radioRecGovtDues" value="N" onclick="addDCRGRecoveryDtl(this)" checked="checked"/>
			</c:otherwise>
		</c:choose>
			
		 </td>
		 <td width="50%">
		 </td>
		 </tr>
</table>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 60%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
 

 <input type="hidden" id="hidRecGridSize" name="hidRecGridSize" value="0"/>
<table id="tblDCRGRecoveyDtls" border="1">
		<tr style="width: 100%" class="datatableheader" >
	       	   <td width="15%" class="datatableheader"><fmt:message key="PPMT.NATURE" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="15%" class="datatableheader"><fmt:message key="PPMT.AMOUNT" bundle="${pensionLabels}" ></fmt:message></td>	
			   <td width="15%" class="datatableheader"><fmt:message key="PPMT.SCHEMECODE" bundle="${pensionLabels}"></fmt:message></td>
			   <td width="5%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		</tr>
		<c:choose>

		<c:when test="${resValue.DCRGRecoveryDtls !=null}">

			<c:forEach var="DCRGRecoveryDtlsVO"
				items="${resValue.DCRGRecoveryDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="hidden" name="hdnDcrgRecoveryDtlId" id="hdnDcrgRecoveryDtlId${Counter.index}" value="${DCRGRecoveryDtlsVO.trnPensionRecoveryDtlsId}" />
					<input type="text" name="txtDcrgNature" id="txtDcrgNature${Counter.index}" size="25" onfocus="onFocus(this)"  onblur="onBlur(this);" value="${DCRGRecoveryDtlsVO.nature}"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDcrgRecAmount" id="txtDcrgRecAmount${Counter.index}" value="${DCRGRecoveryDtlsVO.amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDcrgSchemeCode" id="txtDcrgSchemeCode${Counter.index}" value="${DCRGRecoveryDtlsVO.schemeCode}" size="12" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);" />
					<a href="#" id="txtDcrgSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblDCRGRecoveyDtls');"/></td>
				</tr>
				<script>
					document.getElementById("hidRecGridSize").value = Number('${Counter.index}') + 1;
				</script>
					
	    	</c:forEach>
		</c:when>
		</c:choose>
	
	
		</table>
</div>
-->
<script>
/*if(document.getElementById('tblDCRGRecoveyDtls').rows.length > 1)
 {
 	document.getElementById("btnDcrgRecDtlsAddRow").disabled=false;
 	document.getElementById("radioRecGovtDuesY").checked=true;
 }
 else
 {
	 document.getElementById("btnDcrgRecDtlsAddRow").disabled=true;
	 document.getElementById("radioRecGovtDuesN").checked=true;
 }
 */
 </script>    
<!--  </fieldset>  -->
