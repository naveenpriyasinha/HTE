
function checkfixpay()
{
	var empId = document.getElementById("Employee_ID_EmpSearch").value;
	document.frmBF.empId.value=empId;
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=getVpfDtlsByEmpId&empId="+empId+"&check=1";  
		
        
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {	

			    var initBasic;
			    var emptype;
			    var pfAmount;
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var vpfDetailsMapping = XMLDocForAjax.getElementsByTagName('vpfDetailsMapping');	
				var flag="true";					
				if(vpfDetailsMapping.length != 0) {
					//initBasic=vpfDetailsMapping[0].childNodes[0].text;

					//pfAmount = vpfDetailsMapping[0].childNodes[1].text;	

					emptype= vpfDetailsMapping[0].childNodes[0].text;					
					if(emptype==300018 || emptype==300225)
					{
						alert("Not Accessible For Fixed and Contractual Employee!!");
	     			    document.frmBF.vpfAmount.value='';
						clearEmployee("EmpSearch");
						//window.history.back();
						return false;
					}
					}
		
}
}}

				xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));	
				return true;


}

function chkFunc()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
		return false;
	
	}
	else
	{
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
				   return false;   
				 
				}
			}
		}
		var url = "hrms.htm?actionFlag=getVPFView&empId="+empId+"&chk=1";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {
						
						LoanNo = loanAdvMapping[0].childNodes[0].text;
						if(eval(LoanNo)==-1){
						
							var res = confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system.\n Want to Enter the Record.");
							
							document.frmBF.reset();
							retValue=true;
							if(res){
							
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=newEmpData&newEntryEmpId="+empId;
										window.location=url;
							//document.frmBF.action=url;
							//document.frmBF.submit();
							}
							else
							{
								window.location="./hrms.htm?actionFlag=getVPFView";
							}
							
							
						}
						else
						{
							
                        	if(eval(LoanNo)==0)
							{
								var res = confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system.\n Want to Enter the Record.");
							
								document.frmBF.reset();
								retValue=true;
								if(res){
								
										
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
										
										window.location=url;
							//document.frmBF.action=url;
							//document.frmBF.submit();
								}
							else
							{
								window.location="./hrms.htm?actionFlag=getVPFView";
							}
								
								return;
							}
							
							else
							{
								
						    	if("${empAllRec}"!='true')
								{
								var res=confirm("Emp record is already Entered.\n Do you want to update it.");
							 	if(res)
							 	{
								   window.location="./hrms.htm?actionFlag=getVpfDtlsById&vpfId="+loanAdvMapping[0].childNodes[1].text+"&edit=Y&empId="+empId;
									//document.frmBF.submit();
									retValue=true;
									
									}
							else
							{
								window.location="./hrms.htm?actionFlag=getVPFView";
							}
									
								}
							      						     	
								else
								{
							     	retValue=false;
							     	
							     }
						     	
							}
						}
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return true;
	}
}

function validateForm()
{	
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.frmBF.empId.value=empName;
	    if(chkFunc()!=true)
	    {
	     return false;
	    }
		else if(empName!=null&&empName!='')
		{
		if(!checkAvailability(document.frmBF.vpfAmount.value))
		return false;
		else
		return true;
		//document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N";
		//document.frmBF.submit();
		}
		else
		return true;
}

function beforeSubmit()
{
				
		/*var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.frmBF.empId.value=empName;
	    if(chkFunc()!=true)
	    {
	     document.frmBF.action = 'javascript:beforeSubmit()';
	    }
		else if(empName!=null&&empName!='')
		{
		checkAvailability(document.frmBF.vpfAmount.value);*/
		if("${empAllRec}"=='true')
		window.location="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=true";
		else
		window.location="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=false";
		//document.frmBF.submit();
		//}
}

function chkKey(e){	
	if(e.keyCode=='13')
		return false;
	else	
		return true;
}
function checkAvailability(newVpf)
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if(document.getElementById("Employee_ID_EmpSearch").value!='')
	if(checkfixpay()==false)
	return;
	else
	{	
	var vpfAmount=newVpf.value;	
	document.frmBF.empId.value=empId;
	if(vpfAmount!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=getVpfDtlsByEmpId&empId="+empId+"&check=2";  
		
        
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {	

			    var initBasic;
			    var emptype;
			    var pfAmount;
			    var isEmpAvail;
			    var isGpfAcc;
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var vpfDetailsMapping = XMLDocForAjax.getElementsByTagName('vpfDetailsMapping');	
				var flag="true";					
				if(vpfDetailsMapping.length != 0) {	
					isEmpAvail = vpfDetailsMapping[0].childNodes[1].text;					
					if(isEmpAvail==0){
						var res = confirm("The information for Basic Detail"+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system");
		                //window.location.reload();	
		                document.frmBF.vpfAmount.value='';
		                if(res){
								
										
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
									var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
								window.location=url;
								//document.frmBF.submit();
								}
							else
							{
								window.location="./hrms.htm?actionFlag=getVPFView";
							}
								
								//return;
		                
						//clearEmployee("EmpSearch");
						
						//return false;		                		                
					}					
					isGpfAcc = vpfDetailsMapping[0].childNodes[2].text;
					if(isGpfAcc==0){
						alert(document.getElementById("Employee_Name_EmpSearch").value+" has not opened the GPF Account");
		                //window.location.reload();	
		                document.frmBF.vpfAmount.value='';
						clearEmployee("EmpSearch");
						return false;		                		                
					}
					initBasic=vpfDetailsMapping[0].childNodes[3].text;
					pfAmount = vpfDetailsMapping[0].childNodes[4].text;
					var maxAmount = parseFloat(initBasic);
					var pfAmt = parseFloat(pfAmount);

					/*alert("The Initial Basic is:-"+initialBasic);
					alert("The Value of PF Amount is :-"+pfAmt);										
					alert("The Value of VPF Amount is :-"+vpfAmount);					*/
						if(vpfAmount < pfAmt){
							alert("VPF Amount must be greater then "+pfAmt);
							document.frmBF.vpfAmount.value='';
							document.frmBF.vpfAmount.focus();
							return false;
						}
						else if(vpfAmount > maxAmount){
								 alert("VPF Amount must be Less then "+maxAmount);
								 document.frmBF.vpfAmount.value='';
							     document.frmBF.vpfAmount.focus();
							     return false;
							}
							else{		    
								return true;
							}
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	}
		else
		{
		 alert('Please select an Employee')
		 newVpf.value='';
		 return false;
		}
	
	
}