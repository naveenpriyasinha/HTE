<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>

<script type="text/javascript"  src="script/common/common.js"></script>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels"
	var="pensionLabels" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.PensionNameRequestList}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="agCircle" value="${resValue.agCircle}"></c:set>
<script>
function showNamePensionBillRequestDtls(url)
{
	document.frmNameRequest.action = url;
	document.frmNameRequest.method = "post";
	document.frmNameRequest.submit();
}
function rejectCase(sevaarthId)
{
	var uri = "ifms.htm?actionFlag=rejectCaseByAG";
	var url = "sevaarthId="+sevaarthId;
	
	var answer = confirm("Are you sure you want to Reject this case?");
	if(answer)
	{
		 //showProgressbar();
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						dataStateChangedForRejectCase(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	

}

function dataStateChangedForRejectCase(myAjax)
{
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
		var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
								
		if (successFlag=='true') {
			alert('Pension Case is Rejected');
			//hideProgressbar();
			self.location.href = "ifms.htm?actionFlag=loadForwardedFiles";
		}
}

function showCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";

	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
//Added by harsh
function searchingBySevaarthId(){	
	
	var sevaarthId=document.getElementById("cmbSevaarthId").value;	
	var url = "ifms.htm?actionFlag=loadForwardedFilesBySevaarthId&SevaarthId=" + sevaarthId;

	self.location.href=url;
	}
</script>
<hdiits:form name="frmNameRequest" method="post" validate="">


			<table>
<tr align="left" >
<td align="left">Sevaarth ID
		</td>
		<td></td>
		<c:choose>
		<c:when test="${resValue.sevaarthId == null}">
		<td align="left">
			<input type="text" size="17%" name="cmbSevaarthId" id="cmbSevaarthId" >
			<a href="#" onclick="searchingBySevaarthId()"><img src="images/search.gif" /></a>
		</td>
		</c:when>
		<c:otherwise>
		<td align="left" >
			<input type="text" size="17%" name="cmbSevaarthId" id="cmbSevaarthId" value="${resValue.sevaarthId}">
			<a href="#" onclick="searchingBySevaarthId()"><img src="images/search.gif" /></a>
		</td>
		</c:otherwise>
		</c:choose>
		</tr>
		</table>
		<fieldset class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.APPROVEDFILES" bundle="${pensionLabels}"></fmt:message></b>
</legend>
<input type="hidden" name="agCircle" value="${agCircle }"> 
<div  class="scrollablediv" >
<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%=Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
				
	

	<display:column titleKey="PPMT.INWARDNO" headerClass="datatableheader" class="oddcentre"
						sortable="true" style="text-align: left;">
					<c:set var="URLLink" value="ifms.htm?actionFlag=loadPensionCaseInwardForm&showReadOnly=Y&inwardId=${vo[6]}"></c:set>
				   	<a href="javascript:void(0)" id="${vo[1]}" onclick="javascript:showCase('${URLLink}')"> 
						${vo[1]}
					 </a>
				    </display:column>
	 <!-- || Sevaarth ID || Name Of employee || Department || Date of retirement || Main File || Family record
	  || Non qualifying record || Nominee Record || -->
	
	<display:column titleKey="PPMT.SEVAARTHID" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo[2]}
	</display:column>

	<display:column titleKey="PPMT.NAMEOFEMPLOYEE" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo[3]}
	</display:column>
	<display:column titleKey="PPMT.DEPARTMENT" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo[4]}
	</display:column>
	<display:column titleKey="PPMT.DATEOFRETIREMENT" headerClass="datatableheader"
			sortable="false" style="width:10%;text-align:left" >${vo[5]}
	</display:column>
	
	
	<display:column titleKey="PPMT.MAINFILE" headerClass="datatableheader"
	style="width:15%;text-align:center">
	<c:set var="URLLink" value="ifms.htm?actionFlag=excelForMainDtls&sevaarthId=${vo[2]}&agCircle=${agCircle}"></c:set>
	<a href="javascript:void(0)" onclick="javascript:showNamePensionBillRequestDtls('${URLLink}');">Download</a>
	</display:column>	
	
	<display:column titleKey="PPMT.FAMILYRECORD" headerClass="datatableheader"
	style="width:15%;text-align:center">
	<c:set var="URLLink" value="ifms.htm?actionFlag=excelForFlyDtls&sevaarthId=${vo[2]}&agCircle=${agCircle}"></c:set>
	<a href="javascript:void(0)" onclick="javascript:showNamePensionBillRequestDtls('${URLLink}');">Download</a>
	</display:column>
	
	<display:column titleKey="PPMT.NONQUALIFYING" headerClass="datatableheader"
	style="width:15%;text-align:center">
	<c:set var="URLLink" value="ifms.htm?actionFlag=excelForpnsnBrkDtls&sevaarthId=${vo[2]}&agCircle=${agCircle}"></c:set>
	<a href="javascript:void(0)" onclick="javascript:showNamePensionBillRequestDtls('${URLLink}');">Download</a>
	</display:column>

	<display:column titleKey="PPMT.NOMINEERECORD" headerClass="datatableheader"
	style="width:15%;text-align:center">
	<c:set var="URLLink" value="ifms.htm?actionFlag=excelForNomiDtls&sevaarthId=${vo[2]}&agCircle=${agCircle}"></c:set>
	<a href="javascript:void(0)" onclick="javascript:showNamePensionBillRequestDtls('${URLLink}');">Download</a>
</display:column>

	<display:column titleKey="PPMT.REJECT" headerClass="datatableheader"
	style="width:15%;text-align:center">
	<c:set var="URLLink" value=""></c:set>
	<a href="javascript:void(0)" onclick="rejectCase('${vo[2]}');">Reject</a>
	</display:column>


</display:table>
</div>
</fieldset>
</hdiits:form>



<div align='center'>
<hdiits:button name="btnClose" type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"  />
</div>

