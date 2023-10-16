<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="UserRights" value="${resValue.lStrUseZP}"></c:set>
<c:set var="TypeofUser" value="${resValue.lStrUserZP}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>

<hdiits:form name="DCPSDraftFormsList" id="DCPSDraftFormsList" encType="multipart/form-data"
	validate="true" method="post">
	<div align="center">
	
	<input type="hidden" id="User" name="User" value="${resValue.User}"/>
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}"/>
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AllEmployeeDetails" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
	<font size="2"> <fmt:message
		key="CMN.SEARCH" bundle="${dcpsLables}"></fmt:message> </font>
		
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<select name="cmbSearchBy" id="cmbSearchBy" style="width: 150px" onchange="ChangeNewFormSearchCriteria();" >
		<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
		<c:choose>
				<c:when test="${resValue.SearchCriteria=='Designation'}">
					<option value="Designation" selected="selected" ><c:out value="Designation"></c:out></option>
				</c:when>
				<c:otherwise>
					<option value="Designation"><c:out value="Designation"></c:out></option>
				</c:otherwise>
		</c:choose>
		<c:choose>
				<c:when test="${resValue.SearchCriteria=='Case Status'}">
					<option value="Case Status" selected="selected" ><c:out value="Case Status"></c:out></option>
				</c:when>
				<c:otherwise>
					<option value="Case Status"><c:out value="Case Status"></c:out></option>
				</c:otherwise>
		</c:choose>
	</select>
	
	<c:choose>
		<c:when test="${resValue.DesignationId != ''}">
			<c:set var="displayDesig" scope="page"  value="style='width: 250px;display: inline'"/>
			<c:set var="displayCaseStatus" scope="page"  value="style='width: 150px;display: none'"/>
		</c:when>
		<c:when test="${resValue.CaseStatus != ''}">
			<c:set var="displayDesig" scope="page"  value="style='width: 250px;display: none'"/>
			<c:set var="displayCaseStatus" scope="page"  value="style='width: 150px;display: inline'"/>
		</c:when>
		<c:otherwise>
			<c:set var="displayDesig" scope="page"  value="style='display: none';"/>
			<c:set var="displayCaseStatus" scope="page"  value="style='display: none';"/>	
		</c:otherwise>
	</c:choose>
	
	<select name="cmbCaseStatus" id="cmbCaseStatus" ${displayCaseStatus}>
		<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
		<c:choose>
			<c:when test="${resValue.CaseStatus == 'Draft'}">
				<option value="Draft" selected="selected"><c:out value="Draft"></c:out></option>
			</c:when>
			<c:otherwise>
				<option value="Draft"><c:out value="Draft"></c:out></option>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${resValue.CaseStatus == 'Rejected'}">
				<option value="Rejected" selected="selected"><c:out value="Rejected"></c:out></option>
			</c:when>
			<c:otherwise>
				<option value="Rejected"><c:out value="Rejected"></c:out></option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<select name="cmbDesignation" id="cmbDesignation" ${displayDesig}>
		<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
		<c:forEach var="designationVar" items="${resValue.lLstDesignation}">
							<c:choose>
								<c:when test="${resValue.DesignationId == designationVar.id}">
											<option value="${designationVar.id}" selected="selected"><c:out value="${designationVar.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
											<option value="${designationVar.id}"><c:out value="${designationVar.desc}"></c:out></option>	
								</c:otherwise>
							</c:choose>
		</c:forEach>
	</select>
	
	<hdiits:button name="btnSearch" type="button" captionid="BTN.SEARCH" bundle="${dcpsLables}" onclick="searchEmployeeForm();" />
	<hdiits:button name="btnAll" type="button" captionid="BTN.DISPLAYALL" bundle="${dcpsLables}" onclick="displayAllForms();" />
	
	<br/>
	<br/>
	
	<c:set var="hdnCounter" value="0"/>
	<div style="height: 250px ; overflow: scroll">
	<display:table
		list="${empList}" id="vo" requestURI="" export="" style="width:60%"
		pagesize="500">

		<display:setProperty name="paging.banner.placement" value="bottom" />

		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}"
			var="birthDate" />
			
		<display:column titleKey="CMN.CHKBXEMPSELECT"
			title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="text-align:center;"
			class="oddcentre" sortable="true">
			<input type="checkbox" align="left" name="checkbox"
				id="checkbox${vo_rowNum}" value="${vo[0]}" />
		</display:column>

		<display:column headerClass="datatableheader" style="text-align:left" sortable="true" titleKey="CMN.EMPLOYEENAME">
			<a href=#
				onclick="popUpDcpsEmpData('${vo[0]}','${resValue.EditForm}','${vo[3]}');"><c:out
				value="${vo[1]}" /></a>
			<input type="hidden" id="hidDcpsEmpIdDraft${vo_rowNum}" value="${vo[0]}"/>
			
		<c:set var="hdnCounter" value="${hdnCounter+1 }"/>
		</display:column>

		<display:column headerClass="datatableheader"
			style="text-align:left" sortable="true"
			titleKey="CMN.DOB">
			<c:out value="${birthDate}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" style="text-align:left" sortable="true" titleKey="CMN.StatusFlag">

					<c:choose>
						<c:when test="${vo[3] == 0}">
							<c:out value="Draft"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="Rejected"></c:out>
						</c:otherwise>
					</c:choose>
					
					<input type="hidden" id="hidDraftOrRejected${vo_rowNum}" value="${vo[3]}" />
		</display:column>

		<display:column headerClass="datatableheader" style="text-align:left" sortable="true" titleKey="CMN.REMARKS">
					<c:out value="${vo[4]}"></c:out>
		</display:column>
		
	</display:table>
	</div>
	
	<input type="hidden" id="hdnCounter" name="hdnCounter" value="${ hdnCounter}"/>
	
	<div align="center">
		<hdiits:button	name="BTN.VIEWUPDATEDRAFT" id="btnViewUpdateDraft" type="button" style="width:10%"
											captionid="BTN.VIEWUPDATEDRAFT" bundle="${dcpsLables}"
											onclick="viewUpdateDraft();" />
		<hdiits:button	name="BTN.DELETEDRAFT" id="btnDeleteDraft" type="button"
											captionid="BTN.DELETEDRAFT" bundle="${dcpsLables}"
											onclick="deleteDraft();" />
	</div>
	
	</fieldset>
	</div>
	
</hdiits:form>