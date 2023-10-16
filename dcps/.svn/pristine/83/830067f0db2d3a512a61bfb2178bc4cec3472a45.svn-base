<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLabels" scope="request"/>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/gpf/gpfValidation.js"></script>
<script type="text/javascript" src="script/gpf/interestCalculation.js"></script>

<hdiits:form name="GPFInterestCalc" encType="multipart/form-data" validate="true" method="post">
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="Year" value="${resValue.Year}"/>
<c:set var="interestRate" value="${resValue.InterestRate}"/>
<c:if test="${resValue.OfficeId == null}">
<c:set var="displayNone" value="display: none"/>
</c:if>
<c:if test="${resValue.SevaarthId == null}">
<c:set var="displayNoneEmpDtls" value="display: none"/>
</c:if>
<fieldset style="width:80%" class="tabstyle"><legend><fmt:message key="CMN.INTERESTCALC" bundle="${gpfLabels}" ></fmt:message></legend>
<table width="50%">
	<tr>
	</br>
		<td width="25%"><input type="radio" id="rdoForAllEmp" name="rdoSelectEmp" value="A" onclick="enableSection();"/>
		<fmt:message key="CMN.ALLEMPLOYEE" bundle="${gpfLabels}"></fmt:message></td>		
		<td width="25%"><input type="radio" id="rdoForSingleEmp" name="rdoSelectEmp" value="S" onclick="enableSection();"/>
		<fmt:message key="CMN.SINGLEEMP" bundle="${gpfLabels}"></fmt:message></td>
		
	</tr>
	<tr><td><br></td></tr>
</table>
</fieldset>
<br>
<fieldset style="width:80%" class="tabstyle" id="tblEmpDtls" style="${displayNoneEmpDtls}"><legend><fmt:message key="CMN.FOREMP" bundle="${gpfLabels}" ></fmt:message></legend>
<table width="80%" cellspacing = "4" cellpadding = "4" >
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.SEVAARTHID" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" ><input type="text"  name='txtSevaarthId' id="txtSevaarthId"  value="" onblur="popUpEmpDtls();"/></td>
	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.NAMEOFEMP" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%"><input type="text"  name='txtEmpName' id="txtEmpName" readOnly="readOnly" size="35"/></td>
	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.OFFNAME" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" ><input type="text"  name='txtOffName' id="txtOffName" readOnly="readOnly" size="35"/></td>
	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.GPFACCNO" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" ><input type="text"  name='txtGpfAccNo' id="txtGpfAccNo" readOnly="readOnly"/></td>
	</tr>
	
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.CURRBAL" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" >
		<input type="text"  name='txtCurrentBal' id="txtCurrentBal" readOnly="readOnly"/>
		</td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.CURRINT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" ><input type="text"  name='txtCurrentInt' id="txtCurrentInt"  readOnly="readOnly"/></td>
	</tr>
	<tr>
		<td width="15%" align="left" ><fmt:message key="CMN.INTCALFROM" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%">
			<input type="text" name="txtEffectFromDate" id="txtEffectFromDate" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);validateInterestDate();"/>
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEffectFromDate",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<fmt:message key="CMN.TO" bundle="${gpfLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="txtApplicableToDate" id="txtApplicableToDate" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);compareFromDate();"/>
				<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtApplicableToDate",375,570)' style="cursor: pointer;"/>
				<label class="mandatoryindicator">*</label>			
		</td>
	</tr>
	
	<tr >
		<td width="15%"></td>
		<td width="35%"><br/><hdiits:button type="button" captionid="BTN.CALC" bundle="${gpfLabels}" id="btnCalculate" name="btnCalculate" onclick="calculateInterest();"></hdiits:button></td>

	</tr>
	<tr>
		<td width="15%" align="left" ><br/><fmt:message key="CMN.AMTAFTERINT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="35%" ><input type="text"  name='txtAmtAfterInterest' id="txtAmtAfterInterest"  value="" />
		<input type="hidden" id="hidInterestAmount" name="hidInterestAmount" />
		</td>
	</tr>
</table>
</br>
</br>
<table align="center" width="40%">
  <tr>
    <td><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLabels}" id="btnApproveForEmp" name="btnApproveForEmp" onclick="approveInterestRateCase()"></hdiits:button></td>
    <td><hdiits:button type="button" captionid="BTN.CLEAR" bundle="${gpfLabels}" id="btnClearForEmp" name="btnClearForEmp" onclick="clearAll();"></hdiits:button></td>
  </tr>
</table>
</fieldset>
</br>
<fieldset style="width:80%" class="tabstyle" id="tblOfficeDtls" style="${displayNone}"><legend><fmt:message key="CMN.FOROFFICE" bundle="${gpfLabels}" ></fmt:message></legend>
<table width="80%" cellspacing = "4" cellpadding = "4" >
	<tr>
		<td width="20%" align="left" ><fmt:message key="CMN.OFFNAME" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" colspan="3">
		<select name="cmbOfficeName" id="cmbOfficeName" style="width:320px" onchange="populateInterestRate();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="OfficeName" items="${resValue.OfficeName}">
						<c:choose>
					<c:when test="${resValue.OfficeId == OfficeName.id}">
						<option value="${OfficeName.id}" selected="selected">
						<c:out value="${OfficeName.desc}"></c:out>
						</option>
					</c:when>
					<c:otherwise>
						<option value="${OfficeName.id}">
						<c:out value="${OfficeName.desc}"></c:out>
						</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				</select>
		</td>
	</tr>
	
	<tr>
		<td width="20%" align="left" ><fmt:message key="CMN.GROUP" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" colspan="3">
		<select name="cmbGroup" id="cmbGroup" style="width:180px" onchange="populateInterestRate();">
				<option value="-1"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="groupList" items="${resValue.groupList}">
					<c:choose>
					<c:when test="${resValue.GroupId == groupList.lookupId}">
						<option value="${groupList.lookupId}" selected="selected">
						<c:out value="${groupList.lookupDesc}"></c:out>
						</option>
					</c:when>
					<c:otherwise>
						<option value="${groupList.lookupId}">
							<c:out	value="${groupList.lookupDesc}"/>
						</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
		</select>
		</td>
	</tr>	
	<tr>
		<td width="20%" align="left" ><fmt:message key="CMN.CURRINT" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="25%" colspan="3"><input type="text"  name='txtCurrentOfficeInt' id="txtCurrentOfficeInt" readOnly="readOnly" value="${resValue.InterestRate}"/></td>
	</tr>
	<tr>
		<td width="20%" align="left" ><fmt:message key="CMN.INTCALFORYEAR" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="25%">
		<!-- <input type="text" name="txtEffectFromDateOffice" id="txtEffectFromDateOffice" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);validateInterestDateForOffice();" value="${resValue.EffectFromDate}"/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtEffectFromDateOffice",375,570)' style="cursor: pointer;" />
			<label class="mandatoryindicator">*</label></td>
		<td width="5%"  align="left" ><fmt:message key="CMN.TO" bundle="${gpfLabels}"></fmt:message></td>
		<td align="left" width="25%">
		<input type="text" name="txtApplicableToDateOffice" id="txtApplicableToDateOffice" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);validateInterestDateForOffice();"value="${resValue.ApplicableToDate}"/>
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtApplicableToDateOffice",375,570)' style="cursor: pointer;"/>
			<label class="mandatoryindicator">*</label>  -->
		
			<select name="cmbYearForAllEmp" id="cmbYearForAllEmp" style="width:100px">
				<option value="-1" selected="selected"><fmt:message	key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="yearList" items="${resValue.yearList}">
				<c:choose>
					<c:when test="${resValue.finYear == yearList.desc}" >
						<option value="${yearList.desc}" selected = "selected" >
							<c:out value="${yearList.desc}"/>
						</option>
					</c:when>
					<c:otherwise>
							<option value="${yearList.desc}">
								<c:out value="${yearList.desc}"/>
							</option>						
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr align="center">
		<td colspan="4" ><hdiits:button type="button" captionid="BTN.CALC" bundle="${gpfLabels}" id="btnCalculateForOffice" name="btnCalculateForOffice" onclick="getEmpDtlsUsingOfficeName();"></hdiits:button></td>
	</tr>
	
	<tr style="${displayNone}">
		<td colspan="4" >

		</td>
	</tr>
	
</table>
		<display:table list="${resValue.EmpDtls}" pagesize="10"  id="vo" cellpadding="4" style="width:100%;align:center" requestURI="">
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column style="height:35;text-align: left;" class="oddcentre"
		 titleKey="CMN.SRNO" headerClass="datatableheader" value="${vo_rowNum}">
		</display:column>
		<display:column style="height:35;text-align: left;" class="oddcentre"
		 titleKey="CMN.SEVAARTHID" headerClass="datatableheader" value="${vo[1]}">
		</display:column>
		
		<display:column style="height:35;text-align: left;" class="oddcentre"
		 titleKey="CMN.GPFACCNO" headerClass="datatableheader" value="${vo[2]}">
		 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre" 
		 titleKey="CMN.EMPNAME" headerClass="datatableheader" value="${vo[3]}"> 
		</display:column>
		
		<display:column style="text-align: left;" class="oddcentre"
		 titleKey="CMN.CURRBAL" headerClass="datatableheader" value="${vo[4]}">
		</display:column>
		<fmt:formatNumber var="AmtAfterInt" pattern="0.00" value="${vo[5]}" ></fmt:formatNumber>		
		<display:column style="text-align: left;" class="oddcentre"
		 titleKey="CMN.AMTAFTERINT" headerClass="datatableheader" >
		 <c:out value="${AmtAfterInt + vo[4]}"></c:out>
		 <input type="hidden" id="hidAmountAfterInt${vo_rowNum}" name="hidAmountAfterInt${vo_rowNum}" value="${AmtAfterInt}"/>
		</display:column>		
		<display:column class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader">
		<c:choose>
			<c:when test="${vo[6] == null}">
				<input type="checkbox" id="chkReq_${vo_rowNum}" name="chkReq" value="${vo[0]}"/>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="chkReq_${vo_rowNum}" name="chkReq" value="${vo[0]}" disabled/>
			</c:otherwise>	
		</c:choose>	
		<input type="hidden" id="hdnAmtAfterInterest${vo_rowNum}" name="hdnAmtAfterInterest" value="${AmtAfterInt}"/>
		<input type="hidden" id="hdnGpfAccNo${vo_rowNum}" name="hdnGpfAccNo" value="${vo[2]}"/>
		</display:column>
		
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${vo_rowNum}"/>
<c:if test="${resValue.EmpDtls != null && resValue.EmpDtls[0] != null}">
<table align="center" width="50%" >
  <tr>
    <td width="5%"><hdiits:button type="button" captionid="BTN.APPROVE" bundle="${gpfLabels}" id="btnApprove" name="btnApprove" onclick="approveInterestRateCase()"></hdiits:button></td>
    <td width="5%"><hdiits:button type="button" captionid="BTN.CLEAR" bundle="${gpfLabels}" id="btnClear" name="btnClear" onclick="clearAllForOffice();"></hdiits:button></td>
  </tr>
</table>
</c:if>
</fieldset>

<input type="hidden" id="hidFinYear" name="hidFinYear" value="${resValue.CurFinYear}" />

</hdiits:form>

<c:if test="${resValue.EmpDtls != null}" >
<script>
	document.getElementById("rdoForAllEmp").checked = "checked";	
</script>	
</c:if>
