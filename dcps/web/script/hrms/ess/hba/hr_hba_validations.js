/**
This validation js file contains the functions which are applicable to HBA application.

Created Date : 11-Dec-2007
Author       : Rahul Gamit

**/

/**
 *	This function will call all other function which are to be validated.
 */ 

function inputvalidation()
{	

	if (verify_zero_value()==false)
		return false;
	if (fn_veifyAdvAmt()==false)
		return false;		
	if (verify_advamt_lt_estmd_cost()==false)
		return false;	
	if(fn_verifyno_install()==false)
	    return false;		
	if(fn_verifyno_install_int_amt()==false)
	    return false;
   return true;
}
// Below function verifies advance amount is less then eligible amount or not.
function fn_veifyAdvAmt()
{
	if ((document.getElementById("txteamt")!=null) && (document.getElementById("txtadvamt")!=null))
	{
		var eligible_amt = eval(document.forms[0].elements['txteamt'].value);
		var adv_amt =  eval(document.forms[0].elements['txtadvamt'].value);
		
		if(eval(adv_amt)>eval(eligible_amt))
		{
			alert ("Advance amount(INR) can not be greater than Eligible Amount(INR).");
			return false;
		}	
	}
	return true;
}

// Below Function verifies advance amount is less then estimated cost or not.
function verify_advamt_lt_estmd_cost()
{
	if ((document.forms[0].elements['Estimated Cost (INR)']!=null) && (document.forms[0].elements['txtadvamt']!=null))
	{
		var estmd_amt = eval(document.getElementById("Estimated Cost (INR)").value);
		var adv_amt =  eval(document.forms[0].elements['txtadvamt'].value);
		if( eval(adv_amt) >  eval(estmd_amt))
		{
			alert('Advance Amount(INR) should be less than or equal to Estimated Cost (INR)');
			return false;
		}		
	}
	return true;
}


//This function verifies that any field accepting numeric values is taking zero value or not..	
function verify_zero_value()
{
// For advance amount
	if (document.forms[0].elements['txtadvamt']!=null )
	{
		var adv_amt =  eval(document.forms[0].elements['txtadvamt'].value);		
		if( eval(adv_amt) <=0 )
		{
			alert (" Advance amount should not be zero or negative.");
			document.forms[0].elements['txtadvamt'].focus();
			return false;
		}
	}
// For No of installment of advance amount	
	if (document.forms[0].elements['txtnoinst']!=null)
	{
		var no_install = eval(document.forms[0].elements['txtnoinst'].value);
		if( eval(no_install) <=0 )
		{
			alert (" No of installment for advance amount should not be zero or negative.");
			document.forms[0].elements['txtnoinst'].focus();			
			return false;
		}
	}
// For No of installment for interest amount	
	if (document.forms[0].elements['txtnointinst']!=null)
	{
		var no_install_int_amt = eval(document.forms[0].elements['txtnointinst'].value);
		if( eval(no_install_int_amt) <=0 )
		{
			alert (" No of installment for interest amount should not be zero or negative.");
			document.forms[0].elements['txtnointinst'].focus();			
			return false;
		}
	}
// For Estimated Cost	
	if (document.forms[0].elements['Estimated Cost (INR)']!=null)
	{
		var estimated_cost = eval(document.forms[0].elements['Estimated Cost (INR)'].value);
		if( eval(estimated_cost) <=0 )
		{
			alert (" Estimated amount should not be zero or negative.");
			document.forms[0].elements['Estimated Cost (INR)'].focus();
			return false;
		}
	}	

	// For Floor Area (sq.ft.)
	if (document.forms[0].elements['Floor Area (sq.ft.)']!=null)
	{
		var resale_value = eval(document.forms[0].elements['Floor Area (sq.ft.)'].value);
		if( eval(resale_value) <=0 )
		{
			alert (" Floor Area should not be zero or negative.");
			document.forms[0].elements['Floor Area (sq.ft.)'].focus();
			return false;
		}
	}		
	
// For Resale Value	
	if (document.forms[0].elements['Resale Value (INR)']!=null)
	{
		var resale_value = eval(document.forms[0].elements['Resale Value (INR)'].value);
		if( eval(resale_value) <=0 )
		{
			alert (" Resale Value should not be zero or negative.");
			document.forms[0].elements['Resale Value (INR)'].focus();
			return false;
		}
	}		
	return true;
}

// For No. of Installments(Advance Amount)
function fn_verifyno_install()
{

	if (document.getElementById("txtnoinst")!=null && document.forms[0].elements["max_installment"]!=null )
	{
		var no_install = eval(document.forms[0].elements['txtnoinst'].value);
		var max=eval(document.forms[0].elements['max_installment'].value);
		if(eval(no_install)>eval(max))
		{
	 		alert ("Advance amount must be repaid in maximum " + max+" installments.");
			return false;
		}
	}
	return true;
	
}


// For No. of Installments(Interest Amount)
function fn_verifyno_install_int_amt()
{
	if (document.forms[0].elements['txtnointinst']!=null && document.forms[0].elements['max_install_int_amt']!=null )
	{
		var no_install = eval(document.forms[0].elements['txtnointinst'].value);
		var max=eval(document.forms[0].elements['max_install_int_amt'].value);
		if(eval(no_install)>eval(max) )
		{
		 	alert ("Interest amount must be repaid in maximum " + max+" installments.");
			return false;
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
   //return checkSpecialCharForTxtArea(e,isNumber);
}
