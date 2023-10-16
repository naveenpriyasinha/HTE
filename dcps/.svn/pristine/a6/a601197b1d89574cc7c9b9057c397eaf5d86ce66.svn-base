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
<c:set var="elementCode" value="${resValue.elementCode}" />

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>

<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>

<input type="hidden" name="hdnShowCaseFor" id="hdnShowCaseFor" value="${showCaseFor}"/>
<script>
function showPensionCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
function showCorrectionDtls(url)
{
	var newWindow;
	var urlstyle = "height=500,width=600,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "CorrectionDtls", urlstyle);
}
function approveModification()
{
	pensionerId = "";
	getPensionerDtlId();
	var uri;
	uri = 'ifms.htm?actionFlag=approveModification&pensionerCode='+pensionerId;
	approveUsingAjax(uri,pensionerId);
	
	
}
function approveUsingAjax(uri,pensionerId)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&pensionerCode="+pensionerId,
		        onSuccess: function(myAjax) {
				approveOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
//	   xmlHttp=GetXmlHttpObject();

//	   if (xmlHttp==null)
//	   {
//	      return;
//	   }  
//	   xmlHttp.onreadystatechange=approveOnStateChanged;
//	   xmlHttp.open("POST",uri,false);
//	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	   xmlHttp.send(uri);
}


function approveOnStateChanged(myAjax)
{
	 var XMLDoc=myAjax.responseXML.documentElement;
	 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

	 if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == "Approved")
	 {
		 approveModifiedCase(document.getElementById("hdnShowCaseFor").value);
	 }
//	if (xmlHttp.readyState==complete_state)
//	   { 
//		  var XMLDoc=xmlHttp.responseXML.documentElement;
//	    
//	      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//	      if(XmlHiddenValues[0].childNodes[0].text == "Approved")
//	      {
//		    approveModifiedCase(document.getElementById("hdnShowCaseFor").value);
//	      }
	        

//	   }
}
</script>
<script type="text/javascript">
caseStatusModified = '<fmt:message key="STATFLG.MODIFIED" bundle="${pensionConstants}"></fmt:message>';
caseStatusApproved = '<fmt:message key="STATFLG.APPROVED" bundle="${pensionConstants}"></fmt:message>';
caseStatusRejected = '<fmt:message key="STATFLG.REJECTED" bundle="${pensionConstants}"></fmt:message>';
</script>

<hdiits:form name="SavedCaseList" method="post" validate="">
 <jsp:include page="/WEB-INF/jsp/pensionpay/searchPensionCase.jsp" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
	<c:if test="${curRole == '365450'}">
			<b><fmt:message key="PPMT.LIBRARY" bundle="${pensionLabels}"></fmt:message></b>
	</c:if>
	<c:if test="${curRole == '365451'}">
			<b><fmt:message key="PPMT.VIEWMODIFIEDCASES" bundle="${pensionLabels}"></fmt:message></b>
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
			sortable="true" style="width:10%">
			<c:choose>
			<c:when test="${showCaseFor == '20'}">
				<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&showReadOnly=Y&pensionerId=${vo.pensionerId}&showCaseFor=${showCaseFor}&currRole=${curRole}"></c:set>
				<a href="javascript:void(0)" onclick="javascript:showPensionCase('${URLLink}');">
				<div id="lblPpoNo${vo_rowNum}">
			</c:when>
			<c:otherwise>
				<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&showReadOnly=Y&pensionerId=${vo.pensionerId}&showCaseFor=${showCaseFor}&currRole=${curRole}"></c:set>
				<a href="javascript:void(0)" onclick="javascript:showPensionCase('${URLLink}');">
				<div id="lblPpoNo${vo_rowNum}">
			</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${vo.caseStatus == 'Rejected'}">
					<b> <font color="red"> <c:out value="${vo.ppoNo}"></c:out></font> </b>
				</c:when>
				<c:otherwise>
				 	<c:out value="${vo.ppoNo}"></c:out>
				</c:otherwise>
			</c:choose>
			
			</div></a>
		</display:column>
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left" >
			<c:choose>
				<c:when test="${vo.caseStatus == 'Rejected'}">
					<c:if test="${vo.deathDate != null}">
						<b> <font color="red"><label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.familyMemName}</label></font> </b>
					</c:if>
					<c:if test="${vo.deathDate == null}">
						<b> <font color="red"><label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label></font> </b>
					</c:if>
				</c:when>
				<c:otherwise>
				 	<c:if test="${vo.deathDate != null}">
						<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.familyMemName}</label>
					</c:if>
					<c:if test="${vo.deathDate == null}">
						<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label>
					</c:if>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="PPMT.CASESTATUS"
				headerClass="datatableheader" sortable="true" style="width:10%;text-align:left">
				<c:choose>
					<c:when test="${vo.caseStatus == 'ModifiedByAuditor'}">
						<c:set var="URLLink" value="ifms.htm?actionFlag=showModificationByAuditor&pensionerCode=${vo.pensionerId}&ppoNo=${vo.ppoNo}"></c:set>
						<a href="javascript:void(0)" onclick="javascript:showCorrectionDtls('${URLLink}');">
						<label name="txtCaseStatus${vo_rowNum}" id="txtCaseStatus${vo_rowNum}">Modified</label>
					</c:when>
					<c:when test="${vo.caseStatus == 'Modified'}">
						<c:set var="URLLink" value="ifms.htm?actionFlag=showModificationByAuditor&pensionerCode=${vo.pensionerId}&ppoNo=${vo.ppoNo}"></c:set>
						<a href="javascript:void(0)" onclick="javascript:showCorrectionDtls('${URLLink}');">
						<label name="txtCaseStatus${vo_rowNum}" id="txtCaseStatus${vo_rowNum}">${vo.caseStatus}</label>
					</c:when>
					<c:when test="${vo.caseStatus == 'Rejected'}">
						<c:set var="URLLink" value="ifms.htm?actionFlag=showModificationByAuditor&pensionerCode=${vo.pensionerId}&ppoNo=${vo.ppoNo}"></c:set>
						<a href="javascript:void(0)" onclick="javascript:showCorrectionDtls('${URLLink}');">
						<b> <font color="red"><label name="txtCaseStatus${vo_rowNum}" id="txtCaseStatus${vo_rowNum}">${vo.caseStatus}</label></font> </b>
					</c:when>
					<c:otherwise>
						<label name="txtCaseStatus${vo_rowNum}" id="txtCaseStatus${vo_rowNum}">${vo.caseStatus}</label>
					</c:otherwise>
				</c:choose>
				
		</display:column>
		<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader"	sortable="true" style="width:20%;text-align:left;" >
			<c:choose>
					<c:when test="${vo.caseStatus == 'Rejected'}">
						<b> <font color="red"> <label name="txtBankName${vo_rowNum}" id="txtBankName${vo_rowNum}">${vo.bankName}</label></font> </b>
					</c:when>
					<c:otherwise>
					 	<label name="txtBankName${vo_rowNum}" id="txtBankName${vo_rowNum}">${vo.bankName}</label>
					</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader"	sortable="true" style="width:17%;text-align:left;" >
			<c:choose>
					<c:when test="${vo.caseStatus == 'Rejected'}">
						<b> <font color="red"> <label name="txtBranchName${vo_rowNum}" id="txtBranchName${vo_rowNum}">${vo.branchName}</label></font> </b>
					</c:when>
					<c:otherwise>
					 	<label name="txtBranchName${vo_rowNum}" id="txtBranchName${vo_rowNum}">${vo.branchName}</label>
					</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"	style="width:10%;text-align:left;" >
			<c:choose>
					<c:when test="${vo.caseStatus == 'Rejected'}">
						<b> <font color="red"> <label name="txtAccountNo{vo_rowNum}" id="txtAccountNo${vo_rowNum}">${vo.accountNo}</label></font> </b>
					</c:when>
					<c:otherwise>
					 	<label name="txtAccountNo${vo_rowNum}" id="txtAccountNo${vo_rowNum}">${vo.accountNo}</label>
					</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"	style="width:20%;text-align:left;" >
			<c:choose>
					<c:when test="${vo.caseStatus == 'Rejected'}">
						<b> <font color="red"> <label name="txtAuditorName${vo_rowNum}" id="txtAuditorName${vo_rowNum}">${vo.auditorFname} ${vo.auditorMname} ${vo.auditorLname}</label></font> </b>
					</c:when>
					<c:otherwise>
					 	<label name="txtAuditorName${vo_rowNum}" id="txtAuditorName${vo_rowNum}">${vo.auditorFname} ${vo.auditorMname} ${vo.auditorLname}</label>
					</c:otherwise>
			</c:choose>
		</display:column>	
		
	</display:table>
</fieldset>
<br/>
<input type="hidden" name="txtElementCode" id="txtElementCode" value="${elementCode}" />
<div style="text-align:center;">
	<c:if test="${curRole == '365450'}">
	<hdiits:button type="button" name="forward" style="width:150px" captionid="PPMT.FORWARDTOATO"  bundle="${pensionLabels}" onclick="forwardModifiedCaseToAto(${showCaseFor})"/>
	<!--<hdiits:button name="PnsnSnTopeer" captionid="PPMT.SENDTOPEER" bundle="${pensionLabels}" type="button" style="width:130px" />	-->
	</c:if>	
	<c:if test="${curRole == '365451'}">
	<hdiits:button type="button" name="approve" captionid="PPMT.APPROVE"  bundle="${pensionLabels}" onclick="approveModification();"/>
	<hdiits:button type="button" name="reject" captionid="PPMT.REJECT"  bundle="${pensionLabels}" onclick="rejectModifiedCase(${showCaseFor})"/>
	</c:if>	
	<hdiits:button type="button" name="close" captionid="PPMT.CLOSE" bundle="${pensionLabels}"  onclick="winCls();"/>
</div>	
</hdiits:form>
<% }catch(Exception e){
e.printStackTrace();
}%>