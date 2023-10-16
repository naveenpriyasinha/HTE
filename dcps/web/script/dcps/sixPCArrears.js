function approveDCPSArrearsCase()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	var hidDcpsEmpId=" ";
	var hidTotalAmount=" ";
	var tempFlag = 0;
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		
		if(document.getElementById("checkbox"+i).checked == true)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			hidDcpsEmpId = hidDcpsEmpId + document.getElementById("hidDcpsEmpId"+i).value + "~";
			hidTotalAmount = hidTotalAmount + document.getElementById("hidTotalAmount"+i).value + "~";
			flag = 1;
			var hidName = document.getElementById("hidName"+i).value;
			var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
			
			if(hidCaseStatus == 'A')
			{
				alert("You cannot alter any details of " + hidName + ".This case has already been approved.Please select only those cases which have not been approved.");
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
		var uri = "ifms.htm?actionFlag=approveSixthPCArrears";
		var url = "SixthPC_Id="+SixthPC_Id+"&hidDcpsEmpId="+hidDcpsEmpId+"&hidTotalAmount="+hidTotalAmount;
		
		ApproveArrearsUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to Approve.");
	}
}
function ApproveArrearsUsingAjax(uri,url)
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
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	
	if (successFlag=='true') {
		
		alert("The cases are approved.");
		self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsDDO&elementId=700067&StatusFlag=A&cmbDesignation="+cmbDesignationValue;
	}
}

function rejectDCPSArrearsCase()
{
	var remarks = document.getElementById("txtRemarks").value;
	if(remarks == "")
	{
		alert('Please Enter Remarks.');
		return false ;
	}
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	for(var i=1;i<totalSelectedEmployees;i++)
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
	if(flag == 1 && tempFlag == 0)
	{
		var uri = "ifms.htm?actionFlag=rejectSixthPCArrears";
		var url = "SixthPC_Id="+SixthPC_Id+"&remarks="+remarks;
		RejectArrearsUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to reject.");
	}
}


function RejectArrearsUsingAjax(uri,url)
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
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	if (successFlag=='true') {
		
		alert("The cases are sent back to DDO Assistant.");
		self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsDDO&elementId=700067&StatusFlag=F&cmbDesignation="+cmbDesignationValue;
		
	}
}

function saveAndfrwrdData()
{

	var ForwardToPost = document.frm6PcArrearEntry.ForwardToPost.value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	var amounts="";
	var fromDates="";
	var toDates="";
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(parseInt(document.getElementById("amount"+i).value)  != 0)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			amounts= amounts + document.getElementById("amount"+i).value + "~";
			fromDates = fromDates + document.getElementById("fromDate"+i).value + "~";
			toDates = toDates +  document.getElementById("toDate"+i).value + "~";
			flag = 1;
		}
	}
	if(flag == 1)
	{
		var uri = "ifms.htm?actionFlag=createandfrwrdWF" ;
		var url = "SixthPC_Id="+SixthPC_Id+"&ForwardToPost="+ForwardToPost+"&amounts="+amounts+"&fromDates="+fromDates+"&toDates="+toDates ;
		saveAndfrwrdUsingAjax(uri,url);
	}
	else
	{
		alert("Please select a case to forward.");
	}

	
}
function saveAndfrwrdUsingAjax(uri,url)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					saveAndfrwrdStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function saveAndfrwrdStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
		alert("The cases are forwarded to DDO Approver.");
		self.location.reload(true);
	}
}

function checkUncheckAll()
{						
	 var theElement = document.getElementsByName("SelectAll");
	 for(var z=0; z<theElement.length;z++)
	 {		 
		if(!(theElement[z].checked))
		 theElement[z].checked = true;
		else
		 theElement[z].checked = false;
	 }
	     
}	
function saveData()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var dcpsSixPCIds="";
	var amounts="";
	var flag=0;
	var fromDates="";
	var toDates="";
	
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(parseInt(document.getElementById("amount"+i).value)  != 0)
			{
				dcpsSixPCIds= dcpsSixPCIds + document.getElementById("checkbox"+i).value + "~";
				amounts= amounts + document.getElementById("amount"+i).value + "~";
				fromDates = fromDates + document.getElementById("fromDate"+i).value + "~";
				toDates = toDates +  document.getElementById("toDate"+i).value + "~";
				flag = 1;
			}
		}
	
		if(flag == 1)
		{
			var uri = 'ifms.htm?actionFlag=saveSixPCArrears';
			var url = 'dcpsSixPCIds='+dcpsSixPCIds+'&amounts='+amounts+'&fromDates='+fromDates+'&toDates='+toDates;
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForSave(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
		}
		else
		{
			alert('Please select a case for save the amount.');
		}
}

function getDataStateChangedForSave(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
		alert('Amounts saved successfully.');
		self.location.href = 'ifms.htm?actionFlag=sixthPCArrearsEntry&StatusFlag=R&elementId=700023';
	}
}

function saveDataDDO()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var dcpsSixPCIds="";
	var amounts="";
	var tempFlag = 0;
	var flag=0;
	for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(document.getElementById("checkbox"+i).checked)
			{
				dcpsSixPCIds= dcpsSixPCIds + document.getElementById("checkbox"+i).value + "~";
				amounts= amounts + document.getElementById("amount"+i).value + "~";
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
			var uri = 'ifms.htm?actionFlag=saveSixPCArrears' ;
			var url = 'dcpsSixPCIds='+dcpsSixPCIds+'&amounts='+amounts;
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForSaveDDO(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
		}
		else
		{
			alert('Please select a case for save the amount.');
		}
}

function getDataStateChangedForSaveDDO(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
		alert('Amounts saved successfully.');
		self.location.href = 'ifms.htm?actionFlag=loadSixthPCArrearsDDO&StatusFlag=F';
	}
}

function viewArrearsCaseList()
{
	var url= "";
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	if(cmbDesignationValue == 'All Designations')
	{
		displayAll();
	}
	else
	{
		url = "ifms.htm?actionFlag=sixthPCArrearsEntry&elementId=700023&cmbDesignation="+cmbDesignationValue+"&StatusFlag=D";
		showProgressbar('Please Wait<br>Your request is in progress...');
		self.location.href = url ;
	}
}
function viewArrearsCaseListDDO()
{
	var url= "";
	var cmbDesignationValue = document.getElementById("cmbDesignation").options[document.getElementById("cmbDesignation").selectedIndex].value;
	if(cmbDesignationValue == 'All Designations')
	{
		displayAll();
	}
	else
	{
		url = "ifms.htm?actionFlag=loadSixthPCArrearsDDO&elementId=700067&cmbDesignation="+cmbDesignationValue+"&StatusFlag=F";
		showProgressbar('Please Wait<br>Your request is in progress...');
		self.location.href = url ;
	}
}

function displayAll()
{
	var hidStatus = document.getElementById("hidStatus").value;
	if(hidStatus=='D' || hidStatus=='R')
	{
		self.location.href = "ifms.htm?actionFlag=sixthPCArrearsEntry&elementId=700023&StatusFlag="+hidStatus;
	}
	if(hidStatus=='F' || hidStatus=='A')
	{
		self.location.href = "ifms.htm?actionFlag=loadSixthPCArrearsDDO&elementId=700067&StatusFlag="+hidStatus;
	}
}