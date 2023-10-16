<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DDOCode" value="${resValue.DDOCode}"></c:set>
<c:set var="DdoRegNO" value="${resValue.DdoRegNO}"></c:set>
<c:set var="DtoCode" value="${resValue.DtoCode}"></c:set>
<c:set var="EmpSevarthId" value="${resValue.EmpSevarthId}"></c:set>
<c:set var="EmpFName" value="${resValue.EmpFName}"></c:set>
<c:set var="EmpMName" value="${resValue.EmpMName}"></c:set>
<c:set var="EmpLName" value="${resValue.EmpLName}"></c:set>
<c:set var="RelationList" value="${resValue.RelationList}"></c:set>
<c:set var="empDetails" value="${resValue.empDetails}"></c:set>
<c:set var="BankLists" value="${resValue.BankLists}"></c:set>
<c:set var="StateLists" value="${resValue.StateLists}"></c:set>
<c:set var="payScaleLists" value="${resValue.payScaleLists}"></c:set>
<c:set var="currentDate" value="${resValue.curretDate}"></c:set>
<c:set var="DOJ" value="${resValue.DOJ}"></c:set>
<c:set var="dsgnName" value="${resValue.dsgnName}"></c:set>
<c:set var="DcpsId" value="${resValue.DcpsId}"></c:set>

<c:set var="nomineePK1" value="${resValue.nomineePK1}"></c:set>
<c:set var="nomineeName1" value="${resValue.nomineeName1}"></c:set>
<c:set var="nomineePK2" value="${resValue.nomineePK2}"></c:set>
<c:set var="nomineeName2" value="${resValue.nomineeName2}"></c:set>
<c:set var="nomineePK3" value="${resValue.nomineePK3}"></c:set>
<c:set var="nomineeName3" value="${resValue.nomineeName3}"></c:set>


<style>
.radiomainTb input[type=radio] td {
	margin-right: 20px;
	border: none;
	padding: 5px
}
</style>
<script type="text/javascript">
function resetForm()
{
	//alert('inside reset');
	document.getElementById("permanentAddFlatNo").readOnly=true;
	document.getElementById("permanentAddBuilding").readOnly=true;
	document.getElementById("permanentAddTaluka").readOnly=true;
	document.getElementById("permanentAddDist").readOnly=true;
	document.getElementById("permanentAddPin").readOnly=true;
	document.formS1Form.reset();
}

function radioButtonValue(objButton){
	
	var values;
		for(var i = 0; i < objButton.length; i++){
		    if(objButton[i].checked){
		    	values = objButton[i].value;
		    }
		
		}
		return values;
}
function submitForm1(){

	var salutation = document.getElementById("salutation").selected.trim();
	alert(salutation);
	}

function submitForm()
{

	//document.getElementById("btnSubmit").disabled=true;

	var salutation = document.getElementById("salutation").value.trim();
	var sevarthId = document.getElementById("sevarthId").value.trim();
	var ddoName = document.getElementById("ddoName").value.trim();
// var cmbSalutation
	var empFName = document.getElementById("empFName").value.trim();
	var empFName = document.getElementById("empFName").value.trim();
	var empMName = document.getElementById("empMName").value.trim();
	var empLName = document.getElementById("empLName").value.trim();
	
	var empFatherFName = document.getElementById("empFatherFName").value.trim();
	var empFatherLName = document.getElementById("empFatherLName").value.trim();
	var empMotherName = document.getElementById("empMotherName").value.trim();
    var empDob	= document.getElementById("empMotherName").value.trim();
// var empGender;
	var panNo=document.getElementById("DOJ").value.trim();
	var aadharNo=document.getElementById("uidNo").value.trim();
	var DOJ= document.getElementById("doj").value.trim();
	var superAnnDate=document.getElementById("doj").value.trim();
	var empClass= document.getElementById("empClass").value.trim();
	var empDept= document.getElementById("empDept").value.trim();
	var empMinistry= document.getElementById("empMinistry").value.trim();
	var basicSalary= document.getElementById("basicSalary").value.trim();
	var payScale= document.getElementById("payScale").value.trim();
	

	var dsgnName= document.getElementById("dsgnName").value.trim();
	var DcpsId= document.getElementById("DcpsId").value.trim();
	var DtoCode=document.getElementById("DtoCode").value.trim();
	var DDORegNo=document.getElementById("DDORegNo").value.trim();
	var presentAddFlatNo = document.getElementById("presentAddFlatNo").value.trim();
	var presentAddBuilding = document.getElementById("presentAddBuilding").value.trim();
	var presentAddTaluka = document.getElementById("presentAddTaluka").value.trim();
	var presentAddLandmark = document.getElementById("presentAddLandmark").value.trim();
	var presentAddDist = document.getElementById("presentAddDist").value.trim();
	var presentAddState = document.getElementById("presentAddState").value.trim();
	var presentAddCountry = document.getElementById("presentAddCountry").value.trim();
	var presentAddPin = document.getElementById("presentAddPin").value.trim();
	
	var permanentAddFlatNo = document.getElementById("permanentAddFlatNo").value.trim();
	var permanentAddBuilding = document.getElementById("permanentAddBuilding").value.trim();
	var permanentAddTaluka = document.getElementById("permanentAddTaluka").value.trim();
	var permanentAddLandmark = document.getElementById("permanentAddLandmark").value.trim();
	var permanentAddDist = document.getElementById("permanentAddDist").value.trim();
	var permanentAddState = document.getElementById("permanentAddState").value.trim();
	var permanentAddCountry = document.getElementById("permanentAddCountry").value.trim();
	var permanentAddPin = document.getElementById("permanentAddPin").value.trim();
	
	//var phoneSTDCode = document.getElementById("phoneSTDCode").value.trim();
	var phoneNo = document.getElementById("phoneNo").value.trim();
	var mobileNo = document.getElementById("mobileNo").value.trim();
	var emailId = document.getElementById("emailId").value.trim();
	// var smsSubFlag=Array
	//var emailSubFlag
	var smsSubFlag=document.getElementsByName("smsSubFlag");
	var emailSubFlag=document.getElementsByName("emailSubFlag");
	var noOfNominee=document.getElementsByName("noOfNominee");
	var nominee1Name = document.getElementById("nominee1Name").value.trim();
	var nominee1DOB = document.getElementById("nominee1DOB").value.trim();
	var nominee1Relation = document.getElementById("nominee1Relation").value.trim();//nominee1 relation drop down
	var nominee1Percent = document.getElementById("nominee1Percent").value.trim();
	var nominee1Guardian = document.getElementById("nominee1Guardian").value.trim();
	var nominee1InvalidCondition = document.getElementById("nominee1InvalidCondition").value.trim();

	var nominee2Name = document.getElementById("nominee2Name").value.trim();
	var nominee2DOB = document.getElementById("nominee2DOB").value.trim();
	var nominee2Relation = document.getElementById("nominee2Relation").value.trim();//nominee2 relation drop down
	var nominee2Percent = document.getElementById("nominee2Percent").value.trim();
	var nominee2Guardian = document.getElementById("nominee2Guardian").value.trim();
	var nominee2InvalidCondition = document.getElementById("nominee2InvalidCondition").value.trim();

	var nominee3Name = document.getElementById("nominee3Name").value.trim();
	var nominee3DOB = document.getElementById("nominee3DOB").value.trim();
	var nominee3Relation = document.getElementById("nominee3Relation").value.trim();//nominee3 relation drop down
	var nominee3Percent = document.getElementById("nominee3Percent").value.trim();
	var nominee3Guardian = document.getElementById("nominee3Guardian").value.trim();
	var nominee3InvalidCondition = document.getElementById("nominee3InvalidCondition").value.trim();

	if(salutation==0){
		alert('Please select salutation.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
		}
	if(sevarthId == '')
	{
		alert('Please enter Sevarth Id.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(ddoName == '')
	{
		alert('Please enter ddo name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	
	if(empFName == '')
	{
		alert('Please enter employee first name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empMName == '')
	{
		alert('Please enter employee middle name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empLName == '')
	{
		alert('Please enter employee last name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(empFatherFName == '')
	{
		alert('Please enter employee\'s father first name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empFatherLName == '')
	{
		alert('Please enter employee\'s father last name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empMotherName == '')
	{
		alert('Please enter employee\'s Mother  name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empDob == '')
	{
		alert('Please enter employee\'s date of birth.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	var empGender=document.getElementsByName("empGender");
	//var empGender=document.querySelector('input[name="empGender"]:checked');
		
	if(empGender.checked==false)
	{
		alert('Please select Gender.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}	
	
var maritalStatus=document.getElementsByName("maritalStatus");
//var maritalStatus=document.querySelector( 'input[name="maritalStatus"]:checked');
	
	if(maritalStatus.checked==false)
	{
		alert('Please select marital status.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(panNo == '')
	{
		alert('Please enter pan Number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(panNo.length !=10)
	{
		alert('Please enter correct pan Number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	/* if(aadharNo == '')
	{
		alert('Please enter aadhar number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	} */
	if(aadharNo != '' && aadharNo.length!=12)
	{
		alert('Please enter correct aadhar number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empClass == '' )
	{
		alert('Please enter correct employee class.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empDept == '')
	{
		alert('Please enter employee department.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empMinistry == '')
	{
		alert('Please enter correct employee Department ministry.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(basicSalary == '')
	{
		alert('Please enter employee basic salary.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(basicSalary == '')
	{
		alert('Please enter employee pay scale.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	
	if(presentAddFlatNo == '')
	{
		alert('Please enter present Flat/ Unit No, Block No.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddBuilding == '')
	{
		alert('Please enter present Name of premise/Building/ Village.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddTaluka == '')
	{
		alert('Please enter present Area/ Locality/taluka.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(presentAddLandmark == '')
	{
		alert('Please enter present LandMark.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddDist == '')
	{
		alert('Please enter present District/TOWN/CITY.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddState == '')
	{
		alert('Please enter present STATE/UNION territory.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddCountry == '')
	{
		alert('Please enter present Country.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(presentAddPin == '')
	{
		alert('Please enter present PIN CODE.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(presentAddPin.length!=6)
	{
		alert('Please enter valid Pin Code. It should consist of 6 digits');
		document.getElementById("btnSubmit").disabled=false;
	  	return false;
	}
	if(presentAddPin.charAt(0)!='4')
	{
		alert('Please enter correct Pin code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!(document.getElementById('presentSamePerm').checked) && !(document.getElementById('presentNotSamePerm').checked)) 
	{
		alert('Please select whether present address is same as permanent address.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(permanentAddFlatNo == '')
	{
		alert('Please enter permanent Flat/ Unit No, Block No.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddBuilding == '')
	{
		alert('Please enter permanent Name of premise/Building/ Village.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddTaluka == '')
	{
		alert('Please enter permanent Area/ Locality/taluka.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(permanentAddLandmark == '')
	{
		alert('Please enter permanent LandMark.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddDist == '')
	{
		alert('Please enter permanent District/TOWN/CITY.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddState == '')
	{
		alert('Please enter permanent STATE/UNION territory.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddCountry == '')
	{
		alert('Please enter permanent Country.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(permanentAddPin == '')
	{
		alert('Please enter permanent PIN CODE.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(phoneNo == '')
	{
		alert('Please enter phone number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(phoneNo.length != 10)
	{
		alert('Please enter 8 digit phone number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(mobileNo == '')
	{
		alert('Please enter mobile number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	// var smsSubFlag=Array
	//var emailSubFlag
		
	if(smsSubFlag.checked==false)
	{
		alert('Please select subscribe to SMS Alerts.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	
	if(emailId == '')
	{
		alert('Please enter email Id.');
		return false;
	} 

	if(emailSubFlag.checked==false)
	{
		alert('Please select subscribe to EMAIL Alerts.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(!(document.getElementById('1nominee').checked) && !(document.getElementById('2nominee').checked)&& !(document.getElementById('3nominee').checked)) 
	{
		alert('Please select number of nominees.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	var noOfNomeenies=null;
	if(document.getElementById('1nominee').checked)
	{
		noOfNomeenies='1';
	}
	if(document.getElementById('2nominee').checked)
	{
		noOfNomeenies='2';
	}
	if(document.getElementById('3nominee').checked)
	{
		noOfNomeenies='3';
	}
	//alert('noOfNomeenies: '+noOfNomeenies);
	if(noOfNomeenies=='1')
	{
		
		if(nominee1Name == '')
		{
			alert('Please enter nominee 1 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if(nominee1Percent != 100)
		{
			alert('Please enter nominee percentage 100% for single nominee.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		nominee2Name='NA';
		nominee2DOB=null;
		nominee2Relation='NA';
		nominee2Percent='0';
		nominee2Guardian='NA';
		nominee2InvalidCondition='NA';
		nominee3Name='NA';
		nominee3DOB=null;
		nominee3Relation='NA';
		nominee3Percent='0';
		nominee3Guardian='NA';
		nominee3InvalidCondition='NA';
	}
	if(noOfNomeenies=='2')
	{
		
		if(nominee1Name == '')
		{
			alert('Please enter nominee 1 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian nae in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}


		
		if(nominee2Name == '')
		{
			alert('Please enter nominee 2 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2DOB == '')
		{
			alert('Please enter nominee 2 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Relation == '-1')
		{
			alert('Please enter nominee 2 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Percent == '')
		{
			alert('Please enter valid nominee 2 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Guardian == '')
		{
			alert('Please enter nominee 2 guardian nae in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2InvalidCondition == '')
		{
			alert('Please enter nominee 2 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)== 0))
		{
			alert('Nominee 1 percentage can not be zero.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee2Percent)== 0))
		{
			alert('Nominee 2 percentage can not be zero.');
			document.getElementById("nominee2Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)+Number(nominee2Percent))!=100)
		{
			document.getElementById("nominee1Percent").value='';
			document.getElementById("nominee2Percent").value='';
			alert('Please enter nominee percentage equal to 100 %.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		nominee3Name='NA';
		nominee3DOB=null;
		nominee3Relation='NA';
		nominee3Percent='0';
		nominee3Guardian='NA';
		nominee3InvalidCondition='NA';
	}

	if(noOfNomeenies=='3')
	{
		
		if(nominee1Name == '')
		{
			alert('Please enter nominee 1 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1DOB == '')
		{
			alert('Please enter nominee 1 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Relation == '-1')
		{
			alert('Please enter nominee 1 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Percent == '')
		{
			alert('Please enter valid nominee 1 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1Guardian == '')
		{
			alert('Please enter nominee 1 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee1InvalidCondition == '')
		{
			alert('Please enter nominee 1 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}


		
		if(nominee2Name == '')
		{
			alert('Please enter nominee 2 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2DOB == '')
		{
			alert('Please enter nominee 2 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Relation == '-1')
		{
			alert('Please enter nominee 2 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Percent == '')
		{
			alert('Please enter valid nominee 2 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2Guardian == '')
		{
			alert('Please enter nominee 2 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee2InvalidCondition == '')
		{
			alert('Please enter nominee 2 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		

		
		if(nominee3Name == '')
		{
			alert('Please enter nominee 3 name.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3DOB == '')
		{
			alert('Please enter nominee 3 date of birth.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3Relation == '-1')
		{
			alert('Please enter nominee 3 relation.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3Percent == '')
		{
			alert('Please enter valid nominee 3 percentage.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3Guardian == '')
		{
			alert('Please enter nominee 3 guardian name in case if minor nominee.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		
		if(nominee3InvalidCondition == '')
		{
			alert('Please enter nominee 3 condition rendering rendering nomination invalid. If not applicable please enter NA.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)== 0))
		{
			alert('Nominee 1 percentage can not be zero.');
			document.getElementById("nominee1Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee2Percent)== 0))
		{
			alert('Nominee 2 percentage can not be zero.');
			document.getElementById("nominee2Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee3Percent)== 0))
		{
			alert('Nominee 3 percentage can not be zero.');
			document.getElementById("nominee3Percent").value='';
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
		if((Number(nominee1Percent)+Number(nominee2Percent)+Number(nominee3Percent))!=100)
		{
			document.getElementById("nominee1Percent").value='';
			document.getElementById("nominee2Percent").value='';
			document.getElementById("nominee3Percent").value='';
			alert('Please enter nominee percentage equal to 100 %.');
			document.getElementById("btnSubmit").disabled=false;
			return false;
		}
	}

	var bankAcntNo=document.getElementById("bankAcntNo").value.trim();
	var empBankName=document.getElementById("empBankName").value.trim();
	var empBankBranchName=document.getElementById("empBankBranchName").value.trim();
	var empBankBranchAddress=document.getElementById("empBankBranchAddress").value.trim();
	var empBankPinCode=document.getElementById("empBankPinCode").value.trim();
	var bankstate=document.getElementById("bankstate").value.trim();
	var IfscCode=document.getElementById("IfscCode").value.trim();
  
	if(bankAcntNo == '')
	{
		alert('Please enter bank account number.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empBankName == '' || empBankName==0)
	{
		alert('Please select bank name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(empBankBranchName == '')
	{
		alert('Please enter bank branch name.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empBankBranchAddress == '')
	{
		alert('Please enter bank branch addres.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	if(empBankPinCode == '')
	{
		alert('Please enter bank pin code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(bankstate == '' || bankstate==0)
	{
		alert('Please enter bank state.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}
	if(IfscCode == '')
	{
		alert('Please enter bank ifsc code.');
		document.getElementById("btnSubmit").disabled=false;
		return false;
	}

	var dispNameFlag=document.getElementsByName("dispNameFlag");
	var dobProofFlag=document.getElementsByName("dobProof");
	var eduQualFlag=document.getElementsByName("eduQual");
	var incomeRangeFlag=document.getElementsByName("incomeRange");

/* var dispNameFlag=document.querySelector('input[name="dispNameFlag"]:checked');
var dobProof=document.querySelector('input[name="dobProof"]:checked');
var eduQual=document.querySelector('input[name="eduQual"]:checked');
var incomeRange=document.querySelector('input[name="incomeRange"]:checked'); */
if(dispNameFlag.checked==false)
{
	alert('Please select display name on pran card.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(dobProofFlag.checked==false)
{
	alert('Please select date of birth proof document.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(eduQualFlag.checked==false)
{
	alert('Please select education qualification.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
if(incomeRangeFlag.checked==false)
{
	alert('Please select income range.');
	document.getElementById("btnSubmit").disabled=false;
	return false;
}
var hdnDDOCode=	 document.getElementById("hdnDDOCode").value;
var url = 'ifms.htm?actionFlag=saveFormS1Dtls';
var noOfNominee=radioButtonValue(document.getElementsByName("noOfNominee"));
var Gender=radioButtonValue(empGender);
var Marital=radioButtonValue(maritalStatus);
var dispName=radioButtonValue(dispNameFlag);
var dobProof=radioButtonValue(dobProofFlag);
var eduQual=radioButtonValue(eduQualFlag);
var incomeRange=radioButtonValue(incomeRangeFlag);
var smsSub=radioButtonValue(smsSubFlag);
var emailSub=radioButtonValue(emailSubFlag);



var uri= "&sevarthId="+sevarthId+"&DtoCode="+DtoCode+"&DDORegNo="+DDORegNo+"&hdnDDOCode="+hdnDDOCode+"&ddoName=" +ddoName+"&salutation="+salutation+
"&empFName=" +empFName+"&empMName" +empMName+"&empLName=" +empLName+"&empFatherFName=" +empFatherFName+
"&empFatherLName=" +empFatherLName+"&empMotherName="+empMotherName+"&empDob="+empDob+
"&empGender="+Gender+"&empMaritalStatus="+Marital+
"&panNo="+panNo+"&aadharNo="+aadharNo+"&DOJ="+DOJ+"&superAnnDate="+superAnnDate+"&empClass="+empClass+
"&empDept="+empDept+"&empMinistry="+empMinistry+"&basicSalary="+basicSalary+"&payScale="+payScale+	
"&dsgnName="+dsgnName+"&DcpsId="+DcpsId+
"&presentAddFlatNo="+presentAddFlatNo+"&presentAddBuilding="+presentAddBuilding+
"&presentAddTaluka="+presentAddTaluka+"&presentAddLandmark"+presentAddLandmark+"&presentAddDist="+presentAddDist+
"&presentAddState="+presentAddState+"&presentAddCountry="+presentAddCountry+
"&presentAddPin="+presentAddPin+
"&permanentAddFlatNo="+permanentAddFlatNo+"&permanentAddBuilding="+permanentAddBuilding+
"&permanentAddTaluka="+permanentAddTaluka+"&permanentAddLandmark"+permanentAddLandmark+"&permanentAddDist="+permanentAddDist+
"&permanentAddState="+permanentAddState+"&permanentAddCountry="+permanentAddCountry+
"&permanentAddPin="+permanentAddPin+
"&phoneNo="+phoneNo+"&mobileNo="+mobileNo+"&emailId="+emailId+"&smsSubFlag="+smsSub+"&emailSubFlag="+emailSub+
"&noOfNominee"+noOfNominee+
"&nominee1Name="+nominee1Name+"&nominee1DOB="+nominee1DOB+
"&nominee1Relation="+nominee1Relation+"&nominee1Percent="+nominee1Percent+
"&nominee1Guardian="+nominee1Guardian+"&nominee1InvalidCondition="+nominee1InvalidCondition+
"&nominee2Name="+nominee2Name+"&nominee2DOB="+nominee2DOB+
"&nominee2Relation="+nominee2Relation+"&nominee2Percent="+nominee2Percent+
"&nominee2Guardian="+nominee2Guardian+"&nominee2InvalidCondition="+nominee2InvalidCondition+
"&nominee3Name="+nominee3Name+"&nominee3DOB="+nominee3DOB+
"&nominee3Relation="+nominee3Relation+"&nominee3Percent="+nominee3Percent+
"&nominee3Guardian="+nominee3Guardian+"&nominee3InvalidCondition="+nominee3InvalidCondition+	
"&bankAcntNo="+bankAcntNo+"&empBankName="+empBankName+"&empBankBranchName="+empBankBranchName+"&empBankBranchAddress="+empBankBranchAddress+
"&empBankPinCode="+empBankPinCode+"&bankstate="+bankstate+"&IfscCode="+IfscCode+	
"&dispNameFlag="+dispName+"&dobProofFlag="+dobProof+"&eduQualFlag="+eduQual+"&incomeRangeFlag="+incomeRange+"&flag=sevarthId";	
 //alert(uri);
 
	document.formS1Form.action= url+uri;
	document.formS1Form.submit();
	showProgressbar("Saving Form S1 Form Details...");
       
 

}

function copyPresentPermAdd()
{
	var presentFlat=document.getElementById("presentAddFlatNo").value.trim();
	var presentBuilding=document.getElementById("presentAddBuilding").value.trim();
	var presentTaluka=document.getElementById("presentAddTaluka").value.trim();
	var presentAddLandmark=document.getElementById("presentAddLandmark").value.trim();
	var presentDist=document.getElementById("presentAddDist").value.trim();
	var presentPin=document.getElementById("presentAddPin").value.trim();
	var presentAddState=document.getElementById("presentAddState").value.trim();
	var presentAddCountry=document.getElementById("presentAddCountry").value.trim();
	if((presentFlat=='' || presentBuilding=='' || presentTaluka=='' || presentDist=='' || presentPin==''))
	{
		alert('Please fill all mandatory fields in present address.');
		document.getElementById('presentSamePerm').checked=false;
		document.getElementById('presentNotSamePerm').checked=false;
		return false;
	}
	if(document.getElementById('presentSamePerm').checked) 
		{
			//alert('present address is same as permanent');
		document.getElementById("permanentAddFlatNo").readOnly=true;
		document.getElementById("permanentAddBuilding").readOnly=true;
		document.getElementById("permanentAddTaluka").readOnly=true;
		document.getElementById("permanentAddDist").readOnly=true;
		document.getElementById("permanentAddState").readOnly=true;
		document.getElementById("permanentAddCountry").readOnly=true;
		document.getElementById("permanentAddPin").readOnly=true;

		document.getElementById("permanentAddFlatNo").value=presentFlat;
		document.getElementById("permanentAddBuilding").value=presentBuilding;
		document.getElementById("permanentAddTaluka").value=presentTaluka;
		document.getElementById("permanentAddLandmark").value=presentAddLandmark;
		document.getElementById("permanentAddDist").value=presentDist;
		document.getElementById("permanentAddState").value=presentAddState;
		document.getElementById("permanentAddCountry").value=presentAddCountry;
		document.getElementById("permanentAddPin").value=presentPin;
		}
	else if(document.getElementById('presentNotSamePerm').checked) 
		{
		
			//alert('present address is not same as permanent');
		document.getElementById("permanentAddFlatNo").readOnly=false;
		document.getElementById("permanentAddBuilding").readOnly=false;
		document.getElementById("permanentAddTaluka").readOnly=false;
		document.getElementById("permanentAddLandmark").readOnly=false;
		document.getElementById("permanentAddDist").readOnly=false;
		document.getElementById("permanentAddState").readOnly=false;
		document.getElementById("permanentAddPin").readOnly=false;

		document.getElementById("permanentAddFlatNo").value='';
		document.getElementById("permanentAddBuilding").value='';
		document.getElementById("permanentAddTaluka").value='';
		document.getElementById("permanentAddLandmark").value='';
		document.getElementById("permanentAddDist").value='';
		document.getElementById("permanentAddState").value='';
		document.getElementById("permanentAddPin").value='';
		}
	return false;
}
function onlyNos(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    catch (err) {
        alert(err.Description);
    }
}



function validatePresentPin(pin)
{
	var pinCode=pin.value;
	if(pinCode.length < 6)
	{
		alert('Pin code should be of length 6.\nPlease enter correct Pin code.');
		pin.value='';
		return false;
	}	

		if(pinCode.charAt(0)!='4')
	{
		alert('Please enter correct Pin code.');
		pin.value='';
		return false;
	}
	return false;
}

function validatePermanentPin(pin)
{
			var pinCode=pin.value;
			if(pinCode.length < 6)
			{
				alert('Pin code should be of length 6.\nPlease enter correct Pin code.');
				pin.value='';
				return false;
			}	
			if(pinCode.charAt(0)=='0')
			{
				alert('Please enter correct Pin code.');
				pin.value='';
				return false;
			}
			return false;
		}

function validatePhoneSTDCode(phonestdcode)
{
	var PhoneSTDCode=phonestdcode.value;
	if(PhoneSTDCode.length != 3)
	{
		alert('Phone STD code should be of length 3.\nPlease enter correct Phone STD code.');
		phonestdcode.value='';
		return false;
	}	
	if(PhoneSTDCode.charAt(0)!='0')
	{
		alert('Please enter correct Phone STD code.');
		phonestdcode.value='';
		return false;
	}
	return true;
}
function validatePhoneNo(phone)
{
	var phoneNo=phone.value;
	if(phoneNo.length != 10)
	{
		alert('Phone number should be of length 8.\nPlease enter correct Phone Number.');
		phone.value='';
		return false;
	}	
	if(phoneNo.charAt(0)=='0')
	{
		alert('Please enter correct phone number.');
		phone.value='';
		return false;
	}
	return true;
}		
function validateMobileNo(mobile)
{
	var mobileNo=mobile.value;
	if(mobileNo.length != 10)
	{
		alert('Mobile number should be of length 10.\nPlease enter correct Mobile Number.');
		mobile.value='';
		return false;
	}	
	if(mobileNo.charAt(0)!='7' && mobileNo.charAt(0)!='8' && mobileNo.charAt(0)!='9')
	{
		alert('Please enter correct Mobile number.');
		mobile.value='';
		return false;
	}
	return true;
}
function validateEmail(email)
{
	//alert('email validation');
	var emailId=email.value;
	var atpos = emailId.indexOf("@");
    var dotpos = emailId.lastIndexOf(".");
    if (atpos< 1 || dotpos<atpos+2 || dotpos+2>=emailId.length) 
	{
        alert("Please enter valid email id.");
        email.value='';
        return false;
    }	
    return false;
}
function validateDOBAndMinorGuardianDtls(dob,noOfNominee)
{
	var currentDate=document.getElementById("currentDate");
	compareDatesAndEraseFirst(dob,currentDate,'Date of birth should be less than Current Date.','<');
	var parsedDOB=dob.value.split('/');
	var curd = new Date(curyear,curmon-1,curday);
	var cald = new Date(parsedDOB[2],parsedDOB[1]-1,parsedDOB[0]);
	var diff = Date.UTC(curyear,curmon,curday,0,0,0) - Date.UTC(parsedDOB[2],parsedDOB[1],parsedDOB[0],0,0,0);
	var dife = datediff(curd,cald);
	//alert('years: '+dife[0]);
	//alert('months: '+dife[1]);
	//alert('days: '+dife[2]);
	var age=dife[0];
	if(age<18)
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=false;
		alert('Please enter guardian details(name), for minor nominee.');
		document.getElementById("nominee"+noOfNominee+"Guardian").value='';
	}
	else
	{
		document.getElementById("nominee"+noOfNominee+"Guardian").readOnly=true;
		document.getElementById("nominee"+noOfNominee+"Guardian").value='NA';
	}
}
function enableNomineeFields(nominees)
{
	
	if(nominees == '1')
	{
	 

		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;

		//for nominee 2
		document.getElementById("nominee2Name").value='NA';
		document.getElementById("nominee2DOB").value='';
		document.getElementById("nominee2RelationSelect").selected=true;
		document.getElementById("nominee2Percent").value='0';
		document.getElementById("nominee2Guardian").value='NA';
		document.getElementById("nominee2InvalidCondition").value='NA';

		document.getElementById("nominee2Name").readOnly=true;
		document.getElementById("nominee2DOB").readOnly=true;
		document.getElementById("nominee2Relation").disabled=true;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=true;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=true;

		//for nominee 3
		document.getElementById("nominee3Name").value='NA';
		document.getElementById("nominee3DOB").value='';
		document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';

		document.getElementById("nominee3Name").readOnly=true;
		document.getElementById("nominee3DOB").readOnly=true;
		document.getElementById("nominee3Relation").disabled=true;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=true;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=true;
	}


	if(nominees == '2')
	{
		

		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;

		//for nominee 2
		document.getElementById("nominee2Name").value='';
		document.getElementById("nominee2DOB").value='';
		//document.getElementById("nominee2RelationSelect").selected=true;
		document.getElementById("nominee2Percent").value='0';
		document.getElementById("nominee2Guardian").value='NA';
		document.getElementById("nominee2InvalidCondition").value='NA';

		document.getElementById("nominee2Name").readOnly=false;
		document.getElementById("nominee2DOB").readOnly=false;
		document.getElementById("nominee2Relation").disabled=false;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=false;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=false;

		//for nominee 3
		document.getElementById("nominee3Name").value='NA';
		document.getElementById("nominee3DOB").value='';
		document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';

		document.getElementById("nominee3Name").readOnly=true;
		document.getElementById("nominee3DOB").readOnly=true;
		document.getElementById("nominee3Relation").disabled=true;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=true;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=true;
	}


	if(nominees == '3')
	{
		document.getElementById("nominee1Name").readOnly=false;
		document.getElementById("nominee1DOB").readOnly=false;
		document.getElementById("nominee1Relation").disabled=false;//main drop down
		//document.getElementById("nominee1RelationSelect").readOnly=;//selected option
		document.getElementById("nominee1Percent").readOnly=false;
		document.getElementById("nominee1Guardian").readOnly=false;
		document.getElementById("nominee1InvalidCondition").readOnly=false;
		
		document.getElementById("nominee2Name").readOnly=false;
		document.getElementById("nominee2DOB").readOnly=false;
		document.getElementById("nominee2Relation").disabled=false;//main drop down
		//document.getElementById("nominee2RelationSelect").readOnly=;//selected option
		document.getElementById("nominee2Percent").readOnly=false;
		document.getElementById("nominee2Guardian").readOnly=true;
		document.getElementById("nominee2InvalidCondition").readOnly=false;

		//for nominee 3
		document.getElementById("nominee3Name").value='';
		document.getElementById("nominee3DOB").value='';
		//document.getElementById("nominee3RelationSelect").selected=true;
		document.getElementById("nominee3Percent").value='0';
		document.getElementById("nominee3Guardian").value='NA';
		document.getElementById("nominee3InvalidCondition").value='NA';

		document.getElementById("nominee3Name").readOnly=false;
		document.getElementById("nominee3DOB").readOnly=false;
		document.getElementById("nominee3Relation").disabled=false;//main drop down
		//document.getElementById("nominee3RelationSelect").readOnly=;//selected option
		document.getElementById("nominee3Percent").readOnly=false;
		document.getElementById("nominee3Guardian").readOnly=true;
		document.getElementById("nominee3InvalidCondition").readOnly=false;
	}
	
}
function onlyAlphabets(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}


function dblQuotesRmvr(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if ((charCode != 34)&&(charCode != 39)&&(charCode != 96))
            return true;
        else
            return false;
    }
    catch (err) {
        alert(err.Description);
    }
}
</script>



<script type="text/javascript">
var startyear = "1910";
var endyear = "2100";
var dat = new Date();
var curday = dat.getDate();
var curmon = dat.getMonth()+1;
var curyear = dat.getFullYear();


function checkleapyear(datea)
{
	if(datea.getYear()%4 == 0)
	{
		if(datea.getYear()% 10 != 0)
		{
			return true;
		}
		else
		{
			if(datea.getYear()% 400 == 0)
				return true;
			else
				return false;
		}
	}
	return false; 
} 

function DaysInMonth(Y, M) 
{
	with (new Date(Y, M, 1, 12)) 
	{
		setDate(0);
		return getDate();
	} 
} 

function datediff(date1, date2) 
{
	var y1 = date1.getFullYear(), m1 = date1.getMonth(), d1 = date1.getDate(),
			 y2 = date2.getFullYear(), m2 = date2.getMonth(), d2 = date2.getDate();
	if (d1 < d2) 
	{
		m1--;
		d1 += DaysInMonth(y2, m2);
	}
	if (m1 < m2) 
	{
		y1--;
		m1 += 12;
	}
	return [y1 - y2, m1 - m2, d1 - d2]; 
} 
</script>
<c:forEach items="${empDetails}" var="empDetail">

	<hdiits:form name="formS1Form" id="formS1Form"
		encType="multipart/form-data" validate="true" method="post">
		<input type="text" id="hdnEmpSevarthId" name="hdnEmpSevarthId"
			value="${EmpSevarthId}">
		<input type="text" id="hdnDDOCode" name="hdnDDOCode"
			value="${DDOCode}">
		<input type="text" id="currentDate" name="currentDate"
			value="${currentDate}">
		<input type="text" id="dsgnName" name="dsgnName" value="${dsgnName}">
		<input type="text" id="DcpsId" name="DcpsId" value="${DcpsId}">
		<input type="text" id="DtoCode" name="DtoCode" value="${DtoCode}">
		<input type="text" id="DDORegNo" name="DDORegNo" value="${DdoRegNO}">


		<center>
			<fieldset style="width: 95%;" class="tabstyle">
				<legend>Other Details </legend>
				<table class="radiomainTb">
					<tbody>
						<tr>
							<td width="25%" align="left">Display Name Flag (Name to be
								printed on PRAN Card)</td>
							<td width="25%" align="left"><label>Father Name</label> <input
								type="radio" id="dipsFatherNameFlag" name="dispNameFlag"
								value="F" checked="checked"> <label>Mother Name</label>
								<input type="radio" id="dipsMotherNameFlag" name="dispNameFlag"
								value="M"></td>
						</tr>
						<tr>
							<td width="25%" align="left">Date of Birth Proof</td>
							<td width="25%" align="left" colspan="2">AADHAAR Card <input
								type="radio" id="dobp" name="dobProof" value="150"> PAN
								Card <input type="radio" id="dobp" name="dobProof" value="119">
								Passport <input type="radio" id="dobp" name="dobProof"
								value="103"> Driving License <input type="radio"
								id="dobp" name="dobProof" value="104"> Voter ID <input
								type="radio" id="dobp" name="dobProof" value="107">
							</td>
						</tr>
						<tr>
							<td width="25%" align="left">Educational Qulification</td>
							<td width="25%" align="left">If no information available
								from Subscriber <input type="radio" id="NoInfo" name="eduQual"
								value="00"> Below SSC <input type="radio" id="BelowSSC"
								name="eduQual" value="01"> SSC <input type="radio"
								id="EduSSC" name="eduQual" value="02"> HSC <input
								type="radio" id="EduHsc" name="eduQual" value="03">
								Graduate <input type="radio" id="EduGraduate" name="eduQual"
								value="04"> Masters <input type="radio" id="EduMaster"
								name="eduQual" value="05"> Professionals (CA, CS, CMA
								etc.) <input type="radio" id="EduProfessional" name="eduQual"
								value="06">
							</td>
						</tr>
						<tr>
							<td width="25%" align="left">Income Range</td>
							<td width="25%" align="left" colspan="2">If no information
								available from Subscriber <input type="radio" id="NoInfoIncome"
								name="incomeRange" value="00"> Upto 1 Lac <input
								type="radio" id="IncomeOne" name="incomeRange" value="01">
								1 Lac to 5 Lac <input type="radio" id="IncomeFive"
								name="incomeRange" value="02"> 5 Lac to 10 Lac <input
								type="radio" id="IncomeTenLac" name="incomeRange" value="03">
								10 Lac to 25 Lac <input type="radio" id="IncomeTwenty"
								name="incomeRange" value="04"> 25 Lac and Above <input
								type="radio" id="IncomeAbve" name="incomeRange" value="05">
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</center>
		<br />
		<br />
		<center>
			<fieldset style="width: 95%;" class="tabstyle">
				<legend id="headingMsg">
					<b>Photo/Signature</b>
				</legend>

				<table class="table table-bordered">
					<tbody>
						<tr>
							<td width="25%"><label><span>Photo
										Description sds</span> &nbsp;&nbsp;<b><font color="red"
										size="4px;">*</font></b> </label></td>
							<td width="25%"><input type="file"
								name="photoAttachmentIdnew" maxlength="40"
								class="filetag  form-control" id="photoAttachmentIdnew"
								onchange="myFunction()"> <!--- <input type="file" id="myFile" multiple size="50" onchange="myFunction()"> -->
								<p id="demo">asssss</p> <input type="file" name="imagefile"
								id="imagefile">
								<button onclick="return fileValidation();" name="submit">Upload
									sss File</button></td>
							<td width="25%"><img id="imagePath" src="" alt="Photo"
								width="100" height="100"></td>
						</tr>
						<script type="text/javascript">
					 
				        function fileValidation() {
				            var fileInput = 
				                document.getElementById('imagefile');
				              
				            var filePath = fileInput.value;
				          
				            // Allowing file type
				            var allowedExtensions = 
				                    /(\.jpg|\.jpeg|\.png|\.gif)$/i;
				              
				            if (!allowedExtensions.exec(filePath)) {
				                alert('Invalid file type');
				                fileInput.value = '';
				                return false;
				            } 
				            else 
				            { 

					           // alert("as");
				            	doupload(fileInput);
				                // Image preview
				                /* if (fileInput.files && fileInput.files[0]) {
				                    var reader = new FileReader();
				                    reader.onload = function(e) {
				                        document.getElementById(
				                            'imagePreview').innerHTML = 
				                            '<img src="' + e.target.result
				                            + '"/>';
				                    };
				                      
				                    reader.readAsDataURL(fileInput.files[0]);
				                } */
				            }
				        }
				    
						function doupload(fileInput)
							{
								var fileInput=fileInput;
								//var uri = 'ifms.htm?actionFlag=validateFormS1ForEdit';
								var uri = 'ifms.htm?actionFlag=fileUpload';
							//	var image = document.getElementById("imagefile").value.files[0];
								var url = 'fileInput='+fileInput;
								var myAjax = new Ajax.Request(uri,
									       {
									        method: 'post',
									        asynchronous: false,
									        parameters:url,
									        onSuccess: function(myAjax) {

													//alert(myAjax);
									        	responseCheck(myAjax);
												
											},
									        onFailure: function()
									        			{ 
							  						alert('Something went wrong...');
							  					} 
									          } 
							);
							}
						function responseCheck(myAjax){

							var status;
							XMLDoc = myAjax.responseXML.documentElement;
							var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
							var checkFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
							//alert("checkFlag"+checkFlag);
							if(checkFlag=='correct')
							{
								status= true;
								 alert('all ok');
								 
							}
							else if(checkFlag=='wrong')
							{
								
								
								alert('Selected Sevarth Id\'s Form S1 has been already edited.');
								status= false;
							}
							return status;

							}
						/* function SavePhoto(e) 
						{
						      
						    var xhr = new XMLHttpRequest();
						    var formData = new FormData();
						    var photo = e.files[0];      
						    
						    formData.append("user", JSON.stringify(user));   
						    formData.append("photo", photo);
						    
						    xhr.onreadystatechange = state => { console.log(xhr.status); } // err handling
						    xhr.timeout = 5000;
						    xhr.open("POST", '/upload/image'); 
						    xhr.send(formData);
						} */
						</script>


						<!--  <script type="text/javascript" src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script> -->
						<td ng-app="myApp">
						<td width="25%"><label><span>Signature
									Description </span>&nbsp;&nbsp;<b><font color="red" size="4px;">*</font></b></label></td>
						<td width="25%" ng-controller="myCtrl"><input type="file"
							file-model="myFile" />
							<button ng-click="uploadFile()">upload me</button> <script
								type="text/javascript">
         // module setup
        // var myApp = angular.module('myApp', []);      
         //directive setup    
        /*  myApp.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function() {
                     scope.$apply(function() {
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);
         // CONTROLLER  setup 
         myApp.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload) {
             $scope.uploadFile = function() {
                
                var file = $scope.myFile;
                console.log('file is ' );
                console.dir(file);
                var uploadUrl = "D:/outputnps/upload/";
                fileUpload.uploadFileToUrl(file, uploadUrl);
             };
          }]);

         //services setup
         myApp.service('fileUpload', ['$https:', function ($https) {
            this.uploadFileToUrl = function(file, uploadUrl) {
               var fd = new FormData();
               fd.append('file', file);
            
               $https:post(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               })
               .success(function() { alter ("success");
               })
               .error(function() {
            	   alter ("Error");
               });
            }
         }]); */
         
      </script></td>
						<td width="25%"><img id="imagePathSign" src="" alt="sign"
							width="100" height="100"></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</center>
		<table width="100%">
			<tr>
				<td width="50%" align="right"><input class="buttontag"
					type="button" value="Reset" onclick="resetForm();"></td>
				<td width="50%" align="left"><input class="buttontag"
					id="btnSubmit" type="button" value="Submit Form"
					onclick="submitForm();" /></td>
			</tr>
		</table>

		<form action="" target="" method="">
			<FIELDSET id=Photo class=fieldGroupTag>
				<P>
					<SPAN>Photo </SPAN>
				</P>
				<DIV style="WIDTH: 100%; DISPLAY: block">
					<TABLE id=formTable1Photo cellSpacing=1 cellPadding=1 width="100%"
						align=left border=0>
						<TBODY>
							<TR>
								<TD><LABEL id=FILE_DESCRIPTION class=captionTag>File
										Description</LABEL>&nbsp; <INPUT id=descPhoto class=texttag readOnly
									maxLength=40 size=40 value=Photo name=descPhoto> <INPUT
									type=hidden value=0 name=cntPhoto>&nbsp;</TD>
								<TD id=formTable1FILETDPhoto><LABEL id=ATTACH_FILE
									class=captionTag>Attach File</LABEL> &nbsp; <INPUT
									onkeypress="return checkValue();" id=importFilePhoto
									class=filetag maxLength=40 type=file name=importFilePhoto>

								</TD>
								<INPUT id=attachmentName type=hidden value=Photo
									name=attachmentName>
								<INPUT id=attachmentType type=hidden value=Document
									name=attachmentType>
								<INPUT id=Photo_rowNumber type=hidden name=Photo_rowNumber>
								<TD>
									<TABLE align=center>
										<TBODY>
											<TR>
												<TD rowSpan=2><INPUT onclick=startStatusCheckPhoto()
													id=submitButtonPhoto class=buttontag style="WIDTH: 100px"
													maxLength=3 size=3 type=button value=UploadPhoto
													name=submitButtonPhoto></TD>
												<!-- 
	// START : BIOMETRIC RELATED CODE 
	// ADDED BY : 202414
 -->
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
							<TR>
								<TD colSpan=3>
									<DIV id=statusPhoto style="WIDTH: 100%"></DIV>
									<BR>
									<TABLE id=myTablePhoto class=datatable>
										<TBODY>
											<TR>
												<!-- 	<td><center>Sr No</center></td>     -->
												<TD class=datatableheader>
													<CENTER>
														<LABEL id=DESCRIPTION class=captionTag>Description</LABEL>
													</CENTER>
												</TD>
												<TD class=datatableheader>
													<CENTER>
														<LABEL id=FILE_NAME class=captionTag>File Name</LABEL>
													</CENTER>
												</TD>
												<TD class=datatableheader>
													<CENTER>
														<LABEL id=ACTIONS class=captionTag>Actions</LABEL>
													</CENTER>
												</TD>
											</TR>
										</TBODY>
									</TABLE>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
					<BR>
				</DIV>

			</FIELDSET>
		</form>
		</fieldset>
	</hdiits:form>
</c:forEach>
<SCRIPT language=JavaScript type=text/javascript>
addButtonFunctionPhoto = true;
removeJSFunctionPhoto = true;
 
 

function startStatusCheckPhoto()
{
	
	formName = "DCPSForm";
	
	var lastRow = document.getElementById('myTablePhoto').rows.length; 
		if(Number(lastRow)<=1)
		{
		if(addButtonFunctionPhoto)
		{
		
  		formActionPhoto = window.document.forms[formName].action;  		
  		formTargetPhoto = window.document.forms[formName].target;
  		formMethodPhoto = window.document.forms[formName].method;
  		
  		
  		formNameGlobalVarPhoto = formName;
    	$('submitButtonPhoto').disabled = true;
    	
    	try
    	{
				

	  		var fileNameValue = document.forms[formName].importFilePhoto.value;
	  		if(isFieldValueNull(fileNameValue) || fileNameValue=='' )
	  		{
	  			alert('File Name is Required');
	  			document.forms[formName].importFilePhoto.focus();
	  			throw 'exception';    		
	  		}
	  		var result = fileNameValue.lastIndexOf(".");
		    var fileExtention = fileNameValue.substring(result+1, fileNameValue.length);
		    
			    if(fileExtention.toLowerCase()=='exe' || fileExtention.toLowerCase()=='com' || fileExtention.toLowerCase()=='bat' || fileExtention.toLowerCase()=='xls' || fileExtention.toLowerCase()=='doc'  || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='pif' || fileExtention.toLowerCase()=='sys' || fileExtention.toLowerCase()=='sh' || fileExtention.toLowerCase()=='txt' || fileExtention.toLowerCase()=='pdf' )
			    {
					alert('AttachMent Type '+fileExtention.toUpperCase()+' Does Not allowed For Photo ');
					document.forms[formName].importFilePhoto.focus();			    
		  			throw 'exception';		    	
			    }
		    
		        	
			updaterPhoto = new Ajax.PeriodicalUpdater(
                                'statusPhoto',
                                'Upload',
                                {asynchronous:true, frequency:1, method: 'get', parameters: 'c=status', onFailure: reportErrorPhoto});
                 
        	window.document.forms[formName].target="target_uploadPhoto";
   
        	// form enctype is not supported here  ... it should be written within within the form tag
			// window.document.forms[formName].enctype="multipart/form-data";
			
			// the form method must be get here otherwise the form wont be submitted
 			window.document.forms[formName].method="post";
 			
 			
 			var rowNumber = '';
 			
 			// START : CODE FOR GETTING VAUES OF FILE DESCRIPTION 
 			// ADDED BY : 202414  
 			//	alert ("OBJECT ");
 			var fileDescription = null ;
 			var obj = document.forms[formName].descPhoto;
 			//	alert (obj);
 			//	alert (obj.type) ;
 			if (obj.type == 'text')
 			{
 				fileDescription = obj.value ;	
 			}
 			else 
 			{
 				var selIndex = obj.selectedIndex;
 				//	alert ("SELECTED INDEX :"+selIndex);
 				if (selIndex == 0 )
 				{
 					//	alert ('Please select file description ');
 					alert('Please select file description.');
 					obj.focus();
 					throw 'File description combo is not selected ';
 				}
 				fileDescription = obj.options[selIndex].text  ;	
 			}
 			//	alert ("file description :"+fileDescription);
			// END : CODE FOR GETTING VAUES OF FILE DESCRIPTION 
			
// 			var fileDescription = document.forms[formName].descPhoto.value;
 			
 			window.document.forms[formName].action="Upload?attachmentNameHidden=Photo&fileDescription="+fileDescription+"&rowNumber="+rowNumber;
 			
        	window.document.forms[formName].submit();
        	
        	window.document.forms[formName].action = formActionPhoto;
        	window.document.forms[formName].target = formTargetPhoto;
        	window.document.forms[formName].method = formMethodPhoto;
        	
         }
		catch(ePhoto)
		{
			$('submitButtonPhoto').disabled = false;
		}
		
		
		
    	return true;
    	}
    	else
    	{
    		return false;
    	}
   	}
   	else
   	{
   		alert("You Cant Add MoreThan One Photo");
		return false;
   	}
}

function reportErrorPhoto(request)
{
    $('submitButtonPhoto').disabled = false;

    $('statusPhoto').innerHTML = '<div class="error"><b>Error communicating with server. Please try again.</b></div>';
}

function killUpdatePhoto(message)
{
    $('submitButtonPhoto').disabled = false;

    updaterPhoto.stop();
    if(message != '')
    {
      $('statusPhoto').innerHTML = '<div class="error"><b>' + message + '</b></div>';
 	  remove('importFilePhoto');
 	  var obj = document.forms[formNameGlobalVarPhoto].descPhoto;
 	  // START : CODE FOR RESET FILE DESCRIPTION OBJECT
 	  // ADDED BY  : 202414
 	  if (obj.type == 'text')
 	  {
			document.forms[formNameGlobalVarPhoto].descPhoto.value = '';		
 	  }
 	  else
 	  {
 	  		//	resetBioCmbBox (obj,'') ;
 	  		obj.selectedIndex = 0 ;
 	  }
 	  // END : CODE FOR RESET FILE DESCRIPTION OBJECT
    }
    else
    {
     	 new Ajax.Updater('statusPhoto',
                     'Upload',
                     {asynchronous:true, method: 'get', parameters: 'c=status', onFailure: reportErrorPhoto, onComplete: showResponsePhoto});
                     
    }
} // end of function killUpdate

function showResponsePhoto()
{
			var fileElementLocal = document.getElementById('importFilePhoto').value;
			if(fileElementLocal != '')
			{
	  			insRowPhoto();
  			}
  			
}
  
function insRowPhoto()
{	
		counterPhoto++;
	  	var xPhoto=document.getElementById('myTablePhoto').insertRow()
 		  	
 		  	
	    var col1Photo=xPhoto.insertCell(0);
	    var col2Photo=xPhoto.insertCell(1);
	    var col3Photo=xPhoto.insertCell(2);

	    document.forms[formNameGlobalVarPhoto].cntPhoto.value = counterPhoto;

	    var fileName = document.forms[formNameGlobalVarPhoto].importFilePhoto.value;
	    fileName = fileName.replace(/\\\\/g, '/') ;
	    var result = fileName.lastIndexOf("/");
	    fileName = fileName.substring(result+1, fileName.length);
	    
		if(document.forms[formNameGlobalVarPhoto].descPhoto.value != "")
		{
			// 202414 
			var obj = document.forms[formNameGlobalVarPhoto].descPhoto;
 			if (obj.type == 'text')
 			{
 				fileDescription = obj.value ;	
 			}
 			else 
 			{
 				var selIndex = obj.selectedIndex;
 				// HERE NOT NEEDED TO CHECK DEFAULT SELECTION 
 				// alert ("SELECTED INDEX :"+selIndex);
 				fileDescription = obj.options[selIndex].text  ;	
 			}
 			//	alert ("file description :"+fileDescription);
			
			col1Photo.innerHTML= fileDescription ;
//	    	col1Photo.innerHTML=document.forms[formNameGlobalVarPhoto].descPhoto.value;
	    }
	    else
	    {
		    result = fileName.lastIndexOf(".");
		    var fileNameDesc = fileName.substring(0, result);
	    	//col1Photo.innerHTML = "<B>N.A</B>";
	    	col1Photo.innerHTML = fileNameDesc;
	    }	    
	    
	    col2Photo.innerHTML=fileName;
	    
	    
		col3Photo.innerHTML='<a class="removeLink" onClick="delRowPhoto(this)" target="target_uploadPhoto">Remove</a>'; 			
		

		var obj = document.forms[formNameGlobalVarPhoto].descPhoto
		if (obj.type == 'text')
		{
			document.forms[formNameGlobalVarPhoto].descPhoto.value = '';		
		}
		else
		{
			//alert('resetBioCmbBox  called');
			obj.selectedIndex = 0 ;
			// resetBioCmbBox (obj ,'')
		}
		
		viewRowPhoto(document.getElementById('myTablePhoto'))	
		remove('importFilePhoto');
} // end of function insRow
	function resetBioCmbBox (obj ,bioDeviceType)
	{
		//	alert ("bioDeviceType :"+bioDeviceType)	 ;
		obj.selectedIndex = 0 ;
	}
   
function delRowPhoto(rowNumber)
{
	var	src = document.getElementById("myTablePhoto");
	if(confirm('Whether you would like to delete the attachment?'))
	{
	
		 
		
		if(removeJSFunctionPhoto)
		{ 
		try
		{
			counterPhoto--;
			var rowPhoto = src.parentElement.parentElement;
			
			var rownum = rowPhoto.rowIndex;
			if(rownum > 0)
			{
				rownum--;
			}
			
			
			
			rownum = rownum - rowCountForEidtPhoto;
		
			ajaxfunctionPhoto(rownum,'Upload?attachmentNameHidden=Photo&removeElement=removeElementFromArrayList&elementNumber=');
			
			
			document.all("myTablePhoto").deleteRow(rowPhoto.rowIndex);
			document.getElementById('prewPhoto').innerHTML = "";
			if('descPhoto'=='descPhoto')
			{
			 	document.getElementById('descPhoto').value="Photo";
			}
			else
			{
				document.getElementById('descPhoto').value="Signature";
			}
		}
		catch(qq)
		{
				alert('error in del row ' + qq);
		}
		}
	}	
				
} // end of function delRow
  
function viewRowPhoto(src,rowNumber)
{
	if(document.getElementById('prewPhoto'))
	{
		document.getElementById('prewPhoto').innerHTML = "";
	}
	var actionFlagPhoto = '';
	try
	{
		
		if(rowNumber == undefined) {
			rowNumber = '';
		}
	    var rowPhoto = src.parentElement.parentElement;
		var rowIndex = rowPhoto.rowIndex;
		// Row Count added for Attachment Multiple Add/Edit functionality 2007-10-11
		var rowCount = dbRowCountPhoto;
  		var urlValue = "ifms.htm?actionFlag=viewAttachmentForDCPS&attachmentUniqeName=Photo&rowNumber="+rowNumber+"&rowCount="+rowCount+"&rowIndex=";
		urlValue = urlValue + rowIndex;
		var height = 300;
		var width = 500;
		var urlstring = urlValue;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
		newWindow = window.open(urlstring, "viewAttachment", urlstyle);
		newWindow.close();
	 	var tempattachName = 'Photo';
  		document.getElementById('prew'+tempattachName).innerHTML = '<a href="#" onmouseout="hidImg()" id="'+tempattachName+'" name = "'+tempattachName+'"  onmouseover="showImg(this)"  ><img style="width: 180px;height: 150px;" src="'+urlValue+'"></a>';
  		var lThis = document.getElementById("myTable"+tempattachName); 
  		if(document.getElementById("hid"+tempattachName+"Url"))
  		{
  			document.getElementById("hid"+tempattachName+"Url").value = urlValue;
  		}
	}
	catch(qq)
	{
		alert('error in view row ' + qq);
	}

	
	document.forms[formNameGlobalVarPhoto].action = formActionPhoto;	
	if(document.getElementsByName('actionFlag').length > 0 )
	{
		document.forms[formNameGlobalVarPhoto].actionFlag.value = actionFlagPhoto;  		
	}
} 
function insRowEditPhoto()
{
		
		if(attachmentEditPhoto)
		{	
		
	}  		     			
}
function removeRowPhoto(removeURL,src)
{
	try
	{
		if(confirm('Whether you would like to delete the attachment?'))
		{
			
			 
			if(removeJSFunctionPhoto)
			{	
			counterPhoto--;
			var rowPhoto = src.parentElement.parentElement;
			ajaxfunctionRemoveURLPhoto(removeURL);
			var rowPhoto = src.parentElement.parentElement;
			rowCountForEidtPhoto = rowCountForEidtPhoto - 1;
			document.all("myTablePhoto").deleteRow(rowPhoto.rowIndex);
			}
			if(document.getElementById('prewPhoto'))
			{
					document.getElementById('prewPhoto').innerHTML = "";				
			}
		}
	}
	catch(qq)
	{
			alert('error in removeRow -->' + qq);
	}			
}
function ajaxfunctionRemoveURLPhoto(removeUrl)
{


		try
		{  

		xmlHttpPhoto=new XMLHttpRequest();    
		}
			catch (ePhoto)
			{    // Internet Explorer    
				try
			{

			    xmlHttpPhoto=new ActiveXObject("Msxml2.XMLHTTP");   

			}
			    catch (ePhoto)
			    {
				  try
				  {
				      //alert("here2");
					  xmlHttpPhoto=new ActiveXObject("Microsoft.XMLHTTP");        
				  }
				      catch (ePhoto)
				      {
					      alert("Your browser does not support AJAX!...Photo");        
					      return false;        
				      }
				 }
		}
	
	var urlPhoto = removeUrl;


	xmlHttpPhoto.onreadystatechange = function()
	{

		if (xmlHttpPhoto.readyState == 4) 
		{     
			if (xmlHttpPhoto.status == 200) 
			{       
			}

		}
	 }
	 
	xmlHttpPhoto.open("POST", encodeURI(urlPhoto) , false);    
	xmlHttpPhoto.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	
	xmlHttpPhoto.send(encodeURIComponent(null));


}	 
window.FILL_COMBO_BOX_TAB_WISE = false;
submitThisPageUsingAjax = false;

				  document.getElementById('descPhoto').value="Photo";
				  document.getElementById('descPhoto').readOnly =true;

				  
				  
				  function ajaxfunctionPhoto(rownum,removeUrl)
				  {
				  		try
				  		{  
				  		// Firefox, Opera 8.0+, Safari    

				  		xmlHttpPhoto=new XMLHttpRequest();    
				  		}
				  			catch (ePhoto)
				  			{    // Internet Explorer    
				  				try
				  			{

				  			    xmlHttpPhoto=new ActiveXObject("Msxml2.XMLHTTP");   

				  			}
				  			    catch (ePhoto)
				  			    {
				  				  try
				  				  {
				  					  xmlHttpPhoto=new ActiveXObject("Microsoft.XMLHTTP");        
				  				  }
				  				      catch (ePhoto)
				  				      {
				  					      alert("Your browser does not support AJAX!...Photo");        
				  					      return false;        
				  				      }
				  				 }
				  		}
				  	
				  	var urlPhoto = removeUrl + rownum;


				  	xmlHttpPhoto.onreadystatechange = function()
				  	{

				  		if (xmlHttpPhoto.readyState == 4) 
				  		{     
				  			if (xmlHttpPhoto.status == 200) 
				  			{
				  			
				  			
				  			}

				  		}
				  	 }
				  	xmlHttpPhoto.open("POST", encodeURI(urlPhoto) , false);    
				  	xmlHttpPhoto.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				  	
				  	xmlHttpPhoto.send(encodeURIComponent(null));

				  }	 // end of method ajaxfunction		

				  function handleResponsePhoto() 
				  {
				  			if ((requestObjectPhoto.readyState == 4) && (requestObjectPhoto.status == 200)) 
				  			{				
				  					alert(requestObjectPhoto.responseText);
				  			}
				  } // end of function handleResponsePhoto
				  				  
				</SCRIPT>