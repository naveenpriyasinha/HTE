<%@ page language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/RecoveryTab.js"></script>
<script type="text/javascript"  src="script/pensionpay/DCRGTab.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
	
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>

<input type="hidden" name="hidPnsnrCode" id="hidPnsnrCode" value="${trnPensionRqstHdrVO.pensionerCode}" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.DCRGDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<div>&nbsp;</div>
<table width="100%" align="center" cellspacing="10">
	<tr>
	    <td width="10%">
	       <fmt:message key="PPMT.GPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<!-- <input type="text" id="txtGPONo" name="txtGPONo" />  -->
	    	<input type="text" id="txtDcrgOrder" name="txtDcrgOrder" value="${trnPensionRqstHdrVO.dcrgOrderNo}" onfocus="onFocus(this)" onblur="onBlur(this);" maxlength="35"/>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	       <fmt:message key="PPMT.GPODATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    <!-- 	<input type="text" id="txtGPODate" name="txtGPODate"  />
	    	 <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtGPODate",375,570)'style="cursor: pointer;" ${disabled}/>  -->
		 <input type="text" id="txtDcrgPaidDate" name="txtDcrgPaidDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.paidDate}" />"
		      onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfJoining,this,this,DCRGPAIDDTCOMDT,'<')"/>
		      <img id="imgDcrgPaidDate" src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtDcrgPaidDate",375,570)'style="cursor: pointer;"/>					        	
	    </td>
	    <td width="5%"></td>
	</tr>
	<tr>
	    <td width="10%">
	       <fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<!--  <input type="text" id="txtVoucherNo" name="txtVoucherNo" /> -->
	    	<input type="text" id="txtDcrgVoucherNo" name="txtDcrgVoucherNo"  onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)" value="${trnPensionRqstHdrVO.dcrgVoucherNo}" maxlength="20"/>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	       <fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<!-- <input type="text" id="txtVoucherDate" name="txtVoucherDate"  />
	    	<img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtVoucherDate",375,570)'style="cursor: pointer;" ${disabled}/>  -->
					        
		<input type="text" id="txtDcrgVoucherDate" name="txtDcrgVoucherDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.dcrgVoucherDate}" />"
		      onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
		      <img id="imgDcrgVoucherDate" src='images/CalendarImages/ico-calendar.gif' 
					        onClick='window_open("txtDcrgVoucherDate",375,570)'style="cursor: pointer;"/>						        
	    </td>
	    <td width="5%"></td>
	</tr>
	<tr>
	    <td width="10%">
	       <fmt:message key="PPMT.TOTGRATUITYAMNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<!--  <input type="text" id="txtTotGartuityAmnt" name="txtTotGartuityAmnt" onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this)"/>  -->
	    	<input type="text" id="txtTotalDcrgAmount" name="txtTotalDcrgAmount" value="${trnPensionRqstHdrVO.totalDcrgAmount}" onblur="setValidAmountFormat(this);setAmountAftrWithHeld()" onkeypress="amountFormat(this,event);"  style="text-align: right"/>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	       <fmt:message key="PPMT.WITHHELDAMNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<input type="text" id="txtWithheldAmnt" name="txtWithheldAmnt" onkeypress="amountFormat(this,event);"  onblur="setValidAmountFormat(this);setAmountAftrWithHeld();" style="text-align: right" value="${trnPensionRqstHdrVO.dcrgWithHeldAmnt}" />
	    </td>
	    <td width="5%"></td>
	</tr>
	
	<tr>
	    <td width="10%">
	       <fmt:message key="PPMT.AMNTAFTERWITHHELD" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<input type="text" id="txtAmntAfterWithHeld" name="txtAmntAfterWithHeld" readonly="readonly"  value="${trnPensionRqstHdrVO.dcrgAmntAfterWithHeld}"/>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	    </td>
	    <td width="30%">
	    </td>
	    <td width="5%"></td>
	</tr>
	</table>
				
	<div align="left">
	
	<hdiits:button name="btnDcrgRecDtlsAddRow" type="button"   captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="dcrgRecoveryTableAddRow();"  />
	
 
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
					<input type="text" name="txtDcrgNature" id="txtDcrgNature${Counter.index}" size="25" onfocus="onFocus(this)"  onblur="onBlur(this);" value="${DCRGRecoveryDtlsVO.nature}" maxlength="60"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDcrgRecAmount" id="txtDcrgRecAmount${Counter.index}" value="${DCRGRecoveryDtlsVO.amount}" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);setTotalRecoveryAmnt(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>
					</td>
					<td class="tds" align="center">
					<input type="text" name="txtDcrgSchemeCode" id="txtDcrgSchemeCode${Counter.index}" value="${DCRGRecoveryDtlsVO.schemeCode}" size="12" onfocus="onFocus(this)"  onblur="onBlur(this);validateSchemeCode(this);setTotalRecoveryAmnt(this);" />
					<a href="#" id="txtDcrgSchemeCode${Counter.index}" onclick="showSchemeCodePopup(this);"><img src="images/search.gif" /></a>
					</td>
					<td class="tds" align="center"><img name="Image"
						id="Image${Counter.index}"
						src="images/CalendarImages/DeleteIcon.gif"
						onclick="RemoveTableRow(this, 'tblDCRGRecoveyDtls');setTotalRecoveryAmnt(this);"/></td>
				</tr>
				<script>
					document.getElementById("hidRecGridSize").value = Number('${Counter.index}') + 1;
				</script>
					
	    	</c:forEach>
		</c:when>
		</c:choose>
		
		
		
	
		</table>
</div>

	 <table width="100%" align="center" cellspacing="10">
	 <tr>
	    <td width="10%">
	       <fmt:message key="PPMT.TOTRECOVERYAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<input type="text" id="txtTotRecoveryAmnt" name="txtTotRecoveryAmnt" readonly="readonly" value="${trnPensionRqstHdrVO.dcrgTotalRecoveryAmnt}" />
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	    </td>
	    <td width="30%">
	    </td>
	    <td width="5%"></td>
	</tr>
	<tr>
	    <td width="10%">
	      <fmt:message key="PPMT.PAYPAYEBLEAMNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<!--  <input type="text" id="txtPayableAmnt" name="txtPayableAmnt" readonly=""/>  -->
	    	<input type="text" id="txtDcrgPayableAmount" name="txtDcrgPayableAmount" value="${trnPensionRqstHdrVO.dcrgAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  readonly="readonly"/>
	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	    </td>
	    <td width="30%">
	    </td>
	    <td width="5%"></td>
	</tr>
	<tr>
	    <td width="10%">
	     <fmt:message key="PPMT.PAYAUTH" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    
	   <select name="cmbPayingAuthority" id="cmbPayingAuthority"   style="width: 77%"  tabindex="4">
			<option value="-1" selected="selected"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:choose>
			<c:when test="${trnPensionRqstHdrVO.dcrgPayingAuth == 'DDO'}">
					<option value="DDO" selected="selected"><fmt:message key="PPMT.DDO" bundle="${pensionLabels}"/></option>
					<option value="TO"><fmt:message key="PPMT.TO" bundle="${pensionLabels}"/></option>
			</c:when>
			<c:when test="${trnPensionRqstHdrVO.dcrgPayingAuth == 'TO'}">
					<option value="DDO"><fmt:message key="PPMT.DDO" bundle="${pensionLabels}"/></option>
					<option value="TO" selected="selected"><fmt:message key="PPMT.TO" bundle="${pensionLabels}"/></option>
			</c:when>
			<c:otherwise>
					<option value="DDO" ><fmt:message key="PPMT.DDO" bundle="${pensionLabels}"/></option>
					<option value="TO"><fmt:message key="PPMT.TO" bundle="${pensionLabels}"/></option>
			</c:otherwise>
			</c:choose>
		    </select>
	    

	    </td>
	    <td width="10%"></td>
	    <td width="10%">
	    </td>
	    <td width="30%">
	    </td>
	    <td width="5%"></td>
	</tr>
		
		</table>
	   
	<br>
	
	
</fieldset>
	<br>
	
	<div align="center">
		<hdiits:button name="btnSaveAsHistory" type="button" style="width:130px;" captionid="PPMT.SAVEASHISTORY" bundle="${pensionLabels}" onclick="saveDcrgHistoryDtls('S');"  />
		<hdiits:button name="btnViewHistory" type="button"   captionid="PPMT.VIEWHISTORY" bundle="${pensionLabels}" onclick="showDcrgHistory();"  />
	</div>
  
  