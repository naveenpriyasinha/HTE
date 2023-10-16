/*
 * This function validate for date fields comparision.
 * Param1 : Controlvalue to be validate.
 * Param2 : Next control value to be validate.
 * Param3 : String of seperator getting from locale.
 * Param4 : String of format getting from locale.
 * Param5 : String of operator like GT,LT,GTEQ,LTEQ,EQ and NEQ.
 * Return : If strings matches according to operator ,will return value or else false.
 */
function getAlertMsgs()
{
  var msgsval=document.forms[0].msgs.value;
  arrayofmsgs=msgsval.split('~');
  return arrayofmsgs;
  
}


   
   function y2k(number) { return (number < 1000) ? number + 1900 : number; }
   function padout(number) { return (number < 10) ? '0' + number : number; }


function isDate (day,month,year) {
// checks if date passed is valid
// will accept dates in following format:
// isDate(dd,mm,ccyy), or
// isDate(dd,mm) - which defaults to the current year, or
// isDate(dd) - which defaults to the current month and year.
// Note, if passed the month must be between 1 and 12, and the
// year in ccyy format.

    var today = new Date();
    year = ((!year) ? y2k(today.getYear()):year);
    month = ((!month) ? today.getMonth():month-1);
    if (!day) return false
    var test = new Date(year,month,day);
    if ( (y2k(test.getYear()) == year) &&
         (month == test.getMonth()) &&
         (day == test.getDate()) )
        return true;
    else
        return false
}


   function showDates(startYear,startMonth,startDay,endYear,endMonth,endDay)
   {
       startDate = new Date(startYear,startMonth - 1,startDay);
       endDate = new Date(endYear,endMonth - 1,endDay);

       var lDateArr='';
       var i=0;
       for (;;)
       {
           lDateArr=lDateArr + padout(startDate.getDate()) + '/' + padout(startDate.getMonth() + 1) + '/' + 
           y2k(startDate.getYear()) + ',' ;

           startDate = new Date(startDate.getTime() + 1*24*60*60*1000);
           if (startDate > endDate)
           {
               return lDateArr;
           }
       }
   }





function isDateComparison(controlvalue,nextvalue,separator,formatID,operator,name,label,x)
{	
   
    arrayofmsgs=getAlertMsgs()
    var input2=controlvalue;
		var input1=nextvalue;
		var dateseparator=separator;
		var dateformat=formatID;		
		var parsedString2 = input2.split(dateseparator);
		var parsedString1 = input1.split(dateseparator);
		var Format = dateformat.split(dateseparator);
		var y2,y1,d1,d2,m1,m2;
	if(dateseparator != "" && dateformat !="")
	{
		if((Format[0] == "mm" || Format[0] == "m") && (Format[1] == "dd" || Format[1] == "d")&& (Format[2] == "yy" || Format[2] == "yyyy"))
		{
			m2= parseInt(parsedString2[0], 10);		
			d2=parseInt(parsedString2[1], 10);
			y2=parseInt(parsedString2[2], 10);
			
			m1= parseInt(parsedString1[0], 10);
			d1=parseInt(parsedString1[1], 10);
			y1=parseInt(parsedString1[2], 10);

		}
		else if((Format[0] == "dd" ||Format[0] == "d") && (Format[1] == "mm" ||Format[1] == "m")&& (Format[2] == "yy" || Format[2] == "yyyy"))
		{
			d2= parseInt(parsedString2[0], 10);
			m2=parseInt(parsedString2[1], 10);
			y2=parseInt(parsedString2[2], 10);
			
			d1= parseInt(parsedString1[0], 10);
			m1=parseInt(parsedString1[1], 10);
			y1=parseInt(parsedString1[2], 10);

		}
		else if((Format[0] == "mm" || Format[0] == "m") && (Format[1] == "yy" || Format[1] == "yyyy")&& (Format[2] == "dd" || Format[2] == "d"))
		{
			m2= parseInt(parsedString2[0], 10);
			y2=parseInt(parsedString2[1], 10);
			d2=parseInt(parsedString2[2], 10);
			
			m1= parseInt(parsedString1[0], 10);
			y1=parseInt(parsedString1[1], 10);
			d1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "dd" || Format[0] == "d") && (Format[1] == "yy" || Format[1] == "yyyy")&& (Format[2] == "mm" || Format[2] == "m"))
		{
			d2= parseInt(parsedString2[0], 10);
			y2=parseInt(parsedString2[1], 10);
			m2=parseInt(parsedString2[2], 10);
			
			d1= parseInt(parsedString1[0], 10);
			y1=parseInt(parsedString1[1], 10);
			m1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "yy" ||Format[0] == "yyyy") && (Format[1] == "dd" || Format[1] == "d")&& (Format[2] == "mm" || Format[2] == "m"))
		{
			y2= parseInt(parsedString2[0], 10);
			d2=parseInt(parsedString2[1], 10);
			m2=parseInt(parsedString2[2], 10);
			
			y1= parseInt(parsedString1[0], 10);
			d1=parseInt(parsedString1[1], 10);
			m1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "yy" || Format[0] == "yyyy") && (Format[1] == "mm" ||Format[1] == "m")&& (Format[2] == "dd" || Format[2] == "d"))
		{
			y2= parseInt(parsedString2[0], 10);
			m2=parseInt(parsedString2[1], 10);
			d2=parseInt(parsedString2[2], 10);
			
			y1= parseInt(parsedString1[0], 10);
			m1=parseInt(parsedString1[1], 10);
			d1=parseInt(parsedString1[2], 10);
		}
		if(operator == "GT")
		{		
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{	
				return false;
			}
			if(y2<y1)
			{		
				return false;
			}
			else if(y2==y1)
			{
				if(m2<m1)
				{
					return false;
				}
				else if(m2==m1)
				{
					if(d2<d1)
					{
						return false;
					}
				}
			}
		}	
		if(operator == "GTEQ")
		{
      if(y2<y1)
			{		
				return false;
			}
			else if(y2==y1)
			{
   			if(m2<m1)
				{
					return false;
				}
				else if(m2==m1)
				{
          if(d2<d1)
					{
						return false;
					}
				}
			}
		}
		if(operator == "LT")
		{
      
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{	
				return false;
			}	
			if(y2>y1)
			{	
				return false;
			}
			else if(y2==y1)
			{
        
				if(m2>m1)
				{
					return false;
				}
				else if(m2==m1)
				{
          
					if(d2>d1)
					{
						return false;
					}
				}
			}

		}
		if(operator == "LTEQ")
		{
     
    	if(y2>y1)
			{		
         
      return false;
			}
			else if(y2==y1)
			{
				if(m2>m1)
				{
      
         	return false;
				}
				else if(m2==m1)
				{
					if(d2>d1)
					{

           return false;
					}
				}
			}
		}
		if(operator == "EQ")
		{
			if(y2 != y1 || m2 != m1 || d2 != d1)
			{		
				return false;
			}		
		}
		if(operator == "NEQ")
		{
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{		
				return false;
			}		
		}
	}
    
     alert(label +" "+ arrayofmsgs[x]);
     eval("document.forms[0]." + name).focus();
	return true;	
}

function DateDifferenceInDays( start, end, interval, rounding )
{
      
    var iOut = 0;
    
    // Create 2 error messages, 1 for each argument. 
    var startMsg = "Check the Start Date and End Date\n"
        startMsg += "must be a valid date format.\n\n"
        startMsg += "Please try again." ;
		
    var intervalMsg = "Sorry the dateAdd function only accepts\n"
        intervalMsg += "d, h, m OR s intervals.\n\n"
        intervalMsg += "Please try again." ;

    var bufferA = Date.parse( start ) ;
    var bufferB = Date.parse( end ) ;
    	
    // check that the start parameter is a valid Date. 
    if ( isNaN (bufferA) || isNaN (bufferB) ) {
        alert( startMsg ) ;
        return null ;
    }
	
    // check that an interval parameter was not numeric. 
    if ( interval.charAt == 'undefined' ) {
        // the user specified an incorrect interval, handle the error. 
        alert( intervalMsg ) ;
        return null ;
    }
    
    var number = bufferB-bufferA ;
    
    // what kind of add to do? 
    switch (interval.charAt(0))
    {
        case 'd': case 'D': 
            iOut = parseInt(number / 86400000) ;
            if(rounding) iOut += parseInt((number % 86400000)/43200001) ;
            break ;
        case 'h': case 'H':
            iOut = parseInt(number / 3600000 ) ;
            if(rounding) iOut += parseInt((number % 3600000)/1800001) ;
            break ;
        case 'm': case 'M':
            iOut = parseInt(number / 60000 ) ;
            if(rounding) iOut += parseInt((number % 60000)/30001) ;
            break ;
        case 's': case 'S':
            iOut = parseInt(number / 1000 ) ;
            if(rounding) iOut += parseInt((number % 1000)/501) ;
            break ;
        default:
        // If we get to here then the interval parameter
        // didn't meet the d,h,m,s criteria.  Handle
        // the error. 		
        alert(intervalMsg) ;
        return null ;
    }

    return iOut ;
}


function DateDifferenceInDays_Wo_Alert( start, end, interval, rounding )
{
      
    var iOut = 0;
    
    // Create 2 error messages, 1 for each argument. 
    var startMsg = "Check the Start Date and End Date\n"
        startMsg += "must be a valid date format.\n\n"
        startMsg += "Please try again." ;
		
    var intervalMsg = "Sorry the dateAdd function only accepts\n"
        intervalMsg += "d, h, m OR s intervals.\n\n"
        intervalMsg += "Please try again." ;

    var bufferA = Date.parse( start ) ;
    var bufferB = Date.parse( end ) ;
    	
    // check that the start parameter is a valid Date. 
    if ( isNaN (bufferA) || isNaN (bufferB) ) {

        return null ;
    }
	
    // check that an interval parameter was not numeric. 
    if ( interval.charAt == 'undefined' ) {
        // the user specified an incorrect interval, handle the error. 

        return null ;
    }
    
    var number = bufferB-bufferA ;
    
    // what kind of add to do? 
    switch (interval.charAt(0))
    {
        case 'd': case 'D': 
            iOut = parseInt(number / 86400000) ;
            if(rounding) iOut += parseInt((number % 86400000)/43200001) ;
            break ;
        case 'h': case 'H':
            iOut = parseInt(number / 3600000 ) ;
            if(rounding) iOut += parseInt((number % 3600000)/1800001) ;
            break ;
        case 'm': case 'M':
            iOut = parseInt(number / 60000 ) ;
            if(rounding) iOut += parseInt((number % 60000)/30001) ;
            break ;
        case 's': case 'S':
            iOut = parseInt(number / 1000 ) ;
            if(rounding) iOut += parseInt((number % 1000)/501) ;
            break ;
        default:
        // If we get to here then the interval parameter
        // didn't meet the d,h,m,s criteria.  Handle
        // the error. 		

        return null ;
    }

    return iOut ;
}


function dateDifference(startDate,endDate,alertMsgDates)
				{

					diffDays=0;
				   if (startDate < endDate) {
				     var s = startDate;
				      var e = endDate;
				
				   } 
				   else if(startDate > endDate)
					{
					alert(alertMsgDates[4]);
					document.forms[0].todate.value="";
					document.forms[0].todate.focus();
					document.forms[0].todate.selected;
					return -1;   
				   }
				  else {
				      var s = startDate;
				      var e = endDate;
				   }
				   var diffDays = Math.floor((e - s) / 86400000);
				
				 if(diffDays==0){
				 diffDays=1;
				 }
				 else{
				 diffDays+=1;
				 }
				 					 return diffDays;		



					 		}

function dateDifferenceWOAlert(startDate,endDate)
				{

			diffDays=0;
				   if (startDate < endDate) {
				     var s = startDate;
				      var e = endDate;

				   } 
				   else if(startDate > endDate)
					{
					//	alert(alertMsgDates[4]);
					document.forms[0].todate.focus();
					document.forms[0].todate.selected;
					return -1;   
				   }
				  else {
				      var s = startDate;
				      var e = endDate;
				      				
				   }
				   var diffDays = Math.floor((e - s) / 86400000);
				
				 if(diffDays==0){
				 diffDays=1;
				 }
				 else{
				 diffDays+=1;
				 }

					 return diffDays;		
					 		}




function dateDifferenceWOAlert_pre_suff(startDate,endDate)
				{
			diffDays=0;
				   
				      var s = startDate;
				      var e = endDate;
				   
				   if(startDate > endDate)
					{
					//	alert(alertMsgDates[4]);
					document.forms[0].todate.focus();
					document.forms[0].todate.selected;
					return -1;   
				   }
				   var diffDays = Math.floor((e - s) / 86400000);
				
				 if(diffDays==0){
				 diffDays=1;
				 }
				 else{
				 diffDays+=1;
				 }
				 			alert(diffDays);

					 return diffDays;		
					 		}





function DateToMMDDYYYY(Date)
{
      
    var strDate;
    var strDateArray;
    var strDay;
    var strMonth;
    var strYear;
    var intday;
    var intMonth;
    var intYear;
    var booFound = false;
    var datefield = eval(Date);
    var strSeparatorArray = new Array("-","/");
    var intElementNr;
 //   strDate = datefield.value;
   strDate = Date;
 
 for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) 
    {

        if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) 
        {
     
            strDateArray = strDate.split(strSeparatorArray[intElementNr]);
            if (strDateArray.length != 3) 
            {
                alert("Enter valid date");
             
            }
            else 
            {
                strDay = strDateArray[0];
                strMonth = strDateArray[1];
                strYear = strDateArray[2];
            }
            booFound = true;
        }
    }
            dateconcat=strMonth+"/"+strDay+"/"+strYear;

 return dateconcat;

}


function getDAY(Date)
{
      
    var strDate;
    var strDateArray;
    var strDay;
    var strMonth;
    var strYear;
    var intday;
    var intMonth;
    var intYear;
    var booFound = false;
    //var datefield = eval(Date);
    var strSeparatorArray = new Array("-","/");
    var intElementNr;
     strDate = Date;
   

 for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) 
    {

        if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) 
        {
     
            strDateArray = strDate.split(strSeparatorArray[intElementNr]);
            if (strDateArray.length != 3) 
            {
                alert("Enter valid date");            
            }
            else 
            {
                strDay = strDateArray[0];
                strMonth = strDateArray[1];
                strYear = strDateArray[2];
            }
            booFound = true;
        }
    }
            dateconcat=strDay;

 return dateconcat;

}

function getYear(Date)
{
      
    var strDate;
    var strDateArray;
    var strDay;
    var strMonth;
    var strYear;
    var intday;
    var intMonth;
    var intYear;
    var booFound = false;
  //  var datefield = eval(Date);
    var strSeparatorArray = new Array("-","/");
    var intElementNr;
     strDate = Date;
   

 
 for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) 
    {

        if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) 
        {
     
            strDateArray = strDate.split(strSeparatorArray[intElementNr]);
            if (strDateArray.length != 3) 
            {
                alert("Enter valid date");
             
            }
            else 
            {
                strDay = strDateArray[0];
                strMonth = strDateArray[1];
                strYear = strDateArray[2];
            }
            booFound = true;
        }
    }
            dateconcat=strYear;

 return dateconcat;

}

function getMonth(Date)
{
      
    var strDate;
    var strDateArray;
    var strDay;
    var strMonth;
    var strYear;
    var intday;
    var intMonth;
    var intYear;
    var booFound = false;
   // var datefield = eval(Date);
    var strSeparatorArray = new Array("-","/");
    var intElementNr;
     strDate = Date;
   

 
 for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) 
    {

        if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) 
        {
     
            strDateArray = strDate.split(strSeparatorArray[intElementNr]);
            if (strDateArray.length != 3) 
            {
                alert("Enter valid date");
             
            }
            else 
            {
                strDay = strDateArray[0];
                strMonth = strDateArray[1];
                strYear = strDateArray[2];
            }
            booFound = true;
        }
    }
            dateconcat=strMonth;

 return dateconcat;

}

//Added by sreekanth on 17/02/2005 
// date comparison to compare one date with arraylist of dates

function isDateValid(controlvalue,nextvalue,separator,formatID,operator)
{	
  
  arrayofmsgs=getAlertMsgs()
  var input2=nextvalue;
		var input1=controlvalue;
//  alert(input1); alert(input2);
		var dateseparator=separator;
		var dateformat=formatID;		
		var parsedString2 = input2.split(dateseparator);
		var parsedString1 = input1.split(dateseparator);
		var Format = dateformat.split(dateseparator);
		var y2,y1,d1,d2,m1,m2;
	if(dateseparator != "" && dateformat !="")
	{
		if((Format[0] == "mm" || Format[0] == "m") && (Format[1] == "dd" || Format[1] == "d")&& (Format[2] == "yy" || Format[2] == "yyyy"))
		{
			m2= parseInt(parsedString2[0], 10);		
			d2=parseInt(parsedString2[1], 10);
			y2=parseInt(parsedString2[2], 10);
			
			m1= parseInt(parsedString1[0], 10);
			d1=parseInt(parsedString1[1], 10);
			y1=parseInt(parsedString1[2], 10);

		}
		else if((Format[0] == "dd" ||Format[0] == "d") && (Format[1] == "mm" ||Format[1] == "m")&& (Format[2] == "yy" || Format[2] == "yyyy"))
		{
			d2= parseInt(parsedString2[0], 10);
			m2=parseInt(parsedString2[1], 10);
			y2=parseInt(parsedString2[2], 10);
			
			d1= parseInt(parsedString1[0], 10);
			m1=parseInt(parsedString1[1], 10);
			y1=parseInt(parsedString1[2], 10);

		}
		else if((Format[0] == "mm" || Format[0] == "m") && (Format[1] == "yy" || Format[1] == "yyyy")&& (Format[2] == "dd" || Format[2] == "d"))
		{
			m2= parseInt(parsedString2[0], 10);
			y2=parseInt(parsedString2[1], 10);
			d2=parseInt(parsedString2[2], 10);
			
			m1= parseInt(parsedString1[0], 10);
			y1=parseInt(parsedString1[1], 10);
			d1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "dd" || Format[0] == "d") && (Format[1] == "yy" || Format[1] == "yyyy")&& (Format[2] == "mm" || Format[2] == "m"))
		{
			d2= parseInt(parsedString2[0], 10);
			y2=parseInt(parsedString2[1], 10);
			m2=parseInt(parsedString2[2], 10);
			
			d1= parseInt(parsedString1[0], 10);
			y1=parseInt(parsedString1[1], 10);
			m1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "yy" ||Format[0] == "yyyy") && (Format[1] == "dd" || Format[1] == "d")&& (Format[2] == "mm" || Format[2] == "m"))
		{
			y2= parseInt(parsedString2[0], 10);
			d2=parseInt(parsedString2[1], 10);
			m2=parseInt(parsedString2[2], 10);
			
			y1= parseInt(parsedString1[0], 10);
			d1=parseInt(parsedString1[1], 10);
			m1=parseInt(parsedString1[2], 10);
		}
		else if((Format[0] == "yy" || Format[0] == "yyyy") && (Format[1] == "mm" ||Format[1] == "m")&& (Format[2] == "dd" || Format[2] == "d"))
		{
			y2= parseInt(parsedString2[0], 10);
			m2=parseInt(parsedString2[1], 10);
			d2=parseInt(parsedString2[2], 10);
			
			y1= parseInt(parsedString1[0], 10);
			m1=parseInt(parsedString1[1], 10);
			d1=parseInt(parsedString1[2], 10);
		}
		if(operator == "GT")
		{		
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{	
				return false;
			}
			if(y2<y1)
			{		

				return false;
			}
			else if(y2==y1)
			{
				if(m2<m1)
				{

					return false;
				}
				else if(m2==m1)
				{
					if(d2<d1)
					{

						return false;
					}
				}
			}
		}	
		if(operator == "GTEQ")
		{
   if(y2<y1)
			{		

				return false;
			}
			else if(y2==y1)
			{
   		if(m2<m1)
				{

					return false;
				}
				else if(m2==m1)
				{
     if(d2<d1)
					{

						return false;
					}
				}
			}
		}
		if(operator == "LT")
		{
      
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{	
				return false;
			}	
			if(y2>y1)
			{	
				return false;
			}
			else if(y2==y1)
			{
        
				if(m2>m1)
				{
					return false;
				}
				else if(m2==m1)
				{
          
					if(d2>d1)
					{
						return false;
					}
				}
			}

		}
		if(operator == "LTEQ")
		{
   if(y2>y1)
			{		
    
            return false;
			}
			else if(y2==y1)
			{
				if(m2>m1)
				{

         	return false;
				}
				else if(m2==m1)
				{
					if(d2>d1)
					{

           return false;
					}
				}
			}
		}
		if(operator == "EQ")
		{
			if(y2 != y1 || m2 != m1 || d2 != d1)
			{		
				return false;
			}		
		}
		if(operator == "NEQ")
		{
			if(y2 == y1 && m2 == m1 && d2 == d1)
			{		
				return false;
			}		
		}
	}
	return true;	
}

function NoOfDaysFromTwoDates(fromDateObj,toDateObj,holidayList,form,alertMsg){

		var lHldyArr=new Array();
        lHldyArr=holidayList.split("~");
        var lHldySize=lHldyArr.length;
		var lStrFromDate=fromDateObj.value;
	    var lStrToDate=toDateObj.value;
		var noofDays=0;

  //    var lStrLevVal=document.Form1.OptHol.attrTitle1;
  //     var LevType = document.Form1.LevTyp.value;
            

 if ((fromDateObj.value!="") && (toDateObj.value!=""))
       { 
       if(lHldySize>0)
				 { 
							var substr=lHldyArr[0].substring(0,lHldyArr[0].indexOf(" "));
			        	    for(i=0;i<lHldySize-1; i++){   
								var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
			       				if((DateToMMDDYYYY(fromDateObj.value)==DateToMMDDYYYY(holidayDate))  &&
			       				 (document.frmleaveapply.natureofleave.value=="1_1"))
			       				{
								 alert(alertMsg[0]);
        		             	 //fromDateObj.value="";
    		                     return -1 ;
	                		     }
                			}

				             for(i=0;i<lHldySize-1; i++){ 
	    						var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
					            var splitDate=substr.split("-");	
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
	                			if((DateToMMDDYYYY(toDateObj.value)==DateToMMDDYYYY(holidayDate)) && (document.frmleaveapply.natureofleave.value=="1_1"))
	                			{  
									alert(alertMsg[1]);
               					     //toDateObj.value="";
				                     return -1 ;
            				    }
            				}	
					}
					
					var startDate;
           var endDate;
           var noofSundays;
           //var d1=(fromDateObj.value);
           //var d2=(toDateObj.value);


           // To calculate number of days of leave 
           //startDate=DateToMMDDYYYY(fromDateObj.value);
           //endDate=DateToMMDDYYYY(toDateObj.value);

 		        var Date1=fromDateObj.value.split("/");
				var Date2=toDateObj.value.split("/");

    alert(!isNaN(Date2[1]));

//           isDate(Date1[0],Date1[1],Date1[2])==false){			
           if((!isNaN(Date1[0])||!isNaN(Date1[1])||!isNaN(Date1[2]))==false){
                      return -1;
           }
//			else if(isDate(Date2[0],Date2[1],Date2[2])==false)

		else if((!isNaN(Date2[0])||!isNaN(Date2[1])||!isNaN(Date2[2]))==false)
           {
                  
                      return -1;
           }
           else{
				Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
	           	Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];				
				//noofDays=dateDifferenceWoAlert(new Date(Date1),new Date(Date2),alertMsgDates);

              }
		   
			if(isNaN(noofDays) || eval(noofDays)< eval(0)){
			
			toDateObj.select();			
			toDateObj.focus();
			//toDateObj.value="";

			document.forms[0].Submit.disabled=true;
			//alert(alertMsg[4]);
			return -1 ;

			}
	        else{
	        document.forms[0].Submit.disabled=false;
	        }
           	noofDays=noofDays + 1;
					
					
					
					
					
					
					
					
					
					
       
       
 }
}



	
















function DDMMYYYY(date){
var arr=date.split("-");
return arr[2]+"/"+arr[1]+"/"+arr[0];
}
function FormatDDMMYYYY(date,TDComponent){
var arr=date.split("-");
TDComponent.innerText=": " +arr[2]+"/"+arr[1]+"/"+arr[0];

}

function FormatDDMMYYYY_WO_COLON(date,TDComponent){
var arr=date.split("-");
TDComponent.innerText=arr[2]+"/"+arr[1]+"/"+arr[0];
}


function FormatDDMMYYYY_WO_COLON_Ver2(date){
var arr=date.split("-");

return arr[2]+"/"+arr[1]+"/"+arr[0];

}



function checkDatesFalls_in_GeneralHoliday(fromDateObj,toDateObj,holidayList,form,alertMsg)
{


		var lHldyArr=new Array();
        lHldyArr=holidayList.split("~");
        var lHldySize=lHldyArr.length;

if(fromDateObj.value!="" && toDateObj.value!=""){
		   if(lHldySize>0)
				 { 
							var substr=lHldyArr[0].substring(0,lHldyArr[0].indexOf(" "));
							var fromDate_flag=false;
							var toDate_flag=false;
			        	    for(i=0;i<lHldySize-1; i++){   
								var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
			       				if(DateToMMDDYYYY(fromDateObj.value)==DateToMMDDYYYY(holidayDate))
			       				{
								 fromDate_flag=true;
								 break;
	                		     }
	                		     else{
								 fromDate_flag=false;
	                		     }
	                		     
                			}
                			
                			if(fromDate_flag==false){
	                			alert(alertMsg);
	                			fromDateObj.value="";
	                			fromDateObj.focus();
	                			return false;
                			}
                			else {
                			return true;
                			}

				             for(i=0;i<lHldySize-1; i++){ 
	    						var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
					            var splitDate=substr.split("-");	
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
	                			if(DateToMMDDYYYY(toDateObj.value)==DateToMMDDYYYY(holidayDate))
	                			{  
										toDate_flag=true;
										break;
            				    }
            				    else{
										toDate_flag=false;            				    
            				    }
            				}
            				
            				if(toDate_flag==false){
	                			alert(alertMsg);
	                			toDateObj.value="";
	                			toDateObj.focus();
	                			return false;
                			}
			       			else {return true;}
            					
					}

	}
}



/*function checkHalfDay()
{
if(document.frmleaveapply.fromdate.value!="" &&  document.frmleaveapply.todate.value!=""){
if(document.frmleaveapply.natureofleave.value=='1')
{
if(Differ(document.frmleaveapply.fromdate,document.frmleaveapply.todate)==1){
document.getElementById("halfday").style.display="";
}
else
{
document.getElementById("halfday").style.display="none";
}
}
else{
document.getElementById("halfday").style.display="none";
}
}
}
*/



function Differ(fromDateObj,toDateObj,holidayList,form,alertMsg){

		var lHldyArr=new Array();
        lHldyArr=holidayList.split("~");
        var lHldySize=lHldyArr.length;
		var lStrFromDate=fromDateObj.value;
	    var lStrToDate=toDateObj.value;
		var noofDays=0;

  //    var lStrLevVal=document.Form1.OptHol.attrTitle1;
  //     var LevType = document.Form1.LevTyp.value;
            

 if ((fromDateObj.value!="") && (toDateObj.value!=""))
       { 
       var startDate;
           var endDate;
           var noofSundays;
           var d1=(fromDateObj.value);
           var d2=(toDateObj.value);


           // To calculate number of days of leave 
           startDate=DateToMMDDYYYY(fromDateObj.value);
           endDate=DateToMMDDYYYY(toDateObj.value);
          
			

           // Number of sundays between start date and end date 
          
           startDateDay=getDAY(fromDateObj.value);           
           strMonth=getMonth(fromDateObj.value);
           strYear=getYear(fromDateObj.value);

           endDateDay=getDAY(toDateObj.value);
           endMonth=getMonth(toDateObj.value);
           endYear=getYear(toDateObj.value); 

           var sDate = new Date(strYear,strMonth-1,startDateDay);
           var eDate = new Date(endYear,endMonth-1,endDateDay);
       			leaveType=form.natureofleave.value.split('_');	
				 if(lHldySize>0)
				 { 
						           if(leaveType[0]!= "2"){
							var substr=lHldyArr[0].substring(0,lHldyArr[0].indexOf(" "));
			        	    for(i=0;i<lHldySize-1; i++){   
								var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
			       				if((DateToMMDDYYYY(fromDateObj.value)==DateToMMDDYYYY(holidayDate))  &&
			       				 (document.frmleaveapply.natureofleave.value=="1_1"))
			       				{

								 alert(alertMsg[0]);
        		             	 fromDateObj.value="";
    		                     return -1 ;
	                		     }
                			}

				             for(i=0;i<lHldySize-1; i++){ 
	    						var substr=lHldyArr[i].substring(0,lHldyArr[i].indexOf(" "));
					            var splitDate=substr.split("-");	
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
	                			if((DateToMMDDYYYY(toDateObj.value)==DateToMMDDYYYY(holidayDate)) && (document.frmleaveapply.natureofleave.value=="1_1"))
	                			{  
									alert(alertMsg[1]);
               					     toDateObj.value="";
				                     return -1 ;
            				    }
            				}	
					}
					}

		
			
			// <!-- Restricted  holiday Check-->
           if(leaveType[0] == "2")
           {
			var lDateArrList = new Array(30);
               j=0;ctr=0;
               var lStrDates = showDates(strYear,strMonth,startDateDay,endYear,endMonth,endDateDay);
               //alert(lStrGenHolid);
               var lGenArrList=holidayList.split("~");
               lDateArrList = lStrDates.split(",");
				var flag="No";
			  for(i=0;i<(lDateArrList.length-1);i++)
               {
			              for(k=0;k<lGenArrList.length-1;k++)
                   {   
                 	     		var substr=lGenArrList[k].substring(0,lGenArrList[k].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	

	                    if(lDateArrList[i]==holidayDate)
                       {
                               flag="Yes";
                               ctr = ctr+1;                              

                       }
                       else
                       {
                           flag="No";
                       }
                       
                   }
               }
           

			
               if ((ctr!=(lDateArrList.length-1)) && (flag!="Yes"))
               {
                   lStrLevVal = "";
	               alert(alertMsg[11]);
                  
                   fromDateObj.value="";
				   toDateObj.value="";
                   return -1;
                  
                   
               }
               else{
               return ctr;
               }
           } 
           //<!-- End of if -->
				

 noofDays=DateDifferenceInDays(startDate,endDate,'d');

			if(noofDays < 0){
			
			toDateObj.select();			
			toDateObj.focus();
			//toDateObj.value="";

			document.forms[0].Submit.disabled=true;
			alert(alertMsg[4]);
			return -1 ;

			}
	        else{
	        document.forms[0].Submit.disabled=false;
	        }
           	noofDays=noofDays + 1;
		   
           
           var x =0; var y=0;
           
            
             if (sDate.getDay() == 0)
             {

				alert(alertMsg[2]);             
                // document.frmleaveapply.fromdate.value="";
                 x=1;
             }
             if (eDate.getDay() == 0)
             {
                 //alert("End date falls on a sunday");
            
                 //document.frmleaveapply.todate.value="";
             }
           //}
           //IF CONDITION ADDED BY ANSHU AS ON 24TH JULY 2006 FOR STATION LEAVE FOR IPS OFFICERS END
           
           if ((eDate.getDay() == 0) && (sDate.getDay() == 0))
           {
               x=2;
           }

           
              
              // start : Added on 13th Aug 
              var totalmonths = (endMonth*1) - (strMonth*1) ;
              var liSatCnt = 0;
              var lArrNoOfSaturdays = new Array();
              var tempStrMonth = strMonth ;
              
              for(cnt=0;cnt<=totalmonths;cnt++)
              {
                  
                  //alert("CNT::"+cnt);
                  var monthcnt = 0;
                  var enddatemonth = Calendar_get_daysofmonth((tempStrMonth-1)*1,strYear);
                  //alert(strMonth + "::"+enddatemonth);
                  var startDate = new Date(strYear,tempStrMonth-1,1);
                  var endDate = new Date(strYear,tempStrMonth-1,enddatemonth);
                  while(startDate <= endDate)
                  {                   
                      // Calculation of Saturdays
                      if(startDate.getDay()==6)
                      {   
                         monthcnt = monthcnt + 1;
                         var strSatStrDay = startDate.getUTCDate()+1;
                         var strSatStrMonth = startDate.getUTCMonth()+1;
                         var strSatStrYear = startDate.getUTCFullYear();
                         if(strSatStrMonth<10)
                         {
                            strSatStrMonth = "0" + strSatStrMonth;
                         }
                         if(monthcnt%2==0)
                         {
                            var lDate = strSatStrDay+"/"+strSatStrMonth+"/"+strSatStrYear;
                            //alert("lDate::"+lDate);
                            lArrNoOfSaturdays[liSatCnt] = lDate;
                            //alert("lDate::"+lArrNoOfSaturdays[liSatCnt]);
                            liSatCnt = liSatCnt + 1;
                         }
                         //noOfsaturdays = noOfsaturdays*1 + 1;                                                        
                      }                     
                      newDate = startDate;
                      newDate.setDate(startDate.getDate()+1);
                      startDate = newDate;
                  }
                  tempStrMonth = tempStrMonth*1 + 1;
            }
              
              // End : Added on 13th March 
             
           //Start : Calculate the sundays & saturdays 
            var startDate = new Date(strYear,strMonth-1,startDateDay);
            var endDate = new Date(endYear,endMonth-1,endDateDay);
            var noOfsundays = 0;
            var noOfsaturdays = 0;  // added for saturdays
            var newDate;
            var lArrSundays = new Array();
            var lArrSaturday = new Array();
            var liCnt = 0;
            var liCnt1 = 0;
            
            while(startDate <= endDate)
            {
                if(startDate.getDay()==0)
                {
                   var strSunStrDay = startDate.getUTCDate()+1;
                   var strSunStrMonth = startDate.getUTCMonth()+1;
                   var strSunStrYear = startDate.getUTCFullYear();
                   if(strSunStrMonth<10)
                   {
                      strSunStrMonth = "0" + strSunStrMonth;
                   }
                   var lDate = strSunStrDay+"/"+strSunStrMonth+"/"+strSunStrYear;
                   lArrSundays[liCnt] = lDate;
                   liCnt = liCnt + 1;
                   noOfsundays = noOfsundays*1 + 1;
                }
                
                // Calculation of Saturdays
                if(startDate.getDay()==6)
                {   
                    //alert("lArrNoOfSaturdays.length::"+lArrNoOfSaturdays.length);
                    var strSatStrDay = startDate.getUTCDate()+1;
                    var strSatStrMonth = startDate.getUTCMonth()+1;
                    var strSatStrYear = startDate.getUTCFullYear();
                    if(strSatStrMonth<10)
                     {
                        strSatStrMonth = "0" + strSatStrMonth;
                     }
                    var lDate = strSatStrDay+"/"+strSatStrMonth+"/"+strSatStrYear;
                    for(satCnt=0;satCnt < lArrNoOfSaturdays.length ; satCnt++)
                    {
                      //alert(lDate + "::" + lArrNoOfSaturdays[satCnt] );
                      if(lDate == lArrNoOfSaturdays[satCnt])
                      {
                          noOfsaturdays = noOfsaturdays*1 +1;
                          lArrSaturday[liCnt1] = lDate;
                          liCnt1 = liCnt1 + 1;
                          
                      }
                    }
                }                  
                newDate = startDate;
                newDate.setDate(startDate.getDate()+1);
                startDate = newDate;
            }
            noofDays = noofDays;      // Change for calculation of sundays
            //- noOfsundays;
            //alert(noofDays);       

            
           //End : Calculate the sundays
            //start : Puja code 8th March
                  noofDays = noofDays - noOfsundays;
                  noofDays = noofDays - noOfsaturdays; //subtract saturdays
				

	 /* Start :  General holiday check for casual leaves */
		 
/*           if(document.Form1.LevTyp.value == "<%=lStrCasualLeave%>")
           {*/
               
				var lDateArrList = new Array(30);
               j=0;ctr=0;
               var lStrDates = showDates(strYear,strMonth,startDateDay,endYear,endMonth,endDateDay);


               
               //alert(lStrGenHolid);
               var lGenArrList=holidayList.split("~");
               lDateArrList = lStrDates.split(",");

               
               for(i=0;i<(lDateArrList.length-1);i++)
               {
							

                   for(k=0;k<lGenArrList.length-1;k++)
                   {   
                 	     		var substr=lGenArrList[k].substring(0,lGenArrList[k].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
							 
							
						
	                    if(lDateArrList[i]==holidayDate)
                       {
                               flag="Yes";
                               ctr = ctr+1;                              

                       }
                       else
                       {
                           flag="No";
                       }
                       
                   }
               }
                
                
               // Added For excluding sundays which are having general holidays
               for(k=0;k<lGenArrList.length-1;k++)
               {   
				                var substr=lGenArrList[k].substring(0,lGenArrList[k].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	
		                       
               
                      for(l=0;l<lArrSundays.length;l++)
                      {
							 
                          if(lArrSundays[l] == holidayDate)
                          {
									
                                    ctr = eval(ctr)-1;

                          }
                      }
                      
                 }     
                
                 for(k=0;k<lGenArrList.length-1;k++)
               {
                      for(l=0;l<lArrSaturday.length;l++)
                      {
                      
	                            var substr=lGenArrList[k].substring(0,lGenArrList[k].indexOf(" "));
				            	var splitDate=substr.split("-");
								var holidayDate=splitDate[2]+"/"+splitDate[1]+"/"+splitDate[0];	

                          if(lArrSaturday[l] == holidayDate)
                          {
                          									
                                ctr = eval(ctr)-1;
                          }
                      } 
               }
        
               if (ctr <= noofDays)
               {	 		
                 noofDays = noofDays ;	// Change for calculation of sunday
                 // - ctr;		 
       
               }
                                 
              //start : Manvir code 13th Aug
             if ( leaveType[0]==1 )
              {
			      
                   
                  noofDays = noofDays - ctr;
                  
              }
              //end : Manvir code 13th march


				
               if(noofDays == 0)
               {
                 alert(alertMsg[3]);
                 /* alert("<%=rb.getString("APPS.LevOnGenHolid")%>");
                  document.Form1.fromdt.value="";
                  document.Form1.todt.value="";
                  TableDays.style.display="none";
                  div1.innerHTML="";
                  
               */
               //return;
               return -1;
               }
	//}
           //End :  General holiday check for casual leaves


					if(form.halfday[0].status){
					/*if(noofDays==1){
					noofDays=0.5;
					
					}*/
					
					if (document.getElementById('firstdayhalfdayYes').status && document.getElementById('lastdayhalfdayYes').status){
					noofDays=eval(noofDays-1.0);
					}
					else{

						if(document.getElementById("before2Yes").status)
						{
								noofDays=noofDays-0.5;

						}
						else if((document.getElementById('firstdayhalfdayYes').status && document.getElementById('lastdayhalfdayNo').status) || (document.getElementById('firstdayhalfdayNo').status && document.getElementById('lastdayhalfdayYes').status))
						{
							noofDays=noofDays-0.5;

						}
					}
					
					}
					else {
					/*if(noofDays==1)
					noofDays=1;*/
					noofDays=noofDays;
					}

				  return noofDays;
          //end : Puja code 8th march

 }
}



