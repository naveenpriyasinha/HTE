<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/pramukhlib.js"></script>

<script>
	function pinCodeValidation() {
		var pinCode = document.getElementById("txtPinCode").value;
		if (pinCode.length != '6') {

			alert('Pin code should be of 6 digits.');
			document.getElementById("txtPinCode").value = '';
			return false;
		}
		if (pinCode.charAt(0) != '4') {
			alert('Please enter valid Pin code.');
			document.getElementById("txtPinCode").value = '';
			document.getElementById("txtPinCode").focus();
			return false;
		}
	}

	function validateMobileNumber() {

		var mobNo = document.getElementById("txtCellNo").value.trim();
		//alert(mobNo);
		//alert(mobNo.charAt(0));
		//alert(mobNo.length);
		if (mobNo.length != '10') {

			alert('Mobile number should be of 10 digits.');
			document.getElementById("txtCellNo").value = '';
			return false;
		}
		if (mobNo.charAt(0) != '7' && mobNo.charAt(0) != '8'
				&& mobNo.charAt(0) != '9') {
			alert('Please enter a valid Mobile Number');
			document.getElementById("txtCellNo").value = '';
			return false;
		}
	}

	function chkPANalreadyExists() {
		///alert('Inside chkPANalreadyExists');
		var panNo = document.getElementById("txtPANNo").value.trim();

		var uri = 'ifms.htm?actionFlag=chkPANalreadyExists';
		var url = 'panNo=' + panNo;

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getResponsePANNo(myAjax, panNo);

			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}

	function getResponsePANNo(myAjax, panNo) {

		//alert("hiii checdksdsd");
		var status;
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		var empName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		///	alert('checkFlag'+empName);
		//alert("checkFlag"+checkFlag);
		if (checkFlag == "Y") {
			alert('Entered PAN No:' + panNo
					+ ' is already present for the employee :' + empName
					+ ' in system. Please enter correct PAN number.');
			document.getElementById("txtPANNo").value = "";
			///clearAllData();
			return false;
		}
		return true;
	}

	function clearAllData() {
		document.getElementById("txtPANNo").value = "";

	}
</script>
<style>/*  added by Pratik 03-08-23 */
table.newregperDetailsTb td input {
	max-width: 250px;
}
</style>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="empGenderEmp" value="${resValue.empGenderEmp}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="parentDeptByDefault" value="${resValue.listParentDept[0]}"></c:set>
<c:set var="UserList" value="${resValue.UserList}" />
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:if test="${resValue.EditForm != null && (resValue.EditForm == '78')}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page"
		value="style='display: none;' "></c:set>
</c:if>



<c:if test="${resValue.EditForm == null && resValue.EditForm != '78'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>

<c:if
	test="${resValue.User == 'ZPDDOAsst' && resValue.ZPFormStatus != 0}">
	<c:set var="varMoreQualificationtxtBox" scope="page"
		value="style='display: none;' "></c:set>
</c:if>

<c:if test="${EMPVO.UIDNo != null && EMPVO.UIDNo != ''}">

	<c:set var="varEIDReadOnly" scope="page" value="readonly='readonly'"></c:set>
</c:if>

<c:if test="${EMPVO.EIDNo != null && EMPVO.EIDNo != ''}">
	<c:set var="varUIDReadOnly" scope="page" value="readonly='readonly'"></c:set>
</c:if>

<fieldset class="tabstyle">
	<legend>
		<fmt:message key="CMN.EMPDETAILS" bundle="${dcpsLables}"></fmt:message>
	</legend>


	<table width="100%" align="center" cellpadding="4" cellspacing="4"
		border="0" class="newregperDetailsTb">
		<!-- class added by Pratik 03-08-23 -->

		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.UIDNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<c:set var="UIDNO1" value="${fn:substring(EMPVO.UIDNo,0,4)}" />
			<c:set var="UIDNO2" value="${fn:substring(EMPVO.UIDNo,4,8)}" />
			<c:set var="UIDNO3" value="${fn:substring(EMPVO.UIDNo,8,12)}" />

			<td width="35%" align="left"><input type="text" id="txtUIDNo1"
				size="4" maxlength="4" name="txtUIDNo1" value="${UIDNO1}"
				onblur="IDValidation();" onkeypress="digitFormat(this);"
				onKeyUp="return autoTab(this, 4, event);" ${varDisabled}
				${varUIDReadOnly } /> <input type="text" id="txtUIDNo2" size="4"
				maxlength="4" name="txtUIDNo2" value="${UIDNO2}"
				onblur="IDValidation();" onkeypress="digitFormat(this);"
				onKeyUp="return autoTab(this, 4, event);" ${varDisabled}
				${varUIDReadOnly } /> <input type="text" id="txtUIDNo3" size="4"
				maxlength="4" name="txtUIDNo3" value="${UIDNO3}"
				onblur="IDValidation();validateUIDUniqe();"
				onkeypress="digitFormat(this);"
				onKeyUp="return autoTab(this, 4, event);" ${varDisabled}
				${varUIDReadOnly } /></td>

			<td width="15%" align="left"><fmt:message key="CMN.EIDNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text" id="txtEIDNo"
				size="48" maxlength="40" name="txtEIDNo" value="${EMPVO.EIDNo}"
				onkeypress="eidFormat(this);" onblur="IDValidation();"
				onKeyUp="EIDValidation();" ${varDisabled} ${varEIDReadOnly } /></td>
		</tr>
		<tr>
			<td colspan="4" style="font-size: smaller; font-style: italic"
				${varLabelDisabled}><fmt:message key="CMN.UIDEIDMSG"
					bundle="${dcpsLables}"></fmt:message></td>
		</tr>
		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.SALUTATION"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><select name="cmbSalutation"
				id="cmbSalutation" style="width: 360px;" ${varDisabled}
				onchange="checkGender();">
					<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="Salutation" items="${resValue.listSalutation}">
						<c:choose>
							<c:when test="${EMPVO!=null}">
								<c:choose>
									<c:when test="${EMPVO.salutation == Salutation.lookupId}">
										<option value="${Salutation.lookupId}" selected="selected"><c:out
												value="${Salutation.lookupDesc}"></c:out></option>
									</c:when>
									<c:otherwise>
										<option value="${Salutation.lookupId}"><c:out
												value="${Salutation.lookupDesc}"></c:out></option>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<option value="${Salutation.lookupId}"><c:out
										value="${Salutation.lookupDesc}"></c:out></option>
							</c:otherwise>
						</c:choose>

					</c:forEach>
			</select> <label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
			<td width="15%" align="left"><fmt:message key="CMN.FULLNAME"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				onkeypress="nameFormat(this);checkforConsecutiveSpaces();"
				id="txtName" style="text-transform: uppercase" size="48"
				maxlength="99" name="txtName"
				onblur="isName(txtName,'This field should not contain any special characters or digits.');checkIfNameExist();"
				value="${EMPVO.name}" ${varDisabled} /> <label
				class="mandatoryindicator" ${varLabelDisabled}> *</label></td>

		</tr>

		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.BUCKLENO"
					bundle="${dcpsLables}"></fmt:message></td>

			<td width="35%" align="left"><input type="text"
				name="txtBuckleNo" id="txtBuckleNo" value="${EMPVO.buckleNo}"
				onkeypress="" maxlength="50" size="48" ${varDisabled} /></td>

			<!--<td width="15%">&nbsp;</td><td width="20%">&nbsp;</td>-->
			<td colspan="2" style="font-size: smaller; font-style: italic"
				${varLabelDisabled}><fmt:message key="CMN.NAMEMSGFINAL1"
					bundle="${dcpsLables}"></fmt:message> <img src='images/Correct.jpg'>
				<fmt:message key="CMN.NAMEMSGFINAL2" bundle="${dcpsLables}"></fmt:message>
				<img src='images/Cross.jpg'> <fmt:message
					key="CMN.NAMEMSGFINAL3" bundle="${dcpsLables}"></fmt:message></td>

		</tr>

		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.FULLNAMEDEV"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				name="txtNameInMarathi" id="txtNameInDevanagari"
				value="${EMPVO.name_marathi}" size="48" ${varDisabled}
				onkeypress="nameFormat(this);" /><label class="mandatoryindicator"
				${varLabelDisabled}> *</label></td>

			<script language="javascript" type="text/javascript">
				pph = new PramukhPhoneticHandler();
				pph.convertToIndicIME("txtPnsnrNameInMarathi", document
						.getElementById('txtNameInDevanagari'), 'devanagari');
			</script>

			<td width="15%" align="left"><fmt:message
					key="CMN.FATHERHUSBANDNAME" bundle="${dcpsLables}"></fmt:message></td>

			<td width="35%" align="left"><input type="text"
				name="txtFatherOrHusband" id="txtFatherOrHusband"
				style="text-transform: uppercase" value="${EMPVO.father_or_husband}"
				size="48" ${varDisabled} onkeypress="nameFormat(this);" /> <label
				class="mandatoryindicator" ${varLabelDisabled}> *</label></td>


		</tr>

		<tr ${varLabelDisabled}>
			<td colspan="2"></td>
			<td colspan="2" style="font-size: smaller; font-style: italic"><fmt:message
					key="CMN.FATHERNAMEMSG" bundle="${dcpsLables}"></fmt:message></td>
		</tr>

		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.GENDER"
					bundle="${dcpsLables}"></fmt:message></td>

			<c:set var="Male" value="M"></c:set>
			<c:set var="Female" value="F"></c:set>

			<c:choose>
				<c:when test="${EMPVO!=null}">
					<c:choose>

						<c:when test="${EMPVO.gender == 77}">
							<td width="20%" align="left"><fmt:message key="CMN.MALE"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="M" checked="checked"
								${varDisabled} onclick="checkGender();" /> <fmt:message
									key="CMN.FEMALE" bundle="${dcpsLables}"></fmt:message> <input
								type="radio" id="radioGender" name="radioGender" value="F"
								${varDisabled} onclick="checkGender();" /> <fmt:message
									key="CMN.TRANSGENDER" bundle="${dcpsLables}"></fmt:message> <input
								type="radio" id="radioGender" name="radioGender" value="T"
								${varDisabled} onclick="checkGender();" /> <label
								class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
						</c:when>

						<c:when test="${EMPVO.gender == 70}">
							<td width="20%" align="left"><fmt:message key="CMN.MALE"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="M" ${varDisabled}
								onclick="checkGender();" /> <fmt:message key="CMN.FEMALE"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="F" checked="checked"
								${varDisabled} onclick="checkGender();" /> <fmt:message
									key="CMN.TRANSGENDER" bundle="${dcpsLables}"></fmt:message> <input
								type="radio" id="radioGender" name="radioGender" value="T"
								${varDisabled} onclick="checkGender();" /> <label
								class="mandatoryindicator" ${varLabelDisabled}> *</label></td>
						</c:when>

						<c:otherwise>
							<td width="20%" align="left"><fmt:message key="CMN.MALE"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="M" ${varDisabled}
								onclick="checkGender();" /> <fmt:message key="CMN.FEMALE"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="F" ${varDisabled}
								onclick="checkGender();" /> <fmt:message key="CMN.TRANSGENDER"
									bundle="${dcpsLables}"></fmt:message> <input type="radio"
								id="radioGender" name="radioGender" value="T" checked="checked"
								${varDisabled} onclick="checkGender();" /> <label
								class="mandatoryindicator" ${varLabelDisabled}>*</label></td>
						</c:otherwise>
					</c:choose>

				</c:when>

				<c:otherwise>

					<td width="35%" align="left"><fmt:message key="CMN.MALE"
							bundle="${dcpsLables}"></fmt:message> <input type="radio"
						id="radioGender" name="radioGender" value="M" ${varDisabled}
						onclick="checkGender();" /> <fmt:message key="CMN.FEMALE"
							bundle="${dcpsLables}"></fmt:message> <input type="radio"
						id="radioGender" name="radioGender" value="F" ${varDisabled}
						onclick="checkGender();" /> <fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message> <input type="radio"
						id="radioGender" name="radioGender" value="T" ${varDisabled}
						onclick="checkGender();" /> <label class="mandatoryindicator"
						${varLabelDisabled}>*</label></td>
				</c:otherwise>

			</c:choose>

			<td width="15%" align="left"><fmt:message key="CMN.DOB"
					bundle="${dcpsLables}"></fmt:message></td>

			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
				value="${EMPVO.dob}" var="empBirthDate" />

			<td width="35%" align="left" style="font-size: smaller"><input
				type="hidden" name="currDate1" id="currDate1"
				value="${resValue.lDtCurDate}" /> <input type="text"
				name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="populateGroup();validateDate(txtBirthDate);compareDates(this,document.getElementById('currDate1'),'Date of Birth should be less than current date.','<');
					compareDates(txtBirthDate,txtJoiningDate,'Date Of Joining should be greater than DOB!','<');"
				value="${empBirthDate}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /> <label
				class="mandatoryindicator" ${varLabelDisabled}>*</label> <fmt:message
					key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>

		</tr>



		<tr>

			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
				value="${EMPVO.doj}" var="empJoiningDate" />

			<td width="15%" align="left"><fmt:message key="CMN.DOJ"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left" style="font-size: smaller"><input
				type="hidden" name="joinDate" id="joinDate"
				value="${resValue.lDtJoiDtLimit}" /> <input type="text"
				name="txtJoiningDate" id="txtJoiningDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtJoiningDate);
				changeDcpsOrGpfRadio();
				
				compareDates(this,document.getElementById('currDate1'),'Date of Joining should be less than current date.','<');
				compareDates(txtJoiningDate,txtJoinParentDeptDate,'Date of joining parent dept should be greater than Joining Date','<');
				compareDates(txtJoiningDate,txtJoinPostDate,'Date of joining current post should be greater than date of joining','<');
				checkGISRegristrationDate();"
				value="${empJoiningDate}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoiningDate", 375, 570)'
				style="cursor: pointer;" ${varImageDisabled} /> <label
				class="mandatoryindicator" ${varLabelDisabled}>*</label> <fmt:message
					key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>

			<td width="15%" align="left"><fmt:message key="CMN.HANDICAPPED"
					bundle="${dcpsLables}"></fmt:message></td>


			<td width="35%" align="left"><c:choose>
					<c:when test="${EMPPAYROLLVO != null}">
						<c:choose>
							<c:when test="${EMPPAYROLLVO.phychallanged == 'TRUE'}">
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message>
								<input type="radio" id="radioHandic" name="radioHandic"
									value="TRUE" checked="checked" ${varDisabled}
									onclick="popUpPhyDtlsScreen();" />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<input type="radio" id="radioHandic" name="radioHandic"
									value="FALSE" ${varDisabled} />
							</c:when>
							<c:otherwise>
								<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message>
								<input type="radio" id="radioHandic" name="radioHandic"
									value="TRUE" ${varDisabled} onclick="popUpPhyDtlsScreen();" />
								<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
								<input type="radio" id="radioHandic" name="radioHandic"
									value="FALSE" checked="checked" ${varDisabled} />
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<fmt:message key="CMN.YES" bundle="${dcpsLables}"></fmt:message>
						<input type="radio" id="radioHandic" name="radioHandic"
							value="TRUE" ${varDisabled} onclick="popUpPhyDtlsScreen();" />
						<fmt:message key="CMN.NO" bundle="${dcpsLables}"></fmt:message>
						<input type="radio" id="radioHandic" name="radioHandic"
							value="FALSE" checked="checked" ${varDisabled} />
					</c:otherwise>
				</c:choose> <c:choose>
					<c:when test="${EMPPAYROLLVO != null}">
						<input type="hidden" id="hidPTApplicableForPhyHandi"
							name="hidPTApplicableForPhyHandi"
							value="${EMPPAYROLLVO.phyPTApplicable}" />
					</c:when>
					<c:otherwise>
						<input type="hidden" id="hidPTApplicableForPhyHandi"
							name="hidPTApplicableForPhyHandi" value="F" />
					</c:otherwise>
				</c:choose></td>
		</tr>


		<tr>
			<td width="15%" align="left">Address Building</td>

			<td width="35%" align="left"><input type="text"
				name="txtAddressBuilding" id="txtAddressBuilding"
				value="${EMPVO.building_address}" onkeypress="addressFormat(this);"
				maxlength="60" size="48" ${varDisabled} /></td>

			<td width="15%" align="left">Address Street</td>

			<td width="35%" align="left"><input type="text"
				name="txtAddressStreet" id="txtAddressStreet"
				value="${EMPVO.building_street}" onkeypress="addressFormat(this);"
				maxlength="60" size="48" ${varDisabled} /></td>
		</tr>

		<tr>
			<td width="15%" align="left">Landmark</td>

			<td width="35%" align="left"><input type="text"
				onkeypress="addressFormat(this);" maxlength="60" name="txtLandmark"
				id="txtLandmark" value="${EMPVO.landmark}" size="48" ${varDisabled} />
			</td>

			<td width="15%" align="left">Locality</td>

			<td width="35%" align="left"><input type="text"
				name="txtLocality" id="txtLocality" value="${EMPVO.locality}"
				onkeypress="addressFormat(this);" maxlength="60" size="48"
				${varDisabled} /></td>
		</tr>

		<tr>
			<td width="15%" align="left">District</td>

			<td width="35%" align="left"><input type="text"
				name="txtDistrict" id="txtDistrict" value="${EMPVO.district}"
				onkeypress="districtFormat(this);" maxlength="50" size="48"
				${varDisabled} /></td>

			<td width="15%" align="left"><fmt:message key="CMN.ADDRESSVTC"
					bundle="${dcpsLables}"></fmt:message></td>

			<td width="35%" align="left"><input type="text"
				name="txtAddressVTC" id="txtAddressVTC" value="${EMPVO.addressVTC}"
				onkeypress="addressFormat(this);" maxlength="50" size="48"
				${varDisabled} /></td>


		</tr>

		<tr>

			<td width="15%" align="left">State</td>

			<td width="20%" align="left"><select name="cmbState"
				id="cmbState" style="width: 360px" ${varDisabled}
				disabled="disabled">
					<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="stateName" items="${resValue.STATENAMES}">

						<c:choose>
							<c:when test="${EMPVO!=null}">
								<c:choose>
									<c:when test="${stateName.id==EMPVO.state}">
										<option value="${stateName.id}" selected="selected">${stateName.desc}</option>
									</c:when>
									<c:otherwise>
										<option value="${stateName.id}">${stateName.desc}</option>
									</c:otherwise>
								</c:choose>
							</c:when>

							<c:otherwise>
								<c:choose>
									<c:when test="${stateName.id == 15}">
										<option value="${stateName.id}" selected="selected">${stateName.desc}</option>
									</c:when>
									<c:otherwise>
										<option value="${stateName.id}">${stateName.desc}</option>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>

					</c:forEach>
			</select> <label class="mandatoryindicator" ${varLabelDisabled}>*</label></td>

			<td width="15%" align="left">Pin Code</td>

			<td width="35%" align="left"><input type="text"
				name="txtPinCode" id="txtPinCode" value="${EMPVO.pincode}" size="48"
				maxlength="6" ${varDisabled} onkeypress="digitFormat(this);"
				onblur="pinCodeValidation();" /></td>

		</tr>

		<tr>

			<td width="15%" align="left"><fmt:message key="CMN.CONTACTTELNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				id="txtContactTelNo" size="48" name="txtContactTelNo"
				value="${EMPVO.cntctNo}" maxlength="10"
				onkeypress="digitFormat(this);"
				onblur="checkLength(txtContactTelNo,'Telephone number');"
				${varDisabled} /></td>
			<td width="15%" align="left"><fmt:message key="CMN.CELLNO"
					bundle="${dcpsLables}"></fmt:message></td>

			<td width="35%" align="left"><input type="text" id="txtCellNo"
				maxlength="10" size="48" name="txtCellNo"
				onblur="validateMobileNumber();checkLength(txtCellNo,'Cell Number');"
				onkeypress="digitFormat(this);" value="${EMPVO.cellNo}"
				${varDisabled} /></td>
		</tr>

		<tr ${varLabelDisabled}>
			<td colspan="3" align="left"
				style="font-size: smaller; font-style: italic"><fmt:message
					key="MSG.TEL1" bundle="${dcpsLables}"></fmt:message> <img
				src='images/Correct.jpg'> <fmt:message key="MSG.TEL2"
					bundle="${dcpsLables}"></fmt:message> <img src='images/Cross.jpg'>
				<fmt:message key="MSG.TEL3" bundle="${dcpsLables}"></fmt:message></td>
			<td></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.EMAIL"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text"
				name="txtEmailId" id="txtEmailId" value="${EMPVO.emailId}"
				onkeypress="emailFormat(this);" maxlength="254" size="48"
				onblur="validateEmailID(txtEmailId,'Please Enter Valid Email');"
				${varDisabled} /></td>

			<td width="15%" align="left"><fmt:message key="CMN.PANNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><input type="text" id="txtPANNo"
				maxlength="10" size="48" name="txtPANNo" value="${EMPVO.PANNo}"
				onblur="panNoValidation();chkPANalreadyExists();" ${varDisabled} />
			</td>

		</tr>
		<!-- Added For Qualification -->



		<tr>
			<td width="15%" align="left"><fmt:message
					key="CMN.Qualification" bundle="${dcpsLables}"></fmt:message></td>
			<td width="35%" align="left"><select name="qualification"
				id="qualification" ${varDisabled}>
					<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="qualification"
						items="${resValue.QualificationList}">

						<c:choose>
							<c:when test="${EMPVO!=null}">

								<c:choose>
									<c:when test="${qualification==EMPVO.qualification}">
										<option value="${qualification}" selected="selected">${qualification}</option>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${qualification!=null}">
												<option value="${qualification}">${qualification}</option>
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${qualification!=null}">
										<option value="${qualification}">${qualification}</option>
									</c:when>
								</c:choose>

							</c:otherwise>
						</c:choose>

						<!--<c:choose>
					<c:when test="${qualification!=null}">
						<option value="${qualification}">${qualification}</option>
					</c:when>
				</c:choose>
			-->
					</c:forEach>
			</select></td>

		</tr>


		<tr>
			<td width="15%" align="left">Secondary Qualification</td>
			<td width="10%" align="left"><select name="morequalification"
				id="morequalification" ${varDisabled} multiple="multiple"
				onchange="onchangevalues();">
					<option value="-1"><fmt:message
							key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="qualification"
						items="${resValue.QualificationList}">

						<c:choose>
							<c:when test="${EMPVO!=null}">

								<c:choose>
									<c:when test="${qualification==EMPVO.qualification}">
										<option value="${qualification}" selected="selected">${qualification}</option>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${qualification!=null}">
												<option value="${qualification}">${qualification}</option>
											</c:when>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${qualification!=null}">
										<option value="${qualification}">${qualification}</option>
									</c:when>
								</c:choose>

							</c:otherwise>
						</c:choose>

						<!--<c:choose>
					<c:when test="${qualification!=null}">
						<option value="${qualification}">${qualification}</option>
					</c:when>
				</c:choose>
			-->
					</c:forEach>
			</select></td>
		</tr>

		<tr>
			<td colspan="4" style="font-size: smaller; font-style: italic"
				${varLabelDisabled}><fmt:message key="MSG.QUALIFICATION"
					bundle="${dcpsLables}"></fmt:message></td>
		</tr>

		<tr>
			<input type="hidden" id="txtmoreQualification"
				name="txtmoreQualification" value="" />
			<td style="align: left; padding-top: -20px;"
				${varMoreQualificationtxtBox}>Added Qualification :
				<table>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>

			</td>
			<td colspan="3" id="moreQualificationtxtBox" align="left"><input
				type="text" style="width: 700px;" id="moreQualificationtxtBox"
				name="moreQualificationtxtBox"
				value="${EMPVO.moreQualification},${EMPVO.qualification}"
				${varMoreQualificationtxtBox} />

				<table>
					<tr>
						<td ${varMoreQualificationtxtBox} colspan="2"
							style="font-size: smaller; font-style: italic"
							${varLabelDisabled}><fmt:message key="MSG.QUALIFICATION1"
								bundle="${dcpsLables}"></fmt:message></td>
					</tr>
				</table></td>
		</tr>



	</table>
</fieldset>