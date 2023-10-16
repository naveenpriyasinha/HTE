<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>	
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<!-- resource Bundle  -->
<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.ess.UserPost" var="userPostMapping" scope="request" />
<!-- resource Bundle  -->

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="salRevMstVOList" value="${resValue.salRevMstVOList}" ></c:set>
<c:set var="billNoList" value="${resValue.billNoList}"></c:set>

<c:set var="arDesignationVO" value="${resValue.arDesignationVO}"></c:set>
<c:set var="rltBillList" value="${resValue.rltBillList}" ></c:set>
<c:set var="lOrderId" value="${resValue.lOrderId}" ></c:set>
<c:set var="flag" value="${resValue.flag}" ></c:set>
<c:set var="compoValueList" value="${resValue.compoValueList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />
 
<script type="text/javascript"><!--
var crtExcelFlag="false";
var searchFlag="N";
var updExcelFlag="N";

function getSalRevOrderDetails(obj,flag)
{	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		  alert ("Your browser does not support AJAX!");
		  return;
	} 
	var url; 
	var uri='';
	var orderId ;
	if(flag!='99')
		orderId=document.forms[0].elements('salRevOrder').value;
	else
	{
		orderId=obj;
		document.forms[0].elements('salRevOrder').value=orderId;
	}
	
	url= '&lOrderId=' + orderId;
	
	var actionf="getSalRevOrderDetails";
	uri='./hrms.htm?actionFlag='+actionf;
	url=uri+url;	
	xmlHttp.onreadystatechange=getSalRevDetails;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}
function getSalRevDetails()
{	
	var salRevOrderCtrl = document.forms[0].elements('salRevOrder');	
	if(salRevOrderCtrl.value==0)
	{
		document.forms[0].elements('effFromDate').value='';
		document.forms[0].elements('effToDate').value='';
		document.forms[0].elements('revReason').value='';
		document.forms[0].elements('compoType').value='0';
	}
	//
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var salRevDetails = XMLDoc.getElementsByTagName('salRevDetails');
	   if(salRevDetails.length == 0)
	   {
	     window.status = 'No Records Found.';	     
	   }
	   else
	   {		    
		    window.status='';
			
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('salRevDetails');
		    len = entries.length;		  
		    for ( var i = 0 ; i < len ; i++ )
			{  
		    	if(entries[i].childNodes[0].text!=null)
	            {   			    	
		    		document.forms[0].elements('effFromDate').value=entries[i].childNodes[0].text;		       
	            }
			    if(entries[i].childNodes[1].text!=null)
	            {   			    	
			    	document.forms[0].elements('effToDate').value=entries[i].childNodes[1].text;		       
	            }
			    if(entries[i].childNodes[2].text!=null)
	            {   
			    	document.forms[0].elements('revReason').value=entries[i].childNodes[2].text;
	            }			    
			    if(entries[i].childNodes[3].text!=null)
	            {   
			    	document.forms[0].elements('compoType').value=entries[i].childNodes[3].text;
	            }			    
			    
			}		   
	  	}
	  	//Setting selected component
	  	var cmpValueListCtrl=document.forms[0].elements('compoType'); 
	  	if('${compoValueList}'!=null && '${compoValueList}' != '')
	  	{
		   var cmpValues='${compoValueList}';		 
		   if(cmpValues!=null && cmpValues!='')
		   {		   		   
			   for (var i=0;i<cmpValueListCtrl.length;i++)
			   {			   
				   if(cmpValues.indexOf(document.forms[0].elements('compoType').options[i].value)!=-1)
				   {
					   document.forms[0].elements('compoType').options[i].selected=true;
				   }
			   }
		  	}
	  	}	  	
	}	
}
var rdoBtnSearchFlg='1';
function getEmployees()
{
	var rdoCtrl = document.forms[0].elements('rdoBtnSearchFlg');		
	for (var r=0;r<rdoCtrl.length;r++)
	{		
		if(rdoCtrl[r].checked==true)
		{
			rdoBtnSearchFlg=rdoCtrl[r].value;			
		}	
	}
	
	var billSubHeadId = document.forms[0].elements('searchBillNo').value;
	
	///////////////////////////////////////////////////////////////////////////////////
	var searchBillNoListCtrl=document.forms[0].elements('searchBillNo');
 	var billSubHeadIds='0,';	   		   
    for (var i=0;i<searchBillNoListCtrl.length;i++)
    { 	
       if(searchBillNoListCtrl.options[i].value!=0 && searchBillNoListCtrl.options[i].selected==true)
	   {
		   billSubHeadIds=billSubHeadIds+searchBillNoListCtrl.options[i].value+',';
	   }
    }
    billSubHeadIds = billSubHeadIds.substring(0,billSubHeadIds.length-1)
    
	///////////////////////////////////////////////////////////////////////////////////
	var salRevOrderId = document.forms[0].elements('salRevOrder').value;
	if(salRevOrderId=='0')
	{
	 	 alert('Please select Salary Revision Order ');
	 	 return false;
	}	
	var groupBillNoId = document.forms[0].elements('groupBillNo').value;
	if(groupBillNoId=='0')
	{
	 	 alert('Please select Arrear Bill No');
	 	 return false;
	}	
	var selDesignationId = document.forms[0].elements('selDesignation').value;
	if(billSubHeadId=='0' && selDesignationId=='0')
	{
	 	 alert('Please select Bill No or Designation ');
	 	 return false;
	}	
	var compoTypeId = document.forms[0].elements('compoType').value;
		
	if(compoTypeId=='0' || compoTypeId=='')
	{
	 	 alert('Please select component type');
	 	 return false;
	}
	var orderEffDate = document.forms[0].elements('effFromDate').value;
	var orderEffEndDate = document.forms[0].elements('effToDate').value;
	
	
	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		  alert ("Your browser does not support AJAX!");
		  return;
	} 
	var url; 
	var uri='';
	url= '&lBillSubHeadId=' + billSubHeadIds+'&lDsgnId=' + selDesignationId+'&orderEffDate=' + orderEffDate+'&orderEffEndDate=' + 
		orderEffEndDate+'&rdoBtnSearchFlg='+rdoBtnSearchFlg;
	var actionf="getEmployeeDetailsToMapWithArrear";
	uri='./hrms.htm?actionFlag='+actionf;
	url=uri+url;
	searchFlag='Y';
	xmlHttp.onreadystatechange=getEmployeeDetailsToMapWithArrear;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);
	
}

function getEmployeeDetailsToMapWithArrear()
{	
	// getting Selected Component Type count
	var compoTypeCtrl = document.forms[0].elements('compoType');	
	var compoCounter=0;
	for (var j=0;j<compoTypeCtrl.options.length;j++)
	{
		if(compoTypeCtrl.options[j].selected==true)
			compoCounter=compoCounter+1;		
	}	
	//
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var empDetails = XMLDoc.getElementsByTagName('empDetails');
	   if(empDetails.length == 0)
	   {
	     window.status = 'No Records Found.';
	     alert('No Records Found.');	
	   }
	   else
	   {		    
		    window.status='';
			
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('empDetails');
		    len = entries.length;
		    		    
		    for ( var i = 0 ; i < len ; i++ )
			{  
		    	if(entries[i].childNodes[0].text!=null)
	            {   			    	
		            empId=entries[i].childNodes[0].text;		       
	            }
			    if(entries[i].childNodes[1].text!=null)
	            {   			    	
		            empName=entries[i].childNodes[1].text;		       
	            }
			    if(entries[i].childNodes[2].text!=null)
	            {   
			    	empPostId=entries[i].childNodes[2].text;
	            }
			    empPostId = empPostId + "R" +empId;
			    empDtlsInsRow(empName,compoCounter,empPostId);
			}		   
	  	}
	}	
	document.forms[0].elements('compoType').disabled=true;
}

function getEmployeesToCrtExcel()
{	
	var billSubHeadId = document.forms[0].elements('searchBillNo').value;
	//Reading selected searchBillNo values 	
	var searchBillNoListCtrl=document.forms[0].elements('searchBillNo');
 	var billSubHeadIds='0,';	   		   
    for (var i=0;i<searchBillNoListCtrl.length;i++)
    { 	
       if(searchBillNoListCtrl.options[i].value!=0 && searchBillNoListCtrl.options[i].selected==true)
	   {
		   billSubHeadIds=billSubHeadIds+searchBillNoListCtrl.options[i].value+',';
	   }
    }
    billSubHeadIds = billSubHeadIds.substring(0,billSubHeadIds.length-1)
    
    //Reading selected searchBillNo values ends
	//Reading selected component values
	var compoTypeListCtrl=document.forms[0].elements('compoType');
 	var compoTypeIds='0,';	   		   
    for (var i=0;i<compoTypeListCtrl.length;i++)
    { 	
       if(compoTypeListCtrl.options[i].value!=0 && compoTypeListCtrl.options[i].selected==true)
	   {
    	   var txtVal=compoTypeListCtrl.options[i].text;
    	   var val=compoTypeListCtrl.options[i].value
    	   compoTypeIds=compoTypeIds+txtVal+'_'+val+',';
	   }
    }
    compoTypeIds = compoTypeIds.substring(0,compoTypeIds.length-1)
  	//alert('compoTypeIds::'+compoTypeIds);
   //Reading selected component values ends
	
	var selDesignationId = document.forms[0].elements('selDesignation').value;
	if(billSubHeadId=='0' && selDesignationId=='0')
	{
	 	 alert('Please select Bill No or Designation first');
	 	 return false;
	}	
	var compoTypeId = document.forms[0].elements('compoType').value;
		
	if(compoTypeId=='0' || compoTypeId=='')
	{
	 	 alert('Please select component type');
	 	 return false;
	}
	var orderEffDate = document.forms[0].elements('effFromDate').value;
	var orderEffEndDate = document.forms[0].elements('effToDate').value;
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		  alert ("Your browser does not support AJAX!");
		  return;
	} 
	var url; 
	var uri='';
	
	document.forms[0].elements('lBillSubHeadId').value=billSubHeadIds;
	url= '&lBillSubHeadId=' + billSubHeadIds+'&lDsgnId=' + selDesignationId+'&orderEffDate=' + 
		orderEffDate+'&orderEffEndDate=' + orderEffEndDate+'&crtExcelFlag=true'+'&compoTypeIds='+compoTypeIds;
	var actionf="getEmployeeDetailsToMapWithArrear";
	uri='./hrms.htm?actionFlag='+actionf;
	url=uri+url;	
	
	alert("Excel Sheet creation started...");
	
	document.frmMapEmpWithArrear.action=url;
	document.frmMapEmpWithArrear.submit();	
}
//Added by rahul

//added for multiple registration
var empCounter=0;
var myCompoIdList=new Array();
var empArrearData=new Array();//variable to be sent data
function empDtlsInsRow(empName,compoCounter,empPostId)
{      
	
 if(validateForm())
 { 

	document.getElementById('compoCount').value= compoCounter;
   empCounter++;   
   document.getElementById('tblEmpDtls').style.display='';
   
	//Setting header
	if(empCounter==1)
	{
	   var compoTypeCtrl = document.forms[0].elements('compoType');	
	   var trow1=document.getElementById('tblEmpDtls').insertRow();
	   var td1 = trow1.insertCell(0);
	   var td2 = trow1.insertCell(1);
	
	   td1.innerHTML="<span > <center> <INPUT type='checkbox' name='selectAllChkBox' id='selectAllChkBox' onclick='selDeselectAll(this)' ></center></span>";
	   td2.innerHTML="<span > <b><center>  Employee Name  </center></b></span>";
	
		var tdt3;
		var tdtHeader;
		var cmpCtrLoop=0;
		var compoLoopVar=0;
		for (var j=0;j<compoTypeCtrl.options.length;j++)
		{
			//alert('Test:'+compoTypeCtrl.options[j].selected);
			if(compoTypeCtrl.options[j].selected!=false)
			{
				//compoCounter=compoCounter+1;
				//alert('compoCounter:'+compoCounter);
				tdtHeader=tdt3+compoCounter;
				tdtHeader=trow1.insertCell(2+cmpCtrLoop);
				   
				var cmpTypeName=compoTypeCtrl.options[j].text;
				tdtHeader.innerHTML="<span > <center> <b>"+cmpTypeName+"</b><INPUT type='text' style='text-align:right;' name="+compoTypeCtrl.options[j].value+"TxtBoxCompo"+empCounter+'Q'+cmpCtrLoop+" id="+compoTypeCtrl.options[j].value+"TxtBoxCompo"+empCounter+'Q'+cmpCtrLoop+" value=0 onblur='setSingleAmt(this)'></center></span>";				
				myCompoIdList[compoLoopVar]=compoTypeCtrl.options[j].value;
				createHiddenVariable("compoValue"+compoLoopVar,compoTypeCtrl.options[j].value);
				compoLoopVar=compoLoopVar+1;//this variable is used to set compoid list 				
				cmpCtrLoop=cmpCtrLoop+1;
			}		
		}
		// ends
//		alert('Header setting done..');
	}
	
   //Setting Data
   var trow2=document.getElementById('tblEmpDtls').insertRow();
   
   var tdata1=trow2.insertCell(0);  //First cell for check box
   var tdata2=trow2.insertCell(1);  //Second cell for EmpName

   var newItemChkBox="chkBoxCompo"+empCounter;   
   var lblEmpNameSpanId="lblEmpNameSpanId"+empCounter;
   var hiddenVarName='empArrear'+empCounter;
   createHiddenVariable(hiddenVarName,empPostId+'Z');//Creating varibale in which data will be send    
   tdata1.innerHTML="<center> <INPUT type='checkbox' name='newItemChkBox' id="+newItemChkBox+" VALUE="+empCounter+" ></center>";
   tdata2.innerHTML="<span  id="+lblEmpNameSpanId+"> <center> "+empName+" </center></span>";

   var tdata3;
   var tdt;
   var tempLoop=0;
   //Dynamic cell generation for creating text box for different components
   for (var loop=0;loop<compoCounter;loop++)
   {
	   tdt=tdata3+compoCounter;
	   tdt=trow2.insertCell(2+loop);
	   
	   cntr = eval(2+loop);   
	   var newItemTxtBox=myCompoIdList[loop]+"TxtBoxCompo"+empCounter+loop;
	   
	   tdt.innerHTML="<center> <INPUT type='text'  name="+newItemTxtBox+" id="+newItemTxtBox+" value=0 style='text-align:right;'></center>";
       tempLoop=cmpCtrLoop;
   }
       
   
   document.forms[0].newItemChkBoxcount.value="empCounter"; 
   document.forms[0].empCounter.value=empCounter;
   
 }    

}

function createHiddenVariable(name,value)
{              
	var hiddenElement=document.createElement("input");
	hiddenElement.setAttribute('name',name);
	hiddenElement.setAttribute('id',name);
	hiddenElement.setAttribute('type','hidden');
	hiddenElement.setAttribute('value',value);
	document.forms[0].appendChild(hiddenElement);  
}


function resetForm() 
{  
try {  		
	
		empCounter=0;
		crtExcelFlag='N';
		searchFlag='N';
		updExcelFlag='N';
		
		
		document.getElementById('attachExcel').style.display='none';
		//Deleting table rows
		var tblEmp = document.getElementById('tblEmpDtls');  
		var rowCount = tblEmp.rows.length;		  
		for(var i=0; i<rowCount; i++) 
		{
			var empRow = tblEmp.rows[i];
			tblEmp.deleteRow(i);
			rowCount--;  
			i--; 
		}  
		//
		//Deleteing hidden variables

		for (var j=1; j<=empCounter;j++)
		{
			var chkCtrl=document.getElementById("chkBoxCompo"+j);//getting check box controls			
			var empArrearVarCtrl=document.getElementById('empArrear'+j);			
		}
		//Enabling component ctrl
		
		var compoTypeCtrl = document.getElementById('compoType');
		compoTypeCtrl.disabled=false;
		for (var i=0;i<compoTypeCtrl.length;i++)
		{
			compoTypeCtrl.options[i].selected=false;
		}
		compoTypeCtrl.value=0;
		///////////////////////////////////////////////////
		var searchBillNoListCtrl = document.getElementById('searchBillNo');
		//searchBillNoListCtrl.disabled=false;
		for (var i=0;i<searchBillNoListCtrl.length;i++)
		{
			searchBillNoListCtrl.options[i].selected=false;
		}
		searchBillNoListCtrl.value=0;
		
	
	}catch(e) {  

		alert(e);  

	}  

}  

//ended multiple registration
function setSingleAmt(obj)
{	
	
	var cmpValue=obj.name.substring(0,obj.name.indexOf("T"));
	var intCnt=obj.name.substring(obj.name.indexOf("Q")+1,obj.name.length);	
	var totalCmpCount=document.getElementById('compoCount').value;
	for (var j=1; j<=empCounter;j++)
	{	
		var txtBoxCtrl;
		txtBoxCtrl=document.getElementById(cmpValue+"TxtBoxCompo"+j+''+intCnt);	        
        if(txtBoxCtrl!=null)
        {
	        txtBoxCtrl.value=obj.value;
        }
	}
}
var totalCheckBox=0;
function creatingHiddenDataVariable()
{		
	var totalCheckBox=0;
	var totalCmpCount=document.getElementById('compoCount').value;	
	for (var j=1; j<=empCounter;j++)
	{
		var chkCtrl=document.getElementById("chkBoxCompo"+j);//getting check box controls
		
		var empArrearVar='empArrear'+j;
		var empArrearVarCtrl=document.getElementById(empArrearVar);
		
		var txtBoxCtrl;
		var txtVal='';
		if(chkCtrl.checked==true)
		{
			totalCheckBox++;
	        for(var k=0;k<totalCmpCount;k++)
	        {
	             txtBoxCtrl=document.getElementById(myCompoIdList[k]+"TxtBoxCompo"+j+k); 
	             txtVal=txtVal+myCompoIdList[k]+'E'+txtBoxCtrl.value+'Z';
	        }
	        empArrearVarCtrl.value = empArrearVarCtrl.value+txtVal;	     
	        empArrearVarCtrl.value = empArrearVarCtrl.value.substring(0,empArrearVarCtrl.value.length-1);
	        empArrearVarCtrl.name = empArrearVarCtrl.name+'chk';

	        
		}          
		
	}
	document.getElementById('checkedEmpCount').value = totalCheckBox;
	if(crtExcelFlag=='Y')		
		totalCheckBox=1;
	if(totalCheckBox==0 && searchFlag=='Y')
	  {  	  
	     alert('Select atleast one Record');
	     return false;
	  }
	if(updExcelFlag=='N' && searchFlag=='N')
	{  	  
	     alert('Select atleast one Record or Upload Excel');
	     return false;
	  }
	return true;   
}
function validateForm()
{
	var fieldArray;			
	if(updExcelFlag=='Y')
	{ 
		fieldArray = new Array('salRevOrder','groupBillNo','arrearData');
	}
	else
	{				
		fieldArray = new Array('salRevOrder','groupBillNo');
	}
	var statusValidation = validateSpecificFormFields(fieldArray); 
	if(statusValidation)
	{	
		return true;
	}
	else
	{	
		return false;
	}
}
function saveInfo()
{			
	document.forms[0].elements('lOrderId').value=document.forms[0].elements('salRevOrder').value;
	if(!validateForm())
		return false;
	if(!creatingHiddenDataVariable())
		return false;		
	//Sending selected bill no
	var searchBillNoListCtrl=document.forms[0].elements('searchBillNo'); 
 	var billSubHeadIds='0,';	   		   
    for (var i=0;i<searchBillNoListCtrl.length;i++)
    { 	
       if(searchBillNoListCtrl.options[i].value!=0 && searchBillNoListCtrl.options[i].selected==true)
	   {
		   billSubHeadIds=billSubHeadIds+searchBillNoListCtrl.options[i].value+',';
	   }
    }
    billSubHeadIds = billSubHeadIds.substring(0,billSubHeadIds.length-1)
    document.forms[0].elements('lBillSubHeadId').value=billSubHeadIds;
    
	document.frmMapEmpWithArrear.action="hrms.htm?actionFlag=saveMapEmpWithArrearData&flag=add";
	document.frmMapEmpWithArrear.submit();
	document.frmMapEmpWithArrear.Submit.disabled=true;	
}
function setPaybillNoToSearch(obj)
{
	document.forms[0].elements('searchBillNo').value=obj.value;	
}
function selDeselectAll(obj)
{		
	var selAllChkFlg=obj.checked;
	for (var j=1; j<=empCounter; j++)
    {	
    	document.forms[0].elements('chkBoxCompo'+j).checked=selAllChkFlg;
    }	
}
function updExcel()
{
	updExcelFlag="Y";
	document.getElementById('attachExcel').style.display='';	
}
function closeWindow()
{
	document.frmMapEmpWithArrear.action="hrms.htm?actionFlag=getEmpArrearBillMpgList";
	document.frmMapEmpWithArrear.submit();
}
function addEmployeeUsingSearch()
{	
	var salRevOrderId = document.forms[0].elements('salRevOrder').value;
	if(salRevOrderId=='0')
	{
	 	 alert('Please select Salary Revision Order ');
	 	 return false;
	}	
	var groupBillNoId = document.forms[0].elements('groupBillNo').value;
	if(groupBillNoId=='0')
	{
	 	 alert('Please select Arrear Bill No');
	 	 return false;
	}	
	var compoTypeCtrl = document.forms[0].elements('compoType');	
			
	if(compoTypeCtrl.value=='0' || compoTypeCtrl.value=='')
	{
	 	 alert('Please select component type');
	 	 return false;
	}
	var empId=document.getElementById("Employee_ID_EmployeeSearch").value;	
	if(empId=="")
	{	
		alert("Please search the employee first");
		return false;
	}
	else
	{
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			  alert ("Your browser does not support AJAX!");
			  return;
		} 
		var url; 
		var uri='';
		url= '&empId=' + empId;
		var actionf="getEmpDetailsUsingSearch";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+url;		
		xmlHttp.onreadystatechange=addEmployeeStatusChanged;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);		
	}	
}
function addEmployeeStatusChanged()
{	
	// getting Selected Component Type count
	var compoTypeCtrl = document.forms[0].elements('compoType');	
	var compoCounter=0;
		
	if(compoTypeCtrl.value=='0' || compoTypeCtrl.value=='')
	{
	 	 alert('Please select component type');
	 	 return false;
	}
	for (var j=0;j<compoTypeCtrl.options.length;j++)
	{
		if(compoTypeCtrl.options[j].selected==true)
			compoCounter=compoCounter+1;		
	}	
	//
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var empDetailsFromId = XMLDoc.getElementsByTagName('empDetailsFromId');
	   if(empDetailsFromId.length == 0)
	   {
	     window.status = 'No Records Found.';
	     alert('No Records Found.');	
	   }
	   else
	   {		    
		    window.status='';
		    searchFlag='Y';
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('empDetailsFromId');
		    len = entries.length;

		    var empPostId=0;
			var hrEmpId=0;
			var empName='';
			var gradeName='';
			var desigName='';
			var psrNo=0;				    
		    for ( var i = 0 ; i < len ; i++ )
			{
		    	if(entries[i].childNodes[0].text!=null)
	            {   			    	
		    		empPostId=entries[i].childNodes[0].text;		       
	            }
		    	if(entries[i].childNodes[1].text!=null)
	            {   			    	
			    	hrEmpId=entries[i].childNodes[1].text;		       
	            }
			    if(entries[i].childNodes[2].text!=null)
	            {   
			    	empName=entries[i].childNodes[2].text;
	            }
			    if(entries[i].childNodes[3].text!=null)
	            {   
			    	gradeName=entries[i].childNodes[3].text;
	            }
			    if(entries[i].childNodes[4].text!=null)
	            {   
			    	desigName=entries[i].childNodes[4].text;
	            }
			    psrNo=0;
			    empPostId = empPostId + "R" +hrEmpId;
				empName = empName+'('+gradeName+','+desigName+','+psrNo+')';
			    empDtlsInsRow(empName,compoCounter,empPostId);
			}		   
	  	}
	}	
	
	return false;
}
/*
function checkSalRevOrderBillMpgExists(obj)
{	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		  alert ("Your browser does not support AJAX!");
		  return;
	} 
	var url=''; 
	var uri='';
	var salRevId ;
	salRevId=document.forms[0].elements('salRevOrder').value;
	if(salRevId=='0')
	{
		alert('Please select Salary Revision Order First!');
	}
	
	var actionf="checkSalRevOrderBillMpgExists&salRevId="+salRevId+"&billSubheadId="+obj.value;
	uri='./hrms.htm?actionFlag='+actionf;
	url=uri+url;
	xmlHttp.onreadystatechange=verifySalRevOrdBillMpg;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}
function verifySalRevOrdBillMpg()
{			
	if (xmlHttp.readyState==complete_state)
	 { 
	   var XMLDoc=xmlHttp.responseXML.documentElement;	
	
	   var salBillMpgDetails = XMLDoc.getElementsByTagName('salBillMpgDetails');
	   if(salBillMpgDetails.length == 0)
	   {
	     window.status = 'No Records Found.';	     
	   }
	   else
	   {		    
		    window.status='';			
			var XMLDoc=xmlHttp.responseXML.documentElement;
		    var entries = XMLDoc.getElementsByTagName('salBillMpgDetails');
		    len = entries.length;
		    var salBillMpg='';		  
		    for ( var i = 0 ; i < len ; i++ )
			{  
		    	if(entries[i].childNodes[0].text!=null)
	            {   			    	
		    		salBillMpg=entries[i].childNodes[0].text;		   
		    		if(salBillMpg=='Yes')
		    		{
			    		alert('This Mapping allready Exists!Please select another bill no or order');
			    		return false;
		    		}    
	            }
			   
			}	
		    	   
	  	}	  	
	   document.forms[0].elements('searchBillNo').value=obj.value;		  	
	}	
} */

--></script>
<hdiits:form name="frmMapEmpWithArrear" validate="true" method="POST" action=""  encType="multipart/form-data">
<hdiits:hidden name="newItemChkBoxcount" default="1" />
<hdiits:hidden name="checkedEmpCount" default="1" />
<hdiits:hidden name="empCounter" default="1" />
<hdiits:hidden name="compoCount" default="1" />
<hdiits:hidden name="lOrderId" default="${lOrderId}" />
<hdiits:hidden name="flag" default="${flag}" />
<hdiits:hidden name="lBillSubHeadId" />

	<link rel='stylesheet' href='<c:url value="/themes/hdiits/tabcontent.css"/>' type='text/css' />
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Map Employee with Arrear Bill</b></a></li>
	</ul>
	</div>

<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
		<hdiits:hidden name="postId" default="" />		 
		<hdiits:fieldGroup titleCaptionId="pay.salRevOrderDetails" bundle="${commonLables}" > 
		<table align="center" cellspacing="2" cellpadding="2" border="0" width="100%">
		<tr>			
			<td width="25%" align="left" class="Label"><b><fmt:message key="pay.salRevOrderName" bundle="${commonLables}"/></b></td>
			<td align="left" width="25%" >
				<hdiits:select id="salRevOrder" name="salRevOrder" size="1" sort="false" validation="sel.isrequired" 
				mandatory="true" captionid="pay.salRevOrderName" bundle="${commonLables}" onchange="getSalRevOrderDetails(this)">
					<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
					<c:if test="${salRevMstVOList ne null }">
						<c:forEach var="salRevMst" items="${salRevMstVOList}">
							<hdiits:option value="${salRevMst.salRevId}">
							<fmt:formatDate var="orderDate" value="${salRevMst.revOrderDate}" pattern="dd/MM/yyyy" dateStyle="medium"  />	
								<c:out value="${salRevMst.revOrderNo}(${orderDate})">
								</c:out>
							</hdiits:option>
						</c:forEach>
					</c:if>
				</hdiits:select></td>
			<td width="25%" class="Label"><b><fmt:message key="pay.groupBillNo" bundle="${commonLables}"/></b>
			</td>
			<td width="25%" align="left">		 
				<hdiits:select name="groupBillNo"
					size="1" id="groupBillNo" sort="false" caption="BillNo"
					captionid="pay.groupBillNo" validation="sel.isrequired" mandatory="true"
					default="" bundle="${commonLables}" onchange="setPaybillNoToSearch(this)">
					<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
					<c:forEach var="billList" items="${billNoList}">
						<hdiits:option value="${billList.billHeadId}">
							<c:out value="${billList.billId}">
							</c:out>
						</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>		
		
		<tr>
			<td class="Label" width="25%"><b><fmt:message key="SRM.EffFromDate" bundle="${commonLables}" /></b></td>
			<td width="25%"><hdiits:text captionid="SRM.EffFromDate"
				bundle="${commonLables}" name="effFromDate"  readonly="true"/></TD>

			<td class="Label" width="25%"><b><fmt:message key="SRM.EffToDate" bundle="${commonLables}" /></b></td>
			<td width="25%"><hdiits:text captionid="SRM.EffToDate"
				bundle="${commonLables}" name="effToDate" readonly="true"/></TD>
		</tr>
		<tr>
			<td><b><fmt:message key="SRM.revReason" bundle="${commonLables}"/></b></td>
			<td colspan="3"><hdiits:textarea cols="80" rows="3" name="revReason" default="" captionid="SRM.revReason" bundle="${commonLables}"  readonly="true"/> </td>
	    </tr>
	    <tr>
	    
			<td class="Label" width="25%"><b><fmt:message key="pay.withOrWithoutAmt" bundle="${commonLables}" /></b></td>
			<td width="25%">
				<hdiits:select name="staticAmtOrNot" size="1" id="staticAmtOrNot" sort="false" 
					captionid="pay.withOrWithoutAmt" validation="sel.isrequired" mandatory="true"
					default="" bundle="${commonLables}">
					<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>					
					<hdiits:option value="withStatic" selected="true">
						<fmt:message key="pay.withStatic" bundle="${commonLables}" />							
					</hdiits:option>
					<hdiits:option value="withoutStatic">
						<fmt:message key="pay.withoutStatic" bundle="${commonLables}" />							
					</hdiits:option>
				</hdiits:select></TD>

			<td class="Label" width="25%"></td>
			<td width="25%"></TD>
		</tr>
		</table>
		</hdiits:fieldGroup>
		<hdiits:fieldGroup titleCaptionId="pay.searchCriteria" bundle="${commonLables}" > 
		<table align="center" cellspacing="2" cellpadding="2" border="0" width="100%">
	
		<tr>			
			<td width="25%" align="left" class="Label"><b><fmt:message key="pay.payBillNo" bundle="${commonLables}"/></b></td>
			<td align="left" width="25%" >
				<hdiits:select name="searchBillNo" 
					size="5" id="searchBillNo" sort="false" caption="BillNo"
					captionid="pay.payBillNo" validation="sel.isrequired" mandatory="true"
					default="" bundle="${commonLables}" multiple="true" >
					<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
					<c:forEach var="billList" items="${billNoList}">
						<hdiits:option value="${billList.billHeadId}">
							<c:out value="${billList.billId}">
							</c:out>
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
			<td width="25%" class="Label"><b><fmt:message key="pay.desig" bundle="${commonLables}"/></b>
			</td>
			<td width="25%" align="left">		 
				<hdiits:select captionid="DSGN" bundle="${userPostMapping}" name="selDesignation" id="selDesignation" sort="false" style="width:240px"
						caption="Designation" validation="sel.isrequired" mandatory="true" default="${empCustomVO.dsgnId}">
					<hdiits:option value="0" selected="true">
						<hdiits:caption captionid="SELECT" bundle="${userPostMapping}" />
					</hdiits:option>
					<c:forEach var="orgDesignationMst" items="${arDesignationVO}">
						<hdiits:option value="${orgDesignationMst.dsgnCode}">${orgDesignationMst.dsgnName}</hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
		</tr>
		<tr>
		<td width="25%" align="left" >
		<b><fmt:message key="pay.compoType" bundle="${commonLables}"/></b>
		</td>
		<td width="25%" align="left" >
			<hdiits:select name="compoType" size="10" id="compoType" captionid="pay.compoType" mandatory="true" bundle="${commonLables}"
				onchange="" validation="sel.isrequired" multiple="true" sort="false" >
				<hdiits:option value="0" selected="true" >----Select----</hdiits:option>
				<c:forEach var="rltBillList" items="${rltBillList}">			
					<c:if test="${rltBillList.typeEdpId eq 76}">		
						<hdiits:option value="${rltBillList.typeEdpId}">
							<c:out value="${rltBillList.edpShortName}">
							</c:out>
						</hdiits:option>
					</c:if>
					<c:if test="${rltBillList.typeEdpId ne 76}">		
						<hdiits:option value="${rltBillList.typeEdpId}">
							<c:out value="${rltBillList.edpShortName}(${rltBillList.edpCode})">
							</c:out>
						</hdiits:option>
					</c:if>					
				</c:forEach>
				
				
			</hdiits:select>
		</td>
		<td width="25%" align="left" >
		<b><fmt:message key="pay.pblgen" bundle="${commonLables}"/></b>
		</td>
			<td width="25%" align="left" >
				<hdiits:radio name="rdoBtnSearchFlg" id="rdoBtnSearchFlg" value="1" caption="Yes" errCaption="pay.pblgen"  bundle="${commonLables}" default="1" />
				<hdiits:radio name="rdoBtnSearchFlg" id="rdoBtnSearchFlg" value="2" caption="No" errCaption="pay.pblgen"  bundle="${commonLables}" />
			</td>
		</tr>
		<tr>		
		<td width="25%" align="left" colspan="4">
				
		</td>
		
		</tr>
		<tr>		
		<td width="25%" align="center" colspan="4">
			<hdiits:button name="searchButton" type="button" captionid="pay.searchBtn" bundle="${commonLables}" onclick="getEmployees()"/>			
			<hdiits:button name="crtExcelButton" type="button" captionid="pay.crtExcelBtn" bundle="${commonLables}" onclick="getEmployeesToCrtExcel()"/>
			<hdiits:button name="uploadExcelButton" type="button" captionid="pay.updExcelBtn" bundle="${commonLables}" onclick="updExcel()"/>			
		</td>
		
		</tr>
		<tr>
			<td width="25%" align="center" colspan="4">
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmployeeSearch"/>
						<jsp:param name="formName" value="frmMapEmpWithArrear"/>
					</jsp:include>
			</td>
		</tr>
		<tr>
			<td width="25%" align="center" colspan="4">
				<hdiits:button name="addEmpUsingSearchBtn" type="button" captionid="pay.addEmp" bundle="${commonLables}" onclick="addEmployeeUsingSearch()"/>
			</td>
		</tr>
		
		</table>
		</hdiits:fieldGroup>
		
		<hdiits:fieldGroup titleCaptionId="pay.lblEmpDetails" bundle="${commonLables}" >
		<table align="center" cellspacing="2" cellpadding="2" border="0" width="100%">
		<tr>
		<td>
			<table width="100%" id="tblEmpDtls" border="1" cellpadding="0" cellspacing="0">	                  
	      	</table>
			</td>
		</tr>
		<tr>
		<td colspan="4" >
			<div id="attachExcel" style="display:none">
						<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="arrearData" />
            	    		<jsp:param name="formName" value="frmMapEmpWithArrear" />
	            	    	<jsp:param name="attachmentType" value="Excel" />
				    		<jsp:param name="multiple" value="N" />
				    		<jsp:param name="mandatory" value="Y"/>              
	    				</jsp:include>
	    	</div> 
		</td>
		</tr>	
		</table>
		</hdiits:fieldGroup>
		<table align="center">
			<tr>
			<td align="right" colspan="2">
				<hdiits:button name="Submit" type="button" captionid="SUBMIT" bundle="${empEditListCommonLables}" onclick="saveInfo()"></hdiits:button>
			</td>		
	        <td align="left" colspan="1">
	             <hdiits:button name="delButton" type="button" captionid="RESET" bundle="${empEditListCommonLables}" onclick="resetForm()"/>
	         </td>
	         <td align="left" colspan="1">
	             <hdiits:button name="closeButton" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="closeWindow()"/>
	         </td>	          	         
	       </tr>
		</table>
	</div>
	
	<script type="text/javascript">
	<!--
		initializetabcontent("maintab");
		if('${msg}'!=null && '${msg}'!='')
		{
			alert('${msg}' );
		}	
		getSalRevOrderDetails('${lOrderId}','${flag}');		
	-->
	</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="salRevOrder,groupBillNo,arrearData" />
</div>
</hdiits:form>
<%
	} catch (Exception e) {
	e.printStackTrace();
}
%>
