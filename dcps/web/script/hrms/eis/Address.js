function clearBankCombo()
{
	var v=document.getElementById("bankId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("bankId").options.length -1;
			document.getElementById("bankId").options[lgth] = null;
	}		
}
function clearTalukaCombo()
{
	var v=document.getElementById("branchTaluka").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchTaluka").options.length -1;
			document.getElementById("branchTaluka").options[lgth] = null;
	}		
}

function clearVillageCombo()
{
	var v=document.getElementById("branchVillage").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchVillage").options.length -1;
			document.getElementById("branchVillage").options[lgth] = null;
	}		
}

function clearStateCombo()
{
	var v=document.getElementById("branchState").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchState").options.length -1;
			document.getElementById("branchState").options[lgth] = null;
	}		
}

function clearDistCombo()
{
	var v=document.getElementById("branchDistrict").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("branchDistrict").options.length -1;
			document.getElementById("branchDistrict").options[lgth] = null;
	}		
}


function GetVillages()
{

            /*alert( 'Villages Funct called..' + document.frmBF.branchTaluka.value);*/

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&Taluka_ID='+ document.frmBF.branchTaluka.value;
		  var actionf="getVillage";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
//         alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Taluka;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_Taluka()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
		    clearVillageCombo();	
			var village = document.getElementById("branchVillage");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var villageEntries = XMLDoc.getElementsByTagName('village-mapping');
	           			for ( var i = 0 ; i < villageEntries.length ; i++ )
	     				{
	     				    val=villageEntries[i].childNodes[0].text;    
	     				    text = villageEntries[i].childNodes[1].text; 
	//     				    alert('Village val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               village.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    village.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }

function getBankName() {

	 xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  }  
		  var url; 
		  var uri='';
		  url= uri+'&bankId='+ document.frmBF.cmbBankName.value;
		  var actionf="getBankName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
      //   alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChangedBank;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
}

function stateChangedBank()
	{
	
		if (xmlHttp.readyState==complete_state)	{ 
		//alert("State changed");				
			var bank = document.getElementById("bankName");
			var XMLDoc=xmlHttp.responseXML.documentElement;			
            var bankEntries = XMLDoc.getElementsByTagName('BankName-mapping');
          //  alert('Bank name size:-->' + bankEntries.length);
            var txtBankID=document.getElementById("bankName");
            val=bankEntries[0].childNodes[0].text;  
            txtBankID.value=val;
            //alert("The Value is:-"+val);      		 	                        
 	   }
 }

function GetDistricts()
{

//            alert( 'Districts Funct called..' + document.frmBF.branchCountry.value);

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&State_ID='+ document.frmBF.branchState.value;
		  var actionf="getDistrict";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  //       alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_state;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_state()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
		clearDistCombo();
		clearTalukaCombo();
		clearVillageCombo();	
			var dist = document.getElementById("branchDistrict");
			
					
                    
    //                alert('District size:-->' + distEntries.length);
                    if(xmlHttp.responseXML.documentElement==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var XMLDoc=xmlHttp.responseXML.documentElement;	
                        var distEntries = XMLDoc.getElementsByTagName('dist-mapping');
	           			for ( var i = 0 ; i < distEntries.length ; i++ )
	     				{
	     				    val=distEntries[i].childNodes[0].text;    
	     				    text = distEntries[i].childNodes[1].text; 
	  //   				    alert('Dist val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               dist.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    dist.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }




function GetTalukas()
{

        //    alert( 'Talukas Funct called..' + document.frmBF.branchTaluka.value);

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&District_ID='+ document.frmBF.branchDistrict.value;
		  var actionf="getTaluka";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
         //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Dist;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_Dist()
	{
		if (xmlHttp.readyState==complete_state)
		{ 	
		    clearTalukaCombo();
		    clearVillageCombo();		
			var taluka = document.getElementById("branchTaluka");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                       window.status = 'No Records Found.';
                     }
                    else
                    {
           //         alert('Taluka size:-->' + talukaEntries.length);
                      window.status='';
                        var talukaEntries = XMLDoc.getElementsByTagName('taluka-mapping');
	           			for ( var i = 0 ; i < talukaEntries.length ; i++ )
	     				{
	     				    val=talukaEntries[i].childNodes[0].text;    
	     				    text = talukaEntries[i].childNodes[1].text; 
	     	//			    alert('Dist val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               taluka.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    taluka.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }



function GetStates()
{

 //           alert( ' Funct called..' + document.frmBF.branchCountry.value);

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&Country_ID='+ document.frmBF.branchCountry.value;
		  var actionf="getStates";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_country;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_country()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
		    clearStateCombo();
		    clearDistCombo();
		    clearTalukaCombo();
		    clearVillageCombo();
			var state = document.getElementById("branchState");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc == null)
					{
					 window.status = 'No Records Found.';
					 }
					else
					{
                        window.status='';
				       var entries = XMLDoc.getElementsByTagName('state-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[1].text; 
	 //    				    alert('val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;	
	     			        try
	   				     {      				    					
                            state.add(y,null);
	           			}
	 				     catch(ex)
	   				    {
	   			 		    state.add(y); 
	   			   	   }
	   			   	 }
	   			   	 }	
	}
  }

