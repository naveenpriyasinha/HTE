var tempFlag;
function getAllCheckedECSCode()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsCodeString="";
	
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					ecsCode=ecsCode.substring(0,ecsCode.indexOf("_"));
					
					if(flag==0)
					{
						ecsCodeString=ecsCodeString+ecsCode;
						flag=1;
					}
					else
					{
						ecsCodeString=ecsCodeString+"~"+ecsCode;
					}
					
				}
			}
		}
	}
	return ecsCodeString;
}
function frwdECSForAuth()
{
	var ecsCodeString="";
	if(validateForwardECS())
	{
		ecsCodeString=getAllCheckedECSCode();
			
		var uri="ifms.htm?actionFlag=frwdECS&ecsCodeString="+ecsCodeString;
	//	frwdUsingAjax(uri);
		forwardECSUsingAJAX(uri,ecsCodeString);
		showProgressbar();
	}
	
}
function forwardECSUsingAJAX(uri,ecsCodeString)
{
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "ecsCodeString="+ecsCodeString,
		        onSuccess: function(myAjax) {
					getDataStateChangedForForwardECS(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
	function getDataStateChangedForForwardECS(myAjax)
	{
		
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
	
	
		for( var i=0; i<XmlHiddenValues.length ;i++) {
	
			ecsCodeString= XmlHiddenValues[i].childNodes[0].nodeValue;				
	
		}
	
		alert("ECS/N.EFT File Forwarded Succesfully to Payment ATO with ECS/N.EFT Code "+ecsCodeString+".");
	
		window.self.location.reload();
	}
function frwdUsingAjax(url)
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
		var ecsCodeString=null;
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
									
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
					ecsCodeString= XmlHiddenValues[i].text;					
						
			}
				
					alert("ECS/N.EFT File Forwarded Succesfully to Payment ATO with ECS/N.EFT Code "+ecsCodeString+".");
				
					window.self.location.reload();
					
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function validateForwardECS()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var flag=0;
	
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				flag=1;
			}
		}
	}

	if(flag==0)
	{
		alert("Please check atleast one file. ");
		return false;
	}
	else{
		return true;
	}
		
}
function validateAuthorizeECS()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var ecsCode="";
	var regNo="";
	var regNoId;
	var rowNum;
	var authDateId;
	var authDate="";
	var flag =0;
	var chequeNoId="";
	var chequeNo="";
			
		if (arrChkBox!=null)
		{
			
			if(arrChkBox.length > 0)
			{
				for(var i=0;i<arrChkBox.length;i++)
				{
					if(arrChkBox[i].checked==true)
					{
						ecsCode = arrChkBox[i].value;
						flag=1;
						rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
						authDateId="txtAuthDate_"+rowNum;
						chequeNoId="txtChequeNo_"+rowNum;
						authDate=document.getElementById(authDateId).value;
						chequeNo=document.getElementById(chequeNoId).value;	
						
						if(authDate.length == 0)
						{
							
							alert("Please enter the Authorization Date for ECS/N.EFT Code "+ecsCode.substring(0,ecsCode.indexOf("_"))+".");
							hideProgressbar();
							return false;
						}else if(chequeNo.length == 0 || chequeNo.length > 7)
						{
							alert("Please enter the Cheque No of appropriate size for ECS/N.EFT Code "+ecsCode.substring(0,ecsCode.indexOf("_"))+".");
							hideProgressbar();
							return false;
						}else if(compareDatesForAuth(document.getElementById('currdate'),document.getElementById(authDateId),'Please enter Authorization Date greater than current date.','<')== false)
						{
							
							//alert("Please enter Voucher Date less than current date.");
							//document.getElementById("txtVoucherDate").value="";
							//document.getElementById("txtVoucherDate").focus();
							hideProgressbar();
							return false;
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
		alert("Please select atleast one ECS/N.EFT File to Authorise.");
		return false;
	}

}

function authorizeECS()
{
	var ecsCodeString="";
	var ecsAuthDateString="";
	var ecsChequeNoString="";
	var ecsSettlementDate="";
	if(validateAuthorizeECS())
	{
		
		if(validateChequeNoExsist() == true)
		{
			
			ecsCodeString=getAllCheckedECSCode();
			ecsAuthDateString=getAllCheckedDateForAutho();
			ecsChequeNoString=getAllCheckedNoForAuth();
			ecsSettlementDate=getAllCheckedDateForSettlement();
			var uri="ifms.htm?actionFlag=authorizeECS&ecsCodeString="+ecsCodeString+"&ecsAuthDateString="+ecsAuthDateString+"&ecsChequeNoString="+ecsChequeNoString;
		//	authUsingAjax(uri);
			authoriseECSUsingAJAX(uri,ecsAuthDateString,ecsAuthDateString,ecsChequeNoString,ecsSettlementDate);
			showProgressbar();
		}
	}
	
}
function authoriseECSUsingAJAX(uri,ecsCodeString,ecsAuthDateString,ecsChequeNoString,ecsSettlementDate)
{
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&ecsCodeString="+ecsCodeString+"&ecsAuthDateString="+ecsAuthDateString+"&ecsChequeNoString="+ecsChequeNoString+"&ecsSettlementDate="+ecsSettlementDate,
		        onSuccess: function(myAjax) {
					getDataStateChangedForAuthECS(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForAuthECS(myAjax){
	
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
						
	
	for( var i=0; i<XmlHiddenValues.length ;i++) {
		 
		ecsCodeString= XmlHiddenValues[i].childNodes[0].nodeValue;
			
}
	
		alert("ECS/N.EFT File Authorized Succesfully for ECS/N.EFT Code "+ecsCodeString+".");
	
		window.self.location.reload();
}

function getAllCheckedDateForSettlement()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsDateString="";
	var ecsAuthDate="";
	var ecsAuthDateString="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="txtSettDate_"+rowNum;
					ecsAuthDate=document.getElementById(resultId).value;
					
					if(flag==0)
					{
						ecsAuthDateString=ecsAuthDateString+ecsAuthDate;
						flag=1;
					}
					else
					{
						ecsAuthDateString=ecsAuthDateString+"~"+ecsAuthDate;
					}
					
				}
			}
		}
	}
	return ecsAuthDateString;
}
function getAllCheckedDateForAutho()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsDateString="";
	var ecsAuthDate="";
	var ecsAuthDateString="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="txtAuthDate_"+rowNum;
					ecsAuthDate=document.getElementById(resultId).value;
					
					if(flag==0)
					{
						ecsAuthDateString=ecsAuthDateString+ecsAuthDate;
						flag=1;
					}
					else
					{
						ecsAuthDateString=ecsAuthDateString+"~"+ecsAuthDate;
					}
					
				}
			}
		}
	}
	return ecsAuthDateString;
}
function getAllCheckedNoForAuth()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsChequeString="";
	var ecsCheqNo="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="txtChequeNo_"+rowNum;
					ecsCheqNo=document.getElementById(resultId).value;
					
					if(flag==0)
					{
						ecsChequeString=ecsChequeString+ecsCheqNo;
						flag=1;
					}
					else
					{
						ecsChequeString=ecsChequeString+"~"+ecsCheqNo;
					}
					
				}
			}
		}
	}
	return ecsChequeString;
}
function authUsingAjax(url)
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
		var ecsCodeString=null;
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
									
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
					ecsCodeString= XmlHiddenValues[i].text;					
						
			}
				
					alert("ECS/N.EFT File Authorized Succesfully for ECS/N.EFT Code "+ecsCodeString+".");
				
					window.self.location.reload();
					
		}
		}
	};
	showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function generateMandate(ecsCode)
{
	//var uri="ifms.htm?actionFlag=generateMandate&ecsCode="+ecsCode;
	
	var uri="ifms.htm?actionFlag=reportService&reportCode=365450&action=generateReport&ecsCode="+ecsCode+"&asPopup=TRUE";
	window_new_update(uri);
	//document.frmECSFiles.action =uri;
	//document.frmECSFiles.submit();
	//window.self.location.reload();
	//showProgressbar();
}
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "ECSReport", urlstyle);
}
function discardECS()
{
	var ecsCodeString="";
	var chequeNoString="";
	
	if(validateForwardECS())
	{
		ecsCodeString=getAllCheckedECSCode();
		chequeNoString=getAllCheckedChequeNo();
		
		var uri="ifms.htm?actionFlag=discardECS&ecsCodeString="+ecsCodeString+"&chequeNoString="+chequeNoString;
	//	discardUsingAjax(uri);
		discardECSUsingAJAX(uri,ecsCodeString,chequeNoString);
		showProgressbar();
	}
}
function discardECSUsingAJAX(uri,ecsCodeString,chequeNoString)
{
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&ecsCodeString="+ecsCodeString+"&chequeNoString="+chequeNoString,
		        onSuccess: function(myAjax) {
					getDataStateChangedForDiscardECS(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForDiscardECS(myAjax){
	
	
	var ecsCodeString="";
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
						
	
	for( var i=0; i<XmlHiddenValues.length ;i++) {
		 
		ecsCodeString= XmlHiddenValues[i].childNodes[0].nodeValue;
}
	
		alert("ECS/N.EFT File Discarded Succesfully for ECS/N.EFT Code "+ecsCodeString+".");
	
		window.self.location.reload();
}
function discardUsingAjax(url)
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
		var ecsCodeString=null;
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('ECSCode');
									
				
				for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
					ecsCodeString= XmlHiddenValues[i].text;					
						
			}
				
					alert("ECS/N.EFT File Discarded Succesfully for ECS/N.EFT Code "+ecsCodeString+".");
				
					window.self.location.reload();
					
		}
		}
	};
	//showProgressbar('Please Wait<br>Your request is in progress...');
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
function getAllCheckedChequeNo()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsChequeNo="";
	//var ecsAuthDate="";
	var ecsChequeNoString="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="hdChequeId_"+rowNum;
					ecsChequeNo=document.getElementById(resultId).value;
					
					if(flag==0)
					{
						ecsChequeNoString=ecsChequeNoString+ecsChequeNo;
						flag=1;
					}
					else
					{
						ecsChequeNoString=ecsChequeNoString+"~"+ecsChequeNo;
					}
					
				}
			}
		}
	}
	return ecsChequeNoString;
}
function getAllCheckedBillNo()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsBillNo="";
	//var ecsAuthDate="";
	var ecsBillNoString="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="hdBillNo_"+rowNum;
					ecsBillNo=document.getElementById(resultId).value;
					
					if(flag==0)
					{
						ecsBillNoString=ecsBillNoString+ecsBillNo;
						flag=1;
					}
					else
					{
						ecsBillNoString=ecsBillNoString+"~"+ecsBillNo;
					}
					
				}
			}
		}
	}
	return ecsBillNoString;
}

function validateChequeNoExsist()
{
	var chequeNoString=getAllCheckedNoForAuthValidation();
	
	var uri="ifms.htm?actionFlag=valChequeNo&chequeNoString="+chequeNoString;
//	validateChequeNoExsistUsingAjax(uri);
	valChequeNoExsistUsingAJAX(uri,chequeNoString);

	if(tempFlag == 1)
	{
		
		return true;
	}
	else
	{
		
		return false;
	}
	showProgressbar();
}
function valChequeNoExsistUsingAJAX(uri,chequeNoString)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "chequeNoString="+chequeNoString,
		        onSuccess: function(myAjax) {
					getDataStateChequeNoExsist(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChequeNoExsist(myAjax)
{
	var chequeNo=null;
	var rowNum=null;
	var flag;
	var lIntFlag=0;
	var resultElementId=null;
	
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('CHEQUENO');
	var XmlHiddenValues1 = XMLDoc.getElementsByTagName('ROWNUM');
	var XmlHiddenValues2 = XMLDoc.getElementsByTagName('ISEXISTS');			
	
	flag=XmlHiddenValues2[0].childNodes[0].nodeValue;
	
	if(flag == "true")
	{
		
		
		for( var i=0; i<XmlHiddenValues.length ;i++) {
		 
			chequeNo= XmlHiddenValues[i].childNodes[0].nodeValue;
			rowNum=XmlHiddenValues1[i].childNodes[0].nodeValue;
			resultElementId="txtChequeNo_"+rowNum;
			}
			document.getElementById(resultElementId).focus();
			alert("Cheque No."+chequeNo+" already exsist.");
			document.getElementById(resultElementId).value="";
			tempFlag = 0;
	
	}
	else
	{
		tempFlag = 1;
		
	}
}
function validateChequeNoExsistUsingAjax(url)
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
		var flag;
		var lIntFlag=0;
		var resultElementId=null;
		if (xmlHttp.readyState==4)
		{ 
			if(xmlHttp.status==200)
			{
				
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('CHEQUENO');
				var XmlHiddenValues1 = XMLDoc.getElementsByTagName('ROWNUM');
				var XmlHiddenValues2 = XMLDoc.getElementsByTagName('ISEXISTS');			
				
				flag=XmlHiddenValues2[0].text;
				
				if(flag == "true")
				{
					
					
					for( var i=0; i<XmlHiddenValues.length ;i++) {
		    		 
						chequeNo= XmlHiddenValues[i].text;	
						rowNum=XmlHiddenValues1[i].text;
						resultElementId="txtChequeNo_"+rowNum;
						}
						document.getElementById(resultElementId).focus();
						alert("Cheque No."+chequeNo+" already exsist.");
						document.getElementById(resultElementId).value="";
						tempFlag = 0;
				
				}
				else
				{
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
function getAllCheckedNoForAuthValidation()
{
	var arrChkBox=document.getElementsByName("chkbxECSFiles");
	var rowNum="";
	var flag=0;
	var ecsCode="";
	var ecsChequeString="";
	var ecsCheqNo="";
	var resultId="";
	if (arrChkBox!=null)
	{
		
		if(arrChkBox.length > 0)
		{
			for(var i=0;i<arrChkBox.length;i++)
			{
				if(arrChkBox[i].checked)
				{
					ecsCode = arrChkBox[i].value;
					rowNum=ecsCode.substring(ecsCode.indexOf("_")+1);
					resultId="txtChequeNo_"+rowNum;
					ecsCheqNo=document.getElementById(resultId).value;
					ecsCheqNo=ecsCheqNo+"_"+rowNum;
					
					if(flag==0)
					{
						ecsChequeString=ecsChequeString+ecsCheqNo;
						flag=1;
					}
					else
					{
						ecsChequeString=ecsChequeString+"~"+ecsCheqNo;
					}
					
				}
			}
		}
	}
	return ecsChequeString;
}
function compareDates(fieldName1,fieldName2,alrtStr,flag)
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
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
		    	}
            }
        }
    }
    return true ;
}
/*---------Date Validation---------*/
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function chkValidDate(dtCtrl){

	var dtStr = dtCtrl.value;
	if(dtStr != '')
	{
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){

			dtCtrl.focus();
			alert('Please Enter Date in dd/mm/yyyy format ')
			dtCtrl.value='';
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){

			dtCtrl.focus();
			alert('Please Enter valid Month ')
			dtCtrl.value='';
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){

			dtCtrl.focus();
			alert('Please Enter valid Date ')
			dtCtrl.value='';
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){

			dtCtrl.focus();
			alert('Please Enter valid year ')
			dtCtrl.value='';
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){

			dtCtrl.focus();
			alert('Please Enter Date in dd/mm/yyyy format ')
			dtCtrl.value='';
			return false
		}
		return true
	}
	return false; 
}
/*---------Comparing two dates---------*/
function compareDatesForAuth(fieldName1,fieldName2,alrtStr,flag)
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
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	    return false;
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	    return false;
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	    return false;
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
    		    return false;
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
	    	    return false;
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
    			    return false;
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
    			    return false;
		    	}
            }
        }
    }
    return true ;
}

var Row_ID_ECSReturn=0;
function ecsreturndtls()
{		
	Row_ID_ECSReturn = document.getElementById("hidECSReturnDtlsGridSize").value;
	//var newRow =  document.all("tblReempDtls").insertRow();			  		

	var table=document.getElementById("tblECSReturn");
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
   	var Cell4=newRow.insertCell(3);
   	Cell4.className="tds";	
   	Cell4.align="center";
   	var Cell5=newRow.insertCell(4);
   	Cell5.className="tds";	
   	Cell5.align="center";
	var Cell6=newRow.insertCell(5);
   	Cell6.className="tds";	
   	Cell6.align="center";

   	Cell1.innerHTML = '<input type="text" name="txtMandateNo" id="txtMandateNo'+Row_ID_ECSReturn+'" size="15" />';
   	Cell2.innerHTML = '<input type="text" name="txtPPONo" id="txtPPONo'+Row_ID_ECSReturn+'" size="15" onblur="getPensionerName(this);"/>';
   	Cell3.innerHTML = '<input type="text" name="txtPensionerName" id="txtPensionerName'+Row_ID_ECSReturn+'" size="15" />';
	Cell4.innerHTML = '<input type="text" name="txtAmount" id="txtAmount'+Row_ID_ECSReturn+'" size="15" onblur="validateAmount(this);"/>';
	Cell5.innerHTML = '<select name="cmbROF" id="cmbROF'+Row_ID_ECSReturn+'" > <option value="-1">--SELECT--</option>'+LISTREASONS+'</select>';
	Cell6.innerHTML ='<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblECSReturn\')" /> '; 	
	   		
	document.getElementById("hidECSReturnDtlsGridSize").value = Number(Row_ID_ECSReturn)+1;  
}
function validateEmptyFields(rownum)
{

	if(document.getElementById("txtMandateNo"+rownum).value == "" || document.getElementById("txtMandateNo"+rownum).value == null)
	{
		alert("Please enter Mandate No.");
		return false;
	}
	if(document.getElementById("txtPPONo"+rownum).value == "" || document.getElementById("txtPPONo"+rownum).value == null)
	{
		alert("Please enter PPO No.");
		
		return false;
	}
	if(document.getElementById("txtAmount"+rownum).value == "" || document.getElementById("txtAmount"+rownum).value == null)
	{
		alert("Please enter PPO No.");
		
		return false;
	}
	return true;
}
function validateAmount(object)
{
	var elementId=object.id;
	var rownum=elementId.substring(9);
	
	var txtAmount=document.getElementById(elementId).value;
	var txtPPONo=document.getElementById("txtPPONo"+rownum).value;
	var txtMandateNo=document.getElementById("txtMandateNo"+rownum).value;
	if(txtAmount!=null && txtAmount!="")
	{
	uri="ifms.htm?actionFlag=validateAmntFrmMandate";

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters: "&txtAmount="+txtAmount+"&txtPPONo="+txtPPONo+"&txtMandateNo="+txtMandateNo+"&validationFlag=S",
		        onSuccess: function(myAjax) {
							getDataStateChangedForValidateAmount(myAjax,rownum);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	}
}
function getDataStateChangedForValidateAmount(myAjax,rownum)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	//var resultElementId="cmbBankBranchName"+rownum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('VALID');
	var flag=XmlHiddenValues[0].childNodes[0].nodeValue;
	
	if(flag=="false")
	{
		alert("Please enter valid data.");
		document.getElementById("txtPPONo"+rownum).value="";
		document.getElementById("txtAmount"+rownum).value="";
		document.getElementById("txtMandateNo"+rownum).value="";
		document.getElementById("txtPensionerName"+rownum).value="";
	}
}
function validateBeforeSave()
{
	if(validateReturnedECS())
	{
		
		var uri="ifms.htm?actionFlag=validateAmntFrmMandate";
		var url = runForm(0); 
	
	
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: url+"&validationFlag=M",
			        onSuccess: function(myAjax) {
							getDataStateChangedForValidation(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
}
function getDataStateChangedForValidation(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	//var resultElementId="cmbBankBranchName"+rownum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('VALID');
	var flag=XmlHiddenValues[0].childNodes[0].nodeValue;

	if(flag=="false")
	{
		var XmlHiddenValues1 = XMLDoc.getElementsByTagName('PPONO');
		var PPONo=XmlHiddenValues1[0].childNodes[0].nodeValue;
		alert("Please enter valid data for PPO No "+PPONo);
		return false;
	}
	else
	{
		saveReturnedECSData();

		window.self.location.reload();
		return true;
	}
	
}
function validateReturnedECS()
{
	
	var returnECSLength=Number(document.getElementById("hidECSReturnDtlsGridSize").value);
	var rowCountForECSReturn=document.getElementById("tblECSReturn").rows.length;
	 if(rowCountForECSReturn>0)
	 {
		for(var rowfmlyCnt=0;rowfmlyCnt<Number(returnECSLength);rowfmlyCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtMandateNo"+rowfmlyCnt,"Please Enter Manadate Serial No.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtPPONo"+rowfmlyCnt,"Please Enter PPO No.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtAmount"+rowfmlyCnt,"Please Enter Amount.")==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbROF"+rowfmlyCnt,"Please Select Reason.")==false)
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
function saveReturnedECSData()
{
	
		
			var uri="ifms.htm?actionFlag=saveECSReturnedDtls";
			var url = runForm(0); 


			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters: url,
				        onSuccess: function(myAjax) {
								getDataStateChangedForECSReturned(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
		
	
}
function getDataStateChangedForECSReturned(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	//var resultElementId="cmbBankBranchName"+rownum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('SAVED');

	var flag=XmlHiddenValues[0].childNodes[0].nodeValue;
	
	if(flag=="Y")
	{
		
		alert("Data Saved Successfully");
		return true;
	}
	else
	{
		alert("Sorry data cannot be saved");
		return false;
	}
}
function getPensionerName(object)
{
	
	var elementId=object.id;
	var rownum=elementId.substring(8);

	var ppoNo=document.getElementById(elementId).value;
	
	if(ppoNo!=null && ppoNo!="")
	{
		var uri="ifms.htm?actionFlag=getPenNameFromPPONo";
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: "&PPONo="+ppoNo,
			        onSuccess: function(myAjax) {
			getDataStateChangedForPenName(myAjax,rownum);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
}
function getDataStateChangedForPenName(myAjax,rownum)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	//var resultElementId="cmbBankBranchName"+rownum;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('PENSIONERNAME');
	alert(XmlHiddenValues);
	
	
	
	if(XmlHiddenValues[0].childNodes.length>0)
	{
		var pensionerName=XmlHiddenValues[0].childNodes[0].nodeValue;
		document.getElementById("txtPensionerName"+rownum).value=pensionerName;
		
	}else 
	{
		alert("Please enter a valid PPO No.");
		document.getElementById("txtPPONo"+rownum).value="";
		
	}
	
}