<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<script type="text/javascript" src="script/gpf/requestProcessing.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script>
function changeRequest(){
	var requestArr = document.GPFRequestProcessForm.RadioButtonRequest ;
	var requestValue;
	for (var i=0; i<requestArr.length; i++)
	{
		  if (requestArr[i].checked == true)
		  {
			  requestValue = requestArr[i].value ;
		  }
	}	
	if(requestValue == '1'){
		document.getElementById('chngSub').style.display ='';
		document.getElementById('refAdvance').style.display ='none';
		document.getElementById('nonRefAdvance').style.display ='none';
		document.getElementById('finalWithdraw').style.display ='none';
	}else if(requestValue == '2'){
		document.getElementById('chngSub').style.display ='none';
		document.getElementById('refAdvance').style.display ='';
		document.getElementById('nonRefAdvance').style.display ='none';
		document.getElementById('finalWithdraw').style.display ='none';
	}else if(requestValue == '3'){
		document.getElementById('chngSub').style.display ='none';
		document.getElementById('refAdvance').style.display ='none';
		document.getElementById('nonRefAdvance').style.display ='';
		document.getElementById('finalWithdraw').style.display ='none';
	}else if(requestValue == '4'){
		document.getElementById('chngSub').style.display ='none';
		document.getElementById('refAdvance').style.display ='none';
		document.getElementById('nonRefAdvance').style.display ='none';
		document.getElementById('finalWithdraw').style.display ='';
	}
}
</script>
<hdiits:form name="GPFRequestProcessForm" encType="multipart/form-data"
	validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="empVO" value="${resValue.GPFEmpVO}"/>
<c:set var="UserType" value="${resValue.userType}"/>
<c:set var="toPost" value="${resValue.GPFPostIdForDEO}"/>
<c:set var="RAdvanceDtlVO" value="${resValue.savedAdvanceRADtls}"></c:set>
<c:set var="changeSubList" value="${resValue.changeSubList}" />
<c:set var="finalWithDrawal" value="${resValue.finalWithDrawal}"/>
<c:set var="TillDateCredit" value="${resValue.TillDateCredit}"></c:set>

<input type="hidden"  name='ForwardToPost' id="ForwardToPost" value="${toPost}" />
<input type="hidden"  name='txtGpfAccNo' id="txtGpfAccNo" value="${empVO[6]}" />
<input type="hidden"  name='txtAccBalance' id="txtAccBalance" value="${empVO[1]}" />
<input type="hidden"  name='FinYearList' id="FinYearList" value="${FinYearList}" />
<input type="hidden"  name='hidBasicPay' id="hidBasicPay" value="${empVO[3]}" />
<input type="hidden" name="hidSalary" id="hidSalary" value="${empVO[3]+resValue.DPOrGP}" />
<input type="hidden" name="hidSevaarthId" id="hidSevaarthId" value="${resValue.SevaarthId}" />
<input type="hidden" name="hidNetBalance" id="hidNetBalance" value="${resValue.OpeningBalance +TillDateCredit[0]+TillDateCredit[1]-resValue.AdvanceSanctioned-resValue.WithdrawalSanctioned}">
<input type="hidden"  name='hidReqInProgress' id="hidReqInProgress" value="${resValue.lBlOneRequestExists}" />
<fmt:formatDate value="${empVO[7]}" pattern="dd/MM/yyyy" var="DOJ"/>
<fmt:formatDate value="${empVO[16]}" pattern="dd/MM/yyyy" var="DOS"/>
<fmt:formatDate value="${empVO[21]}" pattern="dd/MM/yyyy" var="DOB"/>
<input type="hidden"  name='hidDateOfJoin' id="hidDateOfJoin" value="${DOJ}" />
<input type="hidden"  name='hidDateOfSuperAnnuation' id="hidDateOfSuperAnnuation" value="${DOS}" />
<input type="hidden"  name='hidDateToCompare' id="hidDateToCompare"/>

  <c:if test="${resValue.requestType == 'CS'}">
      <c:set var="CSchk" value="checked"/>
      <c:set var="CSVisible" value=""/>
      <c:set var="RAVisible" value="none"/>
      <c:set var="NRAVisible" value="none"/>
      <c:set var="FWVisible" value="none"/>
  </c:if>
  <c:if test="${resValue.requestType == 'RA'}">
      <c:set var="RAchk" value="checked"/>
      <c:set var="CSVisible" value="none"/>
      <c:set var="RAVisible" value=""/>
      <c:set var="NRAVisible" value="none"/>
      <c:set var="FWVisible" value="none"/>
  </c:if>
  <c:if test="${resValue.requestType == 'NRA'}">
      <c:set var="NRAchk" value="checked"/>
      <c:set var="CSVisible" value="none"/>
      <c:set var="RAVisible" value="none"/>
      <c:set var="NRAVisible" value=""/>
      <c:set var="FWVisible" value="none"/>
  </c:if>
  <c:if test="${resValue.requestType == 'FW'}">
      <c:set var="FWchk" value="checked"/>
      <c:set var="CSVisible" value="none"/>
      <c:set var="RAVisible" value="none"/>
      <c:set var="NRAVisible" value="none"/>
      <c:set var="FWVisible" value=""/>
  </c:if>

<table width="80%"><tr><td>
<fieldset style="width:100%" class="tabstyle" ><legend><fmt:message key="CMN.EMPDETAILS" bundle="${gpfLabels}"></fmt:message></legend>
<table cellspacing = "4" cellpadding = "4" >
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.NAME" bundle="${gpfLabels}"></fmt:message></b>
		<input type="hidden"  name='hidGpfAccNo' id="hidGpfAccNo"  value="${empVO[6]}" /></td>
		<td align="left" width="40%"><c:out value="${empVO[0]}" /></td>
		<td width="25%" align="left" ><b><fmt:message key="CMN.CURRENTBALANCE" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left"><fmt:formatNumber type="number" pattern="##,##,###.##" value="${empVO[1]}" /></td>
	</tr>
	
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.GENDER" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%">
		<c:if test="${empVO[2] == 'M'}"><c:out value="Male" /></c:if>
		<c:if test="${empVO[2] == 'F'}"><c:out value="Female" /></c:if>
		</td>
		<td width="25%" align="left" ><b><fmt:message key="CMN.PAYCOMMISSION" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left"><c:out value="${empVO[17]}" /></td>
	</tr>
	
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.DESIGNATION" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${empVO[4]}" /></td>
		<td width="25%" align="left" >
		<c:if test="${empVO[17] == '5th Pay Commission'}">
		<b><fmt:message key="CMN.PAYINPAYSCALE" bundle="${gpfLabels}"></fmt:message></b>
		</c:if>
		<c:if test="${empVO[17] == '6th Pay Commission'}">
		<b><fmt:message key="CMN.PAYSCALEBAND" bundle="${gpfLabels}"></fmt:message></b>
		</c:if>
		</td>
		<td align="left"><c:out value="${resValue.payScale}" /></td>
	</tr>
	
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.GPFACNO" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${empVO[6]}" /></td>
		<td width="25%" align="left" ><b><fmt:message key="CMN.BASIC" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left"><fmt:formatNumber type="number" pattern="##,##,###.##" value="${empVO[3]}" /></td>
	</tr>
	
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.ADDRESS" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%" >
		<c:out value="${resValue.EmpAddress}" /></td>
		</td>
		<td width="25%" align="left" >
		<c:if test="${empVO[17] == '5th Pay Commission'}">
		<b><fmt:message key="CMN.DP" bundle="${gpfLabels}"></fmt:message></b>
		</c:if>
		<c:if test="${empVO[17] == '6th Pay Commission'}">
		<b><fmt:message key="CMN.GP" bundle="${gpfLabels}"></fmt:message></b>
		</c:if>
		</td>
		<td align="left"><c:out value="${resValue.DPOrGP}" /></td>
	</tr>
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.CONTACT" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${empVO[14]}" /></td>
		<td width="25%" align="left" ><b><fmt:message key="CMN.SALARY" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" ><fmt:formatNumber type="number" pattern="##,##,###.##" value="${empVO[3]+resValue.DPOrGP}" /></td>
	</tr>
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.MOBILE" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${empVO[15]}" /></td>
		<td width="25%" align="left" ><b><fmt:message key="CMN.SUBSCRIOTION" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left"><c:out value="${empVO[5]}" /></td>
	</tr>
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.DOJ" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${DOJ}" /></td>
		<td width="25%"  align="left" ><b><fmt:message key="CMN.DOS" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left"><c:out value="${DOS}" /></td>
	</tr>
	<tr>
		<td width="20%" align="left" ><b><fmt:message key="CMN.DOB" bundle="${gpfLabels}"></fmt:message></b></td>
		<td align="left" width="40%"><c:out value="${DOB}" /></td>
		<td width="25%"></td>
		<td align="left"></td>
	</tr>
</table>

</fieldset>
<br/>
</td></tr>

<tr><td>
	<table >
		<tr>
			<td width="20%">
				<input type="radio" id="RadioButtonRequest" disabled="disabled"
					name="RadioButtonRequest" value="1" onclick="changeRequest();" ${CSchk}/>
				<fmt:message key="CMN.CHANGESUBSCRIPTION" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%">
				<input type="radio" id="RadioButtonRequest" disabled="disabled"
					name="RadioButtonRequest" value="2" onclick="changeRequest();" ${RAchk}/>
				<fmt:message key="CMN.REFADVANCE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%">
				<input type="radio" id="RadioButtonRequest" disabled="disabled"
					name="RadioButtonRequest" value="3" onclick="changeRequest();" ${NRAchk}/>
				<fmt:message key="CMN.NONREFADVANCE" bundle="${gpfLabels}"></fmt:message>
			</td>
			<td width="20%">
				<input type="radio" id="RadioButtonRequest" disabled="disabled"
					name="RadioButtonRequest" value="4" onclick="changeRequest();" ${FWchk}/>
				<fmt:message key="CMN.FINALWITHDRAW" bundle="${gpfLabels}"></fmt:message>
			</td>
		</tr>
	</table>
	</br>
</td></tr>
<tr><td>
<fieldset id="chngSub" class="tabstyle" style="display:${CSVisible}"><legend><fmt:message key="CMN.CHANGESUBSCRIPTION" bundle="${gpfLabels}"></fmt:message></legend><br/>
	<jsp:include page="ChangeSubscription.jsp" />
</fieldset>

<fieldset id="refAdvance" class="tabstyle" style="display:${RAVisible}"><legend><fmt:message key="CMN.REFADVANCE" bundle="${gpfLabels}"></fmt:message></legend><br/>
	<jsp:include page="RefundableAdvance.jsp" />
</fieldset>

<fieldset id="nonRefAdvance" class="tabstyle" style="display:${NRAVisible}"><legend><fmt:message key="CMN.NONREFADVANCE" bundle="${gpfLabels}"></fmt:message></legend><br/>
	<jsp:include page="NonRefundableAdvance.jsp" />
</fieldset>
<fieldset id="finalWithdraw" class="tabstyle" style="display:${FWVisible}"><legend><fmt:message key="CMN.FINALWITHDRAW" bundle="${gpfLabels}"></fmt:message></legend><br/>
	<jsp:include page="FinalWithdrawal.jsp" />
</fieldset>
</td></tr>

<tr><td>
<table width="70%" align="center">
	<tr>
	<td align="center"><table align="center"><tr>
		<c:choose>
		<c:when test="${resValue.userType == 'DEO'}">
		<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.SAVE" bundle="${gpfLables}" id="btnSave" name="btnSave" onclick="SaveRequest();"></hdiits:button></td>

		<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${gpfLables}" id="btnForward" name="btnForward" onclick="forwardRequest(1);"></hdiits:button></td>
		<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${gpfLables}" id="btnBack" name="btnBack" onclick="backButton(1);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == 'DEOAPP'}">
		<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${gpfLables}" id="btnForward" name="btnForward" onclick="forwardRequest(2);"></hdiits:button></td>
		<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.SENDBACK" bundle="${gpfLables}" id="btnSendBack" name="btnSendBack" onclick="rejectRequest(1);"></hdiits:button></td>
		<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${gpfLables}" id="btnBack" name="btnBack" onclick="backButton(2);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == 'HO'}">
		<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLables}" id="btnApprove" name="btnApprove" onclick="approveRequest();"></hdiits:button></td>
		<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.REJECT" bundle="${gpfLables}" id="btnReject" name="btnReject" onclick="rejectRequest(2);"></hdiits:button></td>
		<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${gpfLables}" id="btnBack" name="btnBack" onclick="backButton(3);"></hdiits:button></td>

		</c:when>
		</c:choose>
	</tr>
	</table>
	</td></tr>
</table>
</td></tr>
</table>
</hdiits:form>