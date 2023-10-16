//VALIDATE PPO NUMBER
function validatePPONumber()
{
	if(document.getElementById("txtPPONO").value != "")
	{
		var uri;
		uri = 'ifms.htm?actionFlag=validationOfPPONo';
		validatePpoNoUsingAjax(uri);
	}
	else
	{
		 document.getElementById("txtPPONO").value="";
   	 	 document.getElementById("txtDOB").value="";
		 document.getElementById("txtDOR").value="";
	     document.getElementById("txtPensionerName").value="";
		 document.getElementById("txtOldBasic1").value="";
		 document.getElementById("txtEffectiveFromDt1").value="";
	}
}

function validatePpoNoUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&PpoNo="+document.getElementById("txtPPONO").value,
		        onSuccess: function(myAjax) {
					validatePpoNoOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
	          } );
     
}


function validatePpoNoOnStateChanged(myAjax) 
{ 
	     var XMLDoc =  myAjax.responseXML.documentElement;
	 
	     var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	  
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
	     {
	    	 getPensionerDetailsFromPPONO();
	     }
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct PPO Number.");
	    	 document.getElementById("txtPPONO").value="";
	    	 document.getElementById("txtPPONO").focus();
	         document.getElementById("txtDOB").value="";
			 document.getElementById("txtDOR").value="";
		     document.getElementById("txtPensionerName").value="";
			 document.getElementById("txtOldBasic1").value="";
			 document.getElementById("txtEffectiveFromDt1").value="";
	     }
}

function getPensionerDetailsFromPPONO()
{
	if(document.getElementById("txtPPONO").value != '')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getPensionerDtlsFromPPONo&PpoNo='+document.getElementById("txtPPONO").value;
		getPnsnrDtlsUsingAjax(uri);
	}
	else
	{
		 document.getElementById("txtPPONO").value="";
    	 document.getElementById("hidPensnReqId").value="";
 	     document.getElementById("hidPnsnrCode").value="";
	     document.getElementById("hidppono").value="";
	     document.getElementById("hidheadcode").value=""; 
	     document.getElementById("hidFamMemDOB").value="";
	     document.getElementById("hidFamMemDOD").value="";
	     document.getElementById("hidPnsnrDOB").value="";
	     //document.getElementById("txtDOB").value="";  
	     document.getElementById("hidPnsnrDOD").value="";
	     document.getElementById("txtOldBasic1").value="";
	     document.getElementById("hidCommensionDate").value="";   
	     document.getElementById("txtEffectiveFromDt1").value="";
	     document.getElementById("hidFP1Date").value="";
	     document.getElementById("hidFP2Date").value="";
	     document.getElementById("hidNewFP1Basic").value="";
	     document.getElementById("hidNewFP2Basic").value="";
	     document.getElementById("hidEndDate").value="";
	     document.getElementById("hidlastBillMonth").value="";
	     document.getElementById("txtDOR").value="";
	     document.getElementById("hidPnsnrDOR").value="";
	     document.getElementById("txtPensionerName").value="";
	     document.getElementById("hdnPensionerName").value="";
	}
   
}
function getPnsnrDtlsUsingAjax(uri)
{
	var bankCode=document.getElementById("cmbBankName").value;
	var branchCode=document.getElementById("cmbBankBranch").value;
	var accountNo=document.getElementById("txtAccountNo").value;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					getPnsnrDtlsCaseStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getPnsnrDtlsCaseStateChanged(myAjax)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	   
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('REEMPFROMDT');  
    var XmlHiddenReEmpl = XMLDoc.getElementsByTagName('REEMPDTLS');  
    var XmlHiddenPunishCutCount =XMLDoc.getElementsByTagName('PUNISHAMT');  
    var XmlHiddenPunishCut = XMLDoc.getElementsByTagName('PUNISHCUTDTLS');  
    var i=1;
    
    for(var j = document.getElementById("ReEmp").rows.length; j > 2;j--)
    {
   	 document.getElementById("ReEmp").deleteRow(j -1);
    }
   
    for(var p = document.getElementById("tblPnshmntCut").rows.length; p > 2;p--)
    {
   	 document.getElementById("tblPnshmntCut").deleteRow(p -1);
    }
    
    if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue != 'Y')
    {       
    	  if((document.getElementById('ReEmp').rows.length)==2)
	  	  {
	  		  addRowReEmp();
	  	  }
    	  if((document.getElementById('tblPnshmntCut').rows.length)==1)
	  	  {
    		  punishmentCutAddRow();
	  	  }
    	  document.getElementById("hidPensnReqId").value=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	      document.getElementById("hidPnsnrCode").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	      document.getElementById("hidppono").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	      document.getElementById("txtPPONO").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	      document.getElementById("hidheadcode").value=XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue; 
	      if(XmlHiddenValues[0].childNodes[5].childNodes[0] != undefined)
	      {
	      	document.getElementById("hidFamMemDOB").value=XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[6].childNodes[0] != undefined)
	      {
	      	document.getElementById("hidFamMemDOD").value=XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidPnsnrDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	      document.getElementById("txtDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue; 
	      if(XmlHiddenValues[0].childNodes[8].childNodes[0] != undefined)
	      { 
	      	document.getElementById("hidPnsnrDOD").value=XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
	      }
	      document.getElementById("txtOldBasic1").value=XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
	      document.getElementById("hidCommensionDate").value=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;   
	      document.getElementById("txtEffectiveFromDt1").value=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;
	      
	      document.getElementById("hidFP1Date").value=XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue;
	      document.getElementById("hidFP2Date").value=XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue;
	      document.getElementById("hidNewFP1Basic").value=XmlHiddenValues[0].childNodes[13].childNodes[0].nodeValue;
	      document.getElementById("hidNewFP2Basic").value=XmlHiddenValues[0].childNodes[14].childNodes[0].nodeValue;
	      if(XmlHiddenValues[0].childNodes[16].childNodes[0] != undefined)
	      {
	      	document.getElementById("hidEndDate").value=XmlHiddenValues[0].childNodes[16].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidlastBillMonth").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;

	      document.getElementById("txtDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	      document.getElementById("hidPnsnrDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	      document.getElementById("txtPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hdnPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hidBankName").value=XmlHiddenValues[0].childNodes[19].childNodes[0].nodeValue;
	      document.getElementById("hidBranchName").value=XmlHiddenValues[0].childNodes[20].childNodes[0].nodeValue;
	      document.getElementById("hidAccountNo").value=XmlHiddenValues[0].childNodes[21].childNodes[0].nodeValue;
	      document.getElementById("hidCurrentDt").value=XmlHiddenValues[0].childNodes[22].childNodes[0].nodeValue;
	      if(XmlHiddenValues[0].childNodes[23].childNodes[0] != null)
	      {
	    	  document.getElementById("hidVolumeNo").value=XmlHiddenValues[0].childNodes[23].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[24].childNodes[0] != null)
	      {
	    	  document.getElementById("hidPageNo").value=XmlHiddenValues[0].childNodes[24].childNodes[0].nodeValue;
	      }
	      
	      document.getElementById("hidTreasuryName").value=XmlHiddenValues[0].childNodes[25].childNodes[0].nodeValue;
	           
          var rowCount = document.getElementById("hidReEmpDtlsGridSize").value;
          rowCount = rowCount - 1;
	      var k=0;
	      
	      for(var j=0;j<XmlHiddenValues1.length;j++)
	      {  
		      document.getElementById("txtOldReEmpEffFrom"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      k++;
		      if(XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue != 'null')
		      {
		    	  document.getElementById("txtOldReEmpEffTo"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      }
		      k++;
		      document.getElementById("cmbDAInPnsnSal"+rowCount).value=XmlHiddenReEmpl[0].childNodes[k].childNodes[0].nodeValue;
		      k++;
		      rowCount++;
		      if(j!=XmlHiddenValues1.length-1)
		      {
	    	 	 addRowReEmp();
		      }
	    	 
	      }
	      
	      var punishCnt = document.getElementById("hidPnshmntCutSize").value;
	      punishCnt = punishCnt - 1;
	      var p=0;
	      
	      for(var j=0;j<XmlHiddenPunishCutCount.length;j++)
	      {  
		      document.getElementById("txtPnshmntAmount"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      p++;
		      if(XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue != 'null')
		      {
		    	  document.getElementById("txtPnshmntFromDate"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      }
		      p++;
		      document.getElementById("txtPnshmntToDate"+punishCnt).value=XmlHiddenPunishCut[0].childNodes[p].childNodes[0].nodeValue;
		      p++;
		      punishCnt++;
		      if(j!=XmlHiddenPunishCutCount.length-1)
		      {
		    	  punishmentCutAddRow();
		      }
	    	 
	      }
	      
	   
    }
    if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
    {
			alert("Please Enter Correct PPO Number or Bank Detail.");
			document.getElementById("txtPPONO").value="";
			document.getElementById("txtDOB").value="";
			document.getElementById("txtDOR").value="";
			document.getElementById("txtPensionerName").value="";
			document.getElementById("txtOldBasic1").value="";
		    document.getElementById("txtEffectiveFromDt1").value="";
		
    }
}

function validateBankDtls()
{
	if(IsEmptyFun("cmbBankName","Please Select Bank Name.")==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbBankBranch","Please Select Branch Name.")==false)
	{
		return false;
	}
	if(IsEmptyFun("txtAccountNo","Please Enter Account Number.")==false)
	{
		return false;
	}
	return true;
}

function getPnsnrDtlsFromBankBranch()
{
	if(validateBankDtls())
	{
		var uri;
		var bankCode=document.getElementById("cmbBankName").value;
		var branchCode=document.getElementById("cmbBankBranch").value;
		var accountNo=document.getElementById("txtAccountNo").value;
		uri = 'ifms.htm?actionFlag=getPensionerDtlsFromPPONo&bankCode='+bankCode+'&branchCode='+branchCode+'&accountNo='+accountNo;
		getPnsnrDtlsUsingAjax(uri);
	}
}

function addRowPnsnBasicDtls()
{
    rowId = document.getElementById("hidPnsnBasicGridSize").value;
	
	var table=document.getElementById("BasicPnsnDtl");
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
   	var Cell5 = newRow.insertCell(4);
   	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6 = newRow.insertCell(5);
	Cell6.className = "tds";
   	Cell6.align="center";
   	var Cell7 = newRow.insertCell(6);
	Cell7.className = "tds";
   	Cell7.align="center";
	
   	Cell1.innerHTML = '<input type="text" name="txtOldBasic" id="txtOldBasic'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
	Cell2.innerHTML = '<input type="text" name="txtNewBasic" id="txtNewBasic'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>'; 
	Cell3.innerHTML = '<input type="text" name="txtOldCvp" id="txtOldCvp'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
	Cell4.innerHTML = '<input type="text" name="txtNewCvp" id="txtNewCvp'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>'; 
	Cell5.innerHTML = '<input type="text" name="txtEffectiveFromDt" id="txtEffectiveFromDt'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtEffectiveFromDt'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';	
	Cell6.innerHTML = '<input type="text" name="txtEffectiveToDt" id="txtEffectiveToDt'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtEffectiveToDt'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" />';
	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'BasicPnsnDtl\')" /> ';
	  
	document.getElementById("hidPnsnBasicGridSize").value = Number(rowId)+1;  
}

function addRowReEmp()
{	
	Row_ID_ReEmp = document.getElementById("hidReEmpDtlsGridSize").value;
	
	var table=document.getElementById("ReEmp");
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
	
   	Cell1.innerHTML = '<input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom'+Row_ID_ReEmp+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldReEmpEffFrom'+Row_ID_ReEmp+'\', 375, 570, \'\', \'\', '+Row_ID_ReEmp+');" />';	
	Cell2.innerHTML = '<input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo'+Row_ID_ReEmp+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldReEmpEffTo'+Row_ID_ReEmp+'\', 375, 570, \'\', \'\', '+Row_ID_ReEmp+');" />';
	Cell3.innerHTML = '<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal'+Row_ID_ReEmp+'" ><option value="-1">--Select--</option>'+LISTDAIN+'</select>';
	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'ReEmp\')" /> ';
	document.getElementById("hidReEmpDtlsGridSize").value = Number(Row_ID_ReEmp)+1;  
}


function punishmentCutAddRow()
{
	Row_ID_Pnshmt = document.getElementById("hidPnshmntCutSize").value;	
	var table=document.getElementById("tblPnshmntCut");
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
	  	
   	
	Cell1.innerHTML = '<input type="hidden" name="hdnPensionCutDtlsId" id="hdnPensionCutDtlsId'+Row_ID_Pnshmt+'"/><input type="text" name="txtPnshmntAmount" id="txtPnshmntAmount'+Row_ID_Pnshmt+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell2.innerHTML = '<input type="text" name="txtPnshmntFromDate" id="txtPnshmntFromDate'+Row_ID_Pnshmt+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtPnshmntFromDate'+Row_ID_Pnshmt+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Pnshmt)+');" />';
   	Cell3.innerHTML = '<input type="text" name="txtPnshmntToDate" id="txtPnshmntToDate'+Row_ID_Pnshmt+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtPnshmntToDate'+Row_ID_Pnshmt+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Pnshmt)+');" />';
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblPnshmntCut\')" /> ';   	
   	document.getElementById("hidPnshmntCutSize").value = Number(Row_ID_Pnshmt)+1; 
}

function validateSixthPayArrear()
{
	if(IsEmptyFun("txtPPONO", "Please Enter PPO Number or Bank Detail.")==false)
	{
		document.getElementById("txtPPONO").focus();
		return false;
	}
	var basicCntLength=document.getElementById("hidPnsnBasicGridSize").value;
	
	 if(document.getElementById('BasicPnsnDtl').rows.length > 1)
	 {
		for(var rowCnt=1;rowCnt<Number(basicCntLength);rowCnt++)
		{		
			try
			{	
				if(IsEmptyFun("txtOldBasic"+rowCnt, "Please Enter Old Basic.")==false)
				{
					document.getElementById("txtOldBasic"+rowCnt).focus();
					return false;
				}
				if(IsEmptyFun("txtNewBasic"+rowCnt, "Please Enter New Basic.")==false)
				{
					document.getElementById("txtNewBasic"+rowCnt).focus();
					return false;
				}
				if(IsEmptyFun("txtEffectiveFromDt"+rowCnt, "Please Enter Effective From Date.")==false)
				{
					document.getElementById("txtEffectiveFromDt"+rowCnt).focus();
					return false;
				}
				if(IsEmptyFun("txtEffectiveToDt"+rowCnt, "Please Enter Effective To Date.")==false)
				{
					document.getElementById("txtEffectiveToDt"+rowCnt).focus();
					return false;
				}
				var sixthPayStrtDt = "01/01/2006";
				var basicEffFromDate=document.getElementById("txtEffectiveFromDt"+rowCnt).value;
				var basicEffToDate=document.getElementById("txtEffectiveToDt"+rowCnt).value;
				if(sixthPayStrtDt != basicEffFromDate)
				{
					if(compareDate(sixthPayStrtDt,basicEffFromDate) == false)
					{
						alert("Effective From Date must be greater than or equal to 01/01/2006.");
						document.getElementById("txtEffectiveFromDt"+rowCnt).focus();
						return false;
					}
				}
				if(compareDate(basicEffFromDate,basicEffToDate) == false)
				{
					alert("Effective To Date should be greater than Effective From Date.");
					document.getElementById("txtEffectiveToDt"+rowCnt).focus();
					return false;
				}
				
			}
			catch(ex)
			{
				
			}
		}
	 }
		//Punishment cut validation
		var punishRowCount=document.getElementById("tblPnshmntCut").rows.length;
		var punishAmount=document.getElementsByName("txtPnshmntAmount");
		var punishFromDate=document.getElementsByName("txtPnshmntFromDate");
		var punishToDate=document.getElementsByName("txtPnshmntToDate");
		
		if(punishRowCount >= 2)
		{
			for(var cnt=0;cnt<punishAmount.length;cnt++)
			{
				if(punishFromDate[cnt].value != "" || punishToDate[cnt].value != "")
				{
					if(punishAmount[cnt].value=='' || punishAmount[cnt].value=='0.00')
					{
						alert("Please Enter Punishment Amount.");
						punishAmount[cnt].focus();
						return false;
					}
					if(punishFromDate[cnt].value=='')
					{
						alert("Please Enter Effective From Date.");
						punishFromDate[cnt].focus();
						return false;
					}
					if(punishToDate[cnt].value=='')
					{
						alert("Please Select Effective To Date.");
						punishToDate[cnt].focus();
						return false;
					}
				}
			}
		}
		//Re-Employment validation
		var reEmpRowCount=document.getElementById("ReEmp").rows.length;
		var reEmpFromDate=document.getElementsByName("txtOldReEmpEffFrom");
		var reEmpToDate=document.getElementsByName("txtOldReEmpEffTo");
		var daInSalaryPnsn=document.getElementsByName("cmbDAInPnsnSal");
		
		if(reEmpRowCount >= 3)
		{
			for(var cnt=0;cnt<reEmpFromDate.length;cnt++)
			{
				if(reEmpFromDate[cnt].value != "" || reEmpToDate[cnt].value != "")
				{
					if(reEmpFromDate[cnt].value=='')
					{
						alert("Please Enter Effective From Date.");
						reEmpFromDate[cnt].focus();
						return false;
					}
					if(reEmpToDate[cnt].value=='')
					{
						alert("Please Enter Effective To Date.");
						reEmpToDate[cnt].focus();
						return false;
					}
					if(daInSalaryPnsn[cnt].value=='-1')
					{
						alert("Please Select DA in Pension/Salary.");
						daInSalaryPnsn[cnt].focus();
						return false;
					}
				}
			}
		}
	return true;
}
function generateSixthPayArrearRpt()
{
	if(validateSixthPayArrear())
	{
		document.forms[0].action="ifms.htm?actionFlag=generateSixthPayArrearStatement";
		document.forms[0].setAttribute("target", "formresult");
	    window.open('','formresult','width=1300,height=800,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'); 
		document.forms[0].submit();
	}
}