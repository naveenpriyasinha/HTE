<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>

<script  type="text/javascript"  src="script/pension/Common.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VOList" value="${resValue.lLstSavedCaseVO}" />
<c:set var="showCaseFor" value="${resValue.showCaseFor}" />
	
<script type="text/javascript" src="script/pension/Common.js"></script>	
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
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

<hdiits:form name="SavedCaseList" method="post" validate="">
 <jsp:include page="/WEB-INF/jsp/pensionpay/searchPensionCase.jsp" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
	<c:if test="${curRole == '365450'}">
			<b><fmt:message key="PPMT.DRFTPNSNCASES" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${curRole == '365451'}">
			<b><fmt:message key="PPMT.VIEWNEWCASES" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>	
	</legend> 
<div class="scrollablediv" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
		<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbxPesnionerNo_${vo_rowNum}"
				onclick="" value="${vo.ppoNo}_${vo_rowNum}" />
			<input type="hidden" name="hdnPensionerId${vo_rowNum}" id="hdnPensionerId${vo_rowNum}" value="${vo.pensionerId}"/>
			<input type="hidden" name="hdnPnsnrDtlsId${vo_rowNum}" id="hdnPnsnrDtlsId${vo_rowNum}" size="30" value="${vo.pensionerDtlsId}">
			<input type="hidden" name="hdnpnsnrqstid${vo_rowNum}" id="hdnpnsnrqstid${vo_rowNum}" value="${vo.pensionRequestId}" >
		</display:column>
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left;">
			<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&pensionerId=${vo.pensionerId}"></c:set>
			<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			<div id="lblPpoNo${vo_rowNum}">${vo.ppoNo}</div></a>
		</display:column>
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left;" >
			<c:choose>
			<c:when test="${vo.deathDate != null}">
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.familyMemName}</label>
			</c:when>
			<c:otherwise>
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label>
			</c:otherwise>
			</c:choose>
			</display:column>
		<display:column titleKey="PPMT.PSD" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:center;" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.commencementDate}"/>
		</display:column>
		<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:20%;text-align:left;" ><label name="txtAuditorName${vo_rowNum}" id="txtAuditorName${vo_rowNum}">${vo.auditorFname} ${vo.auditorMname} ${vo.auditorLname}</label>
		</display:column>
	</display:table>
</div>
</fieldset>
<br/>

<div style="text-align:center;">
<c:if test="${curRole == '365450'}">
	<hdiits:button type="button" name="forward"  style="width:150px"  captionid="PPMT.FORWARDTOATO"  bundle="${pensionLabels}" onclick="forwardCaseUsingAjax(${showCaseFor})"/>
</c:if>
<c:if test="${curRole == '365451'}">
	<hdiits:button name="btnApprove" id="btnApprove" type="button"  captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick="forwardCaseUsingAjax(${showCaseFor})"/>
</c:if>														
	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="winCls();"/>
</div>	
</hdiits:form>