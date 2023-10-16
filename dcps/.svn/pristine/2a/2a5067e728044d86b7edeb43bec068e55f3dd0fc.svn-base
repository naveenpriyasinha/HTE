<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>	

<hdiits:form name="InwardPensionCaseForm" id="InwardPensionCaseForm"
	method="POST" action="" encType="multipart/form-data" validate="true">
   <table width="100%" align="center">
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.PPONUMBER" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtPPONo" name="txtPPONo"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.TYPE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><select name="cmbType"
			id="cmbType" onfocus="" onblur="">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		    </select></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.IDENTIFICATION" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%" align="left"><input type="radio"
			id="radioIdentification" name="radioIdentification" value="" onclick="" /> <fmt:message
			key="PPMT.IDENTIFIED" bundle="${pensionLabels}"></fmt:message> <input
			type="radio" id="radioIdentification" name="radioIdentification" value=""
			onclick="" /> <fmt:message key="PPMT.UNIDENTIFIED" bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.INWARDNO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtInwardNo" name="txtInwardNo"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.AGOUTNO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtAGOutwardNo" name="txtAGOutwardNo"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.DATE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%" align="left"><input type="text"
			id="txtDate" name="txtDate"/></td>
	</tr>
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.TREASURYNAME" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtTreasuryName" name="txtTreasuryName"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.AGOUTDATE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtAGOutwardDate" name="txtAGOutwardDate"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.TIME" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%" align="left"><input type="text"
			id="txtTime" name="txtTime"/></td>
			
			
	</tr>
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.CASESRECEIVED" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><select name="cmbCasesRcvd"
			id="cmbCasesRcvd" onfocus="" onblur="">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
		    </select></td>
			
	</tr>
</table>	
</hdiits:form>

<div id="tabmenu" align="left">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message> </a></li>
		<li class="selected"><a href="#" rel="tcontent2"> <fmt:message
			key="PAYMENTDTLS" bundle="${pensionLabels}"></fmt:message> </a></li>	
	</ul>
</div>
	
<div class="tabcontentstyle">

	<!-- ------------------Pensioner Details--------------- -->
	<div id="tcontent1" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionpay/pensionerDtls.jsp" /></div>

	<!-- ------------------Payment Details--------------- -->
	<div id="tcontent2" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionpay/pensionerPaymentDtls.jsp" /></div>		
</div>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />


