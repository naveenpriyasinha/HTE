<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/pensionpay/LifeCertificate.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.ReceivedLifeCertificateList}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>


<hdiits:form name="frmLifeCerificate" id="frmLifeCerificate" method="post" validate="">
<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.RCVLIFECERTI" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<div style="width:100%;overflow:auto;height:100%;" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	

					
					
	<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%" >
			<input type="checkbox" style="text-align:center" name="chkbxPensionerCode"
				id="chkbxPensionerCode_${vo_rowNum}"
				onclick="" value="${vo.pensionerCode}" />

	</display:column>	
	 		
	<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo.ppoNo}

	</display:column>
	
	
	<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >
			<c:choose>
			<c:when test="${vo.aliveFlag == 'Y'}">
			${vo.name}
			</c:when>
			<c:otherwise>
			${vo.familyName}
			</c:otherwise>
			</c:choose>
			
			
	</display:column>

	<display:column titleKey="PPMT.VOLPAGE" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left"  >
						
			${vo.ledgerNo}/${vo.pageNo}
			
	</display:column>
	<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left"  >${vo.bankName}
			
	</display:column>
	<display:column titleKey="PPMT.BANKBRANCHNAME" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left"  >${vo.branchName}
			
	</display:column>
	<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left"  >${vo.accountNo}
			
	</display:column>
	<display:column titleKey="PPMT.RECEIVEDDATE" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:center"  >
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.receivedDate}"/>	
			
	</display:column>
	<display:column titleKey="PPMT.LIFECERTFLAG" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:center"  >${vo.lifeCertFlag}
			<input type="hidden" name="hdnLifeCertFlag" id="hdnLifeCertFlag${vo_rowNum}" value="${vo.lifeCertFlag}"/>
	</display:column>
</display:table>


</div>
</fieldset>
<div style="width:100%;overflow:auto;height:100%;">

<table width="90%" align="center" id="tblReceivedBtn">
	<tr>
		<td  align="center" width="100%">
			<hdiits:button type="button" captionid="PPMT.APPROVE" bundle="${pensionLabels}" id="btnApprove" name="btnApprove" onclick="approveLifeCertificate()" />&nbsp;&nbsp;&nbsp;
			<hdiits:button id="btnClose" name="btnClose"	type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />
		</td>
	</tr>
</table>
</div>
</hdiits:form>