var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount=0
var rowFCnt=0;
var rowNCnt=0;
function TierICntrnbtnTableAddRow()
{
		
     	var empId=document.getElementById("txtEmployeeId").value ;    		
		rowFCnt = document.getElementById("hidGridSize").value;
	    var newRow =  document.all("tblTierICntrnbtn").insertRow();		    

		  
		var Cell1 = newRow.insertCell();
   		var Cell2 = newRow.insertCell();
   		var Cell3 = newRow.insertCell();
   		var Cell4 = newRow.insertCell();
   		var Cell5 = newRow.insertCell();
   		var Cell6 = newRow.insertCell(); 
   		var Cell7 = newRow.insertCell();
   		var Cell8 = newRow.insertCell();
   		var Cell9 = newRow.insertCell();
   		var Cell10 = newRow.insertCell();
   		var Cell11 = newRow.insertCell();
   		var Cell12 = newRow.insertCell();
   		var Cell13 = newRow.insertCell();
   		var Cell14 = newRow.insertCell();
   		var Cell15 = newRow.insertCell();
   		var Cell16 = newRow.insertCell();    		
   		
   		
   		Cell1.innerHTML = '<input type="text" name="txtSrNo" id="txtSrNo'+Number(rowFCnt)+'" size="10" onkeypress="numberFormat(this);"   style="width:40px" value="'+rowFCnt+'" readonly="readonly"/>';
   		Cell2.innerHTML ='<input type="text" name="txtFinancialYr" id="txtFinancialYr'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" readonly="readonly" value="2011"/>'; 
   		Cell3.innerHTML ='<input type="text" name="txtEmpPnsnId" id="txtEmpPnsnId'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px"/>';
   		Cell4.innerHTML ='<input type="text" name="txtEmpName" id="txtEmpName'+Number(rowFCnt)+'" size="60"  style="width:150px" />';
   		Cell5.innerHTML ='<input type="text" name="txtAmount" id="txtAmount'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" value="'+document.getElementById('txtTotalAmt').value+'" readonly="readonly"/>';
   		Cell6.innerHTML = '<input type="text" size="20"   name="TierICntrnbtnFromDate" onblur=\"validateDate(this);\" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" id="TierICntrnbtnFromDate'+Number(rowFCnt)+'" class="texttag"  readonly="readonly" value="'+document.getElementById('txtFromDate').value+'"/>';  		
   		Cell7.innerHTML = '<input type="text" size="20"   name="TierICntrnbtnToDate" onblur=\"validateDate(this);\" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" id="TierICntrnbtnToDate'+Number(rowFCnt)+'" class="texttag" readonly="readonly" value="'+document.getElementById('txtToDate').value+'" />';
   		Cell8.innerHTML = '<img name="Image" id="Image'+Number(rowFCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblTierICntrnbtn")\'/><input type="hidden" id="pk'+Number(rowFCnt)+'" name="pk">';
   	   	

   		Cell9.innerHTML = '<input type="hidden" name="hidTypeOfArrearId" id="hidTypeOfArrearId'+Number(rowFCnt)+'" value="'+document.getElementById('cmbTypeOfArrear').options[document.getElementById('cmbTypeOfArrear').selectedIndex].value+'" />';
   		Cell10.innerHTML = '<input type="hidden" name="hidFieldDeptId" id="hidFieldDeptId'+Number(rowFCnt)+'" value="'+document.getElementById('cmbFieldDept').options[document.getElementById('cmbFieldDept').selectedIndex].value+'"/>';
   		Cell11.innerHTML = '<input type="hidden" name="hidDesignationId" id="hidDesignationId'+Number(rowFCnt)+'" value="'+document.getElementById('cmbDesignation').options[document.getElementById('cmbDesignation').selectedIndex].value+'"/>';
   		Cell12.innerHTML = '<input type="hidden" name="hidNoOfInstlmnt" id="hidNoOfInstlmnt'+Number(rowFCnt)+'" value="'+document.getElementById('txtNoOfInstallment').value+'" />';
   		Cell13.innerHTML = '<input type="hidden" name="hidMonthInstlmntAmt" id="hidMonthInstlmntAmt'+Number(rowFCnt)+'" value="'+document.getElementById('txtMonthInstlmnt').value+'" />';
   		Cell14.innerHTML = '<input type="hidden" name="hidFirstOddInstlmntAmt" id="hidFirstOddInstlmntAmt'+Number(rowFCnt)+'" value="'+document.getElementById('txtFirstOddInstlmnt').value+'" />';
   		Cell15.innerHTML = '<input type="hidden" name="hidLastOddInstlmntAmt" id="hidLastOddInstlmntAmt'+Number(rowFCnt)+'" value="'+document.getElementById('txtLastOddInstlmnt').value+'" />';
   		Cell16.innerHTML = '<input type="hidden" name="hidEmpId" id="hidEmpId'+Number(rowFCnt)+'" value="'+document.getElementById('txtEmployeeId').value+'" />';
	  
   		getData(empId,Number(rowFCnt));  
   		document.getElementById("hidGridSize").value = Number(rowFCnt)+1;   	
	 
   		resetAllFields();
	 
	  
}

function resetAllFields()
{
	document.getElementById('cmbTypeOfArrear').value=-1;
	document.getElementById('cmbFieldDept').value=-1;
	document.getElementById('cmbDesignation').value=-1;
	document.getElementById("txtFromDate").value='';
	document.getElementById("txtToDate").value='';
	document.getElementById("txtTotalAmt").value='';
	document.getElementById("txtNoOfInstallment").value='';
	document.getElementById("txtMonthInstlmnt").value='';
	document.getElementById("txtFirstOddInstlmnt").value='';
	document.getElementById("txtLastOddInstlmnt").value='';
	document.getElementById("chkEmployee").checked = false;
	document.getElementById("txtEmployeeId").value='';	
	
	
}
function RemoveTableRow(obj, tblId)
{	
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 	
}

function showRowCell(element)
{
    var cell, row;    
    if (element.parentNode) 
    {
    	
      do
      	cell = element.parentNode;
    	while (cell.tagName.toLowerCase() != 'td')
     	row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
       do
      	cell= element.parentElement;
    	  while (cell.tagName.toLowerCase() != 'td')
      		row = cell.parentElement;
    }
    return row.rowIndex;
}

function SaveData()// When the Save button is pressed this function is being called
{
//   if(validDCPSArrears() == true)
//   {	 
      var uri = 'ifms.htm?actionFlag=insertDCPSTierI';
     
      saveDCPSTierIUsingAjx(uri);     
     // resetAllFields();
//   } 
}

function saveDCPSTierIUsingAjx(uri)
{	
   xmlHttp=GetXmlHttpObject();
   if (xmlHttp==null)
   {	  
      return;
   }     
   var url = runForm(0);   
   url = uri + url;   
   xmlHttp.onreadystatechange=caseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);   
}


function caseStateChanged() 
{ 
   if (xmlHttp.readyState==4)
   { 	   
      var XMLDoc=xmlHttp.responseXML.documentElement;    
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');      
    
      if(XmlHiddenValues[0].childNodes[0].text=='insert')
      {         
         alert("Inserted");   
      }
      else if(XmlHiddenValues[0].childNodes[1].text=='update')
      {
         alert("Updated");
      }           
   }
}
function getData(empId,rowCnt)
{
     var uri = "ifms.htm?actionFlag=getDCPSTierI&empId="+ empId + "&rowCnt=" + rowCnt; 	  
     getDCPSTierIUsingAjx(uri);
}
function getDCPSTierIUsingAjx(uri){

	xmlHttp=GetXmlHttpObject();
	   if (xmlHttp==null)
	   {
		  
	      return;
	   }  	 	   
	   var url = runForm(0); 	  
	   url = uri + url; 	  
	   xmlHttp.onreadystatechange=getCaseStateChanged;
	   xmlHttp.open("POST",uri,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(url);
}

function getCaseStateChanged() 
{ 
   if (xmlHttp.readyState==complete_state)
   { 	
      var XMLDoc=xmlHttp.responseXML.documentElement;     
      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');      

         empId=XmlHiddenValues[0].childNodes[0].text;
         empName=XmlHiddenValues[0].childNodes[1].text;
         rowCnt=XmlHiddenValues[0].childNodes[2].text;     
         document.getElementById("txtEmpPnsnId"+rowCnt).value=empId;
         document.getElementById("txtEmpName"+rowCnt).value=empName;    
         document.getElementById("txtEmpPnsnId"+rowCnt).readOnly=true;
         document.getElementById("txtEmpName"+rowCnt).readOnly=true;
         
   }
}

function validDCPSArrears(){
	
	if(document.getElementById("cmbTypeOfArrear").value==-1)
	{
		alert(ARREARTYPE);
		return false;
	}
	if(document.getElementById("cmbFieldDept").value==-1)
	{
		alert(DEPT);
		return false;
	}
	if(document.getElementById("cmbDesignation").value==-1)
	{
		alert(DESG);
		return false;
	}
	if(isEmpty("txtFromDate",FROMDATE)==false)
	{
		return false;
	}
	if(isEmpty("txtToDate",TODATE)==false)
	{
		return false;
	}
	if(isEmpty("txtTotalAmt",TOTALAMT)==false)
	{
		return false;
	}
	if(isEmpty("txtNoOfInstallment",NOOFINSTLMNT)==false)
	{
		return false;
	}
	if(isEmpty("txtMonthInstlmnt",TOTALMONTH)==false)
	{
		return false;
	}
	if(isEmpty("txtFirstOddInstlmnt",FSTODD)==false)
	{
		return false;
	}
	if(isEmpty("txtLastOddInstlmnt",LASTODD)==false)
	{
		return false;
	}
	if(isEmpty("txtEmployeeId",EMPID)==false)
	{
		return false;
	}
	
}

