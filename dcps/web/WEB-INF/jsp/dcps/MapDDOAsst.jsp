<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js">
	
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/MapDDOAsst.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<hdiits:form name="MapDDOAsstForm" id="MapDDOAsstForm"	encType="multipart/form-data" validate="true" method="post">

	<fieldset style="width: 100%" class="tabstyle"><legend
			id="headingMsg"> <b><fmt:message
			key="CMN.SEARCHEMPTOMAPAST" bundle="${dcpsLabels}"></fmt:message></b></legend>
		<table>
				<tr align="center">
					<td width="25%" align="left" ><fmt:message
						key="CMN.SEVARTHID" bundle="${dcpsLabels}" /></td>
					<td width="50%" align="left"><input type="text"
						id="txtSevaarthId" style="text-transform: uppercase" size="30"
						name="txtSevaarthId"/></td>
				</tr>
				<tr align="center">
					<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
						bundle="${dcpsLabels}" /></td>
					<td width="50%" align="left"><input type="text"
						id="txtEmployeeName" size="30" style="text-transform: uppercase"
						name="txtEmployeeName" />
					<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><label style="color: red"><fmt:message
						key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
				</tr>
		</table>
		<div style="width: 50; text-align: center; align: left"><hdiits:button
			name="btnSearch" style="align:center" type="button"
			captionid="CMN.SEARCH" bundle="${dcpsLabels}"
			onclick="showEmpListForMapDDOAsst();" /> <hdiits:button
			name="btnDisplayAll" style="align:center" type="button"
			captionid="BTN.DISPLAYALL" bundle="${dcpsLabels}"
			onclick="showAllForMapDDOAsst();" /></div>
	</fieldset>
	
	<c:if test="${resValue.EmpList != null}">
	
		<fieldset style="width: 100%" class="tabstyle">
		
		<legend	id="headingMsg"><b><fmt:message	key="CMN.EMPSTOMAPASDDOASST" bundle="${dcpsLabels}"></fmt:message></b></legend>
		
		<br>
			
		<div class="scrollablediv"  style="width: 70%; overflow: auto; height: 250px;">
		
		<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
		
			<display:table	list="${resValue.EmpList}" id="vo"  cellpadding="5"  style="width:100%" requestURI="">
	
					<display:column titleKey="CMN.CHKBXEMPSELECT" headerClass="datatableheader" style="text-align:center;width:10%" class="oddcentre" sortable="true">
						<input type="checkbox" align="left" name="checkbox" id="checkbox${vo_rowNum}" value="${vo[0]}" />
						<input type="hidden" name="hidDcpsId${vo_rowNum}" id="hidDcpsId${vo_rowNum}" value="${vo[0]}" />
						<input type="hidden" name="hidOrgEmpMstId${vo_rowNum}" id="hidOrgEmpMstId${vo_rowNum}" value="${vo[4]}" />
					</display:column>
			
					<display:column titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader" sortable="true" style="text-align: left;width:40%">
						<c:out value="${vo[1]}" />
					</display:column>
					
					<display:column titleKey="CMN.SEVARTHID"  headerClass="datatableheader" sortable="true" style="text-align:left;width:15%"   >
						<c:out value="${vo[2]}" />
						<script>
							document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
						</script>
					</display:column>
			
					<display:column  titleKey="CMN.DDOASSTORNOT" headerClass="datatableheader" sortable="true" style="text-align:center;width:15%">
									<c:choose>
										<c:when test="${vo[3] == 1}">
											<c:out value="Yes"></c:out>
											<input type="hidden" id="hidDDOAsstOrNot${vo_rowNum}" value="Y"/>
										</c:when>
										<c:otherwise>
											<c:out value="No"></c:out>
											<input type="hidden" id="hidDDOAsstOrNot${vo_rowNum}" value="N"/>
										</c:otherwise>
						    		</c:choose>
					</display:column>
					
					<display:column titleKey="CMN.USERNAME"  headerClass="datatableheader" sortable="true" style="text-align:left;width:30%"   >
						<c:out value="${vo[5]}" />
					</display:column>
	
				<display:setProperty name="paging.banner.placement" value="bottom" />
	
			</display:table>
	
		</div>
		
		<br>
		
		<div align = "left">
			<hdiits:button	name="btnAssign" type="button" classcss="bigbutton"
				captionid="CMN.ASSIGNASDDOASST" bundle="${dcpsLabels}"
				onclick="AssignAsDDOAsst();" />
			
			<hdiits:button	name="btnDeAssign" type="button" classcss="bigbutton"
				captionid="CMN.DEASSIGN" bundle="${dcpsLabels}"
				onclick="DeAssignFromDDOAsst();" />
		</div>
		
		</fieldset>
		
	</c:if>
	
	<input type="hidden" name="hidSearchFromMapDDOAsst" id="hidSearchFromMapDDOAsst" value="searchByDDO" />

</hdiits:form>


<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromMapDDOAsst}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
