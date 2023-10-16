function setOtherAmounts(id)
{
	if(Number(id.value) > Number(document.getElementById("hidRemAmt").value))
	{
		alert("Grant Amount should not be greater than Reimbursement Amount");
		id.value = "";
		document.getElementById("id_Expenditure").value = "";
		document.getElementById("id_NetAmt").value = "";
		document.getElementById("txtChkAmt").value = "";
		id.focus();
		return false;
	}
	document.getElementById("id_Expenditure").value = id.value;
	document.getElementById("id_NetAmt").value = id.value;
	document.getElementById("txtChkAmt").value = id.value;
}
function validateBillDtls()
{
	if(document.getElementById("PensionType") && document.getElementById("PensionType").value == 'PENSION')
	{
		if(document.getElementById("txtArrearAmt") && Number(document.getElementById("txtArrearAmt").value) < 0)
		{
			alert("Pension Bill Arrear Amount Cannot Be Negative.");
			goToFieldTab("txtArrearAmt",1);
			return false;
		}
	}

	if(document.getElementById("id_Expenditure") && Number(document.getElementById("id_Expenditure").value) <= 0)
	{
		alert(" Bill Expenditure Amount Cannot Be Negative or Zero.");
		return false;
	}
	
	var lNetAmt = document.getElementById("id_NetAmt");
	
	if(Number(lNetAmt.value)<0)
	{	
		alert("Bill Amount Cannot Be Negative.");
		
		if(document.getElementById("PensionType") && ( document.getElementById("PensionType").value == 'DCRG' || document.getElementById("PensionType").value == 'CVP') )
		{
			var result = confirm("Do You Want Create Bill as Nill Bill ?");
			if(result)
			{
				document.getElementById("id_cmbBillType").value = 'Nil';
				document.getElementById("id_NetAmt").value = '0.00';
				var tmpEdpCode = document.getElementById("hdPayEdpCode").value;
				document.getElementById('id_txtAmt'+tmpEdpCode).value = '0.00';
				document.getElementById("id_Expenditure").value = '0.00';
				document.getElementById("txtNetAmt").value = '0.00';
				document.getElementById("txtChkAmt").value = '0.00';
				if(document.getElementById("PensionType").value == 'DCRG')
				{
					document.getElementById("txtDCRGAmt").value = '0.00';
				}
				else if(document.getElementById("PensionType").value == 'CVP')
				{
					document.getElementById("txtCVPAmt").value = '0.00';
				}
					
				return true;
			}
		}
		return false;		
	}
	
	if(document.getElementById("PensionType") && document.getElementById("PensionType").value == 'Monthly')
	{
		if(!validateMonthlyDtls())
		{
			return false;
		}
	}
		
	return true;
}
	

/*function setText(lThis)
{
	document.getElementById("txtBudDesc").value = lookupArray[lThis.selectedIndex];
}*/

function isValidateEmpDtls()
{
	if(!IsNull("txtEmpName", EMP_TXTEMPNAME ))
	{
		return false;
	}
	if(!IsNull("txtEmpDsg", EMP_TXTEMPDESG))
	{
		return false;
	}
	if(!IsNull("txtEmpEstblsmnt", EMP_TXTESTB))
	{
		return false;
	}
	return true;
}



// Functions For EDPDtls.jsp....... Starts......

	function computeSum(counter, edpCode, rowNo, edpType)
	{	
		var ExpAmt = 0.0;
		var RecAmt = 0.0;
		var DedA = 0.0;
		var DedB = 0.0;
		
		var _edpTypeExp="EXP";
		var _edpTypeRec="REC";
		var _edpTypeRpt="RCP";				
		var amountA = 0.0, amountB = 0.0, amountO = 0.0, amountRec = 0.0, amountExp = 0.0;
		
		if(rowNo != '-')
		{
			if(edpType == _edpTypeExp)
			{
				aAmount=document.getElementById("id_txtAmt"+rowNo).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtAmt"+rowNo).value;
			}
			if(edpType == _edpTypeRec)
			{
				aAmount = document.getElementById("id_txtRecAmt"+rowNo).value
				if(aAmount.length == 0 || aAmount == null)
				{
					document.getElementById("id_txtRecAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRecAmt"+rowNo).value;
			}
			if(edpType==_edpTypeRpt)
			{
				aAmount=document.getElementById("id_txtRcptAmt"+rowNo).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtRcptAmt"+rowNo).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRcptAmt"+rowNo).value;
			}
		}
		else
		{
			if(edpType==_edpTypeRec)
			{
				aAmount=document.getElementById("id_txtRecAmt"+edpCode).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtRecAmt"+edpCode).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtRecAmt"+edpCode).value;
			}
			else
			{
				aAmount=document.getElementById("id_txtAmt"+edpCode).value
				if(aAmount.length==0 || aAmount==null)
				{
					document.getElementById("id_txtAmt"+edpCode).value=0;
				}
				array[counter]['amount']=document.getElementById("id_txtAmt"+edpCode).value;

			}
		}
		for(i = 0;i < array.length; i++)
		{
			if(array[i]['expRcpRev']=="Expenditure")
			{
				amountExp=parseFloat(amountExp) + parseFloat(array[i]['amount'])
				document.forms[0].txtExpenditure.value = Math.format(amountExp);
			}
			if(array[i]['expRcpRev']=="Recovery")
			{
				amountRec=parseFloat(amountRec) + parseFloat(array[i]['amount'])
				document.forms[0].txtRecovery.value =  Math.format(amountRec);
			}
			if(array[i]['expRcpRev']=="Receipt")
			{
				if(array[i]['category']=='A')
				{
					amountA=parseFloat(amountA) + parseFloat(array[i]['amount'])
					document.forms[0].DeductionA.value = Math.format(amountA);
				}
				if(array[i]['category']=='B')
				{
					amountB=parseFloat(amountB) + parseFloat(array[i]['amount'])
					document.forms[0].DeductionB.value = Math.format(amountB);
				}
			}
		}
		
		ExpAmt = parseFloat(document.getElementById("txtExpenditure").value);
		RecAmt = parseFloat(document.getElementById("txtRecovery").value);
		DedA = parseFloat(document.getElementById("DeductionA").value);
		DedB = parseFloat(document.getElementById("DeductionB").value);
		
		var TableCal = document.getElementById("GrossTotal");
    	if(TableCal != null)
    	{
       		TableCal.innerHTML = "<b> : " + Math.format(parseFloat(ExpAmt)) + " </b> ";
    	}
    	TableCal = document.getElementById("Recovery");
    	if(TableCal != null)
    	{
    		TableCal.innerHTML = "<b> : " + Math.format(parseFloat(RecAmt)) + " </b> ";
    	}
    	TableCal = document.getElementById("NetTotal");
    	if(TableCal != null)
    	{
	   		NetTotal = parseFloat(ExpAmt - RecAmt) - parseFloat(DedA + DedB);
    		document.getElementById("hidNetTotal").value = NetTotal;
    		TableCal.innerHTML = "<b> : " + Math.format(parseFloat(NetTotal)) + " </b> ";
    	}
    	TableCal = document.getElementById("AmtInRs");
    	if(TableCal != null)
    	{
    		TableCal.innerHTML = "<br /> <b> : " + getAmountInWords(NetTotal) + " </b> ";
    	}
    	TableCal = document.getElementById("DDOExpAmt");
		if(TableCal != null)
		{
			TableCal.innerHTML = "<b> : " + Math.format(parseFloat(glDDOExpAmt + NetTotal)) + " </b> ";
		}
		TableCal = document.getElementById("Balance");
		if(TableCal != null)
		{
			var lAmount = parseFloat(document.getElementById("hidGrantAmt").value);
			lAmount = parseFloat(lAmount - parseFloat(glDDOExpAmt + NetTotal));
			
			if(!isNaN(lAmount))
			{
				TableCal.innerHTML = "<b> : " + Math.format(parseFloat(lAmount)) + " </b> ";
			}
		}
	}	
	
	function getExpenditureEdpDtls(txtEdpCode, edpRowNo,vCounter)
	{	
		var flag = 0;
		if(txtEdpCode.value=="")
		{	
			return;
		}
		for(i=0; i<array.length; i++)
		{
			if(array[i]['edpCode']==txtEdpCode.value)
			{
				if(i!=vCounter)
				{
					document.getElementById("msg").innerHTML=EDP_TXTEDPCODE;
					txtEdpCode.value="";
					document.getElementById("id_txtEdpDesc"+edpRowNo).innerHTML = "";
					document.getElementById("id_txtBudCode"+edpRowNo).innerHTML = "";
					document.getElementById("id_txtEdpCode"+edpRowNo).focus();
					return;
				}
			}
		}
		for(i=0;i<expEdpArry.length;i++)
		{
			if(expEdpArry[i]['edpCode']==txtEdpCode.value && expEdpArry[i]['receiptEdp']=='N')
			{
				document.getElementById("id_txtEdpDesc"+edpRowNo).innerHTML = expEdpArry[i]['edpDesc'];
				document.getElementById("id_txtBudCode"+edpRowNo).innerHTML = expEdpArry[i]['objCode'];			
				document.getElementById("msg").innerHTML="";
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			document.getElementById("msg").innerHTML=EDP_TXTEDPCODE;
			txtEdpCode.value="";
			document.getElementById("id_txtEdpDesc"+edpRowNo).innerHTML = "";
			document.getElementById("id_txtBudCode"+edpRowNo).innerHTML = "";
			txtEdpCode.focus();
		}
	}	
	
	function getRecoveryEdpDtls(txtRecEdpCode,recEdpRowNo,vCounter)
	{	
		var flag=0;
		
		if(txtRecEdpCode.value=="")
		{	
			return;
		}
		for(i=0;i<array.length;i++)
		{
			if(array[i]['edpCode']==txtRecEdpCode.value)
			{
				if(i!=vCounter)
				{
					document.getElementById("msgRec").innerHTML=EDP_TXTEDPCODE;
					document.getElementById("id_txtRecEdpDesc"+recEdpRowNo).innerHTML = "";
					document.getElementById("id_txtRecBudCode"+recEdpRowNo).innerHTML = "";
					txtRecEdpCode.value="";
					document.getElementById("id_txtRecEdpCode"+recEdpRowNo).focus();
					return;
				}
			}
		}
		for(i=0;i<expEdpArry.length;i++)
		{
			if(expEdpArry[i]['edpCode']==txtRecEdpCode.value && expEdpArry[i]['receiptEdp']=='N')
			{
				document.getElementById("id_txtRecEdpDesc"+recEdpRowNo).innerHTML = expEdpArry[i]['edpDesc'];
				document.getElementById("id_txtRecBudCode"+recEdpRowNo).innerHTML = expEdpArry[i]['objCode'];			
				document.getElementById("msgRec").innerHTML="";
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			document.getElementById("msgRec").innerHTML=EDP_TXTEDPCODE;
			document.getElementById("id_txtRecEdpDesc"+recEdpRowNo).innerHTML = "";
			document.getElementById("id_txtRecBudCode"+recEdpRowNo).innerHTML = "";
			txtRecEdpCode.value="";
			document.getElementById("id_txtRecEdpCode"+recEdpRowNo).focus();
		}
		//document.forms[0].txtNetAmount.value=parseFloat(amountO)-parseFloat(amountA)-parseFloat(amountB)				
	}	
	
	function getReceiptEdpDtls(txtRcptEdpCode,rcptEdpRowNo,vCounter)
	{	
		var flag=0;
		if(txtRcptEdpCode.value=="")
		{	
			return;
		}
		for(i=0;i<array.length;i++)
		{
			if(array[i]['edpCode']==txtRcptEdpCode.value)
			{
				if(i!=vCounter)
				{
					document.getElementById("msgRcpt").innerHTML=EDP_TXTEDPCODE;
					document.getElementById("id_txtRcptMjrHdCode"+rcptEdpRowNo).innerHTML = "";
					document.getElementById("id_txtRcptSubMjrHdCode"+rcptEdpRowNo).innerHTML = "";
					document.getElementById("id_txtRcptMinHdCode"+rcptEdpRowNo).innerHTML = "";
					document.getElementById("id_txtRcptSubHdCode"+rcptEdpRowNo).innerHTML = "";
					txtRcptEdpCode.value="";
					document.getElementById("id_txtRcptEdpCode"+rcptEdpRowNo).focus();
					return;
				}
			}
		}
		for(i=0;i<expEdpArry.length;i++)
		{
			if(expEdpArry[i]['edpCode']==txtRcptEdpCode.value && expEdpArry[i]['receiptEdp']=='Y')
			{
				document.getElementById("id_txtRcptMjrHdCode"+rcptEdpRowNo).innerHTML = expEdpArry[i]['budMjrHd'];
				document.getElementById("id_txtRcptSubMjrHdCode"+rcptEdpRowNo).innerHTML = expEdpArry[i]['budSubMjrHd'];
				document.getElementById("id_txtRcptMinHdCode"+rcptEdpRowNo).innerHTML = expEdpArry[i]['budMinHd'];
				document.getElementById("id_txtRcptSubHdCode"+rcptEdpRowNo).innerHTML = expEdpArry[i]['budSubHd'];
				document.getElementById("msgRcpt").innerHTML="";
				//document.getElementById("id_txtRcptBudHdCode"+rcptEdpRowNo).value=expEdpArry[i]['objCode'];			
				flag=1
				break;
			}
		}
		if(flag==0)
		{
			document.getElementById("msgRcpt").innerHTML=EDP_TXTEDPCODE;
			document.getElementById("id_txtRcptMjrHdCode"+rcptEdpRowNo).innerHTML = "";
			document.getElementById("id_txtRcptSubMjrHdCode"+rcptEdpRowNo).innerHTML = "";
			document.getElementById("id_txtRcptMinHdCode"+rcptEdpRowNo).innerHTML = "";
			document.getElementById("id_txtRcptSubHdCode"+rcptEdpRowNo).innerHTML = "";
			document.getElementById("id_txtRcptEdpCode"+rcptEdpRowNo).focus();
			txtRcptEdpCode.value="";
		}
		//document.forms[0].txtNetAmount.value=parseFloat(amountO)-parseFloat(amountA)-parseFloat(amountB)				
	}	

// Functions For EDPDtls.jsp....... Ends......


// Functions For GTR Format JSPs ...... Starts......
				
function printBill()
{
	document.getElementById("printbtn").style.visibility='hidden';
	window.print(this);
	document.getElementById("printbtn").style.visibility='visible';
} 
// Functions For GTR Format JSPs ...... Ends......	

// Functions For partyInfo.jsp ...... Starts......		
RowNum = -1;

function isValidPartiData()
{
	var lTotalChkAmt = 0.0;
	document.getElementById("hidPartiCounter").value = RowNum;
	
	if(RowNum == -1)
	{
		alert(PARTY_PARTYNAME);
		return false;
	}
	else
	{
		var element = document.getElementsByName("txtPartyName");
			
		for(var i = 0; i < element.length; ++i)
		{
		 	var lStr = new String(element[i].value);
			lStr = lStr.trim();
			element[i].value = lStr;
			
			if(lStr == "")
			{
				alert(PARTY_PARTYNAME);
				return false;
			}
		}					
		
		element = document.getElementsByName("txtChkAmt");
			
		for(var i = 0; i < element.length; ++i)
		{
		 	var lStr = new String(element[i].value);
			lStr = lStr.trim();
			element[i].value = lStr;
			lTotalChkAmt += parseFloat(lStr);
			
			if(lStr == "")
			{
				alert(PARTY_PARTYCHQNO);
				return false;
			}
		}
	}
	
	return true;
}
		
// Functions For partyInfo.jsp ...... Ends......

// Functions For createPensionSpecificBill.jsp ...... Ends......
var gSavePensionBill = false;
function saveBillUsingAjax()
{
	//alert('gSavePensionBill'+gSavePensionBill);
	if(!gSavePensionBill){
		gSavePensionBill = true;
		showProgressbar();				
		disable();
		
		var lNetAmt = document.getElementById("txtNetAmt").value;
		//txtExpenditure
		if(lNetAmt >= 0)
		{
			//HandleBillAjaxSave();
			billSaveUsingAJAX()
		}
		else
		{
			alert("Bill with zero amount cannot be generated");
			hideProgressbar();
			window.self.close();
		}
	}
}

function HandleBillAjaxSave()
{
	
	xmlHttp=GetXmlHttpObject();
	var uri = "ifms.htm?actionFlag=savePensionSpecificBills";
	
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	 
	var params = runBillData(); 
	//alert(params);
	//alert(params.length);
	
	///  alert(uri);         
	
	xmlHttp.onreadystatechange=billstateChanged;
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");		
	xmlHttp.send(params);//params);
}
function billSaveUsingAJAX()
{
	var uri = "ifms.htm?actionFlag=savePensionSpecificBills";
	var params = runBillData(); 
	var arrearDtlsStr = document.getElementById("arrearDtls").value;
	arrearDtlsStr = arrearDtlsStr.replace(new RegExp(">", 'g'),"~");
	var currRole=document.getElementById("hdCurrRole").value;
	//alert(arrearDtlsStr);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters: params+"&arrearDtlsStr="+arrearDtlsStr+"&currRole="+currRole,
		        onSuccess: function(myAjax) {
					getDataStateChangedForBillSave(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');hideProgressbar();} 
		          } );
}
function getDataStateChangedForBillSave(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;	
	var lSavedBillTokenNo = '';
	
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');				
		document.getElementById("hidBillNo").value = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		var lSavedBillCntrlNo = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
		lSavedBillTokenNo = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
		var lNewBillFlag = XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;
	
		//For Supp Bill
		var lStrSubjetId=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	
		var lStrSubjetId2=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
		
	
		//hidBillCntrlNo
		document.getElementById("hidBillCntrlNo").value = lSavedBillCntrlNo;
		alert(XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue);
		
		 if(lNewBillFlag != null && lNewBillFlag == "false" )
		{
			if(document.getElementById("PensionType") && document.getElementById("PensionType").value == 'Monthly' && window.opener.document.getElementById("cmbForMonth"))
			{
				window.opener.document.getElementById("cmbForMonth").focus();
			}
			else
			{
				window.opener.location.href = window.opener.location.href; 
			}
		}
		else
		{
			if(lStrSubjetId == "45")
			{
				
				//self.location.reload();
				window.location.href="ifms.htm?actionFlag=loadSuppBillData";
			}else{
				window.opener.location.href = window.opener.location.href;
			}
		}
	}
	else
	{
		alert("Bill not saved.");
		hideProgressbar();
	}	
	gSavePensionBill = false;
	if(lStrSubjetId == "45")
	{
		window.opener.location.href="ifms.htm?actionFlag=loadSuppBillData";
		
	}
	window.opener.location.reload();
	window.close();
}
function billstateChanged() 
{ 
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
	{ 
//		hide_img();
//		enable_div();
//		hideProgressbar();
//		enable();
		
		XMLDoc = xmlHttp.responseXML.documentElement;	
		
		var lSavedBillTokenNo = '';
		
		if(XMLDoc != null)
		{				
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');				
			document.getElementById("hidBillNo").value = XmlHiddenValues[0].childNodes[0].text;
			var lSavedBillCntrlNo = XmlHiddenValues[0].childNodes[1].text;
			lSavedBillTokenNo = XmlHiddenValues[0].childNodes[2].text;
			var lNewBillFlag = XmlHiddenValues[0].childNodes[4].text;
			//For Supp Bill
			var lStrSubjetId=XmlHiddenValues[0].childNodes[5].text;
		
			//
			document.getElementById("hidBillCntrlNo").value = lSavedBillCntrlNo;
			alert(XmlHiddenValues[0].childNodes[3].text);
			
			 if(lNewBillFlag != null && lNewBillFlag == "false" )
			{
				if(document.getElementById("PensionType") && document.getElementById("PensionType").value == 'Monthly' && window.opener.document.getElementById("cmbForMonth"))
				{
					window.opener.document.getElementById("cmbForMonth").focus();
				}
				else
				{
					window.opener.location.href = window.opener.location.href; 
				}
			}
			else
			{
				if(lStrSubjetId == "45")
				{
					
					//self.location.reload();
					window.location.href="ifms.htm?actionFlag=loadSuppBillData";
				}else{
					window.opener.location.href = window.opener.location.href;
				}
			}
		}
		else
		{
			alert("Bill not saved.");
			hideProgressbar();
		}	
		gSavePensionBill = false;
		if(lStrSubjetId == "45")
		{
			window.opener.location.href="ifms.htm?actionFlag=loadSuppBillData";
			
		}
		window.opener.location.reload();
		window.close();
	}
}
function validateBeforeBillSaveData()
{
	/*
	 * Validation if nominee details is present for dcrg bill when pensioenr is dead starts <<<
	 */
	var lValidNomineeFlag = document.getElementById("validNomineeFlag").value;
	if(lValidNomineeFlag != "")
	{
		if(lValidNomineeFlag == "N")
		{
			alert("Please enter nominee details.");
			return false;
		}
	}
	/*
	 * Validation if nominee details is present for dcrg bill when pensioenr is dead ends >>>
	 */
	if(RowNum >1)
	{
		alert("Please generate Supplementary Bill for multiple party.");
		return false;
	}
	
	var paymentMode=document.getElementById("paymentMode").value;
	var flag=0;
	
	if(paymentMode == "ECS")
	{
		
		var arrMicrCode=document.getElementsByName("txtMicrCode");
	
		if(arrMicrCode.length>0)
		{
			for(var i=0;i<arrMicrCode.length;i++)
			{
				if(arrMicrCode[i].value==null || arrMicrCode[i].value=='')
				{
					flag=1;
					break;
				}
			}
		}
	}
	
	if(flag==1)
	{
		alert("Please select a bank with IFSC code to take ECS as Payment Mode.");
		return false;
	}
		
	return true;
	
}
function saveData()
{
	
	
	var currRole=document.getElementById("hdCurrRole").value;
	//var showReadOnly=document.getElementById("showReadOnly").value;
	
	if(currRole == '365451')
	{
		document.getElementById("userAction").value = "approve";
	}else{
		document.getElementById("userAction").value = "save";
	}
	
	if(validateBeforeBillSaveData()==true)
	{
		saveBillUsingAjax();
	}
}
		
function rejectBill()
{
	var billRemarks=document.getElementById("id_txtareaRemarks").innerHTML;
	
	if(isEmpty("id_txtareaRemarks","Please enter Remarks before Rejecting a Bill.")==true)
	{
		
		document.getElementById("userAction").value = "reject";
		saveBillUsingAjax();
	}
	
}
function negativeAmountChk()
		{
			var netAmount = document.getElementsByName("hidnetPensionAmount");
						
			if(document.getElementById("Flag"))
			{
				if(document.getElementById("Flag").value == "N")
				{
					alert(BILL_NT_GEN);
					return false;
				}
			}
			else
			{
				for(var count=0;count < netAmount.length;count++)
				{
					if(netAmount[count].value <= 0)
					{
						alert(NET_AMT_NULL);
						return false;
					}
				}
			}
			return true;
		}
		function closeBill()
		{
			var isFromChqPrep = null;
			
			if(document.getElementById("isFromChqPrep") != null)
			{
				isFromChqPrep = document.getElementById("isFromChqPrep").value;
			}
			
			if(document.getElementById("btnClose") != null)
			{
				window.opener.hideProgressbar();
				window.close();
			}
			
			if(isFromChqPrep != '1')
			{
				if (window.opener && !window.opener.closed) 
				{
					if(document.getElementById("PensionType") && document.getElementById("PensionType").value == 'Monthly')
					{
						if(window.opener.document.getElementById("txtbranchCode"))
						{
							window.opener.document.getElementById("cmbForMonth").focus();
						}
						else
						{
							window.opener.location.reload();
						}
					}
					else
					{
						window.opener.location.reload();
					}
				}			
			}		
		}
		
		// JAVASCRIPT FOR REMARKS PAGE.
		function showAttachPage()
		{
			url="ifms.htm?viewName=cmnAttachment&parentForm=" + document.forms[0].name;
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=800,height=400"); 
		}
		
		function showRemarks(url)
		{
			url += "&BillNo=" + document.getElementById("hidBillNo").value;
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=700,height=650"); 			
		}
		function showBill(URL)
		{
			URL += "&BillNo=" + document.getElementById("hidBillNo").value;
			window.open(URL, "_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
		/********		VALIDATION ON SAVE		*************/
		function IsNull(Id, Message)
		{
			var element = document.getElementById(Id);
			if(element != null)
			{
				var lStr = new String(element.value);
				lStr = lStr.trim();
				element.value = lStr;
				if(lStr.length == 0)
				{
					alert(Message);
					return false;
				}
			}
			
			return true;
		}
		

// Functions For createPensionSpecificBill.jsp ...... Ends......

// AJAX For Validate CVP DCRG & Pension Bills Token No. ...... Start.....

var PnsnBillToken = null;
function validatePnsnBillsTokenNo(lvalue) 
{
	if(lvalue.value != '')
	{
		PnsnBillToken = lvalue.value;
		getPnsnBillTokenValidByAJAX();
	}
	else if(lvalue.value == '')
	{
		document.getElementById("txtPnsnTokenNo").value = "";
	}
}
		
function getPnsnBillTokenValidByAJAX()
{
	req = createXMLHttpRequest();
	
	if(req != null)
	{
		var baseUrl = "ifms.htm?actionFlag=validatePensionBillsTokenNo&pnsnBillToken="+PnsnBillToken;
						
		req.open("post", baseUrl, true); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = responsePnsnBillTokenValid; 
		req.send(baseUrl);
	}
	else
	{
		alert ("Your browser does not support AJAX!");
	}
}
	
function responsePnsnBillTokenValid()
{
	if(req.readyState==complete_state)
	{ 
		var XMLDoc = req.responseXML.documentElement;
		var XMLEntries = XMLDoc.getElementsByTagName("TOKENRES");				
		
		var ResultToken = XMLEntries[0].text;
		
		if(ResultToken == 'false')
		{
			alert(VALID_TOK);
			document.getElementById("txtPnsnTokenNo").value = "";
			goToFieldTab("txtPnsnTokenNo",1);
		}
		else
		{	
			document.getElementById("userAction").value = "save";
			saveBillUsingAjax();
			window.setTimeout("window.close()",1500);					
		}			
	}
}
	
// AJAX For Validate CVP DCRG & Pension Bills Token No. ...... End......

function showHideDiv(lvalue)
{
	if(document.getElementById(lvalue).style.display=='block')
	{
		document.getElementById(lvalue).style.display='none';
	}
	else if(document.getElementById(lvalue).style.display=='none')
	{
		document.getElementById(lvalue).style.display='block';
	}
}

function printPensionArrear(lParaValue)
{
	if(lParaValue == '6PayArrear')
	{
		document.getElementById("hidPrintString").value = document.getElementById("hid6PayPrintString").value;
	}
	else if(lParaValue == 'BillArrear') 
	{
		document.getElementById("hidPrintString").value = document.getElementById("hidBillArrPrintString").value;
	}
	Open_PrintStringBill();
}

// Calling from Common Print Bill link from MR and Monthly. 

function Open_PrintStringBill()
{
	
//			var cw = window.open(null,null,"height="+(screen.height - 75)+",width="+(screen.width)+",top=200,left=200,status=no,toolbar=no,menubar=no,location=no");
	var cw = window.open("","","status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes");
	cw.document.write('<style media="print">');
    cw.document.write('@FONT-FACE { font-family : sans-serif;}');
    cw.document.write('@media print {BODY { font-size: 10pt }}');
    cw.document.write('@media screen {BODY { font-size: 10pt } }');
    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
    cw.document.write('@page cheque{ size 14in 12in; margin: 0.5cm }');
    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
    cw.document.write('</style>');
    cw.document.write('<body>');  
    
    var printString = document.getElementById("hidPrintString").value;
    cw.document.write(printString);	
  
    cw.document.write('</body>');  
    cw.document.write('<script language="javascript">');
	cw.document.write("window.print();");
 	cw.document.write( '<' + "/script" + '>');
    cw.location.reload(false); 
}
function viewRemarks()
{
	var currRole = document.getElementById("hdCurrRole").value;
	var billNo=document.getElementById("hidBillNo").value;
	var url = "ifms.htm?actionFlag=getRemarksOfBill&BillNo="+billNo+"&currRole="+currRole;
	window_new_update1(url);
}
function window_new_update1(url)
{
	
	var newWindow = null;
   	var height = screen.height - 300;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function runBillData(){

	var totalElements= document.forms[0].elements.length
	  var uri='';
	  for(i=0;i<totalElements;i++)
	  {
		  if(document.forms[0].elements[i].id !="hidPrintString" && document.forms[0].elements[i].id !="arrearDtls" && document.forms[0].elements[i].id !="hidPrintDCRGBillString" && document.forms[0].elements[i].id !="hidPrintCVPBillString")
		  {
	   if(document.forms[0].elements[i].type=="checkbox" ||document.forms[0].elements[i].type=="radio")
	  	{
	  		if(document.forms[0].elements[i].checked==1
	  				&& document.forms[0].elements[i].name != 'undefined' 
	  				&& document.forms[0].elements[i].name != ''
	  				&& document.forms[0].elements[i].value != 'undefined')
	  		{
				   uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
			}
		}
		else if(document.forms[0].elements[i].type=="select-multiple")
		{
			for(j=0;j<document.forms[0].elements[i].options.length;j++)
			{
				if(document.forms[0].elements[i].options[j].selected 
					&& document.forms[0].elements[i].name != 'undefined' 
					&& document.forms[0].elements[i].options[j].value != 'undefined'
					&& document.forms[0].elements[i].name != '') 
				uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].options[j].value);
			}	
		}
		else
		{
			if(document.forms[0].elements[i].name != 'undefined' 
				&& document.forms[0].elements[i].name != ''
				&& document.forms[0].elements[i].value != 'undefined') 
	        uri= uri+'&'+document.forms[0].elements[i].name+'='+encodeURIComponent(document.forms[0].elements[i].value);
	    }
	  }
	  }
	  uri = uri.replace(/-/g,"%2D");
	  return uri;
	 }
function validatePaymentMode()
{
	var paymentMode=document.getElementById("paymentMode").value;
	var flag=0;
	
	if(paymentMode == "Cash")
		{
			confirm("Are you sure?");
		}
}

