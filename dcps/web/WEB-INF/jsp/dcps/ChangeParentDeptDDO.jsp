<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/ChangeParentDept.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="DdoDetails" value="${resValue.DdoDetails}"></c:set>

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

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
							<option value="${DdoDetails.deptLocCode}" selected="selected"><c:out value="${resValue.AdminDeptDesc}"></c:out></option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<fmt:message key="CMN.FIELDHODDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left">
						<select name="cmbFieldDeptOld" id="cmbFieldDeptOld" disabled="disabled">
							<option value="${DdoDetails.hodLocCode}" selected="selected"><c:out value="${resValue.FieldDeptDesc}"></c:out></option>
						</select>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="tabstyle">
		<legend><b><fmt:message key="CMN.SIGNINGAUTHO" bundle="${dcpsLables}"></fmt:message></b></legend>
			<table>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.DDONAME" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 75px" >
						<input type="text" id="txtDDOName" name="txtDDOName" value="${DdoDetails.ddoName}" size="30" disabled="disabled"/>
						<label class="mandatoryindicator">*</label>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.DESIGNATION" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 75px" >
						<select name="cmbDesignation" id="cmbDesignation" disabled="disabled" >
							<option value="${DdoDetails.designCode}" selected="selected"><c:out value="${DdoDetails.designName}"></c:out></option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.WEFDATE" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 75px" >
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${DdoDetails.startDate}" var="wefDate"/>
						<input type="text" name="txtWEFDate" id="txtWEFDate" value="${wefDate}" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.TANNO" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 75px" >
						<input type="text" id="txtTANNo" name="txtTANNo" value="${DdoDetails.tanNo}" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CMN.ITOWARDCIR" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 75px" >
						<input type="text" id="txtITWardCircle" name="txtITWardCircle" value="${DdoDetails.itaWardNo}" disabled="disabled" />
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset class="tabstyle">
		<legend><b><fmt:message key="CMN.BANKDETAILS" bundle="${dcpsLables}"></fmt:message></b></legend>
			<table>
				<tr>
					<td align="left" width="20%"><fmt:message key="CMN.BANKNAME" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 13%" >
						<input type="text" name="bankName" id="bankName" value="${resValue.bankName}" size="40" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%"><fmt:message key="CMN.BRANCHNAME" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 13%">
						<input type="text" name="branchName" id="branchName" value="${resValue.branchName}" size="40" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" ><fmt:message key="CNM.ACCNO" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td align="left" style="padding-left: 13%" >
						<input type="text" id="txtAccountNo"  name="txtAccountNo" value="${DdoDetails.accountNo}" size="20" disabled="disabled"/>
					</td>
				</tr>
			</table>
		</fieldset>
</fieldset>

<br/>

<fieldset class="tabstyle"><legend><b><font color="red"><fmt:message key="CMN.REVISEDDETAILS" bundle="${dcpsLables}"></fmt:message></font></b></legend>
<br/>
		<fieldset class="tabstyle"><legend><b><fmt:message key="CMN.PARENTDEPARTMENT" bundle="${dcpsLables}"></fmt:message></b></legend>
			<table>
				<tr>
					<td align="left" width="20%" >
						<fmt:message key="CMN.ADMNDEPT" bundle="${dcpsLables}"></fmt:message>
					</td>
					<td style="padding-left: 6%"> 
						<select name="cmbAdminDept" id="cmbAdminDept" >
							<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
					         				<c:forEach var="AdminDpt" items="${resValue.AdminDept}" >
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
			</table>
		</fieldset>
</fieldset>

<br/>
<div align="center">
<hdiits:button name="btnSave" id="btnSave" type="button" captionid="BTN.SAVE" bundle="${dcpsLables}" onclick="changeParentDeptOfDDO();"/>
</div>

<ajax:select source="cmbAdminDept" target="cmbFieldDept"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=popFieldDept"
	parameters="cmbAdminDept={cmbAdminDept}">
</ajax:select>
