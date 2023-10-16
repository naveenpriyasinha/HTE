function chkFunc()
{
  
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	document.miscRecover.empName.value=empId;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
		
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
				}
			}
		}
		var url = "hrms.htm?actionFlag=getMiscData&empId="+empId+"&chk=1&date="+document.getElementById("date").value;  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var eisEmpMapping = XMLDocForAjax.getElementsByTagName('eisEmpMapping');	
					if(eisEmpMapping.length != 0) {	
						eisEmpNO = eisEmpMapping[0].childNodes[0].text;	
						emptype=eisEmpMapping[0].childNodes[1].text;
					/*if(emptype==300018 || emptype==300225)
					{
						alert("Not Accessible For Fixed and Contractual Employee!!");
	     			    document.miscRecover.amount.value='';
						clearEmployee("EmpSearch");
						//window.history.back();
						window.self.focus();
						return false;
					}*/
						
						if(eisEmpNO==0){
							var res=confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system.\n Want To enter Information.");
							document.miscRecover.amount.value='';
							window.self.focus();
							clearEmployee("EmpSearch");
							document.miscRecover.reset();
							if(res){
							
										var url = "./hrms.htm?actionFlag=newEmpData&empAllRec=false&empId=0&newEntryEmpId="+empId;
										document.miscRecover.action=url;
										document.miscRecover.submit();
											retValue=true;
							       		
										}
										else
										{
											retValue=false;
							        	
										}
							
							
							
						}
						if(eisEmpNO==1){
							var res=confirm("The Basic Detail for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system.\n Want To enter Information.");
							document.miscRecover.amount.value='';
							window.self.focus();
							clearEmployee("EmpSearch");
							document.miscRecover.reset();
							if(res){
							
										var url = "./hrms.htm?actionFlag=fillCombo&empId=0&empAllRec=false&newEntryEmpId="+empId;
										document.miscRecover.action=url;
										document.miscRecover.submit();
											retValue=true;
							       		 return true; 
										}
										else
										{
											retValue=false;
							        		return false; 
										}
							
							
							
							
						}
						
						/*if(eisEmpNO=='y' || eisEmpNO=='Y'){
							alert("The Misclaneous Recovery for "+document.getElementById("Employee_Name_EmpSearch").value+" is entered into the system for the month "+document.getElementById("date").value);
							retValue=false;
							
						}*/
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
	return retValue;
}




function beforeSubmit()
{
				
		/*cmpDate();*/
		
		//if(chkFunc()==true)
		{
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.miscRecover.empName.value=empName;
		
		document.miscRecover.action="./hrms.htm?actionFlag=insertMiscData&edit=N";
		document.miscRecover.submit();
		}
		/*else
		{
		document.miscRecover.action = 'javascript:beforeSubmit()';
		}*/
}

function chkDateCompare()
{
  
    var sysdate= new Date();
   
    if(document.getElementById("date").value!='' )
    { 
    	
    	var dateday=sysdate.getDate();
    	var datemonth = sysdate.getMonth()+1;
    	var dateYear= sysdate.getFullYear();
    	
    	if(datemonth<10)
    	{
    		datemonth="0"+datemonth;
    	}
    	
    	var dateString = dateday + "/" + datemonth+ "/" +dateYear;
    	
    var diff = compareDate(document.getElementById("date").value,dateString);
    if(diff<0)
    {
    	alert("Recovery Date must be greater than or equal to Current Date");
    	document.getElementById("date").value='';    
    	document.getElementById("date").focus();	
    	return false;
    }
    }
    return;
}

function validateForm()
{
if(chkFunc()==true)
return true
else
return false

}

function calculateAmt()
{
	var miscInst = document.miscRecover.installment.value;
	if(miscInst!=null && miscInst!='')
	{
		if(miscInst==0)
		{
			alert('Installment No must be greater than Zero');
			document.miscRecover.installment.value='';
			document.miscRecover.installment.focus();
			return false;
		}
		var totAmt=document.miscRecover.amount.value;
		var installmentNo = document.miscRecover.installment.value;
		var installmentAmount = totAmt/installmentNo;
		document.miscRecover.installmentAmt.value=installmentAmount;
	}
}

function cmpDate()
{
	 var diff = compareDate(document.miscRecover.date.value,document.miscRecover.endDate.value);   

	 if(document.miscRecover.endDate.value!=null && document.miscRecover.endDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.miscRecover.date.value,document.miscRecover.endDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("End Date must be greater than Start Date");
	   		document.miscRecover.endDate.value='';
   			return false;
  	 	}
	  	
  	 }

}

function chkRecoveredAmt()
{
	var totAmt = document.miscRecover.amount.value;
	var recvAmt = document.miscRecover.recoveredAmt.value;
	
	if(recvAmt!=null && recvAmt!='')
	{
		if(eval(recvAmt) > eval(totAmt))
		{
			alert('Recovered Amount must not greater than Total Amount');
			document.miscRecover.recoveredAmt.value='';
			return false;
		}
	}
}

function chkRecoveredInst()
{
	var totInst = document.miscRecover.installment.value;
	var recvInst = document.miscRecover.recoveredInst.value;
	
	if(recvInst!=null && recvInst!='')
	{
		if(eval(recvInst) > eval(totInst))
		{
			alert('Recovered Installment must not greater than Total Installment');
			document.miscRecover.recoveredInst.value='';
			return false;
		}
	}
}

function chkInstallmentAmt()
{
    alert("instalment");
	if(chkFunc()==true)
	{
		var totAmt = document.miscRecover.amount.value;
	    var instAmt = document.miscRecover.installmentAmt.value;
	    if(eval(instAmt) > eval(totAmt))
	    {
	    	alert('Installment amount must not greater than Total Amount');
	    	document.miscRecover.installmentAmt.value='';
	    	return false;
	    }
	    else
	    	return true;
	}
}
