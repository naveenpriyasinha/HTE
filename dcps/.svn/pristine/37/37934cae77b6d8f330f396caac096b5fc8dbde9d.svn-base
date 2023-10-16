			function placeFloat(control)
            {      
              var iChars = "\"`~!@#$%^&*:\\\';<>?/|[]{}()1234567890";
              var value="";
              var valid=true;

              for (var i=0; i<control.value.length;i++) 
              {              
                 if (iChars.indexOf(control.value.charAt(i))==-1) 
                 {
                    value=value+control.value.charAt(i);
                 }               
                 else
                    valid=false;
              }     

              if(!valid)
              {
                  alert("Special Character and number  does not allowed")
                  control.value=value;
                  control.focus();
                  return false; 
              }
              return true; 
         }
			
   

 
function checkSplChars(val)
{

var iChars = "!@#$%^&*()+=-[]\\\';,/{}|\":<>?";

  for (var i = 0; i < val.length; i++) 
  {
  	if (iChars.indexOf(val.charAt(i)) != -1) 
  	{
  	  
      return false;
  	}
  }
  	return true;
}



function checkSplCharExceptArg(control,s)
{
	
	/*if(document.getElementsByName("gpfAcctNo").value=="")
	{
		//document.getElementsByName("gradeName").disable=true;
		document.forms[0].gradeName.disable=true;
	}*/
	if (document.forms[0].gpfAcctNo.value!="")
	{
		alert("Set the appropriate Class in map with the GPF account")
	}
	if(document.forms[0].gpfAcctNo.value=="")
	{
		//document.getElementsByName("gradeName").disable=true;
		if (xmlHttp.readyState == 4) {    
			
			if (xmlHttp.status == 200) {	
			 		
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
               
				var empMapping = XMLDocForAjax.getElementsByTagName('empMapping');	
				
		document.forms[0].gradeName.value=empMapping[0].childNodes[10].text;
		document.forms[0].gradeName.disabled=true;
			}
		}
	}
	else
	{
		document.forms[0].gradeName.disabled=false;
	}
	
    var iChar = "!@#$%^&*()+=-[]\\\';,/{}|\":<>?.~`";
    for(i=0;i<s.length;i++)
    if((iChar.indexOf(s.charAt(i))>-1))
     iChar=iChar.replace(s.charAt(i),"");
    for (var i = 0; i < control.value.length;i++) 
  	if (iChar.indexOf(control.value.charAt(i)) != -1) 
  	{
  	  		
  	  alert("Special Character does not allowed")	
  	  control.value="";
  	  control.focus();
      return false;
  	}
    
  	return true;
  
}


function checkSplChar(control)
{
var iChars = "!@#$%^&*()+=-[]\\\';,/{}|\":<>?.~`";

  for (var i = 0; i < control.value.length; i++) 
  {
  	if (iChars.indexOf(control.value.charAt(i)) != -1) 
  	{
  	  alert("Special Character does not allowed")	
  	  control.value="";
  	  control.focus();
      return false;
  	}
  }
  	return true;
}

function trim(s) 
{
// Remove leading spaces and carriage returns
//  s = s.replace(/&nbsp;/gi,'');

 while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);   
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}

function checkAlpha(val)
{
 var len = val.length;
 for(var i = 0;i<len; i++)
 {
  if(val.charAt(i) < 'A' || val.charAt(i) > 'Z' && val.charAt(i) <'a' || val.charAt(i)>'z')
  {
    return false;
  }
 }
}

function checkSplCharWithoutDashAndDot(control)
{

var iChars = "!@#$%^&*()+=[]\\\';,/{}|\":<>?~";

  for (var i = 0; i < control.value.length; i++) 
  {
  	if (iChars.indexOf(control.value.charAt(i)) != -1) 
  	{
  	  alert("Special Character does not allowed")	
  	  control.value="";
  	  control.focus();
      return false;
  	}
  }
  	return true;
}
