var gFlag = 0;
function getDetailsForGenerateLetter()
{
	if(document.getElementById("txtCaseStatus").value == 'TransferNew')
	{
		if(document.getElementById("hidPpoNo").value == "" )
		{
			if(document.getElementById("txtName").value == "" || document.getElementById("txtOldTreasury").value == "" )
			{
				alert('Please Enter PPO No.');
				return;	
			}
		}
		var txtPpoNo = document.getElementById("hidPpoNo").value;
	}
	else
	{
		var txtPpoNo = document.getElementById("txtPpoNo").value;
		document.getElementById("hidPpoNo").value == "";
	}
	var uri = "ifms.htm?actionFlag=getNameAndTreasuryFromPPONo&txtPpoNo="+txtPpoNo;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getDetailsForGenerateLetterStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
	

}
function getDetailsForGenerateLetterStateChanged(myAjax) 
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPONo');
	var pnsnrName=XmlHiddenValues[0].childNodes[0].text;
	var trsryName=XmlHiddenValues[0].childNodes[1].text;
	var locCode=XmlHiddenValues[0].childNodes[2].text;
	var pnsnrCode=XmlHiddenValues[0].childNodes[3].text;
	var caseStatus = XmlHiddenValues[0].childNodes[4].text;
	if(caseStatus == 'TRANSFERED')
	{
		document.getElementById("txtPnsnrCode").value = pnsnrCode;
	}
	else
	{
		document.getElementById("txtName").value = pnsnrName;
		document.getElementById("txtOldTreasury").value = trsryName;
		document.getElementById("txtLocCode").value = locCode;
		document.getElementById("txtPnsnrCode").value = pnsnrCode;
		document.getElementById("txtCaseStatus").value = caseStatus;
	}
	
}

function getNameAndTrsryFromPPONo()
{

	
	var txtPpoNo = document.getElementById("txtPpoNo").value;
	var uri = "ifms.htm?actionFlag=getNameAndTreasuryFromPPONo&txtPpoNo="+txtPpoNo;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {NameAndTrsryCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
	
}
function NameAndTrsryCaseStateChanged(myAjax) 
{
	 	var XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPONo');
		var pnsnrName=XmlHiddenValues[0].childNodes[0].text;
		if(pnsnrName == 'EmptyList')
		{
			alert('No such PPO exists.');
			document.getElementById("txtPpoNo").value="";
			document.getElementById("txtName").value="";
			document.getElementById("txtOldTreasury").value="";
			return;
		}	
		var trsryName=XmlHiddenValues[0].childNodes[1].text;
		var locCode=XmlHiddenValues[0].childNodes[2].text;
		var pnsnrCode=XmlHiddenValues[0].childNodes[3].text;
		var caseStatus = XmlHiddenValues[0].childNodes[4].text;
		var requestId = XmlHiddenValues[0].childNodes[5].text;
		var toLocation = XmlHiddenValues[0].childNodes[6].text;
		if(caseStatus != 'Approved')
		{
			if(caseStatus == 'TransferNew')
			{
				document.getElementById("hidPpoNo").value = document.getElementById("txtPpoNo").value ;
				document.getElementById("txtPnsnrCode").value = pnsnrCode;
			}
			//document.getElementById("txtPpoNo").value="";
			//document.getElementById("txtName").value="";
			//document.getElementById("txtOldTreasury").value="";
			document.getElementById("txtPpoNo").value="";
			alert("Only approved cases can be transferred.");
			return;
		}
		else
		{
		//	getDetailsForGenerateLetter();
			document.getElementById("txtName").value = pnsnrName;
			document.getElementById("txtOldTreasury").value = trsryName;
			document.getElementById("txtLocCode").value = locCode;
			document.getElementById("txtPnsnrCode").value = pnsnrCode;
			document.getElementById("txtCaseStatus").value = caseStatus;
			document.getElementById("txtRequestId").value = requestId;
			document.getElementById("cmbNewTreasury").value = toLocation;
		}
}

function transferPPOCase(flag)
{
	// flag = 1 means  for Auditor means save transfer case
	// flag = 2 means  for ATO means update transfer case
	// flag = 4 means for ATO reject transfer case
	if(flag == 1) 
	{
		if(document.getElementById("txtPpoNo").value == "" || document.getElementById("txtName").value == "" || document.getElementById("txtOldTreasury").value == "")
		{
			alert('Please enter ppo no.');
			return;
		}
		var requestId = document.getElementById("txtRequestId").value;
		if(requestId.length > 0)
		{
			alert('This ppo is already transfered.');
			return;
		}
		
	}
	
	if(document.getElementById("cmbNewTreasury").value == -1 && document.getElementById("txtOtherState").value == "")
	{
		if(document.getElementById("RadioButtonState").checked == true)
		{
			if(document.getElementById("txtOtherState").value == "")
			{
				alert('Please enter the name of the state and treasury in which you want to trasnfer PPO Case.');
				return;
			}	
		}
		else
		{
			if(document.getElementById("cmbNewTreasury").value == -1 )
			{
				alert('Please select any  treasury in which you want to trasnfer PPO Case.');
				return;
			} 
		}
	}
	var txtLocCode = document.getElementById("txtLocCode").value;
	var txtPnsnrCode = document.getElementById("txtPnsnrCode").value;
	var txtPpoNo = document.getElementById("txtPpoNo").value;
	var txtCaseStatus = document.getElementById("txtCaseStatus").value;
	var uri = "ifms.htm?actionFlag=saveTransferOfPPOCase&txtLocCode="+txtLocCode+"&txtPnsnrCode="+txtPnsnrCode+"&txtPpoNo="+txtPpoNo+"&cmbNewTreasury="+document.getElementById("cmbNewTreasury").value+"&flag="+flag+"&hdnTransferDtlsId="+document.getElementById("hdnTransferDtlsId").value;
	var url = runForm(0); 
	url = uri + url; 
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {TransferPPOCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
	
}
function TransferPPOCaseStateChanged(myAjax) 
{ 
	var XMLDoc=myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	if(string == 'Add')
	{
		var requestID = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
		alert("PPO case transfer request has been generated successfully.Request ID for this PPO is : "+ requestID);
		document.getElementById("txtRequestId").value = requestID;
		document.getElementById("btnSave").disabled = "disabled";
		gFlag = 1;
	}
	else if(string == 'Reject')
	{
		alert("Transfer request for this ppo is rejected.");
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtName").value ="";
		document.getElementById("txtOldTreasury").value ="";
		document.getElementById("txtLocCode").value ="";
		document.getElementById("txtPnsnrCode").value ="";
		document.getElementById("txtCaseStatus").value ="";
		document.getElementById("txtRequestId").value="";
		document.getElementById("cmbNewTreasury").value="-1";
	}
	else
	{
		alert("PPO case has been updated successfully.");
		gFlag = 1;
	}
}
function getAuditorNameFromBranchCode(object)
{
	var elementId=object.id;
	
	var rowNum=elementId.substring(13);
	
	var branchCode=document.getElementById(elementId).value;
	var resultElement="cmbAuditorName"+rowNum;
	if(branchCode != "")
	{
		document.getElementById("txtBranchCode"+rowNum).value = branchCode;
		
	
		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
		
		var myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {getDataStateChangedForAudiName(myAjax,rowNum);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});
		
	}
	else
	{
		
		document.getElementById("txtBranchCode"+rowNum).value = "";
		var theOption = new Option;
		theOption.value = "-1";
		theOption.text ="--Select--";
		document.getElementById(resultElement).options[0]=theOption;
 		document.getElementById(resultElement).options[0].selected="selected";
 		document.getElementById(resultElement).disabled=false;
		
	}
}
function getDataStateChangedForAudiName(myAjax,rowNum)
{
	var resultElement="cmbAuditorName"+rowNum;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var auditorName=XmlHiddenValues[0].childNodes[0].text;
	var auditorPostId = XmlHiddenValues[0].childNodes[1].text;
	
	document.getElementById(resultElement).options[0].text = auditorName;
	document.getElementById(resultElement).value = auditorPostId;
	document.getElementById(resultElement).options[0].selected="selected";
	document.getElementById(resultElement).disabled=true;
}
function getNomBnkBrnchNameFrmBnkCode(object)
{
	
	var elementId=object.id;

	var rowNum=elementId.substring(13);

	var branchCode=document.getElementById(elementId).value;
	var resultElementBankCode="cmbBankName"+rowNum;
	var resultElementBranchName="cmbTargetBranchName"+rowNum;
	
	if(document.getElementById(elementId).value != "")
	{
	
	

	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getDataStateChangedForBnkBrnchAudiName(myAjax,rowNum,branchCode,elementId);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
	

	}
	else
	{
		
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
	}
}

function getDataStateChangedForBnkBrnchAudiName(myAjax,rowNum,branchCode,elementId)
{
	var resultElementBankCode="cmbBankName"+rowNum;
	var resultElementBranchName="cmbTargetBranchName"+rowNum;

			XMLDoc = myAjax.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			
			if(XmlHiddenValues[0].childNodes.length != 0)
			{
			var branchName=XmlHiddenValues[0].childNodes[0].text;
			
			var bankCode=XmlHiddenValues[0].childNodes[1].text;
			var bankName=XmlHiddenValues[0].childNodes[2].text;
			var auditorName=XmlHiddenValues[0].childNodes[3].text;
		
			document.getElementById(resultElementBankCode).options[0].value=bankCode;
			document.getElementById(resultElementBankCode).options[0].text=bankName;
			document.getElementById(resultElementBankCode).options[0].selected="selected";
			document.getElementById(resultElementBankCode).disabled=true;
			
			document.getElementById(resultElementBranchName).options[0].value=branchCode;
			document.getElementById(resultElementBranchName).options[0].text=branchName;
			document.getElementById(resultElementBranchName).options[0].selected="selected";
			document.getElementById(resultElementBranchName).style.width="250px";
			document.getElementById(resultElementBranchName).disabled=true;
			
			}
			else
			{
				alert("NO Such Bank Code Exsists!");
				document.getElementById(elementId).value="";
				var theOption = new Option;
				theOption.value = "-1";
				theOption.text ="--Select--";
				document.getElementById(resultElementBankCode).options[0]=theOption;
		 		document.getElementById(resultElementBankCode).options[0].selected="selected";
		 		document.getElementById(resultElementBankCode).disabled=false;
				theOption = new Option;
				theOption.value = "-1";
				theOption.text ="--Select--";
		 		document.getElementById(resultElementBranchName).options.length = 0;
		 		document.getElementById(resultElementBranchName).options[0]=theOption;
		 		document.getElementById(resultElementBranchName).options[0].selected="selected";
		 		document.getElementById(resultElementBranchName).disabled=false;
						
			}
	
}
function getNomBranchNameFromBankCode(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(11);
	var bankCode=document.getElementById(elementId).value;
	
	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {getDataStateChangedForBranchName(myAjax,rowNum);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
}
function getDataStateChangedForBranchName(myAjax,rowNum){
	

			XMLDoc = myAjax.responseXML.documentElement;
			var resultElementId="cmbTargetBranchName"+rowNum;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
			var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
		   	if(XmlHiddenValues.length >0)
			{
			 		for( var i=0; i<XmlHiddenValues.length ;i++) {
			 		
	    		    var theOption = new Option;
	    		    theOption.value = XmlHiddenValues[i].text;
					theOption.text = XmlHiddenValues1[i].text;
					document.getElementById("txtBranchCode"+rowNum).value=XmlHiddenValues[0].text;
					document.getElementById(resultElementId).options[i] = theOption;
					
			}
		}
		else
		{
			 		alert("This bank does not belong to your location.");
			 		document.getElementById(resultElementId).options.length = 0;
			 		theOption = new Option;
					theOption.value = "-1";
					theOption.text ="--Select--";
					document.getElementById(resultElementId).options[0]=theOption;
			 		document.getElementById(resultElementId).options[0].selected="selected";
			 		document.getElementById(resultElementId).options[0].disabled=true;
		}
   
}

function getBranchCode(object)
{
	
	var elementId=object.id;
	var rowNum=elementId.substring(19);
	var hidListOfBrnchCode = document.getElementById("hidListOfBrnchCode").value;
	var flag=0;
	var tempListOfBrnchCode = hidListOfBrnchCode.substr(1,hidListOfBrnchCode.length-2);
	var finalListOfBrnchCode = tempListOfBrnchCode.split(",");
	
	if(document.getElementById("cmbTargetBranchName"+rowNum).value != "" || document.getElementById("cmbTargetBranchName"+rowNum).value != null)
	{
		for(var cnt=0;cnt<finalListOfBrnchCode.length;cnt++)
		{
			
			if(Number(document.getElementById("cmbTargetBranchName"+rowNum).value) == Number(finalListOfBrnchCode[cnt]))
			{
				flag = 1;
			}
		}
		if(flag == 0)
		{
			alert('This branch is not mapped with any auditor.Please select different branch.');
			document.getElementById("txtBranchCode"+rowNum).value = "";
			document.getElementById("cmbTargetBranchName"+rowNum).options.length = 0;
			var theOption = new Option;
			theOption.value = "-1";
			theOption.text ="--Select--";
			document.getElementById("cmbTargetBranchName"+rowNum).options[0]=theOption;
	 		document.getElementById("cmbTargetBranchName"+rowNum).options[0].selected="selected";
	 		document.getElementById("cmbTargetBranchName"+rowNum).disabled=false;
			document.getElementById("cmbBankName"+rowNum).value ="-1";
			return;
		}
		else
		{
			document.getElementById("txtBranchCode"+rowNum).value = document.getElementById("cmbTargetBranchName"+rowNum).value;
		}
		
	}
}



function changeInPPOCase(flag)
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var TrnfrDtls_Id=" ";
	var bankCode="";
	var ppoNo="";
	var branchCode="";
	var accNo="";
	var tempFlag=0;
	var myAjax;
	var url;
	var oldPpoNo="";
	
	if(flag == 1 || flag == 2)
	{
		if(flag == 1)
		{
			for(var i=1;i<totalSelectedEmployees;i++)
			{
				
				if(document.getElementById("checkbox"+i).checked == true)
				{
					
					TrnfrDtls_Id= TrnfrDtls_Id + document.getElementById("checkbox"+i).value + "~";
					
					if(document.getElementById("cmbBankName"+i).value == -1)
					{
						bankCode = bankCode + "null" +"~";
					}
					else
					{
						bankCode = bankCode + document.getElementById("cmbBankName"+i).value + "~";
					}
					if(document.getElementById("txtBranchCode"+i).value == "")
					{
						branchCode = branchCode + "null" + "~";
					}
					else
					{
						branchCode = branchCode + document.getElementById("txtBranchCode"+i).value + "~";
					}
					if(document.getElementById("txtAccNo"+i).value == "")
					{
						
						accNo = accNo + "null" + "~";
					}
					else
					{
						accNo = accNo + document.getElementById("txtAccNo"+i).value + "~";
					}
					tempFlag=1;
					if(document.getElementById("txtPPONo"+i).readOnly == true)
					{
						continue;
					}
					else
					{
						oldPpoNo = oldPpoNo + document.getElementById("lblOldPpoNo"+i).innerHTML + "~";
						ppoNo = ppoNo + document.getElementById("txtPPONo"+i).value + "~";
					}
					
				}
			}
		}
		
		if(flag == 2)
		{
			for(var i=1;i<totalSelectedEmployees;i++)
			{
				
				if(document.getElementById("checkbox"+i).checked == true)
				{
					
					TrnfrDtls_Id= TrnfrDtls_Id + document.getElementById("checkbox"+i).value + "~";
					tempFlag=1;
					
				}
			}
		}
		
	}
	
	
	
	if(flag==1) // for receive PPO case
	{
		if(tempFlag == 1)
		{
			
			url = "ifms.htm?actionFlag=changeInPPOCase&flag="+flag+"&TrnfrDtls_Id="+TrnfrDtls_Id+"&bankCode="+bankCode+"&branchCode="+branchCode+"&accNo="+accNo+"&ppoNo="+ppoNo+"&oldPpoNo="+oldPpoNo;
			myAjax = new Ajax.Request(url,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {changeInPPOCaseStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
			
		}
		else
		{
			alert("Please select a case to receive.");
		}
	} 
	if(flag==2)  // for reject PPO case
	{
		if(tempFlag == 1)
		{
			var uri = "ifms.htm?actionFlag=changeInPPOCase&flag="+flag+"&TrnfrDtls_Id="+TrnfrDtls_Id;
			popUpRemarks(uri);
		}
		else
		{
			alert("Please select a case to reject.");
		}
	}
	if(flag == 3) // move PPO case into library
	{
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			
			if(document.getElementById("checkbox"+i).checked == true)
			{
				if(document.getElementById("txtBranchCode"+i).value == "" || document.getElementById("txtAccNo"+i).value == "")
				{
					alert('You cannot move ' + document.getElementById("lblOldPpoNo"+i).innerHTML + '.Please enter bank details for this PPO.');
					return;
				}
				TrnfrDtls_Id= TrnfrDtls_Id + document.getElementById("checkbox"+i).value + "~";
				branchCode = branchCode + document.getElementById("txtBranchCode"+i).value + "~";
				bankCode = bankCode + document.getElementById("cmbBankName"+i).value + "~";
				accNo = accNo + document.getElementById("txtAccNo"+i).value + "~";
				tempFlag=1;
				
			}
		}
		
		if(tempFlag == 1)
		{
			url = "ifms.htm?actionFlag=changeInPPOCase&flag="+flag+"&TrnfrDtls_Id="+TrnfrDtls_Id+"&bankCode="+bankCode+"&branchCode="+branchCode+"&accNo="+accNo;
			myAjax = new Ajax.Request(url,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {changeInPPOCaseStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
			
		}
		else
		{
			alert("Please select a case to move into the library.");
		}
		
	}
}


function changeInPPOCaseStateChanged(myAjax) 
{ 
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCFlag');
	var flag=XmlHiddenValues[0].childNodes[0].text;
	if(flag == 1)
	{
		alert("PPO case has been received successfully.");
		self.location.href="ifms.htm?actionFlag=loadReceiveTransferCase";
	}
	if(flag == 2)
	{
		alert("PPO case has been rejected successfully.");
		window.opener.location.reload();
	}
	if(flag == 3)
	{
		alert("PPO case has been moved into library successfully.");
		if(document.getElementById("hidString").value == 'Reject')
		{
			self.location.href="ifms.htm?actionFlag=loadRejectedTransferCase";
		}
		else
		{
			self.location.href="ifms.htm?actionFlag=loadReceiveTransferCase";
		}
		
		
	}	
}
function popUpRemarks(url)
{
	window_new_updateremarks(url);
}
function window_new_updateremarks(url)
{
	
	var newWindow = null;
   	var height = screen.height - 300;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function saveRemarks()
{
	if(document.getElementById("trnsfrDtlId").value == "" || document.getElementById("flagVal").value == "")
	{
		alert('Remarks can not be saved.');
		return;
	}	
	var TrnfrDtls_Id= document.getElementById("trnsfrDtlId").value;
	var flag=document.getElementById("flagVal").value;
	if(document.getElementById("txtRemarks").value == null || document.getElementById("txtRemarks").value == "")
	{
		alert('Please insert any remarks.');
		return;
	}
	var txtRemarks = document.getElementById("txtRemarks").value;
	
	alert('Remarks saved successfully.');
	
	var uri = "ifms.htm?actionFlag=changeInPPOCase&flag="+flag+"&TrnfrDtls_Id="+TrnfrDtls_Id+"&txtRemarks="+txtRemarks;
	
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {changeInPPOCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	closeRemarksWindow(flag);
}
function closeRemarksWindow(flag)
{
	if(flag == 1)
	{
		
		execScript('n = msgbox("Do you want to close the Dialouge box?","4132")',"vbscript","Bhargav");

		if(n==6) //yes
		{
			self.close();
		}
		else if (n == 7) //no
		{
			return;
		}	
	}
	if(flag == 2)
	{
		self.close();
	}
	
	
}
function closeCurrentWindow()
{
	execScript('n = msgbox("Do you want to close the current window?","4132")',"vbscript","Bhargav");
	if(n==6) //yes
	{
		self.close();
	}
	else if (n == 7) //no
	{
		return;
	}	
}
function viewRemarks(TrnfrDtls_Id)
{
	
	var url = "ifms.htm?actionFlag=changeInPPOCase&TrnfrDtls_Id="+TrnfrDtls_Id+"&flag="+2;
	window_new_updateremarks(url);
	//self.location.href = url;
	
}
function searchTransferCases()
{

	if(document.getElementById("cmbSearchBy").value == -1 && (document.getElementById("txtSearchValue").value == null || document.getElementById("txtSearchValue").value == "" ||  document.getElementById('cmbNewTreasury').value == -1))
	{
		alert('Please select valid search criteria to confirm request.');
		return;
	}
	
	var CmbSearchBy = document.getElementById("cmbSearchBy").options[document.getElementById("cmbSearchBy").selectedIndex].text;
	
	
	if(document.getElementById("txtSearchValue").value != null)
	{
		var txtSearchValue = document.getElementById("txtSearchValue").value;
		
	}
	var url;
	
	if(document.getElementById("cmbNewTreasury").value == -1 || document.getElementById("cmbNewTreasury").value == null )
	{
		if(document.getElementById("hidTransferCase").value == "Receive")
		{
			url = "ifms.htm?actionFlag=loadReceiveTransferCase&CmbSearchBy="+CmbSearchBy+"&txtSearchValue="+txtSearchValue;
		}
		else
		{
			
			url = "ifms.htm?actionFlag=loadRejectedTransferCase&CmbSearchBy="+CmbSearchBy+"&txtSearchValue="+txtSearchValue;
		}	
		
		
	}
	else
	{
		
		var CmbNewTreasury = document.getElementById("cmbNewTreasury").value;
		
		if(document.getElementById("hidTransferCase").value == "Receive")
		{
			
			url = "ifms.htm?actionFlag=loadReceiveTransferCase&CmbSearchBy="+CmbSearchBy+"&txtSearchValue="+txtSearchValue+"&CmbNewTreasury="+CmbNewTreasury;
		}
		else
		{
			
			url = "ifms.htm?actionFlag=loadRejectedTransferCase&CmbSearchBy="+CmbSearchBy+"&txtSearchValue="+txtSearchValue+"&CmbNewTreasury="+CmbNewTreasury;
		}	
	}
	
	self.location.href=url;
	 
}
function showSearchByVal()
{

	
	if(document.getElementById("cmbSearchBy").options[document.getElementById("cmbSearchBy").selectedIndex].text == "Treasury Name")
	{
		document.getElementById("txtSearchVal").style.display="none";
		document.getElementById("TreasuryName").style.display="inline";
	}
	else
	{
		document.getElementById("txtSearchVal").style.display="inline";
		document.getElementById("TreasuryName").style.display="none";
	}

}

function validatePPONO(object)
{	
	var elementId=object.id;
	
	var rowNum=elementId.substring(8);
	var ppoNo = document.getElementById(elementId).value;
	
	var hidListOfPPONo = document.getElementById("hidListOfPPONo").value;
	
	var tempListOfPPONo = hidListOfPPONo.substr(1,hidListOfPPONo.length-2);
	var finalListOfPPONo = tempListOfPPONo.split(",");
	
	if(ppoNo != "" && ppoNo != null)
	{
		for(var cnt=0;cnt<finalListOfPPONo.length;cnt++)
		{
			if(ppoNo == finalListOfPPONo[cnt].trim())
			{
				alert('Invalid PPO Number.Please re-enter it.');
				document.getElementById(elementId).value="";
				document.getElementById(elementId).focus();
				return;
			}
		}
	}
	
}
function validateBrnchCode(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(13);
	var branchCode=document.getElementById(elementId).value;
	var hidListOfBrnchCode = document.getElementById("hidListOfBrnchCode").value;
	var flag=0;
	var tempListOfBrnchCode = hidListOfBrnchCode.substr(1,hidListOfBrnchCode.length-2);
	var finalListOfBrnchCode = tempListOfBrnchCode.split(",");
	
	
	if(branchCode != "" || branchCode != null)
	{
		for(var cnt=0;cnt<finalListOfBrnchCode.length;cnt++)
		{
			
			if(Number(branchCode) == Number(finalListOfBrnchCode[cnt]))
			{
				flag = 1;
				
			}
		}
		if(flag == 1)
		{
			getNomBnkBrnchNameFrmBnkCode(object);
		}
		else
		{
			alert('This branch is not mapped with any auditor.Please enter different branch code.');
			document.getElementById("txtBranchCode"+rowNum).value="";
			var theOption = new Option;
			theOption.value = "-1";
			theOption.text ="--Select--";
			document.getElementById("cmbTargetBranchName"+rowNum).options.length = 0;
			document.getElementById("cmbTargetBranchName"+rowNum).options[0]=theOption;
	 		document.getElementById("cmbTargetBranchName"+rowNum).disabled=false;
	 		document.getElementById("cmbBankName"+rowNum).options[0].value="-1";
	 		document.getElementById("cmbBankName"+rowNum).options[0].text="--Select--";
	 		document.getElementById("cmbBankName"+rowNum).disabled=false;
			return;
		}
	}
	
}
function generateTransferLetter()
{
	var requestId = document.getElementById("txtRequestId").value;
	var PnsnrCode = document.getElementById("txtPnsnrCode").value;
	if(document.getElementById("txtPpoNo").value == "" )
	{
		alert('Please Enter PPO No.');
		return;
	}
	if(requestId != null && requestId.length > 0)
	{
		gFlag = 1;
		
	}
	if(gFlag == 0)
	{
		alert('Please transfer this ppo before generate transfer letter.');
		return;
	}
/*	if(document.getElementById("hidPpoNo").value == "" )
	{
		alert('Please Enter PPO No.');
		return;
	}
*/	
	if(gFlag == 1)
	{
		var url = "ifms.htm?actionFlag=generateTransferLetter&PnsnrCode="+PnsnrCode;
		var newWindow = null;
	   	var height = screen.height-50;
	   	var width = screen.width-50;
	   	var urlstring = url;
	   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
	   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
	}
}
function checkedRadioVal(obj)
{
	if(obj.value == 'OtherState')
	{
		document.getElementById("txtOtherState").style.display="inline";
		document.getElementById("cmbNewTreasury").disabled=true;
		document.getElementById("cmbNewTreasury").value="-1";
	}
	else
	{
		document.getElementById("txtOtherState").style.display="none";
		document.getElementById("txtOtherState").value="";
		document.getElementById("cmbNewTreasury").disabled=false;
	}
}
function getTransferDtlsFromReqId()
{

	if(document.getElementById("txtRequestId").value == "")
	{
		alert('Please enter request ID to proceed ahead.');
		return;
	}
	var requestId = document.getElementById("txtRequestId").value;
	var url = 'ifms.htm?actionFlag=getTransferDtlsFromReqId&requestId='+requestId;
	var myAjax = new Ajax.Request(url,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {TransferDtlsCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
}
function TransferDtlsCaseStateChanged(myAjax) 
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCTRANSFERDTLS');
	var emptyList = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	if(emptyList == "EmptyList")
	{
		alert('Invalid Request ID.');
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtName").value ="";
		document.getElementById("txtOldTreasury").value ="";
		document.getElementById("txtLocCode").value ="";
		document.getElementById("txtPnsnrCode").value ="";
		document.getElementById("txtCaseStatus").value ="";
		document.getElementById("txtRequestId").value="";
		return;
	}
	
	var pnsnrCode=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	var ppoNo=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var pnsnrName=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var trsryName=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
	var agFlag=XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;
	var otherStateName=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	var toLocation=XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	var caseStatus=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	var toLocationName=XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
	var transferDtlsId=XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
	var currLocation=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;
	//if(caseStatus == 'TransferNew' || caseStatus == 'TransferToOtherState')
	if(caseStatus == 'Approved')
	{
		document.getElementById("txtPpoNo").value=ppoNo;
		document.getElementById("txtName").value = pnsnrName;
		document.getElementById("txtOldTreasury").value = trsryName;
		document.getElementById("txtPnsnrCode").value = pnsnrCode;
		document.getElementById("txtLocCode").value=currLocation;
		document.getElementById("hdnTransferDtlsId").value=transferDtlsId;
		if(agFlag == 'Y')
		{
			document.getElementById("RadioButtonInAg").checked  = true;
			document.getElementById("cmbNewTreasury").value=toLocation;
			document.getElementById("cmbNewTreasury").disabled=false;
			document.getElementById("txtOtherState").style.display="none";
			document.getElementById("txtOtherState").value="";
		}
		if(agFlag == 'N')
		{
			document.getElementById("RadioButtonOutAg").checked  = true;
			document.getElementById("cmbNewTreasury").disabled=false;
			document.getElementById("cmbNewTreasury").value=toLocation;
			document.getElementById("txtOtherState").style.display="none";
			document.getElementById("txtOtherState").value="";
		}
		if(agFlag == 'O')
		{
			document.getElementById("RadioButtonState").checked  = true;
			document.getElementById("txtOtherState").style.display="inline";
			document.getElementById("txtOtherState").value = otherStateName;
			document.getElementById("cmbNewTreasury").disabled=true;
			document.getElementById("cmbNewTreasury").value="-1";
		}
		document.getElementById("hidPpoNo").value = ppoNo;
		document.getElementById("txtCaseStatus").value = caseStatus;
	}
}
