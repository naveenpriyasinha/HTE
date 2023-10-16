<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>
	
	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
	
	

	<c:set var="resultObj" value="${result}" > </c:set>
	
    <c:set  var="headresultSet" value='${resultObj.resultValue.headresultSet}' /> 
    <c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>
    <c:set var="result" value="${resultObj.resultValue.result}" ></c:set>
    <c:set var="deptList" value="${resultObj.resultValue.deptList}" ></c:set>
    <c:set var="classList" value="${resultObj.resultValue.classList}" ></c:set>
  	<c:set var="dsgnList" value="${resultObj.resultValue.dsgnList}" ></c:set>
  	<c:set var="lstPostType" value="${resultObj.resultValue.lstPostType}" ></c:set>
  	<c:set var="schemeList" value="${resultObj.resultValue.schemeList}" />
  
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

${resValue.msg}


<script type="text/javascript" language="JavaScript"><!--

function RemoveDsgnfromLocation(){




// Added By Varun. To Remove the Destination Class List.
		  v=document.getElementById("srcDsgnId").length;
		
		  for(i=0;i<v;i++)
		  {
			  lgth = document.getElementById("srcDsgnId").options.length - 1;
			 
			  document.getElementById("srcDsgnId").options[lgth] = null;
		  }
		  
		  
		   r=document.getElementById("dsgnId").length;
		
		
		  for(i=0;i<r;i++)
		  {
			  Rlgth = document.getElementById("dsgnId").options.length - 1;
			 
			  document.getElementById("dsgnId").options[Rlgth] = null;
		  }
		  
}	

function AllDesignation(){


var destObj1 = document.getElementById('srcDsgnId');
	var srcObj1 = document.getElementById('srcDsgnId1');
	var srcClassLgth1 = document.getElementById('srcDsgnId1').length;

	var destClassLgth1 = document.getElementById('srcDsgnId').length;
	for(i=destClassLgth1-1;i>=0;i--) {
		if(destObj1.options[i].value=='')
			destObj1.options[i]=null;
	}
	for(i=0;i<srcClassLgth1;i++) {
		var optDest1 = document.createElement('option');
	    optDest1.value=srcObj1.options[i].value;
	    optDest1.text=srcObj1.options[i].text;
	    
	    try {      				    					
               destObj1.add(optDest1,null);
          	}
		catch(ex) {
 			destObj1.add(optDest1); 
 		}
	}
	/*for(i=srcClassLgth1-1;i>=0;i--) {
		srcObj1.options[i]=null;
	} */


}
	  


function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
var chkflag=1;
function chkbillHeadunique()
{
	var bill=document.billHeadMaster.bill.value;
	var location_Id=document.billHeadMaster.cmbDept.value;
	
	
	if(bill!=""&&location_Id!="")
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
	var mpgId=0;
	
	
    var url = "hrms.htm?actionFlag=chkbillHeadunique&bill="+bill+"&location_Id="+location_Id+"&bhMapId="+mpgId;  
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var mpgflag = XMLDocForAjax.getElementsByTagName('bh-mapping');	
				
				if(mpgflag.length != 0)
				{
					if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text!='-1')
					{			
						alert("Mapping already Exists");
						document.billHeadMaster.bill.value = '';
						document.billHeadMaster.bill.focus();
						chkflag=0;
					}
					else
					chkflag=1;
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	
	
	
	
	
//Added By Varun for populating Designations on the basis of Grades selected
function GetDsgnfromLoc1234()
{



	/*var h=document.getElementById("srcDsgnId").length;
		
		  for(l=0;l<h;l++)
		  {
			  Lgth = document.getElementById("srcDsgnId").options.length -1;
			  document.getElementById("srcDsgnId").options[Lgth] = null;
		  }	
		  // Added By Urvin. To Remove the Destincation Post List.
		  h=document.getElementById("dsgnId").length;
		
		  for(l=0;l<h;l++)
		  {
			  Lgth = document.getElementById("dsgnId").options.length -1;
			  document.getElementById("dsgnId").options[Lgth] = null;
		  }	
		  
		  	
		  xmlHttp1=GetXmlHttpObject();
		  if (xmlHttp1==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url1=''; 
		  var uri1='';
		  url1= uri1+'&locId='+ document.billHeadMaster.cmbDept.value;
		  var actionf="GetDsgnfromLocation";
		  uri1='./hrms.htm?actionFlag='+actionf;
		  url1=uri1+"&editFlag=0"; 
		 
		  xmlHttp1.onreadystatechange=GetDesgsfromLocation1;
		  xmlHttp1.open("POST",encodeURI(url1),true);
		  xmlHttp1.send(null);	*/
}
	
	
}
function validateForm()
{	

var frmName = document.getElementsByTagName("form")[0];

	var listBox = frmName.dsgn;
	var listBox1 = frmName.gradeId;
	
	var len = listBox.length;
	var len1 = listBox1.length;

  
			if(len==0)
			{
			alert('Please Select Designation');
				chkflag=0;
				return;
			}
			else{
			    
				for(i=0;i<listBox.options.length;i++){
				
				if(listBox.options[i].value=='' || listBox.options[i].value==null){
				alert('Please Select Designation');
				chkflag=0;
				return;}
				else
				{
				chkflag=1;
				}
				}
			}


	if(chkflag==0)
	return false;
	else
	return true;
}


function beforeSubmit()
{

	var frmName = document.getElementsByTagName("form")[0];

	var listBox = frmName.dsgn;
	var listBox1 = frmName.gradeId;

	var len = listBox.length;
	var len1 = listBox1.length;

	document.billHeadMaster.dsgnLenVal.value=len;
	document.billHeadMaster.classLenVal.value=len1;
	
    var v=document.getElementById("dsgnId");
	for(i=0;i<v.options.length;i++)
	{
      v.options[i].selected = true;
	}	
	
 	var f=document.getElementById("classId");
	for(i=0;i<f.options.length;i++)
	{
      f.options[i].selected = true;
	}

	document.billHeadMaster.action="./hdiits.htm?actionFlag=AddBillHeadData&updateflag=N";
	document.billHeadMaster.submit();
}	

//Added by Varun
function GetDsgnfromGrade(srcObjValue)
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
	var actionf="getDesignationsFromGradeArray";
	uri='./hrms.htm?actionFlag='+actionf+'&gradeId='+srcObjValue;
	
	
	xmlHttp.onreadystatechange = function()
	{
		
		try{	
		
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{
				var cmbDesignations = document.getElementById("srcDsgnId");
				
	//			alert('Entries in Designation Combos are ' + cmbDesignations.length);
				var XMLDoc=xmlHttp.responseXML.documentElement;
				
				if(XMLDoc==null)
                {
                  window.status = 'No Records Found.';
                }
                else
                {
                	var desigEntries = XMLDoc.getElementsByTagName('designation');
                	var srcdsgnBox = document.getElementById("srcDsgnId").length;
                	var destBoxObj = document.getElementById("srcDsgnId");
                	
                
                	for( var i= srcdsgnBox-1 ;i>=0 ; i-- ) {
                					
							if(destBoxObj.options[i].value=='')
								destBoxObj.options[i]=null;
						}
	
      //          	alert('No of Designations for Id gradeId ' + desigEntries.length);
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

//Added By Varun Remove Designation As Per Grade


//Added by Varun
function RemoveDsgnfromGrade(destObjValue , restgradeId)
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
	var actionf="getDesignationsFromGradeArray";
	uri='./hrms.htm?actionFlag='+actionf+'&gradeId='+destObjValue+'&edit=Y&restGradeId='+restgradeId;
	
	
	xmlHttp.onreadystatechange = function()
	{
		
		try{	
		
		if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200) 
			{
				var cmbDesignations = document.getElementById("restDsgnId");
				
	//			alert('Entries in Designation Combos are ' + cmbDesignations.length);
				var XMLDoc=xmlHttp.responseXML.documentElement;
				
				if(XMLDoc==null)
                {
                  window.status = 'No Records Found.';
                }
                else
                {
                	var desigEntries = XMLDoc.getElementsByTagName('designation');
                	var srcdsgnBox = document.getElementById("restDsgnId").length;
                	var destBoxObj = document.getElementById("restDsgnId");
                	
                
                	for( var i= srcdsgnBox-1 ;i>=0 ; i-- ) {
                					
							if(destBoxObj.options[i].value=='')
								destBoxObj.options[i]=null;
						}
	
      //          	alert('No of Designations for Id gradeId ' + desigEntries.length);
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



//Ended By Varun
function GetDesgsfromLocation1()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var dsgn = document.getElementById("srcDsgnId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('dsgn-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text =entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			       
     			        y.text=text;	
     			        try
   				        {      				    					
                            dsgn.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       dsgn.add(y); 
   			   	        }	
	                
	                 }
  }
}


<%-- Added By Varun For Class --%>


function moveFromSrcToDest1(){

	var destObj = document.getElementById('classId');
	var srcObj = document.getElementById('srcClassId');
	var srcClassLgth = document.getElementById('srcClassId').length;
	var destClassLgth = document.getElementById('classId').length;
	//alert(document.getElementById('classId').length);
	//alert(destObj.value);
	
	
	var srcObjValue='';
	var tempSrcLgth=srcClassLgth-1;


	for(i=destClassLgth-1;i>=0;i--) {
		if(destObj.options[i].value=='')
			destObj.options[i]=null;
	}
	var x=0;
	for(i=srcClassLgth-1;i>=0;i--) {
	
	
			if(srcObj.options[i].selected) {
            x++;

			var optDest = document.createElement('option');
			var gradeId= srcObj.options[i].value;


		if(x==1)
		{
		srcObjValue +=  srcObj.options[i].value;

		}
		else
		{
		srcObjValue += ' , ' + srcObj.options[i].value;		
		}
		
		
			optDest.value=srcObj.options[i].value;
		    optDest.text=srcObj.options[i].text;
		  //alert('text and value is ' + srcObj.options[i].value + ' ' + srcObj.options[i].text);

		    try {		    					
                	destObj.add(optDest,null);
           	    }
 			catch(ex) {
   			 	 	destObj.add(optDest); 
   	             	  }
   					srcObj.options[i]=null;
		        }
		
			
			
				}
				
										
	GetDsgnfromGrade(srcObjValue);
	
}

// Function:- This method add all items from Source Post List and add them in to Destination List and remove all items from the Source Post List.

function moveAllFromSrcToDest1(){
moveAllFromDestToSrc1();

	var destObj = document.getElementById('classId');
	var srcObj = document.getElementById('srcClassId');
	var srcClassLgth = document.getElementById('srcClassId').length;
	var destClassLgth = document.getElementById('classId').length;
	var gradeValue='';
	for(i=destClassLgth-1;i>=0;i--) {
		//if(destObj.options[i].value=='')
			destObj.options[i]=null;
	}
	for(i=0;i<srcClassLgth;i++) {
		var optDest = document.createElement('option');
	    optDest.value=srcObj.options[i].value;
	    optDest.text=srcObj.options[i].text;
	    if(i==0  || i==srcClassLgth-1)
	    {
	  
	    gradeValue +=srcObj.options[i].value; 
	    }
	    
	    else
	    
	    {
	   
				    gradeValue += ' , ' + srcObj.options[i].value;	    
	    }
	    
	    try {      				    					
               destObj.add(optDest,null);
          	}
		catch(ex) {
 			destObj.add(optDest); 
 		}
	}
	
	
	for(i=srcClassLgth-1;i>=0;i--) {
		srcObj.options[i]=null;
	}
GetDsgnfromGrade(gradeValue);
}

// Function:- This method remove selected items from Destination Post List and add to Source List and add to the Source Post List.

function moveFromDestToSrc1(){


	var destObj = document.getElementById('classId');
	var srcObj = document.getElementById('srcClassId');
	var srcClassLgth = document.getElementById('srcClassId').length;
	var destClassLgth = document.getElementById('classId').length;
	var destObjValue='';
	var tempDestLgth=destClassLgth-1;
		var x=0;
	for(i=destClassLgth-1;i>=0;i--) {
	
			if(destObj.options[i].selected) {
		x++;
		
					if(x==1)
			{
			destObjValue += destObj[i].value ;
			}
			else
			{
			destObjValue += ',' + destObj[i].value ;
			}
		
			var optSrc = document.createElement('option');
		    optSrc.value=destObj.options[i].value;
		    optSrc.text=destObj.options[i].text;
		    try {      				    					
                srcObj.add(optSrc,null);
           	}
 			catch(ex) {
   			 	 srcObj.add(optSrc); 
   			}
   			destObj.options[i]=null;
		}
		
	}
	
	var restgradeId = '';
	var y=0;
	for(i=document.getElementById('classId').length-1;i>=0;i--) {
	
	y++;
	if(y==1)
	{

	restgradeId += destObj.options[i].value ;
	}
	else
	{

	restgradeId += ',' + destObj.options[i].value ;
	}
	
	}

	if(restgradeId == ''  )
	{
	
	RemoveDsgnfromLocation();
	}
	else
	{
	
	 RemoveDsgnfromGrade(destObjValue ,restgradeId )
	
	}
	

	 v=document.getElementById("srcDsgnId").length;
		
		  for(i=0;i<v;i++)
		  {
			  lgth = document.getElementById("srcDsgnId").options.length - 1;
			 
			  document.getElementById("srcDsgnId").options[lgth] = null;
		  }
		  
		  
		   r=document.getElementById("dsgnId").length;
		
		
		  for(i=0;i<r;i++)
		  {
			  Rlgth = document.getElementById("dsgnId").options.length - 1;
			 
			  document.getElementById("dsgnId").options[Rlgth] = null;
		  }
		  
	
	

	var destDsgnObj = document.getElementById('srcDsgnId');
	var srcDsgnObj = document.getElementById('restDsgnId');
	var destDsgnLgth = document.getElementById('srcDsgnId').length;
	var srcDsgnLgth = document.getElementById('restDsgnId').length;


	
	for(i=0;i<srcDsgnLgth;i++) {
	
	
		var optSrc1 = document.createElement('option');
	    optSrc1.value=srcDsgnObj.options[i].value;
	    optSrc1.text=srcDsgnObj.options[i].text;
	 try {      				    					
                destDsgnObj.add(optSrc1,null);
           	}
 			catch(ex) {
   			 	 destDsgnObj.add(optSrc1); 
   			}
   		
	
}




 w=document.getElementById("restDsgnId").length;
		
		  for(i=0;i<w;i++)
		  {
			  lgth = document.getElementById("restDsgnId").options.length - 1;
			 
			  document.getElementById("restDsgnId").options[lgth] = null;
		  }
		  
		  
		  
}

// Function:- This method remove all items from Destination Post List and add them in to the Source Post List.

function moveAllFromDestToSrc1(){
	var destObj = document.getElementById('classId');
	var srcObj = document.getElementById('srcClassId');
	var srcClassLgth = document.getElementById('srcClassId').length;
	var destClassLgth = document.getElementById('classId').length;
	for(i=0;i<destClassLgth;i++) {
		var optSrc = document.createElement('option');
	    optSrc.value=destObj.options[i].value;
	    optSrc.text=destObj.options[i].text;
	    try {      				    					
               srcObj.add(optSrc,null);
          	}
		catch(ex) {
 			srcObj.add(optSrc); 
 		}
	}
	for(i=destClassLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
	RemoveDsgnfromLocation();
}


<%-- Ended By Varun For Class --%>



<%-- Added By Varun For Designation --%>

function moveFromSrcToDest(){
	var destObj = document.getElementById('dsgnId');
	var srcObj = document.getElementById('srcDsgnId');
	var srcDsgnLgth = document.getElementById('srcDsgnId').length;
	var destDsgnLgth = document.getElementById('dsgnId').length;
	
	for(i=destDsgnLgth-1;i>=0;i--) {
		if(destObj.options[i].value=='')
			destObj.options[i]=null;
	}
	
	
	for(i=srcDsgnLgth-1;i>=0;i--) {
		if(srcObj.options[i].selected) {
			var optDest = document.createElement('option');
		    optDest.value=srcObj.options[i].value;
		   
		    optDest.text=srcObj.options[i].text;
		    //alert('text and value is ' + srcObj.options[i].value + ' ' + srcObj.options[i].text);
		 
		    try {      				    					
                destObj.add(optDest,null);
                
           	}
 			catch(ex) {
   			 	 destObj.add(optDest); 
   			}
   			srcObj.options[i]=null;
		}	
	}
}

// Function:- This method add all items from Source Post List and add them in to Destination List and remove all items from the Source Post List.

function moveAllFromSrcToDest(){
	var destObj = document.getElementById('dsgnId');
	var srcObj = document.getElementById('srcDsgnId');
	var srcDsgnLgth = document.getElementById('srcDsgnId').length;
	var destDsgnLgth = document.getElementById('dsgnId').length;
	
	
	
	
	for(i=destDsgnLgth-1;i>=0;i--) {
		if(destObj.options[i].value=='')
			destObj.options[i]=null;
	}
	
	for(i=0;i<srcDsgnLgth;i++) {
		var optDest = document.createElement('option');
	    optDest.value=srcObj.options[i].value;
	    optDest.text=srcObj.options[i].text;
	    
	    try {      				    					
               destObj.add(optDest,null);
          	}
		catch(ex) {
 			destObj.add(optDest); 
 		}
	}
	for(i=srcDsgnLgth-1;i>=0;i--) {
		srcObj.options[i]=null;
	}
}

// Function:- This method remove selected items from Destination Post List and add to Source List and add to the Source Post List.

function moveFromDestToSrc(){
	var destObj = document.getElementById('dsgnId');
	var srcObj = document.getElementById('srcDsgnId');
	var srcDsgnLgth = document.getElementById('srcDsgnId').length;
	var destDsgnLgth = document.getElementById('dsgnId').length;
	for(i=destDsgnLgth-1;i>=0;i--) {
		if(destObj.options[i].selected) {
			var optSrc = document.createElement('option');
		    optSrc.value=destObj.options[i].value;
		    optSrc.text=destObj.options[i].text;
		    try {      				    					
                srcObj.add(optSrc,null);
           	}
 			catch(ex) {
   			 	 srcObj.add(optSrc); 
   			}
   			destObj.options[i]=null;
		}	
	}
}

// Function:- This method remove all items from Destination Post List and add them in to the Source Post List.

function moveAllFromDestToSrc(){
	var destObj = document.getElementById('dsgnId');
	var srcObj = document.getElementById('srcDsgnId');
	var srcDsgnLgth = document.getElementById('srcDsgnId').length;
	var destDsgnLgth = document.getElementById('dsgnId').length;
	for(i=0;i<destDsgnLgth;i++) {
		var optSrc = document.createElement('option');
	    optSrc.value=destObj.options[i].value;
	    optSrc.text=destObj.options[i].text;
	    try {      				    					
               srcObj.add(optSrc,null);
          	}
		catch(ex) {
 			srcObj.add(optSrc); 
 		}
	}
	for(i=destDsgnLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
}
<%-- Ended By Varun For Designation --%>

<%-- added by japen --%>

function schemeAjax()
{ 
	 
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  
		  //var uri='';
		 // url= uri+'&scheme='+ document.forms[0].scheme.value;
		 // alert('scheme ' + document.forms[0].scheme.value);	
		 // var actionf="getHeadStructureFromScheme";
		 // uri='./hrms.htm?actionFlag='+actionf+'&scheme='+document.forms[0].scheme.value;
		 // alert("uri**************"+uri);

		  var url; 
		  var uri='';
		  url= uri+'&HeadStructure='+ document.forms[0].scheme.value;
		  //alert('scheme ' + document.forms[0].scheme.value);	
		  var actionf="getHeadStructureFromScheme"
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 		

		  //alert(url);
			  
		 
          		  		  
			xmlHttp.onreadystatechange=fillAllHeadStructure;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
  }



function fillAllHeadStructure()
{

	if (xmlHttp.readyState==complete_state)
 	{ 						
	 	var XMLDoc=xmlHttp.responseXML.documentElement;
		//alert('xml doc ' + XMLDoc);			
        var namesEntries = XMLDoc.getElementsByTagName('HeadStructure');
        // var child = nameEntries[0].childNode[0].text;   
                   
					//<HeadStructure><billHeadId>035</billHeadId><billId>7610</billId><billId1>00</billId1><billId2>800</billId2><billId3>01</billId3></HeadStructure>
	                  
                    //for(var k = 0; k < namesEntries.length;k++)
					//{
	                 
	                  //for(var j =0;j<namesEntries[k].childNodes.length;j++)
	                //  {
						//	alert(namesEntries[k].childNodes[j].text);
		                 // }
		                 
		                 var demand =namesEntries[0].childNodes[0].text;
		                 var mjrHd = namesEntries[0].childNodes[1].text;
		                 var subMjr = namesEntries[0].childNodes[2].text;
		                 var minor = namesEntries[0].childNodes[3].text;
		                 var sub = namesEntries[0].childNodes[4].text;
		                 
		  document.getElementById('cmbDemand').value = demand;   
		  document.getElementById('cmbMjrHead').value = mjrHd;
		  document.getElementById('cmbSubMjrHead').value = subMjr;
		  document.getElementById('cmbMnrHead').value = minor;
		  document.getElementById('cmbSubHead').value = sub;
		  document.getElementById('head').value =   namesEntries[0].childNodes[5].text;

		  document.getElementById('headChargable').value = demand + mjrHd + subMjr + minor + sub;
     }            
  
}


--></script>
<hdiits:form name="billHeadMaster" validate="true" method="POST" action="javascript:beforeSubmit()" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BHM.billHeadMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br/><br/>
	<TABLE align="center" width="80%">  
	<TR>
		<TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
    	<TD	width ="25%"><hdiits:select style="width:90%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true"  >
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			<c:forEach items="${deptList}" var="deptList">
	    		   <hdiits:option value="${deptList.locationCode}"> ${deptList.locName} </hdiits:option>
	    		</c:forEach>
	   	</hdiits:select>
	   </TD>
	
		
		  </tr>
		  <tr></tr><tr></tr><tr></tr><tr></tr>
		  
		  <tr>
	<TD align="left" width = "15%">
		  <b><fmt:message key="BHM.scheme" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="25%">
	 <hdiits:select  style="width:90%" name="scheme" id="scheme" size="1" mandatory="true" caption="Demand No" captionid="DmdNo"
		validation="sel.isrequired" sort="false" onchange="schemeAjax();" >
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	   <c:forEach items="${schemeList}" var="scheme">
	    		   <hdiits:option value="${scheme}"> ${scheme} </hdiits:option>
	    		</c:forEach>
	 </hdiits:select>
	 </td>
	<td>
	
		 <b><fmt:message key="BHM.billNo" bundle="${commonLables}"/></b></TD>
	<td><hdiits:number mandatory="true" id = "billNumber" name="bill" caption="Bill Number" validation="txt.isrequired"  size="1"	onblur="chkbillHeadunique();" maxlength="3" /></td>
	
	 
	
	</TR>
	<tr></tr><tr></tr><tr></tr><tr></tr>

<tr>		
			
	<TD  align="left" class="Label" width="15%">
			<b><fmt:message key="PR.DEMAND" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="25%">
	 <hdiits:text  name="cmbDemand" id="cmbDemand" caption="Demand No" captionid="DmdNo" readonly="true"  default=""></hdiits:text>
	 
	 </td>
	
	
	<TD align="left" width="15%"> 
		<b><fmt:message key="PR.MJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD width ="25%">
	
	<hdiits:text  name="cmbMjrHead" id="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo"   readonly="true"  default=""></hdiits:text>
	
	
	</TD>
	</tr>
<tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
	<TD align="left" class="Label" width="15%"> 
		<b><fmt:message key="PR.SUBMJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD align="left" width ="25%">

	  
	  <hdiits:text  name="cmbSubMjrHead" id="cmbSubMjrHead" caption="Sub Major Head" captionid="subMjrHeadNo"   readonly="true"  default=""></hdiits:text>
	  
	  </TD>
	
	
	<TD align="left" align="right" width ="15%"> 
		<b><fmt:message key="PR.MNRHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="25%">
	 
	 <hdiits:text  name="cmbMnrHead" id="cmbMnrHead"  caption="Minor Head" captionid="MnrHeadNo"   readonly="true"  default=""></hdiits:text>
	 </td>
	</tr>
	<tr>
	<td><input type ="hidden" name="head"></td>
	</tr><tr></tr><tr></tr><tr></tr>
	<tr>
	<TD align="left" width ="15%" >
		 <b><fmt:message key="PR.SUBHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="30%">
	 
	  <hdiits:text  name="head" id="cmbSubHead"  caption="Sub Head" captionid="OM.headName"   readonly="true"  default=""></hdiits:text>
	 </td>
	
	
	<TD align="left" width ="15%"> 
		<b><fmt:message key="PR.DTLHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="30%">
	 <hdiits:text  name="cmbDtlHead" id="cmbDtlHead"  caption="Sub Head" captionid="OM.headName"   readonly="true"  default=""></hdiits:text>
	 </td>
	</tr> 
	 
	 <%--Added By Varun For Class--%>
	 
	  <TR>
		<TD width = "5%" style="display:none">
		<b>   <fmt:message key="BHM.class" bundle="${commonLables}" 	/></b>
		</TD>			
		
		<TD width ="10%">
			<hdiits:select    style="display:none" captionid="BHM.class" bundle="${commonLables}"  id="srcDsgnId1" name="srcDsgn1"  multiple="true" size ="5">
					<c:forEach var="dsgnList"  items="${dsgnList}"> 
					 	
						<option value="${dsgnList.dsgnId}" >
							${dsgnList.dsgnName}
							</option>

					</c:forEach>
			</hdiits:select>

				
			</TD>
			
			
			
		<TD width = "5%" style="display:none">
		<b>   <fmt:message key="BHM.class" bundle="${commonLables}" 	/></b>
		</TD>			
		
		<TD width ="10%">
			<hdiits:select   style="display:none"  captionid="BHM.class" bundle="${commonLables}"  id="restDsgnId" name="restDsgn"  multiple="true" size ="5">
				
			</hdiits:select>

				
			</TD>
	 
	 
	 
	 <TR>
		<TD width = "5%">
		<b>   <fmt:message key="BHM.class" bundle="${commonLables}" 	/></b>
		</TD>			
		
		<TD width ="10%">
			<hdiits:select style="width:75%" captionid="BHM.class" bundle="${commonLables}"  id="srcClassId" name="srcClass"  multiple="true" size ="5">
					<c:forEach var="classList"  items="${classList}"> 
					 	
						<option value="${classList.gradeId}" >
							${classList.gradeName}
							</option>

					</c:forEach>
			</hdiits:select>

				
			</TD>

			<td width="1%">
			<table >
			<tr>
				<td>
					<hdiits:button name="addFromSrvToDest1" type="button" captionid="eis.addFromSrcToDest" bundle="${commonLables}" onclick="moveFromSrcToDest1()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromSrcToDest1" type="button" captionid="eis.addMultiFromSrcToDest" bundle="${commonLables}" onclick="moveAllFromSrcToDest1()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addFromDestToSrc1" type="button" captionid="eis.addFromDestToSrc" bundle="${commonLables}" onclick="moveFromDestToSrc1()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromDestToSrc1" type="button" captionid="eis.addMultiFromDestToSrc" bundle="${commonLables}" onclick="moveAllFromDestToSrc1()"/>
				</td>	
			</tr>
			</table>
			</td>
			
			<TD width = "30%">

			<hdiits:select style="width:75%" captionid="BHM.class" mandatory="true" bundle="${commonLables}" id="classId" name="gradeId" multiple="true" size ="5">
			</hdiits:select>
		  </TD>	
		  			
		 
	</tr>	
	
<tr></tr><tr></tr>

	 
	 	 <%--Added By Varun For Designation--%>
	 
	 
	
	<TR>
		<TD width = "5%">
		<b>   <fmt:message key="BHM.designation" bundle="${commonLables}" 	/></b>
		</TD>			
		
		<TD width ="10%">
			<hdiits:select style="width:75%" captionid="OHP.postname" bundle="${commonLables}" id="srcDsgnId" name="srcDsgn"  multiple="true" size ="5">
					<%--<c:forEach var="ohpList"  items="${ohpList}"> 
					 	
						<option value="${ohpList.postId}" >
						${ohpList.empFName}  ${ohpList.empMName} ${ohpList.empLName}  ${ohpList.post}
							</option>

					</c:forEach>--%>
			</hdiits:select>

				
			</TD>

			<td width="1%">
			<table >
			<tr>
				<td>
					<hdiits:button name="addFromSrvToDest" type="button" captionid="eis.addFromSrcToDest" bundle="${commonLables}" onclick="moveFromSrcToDest()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromSrcToDest" type="button" captionid="eis.addMultiFromSrcToDest" bundle="${commonLables}" onclick="moveAllFromSrcToDest()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addFromDestToSrc" type="button" captionid="eis.addFromDestToSrc" bundle="${commonLables}" onclick="moveFromDestToSrc()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromDestToSrc" type="button" captionid="eis.addMultiFromDestToSrc" bundle="${commonLables}" onclick="moveAllFromDestToSrc()"/>
				</td>	
			</tr>
			</table>
			</td>
			
			<TD width = "30%">

			<hdiits:select style="width:75%" captionid="OHP.postname" mandatory="true" bundle="${commonLables}" id="dsgnId" name="dsgn" multiple="true" size ="5">
			</hdiits:select>
		  </TD>			
		 
	</tr>	
	<tr>
	<td class="fieldLabel"><b><hdiits:caption captionid="admin.PostType" bundle="${commonLables}"/></b></td>
		<td class="fieldLabel"><hdiits:select name="postTypeCmb" id="postTypeCmb" captionid="admin.PostType" bundle="${commonLables}">
				<hdiits:option value="" > -------Select------- </hdiits:option>
					<c:forEach items="${lstPostType}" var="postType">
						<option value="<c:out value="${postType.lookupId}"/>">
						<c:out value="${postType.lookupDesc}" /></option>
				</c:forEach>
			</hdiits:select>
		</td>
		
		<td><b><hdiits:caption captionid="BHM.headChargable" bundle="${commonLables}"></hdiits:caption></b></td>
		<td><hdiits:text name="headChargable" default="" captionid="BHM.headChargable"  maxlength="30" readonly="" style="" size="30" mandatory="" validation=""/> 
	</tr>
	
	<hdiits:hidden name="Demand" id="Demand" default="document.frmPaybillPara.cmbDemand.value"/>
	<hdiits:hidden name="MjHd" id="MjHd" default="document.frmPaybillPara.cmbMjrHead.value"/>
	<hdiits:hidden name="SubMjHd" id="SubMjHd" default="document.frmPaybillPara.cmbSubMjrHead.value"/>
    <hdiits:hidden name="MnrHd" id="MnrHd" default="document.frmPaybillPara.cmbMnrHead.value"/>
  	<hdiits:hidden name="txtSbHd" id="txtSbHd" default="document.frmPaybillPara.cmbSubHead.value"/>
  	
  	
  	
  		<hdiits:hidden name="dsgnLenVal"></hdiits:hidden>
  		  		<hdiits:hidden name="classLenVal"></hdiits:hidden>
<tr></tr><tr></tr><tr></tr><tr></tr>

	
	
	<tr><td colspan="2">&nbsp;</td></tr>
	</TABLE>
	</div> 
	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>

	<hdiits:hidden default="getBillHeadData" name="givenurl"/>

	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		// Added By Varun. To Remove the Destincation Class List.
		  v=document.getElementById("classId").length;
		
		  for(i=0;i<v;i++)
		  {
			  lgth = document.getElementById("classId").options.length - 1;
			 
			  document.getElementById("classId").options[lgth] = null;
		  }	
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			
			{
				alert("${msg}");
				
				var url="./hrms.htm?actionFlag=getBillHeadData";
				
				document.billHeadMaster.action=url;
				document.billHeadMaster.submit();
			}
	
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
document.billHeadMaster.cmbDept.focus();
</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


