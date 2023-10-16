function init(){
	var msg = document.getElementById("txtAlert").value;
	var reqType = document.getElementById("hidReqType").value;
	var	requestName = "";
	if(reqType == "800028"){
		requestName="Computer Advance";
	}else if(reqType == "800029"){
		requestName="House Bulilding Advance";
	}else if(reqType == "800030"){
		requestName="Vehicle Advance";
	}
	if(msg != "" && msg!=null){
		if(msg == "InvalidEmp")
			alert("Invalid Employee Code Or Employee Name");
		else if(msg == "OneReqExists")
			alert("A loan request cannot be raised as this employee has already taken "+requestName);
		else if(msg == "Pending")
			alert("For this Employee a loan request cannot be raised as one request of "+requestName+" is in Process");
		else if(msg == "Eligibility")
			alert("This Employee is not Eligible for "+requestName+" because his service period is less than 5 years");
		else if(msg == "GradeDEmp")
			alert("This Employee is in Group D So Employee is not Eligible for "+requestName);
		else if(msg == "BasicPay")
			alert("This Employee is not Eligible for "+requestName+" because his Basic Pay is Less than 4,600");
		else if(msg == "BasicPayGroupD")
			alert("This Employee is of Group D and Employee is not Eligible for "+requestName+" because his Basic Pay is Greater than 5,000");
		else if(msg == "PayInPBForGroupA")
			alert("This Employee is of Group A and Employee is not Eligible for "+requestName+" because his Pay in Pay Band is Less than 8,650");
		else if(msg == "PayInPBForGroupD")
			alert("This Employee is of Group D and Employee is not Eligible for "+requestName+" because his Pay in Pay Band is Greater than 2,800");
		else if(msg == "Accept")
			alert("Request Accepted Successfully");
	}
	if(msg != "Accept"){
		document.getElementById("txtSevaarthId").value = document.getElementById("hidSevaarthId").value;
		document.getElementById("txtEmployeeName").value = document.getElementById("hidEmpName").value;
		if(reqType!=""){
			document.getElementById("cmbLoanType").value = reqType;
		}
	}
}
function validateCriteria()
{
	var sevaarthId = document.getElementById("txtSevaarthId").value;
	var empName=document.getElementById("txtEmployeeName").value;
	var reqType=document.getElementById("cmbLoanType").value;

	if(sevaarthId.trim() == "" && empName.trim() == "")
	{
		alert('Please enter any one value to search');
		return false ;

	}
	if(reqType == "-1"){
		alert('Please select a request type');
		document.getElementById("cmbLoanType").focus();
		return false;
	}
	return true ;
}

function submitSearchDetails()
{
	if(!validateCriteria())
	{
			return false ;
	}
	var criteria;
	var txtSevaarthId=document.getElementById("txtSevaarthId").value;
	var txtEmployeeName=document.getElementById("txtEmployeeName").value;
	var requestType = document.getElementById("cmbLoanType").value;
	var userType = document.getElementById("txtUserType").value;
	
	if(txtEmployeeName == ""){
		criteria=1;
	}
	else if(txtSevaarthId == ""){
		criteria=2;
	}
	else{
		criteria=3;
	}
	var elementId="";
	if(userType=="DEO"){
		elementId = 800011;
	}else{
		elementId = 800025;
	}
	var url = "ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId="+elementId+"&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="
	+txtEmployeeName+"&criteria="+criteria+"&requestType="+requestType+"&userType="+userType;
	document.EmployeeSearchForm.action = url ;
	document.EmployeeSearchForm.submit();
	

}
function clearAllfields()
{
	document.getElementById("txtSevaarthId").value= "";
	document.getElementById("txtEmployeeName").value= "";
	document.getElementById("cmbLoanType").value= -1;
}
function setFocusOnRequestType(){
	document.getElementById("cmbLoanType").focus();
}
function getHomePage(){
	self.location.href = "ifms.htm?actionFlag=getHomePage";
}