<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/ChangeParentDept.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.ALLEMPSUNDERDDO" bundle="${dcpsLables}"></fmt:message></b></legend>
</br>

<input type="hidden" id="hidTreasuryCode" value="${resValue.lStrTreasuryCode}"/>
<input type="hidden" id="hidDdoCode" value="${resValue.lStrDdoCode}"/>

<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
<div style="width:100%;overflow:auto;height:300px;" >
<display:table list="${resValue.empList}" size="10"  id="vo" pagesize="100" cellpadding="5" style="width:70%" requestURI="" >

	<display:setProperty name="paging.banner.placement" value="none" />	
	
	<display:column titleKey="CMN.EMPLOYEENAME" headerClass="datatableheader" sortable="true" style="text-align: left;width:25%">
		<c:out value="${vo[2]}"></c:out>
	</display:column>
	
	<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;width:20%"  sortable="true"  title="Deselect" >
							<input type="checkbox" name="checkbox" id="checkbox${vo_rowNum}" value="${vo[0]}"/>
							<script>
										document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
							</script>
	</display:column>
	
	<display:column style="text-align: left;width:25%" class="tablecelltext" titleKey="CMN.REASONFORDESELECTION" headerClass="datatableheader" sortable="true" >
		<c:out value="Change in DDO Code"></c:out>
	</display:column>
	
	<display:column style="text-align: left;width:30%" class="tablecelltext" titleKey="CMN.DCPSACNO" headerClass="datatableheader" sortable="true" >
		<c:out value="${vo[1]}"></c:out>
	</display:column>
	
</display:table>
</div>
<br>

<table align="center" width="85%">
<tr>
	<td width="15%"><fmt:message key="CMN.CURRTREASURYCODE"
				bundle="${dcpsLables}"></fmt:message>
	</td>
	<td width="35%" >
		<input type="text" name="currTreasuryCode" id="currTreasuryCode" value="${resValue.treasuryCode}" readonly="readonly"/>
	</td>
	<td width="15%" style="padding-left: 5%"><fmt:message key="CMN.CURRDDOCODE"
				bundle="${dcpsLables}"></fmt:message>
	</td>
	<td  width="35%">
		<input type="text" name="currDDOCode" id="currDDOCode" value="${resValue.ddoCode}" readonly="readonly"/>
	</td>
</tr>
<tr>
	<td width="15%"><fmt:message key="CMN.NEWTREASURYCODE"
				bundle="${dcpsLables}"></fmt:message>
	</td>
	<td width="35%">
		<select name="newTreasuryCode" id="newTreasuryCode" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					         				<c:forEach var="Treasury" items="${resValue.TreasuryList}" >
						         						<option value="${Treasury.id}"><c:out value="${Treasury.desc}"></c:out></option>
					         				</c:forEach>
		</select>
	</td>
	<td width="15%"  style="padding-left: 5%"><fmt:message key="CMN.NEWDDOCODE"
				bundle="${dcpsLables}"></fmt:message>
	</td>
	<td  width="35%">
		<input type="text" name="newDDOCode" id="newDDOCode" maxlength="10"/>
	</td>
</tr>
</table>



<br>
<div align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="changeDDOCode();"/>
<hdiits:button name="btnSelectAll" id="btnSelectAll" type="button"  captionid="BTN.SELECTALL" bundle="${dcpsLables}" onclick="checkAll('checkbox')"/>
<hdiits:button name="btnDeSelectAll" id="btnDeSelectAll" type="button"  captionid="BTN.DESELECTALL" bundle="${dcpsLables}" onclick="UnCheckAll('checkbox')"/>
</div>
</fieldset>

<ajax:select source="newTreasuryCode" target="newDdoCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDDOsForTreasury"
	parameters="cmbTreasuryCode={newTreasuryCode}"
	>
</ajax:select>