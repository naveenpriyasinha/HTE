
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setBundle basename="resources.hr.posting.postingLabels" var="PrsntEmpPostngLables" scope="request" />

<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>
<c:set var="ReasonInfo" value="${resultValue.arReasonInfo}"></c:set>
<c:set var="TransfferReasonInfo" value="${resultValue.arTransfferReasonInfo}"></c:set>
<c:set var="OfficeTypeInfo" value="${resultValue.arOfficeTypeInfo}"></c:set>
<c:set var="arDesignationVO" value="${resultValue.arDesignationVO}"></c:set>
<c:set var="allPostingDataOnUserIdVOList" value="${resultValue.allPostingDataOnUserIdVOList}"></c:set>
<c:set var="xmlFilePathNameForMulAddRec" value="${resultValue.xmlFilePathNameForMulAddRec}"></c:set>
<c:set var="currentPostingDataOnHeldByUserIdVoList"	value="${resultValue.currentPostingDataOnHeldByUserIdVoList}"></c:set>
<c:set var="xmlFilePathNameForMulAddRecAddPost"	value="${resultValue.xmlFilePathNameForMulAddRecAddPost}"></c:set>
<c:set var="strAppType" value="SRVC-POSTING"></c:set>
<c:set var="selectedUserId" value="${resultValue.userId}"></c:set>
<c:set var="currentPostingData"	value="${resultValue.currentPostingData}"></c:set>
<c:set var="currentHrEisEmpPostingDtls"	value="${resultValue.currentHrEisEmpPostingDtls}"></c:set>
<c:set var="fromDeptName" value="${resultValue.fromDeptName}"></c:set>
<c:set var="frLocation" value="${resultValue.fromLocation}"></c:set>
<c:set var="frdsgnCode" value="${resultValue.frdsgnCode}"></c:set>
<c:set var="frPostId" value="${resultValue.frPostId}"></c:set>
<c:set var="frPostName" value="${resultValue.frPostName}"></c:set>
<c:set var="arTransfferTimeInfo" value="${resultValue.arTransfferTimeInfo}"></c:set>
<c:set var="setJoiningTime" value="${resultValue.setTime}"></c:set>
<c:set var="todayDate" value="${resultValue.todayDate}"></c:set>
<c:set var="recruitmentRecord" value="${resultValue.recruitmentRecord}"></c:set>
<c:set var="cmnLookupMstForRecruit" value="${resultValue.cmnLookupMstForRecruit}"></c:set>
<c:set var="EmpDsgCode" value="${resultValue.EmpDsgCode}"></c:set><!-- Added by sunil (02/07/08) for emp dsgn -->
<c:set var="lngCurrentEmpDsgnMpgEngId" value="${resultValue.lngCurrentEmpDsgnMpgEngId}"></c:set><!-- Added by sunil (02/07/08) for emp dsgn -->

<fmt:formatDate value="${todayDate}" var="tDate" pattern="dd/MM/yyyy"/>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/posting/EmpPresentPostingDtls.js"/>"></script>

<script>
function fillFromFieldsOnLoad()
{	
	var frDept='${fromDeptName}';
	
	if(frDept!='')
	{
		document.getElementById('selFromOfficeType').value = frDept;
	}
	
	var frOffName='${frLocation}';
	if(frOffName==null || frOffName=='' || frOffName=='null' || frOffName=='0')
	{
		document.getElementById('selFromOfficeName').value = '0';
	}
	else
	{
		fromLocationCombo='${frLocation}';
		showOfficeName('location','selFromOfficeType','selFromOfficeName');
	}
	
	var frdsgnCodeId='${frdsgnCode}';
	if(frdsgnCodeId==null || frdsgnCodeId=='' || frdsgnCodeId=='null' || frdsgnCodeId=='0')
	{
		document.getElementById('selFromDesignation').value = '0';	
	}
	else
	{
		checkForDesg('fromSalect','selFromOfficeType','selFromOfficeName');
		document.getElementById('selFromDesignation').value = frdsgnCodeId;	
	}
	
	postName='${frPostName}';
	var frPost='${frPostId}';
	if(frPost==null || frPost=='' || frPost=='null' || frPost=='0')
	{
		document.getElementById('selFromPost').value = '0';	
   	}
   	else
   	{
   		fromPostCombo = '${frPostId}';
    	showOfficeName('post','','selFromOfficeName','selFromDesignation','selFromPost');
   	}
   	
   	showOfficeName('role','','','','selFromPost','selFromRole');
}

	var empPresentPostingAlertMsgArr = new Array();
	empPresentPostingAlertMsgArr[0]="<fmt:message bundle='${PrsntEmpPostngLables}' key='CrntEndDateAlert'/>";
	empPresentPostingAlertMsgArr[1]="<fmt:message bundle='${PrsntEmpPostngLables}' key='StartDateAlert'/>";
	empPresentPostingAlertMsgArr[2]="<fmt:message bundle='${PrsntEmpPostngLables}' key='DosAndTodayAlert'/>";
	empPresentPostingAlertMsgArr[3]="<fmt:message bundle='${PrsntEmpPostngLables}' key='Eis.ValidStartDate'/>";
	empPresentPostingAlertMsgArr[4]="<fmt:message bundle='${PrsntEmpPostngLables}' key='DoeAndTodayAlert'/>";
	empPresentPostingAlertMsgArr[5]="<fmt:message bundle='${PrsntEmpPostngLables}' key='Eis.ValidEndDate'/>";
	empPresentPostingAlertMsgArr[6]="<fmt:message bundle='${PrsntEmpPostngLables}' key='DooAndTodayAlert'/>";
	empPresentPostingAlertMsgArr[7]="<fmt:message bundle='${PrsntEmpPostngLables}' key='DorAndTodayAlert'/>";
	empPresentPostingAlertMsgArr[8]="<fmt:message bundle='${PrsntEmpPostngLables}' key='Enterenddate'/>";
	
</script>

<hdiits:form name="frmEmpPrsntPostingDtls" validate="true" method="POST">


	<!-- start of Employee's Present Posting Details -->
	<div id="tabmenu">
		<%@ include file="../../eis/ProfessionalDetailsTab.jsp"%>
	</div>
	<div id="education" name="education">
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	 <%@ include file="../../eis/empInfo/EmployeeInfo.jsp"%>
	<!-- <table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" color="white"><u><b><fmt:message key="EIS.PrsntEmplmntPosngDtls" bundle="${PrsntEmpPostngLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>-->
	<hdiits:fieldGroup titleCaptionId="EIS.PrsntEmplmntPosngDtls" bundle="${PrsntEmpPostngLables}" >
	<table class="tabtable">

		<tr>
			<td><b><hdiits:caption captionid="EIS.TypeOfRec" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td>
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="H" onclick="checkRecType()" captionid="EIS.History" bundle="${PrsntEmpPostngLables}" />
				<hdiits:radio name="rdoTypeOfRec" id="rdoTypeOfRec" value="C" onclick="checkRecType()" captionid="EIS.Current"	bundle="${PrsntEmpPostngLables}" />
			</td>
			<!-- Added by sunil on(02/07/08)  for Emp Desgnation  -->
			<td id="EmpDsgCaptionTd" style="display:none"><b><hdiits:caption captionid="EIS.EmpDesig" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td id="EmpDsgComboTd" style="display:none">
				<hdiits:select captionid="EIS.EmpDesig" bundle="${PrsntEmpPostngLables}" name="empDesignation" id="empDesignation" sort="false" mandatory="true" validation="sel.isrequired">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
						<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
							<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
						</c:forEach>
					</hdiits:option>
				</hdiits:select>
			</td>
		</tr>
		<!-- Added by sunil on(02/07/08)  for Emp Desgnation  -->
		<tr>
			<td><b><hdiits:caption captionid="EIS.Reason"	bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.Reason" bundle="${PrsntEmpPostngLables}" name="selReason" id="selReason" sort="false" onchange="checkReason(),showEmpDesg()" validation="sel.isrequired" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select"	bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
					
					<c:forEach var="ReasonInfoVar" items="${ReasonInfo}">
						<hdiits:option value="${ReasonInfoVar.lookupName}">${ReasonInfoVar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td id="emp_trnsffer" style="display:none"><b><hdiits:caption captionid="EIS.TransfferReason" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td id="emp_trnsfferType" style="display:none">
				<hdiits:select captionid="EIS.TransfferReason" bundle="${PrsntEmpPostngLables}"	name="selTransfferReason" id="selTransfferReason" sort="false">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
					<c:forEach var="TransfferReasonInfo" items="${TransfferReasonInfo}">
						<hdiits:option value="${TransfferReasonInfo.lookupName}">${TransfferReasonInfo.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>

			<td><b><hdiits:caption captionid="EIS.TransfferTime" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.TransfferTime" bundle="${PrsntEmpPostngLables}" name="selTransfferTime" id="selTransfferTime" sort="false">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
					<c:forEach var="transfferTime" items="${arTransfferTimeInfo}">
						<hdiits:option value="${transfferTime.lookupName}">${transfferTime.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		
		<tr>
			<td><b><hdiits:caption captionid="EIS.OrderNo" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:text captionid="EIS.OrderNo" bundle="${PrsntEmpPostngLables}" name="txtOrderNo" /></td>

			<td><b><hdiits:caption captionid="EIS.OrderDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:dateTime captionid="EIS.OrderDate"	bundle="${PrsntEmpPostngLables}" name="dtOrderDate" maxvalue="01/12/2099" afterDateSelect="campareOrdDateAndTodayDate()"/></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="EIS.StartDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:dateTime captionid="EIS.StartDate"	bundle="${PrsntEmpPostngLables}" name="dtStartDate" mandatory="true" validation="txt.isdt,txt.isrequired" afterDateSelect="campareStrDateAndTodayDate();"  maxvalue="01/12/2099"/>
			</td>

			<td id="dis_ToDate" style="display:NONE"><b><hdiits:caption captionid="EIS.ToDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td id="dis_ToDate1" align="left" style="display:NONE">
				<hdiits:dateTime captionid="EIS.ToDate" bundle="${PrsntEmpPostngLables}" name="dtToDate" afterDateSelect="campareEndDateAndTodayDate();"  maxvalue="01/12/2099"/> 
			</td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="EIS.FrmOffType" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.FrmOffType" bundle="${PrsntEmpPostngLables}" name="selFromOfficeType" sort="false" validation="sel.isrequired" onchange="showOfficeName('location','selFromOfficeType','selFromOfficeName'),checkForDesg('fromSalect','selFromOfficeType','selFromOfficeName')">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
					<c:forEach var="OfficeTypeInfoVar" items="${OfficeTypeInfo}">
						<hdiits:option value="${OfficeTypeInfoVar.depCode}">${OfficeTypeInfoVar.depName}</hdiits:option>
					</c:forEach>

				</hdiits:select>
			</td>

			<td><b><hdiits:caption captionid="EIS.ToOffType" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.ToOffType" bundle="${PrsntEmpPostngLables}" name="selToOfficeType" sort="false" validation="sel.isrequired" onchange="showOfficeName('location','selToOfficeType','selToOfficeName'),checkForDesg('toSalect','selToOfficeType','selToOfficeName')" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
					<c:forEach var="OfficeTypeInfoVar" items="${OfficeTypeInfo}">
						<hdiits:option value="${OfficeTypeInfoVar.depCode}">${OfficeTypeInfoVar.depName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.FrmOffNm" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.FrmOffNm" bundle="${PrsntEmpPostngLables}" name="selFromOfficeName" id="FromOfficeName" sort="false" validation="sel.isrequired" onchange="checkForDesg('fromSalect','selFromOfficeType','selFromOfficeName')">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>

			<td><b><hdiits:caption captionid="EIS.ToOffNm" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.ToOffNm" bundle="${PrsntEmpPostngLables}" name="selToOfficeName" sort="false" validation="sel.isrequired" onchange="checkForDesg('toSalect','selToOfficeType','selToOfficeName')" mandatory="true">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
				</hdiits:select>
			</td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.FrmDesig" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.FrmDesig" bundle="${PrsntEmpPostngLables}" name="selFromDesignation" id="selFromDesignation" sort="false" validation="sel.isrequired" readonly="true"	onchange="checkStartDate(),showOfficeName('post','','selFromOfficeName','selFromDesignation','selFromPost')">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
						<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
							<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
						</c:forEach>
					</hdiits:option>
				</hdiits:select>
			</td>

			<td><b><hdiits:caption captionid="EIS.ToDesig" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
			<hdiits:select captionid="EIS.ToDesig" bundle="${PrsntEmpPostngLables}" name="selToDesignation"	id="selToDesignation" sort="false" validation="sel.isrequired" onchange="checkStartDate(),showOfficeName('post','','selToOfficeName','selToDesignation','selToPost')" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
						<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
							<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
						</c:forEach>
				</hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.FrmPost" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.FrmPost" bundle="${PrsntEmpPostngLables}" name="selFromPost" id="selFromPost" sort="false" validation="sel.isrequired" onchange="showOfficeName('role','','','','selFromPost','selFromRole')">
					<hdiits:option value="0">
						<fmt:message key="EIS.Select" bundle="${PrsntEmpPostngLables}" />
					</hdiits:option>
			</hdiits:select></td>

			<td><b><hdiits:caption captionid="EIS.ToPost" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left">
				<hdiits:select captionid="EIS.ToPost" bundle="${PrsntEmpPostngLables}" name="selToPost" id="selToPost" sort="false" validation="sel.isrequired" onchange="showOfficeName('role','','','','selToPost','selToRole')" mandatory="true">
				<hdiits:option value="0">
					<fmt:message key="EIS.Select"bundle="${PrsntEmpPostngLables}" />
				</hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td><b><hdiits:caption captionid="EIS.FrmRole" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left" id="selFromRole"></td>

			<td><b><hdiits:caption captionid="EIS.ToRole" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left" id="selToRole"></td>
		</tr>

		<tr>
			<td><b><hdiits:caption captionid="EIS.RelivingNo" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:text captionid="EIS.RelivingNo" bundle="${PrsntEmpPostngLables}" name="txtRelivingNo" /></td>

			<td><b><hdiits:caption captionid="EIS.RelivingDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			<td align="left"><hdiits:dateTime captionid="EIS.RelivingDate" bundle="${PrsntEmpPostngLables}" name="dtRelivingDate"  maxvalue="01/12/2099" afterDateSelect="camparereleivDateAndTodayDate()" /></td>
		</tr>
	</table>
	<br>
	<br>
	<table align="center">
		<tr>
			<td align="center" colspan="4">
			<hdiits:button name="btnEmpPrsntPostngDtlAdd" type="button"  captionid="EIS.Add"	bundle="${PrsntEmpPostngLables}" onclick="addPostingDtls('Add')" /> 
			<hdiits:button name="btnEmpPrsntPostngDtlUpdate" type="button" captionid="EIS.Update" bundle="${PrsntEmpPostngLables}" readonly="true" onclick="addPostingDtls('Update')" /> 
			<hdiits:button name="btnEmpPrsntPostngDtlReset" type="button" captionid="EIS.Reset" bundle="${PrsntEmpPostngLables}" onclick="resetPostingDtls(false)" /></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<br>
	
	<div style="width: 100%;height: 100%;overflow-x:scroll;overflow-y: hidden;">
		<table id='txnAddEmpPrsntPostngDtl' name="EmpPrsntPostng" border="1" borderColor="black" align="center"  cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
			<tr height="25px">
				<td align="center" style="display:NONE"><b><hdiits:caption captionid="EIS.TypeOfRec" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.Reason" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.TransfferReason" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap ><b><hdiits:caption captionid="EIS.OrderNo" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.OrderDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.StartDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.ToDate" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.FrmOffType" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.ToOffType" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.FrmOffNm" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.ToOffNm" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.FrmDesig" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.ToDesig" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.FrmPost" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="EIS.ToPost" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
				<td align="center" bgcolor="${tdBGColor}" nowrap><b><hdiits:caption captionid="eis.actions" bundle="${PrsntEmpPostngLables}"></hdiits:caption></b></td>
			</tr>
		</table>
	 </div> 


	<c:if test="${not empty allPostingDataOnUserIdVOList}">
		<c:forEach items="${allPostingDataOnUserIdVOList}" var="empPostingTuples" varStatus="x">
			<c:set var="curXMLFileName"	value="${xmlFilePathNameForMulAddRec[x.index]}"></c:set>

			<c:set var="reason"	value="${empPostingTuples.cmnLookupMst.lookupDesc}" />
			<c:set var="postingOrderNo"	value="${empPostingTuples.postingOrderNo}" />
			<fmt:formatDate var="postingOrderDate" pattern="dd/MM/yyyy"	value="${empPostingTuples.postingOrderDate}" type="date" />

			<c:set var="releavingOrderNo" value="${empPostingTuples.releavingOrderNo}" />
			<fmt:formatDate var="releavingOrderDate" pattern="dd/MM/yyyy" value="${empPostingTuples.releavingOrderDate}" type="date" />

			<c:forEach items="${empPostingTuples.hrEisEmpPostingDtlses}" var="postingDetails" varStatus="y">

				<c:set var="transferType" value="${postingDetails.cmnLookupMst.lookupDesc}" />

				<c:set var="fromDsgnId"	value="${postingDetails.orgDesignationMstByFromDsgnId.dsgnName}" />
				<c:set var="toDsgnId" value="${postingDetails.orgDesignationMstByToDsgnId.dsgnName}" />

				<c:set var="fromPostId"	value="${postingDetails.orgPostMstByFromPostId.postName}" />
				<c:set var="toPostId" value="${postingDetails.orgPostMstByToPostId.postName}" />

				<c:set var="fromdepName" value="${postingDetails.fromDepartment.depName}" />
				<c:set var="todepName" value="${postingDetails.toDepartment.depName}" />
				<c:set var="fromLocation" value="${postingDetails.fromLocation.locName}" />
				<c:set var="toLocation" value="${postingDetails.toLocation.locName}" />
				<c:set var="recType" value="${postingDetails.typeOfRecord}" />

				<fmt:formatDate var="startDate" pattern="dd/MM/yyyy" value="${postingDetails.startDate}" type="date" />
				<fmt:formatDate var="endDate" pattern="dd/MM/yyyy" value="${postingDetails.endDate}" type="date" />

			</c:forEach>

			<script type="text/javascript">
					var PostingDtlsXmlFileName = '${curXMLFileName}';
					var recTypeForAdd = '${recType}';
					var endDateForAdd = '${endDate}';
					
					var displayPostingDtlsFieldA  = new Array('${recType}','${reason}','${transferType}','${postingOrderNo}','${postingOrderDate}','${startDate}','${endDate}','${fromdepName}','${todepName}','${fromLocation}','${toLocation}','${fromDsgnId}','${toDsgnId}','${fromPostId}','${toPostId}');  
					
					if(recTypeForAdd=='C' && endDateForAdd!="" && endDateForAdd!=null && endDateForAdd!='null') 
						rowId = addDBDataInTable('txnAddEmpPrsntPostngDtl','addedPostXML',displayPostingDtlsFieldA,PostingDtlsXmlFileName,'', '');					
					else if(recTypeForAdd=='C')
					 	rowId = addDBDataInTable('txnAddEmpPrsntPostngDtl','addedPostXML',displayPostingDtlsFieldA,PostingDtlsXmlFileName,'editDBPostingDtls', '');						 
					else
					 	rowId = addDBDataInTable('txnAddEmpPrsntPostngDtl','addedPostXML',displayPostingDtlsFieldA,PostingDtlsXmlFileName,'editDBPostingDtls', 'deleteDBPostingDtls');						 	
					 
					 document.getElementById(rowId).cells[1].style.display = 'NONE';
					 
					 if(recTypeForAdd=='C' && (endDateForAdd=="" || endDateForAdd==null || endDateForAdd=='null'))
					 {
					 	flagForAdd=true;
					 	rowIdForAdd=rowId;
					 }
			</script>
		</c:forEach>
	</c:if> 
	<br>
	<table align="center">
		<hdiits:button name="btnEmpPrsntPostngDtlSubmitInDB" type="button" captionid="EIS.Submit" bundle="${PrsntEmpPostngLables}" onclick="SubmitAction()"></hdiits:button>
		<hdiits:button name="btnEmpPrsntPostngDtlCancel" type="button" captionid="eis.close" bundle="${PrsntEmpPostngLables}" onclick="window.close();"></hdiits:button>
	</table>
	<br>
	
	<!--<table bgcolor="#386CB7" align="center"  width="100%">
		<tr align="center">
			<td>
				<font class="Label3" align="center" color="white"><u><b><fmt:message key="EIS.EmpAddChrg" bundle="${PrsntEmpPostngLables}"/></b></u><span class="UserText" lang="en-us"></span></font>
			</td>
		</tr> 
	</table>-->
	<hdiits:fieldGroup titleCaptionId="EIS.EmpAddChrg" bundle="${PrsntEmpPostngLables}" >
	<table id='txnAddAdditionalChrgDtl' name="txnAddAdditionalChrgDtl" border="1" borderColor="black" align="center" width="100%" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}">
		<tr>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OrderNo" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OrderDate" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.StartDate" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.ToDate" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OffType" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.OffNm" bundle="${PrsntEmpPostngLables}" /></label></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><label><hdiits:caption	captionid="EIS.Post" bundle="${PrsntEmpPostngLables}" /></label></b></td>
		</tr>
	</table>

	<c:if test="${not empty currentPostingDataOnHeldByUserIdVoList}">
		<c:forEach items="${currentPostingDataOnHeldByUserIdVoList}" var="AdditionalPost" varStatus="x">
			<c:set var="currentXMLFileAddnlChrg" value="${xmlFilePathNameForMulAddRecAddPost[x.index]}"></c:set>
			<c:set var="postingOrderNo" value="${AdditionalPost.postingOrderNo}" />
			<fmt:formatDate var="postingOrderDate" pattern="dd/MM/yyyy"	value="${AdditionalPost.postingOrderDate}" type="date" />

			<c:forEach items="${AdditionalPost.hrEisEmpPostingDtlses}" var="postingDetails" varStatus="y">
				<fmt:formatDate var="StartDate" pattern="dd/MM/yyyy" value="${postingDetails.startDate}" type="date" />
				<fmt:formatDate var="ToDate" pattern="dd/MM/yyyy" value="${postingDetails.endDate}" type="date" />
				<c:set var="OfficeType"	value="${postingDetails.toDepartment.depName}" />
				<c:set var="OfficeName" value="${postingDetails.toLocation.locName}" />
				<c:set var="Post" value="${postingDetails.orgPostMstByToPostId.postName}" />
			</c:forEach>

			<script type="text/javascript">
					var xmlFileName = '${currentXMLFileAddnlChrg}';
					var displayPostingDtlsFieldA  = new Array('${postingOrderNo}','${postingOrderDate}','${StartDate}','${ToDate}','${OfficeType}','${OfficeName}','${Post}');
					addDBDataInTable('txnAddAdditionalChrgDtl','encXMLAdditionalCharge',displayPostingDtlsFieldA,xmlFileName,'','');
				</script>
		</c:forEach>
	</c:if> 
	</hdiits:fieldGroup>
	<!-- end of Employee's Present Posting Details --></div>
	</div></div>
	<hdiits:hidden name="hdnEmpPostingId" id="hdnEmpPostingId"></hdiits:hidden>
	<hdiits:hidden name="hdnEmpPostingDtlsId" id="hdnEmpPostingDtlsId"></hdiits:hidden>
	<hdiits:hidden name="userId" id="userId" default="${selectedUserId}"></hdiits:hidden>
	<hdiits:hidden name="hdnEngEmpDesgMpgId" id="hdnEngEmpDesgMpgId"></hdiits:hidden>		
	<hdiits:hidden name="lngCurrentEmpDsgnMpgEngId" id="lngCurrentEmpDsgnMpgEngId" default="${lngCurrentEmpDsgnMpgEngId}"></hdiits:hidden>	

	<script type="text/javascript">
		
		initializetabcontent("maintab");

		document.frmEmpPrsntPostingDtls.dtOrderDate.readOnly = true;
		document.frmEmpPrsntPostingDtls.dtStartDate.readOnly = true;
		document.frmEmpPrsntPostingDtls.dtRelivingDate.readOnly = true;
		document.frmEmpPrsntPostingDtls.dtToDate.readOnly = true;
		
		document.frmEmpPrsntPostingDtls.selToDesignation.disabled=true;
		
		var checkHistoryRec=document.frmEmpPrsntPostingDtls.rdoTypeOfRec;
		checkHistoryRec[1].checked=true;
		recType=checkHistoryRec[1].value;
		fillFromFieldsOnLoad();
		var todayDate = '${tDate}';
		
		var recruitReasonLookupName = '${cmnLookupMstForRecruit.lookupName}';
		var recruitReasonLookupDesc = '${cmnLookupMstForRecruit.lookupDesc}';
		
		var typeOfRecruitmentRecord = '${recruitmentRecord.typeOfRecord}';
		
		var toPostId = '${recruitmentRecord.orgPostMstByToPostId.orgPostMst.postId}';
		
		if (toPostId != "")
			typeOfRecruitmentRecord = '';
		
		if (typeOfRecruitmentRecord == 'C')
			createOptionForReason(recruitReasonLookupName,recruitReasonLookupDesc);
			
		var empDsgCode=	'${EmpDsgCode}';
		if(empDsgCode!='' && empDsgCode!=null)
		{
			document.getElementById('empDesignation').value = empDsgCode;	
		}
	</script>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="empDesignation,selReason,dtStartDate,selToOfficeType,selToOfficeName,selToDesignation,selToPost" />


</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
