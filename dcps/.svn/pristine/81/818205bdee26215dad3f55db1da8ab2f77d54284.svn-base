<%
try {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>	
<script type="text/javascript"	src="script/pensionpay/PensionConfig.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request" />
<input type="hidden" name="hidPnsnrCode" id="hidPnsnrCode" ></input>

<hdiits:form name="frmChangePPONo" id="frmChangePPONo"  validate="true" method="post">

<fieldset style="width:100%"  class="tabstyle">

	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.CHANGEPPONO" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<div>&nbsp;</div>
	<table width="100%" align="center">
	<tr>
		<td width="5%"></td>
	    <td width="10%">
	       <fmt:message key="PPMT.OLDPPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	   		<input type="text" id="txtOldPPONo" name="txtOldPPONo" onblur="getPPODetails();" />
	    </td>
	    <td width="15%"></td>
	     <td width="10%">
	       <fmt:message key="PPMT.NEWPPONO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="30%">
	    	<input type="text" id="txtNewPPONo" name="txtNewPPONo" onblur="ppoExistsOrNot();" />
	    </td>
	</tr>
	
	<tr>
		<td width="5%"></td>	 
		<td width="10%">
	       <fmt:message key="REVARREAR.PENNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
			<input type="text" id="txtPnsnrName" name="txtPnsnrName"  readOnly="readOnly" />
	    </td>
		 <td width="15%">
		  <td width="10%">
	       <fmt:message key="REVARREAR.ACCOUNTNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
		 	<input type="text" id="txtAccNo" name="txtAccNo" readOnly="readOnly"/>
	    </td>           
	</tr>

	<tr>
		<td width="5%"></td>	 
		<td width="10%">
	       <fmt:message key="REVARREAR.BANKNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
			<input type="text" id="txtBankName" name="txtBankName" readOnly="readOnly" />
	    </td>
		  <td width="15%">
		 <td width="10%">
	       <fmt:message key="REVARREAR.BRANCHNAME" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="30%">
		 	<input type="text" id="txtBranchName" name="txtBranchName" readOnly="readOnly" />
	    </td>         
	</tr>
	
		
</table>

<div>&nbsp;</div>
	
</fieldset>
<div>&nbsp;</div>
<table width="100%">
	<tr>
		<td width="40%"></td>
		<td width="60%">
			<hdiits:button id="btnSave" name="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="savePPONo();"  />
			<hdiits:button name="btnHistory" id="btnHistory" type="button"  captionid="SIXPAYARR.HISTORY" bundle="${pensionLabels}" onclick="showPPONoHistory();"/>
			<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"  />
		</td>			
		<td width="20%"></td>
	</tr>
</table>
</hdiits:form>
<%

} catch (Exception e) {
	e.printStackTrace();
}


%>