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
	
   	
   	Cell1.innerHTML = '<select name="cmbPayCommissionType"  id="cmbPayCommissionType'+rowId+'" style="width:90%" onchange="getDARateFromPC(this,cmbNewDARate'+rowId+',cmbOldDARate'+rowId+')"><option value="-1">--Select--</option>'+PAYCOMMISSIONTYPE+'</select><label class="mandatoryindicator">*</label>';		  
	Cell2.innerHTML = '<input type="text" name="txtBasic" id="txtBasic'+rowId+'" size="16" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onKeyPress="numberFormat(this)"  style="text-align: right"/><label class="mandatoryindicator">*</label>';	
	Cell3.innerHTML = '<input type="text" name="txtDARateEffFrom" id="txtDARateEffFrom'+rowId+'" size="13" onfocus="onFocus(this)"  onkeypress="digitFormat(this);dateFormat(this);numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(this,txtDARateEffTo'+rowId+',\'Effective To Date should be greater than Effective From Date\',\'<\')" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtDARateEffFrom'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" /><label class="mandatoryindicator">*</label>'; 
	Cell4.innerHTML = '<input type="text" name="txtDARateEffTo" id="txtDARateEffTo'+rowId+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDARateEffFrom'+rowId+',this,\'Effective To Date should be greater than Effective FRom Date\',\'<\')" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtDARateEffTo'+rowId+'\', 375, 570, \'\', \'\', '+rowId+');" /><label class="mandatoryindicator">*</label>';
	Cell5.innerHTML = '<select name="cmbOldDARate"  id="cmbOldDARate'+rowId+'" style="width:90%" ><option value="-1">--Select--</option></select><label class="mandatoryindicator">*</label>';
	Cell6.innerHTML = '<select name="cmbNewDARate"  id="cmbNewDARate'+rowId+'" style="width:90%" ><option value="-1">--Select--</option></select><label class="mandatoryindicator">*</label>';
	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'BasicPnsnDtl\')" /> ';
	  
	document.getElementById("hidPnsnBasicGridSize").value = Number(rowId)+1;  
}

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
	   }else if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'N')
	   {
		   alert("Please Enter Correct PPO Number.");
		   document.getElementById("txtPPONO").value="";
		   document.getElementById("txtPPONO").focus();
		   document.getElementById("txtDOB").value="";
		   document.getElementById("txtDOR").value="";
		   document.getElementById("txtPensionerName").value="";		 
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
	     document.getElementById("txtOldBasicEffFrom1").value="";
	     document.getElementById("txtNewBasicEffFrom1").value="";
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
      
    var i=1;
    for(var j = document.getElementById("ReEmp").rows.length; j > 1;j--)
    {
   	 document.getElementById("ReEmp").deleteRow(j -1);
    }    
    
    if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue != 'Y')
    {       
	      if((document.getElementById('ReEmp').rows.length)==1 && XmlHiddenValues1.length >0)
	  	  {
	  		  addRowReEmp();
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
	      if(XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue != null)
	      {
	    	  document.getElementById("hidPnsnrDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	    	  document.getElementById("txtDOB").value=XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[8].childNodes[0] != undefined)
	      { 
	      	  document.getElementById("hidPnsnrDOD").value=XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
	      }
	      document.getElementById("txtBasic").value=XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
	      document.getElementById("hidCommensionDate").value=XmlHiddenValues[0].childNodes[10].childNodes[0].nodeValue;     
	     	
	      if(XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("hidFP1Date").value=XmlHiddenValues[0].childNodes[11].childNodes[0].nodeValue;
	      }
	      if(XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue != 'null')
	      {
	    	  document.getElementById("hidFP2Date").value=XmlHiddenValues[0].childNodes[12].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidNewFP1Basic").value=XmlHiddenValues[0].childNodes[13].childNodes[0].nodeValue;
	      document.getElementById("hidNewFP2Basic").value=XmlHiddenValues[0].childNodes[14].childNodes[0].nodeValue;
	      if(XmlHiddenValues[0].childNodes[16].childNodes[0] != undefined)
	      {
	      	document.getElementById("hidEndDate").value=XmlHiddenValues[0].childNodes[16].childNodes[0].nodeValue;
	      }
	      document.getElementById("hidlastBillMonth").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
//	      document.getElementById("txtOldBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
//	      document.getElementById("txtNewBasicEffTo1").value=XmlHiddenValues[0].childNodes[17].text;
	      document.getElementById("txtDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	      document.getElementById("hidPnsnrDOR").value=XmlHiddenValues[0].childNodes[17].childNodes[0].nodeValue;
	      
	      document.getElementById("txtPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hdnPensionerName").value=XmlHiddenValues[0].childNodes[18].childNodes[0].nodeValue;
	      document.getElementById("hidBankName").value=XmlHiddenValues[0].childNodes[19].childNodes[0].nodeValue;
	      document.getElementById("hidBranchName").value=XmlHiddenValues[0].childNodes[20].childNodes[0].nodeValue;
	      document.getElementById("hidAccountNo").value=XmlHiddenValues[0].childNodes[21].childNodes[0].nodeValue;
	      document.getElementById("hidCurrentDt").value=XmlHiddenValues[0].childNodes[22].childNodes[0].nodeValue;
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
    }else{
			alert("Please Enter Correct PPO Number.");
			document.getElementById("txtPPONO").value="";
			document.getElementById("txtDOB").value="";
			document.getElementById("txtDOR").value="";
			document.getElementById("txtPensionerName").value="";			   		
    }
}
function addRowReEmp()
{	
	Row_ID_ReEmp = document.getElementById("hidReEmpDtlsGridSize").value;
	
	var table=document.getElementById("ReEmp");
	var rowCount=table.rows.length;
	var newRow = table.insertRow(rowCount); 
	//var newRow =  document.all("tblCvpRestnDtls").insertRow();	  			  		
	
   	var Cell1 = newRow.insertCell(0);
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell(1);
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell(2);
   	Cell3.className = "tds";
   	Cell3.align="center";
   	
   	Cell1.innerHTML = '<input type="text" name="txtOldReEmpEffFrom" id="txtOldReEmpEffFrom'+Row_ID_ReEmp+'" readonly="readonly" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);" />';	
	Cell2.innerHTML = '<input type="text" name="txtOldReEmpEffTo" id="txtOldReEmpEffTo'+Row_ID_ReEmp+'" onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" size="13"	onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOldReEmpEffTo'+Row_ID_ReEmp+'\', 375, 570, \'\', \'\', '+Row_ID_ReEmp+');" /><label class="mandatoryindicator">*</label>';
	Cell3.innerHTML = '<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal'+Row_ID_ReEmp+'" style="width: 50%" ><option value="-1">--Select--</option>'+LISTDAIN+'</select><label class="mandatoryindicator">*</label>';	
	document.getElementById("hidReEmpDtlsGridSize").value = Number(Row_ID_ReEmp)+1;  
}

function getDARateFromPC(ObjSource,ObjNewDARate,ObjOldDARate){
	  var DARateLength=ObjNewDARate.length;
	  for(var i=0;i<DARateLength;i++)
	  {
		  var lgth = ObjNewDARate.options.length -1;				  
		  ObjNewDARate.options[lgth] = null;
	  }
	  DARateLength=ObjOldDARate.length;
	  for(var i=0;i<DARateLength;i++)
	  {
		  lgth = ObjOldDARate.options.length -1;				  
		  ObjOldDARate.options[lgth] = null;
	  }
	
	  var payCommission= ObjSource.value;
	  var url = 'ifms.htm?actionFlag=getDaRateFromPayCom';
	  var uri = '&PayCommissionType='+payCommission;		  
	  url = url + uri;
	  
	  var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
						GetDARate(myAjax,ObjNewDARate,ObjOldDARate,payCommission);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function GetDARate(myAjax,ObjNewDARate,ObjOldDARate,payCommission){	
	
	  var XMLDoc =  myAjax.responseXML.documentElement;	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('item');	  
	  for ( var i = 0 ; i < XmlHiddenValues.length ; i++ )
	  {
		   var text=XmlHiddenValues[i].childNodes[0].firstChild.nodeValue;
		   var val =XmlHiddenValues[i].childNodes[1].firstChild.nodeValue; 
		   var theOption = new Option;		   
		   theOption.value=val;
		   theOption.text=text;			
		   ObjNewDARate.options[i]=theOption;		 
	  }	
	  for ( var i = 0 ; i < XmlHiddenValues.length ; i++ )
	  {
		   var text=XmlHiddenValues[i].childNodes[0].firstChild.nodeValue;
		   var val =XmlHiddenValues[i].childNodes[1].firstChild.nodeValue; 
		   var theOption = new Option;		  
		   theOption.value=val;
		   theOption.text=text;
		   ObjOldDARate.options[i]=theOption;
	  }	
}
function calculateDAArrear(){
	if(document.getElementById("txtPPONO").value.trim() == '')
	{
		alert("Please Enter PPO Number.");
		document.getElementById("txtPPONO").focus();
		return false;
	}	
	var selectType = document.getElementsByTagName('select');
	for(var i = 0; i < selectType.length; i++)
	{
		if(selectType[i].name == 'cmbPayCommissionType' && selectType[i].value == '-1')
		{
			alert("Please select Pay Commission Type.");
			selectType[i].focus();
			return false;
		}
		if(selectType[i].name == 'cmbOldDARate' && selectType[i].value == '-1')
		{
			alert("Please select Old Rate.");
			selectType[i].focus();
			return false;
		}
		if(selectType[i].name == 'cmbNewDARate' && selectType[i].value == '-1')
		{
			alert("Please select New Rate.");
			selectType[i].focus();
			return false;
		}		
		
	}
	
	var inputType = document.getElementsByTagName('input');
	for(i = 0; i < inputType.length; i++)
	{		
		if(inputType[i].name == 'txtBasic' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Basic.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtDARateEffFrom' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Effective From Date.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtDARateEffTo' && inputType[i].value.trim() == '')
		{
			alert("Please Enter Effective To Date.");
			inputType[i].focus();
			return false;
		}
		else if(inputType[i].name == 'txtNewBasic' && inputType[i].value.trim() == '')
		{
			alert("Please Enter New Basic.");
			inputType[i].focus();
			return false;
		}
	}
	
	var basicCntLength=document.getElementById("hidPnsnBasicGridSize").value;
	
	for(var rowCnt=0;rowCnt<Number(basicCntLength);rowCnt++)
	{		
		try
		{
			var basicEffFromDate=document.getElementById("txtDARateEffFrom"+rowCnt).value;
			var basicEffToDate=document.getElementById("txtDARateEffTo"+rowCnt).value;
			
			if(compareDate(basicEffFromDate,basicEffToDate) == false)
			{
				alert("Effective To Date should be greater than Effective From Date.");
				document.getElementById("txtDARateEffTo"+rowCnt).value="";
				document.getElementById("txtDARateEffTo"+rowCnt).focus();
				return false;
			}
			
		}
		catch(ex)
		{
			
		}
	}
	var reEmpLength=document.getElementById("hidReEmpDtlsGridSize").value;
	
	for(var rowCnt=0;rowCnt<Number(reEmpLength);rowCnt++)
	{		
		try
		{
			var OldReEmpEffTo=document.getElementById("txtOldReEmpEffTo"+rowCnt).value;
			if(OldReEmpEffTo == null || OldReEmpEffTo ==""){
				alert("Please Enter Re-Employment Effective To Date.");				
				document.getElementById("txtOldReEmpEffTo"+rowCnt).focus();
				return false;
			}
			var DAInPnsnSal=document.getElementById("cmbDAInPnsnSal"+rowCnt).value;		
			if(DAInPnsnSal == "-1"){
				alert("Please Select DA In Pension/Salary.");				
				document.getElementById("cmbDAInPnsnSal"+rowCnt).focus();
				return false;
			}
			
		}
		catch(ex)
		{
			
		}
	}	
	for(var rowCnt=0;rowCnt<Number(basicCntLength);rowCnt++)
	{
		try{
			var oldDaRateVal = document.getElementById("cmbOldDARate"+rowCnt).value;
			var newDaRateVal = document.getElementById("cmbNewDARate"+rowCnt).value;			
			if(Number(oldDaRateVal)>Number(newDaRateVal)){
			alert('New DA Rate should be greater than Old DA Rate');				
			document.getElementById("cmbNewDARate"+rowCnt).value = -1;
			document.getElementById("cmbNewDARate"+rowCnt).focus();		
			return false;
			}
		}catch(ex)
		{
			
		}
	}
	document.forms[0].action="ifms.htm?actionFlag=generateDAArrearRecoveryReport";
	document.forms[0].setAttribute("target", "formresult");
    window.open('','formresult','width=1300,height=800,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'); 
	document.forms[0].submit();
    return true;
}