function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="" || str == "-1")
	{
		alert(msg +" Cannot be Empty.");
		ctrl.focus();		
		return false;
	}		
	else
		return true;
}
function chckPercentShare(formfield)
{
	var valueOfShare=formfield.value;
	if(valueOfShare<=100)
	{
		return true;
	}
	else
	{
		alert("Percent Share value should not be greater than 100");
		return false;
	}
}
function digitFormat(formfield)
{	
	var val;
	if(window.event.keyCode>47 && window.event.keyCode<58)
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}

function alphaFormat(formfield)
{	
	var val;
	if((window.event.keyCode>64 && window.event.keyCode<91) || (window.event.keyCode>96 && window.event.keyCode<123)) 
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}
function dateFormat(field)
{
	var value=new String(field.value);
	if(value.length==2)
	{
		field.value=value+'/';
	}
	if(value.length==5)
	{
		field.value=value+'/';
	}
}
function checkLength(fieldname,msg)
{
	
	var numberField=fieldname.value;
	var flag=0;
	if(numberField.length == 10 || numberField.length == 0)
	{
		return true;
	}
	else
	{
		if(flag==0)
		{
			
		alert("Length of " +msg+" Should be 10. ");
	
		flag=1;
		}
			
		return false;
	}	
}
/*---------Date Validation---------*/
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function validateDate(dtCtrl){
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
			alert('Please Enter Date in dd/mm/yyyy format ');
			dtCtrl.value='';
			return false;
		}
		return true;
	}
	return false; 
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}
function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function isInteger(s,msg)
{
	var i;	
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
        	//alert(msg +CMN_TxtIsNumber);
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

function isName(field,alrtString)
{
  var str=field.value;
  if (!str || trim(str)=="") { return  true; }
  re1 = /[^a-z\/\[\]\- \." "]/gi;
  if(str.search(re1) < 0)
  {
  	return true;
  }
  else
  {
  	alert(alrtString);
  	field.focus();
  }
  return (str.search(re1) < 0 ? true : false);
}
function checkUncheckAll(theElement,name) 
{
	var theForm = theElement.form;
	
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != name)
		{
			theForm[z].checked = theElement.checked;
  		}
    }
}