
/*		JAVASCRIPT FUNCTION TO HIGHLIGHT THE FOCUSED CONTROL  */
function showHighLight(cntrl)
{
    cntrl.style.borderColor = "Red";
}

/*		JAVASCRIPT FUNCTION TO REMOVE HIGHLIGHT FROM FOCUSED CONTROL  */
function hideHighLight(cntrl)
{
    cntrl.style.borderColor = 'transparent';
    cntrl.style.borderStyle = 'inset';
    cntrl.style.borderWidth = "1px";
}

//////////////////////////////////////////////// added For Date Chcking

function convertToUpper(src)
{
	src.value = src.value.toUpperCase();
	hideHighLight(src);
}

function alphanumeric(ctrl,msg)
{
	
	var numaric = ctrl.value;
	for(var j=0; j<numaric.length; j++)
		{
		  var alphaa = numaric.charAt(j);
		  var hh = alphaa.charCodeAt(0);
		  if((hh > 47 && hh<59) || (hh > 64 && hh<91) || (hh > 96 && hh<123))
		  {
		  }
		  else	
		  {
		  	 alert(msg +' should contain only AlphaNumeric characters');
		  	 ctrl.focus();
			 return false;
		  }
		}
    return true;
}

var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s,msg)
{
	var i;	
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
        	alert(msg +" is not a number");
        	ctrl.focus();
	        return false;
	     }
    }
    // All characters are numbers.
    return true;
}

function chkInteger(ctrl,msg)
{
	var s=ctrl.value;	
	var i;	
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
        	alert(msg +" should be a number");
        	ctrl.focus();
	        return false;
	     }
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}


function isDate(dtStr){	
	
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
		alert("The date format should be : dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}
return true
}

function chkDate(dtCtrl,msg){
	
	var dtStr = dtCtrl.value;
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
		alert("The date format should be : dd/mm/yyyy in " +msg)
		dtCtrl.focus();
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month in " +msg)
		dtCtrl.focus();
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day in " +msg)
		dtCtrl.focus();
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear +" in " +msg)
		dtCtrl.focus();
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date in " +msg)
		dtCtrl.focus();
		return false
	}
	return true
}

function compareBillCheqDates(dtStr1, dtStr2){
	if(chkDate(dtStr1,'Cheque Date')== true )
	{
		dtStr1 = dtStr1.value;
		var daysInMonth = DaysArray(12)
		var pos11=dtStr1.indexOf(dtCh)
		var pos12=dtStr1.indexOf(dtCh,pos11+1)
		var strDay1=dtStr1.substring(0,pos11)
		var strMonth1=dtStr1.substring(pos11+1,pos12)
		var strYear1=dtStr1.substring(pos12+1)
		strYr1=strYear1
		if (strDay1.charAt(0)=="0" && strDay1.length>1) strDay1=strDay1.substring(1)
		if (strMonth1.charAt(0)=="0" && strMonth1.length>1) strMonth1=strMonth1.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr1.charAt(0)=="0" && strYr1.length>1) strYr1=strYr1.substring(1)
		}
		month1=parseInt(strMonth1)
		day1=parseInt(strDay1)
		year1=parseInt(strYr1)
		var pos21=dtStr2.indexOf(dtCh)
		var pos22=dtStr2.indexOf(dtCh,pos21+1)
		var strDay2=dtStr2.substring(0,pos21)
		var strMonth2=dtStr2.substring(pos21+1,pos22)
		var strYear2=dtStr2.substring(pos22+1)
		strYr2=strYear2
		if (strDay2.charAt(0)=="0" && strDay2.length>1) strDay2=strDay2.substring(1)
		if (strMonth2.charAt(0)=="0" && strMonth2.length>1) strMonth2=strMonth2.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr2.charAt(0)=="0" && strYr2.length>1) strYr2=strYr2.substring(1)
		}
		month2=parseInt(strMonth2)
		day2=parseInt(strDay2)
		year2=parseInt(strYr2)
		if(month1 < month2 || day1 < day2 || year1 < year2 )
		{
			alert("Cheque Date cannot be less than Bill Inward Date  ");
			return false;
		}
				
	}
	else
	{
		return false;
	}
	return true;
}

/*		JAVASCRIPT FUNCTION TO OPEN POP-UP WINDOW FOR DDO-HEAD STRUCTURE		*/
function openDdoHeadPopup()
{
//			var url="ifms.htm?actionFlag=getHeadsForDDO&cmbBudType=" + document.getElementById("id_cmbPlan").value+"&DDOCode=" +document.frmCntrInwPhyBills.DDOCode.value +"&billFlag=1";
	var url="ifms.htm?actionFlag=getGrantHeadsForDDO&cmbBudType=" + document.getElementById("id_cmbPlan").value+"&DDOCode=" +document.forms[0].DDOCode.value +"&billFlag=1";
	window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=80,left=30,width=800,height=400"); 
}

function reflectAmount(cntrl, msg, maxlen)
{
	hideHighLight(cntrl);
	if(chkAmtLength(cntrl,msg,maxlen)==true)
		document.getElementById('id_txtNetAmt').value=document.getElementById('id_txtGrossAmt').value;
}

/*		JAVASCRIPT FUNCTION TO CHECK THE LENGTH OF HEAD STRUCTURE ELEMENT
				 AND IF NOT PROPER THEN APPENDING IT WITH '0'	
*/
function chkHeadLength(ctrl,maxLen)
{
	hideHighLight(ctrl);
	var val = ctrl.value;
	var actLen = ctrl.value.length;
	var tempStr;
	if(actLen < maxLen && actLen!=maxLen && actLen>0)
	{
		tempStr = "0";
		for(var i=1;i < (maxLen-actLen);i++)
		{
			tempStr = tempStr + "0";
		}
		tempStr = tempStr + val;
	}
	else
	{
		tempStr = val;
	}
	ctrl.value = tempStr;
	return true;
}

/*		JAVASCRIPT FUNCTION TO CHECK THE LENGTH OF AMOUNT 		*/
function chkAmtLength(cntrl,msg,maxlen)
{
	var indx = cntrl.value.indexOf('.');
	hideHighLight(cntrl);
	if(indx==-1 && cntrl.value.length == maxlen)
	{
		alert('Invalid amount in ' +msg);
		cntrl.focus();
		return false;
	}
	return true;
}

/*		JAVASCRIPT FUNCTION TO CHECK WHETHER VALUE FROM COMBO IS SELECTED OR NOT*/
function selCombo(ctrl, msg)
{
	var indx = ctrl.selectedIndex;
	if(indx==0)
	{
		alert(msg +' is required to select.');
		ctrl.focus();
		return false;
	}
	return true;
}

/*		JAVASCRIPT FUNCTION TO CHECK FOR EMPTY STRING	*/
function isEmpty(s)
{   
	return ((s == null) || (s.length == 0))
}

/*		JAVASCRIPT FUNCTION TO CHECK VALIDITY OF REMARKS	*/		
function validRemarks(s)
{
	var i;
	var reAlphanumeric = /^[a-zA-Z0-9\s\.\_\n\r]*$/;
    if (isEmpty(s)) 
       	if (validRemarks.arguments.length == 1) 
       		return defaultEmptyOK;
       	else 
     			 return (validRemarks.arguments[1] == true);

    else 
    {
       	return reAlphanumeric.test(s);
    }
}

/*		JAVASCRIPT FUNCTION TO SHOW/HIDE BILLCODE BASED ON EXEMPTED		*/
function showExempt()
{
	if(document.forms[0].radExempted[0].checked)
	{
		document.getElementById('divExempted').style.display='block';
	}
	else
	{
		if(document.forms[0].radExempted[1].checked)
		{
			document.getElementById('divExempted').style.display='none';
			document.getElementById('id_cmbBillCode').value='';
		}
	}
}

/*		JAVASCRIPT FUNCTION TO DISALLOW CHARACTERS OTHER THAN NUMERIC		*/
function AmountFormat()
{
	if(!((window.event.keyCode > 47 && window.event.keyCode < 58) || window.event.keyCode == 46))
	{
		window.event.keyCode = 0;
	}
}

/*		JAVASCRIPT FUNCTION TO CHECK FOR EMPTY TEXTBOX		*/
function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="")
	{
		alert(msg +" cannot be empty.");
		ctrl.focus();
		return false;
	}		
	else
		return true;
}

/*		JAVASCRIPT FUNCTION TO CLEAN HEAD DETAILS IF CHANGE IS MADE TO ANY HEAD INFO FIELD.		*/
function cleanHeadDtls(lThis)
{
	if(document.getElementById("id_cmbDemand") == lThis)
	{
		document.getElementById("id_cmbMajorHead").value = "";
		document.getElementById("id_cmbSubMajorHead").value = "";
		document.getElementById("id_cmbMinorHead").value = "";
		document.getElementById("id_cmbSubHead").value = "";
//		document.getElementById("id_txtSchemeCode").value="";
	}
	else if(document.getElementById("id_cmbSubMajorHead") == lThis)
	{
		document.getElementById("id_cmbMinorHead").value = "";
		document.getElementById("id_cmbSubHead").value = "";
//		document.getElementById("id_txtSchemeCode").value="";
	}
	else if(document.getElementById("id_cmbMinorHead") == lThis)
	{
		document.getElementById("id_cmbSubHead").value = "";
//		document.getElementById("id_txtSchemeCode").value="";
	}
	document.getElementById("id_cmbDetailHead").value = "";
//	document.getElementById("id_txtSchemeCode").value="";
}

/*		JAVASCRIPT FUNCTION TO HANDLE BILL SAVING USING AJAX		*/
function HandleBillAjaxSave()
{
	xmlHttp=GetXmlHttpObject();		
	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 
			  
	var url=run(); 
	var actionf=document.forms[0].actionFlag.value
	var uri='ifms.htm?actionFlag='+actionf;
	url=uri + url;           
		
	xmlHttp.onreadystatechange=billstateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
	if(xmlHttp.responseText.length<=100)
		alert(xmlHttp.responseText);
}

/*		JAVASCRIPT FUNCTION FOR CREATING XmlHttpRequest.		*/
function createXMLHttpRequest() 
{ 
	var ua; 
		
	if(window.XMLHttpRequest) 
	{ 
		ua = new XMLHttpRequest(); 
	} 
	else if(window.ActiveXObject) 
	{ 
		ua = new ActiveXObject("Microsoft.XMLHTTP"); 
	} 

	return ua; 
} 

/*		JAVASCRIPT FUNCTION TO SHOW POP-UP ON AUDITOR NET AMOUNT MISMATCH		*/
function showAuditAmt(url)
{
	var adtAmt = document.getElementById('id_txtAdtNetAmt').value;
	var netAmt = document.getElementById('id_txtNetAmt').value;
	if(adtAmt!='')
	{
		if(parseFloat(adtAmt) != parseFloat(netAmt))
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
			return false;
		}
	}
	return true;
}

/*		JAVASCRIPT FUNCTION FOR CONVERTING AMOUNT IN WORDS		*/
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

/*		JAVASCRIPT VARIABLES FOR CONVERTING AMOUNT IN WORDS		*/
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
		
/*		JAVASCRIPT FUNCTION TO CONVERT AMOUNT IN WORDS(AMOUNT < 1000)		*/		
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

/*		JAVASCRIPT FUNCTION TO CONVERT NUMBERED AMOUNT IN WORDS		*/
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
  		do 
  		{
    			var n = parseInt(number % 1000);
    			if(n != 0)
    			{
       			var s = new String(convertLessThanOneThousand(n));
       			soFar = s + majorNames[place] + soFar;
       		}
    			
    			place++;
    			number = parseInt(number / 1000);
    		} while(number > 0);

  		return prefix + soFar.trim();	    	
}

/*		JAVASCRIPT FUNCTION TO GET AMOUNT IN WORDS		*/
function getAmountInWords(number) 
{
 	var s = new String("");

	if(number  > 10000000 )
	{
		s = convert(number / 10000000) + " Crore"; 
	
		if (number % 10000000 != 0)
		{
			s += convert(number % 10000000);  
		}
	}
	else
	{
		s = convert(number); 
	}
	return s;
}
			
/*		JAVASCRIPT FUNCTION TO VALIDATE CHALLAN DETAILS IF BILL IS TC.	*/			
function checkChallanDetails()
{
	var tbl = document.getElementById("TCTable");
 	    var rowSize = tbl.rows.length;
 	    var rowLen = document.forms[0].rowCount.value;
 	    var total=0;	  	    
 	    var	grossAmt=document.getElementById('id_txtGrossAmt').value;
	if(document.forms[0].rowCount.value==0)
 	   	document.forms[0].rowCount.value=1;
 	else
	 	document.forms[0].rowCount.value=rowSize-1;

	    for(ii=1;ii<=(rowSize-1);ii++)
	    {
	    	var challanDt = eval("document.forms[0].txtChallanDate"+(ii));
	    	var challanMjrHd = eval("document.forms[0].txtChallanMjrHead"+(ii));
	    	var challanAmt = eval("document.forms[0].txtChallanAmt"+(ii));
	    	
		   	if(challanDt.value!='')
		   	{
	 	   		if(chkDate(challanDt,"Challan Date")==false)
		   			return false;
	  	    }
	 	   	if(challanMjrHd.value=='')
			{
				alert("Challan Major Head is Required");
				challanMjrHd.focus();
				return false;
			}				  	    
	  	    if(challanAmt.value=='')
			{
				alert("Challan Amount is Required");
				challanAmt.focus();
				return false;
			}
    	}
	    if(parseFloat(document.forms[0].challanTotal.value) != parseFloat(grossAmt))
		{
			alert('Total Challan Amount should be equal to Gross Amount');
			return false;
		}
	    return true;			
}

/*		JAVASCRIPT FUNCTION TO VALIDATE BUDGET TYPE AND SCHEME NO 		*/
function checkBudType()
{
	var budIndx = document.getElementById('id_cmbPlan').options[document.getElementById('id_cmbPlan').selectedIndex].text;
	var schemeCode = document.getElementById('id_txtSchemeCode');
	if(budIndx==6 || budIndx==8 || budIndx==9)
	{
		if(chkEmpty(schemeCode,"Scheme Code for Plan Budget"))
		{
			if(schemeCode.value.length!=6)
			{
				alert('Scheme Code must be of 6 digit length');
				schemeCode.focus();
				return false;		
			}
		}
		else
			return false;
	}
	if(budIndx!=6 && budIndx!=8 && budIndx!=9)
	{
		if(isEmpty(schemeCode.value)==false)
		{
			alert('Other than Planned Budget Type, Scheme Code cannot be entered.');
			schemeCode.focus();
			return false;
		}		
	}
	return true;
}

/*		JAVASCRIPT FUNCTION FOR BASIC VALIDATIONS RELATED TO BILL		*/
function basicBillValidation()
{
	var i=0;
	var exemptLen = document.getElementById('radExempted');
	if(chkEmpty(document.getElementById('id_CardexNo'),"Cardex No.")!=true)
	 	return false;	
	if(chkInteger(document.getElementById('id_CardexNo'),"Cardex No.")!=true)
		return false;
	if(chkEmpty(document.getElementById('id_txtTokenNo'),"Token No.")!=true)
		return false;
	if(chkInteger(document.getElementById('id_txtTokenNo'),"Token No.")!=true)
		return false;
	if(chkDate(document.getElementById('txtBillDate'), "Bill Date")!=true)
		return false;
	if(chkEmpty(document.getElementById("id_txtGrossAmt"),"Gross Amount")!=true)
		return false;
	if(chkEmpty(document.getElementById("id_txtNetAmt"),"Net Amount")!=true)
		return false;
	if(parseFloat(document.getElementById("id_txtNetAmt").value > parseFloat(document.getElementById("id_txtGrossAmt").value)))
	{
		alert("Net amount should be less than equal to Gross amount");
		document.getElementById("id_txtNetAmt").focus();
		return false;
	}
	if(chkEmpty(document.getElementById('id_BillType'),"Bill Type")!=true)
		return false;
	if(alphanumeric(document.getElementById('id_BillType'),"Bill Type")!=true)
		return false;
	if(exemptLen.checked==true)
	{
		if(selCombo(document.getElementById("id_cmbBillCode"),"Bill Code")!=true)				
			return false;
	}			
	if(selCombo(document.getElementById('id_cmbPlan'),"Budget Type")!=true)
		return false;
	if(checkBudType()!=true)
		return false;
	if(chkEmpty(document.getElementById('id_cmbdemand'),"Demand No.")!=true)
		return false;
	if(chkEmpty(document.getElementById('id_cmbMajorHead'),"Major Head")!=true)
		return false;
	if(document.getElementById("id_txtGrantAmt").value!='' && document.getElementById('id_txtGrantAmt').value.length>0)
	{
		if(parseFloat(document.getElementById("id_txtGrossAmt").value) > parseFloat(document.getElementById("id_txtGrantAmt").value))
		{
			var confirmStatus = confirm('Gross Amount exceeds Grant Amount.\nDo you still want to continue?');
			if(confirmStatus==false)
			{
				document.getElementById("id_txtGrossAmt").focus();
				return false;
			}
		}
	}
	return true;
}

/*		JAVASCRIPT FUNCTION TO VALIDATE REMARKS FOR SPECIAL CHARACTERS	*/
function validateRemarks()
{
	var rmrks = document.getElementById('id_txtareaRemarks').value;
	if(rmrks!=null && rmrks.length > 0)
	{
		if(!validRemarks(rmrks))
		{
			alert('Special characters are not allowed in Remarks');
			document.getElementById('id_txtareaRemarks').focus();
			return false;
		}
	}
}

/*  JAVASCRIPT FUNCTION TO VALIDATE 999 DEMAND NO WITH BILLTYPE     */
function check999(strNil)
{
	var demandNo = document.getElementById('id_cmbdemand').value;
	var majorHead = document.getElementById('id_cmbMajorHead').value; 
	var tcCtgry = document.forms[0].cmbTCCtgry.options[document.forms[0].cmbTCCtgry.selectedIndex].text;
				
	if((tcCtgry==strNil) && (demandNo == '999'))
	{		
		alert('Invalid Demand No for Nil Bill');
		document.getElementById('id_cmbdemand').focus();
		return false;				
	}				
	
	if((tcCtgry!=strNil) && (demandNo=='999') && (majorHead<8000))
	{
		alert('Invalid major Head for 999 Demand No.');
		document.getElementById('id_cmbMajorHead').focus;
		return false;
	}
	return true;
}



function getDDOHeadBySchemeNo(contextPath)
{
	req = createXMLHttpRequest();
	hideHighLight(document.getElementById("id_txtSchemeCode"));
	disable();
	showProgressbar();
	
	var baseUrl = contextPath +"/ifms.htm?actionFlag=getDDOHeadBySchemeNo";
	baseUrl += "&schemeNo=" + document.getElementById("id_txtSchemeCode").value;
	
	req.open("post", baseUrl, false); 
	req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	req.onreadystatechange = responseDDOHeadBySchemeNo; 
	req.send(baseUrl);
	if(document.getElementById("id_txtSchemeCode").value == '')
	{
		document.getElementById("id_cmbDemand").focus();
	}
}
function responseDDOHeadBySchemeNo()
{
	if(req.readyState == 4)
	{ 
		if(req.status == 200)
		{
			hide_img();
			enable_div();
			hideProgressbar();
			enable();
			
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
			document.getElementById("id_cmbDemand").value = XMLEntries[0].childNodes[0].text;
			document.getElementById("id_cmbMajorHead").value = XMLEntries[0].childNodes[1].text;
			document.getElementById("id_cmbSubMajorHead").value = XMLEntries[0].childNodes[2].text;
			document.getElementById("id_cmbMinorHead").value = XMLEntries[0].childNodes[3].text;
			document.getElementById("id_cmbSubHead").value = XMLEntries[0].childNodes[4].text;
			document.getElementById("id_cmbDetailHead").value = "";
			if(XMLEntries[0].childNodes[0].text=='')
				document.getElementById("id_cmbDemand").focus();
		}
	}
}


function getSchemeNoByDDOGrantHead(ctrl,num, contextPath)
{
	if(chkHeadLength(ctrl,num)==true)
	{
		req = createXMLHttpRequest();
		
		disable();
		showProgressbar();
		
		var baseUrl = contextPath +"/ifms.htm?actionFlag=getSchemeNoByDDOGrantHead";
		baseUrl += "&demandCode=" + document.getElementById("id_cmbDemand").value;
		baseUrl += "&majorHead=" + document.getElementById("id_cmbMajorHead").value;
		baseUrl += "&subMajorHead=" + document.getElementById("id_cmbSubMajorHead").value;
		baseUrl += "&minorHead=" + document.getElementById("id_cmbMinorHead").value;
		baseUrl += "&subHead=" + document.getElementById("id_cmbSubHead").value;
		
		req.open("post", baseUrl, false); 
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = responseSchemeNoByDDOGrantHead; 
		req.send(baseUrl);
	}
}
			
function responseSchemeNoByDDOGrantHead()
{
	if(req.readyState == 4)
	{ 
		if(req.status == 200)
		{
			hide_img();
			enable_div();
			hideProgressbar();
			enable();
			
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("DDOGrantHead");
			
			if(!(XMLEntries[0].childNodes[0].text == "null" || XMLEntries[0].childNodes[0].text == ""))
			{
				document.getElementById("id_txtSchemeCode").value = XMLEntries[0].childNodes[0].text;
			}
			document.getElementById("id_cmbDetailHead").value = "";
		}
	}		
}

function getTotal(ctrl)
{
	hideHighLight(ctrl);
	var tbl = document.getElementById("TCTable");
 	    var rowSize = tbl.rows.length;
 	    var rowLen = document.forms[0].rowCount.value;
 	    var total=0;
 	    for(ii=1;ii<=(rowSize-1);ii++)
 	    {	  	    	
  	  	    var amt = eval("document.forms[0].txtChallanAmt"+(ii));					    	  	    
  	  	    if(amt.value!='' && amt.value.length>0)
  	  	    {
 	    		total = parseFloat(total) + parseFloat(amt.value);
 	    	}
 	    }
 	    document.getElementById('challanAmtTotal').innerHTML = total;
 	    document.forms[0].challanTotal.value=total;
}


function AddRowToTable()
{
    var tbl = document.getElementById("TCTable");
    var rowSize = tbl.rows.length;     
    var row = tbl.insertRow(rowSize);       
    document.frmCntrInwPhyBills.rowCount.value = rowSize;

    var cellChallanNo = row.insertCell(-1);
    cellChallanNo.align ="center";
    cellChallanNo.innerHTML = '<input  class="TextCss" type="text" attrTitle="Challan No" name="txtChallanNo'+rowSize+'" size="20" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)">';	      

    var cellDate = row.insertCell(-1);
    cellDate.align ="center";
    cellDate.innerHTML = '<input  class="TextCss" type="text" attrTitle="Challan Date" name="txtChallanDate'+rowSize+'" size="20" maxlength="10" onfocus="javascript:showHighLight(this)" onblur="hideHighLight(this)"><img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtChallanDate'+rowSize+'",375,570) >';

    var cellMjrHd = row.insertCell(-1);  
    cellMjrHd.align ="center";
    cellMjrHd.innerHTML = '<input class="texttag mandatorycontrol" type="text" attrTitle="Challan Major Head" name="txtChallanMjrHead'+rowSize+'" maxlength="4" size="20" class="texttag mandatorycontrol" onblur="javascript:chkHeadLength(this,4)" onfocus="javascript:showHighLight(this)">&nbsp;<font color="red">*</font>';
    
    var cellAmnt = row.insertCell(-1);
    cellAmnt.align ="center";
    cellAmnt.innerHTML = '<input type="text" attrTitle="Challan Amount" name="txtChallanAmt'+rowSize+'" maxlength="17" class="texttag mandatorycontrol" onkeypress="javascript:AmountFormat()"  size="20" onblur="getTotal(this)" onfocus="javascript:showHighLight(this)"/>&nbsp;<font color="red">*</font><img src=images/CalendarImages/DeleteIcon.gif onclick="DeleteThis(this,\'TCTable\')" />';
}
function DeleteThis(obj,tblId)
{	   	 	 
      var rowID = showRowCell(obj);            
      var tbl = document.getElementById(tblId);    
      tbl.deleteRow(rowID);  
      getTotal(obj);
}
function showRowCell (element)
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


function showForward(url)
{
	if(savevalid()==true)
	{
		var audName = document.getElementById('id_AuditorName').value;
		if(audName <= 0)
		{
			alert('Auditor Name is required to be select');
			document.getElementById('id_AuditorName').focus();
			return false;
		}
		url=url+"&BillNo=-1";
		url = url+ "&BillCat="+document.forms[0].cmbTCCtgry.value;
		window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
	}
}


function showCtrl(tcBill)
{
	var indx = document.forms[0].cmbTCCtgry.options[document.forms[0].cmbTCCtgry.selectedIndex].text;
	if(indx==tcBill)
	{
		document.getElementById('divTC').style.display='block';					
	}
	else
	{					
		document.getElementById('divTC').style.display='none';
		document.getElementById('id_txtPrevBillNo').value='';
		document.getElementById('id_txtChallanNo').value='';
		document.getElementById('id_txtChallanMjrHead').value='';
	}
}
		
		
function saveNew()
{
 	if(savevalid()==true)
 	{
		document.forms[0].actionFlag.value='insInwPhyBills';
		document.forms[0].actionBtn.value='SaveNew';
		document.forms[0].action ='ifms.htm';
		document.forms[0].submit();
	}
}