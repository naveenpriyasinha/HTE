<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts_en_US" var="pensionAlerts" scope="request" />

<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/LifeCertificate.js"></script>

<script type="text/javascript">
PPONO='<fmt:message key="PPMT.PPONO" bundle="${pensionAlerts}"></fmt:message>';
RECEIVEDDATE='<fmt:message key="PPMT.RECEIVEDDATE" bundle="${pensionAlerts}"></fmt:message>';
NAME='<fmt:message key="PPMT.NAME" bundle="${pensionAlerts}"></fmt:message>';
</script>


<input type="hidden" id="hdnPensionerId" name="hdnPensionerId" >
<input type="hidden" id="hdnLedgerPageNo" name="hdnLedgerPageNo" >
<input type="hidden" id="hdnCommencementDt" name="hdnCommencementDt" >
<input type="hidden" id="hdnAccountNo" name="hdnAccountNo" >
<input type="hidden" id="hdnAliveFlag" name="hdnAliveFlag" >
<input type="hidden" id="hdnBankName" name="hdnBankName" >
<input type="hidden" id="hdnBranchName" name="hdnBranchName" >

<hdiits:form name="frmLifecerificate"  method="post" validate="">
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.RCVLIFECERTI" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<table width="90%" align="center" id="tblReceived" >

<tr>
	<td width="15%" align="left"><fmt:message key="PPMT.PPONO"
			bundle="${pensionLabels}"></fmt:message></td>
	<td width="15%" align="left">
	   		   <input type="text" id="txtPpoNo" name="txtPpoNo" onblur="validatePPONo();" style="display: ;text-transform: uppercase;"/>
	 </td>
	 <td width="15%" align="left"><fmt:message key="PPMT.PENSIONERNAME"
			bundle="${pensionLabels}"></fmt:message></td>
	<td width="15%" align="left">
	   		   <input type="text" id="txtPnsnrName" name="txtPnsnrName" readonly="readonly"/>
	<td width="15%" align="left"></td>
	<td width="15%" align="left"></td>
</tr>
<tr>
	<td width="15%" align="left"><fmt:message key="PPMT.BUNDLENO"
			bundle="${pensionLabels}"></fmt:message></td>
	<td width="20%" align="left">
	   		   <input type="text" id="txtBundleNo" name="txtBundleNo"/></td>
	<td width="15%" align="left"><fmt:message key="PPMT.LIFECERTFLAG"
			bundle="${pensionLabels}"></fmt:message></td>
	<td width="15%" align="left">
				<input type="radio" id="radioLifeCertFlagY" name="radioLifeCertFlag" value="Y"  />
				<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioLifeCertFlagN" name="radioLifeCertFlag" value="N" />
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				
	</td>
	<td width="15%" align="left"></td>
	<td width="15%" align="left"></td>

</tr>
<tr>
	<td width="15%" align="left"><fmt:message key="PPMT.RECEIVEDDATE"
			bundle="${pensionLabels}"></fmt:message></td>
	<td width="20%" align="left">
		<input type="text" id="txtReceivedDate" name="txtReceivedDate"
			  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  tabindex="10"/>
	 	<img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("txtReceivedDate",375,570)'style="cursor: pointer;" />	
			
			<label id="mandtryFinal" class="mandatoryindicator">*</label>
	</td>
	<td width="15%" align="right" >
					 <hdiits:button name="btnAddRow" type="button" captionid="PPMT.ADD" bundle="${pensionLabels}"  onclick="pnsnrDtlsTableAddRow()"/>
				</td>
	<td width="15%" align="left"></td>
	<!-- 
	<td width="15%" align="center"><fmt:message key="PPMT.UPDATEALL"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align ="left" >
	  			 <select name="cmbType"	id="cmbType" onfocus="" onblur="" disabled="disabled">
	  			 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option>Yes</option>
  					<option>No</option>
			
		    	</select>
		    	</td>	 -->
	</tr>

</table>
</fieldset>

<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 100%; height: 170px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<input type="hidden" id="hidPnsnrGridSize" name="hidPnsnrGridSize" value="0" />
<table id="tblPnsnrDtls" border="1">
	<tr style="width: 100%" class="datatableheader">
		<td width="8%" class="datatableheader"><fmt:message
			key="PPMT.PPONUMBER" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="6%" class="datatableheader"><fmt:message
			key="PPMT.VOLPAGE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" class="datatableheader"><fmt:message
			key="PPMT.BANKBRANCHNAME" bundle="${pensionLabels}"></fmt:message></td>	
		<td width="6%" class="datatableheader"><fmt:message
			key="PPMT.ACCOUNTNO" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
			key="PPMT.RECEIVEDDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="5%" class="datatableheader"><fmt:message
			key="PPMT.LIFECERTFLAG" bundle="${pensionLabels}"></fmt:message></td>
		
		<td width="1%" class="datatableheader"><fmt:message
		 	key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
</table>
</div>
	



<table  align="center" >
<tr>
<td>
		<hdiits:button type="button" captionid="PPMT.FORWARDTOATO"  bundle="${pensionLabels}" id="btnForward" name="btnForward" classcss="bigbutton" onclick="forwardCertificateToAto()"/>
	
		<hdiits:button type="button" captionid="PPMT.APPROVE" bundle="${pensionLabels}" id="btnApprove" name="btnApprove" onclick="" style="display :none"/>
		
		<hdiits:button id="btnClose" name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
</td>
</tr>
</table>

</hdiits:form>