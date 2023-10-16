<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request"/>
<fmt:message key="CMN.HOD" bundle="${lnaLabels}" var="HOD"></fmt:message>
<fmt:message key="CMN.DEO" bundle="${lnaLabels}" var="DEO"></fmt:message>
<fmt:message key="CMN.HODASST2" bundle="${lnaLabels}" var="HODASST2"></fmt:message>
<fmt:message key="CMN.HODASST" bundle="${lnaLabels}" var="HODASST"></fmt:message>
<fmt:message key="CMN.COMPADVC" bundle="${lnaLabels}" var="ComputerAdvance"></fmt:message>
<fmt:message key="CMN.HOUSEADVC" bundle="${lnaLabels}" var="HouseAdvance"></fmt:message>
<fmt:message key="CMN.MOTORADVC" bundle="${lnaLabels}" var="MotorAdvance"></fmt:message>
<fmt:message key="CMN.DEOAPP" bundle="${lnaLabels}" var="DEOAPP"></fmt:message>

<script type="text/javascript" src="script/lna/loanAndAdvance.js"></script>
<script type="text/javascript" src="script/lna/computerAdvance.js"></script>
<script type="text/javascript" src="script/lna/houseAdvance.js"></script>
<script type="text/javascript" src="script/lna/motorAdvance.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>

<hdiits:form name="LNARequestProcessForm" encType="multipart/form-data"	validate="true" method="post">

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="toPost" value="${resValue.PostIdForDEO}"/>
<c:set var="empVO" value="${resValue.LNAEmpVO}"/>
<c:set var="ElementId" value="${resValue.ElementId}"/>

<fmt:formatDate value="${empVO[8]}" pattern="dd/MM/yyyy" var="DOB"/>
<fmt:formatDate value="${empVO[9]}" pattern="dd/MM/yyyy" var="DOJ"/>
<fmt:formatDate value="${empVO[10]}" pattern="dd/MM/yyyy" var="DOJCUROFFICE"/>
<fmt:formatDate value="${empVO[19]}" pattern="dd/MM/yyyy" var="DOS"/>
<input type="hidden"  name='hidDateOfSuperAnnuation' id="hidDateOfSuperAnnuation" value="${DOS}" />
<input type="hidden"  name='hidDateOfJoining' id="hidDateOfJoining" value="${DOJ}" />
<c:choose>
<c:when test="${empVO[2]=='M'}">
 <c:set var="Gender" value="Male"/>
</c:when>
<c:when test="${empVO[2]=='F'}">
 <c:set var="Gender" value="Female"/>
</c:when>
<c:when test="${empVO[2]=='T'}">
 <c:set var="Gender" value="Transgender"/>
</c:when>
<c:otherwise>
 <c:set var="Gender" value="${empVO[2]}"/>
</c:otherwise>
</c:choose>
<input type="hidden"  name='ForwardToPost' id="ForwardToPost" value="${toPost}" />
<input type="hidden"  name='hidRequestType' id="hidRequestType"  value="${resValue.requestType}" />
<input type="hidden"  name='hidBasicPay' id="hidBasicPay" value="${empVO[3]}" />
<input type="hidden" name="hidSevaarthId" id="hidSevaarthId" value="${resValue.SevaarthId}" />
<input type="hidden" name="hidEmpGroup" id="hidEmpGroup" value="${resValue.EmpGroup}" />
<input type="hidden" name="hidDesignation" id="hidDesignation" value="${empVO[6]}" />
<input type="hidden" name="hidPayCommisssion" id="hidPayCommisssion" value="${empVO[1]}" />
<input type="hidden" name="hidPayinPB" id="hidPayinPB" value="${resValue.PayInPb}" />
<input type="hidden" name="hidUserType" id="hidUserType" value="${resValue.userType}" />
<input type="hidden" name="hidGradePay" id="hidGradePay" value="${resValue.GradePay}" />
<input type="hidden" name="hidPreviousSancAmt" id="hidPreviousSancAmt" value="" />
<c:if test="${resValue.requestType == ComputerAdvance }">
      <c:set var="CAVisible" value=""/>
      <c:set var="MCAVisible" value="none"/>
      <c:set var="HBAVisible" value="none"/>
</c:if>
<c:if test="${resValue.requestType == HouseAdvance}">
      <c:set var="CAVisible" value="none"/>
      <c:set var="MCAVisible" value="none"/>
      <c:set var="HBAVisible" value=""/>
</c:if>
  <c:if test="${resValue.requestType == MotorAdvance}">
      <c:set var="CAVisible" value="none"/>
      <c:set var="MCAVisible" value=""/>
      <c:set var="HBAVisible" value="none"/>
</c:if>
<fieldset style="width:100%" class="tabstyle"><legend><fmt:message key="CMN.EMPDETAILS" bundle="${lnaLabels}"></fmt:message></legend>
<table cellspacing = "4" cellpadding = "4" >
	<tr>
		<td width="20%"><b><fmt:message key="CMN.NAME" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[0]}" /></td>
		<td width="20%"><b><fmt:message key="CMN.PAYCOMMISSION" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[1]}" /></td>
	</tr>
	
	<tr>
		<td width="20%"><b><fmt:message key="CMN.GENDER" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${Gender}" /></td>
		<td width="20%"><b><fmt:message key="CMN.BASIC" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[3]}" /></td>
	</tr>
	
	<tr>
		<td width="20%"><b><fmt:message key="CMN.SEVAARTHID" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${resValue.SevaarthId}" /></td>
		<td width="20%">
		<b>
			<c:if test="${empVO[1]=='6th Pay Commission'}">
				<fmt:message key="CMN.PAYBAND" bundle="${lnaLabels}"></fmt:message>
			</c:if>
			<c:if test="${empVO[1]=='5th Pay Commission'}">
				<fmt:message key="CMN.PAYSCALE" bundle="${lnaLabels}"></fmt:message>
			</c:if>
		</b></td>
		<td width="30%"><c:out value="${resValue.payScale}" /></td>
	</tr>
	
	<tr>
		<td width="20%"><b><fmt:message key="CMN.GROUP" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[5]}" /></td>
		<td width="20%">
		<b>
			<c:if test="${empVO[1]=='6th Pay Commission'}">
				<fmt:message key="CMN.GRADEPAY" bundle="${lnaLabels}"></fmt:message>
			</c:if>
			<c:if test="${empVO[1]=='5th Pay Commission'}">
				<fmt:message key="CMN.DP" bundle="${lnaLabels}"></fmt:message>
			</c:if></b></td>
		<td width="30%">
			<c:if test="${empVO[1]=='6th Pay Commission'}">
				<c:out value="${resValue.GradePay}"></c:out>
			</c:if>
			<c:if test="${empVO[1]=='5th Pay Commission'}">
				<c:out value="${empVO[3]*0.5}"></c:out>
			</c:if>
		</td>
	</tr>
	
	<tr>
		<td width="20%"><b><fmt:message key="CMN.DESIGNATION" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[6]}" /></td>
		<td width="20%">
			<b>
			<c:if test="${resValue.dcpsOrGpf == 'Y'}"><fmt:message key="CMN.DCPSACCNO" bundle="${lnaLabels}"></fmt:message></c:if>
			<c:if test="${resValue.dcpsOrGpf == 'N'}"><fmt:message key="CMN.GPFACCNO" bundle="${lnaLabels}"></fmt:message></c:if>
			</b>
		</td>
		<td width="30%"><c:out value="${empVO[7]}" /></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.OFFICEADD" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%">
			<c:out value="${empVO[21]}" />
			<c:if test="${empVO[25] != null}"><c:out value=", ${empVO[25]}" /></c:if>
		</td>
		<td width="20%"><b><fmt:message key="CMN.DOB" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${DOB}" /></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.LANDLINE" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[26]}" /></td>
		<td width="20%"><b><fmt:message key="CMN.DOJGOV" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${DOJ}" /></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.MOBILENO" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[27]}" /></td>
		<td width="20%"><b><fmt:message key="CMN.DOJCURROFFICE" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${DOJCUROFFICE}" /></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.RESIADD" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[11]}" />
			<c:if test="${empVO[12] != null}"><c:out value=", ${empVO[12]}" /></c:if>
			<c:if test="${empVO[13] != null}"><c:out value=", ${empVO[13]}" /></c:if>
			<c:if test="${empVO[14] != null}"><c:out value=", ${empVO[14]}" /></c:if>
			<c:if test="${empVO[15] != null}"><c:out value=", ${empVO[15]}" /></c:if>
			<c:if test="${empVO[16] != null}"><c:out value=", ${empVO[16]}" /></c:if>
		</td>
		<td width="20%"></td>
		<td width="30%"></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.LANDLINE" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[17]}" /></td>
		<td width="20%"></td>
		<td width="30%"></td>
	</tr>
	<tr>
		<td width="20%"><b><fmt:message key="CMN.MOBILENO" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${empVO[18]}" /></td>
		<td width="20%"><b><fmt:message key="CMN.DOS" bundle="${lnaLabels}"></fmt:message></b></td>
		<td width="30%"><c:out value="${DOS}" /></td>
	</tr>
</table>
</fieldset>
<fieldset id="computerAdvance" class="tabstyle" style="display:${CAVisible}"><legend><fmt:message key="CMN.CA" bundle="${lnaLabels}"></fmt:message></legend><br/>
	<jsp:include page="ComputerAdvance.jsp" />
</fieldset>

<fieldset id="houseBuilingAdvance" class="tabstyle" style="display:${HBAVisible}"><legend><fmt:message key="CMN.HBA" bundle="${lnaLabels}"></fmt:message></legend><br/>
	<jsp:include page="HouseBuildingAdvance.jsp" />
</fieldset>

<fieldset id="motorCarAdvance" class="tabstyle" style="display:${MCAVisible}"><legend><fmt:message key="CMN.VA" bundle="${lnaLabels}"></fmt:message></legend><br/>
	<jsp:include page="MotorCarAdvance.jsp" />
</fieldset>
<table width="70%">
	<tr>
	<td align="center"><table align="center"><tr>
		<c:choose>
		<c:when test="${resValue.userType == DEO}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.SAVE" bundle="${lnaLabels}" id="btnSave" name="btnSave" onclick="SaveRequest();"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${lnaLabels}" id="btnForward" name="btnForward" onclick="forwardRequest(1);"></hdiits:button></td>
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(1);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == DEOAPP}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.VERIFY" bundle="${lnaLabels}" id="btnVerify" name="btnVerify" onclick="forwardRequest(2);"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.SENDBACK" bundle="${lnaLabels}" id="btnSendBack" name="btnSendBack" onclick="rejectRequest(1);"></hdiits:button></td>
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(2);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == HO}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${lnaLabels}" id="btnForward" name="btnForward" onclick="forwardRequest(3);"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.SENDBACK" bundle="${lnaLabels}" id="btnSendBack" name="btnSendBack" onclick="rejectRequest(2);"></hdiits:button></td>
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(3);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == HODASST2}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.SAVE" bundle="${lnaLabels}" id="btnSave" name="btnSave" onclick="SaveRequest();"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${lnaLabels}" id="btnForward" name="btnForward" onclick="forwardRequest(5);"></hdiits:button></td>
			<c:if test="${ElementId == '800025'}">
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(6);"></hdiits:button></td>
			</c:if>
			<c:if test="${ElementId == '800029'}">
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(7);"></hdiits:button></td>
			</c:if>
		</c:when>
		<c:when test="${resValue.userType == HODASST}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.FORWARD" bundle="${lnaLabels}" id="btnForward" name="btnForward" onclick="forwardRequest(4);"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.SENDBACK" bundle="${lnaLabels}" id="btnSendBack" name="btnSendBack" onclick="rejectRequest(3);"></hdiits:button></td>
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(4);"></hdiits:button></td>
		</c:when>
		<c:when test="${resValue.userType == HOD}">
			<td width="5%" align="right"><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${lnaLabels}" id="btnApprove" name="btnApprove" onclick="approveRequest();"></hdiits:button></td>
			<td width="5%" align="center"><hdiits:button type="button" captionid="BTN.REJECT" bundle="${lnaLabels}" id="btnReject" name="btnReject" onclick="rejectRequest(4);"></hdiits:button></td>
			<td width="5%" align="left"><hdiits:button type="button" captionid="BTN.BACK" bundle="${lnaLabels}" id="btnBack" name="btnBack" onclick="backButton(5);"></hdiits:button></td>
		</c:when>
		</c:choose>
	</tr>
	</table>
	</td></tr>
</table>
</hdiits:form>