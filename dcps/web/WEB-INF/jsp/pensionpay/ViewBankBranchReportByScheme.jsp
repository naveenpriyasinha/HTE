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
<c:set var="VOList" value="${resValue.lArrOuterDtls}" />
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="schemeNo" value="${resValue.lStrSchemeCode}"></c:set>
<c:set var="schemeName" value="${resValue.lStrSchemeName}"></c:set>
<c:set var="majorHead" value="${resValue.lStrMjrHead}"></c:set>
<c:set var="subMajorHead" value="${resValue.lStrSubMjrHead}"></c:set>
<c:set var="minorHead" value="${resValue.lStrMinorHead}"></c:set>
<c:set var="subMinorHead" value="${resValue.lStrSubMinorHead}"></c:set>
<c:set var="subHead" value="${resValue.lStrSubHead}"></c:set>
<c:set var="demandCode" value="${resValue.lStrDemandCode}"></c:set>
<c:set var="chargeVoted" value="${resValue.lStrChargedVoted}"></c:set>
<c:set var="planNonPlan" value="${resValue.lStrPlanNonPlan}"></c:set>
<c:set var="billCntrlNo" value="${resValue.lStrBillCntrlNo}"></c:set>
<c:set var="forMonthYear" value="${resValue.lStrForMonthYear}"></c:set>

<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript">
function viewBankBranchReportDtls(billNo,bankCode,branchCode)
{
	showProgressbar();
	var url = "ifms.htm?actionFlag=viewMonthlyPensionInnerBill&billNo="+billNo+"&bankCode="+bankCode+"&branchCode="+branchCode;
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "MonthlyPensionBankBranchBill", urlstyle);
	hideProgressbar();

}
</script>
<div style="text-align:center">
	<b>MTR 38</b><BR/>
	<b>2071 PENSION BILL FOR THE MONTH OF ${forMonthYear}</b><BR/>
	<b>Scheme Description&nbsp;&nbsp;:&nbsp;&nbsp;</b>${schemeName}<BR/>
	<b>Scheme Code&nbsp;&nbsp;:&nbsp;&nbsp;</b> ${schemeNo} &nbsp;&nbsp;&nbsp;&nbsp;<b>Demand Code&nbsp;&nbsp;:&nbsp;&nbsp;</b>${demandCode} &nbsp;&nbsp;&nbsp;&nbsp;
	<b>Major Head&nbsp;&nbsp;:&nbsp;&nbsp;</b>${majorHead} &nbsp;&nbsp;&nbsp;&nbsp;<b>Sub Major Head&nbsp;&nbsp;:&nbsp;&nbsp;</b>${subMajorHead} &nbsp;&nbsp;&nbsp;&nbsp;
	<b>Minor Head&nbsp;&nbsp;:&nbsp;&nbsp;</b>${minorHead}&nbsp;&nbsp;&nbsp;&nbsp; <b>Sub Minor Head&nbsp;&nbsp;:&nbsp;&nbsp;</b> ${subMinorHead} &nbsp;&nbsp;&nbsp;&nbsp;
	<b>Sub Head&nbsp;&nbsp;:&nbsp;&nbsp;</b>${subHead} &nbsp;&nbsp;&nbsp;&nbsp; <b>Charged/Voted&nbsp;&nbsp;:&nbsp;&nbsp;</b>${chargeVoted} &nbsp;&nbsp;&nbsp;&nbsp; 
	<b>Plan/Non Plan&nbsp;&nbsp;:&nbsp;&nbsp;</b>${planNonPlan}<br/>
	<b>Bill Control No&nbsp;&nbsp;:&nbsp;&nbsp;</b>${billCntrlNo}
</div>
<fieldset style="width: 100%;" class="tabstyle">
	<legend id="headingMsg">
		<b><fmt:message key="BANKBRANCHRPT.HDG" bundle="${pensionLabels}"></fmt:message></b>
	 </legend>
	 <hdiits:form name="viewBankBranchReport" method="post" validate="">
	 <div class="scrollablediv" >
		<display:table list="${VOList}" id="vo"  requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%"  partialList="true" 
						 offset="1"  size="${totalRecords}" sort="external"  defaultorder="descending" cellpadding="5" defaultsort="3" excludedParams="ajax" >
			<display:column titleKey="BANKBRANCHRPT.BANKNAME" headerClass="datatableheader" sortable="true" style="width:20%;" >
				<lable>${vo[4]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.BRANCHNAME" headerClass="datatableheader" sortable="true" style="width:20%;" >
				<lable>${vo[5]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.NOOFPNSR" headerClass="datatableheader" sortable="true" style="width:10%;" >
				<lable>${vo[6]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.GROSSAMT"  headerClass="datatableheader" sortable="true" style="width:15%;text-align:right;">
				<lable>${vo[7]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.RECAMT" headerClass="datatableheader" sortable="true" style="width:10%;text-align:right;" >
				<lable>${vo[8]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.NETAMT"  headerClass="datatableheader" sortable="true" style="width:15%;text-align:right;" >
				<lable>${vo[9]}</lable>
			</display:column>
			<display:column titleKey="BANKBRANCHRPT.VIEWDTL"  headerClass="datatableheader" style="width:10%;" >
				<lable><a href="#" onclick="javascript:viewBankBranchReportDtls('${vo[0]}','${vo[19]}','${vo[20]}');">View Details</a></lable>
			</display:column>
		</display:table>
	</div>
	 </hdiits:form>
</fieldset>
<br/>
<div style="text-align:center;">
	<hdiits:button name="btnClose" id="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>