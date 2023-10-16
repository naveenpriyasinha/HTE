<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>
<fmt:setBundle basename="resources.eis.empNomineeLables" var="commonLables" scope="request"/>

<script type="text/javascript" src="<c:url value="/script/hrms/eis/NomineeDtls.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set  var="purposeLst" value="${resValue.purposeLst}"></c:set>	
<c:set var="selectedUserId" value="${resValue.userId}"></c:set>
<c:set var="workFlowEnabled" value="${resValue.blnWorkFlowEnabled}"></c:set>

<hdiits:form name="frmPurposeofNomn" validate="true" method="POST" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<c:if test="${workFlowEnabled eq 'true'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EIS.PurposeNomination" bundle="${commonLables}" /></b></a></li>
		</c:if>
		<c:if test="${workFlowEnabled eq 'false'}">
			<!-- IFMS -->
			
			
		<li class="selected"><a href="#" onclick="javascript:openAppWindow('addEditEmpInfo&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="EIS.PersonalDtls" bundle="${commonLables}"/> </b> </a></li>
			
			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('EducationDtls&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.quali_dtls"
				bundle="${commonLables}"></fmt:message> </b> </a></li>

			<li class="selected" style="width: 152px;"><a href="#" style="width: 142px;"
				onclick="javascript:openAppWindow('CoCurricularDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.extraCoCurricularDtls"
				bundle="${commonLables}"></fmt:message> </b> </a></li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('FamilyDetails&flag=getComboDetails&workFlowEnabled=false')">
			<b> <fmt:message key="eis.FamilyDtls"
				bundle="${commonLables}" /> </b> </a></li>

			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
				key="eis.nominee_dtls" bundle="${commonLables}" /></b></a></li>

			<li class="selected"><a href="#"
				onclick="javascript:openAppWindow('getEmpMiscellenousDtls&workFlowEnabled=false')"> <b>
			<fmt:message key="EIS.EmpMislDtl" bundle="${commonLables}" />
			</b> </a></li>
		</c:if>
	</ul>
</div>
<div id="PurposeofNomn" name="PurposeofNomn">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	
		<%@ include file="empInfo/EmployeeInfo.jsp"%>
	
	<hdiits:fieldGroup  titleCaptionId="EIS.PurposeNomination" bundle="${commonLables}">
	<!--<table class="tabtable">
		<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="4" align="center"><font color="#ffffff">
		<strong><u><fmt:message bundle="${commonLables}" key="EIS.PurposeNomination"/></u></strong> </font></td>				
		</tr>
	</table>-->
	<center>
	<BR>
	<table align="center" width="50%" cellpadding="5" cellspacing="5">
		<tr align="center">
		<td class="fieldLabel" width="25%" align="left"><hdiits:caption captionid="EIS.PurposeType" bundle="${commonLables}" /></td>
		<td class="fieldLabel" width="25%" align="left">
			<hdiits:select name="purposeCmb" id="purposeCmb" captionid="EIS.PurposeType" bundle="${commonLables}" onchange="showNextPage();">
				<hdiits:option value="Select">--<fmt:message key="eis.select" bundle="${commonLables}" />--</hdiits:option>
				<c:forEach var="purpose" items="${purposeLst}">
   					<option value="<c:out value="${purpose.lookupName}"/>">
					<c:out value="${purpose.lookupDesc}"/></option>						
				</c:forEach>
			</hdiits:select>
		</td>
		</tr>
	</table>
	<table align="center">
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr>
			<td>
				<hdiits:button type="button" name="Close" captionid="EIS.Cancel" bundle="${commonLables}" onclick="goToHomePageOfPurpseNomi()" />
			</td>
		</tr>
	</table>
	</center>
	</hdiits:fieldGroup>
	</div>
	</div></div>
	
	<!--  IFMS -->
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>  	

	<script type="text/javascript">
		var workFlowEnabled = ${workFlowEnabled};
		initializetabcontent("maintab");
	</script>		
	
</hdiits:form>
