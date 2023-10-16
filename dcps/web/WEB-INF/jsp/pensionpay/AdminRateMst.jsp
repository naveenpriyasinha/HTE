<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.pensionpay.PensionConstant"
	var="pensionConst" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionLabels"
	var="PensionLabels" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionAlerts"
	var="PensionAlerts" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionToolTip"
	var="pensionToolTip" scope="request" />

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

<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/pensionpay/Common.js"> </script>
<script type="text/javascript" src="script/pensionpay/PensionCase.js"> </script>
<script type="text/javascript"
	src="<c:url value="script/common/commonfunctions.js"/>"></script>
<script type="text/javascript" src="script/pensionpay/AdminRateMst.js"></script>
<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/behaviour.js"/>"></script>
<script type="text/javascript"
	src="script/pensionpay/behaviourAdminRateMst.js"></script>

</head>

<hdiits:form name="AdminScreen" validate="false" method="post">
	<hdiits:hidden name="hidTablePK" id="hidTablePK"
		default="${resValue.tablePK}" />
	<hdiits:hidden name="hidTableNewPK" id="hidTableNewPK"
		default="${resValue.lngNewPK}" />
	<table align="center" class="HLabel" width="90%">

		<tr>
			<td><script> cmbmontypeOption1 += '<option value="-1"> --- SELECT --- </option>'; </script>
			<c:forEach var="SgvaMonthMstVO"
				items="${resValue.SgvaMonthMstVOArray}">
				<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth}">
					<script> cmbmontypeOption1 += '<option value="${SgvaMonthMstVO.monthNo}" selected="selected"> ${SgvaMonthMstVO.monthName} </option>'; </script>
				</c:if>
				<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth}">
					<script> cmbmontypeOption1 += '<option value="${SgvaMonthMstVO.monthNo}"> ${SgvaMonthMstVO.monthName} </option>'; </script>
				</c:if>
			</c:forEach> <script> cmbyeartypeOption1 += '<option value="-1"> --- SELECT --- </option>'; </script>
			<c:forEach var="SgvcFinYearMstVO"
				items="${resValue.SgvcFinYearMstVOArray}">
				<c:if
					test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
					<script> cmbyeartypeOption1 += '<option value="${SgvcFinYearMstVO.finYearCode}" selected="selected"> ${SgvcFinYearMstVO.finYearCode} </option>'; </script>
				</c:if>
				<c:if
					test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
					<script> cmbyeartypeOption1 += '<option value="${SgvcFinYearMstVO.finYearCode}"> ${SgvcFinYearMstVO.finYearCode} </option>'; </script>
				</c:if>
			</c:forEach>

			<fieldset style="width: 100%;" class="tabstyle"><legend
				id="headingMsg" class="Label"> <b> <fmt:message
				key="ADM.MSTSCREEN" bundle="${PensionLabels}"></fmt:message> </b> </legend> <br>
			<br>
			<table width="100%">
				<!--
				<tr class="datatableheader">
					<td colspan="4" height="20">
						<b> <fmt:message key="ADM.MSTSCREEN" bundle="${PensionLabels}"></fmt:message> </b>
					</td>
				</tr>
				-->
				<tr>
					<td>

					<td colspan="3">
					<input type="radio" name="radioConfig"
						id="configTI" value="TI" checked="checked"  
						onclick="showTiRateType(this),updateTIVar(this),showMinAmt(this)" />

					<!-- <input type="radio" name="radioConfig"
						id="configMA" value="MA"
						onclick="showTiRateType(this),updateTIVar(this),showMinAmt(this)" />
					MA &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="radioConfig"
						id="configIR" value="IR"
						onclick="showTiRateType(this),updateTIVar(this),showMinAmt(this)" />
					IR &nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="radioConfig"
						id="configDP" value="DP"
						onclick="showTiRateType(this),updateTIVar(this),showMinAmt(this)" />
					DP -->
					</td>
				</tr>

				<tr id="tiRateTypeId">
					<td><fmt:message key="ADM.TIRATETYPE"
						bundle="${PensionLabels}"></fmt:message></td>
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
						onchange="showMinAmt(this),getDataFromPension()">
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
					<td><!--  <select name="cmbHeadCode" id="cmbHeadCode"
						onchange="getDataFromHeadcode()">  -->
						<select name="cmbStateCode" id="cmbStateCode" onchange="getDataFromStateCode('MST')">
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
					<td><input type="text" id="txtHeadcodeDesc" value="${resValue.HeadCodeDesc}" size="45" />
					</td>
				</tr>

				<tr>
					<td><fmt:message key="ADM.OLDRATE" bundle="${PensionLabels}"></fmt:message>
					</td>
					<td><input type="text" id="txtOldRate" name="txtOldRate"
						onkeypress="amountFormat(this)" value="${resValue.rate}" readonly="true" /></td>
					<td><fmt:message key="ADM.NEWRATE" bundle="${PensionLabels}"></fmt:message>
					<label class="mandatoryindicator">*</label></td>
					<td><input type="text" id="txtNewRate" name="txtNewRate"
						onkeypress="amountFormat(this)" /></td>
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
					<td>Old Effective From Date</td>
					<td><input type="text" name="oldtxtEffectedDate"
						id="oldtxtEffectedDate" value="${resValue.EffFrmDate}" readonly="true" /></td>
					<td><fmt:message key="ADM.EFFFROM" bundle="${PensionLabels}"></fmt:message>
					<label class="mandatoryindicator">*</label></td>
					<td><input type="text" name="txtEffectedDate"
						id="txtEffectedDate" value="" onKeyup="dateFormat(this);"
						onblur="validateDate(this),setDefaultMonth(this)" /> <img
						src='images/CalendarImages/ico-calendar.gif' width=20
						onclick='window_open("txtEffectedDate",375,300)' /> <input
						type="hidden" name="hidPKTable" id="hidPKTable" value="${resValue.tablePK}"/> <input
						type="hidden" name="hidOldEffecDate" id="hidOldEffecDate" /></td>
				</tr>
				<tr>
					<td>GR No</td>
					<td><input type="text" name="txtGrNo"
						id="txtGrNo" maxlength="20" /></td>
					<td>GR Date</td>
					<td><input type="text" name="txtGrDt"
						id="txtGrDt" value="" onKeyup="dateFormat(this);"/> <img
						src='images/CalendarImages/ico-calendar.gif' width=20
						onclick='window_open("txtGrDt",375,300)' /></td>
				</tr>

				<tr>
					<td colspan="4" align="center">
					<table id="headerTable" border="1" >
						<tr>
							<td colspan="7"><hdiits:button type="button" value="Add Row"
								name="AddRowBtn" id="sr" tabindex="0"
								onclick="addRow('headerTable')" /></td>
						</tr>
						<tr class="datatableheader">
							<td colspan="2"><fmt:message key="ADM.PAYFROM"
								bundle="${PensionLabels}"></fmt:message></td>
							<td colspan="2"><fmt:message key="ADM.PAYTO"
								bundle="${PensionLabels}"></fmt:message></td>
							<td colspan="2"><fmt:message key="ADM.PAYIN"
								bundle="${PensionLabels}"></fmt:message></td>
							
						</tr>

						<tr class="datatableheader">
							<td><fmt:message key="ADM.MONTH" bundle="${PensionLabels}"></fmt:message>
							</td>
							<td><fmt:message key="ADM.YEAR" bundle="${PensionLabels}"></fmt:message>
							</td>
							<td><fmt:message key="ADM.MONTH" bundle="${PensionLabels}"></fmt:message>
							</td>
							<td><fmt:message key="ADM.YEAR" bundle="${PensionLabels}"></fmt:message>
							</td>
							<td><fmt:message key="ADM.MONTH" bundle="${PensionLabels}"></fmt:message>
							</td>
							<td><fmt:message key="ADM.YEAR" bundle="${PensionLabels}"></fmt:message>
							</td>
							
						</tr>

						<tr>
							<td><select name="PayFromMM" id="PayFromMM1"
								onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvaMonthMstVO"
									items="${resValue.SgvaMonthMstVOArray}">
									<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}" selected="selected">
										<c:out value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
									<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}"><c:out
											value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
								</c:forEach>
							</select></td>
							<td><select name="PayFromYYYY" id="PayFromYYYY1"
								onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvcFinYearMstVO"
									items="${resValue.SgvcFinYearMstVOArray}">
									<c:if
										test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"
											selected="selected"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
									<c:if
										test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
								</c:forEach>

							</select></td>
							<td><select name="PayToMM" onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvaMonthMstVO"
									items="${resValue.SgvaMonthMstVOArray}">
									<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}" selected="selected">
										<c:out value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
									<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}"><c:out
											value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
								</c:forEach>
							</select></td>
							<td><select name="PayToYYYY"
								onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvcFinYearMstVO"
									items="${resValue.SgvcFinYearMstVOArray}">
									<c:if
										test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"
											selected="selected"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
									<c:if
										test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
								</c:forEach>
							</select></td>
							<td><select name="PayInMM" onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvaMonthMstVO"
									items="${resValue.SgvaMonthMstVOArray}">
									<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}" selected="selected">
										<c:out value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
									<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth}">
										<option value="${SgvaMonthMstVO.monthNo}"><c:out
											value="${SgvaMonthMstVO.monthName}">
										</c:out></option>
									</c:if>
								</c:forEach>
							</select></td>
							<td><select name="PayInYYYY"
								onchange="checkDateValidation()">
								<option value="-1">--- SELECT ---</option>
								<c:forEach var="SgvcFinYearMstVO"
									items="${resValue.SgvcFinYearMstVOArray}">
									<c:if
										test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"
											selected="selected"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
									<c:if
										test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
										<option value="${SgvcFinYearMstVO.finYearCode}"><c:out
											value="${SgvcFinYearMstVO.finYearCode}"></c:out></option>
									</c:if>
								</c:forEach>
							</select></td>
							
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</fieldset>
			</td>
		</tr>
		<br>
		<br>
		<tr>
			<td align="center"><hdiits:button type="button" value="Save"
				id="btnSave" name="Save" onclick="saveData()" /> <hdiits:button
				type="button" value="Close" name="Close"
				onclick="javascript:pageClose()" /></td>
		</tr>
	</table>

	<b><font style="color: red"> <i>**Note: Please make
	sure that Monthly bill for the month selected in Payment In has not
	been generated.</i> </font>
</hdiits:form>

<script>
document.getElementById("configTI").style.display = "none";
//showTiRateType(document.getElementById("configTI"));
//updateTIVar(document.getElementById("configTI"));
//showMinAmt(document.getElementById("configTI"));
</script>