 dtCh= "/";
 minYear=1900;
 maxYear=2500;	

function replaceAllChars(str,replaceChar,finalChar)
{
	var str1=str;

	while(str1.search(replaceChar)!=-1)
	{
		str1=str1.replace(replaceChar,finalChar);
	}
	return str1;
}

function winCls()
{
 	try
 	{
 	 	if(window.opener.document) 
 	 	{
 	 		window.close();
 	 	}	
 	}
 	catch(err)
 	{
 		enableAjaxSubmit(true);
 		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
 		document.forms[0].submit();
		//setAjaxSubmit(document.forms[1]);
 	}
}

function digitFormat(formfield)
{	var val;
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

function dateFormat(field)
{
	var value=new String(field.value);
	if(value.length==2)
	{
		field.value=value+'/'
	}
	if(value.length==5)
	{
		field.value=value+'/'
	}
}

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

function isPercentage(formfield,alrStr,maxValue)
{
	var val=formfield.value;
	if(val != '')
	{
		if(Number(val) > Number(maxValue))
		{
			alert(alrStr);
			formfield.value='';
			formfield.focus();
			return false;
		}	
		else
		{
			if(val.charAt(0)=='.')
			{
				val='0'+val;		
				formfield.value=val;
			}
			
			var decimalArray = new Array();
			decimalArray=val.toString().split(".");
			if(decimalArray.length == 2)
			{
			
			if(decimalArray[1].length > 2)
			{
				if(decimalArray[1].charAt(2)>=5)
				{
					val=decimalArray[0]+'.'+decimalArray[1].charAt(0)+(Number(decimalArray[1].charAt(1))+1);
					formfield.value=val;
				}
				else
				{
					val=decimalArray[0]+'.'+decimalArray[1].charAt(0)+decimalArray[1].charAt(1);
					formfield.value=val;
				}
			}
			}
		}
	}
}

function isPincode(formfield,alrStr)
{
	var val=formfield.value;
	if((parseInt(val)== 0))
	{
		alert(alrStr);
		formfield.value='';
		formfield.focus();
		return false;
	}
	if(val.length>0 && val.length<6)
	{
		alert(alrStr);
		formfield.value='';
		formfield.focus();
		return false;
	}	
}

function IsEmptyFun(varStr,alrtStr)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1")
	{
		alert(alrtStr);
		selectRequiredTab(varStr);
		goToField(varStr);
		return false;
	}
}

function goToField(field)
{
	if(document.getElementById(field) != null && document.getElementById(field).disabled == false)
	{
		document.getElementById(field).focus();
	}			
}

function getdt(txtdt)
{
		//alert('In Date Function');
		 var returnArray = new Array();
   		 var array = txtdt.split (" ");

		 var date = array[0];
		// alert ("date "+date);
		 var dateArray =  date.split ("-");
		 var dateStr = dateArray[2]+"/"+dateArray[1] +"/"+dateArray[0]; 
		 //alert(dateStr);
		 return dateStr; 
}

function daysInFebruary (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
		
function DaysArray(n) 
{
	for (var i = 1; i <= n; i++) 
	{
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
 	} 
	return this;
}

function stripCharsInBag(s, bag)
{
	var i;
    var returnString = "";
    
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
	    if (bag.indexOf(c) == -1) returnString += c;
	}
	return returnString;
}

function isInteger(s)
{
	var i;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    return true;
}

		
function isValidDate1(dtStr)
{
	var daysInMonth = DaysArray(12);
	var pos1=dtStr.indexOf(dtCh);
	var pos2=dtStr.indexOf(dtCh,pos1+1);
	var strDay=dtStr.substring(0,pos1);
	var strMonth=dtStr.substring(pos1+1,pos2);
	var strYear=dtStr.substring(pos2+1);
	strYr=strYear;
	if (strDay.charAt(0)=="0" && strDay.length>1){ strDay=strDay.substring(1);}
	if (strMonth.charAt(0)=="0" && strMonth.length>1) {strMonth=strMonth.substring(1);}
	for (var i = 1; i <= 3; i++) 
	{
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);
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
		
function validateDate(lStr)
{
	if(lStr.value.length > 0)
	{
		if(isValidDate1(lStr.value) == false)
		{
			lStr.value=""
			lStr.focus();
			return false
		}
		else
			return true;
	}
	return true;
}

function stringFormat(formfield)
{	
	var val;
	if(window.event.keyCode>47 && window.event.keyCode<58)
	{
		window.event.keyCode=0;
	}
	else
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
}

/*---------Comparing two dates---------*/
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

function resetRadioButton(radio)
{
	for(i=0;i<radio.length;i++)
	{
		if(radio[i].checked)
			radio[i].checked=false;
	}
}

function IsEmptyRadioFun(varRadio,alrtStr)
{
	var radio=document.getElementsByName(varRadio);
	var radioLength=radio.length;
	var flag=false;
	for(var i=0;i<radioLength;i++)
	{
		if(radio[i].checked)
		{
			flag=true;
			break;
		}
	}
	
	if(!flag)
	{
		alert(alrtStr);
		goToField(varRadio);
		return false;
	}
}

function runForm(formnumber){

var totalElements= document.forms[formnumber].elements.length
  var uri='';
  for(i=0;i<totalElements;i++)
  {
   if(document.forms[formnumber].elements[i].type=="checkbox" ||document.forms[formnumber].elements[i].type=="radio")
  	{
  		if(document.forms[formnumber].elements[i].checked==1)
  		{
			   uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+document.forms[formnumber].elements[i].value;
		}
	}
	else if(document.forms[formnumber].elements[i].type=="select-multiple")
  	{
  		var element=document.forms[formnumber].elements[i];
  		for(j=0;j<element.options.length;j++)
		{
			if(element.options[j].selected)
			{
				uri= uri+'&'+element.name+'='+replaceAllChars(element.options[j].value,'&','$$');
			}
		}
  	}
	else
	{
		if(document.forms[formnumber].elements[i].value!=null && document.forms[formnumber].elements[i].value!='' && document.forms[formnumber].elements[i].value!='undefined')
		{
			uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+replaceAllChars(document.forms[formnumber].elements[i].value,'&','$$');
		}
		else
		{
			uri= uri+'&'+document.forms[formnumber].elements[i].name+'='+document.forms[formnumber].elements[i].value;
		}
    }
  }

  return uri;
 }
 
 function clearList(listId)
{
	var listLength = listId.length;
				    		      
	for(var i=listId.length;i>=1;i--)
	{
		listId.remove(i);
    }   
}

//function for validating name 
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

//function for validating Number 
function isNumber(field,alrtString)
{
  var str=field.value;
  if (!str || trim(str)=="") { return  true; }
  re1 = /[^a-z^0-9\/\." "]/gi;
  if(str.search(re1) < 0)
  {
  	return true;
  }
  else
  {
  	alert(alrtString);
  	field.value='';
  	field.focus();
  	return false;
  }
  //return (str.search(re1) < 0 ? true : false);
}

function trim(str) 
{
  return (String(str).replace(/^ +/gi, '').replace(/ +$/gi, ''));
}

//Function for setting focus to AddRow or First Field of Grid
function setGridFocus(fieldname)
{
	if(fieldname.length!=null)
	{
		fieldname[fieldname.length-1].focus();
	}
	else
	{
		fieldname.focus();
	}	
}

//Function for selecting all check box
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
//Function for checking maximum length of Textarea
function setMaxLength(Object, MaxLen)
{
	if(Object.value.length > MaxLen)
	{
//		alert(Object.value.length+' : '+Object.value.substring(0, MaxLen));
		Object.value = Object.value.substring(0, MaxLen);
	}
}

//Function for Textarea enter is pressed then let it work as tab
function textareaFunForEnter()
{
	if (event.keyCode == 13)
	{
		event.keyCode = 9;	
	}
}

//set 2 digit format 
function setServiceFormat(field)
{
	var value=field.value;
	if(value.length<2)
	{
		field.value='0'+value;
	}
}

  function viewData()
{
	//alert('\n\n\n\n\n\nView button start....');
	alert(document.getElementById('pnsnrdetInwardId').value)
	document.getElementById('goButton').style.display='';
	document.getElementById('btnSave1').style.display='none';
	document.getElementById('btnSubmit2').style.display='none';
	document.getElementById('btnSubmit3').style.display='none';
	document.getElementById('btnSubmit4').style.display='none';
	document.getElementById('btnSubmit5').style.display='none';
	//alert('\n\n\n\n\n\nView button over.....');
}

function viewDetails()
{
	//alert('\n\n\n\n\n\nGo button start....');
	var url = "ifms.htm?actionFlag=viewHeaderMenu&inwardId="+document.getElementById('inwardId').value+"&view=readonly&contentUsingAjax=true";
	alert(url);
	document.dppfPension.action= url; //"ifms.htm?actionFlag=viewHeaderMenu&inwardNo="+document.getElementById('inwardId').value;
	document.dppfPension.submit();
	enableAjaxSubmit(true);
	//setAjaxSubmit(document.forms[1]);
	//alert('\n\n\n\n\n\nGo button over....');
}
 
 
function editData()
{
	//alert('\n\n\n\n\n\nEdit button start....');
	document.getElementById('btnGoEdit').style.display='';
//	document.getElementById('btnSave1').style.display='none';
	//alert('\n\n\n\n\n\nEdit button over.....');
}


function editDetails()
{
	//alert('\n\n\n\n\n\nGo button start....');
	var url = "ifms.htm?actionFlag=viewHeaderMenu&inwardId="+document.getElementById('inwardId').value+"&view=edit&contentUsingAjax=true";
	alert(url);
	document.dppfPension.action= url; //"ifms.htm?actionFlag=editHeaderMenu&inwardNo="+document.getElementById('inwardId').value;
	document.dppfPension.submit();
	enableAjaxSubmit(true);
	//setAjaxSubmit(document.forms[1]);
	//alert('\n\n\n\n\n\nGo button over....');
} 

function onFocus(element)
{
	element.style.borderColor='red';
	element.style.borderWidth= '1px'; 
	element.style.borderStyle= 'solid';			
}
function onBlur(element)
{
	element.style.borderColor='';
	element.style.borderWidth= ''; 
	element.style.borderStyle= '' ;
}
/*---------Date Validation---------*/
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
/* Script by Pratik 02-08-23 */
//for setting width of option tag

function shortString(selector, maxLength1) {
	var elements = document.querySelectorAll(selector);
	var tail = '...';
	for (var i = 0; i < elements.length; i++) {
		var text = elements[i].innerText;
		if (true) {
			if (text.length > parseInt(maxLength1)) {
				elements[i].innerText =text.substring(0,maxLength1)+tail; // /'${text.substring(0,parseInt(maxLength1)
																			// -
																			// tail.length).trim()}${tail}';
			}
		}

	}
};
window.addEventListener('DOMContentLoaded', (event) => {
shortString('option', 80);
var list = document.getElementsByTagName("select");
for (var i = 0; i < list.length ; i++) {
if (list[i].getAttribute("size")) {
   /* DO STUFF */
   list[i].setAttribute("size","");
   
}
}
});
/* Script End by Pratik */
