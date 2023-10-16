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
<script  type="text/javascript" src="script/dcps/srkaDdoInfo.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript">

DDOCODE='<fmt:message key="DCPS.DDOCODE" bundle="${dcpsAlerts}"></fmt:message>';
ADMNDEPT='<fmt:message key="DCPS.ADMNDEPT" bundle="${dcpsAlerts}"></fmt:message>';
FIELDDEPT='<fmt:message key="DCPS.FIELDDEPT" bundle="${dcpsAlerts}"></fmt:message>';
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>



</script>


<hdiits:form name="DDOInfoForm" id="DDOInfoForm" encType="multipart/form-data" validate="true" method="post"  >
<fieldset style="width:50%" class="tabstyle">
<legend id="headingMsg">
<b><fmt:message key="CMN.DDOPROFILEINFO" bundle="${dcpsLabels}"></fmt:message></b>
</legend>
<table cellpadding="4" cellspacing="4">
	<tr>
		<td><fmt:message key="CMN.TREASURY"
			bundle="${dcpsLabels}"></fmt:message></td>
		<td><select name="cmbTreasury" id="cmbTreasury"   >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="Treasury" items="${resValue.TreasuryList}" >
				         					<option value="${Treasury.id}">${Treasury.desc}</option>
				         				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td><fmt:message key="CMN.DDOCODE"
			bundle="${dcpsLabels}"></fmt:message></td>
		<td><select name="cmbDdoCode" id="cmbDdoCode" STYLE="width: 200px"  >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="DdoCodes" items="${resValue.DdoList}" >
				         					<option value="${DdoCodes.id}">${DdoCodes.desc}</option>
				         				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td><fmt:message key="CMN.ADMNDEPT"
			bundle="${dcpsLabels}"></fmt:message></td>
		<td><select name="cmbAdminDept" id="cmbAdminDept" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="AdminDpt" items="${resValue.AdminDept}" >
				         					<option value="${AdminDpt.id}">${AdminDpt.desc}</option>
				         				</c:forEach>
			</select></td>
	</tr>
	<tr>
		<td><fmt:message key="CMN.FIELDDEPT"
			bundle="${dcpsLabels}"></fmt:message></td>
		<td><select name="cmbFieldDept" id="cmbFieldDept" style="width:100%" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				</select>
				</td>
	</tr>
</table>
</fieldset>
<br></br>
<div style="width:50%;text-align: center;align:center">
<hdiits:button name="btnSave" type="button" captionid="BTN.SAVE" bundle="${dcpsLabels}" onclick="SaveDdoData();" />
</div>
</hdiits:form>
<ajax:select source="cmbAdminDept" target="cmbFieldDept"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popFieldDept"
	parameters="cmbAdminDept={cmbAdminDept}">
</ajax:select>
<ajax:select source="cmbTreasury" target="cmbDdoCode"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popDdoCode"
	parameters="cmbTreasury={cmbTreasury}">
</ajax:select>