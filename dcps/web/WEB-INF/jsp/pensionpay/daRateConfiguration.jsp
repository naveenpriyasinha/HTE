<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels_en_US" var="pensionLabels" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>

<script type="text/javascript">
function paymentDtlTableAddRow()
{
	var rowCnt = document.getElementById("hidPaymentDtls").value;
	var newRow =  document.all("tblPaymentDtls").insertRow();	
	var Cell1 = newRow.insertCell();
	Cell1.className = "tds";
   	Cell1.align="center";
	var Cell2 = newRow.insertCell();
	Cell2.className = "tds";
   	Cell2.align="center";
	var Cell3 = newRow.insertCell();
	Cell3.className = "tds";
   	Cell3.align="center";
	var Cell4 = newRow.insertCell();
	Cell4.className = "tds";
   	Cell4.align="center";
	var Cell5 = newRow.insertCell();
	Cell5.className = "tds";
   	Cell5.align="center";
	var Cell6 = newRow.insertCell();
	Cell6.className = "tds";
   	Cell6.align="center";
	var Cell7 = newRow.insertCell();
	Cell7.className = "tds";
   	Cell7.align="center";
		
	EvtgridCount = parseInt(rowCnt);
		
	Cell1.innerHTML = '<select name="cmbPayFromMonth" id="cmbPayFromMonth'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell2.innerHTML = '<select name="cmbPayFromYear" id="cmbPayFromYear'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell3.innerHTML = '<select name="cmbPayToMonth" id="cmbPayToMonth'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell4.innerHTML = '<select name="cmbPayToYear" id="cmbPayToYear'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell5.innerHTML = '<select name="cmbPayInMonth" id="cmbPayInMonth'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell6.innerHTML = '<select name="cmbPayInYear" id="cmbPayInYear'+Number(rowCnt)+'" ><option value="-1">--Select--</option></select>';
	Cell7.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblPaymentDtls")\'/>';
	document.getElementById("hidPaymentDtls").value = Number(rowCnt)+1;
}

function showRowCell(element)
{
    var cell, row;    
    if (element.parentNode) 
    {
    	
      do
      	cell = element.parentNode;
    	while (cell.tagName.toLowerCase() != 'td')
     	row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
       do
      	cell= element.parentElement;
    	  while (cell.tagName.toLowerCase() != 'td')
      		row = cell.parentElement;
    }
    return row.rowIndex;
}

function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}
</script>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PPMT.DARATECONFIG"
	bundle="${pensionLabels}"></fmt:message></b></legend>

<table width="97%" align="center">
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.DARATETYPE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <select name="cmbDARateType" id="cmbDARateType" style="width:30%" onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		  </select>
		</td>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.FORPENSION" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <select name="cmbForPension" id="cmbForPension" style="width:30%" onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="pensionval1"><fmt:message key="PPMT.PENSIONVAL1" /></option>
				<option value="pensionval2"><fmt:message key="PPMT.PENSIONVAL2" /></option>
				<option value="pensionval3"><fmt:message key="PPMT.PENSIONVAL3" /></option>
				
		  </select>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.HEADCODE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <select name="cmbHeadCode" id="cmbHeadCode" style="width:30%" onfocus="onFocus(this)" onblur="onBlur(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		  </select>
		</td>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.DESCRIPTION" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <input type="text" id="txtDescription"  size="20"  name="txtDescription" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		</td>
  </tr>
   <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.OLDRATEAMOUNT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <input type="text" id="txtOldRateAmount"  size="20"  name="txtOldRateAmount" onKeyPress="numberFormat(this)" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		</td>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.NEWRATEAMOUNT" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		   <input type="text" id="txtNewRateAmount"  size="20"  name="txtNewRateAmount" onKeyPress="numberFormat(this)" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		</td>
  </tr>
  <tr>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.OLDEFFECTFROMDATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		  <input type="text" name="txtOldEffectFromDate" id="txtOldEffectFromDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.retirementDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtOldEffectFromDate",375,570)'
					style="cursor: pointer;" ${disabled}/>
		</td>
		<td width="20%" align="left" >
			<fmt:message key="PPMT.EFFECTFROMDATE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		    <input type="text" name="txtEffectFromDate" id="txtEffectFromDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${lObjTrnPnsnProcPnsnrDtlsVO.retirementDate}" />"  
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtEffectFromDate",375,570)'
					style="cursor: pointer;" ${disabled}/>
		</td>
  </tr>
</table>
</fieldset>

<fieldset style="width: 80%" class="tabstyle"><legend
	id="headingMsg"><b></b></legend> <hdiits:button
	name="PaymentDtlsAddRow" type="button" captionid="PPMT.ADDROW"
	bundle="${pensionLabels}" onclick="paymentDtlTableAddRow();" />
	<input type="hidden" name="hidPaymentDtls" id="hidPaymentDtls" value="0" />
	
<div
	style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 98%; height: 150px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblPaymentDtls" align="left" width="98%" cellspacing="0"
	border="1">

	<tr class="datatableheader" >
		<td width="10%" class="HLabel" colspan="2"><fmt:message
			key="PPMT.PAYMENTFROM" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel" colspan="2"><fmt:message
			key="PPMT.PAYMENTTO" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="13%" class="HLabel" colspan="2"><fmt:message
			key="PPMT.PAYMENTIN" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="2%" class="HLabel" rowspan="2"><fmt:message
		 	key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<tr class="datatableheader">
		<td width="10%" class="HLabel"><fmt:message
			key="PPMT.MONTH" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.YEAR"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPMT.MONTH" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.YEAR"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message
			key="PPMT.MONTH" bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="10%" class="HLabel"><fmt:message key="PPMT.YEAR"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
			
</tr>
</table>

</div>

</fieldset>
<table align="center">
<tr>
<td width="30%" align="left" >
<hdiits:button name="btnSave"	type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" />&nbsp;&nbsp;&nbsp;&nbsp;
<hdiits:button name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>