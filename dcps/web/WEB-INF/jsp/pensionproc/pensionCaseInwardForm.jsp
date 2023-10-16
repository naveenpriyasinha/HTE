<%try{ %>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionproc.PensionCaseConstants" var="pensionConstants" scope="request" />
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/date.js"></script>
<script type="text/javascript" src="script/login/statusbar.js"></script>

<script type="text/javascript">
CASETYPENEW = '<fmt:message key="CASETYPE.NEW" bundle="${pensionConstants}"></fmt:message>';
CASETYPEREVISION = '<fmt:message key="CASETYPE.REVISION" bundle="${pensionConstants}"></fmt:message>';
FAMILYPNSN='<fmt:message key="PPROC.FAMILYPNSN" bundle="${pensionConstants}"/>';
OUTWARDNO = '<fmt:message key="PPROC.OUTWARDNO" bundle="${pensionAlerts}"></fmt:message>';
OUTWARDDATE = '<fmt:message key="PPROC.OUTWARDDATE" bundle="${pensionAlerts}"></fmt:message>';
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="UpperUserList" value="${resValue.lLstUpperUsers}"></c:set>
<c:set var="LowerUserList" value="${resValue.lLstLowerUsers}" />
<c:set var="curRole" value="${resValue.lStrRole}" />
<c:set var="showReadOnly" value="${resValue.lStrShowReadOnly}"/>
<c:set var="caseStatus" value="${resValue.lStrStatusLookupId}"/>
<c:set var="lObjTrnPnsnProcInwardPensionVO" value="${resValue.lObjTrnPnsnProcInwardPensionVO}"></c:set>
<c:set var="lObjTrnPnsnProcPnsnrDtlsVO" value="${resValue.lObjTrnPnsnProcPnsnrDtlsVO}"></c:set>
<c:set var="lObjTrnPnsnProcPnsnCalcVO" value="${resValue.lObjTrnPnsnProcPnsnCalcVO}"></c:set>
<c:set var="draftFlag" value="${lObjTrnPnsnProcInwardPensionVO.draftFlag}" />
<!-- added by shraddha for deputation module changes-->
<c:set var="flagPen" value="${resValue.flagPen}" />
<input type="hidden" id= "flagPen" name = "flagPen" value="${flagPen}"/>
<c:set var="flagPen2" value="P" />

<input type="hidden" id= "pensionFlag" name = "pensionFlag" value="${lObjTrnPnsnProcInwardPensionVO.pensionFlag}"/>


<c:set var="penOffcDtlsList" value="${resValue.penOffcDtlsList}" />

<!-- added by ankita-->

<c:set var="pensionCaseType" value="${resValue.pensionCaseType}" />
<input type="hidden" id= "pensionCaseType" name = "pensionCaseType" value="${pensionCaseType}"/>

<c:if test="${pensionCaseType=='Provisional'}">
<c:set var="judCasePendingReason" value="${resValue.judCasePendingReason}" />
<input type="hidden" id= "judCasePendingReason" name = "judCasePendingReason" value="${judCasePendingReason}"/>
<c:set var="deptInqPendingReason" value="${resValue.deptInqPendingReason}" />
<input type="hidden" id= "deptInqPendingReason" name = "deptInqPendingReason" value="${deptInqPendingReason}"/>
</c:if>

<c:set var="lagecyFlag" value="${resValue.LegacyFlag}" />
<input type="hidden" id= "hdnLagecyFlag" name = "hdnLagecyFlag" value="${lagecyFlag}"/>

<c:choose>
		<c:when test="${lagecyFlag == 89 }">
			<c:set value="" var="varReadOnly"></c:set>
			<c:set var="varDisplay" value="style='display: none;'"></c:set>
			<c:set var="varDis abled" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplay" value=""></c:set>
			<c:set value="readonly='readonly'" var="varReadOnly"></c:set>
			<c:set var="varDisabled" value="disabled='disabled'"></c:set>
		</c:otherwise>
</c:choose>
<hdiits:form name="PensionInwardForm" id="PensionInwardForm" method="POST" action="" encType="multipart/form-data" validate="true">
	<c:choose>
	<c:when test="${lObjTrnPnsnProcInwardPensionVO.caseType == 'REVISION' && curRole == '700001'}">
		<input type="hidden" id="hdnStatusId" name="hdnStatusId" value="DRAFT"/>
	</c:when>
	<c:otherwise>	
		<input type="hidden" id="hdnStatusId" name="hdnStatusId" value="${lObjTrnPnsnProcInwardPensionVO.caseStatus}"/>
	</c:otherwise>
	</c:choose>
<input type="hidden" id="hdnInwardPensionId" name="hdnInwardPensionId" value="${lObjTrnPnsnProcInwardPensionVO.inwardPensionId}"/>
<!--  <input type="hidden" id="hdnSixPayRetireFlag" name="hdnSixPayRetireFlag" value=""/> -->


<table width="97%" align="center">
	<tr>
		<td width="20%" align="left" >
			Transaction <fmt:message key="PPROC.CASETYPE" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">		 
			<select name="cmbCaseType" id="cmbCaseType" style="width:30%" ${varDisabled} onfocus="onFocus(this)" onblur="onBlur(this);" onchange="" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:choose>
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.caseType == 'NEW'}">
						<option value="<fmt:message key="CASETYPE.NEW" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="CASETYPE.NEW" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="CASETYPE.REVISION" bundle="${pensionConstants}"/>"><fmt:message key="CASETYPE.REVISION" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.caseType == 'REVISION'}">
						<option value="<fmt:message key="CASETYPE.NEW" bundle="${pensionConstants}"/>"><fmt:message key="CASETYPE.NEW" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="CASETYPE.REVISION" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="CASETYPE.REVISION" bundle="${pensionLabels}"/></option>
					</c:when>
					<c:otherwise>
						<option value="<fmt:message key="CASETYPE.NEW" bundle="${pensionConstants}"/>"><fmt:message key="CASETYPE.NEW" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="CASETYPE.REVISION" bundle="${pensionConstants}"/>"><fmt:message key="CASETYPE.REVISION" bundle="${pensionLabels}"/></option>
					</c:otherwise>
				</c:choose>
					<!-- <c:forEach var="PensionType" items="${resValue.lLstTypeOfCase}">
								<c:choose>
										<c:when test="${PensionType.lookupId == lObjTrnPnsnProcInwardPensionVO.inwardCaseTypeLookupId}">
											<option selected="selected" value="<c:out value='${PensionType.lookupId}'/>"><c:out
												value="${PensionType.lookupName}" /></option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value='${PensionType.lookupId}'/>"> <c:out 
												value="${PensionType.lookupName}" /></option>
										</c:otherwise>
									</c:choose>
					</c:forEach> -->
				</select>				
			</td>
			<td width="20%" align="left">
				<fmt:message key="PPROC.PAYCOMN" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">			
			 <select name="cmbPayComsn" id="cmbPayComsn" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="GetScalePostfromDesg();validCommutePer();disableDP();get7PayBasic();" >
				     <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				     <c:choose>				     
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == '5THPAYCOMSN'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					
					
					</c:when>
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == '6THPAYCOMSN'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:when>
						
						<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == 'NonGovtScales'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}" />" selected="selected" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:when>
					
						<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == 'PadmanabhanComm'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:when>
				
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == 'Fourth'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" selected="selected" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					
					</c:when>
						<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == 'ConsolidatedPay'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"  selected="selected"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					
					</c:when>
						<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == 'ShettyCommission'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" selected="selected" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					
					</c:when><c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == '6thPayComissionUGC'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:when>
					
					
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.payCommission == '7THPAYCOMSN'}">
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:when>
					
					
					
					<c:otherwise>
						<option value="<fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.5THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6THPAYCOMSN" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.NonGovtScales" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.PadmanabhanComm" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.Fourth" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.Fourth" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.ConsolidatedPay" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionConstants}"/>" ><fmt:message key="PAYCOMSN.ShettyCommission" bundle="${pensionLabels}"/></option>
						<option value="<fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.6thPayComissionUGC" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionConstants}"/>"><fmt:message key="PAYCOMSN.7THPAYCOMSN" bundle="${pensionLabels}"/></option>
					</c:otherwise>
					</c:choose>
				      <!--  <c:forEach var="PayCmsn" items="${resValue.lLstPayCommission}">
								<c:choose>
										<c:when test="${PayCmsn.lookupId == lObjTrnPnsnProcInwardPensionVO.payCmsnLookupId}">
											<option selected="selected" value="<c:out value='${PayCmsn.lookupId}'/>"><c:out
												value="${PayCmsn.lookupName}" /></option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value='${PayCmsn.lookupId}'/>"> <c:out 
												value="${PayCmsn.lookupName}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach> -->
				</select>	
				<label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
		</tr>
		
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.INWARDID" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">				
			   <input type="text" id="txtInwardId"  size="18"  name="txtInwardId"  value="${lObjTrnPnsnProcInwardPensionVO.sevaarthId}" onfocus="onFocus(this)"  onblur="onBlur(this)" readonly="readonly"/>
			   <input type="button" name="btnCaseSearch" id="btnCaseSearch" type="button" value="Search" onclick="" style="display:none" />
			   <input type="button" name="btnRevised" id="btnRevised" type="button" value="Revised"  onclick="" style="display:none" />			   
			</td>
			<td width="20%" align="left">
				<fmt:message key="PPROC.CLASSOFPNSN" bundle="${pensionLabels}"></fmt:message>
			</td>
			<!-- disabledDeathDate(); -->
			<td width="30%" align="left">
			   <select name="cmbClassOfPnsn" id="cmbClassOfPnsn" disabled="disabled" onfocus="onFocus(this)" onblur="onBlur(this);" style="width: 77%"  tabindex="2" onchange="onChangePensionType();setRetirementDate(txtDateOfBirth);setFP1AndFp2Date();setEmolumnetToDate(2);enableEFPYear();validPensionType();" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<option value="<fmt:message key="PPROC.EXTRAORDINARY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.SUPERANNU" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY64" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.VOLUNTARY65" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING104" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.RETIRING105" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/></option>					
					<option value="<fmt:message key="PPROC.ABSORPTION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INVALID" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.INJURY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPULSORY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPROC.COMPASSIONATE" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/></option>					
					<option value="<fmt:message key="PPROC.COMPENSATION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/></option>										
					<option value="<fmt:message key="PPROC.FAMILYPNSN" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/></option>										
					<option value="<fmt:message key="PPROC.GALLANTRY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/></option>
					
				</select>
			   <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
		</tr>
		<c:if test="${lObjTrnPnsnProcInwardPensionVO.pensionType == 'SUPERANNU'}">
	<script type="text/javascript">
	  document.getElementById("cmbClassOfPnsn").options[1].selected=true;
    </script>
    </c:if>
		<tr>
			<td width="20%" align="left">
				<fmt:message key="PPROC.SEVAARTHID" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			   <input type="text" id="txtSevaarthId" readonly="readonly" style="text-transform: uppercase" size="15" maxlength="11" name="txtSevaarthId"   value="${lObjTrnPnsnProcInwardPensionVO.sevaarthId}" onfocus="onFocus(this)"  onblur="onBlur(this)" />
			    <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
				 <td width="20%" align="left" >
				<fmt:message key="PPROC.PNSNRCATEGORY" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			    <select name="cmbPnsnCatg" id="cmbPnsnCatg" disabled="disabled" onfocus="onFocus(this)" onblur="onBlur(this);" onchange="onChangePnsnrType();setRetirementDate(txtDateOfBirth);setFP1AndFp2Date();setEmolumnetToDate(2);showNPA();commute(radioDoWantCommute);">
				         <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         <c:forEach var="PnsnrType" items="${resValue.lLstPensionerType}">
				         <c:choose>
								<c:when test="${PnsnrType.lookupShortName == lObjTrnPnsnProcInwardPensionVO.pensionerType}">
									<option selected="selected" value='${PnsnrType.lookupShortName}'>
										<c:out value="${PnsnrType.lookupDesc}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value='${PnsnrType.lookupShortName}'>
										<c:out value="${PnsnrType.lookupDesc}"></c:out>									
									</option>
								</c:otherwise>
							</c:choose>
				         </c:forEach>
				       
			   </select>
			 	<input type="text" name="txtOtherPnsnrType" id="txtOtherPnsnrType" maxlength="25" value="${lObjTrnPnsnProcInwardPensionVO.otherPnsnrType}" style="display:none">
		    	<label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>			
			
		</tr>
		
		<tr>
			<td width="20%" align="left">
			<fmt:message key="PPROC.CURRDDOCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
<c:choose>		  	
<c:when test="${pensionCaseType=='Deputation'}">
 <input type="text" id="txtDDOCode" readonly="readonly" size="10"  maxlength="10" name="txtDDOCode"  onKeyPress="numberFormat(event)" value="${resValue.prevDDOCODE}" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
</c:when>
<c:otherwise>
 <input type="text" id="txtDDOCode" readonly="readonly" size="10"  maxlength="10" name="txtDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.ddoCode}" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
</c:otherwise>
</c:choose>

		  	 <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
			
			
			<td width="20%" align="left">
				<fmt:message key="PPROC.DOWANTCOMMUTE" bundle="${pensionLabels}"></fmt:message>
			</td>
			
			<td width="30%" align="left">
			   
			<c:choose>
			<c:when test="${lObjTrnPnsnProcInwardPensionVO.commuateFlag == 78}">
			  <input type="radio"  id="radioDoWantCommuteYes" maxlength="3" name="radioDoWantCommute" value="Y" onclick="commute(radioDoWantCommute)" />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioDoWantCommuteNo" maxlength="3" name="radioDoWantCommute" value="N" onclick="commute(radioDoWantCommute)" checked="checked" />
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>		
			</c:when>
			<c:otherwise>
			 <input type="radio" id="radioDoWantCommuteYes" maxlength="3" name="radioDoWantCommute" value="Y" onclick="commute(radioDoWantCommute)"  checked="checked"  />
			 <fmt:message key="PPROC.YES"	bundle="${pensionLabels}"></fmt:message>
		
			 <input type="radio" id="radioDoWantCommuteNo" maxlength="3" name="radioDoWantCommute" value="N" onclick="commute(radioDoWantCommute)" />
			 <fmt:message key="PPROC.NO"	bundle="${pensionLabels}"></fmt:message>
			 &nbsp;&nbsp;		
			 <div id="divForCom" style="display: inline;">
			  <fmt:message key="PPROC.PEROFCOM" bundle="${pensionLabels}"></fmt:message>
			  </div>
			</c:otherwise>
			</c:choose>
				<c:choose>
					<c:when test="${lObjTrnPnsnProcInwardPensionVO.commuteVal != null && lObjTrnPnsnProcInwardPensionVO.commuteVal != ''}">  
			     		<input type="text"   id="txtDoWantCommute"  size="3" maxlength="5" name="txtDoWantCommute" onKeyPress="amountFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.commuteVal}" onfocus="onFocus(this)"  onblur="onBlur(this);validCommutePer();" style=""/>
			     	</c:when>
			     	<c:otherwise>
			     		<input type="text"   id="txtDoWantCommute"  size="3" maxlength="5" name="txtDoWantCommute" onKeyPress="amountFormat(event)" value="40" onfocus="onFocus(this)"  onblur="onBlur(this);validCommutePer();" style=""/>
			     	</c:otherwise>
			 </c:choose>			 
			 </td>			
	</tr>	
	
	<tr>
		<td width="20%" align="left">
		</td>
		<td width="30%" align="left">
		</td>
		<td width="20%"></td>		
			<td width="30%" align="left">
				 <fmt:message key="PPROC.NOTE"	bundle="${pensionLabels}"></fmt:message>
			</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CURRDDOOFFNAME" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" id="txtDdoOfficeName"  name="txtDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
		
		<td width="20%" align="left"><fmt:message key="PPROC.CURRDDODSGN" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" id="txtDdoDesigName"  name="txtDdoDesigName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.currDdoDesig}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
		
</tr>

	<tr>
<td width="20%" align="left">
			<fmt:message key="PPROC.PENDDOCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			
			 
			
			
<c:choose>		  	
<c:when test="${pensionCaseType=='Deputation'}">
 <input type="text" id="txtPenDDOCode" readonly="readonly" size="10"  maxlength="10" name="txtPenDDOCode"  onKeyPress="numberFormat(event)" value="${resValue.prevDDOCODE}" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
</c:when>
<c:when test="${pensionCaseType=='Regular' &&  flagPen == 'N'}">
		<input type="text" id="txtPenDDOCode"  size="10"  maxlength="10" name="txtPenDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.penDdoCode}" onfocus="onFocus(this)"  onblur="checkDdoCode(this);"/>
</c:when>

<c:when test="${pensionCaseType=='Regular' && (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='FP' || lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P')}">
		<input type="text" id="txtPenDDOCode" readonly="readonly" size="10"  maxlength="10" name="txtPenDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.penDdoCode}" onfocus="onFocus(this)"  onblur="checkDdoCode(this);"/>
</c:when>
<c:when test="${pensionCaseType=='Regular' &&  (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='P1')}">
		<input type="text" id="txtPenDDOCode"  size="10"  maxlength="10" name="txtPenDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.penDdoCode}" onfocus="onFocus(this)"  onblur="checkDdoCode(this);"/>
</c:when>
<c:otherwise>
<input type="text" id="txtPenDDOCode" readonly="readonly" size="10"  maxlength="10" name="txtPenDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.ddoCode}" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
</c:otherwise>
</c:choose>

		  	 <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>	

	<td width="20%" align="left"><fmt:message key="PPROC.PENDDOOFFNAME" bundle="${pensionLabels}"></fmt:message></td>
	
	<c:choose>
	<c:when test="${pensionCaseType=='Regular' && flagPen=='N'}">
<td width="15%" ><input type="text" id="txtPenDdoOfficeName"  name="txtPenDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.penDdoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>
<c:when test="${pensionCaseType=='Regular' && (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='P1' || lObjTrnPnsnProcInwardPensionVO.pensionFlag =='FP' ||  lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P')}">
		<td width="15%" ><input type="text" id="txtPenDdoOfficeName"  name="txtPenDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.penDdoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>
	<c:otherwise>
	<td width="15%"><input type="text" id="txtPenDdoOfficeName"  name="txtPenDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
	</c:otherwise>
	</c:choose>
	</tr>
	<tr>

		<td width="20%" align="left"><fmt:message key="PPROC.PENDDODSGN" bundle="${pensionLabels}"></fmt:message></td>
		<c:choose>
		<c:when test="${pensionCaseType=='Regular' && flagPen=='N'}">
   <td width="15%" > <input type="text" id="txtPenDdoDesigName"  name="txtPenDdoDesigName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.penDdoDesig}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
      </c:when>
   <c:when test="${pensionCaseType=='Regular' && (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='P1' || lObjTrnPnsnProcInwardPensionVO.pensionFlag =='FP' ||  lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P')}">
		 <td width="15%" > <input type="text" id="txtPenDdoDesigName"  name="txtPenDdoDesigName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.penDdoDesig}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>   
		<c:otherwise>
	<td width="15%" ><input type="text" id="txtPenDdoDesigName"  name="txtPenDdoDesigName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.currDdoDesig}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
		</c:otherwise>
		</c:choose>
		
	
	</tr>
	<tr>
	<td width="20%" align="left">
			<fmt:message key="PPROC.GRATDDOCODE" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%" align="left">
			
			 
			
			
<c:choose>		  	
<c:when test="${pensionCaseType=='Deputation'}">
 <input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${resValue.prevDDOCODE}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>
<c:when test="${pensionCaseType=='Deputation' && lObjTrnPnsnProcInwardPensionVO.gratDdoCode != null }">
 <input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>



<c:when test="${pensionCaseType=='Regular' &&  flagPen == 'N'}">
		<input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>

<c:when test="${pensionCaseType=='Regular' && (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='FP' || lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P')}">
		<input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>
<c:when test="${pensionCaseType=='Regular' &&  (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='P1')}">
		<input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>

<c:when test="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode != null}">
		<input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:when>
<c:otherwise>
<input type="text" id="txtGratDDOCode"  size="10"  maxlength="10" name="txtGratDDOCode"  onKeyPress="numberFormat(event)" value="${lObjTrnPnsnProcInwardPensionVO.ddoCode}" onfocus="onFocus(this)"  onblur="getGratOffDtls(this)"/>
</c:otherwise>
</c:choose>

		  	 <label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>	
				<td width="20%" align="left"><fmt:message key="PPROC.GRATDDOOFFNAME" bundle="${pensionLabels}"></fmt:message></td>
	
	<c:choose>
	<c:when test="${pensionCaseType=='Regular' && flagPen=='N'}">
<td width="15%" ><input type="text" id="txtGratDdoOfficeName"  name="txtGratDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>
<c:when test="${pensionCaseType=='Regular' && (lObjTrnPnsnProcInwardPensionVO.pensionFlag =='P1' || lObjTrnPnsnProcInwardPensionVO.pensionFlag =='FP' ||  lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P')}">
		<td width="15%" ><input type="text" id="txtGratDdoOfficeName"  name="txtGratDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>

<c:when test="${lObjTrnPnsnProcInwardPensionVO.gratDdoOfficeName != null}">
		<td width="15%" ><input type="text" id="txtGratDdoOfficeName"  name="txtGratDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcInwardPensionVO.gratDdoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
</c:when>

	<c:otherwise>
	<td width="15%"><input type="text" id="txtGratDdoOfficeName"  name="txtGratDdoOfficeName" readonly="readonly" size="40" value="${lObjTrnPnsnProcPnsnrDtlsVO.ddoOfficeName}" onfocus="onFocus(this)"  onblur="onBlur(this)"/></td>
	</c:otherwise>
	</c:choose>
			</tr>
			
			
	
		<c:if test="${lObjTrnPnsnProcInwardPensionVO.commuateFlag == 89 && lObjTrnPnsnProcInwardPensionVO.pensionerType == 'High Court'}">
	
				<script type="text/javascript">
				
					document.getElementById('txtDoWantCommute').style.display='inline';
				
				</script>
	</c:if>
	<c:if test="${lagecyFlag == 89 }">
	<tr>
		<td width="20%" align="left">
			<fmt:message key="PPROC.OUTWARDNO" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		<input type="text" id="txtOutwardNo"  name="txtOutwardNo"  value="" onfocus="onFocus(this)"  onblur="onBlur(this)"/>
		  	 <label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>
		<td width="20%">
			<fmt:message key="PPROC.OUTWARDDATE" bundle="${pensionLabels}"></fmt:message>
		</td>		
		<td width="30%" align="left">
			<input type="text" name="txtOutwardDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtOutwardDate" value=""	 /> 
			<img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgCpoDate"	onClick='window_open(event,"txtOutwardDate",375,570)'style="cursor: pointer;"/>
		  	<label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>
	</tr>
	</c:if>
</table>

<div id="tabmenu" align="left">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <fmt:message
			key="BASICDETAILS" bundle="${pensionLabels}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent2"> <fmt:message
			key="PAYANDSERVICEDTLS" bundle="${pensionLabels}"></fmt:message> </a></li>
		
		<li><a href="#" rel="tcontent4"> <fmt:message
			key="PPROC.FAMILYDTLS" bundle="${pensionLabels}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent5"> <fmt:message key="RECOVERY"
			bundle="${pensionLabels}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent6"> <fmt:message key="CHECKLIST"
			bundle="${pensionLabels}"></fmt:message> </a></li>
			<li><a href="#" rel="tcontent3"> <fmt:message
			key="PENSIONCALCULATION" bundle="${pensionLabels}"></fmt:message> </a></li>
		<li><a href="#" rel="tcontent7"> <fmt:message key="PPROC.ATHODTLS"
			bundle="${pensionLabels}"></fmt:message> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle"><!--------------------Pension Basic Details--------------- -->
	<div id="tcontent1" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseBasicDtls.jsp" /></div>

	<!-- ------------------Pension Details--------------- -->
	<div id="tcontent2" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCasePayServiceDtls.jsp" /></div>


	<!-- ------------------Family Details--------------- -->
	<div id="tcontent4" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseFamilyDtls.jsp" /></div>
	<!-- ------------------Recovery Details--------------- -->
	<div id="tcontent5" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseRecoveryDtls.jsp" /></div>
	<!-- ------------------Check List--------------- -->
	<div id="tcontent6" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseCheckList.jsp" /></div>
			<!-- ------------------Pension Calculation--------------- -->
	<div id="tcontent3" class="tabcontent" align="left" ><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseCalculation.jsp" /></div>
		
	<div id="tcontent7" class="tabcontent" align="left"><jsp:include
		page="/WEB-INF/jsp/pensionproc/pensionCaseAuthorityDtls.jsp" /></div>
		
	<table width="40%" align="center">
	<tr style="display: none;">
		<td width="20%" align="left" >
				<fmt:message key="PPROC.COMMENTS" bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="30%" align="left">
		 <textarea  id="txtComments" name="txtComments"  style="height: 40px;width: 300px" >${lObjTrnPnsnProcInwardPensionVO.comments}</textarea>
		</td>
		
		</tr>
	<tr style="display: none;">
			<c:if test="${curRole == '800002'}">
		<td width="30%" align="left" style="display: none;"><select name="cmbForwardTo"
				id="cmbForwardTo" style="width: 100%" onfocus="onFocus(this)"
				onblur="onBlur(this);"  onchange="chkValue()">
				<option value="1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
					
			</select>
			</td>
			</c:if>
		 <c:if test="${UpperUserList != null}">
			<td width="20%" align="left"><fmt:message key="PPROC.FORWARDTO"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="70%" align="left"><select name="cmbForwardTo"
				id="cmbForwardTo" style="width: 100%" onfocus="onFocus(this)"
				onblur="onBlur(this);"  onchange="chkValue()">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="UpperUser" items="${UpperUserList}" >
						<option value="${UpperUser[1]}"><c:out value="${UpperUser[0]}"></c:out> </option>
					</c:forEach>
			</select>
			</td>
			
			<td>
			<c:if test="${resValue.lObjTrnPnsnProcInwardPensionVO != null}">
			<hdiits:button name="btnForward" id="btnForward" classcss="bigbutton"
			type="button" captionid="PPROC.FORWARD" bundle="${pensionLabels}"
			onclick="forwardPensionCase();" />
			</c:if>
			
			 </td>
			 </c:if>
			 </tr>
			 <c:choose>
			<c:when test="${resValue.lObjTrnPnsnProcInwardPensionVO != null}">
			 <tr style="display: none;">
			 <c:if test="${LowerUserList != null}">
			 <c:if test="${caseStatus != 'APRVDBYHO'}">
			 <td width="20%" align="left"><fmt:message key="PPROC.REJECTTO"
				bundle="${pensionLabels}"></fmt:message></td>
			<td width="40%" align="left"><select name="cmbRejectTo"
				id="cmbRejectTo" style="width: 100%" onfocus="onFocus(this)"
				onblur="onBlur(this);" onchange="">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="LowerUser" items="${LowerUserList}" >
						<option value="${LowerUser[1]}"><c:out value="${LowerUser[0]}"></c:out> </option>
					</c:forEach>
			</select>
			</td>
			 <td>
			<hdiits:button name="btnReject" id="btnReject" classcss="bigbutton"
			type="button" captionid="PPROC.REJECT" bundle="${pensionLabels}"
			onclick="rejectPensionCase();" /> 
			</td>
			</c:if>
			</c:if>
		</tr>
		
		</c:when>
		 </c:choose>
	</table>
	<br>
	<table width="40%" align="center">
		<tr>
		<td width="40%" align="center">
			
				<c:if test="${curRole == '700001' && lagecyFlag != 89 }">
					<c:choose>
						<c:when test="${draftFlag == 82 && lObjTrnPnsnProcInwardPensionVO.pensionFlag != 'FP'}">
				
	<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('D');" />
	
	</c:when>
	
					
	
	<c:when test="${draftFlag == 65 && lObjTrnPnsnProcInwardPensionVO.pensionFlag != 'FP'}">
		
<hdiits:button name="btnFrwrd2AG" type="button" captionid="PPROC.FRWRDTOAG" bundle="${pensionLabels}" classcss="bigbutton" onclick="ForwardToAG();"  />
			
			</c:when>
		
		<c:when test="${draftFlag == 68 && (lObjTrnPnsnProcInwardPensionVO.pensionFlag != 'FP' ) }"  >
<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('D');" />

</c:when>


<c:when test="${lObjTrnPnsnProcInwardPensionVO.pensionFlag != 'FP' }"  > 
<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('D');" />
 
						
</c:when>

<c:when test="${lObjTrnPnsnProcInwardPensionVO.pensionFlag != 'FP'}"  > 
<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('D');" />
					
</c:when>
 

</c:choose>
<c:choose>
<c:when test="${flagPen == 'N' || lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P1'}">
<hdiits:button name="btnSaveForward"  type="button" captionid="PPROC.FORWARDPENSNASST"	bundle="${pensionLabels}" onclick="forwardPensionCase('F');" style="width:180px;"  />
</c:when>
<c:otherwise>
<hdiits:button name="btnSaveForward" classcss="bigbutton" type="button" captionid="PPROC.FORWARD"	bundle="${pensionLabels}" onclick="forwardPensionCase('F');"  />
</c:otherwise>

</c:choose>
					
		
		</c:if>	
			<c:if test="${curRole == '700018'}">
			  		<c:choose>
			  	<c:when test="${draftFlag == 82}">
						<!-- //addded by SHRADDHA for giving save button in Reject cases -->
						<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('P');" />
						<!-- //addded by SHRADDHA for giving save button in Reject cases -->
						</c:when>
			  	<c:otherwise>
			  	
			  	<hdiits:button name="btnSave"	type="button" captionid="PPROC.SAVEASDRAFT" bundle="${pensionLabels}" onclick="SaveData('P'); " />
			  	
			  	<c:if test="${draftFlag == 65}">
						<hdiits:button name="btnFrwrd2AG"	type="button" captionid="PPROC.FRWRDTOAG" bundle="${pensionLabels}" classcss="bigbutton" onclick="ForwardToAG();"  />
						</c:if>
						</c:otherwise>
						</c:choose>
						<hdiits:button name="btnSaveForward" classcss="bigbutton" type="button" captionid="PPROC.FORWARD"	bundle="${pensionLabels}" onclick="forwardPensionCase('F');"  />
						  <hdiits:button name="btnReject" id="btnReject" classcss="bigbutton"	type="button" captionid="PPROC.SNDTOAST" bundle="${pensionLabels}"
						onclick="rejectPensionCase();" />
			  	</c:if>	 
			  	 
			  	<c:if test="${curRole == '700002'}">
			  	 	<c:if test="${caseStatus != 'APRVDBYHO'}">
			  	    	<hdiits:button name="btnUpdate"	type="button" captionid="PPROC.UPDATE" bundle="${pensionLabels}" onclick="SaveData('${draftFlag}')" />
			  	    </c:if>
			  	    <hdiits:button name="btnApprove" type="button" captionid="PPROC.APPROVE" bundle="${pensionLabels}" onclick="approvePensionCase();" />
			  	   
			  	 
			  	    <hdiits:button name="btnReject" id="btnReject" classcss="bigbutton"	type="button" captionid="PPROC.REJECT" bundle="${pensionLabels}"
						onclick="rejectPensionCase();" />
			  	
			  	
			  	</c:if>
			  <c:if test="${((caseStatus == 'SENDTOAG' || caseStatus == 'AGQUERY' || caseStatus == 'MISBYDDO')&& draftFlag == 65) || lagecyFlag == 89 }">
			  	<c:choose>
			  		<c:when test="${lagecyFlag == 89}">
			  			<hdiits:button name="btnSaveAg"	type="button" style="width:130px" captionid="BTN.APPROVEDBYAG" bundle="${pensionLabels}" onclick="SaveData('L');" />
			  		</c:when>
			  		<c:otherwise>
			  			<hdiits:button name="btnSaveAg"	type="button" style="width:130px" captionid="BTN.APPROVEDBYAG" bundle="${pensionLabels}" onclick="approvePensionCaseForAg();" />
			  		</c:otherwise>
			  	</c:choose>
			  </c:if>
		<hdiits:button name="btnClose"	type="button" captionid="PPROC.CLOSE" bundle="${pensionLabels}" onclick="winCls();" style="width:80px;" />
			</td>
		</tr>
		

	</table>
<table align="center">

<tr align="center" >
<td>

		<c:if test="${flagPen=='N' || lObjTrnPnsnProcInwardPensionVO.pensionFlag == 'P1'}">
		 <div id="divErrorMsg"><font color="yellow" style="font-family: Arial; font-size: 11px;"> <b><strong><label id="errorLabel">
				</label></strong></b></font>
				</div>

		</c:if>
</td>
		</tr>
</table>
	</div>


	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
		
		</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

<input type = "hidden" id = "currRole" value="${curRole}"/>
<input type = "hidden" id = "statusFlag" value="${caseStatus}"/>
<input type = "hidden" id = "draftFlag" value="${draftFlag}"/>


<script>
var lStrShowReadOnly = "${showReadOnly}";
if(lStrShowReadOnly == "Y")
{
	objElems = document.PensionInwardForm.elements;
	for(var i=0;i<objElems.length;i++)
	{
		if(objElems[i].tagName == "INPUT")
		{
			if(objElems[i].type == "button" || objElems[i].type == "radio" || objElems[i].type == "checkbox")
			{
				objElems[i].disabled = true;
			}
			else
			{
				objElems[i].readOnly = true;
			}
		}
		else if(objElems[i].tagName == "SELECT" || objElems[i].tagName == "TEXTAREA" )
		{
			
			objElems[i].disabled = true;
			if(objElems[i].id == "cmbRejectTo" || objElems[i].id == "cmbForwardTo")
			{
				objElems[i].disabled = false;
			}
			
		}

		if(objElems[i].tagName == "hdiits:button"){
			objElems[i].disabled = true;
		}
	}
	var lArrImgElems = document.getElementsByTagName("img");
	 for(var k=0;k<lArrImgElems.length;k++)
	 {
		 if(lArrImgElems[k].id != "imgPnsnrPhoto" && lArrImgElems[k].id != "imgPnsnrSignature"  )
		 {
			lArrImgElems[k].style.display = "none";
		 }
	 }	
	 document.getElementById("btnClose").disabled = false;

//added by ankita
	 document.getElementById("btnFrwrd2AG").disabled = false;

	 
	 if(${(caseStatus == 'SENDTOAG' || caseStatus == 'AGQUERY' || caseStatus == 'MISBYDDO')&& draftFlag == 'A'}){		 
		 document.getElementById("txtPpoNo").readOnly = false;
		 document.getElementById("txtPpoDate").readOnly = false;
		 document.getElementById("txtCpoNo").readOnly = false;
		 document.getElementById("txtCpoDate").readOnly = false;
		 document.getElementById("txtGpoNo").readOnly = false;
		 document.getElementById("txtGpoDate").readOnly = false;
		 document.getElementById("btnSaveAg").disabled = false;
		 document.getElementById("imgPpoDate").style.display = "";
		 document.getElementById("imgCpoDate").style.display = "";
		 document.getElementById("imgGpoDate").style.display = "";
	 }	
	 
	 document.getElementById("divPhoto").disabled=true;
	 document.getElementById('descPhoto').value="";
	 document.getElementById("divSign").disabled=true;
	 document.getElementById('descSign').value="";
	 document.getElementById("myTablescan").disabled=true;
	 document.getElementById("statusscan").disabled=true;
	 document.getElementById("txtComments").disabled=false;
}			
</script>
<script>
var lStrPensionType = "${lObjTrnPnsnProcInwardPensionVO.pensionType}";
var lArrOpts = document.getElementById("cmbClassOfPnsn").options;
var pnsnTypeFound = "N";	//Flag to set select as selected if no match found.
for(var cnt = 0; cnt < lArrOpts.length ; cnt++)
{
	document.getElementById("cmbClassOfPnsn").value = lStrPensionType;
	if(document.getElementById("cmbClassOfPnsn").options[cnt].value == lStrPensionType)
	{
		document.getElementById("cmbClassOfPnsn").options[cnt].selected = "selected";
		pnsnTypeFound = "Y";
	}
}
if(pnsnTypeFound == "N")
{
	document.getElementById("cmbClassOfPnsn").options[0].selected = "selected";
}
</script>


<script>

function ForwardToAG(){
	//alert("sevaarthId"+document.getElementById("txtSevaarthId").value);
	uri = "ifms.htm?actionFlag=forwardToAG&sevaarthId="+document.getElementById("txtSevaarthId").value;

	myAjax = new Ajax.Request(uri, {
	method :'post',
	asynchronous :false,
	parameters :uri,
	onSuccess : function(myAjax) {
	forwardToAGAjax(myAjax);
	},
	onFailure : function() {
	alert('Something went wrong...');
	}
	});

}

function forwardToAGAjax(myAjax){
var XMLDoc = myAjax.responseXML.documentElement;
if (XMLDoc != null) {
var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
var lSaveStatus = XmlHiddenValues[0].childNodes[0].nodeValue;
//alert("lSaveStatus:::"+lSaveStatus);
if (lSaveStatus == "Success") {
	alert("Forwarded To AG successfully.");
	//winCls();
	window.location.href= "ifms.htm?actionFlag=loadSearchPensionCase&DraftFlag=A";
	self.close();
	window.opener.location.reload();

	//self.location.href = uri;
	//return false;
	//winCls();
	//return false;
}

else {

	alert("Someting went wrong");
}
}
return true;
}

// Added by shraddha on 22-3-2016 for deputation module changes
function getPenOffDtls(){

	
	var penDdoCode= document.getElementById("txtPenDDOCode").value;

 	//document.getElementById("dispDDO").value=penDdoCode;

	
	var a= "Note: This form will be forwarded to Pension Asst ";
	//if(document.getElementById("txtPenDDOCode").value!=''){
	var c= a + penDdoCode;
	//alert(c);

		document.getElementById('divErrorMsg').innerHTML=c;	
		document.getElementById('divErrorMsg').style.fontFamily='arial';
		document.getElementById('divErrorMsg').style.fontSize='15px';
		document.getElementById('divErrorMsg').style.fontWeight='900';
		document.getElementById('divErrorMsg').style.color='red';
		if(document.getElementById("txtPenDDOCode").value != ''){
		document.getElementById('divErrorMsg').style.display='inline';
		}
		else{
			
			document.getElementById('divErrorMsg').style.display='none';}
		document.getElementById('divErrorMsg').style.visibility='visible';

	//}
	if(penDdoCode!=''){
  var url = uri = "ifms.htm?actionFlag=getPenOffDtls&penDdoCode="+penDdoCode;
  myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getPenOffDtlsAjax(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	}
}

function getPenOffDtlsAjax(myAjax){

	var XMLDoc = myAjax.responseXML.documentElement;
//	alert("XMLDoc:::::"+XMLDoc);
	if(XMLDoc != null)
	{				
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('OFFNAME');
		var offName = XmlHiddenValues1[0].childNodes[0].nodeValue;
		var XmlHiddenValues2 = XMLDoc.getElementsByTagName('PENDESIG');
		var penDesig = XmlHiddenValues2[0].childNodes[0].nodeValue;
		//alert("name::"+name);
		document.getElementById("txtPenDdoOfficeName").value=offName;
		document.getElementById("txtPenDdoDesigName").value=penDesig;
		
	}
return offName;
}

function checkDdoCode(){
	var penDdoCode= document.getElementById("txtPenDDOCode").value;
	var ddoCode=document.getElementById("txtDDOCode").value;
	

	// alert("penDdoCode***"+penDdoCode);
	if(penDdoCode==ddoCode){
	alert("Pension DDO Code cannot be same as current DDO Code.Kindly re-generate the pension case and click on 'OK' button on submission.");
	document.getElementById("txtPenDDOCode").value='';
	return false;
	}
	else{
		 var url = uri = "ifms.htm?actionFlag=checkDdoCode&penDdoCode="+penDdoCode;
	  myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {checkDdoCodeAjax(myAjax,penDdoCode);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});

	}

}

function checkDdoCodeAjax(myAjax,penDdoCode)
{ 
	
	var XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].nodeValue;
		if(lSaveStatus == "Fail")
		{
			alert("Please enter valid DDO Code");
			document.getElementById("txtPenDDOCode").value='';
			document.getElementById("txtPenDdoOfficeName").value='';
			document.getElementById("txtPenDdoDesigName").value='';
			getPenOffDtls();
			return false;
		}
		else if(lSaveStatus == "Absent")
		{
			alert("Pension Assistant has not been mapped for entered Pension DDO Code.Kindly contact Pension Case DDO for mapping Pension Assistant.");
			document.getElementById("txtPenDDOCode").value='';
			return false;
		}
		
		else{ getPenOffDtls();}
}

	
}

//Added by shraddha on 22-3-2016 for deputation module changes
function getGratOffDtls(){

	//alert("hiii");
	var gratDdoCode= document.getElementById("txtGratDDOCode").value;
	//alert("gratDdoCode***"+gratDdoCode);
	if(document.getElementById("txtGratDDOCode").value == "" || document.getElementById("txtGratDDOCode").value== null){
	//alert("Please enter Gratuity DDO Code");
	return false;
	}
	
	//alert (gratDdoCode);
  var url = uri = "ifms.htm?actionFlag=getGratOffDtls&gratDdoCode="+gratDdoCode;
 // alert(url);
  myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getGratOffDtlsAjax(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});

}

function getGratOffDtlsAjax(myAjax){

	var XMLDoc = myAjax.responseXML.documentElement;
//	alert("XMLDoc:::::"+XMLDoc);
	if(XMLDoc != null)
	{				
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('OFFNAME');
		var offName = XmlHiddenValues1[0].childNodes[0].nodeValue;
		var XmlHiddenValues2 = XMLDoc.getElementsByTagName('PENDESIG');
		var penDesig = XmlHiddenValues2[0].childNodes[0].nodeValue;
		//alert("offName::"+offName);
	if(offName=='Fail'){

		alert("Please enter valid Gratuity DDO Code");
		document.getElementById("txtGratDDOCode").value='';
		document.getElementById("txtGratDdoOfficeName").value='';
		return false;
		
	}
	else{
		document.getElementById("txtGratDdoOfficeName").value=offName;
		document.getElementById("txtGratDdoDesigName").value=penDesig;
	}
	}
return offName;
}




</script>


<%}catch(Exception e){
e.printStackTrace();
}%>


<script>
checkValue();
//getNextDate();
</script>