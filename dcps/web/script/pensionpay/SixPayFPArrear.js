var ROW_ID_CASHARREAR = 0;
function getPPODetails() 
{
	clearAllData();
	if(document.getElementById("txtPpoNo").value == "")
	{
		alert('Please enter ppo no.');
		return;
	}
	var txtPpoNo = document.getElementById("txtPpoNo").value;
	document.getElementById("txtTotAmt").value="";
	document.getElementById("txtPpoName").value="";
	document.getElementById("txtTotAmt").disabled=false;
	
		for(var i=0;i<5;i++)
		{
			document.getElementById("cmbPayinMonth"+(i+1)).options[0].value = "";
			document.getElementById("cmbPayinMonth"+(i+1)).options[0].text = "";
			
			document.getElementById("cmbPayinYear"+(i+1)).options[0].value ="";
			document.getElementById("cmbPayinYear"+(i+1)).options[0].text ="";
			
			document.getElementById("txtInstallmentAmt"+(i+1)).value ="";
			document.getElementById("cmbPayinYear"+(i+1)).style.width = "100px";
			document.getElementById("cmbPayinMonth"+(i+1)).style.width = "100px";
		}
	showProgressbar();
	var url = "ifms.htm?actionFlag=getSixPayPPODtls&txtPpoNo="+txtPpoNo;
	var myAjax = new Ajax.Request(url,
			{
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function(myAjax) {getPPOFromAjaxStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');hideProgressbar();} 
			});
	
}

function getPPOFromAjaxStateChanged(myAjax) 
{
	 
	 
		var XMLDoc = myAjax.responseXML.documentElement;
		var errorXMLDoc = XMLDoc.getElementsByTagName("ERRORMSG");
		if(errorXMLDoc[0] != null)
		{
			var lStrErrorMsg = errorXMLDoc[0].childNodes[0].nodeValue;
			alert(lStrErrorMsg);
			document.getElementById("txtPpoNo").value = "";
			clearAllData();
			hideProgressbar();
			return;
		}
		
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPO');
		var emptyList = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		if(emptyList == "EmptyList")
		{
			
			alert('No such PPO exists.');
			document.getElementById("txtPpoName").value = "";
			document.getElementById("txtPpoNo").value = "";
			for(var i=0;i<5;i++)
			{
				document.getElementById("cmbPayinMonth"+(i+1)).options[0].value = "";
				document.getElementById("cmbPayinMonth"+(i+1)).options[0].text = "";
				
				document.getElementById("cmbPayinYear"+(i+1)).options[0].value ="";
				document.getElementById("cmbPayinYear"+(i+1)).options[0].text ="";
				
				document.getElementById("txtInstallmentAmt"+(i+1)).value ="";
				document.getElementById("cmbPayinYear"+(i+1)).style.width = "100px";
				document.getElementById("cmbPayinMonth"+(i+1)).style.width = "100px";
			}
			hideProgressbar();
			return;
		}
		var pnsnrName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		var fmlyPnsrName = XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;
		var pnsnrCode=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
		var caseStatus = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
		var roptype = XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
		var tempFlag = 0;
		var matchedPnsnrCode;
		
			/*if(caseStatus != 'Approved')
			{
				alert('No such PPO exists.');
				document.getElementById("txtPpoName").value = "";
				document.getElementById("txtPnsnrCode").value = "";
				document.getElementById("txtCaseStatus").value = "";
				document.getElementById("txtPpoNo").value = "";
				return;
			}
			if(roptype != '2006')
			{
				alert('Pay commission is not suitable for this ppo.');
				document.getElementById("txtPpoName").value = "";
				document.getElementById("txtPnsnrCode").value = "";
				document.getElementById("txtCaseStatus").value = "";
				document.getElementById("txtPpoNo").value = "";
				return;
			}*/
			document.getElementById("txtPpoName").value = pnsnrName;
			document.getElementById("txtPnsnrCode").value = pnsnrCode;
			document.getElementById("txtCaseStatus").value = caseStatus;
			document.getElementById("txtFmlyPnsrName").value = fmlyPnsrName;
			
			//check wether pensioner code is already exists or not 
			
//			
//			var listOfPnsnrCode = document.getElementById("listOfPnsnrCode").value;
//			var totalNoOfPnsnrCode = document.getElementById("totalNoOfPnsnrCode").value;
//			
//			var tempPnsnrCodeArr = listOfPnsnrCode.substr(1,listOfPnsnrCode.length-2);
//			var arrTotalPnsnrCode = tempPnsnrCodeArr.split(",");
//			for(var j=0;j<Number(totalNoOfPnsnrCode);j++)
//			{
//				if(Number(pnsnrCode) == Number(arrTotalPnsnrCode[j]))
//				{
//					tempFlag = 1;
//					matchedPnsnrCode = Number(pnsnrCode);
//				}
//			}
			var url = "ifms.htm?actionFlag=getSixPayPPODtls&pnsnrCode="+pnsnrCode+"&FlagVal=Y";
			var myAjaxTemp = new Ajax.Request(url,
						{
					        method: 'post',
					        asynchronous: true,
					        parameters:url,
					        onSuccess: function(myAjaxTemp) {fillDataFromAjaxStateChanged(myAjaxTemp);},
					        onFailure: function(){ alert('Something went wrong...');hideProgressbar();} 
						});
			
				
			
	
}

function clearAllData()
{
	var listOfMonthDesc = document.getElementById("listOfMonthDesc").value;
	var templistOfMonthDescArr = listOfMonthDesc.substr(1,listOfMonthDesc.length-2);
	var arrlistOfMonthDesc = templistOfMonthDescArr.split(",");
	
	var listOfMonthId = document.getElementById("listOfMonthId").value;
	var templistOfMonthIdArr = listOfMonthId.substr(1,listOfMonthId.length-2);
	var arrlistOfMonthId = templistOfMonthIdArr.split(",");
	
	var listOfYearDesc = document.getElementById("listOfYearDesc").value;
	var templistOfYearDescArr = listOfYearDesc.substr(1,listOfYearDesc.length-2);
	var arrlistOfYearDesc = templistOfYearDescArr.split(",");
	
	var totalNoOfMonths = document.getElementById("totalNoOfMonths").value;
	var totalNoOfYears = document.getElementById("totalNoOfYears").value;
	
	var cashArrearTable = document.getElementById("tblCashPaymentDtls");
	/*
	 * Deleting all rows before populating
	 */
	 for(var i = cashArrearTable.rows.length; i > 1;i--)
     {
		 cashArrearTable.deleteRow(i -1);
     }
	for(var cnt =0;cnt<5;cnt++)
	{
		document.getElementById('cmbPayinMonth'+(cnt+1)).options.length = 0;
		var optn = document.createElement("OPTION");
		optn.value = "-1";
		optn.text = "-- Select --";
		document.getElementById('cmbPayinMonth'+(cnt+1)).options.add(optn);
		for(var month=0;month<totalNoOfMonths;month++)
		{
			var option  = document.createElement("OPTION");
			option.value = arrlistOfMonthId[month];
			option.text = arrlistOfMonthDesc[month];
			document.getElementById('cmbPayinMonth'+(cnt+1)).options.add(option);
			//document.getElementById("cmbPayinMonth"+(j+1)).disabled=false;
			document.getElementById("cmbPayinMonth"+(cnt+1)).style.width = "100px";
			
		}
		
		document.getElementById('cmbPayinYear'+(cnt+1)).options.length = 0;
		var optin = document.createElement("OPTION");
		optin.value = "-1";
		optin.text = "-- Select --";
		document.getElementById('cmbPayinYear'+(cnt+1)).options.add(optin);
		for(var year=0;year<totalNoOfYears;year++)
		{
			var defaultoption  = document.createElement("OPTION");
			defaultoption.value = arrlistOfYearDesc[year];
			defaultoption.text = arrlistOfYearDesc[year];
			document.getElementById('cmbPayinYear'+(cnt+1)).options.add(defaultoption);
			//document.getElementById("cmbPayinYear"+(j+1)).disabled=false;
			document.getElementById("cmbPayinYear"+(cnt+1)).style.width = "100px";
			
		}
		
		document.getElementById('txtInstallmentAmt'+(cnt+1)).value = "";
		document.getElementById("txtTotAmt").value = "";
		//document.getElementById("txtTotAmt").disabled=false;
	}
}

function fillDataFromAjaxStateChanged(myAjaxTemp)
{
		hideProgressbar();
		var XMLDoc = myAjaxTemp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPODTLS');
		var XmlCashArrearDtls = XMLDoc.getElementsByTagName('CASHARREARDTLS');
		var lArrIsDtlsExist = XMLDoc.getElementsByTagName("ISDTLSEXSIST");
		var isDtlsExist = lArrIsDtlsExist[0].childNodes[0].nodeValue;
		document.getElementById("hdnIsDtlsExist").value = isDtlsExist;
		if(isDtlsExist == "N")
		{
			return;
		}
		var listYearDesc = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		var listMonthId = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
		var listMonthDesc = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
		var listInstallments = XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
		var count = Number(XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue);
		var totalAmnt=0;
	
		
		var templistYearDescArr = listYearDesc.substr(1,listYearDesc.length-2);
		var arrlistYearDesc = templistYearDescArr.split(",");
		
		var templistMonthIdArr = listMonthId.substr(1,listMonthId.length-2);
		var arrlistMonthId = templistMonthIdArr.split(",");
		
		var templistMonthDescArr = listMonthDesc.substr(1,listMonthDesc.length-2);
		var arrlistMonthDesc = templistMonthDescArr.split(",");
		
		var templistInstallmentsArr = listInstallments.substr(1,listInstallments.length-2);
		var arrlistInstallments = templistInstallmentsArr.split(",");
		
		for(var j = 0;j<count;j++)
		{
			var yearDesc = arrlistYearDesc[j] ;
			var monthId = arrlistMonthId[j];
			var monthDesc = arrlistMonthDesc[j];
			var installments = arrlistInstallments[j];
			
			document.getElementById("cmbPayinMonth"+(j+1)).options.length = 0;
			document.getElementById("cmbPayinYear"+(j+1)).options.length = 0;
			
			if(yearDesc.trim() == 'null')
			{
				var listOfYearDesc = document.getElementById("listOfYearDesc").value;
				var templistOfYearDescArr = listOfYearDesc.substr(1,listOfYearDesc.length-2);
				var arrlistOfYearDesc = templistOfYearDescArr.split(",");
				var totalNoOfYears = document.getElementById("totalNoOfYears").value;
				
				var optin = document.createElement("OPTION");
				optin.value = "-1";
				optin.text = "-- Select --";
				document.getElementById('cmbPayinYear'+(j+1)).options.add(optin);
				for(var year=0;year<totalNoOfYears;year++)
				{
					var option  = document.createElement("OPTION");
					option.value = arrlistOfYearDesc[year];
					option.text = arrlistOfYearDesc[year];
					document.getElementById('cmbPayinYear'+(j+1)).options.add(option);
					//document.getElementById("cmbPayinYear"+(j+1)).disabled=false;
					document.getElementById("cmbPayinYear"+(j+1)).style.width = "100px";
					
				}
				
			}
			else
			{
									
				var optn = document.createElement("OPTION");
				document.getElementById("cmbPayinYear"+(j+1)).options.add(optn);
				document.getElementById("cmbPayinYear"+(j+1)).options[0].value=yearDesc;
				document.getElementById("cmbPayinYear"+(j+1)).options[0].text=yearDesc;
				document.getElementById("cmbPayinYear"+(j+1)).options[0].selected="selected";
				document.getElementById("cmbPayinYear"+(j+1)).style.width = "100px";
				//document.getElementById("cmbPayinYear"+(j+1)).disabled=true;
			}
			if(monthId.trim() == 'null' && monthDesc.trim() == 'null')
			{
				var listOfMonthDesc = document.getElementById("listOfMonthDesc").value;
				var templistOfMonthDescArr = listOfMonthDesc.substr(1,listOfMonthDesc.length-2);
				var arrlistOfMonthDesc = templistOfMonthDescArr.split(",");
				
				var listOfMonthId = document.getElementById("listOfMonthId").value;
				var templistOfMonthIdArr = listOfMonthId.substr(1,listOfMonthId.length-2);
				var arrlistOfMonthId = templistOfMonthIdArr.split(",");
				
				var totalNoOfMonths = document.getElementById("totalNoOfMonths").value;
				
				var opn = document.createElement("OPTION");
				opn.value = "-1";
				opn.text = "-- Select --";
				document.getElementById('cmbPayinMonth'+(j+1)).options.add(opn);
				for(var month=0;month<totalNoOfMonths;month++)
				{
					var optiondefault  = document.createElement("OPTION");
					optiondefault.value = arrlistOfMonthId[month];
					optiondefault.text = arrlistOfMonthDesc[month];
					document.getElementById('cmbPayinMonth'+(j+1)).options.add(optiondefault);
					//document.getElementById("cmbPayinMonth"+(j+1)).disabled=false;
					document.getElementById("cmbPayinMonth"+(j+1)).style.width = "100px";
				}	
			}
			else
			{
				var tempoption = document.createElement("OPTION");
				document.getElementById("cmbPayinMonth"+(j+1)).options.add(tempoption);
				document.getElementById("cmbPayinMonth"+(j+1)).options[0].value=monthId;
				document.getElementById("cmbPayinMonth"+(j+1)).options[0].text=monthDesc;
				document.getElementById("cmbPayinMonth"+(j+1)).options[0].selected="selected";
				document.getElementById("cmbPayinMonth"+(j+1)).style.width = "100px";
				//document.getElementById("cmbPayinMonth"+(j+1)).disabled=true;
			}
			
			document.getElementById("txtInstallmentAmt"+(j+1)).value = installments;
			totalAmnt = totalAmnt + Number(installments);
			
		}
		document.getElementById("txtTotAmt").value = totalAmnt; 
		document.getElementById("txtTotAmt").disabled=true;
		
		//---Insertion of cash arrear payment starts
		var lMonthYear = "";
		var lAmount = "";
		var lPaidFlag = "";
		var lMonthId = "";
		var lYearId = "";
		var lArrOptions;
		if(XmlCashArrearDtls != null)
		{
			var table=document.getElementById("tblCashPaymentDtls");
			
			/*
			 * Deleting all rows of cash arrear table
			 */
			 for(var i = table.rows.length; i > 1;i--)
		     {
				 table.deleteRow(i -1);
		     }  
			for(var cnt = 0; cnt < XmlCashArrearDtls.length ; cnt++)
			{
				lMonthYear = XmlCashArrearDtls[cnt].childNodes[0].childNodes[0].nodeValue;
				lAmount = XmlCashArrearDtls[cnt].childNodes[1].childNodes[0].nodeValue;
				lPaidFlag = XmlCashArrearDtls[cnt].childNodes[2].childNodes[0].nodeValue;
				if(lMonthYear != null)
				{
					lMonthId = lMonthYear.substring(4,6);
					if(parseInt(lMonthId) < 10)
					{
						lMonthId = lMonthId.substring(1,2);
					}
					lYearId = lMonthYear.substring(0,4);
				}
				
				var rowCount=table.rows.length;
				var newRow = table.insertRow(rowCount); 	  		
				
			   	var Cell1 = newRow.insertCell(0);
			   	Cell1.className = "tds";
			   	Cell1.align="center";
			   	var Cell2 = newRow.insertCell(1);
			   	Cell2.className = "tds";
			   	Cell2.align="center";
			   	var Cell3 = newRow.insertCell(2);
			   	Cell3.className = "tds";
			   	Cell3.align="center";
			   	var Cell4 = newRow.insertCell(3);
			   	Cell4.className = "tds";
			   	Cell4.align="center";
			   	
			   	ROW_ID_CASHARREAR = ROW_ID_CASHARREAR +1;
			   	Cell1.innerHTML = '<select name="cmbCashPayInMonth" id="cmbCashPayInMonth'+Number(ROW_ID_CASHARREAR)+'" value="'+lMonthId+'" >'+
									'<option value="-1">--Select--</option>' +LISTMONTHS +
									'</select>&nbsp;'+
									'<select name="cmbCashPayInYear" id="cmbCashPayInYear'+Number(ROW_ID_CASHARREAR)+'" value="'+lYearId+'" >'+
										'<option value="-1">--Select--</option>'+ LISTYEARS +
									'</select>';
				Cell2.innerHTML ='<input type="text" name="txtCashAmount" id="txtCashAmount'+Number(ROW_ID_CASHARREAR)+'" size="15" value="'+lAmount+'" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
				Cell3.innerHTML = '<lable>'+lPaidFlag+'</label>';
				if(lPaidFlag == 'N')
				{
					Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblCashPaymentDtls\')" /> '+
					'<input type="hidden" name="hdnPaidFlag" id="hdnPaidFlag'+Number(ROW_ID_CASHARREAR)+'" value="'+lPaidFlag+'" />';
				}
				else
				{
					Cell4.innerHTML = '<input type="hidden" name="hdnPaidFlag" id="hdnPaidFlag'+Number(ROW_ID_CASHARREAR)+'" value="'+lPaidFlag+'" />';
				}
				
				//---Set value as selected.
				lArrOptions = document.getElementById("cmbCashPayInMonth"+Number(ROW_ID_CASHARREAR)).options;
				for(var i =0 ; i < lArrOptions.length ; i++)
				{
					if(lArrOptions[i].value == lMonthId)
					{
						lArrOptions[i].selected = "selected";
					}
				}
				
				lArrOptions = document.getElementById("cmbCashPayInYear"+Number(ROW_ID_CASHARREAR)).options;
				for(var j =0 ; j < lArrOptions.length ; j++)
				{
					if(lArrOptions[j].value == lYearId)
					{
						lArrOptions[j].selected = "selected";
					}
				}
				if(lPaidFlag == 'Y')
				{
					document.getElementById("cmbCashPayInYear"+Number(ROW_ID_CASHARREAR)).disabled = true;
					document.getElementById("cmbCashPayInMonth"+Number(ROW_ID_CASHARREAR)).disabled = true;
					document.getElementById("txtCashAmount"+Number(ROW_ID_CASHARREAR)).disabled = true;
				}
				//document.getElementById("hidCashArrearCount").value = Number(ROW_ID_CASHARREAR)+1; 
			}
			document.getElementById("hidCashArrearCount").value = XmlCashArrearDtls.length;
		}
}
function getInstallmentFromTotAmnt()
{
	if(document.getElementById("txtTotAmt").value == "")
	{
		alert('Please insert any amount to proceed ahead.');
		return;
	}
	if(Number(document.getElementById("txtTotAmt").value) <= 0)
	{
		alert('Total intallment amount must be greater than zero.');
		document.getElementById("txtTotAmt").value = "";
		return;
	}
	var txtTotAmt = document.getElementById("txtTotAmt").value;
	var tempAmnt = Number(txtTotAmt)%5;
	var finalAmnt = Number(txtTotAmt) - Number(tempAmnt);
	for(var i=1;i<=5;i++)
	{
		document.getElementById("txtInstallmentAmt"+i).value = Number(finalAmnt)/5;
		if(i==5)
		{
			document.getElementById("txtInstallmentAmt"+i).value = Number(finalAmnt)/5 + Number(tempAmnt);
		}
		
	}
}
function getInstallmentFromTotalAmnt()
{
	if(document.getElementById("txtTotalAmt").value == "")
	{
		alert('Please insert any amount to proceed ahead.');
		return;
	}
	if(Number(document.getElementById("txtTotalAmt").value) <= 0)
	{
		alert('Total intallment amount must be greater than zero.');
		document.getElementById("txtTotalAmt").value = "";
		return;
	}
	if(Number(document.getElementById("txtTotalAmt").value) <= Number(document.getElementById("hidTotAmt").value))
	{
		alert('Total installment amount must be greater than previous installment amount.');
		document.getElementById("txtTotalAmt").value = "";
		return;
	}
	var txtTotAmt = document.getElementById("txtTotalAmt").value;
	var tempAmnt = Number(txtTotAmt)%5;
	var finalAmnt = Number(txtTotAmt) - Number(tempAmnt);
	for(var i=1;i<=5;i++)
	{
		document.getElementById("txtInstallmentAmt"+i).value = Number(finalAmnt)/5;
		if(i==5)
		{
			document.getElementById("txtInstallmentAmt"+i).value = Number(finalAmnt)/5 + Number(tempAmnt);
		}
		
	}
}




function saveSixPayArrears()
{
	if(document.getElementById("txtTotAmt").value == "")
	{
		alert('Please insert any amount to proceed ahead.');
		return;
	}
	if(!validateCashArrearPayment())
	{
		return;
	}
	var uri = 'ifms.htm?actionFlag=saveSixPayArrears';
	var url = runForm(0); 
	var totalCashPayEntries = document.getElementById("hidCashArrearCount").value;
	var cashArrearDtls = "";
	var payInMonth = "";
	var payInYear = "";
	var cashArrearAmt = "";
	var paidFlag = "";
	for(var cnt = 1;cnt <=Number(totalCashPayEntries);cnt++)
	{
		if(document.getElementById("cmbCashPayInMonth"+cnt) != null)
		{
			payInMonth = document.getElementById("cmbCashPayInMonth"+cnt).value;
			payInYear = document.getElementById("cmbCashPayInYear"+cnt).value;
			cashArrearAmt = document.getElementById("txtCashAmount"+cnt).value;
			paidFlag = document.getElementById("hdnPaidFlag"+cnt).value;
			//--deleting and re-inerting only that entries which have paid flag N 
			if(paidFlag == "N")
			{
				cashArrearDtls = cashArrearDtls+"&cashArrearDtls="+payInMonth+"~"+payInYear+"~"+cashArrearAmt;
			}
		}
	}
	showProgressbar();
	url = uri + url+cashArrearDtls;   
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');hideProgressBar();} 
			});
}
function validateCashArrearPayment()
{
	var listMonth = document.getElementsByName("cmbCashPayInMonth");
	var listYear = document.getElementsByName("cmbCashPayInYear");
	for(var cnt = 0; cnt < listMonth.length ; cnt++)
	{
		if(listMonth[cnt].value == "-1")
		{
			alert("Please select any month.");
			listMonth[cnt].focus();
			return false;
		}
		if(listYear[cnt].value == "-1")
		{
			alert("Please select any year.");
			listYear[cnt].focus();
			return false;
		}
	}
	return true;
}
function saveSixPayArrearsStateChanged(myAjax) 
{ 
	var XMLDoc=myAjax.responseXML.documentElement;
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   var hidString = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	  
		   if(hidString == 'Add')
		   {
			   alert("Six pay arrears saved successfully.");
			   self.location.reload();
		   }
		   if(hidString == 'Revise')
		   {
			   alert("Six pay arrears revised successfully.");
			   self.close();
			   window.opener.location.reload();
		   }
		   if(hidString == 'Update')
		   {
			   alert("Six pay arrears updated successfully.");
			   self.location.reload();
		   }
		   if(hidString == 'Config')
		   {
			   alert("Payment month and year have been set successfully.");
			   self.location.href = "ifms.htm?actionFlag=loadSixPayFPArrearConfig";
		   }
		   if(hidString == "NoUpdate")
		   {
			   alert("No valid pension case found to update six pay arrears' payin month-year.");
			   hideProgressBar();
		   }
	
}

function revisionOfPPOCase()
{
	
	if(document.getElementById("txtPpoNo").value == "")
	{
		alert('Please enter PPO No. to proceed ahead.');
		return;
	}
	 
	var txtPnsnrCode = document.getElementById("txtPnsnrCode").value;
	//var tempFlag = 0;
	var isDtlsExist = document.getElementById("hdnIsDtlsExist").value;
	
	/*var listOfPnsnrCode = document.getElementById("listOfPnsnrCode").value;
	var totalNoOfPnsnrCode = document.getElementById("totalNoOfPnsnrCode").value;
	
	var tempPnsnrCodeArr = listOfPnsnrCode.substr(1,listOfPnsnrCode.length-2);
	var arrTotalPnsnrCode = tempPnsnrCodeArr.split(",");
	for(var j=0;j<Number(totalNoOfPnsnrCode);j++)
	{
		if(Number(txtPnsnrCode) == Number(arrTotalPnsnrCode[j]))
		{
			tempFlag = 1;
		}
	}*/
	if(isDtlsExist == "Y")
	{
		url = "ifms.htm?actionFlag=getArrearScreen&Revision=Y&txtPnsnrCode="+txtPnsnrCode;
		var newWindow = null;
	   	var height = screen.height - 400;
	   	var width = screen.width - 400 ;
	   	var urlstring = url;
	   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=100,left=180";
	   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
	}
	else
	{
		alert('You cannot revised this PPO case.Because arrear amount has not yet calculated.');
		return;
	}
	
   	
   //	tempPPODtls(newWindow);
   	
   	
   	
}

function tempPPODtls(newWindow)
{
	
	var windowObj = newWindow;
	
	for(var i=0;i<5;i++)
	{
		
		windowObj.document.getElementById("cmbPayinMonth"+(i+1)).options[0].value = window.parent.document.getElementById("cmbPayinMonth"+(i+1)).options[0].value;
		windowObj.document.getElementById("cmbPayinMonth"+(i+1)).options[0].text = window.parent.document.getElementById("cmbPayinMonth"+(i+1)).options[0].text;
		
		windowObj.document.getElementById("cmbPayinYear"+(i+1)).options[0].value = window.parent.document.getElementById("cmbPayinYear"+(i+1)).options[0].value;
		windowObj.document.getElementById("cmbPayinYear"+(i+1)).options[0].text = window.parent.document.getElementById("cmbPayinYear"+(i+1)).options[0].text;
		
		windowObj.document.getElementById("txtInstallmentAmt"+(i+1)).value = window.parent.document.getElementById("txtInstallmentAmt"+(i+1)).value;
		windowObj.document.getElementById("cmbPayinYear"+(i+1)).style.width = "100px";
		windowObj.document.getElementById("cmbPayinMonth"+(i+1)).style.width = "100px";
		//if(window.parent.document.getElementById("cmbPayinYear"+(i+1)).disabled == true)
		{
			//windowObj.document.getElementById("cmbPayinYear"+(i+1)).disabled = true;
			//windowObj.document.getElementById("cmbPayinMonth"+(i+1)).disabled = true;
		}
	} 
	
}
function saveRevisedSixPayArrears()
{
	var txtPnsnrCode = document.getElementById("txtPnsnrCode").value;
	if(document.getElementById("txtTotalAmt").value == "")
	{
		alert('Please insert any amount to proceed ahead.');
		return;
	}
	if(Number(document.getElementById("txtTotalAmt").value) <= 0)
	{
		alert('Total installment amount must be greater than zero.');
		document.getElementById("txtTotalAmt").value = "";
		return;
	}
	
	var txtRemarks="";
	for(var i=0;i<5;i++)
	{
		if(document.getElementById("txtRemarks"+(i+1)).value == "")
		{
			txtRemarks = txtRemarks + "null" + "~";
		}
		else
		{
			txtRemarks = txtRemarks + document.getElementById("txtRemarks"+(i+1)).value + "~";
		}
		
	}
	
	var uri = 'ifms.htm?actionFlag=saveSixPayArrears&txtPnsnrCode='+txtPnsnrCode+"&Revision=Y&txtRemarks="+txtRemarks;
	var url = runForm(0); 
	url = uri + url;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	
}
function revisedHistory()
{
	if(document.getElementById("txtPpoNo").value == "")
	{
		alert('Please enter PPO No. to proceed ahead.');
		return;
	}
	 
	var txtPnsnrCode = document.getElementById("txtPnsnrCode").value;
	url = "ifms.htm?actionFlag=getArrearScreen&txtPnsnrCode="+txtPnsnrCode+"&History=Y";
	var newWindow = null;
   	var height = screen.height - 200;
   	var width = screen.width - 200 ;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=100,left=100";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function getSixPayFPArrears()
{
	if(document.getElementById("cmbInstallments").value == -1)
	{
		alert('Please select any no of installment.');
		return;
	}
	if(document.getElementById("txtPPONo").value == "")
	{
		alert('Please enter PPO No. to proceed ahead.');
		return;
	}
	var cmbInstallments = document.getElementById("cmbInstallments").value;
	var ppoNo = document.getElementById("txtPPONo").value;
	var arrearConfigBy = getArrearConfigByVal();
	//var cmbStateCode = document.getElementById("cmbStateCode");
	/*var HeadCode="";
	for(var i=0;i<cmbStateCode.length;i++)
	{
		if(cmbStateCode.options[i].selected == true)
		{
			HeadCode = HeadCode + cmbStateCode.options[i].value + "~";
		}
	}*/
	//var uri = "ifms.htm?actionFlag=loadSixPayFPArrearConfig&cmbInstallments="+cmbInstallments+"&HeadCode="+HeadCode;
	var uri = "ifms.htm?actionFlag=loadSixPayFPArrearConfig&cmbInstallments="+cmbInstallments+"&ppoNo="+ppoNo+"&arrearConfigBy="+arrearConfigBy;
	self.location.href = uri;
}

/*function saveSixPayFPArrearConfig()
{
	validateSaveSixPayArrearConfig();
	if(document.getElementById("hidSixPayFPArrearsList").value == "")
	{
		alert('Please search first using valid criteria.');
		return;
	}
	var totalSelectedCount= document.getElementById("totalCount").value;
	var tempFlag = 0;
	var Arrear_Id="";
	var flag = 0;
	
	var hidSixPayFPArrearsList = document.getElementById("hidSixPayFPArrearsList").value;
	var tempSixPayFPArrearsList = hidSixPayFPArrearsList.substr(1,hidSixPayFPArrearsList.length-2);
	if(tempSixPayFPArrearsList.length == 0)
	{
		alert('Please search again.');
		return;
	}
		
	
	for(var i=1;i<totalSelectedCount;i++)
	{
		if(document.getElementById("checkPPO"+i).checked == true)
		{
			tempFlag = 1;
			Arrear_Id = Arrear_Id + document.getElementById("checkPPO"+i).value + "~";
		}
	}
	
	if(tempFlag == 1)
	{
		if(document.getElementById("cmbPayinMonth").value == -1 || document.getElementById("cmbPayinYear").value == -1)
		{
			alert('Please select payment month and year.');
			return;
		}
		var cmbPayinMonth = document.getElementById("cmbPayinMonth").value;
		if(Number(cmbPayinMonth)<10)
		{
			cmbPayinMonth = "0"+cmbPayinMonth;
		}
		var cmbPayinYear = document.getElementById("cmbPayinYear").value;
		if(Number(cmbPayinMonth) <= Number(document.getElementById("hidCurrMonth").value) || Number(cmbPayinYear) <= Number(document.getElementById("hidCurrYear").value))
		{
			alert('Selected month or year is less than or equal to current month or year.');
			if(confirm("Do you want to continue?"))
			{
				var uri = "ifms.htm?actionFlag=saveSixPayArrears&Arrear_Id="+Arrear_Id+"&cmbPayinMonth="+cmbPayinMonth+"&cmbPayinYear="+cmbPayinYear;
				var url = runForm(0); 
				url = uri + url;
				var myAjax = new Ajax.Request(uri,
						{
					        method: 'post',
					        asynchronous: false,
					        parameters:url,
					        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
					        onFailure: function(){ alert('Something went wrong...')} 
						});
			}
			else
			{
				return;
			}	
		}
		else
		{
			var uri = "ifms.htm?actionFlag=saveSixPayArrears&Arrear_Id="+Arrear_Id+"&cmbPayinMonth="+cmbPayinMonth+"&cmbPayinYear="+cmbPayinYear;
			var url = runForm(0); 
			url = uri + url;
			var myAjax = new Ajax.Request(uri,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
		}
		
	}
	else
	{
		alert('Please select any PPO.');
	}
	
}*/

function saveSixPayFPArrearConfig()
{
	var lArrSelectedStateDept = new Array();
	var lStrSelectedStateDept = "";
	var lStrPPONo = "";
	if(validateSaveSixPayArrearConfig())
	{
		lStrPPONo = document.getElementById("txtPPONo").value;
		var objDaRateForState = document.getElementById("cmbStateCode");
		for(var cnt = 0;cnt < objDaRateForState.length;cnt++)
		{
			if(objDaRateForState[cnt].selected)
			{
				lArrSelectedStateDept[lArrSelectedStateDept.length] = objDaRateForState[cnt].value;
			}
		}
		lStrSelectedStateDept = lArrSelectedStateDept.join("~");
		var cmbPayinMonth = document.getElementById("cmbPayinMonth").value;
		if(Number(cmbPayinMonth)<10)
		{
			cmbPayinMonth = "0"+cmbPayinMonth;
		}
		var cmbPayinYear = document.getElementById("cmbPayinYear").value;
		var currMonth = Number(document.getElementById("hidCurrMonth").value);
		if(currMonth < 10)
		{
			currMonth = "0"+currMonth;
		}
		var currYear = document.getElementById("hidCurrYear").value;
		var currMonthYear = currYear+currMonth;
		var payInMonthYear = cmbPayinYear+cmbPayinMonth;
		var instlNo = document.getElementById("cmbInstallments").value;
		var arrearId = "";
		if(document.getElementById("arrearId") != null)
		{
			arrearId = document.getElementById("arrearId").value;
		}
		var arrearConfigBy = getArrearConfigByVal();
		if(Number(payInMonthYear) <= Number(currMonthYear))
		{
			alert('Selected month or year is less than or equal to current month or year.');
			if(confirm("Do you want to continue?"))
			{
				var uri = "ifms.htm?actionFlag=saveSixPayArrearConfig";
				var url = "&selctedStateDeptCode="+lStrSelectedStateDept+"&cmbPayinMonth="+cmbPayinMonth+"&cmbPayinYear="+cmbPayinYear+
							"&ppoNo="+lStrPPONo+"&arrearConfigBy="+arrearConfigBy+"&cmbInstallments="+instlNo+
							"&arrearId="+arrearId;
				url = uri + url;
				var myAjax = new Ajax.Request(uri,
						{
					        method: 'post',
					        asynchronous: false,
					        parameters:url,
					        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
					        onFailure: function(){ alert('Something went wrong...');} 
						});
			}
			else
			{
				return;
			}	
		}
		else
		{
			var uri = "ifms.htm?actionFlag=saveSixPayArrearConfig";
			var url = "&selctedStateDeptCode="+lStrSelectedStateDept+"&cmbPayinMonth="+cmbPayinMonth+"&cmbPayinYear="+cmbPayinYear+
						"&ppoNo="+lStrPPONo+"&arrearConfigBy="+arrearConfigBy+"&cmbInstallments="+instlNo+
						"&arrearId="+arrearId;
			url = uri + url;
			var myAjax = new Ajax.Request(uri,
					{
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {saveSixPayArrearsStateChanged(myAjax);},
				        onFailure: function(){ alert('Something went wrong...')} 
					});
		}
	}
}

function checkUncheckAllDisplay(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name == 'checkPPO')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}

function addCashArrearPayment()
{
	ROW_ID_CASHARREAR = document.getElementById("hidCashArrearCount").value;
	ROW_ID_CASHARREAR = Number(ROW_ID_CASHARREAR) +1;
	var table=document.getElementById("tblCashPaymentDtls");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 	  		
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell(3);
   	Cell4.className = "tds";
   	Cell4.align="center";
   	
	Cell1.innerHTML = '<select name="cmbCashPayInMonth" id="cmbCashPayInMonth'+Number(ROW_ID_CASHARREAR)+'" >'+
											'<option value="-1">--Select--</option>' +LISTMONTHS +
										'</select>&nbsp;'+
										'<select name="cmbCashPayInYear" id="cmbCashPayInYear'+Number(ROW_ID_CASHARREAR)+'" >'+
											'<option value="-1">--Select--</option>'+ LISTYEARS +
										'</select>';
   	Cell2.innerHTML ='<input type="text" name="txtCashAmount" id="txtCashAmount'+Number(ROW_ID_CASHARREAR)+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell3.innerHTML = '<label>N</label>';
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblCashPaymentDtls\')" /> '+
   						'<input type="hidden" name="hdnPaidFlag" id="hdnPaidFlag'+Number(ROW_ID_CASHARREAR)+'" value="N" />';
   	document.getElementById("hidCashArrearCount").value = Number(ROW_ID_CASHARREAR); 
}
function showRowCell(element)
{
	var cell, row;    
 
    if (element.parentNode) 
    {
      do
      	cell = element.parentNode;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
      do
      	cell= element.parentElement;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentElement;
    }
    
    return row.rowIndex;
}
function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}
function runForm(formnumber){
	var totalElements= document.forms[formnumber].elements.length;
	  var uri='';
	  for(i=0;i<totalElements;i++)
	  {
		  if(document.forms[formnumber].elements[i].id != "listOfPnsnrCode" && document.forms[formnumber].elements[i].id != "listOfYearDesc" 
			  && document.forms[formnumber].elements[i].id != "listOfMonthDesc")
		  {
			  if(document.forms[formnumber].elements[i].type=="checkbox" ||document.forms[formnumber].elements[i].type=="radio")
			  	{
			  		if(document.forms[formnumber].elements[i].checked==1)
			  		{
						   uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+document.forms[formnumber].elements[i].value;
					}
				}
				else if(document.forms[formnumber].elements[i].type=="select-multiple")
			  	{
			  		var element=document.forms[formnumber].elements[i];
			  		for(j=0;j<element.options.length;j++)
					{
						if(element.options[j].selected)
						{
							uri= uri+'&'+element.name+'='+replaceAllChars(element.options[j].value,'&','$$');
						}
					}
			  	}
				else
				{
					if(document.forms[formnumber].elements[i].value!=null && document.forms[formnumber].elements[i].value!='' && document.forms[formnumber].elements[i].value!='undefined')
					{
						uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+replaceAllChars(document.forms[formnumber].elements[i].value,'&','$$');
					}
					else
					{
						uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+document.forms[formnumber].elements[i].value;
					}
			    }
		  	}
	  	}

	  return uri;
	 }

function showHideArrearOpts(obj)
{
	clearData();
	document.getElementById("erroMsg").innerHTML = "";
	if(obj.value == "P")
	{
		document.getElementById("arrearByState").style.display = "none";
		document.getElementById("arrearByPPO").style.display = "block";
	}
	else
	{
		document.getElementById("arrearByState").style.display = "block";
		document.getElementById("arrearByPPO").style.display = "none";
	}
}
function validateSaveSixPayArrearConfig()
{	
	var instlNo = document.getElementById("cmbInstallments").value;
	if(instlNo == "" || instlNo == "-1")
	{
		alert("Please select any installment number.");
		return false;
	}
	var arrearConfigBy = getArrearConfigByVal();
	if(arrearConfigBy == "P")
	{
		document.getElementById("cmbStateCode").selectedIndex = -1;
		if(document.getElementById("txtPPONo").value == "")
		{
			alert("Please enter any valid ppo no.");
			return false;
		}
		if(document.getElementById("arrearId") == null)
		{
			alert("Please search for a valid pension case first.");
			return false;
		}
	}
	else
	{
		if(document.getElementById("cmbStateCode").selectedIndex == -1 || document.getElementById("cmbStateCode").value == "")
		{
			alert("Please select any state/dept for da rate.");
			return false;
		}
	}
	if(document.getElementById("cmbPayinMonth").value == -1 || document.getElementById("cmbPayinYear").value == -1)
	{
		alert('Please select payment month and year.');
		return;
	}
	return true;
}
function getArrearConfigByVal()
{
	var lArrArrearBy = document.getElementsByName("sixPayArrearBy");
	var arrearConfigBy = "";
	for(var cnt=0;cnt < lArrArrearBy.length ; cnt++)
	{
		if(lArrArrearBy[cnt].checked)
		{
			arrearConfigBy = lArrArrearBy[cnt].value;
		}
	}
	return arrearConfigBy;
}
function clearData()
{
	document.getElementById("txtPPONo").value= "";
	document.getElementById("cmbStateCode").value = "-1";
	document.getElementById("cmbStateCode").selectedIndex = -1;
}