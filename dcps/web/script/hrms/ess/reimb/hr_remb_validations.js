
/**
This validation js file contains the functions which are applicable to Bill reimbursement application.

Created Date : 10-Aug-2007
Author       : Rahul Gamit

Note : to implement validation function for dynamic control their nullable status must be check,
if the function is to be called in inputvalidation function. 

**/

/**
 *	This function will call all other function which are to be validated.
 */ 

function inputvalidation()
{	

	if (verify_zero_value()==false)
		return false;
	if (isbilldateltapplydate()==false)
		return false;
	if(verifyBillduedate()==false)
		return false;	
	if(verifyToDateltapplyDate()==false)
	    return false;	
	if(verifybillduedtgtbilldate()==false)
	   return false;    
	if( verify_no_of_newpapers()==false)
	  	return false;
	if( max_call_limit()==false)
	  	return false;	  	
	
	
   if(document.getElementById("Admissible Amt")!=null)
   {
	   if(document.getElementById("Admissible Amt").value=="")
		{		  
		  document.getElementById("Admissible Amt").value=document.getElementById("txtamount").value;		  
		}
   }	   

   return true;
}
function set_admissible_amt()
{
   if(document.getElementById("Admissible Amt")!=null)
   {		
	  document.getElementById("Admissible Amt").value=document.getElementById("txtamount").value;		
   }	   
   return true;
}
/**
 *	This function will set the admissible amount value as per scrap item status.
 */ 
function cal_admissible_amt(obj) 
{
	if(obj.value=="Yes")
	{
	  document.getElementById("Admissible Amt").value=document.getElementById("txtamount").value;
	}
	else if(obj.value=="No")
	{
	  document.getElementById("Admissible Amt").value= (eval((document.getElementById("txtamount").value)) * 0.75);
	}
	
}	

		
/**
 *	This function will check that the billdate is less than of system date or not.
 */
 
function isbilldateltapplydate()
{
	if (document.forms[0].elements['txtappdate']!=null && document.forms[0].elements['dtbilldate']!=null)
	{
		var applydate = document.getElementById("txtappdate").value;
		//alert("appdate="+document.getElementById("txtappdate").value);	
		var billdate =  document.Reimburse.dtbilldate.value;
    	
	
	      var diff=compareDate(billdate,applydate);
			if (diff<=0)
			{
				alert("BillDate should not be greater or equal to ApplyDate");
				return false;
			}
	}
	return true;
	
}

/**
 *	This function will verify the extension of attachment. 
 
function verifyAttachment()
{
	var atchment = document.getElementById("fileatchment").value; 
	if (checkExtension("fileatchment","jpg,bmp,gif")!=true)
	{
			alert("Only image files are allowed!");		
			return false;
	}
	return true;
}
*/

/**
 *	This function will verify that Bill due date is greater than to date. 
 */
 
function verifyBillduedate()
{
	if(document.forms[0].elements['dttxt2']!=null && document.forms[0].elements['dttotxt5']!=null)
   	{
		var billduedate = document.forms[0].elements['dttxt2'].value;
		var todate = document.forms[0].elements['dttotxt5'].value;
		
		var diff=compareDate(todate,billduedate);
		if (diff<=0)
		{
			alert("ToDate should not be greater or equal to BillDueDate");		
			return false;
		}
		
		
   }
     return true;	
	
}


function verifyToDateltapplyDate()
{

    if(document.forms[0].elements['txtappdate']!=null && document.forms[0].elements['dttotxt5']!=null)
   	{

		var applydate = document.getElementById("txtappdate").value;
		var todate = document.forms[0].elements['dttotxt5'].value;
		
		var diff=compareDate(todate,applydate);
		
		if (diff<=0)
		{
			alert("ToDate should not be greater or equal to ApplyDate");		
			return false;
		}
		
		
   }

   if(document.forms[0].elements['txtappdate']!=null && document.forms[0].elements['dttotxt1']!=null)
   	{

		var applydate = document.getElementById("txtappdate").value;
		var todate = document.forms[0].elements['dttotxt1'].value;
		
		var diff=compareDate(todate,applydate);
		
		if (diff<=0)
		{
			alert("ToDate should not be greater or equal to ApplyDate");		
			return false;
		}
		
		
   }
   
     return true;	
	
}


function verifybillduedtgtbilldate()
{

	if(document.forms[0].elements['dttxt2']!=null && document.forms[0].elements['dtbilldate']!=null)
	{
	   var billduedate = document.forms[0].elements['dttxt2'].value;
	   var billdate =  document.forms[0].elements['dtbilldate'].value;		
		
		var diff=compareDate(billdate,billduedate);
		if (diff<=0)
		{
			alert("BillDueDate should not be less than or equal to BillDate");		
			return false;
		}
	}
	return true;
}
//This function verifies that any field accepting numeric values is taking zero value or not..	
function verify_zero_value()
{
// For Bill number
	if (document.forms[0].elements['txtbno']!=null)
	{
		if(numCheck(document.forms[0].elements['txtbno'].value))
		{   
			var txtbno = eval(document.forms[0].elements['txtbno'].value);
			if( eval(txtbno) <=0 )
			{
				alert (" Bill number should not be zero or negative.");
				document.forms[0].elements['txtbno'].focus();			
				return false;
			}
		}
		else
		 return true;
	}
// For advance amount
	if (document.forms[0].elements['txtamount']!=null )
	{
		var billamount =  eval(document.forms[0].elements['txtamount'].value);		
		if( eval(billamount) <=0 )
		{
			alert (" Bill amount should not be zero or negative.");
			document.forms[0].elements['txtamount'].focus();
			return false;
		}
	}


// For Estimated Cost	
	if (document.forms[0].elements['Contact Number']!=null)
	{
		var contact_no = eval(document.forms[0].elements['Contact Number'].value);
		if( eval(contact_no) <=0 )
		{
			alert (" Contact number should not be zero or negative.");
			document.forms[0].elements['Contact Number'].focus();
			return false;
		}
	}	
// For Resale Value	
	if (document.forms[0].elements['No. of Calls made']!=null)
	{
		var no_of_calls_made = eval(document.forms[0].elements['No. of Calls made'].value);
		if( eval(no_of_calls_made) <=0 )
		{
			alert (" No. of Calls should not be zero or negative.");
			document.forms[0].elements['No. of Calls made'].focus();
			return false;
		}
	}		
	return true;
}



function numCheck(argvalue) {

  if (argvalue.length == 0)
    return false;

  for (var n = 0; n < argvalue.length; n++)
    if (argvalue.substring(n, n+1) < "0" || argvalue.substring(n, n+1) > "9")
      return false;

  return true;

}

function verify_no_of_newpapers()
{
	var controlObj = document.forms[0].elements("Select Newspapers");
	
//	alert("no_of_newspaper:" + controlObj);
	
	if (document.forms[0].elements['max_no_of_newspaper']!=null && controlObj != 'undefined' && controlObj != null )
	{
		var no_of_newspaper = 0;
		//fetching no of selected newspaper		
		for (i=0;i<controlObj.length;i++)
		{
			if(controlObj[i].checked)
			{
//				alert("i " + i + " :" +controlObj[i].value);
				no_of_newspaper = eval(no_of_newspaper) + 1;
			}
		}
		
		var max=eval(document.forms[0].elements['max_no_of_newspaper'].value);
	//	alert("max:" + max);
	//	alert("no_of_newspaper:" + no_of_newspaper);
		if(eval(no_of_newspaper)>eval(max) )
		{
		 	alert ("You can not reimburse bill for more then " + max+" newspaper.");
			return false;
		}
	}
	return true;	
}

function max_call_limit()
{
	var fromdate = document.forms[0].elements["dtfromtxt4"];
	var todate = document.forms[0].elements["dttotxt5"];

	if (fromdate!=null && todate != null && document.forms[0].elements['max_call_limit']!=null)
	{
		var one_day=1000*60*60*24;
		var frommonth = fromdate.value.substring(3,5);
		var tomonth = todate.value.substring(3,5);
		var diff = eval(eval(tomonth) - eval(frommonth));		
		var f_date = new Date(fromdate.value.substring(6),eval(eval(fromdate.value.substring(3,5))-1),fromdate.value.substring(0,2));
		var t_date = new Date(todate.value.substring(6),eval(eval(todate.value.substring(3,5))-1),todate.value.substring(0,2));

		var max=eval(document.forms[0].elements['max_call_limit'].value);
		//Calculate difference btw the two dates, and convert to days
		var days = eval(Math.ceil((t_date.getTime()-f_date.getTime())/(one_day)))+1;		

		var month = Math.ceil(days/31);
		var maxlimit = eval(month * max);
		var billamt = document.forms[0].elements["txtamount"].value;

		var contact_type = document.getElementById("Contact Type");
		if(contact_type.value=='Mobile' )
		{
			if(eval(billamt)>=eval(maxlimit))
			{
				alert("You are exceeding your limit.");
				return false;
			}
		}

	}	
	return true;
}
function TrimingTxtValue(obj)
{
	var TrimedValue;
		if (obj!=null)
		{
			var value = obj.value;
			TrimedValue=value.trim();
			//alert('TrimedValue'+TrimedValue);
			obj.value=TrimedValue;				
		}		
}

function checkSpecialCharForTxtArea(e,isNumberAlso)
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
   
     var iChars = "`[]&_-=$#:;@!*?%~(){}<>/^\\\'|\"+";
     
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
function validateTextArea(e, isNumber)
{
if(checkSpecialCharForTxtArea(e,isNumber)==false)
	{
		alert('You can not add special characters!');
		return false;
	}
	else
	{
		return true;
	}
  // return checkSpecialCharForTxtArea(e,isNumber);
}
