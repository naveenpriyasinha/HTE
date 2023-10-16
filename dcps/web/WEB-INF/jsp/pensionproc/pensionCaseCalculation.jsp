<%try{ %>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>

<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>

<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseCalculation.js"></script>
<script type="text/javascript">
TOTALPNSN = '<fmt:message key="PPROC.TOTALPNSN" bundle="${pensionAlerts}"></fmt:message>';
NEWCVPMONTH = '<fmt:message key="PPROC.NEWCVPMONTH" bundle="${pensionAlerts}"></fmt:message>';
NEWCVPAMT = '<fmt:message key="PPROC.NEWCVPAMT" bundle="${pensionAlerts}"></fmt:message>';
NEWPENSIONAMT = '<fmt:message key="PPROC.NEWPENSIONAMT" bundle="${pensionAlerts}"></fmt:message>';
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lObjTrnPnsnProcPnsnCalcVO" value="${resValue.lObjTrnPnsnProcPnsnCalcVO}"></c:set>
<c:set var="lObjTrnPnsnProcInwardPensionVO" value="${resValue.lObjTrnPnsnProcInwardPensionVO}"></c:set>
<c:set var="lObjTrnPnsnProcRevisionVO" value="${resValue.lObjTrnPnsnProcRevisionVO}"></c:set>
<c:set var="caseStatus" value="${resValue.lStrStatusLookupId}"/>
<c:set var="draftFlag" value="${lObjTrnPnsnProcInwardPensionVO.draftFlag}" />
<c:set var="lagecyFlag" value="${resValue.LegacyFlag}" />
<c:if test="${(lObjTrnPnsnProcInwardPensionVO.caseType == 'NEW' || lagecyFlag == 89 ) || (caseStatus == 'APRVDBYAG' && lObjTrnPnsnProcInwardPensionVO.caseType == 'REVISION')}">
<c:choose>
		<c:when test="${lagecyFlag == 89 }">
			<c:set value="" var="varReadOnlyLegacy"></c:set>
			<c:set var="varDisplayLegacy" value="display: none;"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplayLegacy" value=""></c:set>
			<c:set value="" var="varReadOnlyLegacy"></c:set>
		</c:otherwise>
</c:choose>

<c:choose>
		<c:when test="${((caseStatus == 'SENDTOAG' || caseStatus == 'AGQUERY' || caseStatus == 'MISBYDDO' || caseStatus == 'APRVDBYAG')&& draftFlag == 65)|| lagecyFlag == 89}">
			<c:set var="display" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="display" value="style='display: none;'"></c:set>
		</c:otherwise>
</c:choose>
<div>&nbsp;</div>
<div align="center">
	<hdiits:button name="btnResetTop" type="button" captionid="PPROC.RESET" bundle="${pensionLabels}" onclick="resetAllAmnt();" style="${varDisplayLegacy}"/> 
	<hdiits:button name="btnCalculateTop" type="button" captionid="PPROC.CALCULATE"  bundle="${pensionLabels}" onclick="newPensionCalculation();" style="${varDisplayLegacy}"/>

</div>
<div>&nbsp;</div>
<input type="hidden" id="gratuity_type" name="gratuity_type" value="${lObjTrnPnsnProcPnsnCalcVO.gratuity_flag }" />
<c:choose>
	<c:when test="${lObjTrnPnsnProcPnsnCalcVO.manualEditFlag == 89}">
		<input type="checkbox" id="chkManualEditFlag" style="display: none;" name ="chkManualEditFlag" checked="checked" value="Y" />
		<div style="color: red;" id="divManualEdit" > <fmt:message key="PPROC.MANUALEDIT" bundle="${pensionLabels}"></fmt:message></div>
	</c:when>
	<c:otherwise>
		<input type="checkbox" id="chkManualEditFlag" style="display: none;" name ="chkManualEditFlag" value="Y" />
		<div style="color: red;display: none;" id="divManualEdit"> <fmt:message key="PPROC.MANUALEDIT" bundle="${pensionLabels}"></fmt:message></div>
	</c:otherwise>
</c:choose>


<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="CVP"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<input type="hidden" name="hdnCvpRate" id="hdnCvpRate"  value="" />
	<input type="hidden" name="hdnCvpTempDate" id="hdnCvpTempDate"  value="" />
	<input type="hidden" name="hdnTempDate" id="hdnTempDate"  value="" />
	<input type="hidden" name="hdnTempDate1" id="hdnTempDate1"  value="" />
	<input type="hidden" name="hdnTempDate2" id="hdnTempDate2"  value="" />
	<input type="hidden" name="hdnTempDate3" id="hdnTempDate3"  value="" />
	<input type="hidden" name="hdnTempDate4" id="hdnTempDate4"  value="" />
	
	
	<input type="hidden" name="hdnFirstDate6thPC" id="hdnFirstDate6thPC"  value="01/01/2006" />
	<input type="hidden" name="hdnActualDate6thPC" id="hdnActualDate6thPC"  value="27/02/2006" />
	<input type="hidden" name="hdnDCRGMaxDate" id="hdnDCRGMaxDate"  value="01/09/2009" />
	<input type="hidden" name="hdnDCRGMinDate" id="hdnDCRGMinDate"  value="31/08/2009" />
<table width="60%" align="left">	
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CVPRATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" id="txtCvpRate" maxlength="20"	name="txtCvpRate"  value = "${lObjTrnPnsnProcPnsnCalcVO.cvpRate}" readonly="readonly" style="text-align: right" />
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CVPAPPDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" name="txtCvpAppDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtCvpAppDate"
			 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.cvpAppDate}" />"	 /> 
			 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgCpoDate"	onClick='window_open(event,"txtCvpAppDate",375,570)'style="cursor: pointer;"/>			
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
		
	</tr>
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.CPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<input type="text" id="txtCpoNo" maxlength="20"	name="txtCpoNo"  value = "${lObjTrnPnsnProcPnsnCalcVO.cpoNo}"  style="text-align: right" /></td>
	</tr>
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.CPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
		<input type="text" name="txtCpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtCpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.cpoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgCpoDate"	onClick='window_open(event,"txtCpoDate",375,570)'style="cursor: pointer;"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<input type="text" id="txtMonthAmt" maxlength="7"
			name="txtMonthAmt"  readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.cvpMonthAmnt}" onchange="isManualEditOrNot()" onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy} style="text-align: right" onblur="setValidAmountFormat(this);setTotalPnsnAmt();"/></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CVPAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<input type="text" maxlength="7"
			id="txtCVPAmt" name="txtCVPAmt"  readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.cvpAmnt}" onchange="isManualEditOrNot()" onkeypress="amountFormat(this,event);"${varReadOnlyLegacy} style="text-align: right" onblur="setValidAmountFormat(this);setCVPAmt();setTotalPnsnAmt();"/></td>
	</tr>	
</table>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="PENSION"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="60%" align="left">
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.PPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<input type="text" id="txtPpoNo" maxlength="20"	name="txtPpoNo"  value ="${lObjTrnPnsnProcInwardPensionVO.ppoNo}"  style="text-align: right" /></td>
	</tr>
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.PPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
		<input type="text" name="txtPpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtPpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcInwardPensionVO.ppoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgPpoDate" onClick='window_open(event,"txtPpoDate",375,570)'style="cursor: pointer;"/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtTotPnsnAmt" name="txtTotPnsnAmt" readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerTotalAmnt}" onchange="isManualEditOrNot()" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" onblur="setValidAmountFormat(this);setTotalPnsnAmt();"/>
			</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.REDUCEPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="10%" align="left"><fmt:message key="PPROC.MINUS"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
	</tr>
	<tr>
		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtRedsPnsnAmt" name="txtRedsPnsnAmt" readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerReducedAmnt}" onchange="isManualEditOrNot()" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" /></td>

		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtTotlPnsnAmt" name="txtTotlPnsnAmt" readonly="readonly" ${varReadOnlyLegacy} onchange="isManualEditOrNot()" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerTotalAmnt}" onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>
		<td></td>
		<td width="30%" align="left"><input type="text" maxlength="7" id="txtComPnsnAmt" readonly="readonly" onchange="isManualEditOrNot()" value = "${lObjTrnPnsnProcPnsnCalcVO.cvpMonthAmnt}" onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy}
			name="txtComPnsnAmt"  style="text-align: right" /></td>
	</tr>
</table>
</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="DCRGORSERVGRATUITY"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="60%" align="left" >
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.GPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
		<input type="text" id="txtGpoNo" maxlength="20"	name="txtGpoNo"  value = "${lObjTrnPnsnProcPnsnCalcVO.gpoNo}"  style="text-align: right" /></td>
	</tr>
	<tr ${display}>
		<td width="20%" align="left"><fmt:message key="PPROC.GPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">		
		<input type="text" name="txtGpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtGpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.gpoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgGpoDate"	onClick='window_open(event,"txtGpoDate",375,570)'style="cursor: pointer;"/>
		</td>
	</tr>
	<tr>
	<td><input type="checkbox" name="radiogratuity" id="deathgratuity" onclick="divexpand();" /><fmt:message key="DEATH"
			bundle="${pensionLabels}"></fmt:message></td>
			<td><input type="checkbox" name="radiogratuity" id="retiregratuity" onclick="divexpand();" /><fmt:message key="RETIREMENT"
			bundle="${pensionLabels}"></fmt:message></td>
			<td><input type="checkbox" name="radiogratuity" id="servicegratuity"  onclick="divexpand();"/><fmt:message key="SERVICEGRAT"
			bundle="${pensionLabels}"></fmt:message></td>
	
	</tr>
	</table>
	<br><br>
	<div id="checkboxdepend" style="display:none" align="left">
	<table  width="60%" align="left">
	<tr>	
	<td width="20%" align="left"><fmt:message key="PPROC.DCRGAPLDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left">
			<input type="text" name="txtDCRGAppDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtDCRGAppDate"
			 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.dcrgAppDate}" />"	 /> 
			 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgCpoDate"	onClick='window_open(event,"txtDCRGAppDate",375,570)'style="cursor: pointer;"/>			 
	</td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.SERVGRATYAMT" 
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtServGratyAmt" name="txtServGratyAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.srvcGratuityAmnt}" onchange="isManualEditOrNot()" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" readonly="readonly" /></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.WITHGRA" 
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtWitheldGratuity" name="txtWitheldGratuity" value = "${lObjTrnPnsnProcPnsnCalcVO.witheldGratuity}" onblur="validateGratuity();newPensionCalculation();" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.NETAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="30%" align="left"><input type="text" maxlength="7"
			id="txtTotDCRGAmt" name="txtTotDCRGAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.dcrgAmnt}" onchange="isManualEditOrNot()" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" readonly="readonly"/></td>
	</tr>
	</table>
	</div>

</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="FAMILYPNSN"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="100%" align="left">
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.FP1DATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left">
		<input type="text" name="txtFP1Date" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtFP1Date" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.fp1Date}" />"
			 readonly="readonly"/> 
			
			
			</td>
			<td width="20%" align="left"><fmt:message key="PPROC.FP1TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtFP1TotlAmt" name="txtFP1TotlAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.fp1Amnt}" onchange="isManualEditOrNot()" onkeypress="amountFormat(this,event);" style="text-align: right" readonly="readonly" /></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.FP2STARTDATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)"
			id="txtFP2Date" name="txtFP2Date" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value = "${lObjTrnPnsnProcPnsnCalcVO.fp2Date}"  />" readonly="readonly"/>
			 
			</td>
			<td width="20%" align="left"><fmt:message key="PPROC.FP2TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtFP2TotlAmt" name="txtFP2TotlAmt" onchange="isManualEditOrNot()" value = "${lObjTrnPnsnProcPnsnCalcVO.fp2Amnt}" onkeypress="amountFormat(this,event);" style="text-align: right" readonly="readonly"/></td>
	</tr>
</table>
</fieldset>
<div>&nbsp;</div>
<div align="center">
	<hdiits:button name="btnResetBottom" type="button" captionid="PPROC.RESET" bundle="${pensionLabels}" onclick="resetAllAmnt();" style="${varDisplayLegacy}"/> 
	<hdiits:button name="btnCalculateBottom" type="button" captionid="PPROC.CALCULATE"  bundle="${pensionLabels}" onclick="newPensionCalculation();" style="${varDisplayLegacy}"/>

</div>
</c:if>
<c:if test="${lObjTrnPnsnProcInwardPensionVO.caseType == 'REVISION' && caseStatus != 'APRVDBYAG'}">
<c:choose>
		<c:when test="${lagecyFlag == 89 }">
			<c:set value="" var="varReadOnlyLegacy"></c:set>
			<c:set var="varDisplayLegacy" value="display: none;"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="varDisplayLegacy" value=""></c:set>
			<c:set value="" var="varReadOnlyLegacy"></c:set>
		</c:otherwise>
</c:choose>

<c:choose>
		<c:when test="${((caseStatus == 'SENDTOAG' || caseStatus == 'AGQUERY' || caseStatus == 'MISBYDDO' || caseStatus == 'APRVDBYAG')&& draftFlag == 65)|| lagecyFlag == 89 }">
			<c:set var="display" value=""></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="display" value="style='display: none;'"></c:set>
		</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${lObjTrnPnsnProcPnsnCalcVO.manualEditFlag == 89}">
		<input type="checkbox" id="chkManualEditFlag" style="display: none;" name ="chkManualEditFlag" checked="checked" value="Y" />
		<div style="color: red;" id="divManualEdit" > <fmt:message key="PPROC.MANUALEDIT" bundle="${pensionLabels}"></fmt:message></div>
	</c:when>
	<c:otherwise>
		<input type="checkbox" id="chkManualEditFlag" style="display: none;" name ="chkManualEditFlag" value="Y"/>
		<div style="color: red;display: none;" id="divManualEdit"> <fmt:message key="PPROC.MANUALEDIT" bundle="${pensionLabels}"></fmt:message></div>
	</c:otherwise>
</c:choose>
<div>&nbsp;</div>
<div align="center">
	<hdiits:button name="btnResetTop" type="button" captionid="PPROC.RESET" bundle="${pensionLabels}" onclick="resetAllAmntRevision();" style="${varDisplayLegacy}"/> 
	<hdiits:button name="btnCalculateTop" type="button" captionid="PPROC.CALCULATE"  bundle="${pensionLabels}" onclick="newPensionCalculationRevision();" style="${varDisplayLegacy}"/>
</div>
<div>&nbsp;</div>
<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="CVP"
	bundle="${pensionLabels}"></fmt:message></b></legend>
	<input type="hidden" name="hdnCvpRate" id="hdnCvpRate"  value="" />
	<input type="hidden" name="hdnCvpTempDate" id="hdnCvpTempDate"  value="" />
	<input type="hidden" name="hdnTempDate" id="hdnTempDate"  value="" />
	<input type="hidden" name="hdnTempDate1" id="hdnTempDate1"  value="" />
	<input type="hidden" name="hdnTempDate2" id="hdnTempDate2"  value="" />
	<input type="hidden" name="hdnTempDate3" id="hdnTempDate3"  value="" />
	<input type="hidden" name="hdnTempDate4" id="hdnTempDate4"  value="" />
	
	
	<input type="hidden" name="hdnFirstDate6thPC" id="hdnFirstDate6thPC"  value="01/01/2006" />
	<input type="hidden" name="hdnActualDate6thPC" id="hdnActualDate6thPC"  value="27/02/2006" />
	<input type="hidden" name="hdnDCRGMaxDate" id="hdnDCRGMaxDate"  value="01/09/2009" />
	<input type="hidden" name="hdnDCRGMinDate" id="hdnDCRGMinDate"  value="31/08/2009" />
<table width="100%" align="left">	
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CVPRATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" id="txtCvpRate" maxlength="20"	name="txtCvpRate"  value = "${lObjTrnPnsnProcPnsnCalcVO.cvpRate}" readonly="readonly" style="text-align: right" />
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CVPAPPDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" name="txtCvpAppDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtCvpAppDate"
			 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.cvpAppDate}" />"	readonly="readonly" />
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" id="txtCpoNo" maxlength="20"	name="txtCpoNo"  value = "${lObjTrnPnsnProcPnsnCalcVO.cpoNo}"  style="text-align: right" />
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.CPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">		
		<input type="text" name="txtCpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtCpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.cpoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgCpoDate"	onClick='window_open(event,"txtCpoDate",375,570)'style="cursor: pointer;"/>
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" id="txtMonthAmt" maxlength="7"
			name="txtMonthAmt"  value = "${lObjTrnPnsnProcPnsnCalcVO.cvpMonthAmnt}" readonly="readonly"  onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy} style="text-align: right" onblur="setValidAmountFormat(this);setTotalPnsnAmt();"/></td>
		<td width="20%" align="left">New <fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" id="txtNewMonthAmt" maxlength="7"
			name="txtNewMonthAmt"  onkeypress="amountFormat(this,event);" value="${lObjTrnPnsnProcRevisionVO.cvpMonthAmnt}" onchange="isManualEditOrNot()" style="text-align: right" onblur="setNewCVPMonthlyAmount();setNewReducePensionAmount();"/>
			<label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		<td width="10%" align="left"><fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%" align="left">
		<input type="text" id="txtDiffMonthAmt" maxlength="7" value="${lObjTrnPnsnProcRevisionVO.diffCvpMonthAmnt}"
			name="txtDiffMonthAmt" readonly="readonly"  onkeypress="amountFormat(this,event);" style="text-align: right" onblur=""/>
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.CVPAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" maxlength="7"
			id="txtCVPAmt" name="txtCVPAmt"  value = "${lObjTrnPnsnProcPnsnCalcVO.cvpAmnt}" readonly="readonly" onkeypress="amountFormat(this,event);"${varReadOnlyLegacy} style="text-align: right" onblur="setValidAmountFormat(this);setCVPAmt();setTotalPnsnAmt();"/></td>
		<td width="20%" align="left">New <fmt:message key="PPROC.CVPAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" id="txtNewCVPAmt" maxlength="7" value="${lObjTrnPnsnProcRevisionVO.cvpAmnt}" onchange="isManualEditOrNot()"
			name="txtNewCVPAmt"   onkeypress="amountFormat(this,event);" style="text-align: right" onblur="setNewCVPAmount();setNewReducePensionAmount();"/>
		<label id="mandtryFinal" class="mandatoryindicator">*</label></td>
		<td width="10%" align="left"><fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message>
		</td>
		<td width="20%" align="left">
		<input type="text" id="txtDiffCVPAmt" maxlength="7" value="${lObjTrnPnsnProcRevisionVO.diffCvpAmnt}"
			name="txtDiffCVPAmt" readonly="readonly"  onkeypress="amountFormat(this,event);" style="text-align: right" onblur=""/>
		</td>
	</tr>	
</table>
</fieldset>
<fieldset style="width: 100%" class="tabstyle"><legend id="headingMsg"><b> <fmt:message key="PENSION"	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="100%">
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.PPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" id="txtPpoNo" maxlength="20"	name="txtPpoNo"  value ="${lObjTrnPnsnProcInwardPensionVO.ppoNo}"  style="text-align: right" /></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.PPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">		
		<input type="text" name="txtPpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtPpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcInwardPensionVO.ppoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgPpoDate" onClick='window_open(event,"txtPpoDate",375,570)'style="cursor: pointer;"/>
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtTotPnsnAmt" name="txtTotPnsnAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerTotalAmnt}" readonly="readonly"  ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" onblur="setValidAmountFormat(this);setTotalPnsnAmt();"/>
		</td>
		<td width="20%" align="left">New <fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7" onchange="isManualEditOrNot()"
			id="txtNewTotPnsnAmt" name="txtNewTotPnsnAmt" value="${lObjTrnPnsnProcRevisionVO.pensionerTotalAmnt}" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" onblur="setNewTotalPensionAmount();setNewReducePensionAmount();"  style="text-align: right" />
			<label id="mandtryFinal" class="mandatoryindicator">*</label>
		</td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left">
		<input type="text" maxlength="7" id="txtDiffTotPnsnAmt" name="txtDiffTotPnsnAmt" value="${lObjTrnPnsnProcRevisionVO.diffPensionerTotalAmnt}" readonly="readonly" onkeypress="amountFormat(this,event);"  style="text-align: right;display: none;" />
		</td>
	</tr>
</table>
<table width="70%">
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.REDUCEPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">Old <fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><fmt:message key="PPROC.MINUS"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">Old <fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtRedsPnsnAmt" name="txtRedsPnsnAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerReducedAmnt}" readonly="readonly" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" /></td>

		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtTotlPnsnAmt" name="txtTotlPnsnAmt" ${varReadOnlyLegacy} readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.pensionerTotalAmnt}" onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"><input type="text" maxlength="7" id="txtComPnsnAmt" readonly="readonly" value = "${lObjTrnPnsnProcPnsnCalcVO.cvpMonthAmnt}" onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy}
			name="txtComPnsnAmt"  style="text-align: right" /></td>
	</tr>
	<tr>
		<td width="20%" align="left">New <fmt:message key="PPROC.REDUCEPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">New <fmt:message key="PPROC.TOTPNSNAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><fmt:message key="PPROC.MINUS"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">New <fmt:message key="PPROC.COMPENAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtNewRedsPnsnAmt" name="txtNewRedsPnsnAmt" value="${lObjTrnPnsnProcRevisionVO.pensionerReducedAmnt}" readonly="readonly" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" /></td>

		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtNewTotlPnsnAmt" name="txtNewTotlPnsnAmt" ${varReadOnlyLegacy} readonly="readonly" value="${lObjTrnPnsnProcRevisionVO.pensionerTotalAmnt}" onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"><input type="text" maxlength="7" id="txtNewComPnsnAmt" readonly="readonly" value="${lObjTrnPnsnProcRevisionVO.cvpMonthAmnt}" onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy}
			name="txtNewComPnsnAmt"  style="text-align: right" /></td>
	</tr>
	<tr>
		<td width="20%" align="left"> <fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"> <fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		
	</tr>
	<tr>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtDiffRedsPnsnAmt" name="txtDiffRedsPnsnAmt" value="${lObjTrnPnsnProcRevisionVO.diffPensionerReducedAmnt}" readonly="readonly" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" /></td>

		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtDiffTotlPnsnAmt" name="txtDiffTotlPnsnAmt" ${varReadOnlyLegacy} readonly="readonly" value="${lObjTrnPnsnProcRevisionVO.diffPensionerTotalAmnt}" onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left">
		<input type="text" maxlength="7" id="txtDiffComPnsnAmt" readonly="readonly" value="${lObjTrnPnsnProcRevisionVO.diffCvpMonthAmnt}" onkeypress="amountFormat(this,event);" ${varReadOnlyLegacy}
			name="txtDiffComPnsnAmt"  style="text-align: right;display: none;"/>
			</td>
	</tr>
</table>

</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="DCRGORSERVGRATUITY"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="100%" align="left">
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.GPONO"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
		<input type="text" id="txtGpoNo" maxlength="20"	name="txtGpoNo"  value = "${lObjTrnPnsnProcPnsnCalcVO.gpoNo}"  style="text-align: right" /></td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left"><fmt:message key="PPROC.GPODATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">		
		<input type="text" name="txtGpoDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtGpoDate"
		 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.gpoDate}" />"	 /> 
		 <img src='images/CalendarImages/ico-calendar.gif' width='20' id="imgGpoDate"	onClick='window_open(event,"txtGpoDate",375,570)'style="cursor: pointer;"/>
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
	<td><input type="checkbox" name="radiogratuity" id="deathgratuity" onclick="divexpand();" /><fmt:message key="DEATH"
			bundle="${pensionLabels}"></fmt:message></td>
			<td><input type="checkbox" name="radiogratuity" id="retiregratuity" onclick="divexpand();" /><fmt:message key="RETIREMENT"
			bundle="${pensionLabels}"></fmt:message></td>
			<td><input type="checkbox" name="radiogratuity" id="servicegratuity"  onclick="divexpand();"/><fmt:message key="SERVICEGRAT"
			bundle="${pensionLabels}"></fmt:message></td>
	
	</tr>
	</table>
	<br><br>
	<div id="checkboxdepend" style="display:none" align="left">
	<table  width="60%" align="left">
	<tr>	
	<td width="20%" align="left"><fmt:message key="PPROC.DCRGAPLDATE" bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left">
			<input type="text" name="txtDCRGAppDate" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtDCRGAppDate"
			 value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.dcrgAppDate}" />"	readonly="readonly" />						 
		</td>
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.SERVGRATYAMT" 
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtServGratyAmt" name="txtServGratyAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.srvcGratuityAmnt}" readonly="readonly"  ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" /></td>			
		<td width="20%" align="left">New <fmt:message key="PPROC.SERVGRATYAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7" onchange="isManualEditOrNot()"
			id="txtNewServGratyAmt" name="txtNewServGratyAmt" value="${lObjTrnPnsnProcRevisionVO.srvcGratuityAmnt}" onblur="setNewServGratuityAmount();" ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);"  style="text-align: right" />
		</td>
		<td width="10%" align="left"><fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtDiffServGratyAmt" name="txtDiffServGratyAmt" value="${lObjTrnPnsnProcRevisionVO.diffSrvcGratuityAmnt}" readonly="readonly" onkeypress="amountFormat(this,event);"  style="text-align: right" />
		</td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.NETAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtTotDCRGAmt" name="txtTotDCRGAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.dcrgAmnt}" readonly="readonly"  ${varReadOnlyLegacy} onkeypress="amountFormat(this,event);" style="text-align: right" /></td>
		<td width="20%" align="left">New <fmt:message key="PPROC.NETAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7" onchange="isManualEditOrNot()"
			id="txtNewTotDCRGAmt" name="txtNewTotDCRGAmt" value="${lObjTrnPnsnProcRevisionVO.dcrgAmnt}" ${varReadOnlyLegacy}onblur="setNewDCRGAmount()" onkeypress="amountFormat(this,event);"  style="text-align: right" />
		</td>
		<td width="10%" align="left"><fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtDiffTotDCRGAmt" name="txtDiffTotDCRGAmt" value="${lObjTrnPnsnProcRevisionVO.diffDcrgAmnt}" readonly="readonly" onkeypress="amountFormat(this,event);"  style="text-align: right" />
		</td>
	</tr>	
</table>
</div>
</fieldset>

<fieldset style="width: 100%" class="tabstyle"><legend
	id="headingMsg"><b> <fmt:message key="FAMILYPNSN"
	bundle="${pensionLabels}"></fmt:message></b></legend>
<table width="100%" align="left">
	<tr>
		<td width="20%" align="left"> DATE OF PNSN</td>
		<td width="15%" align="left">
			<input type="text" name="txtFP1Date" readonly="readonly" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)" id="txtFP1Date" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcPnsnCalcVO.fp1Date}" />"/> 
		 	<!--<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open(event,"txtFP1Date",375,570)'	style="cursor: pointer;"/>-->
		</td>					
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.FP1TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtFP1TotlAmt" name="txtFP1TotlAmt" value = "${lObjTrnPnsnProcPnsnCalcVO.fp1Amnt}" readonly="readonly" onkeypress="amountFormat(this,event);" style="text-align: right"  /></td>
		<td width="20%" align="left">New <fmt:message key="PPROC.FP1TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7" onchange="isManualEditOrNot()"
			id="txtNewFP1TotlAmt" name="txtNewFP1TotlAmt" value="${lObjTrnPnsnProcRevisionVO.fp1Amnt}" onblur="setNewFP1Amount()" onkeypress="amountFormat(this,event);" style="text-align: right"  /></td>
		<td width="10%" align="left"><fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtDiffFP1TotlAmt" name="txtDiffFP1TotlAmt" value="${lObjTrnPnsnProcRevisionVO.diffFp1Amnt}" readonly="readonly" onkeypress="amountFormat(this,event);" style="text-align: right"  /></td>
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.FP2STARTDATE"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" onkeypress="digitFormat(this);dateFormat(this);numberFormat(event)"
			id="txtFP2Date" name="txtFP2Date" readonly="readonly" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value = "${lObjTrnPnsnProcPnsnCalcVO.fp2Date}"  />" />
			<!--<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open(event,"txtFP2Date",375,570)'	style="cursor: pointer;" />-->
		</td>	
		<td width="20%" align="left"></td>
		<td width="15%" align="left"></td>
		<td width="10%" align="left"></td>
		<td width="20%" align="left"></td>	
	</tr>
	<tr>
		<td width="20%" align="left">Old <fmt:message key="PPROC.FP2TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7"
			id="txtFP2TotlAmt" name="txtFP2TotlAmt"  value = "${lObjTrnPnsnProcPnsnCalcVO.fp2Amnt}" readonly="readonly" onkeypress="amountFormat(this,event);" style="text-align: right" /></td>
		<td width="20%" align="left">New <fmt:message key="PPROC.FP2TOTALAMT"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="15%" align="left"><input type="text" maxlength="7" onchange="isManualEditOrNot()"
			id="txtNewFP2TotlAmt" name="txtNewFP2TotlAmt"  value="${lObjTrnPnsnProcRevisionVO.fp2Amnt}" onblur="setNewFP2Amount();" onkeypress="amountFormat(this,event);" style="text-align: right" /></td>
		<td width="10%" align="left"> <fmt:message key="PPROC.DIFF"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="20%" align="left"><input type="text" maxlength="7"
			id="txtDiffFP2TotlAmt" name="txtDiffFP2TotlAmt"  value="${lObjTrnPnsnProcRevisionVO.diffFp2Amnt}" readonly="readonly" onkeypress="amountFormat(this,event);" style="text-align: right" /></td>
	</tr>
</table>
</fieldset>
<div>&nbsp;</div>
<div align="center">
	<hdiits:button name="btnResetBottom" type="button" captionid="PPROC.RESET" bundle="${pensionLabels}" onclick="resetAllAmntRevision();" style="${varDisplayLegacy}"/> 
	<hdiits:button name="btnCalculateBottom" type="button" captionid="PPROC.CALCULATE"  bundle="${pensionLabels}" onclick="newPensionCalculationRevision();" style="${varDisplayLegacy}"/>
</div>
</c:if>
<script>

var txtDateOfExpiry=document.getElementById("txtDateOfExpiry").value;

if(txtDateOfExpiry==null || txtDateOfExpiry==""){
	
	setFP1AndFp2Date(1);
}
else{
	
	setFP1AndFp2Date(2);
}


function isManualEditOrNot(){
	document.getElementById("divManualEdit").style.display = "";
	document.getElementById("chkManualEditFlag").checked = true;
}
</script>

<script>
init_for_gratuity();
</script>

<%}catch(Exception e){
e.printStackTrace();
}%>