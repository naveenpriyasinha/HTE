var editFlag=false;

function hideAddress(a1,a2,targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName)
{
	document.getElementById('othertbl'+addrName).style.display='none';	
	document.getElementById(a1).style.display='';
	document.getElementById(a2).style.display='none';
	  if(editFlag==false)
	  {
	  		
			populateFirstLevel(targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName);	
			closeAddress(addrName,false,false);
	  }		
	
	}
function showAddress(a1,a2,targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName)
{
	document.getElementById('othertbl'+addrName).style.display='none';	
	document.getElementById(a2).style.display='';
	document.getElementById(a1).style.display='none';
	 if(editFlag==false)
	  {
	populateFirstLevel(targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName);
	closeAddress(addrName,false,false);
	  }
	
}
function checkVillageValidation(addrId)
{
	var x=document.getElementsByName(addrId);
	
   if(x[1].checked == true)
   
   {
  
   return true;
   }
   else
   {
  
   return false;
   }

}
function checkCityValidation(addrId)
{
	var x=document.getElementsByName(addrId);
   if(x[0].checked==true)
   {return true;
   }
   else
   {
   return false;
   }

}
var nxtCombo;
var addrName;
var glbHtmFile;
function populateFirstLevel(firstLevelId,actionFlag,ctxPath,sourceId,sourceVal,uniqueName,landmarkList)
{
  
		var isAsynch=true;
		if(editFlag==true)
	    {
	    isAsynch=false;
	    }
		nxtCombo=firstLevelId;
		addrName=uniqueName;
	var htmFile=document.getElementById('addrHtmName'+uniqueName).value;
	glbHtmFile=htmFile;
	try
	    {   
	    	xmlHttpFirstLevel=new XMLHttpRequest();    
	    }
		catch (e)
		{   
			try
      		{
      		    xmlHttpFirstLevel=new ActiveXObject("Msxml2.XMLHTTP"); 
      		       
      		}
		    catch (e)
		    {
		          try
        		  {
            	          xmlHttpFirstLevel=new ActiveXObject("Microsoft.XMLHTTP");        
            	          
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              
			              return false;        
			      }
			      
			 }
			 
        }
        var z=document.getElementById(firstLevelId);
        var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+sourceVal;   
		 
		
		
		xmlHttpFirstLevel.open("POST", encodeURI(url) , isAsynch);
		
		xmlHttpFirstLevel.onreadystatechange =  processResponseFirstLevel;
		xmlHttpFirstLevel.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttpFirstLevel.send(encodeURIComponent(null));
		
}
function populateNextLevel(srcLevelId,nextLevelId,actionFlag,ctxPath,sourceId,uniqueName) {
    
      var isAsynch=true;
		if(editFlag==true)
	    {
	    isAsynch=false;
	    }
      nxtCombo=nextLevelId;
      addrName=uniqueName;
  var srcObj = document.getElementById(srcLevelId);
  var htmFile=document.getElementById('addrHtmName'+uniqueName).value;
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
        var z=document.getElementById(nextLevelId);
        var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+srcObj.value;   
		
		
		
		xmlHttp.open("POST", encodeURI(url) , isAsynch);
		
		xmlHttp.onreadystatechange = processResponseNextLevel;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
		
		

}
function populateLandmarks(nextLevelId,actionFlag,ctxPath,sourceId,lookupName)
{

nxtCombo=nextLevelId;
var isAsynch=true;
		if(editFlag==true)
	    {
	    isAsynch=false;
	    }
	try
	    {   
	    	xmlHttpLandmark=new XMLHttpRequest();    
	    }
		catch (e)
		{   
			try
      		{
      		    xmlHttpLandmark=new ActiveXObject("Msxml2.XMLHTTP"); 
      		       
      		}
		    catch (e)
		    {
		          try
        		  {
            	          xmlHttpLandmark=new ActiveXObject("Microsoft.XMLHTTP");        
            	          
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              
			              return false;        
			      }
			      
			 }
			 
        }
        var z=document.getElementById(nextLevelId);
         var url = ctxPath+glbHtmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+lookupName;   
		
		
		if(z.length == 1)
		{
		xmlHttpLandmark.open("POST", encodeURI(url) , isAsynch);
		
		xmlHttpLandmark.onreadystatechange = processResponseLandmarks;
		xmlHttpLandmark.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttpLandmark.send(encodeURIComponent(null));
		}
		

}
function processResponseNextLevel()
		{
			
			
			if (xmlHttp.readyState == 4) 
			{     
				
				if (xmlHttp.status == 200) 
				{          
				
					var text;
						
		            	var z=document.getElementById(nxtCombo);
		            	
		            	
				    	var XMLDoc=xmlHttp.responseXML.documentElement;
				    	   
				    	if(XMLDoc!= null)
				    	{
				    	     
				    	       var districtList=document.getElementById("cmbDistrict"+addrName);
				    	       
				    		  
				    		 if(z.length > 1)
				    		  {
				    		     clearList(z);
				    		  }
				    	 if(districtList!= null)
				    	 {	   
				    	 		var talukaList=document.getElementById("cmbTaluka"+addrName);
				    		   var villageList=document.getElementById("cmbVillage"+addrName);
				    		   var vLandmarks=document.getElementById("cmbVillageLandmark"+addrName);
				    		  
				    		if(districtList.value=='Select')
				    		{
				    		   clearList(talukaList);
				    		   clearList(villageList);  
				    		   
				    		   talukaList.options[0].selected="selected";
				    		   villageList.options[0].selected="selected";
				    		   
				    		}
				    		else if(talukaList.value=='Select')
				    		{
				    			clearList(villageList);  
				    		    
				    		    villageList.options[0].selected="selected";
				    		  
				    		}
				    		
				    	
				    	 }
				    	 
				    	var entries = XMLDoc.getElementsByTagName('element');	
				    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
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
				else 
				{  
			
					alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
			}
}

function processResponseFirstLevel()
		{
			
			
			if (xmlHttpFirstLevel.readyState == 4) 
			{     
				
				if (xmlHttpFirstLevel.status == 200) 
				{          
				  
					var text;
						
		            	var z=document.getElementById(nxtCombo);
		            	
		            	
				    	var XMLDoc=xmlHttpFirstLevel.responseXML.documentElement;
				    	   
				    	if(XMLDoc!= null)
				    	{
				    	     
				    	    
				    		 if(z.length > 1)
				    		  {
				    		     clearList(z);
				    		  }
				    		
				    	var entries = XMLDoc.getElementsByTagName('element');	
				    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
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
				else 
				{  
			
					alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
				
			}
			
}
function processResponseLandmarks()
		{
			
			
			if (xmlHttpLandmark.readyState == 4) 
			{     
				
				if (xmlHttpLandmark.status == 200) 
				{          
				
					var text;
						
		            	var z=document.getElementById(nxtCombo);
		            	
		            	
				    	var XMLDoc=xmlHttpLandmark.responseXML.documentElement;
				    	   
				    	if(XMLDoc!= null)
				    	{
				    	     
				    	      
				    		 if(z.length > 1)
				    		  {
				    		     clearList(z);
				    		  }
				    	
				    	
				    	var entries = XMLDoc.getElementsByTagName('element');	
				    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
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
				else 
				{  
			
					alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
			}
}

function addressParameters(addrName,lookUpName)
{
	var addressParams = new Array();

	addressParams[0]="rdoAddress"+addrName;
	var radioArray = document.getElementsByName("rdoAddress"+addrName);
	if(radioArray[0].checked == true)
	{
		addressParams[1]="txtCityHouseName"+addrName;
		addressParams[2]="txtSocietyName"+addrName;
		addressParams[3]="txtStreet"+addrName;
		addressParams[4]="txtArea"+addrName;
		addressParams[5]="cmbCity"+addrName;
		addressParams[6]="txtareaCityOtherDetails"+addrName;
		addressParams[7]="cmbCityLandmark"+addrName;
		addressParams[8]="txtCityPincode"+addrName;
		addressParams[9]=addrName;
		addressParams[10]=lookUpName;		
		addressParams[11]="cmbCountry"+addrName;	
		addressParams[12]="cmbState"+addrName;	
		if(document.getElementById('cmbCountry'+addrName).value != 1)
		{
		addressParams[13]="txtState"+addrName;	
		}
	}
	else if(radioArray[1].checked == true)
	{
		addressParams[1]="txtVillageHouseName"+addrName;
		addressParams[2]="txtFaliyu"+addrName;
		addressParams[3]="cmbVillage"+addrName;
		addressParams[4]="cmbTaluka"+addrName;
		addressParams[5]="cmbDistrict"+addrName;
		addressParams[6]="cmbVillageLandmark"+addrName;
		addressParams[7]="txtareaVillageOtherDetails"+addrName;
		addressParams[8]="txtVillagePincode"+addrName;
		addressParams[9]=addrName;
		addressParams[10]=lookUpName;	
		addressParams[11]="cmbCountry"+addrName;	
		addressParams[12]="cmbState"+addrName;	
		if(document.getElementById('cmbCountry'+addrName).value != 1)
		{
		addressParams[13]="txtState"+addrName;	
		}					
	}
	else if(radioArray[2].checked == true)
	{
		addressParams[1]="txtOtherHouseName"+addrName;
		addressParams[2]="txtOtherSocietyName"+addrName;
		addressParams[3]="txtOtherArea"+addrName;
		addressParams[4]="cmbOtherDistrict"+addrName;
		
		addressParams[5]="txtOtherTaluka"+addrName;
		addressParams[6]="txtOtherCityVillage"+addrName;
		addressParams[7]="txtOtherPincode"+addrName;
		addressParams[8]="cmbCountry"+addrName;	
		addressParams[9]="cmbState"+addrName;	
		addressParams[10]=addrName;
		addressParams[11]=lookUpName;
		if(document.getElementById('cmbCountry'+addrName).value != 1)
		{
		addressParams[12]="txtOtherDistrict"+addrName;
		addressParams[13]="txtState"+addrName;	
		}
		
	}
	else
	{
		addressParams[1]="txtCityHouseName"+addrName;
		addressParams[2]="txtSocietyName"+addrName;
		addressParams[3]="txtStreet"+addrName;
		addressParams[4]="txtArea"+addrName;
		addressParams[5]="cmbCity"+addrName;
		addressParams[6]="txtareaCityOtherDetails"+addrName;
		addressParams[7]="cmbCityLandmark"+addrName;
		addressParams[8]="txtCityPincode"+addrName;	

		addressParams[9]="txtVillageHouseName"+addrName;
		addressParams[10]="txtFaliyu"+addrName;
		addressParams[11]="cmbVillage"+addrName;
		addressParams[12]="cmbTaluka"+addrName;
		addressParams[13]="cmbDistrict"+addrName;
		addressParams[14]="cmbVillageLandmark"+addrName;
		addressParams[15]="txtareaVillageOtherDetails"+addrName;
		addressParams[16]="txtVillagePincode"+addrName;
		
		addressParams[17]=addrName;
		addressParams[18]=lookUpName;	
		
		addressParams[19]="txtOtherHouseName"+addrName;
		addressParams[20]="txtOtherSocietyName"+addrName;
		addressParams[21]="txtOtherArea"+addrName;
		addressParams[22]="cmbOtherDistrict"+addrName;
		
		addressParams[23]="txtOtherTaluka"+addrName;
		addressParams[24]="txtOtherCityVillage"+addrName;
		addressParams[25]="txtOtherPincode"+addrName;		
		
		addressParams[26]="cmbCountry"+addrName;	
		addressParams[27]="cmbState"+addrName;	
		if(document.getElementById('cmbCountry'+addrName).value != 1)
		{
		addressParams[28]="txtOtherDistrict"+addrName;
		addressParams[29]="txtState"+addrName;	
		}
	}

return addressParams;

}
function makeReadOnly(uniqueName)


{

  var addressParams = new Array();
  addressParams[0]="rdoAddress"+uniqueName;
  addressParams[1]="txtCityHouseName"+uniqueName;
  addressParams[2]="txtSocietyName"+uniqueName;
  addressParams[3]="txtStreet"+uniqueName;
  addressParams[4]="txtArea"+uniqueName;
  addressParams[5]="cmbCity"+uniqueName;
  addressParams[6]="txtareaCityOtherDetails"+uniqueName;
  addressParams[7]="cmbCityLandmark"+uniqueName;
  addressParams[8]="txtCityPincode"+uniqueName;

  addressParams[9]="txtVillageHouseName"+uniqueName;
  addressParams[10]="txtFaliyu"+uniqueName;
  addressParams[11]="cmbVillage"+uniqueName;
  addressParams[12]="cmbTaluka"+uniqueName;
  addressParams[13]="cmbDistrict"+uniqueName;
  addressParams[14]="cmbVillageLandmark"+uniqueName;
  addressParams[15]="txtareaVillageOtherDetails"+uniqueName;
  addressParams[16]="txtVillagePincode"+uniqueName;
  
  
  addressParams[17]="txtOtherHouseName"+uniqueName;
  addressParams[18]="txtOtherSocietyName"+uniqueName;
  addressParams[19]="txtOtherArea"+uniqueName;
  addressParams[20]="cmbOtherDistrict"+uniqueName;
  addressParams[21]="txtOtherDistrict"+uniqueName;
  addressParams[22]="txtOtherTaluka"+uniqueName;
  addressParams[23]="txtOtherCityVillage"+uniqueName;
  addressParams[24]="txtOtherPincode"+uniqueName;
  addressParams[25]="cmbCountry"+uniqueName;	
  addressParams[26]="cmbState"+uniqueName;	
  addressParams[27]="txtState"+uniqueName;

  if(document.getElementById(addressParams[0])!= null)
  {
     
     var radioArr = document.getElementsByName(addressParams[0]);
       radioArr[0].disabled=true;
        radioArr[1].disabled=true;
         radioArr[2].disabled=true;
      // document.getElementById("radioTable"+uniqueName).readonly="readonly"; 
     //document.getElementById(addressParams[0]).disabled=true;
  }
  if(document.getElementById(addressParams[1])!= null)
  {
     document.getElementById(addressParams[1]).disabled=true;
    // document.getElementById(addressParams[1]).readonly="readonly";
  }
  if(document.getElementById(addressParams[2])!= null)
  {
     document.getElementById(addressParams[2]).disabled=true;
    //document.getElementById(addressParams[2]).readonly="readonly";
  }
  if(document.getElementById(addressParams[3])!= null)
  {
     document.getElementById(addressParams[3]).disabled=true;
    // document.getElementById(addressParams[3]).readonly="readonly";
  }
  if(document.getElementById(addressParams[4])!= null)
  {
     document.getElementById(addressParams[4]).disabled=true;
     //document.getElementById(addressParams[4]).readonly="readonly";
  }
  if(document.getElementById(addressParams[5])!= null)
  {
     document.getElementById(addressParams[5]).disabled=true;
  }
  if(document.getElementById(addressParams[6])!= null)
  {
     document.getElementById(addressParams[6]).disabled=true;
    // document.getElementById(addressParams[6]).readonly="readonly";
  }
  if(document.getElementById(addressParams[7])!= null)
  {
     document.getElementById(addressParams[7]).disabled=true;
  }
  if(document.getElementById(addressParams[8])!= null)
  {
     document.getElementById(addressParams[8]).disabled=true;
    // document.getElementById(addressParams[8]).readonly="readonly";
  }
  if(document.getElementById(addressParams[9])!= null)
  {
     document.getElementById(addressParams[9]).disabled=true;
    // document.getElementById(addressParams[9]).readonly="readonly";
  }
  if(document.getElementById(addressParams[10])!= null)
  {
     document.getElementById(addressParams[10]).disabled=true;
    // document.getElementById(addressParams[10]).readonly="readonly";
  }
  if(document.getElementById(addressParams[11])!= null)
  {
     document.getElementById(addressParams[11]).disabled=true;
  }
  if(document.getElementById(addressParams[12])!= null)
  {
     document.getElementById(addressParams[12]).disabled=true;
  }
  if(document.getElementById(addressParams[13])!= null)
  {
     document.getElementById(addressParams[13]).disabled=true;
  }
  if(document.getElementById(addressParams[14])!= null)
  {
     document.getElementById(addressParams[14]).disabled=true;
  }
  if(document.getElementById(addressParams[15])!= null)
  {
     document.getElementById(addressParams[15]).disabled=true;
    // document.getElementById(addressParams[15]).readonly="readonly";
  }
   if(document.getElementById(addressParams[16])!= null)
  {
     document.getElementById(addressParams[16]).disabled=true;
    // document.getElementById(addressParams[16]).readonly="readonly";
  }
  
   if(document.getElementById(addressParams[17])!= null) 
   {
   document.getElementById(addressParams[17]).disabled=true;
   }
   if(document.getElementById(addressParams[18])!= null) 
   {
   document.getElementById(addressParams[18]).disabled=true;
   }
   if(document.getElementById(addressParams[19])!= null) 
   {
   document.getElementById(addressParams[19]).disabled=true;
   }
     if(document.getElementById(addressParams[20])!= null) 
   {
   document.getElementById(addressParams[20]).disabled=true;
   }
     if(document.getElementById(addressParams[21])!= null) 
   {
   document.getElementById(addressParams[21]).disabled=true;
   }
     if(document.getElementById(addressParams[22])!= null) 
   {
   document.getElementById(addressParams[22]).disabled=true;
   }
     if(document.getElementById(addressParams[23])!= null) 
   {
   document.getElementById(addressParams[23]).disabled=true;
   }
     if(document.getElementById(addressParams[24])!= null) 
   {
   document.getElementById(addressParams[24]).disabled=true;
   }
     if(document.getElementById(addressParams[25])!= null) 
   {
   document.getElementById(addressParams[25]).disabled=true;
   }
     if(document.getElementById(addressParams[26])!= null) 
   {
   document.getElementById(addressParams[26]).disabled=true;
   }
     if(document.getElementById(addressParams[27])!= null) 
   {
   document.getElementById(addressParams[27]).disabled=true;
   }
}
 function editAddress(addrName,xmlDOM,addrXPath)
  {
	//alert(addrXPath + '     aaaa');
    if(addrXPath == undefined)
    {
    	editAddressOld(addrName,xmlDOM);
    	return;
    }
 	editFlag=true;
    var addressParams = new Array(); 
    var area; 
    var cityCode;
    var socBuildName;
    var street;
    var districtCode;
    var faliyu;
    var talukaCode;
    var villageCode;
    try
    {
		var houseName  = getXPathValueFromDOM(xmlDOM,addrXPath +'/houseName');				
	//	alert("houseName : :"+houseName);
		var impLandmark = getXPathValueFromDOM(xmlDOM, addrXPath +'/impLandmark');		
//		alert("impLandmark : :"+impLandmark);		
		var otherDetails = getXPathValueFromDOM(xmlDOM,addrXPath +'/otherDetails');
//		alert("otherDetails : :"+otherDetails);	
		var pincode     = getXPathValueFromDOM(xmlDOM, addrXPath +'/pincode');
//		alert("pincode : :"+pincode);
//		var lookupName  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnLookupMstAddTypeLookupid/lookupName');
//		alert("lookupName : :"+lookupName);
		var cityCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnCityMst/cityCode');
	//	alert("cityCode : :"+cityCode);
		var villageCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnVillageMst/villageCode');
		
		var countryCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnCountryMst/countryCode');
		
		var stateCode    = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnStateMst/stateCode');
		
		if(stateCode != null)
		{
		    document.getElementById('tdCmbState'+addrName).style.display='';
		    document.getElementById('tdCmbDistrict'+addrName).style.display='';
		    document.getElementById('tdTxtState'+addrName).style.display='none';
		}
		else
		{
		   document.getElementById('tdTxtDistrict'+addrName).style.display='';
		}
//		alert("villageCode::"+villageCode);	
		var cityvillageName = getXPathValueFromDOM(xmlDOM,addrXPath + '/cityVilllageName');
		addressParams[0]="rdoAddress"+addrName;
		var rdoAddressArr = document.getElementsByName(addressParams[0]);
					
		var contextPath=document.getElementById('addrContextPath'+addrName).value;
		var countryArr = document.getElementsByName('cmbCountry'+addrName);
		var stateArr   = document.getElementsByName('cmbState'+addrName);
				if(countryArr!= null)
		 			{
		   
					   if(countryArr[0]!= null)
					   {
					   var options = countryArr[0].options;
						
					   if(options.length == 1)
					  {
					     
					    //	populateFirstLevel('cmbCity'+addrName,'getAllCity',contextPath,'districtCode','',addrName);
					    populateCountries("cmbCountry"+addrName,"getAllCountries",contextPath,'countryCode','',addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == countryCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			} 	 
		 			if(stateArr!= null)
		 			{
		   
					   if(stateArr[0]!= null)
					   {
					   var options = stateArr[0].options;
						
					   if(options.length == 1)
					  {
		                 populateState("cmbCountry"+addrName,"cmbState"+addrName,"getAllStates",contextPath,"countryCode",addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == stateCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			}
		// alert(cityCode);
		if(cityCode!=null)
		{			
			var area     = getXPathValueFromDOM(xmlDOM,addrXPath + '/area');
		//	alert("area::"+area);		
			var street     = getXPathValueFromDOM(xmlDOM, addrXPath +'/street');
	//		alert("street::"+street);
			
			var socBuildName     = getXPathValueFromDOM(xmlDOM,addrXPath + '/socBuildName');
	//		alert("socBuildName::"+socBuildName);				
			 
			addressParams[1]="txtCityHouseName"+addrName;
			addressParams[2]="txtSocietyName"+addrName;
			addressParams[3]="txtStreet"+addrName;
			addressParams[4]="txtArea"+addrName;
			addressParams[5]="cmbCity"+addrName;
			addressParams[6]="txtareaCityOtherDetails"+addrName;
			addressParams[7]="cmbCityLandmark"+addrName;
			addressParams[8]="txtCityPincode"+addrName;
			var cityHouseNameArr   = document.getElementsByName(addressParams[1]);
			var socNameArr         = document.getElementsByName(addressParams[2]);
			var streetArr          = document.getElementsByName(addressParams[3]);
			var areaArr            = document.getElementsByName(addressParams[4]);
			var cityArr            = document.getElementsByName(addressParams[5]);
			var cityOdArr          = document.getElementsByName(addressParams[6]);
			var cityLandmarkArr    = document.getElementsByName(addressParams[7]);
			var cityPincodeArr     = document.getElementsByName(addressParams[8]);
			rdoAddressArr[0].disabled=false;
   			rdoAddressArr[1].disabled=false;
    		rdoAddressArr[2].disabled=true; 
			rdoAddressArr[0].click();
			if(cityHouseNameArr!= null)
			{
				if(cityHouseNameArr[0]!= null)
				{
					if(houseName!= null)
					{
				    cityHouseNameArr[0].value=houseName;
				    }
				    else
				    {
				    cityHouseNameArr[0].value="";
				    }
				}
			}
			if(socNameArr!= null)
			{
				if(socNameArr[0]!=null)
				{
					if(socBuildName!=null)
					{
						socNameArr[0].value=socBuildName;
					}
					else
					{
						socNameArr[0].value="";  
					}
				}
			}
			if(streetArr!= null)
			{
				if(streetArr[0]!= null)
				{
					if(street!= null)
					{
						streetArr[0].value=street;
					}
					else
					{
						streetArr[0].value="";  
					}
	    }
	 }
   if(areaArr!= null)
	 {
	    if(areaArr[0]!= null)
	    {
	      if(area!=null)
	      {
	    areaArr[0].value=area;
	      }
	      else
	      {
	    areaArr[0].value="";  
      }
    }
 }
 if(cityArr!= null)
		 			{
		   
					   if(cityArr[0]!= null)
					   {
					   var options = cityArr[0].options;
						
					   if(options.length == 1)
					  {
					     
					    	populateFirstLevel('cmbCity'+addrName,'getAllCity',contextPath,'districtCode','',addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == cityCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			} 	 
				 
				  if(cityOdArr!= null)
				 {
				    if(cityOdArr[0]!= null)
				    {
				       if(otherDetails!= null)
				       {
				    cityOdArr[0].value=otherDetails;
				       }
				       else
				       {
				    cityOdArr[0].value="";   
				       }
				    }
				 } 
				 
				   if(cityPincodeArr!= null)
		 		{
				    if(cityPincodeArr[0]!= null)
				    {
				      if(pincode!=null)
				      {
				    cityPincodeArr[0].value=pincode;
				      }
				      else
				      {
				    cityPincodeArr[0].value="";  
				      }
				    }
		 		}
		 		
		 		   if(cityLandmarkArr!= null)
		 			{
		   
					    if(cityLandmarkArr[0]!= null)
					    {
					   var options = cityLandmarkArr[0].options;
					      if(options.length == 1)
					      {
					      populateLandmarks('cmbCityLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','CityLandmarks');
					      }
					     for(var cl=0;cl<options.length;cl++)
					     {
					        if(options[cl].value == impLandmark)
					        {
					         options[cl].selected="selected";
					        }
					     }
					    }
		       }
				 
	 }
	 else if(villageCode!= null)
	 {
		
		var faliyu     = getXPathValueFromDOM(xmlDOM,addrXPath +'/faliyu');
//			alert("faliyu::"+faliyu);
		
		
		var talukaCode  = getXPathValueFromDOM(xmlDOM,addrXPath +'/cmnTalukaMst/talukaCode');
//			alert("talukaCode::"+talukaCode);
				
		var districtCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnDistrictMst/districtCode');
//			alert("districtCode::"+districtCode);
			 
			addressParams[9]="txtVillageHouseName"+addrName;
			addressParams[10]="txtFaliyu"+addrName;
			addressParams[11]="cmbVillage"+addrName;
			addressParams[12]="cmbTaluka"+addrName;
			addressParams[13]="cmbDistrict"+addrName;
			addressParams[14]="cmbVillageLandmark"+addrName;
			addressParams[15]="txtareaVillageOtherDetails"+addrName;
			addressParams[16]="txtVillagePincode"+addrName;
			
			
		 var villageHouseNameArr= document.getElementsByName(addressParams[9]);
		 var faliyuArr          = document.getElementsByName(addressParams[10]); 
		 var villageArr         = document.getElementsByName(addressParams[11]); 
		 var talukaArr          = document.getElementsByName(addressParams[12]); 
		 var districtArr        = document.getElementsByName(addressParams[13]); 
		 var villageLandmarkArr = document.getElementsByName(addressParams[14]); 
		 var villageOdArr       = document.getElementsByName(addressParams[15]); 
		 var villagePincodeArr  = document.getElementsByName(addressParams[16]);
		 	rdoAddressArr[0].disabled=false;
   			rdoAddressArr[1].disabled=false;
    		rdoAddressArr[2].disabled=true; 
			rdoAddressArr[1].click();
			
				  if(villageHouseNameArr!= null)
				 {
				   if(villageHouseNameArr[0]!= null)
				   {
				      if(houseName!= null)
				       {
				    villageHouseNameArr[0].value=houseName;
				       }
				       else
				       {
				    villageHouseNameArr[0].value="";   
				       }
				    }
				 }
				  if(faliyuArr!= null)
				 {
				   
				   if(faliyuArr[0]!= null)
				   {
				     if(faliyu!= null)
				     {
				     faliyuArr[0].value=faliyu;
				     }
				     else
				     {
				     faliyuArr[0].value="";
				     }
				    }
				 }
				 
				
		 		if(districtArr!= null)
		 		{
		   
				    if(districtArr[0]!= null)
				    {
				    var options = districtArr[0].options;
				        if(options.length == 1)
				        {
				        populateFirstLevel('cmbDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
				        }
					       for(var d=0;d<options.length;d++)
						     {
						        if(options[d].value == districtCode)
						        {
						         options[d].selected="selected";
						        }
						     }
				    }
		 		}
		   		 if(talukaArr!= null)
					 {
					   
					    if(talukaArr[0]!= null)
					    {
					     var options = talukaArr[0].options;
					    //  if(options.length == 1)
					    //  {
					       populateNextLevel('cmbDistrict'+addrName,'cmbTaluka'+addrName,'getAllTaluka',contextPath,'districtCode',addrName)
					  //    }
						       for(var t=0;t<options.length;t++)
						     {
						        if(options[t].value == talukaCode)
						        {
						         options[t].selected="selected";
						        }
						     }
					     }
					 }
		     
		       if(villageArr!= null)
				 {
		   
				    if(villageArr[0]!= null)
				    {
				    var options = villageArr[0].options;
				      // if(options.length == 1)
					  //    {
					       populateNextLevel('cmbTaluka'+addrName,'cmbVillage'+addrName,'getAllVillage',contextPath,'talukaCode',addrName)
					  //    }
				       for(var v=0;v<options.length;v++)
					     {
					        if(options[v].value == villageCode)
					        {
					         options[v].selected="selected";
					        }
					     }
				    }
				 }
				      if(villageLandmarkArr!= null)
				 {
				   
				    if(villageLandmarkArr[0]!= null)
				    {
				    var options = villageLandmarkArr[0].options;
				       if(options.length == 1)
					      {
					      populateLandmarks('cmbVillageLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','VillageLandmarks');
					      }
				     for(var vl=0;vl<options.length;vl++)
						     {
						        if(options[vl].value == impLandmark)
						        {
						         options[vl].selected="selected";
						        }
						     }
				    }
				 }
				    if(villageOdArr!= null)
				 {
				   if(villageOdArr[0]!= null)
				   {
				     if(otherDetails!=null)
				     {
				    villageOdArr[0].value=otherDetails;
				     }
				     else
				     {
				    villageOdArr[0].value=""; 
				     }
				    }
				 }
				     if(villagePincodeArr!= null)
				 {
				    if(villagePincodeArr[0]!= null)
				    {
				     if(pincode!= null)
				     {
				    villagePincodeArr[0].value=pincode;
				     }
				     else
				     {
				    villagePincodeArr[0].value=""; 
				     }
				    }
				 }
				 
		 
	}
	else if(cityvillageName!= null)
	{
	    var area             = getXPathValueFromDOM(xmlDOM,addrXPath + '/area');
		var socBuildName     = getXPathValueFromDOM(xmlDOM,addrXPath + '/socBuildName');
		var districtCode     = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnDistrictMst/districtCode');
		var districtName     = getXPathValueFromDOM(xmlDOM,addrXPath + '/districtName');
		var talukaName       = getXPathValueFromDOM(xmlDOM,addrXPath + '/talukaName');
		var stateName        = getXPathValueFromDOM(xmlDOM,addrXPath + '/stateName');
		addressParams[17]="txtOtherHouseName"+addrName;
		addressParams[18]="txtOtherSocietyName"+addrName;
		addressParams[19]="txtOtherArea"+addrName;
		addressParams[20]="cmbOtherDistrict"+addrName;
		addressParams[21]="txtOtherDistrict"+addrName;
		addressParams[22]="txtOtherTaluka"+addrName;
		addressParams[23]="txtOtherCityVillage"+addrName;
		addressParams[24]="txtOtherPincode"+addrName;
		addressParams[25]="txtState"+addrName;
		var otherHouseNameArr   = document.getElementsByName(addressParams[17]); 
		var otherSocietyNameArr = document.getElementsByName(addressParams[18]); 
		var otherAreaArr        = document.getElementsByName(addressParams[19]); 
 		var cmbdistrictArr      = document.getElementsByName(addressParams[20]); 
 		var txtdistrictArr      = document.getElementsByName(addressParams[21]); 
 		var otherTalukaArr      = document.getElementsByName(addressParams[22]); 
 		var otherCVArr          = document.getElementsByName(addressParams[23]); 
 		var otherPincodeArr     = document.getElementsByName(addressParams[24]); 
 		var otherTxtStateArr    = document.getElementsByName(addressParams[25]); 
 			rdoAddressArr[0].disabled=true;
   			rdoAddressArr[1].disabled=true;
    		rdoAddressArr[2].disabled=false; 
 		rdoAddressArr[2].click();
 		      if(otherHouseNameArr!= null)
				 {
				   if(otherHouseNameArr[0]!= null)
				   {
				      if(houseName!= null)
				       {
				    otherHouseNameArr[0].value=houseName;
				       }
				       else
				       {
				    otherHouseNameArr[0].value="";   
				       }
				    }
				 }
			 if(otherSocietyNameArr!= null)
				 {
				   if(otherSocietyNameArr[0]!= null)
				   {
				      if(socBuildName!= null)
				       {
				    otherSocietyNameArr[0].value=socBuildName;
				       }
				       else
				       {
				    otherSocietyNameArr[0].value="";   
				       }
				    }
				 }	
			if(otherAreaArr!= null)
				 {
				   if(otherAreaArr[0]!= null)
				   {
				      if(area!= null)
				       {
				    otherAreaArr[0].value=area;
				       }
				       else
				       {
				    otherAreaArr[0].value="";   
				       }
				    }
				 }	 	  
			if(cmbdistrictArr!= null)
		 		{
		   
				    if(cmbdistrictArr[0]!= null)
				    {
				    var options = cmbdistrictArr[0].options;
				        if(options.length == 1)
				        {
				        populateFirstLevel('cmbOtherDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
				        }
					       for(var d=0;d<options.length;d++)
						     {
						        if(options[d].value == districtCode)
						        {
						         options[d].selected="selected";
						        }
						     }
				    }
		 		}
		 		
		 	if(txtdistrictArr!= null)
				 {
				   if(txtdistrictArr[0]!= null)
				   {
				      if(districtName!= null)
				       {
				    txtdistrictArr[0].value=districtName;
				       }
				       else
				       {
				    txtdistrictArr[0].value="";   
				       }
				    }
				 }	
				if(otherTalukaArr!= null)
				 {
				   if(otherTalukaArr[0]!= null)
				   {
				      if(talukaName!= null)
				       {
				    otherTalukaArr[0].value=talukaName;
				       }
				       else
				       {
				    otherTalukaArr[0].value="";   
				       }
				    }
				 }
				 if(otherCVArr!= null)
				 {
				   if(otherCVArr[0]!= null)
				   {
				      if(cityvillageName!= null)
				       {
				    otherCVArr[0].value=cityvillageName;
				       }
				       else
				       {
				    otherCVArr[0].value="";   
				       }
				    }
				 }	
				 if(otherPincodeArr!= null)
				 {
				   if(otherPincodeArr[0]!= null)
				   {
				      if(pincode!= null)
				       {
				    otherPincodeArr[0].value=pincode;
				       }
				       else
				       {
				    otherPincodeArr[0].value="";   
				       }
				    }
				 } otherTxtStateArr
				  if(otherTxtStateArr!= null)
				 {
				   if(otherTxtStateArr[0]!= null)
				   {
				      if(stateName!= null)
				       {
				    otherTxtStateArr[0].value=stateName;
				       }
				       else
				       {
				    otherTxtStateArr[0].value="";   
				       }
				    }
				 }
		
	}
	else
	{
		closeAddress(addrName);
	}
  
   }catch(ex)
   {
   
   }finally
   {
  
   editFlag=false;
   }	 
  
  }
 
  function editAddressOld(addrName,xmlDOM)
  {
    editFlag=true;
    var addressParams = new Array(); 
    var area; 
    var cityCode;
    var socBuildName;
    var street;
    var districtCode;
    var faliyu;
    var talukaCode;
    var villageCode;
    try
    {
	
		var houseName  = getValueFromDOM(xmlDOM, 'houseName');
		var impLandmark = getValueFromDOM(xmlDOM, 'impLandmark');
		var otherDetails= getValueFromDOM(xmlDOM, 'otherDetails');
		var pincode     = getValueFromDOM(xmlDOM, 'pincode');
		cityCode      = getValueFromDOM(xmlDOM, 'cityCode');
		 villageCode   = getValueFromDOM(xmlDOM, 'villageCode');
		 var countryCode  = getValueFromDOM(xmlDOM, 'countryCode');
		
		var stateCode    = getValueFromDOM(xmlDOM, 'stateCode');
		
		if(stateCode != null)
		{
		    document.getElementById('tdCmbState'+addrName).style.display='';
		    document.getElementById('tdCmbDistrict'+addrName).style.display='';
		    document.getElementById('tdTxtState'+addrName).style.display='none';
		}
		else
		{
		   document.getElementById('tdTxtDistrict'+addrName).style.display='';
		}
//		alert("villageCode::"+villageCode);	
		var cityvillageName = getValueFromDOM(xmlDOM, 'cityVilllageName');
		addressParams[0]="rdoAddress"+addrName;
		var rdoAddressArr = document.getElementsByName(addressParams[0]);
	    
	
	var contextPath=document.getElementById('addrContextPath'+addrName).value;
	var countryArr = document.getElementsByName('cmbCountry'+addrName);
		var stateArr   = document.getElementsByName('cmbState'+addrName);
				if(countryArr!= null)
		 			{
		   
					   if(countryArr[0]!= null)
					   {
					   var options = countryArr[0].options;
						
					   if(options.length == 1)
					  {
					     
					    //	populateFirstLevel('cmbCity'+addrName,'getAllCity',contextPath,'districtCode','',addrName);
					    populateCountries("cmbCountry"+addrName,"getAllCountries",contextPath,'countryCode','',addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == countryCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			} 	 
		 			if(stateArr!= null)
		 			{
		   
					   if(stateArr[0]!= null)
					   {
					   var options = stateArr[0].options;
						
					   if(options.length == 1)
					  {
		                 populateState("cmbCountry"+addrName,"cmbState"+addrName,"getAllStates",contextPath,"countryCode",addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == stateCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			}
	 if(cityCode!=null)
	 {
			 area        = getValueFromDOM(xmlDOM, 'area');
			 
			 socBuildName= getValueFromDOM(xmlDOM, 'socBuildName');
			 street   = getValueFromDOM(xmlDOM, 'street'); 
			 
			addressParams[1]="txtCityHouseName"+addrName;
			addressParams[2]="txtSocietyName"+addrName;
			addressParams[3]="txtStreet"+addrName;
			addressParams[4]="txtArea"+addrName;
			addressParams[5]="cmbCity"+addrName;
			addressParams[6]="txtareaCityOtherDetails"+addrName;
			addressParams[7]="cmbCityLandmark"+addrName;
			addressParams[8]="txtCityPincode"+addrName;
			
			
		 var cityHouseNameArr   = document.getElementsByName(addressParams[1]);
	     var socNameArr         = document.getElementsByName(addressParams[2]);
		 var streetArr          = document.getElementsByName(addressParams[3]);
		 var areaArr            = document.getElementsByName(addressParams[4]);
		 var cityArr            = document.getElementsByName(addressParams[5]);
		 var cityOdArr          = document.getElementsByName(addressParams[6]);
		 var cityLandmarkArr    = document.getElementsByName(addressParams[7]);
		 var cityPincodeArr     = document.getElementsByName(addressParams[8]);
			rdoAddressArr[0].disabled=false;
   			rdoAddressArr[1].disabled=false;
    		rdoAddressArr[2].disabled=true;
			rdoAddressArr[0].click();
			
				if(cityHouseNameArr!= null)
				 {
				    if(cityHouseNameArr[0]!= null)
				    {
				     if(houseName!= null)
				     {
				    cityHouseNameArr[0].value=houseName;
				     }
				     else
				     {
				     cityHouseNameArr[0].value="";
				     }
				    }
				 } 
			    if(socNameArr!= null)
					 {
					   if(socNameArr[0]!=null)
					   {
					      if(socBuildName!=null)
					      {
					    socNameArr[0].value=socBuildName;
					      }
					      else
					      {
					    socNameArr[0].value="";  
					      }
					    }
		 		    }
			    if(streetArr!= null)
				 {
				   if(streetArr[0]!= null)
				   {
				     if(street!= null)
				     {
				    streetArr[0].value=street;
				     }
				     else
				     {
				   streetArr[0].value="";  
				     }
				    }
				 }
			   if(areaArr!= null)
				 {
				    if(areaArr[0]!= null)
				    {
				      if(area!=null)
				      {
				    areaArr[0].value=area;
				      }
				      else
				      {
				    areaArr[0].value="";  
				      }
				    }
				 }
			     if(cityArr!= null)
		 			{
		   
					   if(cityArr[0]!= null)
					   {
					   var options = cityArr[0].options;
						
					   if(options.length == 1)
					  {
					     
					    	populateFirstLevel('cmbCity'+addrName,'getAllCity',contextPath,'districtCode','',addrName);
					   }
					     
					     for(var c=0;c<options.length;c++)
					     {
					        if(options[c].value == cityCode)
					        {
					         options[c].selected="selected";
					        }
					     }
					   }
		 			} 	 
				 
				  if(cityOdArr!= null)
				 {
				    if(cityOdArr[0]!= null)
				    {
				       if(otherDetails!= null)
				       {
				    cityOdArr[0].value=otherDetails;
				       }
				       else
				       {
				    cityOdArr[0].value="";   
				       }
				    }
				 } 
				 
				   if(cityPincodeArr!= null)
		 		{
				    if(cityPincodeArr[0]!= null)
				    {
				      if(pincode!=null)
				      {
				    cityPincodeArr[0].value=pincode;
				      }
				      else
				      {
				    cityPincodeArr[0].value="";  
				      }
				    }
		 		}
		 		
		 		   if(cityLandmarkArr!= null)
		 			{
		   
					    if(cityLandmarkArr[0]!= null)
					    {
					   var options = cityLandmarkArr[0].options;
					      if(options.length == 1)
					      {
					      populateLandmarks('cmbCityLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','CityLandmarks');
					      }
					     for(var cl=0;cl<options.length;cl++)
					     {
					        if(options[cl].value == impLandmark)
					        {
					         options[cl].selected="selected";
					        }
					     }
					    }
		       }
				 
	 }
	 else if(villageCode!= null)
	 {
			 
			 faliyu     = getValueFromDOM(xmlDOM, 'faliyu');
			 talukaCode    = getValueFromDOM(xmlDOM, 'talukaCode');
			
			 districtCode = getValueFromDOM(xmlDOM, 'districtCode');
			addressParams[9]="txtVillageHouseName"+addrName;
			addressParams[10]="txtFaliyu"+addrName;
			addressParams[11]="cmbVillage"+addrName;
			addressParams[12]="cmbTaluka"+addrName;
			addressParams[13]="cmbDistrict"+addrName;
			addressParams[14]="cmbVillageLandmark"+addrName;
			addressParams[15]="txtareaVillageOtherDetails"+addrName;
			addressParams[16]="txtVillagePincode"+addrName;
			
			
		 var villageHouseNameArr= document.getElementsByName(addressParams[9]);
		 var faliyuArr          = document.getElementsByName(addressParams[10]); 
		 var villageArr         = document.getElementsByName(addressParams[11]); 
		 var talukaArr          = document.getElementsByName(addressParams[12]); 
		 var districtArr        = document.getElementsByName(addressParams[13]); 
		 var villageLandmarkArr = document.getElementsByName(addressParams[14]); 
		 var villageOdArr       = document.getElementsByName(addressParams[15]); 
		 var villagePincodeArr  = document.getElementsByName(addressParams[16]);
		 	rdoAddressArr[0].disabled=false;
   			rdoAddressArr[1].disabled=false;
    		rdoAddressArr[2].disabled=true;
			rdoAddressArr[1].click();
			
				  if(villageHouseNameArr!= null)
				 {
				   if(villageHouseNameArr[0]!= null)
				   {
				      if(houseName!= null)
				       {
				    villageHouseNameArr[0].value=houseName;
				       }
				       else
				       {
				    villageHouseNameArr[0].value="";   
				       }
				    }
				 }
				  if(faliyuArr!= null)
				 {
				   
				   if(faliyuArr[0]!= null)
				   {
				     if(faliyu!= null)
				     {
				     faliyuArr[0].value=faliyu;
				     }
				     else
				     {
				     faliyuArr[0].value="";
				     }
				    }
				 }
				 
				
		 		if(districtArr!= null)
		 		{
		   
				    if(districtArr[0]!= null)
				    {
				    var options = districtArr[0].options;
				        if(options.length == 1)
				        {
				        populateFirstLevel('cmbDistrict'+addrName,'getAllDistrict',contextPath,'stateId','',addrName);
				        }
					       for(var d=0;d<options.length;d++)
						     {
						        if(options[d].value == districtCode)
						        {
						         options[d].selected="selected";
						        }
						     }
				    }
		 		}
		   		 if(talukaArr!= null)
					 {
					   
					    if(talukaArr[0]!= null)
					    {
					     var options = talukaArr[0].options;
					    //  if(options.length == 1)
					    //  {
					       populateNextLevel('cmbDistrict'+addrName,'cmbTaluka'+addrName,'getAllTaluka',contextPath,'districtCode',addrName)
					  //    }
						       for(var t=0;t<options.length;t++)
						     {
						        if(options[t].value == talukaCode)
						        {
						         options[t].selected="selected";
						        }
						     }
					     }
					 }
		     
		       if(villageArr!= null)
				 {
		   
				    if(villageArr[0]!= null)
				    {
				    var options = villageArr[0].options;
				      // if(options.length == 1)
					  //    {
					       populateNextLevel('cmbTaluka'+addrName,'cmbVillage'+addrName,'getAllVillage',contextPath,'talukaCode',addrName)
					  //    }
				       for(var v=0;v<options.length;v++)
					     {
					        if(options[v].value == villageCode)
					        {
					         options[v].selected="selected";
					        }
					     }
				    }
				 }
				      if(villageLandmarkArr!= null)
				 {
				   
				    if(villageLandmarkArr[0]!= null)
				    {
				    var options = villageLandmarkArr[0].options;
				       if(options.length == 1)
					      {
					      populateLandmarks('cmbVillageLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','VillageLandmarks');
					      }
				     for(var vl=0;vl<options.length;vl++)
						     {
						        if(options[vl].value == impLandmark)
						        {
						         options[vl].selected="selected";
						        }
						     }
				    }
				 }
				    if(villageOdArr!= null)
				 {
				   if(villageOdArr[0]!= null)
				   {
				     if(otherDetails!=null)
				     {
				    villageOdArr[0].value=otherDetails;
				     }
				     else
				     {
				    villageOdArr[0].value=""; 
				     }
				    }
				 }
				     if(villagePincodeArr!= null)
				 {
				    if(villagePincodeArr[0]!= null)
				    {
				     if(pincode!= null)
				     {
				    villagePincodeArr[0].value=pincode;
				     }
				     else
				     {
				    villagePincodeArr[0].value=""; 
				     }
				    }
				 }
				 
		 
	}
	else if(cityvillageName!= null)
	{
	    var area             = getValueFromDOM(xmlDOM, 'area');
		var socBuildName     = getValueFromDOM(xmlDOM,'socBuildName');
		var districtCode     = getValueFromDOM(xmlDOM,'districtCode');
		var districtName     = getValueFromDOM(xmlDOM,'districtName');
		var talukaName       = getValueFromDOM(xmlDOM, 'talukaName');
		var stateName        = getValueFromDOM(xmlDOM, 'stateName');
		addressParams[17]="txtOtherHouseName"+addrName;
		addressParams[18]="txtOtherSocietyName"+addrName;
		addressParams[19]="txtOtherArea"+addrName;
		addressParams[20]="cmbOtherDistrict"+addrName;
		addressParams[21]="txtOtherDistrict"+addrName;
		addressParams[22]="txtOtherTaluka"+addrName;
		addressParams[23]="txtOtherCityVillage"+addrName;
		addressParams[24]="txtOtherPincode"+addrName;
		addressParams[25]="txtState"+addrName;
		var otherHouseNameArr   = document.getElementsByName(addressParams[17]); 
		var otherSocietyNameArr = document.getElementsByName(addressParams[18]); 
		var otherAreaArr        = document.getElementsByName(addressParams[19]); 
 		var cmbdistrictArr      = document.getElementsByName(addressParams[20]); 
 		var txtdistrictArr      = document.getElementsByName(addressParams[21]); 
 		var otherTalukaArr      = document.getElementsByName(addressParams[22]); 
 		var otherCVArr          = document.getElementsByName(addressParams[23]); 
 		var otherPincodeArr     = document.getElementsByName(addressParams[24]); 
 	    var otherTxtStateArr    = document.getElementsByName(addressParams[25]); 
 		 
 		rdoAddressArr[0].disabled=true;
   		rdoAddressArr[1].disabled=true;
    	rdoAddressArr[2].disabled=false;
 		rdoAddressArr[2].click();
 		      if(otherHouseNameArr!= null)
				 {
				   if(otherHouseNameArr[0]!= null)
				   {
				      if(houseName!= null)
				       {
				    otherHouseNameArr[0].value=houseName;
				       }
				       else
				       {
				    otherHouseNameArr[0].value="";   
				       }
				    }
				 }
			 if(otherSocietyNameArr!= null)
				 {
				   if(otherSocietyNameArr[0]!= null)
				   {
				      if(socBuildName!= null)
				       {
				    otherSocietyNameArr[0].value=socBuildName;
				       }
				       else
				       {
				    otherSocietyNameArr[0].value="";   
				       }
				    }
				 }	
			if(otherAreaArr!= null)
				 {
				   if(otherAreaArr[0]!= null)
				   {
				      if(area!= null)
				       {
				    otherAreaArr[0].value=area;
				       }
				       else
				       {
				    otherAreaArr[0].value="";   
				       }
				    }
				 }	 	  
			if(cmbdistrictArr!= null)
		 		{
		   
				    if(cmbdistrictArr[0]!= null)
				    {
				    var options = cmbdistrictArr[0].options;
				        if(options.length == 1)
				        {
				        populateFirstLevel('cmbOtherDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
				        }
					       for(var d=0;d<options.length;d++)
						     {
						        if(options[d].value == districtCode)
						        {
						         options[d].selected="selected";
						        }
						     }
				    }
		 		}
		 		
		 	if(txtdistrictArr!= null)
				 {
				   if(txtdistrictArr[0]!= null)
				   {
				      if(districtName!= null)
				       {
				    txtdistrictArr[0].value=districtName;
				       }
				       else
				       {
				    txtdistrictArr[0].value="";   
				       }
				    }
				 }	
				if(otherTalukaArr!= null)
				 {
				   if(otherTalukaArr[0]!= null)
				   {
				      if(talukaName!= null)
				       {
				    otherTalukaArr[0].value=talukaName;
				       }
				       else
				       {
				    otherTalukaArr[0].value="";   
				       }
				    }
				 }
				 if(otherCVArr!= null)
				 {
				   if(otherCVArr[0]!= null)
				   {
				      if(cityvillageName!= null)
				       {
				    otherCVArr[0].value=cityvillageName;
				       }
				       else
				       {
				    otherCVArr[0].value="";   
				       }
				    }
				 }	
				 if(otherPincodeArr!= null)
				 {
				   if(otherPincodeArr[0]!= null)
				   {
				      if(pincode!= null)
				       {
				    otherPincodeArr[0].value=pincode;
				       }
				       else
				       {
				    otherPincodeArr[0].value="";   
				       }
				    }
				 } 
		 if(otherTxtStateArr!= null)
				 {
				   if(otherTxtStateArr[0]!= null)
				   {
				      if(stateName!= null)
				       {
				    otherTxtStateArr[0].value=stateName;
				       }
				       else
				       {
				    otherTxtStateArr[0].value="";   
				       }
				    }
				 }
	}
	else
	{
		closeAddress(addrName);
	}
  
   }catch(ex)
   {
   
   }finally
   {
  
   editFlag=false;
   }	 
  }
  
  function resetAddress(addrName)
  {
 
            
		    var addressParams = new Array();
		    addressParams[0]="rdoAddress"+addrName;
			addressParams[1]="txtCityHouseName"+addrName;
			addressParams[2]="txtSocietyName"+addrName;
			addressParams[3]="txtStreet"+addrName;
			addressParams[4]="txtArea"+addrName;
			addressParams[5]="cmbCity"+addrName;
			addressParams[6]="txtareaCityOtherDetails"+addrName;
			addressParams[7]="cmbCityLandmark"+addrName;
			addressParams[8]="txtCityPincode"+addrName;
			
			addressParams[9]="txtVillageHouseName"+addrName;
			addressParams[10]="txtFaliyu"+addrName;
			addressParams[11]="cmbVillage"+addrName;
			addressParams[12]="cmbTaluka"+addrName;
			addressParams[13]="cmbDistrict"+addrName;
			addressParams[14]="cmbVillageLandmark"+addrName;
			addressParams[15]="txtareaVillageOtherDetails"+addrName;
			addressParams[16]="txtVillagePincode"+addrName;
			
			addressParams[17]="txtOtherHouseName"+addrName;
			addressParams[18]="txtOtherSocietyName"+addrName;
			addressParams[19]="txtOtherArea"+addrName;
			addressParams[20]="cmbOtherDistrict"+addrName;
			addressParams[21]="txtOtherDistrict"+addrName;
			addressParams[22]="txtOtherTaluka"+addrName;
			addressParams[23]="txtOtherCityVillage"+addrName;
			addressParams[24]="txtOtherPincode"+addrName;
	
	    
	     var cityHouseNameArr   = document.getElementsByName(addressParams[1]);
	     var socNameArr         = document.getElementsByName(addressParams[2]);
		 var streetArr          = document.getElementsByName(addressParams[3]);
		 var areaArr            = document.getElementsByName(addressParams[4]);
		 var cityArr            = document.getElementsByName(addressParams[5]);
		 var cityOdArr          = document.getElementsByName(addressParams[6]);
		 var cityLandmarkArr    = document.getElementsByName(addressParams[7]);
		 var cityPincodeArr     = document.getElementsByName(addressParams[8]);
		 
		 var villageHouseNameArr= document.getElementsByName(addressParams[9]);
		 var faliyuArr          = document.getElementsByName(addressParams[10]); 
		 var villageArr         = document.getElementsByName(addressParams[11]); 
		 var talukaArr          = document.getElementsByName(addressParams[12]); 
		 var districtArr        = document.getElementsByName(addressParams[13]); 
		 var villageLandmarkArr = document.getElementsByName(addressParams[14]); 
		 var villageOdArr       = document.getElementsByName(addressParams[15]); 
		 var villagePincodeArr  = document.getElementsByName(addressParams[16]);
		 
		 var otherHouseNameArr  = document.getElementsByName(addressParams[17]);
		 var otherSocietyArr    = document.getElementsByName(addressParams[18]); 
		 var otherAreaArr       = document.getElementsByName(addressParams[19]); 
		 var otherCmbDistrictArr= document.getElementsByName(addressParams[20]); 
		 var otherTxtDistrictArr= document.getElementsByName(addressParams[21]); 
		 var otherTalukaArr     = document.getElementsByName(addressParams[22]); 
		 var othercvArr         = document.getElementsByName(addressParams[23]); 
		 var otherPincodeArr    = document.getElementsByName(addressParams[24]);
		 
		
		 if(cityHouseNameArr!= null)
		 {
		    if(cityHouseNameArr[0]!= null)
		    {
		    cityHouseNameArr[0].value="";
		    }
		 }
		  if(socNameArr!= null)
		 {
		   if(socNameArr[0]!=null)
		   {
		    socNameArr[0].value="";
		    }
		 }
		   if(streetArr!= null)
		 {
		   if(streetArr[0]!= null)
		   {
		    streetArr[0].value="";
		    }
		 }
		    if(areaArr!= null)
		 {
		    if(areaArr[0]!= null)
		    {
		    areaArr[0].value="";
		    }
		 }
		     if(cityArr!= null)
		 {
		   
		   if(cityArr[0]!= null)
		   {
		   var options = cityArr[0].options;
		      if( options[0]!= null)
		      {
		   options[0].selected="selected";
		      }
		   }
		 } 
		  if(cityOdArr!= null)
		 {
		    if(cityOdArr[0]!= null)
		    {
		    cityOdArr[0].value="";
		    }
		 }
		   if(cityLandmarkArr!= null)
		 {
		   
		    if(cityLandmarkArr[0]!= null)
		    {
		   var options = cityLandmarkArr[0].options;
		     if(options[0]!= null)
		     {
		   options[0].selected="selected";
		     }
		    }
		 }
		  if(cityPincodeArr!= null)
		 {
		    if(cityPincodeArr[0]!= null)
		    {
		      
		    cityPincodeArr[0].value="";
		    }
		 }
		 
		 if(villageHouseNameArr!= null)
		 {
		   if(villageHouseNameArr[0]!= null)
		   {
		    villageHouseNameArr[0].value="";
		    }
		 }
		  if(faliyuArr!= null)
		 {
		   
		   if(faliyuArr[0]!= null)
		   {
		     faliyuArr[0].value="";
		    }
		 }
		   if(villageArr!= null)
		 {
		   
		    if(villageArr[0]!= null)
		    {
		    var options = villageArr[0].options;
		      if(options[0]!= null)
		      {
		    options[0].selected="selected";
		      }
		    }
		 }
		 
		    if(talukaArr!= null)
		 {
		   
		    if(talukaArr[0]!= null)
		    {
		     var options = talukaArr[0].options;
		       if(options[0]!= null)
		       {
		     options[0].selected="selected";
		       }
		     }
		 }
		     if(districtArr!= null)
		 {
		   
		    if(districtArr[0]!= null)
		    {
		    var options = districtArr[0].options;
		      if(options[0]!= null)
		      {
		    options[0].selected="selected";
		     }
		    }
		 }
		      if(villageLandmarkArr!= null)
		 {
		   
		    if(villageLandmarkArr[0]!= null)
		    {
		    var options = villageLandmarkArr[0].options;
		      if(options[0]!= null)
		      {
		    options[0].selected="selected";
		      }
		    }
		 }
		    if(villageOdArr!= null)
		 {
		   if(villageOdArr[0]!= null)
		   {
		    villageOdArr[0].value="";
		    }
		 }
		     if(villagePincodeArr!= null)
		 {
		    if(villagePincodeArr[0]!= null)
		    {
		    villagePincodeArr[0].value="";
		    }
		 }
		 
		  if(otherHouseNameArr!= null)
		  { 
		       if(otherHouseNameArr[0]!= null)
		       {
		       otherHouseNameArr[0].value="";
		       }
		  }  
		  if(otherSocietyArr != null)
		  {
		      if(otherSocietyArr[0]!= null)
		      {
		      otherSocietyArr[0].value="";	
		      }
		  }   
		  if(otherAreaArr != null)
		  {
		      if(otherAreaArr[0] != null)
		      {
		      	otherAreaArr[0].value="";
		      }
		  }      
		  if(otherCmbDistrictArr!= null)
		  {
		      if(otherCmbDistrictArr[0]!= null)
		      {
		      var options = otherCmbDistrictArr[0].options;
			      if(options[0]!= null)
			      {
			    options[0].selected="selected";
			      }
		      }
		  }
		 if(otherTxtDistrictArr != null)
		 {
		    if(otherTxtDistrictArr[0]!= null)
		      {
		      otherTxtDistrictArr[0].value="";
		      }
		 }
		  if(otherTalukaArr  != null)
		  {
		       if(otherTalukaArr[0]!= null)
		       {
		       otherTalukaArr[0].value="";
		       }
		  }   
		 if(othercvArr  != null)
		  {
		      if(othercvArr[0]!= null)
		      {
		      othercvArr[0].value="";
		      }
		  }       
		 if(otherPincodeArr != null)
		  {
		      if(otherPincodeArr[0]!= null)
		      {
		         otherPincodeArr[0].value="";
		         
		      }
		  }    
  }
  function closeAddress(addrName,isEmptyRequired,isCloseRequired)
  {
   var rdoAddress="rdoAddress"+addrName;
   var rdoAddressArr = document.getElementsByName(rdoAddress);
   
    resetAddress(addrName);
    if(isEmptyRequired!=false)
    {
	    if(document.getElementById("cmbCity"+addrName)!= null)
	    {
	    
	      clearList(document.getElementById("cmbCity"+addrName));
	    }
	    
	   if(document.getElementById("cmbCityLandmark"+addrName)!= null)
	   {
	     clearList(document.getElementById("cmbCityLandmark"+addrName));
	   }
	   if( document.getElementById("cmbVillage"+addrName)!= null)
	   {
	     clearList(document.getElementById("cmbVillage"+addrName));
	   }
	   if(document.getElementById("cmbTaluka"+addrName)!= null)
	   {
	     clearList(document.getElementById("cmbTaluka"+addrName));
	   }
	   if(document.getElementById("cmbDistrict"+addrName)!= null)
	   {
	    clearList(document.getElementById("cmbDistrict"+addrName));
	   }
	   if(document.getElementById("cmbVillageLandmark"+addrName)!= null)
	   {
	     clearList(document.getElementById("cmbVillageLandmark"+addrName));
	   }
	   if(document.getElementById("cmbOtherDistrict"+addrName)!= null)
	   {
	      clearList(document.getElementById("cmbOtherDistrict"+addrName));
	   }
   }
   if(isCloseRequired!= false)
   {
    if(rdoAddressArr!= null)
    {
	   if(rdoAddressArr[0]!= null)
	   {
	   rdoAddressArr[0].checked=false;
	   }
	   if(rdoAddressArr[1]!= null)
	   {
	   rdoAddressArr[1].checked=false;
	   }
	   if(rdoAddressArr[2]!= null)
	   {
	    rdoAddressArr[2].checked=false; 
	   }
    }
   document.getElementById("citytbl"+addrName).style.display='none';
   document.getElementById("villagetbl"+addrName).style.display='none';
    document.getElementById("othertbl"+addrName).style.display='none';
   }
   
  }
  
  function clearList(listId)
  {
  	var listLength = listId.length;
				    		      
	 for(var i=listId.length;i>=1;i--)
			{
				    		        
			listId.remove(i);
				    		        
	       }   
	       
  }
 
 function editMultipleAddress(arrAddrName,xmlDOM,parentProperties)
 {
    for(var j=0;j<parentProperties.length;j++)
    {
	    var addressXML=getXMLFromParentProperty(xmlDOM,parentProperties[j]) ;
	    
	    if(addressXML!= null)
	    {
	    var addressDOM=getDOMFromXML(addressXML);
	     editAddress(arrAddrName[j],addressDOM);
	    }
	}    
 }


 function isAddressClosed(addrName)
 {
 	 	var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
 	 if(rdoAddressArr[0].checked==false && rdoAddressArr[1].checked==false && rdoAddressArr[2].checked==false)
      {
          return true;
      }
      else
      {
         return false;
      }
 }
 function populateCountries(firstLevelId,actionFlag,ctxPath,sourceId,sourceVal,uniqueName)
{
  
		
		nxtCombo=firstLevelId;
		addrName=uniqueName;
	var htmFile=document.getElementById('addrHtmName'+uniqueName).value;
	glbHtmFile=htmFile;
	try
	    {   
	    	xmlHttpCountry=new XMLHttpRequest();    
	    }
		catch (e)
		{   
			try
      		{
      		    xmlHttpCountry=new ActiveXObject("Msxml2.XMLHTTP"); 
      		       
      		}
		    catch (e)
		    {
		          try
        		  {
            	          xmlHttpCountry=new ActiveXObject("Microsoft.XMLHTTP");        
            	          
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              
			              return false;        
			      }
			      
			 }
			 
        }
        var z=document.getElementById(firstLevelId);
        var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+sourceVal;   
		 
		
		
		xmlHttpCountry.open("POST", encodeURI(url) , false);
		
		xmlHttpCountry.onreadystatechange =  processResponseCountry;
		xmlHttpCountry.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttpCountry.send(encodeURIComponent(null));
		
}

function processResponseCountry()
		{
			
			
			if (xmlHttpCountry.readyState == 4) 
			{     
				
				if (xmlHttpCountry.status == 200) 
				{          
				  
					var text;
						
		            	var z=document.getElementById(nxtCombo);
		            	
		            	
				    	var XMLDoc=xmlHttpCountry.responseXML.documentElement;
				    	   
				    	if(XMLDoc!= null)
				    	{
				    	     
				    	    
				    		 if(z.length > 1)
				    		  {
				    		     clearList(z);
				    		  }
				    		
				    	var entries = XMLDoc.getElementsByTagName('element');	
				    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
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
				else 
				{  
			
					alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
				
			}
			
}
function populateState(srcLevelId,nextLevelId,actionFlag,ctxPath,sourceId,uniqueName) {
    
     var countryCode = document.getElementById('cmbCountry'+uniqueName).value;
        if(countryCode != 1)
        {
        	var rdoAddress="rdoAddress"+uniqueName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
        	rdoAddressArr[0].disabled=true;
    	    rdoAddressArr[1].disabled=true;
   		    rdoAddressArr[2].disabled=false;
   		    rdoAddressArr[2].click();
        }
        else
        {
        document.getElementById('tdCmbState'+uniqueName).style.display='';
         
         document.getElementById('tdTxtState'+uniqueName).style.display='none';
         document.getElementById('tdTxtDistrict'+uniqueName).style.display='none';
         document.getElementById('tdCmbDistrict'+uniqueName).style.display='';
    	  nxtCombo=nextLevelId;
    	  addrName=uniqueName;
 		 var srcObj = document.getElementById(srcLevelId);
  		var htmFile=document.getElementById('addrHtmName'+uniqueName).value;
  			try
		    {   
	    	xmlHttpState=new XMLHttpRequest();    
	  		  }
			catch (e)
				{   
					try
      				{
      				    xmlHttpState=new ActiveXObject("Msxml2.XMLHTTP"); 
      		       
      				}
		   			 catch (e)
		   				 {
		      			    try
        				  {
            	          xmlHttpState=new ActiveXObject("Microsoft.XMLHTTP");        
            	          
        		 		 }
			     			 catch (e)
			     			{
			              alert("Your browser does not support AJAX!");        
			              
			              return false;        
			     		 }
			      
					 }
			 
       			 }
        var z=document.getElementById(nextLevelId);
        var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+srcObj.value;   
		
		
		
		xmlHttpState.open("POST", encodeURI(url) , false);
		
		xmlHttpState.onreadystatechange = processResponseState;
		xmlHttpState.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttpState.send(encodeURIComponent(null));
		
	}	

}

function processResponseState()
		{
			
			
			if (xmlHttpState.readyState == 4) 
			{     
				
				if (xmlHttpState.status == 200) 
				{          
				
					var text;
						
		            	var z=document.getElementById(nxtCombo);
		            	
		            	
				    	var XMLDoc=xmlHttpState.responseXML.documentElement;
				    	   
				    	if(XMLDoc!= null)
				    	{
				    	 	 if(z.length > 1)
				    		  {
				    		     clearList(z);
				    		  }
				    	 
				    	 
				    	var entries = XMLDoc.getElementsByTagName('element');	
				    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;
	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;
							
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
				else 
				{  
			
					alert("ERROR");
					
				}
			}
}
 function setCountry(addrName,countryCode)
 {
    var countryCombo = document.getElementById('cmbCountry'+addrName);
   
    
         var countryOptions = countryCombo.options;
		      if(countryOptions!= null)
		      { 
		        for(var c=0;c<countryOptions.length;c++)
		        {
		             if(countryOptions[c].value == countryCode)
		               {
		                countryOptions[c].selected="selected";
		                break;
		               }
		        }
		   
		      }
    
 }
  function setState(addrName,stateCode)
 {
  
    var stateCombo = document.getElementById('cmbState'+addrName);
    
         var stateOptions = stateCombo.options;
		      if(stateOptions!= null)
		      { 
		        for(var s=0;s<stateOptions.length;s++)
		        {
		             if(stateOptions[s].value == stateCode)
		               {
		                stateOptions[s].selected="selected";
		                break;
		               }
		        }
		   
		      }
    
 }
 function showOther(addrName,otherTable,stateCode,countryCode,ctxPath)
 {
 		
 		var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   		
    if(countryCode==1&&stateCode==1)
    {
		
		rdoAddressArr[0].disabled=false;
    	rdoAddressArr[1].disabled=false;
    	rdoAddressArr[2].disabled=true;    
    	rdoAddressArr[2].checked=false;
    	document.getElementById('tdTxtState'+addrName).style.display='none';
    	document.getElementById('tdCmbState'+addrName).style.display='';
    	document.getElementById(otherTable).style.display='none';
    }
    else if(countryCode==1&&stateCode!=1)
    {
    	
    	document.getElementById('citytbl'+addrName).style.display='none';
    	document.getElementById('villagetbl'+addrName).style.display='none';
    	rdoAddressArr[0].disabled=true;
    	rdoAddressArr[1].disabled=true;
    	rdoAddressArr[2].disabled=false;
    	rdoAddressArr[2].checked=true;
    	document.getElementById('tdTxtState'+addrName).style.display='none';
    	document.getElementById('tdCmbState'+addrName).style.display='';
		document.getElementById(otherTable).style.display='';
		document.getElementById('tdTxtDistrict'+addrName).style.display='none';
		populateFirstLevel('cmbOtherDistrict'+addrName,'getAllDistrict',ctxPath,'stateCode',stateCode,addrName);  
    } 
    else
    {
       
   		document.getElementById('citytbl'+addrName).style.display='none';
    	document.getElementById('villagetbl'+addrName).style.display='none';
    	 rdoAddressArr[0].disabled=true;
    	rdoAddressArr[1].disabled=true;
   		 rdoAddressArr[2].disabled=false;
   		 rdoAddressArr[2].checked=true;
    	document.getElementById(otherTable).style.display='';
    	document.getElementById('tdCmbDistrict'+addrName).style.display='none';
    	document.getElementById('tdCmbState'+addrName).style.display='none';
    	document.getElementById('tdTxtState'+addrName).style.display='';
    	document.getElementById('tdTxtDistrict'+addrName).style.display='';
    }
 }
 function checkStateValidation(addrName)
 {
   // alert("textbox"+document.getElementById('txtState'+addrName).style.display);
   //  alert("selbox"+document.getElementById('cmbState'+addrName).style.display);
 
     if(document.getElementById('cmbCountry'+addrName).value == 1)
     {
        return false;
     }
     else
     {
       return true;
     }
 }
 function checkDistrictValidation(addrName)
 {
   		var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   		if(rdoAddressArr[2].checked == true)
   		{
		     if(document.getElementById('cmbCountry'+addrName).value == 1)
		     {
		         
		        return true;
		     }
		     else
		     {
		      
		       return false;
		     } 
		}
		else
		{
		   return false;
		}      
 }  
 function checkAreaValidation(addrName)
 {
    	var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   		  if(rdoAddressArr[2].checked==true)
   		  {
   		    return true;
   		  }
   		  else
   		  {
   		    return false;
   		  }
 } 
 function makeEnableDisable(uniqueName,flag)


{

  var addressParams = new Array();
  addressParams[0]="rdoAddress"+uniqueName;
  addressParams[1]="txtCityHouseName"+uniqueName;
  addressParams[2]="txtSocietyName"+uniqueName;
  addressParams[3]="txtStreet"+uniqueName;
  addressParams[4]="txtArea"+uniqueName;
  addressParams[5]="cmbCity"+uniqueName;
  addressParams[6]="txtareaCityOtherDetails"+uniqueName;
  addressParams[7]="cmbCityLandmark"+uniqueName;
  addressParams[8]="txtCityPincode"+uniqueName;

  addressParams[9]="txtVillageHouseName"+uniqueName;
  addressParams[10]="txtFaliyu"+uniqueName;
  addressParams[11]="cmbVillage"+uniqueName;
  addressParams[12]="cmbTaluka"+uniqueName;
  addressParams[13]="cmbDistrict"+uniqueName;
  addressParams[14]="cmbVillageLandmark"+uniqueName;
  addressParams[15]="txtareaVillageOtherDetails"+uniqueName;
  addressParams[16]="txtVillagePincode"+uniqueName;
  
  
  addressParams[17]="txtOtherHouseName"+uniqueName;
  addressParams[18]="txtOtherSocietyName"+uniqueName;
  addressParams[19]="txtOtherArea"+uniqueName;
  addressParams[20]="cmbOtherDistrict"+uniqueName;
  addressParams[21]="txtOtherDistrict"+uniqueName;
  addressParams[22]="txtOtherTaluka"+uniqueName;
  addressParams[23]="txtOtherCityVillage"+uniqueName;
  addressParams[24]="txtOtherPincode"+uniqueName;
  addressParams[25]="cmbCountry"+uniqueName;	
  addressParams[26]="cmbState"+uniqueName;	
  addressParams[27]="txtState"+uniqueName;
	if(flag==0)
 	{ 	
		  if(document.getElementById(addressParams[0])!= null)
		  {
		     
		     var radioArr = document.getElementsByName(addressParams[0]);
		       radioArr[0].disabled=true;
		        radioArr[1].disabled=true;
		         radioArr[2].disabled=true;
		      // document.getElementById("radioTable"+uniqueName).readonly="readonly"; 
		     //document.getElementById(addressParams[0]).disabled=true;
		  }
		  if(document.getElementById(addressParams[1])!= null)
		  {
		     document.getElementById(addressParams[1]).disabled=true;
		    // document.getElementById(addressParams[1]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[2])!= null)
		  {
		     document.getElementById(addressParams[2]).disabled=true;
		    //document.getElementById(addressParams[2]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[3])!= null)
		  {
		     document.getElementById(addressParams[3]).disabled=true;
		    // document.getElementById(addressParams[3]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[4])!= null)
		  {
		     document.getElementById(addressParams[4]).disabled=true;
		     //document.getElementById(addressParams[4]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[5])!= null)
		  {
		     document.getElementById(addressParams[5]).disabled=true;
		  }
		  if(document.getElementById(addressParams[6])!= null)
		  {
		     document.getElementById(addressParams[6]).disabled=true;
		    // document.getElementById(addressParams[6]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[7])!= null)
		  {
		     document.getElementById(addressParams[7]).disabled=true;
		  }
		  if(document.getElementById(addressParams[8])!= null)
		  {
		     document.getElementById(addressParams[8]).disabled=true;
		    // document.getElementById(addressParams[8]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[9])!= null)
		  {
		     document.getElementById(addressParams[9]).disabled=true;
		    // document.getElementById(addressParams[9]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[10])!= null)
		  {
		     document.getElementById(addressParams[10]).disabled=true;
		    // document.getElementById(addressParams[10]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[11])!= null)
		  {
		     document.getElementById(addressParams[11]).disabled=true;
		  }
		  if(document.getElementById(addressParams[12])!= null)
		  {
		     document.getElementById(addressParams[12]).disabled=true;
		  }
		  if(document.getElementById(addressParams[13])!= null)
		  {
		     document.getElementById(addressParams[13]).disabled=true;
		  }
		  if(document.getElementById(addressParams[14])!= null)
		  {
		     document.getElementById(addressParams[14]).disabled=true;
		  }
		  if(document.getElementById(addressParams[15])!= null)
		  {
		     document.getElementById(addressParams[15]).disabled=true;
		    // document.getElementById(addressParams[15]).readonly="readonly";
		  }
		   if(document.getElementById(addressParams[16])!= null)
		  {
		     document.getElementById(addressParams[16]).disabled=true;
		    // document.getElementById(addressParams[16]).readonly="readonly";
		  }
		  
		   if(document.getElementById(addressParams[17])!= null) 
		   {
		   document.getElementById(addressParams[17]).disabled=true;
		   }
		   if(document.getElementById(addressParams[18])!= null) 
		   {
		   document.getElementById(addressParams[18]).disabled=true;
		   }
		   if(document.getElementById(addressParams[19])!= null) 
		   {
		   document.getElementById(addressParams[19]).disabled=true;
		   }
		     if(document.getElementById(addressParams[20])!= null) 
		   {
		   document.getElementById(addressParams[20]).disabled=true;
		   }
		     if(document.getElementById(addressParams[21])!= null) 
		   {
		   document.getElementById(addressParams[21]).disabled=true;
		   }
		     if(document.getElementById(addressParams[22])!= null) 
		   {
		   document.getElementById(addressParams[22]).disabled=true;
		   }
		     if(document.getElementById(addressParams[23])!= null) 
		   {
		   document.getElementById(addressParams[23]).disabled=true;
		   }
		     if(document.getElementById(addressParams[24])!= null) 
		   {
		   document.getElementById(addressParams[24]).disabled=true;
		   }
		     if(document.getElementById(addressParams[25])!= null) 
		   {
		   document.getElementById(addressParams[25]).disabled=true;
		   }
		     if(document.getElementById(addressParams[26])!= null) 
		   {
		   document.getElementById(addressParams[26]).disabled=true;
		   }
		     if(document.getElementById(addressParams[27])!= null) 
		   {
		   document.getElementById(addressParams[27]).disabled=true;
		   }
	}
	else
	{
		if(document.getElementById(addressParams[0])!= null)
		  {
		     
		     var radioArr = document.getElementsByName(addressParams[0]);
		     radioArr[0].disabled=false;
		     radioArr[1].disabled=false;
		     radioArr[2].disabled=false;
		      // document.getElementById("radioTable"+uniqueName).readonly="readonly"; 
		     //document.getElementById(addressParams[0]).disabled=true;
		  }
		  if(document.getElementById(addressParams[1])!= null)
		  {
		     document.getElementById(addressParams[1]).disabled=false;
		    // document.getElementById(addressParams[1]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[2])!= null)
		  {
		     document.getElementById(addressParams[2]).disabled=false;
		    //document.getElementById(addressParams[2]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[3])!= null)
		  {
		     document.getElementById(addressParams[3]).disabled=false;
		    // document.getElementById(addressParams[3]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[4])!= null)
		  {
		     document.getElementById(addressParams[4]).disabled=false;
		     //document.getElementById(addressParams[4]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[5])!= null)
		  {
		     document.getElementById(addressParams[5]).disabled=false;
		  }
		  if(document.getElementById(addressParams[6])!= null)
		  {
		     document.getElementById(addressParams[6]).disabled=false;
		    // document.getElementById(addressParams[6]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[7])!= null)
		  {
		     document.getElementById(addressParams[7]).disabled=false;
		  }
		  if(document.getElementById(addressParams[8])!= null)
		  {
		     document.getElementById(addressParams[8]).disabled=false;
		    // document.getElementById(addressParams[8]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[9])!= null)
		  {
		     document.getElementById(addressParams[9]).disabled=false;
		    // document.getElementById(addressParams[9]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[10])!= null)
		  {
		     document.getElementById(addressParams[10]).disabled=false;
		    // document.getElementById(addressParams[10]).readonly="readonly";
		  }
		  if(document.getElementById(addressParams[11])!= null)
		  {
		     document.getElementById(addressParams[11]).disabled=false;
		  }
		  if(document.getElementById(addressParams[12])!= null)
		  {
		     document.getElementById(addressParams[12]).disabled=false;
		  }
		  if(document.getElementById(addressParams[13])!= null)
		  {
		     document.getElementById(addressParams[13]).disabled=false;
		  }
		  if(document.getElementById(addressParams[14])!= null)
		  {
		     document.getElementById(addressParams[14]).disabled=false;
		  }
		  if(document.getElementById(addressParams[15])!= null)
		  {
		     document.getElementById(addressParams[15]).disabled=false;
		    // document.getElementById(addressParams[15]).readonly="readonly";
		  }
		   if(document.getElementById(addressParams[16])!= null)
		  {
		     document.getElementById(addressParams[16]).disabled=false;
		    // document.getElementById(addressParams[16]).readonly="readonly";
		  }
		  
		   if(document.getElementById(addressParams[17])!= null) 
		   {
		   document.getElementById(addressParams[17]).disabled=false;
		   }
		   if(document.getElementById(addressParams[18])!= null) 
		   {
		   document.getElementById(addressParams[18]).disabled=false;
		   }
		   if(document.getElementById(addressParams[19])!= null) 
		   {
		   document.getElementById(addressParams[19]).disabled=false;
		   }
		     if(document.getElementById(addressParams[20])!= null) 
		   {
		   document.getElementById(addressParams[20]).disabled=false;
		   }
		     if(document.getElementById(addressParams[21])!= null) 
		   {
		   document.getElementById(addressParams[21]).disabled=false;
		   }
		     if(document.getElementById(addressParams[22])!= null) 
		   {
		   document.getElementById(addressParams[22]).disabled=false;
		   }
		     if(document.getElementById(addressParams[23])!= null) 
		   {
		   document.getElementById(addressParams[23]).disabled=false;
		   }
		     if(document.getElementById(addressParams[24])!= null) 
		   {
		   document.getElementById(addressParams[24]).disabled=false;
		   }
		     if(document.getElementById(addressParams[25])!= null) 
		   {
		   document.getElementById(addressParams[25]).disabled=false;
		   }
		     if(document.getElementById(addressParams[26])!= null) 
		   {
		   document.getElementById(addressParams[26]).disabled=false;
		   }
		     if(document.getElementById(addressParams[27])!= null) 
		   {
		   document.getElementById(addressParams[27]).disabled=false;
		   }
	}		   
}