function gotoHomePage(frmName)
{
	if(confirm('Do you want to Close This Application?')==true)
	{
		document.forms[frmName].action = "hrms.htm?actionFlag=getHomePage";
	   	document.forms[frmName].submit();
	}
}
// For Combo Change Event 
function com(cmb)
{							
	var id=cmb.options[cmb.selectedIndex].text;				
	var id=cmb.value;	
	var comboSubType=document.getElementById('location').length;	
	for(i=1;i<comboSubType;i++)
	{
			lgth = document.getElementById("location").options.length -1;
			document.getElementById("location").options[lgth] = null;
	}	
	if(id=='Select'){return;}
  /* removing a previous value of combo */			
   	if(id=='') {return;}						
	try
	{   
  			 xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
			try
			{
    					xmlHttp=new 
                      ActiveXObject("Msxml2.XMLHTTP");      
    				}
    		catch (e)
    		{
          		try
      		  		{
              	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
      		  		}
		      	catch (e)
		      	{
	           	   	  alert("Your browser does not support ajax");        
	            	  return false;        
	      		}
	 		}			 
    }        	
	var url = "hrms.htm?actionFlag=promotionAjaxDtls&flag=combodtls&cmbid="+id;
	xmlHttp.open("POST", encodeURI(url) , true);
	
	xmlHttp.onreadystatechange = processResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
			
													
			
			
// On Click Of search 			
function search()
{			

	var deptId=document.getElementById("dept").value;	
	var desigId=document.getElementById("designation").value;
	var locId=document.getElementById("location").value;
	if(deptId=='Select' || deptId=='' || deptId=='null' || deptId==null ){return;}
	if(desigId=='Select' || desigId=='' || desigId=='null' || desigId==null ){return;}
	if(locId=='Select' || locId=='' || locId=='null' || locId==null ){return;}		
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
           	   	  alert("Your browser does not support ajax");        
            	  return false;        
      		}
 		}			 
    }        	
	var url = "hrms.htm?actionFlag=promotionAjaxDtls&flag=search&deptId="+deptId+"&locId="+locId+"&desigId="+desigId;
	xmlHttp.open("POST", encodeURI(url) , true);
	
	xmlHttp.onreadystatechange = addVacancyDetails;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}				
function addVacancyDetails()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{          		        				
				var textStr;						
		    	var xmlStr = xmlHttp.responseText;  				
		    	var XMLDoc=getDOMFromXML(xmlStr);
		    	
		    	var OffNameStr = XMLDoc.getElementsByTagName('OfficeName');
		    	var SanctionStr = XMLDoc.getElementsByTagName('Sanction');
		    	var PresentStr = XMLDoc.getElementsByTagName('Present');
		    	var VacancyStr = XMLDoc.getElementsByTagName('Vacancy');
		    	var InOffStr = XMLDoc.getElementsByTagName('InOffice');
		    	var OtherStr = XMLDoc.getElementsByTagName('OtherOffice');
		    			    			    			    			    			    	
		    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('SubTypeOfCoCurricularID'); 
		    	var num = document.getElementById('vacancyTable').rows.length;
				for ( var i = 0 ; i < OffNameStr.length ; i++ ) // adding a new vlaue in combo
   				{	     		     								
   				    var offname=OffNameStr[i].childNodes[0].text;	     
   				    var sanction =SanctionStr[i].childNodes[0].text;	     
   				    var present =PresentStr[i].childNodes[0].text;	     
   				    var vacancy =VacancyStr[i].childNodes[0].text;	     
   				    var inoff =InOffStr[i].childNodes[0].text;	     
   				    var otheroff =OtherStr[i].childNodes[0].text;	     
   				    document.getElementById('present_toatal').value=parseInt(document.getElementById('present_toatal').value)+parseInt(present); 				    
   				    document.getElementById('vacancy_toatal').value=parseInt(document.getElementById('vacancy_toatal').value)+parseInt(vacancy); 
   				    document.getElementById('inOffice_toatal').value=parseInt(document.getElementById('inOffice_toatal').value)+parseInt(inoff); 
   				    document.getElementById('otherOffice_toatal').value=parseInt(document.getElementById('otherOffice_toatal').value)+parseInt(otheroff); 
   				    document.getElementById('sanction_toatal').value=parseInt(document.getElementById('sanction_toatal').value)+parseInt(sanction); 
   				    var dispFieldA = new Array(num-3,offname,sanction,present,vacancy,inoff,otheroff);   				    
   				    document.getElementById('vacancyTable').style.display='';		
		
					var trow=document.getElementById('vacancyTable').insertRow(document.getElementById('vacancyTable').rows.length-1);
					trow.id = 'row' +  + num;				
					trow.insertCell(0).innerHTML = "<INPUT type='hidden' />";
					trow.cells[0].style.display = 'none';
					var len = dispFieldA.length;
					for(var j = 0; j < len; j++) 
					{
						trow.insertCell(j+1).innerHTML = dispFieldA[j];	
					}
   				    num++;
   				}
    	}    			
    }	
}