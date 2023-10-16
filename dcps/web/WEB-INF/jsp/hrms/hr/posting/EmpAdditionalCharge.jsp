
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.hr.posting.postingLabels" var="EmpAdditionalChargesLables" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/posting/EmpAdditionalCharge.js"/>"></script>

<script>

	var empAdditionalChargeAlertMsgArr = new Array();
	empAdditionalChargeAlertMsgArr[0]='<fmt:message bundle="${EmpAdditionalChargesLables}" key="SameUserAlert"/>';
	empAdditionalChargeAlertMsgArr[1]='<fmt:message bundle="${EmpAdditionalChargesLables}" key="EIS.DateValidtn"/>';
	empAdditionalChargeAlertMsgArr[2]="<fmt:message bundle='${EmpAdditionalChargesLables}' key='DosAndTodayAlert'/>";
	empAdditionalChargeAlertMsgArr[3]="<fmt:message bundle='${EmpAdditionalChargesLables}' key='Eis.ValidStartDate'/>";
	empAdditionalChargeAlertMsgArr[4]="<fmt:message bundle='${EmpAdditionalChargesLables}' key='DoeAndTodayAlert'/>";
	empAdditionalChargeAlertMsgArr[5]="<fmt:message bundle='${EmpAdditionalChargesLables}' key='Eis.ValidEndDate'/>";
	empAdditionalChargeAlertMsgArr[6]="<fmt:message bundle='${EmpAdditionalChargesLables}' key='DooAndTodayAlert'/>";

</script>

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="OfficeTypeInfo" value="${resultValue.arOfficeTypeInfo}"></c:set>
<c:set var="DesignationVO" value="${resultValue.arDesignationVO}"></c:set>
<c:set var="xmlFilePathNameForMulAddRec" value="${resultValue.xmlFilePathNameForMulAddRec}" />
<c:set var="allPostingDataOnUserIdVOList" value="${resultValue.allPostingDataOnUserIdVOList}" />
<c:set var="selectedUserId" value="${resultValue.selectedUserId}"></c:set>
<c:set var="todayDate" value="${resultValue.tDate}"></c:set>
<fmt:formatDate value="${todayDate}" var="tDate" pattern="dd/MM/yyyy"/>
<c:set var="strAppType" value="SRVC-ADDCHARGE"></c:set>

<hdiits:form name="frmEmpAdditionalChargesDtls" validate="true" method="POST">

	<div id="tabmenu">
		<%@ include file="../../eis/ProfessionalDetailsTab.jsp"%>
	</div>
	<div id="education" name="education">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<%@ include file="../../eis/empInfo/EmployeeInfo.jsp"%>
	<!--<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" ><u><font class="Label3" align="center" color="white"><b><fmt:message key="EIS.EmpAddChrg" bundle="${EmpAdditionalChargesLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>-->
	<hdiits:fieldGroup  id="additionalChargeFieldGroupId"titleCaptionId="EIS.EmpAddChrg" bundle="${EmpAdditionalChargesLables}">
	<table class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="EIS.TypeOfRec" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td class="fieldLabel" width="25%">
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRecHistory" onclick="checkRecType(),showOfficeName('post','','selOfficeName','selDesignation','selPost')"	value="H" captionid="EIS.History" bundle="${EmpAdditionalChargesLables}" />
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRecCurrent" onclick="checkRecType(),showOfficeName('post','','selOfficeName','selDesignation','selPost')" value="C" captionid="EIS.Current" bundle="${EmpAdditionalChargesLables}" />
			</td>
			<td class="fieldLabel" width="25%"></td>
			<td class="fieldLabel" width="25%"></td>
		</tr>

		<tr>
			<td width="25%">
				<b><hdiits:caption captionid="EIS.OrderNo" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
				<td width="25%"><hdiits:text captionid="EIS.OrderNo" bundle="${EmpAdditionalChargesLables}" validation="txt.isrequired" mandatory="true" name="txtOrderNo" /></td>

				<td width="25%"><b><hdiits:caption captionid="EIS.OrderDate" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
				<td width="25%"><hdiits:dateTime captionid="EIS.OrderDate" bundle="${EmpAdditionalChargesLables}" validation="txt.isdt,txt.isrequired" mandatory="true" name="dtOrderDate" maxvalue="01/12/2099" afterDateSelect="campareOrdDateAndTodayDate()"/></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.StartDate" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:dateTime captionid="EIS.StartDate" bundle="${EmpAdditionalChargesLables}" mandatory="true" name="dtStartDate" afterDateSelect="campareStrDateAndTodayDate()" maxvalue="01/12/2099"/></td>
			<td width="25%" id='dtToDt' style="display:NONE"><b><hdiits:caption	captionid="EIS.ToDate" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%" id='ToDate' style="display:NONE"><hdiits:dateTime captionid="EIS.ToDate" bundle="${EmpAdditionalChargesLables}"	name="dtToDate" maxvalue="01/12/2099" afterDateSelect="campareEndDateAndTodayDate()" /></td>
		</tr>
	</table>
	<br>
	<!--<table class="tabtable">	
		<tr bgcolor="#386CB7">
			<td class="fieldLabel">
				<font color="#ffffff">
					<strong><u><fmt:message bundle="${EmpAdditionalChargesLables}" key="EIS.SrchPost"/></u></strong>
				</font>
			</td>
		</tr>
	</table>-->
	<hdiits:fieldGroup titleCaptionId="EIS.SrchPost" bundle="${EmpAdditionalChargesLables}" >
	<table class="tabtable">
		<tr>
			<td width="50%">
				<b><hdiits:radio name="rdoSearchPost" id="rdoSearchPostVacant" value="vacant" captionid="EIS.Vacant" bundle="${EmpAdditionalChargesLables}" onclick="onClickVacant()" /></b>
				<b><hdiits:radio name="rdoSearchPost" id="rdoSearchPostEmployee" value="ByEmployee" captionid="EIS.ByEmp" bundle="${EmpAdditionalChargesLables}" onclick="onClickEmployee()" /></b>
			</td>
			<td id='txtEmpId' style="display:NONE" width="50%"><hdiits:search name="txtEmployee" id="txtEmployee" url="hrms.htm?actionFlag=getEmpSearchSelData&multiple=false" readonly="true" /></td>
		</tr>
	</table>
	<table class="tabtable">
		<tr>
			<td width="25%" id='offType'><b><hdiits:caption	captionid="EIS.OffType" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%">
				<hdiits:select captionid="EIS.OffType" bundle="${EmpAdditionalChargesLables}" name="selOfficeType" id="OfficeType" sort="true" validation="sel.isrequired" mandatory="true" onchange="showOfficeName('location','selOfficeType','selOfficeName');displayDesignation();">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${EmpAdditionalChargesLables}" />
						<c:forEach var="OfficeTypeInfoVar" items="${OfficeTypeInfo}">
							<hdiits:option value="${OfficeTypeInfoVar.depCode}">${OfficeTypeInfoVar.depName}</hdiits:option>
						</c:forEach>
					</hdiits:option>
				</hdiits:select>
			</td>

			<td width="25%" id='offName'><b><hdiits:caption	captionid="EIS.OffNm" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%">
				<hdiits:select captionid="EIS.OffNm" bundle="${EmpAdditionalChargesLables}" name="selOfficeName" id="OfficeName" sort="true"  validation="sel.isrequired" mandatory="true" onchange="displayDesignation()">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${EmpAdditionalChargesLables}" />
				</hdiits:option>
			</hdiits:select></td>
		</tr>
		
		<tr>
			<td width="25%" id='desig'><b><hdiits:caption captionid="EIS.Desig" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select captionid="EIS.Desig" bundle="${EmpAdditionalChargesLables}" name="selDesignation" id="selDesignation" sort="false"  validation="sel.isrequired" readonly="true" mandatory="true" onchange="dateValidation();showOfficeName('post','','selOfficeName','selDesignation','selPost')">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${EmpAdditionalChargesLables}" />
						<c:forEach var="DesignationVOVar" items="${DesignationVO}">
							<hdiits:option value="${DesignationVOVar.dsgnCode}">${DesignationVOVar.dsgnName}</hdiits:option>
						</c:forEach>
					</hdiits:option>
			</hdiits:select></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption captionid="EIS.Post" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%"><hdiits:select captionid="EIS.Post" bundle="${EmpAdditionalChargesLables}" name="selPost" id="Post"	sort="true"  validation="sel.isrequired" mandatory="true" onchange="showOfficeName('role','','','','selPost','selRole')">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${EmpAdditionalChargesLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>
			<td width="25%"><b><hdiits:caption captionid="EIS.Role" bundle="${EmpAdditionalChargesLables}"></hdiits:caption></b></td>
			<td width="25%" id="selRole"></td>
		</tr>

		<tr>
			<td align="center" colspan="4">
				<hdiits:button	name="btnEmpAdditChrgDtlAdd" type="button" captionid="EIS.Add" bundle="${EmpAdditionalChargesLables}" onclick="javascript:addOrUpdateAdditnalChrgDtls('Add')" />
				<hdiits:button	name="btnEmpAdditChrgDtlUpdate" type="button" captionid="EIS.Update" bundle="${EmpAdditionalChargesLables}"	onclick="javascript:addOrUpdateAdditnalChrgDtls('Update')" style="display:NONE" /> 
				<hdiits:button	name="btnEmpAdditChrgDtlReset" type="button" captionid="EIS.Reset" bundle="${EmpAdditionalChargesLables}" onclick="resetVacantData();" />
			</td>
		</tr>
	</table>
</hdiits:fieldGroup>

	<br>
	<hdiits:fieldGroup id="additionalFieldGroupId" titleCaptionId="EIS.EmpAddChrg" bundle="${EmpAdditionalChargesLables}" collapseOnLoad="false">
	<table id='txnAddAdditionalChrgDtl' height="100%" width="100%" name="AdditionalChrg" border="1" borderColor="black" align="center" width="90%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
		<tr>
			<td style="display:none">&nbsp;</td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OrderNo" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OrderDate" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.StartDate" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.ToDate" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OffType" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OffNm" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.Post" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="eis.actions" bundle="${EmpAdditionalChargesLables}" /></label></b></td>
		</tr>
	</table>

	<c:if test="${not empty allPostingDataOnUserIdVOList}">
		<c:forEach items="${allPostingDataOnUserIdVOList}" var="allPostingDataOnUserIdVO" varStatus="x">
			<c:set var="currentXMLFileAddnlChrg" value="${xmlFilePathNameForMulAddRec[x.index]}"></c:set>
			<c:set var="postingOrderNo"	value="${allPostingDataOnUserIdVO.postingOrderNo}" />
			<fmt:formatDate var="postingOrderDate" pattern="dd/MM/yyyy"	value="${allPostingDataOnUserIdVO.postingOrderDate}" type="date" />
			<c:set var="postingOrderDate" value="${postingOrderDate}" />

			<c:forEach items="${allPostingDataOnUserIdVO.hrEisEmpPostingDtlses}" var="postingDetails" varStatus="y">

				<fmt:formatDate var="StartDate" pattern="dd/MM/yyyy" value="${postingDetails.startDate}" type="date" />
				<c:set var="StartDate" value="${StartDate}" />
				<fmt:formatDate var="ToDate" pattern="dd/MM/yyyy" value="${postingDetails.endDate}" type="date" />
				<c:set var="ToDate" value="${ToDate}" />
				<c:set var="OfficeType" value="${postingDetails.toDepartment.depName}" />
				<c:set var="OfficeName" value="${postingDetails.toLocation.locName}" />
				<c:set var="Post" value="${postingDetails.orgPostMstByToPostId.postName}" />
				<c:set var="recTypeForAdd" value="${postingDetails.typeOfRecord}" />
			</c:forEach>

			<script type="text/javascript">
						var recTypeForAdd = '${recTypeForAdd}';
						var endDateForAdd = '${ToDate}';
						var xmlFileName = '${currentXMLFileAddnlChrg}';
						var displayPostingDtlsFieldA  = new Array('${postingOrderNo}','${postingOrderDate}','${StartDate}','${ToDate}','${OfficeType}','${OfficeName}','${Post}');
						if(recTypeForAdd == 'C' && endDateForAdd != "" && endDateForAdd != null && endDateForAdd != "null") 
							addDBDataInTable('txnAddAdditionalChrgDtl','encXMLAdditionalCharge',displayPostingDtlsFieldA,xmlFileName,'','');
						else if(recTypeForAdd == 'C')
					 		addDBDataInTable('txnAddAdditionalChrgDtl','encXMLAdditionalCharge',displayPostingDtlsFieldA,xmlFileName,'editEmpAddtnalChrgRecord','');
						else
							addDBDataInTable('txnAddAdditionalChrgDtl','encXMLAdditionalCharge',displayPostingDtlsFieldA,xmlFileName,'editEmpAddtnalChrgRecord','deleteDBEmpAddtnalChrgRecord');
					
					</script>
		</c:forEach>
	</c:if>
	</hdiits:fieldGroup>
	</hdiits:fieldGroup>
	 <br>
	<br>

	<table align="center">
		<hdiits:button name="btnEmpAdditChrgDtlSubmitInDB" type="button" captionid="EIS.Submit" bundle="${EmpAdditionalChargesLables}" onclick="submitInDb()"></hdiits:button>
		<hdiits:button name="btnEmpAdditChrgDtlCancel" type="button" captionid="eis.close" bundle="${EmpAdditionalChargesLables}" onclick="window.close()"></hdiits:button>
	</table>


	<!-- end of Employee's Present Posting Details --></div>
	</div></div>

	<hdiits:validate locale="${locale}"	controlNames="txtOrderNo,dtOrderDate,selOfficeType,selOfficeName,selDesignation,selPost" />


	<script type="text/javascript">
		additionalChargeOnLoadFun();
		var todayDate = '${tDate}';
	</script>
	
	<hdiits:hidden name="hdnEmpPostingDtlsId" id="hdnEmpPostingDtlsId"></hdiits:hidden>
	<hdiits:hidden name="hdnAdditnalChrgId" id="hdnAdditnalChrgId" />
	<hdiits:hidden name="empUserId" id="empUserId" />
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}" />
	<hdiits:hidden name="departmentName" id="departmentName" />
	<hdiits:hidden name="locationName" id="locationName" />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
