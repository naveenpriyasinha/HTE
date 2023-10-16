	function checkAttDate(attDate, trngCode, schId, ctxPath)
	{
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
	        
	    var url = ctxPath+"/hdiits.htm?actionFlag=checkDupAttendance&attDate=" +attDate + "&trngCode=" + trngCode + "&schId=" + schId; 
	    xmlHttp.open("POST", encodeURI(url) , false);
			
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.onreadystatechange = function(){
			if (xmlHttp.readyState == 4){
				if (xmlHttp.status == 200) 
				{ 
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc!= null)
			    	{
				    	var entriesValue = XMLDoc.getElementsByTagName('value');	
				    	var text=entriesValue[0].childNodes[0].text;
				    	var dateEntered=document.getElementById('attndDate').value;
						if(text=="true"){
							alert("Attendance for the date " + dateEntered +" already entered.");
							document.getElementById('attndDate').value="";	
						}
				    }
				}
				else 
				{  
					alert("ERROR");
				}
			}
		}
		
		xmlHttp.send(encodeURIComponent(null));
	}


	
	
     