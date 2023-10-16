req = null;
bankCode = new Array();
var lMFlag = true;
var lChngStmntValidFlag = true;
var lPrevMonthBillGenerateFlag = true;
var gObjChangStmntDtls = new Object();
gObjChangStmntDtls["genChangStmntBy"] = "branchWise";	//setting default value to branchWise when screen loads.
	
function chkValidation()
{
		lMFlag = true;
		lChngStmntValidFlag = true;
		lPrevMonthBillGenerateFlag = true;
		var lObjBankDtls = new Object();
		var lObjBankBranchRlt = new Object();
		var lArrBranchCode = new Array();
		if(gObjChangStmntDtls["genChangStmntBy"] == "branchWise")
		{
			if(document.getElementById("cmbBank").value == "" || document.getElementById("cmbBank").value == "-1"  )
			{
				alert(BANK);
				document.getElementById("cmbBank").focus();
					return false;
			}
	
			if(document.getElementById("cmbBranch").value == "" || document.getElementById("cmbBranch").value == "-1"  )
			{
					alert(BRANCH);
					document.getElementById("cmbBranch").focus();
					return false;
			}
		
			/*
			 * Preparing change stmnt dtls object starts
			 */
			
			var lBankCode = document.getElementById("cmbBank").value;
			var lBranchCode = document.getElementById("cmbBranch").value;
			var lBankName = document.getElementById("cmbBank").options[document.getElementById("cmbBank").selectedIndex].text;
			var lBranchName = document.getElementById("cmbBranch").options[document.getElementById("cmbBranch").selectedIndex].text;
			lObjBankDtls["bankName"] = lBankName;
			lObjBankDtls["branchName"] = lBranchName;
			lObjBankDtls["bankCode"] = lBankCode;
			lObjBankDtls["branchCode"] = lBranchCode;
			lObjBankBranchRlt[lBranchCode] = lObjBankDtls;
			lArrBranchCode[0] = lBranchCode;
			gObjChangStmntDtls["bankBranchRlt"] = lObjBankBranchRlt;
			gObjChangStmntDtls["branchCodeList"] = lArrBranchCode;
			
			//alert("branchName is :"+gObjChangStmntDtls["bankBranchRlt"][lBranchCode]["branchName"]);
			//alert("branchName is :"+gObjChangStmntDtls["branchCodeList"]);
			/*
			 * Preparing change stmnt dtls object ends
			 */
		}
		else
		{
			if(document.getElementById("cmbGrpName").value == '-1')
			{
				alert("Please select bank branch group.");
				document.getElementById("cmbGrpName").focus();
				return false;
			}
			if(gObjChangStmntDtls["branchCodeList"] == null)
			{
				alert("No bank branch is grouped with selected bank branch group.");
				return false;
			}
		}
		var month = document.getElementById("cmbForMonth").value;
		if(month < 10)
		{
			month = "0"+month;
		}

		var year = document.getElementById("cmbForYear").value;
		var selectedDate = year+month;
		var curMonth = document.getElementById("CurrentMonth").value;
		if(curMonth < 10)
		{
			curMonth = "0"+curMonth;
		}
		var curYear = document.getElementById("CurrentYear").value;
		var curDate = curYear+curMonth;
		
		
		/*if(selectedDate != curDate )
		{	
			alert("Change Statement can be generated for current monthly only");
			document.getElementById("cmbForMonth").focus();
			return false;
		}*/
		
		/*if(selectedDate > curDate )
		{	
			alert("Change Statement for future cannot be generated");
			document.getElementById("cmbForMonth").focus();
			return false;
		}*/
			
		chkPrevMonthBillGenStatus();
		if(lPrevMonthBillGenerateFlag)
		{
			checkStatusOfCurrMonthChangeStatement();
			if(lChngStmntValidFlag)
			{
				chkModifiedPPO();
				if(lMFlag)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
}
	
	function chkPrevMonthBillGenStatus()
	{
		var uri = "ifms.htm?actionFlag=chckPrevMonthGenStatus";
		var lBranchCode =document.getElementById('cmbBranch').value;
		var lForMonth = document.getElementById("cmbForMonth").value;
		var lForYear = document.getElementById("cmbForYear").value;
		
		var lStrBranchCode = "&branchCode=";
		var lArrBranchCode = gObjChangStmntDtls["branchCodeList"];
		lStrBranchCode = lStrBranchCode+lArrBranchCode.join("&branchCode=");
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:"cmbForMonth="+lForMonth+"&cmbForYear="+lForYear+lStrBranchCode,
			        onSuccess: function(myAjax){
						getDataStateChangedForChkPrevMonthGenStatus(myAjax);
					},	
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
		
		
	}
	
	function getDataStateChangedForChkPrevMonthGenStatus(myAjax)
	{
		XMLDoc = myAjax.responseXML.documentElement;
		var lStrAlertMessage = "";
		var lStrBranchCode = "";
		if(XMLDoc != null)
		{
			var lArrBranchCode = XMLDoc.getElementsByTagName('BRANCHCODE');
			var lBillErrorNode = XMLDoc.getElementsByTagName('ERROR');
			if(lBillErrorNode[0] != null)
			{
				lPrevMonthBillGenerateFlag = false;
				alert("Some Problem occured during generation of change statement.Please try again.");
				hideProgressbar();
			}
			else if(lArrBranchCode.length > 0)
			{
				lPrevMonthBillGenerateFlag = false;
				for(var i = 0 ; i < lArrBranchCode.length ; i++)
				{
					lStrBranchCode = lArrBranchCode[i].childNodes[0].nodeValue;
					lStrAlertMessage = lStrAlertMessage+"["+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+"] ";
				}
				alert("Please generate/approve previous month's monthly pension bill for following bank branch. \n"+lStrAlertMessage);
			}
		}
	}
	function pageClose()
	{
		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
		document.forms[0].submit();
	}
	
	// AJAX For Validate Bill check ...... Start.....

	function validateChngStmnt()
	{  
		if(chkValidation())
		{  
			var url;
			//url = run(); 
			//getBillValidByAJAX(url);
			genChangeStatement();
		}
		
	}
	
	function genChangeStatement()
	{
		showProgressbar();
		var uri = "ifms.htm?actionFlag=genChangeStatement";
		var lForMonth = document.getElementById("cmbForMonth").value;
		var lForYear = document.getElementById("cmbForYear").value;
		
		var lArrBankBranchDtl = new Array();
		var lStrBankBranchDtls = "&bankBranchDtls=";
		var lArrBranchCode = gObjChangStmntDtls["branchCodeList"];
		for(var i = 0 ; i < lArrBranchCode.length ; i++)
		{
			lArrBankBranchDtl[i] = gObjChangStmntDtls["bankBranchRlt"][lArrBranchCode[i]]["branchCode"]+"~"+gObjChangStmntDtls["bankBranchRlt"][lArrBranchCode[i]]["bankCode"];
		}
		lStrBankBranchDtls = lStrBankBranchDtls+lArrBankBranchDtl.join("&bankBranchDtls=");
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:"cmbForMonth="+lForMonth+"&cmbForYear="+lForYear+lStrBankBranchDtls+"&genChangStmntBy="+gObjChangStmntDtls["genChangStmntBy"],
			        onSuccess: function(myAjax){
			        	responseGenChngStatement(myAjax);
					},	
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
		
		
		/*req = createXMLHttpRequest();
		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=genChangeStatement";
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responseGenChngStatement; 
			req.send(url);
		}
		else
		{
			alert (AL_AJAX);
		}*/
	}
	
	function checkStatusOfCurrMonthChangeStatement()
	{
		/*req = createXMLHttpRequest();
		if(req != null)
		{
			//alert("req is not null");
			var baseUrl = "ifms.htm?actionFlag=validateChngStmnt";
			req.open("post", baseUrl, false); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responseChngStmntValid; 
			req.send(url);
		}
		else
		{
			alert (AL_AJAX);
		}*/
		
		var uri = "ifms.htm?actionFlag=validateChngStmnt";
		var lForMonth = document.getElementById("cmbForMonth").value;
		var lForYear = document.getElementById("cmbForYear").value;
		
		var lStrBranchCode = "&branchCode=";
		var lArrBranchCode = gObjChangStmntDtls["branchCodeList"];
		lStrBranchCode = lStrBranchCode+lArrBranchCode.join("&branchCode=");
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:"cmbForMonth="+lForMonth+"&cmbForYear="+lForYear+lStrBranchCode,
			        onSuccess: function(myAjax){
			        	responseChngStmntValid(myAjax);
					},	
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	
	}
	
	function responseChngStmntValid(myAjax)
	{
//		if(req.readyState==complete_state)
//		{ 
//			var XMLDoc = req.responseXML.documentElement;
//			var XMLEntries1 = XMLDoc.getElementsByTagName("STATUS");
//			
//			//Do not remove this code.If generation of multiple change statement in one month is allowed then only enable this code.
//			//---------------------------------------------------------------------------------------------------------------------
//			/*var XMLEntries2 = XMLDoc.getElementsByTagName("UPTODATE");
//			var lChngStmntStatus = XMLEntries1[0].text;
//			var lUptoDate = XMLEntries2[0].text;
//			if(lChngStmntStatus == "BfApproval")
//			{
//				alert("Please approve or reject the change statement of upto "+lUptoDate+" date for selected bank branch");
//				lChngStmntValidFlag = false;
//				hideProgressbar();
//			}*/
//			//---------------------------------------------------------------------------------------------------------------------
//			var lChngStmntStatus = XMLEntries1[0].text;
//			if(lChngStmntStatus == "Generated")
//			{
//				var lBankName = document.getElementById("cmbBank").options[document.getElementById("cmbBank").selectedIndex].text;
//				var lBranchName = document.getElementById("cmbBranch").options[document.getElementById("cmbBranch").selectedIndex].text;
//				var month = document.getElementById("cmbForMonth").options[document.getElementById("cmbForMonth").selectedIndex].text;
//				var year = document.getElementById("cmbForYear").value;
//				var selectedDate = month+"-"+year;
//				alert("Change Statement for "+lBankName+","+lBranchName+ " is already generated for "+selectedDate);
//				lChngStmntValidFlag = false;
//				hideProgressbar();
//			}
//			
//		}
		
		XMLDoc = myAjax.responseXML.documentElement;
		var lStrAlertMessage = "";
		var lStrBranchCode = "";
		var lStrAuditorName = "";
		if(XMLDoc != null)
		{
			var lArrBranchCode = XMLDoc.getElementsByTagName('BRANCHCODE');
			var lAuditorName = XMLDoc.getElementsByTagName('AUDITORNAME');
			var lBillErrorNode = XMLDoc.getElementsByTagName('ERROR');
			var month = document.getElementById("cmbForMonth").options[document.getElementById("cmbForMonth").selectedIndex].text;
			var year = document.getElementById("cmbForYear").value;
			var selectedDate = month+"-"+year;
			if(lBillErrorNode[0] != null)
			{
				lChngStmntValidFlag = false;
				alert("Some Problem occured during generation of change statement.Please try again.");
				hideProgressbar();
			}
			else if(lArrBranchCode.length > 0)
			{
				lChngStmntValidFlag = false;
				for(var i = 0 ; i < lArrBranchCode.length ; i++)
				{
					lStrBranchCode = lArrBranchCode[i].childNodes[0].nodeValue;
					lStrAuditorName = lAuditorName[i].childNodes[0].nodeValue;
					if(lStrAuditorName.length > 0)
					{
						lStrAlertMessage = lStrAlertMessage+"["+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+" by "+lStrAuditorName+"] ";
					}
					else
					{
						lStrAlertMessage = lStrAlertMessage+"["+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"] +"] ";
					}
				}
				alert("Change statement of following bank branches are already generated for "+selectedDate+" \n"+lStrAlertMessage);
				hideProgressbar();
			}
		}
	}
	function responseGenChngStatement(myAjax)
	{
		XMLDoc = myAjax.responseXML.documentElement;
		if(XMLDoc != null)
		{
			var XMLEntries1 = XMLDoc.getElementsByTagName("ERROR");	
			//var XMLEntries2 = XMLDoc.getElementsByTagName("STATUS");
			var XMLEntries3 = XMLDoc.getElementsByTagName("CHNGRQSTID");
			var XMLEntries4 = XMLDoc.getElementsByTagName("NETAMTERROR");
			var XMLEntries5 = XMLDoc.getElementsByTagName("BRANCHWITHNOPNSR");
			var XMLEntries6 = XMLDoc.getElementsByTagName("NOFPDATEERROR");
			var lBankName = document.getElementById("cmbBank").options[document.getElementById("cmbBank").selectedIndex].text;
			var lBranchName = document.getElementById("cmbBranch").options[document.getElementById("cmbBranch").selectedIndex].text;
			
			var month = document.getElementById("cmbForMonth").options[document.getElementById("cmbForMonth").selectedIndex].text;
			var year = document.getElementById("cmbForYear").value;
			var selectedDate = month+"-"+year;
			var lStrBranchCode = "";
			if(XMLEntries1[0] != null)
			{
				alert("Some Problem occured during generation of change statement.Please try again.");
				hideProgressbar();
			}
			else if((XMLEntries4[0] != null) || (XMLEntries6[0] != null))
			{
				var lStrProbPpoList = "";
				var lStrNoFpDatesList = "";
				var lStrAlertNetAmtError = "";
				var lStrAlertNoFpDtlsError = "";
				if(XMLEntries4[0] != null)
				{
					lStrAlertNetAmtError = "The cases with following ppo no have negative net amount. \n";
					for(var i = 0 ; i < XMLEntries4.length ; i++)
					{
						lStrBranchCode = XMLEntries4[i].childNodes[0].childNodes[0].nodeValue;
						lStrProbPpoList = XMLEntries4[i].childNodes[1].childNodes[0].nodeValue;
						lStrAlertNetAmtError = lStrAlertNetAmtError +gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+"\n"+lStrProbPpoList+"\n";
					}
				}
				if(XMLEntries6[0] != null)
				{
					lStrAlertNoFpDtlsError = "The following pension cases have death date and valid family pensioners available, but EFP date or RR date or both are not available. \n";
					lStrBranchCode = "";
					for(var i = 0 ; i < XMLEntries6.length ; i++)
					{
						lStrBranchCode = XMLEntries6[i].childNodes[0].childNodes[0].nodeValue;
						lStrNoFpDatesList = XMLEntries6[i].childNodes[1].childNodes[0].nodeValue;
						lStrAlertNoFpDtlsError = lStrAlertNoFpDtlsError +gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+"\n"+lStrNoFpDatesList+"\n";
					}
				}
				alert("Change Statements can't be generated because \n "+lStrAlertNetAmtError+lStrAlertNoFpDtlsError);
				hideProgressbar();
			}
			else
			{
				/*var chngStmntStatus = XMLEntries2[0].text;
				var chngStmntRqstId = XMLEntries3[0].text;
				if(chngStmntStatus == "Generated")
				{
					alert("Change Statement of "+selectedDate+" for  "+lBankName+","+lBranchName+" is generated successfully");
					 showChangeStatement(chngStmntRqstId);
				}
				else
				{
					alert("There is not any valid pensioner for "+lBankName+","+lBranchName+" for "+selectedDate);
				}
				*/
				var lStrAlertNoValidPnsrs = "";
				var lStrAlertMessage = "";
				if(XMLEntries5[0] != null)
				{
					lStrAlertNoValidPnsrs = lStrAlertNoValidPnsrs + "Following change statements are generated with no valid pensioners . \n";
					for(var i = 0 ; i < XMLEntries5.length ; i++)
					{
						lStrBranchCode = XMLEntries5[0].childNodes[0].nodeValue;
						lStrAlertNoValidPnsrs = lStrAlertNoValidPnsrs + "["+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+"] ";
					}
				}
				if(XMLEntries3[0] != null)
				{
					var chngStmntRqstId = XMLEntries3[0].childNodes[0].nodeValue;
					lStrAlertMessage = lStrAlertMessage + "Change Statement of "+selectedDate+" for  "+lBankName+","+lBranchName+" is generated successfully.";
					lStrAlertMessage = lStrAlertMessage  +"\n"+ lStrAlertNoValidPnsrs;
					alert(lStrAlertMessage);
					hideProgressbar();
					showChangeStatement(chngStmntRqstId);
				}
				else
				{
					var bankBranchGroup = document.getElementById("cmbGrpName").options[document.getElementById("cmbGrpName").selectedIndex].text;
					lStrAlertMessage = lStrAlertMessage + "Change Statement of "+selectedDate +" for bank branch group '"+bankBranchGroup+"' generated successfully.\n";
					lStrAlertMessage = lStrAlertMessage  +"\n"+ lStrAlertNoValidPnsrs;
					alert(lStrAlertMessage);
					hideProgressbar();
				}
					
			}
		}		
	}
	
	function showChangeStatement(chngStmntRqstId)
	{
		var url = "ifms.htm?actionFlag=reportService&reportCode=365454&action=generateReport&asPopup=TRUE";
		//var url = "ifms.htm?actionFlag=viewChngStmntSummary";
		var lBankCode = document.getElementById("cmbBank").value;
		var lBranchCode = document.getElementById("cmbBranch").value;
		var month = document.getElementById("cmbForMonth").value;
		if(month < 10)
		{
			month = "0"+month;
		}

		var year = document.getElementById("cmbForYear").value;
		var selectedDate = year+month;
		
		url += "&bankCode="+lBankCode+"&branchCode="+lBranchCode+"&forMonth="+selectedDate+"&changeRqstId="+chngStmntRqstId;
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "showChangeStatement", urlstyle);
	}
	function getBillValidByAJAX(url)
	{
		//alert(url);
		req = createXMLHttpRequest();
		if(req != null)
		{
			//alert("req is not null");
			var baseUrl = "ifms.htm?actionFlag=validateBill&"+url;
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responseBillValid; 
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}
	}
	
	function responseBillValid()
	{
		if(req.readyState==complete_state)
		{ 
			hideProgressbar();
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("BILL");				
			
			var blnResult = XMLEntries[0].text;
			
			if(blnResult == 'bilApproved')
			{
				if(window.confirm("  Bill is already created for this month \n  Do you want to generate this Bill Again ?"))
				{
					var height = screen.height - 100;
		  			var width = screen.width;
		  			var url = run();
					var urlstring = "./ifms.htm?actionFlag=createPensionSpecificBills&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
					var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
					window.open(urlstring, "monthlyPensionBill", urlstyle);
				}
				else
				{			
					//alert(BILLGEN);
					//document.forms[0].elements["cmbForMonth"].selectedIndex = 0;
					document.forms[0].elements["cmbForMonth"].selectedIndex = document.getElementById("CurrentMonth").value - 1;
					document.forms[0].elements["cmbForMonth"].focus();
				}
			}
			else if(blnResult == 'bilNotApproved')
			{
				alert( "Please Approve or Reject the Bill before creating it again. " )
				document.forms[0].elements["cmbForMonth"].selectedIndex = document.getElementById("CurrentMonth").value - 1;
				document.forms[0].elements["cmbForMonth"].focus();
			}
			else
			{
				var height = screen.height - 100;
		  		var width = screen.width;
		  		var url = run();
				//var urlstring = "ifms.htm?actionFlag=createPensionSpecificBills&subjectId=44&PensionType=Monthly&cmbForScheme=IRLA"+url;
				var urlstring = "ifms.htm?actionFlag=getMonthlyPension&subjectId=44&PensionType=Monthly&cmbForScheme=IRLA"+url;
				var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
				window.open(urlstring, "monthlyPensionBill", urlstyle);
			}	
		}
	}
	
	// AJAX For Validate Bill check ...... End......
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
	
	PPONO = '';
	PPONOVALUE = '' ;
	function getDetailsOfBank(lstr,lPPONO) {
		PPONO = lstr.name;
        PPONOVALUE = lPPONO;
		if(lstr.value >0 )
		{
			if(document.getElementById("DivAud"+lstr.value) && document.getElementById("DivAud"+lstr.value).childNodes[1].options)
			{
				
				var cn = document.getElementById("DivAud"+lstr.value).childNodes;
				var tempBankCode = cn[i].name;
				document.getElementById("txtBranch"+PPONOVALUE).style.display = 'inline';
				document.getElementById("txtBank"+PPONOVALUE).style.display = 'inline';
				document.getElementById("hidbankBranchCode").value = lstr.value;
				document.getElementById("hidbankCode"+PPONOVALUE).value = document.getElementById("hidbankCode"+tempBankCode).value;
				document.getElementById("txtBranch"+PPONOVALUE).innerHTML = document.getElementById("Branch"+lstr.value).innerHTML;
				document.getElementById("txtBank"+PPONOVALUE).innerHTML = document.getElementById("Bank"+lstr.value).innerHTML;
				document.getElementById("txtAuditorName"+PPONOVALUE).innerHTML = document.getElementById("DivAud"+lstr.value).innerHTML;
				cn = document.getElementById("txtAuditorName"+PPONOVALUE).childNodes;
				cn[i].name = PPONOVALUE;	
			}
			else
			{
				req = createXMLHttpRequest();
				if(req != null)
				{
					var baseUrl = "ifms.htm?actionFlag=getDetailsOfBank&txtBranchCode="+lstr.value; 
					req.open("post", baseUrl, true); 
					req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					req.onreadystatechange = responsegetDetailsOfBank; 
					req.send(baseUrl);
				}
				else
				{
					alert (AL_AJAX);
				}
			}
		}
		else
		{
			document.getElementById(lstr.name).value = "";
		}
	}
	function responsegetDetailsOfBank()
	{
		var flag = 0;
		if(req.readyState==complete_state)
		{   
			var XMLDoc = req.responseXML.documentElement;
			if(XMLDoc != null)
			{	
				var XmlResValues = XMLDoc.getElementsByTagName('XMLDOCBANKDETAILS');	
				
				if(XmlResValues != null && XmlResValues[0] != null)
				{
				document.getElementById("txtBranch"+PPONOVALUE).style.display = 'inline';
				document.getElementById("txtBank"+PPONOVALUE).style.display = 'inline';
				document.getElementById("hidbankBranchCode").value = XmlResValues[0].childNodes[2].text;
				var tempBranchCode = XmlResValues[0].childNodes[2].text;
				document.getElementById("hidbankCode"+PPONOVALUE).value = XmlResValues[0].childNodes[0].text;
				document.getElementById("txtBranch"+PPONOVALUE).innerHTML = '<div id="Branch'+tempBranchCode+'">'+XmlResValues[0].childNodes[3].text+'</div>';
				document.getElementById("txtBank"+PPONOVALUE).innerHTML = '<div id="Bank'+tempBranchCode+'">'+XmlResValues[0].childNodes[1].text+'</div>';
				XmlResValues  = XMLDoc.getElementsByTagName('XMLDOCAUDDETAILS');
				var optionString = "";
				for(var j=0;j<XmlResValues[0].childNodes.length;j++)
				{
					var x = XmlResValues[0].childNodes[j].text;
					optionString = "<option value='"+x.split("~")[1]+"'>"+ x.split("~")[0] +"</option>"+optionString;
				}
				  
				   document.getElementById("txtAuditorName"+PPONOVALUE).innerHTML = '<div id="DivAud'+tempBranchCode+'"> <select name="'+PPONOVALUE+'" onchange="getSelectedPost(this)"> <option value="-1"> --Select-- </option>'+optionString+'</Select> </div>';
				}
				else {
					document.getElementById("txtBranch"+PPONOVALUE).innerHTML = '';
					document.getElementById("txtBank"+PPONOVALUE).innerHTML = '';
					document.getElementById("txtAuditorName"+PPONOVALUE).innerHTML = '';
					alert("Please Enter Proper Bank Branch Code");
					document.getElementById(PPONO).value = "";
					document.getElementById(PPONO).focus();
				}
			}
		}
	}
	function getSelectedPost(x)
	{
		document.getElementById("hidPostId"+x.name).value = x.value;
	}
	
function getBranchByBranchCodeMnthly()
	{
		//ajaxCounter = varStr;
		
		if(document.getElementById("cmbAuditor") && (document.getElementById("cmbAuditor").value =="" || document.getElementById("cmbAuditor").value == "-1" ))
		{
			alert("Please Select an Auditor.")
			document.getElementById("cmbAuditor").focus();
			return false;
			
		}
		if(document.getElementById("txtbranchCode").value.length >0 )
		{
			req = createXMLHttpRequest();
			if(req != null)
			{
				var baseUrl = "ifms.htm?actionFlag=getBranchFromBranchCode&txtbranchCode="+document.getElementById("txtbranchCode").value;
				if(document.getElementById("cmbAuditor") && document.getElementById("cmbAuditor").value!="" && document.getElementById("cmbAuditor").value!="-1" )
				  baseUrl += "&lAuditor="+document.getElementById("cmbAuditor").value;
				req.open("post", baseUrl, true); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = responsegetBranchByBranchCodeMnth; 
				req.send(baseUrl);
			}
			else
			{
				alert (AL_AJAX);
			}
		}
		else
		{
			document.getElementById("cmbBank").value = "-1";
			document.getElementById("cmbBranch").options[0].value = "-1";
			document.getElementById("cmbBranch").options[0].innerHTML = " -- Select --";
			document.getElementById("cmbBank").options[0].innerHTML = " -- Select --";
			document.getElementById("cmbBank").disabled="";
			document.getElementById("cmbBranch").disabled="";
		}
	}
	
	function responsegetBranchByBranchCodeMnth()
	{
		var flag = 0;
		
		
		if(req.readyState==complete_state)
		{ 
			var XMLDoc = req.responseXML.documentElement;
			if(XMLDoc != null)
			{
				var XmlResValues = XMLDoc.getElementsByTagName('XMLDOC');	
				if(XmlResValues[0].childNodes[0] != null)
				{
				   
					
						for(var i=0;i<bankCode.length;i++)
						{
							if(bankCode[i] == XmlResValues[0].childNodes[0].text)
							{
								flag = 1;
							}
						}
					
					if(flag == 1 )
					{
						document.getElementById("cmbBank").value = XmlResValues[0].childNodes[0].text;
						document.getElementById("cmbBank").options[0].innerHTML = XmlResValues[0].childNodes[1].text;
						document.getElementById("cmbBranch").options[0].value = XmlResValues[0].childNodes[2].text;
						document.getElementById("cmbBranch").options[0].innerHTML = XmlResValues[0].childNodes[3].text;
						document.getElementById("cmbBranch").options[0].selected="selected";
						/*var headcodeList = XmlResValues[0].childNodes[4].text;
						
						var str = headcodeList.split("#");
						var hdrStr = '<option value="-1">--SELECT--</option>';
						for(var i=0;i<str.length;i++)
						{
							hdrStr += '<option value="'+str[i]+'">'+str[i]+'</option>';							
						}*/
						document.getElementById("cmbBank").disabled="disabled";
						document.getElementById("cmbBranch").disabled="disabled";
						//document.getElementById("cmbHeadCode").outerHTML = '<select name="cmbHeadCode" id="cmbHeadCode" style="width:80%" tabindex="6" onchange="changeHeadDescMnthly()">'+ hdrStr +'</select>';
						document.getElementById("GenChngStmnt").focus();
					}
					else
					{
						setDefaultCmb();
					}
				}
				else
				{
					setDefaultCmb();
				}
			}
			else
			{
				setDefaultCmb();
			}
		}
	}
	function setDefaultCmb()
	{
		alert(BANKCODE);
		document.getElementById("txtbranchCode").value="";
		document.getElementById("cmbBank").value="-1";
		document.getElementById("cmbBranch").value="-1";
		document.getElementById("txtbranchCode").focus();
	}
	
	/*function changeHeadDescMnthly()
	{
		if(document.getElementById("cmbHeadCode").value == "-1")
		{
			document.getElementById("cmbHeadCodeDesc").value = "";
		}
		else
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert (AL_AJAX);
				return;
			} 
			var uri = "ifms.htm?actionFlag=getHeadDesc&HeadCode="+document.getElementById("cmbHeadCode").value;
			xmlHttp.onreadystatechange=getHeadDescMnthly;
			xmlHttp.open("POST",uri,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			
			xmlHttp.send(uri);
			document.getElementById("cmbHeadCodeDesc").disabled="disabled";
		}
	}
	
	function getHeadDescMnthly()
	{
		if (xmlHttp.readyState==complete_state)
		{
			 XMLDoc = xmlHttp.responseXML.documentElement;	
			 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');	
			 document.getElementById("cmbHeadCodeDesc").value = XmlHiddenValues[0].childNodes[0].text.replace('~','&');
			 if( document.getElementById("hidDcrg"))
			 {
			 	 document.getElementById("hidDcrg").value =   XmlHiddenValues[0].childNodes[1].text;
			 }
			 if(document.getElementById("hidPension"))
			 {
			 	 document.getElementById("hidPension").value =  XmlHiddenValues[0].childNodes[2].text;
			 }
			 if(document.getElementById("hidCVP"))
			 {
			 	 document.getElementById("hidCVP").value  =  XmlHiddenValues[0].childNodes[3].text;	
			 }
		}
	}*/
	
	function getBranchForPrint()
	{
		if(document.getElementById("txtbranchCode").value != '')
		{
			document.getElementById("cmbBranch").value = document.getElementById("txtbranchCode").value;
			//document.getElementById("cmbBranch").disabled="disabled";
			document.getElementById("cmbForScheme").focus();
		}
		else
		{
			document.getElementById("cmbBranch").value="-1";
			document.getElementById("cmbBranch").focus();
		}
	}
	
	function chkPrintValidation()
	{
		if(document.getElementById("cmbBranch").value == "-1")
		{
			alert(BRANCH);
			document.getElementById("cmbBranch").focus();
			return false;
		}
		/*if(document.getElementById("cmbHeadCode").value == "-1")
		{
			alert(HEADCODE);
			document.getElementById("cmbHeadCode").focus();
			return false;
		}*/
		if(document.getElementById("cmbForScheme").value=="-1")
		{
			alert(SCHEME);
			document.getElementById("cmbForScheme").focus();
			return false;
		}
		return true;
	}
	
	function getBillPrint()
	{
		if(chkValidation())
		{
		
	  		var url = run();
	  		//document.monthlyPension.action= "./ifms.htm?actionFlag=getPrintMonthlyBill&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
			//document.monthlyPension.submit();
	  		getPrintValidByAJAX(url);
		}
	}

	function getPrintValidByAJAX(url)
	{
		showProgressbar();
		/*req = createXMLHttpRequest();
		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=validateBill&"+url;
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responsePrintValid; 
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}*/
		var billCrtd = document.getElementById("cmbBillCrtd").value;
		alert('billCrtd=='+billCrtd);
		if(billCrtd == 'Y')
		{
			var height = screen.height - 100;
	  		var width = screen.width;
	  		var url = run();
			document.monthlyPension.action = "./ifms.htm?actionFlag=viewMonthlyReport&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
			document.monthlyPension.submit();
		}
		else if(billCrtd == 'N')
		{
			var height = screen.height - 100;
	  		var width = screen.width;
	  		var url = run();
			document.monthlyPension.action= "./ifms.htm?actionFlag=getPrintMonthlyBill&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
			document.monthlyPension.submit();
		}
	}
	
	/*function responsePrintValid()
	{
		if(req.readyState==complete_state)
		{ 
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("BILL");				
			
			var blnResult = XMLEntries[0].text;
			
			if(blnResult == 'true')
			{
				//call viewmonthly
				var height = screen.height - 100;
		  		var width = screen.width;
		  		var url = run();
				document.monthlyPension.action = "./ifms.htm?actionFlag=viewMonthlyReport&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
				document.monthlyPension.submit();
			}		
			else
			{
				//call getmonthly
				var height = screen.height - 100;
		  		var width = screen.width;
		  		var url = run();
				document.monthlyPension.action= "./ifms.htm?actionFlag=getPrintMonthlyBill&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
				document.monthlyPension.submit();
			}	
		}
	}*/
	function homePageForDIS()
	{
		document.printMonthlyBill.action= 'ifms.htm?viewName=DIS';
		document.printMonthlyBill.submit();
	}
	function homePage1()
	{
		document.printMonthlyBill.action= 'ifms.htm?viewName=MRTrackingParaPage';
		document.printMonthlyBill.submit();
	}
	function homePage()
	{
		document.printMonthlyBill.action= './ifms.htm?actionFlag=openMRExpParamPage';
		document.printMonthlyBill.submit();
	}	
	function Open_MonthlyPrintBill()
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
        
        var printString = document.getElementById("hidMonthlyPrint").value;
        cw.document.write(printString);	
      
	    cw.document.write('</body>');  
	    cw.document.write('<script language="javascript">');
		cw.document.write("window.print();");
     	cw.document.write( '<' + "/script" + '>');
        cw.location.reload(false); 
	}
		
	function Close_report()
	{
		document.printMonthlyBill.action= "./ifms.htm?actionFlag=loadPrintMonthlyPara";
		document.printMonthlyBill.submit();
	}
	
	function getPrvMnthArrDtls(lPnsnerCode)
	{
		var height = screen.height/2;
 		var width = screen.width - 250;
		var url = "ifms.htm?actionFlag=getPrvMonthArrDtls&pnsnerCode="+lPnsnerCode;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		window.open(url, "PrvMonthlyArrearDetails", urlstyle);
	}
	
	function validateMonthlyDtls()
	{
		if(document.getElementById("hidLessAmntFlag").value == 'Y')
		{
			if(window.confirm(document.getElementById("hidLessAmntPPONos").value))
			{
				return false;
			}
		}
		else if(document.getElementById("hidNoAccntPPONos").value != "N" && document.getElementById("hidNoAccntPPONos").value != null
		&& document.getElementById("hidNoAccntPPONos").value != "")
		{
			if(window.confirm(document.getElementById("hidNoAccntPPONos").value))
			{
				return false;
			}
		}
		else if(document.getElementById("DupPPO").value != "N" && document.getElementById("DupPPO").value != null
		&&  document.getElementById("DupPPO").value != "")
		{
			alert(document.getElementById("DupPPO").value)
			return false;
		
		}
		
		
		else if(document.getElementById("DupPPOCVP").value != "N" && document.getElementById("DupPPOCVP").value != null 
		&& document.getElementById("DupPPOCVP").value != "")
		{
			alert(document.getElementById("DupPPOCVP").value)
			return false;
		
		}
		else if(document.getElementById("DupPPODCRG").value != "N" && document.getElementById("DupPPODCRG").value != null 
		&& document.getElementById("DupPPODCRG").value != "")
		{
			alert(document.getElementById("DupPPODCRG").value)
			return false;
		
		}
		else if(document.getElementById("ProbPPOFlag").value == 'Y')
		{
			if(window.confirm("Some cases have problem and are not included in this bill.\n Do you wish to continue saving the bill?"))
			{
				return true;
			}
		}
		else if(document.getElementById("ProbPPOFlag").value == 'N')
		{
			return true;
		}
		return false;
	}
	
	function monthlyVariation()
	{
		if(chkValidation())
		{
			showProgressbar();
	  		var url = run();
	  		//document.monthlyPension.action= "./ifms.htm?actionFlag=getMonthlyVariation&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
			//document.monthlyPension.submit();
			getValidMonthlyVarByAJAX(url);
		}
		
	}
	
	function getValidMonthlyVarByAJAX(url)
	{
		req = createXMLHttpRequest();
		if(document.getElementById("cmbForScheme").value != 'RUBARU')
		{
			if(req != null)
			{
				var baseUrl = "ifms.htm?actionFlag=validateBill&"+url;
				req.open("post", baseUrl, true); 
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = responseValidMonthlyVar; 
				req.send(baseUrl);
			}
			else
			{
				alert (AL_AJAX);
			}
		}
		else
		{
			
			var url = run();
			document.monthlyPension.action= "./ifms.htm?actionFlag=getMonthlyVariation&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
			document.monthlyPension.submit();
		}
	}
	
	function responseValidMonthlyVar()
	{
		if(req.readyState==complete_state)
		{ 
			//hideProgressbar();
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("BILL");				
			
			var blnResult = XMLEntries[0].text;
			
			if(blnResult == 'true'){
				// bill already generated...so sholdn't be allowed
				
				hideProgressbar();
				
				alert(BILLVARGEN);
				document.forms[0].elements["cmbForMonth"].selectedIndex = document.getElementById("CurrentMonth").value - 1;
				document.forms[0].elements["cmbForMonth"].focus();
				return;
			}		
			else
			{
				//call getmonthly
		  		var url = run();
				document.monthlyPension.action= "./ifms.htm?actionFlag=getMonthlyVariation&subjectId=44&PensionType=Monthly&Scheme="+document.getElementById("cmbForScheme").value+url;
				document.monthlyPension.submit();
			}	
		}
	}
	
	function CloseVar_report()
	{
		document.printMonthlyBill.action= "./ifms.htm?actionFlag=loadMonthlyVariation";
		document.printMonthlyBill.submit();
	}
	
	
	function noDispBank()
	  {
	   
	   if(document.getElementById('cmbForScheme').value == 'MONEY ORDER' || document.getElementById('cmbForScheme').value == 'RUBARU')
	     {
	        if(document.getElementById('cmbForScheme').value == 'RUBARU')
	        {
	         	document.getElementById("PPONo").style.display = 'block';
	         	
	        }
	        else
	        {
	        	document.getElementById("PPONo").style.display = 'none';
	        }
	      //  document.getElementById("bnkCode").style.display = 'none';
	        document.getElementById("bnkBrnch").style.display = 'none';
	        document.getElementById("GenChngStmnt").focus();
	     }
	    if(document.getElementById('cmbForScheme').value == 'IRLA' || document.getElementById('cmbForScheme').value == '-1')
	     {
	         document.getElementById("bnkBrnch").style.display = 'block';
	         document.getElementById("PPONo").style.display = 'none';
	        // document.getElementById("bnkBrnch").style.display = 'block';
	        // document.getElementById("PPONo").style.display = 'none';
	         
	     }
	 }
	     
	 function disableBranchCode()
	 {
	 	if(document.getElementById("cmbBank").value!="-1")
	 	{
		 	document.getElementById("txtbranchCode").value = "";
		 	document.getElementById("txtbranchCode").disabled="disabled";
		}
		else
		{
		 	document.getElementById("txtbranchCode").disabled="";
		}
	 }
	 
	function chkModifiedPPO()
	{
		var uri = 'ifms.htm?actionFlag=getModifiedPPOForMonthly';
		var lStrBranchCode = "&branchCode=";
		var lArrBranchCode = gObjChangStmntDtls["branchCodeList"];
		lStrBranchCode = lStrBranchCode+lArrBranchCode.join("&branchCode=");
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:lStrBranchCode,
			        onSuccess: function(myAjax){
			        	chkModifiedPPOForMonthly(myAjax);
					},	
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	
	function chkModifiedPPOForMonthly(myAjax)
	{
		lMFlag = true ;
		XMLDoc = myAjax.responseXML.documentElement;
		var lStrAlertMessage = "";
		var lStrBranchCode = "";
		var lStrModifiedPPONos = "";
		if(XMLDoc != null)
		{
			var lArrModifiedPPODtls = XMLDoc.getElementsByTagName('MODIFIEDPPODTLS');
			var lBillErrorNode = XMLDoc.getElementsByTagName('ERROR');
			if(lBillErrorNode[0] != null)
			{
				lMFlag = false;
				alert("Some Problem occured during generation of change statement.Please try again.");
				hideProgressbar();
			}
			else if(lArrModifiedPPODtls.length > 0)
			{
				for(var i = 0 ; i < lArrModifiedPPODtls.length ; i++)
				{
					lStrBranchCode = lArrModifiedPPODtls[i].childNodes[0].childNodes[0].nodeValue;
					lStrModifiedPPONos = lArrModifiedPPODtls[i].childNodes[1].childNodes[0].nodeValue;
					lStrAlertMessage = lStrAlertMessage+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["bankName"]+","+gObjChangStmntDtls["bankBranchRlt"][lStrBranchCode]["branchName"]+"\n"+lStrModifiedPPONos+"\n";
				}
				alert("System Can't generate change statements for selected bank branches as following PPOs are in Modified state. \n"+lStrAlertMessage);
				lMFlag = false;
			}
		}
	}
	// For Variation in Monthly
	function openVariation()
	{
	    if(validateMonthlyDtls())
	    {
	    	url="ifms.htm?viewName=openVariation&hidBranch="+document.getElementById("hidBranch").value+"&hidforMonth="+document.getElementById("hidforMonth").value+"&billStatus="+document.getElementById("billStatus").value;
			var urlstyle = 'dialogHeight:'+300+';dialogWidth:'+500+';center:1;edge:sunken;resizable:0;scroll:0;status:0;unadorned:1;'
			if(window.showModalDialog(url, "", urlstyle) == true)
			{
				alert('return back from variation window');
				saveData();
			}
	    }

	}
	// From Variation Monthly .. For getting Variation Details !!
	function getVariationDetails()
	{
		//alert("System is generating Variation Report..")
		document.getElementById("MonthlyPrint").value = '';
		var urlV="./ifms.htm?actionFlag=getVariationReportForMonthly&hidBranch="+document.getElementById("hidBranch").value+"&hidforMonth="+document.getElementById("hidforMonth").value+"&billStatus="+document.getElementById("billStatus").value+"&"+run();
		var urlstyle = "height=1,width=1,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
		//window.open(urlV, "monthlyPension", urlstyle);
		
		//var urlstring = "./ifms.htm?actionFlag=getVariationReportForMonthly&hidBranch="+document.getElementById("hidBranch").value+"&hidforMonth="+document.getElementById("hidforMonth").value+"&billStatus="+document.getElementById("billStatus").value+"&"+run();
		//var urlstyle = "height=1,width=1,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=no,top=0,left=0";
		//window.open(urlstring, "monthlyPensionBill", urlstyle);
		
		document.getElementById("MonthlyPrint").value = window.showModalDialog(urlV, "", urlstyle);
		document.getElementById("btnSave").disabled = false;
		document.getElementById("btnPrint").disabled = false;
	}
	
	function getMonthlyVariation()
	{
		if (xmlHttp.readyState==complete_state)
		{
			XMLDoc = xmlHttp.responseXML.documentElement;
			if(XMLDoc != null)
			{
				var XmlResValues = XMLDoc.getElementsByTagName('VARIATION');
				var str = XmlResValues[0].childNodes[0].text;
				alert(str);
			}
		}
	}
	
	function genChangStmntBySelection(obj)
	{
		gObjChangStmntDtls = new Object();
		gObjChangStmntDtls["genChangStmntBy"] = obj.value;
		clearAllData();
		if(obj.value == "grpWise")
		{
			document.getElementById("bnkBrnch").style.display = "none";
			document.getElementById("bnkBranchGrp").style.display = "block";
		}
		else
		{
			document.getElementById("bnkBrnch").style.display = "block";
			document.getElementById("bnkBranchGrp").style.display = "none";
		}
	}
	
	function clearAllData()
	{
		document.getElementById("txtbranchCode").value = "";
		document.getElementById("cmbGrpName").value = "-1";
		document.getElementById("cmbBank").value = "-1";
		document.getElementById("cmbBranch").value = "-1";
	}
	
	function getBankBranchGrpDtls(objCmbGrp)
	{
		gObjChangStmntDtls["bankBranchRlt"] = null;
		gObjChangStmntDtls["branchCodeList"] = null;
		//--Not fetching bank branch details if no bank group is selected
		if(document.getElementById("cmbGrpName").value == '-1')
		{
			return false;
		}
		var lGrpId = objCmbGrp.value;
		var url = "ifms.htm?actionFlag=getBankGroupDtlsByGrpId"; 
		var params = "grpId="+lGrpId;
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: params,
			        onSuccess: function(myAjax) {
								populateBankBranchGrpDtls(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
	
	function populateBankBranchGrpDtls(myAjax)
	{
		XMLDoc =  myAjax.responseXML.documentElement;
		var bankName = "";
		var branchName = "";
		var bankCode = "";
		var branchCode = "";
		var lObjBankDtls = null;
		var lObjBankBranchRlt = new Object();
		var lArrBranchCode = new Array();
		if(XMLDoc != null)
		{
			var bnkBranchGrpDtls = XMLDoc.getElementsByTagName('GRPDTLS');
			if(bnkBranchGrpDtls.length > 0)
			{
				for(var i = 0;i <bnkBranchGrpDtls.length ;i++)
				{
					bankName = bnkBranchGrpDtls[i].childNodes[0].childNodes[0].nodeValue;
					branchName = bnkBranchGrpDtls[i].childNodes[1].childNodes[0].nodeValue;
					bankCode = bnkBranchGrpDtls[i].childNodes[2].childNodes[0].nodeValue;
					branchCode = bnkBranchGrpDtls[i].childNodes[3].childNodes[0].nodeValue;
					lArrBranchCode[i] = branchCode;
					lObjBankDtls = new Object();
					lObjBankDtls["bankName"] = bankName;
					lObjBankDtls["branchName"] = branchName;
					lObjBankDtls["bankCode"] = bankCode;
					lObjBankDtls["branchCode"] = branchCode;
					lObjBankBranchRlt[branchCode] = lObjBankDtls;
				}
				gObjChangStmntDtls["bankBranchRlt"] = lObjBankBranchRlt;
				gObjChangStmntDtls["branchCodeList"] = lArrBranchCode;
			}
		}
	}				