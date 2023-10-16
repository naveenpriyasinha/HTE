<%try{%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VOList" value="${resValue.lLstSavedCaseVO}" />
<c:set var="showCaseFor" value="${resValue.showCaseFor}" />

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>

<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script>
function showPensinCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
</script>
<hdiits:form name="ScheduleList" method="post" validate="">
 <jsp:include page="/WEB-INF/jsp/pensionpay/searchPensionCase.jsp" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.IDENTSCHEDULE" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
<div style="width:100%;overflow:auto;height:400px;" >
	<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center">
			<input type="hidden" name="hdnPensionerId${vo_rowNum}" id="hdnPensionerId${vo_rowNum}" value="${vo.pensionerId}"/>
			<input type="hidden" name="hdnPnsnrDtlsId${vo_rowNum}" id="hdnPnsnrDtlsId${vo_rowNum}" size="30" value="${vo.pensionerDtlsId}">
			<input type="hidden" name="hdnpnsnrqstid${vo_rowNum}" id="hdnpnsnrqstid${vo_rowNum}" value="${vo.pensionRequestId}" >
			<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&pensionerId=${vo.pensionerId}&showReadOnly=Y"></c:set>
			<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			<div id="lblPpoNo${vo_rowNum}">${vo.ppoNo}</div></a>
		</display:column>
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center" ><label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label></display:column>
		<display:column titleKey="PPMT.CALLDATE" headerClass="datatableheader" style="width:20%;text-align:center" sortable="true"> 
			<label name="txtCalledDate${vo_rowNum}" id="txtCalledDate${vo_rowNum}" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.callDate}"/></label>
		</display:column>
		<display:column titleKey="PPMT.CALLTIME" headerClass="datatableheader" style="width:20%;text-align:center" sortable="true">
			<label name="txtCalledTime${vo_rowNum}" id="txtCalledTime${vo_rowNum}">${vo.callTime}</label>
		</display:column>
		<display:column titleKey="PPMT.STATUS" headerClass="datatableheader"
						style="width:20%;text-align:center" sortable="true" ><label name="txtSchStatus${vo_rowNum}" id="txtSchStatus${vo_rowNum}"><c:choose><c:when test="${vo.identStatus != null and vo.identStatus != ''}">${vo.identStatus}</c:when><c:otherwise>Not Scheduled</c:otherwise></c:choose></label>
		</display:column>
	</display:table>
</div>
</fieldset>
<br/>
<div style="text-align:center;" > 
		<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="winCls();"/>
</div>		
</hdiits:form>
<% }catch(Exception e){
e.printStackTrace();
}%>	