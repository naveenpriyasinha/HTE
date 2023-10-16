var totalEmployees=0 ;
var nextRow=0;
var employeesAdded = 0;

function window_open(val,x,y,afterDateSelect,dateInputParams){
    var newWindow;

    if(afterDateSelect == undefined) {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;

    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;

    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    

    var urlstyle = 'height=230,width=315,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;

	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}

function checkUncheckAll(theElement)
{
	 var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}

function AddNewRowContri(dcpsContributionIdValue)
{
	employeesAdded = employeesAdded + 1;

	if(employeesAdded <= 1)
	{
			var dcpsContributionId = dcpsContributionIdValue ;
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert ("Your browser does not support AJAX!");
				return false;
			} 
			uri= 'ifms.htm?actionFlag=getContributionDetails&dcpsContributionId='+dcpsContributionId  ;
			xmlHttp.onreadystatechange=addNewRowOnReadyStateChanged;
			xmlHttp.open("POST",uri,false);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(uri);
	}
	else
	{
			alert('You have already added one employee');
			return false;
	}
}

function addNewRowOnReadyStateChanged()
{
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {

			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			
			var empName =  XmlHiddenValues[0].childNodes[0].text;
			var dcpsId = XmlHiddenValues[0].childNodes[1].text;
			var startDate = XmlHiddenValues[0].childNodes[2].text;
			var endDate = XmlHiddenValues[0].childNodes[3].text;
			var payCommission = XmlHiddenValues[0].childNodes[4].text;
			var typeOfPayment = XmlHiddenValues[0].childNodes[5].text;
			var basic = XmlHiddenValues[0].childNodes[6].text;
			var DP = XmlHiddenValues[0].childNodes[7].text;
			var DA = XmlHiddenValues[0].childNodes[8].text;
			var contribution = XmlHiddenValues[0].childNodes[9].text;
			var dcpsEmpId =  XmlHiddenValues[0].childNodes[10].text;
			var dcpsContributionId = XmlHiddenValues[0].childNodes[11].text;
			
	
						document.getElementById("saveAndDelete").style.display='';
						document.getElementById("contributionAddRowTableDiv").style.display='';
						totalEmployees = totalEmployees+1;
						var table=document.getElementById("contributionAddRowTable");
						nextRow = nextRow + 1 ;
						
							var newRow = table.insertRow();	
							var Cell1 = newRow.insertCell();
							var Cell2 = newRow.insertCell();
							var Cell3 = newRow.insertCell();
							var Cell4 = newRow.insertCell();
							var Cell5 = newRow.insertCell(); 
							var Cell6 = newRow.insertCell(); 
							var Cell7 = newRow.insertCell(); 
							var Cell8 = newRow.insertCell(); 
							var Cell9 = newRow.insertCell(); 
							var Cell10 = newRow.insertCell(); 
							var Cell11 = newRow.insertCell();
							Cell11.className = "tds";
					 	  	Cell11.align="center";
					  
						
						Cell1.innerHTML = '<input type="text" name="name" id="nameNew'+nextRow+'" value="'+empName+'" />';
						Cell2.innerHTML = '<input type="text" name="dcpsIdNew" id="dcpsIdNew'+nextRow+'" value="'+dcpsId+'" />';
						Cell3.innerHTML = ' <input type="text" size="10" name="txtStartDateNew'+nextRow+'" id="txtStartDateNew'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
										+ '	onBlur="validateDate(this);" value="'+startDate+'" width="10" /> ' 
										+ ' &nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtStartDateNew'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>'  ;
						Cell4.innerHTML = ' <input type="text" size="10" name="txtEndDateNew'+nextRow+'" id="txtEndDateNew'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
										+ '	onBlur="validateDate(this);" value="'+endDate+'" width="10" /> ' 
										+ ' &nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtEndDateNew'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');" style="cursor: pointer;"/>'  ;
						Cell5.innerHTML = ' <select name="cmbPayCommissionNew" id="cmbPayCommissionNew'+nextRow+'" >' 
														+ ' <option value="-1">--Select--</option>' +LISTPAYCOMMISSIONS +'</select>';
						Cell6.innerHTML = ' <select name="cmbTypeOfPaymentNew" id="cmbTypeOfPaymentNew'+nextRow+'" >'
														+ ' <option value="-1">--Select--</option>' +LISTTYPESOFPAYMENT +'</select>';
						Cell7.innerHTML = ' <input type="text" size="10" id="basicNew'+nextRow+'" name="basic" value="'+basic+'" onchange="changeDpDaAndContri('+nextRow+')" ; />' ;
						Cell8.innerHTML = ' <input type="text" size="10" id="DPNew'+nextRow+'" name="DP"  value="'+DP+'"  readonly="readonly" />' ;
						Cell9.innerHTML = ' <input type="text" size="10" id="DANew'+nextRow+'" name="DA"  value="'+DA+'"  readonly="readonly" />' ;
						Cell10.innerHTML = ' <input type="text" size="10" id="contributionNew'+nextRow+'" name="contribution"  value="'+contribution+'"  readonly="readonly" />' ;
						Cell11.innerHTML= ' <input type="checkbox" id="checkboxNew'+nextRow+'" name="checkboxNew" value="'+dcpsContributionId+'" />' 
														+ ' <input type="hidden" name="dcpsempIdNew" id="dcpsempIdNew'+ nextRow+'" value="'+dcpsEmpId+'" />' ;
					
						var lObjectcmbPayCommissionNew = document.getElementById("cmbPayCommissionNew"+nextRow);
						var lValuecmbPayCommission = payCommission;
						setSelectedIndex(lObjectcmbPayCommissionNew,lValuecmbPayCommission) ;
					
						var lObjectcmbTypeOfPaymentNew = document.getElementById("cmbTypeOfPaymentNew"+nextRow);
						var lValuecmbTypeOfPayment = typeOfPayment;
						setSelectedIndex(lObjectcmbTypeOfPaymentNew,lValuecmbTypeOfPayment) ;

		}
	}
}

function setSelectedIndex(s, v) {
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].value == v ) {
            s.options[i].selected = true;
            return;
        }
    }
}

function getSchemeforBillGroup()
{
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	   {
	      return;
	   }  

	 var billGroupId ="";
	 
	 billGroupId = document.getElementById("cmbBillGroup").value;
	 
	 var uri = 'ifms.htm?actionFlag=getSchemeforBillGroup&billGroupId='+ billGroupId ;
	 xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var schemeName = XmlHiddenValues[0].childNodes[0].text;
						var schemeCode = XmlHiddenValues[0].childNodes[1].text;
						document.getElementById("txtSchemeName").value=schemeName;
						document.getElementById("schemeCode").value=schemeCode;
					}
				}
		};
		
	   xmlHttp.open("POST",uri,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(uri);
	
}

function getEmpListAfterValidation(){
	if(chkEmpty(document.getElementById('cmbTreasuryCode'),'Treasury Name') && 
			chkEmpty(document.getElementById('cmbDDOCode'),'DDO Name') &&
			chkEmpty(document.getElementById('cmbBillGroup'),'Bill Group') &&
			chkEmpty(document.getElementById('txtSchemeName'),'Scheme') &&
			chkEmpty(document.getElementById('cmbMonth'),'Pay Month') &&
			chkEmpty(document.getElementById('cmbYear'),'Pay Year')){
		getEmpListforContri();
	}
}

function getEmpListforContri()
{

	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var txtSchemeName = document.getElementById("txtSchemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	self.location.href = "ifms.htm?actionFlag=loadOfflineCorrectionForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
			+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode ;
}

function changeDpDaAndContri(counter)
{
	var nthElement = counter ; 
	var basic = document.getElementById("basicNew"+nthElement).value ;
	var DP = 0;
	var DA = ( Number(basic * 0.27) ).toFixed(2) ;
	var tempcontribution = Number(basic) + Number(DA) ;
	var contribution = ( Number( tempcontribution * 0.10 )).toFixed(2);
	document.getElementById("DPNew" + nthElement).value = DP ;
	document.getElementById("DANew" + nthElement).value = DA ;
	document.getElementById("contributionNew" + nthElement).value = contribution ;

}

function saveContributionData()
{
	var dcpsEmpIds = "" ;
	var cmbPayCommission = "" ;
	var cmbTypeOfPayment = "" ;
	var basic = "" ;
	var DP = "" ;
	var DA = "" ;
	var contribution = "" ;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeCode=document.getElementById("schemeCode").value;
	var dcpsContributionIds="";
	var txtStartDate="";
	var txtEndDate="";
	
	var voucherOrChallan="";
	var txtVCNo="";
	var txtVCDate="";
	var txtBillNo="";
	
   try
   {
	var testCounter=1;
	var totalSelectedEmployees = 0;
	

	for (var i=0; i < document.DCPSOfflineEntryCorrectionForm.voucherOrChallan.length; i++)
	   {
	   if (document.DCPSOfflineEntryCorrectionForm.voucherOrChallan[i].checked)
	      {
		    voucherOrChallan = document.DCPSOfflineEntryCorrectionForm.voucherOrChallan[i].value;
	      }
	   }
		
	txtVCNo =  document.getElementById("txtVCNO").value ;
	txtVCDate = document.getElementById("txtVCDate").value ;
	txtBillNo = document.getElementById("txtBILLNO").value ;
	
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkboxNew"+k).checked)
		{  	
			if(!chkEmpty(document.getElementById("nameNew"+k),'Name') || 
					!chkEmpty(document.getElementById("dcpsIdNew"+k),'DCPS ID') ||
					!chkEmpty(document.getElementById("txtStartDateNew"+k),'Contribution Start Date') ||
					!chkEmpty(document.getElementById("txtEndDateNew"+k),'Contribution End Date') ||
					!chkEmpty(document.getElementById("cmbPayCommissionNew"+k),'Pay Commission') ||
					!chkEmpty(document.getElementById("cmbTypeOfPaymentNew"+k),'Payment Type') ||
					!chkEmpty(document.getElementById("basicNew"+k),'Basic') ||
					!chkEmpty(document.getElementById("DPNew"+k),'DP') ||
					!chkEmpty(document.getElementById("DANew"+k),'DA') ||
					!chkEmpty(document.getElementById("voucherOrChallan"),'Voucher or Challan') ||
					!chkEmpty(document.getElementById("txtVCNO"),'Voucher No./Challan No') ||
					!chkEmpty(document.getElementById("txtVCDate"),'Voucher/Challan Date') ||
					!chkEmpty(document.getElementById("txtBILLNO"),'BIll No') )
			{
				return false;
			}
			totalSelectedEmployees++ ;	
		}
	}

	if(totalSelectedEmployees == 0)
	{
		alert('Please select at least one contribution');
		return false;
	}

	for(i=1;i<=totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkboxNew"+i).checked)
		{
			if(testCounter==totalSelectedEmployees)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempIdNew"+i).value;
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommissionNew"+i).value;
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPaymentNew"+i).value;
				basic = basic + document.getElementById("basicNew"+i).value;
				DP = DP + document.getElementById("DPNew"+i).value;
				DA = DA + document.getElementById("DANew"+i).value;
				contribution = contribution + document.getElementById("contributionNew"+i).value;
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value  ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDateNew"+i).value ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDateNew"+i).value ;
				testCounter++ ;
			}
			else
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempIdNew"+i).value + "~";
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommissionNew"+i).value + "~";
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPaymentNew"+i).value + "~";
				basic = basic + document.getElementById("basicNew"+i).value + "~";
				DP = DP + document.getElementById("DPNew"+i).value + "~";
				DA = DA + document.getElementById("DANew"+i).value + "~";
				contribution = contribution + document.getElementById("contributionNew"+i).value + "~";
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value + "~" ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDateNew"+i).value + "~" ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDateNew"+i).value + "~" ;
				testCounter++ ;
			}
		}
	}	
   }
   catch(e)
   {
   }
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=saveContributionsCorrections&dcpsEmpIds='+dcpsEmpIds+'&dcpsContributionIds='+dcpsContributionIds+'&txtStartDate='+txtStartDate+'&txtEndDate='+txtEndDate
	+'&cmbPayCommission='+cmbPayCommission+'&cmbTypeOfPayment='+cmbTypeOfPayment+
	'&basic='+basic+'&DP='+DP+'&DA='+DA+'&contribution='+contribution+'&monthId='+monthId+'&yearId='+yearId+'&cmbTreasuryCode='+cmbTreasuryCode+
	'&cmbDDOCode='+cmbDDOCode+'&cmbBillGroup='+cmbBillGroup+'&schemeCode='+schemeCode+ 
	'&voucherOrChallan='+voucherOrChallan+'&txtVCNo='+txtVCNo+'&txtVCDate='+txtVCDate+'&txtBillNo='+txtBillNo ;

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Amounts Changed successfully');
							var treasuryCode = document.getElementById("cmbTreasuryCode").value;
							var txtSchemeName = document.getElementById("txtSchemeName").value;
							var cmbMonth = document.getElementById("cmbMonth").value;
							var cmbYear = document.getElementById("cmbYear").value;
							self.location.href = "ifms.htm?actionFlag=loadOfflineCorrectionForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
									+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode ;
					}
				}
			}
	} ;
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}

function deleteContributionData()
{
	var testCounter=1;
	var dcpsEmpIds = "" ;
	var dcpsContributionIds="";
	var totalSelectedEmployees =0;

	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkboxNew"+k).checked)
		{  	
			totalSelectedEmployees++ ;
		}
	}

	if(totalSelectedEmployees == 0)
	{
		alert('Please select at least one contribution');
		return false;
	}
	execScript('n = msgbox("Do you really want to delete this contribution ?","4132")',"vbscript","Kapil");
	if(n==7)
	{
		return false; 
	}

	for(var i=1;i<=totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkboxNew"+i).checked)
		{
			if(testCounter==totalSelectedEmployees)
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value  ;
				testCounter++ ;
			}
			else
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value + "~" ;
				testCounter++ ;
			}
		}
	}	

	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=deleteContributions&dcpsContributionIds='+dcpsContributionIds ;

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Contributions Deleted Successfully');
							var treasuryCode = document.getElementById("cmbTreasuryCode").value;
							var cmbDDOCode = document.getElementById("cmbDDOCode").value;
							var cmbBillGroup = document.getElementById("cmbBillGroup").value;
							var txtSchemeName = document.getElementById("txtSchemeName").value;
							var schemeCode =  document.getElementById("schemeCode").value;
							var cmbMonth = document.getElementById("cmbMonth").value;
							var cmbYear = document.getElementById("cmbYear").value;
							self.location.href = "ifms.htm?actionFlag=loadOfflineCorrectionForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
									+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+txtSchemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode ;
					}
				}
			}
	} ;

	xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}
