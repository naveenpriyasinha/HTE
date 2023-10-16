<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src ="script/dcps/dcpsDDO.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsSrka.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DesigList" value="${resValue.DesigDsplyDtls}"></c:set>

<hdiits:form name="dcpsDesigInfo" encType="multipart/form-data"
	validate="true" method="post">
	
<fieldset style="width:100%" class="tabstyle">
<legend><fmt:message key="CMN.DESIGNATIONMST" bundle="${dcpsLabels}"></fmt:message></legend>
<table id="tbl1" width="100%" align="center">
<tr>
	<td width="100%" valign="top">
		<table cellpadding = "4" cellspacing = "4">
		<tr>
		<td width="20%" align="left" ><fmt:message key="CMN.FIELDDEPARTMENT" bundle="${dcpsLabels}"></fmt:message></td>
		<td width="50%" align="left">
			 <select name="cmbFieldDepartment" id="cmbFieldDepartment" onchange="getDesigsForTheFieldDept();">				
			 					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
			 						<c:forEach var="FieldDeptList" items="${resValue.FieldDeptList}">		
			 							<c:choose>
			 								<c:when test="${FieldDeptList.id == resValue.CurrFieldDeptId}">			
												<option value="${FieldDeptList.id}" selected="selected">
													<c:out value="${FieldDeptList.desc}"></c:out>
												</option>
											</c:when>
											<c:otherwise>
												<option value="${FieldDeptList.id}">
													<c:out value="${FieldDeptList.desc}"></c:out>
												</option>
											</c:otherwise>
										</c:choose>
			 						</c:forEach>
		   	</select>
	 		<label class="mandatoryindicator">*</label>
		</td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DESIGNATIONCODE" bundle="${dcpsLabels}"></fmt:message></td>
			<td width="50%" align="left"><input type="text"  size="20%" name='txtDesigCode' id="txtDesigCode" value="${resValue.designationCode}" style="text-align: left" onKeyPress="" />
			<label class="mandatoryindicator">*</label>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLabels}"></fmt:message></td>
			<td width="50%" align="left"><input type="text"  size="50%" name='txtDesig' id="txtDesig" style="text-align: left" onKeyPress=""/>
			<label class="mandatoryindicator">*</label>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.DESIGNATIONSHORT" bundle="${dcpsLabels}"></fmt:message></td>
			<td width="50%" align="left"><input type="text"  size="50%" name='txtDesigShort' id="txtDesigShort" style="text-align: left" onKeyPress=""/>
			<label class="mandatoryindicator">*</label>
			</td>
		</tr>
		<tr>
			<td width="20%" align="left" valign="top"><fmt:message key="CMN.CADRE" bundle="${dcpsLabels}"></fmt:message></td>
			<td width="50%" align="left">
				<select name="cmbCadre" id="cmbCadre" style="width:50%" onfocus="onFocus(this)" onblur="onBlur(this);">
					<c:forEach var="cadre" items="${resValue.CadreList}">					
							<option value="${cadre.id}"><c:out value="${cadre.desc}"></c:out></option>
				 	</c:forEach>
				</select>
				<label class="mandatoryindicator">*</label>
			</td>
		</tr>
			<tr>
			
			<td width="15%" align="left"><fmt:message key="CMN.Qualification"
				bundle="${dcpsLabels}"></fmt:message></td>
			<td width="20%" align="left"><select name="qualification" id="qualification" ${varDisabled}>
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="qualification" items="${resValue.QualificationList}">
							<option value="${qualification}">${qualification}</option>
		
	
			</c:forEach>
			</select>
			<input type="hidden" value="" name="txtqualification" id="txtqualification"/>
			</td>
			</tr>
			
			<tr>
			<hdiits:button type="button" style="align:center" captionid="CMN.ADDMOREQUALIFICATION" bundle="${dcpsLabels}" id="btnaddqualification" name="btnaddqualification"  value="1" onclick="fieldDisplay();"/>
			</tr>
			
			<tr id="moreQualification" style="display:none">
			
			<td width="15%" align="left"><fmt:message key="CMN.ADDMOREQUALIFICATION"
				bundle="${dcpsLabels}"></fmt:message></td>
			<td width="20%" align="left"><select name="moreQualification" id="moreQualification" ${varDisabled} multiple="multiple" onchange="onchangevalues();">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="qualification" items="${resValue.QualificationList}">
							<option value="${qualification}">${qualification}</option>
		
	
			</c:forEach>
			</select>
			<input type="hidden" value="" name="txtqualification" id="txtqualification"/>
			</td>
			</tr>
			<input type="hidden" id="txtmoreQualification" name="txtmoreQualification" value=""/>
		<tr>
			<td width="20%" align="left"><fmt:message key="CMN.PAYCOMMISSION" bundle="${dcpsLabels}"></fmt:message></td>
			<td width="50%" align="left">
				<select name="cmbPayCommission" id="cmbPayCommission">			
				 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				 		<c:forEach var="PayCommissionDCPS" items="${resValue.PayCommissionDCPS}">					
							<option value="${PayCommissionDCPS.lookupId}"><c:out value="${PayCommissionDCPS.lookupDesc}"></c:out></option>
				 		</c:forEach>
			   	</select>
			   	<label class="mandatoryindicator">*</label>
			</td>
		</tr>
		</table>
	</td>
	</tr>
</table><br/>
</fieldset><br/><br/>
<div style="width:50;text-align: center;align:center">
	<hdiits:button type="button" style="align:center" captionid="CMN.SAVE" bundle="${dcpsLabels}" id="btnSave" name="btnSave"  onclick="saveDesigInfo();"/>
</div>

<fieldset class="tabstyle"><legend>Designations Added</legend>
<br/>
<table width="98%" id="tblDesigDtls">
	<tr>
	<td>
	<c:if test="${fn:length(DesigList)>0}">
		<display:table list="${DesigList}" size="15" id="vo" pagesize="15" cellpadding="5" style="width:60%" requestURI="">	
		<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:column style="text-align: left;" 
				titleKey="CMN.CODE" headerClass="datatableheader"
				sortable="true" value="${vo[1]}" >						
			    </display:column>
			    <display:column style="text-align: left;" 
				titleKey="CMN.FIELDDEPT" headerClass="datatableheader"
				sortable="true" value="${vo[5]}" >						
			    </display:column>
				
				<display:column style="text-align: left;"
				titleKey="CMN.DESIGNATION" headerClass="datatableheader"
				sortable="true" value="${vo[2]}">		
			    </display:column>
			    
			    <display:column style="text-align: left;" 
				titleKey="CMN.GROUP" headerClass="datatableheader"
				sortable="true" value="${vo[3]}">				
			    </display:column>
			    
			    <display:column style="text-align: left;" 
				titleKey="CMN.PAYCOMMISSION" headerClass="datatableheader"
				sortable="true" value="${vo[4]}">				
			    </display:column>
			    
			    <display:column style="text-align: left;" 
				titleKey="CMN.PAYCOMMISSION" headerClass="datatableheader"
				sortable="true" value="${vo[6]}">				
			    </display:column>
			    
			    			</display:table>
		</c:if>
		<c:if test="${fn:length(DesigList)==0}">
			<table id="vo" style="width:60%" cellpadding="4" class="datatable">
				<thead>
				<tr>
				<th class="datatableheader">Code</th>
				<th class="datatableheader">Field Department</th>
				<th class="datatableheader">Designation</th>
				<th class="datatableheader">Group</th>
				<th class="datatableheader">Pay Commission</th></tr></thead>
				<tbody>
				<tr>
				<td colspan = "6">No Records Found to Display</td>
				</tr>
				</tbody>
			</table>
		
		</c:if>
		
		</td>
	</tr>
</table>

<br/>
	</fieldset>
</hdiits:form>

