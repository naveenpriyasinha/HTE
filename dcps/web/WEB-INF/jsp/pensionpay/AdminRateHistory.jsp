<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TIRateTypeText" value="${resValue.TIRateTypeText}"></c:set>
<c:set var="HeadCodeTypeText" value="${resValue.HeadCodeTypeText}"></c:set>
<c:set var="ForPension" value="${resValue.ForPension}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionConstant"
	var="pensionConst" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionLabels"
	var="PensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionAlerts"
	var="PensionAlerts" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionToolTip"
	var="pensionToolTip" scope="request" />






<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
	









<script type="text/javascript">
keyPreview = 1;
var PEN_DELETEOPTION = "<fmt:message key="PEN.DELETEOPTION" bundle="${PensionAlerts}"></fmt:message>";
var ADM_DATEVALIDATION = "<fmt:message key="ADM.DATEVALIDATION" bundle="${PensionAlerts}"></fmt:message>";
var ADM_DATEVALINPAYMONTH = "<fmt:message key="ADM.DATEVALINPAYMONTH" bundle="${PensionAlerts}"></fmt:message>";
var ADM_EFFDATEVALIDATION = "<fmt:message key="ADM.EFFDATEVALIDATION" bundle="${PensionAlerts}"></fmt:message>";

var SRCH_DTFORMAT = "<fmt:message key="CMN.DTFORMAT" bundle="${PensionAlerts}"></fmt:message>";
var SRCH_VALMNTH = "<fmt:message key="CMN.VALMNTH" bundle="${PensionAlerts}"></fmt:message>";
var SRCH_VALDAY = "<fmt:message key="CMN.VALDAY" bundle="${PensionAlerts}"></fmt:message>";
var SRCH_VALDIGIT ="<fmt:message key="CMN.VALDIGIT" bundle="${PensionAlerts}"></fmt:message>"+1900+"<fmt:message key="CMN.AND" bundle="${PensionAlerts}"></fmt:message>"+2500;
var SRCH_VALDT = "<fmt:message key="CMN.VALDT" bundle="${PensionAlerts}"></fmt:message>";

var ADM_CONFIG = "<fmt:message key="ADM.CONFIG" bundle="${PensionAlerts}"></fmt:message>";
var ADM_TITYPE = "<fmt:message key="ADM.TITYPE" bundle="${PensionAlerts}"></fmt:message>";
var ADM_PENSION = "<fmt:message key="ADM.PENSION" bundle="${PensionAlerts}"></fmt:message>";
var ADM_HEADCODE = "<fmt:message key="ADM.HEADCODE" bundle="${PensionAlerts}"></fmt:message>";
var ADM_HEADCODE1 = "<fmt:message key="ADM.HEADCODE" bundle="${PensionLabels}"></fmt:message>";
var ADM_EFFDATE = "<fmt:message key="ADM.EFFDATE" bundle="${PensionAlerts}"></fmt:message>";
	
</script>

<head>
<script type="text/javascript"	src="script/common/val.js"></script>
<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/pensionpay/Common.js"> </script>
<script type="text/javascript" src="script/pensionpay/PensionCase.js"> </script>
<script type="text/javascript" src="script/pensionpay/AdminRateMst.js"></script>



</head>

<hdiits:form name="AdminHistoryScreen" validate="false" method="post" id="AdminHistoryScreen">

	
	<input type="hidden" id="hidTiRateTypeText" name="hidTiRateTypeText" ></input>
	<input type="hidden" id="hidForPensionText" name="hidForPensionText" ></input>
	<input type="hidden" id="hidHeadCode" name="hidHeadCode" ></input>
	<fieldset style="width: 100%;" class="tabstyle"><legend
				id="headingMsg" class="Label"> <b> <fmt:message
				key="DARATEHST.CONFIG" bundle="${PensionLabels}"></fmt:message> </b> </legend> <br>
			<br>
			<table width="100%">
				<tr>
					<td>

					<td colspan="3">
					<input type="radio" name="radioConfig"
						id="configTI" value="TI" checked="checked"  
						onclick="showTiRateType(this),updateTIVar(this),showMinAmt(this)" />

					</td>
				</tr>

	<tr id="tiRateTypeId">
					<td><fmt:message key="ADM.TIRATETYPE"
						bundle="${PensionLabels}"></fmt:message>
						<label class="mandatoryindicator">*</label></td>
					<td><select name="TIRateType" id="TIRateType"
						onchange="showForPensionCmb(this),getDataFromTI()">
						<option value="-1">--- SELECT ---</option>
						<c:forEach var="listTIRate" items="${resValue.listTIRate}">
							<c:choose>
								<c:when test="${listTIRate.id == resValue.TIRateType}">
									<option value='${listTIRate.id}' selected="selected">
										<c:out value="${listTIRate.desc}"/>
									</option>
								</c:when>
								<c:otherwise>
									<option value='${listTIRate.id}'>
										<c:out value="${listTIRate.desc}"/>
									</option>
								</c:otherwise>
							</c:choose>			
						</c:forEach>
					</select></td>
					<td id="tiForPensionId1" style="display: none;"><fmt:message
						key="ADM.FORPENSION" bundle="${PensionLabels}"></fmt:message></td>
					<td id="tiForPensionId2" style="display: none;"><select
						name="ForPension" id="ForPension"
						onchange="getDataFromPension()">
						<option value="-1">--- SELECT ---</option>
						<c:forEach var="listForPension" items="${resValue.listForPension}">
							<option value='${listForPension.id}'><c:out
								value="${listForPension.desc}"></c:out></option>
						</c:forEach>
					</select></td>
				</tr>
				
				<tr>
					<td><fmt:message key="ADM.STATEDEPT" bundle="${PensionLabels}"></fmt:message>
					<label class="mandatoryindicator">*</label></td>
					<td><select name="cmbStateCode" id="cmbStateCode"
						onchange="getDataFromStateCode('HIS')">
						<option value="-1">--- SELECT ---</option>
						<c:forEach var="ListOfStateCode" items="${resValue.lLstStateDept}">
							<c:choose>								
								<c:when test="${ListOfStateCode.id == resValue.HeadCode}">
									<c:out value="${ListOfStateCode.id}"></c:out>
									<option value='${ListOfStateCode.id}' selected="selected">
										<c:out value="${ListOfStateCode.desc}"></c:out>
									</option>
								</c:when>
								<c:otherwise>
									<option value='${ListOfStateCode.id}'>
										<c:out value="${ListOfStateCode.desc}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select></td>
					<td><fmt:message key="ADM.DESC" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><input type="text" id="txtHeadcodeDesc" name="txtHeadcodeDesc" value="${resValue.HeadCodeDesc}" size="45" />					
					</td>
				</tr>
				
				<tr>
					<td><fmt:message key="DARATEHST.CURRDARATE" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><input type="text" id="txtOldRate" name="txtOldRate"
						onkeypress="amountFormat(this)" readonly="true" value="${resValue.rate}"/></td>
					<td><fmt:message key="ADM.EFFFROM" bundle="${PensionLabels}"></fmt:message>
					<label class="mandatoryindicator">*</label></td>
					<td><input type="text" name="oldtxtEffectedDate"
						id="oldtxtEffectedDate" readonly="true" value="${resValue.EffFrmDate}"/></td>
				</tr>

				<tr>
					<td id="OldMinAmt1" style="display: none;"><fmt:message
						key="ADM.OLDAMT" bundle="${PensionLabels}"></fmt:message></td>
					<td id="OldMinAmt2" style="display: none;"><input type="text"
						id="txtOldMinAmt" name="txtOldMinAmt"
						onkeypress="amountFormat(this)" readonly="true" /></td>
					<td id="NewMinAmt1" style="display: none;"><fmt:message
						key="ADM.NEWAMT" bundle="${PensionLabels}"></fmt:message></td>
					<td id="NewMinAmt2" style="display: none;"><input type="text"
						id="txtNewMinAmt" name="txtNewMinAmt"
						onkeypress="amountFormat(this)" /></td>
				</tr>
			<tr>
				</table>
				<div>&nbsp;</div>	
			<div align="center"><input type="hidden"
	name="hidDARateGridSize" id="hidDARateGridSize" value="1" />
			<hdiits:button	name="AdminRateHisotryAddRow" type="button" captionid="DARATEHST.ADDROW"
	bundle="${PensionLabels}" onclick="AdminRateAddRow();" />
	<hdiits:button name="btnHistory" id="btnHistory" type="button" captionid="DARATEHST.HISTORY" bundle="${PensionLabels}" onclick="viewDaRateHistory();"/>
			</div>
			<div>&nbsp;</div>
				
			<table id="tblAdminRateHisotry" align="center" width="90%" cellspacing="0" border="2">
			<tr class="datatableheader" style="width: 90px">
				<td width="20%" class="HLabel"><fmt:message key="DARATEHST.FROMDATE" bundle="${PensionLabels}"></fmt:message></td>
				<td width="20%" class="HLabel"><fmt:message key="DARATEHST.TODATE" bundle="${PensionLabels}"></fmt:message></td>
				<td width="20%" class="HLabel"><fmt:message key="DARATEHST.RATE" bundle="${PensionLabels}"></fmt:message></td>
				<td width="20%" class="HLabel"><fmt:message key="DARATEHST.AMNT" bundle="${PensionLabels}"></fmt:message></td>
				<td width="10%" class="HLabel"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>
			</tr>
			
			<c:choose>

			<c:when test="${resValue.DARateDetails != null}">
				<c:forEach var="DARateDetails" items="${resValue.DARateDetails}" varStatus="Counter">
					<tr>
						<td class="HLabel" align="center">
							<fmt:formatDate var="FromDate" value="${DARateDetails[0]}" pattern="dd/MM/yyyy"/>
							<input type="text" name="txtFromDate"  id="txtFromDate${Counter.index}" style="width:120px"  onblur="chkFutureDate(this);" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"  class="texttag, textString"  size="10" value="${FromDate}" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick='window_open("txtFromDate${Counter.index}",375,570)' style="cursor: pointer;" />
							<input type="hidden" id="hidTablePk${Counter.index}" name="hidTablePk" value="${DARateDetails[4]}" />
						</td>
							
						<td class="HLabel" align="center">
							<fmt:formatDate var="ToDate" value="${DARateDetails[1]}" pattern="dd/MM/yyyy"/>
							<input type="text" name="txtToDate"  id="txtToDate${Counter.index}" style="width:120px" onblur="chkFutureDate(this);validateWithFromDate(${Counter.index});" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10"  class="texttag, textString"  size="10" value="${ToDate}" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" onClick='window_open("txtToDate${Counter.index}",375,570)' style="cursor: pointer;" />
						</td>	
   		
   						<td class="HLabel" align="center">
   							<input type="text" name="txtDARate" id="txtDARate${Counter.index}" size="30" maxlength="7" value="${DARateDetails[2]}" onkeypress="numberFormat(event);" style="width:120px" />
   						</td>	
   		
   						<td class="HLabel" align="center">
   							<input type="text" name="txtMinAmnt" id="txtMinAmnt${Counter.index}" size="30" onkeypress="numberFormat(event);" value="${DARateDetails[3]}" style="width:120px;text-align: right" />
   						</td>	
   		
   						<td class="HLabel" align="center">
   							<img name="Image" id="Image${Counter.index}" src='images/CalendarImages/DeleteIcon.gif' onClick='RemoveTableRow(this,"tblAdminRateHisotry");'/>
   						</td>						
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
			
		</table><br>
		<div>&nbsp;</div>
			<div align="center">
				<hdiits:button name="btnSave" id="btnSave" type="button" captionid="DARATEHST.SAVE" bundle="${PensionLabels}" onclick="saveDaRateHistory();"/>
				<hdiits:button name="btnClose" id="btnClose" type="button" captionid="DARATEHST.CLOSE" bundle="${PensionLabels}" onclick="winCls();"/>
			</div>
			<div>&nbsp;</div>
</fieldset>
	
</hdiits:form>

<script>
document.getElementById("configTI").style.display = "none";
//showTiRateType(document.getElementById("configTI"));
//updateTIVar(document.getElementById("configTI"));
//showMinAmt(document.getElementById("configTI"));
</script>

<c:if test="${resValue.DARateDetails != null}">
<script>
	document.getElementById("txtHeadcodeDesc").disabled="disabled";
	document.getElementById("txtOldRate").disabled="disabled";
</script>
</c:if>

<c:if test="${resValue.flag	== 'N'}">
<script>
				alert("There is no corresponding record for this combination");
			 	document.getElementById("txtOldRate").removeAttribute('disabled');			 	
			 	document.getElementById("txtOldRate").value = "";
				document.getElementById("txtOldMinAmt").value = "";
				document.getElementById("hidPKTable").value = "";
			 	document.getElementById("cmbStateCode").focus();			 	
</script>
</c:if>