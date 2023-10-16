/* fuction for emp bank dtls in table in form*/

	var branchCodeInEditMode = -1;

	function submitInDb()
	{
		var passportDtlSubmitStatus=checkPosportDateDtls();
		var licenseDtlSubmitStatus=checkLicenseDateDtls();
		if(passportDtlSubmitStatus)
		{
			if(licenseDtlSubmitStatus)
			{
				document.EmpMislenousDtls.action = "hrms.htm?actionFlag=saveBnkDtl&workFlowEnabled="+ workFlowEnabled;
				document.EmpMislenousDtls.submit();
			}
		}
		else
			return;
	}
	
	function addOrUpdateInsuranceDtls(btnObj)
	{	
	    if (btnObj == 'Reset')
		{
			resetEmpInsuranceData(); 
		}
		else
		{	
			var addInsurancsDtlArray= new Array('txtPolcyNo','txtNameOfPolcy','txtCmpName','dtDtOfPolcy','txtDurationYrs','txtDurationMonths','txtInsuredAmt');
			var statusAddInsuranceDtlValidation =  validateSpecificFormFields(addInsurancsDtlArray);
			if (btnObj == 'Add')
			{
				if (statusAddInsuranceDtlValidation)
				{
					var hdnInsuranceId = 0;
					var InsuranceDtls = "InsuranceDtls";   
					addOrUpdateRecord('addEmpMiscelInsuranceRecord','addEmpMiscelBnk&hdnInsuranceId='+ hdnInsuranceId +'&flag='+ InsuranceDtls, new Array('txtPolcyNo','txtNameOfPolcy','txtCmpName','dtDtOfPolcy','txtDurationYrs','txtDurationMonths','txtInsuredAmt'));
          	    }
            	else
           	    {
            		return false;
            	}  		
			}
			else if(btnObj == 'Update')
			{
				if (statusAddInsuranceDtlValidation)
				{
					var hdnInsuranceId = document.getElementById('hdnInsuranceId').value;
					var InsuranceDtls = "InsuranceDtls";   
	  	   		    addOrUpdateRecord('updateEmpMiscelInsuranceRecord','addEmpMiscelBnk&hdnInsuranceId='+ hdnInsuranceId +'&flag='+ InsuranceDtls, new Array('txtPolcyNo','txtNameOfPolcy','txtCmpName','dtDtOfPolcy','txtDurationYrs','txtDurationMonths','txtInsuredAmt'));
	    		}
	    	}
	     }
		
	}

	function addEmpMiscelInsuranceRecord()
	{
		if (xmlHttp.readyState == 4)
		{				
			removeBlankValueForInsuranceDtls();
			document.getElementById("hdnDuration").value  = document.getElementById("txtDurationYrs").value+miscellenousAlertMsgArr[5]+ document.getElementById("txtDurationMonths").value + miscellenousAlertMsgArr[6];
			displayFieldArray1 = new Array('txtPolcyNo','txtNameOfPolcy','txtCmpName','dtDtOfPolcy','hdnDuration','txtInsuredAmt');
			addDataInTable('txnAddInsurance','encXMLIns',displayFieldArray1, 'editEmpMiscelInsuranceRecord','deleteEmpMiscelInsuranceRecord');
   	    	resetEmpInsuranceData();   			
		}	
	}	
	   
	function updateEmpMiscelInsuranceRecord() 
	{
	  	if (xmlHttp.readyState == 4) 
	  	{
	  		removeBlankValueForInsuranceDtls();
			document.getElementById("hdnDuration").value  = document.getElementById("txtDurationYrs").value+miscellenousAlertMsgArr[5]+ document.getElementById("txtDurationMonths").value + miscellenousAlertMsgArr[6];
			var displayFieldArray = new Array('txtPolcyNo','txtNameOfPolcy','txtCmpName','dtDtOfPolcy','hdnDuration','txtInsuredAmt');
			updateDataInTable('encXMLIns', displayFieldArray);		
			resetEmpInsuranceData(); 
     		document.getElementById('btnInsuranceDtlAdd').style.display='';
			document.getElementById('btnInsuranceDtlUpdate').style.display='none';	
		}
	}
	
	function removeBlankValueForInsuranceDtls()
	{
		if(document.getElementById("txtDurationYrs").value=='' || document.getElementById("txtDurationYrs").value==null)
		{
			document.getElementById("txtDurationYrs").value='0';
		}
		if(document.getElementById("txtDurationMonths").value=='' || document.getElementById("txtDurationMonths").value==null)
		{
			document.getElementById("txtDurationMonths").value='0';
		}
		if(document.getElementById("txtInsuredAmt").value=='' || document.getElementById("txtInsuredAmt").value==null)
		{
			document.getElementById("txtInsuredAmt").value='-';
		}
	}
	function deleteEmpMiscelInsuranceRecord(rowId)
	{
		deleteRecord(rowId);
	}
	
	function deleteDBEmpMiscelInsuranceRecord(rowId)
	{
		deleteDBRecord(rowId);
	}
	
	function editEmpMiscelInsuranceRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormInsurance');
		document.getElementById('btnInsuranceDtlAdd').style.display='none';
		document.getElementById('btnInsuranceDtlUpdate').style.display='';
	}	   	
	
	function populateFormInsurance() 
	{				
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
			{
			  	var decXML = xmlHttp.responseText;				  	
	  	        var xmlDOM = getDOMFromXML(decXML);
				document.getElementById('hdnInsuranceId').value = getXPathValueFromDOM(xmlDOM, 'empInsrcpolicyDtlsPk');
				
				str = getXPathValueFromDOM(xmlDOM, 'policyNumber');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtPolcyNo').value = str;
						
			    str = getXPathValueFromDOM(xmlDOM, 'nameOfPolicy');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtNameOfPolcy').value = str;	
							
				str = getXPathValueFromDOM(xmlDOM, 'insrcCompanyName');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtCmpName').value = str;	   
								
				str = getXPathValueFromDOM(xmlDOM, 'dateOfPolicy');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				var dateArray = getDateAndTimeFromDateObj(str);
				document.getElementById('dtDtOfPolcy').value = dateArray[0];
								
				str = getXPathValueFromDOM(xmlDOM, 'durationYear');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtDurationYrs').value = str;
						
				str = getXPathValueFromDOM(xmlDOM, 'durationMonth');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtDurationMonths').value = str;
						
				str = getXPathValueFromDOM(xmlDOM, 'insuredAmount');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtInsuredAmt').value = str;
			 }		   		    
		 }	
	 }
	
	function monthComparisonForInsurance()
    {
    	if(document.getElementById("txtDurationMonths").value>=0 && document.getElementById("txtDurationMonths").value<12)
		{
		}
		else
		{	
			alert(miscellenousAlertMsgArr[0]);
			document.getElementById('txtDurationMonths').value='';
			document.EmpMislenousDtls.txtDurationMonths.focus();
		}
    }
     
	function resetEmpBankData()
	{
		document.getElementById('drodnBankName').value='0';
		document.getElementById('drodnBranchName').length=1;
		document.getElementById('drodnBranchName').value='0';
		document.getElementById('txtAccNo').value='';
		document.getElementById('drodnTypeOfAcc').value='0';
		updateRow = null; 
		
		document.getElementById('btnBnkDtlAdd').style.display='';
		document.getElementById('btnBnkDtlUpdate').style.display='none';
		
		if (document.getElementById('txnAddBank') != null)
		{
			var rows = document.getElementById('txnAddBank').rows.length;
			if (rows > 1  && document.getElementById('bankNoRecords') != null)
				document.getElementById('bankNoRecords').style.display='none';
		}
	}
	
	function resetEmpInsuranceData()
	{	
		document.getElementById('txtPolcyNo').value='';
		document.getElementById('txtNameOfPolcy').value='';
		document.getElementById('txtCmpName').value='';
		document.getElementById('dtDtOfPolcy').value='';
		document.getElementById('txtDurationYrs').value='';
		document.getElementById('txtDurationMonths').value='';
		document.getElementById('txtInsuredAmt').value='';
		updateRow = null; 
		
		document.getElementById('btnInsuranceDtlAdd').style.display='';
		document.getElementById('btnInsuranceDtlUpdate').style.display='none';	
		
		if (document.getElementById('txnAddInsurance') != null)
		{
			var rows = document.getElementById('txnAddInsurance').rows.length;
			if (rows > 1 && document.getElementById('insuranceNoRecords') != null)
			document.getElementById('insuranceNoRecords').style.display='none';
		}
	}
	
	function resetEmpPassportData()
	{
		document.getElementById('txtPasprtNo').value='';
		document.getElementById('dtPasprtIsueDt').value='';
		document.getElementById('dtPasprtExpDt').value='';
		document.getElementById('txtPlaceOfIssue').value='';
		document.getElementById('txtIsungAuth').value='';
		updateRow = null;  
		
		document.getElementById('btnPassportDtlAdd').style.display='';
     	document.getElementById('btnPassportDtlUpdate').style.display='none';
     	
     	if (document.getElementById('txnAddPassport') != null)
		{
			var rows = document.getElementById('txnAddPassport').rows.length;
			if (rows > 1 && document.getElementById('passportNoRecords') != null)
			document.getElementById('passportNoRecords').style.display='none';
		}
	}

	
	function resetEmpLicenseData()
	{
		document.getElementById('txtLicenseNo').value='';
		document.getElementById('dtLicenseIsueDt').value='';
		document.getElementById('dtLicenseExpDt').value='';
		document.getElementById('txtPlaceOfIssueLicense').value='';
		document.getElementById('txtLicenseIsungAuth').value='';
		document.getElementById('drodnLicenseType').value='0';
		updateRow = null;  
		
		document.getElementById('btnLicenseDtlAdd').style.display='';
     	document.getElementById('btnLicenseDtlUpdate').style.display='none';
     	
     	if (document.getElementById('txnAddPassport') != null)
		{
			var rows = document.getElementById('txnAddLicense').rows.length;
			if (rows > 1 && document.getElementById('licenseNoRecords') != null)
			document.getElementById('licenseNoRecords').style.display='none';
		}
	}
	
//for passport---------------------------
	function dateComparisonForPassport()
	{
		if(document.getElementById("dtPasprtIsueDt").value!="" && document.getElementById("dtPasprtExpDt").value!="")
		{
			var lFrmDate=document.getElementById("dtPasprtIsueDt").value;		
			var lToDate=document.getElementById("dtPasprtExpDt").value;		
			if(compareDate(lFrmDate,lToDate) < 0 )	
			{
				alert(miscellenousAlertMsgArr[1]);
				document.getElementById('dtPasprtExpDt').value='';
				document.EmpMislenousDtls.dtPasprtExpDt.focus();
			}
		}	
	} 
	
	function dateComparisonForLicense()
	{
		if(document.getElementById("dtLicenseIsueDt").value!="" && document.getElementById("dtLicenseExpDt").value!="")
		{
			var lFrmDate=document.getElementById("dtLicenseIsueDt").value;		
			var lToDate=document.getElementById("dtLicenseExpDt").value;		
			if(compareDate(lFrmDate,lToDate) < 0 )	
			{
				alert(miscellenousAlertMsgArr[8]);
				document.getElementById('dtLicenseExpDt').value='';
				document.EmpMislenousDtls.dtLicenseExpDt.focus();
			}
		}	
	} 
	
	function addOrUpdatePassportDtls(btnObj)
	{	
	    if(btnObj == 'Reset')
		{
			resetEmpPassportData();
		}
		else
		{	
			var addPassportDtlArray= new Array('txtPasprtNo','dtPasprtIsueDt','dtPasprtExpDt');
			var statusAddPassportDtlValidation =  validateSpecificFormFields(addPassportDtlArray);	
			if (btnObj == 'Add')
			{ 
				if (statusAddPassportDtlValidation)
				{
				 	var hdnPassportId = 0;
			   	 	var PassportDtls = "PassportDtls";   
				 	addOrUpdateRecord('addEmpMiscelPassportRecord','addEmpMiscelBnk&hdnPassportId='+ hdnPassportId +'&flag='+ PassportDtls, new Array('txtPasprtNo','dtPasprtIsueDt','dtPasprtExpDt','txtPlaceOfIssue','txtIsungAuth'));
				}
				else
				{
					return false;
				}
			}
	   		else if(btnObj == 'Update')
			{
				if (statusAddPassportDtlValidation)
				{
					var hdnPassportId = document.getElementById('hdnPassportId').value;
					var PassportDtls = "PassportDtls";   
	   	   		    addOrUpdateRecord('updateEmpMiscelPassportRecord','addEmpMiscelBnk&hdnPassportId='+ hdnPassportId +'&flag='+ PassportDtls, new Array('txtPasprtNo','dtPasprtIsueDt','dtPasprtExpDt','txtPlaceOfIssue','txtIsungAuth'));
		 		}
		 		else
				{
					return false;
				}
			}
		}
	}

	function addEmpMiscelPassportRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 			
			removeBlankValueForPosport();		
			displayFieldArray1 = new Array('txtPasprtNo','dtPasprtIsueDt','dtPasprtExpDt','txtPlaceOfIssue','txtIsungAuth');
			addDataInTable('txnAddPassport','encXMLPasprt',displayFieldArray1, 'editEmpMiscelPassportRecord','deleteEmpMiscelPassportRecord');
			resetEmpPassportData();   			
		}	
	}	 
	  
	function updateEmpMiscelPassportRecord() 
	{
		if (xmlHttp.readyState == 4) 
		{
			removeBlankValueForPosport();
			var displayFieldArray = new Array('txtPasprtNo','dtPasprtIsueDt','dtPasprtExpDt','txtPlaceOfIssue','txtIsungAuth');
			updateDataInTable('encXMLPasprt', displayFieldArray);		
			resetEmpPassportData(); 
		    document.getElementById('btnPassportDtlAdd').style.display='';
		    document.getElementById('btnPassportDtlUpdate').style.display='none';	
		}
    }
    function removeBlankValueForPosport()
    {
    	if(document.getElementById("txtPlaceOfIssue").value=='' || document.getElementById("txtPlaceOfIssue").value==null)
		{
			document.getElementById("txtPlaceOfIssue").value='-';
		}
		if(document.getElementById("txtIsungAuth").value=='' || document.getElementById("txtIsungAuth").value==null)
		{
			document.getElementById("txtIsungAuth").value='-';
		}	
    }
    
	function deleteEmpMiscelPassportRecord(rowId)
	{
		var flag=deleteRecord(rowId);
	}
	
	function deleteDBEmpMiscelPassportRecord(rowId)
	{
		var flag1= deleteDBRecord(rowId);	
		if(flag1)
		{	
			trow=document.getElementById(rowId);
			trow.cells[1].innerText='0';
		}
	}
	
	function editEmpMiscelPassportRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormPassport');
		document.getElementById('btnPassportDtlAdd').style.display='none';
		document.getElementById('btnPassportDtlUpdate').style.display='';
	}	   	
	
	function populateFormPassport() 
	{				
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
			{
			  	var decXML = xmlHttp.responseText;				  	
	  	        var xmlDOM = getDOMFromXML(decXML);
	  	        var srno=getXPathValueFromDOM(xmlDOM, 'srNo');
	  	        document.getElementById('hdnPassportId').value = getXPathValueFromDOM(xmlDOM, 'srNo');
				
				str = getXPathValueFromDOM(xmlDOM, 'proofNum');						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('txtPasprtNo').value = str;
										
				str = getXPathValueFromDOM(xmlDOM, 'issueDate');						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						var dateArray = getDateAndTimeFromDateObj(str);
						document.getElementById('dtPasprtIsueDt').value = dateArray[0];
						
				str = getXPathValueFromDOM(xmlDOM, 'expiryDate');						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						var dateArray = getDateAndTimeFromDateObj(str);
						document.getElementById('dtPasprtExpDt').value = dateArray[0];
								
				str = getXPathValueFromDOM(xmlDOM, 'issuePlace');						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('txtPlaceOfIssue').value = str;
						
				str = getXPathValueFromDOM(xmlDOM, 'issueAuthority');						
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('txtIsungAuth').value = str;
		    }		   		    
		 }	
	 }
	
	
	// for license Details
	
	function addOrUpdateLicenseDtls(btnObj)
	{	
		if(btnObj == 'Reset')
		{
			resetEmpLicenseData();
		}
		else
		{	
			var addLicenseDtlArray= new Array('txtLicenseNo','drodnLicenseType','dtLicenseIsueDt','dtLicenseExpDt');
			var statusAddLicenseDtlValidation =  validateSpecificFormFields(addLicenseDtlArray);	
			if (btnObj == 'Add')
			{ 
				if (statusAddLicenseDtlValidation)
				{
					var hdnLicenseId = 0;
					var LicenseDtls = "LicenseDtls";   
					addOrUpdateRecord('addEmpMiscelLicenseRecord','addEmpMiscelBnk&hdnLicenseId='+ hdnLicenseId +'&flag='+ LicenseDtls, new Array('txtLicenseNo','drodnLicenseType','dtLicenseIsueDt','dtLicenseExpDt','txtPlaceOfIssueLicense','txtLicenseIsungAuth'));
				}
				else
				{
					return false;
				}
			}
			else if(btnObj == 'Update')
			{
				if (statusAddLicenseDtlValidation)
				{
					var hdnLicenseId = document.getElementById('hdnLicenseId').value;
					var LicenseDtls = "LicenseDtls";   
					addOrUpdateRecord('updateEmpMiscelLicenseRecord','addEmpMiscelBnk&hdnLicenseId='+ hdnLicenseId +'&flag='+ LicenseDtls, new Array('txtLicenseNo','drodnLicenseType','dtLicenseIsueDt','dtLicenseExpDt','txtPlaceOfIssueLicense','txtLicenseIsungAuth'));
				}
				else
				{
					return false;
				}
			}
		}
	}

	function addEmpMiscelLicenseRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 			
			removeBlankValueForLicense();		
			displayFieldArray1 = new Array('txtLicenseNo','drodnLicenseType','dtLicenseIsueDt','dtLicenseExpDt','txtPlaceOfIssueLicense','txtLicenseIsungAuth');
			addDataInTable('txnAddLicense','encXMLLicnes',displayFieldArray1, 'editEmpMiscelLicenseRecord','deleteEmpMiscelLicenseRecord');
			resetEmpLicenseData();   			
		}	
	}	 
	  
	function updateEmpMiscelLicenseRecord() 
	{
		if (xmlHttp.readyState == 4) 
		{
			removeBlankValueForLicense();
			var displayFieldArray = new Array('txtLicenseNo','drodnLicenseType','dtLicenseIsueDt','dtLicenseExpDt','txtPlaceOfIssueLicense','txtLicenseIsungAuth');
			updateDataInTable('encXMLLicnes', displayFieldArray);		
			resetEmpLicenseData(); 
			document.getElementById('btnLicenseDtlAdd').style.display='';
     		document.getElementById('btnLicenseDtlUpdate').style.display='none';
		}
    }
    function removeBlankValueForLicense()
    {
    	if(document.getElementById("txtPlaceOfIssueLicense").value=='' || document.getElementById("txtPlaceOfIssueLicense").value==null)
		{
			document.getElementById("txtPlaceOfIssueLicense").value='-';
		}
		if(document.getElementById("txtLicenseIsungAuth").value=='' || document.getElementById("txtLicenseIsungAuth").value==null)
		{
			document.getElementById("txtLicenseIsungAuth").value='-';
		}	
    }
    
	function deleteEmpMiscelLicenseRecord(rowId)
	{
		var flag=deleteRecord(rowId);
	}
	
	function deleteDBEmpMiscelLicenseRecord(rowId)
	{
		var flag1= deleteDBRecord(rowId);	
		if(flag1)
		{	
			trow=document.getElementById(rowId);
			trow.cells[1].innerText='0';
		}
	}
	
	function editEmpMiscelLicenseRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormLicense');
		document.getElementById('btnLicenseDtlAdd').style.display='none';
     	document.getElementById('btnLicenseDtlUpdate').style.display='';
	}	   	
	
	function populateFormLicense() 
	{				
		if (xmlHttp.readyState == 4) 
		{	 	
			if (xmlHttp.status == 200) 
			{
				var decXML = xmlHttp.responseText;				  	
				var xmlDOM = getDOMFromXML(decXML);
				var srno=getXPathValueFromDOM(xmlDOM, 'srNo');
				document.getElementById('hdnLicenseId').value = getXPathValueFromDOM(xmlDOM, 'srNo');

				str = getXPathValueFromDOM(xmlDOM, 'proofNum');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtLicenseNo').value = str;

				
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByLicenseType/lookupName');		
				if(str==null || str=='' || str=='null')
				{
					document.getElementById('drodnLicenseType').value='0';
				}
				document.getElementById('drodnLicenseType').value = str;
				
				
				
				str = getXPathValueFromDOM(xmlDOM, 'issueDate');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				var dateArray = getDateAndTimeFromDateObj(str);
				document.getElementById('dtLicenseIsueDt').value = dateArray[0];

				str = getXPathValueFromDOM(xmlDOM, 'expiryDate');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				var dateArray = getDateAndTimeFromDateObj(str);
				document.getElementById('dtLicenseExpDt').value = dateArray[0];

				str = getXPathValueFromDOM(xmlDOM, 'issuePlace');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtPlaceOfIssueLicense').value = str;

				str = getXPathValueFromDOM(xmlDOM, 'issueAuthority');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtLicenseIsungAuth').value = str;
			}		   		    
		}	
	}
	
	
	
	
	

//for bank details---------
	
	function addEmpMiscelBankRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 				
			if (xmlHttp.readyState == 4)
		    { 				
				displayFieldArray1 = new Array('drodnBankName','drodnBranchName','txtAccNo','drodnTypeOfAcc');
				addDataInTable('txnAddBank','encXMLBnk',displayFieldArray1, 'editEmpMiscelBankRecord','deleteEmpMiscelBankRecord');
	   	    	resetEmpBankData();			
		    }	
	    }
	}
		   
	function deleteEmpMiscelBankRecord(rowId)
	{
		deleteRecord(rowId);
	}
	
	function deleteDBEmpMiscelBankRecord(rowId)
	{
		deleteDBRecord(rowId);
	}	
	
	function updateEmpMiscelBankRecord() 
	{
		if (xmlHttp.readyState == 4) 
		{ 	
			var displayFieldArray = new Array('drodnBankName','drodnBranchName','txtAccNo','drodnTypeOfAcc')
			updateDataInTable('encXMLBnk', displayFieldArray);		
			resetEmpBankData(); 
	     	document.getElementById('btnBnkDtlAdd').style.display='';
			document.getElementById('btnBnkDtlUpdate').style.display='none';	
		}
	}

	function showBranchName(objBranch)
	{
		BranchValue = objBranch.value;
		try
		{
		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (e)
			{							
				return false; 
			}
		}
	    var url = "hrms.htm?actionFlag=getBranchDataByBank&flag=getBranchData&BranchValue="+ BranchValue; 	
        xmlHttp.open("POST", encodeURI(url) , true); 
    	xmlHttp.onreadystatechange = processResponseforBlocks; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); 
	}

	function processResponseforBlocks()
	{
		if (xmlHttp.readyState == 4) 
		{ 				
			if (xmlHttp.status == 200) 
			{ 					
				var xmlStr = xmlHttp.responseText; 
				var XMLDoc=getDOMFromXML(xmlStr); 
				if(XMLDoc.getElementsByTagName('option')!= null)
				{
					var branchNm=XMLDoc.getElementsByTagName('option'); 	
					var objBranchCombo = document.getElementById("BranchName");
					objBranchCombo.length = 1; 	
					for ( var iter = 0 ; iter < branchNm.length ; iter++ )
					{
						var objoption = document.createElement('option');
						objoption.text= branchNm[iter].childNodes[0] != null ? branchNm[iter].childNodes[0].text : "";
						objoption.value=branchNm[iter].getAttribute('value');
						try
						{
							objBranchCombo.add(objoption,null); 
						}
						catch(ex)
						{ 
							objBranchCombo.add(objoption); 
						}
					}
					if (branchCodeInEditMode != -1)
					{
						if (branchCodeInEditMode != null)
						{
							document.EmpMislenousDtls.drodnBranchName.value = branchCodeInEditMode;
						}
						else
							document.EmpMislenousDtls.drodnBranchName.options[0].selected = true;
							
						if (document.EmpMislenousDtls.drodnBranchName.value == '')
							document.EmpMislenousDtls.drodnBranchName.options[0].selected = true;
								
						branchCodeInEditMode = -1;
					}
				} 	
			}
		}
	}

	function addOrUpdateBankDtls(btnObj)
	{
	    if(btnObj == 'Reset')
		{
			resetEmpBankData();
		}
		else
		{
			var addBankDtlArray= new Array('drodnBankName','drodnBranchName','txtAccNo','drodnTypeOfAcc');
			var statusAddBankDtlValidation =  validateSpecificFormFields(addBankDtlArray);
			var checkAccount=true;
			
			if(statusAddBankDtlValidation) //For Salary Account Checking
			{
				var salaryType=document.getElementById("drodnTypeOfAcc").value;	
				var num = document.getElementById('txnAddBank').rows.length;	
				
				if(salaryType=='typeofacc_sal')
				{	 
					for(var i=1;i<num;i++)
					{							
						trow=document.getElementById('txnAddBank').rows[i];
						rowId=trow.id;
						
						if (updateRow!=null && rowId == updateRow)
							continue;
				
						if(trow.cells[4].innerText==miscellenousAlertMsgArr[7])
						{
							alert(miscellenousAlertMsgArr[2]);
							checkAccount=false;
							return false;
						}	
					} 	
				}
			}
			
			if(checkAccount==true)
			{
				if (btnObj == 'Add')
				{
			  		if (statusAddBankDtlValidation)
					{
						if(checkAccount!='error')
						{
							var hdnBankId = 0;
							var BankDtls = "BankDtls";   
							addOrUpdateRecord('addEmpMiscelBankRecord','addEmpMiscelBnk&hdnBankId='+ hdnBankId +'&flag='+ BankDtls, new Array('drodnBankName','drodnBranchName','txtAccNo','drodnTypeOfAcc'));
	           			}
           			}
            		else
            		{
            			return false;
            		}	
				}
				else if(btnObj == 'Update')
				{
					if (statusAddBankDtlValidation)
					{
			 			var hdnBankId = document.getElementById('hdnBankId').value;
			 			var BankDtls = "BankDtls";  
	   		 			addOrUpdateRecord('updateEmpMiscelBankRecord','addEmpMiscelBnk&hdnBankId='+ hdnBankId +'&flag='+ BankDtls, new Array('drodnBankName','drodnBranchName','txtAccNo','drodnTypeOfAcc'));
	    			}
	    			else
            		{
            			return false;
            		}	
		 		}
	 		}	
	 	}
	}

	function checkNumNCharNumber(key)
 	{
 		var num;
 		num=window.event.keyCode;
 		if(eval(num)<=47 ||eval(num)>58 && eval(num)<65 || (eval(num)>90 && eval(num)<97) || (eval(num)>122 && eval(num)<127))
 			return false;
 		else
 			return true;	
 	}

	function editEmpMiscelBankRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormBank');
		document.getElementById('btnBnkDtlAdd').style.display='none';
		document.getElementById('btnBnkDtlUpdate').style.display='';
	}	

	function populateFormBank() 
	{				
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
		 	{
		 		var decXML = xmlHttp.responseText;				  	
		 		var xmlDOM = getDOMFromXML(decXML);
		 		document.getElementById('hdnBankId').value = getXPathValueFromDOM(xmlDOM, 'bankDtlId');

		 		str = getXPathValueFromDOM(xmlDOM, 'hrEisBankMst/bankTypeCode');	
		 		if(str==null || str=='' || str=='null')
		 		{
		 			document.getElementById('drodnBankName').value='0';
		 		}	
		 		else
		 		{
		 			if(document.getElementById('drodnBankName').length==1)
		 			{
		 				document.getElementById('drodnBankName').value='0';
		 			}
		 			else
		 			{
		 				document.getElementById('drodnBankName').value=str;
		 			}
		 		}	

		 		branchCodeInEditMode = getXPathValueFromDOM(xmlDOM, 'hrEisBranchMst/branchTypeCode');	

		 		str = getXPathValueFromDOM(xmlDOM, 'bankAcctNo');

		 		if(str==null || str=='' || str=='null')
		 		{
		 			str='';
		 		}
		 		document.getElementById('txtAccNo').value = str;	   

		 		str = getXPathValueFromDOM(xmlDOM, 'bankAcctType');						
		 		if(str==null || str=='' || str=='null')
		 		{
		 			document.getElementById('drodnTypeOfAcc').value='0';
		 		}	
		 		else
		 		{
		 			var acTypeForEdit =giveLookupName(str);
		 		}	

		 		showBranchName(document.getElementById('drodnBankName'));
		 	}		   		    
		}	
	}	
	 
	function checkDecimalNumber(key)
	{
		var num;
		num = window.event.keyCode;
		if (eval(num)<=47 ||eval(num)>58)
			return false;
		else
		 	return true;
	}
	
	function openAppWindow(actionFlag)//IFMS
	{
		var userId = document.getElementById("userId").value;
		var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
		objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
	}	
	
	function checkPosportDateDtls()
	{
		var checkStatus=true;
		var delRow=0;
		var num = document.getElementById('txnAddPassport').rows.length;
		if(num>2)
		{
			for(var i=1;i<num;i++)
			{		
				trow=document.getElementById('txnAddPassport').rows[i];
				var passportNo=trow.cells[1].innerText;
				if(passportNo!='0')
				{
					var preEndDate=trow.cells[3].innerText;
					for(var j=i+1;j<num;j++)
					{
						var nextTrow=document.getElementById('txnAddPassport').rows[j];
						var nextstartDate=nextTrow.cells[2].innerText;
						if(delRow!=0)
						{
							var currRow=j-delRow;
							checkStatus=checkDateForPassport(preEndDate,nextstartDate,currRow);
						}
						else
						{
							checkStatus=checkDateForPassport(preEndDate,nextstartDate,j);
						}
						if(!checkStatus)
						{
							break;
						}
					}
				}
				else
				{
					var delRow =delRow+1;
				}
				if(!checkStatus)
				{
					return checkStatus;
				}  
			}
		}
		if(checkStatus)
			return checkStatus;
	}
	
	function checkDateForPassport(preEndDate,nextstartDate,j)
    {
  		var validDate=true;
		var lFrmDate=preEndDate	;
		var lToDate=nextstartDate;	
		if(compareDate(lFrmDate,lToDate) < 0 )
		{
			alert(miscellenousAlertMsgArr[3]+ j +miscellenousAlertMsgArr[4]);
			validDate=false;
		}
		return validDate;
	}	
	
	function checkLicenseDateDtls()
	{
		var checkLicenseStatus=true;
		var delRow=0;
		var num = document.getElementById('txnAddLicense').rows.length;
		if(num>2)
		{
			for(var i=1;i<num;i++)
			{		
				trow=document.getElementById('txnAddLicense').rows[i];
				var licenseNo=trow.cells[1].innerText;
				if(licenseNo!='0')
				{
					var preEndDate=trow.cells[4].innerText;
					for(var j=i+1;j<num;j++)
					{
						var nextTrow=document.getElementById('txnAddLicense').rows[j];
						var nextstartDate=nextTrow.cells[3].innerText;
						if(delRow!=0)
						{
							var currRow=j-delRow;
							checkLicenseStatus=checkDateForLicense(preEndDate,nextstartDate,currRow);
						}
						else
						{
							checkLicenseStatus=checkDateForLicense(preEndDate,nextstartDate,j);
						}
						if(!checkLicenseStatus)
						{
							break;
						}
					}
				}
				else
				{
					var delRow =delRow+1;
				}
				if(!checkLicenseStatus)
				{
					return checkLicenseStatus;
				}  
			}
		}
		if(checkLicenseStatus)
			return checkLicenseStatus;
	}
	
	function checkDateForLicense(preEndDate,nextstartDate,j)
    {
  		var validDateLicense=true;
		var lFrmDate=preEndDate	;
		var lToDate=nextstartDate;	
		if(compareDate(lFrmDate,lToDate) < 0 )
		{
			alert(miscellenousAlertMsgArr[3]+ j +miscellenousAlertMsgArr[9]);
			validDateLicense=false;
		}
		return validDateLicense;
	}
	
	function closeWindow()
	{
		if(workFlowEnabled=="true")
		{
			document.EmpMislenousDtls.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		   	document.EmpMislenousDtls.submit();
		}
		else
		{
			window.close();
		}
	}
	
	function giveLookupName(str)
	{
		var argument=parseInt(str);
		showLookUpName(argument);
	}
	
	function showLookUpName(argument)
	{
		try
		{
		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch (e)
			{							
				return false; 
			}
		}
	    var url = "hrms.htm?actionFlag=getLookUpNameForId&argument="+ argument;
        xmlHttp.open("POST", encodeURI(url) , false); 
    	xmlHttp.onreadystatechange = processResponseforLookUp; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null)); 
	}
	
	function processResponseforLookUp()
	{
		if (xmlHttp.readyState == 4) 
		{ 				
			if (xmlHttp.status == 200) 
			{ 					
				var xmlStr = xmlHttp.responseText; 
				var XMLDoc=getDOMFromXML(xmlStr); 
				if(XMLDoc.getElementsByTagName('option')!= null)
				{
					document.EmpMislenousDtls.drodnTypeOfAcc.value = XMLDoc.getElementsByTagName('lookupName')[0].childNodes[0].text
				}	
			}
		}
	} 