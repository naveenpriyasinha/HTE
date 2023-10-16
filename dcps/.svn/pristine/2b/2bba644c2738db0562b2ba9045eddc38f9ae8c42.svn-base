<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/AllocationRevisionTab.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>
<c:set var="mstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}"></c:set>
<c:set var="mstPensionerDtlsVO" value="${resValue.MstPensionerDtlsVO}"></c:set>
<c:set var="TrnCvpRestorationDtls" value="${resValue.TrnCvpRestorationDtls}"></c:set>

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.ALLOCATION" bundle="${pensionLabels}"></fmt:message></b> </legend>		
  
  <table width="100%">	
	<tr>
	<!-- 
		<td width="25%">
	       <fmt:message key="PPMT.ALLOCINDICATOR" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		  <c:choose>
	    	 <c:when test="${trnPensionRqstHdrVO.allocIndicatorFlag == 'Y'}" >
			    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorY" name="radioAllocIndicator" value="Y" onclick="enableAllocationFields(this);"  checked="checked"/>
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorN" name="radioAllocIndicator" value="N" onclick="enableAllocationFields(this);" />
			</c:when>
			<c:when test="${trnPensionRqstHdrVO.allocIndicatorFlag == 'N'}" >
			    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorY" name="radioAllocIndicator" value="Y" onclick="enableAllocationFields(this);"/>
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorN" name="radioAllocIndicator" value="N" onclick="enableAllocationFields(this);"  checked="checked" />
			</c:when>
			<c:otherwise>
			    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorY" name="radioAllocIndicator" value="Y" onclick="enableAllocationFields(this);"/>
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioAllocIndicatorN" name="radioAllocIndicator" value="N" onclick="enableAllocationFields(this);" checked="checked"/>
			</c:otherwise>
		  </c:choose>	
		 </td>
		  -->
		 <td width="25%">
	       <fmt:message key="PPMT.PNSNAFCOMTN" bundle="${pensionLabels}"></fmt:message><br/>
	       <font color="red"><fmt:message key="PPMT.SUMOFALLOCN" bundle="${pensionLabels}"></fmt:message></font>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtPensionAmount" name="txtPensionAmount" onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="41"/>
	        <label id="mandtryFinal" class="mandatoryindicator">*</label>
	        <input type="hidden" id="hdnPensionAmount" name="hdnPensionAmount"/>
		 </td>
		
	</tr>
	
	<tr>
		 <td width="25%">
	       <fmt:message key="PPMT.BEFORE01041936" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		      <input type="text" id="txtBefore01041936" name="txtBefore01041936"  value="${trnPensionRqstHdrVO.orgBf11136}" onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="42"/>
		 </td>
	    <td width="25%">
	       <fmt:message key="PPMT.AFTER01041936" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtAfter01041936" name="txtAfter01041936" value="${trnPensionRqstHdrVO.orgAf11136}"  onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="43"/>
		 </td>	
		 		      
	</tr>
	
	<tr>
		 <td width="25%">
	       <fmt:message key="PPMT.AFTER01111956" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		      <input type="text" id="txtAfter01111956" name="txtAfter01111956" value="${trnPensionRqstHdrVO.orgAf11156}"  onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="44"/>
		 </td>	
	    <td width="25%">
	       <fmt:message key="PPMT.AFTER01051960" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtAfter01051960" name="txtAfter01051960" value="${trnPensionRqstHdrVO.orgAf10560}"  onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="45"/>
		 </td>		
		 	      
	</tr>
	
	<tr>
		<td width="25%">
	       <fmt:message key="PPMT.ZILLAPARISHAD" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		      <input type="text" id="txtZillaParishad" name="txtZillaParishad" value="${trnPensionRqstHdrVO.orgAfZp}"  onfocus="onFocus(this)"  onblur="onBlur(this);roundNumber(this, 2);" onkeypress="amountFormat(this);"  style="text-align: right" tabindex="46"/>
		 </td> 	
		 <td width="25%">
		 </td>
		 <td width="25%">
	    	<hdiits:button id="btnRevision" type="button" name="btnRevision" captionid="PPMT.HISTORY" bundle="${pensionLabels}" onclick="getPnsnrRevisionDtls();" readonly="true" tabindex="47"/>
	    </td>
	
  </table>
  
</fieldset>
<c:if test="${resValue.lStrShowCaseFor == 15 or resValue.lStrShowCaseFor == 20}">
<script>
document.getElementById("btnRevision").disabled=false;
</script>
</c:if>

<script>
validateAllocationAmt();
var totalAmount=document.getElementById("hdnPensionAmount").value;
document.getElementById("txtPensionAmount").value=totalAmount;
</script>
<!-- 
<c:if test="${trnPensionRqstHdrVO.allocIndicatorFlag == 'Y'}">
<script>
validateAllocationAmt();
var totalAmount=document.getElementById("hdnPensionAmount").value;
document.getElementById("txtPensionAmount").value=totalAmount;  
document.getElementById("txtBefore01041936").disabled=false;
document.getElementById("txtAfter01041936").disabled=false;
document.getElementById("txtAfter01111956").disabled=false;
document.getElementById("txtAfter01051960").disabled=false;
document.getElementById("txtZillaParishad").disabled=false;
</script>
</c:if>

<c:if test="${trnPensionRqstHdrVO.allocIndicatorFlag == 'N'}">
<script>
validateAllocationAmt();
totalAmount=document.getElementById("hdnPensionAmount").value;
document.getElementById("txtPensionAmount").value=totalAmount;  
document.getElementById("txtBefore01041936").disabled=true;
document.getElementById("txtAfter01041936").disabled=true;
document.getElementById("txtAfter01111956").disabled=true;
document.getElementById("txtAfter01051960").disabled=true;
document.getElementById("txtZillaParishad").disabled=true;
document.getElementById("txtBefore01041936").value="";
document.getElementById("txtAfter01041936").value="";
document.getElementById("txtAfter01111956").value="";
document.getElementById("txtZillaParishad").value="";
</script>
</c:if>
 -->

<!-- 
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REVISION" bundle="${pensionLabels}"></fmt:message></b> </legend>		
  
  <table width="100%">
  
  	<tr>
	    <td width="25%">
	       <fmt:message key="PPMT.ROPTYPE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	       <select name="cmbRopType" id="cmbRopType"   style="width: 52%">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		    </select>
	    </td>
  		
  		<td width="25%">
	       <fmt:message key="PPMT.REVISIONDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		      <input type="text" id="txtRevisionDate" name="txtRevisionDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionerRivisionDtlsVO.rivisionDate}" />"/>
		 </td>
	</tr>
	
	<tr>				
	    <td width="25%">
	       <fmt:message key="PPMT.ORIGINALPENBEFORE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtOrgnlPenBfr01041936" name="txtOrgnlPenBfr01041936" value="${trnPensionerRivisionDtlsVO.orgBf11156}"/>
		 </td>
		 
		  <td width="25%">
	       <fmt:message key="PPMT.ORIGINALPENAFTER" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtOrgnlPenAfter01041936" name="txtOrgnlPenAfter01041936" value="${trnPensionerRivisionDtlsVO.redBf11156}"/>
		 </td>			 		      
	</tr>	 
	
	<tr>	
		<td width="25%">
	       <fmt:message key="PPMT.REVISEDPENAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="25%">
		      <input type="text" id="txtRevisedPnsnAmount" name="txtRevisedPnsnAmount"/>
		 </td>			
	   
		 	 		      
	</tr>	 
	
	<tr>				
	    <td width="25%">
	       <fmt:message key="PPMT.REDUCEPENBEFORE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtReducePenBfr01041936" name="txtReducePenBfr01041936" value="${trnPensionerRivisionDtlsVO.orgAf10560}"/>
		 </td>
		 
		  <td width="25%">
	       <fmt:message key="PPMT.REDUCEPENAFTER" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtReducePenAfter01041936" name="txtReducePenAfter01041936" value="${trnPensionerRivisionDtlsVO.redAf10560}"/>
		 </td>			 		      
	</tr>
	
	<tr>				
	    <td width="25%">
	       <fmt:message key="PPMT.DCRGAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtDcrgAmount" name="txtDcrgAmount" value="${trnPensionerRivisionDtlsVO.dcrgAmount}"/>
		 </td>
		 
		  <td width="25%">
	       <fmt:message key="PPMT.CVPAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtCvpAmount" name="txtCvpAmount" value="${trnPensionerRivisionDtlsVO.cvpAmount}"/>
		 </td>			 		      
	</tr>
	
	<tr>				
	    <td width="25%">
	       <fmt:message key="PPMT.FP1AMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtFp1Amount" name="txtFp1Amount" value="${trnPensionerRivisionDtlsVO.fp1Amount}" />
		 </td>
		 
		<td width="25%">
	       <fmt:message key="PPMT.FP2AMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="25%">
	        <input type="text" id="txtFp2Amount" name="txtFp2Amount" value="${trnPensionerRivisionDtlsVO.fp2Amount}"  />
		 </td>			 		      
	</tr>
	
  </table>
  
</fieldset>
 -->
 <br/><br/>
<font color="red" >Note: Even if you have entered Commuted Pension Details, Please enter the following detals. Otherwise commuted amount will not be deducted from the Bill</font>
<br/>
<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.CVPRESTORATION" bundle="${pensionLabels}"></fmt:message></b> </legend>
 <hdiits:button id="btnCvpAddRow" type="button" name="btnCvpAddRow" captionid="PPMT.ADDROW" onclick="cvpRestDtlTableAddRow();" bundle="${pensionLabels}" tabindex="48"/>
 <input type="hidden" id="hidCvpGridSize" name="hidCvpGridSize" value="0"/>
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
	<table id="tblCvpRestnDtls" align="left" width="95%" cellspacing="0" border="1">
	   		<tr  class="datatableheader" >						   
				   <td width="15%" class="datatableheader"><fmt:message key="PPMT.CVPRESAMT" bundle="${pensionLabels}"></fmt:message></td>
				   <td width="15%" class="datatableheader"><fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}" ></fmt:message></td>	
				   <td width="15%" class="datatableheader"><fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>		
				   <td width="15%" class="datatableheader"><fmt:message key="PPMT.RESTNAPLNRECEIVED" bundle="${pensionLabels}"></fmt:message></td>	
				   <td width="15%" class="datatableheader"><fmt:message key="PPMT.RESTNAPLNRECVDATE" bundle="${pensionLabels}"></fmt:message></td>				   
				   <td width="5%" class="datatableheader"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
		    </tr>
		    
	    <c:choose>
			<c:when test="${resValue.TrnCvpRestorationDtls !=null}">
				<c:forEach var="trnCvpRestorationDtlsVO" items="${resValue.TrnCvpRestorationDtls}" varStatus="Counter">
				<tr>
					<td class="tds" align="center">
					<input type="text" name="txtCvpRestnAmt" id="txtCvpRestnAmt${Counter.index}" size="15" value="${trnCvpRestorationDtlsVO.amount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
					<input type="hidden" name="hdnCvpRestnDtlId" id="hdnCvpRestnDtlId${Counter.index}" value="${trnCvpRestorationDtlsVO.cvpRestorationDtlsId}"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtRestnFromDate" id="txtRestnFromDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnCvpRestorationDtlsVO.fromDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);setCvpRestnToDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtRestnFromDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					
					<td class="tds" align="center">
					<input type="text" name="txtRestnToDate" id="txtRestnToDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnCvpRestorationDtlsVO.toDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtRestnToDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					<td class="tds" align="center">
					<c:choose>
						<c:when test="${trnCvpRestorationDtlsVO.restnAplnReceivedFlag=='Y'}">
							<input type="checkbox" id="chkRestnAplnRecvd${Counter.index}"
								name="chkRestnAplnRecvd" checked="checked" value="Y"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="chkRestnAplnRecvd${Counter.index}"
								name="chkRestnAplnRecvd"  value="Y"/>
						</c:otherwise>
					</c:choose>
					</td> 
					<td class="tds" align="center">
					<input type="text" name="txtRestnAplnRecvdDate" id="txtRestnAplnRecvdDate${Counter.index}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnCvpRestorationDtlsVO.restnAplnReceivedDate}" />"
	       				onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="15"
							onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);validateApplnRecvdDate(this);"/>
	      				 <img src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtRestnAplnRecvdDate${Counter.index}",375,570)'style="cursor: pointer;" ${disabled}/>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblCvpRestnDtls');"/></td>
				</tr>
				<script>
				    
					document.getElementById("hidCvpGridSize").value = Number('${Counter.index}') + 1;
					
				</script>
					
		    	</c:forEach>
			</c:when>
		</c:choose>
		    
	</table>
				
</div>
</fieldset>