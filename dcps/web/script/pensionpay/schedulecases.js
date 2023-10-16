var gFlag;
function getScheduleForPnsr() {
	getScheduleForPnsrUsingAJAX();
//	disableButtons();
//	showProgressbar();
//	gRowNumList = "";
//	getPensionerDtlId();
//	if(gRowNumList != null && gRowNumList.length>0)
//	{
//		xmlHttp = GetXmlHttpObject();
//		if (xmlHttp == null) {
//			return;
//		}
//		
//		var lArrRowNumList = gRowNumList.split("~");
//		var lArrUnscheduledRows = new Array();
//		var lStrUnscheduledRows = "";
//		var lArrPnsrCode = new Array();
//		var lStrPnsrCode = "";
//		var cnt = 0;
//		for(var i=0;i<lArrRowNumList.length;i++)
//		{
//			var lSchStatus = document.getElementById("txtSchStatus"+lArrRowNumList[i]).innerHTML;
//			var lPnsrCode = document.getElementById("hdnPensionerId"+lArrRowNumList[i]).value;
//			if((lSchStatus != statusAwaited) && (lSchStatus != statusReminder))
//			{
//				lArrUnscheduledRows[cnt] = lArrRowNumList[i];
//				lArrPnsrCode[cnt] = lPnsrCode;
//				cnt++;
//			}
//			else
//			{
//				alert("Some of the selected cases are already scheduled.Please deselect that cases.");
//				hideProgressbar();
//				enableButtons();
//				return false;
//			}
//		}
//		lStrUnscheduledRows = lArrUnscheduledRows.join("~");
//		lStrPnsrCode = lArrPnsrCode.join("~");
//		var uri = "ifms.htm?actionFlag=scheduleIdentification";
//		var url ="rowNum="+ lStrUnscheduledRows+"&pnsrCode="+lStrPnsrCode;
//		xmlHttp.onreadystatechange = getScheduleList;
//		xmlHttp.open("POST", uri, true);
//		xmlHttp.setRequestHeader("Content-type",
//				"application/x-www-form-urlencoded");
//		xmlHttp.send(url);
//	}
//	else{
//		hideProgressbar();
//		enableButtons();
//		alert("Please select any one case.");
//	}
}

function getScheduleForPnsrUsingAJAX()
{
	disableButtons();
	showProgressbar();
	gRowNumList = "";
	getPensionerDtlId();
	if(gRowNumList != null && gRowNumList.length>0)
	{
		var lArrRowNumList = gRowNumList.split("~");
		var lArrUnscheduledRows = new Array();
		var lStrUnscheduledRows = "";
		var lArrPnsrCode = new Array();
		var lArrPpoNo = new Array();
		var lStrPnsrCode = "";
		var lStrPpoNo = "";
		var cnt = 0;
		for(var i=0;i<lArrRowNumList.length;i++)
		{
			var lSchStatus = document.getElementById("txtSchStatus"+lArrRowNumList[i]).innerHTML;
			var lPnsrCode = document.getElementById("hdnPensionerId"+lArrRowNumList[i]).value;
			var lPpoNo = document.getElementById("lblPpoNo"+lArrRowNumList[i]).innerHTML;
			if((lSchStatus != statusAwaited) && (lSchStatus != statusReminder))
			{
				lArrUnscheduledRows[cnt] = lArrRowNumList[i];
				lArrPnsrCode[cnt] = lPnsrCode;
				lArrPpoNo[cnt] = lPpoNo;
				cnt++;
			}
			else
			{
				alert("Some of the selected cases are already scheduled.Please deselect that cases.");
				hideProgressbar();
				enableButtons();
				return false;
			}
		}
		lStrUnscheduledRows = lArrUnscheduledRows.join("~");
		lStrPnsrCode = lArrPnsrCode.join("~");
		lStrPpoNo = lArrPpoNo.join("~");
		var uri = "ifms.htm?actionFlag=scheduleIdentification";
		var url ="rowNum="+ lStrUnscheduledRows+"&pnsrCode="+lStrPnsrCode+"&ppoNo="+lStrPpoNo;	
		
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&rowNum="+ lStrUnscheduledRows+"&pnsrCode="+lStrPnsrCode+"&ppoNo="+lStrPpoNo,
		        onSuccess: function(myAjax) {
						getScheduleListUsingAJAX(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	}
	else{
		hideProgressbar();
		enableButtons();
		alert("Please select any one case.");
	}
}
//---When call time is textbox
/*
function getScheduleListUsingAJAX(myAjax)
{

	var XMLDoc =  myAjax.responseXML.documentElement;
	if (XMLDoc != null) {
		var lArrRowNo = XMLDoc.getElementsByTagName('ROWNO');
		var lArrCallDate = XMLDoc.getElementsByTagName('CALLDATE');
		var lArrCallTime = XMLDoc.getElementsByTagName('CALLTIME');
		var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
		var lArrPnsrId = document.getElementsByName("hdnPensionerId");
		for ( var j = 1; j <= lArrPnsrId.length; j++) {
			document.getElementById("txtCalledDate" + j).value = "";
			document.getElementById("txtCalledTime" + j).value = "";
		}
		for ( var i = 0; i < lArrRowNo.length; i++) {
			document.getElementById("txtCalledDate" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrCallDate[i].childNodes[0].nodeValue;
			document.getElementById("txtCalledTime" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrCallTime[i].childNodes[0].nodeValue;
			document.getElementById("txtSchStatus" + lArrRowNo[i].childNodes[0].nodeValue).innerHTML = statusAwaited;
			//document.getElementById("hidSlotNo" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrSlotNo[i].childNodes[0].nodeValue;
			//document.getElementById("txtCalledDate" + lArrRowNo[i].text).setAttribute("className","unSchCol");
			//document.getElementById("txtCalledTime" + lArrRowNo[i].text).setAttribute("className","unSchCol");
		}
		alert("Call date and time for selected pensioners scheduled successfully");
	}
		enableButtons();
		hideProgressbar();
}
*/


function getScheduleListUsingAJAX(myAjax)
{

	var XMLDoc =  myAjax.responseXML.documentElement;
	if (XMLDoc != null) {
		var lArrRowNo = XMLDoc.getElementsByTagName('ROWNO');
		var lArrCallDate = XMLDoc.getElementsByTagName('CALLDATE');
		var lArrCallTime = XMLDoc.getElementsByTagName('CALLTIME');
		var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
		var lArrPnsrId = document.getElementsByName("hdnPensionerId");
		for ( var j = 1; j <= lArrPnsrId.length; j++) {
			document.getElementById("txtCalledDate" + j).value = "";
			document.getElementById("cmbCallTime" + j).value = "";
		}
		for ( var i = 0; i < lArrRowNo.length; i++) {
			document.getElementById("txtCalledDate" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrCallDate[i].childNodes[0].nodeValue;
			document.getElementById("cmbCallTime" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrSlotNo[i].childNodes[0].nodeValue;
			document.getElementById("txtSchStatus" + lArrRowNo[i].childNodes[0].nodeValue).innerHTML = statusAwaited;
			//document.getElementById("cmbCallTime" + lArrRowNo[i].childNodes[0].nodeValue).selected = "selected";
			document.getElementById("callDatePicker" + lArrRowNo[i].childNodes[0].nodeValue).style.display = "inline";
			document.getElementById("cmbCallTime" + lArrRowNo[i].childNodes[0].nodeValue).disabled = "";
			//document.getElementById("hidSlotNo" + lArrRowNo[i].childNodes[0].nodeValue).value = lArrSlotNo[i].childNodes[0].nodeValue;
			//document.getElementById("txtCalledDate" + lArrRowNo[i].text).setAttribute("className","unSchCol");
			//document.getElementById("txtCalledTime" + lArrRowNo[i].text).setAttribute("className","unSchCol");
		}
		alert("Call date and time for selected pensioners scheduled successfully");
	}
		enableButtons();
		hideProgressbar();
}
function getScheduleList() {
	if (xmlHttp.readyState == complete_state) {
		var XMLDoc = xmlHttp.responseXML.documentElement;
		if (XMLDoc != null) {
			var lArrRowNo = XMLDoc.getElementsByTagName('ROWNO');
			var lArrCallDate = XMLDoc.getElementsByTagName('CALLDATE');
			var lArrCallTime = XMLDoc.getElementsByTagName('CALLTIME');
			var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
			var lArrPnsrId = document.getElementsByName("hdnPensionerId");
			for ( var j = 1; j <= lArrPnsrId.length; j++) {
				document.getElementById("txtCalledDate" + j).value = "";
				document.getElementById("txtCalledTime" + j).value = "";
			}
			for ( var i = 0; i < lArrRowNo.length; i++) {
				document.getElementById("txtCalledDate" + lArrRowNo[i].text).value = lArrCallDate[i].text;
				document.getElementById("txtCalledTime" + lArrRowNo[i].text).value = lArrCallTime[i].text;
				document.getElementById("txtSchStatus" + lArrRowNo[i].text).innerHTML = statusAwaited;
				document.getElementById("hidSlotNo" + lArrRowNo[i].text).value = lArrSlotNo[i].text;
				//document.getElementById("txtCalledDate" + lArrRowNo[i].text).setAttribute("className","unSchCol");
				//document.getElementById("txtCalledTime" + lArrRowNo[i].text).setAttribute("className","unSchCol");
			}
			alert("Call date and time for selected pensioners scheduled successfully");
		}
	}
	 enableButtons();
	 hideProgressbar();
}
function saveScheduleUsingAJAX()
{
	pensionerDtlId = "";
	pensionerId = "";
	gRowNumList="";
	getPensionerDtlId();
	if(gRowNumList != null && gRowNumList.length>0)
	{
		if(validateBankDtlsForAccountNo())
		{
			disableButtons();
			showProgressbar();
			var uri = "ifms.htm?actionFlag=saveScheduleDtls";
			var url = "pensionerDtlId="+pensionerDtlId+"&pensionerId="+pensionerId+"&rowNum="+gRowNumList+prepareUrlForSave();
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: true,
				        parameters: url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForSaveSchedule(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
		}
	}else{
		hideProgressbar();
		enableButtons();
		alert("Please select any one case.");
	}
}
function getDataStateChangedForSaveSchedule(myAjax)
{
	alert("Data Saved Successfully");
	enableButtons();
	//window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor=5";
	window.location.reload();
}
function saveSchedule() {
	saveScheduleUsingAJAX();
//	pensionerDtlId = "";
//	pensionerId = "";
//	gRowNumList="";
//	getPensionerDtlId();
//	if(gRowNumList != null && gRowNumList.length>0)
//	{
//		if(validateBankDtlsForAccountNo())
//		{
//			disableButtons();
//			showProgressbar();
//			var uri = "ifms.htm?actionFlag=saveScheduleDtls";
//			xmlHttp = GetXmlHttpObject();
//			if (xmlHttp == null) {
//				return;
//			}
//			var url = "pensionerDtlId="+pensionerDtlId+"&pensionerId="+pensionerId+runForm(0);
//			xmlHttp.onreadystatechange = function(){
//				if (xmlHttp.readyState==complete_state)
//				  {
//						alert("Data Saved Successfully");
//						enableButtons();
//						window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor=5";
//				  }
//				
//			};
//			xmlHttp.open("POST", uri, true);
//			xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//			xmlHttp.send(url);
//		}
//	}
//	else{
//		hideProgressbar();
//		enableButtons();
//		alert("Please select any one case.");
//	}
}

function showCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
function showReschedular()
{
	gRowNumList="";
	getPensionerDtlId();
	if(gRowNumList != null && gRowNumList.length>0)
	{
		var lArrRowNum = gRowNumList.split("~");
		var uri = "ifms.htm?actionFlag=showReschedule";
		//var lArrUnSch = document.getElementsByClassName("unSchCol");
		/*for(var j=0;j<lArrUnSch.length;j++)
		{
			lArrUnSch[j].value = "";
			lArrUnSch[j].setAttribute("className","");
		}*/
		if(lArrRowNum.length == 1)
		{
			var lPensionerId = document.getElementById("hdnPensionerId"+lArrRowNum[0]);
			scheduleStatus = document.getElementById("txtSchStatus"+lArrRowNum[0]).innerHTML;
			if((scheduleStatus == statusReminder) || (scheduleStatus == statusAwaited))
			{
				
				var url = "&rowNum="+lArrRowNum[0];
				uri = uri+url;
				var urlstyle = "height=250,width=500,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=200,left=400";
				window.open(uri, "open", urlstyle);
			}
			else{	
				alert("Only scheduled cases can be rescheduled.");
			}
		}
		
		else
		{
			alert("Please Select One Case At a Time");
		}
	}
	else{
		alert("Please select any one case.");
	}
}

function enableButtons()
{
	if(document.getElementById("btnSchedule") != null)
	{
		document.getElementById("btnSchedule").disabled = "";
	}
	if(document.getElementById("btnReschedule") != null)
	{
		document.getElementById("btnReschedule").disabled = "";
	}
	if(document.getElementById("btnGenerateLetter") != null)
	{
		document.getElementById("btnGenerateLetter").disabled = "";
	}
	if(document.getElementById("btnApprove") != null)
	{
		document.getElementById("btnApprove").disabled = "";
	}
	if(document.getElementById("btnSave") != null)
	{
		document.getElementById("btnSave").disabled = "";
	}
}

function disableButtons()
{
	if(document.getElementById("btnSchedule") != null)
	{
		document.getElementById("btnSchedule").disabled = "disabled";
	}
	if(document.getElementById("btnReschedule") != null)
	{
		document.getElementById("btnReschedule").disabled = "disabled";
	}
	if(document.getElementById("btnGenerateLetter") != null)
	{
		document.getElementById("btnGenerateLetter").disabled = "disabled";
	}
	if(document.getElementById("btnApprove") != null)
	{
		document.getElementById("btnApprove").disabled = "disabled";
	}
	if(document.getElementById("btnSave") != null)
	{
		document.getElementById("btnSave").disabled = "disabled";
	}
}

function approveSchedule()
{
	getPensionerDtlId();
	var lArrRowNumList = gRowNumList.split("~");
	var lPnsnRqstId = document.getElementById("hdnpnsnrqstid"+lArrRowNumList[0]).value;
	var lPnsrCode = document.getElementById("hdnPensionerId"+lArrRowNumList[0]).value;
	var lBrnchCode = document.getElementById("txtBankBranchCode"+lArrRowNumList[0]).value;
	var lBankName = document.getElementById("cmbBankName"+lArrRowNumList[0]).value;
	var lBranchName = document.getElementById("cmbBankBrnchName"+lArrRowNumList[0]).value;
	var lAcNo = document.getElementById("txtAccountNo"+lArrRowNumList[0]).value;
	var lCallDate = document.getElementById("txtCalledDate"+lArrRowNumList[0]).value;
	var lCallTime = document.getElementById("txtCalledTime"+lArrRowNumList[0]).value;
	var lPnsrName = document.getElementById("lblPnsrName"+lArrRowNumList[0]).innerHTML;
	var uri = "ifms.htm?actionFlag=approveSchedule";
	var url ="pnsrCode="+ lPnsrCode
			+"&branchCode="+lBrnchCode
			+"&bankName="+lBankName
			+"&branchName="+lBranchName
			+"&acNo="+lAcNo
			+"&pnsnRqstId="+lPnsnRqstId;		
	
	if(validateApproveSchedule())
	{
		disableButtons();
		showProgressbar();
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			return;
		}
		xmlHttp.onreadystatechange = function(){
			if (xmlHttp.readyState==complete_state)
			  {
				var XMLDoc = xmlHttp.responseXML.documentElement;
				if (XMLDoc != null) {
					var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
					if(lArrMessage != null && lArrMessage.length > 0)
					{
						alert("Please Enter "+lArrMessage[0].text);
						hideProgressbar();
						enableButtons();
					}
					else{
						alert(lPnsrName+" Is Identified Successfully");
						window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor=5";
						enableButtons();
					}
				
			  }
							  }	
		};
		xmlHttp.open("POST", uri, true);
		xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlHttp.send(url);
	}
}

function validateApproveSchedule()
{
	gRowNumList="";
	getPensionerDtlId();
	var lArrRowNum = gRowNumList.split("~");
//	if(lArrRowNum.length == 1)
//	{
	for(var cnt = 0;cnt<lArrRowNum.length;cnt++)
	{
		var rowNum = lArrRowNum[cnt];
		var lScheduleStatus = document.getElementById("txtSchStatus"+rowNum).innerHTML;
		if(lScheduleStatus == statusAwaited)
		{
			
			var lPpoNo = document.getElementById("lblPpoNo"+rowNum).innerHTML;
			var bankBranchCode = document.getElementById("txtBankBranchCode"+rowNum).value;
			var bankCode = document.getElementById("cmbBankName"+rowNum).value;
			var branchCode = document.getElementById("cmbBankBrnchName"+rowNum).value;
			var accountNo = document.getElementById("txtAccountNo"+rowNum).value;
			
			if(checkEmptyByValue(bankCode) || checkEmptyByValue(branchCode) || checkEmptyByValue(accountNo))
			{
				if(IsEmpty("cmbBankName"+rowNum,bankNameAlert+" "+lPpoNo) == false)
				{
					return false;
				}
				else if(IsEmpty("cmbBankBrnchName"+rowNum,bankBranchAlert+" "+lPpoNo) == false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAccountNo"+rowNum,acNoAlert+" "+lPpoNo) == false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAuditorName"+rowNum,"No auditor is mapped for selected branch for "+" "+lPpoNo) == false)
				{
					return false;
				}
			}
			else if(IsEmpty("cmbCallTime"+rowNum,"Please select call time for ppo no."+" "+lPpoNo) == false)
			{
				return false;
			}
			else if(IsEmptyFun("txtCalledDate"+rowNum,"Please assign call date to ppo"+" "+lPpoNo) == false)
			{
				return false;
			}
			
		}
		else{
			alert("Identification letter cannot be generated without scheduling");
			return false;
		}
	}
	
//	}
	/*else{
		alert("At a Time Only One Case Can Be Identified");
		return false;
	}*/
	return true;
	
}

function saveDetailsAndGenerateLetter(flag)
{
	var langFlag = flag;
	
	saveDetailsAndGenerateLetterUsingAJAX(langFlag);
//	pensionerId="";
//	pensionerDtlId="";
//	gRowNumList="";
//	getPensionerDtlId();
//	document.getElementById("hdnSelectedPnsrId").value = pensionerId;
//	if(gRowNumList != null && gRowNumList.length>0)
//	{
//		if(validateApproveSchedule())
//		{
//			showProgressbar();
//			var uri = "ifms.htm?actionFlag=saveScheduleDtls";
//			xmlHttp = GetXmlHttpObject();
//			if (xmlHttp == null) {
//				return;
//			}
//			var url = "pensionerDtlId="+pensionerDtlId+"&pensionerId="+pensionerId+"&printIdentLetterFlg=Y"+runForm(0);
//			xmlHttp.onreadystatechange = function(){
//				if (xmlHttp.readyState==complete_state)
//				{
//					document.UploadedCases.action = "ifms.htm?actionFlag=genIdentLetter";
//					document.UploadedCases.submit();
//				}
//			};
//			xmlHttp.open("POST", uri, true);
//			xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//			xmlHttp.send(url);
//		}
//	}
//	else{
//		alert("Please select any one case.");
//	}
}
function saveDetailsAndGenerateLetterUsingAJAX(langFlag)
{
	pensionerId="";
	pensionerDtlId="";
	gRowNumList="";
	getPensionerDtlId();
	gFlag = langFlag;
	document.getElementById("hdnSelectedPnsrId").value = pensionerId;
	if(gRowNumList != null && gRowNumList.length>0)
	{
		if(validateApproveSchedule())
		{
			//showProgressbar();
			var uri = "ifms.htm?actionFlag=saveScheduleDtls";
			var url = "pensionerDtlId="+pensionerDtlId+"&pensionerId="+pensionerId+"&rowNum="+gRowNumList+"&printIdentLetterFlg=Y"+prepareUrlForSave();
			
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: true,
				        parameters: url,
				        onSuccess: function(myAjax) {
								getDataStateChangedForsaveDtlGenLet(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
		}
	}else{
		alert("Please select any one case.");
	}
}
function getDataStateChangedForsaveDtlGenLet(myAjax)
{
	var url = "ifms.htm?actionFlag=genIdentLetter&gFlag="+gFlag+"&hdnSelectedPnsrId="+document.getElementById("hdnSelectedPnsrId").value;
	var urlstyle = "height=650,width=900,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
	newWindow = window.open(url, "GenerateIdentificationLetter", urlstyle);
	
//	document.UploadedCases.action = "ifms.htm?actionFlag=genIdentLetter&gFlag="+gFlag;
//	document.UploadedCases.submit();
}
function validateBankDtlsForAccountNo()
{
	var lArrRowNum = gRowNumList.split("~");
	var lAcNo = "";
	var lBankCode = "";
	var lBranchCode = "";
	var rowNum = "";
	var lPpoNo = "";
	var lCallDate = "";
	var lSlotNo = "";

	for(var cnt = 0;cnt<lArrRowNum.length;cnt++)
	{
		rowNum = lArrRowNum[cnt];
		lPpoNo = document.getElementById("lblPpoNo"+rowNum).innerHTML;
		lAcNo = document.getElementById("txtAccountNo"+rowNum).value;
		lBankCode = document.getElementById("cmbBankName"+rowNum).value;
		lCallDate = document.getElementById("txtCalledDate"+rowNum).value;
		lSlotNo = document.getElementById("cmbCallTime"+rowNum).value;
		
		if(lBankCode == "-1" && lAcNo.length > 0)
		{
			if(IsEmpty("cmbBankName"+rowNum,bankNameAlert+" "+lPpoNo) == false)
			{
				return false;
			}
		}
		
		if(lBankCode != "-1" || lAcNo.length > 0)
		{
			if(IsEmpty("cmbBankBrnchName"+rowNum,bankBranchAlert+" "+lPpoNo) == false)
			{
				return false;
			}
			else if(IsEmptyFun("txtAccountNo"+rowNum,acNoAlert+" "+lPpoNo) == false)
			{
				return false;
			}
		}
		//----Validation for call date and call time
		if(lCallDate.length > 0)
		{
			if(IsEmpty("cmbCallTime"+rowNum,"Please select call time for ppo no."+" "+lPpoNo) == false)
			{
				return false;
			}
		}
		if(lSlotNo != "-1" || lSlotNo.length > 0)
		{
			if(IsEmptyFun("txtCalledDate"+rowNum,"Please assign call date to ppo"+" "+lPpoNo) == false)
			{
				return false;
			}
		}
		
	}
	return true;
}

function IsEmpty(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1")
	{
		alert(alrtStr);
		document.getElementById(varStr).focus();
		return false;
	}
	return true;
}

function prepareUrlForSave()
{
	var lArrRowNum = gRowNumList.split("~");
	var url = "";
	for(var cnt = 0;cnt<lArrRowNum.length;cnt++)
	{
		url = url+"&cmbBankName"+lArrRowNum[cnt]+"="+document.getElementById("cmbBankName"+lArrRowNum[cnt]).value
				 +"&cmbBankBrnchName"+lArrRowNum[cnt]+"="+document.getElementById("cmbBankBrnchName"+lArrRowNum[cnt]).value
				 +"&txtAccountNo"+lArrRowNum[cnt]+"="+document.getElementById("txtAccountNo"+lArrRowNum[cnt]).value
				 +"&hdnpnsnrqstid"+lArrRowNum[cnt]+"="+document.getElementById("hdnpnsnrqstid"+lArrRowNum[cnt]).value
				 +"&hdnAuditorPostId"+lArrRowNum[cnt]+"="+document.getElementById("hdnAuditorPostId"+lArrRowNum[cnt]).value
				 +"&txtCalledDate"+lArrRowNum[cnt]+"="+document.getElementById("txtCalledDate"+lArrRowNum[cnt]).value
				 +"&cmbCallTime"+lArrRowNum[cnt]+"="+document.getElementById("cmbCallTime"+lArrRowNum[cnt]).value;
	}
	return url;
}

function validateCallDate(currRowNum)
{
	validateCallDateUsingAJAX(currRowNum);
/*	var lCallDate = document.getElementById("txtCallDate").value;
	xmlHttp = GetXmlHttpObject();
	if (xmlHttp == null) {
		return;
	}
	var uri = "ifms.htm?actionFlag=validateCallDate";
	var url = "&callDate="+lCallDate;
	url = uri + url;
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState==complete_state)
		  {
			var XMLDoc = xmlHttp.responseXML.documentElement;
			if (XMLDoc != null) {
				var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
				var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
				var lArrSlotTime = XMLDoc.getElementsByTagName('SLOTTIME');
				if(lArrMessage != null && lArrMessage.length > 0)
				{
					alert(lArrMessage[0].text);
					document.getElementById("cmbCallTime").innerHTML = "";
					document.getElementById("txtCallDate").value = "";
					return;
				}
				if(lArrSlotNo != null && lArrSlotNo.length > 0)
				{
					document.getElementById("cmbCallTime").innerHTML = "";
					for(var i=0;i<lArrSlotNo.length;i++)
					{
						var theOption = new Option;
						theOption.value =lArrSlotNo[i].text;;
						theOption.text = lArrSlotTime[i].text;
						document.getElementById("cmbCallTime").options[i] = theOption;
						//document.getElementById("cmbCallTime").options[i].value = 
						//document.getElementById("cmbCallTime").options[i].text = ;
					}
					
				}
		  }
		  }
		
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(url);*/
}
function validateCallDateUsingAJAX(currRowNum)
{
	var lCallDate = document.getElementById("txtCalledDate"+currRowNum).value;
	var uri = "ifms.htm?actionFlag=validateCallDate";
	var url = "&callDate="+lCallDate;
//	url = uri + url;
	
	if(lCallDate.length > 0)
	{
		var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					getStateChangedForValidateCallDate(myAjax,currRowNum);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	}
}
function getStateChangedForValidateCallDate(myAjax,currRowNum)
{

	var XMLDoc = myAjax.responseXML.documentElement;
	if (XMLDoc != null) {
		var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
		var lArrSlotNo = XMLDoc.getElementsByTagName('SLOTNO');
		var lArrSlotTime = XMLDoc.getElementsByTagName('SLOTTIME');
		if(lArrMessage != null && lArrMessage.length > 0)
		{
			alert(lArrMessage[0].childNodes[0].nodeValue);
			document.getElementById("cmbCallTime"+currRowNum).value = "-1";
			document.getElementById("txtCalledDate"+currRowNum).value = "";
			return;
		}
		/*if(lArrSlotNo != null && lArrSlotNo.length > 0)
		{
			document.getElementById("cmbCallTime").innerHTML = "";
			for(var i=0;i<lArrSlotNo.length;i++)
			{
				var theOption = new Option;
				theOption.value =lArrSlotNo[i].childNodes[0].nodeValue;;
				theOption.text = lArrSlotTime[i].childNodes[0].nodeValue;
				document.getElementById("cmbCallTime").options[i] = theOption;
				//document.getElementById("cmbCallTime").options[i].value = 
				//document.getElementById("cmbCallTime").options[i].text = ;
			}
			
		}*/
  }
}

function saveCheckListDtls(pnsrCode)
{
	var lArrCheckList = document.getElementsByName("chkbxChecklistId");
	var totalCheckList = lArrCheckList.length;
	var lArrSelectedDocIds = new Array();
	var lStrSelectedDocIds = "";
	for(var i=0;i<totalCheckList;i++)
	{
		if(lArrCheckList[i].checked == true)
		{
			lArrSelectedDocIds[lArrSelectedDocIds.length] =  lArrCheckList[i].value ;
		}
	}
	lStrSelectedDocIds = lArrSelectedDocIds.join("~");
	saveCheckListDtlsUsingAjax(lStrSelectedDocIds,pnsrCode);
	
}

function saveCheckListDtlsUsingAjax(lStrSelectedDocIds,pnsrCode)
{
	var uri = "ifms.htm?actionFlag=saveChecklist";
	var url = "lStrSelectedDocIds="+lStrSelectedDocIds+"&pnsrCode="+pnsrCode;
	myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {saveCheckLstDtlsChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
}

function saveCheckLstDtlsChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;	
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		if(lSaveStatus == "Success")
		{
			alert("Checklist is saved successfully.");
		}
		else
		{
			alert("Some problem occurred during save.Please try again.");
		}
	}
	else
	{
		alert("Some problem occurred during save.Please try again.");
	}
}
function saveArchivedCase()
{
	var totalElements = document.getElementById("totalCount").value;
	var totalPensioner=""; 
	var flag=0;
	var uri;
	var myAjax;
	for(var i=1;i<totalElements;i++)
	{
		if(document.getElementById("chkbxPesnionerNo_"+i).checked == true)
		{
			totalPensioner = totalPensioner + document.getElementById("hdnPensionerId"+i).value + "~";
			flag=1;
		}
	}
	if(flag == 0)
	{
		alert('Please select any PPO.');
		return;
	}
	else
	{
		uri = "ifms.htm?actionFlag=saveArchivedCase&totalPensioner="+totalPensioner;
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {saveArchivedCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});
	}
}
function saveArchivedCaseStateChanged(myAjax) 
{ 
	var XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCARCHIVED');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		if(lSaveStatus == "Success")
		{
			alert("Cases are sent back to AG successfully.");
			self.location.reload(true);
		}
		else
		{
			alert("Some problem occurred during sent back to AG.Please try again.");
		}
	}
	else
	{
		alert("Some problem occurred during sent back to AG.Please try again.");
	}

}
function checkEmptyByValue(objVal)
{
	if(objVal != "" && objVal.length > 0 && objVal != "-1")
	{
		return true;
	}
	return false;
}