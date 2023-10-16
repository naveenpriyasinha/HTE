function isNumber(myfield,e,isDecimalField)
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
    if ((key==null) || (key==0) || (key==8) || (key==9) || (key==13) || (key==27) )
    {
        // control keys
        return true;
    }
    else if ((("0123456789").indexOf(keychar) > -1))
    {
        // numbers
        return true;
    }
    else if (isDecimalField && (keychar == "."))
    {
        // decimal point
        if( (myfield.value.indexOf( keychar ) > -1) )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    else
        return false;
}
  
//function  to check the email Address is in correct format or not.
//to call this function pass the formname.textboxname as the parameter.
function isValidEmailAddress(compName)
{
    var x,y,z;
    x=compName.value.indexOf('@');
    y=compName.value.lastIndexOf('@');
    z=compName.value.indexOf('.',y);
    
    var not_valid = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
    var valid = /^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    if(compName.value!="")      
    {
        if ((!valid.test (compName.value)) || (not_valid.test (compName.value)))
        {
            return false;
        }
    }
    if(compName.value=="")
        return false;
    else
        return true;
}


function compareDates( Date1, Date2 )
//*******************************************************************
//This function compares two dates.
//Input  : Date1, Date2
//Output : Returns true if Date2 >= Date1. Returns false if Date2 < Date1.
//*******************************************************************
{
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
        return true;
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 )
    {
        return false;
    }
    else
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {
            return false;
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                return false;
            }
        }
    }

    return true 
}

// Move From ReportColumnRules.jsp

	function addToQuery()
  	{
		
    	btnAction = "addQuery";
    	formAction();
  	} 
  	
  	function disableCondCombo()
  	{ 
	     if(document.forms[0].txt_FinalQuery.value == null || document.forms[0].txt_FinalQuery.value == "")
	     {
	       document.forms[0].cmb_LogOper.disabled = true;
	     }  
	     else
	     {
	       document.forms[0].cmb_LogOper.disabled = false;
	     }  
  	} 
  
// Move From ReportParameter.jsp

	function getDocElement(elemID)
    {
        var obj
        if (document.all)
        {
            obj = document.all(elemID)
        }
        else if (document.getElementById)
        {
            obj = document.getElementById(elemID)
        } 
        else if (document.layers) 
        {
            obj = document.layers[elemID]
        }
        return obj;
    }
    
    
	function searchReport()
  	{
    	btnAction = "search";
  	}
  
  	function generateReportPrmt(src)
  	{
  		if(arrOfCombos != null)
  		{
  			if(arrOfCombos.length > 0)
  			{
  				for(var iarrOfCombos=0;iarrOfCombos<arrOfCombos.length;iarrOfCombos++)
  				{
  					arrOfCombos[iarrOfCombos].disabled = false;
  				}
  			}
  		}
  		
  		if(arrOfRadioButtons != null)
  		{
  			if(arrOfRadioButtons.length > 0)
  			{
  				for(var iarrOfRadioButtons=0;iarrOfRadioButtons<arrOfRadioButtons.length;iarrOfRadioButtons++)
  				{
  					arrOfRadioButtons[iarrOfRadioButtons].disabled = false;
  				}
  			}
  		}
  		
  		if(arrOfCheckBox != null)
  		{
  			if(arrOfCheckBox.length > 0)
  			{
  				for(var iarrOfCheckBox=0;iarrOfCheckBox<arrOfCheckBox.length;iarrOfCheckBox++)
  				{
  					arrOfCheckBox[iarrOfCheckBox].disabled = false;
  				}
  			}
  		}
  		
    	btnAction = "report";
    	src.disabled=true;
    	if(document.getElementById("rptBtn_ResetBtn") != null)
    		document.getElementById("rptBtn_ResetBtn").disabled = true;
    	formAction();
  	}
  
	function resetReport()
  	{
    	btnAction = "reset";
    	formAction();
  	}
  
	function window_open_old(val,x,y)
  	{
    	var newWindow;
    	var urlstring = '/IWAS/FMS/Calendar.jsp?requestSent='+val;
	    var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
    	newWindow = window.open(urlstring,'Calendar',urlstyle);
	}  
  
  //Added by samir to make fields visible or hidden according to selected option
  function onChangeComboDetails(paraVal,paraName)  
  {
    var val = paraVal ; 
    var paraFrom = paraName + "from";    
    var paraTo = paraName + "to";  
    if(val=="4")
    {
        document.ReportForm.elements[paraFrom].style.visibility="visible";
        document.ReportForm.elements[paraTo].style.visibility="visible";        
    }
    
    else if(val != "4" && val !="-1") 
    {
        document.ReportForm.elements[paraFrom].style.visibility="visible";
        document.ReportForm.elements[paraTo].style.visibility="hidden";  
    }   
    else if(val =="-1")
    {
        document.ReportForm.elements[paraFrom].style.visibility="hidden";
        document.ReportForm.elements[paraTo].style.visibility="hidden"; 
    }    
  }  
    
    

// Move From rptBody.jsp

		function openWindow( url, height, width )
        {
            var widthVal = 610;
        	var hgtVal = 280;
			if(height != null && width != null)
			{
				hgtVal = height;
				widthVal = width;
			}
            var child= window.open( url+'&asPopup=TRUE', '', 'width='+widthVal+', height='+hgtVal+', top=0, left=0, resizable=yes, titlebar=no, menubar=no, scrollbars=yes, toolbar=no' );
            child.moveTo( 0, 0 );
			if(height == null && width == null)
			{
	            child.resizeTo( screen.availWidth, screen.availHeight );
	         }
        }    
        
        /*function zoomImage(ele)
        {
	        document.getElementById('lightimg').src=document.getElementById(ele).src;
			document.getElementById('fade').style.height=document.body.clientHeight;
			var light = document.getElementById('light');
			var st = document.documentElement.scrollTop;
			var sh = document.documentElement.scrollHeight;
			var ch = document.documentElement.clientHeight;
			document.getElementById('light').style.display='block';
			light.style.top = st + ((ch - (screen.height/2))/2);
			document.getElementById('fade').style.display='block';
        }*/
    
// Move From PrintPage.jsp

		function fn_print()
        {
            window.print();
            window.close();
        }    

//From reportChildWindowPopup.js
		
		function openDrillDownReport(url)
		{
			var dt = new Date();
			wndName = "hdiits" + dt.getMilliseconds();
			var childWindow = window.open(url,wndName,'width=610, height=280, top=0, left=0, resizable=no, titlebar=no, menubar=no, scrollbars=yes, toolbar=no');
			childWindow.moveTo( 0, 0 );
		    childWindow.resizeTo( screen.availWidth, screen.availHeight );
		    childWindow.focus();    
		    
		    win = top;
			win.opener = top;	
			win.close ();	
		}
	//   Added by Nikunj Anand (602710)
		
		 function sendReportAsCommunique(reportcode,format)
		  {
	 
			var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen';
			var url='hdiits.htm?actionFlag=FMS_viewNewCommunique&reportCode='+reportcode+'&format='+format;
			window.open(url,'sendascomm',urlStyle);
			
		  }
