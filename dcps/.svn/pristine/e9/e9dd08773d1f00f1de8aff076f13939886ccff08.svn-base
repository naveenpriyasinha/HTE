<%try{ %>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<fmt:setBundle basename="resources.pensionproc.PensionCaseLabels" var="pensionLabels" scope="request"/>

<fmt:setBundle basename="resources.pensionproc.PensionCaseAlerts" var="pensionAlerts" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script type="text/javascript"  src="script/pensionproc/pensionCaseCheckList.js"></script>
<script type="text/javascript" >
CERTIFICATE='<fmt:message key="PPROC.CERTIFICATE" bundle="${pensionAlerts}"></fmt:message>';
OFFICE='<fmt:message key="PPROC.OFFICE" bundle="${pensionAlerts}"></fmt:message>';
CERTFROMDATE= '<fmt:message key="PPROC.CERTFROMDATE" bundle="${pensionAlerts}"></fmt:message>';
CERTTODATE = '<fmt:message key="PPROC.CERTTODATE" bundle="${pensionAlerts}"></fmt:message>';
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstTrnPnsnProcCheckList" value="${resValue.lLstTrnPnsnProcCheckList}"></c:set>
<c:set var="lObjTrnPnsnProcCheckListVO" value="${resValue.lObjTrnPnsnProcCheckListVO}" ></c:set>
<c:set var="certificateTypeList" value="${resValue.lLstCertificate}"></c:set>
<c:set var="officeTypeList" value="${resValue.lLstOfficeList}"></c:set>
<c:set var="pensionCaseType" value="${resValue.pensionCaseType}" />
<c:set var="orderno" value="${resValue.orderno}"></c:set>
<c:set var="orderDate" value="${resValue.orderDate}" />


<table>
	<tr>
		<td width="30%">
			<fmt:message key="PPROC.GOVTACCOMODATION" bundle="${pensionLabels}">
			</fmt:message>
		</td>
		<td>
		<c:choose>
		<c:when test="${lObjTrnPnsnProcCheckListVO.gvnmntAcmdtnFlag == 89}">
			<input type="radio" id="radioGovtAcmdn" name="radioGovtAcmdn" maxlength="1" value="Y" checked="checked" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioGovtAcmdn" name="radioGovtAcmdn" value="N"  maxlength="1" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
			<input type="radio" id="radioGovtAcmdn" name="radioGovtAcmdn" value="Y"  maxlength="1" /> 
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioGovtAcmdn" name="radioGovtAcmdn" value="N" checked="checked"  maxlength="1" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
			
		</td>
	</tr>
	<tr>
		<td width="30%">
		<fmt:message key="PPROC.NOCOBTAINED" bundle="${pensionLabels}" >
		</fmt:message>
		</td>
		<td>
		<c:choose>
		<c:when test="${lObjTrnPnsnProcCheckListVO.nocFlag == 89}">
			<input type="radio" id="radioNocObtained" name="radioNocObtained" value="Y" onclick="" checked="checked"  maxlength="1" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioNocObtained" name="radioNocObtained" value="N" onclick=""  maxlength="1" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:when>
		<c:otherwise>
			<input type="radio" id="radioNocObtained" name="radioNocObtained" value="Y" onclick=""  maxlength="1" />
			<fmt:message key="PPROC.YES" bundle="${pensionLabels}"></fmt:message> 
			<input type="radio" id="radioNocObtained" name="radioNocObtained" value="N" onclick="" checked="checked"  maxlength="1" /> 
			<fmt:message key="PPROC.NO" bundle="${pensionLabels}"></fmt:message>
		</c:otherwise>
		</c:choose>
			
		</td>
	</tr>
	</table>

<fieldset style="width: 100%" class="tabstyle">
	<legend id="headingMsg">
		<b> Certificate Details</b>
	</legend> 
	<input type="hidden" name="hidGridSize" id="hidGridSize" value="1" /> 
	<table>
	<tr>
	<td>
		<fmt:message key="PPROC.CERTIFICATE" bundle="${pensionLabels}"></fmt:message></td>
	<td>
		<select name="cmbCertificate" id="cmbCertificate" onfocus="onFocus(this)"
			onblur="onBlur(this);" onchange="NoDENoDuesPWD(this);" >
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="certificateTypeList" items="${resValue.lLstCertificate}">
				<c:choose>
				<c:when test="${lObjTrnPnsnProcCheckListVO.certificateTypeId == certificateTypeList.lookupId}">
				
					<option selected="selected" value="${certificateTypeList.lookupId}"><c:out value="${certificateTypeList.lookupName}"></c:out>
					</option>
				</c:when>
				<c:otherwise>
				
					<option value="${certificateTypeList.lookupId}"><c:out value="${certificateTypeList.lookupName}"></c:out>
					</option>
				</c:otherwise>
				</c:choose>
				</c:forEach>
		</select><label	class="mandatoryindicator">*</label>
	</td>
	</tr>
	<tr>
	<td width="20%" align="left">
		<fmt:message key="PPROC.OFFICE" bundle="${pensionLabels}"></fmt:message>
	</td>
	<td width="20%" align="left">
		 <input type="text" id="txtCheckListOffName" name="txtCheckListOffName" value="${lObjTrnPnsnProcCheckListVO.officeName}" onKeyPress="" onfocus="onFocus(this)"  onblur="onBlur(this)" tabindex="" maxlength=""/>
		 <label class="mandatoryindicator">*</label>
	</td>
	</tr>
	
			<tr style="display: none;">
			<td width="20%" align="left"><fmt:message key="PPROC.DURATION"
			bundle="${pensionLabels}"></fmt:message></td>
		<td width="35%"><fmt:message key="PPROC.FROM"
			bundle="${pensionLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;<input type="text"
			name="txtFromDate" id="txtFromDate" maxlength="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcCheckListVO.fromDate }" />"
			onkeypress="digitFormat(this);dateFormat(this);"
			onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
			onblur="onBlur(this);chkValidDate(this);" />
			 <img src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open(event,"txtFromDate",375,570)'
			style="cursor: pointer;" /></td>
		<td width="35%"><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message> <input type="text"
			name="txtToDate" id="txtToDate" maxlength="10" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${lObjTrnPnsnProcCheckListVO.toDate}" />"
			onkeypress="digitFormat(this);dateFormat(this);" onKeyPress="numberFormat(event)" onfocus="onFocus(this)"
			onblur="onBlur(this);chkValidDate(this);compareDates(txtFromDate,this,'Certification To Date should be greater than Certification From Date.','<')" />
			 <img src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open(event,"txtToDate",375,570)'
			style="cursor: pointer;" /></td></tr>
	</table>


<br></br>
<hdiits:button name="update" type="button" id="update" value="Update" onclick="UpdateTableDetail()"/>
<hdiits:button name="CertDtlsAddRow" type="button" id="CertDtlsAddRow" value="Add Certificate" style="width:10%"    onclick="CertiDtlsTableAddRow();" />
	
	
<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 70%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table id="tblCertDtls" width="95%" cellspacing="0"	border="1">

	<tr class="datatableheader" style="width: 80%">
		<td width="20%" class="HLabel">Description<label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel">Office Name
			<label class="mandatoryindicator">*</label></td>
		<!--<td width="20%" class="HLabel" ><fmt:message key="PPROC.FROM"
			bundle="${pensionLabels}"></fmt:message><label
			class="mandatoryindicator">*</label></td>
		<td width="20%" class="HLabel" ><fmt:message key="PPROC.TO"
			bundle="${pensionLabels}"></fmt:message>
			<label class="mandatoryindicator">*</label></td>-->
		<td width="20%" class="HLabel">Delete Row</td>
		<td width="20%" class="HLabel">Update Row <input type="hidden" id="rowCount" name="rowCount"  ></input></td>
		
 		</tr>
				<c:choose>
					<c:when test="${resValue.lLstTrnPnsnProcCheckList !=null}">
						<c:forEach var="CheckListVO" items="${resValue.lLstTrnPnsnProcCheckList}" varStatus="Counter">
						<tr>
						<td class="tds" align="center">
						<c:forEach var="certificateTypeList" items="${resValue.lLstCertificate}">
						<c:choose>
							<c:when test="${CheckListVO.certificateTypeId == certificateTypeList.lookupId}">
								<input type="text" name="txtCert" id="txtCert${Counter.index}" class='${Counter.index}' size="20" readonly="readonly"   style="width:60px" value="${certificateTypeList.lookupName}">
							</c:when>
						</c:choose>
						</c:forEach>
						</td>
						<td class="tds" align="center">						
						<input type="text" name="txtOfficeName" id="txtOfficeName${Counter.index}"  class='${Counter.index}' readonly="readonly"  style="width:90px" value="${CheckListVO.officeName}">												
						</td>
						<!--<td class="tds" align="center" >
								<input type="text" name="txtDateOfFrom"  class='${Counter.index}' id="txtDateOfFrom${Counter.index}" readonly="readonly"  onkeypress="numberFormat(event);digitFormat(this);dateFormat(this);"   style="width:90px"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${CheckListVO.fromDate}"/>" />			
						</td>
						<td class="tds" align="center">
								<input type="text" name="txtDateOfTo"  class='${Counter.index}' id="txtDateOfTo${Counter.index}" readonly="readonly" onkeypress="numberFormat(event);digitFormat(this);dateFormat(this);"  style="width:90px"   maxlength="10"  class="texttag, textString" tabindex="37" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${CheckListVO.toDate}"/>">
						</td>-->
							<td class="tds" align="center">
								<img name="Image" id="Image${Counter.index}" src="images/CalendarImages/DeleteIcon.gif"  onclick="RemoveTableRow(this,'tblCertDtls')" />								
							</td>
							<td class="tds" align="center">
								<input type="button"  id="btnUpdate${Counter.index}"   class='${Counter.index}' name="btnUpdate" value="Update" onClick="UpdateTableRow('${Counter.index}')" />
								<input type="hidden" class='${Counter.index}' name="cmbCertId" value="${CheckListVO.certificateTypeId}"  maxlength="10" id="cmbCertId${Counter.index}"   />
								<input type="hidden" class='${Counter.index}' name="cmbOfficeId" value="${CheckListVO.officeName}" maxlength="10"  id="cmbOfficeId${Counter.index}"   />
								<input type="hidden" class='${Counter.index}' id="count'${Counter.index}'" name="count" value='${Counter.index}' />  
							</td>
						</tr>
					<script>
						document.getElementById("hidGridSize").value=Number('${Counter.index}') + 1;
					</script>
					</c:forEach>
					</c:when>
				</c:choose>
</table>
</div>
</fieldset>

<fieldset class="tabstyle">
<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
	<jsp:param name="attachmentName" value="scan" />
	<jsp:param name="formName" value="PensionInwardForm" />
	<jsp:param name="attachmentType" value="Document" />
	<jsp:param name="attachmentTitle" value="Certificates" />	
	<jsp:param name="multiple" value="N" />
	<jsp:param name="removeAttachmentFromDB" value="Y" />
</jsp:include>
<br><br>
</fieldset>
<br><br>
<!-- Provisional -->
<c:if test="${pensionCaseType=='Provisional'}" >
<fieldset style="width: 100%" class="tabstyle">

<legend id="headingMsg">
		<b> Provisional Details</b>
	</legend> 
<table > 
	<tr>
	<td>Order No</td>
	<td>
	<input type="text"  id="orderno" value="${orderno}" />
	<label class="mandatoryindicator">*</label>
	</td>
	</tr>
	
	<tr>
	<td>Order Date</td>
	<td>
 
				<input type="text" id="orderDate" 
	 				 onkeypress="digitFormat(this);dateFormat(this);" 
		 				  maxlength="10" onKeyPress="numberFormat(this)" 
		 				  onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"
		 				      value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"  value="${orderDate}" />"
		 				
		 			 />&nbsp;
	 				 <img id="imgDateofDeath" src='images/CalendarImages/ico-calendar.gif' 
	 				 onClick='window_open("orderDate",375,570)' style="cursor: pointer;"/>
	 			 <label class="mandatoryindicator">*</label>  
	
	</td>
	</tr>
	</table><br>
	<table width="100%">
<tr>
		<td>
 	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="Prov" />
				<jsp:param name="formName" value="PensionInwardForm" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Provisional Certificate" />
				<jsp:param name="attachmentSize" value="1" /> 
				<jsp:param name="multiple" value="N" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include>
			
			</td>
</tr>
</table>
<br>
</fieldset>
</c:if>
<br><br>

<!-- Provisional end-------------------- -->

<%}catch(Exception e){
e.printStackTrace();
}%>
