<%try{%>
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
			<select  name="cmbSearch" id="id_cmbSearch" onchange="showDtPic(),getTypeCmb()" onfocus="showDtPic()">
				<option value="-1"> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="r.ppo_No"> <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="name"> <fmt:message key="PPMT.NAME" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="caseStatus"> <fmt:message key="PPMT.CASESTATUS" bundle="${pensionLabels}"></fmt:message> </option>
				<option value="r.auditor_name"> <fmt:message key="PPMT.AUDITORNAME" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
		</td>
		<td id="txtSearch1">
			<input id="txtSearch" type="text" onfocus="changeOnFocus(this)" onkeypress="upperCase(event)"   name="txtSearch"  size="15" />
		</td>
		<td width="20%" align="left">
			<a href="#" onclick=""><img src="images/search.gif" /></a>
		</td>
	</tr>
</table>
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.VIEWMODIFIEDBILLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
 <c:set var="VOList" value="${resValue.SavedCaseList}" />
 <c:set var="cnt" value="0"></c:set> 
<display:table  list="" export="false"
		id="vo" requestURI="" style="width:100%" partialList=""  sort="external" defaultsort="1" pagesize="100">
		<display:setProperty name="paging.banner.placement" value="top" />
		<display:column
			title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this),setValueofPPO()'/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxTrnPensionRqstHdrId"
				id="chkbxTrnPensionRqstHdrId"
				onclick="ChkHdrChkBx(this),setValueofPPO()" value="" />
		</display:column>
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			<c:set var="URLLink"
				value=""></c:set>
			<a href="javascript:void(0)" onclick="javascript:window.open('ifms.htm?viewName=HeaderField');">
			 11100111294
			 </a>
		</display:column>
		<display:column titleKey="PPMT.NAME" headerClass="datatableheader"
			sortable="true" style="text-align:center">
			VASANT BALWANT GHORPADE
		</display:column>
		<display:column titleKey="PPMT.CASESTATUS"
				headerClass="datatableheader" sortable="true" style="text-align:center">
			<fmt:message key="PPMT.APPRVD" bundle="${pensionLabels}"></fmt:message>
		</display:column>
		<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
				sortable="true" style="text-align:center">
			N. S. ATHAWALE	
		</display:column>
	</display:table>
</fieldset>
<br/>
<div align="center">
	<hdiits:button type="button" name="btnApprove" captionid="PPMT.APPROVE"  bundle="${pensionLabels}" />	
	<hdiits:button type="button" name="btnReject" captionid="PPMT.REJECT"  bundle="${pensionLabels}" />			
	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="javascript:pageClose()"/>
</div>	
</hdiits:form>
<% }catch(Exception e){
e.printStackTrace();
}%>