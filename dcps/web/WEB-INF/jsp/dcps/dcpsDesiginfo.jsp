<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsSrka.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src ="script/dcps/dcpsDDO.js"></script>



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
	<table>
	<tr>
	<td width="50%" align="left" ><b><fmt:message key="CMN.FIELDDEPARTMENT" bundle="${dcpsLabels}"></fmt:message></b></td>
	<td width="50%" align="left">
		<select name="cmbFieldDepartment" id="cmbFieldDepartment" style="width:100%">		
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 	<c:forEach var="ParentDeptList" items="${resValue.ParentDeptList}">					
					<option value="${ParentDeptList.lookupId}"><c:out value="${ParentDeptList.lookupDesc}"></c:out></option>
			 	</c:forEach>
 		</select>
	</td>
	</tr>
	<tr>
		<td width="50%" align="left"><b><fmt:message key="CMN.DESIGNATIONCODE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td width="50%" align="left"><input type="text"  size="20%" name='txtDesigCode' id="txtDesigCode" style="text-align: left" onKeyPress=""/></td>
	</tr>
	<tr>
		<td width="50%" align="left"><b><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td width="50%" align="left"><input type="text"  size="50%" name='txtDesig' id="txtDesig" style="text-align: left" onKeyPress=""/></td>
	</tr>
	<tr>
		<td width="50%" align="left"><b><fmt:message key="CMN.CADRE" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td width="50%" align="left">
			<select name="cmbCadre" id="cmbCadre" style="width:50%" onfocus="onFocus(this)" onblur="onBlur(this);">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="CadreList" items="${resValue.CadreList}">					
						<option value="${CadreList.id}"><c:out value="${CadreList.desc}"></c:out></option>
			 		</c:forEach>
			</select>
		
		<div>
			<button style="width:50%" type="button" bundle="${dcpsLabels}" id="btnSave" name="btnSave" >Reorganise Cadre Hierarchy</button>
		</div>
		</td>
	</tr>
	<tr>
		<td width="50%" align="left"><b><fmt:message key="CMN.PAYCOMMISSION" bundle="${dcpsLabels}"></fmt:message></b></td>
		<td width="50%" align="left">
			<select name="cmbPayCommission" id="cmbPayCommission" style="width:100%">			
			 	<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			 		<c:forEach var="PayCommissionDCPS" items="${resValue.PayCommissionDCPS}">					
						<option value="${PayCommissionDCPS.lookupId}"><c:out value="${PayCommissionDCPS.lookupDesc}"></c:out></option>
			 		</c:forEach>
		   	</select>
		</td>
	</tr>
	</table>
	</td>
	</tr>
</table><br></br>
<div style="width:50;text-align: center;align:center">
	<hdiits:button type="button" style="align:center" captionid="CMN.SAVE" bundle="${dcpsLabels}" id="btnSave" name="btnSave"  onclick="saveDesigInfo();"/>
</div><br></br>
</fieldset><br></br>
<table width="98%" id="tblDesigDtls">
	<tr>
	<td>
		<display:table list="${DesigList}" size="10" id="vo" pagesize="10" cellpadding="5" style="width:100%" requestURI="">	
				<display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.CODE" headerClass="datatableheader"
				sortable="true" value="${vo[1]}" >						
			    </display:column>
				
				<display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.DESIGNATION" headerClass="datatableheader"
				sortable="true" value="${vo[2]}">		
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.CADRE" headerClass="datatableheader"
				sortable="true" value="${vo[3]}">				
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.GROUP" headerClass="datatableheader"
				sortable="true" value="${vo[4]}">				
			    </display:column>
			    
			    <display:column style="text-align: center;" class="tablecelltext"
				titleKey="CMN.PAYCOMMISSION" headerClass="datatableheader"
				sortable="true" value="${vo[5]}">				
			    </display:column>
			</display:table>
		</td>
	</tr>
</table>
	
</hdiits:form>