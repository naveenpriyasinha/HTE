var gIsReloadOpner = true;
var dtCh= "/";
var minYear=1900;
var maxYear=2500;		

function amountFormat(lThis)
{
	if(window.event.keyCode == 46)
	{
		var lStr = new String(lThis.value);
		lStr = lStr.trim();
		if(lStr.indexOf('.') != -1)
		{	
			window.event.keyCode = 0;
		}
	}
	else if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
	{
		window.event.keyCode = 0;
	}
}

function setValidAmountFormat(lThis)
{
	var lStr = new String(lThis.value);
	lStr = lStr.trim();
	lThis.value = Math.round(lStr) + ".00";
}

function ChkHdrChkBx(theElement)
{
	document.getElementById("chkSelect").checked = true;
	var theForm = theElement.form;
	for(var z = 0; z < theForm.elements.length; z++)
	{
		if(theForm.elements[z].type == 'checkbox' && theForm.elements[z].name != 'chkSelect')
		{
			if(theForm.elements[z].checked == false)
			{
				document.getElementById("chkSelect").checked = false;
				break;
			}
		}
	}
}

function checkUncheckAll(theElement) 
{
	var theForm = theElement.form;
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
		{
			theForm[z].checked = theElement.checked;
		}
	}
}

function formateWholeDate(ele)
{	
	var str1="0";
	var str5="200";
	var str6="20";
	var str7="2";
	var val = ele.value;

	if(ele.value == "")
		return true;
	var valArr=val.split("/");
	if(valArr.length!=3)
	{	
		alert('Invalid Date');
		ele.focus();
		return false;
	}
	
	var str2=valArr[0];
	var str3=valArr[1];
	var str4=valArr[2];

	if(valArr[0]!="" && valArr[1]!="" && valArr[2]!="")
	{	
		if (str2<=31 && str3<=12)
		{
			if(valArr[0].length==1)
				valArr[0] = str1.concat(str2);
			else if(valArr[0].length==2)
				valArr[0]=valArr[0];
	
			if(valArr[1].length==1)
				valArr[1] = str1.concat(str3);
			else if(valArr[1].length==2)
				valArr[1]=valArr[1];
	
			if(valArr[2].length==1)
				valArr[2]=str5.concat(str4);
			else if(valArr[2].length==2)
				valArr[2]=str6.concat(str4);
			else if(valArr[2].length==3)
				valArr[2]=str7.concat(str4);
			else
				valArr[2]=valArr[2];
		}
		else
		{
			ele.focus();
			return false;
		}
	}	
	else
	{
		alert("Enter valid date");
		ele.focus();
		return false;
	}
	ele.value = (valArr[0] + "/" + valArr[1] + "/" + valArr[2]);
	return true;	
}
		
		
		
		Math.format = function(number)
		{
			var lStr = new String(Math.round(number * 100) / 100);
			
			if(lStr.indexOf('.') == -1)
			{
				lStr += ".00"; 
			}
			else if((lStr.indexOf('.') + 2) == lStr.length)
			{
				lStr += "0";
			} 
			
			return lStr;
		}
		
		var majorNames = new Array(7);
		majorNames[0] = "";
    	majorNames[1] = " Thousand";
    	majorNames[2] = " Lac";
    	majorNames[3] = " Crore";
    	majorNames[4] = " Trillion";
    	majorNames[5] = " Quadrillion";
		majorNames[6] = " Quintillion";
		
		var tensNames = new Array(10);
		tensNames[0] = "";
    	tensNames[1] = " Ten";
    	tensNames[2] = " Twenty";
    	tensNames[3] = " Thirty";
    	tensNames[4] = " Fourty";
    	tensNames[5] = " Fifty";
		tensNames[6] = " Sixty";
		tensNames[7] = " Seventy";
		tensNames[8] = " Eighty";
		tensNames[9] = " Ninety";
		
		var numNames = new Array(10);
		numNames[0] = "";
    	numNames[1] = " One";
    	numNames[2] = " Two";
    	numNames[3] = " Three";
    	numNames[4] = " Four";
    	numNames[5] = " Five";
		numNames[6] = " Six";
		numNames[7] = " Seven";
		numNames[8] = " Eight";
		numNames[9] = " Nine";
		numNames[10] = " Ten";
		numNames[11] = " Eleven";
		numNames[12] = " Twelve";
		numNames[13] = " Thirteen";
		numNames[14] = " Fourteen";
		numNames[15] = " Fifteen";
		numNames[16] = " Sixteen";
		numNames[17] = " Seventeen";
		numNames[18] = " Eighteen";		
		numNames[19] = " Nineteen";
				
		function convertLessThanOneThousand(number) 
		{
			var soFar = new String("");

    		if (parseInt(number % 100) < 20)
    		{
        		soFar = numNames[parseInt(number % 100)];
        		number = parseInt(number / 100);
       		}
    		else 
    		{
        		soFar = numNames[parseInt(number % 10)];
        		number = parseInt(number / 10);

        		soFar = tensNames[parseInt(number % 10)] + soFar;
        		number = parseInt(number / 10);
       		}
    		if (number == 0) 
    		{
    			return soFar;
    		}
    		
    		return numNames[number] + " hundred" + soFar;	    	
		}
		
		function convert(number)
		{
			var prefix = new String("");
			var soFar = new String("");
    		var place = 0;

			if (number == 0) 
			{ 
				return "zero"; 
			}
    		if (number < 0) 
    		{
        		number = -number;
        		prefix = "negative ";
      		}

      		var n = parseInt(number % 1000);
    		if(n != 0)
    		{
       			var s = new String(convertLessThanOneThousand(n));
       			soFar = s + majorNames[place] + soFar;
       		}

   			place++;
   			number = parseInt(number / 1000);

    		while(number > 0) 
    		{
      			var n = parseInt(number % 100);
      			if(n != 0)
      			{
         			var s = new String(convertLessThanOneThousand(n));
         			soFar = s + majorNames[place] + soFar;
         		}

      			place++;
      			number = parseInt(number / 100);
      		} 

    		return prefix + soFar.trim();	    	
		}
		

function getAmountInWords(number) 
{
 	var s = new String("");

		if(isNaN(number))
		{
			return s;
		}
		
	if(number  > 10000000 )
	{
		s += convert(number / 10000000) + " Crore"; 
	
		if (number % 10000000 != 0)
		{
			s += convert(number % 10000000);  
		}
	}
	else
	{
		s += convert(number); 
	}
	s += " Rupees ";
		
	return s;
}
		

function NumberFormet()
{
	if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
	{
		window.event.keyCode = 0;
	}				
}

function getNumber(CurrentLength, MaxLength)
{
	var lStrReturn = "";
	for(var lIntCount = CurrentLength; lIntCount < MaxLength; ++lIntCount)
	{
		lStrReturn += "0";
	}
	
	return lStrReturn;
}		

// Functions For newBillSelection.jsp....... Starts......
	
	//onerror = handleErr;
	
	function handleErr(Message, URL, LineNo)
		{
			var eText = "There was an error on this page.\n\n";
			eText += "Error: " + Message + "\n";
			eText += "URL: " + URL + "\n";
			eText += "Line: " + LineNo + "\n\n";
			eText += "Click OK to continue.\n\n";
			alert(eText);
			
			return true;
		}
	
// Functions For newBillSelection.jsp....... Ends......	


// Functions For srchRqstInit.jsp ...... Starts......			
		
		function isValidDate1(dtStr)
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
			for (var i = 1; i <= 3; i++) 
			{
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
			}
			month=parseInt(strMonth)
			day=parseInt(strDay)
			year=parseInt(strYr)
			if (pos1==-1 || pos2==-1)
			{
				alert(SRCH_DTFORMAT);
				return false;
			}
			if (strMonth.length<1 || month<1 || month>12)
			{
				alert(SRCH_VALMNTH);
				return false;
			}
			if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
			{
				alert(SRCH_VALDAY);
				return false;
			}
			if (strYear.length != 4 || year==0 || year<minYear || year>maxYear)
			{
				alert(SRCH_VALDIGIT);
				return false;
			}
			if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false)
			{
				alert(SRCH_VALDT);
				return false;
			}
			return true;
		}
		
		function daysInFebruary (year)
		{
		   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
		}
		
		function DaysArray(n) 
		{
			for (var i = 1; i <= n; i++) {
				this[i] = 31
				if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
				if (i==2) {this[i] = 29}
	  	 	} 
	   		return this;
		}
		
		function isInteger(s)
		{
			var i;
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (((c < "0") || (c > "9"))) return false;
		    }
		    return true;
		}
		
		function stripCharsInBag(s, bag)
		{
			var i;
		    var returnString = "";
		    
		    for (i = 0; i < s.length; i++){   
		        var c = s.charAt(i);
		        if (bag.indexOf(c) == -1) returnString += c;
		    }
		    return returnString;
		}
		
		function isProper(frmStr,toStr,alertStr)
		{
			if(frmStr.length > 0 && frmStr.length > 0)
			{
				var frmdaysInMonth = DaysArray(12)
				var frmpos1=frmStr.indexOf(dtCh)
				var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
				var frmDay=frmStr.substring(0,frmpos1)
				var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
				var frmYear=frmStr.substring(frmpos2+1)
				
				var todaysInMonth = DaysArray(12)
				var topos1=toStr.indexOf(dtCh)
				var topos2=toStr.indexOf(dtCh,topos1+1)
				var toDay=toStr.substring(0,topos1)
				var toMonth=toStr.substring(topos1+1,topos2)
				var toYear=toStr.substring(topos2+1)
				
				if(frmYear > toYear)
				{
					alert(alertStr);
					return false;
				} 
				if(frmYear == toYear) 
				{
					if(frmMonth > toMonth)
					{
						alert(alertStr);
						return false;
					}
				} 
				if((frmYear == toYear) && (frmMonth == toMonth))
				{
					if(frmDay > toDay)
					{
						alert(alertStr);
						return false;
					}
				}
				return true;	
			}		
		} 
		function validateDate(lStr)
		{
			if(lStr.value.length > 0)
			{
				if(isValidDate1(lStr.value) == false)
				{
					lStr.value=""
					lStr.focus();
					return false;
				}
				else
				{
					return true
				}
			}
		}
// Functions For srchRqstInit.jsp ...... Ends......		

function createXMLHttpRequest() 
	{ 
  		var ua; 
		
		if(window.XMLHttpRequest) 
		{ 
			try 
			{ 
				ua = new XMLHttpRequest(); 
			} 
			catch(e) 
			{ 
				ua = false; 
			} 
		} 
		else if(window.ActiveXObject) 
		{ 
			try 
			{ 
				ua = new ActiveXObject("Microsoft.XMLHTTP"); 
			} 
			catch(e) 
			{ 
				ua = false; 
			} 
		} 
		return ua; 
	}

function goToFieldTab(field,cnt)
{
	goToTab(cnt);
	
	if(document.getElementById(field) != null)
	{
		document.getElementById(field).focus();
	}			
}

function getBranchByBranchCode()
{
	if(document.getElementById("txtbranchCode").value.length >0 )
	{
		req = createXMLHttpRequest();
		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=getBranchbyCode&txtbranchCode="+document.getElementById("txtbranchCode").value;
			if(document.getElementById("cmbTreasury"))
			{
				baseUrl = baseUrl+"&cmbTreasury="+document.getElementById("cmbTreasury").value;
			}
			
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responsegetBranchByBranchCode; 
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}
	}
	else
	{
		document.getElementById("cmbBank").value = "-1";
		document.getElementById("cmbBranch").innerHTML = "";
		document.getElementById("cmbBranch").add(new Option(" -- Select -- ","-1"));
		document.getElementById("cmbBank").disabled="";
		document.getElementById("cmbBranch").disabled="";
		if(document.getElementById("mandtryBank"))
		{
			document.getElementById("mandtryBank").style.display = "none";
		}
	}
}

function responsegetBranchByBranchCode()
{
	if(req.readyState==complete_state)
	{ 
		var XMLDoc = req.responseXML.documentElement;
		var XmlResValues = XMLDoc.getElementsByTagName('XMLDOC');	
			if(XmlResValues[0].childNodes[0] != null)
			{
				document.getElementById("cmbBank").value = XmlResValues[0].childNodes[0].text
				document.getElementById("cmbBranch").options[0].value = XmlResValues[0].childNodes[1].text;
				document.getElementById("cmbBranch").options[0].innerHTML = XmlResValues[0].childNodes[2].text;
				document.getElementById("cmbBranch").options[0].selected = "selected"
				document.getElementById("cmbBank").disabled="disabled";
				document.getElementById("cmbBranch").disabled="disabled";
				if(document.getElementById("mandtryBank"))
				{
					document.getElementById("mandtryBank").style.display = "inline";
				}
				if(document.getElementById("txtFmName"))
				{
					displyBankAndBranch();
				}
			}
		
		else
		{
			alert("Please Enter Proper Bankcode");
			document.getElementById("txtbranchCode").focus();
			document.getElementById("txtbranchCode").value = "";
			document.getElementById("cmbBank").value = "-1";
			document.getElementById("cmbBranch").options[0].value = "-1";
			document.getElementById("cmbBranch").options[0].innerHTML = " -- Select --";
			document.getElementById("cmbBank").disabled="";
			document.getElementById("cmbBranch").disabled="";
			if(document.getElementById("mandtryBank"))
			{
				document.getElementById("mandtryBank").style.display = "none";
			}
		}
	}
}

function changeOnBlur(element)
{
	if(element.type=='text')
	{
		// text onblur
		element.style.backgroundColor = 'white';
		element.style.borderColor='black';
		element.style.borderWidth= '1px'; 
	    element.style.borderStyle= 'solid' ;
	 }
	 if(element.type=='select')
	 {
	 	// select onblur
	 	element.style.backgroundColor = 'white';
		element.style.borderColor='black';
		element.style.borderWidth= '1px'; 
	    element.style.borderStyle= 'solid' ;
	 }
	 if(element.type=='checkbox')
	 {
	 	element.style.backgroundColor = 'white';
		element.style.borderColor='black';
		element.style.borderWidth= '1px'; 
	    element.style.borderStyle= 'solid' ;
	 }
}

function changeOnFocus(element)
{
	if(element.type=='text')
	{
		// text onfocus
		element.style.backgroundColor ='#FEEEF4';
		element.style.borderColor= '#FF0000';
		element.style.borderWidth= '2px'; 
		element.style.borderStyle= 'solid' ;
	}
	if(element.type=='select')
	{
		// text onfocus
		element.style.backgroundColor ='#FEEEF4';
		element.style.borderColor= '#FF0000';
		element.style.borderWidth= '2px'; 
		element.style.borderStyle= 'solid' ;
	}
	if(element.type=='checkbox')
	{
		element.style.backgroundColor ='#FEEEF4';
		element.style.borderColor= '#FF0000';
		element.style.borderWidth= '2px'; 
		element.style.borderStyle= 'solid' ;
	}
}

function upperCase(event)
{
	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode <= 57 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}

function upperCaseNumber(event)
{
	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode < 47 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}
function upperCaseNumberPPo(event)
{
	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode < 47 && window.event.keyCode != 46 && window.event.keyCode != 45 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}

// Month - Year checkign for 
function validateMM(txtElmnt,max)
{
	var max2 = max.charAt(0);
	var max1 = max.charAt(1);
	var num = Number(String.fromCharCode(window.event.keyCode));
	
	if(document.getElementById(txtElmnt+'MM').value == "")
	{	
		if(num > max2)
		{			
			window.event.keyCode = 0;
			document.getElementById(txtElmnt+'MM').value = '0'+num;				
		}
	}
	if(document.getElementById(txtElmnt+'MM').value == max2)
	{
		if(num > max1)
		{
			window.event.keyCode = 0;
		}			
	}
	setFocusOnYear(txtElmnt);				
}

function setFocusOnYear(txtElmnt)
{
	if(document.getElementById(txtElmnt+'MM').value.length == 2)
	{
		document.getElementById(txtElmnt+'YYYY').focus();
	}
}

function SubmitObjData()
{
//	if(confirm(CMN_PromptObjections))
		var objList='';
		for(i=0;i<document.forms[0].elements.length;i++)
	    {
	        if(document.forms[0].elements[i].type=="checkbox" && document.forms[0].elements[i].name != 'chkSelect')
	        {	
    	      if(document.forms[0].elements[i].checked == true)
        	  {
          		objList = objList+'~'+document.forms[0].elements[i].value;
	          }
	        } 
	    }
//		window.opener.document.forms[0].chkbox.value = objList;
		document.forms[0].hidChkbox.value = objList;
}

// to test the length of textarea
function limitText(limitField, limitNum) 
{
    if (limitField.value.length > limitNum) 
    {
        limitField.focus();
        return false;
    }
  
   return true;
}
