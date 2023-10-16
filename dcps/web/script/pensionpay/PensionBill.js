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
function getAmountInWords(number) 
{
	alert("Number is "+number);
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
		
	alert("String formed is "+s);
	return s;
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

function calculateShare(cvpamount,sharePercent,counter)
{
	var shareAmount = new String("");

	if(isNaN(cvpamount))
	{
		return shareAmount;
	}
	shareAmount=(sharePercent/100) * cvpamount;
	alert(shareAmount);
	var elementId="txtChequeAmnt"+counter;
	alert(elementId);
	document.getElementById(elementId).value=shareAmount;
	return shareAmount;
}
