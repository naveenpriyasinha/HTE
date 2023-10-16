<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<c:set var="empList" value="${resValue.empList}"></c:set>

<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page" value="style='display: none;' "></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>


<fieldset class="tabstyle"><legend><fmt:message
		key="CMN.PFDETAILS" bundle="${dcpsLables}"></fmt:message> </legend>
		<table width="100%" align="center" cellpadding="4" cellspacing="4">
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.ACMAINTENEDBY" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<select name="cmbAcMaintainedBy"
						id="cmbAcMaintainedBy" style="width: 60%" onChange="" ${varDisabled} >
						<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="acMntndByVar" items="${resValue.lLstPFAccntMntdBy}" >
																<c:choose>
																	<c:when test="${EMPPAYROLLVO.acMaintainedBy == acMntndByVar.lookupId}">
																	<option value="${acMntndByVar.lookupId}" selected="selected"><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${acMntndByVar.lookupId}" ><c:out 
																			value="${acMntndByVar.lookupDesc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
						</c:forEach>
					</select> <label class="mandatoryindicator" ${varLabelDisabled} >*</label>
					</td>
					
			</tr>
			
			<tr>
					<td width="15%" align="left"><fmt:message
						key="CMN.PFSERIES" bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left">
					<select name="cmbPFSeries"
						id="cmbPFSeries" style="width: 60%" onChange="getPFDesc();" ${varDisabled} > 
						<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="pfSeriesVar" items="${resValue.lLstPFSeries}" >
																<c:choose>
																	<c:when test="${EMPPAYROLLVO.pfSeries == pfSeriesVar.lookupId}">
																	<option value="${pfSeriesVar.lookupId}" selected="selected"><c:out 
																			value="${pfSeriesVar.lookupDesc}"></c:out></option>
																	</c:when>
																	<c:otherwise>
																	<option value="${pfSeriesVar.lookupId}" ><c:out 
																			value="${pfSeriesVar.lookupDesc}"></c:out></option>
																	</c:otherwise>
																</c:choose>
						</c:forEach>
					</select> <label class="mandatoryindicator" ${varLabelDisabled} >*</label>
					</td>
					<td width="15%" align="left"><fmt:message key="CMN.PFSERIESDESC"
				bundle="${dcpsLables}"></fmt:message></td>
					<td width="20%" align="left"><input type="text" id="txtPfSeriesDesc"
				size="30" name="txtPfSeriesDesc" value="${EMPPAYROLLVO.pfSeriesDesc}" ${varDisabled} readOnly="readOnly" /></td>
					
			</tr>
			
			<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PFACNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtPfAccountNo" size="28" name="txtPfAccountNo"
				value="${EMPPAYROLLVO.pfAcNo}" ${varDisabled} /> <label class="mandatoryindicator" ${varLabelDisabled} >*</label></td>
			</tr>
		</table>
</fieldset>