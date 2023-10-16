function getVictimNamess()
{ 
clearVictim();
xmlHttp=GetXmlHttpObject();
if (xmlHttp==null)
  {
  alert ("Your browser does not support AJAX!");
  return;
  } 
  
  var url; 
  var uri='';
  url= uri+'&caseId='+document.frmChehra.caseId_stolen.value ;
  //url= uri+'&'+document.forms[0].firNo.name+'='+document.forms[0].firNo.value ;
  var actionf=document.frmChehra.actionvictimnames.value
  uri='ifms.htm?actionFlag='+actionf;
  url=uri+url;    

xmlHttp.onreadystatechange=stateChangeds;
xmlHttp.open("POST",encodeURI(url),true);
xmlHttp.send(null);
disables();
}

function enables()
{
	var totalElements= document.forms[0].elements.length
	for(var i=0;i<totalElements;i++)
	{
		document.forms[0].elements[i].disabled=false
	}
}

function disables()
{
	var totalElements= document.forms[0].elements.length
	for(var i=0;i<totalElements;i++)
	{
			document.forms[0].elements[i].disabled=true
	}
}

function stateChangeds() 
{ 
	if (xmlHttp.readyState==complete_state)
	{ 
			enables();
			var z=document.getElementById("victimList");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
				    var entries = XMLDoc.getElementsByTagName('personMapped');
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					val=entries[i].childNodes[0].text;   
	     					text=entries[i].childNodes[1].text;   
	     					var y=document.createElement('option');
		 					y.text=text;
							y.value=val;
							try
	   						{
	   							z.add(y,null); 	   						   							
	   						}
	 						catch(ex)
	   						{
	   			 	  		    z.add(y); 
	   						}    					
	     					
	           			}	
	}
}

function getLinkDetails()
{ 

xmlHttp=GetXmlHttpObject();
if (xmlHttp==null)
  {
  alert ("Your browser does not support AJAX!");
  return;
  } 
  
  var z=document.getElementById("viscView");z.style.display='none';
  z=document.getElementById("viscIns");z.style.display='none';
  z=document.getElementById("deaddView");z.style.display='none';
  z=document.getElementById("deaddIns");z.style.display='none';
  z=document.getElementById("deadBhoView");z.style.display='none';
  z=document.getElementById("deadBhoIns");z.style.display='none';
  z=document.getElementById("inj");z.style.display='none';
  z=document.getElementById("injIns");z.style.display='none';
  var url; 
  var uri='';
  url= uri+'&'+document.forms[0].victimList.name+'='+document.forms[0].victimList.value ;
  var actionf=document.forms[0].actionvictimdetails.value
  actionf='linkDetails';
  uri='ifms.htm?actionFlag='+actionf;
  url=uri+url;           
xmlHttp.onreadystatechange=processResponses;
xmlHttp.open("POST",encodeURI(url),true);
xmlHttp.send(null);
disables();
}

function processResponses() 
{ 
	if (xmlHttp.readyState==complete_state)
	{ 
	
		enables();
		// call
		var x1;
	    var y1;
	    var z1;
		var XMLDoc=xmlHttp.responseXML.documentElement;
		
		var entries12 = XMLDoc.getElementsByTagName('injlink');
		var x=entries12[0].childNodes[0].text;
        //	var z=document.getElementById("visc");
	    // 	if(z==null)alert("var deadBHo is null")
        z1=document.getElementById("inj");
        y1=document.getElementById("injIns");
	     	if(x=='y')
	     	{	     		
	     				z1.href="ifms.htm?actionFlag=injuryDetails&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;
	     				z1.style.display='';
	     				y1.style.display='none';
	      	}
	     	else
	     	{
	     				y1.href="ifms.htm?actionFlag=injuryDetails&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;
	     				y1.style.display='';
	     				z1.style.display='none';
	     	}		
		
    	var entries1 = XMLDoc.getElementsByTagName('personDetails');
    	
		if(entries1.length>0)
		{
            document.frmChehra.firName.value=entries1[0].childNodes[0].text; 
            document.frmChehra.firFatherHushbandName.value=entries1[0].childNodes[1].text;
            document.frmChehra.firSurname.value=entries1[0].childNodes[2].text;
        }
        var entries2 = XMLDoc.getElementsByTagName('visceral');
        if(entries2.length>0)
        {
        	var x=entries2[0].childNodes[0].text;
        //	var z=document.getElementById("visc");
	    // 	if(z==null)alert("var deadBHo is null")
	    x1=entries2[0].childNodes[0].text;
        z1=document.getElementById("viscView");
        y1=document.getElementById("viscIns");
	     	if(x=='y')
	     	{
	     		var entries3 = XMLDoc.getElementsByTagName('vvlinlk');
	     	
       			if(entries3.length>0)
        		{ 
	     			if(z1==null)alert("var deadBHo is null")
	     			if(x1=='y')
	     			{
	     				z1.href="ifms.htm?actionFlag=populateVisceralAnalysisDtl&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;
	     				z1.style.display='';
	     				y1.style.display='none';
	      			}
	     			else
	     			{
	     				y1.href="ifms.htm?actionFlag=populateVisceralAnalysisDtl&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;
	     				y1.style.display='';
	     				z1.style.display='none';
	     			}
        		}
	      	}
	     	else
	     	{
	     		z1.style.display='none';
	     		y1.style.display='none';
	     	}
        }
        
        
        var entries6 = XMLDoc.getElementsByTagName('personInjDec');
	    var flag1=true;
	    var flag2=true;
	    var flag3=true;
	   
	    if(entries6.length>0)
	    {
	    for ( var i = 0 ; i < entries6.length ; i++ )
	    {
	       if(entries6[i].childNodes[0].text=='Dying Declaration')
	     	{
	     	//	var z=document.getElementById("dyingDec");
	     	//	if(z==null)alert("var dyingDec is null")
	     	x1=entries6[0].childNodes[0].text;
	        			 z1=document.getElementById("deaddView");
	        			 y1=document.getElementById("deaddIns");
	     	 	if(entries6[i].childNodes[1].text=='Y'&& flag1==true)
	     		{
	     			var entries7 = XMLDoc.getElementsByTagName('ddlinlk');
	       			if(entries7.length>0)
	        		{
	        		
	        			 
		     			if(z1==null)alert("var deadBHo is null")
		     			if(x1=='y')
		     			{
		     				z1.href="ifms.htm?actionFlag=ps-dyingDeclaration&firNo="+document.frmChehra.caseId_stolen.value+"&name="+document.frmChehra.firName.value+"&fatherHusbandName="+document.frmChehra.firFatherHushbandName.value+"&surName="+document.frmChehra.firSurname.value;			     		
		     				z1.style.display='';
		     				y1.style.display='none';
		      			}
		     			else
		     			{
		     				y1.href="ifms.htm?actionFlag=ps-dyingDeclaration&firNo="+document.frmChehra.caseId_stolen.value+"&name="+document.frmChehra.firName.value+"&fatherHusbandName="+document.frmChehra.firFatherHushbandName.value+"&surName="+document.frmChehra.firSurname.value;			     		
		     				y1.style.display='';
		     				z1.style.display='none';
		     			}
	        		}
	     			
	     			flag1=false;
	     		}
	     		if(entries6[i].childNodes[1].text=='N'&& flag1==true)
	     		{
	     			z1.style.display='none';
		     		y1.style.display='none';
	     		}
	     	}
	     	if(entries6[i].childNodes[0].text=='Postmortem' && flag2==true)
	     	{
	     //	var z=document.getElementById("postm");
	     //	if(z==null)alert("var postm is null")
	      x1=entries6[0].childNodes[0].text;
	        			 z1=document.getElementById("postmView");
	        			 y1=document.getElementById("postmIns");
	        	if(entries6[i].childNodes[1].text=='Y')
	     		{	
	     		    var entries8 = XMLDoc.getElementsByTagName('pmlinlk');
	       			if(entries8.length>0)
	        		{
	        		
	        			
		     			if(z1==null)alert("var deadBHo is null")
		     			if(x1=='y')
		     			{
		     				z1.href="ifms.htm?actionFlag=populatePostmortemDtl&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;		
		     				z1.style.display='';
		     				y1.style.display='none';
		      			}
		     			else
		     			{
		     				y1.href="ifms.htm?actionFlag=populatePostmortemDtl&caseId="+document.frmChehra.caseId_stolen.value+"&personId="+document.frmChehra.victimName.value;		
		     				y1.style.display='';
		     				z1.style.display='none';
		     			}
	        		}
	     			flag2=false;
	     		}
	     		if(entries6[i].childNodes[1].text=='N'&& flag2==true)
	     		{
	     			z1.style.display='none';
		     		y1.style.display='none';
	     		}
	     	}
	     	if(entries6[i].childNodes[0].text=='person dead'&&flag3==true)
	     	{ 
	    // 	var z=document.getElementById("deadBHo");
	    // 	if(z==null)alert("var deadBHo is null")
	     x1=entries6[0].childNodes[0].text;
	        			 z1=document.getElementById("deadBhoView");
	        			 y1=document.getElementById("deadBhoIns");
	        if(entries6[i].childNodes[1].text=='Y')
	        {	 
	        	var entries9 = XMLDoc.getElementsByTagName('holinlk');
	       		if(entries9.length>0)
	        	{
		     			if(z1==null)alert("var deadBHo is null")
		     	    	if(x1=='y')
		     			{
		     				z1.href="ifms.htm?actionFlag=ps-deadBody&firNo="+document.frmChehra.caseId_stolen.value+"&name="+document.frmChehra.firName.value+"&fatherHusbandName="+document.frmChehra.firFatherHushbandName.value+"&surName="+document.frmChehra.firSurname.value;		
		     				z1.style.display='';
		     				y1.style.display='none';
		      			}
		     			else
		     			{
		     				y1.href="ifms.htm?actionFlag=ps-deadBody&firNo="+document.frmChehra.caseId_stolen.value+"&name="+document.frmChehra.firName.value+"&fatherHusbandName="+document.frmChehra.firFatherHushbandName.value+"&surName="+document.frmChehra.firSurname.value;		
		     				y1.style.display='';
		     				z1.style.display='none';
		     			}
	        		}       	
	           
	     		flag3=false;
	     		}
	     		if(entries6[i].childNodes[1].text=='N'&& flag3==true)
	     		{
				 z1.style.display='none';
 	     		y1.style.display='none';
	     		}
	     	}			
	    
	      }
	    }
        
     }
}