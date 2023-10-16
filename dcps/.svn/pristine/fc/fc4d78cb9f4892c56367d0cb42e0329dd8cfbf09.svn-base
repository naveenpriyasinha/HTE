
function calculateAge(source,destination,message)
	{
	

  try
  {
	var b =checkDate(source,' ',message);

		if(b==true)
		{


		var CURDATE = new Date()
		var CURYear = CURDATE.getFullYear()
		var CURMonth = CURDATE.getMonth()
		var CURDate = CURDATE.getDate()

		var CURMonth = CURMonth + 1;
	
		var BIRTHDATE = document.getElementById(source).value

		var BIRTHYear = (BIRTHDATE.charAt(6) + BIRTHDATE.charAt(7) + BIRTHDATE.charAt(8) + BIRTHDATE.charAt(9));
		var BIRTHMonth = (BIRTHDATE.charAt(3) + BIRTHDATE.charAt(4));
		var BIRTHDatee = (BIRTHDATE.charAt(0) + BIRTHDATE.charAt(1));
		var TXTAGE = CURYear - BIRTHYear;
		 
		if(TXTAGE < 0)
		{
			alert(message);
			document.getElementById(source).value='';
			document.getElementById(destination).value='';
			document.getElementById(source).focus();
		}
		
		else if(TXTAGE > 0)
		
		{
		if(CURMonth < BIRTHMonth)
			{
				TXTAGE = TXTAGE-1;
			}
		else if(CURMonth == BIRTHMonth)
			{
				if(CURDate < BIRTHDatee)
					{
						TXTAGE = TXTAGE-1;
					}
			}
		
			document.getElementById(destination).value = TXTAGE;
			
		}
		
		else if(TXTAGE == 0)
		{

			if(BIRTHMonth > CURMonth)
			{
				alert(message);

				document.getElementById(destination).value='';
				document.getElementById(source).value='';
				document.getElementById(source).focus();	
						
			}
			else if(BIRTHMonth < CURMonth)
			{
				document.getElementById(destination).value = TXTAGE;
			}
			else if(BIRTHMonth == CURMonth)
			{

				if(BIRTHDatee > CURDate)
				{
				alert(message);
					document.getElementById(destination).value='';
					document.getElementById(source).value='';
					document.getElementById(source).focus();
					
				}
				else if(BIRTHDatee < CURDate)
				{
					document.getElementById(destination).value = TXTAGE;
				}
				else if(BIRTHDatee == CURDate)
				{
					document.getElementById(destination).value = TXTAGE;
				}
				
			}

		}
				
		document.getElementById(destination).readOnly=true;
		document.getElementById(destination).blur();
		
		if(document.getElementById(source).value == '')
		{
			document.getElementById(destination).readOnly=false;
		}
}
else
{
					document.getElementById(destination).value='';
					document.getElementById(source).value='';
return false;

}
  }catch(e)
  {
  }
  finally
  {
  
  }

	}
	
