<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js">
	
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/srkaMasters.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/dcpsDDO.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.SELECTEMPLIST}"></c:set>
<c:set var="DDOCODE" value="${resValue.DDOCODE}"></c:set>

<hdiits:form name="DDOEmpSelect" id="DDOEmpSelect"
	encType="multipart/form-data" validate="true" method="post">
	<input type="hidden" name="ddoCode" id="ddoCode" value="${DDOCODE}">
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.DDOEMPSELECTSEARCH" bundle="${dcpsLabels}"></fmt:message></b> </legend>
	<table>
			<tr align="center">
				<td width="25%" align="left" ><fmt:message key="CMN.SEVARTHID" bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left">
					<input type="text" id="txtSevaarthId" style="text-transform: uppercase" size="30" name="txtSevaarthId"/>
				</td>
			</tr>
			
			<tr align="center">
				<td width="25%" align="left"><fmt:message key="CMN.EMPNAME" bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left">
					<input type="text" id="txtEmployeeName" size="30" style="text-transform: uppercase"name="txtEmployeeName" />
					<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message
					key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
			</tr>
	</table>
	<div style="width: 50; text-align: center; align: center">
		<hdiits:button name="btnSearch" style="align:center" type="button" captionid="CMN.SEARCH" bundle="${dcpsLabels}" onclick="showEmpSelectionList();" /> 
		<hdiits:button name="btnDisplayAll" style="align:center;display:none" type="button" captionid="BTN.DISPLAYALL" bundle="${dcpsLabels}" onclick="showAllForSelection();" />
	</div>
	
	</fieldset>
	<br></br>
	
	<c:if test="${VOList != null}" >

		<fieldset style="width: 100%" class="tabstyle">
		<legend id="headingMsg"> <b><fmt:message key="CMN.DDOEMPSELECT" bundle="${dcpsLabels}"></fmt:message></b> </legend> 
		<input type="hidden" name="hdnCounter" id="hdnCounter" value="0" />
			
		<div class="scrollablediv"  style="width: 100%; overflow: auto; height: 200px ;">
		<display:table list="${VOList}" id="vo"  cellpadding="5" style="width:180%" requestURI="">
	
			<display:column titleKey="CMN.CHKBXEMPSELECT" title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader" style="text-align:center;" class="oddcentre" sortable="true">
				<input type="checkbox" align="left" name="checkbox" id="checkbox${vo_rowNum}" value="${vo[0]}" />
			</display:column>
	
			<display:column titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader" sortable="true" style="width:15%;text-align: left;">
							${vo[1]}
							<input type="hidden" name="hidName" id="hidName${vo_rowNum}" value="${vo[1]}" />
			</display:column>
					    
			<display:column headerClass="datatableheader" style="text-align:left;width: 20%" class="oddcentre" sortable="true"  titleKey="CMN.OFFICERELIEVEDFROM" >		
								<c:out value="${vo[5]}"></c:out> 
			</display:column>
	
			<display:column style="height:35;text-align: left;width:10%" class="tablecelltext" titleKey="CMN.DESIGNATIONWOCOLON" headerClass="datatableheader" sortable="true">
							${vo[2]}
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width: 8%" sortable="true"  titleKey="CMN.ORDERNO" >
								<c:out value="${vo[6]}"></c:out>
			</display:column>
							
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:left;width: 8%" sortable="true"  titleKey="CMN.ORDERDATE" >
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[7]}" var="orderDate"/>
								<c:out value="${orderDate}"></c:out>
			</display:column>
	
			<display:column style="height:35;text-align: center;width:10%" class="tablecelltext" titleKey="CMN.DATEOFSELECTION" headerClass="datatableheader" sortable="true">
				<input type="text" name="txtDateOfSelection${vo_rowNum}" id="txtDateOfSelection${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"	onblur="validateDate(this);chkDtLaterThanDeSelectionDt(this,'${vo_rowNum}');" size="10%" />
				<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtDateOfSelection${vo_rowNum}",375,570)' style="cursor: pointer;" ${disabled}/>
				<label class="mandatoryindicator"${varLabelDisabled}>*</label>
				<script>
				document.getElementById("hdnCounter").value = Number(document
				.getElementById("hdnCounter").value) + 1;
				</script>
			</display:column>
	
	
			<display:column style="height:35;text-align: left;width:15%" class="tablecelltext" titleKey="CMN.OFFICE" headerClass="datatableheader" sortable="true">
				<select name="cmbOffice" id="cmbOffice${vo_rowNum}" style="width: 90%" onChange="getVacantPostsInOffice('${vo_rowNum}');">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					<c:if test="${resValue.OFFICESLIST == null}">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					</c:if>
					<c:forEach var="office" items="${resValue.OFFICESLIST}">
								<option value="${office[0]}" title="${office[1]}" ><c:out value="${office[1]}"></c:out></option>
					</c:forEach>
				</select>
			<label class="mandatoryindicator"${varLabelDisabled}>*</label>
			</display:column>
	
			<display:column style="height:35;text-align: left;width:15%" class="tablecelltext" titleKey="CMN.POST" headerClass="datatableheader" sortable="true">
				<select name="cmbPost" id="cmbPost${vo_rowNum}" style="width: 90%" onChange="getBGAndGRForPost('${vo_rowNum}');">
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				</select>
				<label class="mandatoryindicator"${varLabelDisabled}>*</label>
			</display:column>
			
			<display:column style="height:35;text-align: left;width:20%;" class="tablecelltext" titleKey="CMN.BILLGROUP" headerClass="datatableheader" sortable="true">
				
				<input type="text" name="txtBillGroup" id="txtBillGroup${vo_rowNum}" readOnly=readOnly />
				<input type="hidden" name="hidbillGroupId" id="hidbillGroupId${vo_rowNum}" />
				
				<label class="mandatoryindicator" id="labelBillGroup" style="display:none" ></label>
				
				<select name="cmbBillGroup" id="cmbBillGroup${vo_rowNum}" style="width: 250px;display:none;"
					onChange=""  >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" />
						<c:forEach var="BillGroupList" items="${resValue.BillGroupList}" >
									<option value="${BillGroupList.id}" ><c:out value="${BillGroupList.desc}"></c:out></option>
						</c:forEach>
					</option>
				</select>
			</display:column>
			
			<display:column style="height:35;text-align: left;width:10%" class="tablecelltext" titleKey="CMN.GRORDER" headerClass="datatableheader" sortable="true">
				<input type="text" name="txtGROrder" id="txtGROrder${vo_rowNum}" value="" readOnly=readOnly />
				<input type="hidden" name="hidGROrderId" id="hidGROrderId" />
			</display:column>
	
			<display:setProperty name="paging.banner.placement" value="bottom" />
	
		</display:table>
		</div>
	
		</fieldset>
	
	<br>

	<div style="width: 50; text-align: center; align: center">
		<hdiits:button name="btnSelect" type="button" classcss="bigbutton" id="btnDDOEmpSelect" captionid="CMN.DDOEMPSELECTBTN" bundle="${dcpsLabels}" onclick="dcpsDDOEmpSelect();" /></div>
	
	</c:if>

	<input type="hidden" name="hidSearchFromDDOSelection" id="hidSearchFromDDOSelection" value="searchFromDDOSelection" />

</hdiits:form>

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDOSelection}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
