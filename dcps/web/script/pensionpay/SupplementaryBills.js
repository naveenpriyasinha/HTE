var Row_ID_Rec=0;
var Row_ID_Bank=0;
var tempFlag=0;
function recoveryDtlsTableAddRow()
{		
	Row_ID_Rec = document.getElementById("hidRecoveryGridSize").value;
	
	var table=document.getElementById("tblRecoveryDtls");
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
   
   	if(document.getElementById("radioSupBillTypePnsn").checked)
   	{
   		Cell1.innerHTML = '<select name="cmbRecoveryType" id="cmbRecoveryType'+Row_ID_Rec+'"  > <option value="-1">--SELECT--</option>'+ LISTADVANCE + '</select>';
   	}
   	if(document.getElementById("radioSupBillTypeDcrg").checked)
   	{
   		Cell1.innerHTML = '<input name="txtNature" id="txtNature'+Row_ID_Rec+'"  maxlength="60"/> ';
   	}
	Cell2.innerHTML = '<input type="text" name="txtAmount" id="txtAmount'+Row_ID_Rec+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calRecoveryAmtTotal(this,\'txtAmount\'); calculateNetAmount();" onkeypress="amountFormat(this);"  style="text-align: right;"/>';      	
   	Cell3.innerHTML = '<input type="text" name="txtSchemeCode" id="txtSchemeCode'+Row_ID_Rec+'" onblur="validateSchemeCode(this);" size="15" />';
   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblRecoveryDtls\');calRecoveryAmtTotalForDelete(\'txtAmount\');calculateNetAmount();" /> ';
  	document.getElementById("hidRecoveryGridSize").value = Number(Row_ID_Rec)+1;  	
}


function bankDtlsTableAddRow()
{	
	Row_ID_Bank = document.getElementById("hidBankGridSize").value;
	var table=document.getElementById("tblBankDtls");
	var rowCount=table.rows.length;
	var newRow =  table.insertRow(rowCount);	
		
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
   	
   	
    Cell1.innerHTML = '<input type="text" name="txtPartyName" id="txtPartyName'+Row_ID_Bank+'"  size="15" onfocus="onFocus(this)"  onblur="onBlur(this);"  style="text-align: left" />';      	
   	Cell2.innerHTML = '<input type="text" name="txtBranchCode" id="txtBranchCode'+Row_ID_Bank+'" size="15" onblur="getBnkBrnchNameFrmBnkCode(this);" onKeyPress="numberFormat(this)"/>';
   	Cell3.innerHTML = '<select name="cmbBankCode" id="cmbBankCode'+Row_ID_Bank+'"  style="width: 100%" onchange="getBranchNameFromBankCode(this);"> <option value="-1">--SELECT--</option>'+LISTBANK+'</select>';   
   	Cell4.innerHTML = '<select name="cmbBankBranchName" id="cmbBankBranchName'+Row_ID_Bank+'" onchange="getIfscCodeFromBrachCode('+Row_ID_Bank+');" style="width: 100%"> <option value="-1">--SELECT--</option></select>';
   	Cell5.innerHTML = '<input type="text" name="txtAccountNo" id="txtAccountNo'+Row_ID_Bank+'" size="25" />';
	Cell6.innerHTML = '<input type="text" name="txtChkAmt" id="txtChkAmt'+Row_ID_Bank+'" size="15" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);calAdvanceAmtTotal(this,\'txtChkAmt\')" onkeypress="amountFormat(this);"  style="text-align: right"/>';
   	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblBankDtls\')" /> '+
	'<input type="hidden" name="txtAddress" id="txtAddress'+Row_ID_Bank+'" value=" " />' +
	'<input type="hidden" name="txtMicrCode" id="txtMicrCode'+Row_ID_Bank+'" value=" " />';   	
	document.getElementById("hidBankGridSize").value = Number(Row_ID_Bank)+1;  	
}
function getBranchNameFromBankCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(11);
	var bankCode=document.getElementById(elementId).value;
	
	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&bankCode="+bankCode,
		        onSuccess: function(myAjax) {
						getDataStateChangedForBranchName(myAjax,rowNum,bankCode);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForBranchName(myAjax,rowNum,bankCode)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var resultElementId="cmbBankBranchName"+rowNum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
   	if(XmlHiddenValues.length >0)
	{
	 		for( var i=0; i<XmlHiddenValues.length ;i++) {
		    var theOption = new Option;
			theOption.value = XmlHiddenValues[i].childNodes[0].nodeValue;
			theOption.text = XmlHiddenValues1[i].childNodes[0].nodeValue;
			document.getElementById(resultElementId).options[i] = theOption;
			//getIfscCodeFromBrachCode(rowNum,XmlHiddenValues[i].childNodes[0].nodeValue);
	 		}
	}
   	else
   	{
	 		alert("This bank does not belong to your location.");
	 		document.getElementById(resultElementId).options.length = 0;
	 		theOption = new Option;
			theOption.value = "-1";
			theOption.text ="--SELECT--";
			document.getElementById(resultElementId).options[0]=theOption;
	 		document.getElementById(resultElementId).options[0].selected="selected";
	 		document.getElementById(resultElementId).options[0].disabled=true;
   	}
}
function getBranchNameFromBankCode(object)
{
	getBranchNameFromBankCodeUsingAJAX(object);
//	var elementId=object.id;
//	var rowNum=elementId.substring(11);
//	var bankCode=document.getElementById(elementId).value;
//	
//	xmlHttp=GetXmlHttpObject();
//
//	if (xmlHttp==null)
//	{
//		alert ("Your browser does not support AJAX!");
//		return;
//	} 
//
//	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
//	
//	xmlHttp.onreadystatechange=function getDataStateChangedForBranchName(){
//		
//		if (xmlHttp.readyState==4)
//		{ 
//			if(xmlHttp.status==200)
//			{
//				
//				XMLDoc = xmlHttp.responseXML.documentElement;
//				var resultElementId="cmbBankBranchName"+rowNum;
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
//				var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
//			   	if(XmlHiddenValues.length >0)
//    			{
//				 		for( var i=0; i<XmlHiddenValues.length ;i++) {
//		    		    var theOption = new Option;
//						theOption.value = XmlHiddenValues[i].text;
//						theOption.text = XmlHiddenValues1[i].text;
//						document.getElementById(resultElementId).options[i] = theOption;
//						getIfscCodeFromBrachCode(rowNum,XmlHiddenValues[i].text);
//				}
//    		}
//			else
//			{
//				 		alert("This bank does not belong to your location.");
//				 		document.getElementById(resultElementId).options.length = 0;
//				 		theOption = new Option;
//						theOption.value = "-1";
//						theOption.text ="--SELECT--";
//						document.getElementById(resultElementId).options[0]=theOption;
//				 		document.getElementById(resultElementId).options[0].selected="selected";
//				 		document.getElementById(resultElementId).options[0].disabled=true;
//			}
//	      }
//		}
//	};
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(uri);

}

function getBnkBrnchNameFrmBnkCode(object){
	getBnkBrnchNameFrmBnkCodeUsingAJAX(object);
//	var elementId=object.id;
//	var rowNum=elementId.substring(13);
//	var branchCode=document.getElementById(elementId).value;
//	
//	var resultElementBankCode="cmbBankCode"+rowNum;
//	var resultElementBranchName="cmbBankBranchName"+rowNum;
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
//				getIfscCodeFromBrachCode(rowNum,branchCode);
//				}
//				else
//				{
//					alert("Please enter correct branch code.");
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
//							
//				}
//			}
//		}
//	};
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(uri);
//	}
//	else
//	{
//		
//		document.getElementById(resultElementBankCode).disabled=false;
//		document.getElementById(resultElementBranchName).disabled=false;
//	}
}
function getBnkBrnchNameFrmBnkCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(13);
	var branchCode=document.getElementById(elementId).value;
	
	var resultElementBankCode="cmbBankCode"+rowNum;
	var resultElementBranchName="cmbBankBranchName"+rowNum;
	
	uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
	if(document.getElementById(elementId).value != "")
	{
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
						getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,rowNum,branchCode,elementId);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}else
	{
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
	}
}
function getDataStateChangedForBankBranchName(myAjax,resultElementBankCode,resultElementBranchName,rowNum,branchCode,elementId)
{
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
	getIfscCodeFromBrachCode(rowNum);
	}
	else
	{
		alert("Please enter correct branch code.");
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
				
	}
}
function getIfscCodeFromBrachCode(rowNum)
{
//	if(document.getElementById("cmbBankBranch").value != '-1')
//	{
	
		//var elementId=object.id;
		//var rowNum=elementId.substring(17);
		//var branchCode=document.getElementById(elementId).value;
		var branchCode=document.getElementById("cmbBankBranchName"+rowNum).value;
		var resultElementId="txtMicrCode"+rowNum;
		var uri;
		uri = 'ifms.htm?actionFlag=getIfscCodeFromBranchCode&branchCode='+branchCode;
		//getIfscCodeUsingAjax(uri);
		getIFSCCodeUsingAJAX(uri,branchCode,resultElementId);
//		xmlHttp=GetXmlHttpObject();
//
//		   if (xmlHttp==null)
//		   {
//		      return;
//		   }  
//		   xmlHttp.onreadystatechange=function getIfscCodeOnStateChanged(){
//
//			   if (xmlHttp.readyState==complete_state)
//		   		{ 
//				  var XMLDoc=xmlHttp.responseXML.documentElement;
//				
//			      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//			      var XmlHiddenValues1=XMLDoc.getElementsByTagName('IFSCCODE');
//
//			      if(XmlHiddenValues1[0].text == '')
//			      {
//			    	  document.getElementById(resultElementId).value="";
//			      }else{
//			    	  document.getElementById(resultElementId).value=XmlHiddenValues[0].childNodes[1].text;
//			      }
//
//			   	}
//			   };
//		   xmlHttp.open("POST",uri,false);
//		   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		   xmlHttp.send(uri);
	}
//}
function getIFSCCodeUsingAJAX(uri,branchCode,resultElementId)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&branchCode="+branchCode,
		        onSuccess: function(myAjax) {
					getIfscCodeOnStateChangedUsingAJAX(myAjax,resultElementId);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getIfscCodeOnStateChangedUsingAJAX(myAjax,resultElementId)
{
	XMLDoc = myAjax.responseXML.documentElement;
    
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    
//    document.getElementById("txtIfscCode").value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
//    document.getElementById("txtBankBranchCode").value=document.getElementById("cmbBankBranch").value;

    var XmlHiddenValues1=XMLDoc.getElementsByTagName('IFSCCODE');

    if(XmlHiddenValues1[0].childNodes.length <=0)
    {
  	  document.getElementById(resultElementId).value="";
    }else{
  	  document.getElementById(resultElementId).value=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
    }
}
function getPensionerDtls()
{
	var ppoNo=document.getElementById("txtPPONo").value;
	var suplBillType ="";
	if(document.getElementById("radioSupBillTypePnsn").checked)
	{
		suplBillType=document.getElementById("radioSupBillTypePnsn").value;
	}
	else if(document.getElementById("radioSupBillTypeCvp").checked)
	{
		suplBillType=document.getElementById("radioSupBillTypeCvp").value;
	}
	else if(document.getElementById("radioSupBillTypeDcrg").checked)
	{
		suplBillType=document.getElementById("radioSupBillTypeDcrg").value;
	}
	if(isValidPPONo(ppoNo))
	{
		var uri="";
		uri="ifms.htm?actionFlag=getSuppBillData&PPONo="+ppoNo+"&CurrRole="+document.getElementById("hdCurrRole").value+"&SuplBillType="+suplBillType;
		window.location.href=uri;
	}
	//getPensionerDtlsUsingAJAX(uri);
}
function getPensionerDtlsUsingAJAX(uri)
{
	
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	
	xmlHttp.onreadystatechange=function getDataStateChangedForBranchName(){
		
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				
				self.location.reload();
				
	      }
		}
	};
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function isValidPPONo(ppoNo)
{
	if(ppoNo != "")
	{
	showProgressbar();
	var uri="ifms.htm?actionFlag=valPPONo&PPONo="+ppoNo;
	//isValidPPONoUsingAJAX(uri);
	isPPONoValidUsingAJAX(uri,ppoNo);
	var flag=document.getElementById("hidFlag").value;
	if(flag=="Y")
		{
			
			return true;
		}else
		{
			return false;
		}
	}
}
function isPPONoValidUsingAJAX(uri,ppoNo)
{
	var myAjax = new Ajax.Request(uri,
		        {
		        	method: 'post',
		        	asynchronous: false,
		        	parameters: "&PPONo="+ppoNo,
		        	onSuccess: function(myAjax) {
					getDataStateChangedPpoNo(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedPpoNo(myAjax)
{
	var flag=null;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
	
	flag=XmlHiddenValues[0].childNodes[0].nodeValue;

	if(flag=="N")
	{
		
		alert("PPO No is not valid. Please enter valid PPO No.");
		document.getElementById("txtPPONo").value="";
		document.getElementById("txtPPONo").focus();
		hideProgressbar();
		return true;
	}
	document.getElementById("hidFlag").value=flag;
	hideProgressbar();
}



function isValidPPONoUsingAJAX(url)
{
	xmlHttp=GetXmlHttpObject();
    
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange= function saveDataStateChanged() 
	{ 
		var chequeNo=null;
		var rowNum=null;
		var flag=null;
		var lIntFlag=0;
		var resultElementId=null;
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
				
				flag=XmlHiddenValues[0].text;
			
				if(flag=="N")
				{
					
					alert("PPO No is not valid. Please enter valid PPO No.");
					document.getElementById("txtPPONo").value="";
					document.getElementById("txtPPONo").focus();
					hideProgressbar();
					return true;
				}
				document.getElementById("hidFlag").value=flag;
				hideProgressbar();
			}
	
		}
		
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function calRecoveryAmtTotal(field,fieldName)
{
	try
	{
		if(field.value!='')
		{
			var rowCount=Number(document.getElementById("hidRecoveryGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && document.getElementById(fieldName+cnt).value!='')
				{
					total=total+Number(document.getElementById(fieldName+cnt).value);
					if(document.getElementById("radioSupBillTypeDcrg").checked)
					{
						document.getElementById("txtDcrgRecAmt").value=total;
						setValidAmountFormat(document.getElementById("txtDcrgRecAmt"));
					}
					if(document.getElementById("radioSupBillTypePnsn").checked)
					{
						document.getElementById("txtRecAmt").value=total;	
						setValidAmountFormat(document.getElementById("txtRecAmt"));
					}
					
					
				}
			}
			
		}
	}catch(ex)
	{
		
	}
	return false;
}
function calculateNetAmount()
{	
	if(document.getElementById("radioSupBillTypePnsn").checked)
	{
		var netAmnt=0;
	//	if(document.getElementById("txtOthArrearAmt").value!=null)
	//	{
	//		var otherArrear=Number(document.getElementById("txtOthArrearAmt").value);
	//		netAmnt=otherArrear;
	//	}
		var pensionAmt = document.getElementById("txtPensionAmt").value;
		var adpAmt = document.getElementById("txtADPAmt").value;
		var dpAmt = document.getElementById("txtDPAmt").value;
		var ir1Amt = document.getElementById("txtIR1Amt").value;
		var ir2Amt = document.getElementById("txtIR2Amt").value;
		var ir3Amt = document.getElementById("txtIR3Amt").value;
		var daAmt = document.getElementById("txtDAAmt").value;
		var cvpAmt = document.getElementById("txtCvpAmt").value;
		var peonAllowAmt = document.getElementById("txtPeonAllowAmt").value;
		var medicalAllowAmt =document.getElementById("txtMedicalAllowAmt").value;
		var gallantryAmt = document.getElementById("txtGallantryAmt").value;
		var otherBenefit = document.getElementById("txtOtherBenefit").value;
			
		var otherArrear=document.getElementById("txtOthArrearAmt").value;
		var otherArrear2=document.getElementById("txtOthArrearAmt2").value;
		var otherArrear3=document.getElementById("txtOthArrearAmt3").value;
		var totalAmt=0;
		if(pensionAmt != "" && pensionAmt != "0.00")
		{
			totalAmt=totalAmt+Number(pensionAmt);
		}
		if(adpAmt != "" && adpAmt != "0.00")
		{
			totalAmt=totalAmt+Number(adpAmt);
		}
		if(dpAmt != "" && dpAmt != "0.00")
		{
			totalAmt=totalAmt+Number(dpAmt);
		}
		if(ir1Amt != "" && ir1Amt != "0.00")
		{
			totalAmt=totalAmt+Number(ir1Amt);
		}
		if(ir2Amt != "" && ir2Amt != "0.00")
		{
			totalAmt=totalAmt+Number(ir2Amt);
		}
		if(ir3Amt != "" && ir3Amt != "0.00")
		{
			totalAmt=totalAmt+Number(ir3Amt);
		}
		if(daAmt != "" && daAmt != "0.00")
		{
			totalAmt=totalAmt+Number(daAmt);
		}
		if(cvpAmt != "" && cvpAmt != "0.00")
		{
			totalAmt=totalAmt-Number(cvpAmt);
		}
		if(peonAllowAmt != "" && peonAllowAmt != "0.00")
		{
			totalAmt=totalAmt+Number(peonAllowAmt);
		}
		if(medicalAllowAmt != "" && medicalAllowAmt != "0.00")
		{
			totalAmt=totalAmt+Number(medicalAllowAmt);
		}
		if(gallantryAmt != "" && gallantryAmt != "0.00")
		{
			totalAmt=totalAmt+Number(gallantryAmt);
		}
		if(otherBenefit != "" && otherBenefit != "0.00")
		{
			totalAmt=totalAmt+Number(otherBenefit);
		}
		if(otherArrear != "" && otherArrear != "0.00")
		{
			totalAmt=totalAmt+Number(otherArrear);
		}
		if(otherArrear2 != "" && otherArrear2 != "0.00")
		{
			totalAmt=totalAmt+Number(otherArrear2);
		}
		if(otherArrear3 != "" && otherArrear3 != "0.00")
		{
			totalAmt=totalAmt+Number(otherArrear3);
		}
		
		document.getElementById("txtGrossAmt").value=totalAmt;
		setValidAmountFormat(document.getElementById("txtGrossAmt"));
		
		if(document.getElementById("txtGrossAmt").value!=null)
		{
			
		// var grossAmnt=Number(document.getElementById("txtGrossAmt").value);
			var recoveryAmnt=Number(document.getElementById("txtRecAmt").value);
		//	totalAmt=totalAmt+grossAmnt;
			
		
			netAmnt=totalAmt-recoveryAmnt;
		
			document.getElementById("txtNetAmt").value=netAmnt;	
			//document.getElementById("txtGrossAmt").value=totalAmt;	
			setValidAmountFormat(document.getElementById("txtNetAmt"));
		}
	}
	else if(document.getElementById("radioSupBillTypeDcrg").checked)
	{
		document.getElementById("txtDcrgNetAmt").value=document.getElementById("txtDcrgSanctionedAmt").value;
		totalAmt=Number(document.getElementById("txtDcrgSanctionedAmt").value);
		recoveryAmnt=Number(document.getElementById("txtDcrgRecAmt").value);
		netAmnt=totalAmt-recoveryAmnt;
		document.getElementById("txtDcrgNetAmt").value=netAmnt;	
				
		setValidAmountFormat(document.getElementById("txtDcrgNetAmt"));
	}
}
function validateSaveData(){

	
	if(IsEmptyFun("txtPPONo", "Please Enter PPO No.")==false)
	{
		document.getElementById("txtPpoNo").focus();
		return false;
	}
	if(IsEmptyFun("txtGrossAmt", "Please Enter Gross Amount.")==false)
	{
		document.getElementById("txtGrossAmt").focus();
		return false;
	}
	var netAmnt = Number(document.getElementById("txtNetAmt").value);
	var totalAmnt=Number(document.getElementById("txtTotalAmt").value);

	if(netAmnt != totalAmnt)
	{
		alert("Net Amount should be equal to Total amount.");
		return false;
	}
	var rowCountForParty=document.getElementById("tblBankDtls").rows.length;
	if(rowCountForParty <= 0)
	{
		alert("There should be atleast one Party.");
	}
	if(validateRecoveryDtls()==false)
	{
		return false;
	}
	
	var grossAmt = Number(document.getElementById("txtGrossAmt").value);
	var arrearAmt = Number(document.getElementById("hdnArrearAmt").value);
	var arrearDtls = document.getElementById("arrearDtlContainer").innerHTML;

	if(arrearDtls != "")
	{
	    if(grossAmt != arrearAmt)
	    {
	    	if (!confirm("Arrear amount and gross amount are different.")) 
	    	{ 
	    		return false; 
	    	}
	    	//alert("Arrear amount and gross amount are different.");
	    }
	}
//	if(!validateBeforeSave())
//	{
//		return false;
//	}
	return true;
}
function saveDataOfBill()
{
	if(document.getElementById("radioSupBillTypePnsn").checked)
	{
		if(validateSaveData())
		{
			document.getElementById("txtExpenditure").value=Number(document.getElementById("txtNetAmt").value)+Number(document.getElementById("txtRecAmt").value);
			document.getElementById("txtRecovery").value=document.getElementById("txtRecAmt").value;
			
			saveData();
		}
	}
	else if(document.getElementById("radioSupBillTypeCvp").checked)
	{
		if(validateCommutationData())
		{
			document.getElementById("txtExpenditure").value=Number(document.getElementById("txtCvpSanctionedAmt").value);
			document.getElementById("txtRecovery").value="0.00";
			saveData();
		}
	}
	else if(document.getElementById("radioSupBillTypeDcrg").checked)
	{
		if(validateDcrgData())
		{
			document.getElementById("txtExpenditure").value=Number(document.getElementById("txtDcrgNetAmt").value)+Number(document.getElementById("txtDcrgRecAmt").value);
			document.getElementById("txtRecovery").value=document.getElementById("txtDcrgRecAmt").value;
			saveData();
		}
	}
}
function validateCommutationData()
{
	if(IsEmptyFun("txtPPONo", "Please Enter PPO No.")==false)
	{
		document.getElementById("txtPpoNo").focus();
		return false;
	}
	if(IsEmptyFun("txtCPONumber", "Please Enter Commutation Order No.")==false)
	{
		document.getElementById("txtCPONumber").focus();
		return false;
	}
	if(IsEmptyFun("txtCPODate", "Please Enter Commutation Order Date.")==false)
	{
		document.getElementById("txtCPODate").focus();
		return false;
	}
	if(IsEmptyFun("txtCvpSanctionedAmt", "Please Enter Amount Sanctioned.")==false)
	{
		document.getElementById("txtCvpSanctionedAmt").focus();
		return false;
	}
	if(IsEmptyFun("txtCommutationAmt", "Please Enter Commutation Amount.")==false)
	{
		document.getElementById("txtCommutationAmt").focus();
		return false;
	}
	var sanctAmt = Number(document.getElementById("txtCvpSanctionedAmt").value);
	var totalAmnt=Number(document.getElementById("txtTotalAmt").value);

	if(sanctAmt != totalAmnt)
	{
		alert("Amount sanctioned should be equal to Total amount.");
		return false;
	}
	return true;
}
function validateDcrgData()
{
	if(IsEmptyFun("txtPPONo", "Please Enter PPO No.")==false)
	{
		document.getElementById("txtPpoNo").focus();
		return false;
	}
	if(IsEmptyFun("txtGPONumber", "Please Enter GPO Number.")==false)
	{
		document.getElementById("txtGPONumber").focus();
		return false;
	}
	if(IsEmptyFun("txtGPODate", "Please Enter GPO Date.")==false)
	{
		document.getElementById("txtGPODate").focus();
		return false;
	}
	if(IsEmptyFun("txtDcrgSanctionedAmt", "Please Enter Amount Sanctioned.")==false)
	{
		document.getElementById("txtDcrgSanctionedAmt").focus();
		return false;
	}
	if(validateRecoveryDtls()==false)
	{
		return false;
	}
	var netAmt = Number(document.getElementById("txtDcrgNetAmt").value);
	var totalAmnt=Number(document.getElementById("txtTotalAmt").value);

	if(netAmt != totalAmnt)
	{
		alert("Net Amount should be equal to Total amount.");
		return false;
	}
	return true;
}
function validateBeforeSave()
{
	
	var paymentMode=document.getElementById("paymentMode").value;	
	var fieldName="txtBranchCode";
	var branchCodeString="";
	var flag=0;
	if(paymentMode == "ECS")
	{
		
		var rowCount=Number(document.getElementById("hidBankGridSize").value);
		
		for(var i=0;i<rowCount;i++)
		{
			var branchCode=document.getElementById(fieldName+i).value;
			

			if(flag==0)
			{
				branchCodeString=branchCodeString+branchCode;
				flag=1;
			}
			else
			{
				branchCodeString=branchCodeString+"~"+branchCode;
			}
			
		}
		uri="ifms.htm?actionFlag=isIFSCCodeExsist&branchCodeString="+branchCodeString;
		validateUsingAJAX(uri);
		
	}
	if(tempFlag==0)
	{
		return true;	
	}else
	{
	
		return false;
	}
}

function validateUsingAJAX(url)
{
	xmlHttp=GetXmlHttpObject();
    
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
	uri=url;
	xmlHttp.onreadystatechange= function saveDataStateChanged() 
	{ 
		
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
									
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
					var resultFlag= XmlHiddenValues[i].text;					
						
			}
				
				if(resultFlag=="Y")
				{
					
					tempFlag = 0;
				}
				else
				{
					
					var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchCode');
					
					var branchCode=XmlHiddenValues1[0].text;
					alert("IFSC Code for Branch Code"+branchCode+" does not exsist.");
					tempFlag = 1;
				}	
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}

function validateRecoveryDtls()
{
	
	var recoveryDtlsLength=Number(document.getElementById("hidRecoveryGridSize").value);
	var rowCountForRecovery=document.getElementById("tblRecoveryDtls").rows.length;
	 if(rowCountForRecovery>0)
	 {
		for(var rowfmlyCnt=0;rowfmlyCnt<Number(recoveryDtlsLength);rowfmlyCnt++)
		{		
			try
			{		
				if(document.getElementById("radioSupBillTypePnsn").checked)
				{
					if(IsEmptyFun("cmbRecoveryType"+rowfmlyCnt,"Please select Advance Type.")==false)
					{
						return false;
					}
				}
				if(document.getElementById("radioSupBillTypeDcrg").checked)
				{
					if(IsEmptyFun("txtNature"+rowfmlyCnt,"Please Enter Advance Type.")==false)
					{
						return false;
					}
				}
				if(IsEmptyFun("txtAmount"+rowfmlyCnt,"Please Enter Recovery Amount.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtSchemeCode"+rowfmlyCnt,"Please Enter Scheme Code.")==false)
				{
					return false;
				}
			}
			catch(ex)
			{
				
			}
		}
	 }
	 var rowCountForParty=document.getElementById("tblBankDtls").rows.length;
	 var partyDtlsLength=Number(document.getElementById("hidBankGridSize").value);
	 if(rowCountForParty>0)
	 {
		for(var rowPartyCount=0;rowPartyCount<Number(partyDtlsLength);rowPartyCount++)
		{		
			try
			{				 
				if(IsEmptyFun("txtPartyName"+rowPartyCount,"Please Enter Party Name.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtBranchCode"+rowPartyCount,"Please Enter Branch Code.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbBankCode"+rowPartyCount,"Please Enter Bank Name.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbBankBranchName"+rowPartyCount,"Please Enter Bracnd Name .")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAccountNo"+rowPartyCount,"Please Enter Account Number.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtChkAmt"+rowPartyCount,"Please Enter Cheque Amount.")==false)
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
function IsEmptyFun(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1" || element == "0.00")
	{
		alert(alrtStr);
	//	goToFieldTab(varStr,tabNo);
		document.getElementById(varStr).focus();
		return false;
	}
	return true;
}


function goToFieldTab(field,cnt)
{
	goToTab(cnt);

	if(document.getElementById(field) != null && ! document.getElementById(field).disabled)
	{
		document.getElementById(field).focus();
	}
}
function openWindowForArrear()
{
	
	var ppoNo=document.getElementById("txtPPONo").value;
	if(ppoNo!="")
	{
			var url='ifms.htm?actionFlag=openRevisedArrear&BillFlag=S';
		
			var newWindow = null;
		   	var height = screen.height - 150;
		   	var width = screen.width;
		   	var urlstring = url;
		   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		   	newWindow = window.open(urlstring, "Arrear", urlstyle);
	
	}else
	{
		alert("Please enter PPO No.");
		document.getElementById("txtPPONo").focus();
	}
}
function calRecoveryAmtTotalForDelete(fieldName)
{
	try
	{
	//	if(field.value!='')
	//	{
			var rowCount=Number(document.getElementById

("hidRecoveryGridSize").value);
			var total=0;
			for(var cnt=0;cnt<(rowCount+1);cnt++)
			{
				if(document.getElementById(fieldName+cnt)!=null && 

document.getElementById(fieldName+cnt).value!='')
				{
					total=total+Number(document.getElementById

(fieldName+cnt).value);
					
				}
				if(document.getElementById("radioSupBillTypeDcrg").checked)
				{
					document.getElementById("txtDcrgRecAmt").value=total;
					setValidAmountFormat(document.getElementById("txtDcrgRecAmt"));
				}
				if(document.getElementById("radioSupBillTypePnsn").checked)
				{
					document.getElementById("txtRecAmt").value=total;	
					setValidAmountFormat(document.getElementById("txtRecAmt"));
				}
//				document.getElementById("txtRecAmt").value=total;
//				setValidAmountFormat(document.getElementById("txtRecAmt"));
			}
			
	//}
	}catch(ex)
	{
		
	}
	return false;
}

function onChangeSupBillType()
{
	if(document.getElementById("radioSupBillTypePnsn").checked)
	{
		document.getElementById("divPensionDtls").style.display="block";
		document.getElementById("divCvpDtls").style.display="none";
		document.getElementById("divDcrgDtls").style.display="none";
		document.getElementById("fsRecoveryDtls").style.display="block";
		document.getElementById("txtNetAmt").value=document.getElementById("txtGrossAmt").value;
		document.getElementById("txtRecAmt").value="0.00";
	}
	else if(document.getElementById("radioSupBillTypeCvp").checked)
	{
		document.getElementById("divPensionDtls").style.display="none";
		document.getElementById("divCvpDtls").style.display="block";
		document.getElementById("divDcrgDtls").style.display="none";
		document.getElementById("fsRecoveryDtls").style.display="none";
	
	}
	else if(document.getElementById("radioSupBillTypeDcrg").checked)
	{
		document.getElementById("divPensionDtls").style.display="none";
		document.getElementById("divCvpDtls").style.display="none";
		document.getElementById("divDcrgDtls").style.display="block";
		document.getElementById("fsRecoveryDtls").style.display="block";
		document.getElementById("txtDcrgNetAmt").value=document.getElementById("txtDcrgSanctionedAmt").value;
		document.getElementById("txtDcrgRecAmt").value="0.00";
	}
	for(var i = document.getElementById("tblRecoveryDtls").rows.length; i > 1;i--)
    {
		  document.getElementById("tblRecoveryDtls").deleteRow(i -1);
    } 
}