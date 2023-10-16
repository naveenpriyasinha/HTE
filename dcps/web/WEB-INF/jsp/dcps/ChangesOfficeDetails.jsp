<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>

<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="EMPVOMST" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVOMST" value="${resValue.lObjRltDcpsPayrollEmp}"></c:set>

<c:set var="EMPVO" value="${resValue.lObjHstDcpsOfficeChanges}"></c:set>

<c:set var="DDOOFFICEVOMST" value="${resValue.lObjDdoOfficeVOMst}"></c:set>
<c:set var="DDOOFFICEVO" value="${resValue.lObjDdoOfficeVO}"></c:set>
<c:set var="CADRELIST" value="${resValue.CADRELISTMST}"></c:set>
<c:set var="OFFICELIST" value="${resValue.OFFICELIST}" />

<c:set var="ddoOfficeId" value="${resValue.ddoOfficeId}"></c:set>
<c:set var="ddoOfficeDesc" value="${resValue.ddoOfficeDesc}"></c:set>


<c:set var="CHANGESHISTORYVO" value="${resValue.lObjHstDcpsChanges}"></c:set>

<c:choose>
	<c:when test="${EMPVO != null}">
		<input type="hidden" id="hidPayCommission"
			value="${EMPVO.payCommission}" />
	</c:when>
	<c:otherwise>
		<input type="hidden" id="hidPayCommission"
			value="${EMPVOMST.payCommission}" />
	</c:otherwise>
</c:choose>

<c:if test="${resValue.UserType == 'DDO'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>

<script>
	function ReturnToChangesInDDOAsst() {
		var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value
				.trim();
		if (dcpsHstChangesId == "") {
			var designationId = document.getElementById("lStrDesignation").value
					.trim();
			var url = "ifms.htm?actionFlag=loadChangesForm&elementId=700163&DesignationId="
					+ designationId
					+ "&Changes=700041&User=DDOAsst&Type=Office";
			self.location.href = url;
		} else {
			var User = document.getElementById("User").value.trim();
			var designationIdDraft = document
					.getElementById("lStrDesignationDraft").value.trim();
			var url = "ifms.htm?actionFlag=loadChangesDrafts&DesignationId="
					+ designationIdDraft + "&User=" + User;
			self.location.href = url;
		}
	}
	function GoBackToSearchPage() {
		var hidDcpsId = document.getElementById("hidDcpsId").value.trim();
		var hidEmpName = document.getElementById("hidEmpName").value.trim();
		var hidBirthDate = document.getElementById("hidBirthDate").value.trim();
		var hidSevarthId = document.getElementById("hidSevarthId").value.trim();
		var hidName = document.getElementById("hidName").value.trim();
		self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="
				+ hidDcpsId + "&txtEmployeeName=" + hidEmpName
				+ "&txtBirthDate=" + hidBirthDate + "&Criteria=1"
				+ "&sevarthId=" + hidSevarthId + "&employeeName=" + hidName;
	}

	function EnableSalChangesFields() {
		var cmbPayScale = document.getElementById("cmbPayScale").value.trim();
		if (cmbPayScale != -1) {
			document.getElementById("cmbReasonForSalChange").disabled = false;
			document.getElementById("txtWithEffectFromDate").readOnly = false;
			document.getElementById("labelForReasonForPSChange").style.display = 'inline';
			document.getElementById("labelForWEFDateForPSChange").style.display = 'inline';
			document.getElementById("labelForBasicPayForPSChange").style.display = 'inline';
		} else {
			document.getElementById("cmbReasonForSalChange").disabled = true;
			document.getElementById("txtWithEffectFromDate").readOnly = true;
			document.getElementById("labelForReasonForPSChange").style.display = 'none';
			document.getElementById("labelForWEFDateForPSChange").style.display = 'none';
			document.getElementById("labelForBasicPayForPSChange").style.display = 'none';
		}
	}
	function checkForOtherReason() {
		var reasonId = document.getElementById("cmbReasonForSalChange").value
				.trim();
		if (reasonId == 700161 || reasonId == 700172) {
			document.getElementById("txtOtherReason").readOnly = false;
			document.getElementById("labelForOtherReasonForPSChange").style.display = 'inline';
		} else {
			document.getElementById("txtOtherReason").readOnly = true;
			document.getElementById("labelForOtherReasonForPSChange").style.display = 'none';
		}
	}

	function popReasonsForSalaryChange() {
		xmlHttp = GetXmlHttpObject();

		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var hidPayCommission = document.getElementById('hidPayCommission').value
				.trim();

		var uri = "ifms.htm?actionFlag=popUpReasonsForSalaryChange&cmbPayCommission="
				+ hidPayCommission;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {

				if (xmlHttp.status == 200) {

					var cmbReasonForSalChange = document.getElementById(
							'cmbReasonForSalChange').trim();
					cmbReasonForSalChange.options.length = 0;
					var optn = document.createElement("OPTION");
					optn.text = "-- Select --";
					optn.value = "-1";
					cmbReasonForSalChange.options.add(optn);

					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var totalReasons = XmlHiddenValues[0].childNodes[0].textContent;

					var count = 1;
					while (count <= (2 * totalReasons)) {
						var optn = document.createElement("OPTION");
						optn.value = XmlHiddenValues[0].childNodes[count].text;
						optn.text = XmlHiddenValues[0].childNodes[count + 1].text;
						document.getElementById("cmbReasonForSalChange").options
								.add(optn);
						count = count + 2;
					}
				}
			}
		};

		xmlHttp.open("POST", uri, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(uri);
	}

	function getChangedFieldsUrl() {
		var urlConstructed = "";

		var listParentDept1 = document.getElementById("listParentDept1").value
				.trim();
		var cmbCadre1 = document.getElementById("cmbCadre1").value.trim();
		var txtGroup1 = document.getElementById("txtGroup1").value.trim();
		var cmbCurrentOffice1 = document.getElementById("cmbCurrentOffice1").value
				.trim();

		var txtRemarks1 = document.getElementById("txtRemarks1").value.trim();
		var cmbDesignationFirst1 = document
				.getElementById("cmbFirstDesignation1").value.trim();
		var txtJoinParentDeptDate1 = document
				.getElementById("txtJoinParentDeptDate1").value.trim();
		var txtJoinPostDate1 = document.getElementById("txtJoinPostDate1").value
				.trim();
		var txtJoinCadreDate1 = document.getElementById("txtJoinCadreDate1").value
				.trim();
		var cmbPayScale1 = document.getElementById("cmbPayScale1").value.trim();
		var txtBasicPay1 = document.getElementById("txtBasicPay1").value.trim();

		var listParentDept = document.getElementById("listParentDept").value
				.trim();
		var cmbCadre = document.getElementById("cmbCadre").value.trim();
		var txtGroup = document.getElementById("txtGroup").value.trim();
		var cmbCurrentOffice = document.getElementById("cmbCurrentOffice").value
				.trim();
		var cmbDesignation1 = document.getElementById("cmbDesignation1").value
				.trim();
		var cmbDesignation = document.getElementById("cmbDesignation").value
				.trim();
		var cmbPayCommission = document.getElementById("cmbPayCommission").value
				.trim();
		var cmbPayCommission1 = document.getElementById("cmbPayCommission1").value
				.trim();

		var txtRemarks = document.getElementById("txtRemarks").value.trim();
		var cmbDesignationFirst = document
				.getElementById("cmbFirstDesignation").value.trim();
		var txtJoinParentDeptDate = document
				.getElementById("txtJoinParentDeptDate").value.trim();
		var txtJoinPostDate = document.getElementById("txtJoinPostDate").value
				.trim();
		var txtJoinCadreDate = document.getElementById("txtJoinCadreDate").value
				.trim();
		var cmbPayScale = document.getElementById("cmbPayScale").value.trim();
		var txtBasicPay = document.getElementById("txtBasicPay").value.trim();

		urlConstructed = urlConstructed + "&change=" + "OfficeDetails"
				+ "&changeDetails=";

		var parentFieldDept = '<fmt:message key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message>';
		var cadre = '<fmt:message key="CMN.CADRE" bundle="${dcpsLables}"></fmt:message>';
		var group = '<fmt:message key="CMN.GROUP" bundle="${dcpsLables}"></fmt:message>';
		var currentOffice = '<fmt:message key="CMN.CURRENTOFFICE" bundle="${dcpsLables}"></fmt:message>';
		var designation = '<fmt:message key="CMN.DESIGNATION" bundle="${dcpsLables}"></fmt:message>';
		var payCommission = '<fmt:message key="CMN.PAYCOMMISSION" bundle="${dcpsLables}"></fmt:message>';
		var remarks = '<fmt:message key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message>';
		var designationFirst = '<fmt:message key="CMN.DESIGNATIONFIRSTAPNTMNT" bundle="${dcpsLables}"></fmt:message>';
		var joinParentDeptDate = '<fmt:message key="CMN.DOJPARENTDEPT" bundle="${dcpsLables}"></fmt:message>';
		var joinPostDate = '<fmt:message key="CMN.DOJPOST" bundle="${dcpsLables}"></fmt:message>';
		var joinCadreDate = '<fmt:message key="CMN.DOJCADRE" bundle="${dcpsLables}"></fmt:message>';
		var payScale = '<fmt:message key="CMN.PAYSCALE" bundle="${dcpsLables}"></fmt:message>';
		var basicPay = '<fmt:message key="CMN.BASICPAY" bundle="${dcpsLables}"></fmt:message>';

		if (listParentDept != listParentDept1 && listParentDept != -1) {
			urlConstructed = urlConstructed + parentFieldDept + ","
					+ listParentDept + "," + listParentDept1 + "~";
		}
		if (cmbCadre != cmbCadre1 && cmbCadre != -1) {
			urlConstructed = urlConstructed + cadre + "," + cmbCadre + ","
					+ cmbCadre1 + "~";
		}
		if (txtGroup != txtGroup1 && txtGroup != "") {
			urlConstructed = urlConstructed + group + "," + txtGroup + ","
					+ txtGroup1 + "~";
		}
		if (cmbCurrentOffice != cmbCurrentOffice1 && cmbCurrentOffice != -1) {
			urlConstructed = urlConstructed + currentOffice + ","
					+ cmbCurrentOffice + "," + cmbCurrentOffice1 + "~";
		}
		if (cmbDesignation != cmbDesignation1 && cmbDesignation != -1) {
			urlConstructed = urlConstructed + designation + ","
					+ cmbDesignation + "," + cmbDesignation1 + "~";
		}
		if (cmbPayCommission != cmbPayCommission1 && cmbPayCommission != -1) {
			urlConstructed = urlConstructed + payCommission + ","
					+ cmbPayCommission + "," + cmbPayCommission1 + "~";
		}
		if (txtRemarks != txtRemarks1 && txtRemarks != "") {
			urlConstructed = urlConstructed + remarks + "," + txtRemarks + ","
					+ txtRemarks1 + "~";
		}
		if (cmbDesignationFirst != cmbDesignationFirst1
				&& cmbDesignationFirst != "" && cmbDesignationFirst != -1) {
			urlConstructed = urlConstructed + designationFirst + ","
					+ cmbDesignationFirst + "," + cmbDesignationFirst1 + "~";
		}
		if (txtJoinParentDeptDate != txtJoinParentDeptDate1
				&& txtJoinParentDeptDate != "") {
			urlConstructed = urlConstructed + joinParentDeptDate + ","
					+ txtJoinParentDeptDate + "," + txtJoinParentDeptDate1
					+ "~";
		}
		if (txtJoinPostDate != txtJoinPostDate1 && txtJoinPostDate != "") {
			urlConstructed = urlConstructed + joinPostDate + ","
					+ txtJoinPostDate + "," + txtJoinPostDate1 + "~";
		}
		if (txtJoinCadreDate != txtJoinCadreDate1 && txtJoinCadreDate != "") {
			urlConstructed = urlConstructed + joinCadreDate + ","
					+ txtJoinCadreDate + "," + txtJoinCadreDate1 + "~";
		}
		if (cmbPayScale != cmbPayScale1 && cmbPayScale != -1) {
			urlConstructed = urlConstructed + payScale + "," + cmbPayScale
					+ "," + cmbPayScale1 + "~";
		}
		if (txtBasicPay != txtBasicPay1 && txtBasicPay != "") {
			urlConstructed = urlConstructed + basicPay + "," + txtBasicPay
					+ "," + txtBasicPay1 + "~";
		}

		return urlConstructed;

	}
	function approveOfficeDetails() {
		showProgressbar();
		var dcpsChangesId = document.getElementById("dcpsHstChangesId").value
				.trim();
		var designationIdDraft = document
				.getElementById("lStrDesignationDraft").value.trim();
		var User = document.getElementById("User").value.trim();

		xmlHttp = GetXmlHttpObject();

		if (xmlHttp == null) {
			hideProgressbar();
			return;
		}

		var uri = 'ifms.htm?actionFlag=approveChangesByDDO';
		var url = uri + '&dcpsChangesId=' + dcpsChangesId;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {

					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

					if (test_Id) {
						alert('The Changes are Approved.');
						self.location.href = "ifms.htm?actionFlag=loadChangesDrafts&DesignationId="
								+ designationIdDraft + "&User=" + User;
					}
				}
			}
		};

		xmlHttp.open("POST", uri, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}

	function rejectOfficeDetails() {
		showProgressbar();
		var dcpsChangesId = document.getElementById("dcpsHstChangesId").value
				.trim();
		var designationIdDraft = document
				.getElementById("lStrDesignationDraft").value.trim();
		var sentBackRemarks = document.getElementById("sentBackRemarks").value
				.trim();
		var User = document.getElementById("User").value.trim();

		if (sentBackRemarks == "") {
			alert('Please Enter Remarks.');
			hideProgressbar();
			return false;
		}

		xmlHttp = GetXmlHttpObject();

		if (xmlHttp == null) {
			hideProgressbar();
			return;
		}

		var uri = 'ifms.htm?actionFlag=rejectChangesToDDOAsst';
		var url = uri + '&dcpsChangesId=' + dcpsChangesId + '&sentBackRemarks='
				+ sentBackRemarks;
		//alert(url);
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {

					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

					if (test_Id) {
						alert('The changes are rejected and sent back to DDO Assistant.');
						self.location.href = "ifms.htm?actionFlag=loadChangesDrafts&DesignationId="
								+ designationIdDraft + "&User=" + User;
					}
				}
			}
		};

		xmlHttp.open("POST", uri, false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}
	function validateOfficeChangeDtls(flag) {
		var changeFlag = false;

		var listParentDept1 = document.getElementById("listParentDept1").value
				.trim();
		var cmbCadre1 = document.getElementById("cmbCadre1").value.trim();
		var txtGroup1 = document.getElementById("txtGroup1").value.trim();
		var cmbDesignation1 = document.getElementById("cmbDesignation1").value
				.trim();
		var cmbDesignation = document.getElementById("cmbDesignation").value
				.trim();
		var cmbPayCommission = document.getElementById("cmbPayCommission").value
				.trim();
		var cmbPayCommission1 = document.getElementById("cmbPayCommission1").value
				.trim();
		var txtSuperAnnuation1 = document.getElementById("txtSuperAnnuation1").value
				.trim();
		var txtSuperAnnDate1 = document.getElementById("txtSuperAnnDate1").value
				.trim();
		var cmbCurrentOffice1 = document.getElementById("cmbCurrentOffice1").value
				.trim();
		var txtOfficeAddress1 = document.getElementById("txtOfficeAddress1").value
				.trim();
		var txtOfficeContactNo11 = document
				.getElementById("txtOfficeContactNo11").value.trim();
		var txtOfficeMobile1 = document.getElementById("txtOfficeMobile1").value
				.trim();
		var txtOfficeContactNo21 = document
				.getElementById("txtOfficeContactNo21").value.trim();
		var txtOfficeEmailId1 = document.getElementById("txtOfficeEmailId1").value
				.trim();
		var txtOfficeCityClass1 = document
				.getElementById("txtOfficeCityClass1").value.trim();

		var cmbDesignationFirst1 = document
				.getElementById("cmbFirstDesignation1").value.trim();
		var txtJoinParentDeptDate1 = document
				.getElementById("txtJoinParentDeptDate1").value.trim();
		var txtRemarks1 = document.getElementById("txtRemarks1").value.trim();
		var txtJoinPostDate1 = document.getElementById("txtJoinPostDate1").value
				.trim();
		var txtJoinCadreDate1 = document.getElementById("txtJoinCadreDate1").value
				.trim();
		var cmbPayScale1 = document.getElementById("cmbPayScale1").value.trim();
		var txtBasicPay1 = document.getElementById("txtBasicPay1").value.trim();

		var cmbDesignationFirst = document
				.getElementById("cmbFirstDesignation").value.trim();
		var txtJoinParentDeptDate = document
				.getElementById("txtJoinParentDeptDate").value.trim();
		var txtRemarks = document.getElementById("txtRemarks").value.trim();
		var txtJoinPostDate = document.getElementById("txtJoinPostDate").value
				.trim();
		var txtJoinCadreDate = document.getElementById("txtJoinCadreDate").value
				.trim();
		var cmbPayScale = document.getElementById("cmbPayScale").value.trim();
		var txtBasicPay = document.getElementById("txtBasicPay").value.trim();

		var listParentDept = document.getElementById("listParentDept").value
				.trim();
		var cmbCadre = document.getElementById("cmbCadre").value.trim();
		var txtGroup = document.getElementById("txtGroup").value.trim();
		var txtSuperAnnuation = document.getElementById("txtSuperAnnuation").value
				.trim();
		var txtSuperAnnDate = document.getElementById("txtSuperAnnDate").value
				.trim();
		var cmbCurrentOffice = document.getElementById("cmbCurrentOffice").value
				.trim();
		var txtOfficeAddress = document.getElementById("txtOfficeAddress").value
				.trim();
		var txtOfficeContactNo1 = document
				.getElementById("txtOfficeContactNo1").value.trim();
		var txtOfficeMobile = document.getElementById("txtOfficeMobile").value
				.trim();
		var txtOfficeContactNo2 = document
				.getElementById("txtOfficeContactNo2").value.trim();
		var txtOfficeEmailId = document.getElementById("txtOfficeEmailId").value
				.trim();
		var txtOfficeCityClass = document.getElementById("txtOfficeCityClass").value
				.trim();

		var txtAuthorityLetterNo = document
				.getElementById("txtAuthorityLetterNo").value.trim();
		var txtAuthorityLetterDate = document
				.getElementById("txtAuthorityLetterDate").value.trim();

		if (listParentDept1 != listParentDept || cmbCadre1 != cmbCadre
				|| txtGroup1 != txtGroup || txtRemarks1 != txtRemarks
				|| txtSuperAnnuation != txtSuperAnnuation1
				|| txtSuperAnnDate != txtSuperAnnDate1
				|| cmbCurrentOffice != cmbCurrentOffice1
				|| txtOfficeAddress != txtOfficeAddress1
				|| txtOfficeContactNo1 != txtOfficeContactNo11
				|| txtOfficeMobile != txtOfficeMobile1
				|| txtOfficeContactNo2 != txtOfficeContactNo21
				|| txtOfficeEmailId != txtOfficeEmailId1
				|| txtOfficeCityClass != txtOfficeCityClass1
				|| txtJoinPostDate != txtJoinPostDate1
				|| txtJoinCadreDate != txtJoinCadreDate1
				|| cmbDesignationFirst != cmbDesignationFirst1
				|| txtJoinParentDeptDate1 != txtJoinParentDeptDate
				|| cmbDesignation1 != cmbDesignation
				|| cmbPayCommission1 != cmbPayCommission
				|| cmbPayScale != cmbPayScale1 || txtBasicPay != txtBasicPay1) {
			if (listParentDept != -1 || cmbCadre != -1 || txtGroup != ""
					|| txtRemarks != "" || cmbCurrentOffice != -1
					|| txtJoinPostDate != "" || txtJoinCadreDate != ""
					|| cmbDesignationFirst != "" || txtJoinParentDeptDate != ""
					|| cmbPayCommission != -1 || cmbPayScale != -1
					|| txtBasicPay != "" || cmbDesignation != -1) {
				changeFlag = true;
			}
		}

		if (cmbPayScale != -1) {
			if (document.getElementById("cmbReasonForSalChange").value.trim() == -1) {
				alert('Please provide the reason for Pay Scale Change.');
				return false;
			}
			if (document.getElementById("cmbReasonForSalChange").value.trim() == '700161'
					|| document.getElementById("cmbReasonForSalChange").value
							.trim() == '700172') {
				if (document.getElementById("txtOtherReason").value.trim() == "") {
					alert('Please Provide Other Reason');
					return false;
				}
			}
			if (document.getElementById("txtBasicPay").value.trim() == "") {
				alert('Please provide basic pay');
				return false;
			}
			if (document.getElementById("txtWithEffectFromDate").value.trim() == "") {
				alert('Please provide With Effect From Date for Pay Scale Change.');
				return false;
			}
		}

		if (changeFlag == false) {
			alert('You have not chaged any details');
			return false;
		}

		if (flag == 2) {
			if (txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "") {
				alert('Please fill the Authority Details.');
				return false;
			}
		}

		return true;
	}

	function getOfficeDetails() {
		var officeId = document.getElementById('cmbCurrentOffice').value;

		if (officeId == 1) {
			document.getElementById('txtOfficeAddress').value = "";
			document.getElementById('txtOfficeContactNo1').value = "";
			document.getElementById('txtOfficeMobile').value = "";
			document.getElementById('txtOfficeContactNo2').value = "";
			document.getElementById('txtOfficeEmailId').value = "";
			return;
		}
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}

		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var uri = "ifms.htm?actionFlag=popOfficeDet";
		url = uri + "&officeId=" + officeId;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;

					if (XMLDoc != null) {

						var XMLEntries = XMLDoc.getElementsByTagName('XMLDOC');

						var address1 = XMLEntries[0].childNodes[0].text;

						var contact1 = XMLEntries[0].childNodes[1].text;
						var contact2 = XMLEntries[0].childNodes[2].text;
						var contact3 = XMLEntries[0].childNodes[3].text;
						var email = XMLEntries[0].childNodes[4].text;
						var address2 = XMLEntries[0].childNodes[5].text;

						document.getElementById('txtOfficeAddress').value = address1
								+ " " + address2;
						document.getElementById('txtOfficeContactNo1').value = contact1;
						document.getElementById('txtOfficeMobile').value = contact2;
						document.getElementById('txtOfficeContactNo2').value = contact3;
						document.getElementById('txtOfficeEmailId').value = email;

						document.getElementById('txtOfficeAddress').readOnly = true;
						document.getElementById('txtOfficeContactNo1').readOnly = true;
						document.getElementById('txtOfficeMobile').readOnly = true;
						document.getElementById('txtOfficeContactNo2').readOnly = true;
						document.getElementById('txtOfficeEmailId').readOnly = true;

					}
				}
			}
		};
		xmlHttp.open("POST", url, true);

		xmlHttp.send(url);

	}

	function updateOrForwardOfficeDetails(emp_id, flag) {
		showProgressbar();
		if (!validateOfficeChangeDtls(flag)) {
			hideProgressbar();
			return false;
		}

		var empId = emp_id;
		var saveOrForwardFlag = flag;
		var designationId = document.getElementById("lStrDesignation").value;
		var typeOfChanges = document.getElementById("lStrChangesType").value;

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			hideProgressbar();
			return;
		}

		var uri = 'ifms.htm?actionFlag=updateOfficeDtls';
		var url = runForm(0);
		var urlChangedFields = getChangedFieldsUrl();
		url = uri + url + urlChangedFields;
		url = url + "&empId=" + empId;
		var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value
				.trim();
		var designationIdDraft = document
				.getElementById("lStrDesignationDraft").value.trim();
		url = url + "&dcpsHstChangesId=" + dcpsHstChangesId;

		var hidDcpsId = document.getElementById("hidDcpsId").value.trim();
		var hidEmpName = document.getElementById("hidEmpName").value.trim();
		var hidBirthDate = document.getElementById("hidBirthDate").value.trim();
		var hidSevarthId = document.getElementById("hidSevarthId").value.trim();
		var hidName = document.getElementById("hidName").value.trim();
		var lblFlagFromSearch = false;
		var hidFromSearchEmp = document.getElementById("hidFromSearchEmp").value
				.trim();
		var hidFromChangesOfficeElement = document
				.getElementById("hidFromChangesOfficeElement").value.trim();

		if (hidDcpsId != "" || (hidEmpName != "" && hidBirthDate != "")
				|| hidSevarthId != "" || hidName != "") {
			if (hidFromSearchEmp != 'NO') {
				lblFlagFromSearch = true;
			}
		}

		//alert(lblFlagFromSearch);

		//self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="+hidDcpsId+"&txtEmployeeName="+hidEmpName+"&txtBirthDate="+hidBirthDate+"&Criteria=1"+"&sevarthId="+hidSevarthId+"&employeeName="+hidName;

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {

					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].textContent;
					var dcpsChangesId = XmlHiddenValues[0].childNodes[1].textContent;
					if (test_Id) {
						var User = document.getElementById("User").value.trim();
						if (saveOrForwardFlag == 1) {
							if (dcpsHstChangesId == "") {
								alert('Office Details have been successfully changed and saved.');
								if (!lblFlagFromSearch) {
									//self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
									self.location.href = "ifms.htm?actionFlag=loadChangesForm&elementId=700163&DesignationId="
											+ designationId
											+ "&Changes=700041&User=DDOAsst&Type=Office";
								} else {
									self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="
											+ hidDcpsId
											+ "&txtEmployeeName="
											+ hidEmpName
											+ "&txtBirthDate="
											+ hidBirthDate
											+ "&Criteria=1"
											+ "&sevarthId="
											+ hidSevarthId
											+ "&employeeName=" + hidName;
								}
							} else {
								alert('Office Details have been successfully changed and saved.');
								if (!lblFlagFromSearch) {
									self.location.href = "ifms.htm?actionFlag=loadChangesDrafts&DesignationId="
											+ designationIdDraft
											+ "&User="
											+ User;
								} else {
									self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="
											+ hidDcpsId
											+ "&txtEmployeeName="
											+ hidEmpName
											+ "&txtBirthDate="
											+ hidBirthDate
											+ "&Criteria=1"
											+ "&sevarthId="
											+ hidSevarthId
											+ "&employeeName=" + hidName;
								}
							}
						}
						if (saveOrForwardFlag == 2) {
							var ForwardToPost = document
									.getElementById("ForwardToPost").value
									.trim();
							var uriForward;
							if (dcpsHstChangesId == "") {
								uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="
										+ dcpsChangesId
										+ "&ForwardToPost="
										+ ForwardToPost;
							} else {
								uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="
										+ dcpsHstChangesId
										+ "&ForwardToPost="
										+ ForwardToPost;
							}
							//alert(uriForward);
							xmlHttpNew = GetXmlHttpObject();

							if (xmlHttpNew == null) {
								alert("Your browser does not support AJAX!");
								return;
							}
							xmlHttpNew.onreadystatechange = function() {
								if (xmlHttpNew.readyState == 4) {
									if (xmlHttpNew.status == 200) {

										XMLDocNew = xmlHttpNew.responseXML.documentElement;
										var XmlHiddenValuesNew = XMLDocNew
												.getElementsByTagName('XMLDOC');
										var success_flag = XmlHiddenValuesNew[0].childNodes[0].textContent;
										if (success_flag == 'true') {
											if (dcpsHstChangesId == "") {
												alert("Changes are forwarded successfully");
												if (!lblFlagFromSearch) {
													//self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
													self.location.href = "ifms.htm?actionFlag=loadChangesForm&elementId=700163&DesignationId="
															+ designationId
															+ "&Changes=700041&User=DDOAsst&Type=Office";
												} else {
													self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="
															+ hidDcpsId
															+ "&txtEmployeeName="
															+ hidEmpName
															+ "&txtBirthDate="
															+ hidBirthDate
															+ "&Criteria=1"
															+ "&hidSevarthId="
															+ hidSevarthId
															+ "&employeeName="
															+ hidName;
												}
											} else {
												alert("Changes are forwarded successfully");
												if (!lblFlagFromSearch) {
													self.location.href = "ifms.htm?actionFlag=loadChangesDrafts&DesignationId="
															+ designationIdDraft
															+ "&User=" + User;
												} else {
													self.location.href = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="
															+ hidDcpsId
															+ "&txtEmployeeName="
															+ hidEmpName
															+ "&txtBirthDate="
															+ hidBirthDate
															+ "&Criteria=1"
															+ "&hidSevarthId="
															+ hidSevarthId
															+ "&employeeName="
															+ hidName;
												}
											}
										}
									}
								}
							};
							xmlHttpNew.open("POST", uriForward, false);
							xmlHttpNew.setRequestHeader("Content-type",
									"application/x-www-form-urlencoded");
							xmlHttpNew.send(uriForward);
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
</script>


<fieldset class="tabstyle">
	<legend>
		<b><fmt:message key="CMN.EXISTINGDETAILS" bundle="${dcpsLables}"></fmt:message></b>
	</legend>
	<input type="hidden" id="txtDdoCode1" name="txtDdoCode1"
		value="${resValue.DDOCODE}" />
	<table width="100%" align="center" cellpadding="4" cellspacing="4">

		<tr>
			<td width="15%" align="left"><fmt:message
					key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>

			<td width="20%" align="left"><select name="listParentDept1"
				id="listParentDept1" style="width: 65%; display: block;"
				disabled="disabled">
					<option value="${resValue.parentDeptId}" selected="selected"><c:out
							value="${resValue.parentDeptDesc}"></c:out></option>
			</select></td>

			<td width="15%" align="left"><fmt:message
					key="CMN.CHANGEPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>

			<td width="20%" align="left"><input type="checkbox"
				name="cbChangeParentDept" id="cbChangeParentDept"
				value="ChangeParentDept" onclick="hideUnhideParentList();"
				${varDisabled} disabled="disabled"></td>

		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.CADRE"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbCadre1"
				id="cmbCadre1" style="width: 66%" onChange="populateGroup();"
				${varDisabled} disabled="disabled">
					<option value="${resValue.cadreId}" selected="selected"><c:out
							value="${resValue.cadreDesc}"></c:out></option>
			</select></td>
			<td width="15%" align="left"><fmt:message key="CMN.GROUP"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtGroup1"
				size="30" name="txtGroup1" value="${resValue.GroupNameMst}"
				readonly="readonly" disabled="disabled" /></td>

		</tr>
		<tr>
			<td width="15%" align="left"><fmt:message
					key="CMN.SUPERANNUATIONAGE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtSuperAnnuation1" size="30" name="txtSuperAnnuation1"
				value="${resValue.SuperAnnAgeMst}" readonly="readonly"
				disabled="disabled" /></td>

			<td width="15%" align="left"><fmt:message
					key="CMN.SUPERANNUATIONDATE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="hidden"
				name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> <input
				type="text" name="txtSuperAnnDate1" id="txtSuperAnnDate1"
				maxlength="10" disabled="disabled"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtSuperAnnDate1);compareDates(this,document.getElementById('currDate1'),'Date of retiring should be less than current date.','<');"
				value="${resValue.SuperAnnDateMst}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtSuperAnnDate1",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.DESIGNATION"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbDesignation1"
				id="cmbDesignation1" style="width: 65%" onchange="" ${varDisabled}
				disabled="disabled">
					<option value="${resValue.designationId}" selected="selected"><c:out
							value="${resValue.designationDesc}"></c:out></option>
			</select></td>
			<td width="15%" align="left"><fmt:message
					key="CMN.PAYCOMMISSION" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbPayCommission1"
				id="cmbPayCommission1" style="width: 65%;"
				onchange="GetScalePostfromDesg();" ${varDisabled}
				disabled="disabled">
					<option value="${resValue.payCommissionId}" selected="selected"><c:out
							value="${resValue.payCommissionDesc}"></c:out></option>
					<!-- 10001198219 -->
			</select></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PAYSCALE"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbPayScale1"
				id="cmbPayScale1" style="width: 65%" onChange="" ${varDisabled}
				disabled="disabled">
					<option value="${resValue.payScaleId}" selected="selected"><c:out
							value="${resValue.payScaleDesc}"></c:out></option>
			</select></td>
			<td width="15%" align="left"><fmt:message key="CMN.BASICPAY"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtBasicPay1" size="30" name="txtBasicPay1"
				value="${EMPVOMST.basicPay}" onblur="" ${varDisabled}
				disabled="disabled" /></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message
					key="CMN.DESIGNATIONFIRSTAPNTMNT" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				name="cmbFirstDesignation1" id="cmbFirstDesignation1" size="30"
				value="${EMPVOMST.firstDesignation}" disabled="disabled"
				${varDisabled} /></td>
			<td width="15%" align="left"><fmt:message
					key="CMN.DOJPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>

			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
				value="${EMPVOMST.appointmentDate}" var="dojParentDept" />

			<td width="20%" align="left"><input type="hidden"
				name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> <input
				type="text" name="txtJoinParentDeptDate1"
				id="txtJoinParentDeptDate1" maxlength="10" disabled="disabled"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtJoinParentDeptDate1);compareDates(this,document.getElementById('currDate1'),'Date of joining parent dept should be less than current date.','<');"
				value="${dojParentDept}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoinParentDeptDate1",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>

		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.DOJCADRE"
					bundle="${dcpsLables}"></fmt:message></td>

			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
				value="${EMPPAYROLLVOMST.currCadreJoiningDate}" var="dojCadre1" />

			<td width="20%" align="left"><input type="hidden"
				name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> <input
				type="text" name="txtJoinCadreDate1" id="txtJoinCadreDate1"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				disabled="disabled"
				onBlur="validateDate(txtJoinCadreDate1);compareDates(this,document.getElementById('currDate1'),'Date of joining cadre should be less than current date.','<');"
				value="${dojCadre1}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoinCadreDate1",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>

			<td width="15%" align="left"><fmt:message key="CMN.DOJPOST"
					bundle="${dcpsLables}"></fmt:message></td>

			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
				value="${EMPPAYROLLVOMST.currPostJoiningDate}" var="dojPost1" />

			<td width="20%" align="left"><input type="hidden"
				name="joinDate" id="joinDate" value="${resValue.lDtJoiDtLimit}" /><input
				type="text" name="txtJoinPostDate1" id="txtJoinPostDate1"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				disabled="disabled"
				onBlur="validateDate(txtJoinPostDate1);compareDates(this,document.getElementById('currDate1'),'Date of joining post should be less than current date.','<');"
				value="${dojPost1}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoinPostDate1",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message
					key="CMN.CURRENTOFFICE" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><select name="cmbCurrentOffice1"
				id="cmbCurrentOffice1" style="width: 66%"
				onChange="getOfficeDetails();" ${varDisabled} disabled="disabled">
					<option value="${resValue.ddoOfficeIdMst}" selected="selected"><c:out
							value="${resValue.ddoOfficeDescMst}"></c:out></option>
			</select></td>

			<td width="15%" align="left"><fmt:message
					key="CMN.OFFICEADDRESS" bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeAddress1"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeAddress1}" size="30"
				name="txtOfficeAddress1" ${varDisabled} disabled="disabled" /></td>
		</tr>

		<tr>
			<td width="15%" align="left">Telephone 1</td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeContactNo11" size="30" name="txtOfficeContactNo11"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeTelNo1}"
				onkeypress="digitFormat(this);"
				onblur="checkLength(txtOfficeContactNo11,'Office contact number');"
				${varDisabled} disabled="disabled" /></td>

			<td width="15%" align="left">Telephone 2</td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeMobile1" size="30" name="txtOfficeMobile1"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeTelNo2}"
				onkeypress="digitFormat(this);"
				onblur="checkLength(txtOfficeMobile1,'Office contact number');"
				${varDisabled} disabled="disabled" /></td>
		</tr>

		<tr>
			<td width="15%" align="left">Mobile</td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeContactNo21" size="30" name="txtOfficeContactNo21"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeFax}"
				onkeypress="digitFormat(this);"
				onblur="checkLength(txtOfficeContactNo21,'Office contact number');"
				${varDisabled} disabled="disabled" /></td>

			<td width="15%" align="left">Office Email Id</td>
			<td width="20%" align="left"><input type="text"
				name="txtOfficeEmailId1"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeEmail}" id="txtOfficeEmailId1"
				value="" size="30" disabled="disabled" /></td>
		</tr>

		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.CITYCLASS"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtOfficeCityClass1"
				value="${DDOOFFICEVOMST.dcpsDdoOfficeCityClass}" size="30"
				name="txtOfficeCityClass1" ${varDisabled} disabled="disabled" /></td>

			<td width="15%" align="left"><fmt:message key="CMN.REMARKS"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" rowspan="2"><textarea name='txtRemarks1'
					id="txtRemarks1" rows="4" cols="20" ${varDisabled}
					disabled="disabled">${EMPVOMST.remarks}</textarea></td>
		</tr>

		<tr>
			<td width="15%" align="left">&nbsp;</td>
			<td width="20%" align="left">&nbsp;</td>
		</tr>

	</table>
</fieldset>

<br />

<hdiits:form name="DCPSForm" id="DCPSForm" encType="multipart/form-data"
	validate="true" method="post">

	<fieldset class="tabstyle">
		<legend>
			<b><fmt:message key="CMN.CHANGEDETAILS" bundle="${dcpsLables}"></fmt:message></b>
		</legend>
		<input type="hidden" id="txtDdoCode" name="txtDdoCode"
			value="${resValue.DDOCODE}" /> <input type="hidden"
			id="lStrDesignation" name="lStrDesignation"
			value="${resValue.lStrDesignation}" /> <input type="hidden"
			id="lStrChangesType" name="lStrChangesType"
			value="${resValue.lStrChangesType}" /> <input type="hidden"
			id="hidDcpsId" value="${resValue.hidDcpsId}" /> <input type="hidden"
			id="hidEmpName" value="${resValue.hidEmpName}" /> <input
			type="hidden" id="hidBirthDate" value="${resValue.hidBirthDate}" />
		<input type="hidden" id="hidSevarthId"
			value="${resValue.hidSevarthId}" /> <input type="hidden"
			id="hidName" value="${resValue.hidName}" /> <input type="hidden"
			id="hidFromSearchEmp" value="${resValue.FromSearchEmp}" /> <input
			type="hidden" id="hidFromChangesOfficeElement"
			value="${resValue.FromChangesOfficeElement}" />

		<table width="100%" align="center" cellpadding="4" cellspacing="4">

			<tr>
				<td width="15%" align="left"><fmt:message
						key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><select name="listParentDept"
					id="listParentDept" style="width: 65%; display: block;"
					disabled="disabled">
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="ParentDept" items="${resValue.listParentDept}">
							<c:choose>
								<c:when test="${EMPVO.parentDept==ParentDept.id}">
									<option value="${ParentDept.id}" selected="selected"><c:out
											value="${ParentDept.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${ParentDept.id}"><c:out
											value="${ParentDept.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>

				<td width="15%" align="left"><fmt:message
						key="CMN.CHANGEPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><input type="checkbox"
					name="cbChangeParentDept" id="cbChangeParentDept"
					value="ChangeParentDept" onclick="hideUnhideParentList();"
					${varDisabled} disabled="disabled"></td>

			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message key="CMN.CADRE"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><fmt:formatDate dateStyle="full"
						pattern="dd/MM/yyyy" value="${resValue.empDOB}" var="empBirthDate" />
					<input type="hidden" id="txtBirthDate" name="txtBirthDate"
					value="${empBirthDate}" /> <select name="cmbCadre" id="cmbCadre"
					style="width: 66%" onChange="populateGroup();" ${varDisabled}>
						<c:forEach var="cadre" items="${CADRELIST}">
							<c:choose>
								<c:when test="${EMPVO.cadre == cadre.id}">
									<option value="${cadre.id}" selected="selected"><c:out
											value="${cadre.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${cadre.id}"><c:out
											value="${cadre.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>

				<td width="15%" align="left"><fmt:message key="CMN.GROUP"
						bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><input type="text" id="txtGroup"
					size="30" name="txtGroup" value="${resValue.GroupName}"
					readonly="readonly" /></td>


			</tr>
			<tr>

				<td width="15%" align="left"><fmt:message
						key="CMN.SUPERANNUATIONAGE" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><input type="text"
					id="txtSuperAnnuation" size="30" name="txtSuperAnnuation"
					value="${resValue.SuperAnnAge}" readonly="readonly" /></td>

				<td width="15%" align="left"><fmt:message
						key="CMN.SUPERANNUATIONDATE" bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><c:set var="superAnnDateJSP"
						value="${resValue.SuperAnnDate}" /> <input type="hidden"
					name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" />
					<input type="text" name="txtSuperAnnDate" id="txtSuperAnnDate"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onBlur="validateDate(txtSuperAnnDate);compareDates(this,document.getElementById('currDate1'),'Date of retiring should be less than current date.','<');"
					value="${superAnnDateJSP}" ${varDisabled} /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtSuperAnnDate",375,570)'
					style="cursor: pointer;" ${varImageDisabled} /></td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message key="CMN.DESIGNATION"
						bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><select name="cmbDesignation"
					id="cmbDesignation" style="width: 65%" onchange="" ${varDisabled}>
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="designation" items="${resValue.lLstDesignation}">
							<c:choose>
								<c:when test="${EMPVO!=null}">
									<c:choose>
										<c:when test="${EMPVO.designation == designation.id}">
											<option value="${designation.id}" selected="selected"><c:out
													value="${designation.desc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${designation.id}"><c:out
													value="${designation.desc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${designation.id}"><c:out
											value="${designation.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>

				</select></td>

				<td width="15%" align="left"><fmt:message
						key="CMN.PAYCOMMISSION" bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><select name="cmbPayCommission"
					id="cmbPayCommission" style="width: 65%;"
					onchange="popReasonsForSalaryChange();EnableSalChangesFields();GetScalePostfromDesg();"
					${varDisabled}>
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="PayCommission"
							items="${resValue.listPayCommission}">
							<c:choose>
								<c:when test="${EMPVO!=null}">
									<c:choose>
										<c:when
											test="${EMPVO.payCommission == PayCommission.lookupId}">
											<option value="${PayCommission.lookupId}" selected="selected"><c:out
													value="${PayCommission.lookupDesc}"></c:out></option>
										</c:when>
										<c:otherwise>
											<option value="${PayCommission.lookupId}"><c:out
													value="${PayCommission.lookupDesc}"></c:out></option>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<option value="${PayCommission.lookupId}"><c:out
											value="${PayCommission.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message key="CMN.PAYSCALE"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><select name="cmbPayScale"
					id="cmbPayScale" style="width: 65%"
					onChange="validateBasicPay();EnableSalChangesFields();"
					${varDisabled}>
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="PayScale" items="${resValue.PayScaleList}">
							<c:choose>
								<c:when test="${PayScale.id == EMPVO.payScale}">
									<option value="${PayScale.id}" selected="selected"><c:out
											value="${PayScale.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${PayScale.id}"><c:out
											value="${PayScale.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>

				<td width="15%" align="left"><fmt:message key="CMN.BASICPAY"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtBasicPay" size="30" name="txtBasicPay"
					value="${EMPVO.basicPay}" onblur="validateBasicPay();"
					${varDisabled} /> <label class="mandatoryindicator"
					id="labelForBasicPayForPSChange" style="display: none">*</label></td>

			</tr>

			<tr>

				<td width="15%" align="left"><fmt:message
						key="CMN.REASONSFORSALARYCHANGE" bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><select
					name="cmbReasonForSalChange" id="cmbReasonForSalChange"
					style="width: 65%" onChange="checkForOtherReason();" ${varDisabled}
					disabled="disabled">
						<option value="-1"><fmt:message
								key="COMMON.DROPDOWN.SELECT" /></option>
						<c:forEach var="reasonForSalChange"
							items="${resValue.lListReasonsForSalaryChangeMst}">
							<c:choose>
								<c:when
									test="${reasonForSalChange.lookupId == EMPVO.reasonForPSChange}">
									<option value="${reasonForSalChange.lookupId}"
										selected="selected"><c:out
											value="${reasonForSalChange.lookupDesc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${reasonForSalChange.lookupId}"><c:out
											value="${reasonForSalChange.lookupDesc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select> <label class="mandatoryindicator" id="labelForReasonForPSChange"
					style="display: none">*</label></td>
				<td width="15%" align="left"><fmt:message key="CMN.OTHERREASON"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><c:choose>
						<c:when test="${EMPVO != null}">
							<input type="text" id="txtOtherReason" size="30"
								name="txtOtherReason" value="${EMPVO.otherReasonForPSChange}"
								onblur="" ${varDisabled} readonly="readonly" />
						</c:when>
						<c:otherwise>
							<input type="text" id="txtOtherReason" size="30"
								name="txtOtherReason" value="" onblur="" ${varDisabled}
								readonly="readonly" />
						</c:otherwise>
					</c:choose> <label class="mandatoryindicator"
					id="labelForOtherReasonForPSChange" style="display: none">*</label>
				</td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message
						key="CMN.WITHEFFECTFROMDATE" bundle="${dcpsLables}"></fmt:message></td>
				<c:choose>
					<c:when test="${EMPVO != null}">
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
							value="${EMPVO.withEffectFromDate}" var="WithEffectFromDate" />
					</c:when>
					<c:otherwise>
						<c:set var="WithEffectFromDate" value="" />
					</c:otherwise>
				</c:choose>

				<td width="20%" align="left"><input type="text"
					name="txtWithEffectFromDate" id="txtWithEffectFromDate"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onBlur="validateDate(txtWithEffectFromDate);"
					value="${WithEffectFromDate}" ${varDisabled} readonly="readonly" />
					<img src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtWithEffectFromDate",375,570)'
					style="cursor: pointer;" ${varImageDisabled} /> <label
					class="mandatoryindicator" id="labelForWEFDateForPSChange"
					style="display: none">*</label></td>

				<td></td>
				<td></td>
			</tr>

			<tr></tr>
			<tr></tr>

			<tr>
				<td width="15%" align="left"><fmt:message
						key="CMN.DESIGNATIONFIRSTAPNTMNT" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><input type="text"
					name="cmbFirstDesignation" id="cmbFirstDesignation" size="30"
					value="${EMPVO.firstDesignation}" ${varDisabled} /> <span
					id="roleIndicatorRegion" style="display: none"> <img
						src="./images/busy-indicator.gif" /></span></td>

				<td width="15%" align="left"><fmt:message
						key="CMN.DOJPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>

				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
					value="${EMPVO.appointmentDate}" var="dojParentDept" />

				<td width="20%" align="left"><input type="hidden"
					name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" />
					<input type="text" name="txtJoinParentDeptDate"
					id="txtJoinParentDeptDate" maxlength="10"
					onkeypress="digitFormat(this);dateFormat(this);"
					onBlur="validateDate(txtJoinParentDeptDate);compareDates(this,document.getElementById('currDate1'),'Date of joining parent dept should be less than current date.','<');"
					value="${dojParentDept}" ${varDisabled} /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtJoinParentDeptDate",375,570)'
					style="cursor: pointer;" ${varImageDisabled} /></td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message key="CMN.DOJCADRE"
						bundle="${dcpsLables}"></fmt:message></td>

				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
					value="${EMPVO.currCadreJoiningDate}" var="dojCadre" />

				<td width="20%" align="left"><input type="hidden"
					name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" />
					<input type="text" name="txtJoinCadreDate" id="txtJoinCadreDate"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onBlur="validateDate(txtJoinCadreDate);compareDates(this,document.getElementById('currDate1'),'Date of joining cadre should be less than current date.','<');"
					value="${dojCadre}" ${varDisabled} /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtJoinCadreDate",375,570)'
					style="cursor: pointer;" ${varImageDisabled} /></td>

				<td width="15%" align="left"><fmt:message key="CMN.DOJPOST"
						bundle="${dcpsLables}"></fmt:message></td>

				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
					value="${EMPVO.currPostJoiningDate}" var="dojPost" />
				<td width="20%" align="left"><input type="hidden"
					name="joinDate" id="joinDate" value="${resValue.lDtJoiDtLimit}" /><input
					type="text" name="txtJoinPostDate" id="txtJoinPostDate"
					maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
					onBlur="validateDate(txtJoinPostDate);compareDates(this,document.getElementById('currDate1'),'Date of joining post should be less than current date.','<');"
					value="${dojPost}" ${varDisabled} /> <img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtJoinPostDate",375,570)'
					style="cursor: pointer;" ${varImageDisabled} /></td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message
						key="CMN.CURRENTOFFICE" bundle="${dcpsLables}"></fmt:message></td>

				<td width="20%" align="left"><select name="cmbCurrentOffice"
					id="cmbCurrentOffice" style="width: 66%"
					onChange="getOfficeDetails();" ${varDisabled}>
						<c:forEach var="officeVar" items="${OFFICELIST}">
							<c:choose>
								<c:when test="${officeVar.id == resValue.ddoOfficeId}">
									<option value="${officeVar.id}" selected="selected"><c:out
											value="${officeVar.desc}"></c:out></option>
								</c:when>
								<c:otherwise>
									<option value="${officeVar.id}"><c:out
											value="${officeVar.desc}"></c:out></option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>

				<td width="15%" align="left"><fmt:message
						key="CMN.OFFICEADDRESS" bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtOfficeAddress" value="${DDOOFFICEVO.dcpsDdoOfficeAddress1}"
					size="30" name="txtOfficeAddress" ${varDisabled}
					readonly="readonly" /></td>

			</tr>

			<tr>
				<td width="15%" align="left">Telephone 1</td>
				<td width="20%" align="left"><input type="text"
					id="txtOfficeContactNo1" size="30" name="txtOfficeContactNo1"
					value="${DDOOFFICEVO.dcpsDdoOfficeTelNo1}"
					onkeypress="digitFormat(this);"
					onblur="checkLength(txtOfficeContactNo1,'Office contact number');"
					${varDisabled} readonly="readonly" /></td>

				<td width="15%" align="left">Telephone 2</td>
				<td width="20%" align="left"><input type="text"
					id="txtOfficeMobile" size="30" name="txtOfficeMobile"
					value="${DDOOFFICEVO.dcpsDdoOfficeTelNo2}"
					onkeypress="digitFormat(this);"
					onblur="checkLength(txtOfficeMobile,'Office contact number');"
					${varDisabled} readonly="readonly" /></td>
			</tr>

			<tr>
				<td width="15%" align="left">Mobile</td>
				<td width="20%" align="left"><input type="text"
					id="txtOfficeContactNo2" size="30" name="txtOfficeContactNo2"
					value="${DDOOFFICEVO.dcpsDdoOfficeFax}"
					onkeypress="digitFormat(this);"
					onblur="checkLength(txtOfficeContactNo2,'Office contact number');"
					${varDisabled} readonly="readonly" /></td>

				<td width="15%" align="left">Office Email Id</td>
				<td width="20%" align="left"><input type="text"
					name="txtOfficeEmailId" value="${DDOOFFICEVO.dcpsDdoOfficeEmail}"
					id="txtOfficeEmailId" size="30" readonly="readonly" /></td>
			</tr>

			<tr>
				<td width="15%" align="left"><fmt:message key="CMN.CITYCLASS"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtOfficeCityClass"
					value="${DDOOFFICEVO.dcpsDdoOfficeCityClass}" size="30"
					name="txtOfficeCityClass" ${varDisabled} readonly="readonly" /></td>

				<td width="15%" align="left"><fmt:message key="CMN.REMARKS"
						bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" rowspan="2"><textarea name='txtRemarks'
						id="txtRemarks" rows="4" cols="20" ${varDisabled}>${EMPVO.remarks}</textarea></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td width="15%" align="left">&nbsp;</td>
				<td width="20%" align="left">&nbsp;</td>
			</tr>

		</table>
	</fieldset>

	<br />
	<fieldset class="tabstyle">
		<legend>
			<b><fmt:message key="CMN.AUTHORITYDETAILS" bundle="${dcpsLables}"></fmt:message></b>
		</legend>
		<table width="50%" align="left" cellpadding="4" cellspacing="4">
			<tr>
				<td width="15%" align="left">Authority Letter No.</td>
				<td width="20%" align="left" colspan="3"><input type="text"
					id="txtAuthorityLetterNo" style="text-transform: uppercase"
					size="30" name="txtAuthorityLetterNo"
					value="${CHANGESHISTORYVO.letterNo}"
					onblur="isIntegerOrNot(document.getElementById('txtAuthorityLetterNo'),'Authority Letter No should be Number Only')" />
					<label class="mandatoryindicator">*</label></td>
			</tr>
			<tr>
				<td width="15%" align="left">Letter Date</td>
				<c:if test="${resValue.lObjHstDcpsChanges != null}">
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
						value="${CHANGESHISTORYVO.letterDate}" var="letterDate" />
				</c:if>
				<td width="20%" align="left" colspan="3"><input type="text"
					id="txtAuthorityLetterDate" style="text-transform: uppercase"
					size="30" onkeypress="digitFormat(this);dateFormat(this);"
					name="txtAuthorityLetterDate" value="${letterDate}" /><img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtAuthorityLetterDate", 375, 570)'
					style="cursor: pointer;" /> <label class="mandatoryindicator">*</label></td>
			</tr>

		</table>

	</fieldset>
	<br />

	<input type="hidden" id="User" name="User" value="${resValue.UserType}">
	<c:if test="${resValue.UserType == 'DDO'}">
		<table width="100%">
			<tr>
				<td width="20%" align="left" style="padding-left: 5px"><fmt:message
						key="CMN.REMARKS" bundle="${dcpsLables}"></fmt:message></td>
				<td align="left" width="80%" style="padding-left: 23px"><input
					type="text" name="sentBackRemarks" id="sentBackRemarks" value=""
					size="100" /></td>
			</tr>
		</table>
	</c:if>

	<input type="hidden" id="ForwardToPost" name="ForwardToPost"
		value="${resValue.ForwardToPost}" />

	<div align="right">
		<c:choose>
			<c:when test="${resValue.dcpsChangesId != null}">
				<input type="hidden" id="dcpsHstChangesId"
					value="${resValue.dcpsChangesId}" />
				<input type="hidden" id="lStrDesignationDraft"
					value="${resValue.lStrDesignationDraft}" />
			</c:when>
			<c:otherwise>
				<input type="hidden" id="dcpsHstChangesId" value="" />
				<input type="hidden" id="lStrDesignationDraft" value="" />
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${resValue.UserType == 'DDOAsst'}">
				<c:choose>
					<c:when
						test="${(resValue.hidDcpsId != '' || (resValue.hidEmpName != '' && resValue.hidBirthDate != '') || (resValue.hidSevarthId != '') || (resValue.hidName != '')) && resValue.FromSearchEmp != 'NO'}">
						<hdiits:button name="btnBack" id="btnBack" type="button"
							captionid="BTN.BACK" bundle="${dcpsLables}"
							onclick="GoBackToSearchPage();" />
					</c:when>
					<c:otherwise>
						<hdiits:button name="btnBack" id="btnBack" type="button"
							captionid="BTN.BACK" bundle="${dcpsLables}"
							onclick="ReturnToChangesInDDOAsst();" />
					</c:otherwise>
				</c:choose>

				<hdiits:button name="btnUpdatePersonalDtls"
					id="btnUpdatePersonalDtls" type="button"
					captionid="BTN.SAVEASDRAFT" bundle="${dcpsLables}"
					onclick="updateOrForwardOfficeDetails('${EMPVOMST.dcpsEmpId}',1);" />
				<input type="hidden" id="ForwardToPost" name="ForwardToPost"
					value="${UserList[0]}" />
				<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally"
					type="button" captionid="BTN.FORWARD" bundle="${dcpsLables}"
					onclick="updateOrForwardOfficeDetails('${EMPVOMST.dcpsEmpId}',2);" />
			</c:when>
			<c:otherwise>
				<hdiits:button name="btnBack" id="btnBack" type="button"
					captionid="BTN.BACK" bundle="${dcpsLables}"
					onclick="ReturnfromChanges();" />
				<hdiits:button name="btnApprovePersonalDtls"
					id="btnApprovePersonalDtls" type="button" captionid="BTN.APPROVE"
					bundle="${dcpsLables}" onclick="approveOfficeDetails();" />
				<hdiits:button name="BTN.REJECT" id="btnRejectPersonalDtls"
					type="button" captionid="BTN.REJECT" bundle="${dcpsLables}"
					onclick="rejectOfficeDetails();" />
			</c:otherwise>
		</c:choose>
	</div>

</hdiits:form>

<ajax:autocomplete source="cmbFirstDesignation"
	target="cmbFirstDesignation"
	baseUrl="ifms.htm?actionFlag=getFirstDesignationForAutoComplete"
	parameters="searchKey={cmbFirstDesignation}" className="autocomplete"
	minimumCharacters="2" indicator="roleIndicatorRegion" />
