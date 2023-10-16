
function getEmpDtlsForName()
{
	var empName=document.getElementById("txtEmployeeName").value.trim();
	
	var uri="ifms.htm?actionFlag=getEmpDtlsForName";
	var url="empName="+empName;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForGettingOtherDtls(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForGettingOtherDtls(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var dcpsEmpId = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var dcpsId = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var dob = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	
	document.getElementById("hidDcpsEmpId").value = dcpsEmpId;
	document.getElementById("txtDCPSId").value = dcpsId;
	document.getElementById("hidJoiningDate").value = dob;
	
	document.getElementById("txtTerminationDate").focus();
}

function getBelowDetailsForTermination()
{
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	var hidDcpsEmpId = document.getElementById("hidDcpsEmpId").value.trim();
	var txtDCPSId = document.getElementById("txtDCPSId").value.trim();
	var txtTerminationDate = document.getElementById("txtTerminationDate").value.trim();
	var hidJoiningDate = document.getElementById("hidJoiningDate").value.trim();
	var cmbReasonForTermination = document.getElementById("cmbReasonForTermination").value.trim();
	var hidUser = document.getElementById("hidUser").value.trim();
	var hidUse = document.getElementById("hidUse").value.trim();
	var url = "";
	var terminalId = "";
	
	if(txtEmployeeName == "")
		{
			alert('Please enter Employee Name');
			return;
		}
	if(txtTerminationDate == "")
	{
		alert('Please enter Termination Date');
		return;
	}
	if(cmbReasonForTermination == -1)
	{
		alert('Please enter Reason for Termination');
		return;
	}
	
	if(hidUse == 'NewRequest')
		{	
			url = "ifms.htm?actionFlag=loadTerminalRequest&elementId=700189";
		}
	if(hidUse == 'FromDraft')
		{
			url = "ifms.htm?actionFlag=popUpTerminalDetails&elementId=700193";
			terminalId = document.getElementById("hidTerminalId").value.trim();
			url = url + "&terminalId="+terminalId;
		}
	url = url + "&User="+hidUser+"&Use="+hidUse+"&GoPressed=Yes";
	url = url + "&hidDcpsEmpId="+hidDcpsEmpId+"&txtTerminationDate="+txtTerminationDate+"&hidJoiningDate="+hidJoiningDate+"&txtEmployeeName="+txtEmployeeName+"&txtDCPSId="+txtDCPSId+"&cmbReasonForTermination="+cmbReasonForTermination;
	self.location.href = url ;
}

function saveOrForwardTerminalRequest(saveOrForwardFlagValue)
{
	var saveOrForwardFlag = saveOrForwardFlagValue;
	
	if(!validateTerminalData(saveOrForwardFlag))
		{
			return ;
		}
	
	var uri="ifms.htm?actionFlag=saveOrForwardTerminalRequest";
	var url = runForm(0);
	var hidUser = document.getElementById("hidUser").value.trim();
	var hidUse = document.getElementById("hidUse").value.trim();
	
	url = url + "&saveOrForwardFlag=" + saveOrForwardFlag;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForSaveTerminalData(myAjax,hidUser,hidUse,saveOrForwardFlag);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	
}

function getDataStateChangedForSaveTerminalData(myAjax,hidUser,hidUse,saveOrForwardFlag)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
		if(hidUser == 'DDO')
			{
				if(saveOrForwardFlag == 1)
					{
						alert('Terminal Request is saved.');
					}
				else if(saveOrForwardFlag == 2)
					{
						alert('Terminal Request is forwarded to Treasury');
					}
				else if(saveOrForwardFlag == 5)
					{
						alert('Terminal Request is forwarded to System Admin');
					}
				
				if(hidUse == 'FromDraft')
					{
						self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=DDO&Use=FromDraft&elementId=700193" ;
					}
				else if(hidUse == 'NewRequest')
					{
						self.location.href = "ifms.htm?actionFlag=loadTerminalRequest&User=DDO&Use=NewRequest&elementId=700189";
					}
				else if(hidUse == 'FromTO')
					{
						self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=DDO&Use=FromTO&elementId=700190";
					}
			}
		if(hidUser == 'TO')
			{
				if(saveOrForwardFlag == 3)
					{
						alert('Terminal Request is saved.');
					}
				else if(saveOrForwardFlag == 4)
					{
						alert('Terminal Request is sent to DDO');
					}
				self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=TO&Use=FromDDO&elementId=700191" ;
			}
		if(hidUser == 'SRKA')
			{
				if(saveOrForwardFlag == 5)
				{
					alert('Terminal Request is processed by SRKA.');
				}
				self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=SRKA&Use=FromDDO&elementId=700192";
			}
		
	}	
}

function popUpTerminalDetails(terminalId,User,Use)
{
	self.location.href = "ifms.htm?actionFlag=popUpTerminalDetails&terminalId="+terminalId+"&User="+User+"&Use="+Use;
}

function ReturnBackOrClose()
{
	var hidUser = document.getElementById("hidUser").value.trim();
	var hidUse = document.getElementById("hidUse").value.trim();
	if(hidUser == 'DDO')
		{
			if(hidUse == 'FromDraft')
				{
					self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User="+hidUser+"&Use="+hidUse;
				}
			else
				{
					self.location.href = "ifms.htm?actionFlag=validateLogin";
				}
		}
	if(hidUser == 'TO')
	{
		if(hidUse == 'FromDDO')
			{
				self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User="+hidUser+"&Use="+hidUse;
			}
	}
	if(hidUser == 'SRKA')
	{
		if(hidUse == 'FromDDO')
			{
				self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User="+hidUser+"&Use="+hidUse;
			}
	}
}

function generateFormD(){}
function generateFormB(){}
function generateFormC(){}

function validateTerminalData(saveOrForwardFlag)
{
	var remarks = "";
	var amounts = "";
	var voucherNos = "";
	var voucherDates = "";
	var attachmentTable ;
	
	var totalMissingCredits = document.getElementById("hidTotalMissingCredits").value;
	if(saveOrForwardFlag == 2) // Validate data when DDO forwards
		{
			for(var counter=1;counter<=totalMissingCredits;counter++)
				{
					remarks = document.getElementById("txtRemarks"+counter).value;
					if(remarks == "")
						{
							alert('Please enter remarks');
							return false;
						}
				}
			
			
			attachmentTable = document.getElementById("myTableFormA");
			
			if(attachmentTable.rows.length < 2){
				alert("Please attach Form A");
				return false;
			}
			
			attachmentTable = document.getElementById("myTableDeathCertificate");
			
			if(attachmentTable.rows.length < 2){
				alert("Please attach Death Certificate");
				return false;
			}
			
			attachmentTable = document.getElementById("myTableR3Report");
			
			if(attachmentTable.rows.length < 2){
				alert("Please attach R3 Report of previous financial year");
				return false;
			}
			
		
		}
	
	if(saveOrForwardFlag == 4) // Validate data when DDO forwards
	{
		for(var counter=1;counter<=totalMissingCredits;counter++)
			{
				amounts = document.getElementById("txtDeducAmount"+counter).value;
				if(amounts == "")
					{
						alert('Please enter Amount To Be Deducted');
						return false;
					}
				voucherNos = document.getElementById("txtVoucherNo"+counter).value;
				if(voucherNos == "")
					{
						alert('Please enter Voucher No');
						return false;
					}
				voucherDates = document.getElementById("txtVoucherDate"+counter).value;
				if(voucherDates == "")
					{
						alert('Please enter Voucher Date');
						return false;
					}
			}
	}
	return true;
}

function deleteTerminalRequest(terminalId)
{
	var answer = confirm("Are you sure you want to delete this terminal request?");
	if(answer)
	{
		var uri = "ifms.htm?actionFlag=deleteTerminalRequest";
		var url = "terminalId="+terminalId;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForDeleteTerminalRqst(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function getDataStateChangedForDeleteTerminalRqst(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(lBlFlag=='true')
	{
		alert('This Termination Request is deleted.');
		self.location.href = "ifms.htm?actionFlag=viewTerminalRequests&User=DDO&Use=FromDraft&elementId=700193" ;
	}
}