<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseConstants" var="pensionConstants" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionPayAlerts" var="pensionAlerts" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="curRole" value="${resValue.lStrToRole}"/>
<c:set var="showCaseFor" value="${resValue.showCaseFor}"/>
<c:set var="counter" value="1"></c:set>
<c:if test="${showCaseFor == 6 or curRole == '365453'}">
	<c:set var="disabled" value="disabled" />
</c:if>
<style type="text/css">
.unSchCol{
color:red;
}
</style>
<script type="text/javascript">
statusAwaited = '<fmt:message key="IDENT.AWAITED" bundle="${pensionConstants}"></fmt:message>';
statusReminder = '<fmt:message key="IDENT.REMINDER" bundle="${pensionConstants}"></fmt:message>';
bankNameAlert = '<fmt:message key="PPMT.ENTR_BANKNAME" bundle="${pensionAlerts}"></fmt:message>';
bankBranchAlert = '<fmt:message key="PPMT.ENTR_BANKBRANCH" bundle="${pensionAlerts}"></fmt:message>';
acNoAlert = '<fmt:message key="PPMT.ENTR_ACNO" bundle="${pensionAlerts}"></fmt:message>';
callDateAlert = '<fmt:message key="PPMT.EMPTY_CALLDATE" bundle="${pensionAlerts}"></fmt:message>';
callTimeAlert = '<fmt:message key="PPMT.EMPTY_CALLTIME" bundle="${pensionAlerts}"></fmt:message>';
brnchCodeAlert = '<fmt:message key="PPMT.EMPTY_BNRCHCODE" bundle="${pensionAlerts}"></fmt:message>';
identAuditor = '<fmt:message key="CASEFOR.IDENTAUDITOR" bundle="${pensionConstants}"></fmt:message>';
</script>
<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>
<script type="text/javascript"  src="script/pensionpay/uploadcases.js"></script>
<script type="text/javascript" src="script/pensionpay/schedulecases.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script>
function showPensinCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
function showCheckList(url)
{
	var newWindow;
	var height = screen.height - 200;
	var width =500;
	var screenPosY = 100;
	var screenPosX = (screen.width - width)/2;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top="+screenPosY+",left="+screenPosX;
	newWindow = window.open(url, "checkList", urlstyle);
}
</script>
<hdiits:form name="UploadedCases" method="post" validate="">
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="VOList" value="${resValue.lLstSavedCaseVO}" />
<jsp:include page="/WEB-INF/jsp/pensionpay/searchPensionCase.jsp" />
<fieldset style="width: 100%" class="tabstyle">
	<legend id="headingMsg">
		<c:if test="${curRole == '365453'}">
			<b><fmt:message key="PPMT.RCVONLCASES" bundle="${pensionLabels}"></fmt:message></b>
		</c:if>
		<c:if test="${curRole == '365451' or curRole == '365452'}">
			<b><fmt:message key="PPMT.SCHEDULEIDENTIFICATION" bundle="${pensionLabels}"></fmt:message></b>
		</c:if>
	 </legend>
<div class="scrollablediv" >
	<display:table list="${VOList}" id="vo" requestURI="" pagesize="<%= Constants.PAGE_SIZE %>"  export="false" style="width:100%" partialList="true" 
						 offset="1" excludedParams="ajax" size="${totalRecords}" sort="external" defaultsort="3" defaultorder="descending" cellpadding="5" >	
		<c:if test="${showCaseFor != 6}">
		<display:column title="<input name='chkSelect'type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="width:2%">
			<input type="checkbox" align="middle" name="chkbxPesnionerNo"
				id="chkbxPesnionerNo_${vo_rowNum}"
				onclick="" value="${vo.ppoNo}_${vo_rowNum}" />
			<input type="hidden" name="hdnPensionerId${vo_rowNum}" id="hdnPensionerId${vo_rowNum}" value="${vo.pensionerId}"/>
			<input type="hidden" name="hdnPnsnrDtlsId${vo_rowNum}" id="hdnPnsnrDtlsId${vo_rowNum}" size="30" value="${vo.pensionerDtlsId}">
			<input type="hidden" name="hdnpnsnrqstid${vo_rowNum}" id="hdnpnsnrqstid${vo_rowNum}" value="${vo.pensionRequestId}" >
			<input type="hidden" name="hdnAuditorPostId${vo_rowNum}" id="hdnAuditorPostId${vo_rowNum}" value="${vo.auditorPostId}"/>
			<input type="hidden" name="hdnSelectedPnsrId" id="hdnSelectedPnsrId" />
		</display:column>
		</c:if>
		<display:column titleKey="PPMT.PPONO" headerClass="datatableheader"
			sortable="true" style="width:10%;text-align:left;">
			<c:choose>
			<c:when test="${curRole == '365451' and showCaseFor == '5'}">
				<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&showApprove=Y&callDate=${vo.callDate}&callSlotNo=${vo.slotNo}&showReadOnly=Y&pensionerId=${vo.pensionerId}&showCaseFor=${showCaseFor}&currRole=${curRole}"></c:set>
					<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			</c:when>
			<c:when test="${curRole == '365453' or showCaseFor == 6}">
			<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&showApprove=Y&showReadOnly=Y&pensionerId=${vo.pensionerId}&showCaseFor=${showCaseFor}&currRole=${curRole}"></c:set>
			<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			</c:when>
			<c:otherwise>
			<c:set var="URLLink" value="ifms.htm?actionFlag=loadPhyPenCase&showApprove=Y&showReadOnly=Y&callDate=${vo.callDate}&callSlotNo=${vo.slotNo}&pensionerId=${vo.pensionerId}&showCaseFor=${showCaseFor}&currRole=${curRole}"></c:set>
			<a href="javascript:void(0)" onclick="javascript:showPensinCase('${URLLink}');">
			</c:otherwise>
			</c:choose>
			<div id="lblPpoNo${vo_rowNum}">${vo.ppoNo}</div></a>
		</display:column>	 
		<display:column titleKey="PPMT.PENSIONERNAME" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left;" >
			<c:choose>
			<c:when test="${vo.deathDate != null}">
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.familyMemName}</label>
			</c:when>
			<c:otherwise>
			<label id="lblPnsrName${vo_rowNum}" name="lblPnsrName${vo_rowNum}">${vo.pnsrName}</label>
			</c:otherwise>
			</c:choose>
		</display:column>
		<c:if test="${curRole == '365453'}">
			<display:column titleKey="PPMT.REGISTARTIONNO" headerClass="datatableheader"
				sortable="true" style="width:20%;text-align:left;" ><input type="text" name="txtRegNo${vo_rowNum}" id="txtRegNo${vo_rowNum}"size="30" value="${vo.regNo}" disabled>
			</display:column>	
		</c:if>
		<c:if test="${curRole == '365452' or curRole == '365451' or curRole == '365450'}">
			<display:column titleKey="PPMT.DOR" headerClass="datatableheader" 
			sortable="true" style="width:20%" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.retirementDate}"/>
			</display:column>
		</c:if>		
		<c:if test="${curRole == '365452' or curRole == '365451' or curRole == '365450'}">
			<display:column titleKey="PPMT.PSD" headerClass="datatableheader"
			sortable="true" style="width:20%" ><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.commencementDate}"/>
			</display:column>
		</c:if>
		<c:if test="${curRole == '365453'}">
		<!-- File Uploaded Date is mapped with ppo inward date of trn_pension_rqst_hdr -->
		<!-- Physical Receipt Date is mapped with ppo_reg_date of trn_pension_rqst_hdr -->
			<display:column titleKey="PPMT.FILEUPLDDATE" headerClass="datatableheader"
				sortable="true" style="width:15%;text-align:center;" ><input style="text-align:center;" type="text" name="txtFileUpldDate_${vo_rowNum}" id="txtFileUpldDate_${vo_rowNum}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy h:mm a" value="${vo.ppoInwardDate}"/>" size="20" disabled>
			</display:column>
			<display:column titleKey="PPMT.PHYPPORCVDDATE" headerClass="datatableheader"
				sortable="true" style="width:15%;text-align:center;" ><input style="text-align:center;" type="text" name="txtPhyPpoRcvdDt_${vo_rowNum}" id="txtPhyPpoRcvdDt_${vo_rowNum}" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.ppoRegDate}"/>" size="12" disabled>
			</display:column>
		</c:if>
		<display:column titleKey="PPMT.BANKBRNCODE" headerClass="datatableheader"
			style="width:10%;text-align:center" sortable="true">
			<input type="text" name="txtBankBranchCode${vo_rowNum}" id="txtBankBranchCode${vo_rowNum}" size="12" onblur="getBnkBrnchNameFrmBnkCode(this)" value="${vo.branchCode}" ${disabled}/>
		</display:column>
		<c:if test="${curRole == '365453' or curRole == '365452' or curRole == '365451' or curRole == '365450'}">
			<display:column titleKey="PPMT.BANKNAME" headerClass="datatableheader" style="width:20%;text-align:left;" sortable="true">
			<select name="cmbBankName${vo_rowNum}" id="cmbBankName${vo_rowNum}" onChange="getBranchNameFromBankCode(this);"  ${disabled}>
				<option value='-1'>---Select---</option>
				<c:forEach var="Bank" items="${resValue.BankList}">
				<c:choose>
							<c:when test="${vo.bankCode == Bank.id}">
								<option selected="true" value='${Bank.id}'>
									<c:out value="${Bank.desc}"></c:out>
								</option>
							</c:when>
							<c:otherwise>
								<option value='${Bank.id}'>
									<c:out value="${Bank.desc}"></c:out>
								</option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</select>
			</display:column>
		</c:if>
		<display:column titleKey="PPMT.BANKBRNNAME" headerClass="datatableheader"
			 style="width:20%;text-align:left;" >
			<select name="cmbBankBrnchName${vo_rowNum}"  id="cmbBankBrnchName${vo_rowNum}" onChange="getAuditorNameFromBranchCode(this)" ${disabled}>
				<option value='-1'>---Select---</option>
				<c:if test="${vo.branchCode != null}">
					<option value="${vo.branchCode}" selected="selected"><c:out value="${vo.branchName}"/></option>
				</c:if>
			</select>
		</display:column>	
		<display:column titleKey="PPMT.ACCOUNTNO" headerClass="datatableheader"
		  style="width:20%;text-align:left;" ><input type="text" name="txtAccountNo${vo_rowNum}" id="txtAccountNo${vo_rowNum}" size="12" value="${vo.accountNo}" ${disabled}>
		</display:column>		

		<display:column titleKey="PPMT.AUDITORNAME" headerClass="datatableheader"
			style="width:20%;text-align:left;" ><input type="text" name="txtAuditorName${vo_rowNum}" id="txtAuditorName${vo_rowNum}" disabled = "disabled" value="${vo.auditorFname} ${vo.auditorMname} ${vo.auditorLname}" >
		</display:column>	

 		<c:if test="${curRole == '365453'}">										
			<display:column titleKey="PPMT.CASETYPE" headerClass="datatableheader"
			sortable="true" style="width:20%;text-align:left;" >
			<c:if test="${vo.caseType == 'R'}">
				<c:out value="Regular" />
			</c:if>
			<c:if test="${vo.caseType == 'P'}">
				<c:out value="Provisional" />
			</c:if>
			<c:if test="${vo.caseType == 'C'}">
				<c:out value="Revised" />
			</c:if>
			<c:if test="${vo.caseType == 'T'}">
				<c:out value="Transferred" />
			</c:if>
			</display:column>	
		<display:column titleKey="PPMT.RECEIVINGMODE" headerClass="datatableheader"
			sortable="true" style="width:20%" >${vo.inwardMode}
		</display:column>
</c:if>			
				
			<c:if test="${curRole == '365452' or curRole == '365451' or curRole == '365450'}">
				
				<display:column titleKey="PPMT.CALLDATE" headerClass="datatableheader" sortable="true">
				<c:choose>
				<c:when test="${vo.callDate == null}">
					<input type="text" name="txtCalledDate${vo_rowNum}" id="txtCalledDate${vo_rowNum}" onblur="validateCallDate(${vo_rowNum});" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.callDate}"/>" size="12" readOnly  style="text-align:center">&nbsp;<img id="callDatePicker${vo_rowNum}" src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtCalledDate${vo_rowNum}",375,570)'style="cursor: pointer;display:none;" />
				</c:when>
				<c:otherwise>
					<input type="text" name="txtCalledDate${vo_rowNum}" id="txtCalledDate${vo_rowNum}" onblur="validateCallDate(${vo_rowNum});" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo.callDate}"/>" size="12" readOnly  style="text-align:center">&nbsp;<img id="callDatePicker${vo_rowNum}" src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtCalledDate${vo_rowNum}",375,570)'style="cursor: pointer;" />
				</c:otherwise>
				</c:choose>
				</display:column>
				<display:column titleKey="PPMT.CALLTIME" headerClass="datatableheader" sortable="true" > 
				<c:choose>
				<c:when test="${vo.slotNo == null}">
					<select name="cmbCallTime${vo_rowNum}" id="cmbCallTime${vo_rowNum}" disabled="disabled">
				</c:when>
				<c:otherwise>
					<select name="cmbCallTime${vo_rowNum}" id="cmbCallTime${vo_rowNum}" >
				</c:otherwise>
				</c:choose>
				<option value="-1" >---Select---</option>
					<c:forEach var="slotDtls" items="${resValue.lLstSlotDtls}">
								<c:choose>
										<c:when test="${slotDtls.slotNo == vo.slotNo}">
											<option selected="selected" value="<c:out value='${slotDtls.slotNo}'/>"><c:out
												value="${slotDtls.slotTime}" /></option>
										</c:when>
										<c:otherwise>
											<option value="<c:out value='${slotDtls.slotNo}'/>"> <c:out 
												value="${slotDtls.slotTime}" /></option>
										</c:otherwise>
									</c:choose>
					</c:forEach>
				</select>
				<!--<input type="text" name="txtCalledTime${vo_rowNum}" id="txtCalledTime${vo_rowNum}" value="${vo.callTime}" disabled = "disabled" style="text-align:center;">
				<input type="hidden" name="hidSlotNo${vo_rowNum}" name="hidSlotNo${vo_rowNum}" value="${vo.slotNo}" />
				--></display:column>
				<c:choose>
				<c:when  test="${curRole == '365450'}">
				<display:column titleKey="PPMT.CHCKLIST" headerClass="datatableheader"
						style="width:20%;text-align:center" ><a href="javascript:void(0)" onclick="javascript:showCheckList('ifms.htm?actionFlag=loadChecklist&pnsrCode=${vo.pensionerId}&showReadOnly=Y');">View</a>
				</display:column>
				</c:when>
				<c:otherwise>
				<display:column titleKey="PPMT.CHCKLIST" headerClass="datatableheader"
						style="width:20%;text-align:center" ><a href="javascript:void(0)" onclick="javascript:showCheckList('ifms.htm?actionFlag=loadChecklist&pnsrCode=${vo.pensionerId}&showReadOnly=N');">View</a>
				</display:column>
				</c:otherwise>
				</c:choose>
				<display:column titleKey="PPMT.STATUS" headerClass="datatableheader" sortable="true"
						style="width:20%;text-align:left" ><label name="txtSchStatus${vo_rowNum}" id="txtSchStatus${vo_rowNum}"><c:choose><c:when test="${vo.identStatus != null and vo.identStatus != ''}">${vo.identStatus}</c:when><c:otherwise>Not Scheduled</c:otherwise></c:choose></label>
				</display:column>
			</c:if>	
			<c:set var="counter" value="${counter+1}"></c:set>						
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}" />
</div>
</hdiits:form>
</fieldset>
<br/>
<div style="text-align:center">
	<c:if test="${curRole == '365453'}">
		<hdiits:button name="btnPhyPpoRcvd" id="btnPhyPpoRcvd" type="button"  captionid="PPMT.PHYPPORCVD" bundle="${pensionLabels}" onclick="genRegNo();" classcss="bigbutton"/>
		<hdiits:button name="btnForward" id="btnForward" type="button"  style="width:200px" captionid="PPMT.FORWARDTOPNSNCLRK" bundle="${pensionLabels}" onclick="forwardPensionCase()"/>
	</c:if>
	<c:if test="${curRole == '365452' or curRole == '365451'}">
		<hdiits:button name="btnSchedule" id="btnSchedule" type="button" classcss="bigbutton" captionid="PPMT.SHEDULETIME" bundle="${pensionLabels}" onclick="getScheduleForPnsr()"/>
		<!--<hdiits:button name="btnReschedule" id="btnReschedule" type="button" classcss="bigbutton" captionid="PPMT.RESCHEDULETIME" bundle="${pensionLabels}" onclick="showReschedular()"/>-->
		<!--<hdiits:button name="btnGenerateLetter" id="btnGenerateLetter" type="button" classcss="bigbutton" captionid="PPMT.GENERATELETT" bundle="${pensionLabels}" onclick="saveDetailsAndGenerateLetter('E')"/>-->
		<hdiits:button name="btnGenerateLetterInMarathi" id="btnGenerateLetterInMarathi"  style="width:200px"  type="button"  captionid="PPMT.GENERATELETT" bundle="${pensionLabels}" onclick="saveDetailsAndGenerateLetter('M')"/>
	<!--<c:if test="${curRole == '365451'}">
		<hdiits:button name="btnApprove" id="btnApprove" type="button"  captionid="PPMT.APPROVE" bundle="${pensionLabels}" onclick="approveSchedule();"/>
	</c:if>-->
		<hdiits:button name="btnSave" id="btnSave" type="button"  captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveSchedule();"/>
	</c:if>
	<c:if test="${showCaseFor == 5}">
		<hdiits:button name="btnSendBackToAG" id="btnSendBackToAG" type="button"  captionid="PPMT.SENDBACKTOAG" style="width:130px;" bundle="${pensionLabels}" onclick="saveArchivedCase();"/>
	</c:if>
	<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
<% }catch(Exception e)
{
	e.printStackTrace();
}%>