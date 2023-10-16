
/**
This validation js file contains the functions which are applicable to Loans and advance application.

Created Date : 22-Nov-2007
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
	if (fn_verifyno_install()==false)
		return false;				
	if (fn_verifyno_install_int_amt()==false)
		return false;		
	if( resale_valueCheck()==false)
	  	return false;			
   return true;
}

function fn_DisplayDate(obj)
{
	if (obj!=null)
	{
		var hldydt = obj.value;
	
		if(obj.value!="Select")
		{
			document.forms[0].elements['Festival Date'].value = hldydt.substring(0,10);
			document.forms[0].elements['hldyname'].value = hldydt.substring(10);
		}
		else
		{
			document.forms[0].elements['Festival Date'].value = "";
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

function fn_veifyAdvAmt()
{
	if ((document.getElementById("Eligible_Amt")!=null) && (document.getElementById("Adv_Amt")!=null))
	{
		var eligible_amt = eval(document.forms[0].elements['Eligible_Amt'].value);
		var adv_amt =  eval(document.forms[0].elements['Adv_amt'].value);
		
		if(eval(adv_amt)>eval(eligible_amt))
		{
			alert ("Advance amount(INR) can not be greater than Eligible Amount(INR).");
			return false;
		}	
	}
	return true;
}

function verify_advamt_lt_estmd_cost()
{
	if ((document.forms[0].elements['Estimated Cost (INR)']!=null) && (document.getElementById("Adv_Amt")!=null))
	{
		var estmd_amt = eval(document.getElementById("Estimated Cost (INR)").value);
		var adv_amt =  eval(document.forms[0].elements['Adv_amt'].value);
		if( eval(adv_amt) >  eval(estmd_amt))
		{
			alert('Advance Amount(INR) should be less than or equal to Estimated Cost (INR)');
			return false;
		}		
	}
	return true;
}

function fn_verifyno_install()
{

	if (document.getElementById("No_Install")!=null && document.forms[0].elements["max_installment"]!=null )
	{
		var no_install = eval(document.forms[0].elements['No_Install'].value);
		var max=eval(document.forms[0].elements['max_installment'].value);
		if(eval(no_install)>eval(max))
		{
	 		alert ("Loan amount must be repaid in maximum " + max+" installments.");
			return false;
		}
	}
	return true;
	
}
function fn_verifyno_install_int_amt()
{
	if (document.forms[0].elements['No_Install_Int_Amt']!=null && document.forms[0].elements['max_install_int_amt']!=null )
	{
		var no_install = eval(document.forms[0].elements['No_Install_Int_Amt'].value);
		var max=eval(document.forms[0].elements['max_install_int_amt'].value);
		if(eval(no_install)>eval(max) )
		{
		 	alert ("Interest amount must be repaid in maximum " + max+" installments.");
			return false;
		}
	}
	return true;
	
}
function  resale_valueCheck()
	{
		if(document.getElementById("Resale Value (INR)")!=null)
		{
						
			if( eval(document.getElementById("Resale Value (INR)").value) >= eval(document.getElementById("Estimated Cost (INR)").value))
			{
				alert('Resale Value (INR) should be less than Estimated Cost (INR)');
				return false;
			}
		}
		return true;
	}

//This function verifies that any field accepting numeric values is taking zero value or not..	
function verify_zero_value()
{
// For advance amount
	if (document.forms[0].elements['Adv_Amt']!=null )
	{
		var adv_amt =  eval(document.forms[0].elements['Adv_amt'].value);		
		if( eval(adv_amt) <=0 )
		{
			alert (" Advance amount should not be zero or negative.");
			document.forms[0].elements['Adv_amt'].focus();
			return false;
		}
	}
// For No of installment of advance amount	
	if (document.forms[0].elements['No_Install']!=null)
	{
		var no_install = eval(document.forms[0].elements['No_Install'].value);
		
		if( eval(no_install) <0 )
		{
			alert (" No of installment for advance amount should not be zero or negative.");
			document.forms[0].elements['No_Install'].focus();			
			return false;
		}
		
	}
// For No of installment for interest amount	
/*	if (document.forms[0].elements['No_Install_Int_Amt']!=null)
	{
		var no_install_int_amt = eval(document.forms[0].elements['No_Install_Int_Amt'].value);
		if( eval(no_install_int_amt) <=0 )
		{
			alert (" No of installment for interest amount should not be zero or negative.");
			document.forms[0].elements['No_Install_Int_Amt'].focus();			
			return false;
		}
	}*/
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

function sanction_amountCheck()
 {
 
	if(document.getElementById("txtsamount")!=null)
	{
		if( eval(document.getElementById("txtsamount").value) > eval(document.getElementById("advamt").value))
		{
			alert('Sanctioned Amount should not be greater than Advance Amount (INR)');
			document.forms[0].elements['txtsamount'].focus();
			return false;
		}
	}
	return true;
 }

		
function checkDecimalNumber(key)
	{
		var num;
		num = window.event.keyCode;
		if (eval(num)<=47 ||eval(num)>58)
			return false;
		else
		 	return true;
	}
	
