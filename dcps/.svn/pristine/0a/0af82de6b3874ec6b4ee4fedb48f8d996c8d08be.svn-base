<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script language="JavaScript" src="script/dcps/TerminalRequest.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="User" value="${resValue.hidUser}" ></c:set>
<c:set var="Use" value="${resValue.hidUse}" ></c:set>

<input type="hidden" id="hidGoPressed" name="hidGoPressed" value="${resValue.GoPressed}"/>
<c:if test="${resValue.GoPressed == 'Yes'}">
		<input type="hidden" id="hidTerminalRequestAlreadyRaised" name="hidTerminalRequestAlreadyRaised" value="${resValue.TerminalRequestAlreadyRaised}"/>
</c:if>

<script>
	var goPressed = document.getElementById("hidGoPressed").value.trim();
	var hidTerminalRequestAlreadyRaised = "";
	if(goPressed == 'Yes')
		{
			hidTerminalRequestAlreadyRaised = document.getElementById("hidTerminalRequestAlreadyRaised").value.trim();
			if(hidTerminalRequestAlreadyRaised == 'Yes')
				{
					alert('Terminal Request for this Employee is already raised.');
					self.location.href = "ifms.htm?actionFlag=loadTerminalRequest&User=DDO&Use=NewRequest&elementId=700189";
				}
		}
</script>

<c:if test="${User != 'TO'}">
	<c:set var="varReadOnly" scope="page" value="readOnly='readOnly'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>

<c:if test="${!(User == 'DDO' && Use == 'NewRequest')}">
		<c:set var="varReadOnlyAboveInputDtls" scope="page" value="readOnly='readOnly'"></c:set>
		<c:set var="varImageDisabledAboveInputDtls" scope="page" value="style='display:none'"></c:set>
</c:if>

<c:choose>
	<c:when test="${!(	User == 'DDO' && (Use == 'NewRequest' || Use == 'FromDraft')	)}">
		<c:set var="varDisableAboveInputDtls" scope="page" value="disabled='disabled'"></c:set>
		<c:set var="varReadOnlyRemarks" scope="page" value="readOnly='readOnly'"></c:set>
		<c:set var="attachmentRemoveOption" value="N"></c:set>
		<c:set var="attachmentReadOnlyOption" value="Y"></c:set>
		<c:set var="varGoButtonDisabled" scope="page" value="style='display:none'"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="attachmentRemoveOption" value="Y"></c:set>
		<c:set var="attachmentReadOnlyOption" value="N"></c:set>
	</c:otherwise>
</c:choose>

<hdiits:form name="TerminalRequestForm" id="TerminalRequestForm" encType="multipart/form-data" validate="true" method="post">


<fieldset class="tabstyle"><legend><fmt:message key="CMN.TERMINATIONDETAILS" bundle="${dcpsLables}" /></legend>

	<input type="hidden" name="hidUser" id="hidUser" value="${resValue.hidUser}" />
	<input type="hidden" name="hidUse" id="hidUse" value="${resValue.hidUse}" />
	<input type="hidden" name="hidDcpsEmpId" id="hidDcpsEmpId" value="${resValue.lStrEmpId}" />
	<input type="hidden" name="hidTerminalId" id="hidTerminalId" value="${resValue.hidTerminalId}" />
	
	<table align="center" width="70%">
			<tr>
				<td width="25%" align="left"><fmt:message key="CMN.NAME" bundle="${dcpsLables}"></fmt:message></td>
					
				<td width="35%" align="left">
					<input type="text" id="txtEmployeeName" style="text-transform: uppercase;width: 80%" name="txtEmployeeName" value="${resValue.lStrName}" ${varReadOnlyAboveInputDtls}/>
					<span id="roleIndicatorRegion" style="display: none"><img src="./images/busy-indicator.gif" /></span>
					<label class="mandatoryindicator" ${varImageDisabledAboveInputDtls }>*</label>
				</td>
			
				<td width="15%" align="left"><fmt:message key="CMN.DCPSID" bundle="${dcpsLables}"></fmt:message></td>
				
				<td width="25%" align="left">
					<input type="text" name="txtDCPSId" id="txtDCPSId" readOnly="readOnly" style="width: 95%" value="${resValue.lStrDcpsId}" ${varReadOnlyAboveInputDtls}/> 
				</td>
			</tr>
			
			<tr>
				<td width="25%" align="left"><fmt:message key="CMN.DATEOFTERMINATION" bundle="${dcpsLables}"></fmt:message></td>
					
				<td width="35%" align="left">
					<input type="hidden" name="hidJoiningDate" id="hidJoiningDate" value="${resValue.lStrDOJ }"/>
					<input type="text" name="txtTerminationDate" id="txtTerminationDate" maxlength="10" value="${resValue.lStrTerminationDate}" onkeypress="digitFormat(this);dateFormat(this);" 
						onBlur="compareDates(txtTerminationDate,hidJoiningDate,'Date of Termination should be greater than date Of Joining.','>');"  ${varReadOnlyAboveInputDtls}/> 
					<img src='images/CalendarImages/ico-calendar.gif' width='20' onClick='window_open("txtTerminationDate",375,570)' style="cursor: pointer;" ${varImageDisabledAboveInputDtls} />
					<label class="mandatoryindicator"} ${varImageDisabledAboveInputDtls }>*</label>
				</td>
			
				<td width="15%" align="left">&nbsp;</td>
				
				<td width="25%" align="left">&nbsp;</td>
			</tr>
			
			<tr>
				<td width="25%" align="left"><fmt:message key="CMN.REASONFORTERMINATION" bundle="${dcpsLables}"></fmt:message></td>
					
				<td width="35%" align="left">
						<select name="cmbReasonForTermination" id="cmbReasonForTermination" onChange=""  ${varDisableAboveInputDtls} >
											<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
												<c:forEach var="reasonForTermination" items="${resValue.listReasonsForTermination}" >
													<c:choose>
														<c:when test="${resValue.lStrReasonForTermination == reasonForTermination.lookupId}">
																<option value="${reasonForTermination.lookupId}" selected="selected"><c:out value="${reasonForTermination.lookupDesc}"></c:out></option>
														</c:when>
														<c:otherwise>
																<option value="${reasonForTermination.lookupId}"><c:out value="${reasonForTermination.lookupDesc}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
						</select>
						<label class="mandatoryindicator" ${varImageDisabledAboveInputDtls }>*</label>
				</td>
			
				<td width="15%" align="left">&nbsp;</td>
					
				<td width="25%" align="left">&nbsp;</td>
			</tr>
	</table>
<br>

<div align="center" ${varGoButtonDisabled}>
			<hdiits:button type="button" captionid="BTN.GO" bundle="${dcpsLables}" name="btnGo" id="btnGo" onclick="getBelowDetailsForTermination();" />
			<c:if test="${User == 'DDO' && Use == 'FromDraft'}">
				<hdiits:button type="button" captionid="BTN.DELETEREQUEST" classcss="bigbutton" bundle="${dcpsLables}" name="deleteRequest" id="deleteRequest" onclick="deleteTerminalRequest('${resValue.hidTerminalId}');" />
			</c:if>
			
</div>
</fieldset>



<c:set var="totalMissingCredits" value="0"/>
<c:if test="${resValue.listMissingCredits != null}">
<fieldset class="tabstyle"><legend><fmt:message key="CMN.MISSINGCREDITSOFEMP" bundle="${dcpsLables}" /></legend>
	<div align="center">
	<br>
		<div style="overflow:auto;height: 200px;" >	
		
					<display:table list="${resValue.listMissingCredits}" id="vo" pagesize="100" cellpadding="5" style="width:80%" requestURI="" >
					
						<display:setProperty name="paging.banner.placement" value="none" />
			
						<display:column style="text-align: center;width:5%"  class="oddcentre" titleKey="CMN.EMPSRNO" headerClass="datatableheader" sortable="true" value="${vo_rowNum}" >
						</display:column>
						
						<display:column style="text-align: left;width:10%"  class="oddcentre" titleKey="CMN.MONTH" headerClass="datatableheader"  sortable="true" >
							<c:out value="${vo[1]} "/>
							<input type="hidden" id="hidMonth${vo_rowNum}" name="hidMonth${vo_rowNum}" value="${vo[3]}"/>
							<input type="hidden" id="hidMissingCreditPK${vo_rowNum}" name="hidMissingCreditPK${vo_rowNum}" value="${vo[4]}"/>
						</display:column>
						
						<display:column style="text-align: left;width:15%"  class="oddcentre" titleKey="CMN.YEAR" headerClass="datatableheader"  sortable="true" >
							<c:out value="${vo[0]} "/>
							<input type="hidden" id="hidYear${vo_rowNum}" name="hidYear${vo_rowNum}" value="${vo[2]}"/>
						</display:column>
						
						<c:choose>
							<c:when test="${User == 'TO'}">
								<display:column style="text-align: center;width:20%"  class="oddcentre" titleKey="CMN.AMOUNTTOBEDEDUC*" headerClass="datatableheader"  sortable="true" >
									<input type="text" name="txtDeducAmount${vo_rowNum}" id="txtDeducAmount${vo_rowNum}" value="${vo[5]}" onkeypress="digitFormat(this);" style="text-align: right;" ${varReadOnly} />
								</display:column>
							</c:when>
							<c:otherwise>
								<display:column style="text-align: center;width:20%"  class="oddcentre" titleKey="CMN.AMOUNTTOBEDEDUC" headerClass="datatableheader"  sortable="true" >
									<input type="text" name="txtDeducAmount${vo_rowNum}" id="txtDeducAmount${vo_rowNum}" value="${vo[5]}" onkeypress="digitFormat(this);" style="text-align: right;" ${varReadOnly} />
								</display:column>
							</c:otherwise>
						</c:choose>
							
						<c:choose>
							<c:when test="${User == 'TO'}">
								<display:column style="text-align: center;width:15%"  class="oddcentre" titleKey="CMN.VOUCHERNO*" headerClass="datatableheader" sortable="true" >
									<input type="text" name="txtVoucherNo${vo_rowNum}" id="txtVoucherNo${vo_rowNum}" value="${vo[6]}" onkeypress="digitFormat(this);" ${varReadOnly} />
								</display:column>
							</c:when>
							<c:otherwise>
								<display:column style="text-align: center;width:15%"  class="oddcentre" titleKey="CMN.VOUCHERNO" headerClass="datatableheader" sortable="true" >
									<input type="text" name="txtVoucherNo${vo_rowNum}" id="txtVoucherNo${vo_rowNum}" value="${vo[6]}" onkeypress="digitFormat(this);" ${varReadOnly} />
								</display:column>
							</c:otherwise>
						</c:choose>
							
						<c:choose>
							<c:when test="${User == 'TO'}">
								<display:column style="text-align: center;width:20%"  class="oddcentre" titleKey="CMN.VOUCHERDATE*" headerClass="datatableheader" sortable="true" >
								<c:choose>
									<c:when test="${vo[7] != '' && vo[7] != null}">
										<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[7]}" var="varVoucherDate"/>
									</c:when>
									<c:otherwise>
										<c:set var="varVoucherDate" value=""></c:set>
									</c:otherwise>
								</c:choose>
								
									<input type="text" name="txtVoucherDate${vo_rowNum}" id="txtVoucherDate${vo_rowNum}" value="${varVoucherDate}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" style="width: 75%" ${varReadOnly}/>
									<img src='images/CalendarImages/ico-calendar.gif' width='20'
										onClick='window_open(event,"txtVoucherDate${vo_rowNum}",375,570)'
										style="cursor: pointer;" ${varImageDisabled}/>
								</display:column> 
							</c:when>
							<c:otherwise>
								<display:column style="text-align: center;width:20%"  class="oddcentre" titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" sortable="true" >
								<c:choose>
									<c:when test="${vo[7] != '' && vo[7] != null}">
										<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[7]}" var="varVoucherDate"/>
									</c:when>
									<c:otherwise>
										<c:set var="varVoucherDate" value=""></c:set>
									</c:otherwise>
								</c:choose>
								
								<input type="text" name="txtVoucherDate${vo_rowNum}" id="txtVoucherDate${vo_rowNum}" value="${varVoucherDate}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" style="width: 75%" ${varReadOnly}/>
									<img src='images/CalendarImages/ico-calendar.gif' width='20'
										onClick='window_open(event,"txtVoucherDate${vo_rowNum}",375,570)'
										style="cursor: pointer;"  ${varImageDisabled}/>
								</display:column> 
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${User == 'DDO'}">
								<display:column style="text-align: center;width:15%"  class="oddcentre" titleKey="CMN.REMARKS*" headerClass="datatableheader" sortable="true" >
									<input type="text" name="txtRemarks${vo_rowNum}" id="txtRemarks${vo_rowNum}" value="${vo[8]}" ${varReadOnlyRemarks}/>
									<c:set var="totalMissingCredits" value="${totalMissingCredits + 1}"/>
								</display:column>
							</c:when>
							<c:otherwise>
								<display:column style="text-align: center;width:15%"  class="oddcentre" titleKey="CMN.REMARKS" headerClass="datatableheader" sortable="true" >
									<input type="text" name="txtRemarks${vo_rowNum}" id="txtRemarks${vo_rowNum}" value="${vo[8]}" ${varReadOnlyRemarks}/>
									<c:set var="totalMissingCredits" value="${totalMissingCredits + 1}"/>
								</display:column>
							</c:otherwise>
						</c:choose>
							
					</display:table>
		</div>
	</div>
</fieldset>
</c:if>

<input type="hidden" id="hidTotalMissingCredits" name="hidTotalMissingCredits" value="${totalMissingCredits}"/>

<br>
<c:if test="${resValue.GoPressed == 'Yes' || (User == 'DDO' && (Use == 'FromDraft' || Use == 'FromTO')) || (User == 'TO') || (User == 'SRKA')}">
	<fieldset class="tabstyle"><legend><fmt:message key="CMN.ATTACHMENTS" bundle="${dcpsLables}" /></legend>
		<table width="100%" align="center">
			<tr><td></td></td>
			<tr>
				<td  style="width:75%">
							<jsp:include page="/WEB-INF/jsp/dcps/TerminalProcessAttachment.jsp">
								<jsp:param name="attachmentName" value="FormA" />
								<jsp:param name="formName" value="TerminalRequestForm" />
								<jsp:param name="attachmentType" value="Document" />
								<jsp:param name="attachmentTitle" value="Application As Per Form A" />
								<jsp:param name="multiple" value="N" />
								<jsp:param name="removeAttachmentFromDB" value="${attachmentRemoveOption}" />
								<jsp:param name="readOnly" value="${attachmentReadOnlyOption}" />
								<jsp:param name="attachmentSize" value="1" /> 
							</jsp:include>
				</td>
			</tr>
			
			<tr><td></td></tr>
			
			<tr>
				<td  style="width:75%">
							<jsp:include page="/WEB-INF/jsp/dcps/TerminalProcessAttachment.jsp">
								<jsp:param name="attachmentName" value="DeathCertificate" />
								<jsp:param name="formName" value="TerminalRequestForm" />
								<jsp:param name="attachmentType" value="Document" />
								<jsp:param name="attachmentTitle" value="Death Certificate" />
								<jsp:param name="multiple" value="N" />
								<jsp:param name="removeAttachmentFromDB" value="${attachmentRemoveOption}" />
								<jsp:param name="readOnly" value="${attachmentReadOnlyOption}" />
								<jsp:param name="attachmentSize" value="1" /> 
							</jsp:include>
				</td>
			</tr>	
			
			<tr><td></td></tr>
			
			<tr>	
				<td  style="width:75%">
							<jsp:include page="/WEB-INF/jsp/dcps/TerminalProcessAttachment.jsp">
								<jsp:param name="attachmentName" value="R3Report" />
								<jsp:param name="formName" value="TerminalRequestForm" />
								<jsp:param name="attachmentType" value="Document" />
								<jsp:param name="attachmentTitle" value="R3 of Previous Financial Year" />
								<jsp:param name="multiple" value="N" />
								<jsp:param name="removeAttachmentFromDB" value="${attachmentRemoveOption}" />
								<jsp:param name="readOnly" value="${attachmentReadOnlyOption}" />
								<jsp:param name="attachmentSize" value="1" /> 
							</jsp:include>
				</td>
			</tr>
		</table>
	</fieldset>
</c:if>

<br>
<div align="center">
	<c:if test="${User == 'DDO' && 	(  (Use == 'NewRequest' && resValue.GoPressed == 'Yes')  ||  (Use == 'FromDraft') )}">
		<hdiits:button	name="btnSave" id="btnSave" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" style="width:10%"  classcss="bigbutton" onclick="saveOrForwardTerminalRequest(1);" />
		<hdiits:button	name="btnGenerateFormB" id="btnGenerateFormB" type="button" captionid="BTN.GENERATEFORMB" classcss="bigbutton" bundle="${dcpsLables}" onclick="generateFormB();" />
		<hdiits:button	name="forwardToTO" id="forwardToTO" type="button" captionid="BTN.FORWARDTOTO" bundle="${dcpsLables}"  classcss="bigbutton" onclick="saveOrForwardTerminalRequest(2);" />
		<hdiits:button  name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" classcss="bigbutton" style="width:10%" onclick="ReturnBackOrClose();"/>   
	</c:if>
	
	<c:if test="${User == 'TO'}">
		<hdiits:button	name="btnSave" id="btnSave" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" style="width:10%"  classcss="bigbutton" onclick="saveOrForwardTerminalRequest(3);" />
		<hdiits:button	name="btnGenerateFormD" id="btnGenerateFormD" type="button" captionid="BTN.GENERATEFORMD" classcss="bigbutton" bundle="${dcpsLables}" onclick="generateFormD();" />
		<hdiits:button	name="forwardToDDOFromTO" id="forwardToDDOFromTO" type="button" captionid="BTN.FORWARDTODDO" bundle="${dcpsLables}"  classcss="bigbutton" onclick="saveOrForwardTerminalRequest(4);" />
		<hdiits:button  name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" classcss="bigbutton" style="width:10%" onclick="ReturnBackOrClose();"/>   
	</c:if>
	
	<c:if test="${User == 'DDO' && Use == 'FromTO'}">
		<hdiits:button	name="btnGenerateFormC" id="btnGenerateFormC" type="button" captionid="BTN.GENERATEFORMC" classcss="bigbutton" bundle="${dcpsLables}" onclick="generateFormC();" />
		<hdiits:button	name="forwardToSRKAFromDDO" id="forwardToSRKAFromDDO" type="button" captionid="BTN.FORWARDTOSRKA" bundle="${dcpsLables}"  classcss="bigbutton" onclick="saveOrForwardTerminalRequest(5);" />
		<hdiits:button  name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" classcss="bigbutton" style="width:10%" onclick="ReturnBackOrClose();"/>   
	</c:if>
</div>


</hdiits:form>

<input type="hidden" name="hidSearchFromDDO" id="hidSearchFromDDO" value="searchByDDO" />
<input type="hidden" name="searchType" id="searchType" value="OnlyDCPS" />

<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS" 
	parameters="searchKey={txtEmployeeName},searchBy={hidSearchFromDDO},searchType={searchType}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" postFunction="getEmpDtlsForName"/>
