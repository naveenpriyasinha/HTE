<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript" src="script/gpf/challanEntry.js"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="RAdvance" value="${resValue.RAdvance}"></c:set>
<c:choose>
<c:when test="${RAdvance == null || RAdvance[0] ==null}">
<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="0"/>
</c:when>
<c:otherwise>
<input type="hidden" id="hidAdvanceReqCount" name="hidAdvanceReqCount" value="1"/>
</c:otherwise>
</c:choose>
<hdiits:form name="GPFChallanEntryForm" encType="multipart/form-data"
	validate="true" method="post">
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />
<table align="center" width="70%"><tr><td>
<fieldset class="tabstyle"><legend><fmt:message key="CMN.CHALLANENTRY" bundle="${gpfLables}"></fmt:message></legend>
<br>
<table align="center" width="70%">
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANTYPE" bundle="${gpfLables}"></fmt:message>
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
		<td width="10%"><fmt:message key="CMN.GPFACCNO" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtGpfAcNo" name="txtGpfAcNo" size="30" value="${resValue.GpfAccNo}" onblur="getEmployeeData();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	
	<tr>
		<td width="10%"><fmt:message key="CMN.EMPNAME" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtName" name="txtName" size="30" value="${resValue.Name}" readOnly="readOnly"/>
		    <input type="hidden" id="hidSuperAnnDate" name="hidSuperAnnDate" />
		    <input type="hidden" id="hidJoiningDate" name="hidJoiningDate" />
		</td>
	</tr>
	
	<tr>
		<td width="10%"><fmt:message key="CMN.GROUP" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtGroup" name="txtGroup" value="${resValue.Group}" readOnly="readOnly"/>
		    
		</td>
	</tr>
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANNO" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtChallanNo" name="txtChallanNo" />
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr>
		<td width="10%"><fmt:message key="CMN.CHALLANDATE" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtChallanDate" name="txtChallanDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);"/>
		    <img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtChallanDate",375,570)' style="cursor: pointer;"  />
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trTrnId" style="display:none">
		<td width="10%"><fmt:message key="CMN.TID" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtTrnId" name="txtTrnId"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trExcessPay" style="display:none">
		<td width="10%"><fmt:message key="CMN.EXCESSPAY" bundle="${gpfLables}"></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtExcessPay" name="txtExcessPay" onkeypress="digitFormat();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr>
	<tr id="trAmountDept" style="display:none">
		<td width="10%"><fmt:message key="CMN.CHALLANAMOUNT" bundle="${gpfLables}" ></fmt:message>
		</td>
		<td width="20%">
		    <input type="text" id="txtAmountDept" name="txtAmountDept" onkeypress="digitFormat();"/>
		    <label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</td>
	</tr></table><table width="100%">
	<tr id="trDisplayTab"><td >	
		<c:if test="${resValue.ChallanType !=null && resValue.ChallanType != ''}">
		<display:table list="${RAdvance}" pagesize="10"  id="vo" cellpadding="4" style="width:100%" requestURI="">
		
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="basic.msg.empty_list" value="No Advance details found" />
				
				<display:column style="height:35;text-align: center;" titleKey="CMN.TID" headerClass="datatableheader" >
				<c:out value="${vo[1]}"></c:out>
				<input type="hidden" id="hidTID" name="hidTID" value="${vo[1]}"/>
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
				 <input type="text" id="txtPayment" name="txtPayment" value="${vo[2]}" onkeypress="digitFormat(this);"/> 
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
<hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLables}" id="btnApprove" name="btnApprove" onclick="approveChallan();" ></hdiits:button></td>
<td ><hdiits:button type="button" captionid="BTN.CLEAR" bundle="${gpfLables}" id="btnClear" name="btnClear" onclick="clearData();" ></hdiits:button>
</td>
</tr>
</table>
</td></tr></table>
</hdiits:form>