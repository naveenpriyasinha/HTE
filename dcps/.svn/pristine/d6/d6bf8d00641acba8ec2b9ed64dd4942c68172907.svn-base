<%try{%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/pensionpay/uploadcases.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<hdiits:form name="UploadedCases" method="post" validate="">

<!--<c:set var="VOList" value="${resValue.PensionerList}" />

-->
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.SCHELIST" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="" size="10" id="vo" pagesize="10"
					cellpadding="5" requestURI="" >
	<display:column title="<input name='chkSelect'type='checkbox' onclick=''/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbxPesnionerNo"
				onclick="" value="" />
	</display:column>			
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center"><div id="lblPpoNo"><a href="javascript:void(0)" onclick="javascript:window.open('ifms.htm?viewName=HeaderField');">11100111294</a></div></display:column>		 
	<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:30%;text-align:center" >VASANT BALWANT GHORPADE </display:column>
	<display:column titleKey="PPMT.DOR" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center" >01/01/2011

	</display:column>			
	<display:column titleKey="PPMT.PSD" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center" >02/01/2011

	</display:column>				
	<display:column titleKey="PPMT.BANKBRNCODE" headerClass="datatableheader"
			style="width:10%;text-align:center" ><input type="text" name="txtBankBranchCode" id="txtBankBranchCode">
	</display:column>	
	<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader" style="width:20%">
			<select name="cmbBankName${vo_rowNum}" id="cmbBankName${vo_rowNum}" onChange="">
				<option value='-1'>---Select---</option>
			</select>
	</display:column>
	<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader" style="width:20%">
			<select name="cmbBankBrnchName${vo_rowNum}" id="cmbBankBrnchName${vo_rowNum}" onChange="">
				<option value='-1'>---Select---</option>
			</select>
	</display:column>
	<display:column titleKey="PPMT.ACCNO" headerClass="datatableheader"
			style="width:20%;text-align:center" ><input type="text" name="txtAccNo" id="txtAccNo">
	</display:column>
	<display:column titleKey="PPMT.CALLDATE" headerClass="datatableheader" style="width:100%;text-align:center">
	<input type="text" name="txtCalledDate" id="txtCalledDate" size="12" >&nbsp;<img src='images/CalendarImages/ico-calendar.gif' 
			onClick='window_open("txtCalledDate",375,570)'style="cursor: pointer;" />
	</display:column>
	<display:column titleKey="PPMT.CALLTIME" headerClass="datatableheader"
			style="width:20%;text-align:center" ><input type="text" name="txtCalledTime" id="txtCalledTime">
	</display:column>
	<display:column titleKey="PPMT.CHCKLIST" headerClass="datatableheader"
			style="width:20%;text-align:center" ><a href="View">View</a>
	</display:column>
	<display:column titleKey="PPMT.STATUS" headerClass="datatableheader"
			style="width:20%;text-align:center" ><a href="Awaited">Awaited</a>
	</display:column>
</display:table>


</div>
</fieldset>
</hdiits:form>
<table align="center">
<hdiits:button name="btnSchedule" id="btnSchedule" type="button"  classcss="bigbutton" captionid="PPMT.SHEDULETIME" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnReschedule" id="btnReschedule" type="button" classcss="bigbutton" captionid="PPMT.RESCHEDULETIME" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnGenerateLetter" id="btnGenerateLetter" type="button" classcss="bigbutton" captionid="PPMT.GENERATELETT" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnApprove" id="btnApprove" type="button"  captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick=""/>
</table>
<% }catch(Exception e){
e.printStackTrace();
}%>