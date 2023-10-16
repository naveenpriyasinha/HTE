<%@ page language="java" isErrorPage="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script language="JavaScript" src="script/dcps/offlineEntry.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="UserList" value="${resValue.UserList}" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>

<hdiits:form name="DCPSOfflineEntryForm" id="DCPSOfflineEntryForm"
	encType="multipart/form-data" validate="true" method="post">

	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.INPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b>
	</legend>

	<table align="center" width="100%">
		<tr>
			<td width="20%"><fmt:message key="CMN.TreasuryName"
				bundle="${dcpsLables}"></fmt:message> </td>
			<td width="30%"><select name="cmbTreasuryCode"
				id="cmbTreasuryCode"  onChange="" disabled="disabled">
				
				<c:forEach var="treasuries" items="${resValue.TREASURIES}">

					<option value="${treasuries.id}"><c:out
						value="${treasuries.desc}"></c:out></option>
				</c:forEach>

			</select></td>
			<td width="20%"><fmt:message key="CMN.DDOName"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="30%"><select name="cmbDDOCode" id="cmbDDOCode"
				style="width: 240px" onChange="" disabled="disabled">
				
				<c:forEach var="ddoName" items="${resValue.DDONAMES}">
					
						
							<option value="${ddoName.id}" selected="selected"><c:out
								value="${ddoName.desc}"></c:out></option>
						
				</c:forEach>
			</select></td>
		</tr>

		<tr>
			<td width="20%"><fmt:message key="CMN.BillGroup"
				bundle="${dcpsLables}"></fmt:message> <label
				class="mandatoryindicator">*</label></td>
			<td width="30%"><select name="cmbBillGroup" id="cmbBillGroup"
				style="width: 240px" onChange="getSchemeforBillGroup();">
				
				<c:forEach var="billGroup" items="${resValue.BillGroupList}">
					<c:choose>
						<c:when test="${resValue.lLongbillGroupId == billGroup.id}">
							<option value="${billGroup.id}" selected="selected"><c:out
								value="${billGroup.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${billGroup.id}"><c:out
								value="${billGroup.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>

			<td width="20%"><fmt:message key="CMN.Scheme"
				bundle="${dcpsLables}"></fmt:message> <label
				class="mandatoryindicator">*</label></td>
			<td width="30%"><input type="text" name="txtSchemeName"
				id="txtSchemeName" value="${resValue.schemename}" size="31" readonly="readonly"/> <input
				type="hidden" name="schemeCode" id="schemeCode"
				value="${resValue.schemeCode}" /></td>
		</tr>

		<tr>
			<td width="20%"><fmt:message key="CMN.Month"
				bundle="${dcpsLables}"></fmt:message> <label
				class="mandatoryindicator">*</label></td>

			<td width="30%"><select name="cmbMonth" id="cmbMonth"
				style="width: 240px" onChange="">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="month" items="${resValue.MONTHS}">
					<c:choose>
						<c:when test="${resValue.monthId == month.id}">
							<option value="${month.id}" selected="selected"><c:out
								value="${month.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${month.id}"><c:out value="${month.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
			<td width="20%"><fmt:message key="CMN.Year"
				bundle="${dcpsLables}"></fmt:message> <label
				class="mandatoryindicator">*</label></td>

			<td width="30%"><select name="cmbYear" id="cmbYear"
				style="width: 240px" onChange="">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="year" items="${resValue.YEARS}">
					<c:choose>
						<c:when test="${resValue.yearId == year.id}">
							<option value="${year.id}" selected="selected"><c:out
								value="${year.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${year.id}"><c:out value="${year.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		</tr>
	</table>
	<input type = "hidden" id = "hidBGIdForContriThruChallan" name = "hidBGIdForContriThruChallan" value = "0" />
	</fieldset>
	<table align="center">
		<tr>
			<td align="center"></td>
		</tr>
		<tr>
			<td id="go" align="center" style="display: block;"><hdiits:button
				name="btnGo" id="btnGo" type="button" captionid="BTN.GENERATE"
				style="width:100%" bundle="${dcpsLables}"
				onclick="getEmpListforContriDDO()" /> <input type="hidden"
				id="User" value="${resValue.lStrUser}" /> <input type="hidden"
				id="Use" value="${resValue.lStrUse}" /></td>
		</tr>
	</table>




</hdiits:form>
