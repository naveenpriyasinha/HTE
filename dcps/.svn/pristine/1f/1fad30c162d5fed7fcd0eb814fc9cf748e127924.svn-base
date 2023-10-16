function submitForm()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	
	var url="./hrms.htm?actionFlag=getVPFView";
	
	document.vpfDtlsView.action=url;
	document.vpfDtlsView.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		return true;
	}
	
}
function submitFormAuto()
{
	if(chkValue())
	{
		document.vpfDtlsView.submit();
	}
	return true;
}