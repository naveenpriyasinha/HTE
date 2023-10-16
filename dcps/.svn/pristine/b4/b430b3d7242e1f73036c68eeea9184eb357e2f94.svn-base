
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>
<script type="text/javascript"  src="script/pensionpay/uploadcases.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>

<hdiits:form name="UploadedCases" method="post" validate="">

<c:set var="VOList" value="${resValue.PensionerList}" />

<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="${VOList}" size="10" id="vo" pagesize="10"
					cellpadding="5" requestURI="" >
<display:column title="<input name='chkSelect'type='checkbox' onclick=''/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbxPesnionerNo_${vo_rowNum}"
				onclick="" value="${vo[0]}_${vo_rowNum}" />
</display:column>
<display:column titleKey="PPMT.PPONUMBER" headerClass="datatableheader"
			sortable="true" style="width:10%"><div id="lblPpoNo${vo_rowNum}">${vo[0]}</div></display:column>		 
<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%" >${vo[1]}</display:column>
<display:column titleKey="PPMT.REGISTARTIONNO" headerClass="datatableheader"
			style="width:20%" ><input type="text" name="txtRegNo" id="txtRegNo${vo_rowNum}"size="30">
			</display:column>	
<display:column titleKey="PPMT.DOR" headerClass="datatableheader"
			sortable="true" style="width:20%" >
			 <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}"/>
			</display:column>
<display:column titleKey="PPMT.PHYPPORCVD" headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPhyPPORcvd"
				id="chkbxPhyPPORcvd_${vo_rowNum}" onclick="" value="${vo[0]}_${vo_rowNum}" />
			</display:column>
<display:column titleKey="PPMT.PHYPPORCVDDATE" headerClass="datatableheader"
			style="width:15%" ><input type="text" name="txtPhyPpoRcvdDt" id="txtPhyPpoRcvdDt${vo_rowNum}" size="12"><img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("txtPhyPpoRcvdDt${vo_rowNum}",375,570)'style="cursor: pointer;" />
			</display:column>

<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader" style="width:20%">
			<select name="cmbBankName${vo_rowNum}" id="cmbBankName${vo_rowNum}" onChange="getBranchNameFromBankCode(this);">
				<option value='-1'>---Select---</option>
				<c:forEach var="Bank" items="${resValue.BankList}">
					<option value='${Bank.id}'>${Bank.desc}</option>
				</c:forEach>
			</select>
			</display:column>
<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader"
			 style="width:20%" >
			<select name="cmbBankBrnchName${vo_rowNum}"  id="cmbBankBrnchName${vo_rowNum}" onChange="getAuditorNameFromBranchCode(this)">
				<option value='-1'> <fmt:message key="PPMT.SELECT" bundle="${pensionLabels}"></fmt:message> </option>
			</select>
			</display:column>			
 
<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:20%" ><input type="text" name="txtAuditorName${vo_rowNum}" id="txtAuditorName${vo_rowNum}">
			</display:column>										
<display:column titleKey="PPMT.CASETYPE" headerClass="datatableheader"
			sortable="true" style="width:20%" ><input type="text" name="txtCaseType" id="txtCaseType">
			</display:column>	
<display:column titleKey="PPMT.RECEIVINGMODE" headerClass="datatableheader"
			sortable="true" style="width:20%" ><input type="text" name="txtReceivingMode" id="txtReceivingMode">
			</display:column>												
</display:table>
</div>

</hdiits:form>
<table align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveDataUsingAJAX();"/>
<hdiits:button name="btnForward" id="btnForward" type="button"  captionid="PPMT.FORWARD" bundle="${pensionLabels}" onclick=""/>
<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick=""/>
</table>
