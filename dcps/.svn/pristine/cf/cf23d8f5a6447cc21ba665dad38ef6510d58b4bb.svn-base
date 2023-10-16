function loadBelowDetailsForChangeParent()
{
	var url="";
	var ddoCode = document.getElementById("cmbDdoCode").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	
	if(treasuryCode == -1)
	{
		alert('Please select Treasury');
		document.getElementById("radioChangePFDOptionDDO").checked = false;
		document.getElementById("radioChangePFDOptionEMP").checked = false;
		document.getElementById("radioChangePFDOptionDDOCODE").checked = false;
		return;
	}
	
	if(ddoCode == -1)
	{
		alert('Please select DDO');
		document.getElementById("radioChangePFDOptionDDO").checked = false;
		document.getElementById("radioChangePFDOptionEMP").checked = false;
		document.getElementById("radioChangePFDOptionDDOCODE").checked = false;
		return;
	}
	
	if(document.getElementById("radioChangePFDOptionDDO").checked)
	{
		url="ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&viewPageFor=DDO&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
		self.location.href = url ;
	}
	if(document.getElementById("radioChangePFDOptionEMP").checked)
	{
		url="ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&viewPageFor=EMP&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
		self.location.href = url ;
	}
	if(document.getElementById("radioChangePFDOptionDDOCODE").checked)
	{
		url="ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&viewPageFor=DDOCODE&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
		self.location.href = url ;
	}
}

function clearBelowDetails()
{
	var selectedTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	url = "ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&treasuryCode="+selectedTreasuryCode;
	if(document.getElementById("radioChangePFDOptionEMP").checked ||
			document.getElementById("radioChangePFDOptionDDOCODE").checked ||
			document.getElementById("radioChangePFDOptionDDOCODE").checked
	  )
	{
		self.location.href = url ;
	}
}

function changeParentDeptOfDDO()
{
	var newAdminDept = document.getElementById("cmbAdminDept").value;
	var newFieldDept = document.getElementById("cmbFieldDept").value;
	var ddoCode = document.getElementById("cmbDdoCode").value;
	if(newAdminDept == -1)
	{
		alert('Please Select Admin Department');
		return ;
	}
	if(newFieldDept == -1)
	{
		alert('Please Select Field Department');
		return ;
	}
	
	var uri="ifms.htm?actionFlag=changeParentDeptOfDDO";
	var url="newAdminDept="+newAdminDept+"&newFieldDept="+newFieldDept+"&ddoCode="+ddoCode;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForChangePFDDDO(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForChangePFDDDO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lBlFlag=='true')
	{
		alert("DDO's Parent Field Department and HOD are changed.");
		self.location.reload(true);
		//self.location.href = 'ifms.htm?actionFlag=loadChangeParentDept';
	}
}

function displayPFDForEmp(empId)
{
	var ddoCode = document.getElementById("cmbDdoCode").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	url = "ifms.htm?actionFlag=popUpEmpPFDChangeWndw&elementId=700079&empId="+empId+"&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
	self.location.href=url;
}

function goBackToMainChangePFD()
{
	var ddoCode = document.getElementById("hidDdoCode").value;
	var treasuryCode =  document.getElementById("hidTreasuryCode").value;
	url="ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&viewPageFor=EMP&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
	self.location.href=url;
}

function changeParentDeptOfEmp()
{
	var newAdminDept = document.getElementById("cmbAdminDept").value;
	var newFieldDept = document.getElementById("cmbFieldDept").value;
	var empId = document.getElementById("hidEmpId").value;
	
	var txtBirthDate = document.getElementById("txtBirthDate").value;
	var txtUIDNo1 = document.getElementById("txtUIDNo1").value; 
	var txtUIDNo2 = document.getElementById("txtUIDNo2").value; 
	var txtUIDNo3 = document.getElementById("txtUIDNo3").value; 
	
	if(newAdminDept == -1)
	{
		alert('Please Select Admin Department');
		return ;
	}
	if(newFieldDept == -1)
	{
		alert('Please Select Field Department');
		return ;
	}
	
	var uri="ifms.htm?actionFlag=changeParentDeptOfEmp";
	var url="newAdminDept="+newAdminDept+"&newFieldDept="+newFieldDept+"&empId="+empId+"&txtBirthDate="+txtBirthDate+"&txtUIDNo1="+txtUIDNo1+"&txtUIDNo2="+txtUIDNo2+"&txtUIDNo3="+txtUIDNo3 ;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForChangePFDEmp(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForChangePFDEmp(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lBlFlag=='true')
	{
		alert("Employee's Parent Field Department and HOD are changed.");
		var ddoCode = document.getElementById("hidDdoCode").value;
		var treasuryCode =  document.getElementById("hidTreasuryCode").value;
		self.location.href="ifms.htm?actionFlag=loadChangeParentDept&elementId=700079&viewPageFor=EMP&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
	}
}

function changeDDOCode()
{
	var empIds = "" ;
	var finalSelectedEmployee =0;
	var totalEmployees = document.getElementById("hdnCounter").value ;	
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			finalSelectedEmployee = k ;	
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employee');
		return ; 
	}

	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				empIds = empIds +  document.getElementById("checkbox"+i).value ;
			}
			else
			{
				empIds = empIds +  document.getElementById("checkbox"+i).value + "~" ;
			}
		}
	}
	
	var newDDOCode = document.getElementById("newDDOCode").value;
	var newTreasuryCode = document.getElementById("newDDOCode").value;
	
	if(newDDOCode == "")
	{
		alert('Please enter new DDO Code');
		return false;
	}
	
	if(newTreasuryCode == -1)
	{
		alert('Please select treasury');
		return false;
	}
	
	var uri="ifms.htm?actionFlag=changeDDOCode";
	var url="newDDOCode="+newDDOCode+"&newTreasuryCode="+newTreasuryCode+"&empIds="+empIds ;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForChangeDDOCode(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
}

function getDataStateChangedForChangeDDOCode(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(lBlFlag=='true')
	{
		alert("DDO Code for selected DDO and employees has changed.");
		//var ddoCode = document.getElementById("hidSelectedDDOCode").value;
		//var treasuryCode =  document.getElementById("hidSelectedTreasuryCode").value;
		self.location.reload(true);
		//self.location.href="ifms.htm?actionFlag=loadChangeParentDept&viewPageFor=DDOCODE&ddoCode="+ddoCode+"&treasuryCode="+treasuryCode;
	}
}
