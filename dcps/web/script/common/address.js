var editFlag=false;
var langlatdest;

function hideAddress(a1,a2,targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName)
{
	document.getElementById('othertbl'+addrName).style.display='none';	
	document.getElementById(a1).style.display='';
	var langlat = document.getElementById("hiddenlanglat"+addrName).value;
	if(langlat == 'Y')
	{
		document.getElementById('langlat'+addrName).style.display='';
	}
	document.getElementById(a2).style.display='none';
	if(editFlag==false)
	{	
		populateFirstLevel(targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName);	
		closeAddress(addrName,true,false);
	 }		
}

function showAddress(a1,a2,targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName)
{
	document.getElementById('othertbl'+addrName).style.display='none';
	var langlat = document.getElementById("hiddenlanglat"+addrName).value;
	if(langlat == 'Y')
	{
		document.getElementById('langlat'+addrName).style.display='';
	}
	document.getElementById(a2).style.display='';
	document.getElementById(a1).style.display='none';
	 if(editFlag==false)
	  {
	populateFirstLevel(targetCombo,actionFlag,ctxPath,srcId,srcVal,addrName);
	closeAddress(addrName,true,false);
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
		showProgressbar();
		var isAsynch=true;
		var empAddress = document.getElementById('employeeAddress'+uniqueName).value;
		if(empAddress == 'Y')
		{
			isAsynch=false;
		}
		else
		{
			isAsynch=true;
		}
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
	
        var myAjax = new Ajax.Request(url,
        		{
        					method: 'post',
        					asynchronous: isAsynch,
        					onSuccess: processResponseFirstLevel,
        					onFailure: function(){ alert('Something went wrong in populate first level...') } 
        		} );
}

var keySrcDest = "";
var cachedData = new JSOC();

function populateDropDownWithCachedData(key,dropDownId)
{
	var dropDown = document.getElementById(dropDownId);
	clearList(dropDown);
	
	var cacheObj = cachedData.get(key);
	var cachedArray = cacheObj[key];
	
	for ( var i = 0 ; i < cachedArray.length ; i++ )
	{
		var temp = cachedArray[i]
		var myOpt = new Option(temp.text,temp.value)
		dropDown.options[(i+1)] = myOpt;
		//dropDown.add(temp); 
	}
}

function isCachedData(srcLevelId,nextLevelId)
{
	var srcObj = document.getElementById(srcLevelId);
	var isDistrictSrc = false;
	var isTalukaSrc = false;
	var isCountrySrc = false;
	var isStateSrc = false;
	var isTalukaTarget = false;
	var isVillageTarget = false;
	var isStateTarget   = false;
	var isCountryTarget = false;
	keySrcDest = "";

	if(srcLevelId.toLowerCase().indexOf("district") != -1) isDistrictSrc=true;
	if(srcLevelId.toLowerCase().indexOf("taluka") != -1) isTalukaSrc=true;
	if(nextLevelId.toLowerCase().indexOf("taluka") != -1) isTalukaTarget=true;
	if(nextLevelId.toLowerCase().indexOf("village") != -1) isVillageTarget=true;
	
	if(srcLevelId.toLowerCase().indexOf("country") != -1) isCountrySrc=true;
	if(nextLevelId.toLowerCase().indexOf("country") != -1) isCountryTarget=true;
	if(srcLevelId.toLowerCase().indexOf("state") != -1) isStateSrc=true;
	if(nextLevelId.toLowerCase().indexOf("state") != -1)  isStateTarget=true;
	
	if(isDistrictSrc && isTalukaTarget)
	{
		keySrcDest = "DistrictTaluka_" + srcObj.value;
	}
	if(isTalukaSrc && isVillageTarget)
	{
		keySrcDest = "TalukaVillage_" + srcObj.value;
	}
	
	if(isCountrySrc && isStateTarget)
	{
		keySrcDest = "CountryState_" + srcObj.value;
	}
	if(isCountrySrc && isCountryTarget)
	{
		keySrcDest = "Country_" + srcObj.value;
	}
	if(isStateSrc && isStateTarget)
	{
		keySrcDest = "State_" + srcObj.value;
	}
	if(cachedData.get(keySrcDest) != undefined )
	{
		return true;
	}
	else
	{
		return false;
	}
}

function populateNextLevel(srcLevelId,nextLevelId,actionFlag,ctxPath,sourceId,uniqueName) 
{
	var srcObj = document.getElementById(srcLevelId);
	var checkCachedData = isCachedData(srcLevelId,nextLevelId);
	if(checkCachedData)
	{
		populateDropDownWithCachedData(keySrcDest,nextLevelId)
	}
	else
	{
		showProgressbar();
		var isAsynch=true;
		if(editFlag==true)
		{
			isAsynch=false;
		}
		nxtCombo=nextLevelId;
	    addrName=uniqueName;      
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
		var myAjax = new Ajax.Request(url,
        		{
        					method: 'post',
        					asynchronous: isAsynch,
        					onSuccess: processResponseNextLevel,
        					onFailure: function(){ alert('Something went wrong in populate level next...') } 
        		} );		
	}
}

function populateLandmarks(nextLevelId,actionFlag,ctxPath,sourceId,lookupName,typeCode,uniqueName)
{
	//showProgressbar();
    nxtCombo=nextLevelId;
    var isAsynch=true;
    if(editFlag==true)
    {
    isAsynch=false;
    }
    var htmFile=document.getElementById('addrHtmName'+uniqueName).value;
	glbHtmFile=htmFile;
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
         var url = ctxPath+glbHtmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+lookupName+"&typeCode="+typeCode;   
		//if(z.length == 1)
         //	{
         var myAjax = new Ajax.Request(url,
         		{
         					method: 'post',
         					asynchronous: isAsynch,
         					onSuccess: processResponseLandmarks,
         					onFailure: function(){ alert('Something went wrong...') } 
         		} );
		//}
}


function processResponseNextLevel(myAjax)
{
	var text;
	var z=document.getElementById(nxtCombo);
	var XMLDoc=myAjax.responseXML.documentElement;
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
    	var tempArray = new Array();	

		for ( var i = 0 ; i < entries.length ; i++ )
			{
				text=entries[i].childNodes[0].firstChild.nodeValue;   
			    value=entries[i].childNodes[1].firstChild.nodeValue;

				var y=document.createElement('option');
				y.text=value;
				y.value=text;
				tempArray[i] = y;
				try
				{
					z.add(y,null); 
				}
				catch(ex)
				{
					z.add(y); 
				}     					
			}	
	    if( (keySrcDest != "") && (cachedData.get(keySrcDest) == undefined) )
			{
				cachedData.add(keySrcDest,tempArray);
				keySrcDest = "";
			}
	 }			    	     
	hideProgressbar();	
}


function processResponseFirstLevel(myAjax)
{
	var text;
	var z=document.getElementById(nxtCombo);
	var XMLDoc=myAjax.responseXML.documentElement;
	if(XMLDoc!= null)
	{
		 if(z.length > 1)
		  {
		     clearList(z);
		  }
		var entries = XMLDoc.getElementsByTagName('element');	
		for ( var i = 0 ; i < entries.length ; i++ )
		{
			text=entries[i].childNodes[0].firstChild.nodeValue;   
		    value=entries[i].childNodes[1].firstChild.nodeValue;
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
	hideProgressbar();							            	
}

function processResponseLandmarks(myAjax)
{
	var text;
	var z=document.getElementById(nxtCombo);
	var XMLDoc=myAjax.responseXML.documentElement;
	if(XMLDoc!= null)
	{
	  	 if(z.length > 1)
		  {
		     clearList(z);
		  }
	 var cityList=document.getElementById("cmbCity"+addrName);
	 var villageList=document.getElementById("cmbVillage"+addrName);
	 if(cityList!= null)
	 {	   
	 		
		   var cLandmarks=document.getElementById("cmbCityLandmark"+addrName);
		   if(cityList.value=='Select')
		   {
			   clearList(cLandmarks);
			   cityList.options[0].selected="selected";
			   cLandmarks.options[0].selected="selected";
		   }
	}
	  if(villageList!= null)
	 {	   
    	   var vLandmarks=document.getElementById("cmbVillageLandmark"+addrName);
		   if(villageList.value=='Select')
		   {
			   clearList(vLandmarks);
			   villageList.options[0].selected="selected";
			   vLandmarks.options[0].selected="selected";
		   }
	}
	var entries = XMLDoc.getElementsByTagName('element');	
	for ( var i = 0 ; i < entries.length ; i++ )
		{
			text=entries[i].childNodes[0].firstChild.nodeValue;   
		    value=entries[i].childNodes[1].firstChild.nodeValue;
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
	//hideProgressbar();	
}

function addressParameters(addrName,lookUpName)
{
	var addressParams = new Array();
	var langlat = document.getElementById("hiddenlanglat"+addrName).value;
	var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
	if(employeeAddress == 'Y')
	{
		addressParams[0]="txtempAddHouseName"+addrName;
		addressParams[1]="txtempAddSocietyName"+addrName;
		addressParams[2]="txtempAddStreet"+addrName;
		addressParams[3]="txtempAddArea"+addrName;
		addressParams[4]="cmbempAddDistrict"+addrName;
		addressParams[5]="cmbempAddTaluka"+addrName;
		addressParams[6]="txtareaempAddOtherDetails"+addrName;
		addressParams[7]="txtempAddPincode"+addrName;
		addressParams[8]="cmbCountry"+addrName;	
		addressParams[9]="cmbState"+addrName;	
		addressParams[10]=addrName;
		addressParams[11]=lookUpName;
		addressParams[12]="employeeAddress"+addrName;
		if(document.getElementById('cmbCountry'+addrName).value != 1)
		{
			addressParams[13]="txtempAddDistrict"+addrName;
			addressParams[14]="txtState"+addrName;	
			addressParams[15]="txtempAddTaluka"+addrName;	
		}
		else
		{
		if(document.getElementById('cmbState'+addrName).value != 1)
		{
			addressParams[13]="txtempAddTaluka"+addrName;	
		}
		}
	}
	else
	{
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
				
			if(langlat == 'Y')
			{
				addressParams[9]="txtlang"+addrName;	
				addressParams[10]="txtlat"+addrName;
				addressParams[11]=addrName;
				addressParams[12]=lookUpName;	
				addressParams[13]="cmbCountry"+addrName;	
				addressParams[14]="cmbState"+addrName;
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
						addressParams[15]="txtState"+addrName;	
				}
			}
			else
			{
				addressParams[9]=addrName;
				addressParams[10]=lookUpName;	
				addressParams[11]="cmbCountry"+addrName;	
				addressParams[12]="cmbState"+addrName;
			if(document.getElementById('cmbCountry'+addrName).value != 1)
			{
					addressParams[13]="txtState"+addrName;	
			}
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
			if(langlat == 'Y')
			{
				addressParams[13]="txtlang"+addrName;	
				addressParams[14]="txtlat"+addrName;
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
					addressParams[15]="txtState"+addrName;	
				}
			}
			else
			{
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
					addressParams[13]="txtState"+addrName;	
				}
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
			if(langlat == 'Y')
			{
				addressParams[12]="txtlang"+addrName;	
				addressParams[13]="txtlat"+addrName;
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
				addressParams[14]="txtOtherDistrict"+addrName;
				addressParams[15]="txtState"+addrName;	
				}
			}
			else
			{
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
				addressParams[12]="txtOtherDistrict"+addrName;
				addressParams[13]="txtState"+addrName;	
				}
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
	
			if(langlat == 'Y')
			{
				addressParams[28]="txtlang"+addrName;	
				addressParams[29]="txtlat"+addrName;
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
				addressParams[30]="txtOtherDistrict"+addrName;
				addressParams[31]="txtState"+addrName;	
				}
			}
			else
			{
				if(document.getElementById('cmbCountry'+addrName).value != 1)
				{
				addressParams[28]="txtOtherDistrict"+addrName;
				addressParams[29]="txtState"+addrName;	
				}
			}
	
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
  addressParams[28]="sameAsLink"+uniqueName;
  addressParams[29]="resetAddressLink"+uniqueName;
  
  addressParams[30]="txtlang"+uniqueName;
  addressParams[31]="txtlat"+uniqueName;
  
  addressParams[32]="txtempAddHouseName"+uniqueName;
  addressParams[33]="txtempAddSocietyName"+uniqueName;
  addressParams[34]="txtempAddStreet"+uniqueName;
  addressParams[35]="txtempAddArea"+uniqueName;
  addressParams[36]="cmbempAddDistrict"+uniqueName;
  addressParams[37]="cmbempAddTaluka"+uniqueName;
  addressParams[38]="txtareaempAddOtherDetails"+uniqueName;
  addressParams[39]="txtempAddPincode"+uniqueName;
  addressParams[40]="txtempAddTaluka"+uniqueName;
  addressParams[41]="txtempAddDistrict"+uniqueName;
  
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
   if(document.getElementById(addressParams[28])!= null) 
   {
   
   document.getElementById(addressParams[28]).href = '#';
   document.getElementById(addressParams[28]).onclick = '';
    document.getElementById(addressParams[28]).disabled=true;
   }
   if(document.getElementById(addressParams[29])!= null) 
   {
   
   document.getElementById(addressParams[29]).href = '#';
   document.getElementById(addressParams[29]).onclick = '';
    document.getElementById(addressParams[29]).disabled=true;
   }
   if(document.getElementById(addressParams[30])!= null) 
   {
	   document.getElementById(addressParams[30]).disabled=true;
   }
   if(document.getElementById(addressParams[31])!= null) 
   {
	   document.getElementById(addressParams[31]).disabled=true;
   }
   
   if(document.getElementById(addressParams[32])!= null) 
   {
	   document.getElementById(addressParams[32]).disabled=true;
   }
   if(document.getElementById(addressParams[33])!= null) 
   {
	   document.getElementById(addressParams[33]).disabled=true;
   }
   if(document.getElementById(addressParams[34])!= null) 
   {
	   document.getElementById(addressParams[34]).disabled=true;
   }
   if(document.getElementById(addressParams[35])!= null) 
   {
	   document.getElementById(addressParams[35]).disabled=true;
   }
   if(document.getElementById(addressParams[36])!= null) 
   {
	   document.getElementById(addressParams[36]).disabled=true;
   }
   if(document.getElementById(addressParams[37])!= null) 
   {
	   document.getElementById(addressParams[37]).disabled=true;
   }
   if(document.getElementById(addressParams[38])!= null) 
   {
	   document.getElementById(addressParams[38]).disabled=true;
   }
   if(document.getElementById(addressParams[39])!= null) 
   {
	   document.getElementById(addressParams[39]).disabled=true;
   }
   if(document.getElementById(addressParams[40])!= null) 
   {
	   document.getElementById(addressParams[40]).disabled=true;
   }
   if(document.getElementById(addressParams[41])!= null) 
   {
	   document.getElementById(addressParams[41]).disabled=true;
   }
   
}
 
function editAddress(addrName,xmlDOM,addrXPath)
  {
    
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
    	//added by khushali
    	var longitude = getXPathValueFromDOM(xmlDOM,addrXPath +'/longitude');	   	
    	var latitude =  getXPathValueFromDOM(xmlDOM,addrXPath +'/latitude');
    	//end by khushali
    	
		var houseName  = getXPathValueFromDOM(xmlDOM,addrXPath +'/houseName');				
//		alert("houseName : :"+houseName);
		var impLandmark = getXPathValueFromDOM(xmlDOM, addrXPath +'/impLandmark/landmarkCode');		
//		alert("impLandmark : :"+impLandmark);		
		var otherDetails = getXPathValueFromDOM(xmlDOM,addrXPath +'/otherDetails');
//		alert("otherDetails : :"+otherDetails);	
		var pincode     = getXPathValueFromDOM(xmlDOM, addrXPath +'/pincode');
//		alert("pincode : :"+pincode);
//		var lookupName  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnLookupMstAddTypeLookupid/lookupName');
//		alert("lookupName : :"+lookupName);
		 cityCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnCityMst/cityCode');
//		alert("cityCode : :"+cityCode);
		villageCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnVillageMst/villageCode');
		
		 talukaCode  = getXPathValueFromDOM(xmlDOM,addrXPath +'/cmnTalukaMst/talukaCode');
		
		var countryCode  = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnCountryMst/countryCode');
		
		var stateCode    = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnStateMst/stateCode');
		
		var lookup = getXPathValueFromDOM(xmlDOM,addrXPath +'/cmnLookupMstAddTypeLookupid/lookupName');
		
		var empAddress = document.getElementById('employeeAddress'+addrName).value;
		
		if(stateCode != null)
		{
			var defaultStateCode = document.getElementById('addressDefaultStateCode'+addrName).value;
			document.getElementById('tdCmbState'+addrName).style.display='';
			document.getElementById('tdTxtState'+addrName).style.display='none';   
			if(empAddress == 'Y')
			{
				 document.getElementById('tdCmbempAddDistrict'+addrName).style.display='';
				 document.getElementById('tdTxtempAddDistrict'+addrName).style.display='none';
				 if(stateCode == defaultStateCode)
				 {
					 document.getElementById('tdcmbempAddTaluka'+addrName).style.display='';
					 document.getElementById('tdTxtempAddTaluka'+addrName).style.display='none';
				 }
				 else
				 {
					 document.getElementById('tdcmbempAddTaluka'+addrName).style.display='none';
					 document.getElementById('tdTxtempAddTaluka'+addrName).style.display='';
				 }
			}
			else
			{
		    document.getElementById('tdCmbDistrict'+addrName).style.display='';
			}
		}
		else
		{
			if(empAddress != 'Y')
			{
				document.getElementById('tdTxtDistrict'+addrName).style.display='';
			}
			else
			{
				document.getElementById('tdCmbState'+addrName).style.display='none';
				document.getElementById('tdTxtState'+addrName).style.display='';   
				document.getElementById('tdTxtempAddDistrict'+addrName).style.display='';
				document.getElementById('tdCmbempAddDistrict'+addrName).style.display='none';
				document.getElementById('tdcmbempAddTaluka'+addrName).style.display='none';
				document.getElementById('tdTxtempAddTaluka'+addrName).style.display='';
			}
		}
	
//		alert("villageCode::"+villageCode);	
		var cityvillageName = getXPathValueFromDOM(xmlDOM,addrXPath + '/cityVilllageName');
		addressParams[0]="rdoAddress"+addrName;
		var rdoAddressArr = document.getElementsByName(addressParams[0]);
					
		//added by khushali
		var isReadOnly = "No";
		isReadOnly = document.getElementById('isReadOnly'+addrName).value;	
		
		var contextPath=document.getElementById('addrContextPath'+addrName).value;
		var countryArr = document.getElementsByName('cmbCountry'+addrName);
		var stateArr   = document.getElementsByName('cmbState'+addrName);
		if(countryArr!= null)
			{

		   if(countryArr[0]!= null)
		   {
		   var options = countryArr[0].options;
			
		   if(options.length == 3 && options[2].value=='OtherCountry')
		  
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
		   if((options.length == 1) ||(options.length == 3 && options[2].value=='OtherState'))
		 
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
		if(empAddress == 'Y')
		{
			if(stateCode != null)
			{
				populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
			}
		}
		if(lookup != 'EmployeeAddress' && empAddress != 'Y')
		{
		if(cityCode!=null)
		{			
			var area     = getXPathValueFromDOM(xmlDOM,addrXPath + '/area');
	//		alert("area::"+area);		
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
    	
    		//added by khushali
    		if(isReadOnly == "Yes")
			{
				rdoAddressArr[0].disabled=true;
				rdoAddressArr[1].disabled=true;
			}
    		
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

					      if(options.length >= 1)
					      {
					      var cityCodeVal = document.getElementById('cmbCity'+addrName).value;
					      populateLandmarks('cmbCityLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','CityLandmarks',cityCodeVal,addrName);
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
		
	 else if(talukaCode != null)
	 {
		
		var faliyu     = getXPathValueFromDOM(xmlDOM,addrXPath +'/faliyu');
//			alert("faliyu::"+faliyu);
		
		
	//	var talukaCode  = getXPathValueFromDOM(xmlDOM,addrXPath +'/cmnTalukaMst/talukaCode');
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
			
			//added by khushali
			if(isReadOnly == 'Yes')
			{
				rdoAddressArr[0].disabled=true;
				rdoAddressArr[1].disabled=true;
			}
			
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
					      var villageCodeVal = document.getElementById('cmbVillage'+addrName).value;
					      populateLandmarks('cmbVillageLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','VillageLandmarks',villageCodeVal,addrName);
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
 		
 		
 		//added by khushali
 		if(isReadOnly == "Yes")
		{
 			rdoAddressArr[2].disabled=true; 
		}
 		
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
	//added by Khushali
	addressParams[26]="txtlang"+addrName;
	addressParams[27]="txtlat"+addrName;
	var longitudeArr 	   = document.getElementsByName(addressParams[26]);
	var latitudeArr 	   = document.getElementsByName(addressParams[27]);
	if(longitudeArr!= null)
	{
		if(longitudeArr[0]!= null)
		{
			if(longitude != null)
			{
				longitudeArr[0].value=longitude;
			}
			else
			{
				longitudeArr[0].value="";
			}
		}
	}
	if(latitudeArr!= null)
	{
		if(latitudeArr[0]!= null)
		{
			if(latitude != null)
			{
				latitudeArr[0].value=latitude;
		    }
		    else
		    {
		    	latitudeArr[0].value="";
		    }
		}
	}
	////end by Khushali
	
	else
	{
		closeAddress(addrName);
	}
    }
	var empAddress = document.getElementById('employeeAddress'+addrName).value;
	if(empAddress == 'Y')
	{
		var area             = getXPathValueFromDOM(xmlDOM,addrXPath + '/area');
		var houseName  		 = getXPathValueFromDOM(xmlDOM,addrXPath +'/houseName');	
		var socBuildName     = getXPathValueFromDOM(xmlDOM,addrXPath + '/socBuildName');
		var districtCode     = getXPathValueFromDOM(xmlDOM,addrXPath + '/cmnDistrictMst/districtCode');
		var districtName     = getXPathValueFromDOM(xmlDOM,addrXPath + '/districtName');
		var talukaName       = getXPathValueFromDOM(xmlDOM,addrXPath + '/talukaName');
		var stateName        = getXPathValueFromDOM(xmlDOM,addrXPath + '/stateName');
		var area     		 = getXPathValueFromDOM(xmlDOM,addrXPath + '/area');	
		var street    		 = getXPathValueFromDOM(xmlDOM, addrXPath +'/street');
		var talukaCode  	 = getXPathValueFromDOM(xmlDOM,addrXPath +'/cmnTalukaMst/talukaCode');
		  
		addressParams[28]="txtempAddHouseName"+addrName;
		addressParams[29]="txtempAddSocietyName"+addrName;
		addressParams[30]="txtempAddStreet"+addrName;
		addressParams[31]="txtempAddArea"+addrName;
		addressParams[32]="cmbempAddDistrict"+addrName;
		addressParams[33]="cmbempAddTaluka"+addrName;
		addressParams[34]="txtareaempAddOtherDetails"+addrName;
		addressParams[35]="txtempAddPincode"+addrName;
		addressParams[36]="txtempAddDistrict"+addrName;
		addressParams[37]="txtempAddTaluka"+addrName;
		addressParams[38]="txtState"+addrName;
		
		var txtempAddHouseNameArr   = document.getElementsByName(addressParams[28]); 
		var txtempAddSocietyNameArr = document.getElementsByName(addressParams[29]); 
		var txtempAddStreetArr        = document.getElementsByName(addressParams[30]); 
 		var txtempAddAreaArr      = document.getElementsByName(addressParams[31]); 
 		var cmbempAddDistrictArr      = document.getElementsByName(addressParams[32]); 
 		var cmbempAddTalukaArr      = document.getElementsByName(addressParams[33]); 
 		var txtareaempAddOtherDtlsArr     = document.getElementsByName(addressParams[34]); 
 		var txtempAddPincodeArr    = document.getElementsByName(addressParams[35]);
 		var txtempAddDistrictArr    = document.getElementsByName(addressParams[36]);
 		var txtempAddTalukaArr    = document.getElementsByName(addressParams[37]);
 		var otherTxtStateArr = document.getElementsByName(addressParams[38]);
 		
 		if(txtempAddHouseNameArr!= null)
 		{
 			if(txtempAddHouseNameArr[0]!= null)
 			{
 				if(houseName != null)
 				{
 					txtempAddHouseNameArr[0].value = houseName;
 				}
 				else
 				{
 					txtempAddHouseNameArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddSocietyNameArr!= null)
 		{
 			if(txtempAddSocietyNameArr[0]!= null)
 			{
 				if(socBuildName != null)
 				{
 					txtempAddSocietyNameArr[0].value = socBuildName;
 				}
 				else
 				{
 					txtempAddSocietyNameArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddStreetArr!= null)
 		{
 			if(txtempAddStreetArr[0]!= null)
 			{
 				if(street != null)
 				{
 					txtempAddStreetArr[0].value = street;
 				}
 				else
 				{
 					txtempAddStreetArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddAreaArr!= null)
 		{
 			if(txtempAddAreaArr[0]!= null)
 			{
 				if(area != null)
 				{
 					txtempAddAreaArr[0].value = area;
 				}
 				else
 				{
 					txtempAddAreaArr[0].value="";
 				}
 			}
 		}
 		
		if(txtareaempAddOtherDtlsArr!= null)
 		{
 			if(txtareaempAddOtherDtlsArr[0]!= null)
 			{
 				if(otherDetails  != null)
 				{
 					txtareaempAddOtherDtlsArr[0].value = otherDetails ;
 				}
 				else
 				{
 					txtareaempAddOtherDtlsArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddPincodeArr!= null)
 		{
 			if(txtempAddPincodeArr[0]!= null)
 			{
 				if(pincode  != null)
 				{
 					txtempAddPincodeArr[0].value = pincode ;
 				}
 				else
 				{
 					txtempAddPincodeArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddDistrictArr!= null)
 		{
 			if(txtempAddDistrictArr[0]!= null)
 			{
 				if(districtName  != null)
 				{
 					txtempAddDistrictArr[0].value = districtName ;
 				}
 				else
 				{
 					txtempAddDistrictArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddTalukaArr!= null)
 		{
 			if(txtempAddTalukaArr[0]!= null)
 			{
 				if(talukaName  != null)
 				{
 					txtempAddTalukaArr[0].value = talukaName;
 				}
 				else
 				{
 					txtempAddTalukaArr[0].value="";
 				}
 			}
 		}
		
		if(cmbempAddDistrictArr!= null)
 		{
   
		    if(cmbempAddDistrictArr[0]!= null)
		    {
		    var options = cmbempAddDistrictArr[0].options;
		        if(options.length == 1)
		        {
		        populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
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
		
		if(cmbempAddTalukaArr!= null)
 		{
   
		    if(cmbempAddTalukaArr[0]!= null)
		    {
		    var options = cmbempAddTalukaArr[0].options;
		        if(options.length == 1)
		        {
		        populateNextLevel('cmbempAddDistrict'+addrName,'cmbempAddTaluka'+addrName,'getAllTaluka',contextPath,'districtCode',addrName)
		        }
			       for(var d=0;d<options.length;d++)
				     {
				        if(options[d].value == talukaCode)
				        {
				         options[d].selected="selected";
				        }
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
	
   }
   catch(ex)
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
		var impLandmark = getValueFromDOM(xmlDOM, 'landmarkCode');
		var otherDetails= getValueFromDOM(xmlDOM, 'otherDetails');
		var pincode     = getValueFromDOM(xmlDOM, 'pincode');
		cityCode      = getValueFromDOM(xmlDOM, 'cityCode');
		 villageCode   = getValueFromDOM(xmlDOM, 'villageCode');
		 talukaCode  = getValueFromDOM(xmlDOM,'talukaCode');
		 var countryCode  = getValueFromDOM(xmlDOM, 'countryCode');
		
		var stateCode    = getValueFromDOM(xmlDOM, 'stateCode');
		
		var longitude = getValueFromDOM(xmlDOM, 'longitude');
		var latitude  = getValueFromDOM(xmlDOM, 'latitude');
		
		var empAddress = document.getElementById('employeeAddress'+addrName).value;
		
		if(stateCode != null)
		{
			var defaultStateCode = document.getElementById('addressDefaultStateCode'+addrName).value;
			document.getElementById('tdCmbState'+addrName).style.display='';
			document.getElementById('tdTxtState'+addrName).style.display='none';   
			if(empAddress == 'Y')
			{
				 document.getElementById('tdCmbempAddDistrict'+addrName).style.display='';
				 document.getElementById('tdTxtempAddDistrict'+addrName).style.display='none';
				 if(stateCode == defaultStateCode)
				 {
					 document.getElementById('tdcmbempAddTaluka'+addrName).style.display='';
					 document.getElementById('tdTxtempAddTaluka'+addrName).style.display='none';
				 }
				 else
				 {
					 document.getElementById('tdcmbempAddTaluka'+addrName).style.display='none';
					 document.getElementById('tdTxtempAddTaluka'+addrName).style.display='';
				 }
			}
			else
			{
		    document.getElementById('tdCmbDistrict'+addrName).style.display='';
			}
		}
		else
		{
			if(empAddress != 'Y')
			{
				document.getElementById('tdTxtDistrict'+addrName).style.display='';
			}
			else
			{
				document.getElementById('tdCmbState'+addrName).style.display='none';
				document.getElementById('tdTxtState'+addrName).style.display='';   
				document.getElementById('tdTxtempAddDistrict'+addrName).style.display='';
				document.getElementById('tdCmbempAddDistrict'+addrName).style.display='none';
				document.getElementById('tdcmbempAddTaluka'+addrName).style.display='none';
				document.getElementById('tdTxtempAddTaluka'+addrName).style.display='';
			}
		}
//		alert("villageCode::"+villageCode);	
		var cityvillageName = getValueFromDOM(xmlDOM, 'cityVilllageName');
		addressParams[0]="rdoAddress"+addrName;
		var rdoAddressArr = document.getElementsByName(addressParams[0]);
	    
		//added by khushali
		var isReadOnly = "No";
		isReadOnly = document.getElementById('isReadOnly'+addrName).value;	
		
		var contextPath=document.getElementById('addrContextPath'+addrName).value;
		var countryArr = document.getElementsByName('cmbCountry'+addrName);
		var stateArr   = document.getElementsByName('cmbState'+addrName);
				if(countryArr!= null)
		 			{
		   
					   if(countryArr[0]!= null)
					   {
					   var options = countryArr[0].options;
						
					   if(options.length == 3 && options[2].value=='OtherCountry')
					
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
						
					  if((options.length == 1) ||(options.length == 3 && options[2].value=='OtherState'))
					 
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
		 			if(empAddress == 'Y')
		 			{
		 				if(stateCode != null)
		 				{
		 					populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
		 				}
		 			}
	if(lookup != 'EmployeeAddress' && empAddress != 'Y')
	{
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
			
			//added by khushali
			if(isReadOnly == "Yes")
			{
				rdoAddressArr[0].disabled=true;
				rdoAddressArr[1].disabled=true;
			}
			
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
					      var cityCodeVal = document.getElementById('cmbCity'+addrName).value;
					      populateLandmarks('cmbCityLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','CityLandmarks',cityCodeVal,addrName);
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
	 else if(talukaCode != null)
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
			
			//added by khushali
			if(isReadOnly == 'Yes')
			{
				rdoAddressArr[0].disabled=true;
				rdoAddressArr[1].disabled=true;
			}
			
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
					      var villageCodeVal = document.getElementById('cmbVillage'+addrName).value;
					      populateLandmarks('cmbVillageLandmark'+addrName,'getAllLandmark',contextPath,'landmarkType','VillageLandmarks',villageCodeVal,addrName);
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
 		
 		//added by khushali
		if(isReadOnly == "Yes")
  		{
			rdoAddressArr[2].disabled=true; 
		}
		
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
	//added by Khushali
		addressParams[26]="txtlang"+addrName;
		addressParams[27]="txtlat"+addrName;
		var longitudeArr 	   = document.getElementsByName(addressParams[26]);
		var latitudeArr 	   = document.getElementsByName(addressParams[27]);
		if(longitudeArr!= null)
		{
			if(longitudeArr[0]!= null)
			{
				if(longitude != null)
				{
					longitudeArr[0].value=longitude;
				}
				else
				{
					longitudeArr[0].value="";
				}
			}
		}
		if(latitudeArr!= null)
		{
			if(latitudeArr[0]!= null)
			{
				if(latitude != null)
				{
					latitudeArr[0].value=latitude;
			    }
			    else
			    {
			    	latitudeArr[0].value="";
			    }
			}
		}
		////end by Khushali
	else
	{
		closeAddress(addrName);
	}
	}
	var empAddress = document.getElementById('employeeAddress'+addrName).value;
	if(empAddress == 'Y')
	{
		var area             = getValueFromDOM(xmlDOM, 'area');
		var houseName  		 = getValueFromDOM(xmlDOM,'houseName');	
		var socBuildName     = getValueFromDOM(xmlDOM,'socBuildName');
		var districtCode     = getValueFromDOM(xmlDOM, 'districtCode');
		var districtName     = getValueFromDOM(xmlDOM,'districtName');
		var talukaName       = getValueFromDOM(xmlDOM, 'talukaName');
		var stateName        = getValueFromDOM(xmlDOM, 'stateName');
		var area     		 = getValueFromDOM(xmlDOM, 'area');	
		var street    		 = getValueFromDOM(xmlDOM,'street');
		var talukaCode  	 = getValueFromDOM(xmlDOM,'talukaCode');
		
	      
		addressParams[28]="txtempAddHouseName"+addrName;
		addressParams[29]="txtempAddSocietyName"+addrName;
		addressParams[30]="txtempAddStreet"+addrName;
		addressParams[31]="txtempAddArea"+addrName;
		addressParams[32]="cmbempAddDistrict"+addrName;
		addressParams[33]="cmbempAddTaluka"+addrName;
		addressParams[34]="txtareaempAddOtherDetails"+addrName;
		addressParams[35]="txtempAddPincode"+addrName;
		addressParams[36]="txtempAddDistrict"+addrName;
		addressParams[37]="txtempAddTaluka"+addrName;
		addressParams[38]="txtState"+addrName;
		
		var txtempAddHouseNameArr   = document.getElementsByName(addressParams[28]); 
		var txtempAddSocietyNameArr = document.getElementsByName(addressParams[29]); 
		var txtempAddStreetArr        = document.getElementsByName(addressParams[30]); 
 		var txtempAddAreaArr      = document.getElementsByName(addressParams[31]); 
 		var cmbempAddDistrictArr      = document.getElementsByName(addressParams[32]); 
 		var cmbempAddTalukaArr      = document.getElementsByName(addressParams[33]); 
 		var txtareaempAddOtherDtlsArr     = document.getElementsByName(addressParams[34]); 
 		var txtempAddPincodeArr    = document.getElementsByName(addressParams[35]);
 		var txtempAddDistrictArr    = document.getElementsByName(addressParams[36]);
 		var txtempAddTalukaArr    = document.getElementsByName(addressParams[37]);
 		var otherTxtStateArr = document.getElementsByName(addressParams[38]);
 		
 		if(txtempAddHouseNameArr!= null)
 		{
 			if(txtempAddHouseNameArr[0]!= null)
 			{
 				if(houseName != null)
 				{
 					txtempAddHouseNameArr[0].value = houseName;
 				}
 				else
 				{
 					txtempAddHouseNameArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddSocietyNameArr!= null)
 		{
 			if(txtempAddSocietyNameArr[0]!= null)
 			{
 				if(socBuildName != null)
 				{
 					txtempAddSocietyNameArr[0].value = socBuildName;
 				}
 				else
 				{
 					txtempAddSocietyNameArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddStreetArr!= null)
 		{
 			if(txtempAddStreetArr[0]!= null)
 			{
 				if(street != null)
 				{
 					txtempAddStreetArr[0].value = street;
 				}
 				else
 				{
 					txtempAddStreetArr[0].value="";
 				}
 			}
 		}
 		
 		if(txtempAddAreaArr!= null)
 		{
 			if(txtempAddAreaArr[0]!= null)
 			{
 				if(area != null)
 				{
 					txtempAddAreaArr[0].value = area;
 				}
 				else
 				{
 					txtempAddAreaArr[0].value="";
 				}
 			}
 		}
 		
		if(txtareaempAddOtherDtlsArr!= null)
 		{
 			if(txtareaempAddOtherDtlsArr[0]!= null)
 			{
 				if(otherDetails  != null)
 				{
 					txtareaempAddOtherDtlsArr[0].value = otherDetails ;
 				}
 				else
 				{
 					txtareaempAddOtherDtlsArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddPincodeArr!= null)
 		{
 			if(txtempAddPincodeArr[0]!= null)
 			{
 				if(pincode  != null)
 				{
 					txtempAddPincodeArr[0].value = pincode ;
 				}
 				else
 				{
 					txtempAddPincodeArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddDistrictArr!= null)
 		{
 			if(txtempAddDistrictArr[0]!= null)
 			{
 				if(districtName  != null)
 				{
 					txtempAddDistrictArr[0].value = districtName ;
 				}
 				else
 				{
 					txtempAddDistrictArr[0].value="";
 				}
 			}
 		}
		
		if(txtempAddTalukaArr!= null)
 		{
 			if(txtempAddTalukaArr[0]!= null)
 			{
 				if(talukaName  != null)
 				{
 					txtempAddTalukaArr[0].value = talukaName;
 				}
 				else
 				{
 					txtempAddTalukaArr[0].value="";
 				}
 			}
 		}
		
		if(cmbempAddDistrictArr!= null)
 		{
   
		    if(cmbempAddDistrictArr[0]!= null)
		    {
		    var options = cmbempAddDistrictArr[0].options;
		        if(options.length == 1)
		        {
		        populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
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
		
		if(cmbempAddTalukaArr!= null)
 		{
   
		    if(cmbempAddTalukaArr[0]!= null)
		    {
		    var options = cmbempAddTalukaArr[0].options;
		        if(options.length == 1)
		        {
		        populateFirstLevel('cmbempAddTaluka'+addrName,'getAllDistrict',contextPath,'stateCode',stateCode,addrName);
		        }
			       for(var d=0;d<options.length;d++)
				     {
				        if(options[d].value == talukaCode)
				        {
				         options[d].selected="selected";
				        }
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
			
			addressParams[25]="txtlang"+addrName;
			addressParams[26]="txtlat"+addrName;
			
			addressParams[27]="txtempAddHouseName"+addrName;
			addressParams[28]="txtempAddSocietyName"+addrName;
			addressParams[29]="txtempAddStreet"+addrName;
			addressParams[30]="txtempAddArea"+addrName;
			addressParams[31]="cmbempAddDistrict"+addrName;
			addressParams[32]="cmbempAddTaluka"+addrName;
			addressParams[33]="txtareaempAddOtherDetails"+addrName;
			addressParams[34]="txtempAddPincode"+addrName;
			addressParams[35]="txtempAddDistrict"+addrName;
			addressParams[36]="txtempAddTaluka"+addrName;
		
	    
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
		 
		 var txtlangArr    = document.getElementsByName(addressParams[25]);
		 var txtlatArr     = document.getElementsByName(addressParams[26]);
		 
		 var txtempAddHouseNameArr     = document.getElementsByName(addressParams[27]);
		 var txtempAddSocietyNameArr   = document.getElementsByName(addressParams[28]);
		 var txtempAddStreetArr        = document.getElementsByName(addressParams[29]);
		 var txtempAddAreaArr          = document.getElementsByName(addressParams[30]);
		 var cmbempAddDistrictArr      = document.getElementsByName(addressParams[31]);
		 var cmbempAddTalukaArr        = document.getElementsByName(addressParams[32]);
		 var txtempAddOtherDtlsArr     = document.getElementsByName(addressParams[33]);
		 var txtempAddPincodeArr       = document.getElementsByName(addressParams[34]);
		 var txtempAddDistrictArr      = document.getElementsByName(addressParams[35]);
		 var txtempAddTalukaArr        = document.getElementsByName(addressParams[36]);
		 
		 if(txtlangArr!= null)
		 {
		    if(txtlangArr[0]!= null)
		    {
		    	txtlangArr[0].value="";
		    }
		 }
		 if(txtlatArr!= null)
		 {
		    if(txtlatArr[0]!= null)
		    {
		    	txtlatArr[0].value="";
		    }
		 }
		 
		 
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
		 
		 if(txtempAddHouseNameArr != null)
		  {
		      if(txtempAddHouseNameArr[0]!= null)
		      {
		    	  txtempAddHouseNameArr[0].value="";
		      }
		  }  
		 
		 if(txtempAddSocietyNameArr != null)
		  {
		      if(txtempAddSocietyNameArr[0]!= null)
		      {
		    	  txtempAddSocietyNameArr[0].value="";
		      }
		  }  
		 
		 if(txtempAddStreetArr != null)
		  {
		      if(txtempAddStreetArr[0]!= null)
		      {
		    	  txtempAddStreetArr[0].value="";
		      }
		  }  
		 
		 if(txtempAddAreaArr != null)
		  {
		      if(txtempAddAreaArr[0]!= null)
		      {
		    	  txtempAddAreaArr[0].value="";
		      }
		  }  
		 
		 if(txtempAddOtherDtlsArr != null)
		  {
		      if(txtempAddOtherDtlsArr[0]!= null)
		      {
		    	  txtempAddOtherDtlsArr[0].value="";
		      }
		  } 
		 
		 if(txtempAddPincodeArr != null)
		  {
		      if(txtempAddPincodeArr[0]!= null)
		      {
		    	  txtempAddPincodeArr[0].value="";
		      }
		  } 
		 
		 if(txtempAddDistrictArr != null)
		  {
		      if(txtempAddDistrictArr[0]!= null)
		      {
		    	  txtempAddDistrictArr[0].value="";
		      }
		  } 
		 
		 if(txtempAddTalukaArr != null)
		  {
		      if(txtempAddTalukaArr[0]!= null)
		      {
		    	  txtempAddTalukaArr[0].value="";
		      }
		  }
		 
	     if(cmbempAddDistrictArr != null)
		 {
		   
		    if(cmbempAddDistrictArr[0]!= null)
		    {
		    var options = cmbempAddDistrictArr[0].options;
		      if(options[0]!= null)
		      {
		    options[0].selected="selected";
		      }
		    }
		 }
	     
	     if(cmbempAddTalukaArr != null)
		 {
		   
		    if(cmbempAddTalukaArr[0]!= null)
		    {
		    var options = cmbempAddTalukaArr[0].options;
		      if(options[0]!= null)
		      {
		    options[0].selected="selected";
		      }
		    }
		 }
		 
  }
  
  function closeAddress(addrName,isEmptyRequired,isCloseRequired)
  {
   var rdoAddress="rdoAddress"+addrName;
   var rdoAddressArr = document.getElementsByName(rdoAddress);
   
   var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+addrName).value;
   var defaultCountryName = document.getElementById('addressDefaultCountryName'+addrName).value;
   var defaultStateCode = document.getElementById('addressDefaultStateCode'+addrName).value;
   var defaultStateName = document.getElementById('addressDefaultStateName'+addrName).value;
   var countryArr     = document.getElementsByName("cmbCountry"+addrName);
   var stateArr     = document.getElementsByName("cmbState"+addrName);
   var addressParams = new Array();
   var value = new Array();
   var Text = new Array();
   addressParams[0]="cmbCountry"+addrName;
   addressParams[1]="cmbState"+addrName;
   
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
	   if(document.getElementById("cmbempAddTaluka"+addrName)!= null)
	   {
	      clearList(document.getElementById("cmbempAddTaluka"+addrName));
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
    
    if (document.getElementById("langlat"+addrName) != null)
    {
    	document.getElementById("langlat"+addrName).style.display='none';
    }
    //change by khushali

    document.getElementById("tdCmbState"+addrName).style.display='';
    document.getElementById("tdCmbState"+addrName).value = "";
    document.getElementById("tdTxtState"+addrName).style.display='none';
    document.getElementById("txtState"+addrName).value = "";
    document.getElementById("cmbState"+addrName).value = defaultStateCode;
    document.getElementById("cmbCountry"+addrName).value = defaultCountryCode;
    if(document.getElementById("cmbState"+addrName)!= null)
	   {
	      clearList(document.getElementById("cmbState"+addrName));
	   }
    if(document.getElementById("cmbCountry"+addrName)!= null)
	   {
	      clearList(document.getElementById("cmbCountry"+addrName));
	   }
    if(stateArr != null)
    {
    	if(stateArr[0]!= null)
	    {
			var options = stateArr[0].options;
	    	if(options.length == 1)
	    	{
	    		var z=document.getElementById(addressParams[1]);
	    		value[1] = defaultStateCode;
				value[2] = "OtherState";
				Text[1] = defaultStateName;
				Text[2] = fmtStateOther;
				for ( var i = 1 ; i < 3 ; i++ )
 				{
					var y=document.createElement('option');
 					y.text=Text[i];
					y.value=value[i];
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
	    	options.value = defaultStateCode;
	    	}
	    }
    
    if(countryArr != null)
    {
    	if(countryArr[0]!= null)
	    {
			var options = countryArr[0].options;
	    	if(options.length == 1)
	    	{
	    		var z=document.getElementById(addressParams[0]);
	    		value[1] = defaultCountryCode;
				value[2] = "OtherCountry";
				Text[1] = defaultCountryName;
				Text[2] = fmtCountryOther; 
				for ( var i = 1 ; i < 3 ; i++ )
 				{
					var y=document.createElement('option');
 					y.text=Text[i];
					y.value=value[i];
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
	    	options.value = defaultCountryCode;
	    	}
	    }
    
    var empAddress = document.getElementById('employeeAddress'+addrName).value;
    if(empAddress == 'Y')
    {
    	var ctxPath = document.getElementById('addrContextPath'+addrName).value; 
    	populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',ctxPath,'stateCode',defaultStateCode,addrName);
    	document.getElementById("tdTxtempAddTaluka"+addrName).style.display='none';
    	document.getElementById("tdcmbempAddTaluka"+addrName).style.display='';
    	document.getElementById("tdCmbempAddDistrict"+addrName).style.display='';
    	document.getElementById("tdTxtempAddDistrict"+addrName).style.display='none';
    }
  //change end by khushali
    
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
  
    var checkCachedData = isCachedData(firstLevelId,firstLevelId);
	
	if(checkCachedData)
	{
		populateDropDownWithCachedData(keySrcDest,firstLevelId)
	}
	else
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
        var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+sourceVal;///+"&fromAddress=true";
        
        var myAjax = new Ajax.Request(url,
        		{
        					method: 'post',
        					asynchronous: false,
        					onSuccess: processResponseCountry,
        					onFailure: function(){ alert('Something went wrong in populate country function...') } 
        		} );
	}	
		
}

function processResponseCountry(myAjax)
{
	var text;
  	var z=document.getElementById(nxtCombo);
	var XMLDoc=myAjax.responseXML.documentElement;
	if(XMLDoc!= null)
	{
		 if(z.length > 1)
		  {
		     clearList(z);
		  }
		var entries = XMLDoc.getElementsByTagName('element');	
		var tempArray = new Array();
		for ( var i = 0 ; i < entries.length ; i++ )
		{
			text=entries[i].childNodes[0].firstChild.nodeValue;   
		    value=entries[i].childNodes[1].firstChild.nodeValue;   
			var y=document.createElement('option');
			y.text=value;
			y.value=text;
			tempArray[i] = y;	
			try
			{
				z.add(y,null); 
			}
			catch(ex)
			{
				z.add(y); 
			}     						
		}
		if( (keySrcDest != "") && (cachedData.get(keySrcDest) == undefined) )
		{
			cachedData.add(keySrcDest,tempArray);
			keySrcDest = "";
		}
	}		
}

function populateState(srcLevelId,nextLevelId,actionFlag,ctxPath,sourceId,uniqueName) 
{
		var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+uniqueName).value;
		var countryCode = document.getElementById('cmbCountry'+uniqueName).value;
		var employeeAddress = document.getElementById('employeeAddress'+uniqueName).value;
        if(countryCode != defaultCountryCode)
        {
        	var countryValue = document.getElementById("cmbCountry"+uniqueName).value;
    	    if(countryValue=='OtherCountry')
    	    {
        	populateCountries('cmbCountry'+uniqueName,"getAllCountries",ctxPath,'countryCode','',uniqueName);
        	}
    	    //condition
    	    if(employeeAddress != "Y")
        	 {
    	    //condition end 
    	    	if(countryValue!='Select')
    	    	{
    	    		//added by khushali 
    	    		document.getElementById('tdTxtState'+uniqueName).style.display='';
    	    		var rdoAddress="rdoAddress"+uniqueName;
    	    		var rdoAddressArr = document.getElementsByName(rdoAddress);
    	    		rdoAddressArr[0].disabled=true;
    	    		rdoAddressArr[1].disabled=true;
    	    		rdoAddressArr[2].disabled=false;
    	    		rdoAddressArr[2].click();
    	    	}
        	 }
    	    //condition
	    	    else
	    	    {
	    	    	resetAddress(uniqueName);
	    	    	document.getElementById('tdTxtState'+uniqueName).style.display='';
	    	    	document.getElementById('tdCmbState'+uniqueName).style.display='none';
	    	    	document.getElementById('tdTxtempAddDistrict'+uniqueName).style.display='';
	    	    	document.getElementById('tdTxtempAddTaluka'+uniqueName).style.display='';
	    	    	document.getElementById('tdcmbempAddDistrict'+uniqueName).style.display='none';
	    	    	document.getElementById('tdcmbempAddTaluka'+uniqueName).style.display='none';
	    	    }
        	
    	  //condition end 
        }
        else
        {
        	if(employeeAddress != "Y")
       	 	{
        		document.getElementById('tdCmbState'+uniqueName).style.display='';
        		document.getElementById('tdTxtState'+uniqueName).style.display='none';
        		document.getElementById('tdTxtDistrict'+uniqueName).style.display='none';
        		document.getElementById('tdCmbDistrict'+uniqueName).style.display='';
       	 	}
        	else
        	{
        		resetAddress(uniqueName);
        		document.getElementById('tdCmbState'+uniqueName).style.display='';
        		document.getElementById('tdTxtState'+uniqueName).style.display='none';
        		document.getElementById('tdTxtempAddDistrict'+uniqueName).style.display='none';
        		document.getElementById('tdcmbempAddDistrict'+uniqueName).style.display='';
        	}
         }
        var checkCachedData = isCachedData(srcLevelId,nextLevelId);     
        if(checkCachedData)
        {
        	populateDropDownWithCachedData(keySrcDest,nextLevelId)
        }
		else
		{
	    
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
	    	var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+sourceId+"="+srcObj.value;///+"&fromAddress=true";   
		
	    	var myAjax = new Ajax.Request(url,
	    				{
	    							method: 'post',
	    							asynchronous: false,
	    							onSuccess: processResponseState,
	    							onFailure: function(){ alert('Something went wrong in populate state method...') } 
	    				} );
		}
}

function processResponseState(myAjax)
{
	var text;
	var z=document.getElementById(nxtCombo);
	var XMLDoc=myAjax.responseXML.documentElement;
	if(XMLDoc!= null)
	{
	 	 if(z.length > 1)
		  {
		     clearList(z);
		  }
	 
	 
	var entries = XMLDoc.getElementsByTagName('element');	
	var tempArray = new Array();

	for ( var i = 0 ; i < entries.length ; i++ )
		{
			text=entries[i].childNodes[0].firstChild.nodeValue;   
		    value=entries[i].childNodes[1].firstChild.nodeValue;
		   
			var y=document.createElement('option');
			y.text=value;
			y.value=text;
			tempArray[i] = y;	
			try
			{
				z.add(y,null); 
			}
			catch(ex)
			{
				z.add(y); 
			}     					
		}
		 if( (keySrcDest != "") && (cachedData.get(keySrcDest) == undefined) )
		{
			cachedData.add(keySrcDest,tempArray);
			keySrcDest = "";
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
		var defaultStateCode = document.getElementById('addressDefaultStateCode'+addrName).value;
  		var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+addrName).value;

  		resetAddress(addrName);
 		var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   		var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
    if(countryCode==defaultCountryCode && stateCode==defaultStateCode)
    {
    	if(employeeAddress == 'Y')
	    {
			 populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',ctxPath,'stateCode',stateCode,addrName);
			 if(document.getElementById('tdTxtState'+addrName)!= null)
			 {
					document.getElementById('tdTxtState'+addrName).style.display='none';
			 }
			 if(document.getElementById('tdCmbState'+addrName)!= null)
			 {
			    	document.getElementById('tdCmbState'+addrName).style.display='';
			 }
			 document.getElementById('tdcmbempAddTaluka'+addrName).style.display='';
			 document.getElementById('tdTxtempAddTaluka'+addrName).style.display='none';
	    }
    	else
    	{
    	rdoAddressArr[0].disabled=false;
		rdoAddressArr[1].disabled=false;
		rdoAddressArr[2].disabled=true;    
		rdoAddressArr[2].checked=false;
		if(document.getElementById('tdTxtState'+addrName)!= null)
		{
			document.getElementById('tdTxtState'+addrName).style.display='none';
	    }
	    if(document.getElementById('tdCmbState'+addrName)!= null)
	    {
	    	document.getElementById('tdCmbState'+addrName).style.display='';
	    }
		document.getElementById(otherTable).style.display='none';
	    document.getElementById('langlat'+addrName).style.display='none';
    	}    
    }
    else if(countryCode==defaultCountryCode&&stateCode!=defaultStateCode)
    {
    	var stateValue = document.getElementById("cmbState"+addrName).value;
    	if(stateValue=='OtherState')
    	{
    	populateState("cmbCountry"+addrName,"cmbState"+addrName,"getAllStates",ctxPath,"countryCode",addrName);
    	}
    	if(stateValue!='Select')
    	{
    		if(employeeAddress == 'Y')
    		{
    			document.getElementById('employeeAddresstbl'+addrName).style.display='';
    			document.getElementById('tdTxtempAddTaluka'+addrName).style.display='';
    			document.getElementById('tdcmbempAddTaluka'+addrName).style.display='none';
    			 populateFirstLevel('cmbempAddDistrict'+addrName,'getAllDistrict',ctxPath,'stateCode',stateCode,addrName);
    		}
    		else
    		{
		    	document.getElementById('citytbl'+addrName).style.display='none';
		    	document.getElementById('villagetbl'+addrName).style.display='none';
		    	rdoAddressArr[0].disabled=true;
		    	rdoAddressArr[1].disabled=true;
		    	rdoAddressArr[2].disabled=false;
		    	rdoAddressArr[2].checked=true;
		    	if(document.getElementById('tdTxtState'+addrName)!= null)                                                                                             
		    	{
		    		document.getElementById('tdTxtState'+addrName).style.display='none';
		    	}
		    	if(document.getElementById('tdCmbState'+addrName)!= null)
		    	{
		    		document.getElementById('tdCmbState'+addrName).style.display='';
		    	}
		    	document.getElementById(otherTable).style.display='';
		    	var langlat = document.getElementById("hiddenlanglat"+addrName).value;
		    	if(langlat == 'Y')
		    	{
		    		document.getElementById('langlat'+addrName).style.display='';
		    	}
		    	if(document.getElementById('tdTxtDistrict'+addrName)!= null)
		    	{
		    		document.getElementById('tdTxtDistrict'+addrName).style.display='none';
		    	}
		    	populateFirstLevel('cmbOtherDistrict'+addrName,'getAllDistrict',ctxPath,'stateCode',stateCode,addrName);
    		}//end of if state value not select
    	} 
    }
    else
    {
   		 //condition
   		var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
		if(employeeAddress == 'Y')
		{
			document.getElementById('employeeAddresstbl'+addrName).style.display='';
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
			var langlat = document.getElementById("hiddenlanglat"+addrName).value;
			if(langlat == 'Y')
			{
	    	document.getElementById('langlat'+addrName).style.display='';
			}
			if(document.getElementById('tdCmbDistrict'+addrName)!= null)
	    	    {
	    	document.getElementById('tdCmbDistrict'+addrName).style.display='none';
	    	   }
	    	 if(document.getElementById('tdCmbState'+addrName)!= null)
	    	    {
	    	document.getElementById('tdCmbState'+addrName).style.display='none';
	    	    }
	    	 if(document.getElementById('tdTxtState'+addrName)!= null)
	    	   {
	    	document.getElementById('tdTxtState'+addrName).style.display='';
	    	   }
	    	 if(document.getElementById('tdTxtDistrict'+addrName)!= null)
			  {
	    	document.getElementById('tdTxtDistrict'+addrName).style.display='';
	    	  }
		}
		//complete
    }
 }
 
function checkStateValidation(addrName,stateType)
 {


	 var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+addrName).value;
     if(document.getElementById('cmbCountry'+addrName).value == defaultCountryCode)
     {
	        if(stateType == 'Text')
	        {
	        return false;
	        }
	        else
	        {
	        return true;
	        }
     }
     else
     {
       if(stateType == 'Combo')
        {
       return false;
        }
        else
        {
        return true;
        }
     }
 }

function checkDistrictValidation(addrName)
 {
   		var rdoAddress="rdoAddress"+addrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   		var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+addrName).value;
   		var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
   		if(rdoAddressArr[2].checked == true)
   		{
   			 if(document.getElementById('cmbCountry'+addrName).value == defaultCountryCode)
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
 
function checkempDistrictValidation(addrName)
 {
   		var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+addrName).value;
   		var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
   		if(employeeAddress == 'Y')
   		{
   			if(document.getElementById('cmbCountry'+addrName).value == defaultCountryCode)
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
   		var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
   		if(rdoAddressArr[2].checked==true)
   		  {
   				return true;
   		  }
   		  else
   		  {
   			  return false;
   		  }		
 } 
 
function checkempAreaValidation(addrName)
 {
	 var employeeAddress = document.getElementById('employeeAddress'+addrName).value;
	 if(employeeAddress != 'Y')
	 {
		 return false;
	 }
	 else
	 {
		 return true;
	 } 
 }
 
function copyAddress(srcAddrName,destAddrName,ctxPath)
 {
    
      var defaultCountryCode = document.getElementById('addressDefaultCountryCode'+srcAddrName).value;
      var defaultStateCode   = document.getElementById('addressDefaultStateCode'+srcAddrName).value;
     var countryCode = document.getElementById('cmbCountry'+srcAddrName).value;
     var countryOptions = document.getElementById('cmbCountry'+srcAddrName).options;
     /*alert('country::'+countryOptions.length);
		 for(var c=0;c<countryOptions.length;c++)
		     {
		        if(countryOptions[c].value == countryCode)
			        {
			        
			         countryOptions[c].selected="selected";
			        }
		     }  */
	 copyDropDownOptions(document.getElementById('cmbCountry'+srcAddrName),document.getElementById('cmbCountry'+destAddrName),countryCode);
       var stateCode   =  document.getElementById('cmbState'+srcAddrName).value;
       var stateName   = document.getElementById('txtState'+srcAddrName).value;
       var distirctName=document.getElementById('txtOtherDistrict'+srcAddrName).value;
		  if(countryCode == defaultCountryCode)
		  {
		    
		     document.getElementById('tdCmbState'+destAddrName).style.display='';
		     document.getElementById('tdTxtState'+destAddrName).style.display='none';
		     document.getElementById('tdCmbDistrict'+destAddrName).style.display='';
		     document.getElementById('tdTxtDistrict'+destAddrName).style.display='none';
		     var stateOptions = document.getElementById('cmbState'+srcAddrName).options;
		    copyDropDownOptions(document.getElementById('cmbState'+srcAddrName),document.getElementById('cmbState'+destAddrName),stateCode);
		    /*  for(var s=0;s<stateOptions.length;s++)
		     {
		        if(stateOptions[s].value == stateCode)
			        {
			         stateOptions[s].selected="selected";
			        }
		     }*/
		        
		  }
		  else
		  {
		 	
		     document.getElementById('tdCmbState'+destAddrName).style.display='none';
		     document.getElementById('tdTxtState'+destAddrName).style.display='';
		     document.getElementById('txtState'+destAddrName).value=stateName;
		     document.getElementById('tdCmbDistrict'+destAddrName).style.display='none';
		     document.getElementById('tdTxtDistrict'+destAddrName).style.display='';
		     document.getElementById('txtOtherDistrict'+destAddrName).value=distirctName;
		      
		  } 
		var employeeAddress = document.getElementById('employeeAddress'+destAddrName).value;
		if(employeeAddress == 'Y')
		{
			 document.getElementById('txtempAddHouseName'+destAddrName).value = document.getElementById('txtempAddHouseName'+srcAddrName).value;
			 document.getElementById('txtempAddSocietyName'+destAddrName).value = document.getElementById('txtempAddSocietyName'+srcAddrName).value;
			 document.getElementById('txtempAddStreet'+destAddrName).value = document.getElementById('txtempAddStreet'+srcAddrName).value;
			 document.getElementById('txtempAddArea'+destAddrName).value = document.getElementById('txtempAddArea'+srcAddrName).value;
			 document.getElementById('txtareaempAddOtherDetails'+destAddrName).value = document.getElementById('txtareaempAddOtherDetails'+srcAddrName).value;
			 document.getElementById('txtempAddPincode'+destAddrName).value = document.getElementById('txtempAddPincode'+srcAddrName).value;
	   		   var stateCode = document.getElementById('cmbState'+srcAddrName).value;
	   		   if(stateCode == defaultStateCode)
	   		   {
	   			   document.getElementById('tdcmbempAddTaluka'+destAddrName).style.display='';
	   			   document.getElementById('tdTxtempAddTaluka'+destAddrName).style.display='none';
	   	  		   var cmbTalukaSrc = document.getElementById("cmbempAddTaluka"+srcAddrName); 
		   		   var cmbTalukaDest = document.getElementById("cmbempAddTaluka"+destAddrName); 
		   		   var talukaCode = document.getElementById('cmbempAddTaluka'+srcAddrName).value;
		   		     if(cmbTalukaDest.length > 1)
		   		     {
		   		        clearList(cmbTalukaDest);
		   		     }
		   		   copyDropDownOptions(cmbTalukaSrc,cmbTalukaDest,talukaCode);
	   		   }
	   		   else
	   		   {
	   			   document.getElementById('tdcmbempAddTaluka'+destAddrName).style.display='none';
	   			   document.getElementById('tdTxtempAddTaluka'+destAddrName).style.display='';
	   			   document.getElementById('txtempAddTaluka'+destAddrName).value = document.getElementById('txtempAddTaluka'+srcAddrName).value;
	   		   }
	   		  if(countryCode == defaultCountryCode)
			  {
	   		  		document.getElementById('tdCmbempAddDistrict'+destAddrName).style.display='';
   			   		document.getElementById('tdTxtempAddDistrict'+destAddrName).style.display='none';
   			   		var cmbDistirctSrc = document.getElementById("cmbempAddDistrict"+srcAddrName); 
   			   		var cmbDistrictDest = document.getElementById("cmbempAddDistrict"+destAddrName); 
   			   		var districtCode = document.getElementById('cmbempAddDistrict'+srcAddrName).value;
		   		    if(cmbDistrictDest.length > 1)
		   		     {
		   		        clearList(cmbDistrictDest);
		   		     }
		   		   copyDropDownOptions(cmbDistirctSrc,cmbDistrictDest,districtCode);
			  }
	   		  else
	   		  {
	   			  	document.getElementById('tdCmbempAddDistrict'+destAddrName).style.display='none';
			   		document.getElementById('tdTxtempAddDistrict'+destAddrName).style.display='';
			   		document.getElementById('txtempAddDistrict'+destAddrName).value = document.getElementById('txtempAddDistrict'+srcAddrName).value;
	   		  }
		}
		else
		{
	    var rdoAddress="rdoAddress"+srcAddrName;
   		var rdoAddressArr = document.getElementsByName(rdoAddress);
   	    var rdoAddressDest="rdoAddress"+destAddrName;
   		var rdoAddressArrDest = document.getElementsByName(rdoAddressDest);
		if(rdoAddressArr[2].checked==true)
		{
		   document.getElementById('othertbl'+destAddrName).style.display='';
		   var langlat = document.getElementById("hiddenlanglat"+destAddrName).value;
			if(langlat == 'Y')
			{
			document.getElementById('langlat'+destAddrName).style.display='';
			}
		   document.getElementById('citytbl'+destAddrName).style.display='none';
		   document.getElementById('villagetbl'+destAddrName).style.display='none';
		  
   		   rdoAddressArrDest[0].disabled=true;
    	   rdoAddressArrDest[1].disabled=true;
   		   rdoAddressArrDest[2].disabled=false;
   		   rdoAddressArrDest[2].checked=true;
   		   var cmbOtherDistirctSrc = document.getElementById("cmbOtherDistrict"+srcAddrName); 
   		   var cmbOtherDistrictDest = document.getElementById("cmbOtherDistrict"+destAddrName); 
   		   var districtCode = document.getElementById('cmbOtherDistrict'+srcAddrName).value;
   		     if(cmbOtherDistrictDest.length > 1)
   		     {
   		        clearList(cmbOtherDistrictDest);
   		     }
   		   copyDropDownOptions(cmbOtherDistirctSrc,cmbOtherDistrictDest,districtCode);
   		   document.getElementById('txtOtherHouseName'+destAddrName).value = document.getElementById('txtOtherHouseName'+srcAddrName).value;
		   document.getElementById('txtOtherSocietyName'+destAddrName).value = document.getElementById('txtOtherSocietyName'+srcAddrName).value;
		   document.getElementById('txtOtherArea'+destAddrName).value = document.getElementById('txtOtherArea'+srcAddrName).value;
		   document.getElementById('txtOtherTaluka'+destAddrName).value = document.getElementById('txtOtherTaluka'+srcAddrName).value;
		   document.getElementById('txtOtherCityVillage'+destAddrName).value = document.getElementById('txtOtherCityVillage'+srcAddrName).value;
		   document.getElementById('txtOtherPincode'+destAddrName).value = document.getElementById('txtOtherPincode'+srcAddrName).value;
		   if(document.getElementById('hiddenlanglat'+srcAddrName).value == 'Y')
		   {	
			   document.getElementById('langlat'+destAddrName).style.display='';
			   document.getElementById('hiddenlanglat'+destAddrName).value = document.getElementById('hiddenlanglat'+srcAddrName).value;
			   document.getElementById('txtlang'+destAddrName).value = document.getElementById('txtlang'+srcAddrName).value;
			   document.getElementById('txtlat'+destAddrName).value = document.getElementById('txtlat'+srcAddrName).value;
		   }
		   //  document.getElementById('tdCmbDistrict').style.display='none';
		   //  document.getElementById('tdTxtDistrict').style.display='';
		     /*  if(document.getElementById('tdCmbDistrict'+srcAddrName).style.display != 'none')
		       {
		     
		       }else
		       {
		       
		      document.getElementById('txtOtherDistrict'+destAddrName).value = document.getElementById('txtOtherDistrict'+srcAddrName).value; 
		       }*/
		}
		else if(rdoAddressArr[1].checked==true)
		{
			 document.getElementById('othertbl'+destAddrName).style.display='none';
			 document.getElementById('citytbl'+destAddrName).style.display='none';
			 document.getElementById('villagetbl'+destAddrName).style.display='';
			 var langlat = document.getElementById("hiddenlanglat"+destAddrName).value;
				if(langlat == 'Y')
				{
				document.getElementById('langlat'+destAddrName).style.display='';
				}
			
			   rdoAddressArrDest[0].disabled=false;
	    	   rdoAddressArrDest[1].disabled=false;
	   		   rdoAddressArrDest[2].disabled=true;
	   		   rdoAddressArrDest[1].checked=true;
	   		 //copy district options
	   		 
	   		   var cmbDistirctSrc = document.getElementById("cmbDistrict"+srcAddrName); 
	   		   var cmbDistrictDest = document.getElementById("cmbDistrict"+destAddrName); 
	   		   var districtCode = document.getElementById('cmbDistrict'+srcAddrName).value;
	   		     if(cmbDistrictDest.length > 1)
	   		     {
	   		        clearList(cmbDistrictDest);
	   		     }
	   		   copyDropDownOptions(cmbDistirctSrc,cmbDistrictDest,districtCode);
	   		// copy taluka options
	   		
	   		   var cmbTalukaSrc = document.getElementById("cmbTaluka"+srcAddrName); 
	   		   var cmbTalukaDest = document.getElementById("cmbTaluka"+destAddrName); 
	   		   var talukaCode = document.getElementById('cmbTaluka'+srcAddrName).value;
	   		     if(cmbTalukaDest.length > 1)
	   		     {
	   		        clearList(cmbTalukaDest);
	   		     }
	   		   copyDropDownOptions(cmbTalukaSrc,cmbTalukaDest,talukaCode);
	   		   
	   		// copy village options
	   		  
	   		   var cmbVillageSrc = document.getElementById("cmbVillage"+srcAddrName); 
	   		   var cmbVillageDest = document.getElementById("cmbVillage"+destAddrName); 
	   		   var villageCode = document.getElementById('cmbVillage'+srcAddrName).value;
	   		     if(cmbVillageDest.length > 1)
	   		     {
	   		        clearList(cmbVillageDest);
	   		     }
	   		   copyDropDownOptions(cmbVillageSrc,cmbVillageDest,villageCode); 
	   		   
	   		// copy villagelandmark options
	   		
	   		    var cmbVillageLandmarkSrc = document.getElementById("cmbVillageLandmark"+srcAddrName); 
	   		   var cmbVillageLandmarkDest = document.getElementById("cmbVillageLandmark"+destAddrName); 
	   		   var villageLandmarkCode = document.getElementById('cmbVillageLandmark'+srcAddrName).value;
	   		     if(cmbVillageLandmarkDest.length > 1)
	   		     {
	   		        clearList(cmbVillageLandmarkDest);
	   		     }
	   		   copyDropDownOptions(cmbVillageLandmarkSrc,cmbVillageLandmarkDest,villageLandmarkCode);
	   		   
	   		//copy textboxes
	   		
	   		document.getElementById('txtVillageHouseName'+destAddrName).value = document.getElementById('txtVillageHouseName'+srcAddrName).value;      
	   		document.getElementById('txtFaliyu'+destAddrName).value = document.getElementById('txtFaliyu'+srcAddrName).value;      
	   		document.getElementById('txtareaVillageOtherDetails'+destAddrName).value = document.getElementById('txtareaVillageOtherDetails'+srcAddrName).value;      
	   		document.getElementById('txtVillagePincode'+destAddrName).value = document.getElementById('txtVillagePincode'+srcAddrName).value;
	   		if(document.getElementById('hiddenlanglat'+srcAddrName).value == 'Y')
		   {	
	   		 document.getElementById('langlat'+destAddrName).style.display='';
	   		 document.getElementById('hiddenlanglat'+destAddrName).value = document.getElementById('hiddenlanglat'+srcAddrName).value;
			   document.getElementById('txtlang'+destAddrName).value = document.getElementById('txtlang'+srcAddrName).value;
			   document.getElementById('txtlat'+destAddrName).value = document.getElementById('txtlat'+srcAddrName).value;
		   }
		 
		}
		else if(rdoAddressArr[0].checked==true)
		{
			document.getElementById('othertbl'+destAddrName).style.display='none';
			document.getElementById('citytbl'+destAddrName).style.display='';
			var langlat = document.getElementById("hiddenlanglat"+destAddrName).value;
			if(langlat == 'Y')
			{
			document.getElementById('langlat'+destAddrName).style.display='';
			}
			document.getElementById('villagetbl'+destAddrName).style.display='none';
			   rdoAddressArrDest[0].disabled=false;
	    	   rdoAddressArrDest[1].disabled=false;
	   		   rdoAddressArrDest[2].disabled=true;
	   		   rdoAddressArrDest[0].checked=true;
	   		   
	   		 //copy city options
	   		 
	   		   var cmbCitySrc = document.getElementById("cmbCity"+srcAddrName); 
	   		   var cmbCityDest = document.getElementById("cmbCity"+destAddrName); 
	   		   var cityCode = document.getElementById('cmbCity'+srcAddrName).value;
	   		     if(cmbCityDest.length > 1)
	   		     {
	   		        clearList(cmbCityDest);
	   		     }
	   		   copyDropDownOptions(cmbCitySrc,cmbCityDest,cityCode);   
	   		   
	   		 // copy citylandmark options
	   		
	   		    var cmbCityLandmarkSrc = document.getElementById("cmbCityLandmark"+srcAddrName); 
	   		   var cmbCityLandmarkDest = document.getElementById("cmbCityLandmark"+destAddrName); 
	   		   var cityLandmarkCode = document.getElementById('cmbCityLandmark'+srcAddrName).value;
	   		     if(cmbCityLandmarkDest.length > 1)
	   		     {
	   		        clearList(cmbCityLandmarkDest);
	   		     }
	   		   copyDropDownOptions(cmbCityLandmarkSrc,cmbCityLandmarkDest,cityLandmarkCode);  
	   		   document.getElementById('txtCityHouseName'+destAddrName).value = document.getElementById('txtCityHouseName'+srcAddrName).value;      
	   		   document.getElementById('txtSocietyName'+destAddrName).value = document.getElementById('txtSocietyName'+srcAddrName).value;      
	   		    document.getElementById('txtStreet'+destAddrName).value = document.getElementById('txtStreet'+srcAddrName).value;      
	   		   document.getElementById('txtareaCityOtherDetails'+destAddrName).value = document.getElementById('txtareaCityOtherDetails'+srcAddrName).value;      
	   		    document.getElementById('txtArea'+destAddrName).value = document.getElementById('txtArea'+srcAddrName).value;      
	   		   document.getElementById('txtCityPincode'+destAddrName).value = document.getElementById('txtCityPincode'+srcAddrName).value;
	   		   if(document.getElementById('hiddenlanglat'+srcAddrName).value == 'Y')
			   {
	   			 document.getElementById('langlat'+destAddrName).style.display='';
	   			 document.getElementById('hiddenlanglat'+destAddrName).value = document.getElementById('hiddenlanglat'+srcAddrName).value;
				   document.getElementById('txtlang'+destAddrName).value = document.getElementById('txtlang'+srcAddrName).value;
				   document.getElementById('txtlat'+destAddrName).value = document.getElementById('txtlat'+srcAddrName).value;
			   }
		}
		}
 }
 function copyDropDownOptions(sourceDropDown,destDropDown,srcCode)
 {
   		       var optionsArr = sourceDropDown.options;
   		       for(var i=1;i<optionsArr.length;i++)
   		       { 
	   		       var y=document.createElement('option');
	               y.text=optionsArr[i].text;
	   		       y.value=optionsArr[i].value;
	   		          try
	   		          {
	   		        destDropDown.add(y,null);
	   		          }catch(e)
	   		          {
	   		           destDropDown.add(y);
	   		          }
   		       }
   		  	 var destOptions =  destDropDown.options;	
   		  
   		  	  for(var d=0;d<destOptions.length;d++)
		     {
		     
		        if(destOptions[d].value == srcCode)
			        {
			         destOptions[d].selected="selected";
			        }
		     }	
 }
 
  function removeReadOnly(uniqueName)
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
  addressParams[28]="sameAsLink"+uniqueName;
  addressParams[29]="resetAddressLink"+uniqueName;
  addressParams[30]="txtlang"+uniqueName;
  addressParams[31]="txtlat"+uniqueName;
  
  addressParams[32]="txtempAddHouseName"+uniqueName;
  addressParams[33]="txtempAddSocietyName"+uniqueName;
  addressParams[34]="txtempAddStreet"+uniqueName;
  addressParams[35]="txtempAddArea"+uniqueName;
  addressParams[36]="cmbempAddDistrict"+uniqueName;
  addressParams[37]="cmbempAddTaluka"+uniqueName;
  addressParams[38]="txtareaempAddOtherDetails"+uniqueName;
  addressParams[39]="txtempAddPincode"+uniqueName;
  addressParams[40]="txtempAddTaluka"+uniqueName;
  addressParams[41]="txtempAddDistrict"+uniqueName;
 
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
   if(document.getElementById(addressParams[28])!= null) 
   {
   
   document.getElementById(addressParams[28]).href = '#';
   document.getElementById(addressParams[28]).onclick = '';
    document.getElementById(addressParams[28]).disabled=false;
   }
   
    if(document.getElementById(addressParams[29])!= null) 
   {
   
   document.getElementById(addressParams[29]).href = '#';
   document.getElementById(addressParams[29]).onclick = '';
   document.getElementById(addressParams[29]).disabled=true;
   }
   if(document.getElementById(addressParams[30])!= null) 
   {
	   document.getElementById(addressParams[30]).disabled=false;
   }
   if(document.getElementById(addressParams[31])!= null) 
   {
	   document.getElementById(addressParams[31]).disabled=false;
   }
   
   if(document.getElementById(addressParams[32])!= null) 
   {
	   document.getElementById(addressParams[32]).disabled=false;
   }
   if(document.getElementById(addressParams[33])!= null) 
   {
	   document.getElementById(addressParams[33]).disabled=false;
   }
   if(document.getElementById(addressParams[34])!= null) 
   {
	   document.getElementById(addressParams[34]).disabled=false;
   }
   if(document.getElementById(addressParams[35])!= null) 
   {
	   document.getElementById(addressParams[35]).disabled=false;
   }
   if(document.getElementById(addressParams[36])!= null) 
   {
	   document.getElementById(addressParams[36]).disabled=false;
   }
   if(document.getElementById(addressParams[37])!= null) 
   {
	   document.getElementById(addressParams[37]).disabled=false;
   }
   if(document.getElementById(addressParams[38])!= null) 
   {
	   document.getElementById(addressParams[38]).disabled=false;
   }
   if(document.getElementById(addressParams[39])!= null) 
   {
	   document.getElementById(addressParams[39]).disabled=false;
   }
   if(document.getElementById(addressParams[40])!= null) 
   {
	   document.getElementById(addressParams[40]).disabled=false;
   }
   if(document.getElementById(addressParams[41])!= null) 
   {
	   document.getElementById(addressParams[41]).disabled=false;
   }
}

  function populateAddressOnAjax(addrName,xmlDOM)
  {
  	try
  	{
  		var houseName  = getXPathValueFromDOM(xmlDOM,'houseName');
  		var addressType = getXPathValueFromDOM(xmlDOM,'cmnLookupMstAddTypeLookupid/lookupName');
  		var countryCode  = getXPathValueFromDOM(xmlDOM,'cmnCountryMst/countryCode');
  		var stateCode    = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateCode');
  		var countryName  = getXPathValueFromDOM(xmlDOM,'cmnCountryMst/countryName');
  		
  		if (addressType == 'City')
  		{
  			document.getElementById("employeeAddresstbl"+ addrName).style.display='none';
  			document.getElementById("villagetbl"+ addrName).style.display='none';
  			document.getElementById("othertbl"+ addrName).style.display='none';
  			document.getElementById("citytbl"+ addrName).style.display='';	
  			var langlat = "";

  			if (document.getElementById("hiddenlanglat"+addrName) != null)
  				langlat = document.getElementById("hiddenlanglat"+addrName).value;

  			if(langlat == 'Y')
  			{
  				document.getElementById("langlat"+ addrName).style.display='';
  			}
  		}	
  		else if (addressType == 'Village')
  		{
  			document.getElementById("employeeAddresstbl"+ addrName).style.display='none';
  			document.getElementById("citytbl"+ addrName).style.display='none';	
  			document.getElementById("othertbl"+ addrName).style.display='none';
  			document.getElementById("villagetbl"+ addrName).style.display='';
  			var langlat = "";

  			if (document.getElementById("hiddenlanglat"+addrName) != null)
  				langlat = document.getElementById("hiddenlanglat"+addrName).value;

  			if(langlat == 'Y')
  			{
  				document.getElementById("langlat"+ addrName).style.display='';
  			}
  		}
  		else if (addressType == 'EmployeeAddress')
  		{
  			document.getElementById("employeeAddresstbl"+ addrName).style.display='';	
  			document.getElementById("citytbl"+ addrName).style.display='none';	
  			document.getElementById("othertbl"+ addrName).style.display='none';
  			document.getElementById("villagetbl"+ addrName).style.display='none';
  			var langlat = "";

  			if (document.getElementById("hiddenlanglat"+addrName) != null)
  				langlat = document.getElementById("hiddenlanglat"+addrName).value;

  			if(langlat == 'Y')
  			{
  				document.getElementById("langlat"+ addrName).style.display='';
  			}
  		}
  		else
  		{
  			document.getElementById("employeeAddresstbl"+ addrName).style.display='none';	
  			document.getElementById("citytbl"+ addrName).style.display='none';	
  			document.getElementById("villagetbl"+ addrName).style.display='none';
  			document.getElementById("othertbl"+ addrName).style.display='';
  			var langlat = "";

  			if (document.getElementById("hiddenlanglat"+addrName) != null)
  				langlat = document.getElementById("hiddenlanglat"+addrName).value;

  			if(langlat == 'Y')
  			{
  				document.getElementById("langlat"+ addrName).style.display='';
  			}
  		}


  		if(countryCode==1 && stateCode==1)
  		{
  			document.getElementById('tdTxtState'+addrName).style.display='none';
  			document.getElementById('tdCmbState'+addrName).style.display='block';
  			document.getElementById('tdTxtDistrict'+addrName).style.display='none';
  			document.getElementById('tdCmbDistrict'+addrName).style.display='';

  			if(addressType == 'City')
  			{
  				var socBuildName  = getXPathValueFromDOM(xmlDOM,'socBuildName');
  				var street  = getXPathValueFromDOM(xmlDOM,'street');
  				var area  = getXPathValueFromDOM(xmlDOM,'area');
  				var importantLandmark  = getXPathValueFromDOM(xmlDOM,'impLandmark/landmarkName');
  				var otherDetails  = getXPathValueFromDOM(xmlDOM,'otherDetails');
  				var cityName  = getXPathValueFromDOM(xmlDOM,'cmnCityMst/cityName');
  				var pincode  = getXPathValueFromDOM(xmlDOM,'pincode');
  				var otherDetails  = getXPathValueFromDOM(xmlDOM,'otherDetails');
  				var stateName  = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateName');
  				var longitude = getXPathValueFromDOM(xmlDOM,'longitude');
  				var latitude = getXPathValueFromDOM(xmlDOM,'latitude');

  				if (houseName != null)
  					document.getElementById('citytbl'+addrName+'HouseName').innerHTML = houseName;
  				else
  					document.getElementById('citytbl'+addrName+'HouseName').innerHTML = "-";
  				
  				if (socBuildName != null)
  					document.getElementById('citytbl'+addrName+'SocietyName').innerHTML = socBuildName;
  				else
  					document.getElementById('citytbl'+addrName+'SocietyName').innerHTML = "-";

  				if (street != null)
  					document.getElementById('citytbl'+addrName+'Street').innerHTML = street;
  				else
  					document.getElementById('citytbl'+addrName+'Street').innerHTML = "-";

  				if (area != null)
  					document.getElementById('citytbl'+addrName+'Area').innerHTML = area;

  				if (importantLandmark != null)
  					document.getElementById('citytbl'+addrName+'ImportantLandmark').innerHTML = importantLandmark;
  				else
  					document.getElementById('citytbl'+addrName+'ImportantLandmark').innerHTML = "-";
  				
  				if (otherDetails != null)
  					document.getElementById('citytbl'+addrName+'OtherDtls').innerHTML = otherDetails;
  				else
  					document.getElementById('citytbl'+addrName+'OtherDtls').innerHTML = "-";

  				if (cityName != null)	
  					document.getElementById('citytbl'+addrName+'City').innerHTML = cityName;

  				if (pincode != null)	
  					document.getElementById('citytbl'+addrName+'PinCode').innerHTML = pincode;
  				else
  					document.getElementById('citytbl'+addrName+'PinCode').innerHTML = "-";

  				if (longitude != null)
  					document.getElementById('langlat'+addrName+'Longitude').innerHTML = longitude;
  				
  				if (latitude != null)
  					document.getElementById('langlat'+addrName+'Latitude').innerHTML = latitude;
  				
  			}
  			else if(addressType == 'Village')
  			{

  				var faliyuName  = getXPathValueFromDOM(xmlDOM,'faliyu');
  				var importantLandmark  = getXPathValueFromDOM(xmlDOM,'impLandmark/landmarkName');

  				var otherDetails  = getXPathValueFromDOM(xmlDOM,'otherDetails');
  				var villageName  = getXPathValueFromDOM(xmlDOM,'cmnVillageMst/villageName');
  				var pincode  = getXPathValueFromDOM(xmlDOM,'pincode');
  				var talukaName  = getXPathValueFromDOM(xmlDOM,'cmnTalukaMst/talukaName');
  				var districtName  = getXPathValueFromDOM(xmlDOM,'cmnDistrictMst/districtName');
  				var stateName  = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateName');
  				var longitude = getXPathValueFromDOM(xmlDOM,'longitude');
  				var latitude = getXPathValueFromDOM(xmlDOM,'latitude');

  				if (houseName != null)
  					document.getElementById('villagetbl'+addrName+'HouseName').innerHTML = houseName;
  				else
  					document.getElementById('villagetbl'+addrName+'HouseName').innerHTML = "-";

  				if (faliyuName != null)
  					document.getElementById('villagetbl'+addrName+'Faliyu').innerHTML = faliyuName;
  				else
  					document.getElementById('villagetbl'+addrName+'Faliyu').innerHTML = "-";

  				if (importantLandmark != null)
  					document.getElementById('villagetbl'+addrName+'ImportantLandmark').innerHTML = importantLandmark;
  				else
  					document.getElementById('villagetbl'+addrName+'ImportantLandmark').innerHTML = "-";

  				if (otherDetails != null)
  					document.getElementById('villagetbl'+addrName+'OtherDtls').innerHTML = otherDetails;
  				else
  					document.getElementById('villagetbl'+addrName+'OtherDtls').innerHTML = "-";

  				if (villageName != null)
  					document.getElementById('villagetbl'+addrName+'Village').innerHTML = villageName;

  				if (pincode != null)
  					document.getElementById('villagetbl'+addrName+'PinCode').innerHTML = pincode;
  				else
  					document.getElementById('villagetbl'+addrName+'PinCode').innerHTML = "-";
  				
  				if (talukaName != null)
  					document.getElementById('villagetbl'+addrName+'Taluka').innerHTML = talukaName;

  				if (districtName != null)
  					document.getElementById('villagetbl'+addrName+'District').innerHTML = districtName;

  				if (longitude != null)
  					document.getElementById('langlat'+addrName+'Longitude').innerHTML = longitude;
  				
  				if (latitude != null)
  					document.getElementById('langlat'+addrName+'Latitude').innerHTML = latitude;
  			}

  			if (stateName != null)
  				document.getElementById('tdCmbState'+addrName).innerHTML = stateName;

  		}
  		else if(addressType != 'EmployeeAddress')
  		{
  			var socBuildName  = getXPathValueFromDOM(xmlDOM,'socBuildName');
  			var area  = getXPathValueFromDOM(xmlDOM,'area');
  			var cityVilllageName  = getXPathValueFromDOM(xmlDOM,'cityVilllageName');
  			var pincode  = getXPathValueFromDOM(xmlDOM,'pincode');
  			var talukaName  = getXPathValueFromDOM(xmlDOM,'talukaName');
  			var longitude = getXPathValueFromDOM(xmlDOM,'longitude');
  			var latitude = getXPathValueFromDOM(xmlDOM,'latitude');

  			if (houseName != null)
  				document.getElementById('othertbl'+addrName+'HouseName').innerHTML = houseName;
  			else
  				document.getElementById('othertbl'+addrName+'HouseName').innerHTML = "-";

  			if (socBuildName != null)
  				document.getElementById('othertbl'+addrName+'SocietyName').innerHTML = socBuildName;
  			else
  				document.getElementById('othertbl'+addrName+'SocietyName').innerHTML = "-";

  			if (area != null)
  				document.getElementById('othertbl'+addrName+'Area').innerHTML = area;
  			else
  				document.getElementById('othertbl'+addrName+'Area').innerHTML = "-";

  			if (cityVilllageName != null)
  				document.getElementById('othertbl'+addrName+'CityVillege').innerHTML = cityVilllageName;
  			else
  				document.getElementById('othertbl'+addrName+'CityVillege').innerHTML = "-";

  			if (pincode != null)
  				document.getElementById('othertbl'+addrName+'PinCode').innerHTML = pincode;
  			else
  				document.getElementById('othertbl'+addrName+'PinCode').innerHTML = "-";

  			if (talukaName != null)
  				document.getElementById('othertbl'+addrName+'Taluka').innerHTML = talukaName;
  			else
  				document.getElementById('othertbl'+addrName+'Taluka').innerHTML = "-";

  			if (longitude != null)
  				document.getElementById('langlat'+addrName+'Longitude').innerHTML = longitude;
  			
  			if (latitude != null)
  				document.getElementById('langlat'+addrName+'Latitude').innerHTML = latitude;
  			
  			if(countryCode==1 && stateCode!=1)
  			{
  				document.getElementById('citytbl'+addrName).style.display='none';
  				document.getElementById('villagetbl'+addrName).style.display='none';
  				document.getElementById('tdTxtState'+addrName).style.display='none';
  				document.getElementById('tdCmbState'+addrName).style.display='';
  				document.getElementById('othertbl'+addrName).style.display='';
  				var langlat = "";
  				if(document.getElementById("hiddenlanglat"+addrName) != null)
  					langlat = document.getElementById("hiddenlanglat"+addrName).value;
  				if(langlat == 'Y')
  				{
  					document.getElementById('langlat'+addrName).style.display='';
  				}
  				document.getElementById('tdTxtDistrict'+addrName).style.display='none';
  				var districtName  = getXPathValueFromDOM(xmlDOM,'cmnDistrictMst/districtName');
  				var stateName  = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateName');


  				if (districtName != null)
  					document.getElementById('tdCmbDistrict'+addrName).innerHTML = districtName;
  				else
  					document.getElementById('tdCmbDistrict'+addrName).innerHTML = "-";

  				if (stateName != null)
  					document.getElementById('tdCmbState'+addrName).innerHTML = stateName;
  				else
  					document.getElementById('tdCmbState'+addrName).innerHTML = "-";
  			} 
  			else
  			{
  				document.getElementById('citytbl'+addrName).style.display='none';
  				document.getElementById('villagetbl'+addrName).style.display='none';
  				document.getElementById('othertbl'+addrName).style.display='';
  				var langlat = "";
  				if(document.getElementById("hiddenlanglat"+addrName) != null)
  					langlat = document.getElementById("hiddenlanglat"+addrName).value;

  				if(langlat == 'Y')
  				{
  					document.getElementById('langlat'+addrName).style.display='';
  				}
  				document.getElementById('tdCmbDistrict'+addrName).style.display='none';
  				document.getElementById('tdCmbState'+addrName).style.display='none';
  				document.getElementById('tdTxtState'+addrName).style.display='';
  				document.getElementById('tdTxtDistrict'+addrName).style.display='';

  				var districtName  = getXPathValueFromDOM(xmlDOM,'districtName');
  				var stateName  = getXPathValueFromDOM(xmlDOM,'stateName');


  				if (districtName != null)
  					document.getElementById('tdTxtDistrict'+addrName).innerHTML = districtName;
  				else
  					document.getElementById('tdTxtDistrict'+addrName).innerHTML = "-";

  				if (stateName != null)
  					document.getElementById('tdTxtState'+addrName).innerHTML = stateName;
  				else
  					document.getElementById('tdTxtState'+addrName).innerHTML = "-";
  			}
  		}

  		if(addressType == 'EmployeeAddress')
  		{
  			var socBuildName  = getXPathValueFromDOM(xmlDOM,'socBuildName');
  			var street  = getXPathValueFromDOM(xmlDOM,'street');
  			var area  = getXPathValueFromDOM(xmlDOM,'area');
  			var otherDetails  = getXPathValueFromDOM(xmlDOM,'otherDetails');
  			var pincode  = getXPathValueFromDOM(xmlDOM,'pincode');

  			if (houseName != null)
  				document.getElementById('employeeAddresstbl'+addrName+'HouseName').innerHTML = houseName;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'HouseName').innerHTML = "-";
  			
  			if (socBuildName != null)
  				document.getElementById('employeeAddresstbl'+addrName+'SocietyName').innerHTML = socBuildName;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'SocietyName').innerHTML = "-";
  			
  			if (street != null)
  				document.getElementById('employeeAddresstbl'+addrName+'Street').innerHTML = street;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'Street').innerHTML = "-";
  			
  			if (area != null)
  				document.getElementById('employeeAddresstbl'+addrName+'Area').innerHTML = area;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'Area').innerHTML = "-";
  			
  			if (otherDetails != null)
  				document.getElementById('employeeAddresstbl'+addrName+'OtherDtls').innerHTML = otherDetails;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'OtherDtls').innerHTML = "-";
  			
  			if (pincode != null)	
  				document.getElementById('employeeAddresstbl'+addrName+'PinCode').innerHTML = pincode;
  			else
  				document.getElementById('employeeAddresstbl'+addrName+'PinCode').innerHTML = "-";
  			
  			if(countryCode!=1)
  			{
  				document.getElementById('tdTxtState'+addrName).style.display='block';
  				document.getElementById('tdCmbState'+addrName).style.display='none';

  				var stateName  = getXPathValueFromDOM(xmlDOM,'stateName');

  				if (stateName != null)
  					document.getElementById('tdTxtState'+addrName).innerHTML = stateName;

  				var districtName  = getXPathValueFromDOM(xmlDOM,'districtName');
  				if (districtName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = districtName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = "-";
  				
  				var talukaName  = getXPathValueFromDOM(xmlDOM,'talukaName');
  				if (talukaName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = talukaName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = "-";
  			}
  			else if(stateCode!=1)
  			{
  				document.getElementById('tdTxtState'+addrName).style.display='none';
  				document.getElementById('tdCmbState'+addrName).style.display='';

  				var stateName  = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateName');
  				if (stateName != null)
  					document.getElementById('tdCmbState'+addrName).innerHTML = stateName;

  				var districtName  = getXPathValueFromDOM(xmlDOM,'cmnDistrictMst/districtName');
  				if (districtName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = districtName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = "-";

  				var talukaName  = getXPathValueFromDOM(xmlDOM,'talukaName');
  				if (talukaName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = talukaName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = "-";
  			}
  			else
  			{
  				document.getElementById('tdTxtState'+addrName).style.display='none';
  				document.getElementById('tdCmbState'+addrName).style.display='';

  				var stateName  = getXPathValueFromDOM(xmlDOM,'cmnStateMst/stateName');
  				if (stateName != null)
  					document.getElementById('tdCmbState'+addrName).innerHTML = stateName;

  				var districtName  = getXPathValueFromDOM(xmlDOM,'cmnDistrictMst/districtName');
  				if (districtName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = districtName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'District').innerHTML = "-";

  				var talukaName  = getXPathValueFromDOM(xmlDOM,'cmnTalukaMst/talukaName');
  				if (talukaName != null)
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = talukaName;
  				else
  					document.getElementById('employeeAddresstbl'+addrName+'Taluka').innerHTML = "-";
  			}
  		}


  		if (countryName != null)
  			document.getElementById('rdonlyCountry'+addrName+'country').innerHTML = countryName;

  		document.getElementById('rdonlyCountry'+addrName).style.display='block';
  	}
  	catch(ex)
  	{
  	}
  }  

//longitude and latitude check start
function longitudeCheck(addrName)
{
	var longi="txtlang"+addrName;	
	var longiarray =  document.getElementById(longi);
	if(longiarray != null)
	{
		var longitude = longiarray[0].value;
		if(longitude > 180)
		{
			alert("Please Enter valid value for longitude");
			longiarray[0].value="";
		}
	}
}
function latitudeCheck(addrName)
{
	var lati="txtlat"+addrName;	
	var latiarray =  document.getElementById(lati);
	if(latiarray != null)
	{
		var latitude = latiarray[0].value;
		if(latitude > 90)
		{
			alert("Please Enter valid value for latitude");
			latiarray[0].value="";
		}
	}
}
//longitude and latitude check start

//populate address by loaction code start
function poulateAddress(name)
{
	showProgressbar();
	var addressPopUP = document.getElementById('addressPopUP'+name).value; 
	var ctxPath = document.getElementById('addrContextPath'+name).value; 
	if(addressPopUP == 'Y')
	{
		try
		{   
		   // Firefox, Opera 8.0+, Safari    
			xmlHttpFirstLevel=new XMLHttpRequest();    
		}
		catch(e)
		{    // Internet Explorer    
			try
		     	{
				xmlHttpFirstLevel=new ActiveXObject("Msxml2.XMLHTTP");   
		     	}
		      catch(e)
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
		var actionFlag = "getaddressVO_By_Location";
		var addressName = "addressName";
		var htmFile=document.getElementById('addrHtmName'+name).value;
	    var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+addressName+"="+name;   
		  var myAjax = new Ajax.Request(url,
			{
				method: 'post',
				asynchronous: false,
				onSuccess: populate,
				onFailure: function(){ alert('Something went wrong...') } 
		    } );
	}
}
	 function populate(myAjax)
	{
			var XMLDoc = myAjax.responseXML.documentElement;
			var addressParams = new Array();
			var addrName = null; 
			
			if(XMLDoc != null)
	    	{
				var cmnAddressMsts = XMLDoc.getElementsByTagName('cmnAddressMst');
				for(i=0;i<cmnAddressMsts.length;i++)
				{
					addrName = cmnAddressMsts[i].childNodes[0].firstChild.nodeValue;
					var contextPath = document.getElementById('addrContextPath'+addrName).value; 
					var statecode = cmnAddressMsts[i].childNodes[1].firstChild.nodeValue;
					var countryCode = cmnAddressMsts[i].childNodes[2].firstChild.nodeValue;
					var districtCode = cmnAddressMsts[i].childNodes[3].firstChild.nodeValue;
					var talukaCode = cmnAddressMsts[i].childNodes[4].firstChild.nodeValue;
					var cityCode = cmnAddressMsts[i].childNodes[5].firstChild.nodeValue;
					addressParams[0]="rdoAddress"+addrName;
					var rdoAddressArr = document.getElementsByName(addressParams[0]);
					if(districtCode != "-")
					{
						rdoAddressArr[0].disabled=false;
						rdoAddressArr[1].disabled=false;
						rdoAddressArr[2].disabled=true; 
						rdoAddressArr[1].click();
						addressParams[1]="cmbDistrict"+addrName;
						addressParams[2]="cmbTaluka"+addrName;
						addressParams[3]="cmbVillage"+addrName
						var districtArr = document.getElementsByName(addressParams[1]);
						var talukaArr = document.getElementsByName(addressParams[2]);
						var villageArr = document.getElementsByName(addressParams[3]);
						if(districtArr!= null)
				 		{
							if(districtArr[0]!= null)
						    {
								try
								{
								var options = districtArr[0].options;
								if(options.length == 1)
						        { 
						      		var text;
									var z=document.getElementById(addressParams[1]);
									var entries = XMLDoc.getElementsByTagName('cmnDistrictMst');
					      			for ( var i = 0 ; i < entries.length ; i++ )
				     				{
				     					text=entries[i].childNodes[0].firstChild.nodeValue;   
				     				    value=entries[i].childNodes[1].firstChild.nodeValue;
				     				    if(text != "-" && value != "-")
				     				    {
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
					      			comboSelect(options,districtCode);
						        }
								}
								catch(e)
								{
									alert("  " + e.message);
								}	
						    }
						}
						if(talukaArr!= null)
						{
						    if(talukaArr[0]!= null)
						    {
						    	var options = talukaArr[0].options;
						    	var text;
								var z=document.getElementById(addressParams[2]);
								var entries = XMLDoc.getElementsByTagName('cmnTalukaMst');
				      			for ( var i = 0 ; i < entries.length ; i++ )
			     				{
			     					text=entries[i].childNodes[0].firstChild.nodeValue;   
			     				    value=entries[i].childNodes[1].firstChild.nodeValue;
			     				    if(text != "-" && value != "-")
			     				    {
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
						    populateNextLevel('cmbTaluka'+addrName,'cmbVillage'+addrName,'getAllVillage',contextPath,'talukaCode',addrName);
						    }
						 }
				 	}
					if(cityCode != "-")
					{
						try{
						rdoAddressArr[0].disabled=false;
						rdoAddressArr[1].disabled=false;
						rdoAddressArr[2].disabled=true; 
						rdoAddressArr[0].click();
						addFinalName = addrName;
						addressParams[1]="cmbCity"+addrName;
						addressParams[2]="cmbCityLandmark"+addrName;
						var cityArr = document.getElementsByName(addressParams[1]);
						var cityLandmarkArr = document.getElementsByName(addressParams[2]);
						if(cityArr!= null)
				 		{
							if(cityArr[0]!= null)
						    {
								var options = cityArr[0].options;
								if(options.length == 1)
								{
									var text;
									var z=document.getElementById(addressParams[1]);
									var entries = XMLDoc.getElementsByTagName('CmnCityMst');
									for ( var i = 0 ; i < entries.length ; i++ )
				     				{
				     					text=entries[i].childNodes[0].firstChild.nodeValue;   
				     				    value=entries[i].childNodes[1].firstChild.nodeValue;
				     				    if(text != "-" && value != "-")
				     				    {
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
								
									opt = z;
									setTimeout("comboSelect(opt,'"+cityCode+"')",500);
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
						    		var text;
									var z=document.getElementById(addressParams[2]);
									var entries = XMLDoc.getElementsByTagName('CmnLandmarkMst');
					      			for ( var i = 0 ; i < entries.length ; i++ )
				     				{
				     					text=entries[i].childNodes[0].firstChild.nodeValue;   
				     				    value=entries[i].childNodes[1].firstChild.nodeValue;
				     				    if(text != "-" && value != "-")
				     				    {
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
						    }
			 			}
						}
						catch(e)
						{
							alert(e.message);
						}
					}
					if(cityCode == "-" && districtCode == "-")
					{
						resetAddress();
					}
				}	
	    	}
	}
	function comboSelect(opts,Code)
	{
		opts.value = Code;
	}
//populate address by loaction code end
	
//get country code for passing country value param 
function getCountryCode(name)
{
	showProgressbar();
	var countryName = document.getElementById('countryName'+name).value; 
	var ctxPath = document.getElementById('addrContextPath'+name).value; 
	if(countryName != '' && countryName != null)
	{
		try
		{   
		   // Firefox, Opera 8.0+, Safari    
			xmlHttpFirstLevel=new XMLHttpRequest();    
		}
		catch(e)
		{    // Internet Explorer    
			try
		     	{
				xmlHttpFirstLevel=new ActiveXObject("Msxml2.XMLHTTP");   
		     	}
		      catch(e)
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
		var actionFlag = "getcountryVO_By_Name";
		var addressName = "addressName";
		var htmFile=document.getElementById('addrHtmName'+name).value;
	    var url = ctxPath+htmFile+"actionFlag="+actionFlag+"&"+addressName+"="+name+"&countryName="+countryName;   
	    var myAjax = new Ajax.Request(url,
		{
					method: 'post',
					asynchronous: false,
					onSuccess: returnCountryVO,
					onFailure: function(){ alert('Something went wrong...') } 
		} );
	}
}	 
function returnCountryVO(myAjax)
{
		var XMLDoc = myAjax.responseXML.documentElement;
		var addrName = null;
		if(XMLDoc != null)
    	{
			var cmnaddressName = XMLDoc.getElementsByTagName('addressName');
			for(i=0;i<cmnaddressName.length;i++)
			{
				addrName = cmnaddressName[i].firstChild.nodeValue;
			}
			var cmnCountryMst = XMLDoc.getElementsByTagName('CountryVO');
			for(i=0;i<cmnCountryMst.length;i++)
			{
				var countryCode = cmnCountryMst[i].childNodes[0].firstChild.nodeValue;
				var countryName = cmnCountryMst[i].childNodes[1].firstChild.nodeValue;
				var countryArr = document.getElementsByName('cmbCountry'+addrName);
				if(countryArr!= null)
		 		{
					if(countryArr[0]!= null)
				    {
						var options = countryArr[0].options;
						var z=document.getElementById('cmbCountry'+addrName);
						z.remove(z.selectedIndex);
						var y=document.createElement('option');
						y.text=countryName;
						y.value=countryCode;
						try
						{
							z.add(y,null); 
						}
						catch(ex)
						{
							z.add(y); 
						}
				    }
					for(var t=0;t<options.length;t++)
					{
						if(options[t].value == countryCode)
						{
							options[t].selected="selected";
						}
					}
			   }
		}
    }
}


