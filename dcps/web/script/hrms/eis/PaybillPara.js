function clearMjrHeadCombo()
{
	var v=document.getElementById("cmbMjrHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbMjrHead").options.length -1;
			document.getElementById("cmbMjrHead").options[lgth] = null;
	}		
}
function clearSubMjrHeadCombo()
{
	var v=document.getElementById("cmbSubMjrHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbSubMjrHead").options.length -1;
			document.getElementById("cmbSubMjrHead").options[lgth] = null;
	}		
}

function clearMnrHeadCombo()
{
	var v=document.getElementById("cmbMnrHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbMnrHead").options.length -1;
			document.getElementById("cmbMnrHead").options[lgth] = null;
	}		
}

function clearDemandNo()
{
	var v=document.getElementById("cmbDemand").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbDemand").options.length -1;
			document.getElementById("cmbDemand").options[lgth] = null;
	}		
}

function clearSubHeadCombo()
{
	var v=document.getElementById("cmbSubHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbSubHead").options.length -1;
			document.getElementById("cmbSubHead").options[lgth] = null;
	}		
}

function clearDetailHeadCombo()
{
	var v=document.getElementById("cmbDtlHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbDtlHead").options.length -1;
			document.getElementById("cmbDtlHead").options[lgth] = null;
	}		
}

function clearMonthCombo()
{
	var v=document.getElementById("cmbMonth").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbMonth").options.length -1;
			document.getElementById("cmbMonth").options[lgth] = null;
	}		
}


function GetDemandNo()
{

          //  alert( 'Villages Funct called..');

		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&loc_code='+ document.frmPaybillPara.cmbDept.value;
		  var actionf="getDemandNo";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Dept;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_Dept()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
		    clearDemandNo();
		    clearMjrHeadCombo();		     
  	        clearSubMjrHeadCombo();
  	        clearMnrHeadCombo();
  	        clearSubHeadCombo();
  	        clearDetailHeadCombo();
  	        
			var demandNo = document.getElementById("cmbDemand");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var demandNoEntries = XMLDoc.getElementsByTagName('demand-mapping');
	           			for ( var i = 0 ; i < demandNoEntries.length ; i++ )
	     				{
	     				    val=demandNoEntries[i].childNodes[0].firstChild.nodeValue;  
	     				    text = demandNoEntries[i].childNodes[0].firstChild.nodeValue; 
	//     				    alert('Village val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               demandNo.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    demandNo.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }


function GetMjrHeadNo()
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
		  url= uri+'&demand_no='+ document.frmPaybillPara.cmbDemand.value;
		  var actionf="getMjrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
  //       alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_DemandNo;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_DemandNo()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
 		      clearMjrHeadCombo();
		      clearSubMjrHeadCombo();
		      clearMnrHeadCombo();
  	        clearSubHeadCombo();
  	        clearDetailHeadCombo();
		      
			var mjrHead = document.getElementById("cmbMjrHead");								                    
    //                alert('District size:-->' + distEntries.length);
                    if(xmlHttp.responseXML.documentElement==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var XMLDoc=xmlHttp.responseXML.documentElement;	
                        var mjrHeadEntries = XMLDoc.getElementsByTagName('mjrHead-mapping');
	           			for ( var i = 0 ; i < mjrHeadEntries.length ; i++ )
	     				{
	     				    val=mjrHeadEntries[i].childNodes[0].firstChild.nodeValue;  
	     				    text = mjrHeadEntries[i].childNodes[0].firstChild.nodeValue;
	  //   				    alert('Dist val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               mjrHead.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    mjrHead.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }




function GetSubMjrHeadNo()
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
		  url= uri+'&demand_no='+ document.frmPaybillPara.cmbDemand.value + '&mjrHead_Code='+ document.frmPaybillPara.cmbMjrHead.value;
		  var actionf="getSubMjrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
         //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_MjrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_MjrHead()
	{
	clearSubMjrHeadCombo();
		      clearMnrHeadCombo();
  	        clearSubHeadCombo();
  	        clearDetailHeadCombo();
		if (xmlHttp.readyState==complete_state)
		{ 			
			var subMjrHead = document.getElementById("cmbSubMjrHead");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                       window.status = 'No Records Found.';
                     }
                    else
                    {
           //         alert('Taluka size:-->' + talukaEntries.length);
                      window.status='';
                        var subMjrHeadsEntries = XMLDoc.getElementsByTagName('subMjrHead-mapping');
	           			for ( var i = 0 ; i < subMjrHeadsEntries.length ; i++ )
	     				{
	     				    val=subMjrHeadsEntries[i].childNodes[0].firstChild.nodeValue;   
	     				    text = subMjrHeadsEntries[i].childNodes[0].firstChild.nodeValue;
	     	//			    alert('Dist val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               subMjrHead.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    subMjrHead.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }



function GetMnrHeads()
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
		  url= uri+'&demand_no='+ document.frmPaybillPara.cmbDemand.value + '&mjrHead_Code='+ document.frmPaybillPara.cmbMjrHead.value + '&subMjrHead_Code=' + document.frmPaybillPara.cmbSubMjrHead.value;
		  var actionf="getMnrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_subMjrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_subMjrHead()
	{	
	clearMnrHeadCombo();
  	        clearSubHeadCombo();
  	        clearDetailHeadCombo();
		if (xmlHttp.readyState==complete_state)
		{ 		    
			var mnrHead = document.getElementById("cmbMnrHead");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc == null)
					{
					 window.status = 'No Records Found.';
					 }
					else
					{
                        window.status='';
				       var entries = XMLDoc.getElementsByTagName('mnrHead-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].firstChild.nodeValue;
	     				    text = entries[i].childNodes[0].firstChild.nodeValue;
	 //    				    alert('val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;	
	     			        try
	   				     {      				    					
                            mnrHead.add(y,null);
	           			}
	 				     catch(ex)
	   				    {
	   			 		    mnrHead.add(y); 
	   			   	   }
	   			   	 }
	   			   	 }	
	}
  }


function GetSubHeads()
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
		  url= uri+'&demand_no='+ document.frmPaybillPara.cmbDemand.value + '&mjrHead_Code='+ document.frmPaybillPara.cmbMjrHead.value + '&subMjrHead_Code=' + document.frmPaybillPara.cmbSubMjrHead.value + '&mnrHead_code=' +  document.frmPaybillPara.cmbMnrHead.value;
		  var actionf="getSubHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_mnrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_mnrHead()
	{	
	clearSubHeadCombo();
  	        clearDetailHeadCombo();
		if (xmlHttp.readyState==complete_state)
		{ 		    
			var subHead = document.getElementById("cmbSubHead");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc == null)
					{
					 window.status = 'No Records Found.';
					 }
					else
					{
                        window.status='';
				       var entries = XMLDoc.getElementsByTagName('subHead-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].firstChild.nodeValue;
	     				    text = entries[i].childNodes[0].firstChild.nodeValue;
	 //    				    alert('val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;	
	     			        try
	   				     {      				    					
                            subHead.add(y,null);
	           			}
	 				     catch(ex)
	   				    {
	   			 		    subHead.add(y); 
	   			   	   }
	   			   	 }
	   			   	 }	
	}
  }

function GetDtlHeads()
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
		  url= uri+'&mjrHead_Code='+ document.frmPaybillPara.cmbMjrHead.value + '&subMjrHead_Code=' + document.frmPaybillPara.cmbSubMjrHead.value + '&mnrHead_code=' +  document.frmPaybillPara.cmbMnrHead.value + '&subHead_code='+ document.frmPaybillPara.cmbSubHead.value;
		  var actionf="getDtlHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_subHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_subHead()
	{	
	clearDetailHeadCombo();
		if (xmlHttp.readyState==complete_state)
		{ 		    
			var dtlHead = document.getElementById("cmbDtlHead");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc == null)
					{
					 window.status = 'No Records Found.';
					 }
					else
					{
                        window.status='';
				       var entries = XMLDoc.getElementsByTagName('DtlHead-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].firstChild.nodeValue;
	     				    text = entries[i].childNodes[0].firstChild.nodeValue;
	 //    				    alert('val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;	
	     			        try
	   				     {      				    					
                            dtlHead.add(y,null);
	           			}
	 				     catch(ex)
	   				    {
	   			 		    dtlHead.add(y); 
	   			   	   }
	   			   	 }
	   			   	 }	
	}
  }

