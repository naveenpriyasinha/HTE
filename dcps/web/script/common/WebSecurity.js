
//*******************************************************************
//Purpose	        : This function checks the given strVal for cross site scripting(CSS) tags.
//Input	            : Input for this funtion is the strVal contain safe input or cross site scripting(CSS)tag.
//Output	        : This function will returns true if the field value is sate input or else if CSS returns false
//Limitation        : 
//Developer Name    : Narender E
//Date              : 08/07/2005.
//*******************************************************************
function checkForCSS(strVal)                                        
{ 
 //CSS - Cross site scripting                                     // Example are below for each regular expression
  var regexpforHTMLTag1=/(<|&#60|u003C)\s*(\S+)\s*[^>]*\s*(>|&#62|u003E)(.*)(<|&#60|U003C)\/\s*\2\s*(>|&#62|u003E)/i; //<script> <//script> <html> </html>
  var regexpforHTMLTag2=/(<|&#60|u003C)\s*(\S+)\s*([^>]*)\s*(>|&#62|u003E)/i;                //<font face="Arial, Serif" size="+2" color="red">
  var regexpforXMLTag=/((<|&#60|u003C).[^(><.)]+(>|&#62|u003E))/i;                           //<servlet-name attr1=value attr2=value />
  var regexpForEqualVal=/(\s*\w+\s*)=\1/i;                                                   //link=1=1

  //alert(strVal);
 if(regexpforHTMLTag2.test(strVal) || regexpforHTMLTag1.test(strVal) || 
	regexpforXMLTag.test(strVal) || regexpForEqualVal.test(strVal) || !sqlInjection(strVal))
 {
   //alert(">> UnSafe Input <<");
   return false;
 }
 else
 {
   // alert("Safe Input");
   return true;
 }
}


//*******************************************************************
//Purpose	        : This function checks the given strVal for SQL Injection.
//Input	            : Input for this funtion is the strVal contain safe input or SQL Injection.
//Output	        : This function will returns true if the field value is sate input or else if CSS returns false
//Limitation        : 
//Developer Name    : Narender E
//Date              : 18/08/2005.
//*******************************************************************
function sqlInjection(strVal)
{
   var regexpforMETACHAR1= /(\%27)|(&#32)|(u0027)|(\')|(\-\-)|(\%23)|(&#35)|(u0023)|(#)/i;  //Regex for detection of SQL meta-characters
   var regexpforMETACHAR2= /((\%3D)|(&#61)|(u003D)|(=))[^\n]*((\%27)|(&#32)|(u0027)|(\')|(\-\-)|(\%3B)|(&#59)|(u003B)|(;))/i;  //Modified regex for detection of SQL meta-characters
   var regexpforORclause= /\w*((\%27)|(&#32)|(u0027)|(\'))(\s*)((\%6F)|(&#111)|(u006F)|o|(\%4F)|(&#79)|(u004F))((\%72)|(&#114)|(u0072)|r|(\%52)|(&#82)|(u0052))/i; //Regex for typical SQL Injection attack using OR
   var regexpforSQLwords= /((\%27)|(&#32)|(u0027)|(\'))(\s*)(union|select|insert|update|delete|drop)/i; //Regex for detecting SQL Injection with the UNION,SELECT,INSERT,UPDATE,DELETE,DROP keyword
   var regexpforMsSQL= /exec(\s|\+)+(s|x)p\w+/i;      //Regex for detecting SQL Injection attacks on a MS SQL Server

	 if( regexpforMETACHAR1.test(strVal) || regexpforMETACHAR2.test(strVal) ||
	     regexpforORclause.test(strVal) || regexpforSQLwords.test(strVal) ||
		 regexpforMsSQL.test(strVal))
	 {
	   return false;
	 }
	 else
	 {
	   return true;
	 }
}