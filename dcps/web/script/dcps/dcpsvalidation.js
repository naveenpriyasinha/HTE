function checkvalue(val) 
{
if (val === "others")
document.getElementById('description').style.display = 'block';
else
document.getElementById('description').style.display = 'none';
}

function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	//alert("str *************"+str);
	if(str=="" || str == "-1" )
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

function compareDatesWithoutAlert(Date1,Date2,flag)
{
	var one_day = 1000*60*60*24; 

	var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    
    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' && Diff == 0){
		return false;
	}

    else if( (flag == '<' && Diff<=0) || (flag == '>' && Diff>=0))
    {
        	return false;
    }
    else {
    	return true;
    }
}
	
function compareDates(fieldName1,fieldName2,alrtStr,flag)
{
	
	var one_day = 1000*60*60*24; 

	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;

	var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    
    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' && Diff == 0){
		alert(alrtStr);
		return false;
	}

    else if( (flag == '<' && Diff<0) || (flag == '>' && Diff>0))
    {
        	alert(alrtStr);
        	return false;
    }
    else {
    	return true;
    }
}

function compareDatesAndEraseFirst(fieldName1,fieldName2,alrtStr,flag)
{
	
	var one_day = 1000*60*60*24; 

	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;

	var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    
    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
    
	var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
	
	if(flag == '=' && Diff == 0){
		alert(alrtStr);
		fieldName1.value = "";
		return false;
	}

    else if( (flag == '<' && Diff<0) || (flag == '>' && Diff>0))
    {
        	alert(alrtStr);
        	fieldName1.value = "";
        	return false;
    }
    else {
    	return true;
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
        	var ctrl;
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
function checkAll(name)
{
	var lArrCheckboxes =  document.getElementsByName(name);
	var length = lArrCheckboxes.length;
	
	for(var lInt=0;lInt<length;lInt++)
	{
		lArrCheckboxes[lInt].checked = true ;
	}
}
function UnCheckAll(name)
{
	var lArrCheckboxes =  document.getElementsByName(name);
	var length = lArrCheckboxes.length;
	
	for(var lInt=0;lInt<length;lInt++)
	{
		lArrCheckboxes[lInt].checked = false ;
	}
}
function chckPin(fieldname){
	var numberField=fieldname.value;
	var flag=0;
	if(numberField.length == 6 || numberField.length == 0)
	{
		return true;
	}
	else
	{
		if(flag==0)
		{
			
		alert("Length of Number Should be 06. ");
		fieldname.focus();
		flag=1;
		}
			
		return false;
	}	
	
}
function emailValidate(field) {
	   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   var address = field.value;
	   if(reg.test(address) == false && address.length != 0) {
	      alert('Invalid Email Address');
	      //field.focus();
	      return false;
	   }
	   	var at="@"
		var dot="."
		var lat=field.indexOf(at)
		var lstr=field.length
		var ldot=field.indexOf(dot)
		if (field.indexOf(at)==-1){
		   alert("Invalid Email Address")
		   return false
		}

		if (field.indexOf(at)==-1 || field.indexOf(at)==0 || field.indexOf(at)==lstr){
		   alert("Invalid Email Address");
		   field.focus();
		   return false;
		}

		if (field.indexOf(dot)==-1 || field.indexOf(dot)==0 || field.indexOf(dot)==lstr){
		    alert("Invalid Email Address");
		    field.focus();
		    return false;
		}

		 if (field.indexOf(at,(lat+1))!=-1){
		    alert("Invalid Email Address");
		    field.focus();
		    return false;
		 }

		 if (field.substring(lat-1,lat)==dot || field.substring(lat+1,lat+2)==dot){
		    alert("Invalid Email Address");
		    field.focus();
		    return false;
		 }

		 if (field.indexOf(dot,(lat+2))==-1){
		    alert("Invalid Email Address");
		    field.focus();
		    return false;
		 }
		
		 if (field.indexOf(" ")!=-1){
		    alert("Invalid Email Address");
		    field.focus();
		    return false;
		 }

		 return true;
}
function validateEmailID(txtEmail,alerttxt)
{
	var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
	if(txtEmail.value.length > 0)
	{
		var returnval=emailfilter.test(txtEmail.value)
		if (returnval==false)
		{
			alert(alerttxt)
			txtEmail.focus();
			return false;
		}
		return returnval;
	}
	return false;
}
function isIntegerOrNot(s,msg)
{
	a = s;
	s = s.value ;
	var i;
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
        	alert(msg);
        	a.focus();
	        return false;
	     }
    }
    // All characters are numbers.
    return true;
}
function ReturnfromChanges()
{
	var designationId = document.getElementById("lStrDesignation").value;
	var typeOfChanges = document.getElementById("lStrChangesType").value;
	var designationIdDraft = document.getElementById("lStrDesignationDraft").value;
	var User =document.getElementById("User").value ; 
	var dcpsHstChangesId = document.getElementById("dcpsHstChangesId").value;
	if(dcpsHstChangesId == "")
	{
		 self.location.href="ifms.htm?actionFlag=loadChangesForm&DesignationId="+designationId+"&Changes="+typeOfChanges+"&User="+User;
	}
	else
	{
		self.location.href="ifms.htm?actionFlag=loadChangesDrafts&DesignationId="+designationIdDraft+"&User="+User;
	}
}

function ReturnLoginPage()
{
	self.location.href = "ifms.htm?actionFlag=validateLogin";
}

function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}

/*function alphanumeric(ctrl,msg)
{
	//alert("alphanumeric:::");
	var numaric = ctrl.value;
	//alert("alphanumeric:::"+numaric);
	//var numaric= document.getElementById("txtPFSeriesDesc").value;
	for(var j=0; j<numaric.length; j++)
		{//alert("alphanumeric:::");
		  var alphaa = numaric.charAt(j);
		  var hh = alphaa.charCodeAt(0);
		  if((hh > 47 && hh<59) || (hh > 64 && hh<91) || (hh > 96 && hh<123))
		  {//alert("alphanumeric:::----");
		  }
		  
		}
    return true;
}
*/

function alphanumeric(ctrl,msg)
{
	var numaric = ctrl.value;
	for(var j=0; j<numaric.length; j++)
		{
		  var alphaa = numaric.charAt(j);
		  var hh = alphaa.charCodeAt(0);
		  if((hh > 47 && hh < 58) || (hh > 64 && hh < 91) || (hh > 96 && hh < 123) || (hh > 32 && hh < 48) || (hh > 57 && hh < 65) || (hh > 90&& hh < 97) || (hh > 122 && hh < 127) )
		  {
		  }
		else	{
                         alert("Your Alpha Numeric Test Failed");
			 return false;
		  }
 		}
		//alert("Your Alpha Numeric Test Passed");
 return true;
}



function validatePFSeries() {
	
var name = document.getElementById("txtPFSeriesDesc").value;
var pattern = /^[A-Za-z0-9 ]{3,20}$/;
if (pattern.test(name)) {
	alert(name +" has alphanumeric value");
	return true;  
	}
else {
	 alert("PF Series Description is not valid.Please input alphanumeric value!");
	 document.getElementById('txtPFSeriesDesc').value = ""; 
  	 document.getElementById('txtPFSeriesDesc').focus();
	return false;        } 
}



function AlphaAnddigitFormat(formfield)
{	
	var val;
	
    if((window.event.keyCode>64 && window.event.keyCode<91) || (window.event.keyCode>96 && window.event.keyCode<123) || window.event.keyCode>47 && window.event.keyCode<58) 
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