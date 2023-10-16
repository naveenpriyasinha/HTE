
function showAttach()
	{
		
		
		document.getElementById("attach").style.display="";
	}

function hideAttach()
	{
		
		document.getElementById("attach").style.display="none";
	}
	
function calYearOfService()
{
var newEol=0;

if(document.getElementById("dateOfJoin").innerHTML!="")
{
var lDoj=document.getElementById("dateOfJoin").innerHTML;

var varEol=0;
if(document.CessationDet.eol.value!="" )
{
if (document.CessationDet.eol.value.charAt(0)!=".")
{
varEol=document.CessationDet.eol.value;
varEol=varEol-0;
}
}
 varLwp=0;
 if(document.CessationDet.lwp.value!="" )
{
if (document.CessationDet.lwp.value.charAt(0)!=".")
{
varLwp=document.CessationDet.lwp.value;
varLwp=varLwp-0;
}
}
 varSuspensionDuration=0;
 if(document.CessationDet.suspension_duration.value!=""  )
{
if (document.CessationDet.suspension_duration.value.charAt(0)!=".")
{
varSuspensionDuration=document.CessationDet.suspension_duration.value;
varSuspensionDuration=varSuspensionDuration-0;
}
}
if (varEol>300)
{

newEol=varEol-300;
}
var totLeave=parseInt(newEol)+parseInt(varLwp)+parseInt(varSuspensionDuration);
var lCurrDate=document.getElementById("srvdate").innerHTML;
var retDays=getTotalDays(lDoj, lCurrDate)-totLeave;
var days=getTotalDays(lDoj, lCurrDate)-totLeave;
var years=parseInt(days/365);
var days=days % 365;
var months=parseInt(days/30);
var days=days % 30;
var daysWithLeave=getTotalDays(lDoj, lCurrDate);
var years1=parseInt(daysWithLeave/365);
var daysWithLeave=daysWithLeave % 365;
var months1=parseInt(daysWithLeave/30);
var days1=daysWithLeave % 30;


document.CessationDet.yearOfService.value=retDays;
document.CessationDet.yearOfService1.value=years+"(Years)"+" "+months+"(Months)"+" "+days+"(Days)";
document.CessationDet.yearOfService2.value=years1+"(Years)"+" "+months1+"(Months)"+" "+days1+"(Days)";
}
else{
document.CessationDet.yearOfService.value=0;
document.CessationDet.yearOfService1.value=0;
document.CessationDet.yearOfService2.value=0;


}

}


function getTotalDays(strDate1,strDate2)
{	
	
	strDate1 = strDate1.split("/"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	starttime = new Date(starttime.valueOf()); 	
	//End date split to UK date format 
	strDate2 = strDate2.split("/"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	endtime = new Date(endtime.valueOf()); 							

					
	
	setDay1 = getDays(parseInt(strDate1[1]),parseInt(strDate1[2]));
	
	
	setDay2 = getDays(parseInt(strDate2[1]),parseInt(strDate2[2]));
	
	var totalDays = setDay1 - (parseInt(strDate1[0])-1) + parseInt(strDate2[0]);
	
	if(endtime >= starttime) 
	{	
		var setDay = 0;    	
		var lIntPenSerYear = strDate2[2] - strDate1[2];
		
   	 	var lIntPenSerMonth = (strDate2[1])- (strDate1[1]);
   	 	
   	 	var lIntPenSerDay = strDate2[0] - strDate1[0];     	 	
   	      	 	 	 	
   	 	var intMonth=parseInt(strDate1[1]);
   	 
   	 	var intday = parseInt(strDate1[0]);
   	 
   	 	intYear = parseInt(strDate1[2]);
   	 
   	 	while(parseInt(strDate2[2]) >= intYear)
   	 	{   	 
   	 		intMonth++;	
   	 			 		
   	 		if(intMonth!=parseInt(strDate2[1]) || intYear!=parseInt(strDate2[2]))
   	 		{
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
					
				}
				setDay = getDays(intMonth,intYear);
				
				totalDays = totalDays + setDay;	
						
			}
			else if(intMonth==parseInt(strDate2[1]) && intYear==parseInt

(strDate2[2]))
			{
				break;
			}			
		}
	}
	return totalDays;
}
function getDays(month,year)
{
	var setDay=0;	
	if (month == 1 || month == 3 || month == 5 || month == 7 || month 

== 8 || month == 10 || month == 12) {
		setDay = 31;	
	}
	if (month == 4 || month == 6 || month == 9 || month == 11) {
		setDay = 30;
	}
	if (month == 2) 
	{
		if (LeapYear(year) == true) {
			setDay = 29;
		}
		else {
			setDay = 28;
		}
	}
	return setDay;
}

	function LeapYear(year)
	{	
		if(year%4 == 0 )
		{
		
			return true;
		}
		else 	
		{
			return false;		
		}
	}
	function getDateDiffInString(strDate1,strDate2)
	{
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
alert(starttime);
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 

		if(endtime >= starttime) 
		{ 
			var setDay = 0; 
			var lIntPenSerYear = strDate2[2] - strDate1[2];
			var lIntPenSerMonth = strDate2[1]- strDate1[1];
			var lIntPenSerDay = strDate2[0] - strDate1[0]; 
			var intMonth = parseInt(strDate1[1]);

			var intday = parseInt(strDate1[0]);
			intYear = parseInt(strDate1[2]);
			while(parseInt(strDate2[2]) >= intYear)
			{ 
				if (intMonth>=13) 
				{ 
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) 
				{
					setDay = 31; 	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) 
				{
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) 
					{
						setDay = 29;
					}
					else 
					{
						setDay = 28;
					}
				}
				if(setDay!=0)
				{
					while(lIntPenSerDay > setDay)
					{
						lIntPenSerDay -= setDay;
						lIntPenSerMonth++;
						if(lIntPenSerMonth==12)
						{
							lIntPenSerMonth=0;
							lIntPenSerYear++;
						}
					}
					while(lIntPenSerDay < 0)
					{
						lIntPenSerDay = setDay + lIntPenSerDay;
						lIntPenSerMonth--;
						if(lIntPenSerMonth<=-1)
						{
							lIntPenSerMonth=12+lIntPenSerMonth;
							lIntPenSerYear--; 
						}
					}
					if(lIntPenSerMonth <=-1)
					{
						lIntPenSerMonth=12+lIntPenSerMonth;
						lIntPenSerYear--; 
					}
					if(strDate1.toString() == strDate2.toString())
						{lIntPenSerDay=1;}
					return (lIntPenSerYear+'~'+lIntPenSerMonth+'~'+lIntPenSerDay);
				}
				else 
				{
					return '0~0~0'; 
				}
				intMonth++; 
			}
		}
		else
		{
			return '0~0~0'; 
		}
	} 
	

			  
			  
			  
			  
			  
			  
			  
			  
			  
			  