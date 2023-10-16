<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>

<script  type="text/javascript"  src="script/pension/Common.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>	
<script type="text/javascript" src="script/pension/Common.js"></script>	
<script type="text/javascript" src="script/pension/popUpSearch.js"></script>
<script type="text/javascript" src="script/pension/SavedCases.js"></script>	
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<hdiits:form name="SavedCaseList" method="post" validate="">
 <table width="100%">
	<tr>
		<td width="50%">
		</td>
		<td align="left">
			<b> <fmt:message key="PPMT.SEARCH" bundle="${pensionLabels}"></fmt:message> </b>
		</td>
		<td align="left">
			<select  name="cmbSearch" id="id_cmbSearch"  >
				<option value="-1"> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="ppo_No"> <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="pnsnr_name"> <fmt:message key="PPMT.NAME" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="pnsnr_dor"><fmt:message key="PPMT.DOR" bundle="${pensionLabels}"></fmt:message></option>
				<option value="pnsn_strtdt"><fmt:message key="PPMT.PSD" bundle="${pensionLabels}"></fmt:message></option>
				<option value="pnsn_amt"><fmt:message key="PPMT.PNSNAMT" bundle="${pensionLabels}"></fmt:message></option>
				<option value="grty_amt"><fmt:message key="PPMT.GRATUITYAMT" bundle="${pensionLabels}"></fmt:message></option>
				<option value="cvp_amt"><fmt:message key="PPMT.CVPAMOUNT" bundle="${pensionLabels}"></fmt:message></option>
				<option value="rop_type"> <fmt:message key="PPMT.ROPTYPE" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
		</td>
		<td id="txtSearch1">
			<input id="txtSearch" type="text" onfocus="changeOnFocus(this)"  name="txtSearch"  size="15" />
		</td>
		<td width="20%" align="left">
			<a href="#" onclick=""><img src="images/search.gif" /></a>
		</td>
	</tr>
</table>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.REVISEDCASE" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
 <c:set var="VOList" value="${resValue.SavedCaseList}" />
 <c:set var="cnt" value="0"></c:set> 
<display:table  list="" export="false"
		id="vo" requestURI="" style="width:100%" partialList=""  sort="external" defaultsort="1" pagesize="100">
		<display:setProperty name="paging.banner.placement" value="top" />
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			<c:set var="URLLink"
				value=""></c:set>
			<a href="javascript:void(0)" onclick="javascript:window.open('ifms.htm?viewName=RevisedCaseProcess');">
			 11100111294
			 </a>
		</display:column>
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			VASANT BALWANT GHORPADE
		</display:column>
		<display:column titleKey="PPMT.DOR" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			01/01/2011
		</display:column>
		<display:column titleKey="PPMT.PSD" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			02/01/2011
		</display:column>
		<display:column titleKey="PPMT.PNSNAMT" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			7000.00
		</display:column>
		<display:column titleKey="PPMT.GRATUITYAMT" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			50000.00
		</display:column>
		<display:column titleKey="PPMT.CVPAMOUNT" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			80000.00
		</display:column>
	</display:table>
</fieldset>
<br/>
<div align="center">
	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="javascript:pageClose()"/>
</div>	
</hdiits:form>