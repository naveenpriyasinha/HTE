var win; 
onerror=handleErr
function handleErr()
{
//Handle the error here
window.status="Done";
return true;
}
function amountFormat(lThis)
{
	if(window.event.keyCode == 46)
	{
		var lStr = new String(lThis.value);
		lStr = lStr.trim();
		if(lStr.indexOf('.') != -1)
		{
			window.event.keyCode = 0;
		}
	}
	else if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
	{
		window.event.keyCode = 0;
	}
}
function verifySavedBill()
{
	var newWindow;
   	var height =300;
   	var width = 300;
	var urlstring = "ifms.htm?actionFlag=signVerification";
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "Verification", urlstyle);
}

function digitFormat()
{			
	if(!((window.event.keyCode > 47 && window.event.keyCode < 58 || window.event.keyCode == 47 || window.event.keyCode == 58) ))
	{
		window.event.keyCode = 0;
	}			
}


// Functions For SavedBills.jsp Added By Sagar....... Starts......

function checkUncheckAll(theElement) 
{
	var theForm = theElement.form;
	
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
		{
			theForm[z].checked = theElement.checked;
  		}
    }
}
		     
function showSavedBill(BillNo, BillStatus, BillType)
{
	var newWindow;
   	var height = screen.height - 100;
   	var width = screen.width;
   	//added by ravysh
   	
   	var urlstring = "ifms.htm?actionFlag=getBillData&billNo=" + BillNo + "&billStatus=" + BillStatus+"&BillType="+BillType;

   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
}
	
	function displ()	// Added by Keyur To Forward the Bill
	{		
		document.forms[0].action="ifms.htm?actionFlag=forwardOnlineBill&action=frwrd";
		document.forms[0].submit();
		window.setTimeout("closeBill()",400);	
	}
	
	function discardBill()
	{   
		var billNo= checkSelBill();
	
	
		if(billNo != "-1")
		{
	   		
	   		xmlHttp=GetXmlHttpObject();
			
			if (xmlHttp==null)
			{
				alert ("Your browser does not support AJAX!");
				return;
			} 
			else
			{	
				document.forms[0].btnDiscard.disabled=true;		
			var url = run(); 
			var uri = "ifms.htm?actionFlag=checkPaybillStatus&trnBillId="+billNo;
			url = uri + url;           
			
			
			xmlHttp.onreadystatechange=checkPaybillStatus;
			xmlHttp.open("POST",uri,false);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(url);
			}
	   		
	   		
		}
	}
	
	
	function checkPaybillStatus()
	{
		if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200)
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var paybillMapping = XMLDoc.getElementsByTagName('paybillMapping');
					
					var status=paybillMapping[0].childNodes[0].text;    
       				
       				
       				var billNo= checkSelBill();
       				if(status == -1)
       				{
       					alert("There is some problem related to inner paybill");
       				}
       				else if(status == 0)
       				{
       					document.savedBillsForm.hidBillNo.value = billNo;
	   					document.savedBillsForm.action = "ifms.htm?actionFlag=discardBill";
	   					document.savedBillsForm.submit();
       				}
       				else if(status == 1)
       				{
       					alert("Paybill has been approved. You can not discard Paybill now.");
       				}
       				else if(status == 2)
       				{
       					alert("Paybill has been Rejected. You can not discard Paybill now.");
       				}
       				
					
				}
			}
	}
	
	function checkSelBill()
	{
		var billNo = "";
		var indx = 0;
		
    	for(i=0;i < document.savedBillsForm.elements.length;i++)
		{
			if(document.savedBillsForm.elements[i].type=="checkbox" && document.savedBillsForm.elements[i].name != 'chkSelect')
			{
				if(document.savedBillsForm.elements[i].checked)
				{
					                                                                   
					billNo=document.savedBillsForm.elements[i].value + "," + billNo;  //prasenjit added this for multiple bill discard as we get billno as comma sep.value
					indx++;
				} 
			}
		}
    	if(indx==0)
    	{
   			alert(SVBILL_SELECTBILL);
   		 	return -1;
   		}
   		/*else if(indx > 1)
   		{
   			alert(SVBILL_ONEBILLFORWARDING);
   		 	return -1;
   		}*/
   		
   		return billNo;
	}
	
	function forwardBill()	// Added by Keyur To Forward the Bill
	{
		var indx = 0;
		var billNo= checkSelBill();
		
		if(billNo != "-1")
		{		
			for(i=0;i < document.savedBillsForm.elements.length;i++)
			{
				if(document.savedBillsForm.elements[i].type=="checkbox" && document.savedBillsForm.elements[i].name != 'chkSelect')
				{
					indx ++;	// Getting selcted Index
					
					if(document.savedBillsForm.elements[i].checked)
					{
						break;
					} 
				}
			}
		}
		var checkedBillStatus = document.getElementsByName('SelBillStatus');

		document.getElementById("CurrBillStatus").value = checkedBillStatus[indx-1].value;
	
		
		if(billNo != "-1")
		{
	   		document.forms[0].hidBillNo.value = billNo;
   		
			var url = "ifms.htm?actionFlag=getHyrUsers&BillNo=" + billNo;
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500");
		}
	}
	
	function sendtoTrsryOffice()	// Added by Keyur to send the approved bills to TO
	{
		var billNo = checkSelBill();
		var indx = 0;
				
		// Added by Sagar For Bill Approved Checking Start...
		if(billNo != "-1")
		{		
			for(i=0;i < document.savedBillsForm.elements.length;i++)
			{
				if(document.savedBillsForm.elements[i].type=="checkbox" && document.savedBillsForm.elements[i].name != 'chkSelect')
				{
					indx ++;
					
					if(document.savedBillsForm.elements[i].checked)
					{
						break;
					} 
				}
			}
		}
		// Added by Sagar For Bill Approved Checking End...
		
		var checkedBillStatus = document.getElementsByName('SelBillStatus');
	
		if(checkedBillStatus[indx-1].value != 'BAPRVD_DDO')
		{
			alert(SVBILL_BILLNOTAPPROVED);
			billNo = "-1";
		}
		
		if(billNo != "-1")
		{
			document.forms[0].hidBillNo.value = billNo;
			var url = "ifms.htm?actionFlag=sendtoTrsryOffice&BillNo=" + billNo;
			window.open(url,"TrsryList","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400");
		}
	}
		
	
	
	function showDtPic()/// Added By Sagar For Searching
	{
		if(document.savedBillsForm.cmbSearch.value=="BR.billDate")
		{
			document.getElementById("dtpicker").style.display="inline";
		}
		else
		{
			document.getElementById("dtpicker").style.display="none";
		}
		document.getElementById("txtSearch").value = "";
	}
	
	function checkCategorySelection() // Added By Sagar For checking Search Category..
	{
		if(document.getElementById("id_cmbSearch").value == "0")
		{
			document.getElementById("txtSearch").value = "";
			alert(SVBILL_SRCHCRITERIANOTSELECTED);			
		}
	}
	
	function createNewBill()
	    {
	    	if(checkSelBill() == -1)
	    		return;
	    	var flag = false;
	    	var rejBillNo;
	    	var rejBillCntrlNoArr = document.getElementsByName("billCntrlNo");
	    	var newBillNoArr = document.getElementsByName("newBillNo");
	    	var newBillCntrlNoArr = document.getElementsByName("newBillCntrlNo");
	    	var newBillStatusArr = document.getElementsByName("newBillStatus");
	    	var rejBillCntrlNo;
	    	var newBillNo;
	    	var newBillCntrlNo;
	    	var newBillStatus;
	    	var i;
	    	var element = document.getElementsByName("chkbox");
	    	
	    	for(i=0; i < element.length; ++i)
	    	{
	    		if(element[i].checked)
	    		{
	    			flag = true;
	    			rejBillNo = element[i].value;
	    			break;
	    		}
	    	}
	    		
    		rejBillCntrlNo = rejBillCntrlNoArr[i].value;
    		newBillNo = newBillNoArr[i].value;
    		newBillCntrlNo = newBillCntrlNoArr[i].value;
    		newBillStatus = newBillStatusArr[i].value;
    		var newBillDscrd = 0;//new bill is not discarded
    		
    		if(newBillStatus == 'BDSCRD_DDO')
    		{    			
    			newBillDscrd = 1;// new bill is discarded
    		}
    		
	    	if(newBillDscrd == 0 && newBillNo != null && newBillNo.trim() != "")
	    	{		
	    		alert(SVBILL_RJCTDBILLCRTD + newBillCntrlNo);
	    		return;
	    	}
	    		
	    	if(flag == true)
	    	{
	    		createNewFromRejected(rejBillNo, rejBillCntrlNo,'BCRTD');
	    	}
	    	else
	    	{
	    		alert(SVBILL_SELECTBILL);
	    	}
	    }		
		
	function createNewFromRejected(BillNo, BillCntrlNo, BillStatus)
	{
		var newWindow;
	   	var height = screen.height - 100;
	   	var width = screen.width;
	   	var isNewFromRejected = "Y";
	   	var urlstring = "ifms.htm?actionFlag=getBillData&billNo=" + BillNo + "&billStatus=" + BillStatus + '&isNewFromRejected=' + isNewFromRejected;
	   	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	   	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
	}		

// Functions For SavedBills.jsp Added By Sagar....... END......


//Added By Amit for Removing JS Code from JSPs.

// Functions For budDtls.jsp....... Starts......

		Math.format = function(number)
		{
			var lStr = new String(Math.round(number * 100) / 100);
			
			if(lStr.indexOf('.') == -1)
			{
				lStr += ".00"; 
			}
			else if((lStr.indexOf('.') + 2) == lStr.length)
			{
				lStr += "0";
			} 
			
			return lStr;
		}
		
		var majorNames = new Array(7);
		majorNames[0] = "";
    	majorNames[1] = " Thousand";
    	majorNames[2] = " Lac";
    	majorNames[3] = " Crore";
    	majorNames[4] = " Trillion";
    	majorNames[5] = " Quadrillion";
		majorNames[6] = " Quintillion";
		
		var tensNames = new Array(10);
		tensNames[0] = "";
    	tensNames[1] = " Ten";
    	tensNames[2] = " Twenty";
    	tensNames[3] = " Thirty";
    	tensNames[4] = " Fourty";
    	tensNames[5] = " Fifty";
		tensNames[6] = " Sixty";
		tensNames[7] = " Seventy";
		tensNames[8] = " Eighty";
		tensNames[9] = " Ninety";
		
		var numNames = new Array(10);
		numNames[0] = "";
    	numNames[1] = " One";
    	numNames[2] = " Two";
    	numNames[3] = " Three";
    	numNames[4] = " Four";
    	numNames[5] = " Five";
		numNames[6] = " Six";
		numNames[7] = " Seven";
		numNames[8] = " Eight";
		numNames[9] = " Nine";
		numNames[10] = " Ten";
		numNames[11] = " Eleven";
		numNames[12] = " Twelve";
		numNames[13] = " Thirteen";
		numNames[14] = " Fourteen";
		numNames[15] = " Fifteen";
		numNames[16] = " Sixteen";
		numNames[17] = " Seventeen";
		numNames[18] = " Eighteen";		
		numNames[19] = " Nineteen";
				
		function convertLessThanOneThousand(number) 
		{
			var soFar = new String("");

    		if (parseInt(number % 100) < 20)
    		{
        		soFar = numNames[parseInt(number % 100)];
        		number = parseInt(number / 100);
       		}
    		else 
    		{
        		soFar = numNames[parseInt(number % 10)];
        		number = parseInt(number / 10);

        		soFar = tensNames[parseInt(number % 10)] + soFar;
        		number = parseInt(number / 10);
       		}
    		if (number == 0) 
    		{
    			return soFar;
    		}
    		
    		return numNames[number] + " hundred" + soFar;	    	
		}
		function convert(number)
		{
			var prefix = new String("");
			var soFar = new String("");
    		var place = 0;

			if (number == 0) 
			{ 
				return "zero"; 
			}
    		if (number < 0) 
    		{
        		number = -number;
        		prefix = "negative ";
      		}


      		var n = parseInt(number % 1000);
    		if(n != 0)
    		{
       			var s = new String(convertLessThanOneThousand(n));
       			soFar = s + majorNames[place] + soFar;
       		}

   			place++;
   			number = parseInt(number / 1000);

    		while(number > 0) 
    		{
      			var n = parseInt(number % 100);
      			if(n != 0)
      			{
         			var s = new String(convertLessThanOneThousand(n));
         			soFar = s + majorNames[place] + soFar;
         		}

      			place++;
      			number = parseInt(number / 100);
      		} 

    		return prefix + soFar.trim();	    	
		}
		function getAmountInWords(number) 
		{
		 	var s = new String("");
	 
	 		if(isNaN(number))
	 		{
	 			return s;
	 		}
	 		
			if(number  > 10000000 )
			{
				s = convert(number / 10000000) + " Crore"; 
			
				if (number % 10000000 != 0)
				{
					s += convert(number % 10000000);  
				}
			}
			else
			{
				s = convert(number); 
			}
				
			return s;
		}
		function NumberFormet()
		{
			if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
			{
				window.event.keyCode = 0;
			}				
		}
		function amountFormat(lThis)
		{
			if(window.event.keyCode == 46)
			{
				var lStr = new String(lThis.value);
				lStr = lStr.trim();
				if(lStr.indexOf('.') != -1)
				{
					window.event.keyCode = 0;
				}
			}
			else if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
			{
				window.event.keyCode = 0;
			}
		}
		function setValidAmountFormat(lThis)
		{
			var lStr = new String(lThis.value);
			lStr = lStr.trim();
			
			if(lStr == "")
			{
				lThis.value = "0.00";
			}
			else if(lStr.indexOf('.') == 0 && lStr.length == 1)
			{
				lThis.value = "0.00";
			}
			else if(lStr.indexOf('.') == 0 && lStr.length == 2)
			{
				lThis.value = "0" + lStr + "0";
			}
			else if(lStr.indexOf('.') == -1)
			{
				lThis.value = lStr + ".00";
			}
			else if(lStr.indexOf('.') != -1)
			{
				lThis.value = Math.format(parseFloat(lStr));
			}
		}
		function getNumber(CurrentLength, MaxLength)
		{
			var lStrReturn = "";
			for(var lIntCount = CurrentLength; lIntCount < MaxLength; ++lIntCount)
			{
				lStrReturn += "0";
			}
			
			return lStrReturn;
		}
		function getValidHeadCode(lThis, MaxLength)
		{
			var lStr = new String(lThis.value);
			lStr = lStr.trim();
			lStr = getNumber(lStr.length, MaxLength) + lThis.value;
			lThis.value = lStr;
		}
		function getHdChrgble()
		{
			var lStr = new String();
			//alert("1"+lStr);
			lStr = document.getElementById("MjHd").value;
			//alert("2"+lStr);
			lStr += document.getElementById("SubMjHd").value;
			//alert("3"+lStr);
			lStr += document.getElementById("MnrHd").value;
			//alert("4"+lStr);
			lStr += document.getElementById("SubHd").value;
			//alert("5"+lStr);
			lStr += document.getElementById("DtlHd").value;		
			
			//alert(lStr);
			
			document.getElementById("HdChrgble").value = lStr;			
		}
		
	    function setText(lThis)
	    {
	    	document.getElementById("txtBudDesc").value = lookupArray[lThis.selectedIndex];
		}
		function showExempt(lThis)
		{
			if(lThis.value == 'Y')
			{
				document.getElementById("tblExempted").style.display = "block";
			}
			else if(lThis.value == 'N')
			{
				document.getElementById("tblExempted").style.display = "none";
			}
		}
		function cleanHeadDtls(lThis)
		{
			if(document.getElementById("Demand") == lThis)
			{
				document.getElementById("MjHd").value = "";
				document.getElementById("SubMjHd").value = "";
				document.getElementById("MnrHd").value = "";
				document.getElementById("SubHd").value = "";
			}
			else if(document.getElementById("MjHd") == lThis)
			{
				document.getElementById("SubMjHd").value = "";
				document.getElementById("MnrHd").value = "";
				document.getElementById("SubHd").value = "";
			}
			else if(document.getElementById("SubMjHd") == lThis)
			{
				document.getElementById("MnrHd").value = "";
				document.getElementById("SubHd").value = "";
			}
			else if(document.getElementById("MnrHd") == lThis)
			{
				document.getElementById("SubHd").value = "";
			}
			
			document.getElementById("DtlHd").value = "";
			document.getElementById("HdChrgble").value = "";
		}
	    function digiSignBudDtlsData()
	    {
    	   	generateDigiSign(document.forms[0].cmbClsOfExp.value,"digi2_0_CLS_EXP");
			generateDigiSign(document.forms[0].cmbFund.value,"digi2_0_FUND");
			generateDigiSign(document.forms[0].cmbBudType.value,"digi2_0_BUD_TYPE");
			generateDigiSign(document.forms[0].txtSchemeNo.value,"digi2_0_SCHEME_NO");
			generateDigiSign(document.forms[0].txtMjrHd.value,"digi2_0_BUD_MJR_HD");
			generateDigiSign(document.forms[0].txtMjrHd.value,"digi3_0_BUDMJR_HD");
			generateDigiSign(document.forms[0].txtSbMjrHd.value,"digi2_0_BUD_SUBMJR_HD");
			generateDigiSign(document.forms[0].txtMnrHd.value,"digi2_0_BUD_MIN_HD");
			generateDigiSign(document.forms[0].txtSbHd.value,"digi2_0_BUD_SUB_HD");
			generateDigiSign(document.forms[0].txtDtldHd.value,"digi2_0_BUD_DTL_HD");
			generateDigiSign(document.forms[0].txtHdChrgble.value,"digi2_0_HEAD_CHRG");
			generateDigiSign(document.forms[0].txtDmd.value,"digi2_0_DMND_NO");
			generateDigiSign(document.forms[0].txtDmd.value,"digi3_0_DEMAND_CODE");			
			generateDigiSign(document.forms[0].cmbBillType.value,"digi3_0_TC_BILL");
		}
		function validateGrantDtls()
		{
			if(document.getElementById("cmbBudType").value == "-1")
			{
				alert(BUD_CMBBUDTYPE);
				return false;
			}
			if(!IsNull("Demand", BUD_TXTDMNDNO))
			{
				return false;
			}
			if(!IsNull("MjHd", BUD_TXTMAJHD))
			{
				return false;
			}
			if(!IsNull("SubMjHd",BUD_TXTSUBMAJHD))
			{
				return false;
			}
			if(!IsNull("MnrHd", BUD_TXTMINHD))
			{
				return false;
			}
			if(!IsNull("SubHd", BUD_TXTSUBHD))
			{
				return false;
			}
			if(!IsNull("DtlHd", BUD_TXTDTLHD))
			{
				return false;
			}
			
			return true;
		}
		function isValidateBudDtls()
		{
			if(document.getElementById("cmbClsOfExp").value == "-1")
			{
				alert(BUD_CMBCLSEXP);
				return false;
			}
			if(document.getElementById("cmbFund").value == "-1")
			{
				alert(BUD_CMBFUND);
				return false;
			}
			if(!IsNull("txtSchemeNo", BUD_TXTSCHNO))
			{
				return false;
			}
			if(!validateGrantDtls())
			{
				return false;
			}
			if(!IsNull("HdChrgble", BUD_TXTHDCHRG))
			{
				return false;
			}
			if(document.getElementById("cmbBillType").value == "-1")
			{
				alert(BUD_CMBCATEGORY);
				return false;
			}
			var element = document.getElementsByName("radExempted");
			if(element != null)
			{
				if(element[0].checked)
				{
					if(document.getElementById("cmbBillCode").value == "" || document.getElementById("cmbBillCode").value == "-1")
					{
						alert(BUD_CMBBILLCODE);
						return false;
					}
			    }
			}
		
		
						// added by jay - Validations of Challan - start
						
			if(document.getElementById("cmbBillType").value == "TC" )			
			{
				// alert("Inside TC");
				
				if(validateChallanAmount())
				{
					// alert("validateChallanAmount returns true");
					if(document.getElementById("id_txtRcptMjrHdCode1") != null)
					{
						// alert("Inside id_txtRcptMjrHdCode1");
						var lIntNetAmountforCh2 = (parseInt(document.getElementById("txtExpenditure").value) 
							- parseInt(document.getElementById("txtRecovery").value));
							
						alert(lIntNetAmountforCh2);
						alert(document.getElementById("id_txtRcptAmt1").value);
												
						
						if(parseInt(document.getElementById("id_txtRcptAmt1").value) != lIntNetAmountforCh2)
						{
							alert("Deduction Amount must be same as Challan Amount");
							return false;
						}
						else
						{
							return true;
						}
					}
				}else{
					return false;
				}
			}
			
		  return true;			
		  			
		}
		
		
		function validateChallanAmount()
		{			
			if(checkForNull())
			{		
				var lIntNetAmountforCh1 = (parseInt(document.getElementById("txtExpenditure").value) 
							- parseInt(document.getElementById("txtRecovery").value));

				var challanAmount = Math.round(parseFloat(document.getElementById("chChlnValue").value));
							
				// alert("Inside validateChallanAmount()");
				if(lIntNetAmountforCh1 != challanAmount)						
				{
					alert("Challan Value should be same as Gross Amount");				
					return false;
				}			
				else
				{	
					return true;
				}
			}
		}	
		
		
		
		function setNetAmtFrmChallan()
		{
			var challanAmount = Math.round(parseFloat(document.getElementById("chChlnValue").value));
			document.getElementById("NetTotal").innerHTML = "<b> : " + challanAmount + " </b>";
			
			var GrossTotal = parseInt(document.getElementById("txtExpenditure").value);
			var Recovery = parseInt(document.getElementById("txtRecovery").value);
			var challanAmount = parseInt(document.getElementById("chChlnValue").value);
			
			document.getElementById("NetTotal").innerHTML = "<b> : " + (GrossTotal - (Recovery + challanAmount)) + "  </b>";
			document.getElementById("AmtInRs").innerHTML = "<b> : " + getAmountInWords((GrossTotal - (Recovery + challanAmount))) + " </b>";
			document.getElementById("Appropriation").innerHTML = "<b> : " + document.getElementById("hidGrantAmt").value + " </b>";
			document.getElementById("DDOExpAmt").innerHTML = "<b> : " + parseInt(glDDOExpAmt) + " </b>";
			document.getElementById("Balance").innerHTML = "<b> : " + (parseInt(document.getElementById("hidGrantAmt").value) - parseInt(glDDOExpAmt))+ " </b>";
			
			
		
		}
			
			
						// added by jay - Validations of Challan - end
		
// Functions For budDtls.jsp....... Ends......		

// Functions For empDtls.jsp....... Starts..

		function setIncumbentName()
		{
			var element = document.getElementsByName("txtIncumbentOne");
			if(element != null)
			{
				for(var i = 0; i < element.length; ++i)
				{
					element[i].value = document.getElementById("txtEmpName").value;
				}
			}
			element = document.getElementsByName("txtIncumbentTwo");
			if(element != null)
			{
				for(var i = 0; i < element.length; ++i)
				{
					element[i].value = document.getElementById("txtEmpName").value;
				}
			}
		}
		function setEstablishment()
		{
			var element = document.getElementsByName("txtEstablishmentTwo");
			if(element != null)
			{
				for(var i = 0; i < element.length; ++i)
				{
					element[i].value = document.getElementById("txtEmpEstblsmnt").value;
				}
			}
		}
		function digiSignEmpData()
		{
			generateDigiSign(document.forms[0].txtEmpName.value,"digi8_0_EMP_NAME");
			generateDigiSign(document.forms[0].txtEmpDsg.value,"digi8_0_EMP_DESGN");
			generateDigiSign(document.forms[0].txtEmpEstblsmnt.value,"digi8_0_DEPT_NAME");
			generateDigiSign(document.forms[0].cmbForMonth.value,"digi8_0_MONTH_CODE");
		}
		
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
			if(document.getElementById("cmbForMonth").value == "-1")
			{
				alert(EMP_CMBMONTH);
				return false;
			}
			/*
			if(document.getElementById("cmbEmpType").value == "-1")
			{
				alert(EMP_CMBEMPTYPE);
				return false;
			} 
			*/
			
			return true;
		}
		
// Functions For empDtls.jsp....... Ends......

// Functions For ddoHeadDtlsPopup.jsp....... Starts......

		function setValue(val)
		{
			val = new String("RedSelect" + val);
			//alert("hello from setvale "+val);
			var SelectedValue = document.getElementById(val).value;
			//alert("hello SelectedValue from setvale "+SelectedValue);
			var ArrHead = SelectedValue.split("~");
			//alert("hello ArrHead from setvale "+ArrHead);
			window.opener.document.getElementById("txtSchemeNo").value = ArrHead[0];
			window.opener.document.getElementById("Demand").value = ArrHead[1];
			window.opener.document.getElementById("MjHd").value = ArrHead[2];
			window.opener.document.getElementById("SubMjHd").value = ArrHead[3];
			window.opener.document.getElementById("MnrHd").value = ArrHead[4];
			window.opener.document.getElementById("SubHd").value = ArrHead[5];
			window.opener.document.getElementById("DtlHd").value = "";
			window.opener.document.getElementById("HdChrgble").value = "";
		}
		function window_close()
		{
			window.close();
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
				amountExp=parseFloat(amountExp)+parseFloat(array[i]['amount'])
				document.forms[0].txtExpenditure.value=amountExp;
			}
			if(array[i]['expRcpRev']=="Recovery")
			{
				amountRec=parseFloat(amountRec)+parseFloat(array[i]['amount'])
				document.forms[0].txtRecovery.value=amountRec;
			}
			if(array[i]['expRcpRev']=="Receipt")
			{
				if(array[i]['category']=='A')
				{
					amountA=parseFloat(amountA)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionA.value=amountA
				}
				if(array[i]['category']=='B')
				{
					amountB=parseFloat(amountB)+parseFloat(array[i]['amount'])
					document.forms[0].DeductionB.value=amountB
				}
			}
		}
		
		ExpAmt = parseInt(document.getElementById("txtExpenditure").value);
		RecAmt = parseInt(document.getElementById("txtRecovery").value);
		DedA = parseInt(document.getElementById("DeductionA").value);
		DedB = parseInt(document.getElementById("DeductionB").value);
		
		var TableCal = document.getElementById("GrossTotal");
    	if(TableCal != null)
    	{
       		TableCal.innerHTML = "<b> : " + parseInt(ExpAmt) + " </b> ";
    	}
    	TableCal = document.getElementById("Recovery");
    	if(TableCal != null)
    	{
    		TableCal.innerHTML = "<b> : " + parseInt(RecAmt) + " </b> ";
    	}
    	TableCal = document.getElementById("NetTotal");
    	if(TableCal != null)
    	{
	   		NetTotal = parseInt(ExpAmt - RecAmt) - parseInt(DedA + DedB);
    		document.getElementById("hidNetTotal").value = NetTotal;
    		TableCal.innerHTML = "<b> : " + parseInt(NetTotal) + " </b> ";
    	}
    	TableCal = document.getElementById("AmtInRs");
    	if(TableCal != null)
    	{
    		TableCal.innerHTML = "<br /> <b> : " + getAmountInWords(NetTotal) + " </b> ";
    	}
    	TableCal = document.getElementById("DDOExpAmt");
		if(TableCal != null)
		{
			TableCal.innerHTML = "<b> : " + parseInt(glDDOExpAmt + NetTotal) + " </b> ";
		}
		TableCal = document.getElementById("Balance");
		if(TableCal != null)
		{
			var lAmount = parseInt(document.getElementById("hidGrantAmt").value);
			lAmount = parseInt(lAmount - parseInt(glDDOExpAmt + NetTotal));
			if(!isNaN(lAmount))
			{
				TableCal.innerHTML = "<b> : " + parseInt(lAmount) + " </b> ";
			}
		}
	}	
	function computeSumAftDelete()
	{	
		var amountA = 0.0, amountB = 0.0, amountO = 0.0, amountRec = 0.0, amountExp = 0.0;
		
		for(i = 0; i < array.length; i++)
		{
			if(array[i]['expRcpRev']=="Expenditure")
			{
				amountExp = parseFloat(amountExp) + parseFloat(array[i]['amount']);
				document.getElementsByName("txtExpenditure").value = Math.format(amountExp);
			}
			if(array[i]['expRcpRev']=="Recovery")
			{
				amountRec = parseFloat(amountRec) + parseFloat(array[i]['amount']);
				document.getElementsByName("txtRecovery").value = Math.format(amountRec);
			}
			if(array[i]['expRcpRev']=="Receipt")
			{
				if(array[i]['category']=='A')
				{
					amountA = parseFloat(amountA) + parseFloat(array[i]['amount']);
					document.getElementsByName("DeductionA").value = Math.format(amountA);
				}
				if(array[i]['category']=='B')
				{
					amountB = parseFloat(amountB) + parseFloat(array[i]['amount']);
					document.getElementsByName("DeductionB").value = Math.format(amountB);
				}
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
		//document.forms[0].txtNetAmount.value=parseFloat(amountO)-parseFloat(amountA)-parseFloat(amountB)				
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
	function deleteRow(vRow,vCounter)
	{
		array[vCounter]['amount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('ExpDetPostTbl').deleteRow(i)
  		edpRowNo = edpRowNo-1;
  		shuffleEdpRows();
  	}
	function deleteRecRow(vRow,vCounter)
	{
		array[vCounter]['mount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('recDetPostTbl').deleteRow(i);
  		recEdpRowNo = recEdpRowNo-1;
  		shuffleEdpRows();
  	}
	function deleteRcptRow(vRow,vCounter)
	{
		array[vCounter]['amount']=0;
		array[vCounter]['edpCode']="";
		computeSumAftDelete();
		var i=vRow.parentNode.parentNode.rowIndex
  		document.getElementById('rcptDetPostTbl').deleteRow(i)
  	}
	
	function addEdpRow()
	{
		  var tbody = document.getElementById('ExpDetPostTbl').getElementsByTagName('tbody')[0]; 
		
		  var dExpCounter = document.getElementById("expEdpCounter").value;
	  	  var dRecCounter = document.getElementById("recEdpCounter").value;
		  edpRowNo++;
		  rowCount = Number(dExpCounter) + Number(dExpCounter);
		  var rowType1 = "EXP"
		  var row = document.createElement('TR'); 
		  row.className = "odd";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  cell2.id = "id_txtEdpDesc" + edpRowNo;
		  cell2.className = "Label";
		  var cell3 = document.createElement('TD'); 
		  cell3.id = "id_txtBudCode" + edpRowNo;
		  cell3.className = "Label";
		  var cell4 = document.createElement('TD'); 
		  			
		  cell1.innerHTML = "<input type=\"hidden\" name=\"exprows\" value=\""+edpRowNo+"\">"+"<input type=\"text\" size=\"6\" name=\"txtEdpCode"+edpRowNo+"\" id=\"id_txtEdpCode"+edpRowNo+"\"  onblur=getExpEdpDtls(this,"+edpRowNo+","+nCounter+")>"+"<input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_CODE\"><input type=\"hidden\" size=\"6\" name=\"txtDedType"+edpRowNo+"\" id=\"id_txtDedType"+edpRowNo+"\" value=\"O\"><input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_CATEGORY\"><input type=\"hidden\" size=\"6\" name=\"txtAddDed"+edpRowNo+"\" id=\"id_txtAddDed"+edpRowNo+"\"><input type=\"hidden\" name=\"digi1_"+rowCount+"_ADD_DED_FLAG\"> ";
		  cell2.innerHTML = ""; 
		  cell3.innerHTML = "";
		  cell4.innerHTML = "<input type=\"text\" size=\"9\" value=\"0\" name=\"txtAmt"+edpRowNo+"\" id=\"id_txtAmt"+edpRowNo+"\" onkeypress=\"NumberFormet()\" onblur=\"computeSumExtSum(this,"+edpRowNo+",'EXP',"+nCounter+")\" /> <input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_AMT\"><img src=\"images/CalendarImages/DeleteIcon.gif\" onclick=\"deleteRow(this," + nCounter + ")\" />";
			  	
		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		  row.appendChild(cell3); 
	      row.appendChild(cell4); 
	     
	      tbody.appendChild(row); 
		 		  
		  var subArray = new Array();
	      subArray['expRcpRev'] = 'Expenditure';						
		  subArray['amount'] = 0;
		  array[nCounter] = subArray;
		  nCounter = nCounter + 1;
		  rowCount = rowCount + 1;	
		  document.getElementById("id_txtEdpCode"+edpRowNo).focus();	 
	}
	function shuffleEdpRows()
	{
		  var dExpCounter = document.getElementById("expEdpCounter").value;
		  var j = parseInt(dExpCounter)+parseInt(edpRowNo);
		
		  var k = 0; 	
		  var h=1;
		 
		  for(var i=1; i<=j; i++)
		  {
		  	var cell1 = document.getElementById('ExpDetPostTbl').cells(((i*4)+0));
		  	var cell2 = document.getElementById('ExpDetPostTbl').cells(((i*4)+3));
            
            if(cell2.children[0].value > 0)
            {
            
	            cell1.children[2].name = "digi1_"+k+"_EDP_CODE";
	            cell1.children[4].name = "digi1_"+k+"_EDP_CATEGORY";
	            cell1.children[6].name = "digi1_"+k+"_ADD_DED_FLAG";
	            cell2.children[1].name = "digi1_"+k+"_EDP_AMT";
	            
	            cell1.children[2].id = "digi1_"+k+"_EDP_CODE";
	            cell1.children[4].id = "digi1_"+k+"_EDP_CATEGORY";
	            cell1.children[6].id = "digi1_"+k+"_ADD_DED_FLAG";
	            cell2.children[1].id = "digi1_"+k+"_EDP_AMT";
	            k = k + 1;
            }
            else
            {
            	cell1.children[2].name = "removed";
				cell1.children[4].name = "removed";
	            cell1.children[6].name = "removed";
	            cell2.children[1].name = "removed";
				
				cell1.children[2].id = "removed";
				cell1.children[4].id = "removed";
	            cell1.children[6].id = "removed";
	            cell2.children[1].id = "removed";
            }
         }
		 
		 shuffleRecRows(k);		    
	}

	function addRecEdpRow()
	{
		  var tbody = document.getElementById('recDetPostTbl').getElementsByTagName('tbody')[0]; 
		  var dExpCounter = document.getElementById("expEdpCounter").value;
	  	  var dRecCounter = document.getElementById("recEdpCounter").value;	
	  	
	  	  recEdpRowNo++;
	  	  
		  var row = document.createElement('TR'); 
		  row.className="odd";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  cell2.id = "id_txtRecEdpDesc" + recEdpRowNo;
		  cell2.className = "Label";
		  var cell3 = document.createElement('TD'); 
		  cell3.id = "id_txtRecBudCode" + recEdpRowNo;
		  cell3.className = "Label";
		  var cell4 = document.createElement('TD'); 
		
		  cell1.innerHTML = "<input type=\"hidden\" name=\"recRows\" value=\""+recEdpRowNo+"\"><input type=\"text\" size=\"6\" name=\"txtRecEdpCode"+recEdpRowNo+"\" id=\"id_txtRecEdpCode"+recEdpRowNo+"\"  onblur=getRecEdpDtls(this,"+recEdpRowNo+","+nCounter+")><input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_CODE\"><input type=\"hidden\" size=\"6\" name=\"txtRecDedType"+recEdpRowNo+"\" id=\"id_txtRecDedType"+recEdpRowNo+"\" value=\"O\"><input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_CATEGORY\"><input type=\"hidden\" size=\"6\" name=\"txtRecAddDed"+recEdpRowNo+"\" id=\"id_txtRecAddDed"+recEdpRowNo+"\"><input type=\"hidden\" name=\"digi1_"+rowCount+"_ADD_DED_FLAG\"> ";
		  cell2.innerHTML = "";
		  cell3.innerHTML = "";
		  cell4.innerHTML = "<input type=\"text\" size=\"9\" value=\"0\" name=\"txtRecAmt"+recEdpRowNo+"\" id=\"id_txtRecAmt"+recEdpRowNo+"\" onkeypress=\"NumberFormet()\" onblur=\"computeSumExtSum(this,"+recEdpRowNo+",'REC',"+nCounter+")\" /> <input type=\"hidden\" name=\"digi1_"+rowCount+"_EDP_AMT\"><img src=\"images/CalendarImages/DeleteIcon.gif\" onclick=deleteRecRow(this,"+nCounter+") />";

		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		  row.appendChild(cell3); 
       	  row.appendChild(cell4); 
		  tbody.appendChild(row); 
		  var subArray = new Array();
          subArray['expRcpRev'] = 'Recovery';						
		  subArray['amount'] = 0;
		  array[nCounter] = subArray;
		  nCounter = nCounter + 1;
		  rowCount = rowCount + 1;		
		  document.getElementById("id_txtRecEdpCode"+recEdpRowNo).focus();  
		  shuffleEdpRows();
	}
	function shuffleRecRows(k)
	{
	
		var dExpCounter = document.getElementById("expEdpCounter").value;
		var j = Number(dExpCounter)+Number(edpRowNo);
		var dRecCounter = document.getElementById("recEdpCounter").value;	
		var count = Number(dRecCounter)+Number(recEdpRowNo)+Number(j);
		var tb = 1;
		for(var i=j;i<count;i++)
		{
			var cell1 = document.getElementById('recDetPostTbl').cells(((tb*4)+0));
			var cell2 = document.getElementById('recDetPostTbl').cells(((tb*4)+3));	
			tb = tb+1;
			
			 if(cell2.children[0].value > 0)
            {
				cell1.children[2].name = "digi1_"+k+"_EDP_CODE";
				cell1.children[4].name = "digi1_"+k+"_EDP_CATEGORY";
	            cell1.children[6].name = "digi1_"+k+"_ADD_DED_FLAG";
	            cell2.children[1].name = "digi1_"+k+"_EDP_AMT";
				
				cell1.children[2].id = "digi1_"+k+"_EDP_CODE";
				cell1.children[4].id = "digi1_"+k+"_EDP_CATEGORY";
	            cell1.children[6].id = "digi1_"+k+"_ADD_DED_FLAG";
	            cell2.children[1].id = "digi1_"+k+"_EDP_AMT";
	            k++;
            }
            else
            {
            	cell1.children[2].name = "removed";
				cell1.children[4].name = "removed";
	            cell1.children[6].name = "removed";
	            cell2.children[1].name = "removed";
				
				cell1.children[2].id = "removed";
				cell1.children[4].id = "removed";
	            cell1.children[6].id = "removed";
	            cell2.children[1].id = "removed";
            }
		}	
		
	}
	function addRcptEdpRow()
	{
		  var tbody = document.getElementById('rcptDetPostTbl').getElementsByTagName('tbody')[0]; 
	
		  rcptEdpRowNo++;

		  var row = document.createElement('TR'); 
		  row.className="odd";
		  var cell1 = document.createElement('TD'); 
		  var cell2 = document.createElement('TD'); 
		  var cell3 = document.createElement('TD'); 
		  cell3.id = "id_txtRcptMjrHdCode" + rcptEdpRowNo;		  
		  cell3.className = "Label";
		  var cell4 = document.createElement('TD'); 
		  cell4.id = "id_txtRcptSubMjrHdCode" + rcptEdpRowNo;
		  cell4.className = "Label";
		  var cell5 = document.createElement('TD'); 
		  cell5.id = "id_txtRcptMinHdCode" + rcptEdpRowNo;
		  cell5.className = "Label";
		  var cell6 = document.createElement('TD'); 
		  cell6.id = "id_txtRcptSubHdCode" + rcptEdpRowNo;
		  cell6.className = "Label";
  		  var cell7 = document.createElement('TD'); 
		
		  cell1.innerHTML = "<input type=\"hidden\" name=\"rcptRows\" value=\""+rcptEdpRowNo+"\"><input type=\"text\" size=\"6\" name=\"txtRcptEdpCode"+rcptEdpRowNo+"\" id=\"id_txtRcptEdpCode"+rcptEdpRowNo+"\"  onblur=getRcptEdpDtls(this,"+rcptEdpRowNo+","+nCounter+")> ";
		  cell2.innerHTML = "<input type=\"text\" size=\"6\" name=\"txtRcptDedType"+rcptEdpRowNo+"\" id=\"id_txtRcptDedType"+rcptEdpRowNo+"\" maxlength=\"1\" onkeypress=isValidDedctionType()> ";
		  cell3.innerHTML = "";
		  cell4.innerHTML = "";
  		  cell5.innerHTML = "";
  		  cell6.innerHTML = "";
  		  cell7.innerHTML = "<input type=\"text\" size=\"7\" value=\"0\" name=\"txtRcptAmt"+rcptEdpRowNo+"\" id=\"id_txtRcptAmt"+rcptEdpRowNo+"\" onkeypress=\"NumberFormet()\" onblur=\"computeSumExtSum(this," + rcptEdpRowNo + ",'RCP'," + nCounter + ")\" /> <img src=\"images/CalendarImages/DeleteIcon.gif\" onclick=deleteRcptRow(this,"+nCounter+") />";
		  
		  row.appendChild(cell1); 
		  row.appendChild(cell2); 
		  row.appendChild(cell3); 
		  row.appendChild(cell4); 
		  row.appendChild(cell5); 
       	  row.appendChild(cell6); 
    	  row.appendChild(cell7); 
		  tbody.appendChild(row);

		  var subArray = new Array();
          subArray['expRcpRev'] = 'Receipt';						
		  subArray['amount'] = 0;
		  array[nCounter] = subArray;
		  nCounter = nCounter + 1; 
		  document.getElementById("id_txtRcptEdpCode"+rcptEdpRowNo).focus();
	}	
	function digiSignEdpDtlsData()
	{
		shuffleEdpRows();
		var edpElementEdpCode = document.getElementsByName("hdPayEdpCode");
		var edpElementCate = document.getElementsByName("hdPayEdpCate");
		var edpElementAddded = document.getElementsByName("hdPayADDDed");
		var i=0;
		for(var DCount = 0 ; DCount < edpElementEdpCode.length ; DCount++ )
		{
			var lStrEdpCode = new String(edpElementEdpCode[DCount].value);
			var lStrAmt = document.getElementById("txtAmt"+lStrEdpCode).value;
			var lStrCate = new String(edpElementCate[DCount].value);
			var lStrAddDed = new String(edpElementAddded[DCount].value);
			if(lStrAmt>0)
			{
				generateDigiSign(lStrEdpCode,"digi1_"+i+"_EDP_CODE");
				generateDigiSign(lStrAmt,"digi1_"+i+"_EDP_AMT");	
				generateDigiSign(lStrCate,"digi1_"+i+"_EDP_CATEGORY");
				generateDigiSign(lStrAddDed,"digi1_"+i+"_ADD_DED_FLAG");
				i++;
			}
		}
		var counter1 = Number(edpElementEdpCode.length)+Number(edpRowNo);
		var k =1;
		for(var j=Number(edpElementEdpCode.length);j<counter1;j++)
		{
			 var elementCode = document.getElementById("txtEdpCode" + k);
			 var elementCate = document.getElementById("txtDedType" + k);
			 var elementAmt = document.getElementById("txtAmt" + k);
			 var elementAddDed = document.getElementById("txtAddDed" + k);
			 
			 if(elementAmt > 0)
			 {			  
				generateDigiSign(elementCode.value,"digi1_" + i + "_EDP_CODE");	
				generateDigiSign(elementCate.value,"digi1_" + i + "_EDP_CATEGORY");
				generateDigiSign(elementAddDed.value,"digi1_" + i + "_ADD_DED_FLAG");						
				generateDigiSign(elementAmt.value,"digi1_" + i + "_EDP_AMT");		
				i = i+1;				
			  }
			  k = k+1;
		}	
		var recElementCode = document.getElementsByName("hdRecEdpCode");
		var recElementCate = document.getElementsByName("hdRecEdpCate");
		var recElementAddDed = document.getElementsByName("hdRecAddDed");
		
		for(var j=0;j<recElementCode.length;j++)
		{
			var lStrEdpCode = new String(recElementCode[j].value);
			var lStrAmt = document.getElementById("txtRecAmt"+lStrEdpCode).value;
			var lStrCate = new String(recElementCate[j].value);
			var lStrAddDed = new String(recElementAddDed[j].value);
			
			if(lStrAmt>0)
			{
				generateDigiSign(lStrEdpCode,"digi1_"+i+"_EDP_CODE");
				generateDigiSign(lStrAmt,"digi1_"+i+"_EDP_AMT");
				generateDigiSign(lStrCate,"digi1_"+i+"_EDP_CATEGORY");
				generateDigiSign(lStrAddDed,"digi1_"+i+"_ADD_DED_FLAG");
				i = 1+1;
			}
		}
			
		var counter2 = Number(counter1)+Number(recEdpRowNo)+Number(recElementCode.length);
		var k =1;
	    for(var j = Number(counter1)+Number(recElementCode.length) ; j< counter2; j++)
		{
			var recCode = document.getElementById("txtRecEdpCode" + k);
			var recCate = document.getElementById("txtRecDedType" + k);
			var recAddDed = document.getElementById("txtRecAddDed" + k);
			var recAmt = document.getElementById("txtRecAmt" + k);
			if(recAmt > 0)
			{			
				generateDigiSign(recCode.value,"digi1_" + i + "_EDP_CODE");	
				generateDigiSign(recCate.value,"digi1_" + i + "_EDP_CATEGORY");
				generateDigiSign(recAddDed.value,"digi1_" + i + "_ADD_DED_FLAG");						
				generateDigiSign(recAmt.value,"digi1_" + i + "_EDP_AMT");	
				i = 1+1; 					
			}
			k++;			
		 } 
		
	}
	function isValidDedctionType()
	{
		if(window.event.keyCode == 97)
		{
			window.event.keyCode = 65;
			return;
		}
		if(window.event.keyCode == 98)
		{
			window.event.keyCode = 66;
			return;
		}	
		if(window.event.keyCode == 65 || window.event.keyCode == 66)
		{
			return;
		}
		
		window.event.keyCode = 0;
	}
	
// Functions For EDPDtls.jsp....... Ends......

// Functions For newBillSelection.jsp....... Starts......
	
	//onerror = handleErr;
	
	function handleErr(Message, URL, LineNo)
		{
			var eText = "There was an error on this page.\n\n";
			eText += "Error: " + Message + "\n";
			eText += "URL: " + URL + "\n";
			eText += "Line: " + LineNo + "\n\n";
			eText += "Click OK to continue.\n\n";
			alert(eText);
			
			return true;
		}
	
// Functions For newBillSelection.jsp....... Ends......	

// Functions For GTR Format JSPs ...... Starts......
				
		function printBill()
		{
			document.getElementById("printbtn").style.visibility='hidden';
			window.print(this);
			document.getElementById("printbtn").style.visibility='visible';

   		} 
	
// Functions For GTR Format JSPs ...... Ends......	

// Functions For srchRqstResult.jsp ...... Starts......

		function getURL(lStrRqstId)
		{
			var ReqestStr = "ifms.htm?actionFlag=getRqstData";
				
			if(document.getElementById("chkSelected") != null)
			{
				var element = document.getElementsByName("chkSelected");
				var cmbo = document.getElementsByName("cmbSrch");
				ReqestStr += "&chkSelected=" + lStrRqstId;
				if(cmbo != null)
				{
					for(var i = 0; i < element.length; i++)
				    {
				    	if(element[i].checked)
					    {
					        ReqestStr += "&empType=" + cmbo[i].value;
					        break;				      
						}
					}
				}
			}				

			return ReqestStr;
		}
		function isSelected()
	    {
	    	var status = false;
	    	var aprvdReqId = new String();
	    	var empIdArr = new Array();
	    	var empTypeCount = 0;
	    	var count = 0;
	    	var sameEmpOnly = 0;
	    	var sameEmpAndBillType = 0;
	    	var element = document.getElementsByName("chkSelected");
	    	var billType = document.getElementsByName("billType");
	    	var SelChkCnt = 0;
	    	
	    	for(var i=0; i < element.length; ++i)
	    	{
	    		if(element[i].checked)
	    		{
	    			SelChkCnt ++;
	    			var reqId_empId = element[i].value.split("~");
					aprvdReqId += reqId_empId[0] + "~" ;
					empIdArr[count] = reqId_empId[1];
					count++;
	    			if(billType[i].value == '2')
		    		{
		    			empTypeCount++;
		    		}
	    		}
	    	}
	    		
    		var lStr = new String(aprvdReqId);
	    	lStr = lStr.substring(0, lStr.length - 1);
	    	
	    	if(SelChkCnt > 1)	// If there is consolidation
	    	{
	    		
		    	
		    	for(var k=0; k < count; ++k)
				{
					
					if(empIdArr[0] == empIdArr[k])
					{
						sameEmpOnly++;
						if(empTypeCount == count)
						{
							sameEmpAndBillType++;
							status = true;
						}
					}
					else
					{
						status = false;
						break;
					}
				}
	    	}
	    	else if(SelChkCnt == 1)
	    		{
	    			status = true;
	    		}
	    	
	    	if(status == true)
	    	{
	    		window_new_open(lStr);
	    	}
	    	else
	    	{
	    		if(count == sameEmpOnly && count != 0)
				{
					alert(SRCHRESULT_ONLYMEDRQST);
				}
				
				else if(SelChkCnt > 1)
				{
					alert(SRCHRESULT_SAMEEMPRQST);
				}
				else
	    		{
	    			alert(SRCHRESULT_SELECTEMP);
	    		}
	    	}
	    }	
		
// Functions For srchRqstResult.jsp ...... Ends......

// Functions For srchRqstInit.jsp ...... Starts......			
		
		var dtCh= "/";
		var minYear=1900;
		var maxYear=2500;		

		function validateForm_frmSearch()
		{
			var frmDate = document.frmSearch.txtFrmDt.value;
			var toDate = document.frmSearch.txtToDt.value;
			var frmFlag = 0;
			var toFlag = 0;
			if((frmDate.length) > 0 || (toDate.length) > 0)
			{
				if((frmDate.length) > 0 ){
					if (isDate(frmDate) == false){
						document.frmSearch.txtFrmDt.focus();
						return false;
					}
				frmFlag = 1;	
				}
				if((toDate.length)>0){		
					if (isDate(toDate) == false){
						document.frmSearch.txtToDt.focus();
						return false;
					}
				toFlag = 1;	
				}
				if((frmFlag == 1) && (toFlag == 1))
				{
					if(isProper(frmDate,toDate) == false){
					return false;
					}
				}
			}
			document.frmSearch.submit();
		}
		
		function isDate(dtStr)
		{
			var daysInMonth = DaysArray(12)
			var pos1=dtStr.indexOf(dtCh)
			var pos2=dtStr.indexOf(dtCh,pos1+1)
			var strDay=dtStr.substring(0,pos1)
			var strMonth=dtStr.substring(pos1+1,pos2)
			var strYear=dtStr.substring(pos2+1)
			strYr=strYear
			if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
			if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
			for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
			}
			month=parseInt(strMonth)
			day=parseInt(strDay)
			year=parseInt(strYr)
			if (pos1==-1 || pos2==-1)
			{
				alert(SRCH_DTFORMAT);
				return false;
			}
			if (strMonth.length<1 || month<1 || month>12)
			{
				alert(SRCH_VALMNTH);
				return false;
			}
			if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
			{
				alert(SRCH_VALDAY);
				return false;
			}
			if (strYear.length != 4 || year==0 || year<minYear || year>maxYear)
			{
				alert(SRCH_VALDIGIT);
				return false;
			}
			if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false)
			{
				alert(SRCH_VALDT);
				return false;
			}
			return true;
		}
		function daysInFebruary (year)
		{
		   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
		}
		function DaysArray(n) {
			for (var i = 1; i <= n; i++) {
				this[i] = 31
				if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
				if (i==2) {this[i] = 29}
	  	 	} 
	   		return this;
		}
		function isInteger(s){
			var i;
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (((c < "0") || (c > "9"))) return false;
		    }
		    return true;
		}
		function stripCharsInBag(s, bag){
			var i;
		    var returnString = "";
		    
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (bag.indexOf(c) == -1) returnString += c;
		    }
		    return returnString;
		}
		function isProper(frmStr,toStr)
		{
			if(frmStr.length > 0 && frmStr.length > 0)
			{
				var frmdaysInMonth = DaysArray(12)
				var frmpos1=frmStr.indexOf(dtCh)
				var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
				var frmDay=frmStr.substring(0,frmpos1)
				var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
				var frmYear=frmStr.substring(frmpos2+1)
				
				var todaysInMonth = DaysArray(12)
				var topos1=toStr.indexOf(dtCh)
				var topos2=toStr.indexOf(dtCh,topos1+1)
				var toDay=toStr.substring(0,topos1)
				var toMonth=toStr.substring(topos1+1,topos2)
				var toYear=toStr.substring(topos2+1)
				
				if(frmYear > toYear)
				{
					alert(SRCH_PROYR);
					return false;
				} 
				if(frmYear == toYear) 
				{
					if(frmMonth > toMonth)
					{
						alert(SRCH_PROMNTH);
						return false;
					}
				} 
				if(frmMonth == toMonth)
				{
					if(frmDay > toDay)
					{
						alert(SRCH_PRODAY);
						return false;
					}
				}
				return true;	
			}		
		} 
// Functions For srchRqstInit.jsp ...... Ends......		

// Functions For partyInfo.jsp ...... Starts......		

		var RowNum = -1;
		
		function RemovePartyRow(obj, tblId)
		{	   	 	
			var rowID = showRowCell(obj);            
		    var tbl = document.getElementById(tblId);    
		    tbl.deleteRow(rowID); 
		    RowNum = RowNum - 1; 
		    
		    document.getElementById("hidPartiCounter").value = RowNum;
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
		function AddPartyRow()
		{
			RowNum = RowNum + 1;
	   			   		   		
	    	var newRow = document.all("TableParty").insertRow(RowNum + 3);
	    	newRow.className = "odd";
	    	var Cell1 = newRow.insertCell();
	    	var Cell2 = newRow.insertCell();
	    	var Cell3 = newRow.insertCell();
	    	var Cell4 = newRow.insertCell();
	    	
	    	Cell1.innerHTML = '<input type="text" name="txtPartyName' + RowNum + '"  class="texttag" /> <img src="images/party.gif" onclick="ShowAllParty('+ RowNum + ')" /> <input type="hidden" name="partyCode' + RowNum + '" /> ';
	    	Cell2.innerHTML = '<input type="text" name="txtAddress' +  RowNum + '" class="texttag" /> ';
	    	Cell3.innerHTML = '<input type="text" name="txtAccountNo' + RowNum + '" class="texttag" onkeypress="NumberFormet()" /> ';
	    	Cell4.innerHTML = '<input type="text" name="txtChkAmt' + RowNum + '" class="texttag" onkeypress="amountFormat(this)" onblur="setValidAmountFormat(this)" /> <img src="images/CalendarImages/DeleteIcon.gif" onclick="RemovePartyRow(this, \'TableParty\')" /> ';
	    	
	    	document.getElementById("hidPartiCounter").value = RowNum;
		}
		function ShowAllParty(indx)
		{
			var URL = "ifms.htm?actionFlag=getAllParties&&ddo=n&Index=" + indx;
	 		window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=350"); 
		}
		function isValidPartiData()
		{
			document.getElementById("hidPartiCounter").value = RowNum;
			
			if(RowNum == -1)
			{
				return true;
			}
			else
			{
				for(var i = 0; i <= RowNum; ++i)
				{
					var lStr = new String(document.getElementById("txtPartyName" +  i).value);
					lStr = lStr.trim();
					document.getElementById("txtPartyName" +  i).value = lStr;
					
					if(lStr == "")
					{
						alert(PARTY_PARTYNAME);
						return false;
					}
				}
				for(var i = 0; i <= RowNum; ++i)
				{
					var lStr = new String(document.getElementById("txtAddress" +  i).value);
					lStr = lStr.trim();
					document.getElementById("txtAddress" +  i).value = lStr;
					
					if(lStr == "")
					{
						alert(PARTY_PARTYADD);
						return false;
					}
				}
				for(var i = 0; i <= RowNum; ++i)
				{
					var lStr = new String(document.getElementById("txtAccountNo" +  i).value);
					lStr = lStr.trim();
					document.getElementById("txtAccountNo" +  i).value = lStr;
					
					if(lStr == "")
					{
						alert(PARTY_PARTYACCNO);
						return false;
					}
				}
				for(var i = 0; i <= RowNum; ++i)
				{
					var lStr = new String(document.getElementById("txtChkAmt" +  i).value);
					lStr = lStr.trim();
					document.getElementById("txtChkAmt" +  i).value = lStr;
					
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

// Functions For createOnlineBill.jsp ...... Ends......

		
		function saveBillUsingAjax()
		{
			disable();
			//showProgressbar();

			window.setTimeout("HandleBillAjaxSave()",1000);
			return;
		}
		
		function HandleBillAjaxSave()
		{
			xmlHttp=GetXmlHttpObject();
			
			if (xmlHttp==null)
			{
				alert ("Your browser does not support AJAX!");
				return;
			} 
			  
			var url = run(); 
			var uri = "ifms.htm?actionFlag=createOnlineBill";
			url = uri + url;           
			
			
			
			xmlHttp.onreadystatechange=billstateChanged;
			xmlHttp.open("POST",uri,false);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(url);

			//alert(xmlHttp.responseText);
			
			printBillObjection();
		}
		
		function billstateChanged() 
		{ 
			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					win.close();
					alert(xmlHttp.responseText);
					
				}
				else
				{
					 win.close();
					 alert("There is some problem while generating paybill.");
				}	
				
				
					win.close();			
					hide_img();
					enable_div();
					hideProgressbar();
			}
		}			
		
		function saveBill()
		{
			
			if(notNullValidation()&&chkDuration())
			{
				if(document.getElementById("billNo").value=="-1")
				{
					alert("Please select bill");
					document.getElementById("billNo").focus();
				}
				else {
				if(parseFloat(glDDOExpAmt + NetTotal) > parseFloat(document.getElementById("hidGrantAmt").value))
				{
					alert("Total Expenditure including this bill exceeds the available Grant Amount.");
				}
				document.getElementById("userAction").value = "save";
				
				saveBillUsingAjax();
				//window.setTimeout("closeBill()",1500);	
				window.setTimeout("navigateToPayBillListPage()",1500);	
				win = window.open("payroll-progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
				}
			}
		}
		function approveBill()
		{			
			if(notNullValidation())
			{				
				// Added by Keyur to check if Bill has been created or not.
				if(document.forms[0].hidBillNo.value == '')
				{
					
					alert("Please Save the Bill before submitting it to the Treasury Office");
					return false;
				}
				approveDigiSign();
				if(approveCheck())
				{
					document.getElementById("userAction").value='approve';
					saveBillUsingAjax();	
				}				
				window.setTimeout("disableSaveApprove()",1000);	
			}
		}
		function disableSaveApprove()
		{
			document.forms[0].btnSave.disabled=true;	
			document.forms[0].btnApprove.disabled=true;	
			window.setTimeout("sendtoTrsryOfficeOnSubmit()",1200);		
		}
		
		function sendtoTrsryOfficeOnSubmit()
		{
			var url = "ifms.htm?actionFlag=sendtoTrsryOffice&BillNo=" + document.forms[0].hidBillNo.value;
			window.open(url,"TrsryList","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400");
		}
		
		function approveCheck()
		{
			var r=confirm("Once the Bill is Signed, you will not be able to edit it.\nDo you want to Proceed?")
			return r;
		}
		
		function generateDigiSign(field,digiField)
		{
			if(TCSCADataSigner1.UpdateData(field,field.length))
			{ 
				var lsignature = TCSCADataSigner1.Sign();
			    document.getElementById(digiField).value = lsignature;
			} 
		}	
		
		function digiSign()
		{
			TCSCADataSigner1.setDetached(true);
			if(TCSCADataSigner1.SelectCertificateFromUI())
			{
				digiSignEdpDtlsData();
				digiSignBudDtlsData();
				digiSignBillSpecData();
				digiSignEmpData();	
				digiSignRemarksDtlsData();
				if(document.forms[0].cmbBillType.value == 'TC')
				{
					digiSignChallanData();
				}
			}		
		}

		//Added by Ankit Bhatt.
		function navigateToHomePage()
		{
		  window.location="./hrms.htm?actionFlag=getHomePage";
		 }
		 function navigateToPayBillListPage()
		{
		  window.location="./hrms.htm?actionFlag=getMySavedBills&billStatus=saved";
		}
	//ended by Ankit Bhatt.
		
		function closeBill()
		{
			var isFromChqPrep = null;
			
			if(document.getElementById("isFromChqPrep") != null)
			{
				isFromChqPrep = document.getElementById("isFromChqPrep").value;
			}
			
			if(document.getElementById("btnClose") != null)
			{
				for(i=0;i<200000;i++);
				window.close();
			}
			
			if(isFromChqPrep != '1')
			{
				if (window.opener && !window.opener.closed) 
				{
					window.opener.location.reload();
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
			window.open(URL, "_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
		//********		VALIDATION ON SAVE		*************//
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
		

// Functions For createOnlineBill.jsp ...... Ends......

// Functions For showTrsrylist.jsp ...... Starts......

function createXMLHttpRequest() 
	{ 
  		var ua; 
		
		if(window.XMLHttpRequest) 
		{ 
			try 
			{ 
				ua = new XMLHttpRequest(); 
			} 
			catch(e) 
			{ 
				ua = false; 
			} 
		} 
		else if(window.ActiveXObject) 
		{ 
			try 
			{ 
				ua = new ActiveXObject("Microsoft.XMLHTTP"); 
			} 
			catch(e) 
			{ 
				ua = false; 
			} 
		} 
		return ua; 
	}
// Functions For showTrsrylist.jsp ...... Ends......
		
// Function For Objection ......................................................................................
	
	function printObjections()
	{
		var result = confirm("Do You Want Print the Objection");
		if(result)
		{
			showRemarks("ifms.htm?actionFlag=getAllObjectionData&pageFlg=sb");
		}
	}
		
	function printBillObjection()
	{	
		if(document.getElementById("hidBillNo").value != '')
		{
			if(document.getElementById("objCount").value != 0 && document.getElementById("objCount").value != '')
			{
				printObjections();
			}
			else if(document.getElementById("chkbox").value != '')
	 		{
				printObjections();
			}
		}
	}

// End Function For Objection ......................................................................................