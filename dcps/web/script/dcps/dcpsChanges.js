
function getEmployees()
{
	var designationId = document.getElementById("cmbDesignation").value;
	var cmbChanges = document.getElementById("cmbChanges").value;
	
	var User = document.getElementById("User").value;
	var url = "ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+cmbChanges+"&User="+User;
	if(cmbChanges==700041)
		{
			url = url + "&Type=Office" + "&elementId=700163";
		}
	else
		{
			url = url + "&elementId=700112";
		}
	
	self.location.href=url;
	
}

function openChangesPage(EmpId)
{
	var combo1 = document.getElementById("cmbChanges"); 
	var cmbChanges = combo1.options[combo1.selectedIndex].text;
	var actionFlag;
	var UserType = document.getElementById("User").value;
	var designationId = document.getElementById("cmbDesignation").value;
	var changesType = document.getElementById("cmbChanges").value;
	
	if(cmbChanges == "Personal Details")
	{	
		actionFlag = "changesPersonalDetails";
	}
	
	if(cmbChanges == "Office Details")
	{	
		actionFlag = "changesOfficeDetails";
	}
	
	if(cmbChanges == "Other Details")
	{	
		actionFlag = "changesOtherDetails";
	}
	
	if(cmbChanges == "Nominee Details")
	{	
		actionFlag = "changesNomineeDetails";
	}
	
	if(cmbChanges == "Photo And Signature Details")
	{	
		actionFlag = "changesPhotoAndSignatureDetails";
	}
	
	var url = "ifms.htm?actionFlag="+actionFlag+"&EmpId="+EmpId+"&UserType="+UserType+"&designationId="+designationId+"&changesType="+changesType;
	//alert(url)
	self.location.href=url;

}

function openOfficeDetailsPage(dcpsEmpId,name,sevarthId)
{
	var designationId = document.getElementById("cmbDesignation").value;
	//self.location.href = "ifms.htm?actionFlag=changesOfficeDetails&EmpId="+EmpId+"&UserType=DDOAsst&changesType=700041"+"&hidDcpsId="+hidDcpsId+"&hidEmpName="+hidEmpName+"&hidBirthDate="+hidBirthDate+"&hidSevarthId="+hidSevarthId+"&hidName="+hidName;
	var url = "ifms.htm?actionFlag=changesOfficeDetails&EmpId="+dcpsEmpId+"&UserType=DDOAsst&changesType=700041"+"&hidSevarthId="+sevarthId+"&hidName="+name+"&elementId=700163"+"&FromSearchEmp=NO"+"&designationId="+designationId+"&Type=Office"+"FromChangesOfficeElement=YES";
	//var url = "ifms.htm?actionFlag=srchEmp&txtEmployeeId="+dcpsId+"&Type=Office";
	self.location.href=url;
}




