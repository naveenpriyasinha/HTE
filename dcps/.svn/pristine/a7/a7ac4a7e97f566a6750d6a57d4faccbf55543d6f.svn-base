

function showPensionBills(BillNo,SubId,PpoNo)
{

	var newWindow;
    var height = screen.height - 100;
    var width = screen.width;
    var urlstring;
    var currRole=document.getElementById("currRole").value;
    var showReadOnly="Y";
    var billFlag=document.getElementById("billFlag").value;
    if(SubId == "45")
    {
    	if(currRole=='365450' && billFlag != 'A')
    	{
    		showReadOnly="N";
    	}
    	 urlstring = "ifms.htm?actionFlag=getPensionBillsData&billNo=" + BillNo + "&billStatus=" + document.getElementById("billStatus").value + "&subId=" + SubId + "&ppoNo=" + PpoNo +"&showReadOnly="+showReadOnly+"&billFlag="+billFlag+"&currRole="+currRole;
    }
    else{
    	 urlstring = "ifms.htm?actionFlag=getPensionBillsData&billNo=" + BillNo + "&billStatus=" + document.getElementById("billStatus").value + "&subId=" + SubId + "&ppoNo=" + PpoNo +"&billFlag="+billFlag+"&currRole="+currRole;
    }
  
   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
}

function showDtPkr()
{
	if(document.getElementById("cmbSearch").value == "tbr.bill_Date") 
	{
		document.getElementById("dtpicker").style.display = "inline";
	}
	else
	{
		document.getElementById("dtpicker").style.display = "none";
	}
}
function blankSearchField()
{
	document.getElementById("txtSearch").value = "";
	document.getElementById("hdSearchValue").value="";
}
function getSerchField()
{
	var srchcmb = document.getElementById("cmbSearch").value;
	document.getElementById("txtSearch").value = "";
	document.getElementById("CmbBillType").value = "-1";
	document.getElementById("CmbBillCtgry").value = "Regular";
	document.getElementById("CmbSerchAuditots").value = "-1";
	
	var srchValue="";//document.getElementById("hdSearchValue").value;

//	document.getElementById("txtSearch").value = "";
//	var lArrCmbElements = document.getElementsByName("cmbSearch");
//	for(var i = 0 ;i<lArrCmbElements.length;i++)
//	{
//		lArrCmbElements[i].value = "-1";
//	}
	
	
	if( srchcmb == 'tbr.bill_Date') 
	{
		document.getElementById("txtSearch").value="";
		document.getElementById("dtpicker").style.display = "inline";
		document.getElementById("txtSearch").style.display = "inline";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		document.getElementById("CmbBillType").style.display = "none";
		document.getElementById("CmbBillCtgry").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		
		srchValue=document.getElementById("hdSearchValue").value;
		if(srchValue != null || srchValue != "")
		{
			document.getElementById("txtSearch").value=srchValue;
		}
	}
	else if (srchcmb == 'tbr.subject_id')
	{
		document.getElementById("dtpicker").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		document.getElementById("CmbBillType").style.display = "inline";
		document.getElementById("CmbBillCtgry").style.display = "none";
		document.getElementById("txtSearch").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		
		srchValue=document.getElementById("hdSearchValue").value;
		if(srchValue != null || srchValue != "")
		{
			if(srchValue == "9")
			{
				document.getElementById("CmbBillType").options[3].selected=true;
			}else if(srchValue == "10")
			{
				document.getElementById("CmbBillType").options[2].selected=true;
			}else if(srchValue == "11")
			{
				document.getElementById("CmbBillType").options[1].selected=true;
			}
		}
	}
	else if (srchcmb == 'tbr.tc_bill')
	{
		document.getElementById("dtpicker").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		document.getElementById("CmbBillType").style.display = "none";
		document.getElementById("CmbBillCtgry").style.display = "inline";
		document.getElementById("txtSearch").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		
		srchValue=document.getElementById("hdSearchValue").value;
		if(srchValue != null || srchValue != "")
		{
			 if(srchValue == "First Pay")
			{
				document.getElementById("CmbBillCtgry").options[1].selected=true;
			}else if(srchValue == "Monthly")
			{
				document.getElementById("CmbBillCtgry").options[2].selected=true;
			}else if(srchValue == "Regular"){
				document.getElementById("CmbBillCtgry").options[3].selected=true;
			}
		}
	}
	else if (srchcmb == 'our.post_id')
	{
		document.getElementById("dtpicker").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		document.getElementById("CmbBillType").style.display = "none";
		document.getElementById("CmbBillCtgry").style.display = "none";
		document.getElementById("txtSearch").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "inline";
		
	}
	else
	{
		document.getElementById("txtSearch").value="";
		document.getElementById("dtpicker").style.display = "none";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		document.getElementById("CmbBillType").style.display = "none";
		document.getElementById("CmbBillCtgry").style.display = "none";
		document.getElementById("txtSearch").style.display = "inline";
		document.getElementById("CmbSerchAuditots").style.display = "none";
		
		srchValue=document.getElementById("hdSearchValue").value;
		if(srchValue != null || srchValue != "")
		{
			document.getElementById("txtSearch").value=srchValue;
		}
	}
	
}
function searching()
{
	var uri='ifms.htm?actionFlag=getDraftBills';
	var currRole=document.getElementById("currRole").value;
	var billFlag=document.getElementById("billFlag").value;

	
	//if(currRole == '365450')
	//{
		uri=uri+"&billFlag="+billFlag+"&currRole="+currRole;
	//}
	
	document.frmDraftBills.action =uri;
	document.frmDraftBills.submit();
	disable();
	showProgressbar();
}
function showCase(url)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "frmPensionCase", urlstyle);
}
function pageClose()
{
	if(document.getElementById('printableString1') != null)
		document.getElementById('printableString1').value = '';
	document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
	document.forms[0].submit();
}
function printDotMatrix()
{
	//height=400,width=
	// 700,top=200,left=200,
	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family: sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 30in 30in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    cw.document.write(document.getElementById("printableString").value);	
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>');
    cw.location.reload(false); 
} 
function getPopUp(actnVal)
{
	if(chkSelected(actnVal) == true)
	{
		var newWindow;
		var url = "ifms.htm?actionFlag=getPnsnSendToOthers&FrwdType="+ actnVal +"&FromLevel="+document.getElementById("BillFromlevel").value+"&"+run();
		var height = screen.height - 100;
		var width = screen.width;
		var x = document.getElementsByName("chkbox");
		var urlstyle = "height=" + 300 + ",width=" + 500 + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "cmnCaseSelectionFrwd", urlstyle);
	}
}
function searchByEnter(event)
{
	if (event.keyCode == 13)
	{
		if(setDate() != false)
		{
			searching();
		}
	}
}
function setDate()
{
	if(document.getElementById("cmbSearch").value == 'tbr.bill_Date')
	{
		var lStr  = document.getElementById("txtSearch");
		if(lStr.value.length > 0)
		{
			formateWholeDate(lStr);
			if(isValidDate1(lStr.value) == false)
			{
				lStr.value ="";
				lStr.focus();
				return false
			}
			else
			{
				return true;
			}
		}
	}
}
function chkForToken()
{
	var theForm = document.getElementById("chkbox").form;
	var flag1 = 0;
	var flag2 = 1;
	var x = '';
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
		{
			if(theForm[z].checked)
			{
				var arr1 = (theForm[z].value).split("~");
				if(document.getElementById(arr1[0]).value.length > 0)
				{
					flag1 = 1;
				}
				else
				{
					flag2 = 0;
					x = arr1[0];
				}
			}
		}
	}
	if(flag1 == 1 && flag2 == 1)
	{
		return true;
	}
	else
	{
		document.getElementById(x).focus();
		return false;
	}
}
reptBillNo = "";
function checkForExistingToken(lthis)
{
	if(validatePnsnBillsTokenNo(lthis))
	{
		var theForm = document.getElementById("chkbox").form;
		for(var z = 0; z < theForm.length; z++)
		{
			if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
			{
				var arr1 = (theForm[z].value).split("~");
				if(arr1[0] != lthis.id && document.getElementById(arr1[0]).value.length > 0 && lthis.value .length > 0 )
				{
					if(document.getElementById(arr1[0]).value == lthis.value)
				 	{
						alert("Please Enter proper Token Number ");
						lthis.value = "";
						lthis.focus();
					}
				}
			}
		}
	}
}
PnsnBillToken = null;
fcsVal = null;
resFlag = "" ;
function validatePnsnBillsTokenNo(lvalue) 
{
	fcsVal = lvalue;
	var reptBillNo2 = "";
	if(lvalue.value != '')
	{
		PnsnBillToken = lvalue.value;
		reptBillNo2 = lvalue.name;
		getPnsnBillTokenValidByAJAX(reptBillNo2);
		if(resFlag == "T")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else if(lvalue.value == '')
	{
		fcsVal.value = "";
		return false;
	}
}
function getPnsnBillTokenValidByAJAX(reptBillNo2)
{
	req = createXMLHttpRequest();
	
	if(req != null)
	{
		var baseUrl = "ifms.htm?actionFlag=validatePensionBillsTokenNo&pnsnBillToken="+PnsnBillToken;
		req.open("post", baseUrl, true); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = function ()
		{
			if(req.readyState==complete_state)
			{ 
				var XMLDoc = req.responseXML.documentElement;
				var XMLEntries = XMLDoc.getElementsByTagName("TOKENRES");				
				var ResultToken = XMLEntries[0].text;
				if(ResultToken != null && ResultToken != '')
				{
					reptBillNo = reptBillNo2;
					alert(ResultToken);
					fcsVal.value = "";
					document.getElementById(reptBillNo).value = "";
					document.getElementById(reptBillNo).focus();
					return false;
				}
				else
				{
					resFlag = "T";
				}
			}
		}
		req.send(baseUrl);
	}
	else
	{
		alert ("Your browser does not support AJAX!");
	}
}
function upperCase(event)
{
	var cmbVal = document.getElementById("cmbSearch").value;
	if(cmbVal == 'rbp.party_name' || cmbVal == 'tpbh.scheme' )
	{
		if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
		if(((window.event.keyCode >32 && window.event.keyCode <= 57 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
		{
			window.event.keyCode = 0;
		}	
	}
	else if(cmbVal == 'tbr.bill_cntrl_no' || cmbVal == 'tbr.ppo_no' )
	{
		if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
		if(((window.event.keyCode >32 && window.event.keyCode <= 46 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
		{
			window.event.keyCode = 0;
		}	
	}
	else if(cmbVal == 'tbr.bill_Date')
	{
		dateFormat(document.getElementById("txtSearch"));
	}
	else if(cmbVal == 'tbr.token_Num' || cmbVal == 'tbr.budmjr_hd')
	{
		NumberFormet();
	}
}
function dateFormat(field)
{	if(event.keyCode != 8)
	{
		var value=new String(field.value);
		if(value.length==2)
		{
			field.value=value+'/'
		}
		if(value.length==5)
		{
			field.value=value+'/'
		}
	}
}
function changeOnFocus(element)
{
	if(element.type=='text')
	{
		// text onfocus
		element.style.backgroundColor ='#FEEEF4';
		element.style.borderColor= '#FF0000';
		element.style.borderWidth= '2px'; 
		element.style.borderStyle= 'solid' ;
	}
	if(element.type=='select')
	{
		// text onfocus
		element.style.backgroundColor ='#FEEEF4';
		element.style.borderColor= '#FF0000';
		element.style.borderWidth= '2px'; 
		element.style.borderStyle= 'solid' ;
	}
}

function billDelete()
{
	var arrChkBox = document.getElementsByName("chkbox");
	var billNoArr = "";
	var subIdArr = "";
	var flagChk = false;
	
	for(var i=0; i < arrChkBox.length; i++)
	{
		if(arrChkBox[i].checked == true)
		{
			flagChk = true;
		}
	}
	if(flagChk == false)
	{
		alert("Please select atleast one Bill to delete");
		return false;
	}
				
	var message = 'Are You Sure Want to Delete the Bill';
	var x = window.confirm(message);
	
	if(x)
	{	
		showProgressbar();
		for(var i=0; i < arrChkBox.length; i++)
		{
			if(arrChkBox[i].checked == true)
			{
				var x = arrChkBox[i].value.split("~");
				
				billNoArr = billNoArr + x[0]+"~";
				subIdArr = subIdArr + x[7]+"~";
			}
		}
		document.forms[0].action = 'ifms.htm?actionFlag=deleteBills&billNoArrString='+billNoArr+'&subIdArrString='+subIdArr;
		document.forms[0].submit();
	}
	else
	{
		return false;		
	}
}

function validateGenerateECS()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var flag=0;
	
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				flag=1;
			}
		}
	}

	if(flag==0)
	{
		alert("Please check atleast one bill.");
		return false;
	}
	else{
		return true;
	}
		
}
function generateECS()
{

	var totalBillAmount=0;
	var billNoString="";
	if(validateGenerateECS())	
	{	
		totalBillAmount=Number(getTotalChequeAmount());
		billNoString=getAllCheckedBillNos();
		
		var uri="ifms.htm?actionFlag=genECS&totalBillAmnt="+totalBillAmount+"&billNoString="+billNoString+"&billNetAmountString="+billNetAmountString;
		generateECSUsingAJAX(uri,totalBillAmount,billNoString,billNetAmountString)
	//	genECSUsingAJAX(uri);
		
		//showECSFile()
	//	document.frmDraftBills.action =uri;
	//	document.frmDraftBills.submit();
	//	disable();
		showProgressbar();
	}
}
function generateECSUsingAJAX(uri,totalBillAmount,billNoString,billNetAmountString)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters: "totalBillAmnt="+totalBillAmount+"&billNoString="+billNoString+"&billNetAmountString="+billNetAmountString,
		        onSuccess: function(myAjax) {
					getDataStateChangedForECS(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForECS(myAjax) 
{ 
			XMLDoc = myAjax.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCODE');
			var XmlHiddenValues1=XMLDoc.getElementsByTagName('ECSFlag');
								
			
			for( var i=0; i<XmlHiddenValues.length ;i++) {
				
    		  	var ecsCode= XmlHiddenValues[i].childNodes[0].nodeValue;
				var ecsFlag=XmlHiddenValues1[i].childNodes[0].nodeValue;
				
					
		}
			if(ecsFlag == 1)
			{
				document.getElementById("txtEcsCode").value=ecsCode;	
				alert("ECS/N.EFT File Generated Succesfully with ECS/N.EFTCode "+ecsCode+".");
				
			}
			else
			{
				alert("ECS/N.EFT File cannot be Generated.");
			}
			
			window.self.location.reload();
			hideProgressbar();
	
};
function getAllCheckedBillNos()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var rowNum="";
	var flag=0;
	var billNo="";
	var billNoString="";
	
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					billNo = arrChkBox[i].value;
					
					if(flag==0)
					{
						billNoString=billNoString+billNo;
						flag=1;
					}
					else
					{
						billNoString=billNoString+"~"+billNo;
					}
					
				}
			}
		}
	}
	return billNoString;
}
var billNetAmountString="";
function getTotalChequeAmount()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var rowNum="";
	var flag=0;
	var billNo="";
	var totalBillAmnt=0;
	var billAmnt=0;
	var resId="";
	billNetAmountString="";
	var amntFlag=0;
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					billNo = arrChkBox[i].value;
					flag=1;
					rowNum=billNo.substring(billNo.indexOf("_")+1);
					resId="lblBillNetAmount"+rowNum;
					billAmnt=document.getElementById(resId).innerHTML;
					if(amntFlag==0)
					{
						billNetAmountString=billNetAmountString+billAmnt;
						amntFlag=1;
					}
					else
					{
						billNetAmountString=billNetAmountString+"~"+billAmnt;
					}
					
					totalBillAmnt=Number(totalBillAmnt) +Number(billAmnt);	
				}
			}
		}
	}
	return totalBillAmnt;
}
function genECSUsingAJAX(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange= function saveDataStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCODE');
				var XmlHiddenValues1=XMLDoc.getElementsByTagName('ECSFlag');
									
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
	    		  	var ecsCode= XmlHiddenValues[i].text;
					var ecsFlag=XmlHiddenValues1[i].text;
					
						
			}
				if(ecsFlag == 1)
				{
					document.getElementById("txtECSCode").value=ecsCode;	
					alert("ECS/N.EFT File Generated Succesfully with ECS/N.EFTCode "+ecsCode+".");
					
				}
				else
				{
					alert("ECS/N.EFT File cannot be Generated.");
				}
				
				window.self.location.reload();
				hideProgressbar();
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
	
}
function showECSFile(txtEcsCode)
{
	if(txtEcsCode != null)
	{
		document.frmECSFiles.action = 'ifms.htm?actionFlag=showECS&ECSCode='+txtEcsCode;
		document.frmECSFiles.submit();
	}
	else
	{
		alert("ECS/N.EFT File cannot be Viewed!");
	}

}
function forwardApproveBills(rejectFlag)
{
	
	var billNoString="";
	var billFlag=document.getElementById("billFlag").value;
	var uri;
	if(validateGenerateECS()){
		showProgressbar();
		billNoString=getAllCheckedBillNos();
		uri="ifms.htm?actionFlag=forwardApproveBills&billNoString="+billNoString+"&billFlag="+billFlag+"&rejectFlag="+rejectFlag;
		
		if(billFlag == 'A' && rejectFlag != 'AR')
		{
			if(validateApprovedBills())
			{
				//approveBillsUsingAJAX(uri);
				forwardApproveBillUsingAJAX(uri,billNoString,billFlag,rejectFlag)
			}
		}else if(rejectFlag == 'AR'){
			if(validateArcheivedBills())
			{
				//approveBillsUsingAJAX(uri);
				forwardApproveBillUsingAJAX(uri,billNoString,billFlag,rejectFlag)
			}
		}
		else{
				//approveBillsUsingAJAX(uri);
			forwardApproveBillUsingAJAX(uri,billNoString,billFlag,rejectFlag)
		}
	}
	
}
function forwardApproveBillUsingAJAX(uri,billNoString,billFlag,rejectFlag)
{
	var currRole=document.getElementById("currRole").value;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&billNoString="+billNoString+"&billFlag="+billFlag+"&rejectFlag="+rejectFlag+"&currRole="+currRole,
		        onSuccess: function(myAjax) {
					getDataStateChangedForforAppBills(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForforAppBills(myAjax)
{
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
	var XmlHiddenValues1= XMLDoc.getElementsByTagName('TOROLE');				
	var toRole="";
	for( var i=0; i<XmlHiddenValues.length ;i++) {
	
	  	var flag= XmlHiddenValues[i].childNodes[0].nodeValue;
	  
	  
		if(XmlHiddenValues1[i].childNodes.length > 0)
		{
			  	toRole=XmlHiddenValues1[i].childNodes[0].nodeValue;
		}	  
			
}

	if(flag != null || flag.length >0)
	{	
		alert("Bills "+flag+" Successfully "+toRole);
		window.self.location.reload();
	}
	else
	{
		alert("Sorry process cannot be completed!");
		hideProgressbar();
	
	}
}
function approveBillsUsingAJAX(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange= function saveDataStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
				var XmlHiddenValues1= XMLDoc.getElementsByTagName('TOROLE');				
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
	    		  	var flag= XmlHiddenValues[i].text;
					var toRole=XmlHiddenValues1[i].text;
					
						
			}
				if(flag != null || flag.length >0)
				{	
					alert("Bills "+flag+" Successfully "+toRole);
					window.self.location.reload();
				}
				else
				{
					alert("Sorry process cannot be completed!");
					hideProgressbar();
				
				}
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);	
}
function validateApprovedBills()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var rowNum="";
	var flag=0;
	var currBillStatus="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="hdCurrBillStatus_"+rowNum;
					currBillStatus=document.getElementById(resultId).value;
					if(currBillStatus == "60")								
					{
						flag=1;
						alert("Rejected Bills cannot be Forwarded!");
						hideProgressbar();
						return false;
					}
				}
			}
		}
	}
	if(flag==0)
	{
		return true;
	}	

}

function validateArcheivedBills()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var rowNum="";
	var flag=0;
	var currBillStatus="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="hdCurrBillStatus_"+rowNum;
					currBillStatus=document.getElementById(resultId).value;
					if(currBillStatus == "20")								
					{
						flag=1;
						alert("Approved Bills cannot be Archeived!");
						hideProgressbar();
						return false;
					}
				}
			}
		}
	}
	if(flag==0)
	{
		return true;
	}	

}
function getAllCheckedChequeNos(){
	
	

	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var rowNum="";
	var flag=0;
	var billNo="";
	var sizeId="";
	var size="";
	var billChequeString="";
	var billCheque="";
	var chequeNoID="";
	var chequeNo="";
	var chequeAmtId="";
	var chequeAmt="";
	var partyNameId="";
	var partyName="";
	var chequeDateId="";
	var chequeDate="";
	//var arrChkBox=document.getElementsByName("chkbxDraftBills");
//	var flag=0;
	

	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					billNo = arrChkBox[i].value;
					
					rowNum=billNo.substring(billNo.indexOf("_")+1);
					billNo=billNo.substring(0,billNo.indexOf("_"));
				
					sizeId="hdSize_"+rowNum;
					size=Number(document.getElementById(sizeId).value)+1;
					
					
					for(var j=0;j<size;j++)
					{
						chequeNoId="txtChequeNo_"+rowNum+"_"+j;
						
						chequeNo=document.getElementById(chequeNoId).value;
						
						chequeAmtId="hdChequeAmt_"+rowNum+"_"+j;
						chequeAmt=document.getElementById(chequeAmtId).value;
						
						partyNameId="hdPartyName_"+rowNum+"_"+j;
						partyName=document.getElementById(partyNameId).value;

						chequeDateId="txtChqDate_"+rowNum;
						chequeDate=document.getElementById(chequeDateId).value;
						
						billCheque=billNo + "_" + chequeNo + "-" + chequeAmt + "*" + partyName +"#"+chequeDate;
						
						if(flag==0)
						{
							billChequeString=billChequeString + billCheque;
							flag=1;
						}else
						{
							billChequeString=billChequeString + "~"+ billCheque;
						}
					}
					
				}
			}
		}
	}
	return billChequeString;
}

function validateSaveCheques()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var flag=0;
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				flag=1;
				billNo = arrChkBox[i].value;
				
				rowNum=billNo.substring(billNo.indexOf("_")+1);
				billNo=billNo.substring(0,billNo.indexOf("_"));
			
				sizeId="hdSize_"+rowNum;
				size=Number(document.getElementById(sizeId).value)+1;
				
				
				for(var j=0;j<size;j++)
				{
					chequeNoId="txtChequeNo_"+rowNum+"_"+j;
					
					chequeNo=document.getElementById(chequeNoId).value;
					
					if(chequeNo.length == 0 || chequeNo.length > 7)
					{
						alert("Please enter cheque no of appropriate size.");
						document.getElementById(chequeNoId).focus();
						return false;
					}
				
						
					chequeDateId="txtChqDate_"+rowNum;
					chequeDate=document.getElementById(chequeDateId).value;
					
					if(chequeDate.length == 0 )
					{
						alert("Please enter cheque date.");
						document.getElementById(chequeDateId).focus();
						return false;
					}
					
					
				}
				
			}
		}
	}

	if(flag==0)
	{
		alert("Please check atleast one bill to save Cheque.");
		return false;
	}
	else{
		return true;
	}
}
function saveCheques()
{
	
	if(validateSaveCheques())
	{		showProgressbar();
			var billChequeString=getAllCheckedChequeNos();
			var uri="ifms.htm?actionFlag=saveChequeNos&billChequeString="+billChequeString;
			assignChequeUsingAJAX(uri,billChequeString);
		//	saveChequesUsingAJAX(uri);
			//showECSFile()
		//	document.frmDraftBills.action =uri;
		//	document.frmDraftBills.submit();
		//	disable();
			
			return true;
		}
}
function assignChequeNo()
{
	if(validateSaveCheques())
	{
		
		showProgressbar();
		var billChequeString=getAllCheckedBillNos();
		
		var uri="ifms.htm?actionFlag=saveChequeNos";
		
		assignChequeUsingAJAX(uri,billChequeString);
		return true;
	}
}
	function assignChequeUsingAJAX(uri,billChequeString)
	{
		
		var url = runForm(0); 
	
		uri=uri+url;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "billChequeString="+billChequeString,
			        onSuccess: function(myAjax) {
					getDataStateChangedForCheque(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
function getDataStateChangedForCheque(myAjax)
{
	
	XMLDoc = myAjax.responseXML.documentElement;

	var XmlHiddenValues = XMLDoc.getElementsByTagName('SAVED');
	
					
	
	for( var i=0; i<XmlHiddenValues.length ;i++) {
		
	  	var flag= XmlHiddenValues[i].childNodes[0].nodeValue;
	
		
			
}
	
	if(flag=='Y')
	{
		
		alert("Cheque Generated Succesfully.");
		window.self.location.reload();
		
	}
	else
	{
		
		alert("Cheque cannot be Generated.");
		hideProgressbar();
	}
	
	
}

function saveChequesUsingAJAX(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	var flag;
	xmlHttp.onreadystatechange= function saveDataStateChanged() 
	{ 
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				XMLDoc = xmlHttp.responseXML.documentElement;
//				alert(xmlHttp.responseXML);
//				alert(XMLDoc);
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('SAVED');
//				alert(XmlHiddenValues);
//				for( var i=0; i<XmlHiddenValues.length ;i++) {
//		    		 
//					 flag= XmlHiddenValues[0].text;			
//						
//				}										    		 
//	    		
//				alert(flag);
//				if(flag=='Y')
//				{
					
					alert("Cheque Generated Succesfully.");
					window.self.location.reload();
//				}
//				else
//				{
//					alert("Cheque cannot be Generated.");
//					hideProgressbar();
//				}
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
		
	



function compareDates(fieldName1,fieldName2,alrtStr,flag)
{
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
		    	}
            }
        }
    }
    return true ;
}
	
/*---------Date Validation---------*/
var dtCh= "/";
var minYear=1900;
var maxYear=2100;


function viewArcheiveBill()
{
	var currRole=document.getElementById("currRole").value;
	document.frmDraftBills.action = 'ifms.htm?actionFlag=getDraftBills&billFlag=AR&currRole='+currRole;
	document.frmDraftBills.submit();
}

function validateCashPayment()
{
	var arrChkBox=document.getElementsByName("chkbxDraftBills");
	var flag=0;
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				flag=1;
				billNo = arrChkBox[i].value;
				
				rowNum=billNo.substring(billNo.indexOf("_")+1);
				billNo=billNo.substring(0,billNo.indexOf("_"));
			
				sizeId="hdSize_"+rowNum;
				size=Number(document.getElementById(sizeId).value)+1;
				
				
				for(var j=0;j<size;j++)
				{
										
					chequeDateId="txtPayDate_"+rowNum;
					chequeDate=document.getElementById(chequeDateId).value;
					
					if(chequeDate.length == 0 )
					{
						alert("Please enter Payment date.");
						document.getElementById(chequeDateId).focus();
						return false;
					}
					
					
				}
				
			}
		}
	}

	if(flag==0)
	{
		alert("Please check atleast one bill to save Payment Date.");
		return false;
	}
	else{
		return true;
	}
}

function saveCashPayment()
{
	if(validateCashPayment())
	{
		showProgressbar();
		var billChequeString=getAllCheckedBillNos();
		
		var uri="ifms.htm?actionFlag=saveCashPayment";
		
		saveCashPaymentUsingAJAX(uri,billChequeString);
		return true;
	}
}
function saveCashPaymentUsingAJAX(uri,billChequeString)
{
		var url = runForm(0); 
	
		uri=uri+url;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "billChequeString="+billChequeString,
			        onSuccess: function(myAjax) {
					getDataStateChangedForCash(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
}

function getDataStateChangedForCash(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;

	var XmlHiddenValues = XMLDoc.getElementsByTagName('SAVED');

	for(var i=0; i<XmlHiddenValues.length ;i++) 
	{
		var flag= XmlHiddenValues[i].childNodes[0].nodeValue;
	}
	
	if(flag=='Y')
	{
		alert("Payment Date saved Succesfully.");
		window.self.location.reload();
	}
	else
	{
		alert("Payment Date cannot be saved.");
		hideProgressbar();
	}
		
}


