
//*******************************************************************
//Purpose	        : This JS file contains common functions used by IFMS Team
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

//*******************************************************************
//Purpose	        : This function disable typing of non numeric characters in numeric field
//Input				: 
//Output			: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function numberFormat()
{			
	if(!((window.event.keyCode > 47 && window.event.keyCode < 58 ) ))
	{
		window.event.keyCode = 0;
	}			
}

//*******************************************************************
//Purpose	        : This function disable typing of more than single . and non numeric characters in amount field
//Input				: Number
//Output			: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************


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

//*******************************************************************
//Purpose	        : This function addes .00 after entered amuount
//Input				: Number
//Output			: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************


function setValidAmountFormat(lThis)
{
	var lStr = new String(lThis.value);
	lStr = lStr.trim();

	if(lStr.indexOf(".") == 0 && lStr.length == 1)
	{
		lStr = "0" ;
	}
	else if(lStr.indexOf(".") == 0)
	{
		lStr = "0" + lStr;
	}
	
	if(lStr.length <= 15)
	{
		lThis.value = Math.round(lStr) + ".00";
	}

}

//*******************************************************************
//Purpose	        : This function checks and unchecks all the checkboxes in the screen
//Input				: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

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

//*******************************************************************
//Purpose	        : This function gives amount in words for passed no
//Input				: Number to convert in words
//Output			: Amount in words
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function getAmountInWords(number) 
{
	var s = new String("");

	if(isNaN(number))
	{
		return s;
	}
	
	if(number  > 10000000 )
	{
		s = convert(number / 10000000) + " Crore "; 
	
		if (number % 10000000 != 0)
		{
			s += convert(number % 10000000);  
		}
	}
	else
	{
		s = convert(number); 
	}
	
	/*if(s == "")
	{
		s = " Zero"
	}
	*/
	if(s != "")
	{
		s = s + " Only";
	}
		
	return s;
}

//*******************************************************************
//Purpose	        : This function to convert the number in String. Its being called from getAmountInWords
//Input				: Number to convert in words
//Output			: Amount in words
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function convert(number)
{
	var prefix = new String("");
	var soFar = new String("");
	var place = 0;
	
	if (parseInt(number) == 0) 
	{ 
		return "zero"; 
	}
	else if (number == "") 
	{ 
		return ""; 
	}
	else if (number < 0) 
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

//*******************************************************************
//Purpose	        : This function to convert the Number in String to for amt < 1000.
//Input				: Number to convert in words
//Output			: Amount in words
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

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
	
	return numNames[number] + " Hundred" + soFar;	    	
}

//*******************************************************************
//Purpose	        : This is a global variables used to convert the number to String.
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

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
tensNames[4] = " Forty";
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



//*******************************************************************
//Purpose	        : This function checks if passed from date is less than to date or not
//Input				: From Date, To Date
//Output			: true / false
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function compareFromToDates(fdate,tdate)
{
	var Fdate = Date.parse(fdate);
	var Tdate = Date.parse(tdate);

	if(Fdate > Tdate)
	{
		return false;
	}
	else
	{
		return true;
	}
}

//*******************************************************************
//Purpose	        : This function converts text to UpperCase
//Input				: Form Object for which text to be converted to upper
//Output			: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function convertToUpper(src)
{
	src.value = src.value.toUpperCase();
}

//*******************************************************************
//Purpose	        : Common Variables used for Date Functions
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

var dtCh = "/";
var minYear=1900;
var maxYear=2100;

//*******************************************************************
//Purpose	        : This function checks if passed Date is in proper format or not
//Input				: String to check
//Output			: True / False
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function isValidDate(dtStr)
{
	var daysInMonth = DaysInMonth(12);

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
		return false
	}

	if (strMonth.length<1 || month<1 || month>12)
	{
		return false
	}

	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month])
	{
		return false
	}

	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear)
	{
		return false
	}

	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr,dtCh))==false)
	{
		return false
	}
	return true
}

//*******************************************************************
//Purpose	        : This function returns Days in passed month. Its being called from isValidDate.
//Input				: Month No
//Output			: No of Days
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function DaysInMonth(n) 
{
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

//*******************************************************************
//Purpose	        : This function returns Days for February. Its being called from isValidDate.
//Input				: Year
//Output			: No of Days
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function daysInFebruary (year)
{
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

//*******************************************************************
//Purpose	        : This function check for the integer in Dates. Its being called from isValidDate.
//Input				: String
//Output			: True / False
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function isInteger(s)
{
	var i;	
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
	        return false;
	     }
    }
    // All characters are numbers.
    return true;
}

//*******************************************************************
//Purpose	        : This function checks date format and its validity. Its being called from isValidDate.
//Input				: String, Splitted String
//Output			: String
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function stripCharsInBag(s, bag)
{
	var i;
    var returnString = "";

    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++)
	{   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}

//*******************************************************************
//Purpose	        : This function checks in the passed field is empty or not.
//Input				: Field to check, Message to display in case of field is empty
//Output			: true / false
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function isEmpty(Id, Message)
{
	var element = document.getElementById(Id);

	if(element != null)
	{
		var lStr = new String(element.value);
		lStr = lStr.trim();
		element.value = lStr;
		if(lStr.length == 0)
		{
			alert(Message);
			return false;
		}
	}
	return true;
}

//*******************************************************************
//Purpose	        : This function is used to open the window in popup.
//Input				: URL, Window Name, Height, Width
//Output			: 
//Developer Name    : Keyur Shah
//Date              : 01/03/2008
//*******************************************************************

function openPopupWin(URL, WindowName, Height, Width)
{
	var style = "toolbar=no, location=no, directories=no,status=yes, menubar=no"
	style = style + " copyhistory=no, height=" + Height + ",width=" + Width + ",top=0,left=0,scrollbars=yes";

	window.open(URL,WindowName,style);
}