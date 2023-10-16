

// Added By Maruthi.

function chkDate(){
	if(document.frmOrderMaster.txtEndDate.value!=null && document.frmOrderMaster.txtEndDate.value!="")
	{
	var diff = compareDate(document.frmOrderMaster.txtStartDate.value,document.frmOrderMaster.txtEndDate.value);
	    if(diff<0){
	    	alert("End Date must be Greater then Order Date");
	    	document.frmOrderMaster.txtEndDate.value=''; 
	    	document.frmOrderMaster.txtEndDate.focus();   	
	    	return false;
	    }
	   }
	return true;    
}

// End Urvin Shah

function checkAvailability(newOrderName)
{
	var newOrderName=newOrderName.value;
	var oldOrderName="${actionList.orderName}";	

	if(newOrderName!="")
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
	//    var url = "hrms.htm?actionFlag=getDesigData&editdesig=${desg_name}&&chk=1&dname="+dname;  
   			if(newOrderName==oldOrderName) {	
				return true;
			}
			else{
				//alert("Not eqal");
				var url = "hrms.htm?actionFlag=checkOrderNameAvailability&newOrderName="+newOrderName;  	
			}
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
							document.frmBF.orderName.value='';
							document.frmBF.orderName.focus();
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

function chkOrderName()
{ 
  if(!trim(document.frmOrderMaster.orderName.value)== '')
  {
  if(!checkSplChars(document.frmOrderMaster.orderName.value)/* || (checkAlpha(document.frmBankMaster.bankName.value)==false)*/)
  {
   alert('Order Name Contains Invalid Characters. Try Again.');
   document.frmOrderMaster.orderName.value = '';
   document.frmOrderMaster.orderName.focus();
  }
  else
  {
  var name =document.frmOrderMaster.orderName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  if(${actionList.orderName eq 'null'} || ${actionList.orderName eq ''})
  		   url= uri+'&orderName='+ document.frmOrderMaster.orderName.value;
  		  else		  
		   url= uri+ '&oldname=${actionList.orderName}&orderName=' + document.frmOrderMaster.orderName.value;
		  var actionf="chkOrderName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
 //        alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_orderName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
  }
}
}

function chk_orderName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('order-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
                    {                    
                     alert('Order Name already exists.');
                     document.frmOrderMaster.orderName.value = '';
                     document.frmOrderMaster.orderName.focus();
                    }
  }
}

function validateForm()
{
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.frmOrderMaster.txtAction.value;

 document.frmOrderMaster.action = url;
 document.frmOrderMaster.submit();
}

