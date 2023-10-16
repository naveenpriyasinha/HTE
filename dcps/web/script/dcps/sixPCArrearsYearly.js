function addYearlyArrears(flagvalue)
{
	var saveOrForwardFlag = flagvalue ;
	var dcpsEmpIds = "";
	var dcpsIds = "";
	var amounts = "";
	var dcpsSixPCYearlyIds = "";
	var hidTotalAmount = "";
	var hidAmountPaid = "";
	var tempFlag = 0;
	var finYearId= document.getElementById("finYearId").value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag = 0;
	
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			hidTotalAmount = document.getElementById("hidTotalAmount"+i).value;
			hidAmountPaid = document.getElementById("hidAmountPaid"+i).value;
			var hidName = document.getElementById("hidName"+i).value;
			
			
			if(Number(hidAmountPaid)  >=  Number(hidTotalAmount))
			{
				alert("You cannot add any more amounts for Employee " + hidName + ".Please forward this case.");
				tempFlag = 1;
				return;
			}
			else
			{
				tempFlag = 0;
			}
			
			if(i==totalSelectedEmployees-1)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value;
				dcpsIds = dcpsIds + document.getElementById("dcpsId"+i).value ;
				amounts = amounts + document.getElementById("amount"+i).value ;
				dcpsSixPCYearlyIds = dcpsSixPCYearlyIds + document.getElementById("checkbox"+i).value  ;
				flag=1;
			}
			else 
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value + "~";
				dcpsIds = dcpsIds + document.getElementById("dcpsId"+i).value + "~";
				amounts = amounts + document.getElementById("amount"+i).value + "~";
				dcpsSixPCYearlyIds = dcpsSixPCYearlyIds + document.getElementById("checkbox"+i).value + "~" ;
				flag=1;
			}
		}
	}

	if(flag == 1 && tempFlag == 0)
	{
		var uri = 'ifms.htm?actionFlag=saveSixPCArrearsYearly';
		var url = 'dcpsEmpIds='+dcpsEmpIds+'&dcpsIds='+dcpsIds+'&amounts='+amounts+'&finYearId='+finYearId+'&dcpsSixPCYearlyIds='+dcpsSixPCYearlyIds;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForSave(myAjax,saveOrForwardFlag);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		alert('Please select a case.');
	}
}
function getDataStateChangedForSave(myAjax,saveOrForwardFlag)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {

		if(saveOrForwardFlag == 1)
		{
			alert('Amounts saved successfully.');
			self.location.href = 'ifms.htm?actionFlag=sixthPCYearlyInstallment&UserType=DDOAsst&StatusFlag=D';
		}
/*		if(saveOrForwardFlag == 2)
		{
			var ForwardToPost = document.DCPSSixPCArrearsEntryForm.ForwardToPost.value;
			var urlforward = "ifms.htm?actionFlag=forwardDCPSArrearsYearly&dcpsSixPCYearlyIds="+dcpsSixPCYearlyIds+"&ForwardToPost="+ForwardToPost+"&amounts="+amounts ;
			FrwrdDCPSArrearsYearlyUsingAjax(urlforward);
		}  */
	}
}

function approveDCPSArrearsYearlyTO()
{
	var hidSchedule = document.getElementById("hidSchedule").value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var SixthPC_Id=" ";
	var amounts = "";
	var dcpsSixPcId=" ";
	var flag=0;
	
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		
		if(hidSchedule == 'no')
		{
			if(document.getElementById("checkbox"+i).checked == true)
			{
				SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
				flag = 1;
				var hidName = document.getElementById("hidName"+i).value;
				var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
				if(hidCaseStatus == 'A')
				{
					alert("You cannot alter any details of the schedule " + hidName + ".This schedule's case has already been approved.Please select only those cases which has not been approved.");
					tempFlag = 1;
					return;
				}
				else
				{
					tempFlag = 0;
				}
			}
		}
		else
		{
				SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
				
				dcpsSixPcId = dcpsSixPcId + document.getElementById("dcpsSixPcId"+i).value + "~";
				amounts = amounts + document.getElementById("amount"+i).value + "~" ;
				
				flag = 1;
				var hidName = document.getElementById("hidName"+i).value;
				var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
				if(hidCaseStatus == 'A')
				{
					alert("You cannot alter any details of " + hidName + ".This case has already been approved.Please select only those cases which has not been approved.");
					tempFlag = 1;
					return;
				}
				else
				{
					tempFlag = 0;
				}
		}
		
	}
	
	if(flag == 1 && tempFlag == 0)
	{
		var uri = "";
		if(hidSchedule == 'no')
		{
			uri = "ifms.htm?actionFlag=approveSixthPCArrearsYearlyByTO&approveWholeSchedule=yes";
		}
		else
		{
			uri = "ifms.htm?actionFlag=approveSixthPCArrearsYearlyByTO";
		}
		var url = "SixthPC_Id="+SixthPC_Id+"&amounts="+amounts+"&dcpsSixPcId="+dcpsSixPcId;
		ApproveArrearsYearlyTOUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to approve.");
	}
}
function ApproveArrearsYearlyTOUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					approveDataTOStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function approveDataTOStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (successFlag=='true') {
		
		alert("The case is approved.");
		self.location.reload(true);
		//self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=A";
	}
}
function rejectDCPSArrearsYearlyTO()
{
	var hidSchedule = document.getElementById("hidSchedule").value;
	var remarks = document.getElementById("txtRemarks").value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(hidSchedule == 'no')
		{
			if(document.getElementById("checkbox"+i).checked == true)
			{
				SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
				flag = 1;
				var hidName = document.getElementById("hidName"+i).value;
				var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
				if(hidCaseStatus == 'A')
				{
					alert("You cannot alter any details of " + hidName + ".This case has already been approved.Please select only those cases which has not been approved.");
					tempFlag = 1;
					return;
				}
				else
				{
					tempFlag = 0;
				}
			}
		}
		else
			{
				SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
				flag = 1;
				var hidName = document.getElementById("hidName"+i).value;
				var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
				if(hidCaseStatus == 'A')
				{
					alert("You cannot alter any details of " + hidName + ".This case has already been approved.Please select only those cases which has not been approved.");
					tempFlag = 1;
					return;
				}
				else
				{
					tempFlag = 0;
				}
			}
			
		
	}
	if(flag == 1 && tempFlag == 0)
	{
		if(remarks == "")
		{
			alert('Please Enter Remarks.');
		}
		else
		{
			var uri = "";
			if(hidSchedule == 'no')
			{
				uri = "ifms.htm?actionFlag=rejectSixthPCArrearsYearlyByTO&rejectWholeSchedule=yes";
			}
			else
			{
				uri = "ifms.htm?actionFlag=rejectSixthPCArrearsYearlyByTO";
			}
			
			var url = "SixthPC_Id="+SixthPC_Id+"&remarks="+remarks;
			RejectArrearsYearlyTOUsingAjax(uri,url);
		}
	}
	else
	{
		alert("Please select a case to reject.");
	}
}


function RejectArrearsYearlyTOUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					rejectDataTOStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function rejectDataTOStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (successFlag=='true') {
			alert("The cases are sent back to DDO Assistant.");
			//self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=F";
			self.location.reload(true);
		}
}

function approveDCPSArrearsYearlyDDO()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var ForwardToPost = document.DCPSSixPCArrearsEntryForm.ForwardToPost.value;
	
	var SixthPC_Id=" ";
	var flag=0;
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			flag = 1;
		}
	}
	if(flag == 1)
	{
		var uri = "ifms.htm?actionFlag=approveSixthPCArrearsYearlyByDDO";
		var url = "SixthPC_Id="+SixthPC_Id+"&ForwardToPost="+ForwardToPost;
		ApproveArrearsYearlyUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to approve.");
	}
}
function ApproveArrearsYearlyUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					approveDataStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function approveDataStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (successFlag=='true') {
			alert("The case is forwarded to Treasury Officer.");
			self.location.reload(true);
			//self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&StatusFlag=F";
		}
}

function rejectDCPSArrearsYearlyDDO()
{
	var remarks = document.getElementById("txtRemarks").value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(document.getElementById("checkbox"+i).checked == true)
			{
				SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
				flag = 1;
			}
		}
		if(flag == 1)
		{
			if(remarks == "")
			{
				alert('Please Enter Remarks.');
			}
			else
			{
				var uri = "ifms.htm?actionFlag=rejectSixthPCArrearsYearlyByDDO";
				var url = "SixthPC_Id="+SixthPC_Id+"&remarks="+remarks;
				RejectArrearsYearlyUsingAjax(uri,url);
			}
		}
		else
		{
			alert("Please select a case to reject.");
		}
}


function RejectArrearsYearlyUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					rejectDataStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function rejectDataStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (successFlag=='true') {
			alert("The case is sent back to DDO Assistant.");
			self.location.reload(true);
		 //	self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&StatusFlag=F";
		}
}

function FrwrdDCPSArrearsYearly()
{
	var ForwardToPost = document.DCPSSixPCArrearsEntryForm.ForwardToPost.value;
	var amounts="";
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var dcpsSixPCYearlyIds = "";
	var flag=0;
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			dcpsSixPCYearlyIds = dcpsSixPCYearlyIds + document.getElementById("checkbox"+i).value + "~" ;
			amounts = amounts + document.getElementById("amount"+i).value + "~";
			flag = 1;
			
		}
	}
	if(flag == 1)
	{
		var uri = "ifms.htm?actionFlag=forwardDCPSArrearsYearly";
		var url = "dcpsSixPCYearlyIds="+dcpsSixPCYearlyIds+"&ForwardToPost="+ForwardToPost+"&amounts="+amounts;
		FrwrdDCPSArrearsYearlyUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to forward.");
	}
}

function FrwrdDCPSArrearsYearlyUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					FrwrdDCPSArrearsYearlyStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function FrwrdDCPSArrearsYearlyStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (successFlag=='true') {
			alert("The case is forwarded to DDO Approver.");
			self.location.reload(true);
			//self.location.href='ifms.htm?actionFlag=sixthPCYearlyInstallment&UserType=DDOAsst&StatusFlag=D&yearId='+document.getElementById("cmbFinancialYear").value
		}
}
function displayDataForGivenYear()
{
	if(document.getElementById("cmbFinancialYear").value == -1)
	{
		alert('Please select financial year  to confirm request.');
		return;
	}
	else
	{
		self.location.href='ifms.htm?actionFlag=sixthPCYearlyInstallment&elementId=700024&UserType=DDOAsst&StatusFlag=D&yearId='+document.getElementById("cmbFinancialYear").value;
	}
}

function displayDataForGivenYearDDO()
{
	if(document.getElementById("cmbFinancialYear").value == -1)
	{
		alert('Please select financial year  to confirm request.');
		return;
	}
	else
	{
		self.location.href='ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&elementId=700053&UserType=DDO&StatusFlag=F&yearId='+document.getElementById("cmbFinancialYear").value;
	}
}

function displayDataForSchedule(scheduleId)

{
	var yearId = document.getElementById("cmbFinancialYear").value;
	var cmbDDO = document.getElementById("cmbDDO").value;
	if(document.getElementById("cmbDDO").value == -1)
	{
		alert('Please Select DDO to confirm request.');
		return;
	}
	else if(document.getElementById("cmbFinancialYear").value == -1)
	{
		alert('Please select financial year  to confirm request.');
		return;
	}
	else{
	self.location.href='ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&elementId=700055&UserType=TO&StatusFlag=F&yearId='+yearId+'&ddoCode='+cmbDDO+'&requestforgivenDDO=yes&scheduleId='+scheduleId;
	}
}

function displayDataForGivenYearTO()
{
	var yearId = document.getElementById("cmbFinancialYear").value;
	var cmbDDO = document.getElementById("cmbDDO").value;
	
	if(document.getElementById("cmbDDO").value == -1)
	{
		alert('Please Select DDO to confirm request.');
		return;
	}
	else if(document.getElementById("cmbFinancialYear").value == -1)
	{
		alert('Please select financial year  to confirm request.');
		return;
	}
	else
	{
		self.location.href='ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&elementId=700055&UserType=TO&StatusFlag=F&yearId='+yearId+'&ddoCode='+cmbDDO+'&requestforgivenDDO=yes';
	}
	//document.getElementById("tblDDO").style.display="block";
}

function checkUncheckAll(theElement)
{
	 var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}	

function viewArrearsCaseListYearly()
{
	var url= "";
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	
	if(cmbDesignationValue == 'All Designations')
	{
		url = "ifms.htm?actionFlag=sixthPCYearlyInstallment&StatusFlag=D&UserType=DDOAsst&yearId="+document.getElementById("cmbFinancialYear").value;
	}
	else
	{
		url = "ifms.htm?actionFlag=sixthPCYearlyInstallment&StatusFlag=D&UserType=DDOAsst&cmbDesignation="+cmbDesignationValue+"&yearId="+document.getElementById("cmbFinancialYear").value;
	}
	self.location.href = url ;
}

function viewArrearsCaseListYearlyDDO()
{
	var url= "";
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	
	if(cmbDesignationValue == 'All Designations')
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&StatusFlag=F&yearId="+document.getElementById("cmbFinancialYear").value;
	}
	else
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&cmbDesignation="+cmbDesignationValue+"&StatusFlag=F&yearId="+document.getElementById("cmbFinancialYear").value;
	}
	self.location.href = url ;
}
function viewArrearsCaseListYearlyTO()
{
	var url= "";
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	var yearId = document.getElementById("cmbFinancialYear").value;
	var cmbDDO = document.getElementById("cmbDDO").value;
	if(cmbDesignationValue == 'All Designations')
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=A&yearId="+yearId+"&ddoCode="+cmbDDO+"&requestforgivenDDO=yes";
	}
	else
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&cmbDesignation="+cmbDesignationValue+"&StatusFlag=A&yearId="+yearId+"&ddoCode="+cmbDDO+"&requestforgivenDDO=yes";
	}
	self.location.href = url ;
}

function ReturnForArrearsYearlyInTOLogin()
{
	var hidSchedule = document.getElementById("hidSchedule").value;
	var yearId = document.getElementById("cmbFinancialYear").value;
	var ddoCode = document.getElementById("cmbDDO").value;
	var url="";
	if(hidSchedule=='yes')
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&elementId=700055&UserType=TO&StatusFlag=F"+"&yearId="+yearId+"&ddoCode="+ddoCode+"&requestforgivenDDO=yes";
	}
	else
	{
		url = "ifms.htm?actionFlag=validateLogin";
	}
	self.location.href = url;
}
