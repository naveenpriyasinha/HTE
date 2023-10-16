function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpQtrSearch").value;
	
	var url="./hrms.htm?actionFlag=getQuarterDtls&edit=x";
	
	document.EmpQtrInfo.action=url;
	document.EmpQtrInfo.submit();
}
function chkValue()
{
  	var empId=document.getElementById("Employee_ID_EmpQtrSearch").value;
	if(empId==""){
		alert("Please search the employee first");
		return false;
	}
	else {
		return true;
	}	
}
function submitFormAuto()
{
	if(chkValue())
	{
		document.EmpQtrInfo.submit();
	}
	return true;
}
