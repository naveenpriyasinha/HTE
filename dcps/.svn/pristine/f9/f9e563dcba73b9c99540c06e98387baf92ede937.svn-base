

function validateForm()
{
 document.frmOrderMaster.action ="./hrms.htm?actionFlag=AddOrderData&edit=N";
 document.frmOrderMaster.submit();
}

// Start By Urvin Shah.

function chkDate(){
	
	if(document.frmOrderMaster.txtEndDate.value!=null && document.frmOrderMaster.txtEndDate.value!="")
	{	
		var diff = compareDate(document.frmOrderMaster.txtStartDate.value,document.frmOrderMaster.txtEndDate.value);
	    if(diff<0){
	    	alert("End Date must be Greater then Start Date");
	    	document.frmOrderMaster.txtEndDate.value=''; 
	    	document.frmOrderMaster.txtEndDate.focus();   	
	    	return false;
	    }
	}
	return true;    
}

// End By Urvin Shah.
function checkAvailability()
{

	var newOrderName=trim(document.frmOrderMaster.orderName.value);
	var dept=document.frmOrderMaster.cmbDept.value;
	var orderDate=document.frmOrderMaster.txtStartDate.value;
	
	if(newOrderName!=""&&dept!=""&&orderDate!="")
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
		var url = "hrms.htm?actionFlag=checkOrderNameAvailability&newOrderName="+newOrderName+"&dept="+dept+"&date="+orderDate;  	


    xmlHttp.onreadystatechange = function() {	

		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var orderNameMapping = XMLDocForAjax.getElementsByTagName('orderNameMapping');	

				var flag="true";				
				if(orderNameMapping.length != 0) {		

						if(orderNameMapping[0].childNodes[0].text==flag)
						{			
							alert("Order Name is already Exists, Please Enter other Name");
							document.frmOrderMaster.orderName.value='';
							document.frmOrderMaster.orderName.focus();
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
	
 function addOrderData()
 {
 	var displayFieldArray = new Array('cmbDept','orderName','txtStartDate','txtEndDate');
	addOrUpdateRecord('addRecordInTable', 'multipleAddOrderData', displayFieldArray );		
 }
 
 function addRecordInTable()
 {
 		if(document.frmOrderMaster.cmbDept.value=="-1")
 		{
 			alert('Please Select Department');
 			document.frmOrderMaster.cmbDept.focus();
 			return false;
 		}
 		else if(document.frmOrderMaster.orderName.value==null || document.frmOrderMaster.orderName.value=="")
 		{
 			alert('Please Enter Order No');
 			document.frmOrderMaster.orderName.focus();
 			return false;
 		}
 		else if(document.frmOrderMaster.txtStartDate.value==null || document.frmOrderMaster.txtStartDate.value=="")
 		{
 			alert('Please Enter Start Date');
 			document.frmOrderMaster.txtStartDate.focus();
 			return false;
 		}
 		else{
 		if (xmlHttp.readyState == 4)
	  	{ 	
	  	  	var displayFieldArray;
		  	displayFieldArray = new Array('cmbDept','orderName','txtStartDate','txtEndDate');
		  	//var attch_chk = mandatory_Attachment('CommAttachment','Please Add Attachment');
		  	//if(attch_chk){
		  		var rowNum = addDataInTableAttachment('orderDataTable', 'encXML', displayFieldArray, '','deleteRecord');				
		   		
		   		resetFormData(rowNum);	
		   //}
		    
		}
		}	
 }
 
 function resetFormData(rowNum)
 {
 	document.forms[0].cmbDept.value="-1"; 
    document.forms[0].orderName.value=""; 
    document.forms[0].txtStartDate.value=""; 
    document.forms[0].txtEndDate.value="";
    removeRowFromTableorderId(rowNum); 
    
 }
 
 function chkDataInTable()
 {
 	var tableLength = document.getElementById('orderDataTable').rows.length;
 	if(tableLength > 1)
 		return true;
 	else 
 	{
 		alert('Please add record');
 		return false;
 	}
 }
