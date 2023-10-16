//added by manish
function GetFromMonths()
{
	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  var yearValue=document.getElementById("Year").value;
		  //alert(''+yearValue);
		  url= uri+'&year_id='+yearValue;
		  var actionf="getMonths";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_FromYear;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_FromYear()
	{
		
		if (xmlHttp.readyState==complete_state)
		{ 		
		
		    clearFromMonthCombo();
  	        
			var month = document.getElementById("Month");
			
			var yearVal =document.getElementById("Year").value;
		
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     
                     }
                    else
                    {
                        window.status='';
                        var monthEntries = XMLDoc.getElementsByTagName('month-mapping');
                       // alert('month entry size is ' + monthEntries.length);
	           			for ( var i = 0 ; i < monthEntries.length ; i++ )
	     				{
	     				    val=monthEntries[i].childNodes[0].text;    
	     				    text = monthEntries[i].childNodes[1].text; 
     				   // alert('Month val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        if(yearVal!=-1)
		     			        y.selected=true;	
 	                        try
 	   				        {	      				    					
 	                        	month.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	 							month.add(y); 
	   						 }
	   		          }
	   		         }
	   		       
 	   }
	
 }
function clearFromMonthCombo()
{
	var v=document.getElementById("Month").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("Month").options.length -1;
			document.getElementById("Month").options[lgth] = null;
	}		
}
function beforeSubmit()
{
	//alert('in before submit');
	var cnt=1;
	if(document.getElementById("Year").value=="-1")
	{
		alert("Please Select The Year .");
		document.getElementById("Year").focus();
		cnt+=1;
		return false;
	}
	if(document.getElementById("Month").value=="-1")
	{
		alert("Please Select The month .");
		document.getElementById("Month").focus();
		cnt+=1;
		return false;
	}
	if(document.getElementById("BillNo").value=="-1")
	{
		alert("Please Select The Bill Number .");
		document.getElementById("BillNo").focus();
		cnt+=1;
		return false;
	}
	if(cnt>1)
	{
		return false;
	}
	else{
		chkValue();
	}
	return true;
}
//ended by manish
