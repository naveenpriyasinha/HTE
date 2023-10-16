<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/ChangeParentDept.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="EMPVO" value="${resValue.lObjMstEmp}" />

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="ChangeParentDeptOfEmpForm" id="ChangeParentDeptOfEmpForm" encType="multipart/form-data"
	validate="true" method="post">

<fieldset class="tabstyle">
<legend><b><font color="red"><fmt:message key="CMN.CURRENTDETAILS" bundle="${dcpsLables}"></fmt:message></font></b></legend>
<br/>
		<fieldset class="tabstyle">
		<legend><b><fmt:message key="CMN.PARENTDEPARTMENT" bundle="${dcpsLables}"></fmt:message></b></legend>
			<table>
				<tr>
					<td align="left">
						<fmt:message key="CMN.ADMNDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left">
						<select name="cmbAdminDeptOld" id="cmbAdminDeptOld" disabled="disabled">
								<c:forEach var="adminDeptOld" items="${resValue.lListParentDept}">
									<option value="${adminDeptOld.id}" selected="selected"><c:out value="${adminDeptOld.desc}"></c:out></option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<fmt:message key="CMN.FIELDHODDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left">
						<select name="cmbFieldDeptOld" id="cmbFieldDeptOld" disabled="disabled">
								<c:forEach var="fieldDeptOld" items="${resValue.lListFieldHODDept}">
									<option value="${fieldDeptOld.id}" selected="selected"><c:out value="${fieldDeptOld.desc}"></c:out></option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left"><fmt:message key="CMN.DOB"
					bundle="${dcpsLables}"></fmt:message></td>
						
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVO.dob}" var="empBirthDate"/>
				
					<td align="left" style="font-size : smaller"><input type="text"
						name="txtBirthDate1" id="txtBirthDate1" maxlength="10"
						 value="${empBirthDate}" readonly="readonly"  /> 
					<fmt:message key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>
				</tr>
				
				<tr>
					<td align="left"><fmt:message key="CMN.UIDNO"
						bundle="${dcpsLables}"></fmt:message></td>
					
					<c:set var ="UIDNO11" value="${fn:substring(EMPVO.UIDNo,0,4)}"/>
					<c:set var ="UIDNO21" value="${fn:substring(EMPVO.UIDNo,4,8)}"/>
					<c:set var ="UIDNO31" value="${fn:substring(EMPVO.UIDNo,8,12)}"/> 
						
					<td align="left"><input type="text" id="txtUIDNo11"
						size="4" maxlength="4"  name="txtUIDNo11" value="${UIDNO11}" readonly="readonly"/> <input type="text" id="txtUIDNo21"
						size="4"  maxlength="4" name="txtUIDNo21" value="${UIDNO21}" readonly="readonly"/> <input type="text" id="txtUIDNo31"
						size="4"  maxlength="4" name="txtUIDNo31" value="${UIDNO31}" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
</fieldset>
<br></br>

<fieldset class="tabstyle"><legend><b><font color="red"><fmt:message key="CMN.REVISEDDETAILS" bundle="${dcpsLables}"></fmt:message></font></b></legend>
<br/>
		<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.PARENTDEPARTMENT" bundle="${dcpsLables}"></fmt:message></b></legend>
			<table border="0" >
				<tr>
					<td align="left" width="20%" >
						<fmt:message key="CMN.ADMNDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td style="padding-left: 6%"> 
						<select name="cmbAdminDept" id="cmbAdminDept" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					         				<c:forEach var="AdminDpt" items="${resValue.lLstAdminDept}" >
					         					<option value="${AdminDpt.id}">${AdminDpt.desc}</option>
					         				</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%"><fmt:message key="CMN.FIELDDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td style="padding-left: 6%">
						<select name="cmbFieldDept" id="cmbFieldDept" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						</select>
					</td>
				</tr>
				
					<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.DOB"
					bundle="${dcpsLables}"></fmt:message></td>
				
					<td align="left" style="font-size : smaller;padding-left: 6%"><input type="hidden" name="currDate1"
						id="currDate1" value="${resValue.lDtCurDate}" />
						<input type="hidden" id="txtJoiningDate" name="txtJoiningDate" value="${EMPVO.doj}"/>
						<input type="text"
						name="txtBirthDate" id="txtBirthDate" maxlength="10"
						onkeypress="digitFormat(this);dateFormat(this);" 
							onBlur="validateDate(txtBirthDate);compareDates(this,document.getElementById('currDate1'),'Date of Birth should be less than current date.','<');
							compareDates(txtBirthDate,txtJoiningDate,'Date Of Joining should be greater than DOB!','<');" value=""  /> 
						<img src='images/CalendarImages/ico-calendar.gif' width='20'
						onClick='window_open("txtBirthDate",375,570)'
						style="cursor: pointer;" />
					<fmt:message key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>
				</tr>
				
				<tr>
					<td width="20%" align="left"><fmt:message key="CMN.UIDNO"
				bundle="${dcpsLables}"></fmt:message></td>
				
					<td align="left" style="padding-left: 6%" >
						<!--  don't delete below line -->
						<input type="hidden" id="txtEIDNo" value="" />
					<input type="text" id="txtUIDNo1"
						size="4" maxlength="4"  name="txtUIDNo1" value="" onblur="" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);"  /> <input type="text" id="txtUIDNo2"
						size="4"  maxlength="4" name="txtUIDNo2" value="" onblur="" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);"  /> <input type="text" id="txtUIDNo3"
						size="4"  maxlength="4" name="txtUIDNo3" value="" onblur="" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);" /></td>
				</tr>
			</table>
		</fieldset>
</fieldset>

<input type="hidden" id="hidTreasuryCode" value="${resValue.lStrTreasuryCode}"/>
<input type="hidden" id="hidDdoCode" value="${resValue.lStrDdoCode}"/>
<input type="hidden" id="hidEmpId" value="${resValue.lStrEmpId}"/>

</br>

<div align="center">
<hdiits:button name="btnSave" id="btnSave" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="changeParentDeptOfEmp();"/>
<hdiits:button name="btnBack" id="btnBack" type="button" captionid="BTN.BACK" bundle="${dcpsLables}" onclick="goBackToMainChangePFD();"/>
</div>

</hdiits:form>

<ajax:select source="cmbAdminDept" target="cmbFieldDept"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popFieldDept"
	parameters="cmbAdminDept={cmbAdminDept}">
</ajax:select>

