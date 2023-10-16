<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empList" value="${resValue.empList}" />
<c:set var="Type" value="${resValue.Type}" />
<c:if test="${Type == 'Office'}">
	<c:set var="varRowDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>
<fieldset class="tabstyle"><legend><b> <fmt:message key="CMN.DETAILS" bundle="${DCPSLables}"/></b></legend>
<table width="60%" cellpadding="4" cellspacing="4" class="changeTb">
	<tr ${varRowDisabled }>
		<td width="25%"><fmt:message key="CMN.CHANGES" bundle="${DCPSLables}"/></td>
		<td width="25%"><select name="cmbChanges" id="cmbChanges">
		<c:choose>
			<c:when test="${Type=='Office' }">
				<option value = "700041"><fmt:message key="CMN.OFFICEDETAILS" bundle="${DCPSLables}"/></option>
			</c:when>
			<c:otherwise>
			<c:forEach var="ChangesList" items="${resValue.CHANGESLIST}">
				<c:choose>
					<c:when test="${resValue.Changes!=null}">
						<c:choose>
							<c:when test="${ChangesList.lookupId==resValue.Changes}">
								<c:if test="${ChangesList.lookupDesc != 'Office Details'}">
									<option value="${ChangesList.lookupId}" selected="selected">${ChangesList.lookupDesc}</option>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${ChangesList.lookupDesc != 'Office Details'}">
									<option value="${ChangesList.lookupId}">${ChangesList.lookupDesc}</option>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<c:if test="${ChangesList.lookupDesc != 'Office Details'}">
							<option value="${ChangesList.lookupId}">${ChangesList.lookupDesc}</option>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</c:otherwise></c:choose>
		</select></td>
	</tr>

	<tr>
		<td width="25%"><fmt:message key="CMN.DESIGNATION" bundle="${DCPSLables}"/></td>
		<td>
		<input type="hidden" id="User" name="User" value="${resValue.UserType}" >
		<select name="cmbDesignation" id="cmbDesignation"
			onchange="">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="Designation" items="${resValue.lLstDesignation}">
				<c:choose>
					<c:when test="${resValue.DesignationId!=null}">
						<c:choose>
							<c:when test="${Designation.id==resValue.DesignationId}">
								<option value="${Designation.id}" selected="selected">${Designation.desc}</option>
							</c:when>
							<c:otherwise>
								<option value="${Designation.id}">${Designation.desc}</option>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<option value="${Designation.id}">${Designation.desc}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></td>
	</tr>
</table>

<div align="center">
<hdiits:button name="btnGo" id="btnGo" type="button"  captionid="BTN.GO" bundle="${DCPSLables}" onclick="getEmployees();"/>&nbsp;&nbsp;
<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="BTN.BACK" bundle="${DCPSLables}" onclick="ReturnLoginPage();"/>
</div>
<br/>
</fieldset>
<br />
<c:if test="${empList!=null}">
<fieldset class="tabstyle" id="fsDisplayEmp"><legend><b><fmt:message key="CMN.SELECTEMPLOYEE" bundle="${DCPSLables}"/></b></legend> 
<br/>
<display:table list="${empList}" id="vo" requestURI=""
	export="" style="width:20%" pagesize="10">

	<display:setProperty name="paging.banner.placement" value="bottom" />

	<display:column headerClass="datatableheader" class="oddcentre"
		style="text-align:center" sortable="true" titleKey="CMN.NAMEOFEMPLOYEE">
		<c:choose>
			<c:when test="${Type=='Office' }">
				<a href=# onclick="openOfficeDetailsPage('${vo[0]}','${vo[1]}','${vo[3]}');"><c:out
					value="${vo[1]}"/></a>
			
			</c:when>
		<c:otherwise>
		<a href=# onclick="openChangesPage('${vo[0]}');"><c:out
			value="${vo[1]}"/></a>
		</c:otherwise></c:choose>
	</display:column>
</display:table>
<br/>
</fieldset>
</c:if>

