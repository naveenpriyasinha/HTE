var Row_ID_Vchr=0;

function voucherDtlsTableAddRow()
{		
	Row_ID_Vchr = document.getElementById("hidVoucherGridSize").value;
	var newRow =  document.all("tblVoucherDtls").insertRow();			  		
	 	
   	var Cell1 = newRow.insertCell();
   	Cell1.className = "tds";
   	Cell1.align="center";
   	var Cell2 = newRow.insertCell();	
   	Cell2.className = "tds";
   	Cell2.align="center";
   	var Cell3 = newRow.insertCell();
   	Cell3.className = "tds";
   	Cell3.align="center";
   	var Cell4 = newRow.insertCell();
	Cell4.className = "tds";
   	Cell4.align="center";
	  	
   	Cell1.innerHTML = '<select name="cmbVoucherMonth" id="cmbVoucherMonth'+Number(Row_ID_Vchr)+'" ><option value="-1">--Select--</option>' +LISTMONTHS +'</select>'
   		+'&nbsp;<select name="cmbVoucherYear" id="cmbVoucherYear'+Number(Row_ID_Vchr)+'" ><option value="-1">--Select--</option>'+ LISTYEARS +'</select>';
	Cell2.innerHTML = '<input type="text" name="txtVoucherNo" id="txtVoucherNo'+Row_ID_Vchr+'" size="15" /><input type="hidden" name="hdnProvVoucherDtlsId" id="hdnProvVoucherDtlsId'+Row_ID_Vchr+'" />';      	
   	Cell3.innerHTML = '<input type="text" name="txtVoucherDate" id="txtVoucherDate'+Row_ID_Vchr+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtVoucherDate'+Row_ID_Vchr+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Vchr)+');" />';   
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblVoucherDtls\')" /> ';   	
   	document.getElementById("hidVoucherGridSize").value = Number(Row_ID_Vchr)+1;  
}


function getHeadCodeDesc()
{
	if(document.getElementById("cmbHeadCode").value != '-1')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getHeadCodeDesc&headCode='+document.getElementById("cmbHeadCode").value+
		'&stateCode='+document.getElementById("cmbStateCode").value;
		
		//getHeadCodeDescUsingAjax(uri);
		getHeadCodeDescUsingAJAX(uri);
	}
	else
	{
		document.getElementById("txtHeadCodeDesc").value="";
		document.getElementById("txtDpRate").value="";
		document.getElementById("hidDpRate").value="";
		document.getElementById("txtDpAmount").value="";
	}
   
}
//new AJAX call
function getHeadCodeDescUsingAJAX(url)
{

	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&headCode="+document.getElementById("cmbHeadCode").value,
		        onSuccess: function(myAjax) {
					onCaseStateChangedForHeadCode(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
 function onCaseStateChangedForHeadCode(myAjax){
	 
	 XMLDoc = myAjax.responseXML.documentElement;
	    
     var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
     
     document.getElementById("txtHeadCodeDesc").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
    
     if(XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue != 0)
	  {
   	  if(document.getElementById("cmbRopType").value == "2006")
   	  {
   		  document.getElementById("txtDpRate").value="0.00";
   	  }
   	  if(document.getElementById("radioDpMergeY").value == 'Y' && document.getElementById("cmbRopType").value == "1996")
   	  {
   		  document.getElementById("txtDpRate").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
   	  }
   	  document.getElementById("hidDpRate").value=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	  }
     else
     {
   	  document.getElementById("txtDpRate").value="0.00";
   	  document.getElementById("hidDpRate").value="0.00";
     }
 }
//function getHeadCodeDescUsingAjax(uri)
//{
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
//   
//}

//Old Call
//function onCaseStateChanged() 
//{ 
//
//   if (xmlHttp.readyState==complete_state)
//   { 
//	  var XMLDoc=xmlHttp.responseXML.documentElement;
//    
//      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//      
//      document.getElementById("txtHeadCodeDesc").value=XmlHiddenValues[0].childNodes[1].text;
//     
//      if(XmlHiddenValues[0].childNodes[2].text != 0)
//	  {
//    	  if(document.getElementById("cmbRopType").value == "2006")
//    	  {
//    		  document.getElementById("txtDpRate").value="0.00";
//    	  }
//    	  if(document.getElementById("radioDpMergeY").value == 'Y' && document.getElementById("cmbRopType").value == "1996")
//    	  {
//    		  document.getElementById("txtDpRate").value=XmlHiddenValues[0].childNodes[2].text;
//    	  }
//    	  document.getElementById("hidDpRate").value=XmlHiddenValues[0].childNodes[2].text;
//	  }
//      else
//      {
//    	  document.getElementById("txtDpRate").value="0.00";
//    	  document.getElementById("hidDpRate").value="0.00";
//      }
//   }
//}
//

function getIfscCodeFromBrachCode()
{
	if(document.getElementById("cmbBankBranch").value != '-1')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getIfscCodeFromBranchCode&branchCode='+document.getElementById("cmbBankBranch").value;
		//getIfscCodeUsingAjax(uri);
		getIFSCCode(uri);
	}
	
}
//new AJAX Call

function getIFSCCode(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&branchCode="+document.getElementById("cmbBankBranch").value,
		        onSuccess: function(myAjax) {
					getIfscCodeOnStateChangedUsingAJAX(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getIfscCodeOnStateChangedUsingAJAX(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
    
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    
    document.getElementById("txtIfscCode").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
    document.getElementById("txtBankBranchCode").value=document.getElementById("cmbBankBranch").value;
}
//old call
//function getIfscCodeUsingAjax(uri)
//{
//	   xmlHttp=GetXmlHttpObject();
//
//	   if (xmlHttp==null)
//	   {
//	      return;
//	   }  
//	   xmlHttp.onreadystatechange=getIfscCodeOnStateChanged;
//	   xmlHttp.open("POST",uri,false);
//	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	   xmlHttp.send(uri);
//}
//
//function getIfscCodeOnStateChanged()
//{
//	if (xmlHttp.readyState==complete_state)
//	   { 
//		  var XMLDoc=xmlHttp.responseXML.documentElement;
//	    
//	      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//	      
//	      document.getElementById("txtIfscCode").value=XmlHiddenValues[0].childNodes[1].text;
//	      document.getElementById("txtBankBranchCode").value=document.getElementById("cmbBankBranch").value;
//	   }
//}
//Old call
function getBankBranchFrmBnkCode(object)
{getBankBranchFrmBnkCodeUsingAJAX(object);
	
//	
//	var elementId=object.id;
//	
//	var branchCode=document.getElementById(elementId).value;
//	var resultElementBankCode="cmbBankCode";
//	var resultElementBranchName="cmbBankBranch";
//	
//	if(document.getElementById(elementId).value != "")
//	{
//	
//	xmlHttp=GetXmlHttpObject();
//
//	if (xmlHttp==null)
//	{
//		alert ("Your browser does not support AJAX!");
//		return;
//	} 
//
//	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
//	
//	
//	
//	xmlHttp.onreadystatechange=function getDataStateChangedForBnkBrnchAudiName()
//	{
//		if (xmlHttp.readyState==4)
//		{ 
//			if(xmlHttp.status==200)
//			{
//				XMLDoc = xmlHttp.responseXML.documentElement;
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//				if(XmlHiddenValues[0].childNodes.length != 0)
//				{
//				var branchName=XmlHiddenValues[0].childNodes[0].text;
//				
//				var bankCode=XmlHiddenValues[0].childNodes[1].text;
//				var bankName=XmlHiddenValues[0].childNodes[2].text;
//				var auditorName=XmlHiddenValues[0].childNodes[3].text;
//			
//				document.getElementById(resultElementBankCode).options[0].value=bankCode;
//				document.getElementById(resultElementBankCode).options[0].text=bankName;
//				document.getElementById(resultElementBankCode).options[0].selected="selected";
//				document.getElementById(resultElementBankCode).disabled=true;
//				
//				document.getElementById(resultElementBranchName).options[0].value=branchCode;
//				document.getElementById(resultElementBranchName).options[0].text=branchName;
//				document.getElementById(resultElementBranchName).options[0].selected="selected";
//				document.getElementById(resultElementBranchName).disabled=true;
//				
//				}
//				else
//				{
//					alert("Please Enter Correct Branch Code.");
//					document.getElementById(elementId).value="";
//					var theOption = new Option;
//					theOption.value = "-1";
//					theOption.text ="--SELECT--";
//					document.getElementById(resultElementBankCode).options[0]=theOption;
//			 		document.getElementById(resultElementBankCode).options[0].selected="selected";
//			 		document.getElementById(resultElementBankCode).disabled=false;
//					theOption = new Option;
//					theOption.value = "-1";
//					theOption.text ="--SELECT--";
//			 		document.getElementById(resultElementBranchName).options.length = 0;
//			 		document.getElementById(resultElementBranchName).options[0]=theOption;
//			 		document.getElementById(resultElementBranchName).options[0].selected="selected";
//			 		document.getElementById(resultElementBranchName).disabled=false;
//			 		document.getElementById(elementId).focus();
//							
//				}
//			}
//		}
//	};
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(uri);
//	getIfscCodeFromBrachCode();
//	}
//	else
//	{
//		document.getElementById(resultElementBankCode).disabled=false;
//		document.getElementById(resultElementBranchName).disabled=false;
//		document.getElementById("txtIfscCode").value="";
//	}
}
//new call
function getBankBranchFrmBnkCodeUsingAJAX(object)
{
	var elementId=object.id;
	
	var branchCode=document.getElementById(elementId).value;
	var resultElementBankCode="cmbBankCode";
	var resultElementBranchName="cmbBankBranch";
	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
	if(document.getElementById(elementId).value != "")
	{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&branchCode="+branchCode,
		        onSuccess: function(myAjax) {
					getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	getIfscCodeFromBrachCode();
	}else
	{
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
		document.getElementById("txtIfscCode").value="";
	}
}
function getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId){
	
	XMLDoc =  myAjax.responseXML.documentElement;
	
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	if(XmlHiddenValues[0].childNodes.length != 0)
	{
	var branchName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	var bankCode=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var bankName=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var auditorName=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;

	document.getElementById(resultElementBankCode).options[0].value=bankCode;
	document.getElementById(resultElementBankCode).options[0].text=bankName;
	document.getElementById(resultElementBankCode).options[0].selected="selected";
	document.getElementById(resultElementBankCode).disabled=true;
	
	document.getElementById(resultElementBranchName).options[0].value=branchCode;
	document.getElementById(resultElementBranchName).options[0].text=branchName;
	document.getElementById(resultElementBranchName).options[0].selected="selected";
	document.getElementById(resultElementBranchName).disabled=true;
	
	}
	else
	{
		alert("Please Enter Correct Branch Code.");
		document.getElementById(elementId).value="";
		var theOption = new Option;
		theOption.value = "-1";
		theOption.text ="--SELECT--";
		document.getElementById(resultElementBankCode).options[0]=theOption;
 		document.getElementById(resultElementBankCode).options[0].selected="selected";
 		document.getElementById(resultElementBankCode).disabled=false;
		theOption = new Option;
		theOption.value = "-1";
		theOption.text ="--SELECT--";
 		document.getElementById(resultElementBranchName).options.length = 0;
 		document.getElementById(resultElementBranchName).options[0]=theOption;
 		document.getElementById(resultElementBranchName).options[0].selected="selected";
 		document.getElementById(resultElementBranchName).disabled=false;
 		document.getElementById(elementId).focus();
				
	}
}
function calculateDPAndDAAmounts()
{
	if(document.getElementById("txtBasicPensionAmt").value != "")
	{
	var BasicPension=Number(document.getElementById("txtBasicPensionAmt").value);
	var DPRate=Number(document.getElementById("txtDpRate").value);
	//var DARate=Number(document.getElementById("txtDaRate").value);
	
	//document.getElementById("txtAdpAmount").value="0.00";
	//var ADPAmount=Number(document.getElementById("txtAdpAmount").value);
	var DPAmount =(Number(DPRate * BasicPension))/100;  
	
	document.getElementById("txtDpAmount").value=DPAmount;
	//var DAAmount = (DARate * (BasicPension + DPAmount + ADPAmount))/100;
	//document.getElementById("txtDaAmount").value=DAAmount;
	setValidAmountFormat(document.getElementById("txtDpAmount"));
	
	//setValidAmountFormat(document.getElementById("txtDaAmount"));
	}
	
}

function calcGrossAmount()
{
	if(document.getElementById("txtBasicPensionAmt").value != "")
	{
	var BasicPension=Number(document.getElementById("txtBasicPensionAmt").value);
	var DPAmount=Number(document.getElementById("txtDpAmount").value);
	var DAAmount=Number(document.getElementById("txtDaAmount").value);
	var ArrearAmount=Number(document.getElementById("txtArrearAmount").value);
	var GrossAmount=BasicPension + DPAmount + DAAmount + ArrearAmount;
	document.getElementById("txtGrossAmount").value=GrossAmount;
	setValidAmountFormat(document.getElementById("txtGrossAmount"));
	}
}

function calcReducedPension()
{
	if(document.getElementById("txtBasicPensionAmt").value != "" && document.getElementById("txtCvpMonthly").value != "")
	{
		var BasicPension=Number(document.getElementById("txtBasicPensionAmt").value);
		var DPAmount=Number(document.getElementById("txtDpAmount").value);
	   //var ADPAmount=Number(document.getElementById("txtAdpAmount").value);
		var CVPMonthlyAmount=Number(document.getElementById("txtCvpMonthly").value);
		var ReducedPensionAmount;
		//if(document.getElementById("txtPensionableAmnt").value == "0.00" || document.getElementById("txtPensionableAmnt").value == "0" || document.getElementById("txtPensionableAmnt").value == "")
		if(document.getElementById("cmbRopType").value == "1996")
		{
			ReducedPensionAmount=(BasicPension + DPAmount) - CVPMonthlyAmount;
			document.getElementById("txtReducedPension").value=ReducedPensionAmount;
		}
		if(document.getElementById("cmbRopType").value == "2006")
		{
			ReducedPensionAmount=BasicPension  - CVPMonthlyAmount;
			document.getElementById("txtReducedPension").value=ReducedPensionAmount;
		}
		
		setValidAmountFormat(document.getElementById("txtReducedPension"));
	}
}
function onChangeROPType()
{
	
	if(document.getElementById("cmbRopType").value == "1996")
	{
		document.getElementById("radioDpMergeY").disabled=false;
		document.getElementById("radioDpMergeN").disabled=false;
		document.getElementById("radioDpMergeY").checked=false;
		document.getElementById("radioDpMergeN").checked=false;
		document.getElementById("chkFpForTenYears").disabled=true;
		document.getElementById("chkFpForTenYears").checked=false;
		document.getElementById("radioDpMergeY").checked=true;
		document.getElementById("txtDpRate").value=document.getElementById("hidDpRate").value;
		
	}
	else if(document.getElementById("cmbRopType").value == "1986")
	{
		document.getElementById("radioDpMergeY").disabled=true;
		document.getElementById("radioDpMergeN").disabled=true;
		document.getElementById("radioDpMergeY").checked=false;
		document.getElementById("radioDpMergeN").checked=false;
		document.getElementById("chkFpForTenYears").disabled=true;
		document.getElementById("chkFpForTenYears").checked=false;
		document.getElementById("txtDpRate").value="0.00";
		
	}
	else if(document.getElementById("cmbRopType").value == "2006")
	{
		document.getElementById("radioDpMergeY").disabled=true;
		document.getElementById("radioDpMergeN").disabled=true;
		document.getElementById("radioDpMergeY").checked=false;
		document.getElementById("radioDpMergeN").checked=false;
		document.getElementById("chkFpForTenYears").disabled=false;
		document.getElementById("chkFpForTenYears").checked=false;
		document.getElementById("txtDpRate").value="0.00";
		//getDARateFromROPType(this);
	}
}



function onClickDPMerged(obj)
{
	if(obj.value=="Y")
	{
		document.getElementById("txtDpRate").value=document.getElementById("hidDpRate").value;
	}
	else
	{
		document.getElementById("hidDpRate").value=document.getElementById("txtDpRate").value;
		document.getElementById("txtDpRate").value="0.00";
	}
}

function getDARateFromROPType(object)
{
	if(document.getElementById("cmbRopType").value != '-1')
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getDARateFromROPType&ROPType='+document.getElementById("cmbRopType").value+'&DpMerged='+object.value+'&headCode='+document.getElementById("cmbHeadCode").value;
		getDARateUsingAjax(uri);
	}
}

function getDARateUsingAjax(uri)
{
   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
      return;
   }  
           
   xmlHttp.onreadystatechange=getDARateOnStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(uri);
   
}


function getDARateOnStateChanged() 
{ 

   if (xmlHttp.readyState==complete_state)
   { 
	  var XMLDoc=xmlHttp.responseXML.documentElement;
    
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
         
      document.getElementById("txtDaRate").value=XmlHiddenValues[0].childNodes[1].text;

   }
}

function resetFpDate()
{
	if(document.getElementById("chkFpForTenYears").checked)
	{
		document.getElementById("txtFp1Date").disabled=false;
		document.getElementById("txtFp2Date").disabled=false;
		document.getElementById("txtFp1Date").value="";
		document.getElementById("txtFp2Date").value="";
		
	}else
	{
		if(document.getElementById("hidFp1Date2").value != "")
		{
			if(compareDate(document.getElementById("hidFp1Date1").value ,document.getElementById("hidFp1Date2").value))
			{
				document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date1").value;
			}
			else
			{
				document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date2").value;
			}
		}
		else
		{
			document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date1").value;
		}
		document.getElementById("txtFp2Date").value=document.getElementById("hidFp2Date").value;
	}
}

function onChangeCVPFlag(object)
{
	if(object.value=='Y')
	{
		document.getElementById("txtCvpPaidDate").disabled=false;
		document.getElementById("imgCvpPaidDate").disabled=false;
	}
	else
	{
		document.getElementById("txtCvpPaidDate").disabled=true;
		document.getElementById("imgCvpPaidDate").disabled=true;
		document.getElementById("txtCvpPaidDate").value="";
	}
}
function onChangeDCRGFlag(object)
{
	if(object.value=='Y' || object.value=='P')
	{
		document.getElementById("txtDcrgPaidDate").disabled=false;
		document.getElementById("imgDcrgPaidDate").disabled=false;
	}
	else
	{
		document.getElementById("txtDcrgPaidDate").disabled=true;
		document.getElementById("imgDcrgPaidDate").disabled=true;
		document.getElementById("txtDcrgPaidDate").value="";
	}
}

function validateVoucherDetails()
{
	var voucherDtlCntLength=document.getElementById("hidVoucherGridSize").value;
	 if(document.getElementById('tblVoucherDtls').rows.length > 1)
	 {
		for(var rowCnt=0;rowCnt<Number(voucherDtlCntLength);rowCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("cmbVoucherMonth"+rowCnt,VOUCHERMONTH,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbVoucherYear"+rowCnt,VOUCHERYEAR,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtVoucherNo"+rowCnt,VOUCHERNO,'1')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtVoucherDate"+rowCnt,VOUCHERDATE,'1')==false)
				{
					return false;
				}
								 
			}
			catch(ex)
			{
				
			}
		}
	 }
	 return true;
}

function resetIfscCode()
{
	if(document.getElementById("cmbBankCode").value=='-1')
	{
		document.getElementById("txtBankBranchCode").value="";
		document.getElementById("txtIfscCode").value="";
	}
}
function getAuditorNameFromBranchCode()
{
	uri="ifms.htm?actionFlag=getAudiNms";
	var branchCode=document.getElementById("cmbBankBranch").value;
	
	if(branchCode != -1)
	{
		document.getElementById("txtBankBranchCode").value = branchCode;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
						getDataStateChangedForAudiName(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
		
		
//		xmlHttp=GetXmlHttpObject();
//	
//		if (xmlHttp==null)
//		{
//			alert ("Your browser does not support AJAX!");
//			return;
//		} 
//	
//		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
//		
//		
//		xmlHttp.onreadystatechange=function getDataStateChangedForAudiName()
//		{
//			if (xmlHttp.readyState==4)
//			{ 
//				if(xmlHttp.status==200)
//				{
//					XMLDoc = xmlHttp.responseXML.documentElement;
//					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//					var auditorName=XmlHiddenValues[0].childNodes[0].text;
//					var auditorPostId = XmlHiddenValues[0].childNodes[1].text;
//					if(auditorName != "")
//					{
//						
//					}
//					else{
//						alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
//						document.getElementById("txtBankBranchCode").value ="";
//						document.getElementById("cmbBankBranch").value="-1";
//						document.getElementById("txtIfscCode").value="";
//					}
//				}
//			}
//		};
//		xmlHttp.open("POST",uri,false);
//		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		xmlHttp.send(uri);
	}
	
}
function getDataStateChangedForAudiName(myAjax)
{
	 var auditorName = "";
	 var auditorPostId = "";
	 var XMLDoc =  myAjax.responseXML.documentElement;
	 if(XMLDoc != null)
	 {
	    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    if(XmlHiddenValues[0].childNodes[0].childNodes[0] != undefined)
	    {
	    	auditorName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	    }
	    if(XmlHiddenValues[0].childNodes[1].childNodes[0] != undefined)
	    {
	    	auditorPostId = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	    }
		if(auditorName == "")
		{
			alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
			document.getElementById("txtBankBranchCode").value ="";
			document.getElementById("cmbBankBranch").value="-1";
			document.getElementById("txtIfscCode").value="";
		}
	 } 
//	if (xmlHttp.readyState==4)
//	{ 
//		if(xmlHttp.status==200)
//		{
//			XMLDoc = xmlHttp.responseXML.documentElement;
//			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//			var auditorName=XmlHiddenValues[0].childNodes[0].text;
//			var auditorPostId = XmlHiddenValues[0].childNodes[1].text;
//			if(auditorName != "")
//			{
//				
//			}
//			else{
//				alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
//				document.getElementById("txtBankBranchCode").value ="";
//				document.getElementById("cmbBankBranch").value="-1";
//				document.getElementById("txtIfscCode").value="";
//			}
//		}
//	}
}
function openBillDtls()
{
	var height = screen.height - 100;
	var width = screen.width;
	var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId=9&PPONo='+document.getElementById("txtPpoNo").value+'&isNewSavingBill=Y&ViewFlag=Y';
	
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";

	window.open(urlstring,"VoucherDetails", urlstyle);
}

function showCommutationHistory()
{
	if(document.getElementById("hdnPensionerCode").value != "")
	{
		var newWindow;
		var height = screen.height - 100;
		var width = screen.width;
		var url='ifms.htm?actionFlag=loadCommutationHistory&PensionerCode='+document.getElementById("hdnPensionerCode").value;
		var urlstyle = "height=300,width=1000,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		newWindow = window.open(url, "CommutationHistory", urlstyle);
	}
	else
	{
		alert("Please save the case incase pension case is not saved.");
	}
}

function provPnsnDtlsTableAddRow()
{	
	Row_ID_Prov = document.getElementById("hidProvPnsnGridSize").value;		
	var table=document.getElementById("tblProvPnsnDtls");
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
   	var Cell8 = newRow.insertCell(7);
	Cell8.className = "tds";
   	Cell8.align="center";
   	var Cell9 = newRow.insertCell(8);
	Cell9.className = "tds";
   	Cell9.align="center";
   	var Cell10 = newRow.insertCell(9);
	Cell10.className = "tds";
   	Cell10.align="center";
   	var Cell11 = newRow.insertCell(10);
	Cell11.className = "tds";
   	Cell11.align="center";
     	
   	Cell1.innerHTML = '<input type="hidden" name="hdnProvPnsnDtlsId" id="hdnProvPnsnDtlsId'+Row_ID_Prov+'"/><input type="text" name="txtSancAuthName" id="txtSancAuthName'+Row_ID_Prov+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" onkeypress="upperCase(event)" maxlength="60"/>';
   	Cell2.innerHTML = '<input type="text" name="txtSancAuthNo" id="txtSancAuthNo'+Row_ID_Prov+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="20"/>';
	Cell3.innerHTML = '<input type="text" name="txtSanctionedDate" id="txtSanctionedDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtSanctionedDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
	Cell4.innerHTML = '<input type="text" name="txtApplnFromDate" id="txtApplnFromDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnFromDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
	Cell5.innerHTML = '<input type="text" name="txtApplnToDate" id="txtApplnToDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtApplnToDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';
	Cell6.innerHTML = '<select name="cmbBillType" id="cmbBillType'+Row_ID_Prov+'" ><option value="-1">--Select--</option>'+LISTBILLTYPE+'</select>';
	Cell7.innerHTML = '<input type="text" name="txtPaidAmount" id="txtPaidAmount'+Row_ID_Prov+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>';
	Cell8.innerHTML = '<input type="text" name="txtProVoucherNo" id="txtProVoucherNo'+Row_ID_Prov+'" size="15" maxlength="20"/>';      	
   	Cell9.innerHTML = '<input type="text" name="txtProVoucherDate" id="txtProVoucherDate'+Row_ID_Prov+'" maxlength="10" size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtProVoucherDate'+Row_ID_Prov+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Prov)+');" />';   
   	Cell10.innerHTML = '<select name="cmbChargedVoted" id="cmbChargedVoted'+Row_ID_Prov+'" ><option value="-1">--Select--</option>'+LISTCHRGVOTD+'</select>';
	Cell11.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblProvPnsnDtls\')" /> ';
	document.getElementById("hidProvPnsnGridSize").value = Number(Row_ID_Prov)+1;  		
}


function ProvPenEnableDisableAddRow(obj)
{					
	if(obj.value=='Y')
	{
		document.getElementById("btnProvPnsnDtlsAddRow").disabled=false;
	}
	else
	{
		if(document.getElementById('tblProvPnsnDtls').rows.length > 1)
		{
			alert("You cannot select No,until Provisional pension detail is there");
			document.getElementById(obj.id).checked=false;
			document.getElementById("radioProvPnsnY").checked="checked";
		}
		else
		{
			document.getElementById("btnProvPnsnDtlsAddRow").disabled=true;
		}
	}	
}

function isEmptyProvPnsnDtls()
{	
	var provPenLength=document.getElementById("hidProvPnsnGridSize").value;			
	if(document.getElementById('tblProvPnsnDtls').rows.length > 1)
	{		
		for(var rowCnt=0;rowCnt<Number(provPenLength);rowCnt++)
		{				
				try
				{							
					if(IsEmptyFun("txtSancAuthName"+rowCnt,SANCAUTHNAME,"1")==false)
					{						
						return false;						
					}										
					else if(IsEmptyFun("txtApplnFromDate"+rowCnt,APPLNFROMDATE,"1")==false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtApplnToDate"+rowCnt,APPLNTODATE,"1")==false)
					{						
						return false;
					}
					else if(IsEmptyFun("cmbBillType"+rowCnt,BILLTYPE,"1") == false)
					{												
						return false;
					}
					else if(IsEmptyFun("txtPaidAmount"+rowCnt,AMOUNT,"1")==false)
					{						
						return false;
					}					
					else if(IsEmptyFun("cmbChargedVoted"+rowCnt,CHARGEDVOTED,"1") == false)					
					{												
						return false;
					}
					else if(compareDate(document.getElementById("txtApplnFromDate"+rowCnt).value,document.getElementById("txtApplnToDate"+rowCnt).value) == false)
					{					
						alert('Applicable To Date should be greater than Applicable From Date');						
						goToFieldTab("txtApplnToDate"+rowCnt,1);
						document.getElementById("txtApplnToDate"+rowCnt).value = "";
						return false;
					}
				}
				catch(ex)
				{
					
				}				
		}	
			
	}	
	return true;
		
}
function onChangeBasicPension()
{
	var oldBasicPnsnAmount = document.getElementById("hdnBasicPensionAmt").value;
	var newBasicPnsnAmount = document.getElementById("txtBasicPensionAmt").value;
	if(document.getElementById("hdnShowCaseFor").value == '15' && oldBasicPnsnAmount != newBasicPnsnAmount)
	{
		alert("You have changed basic Pension, Please change allocation");
	}
}

function enableCommutationDtls(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("txtCvpOrderNo").disabled=false;
		document.getElementById("txtCvpPaidDate").disabled=false;
		document.getElementById("txtCvpVoucherNo").disabled=false;
		document.getElementById("txtCvpVoucherDate").disabled=false;
		document.getElementById("txtCvpPayableAmount").disabled=false;
		document.getElementById("txtTotalCvpAmount").disabled=false;
		
	}
	else
	{
		document.getElementById("txtCvpOrderNo").disabled=true;
		document.getElementById("txtCvpPaidDate").disabled=true;
		document.getElementById("txtCvpVoucherNo").disabled=true;
		document.getElementById("txtCvpVoucherDate").disabled=true;
		document.getElementById("txtCvpPayableAmount").disabled=true;
		document.getElementById("txtTotalCvpAmount").disabled=true;
		document.getElementById("txtCvpOrderNo").value="";
		document.getElementById("txtCvpPaidDate").value="";
		document.getElementById("txtCvpVoucherNo").value="";
		document.getElementById("txtCvpVoucherDate").value="";
		document.getElementById("txtCvpPayableAmount").value="";
		document.getElementById("txtTotalCvpAmount").value="";
		
	}
}

function enableDCRGDtls(obj)
{
	if(obj.value=='Y')
	{
		document.getElementById("txtDcrgOrder").disabled=false;
		document.getElementById("txtDcrgPaidDate").disabled=false;
		document.getElementById("txtDcrgVoucherNo").disabled=false;
		document.getElementById("txtDcrgVoucherDate").disabled=false;
		document.getElementById("txtTotalDcrgAmount").disabled=false;
		document.getElementById("txtDcrgPayableAmount").disabled=false;
		
	}
	else
	{
		document.getElementById("txtDcrgOrder").disabled=true;
		document.getElementById("txtDcrgPaidDate").disabled=true;
		document.getElementById("txtDcrgVoucherNo").disabled=true;
		document.getElementById("txtDcrgVoucherDate").disabled=true;
		document.getElementById("txtTotalDcrgAmount").disabled=true;
		document.getElementById("txtDcrgPayableAmount").disabled=true;
		document.getElementById("txtDcrgOrder").value="";
		document.getElementById("txtDcrgPaidDate").value="";
		document.getElementById("txtDcrgVoucherNo").value="";
		document.getElementById("txtDcrgVoucherDate").value="";
		document.getElementById("txtTotalDcrgAmount").value="";
		document.getElementById("txtDcrgPayableAmount").value="";
		
	}
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

function validatePunishmentCut()
{
	var pnshmntCutLength=document.getElementById("hidPnshmntCutSize").value;
	var commencementDt = document.getElementById("txtDateOfCommencement").value;
	if(document.getElementById('tblPnshmntCut').rows.length > 1)
	{		
		for(var rowCnt=0;rowCnt<Number(pnshmntCutLength);rowCnt++)
		{				
				try
				{		
					var punishmentFromDt =  document.getElementById("txtPnshmntFromDate"+rowCnt).value;
					var punishmentToDt = document.getElementById("txtPnshmntToDate"+rowCnt).value;
					if(IsEmptyFun("txtPnshmntAmount"+rowCnt,AMOUNT,"1")==false)
					{						
						return false;						
					}										
					else if(IsEmptyFun("txtPnshmntFromDate"+rowCnt,FROMDATE,"1")==false)
					{						
						return false;
					}
					else if(IsEmptyFun("txtPnshmntToDate"+rowCnt,TODATE,"1")==false)
					{						
						return false;
					}
					else if(compareDate(commencementDt,punishmentFromDt) == false)
					{
						alert("Punishment From Date should be greater than Commencement Date.");
						goToFieldTab("txtPnshmntFromDate"+rowCnt,1);
						return false;
					}
					else if(compareDate(punishmentFromDt,punishmentToDt) == false)
					{					
						alert('Punishment To Date should be greater than Punishment From Date.');						
						goToFieldTab("txtPnshmntToDate"+rowCnt,1);
						return false;
					}
					
				}
				catch (ex) {
				
				}
		}
	}
	return true;
}

function onChangePaymentScheme()
{
	var url="ifms.htm?actionFlag=getBankBranchFromPaymentScheme";
	if(document.getElementById("cmbPaymentScheme").value != "-1")
	{
		if(document.getElementById("cmbPaymentScheme").value == MONEYORDER)
		{
			document.getElementById("cmbBankCode").value=BANKCODEFORMONEYORDER;
			getBankBranchFromPaymentScheme(url);
		}
		else
		{
			document.getElementById("cmbBankBranch").disabled=false;
			document.getElementById("cmbBankCode").disabled=false;
			document.getElementById("cmbBankBranch").options.length = 0;
			var theOption = new Option;
			theOption.value="-1";
			theOption.text ="-- Select --";
			document.getElementById("cmbBankBranch").options[0]=theOption;
			document.getElementById("cmbBankBranch").value="-1";
			document.getElementById("cmbBankCode").value="-1";
			document.getElementById("txtBankBranchCode").value = "";
		}
	}
	else
	{
		document.getElementById("cmbBankBranch").disabled=false;
		document.getElementById("cmbBankCode").disabled=false;
	}
}

function getBankBranchFromPaymentScheme(url)
{
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        onSuccess: function(myAjax) {
					getBankBranchFrmPmntCodeOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getBankBranchFrmPmntCodeOnStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;

	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	document.getElementById("cmbBankBranch").options.length = 0;

	var theOption = new Option;
	theOption.value = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	theOption.text =XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	document.getElementById("cmbBankBranch").options[0]=theOption;
	document.getElementById("cmbBankBranch").options[0].selected="selected";
	document.getElementById("txtBankBranchCode").value = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	document.getElementById("txtAccountNo").value="";
	document.getElementById("txtIfscCode").value="";
	document.getElementById("cmbBankBranch").disabled=true;
	document.getElementById("cmbBankCode").disabled=true;
	
}

function setFp1FrmFp2Date()
{
	var lStrDate = document.getElementById("txtFp2Date").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById("txtFp1Date").value = (new String(""));
		return;
	}
	date.setDate(date.getDate()-1);

	if(date.getMonth()==11)
	 {
		document.getElementById("txtFp1Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();

	 }
	 else
	 {
		  document.getElementById("txtFp1Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();

	 }
}

function onClickFpAvailableFlag()
{
	if(document.getElementById("radioFpAvailableY").checked)
	{
	    document.getElementById("txtFp1Date").disabled = false;
	    document.getElementById("txtFp1amount").disabled = false;
	    document.getElementById("txtFp2Date").disabled = false;
	    document.getElementById("txtFp2amount").disabled = false;
	    document.getElementById("imgFp1Date").disabled = false;
	    document.getElementById("imgFp2Date").disabled = false;
	}
	else 
	{
		document.getElementById("txtFp1Date").disabled = true;
	    document.getElementById("txtFp1amount").disabled = true;
	    document.getElementById("txtFp2Date").disabled = true;
	    document.getElementById("txtFp2amount").disabled = true;
	    document.getElementById("imgFp1Date").disabled = true;
	    document.getElementById("imgFp2Date").disabled = true;
	    document.getElementById("txtFp1Date").value = "";
	    document.getElementById("txtFp1amount").value = "";
	    document.getElementById("txtFp2Date").value = "";
	    document.getElementById("txtFp2amount").value = "";
	}
}