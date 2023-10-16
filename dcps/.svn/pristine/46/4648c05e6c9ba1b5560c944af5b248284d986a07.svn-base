<%@ page language="java" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request" />

<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts" var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"	src="script/pensionpay/RevisedCaseProcess.js"></script>


<script type="text/javascript">
PPONO='<fmt:message key="PPMT.PPONO" bundle="${pensionAlerts}"></fmt:message>';
PNSNCLASS='<fmt:message key="PPMT.PNSNCLASS" bundle="${pensionAlerts}"></fmt:message>';
PNSNCASETYPE='<fmt:message key="PPMT.PNSNCASETYPE" bundle="${pensionAlerts}"></fmt:message>';
PNSNCASESTATUS='<fmt:message key="PPMT.PNSNCASESTATUS" bundle="${pensionAlerts}"></fmt:message>';
CALCTYPE='<fmt:message key="PPMT.CALCTYPE" bundle="${pensionAlerts}"></fmt:message>';
AGOUTWARDNO='<fmt:message key="PPMT.AGOUTWARDNO" bundle="${pensionAlerts}"></fmt:message>';
AGOUTWARDDT='<fmt:message key="PPMT.AGOUTWARDDT" bundle="${pensionAlerts}"></fmt:message>';
NAME='<fmt:message key="PPMT.NAME" bundle="${pensionAlerts}"></fmt:message>';
FULLNAMEPNSNR='<fmt:message key="PPMT.FULLNAMEPNSNR" bundle="${pensionAlerts}"></fmt:message>';
FULLNAMEFAMILY='<fmt:message key="PPMT.FULLNAMEFAMILY" bundle="${pensionAlerts}"></fmt:message>';
NAMEINMARATHI='<fmt:message key="PPMT.NAMEINMARATHI" bundle="${pensionAlerts}"></fmt:message>';
FATHERNAME='<fmt:message key="PPMT.FATHERNAME" bundle="${pensionAlerts}"></fmt:message>';
GENDER='<fmt:message key="PPMT.GENDER" bundle="${pensionAlerts}"></fmt:message>';
DATEOFBIRTH='<fmt:message key="PPMT.DATEOFBIRTH" bundle="${pensionAlerts}"></fmt:message>';
DATEOFJOINING='<fmt:message key="PPMT.DATEOFJOINING" bundle="${pensionAlerts}"></fmt:message>';
DATEOFRET='<fmt:message key="PPMT.DATEOFRET" bundle="${pensionAlerts}"></fmt:message>';
COMDATE='<fmt:message key="PPMT.COMDATE" bundle="${pensionAlerts}"></fmt:message>';
ALIVE='<fmt:message key="PPMT.ALIVE" bundle="${pensionAlerts}"></fmt:message>';
BUILDING='<fmt:message key="PPMT.BUILDING" bundle="${pensionAlerts}"></fmt:message>';
STATE='<fmt:message key="PPMT.STATE" bundle="${pensionAlerts}"></fmt:message>';
DISTRICT='<fmt:message key="PPMT.DISTRICT" bundle="${pensionAlerts}"></fmt:message>';
PINCODE='<fmt:message key="PPMT.PINCODE" bundle="${pensionAlerts}"></fmt:message>';
PANNO='<fmt:message key="PPMT.PANNO" bundle="${pensionAlerts}"></fmt:message>';
DESIGNATION='<fmt:message key="PPMT.DESIGNATION" bundle="${pensionAlerts}"></fmt:message>';
PNSNRTYPE='<fmt:message key="PPMT.PNSNRTYPE" bundle="${pensionAlerts}"></fmt:message>';
HEADCODE='<fmt:message key="PPMT.HEADCODE" bundle="${pensionAlerts}"></fmt:message>';
STATECODE='<fmt:message key="PPMT.STATECODE" bundle="${pensionAlerts}"></fmt:message>';
PAYMENTSCHEME='<fmt:message key="PPMT.PAYMENTSCHEME" bundle="${pensionAlerts}"></fmt:message>';
BANKNAME='<fmt:message key="PPMT.BANKNAME" bundle="${pensionAlerts}"></fmt:message>';
BRANCHNAME='<fmt:message key="PPMT.BRANCHNAME" bundle="${pensionAlerts}"></fmt:message>';
ACCOUNTNO='<fmt:message key="PPMT.ACCOUNTNO" bundle="${pensionAlerts}"></fmt:message>';
ROPTYPE='<fmt:message key="PPMT.ROPTYPE" bundle="${pensionAlerts}"></fmt:message>';
BASICPNSNAMT='<fmt:message key="PPMT.BASICPNSNAMT" bundle="${pensionAlerts}"></fmt:message>';
DCRGPAID='<fmt:message key="PPMT.DCRGPAID" bundle="${pensionAlerts}"></fmt:message>';
DCRGDATE='<fmt:message key="PPMT.DCRGDATE" bundle="${pensionAlerts}"></fmt:message>';
PROVPNSN='<fmt:message key="PPMT.PROVPNSN" bundle="${pensionAlerts}"></fmt:message>';
PROVPNSNAMT='<fmt:message key="PPMT.PROVPNSNAMT" bundle="${pensionAlerts}"></fmt:message>';
PROVPNSNFROMDT='<fmt:message key="PPMT.PROVPNSNFROMDT" bundle="${pensionAlerts}"></fmt:message>';
PROVPNSNTODT='<fmt:message key="PPMT.PROVPNSNTODT" bundle="${pensionAlerts}"></fmt:message>';
TOTALAMTPAID='<fmt:message key="PPMT.TOTALAMTPAID" bundle="${pensionAlerts}"></fmt:message>';
PROVGRATUITY='<fmt:message key="PPMT.PROVGRATUITY" bundle="${pensionAlerts}"></fmt:message>';
PROVGRATUITYAMT='<fmt:message key="PPMT.PROVGRATUITYAMT" bundle="${pensionAlerts}"></fmt:message>';
ACTUALAMTPAID='<fmt:message key="PPMT.ACTUALAMTPAID" bundle="${pensionAlerts}"></fmt:message>';
PAYMENTDT='<fmt:message key="PPMT.PAYMENTDT" bundle="${pensionAlerts}"></fmt:message>';
ADVANCETYPE='<fmt:message key="PPMT.ADVANCETYPE" bundle="${pensionAlerts}"></fmt:message>';
AMOUNT='<fmt:message key="PPMT.AMOUNT" bundle="${pensionAlerts}"></fmt:message>';
NOOFINSTL='<fmt:message key="PPMT.NOOFINSTL" bundle="${pensionAlerts}"></fmt:message>';
STARTMONTH='<fmt:message key="PPMT.STARTMONTH" bundle="${pensionAlerts}"></fmt:message>';
STARTYEAR='<fmt:message key="PPMT.STARTYEAR" bundle="${pensionAlerts}"></fmt:message>';
ENDMONTH='<fmt:message key="PPMT.ENDMONTH" bundle="${pensionAlerts}"></fmt:message>';
ENDYEAR='<fmt:message key="PPMT.ENDYEAR" bundle="${pensionAlerts}"></fmt:message>';
NATURE='<fmt:message key="PPMT.NATURE" bundle="${pensionAlerts}"></fmt:message>';
SCHEMECODE='<fmt:message key="PPMT.SCHEMECODE" bundle="${pensionAlerts}"></fmt:message>';
JOININGBIRTHDT='<fmt:message key="PPMT.JOININGBIRTHDT" bundle="${pensionAlerts}"></fmt:message>';
RETBIRTHDT='<fmt:message key="PPMT.RETBIRTHDT" bundle="${pensionAlerts}"></fmt:message>';
COMRETDT='<fmt:message key="PPMT.COMRETDT" bundle="${pensionAlerts}"></fmt:message>';
EXPBIRTH='<fmt:message key="PPMT.EXPBIRTH" bundle="${pensionAlerts}"></fmt:message>';
RETJOINDT='<fmt:message key="PPMT.RETJOINDT" bundle="${pensionAlerts}"></fmt:message>';
EXPJOINDT='<fmt:message key="PPMT.EXPJOINDT" bundle="${pensionAlerts}"></fmt:message>';
CMNDATEGTRETDATE='<fmt:message key="PPMT.CMNDATEGTRETDATE" bundle="${pensionAlerts}"></fmt:message>';
EXPIRYDATELTRETDATE='<fmt:message key="PPMT.EXPIRYDATELTRETDATE" bundle="${pensionAlerts}"></fmt:message>';
FP1DTGRTCOMDT='<fmt:message key="PPMT.FP1DTGRTCOMDT" bundle="${pensionAlerts}"></fmt:message>';
FP2DTGRTFP1DT='<fmt:message key="PPMT.FP2DTGRTFP1DT" bundle="${pensionAlerts}"></fmt:message>';
PNSNRFAMILYDTLS='<fmt:message key="PPMT.PNSNRFAMILYDTLS" bundle="${pensionAlerts}"></fmt:message>';
PNSNRNOMDTLS='<fmt:message key="PPMT.PNSNRNOMDTLS" bundle="${pensionAlerts}"></fmt:message>';
RELATIONSHIP='<fmt:message key="PPMT.RELATIONSHIP" bundle="${pensionAlerts}"></fmt:message>';
PERCENTAGE='<fmt:message key="PPMT.PERCENTAGE" bundle="${pensionAlerts}"></fmt:message>';
GUARDIANNAME='<fmt:message key="PPMT.GUARDIANNAME" bundle="${pensionAlerts}"></fmt:message>';
PHYSICALHAND='<fmt:message key="PPMT.PHYSICALHAND" bundle="${pensionAlerts}"></fmt:message>';
LEDGERNO='<fmt:message key="PPMT.LEDGERNO" bundle="${pensionAlerts}"></fmt:message>';
PAGENO='<fmt:message key="PPMT.PAGENO" bundle="${pensionAlerts}"></fmt:message>';
FP1DATE='<fmt:message key="PPMT.FP1DATE" bundle="${pensionAlerts}"></fmt:message>';
FP2DATE='<fmt:message key="PPMT.FP2DATE" bundle="${pensionAlerts}"></fmt:message>';
FP1AMOUNT='<fmt:message key="PPMT.FP1AMOUNT" bundle="${pensionAlerts}"></fmt:message>';
FP2AMOUNT='<fmt:message key="PPMT.FP2AMOUNT" bundle="${pensionAlerts}"></fmt:message>';
PPOENDDTGRTCOMDT='<fmt:message key="PPMT.PPOENDDTGRTCOMDT" bundle="${pensionAlerts}"></fmt:message>';
COMPAIDDTCOMDT='<fmt:message key="PPMT.COMPAIDDTCOMDT" bundle="${pensionAlerts}"></fmt:message>';
DCRGPAIDDTCOMDT='<fmt:message key="PPMT.DCRGPAIDDTCOMDT" bundle="${pensionAlerts}"></fmt:message>';
PROVPNSNDTS='<fmt:message key="PPMT.PROVPNSNDTS" bundle="${pensionAlerts}"></fmt:message>';
COMDTRESTDT='<fmt:message key="PPMT.COMDTRESTDT" bundle="${pensionAlerts}"></fmt:message>';
CASERECEIVEFROM='<fmt:message key="PPMT.CASERECEIVEFROM" bundle="${pensionAlerts}"></fmt:message>';
RECEIVEDOFFICE='<fmt:message key="PPMT.RECEIVEDOFFICE" bundle="${pensionAlerts}"></fmt:message>';
RECEIVEDOTHERSRC='<fmt:message key="PPMT.RECEIVEDOTHERSRC" bundle="${pensionAlerts}"></fmt:message>';
COMDTGRTRECDT='<fmt:message key="PPMT.COMDTGRTRECDT" bundle="${pensionAlerts}"></fmt:message>';
VOUCHERMONTH='<fmt:message key="PPMT.VOUCHERMONTH" bundle="${pensionAlerts}"></fmt:message>';
VOUCHERYEAR='<fmt:message key="PPMT.VOUCHERYEAR" bundle="${pensionAlerts}"></fmt:message>';
VOUCHERNO='<fmt:message key="PPMT.VOUCHERNO" bundle="${pensionAlerts}"></fmt:message>';
VOUCHERDATE='<fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionAlerts}"></fmt:message>';
PNSNAMOUNT='<fmt:message key="PPMT.PNSNAMOUNT" bundle="${pensionAlerts}"></fmt:message>';
AGOUTDTCURDT='<fmt:message key="PPMT.AGOUTDTCURDT" bundle="${pensionAlerts}"></fmt:message>';
FMDOBCURDT='<fmt:message key="PPMT.FMDOBCURDT" bundle="${pensionAlerts}"></fmt:message>'; 
COMTNFROMDATE='<fmt:message key="PPMT.COMTNFROMDATE" bundle="${pensionAlerts}"></fmt:message>';
COMTNTODATE='<fmt:message key="PPMT.COMTNTODATE" bundle="${pensionAlerts}"></fmt:message>'; 
COMTNFROMTODATE='<fmt:message key="PPMT.COMTNFROMTODATE" bundle="${pensionAlerts}"></fmt:message>';
REEMPLTFROMDATE='<fmt:message key="PPMT.REEMPLTFROMDATE" bundle="${pensionAlerts}"></fmt:message>';
REEMPLTTODATE='<fmt:message key="PPMT.REEMPLTTODATE" bundle="${pensionAlerts}"></fmt:message>';
REEMPTODTCURDT='<fmt:message key="PPMT.REEMPTODTCURDT" bundle="${pensionAlerts}"></fmt:message>';
DAINPNSNSAL='<fmt:message key="PPMT.DAINPNSNSAL" bundle="${pensionAlerts}"></fmt:message>';
REEMPFROMTODATE='<fmt:message key="PPMT.REEMPFROMTODATE" bundle="${pensionAlerts}"></fmt:message>';
REEMPFROMCOMNTDT='<fmt:message key="PPMT.REEMPFROMCOMNTDT" bundle="${pensionAlerts}"></fmt:message>';
SANCAUTHNAME='<fmt:message key="PPMT.SANCAUTHNAME" bundle="${pensionAlerts}"></fmt:message>';
SANCAUTHNO='<fmt:message key="PPMT.SANCAUTHNO" bundle="${pensionAlerts}"></fmt:message>';
SANCTIONEDDATE='<fmt:message key="PPMT.SANCTIONEDDATE" bundle="${pensionAlerts}"></fmt:message>';
APPLNFROMDATE='<fmt:message key="PPMT.APPLNFROMDATE" bundle="${pensionAlerts}"></fmt:message>';
APPLNTODATE='<fmt:message key="PPMT.APPLNTODATE" bundle="${pensionAlerts}"></fmt:message>';
BILLTYPE='<fmt:message key="PPMT.BILLTYPE" bundle="${pensionAlerts}"></fmt:message>';
CHARGEDVOTED='<fmt:message key="PPMT.CHARGEDVOTED" bundle="${pensionAlerts}"></fmt:message>';
FROMDATE='<fmt:message key="PPMT.PNSHMNTFROMDATE" bundle="${pensionAlerts}"></fmt:message>';
TODATE=	'<fmt:message key="PPMT.PNSHMNTTODATE" bundle="${pensionAlerts}"></fmt:message>';
RETDTLTCURDT='<fmt:message key="PPMT.RETDTLTCURDT" bundle="${pensionAlerts}"></fmt:message>';
GPONO='<fmt:message key="PPMT.GPONO" bundle="${pensionAlerts}"></fmt:message>';
GPODATE='<fmt:message key="PPMT.GPODATE" bundle="${pensionAlerts}"></fmt:message>';
TOTALGRTYAMT='<fmt:message key="PPMT.TOTALGRTYAMT" bundle="${pensionAlerts}"></fmt:message>'; 
WITHHELDAMT='<fmt:message key="PPMT.WITHHELDAMT" bundle="${pensionAlerts}"></fmt:message>';
COMTNORDERNO='<fmt:message key="PPMT.COMTNORDERNO" bundle="${pensionAlerts}"></fmt:message>';
COMTNORDERDT='<fmt:message key="PPMT.COMTNORDERDT" bundle="${pensionAlerts}"></fmt:message>';
COMTNAMT='<fmt:message key="PPMT.COMTNAMT" bundle="${pensionAlerts}"></fmt:message>';
COMTNPAYBLEAMT='<fmt:message key="PPMT.COMTNPAYBLEAMT" bundle="${pensionAlerts}"></fmt:message>';
MAJORHEAD='<fmt:message key="PPMT.MAJORHEAD" bundle="${pensionAlerts}"></fmt:message>';

AGMUMBAI='<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"></fmt:message>';
OTHRSTATE='<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"></fmt:message>';
OTHRTRSURY='<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"></fmt:message>';
ANYOTHRSRC='<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"></fmt:message>';
FAMILYPNSN='<fmt:message key="PPMT.FAMILYPNSN" bundle="${pensionConstants}"></fmt:message>';
SUPERANNU='<fmt:message key="PPMT.SUPERANNU" bundle="${pensionLabels}"></fmt:message>';
ORDERNO='<fmt:message key="PPMT.ORDERNO" bundle="${pensionAlerts}"></fmt:message>';
ORDERDATE='<fmt:message key="PPMT.ORDERDATE" bundle="${pensionAlerts}"></fmt:message>';
REMARKS='<fmt:message key="PPMT.REMARKS" bundle="${pensionAlerts}"></fmt:message>';

VOLUNTARY64='<fmt:message key="PPMT.VOLUNTARY64" bundle="${pensionConstants}"/>';
VOLUNTARY65='<fmt:message key="PPMT.VOLUNTARY65" bundle="${pensionConstants}"/>';
BANKCODEFORMONEYORDER='<fmt:message key="BANKCODE.PMNTMONEYORDER" bundle="${pensionConstants}"></fmt:message>';
MONEYORDER='<fmt:message key="PAYSCHEME.MONEYORDER" bundle="${pensionConstants}"></fmt:message>';
LISTSTATE='';
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}"></c:set>
<c:set var="mstPensionerHdrVO" value="${resValue.MstPensionerHdrVO}"></c:set>
<c:set var="mstPensionerDtlsVO" value="${resValue.MstPensionerDtlsVO}"></c:set>
<c:set var="trnProvPensionDtlsVO" value="${resValue.TrnProvisionalPensionDtlsVO}"></c:set>
<c:set var="currentDate" value="${resValue.CurrDate}"/>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="showReadOnly" value="${resValue.lStrShowReadOnly}"/>  
<c:set var="showCaseFor" value="${resValue.lStrShowCaseFor}"/> 
<c:set var="showApprove" value="${resValue.lStrShowApprove}"/>
<c:set var="showHistoryBtn" value="${resValue.lStrShowHistoryBtn}"/> 

<input type="hidden" name="hdnCurrDate" id="hdnCurrDate" value="${resValue.CurrDate}"/>
<input type="hidden" name="hdnPnsnRqstId" id="hdnPnsnRqstId" value="${trnPensionRqstHdrVO.pensionRequestId}"/>
<input type="hidden" name="hdnCallDate" id="hdnCallDate" value="${resValue.lStrCallDate}" />
<input type="hidden" name="hdnCallSlotNo" id="hdnCallSlotNo" value="${resValue.lStrCallSlotNo}" />
<input type="hidden" name="hdnCaseStatus" id="hdnCaseStatus" value="${trnPensionRqstHdrVO.caseStatus}" />
<input type="hidden" name="hdnCurrRole" id="hdnCurrRole" value="${curRole}" />

<hdiits:form name="InwardPensionerTabForm" id="InwardPensionerTabForm"
	method="POST" action="" encType="multipart/form-data" validate="true">

<table width="100%">	
	
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.PPONO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			<input type="hidden" name="hdnPensionerCode" id="hdnPensionerCode" value="${mstPensionerHdrVO.pensionerCode}"/>
			<input type="hidden" name="hdnPpoInwardDate" id="hdnPpoInwardDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.ppoInwardDate}" />" />
			<input type="text"
			id="txtPpoNo" name="txtPpoNo" value="${trnPensionRqstHdrVO.ppoNo}" onfocus="onFocus(this)" onblur="onBlur(this);validatePPONo();" tabindex="1" maxlength="50"/>
			<input type="hidden" name="hdnPpoNo" id="hdnPpoNo"  value="${trnPensionRqstHdrVO.ppoNo}"/>
			<label id="mandtryFinal" class="mandatoryindicator">*</label>
			<input type="hidden" name="hdnShowCaseFor" id="hdnShowCaseFor" value="${showCaseFor}"/>		
			</td>
				
								  
		 <td width="10%" align="left"><fmt:message key="PPMT.PNSNCLASS" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			<select name="cmbPensionClass"	id="cmbPensionClass"  style="width: 77%;" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:choose>
			<c:when test="${trnPensionRqstHdrVO.typeFlag == 'C'}">
			        <option  value="R"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
					<option selected="selected" value="C"><fmt:message key="PPMT.REVISION" bundle="${pensionLabels}"/></option>
			</c:when>
			<c:otherwise>
					<option  value="R" selected="selected"><fmt:message key="PPMT.REGULAR" bundle="${pensionLabels}"/></option>
					<option value="C"><fmt:message key="PPMT.REVISION" bundle="${pensionLabels}"/></option>
			</c:otherwise>
			</c:choose>

		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label></td>		    
			
		    <td width="10%" align="left"><fmt:message key="PPMT.PENSIONTYPE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left">
			<select name="cmbPensionType" id="cmbPensionType"   style="width: 77%"  tabindex="2" onchange="onChangePensionType();">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<option value="<fmt:message key="PPMT.ABSORPTION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.COMPASSIONATE" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.COMPENSATION" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.COMPULSORY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.EXTRAORDINARY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.FAMILYPNSN" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.INVALID" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.RETIRING105" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.RETIRING104" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.SUPERANNU" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.VOLUNTARY64" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.VOLUNTARY65" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.INJURY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.GALLANTRY" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/></option>
				<option value="<fmt:message key="PPMT.POLITICAL" bundle="${pensionConstants}"/>" title="<fmt:message key="PNSNTYPE.POLITICAL" bundle="${pensionLabels}"/>"><fmt:message key="PNSNTYPE.POLITICAL" bundle="${pensionLabels}"/></option>				
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
	</tr>
	<c:if test="${trnPensionRqstHdrVO.pensionType == 'Superannuation Pension (Rule 63)'}">
	<script type="text/javascript">
	  document.getElementById("cmbPensionType").options[1].selected=true;
    </script>
    </c:if>
	<tr>
			<td width="10%" align="left"><fmt:message key="PPMT.PENSIONSTATUS" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			<select name="cmbPensionStatus" id="cmbPensionStatus"   style="width: 77%"  tabindex="3">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:choose>
				<c:when test="${trnPensionRqstHdrVO.status == 'Continue'}">
				 <option value="<fmt:message key="STATUS.CONTINUE" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.STATUSCONTINUE" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.WITHHELD" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSHELD" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.ENDOFPNSN" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSENDOFPNSN" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${trnPensionRqstHdrVO.status == 'Closed'}">
				 <option value="<fmt:message key="STATUS.CONTINUE" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.STATUSCONTINUE" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.WITHHELD" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSHELD" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.ENDOFPNSN" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.STATUSENDOFPNSN" bundle="${pensionLabels}"/></option>
    			</c:when>
				<c:when test="${trnPensionRqstHdrVO.status == 'Withheld'}">
				 <option value="<fmt:message key="STATUS.CONTINUE" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.STATUSCONTINUE" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.WITHHELD" bundle="${pensionConstants}"/>" selected="selected"><fmt:message key="PPMT.STATUSHELD" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.ENDOFPNSN" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSENDOFPNSN" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:otherwise>
				 <option value="<fmt:message key="STATUS.CONTINUE" bundle="${pensionConstants}"/>" ><fmt:message key="PPMT.STATUSCONTINUE" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.WITHHELD" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSHELD" bundle="${pensionLabels}"/></option>
				 <option value="<fmt:message key="STATUS.ENDOFPNSN" bundle="${pensionConstants}"/>"><fmt:message key="PPMT.STATUSENDOFPNSN" bundle="${pensionLabels}"/></option>
			     
				</c:otherwise>
			</c:choose>
		    		   
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		    		   
		    <c:if test="${mstPensionerHdrVO.pensionerCode == null}">
		    <script>
		    	document.getElementById("cmbPensionStatus").disabled=true;
		    	document.getElementById("cmbPensionStatus").value='Continue';
		    </script>
		    </c:if>
		    <td width="10%" align="left"><fmt:message key="PPMT.CALCTYPE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			<select name="cmbCalculationType" id="cmbCalculationType"   style="width: 77%"  tabindex="4" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:choose>
			<c:when test="${trnPensionRqstHdrVO.calcType == 'A'}">
					<option value="A" selected="selected"><fmt:message key="PPMT.AUTO" bundle="${pensionLabels}"/></option>
					<option value="M"><fmt:message key="PPMT.MANUAL" bundle="${pensionLabels}"/></option>
			</c:when>
			<c:when test="${trnPensionRqstHdrVO.calcType == 'M'}">
					<option value="M" selected="selected"><fmt:message key="PPMT.MANUAL" bundle="${pensionLabels}"/></option>
					<option value="A"><fmt:message key="PPMT.AUTO" bundle="${pensionLabels}"/></option>
			</c:when>
			<c:otherwise>
					<option value="A" selected="selected"><fmt:message key="PPMT.AUTO" bundle="${pensionLabels}"/></option>
					<option value="M"><fmt:message key="PPMT.MANUAL" bundle="${pensionLabels}"/></option>
			</c:otherwise>
			</c:choose>
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		    
		    <td width="10%" align="left"><fmt:message key="PPMT.INWARDNO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%">
				<input type="text" id="txtInwardNo" name="txtInwardNo" readonly="readonly" value="${mstPensionerDtlsVO.registrationNo}" size='25'/>
				
			
			</td>
			
	</tr>
	
	<tr>						
			<td width="10%" align="left"><fmt:message key="PPMT.TREASURYNAME" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			<input type="text" id="txtTreasuryName" name="txtTreasuryName" value="${resValue.TreasuryName}" size="30" readonly="readonly"/>
			</td>
		    
		    <td width="10%" align="left"><fmt:message key="PPMT.CASERECEIVEDFROM" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left"><select name="cmbCaseReceivedFrom"
			id="cmbCaseReceivedFrom" style="width: 77%"  tabindex="5" onchange="getCaseReceivedOffice();">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			
			<c:choose>
				<c:when test="${trnPensionRqstHdrVO.caseReceivedFrom == 'AG Mumbai'}">
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${trnPensionRqstHdrVO.caseReceivedFrom == 'AG Nagpur'}">
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${trnPensionRqstHdrVO.caseReceivedFrom == 'Other State'}">
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${trnPensionRqstHdrVO.caseReceivedFrom == 'Other Treasury'}">
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:when test="${trnPensionRqstHdrVO.caseReceivedFrom == 'Any Other Source'}">
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>" selected="selected"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:when>
				<c:otherwise>
					<option value="<fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGMUMBAI" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.AGNAGPUTR" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRSTATE" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.OTHRTRSURY" bundle="${pensionLabels}"/></option>
					<option value="<fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/>"><fmt:message key="PPMT.ANYOTHRSRC" bundle="${pensionLabels}"/></option>
				</c:otherwise>
			</c:choose>
						
		    </select><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		    
		
		    <td width="10%" align="left"><fmt:message key="PPMT.PERTAININGTO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td  align="left"><select name="cmbReceivedOffice"
			id="cmbReceivedOffice" style="width: 77%" tabindex="6" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			
		    </select>
		    <select name="cmbReceivedTrsyOffice"
			id="cmbReceivedTrsyOffice" style="width: 77%;display: none" tabindex="7"   >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="treasuryList" items="${resValue.lLstTreasury}">
					<c:choose>
					<c:when test="${treasuryList.desc == trnPensionRqstHdrVO.caseReceivedOffice}">
				         <option selected="selected" value="${treasuryList.desc}" title="${treasuryList.desc}">
				        	 <c:out value="${treasuryList.desc}"></c:out>
				         </option>
				    </c:when>
				    <c:otherwise>
				            <option value="${treasuryList.desc}" title="${treasuryList.desc}">
								<c:out value="${treasuryList.desc}"></c:out>									
							</option>
					 </c:otherwise>		
				    </c:choose>       
	            	</c:forEach>
			
		    </select>
		    <select name="cmbOtherState"
			id="cmbOtherState" style="width: 77%;display: none" tabindex="7">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="stateList" items="${resValue.lLstState}">
					<c:choose>
					<c:when test="${stateList.desc == 'Maharashtra'}"></c:when>
					<c:when test="${stateList.desc == trnPensionRqstHdrVO.caseReceivedOffice}">
				         <option selected="selected" value="${stateList.desc}"><c:out value="${stateList.desc}"></c:out></option>
				    </c:when>
					<c:otherwise>
				            <option value="${stateList.desc}">
								<c:out value="${stateList.desc}"></c:out>									
							</option>
				    </c:otherwise>
				      </c:choose>   
	            	</c:forEach>
	            				
		    </select>
		    
		   	 <input type="text" name="txtCaseReceivedOffice" id="txtCaseReceivedOffice" style="display: none" value="${trnPensionRqstHdrVO.caseReceivedOffice}" tabindex="7" onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="100"/>
		 
		    
		    <label id="mandtryFinal" class="mandatoryindicator">*</label>
		    
		    </td>
		    
	</tr>
	<script>
	getCaseReceivedOffice();
	</script>
	<tr>					
			<td width="10%" align="left"><fmt:message key="PPMT.AGOUTWARDNO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left"><input type="text"
			id="txtPpoAGOutwNO" name="txtPpoAGOutwNO" value='${trnPensionRqstHdrVO.ppoAGOutwardNo}' style="display: ;text-transform: uppercase;" onfocus="onFocus(this)" onblur="onBlur(this);" tabindex="8" maxlength="50"/><label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		    
		    <td width="10%" align="left"><fmt:message key="PPMT.AGOUTWARDDATE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left">
			 <input type="text" id="txtPpoAGOutwDate" name="txtPpoAGOutwDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.ppoAGOutwardDate}" />"
			  onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"  tabindex="9"/>
	          <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtPpoAGOutwDate",375,570)'style="cursor: pointer;" ${disabled}/>	
			
			<label id="mandtryFinal" class="mandatoryindicator">*</label>
			</td>
			 <td width="10%" align="left"><fmt:message key="PPMT.PPOIDNTFDOTHRRSN" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td>
			<c:choose>
			<c:when test="${mstPensionerDtlsVO.identificationFlag == 'Y'}">
				<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioPpoIdentifyY" name="radioPpoIdentify" value="Y"  disabled="disabled"  checked="checked"/>
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioPpoIdentifyN" name="radioPpoIdentify" value="N" disabled="disabled"/>
			</c:when>
		    <c:otherwise>
				<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioPpoIdentifyY" name="radioPpoIdentify" value="Y"  disabled="disabled" />
				<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
				<input type="radio" id="radioPpoIdentifyN" name="radioPpoIdentify" value="N" disabled="disabled" checked="checked"/>
			</c:otherwise>
			</c:choose>
				 <c:if test="${curRole == '365451' and showApprove == 'Y'}">
			    	 <script>
			      		 document.getElementById("radioPpoIdentifyY").disabled=false;
			       		 document.getElementById("radioPpoIdentifyN").disabled=false;
			     	 </script>
			     </c:if>
			</td>
			
	</tr>
	
	<tr>					
			<td width="10%" align="left"><fmt:message key="PPMT.DATE" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left"><input type="text"
			id="txtDate" name="txtIdentDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${mstPensionerDtlsVO.identificationDate}" />" readonly="readonly"/></td>
		    
		    <td width="10%" align="left"><fmt:message key="PPMT.TIME" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="10%" align="left"><input type="text" 
			id="txtIdentTime" name="txtIdentTime" value="${fn:substring(mstPensionerDtlsVO.identificationDate,11,16)}" readonly="readonly"/></td>
			
			<td width="10%" align="left"><fmt:message key="PPMT.VOLUMENO" 
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="15%" align="left"><input type="text"
			id="txtLedgerNo" name="txtLedgerNo" value="${trnPensionRqstHdrVO.ledgerNo}" size='5' maxlength = "5" onKeyPress="numberFormat(this)" tabindex="10" onfocus="onFocus(this)"  onblur="onBlur(this);" disabled="disabled"/>
		    
		    <fmt:message key="PPMT.PAGENO" 
			bundle="${pensionLabels}"></fmt:message>
			<input type="text" 
			id="txtPageNo" name="txtPageNo" value="${trnPensionRqstHdrVO.pageNo}" size='5' maxlength = "5" onKeyPress="numberFormat(this)"  tabindex="11" onfocus="onFocus(this)"  onblur="onBlur(this);" disabled="disabled"/></td>
	</tr>
	<tr>					
			<td width="10%" align="left">
			<fmt:message key="PPMT.LIBRARYNO" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="10%" align="left">
			<input type="text" id="txtLibraryNo" name="txtLibraryNo" value="${mstPensionerHdrVO.libraryNo}" readonly="readonly"/>
			</td>
		    
		    <td width="10%" align="left"></td>
			<td width="10%" align="left"></td>
			
			<td width="10%" align="left"></td>
			<td width="15%" align="left">
		    
			</td>
	</tr>
	
</table>

<div id="tabmenu">
     <ul id="maintab" class="shadetabs" >
	     	<li class="selected">
		    	<a href="#" rel="tcontent1" >
		  			<fmt:message key="PPMT.PENSIONERDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	       	<li>
		        <a href="#" rel="tcontent2"  tabindex="50">
					<fmt:message key="PPMT.PAYMENTDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	        
	        <li>
		        <a href="#" rel="tcontent3">
					<fmt:message key="PPMT.FAMILYDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	        
	         <li>
		        <a href="#" rel="tcontent4">
					<fmt:message key="PPMT.RECOVERYDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li>
	        
	         <li>
		        <a href="#" rel="tcontent5">
					<fmt:message key="PPMT.DCRGDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li> 
	         <li>
		        <a href="#" rel="tcontent6">
					<fmt:message key="PPMT.CVPDTLS" bundle="${pensionLabels}"></fmt:message>
		        </a>
	        </li> 
			
	             	       
     </ul>
</div>

<div class="tabcontentstyle">

	<!-- ------------------Pension Details--------------- -->
		<div id="tcontent1" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/PensionerTab.jsp" />
		</div>	

	<!-- ------------------ Payment Details --------------- -->
		<div id="tcontent2" class="tabcontent" align="left">
			<jsp:include page="/WEB-INF/jsp/pensionpay/PaymentTab.jsp" />
		</div>
	<!-- ------------------Family Details--------------- -->
		<div id="tcontent3" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/FamilyTab.jsp" />
		</div>
		
		<div id="tcontent4" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/RecoveryTab.jsp" />
		</div>	
		 
		<div id="tcontent5" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/DCRGTab.jsp" />
		</div> 
		<div id="tcontent6" class="tabcontent" align="left" >
			<jsp:include page="/WEB-INF/jsp/pensionpay/CommutationTab.jsp" />
		</div> 
		<br>
		
		<c:if test="${showCaseFor == '15' || showCaseFor == '20'}">
		
		<div align="center">
		<fieldset class="tabstyle" style="width:65%" ><legend> <b>
		<fmt:message key="PPMT.REMARKS" bundle="${pensionLabels}"></fmt:message></b> </legend>
		
		
		<input type="hidden" name="hidGridSize" id="hidGridSize" value="1" />
			<c:if test="${curRole == '365451' or curRole == '365450'}">
			<div align="left" style="padding-left: 50px">
				<hdiits:button	name="btnRemarksTableAddRow" type="button" captionid="PPMT.ADDROW" bundle="${pensionLabels}" onclick="addRowForRemarks();" />
				<hdiits:button	name="btnViewRemarks" type="button" captionid="PPMT.VIEWREMARKS" bundle="${pensionLabels}" onclick="showPensionCaseRemarks();" />
		    </div>
		    <br>
			<table id="tblForRemarks" align="center" width="90%" cellspacing="0" border="1">
			<tr class="datatableheader" style="width: 100px">
				<td width="20%" class="HLabel"><fmt:message key="PPMT.ORDERNO"
					bundle="${pensionLabels}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="20%" class="HLabel"><fmt:message key="PPMT.ORDERDATE"
					bundle="${pensionLabels}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="30%" class="HLabel"><fmt:message key="PPMT.REMARKS"
					bundle="${pensionLabels}"></fmt:message><label
					class="mandatoryindicator">*</label></td>
				<td width="10%" class="HLabel"><fmt:message key="PPMT.DELETE"
					bundle="${pensionLabels}"></fmt:message></td>
					
			</tr>
			
			</table>
			
			</c:if>
		
		</fieldset>
		</div>
		</c:if>	
		<br>
		<div align="center">
			<hdiits:button name="btnSave" id="btnSave" type="button" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="SaveData('N');" />&nbsp;&nbsp;&nbsp;
			<c:if test="${curRole == '365451'}">
			  		<hdiits:button name="btnEdit" id="btnEdit" type="button" captionid="PPMT.EDIT" bundle="${pensionLabels}" onclick="enableFormField();" style="display:none"/>&nbsp;&nbsp;&nbsp;
			 </c:if>	
			<c:if test="${curRole == '365451' and showApprove == 'Y'}">
			  	<hdiits:button name="btnApprove" type="button" captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick="SaveData('Y');" />&nbsp;&nbsp;&nbsp;
			</c:if>
			<c:if test="${showCaseFor != ''}">
			<hdiits:button name="btnEditFinFields" id="btnEditFinFields" type="button" captionid="PPMT.EDITFINCLFIELD" bundle="${pensionLabels}" classcss="bigbutton" onclick="enableFinancialFields();"/>&nbsp;&nbsp;&nbsp;
			<hdiits:button name="btnEditNonFinFields" id="btnEditNonFinFields" type="button" captionid="PPMT.EDITNONFINCLFIELD" bundle="${pensionLabels}" classcss="bigbutton"  onclick="enableNonFinancialFields();"/>&nbsp;&nbsp;&nbsp;
			</c:if>
		 	<hdiits:button id="btnClose" name="btnClose"  type="button" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();" />&nbsp;&nbsp;&nbsp;
		</div>
			
		
			 
		
</div>
</hdiits:form>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
</script>
<form name="loadForm" id="loadForm"
	method="POST"  >
	
</form>
<c:if test="${mstPensionerHdrVO.pensionerCode != null}" >
<script>
document.getElementById("txtPpoNo").disabled=true;
</script>
</c:if>	
<script>
var lStrPensionType = "${trnPensionRqstHdrVO.pensionType}";
var lArrOpts = document.getElementById("cmbPensionType").options;
var pnsnTypeFound = "N";	//Flag to set select as selected if no match found.
for(var cnt = 0; cnt < lArrOpts.length ; cnt++)
{
	document.getElementById("cmbPensionType").value = lStrPensionType;
	if(document.getElementById("cmbPensionType").options[cnt].value == lStrPensionType)
	{
		document.getElementById("cmbPensionType").options[cnt].selected = "selected";
		pnsnTypeFound = "Y";
	}
}
if(pnsnTypeFound == "N")
{
	document.getElementById("cmbPensionType").options[0].selected = "selected";
}
</script>

<script>
var lStrShowReadOnly = "${showReadOnly}";
if(lStrShowReadOnly == "Y")
{
	objElems = document.InwardPensionerTabForm.elements;
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
		}
	 }
	 var lArrImgElems = document.getElementsByTagName("img");
	 /*
	 for(var k=0;k<lArrImgElems.length;k++)
	 {
		 if(lArrImgElems[k].id != "imgPnsnrPhoto" && lArrImgElems[k].id != "imgPnsnrSign")
		 {
		 	lArrImgElems[k].style.display = "none";
		 }
	 }*/
	 document.getElementById("btnClose").disabled = false;
	 document.getElementById("btnEditFinFields").disabled = false;
	 document.getElementById("btnEditNonFinFields").disabled = false;
	 document.getElementById("btnSave").disabled = false;
	 document.getElementById("divPhoto").disabled=true;
	 document.getElementById('descPhoto').value="";
	 document.getElementById("divSign").disabled=true;
	 document.getElementById('descSign').value="";
	
}							



</script>

<c:if test="${curRole == '365451'}">
<script>
document.getElementById("btnEdit").disabled=false;
document.getElementById("btnSave").disabled=false;
</script>
</c:if>
<c:if test="${curRole == '365451' and showCaseFor == '5'}">
<script>
document.getElementById("btnApprove").disabled=false;
document.getElementById("radioPpoIdentifyY").disabled=false;
document.getElementById("radioPpoIdentifyN").disabled=false;
</script>
</c:if>

<script>
function enableFormField()
{
	objElems = document.InwardPensionerTabForm.elements;
	for(var i=0;i<objElems.length;i++)
	{
		if(objElems[i].tagName == "INPUT")
		{
			if(objElems[i].type == "button" || objElems[i].type == "radio" || objElems[i].type == "checkbox")
			{
				objElems[i].disabled = false;
			}
			else
			{
				objElems[i].readOnly = false;
			}
		}
		else if(objElems[i].tagName == "SELECT" || objElems[i].tagName == "TEXTAREA" )
		{
			objElems[i].disabled = false;
		}
	 }
	 var lArrImgElems = document.getElementsByTagName("img");
	 for(var k=0;k<lArrImgElems.length;k++)
	 {
		 if(lArrImgElems[k].id != "imgPnsnrPhoto" && lArrImgElems[k].id != "imgPnsnrSign")
		 {
		 	lArrImgElems[k].style.display = "block";
		 }
	 }
	 document.getElementById("btnClose").disabled = false;
	 document.getElementById("divPhoto").disabled=false;
	 document.getElementById('descPhoto').value="";
	 document.getElementById("divSign").disabled=false;
	 document.getElementById('descSign').value="";
	 if(document.getElementById("hdnShowCaseFor").value != '5')
	 {
		 document.getElementById("radioPpoIdentifyY").disabled=true;
		 document.getElementById("radioPpoIdentifyN").disabled=true;
	 }
}							

</script>
<c:if test="${showCaseFor == '5'}">
<script>
document.getElementById("txtLedgerNo").disabled=false;
document.getElementById("txtPageNo").disabled=false;
</script>
</c:if>
<c:if test="${showCaseFor == '15' || showCaseFor == '20'}">
<script>
addRowForRemarks();
document.getElementById("btnRemarksTableAddRow").disabled=false;
document.getElementById("btnViewRemarks").disabled=false;
</script>
</c:if>
<c:if test="${showCaseFor == '15'}">
<script>
document.getElementById("txtLedgerNo").disabled=false;
document.getElementById("txtPageNo").disabled=false;
</script>
</c:if>
<c:if test="${showHistoryBtn == 'Y'}">
<script>
document.getElementById("btnPayHist").disabled=false;
</script>
</c:if> 

