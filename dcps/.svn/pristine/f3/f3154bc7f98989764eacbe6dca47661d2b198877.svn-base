<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>

<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseCalculation.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstPnsnCaseStatus" value="${resValue.lLstPnsnCaseStatus}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>

<hdiits:form name="getStatusOfPensionCases" validate="" method="post">
<div>&nbsp;</div>
<fieldset style="width: 100%" class="tabstyle" id="fsPensionCaseStatus">
<legend id="headingMsg"><b> <fmt:message
	key="PPROC.STATUS" bundle="${pensionLabels}"></fmt:message></b></legend>
	<div>&nbsp;</div>
<table width="98%" id="tblPnsnCaseStatus">
			<tr>
				<td>
				<display:table list="${lLstPnsnCaseStatus}" id="vo" cellpadding="5" requestURI="" style="width:100%" pagesize="100" export="false" partialList="true"  offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending"  >				
			
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:column titleKey="PPROC.INWARDNO" headerClass="datatableheader"  class="oddcentre"
						sortable="true" style="width:20%;text-align: left;">
					 	${vo[1]}
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.NAMEOFPENSIONER"
						headerClass="datatableheader" sortable="true" >
						${vo[2]}
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.TYPEOFPENSION"
						headerClass="datatableheader" sortable="true" >
						<c:if test="${vo[3] == 'ABSORPTION'}">
						<fmt:message key="PNSNTYPE.ABSORPTION" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo[3] == 'COMPASSIONATE'}">
						<fmt:message key="PNSNTYPE.COMPASSIONATE" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo[3] == 'COMPENSATION'}">
						<fmt:message key="PNSNTYPE.COMPENSATION" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'COMPULSORY'}">
						<fmt:message key="PNSNTYPE.COMPULSORY" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'EXTRAORDINARY'}">
						<fmt:message key="PNSNTYPE.EXTRAORDINARY" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'FAMILYPNSN'}">
						<fmt:message key="PNSNTYPE.FAMILYPNSN" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'INVALID'}">
						<fmt:message key="PNSNTYPE.INVALID" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'RETIRING105'}">
						<fmt:message key="PNSNTYPE.RETIRING105" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'RETIRING104'}">
						<fmt:message key="PNSNTYPE.RETIRING104" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'SUPERANNU'}">
						<fmt:message key="PNSNTYPE.SUPERANNU" bundle="${pensionLabels}"/>
						</c:if>
						<c:if test="${vo[3] == 'VOLUNTARY64'}">
						<fmt:message key="PNSNTYPE.VOLUNTARY64" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo[3] == 'VOLUNTARY65'}">
						<fmt:message key="PNSNTYPE.VOLUNTARY65" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo[3] == 'INJURY'}">
						<fmt:message key="PNSNTYPE.INJURY" bundle="${pensionLabels}"/>
						</c:if>	
						<c:if test="${vo[3] == 'GALLANTRY'}">
						<fmt:message key="PNSNTYPE.GALLANTRY" bundle="${pensionLabels}"/>
						</c:if>
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.DATEOFRETIREMENT"
						headerClass="datatableheader" sortable="true" >
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${vo[4]}"/>
					</display:column>
					<display:column style="height:35;text-align: left;"
						class="oddcentre" titleKey="PPROC.STATUS"
						headerClass="datatableheader" sortable="true" >						
						<c:if test="${vo[5] == 'DRAFT'}">
							<c:out value="Draft"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'FWDBYDEO'}">
							<c:out value="Fowarded to DDO"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'APRVDBYDDO'}">
							<c:out value="Approved By DDO"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'RJCTBYDDO'}">
							<c:out value="Rejected By DDO"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'SENDTOAG'}">
							<c:out value="Send to AG"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'MISBYDDO'}">
							<c:out value="Move for Correction due to approved by DDO mistake "></c:out>
						</c:if>
						<c:if test="${vo[5] == 'AGQUERY'}">
							<c:out value="Move for correction due to AG query  "></c:out>
						</c:if>
						<c:if test="${vo[5] == 'APRVDBYAG'}">
							<c:out value="Approved By AG"></c:out>
						</c:if>
						<c:if test="${vo[5] == 'FRWDTOAG'}">
							<c:out value="Forward To AG"></c:out>
						</c:if>
							<c:if test="${vo[5] == 'FWDTOPEN'}">
							<c:out value="Forward To Pension Asst"></c:out>
						</c:if>
					</display:column>
					<display:column style="height:35;text-align: center;"
						class="oddcentre" titleKey="PPROC.CASETYPE"
						headerClass="datatableheader" sortable="true" >
						${vo[6]}
					</display:column>
					</display:table>
				</td>
			</tr>
		</table>
		<div>&nbsp;</div>		
</fieldset>
<center><hdiits:button name="btnClose"	type="button" captionid="PPROC.CLOSE" bundle="${pensionLabels}" onclick="winCls();" /></center>
<div>&nbsp;</div>
</hdiits:form>

