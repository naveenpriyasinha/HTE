

function getPoliceLine(q,n)
{	var pID;
	var N=1;
	var newEntry=N;
	pID=document.getElementById('Policestation');
	if(pID.value!=0){
	try{   
		xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{    // Internet Explorer    
		try{
    		xmlHttp=new 
            ActiveXObject("Msxml2.XMLHTTP");      
    	}
		catch (e){
			try{
            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
   			}
	   		catch (e){
	  	   		alert("Your browser does not support AJAX!");        
	        	return false;        
			}
		}			 
   }
   	var url = "hrms.htm?actionFlag=getPoliceLineList&PoliceSTId="+pID.value+"&NewEntry="+newEntry;     
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponse3;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
   }
   document.getElementById('QtrList').style.display='none';
   document.getElementById('EditAllocation').style.display='none';	

}
function processResponse3()
{
	if (xmlHttp.readyState == 4) {
	    if (xmlHttp.status == 200) {
			var PoliceLine;
				PoliceLine=document.getElementById('PoliceLine');
			for (var i=PoliceLine.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					PoliceLine.remove(PoliceLine.value);	     				
					PoliceLine.remove(PoliceLine.text);
		  	}
		  	
			var xmlStr = xmlHttp.responseText; //alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr); //alert(XMLDoc);  // xmlHttp.responseXML.documentElement;						    			    	
			var Police = XMLDoc.getElementsByTagName('PoliceLineName');
			if(Police.length>0){
			for ( var i = 0 ; i < Police.length ; i++ ){
				value=Police[i].childNodes[0].text;	
		 		var y=document.createElement('option');
		 		y.text=value;
				y.value=value;
				try
				{
					PoliceLine.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				PoliceLine.add(y);	   			 				 
				}     				
   			}//end of for loop 
   			}// end of if loop
		}
	}
}
function getPoliceST(q,n){
	var oID;
	oID=document.getElementById('Jurisdiction');
	//alert(oID);
	if(oID.value!=0){
	try{   
		xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{    // Internet Explorer    
		try{
    		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
    	}
		catch (e){
			try{
            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
   			}
	   		catch (e){
	  	   		alert("Your browser does not support AJAX!");        
	        	return false;        
			}
		}			 
   }
   	var url = "hrms.htm?actionFlag=getPoliceStation&OfficeId="+q.value;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponse2;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	}
	document.getElementById('QtrList').style.display='none';
	document.getElementById('EditAllocation').style.display='none';	
}
function processResponse2(){

	if (xmlHttp.readyState == 4) {
	    if (xmlHttp.status == 200) {
	   
			var PoliceST;
			var PoliceLine;
				PoliceST=document.getElementById('Policestation');
				PoliceLine=document.getElementById('PoliceLine');
			for (var i=PoliceST.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					PoliceST.remove(PoliceST.value);	     				
					PoliceST.remove(PoliceST.text);
		  	}
		  	for (var i=PoliceLine.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					PoliceLine.remove(PoliceLine.value);	     				
					PoliceLine.remove(PoliceLine.text);
		  	}
			
			var xmlStr = xmlHttp.responseText; //alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr); //alert(XMLDoc);  // xmlHttp.responseXML.documentElement;						    			    	
			var locId = XMLDoc.getElementsByTagName('LocationId');
			var locName = XMLDoc.getElementsByTagName('LocationName');
			for ( var i = 0 ; i < locId.length ; i++ ){
				value=locName[i].childNodes[0].text;	
				Id=locId[i].childNodes[0].text;    
	 			var y=document.createElement('option');
		 		y.text=value;
				y.value=Id;
				try
				{
					PoliceST.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				PoliceST.add(y);	   			 				 
				}     				
   			}
		}
	}
}
function getJurisdiction(q,n)
{
			var dID;
			var jurisdiction;
			var PoliceST;
			var PoliceLine;
				dID=document.getElementById('TypeOfOff');
				jurisdiction=document.getElementById('Jurisdiction');
				//alert(jurisdiction);
				PoliceST=document.getElementById('Policestation');
				//alert(PoliceST);
				PoliceLine=document.getElementById('PoliceLine');
				//alert(PoliceLine);
		
			try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e){
			           	   	  alert("Your browser does not support AJAX!");        
			            	  return false;        
			      		}
			 		}			 
        	}	
			var url = "hrms.htm?actionFlag=getJurisdiction&deptId="+dID.value;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
			document.getElementById('QtrList').style.display='none';
			document.getElementById('EditAllocation').style.display='none';	
}
function processResponse1()
{
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var jurisdiction;
			var PoliceST;
			var PoliceLine;
				jurisdiction=document.getElementById('Jurisdiction');
				PoliceST=document.getElementById('Policestation');
				PoliceLine=document.getElementById('PoliceLine');
			
			for (var i=jurisdiction.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					jurisdiction.remove(jurisdiction.value);	     				
					jurisdiction.remove(jurisdiction.text);
		  	}
		  	for (var i=PoliceST.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					PoliceST.remove(PoliceST.value);	     				
					PoliceST.remove(PoliceST.text);
		  	}
		  	for (var i=PoliceLine.length;i>0;i--)  // removing a previous value of combo
		   	{	     				     					
					PoliceLine.remove(PoliceLine.value);	     				
					PoliceLine.remove(PoliceLine.text);
		  	}
			var xmlStr = xmlHttp.responseText;
			//alert(xmlStr);
			var XMLDoc=getDOMFromXML(xmlStr);  // xmlHttp.responseXML.documentElement;						    			    	
			//alert(XMLDoc);
			var locId = XMLDoc.getElementsByTagName('LocationId');
			var locName = XMLDoc.getElementsByTagName('LocationName');
			
			for ( var i = 0 ; i < locId.length ; i++ ){
				value=locName[i].childNodes[0].text;	
				Id=locId[i].childNodes[0].text;    
	     		var y=document.createElement('option');
		 		y.text=value;
				y.value=Id;
				try
				{
					jurisdiction.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				jurisdiction.add(y);	   			 				 
				}     				
   			}
		}
	}
			          				
}





function addDataInTable1(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName,methodName) 
	{
//		alert ("addDataInTable called...");
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		if(methodName == undefined) 
		{
			methodName = '';
		}
		//alert(methodName);
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
					
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	
			}
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
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
		counter++;	
		
		return trow.id;
	}
	
	function updateDataInTable1(hiddenField, displayFieldArray,updateRow1) 
	{
			//alert ("updateDataInTable called...");
		var trow = document.getElementById(updateRow1);
		//alert('trow='+trow);
		//alert('update.row='+updateRow1);
			//var hFieldId = updateRow1.substring(updateRow.length-1, updateRow.length);
		var hFieldId = updateRow1.substring(3, updateRow1.length);
		//alert('hFieldId='+hFieldId);
		//	MAKING IT NULL BECAUSE IT WILL USED TO IDENTIFY IF
		//	ANY OTHER MULTIPLE EDIT IS OPEN IN EDIT MODE.
		//  CHANGE BY 202414
		updateRow1 = null;
		//alert(document.getElementById(hFieldId).value);
		
		//	alert ("xmlHttp.responseText "+xmlHttp.responseText);
		if(flagForUpdatedVO)
		{
			//	alert ("_U append to XML response..");
			//	HERE ADDED "_U" TO INDICATE THIS RECORD IS UPDATED BY USER. 
			//document.getElementById(hFieldId).value = decXML +"_U";
			//alert(document.getElementById(hFieldId).value);
			flagForUpdatedVO = false;
		}
		else
		{
			//document.getElementById(hFieldId).value = decXML;
			//alert(document.getElementById(hFieldId).value);
		}
		
		//trow.cells[0].innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hFieldId + "' value='" + xmlHttp.responseText + "'/>";					
		//trow.cells[0].style.display = 'none';
		trow.cells(0).innerHTML ="<INPUT type='radio' name='FirstCheck' id='FirstCheck' value='YesChecked"+counter+"')>";
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//	alert(field.type);
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.cells[i+1].innerHTML = "";
				}
				else
				{
					trow.cells[i+1].innerHTML = field.options[field.selectedIndex].text;
				}
				
			}
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.cells[i+1].innerHTML = radio[j].value;
					}
				}
			}				
			else
			{
				trow.cells[i+2].innerHTML = field.value;	
			}
		}		
	}