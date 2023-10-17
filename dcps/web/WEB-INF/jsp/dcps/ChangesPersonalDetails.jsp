<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script  type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"  src="script/common/pramukhlib.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/dcps/ChangePersonalDetails.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVOMST" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPPAYROLLVOMST" value="${resValue.lObjRltDcpsPayrollEmp}"></c:set>

<c:set var="EMPVO" value="${resValue.lObjHstDcpsPersonalChanges}"></c:set>
<c:set var="empGender" value="${resValue.empGender}"></c:set>

<c:if test="${resValue.UserType == 'DDO'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>

<c:set var="CHANGESVO" value="${resValue.lObjHstDcpsChanges}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<script>
function getChangedFieldsUrl()
{
	var urlConstructed ="" ;
	
	var txtUIDNo1Below = document.getElementById("txtUIDNo1").value.trim() ;
	var txtUIDNo2Below = document.getElementById("txtUIDNo2").value.trim() ;
	var txtUIDNo3Below = document.getElementById("txtUIDNo3").value.trim() ;
	var txtUIDNo = txtUIDNo1Below.toString() + txtUIDNo2Below.toString() + txtUIDNo3Below.toString() ;
	var txtEIDNo = document.getElementById("txtEIDNo").value.trim() ;
	var txtPinCode = document.getElementById("txtPinCode").value.trim() ;
	var txtName = document.getElementById("txtName").value.trim() ;
	var txtNameInDevanagari = document.getElementById("txtNameInDevanagari").value.trim() ;

	var radioGenderArr = document.DCPSPersonalChangesForm.radioGender ;
	var radioGender="";
	for (var i=0; i<radioGenderArr.length; i++)
	{
		  if (radioGenderArr[i].checked == true)
		  {
			  radioGender = radioGenderArr[i].value ;
		  }
	}	

	var radioHandicArr = document.DCPSPersonalChangesForm.radioHandic ;
	var radioHandic="";
	for (i=0; i<radioHandicArr.length; i++)
	{
		  if (radioHandicArr[i].checked == true)
		  {
			  radioHandic = radioHandicArr[i].value ;
		  }
	}

	
	var txtBirthDate = document.getElementById("txtBirthDate").value.trim() ;
	var txtJoiningDate = document.getElementById("txtJoiningDate").value.trim() ;
	var cmbSalutation = document.getElementById("cmbSalutation").value.trim() ;
	var txtFatherOrHusband = document.getElementById("txtFatherOrHusband").value.trim() ;
	var txtAddressBuilding = document.getElementById("txtAddressBuilding").value.trim() ;
	var txtAddressStreet = document.getElementById("txtAddressStreet").value.trim() ;
	var txtLandmark = document.getElementById("txtLandmark").value.trim() ;
	var txtLocality = document.getElementById("txtLocality").value.trim() ;
	var txtDistrict = document.getElementById("txtDistrict").value.trim() ;
	var cmbState = document.getElementById("cmbState").value.trim() ;
	var txtPANNo = document.getElementById("txtPANNo").value.trim() ;
	var txtContactTelNo =  document.getElementById("txtContactTelNo").value.trim() ;
	var txtCellNo =  document.getElementById("txtCellNo").value.trim() ;
	var txtEmailId =  document.getElementById("txtEmailId").value.trim();

	var txtUIDNo11 = document.getElementById("txtUIDNo11").value.trim() ;
	var txtUIDNo21 = document.getElementById("txtUIDNo21").value.trim() ;
	var txtUIDNo31 = document.getElementById("txtUIDNo31").value.trim() ;
	var txtUIDNo1 = txtUIDNo11.toString() + txtUIDNo21.toString() + txtUIDNo31.toString() ;
	var txtEIDNo1 = document.getElementById("txtEIDNo1").value.trim() ;
	var txtPinCode1 = document.getElementById("txtPinCode1").value.trim() ;
	var txtName1 = document.getElementById("txtName1").value.trim() ;
	var txtNameInDevanagari1 = document.getElementById("txtNameInDevanagari1").value.trim() ;

	var radioGenderArr1 = document.DCPSPersonalChangesForm.radioGender1 ;
	var radioGender1;
	for (i=0; i<radioGenderArr1.length; i++)
	{
		  if (radioGenderArr1[i].checked == true)
		  {
			  radioGender1 = radioGenderArr1[i].value ;
		  }
	}	

	var radioHandicArr1 = document.DCPSPersonalChangesForm.radioHandic1 ;
	var radioHandic1;
	for (i=0; i<radioHandicArr1.length; i++)
	{
		  if (radioHandicArr1[i].checked == true)
		  {
			  radioHandic1 = radioHandicArr1[i].value ;
		  }
	}
	
	var txtBirthDate1 = document.getElementById("txtBirthDate1").value.trim() ;
	var txtJoiningDate1 = document.getElementById("txtJoiningDate1").value.trim() ;
	var cmbSalutation1 = document.getElementById("cmbSalutation1").value.trim() ;
	var txtFatherOrHusband1 = document.getElementById("txtFatherOrHusband1").value.trim() ;
	var txtAddressBuilding1 = document.getElementById("txtAddressBuilding1").value.trim() ;
	var txtAddressStreet1 = document.getElementById("txtAddressStreet1").value.trim() ;
	var txtLandmark1 = document.getElementById("txtLandmark1").value.trim() ;
	var txtLocality1 = document.getElementById("txtLocality1").value.trim() ;
	var txtDistrict1 = document.getElementById("txtDistrict1").value.trim() ;
	var cmbState1 = document.getElementById("cmbState1").value.trim() ;
	var txtPANNo1 = document.getElementById("txtPANNo1").value.trim() ;
	var txtContactTelNo1 =  document.getElementById("txtContactTelNo1").value.trim() ;
	var txtCellNo1 =  document.getElementById("txtCellNo1").value.trim() ;
	var txtEmailId1 =  document.getElementById("txtEmailId1").value.trim() ;

	urlConstructed = urlConstructed + "&change="+ "PersonalDetails" + "&changeDetails="  ;

	var UIDNo='<fmt:message key="CMN.UIDNO" bundle="${dcpsLables}"></fmt:message>';
	var EIDNo='<fmt:message key="CMN.EIDNO" bundle="${dcpsLables}"></fmt:message>';
	var PINCode='<fmt:message key="CMN.PINCODE" bundle="${dcpsLables}"></fmt:message>';
	var Name='<fmt:message key="CMN.FULLNAME" bundle="${dcpsLables}"></fmt:message>';
	var NameInDev = '<fmt:message key="CMN.FULLNAMEDEV" bundle="${dcpsLables}"></fmt:message>'
	var gender='<fmt:message key="CMN.GENDER" bundle="${dcpsLables}"></fmt:message>';
	var handicapped = '<fmt:message key="CMN.HANDICAPPED" bundle="${dcpsLables}"></fmt:message>';
	var dob =   '<fmt:message key="CMN.DOB" bundle="${dcpsLables}"></fmt:message>';
	var doj =   '<fmt:message key="CMN.DOJ" bundle="${dcpsLables}"></fmt:message>';
	var salutation =  '<fmt:message key="CMN.SALUTATION" bundle="${dcpsLables}"></fmt:message>';
	var fatherOrhusband = '<fmt:message key="CMN.FATHERHUSBANDNAME" bundle="${dcpsLables}"></fmt:message>';
	var addressBuilding = '<fmt:message key="CMN.ADDRESSBUILDING" bundle="${dcpsLables}"></fmt:message>';
	var addressStreet =  '<fmt:message key="CMN.ADDRESSSTREET" bundle="${dcpsLables}"></fmt:message>';
	var state =  '<fmt:message key="CMN.STATE" bundle="${dcpsLables}"></fmt:message>';
	var PANNo =  '<fmt:message key="CMN.PANNO" bundle="${dcpsLables}"></fmt:message>';
	var contactTelNo = '<fmt:message key="CMN.CONTACTTELNO" bundle="${dcpsLables}"></fmt:message>';
	var cellNo = '<fmt:message key="CMN.CELLNO" bundle="${dcpsLables}"></fmt:message>';
	var emailId = '<fmt:message key="CMN.EMAIL" bundle="${dcpsLables}"></fmt:message>';
	var landMark = '<fmt:message key="CMN.LANDMARK" bundle="${dcpsLables}"></fmt:message>';
	var locality =  '<fmt:message key="CMN.LOCALITY" bundle="${dcpsLables}"></fmt:message>';
	var district = '<fmt:message key="CMN.DISTRICT" bundle="${dcpsLables}"></fmt:message>';


	if(txtUIDNo != txtUIDNo1 && txtUIDNo!="")
	{
		urlConstructed = urlConstructed + UIDNo + "," + txtUIDNo + "," + txtUIDNo1 + "~" ;
	}
	if(txtEIDNo != txtEIDNo1 && txtEIDNo!="")
	{
		urlConstructed = urlConstructed + EIDNo + "," + document.getElementById("txtEIDNo").value.trim() + "," + document.getElementById("txtEIDNo1").value.trim() + "~" ;
	}
	if(txtPinCode != txtPinCode1 && txtPinCode!="")
	{
		urlConstructed = urlConstructed + PINCode + "," + document.getElementById("txtPinCode").value.trim() + "," + document.getElementById("txtPinCode1").value.trim() + "~" ;
	}
	if(txtName != txtName1 && txtName!="")
	{
		urlConstructed = urlConstructed + Name + "," + document.getElementById("txtName").value.trim() + "," + document.getElementById("txtName1").value.trim() + "~" ;
	}
	if(txtNameInDevanagari != txtNameInDevanagari1 && txtNameInDevanagari!="")
	{
		urlConstructed = urlConstructed + NameInDev + "," + document.getElementById("txtNameInDevanagari").value.trim() + "," + document.getElementById("txtNameInDevanagari1").value.trim() + "~" ;
	} 
	if(radioGender != radioGender1 && radioGender!="")
	{
		urlConstructed = urlConstructed + gender + "," +  radioGender + "," + radioGender1 + "~" ;
	}
	if(txtBirthDate != txtBirthDate1 && txtBirthDate!="")
	{
		urlConstructed = urlConstructed + dob + "," + document.getElementById("txtBirthDate").value.trim() + "," + document.getElementById("txtBirthDate1").value.trim() + "~" ;
	}
	if(txtJoiningDate != txtJoiningDate1 && txtJoiningDate!="")
	{
		urlConstructed = urlConstructed + doj + "," + document.getElementById("txtJoiningDate").value.trim() + "," + document.getElementById("txtJoiningDate1").value.trim() + "~" ;
	}
	if(radioHandic != radioHandic1 && radioHandic!="")
	{
		urlConstructed = urlConstructed + handicapped + "," +  radioHandic + "," + radioHandic1 + "~" ;
	}
	if(cmbSalutation != cmbSalutation1 && cmbSalutation!=-1)
	{
		urlConstructed = urlConstructed + salutation + "," + document.getElementById("cmbSalutation").value.trim() + "," + document.getElementById("cmbSalutation1").value.trim() + "~" ;
	}
	if(txtFatherOrHusband != txtFatherOrHusband1 && txtFatherOrHusband!="")
	{
		urlConstructed = urlConstructed + fatherOrhusband + "," +  document.getElementById("txtFatherOrHusband").value.trim() + "," + document.getElementById("txtFatherOrHusband1").value.trim() + "~" ;
	}
	if(txtAddressBuilding != txtAddressBuilding1 && txtAddressBuilding!="")
	{
		urlConstructed = urlConstructed + addressBuilding + "," + document.getElementById("txtAddressBuilding").value.trim() + "," + document.getElementById("txtAddressBuilding1").value.trim() + "~" ;
	}
	if(txtAddressStreet != txtAddressStreet1 && txtAddressStreet!="")
	{
		urlConstructed = urlConstructed + addressStreet+ "," + document.getElementById("txtAddressStreet").value.trim() + "," + document.getElementById("txtAddressStreet1").value.trim() + "~" ;
	}
	if(txtLocality != txtLocality1 && txtLocality!="")
	{
		urlConstructed = urlConstructed + locality+ "," + document.getElementById("txtLocality").value.trim() + "," + document.getElementById("txtLocality1").value.trim() + "~" ;
	}
	if(txtLandmark != txtLandmark1 && txtLandmark!="")
	{
		urlConstructed = urlConstructed + landMark + "," + document.getElementById("txtLandmark").value.trim() + "," + document.getElementById("txtLandmark1").value.trim() + "~" ;
	}
	if(txtDistrict != txtDistrict1 && txtDistrict!="")
	{
		urlConstructed = urlConstructed + district+ "," + document.getElementById("txtDistrict").value.trim() + "," + document.getElementById("txtDistrict1").value.trim() + "~" ;
	}
	if(cmbState != cmbState1 && cmbState!=-1)
	{
		urlConstructed = urlConstructed + state + "," +  document.getElementById("cmbState").value.trim() + "," + document.getElementById("cmbState1").value.trim() + "~" ;
	}
	if(txtPANNo != txtPANNo1 && txtPANNo!="")
	{
		urlConstructed = urlConstructed + PANNo + "," + document.getElementById("txtPANNo").value.trim() + "," + document.getElementById("txtPANNo1").value.trim() + "~" ;
	}
	if(txtContactTelNo != txtContactTelNo1 && txtContactTelNo!="")
	{
		urlConstructed = urlConstructed + contactTelNo + "," + document.getElementById("txtContactTelNo").value.trim() + "," + document.getElementById("txtContactTelNo1").value.trim() + "~" ;
	}
	if(txtCellNo != txtCellNo1 && txtCellNo!="")
	{
		urlConstructed = urlConstructed + cellNo + "," +  document.getElementById("txtCellNo").value.trim() + "," + document.getElementById("txtCellNo1").value.trim() + "~" ;
	}
	if(txtEmailId != txtEmailId1 && txtEmailId!="")
	{
		urlConstructed = urlConstructed + emailId + "," + document.getElementById("txtEmailId").value.trim() + "," + document.getElementById("txtEmailId1").value.trim() + "~" ;
	}
	return urlConstructed ;
}
function approvePersonalDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var User =document.getElementById("User").value.trim() ; 
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
	   hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=approveChangesByDDO';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId ;
	
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].textContent;

						if (test_Id) {
							alert('The Changes are Approved.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}

function rejectPersonalDetails()
{
	showProgressbar();
	var dcpsChangesId = document.getElementById("dcpsHstChangesId").value.trim();
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
	var sentBackRemarks = document.getElementById("sentBackRemarks").value.trim();
	var User =document.getElementById("User").value.trim() ; 

	if(sentBackRemarks == "")
	{
		alert('Please Enter Remarks.');
		hideProgressbar();
		return false ;
	}
	 
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	{
		hideProgressbar();
	   return;
	}

	var uri = 'ifms.htm?actionFlag=rejectChangesToDDOAsst';
	var url = uri + '&dcpsChangesId='+ dcpsChangesId + '&sentBackRemarks='+sentBackRemarks ;
	//alert(url);
	xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

						if (test_Id) {
							alert('The changes are rejected and sent back to DDO Assistant.');
							self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
						}
					}
				}
		} ;

	 xmlHttp.open("POST",uri,false);
	 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlHttp.send(url);				
}

function validatePersonalChangeDtls(flag)
{
	var changeFlag = false ;
	var changeFlagMandatoryFields = false ;
	
	var txtUIDNo1Below = document.getElementById("txtUIDNo1").value.trim() ;
	var txtUIDNo2Below = document.getElementById("txtUIDNo2").value.trim() ;
	var txtUIDNo3Below = document.getElementById("txtUIDNo3").value.trim() ;
	var txtUIDNo = txtUIDNo1Below.toString() + txtUIDNo2Below.toString() + txtUIDNo3Below.toString() ;
	var txtEIDNo = document.getElementById("txtEIDNo").value.trim() ;
	var txtPinCode = document.getElementById("txtPinCode").value.trim() ;
	var txtName = document.getElementById("txtName").value.trim() ;
	var txtNameInDevanagari = document.getElementById("txtNameInDevanagari").value.trim() ;

	var radioGenderArr = document.DCPSPersonalChangesForm.radioGender ;
	var radioGender="";

	for (var i=0; i<radioGenderArr.length; i++)
	{
		  if (radioGenderArr[i].checked == true)
		  {
			  radioGender = radioGenderArr[i].value ;
		  }
	}	

	var radioHandicArr = document.DCPSPersonalChangesForm.radioHandic ;
	var radioHandic="";
	for (i=0; i<radioHandicArr.length; i++)
	{
		  if (radioHandicArr[i].checked == true)
		  {
			  radioHandic = radioHandicArr[i].value ;
		  }
	}

	var txtBirthDate = document.getElementById("txtBirthDate").value.trim() ;
	var txtJoiningDate = document.getElementById("txtJoiningDate").value.trim() ;
	var cmbSalutation = document.getElementById("cmbSalutation").value.trim() ;
	var txtFatherOrHusband = document.getElementById("txtFatherOrHusband").value.trim() ;
	var txtAddressBuilding = document.getElementById("txtAddressBuilding").value.trim() ;
	var txtAddressStreet = document.getElementById("txtAddressStreet").value.trim() ;
	var txtLandmark = document.getElementById("txtLandmark").value.trim() ;
	var txtLocality = document.getElementById("txtLocality").value.trim() ;
	var txtDistrict = document.getElementById("txtDistrict").value.trim() ;
	var cmbState = document.getElementById("cmbState").value.trim() ;
	var txtPANNo = document.getElementById("txtPANNo").value.trim() ;

	var txtUIDNo11 = document.getElementById("txtUIDNo11").value.trim() ;
	var txtUIDNo21 = document.getElementById("txtUIDNo21").value.trim() ;
	var txtUIDNo31 = document.getElementById("txtUIDNo31").value.trim() ;
	var txtUIDNo1 = txtUIDNo11.toString() + txtUIDNo21.toString() + txtUIDNo31.toString() ;
	var txtEIDNo1 =document.getElementById("txtEIDNo1").value.trim() ;
	var txtPinCode1 = document.getElementById("txtPinCode1").value.trim() ;
	var txtName1 = document.getElementById("txtName1").value.trim() ;
	var txtNameInDevanagari1 = document.getElementById("txtNameInDevanagari1").value.trim() ;

	var radioGenderArr1 = document.DCPSPersonalChangesForm.radioGender1 ;
	var radioGender1;
	for (i=0; i<radioGenderArr1.length; i++)
	{
		  if (radioGenderArr1[i].checked == true)
		  {
			  radioGender1 = radioGenderArr1[i].value ;
		  }
	}	

	var radioHandicArr1 = document.DCPSPersonalChangesForm.radioHandic1 ;
	var radioHandic1;
	for (i=0; i<radioHandicArr1.length; i++)
	{
		  if (radioHandicArr1[i].checked == true)
		  {
			  radioHandic1 = radioHandicArr1[i].value ;
		  }
	}
	
	var txtBirthDate1 = document.getElementById("txtBirthDate1").value.trim() ;
	var txtJoiningDate1 = document.getElementById("txtJoiningDate1").value.trim() ;
	var cmbSalutation1 = document.getElementById("cmbSalutation1").value.trim() ;
	var txtFatherOrHusband1 = document.getElementById("txtFatherOrHusband1").value.trim() ;
	var txtAddressBuilding1 = document.getElementById("txtAddressBuilding1").value.trim() ;
	var txtAddressStreet1 = document.getElementById("txtAddressStreet1").value.trim() ;
	var txtLandmark1 = document.getElementById("txtLandmark1").value.trim() ;
	var txtLocality1 = document.getElementById("txtLocality1").value.trim() ;
	var txtDistrict1 = document.getElementById("txtDistrict1").value.trim() ;
	var cmbState1 = document.getElementById("cmbState1").value.trim() ;
	var txtPANNo1 = document.getElementById("txtPANNo1").value.trim() ;

	var txtContactTelNo1 =  document.getElementById("txtContactTelNo1").value.trim() ;
	var txtCellNo1 =  document.getElementById("txtCellNo1").value.trim() ;
	var txtEmailId1 =  document.getElementById("txtEmailId1").value.trim() ;

	var txtContactTelNo =  document.getElementById("txtContactTelNo").value.trim() ;
	var txtCellNo =  document.getElementById("txtCellNo").value.trim() ;
	var txtEmailId =  document.getElementById("txtEmailId").value.trim() ;
	
	var txtAuthorityLetterNo = document.getElementById("txtAuthorityLetterNo").value.trim() ;
	var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value.trim() ;

	if(txtName != txtName1 || radioGender != radioGender1 || txtBirthDate!= txtBirthDate1 || txtJoiningDate!= txtJoiningDate1 || cmbSalutation!=cmbSalutation1 
			 || txtFatherOrHusband!=txtFatherOrHusband1  || txtEmailId1!=txtEmailId || txtNameInDevanagari!=txtNameInDevanagari1 
			 || txtAddressBuilding1!=txtAddressBuilding || txtAddressStreet1!=txtAddressStreet 
			 || txtLandmark!=txtLandmark1 || txtLocality!=txtLocality1 || txtDistrict!=txtDistrict1 || cmbState!=cmbState1 || txtPANNo!=txtPANNo1
			 || txtUIDNo1!=txtUIDNo || txtEIDNo1!=txtEIDNo || txtPinCode1!=txtPinCode || radioHandic1!=radioHandic || txtContactTelNo1!=txtContactTelNo || txtCellNo1!=txtCellNo)
	{
		//Lines added
		if(txtName!="" || radioGender!="" || txtBirthDate!="" || txtJoiningDate!="" || cmbSalutation!=-1 || txtFatherOrHusband!="" || txtEmailId!="" || txtNameInDevanagari!="" ||
			txtAddressBuilding!="" || txtAddressStreet!="" || txtLandmark!="" || txtLocality!="" || txtDistrict!="" || cmbState!=-1 || txtPinCode!="" ||
			txtPANNo!="" || txtUIDNo!="" || txtEIDNo!="" || txtPinCode!="" || radioHandic!="" || txtContactTelNo!="" || txtCellNo!="" )
		{
			changeFlag = true ;
		}
	}

	if(txtName != txtName1 || radioGender != radioGender1 || txtBirthDate!= txtBirthDate1 || txtJoiningDate!= txtJoiningDate1 || cmbSalutation!=cmbSalutation1 || txtNameInDevanagari!=txtNameInDevanagari1
			 || txtFatherOrHusband!=txtFatherOrHusband1 || txtUIDNo1!=txtUIDNo || txtEIDNo1!=txtEIDNo || radioHandic1!=radioHandic)
	{
		if(txtName != "" || radioGender != "" || txtBirthDate!= "" || txtJoiningDate!= "" || cmbSalutation!=-1 || txtNameInDevanagari!=""
				 || txtFatherOrHusband!="" || txtUIDNo!="" || txtEIDNo!="" || radioHandic!="")
		{
			changeFlagMandatoryFields = true ;
		}
	}

	if(changeFlag==false)
	{
		alert('You have not changed any details');
		return false;
	}

	if(changeFlag && flag==2)
	{
		if(changeFlagMandatoryFields)
		{
			if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "")
			{
				alert('Please fill the Authority Details.') ;
				return false;
			}
		}
	}

	return true ;
}
function updateOrForwardPersonalDetails(emp_id,flag)
{	
		showProgressbar();
		if(!validatePersonalChangeDtls(flag))
		{
			hideProgressbar();
			return false ;
		}
		
	
		var empId= emp_id;
		var saveOrForwardFlag=flag;
		var designationId = document.getElementById("lStrDesignation").value.trim();
		var typeOfChanges = document.getElementById("lStrChangesType").value.trim();

		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
		   hideProgressbar();
		   return;
		}

		 var uri = 'ifms.htm?actionFlag=updatePersonalDtls';
		 var url = runForm(0); 
		 var urlChangedFields = getChangedFieldsUrl();
		 url = uri + url + urlChangedFields ;   
		 url = url + "&empId="+empId;
		 var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value.trim();
		 var designationIdDraft = document.getElementById("lStrDesignationDraft").value.trim();
		 url = url + "&dcpsHstChangesId="+dcpsHstChangesId; 

		 //alert(url);

		 xmlHttp.onreadystatechange = function()
			{
				if (xmlHttp.readyState == 4) {
					if (xmlHttp.status == 200) {
					// 	alert("testing--"); 
						hideProgressbar();
						XMLDoc = xmlHttp.responseXML.documentElement;
						///alert("testing--"+XMLDoc);
						var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
							var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
							///alert("testing test_Id--"+test_Id);
							var dcpsChangesId =  XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
						///	alert("testing dcpsChangesId--"+dcpsChangesId);
							if (test_Id) {
								var User =document.getElementById("User").value.trim() ; 
								if(saveOrForwardFlag==1)
								{
									if(dcpsHstChangesId == "")
									{
										alert('Personal Details have been successfully changed and saved.');
										self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
									}
									else
									{
										alert('Personal Details have been successfully changed and saved.');
										self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
									}
								}
								if(saveOrForwardFlag==2)
								{
									var ForwardToPost =  document.getElementById("ForwardToPost").value.trim();
									//alert(ForwardToPost);
									var uriForward ;
										if(dcpsHstChangesId == "")
										{
										uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsChangesId+"&ForwardToPost="+ForwardToPost;
										}
										else
										{
										uriForward = "ifms.htm?actionFlag=dcpsFwdChanges&dcpsChangesId="+dcpsHstChangesId+"&ForwardToPost="+ForwardToPost;
										}
									//alert(uriForward);
									xmlHttpNew=GetXmlHttpObject();
									
									if (xmlHttpNew==null)
									{
										alert ("Your browser does not support AJAX!");
										return;
									} 
									xmlHttpNew.onreadystatechange= function()
									{
										if (xmlHttpNew.readyState == 4) {
											if (xmlHttpNew.status == 200) {
												
												XMLDocNew = xmlHttpNew.responseXML.documentElement;
												var XmlHiddenValuesNew = XMLDocNew.getElementsByTagName('XMLDOC');
												var success_flag = XmlHiddenValuesNew[0].childNodes[0].firstChild.nodeValue;
												if(success_flag=='true')
												{
													if(dcpsHstChangesId == "")
													{
														alert("Changes are forwarded successfully");
														self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
													}
													else
													{
														alert("Changes are forwarded successfully");
														self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
													}
												}
											  }
											}
									};
									xmlHttpNew.open("POST",uriForward,false);
									xmlHttpNew.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
									xmlHttpNew.send(uriForward);
								}
							}
						}
					}
			};
		   xmlHttp.open("POST",uri,false);
		   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		   xmlHttp.send(url);
}

function chkPANalreadyExists()
{
	///alert('Inside chkPANalreadyExists');
	  var panNo = document.getElementById("txtPANNo").value.trim();
	  var dcpsEmpId = ${EMPVOMST.dcpsEmpId};
	  
		var uri = 'ifms.htm?actionFlag=chkPANalreadyExists'; 
		var url = 'panNo='+panNo + '&dcpsEmpId='+dcpsEmpId;
		///var url = 'panNo='+panNo;
		var myAjax = new Ajax.Request(uri, 
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
			        	getResponsePANNo(myAjax,panNo);
						
					},
			        onFailure: function()
			        			{  
	  						alert('Something went wrong...');
	  					} 
			          } 
	);
}
function panNoValidation()
{
	var panValue=document.getElementById('txtPANNo').value;
	var regex1=/^[A-Za-z]{5}\d{4}[A-Za-z]{1}$/;  

	if(regex1.test(panValue)== false && panValue.length != 0){  
		alert('Please enter valid PAN No');  
		document.getElementById('txtPANNo').focus();
		return false;
	}
	return true;
}
function getResponsePANNo(myAjax,panNo){

	//alert("hiii checdksdsd");
	var status;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var empName = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
///	alert('checkFlag'+empName);
	//alert("checkFlag"+checkFlag);
	if(checkFlag=="Y")
	{
		alert('Entered PAN No:'+panNo+' is already present for the employee :'+empName+' in system. Please enter correct PAN number.');
		 document.getElementById("txtPANNo").value = "";
		///clearAllData();
		return false;
	}
	return true;
} 

</script>


<hdiits:form name="DCPSPersonalChangesForm" id="DCPSPersonalChangesForm" encType="multipart/form-data"
	validate="true" method="post" >
	
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.EXISTINGDETAILS" bundle="${dcpsLables}"/></b> </legend>
<input type="hidden" id="txtDdoCode1" name="txtDdoCode1" value="${resValue.DDOCODE}"/>	
<table width="100%" align="center" cellpadding="4" cellspacing="4" height="70%" >
	
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.UIDNO"
				bundle="${dcpsLables}"></fmt:message></td>
			
			<c:set var ="UIDNO11" value="${fn:substring(EMPVOMST.UIDNo,0,4)}"/>
			<c:set var ="UIDNO21" value="${fn:substring(EMPVOMST.UIDNo,4,8)}"/>
			<c:set var ="UIDNO31" value="${fn:substring(EMPVOMST.UIDNo,8,12)}"/> 
				
			<td width="20%" align="left"><input type="text" id="txtUIDNo11"
				size="4" maxlength="4"  name="txtUIDNo11" value="${UIDNO11}" readonly="readonly"/> <input type="text" id="txtUIDNo21"
				size="4"  maxlength="4" name="txtUIDNo21" value="${UIDNO21}" readonly="readonly"/> <input type="text" id="txtUIDNo31"
				size="4"  maxlength="4" name="txtUIDNo31" value="${UIDNO31}" readonly="readonly"/></td>
  
			<td width="15%" align="left"><fmt:message key="CMN.EIDNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtEIDNo1"
				size="30" name="txtEIDNo1" value="${EMPVOMST.EIDNo}" ${varDisabled} readonly="readonly"/></td>
		</tr>
		
		<tr>
		
				<td width="15%" align="left"><fmt:message key="CMN.SALUTATION"
				bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left">
				<select name="cmbSalutation1"
				id="cmbSalutation1" style="width: 66%;"  ${varDisabled} disabled="disabled" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="Salutation" items="${resValue.listSalutation}">
							       <c:choose>
										<c:when test="${EMPVOMST!=null}">
													<c:choose>
														<c:when test="${EMPVOMST.salutation == Salutation.lookupId}">
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
				</select>
				</td>
				<td width="15%" align="left"><fmt:message key="CMN.FULLNAME"
					bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtName1" style="text-transform: uppercase" size="30" disabled="disabled"
					name="txtName1" onblur="isName(txtName1,'This field should not contain any special characters or digits.')" value="${EMPVOMST.name}" ${varDisabled} /></td>
		</tr>	
		
		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.FULLNAMEDEV"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtNameInMarathi1" disabled="disabled"
				id="txtNameInDevanagari1" value="${EMPVOMST.name_marathi}" size="30" ${varDisabled} /> </td>
				
					<script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrNameInMarathi", document.getElementById('txtNameInDevanagari1'),'devanagari');
					
					</script>
		
			<td width="15%" align="left"><fmt:message key="CMN.GENDER"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<c:set var="Male" value="M"></c:set>
			<c:set var="Female" value="F"></c:set>
			
			<c:choose>
					<c:when test="${EMPVOMST!=null}">
							<c:choose>
							
											<c:when test="${EMPVOMST.gender == 77}">
											
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="M" checked="checked" ${varDisabled} disabled="disabled" />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="F" ${varDisabled} disabled="disabled" />
											<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="T" ${varDisabled} disabled="disabled"/>
											</td>
											</c:when>
											
											
											<c:when test="${EMPVOMST.gender== 70}">
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="M" ${varDisabled} disabled="disabled"/>
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="F" checked="checked" ${varDisabled} disabled="disabled"/>
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="T" ${varDisabled} disabled="disabled"/>
											</td>
											</c:when>
											
											<c:otherwise>
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="M" ${varDisabled} disabled="disabled"/>
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="F"  ${varDisabled} disabled="disabled"/>
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="T" checked="checked" ${varDisabled} disabled="disabled"/>
											</td>
											</c:otherwise>
							</c:choose>
						
					</c:when>
				
					<c:otherwise>
					
					<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="M" checked="checked" ${varDisabled} disabled="disabled"/>
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender1" name="radioGender1" value="F" ${varDisabled} disabled="disabled"/>
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender1" name="radioGender1" value="T" ${varDisabled} disabled="disabled"/>
					</td>
					</c:otherwise>
			
			</c:choose>

		</tr>
		
		<tr>
			
			<td width="15%" align="left"><fmt:message key="CMN.FATHERHUSBANDNAME"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtFatherOrHusband1"
				id="txtFatherOrHusband1" value="${EMPVOMST.father_or_husband}"  size="30" ${varDisabled} disabled="disabled"/> </td>
				
			<td width="15%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}"></fmt:message></td>
					
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVOMST.dob}" var="empBirthDate"/>
			
		<td width="20%" align="left"><input type="hidden" name="currDate1"
				id="currDate1" value="${resValue.lDtCurDate}" /> <input type="text"
				name="txtBirthDate1" id="txtBirthDate1" maxlength="10" disabled="disabled"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDate1);compareDates(this,currDate1,'Date of Birth should be less than current date.','<');" value="${empBirthDate}" ${varDisabled}  /> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate1",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>
			
		</tr>
		
		
		
		<tr>
			
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVOMST.doj}" var="empJoiningDate"/>
		
			<td width="15%" align="left"><fmt:message key="CMN.DOJ"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="hidden" name="joinDate"
				id="joinDate" value="${resValue.lDtJoiDtLimit}" /><input type="text"
				name="txtJoiningDate1" id="txtJoiningDate1" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtJoiningDate1);
				compareDates(txtJoiningDate1,this,'Date Of Joining should be greater than DOB!','<');
				compareDates(this,currDate1,'Date of Joining should be less than current date.','<');
				compareDates(joinDate,this,'Date of Joining should be greater than 01/01/2005.','<');"
				value="${empJoiningDate}" ${varDisabled} disabled="disabled" /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoiningDate1", 375, 570)'
				style="cursor: pointer;" ${varImageDisabled}/></td>
				
			<td width="15%" align="left"><fmt:message key="CMN.HANDICAPPED"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<c:choose>
					<c:when test="${EMPPAYROLLVOMST!=null}">
							<c:choose>
											<c:when test="${EMPPAYROLLVOMST.phychallanged == 'TRUE'}">
													<td width="20%" align="left">
																	<fmt:message key="CMN.YES"	bundle="${dcpsLables}"></fmt:message> 
																<input type="radio"	id="radioHandic1" name="radioHandic1" value="TRUE" ${varDisabled}  checked="checked"  disabled="disabled"/>
																	<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
																<input type="radio"	id="radioHandic1" name="radioHandic1" value="FALSE"  ${varDisabled} />
													</td>
											
											</c:when>
											<c:otherwise>
												<td width="20%" align="left">
																<fmt:message key="CMN.YES"	bundle="${dcpsLables}">											</fmt:message> 
															<input type="radio"	id="radioHandic1" name="radioHandic1" value="TRUE" ${varDisabled}  disabled="disabled"/>
																<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
															<input type="radio"	id="radioHandic1" name="radioHandic1" value="FALSE" checked="checked" ${varDisabled} checked="checked" disabled="disabled"/>
												</td>
											</c:otherwise>
							</c:choose>
						
					</c:when>
				
					<c:otherwise>
					
											<td width="20%" align="left">
															<fmt:message key="CMN.YES"	bundle="${dcpsLables}">											</fmt:message> 
								<input type="radio"	id="radioHandic1" name="radioHandic1" value="TRUE" ${varDisabled}  disabled="disabled"/>
															<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioHandic1" name="radioHandic1" value="FALSE" checked="checked" ${varDisabled} checked="checked" disabled="disabled"/>
											</td>
					</c:otherwise>
			
			</c:choose>
			
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESSBUILDING"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtAddressBuilding1"
				id="txtAddressBuilding1" value="${EMPVOMST.building_address}" size="30" ${varDisabled} disabled="disabled" /> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESSSTREET"
				bundle="${dcpsLables}"></fmt:message></td>
			
			<td width="20%" align="left"><input type="text" name="txtAddressStreet1"
				id="txtAddressStreet1" value="${EMPVOMST.building_street}" size="30" ${varDisabled} disabled="disabled"/> </td>
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.LANDMARK"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtLandmark1"
				id="txtLandmark1" value="${EMPVOMST.landmark}" size="30" ${varDisabled} disabled="disabled"/> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.LOCALITY"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtLocality1"
				id="txtLocality1" value="${EMPVOMST.locality}" size="30" ${varDisabled} disabled="disabled" /> </td>
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.DISTRICT"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtDistrict1"
				id="txtDistrict1" value="${EMPVOMST.district}" size="30"  ${varDisabled} disabled="disabled"/> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.STATE"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><select name="cmbState1" id="cmbState1" style="width:65%" ${varDisabled} disabled="disabled">
				         			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="stateName" items="${resValue.STATENAMES}" >
				         					
				         					<c:choose>
				         						<c:when test="${EMPVOMST!=null}">
				         							<c:choose>
				         							<c:when test="${stateName.id==EMPVOMST.state}">
				         								<option value="${stateName.id}" selected="selected">${stateName.desc}</option>
				         							</c:when>
				         							<c:otherwise>
				         								<option value="${stateName.id}">${stateName.desc}</option>
				         							</c:otherwise>
				         							</c:choose>
				         						</c:when>
				         					
				         						<c:otherwise>
				         								<option value="${stateName.id}">${stateName.desc}</option>
				         						</c:otherwise>
				         					</c:choose>
												
										</c:forEach>
				         	 		</select> </td>
		</tr>
		
		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.PINCODE"
				bundle="${dcpsLables}"></fmt:message></td>
					
			<td width="20%" align="left"><input type="text" name="txtPinCode1"
					id="txtPinCode1" value="${EMPVOMST.pincode}" size="30" ${varDisabled} disabled="disabled"/> </td>
			
			<td width="15%" align="left"><fmt:message key="CMN.CONTACTTELNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtContactTelNo1" size="30" name="txtContactTelNo1" value="${EMPVOMST.cntctNo}" 
				onkeypress="digitFormat(this);"onblur="checkLength(txtContactTelNo1,'Telephone number');" ${varDisabled} disabled="disabled"/></td>
				
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.CELLNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtCellNo1"
				size="30" name="txtCellNo1" onblur="checkLength(txtCellNo1,'Cell Number');"
				onkeypress="digitFormat(this);" 
				value="${EMPVOMST.cellNo}" ${varDisabled} disabled="disabled"/></td>
			
			<td width="15%" align="left"><fmt:message key="CMN.EMAIL"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtEmailId1"
				id="txtEmailId1" value="${EMPVOMST.emailId}" size="30" ${varDisabled} disabled="disabled"/> </td>
			
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PANNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtPANNo1"
				size="30" name="txtPANNo1" value="${EMPVOMST.PANNo}" ${varDisabled}  disabled="disabled"/></td>
			<td></td>
			<td></td>
		</tr>
		
	</table>
	</fieldset>
	
	<br/>
	
	
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CHANGEDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>	
	<input type="hidden" id="txtDdoCode" name="txtDdoCode" value="${resValue.DDOCODE}"/>
	<input type="hidden" id="lStrDesignation" name="lStrDesignation" value="${resValue.lStrDesignation}"/>
	<input type="hidden" id="lStrChangesType" name="lStrChangesType" value="${resValue.lStrChangesType}"/>
	
	<c:if test="${EMPVO.UIDNo != null && EMPVO.UIDNo != ''}">
		<c:set var="varEIDReadOnly" scope="page" value="readonly='readonly'"></c:set>
	</c:if>
	
	<c:if test="${EMPVO.EIDNo != null && EMPVO.EIDNo != ''}">
		<c:set var="varUIDReadOnly" scope="page" value="readonly='readonly'"></c:set>
	</c:if>
		
	<table width="100%" align="center" cellpadding="4" cellspacing="4" height="70%" >
	
			<c:set var ="UIDNO1" value="${fn:substring(EMPVO.UIDNo,0,4)}"/>
			<c:set var ="UIDNO2" value="${fn:substring(EMPVO.UIDNo,4,8)}"/>
			<c:set var ="UIDNO3" value="${fn:substring(EMPVO.UIDNo,8,12)}"/> 
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.UIDNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtUIDNo1"
				size="4" maxlength="4"  name="txtUIDNo1" value="${UIDNO1}" onblur="IDValidation();" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);"  ${varDisabled} ${varUIDReadOnly } /> <input type="text" id="txtUIDNo2"
				size="4"  maxlength="4" name="txtUIDNo2" value="${UIDNO2}" onblur="IDValidation();" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);"  ${varDisabled} ${varUIDReadOnly }/> <input type="text" id="txtUIDNo3"
				size="4"  maxlength="4" name="txtUIDNo3" value="${UIDNO3}" onblur="IDValidation();" onkeypress="digitFormat(this);"  onKeyUp="return autoTab(this, 4, event);" ${varDisabled} ${varUIDReadOnly }/></td>
  
			<td width="15%" align="left"><fmt:message key="CMN.EIDNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtEIDNo"
				size="30" name="txtEIDNo" value="${EMPVO.EIDNo}" ${varDisabled} /></td>
		</tr>
		
		<tr>
		
				<td width="15%" align="left"><fmt:message key="CMN.SALUTATION"
				bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left">
				<select name="cmbSalutation"
				id="cmbSalutation" style="width: 66%;"  ${varDisabled} >
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
				</select>
				</td>
				<td width="15%" align="left"><fmt:message key="CMN.FULLNAME"
					bundle="${dcpsLables}"></fmt:message></td>
				<td width="20%" align="left"><input type="text"
					id="txtName" style="text-transform: uppercase" size="30"
					name="txtName" onblur="isName(txtName,'This field should not contain any special characters or digits.')" value="${EMPVO.name}" ${varDisabled} /></td>
		</tr>	
		
		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.FULLNAMEDEV"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtNameInMarathi"
				id="txtNameInDevanagari" value="${EMPVO.name_marathi}" size="30" ${varDisabled} /> </td>
				
					<script language="javascript" type="text/javascript">
					
						pph = new PramukhPhoneticHandler();
						pph.convertToIndicIME("txtPnsnrNameInMarathi", document.getElementById('txtNameInDevanagari'),'devanagari');
					
					</script>
		
			<td width="15%" align="left"><fmt:message key="CMN.GENDER"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<c:set var="Male" value="M"></c:set>
			<c:set var="Female" value="F"></c:set>
			
			<c:choose>
					<c:when test="${EMPVO!=null && EMPVO.gender != null }">
							<c:choose>
							
											<c:when test="${EMPVO.gender == 77}">
											
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="M" checked="checked" ${varDisabled}  />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="F" ${varDisabled} />
											<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="T" ${varDisabled} />
											</td>
											</c:when>
											
											<c:when test="${EMPVO.gender == 70}">
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="M" ${varDisabled} />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="F" checked="checked" ${varDisabled} />
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="T" ${varDisabled} />
											</td>
											</c:when>
											
											<c:otherwise>
											<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="M" ${varDisabled} />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="F"  ${varDisabled} />
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="T" checked="checked" ${varDisabled} />
											</td>
											</c:otherwise>
							</c:choose>
						
					</c:when>
				
					<c:otherwise>
					
					<td width="20%" align="left"><fmt:message key="CMN.MALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="M" ${varDisabled} />
											<fmt:message key="CMN.FEMALE"
						bundle="${dcpsLables}"></fmt:message> 
											<input type="radio"
									id="radioGender" name="radioGender" value="F" ${varDisabled} />
									<fmt:message key="CMN.TRANSGENDER"
							bundle="${dcpsLables}"></fmt:message>
											<input type="radio"
									id="radioGender" name="radioGender" value="T" ${varDisabled} />
					</td>
					</c:otherwise>
			
			</c:choose>

		</tr>
		
		<tr>
			
			<td width="15%" align="left"><fmt:message key="CMN.FATHERHUSBANDNAME"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtFatherOrHusband"
				id="txtFatherOrHusband" value="${EMPVO.father_or_husband}"  size="30" ${varDisabled} /> </td>
				
			<td width="15%" align="left"><fmt:message key="CMN.DOB"
				bundle="${dcpsLables}"></fmt:message></td>
					
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVO.dob}" var="empBirthDate"/>
			
		<td width="20%" align="left"><input type="hidden" name="currDate1"
				id="currDate1" value="${resValue.lDtCurDate}" />
				<input type="text"
				name="txtBirthDate" id="txtBirthDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtBirthDate);compareDates(this,document.getElementById('currDate1'),'Date of Birth should be less than current date.','<');" value="${empBirthDate}" ${varDisabled}  /> 
				<img src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtBirthDate",375,570)'
				style="cursor: pointer;" ${varImageDisabled} /></td>
			
		</tr>
		
		
		
		<tr>
			
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${EMPVO.doj}" var="empJoiningDate"/>
		
			<td width="15%" align="left"><fmt:message key="CMN.DOJ"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="hidden" name="joinDate"
				id="joinDate" value="${resValue.lDtJoiDtLimit}" /><input type="text"
				name="txtJoiningDate" id="txtJoiningDate" maxlength="10"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(txtJoiningDate);
				compareDates(txtJoiningDate,this,'Date Of Joining should be greater than DOB!','<');
				compareDates(this,currDate1,'Date of Joining should be less than current date.','<');
				compareDates(joinDate,this,'Date of Joining should be greater than 01/01/2005.','<');"
				value="${empJoiningDate}" ${varDisabled} /> <img
				src='images/CalendarImages/ico-calendar.gif' width='20'
				onClick='window_open("txtJoiningDate", 375, 570)'
				style="cursor: pointer;" ${varImageDisabled}/></td>
				
			<td width="15%" align="left"><fmt:message key="CMN.HANDICAPPED"
				bundle="${dcpsLables}"></fmt:message>
			</td>
				
			<c:choose>
					<c:when test="${EMPVO!=null && EMPVO.phychallanged != null}">
							<c:choose>
							
											<c:when test="${EMPVO.phychallanged == 'TRUE'}">
											
											<td width="20%" align="left">
															<fmt:message key="CMN.YES"	bundle="${dcpsLables}">											</fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="TRUE" ${varDisabled}  checked="checked"  />
															<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="FALSE" ${varDisabled} />
											</td>
											
											</c:when>
											
											<c:otherwise>
											
											<td width="20%" align="left">
															<fmt:message key="CMN.YES"	bundle="${dcpsLables}">											</fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="TRUE" ${varDisabled} />
															<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="FALSE" checked="checked" ${varDisabled} />
											</td>
											
											</c:otherwise>
							</c:choose>
						
					</c:when>
				
					<c:otherwise>
					
											<td width="20%" align="left">
															<fmt:message key="CMN.YES"	bundle="${dcpsLables}">											</fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="TRUE" ${varDisabled} />
															<fmt:message key="CMN.NO"	bundle="${dcpsLables}"></fmt:message> 
								<input type="radio"	id="radioHandic" name="radioHandic" value="FALSE" ${varDisabled} />
											</td>
					</c:otherwise>
			
			</c:choose>
			
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESSBUILDING"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtAddressBuilding"
				id="txtAddressBuilding" value="${EMPVO.building_address}" size="30" ${varDisabled} /> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.ADDRESSSTREET"
				bundle="${dcpsLables}"></fmt:message></td>
			
			<td width="20%" align="left"><input type="text" name="txtAddressStreet"
				id="txtAddressStreet" value="${EMPVO.building_street}" size="30" ${varDisabled} /> </td>
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.LANDMARK"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtLandmark"
				id="txtLandmark" value="${EMPVO.landmark}" size="30" ${varDisabled} /> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.LOCALITY"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtLocality"
				id="txtLocality" value="${EMPVO.locality}" size="30" ${varDisabled} /> </td>
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.DISTRICT"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><input type="text" name="txtDistrict"
				id="txtDistrict" value="${EMPVO.district}" size="30"  ${varDisabled} /> </td>
		
			<td width="15%" align="left"><fmt:message key="CMN.STATE"
				bundle="${dcpsLables}"></fmt:message></td>
				
			<td width="20%" align="left"><select name="cmbState" id="cmbState" style="width:65%" ${varDisabled} >
				         			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				         				<c:forEach var="stateName" items="${resValue.STATENAMES}" >
				         					
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
				         								<option value="${stateName.id}">${stateName.desc}</option>
				         						</c:otherwise>
				         					</c:choose>
												
										</c:forEach>
				         	 		</select> </td>
		</tr>
		
		<tr>
		
			<td width="15%" align="left"><fmt:message key="CMN.PINCODE"
				bundle="${dcpsLables}"></fmt:message></td>
					
			<td width="20%" align="left"><input type="text" name="txtPinCode"
					id="txtPinCode" value="${EMPVO.pincode}" size="30" ${varDisabled} /> </td>
			
			<td width="15%" align="left"><fmt:message key="CMN.CONTACTTELNO"
					bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text"
				id="txtContactTelNo" size="30" name="txtContactTelNo" value="${EMPVO.cntctNo}" 
				onkeypress="digitFormat(this);"onblur="checkLength(txtContactTelNo,'Telephone number');" ${varDisabled} /></td>
				
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.CELLNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtCellNo"
				size="30" name="txtCellNo" onblur="checkLength(txtCellNo,'Cell Number');"
				onkeypress="digitFormat(this);" 
				value="${EMPVO.cellNo}" ${varDisabled} /></td>
			
			<td width="15%" align="left"><fmt:message key="CMN.EMAIL"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" name="txtEmailId"
				id="txtEmailId" value="${EMPVO.emailId}" size="30" ${varDisabled} /> </td>
			
		</tr>
		
		<tr>
			<td width="15%" align="left"><fmt:message key="CMN.PANNO"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="20%" align="left"><input type="text" id="txtPANNo"
				size="30" name="txtPANNo" value="${EMPVO.PANNo}" ${varDisabled} onblur="panNoValidation();chkPANalreadyExists();"/></td>
			<td></td>
			<td></td>
		</tr>
		
	</table>
	
	<br/>
	
	</fieldset>
	
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.AUTHORITYDETAILS" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
		<table width="50%" align="left" cellpadding="4" cellspacing="4" >
			<tr>
				<td width="15%" align="left">Authority Letter No.</td>
				<td width="20%" align="left" colspan = "3"><input type="text"
					id="txtAuthorityLetterNo" style="text-transform: uppercase" size="30"
					name="txtAuthorityLetterNo" value="${CHANGESVO.letterNo}" onblur="isIntegerOrNot(document.getElementById('txtAuthorityLetterNo'),'Authority Letter No should be Number Only')"/>
					<label class="mandatoryindicator">*</label></td>
			
			</tr>
			<tr>
				<td width="15%" align="left">Letter Date</td>
				<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${CHANGESVO.letterDate}" var="letterDate"/>
				<td width="20%" align="left" colspan = "3"><input type="text"
					id="txtAuthorityLetterDate" style="text-transform: uppercase" size="30"
					onkeypress="digitFormat(this);dateFormat(this);"
					name="txtAuthorityLetterDate" value="${letterDate}" /><img
					src='images/CalendarImages/ico-calendar.gif' width='20'
					onClick='window_open("txtAuthorityLetterDate", 375, 570)'
					style="cursor: pointer;"/>
					<label class="mandatoryindicator">*</label></td>
			
			</tr>
		</table>
		
	</fieldset>
	
	<br/>
	
	<input type="hidden" id="User" name="User" value="${resValue.UserType}">
	<c:if test="${resValue.UserType == 'DDO'}">
		<table width="100%">
			<tr>
				<td width="20%" align="left" style="padding-left: 5px"><fmt:message key="CMN.REMARKS"
					bundle="${dcpsLables}"></fmt:message></td>
				<td align="left" width="80%" style="padding-left: 23px">
					<input type="text" name="sentBackRemarks" id="sentBackRemarks" value="" size="100"  />
				</td>
			</tr>
		</table>
	</c:if>
		
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${resValue.ForwardToPost}"/>	
	
		<div align="right">
						<c:choose>
										<c:when test="${resValue.dcpsChangesId != null}">
											<input type="hidden" id="dcpsHstChangesId" value="${resValue.dcpsChangesId}"/>
											<input type="hidden" id="lStrDesignationDraft" value="${resValue.lStrDesignationDraft}"/>
										</c:when>
										<c:otherwise>
											<input type="hidden" id="dcpsHstChangesId" value=""/>
											<input type="hidden" id="lStrDesignationDraft" value=""/>
										</c:otherwise>
						</c:choose>
							
						<c:choose>
							<c:when test="${resValue.UserType == 'DDOAsst'}">
							
								<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnUpdatePersonalDtls" id="btnUpdatePersonalDtls" type="button"
									captionid="BTN.SAVEASDRAFT" bundle="${dcpsLables}"
									onclick="updateOrForwardPersonalDetails('${EMPVOMST.dcpsEmpId}',1);" />
								<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${resValue.ForwardToPost}"/>	
								<hdiits:button name="BTN.FORWARD" id="btnForwardForUpdateTotally" type="button"
									captionid="BTN.FORWARD" bundle="${dcpsLables}" onclick= "updateOrForwardPersonalDetails('${EMPVOMST.dcpsEmpId}',2);" />
							</c:when>
							
							<c:otherwise>
								<hdiits:button name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK" bundle="${dcpsLables}" onclick="ReturnfromChanges();"/>
								<hdiits:button
									name="btnApprovePersonalDtls" id="btnApprovePersonalDtls" type="button"
									captionid="BTN.APPROVE" bundle="${dcpsLables}"
									onclick="approvePersonalDetails();" />
								<hdiits:button name="BTN.REJECT" id="btnRejectPersonalDtls" type="button"
									captionid="BTN.REJECT" bundle="${dcpsLables}" onclick= "rejectPersonalDetails();" />
							</c:otherwise>
					</c:choose>	
		</div>
		</hdiits:form>