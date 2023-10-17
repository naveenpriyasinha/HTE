var defaultParentDpt = true;
var saveOrUpdateFlag = 0;
var emp_Id = "";
var nomineeSavedOrNot;
var saveOrFwdFlag = 0;
var forwardFlag = false;

function openPerformaA() {
	var urlToOpen = 'images/Proforma A.pdf';
	var mwname = 'IFMS';
	var prop = 'width='
			+ screen.availWidth
			- 100
			+ ',height='
			+ screen.availHeight
			- 100
			+ ',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	window.open(urlToOpen, mwname, prop);
}
function openPerformaB() {
	var urlToOpen = 'images/Proforma B.pdf';
	var mwname = 'IFMS';
	var prop = 'width='
			+ screen.availWidth
			- 100
			+ ',height='
			+ screen.availHeight
			- 100
			+ ',top=0,left=0,resizable=no,menubar=no,scrollbars=yes,toolbar=no,location=no,status=no';
	window.open(urlToOpen, mwname, prop);
}

function popUpDcpsEmpData(dcpsEmpId, ddoFlag, ZPFormStatus) {
	// alert("I m here");
	showProgressbar();
	var lStrUserZP = document.getElementById("User").value;
	var lStrUseZP = document.getElementById("Use").value;
	// lStrUseZP=
	// alert(lStrUseZP);
	var empId = dcpsEmpId;

	/*
	 * if (ddoFlag == "N") { User = "DDO"; } else { User = "Asst"; }
	 */

	// url = "ifms.htm?actionFlag=popUpEmpDtls&elementId=700159&empId=" + empId
	// + "&User=" + User;
	url = "ifms.htm?actionFlag=popUpEmpDtls&elementId=700159&empId=" + empId
			+ "&User=" + lStrUserZP + "&Use=" + lStrUseZP + "&ZPFormStatus="
			+ ZPFormStatus;
	self.location.href = url;

}

// added by Demolisher

function onchangevalues() {
	// alert("within onchangevalues()");
	var str = "", i;
	var qualList = document.forms.DCPSForm;
	// alert(qualList);
	var moreQualification = "";
	// alert("after qualist");
	for (i = 0; i < qualList.morequalification.length; i++) {
		if (qualList.morequalification[i].selected) {
			str = str + DCPSForm.morequalification.options[i].value + ",";
			// alert(str);
			document.getElementById("txtmoreQualification").value = str;
		}
	}
	document.getElementById("txtmoreQualification").value = str;
	// alert(document.getElementById("txtmoreQualification").value);
	// document.getElementById(moreQualification).value=str;
	moreQualification = document.getElementById("txtmoreQualification").value
	alert("Options selected are " + moreQualification);

}

// added by Demolisher

function SaveDataUsingAjaxForDraft() {

	// alert("SaveDataUsingAjaxForDraft() called...");
	showProgressbar();

	if (!validateRegFormDataForDraft()) {
		hideProgressbar();
		return false;
	}
	var saveOrUpdateFlag = 1;

	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		hideProgressbar();
		return;
	}

	var uri = 'ifms.htm?actionFlag=saveDCPSForm';
	var url = runForm(0);
	url = url + "&saveOrUpdateFlag=" + saveOrUpdateFlag;

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				emp_Id = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;

				if (test_Id) {
					nomineeSavedOrNot = submitNomineeDtls(emp_Id);

					if (nomineeSavedOrNot == 1) {
						alert('All the details saved successfully');
					}
					if (nomineeSavedOrNot == 2 && serialNo == 1) {
						alert('All the details saved successfully');
					}
					if (nomineeSavedOrNot == 2 && serialNo > 1) {
						alert('All the details except nominee details saved successfully');
					}
					// changeSaveOrUpdateBtn();

					// self.location.href =
					// "ifms.htm?actionFlag=loadRegistrationForm&User=ZPDDOAsst&elementId=700022";
					self.location.href = "ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
				}
			}
		}
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(url);
	hideProgressbar();
}

function chkEmptyGender() {
	var genderAdded = false;
	for (var i = 0; i < document.forms[0].radioGender.length; i++) {
		if (document.forms[0].radioGender[i].checked) {
			genderAdded = true;
		}
	}
	if (!genderAdded) {
		alert('Please Select Gender');
		return false;
	} else {
		return true;
	}
}

function chkEmptyPayscale() {
	var paycommission = document.forms[0].cmbPayCommission.value;
	var payscale = document.forms[0].cmbPayScale.value;
	if (paycommission == '700015' || paycommission == '700016') {
		if (payscale == -1) {
			alert('Please enter pay scale');
			return false;
		}
	}
	return true;
}

function validateRegFormDataForDraft() {

	/*
	 * if(document.getElementById('txtEIDNo').value == "" &&
	 * document.getElementById('txtUIDNo1').value == "" &&
	 * document.getElementById('txtUIDNo2').value == "" &&
	 * document.getElementById('txtUIDNo3').value == ""){ alert("Please enter
	 * UID or EID"); return false; }
	 */

	if (document.getElementById('txtUIDNo1').value != ""
			&& document.getElementById('txtUIDNo2').value != ""
			&& document.getElementById('txtUIDNo3').value != "") {
		if (document.getElementById('txtUIDNo1').value.length != 4
				|| document.getElementById('txtUIDNo2').value.length != 4
				|| document.getElementById('txtUIDNo3').value.length != 4) {
			alert("Please enter a valid UID");
			return false;
		}
	}

	if (!chkEmpty(document.getElementById('cmbSalutation'), 'Salutation')
			|| !chkEmpty(document.getElementById('txtName'), 'Full Name')
			|| !chkEmpty(document.getElementById('txtNameInDevanagari'),
					'Full Name in Devanagari') ||
			// !chkEmpty(document.getElementById('txtFatherOrHusband'),'Father/Husband
			// Name') ||
			!chkEmpty(document.getElementById('txtBirthDate'), 'Date of Birth')
	/*
	 * !chkEmpty(document.getElementById('txtJoiningDate'),'Date of Joining
	 * Government Service(GOM)')
	 */

	)
	// !chkEmpty(document.getElementById('cmbDesignation'),'Designation') ||
	// !chkEmpty(document.getElementById('cmbPayCommission'),'Pay
	// Commission') ||
	// !chkEmpty(document.getElementById('cmbPayScale'),'Pay Scale'))
	{
		alert("Please fill all the mandatory details to save as Draft");
		return false;
	}

	if (!chkEmptyGender() ||
	// !changeDcpsOrGpfRadio() ||
	!checkEmptyForDeptSelectedInPF() || !checkGISRegristrationDate()) {

		return false;
	}

	if (!checkGender()) {

		return false;
	}

	return true;
}

// Function to not allow user to click on other tabs
function checkMinDtls() {

	var name = document.getElementById("txtName").value;
	var gender = document.getElementById("radioGender").value;
	var dob = document.getElementById("txtBirthDate").value;
	var doj = document.getElementById("txtJoiningDate").value;

	function showAlert(elementId, message) {
		alert(message);
		document.getElementById(elementId).focus();
	}

	if (!name) {
		showAlert("txtName", 'Please fill the Employee Name first');
	} else if (!gender) {
		showAlert("radioGender", 'Please fill the Employee Gender first');
	} else if (!dob) {
		showAlert("txtBirthDate",
				'Please fill the Employee Date Of Birth first');
	} else if (!doj) {
		showAlert("txtJoiningDate", 'Please fill the Employee Date Of Joining');
	} else {
		document.getElementById("officeDtls").rel = "tcontent2";
		document.getElementById("otherDtls").rel = "tcontent3";
		document.getElementById("nomineeDtls").rel = "tcontent4";
		document.getElementById("photoAndSignDtls").rel = "tcontent5";
	}

}

// Reject a request by DDO
function rejectRequest(EmpId) {
	// alert('inside rejectRequest');
	if (EmpId == null || EmpId == "") {
		alert("Please select a form to send back");
	} else {
		// alert('EmpId: '+EmpId);
		var remarks = document.getElementById("txtApproverRemarks").value;
		if (chkEmpty(document.getElementById("txtApproverRemarks"),
				'Approver Remarks') == false) {
			return false;
		}
		xmlHttp = GetXmlHttpObject();

		if (xmlHttp == null) {

			return;
		}
		var url = "ifms.htm?actionFlag=rejectRequest&Emp_Id=" + EmpId
				+ "&remarks=" + remarks;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;

					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

					if (test_Id) {

						var User = document.getElementById("User").value.trim();

						alert('Form is sent back to the ZP DDO Assistant');
						self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsst&elementId=700009&User="
								+ User;
					}
				}
			}
		};
		xmlHttp.open("POST", url, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}
}

// Function to save Data

function validateRegFormData() {
	// Validation - UID or EID cannot be empty removed.
	/*
	 * if(document.getElementById('txtEIDNo').value.trim() == "" &&
	 * document.getElementById('txtUIDNo1').value.trim() == "" &&
	 * document.getElementById('txtUIDNo2').value.trim() == "" &&
	 * document.getElementById('txtUIDNo3').value.trim() == ""){ alert("Please
	 * enter UID or EID"); return false; }
	 */
	// alert("Mujhe bulaya");
	if (document.getElementById('txtUIDNo1').value != ""
			&& document.getElementById('txtUIDNo2').value != ""
			&& document.getElementById('txtUIDNo3').value != "") {
		if (document.getElementById('txtUIDNo1').value.length != 4
				|| document.getElementById('txtUIDNo2').value.length != 4
				|| document.getElementById('txtUIDNo3').value.length != 4) {
			alert("Please enter a valid UID");
			return false;
		}
	}

	if (!chkEmptyWithoutFocus(document.getElementById('cmbSalutation'),
			'Salutation')
			|| !chkEmptyWithoutFocus(document.getElementById('txtName'),
					'Full Name')
			|| !chkEmptyWithoutFocus(document
					.getElementById('txtNameInDevanagari'),
					'Full Name in Devanagari')
			||
			// !chkEmptyWithoutFocus(document.getElementById('txtFatherOrHusband'),'Father/Husband
			// Name') ||
			!chkEmptyWithoutFocus(document.getElementById('txtBirthDate'),
					'Date of Birth')
			||
			// !chkEmptyWithoutFocus(document.getElementById('txtJoiningDate'),'Date
			// of Joining Government Service(GOM)') ||
			!chkEmptyWithoutFocus(document.getElementById('cmbDesignation'),
					'Designation')
			|| !chkEmptyWithoutFocus(document.getElementById('cmbCurrentPost'),
					'Current Post')
			|| !chkEmptyWithoutFocus(document
					.getElementById('cmbPayCommission'), 'Pay Commission')
			||
			// !chkEmptyWithoutFocus(document.getElementById('cmbPayScale'),'Pay
			// Scale') ||
			!chkEmptyWithoutFocus(document.getElementById('txtBasicPay'),
					'Basic Pay')
			|| !chkEmptyWithoutFocus(document.getElementById('cmbState'),
					'State')) {
		alert("Please fill all the mandatory details in Employee Details tab");
		return false;
	}

	if (!chkEmptyPayscale()) {
		return false;
	}

	if (!chkEmptyGender() ||
	// !changeDcpsOrGpfRadio() ||
	!checkEmptyForDeptSelectedInPF() || !checkGISRegristrationDate()) {
		return false;
	}

	if (!checkGender()) {
		return false;
	}

	if (checkLength(document.getElementById('txtContactTelNo'),
			'telephone number') == false) {
		return false;
	}

	if (checkLength(document.getElementById('txtCellNo'), 'cell number') == false) {
		return false;
	}

	if (!chkEmptyWithoutFocus(document.getElementById('cmbCadre'), 'Cadre')
			|| !chkEmptyWithoutFocus(document.getElementById('txtGroup'),
					'Group')
			||
			/*
			 * !chkEmptyWithoutFocus(document.getElementById('cmbFirstDesignation'),'Name
			 * Of Post/Designation at First Appointment') ||
			 */
			/*
			 * !chkEmptyWithoutFocus(document.getElementById('txtJoinParentDeptDate'),'Date
			 * of initial appointment in parent department') ||
			 */
			!chkEmptyWithoutFocus(document.getElementById('cmbCurrentOffice'),
					'Current Office')
			|| !chkEmptyWithoutFocus(
					document.getElementById('txtJoinPostDate'),
					'Date of Joining current Post(in the current office)')
			|| !chkEmptyWithoutFocus(document.getElementById('txtinduvisalno'),
					'Indivisual Approve Number')
			|| !chkEmptyWithoutFocus(document
					.getElementById('txtinduvisalDate'),
					'Indivisual Approve Date')) {
		alert("Please fill all the mandatory details in Office Details tab");
		return false;
	}
	if (!chkEmptyWithoutFocus(document.getElementById('cmbBankName'),
			'Bank Name')
			|| !chkEmptyWithoutFocus(document.getElementById('cmbBranchName'),
					'Branch Name')
			|| !chkEmptyWithoutFocus(document
					.getElementById('txtbankAccountNo'), 'Bank A/C No.')
			|| !chkEmptyWithoutFocus(document.getElementById('txtIFSCCode'),
					'IFCS Code') || !chkEmptyAcMaintainedBy()) {
		alert("Please fill all the mandatory details in Bank Details tab");
		hideProgressbar();
		return false;
	}

	if (!chkEmptyWithoutFocus(document.getElementById('cmbGisApplicable'),
			'GIS Applicable')) {
		alert("Please fill all the mandatory details in GIS Details tab");
		return false;
	}

	if (document.getElementById('cmbGisApplicable').value != 700212) {
		if (!chkEmptyWithoutFocus(document.getElementById('cmbGisGroup'),
				'GIS Group')
				|| !chkEmptyWithoutFocus(document
						.getElementById('txtMembershipDate'), 'Membership Date')) {
			return false;
		}
	}
	if (!compareDates(document.getElementById('txtBirthDate'), document
			.getElementById('txtJoiningDate'),
			'Date Of Joining should be greater than DOB!', '<')
			||
			// !compareDates(document.getElementById('txtJoiningDate'),document.getElementById('currDate1'),'Date
			// of Joining should be less than current date.','<') ||
			// !compareDates(document.getElementById('joinDate'),document.getElementById('txtJoiningDate'),'Date
			// of Joining should be greater than 01/01/2005.','<') ||
			// !compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinParentDeptDate'),'Date
			// of joining parent dept should be greater than Joining Date','<')
			// ||
			// !compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinPostDate'),'Date
			// of joining current post should be greater than date of
			// joining','<') ||
			/*
			 * !compareDates(document.getElementById('txtJoiningDate'),document.getElementById('txtJoinCadreDate'),'Date
			 * Of Cadre should be greater than date of joining','<') ||
			 */
			// !validateDate(document.getElementById('txtJoiningDate')) ||
			!validateDate(document.getElementById('txtBirthDate'))
			|| !compareDates(document.getElementById('txtBirthDate'), document
					.getElementById('currDate1'),
					'Date of Birth should be less than current date.', '<')
			|| !isName(document.getElementById('txtName'),
					'This field should not contain any special characters or digits.')
			||
			// !emailValidate(document.getElementById('txtEmailId')) ||
			/* !validateDate(document.getElementById('txtJoinCadreDate')) || */
			// !validateDate(document.getElementById('txtJoinParentDeptDate'))
			// ||
			// !compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('currDate1'),'Date
			// of joining parent dept should be less than current date.','<') ||
			// !compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('txtJoinPostDate'),'Date
			// of joining current post should be greater than date of
			// joining','<') ||
			!validateDate(document.getElementById('txtJoinPostDate'))
			|| !compareDates(document.getElementById('txtJoinPostDate'),
					document.getElementById('currDate1'),
					'Date of joining post should be less than current date.',
					'<'))
	// !compareDates(document.getElementById('txtJoinParentDeptDate'),document.getElementById('txtJoinPostDate'),'Date
	// of joining current post should be greater than date of initial
	// appointment in parent department','<'))
	{
		return false;
	}
	return true;
}

function changeSaveOrUpdateBtn() {
	// alert('inside changeSaveOrUpdateBtn') ;
	if (emp_Id == "") {
		document.getElementById("savebtn").style.display = "block";
		document.getElementById("updatebtn").style.display = "none";
	} else {
		document.getElementById("savebtn").style.display = "none";
		document.getElementById("updatebtn").style.display = "block";
	}
}

function SaveDataUsingAjax() {
	// alert("SaveDataUsingAjax");
	// showProgressbar();
	if (!validateRegFormData()) {
		// alert("SaveDataUsingAjax - > in ValidateForm");
		hideProgressbar();

		return false;
	}

	xmlHttp = GetXmlHttpObject();

	var saveOrUpdateFlag = 1;

	if (xmlHttp == null) {
		hideProgressbar();
		return false;
	}
	var uri = 'ifms.htm?actionFlag=saveDCPSForm';
	var url = runForm(0);
	url = uri + url;
	url = url + "&saveOrUpdateFlag=" + saveOrUpdateFlag;

	var genderValue;
	var genderArr = document.DCPSForm.radioGender;
	for (var i = 0; i < genderArr.length; i++) {
		if (genderArr[i].checked == true) {
			genderValue = genderArr[i].value;
		}
	}
	// alert(genderValue);
	var index = document.DCPSForm.cmbSalutation.selectedIndex;
	var salutationTextValue = document.DCPSForm.cmbSalutation[index].text;

	// alert(document.DCPSForm.cmbSalutation[index].text);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				emp_Id = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;

				// alert(emp_Id+"emp_Id @ 423");

				if (test_Id) {
					nomineeSavedOrNot = submitNomineeDtls(emp_Id);

					if (nomineeSavedOrNot == 1) {
						if (!forwardFlag) {
							alert('All the details saved successfully');
						}
					}
					if (nomineeSavedOrNot == 2 && serialNo == 1) {
						if (!forwardFlag) {
							alert('All the details saved successfully');
						}
					}
					if (nomineeSavedOrNot == 2 && serialNo > 1) {
						if (!forwardFlag) {
							alert('All the details except nominee details saved successfully');
						}
					}

					// var reloadUrl =
					// "ifms.htm?actionFlag=loadRegistrationForm&User=ZPDDOAsst&elementId=700233";
					var reloadUrl = "ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst&elementId=700022";
					self.location.href = reloadUrl;
				}
			}
		}
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(url);
	hideProgressbar();
}

function UpdateDataUsingAjax() {

	EmpId = emp_Id;

	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	var saveOrUpdateFlag = 2;

	var uri = 'ifms.htm?actionFlag=updateDCPSForm';
	var url = runForm(0);
	url = uri + url;
	url = url + "&saveOrUpdateFlag=" + saveOrUpdateFlag + "&empId=" + EmpId;

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

				var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				emp_Id = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;

				if (test_Id) {
					nomineeSavedOrNot = submitNomineeDtls(emp_Id);
					if (nomineeSavedOrNot == 1) {
						alert('All the details updated successfully');
					}
					if (nomineeSavedOrNot == 2 && serialNo > 1) {
						alert('All the details except Nominee details updated successfully');
					}
					if (nomineeSavedOrNot == 2 && serialNo == 1) {
						alert('All the details updated successfully');
					}

					self.location.href = "ifms.htm?actionFlag=loadDCPSForm&User=Asst";
				}
			}
		}
	};

	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(url);
}

function updateDataUsingAJAXForUpdateTotally(empid) {
	// alert("updateDataUsingAJAXForUpdateTotally"+empid);
	showProgressbar();
	var EmpId = empid;
	var saveOrUpdateFlag = 2;
	SaveOrUpdateNominee = 2;
	// alert("EmpId "+EmpId);
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}

	var uri = 'ifms.htm?actionFlag=updateDCPSForm&User=ZPDDOAsst';// viewDraftForms->updateDCPSForm
	var url = runForm(0);
	url = uri + url;
	url = url + "&saveOrUpdateFlag=" + saveOrUpdateFlag + "&empId=" + EmpId;

	var User = document.getElementById("User").value;
	var Use = document.getElementById("Use").value;
	// alert("Updated:"+User);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

				var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				emp_Id = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
				// alert("TestID:"+test_Id);
				// alert("emp_Id:"+emp_Id);
				if (test_Id) {
					nomineeSavedOrNot = submitNomineeDtls(EmpId);

					if (nomineeSavedOrNot == 1) {
						if (!forwardFlag) {
							alert('All the details updated successfully');
						}
					}
					if (nomineeSavedOrNot == 2 && serialNo > 1) {
						if (!forwardFlag) {
							alert('All the details except Nominee details updated successfully');
						}
					}
					if (nomineeSavedOrNot == 2 && serialNo == 1) {
						if (!forwardFlag) {
							alert('All the details updated successfully');
						}
					}
					if (!forwardFlag) {
						self.location.href = "ifms.htm?actionFlag=viewDraftForms&elementId=700159&User="
								+ User + "&Use=" + Use;
					}
				}
			}
		}
	};

	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(url);

}

function ForwardRequest(empId, flag) {
	// showProgressbar();
	// alert("Change ForwardRequest Line 583"+empId);
	var ForwardToPost = document.getElementById("ForwardToPost").value;
	var ApproveToPost = document.getElementById("ApproveToPost").value;
	// alert("ZPFormStatus Line
	// 586"+document.getElementById("ZPFormStatus").value);
	var ZPFormStatus = document.getElementById("ZPFormStatus").value;

	ForwardRequestUsingAjax("ifms.htm?actionFlag=dcpsFwdReq&Emp_Id=" + empId
			+ "&ForwardToPost=" + ForwardToPost + "&ApproveToPost="
			+ ApproveToPost + "&flag=" + flag + "&ZPFormStatus=" + ZPFormStatus);
}

function ForwardRequestUsingAjax(url) {
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	uri = url;
	xmlHttp.onreadystatechange = forwardDataStateChanged;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function forwardDataStateChanged() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			if (test_Id) {
				alert("Registration form is forwarded successfully");
			}
			self.location.href = 'ifms.htm?actionFlag=loadDCPSForm&User=ZPDDOAsst';
		}
	}
}

function ForwardRequestForUpdateTotally(empId, flag) {
	showProgressbar();
	// alert("ForwardRequestForUpdateTotally EmP_ID"+empId);
	var ForwardToPost = document.getElementById("ForwardToPost").value;
	var ApproveToPost = document.getElementById("ApproveToPost").value;

	var ZPFormStatus = document.getElementById("ZPFormStatus").value;

	var url = "ifms.htm?actionFlag=dcpsFwdReq&Emp_Id=" + empId
			+ "&ForwardToPost=" + ForwardToPost + "&ApproveToPost="
			+ ApproveToPost + "&flag=" + flag + "&ZPFormStatus=" + ZPFormStatus;

	ForwardRequestUsingAjaxForUpdateTotally(url);
}

function ForwardRequestUsingAjaxForUpdateTotally(url) {
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	uri = url;
	xmlHttp.onreadystatechange = forwardDataStateChangedforUpdateTotally;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function forwardDataStateChangedforUpdateTotally() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			if (test_Id) {
				alert("Registration form is forwarded successfully");
			}

			var User = document.getElementById("User").value.trim();
			var Use = document.getElementById("Use").value.trim();
			var url = "ifms.htm?actionFlag=viewDraftForms&elementId=700159&User="
					+ User + "&Use=" + Use;
			self.location.href = url;
		}
	}
}

function ForwardRequestDDO(empId) {
	// alert('hii'+empId);
	showProgressbar();

	/*
	 * if(document.getElementById("formPrintedOrNot").value.trim() == 'NO'){
	 * alert('Print Form 1 before approving or forwarding the form');
	 * hideProgressbar(); return; }
	 */

	var emp_Id = empId;

	if (emp_Id == null || emp_Id == "") {
		alert("Please select a form to forward");
		hideProgressbar();
	} else {// alert("hi i m here");
		var ForwardToPost = "";
		if (document.getElementById("ForwardToPost")) {
			// alert("hi i m inside");
			ForwardToPost = document.getElementById("ForwardToPost").value;
		}
		// alert("dgdg");
		ForwardRequestUsingAjaxDDO("ifms.htm?actionFlag=FwdReqTreasury&Emp_Id="
				+ emp_Id + "&ForwardToPost=" + ForwardToPost);
	}
}

function ForwardRequestUsingAjaxDDO(url) {
	showProgressbar();
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	uri = url;
	xmlHttp.onreadystatechange = forwardDataStateChangedDDO;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function forwardDataStateChangedDDO() {
	showProgressbar();
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			XMLDoc = xmlHttp.responseXML.documentElement;

			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var suceess_flag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
			var dcpsOrGpf = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;

			if (suceess_flag) {

				if (dcpsOrGpf == 'DCPS') {
					alert("Form is forwarded successfully to Level 2 DDO");
				}
				if (dcpsOrGpf == 'GPF') {
					var sevarthId = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
					var billGroupSuccessFlag = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;

					if (!billGroupSuccessFlag) {
						alert("GPF Employee is approved. Employee is not attached to any billgroup. Employee's generated HTESevarth Id is  "
								+ sevarthId);
					} else {
						alert("GPF Employee is approved and the HTESevarth Id is  "
								+ sevarthId);
					}
				}

			}
			hideProgressbar();
			var User = document.getElementById("User").value.trim();
			var Use = document.getElementById("Use").value.trim();
			var url = "ifms.htm?actionFlag=viewFormsForwardedByAsst&elementId=700009&User="
					+ User + "&Use=" + Use;
			self.location.href = url;
		}
	}
}

function window_new_update(url) {
	var newWindow = null;
	var height = screen.height - 150;
	var width = screen.width;
	var urlstring = url;
	var urlstyle = "height="
			+ height
			+ ",width="
			+ width
			+ ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(urlstring, "NomineeDetails", urlstyle);
}

function hideUnhideParentList() {
	if (document.DCPSForm.cbChangeParentDept.checked == true) {
		// document.getElementById("ParentFieldDept").style.display = "none";
		document.getElementById("listParentDept").disabled = "";
		defaultParentDpt = false;
	}
	if (document.DCPSForm.cbChangeParentDept.checked == false) {
		// document.getElementById("ParentFieldDept").style.display = "";
		var selectedIndex = document.getElementById("hidPFDByDefault").value;
		document.getElementById('listParentDept').value = selectedIndex;
		document.getElementById("listParentDept").disabled = "disabled";

		document.getElementById("reasonForChangeInPFD").value = "";
		document.getElementById("reasonForChangeInPFD").readOnly = true;
	}
}

function enableReasonForPFD() {
	var hidPFDByDefault = document.getElementById("hidPFDByDefault").value;
	var listParentDept = document.getElementById("listParentDept").value;

	if (hidPFDByDefault != listParentDept
			&& document.DCPSForm.cbChangeParentDept.checked == true) {
		document.getElementById("reasonForChangeInPFD").readOnly = false;
	} else {
		document.getElementById("reasonForChangeInPFD").readOnly = true;
	}
}

function populateGroup() {
	var selectOption = document.getElementById('cmbCadre').value;
	var txtBirthDate = document.getElementById('txtBirthDate').value;
	if (selectOption != -1 && txtBirthDate != "") {
		var dobEmp = document.getElementById("txtBirthDate").value;
		var uri = "ifms.htm?actionFlag=popGroup";
		var url = "cmbCadre=" + selectOption + "&dobEmp=" + dobEmp;

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getDataStateChangedForPopGroup(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	} else {
		document.getElementById("txtGroup").value = "";
		document.getElementById("txtSuperAnnuation").value = "";
		document.getElementById("txtSuperAnnDate").value = "";
	}
}

function getDataStateChangedForPopGroup(myAjax) {
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
	if (XMLEntries[0].childNodes[0].firstChild.nodeValue != null) {
		var group = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	}
	if (XMLEntries[0].childNodes[0].firstChild.nodeValue != null) {
		var superAnnAge = XMLEntries[0].childNodes[1].firstChild.nodeValue;
	}
	if (XMLEntries[0].childNodes[0].firstChild.nodeValue != null) {
		var retiringDate = XMLEntries[0].childNodes[2].firstChild.nodeValue;
	}

	document.getElementById('txtGroup').value = group;
	document.getElementById('txtSuperAnnuation').value = superAnnAge;
	document.getElementById("txtSuperAnnDate").value = retiringDate;
}

function getOfficeDetails() {

	var officeId = document.getElementById('cmbCurrentOffice').value;

	var uri = "ifms.htm?actionFlag=popOfficeDet";
	var url = "officeId=" + officeId;

	document.getElementById('txtOfficeAddress').value = "";
	document.getElementById('txtOfficeContactNo1').value = "";
	document.getElementById('txtOfficeMobile').value = "";
	document.getElementById('txtOfficeContactNo2').value = "";
	document.getElementById('txtOfficeEmailId').value = "";
	document.getElementById('txtOfficeCityClass').value = "";

	var myAjax = new Ajax.Request(uri, {
		method : 'post',
		asynchronous : false,
		parameters : url,
		onSuccess : function(myAjax) {
			getDataStateChangedForPopOfficeDtls(myAjax);
		},
		onFailure : function() {
			alert('Something went wrong...');
		}
	});
}

// changed by vaibhav tyagi: start
function getDataStateChangedForPopOfficeDtls(myAjax) {
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');
	if (XMLEntries[0].childNodes[0].firstChild)
		var address1 = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	if (XMLEntries[0].childNodes[1].firstChild)
		var contact1 = XMLEntries[0].childNodes[1].firstChild.nodeValue;
	if (XMLEntries[0].childNodes[2].firstChild)
		var contact2 = XMLEntries[0].childNodes[2].firstChild.nodeValue;
	if (XMLEntries[0].childNodes[3].firstChild)
		var contact3 = XMLEntries[0].childNodes[3].firstChild.nodeValue;
	if (XMLEntries[0].childNodes[4].firstChild)
		var email = XMLEntries[0].childNodes[4].firstChild.nodeValue;
	var address2;
	if (XMLEntries[0].childNodes[5].firstChild != null) {
		address2 = XMLEntries[0].childNodes[5].firstChild.nodeValue;
	} else {
		address2 = "";
	}
	if (XMLEntries[0].childNodes[6].firstChild)
		var officeCityClass = XMLEntries[0].childNodes[6].firstChild.nodeValue;

	if (address1 != undefined) {
		document.getElementById('txtOfficeAddress').value = address1 + " "
				+ address2;
	} else {
		document.getElementById('txtOfficeAddress').value = "";
	}

	if (contact1 != undefined) {
		document.getElementById('txtOfficeContactNo1').value = contact1;
	} else {
		document.getElementById('txtOfficeContactNo1').value = "";
	}

	if (contact2 != undefined) {
		document.getElementById('txtOfficeMobile').value = contact2;
	} else {
		document.getElementById('txtOfficeMobile').value = "";
	}

	if (contact3 != undefined) {
		document.getElementById('txtOfficeContactNo2').value = contact3;
	} else {
		document.getElementById('txtOfficeContactNo2').value = "";
	}

	if (email != undefined) {
		document.getElementById('txtOfficeEmailId').value = email;
	} else {
		document.getElementById('txtOfficeEmailId').value = "";
	}

	if (officeCityClass != undefined) {
		document.getElementById('txtOfficeCityClass').value = officeCityClass;
	} else {
		document.getElementById('txtOfficeCityClass').value = "";
	}

	document.getElementById('txtOfficeAddress').readOnly = true;
	document.getElementById('txtOfficeContactNo1').readOnly = true;
	document.getElementById('txtOfficeMobile').readOnly = true;
	document.getElementById('txtOfficeContactNo2').readOnly = true;
	document.getElementById('txtOfficeEmailId').readOnly = true;
	document.getElementById('txtOfficeCityClass').readOnly = true;
}

// changed by vaibhav tyagi: end

function updateAfterValidation() {
	if (validateRegFormDataForDraft()) {
		UpdateDataUsingAjax();
	}
}

function forwardRequestAfterValidation(flag) {

	// alert("forwardRequestAfterValidation"+flag);
	forwardFlag = true;

	if (serialNo > 1) {
		if (calculatePercentShare()) {
			if (validateRegFormData()) {
				// alert("inside validateRegFormData Single Para");
				SaveDataUsingAjax();

				if (!(nomineeSavedOrNot == 2 && serialNo > 1)) {
					ForwardRequest(emp_Id, flag);
				} else {
					alert('Form is saved but not forwarded due to incorrect Nominee Details');
				}
			}
		}
	} else {
		// alert('Please Enter Nominee Details');
		// Lines added to make nominee details non mandatory
		/*
		 * if(validateRegFormData()) {
		 */
		// alert("Else of Single PARA");
		SaveDataUsingAjax();
		ForwardRequest(emp_Id, flag);
		// }
	}
}

function forwardRequestAfterValidationforUpdateTotally(empId, flag) {
	showProgressbar();
	forwardFlag = true;
	// alert('CalledForward in JS Line NO:938'+empId);
	if (serialNo > 1) {
		if (calculatePercentShare()) {
			// alert("inside calculatePercentShare");
			if (validateRegFormData()) {
				// alert("inside validateRegFormData");
				saveOrFwdFlag = 1;
				updateAfterValidationForUpdateTotally(empId);

				if (!(nomineeSavedOrNot == 2 && serialNo > 1)) {
					ForwardRequestForUpdateTotally(empId, flag);
				} else {
					alert('Form is saved but not forwarded due to incorrect Nominee Details');
				}
			}
		}
	} else {
		// alert('Please Enter Nominee Details');
		// Lines added to make nominee details non mandatory
		/*
		 * if(validateRegFormData()) {
		 */
		if (validateRegFormData()) {
			updateAfterValidationForUpdateTotally(empId);
			ForwardRequestForUpdateTotally(empId, flag);
		} // }
	}
}
function updateAfterValidationForUpdateTotally(empid) {
	// alert("updateAfterValidationForUpdateTotally"+empid);
	showProgressbar();
	if (saveOrFwdFlag == 0) {
		if (validateRegFormDataForDraft()) {
			updateDataUsingAJAXForUpdateTotally(empid);
		}
	}

	if (saveOrFwdFlag == 1) {
		updateDataUsingAJAXForUpdateTotally(empid);
	}
	hideProgressbar();
}
function backForNew() {
	self.location.href = 'ifms.htm?actionFlag=validateLogin';
}

function backForUpdate() {
	self.close();
}

function hidImg() {
	newWindow1.close();
}
function showImg(lStr) {
	document.getElementById("attachmentName1").value = lStr.name;
	var height = 600;
	var width = 600;
	var urlstring = "ifms.htm?viewName=DcpsPhoto";
	var urlstyle = "height="
			+ height
			+ ",width="
			+ width
			+ ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow1 = window
			.open(
					urlstring,
					"DcpsPhoto",
					"'titlebar=no,directories=no,height=355,location=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=600,height=600,top=0,left=0",
					"false");
	// globArray[9] = newWindow1;
}

function popUpIFSCCode() {

	if (document.getElementById("cmbBranchName").value == -1) {
		document.getElementById("txtIFSCCode").value = '';
	}

	var cmbBranchName = document.getElementById("cmbBranchName").value;
	var uri = "ifms.htm?actionFlag=displayIFSCCodeForBranch";
	var url = "cmbBranchName=" + cmbBranchName;

	var myAjax = new Ajax.Request(uri, {
		method : 'post',
		asynchronous : false,
		parameters : url,
		onSuccess : function(myAjax) {
			getDataStateChangedForPopIFSCode(myAjax);
		},
		onFailure : function() {
			alert('Something went wrong...');
		}
	});
}

function getDataStateChangedForPopIFSCode(myAjax) {
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var IFSCCode = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (IFSCCode != 'null') {
		document.getElementById("txtIFSCCode").value = IFSCCode;
	}
}

function checkGender() {
	var index = document.DCPSForm.cmbSalutation.selectedIndex;
	var salutationTextValue = document.DCPSForm.cmbSalutation[index].text;
	var genderArr = document.DCPSForm.radioGender;
	var genderValue;
	for (var i = 0; i < genderArr.length; i++) {
		if (genderArr[i].checked == true) {
			genderValue = genderArr[i].value;
		}
	}

	if (salutationTextValue == 'Shri.') {
		if (genderValue == 'Female') {
			alert('Please Check Salutaion and Gender properly');
			return false;
		}
	}
	if (salutationTextValue == 'Smt.' || salutationTextValue == 'Kum.') {
		if (genderValue == 'Male') {
			alert('Please Check Salutation and Gender properly');
			return false;
		}
	}

	return true;
}
var serialNo = 1;
var rowCount = 0;
var addedOrNotFlag = true;
var tbody;
var SaveOrUpdateNominee = 0; // 1 for SAVE Nominee and for all other
// positive values, it is UPDATE
// Nominee.
var totalNomineeShareAdded = 0;
var motherAdded = false;
var fatherAdded = false;
var spouseAdded = false;

function validateNomineeData() {
	if (!chkEmpty(document.getElementById('txtNomineeName'), 'Nominee Name')
			|| !chkEmpty(document.getElementById('txtNomAddress1'),
					'Nominee Address')
			|| !chkEmpty(document.getElementById('txtBirthDateOfNominee'),
					'Nominee Date of Birth')
			|| !chkEmpty(document.getElementById('cmbRelationship'),
					'Nominee Relationship')
			|| !chkEmpty(document.getElementById('txtPercentShare'),
					'Percent Share')) {
		return false;
	}

	if (document.getElementById("txtPercentShare").value > 100) {
		alert('Percentage Share cannot be more than 100');
		addedOrNotFlag = false;
		return false;
	}

	return true;

}

function deleteRow() {
	// alert("hiiiiiiiiiiiiiiiiiii");
	var nomineeId = document.getElementById("txtNomineeID").value;

	var uri = "ifms.htm?actionFlag=deleteNomineeDetails";
	var url = "nomineeId=" + nomineeId;
	// alert("url = "+url);

	var myAjax = new Ajax.Request(uri, {
		method : 'post',
		asynchronous : false,
		parameters : url,
		onSuccess : function(myAjax) {
			delNomineeDetails(myAjax, nomineeId);
		},
		onFailure : function() {
			alert('not deleted');
			alert('Something went wrong...');
		}
	});
}

function delNomineeDetails(myAjax, nomineeId) {

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('flag');
	// alert(XmlHiddenValues);
	var flag = XmlHiddenValues[0].firstChild.nodeValue;

	if (flag == 'true') {
		alert('Nominee is successfully deleted.');
	} else {

		alert('There is some problem in deletion of nominee.');
	}

}

function addRow() {
	if (document.getElementById("cmbRelationship").value == 'Mother') {
		if (motherAdded) {
			alert('Nominee as Mother is already added.');
			return false;
		}
	}
	if (document.getElementById("cmbRelationship").value == 'Father') {
		if (fatherAdded) {
			alert('Nominee as Father is already added.');
			return false;
		}
	}
	if (document.getElementById("cmbRelationship").value == 'Spouse') {
		if (spouseAdded) {
			alert('Nominee as Spouse is already added.');
			return false;
		}
	}

	if (!validateNomineeData()) {
		return false;
	}

	if (totalNomineeShareAdded
			+ Number(document.getElementById("txtPercentShare").value) > 100) {
		alert('Total Nominee share exceeds 100.Please enter correct nominee share.');
		return false;
	}

	totalNomineeShareAdded = totalNomineeShareAdded
			+ Number(document.getElementById("txtPercentShare").value);

	tbody = document.getElementById('displayTableForNomineeDtls')
			.getElementsByTagName('tbody')[0];
	var row = document.createElement('TR');

	rowCount++;

	var className = serialNo;

	var cell1 = document.createElement('TD');
	cell1.align = "center";

	var cell2 = document.createElement('TD');

	var cell3 = document.createElement('TD');
	var cell4 = document.createElement('TD');
	var cell5 = document.createElement('TD');

	cell1.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtNomineeSerialNoValue\" class='"
			+ className + "' value='" + serialNo + "' readonly=\"readonly\" />";
	cell2.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtNameValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("txtNomineeName").value.toUpperCase()
			+ "' readonly=\"readonly\" />";
	cell3.innerHTML = "<input type='text' style=\"border: none;\" size=\"20\" style=\"text-align: center\" name=\"txtDateOfBirthValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("txtBirthDateOfNominee").value
			+ "' readonly=\"readonly\"  />";
	cell4.innerHTML = "<input type='text' style=\"border: none;\" size=\"5\" style=\"text-align: center\" name=\"txtPercentShareValue\"  id=\"txtPercentShareValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("txtPercentShare").value
			+ "' readonly=\"readonly\"  />";
	cell5.innerHTML = "<input type='text' style=\"border: none;\" size=\"10\" style=\"text-align: center\" name=\"txtRelationshipValue\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("cmbRelationship").value
			+ "'  readonly=\"readonly\" />";
	cell1.align = "center";
	cell1.className = "tds";
	cell2.align = "center";
	cell2.className = "tds";
	cell3.align = "center";
	cell3.className = "tds";
	cell4.align = "center";
	cell4.className = "tds";
	cell5.align = "center";
	cell5.className = "tds";

	var hiddenCell0 = document.createElement('TD');
	hiddenCell0.innerHTML = "<input type='hidden' name=\"txtNomSerialNo\" class='"
			+ className + "' value='" + serialNo + "' />";
	var hiddenCell1 = document.createElement('TD');
	hiddenCell1.innerHTML = "<input type='hidden' name=\"txtNomName\" class='"
			+ className + "' value='"
			+ document.getElementById("txtNomineeName").value.toUpperCase()
			+ "' />";
	var hiddenCell4 = document.createElement('TD');
	hiddenCell4.innerHTML = "<input type='hidden' name=\"txtNomAddr1\" class='"
			+ className + "' value='"
			+ document.getElementById("txtNomAddress1").value.toUpperCase()
			+ "' />";
	var hiddenCell6 = document.createElement('TD');
	hiddenCell6.innerHTML = "<input type='hidden' name=\"txtNomDOB\" class='"
			+ className + "' value='"
			+ document.getElementById("txtBirthDateOfNominee").value + "' />";
	var hiddenCell7 = document.createElement('TD');
	hiddenCell7.innerHTML = "<input type='hidden' name=\"txtNomPerShare\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("txtPercentShare").value + "' />";
	var hiddenCell8 = document.createElement('TD');
	hiddenCell8.innerHTML = "<input type='hidden' name=\"txtNomRelationship\" class='"
			+ className
			+ "' value='"
			+ document.getElementById("cmbRelationship").value + "' />";

	var cell7 = document.createElement('TD');
	cell7.innerHTML = "<img src=\"images/CalendarImages/DeleteIcon.gif\"  onclick=\"deleteRow()\" />";
	cell7.align = "center";
	cell7.className = "tds";

	var cell8 = document.createElement('TD');
	cell8.innerHTML = "<input type=\"hidden\" value='" + rowCount + "'/>";

	if (document.getElementById("cmbRelationship").value == 'Mother') {
		motherAdded = true;
	}
	if (document.getElementById("cmbRelationship").value == 'Father') {
		fatherAdded = true;
	}
	if (document.getElementById("cmbRelationship").value == 'Spouse') {
		spouseAdded = true;
	}

	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell7);
	row.appendChild(cell8);

	row.appendChild(hiddenCell0);
	row.appendChild(hiddenCell1);
	row.appendChild(hiddenCell4);
	row.appendChild(hiddenCell6);
	row.appendChild(hiddenCell7);
	row.appendChild(hiddenCell8);

	tbody.appendChild(row);
	serialNo++;

	addedOrNotFlag = true;

	resetFields();
	return true;

}
function resetFields() {
	if (addedOrNotFlag) {
		document.getElementById("txtNomineeName").value = "";
		document.getElementById("txtBirthDateOfNominee").value = "";
		document.getElementById("txtPercentShare").value = "";
		document.getElementById("cmbRelationship").value = -1;
		document.getElementById("txtNomAddress1").value = "";
	}
}

function calculatePercentShare() {
	var lTotalPercentShare = 0;

	var lListPercentShare = document.getElementsByName("txtNomPerShare");
	var lListPercentShareLength = lListPercentShare.length;

	for (i = 0; i < lListPercentShareLength; i++) {
		lTotalPercentShare = Number(lTotalPercentShare)
				+ Number(lListPercentShare[i].value);
	}

	if (lTotalPercentShare != 100) {
		if (serialNo > 1) {
			alert("Percent Share total should be equal to 100.So Nominee Details will not be saved.");
		}
		return false;
	} else {
		return true;
	}
}
function submitNomineeDtls(empId) {

	var emp_Id = empId;

	if (calculatePercentShare()) {
		saveNomineeDtls(emp_Id);
		return 1; // nominee details saved
	} else {
		return 2; // nominee details not saved
	}
}

function saveNomineeDtls(emp_Id) {
	var empId = emp_Id;
	var lNomineeName = "";
	var lAddress1 = "";
	var lAddress2 = "";
	var lDob = "";
	var lPercentShare = "";
	var lRelationship = "";
	var lEmployeeId = document.getElementById("empID").value;

	// Name
	var lListNomineeNames = document.getElementsByName("txtNomName");
	var lListNomineeNamesLength = lListNomineeNames.length;
	for (i = 0; i < lListNomineeNamesLength; i++) {
		lNomineeName = lNomineeName + lListNomineeNames[i].value + "~";
	}

	// Address 1
	var lListAddress1 = document.getElementsByName("txtNomAddr1");
	var lListAddress1Length = lListAddress1.length;
	for (k = 0; k < lListAddress1Length; k++) {
		lAddress1 = lAddress1 + lListAddress1[k].value + "~";
	}

	// DOB
	var lListDOB = document.getElementsByName("txtNomDOB");
	var lListDOBLength = lListDOB.length;
	for (k = 0; k < lListDOBLength; k++) {
		lDob = lDob + lListDOB[k].value + "~";
	}
	// Percent Share
	var lListPercentShare = document.getElementsByName("txtNomPerShare");
	var lListPercentShareLength = lListPercentShare.length;
	for (j = 0; j < lListPercentShareLength; j++) {
		lPercentShare = lPercentShare + lListPercentShare[j].value + "~";
	}

	// Relationship
	var lListRelationship = document.getElementsByName("txtNomRelationship");
	var lListRelationshipLength = lListRelationship.length;
	for (l = 0; l < lListRelationshipLength; l++) {
		lRelationship = lRelationship + lListRelationship[l].value + "~";
	}

	SaveOrUpdateNominee = SaveOrUpdateNominee + 1;

	var uri = "ifms.htm?actionFlag=savNomData&lNomineeName=" + lNomineeName
			+ "&lAddress1=" + lAddress1 + "&lDob=" + lDob + "&lPercentShare="
			+ lPercentShare + "&lRelationship=" + lRelationship + "&empId="
			+ empId + "&SaveOrUpdateNominee=" + SaveOrUpdateNominee;

	SaveDataUsingAjaxForNominee(uri);

}

function SaveDataUsingAjaxForNominee(uri) {
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}

	xmlHttp.onreadystatechange = saveDataStateChangedForNominee;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}
function saveDataStateChangedForNominee() {

	if (xmlHttp.readyState == 4) {

		if (xmlHttp.status == 200) {
			XMLDoc = xmlHttp.responseXML.documentElement;

			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

			if (test_Id) {
				// self.location.href =
				// 'ifms.htm?actionFlag=loadDcpsForm&empId='+empId;
				return 1;
			} else {
				alert('Nominee details are not saved.');
				return 2;
			}
		}
	}
	return 1;
}

function populateDesig() {
	if (document.getElementById("cmbCadre").value != -1) {
		var cmbCadre = document.getElementById("cmbCadre").value;
		var listParentDept = document.getElementById("listParentDept").value;
		xmlHttp = GetXmlHttpObject();

		var uri = "ifms.htm?actionFlag=getDesigsForPFDAndCadre";
		var url = "cmbCadre=" + cmbCadre + "&listParentDept=" + listParentDept;

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getDataStateChangedForPopDesig(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}
}

function getDataStateChangedForPopDesig(myAjax) {
	XMLDoc = xmlHttp.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
	document.getElementById('cmbDesignation').options.length = 0;

	for (var j = 0; j < XmlHiddenValues.length; j++) {
		var branchName = XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var branchCode = XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var optn = document.createElement("OPTION");
		optn.value = branchCode;
		optn.text = branchName;
		document.getElementById("cmbDesignation").options.add(optn);
	}
}

function validateBasicPay() {

	var index = document.forms[0].cmbPayCommission.selectedIndex;
	var payCommission = document.forms[0].cmbPayCommission[index].text;
	index = document.forms[0].cmbPayScale.selectedIndex;
	var payScale = document.forms[0].cmbPayScale[index].text;
	var basicPay = document.getElementById("txtBasicPay").value;
	var payArray;
	var count = 0;

	if ((basicPay != null && basicPay != "")
			&& (document.getElementById("cmbPayScale").value != null && document
					.getElementById("cmbPayScale").value != -1)) {
		if (payCommission == '6th Pay Commission') {
			var tempArray = payScale.split("(");
			payArray = tempArray[0].split("-");
			var payIn = basicPay - (tempArray[1].split(")"))[0];
			for (k = 0; k < payArray.length; k++) {
				temp = payArray[k];
				payArray[k] = temp.trim();

			}
			if (parseInt(payIn) < parseInt(payArray[0])
					|| parseInt(payIn) > parseInt(payArray[1])) {
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				document.getElementById("txtBasicPay").focus();
				return;
			}

		} else if (payCommission == '5th Pay Commission') {
			payArray = payScale.split("-");

			var temp;
			for (k = 0; k < payArray.length; k++) {
				temp = payArray[k];
				payArray[k] = temp.trim();
			}
			for (var j = 0; j < payArray.length; j++) {
				if (payArray[j] == 'EB') {
					payArray.splice(j, 1);
				}
			}
			if (basicPay == payArray[payArray.length - 1]) {
				return;
			}
			for (var i = 0; i < payArray.length; i += 2) {

				if (i != 0) {
					if (parseInt(basicPay) > parseInt(payArray[i])) {
						count = i;
						continue;
					}

					else {
						var start = payArray[i - 2];
						var variance = payArray[i - 1];

						if ((basicPay - start) % variance != 0) {
							alert("The Basic Pay is not in accordance with the Pay Scale selected");
							document.getElementById("txtBasicPay").value = '';
						}
						return;
					}
				} else {
					if (parseInt(basicPay) < parseInt(payArray[i])) {

						alert("The Basic Pay is not in accordance with the Pay Scale selected");
						document.getElementById("txtBasicPay").value = '';
						return;
					}
				}
				count = i;
			}
			if (count != 0 && parseInt(basicPay) > parseInt(payArray[count])) {
				alert("The Basic Pay is not in accordance with the Pay Scale selected");
				document.getElementById("txtBasicPay").value = '';
				return;
			}
		}
	}
}

function GetPostfromDesg(userType) {
	var uri = 'ifms.htm?actionFlag=GetPostfromDesignation';
	var url = 'User=' + userType + '&dsgnId='
			+ document.forms[0].cmbDesignation.value;
	var myAjax = new Ajax.Request(uri, {
		method : 'post',
		asynchronous : false,
		parameters : url,
		onSuccess : function(myAjax) {
			getDataStateChangedForGettingPostFromDesig(myAjax);
		},
		onFailure : function() {
			alert('Something went wrong...');
		}
	});
	// alert('sent');
}

function getDataStateChangedForGettingPostFromDesig(myAjax) {
	var XMLDoc = myAjax.responseXML.documentElement;
	var namesEntries = XMLDoc.getElementsByTagName('post-mapping');
	document.getElementById("cmbCurrentPost").options.length = 0;

	var optn = document.createElement('option');
	optn.text = "-- Select --";
	optn.value = "-1";
	document.getElementById("cmbCurrentPost").options.add(optn);

	var billNoListObject = document.getElementById('cmbCurrentPost');

	for (var k = 0; k < namesEntries.length; k++) {
		var y1 = document.createElement('option');
		val = namesEntries[k].childNodes[0].firstChild.nodeValue;
		text = namesEntries[k].childNodes[1].firstChild.nodeValue;
		y1.value = val;
		y1.text = text;
		try {
			billNoListObject.add(y1, null);
		} catch (ex) {
			billNoListObject.add(y1);
		}
	}

	document.getElementById("cmbCurrentOffice").options.length = 0;
	var optnForOffice = document.createElement('option');
	optnForOffice.text = "-- Select --";
	optnForOffice.value = "-1";
	document.getElementById("cmbCurrentOffice").options.add(optnForOffice);
	document.getElementById('txtOfficeAddress').value = "";
	document.getElementById('txtOfficeContactNo1').value = "";
	document.getElementById('txtOfficeMobile').value = "";
	document.getElementById('txtOfficeContactNo2').value = "";
	document.getElementById('txtOfficeEmailId').value = "";
	document.getElementById('txtOfficeCityClass').value = "";
}

function GetScalePostfromDesg() {

	// alert('function called')
	var commissionId = document.forms[0].elements['cmbPayCommission'].value
			.trim();
	if (commissionId == '700337') {
		document.forms[0].cmbPayScale.value = -1;
		document.forms[0].cmbPayScale.disabled = true;
		document.getElementById("lableMandatoryForPayscale").innerHTML = "";
	}

	else {
		document.getElementById("lableMandatoryForPayscale").innerHTML = "*";
		document.forms[0].cmbPayScale.disabled = false;

		var v = document.getElementById("cmbPayScale").length;
		for (i = 1; i < v; i++) {
			lgth = document.getElementById("cmbPayScale").options.length - 1;
			document.getElementById("cmbPayScale").options[lgth] = null;
		}

		var url = '&commissionId='
				+ document.forms[0].elements['cmbPayCommission'].value
				+ '&cadre=' + document.forms[0].elements['cmbCadre'].value
				+ '&ifAjax=TRUE';
		var uri = 'ifms.htm?actionFlag=GetScalefromDesignation';

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getDataStateChangedForGettingScalesFromPC(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}
}

function getDataStateChangedForGettingScalesFromPC(myAjax) {
	var XMLDoc = myAjax.responseXML.documentElement;
	var entries = XMLDoc.getElementsByTagName('scale-mapping');
	var scale = document.getElementById("cmbPayScale");
	var val = 0;
	var text = '';
	var y;
	for (var i = 0; i < entries.length; i++) {
		val = entries[i].childNodes[0].firstChild.nodeValue;
		text = entries[i].childNodes[1].firstChild.nodeValue;

		y = document.createElement('option');
		y.value = val;
		y.text = text;
		try {
			scale.add(y, null);
		} catch (ex) {
			scale.add(y);
		}

	}
}
function getPFDesc() {
	var index = document.DCPSForm.cmbPFSeries.selectedIndex;
	if (document.DCPSForm.cmbPFSeries[index].value.trim() != -1) {
		document.getElementById("txtPFSeriesDesc").value = document.DCPSForm.cmbPFSeries[index].text;
	} else {
		document.getElementById("txtPFSeriesDesc").value = "";
	}
}

function changeDcpsOrGpfRadio() {
	// alert("hello");
	var joiningDate = document.getElementById("txtJoiningDate").value;
	var joinDateLimit = document.getElementById("joinDate").value;
	var lArrRadioDCPSOrGPF;
	if (compareDatesWithoutAlert(joinDateLimit, joiningDate, '>')) {
		// GPF Employee
		/*
		 * if(document.getElementById("radioDCPS").checked) { alert('Joining
		 * date is less than 01/11/2005.So please select this employee as a GPF
		 * employee in Bank details tab.'); return false; }
		 */
		lArrRadioDCPSOrGPF = document.getElementsByName("radioDCPS");

		for (var i = 0; i < lArrRadioDCPSOrGPF.length; i++) {
			if (lArrRadioDCPSOrGPF[i].value.trim() == 'N') {
				lArrRadioDCPSOrGPF[i].checked = true;
				displayPFEntryDetails();
				displayAsterisks();
			}
		}
	} else {
		// DCPS Employee
		/*
		 * if(document.getElementById("radioGPF").checked) { alert('Joining date
		 * is greater than 01/11/2005.So please select this employee as a DCPS
		 * employee in Bank details tab.'); return false; }
		 */

		lArrRadioDCPSOrGPF = document.getElementsByName("radioDCPS");

		for (var i = 0; i < lArrRadioDCPSOrGPF.length; i++) {
			if (lArrRadioDCPSOrGPF[i].value.trim() == 'Y') {
				lArrRadioDCPSOrGPF[i].checked = true;
				displayPFEntryDetails();
				hideAsterisks();
			}
		}
	}

	return true;
}

function chkEmptyAcMaintainedBy() {
	if (document.getElementById("radioDCPS").checked) {
		if (document.getElementById("dcpsAcntMntndBy").value == -1) {
			alert('Please Select DCPS Account Maintaining Authority');
			return false;
		}
		var dcpsAcntMntndBy = document.getElementById("dcpsAcntMntndBy").value
				.trim();

		if (dcpsAcntMntndBy == 700180) {
			if (document.getElementById("txtAcNoForNonSRKAEmp").value.trim() == "") {
				alert('Please enter Account No for Non SRKA employee');
				return false;
			}

			if (document.getElementById("txtOthersNonSRKAEmp").value.trim() == "") {
				alert('Please enter Account maintaining Authority');
				return false;
			}
		}
	}
	if (document.getElementById("radioGPF").checked) {
		var cmbAcMaintainedBy = document.getElementById('cmbAcMaintainedBy').value
				.trim();
		if (document.getElementById('cmbAcMaintainedBy').value == -1) {
			alert('Please Select GPF Account Maintaining Authority');
			return false;
		}
		if (document.getElementById('txtPFSeriesDesc').value == "") {
			alert('Please Select GPF Series or Description');
			return false;
		}
		if (cmbAcMaintainedBy == 700092 || cmbAcMaintainedBy == 700093
				|| cmbAcMaintainedBy == 700094) {
			if (document.getElementById('txtPfAccountNo').value == "") {
				alert('Please Enter GPF Account No');
				return false;
			}
		}
	}
	return true;
}

function displayPFEntryDetails() {
	var radioDCPSArr = document.DCPSForm.radioDCPS;
	var radioDCPS;
	//
	for (i = 0; i < radioDCPSArr.length; i++) {
		if (radioDCPSArr[i].checked == true) {
			radioDCPS = radioDCPSArr[i].value;
		}
	}
	if (radioDCPS == "Y") {
		document.getElementById('cmbAcMaintainedBy').value = -1;
		document.getElementById('cmbPFSeries').value = -1;
		document.getElementById('txtPFSeriesDesc').value = "";
		document.getElementById('txtPfAccountNo').value = "";
		document.getElementById('cmbAcMaintainedBy').disabled = true;
		document.getElementById('cmbPFSeries').disabled = true;
		document.getElementById('txtPFSeries').readOnly = true;
		document.getElementById('txtPFSeriesDesc').readOnly = true;
		document.getElementById('txtPfAccountNo').readOnly = true;
		document.getElementById('dcpsAcntMntndBy').disabled = false;
	} else {
		document.getElementById('dcpsAcntMntndBy').value = -1;
		document.getElementById('dcpsAcntMntndBy').disabled = true;

		if (document.getElementById("tdForAcno") != null) {
			document.getElementById("tdForAcno").style.display = 'none';
		}
		if (document.getElementById("tdForAcNoTxtBox") != null) {
			document.getElementById("tdForAcNoTxtBox").style.display = 'none';
		}

		/*
		 * if(document.getElementById("tdForAcMntnOthers") != null) {
		 * document.getElementById("tdForAcMntnOthers").style.display = 'none'; }
		 * if(document.getElementById("tdForAcMntnOthersTxtBox") != null) {
		 * document.getElementById("tdForAcMntnOthersTxtBox").style.display =
		 * 'none'; }
		 */

		if (document.getElementById('txtAcNoForNonSRKAEmp') != null) {
			document.getElementById('txtAcNoForNonSRKAEmp').value = "";
			document.getElementById('txtAcNoForNonSRKAEmp').readOnly = true;
			document.getElementById('txtAcNoForNonSRKAEmp').disabled = true;
		}
		/*
		 * if(document.getElementById('txtAcNoMntndByOthers') != null) {
		 * document.getElementById('txtAcNoMntndByOthers').value = "";
		 * document.getElementById('txtAcNoMntndByOthers').readOnly = true;
		 * document.getElementById('txtAcNoMntndByOthers').disabled = true; }
		 */

		document.getElementById('cmbAcMaintainedBy').disabled = false;
		document.getElementById('cmbPFSeries').disabled = false;
		document.getElementById('txtPFSeriesDesc').readOnly = false;
		document.getElementById('txtPfAccountNo').readOnly = false;
		document.getElementById('txtPfAccountNo').disabled = false;
	}
}
function IDValidation() {
	if (document.getElementById('txtEIDNo').value != ""
			&& document.getElementById('txtUIDNo').value != "") {
		alert("Please enter only one of UID or EID");
	}
}

function checkEmptyForDeptSelectedInPF() {
	var AccMaintainedBy = document.getElementById('cmbAcMaintainedBy');

	var index = AccMaintainedBy.selectedIndex;
	var selected_text = AccMaintainedBy.options[index].text;

	if (selected_text == 'Department') {
		if (document.getElementById("txtPFSeriesDesc").value == "") {
			alert('Please enter PF Series description for department');
			return false;
		} else {
			return true;
		}
	}
	return true;
}

function checkGroupDtls() {

	var Group = document.getElementById('txtGroup').value;
	var AccMaintainedBy = document.getElementById('cmbAcMaintainedBy');
	var index = AccMaintainedBy.selectedIndex;
	var selected_text = AccMaintainedBy.options[index].text.trim();
	var selected_value = AccMaintainedBy.options[index].value.trim();

	// AG Mumbai - 700092
	// AG Nagpur - 700093
	// Department - 700094
	// Not applicable - 700095
	// Others - 700096

	if (AccMaintainedBy.value != -1 && Group != '') {
		if (Group == 'D' && selected_text != 'Department') {
			alert('Group D accounts maintained by Department');
		} else if ((Group == 'A' || Group == 'B' || Group == 'C')
				&& selected_value != 700092 && selected_value != 700093
				&& selected_value != 700096) {
			// alert('Group A,B,C accounts maintained by A.G.');
		}
	}
	if (selected_value == 700094) {
		document.getElementById("cmbPFSeries").value = -1;
		document.getElementById("txtPFSeriesDesc").value = "";
		document.getElementById("txtPFSeries").value = "";
		document.getElementById("cmbPFSeries").disabled = true;
		document.getElementById("txtPFSeriesDesc").readOnly = false;
		document.getElementById("txtPFSeriesDesc").disabled = false;
		document.getElementById("txtPfAccountNo").readOnly = false;
		if (document.getElementById("labelForGPFSeriesDesc") != null) {
			document.getElementById("labelForGPFSeriesDesc").style.display = '';
		}
		if (document.getElementById("labelForGPFAcNo") != null) {
			document.getElementById("labelForGPFAcNo").style.display = 'inline';
		}
	} else if (selected_value == 700095) {
		document.getElementById('cmbPFSeries').style.display = 'none';
		document.getElementById('txtPFSeries').style.display = '';
		document.getElementById("txtPFSeries").value = 'NA';
		document.getElementById("txtPFSeries").readOnly = true;
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = 'NA';
		// document.getElementById("txtPfAccountNo").readOnly = true;
		document.getElementById("txtPfAccountNo").readOnly = false;
		document.getElementById("txtPfAccountNo").value = "";
		if (document.getElementById("labelForGPFAcNo") != null) {
			document.getElementById("labelForGPFAcNo").style.display = 'none';
		}

		if (document.getElementById("labelForGPFSeriesDesc") != null) {
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none';
		}
	} else if (selected_value == 700096) {
		document.getElementById('cmbPFSeries').style.display = 'none';
		document.getElementById('txtPFSeries').style.display = '';
		document.getElementById("txtPFSeries").value = 'Others';
		document.getElementById("txtPFSeries").readOnly = false;
		document.getElementById("txtPFSeriesDesc").readOnly = false;
		document.getElementById("txtPFSeries").disabled = false;
		document.getElementById("txtPFSeriesDesc").disabled = false;
		document.getElementById("txtPFSeriesDesc").value = 'Others';
		// document.getElementById("txtPfAccountNo").readOnly = true;
		document.getElementById("txtPfAccountNo").readOnly = false;
		if (document.getElementById("labelForGPFAcNo") != null) {
			document.getElementById("labelForGPFAcNo").style.display = 'none';
		}

		if (document.getElementById("labelForGPFSeriesDesc") != null) {
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none';
		}
	} else if (selected_value == 700092 || selected_value == 700093) {
		document.getElementById("cmbPFSeries").disabled = false;
		document.getElementById('cmbPFSeries').style.display = '';
		document.getElementById('txtPFSeries').style.display = 'none';
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = '';
		document.getElementById("txtPfAccountNo").readOnly = false;
		if (document.getElementById("labelForGPFSeriesDesc") != null) {
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none';
		}
		if (document.getElementById("labelForGPFAcNo") != null) {
			document.getElementById("labelForGPFAcNo").style.display = 'inline';
		}

		var uri = "ifms.htm?actionFlag=getLookupValuesForParentAG";
		var url = "typeOfAG=" + selected_value;

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				getDataStateChangedForCheckGroup(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	} else {

		var optnTemp = document.createElement("OPTION");
		optnTemp.value = -1;
		optnTemp.text = "-- Select --";
		document.getElementById("cmbPFSeries").options.length = 0;
		document.getElementById("cmbPFSeries").options.add(optnTemp);
		document.getElementById("cmbPFSeries").value = -1;

		document.getElementById("cmbPFSeries").disabled = false;
		document.getElementById('cmbPFSeries').style.display = '';
		document.getElementById('txtPFSeries').style.display = 'none';
		document.getElementById("txtPFSeriesDesc").readOnly = true;
		document.getElementById("txtPFSeriesDesc").value = '';
		document.getElementById("txtPfAccountNo").readOnly = false;
		if (document.getElementById("labelForGPFSeriesDesc") != null) {
			document.getElementById("labelForGPFSeriesDesc").style.display = 'none';
		}
	}
}

function getDataStateChangedForCheckGroup(myAjax) {
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
	document.getElementById('cmbPFSeries').options.length = 0;

	for (var j = 0; j < XmlHiddenValues.length; j++) {
		var branchName = XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
		var branchCode = XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
		var optn = document.createElement("OPTION");
		optn.value = branchCode;
		optn.text = branchName;
		document.getElementById("cmbPFSeries").options.add(optn);
	}
}

function displayAsterisks() {
	document.getElementById("labelForDcpsAcMntndBy").style.display = 'none';
	document.getElementById("labelForGPFAcmntndBy").style.display = '';
	document.getElementById("labelForGPFSeries").style.display = '';
	document.getElementById("labelForGPFAcNo").style.display = '';
	document.getElementById("nomineeDtls").style.display = 'none'
}

function hideAsterisks() {
	document.getElementById("labelForDcpsAcMntndBy").style.display = '';
	document.getElementById("labelForGPFAcmntndBy").style.display = 'none';
	document.getElementById("labelForGPFSeries").style.display = 'none';
	document.getElementById("labelForGPFAcNo").style.display = 'none';
	document.getElementById("labelForGPFSeriesDesc").style.display = 'none';
	document.getElementById("nomineeDtls").style.display = ''
}

function panNoValidation() {
	var panValue = document.getElementById('txtPANNo').value;
	var regex1 = /^[A-Za-z]{5}\d{4}[A-Za-z]{1}$/;

	if (regex1.test(panValue) == false && panValue.length != 0) {
		alert('Please enter valid PAN No');
		document.getElementById('txtPANNo').focus();
		return false;
	}
	return true;
}

function autoTab(input, len, e) {
	// alert("hiiiiiiiiiiii");
	UIDValidation();
	var keyCode = e.keyCode;
	var filter = [ 0, 8, 9, 16, 17, 18, 37, 38, 39, 40, 46 ];
	if (input.value.length >= len && !containsElement(filter, keyCode)) {
		input.value = input.value.slice(0, len);
		input.form[(getIndex(input) + 1) % input.form.length].focus();
	}

	function containsElement(arr, ele) {
		var found = false, index = 0;
		while (!found && index < arr.length)
			if (arr[index] == ele)
				found = true;
			else
				index++;
		return found;
	}

	function getIndex(input) {
		var index = -1, i = 0, found = false;
		while (i < input.form.length && index == -1)
			if (input.form[i] == input)
				index = i;
			else
				i++;
		return index;
	}
	return true;
}

function UIDValidation() {
	if (document.getElementById('txtUIDNo1').value != ""
			|| document.getElementById('txtUIDNo2').value != ""
			|| document.getElementById('txtUIDNo3').value != "") {
		document.getElementById('txtEIDNo').readOnly = "readOnly";
	} else {
		document.getElementById('txtEIDNo').readOnly = "";
	}
}

function EIDValidation() {
	if (document.getElementById('txtEIDNo').value != "") {
		document.getElementById('txtUIDNo1').value = "";
		document.getElementById('txtUIDNo2').value = "";
		document.getElementById('txtUIDNo3').value = "";
		document.getElementById('txtUIDNo1').readOnly = "readOnly";
		document.getElementById('txtUIDNo2').readOnly = "readOnly";
		document.getElementById('txtUIDNo3').readOnly = "readOnly";
	} else {
		document.getElementById('txtUIDNo1').readOnly = "";
		document.getElementById('txtUIDNo2').readOnly = "";
		document.getElementById('txtUIDNo3').readOnly = "";
	}
}

function checkGISGroup() {
	var index = document.DCPSForm.cmbGisGroup.selectedIndex;
	var GISGroup = document.DCPSForm.cmbGisGroup[index].text;
	var CadreGroup = document.getElementById("txtGroup").value;

	var GisSelected = document.getElementById("cmbGisGroup").value;

	if (CadreGroup != GISGroup && GisSelected != -1) {
		alert("Are you sure the GIS Group is '" + GISGroup + "'");
	}

}

function changeGISDetails() {
	var GisApplicable = document.getElementById("cmbGisApplicable").value;
	if (GisApplicable == '700212') {
		document.getElementById('txtMembershipDate').value = "";
		document.getElementById("cmbGisGroup").value = -1;
		document.getElementById('txtMembershipDate').readOnly = "readOnly";
		document.getElementById("cmbGisGroup").disabled = true;
	} else {
		document.getElementById('txtMembershipDate').readOnly = "";
		document.getElementById("cmbGisGroup").disabled = false;
	}
}

function checkGISRegristrationDate() {
	var RegDate = document.getElementById('txtMembershipDate').value;

	if (RegDate != "") {
		var RegArray = new Array();
		RegArray = RegDate.split("/");

		if (RegArray[0] != '01' || RegArray[1] != '01') {
			alert("Membership date must be 1st of January");
			return false;
		}

		if (!compareDates(document.getElementById('txtJoiningDate'), document
				.getElementById('txtMembershipDate'),
				'Membership Date should be greater than date of Joining', '<')) {

			return false;
		}
	}
	return true;

}

function ReturnToDrafts() {
	var User = document.getElementById("User").value.trim();
	var Use = document.getElementById("Use").value.trim();
	self.location.href = "ifms.htm?actionFlag=viewDraftForms&elementId=700159&User="
			+ User + "&Use=" + Use;
}
function ReturnToForwardedForms() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsst&elementId=700009";
}

function ChangeNewFormSearchCriteria() {
	var searchCriteria = document.getElementById("cmbSearchBy").value;
	if (searchCriteria == 'Designation') {
		document.getElementById("cmbCaseStatus").style.display = 'none';
		document.getElementById("cmbDesignation").style.display = '';
	}
	if (searchCriteria == 'Case Status') {
		document.getElementById("cmbDesignation").style.display = 'none';
		document.getElementById("cmbCaseStatus").style.display = '';
	}
}

function searchEmployeeForm() {
	var searchCriteria = document.getElementById("cmbSearchBy").value;
	var searchValue;
	if (searchCriteria == -1) {
		alert('Please select a search criterion.');
	}
	if (searchCriteria == 'Designation') {
		searchValue = document.getElementById("cmbDesignation").value;
		if (searchValue == -1) {
			alert('Please Select Designation');
		} else {
			self.location.href = "ifms.htm?actionFlag=viewDraftForms&elementId=700159&searchCriteria="
					+ searchCriteria + "&searchValue=" + searchValue;
		}
	}
	if (searchCriteria == 'Case Status') {
		searchValue = document.getElementById("cmbCaseStatus").value;
		if (searchValue == -1) {
			alert('Please Select Case Status');
		} else {
			self.location.href = "ifms.htm?actionFlag=viewDraftForms&elementId=700159&searchCriteria="
					+ searchCriteria + "&searchValue=" + searchValue;
		}
	}

}
function displayAllForms() {
	var user = document.getElementById("User").value;// .trim();
	alter("Invalid user" + user);

	if (user != '') {
		self.location.href = "ifms.htm?actionFlag=viewDraftForms&User=" + user
				+ "&elementId=700159";
	} else {
		alter("Invalid user");
	}
}

function checkAcMntndBy() {
	var acMaintainedBy = document.getElementById("dcpsAcntMntndBy").value
			.trim();
	alert(acMaintainedBy);

	if (acMaintainedBy == '700180') {
		// alert('HIIIIIIIIIII');
		document.getElementById("tdForAcno").style.display = 'inline';
		document.getElementById("txtAcNoForNonSRKAEmp").style.display = 'inline';
		document.getElementById("tdForAcNoTxtBox").style.display = 'inline';
	} else {
		// alert('BYYYYYYYYYY');
		document.getElementById("tdForAcno").style.display = 'none';
		document.getElementById("txtAcNoForNonSRKAEmp").style.display = 'none';
		document.getElementById("tdForAcNoTxtBox").style.display = 'none';
	}
}

function checGIS() {
	var gisDescription = document.getElementById("cmbGisApplicable").value
			.trim();

	if (gisDescription == '700342') {
		alert('HIIIIIIIIIII');
		document.getElementById("descriptionLable").style.display = 'inline';
		document.getElementById("description").style.display = 'inline';
		// document.getElementById("descriptionLable").style.display = 'inline'
		// ;
	} else {
		alert('BYYYYYYYYYY');
		document.getElementById("descriptionLable").style.display = 'none';
		document.getElementById("description").style.display = 'none';
		// document.getElementById("descriptionLable").style.display = 'none' ;
	}
}

function closeCurrentWindow() {
	window.close();
}

function ReturnToSearch() {
	var searchFromDDO = document.getElementById("hidSearchFromDDO").value
			.trim();
	if (searchFromDDO == 'Yes') {
		self.location.href = "ifms.htm?viewName=DCPSEmpSearch&elementId=700162";
	}
}

function eidFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode > 47 && window.event.keyCode < 58)
			|| (window.event.keyCode == 32) || (window.event.keyCode == 58)
			|| (window.event.keyCode == 45) || (window.event.keyCode == 47)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function bankACFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode > 47 && window.event.keyCode < 58)
			|| (window.event.keyCode == 32) || (window.event.keyCode == 45)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function nameFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode == 32) || (window.event.keyCode == 46)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function addressFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode > 47 && window.event.keyCode < 58)
			|| (window.event.keyCode == 32) || (window.event.keyCode == 58)
			|| (window.event.keyCode == 45) || (window.event.keyCode == 44)
			|| (window.event.keyCode == 46) || (window.event.keyCode == 35)
			|| (window.event.keyCode == 47)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function districtFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode > 47 && window.event.keyCode < 58)
			|| (window.event.keyCode == 32) || (window.event.keyCode == 38)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function emailFormat(formfield) {
	var val;
	if ((window.event.keyCode > 64 && window.event.keyCode < 91)
			|| (window.event.keyCode > 96 && window.event.keyCode < 123)
			|| (window.event.keyCode > 47 && window.event.keyCode < 58)
			|| (window.event.keyCode == 45) || (window.event.keyCode == 95)
			|| (window.event.keyCode == 46) || (window.event.keyCode == 64)) {
		val = formfield.value;
		if (val[1] != null) {
			if (val[1].length > 1) {
				window.event.keyCode = 0;
			}
		}
	} else {
		window.event.keyCode = 0;
	}
}

function viewUpdateDraft() {
	var finalSelectedEmployee = 0;
	var totalEmployees = document.getElementById("hdnCounter").value;
	var totalSelectedDrafts = 0;

	for (var k = 1; k <= totalEmployees; k++) {
		if (document.getElementById("checkbox" + k).checked) {
			finalSelectedEmployee = k;
			totalSelectedDrafts++;
		}
	}

	if (finalSelectedEmployee == 0) {
		alert('Please select at least one draft');
		return false;
	}
	if (totalSelectedDrafts > 1) {
		alert('Please select only one draft.');
		return false;
	}
	var dcpsEmpId = document.getElementById("hidDcpsEmpIdDraft"
			+ finalSelectedEmployee).value.trim();
	popUpDcpsEmpData(dcpsEmpId, 'Y');
	return true;
}

function deleteDraft() {
	var finalSelectedEmployee = 0;
	var totalEmployees = document.getElementById("hdnCounter").value;
	var totalSelectedDrafts = 0;
	var draftDcpsEmpIds = "";
	var hidDraftOrRejected;

	for (var k = 1; k <= totalEmployees; k++) {
		if (document.getElementById("checkbox" + k).checked) {
			finalSelectedEmployee = k;
			totalSelectedDrafts++;
		}
	}

	if (finalSelectedEmployee == 0) {
		alert('Please select at least one draft');
		return false;
	}

	for (var k = 1; k <= totalEmployees; k++) {
		if (document.getElementById("checkbox" + k).checked) {
			hidDraftOrRejected = document.getElementById("hidDraftOrRejected"
					+ k).value.trim();
			if (hidDraftOrRejected == -1) {
				alert('Rejected forms can not be deleted. Please deselect the rejected forms');
				return false;
			}
			draftDcpsEmpIds = draftDcpsEmpIds
					+ document.getElementById("hidDcpsEmpIdDraft" + k).value
							.trim() + "~";
		}
	}

	var uri = "ifms.htm?actionFlag=deleteNewRegDraft";
	var url = "draftDcpsEmpIds=" + draftDcpsEmpIds;

	var answer = confirm("Are you sure you want to delete this draft?");
	if (answer) {
		showProgressbar();
		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				dataStateChangedForDelDraft(myAjax);
			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}

}
function dataStateChangedForDelDraft(myAjax) {

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	if (successFlag == 'true') {
		alert('Selected drafts are deleted');
		hideProgressbar();
		// self.location.href =
		// "ifms.htm?actionFlag=viewDraftForms&elementId=700159";
		self.location.href = "ifms.htm?actionFlag=viewDraftForms&User=ZPDDOAsst&elementId=700233";
	}
}

function printForm1ByDDO(EmpId) {
	if (document.getElementById("formPrintedOrNot") != null) {
		document.getElementById("formPrintedOrNot").value = "YES";
	}
	url = "ifms.htm?actionFlag=reportService&reportCode=700001&action=generateReport&empid="
			+ EmpId + "&asPopup=TRUE";
	window_new_update(url);
}
function approveRequest(EmpId) {
	var url;
	// alert(EmpId);
	url = "ifms.htm?actionFlag=approveReq&Emp_Id=" + EmpId;
	document.forms[0].method = 'post';
	document.forms[0].action = url;
	document.forms[0].submit();
}

function ReturnToForwardedFormsRepoDDOForward() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&User=ReportingDDO&Use=Forward&elementId=90002593";
}

function ReturnToForwardedFormsRepoDDOApproval() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&User=ReportingDDO&Use=Approval&elementId=90002598";
}

function ReturnToForwardedFormsFinalDDOForward() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Forward&elementId=90002594";
}

function ReturnToForwardedFormsFinalDDOApproval() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Approval&elementId=90002599";
}

function ReturnToForwardedFormsSpecialDDOApproval() {
	self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Approval&elementId=90002599";
}

function rejectRequestZP(EmpId) {
	// alert('Reject called');

	if (EmpId == null || EmpId == "") {
		alert("Please select a form to send back");
	} else {
		var remarks = document.getElementById("txtApproverRemarks").value;
		if (chkEmpty(document.getElementById("txtApproverRemarks"),
				'Approver Remarks') == false) {
			return false;
		}
		// showProgressbar();
		xmlHttp = GetXmlHttpObject();

		if (xmlHttp == null) {

			return;
		}
		var url = "ifms.htm?actionFlag=rejectRequestZP&Emp_Id=" + EmpId
				+ "&remarks=" + remarks;
		var User = document.getElementById("User").value.trim();
		var Use = document.getElementById("Use").value.trim();
		// alert(User);
		// alert(Use);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;

					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].textContent;
					// alert(test_Id);
					if (test_Id) {

						var elementId = "";
						var actionFlag = "";

						// For reporting DDO
						if (User == 'ReportingDDO' && Use == 'Forward') {
							actionFlag = "viewFormsForwardedByAsstZpRepoDDO";
							elementId = 90002593;
						}
						if (User == 'ReportingDDO' && Use == 'Approval') {
							actionFlag = "viewFormsForwardedByAsstZpRepoDDO";
							elementId = 90002598;
						}

						// For Final DDO
						if (User == 'FinalDDO' && Use == 'Forward') {
							actionFlag = "viewFormsForwardedByAsstZpFinalDDO";
							elementId = 90002594;
						}
						if (User == 'FinalDDO' && Use == 'Approval') {
							actionFlag = "viewFormsForwardedByAsstZpFinalDDO";
							elementId = 90002599;
						}

						// For Special DDO

						if (User == 'ReportingDDO' && Use == 'Forward') {
							actionFlag = "";
							elementId = "";
						}
						if (User == 'ReportingDDO' && Use == 'Approval') {
							actionFlag = "";
							elementId = "";
						}

						alert('Form is sent back to the ZP DDO Assistant');
						self.location.href = "ifms.htm?actionFlag="
								+ actionFlag + "&elementId=" + elementId
								+ "&User=" + User + "&Use=" + Use;
						hideProgressbar();
					}
				}
			}
		};
		xmlHttp.open("POST", url, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(url);

		alert('Form is sent back to the ZP DDO Assistant');

		if (User == 'FinalDDO' && Use == 'Approval') {
			self.location.href = 'ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Approval';
		}
		if (User == 'ReportingDDO' && Use == 'Forward') {
			self.location.href = 'ifms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&User=ReportingDDO&Use=Forward';

		}
		if (User == 'ReportingDDO' && Use == 'Approval') {
			self.location.href = 'ifms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&User=ReportingDDO&Use=Approval';
		}
	}
	hideProgressbar();
}

function ForwardRequestReportingDDO(empId) {

	showProgressbar();
	var emp_Id = empId;
	// alert("ForwardRequestReportingDDO"+emp_Id);
	if (emp_Id == null || emp_Id == "") {
		alert("Please select a form to forward");
		hideProgressbar();
	} else {
		var ForwardToPost = document.getElementById("ForwardToPost").value;
		// alert("ForwardRequestReportingDDO : "+ForwardToPost);
		ForwardRequestUsingAjaxRptDDO("ifms.htm?actionFlag=FwdFromRepoDDO&Emp_Id="
				+ emp_Id + "&ForwardToPost=" + ForwardToPost);
	}
}

function ForwardRequestUsingAjaxRptDDO(url) {

	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	uri = url;
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;

				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

				if (test_Id) {

					var User = document.getElementById("User").value.trim();
					var Use = document.getElementById("Use").value.trim();

					alert('Form is Successfully Forwarded to Level 3 DDO');
					self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpRepoDDO&elementId=90002593&User="
							+ User + "&Use=" + Use;
				}
			}
		}
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function ForwardRequestFinalDDO(empId) {

	showProgressbar();

	var emp_Id = empId;

	if (emp_Id == null || emp_Id == "") {
		alert("Please select a form to forward");
		hideProgressbar();
	} else {
		var ForwardToPost = document.getElementById("ForwardToPost").value;

		ForwardRequestUsingAjaxFinalDDO("ifms.htm?actionFlag=FwdFromFinalDDO&Emp_Id="
				+ emp_Id + "&ForwardToPost=" + ForwardToPost);
	}
}

function ForwardRequestUsingAjaxFinalDDO(url) {

	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	uri = url;
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;

				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

				if (test_Id) {

					var User = document.getElementById("User").value.trim();
					var Use = document.getElementById("Use").value.trim();

					alert('Form is Successfully Forwarded to Level 4 DDO');
					self.location.href = "ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&elementId=90002594&User="
							+ User + "&Use=" + Use;
				}
			}
		}
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function approveFormZP(empId) {
	showProgressbar();
	// alert("Called");
	var emp_Id = empId;
	var dcpsAcntNo = document.getElementById("txtAcNoForNonSRKAEmp").value;
	var dcpsAcntMaintaingAuth = document.getElementById("txtOthersNonSRKAEmp").value;

	if (emp_Id == null || emp_Id == "") {
		alert("Please select a form to approve");
		hideProgressbar();
	} else {
		ApproveRequestUsingAjax("ifms.htm?actionFlag=approveReq&Emp_Id="
				+ emp_Id + "&dcpsAcntNo=" + dcpsAcntNo
				+ "&dcpsAcntMaintaingAuth=" + dcpsAcntMaintaingAuth);

	}
}

function ApproveRequestUsingAjax(url) {
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}

	uri = url;
	xmlHttp.onreadystatechange = ApprovedDataStateChanged;
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function ApprovedDataStateChanged() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var successFlag = XmlHiddenValues[0].childNodes[1].textContent;
			var dcps_Id = XmlHiddenValues[0].childNodes[3].textContent;
			var empId = XmlHiddenValues[0].childNodes[5].textContent;
			var billGroupSuccessFlag = XmlHiddenValues[0].childNodes[7].textContent;

			if (successFlag) {
				var answer;
				if (!billGroupSuccessFlag) {
					answer = confirm("DCPS ID "
							+ dcps_Id
							+ " Registered Successfully. Employee is not attached to any billgroup. Do you want to print Acknowledgement?");
				} else {
					answer = confirm("DCPS ID "
							+ dcps_Id
							+ " Registered Successfully. Do you want to print Acknowledgement?");
				}
				if (answer) {
					// printAcknowledgementReport(empId);//DCPSForm
					self.location.href = 'ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Approval&elementId=90002599';
					// self.location.href='ifms.htm?actionFlag=viewFormsForwardedByAsstZpSpecialDDO&User=SpecialDDO&Use=Approval';
				} else {
					// self.location.href='ifms.htm?actionFlag=viewFormsForwardedByAsstZpSpecialDDO&User=SpecialDDO&Use=Approval';
					self.location.href = 'ifms.htm?actionFlag=viewFormsForwardedByAsstZpFinalDDO&User=FinalDDO&Use=Approval&elementId=90002599';
				}
				hideProgressbar();
			}
		}
	}
}

function showProgressbarForBank() {
	document.getElementById("txtIFSCCode").value = "";
	showProgressbar();
}

function hideProgressbarForBank() {
	hideProgressbar();
}

function validatePIPBAndChangeBasic() {
	paycommission = document.forms[0].cmbPayCommission.value.trim();
	if (paycommission == '700016') {
		var PIPB = document.forms[0].txtPayInPayBand.value.trim();
		var minPIPB;
		var maxPIPB;
		var gradePay;

		if (document.forms[0].cmbPayScale != null) {
			if (document.forms[0].cmbPayScale.value != -1
					&& document.forms[0].cmbPayScale.value != "") {
				// validate PIPB
				var payscale = document.forms[0].cmbPayScale.options[document.forms[0].cmbPayScale.selectedIndex].text;
				var tempArray1 = payscale.split("-");
				minPIPB = tempArray1[0];
				var tempArray2 = tempArray1[1].trim().split("(");
				maxPIPB = tempArray2[0];
				gradePay = tempArray2[1].split(")")[0];

				if (PIPB != "") {
					if (Number(PIPB) < Number(minPIPB)) {
						alert('Pay In Pay band must be greater than ' + minPIPB);
						document.forms[0].txtPayInPayBand.value = "";
						return false;
					}

					if (Number(PIPB) > Number(maxPIPB)) {
						alert('Pay In Pay band must be less than ' + maxPIPB);
						document.forms[0].txtPayInPayBand.value = "";
						return false;
					}

					document.forms[0].txtBasicPay.value = Number(PIPB)
							+ Number(gradePay);
				}

			}
		}
	}
	return true;
}

function checkForPIPBAndGradePay() {
	var paycommission = "";
	var payscale = "";
	var payscaleValue = "";
	var gradePay = "";

	if (document.forms[0].cmbPayCommission != null) {
		paycommission = document.forms[0].cmbPayCommission.value.trim();
	}
	if (document.forms[0].cmbPayScale != null) {
		payscaleValue = document.forms[0].cmbPayScale.value.trim();
		if (payscaleValue != '-1') {
			payscale = document.forms[0].cmbPayScale.options[document.forms[0].cmbPayScale.selectedIndex].text;
		}
	}

	if (document.forms[0].cmbPayCommission != null) {
		if (paycommission == '700016') {
			// Show pay in pay band and grade pay for the employee

			document.getElementById("payInPayBandAndGradePayRow").style.display = "contents"; 
			document.getElementById("lableMandatoryForPayInPayBand").style.display = "inline";
			document.getElementById("lableMandatoryForGradePay").style.display = "inline";

			if (document.forms[0].cmbPayScale != null) {
				if (payscaleValue != '' && payscaleValue != '-1') {
					var tempArray = payscale.split("(");
					var gradePay7 = tempArray[1].split(")")[0].trim();
					document.forms[0].txtGradePay.value = gradePay7;
				}
			}

		} else {
			document.getElementById("payInPayBandAndGradePayRow").style.display = "none";
			document.getElementById("lableMandatoryForPayInPayBand").style.display = "none";
			document.getElementById("lableMandatoryForGradePay").style.display = "none";
			document.forms[0].txtBasicPay.value = "";
		}
	}

}

function GISothers() {
	var others = "";

	if (document.forms[0].others != null) {
		if (others == '700342') {
			document.getElementById("others").style.display = "inline";
		} else {
			document.getElementById("others").style.display = "none";
		}
	}
}

function validatePIPBAndGradePay() {
	var paycommission = "";
	if (document.forms[0].cmbPayCommission != null) {
		paycommission = document.forms[0].cmbPayCommission.value.trim();
		if (paycommission == '700016') {
			var PIPB = document.forms[0].txtPayInPayBand.value.trim();
			var GradePay = document.forms[0].txtGradePay.value.trim();
			if (PIPB == "" || PIPB == 0) {
				alert('Please enter Pay in Pay band value');
				return false;
			}
			if (GradePay == "") {
				alert('Please enter Grade Pay value');
				return false;
			}
		}
	}

	return true;
}

function chkEmptyWithoutFocus(ctrl, msg) {
	var str = ctrl.value;
	if (str == "" || str == "-1") {
		alert(msg + " Cannot be Empty.");
		// ctrl.focus();
		return false;
	} else
		return true;
}

function validateUIDUniqe() {
	// alert('inside validateUIDUniqe');
	var UID1 = document.getElementById("txtUIDNo1").value;
	var UID2 = document.getElementById("txtUIDNo2").value;
	var UID3 = document.getElementById("txtUIDNo3").value;
	if (UID1 == "" || UID1 == null || UID2 == "" || UID2 == null || UID3 == ""
			|| UID3 == null) {
		alert('Please enter complete UID number.');

	} else {
		var UID = UID1 + UID2 + UID3;
		// alert('complete UID: '+UID);

		var uri = 'ifms.htm?actionFlag=validateUIDUniqeness';
		var url = 'UID=' + UID;

		var myAjax = new Ajax.Request(uri, {
			method : 'post',
			asynchronous : false,
			parameters : url,
			onSuccess : function(myAjax) {
				checkUID(myAjax, UID);

			},
			onFailure : function() {
				alert('Something went wrong...');
			}
		});
	}

}

function checkUID(myAjax, UID) {
	// alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	// alert("checkFlag"+checkFlag);
	if (checkFlag == 'correct') {
		status = true;
		// alert('all ok');
	} else if (checkFlag == 'wrong') {

		alert('Entered UID number: '
				+ UID
				+ ' is already present in system. Please enter correct UID number.');

		document.getElementById("txtUIDNo1").value = "";
		document.getElementById("txtUIDNo2").value = "";
		document.getElementById("txtUIDNo3").value = "";
		status = false;
	}
	return status;
}