function submitForm()
{
	var empId=document.getElementById("Employee_ID_miscSearch").value;
	
	var url="./hrms.htm?actionFlag=getMiscData";
	
	document.miscRecover.action=url;
	document.miscRecover.submit();
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_miscSearch").value;
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
		document.miscRecover.submit();
	}
	return true;
}
