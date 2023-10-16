/*********Open a Window ********/

/*************To check Physical PPO received before Generating Registration Number ******************/




function genRegNoValidation()
{
	
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");
	var regNo="";
	var regNoId;
	var rowNum;
	var ppoRcvdDateId;
	var ppoRcvdDate="";
	var flag =0;
	
	
	if(arrChkBox.checked == true)
	{
		
		ppoNo = arrChkBox.value;
		rowNum=ppoNo.substring(ppoNo.indexOf("_")+1);
		regNoId="txtRegNo"+rowNum;
		ppoRcvdDateId="txtPhyPpoRcvdDt_"+rowNum;
		ppoRcvdDate=document.getElementById(ppoRcvdDateId).value;
		regNo=document.getElementById(regNoId).value;
		
		
		if(regNo != null || regNo.length>0)
		{
			alert("Registration number is already generted for PPO No "+ppoNo+".");
			return false;
		}
		/*else if(ppoRcvdDate == null || ppoRcvdDate.length == 0){
			alert("Please enter the PPO Receipt Date for PPO No "+ppoNo.substring(0,ppoNo.indexOf("_"))+".");
			return false;
		}*/
		else
		{
			flag=1;
		}
	}
	else
	{
		if (arrChkBox!=null)
		{
			
			if(arrChkBox.length > 0)
			{
				for(var i=0;i<arrChkBox.length;i++)
				{
					if(arrChkBox[i].checked==true)
					{
						ppoNo = arrChkBox[i].value;
						flag=1;
						rowNum=ppoNo.substring(ppoNo.indexOf("_")+1);
						ppoRcvdDateId="txtPhyPpoRcvdDt_"+rowNum;
						ppoRcvdDate=document.getElementById(ppoRcvdDateId).value;
						regNoId="txtRegNo"+rowNum;
						regNo=document.getElementById(regNoId).value;
						
						if(regNo.length>0)
						{
							
							alert("Registration number is already generted for PPO No "+(ppoNo.split("_"))[0]+".");
							return false;
						}
						/*else if(ppoRcvdDate.length == 0)
						{
							
							alert("Please enter the PPO Receipt Date for PPO No "+ppoNo.substring(0,ppoNo.indexOf("_"))+".");
							return false;
						}*/
													
					}
					
				}
			}
		}
	}
	if(flag==1)
	{
		return true;		
	}
	else
	{
		alert("Please select atleast one PPO No to generate the Registration No.");
		return false;
	}
	
	return true;
}

function genRegNo()
{
    if(genRegNoValidation())
	{
    	pensionerDtlId="";
    	pensionerId="";
		getPensionerDtlId();
	    var uri="ifms.htm?actionFlag=genRegNo&pensionerDtlId="+pensionerDtlId+"&pensionerId="+pensionerId;
		genRegNoUsingAJAX(uri);
	}
}
function genRegNoUsingAJAX(uri)
{
	generateRegNoUsingAJAX(uri);
//	xmlHttp=GetXmlHttpObject();
//
//	if (xmlHttp==null)
//	{
//		alert ("Your browser does not support AJAX!");
//		return;
//	} 
//	var url = runForm(0); 
//	xmlHttp.onreadystatechange= function saveDataStateChanged() 
//	{ 
//		if (xmlHttp.readyState==4)
//		{ 
//			if(xmlHttp.status==200)
//			{
//				
//				XMLDoc = xmlHttp.responseXML.documentElement;
//				var XmlHiddenValues = XMLDoc.getElementsByTagName('RegNO');
//				var XmlHiddenValues1 = XMLDoc.getElementsByTagName('RowNo');
//				var XmlHiddenValues2 = XMLDoc.getElementsByTagName('RegDate');		
//				
//				for( var i=0; i<XmlHiddenValues.length ;i++) {
//		    		 
//	    		    var rowNum = XmlHiddenValues1[i].text;
//	    		  	var regNo= XmlHiddenValues[i].text;
//					var resultElementId="txtRegNo"+rowNum;
//					document.getElementById(resultElementId).value=regNo;
//					document.getElementById("txtPhyPpoRcvdDt_"+rowNum).value =XmlHiddenValues2[0].text;
//					document.getElementById(resultElementId).disabled=true;
//						
//			}
//				alert("Registration Numbers generated sucessfully");
//		}
//		}
//	};
//	//showProgressbar('Please Wait<br>Your request is in progress...');
//	xmlHttp.open("POST",uri,false);
//	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	xmlHttp.send(url);
	
}
function generateRegNoUsingAJAX(uri)
{

	var url = runForm(0); 
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForGenRegNo(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForGenRegNo(myAjax)
	{
	
		
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('RegNO');
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('RowNo');
		var XmlHiddenValues2 = XMLDoc.getElementsByTagName('RegDate');		
		
		for( var i=0; i<XmlHiddenValues.length ;i++) {
			 
		    var rowNum = XmlHiddenValues1[i].childNodes[0].nodeValue;
		  	var regNo= XmlHiddenValues[i].childNodes[0].nodeValue;
			var resultElementId="txtRegNo"+rowNum;
			document.getElementById(resultElementId).value=regNo;
			document.getElementById("txtPhyPpoRcvdDt_"+rowNum).value =XmlHiddenValues2[0].childNodes[0].nodeValue;
			document.getElementById(resultElementId).disabled=true;
				
	}
		alert("Registration Numbers generated sucessfully");
	
}
var pensionerDtlId="";
var pensionerId="";
var pnsnRqstId="";
var gRowNumList="";
function getPensionerDtlId()
{
	var rowNum;
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");

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
						if(document.getElementById("hdnPnsnrDtlsId"+rowNum) != null)
						{
							pensionerDtlId=document.getElementById("hdnPnsnrDtlsId"+rowNum).value;
						}
						if(document.getElementById("hdnpnsnrqstid"+rowNum) != null)
						{
							pnsnRqstId=document.getElementById("hdnpnsnrqstid"+rowNum).value;
						}
						pensionerId=document.getElementById("hdnPensionerId"+rowNum).value;
						gRowNumList = rowNum;
					}
					else
					{
						if(document.getElementById("hdnPnsnrDtlsId"+rowNum) != null)
						{
							pensionerDtlId=pensionerDtlId+"~"+document.getElementById("hdnPnsnrDtlsId"+rowNum).value;
						}
						if(document.getElementById("hdnpnsnrqstid"+rowNum) != null)
						{
							pnsnRqstId=pnsnRqstId+"~"+document.getElementById("hdnpnsnrqstid"+rowNum).value;
						}
					    pensionerId=pensionerId+"~"+document.getElementById("hdnPensionerId"+rowNum).value;
					    gRowNumList = gRowNumList+"~"+rowNum;
					}
					
				}
			}
		}
	}
					
}
	

function getBranchNameFromBankCode(object)
{
	
	var elementId=object.id;
	var rowNum=elementId.substring(11);
	var bankCode=document.getElementById(elementId).value;
	var branchCodeId="txtBankBranchCode"+rowNum;
	document.getElementById(branchCodeId).value="";
	document.getElementById("txtAuditorName"+rowNum).value="";
	document.getElementById("hdnAuditorPostId"+rowNum).value="";
	
	//xmlHttp=GetXmlHttpObject();
	// xmlHttp=new XMLHttpRequest();

	/*if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
*/
	uri="ifms.htm?actionFlag=getBrnchNms&bankCode="+bankCode;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:"bankCode="+bankCode,
		        onSuccess: function(myAjax) {
					getDataStateChangedForBranchName(myAjax,rowNum);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
	//xmlHttp.onreadystatechange=getDataStateChangedForBranchName(rowNum);

}

function getDataStateChangedForBranchName(myAjax,rowNum){
			XMLDoc = myAjax.responseXML.documentElement;
			var resultElementId="cmbBankBrnchName"+rowNum;
			document.getElementById(resultElementId).innerHTML = "";
			
			 var XmlHiddenValues = XMLDoc.getElementsByTagName('BranchCode');
			 var XmlHiddenValues1 = XMLDoc.getElementsByTagName('BranchName');
			 
			 	if(XmlHiddenValues.length >0)
			 	{
			 		var theOption = new Option;
		   			theOption.value = "-1";
		   			theOption.text ="--Select--";
		   			document.getElementById(resultElementId).options[0]=theOption;
			 		document.getElementById(resultElementId).options[0].selected="selected";
			 		for( var i=0; i<XmlHiddenValues.length ;i++) {
			 			theOption = new Option;
			 			theOption.value = XmlHiddenValues[i].childNodes[0].nodeValue;
			 			theOption.text = XmlHiddenValues1[i].childNodes[0].nodeValue;
			 			document.getElementById(resultElementId).options[i+1] = theOption;
				 	}
			 	}
			 	else
			 	{
			 		alert("This bank does not belong to your location.");
			 		document.getElementById(resultElementId).innerHTML = "";
			 		var theDefaultOption = new Option;
			 		theDefaultOption.value = "-1";
			 		theDefaultOption.text = "---Select---";
	 				document.getElementById(resultElementId).options[0] = theDefaultOption;
			 		document.getElementById(resultElementId).options[0].disabled=true;
			 	}
}

function getAuditorNameFromBranchCode(object)
{
	getAuditorNameFromBranchCodeUsingAJAX(object);
//	var elementId=object.id;
//	var rowNum=elementId.substring(16);
//	var branchCode=document.getElementById(elementId).value;
//	if(branchCode != -1)
//	{
//		document.getElementById("txtBankBranchCode"+rowNum).value = branchCode;
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
//		xmlHttp.open("POST",uri,false);
//		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		xmlHttp.send(uri);
//		xmlHttp.onreadystatechange=getDataStateChangedForAudiName(rowNum);
//		
//	}
//	else{
//		document.getElementById("txtBankBranchCode"+rowNum).value = "";
//		document.getElementById("txtAuditorName"+rowNum).value="";
//		document.getElementById("hdnAuditorPostId"+rowNum).value="";
//	}
}
//function getDataStateChangedForAudiName(rowNum)
//{
//	if (xmlHttp.readyState==4)
//	{ 
//		if(xmlHttp.status==200)
//		{
//			XMLDoc = xmlHttp.responseXML.documentElement;
//			var XmlHiddenValues = XMLDoc.getElementsByTagName('AuditorName');
//			var XmlHiddenValues1 = XMLDoc.getElementsByTagName('AuditorPostID');
//			if(XmlHiddenValues[0].childNodes.length > 0)
//			{
//				var auditorName=XmlHiddenValues[0].childNodes[0].nodeValue;
//				var auditorPostId = XmlHiddenValues1[0].childNodes[0].nodeValue;
//				var resultElement="txtAuditorName"+rowNum;
//				var resultElementAudiPostId="hdnAuditorPostId"+rowNum; 
//				
//				document.getElementById(resultElement).value=auditorName;
//				document.getElementById(resultElementAudiPostId).value=auditorPostId;
//			}
//			else{
//				alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
//				document.getElementById("txtBankBranchCode"+rowNum).value = "";
//				document.getElementById("txtAuditorName"+rowNum).value="";
//				document.getElementById("hdnAuditorPostId"+rowNum).value="";
//				document.getElementById("cmbBankBrnchName"+rowNum).options[0].value="-1";
//				document.getElementById("cmbBankBrnchName"+rowNum).options[0].selected="selected";
//			}
//		}
//	}
//}
function getAuditorNameFromBranchCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(16);
	var branchCode=document.getElementById(elementId).value;
	if(branchCode != -1)
	{
		document.getElementById("txtBankBranchCode"+rowNum).value = branchCode;
		uri="ifms.htm?actionFlag=getAudiNms&branchCode="+branchCode;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
						getDataStateChangedForAudiName(myAjax,branchCode,elementId,rowNum);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}else{
		document.getElementById("txtBankBranchCode"+rowNum).value = "";
		document.getElementById("txtAuditorName"+rowNum).value="";
		document.getElementById("hdnAuditorPostId"+rowNum).value="";
	}
}
function getDataStateChangedForAudiName(myAjax,branchCode,elementId,rowNum)
{

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('AuditorName');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('AuditorPostID');
	if(XmlHiddenValues[0].childNodes.length > 0)
	{
		var auditorName=XmlHiddenValues[0].childNodes[0].nodeValue;
		var auditorPostId = XmlHiddenValues1[0].childNodes[0].nodeValue;
		var resultElement="txtAuditorName"+rowNum;
		var resultElementAudiPostId="hdnAuditorPostId"+rowNum; 
		
		document.getElementById(resultElement).value=auditorName;
		document.getElementById(resultElementAudiPostId).value=auditorPostId;
	}
	else{
		alert("No auditor is mapped with selected bank branch.Please select other bank branch.");
		document.getElementById("txtBankBranchCode"+rowNum).value = "";
		document.getElementById("txtAuditorName"+rowNum).value="";
		document.getElementById("hdnAuditorPostId"+rowNum).value="";
		document.getElementById("cmbBankBrnchName"+rowNum).options[0].value="-1";
		document.getElementById("cmbBankBrnchName"+rowNum).options[0].selected="selected";
	}

}
function getBnkBrnchNameFrmBnkCode(object){
	getBnkBrnchNameFrmBnkCodeUsingAJAX(object);
//	var elementId=object.id;
//	var rowNum=elementId.substring(17);
//	var branchCode=document.getElementById(elementId).value;
//	var resultElementBankCode="cmbBankName"+rowNum;
//	var resultElementBranchName="cmbBankBrnchName"+rowNum;
//	var resultElementAudiName="txtAuditorName"+rowNum;
//	var resultElementAudiPostId="hdnAuditorPostId"+rowNum;
//	
//	if(document.getElementById(elementId).value != ""){
//	
//		xmlHttp=GetXmlHttpObject();
//	
//		if (xmlHttp==null)
//		{
//			alert ("Your browser does not support AJAX!");
//			return;
//		} 
//	
//		uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
//	
//		xmlHttp.onreadystatechange=function getDataStateChangedForBnkBrnchAudiName()
//		{
//			if (xmlHttp.readyState==4)
//			{ 
//				if(xmlHttp.status==200)
//				{
//					XMLDoc = xmlHttp.responseXML.documentElement;
//					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//					if(XmlHiddenValues[0].childNodes.length != 0)
//					{
//					var branchName=XmlHiddenValues[0].childNodes[0].text;
//					
//					var bankCode=XmlHiddenValues[0].childNodes[1].text;
//					var bankName=XmlHiddenValues[0].childNodes[2].text;
//					var auditorName=XmlHiddenValues[0].childNodes[3].text;
//					var auditorPostId = XmlHiddenValues[0].childNodes[4].text;
//				
//					document.getElementById(resultElementBankCode).options[0].value=bankCode;
//					document.getElementById(resultElementBankCode).options[0].text=bankName;
//					document.getElementById(resultElementBankCode).options[0].selected="selected";
//					document.getElementById(resultElementBankCode).disabled=true;
//					
//					document.getElementById(resultElementBranchName).options[0].value=branchCode;
//					document.getElementById(resultElementBranchName).options[0].text=branchName;
//					document.getElementById(resultElementBranchName).options[0].selected="selected";
//					document.getElementById(resultElementBranchName).disabled=true;
//					document.getElementById(resultElementAudiName).value=auditorName;
//					document.getElementById(resultElementAudiName).disabled=true;
//					document.getElementById(resultElementAudiPostId).value=auditorPostId;
//					}
//					else
//					{
//						alert("Please enter correct branch code.");
//						document.getElementById(elementId).value="";
//						document.getElementById(resultElementBankCode).options[0].value="-1";
//						document.getElementById(resultElementBankCode).options[0].selected="selected";
//						document.getElementById(resultElementBranchName).options[0].value="-1";
//						document.getElementById(resultElementBranchName).options[0].selected="selected";
//						document.getElementById(resultElementAudiName).value=" ";
//						document.getElementById(resultElementAudiPostId).value="";
//						document.getElementById(resultElementBankCode).disabled=false;
//						document.getElementById(resultElementBranchName).disabled=false;
//						document.getElementById(resultElementAudiName).disabled=false;
//					}
//				}
//			}
//		};
//		xmlHttp.open("POST",uri,false);
//		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		xmlHttp.send(uri);
//	}
//	else
//	{
//		document.getElementById(resultElementBankCode).disabled=false;
//		document.getElementById(resultElementBranchName).disabled=false;
//	}
}
function getBnkBrnchNameFrmBnkCodeUsingAJAX(object)
{
	var elementId=object.id;
	var rowNum=elementId.substring(17);
	var branchCode=document.getElementById(elementId).value;
	var resultElementBankCode="cmbBankName"+rowNum;
	var resultElementBranchName="cmbBankBrnchName"+rowNum;
	var resultElementAudiName="txtAuditorName"+rowNum;
	var resultElementAudiPostId="hdnAuditorPostId"+rowNum;
	
	if(document.getElementById(elementId).value != ""){
		
		uri="ifms.htm?actionFlag=getBnkBrnchAudi&branchCode="+branchCode;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&branchCode="+branchCode,
			        onSuccess: function(myAjax) {
							getDataStateChangedForBnkBrnchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId,resultElementAudiName,resultElementAudiPostId);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}else
	{
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
	}
	
}
function getDataStateChangedForBnkBrnchName(myAjax,resultElementBankCode,resultElementBranchName,branchCode,elementId,resultElementAudiName,resultElementAudiPostId)
{

	XMLDoc =  myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	if(XmlHiddenValues[0].childNodes.length != 0)
	{
	var branchName=XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	var bankCode=XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var bankName=XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var auditorName=XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
	var auditorPostId = XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue;

	document.getElementById(resultElementBankCode).options[0].value=bankCode;
	document.getElementById(resultElementBankCode).options[0].text=bankName;
	document.getElementById(resultElementBankCode).options[0].selected="selected";
	document.getElementById(resultElementBankCode).disabled=true;
	
	document.getElementById(resultElementBranchName).options[0].value=branchCode;
	document.getElementById(resultElementBranchName).options[0].text=branchName;
	document.getElementById(resultElementBranchName).options[0].selected="selected";
	document.getElementById(resultElementBranchName).disabled=true;
	document.getElementById(resultElementAudiName).value=auditorName;
	document.getElementById(resultElementAudiName).disabled=true;
	document.getElementById(resultElementAudiPostId).value=auditorPostId;
	}
	else
	{
		alert("Please enter correct branch code.");
		document.getElementById(elementId).value="";
		document.getElementById(resultElementBankCode).options[0].value="-1";
		document.getElementById(resultElementBankCode).options[0].selected="selected";
		document.getElementById(resultElementBranchName).options[0].value="-1";
		document.getElementById(resultElementBranchName).options[0].selected="selected";
		document.getElementById(resultElementAudiName).value=" ";
		document.getElementById(resultElementAudiPostId).value="";
		document.getElementById(resultElementBankCode).disabled=false;
		document.getElementById(resultElementBranchName).disabled=false;
		document.getElementById(resultElementAudiName).disabled=false;
	}

}
function forwardPensionCase()
{
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");

	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					flag=1;
				}
			}
		}
	}
	if(flag==1)
	{
		if(IsEmptyRegNo())
		{
			
			var uri="ifms.htm?actionFlag=forwardPensionPaymentCase";
			forwardCaseUsingAjax(uri);
		}
	}
	else
	{
		alert("Please select atleast one record to forward.");
	}

}

function savePnsnrBankDtls()
{
	
	if(IsEmptyRegNo())
	{
		pensionerDtlId="";
		getPensionerDtlId();
	    var uri="ifms.htm?actionFlag=savePnsnrBankBranchDtls&pensionerDtlId="+pensionerDtlId;
		
		saveBankDetailsUsingAjx(uri);
	}
	
	
}
function saveBankDetailsUsingAjx(uri)
{
	 var url = runForm(0); 
//	 url = uri + url;  
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:"&pensionerDtlId="+pensionerDtlId + url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForSave(myAjax);
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
//   var url = runForm(0); 
//   url = uri + url;  
//   xmlHttp.onreadystatechange=caseStateChanged;
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(url);
   
}
function getDataStateChangedForSave(myAjax)
{
	var XMLDoc=xmlHttp.responseXML.documentElement;
	  if(XMLDoc!=null)
	  {
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    alert(XmlHiddenValues[0].childNodes[0].nodeValue);
	  }
	  else
	  {
		  alert("Forwarded Successfully.");
		  self.location.reload;
	  }
}
function forwardCaseUsingAjax(uri)
{
	pnsnRqstId="";
	pensionerDtlId="";
	getPensionerDtlId();
	var url = "pnsnRqstId="+pnsnRqstId+"&showCaseFor="+1+"&pensionerDtlId="+pensionerDtlId;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForForwadCase(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
//	pnsnRqstId="";
//	pensionerDtlId="";
//	getPensionerDtlId();
//	xmlHttp=GetXmlHttpObject();
//
//	   if (xmlHttp==null)
//	   {
//	      return;
//	   }  
//	   var url = "pnsnRqstId="+pnsnRqstId+"&showCaseFor="+1+"&pensionerDtlId="+pensionerDtlId;
//	   xmlHttp.onreadystatechange=function(){
//		   if (xmlHttp.readyState==complete_state)
//		   { 
//			  var XMLDoc=xmlHttp.responseXML.documentElement;
//			  var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
//			  if(XmlHiddenValues != null && XmlHiddenValues.length > 0)
//			  {
//				  alert(XmlHiddenValues[0].text);
//				  window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor=1";
//			  }
//			  else
//			  {
//				  alert("Some Problem Occurred During Forward.Please Try Again");		
//			  }
//		   }
//	   };
//	   xmlHttp.open("POST",uri,false);
//	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//	   xmlHttp.send(url);
	
}
function getDataStateChangedForForwadCase(myAjax)
{
		var XMLDoc =  myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
		if(XmlHiddenValues[0].childNodes.length > 0)
		{
		  alert(XmlHiddenValues[0].childNodes[0].nodeValue);
		  //window.location.href = "ifms.htm?actionFlag=getOLPenCases&showCaseFor=1";
		  window.location.reload();
		}
		else
		{
		  alert("Some Problem Occurred During Forward.Please Try Again");		
		}
}
function caseStateChanged() 
{ 
	
   if (xmlHttp.readyState==complete_state)
   { 
	  var XMLDoc=xmlHttp.responseXML.documentElement;
	  if(XMLDoc!=null)
	  {
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
      alert(XmlHiddenValues[0].text);
	  }
	  else
	  {
		  alert("Forwarded Successfully.");
		  self.location.reload;
	  }
                
   }
}

function IsEmptyRegNo()
{
	var rowNum;
	var flag=0;
	var arrChkBox=document.getElementsByName("chkbxPesnionerNo");

	if(arrChkBox!=null)
	{
		if(arrChkBox.length > 0)
		{
			
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked == true)
				{
					flag=1;
					var rowId=arrChkBox[i].id;
					rowNum=rowId.substring(rowId.indexOf("_")+1);
					var regNo=document.getElementById("txtRegNo"+rowNum).value;
					
					if(regNo.length<=0 || regNo=="")
					{
						alert("First generate Registration Number.")
						return false;
					}
				}
			}
		}
	}
	if(flag==0)
	{
		alert("Please select atleast one PPO No to Save Bank Details.");
		return false;
	}
	return true;
}


