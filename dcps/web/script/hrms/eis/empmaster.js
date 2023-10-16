
function clearSubCastCombo()
{
	var v=document.getElementById("empSubCastId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("empSubCastId").options.length -1;
			document.getElementById("empSubCastId").options[lgth] = null;
	}		
}

function clearCastCombo()
{
	var v=document.getElementById("empCastId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("empCastId").options.length -1;
			document.getElementById("empCastId").options[lgth] = null;
	}		
}


function getCasts()
{
	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&religionId='+ document.frmBF.Religion.value;
		  var actionf="getCastSubCastData";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		 // alert('url is:-' + url);	  		  		  
		  xmlHttp.onreadystatechange=stateChangedReligion;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function stateChangedReligion()
	{
	
		if (xmlHttp.readyState==complete_state)
		{ 		
			clearSubCastCombo();		    			
  		    clearCastCombo();
  		   
			var castList = document.getElementById("empCastId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var castMapping = XMLDoc.getElementsByTagName('Cast-mapping');
                       // alert("The CastMapping Length is:-"+castMapping.length);
	           			for ( var i = 0 ; i < castMapping.length ; i++ )
	     				{
	     				    val=castMapping[i].childNodes[0].text;    
	     				    text = castMapping[i].childNodes[1].text; 	     				    
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               castList.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    castList.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }
 
 //Added by Varun For GPF A/c No:- Uniqueness Dt.02-08-2008
 function chkuniqueness()
{

	var retValue=true;
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
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
				    retValue=false;     
				}
			}
		}
		var gpfAcctNo=document.frmBF.gpfAcctNo.value;

		var url = "hrms.htm?actionFlag=getGpfDtls&gpfAcctNo='"+gpfAcctNo+"'&chk=3";  

		xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var gpfNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('gpfAccNo');
					if(loanAdvMapping.length != 0) {	
						gpfNo = loanAdvMapping[0].childNodes[0].text;	

						if(gpfNo==-1){
							alert("This GPF Account No. is already entered in the system");
							document.frmBF.gpfAcctNo.value='';
							document.frmBF.gpfAcctNo.focus();
							
									retValue=false;
							        return false; 
						     	}  
								else
								{
							     	retValue=true;
							     	return true; 
							     }
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
}
 
 //Ended by Varun For GPF A/c No:- Uniqueness Dt.02-08-2008

function getSubCasts()
{


			
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&castId='+ document.frmBF.emp_caste_id.value;
		  var actionf="getCastSubCastData";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  //alert('url is:-' + url);	  		  		  
		  xmlHttp.onreadystatechange=stateChangedCast;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function stateChangedCast()
	{
	
		if (xmlHttp.readyState==complete_state)
		{ 		
			clearSubCastCombo();		    			
  		   // clearCastCombo();
  		   
			var subCastList = document.getElementById("empSubCastId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var subCastMapping = XMLDoc.getElementsByTagName('subCast-mapping');
                       // alert("The CastMapping Length is:-"+subCastMapping.length);
	           			for ( var i = 0 ; i < subCastMapping.length ; i++ )
	     				{
	     				    val=subCastMapping[i].childNodes[0].text;    
	     				    text = subCastMapping[i].childNodes[1].text; 
	     				    
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               subCastList.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    subCastList.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }





function checkConfDate()
{
	if (document.forms[0].elements['emp_doj']!=null && document.forms[0].elements['emp_conf']!=null && document.forms[0].elements['emp_conf'].value!="" && document.forms[0].elements['emp_leave_dt']!=null) 
	{	
		var dob = document.forms[0].elements['emp_dob'].value;
		var doj =  document.forms[0].elements['emp_doj'].value;
		var dor=document.forms[0].elements['emp_leave_dt'].value;
		var doc=document.forms[0].elements['emp_conf'].value;
         var diff=compareDate(dob,doc);
			if (diff<=0)
			{
				alert("Confirmation Date should not be less then or equal to Birth Date");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         var diff=compareDate(doj,doc);
			if (diff<=0)
			{
				alert("Confirmation Date should not be less then or equal to Joining Date ");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         var diff=compareDate(doc,dor);
			if (diff<=0)
			{
				alert("Confirmation Date should not be greater then or equal to Retirement Date");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         var diffdoj=compareDate(doj,dob);
			if (doj!=''&&diffdoj>=0)
			{
				alert("Joining Date should  be greater then Date of Birth");
				document.forms[0].elements['emp_doj'].value="";
				document.forms[0].elements['emp_doj'].focus();
				return false;
			}
	}
	return true;

}
	
/*function validateEmail(field)
   {
		var field_value=field.value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQzXCVBNM.@_-";
		if(field_value!="")
		{
		
		for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
          alert("Enter Valid Charecters in Email Field");
          field.focus();
          return false;
        }
    } 
	 atpos=field_value.indexOf("@",1)
  if(atpos==-1)
	 {
                alert("Enter your email id in format like info@tcs.com");
		field.focus();
		return false;
	 }
	 periodpos=field_value.lastIndexOf(".")
	 if(periodpos==-1)
	 {
	        alert("should have @y.com etc");
		field.focus();
	        return false;
       	 }

	if(!((periodpos + 3 == field_value.length) || (periodpos + 4 == field_value.length)))
	{
		alert("Enter just 3 chars after the symbol.,like .com etc");
		return false;
	}
	}
 return true;
 }*/

function validateForm()
{


if(!chkuniqueness())
	{
	return false;
	}
	else{
	 var uri = "./hrms.htm?actionFlag=";

	 if("${empAllRec}"=='true')
	 {

	 var url = uri + "insertEmpData&edit=N&empId=${empId}&empAllRec=Y";
	 	 	
	 }
	else
 	{
 	 var url = uri + "insertEmpData&edit=N";
 	
	}
	/* if(document.frmBF.email.value!="")
    {
    if(!validateEmail(document.frmBF.email))
    {  
          
          document.frmBF.email.value="";
          document.frmBF.email.focus();
    }
	 else
	 {
	
	 document.frmBF.action = url;
	 document.frmBF.submit();
	 }
    }
 else
 {
*/

document.frmBF.action = url;
 document.frmBF.submit();
 //}
 
}
}

function getEmployeeDetails()
{
	addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch'));
}
function ChkEmp()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
								if(emp>0)
								{
									var res=confirm("Information for the employee is already entered\nWant To update the record.");
									if(res)
										window.location="./hrms.htm?actionFlag=getEmpData&empId="+empId+"&edit=Y";
								}
								/*else if(emp==0)
								{
								   alert("Selected employee belongs to other department ")
								}*/
								else
								{
									getNewEmployeeDetails();
								}	
							}
								
			}
		}
}
function getNewEmployeeDetails()
{
		addOrUpdateRecord('empDetails', 'getOrgEmployeeDetails', new Array('Employee_ID_EmpSearch'));
}
function getNewEmployeeDetailsFromLink(emp_id)
{
		document.getElementById("emp_id").value=emp_id;
		addOrUpdateRecord('empDetails', 'getOrgEmployeeDetails', new Array('emp_id'));
		document.getElementById("emp_id").value="";
}
function empDetails() {

        //alert("Function Called");	
       	if (xmlHttp.readyState == 4) {    
		
			if (xmlHttp.status == 200) {	
			 		
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
               
				var empMapping = XMLDocForAjax.getElementsByTagName('empMapping');	
					
				var flag="true";
				
					document.frmBF.emp_first_name.value=empMapping[0].childNodes[0].text;
					
					document.frmBF.emp_middle_name.value=empMapping[0].childNodes[1].text;
					
					document.frmBF.emp_last_name.value=empMapping[0].childNodes[2].text;
					
					document.frmBF.emp_dob.value=empMapping[0].childNodes[3].text;
					
					document.frmBF.emp_doj.value=empMapping[0].childNodes[4].text;
					
					document.frmBF.emp_leave_dt.value=empMapping[0].childNodes[5].text;
					
					document.frmBF.Salutation.value=empMapping[0].childNodes[6].text;				
					
					document.frmBF.employeeId.value=empMapping[0].childNodes[7].text;	

					document.frmBF.userID.value=empMapping[0].childNodes[8].text;	
						
					document.getElementById('tblEmpInfo').style.display='';
					
					document.getElementById('table_of_search').style.display='none';
					document.getElementById('table_of_rows').style.display='none';
					document.getElementById('link').style.display='';
					document.frmBF.contactNo.focus();
								
			}
		}
	}
function showEmpName()
{
 childWindow = window.open("hrms.htm?actionFlag=getOrgEmpData","OrgEmployeeNames","toolbar=no, location=no, directories=no,status=no, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
 if (childWindow.opener == null) childWindow.opener = self;
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		getEmployeeDetails();
		return true;
		//getNewEmployeeDetails();
	}
	
}
function clearTable()
{
	document.getElementById("Reset").click();
	document.getElementById('tblEmpInfo').style.display='none';
	document.getElementById('link').style.display='none';
	document.getElementById('table_of_rows').style.display='';
	
}
