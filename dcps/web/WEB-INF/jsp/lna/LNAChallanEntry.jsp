<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/lna/loanChallanEntry.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="CompAdvance" value="${resValue.CompAdvance}"></c:set>
<c:set var="HouseAdvance" value="${resValue.HouseAdvance}"></c:set>
<c:set var="MotorAdvance" value="${resValue.MotorAdvance}"></c:set>
<c:set var="ChallanType" value="${resValue.ChallanType}"></c:set>

<c:if test="${ChallanType == 800070}">
	<c:choose>
		<c:when test="${CompAdvance == null || CompAdvance[0] ==null}">
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="0"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="1"/>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test="${ChallanType == 800071}">
	<c:choose>
		<c:when test="${HouseAdvance == null || HouseAdvance[0] ==null}">
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="0"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="1"/>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test="${ChallanType == 800072}">
	<c:choose>
		<c:when test="${MotorAdvance == null || MotorAdvance[0] ==null}">
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="0"/>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="1"/>
		</c:otherwise>
	</c:choose>
</c:if>


<hdiits:form name="LNAChallanEntryForm" encType="multipart/form-data" validate="true" method="post">
<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request" />

<table align="center" width="70%"><tr><td>
<fieldset class="tabstyle"><legend><fmt:message key="CMN.CHALLANENTRY" bundle="${lnaLabels}"></fmt:message></legend>
<br>
<table align="center" width="70%">
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANTYPE" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <select name="cmbChallanType" id="cmbChallanType" style="width:280px" onchange="actionOnChallanType();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="ChallanType" items="${resValue.lLstChallanType}">
					<c:choose>
						<c:when test="${resValue.ChallanType == ChallanType.lookupId}">
							<option value="${ChallanType.lookupId}" selected="selected"><c:out
								value="${ChallanType.lookupDesc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${ChallanType.lookupId}"><c:out
								value="${ChallanType.lookupDesc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		   
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	
	<tr>
		<td width="10%"><fmt:message key="CMN.EMPLOYEECODE" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtSevaarthId" name="txtSevaarthId" size="30" value="${resValue.SevaarthId}" onblur="getEmployeeData();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	
	<tr>
		<td width="10%"><fmt:message key="CMN.EMPNAME" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtName" name="txtName" size="30" value="${resValue.Name}" readOnly="readOnly"/>
		    <input type="hidden" id="hidSuperAnnDate" name="hidSuperAnnDate"  value="${resValue.SuperAnnDate}" />
		    <input type="hidden" id="hidJoiningDate" name="hidJoiningDate"   value="${resValue.JoiningDate}"/>
		</td>
	</tr>
	
	<tr>
		<td width="10%"><fmt:message key="CMN.GROUP" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtGroup" name="txtGroup" value="${resValue.Group}" readOnly="readOnly"/>
		    
		</td>
	</tr>
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANNO" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtChallanNo" name="txtChallanNo" />
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANDATE" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtChallanDate" name="txtChallanDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		    <img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtChallanDate",375,570)' style="cursor: pointer;"  />
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trTrnId" style="display:none">
		<td width="10%"><fmt:message key="CMN.TID" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtTrnId" name="txtTrnId"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trExcessPay" style="display:none">
		<td width="10%"><fmt:message key="CMN.EXCESSPAY" bundle="${lnaLabels}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtExcessPay" name="txtExcessPay" onkeypress="digitFormat();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trAmountDept" style="display:none">
		<td width="10%"><fmt:message key="CMN.CHALLANAMOUNT" bundle="${lnaLabels}" ></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtAmountDept" name="txtAmountDept" onkeypress="digitFormat();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr></table><table width="100%">
	<tr id="trDisplayTab"><td >
		<c:if test="${resValue.ChallanType ==800070}">
		<display:table list="${CompAdvance}" pagesize="10"  id="vo" cellpadding="4" style="width:100%" requestURI="">
		
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="basic.msg.empty_list" value="No Advance details found" />
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.TID" headerClass="datatableheader" >
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hidTID" name="hidTID" value="${vo[1]}"/>
				<input type="hidden" id="hidAdvanceId" name="hidAdvanceId" value="${vo[0]}"/>
				</display:column>
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.RECVRYOUT" headerClass="datatableheader">
				<c:out value="${vo[2]}"></c:out>
				<input type="hidden" id="hidOutstandingAmt" name="hidOutstandingAmt" value="${vo[2]}"/>
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.INSTLMNTAMTPM" headerClass="datatableheader">
				<c:out value="${vo[3]}"></c:out>
				<input type="hidden" id="hidInstAmt" name="hidInstAmt" value="${vo[3]}"/>				
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.PAYMENT" headerClass="datatableheader" >
				 <input type="text" id="txtPayment" name="txtPayment" value="${vo[3]}" onkeypress="digitFormat(this);"/> 
				</display:column>		
				 
		</display:table>		
		</c:if>
		<c:if test="${resValue.ChallanType ==800071}">
		<display:table list="${HouseAdvance}" pagesize="10"  id="vo" cellpadding="4" style="width:100%" requestURI="">
		
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="basic.msg.empty_list" value="No Advance details found" />
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.TID" headerClass="datatableheader" >
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hidTID" name="hidTID" value="${vo[1]}"/>
				<input type="hidden" id="hidAdvanceId" name="hidAdvanceId" value="${vo[0]}"/>
				</display:column>
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.RECVRYOUT" headerClass="datatableheader">
				<c:out value="${vo[2]}"></c:out>
				<input type="hidden" id="hidOutstandingAmt" name="hidOutstandingAmt" value="${vo[2]}"/>
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.INSTLMNTAMTPM" headerClass="datatableheader">
				<c:out value="${vo[3]}"></c:out>
				<input type="hidden" id="hidInstAmt" name="hidInstAmt" value="${vo[3]}"/>				
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.PAYMENT" headerClass="datatableheader" >
				 <input type="text" id="txtPayment" name="txtPayment" value="${vo[3]}" onkeypress="digitFormat(this);"/> 
				</display:column>		
				 
		</display:table>		
		</c:if>
		<c:if test="${resValue.ChallanType ==800072}">
		<display:table list="${MotorAdvance}" pagesize="10"  id="vo" cellpadding="4" style="width:100%" requestURI="">
		
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="basic.msg.empty_list" value="No Advance details found" />
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.TID" headerClass="datatableheader" >
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hidTID" name="hidTID" value="${vo[1]}"/>
				<input type="hidden" id="hidAdvanceId" name="hidAdvanceId" value="${vo[0]}"/>
				</display:column>
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.RECVRYOUT" headerClass="datatableheader">
				<c:out value="${vo[2]}"></c:out>
				<input type="hidden" id="hidOutstandingAmt" name="hidOutstandingAmt" value="${vo[2]}"/>
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.INSTLMNTAMTPM" headerClass="datatableheader">
				<c:out value="${vo[3]}"></c:out>
				<input type="hidden" id="hidInstAmt" name="hidInstAmt" value="${vo[3]}"/>				
				</display:column>
				
				<display:column style="text-align: center;" titleKey="CMN.PAYMENT" headerClass="datatableheader" >
				 <input type="text" id="txtPayment" name="txtPayment" value="${vo[3]}" onkeypress="digitFormat(this);"/> 
				</display:column>		
				 
		</display:table>		
		</c:if>
	</td></tr>
</table>
<br/>
</fieldset>
</td></tr>
<tr><td><br>
<table width="50%" align="center">
<tr ><td >
<hdiits:button type="button" captionid="BTN.APPROVE" bundle="${lnaLabels}" id="btnApprove" name="btnApprove" onclick="approveChallan();" ></hdiits:button></td>
<td ><hdiits:button type="button" captionid="BTN.CLEAR" bundle="${lnaLabels}" id="btnClear" name="btnClear" onclick="clearData();" ></hdiits:button>
</td>
</tr>
</table>
</td></tr></table>
</hdiits:form>