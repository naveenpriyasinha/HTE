var pensionerCode="";
function getPensionerCode()
{
	var flag=0;
	pensionerCode="";
	var arrChkBox=document.getElementsByName("chkbxPensionerCode");

	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					if(flag==0)
					{
						flag=1;
						pensionerCode=arrChkBox[i].value;
						
					}
					else
					{
						pensionerCode=pensionerCode+"~"+arrChkBox[i].value;
					    
					}
					
				}
			}
		}
	}
					
}

function viewLifeCertificate()
{
	var url = "ifms.htm?actionFlag=generateLifeCertificateList&SearchCrt="+document.getElementById("cmbSearchCrt").value
				+"&SearchType="+document.getElementById("cmbSearchType").value
				+"&SearchValue="+document.getElementById("txtSeachValue").value
				+"&PageNo="+document.getElementById("txtPageNo").value
				+"&BankCode="+document.getElementById("cmbBankName").value
				+"&BranchCode="+document.getElementById("cmbBankBranch").value
				+"&LifeCertFlag="+document.getElementById("cmbSearchLifeCertFlag").value;
	showProgressbar();
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "LifeCertificate", urlstyle);
	
//	document.frmLifeCerificate.action = "ifms.htm?actionFlag=generateLifeCertificateList&SearchCrt="+document.getElementById("cmbSearchCrt").value
//										+"&SearchType="+document.getElementById("cmbSearchType").value
//										+"&SearchValue="+document.getElementById("txtSeachValue").value
//										+"&PageNo="+document.getElementById("txtPageNo").value
//										+"&BankCode="+document.getElementById("cmbBankName").value
//										+"&BranchCode="+document.getElementById("cmbBankBranch").value
//										+"&LifeCertFlag="+document.getElementById("cmbSearchLifeCertFlag").value;
//	document.frmLifeCerificate.method = "post";
//	document.frmLifeCerificate.submit();
	hideProgressbar();
	
}

function search()
{
	if(document.getElementById("cmbSearchCrt").value == "-1")
	{
		document.getElementById("cmbSearchType").value=-1;
		document.getElementById("cmbSearchType").disabled=false;
		document.getElementById("txtPageNo").style.display="none";
		document.getElementById("txtSeachValue").style.display="inline";
		document.getElementById("txtSeachValue").value="";
		document.getElementById("txtPageNo").value="";
		document.getElementById("cmbBankName").style.display="none";
		document.getElementById("cmbBankBranch").style.display="none";
		document.getElementById("cmbSearchLifeCertFlag").style.display="none";
	}
	else if(document.getElementById("cmbSearchCrt").value == "prh.ppo_no" || document.getElementById("cmbSearchCrt").value == "mpd.account_no")
	{
		document.getElementById("cmbSearchType").value="Normal";
		document.getElementById("cmbSearchType").disabled=true;
		document.getElementById("txtPageNo").style.display="none";
		document.getElementById("txtSeachValue").style.display="inline";
		document.getElementById("txtSeachValue").value="";
		document.getElementById("txtPageNo").value="";
		document.getElementById("cmbBankName").style.display="none";
		document.getElementById("cmbBankBranch").style.display="none";
		document.getElementById("cmbSearchLifeCertFlag").style.display="none";
	}
	else if(document.getElementById("cmbSearchCrt").value == "mpd.branch_code")
	{
		document.getElementById("cmbSearchType").value="Normal";
		document.getElementById("cmbSearchType").disabled=true;
		document.getElementById("txtPageNo").style.display="none";
		document.getElementById("txtSeachValue").style.display="none";
		document.getElementById("txtPageNo").value="";
		document.getElementById("cmbBankName").style.display="inline";
		document.getElementById("cmbBankBranch").style.display="inline";
		document.getElementById("cmbSearchLifeCertFlag").style.display="none";
	}
	else if(document.getElementById("cmbSearchCrt").value == "prh.ledger_page_no")
	{
		document.getElementById("cmbSearchType").disabled=false;
		document.getElementById("txtSeachValue").style.display="inline";
		document.getElementById("txtSeachValue").value="";
		document.getElementById("txtPageNo").value="";
		document.getElementById("txtPageNo").style.display="inline";
		document.getElementById("cmbBankName").style.display="none";
		document.getElementById("cmbBankBranch").style.display="none";
		document.getElementById("cmbSearchLifeCertFlag").style.display="none";
	}
	else if(document.getElementById("cmbSearchCrt").value == "prh.life_cert_flag")
	{
		document.getElementById("cmbSearchType").value="Normal";
		document.getElementById("cmbSearchType").disabled=true;
		document.getElementById("txtSeachValue").style.display="none";
		document.getElementById("txtSeachValue").value="";
		document.getElementById("txtPageNo").value="";
		document.getElementById("txtPageNo").style.display="none";
		document.getElementById("cmbBankName").style.display="none";
		document.getElementById("cmbBankBranch").style.display="none";
		document.getElementById("cmbSearchLifeCertFlag").style.display="inline";
	}

}
function validateSearchData()
{
	if(document.getElementById("cmbSearchCrt").value == "prh.ppo_no")
	{
		if(isEmpty("txtSeachValue", 'Please Enter PPO Number.')==false)
		{
			document.getElementById("txtSeachValue").focus();
			return false;
		}
	}
	if(document.getElementById("cmbSearchCrt").value == "mpd.branch_code")
	{
		if(IsEmptyFun("cmbBankName", 'Please Select Bank.')==false)
		{
			document.getElementById("cmbBankName").focus();
			return false;
		}
		if(IsEmptyFun("cmbBankBranch", 'Please Select Branch.')==false)
		{
			document.getElementById("cmbBankBranch").focus();
			return false;
		}
	
	}
	if(document.getElementById("cmbSearchCrt").value == "prh.ledger_page_no")
	{
		if(isEmpty("txtSeachValue", 'Please Enter Volume Number.')==false)
		{
			document.getElementById("txtSeachValue").focus();
			return false;
		}
		if(isEmpty("txtPageNo", 'Please Enter Page Number.')==false)
		{
			document.getElementById("txtPageNo").focus();
			return false;
		}
	}
	if(document.getElementById("cmbSearchCrt").value == "prh.life_cert_flag")
	{
		if(document.getElementById("cmbSearchLifeCertFlag").value == "-1")
		{
			alert("Please Select Life Certificate Flag.");
			document.getElementById("cmbSearchLifeCertFlag").focus();
			return false;
		}
	}
	return true;
}
function showList()
{
	if(validateSearchData())
	{
			document.frmLifeCerificate.action = "ifms.htm?actionFlag=getLifeCertificateList&SearchCrt="+document.getElementById("cmbSearchCrt").value
												+"&SearchType="+document.getElementById("cmbSearchType").value
												+"&SearchValue="+document.getElementById("txtSeachValue").value
												+"&PageNo="+document.getElementById("txtPageNo").value
												+"&BankCode="+document.getElementById("cmbBankName").value
												+"&BranchCode="+document.getElementById("cmbBankBranch").value
												+"&LifeCertFlag="+document.getElementById("cmbSearchLifeCertFlag").value;
			document.frmLifeCerificate.submit();
	}
}

function printSelectedLifeCertificates()
{
	getPensionerCode();
	
	if(pensionerCode.length > 0)
	{
		showProgressbar();
		var url = "ifms.htm?actionFlag=generateLifeCertificateList&pensionerCode="+pensionerCode;
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "PrintLifeCertificate", urlstyle);
		
//		document.frmLifeCerificate.action = "ifms.htm?actionFlag=generateLifeCertificateList&pensionerCode="+pensionerCode;
//		document.frmLifeCerificate.method = "post";
//		document.frmLifeCerificate.submit();
		hideProgressbar();
	}
	else
	{
		alert("Please Select Atleast One Certificate to Print");
	}
}


//receive life certificate
var ppoNo=new Array();


function validatePPONo()
{
	if(document.getElementById("txtPpoNo").value != "")
	{
		var uri;
		uri = 'ifms.htm?actionFlag=validationOfPPONo';
		validatePPOUsingAjax(uri);
	}
}

function validatePPOUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&PpoNo="+document.getElementById("txtPpoNo").value,
		        onSuccess: function(myAjax) {
					validatePPOOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//           
//   xmlHttp.onreadystatechange=validatePPOOnStateChanged;
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(uri);
   
}


function validatePPOOnStateChanged(myAjax) 
{ 
	   var XMLDoc =  myAjax.responseXML.documentElement;
	  
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   
	   if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
	     {
	    	 if(validPpoNumber(ppoNo,document.getElementById("txtPpoNo").value))
	    	 {
	    	 	getPensionerDetails();
	    	 }
	    	 
	     }
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct PPO Number.");
	    	 document.getElementById("txtPpoNo").value="";
	    	 document.getElementById("txtPpoNo").focus();
	     }
	   
//   if (xmlHttp.readyState==complete_state)
//   { 
//	  var XMLDoc=xmlHttp.responseXML.documentElement;
//    
//      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//     
//     if(XmlHiddenValues[0].childNodes[1].text == 'Y')
//     {
//    	 if(validPpoNumber(ppoNo,document.getElementById("txtPpoNo").value))
//    	 {
//    	 	getPensionerDetails();
//    	 }
//    	 
//     }
//     if(XmlHiddenValues[0].childNodes[1].text == 'N')
//     {
//    	 alert("Please Enter Correct PPO Number.");
//    	 document.getElementById("txtPpoNo").value="";
//    	 document.getElementById("txtPpoNo").focus();
//     }
//
//   }
}
function getPensionerDetails()
{
	if(document.getElementById("txtPpoNo").value != '')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getPnsnrDtlsFromPpoNo';
		getPnsnrDtlsUsingAjax(uri);
	}
   
}
function getPnsnrDtlsUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&PpoNo="+document.getElementById("txtPpoNo").value,
		        onSuccess: function(myAjax) {
					getPnsnrDtlsOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//        
//   xmlHttp.onreadystatechange=onCaseStateChanged;
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(uri);
   
}


function getPnsnrDtlsOnStateChanged(myAjax) 
{ 
	var XMLDoc =  myAjax.responseXML.documentElement;
	  
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue != 'N')
    {
          document.getElementById("hdnPensionerId").value = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	     	      
	      if(XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue == 'Y')
	      {
	      	  document.getElementById("txtPnsnrName").value = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	      }
	      else
	      {
	    	  document.getElementById("txtPnsnrName").value=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue; 
	      }
	      if(XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue != "null" && XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue != "null")
	      {
	      document.getElementById("hdnLedgerPageNo").value = XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue+"/"+XmlHiddenValues[0].childNodes[5].childNodes[0].nodeValue;
	      }
	      document.getElementById("hdnAccountNo").value = XmlHiddenValues[0].childNodes[6].childNodes[0].nodeValue;
	      document.getElementById("hdnAliveFlag").value = XmlHiddenValues[0].childNodes[7].childNodes[0].nodeValue;
	      document.getElementById("hdnBankName").value = XmlHiddenValues[0].childNodes[8].childNodes[0].nodeValue;
	      document.getElementById("hdnBranchName").value = XmlHiddenValues[0].childNodes[9].childNodes[0].nodeValue;
  	  
    }
    else
    {
	  	  	alert("This PPO Life Certificate is already received.");
	        document.getElementById("txtPpoNo").value="";
	        document.getElementById("txtPpoNo").focus();
	        document.getElementById("hdnPensionerId").value="";
		   	document.getElementById("txtBundleNo").value="";
		    document.getElementById("txtPnsnrName").value="";
		    document.getElementById("hdnLedgerPageNo").value="";
		    document.getElementById("hdnAccountNo").value="";
     	    document.getElementById("hdnAliveFlag").value="";
     	    document.getElementById("hdnBankName").value="";
     	    document.getElementById("hdnBranchName").value="";
    }

//   if (xmlHttp.readyState==complete_state)
//   { 
//	  var XMLDoc=xmlHttp.responseXML.documentElement;
//    
//      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//   
//      if(XmlHiddenValues[0].childNodes[0].text != 'N')
//      {
//          document.getElementById("hdnPensionerId").value=XmlHiddenValues[0].childNodes[0].text;
//  	     
//	      document.getElementById("hdnHeadCodeDesc").value=XmlHiddenValues[0].childNodes[2].text;
//	      if(XmlHiddenValues[0].childNodes[8].text == 'Y')
//	      {
//	      	document.getElementById("txtPnsnrName").value=XmlHiddenValues[0].childNodes[3].text;
//	      }
//	      else
//	      {
//	    	  document.getElementById("txtPnsnrName").value=XmlHiddenValues[0].childNodes[4].text; 
//	      }
//	      document.getElementById("hdnRetirementDt").value=XmlHiddenValues[0].childNodes[5].text;
//	      document.getElementById("hdnCommencementDt").value=XmlHiddenValues[0].childNodes[6].text;
//	      document.getElementById("hdnAccountNo").value=XmlHiddenValues[0].childNodes[7].text;
//	      document.getElementById("hdnAliveFlag").value=XmlHiddenValues[0].childNodes[8].text;
//	      document.getElementById("hdnBankName").value=XmlHiddenValues[0].childNodes[9].text;
//	      document.getElementById("hdnBranchName").value=XmlHiddenValues[0].childNodes[10].text;
//    	  
//      }
//      else
//      {
//    	  alert("This PPO Life Certificate is already received.");
//          document.getElementById("txtPpoNo").value="";
//          document.getElementById("txtPpoNo").focus();
//          document.getElementById("hdnPensionerId").value="";
//     	  document.getElementById("hdnHeadCodeDesc").value="";
//     	  document.getElementById("txtBundleNo").value="";
//     	  document.getElementById("txtPnsnrName").value="";
//     	  document.getElementById("hdnRetirementDt").value="";
//       	  document.getElementById("hdnCommencementDt").value="";
//       	  document.getElementById("hdnAccountNo").value="";
//       	  document.getElementById("hdnAliveFlag").value="";
//       	  document.getElementById("hdnBankName").value="";
//       	  document.getElementById("hdnBranchName").value="";
//      }
//   }
}
function validateData()
{
	var flag = 0;
	if(isEmpty("txtPpoNo", PPONO)==false)
	{
		document.getElementById("txtPpoNo").focus();
		return false;
	}
	if(isEmpty("txtReceivedDate", RECEIVEDDATE)==false)
	{
		document.getElementById("txtReceivedDate").focus();
		return false;
	}
	if(isEmpty("txtPnsnrName", "Pensioner Name is required.")==false)
	{
		document.getElementById("txtPnsnrName").focus();
		return false;
	}
	if(document.getElementById("radioLifeCertFlagY").checked || document.getElementById("radioLifeCertFlagN").checked)
	{
		flag=1;
	}
	if(flag == 0)
	{
		alert("Please select life certificate flag.")
		return false;
	}
	
	return true;
}

function pnsnrDtlsTableAddRow()
{
	  if(validateData()==true)
	  {
     	var rowCnt = document.getElementById("hidPnsnrGridSize").value;
     	
    	var table=document.getElementById("tblPnsnrDtls");
    	var rowCount=table.rows.length;
    	var newRow = table.insertRow(rowCount); 
    	
     	//var newRow =  document.all("tblPnsnrDtls").insertRow();	
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
	   	var Cell8 = newRow.insertCell(7);
   		Cell8.className = "tds";
	   	Cell8.align="center";
	   	var Cell9 = newRow.insertCell(8);
   		Cell9.className = "tds";
	   	Cell9.align="center";
	   	var Cell10 = newRow.insertCell(9);
   		Cell10.className = "tds";
	   	Cell10.align="center";
	   	   
	  	Cell1.innerHTML = '<input type="hidden" name="txtPensionerId" id="txtPensionerId'+rowCnt+'" value="'+document.getElementById("hdnPensionerId").value+'"/><input type="hidden" name="txtBundleNumber" id="txtBundleNumber'+rowCnt+'" value="'+document.getElementById("txtBundleNo").value+'"/><input type="text" name="txtPpoNumber" id="txtPpoNumber'+rowCnt+'" size="15" value="'+ document.getElementById('txtPpoNo').value +'" readonly/>';
   		Cell2.innerHTML = '<input type="text" name="txtPensionerName" id="txtPensionerName'+rowCnt+'" size="18" value="'+ document.getElementById("txtPnsnrName").value +'" readonly/>';
   		Cell3.innerHTML = '<input type="text" name="txtLedgerPageNo" id="txtLedgerPageNo'+rowCnt+'" size="10" value="'+ document.getElementById("hdnLedgerPageNo").value +'" readonly/>'; 
   		Cell4.innerHTML = '<input type="text" name="txtBankName" id="txtBankName'+rowCnt+'" size="20" value="'+ document.getElementById("hdnBankName").value +'" readonly/>';
   		Cell5.innerHTML = '<input type="text" name="txtBranchName" id="txtBranchName'+rowCnt+'" size="15" value="'+ document.getElementById("hdnBranchName").value +'" readonly/>'; 
   		Cell6.innerHTML = '<input type="text" name="txtAccountNo" id="txtAccountNo'+rowCnt+'" size="15" value="'+ document.getElementById("hdnAccountNo").value +'" readonly/>'; 
   		Cell7.innerHTML = '<input type="text" name="txtLCReceivedDate" id="txtLCReceivedDate'+rowCnt+'" size="10" value="'+ document.getElementById("txtReceivedDate").value +'" readonly/>';
   		if(document.getElementById("radioLifeCertFlagY").checked)
   		{
   		Cell8.innerHTML = '<input type="text" name="txtLifeCertFlag" id="txtLifeCertFlag'+rowCnt+'" size="10" value="Y" readonly/>';
   		}
   		if(document.getElementById("radioLifeCertFlagN").checked)
   		{
   		Cell8.innerHTML = '<input type="text" name="txtLifeCertFlag" id="txtLifeCertFlag'+rowCnt+'" size="10" value="N" readonly/>';
   		}
   		Cell9.innerHTML = '<img name="Image'+Number(rowCnt)+'" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick="removePpoNo('+Number(rowCnt)+');RemoveTableRow(this,\'tblPnsnrDtls\')"  />';
   		ppoNo[Number(rowCnt)] =  document.getElementById("txtPpoNo").value;		   		                 
   		document.getElementById("hidPnsnrGridSize").value = Number(rowCnt)+1;
   		//document.getElementById("txtPpoNo").value="";
   		document.getElementById("hdnPensionerId").value="";
   		document.getElementById("txtBundleNo").value="";
   		document.getElementById("txtPnsnrName").value="";
   	 	document.getElementById("hdnLedgerPageNo").value="";
     	document.getElementById("hdnAccountNo").value="";
     	document.getElementById("hdnAliveFlag").value="";
     	document.getElementById("hdnBankName").value="";
     	document.getElementById("hdnBranchName").value="";
	 }
}

function removePpoNo(count)
{
	var ppo=document.getElementById("txtPpoNumber"+count).value;
	for(var i=0; i<ppoNo.length;i++ )
    { 
 		if(ppoNo[i]==ppo)
 		{
 	 		ppoNo.splice(i,1);
 		} 
 	} 
 	
}
function validPpoNumber(ppoNo,arrayElement)
{
   var flag=0;	
  
   for(var i=0; i<ppoNo.length;i++ )
   { 
	    if(ppoNo[i]==arrayElement)
 		{
 			flag=1;
 		} 
   }
   if(flag==1)
   {
	   alert("Same PPO number cannot be received twice.");
	   return false;
   } 
   return true;
}
function updateAllPPO()
{
	getPensionerCode();
	if(isEmpty("txtReceivedDate", RECEIVEDDATE)==false)
	{
		document.getElementById("txtReceivedDate").focus();
		return false;
	}
	if(document.getElementById("cmbLifeCertFlag").value == "-1")
	{
		alert("Please select Life Certificate Flag.");
		document.getElementById("cmbLifeCertFlag").focus();
		return false;
	}
	var receivedDate = document.getElementById("txtReceivedDate").value;
	var lifeCertFlag = document.getElementById("cmbLifeCertFlag").value;
	if(pensionerCode.length > 0)
	{
	   var url = 'ifms.htm?actionFlag=insertPensionerSeenDtls&pensionerCode='+pensionerCode + '&receiveDate=' + receivedDate + '&lifeCertFlag='+lifeCertFlag;
       updateAllPPOUsingAjax(url);
 
	}
	else
	{
		alert("Please Select Atleast One Certificate.");
	}
}
function updateAllPPOUsingAjax(uri)
{

	var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	       
	        onSuccess: function(myAjax) {
	        	updateAllPPOOnStateChanged(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...')} 
	          } );
}
function updateAllPPOOnStateChanged(myAjax)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
	  {
		  alert("Updated Successfully.");
		   
		  self.location.href="ifms.htm?actionFlag=getLifeCertificateList&SearchCrt="+document.getElementById("cmbSearchCrt").value
												+"&SearchType="+document.getElementById("cmbSearchType").value
												+"&SearchValue="+document.getElementById("txtSeachValue").value
												+"&PageNo="+document.getElementById("txtPageNo").value
												+"&BankCode="+document.getElementById("cmbBankName").value
												+"&BranchCode="+document.getElementById("cmbBankBranch").value
												+"&LifeCertFlag="+document.getElementById("cmbSearchLifeCertFlag").value;
	  }
	  else
	  {
		  alert("Some Problem Occurred During Updation.Please Try Again");		
	  }
}
function forwardCertificateToAto()
{
		var uri;
		uri = 'ifms.htm?actionFlag=insertPensionerSeenDtls';
		getForwardCertUsingAjax(uri);
}
 
function getForwardCertUsingAjax(uri)
{
	var url = runForm(0);   
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					onCaseStateChangedForwardCert(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//   var url = runForm(0);      
//   xmlHttp.onreadystatechange=onCaseStateChangedForwardCert;
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(url);
   
}


function onCaseStateChangedForwardCert(myAjax) 
{ 
	  var XMLDoc =  myAjax.responseXML.documentElement;
	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
	  {
		  alert("Forwarded Successfully.");
		  for(var i = document.getElementById("tblPnsnrDtls").rows.length; i > 1;i--)
	      {
			  document.getElementById("tblPnsnrDtls").deleteRow(i -1);
	      }  
		 
	  }
	  else
	  {
		  alert("Some Problem Occurred During Forwad.Please Try Again");		
	  }
	 

//	if (xmlHttp.readyState==complete_state)
//	   { 
//		 var XMLDoc=xmlHttp.responseXML.documentElement;
//		    
//	      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//	   	     
//		  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
//		  {
//			  alert("Forwarded Successfully.");
//			 
//		  }
//		  else
//		  {
//			  alert("Some Problem Occurred During Forwad.Please Try Again");		
//		  }
//		  for(var i = document.getElementById("tblPnsnrDtls").rows.length; i > 1;i--)
//	      {
//	    	document.getElementById("tblPnsnrDtls").deleteRow(i -1);
//	      }  
//
//	   }
}
var pnsnrCodeLifeCertFlag="";
function getPensionerCodeLifeCertFlag()
{
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxPensionerCode");
	pnsnrCodeLifeCertFlag="";
	var lifeCertFlag="";
	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					lifeCertFlag = document.getElementById("hdnLifeCertFlag"+rowNum).value;
					if(flag==0)
					{
						flag=1;
						pnsnrCodeLifeCertFlag=arrChkBox[i].value + "*" +lifeCertFlag;
						
					}
					else
					{
						pnsnrCodeLifeCertFlag=pnsnrCodeLifeCertFlag+"~"+arrChkBox[i].value + "*" +lifeCertFlag;
					    
					}
					
				}
			}
		}
	}
						
}
//receive life certificate list



function approveLifeCertificate()
{
	getPensionerCodeLifeCertFlag();
	if(pnsnrCodeLifeCertFlag.length > 0)
	{
	   var uri;
	   
	   uri = 'ifms.htm?actionFlag=approveLifeCertificates&pnsnrCodeLifeCertFlag='+pnsnrCodeLifeCertFlag;
	   approveLifeCertUsingAjax(uri);
//	   xmlHttp=GetXmlHttpObject();
//
//	   if (xmlHttp==null)
//	   {
//	      return;
//	   }  
//	   xmlHttp.onreadystatechange=approveLifeCertOnStateChanged;
//	   xmlHttp.open("POST",uri,true);
//	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	   xmlHttp.send(uri);
	}
	else
	{
		alert("Please Select Atleast One Certificate to Approve");
	}
	
}

function approveLifeCertUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					approveLifeCertOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function approveLifeCertOnStateChanged(myAjax)
{
	  var XMLDoc =  myAjax.responseXML.documentElement;
	   
	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	  
	  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
	  {
		  alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue);
		  window.location.href = "ifms.htm?actionFlag=getReceivedLifeCertList";
		
	  }
	  else
	  {
		  alert("Some Problem Occurred During Approve.Please Try Again");	
		  
	  }
	  
//	   if (xmlHttp.readyState==complete_state)
//	   { 
//		  var XMLDoc=xmlHttp.responseXML.documentElement;
//	        
//	      var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
//		  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
//		  {
//			  alert(XmlHiddenValues[0].text);
//			  window.location.href = "ifms.htm?actionFlag=getReceivedLifeCertList";
//			
//		  }
//		  else
//		  {
//			  alert("Some Problem Occurred During Approve.Please Try Again");	
//			  
//		  }
//	        
//
//	   }
}
