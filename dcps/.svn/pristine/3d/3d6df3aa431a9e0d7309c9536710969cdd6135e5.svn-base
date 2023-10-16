var gFlag = 0;
var defaultSchemeCode = "0071018201";
function getPPONameFromPPONo()
{
	var txtPpoNo = document.getElementById("txtPpoNo").value;
	var url = "ifms.htm?actionFlag=getPPODtls&txtPpoNo="+txtPpoNo;
	document.getElementById("txtRequestId").value="";
	document.getElementById("txtDateOfExpry").value="";
	document.getElementById("txtTotalRecvAmnt").value="";
	document.getElementById("txtSchemeCode").value=defaultSchemeCode;
	var myAjax = new Ajax.Request(url,
	{
        method: 'post',
        asynchronous: false,
        parameters:url,
        onSuccess: function(myAjax) {getNameCaseStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...')} 
	});

}


function getNameCaseStateChanged(myAjax) 
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPODtls');
		
	var pnsnrName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	var dateOfDeath = "";
	var currHeadCode = "";
	var caseStatus = "";
	var commensionDate = "";
	var totalRecoveryAmt = "";
	if(pnsnrName == 'EmptyList')
	{
		alert('No Such PPO Exists.');
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtPensrName").value="";
		document.getElementById("txtCurrHeadCode").value="";
		document.getElementById("txtRequestId").value = "";
		return;
	}
	var pnsnrCode=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	
	if(XmlHiddenValues[0].childNodes[2].childNodes[0] != null)
	{
		commensionDate =XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	}
	if(XmlHiddenValues[0].childNodes[3].childNodes[0] != null)
	{
		caseStatus =XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
	}
	if(XmlHiddenValues[0].childNodes[4].childNodes[0] != null)
	{
		currHeadCode=XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;
	}
	if(XmlHiddenValues[0].childNodes[6].childNodes[0] != null)
	{
		dateOfDeath = XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	}
	if(XmlHiddenValues[0].childNodes[7].childNodes[0] != null)
	{
		totalRecoveryAmt = XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	}
	
	//var requestId=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	//alert('requestId is  ' + requestId);
	var tempDt = commensionDate.split("-");
	var year=tempDt[0];
	var month=tempDt[1];
	var day=tempDt[2].substr(0,2);
	var finalDate = day+"/"+month+"/"+year;
	if(caseStatus != 'Approved' && caseStatus != 'Identified')
	{
		alert('No such PPO exists.');
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtPensrName").value="";
		document.getElementById("txtCurrHeadCode").value="";
		document.getElementById("txtRequestId").value = "";
		return;
	}
	
	document.getElementById("txtPensrName").value = pnsnrName;
	document.getElementById("hidPnsnrCode").value = pnsnrCode;
	document.getElementById("hidCommensionDate").value = finalDate;
	document.getElementById("txtCurrHeadCode").value = currHeadCode;
	document.getElementById("txtDateOfExpry").value = dateOfDeath;
	document.getElementById("txtTotalRecvAmnt").value = totalRecoveryAmt;
	if(Number(XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue) != 0)
	{
		document.getElementById("txtRequestId").value = XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	}
	else
	{
		document.getElementById("txtRequestId").value = "";
	}
	document.getElementById("btnSave").disabled = false;
}

function getTotalRecoveryAmnt()
{
	if(document.getElementById("txtDateOfExpry").value == "")
	{
		alert('Please select date of expiry to proceed ahead.');
		return;
	}
	var dateOfExpry = document.getElementById("txtDateOfExpry").value;
	var url = "ifms.htm?actionFlag=getTotalRecoveryAmnt&dateOfExpry="+dateOfExpry+"&pnsnrCode="+document.getElementById("hidPnsnrCode").value;
	var myAjax = new Ajax.Request(url,
	{
        method: 'post',
        asynchronous: false,
        parameters:url,
        onSuccess: function(myAjax) {getTotalRecoveryAmntStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...')} 
	});
	
}

function getTotalRecoveryAmntStateChanged(myAjax) 
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCRECOVERYAMNT');
	var totalRecoveryAmnt=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	document.getElementById("txtTotalRecvAmnt").value = totalRecoveryAmnt;
}

function saveOverPmntRecovery()
{

	var uri;
	var requestId = document.getElementById("txtRequestId").value;
	if(requestId != null && requestId.length > 0)
	{
		alert('Over payment recovery has been already set for this PPO case.');
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtPensrName").value="";
		document.getElementById("txtCurrHeadCode").value="";
		document.getElementById("txtRequestId").value = "";
		document.getElementById("btnSave").disabled = "disabled" ;
		return;
	}
	if(document.getElementById("txtPpoNo").value == "" || document.getElementById("txtDateOfExpry").value == "" || document.getElementById("txtSchemeCode").value == "")
	{
		alert('All details are compulsory to fill.Please fill up all details.');
		return;
	}
	uri = 'ifms.htm?actionFlag=saveOverPmntRecovery';
	var url = runForm(0); 
	url = uri + url;  
	var myAjax = new Ajax.Request(uri,
	{
        method: 'post',
        asynchronous: false,
        parameters:url,
        onSuccess: function(myAjax) {saveOverPmntRecoveryStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...')} 
	});

	
}
function saveOverPmntRecoveryStateChanged(myAjax) 
{ 
   
   var XMLDoc=myAjax.responseXML.documentElement;
   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
   var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
   var requestID = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
		
	if(string == 'Add')
	{
		alert("Over payment recovery details has been saved successfully.Request ID for this PPO is : "+ requestID);
		document.getElementById("txtRequestId").value = requestID;
		document.getElementById("btnSave").disabled = "disabled";
		gFlag = 1;
	}
			
		
	
}

function getRecoveryDtlsFromReqId()
{
	if(document.getElementById("txtRequestId").value == "")
	{
		alert('Please enter request ID to proceed ahead.');
		return;
	}
	var requestId = document.getElementById("txtRequestId").value;
	var url = 'ifms.htm?actionFlag=getRecoveryDetails&requestId='+requestId;
	var myAjax = new Ajax.Request(url,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {RecoveryDtlsCaseStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
	
	
}

function RecoveryDtlsCaseStateChanged(myAjax) 
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCRECOVERYDTLS');
	var emptyList = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	if(emptyList == "EmptyList")
	{
		alert('Invalid Request ID.');
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtPensrName").value="";
		document.getElementById("txtDateOfExpry").value="";
		document.getElementById("txtTotalRecvAmnt").value="";
		document.getElementById("txtSchemeCode").value="";
		document.getElementById("txtCurrHeadCode").value="";
		document.getElementById("txtAmntRecovered").value="";
		document.getElementById("txtRequestId").value="";
		document.getElementById("txtPayOrderNo").value = "";
		document.getElementById("txtPayOrderDate").value = "";
		document.getElementById("txtPayOrderAmnt").value = "";
		document.getElementById("txtChallanNo").value = "";
		document.getElementById("txtChallanDate").value = "";
		return;
	}
	
	var pnsnrCode=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	var ppoNo=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var pnsnrName=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var deathDate=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
	var totRecoveryAmnt=XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;
	var schemeCode=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	var headCode=XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	var status=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	
	
	var tempDt = deathDate.split("-");
	var year=tempDt[0];
	var month=tempDt[1];
	var day=tempDt[2].substr(0,2);
	var finalDate = day+"/"+month+"/"+year;
	if(status == "Rejected")
	{
		alert("Over payment recovery is already Rejected");
		document.getElementById("txtRequestId").value = "";
		document.getElementById("txtPpoNo").value="";
		document.getElementById("txtPensrName").value="";
		document.getElementById("txtDateOfExpry").value="";
		document.getElementById("txtTotalRecvAmnt").value="";
		document.getElementById("txtSchemeCode").value=defaultSchemeCode;
		document.getElementById("txtCurrHeadCode").value="";
		document.getElementById("txtAmntRecovered").value="";
		document.getElementById("txtRequestId").value="";
		document.getElementById("txtSchemeCode").value="";
		document.getElementById("txtPayOrderNo").value = "";
		document.getElementById("txtPayOrderDate").value = "";
		document.getElementById("txtPayOrderAmnt").value = "";
		document.getElementById("txtChallanNo").value = "";
		document.getElementById("txtChallanDate").value = "";
		return;
	}
	if(status == "Approved")
	{
		var payOrderNo=XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
		var payOrderDate=XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
		var challanNo=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;
		var challanDate=XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue;
		var payOrderAmnt=XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue;
		
		alert("Over payment recovery is already Approved");
		document.getElementById("hidPnsnrCode").value = pnsnrCode;
		document.getElementById("txtPpoNo").value = ppoNo;
		document.getElementById("txtPensrName").value = pnsnrName;
		document.getElementById("txtDateOfExpry").value = finalDate;
		document.getElementById("txtTotalRecvAmnt").value = Number(totRecoveryAmnt);
		document.getElementById("txtAmntRecovered").value == "";
		document.getElementById("txtSchemeCode").value = schemeCode;
		document.getElementById("txtCurrHeadCode").value = headCode;
		document.getElementById("txtPayOrderNo").value = payOrderNo;
		document.getElementById("txtPayOrderDate").value = payOrderDate;
		document.getElementById("txtPayOrderAmnt").value = payOrderAmnt;
		document.getElementById("txtChallanNo").value = challanNo;
		document.getElementById("txtChallanDate").value = challanDate;

		return;
	}
	document.getElementById("hidPnsnrCode").value = pnsnrCode;
	document.getElementById("txtPpoNo").value = ppoNo;
	document.getElementById("txtPensrName").value = pnsnrName;
	document.getElementById("txtDateOfExpry").value = finalDate;
	document.getElementById("txtTotalRecvAmnt").value = Number(totRecoveryAmnt);
	document.getElementById("txtAmntRecovered").value = Number(totRecoveryAmnt);
	document.getElementById("txtSchemeCode").value = schemeCode;
	document.getElementById("txtCurrHeadCode").value = headCode;
//	document.getElementById("txtPayOrderNo").value = payOrderNo;
//	document.getElementById("txtPayOrderDate").value = payOrderDate;
//	document.getElementById("txtChallanNo").value = challanNo;
//	document.getElementById("txtChallanDate").value = challanDate;
//	document.getElementById("txtPayOrderAmnt").value = payOrderAmnt;
			
}

function approveOverPmntRecovery(lStrActionFlag)
{
	if(document.getElementById("txtRequestId").value == "" || document.getElementById("txtRequestId").value == null)
	{
		alert('Please enter request ID to proceed ahead.');
		return;
	}
	if(document.getElementById("txtAmntRecovered").value == "" || document.getElementById("txtAmntRecovered").value == null)
	{
		alert('Please enter recoverd amount to proceed ahead.');
		return;
	}
	var flag = 0;
	var requestId = document.getElementById("txtRequestId").value;
	var PnsnrCode = document.getElementById("hidPnsnrCode").value;
	
	var ListOfPnsnrCode = document.getElementById("hidListOfPnsnrCode").value;
	var tempPnsnrCodeArr = ListOfPnsnrCode.substr(1,ListOfPnsnrCode.length-2);
	var arrTotalPnsnrCode = tempPnsnrCodeArr.split(",");
	
	var ListOfStatus = document.getElementById("hidListOfStatus").value;
	var tempStatusArr = ListOfStatus.substr(1,ListOfStatus.length-2);
	var arrTotalStatus = tempStatusArr.split(",");
	
	for(var j=0;j<arrTotalPnsnrCode.length;j++)
	{
		if(Number(PnsnrCode) == Number(arrTotalPnsnrCode[j]))
		{
			if(arrTotalStatus[j].trim() == 'Approved')
			{
				flag = 1;
			}
			
		}
	}
	if(flag == 1)
	{
		alert("You cannot approve this over payment recovery because it has already approved.");
		return;
	}
	var amntRecovered = document.getElementById("txtAmntRecovered").value;
	var payOrderNo = document.getElementById("txtPayOrderNo").value;
	var payOrderDate = document.getElementById("txtPayOrderDate").value;
	var payOrderAmnt = document.getElementById("txtPayOrderAmnt").value;
	var challanNo = document.getElementById("txtChallanNo").value;
	var challanDate = document.getElementById("txtChallanDate").value;
	var url = 'ifms.htm?actionFlag=approveOverPmntRecovery&requestId='+requestId+'&amntRecovered='+amntRecovered+'&payOrderNo='+payOrderNo+
				'&payOrderDate='+payOrderDate+'&payOrderAmnt='+payOrderAmnt+'&challanNo='+challanNo+'&challanDate='+challanDate+"&lStrActionFlag="+lStrActionFlag;
	var myAjax = new Ajax.Request(url,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {approveOverPmntRecoveryStateChanged(myAjax,lStrActionFlag);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});

	
}

function approveOverPmntRecoveryStateChanged(myAjax,lStrActionFlag) 
{
	if(lStrActionFlag == "A")
	{
		alert('Over payment recovery details have been approved successfully.');
	}
	else
	{
		alert('Over payment recovery request is rejected successfully.');
	}
	self.location.reload();

}

function compareCmnsnDate(CmnsnDt,DeathDt)
{
	var commensionDate = CmnsnDt.value;
	var deathDate = DeathDt.value;

	
	var commensionDateArr = commensionDate.split("/");
	var commensionDateDay = commensionDateArr[0];
	var commensionDateMonth = commensionDateArr[1];
	var commensionDateYear = commensionDateArr[2];
	
	var deathDateArr = deathDate.split("/");
	var deathDateDay = deathDateArr[0];
	var deathDateMonth = deathDateArr[1];
	var deathDateYear = deathDateArr[2];
	
	if(Number(commensionDateYear) > Number(deathDateYear))
	{
		alert('Date of Death should be greater than or equal to Commension Date.');
		document.getElementById("txtDateOfExpry").value ="";
		document.getElementById("txtDateOfExpry").focus();
		
	}
	if(Number(commensionDateYear) == Number(deathDateYear))
	{
		if(Number(commensionDateMonth) > Number(deathDateMonth))
		{
			alert('Date of Death should be greater than or equal to Commension Date.');
			document.getElementById("txtDateOfExpry").value ="";
			document.getElementById("txtDateOfExpry").focus();
		}
		else(Number(commensionDateMonth) == Number(deathDateMonth))
		{
			if(Number(commensionDateDay) > Number(deathDateDay))
			{
				alert('Date of Death should be greater than or equal to Commension Date.');
				document.getElementById(""+deathDate.id+"").value ="";
				document.getElementById(""+deathDate.id+"").focus();
			}
		}
	}
	
	
}

function generateRecoveryLtr()
{
	if(document.getElementById("txtPpoNo").value == "" || document.getElementById("txtDateOfExpry").value == "" || document.getElementById("txtSchemeCode").value == "" )
	{
		alert('All details are compulsory to fill.Please fill up all details.');
		return;
	}
	var flag = 0;
	var ListOfPnsnrCode = document.getElementById("hidListOfPnsnrCode").value;
	var PnsnrCode = document.getElementById("hidPnsnrCode").value;
	var tempPnsnrCodeArr = ListOfPnsnrCode.substr(1,ListOfPnsnrCode.length-2);
	var arrTotalPnsnrCode = tempPnsnrCodeArr.split(",");
	for(var j=0;j<arrTotalPnsnrCode.length;j++)
	{
		if(Number(PnsnrCode) == Number(arrTotalPnsnrCode[j]))
		{
			flag = 1;
			gFlag = 0;
		}
	}
	if(flag == 0 && gFlag == 0)
	{
		alert('Please save recovery details before generate over payment recovery letter.');
	}
	else
	{
		var url = "ifms.htm?actionFlag=generateRecoveryLetter&PnsnrCode="+PnsnrCode;
		var newWindow = null;
	   	var height = screen.height-50;
	   	var width = screen.width-50;
	   	var urlstring = url;
	   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
	   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
	}
	
}

function showSchemeCodePopup(elementId)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType=R&elementId='+elementId;
	var urlstyle = "height=500,width=650,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "SchemeCodeList", urlstyle);
}

function validateSchemeCode(object)
{
	var schemeCode=object.value;
	var url;
	if(schemeCode != "")
	{
		url="ifms.htm?actionFlag=validateSchemeCode&SchemeType=R&SchemeCode="+schemeCode;
		validateSchemeCodeUsingAjax(url,object.id,schemeCode);
	}
}
function validateSchemeCodeUsingAjax(uri,elementId,schemeCode)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&SchemeCode="+schemeCode,
		        onSuccess: function(myAjax) {
					getStateChangedForValidSchemeCode(myAjax,elementId);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
   
}
function getStateChangedForValidSchemeCode(myAjax,elementId)
{
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    
	     if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct Scheme Code.");
	    	 document.getElementById(elementId).value="";
    	 document.getElementById(elementId).focus();
	     }

}