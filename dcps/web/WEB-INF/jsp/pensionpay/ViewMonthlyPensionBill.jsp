<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts" var="pensionAlerts" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.lLstMonthlyBillDtls}" />
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionpay/ViewMonthlyPensionBill.js"></script>
<fieldset style="width: 100%;" class="tabstyle">
	<legend id="headingMsg">
		<b><fmt:message key="VIEWMONTHLYBILL.HDNG" bundle="${pensionLabels}"></fmt:message></b>
	 </legend>
	 <hdiits:form name="viewMonthlyPensionBills" method="post" validate="">
	 <div class="scrollablediv" >
		<display:table list="${VOList}" id="vo"  requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%"  partialList="true" 
						 offset="1"  size="${totalRecords}" sort="external"  defaultorder="descending" cellpadding="5" defaultsort="3" excludedParams="ajax" >
			<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
							headerClass="datatableheader" style="width:2%">
				<input type="checkbox" align="middle" name="chkbxBillNo"
						id="chkbxBillNo_${vo_rowNum}"
						onclick="" value="${vo[8]}" />
			</display:column>
			<display:column titleKey="PPMT.BILLCONTROLNO" headerClass="datatableheader" sortable="true" style="width:10%;" ><lable>${vo[10]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.SCHEMECODE" headerClass="datatableheader" sortable="true" style="width:8%;" ><lable>${vo[0]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.PAYMODE" headerClass="datatableheader" sortable="true" style="width:5%" ><lable>${vo[1]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.MONTHYEAR" headerClass="datatableheader" sortable="true" style="width:8%;text-align:center;" >
				<input type="hidden" name="forMonth" id="formonth_${vo_rowNum}" value="${vo[9]}" />
				<input type="hidden" name="forMnothDesc" id="forMonthDesc_${vo_rowNum}" value="${vo[2]}" />
				<lable>${vo[2]}</lable>
			</display:column>
			<display:column titleKey="VIEWMONTHLYBILL.BILLDATE" headerClass="datatableheader" sortable="true" style="width:8%;text-align:center;" >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}"/>
			</display:column>
			<display:column titleKey="VIEWMONTHLYBILL.GROSSAMT" headerClass="datatableheader" sortable="true" style="width:7%;text-align:right;" ><lable>${vo[4]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.RCVRYAMT" headerClass="datatableheader" sortable="true" style="width:6%;text-align:right;" ><lable>${vo[5]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.NETAMT" headerClass="datatableheader" sortable="true" style="width:7%;text-align:right;" ><lable>${vo[6]}</lable></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.STATUS" headerClass="datatableheader" style="width:5%" >
				<lable>
				<c:if test="${vo[7] == '5'}">
					<fmt:message key="VIEWMONTHLYBILL.PNDNGAPRVL" bundle="${pensionLabels}"></fmt:message>
				</c:if>
				<c:if test="${vo[7] == '20'}">
					<fmt:message key="VIEWMONTHLYBILL.APPROVED" bundle="${pensionLabels}"></fmt:message>
				</c:if>
				<c:if test="${vo[7] == '60'}">
					<fmt:message key="VIEWMONTHLYBILL.REJECTED" bundle="${pensionLabels}"></fmt:message>
				</c:if>
				<input type = "hidden" id="status_${vo_rowNum}" value="${vo[7]}" />
				</lable>
			</display:column>
			<display:column titleKey="VIEWMONTHLYBILL.OUTER" headerClass="datatableheader"  style="width:10%;text-align:center;" >
				<a href="#" onclick="javascript:viewOuterMonthlyBill('${vo[8]}','<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}"/>');">Print Outer</a><br/>
				<a href="#" onclick="javascript:viewBankwiseOuterMonthlyBill('${vo[8]}','<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}"/>');">Print Bankwise Outer</a>
			</display:column> 
			<display:column titleKey="VIEWMONTHLYBILL.INNER" headerClass="datatableheader"  style="width:6%" ><a href="#" onclick="javascript:viewInnerMonthlyBill('${vo[8]}','<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}"/>');">Print Inner</a></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.CHNGSTMNT" headerClass="datatableheader" style="width:8%" ><a href="#" onclick="javascript:viewChangeStatement('${vo[8]}','${vo[9]}','${vo[0]}','${vo[1]}','${vo[2]}');">Change Statement</a></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.PRINTCHNGSTMNT" headerClass="datatableheader" style="width:8%" ><a href="#" onclick="javascript:printChangeStatement('${vo[8]}','${vo[9]}','${vo[0]}');">Print Change Statement</a></display:column>
			<display:column titleKey="VIEWMONTHLYBILL.VIEWBILL" headerClass="datatableheader" style="width:6%;" ><a href="#" onclick="javascript:viewBankBranchReport('${vo[8]}');">View Bill</a></display:column>
		</display:table>
	</div>
</hdiits:form>
</fieldset>
<br/>
<div style="text-align:center;">
	<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="CMN.APPROVE" bundle="${pensionLabels}" onclick="approveMonthlyPensionBill();"/>
	<hdiits:button name="btnReject" id="btnReject" type="button" captionid="CMN.REJECT" bundle="${pensionLabels}" onclick="rejectMonthlyPensionBill();"/>
	<hdiits:button name="btnClose" id="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>