
var counter = 1;

function addDBDataInTable1(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,personId)
	{
		//	alert ('addDBDataInTable called....');
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		if(personId 	== undefined) 
		{
			personId 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = dispFieldA[i];	
		}
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		var ShowAddre="Show Address";	
		trow.insertCell(len + 1).innerHTML ="<a href=javascript:void('personId')  onclick=javascript:getAddress('"+personId +"')>"+ShowAddre+"</a>";
			
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
		trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 2).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
		counter++;
		
		return trow.id;
	}
function getAddress(personId)
{
	if(personId!=0)
		{
		try
		{   
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
   		}
   		var url = "hrms.htm?actionFlag=GetAddresForPersonDtls&PersonId="+personId;
		xmlHttp.open("POST", encodeURI(url) , true);
		xmlHttp.onreadystatechange = processResponse7;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
}
function processResponse7()
		{	//alert("inside second")
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr); 			    
			    	
			    	xmlFileName1=XMLDoc.getElementsByTagName('Address1');
			    	xmlFileName=xmlFileName1[0].childNodes[0].text;		
			    	    						
					try{   
		    			xmlHttp=new XMLHttpRequest();	    	    
			    	}
					catch (e)
					{    // Internet Explorer    
							try{
		      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		      				}
				    		catch (e){
				          		try
		        		  		{
		                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
		        		  		}
						      	catch (e)
						      	{
		       	   	  	           	 
					            	  return false;        
					      		}
					 		}			 
		        	}	
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;   
					xmlHttp.open("POST", encodeURI(url) , true);			
					xmlHttp.onreadystatechange = showAddressDt;
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));
					
					//document.getElementById('deletebtn').style.display="";
				}
			}
		}
		
		
			
function showAddressDt()			    				    	
		{//alert("inside third")
			if (xmlHttp.readyState == 4) 
			{     		
				if (xmlHttp.status == 200) 
				{						
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    			    	
			    	var addrXPath = 'cmnAddressMst';
					editAddress('Address1',XMLDoc,addrXPath);
					makeEnableDisable("Address1",0);
				
						    
			//document.getElementById('updatesave').style.display="";	
								    	
				}							
			}
		}	
	