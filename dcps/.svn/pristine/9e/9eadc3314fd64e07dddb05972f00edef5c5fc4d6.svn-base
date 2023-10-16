var personKey;
function getPersonInfo(personId,ctxPath,Pkey)
{
	personKey=Pkey;
	
	try
	    {   
	    	xmlHttp=new XMLHttpRequest();    
	    }
		catch (e)
		{   
			try
      		{
      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      		   
      		}
		    catch (e)
		    {
		    alert('error');
		          try
        		  {
            	          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
            	          
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              
			              return false;        
			      }
			      
			 }
			 
        }
        
         var url = ctxPath+"/ifms.htm?actionFlag=getPersonXML&personId="+personId; 
         xmlHttp.open("POST", encodeURI(url) , false);
		
		xmlHttp.onreadystatechange = processResponsePersonInfo;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
}

function processResponsePersonInfo()
		{
			
			
			if (xmlHttp.readyState == 4) 
			{     
				
				if (xmlHttp.status == 200) 
				{          
					    var xmlStr = xmlHttp.responseText;
					    var temp =xmlStr.split('$$$ERROR_XML$$$');
					    var strXMLDoc;
					    var strPersonArr;
					    var strAddressArr;
					    var strContactArr;
					    var strAllXML;
					    var strPersonXML=null;
					    var strAddressXML=null;
					    var strContactXML=null;
					    var personDOM=null;
					    var addressDOM=null;
					    var contactDOM=null;
					     if(temp.length == 1)
					     {
					      
					       if(xmlStr!= null)
					       {
					     strXMLDoc= xmlStr.split('$$$START_XML$$$');
					      strAllXML = strXMLDoc[1];
					       }
					    
					      if(strAllXML!=null)
					      {
					    strPersonArr = strAllXML.split('$$$ADDRESS_XML$$$'); 
					    strPersonXML = strPersonArr[0];
					    }
					      if(strPersonArr[1]!= null)
					      {
					    strAddressArr= strPersonArr[1].split('$$$CONTACT_XML$$$');
					    strAddressXML= strAddressArr[0];
					      }
					     
					      if(strAddressArr[1]!= null)
					      {
					   strContactArr= strAddressArr[1].split('$$$END_XML$$$');
					   strContactXML= strContactArr[0];
					    }
					     
					    
					    
					     if(strPersonXML!= null)
					     {
					     
					     personDOM = getDOMFromXML(strPersonXML);
					     }
					     if(strAddressXML!=null)
					     {
					     addressDOM= getDOMFromXML(strAddressXML);
					     }
					     if(strContactXML!=null)
					     {
					     contactDOM= getDOMFromXML(strContactXML);
					     }
					      if(personDOM!=null)
					      {
					     showPersonDetails(personDOM);
					     }
					     if(addressDOM!=null)
					     {
					    
					     showAddressDetails(addressDOM);
					     }
					     if(contactDOM!=null)
					     {
	                     showContactDetails(contactDOM);
	                     }
	                   }
	                   else
	                   {
	                    var tempErrorXML = temp[1];
	                    var errorXMLArr = temp[1].split('$$$END_ERROR_XML$$$');
	                    var strErrorXML = errorXMLArr[0];
	                    
	                    var errorDOM = getDOMFromXML(strErrorXML);
	                    showErrorMsg(errorDOM);
	                   }

				}
				else 
				{  
			
					alert("ERROR");
					
				}
			}
   }
   
   function showErrorMsg(errorDOM)
   {
      var errEntries =  errorDOM.getElementsByTagName('errorMsg');
         for (var i = 0; i < errEntries.length; i++) 
	     {
	      var errorMsg =   errEntries[0].childNodes[0].text;
	      alert(errorMsg);
	     }
   }
	function showPersonDetails(personDOM)
	{
	   
	     var personEntries = personDOM.getElementsByTagName('person');	
	     
	    for (var i = 0; i < personEntries.length; i++) 
	     {  
	      		
		var firstName      = personEntries[0].childNodes[0].text;
		
		var middleName     = personEntries[0].childNodes[1].text;
		var lastName       = personEntries[0].childNodes[2].text;
		
		var dateOfBirth    = personEntries[0].childNodes[3].text;
		var age            = personEntries[0].childNodes[4].text;
		var gender         = personEntries[0].childNodes[5].text;
		var religion       = personEntries[0].childNodes[6].text;
		var religionOther  = personEntries[0].childNodes[7].text;
		var caste          = personEntries[0].childNodes[8].text;
		var category       = personEntries[0].childNodes[9].text;
		var occupation     = personEntries[0].childNodes[10].text;
		var occupationOther= personEntries[0].childNodes[11].text;
		var maritalStatus  = personEntries[0].childNodes[12].text;
		var isAdult        = personEntries[0].childNodes[13].text;
		var nationality    = personEntries[0].childNodes[14].text;
		
		
		
		
		
		 }
		 
		if(document.getElementById('firstName'+personKey)!= null)
		{
		  if(firstName.length > 0)
		  {
           document.getElementById('firstName'+personKey).innerHTML=firstName;	 
          }
          else
          {
           document.getElementById('firstName'+personKey).innerHTML="&nbsp;";
          }
           
        }
       if(document.getElementById('middleName'+personKey)!= null)
       
		{ 
              if(middleName.length > 0)
		      {
             document.getElementById('middleName'+personKey).innerHTML=middleName;	
              }
             else
             {
           document.getElementById('middleName'+personKey).innerHTML="&nbsp;";
             }
        }
        if(document.getElementById('lastName'+personKey)!= null)
        {
		         if(lastName.length > 0)
				      {
		     document.getElementById('lastName'+personKey).innerHTML=lastName;
		           }  else
		             {
		           document.getElementById('lastName'+personKey).innerHTML="&nbsp;";
		             }
           	
        } 
        if(document.getElementById('dateOfBirth'+personKey)!= null)
        {
          if(dateOfBirth.length > 0)
			 {
     		document.getElementById('dateOfBirth'+personKey).innerHTML=dateOfBirth;	
              } else
		       {
		           document.getElementById('dateOfBirth'+personKey).innerHTML="&nbsp;";
		       }
		 }      
         if(document.getElementById('age'+personKey)!= null)
        {
          if(age.length > 0)
			 {
     		document.getElementById('age'+personKey).innerHTML=age;	
             }
             else
             {
             document.getElementById('age'+personKey).innerHTML="&nbsp;";
             }
       }
       if(document.getElementById('gender'+personKey)!=null)
       {
              if(gender.length > 0)
			 {
     			document.getElementById('gender'+personKey).innerHTML=gender;	
             }
             else
             {
                document.getElementById('gender'+personKey).innerHTML="&nbsp;";
             }
             
       }
        if(document.getElementById('religion'+personKey)!=null)
       {
            if(religion.length > 0)
			 {  
             document.getElementById('religion'+personKey).innerHTML=religion;
            }
            else if(religionOther.length > 0)
            {
             document.getElementById('religion'+personKey).innerHTML=religionOther;
            }
            else
            {
            document.getElementById('religion'+personKey).innerHTML="&nbsp;";
            }
       }
      
        if(document.getElementById('caste'+personKey)!=null)
       {	
          if(caste.length > 0)
			 {
     		document.getElementById('caste'+personKey).innerHTML=caste;	
           }
           else
           {
           document.getElementById('caste'+personKey).innerHTML="&nbsp;";	
           }
      }
      if(document.getElementById('category'+personKey)!=null)
       {
          if(category.length > 0)
			 {
     		document.getElementById('category'+personKey).innerHTML=category;	
     		}
     		else
           {
           document.getElementById('category'+personKey).innerHTML="&nbsp;";	
           }
      }
       if(document.getElementById('occupation'+personKey)!=null)
       {
	       if(occupation.length > 0)
	        { 
	     document.getElementById('occupation'+personKey).innerHTML=occupation;	
	        }
	        else if(occupationOther.length > 0)
	        {
	     document.getElementById('occupation'+personKey).innerHTML=occupationOther;   
	        }
	        else
	        {
	        document.getElementById('occupation'+personKey).innerHTML="&nbsp;";
	        }
       }
    
      
       if(document.getElementById('maritalStatus'+personKey)!=null)
       {
          if(maritalStatus.length > 0)
	        {     
     document.getElementById('maritalStatus'+personKey).innerHTML=maritalStatus;	
           }
           else
           {
     document.getElementById('maritalStatus'+personKey).innerHTML="&nbsp;";         
           }
       }
       if(document.getElementById('isAdult'+personKey)!=null)
       {
          if(isAdult.length > 0)
	        {   
     		document.getElementById('isAdult'+personKey).innerHTML=isAdult;
          }   else
           {
     document.getElementById('isAdult'+personKey).innerHTML="&nbsp;";         
           }
          
       }
        if(document.getElementById('nationality'+personKey)!=null)
       {	
         if(nationality.length > 0)
	        {   
     		document.getElementById('nationality'+personKey).innerHTML=nationality;
           }
           else
           {
           document.getElementById('nationality'+personKey).innerHTML="&nbsp;";  
           }
       }	
        var personTblId ='personTbl'+personKey;
        var fieldsetId ='fieldset'+personKey;
         document.getElementById(fieldsetId).style.display='';
     	document.getElementById(personTblId).style.display='';
 
	}
     function showAddressDetails(addressDOM)
	{
	     var addressEntries = addressDOM.getElementsByTagName('address');	
	     if(addressEntries.length == 0)
	     {
	      document.getElementById('addressTitle'+personKey).innerHTML="&nbsp;";
	      document.getElementById('addressDescription'+personKey).innerHTML="&nbsp;";
	     
	     }
	     else
	     {
	    for (var i = 0; i < addressEntries.length; i++) 
	     {    			
		var addressTitle       = addressEntries[0].childNodes[0].text;
		var addressDescription = addressEntries[0].childNodes[1].text;
		
		 if(document.getElementById('addressTitle'+personKey)!= null)
		 {
		    if(addressTitle.length > 0)
		    {
		   document.getElementById('addressTitle'+personKey).innerHTML="<b>"+addressTitle+":";
		   }
		   else
		   {
		   document.getElementById('addressTitle'+personKey).innerHTML="&nbsp;";
		   }
		 }
	     if(document.getElementById('addressDescription'+personKey)!= null)
		 {
		   if(addressDescription.length > 0)
		   {
		   document.getElementById('addressDescription'+personKey).innerHTML=addressDescription;
		   }
		   else
		   {
		    document.getElementById('addressDescription'+personKey).innerHTML="&nbsp;";
		   }
		 }
		 }
		  var addressTblId ='addressTbl'+personKey;
		  
		  document.getElementById(addressTblId).style.display='';
		} 
		
  
	}
	
	  function showContactDetails(contactDOM)
	{
	     var contactEntries = contactDOM.getElementsByTagName('contact');	
	     if(contactEntries.length == 0)
	     {
	      document.getElementById('contactTitle'+personKey).innerHTML ="&nbsp;";   
	      document.getElementById('residencePhone'+personKey).innerHTML = "&nbsp;";
	       document.getElementById('officePhone'+personKey).innerHTML = "&nbsp;";
	       document.getElementById('mobile'+personKey).innerHTML ="&nbsp;";
	      document.getElementById('email'+personKey).innerHTML = "&nbsp;"; 
	     }
	     else
	     {
	    for (var i = 0; i < contactEntries.length; i++) 
	     {    			
		var contactTitle   = contactEntries[0].childNodes[0].text;
		var residencePhone = contactEntries[0].childNodes[1].text;
		var officePhone    = contactEntries[0].childNodes[2].text;
		var mobile         = contactEntries[0].childNodes[3].text;
	    var email          = contactEntries[0].childNodes[4].text;
	    
	      if(document.getElementById('contactTitle'+personKey)!= null)
	      {
	         if(contactTitle.length > 0)
	         {
	      document.getElementById('contactTitle'+personKey).innerHTML ="<b>"+contactTitle+":";
	         }
	         else
	         {
	      document.getElementById('contactTitle'+personKey).innerHTML ="&nbsp;";   
	         }
	      }
	       if(document.getElementById('residencePhone'+personKey)!= null)
	      {
	        if(residencePhone.length > 0)
	        {
	      document.getElementById('residencePhone'+personKey).innerHTML = residencePhone;
	        }
	        else
	        {
	        document.getElementById('residencePhone'+personKey).innerHTML = "&nbsp;";
	        }
	      }
	        if(document.getElementById('officePhone'+personKey)!= null)
	      {
	         if(officePhone.length > 0)
	         {
	      document.getElementById('officePhone'+personKey).innerHTML=officePhone;
	         }
	         else
	         {
	       document.getElementById('officePhone'+personKey).innerHTML = "&nbsp;";   
	         }
	      }
	        if(document.getElementById('mobile'+personKey)!= null)
	      {
	          if(mobile.length > 0)
	         {
	      document.getElementById('mobile'+personKey).innerHTML =mobile;
	         }
	         else
	         {
	       document.getElementById('mobile'+personKey).innerHTML = "&nbsp;";    
	         }
	      }
	         if(document.getElementById('email'+personKey)!= null)
	      {
	         if(email.length > 0)
	         {
	        document.getElementById('email'+personKey).innerHTML =email;
	         }
	         else
	         {
	         document.getElementById('email'+personKey).innerHTML = "&nbsp;"; 
	         }
	      }
	      
	      
		 }
		 var contactTblId='contactTbl'+personKey;
		 
	     document.getElementById(contactTblId).style.display='';  
	     }
	}