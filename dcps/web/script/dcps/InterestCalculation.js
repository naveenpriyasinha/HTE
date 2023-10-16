function reloadBelowDetails()
{
	if(document.getElementById("radioForTreasury").checked
			|| document.getElementById("radioForDDO").checked
			||  document.getElementById("radioForEmp").checked)
	{
		var yearId = document.getElementById("cmbYear").value;
		var url = "ifms.htm?actionFlag=loadInterestCalculation&elementId=700110&yearId="+yearId;
		self.location.href = url ;
	}
}
function calculateInterestForYear()
{
	showProgressbar();
	var treasuryCode="";
	var ddoCode="";
	var empIds="";
	var interestFor="";
	
	var yearId = document.getElementById("cmbYear").value ;
	if(yearId == -1)
	{
		alert('Please select year');
		hideProgressbar();
		return false;
	}
	
	if(document.getElementById("radioForTreasury").checked)
	{
		interestFor = "Treasury";
		treasuryCode = document.getElementById("cmbTreasury").value ;
		if(treasuryCode == -1)
		{
			alert('Please select Treasury');
			hideProgressbar();
			return false;
		}
	}
	
	if(document.getElementById("radioForDDO").checked)
	{
		interestFor = "DDO";
		treasuryCode = document.getElementById("cmbTreasury").value ;
		ddoCode = document.getElementById("cmbDDOCode").value;
		if(treasuryCode == -1)
		{
			alert('Please select Treasury');
			hideProgressbar();
			return false;
		}
		if(ddoCode == -1)
		{
			alert('Please select DDO');
			hideProgressbar();
			return false ;
		}
	}
	
	if(document.getElementById("radioForEmp").checked)
	{
		interestFor = "Emp";
		treasuryCode = document.getElementById("cmbTreasury").value ;
		ddoCode = document.getElementById("cmbDDOCode").value;
		var finalSelectedEmployee=0;
		
		if(treasuryCode == -1)
		{
			alert('Please select Treasury');
			hideProgressbar();
			return false;
		}
		if(ddoCode == -1)
		{
			alert('Please select DDO');
			hideProgressbar();
			return false ;
		}
		
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
			alert('Please select at least one employee to calculate interest');
			hideProgressbar();
			return false;
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
	}
	
	var uri = "ifms.htm?actionFlag=calculateDCPSYearlyInterest";
	var url = "yearId="+yearId+"&treasuryCode="+treasuryCode+"&ddoCode="+ddoCode+"&empIds="+empIds+"&interestFor="+interestFor;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForCalcInterest(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function dataStateChangedForCalcInterest(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc == null)
	{
		hideProgressbar();
	}
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

		var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		var interestFor = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
								
		if (successFlag=='true') {
			if(interestFor == 'Treasury')
			{
				alert('Interest has been calculated for selected year and treasury.');
			}
			else if(interestFor == 'DDO')
			{
				alert('Interest has been calculated for selected year and DDO.');
			}
			else if(interestFor == 'Emp')
			{
				alert('Interest has been calculated for selected year and Employees.');
			}
			else
			{
				alert('Interest has been calculated.');
			}
			hideProgressbar();
			self.location.href="ifms.htm?actionFlag=loadInterestCalculation&elementId=700110";
		}
}

function loadBelowDetails()
{
	var yearId = document.getElementById("cmbYear").value;
	var url;
	
	if(yearId == -1)
	{
		alert('Please select year');
		document.getElementById("radioForTreasury").checked = false;
		document.getElementById("radioForDDO").checked = false;
		document.getElementById("radioForEmp").checked = false;
		return;
	}
	
	if(document.getElementById("radioForTreasury").checked)
	{
		url="ifms.htm?actionFlag=loadInterestCalculation&elementId=700110&viewPageFor=Treasury&yearId="+yearId;
	}
	if(document.getElementById("radioForDDO").checked)
	{
		url="ifms.htm?actionFlag=loadInterestCalculation&elementId=700110&viewPageFor=DDO&yearId="+yearId;
	}
	if(document.getElementById("radioForEmp").checked)
	{
		url="ifms.htm?actionFlag=loadInterestCalculation&elementId=700110&viewPageFor=Emp&yearId="+yearId;
	}
	
	self.location.href = url ;
}

function getEmpListUnderDDOForEmpChecked()
{
	var yearId = document.getElementById("cmbYear").value;
	var treasuryCode = document.getElementById("cmbTreasury").value ;
	var ddoCode = document.getElementById("cmbDDOCode").value;
	if(yearId == -1)
	{
		alert('Please select Year');
		return ;
	}
	if(treasuryCode == -1)
	{
		alert('Please select Treasury');
		return ;
	}
	if(ddoCode == -1)
	{
		alert('Please select DDO.');
		return ;
	}
	url="ifms.htm?actionFlag=loadInterestCalculation&elementId=700110&viewPageFor=Emp&getEmpsForDDO=Yes&yearId="+yearId+"&treasuryCode="+treasuryCode+"&ddoCode="+ddoCode;
	self.location.href = url ;
}