<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"
	src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}" />
<c:set var="ATOUserList" value="${resValue.ATOUserList}" />
<c:set var="empList" value="${resValue.empList}"></c:set>

<script type="text/javascript" defer="defer">
	$(document).ready(function() {
		alert('document ready');
	});
</script>
<style>
 .tabcontentstyle select,input#descSign, input#descPhoto {
    max-width: 200px !important;
}
</style>


<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post">

	<input type="hidden" id="User" name="User" value="${resValue.User}" />
	<input type="hidden" id="Use" name="Use" value="${resValue.Use}" />
	<input type="hidden" id="ZPFormStatus" name="ZPFormStatus"
		value="${resValue.ZPFormStatus}" />

	<div id="tabmenu" align="left">
		<ul id="maintab">

			<li class="selected" id="personalDtls"><a href="#"
				rel="tcontent1"><fmt:message key="CMN.EMPDETAILS"
						bundle="${dcpsLables}"></fmt:message> </a></li>
			<li id="officeDtls"><a href="#" rel="tcontent2"
				onfocus="checkMinDtls();"><fmt:message key="CMN.OFFDETAILS"
						bundle="${dcpsLables}"></fmt:message></a></li>
			<li id="otherDtls"><a href="#" rel="tcontent3"
				onfocus="checkMinDtls();"><fmt:message
						key="CMN.BANKDCPSGPFDETAILS" bundle="${dcpsLables}"></fmt:message></a></li>
			<li id="otherDtls"><a href="#" rel="tcontent4"
				onfocus="checkMinDtls();"><fmt:message key="CMN.GISDETAILS"
						bundle="${dcpsLables}"></fmt:message></a></li>
			<li id="nomineeDtls"><a href="#" rel="tcontent5"
				onfocus="checkMinDtls();"><fmt:message key="CMN.NOMINEEDETAILS"
						bundle="${dcpsLables}"></fmt:message></a></li>
			<li id="photoAndSignDtls"><a href="#" rel="tcontent6"
				onfocus="checkMinDtls();"><fmt:message
						key="CMN.PHOTOSIGNDETAILS" bundle="${dcpsLables}"></fmt:message></a></li>


		</ul>
	</div>
	<input type="hidden" id="txtDdoCode" name="txtDdoCode"
		value="${resValue.DDOCODE}" />
	<input type="hidden" id="txtUserType" name="txtUserType"
		value="${resValue.UserType }" />
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/NewRegistrationPersonalDetails.jsp" /></div>

		<!-- Office Details Page -->
		<div id="tcontent2" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/NewRegistrationOfficeDetails.jsp" /></div>

		<!-- Other Details Page -->
		<div id="tcontent3" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/NewRegistrationOtherDetails.jsp" /></div>

		<!-- GIS Details Page -->
		<div id="tcontent4" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/NewRegistrationGISDetails.jsp" /></div>

		<!-- Nominee Details Page -->
		<div id="tcontent5" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/dcpsnomineedtls.jsp" /></div>

		<!-- Photo Signature Details Page -->
		<div id="tcontent6" class="tabcontent"><jsp:include
				page="/WEB-INF/jsp/dcps/pensionerPhotoSignAttachment.jsp" /></div>
	</div>
	<br />

	<input type="hidden" id="hidSearchFromDDO"
		value="${resValue.lStrFromSearch}" />

	<c:choose>
		<c:when test="${resValue.lStrFromSearch == 'Yes'}">
			<div align="center">
				<hdiits:button name="btnClose" id="btnClose" type="button"
					captionid="BTN.BACK" bundle="${dcpsLables}"
					onclick="ReturnToSearch();" />
			</div>
		</c:when>
		<c:otherwise>
			<table width="100%">
				<tr>
					<td>
						<table align="left" cellpadding="0" cellspacing="0" width="70%"
							border="0">

							<c:choose>
								<c:when test="${resValue.EditForm == 'N'}">
									<tr>
										<td>Reason For Rejection</td>

										<td width="80%"><input type="text"
											id="txtApproverRemarks" name="txtApproverRemarks" size="100"
											value="" /></td>
									</tr>
									<%--
								<tr>
									<td colspan="2" style="font-style: italic; color: red"><fmt:message
										key="CMN.FORMFORWARDTOTOFORDCPSMSG" bundle="${dcpsLables}"></fmt:message></td>
								</tr>
								--%>
								</c:when>
							</c:choose>
						</table>
					</td>
				</tr>

				<tr>
					<td></td>
				</tr>

				<tr>
					<td>
						<table align="right" cellspacing="4" cellpadding="4" width="100%"
							border="0">
							<c:choose>
								<c:when test="${resValue.User == 'ZPDDOAsst'}">

									<c:choose>
										<c:when test="${EMPVO==null}">
											<tr>
												<td><input id="printPerformaA" class="buttontag"
													type="button" align="center" size="5" maxlength="5"
													value="Print Performa A" onclick="openPerformaA();"
													name="printPerformaA" style="width: 120px;" /></td>
												<td><input id="printPerformaB" class="buttontag"
													type="button" align="center" size="5" maxlength="5"
													value="Print Performa B" onclick="openPerformaB();"
													name="printPerformaB" style="width: 120px;" /></td>

												<td id="backBtn" align="center"><hdiits:button
														name="btnBack" id="btnBack" type="button"
														captionid="BTN.BACK" bundle="${dcpsLables}"
														onclick="ReturnLoginPage();" /></td>

												<td id="savebtn" align="center" style="display: block;"><hdiits:button
														name="btnSaveData" id="btnSaveData" type="button"
														captionid="BTN.SAVEASDRAFT" bundle="${dcpsLables}"
														onclick="SaveDataUsingAjaxForDraft();" /></td>

												<td><input type="hidden" id="ForwardToPost"
													name="ForwardToPost" value="${UserList[0]}" /> <hdiits:button
														name="BTN.FORWARDTODDO" id="btnForward" type="button"
														captionid="BTN.FORWARDTODDO" bundle="${dcpsLables}"
														style="width:100%"
														onclick="forwardRequestAfterValidation(0);" /></td>

												<td style="display: none"><input type="hidden"
													id="ApproveToPost" name="ApproveToPost"
													value="${ATOUserList[0]}" /> <hdiits:button
														name="BTN.APPROVE" style="width:100%" id="btnForward"
														type="button" captionid="BTN.APPROVE"
														bundle="${dcpsLables}"
														onclick="forwardRequestAfterValidation(1);" /></td>

											</tr>
										</c:when>
										<c:otherwise>
											<tr>

												<td><input id="printPerformaA" class="buttontag"
													type="button" align="center" size="5" maxlength="5"
													value="Print Performa A" onclick="openPerformaA();"
													name="printPerformaA" style="width: 120px;" /></td>
												<td><input id="printPerformaB" class="buttontag"
													type="button" align="center" size="5" maxlength="5"
													value="Print Performa B" onclick="openPerformaB();"
													name="printPerformaB" style="width: 120px;" /></td>

												<td id="backBtn" align="center"><hdiits:button
														name="btnBack" id="btnBack" type="button"
														captionid="BTN.BACK" bundle="${dcpsLables}"
														onclick="ReturnToDrafts();" /></td>

												<td align="center" id="updatebtn"><hdiits:button
														name="btnUpdatedataForUpdateTotally"
														id="btnUpdatedataForUpdateTotally" type="button"
														captionid="BTN.UPDATE" bundle="${dcpsLables}"
														onclick="updateAfterValidationForUpdateTotally('${EMPVO.dcpsEmpId}');" />
												</td>

												<td><input type="hidden" id="ForwardToPost"
													name="ForwardToPost" value="${UserList[0]}" /> <hdiits:button
														name="BTN.FORWARDTODDO" id="btnForwardForUpdateTotally"
														type="button" captionid="BTN.FORWARDTODDO"
														bundle="${dcpsLables}" style="width:100%"
														onclick="forwardRequestAfterValidationforUpdateTotally('${EMPVO.dcpsEmpId}',0);" />
												</td>

												<td style="display: none"><input type="hidden"
													id="ApproveToPost" name="ApproveToPost"
													value="${ATOUserList[0]}" /> <hdiits:button
														name="BTN.APPROVE" style="width:100%" id="btnForward"
														type="button" captionid="BTN.APPROVE"
														bundle="${dcpsLables}"
														onclick="forwardRequestAfterValidationforUpdateTotally('${EMPVO.dcpsEmpId}',1);" /></td>
											</tr>
										</c:otherwise>
									</c:choose>

								</c:when>

								<c:when test="${resValue.User == 'ZPDDO'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedForms();" /></td>

										<td align="center" id="updatebtn"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequest('${EMPVO.dcpsEmpId}');" /></td>

										<input type="hidden" id="hidDcpsOrGpf" name="hidDcpsOrGpf"
											value="${EMPVO.dcpsOrGpf}" />

										<c:choose>

											<c:when test="${EMPVO.dcpsOrGpf == '78'}">

												<!-- 
													<input type="hidden" id="ForwardToPost"
														name="ForwardToPost" value="${UserList[0]}" />
												 -->

												<td><input type="hidden" id="ForwardToPost"
													name="ForwardToPost" value="${UserList[0]}" /> <hdiits:button
														name="BTN.APPROVE" style="width:100%"
														id="btnApproveForUpdateTotally" type="button"
														captionid="BTN.APPROVE" bundle="${dcpsLables}"
														onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" /></td>

												<!--<td><hdiits:button name="BTN.FORWARD"
												id="btnForwardForUpdateTotally" type="button"
												readonly="true" captionid="BTN.FORWARD"
												bundle="${dcpsLables}"
												onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" /></td>

										-->
											</c:when>

											<c:otherwise>

												<td><hdiits:button name="BTN.APPROVE"
														style="width:100%" id="btnApproveForUpdateTotally"
														type="button" readonly="true" captionid="BTN.APPROVE"
														bundle="${dcpsLables}" onclick="" /></td>

												<!--<td><input type="hidden" id="ForwardToPost"
												name="ForwardToPost" value="${UserList[0]}" /> <hdiits:button
												name="BTN.FORWARD" id="btnForwardForUpdateTotally"
												type="button" captionid="BTN.FORWARD" bundle="${dcpsLables}"
												style="width:100%"
												onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" /></td>
										-->
											</c:otherwise>
										</c:choose>

										<!--<td><input type="hidden" id="formPrintedOrNot" value="NO" />
									<hdiits:button name="btnPrintForm1" id="btnPrintForm1"
										type="button" captionid="BTN.PRINTFORM1"
										bundle="${dcpsLables}"
										onclick="printForm1ByDDO('${EMPVO.dcpsEmpId}');" /></td>
								-->
									</tr>
								</c:when>

								<c:when
									test="${resValue.User == 'ReportingDDO' && resValue.Use == 'Forward'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedFormsRepoDDOForward();" /></td>

										<td align="center" id="btnReject"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequestZP('${EMPVO.dcpsEmpId}');" /> <input
											type="hidden" id="ForwardToPost" name="ForwardToPost"
											value="${UserList[0]}" /></td>




										<c:choose>

											<c:when test="${EMPVO.dcpsOrGpf == '78'}">
												<td><input type="hidden" id="hidDcpsOrGpf"
													name="hidDcpsOrGpf" value="${EMPVO.dcpsOrGpf}" />
												<hdiits:button name="BTN.APPROVE" style="width:100%"
														id="btnApproveForUpdateTotally" type="button"
														captionid="BTN.APPROVE" bundle="${dcpsLables}"
														onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" /></td>
											</c:when>
											<c:otherwise>
												<td><hdiits:button name="BTN.APPROVE"
														style="width:100%" id="btnApprove" type="button"
														captionid="BTN.APPROVE" bundle="${dcpsLables}"
														onclick="approveFormZP('${EMPVO.dcpsEmpId}');" /></td>

											</c:otherwise>
										</c:choose>

										<td><input type="hidden" id="formPrintedOrNot" value="NO" />
											<hdiits:button name="btnPrintForm1" id="btnPrintForm1"
												type="button" captionid="BTN.PRINTFORM1"
												bundle="${dcpsLables}"
												onclick="printForm1ByDDO('${EMPVO.dcpsEmpId}');" /></td>

									</tr>

								</c:when>

								<c:when
									test="${resValue.User == 'ReportingDDO' && resValue.Use == 'Approval'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedFormsRepoDDOApproval();" /></td>

										<td align="center" id="btnReject"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequestZP('${EMPVO.dcpsEmpId}');" /></td>

										<td><c:choose>
												<c:when test="${EMPVO.dcpsOrGpf == '78'}">

													<input type="hidden" id="hidDcpsOrGpf" name="hidDcpsOrGpf"
														value="${EMPVO.dcpsOrGpf}" />
													<hdiits:button name="BTN.APPROVE" style="width:100%"
														id="btnApproveForUpdateTotally" type="button"
														captionid="BTN.APPROVE" bundle="${dcpsLables}"
														onclick="ForwardRequestDDO('${EMPVO.dcpsEmpId}');" />


												</c:when>
												<c:otherwise>
													<hdiits:button name="BTN.APPROVE" style="width:100%"
														id="btnApprove" type="button" captionid="BTN.APPROVE"
														bundle="${dcpsLables}"
														onclick="approveFormZP('${EMPVO.dcpsEmpId}');" />
												</c:otherwise>
											</c:choose></td>

										<!--<td><input type="hidden" id="formPrintedOrNot" value="NO" />
									<hdiits:button name="btnPrintForm1" id="btnPrintForm1"
										type="button" captionid="BTN.PRINTFORM1"
										bundle="${dcpsLables}"
										onclick="printForm1ByDDO('${EMPVO.dcpsEmpId}');" /></td>
								-->
									</tr>
								</c:when>

								<c:when
									test="${resValue.User == 'FinalDDO' && resValue.Use == 'Forward'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedFormsFinalDDOForward();" /></td>

										<td align="center" id="btnReject"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequestZP('${EMPVO.dcpsEmpId}');" /></td>

										<td><input type="hidden" id="ForwardToPost"
											name="ForwardToPost" value="${UserList[0]}" /> <hdiits:button
												name="BTN.FORWARD" id="btnForwardReporting" type="button"
												captionid="BTN.FORWARD" bundle="${dcpsLables}"
												style="width:100%"
												onclick="ForwardRequestFinalDDO('${EMPVO.dcpsEmpId}');" /></td>

									</tr>

								</c:when>

								<c:when
									test="${resValue.User == 'FinalDDO' && resValue.Use == 'Approval'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedFormsFinalDDOApproval();" /></td>

										<td align="center" id="btnReject"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequestZP('${EMPVO.dcpsEmpId}');" /></td>

										<td><hdiits:button name="BTN.APPROVE" style="width:100%"
												id="btnApprove" type="button" captionid="BTN.APPROVE"
												bundle="${dcpsLables}"
												onclick="approveFormZP('${EMPVO.dcpsEmpId}');" /></td>

									</tr>

								</c:when>

								<c:when
									test="${resValue.User == 'SpecialDDO' && resValue.Use == 'Approval'}">
									<tr>
										<td id="backBtn" align="center"><hdiits:button
												name="btnBack" id="btnBack" type="button"
												captionid="BTN.BACK" bundle="${dcpsLables}"
												onclick="ReturnToForwardedFormsSpecialDDOApproval();" /></td>

										<td align="center" id="updatebtn"><hdiits:button
												name="btnReject" id="btnReject" type="button"
												captionid="BTN.REJECT" bundle="${dcpsLables}"
												onclick="rejectRequestZP('${EMPVO.dcpsEmpId}');" /></td>

										<td><hdiits:button name="BTN.APPROVE" style="width:100%"
												id="btnForward" type="button" captionid="BTN.APPROVE"
												bundle="${dcpsLables}"
												onclick="approveFormZP('${EMPVO.dcpsEmpId}');" /></td>

									</tr>

								</c:when>
							</c:choose>
							<tr>
							</tr>

						</table>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
	<hdiits:validate locale='<%=(String) session.getAttribute("locale")%>' />
</hdiits:form>

<ajax:select source="cmbBankName" target="cmbBranchName"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popBrnchNms"
	parameters="cmbBankName={cmbBankName}"
	preFunction="showProgressbarForBank"
	postFunction="hideProgressbarForBank">
</ajax:select>

<ajax:select source="listParentDept" target="cmbDesignation"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDesigsForPFDAndCadre"
	parameters="listParentDept={listParentDept},cmbCadre={cmbCadre},cmbPayCommission={cmbPayCommission}"
	postFunction="enableReasonForPFD">
</ajax:select>

<ajax:select source="listParentDept" target="cmbCadre"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popCadres"
	parameters="cmbFieldDepartment={listParentDept}">
</ajax:select>

<ajax:select source="cmbCurrentPost" target="cmbCurrentOffice"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popOfficesForPost"
	parameters="cmbCurrentPost={cmbCurrentPost}"
	postFunction="getOfficeDetails">
</ajax:select>

