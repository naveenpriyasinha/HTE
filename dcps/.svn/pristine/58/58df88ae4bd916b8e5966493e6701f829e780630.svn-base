

function storePreferences(urlStr)
{	
	showProgressbar();
	var hiddenDivCntObj = document.getElementById('divCount');
	var paramStr = "";
	if(hiddenDivCntObj != null && hiddenDivCntObj != 'undefined')
	{	
		var divCount = hiddenDivCntObj.value;	
		var divName;
		var divHiddenParaName;	
		
		paramStr = "&divCount="+divCount;		
		for(var i = 0;i<divCount;i++)
		{
			divName = "homePageDiv_"+i;
			divStatusParaName = "hpDiv_"+i;
			var divObj = document.getElementById(divName);		
			paramStr = paramStr+"&"+divStatusParaName+"="+divObj.style.display;
		}
		//alert("paramStr ---> "+paramStr);
		storeMenuPreferences(paramStr);			
	}
	//document.forms[0].action = urlStr;
	//document.forms[0].submit();
	window.location.href=urlStr; 
}


	function storeMenuPreferences(paramStr)
	{			
		
		try
    	{   
    	// Firefox, Opera 8.0+, Safari    
    	
    	xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
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
    	
    	var contextPath = document.getElementById('projectContextPath').value;		  
        var url = contextPath+"?actionFlag=manageUserPreferences&functionality=storeUserMenuPref"+paramStr;       
        //alert(url);
        xmlHttp.open("POST", encodeURI(url) , false);         
        xmlHttp.onreadystatechange = function()
        {        	
        	if (xmlHttp.readyState == 4) 
        	{  			  	
				if (xmlHttp.status == 200) 
				{
					
				}//end of if
				
			}
        }
        xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	     
	}