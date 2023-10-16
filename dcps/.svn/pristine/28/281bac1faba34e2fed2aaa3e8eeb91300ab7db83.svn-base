var gRowCount = 0;


cmbmontypeOption1 = "";
cmbyeartypeOption1 = "";
 var months = new Array('January','February','March','April','May',
      'June','July','August','September','October',
      'November','December');
function showTiRateType(lthis)
{
	if(lthis.value=='TI')
	{
		document.getElementById("tiRateTypeId").style.display = 'block';
	}
	else
	{
		document.getElementById("tiRateTypeId").style.display = 'none';
	}
}

function showForPensionCmb(lthis)
{
	if(lthis.value=='DA_1986')
	{
		document.getElementById("tiForPensionId1").style.display = 'block';
		document.getElementById("tiForPensionId2").style.display = 'block';
	}
	else
	{
		document.getElementById("tiForPensionId1").style.display = 'none';
		document.getElementById("tiForPensionId2").style.display = 'none';
		document.getElementById("OldMinAmt1").style.display = 'none';
		document.getElementById("OldMinAmt2").style.display = 'none';
		document.getElementById("NewMinAmt1").style.display = 'none';
		document.getElementById("NewMinAmt2").style.display = 'none';
	}
}

function showMinAmt(lthis)
{
	if(lthis.value=='UPTO 3000' || lthis.value=='ABOVE 3001' || lthis.value=='IR')
	{
		document.getElementById("OldMinAmt1").style.display = 'block';
		document.getElementById("OldMinAmt2").style.display = 'block';
		document.getElementById("NewMinAmt1").style.display = 'block';
		document.getElementById("NewMinAmt2").style.display = 'block';
	}
	else
	{
		document.getElementById("OldMinAmt1").style.display = 'none';
		document.getElementById("OldMinAmt2").style.display = 'none';
		document.getElementById("NewMinAmt1").style.display = 'none';
		document.getElementById("NewMinAmt2").style.display = 'none';
	}
}

function chkValidation(selValue)
	{
		if(selValue == "")
		{
			alert(ADM_CONFIG);
			document.getElementById("cmbStateCode").value = "-1";
			return false;
		}
		if(selValue == "TI" && document.getElementById("TIRateType").value == "-1")
		{
			alert(ADM_TITYPE);
			document.getElementById("cmbStateCode").value = "-1";
			document.getElementById("TIRateType").focus();
			return false;
		}
		if(selValue == "TI" && document.getElementById("TIRateType").value == "TI Rate Continue From ROP 1986" && document.getElementById("ForPension").value == "-1")
		{
			alert(ADM_PENSION);
			document.getElementById("cmbStateCode").value = "-1";
			document.getElementById("ForPension").focus();
			return false;
		}
		/*if(document.getElementById("cmbHeadCode").value == "16" || document.getElementById("cmbHeadCode").value == "17" || document.getElementById("cmbHeadCode").value == "18" || document.getElementById("cmbHeadCode").value == "19")
		{
			if(selValue == "MA" || selValue == "IR" || selValue == "DP")
			{
				alert("MA/DP/IR change not applicable for this headcode");
				document.getElementById("cmbHeadCode").focus();
				return false;
			}
		}*/
		return true;
	}

/*function getDataFromHeadcode()
	{
		
		var x = document.getElementsByName("radioConfig");
		var selValue = "";
		for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
		{
			if(x[i].checked)
			{
				selValue = x[i].value;
			}
		}
		
		if(document.getElementById("cmbHeadCode").value == "-1")
		{
			document.getElementById("txtHeadcodeDesc").value = "";
			document.getElementById("txtOldRate").value = "";
			//document.getElementById("txtOldMinAmt").value = "";
		}
		else
		{
			
			if(chkValidation(selValue))
			{
				xmlHttp=GetXmlHttpObject();
				if (xmlHttp==null)
				{
					alert (AL_AJAX);
					return;
				} 
				var uri = "ifms.htm?actionFlag=getDataFromHeadcode&HeadCode="+document.getElementById("cmbHeadCode").value+"&ConfigType="+selValue+"&TIRateType="+document.getElementById("TIRateType").value+"&ForPension="+document.getElementById("ForPension").value;
				xmlHttp.onreadystatechange=getData;
				xmlHttp.open("POST",uri,true);
				xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				
				xmlHttp.send(uri);
				document.getElementById("txtHeadcodeDesc").disabled="disabled";
				document.getElementById("txtOldRate").disabled="disabled";
				//document.getElementById("txtOldMinAmt").disabled="disabled";
			}
		}
	}*/
function getDataFromStateCode()
{
	var x = document.getElementsByName("radioConfig");
	var selValue = "";
	for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
	{
		if(x[i].checked)
		{
			selValue = x[i].value;
		}
	}
	if(document.getElementById("cmbStateCode").value == "-1")
	{
		document.getElementById("txtHeadcodeDesc").value = "";
		document.getElementById("txtOldRate").value = "";
		//document.getElementById("txtOldMinAmt").value = "";
	}
	else
	{
		
		if(chkValidation(selValue))
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert (AL_AJAX);
				return;
			} 
			var uri = "ifms.htm?actionFlag=getDataFromHeadcode&HeadCode="+document.getElementById("cmbStateCode").value+"&ConfigType="+selValue+"&TIRateType="+document.getElementById("TIRateType").value+"&ForPension="+document.getElementById("ForPension").value;
			xmlHttp.onreadystatechange=getData;
			xmlHttp.open("POST",uri,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			
			xmlHttp.send(uri);
			document.getElementById("txtHeadcodeDesc").disabled="disabled";
			document.getElementById("txtOldRate").disabled="disabled";
			//document.getElementById("txtOldMinAmt").disabled="disabled";
		}
	}

}
	function getData()
	{
		if (xmlHttp.readyState==complete_state)
		{
			 XMLDoc = xmlHttp.responseXML.documentElement;	
			 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');	
			 document.getElementById("txtHeadcodeDesc").value = XmlHiddenValues[0].childNodes[0].text.replace('~','&');
			 var flag = XmlHiddenValues[0].childNodes[1].text;
			 if(flag == 'Y')
			 {
				 
				 /*0-desc
				 1-flag
				 2-rate
				 3-tablepk
				 4-tablenewpk
				 5-effectdat*/
			
			 	 document.getElementById("txtOldRate").value = XmlHiddenValues[0].childNodes[2].text;
			 	 //alert('XmlHiddenValues[0].childNodes[3].text is ' + XmlHiddenValues[0].childNodes[3].text);
				// document.getElementById("txtOldMinAmt").value = XmlHiddenValues[0].childNodes[3].text;
				 document.getElementById("oldtxtEffectedDate").value = XmlHiddenValues[0].childNodes[5].text;
				 document.getElementById("hidPKTable").value = XmlHiddenValues[0].childNodes[3].text;
				 document.getElementById("hidTableNewPK").value = XmlHiddenValues[0].childNodes[4].text;
				 
			 }
			 else if(flag == 'N')
			 {
			 	alert("There is no corresponding record for this combination");
			 	document.getElementById("txtOldRate").removeAttribute('disabled');
			 	//document.getElementById("txtOldMinAmt").removeAttribute('disabled');
			 	document.getElementById("txtOldRate").value = "";
				document.getElementById("txtOldMinAmt").value = "";
				document.getElementById("hidPKTable").value = "";
			 	document.getElementById("cmbStateCode").focus();
			 }
			 
		}
	}
	
	function addRow(tabId)
    {
    	var noOfRows = document.getElementsByName("PayFromMM").length;		  
		var lastRow = noOfRows+3;	
		var newRow = document.all(tabId).insertRow(lastRow);
		
		var cell1 = newRow.insertCell(); 
		var cell2 = newRow.insertCell();
		var cell3 = newRow.insertCell();
		var cell4 = newRow.insertCell();	
		var cell5 = newRow.insertCell();
		var cell6 = newRow.insertCell(); 
		var cell7 = newRow.insertCell();		  		  
		
		
		cell1.innerHTML = '<select name="PayFromMM" onchange="checkDateValidation()" id="PayFromMM'+noOfRows+'" >' + cmbmontypeOption1 + '</select>';
		cell2.innerHTML = '<select name="PayFromYYYY" onchange="checkDateValidation()" id="PayFromYYYY'+noOfRows+'" >'+ cmbyeartypeOption1 +'</select>';
		cell3.innerHTML = '<select name="PayToMM" onchange="checkDateValidation()" id="PayToMM'+noOfRows+'" >' + cmbmontypeOption1 + '</select>';
		cell4.innerHTML = '<select name="PayToYYYY" onchange="checkDateValidation()" id="PayToYYYY'+noOfRows+'" >'+ cmbyeartypeOption1 +'</select>';
		cell5.innerHTML = '<select name="PayInMM" onchange="checkDateValidation()" id="PayInMM'+noOfRows+'" >' + cmbmontypeOption1 + '</select>';
		cell6.innerHTML = '<select name="PayInYYYY" onchange="checkDateValidation()" id="PayInYYYY'+noOfRows+'" >'+ cmbyeartypeOption1 +'</select>';
			  
		if(noOfRows == 0){
			cell7.innerHTML = '';
		}
		else{
			cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveRow(this, \'headerTable\')"/>';
		}
		
   		document.getElementById('PayFromMM'+noOfRows).focus();
	   	
    }
    
	function RemoveRow(obj, tblId)
	{	   	 	
		if(window.confirm(PEN_DELETEOPTION))
		{
			var rowID = showRowCell(obj);            
		    var tbl = document.getElementById(tblId);    
		    tbl.deleteRow(rowID); 
			return true;
		}
		else
		{
			return false;
		}		
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
	
	function pageClose()
	{
		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
		document.forms[0].submit();
	}
	
	function chkNewRateAmnt()
	{
		var oldRate  = document.getElementById("txtOldRate").value;
		var newRate  = document.getElementById("txtNewRate").value;
		
		if(oldRate!= '' && newRate!='')
		{
			if(Number(oldRate) >= Number(newRate))
			{
				alert("New Rate should be greater than the Old Rate.")
				document.getElementById("txtNewRate").value = '';
				document.getElementById("txtNewRate").focus();
				return false;
			}
		}
		if((document.getElementById("txtOldMinAmt")) && (document.getElementById("txtNewMinAmt")))
		{
			var oldMinAmnt  = document.getElementById("txtOldMinAmt").value;
			var newMinAmnt  = document.getElementById("txtNewMinAmt").value;
			if(oldMinAmnt!= '' && newMinAmnt!='')
			{
				if(Number(oldMinAmnt) >= Number(newMinAmnt))
				{
					alert("New Minimum Amount should be greater than the Old Minimum Amount.")
					document.getElementById("txtNewMinAmt").value = '';
					document.getElementById("txtNewMinAmt").focus();
					return false;
				}
			}
		}
		return true;
	}
	
	
	function greaterEffDate()
	{
		var fromMonth = document.getElementsByName("PayFromMM");
		var fromYear =document.getElementsByName("PayFromYYYY");
		var toMonth = document.getElementsByName("PayToMM");
		var toYear = document.getElementsByName("PayToYYYY");
		var inMonth = document.getElementsByName("PayInMM");
		var inYear = document.getElementsByName("PayInYYYY");
		
		var effDate = document.getElementById("txtEffectedDate").value;
		var oldEffDate = document.getElementById("hidOldEffecDate").value;
		var x = new String(effDate).split("/");
		var effMonth = x[1];
		var effYear = x[2];
		
		if(isProper1(oldEffDate,effDate) == false)
		{
			alert("Please enter Effective Date greater than the previous effective Date");
			return false;
		}
		for(var i=0;i<fromMonth.length;i++)	
		{	
			if(Number(fromYear[i].value) < effYear)
			{
				alert(ADM_EFFDATEVALIDATION);
				return false;
				
			}
			if(Number(toYear[i].value) < effYear)
			{
				alert(ADM_EFFDATEVALIDATION);
				return false;
			}
			if(Number(inYear[i].value) < effYear)
			{
				alert(ADM_EFFDATEVALIDATION);
				return false;
			}
			
			if(Number(fromYear[i].value) == Number(effYear))
			{
				if(Number(fromMonth[i].value) < Number(effMonth))
				{	
					if(fromMonth[i].value !='-1' && effMonth!='-1')
					{				
						alert(ADM_EFFDATEVALIDATION);
						return false;
					}
				}
			}		
			if(Number(toYear[i].value) == Number(effYear))
			{
				if(Number(toMonth[i].value) < Number(effMonth))
				{
					if(fromMonth[i].value !='-1' && effMonth!='-1')
					{					
						alert(ADM_EFFDATEVALIDATION);
						return false;
					}
				}
			}		
			if(Number(inYear[i].value) == Number(effYear))
			{
				if(Number(inMonth[i].value) < Number(effMonth))
				{	
					if(fromMonth[i].value !='-1' && effMonth!='-1')
					{				
						alert(ADM_EFFDATEVALIDATION);
						return false;
					}
				}
			}					
		}
		return true;			
	}
	
	/*validating date*/
	function checkDateValidation()
	{
		var fromMonth = document.getElementsByName("PayFromMM");
		var fromYear =document.getElementsByName("PayFromYYYY");
		var toMonth = document.getElementsByName("PayToMM");
		var toYear = document.getElementsByName("PayToYYYY");
		var inMonth = document.getElementsByName("PayInMM");
		var inYear = document.getElementsByName("PayInYYYY");
		
		for(var i=0;i<fromMonth.length;i++)	
		{	
			
			if(fromMonth.length>1 && i >1)
			{
			    // if 2nd row is added then from month of the second row should be greater the to month of the previos row.
				if(Number(fromYear[i].value) < Number(toYear[i-1].value))
				{
					return false;
					
				}
				
				if(Number(fromYear[i].value) == Number(toYear[i-1].value))
				{
					if(Number(fromMonth[i].value) < Number(toMonth[i-1].value))
					{	
						if(fromMonth[i].value !='-1' && toMonth[i].value!='-1')
						{				
							return false;
						}
					}
				}		

			}
			
			// validation within the same row.
			if(Number(fromYear[i].value) > Number(toYear[i].value))
			{
				alert(ADM_DATEVALIDATION);
				return false;
				
			}
			else if(Number(toYear[i].value) > Number(inYear[i].value))
			{
				alert(ADM_DATEVALINPAYMONTH);
				return false;
			}
			
			if(Number(fromYear[i].value) == Number(toYear[i].value))
			{
				if(Number(fromMonth[i].value) > Number(toMonth[i].value))
				{	
					if(fromMonth[i].value !='-1' && toMonth[i].value!='-1')
					{				
						alert(ADM_DATEVALIDATION);
						return false;
					}
				}
			}		
			if(Number(toYear[i].value) == Number(inYear[i].value))
			{
				if(Number(toMonth[i].value) >= Number(inMonth[i].value))
				{	
					if(toMonth[i].value !='-1' && inMonth[i].value!='-1')
					{			
						alert(ADM_DATEVALINPAYMONTH);
						return false;
					}
				}
			}					
		}
		return true;			
	}
	
	function updateTIVar(lthis)
	{
		var x = document.getElementsByName("radioConfig");
		var selValue = "";
		for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
		{
			if(x[i].checked)
			{
				selValue = x[i].value;
			}
		}
		
		if(selValue == "MA" || selValue == "IR" || selValue == "DP" || selValue == "TI")
		{
			document.getElementById("TIRateType").value = "-1";
			document.getElementById("ForPension").value = "-1";
			document.getElementById("cmbStateCode").value = "-1";
			document.getElementById("txtHeadcodeDesc").value = "";
			document.getElementById("txtOldRate").value = "";
			//document.getElementById("txtOldMinAmt").value = "";
		}
	}
	
	function chkFormValidation()
	{
		if(chkValidation())
		{
			if(document.getElementById("cmbStateCode").value == "-1")
			{
				alert(ADM_HEADCODE);
				document.getElementById("cmbStateCode").focus();
				return false;
			}
			if(document.getElementById("txtEffectedDate").value == "")
			{
				alert(ADM_EFFDATE);
				document.getElementById("txtEffectedDate").focus();
				return false;
			}
			if(document.getElementById("txtNewRate").value == "")
			{
				alert("Please Enter New Rate/Amount.");
				document.getElementById("txtNewRate").focus();
				return false;
			}
		}
		return true;
	}
	
	function saveData()
	{
		var oldRate  = '';
		var newRate = '';
		var oldMinAmnt  = '';
		var newMinAmnt  ='';
		var fromMonth = document.getElementsByName("PayFromMM");
		var fromYear =document.getElementsByName("PayFromYYYY");
		var toMonth = document.getElementsByName("PayToMM");
		var toYear = document.getElementsByName("PayToYYYY");
		var inMonth = document.getElementsByName("PayInMM");
		var inYear = document.getElementsByName("PayInYYYY");
		var selValue = '';
	    var x = document.getElementsByName("radioConfig");
		
	    if (document.getElementById("txtOldRate").value == '') {
	    	alert('Please select proper pensioner\'s category');
	    	return false;
	    }
		var frmMnth = months[Number(fromMonth[0].value)-1]
		var toMnth = months[Number(toMonth[0].value)-1]
		var inMnth = months[Number(inMonth[0].value)-1]
		
		for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
		{
			if(x[i].checked)
			{
				selValue = x[i].value;
			}
		}
		if(chkFormValidation() && greaterEffDate() && chkNewRateAmnt() && checkDateValidation())
		{
			if(document.getElementById("txtOldRate") && document.getElementById("txtNewRate"))
			{
				if(document.getElementById("txtOldRate").value!= '' && document.getElementById("txtNewRate")!='')
				{
					 oldRate  = document.getElementById("txtOldRate").value;
					 newRate  = document.getElementById("txtNewRate").value;
				}
			}
			if(document.getElementById("txtOldMinAmt") && document.getElementById("txtNewMinAmt"))
			{
	
				if(document.getElementById("txtOldMinAmt").value!= '' && document.getElementById("txtNewMinAmt").value!='')
				{
						 oldMinAmnt  = document.getElementById("txtOldMinAmt").value;
						 newMinAmnt  = document.getElementById("txtNewMinAmt").value;
				}
			}
			if(oldRate!='' && newRate!='' && oldMinAmnt!='' && newMinAmnt!='') 
			{
				if(window.confirm("You are going to change the DA rate Minimum amount from "+oldMinAmnt+" to "+newMinAmnt+
		    		  ".0 and rate from "+oldRate+" to "+newRate+".0 for statecode "+document.getElementById("cmbStateCode").value+
		    		  ". It will be effective from "+ document.getElementById("txtEffectedDate").value+".\nArrears from "+
		    		   frmMnth+"-"+fromYear[0].value+ " to "+toMnth+"-"+toYear[0].value+ " will be paid in the month "+inMnth+"-"+inYear[0].value+
		    		  " \nPress OK to continue and Cancel to Quit."))
		    	{
					saveAdminData();	
		    	}
		    	else
		    	{
		    		document.getElementById("txtOldRate").value = '';
		    		document.getElementById("txtNewRate").value = '';
		    		document.getElementById("txtOldMinAmt").value = '';
		    		document.getElementById("txtNewMinAmt").value = '';
		    		document.getElementById("cmbStateCode").value = -1;
		    		return false;
		    	}
		    		  
			}
			 else
			 if(oldRate!='' && newRate!='')
			 {
		 		 if(window.confirm("You are going to change the DA rate from "+oldRate+" to "+newRate+".0 for statecode "+document.getElementById("cmbStateCode").value+
			    		 ". It will be effective from "+ document.getElementById("txtEffectedDate").value+".\nArrears from "+
			    		 frmMnth+"-"+fromYear[0].value+ " till "+toMnth+"-"+toYear[0].value+ " will be paid in the month "+inMnth+"-"+inYear[0].value+
			    		 " \nPress OK to continue and Cancel to Quit."))
			    {
					saveAdminData();	
		    	}
		    	else
		    	{
		    		document.getElementById("txtOldRate").value = '';
		    		document.getElementById("txtNewRate").value = '';
		    		document.getElementById("txtOldMinAmt").value = '';
		    		document.getElementById("txtNewMinAmt").value = '';
		    		document.getElementById("cmbStateCode").value = -1;
		    		return false;
		    	}
			 }
			 else
			 if(oldMinAmnt!='' && newMinAmnt!='')
			 {
		 		 if(window.confirm("You are going to change the DA rate from "+oldMinAmnt+" to "+newMinAmnt+".0 for statecode "+document.getElementById("cmbStateCode").value+
		    		   " .It will be effective from "+ document.getElementById("txtEffectedDate").value+".\nArrears from "+
		    		    frmMnth+"-"+fromYear[0].value+ " till "+toMnth+"-"+toYear[0].value+ " will be paid in the month "+inMnth+"-"+inYear[0].value+
		    		    " \nPress OK to continue and Cancel to Quit."))
		    	{
					saveAdminData();	
		    	}
		    	else
		    	{
		    		document.getElementById("txtOldRate").value = '';
		    		document.getElementById("txtNewRate").value = '';
		    		document.getElementById("txtOldMinAmt").value = '';
		    		document.getElementById("txtNewMinAmt").value = '';
		    		document.getElementById("cmbStateCode").value = -1;
		    		return false;
		    	}
			 }
		  
		}
	}
	
	function saveAdminData()
	{
		var x = document.getElementsByName("radioConfig");
		var selValue = "";
		for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
		{
			if(x[i].checked)
			{
				selValue = x[i].value;
			}
		}
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert (AL_AJAX);
			return;
		} 
		if(selValue == 'TI' && document.getElementById("TIRateType").value == 'Revised TI After DP Merge')
		{
			var uri = "ifms.htm?actionFlag=checkHeadQueue&HeadCode="+document.getElementById("cmbStateCode").value;
			xmlHttp.onreadystatechange=getCheckResponse;
			xmlHttp.open("POST",uri,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send();
		}
		else
		{
			showProgressbar();
			saveAfterValidation();
		}
	}
	
	function getCheckResponse()
	{
		if (xmlHttp.readyState==complete_state)
		{
			 XMLDoc = xmlHttp.responseXML.documentElement;	
			 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');	
			 
			 if(XmlHiddenValues[0].childNodes[0].text != '1')
			 {
			 	showProgressbar();
			 	saveAfterValidation();
			 }
			 else
			 {
			 	alert("Operation is in process for State Code " + document.getElementById("cmbStateCode").value +
			 	   		"\n Please wait for some time.");
			 }
		}
	}	
	
	function saveAfterValidation()
	{
		var x = document.getElementsByName("radioConfig");
		var selValue = "";
		for(var i=0 ; i < document.getElementsByName("radioConfig").length;i++)
		{
			if(x[i].checked)
			{
				selValue = x[i].value;
			}
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert (AL_AJAX);
			return;
		} 
		
		var url = run();
		var uri = "ifms.htm?actionFlag=saveAdminScreen&&Fieldtype="+selValue+"&&OldRate="+document.getElementById("txtOldRate").value+"&&OldMinAmt="+document.getElementById("txtOldMinAmt").value+"&&TabPK="+document.getElementById("hidPKTable").value;
		url = uri + url;
		xmlHttp.onreadystatechange=getFinalResponse;
		xmlHttp.open("POST",uri,true);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		document.getElementById("btnSave").disabled = true;
		xmlHttp.send(url);
	}
	
	function getFinalResponse()
	{
		if (xmlHttp.readyState==complete_state)
		{
			 hideProgressbar();
			
			 XMLDoc = xmlHttp.responseXML.documentElement;	
			 var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');	
			 
			 alert(XmlHiddenValues[0].childNodes[0].text);
			 
			 document.forms[0].action = 'ifms.htm?actionFlag=loadAdminRateMst';
			 document.forms[0].submit();
		}
	}
	
	function getDataFromTI()
	{
		if(document.getElementById("TIRateType").value == "DA_1986" && document.getElementById("ForPension").value == "-1")
		{
			/*alert("Please select Pension for which change to be made");
			document.getElementById("ForPension").focus();*/
			return;
		}
		if(document.getElementById("cmbStateCode").value == "-1")
		{
			/*alert("Please select Headcode");
			document.getElementById("cmbHeadCode").focus();*/
			return;
		}	
		else 
		{
			getDataFromStatecode();
		}
	}
	
	function getDataFromPension()
	{
		if(document.getElementById("TIRateType").value == "-1")
		{
			alert("Please select DA Rate type for which change to be made");
			document.getElementById("TIRateType").focus();
			return;
		}
		if(document.getElementById("TIRateType").value == "DA_1986" && document.getElementById("ForPension").value == "-1")
		{
			alert("Please select Pension for which change to be made");
			document.getElementById("ForPension").focus();
			return;
		}
		if(document.getElementById("cmbStateCode").value == "-1")
		{
			/*alert("Please select Headcode");
			document.getElementById("cmbHeadCode").focus();*/
			return;
		}	
		else 
		{
			getDataFromStatecode();
		}
	}
	
	function setDefaultMonth(lDate)
	{
		document.getElementById("PayFromMM1").value = (lDate.value).substring(3,5) * 1;
		document.getElementById("PayFromYYYY1").value = (lDate.value).substring(6);
		document.getElementById("PayFromMM1").disabled = true;
		document.getElementById("PayFromYYYY1").disabled = true;
	}
	
	function viewDaRateHistory()
	{
		if(document.getElementById("TIRateType").value == -1)
		{
				alert('Please select DA rate type to proceed ahead.');
				return;
		}
		if(document.getElementById("TIRateType").options[document.getElementById("TIRateType").selectedIndex].text == 'DA_1986')
		{
			if(document.getElementById("ForPension").value == -1)
			{
				alert('Please select for pension to proceed ahead.');
				return;
			}
		}
		if(document.getElementById("cmbStateCode").value == -1)
		{
			alert('Please select state code type to proceed ahead.');
			return;
		}
		var forPension="";
		var TIRateTypeText = document.getElementById("TIRateType").options[document.getElementById("TIRateType").selectedIndex].text;
		var headCodeTypeText = document.getElementById("cmbStateCode").options[document.getElementById("cmbStateCode").selectedIndex].text;
		
		if(document.getElementById("ForPension").value != -1)
		{
			forPension = document.getElementById("ForPension").options[document.getElementById("ForPension").selectedIndex].text;
		}
		
		var headCodeType = document.getElementById("cmbStateCode").value;
		var url = "ifms.htm?actionFlag=loadDARateHistoryConfig&TIRateTypeText="+TIRateTypeText+"&headCodeTypeText="+headCodeTypeText+"&forPension="+forPension+"&headCodeType="+headCodeType+"&History=Y";
		var newWindow = null;
	   	var height = screen.height - 200;
	   	var width = screen.width - 200 ;
	   	var urlstring = url;
	   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=100,left=100";
	   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
	   	
	}
	
	function AdminRateAddRow()
	{

		if(document.getElementById("TIRateType").value == -1)
		{
				alert('Please select DA rate type to proceed ahead.');
				return;
		}
		else
		{
			document.getElementById("hidTIRateTypeText").value = document.getElementById("TIRateType").options[document.getElementById("TIRateType").selectedIndex].text;
		}
		if(document.getElementById("TIRateType").options[document.getElementById("TIRateType").selectedIndex].text == 'DA_1986')
		{
			if(document.getElementById("ForPension").value == -1)
			{
				alert('Please select for pension to proceed ahead.');
				return;
			}
			else
			{
				document.getElementById("hidForPensionText").value = document.getElementById("ForPension").options[document.getElementById("ForPension").selectedIndex].text;
			}
		}
		if(document.getElementById("cmbStateCode").value == -1)
		{
			alert('Please select state code type to proceed ahead.');
			return;
		}
		else
		{
			document.getElementById("hidHeadCode").value = document.getElementById("cmbStateCode").value;
		}
     	var rowCnt = document.getElementById("hidDARateGridSize").value;
     	var newRow =  document.getElementById("tblAdminRateHisotry").insertRow(document.getElementById("tblAdminRateHisotry").rows.length);	
		var Cell1 = newRow.insertCell(-1);
		Cell1.className = "tds";
	   	Cell1.align="center";
   		var Cell2 = newRow.insertCell(-1);
   		Cell2.className = "tds";
	   	Cell2.align="center";
   		var Cell3 = newRow.insertCell(-1);
   		Cell3.className = "tds";
	   	Cell3.align="center";
   		var Cell4 = newRow.insertCell(-1);
   		Cell4.className = "tds";
	   	Cell4.align="center";
	   	var Cell5 = newRow.insertCell(-1);
   		Cell5.className = "tds";
	   	Cell5.align="center";
   		
	   	
   		
	   	Cell1.innerHTML = '<input type="text" class="'+Number(rowCnt)+'" name="txtFromDate"  id="txtFromDate'+Number(rowCnt)+'" style="width:120px"  onkeypress="digitFormat(this);dateFormat(this);" onblur="validatewithEffFromDate('+Number(rowCnt)+');chkDateIsOverLapOrNot('+Number(rowCnt)+');validateWithPreviuosRowToDate('+Number(rowCnt)+',this)"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(event,\'txtFromDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
   		Cell2.innerHTML = '<input type="text" class="'+Number(rowCnt)+'"name="txtToDate"  id="txtToDate'+Number(rowCnt)+'" style="width:120px"  onkeypress="digitFormat(this);dateFormat(this);" onblur="validatewithEffFromDate('+Number(rowCnt)+');chkDateIsOverLapOrNot('+Number(rowCnt)+');validateWithFromDate('+Number(rowCnt)+');"  maxlength="10"  class="texttag, textString"  size="10" value="" tabindex="37"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" id="imgDateOfEventTo'+Number(rowCnt)+'" style="width:16px" onClick="window_open(event,\'txtToDate'+Number(rowCnt)+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" />';
   		Cell3.innerHTML = '<input type="text" name="txtDARate" id="txtDARate'+Number(rowCnt)+'" size="30" maxlength="7" onkeypress="numberFormat(event);"   style="width:120px" />';
   		if(document.getElementById("TIRateType").options[document.getElementById("TIRateType").selectedIndex].text == 'DA_1986' && document.getElementById("ForPension").options[document.getElementById("ForPension").selectedIndex].text != 'UPTO 1750')
   		{
   			Cell4.innerHTML = '<input type="text" name="txtMinAmnt" id="txtMinAmnt'+Number(rowCnt)+'" size="30" onkeypress="numberFormat(event);"   style="width:120px;text-align: right" />';
   		}
   		else
   		{
   			Cell4.innerHTML = '<input type="text" name="txtMinAmnt" id="txtMinAmnt'+Number(rowCnt)+'" size="30" onkeypress="numberFormat(event);"   readOnly="readOnly" style="width:120px;text-align: right" />';
   		}
   		
   		Cell5.innerHTML = '<img name="Image" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblAdminRateHisotry")\'/>';
   		gRowCount  = Number(rowCnt);
   		document.getElementById("hidDARateGridSize").value = Number(rowCnt)+1;
   		document.getElementById("TIRateType").disabled =true;
   		document.getElementById("ForPension").disabled =true;
   		document.getElementById("cmbStateCode").disabled =true;
	   	   		
   		
	}
	
	function RemoveTableRow(obj, tblId)
	{
		var rowID = showRowCell(obj); 
		var tbl = document.getElementById(tblId); 
		tbl.deleteRow(rowID); 
	}
	function validateWithFromDate(Count)
	{
		var fromDate = document.getElementById("txtFromDate"+Count).value;
		var toDate = document.getElementById("txtToDate"+Count).value;
		
		var fromDateArr = fromDate.split("/");
		var fromDateDay = fromDateArr[0];
		var fromDateMonth = fromDateArr[1];
		var fromDateYear = fromDateArr[2];
		
		var toDateArr = toDate.split("/");
		var toDateDay = toDateArr[0];
		var toDateMonth = toDateArr[1];
		var toDateYear = toDateArr[2];
		
		if(Number(toDateYear) < Number(fromDateYear))
		{
			alert('Effective To Date should be greater than  Effective From Date.');
			document.getElementById("txtToDate"+Count).value ="";
			return;
			
		}
		else(Number(toDateYear) == Number(fromDateYear))
		{
			if(Number(fromDateMonth) > Number(toDateMonth))
			{
				alert('Effective To Date should be greater than  Effective From Date.');
				document.getElementById("txtToDate"+Count).value ="";
				return;
			}
			else(Number(toDateMonth) == Number(fromDateMonth))
			{
				if(Number(fromDateDay) > Number(toDateDay))
				{
					alert('Effective To Date should be greater than  Effective From Date.');
					document.getElementById("txtToDate"+Count).value ="";
					return;
				}
				if(Number(fromDateDay) == Number(toDateDay))
				{
					alert('Effective To Date should be greater than  Effective From Date.');
					document.getElementById("txtToDate"+Count).value ="";
					return;
				}
			}
		}
		
		
	}
	function validateWithPreviuosRowToDate(Count,Object)
	{
		
		var previousEffctvToDt = document.getElementsByName("txtToDate");
		var effctvFromDt = document.getElementsByName("txtFromDate");
		var tempArr = new Array();
		var tempArrCnt = 0;
		var previousEffctvToDtLength = previousEffctvToDt.length;
		var effctvFromDtLength = effctvFromDt.length;
		for(var i=0;i<previousEffctvToDtLength;i++)
		{
			if(previousEffctvToDt[i].value != "")
			{
				tempArr[tempArrCnt]= previousEffctvToDt[i].value;
				tempArrCnt++;
			}
			
		}
		var fromDate = document.getElementById(Object.id).value;
		var tempval = tempArr[tempArr.length-1];
		var previousToDate = tempval ;
		var fromDateArr = fromDate.split("/");
		var fromDateDay = fromDateArr[0];
		var fromDateMonth = fromDateArr[1];
		var fromDateYear = fromDateArr[2];
		
		var previousToDateArr = previousToDate.split("/");
		var previousToDateDay = previousToDateArr[0];
		var previousToDateMonth = previousToDateArr[1];
		var previousToDateYear = previousToDateArr[2];
		
		if(Number(fromDateYear) < Number(previousToDateYear))
		{
			alert('Effective From Date should be greater than  previous Effective To Date.');
			document.getElementById(Object.id).value="";
			return;
			
			
		}
		else(Number(fromDateYear) == Number(previousToDateYear))
		{
			if(Number(previousToDateMonth) > Number(fromDateMonth))
			{
				alert('Effective From Date should be greater than  previous Effective To Date.');
				document.getElementById(Object.id).value="";
				return;
			}
			else(Number(fromDateMonth) == Number(previousToDateMonth))
			{
				if(Number(previousToDateDay) > Number(fromDateDay))
				{
					alert('Effective From Date should be greater than  previous Effective To Date.');
					document.getElementById(Object.id).value="";
					return;
				}
				if(Number(previousToDateDay) == Number(fromDateDay))
				{
					alert('Effective From Date should be greater than  previous Effective To Date.');
					document.getElementById(Object.id).value="";
					return;
				}
			}
		}
		
		
	}
	function validatewithEffFromDate(Count)
	{
		var fromDate = document.getElementById("txtFromDate"+Count);
		var effFromDate = document.getElementById("oldtxtEffectedDate");
		if(compareTwoDates(fromDate,effFromDate,">") == true || compareTwoDates(fromDate,effFromDate,"=") == true)
		{
			alert('Please enter from date which is lesser than effective from date.');
			document.getElementById("txtFromDate"+Count).value="";
			document.getElementById("txtFromDate"+Count).focus();
			return;
		}
	}
	function chkDateIsOverLapOrNot(Count)
	{
		var fromDate = document.getElementById("txtFromDate"+Count).value;
		var toDate = document.getElementById("txtToDate"+Count).value;
		var hidTiRateTypeText = document.getElementById("hidTiRateTypeText").value;
		var hidForPensionText = document.getElementById("hidForPensionText").value;
		var hidHeadCode = document.getElementById("hidHeadCode").value;
		var url = "ifms.htm?actionFlag=chkDateIsOverLapOrNot&fromDate="+fromDate+"&toDate="+toDate+"&TiRateTypeText="+hidTiRateTypeText+"&ForPensionText="+hidForPensionText+"&HeadCode="+hidHeadCode;
		var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {chkDateCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});
		
		
	}
	function chkDateCaseStateChanged(myAjax)
	{
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCCHECKDATE');
		var flagVal=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
		if(flagVal == 'true')
		{
			alert('The rate for this date is already present.Please enter proper date.');
			document.getElementById("txtFromDate"+gRowCount).value="";
			document.getElementById("txtToDate"+gRowCount).value="";
			return;
		}
	}
	function saveDaRateHistory()
	{
		if(document.getElementById("tblAdminRateHisotry").rows.length == 1)
		{
			alert('Please add any row for this da rate.');
			return;
		}
		var effctvToDt = document.getElementsByName("txtToDate");
		var effctvFromDt = document.getElementsByName("txtFromDate");
		for(var i=0;i<effctvToDt.length;i++)
		{
			if(effctvToDt[i].value == '' || effctvFromDt[i].value == '')
			{
				alert('Effective from date or Effective to date cannot be null.');
				return;
			}
		}
		if(document.getElementById("cmbStateCode").value == -1)
		{
			alert('Please select state code type to proceed ahead.');
			return;
		}
		if(document.getElementById("TIRateType").value == -1)
		{
			alert('Please select DA rate type to proceed ahead.');
			return;
		}
		var uri = "ifms.htm?actionFlag=saveAdminHistory";
		var url = run();
		url = uri + url;
		var myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {saveDaRateHistoryStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...')} 
				});
			
		
		
	}
	function saveDaRateHistoryStateChanged(myAjax)
	{
		var XMLDoc=myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		if(string == 'Add')
		{
			alert("DA rate details have been saved successfully.");
			self.location.reload(true);
		}   
			
	}
function moveDARateConfigForState(count,hidString)
{
	document.getElementById("txtStateDesc").value  = document.getElementById("lblStateDescrptn"+count).innerHTML;
	 
	document.getElementById("hidString").value = hidString;
	
	document.getElementById("hidMstPensionStateRateId").value = document.getElementById("hidMstPensionStateRatePk"+count).value;
	
}
function saveDARateConfigForState()
{

	var uri;
	
	if(document.getElementById("txtStateDesc").value == "")
	{
		alert('Please enter state description.');
		return;
	}
	if(document.getElementById("hidString").value == 'Update')
	{
		uri = 'ifms.htm?actionFlag=saveDARateConfigForState&hidString='+document.getElementById("hidString").value+'&hidMstPensionStateRateId='+document.getElementById("hidMstPensionStateRateId").value;
	}
	else
	{
		uri = 'ifms.htm?actionFlag=saveDARateConfigForState&hidString=Add';
	}
	
	var url =  runForm(0);
	url = uri + url;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {saveDARateConfigForStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
	
}
function deleteDARateConfigForState()
{
	var totalSelectedDARate= document.getElementById("totalCount").value;
	var MstPensionStateRateId="";
	var tempFlag = 0;
	//var hidMainCtgryTotalArr = document.getElementById("hidMainCategoryIdList").value;
	var uri;
	var url;
	var myAjax;
	//var tempMainCtgryArr = hidMainCtgryTotalArr.substr(1,hidMainCtgryTotalArr.length-2);
	//var arrTotalMainCtgry = tempMainCtgryArr.split(",");
	for(var i=1;i<totalSelectedDARate;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			tempFlag = 1;
			MstPensionStateRateId= MstPensionStateRateId + document.getElementById("checkbox"+i).value + "~";
			/*for(var j=0;j<arrTotalMainCtgry.length;j++)
			{
				if(Number(document.getElementById("checkbox"+i).value) == Number(arrTotalMainCtgry[j]))
				{
					alert('You cannot delete ' + document.getElementById("lblStateDescrptn"+i).innerHTML  + '.Because It is related with some PPO.');
					return;
				}
			}*/
		}
	}
	if(tempFlag == 1)
	{
		uri = 'ifms.htm?actionFlag=saveDARateConfigForState&MstPensionStateRateId='+MstPensionStateRateId;
		url = runForm(0); 
		url = uri + url; 
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {saveDARateConfigForStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please select any State/Dept. configuration for da rate to delete.');
	}

}
function saveDARateConfigForStateChanged(myAjax) 
{ 
  
	   var XMLDoc=myAjax.responseXML.documentElement;
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		
		if(string == 'Add')
		{
			alert("State/Dept. configuration for da rate has been saved successfully.");
			self.location.reload();
			
		}
		if(string == 'Update')
		{
			alert("State/Dept. configuration for da rate has been updated successfully.");
			self.location.reload();
			
		}
		if(string == 'Delete')
		{
			alert("State/Dept. configuration for da rate has been deleted successfully.");
			self.location.reload();
			
		}
	
}
function compareTwoDates(fieldName1,fieldName2,flag)
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
    	    return true;
    	}
    	else
    	{
	        return false;
	    }
    }
    else if( year2 > year1 )
    {
    	if(flag == '<')
    	{
    		 return true;
    	}
    	else
    	{
    		return false;
    	}
       
    }
    else if( year2 < year1 && flag != '=')
    {
        if(flag == '<')
        {
    	    return false;
    	}
    	else if(flag == '>')
    	{
    		return true;
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
        	if(flag == '<')
        	{
        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else if( month2 < month1 )
        {     
            if(flag == '<')
	        {
    		    return false;
	    	}
    		else if(flag == '>')
    		{
    			return true;
    		}
        }
        else
        {
            if( day2 > day1 )
            {
            	if(flag == '<')
            	{
            		return true;
            	}
            	else
            	{
            		return false;
            	}
            }
            else if( day2 < day1 )
            {
                 if(flag == '<')
			     {
                	 return false;
			   	}
		    	else if(flag == '>')
    			{
		    		return true;
		    	}
            }
        }
    }
}