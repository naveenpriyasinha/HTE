function clearGradeCombo()
{
   document.getElementById("txtGradeId").value="";
	var v=document.getElementById("cmbGrade").length;
	var t = document.getElementById("cmbSelGrade").length;
	for(i=0;i<v;i++)
	{
			lgth = document.getElementById("cmbGrade").options.length -1;
			document.getElementById("cmbGrade").options[lgth] = null;
	}		
	
		for(i=0;i<t;i++)
	{
			lgth = document.getElementById("cmbSelGrade").options.length -1;
			document.getElementById("cmbSelGrade").options[lgth] = null;
	}		
}

function fillBillDesc()
  {
  		var selYear=document.forms[0].selYear.value;
  		var selMonth=document.forms[0].selMonth.options[document.forms[0].selMonth.selectedIndex].text;
  		var billNo=document.forms[0].billNo.options[document.forms[0].billNo.selectedIndex].text;
  		var billNoVal=document.forms[0].billNo.value;

		var desc;
		if(eval(selYear)==-1)
		{
			desc=billNo;
		}
		else if(eval(billNoVal!=-1))
		{
			desc=billNo+'('+selMonth+'/'+selYear+')'
		}
		if(eval(billNoVal)!=-1)
			document.forms[0].ctrlNo.value=desc;
		return true;
  }

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

// To clear the Order List in the Order-Head Mapping screen.

function clearOrderList()
{
	var v=document.getElementById("order").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("order").options.length -1;
			document.getElementById("order").options[lgth] = null;
	}		
}

function getGradesFromCategory()
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
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
			 }

		  
		 
		  var uri='';
		  var actionf="getGrades";
		  uri='./hrms.htm?actionFlag='+actionf;		 
//        alert(' ' + uri);	
        
        
        
        
         xmlHttp.onreadystatechange = function()
		{
			
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
				    clearGradeCombo();
				    //Added By Paurav for clearing designation combo
				    clearDesignationCombo();
				    //Ended by Paurav
					var cmbGrade = document.getElementById("cmbGrade");
					var cmbSelGrade = document.getElementById("cmbSelGrade");
					var txtGradeId = document.getElementById("txtGradeId");
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';

                        document.getElementById("cmbGrade").style.display = "";
                        
                        var gradeEntries = XMLDoc.getElementsByTagName('grade-mapping');
//                        alert('Grade entry size is ' + gradeEntries.length);
     				    

	           			  for ( var i = 0 ; i < gradeEntries.length ; i++ )
	     				   {
	     				    val=gradeEntries[i].childNodes[0].text;    
	     				    text = gradeEntries[i].childNodes[1].text;                             	     				    
	     				    
	     				    //for AIS/IAS
	     				    if(document.getElementById("cmbCat").value==1)
                             {
                             document.getElementById("cmbSelGrade").style.display = "none";
       			             document.getElementById("btnAdd").style.display = "none";
        			             document.getElementById("btnRemove").style.display = "none";        			                    			             
                               if(val==100110015) 
                               {
                                var y = document.createElement('option');
	     			            y.value=val;
      			                y.text=text;
      			                
      			                var y1 = document.createElement('option');
	     			            y1.value=val;
      			                y1.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
   	                               txtGradeId.value += val;	                               
	                               cmbSelGrade.add(y1,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
                                     txtGradeId.value += "," +  val;		   			 	  		    
		   			 	  		    cmbSelGrade.add(y1);
		   						 }
		   						 
		   						 //Added By Paurav for populating Designations on the basis of grades
		   						 //alert('Populating Designaitons');
		   						 populateDesignationsFromGrade(val);
		   						 //Ended By Paurav
		   						 
      			               }
      			             }
      			             //IAS/AIS ended
      			             
      			              //for Gadgeted
      			             else if(document.getElementById("cmbCat").value==2)
      			             {
      			             document.getElementById("cmbSelGrade").style.display = "none";
      			             document.getElementById("btnAdd").style.display = "none";      			             
       			             document.getElementById("btnRemove").style.display = "none";        			             
      			              if(val==100110011 || val==100110012)
      			              {
      			                var y = document.createElement('option');
  	     			            y.value=val;
      			                y.text=text;
      			                
      			                var y1 = document.createElement('option');
  	     			            y1.value=val;
      			                y1.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
   	                               txtGradeId.value += val;
	                               cmbSelGrade.add(y1,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
   	                                txtGradeId.value += "," +  val;
		   			 	  		    cmbSelGrade.add(y1);
		   						 }
		   						 
		   						 //Added By Paurav for populating Designations on the basis of grades
		   						 //alert('Populating Designaitons');
		   						 populateDesignationsFromGrade(val);
		   						 //Ended By Paurav
      			               }
      			             }    
      			             //Gadgeted End
      			             
      			             //For Non Gadgeted
     			             else if(document.getElementById("cmbCat").value==3) 
      			             {
      			             document.getElementById("cmbSelGrade").style.display = "none";
      			             document.getElementById("btnAdd").style.display = "none";
        			             document.getElementById("btnRemove").style.display = "none";        			                   			             
      			              if(val==100110013 || val==100110014)
      			              {
      			                var y = document.createElement('option');
  	     			            y.value=val;
      			                y.text=text;
      			                
      			                var y1 = document.createElement('option');
  	     			            y1.value=val;
      			                y1.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
	                               txtGradeId.value += val;
	                               cmbSelGrade.add(y1,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
   	                                txtGradeId.value += "," +  val;
		   			 	  		    cmbSelGrade.add(y1);
		   						 }	   		   						 
		   						 //Added By Paurav for populating Designations on the basis of grades
		   						 //alert('Populating Designaitons');
		   						 populateDesignationsFromGrade(val);
		   						 //Ended By Paurav  				 
      			               }
      			             }
      			             //Non - gadegeted end.
      			             
      			             
      			             //For Custom
     			             else if(document.getElementById("cmbCat").value==4) 
      			             {
        			             //document.getElementById("cmbSelGrade").style.display = "";
        			             //document.getElementById("cmbGrade").style.display = "none";
        			             //document.getElementById("btnAdd").style.display = "";
        			             document.getElementById("btnRemove").style.display = "";        			             
        			             //txtGradeId.value = '';
      			                var y = document.createElement('option');
  	     			            y.value=val;
      			                y.text=text;
      			                
      			                var y1 = document.createElement('option');
  	     			            y1.value=val;
      			                y1.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
	                               cmbSelGrade.add(y1,null);
	                               txtGradeId.value += "," + val;
	                              
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
		   			 	  		    cmbSelGrade.add(y1);
   	                                txtGradeId.value += "," +  val;
		   			 	  		    
		   						 }
		   						 
		   						 //Added By Paurav for populating Designations on the basis of grades
		   						 //alert('Populating Designaitons');
		   						 populateDesignationsFromGrade(val);
		   						 //Ended By Paurav  			   		   						 		   						   				 
      			             }
      			             //Custom end.
 	                        
	   		          }
	   		         }
 	            }
 	        }
 	   }
          		  		  
			//xmlHttp.onreadystatechange=stateChanged_Category;
			xmlHttp.open("POST",encodeURI(uri),true);
			xmlHttp.send(null);	
}

/*function stateChanged_Category()
{
if (xmlHttp.readyState==complete_state)
		{ 		  	        
		    clearGradeCombo();
			var cmbGrade = document.getElementById("cmbGrade");
			var cmbSelGrade = document.getElementById("cmbSelGrade");
			var txtGradeId = document.getElementById("txtGradeId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';

                        document.getElementById("cmbGrade").style.display = "";
                        document.getElementById("cmbSelGrade").style.display = "";
                        var gradeEntries = XMLDoc.getElementsByTagName('grade-mapping');
//                        alert('Grade entry size is ' + gradeEntries.length);
     				    

	           			  for ( var i = 0 ; i < gradeEntries.length ; i++ )
	     				   {
	     				    val=gradeEntries[i].childNodes[0].text;    
	     				    text = gradeEntries[i].childNodes[1].text; 
                            // alert('Grade val is:-->' + val + 'and text is:--->' + text);
	     				    
	     				    
	     				    //for AIS/IAS
	     				    if(document.getElementById("cmbCat").value==1)
                             {
                               if(val==100110015) 
                               {
                               var y = document.createElement('option');
	     			            y.value=val;
      			                y.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
   	                               txtGradeId.value += val;	                               
//	                               cmbSelGrade.add(y,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
                                     txtGradeId.value += "," +  val;		   			 	  		    
	//	   			 	  		    cmbSelGrade.add(y);
		   						 }
      			               }
      			             }
      			             //IAS/AIS ended
      			             
      			              //for Gadgeted
      			             if(document.getElementById("cmbCat").value==2)
      			             {
      			              if(val==100110011 || val==100110012)
      			              {
      			                var y = document.createElement('option');
  	     			            y.value=val;
      			                y.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
   	                               txtGradeId.value += val;
	  //                             cmbSelGrade.add(y,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
   	                                txtGradeId.value += "," +  val;
		//   			 	  		    cmbSelGrade.add(y);
		   						 }
      			               }
      			             }    
      			             //Gadgeted End
      			             
      			             //For Non Gadgeted
     			             if(document.getElementById("cmbCat").value==3) 
      			             {
      			              if(val==100110013 || val==100110014)
      			              {
      			              var y = document.createElement('option');
  	     			            y.value=val;
      			                y.text=text;
      			                
      			                try
	 	   				        {	      				    					
	                               cmbGrade.add(y,null);
	                               txtGradeId.value += val;
	      //                         cmbSelGrade.add(y,null);
		           		        }		           		
		 						 catch(ex)
		   						 {
		   			 	  		    cmbGrade.add(y); 
   	                                txtGradeId.value += "," +  val;
		   			 	  		//    cmbSelGrade.add(y);
		   						 }	   						 
      			               }
      			             }
      			             //Non - gadegeted end.
 	                        
	   		          }
	   		         }
 	   }

}*/

/*function addGrades()
{

 var txtGrade = document.getElementById("txtGradeId");
 txtGrade.value='';
 var selGrade = document.getElementById("cmbSelGrade").value;
 var destGradeCmb =  document.getElementById("cmbGrade");
 var y = document.createElement('option');
 y.value = selGrade;
 y.text = document.forms[0].cmbSelGrade(document.getElementById("cmbSelGrade").selectedIndex).text;
 
  document.getElementById("cmbSelGrade").options[document.getElementById("cmbSelGrade").selectedIndex] = null;
 try{
 destGradeCmb.add(y,null);
 //txtGrade+=selGrade;
 }catch(ex)
 {
 destGradeCmb.add(y)
// txtGrade+=selGrade;
 }
 
 document.getElementById("cmbGrade").style.display = "";
 document.getElementById("btnRemove").style.display = "";
 var v=document.getElementById("cmbGrade").length;
	for(i=0;i<v;i++)
	{	
//			alert('in add if ' +  document.getElementById("cmbGrade").options[i].value);
			txtGrade.value += "," + document.getElementById("cmbGrade").options[i].value;
			
	}	
	//Added By Paurav for populating the designations as per the grades added
	if(v==1) clearDesignationCombo();
	populateDesignationsFromGrade(selGrade);
	//Ended By Paurav
	
	
}*/


function removeGrades()
{
 var txtGrade = document.getElementById("txtGradeId");
 txtGrade.value = '';
 var GradeCmb = document.getElementById("cmbGrade").value;
 var selGrade =  document.getElementById("cmbSelGrade");
 var y = document.createElement('option');
 y.value = GradeCmb;
 y.text = document.forms[0].cmbGrade(document.getElementById("cmbGrade").selectedIndex).text;
 
  document.getElementById("cmbGrade").options[document.getElementById("cmbGrade").selectedIndex] = null;
 try{
 selGrade.add(y,null);
 //txtGrade+=selGrade;
 }catch(ex)
 {
 selGrade.add(y)
// txtGrade+=selGrade;
 }
	//Added By Paurav for removing designations for the grade being removed
	clearDesignationCombo();
	//Ended By Paurav
    var v=document.getElementById("cmbGrade").length;
    /*if(v==0)
    {
    	document.getElementById("btnRemove").style.display = "none";
       	document.getElementById("cmbGrade").style.display = "none";
    	var t=document.getElementById("cmbSelGrade").length;       
    	for(i=0;i<t;i++)
		{
			//alert('in add if ' +  document.getElementById("cmbGrade").options[lgth].value);
			txtGrade.value += "," + document.getElementById("cmbSelGrade").options[i].value
		}	
    }*/
	for(i=0;i<v;i++)
	{
		//alert('in add if ' +  document.getElementById("cmbGrade").options[lgth].value);
		txtGrade.value += "," + document.getElementById("cmbGrade").options[i].value
		
		//Added By Paurav
		populateDesignationsFromGrade(document.getElementById("cmbGrade").options[i].value);
		//Ended By Paurav
	}
	
}
function setDemandOuter()
{
 document.getElementById('Demand').value = document.forms[0].cmbDemand.value;

}
function GetMonths()
{
		    document.getElementById('billNo').options[0].text = 'Select';
		    document.getElementById('billNo').value = '-1';
			clearAllData();
			clearGradeDsgnListBox();
          //  alert( 'Villages Funct called..');
		  document.forms[0].billNo.value='-1';
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&year_id='+ document.forms[0].cmbYear.value;
		  var actionf="getMonths";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Year;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function stateChanged_Year()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
		    clearMonthCombo();
  	        
			var month = document.getElementById("cmbMonth");
			var yearVal = document.getElementById("cmbYear").value;

					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var monthEntries = XMLDoc.getElementsByTagName('month-mapping');
                        //alert('month entry size is ' + monthEntries.length);
	           			for ( var i = 0 ; i < monthEntries.length ; i++ )
	     				{
	     				    val=monthEntries[i].childNodes[0].text;    
	     				    text = monthEntries[i].childNodes[1].text; 
//	     				    alert('Month val is:-->' + val + 'and text is:--->' + text);
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
	   		         fillBillDesc();
 	   }
 }

//added by ravysh for multiple month supplimentary bill
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
		  url= uri+'&year_id='+ document.forms[0].cmbFromYear.value;
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
  	        
			var month = document.getElementById("cmbFromMonth");
			var yearVal = document.getElementById("cmbFromYear").value;

					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var monthEntries = XMLDoc.getElementsByTagName('month-mapping');
                        //alert('month entry size is ' + monthEntries.length);
	           			for ( var i = 0 ; i < monthEntries.length ; i++ )
	     				{
	     				    val=monthEntries[i].childNodes[0].text;    
	     				    text = monthEntries[i].childNodes[1].text; 
//	     				    alert('Month val is:-->' + val + 'and text is:--->' + text);
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
	var v=document.getElementById("cmbFromMonth").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbFromMonth").options.length -1;
			document.getElementById("cmbFromMonth").options[lgth] = null;
	}		
}
//ended by ravysh
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
		  url= uri+'&loc_code='+ document.forms[0].cmbDept.value;
		  var actionf="getDemandNo";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_Dept;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function GetDemandNobyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&loc_code='+ document.forms[0].cmbDept.value+'&month='+month+'&Year='+Year;
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
	     				    val=demandNoEntries[i].childNodes[0].text;    
	     				    text = demandNoEntries[i].childNodes[0].text; 
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

         document.getElementById('Demand').value = document.forms[0].cmbDemand.value;
		  
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value;
		  var actionf="getMjrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
//       alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_DemandNo;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function GetMjrHeadNobyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
         document.getElementById('Demand').value = document.forms[0].cmbDemand.value;
		  
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value+'&month='+month+'&Year='+Year;
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
	     				    val=mjrHeadEntries[i].childNodes[0].text;    
	     				    text = mjrHeadEntries[i].childNodes[0].text; 
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
          document.getElementById('MjHd').value = document.forms[0].cmbMjrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value;
		  var actionf="getSubMjrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
         //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_MjrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function GetSubMjrHeadNobyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
          document.getElementById('MjHd').value = document.forms[0].cmbMjrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value+'&month='+month+'&Year='+Year;
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
	     				    val=subMjrHeadsEntries[i].childNodes[0].text;    
	     				    text = subMjrHeadsEntries[i].childNodes[0].text; 
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
           document.getElementById('SubMjHd').value = document.forms[0].cmbSubMjrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value;
		  var actionf="getMnrHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_subMjrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function GetMnrHeadsbyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
           document.getElementById('SubMjHd').value = document.forms[0].cmbSubMjrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value+'&month='+month+'&Year='+Year;
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
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[0].text; 
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
           document.getElementById('MnrHd').value = document.forms[0].cmbMnrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value;
		  var actionf="getSubHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_mnrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function GetSubHeadsbyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
           document.getElementById('MnrHd').value = document.forms[0].cmbMnrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value+'&month='+month+'&Year='+Year;
		  var actionf="getSubHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_mnrHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function GetSubHeads1()
{

 //           alert( ' Funct called..' + document.frmBF.branchCountry.value);
           document.getElementById('MnrHd').value = document.forms[0].cmbMnrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value;
		  var actionf="getSubHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_mnrHead1;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function stateChanged_mnrHead1()
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
					// alert('No Records Found For This Sub Head');
					 }
					else
					{
                        window.status='';
				       var entries = XMLDoc.getElementsByTagName('subHead-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[2].text; 
	     				    id = entries[i].childNodes[1].text; 
	 		//  alert('val is:-->' + val + 'and text is:--->' + text + ' and id is:-->' + id);
	     				    var y = document.createElement('option')
	     				      
	     			        y.value=val;
	     			        y.text='('+id+')'+text;	
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
	     				    val=entries[i].childNodes[1].text;    
	     				    text = entries[i].childNodes[1].text; 
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
 document.getElementById('txtSbHd').value = document.forms[0].cmbSubHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value + '&subHead_code='+ document.forms[0].cmbSubHead.value;
		  var actionf="getDtlHeads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
   //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_subHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function GetDtlHeadsbyMonthYear()
{

          var month = document.getElementById("cmbMonth").value;
          var Year = document.getElementById("cmbYear").value;
          document.getElementById('txtSbHd').value = document.forms[0].cmbSubHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value + '&subHead_code='+ document.forms[0].cmbSubHead.value+'&month='+month+'&Year='+Year;
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
	document.getElementById("DtlHd").value='00';
	loadData();
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
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[0].text; 
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

/*


*/


function getOrderList()
{
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&cmbDept='+ document.forms[0].cmbDept.value;
		  var actionf="getOrderListByLocation";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=deptChanged;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function deptChanged()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
			clearDemandNo();
		    clearMjrHeadCombo();		     
  	        clearSubMjrHeadCombo();
  	        clearMnrHeadCombo();
  	        clearSubHeadCombo();
  	        clearDetailHeadCombo();
		    clearOrderList();
  	        
			var order = document.getElementById("order");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var orderList = XMLDoc.getElementsByTagName('order-mapping');
	           			for ( var i = 0 ; i < orderList.length ; i++ )
	     				{
	     				    val=orderList[i].childNodes[0].text;    
	     				    text = orderList[i].childNodes[1].text; 
	//     				    alert('Village val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               order.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    order.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }  


//Added By Paurav for populating Designations on the basis of Grades selected
function populateDesignationsFromGrade(gradeId)
{
	//alert('Now trying to fetch designations throught ajax');
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
            	//alert("here2");
        		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        	}
			catch (e)
			{
				alert("Your browser does not support AJAX!");        
			    return false;        
			}
		}
	}
	
	//alert('Ajax object' + xmlHttp);
	var uri='';
	var actionf="getDesignationsFromGrade";
	uri='./hrms.htm?actionFlag='+actionf+'&gradeId='+gradeId;
	
	
	xmlHttp.onreadystatechange = function()
	{
		
		try{	
		
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{
				var cmbDesignations = document.getElementById("cmbDesignations");
				//alert('Entries in Designation Combos are ' + cmbDesignations.length);
				var XMLDoc=xmlHttp.responseXML.documentElement;
				
				if(XMLDoc==null)
                {
                  window.status = 'No Records Found.';
                }
                else
                {
                	var desigEntries = XMLDoc.getElementsByTagName('designation');
                	//alert('No of Designations for Id gradeId ' + desigEntries.length);
                	for ( var i = 0 ; i < desigEntries.length ; i++ )
	     			{
	     				val=desigEntries[i].childNodes[0].text;    
	     				text = desigEntries[i].childNodes[1].text; 
	     				
	     				var y = document.createElement('option');
	     			    y.value=val;
      			        y.text=text;
      			        
      			        try{
      			        	cmbDesignations.add(y);
      			        }catch(e)
      			        {
      			        	cmbDesignations.add(y);
      			        }
	     			}	                            	     				    
	     		}
			}
		}
		}catch(e)
		{
			window.status('Error ' + e);
		}
	
	}
	
	xmlHttp.open("POST",encodeURI(uri),false);
	xmlHttp.send(null);	
}

function clearDesignationCombo()
{
	var v=document.getElementById("cmbDesignations").length;
	
	for(i=0;i<v;i++)
	{
			lgth = document.getElementById("cmbDesignations").options.length -1;
			document.getElementById("cmbDesignations").options[lgth] = null;
	}		
	
	var v1=document.getElementById("cmbSelDesignations").length;
	
	for(i=0;i<v1;i++)
	{
			lgth = document.getElementById("cmbSelDesignations").options.length -1;
			document.getElementById("cmbSelDesignations").options[lgth] = null;
	}
}

function removeDesignation()
{
	if(document.getElementById("cmbSelDesignations").length == 0)
	{
		return;
	}
	var designations = document.getElementById("designations");
	designations.value = '';
	var designation = document.getElementById("cmbSelDesignations").value;
 	var y = document.createElement('option');
 	y.value = designation;
 	y.text = document.forms[0].cmbSelDesignations.options[document.getElementById("cmbSelDesignations").selectedIndex].text;
 
  	document.getElementById("cmbSelDesignations").options[document.getElementById("cmbSelDesignations").selectedIndex] = null;
	
	var length = document.getElementById("cmbSelDesignations").length;
	
	for(i=0;i<length;i++)
	{
		if(designations == '' || designations == null)
			designations.value += document.forms[0].cmbSelDesignations.options[i].value;
		else
			designations.value += "," + document.forms[0].cmbSelDesignations.options[i].value;
		
	}
	
	//alert(designations.value);
}
//Commented by Mrugesh.(If we want to select the designation then remove the comment
/*function addDesignation()
{
	if(document.getElementById("cmbDesignations").length == 0)
	{
		return;
	}
	var designations = document.getElementById("designations");
	designations.value = '';
	var designation = document.getElementById("cmbDesignations").value;
 	var y = document.createElement('option');
 	y.value = designation;
 	y.text = document.forms[0].cmbDesignations.options[document.getElementById("cmbDesignations").selectedIndex].text;
 
  	//document.getElementById("cmbDesignations").options[document.getElementById("cmbDesignations").selectedIndex] = null;
  	try
  	{
  		document.getElementById("cmbSelDesignations").add(y);
  	}catch(e)
  	{
  		document.getElementById("cmbSelDesignations").add(y);
  	}
	
	var length = document.getElementById("cmbSelDesignations").length;
	
	for(i=0;i<length;i++)
	{
		if(designations == '' || designations == null)
			designations.value += document.forms[0].cmbSelDesignations.options[i].value;
		else
			designations.value += "," + document.forms[0].cmbSelDesignations.options[i].value;
		
	}
	
	//alert(designations.value);
}*/
//Ended by Mrugesh

//Added by Mrugesh.
function addDesignationForBill()
{
	/*if(document.getElementById("cmbDesignations").length == 0)
	{
		return;
	}
	var designations = document.getElementById("designations");
	designations.value = '';
	var designation = document.getElementById("cmbDesignations").value;
 	var y = document.createElement('option');
 	y.value = designation;
 	y.text = document.forms[0].cmbDesignations.options[document.getElementById("cmbDesignations").selectedIndex].text;
 
  	//document.getElementById("cmbDesignations").options[document.getElementById("cmbDesignations").selectedIndex] = null;
  	try
  	{
  		document.getElementById("cmbSelDesignations").add(y);
  	}catch(e)
  	{
  		document.getElementById("cmbSelDesignations").add(y);
  	}*/
	
	var designations = document.getElementById("designations");
	var length = document.getElementById("cmbSelDesignations").length;
	
	for(i=0;i<length;i++)
	{
		if(designations == '' || designations == null)
			designations.value += document.forms[0].cmbSelDesignations.options[i].value;
		else
			designations.value += "," + document.forms[0].cmbSelDesignations.options[i].value;
		
	}
		
	//alert(designations.value);
}

function addGradesForBill()
{
		
	var grades = document.getElementById("gradeId1");
	var length = document.getElementById("cmbGrade").length;
	
	for(i=0;i<length;i++)
	{
		if(grades == '' || grades == null)
			grades.value += document.forms[0].cmbGrade.options[i].value;
		else
			grades.value += "," + document.forms[0].cmbGrade.options[i].value;
		
	}
	
	//alert(grades.value);
}
//Ended by Mrugesh
function loadData()
		{
		setText(document.getElementById("cmbBudType"));
		getValidHeadCode(document.getElementById("txtSchemeNo"), 7);
		getDDOHeadBySchemeNo();
		getValidHeadCode(document.getElementById("Demand"), 3);
		getValidHeadCode(document.getElementById("MjHd"), 4);
		getValidHeadCode(document.getElementById("SubMjHd"), 2);
		getValidHeadCode(document.getElementById("MnrHd"), 3);
		getValidHeadCode(document.getElementById("SubHd"), 2);
		getValidHeadCode(document.getElementById("DtlHd"), 2);
		getSchemeNoByDDOGrantHead();
		getHdChrgble();
		showChallan();
	
		
		}
//Ended By Paurav

//Added By Varun For Clear Class-Designation List box while selecting Bill Dt.11-June-2008
function clearGradeDsgnListBox(){

var w=document.getElementById("cmbGrade").length;
	
	for(i=0;i<w;i++)
	{
			lgth = document.getElementById("cmbGrade").options.length -1;
			document.getElementById("cmbGrade").options[lgth] = null;
	}		
	

var v=document.getElementById("cmbSelDesignations").length;
	
	for(i=0;i<v;i++)
	{
			lgth = document.getElementById("cmbSelDesignations").options.length -1;
			document.getElementById("cmbSelDesignations").options[lgth] = null;
	}		
	
	}
//Ended by Varun	





//Added by Abhilash for Scheme to get the head chargable
function clearHeadChargable()
{	
	var v=document.getElementById("cmbSubHead").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("cmbSubHead").options.length -1;
			document.getElementById("headChargable").value= '';
	}		
}

//Ended




//added by Abhilash for Scheme Master
function GetSubHeads2()
{

      // alert( ' Funct called..' + document.frmBF.branchCountry.value);
         document.getElementById('MnrHd').value = document.forms[0].cmbMnrHead.value;
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&demand_no='+ document.forms[0].cmbDemand.value + '&mjrHead_Code='+ document.forms[0].cmbMjrHead.value + '&subMjrHead_Code=' + document.forms[0].cmbSubMjrHead.value + '&mnrHead_code=' +  document.forms[0].cmbMnrHead.value;
		  var actionf="getSubHeadsSchemeMaster";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_mnrHead1;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function stateChanged_mnrHead1()
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
					// alert('No Records Found For This Sub Head');
					 }
					else
					{
                      window.status='';
				       var entries = XMLDoc.getElementsByTagName('subHead-mapping');            				    
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[2].text; 
	     				    id = entries[i].childNodes[1].text; 
	 		//  alert('val is:-->' + val + 'and text is:--->' + text + ' and id is:-->' + id);
	     				    var y = document.createElement('option')
	     				      
	     			        y.value=val;
	     			        y.text='('+id+')'+text;	
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



//ended




//Added by abhilash for head chargable-----For Scheme

function GetHeadChargable()
{

 // alert( ' Funct called..' + document.frmBF.branchCountry.value);
		  document.getElementById('txtSbHd').value = document.forms[0].cmbSubHead.value;
		  
		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&sub_Head='+ document.forms[0].cmbSubHead.value ;
		  var actionf="getHeadChargableFromSubHead";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //      alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_subHead;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}


function stateChanged_subHead()
	{	
	
	clearHeadChargable();
	//clearDetailHeadCombo();
	   //clearHeadChargable();
		if (xmlHttp.readyState==complete_state)
		{ 		    
			var dtlHead = document.getElementById("headChargable");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc == null)
					{
					 window.status = 'No Records Found.';
					 }
					else
					{
						
                      window.status='';
				       var entries = XMLDoc.getElementsByTagName('DtlHead-mapping');      
				       //alert('entries' + entries.length);	
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     				    val=entries[i].childNodes[0].text;    
	     				    text = entries[i].childNodes[0].text; 
	     				 //  alert('text' + text);	
	 //    				    alert('val is:-->' + val + 'and text is:--->' + text);
	     				  document.getElementById('headChargable').value =  text;
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