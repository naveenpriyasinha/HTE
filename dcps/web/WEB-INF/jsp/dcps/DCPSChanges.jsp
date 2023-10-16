<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empList" value="${resValue.empList}" />
<fieldset class="tabstyle"><legend><b> <fmt:message key="CMN.DETAILS" bundle="${DCPSLables}"/></b></legend>Kapil
<table width="60%" cellpadding="4" cellspacing="4" class="DCPSChangetb">
	<tr>
		<td width="25%"><fmt:message key="CMN.CHANGES" bundle="${DCPSLables}"/></td>
		<td><select name="cmbChanges" id="cmbChanges">
			<c:forEach var="ChangesList" items="${resValue.CHANGESLIST}">
				<c:choose>
					<c:when test="${resValue.Changes!=null}">
						<c:choose>
							<c:when test="${ChangesList.lookupId==resValue.Changes}">
								<option value="${ChangesList.lookupId}" selected="selected">${ChangesList.lookupDesc}</option>
							</c:when>
							<c:otherwise>
								<option value="${ChangesList.lookupId}">${Designation.lookupDesc}</option>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<option value="${ChangesList.lookupId}">${ChangesList.lookupDesc}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></td>
	</tr>

	<tr>
		<td width="25%"><fmt:message key="CMN.DESIGNATION" bundle="${DCPSLables}"/></td>
		<td><select name="cmbDesignation" id="cmbDesignation"
			onchange="getEmployees();">
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
</fieldset>
<br />
<fieldset class="tabstyle" id="fsDisplayEmp"><legend><b><fmt:message key="CMN.SELECTEMPLOYEE" bundle="${DCPSLables}"/></b></legend> 
<br/>
<display:table list="${empList}" id="vo" requestURI=""
	export="" style="width:20%" pagesize="5">

	<display:setProperty name="paging.banner.placement" value="bottom" />

	<display:column headerClass="datatableheader" class="oddcentre"
		style="text-align:center" sortable="true" titleKey="CMN.NAMEOFEMPLOYEE">
		<a href=# onclick="openChangesPage('${vo[0]}');"><c:out
			value="${vo[1]}"/></a>
	</display:column>





</display:table>
<br/>
</fieldset>

