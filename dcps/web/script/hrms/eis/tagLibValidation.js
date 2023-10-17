
/**
This validation js file contains the functions for major client side validations.

Created Date : 23 June 2003
Author       : Vaibhavi Shah

**/

var formName;
var formNamePassed;
var checkboxName;
var serverDate = new Date();
var alertMessageForCssCheck;
function startProcess()
{
   showProgressbar();
}
function endProcess()
{
   hideProgressbar();
}
/**
 *	This function will set the tile of the window.
 */
function setTitle(paramTitle)
{
	window.document.title = paramTitle;
}	

/**
 * This function will set the form name of the page.
 */
function setForm(paramFormName)
{
	formName = 'document.' + paramFormName + ".";
	formNamePassed = paramFormName;
}

/**
 * This function will set the alert message for Css Check Function.
 */
function setAlertMessageForCssCheck(alertMessage)
{
	alertMessageForCssCheck = alertMessage;
}

/**
 * This function will set the combo box name for group toggle.
 */
function setParameters(paramCheckboxName)
{
	checkboxName = paramCheckboxName;
}

/**
 * This function will set the maxlength for text area.
 */
function imposeMaxLength(Object, MaxLen)
{
  return (Object.value.length < MaxLen);
}

/**
 * This function will set the maxlength for text area.
 */
function setMaxLengthTextArea(Object, MaxLen)
{
  var textAreaLength = Object.value.length;
  if(textAreaLength > MaxLen)
  {
  	Object.value = Object.value.substring(0,MaxLen);  	
  }
}

/**
 * This function will set the server date in this file.
 */
 function setServerDate(paramServerDate)
 {
	serverDate = new Date(paramServerDate);
 }

/**
 * This function trims string from the left
 */
function lefttrim(value)
{
	while( value.length != 0 )
	{
		mychar=value.substring(0,1);
		if( mychar == "\u0020" )
		{
			value=value.substr(1);
		}
		else
			break;
		
	}
	return value;
}

/**
 * This function trims string from the right
 */
function righttrim(value)
{
	while( value.length != 0 )
	{
		mychar=value.substring(value.length-1);
		if( mychar == "\u0020" )
		{
			value=value.substr(0,value.length-1);
		}
		else
			break;
		
	}
	return value;
}


function isNull1(value)
{	

	myvalue1=righttrim(value);
	myvalue2=lefttrim(myvalue1);
	return (myvalue2.length == 0)
}

function checkNumber(key)
{
		var num;
		num = window.event.keyCode;
		if ((eval(num)<46||eval(num)==47)||eval(num)>58)
			return false;
		else
		 	return true;
}
function checkRange(minvalue,maxvalue,name,errMessage)
{
	var numberVal=eval(formName + name).value;
	if(!isNull1(numberVal))
	{
		if(!isNull1(minvalue) && minvalue.length > 0 && !isNull1(maxvalue) && maxvalue.length > 0 )
		{
			if(numberVal<parseInt(minvalue) || numberVal>parseInt(maxvalue))
			{
				alert(errMessage);
				setFocusSelection(name);
				return false;
			}
		}
		else if(!isNull1(minvalue) && minvalue.length > 0)
		{
			if(numberVal<parseInt(minvalue))
			{
				alert(errMessage);
				setFocusSelection(name);
				return false;
			}
		}
		else if(!isNull1(maxvalue) && maxvalue.length > 0)
		{
			if(numberVal>parseInt(maxvalue))
			{
				alert(errMessage);
				selectRequiredTab(name);
				setFocusSelection(name);
				return false;
			}
		}
	}
}
function checkDateNumber(key)
{
		var num;
		num = window.event.keyCode;
		if (eval(num)<47 || eval(num)>57)
				return false;				
}
function checkDate(name,errMessage, errCaption, minvalue, maxvalue, dtRangeErrMsg)
{
	if(minvalue == undefined) {
		minvalue = '';
	}
	if(maxvalue == undefined) {
		maxvalue = '';
	}
	if(dtRangeErrMsg == undefined){
		dtRangeErrMsg = '';
	}
	
	var val=eval(formName + name).value;
	if(val.length>5 && val.lastIndexOf("/")<0)
	{	
		var firstString = val.substring(0,2);
		var secondString = val.substring(2,4);
		var thirdString = val.substring(4,8);
		if(thirdString.length==2)
		{
			thirdString = '20'+thirdString;
		}
		
		if(secondString.lastIndexOf("/")>=0)
		{
			secondString = val.substring(3,5);
		}
		else
		{
			secondString = '/' + secondString;
		}
		if(thirdString.lastIndexOf("/")>=0)
		{
			thirdString = val.substring(5,val.length);
		}
		else
		{
			thirdString = '/' + thirdString;
		}
		
		var fullString = firstString + secondString + thirdString;
	
		eval(formName + name).value = fullString;		
	}
	var dateValue=eval(formName + name).value;
	dateValue = trimSentense(dateValue);
	if(!isNull1(dateValue) && dateValue.length>0)
	{
		var lastIndex = dateValue.lastIndexOf("/");
		var dateValueTest = dateValue.substring(lastIndex+1,dateValue.length);
		if(dateValueTest.length==3)
		{
			alert(errCaption+ ' ' +errMessage);
			selectRequiredTab(name);
			setFocusSelection(name);
			return false;			
		}
		dt=/^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((2[0-9][0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/;
		if (!dt.test(dateValue))
		{
			alert(errCaption+ ' ' +errMessage);
			selectRequiredTab(name);
			setFocusSelection(name);
			return false;
		}
		else
		{
			if(!isNull1(minvalue) && minvalue.length > 2 && !isNull1(maxvalue) && maxvalue.length > 2 )
			{
				if(compareDate(dateValue,minvalue) > 0 || compareDate(dateValue,maxvalue) < 0)
				{
					alert(dtRangeErrMsg);
					selectRequiredTab(name);
					setFocusSelection(name);
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(!isNull1(minvalue) && minvalue.length > 2)
			{
				if(compareDate(dateValue,minvalue) > 0)
				{
					alert(dtRangeErrMsg);
					selectRequiredTab(name);
					setFocusSelection(name);
					return false;
				}
				else
				{
					return true;
				}
			}
			else if(!isNull1(maxvalue) && maxvalue.length > 2)
			{
				if(compareDate(dateValue,maxvalue) < 0)
				{
					alert(dtRangeErrMsg);
					selectRequiredTab(name);
					setFocusSelection(name);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{		
				return true;
			}
		}
	}			
}
function checkTime(name)
{
	var val=eval(formName + name).value
	if(!(val.lastIndexOf(":")>1)&&(val.length>2))
	{	
		var firstString = val.substring(0,2);
		var lastString = val.substring(2,val.length);
		if(lastString.length>=2)
		{
			lastString = lastString.substring(0,2);
		}
		else
		{
			lastString = lastString + '0';
		}
		var fullString = firstString + ':' + lastString;
		eval(formName + name).value = fullString;
	}
	else if(!(val.lastIndexOf(":")>1)&&(val.length==2))
	{
		val = val + ':' + '00';
		eval(formName + name).value = val;
	}

}


function validate()
{
	var args=validate.arguments;

    // Space Not allowed   
	re =/^[a-zA-Z][\w-_.]*$/; //'RNotSpace'
	//Space Allowed
	sre=/^[a-zA-Z][\w-_./ ][^\\]*$/;
	//sre = /[a-zA-Z0-9\u00A0]+/;//-'RSpace'
	cre=/^[, a-zA-Z\d]+$/;//'RComma'
	//Date in format mm/dd/yyyy    /^(?=\d)(?:(?:(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})|(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))|(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2}))($|\ (?=\d)))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\ [AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/; 
	//dt=/^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d?$/;//'RDate'
	dt=/^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((2[0-9][0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$/;//'RDate'
	//Time in format hh:mm with optional am or pm /^([01]?[0-9]|[2][0-3])(:[0-5][0-9])?$/;
	//tm=/^([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])?$/;//for hh:mm:ss
	//tm=/^(([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9]))|([01]?[0-9]|[2][0-3])(:[0-5][0-9])?$/;//for hh:mm:ss
	tm=/^([0][0-9]|[2][0-3]|[1][0-9]):[0-5]\d(:[0-5]\d(\.\d{1,3})?)?$/;
	
	//'RTime'
	//checkname=/^[ a-zA-Z\d\\/_]*$/;//'RComma'
	checkname=/^[ a-zA-Z\d\\/_]|[^\x00-\x7F]*$/;//'RComma'

	//User Name 3 to 15 characters,digits and underscore
	userre = /^[a-zA-Z0-9_]{3,15}$/;//'RUser'
	//Password 3 to 15 characters,digits and underscore
	passwordre = /^[a-zA-Z0-9_]{3,15}$/;//for username - 'RPassword'
	//File allows space,digits,characters,:,./ -filepath	
	filere= /^[a-zA-Z0-9\.:_\x20\-\\]+$/;//-'RFile'
	//Allows integer 0 or greater than 0
	nre= /^\d+$/;// 'RNum'
	//Allows integer greater than 0
	nzre= /^0*[1-9]\d*$/;// 'RNZNum'
	//Allows float 
	fre=/^((\d+(\.\d*)?)|((\d*\.)?\d+))$/;	//'RFloat'
	//Currency 13,2 which allows 0.0 also
	currencyre=/^\d{1,10}((\.\d{1,2}$)|$)/; //'RCurrency'
	//Currency greater than 0.0
	nzcurrencyre=/^0*[1-9]\d{0,12}$|^0*[1-9]\d{0,12}\.\d?\d?|^0*\.\d?[1-9]$|^0*\.[1-9]\d?$/;//'RNZCurrency'	
 	//Percentage (5,2)which allows 0.0(till 100 only)
	percentre=/^\d{1,3}((\.\d{1,2}$)|$)/; //'RPercentage'
	//Percentage greater than 0.0
	nzpercentre=/^0*[1-9]\d?$|^0*[1-9]\d?\.\d?\d?$|^0*100\.0?0?$|^0*100$|^0*\.\d?[1-9]$|^0*\.[1-9]\d?$/;//RNZPercentage
	//Allows negative and float
	nfre = /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/; // allows negative and float-
	//Checks Email
	emailre = /^\w+(\-\w+)*(\.\w+(\-\w+)*)*@\w+(\-\w+)*(\.\w+(\-\w+)*)+$/;//'REmail'
	//Allows days 1 to 365
	nzdayre=/^0*[1-2]\d?\d?$|^0*[1-9]\d?$|^0*[1-9]$|^0*3[1-5]\d?$|^0*36[1-5]$/;//'RNZDay'
	//Allows days 0 to 365
	dayre=/^0*[1-2]?\d?\d?$|^0*3[1-5]\d?$|^0*36[1-5]$|^0*$/;//'RDay'
	//Phone Number
	phonere = /^[1-9]\d{2}\-\s?\d{3}\-\d{4}$/;//'RPhone'
	ssnExp = /^(\d{3})-?\d{2}-?\d{4}$/;
	pincodere = /^[1-9]\d{5}$/;
 	MinVal=0; //Minimum value to be accepted for RMinMax...change according to requirements
	MaxVal=3;
	args[3] = '"' + args[3] + '"';

	//for security check start	
	if(!cssCheck(args[2], args[3]))
	{
		return false;
	}
	//for security check end

		if (args[0]!='RDropMenu' && args[0]!='RCheck'&& args[0]!='RRadio'&& args[0]!='RList'&& args[0]!='RPhone')
		{		
				FldExpVal=eval(formName + args[2]).value;
				if(!(eval(formName + args[2]).disabled))
				{
					//FldExpFoc=eval(formName + args[2]).focus();
					//FldExpSel=eval(formName + args[2]).select();
					FldExpLen=eval(formName + args[2]).value.length;
				}
		}
		if(args[0]=='RAge')
		{
			if(!isNull1(FldExpVal) && (FldExpVal<=1 || FldExpVal>=100))
			{				
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}

		if(args[0]=='RHeight')
		{
			if(!isNull1(FldExpVal) && (FldExpVal<=1 || FldExpVal>=240))
			{				
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
	    if(args[0]=='RWeight')
		{
			if(!isNull1(FldExpVal) && (FldExpVal<=1 || FldExpVal>=300))
			{				
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		if(args[0]=='RNull')
		{
			if(isNull1(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RComma')
		{
			if (!cre.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RDate')
		{
			if(!isNull1(FldExpVal))
			{
				if (!dt.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RTime')
		{
			if(!isNull1(FldExpVal))
			{		
				if (!tm.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RUser')
		{
			if (!userre.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RPassword')
		{
			if (!passwordre.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RNotSpace')
		{
			if (!re.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RCheckName')
		{
			if (!checkname.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}

		else if (args[0]=='RFile')
		{
			if(!isNull1(FldExpVal))
			{
				if (!filere.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}		
		else if (args[0]=='RSpace')
		{
			if (!sre.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RDropMenu')
		{
			FldExpValMenu=eval(formName + args[2]).selectedIndex;
			if (FldExpValMenu==0)
			{
				selectRequiredTab(args[2]);
				alert(args[3] +' '+args[1]);
			    eval(formName + args[2]).focus();
//				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RNum')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZNum')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nzre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RFloat')
		{
			if(!isNull1(FldExpVal))
			{
				if (!fre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else 
				return true;
		}
		else if (args[0]=='RCurrency')
		{
			if(!isNull1(FldExpVal))
			{
				if(!currencyre.test(FldExpVal))
				{
					alert(args[3]+' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZCurrency')
		{
			if(!isNull1(FldExpVal))
			{
				if(!nzcurrencyre.test(FldExpVal))
				{
					alert(args[3]+' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RPercentage')
		{
			if(!isNull1(FldExpVal))
			{
				if(!percentre.test(FldExpVal))
				{
					alert(args[3]+' '+args[1]);
					selectRequiredTab(args[2]);
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZPercentage')
		{
			if(!isNull1(FldExpVal))
			{
				if(!nzpercentre.test(FldExpVal))
				{
					alert(args[3]+' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='REmail')
		{
			if(!isNull1(FldExpVal))
			{
				if (!emailre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZDay')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nzdayre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RDay')
		{
			if(!isNull1(FldExpVal))
			{
				if (!dayre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RPincode')
		{
			if (!pincodere.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);					
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RPhone')
		{
			FldExpVal=eval(formName + args[2]).value;
			if(!phonere.test(FldExpVal))
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				setFocusSelection(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RSSN')
		{
			if (isNull1(FldExpVal))
			{
				alert(args[i+1] + requiredCaption);
				FldExpFoc;
				return false;
			}
			var matchArr = FldExpVal.match(/^(\d{3})-?\d{2}-?\d{4}$/);
			var numDashes = FldExpVal.split('-').length - 1;
			if (matchArr == null || numDashes == 1) 
			{
				alert("Invalid " + args[i+1] + ". Must be 9 digits.");
				FldExpFoc;
				return false;
			}
			else if (parseInt(matchArr[1],10)==0) 
			{
				alert("Invalid " + args[i+1] + " : SSN can\'t start with 000.");
				FldExpFoc;
				return false;
			}
		}
		else if (args[0]=='RMinMax')
		{
			if (isNull1(FldExpVal))
			{
				alert(args[i+1] + requiredCaption);
				FldExpFoc;
				return false;
			}
			if((FldExpLen<MinVal)||(FldExpLen>MaxVal))
			{
				alert(args[i+1]+' should be of '+ MinVal +' to '+ MaxVal +' characters.');
				FldExpFoc;
				return false;
			}
		}		
		else if(args[0]=='RCheck')
		{
			checkLen=eval(formName + args[2]).length;
			isChecked=false;
			for(var i=0;i<checkLen;i++)
			{
				if(eval((formName+args[2]+"["+i+"].checked")))
				{
					isChecked=true;
					break;
				}
			}
			if(!isChecked)
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(args[0]=='RRadio')
		{
			radioLen=eval(formName + args[2]).length;
			isChecked=false;
			for(var i=0;i<radioLen;i++)
			{
				if(eval((formName+args[2]+"["+i+"].checked")))
				{
					isChecked=true;
				}
			}
			if(!isChecked)
			{
				alert(args[3] +' '+args[1]);
				selectRequiredTab(args[2]);				
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(args[0]=='RList')
		{
			FldExpValMenu=eval(formName + args[2]).selectedIndex;
			if(FldExpValMenu==-1)
			{
				alert(args[3] +' '+args[1]);

				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RSysParamNum')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				alert(args[3]+' '+'is required');
				setFocusSelection(args[2]);
				return false;
			}
		}
		else if (args[0]=='RSysParamCurrency')
		{
			if(!isNull1(FldExpVal))
			{
				if(!currencyre.test(FldExpVal))
				{
					alert(args[3]+' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				alert(args[3]+' '+'is required');
				setFocusSelection(args[2]);
				return false;
			}
		}
		else if (args[0]=='RSysParamEmail')
		{
			
			if(!isNull1(FldExpVal))
			{
			
				if (!emailre.test(FldExpVal))
				{
					alert(args[3] +' '+args[1]);
					selectRequiredTab(args[2]);					
					setFocusSelection(args[2]);
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				alert(args[3]+' '+'is required');
				setFocusSelection(args[2]);
				return false;
			}
		}
}


function setFocusSelection(obj)
{
	eval(formName + obj).select();	
	eval(formName + obj).focus();
}
function performValidate()
{
	
	var args=performValidate.arguments;
	// Space Not allowed   
	re =/^[a-zA-Z][\w-_.]*$/; //'RNotSpace'
	cre =/^[, a-zA-Z\d]+$/;//'RComma'
	//Space Allowed
	sre=/^[a-zA-Z][\w-_./ ]*$/;
	//User Name 3 to 15 characters,digits and underscore
	userre = /^[a-zA-Z0-9_]{3,15}$/;//'RUser'
	//Password 3 to 15 characters,digits and underscore
	passwordre = /^[a-zA-Z0-9_]{3,15}$/;//for username - 'RPassword'
	//File allows space,digits,characters,:,./ -filepath	
	filere= /^[a-zA-Z0-9\.:_\x20\-\\]+$/;//-'RFile'
	//Allows integer 0 or greater than 0
	nre= /^\d+$/;// 'RNum'
	//Allows integer greater than 0
	nzre= /^0*[1-9]\d*$/;// 'RNZNum'
	//Allows float 
	fre=/^((\d+(\.\d*)?)|((\d*\.)?\d+))$/;	//'RFloat'
	//Currency 13,2 which allows 0.0 also
	currencyre=/^\d{1,10}((\.\d{1,2}$)|$)/; //'RCurrency'
	//Currency greater than 0.0
	nzcurrencyre=/^0*[1-9]\d{0,12}$|^0*[1-9]\d{0,12}\.\d?\d?|^0*\.\d?[1-9]$|^0*\.[1-9]\d?$/;//'RNZCurrency'	
 	//Percentage (5,2)which allows 0.0(till 100 only)
	percentre=/^\d{1,3}((\.\d{1,2}$)|$)/; //'RPercentage'
	//Percentage greater than 0.0
	nzpercentre=/^0*[1-9]\d?$|^0*[1-9]\d?\.\d?\d?$|^0*100\.0?0?$|^0*100$|^0*\.\d?[1-9]$|^0*\.[1-9]\d?$/;//RNZPercentage
	//Allows negative and float
	nfre = /(^-?\d\d*\.\d*$)|(^-?\d\d*$)|(^-?\.\d\d*$)/; // allows negative and float-
	//Checks Email
	emailre = /^\w+(\-\w+)*(\.\w+(\-\w+)*)*@\w+(\-\w+)*(\.\w+(\-\w+)*)+$/;//'REmail'
	//Allows days 1 to 365
	nzdayre=/^0*[1-2]?\d?\d?$|^0*3[1-5]\d?$|^0*36[1-5]$/;//'RNZDay'
	//Allows days 0 to 365
	dayre=/^0*[1-2]?\d?\d?$|^0*3[1-5]\d?$|^0*36[1-5]$|^0*$/;//'RDay'
	//Phone Number
	phonere = /^[1-9]\d{2}\-\s?\d{3}\-\d{4}$/;//'RPhone'
	ssnExp = /^(\d{3})-?\d{2}-?\d{4}$/;
	pincodere = /^[1-9]\d{5}$/;
 	MinVal=0; //Minimum value to be accepted for RMinMax...change according to requirements
	MaxVal=3;

		if (args[0]!='RDropMenu' && args[0]!='RCheck'&& args[0]!='RRadio'&& args[0]!='RList'&& args[0]!='RPhone')
		{		
				FldExpVal=eval(formName + args[2]).value;
				FldExpFoc=eval(formName + args[2]).focus();
				FldExpSel=eval(formName + args[2]).select();
				FldExpLen=eval(formName + args[2]).value.length;
		}
		if(args[0]=='RNull')
		{
			if(isNull1(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}else if (args[0]=='RComma')
		{
			if (!cre.test(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RUser')
		{
			if (!userre.test(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RPassword')
		{
			if (!passwordre.test(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RNotSpace')
		{
			if (!re.test(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RFile')
		{
			if(!isNull1(FldExpVal))
			{
				if (!filere.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}		
		else if (args[0]=='RSpace')
		{
			if (!sre.test(FldExpVal))
			{
				
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RDropMenu')
		{
			FldExpValMenu=eval(formName + args[2]).selectedIndex;
			if ( FldExpValMenu==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		else if (args[0]=='RNum')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZNum')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nzre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RFloat')
		{
			if(!isNull1(FldExpVal))
			{
				if (!fre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else 
				return true;
		}
		else if (args[0]=='RCurrency')
		{
			if(!isNull1(FldExpVal))
			{
				if(!currencyre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZCurrency')
		{
			if(!isNull1(FldExpVal))
			{
				if(!nzcurrencyre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RPercentage')
		{
			if(!isNull1(FldExpVal))
			{
				if(!percentre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZPercentage')
		{
			if(!isNull1(FldExpVal))
			{
				if(!nzpercentre.test(FldExpVal))
				{
					return true;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='REmail')
		{
			if(!isNull1(FldExpVal))
			{
				if (!emailre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RNZDay')
		{
			if(!isNull1(FldExpVal))
			{
				if (!nzdayre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RDay')
		{
			if(!isNull1(FldExpVal))
			{
				if (!dayre.test(FldExpVal))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
				return true;
		}
		else if (args[0]=='RPincode')
		{
			if (!pincodere.test(FldExpVal))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if (args[0]=='RSSN')
		{
			if (isNull1(FldExpVal))
			{
				alert(args[i+1] + requiredCaption);
				FldExpFoc;
				return false;
			}
			var matchArr = FldExpVal.match(/^(\d{3})-?\d{2}-?\d{4}$/);
			var numDashes = FldExpVal.split('-').length - 1;
			if (matchArr == null || numDashes == 1) 
			{
				alert("Invalid " + args[i+1] + ". Must be 9 digits.");
				FldExpFoc;
				return false;
			}
			else if (parseInt(matchArr[1],10)==0) 
			{
				alert("Invalid " + args[i+1] + " : SSN can\'t start with 000.");
				FldExpFoc;
				return false;
			}
		}
		else if (args[0]=='RMinMax')
		{
			if (isNull1(FldExpVal))
			{
				alert(args[i+1] + requiredCaption);
				FldExpFoc;
				return false;
			}
			if((FldExpLen<MinVal)||(FldExpLen>MaxVal))
			{
				alert(args[i+1]+' should be of '+ MinVal +' to '+ MaxVal +' characters.');
				FldExpFoc;
				return false;
			}
		}		
		else if(args[0]=='RCheck')
		{
			isChecked=eval(formName + args[2]).checked;
			if(!isChecked)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(args[0]=='RRadio')
		{
			radioLen=eval(formName + args[2]).length;
			isChecked=false;
			for(var i=0;i<radioLen;i++)
			{
				if(eval((formName+args[2]+"["+i+"].checked")))
				{
					isChecked=true;
				}
			}
			if(!isChecked)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else if(args[0]=='RList')
		{
			FldExpValMenu=eval(formName + args[2]).selectedIndex;
			if(FldExpValMenu==-1)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
}


	/*
		This function check the length of the field especiallyrequired for textarea fields
	*/
	function isValidLength(fieldId, allowedLength, fieldName,alertMsg)
	{
		/*var lenCaption = " length should not exceed "; */

		var obj = eval(formName + fieldId);
		var len = obj.value.length;
		if(len>allowedLength)
		{
			obj.select();
			obj.focus();
	/*		alert(fieldName + lenCaption + allowedLength + "."); */
			alert(fieldName + alertMsg + allowedLength + ".");
			return false;	
		}
		else
			return true;
	}

//THIS FUNCTION WILL TRIM THE INPUT TEXT
function trimText(strtext)
{	
	if(strtext == null)
	 return null;
	if(strtext.length == 0)
		return strtext;				
		
	//GET RID OF LEADING SPACES
	while (strtext.substring(0,1) == ' ') 
		strtext= strtext.substring(1, strtext.length);
				
	//GET RID OF TRAILING SPACES 
	while (strtext.substring(strtext.length-1,strtext.length) == ' ')
		strtext = strtext.substring(0, strtext.length-1);	

	return strtext;
}

function dateRange(fromDate,toDate)
{
    if(!isNull1(fromDate))
    {
    	if(!isNull1(toDate))
    	{
    		if(compareDate(fromDate,toDate) < 0)
    		{
    			return false;
    		}
    	}
    }
    
	return true;
}

/**
	Compares two date and return the difference if 
	difference is positive means fromDate is less the toDate
	difference is negative means fromDate is greater the toDate
	difference is 0 means fromDate toDate are equal
**/
function compareDate(paramFirstDate,paramSecondDate)
{
//	var fromDate = new Date(paramFirstDate);
//	var hour = fromDate.getHours();
	
	
//	var toDate = new Date(paramSecondDate);

	var DD = paramFirstDate.substr(0,2);
	var MM = paramFirstDate.substr(3,2);
	var YY = paramFirstDate.substr(6,4);
	
	var dd = paramSecondDate.substr(0,2);
	var mm = paramSecondDate.substr(3,2);
	var yy = paramSecondDate.substr(6,4);
	
	var diff = Date.UTC(yy,mm,dd) - Date.UTC(YY,MM,DD);
//	var diff = toDate.getTime() - fromDate.getTime();

	return (diff);
}
function datediff(paramFirstDate,paramSecondDate)
{
	var DD = paramFirstDate.substr(0,2);
	var MM = paramFirstDate.substr(3,2);
	var YY = paramFirstDate.substr(6,4);
	
	var dd = paramSecondDate.substr(0,2);
	var mm = paramSecondDate.substr(3,2);
	var yy = paramSecondDate.substr(6,4);
	
	var diff = Date.UTC(yy,mm,dd) - Date.UTC(YY,MM,DD);
	//var diff = toDate.getTime() - fromDate.getTime();
	var day = (diff)/(24*60*60*1000);
	return day;	

}
/**
	Compares two date with from date time set as 00:00:00 a
	and to date time set as 23:59:59 .Return the difference if 
	difference is positive means fromDate is less the toDate
	difference is negative means fromDate is greater the toDate
	difference is 0 means fromDate toDate are equal
**/
function compareDateTime(paramFirstDate,paramSecondDate)
{
	var fromDate = new Date(paramFirstDate);
	var toDate = new Date(paramSecondDate);
	toDate.setHours(23);
	toDate.setMinutes(59);
	toDate.setSeconds(59);
	var diff = toDate.getTime() - fromDate.getTime();
	return (diff);
}
/**
Compares given two dates and the given format which can be MM-DD-YYYY and 
DD-MM-YYYY
returns 0 if both equal
negative value if first date < second date
positive valie if first date > second date
**/


function dateDiff(paramFirstDate, paramSecondDate, dateFormat)
{  
	var d1;
	var d2;
	var mmIndex;
	var ddIndex;
	var yyIndex;
	var separator;
	var inputEndDateArray;
	var inputStartDateArray;
	var inputEndDateStr;
	var inputStartDateStr;

	if(dateFormat !="" || trimText(dateFormat).length > 0)
	{
		mmIndex = (dateFormat.toUpperCase()).indexOf("MM");
		ddIndex = (dateFormat.toUpperCase()).indexOf("DD");
		yyIndex = (dateFormat.toUpperCase()).indexOf("YYYY");
		
		if(dateFormat.indexOf("/")!=-1)
			separator = "/";
		else
			separator = "-";
	}

	if(paramFirstDate !="" || trimText(paramFirstDate).length > 0)
	{
		inputEndDateArray = paramFirstDate.split(separator);	
		if(mmIndex==0 && ddIndex==3 && yyIndex==6)
		{	
			inputEndDateStr = inputEndDateArray[0] + separator + inputEndDateArray[1] + separator + inputEndDateArray[2];
		}
		else if(mmIndex==3 && ddIndex==0 && yyIndex==6)
		{
			inputEndDateStr = inputEndDateArray[1] + separator + inputEndDateArray[0] + separator + inputEndDateArray[2];
		}
		d1 = new Date(inputEndDateStr);
	}
	else
	{
		d1 = serverDate;
	}
	
	if(paramSecondDate != "" || trimText(paramSecondDate).length > 0)
	{
		inputStartDateArray = paramSecondDate.split(separator);
		if(mmIndex==0 && ddIndex==3 && yyIndex==6)
		{	
			inputStartDateStr = inputStartDateArray[0] + separator + inputStartDateArray[1] + separator + inputStartDateArray[2];
		}
		else if(mmIndex==3 && ddIndex==0 && yyIndex==6)
		{
			inputStartDateStr = inputStartDateArray[1] + separator + inputStartDateArray[0] + separator + inputStartDateArray[2];
		}
		d2 = new Date(inputStartDateStr);
	}
	else
	{
		d2 = serverDate;
	}
	//days = Math.floor(timediff / (1000 * 60 * 60 * 24)); 
	//alert("First Date : " + d1.getTime() + " ###### Second Date : " + d2.getTime());
	
	if(d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate())	
		return 0;
	else
		return (d1.getTime() - d2.getTime());
} 

/**
Compares given two dates and time and the given format which can be MM-DD-YYYY and 
DD-MM-YYYY
returns 0 if both equal
negative value if first date < second date
positive valie if first date > second date
**/

//TimeFormat is assumed to be HH:MM (24 hours format)
function dateTimeDiff(paramFirstDate, paramFirstTime, paramSecondDate, paramSecondTime, dateFormat)
{  
	var d1;
	var d2;
	var mmIndex;
	var ddIndex;
	var yyIndex;
	var separator;
	var inputFirstDateArray;
	var inputSecondDateArray;
	var inputFirstDateStr;
	var inputSecondDateStr;
	var inputFirstTimeArray;
	var inputSecondTimeArray;

	if(dateFormat != "" || trimText(dateFormat).length > 0)
	{
		mmIndex = (dateFormat.toUpperCase()).indexOf("MM");
		ddIndex = (dateFormat.toUpperCase()).indexOf("DD");
		yyIndex = (dateFormat.toUpperCase()).indexOf("YYYY");
		
		if(dateFormat.indexOf("/")!=-1)
			separator = "/";
		else
			separator = "-";
	}

	if(paramFirstDate != "" || trimText(paramFirstDate).length > 0)
	{
		inputFirstDateArray = paramFirstDate.split(separator);
		if(mmIndex==0 && ddIndex==3 && yyIndex==6)
		{	
			//alert("MM : " + inputFirstDateArray[0] + " DD : " + inputFirstDateArray[1] + " YYYY : " + inputFirstDateArray[2]);
			inputFirstDateStr = inputFirstDateArray[0] + separator + inputFirstDateArray[1] + separator + inputFirstDateArray[2];
		}
		else if(mmIndex==3 && ddIndex==0 && yyIndex==6)
		{
			//alert("MM : " + inputFirstDateArray[0] + " DD : " + inputFirstDateArray[1] + " YYYY : " + inputFirstDateArray[2]);
			inputFirstDateStr = inputFirstDateArray[1] + separator + inputFirstDateArray[0] + separator + inputFirstDateArray[2];
		}
		d1 = new Date(inputFirstDateStr);
		//alert("D1 Time : " + d1.getTime());
		if(paramFirstTime.indexOf(":") != -1)
		{				
			var hours = parseInt(paramFirstTime.substring(0, paramFirstTime.indexOf(":")));
			var minutes = parseInt(paramFirstTime.substring(paramFirstTime.indexOf(":") + 1));
			d1.setHours(hours);
			d1.setMinutes(minutes);
		}
	}
	else
	{
		d1 = serverDate;
		if((paramFirstTime != "" || trimText(paramFirstTime).length > 0) && paramFirstTime.indexOf(":") != -1)
		{
			var hours = parseInt(paramFirstTime.substring(0, paramFirstTime.indexOf(":")));
			var minutes = parseInt(paramFirstTime.substring(paramFirstTime.indexOf(":") + 1));
			d1.setHours(hours);
			d1.setMinutes(minutes);
		}
	}
	if(paramSecondDate != "" || trimText(paramSecondDate).length > 0)
	{
		inputSecondDateArray = paramSecondDate.split(separator);
		if(mmIndex==0 && ddIndex==3 && yyIndex==6)
		{	
			inputSecondDateStr = inputSecondDateArray[0] + separator + inputSecondDateArray[1] + separator + inputSecondDateArray[2];
		}
		else if(mmIndex==3 && ddIndex==0 && yyIndex==6)
		{
			inputSecondDateStr = inputSecondDateArray[1] + separator + inputSecondDateArray[0] + separator + inputSecondDateArray[2];
		}
		d2 = new Date(inputSecondDateStr);

		if(paramSecondTime.indexOf(":") != -1)
		{
			var hours = parseInt(paramSecondTime.substring(0, paramSecondTime.indexOf(":")));
			var minutes = parseInt(paramSecondTime.substring(paramSecondTime.indexOf(":") + 1));
			d2.setHours(hours);
			d2.setMinutes(minutes);
		}
	}
	else
	{
		d2 = serverDate;
		if((paramSecondTime != "" || trimText(paramSecondTime).length > 0) && paramSecondTime.indexOf(":") != -1)
		{
			var hours = parseInt(paramSecondTime.substring(0, paramSecondTime.indexOf(":")));
			var minutes = parseInt(paramSecondTime.substring(paramSecondTime.indexOf(":") + 1));
			d2.setHours(hours);
			d2.setMinutes(minutes);
		}
	}
	return d1.getTime() - d2.getTime();
}

/*
	This function will check the extenstion for the uploaded file
*/
function checkExtension(fileFieldId, allowedExtensions)
{	
	var filename = eval(formName + fileFieldId).value;
	
	filename = trimText(filename);

	if(filename.length==0)
		return true;

	if(filename.indexOf(".")==-1)
		return false;
	
	var extension = filename.substr(filename.lastIndexOf(".")+1,(filename.length));
					
	if(allowedExtensions.indexOf("*.*")!=-1)
		return true;
	else
	{	
		var extArr = allowedExtensions.split(',');
		for(var i=0;i<extArr.length;i++)
		{
			if(extension.toUpperCase()==extArr[i].toUpperCase())
				return true;
		}
	}
}

//added by vaibhavi
/** 
*	This function checks the value of the field against
*	given value
*	@param Label,element id,value
*/
function isValidValue(fieldId,allowedvalue,fieldName)
{
	var caption= " value should not exceed ";
	var obj = eval(formName + fieldId)
	if(parseInt(obj.value) > allowedvalue)
	{
		alert(fieldName+caption+allowedvalue);
		obj.focus();
		return false;
	}
	else
		return true;
}

//added by vaibhavi
/** 
*	This function checks the minimum value for the field against
*	given value
*	@param Label,element id,value
*/

function isValidMinValue(fieldId,minValue,fieldName)
{
	var caption= " value should be greater than " + minValue;
	var obj = eval(formName + fieldId)
	if(parseInt(obj.value) <= minValue)
	{
		alert(fieldName+caption);
		obj.focus();
		return false;
	}
	else
		return true;
}
//added by vaibhavi
/** 
*	This function checks the range for the field against
*	given min and max value
*	@param Label,element id,value
*/

function isValidRange(fieldId,minValue,maxValue,fieldName)
{
	var caption= " value should be between ";
	var obj = eval(formName + fieldId)
	if(parseInt(obj.value) > maxValue || parseInt(obj.value) < minValue)
	{
		alert(fieldName+caption+minValue+"-"+maxValue);
		obj.focus();
		return false;
	}
	else
		return true;
}

function trimSentense(strText) 
{ 
    // this will get rid of leading spaces 
    while (strText.substring(0,1) == ' ') 
        strText = strText.substring(1, strText.length);

    // this will get rid of trailing spaces 
    while (strText.substring(strText.length-1,strText.length) == ' ')
        strText = strText.substring(0, strText.length-1);

   return strText;
} 

//added by Novin Jaiswal
/** 
*	This function will trim the Specified feild value than remove inbetween spaces
*	and convert the first letter of every word in caps.
*	@param fieldId
*/


function sentenseCase(fieldId)
{
	 var strTextBox = eval(formName + fieldId) ;
	if(strTextBox != null)
	{
		 var strOriginalText = strTextBox.value;

		 strOriginalText = trimSentense(strOriginalText);
		 var strChangedText = "";
		 var flag = false;
		 var strTemp = "";

		  for(var i=0;i<strOriginalText.length;i++)
		  {
			strTemp = strOriginalText.charAt(i);

			if(!(flag && strTemp == ' '))
			{
				
				if(i==0 || (flag && strTemp != ' '))
				{
					strChangedText = strChangedText + strTemp.toUpperCase();
				}
				else if(strTemp == ' ')
				{
					flag = true;
					strChangedText = strChangedText + ' ';
				}
				else
				{
					strChangedText = strChangedText + strTemp.toLowerCase();
				}
				
				if(flag && strTemp != ' ')
				{
					flag = false;
				}
			}
		  }		//for loop over
		 strTextBox.value = strChangedText;
	}
//	 alert("return ="+strTextBox.value);
	 return true;

}
//added for security check. checking invalid data
function cssCheck(fieldName, controlName)
{
	var i = true;
	var fieldType = 'document.forms[0].'+fieldName+'.type';
	
	if(eval(fieldType)=="text" || eval(fieldType)=="textarea")
	{
		var fieldValue = 'document.forms[0].'+fieldName+'.value';
		var a = eval(fieldValue);		
		
		if(!checkForCSS(a))
		{
			alert(controlName + ' ' + alertMessageForCssCheck);
			selectRequiredTab(fieldName);				
			setFocusSelection(fieldName);			
			return false;
		}
		if(!sqlInjection(a))
		{
			alert(controlName + ' ' + alertMessageForCssCheck);
			selectRequiredTab(fieldName);				
			setFocusSelection(fieldName);			
			return false;
		}
	}	
	return true;
}
function validateSpecificFormFields(controlNames)
{
	var returnValue = true;
	for (var i=0; i<controlNames.length; i++)
	{
		var strValidation = document.getElementsByName('formField_'+controlNames[i]);
		for (var j=0; j<strValidation.length; j++)
		{
			//alert(strValidation[j].value);
			var validation = strValidation[j].value.split(' && ');

			if(validation.length > 1)
			{
				returnValue = eval(validation[0]);
				if(returnValue)
				{
					returnValue = eval(validation[1]);
					if(!returnValue)
					{
						return false;
					}
				}
			}
			else
			{			
				returnValue = eval(strValidation[j].value);
				if(!returnValue)
				{
					return false;
				}
			}
		}
	}
	
	return returnValue;
}

function selectRequiredTab( fieldname )
{
	var elementTabNo = getTabNumber(fieldname);
	if(elementTabNo!=null)
	{
		if(currentTab==elementTabNo)
		{
		}
		else
		{
			expandtab( 'tabmenu',elementTabNo);				
		}				
	}
}
function checkSpecialChar(e,isNumberAlso)
{
     var key;
     var keychar;
       
     if (e)
         key = e.keyCode;
     else if (e)
         key = e.which;
     else
         return true;
         
     keychar = String.fromCharCode(key);
     //alert('keychar: '+keychar); 
   
     var iChars = "-=$#:;,@!*?%~(){}<>/0123456789^\\\'|\"+";
     
     if ( iChars.indexOf(keychar) != -1 )
     {
         return false;
     }
     
     var inChars = "0123456789";
     
     if( isNumberAlso && inChars.indexOf(keychar) != -1 )
     {
         return false;
     }
     
     return true;       
}
function validateText(label,e)
{
   return checkSpecialChar(e,false);
}